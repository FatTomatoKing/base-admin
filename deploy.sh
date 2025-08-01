#!/bin/bash

# set -v 

# 修改APP_NAME为应用名 （需修改第6和14行）
APP_NAME=base-admin


PROG_NAME=$0
ACTION=$1
APP_START_TIMEOUT=120    # 等待应用启动的时间
APP_PORT=9998           # 应用端口
HEALTH_CHECK_URL=http://127.0.0.1:${APP_PORT}/prod-api/actuator/health  # 应用健康检查URL
APP_HOME=./base-admin # 从package.tgz中解压出来的jar包放到这个目录下
JAR_NAME=${APP_HOME}/${APP_NAME}.jar # jar包的名字
JAVA_OUT=${APP_HOME}/logs/start.log  #应用的启动日志
SPRING_PROFILES_ACTIVE=zard  # 系统运行激活环境
USER_TIMEZONE=Asia/Shanghai  # 系统运行时区

# 创建出相关目录
mkdir -p ${APP_HOME}
mkdir -p ${APP_HOME}/logs
usage() {
    echo "Usage: $PROG_NAME {start|stop|restart}"
    exit 2
}

health_check() {
    exptime=0
    echo "checking ${HEALTH_CHECK_URL}"
    while true
        do
            status_code=`/usr/bin/curl -L -o /dev/null --connect-timeout 5 -s -w %{http_code}  ${HEALTH_CHECK_URL}`
            if [ "$?" != "0" ]; then
               echo -n -e "\rapplication not started"
            else
                echo "code is $status_code"
                if [ "$status_code" == "200" ];then
                    break
                fi
            fi
            sleep 1
            ((exptime++))

            echo -e "\rWait app to pass health check: $exptime..."

            if [ $exptime -gt ${APP_START_TIMEOUT} ]; then
                echo 'app start failed'
               exit 1
            fi
        done
    echo "check ${HEALTH_CHECK_URL} success"
}
start_application() {
    echo "starting java process"
    nohup java -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -Duser.timezone=${USER_TIMEZONE} -jar ${JAR_NAME}  > ${JAVA_OUT} 2>&1 &
    echo "started java process"
}

stop_application() {
   checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
   
   if [[ ! $checkjavapid ]];then
      echo -e "\rno java process"
      return
   fi

   echo "stop java process"
   sleep 1
   kill $checkjavapid

   times=60
   for e in $(seq 60)
   do
        sleep 1
        COSTTIME=$(($times - $e ))
        checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
        if [[ $checkjavapid ]];then
#            kill -9 $checkjavapid
            echo -e  "\r        -- stopping java lasts `expr $COSTTIME` seconds."
        else
            echo -e "\rjava process has exited"
            break;
        fi
   done

   checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
   if [[ $checkjavapid ]]; then
     kill -9 $checkjavapid

      times=60
      for e in $(seq 60)
      do
           sleep 1
           COSTTIME=$(($times - $e ))
           checkjavapid=`ps -ef | grep java | grep ${APP_NAME} | grep -v grep |grep -v 'deploy.sh'| awk '{print$2}'`
           if [[ $checkjavapid ]];then
               echo -e  "\r        -- stopping java lasts `expr $COSTTIME` seconds."
           else
               echo -e "\rjava process has exited"
               break;
           fi
      done
   fi

   echo ""
}
start() {
    start_application
    health_check
}
stop() {
    stop_application
}
case "$ACTION" in
    start)
        start
    ;;
    stop)
        stop
    ;;
    restart)
        stop
        start
    ;;
    *)
        usage
    ;;
esac


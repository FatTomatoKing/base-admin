#!/bin/bash
#需修改第3行
APP_HOME=base-admin
set +v 

select_branch_view(){
 echo "请选择分支"
 git branch -a --sort=-committerdate | grep origin | grep -v 'HEAD' | cut -d/ -f 3 | nl -s ")"
 max_number=$(git branch -a | grep origin | grep -v 'HEAD' -c)
 read -r -p "选择 [1]: " number
 until [[ -z "$number" || "$number" =~ ^[1-9]+$ && "$number" -le "$max_number" ]]; do
    echo "$number: 无效的选择."
    read -r -p "选择 [1]: " number
 done
 number="${number:-1}"
 branch=$(git branch -a --sort=-committerdate | grep origin | grep -v 'HEAD' | cut -d/ -f 3 | sed -n "${number}p")
 if [[ -n "$branch" ]]; then
    git pull
    echo "git checkout $branch"	
    git checkout "$branch"
    git pull
 else
    echo "没有选择有效的分支."
 fi
}

select_branch_view



mvn clean package -P package-history -s settings.xml -Dmaven.test.skip=true


cd target
unzip ${APP_HOME}*-bin.zip -d temp
mkdir ${APP_HOME}
mv temp/${APP_HOME}*/${APP_HOME}-*.jar ../${APP_HOME}/${APP_HOME}.jar
mv temp/${APP_HOME}*/lib ../${APP_HOME}/
rm -rf temp
cd ../


sh deploy.sh restart




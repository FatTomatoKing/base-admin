package com.biz.util;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class HttpsUtils {
    private static void initRequestFactory(HttpComponentsClientHttpRequestFactory factory)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException
    {
        TrustStrategy acceptingTrustStrategy = (x509Certificates, authType) -> true;
        SSLContext sslContext = SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy).build();
        SSLConnectionSocketFactory connectionSocketFactory = new SSLConnectionSocketFactory(
                sslContext, new NoopHostnameVerifier());

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setSSLSocketFactory(connectionSocketFactory);
        CloseableHttpClient httpClient = httpClientBuilder.build();
        factory.setHttpClient(httpClient);
    }

    /**
     * RestTemplate 对https 的支持
     * 只要通过此创建的RestTemplate 就可以使用https 进行访问服务器端了
     */
    public static RestTemplate httpsRestTemplate(HttpComponentsClientHttpRequestFactory factory)
            throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        if (factory == null) {
            factory = new HttpComponentsClientHttpRequestFactory();
        }
        HttpsUtils.initRequestFactory(factory);
        return new RestTemplate(factory);
    }
}

package com.cevher.keycloak;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.eclipse.microprofile.config.ConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Client {
    private static final String ENDPOINT = ConfigProvider.getConfig().getValue("membership-maestro.endpoint",
            String.class);
    private static final Logger LOG = LoggerFactory.getLogger(CustomEventListenerProvider.class);

    public static void postServiceAdminEvent(String data) throws IOException {

        // Do Http POST request to Service
        // Generated by ChatGPT

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uriAdmin = String.format("http://%s/startRegistrationProcess/Admin", ENDPOINT);

        LOG.info("POST Event Data to: " + uriAdmin);

        try {
            HttpPost httpPost = new HttpPost(uriAdmin);

            // JSON payload
            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity, "UTF-8");
                    EntityUtils.consume(responseEntity);
                    LOG.info("Response status: " + response.getStatusLine().getStatusCode());
                    LOG.info("Response body: " + responseString);

                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {

        } finally {
            httpClient.close();
        }
    }

    public static void postServiceUserEvent(String data) throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uriUser = String.format("http://%s/startRegistrationProcess/User", ENDPOINT);

        LOG.info("POST Event Data to: " + uriUser);

        try {
            HttpPost httpPost = new HttpPost(uriUser);

            // Json Payload
            StringEntity entity = new StringEntity(data, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            try {
                HttpEntity responseEntity = response.getEntity();
                if (responseEntity != null) {
                    String responseString = EntityUtils.toString(responseEntity, "UTF-8");
                    EntityUtils.consume(responseEntity);
                    LOG.info("Response status: " + response.getStatusLine().getStatusCode());
                    LOG.info("Response body: " + responseString);

                }
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {

        } finally {
            httpClient.close();
        }

    }
}
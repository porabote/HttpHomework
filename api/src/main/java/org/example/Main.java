package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.util.List;

public class Main {

    static String sourceUrl = "https://raw.githubusercontent.com/netology-code/jd-homeworks/master/http/task1/cats";

    public static void main(String[] args) throws IOException {

        try (
                CloseableHttpClient httpClient = HttpClientBuilder.create()
                        .setDefaultRequestConfig(RequestConfig.custom()
                                .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                                .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                                .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                                .build())
                        .build();
        ) {
            // create request object
            HttpGet request = new HttpGet(sourceUrl);
            request.setHeader(HttpHeaders.ACCEPT, ContentType.APPLICATION_JSON.getMimeType());

            //send request
            CloseableHttpResponse httpResponse = httpClient.execute(request);

            ObjectMapper objectMapper = new ObjectMapper();

            List<Cat> cats = objectMapper.readValue(
                    httpResponse.getEntity().getContent(),
                    new TypeReference<>() {

                    }
            );

            cats.stream()
                    .filter(value -> value.getUpvotes() != null && value.getUpvotes() > 0)
                    .forEach(System.out::println);

            httpResponse.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}

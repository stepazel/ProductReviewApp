package com.isep.api.gateway.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class HttpService {

    private final HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();

    public HttpService() {
        //
    }

    public <T> T sendGetRequest(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Accept",
                "application/json").build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public <T> List<T> sendGetListRequest(String url, Class<T> responseType) throws IOException, InterruptedException {

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).header("Accept",
                "application/json").build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(response.body(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, responseType));
    }

    public <T> T sendPostRequest(String url, Object body, Class<T> responseType) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String       requestBody  = objectMapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(requestBody))
                                         .uri(URI.create(url)).header("Accept", "application/json").header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public <T> T sendPutRequest(String url, Object body, Class<T> responseType) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String       requestBody  = objectMapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder().PUT(HttpRequest.BodyPublishers.ofString(requestBody))
                                         .uri(URI.create(url)).header("Accept", "application/json").header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public <T> T sendDeleteRequest(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url)).header("Accept",
                "application/json").build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public void sendDeleteRequest(String url) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url)).header("Accept",
                "application/json").build();

        httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public <T> T sendPatchRequest(String url, Object body, Class<T> responseType) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        String       requestBody  = objectMapper.writeValueAsString(body);

        HttpRequest request = HttpRequest.newBuilder().method("PATCH", HttpRequest.BodyPublishers.ofString(requestBody))
                                         .uri(URI.create(url)).header("Accept", "application/json").header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public <T> T sendPatchRequest(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().method("PATCH", HttpRequest.BodyPublishers.noBody())
                                         .uri(URI.create(url)).header("Accept", "application/json").header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }

    public <T> T sendPostRequest(String url, Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.noBody())
                                         .uri(URI.create(url)).header("Accept", "application/json").header("Content-Type", "application/json")
                                         .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return new ObjectMapper().readValue(response.body(), responseType);
    }
}

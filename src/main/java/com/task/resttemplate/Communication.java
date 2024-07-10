package com.task.resttemplate;

import com.task.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


import java.util.List;

@Component
public class Communication {
    @Autowired
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private List<String> cookies;

    private String response;

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<User> getUsers() {
        ResponseEntity<List<User>> responseEntity =
                restTemplate.exchange(URL, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);
        return responseEntity.getBody();
    }

    public void createUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        if (cookies != null) {
            headers.add(HttpHeaders.COOKIE, String.join("; ", cookies));
        }
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        response = responseEntity.getBody();
        System.out.println(response);
    }

    public void updateUser(User user) {
        HttpHeaders headers = new HttpHeaders();
        if (cookies != null) {
            headers.add(HttpHeaders.COOKIE, String.join("; ", cookies));
        }
        HttpEntity<User> entity = new HttpEntity<>(user, headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        response = response + responseEntity.getBody();
        System.out.println(response);
    }

    public void deleteUser(Long id) {
        HttpHeaders headers = new HttpHeaders();
        if (cookies != null) {
            headers.add(HttpHeaders.COOKIE, String.join("; ", cookies));
        }
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity =
                restTemplate.exchange(URL + "/" + id, HttpMethod.DELETE, entity, String.class);
        response = response + responseEntity.getBody();
        System.out.println(response);
    }
}

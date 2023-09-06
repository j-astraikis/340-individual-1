package com.example.individual1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Individual1Application {

    public static void main(String[] args) {
        SpringApplication.run(Individual1Application.class, args);

        String word = "fun";

        try {
            String url = "https://api.datamuse.com/words?ml=" + word;
            RestTemplate restTemplate = new RestTemplate();
            ObjectMapper mapper = new ObjectMapper();

            String similarWords = restTemplate.getForObject(url, String.class);
            JsonNode root = mapper.readTree(similarWords);

            System.out.println("\nwords similar to " + word + ":");
            for (int i = 0; i < 5; i++) {
                System.out.println(root.get(i).get("word"));
            }
        } catch (JsonProcessingException ex) {
            System.out.println("ERROR: " + ex.toString());
        }
    }

}

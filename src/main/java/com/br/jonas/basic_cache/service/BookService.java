package com.br.jonas.basic_cache.service;

import com.br.jonas.basic_cache.model.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class BookService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();

    @Cacheable("books")
    public Book getBookByTitle(String title) {
        try {
            System.out.println("🔍 Calling external API for: " + title);

            String url = "https://openlibrary.org/search.json?q=" 
             + URLEncoder.encode(title, StandardCharsets.UTF_8);
            String response = restTemplate.getForObject(url, String.class);

            JsonNode root = mapper.readTree(response);
            JsonNode docs = root.path("docs");

            if (docs.isEmpty() || docs.size() == 0) {
                System.out.println("⚠️ No result found for: " + title);
                return null;
            }

            // Get first result
            JsonNode first = docs.get(0);

            String bookTitle = first.path("title").asText("Unknown title");
            String author = first.path("author_name").isArray()
                    ? first.path("author_name").get(0).asText("Unknown author")
                    : "Unknown author";
            String year = first.path("first_publish_year").asText("Unknown year");

            System.out.println("📚 Book found: " + bookTitle + " (" + author + ", " + year + ")");
            return new Book(bookTitle, author, year);

        } catch (RestClientException e) {
            System.err.println("❌ Error when calling external API: " + e.getMessage());
        } catch (JsonProcessingException e) {
            System.err.println("❌ Error when processing JSON response: " + e.getMessage());
        }

        return null;
    }
}

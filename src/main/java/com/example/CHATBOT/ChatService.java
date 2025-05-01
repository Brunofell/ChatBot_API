package com.example.CHATBOT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatService {

    @Value("${news.api.key}")
    private String apiKey;

    public String buscarNoticiasFuria() {
        String url = "https://newsapi.org/v2/everything?q=FURIA&apiKey=" + apiKey;

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);

        // Aqui você pode fazer a lógica para processar e formatar as notícias
        return response;
    }

}

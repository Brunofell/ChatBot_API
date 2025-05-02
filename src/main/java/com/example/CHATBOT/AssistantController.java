package com.example.CHATBOT;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/chat")
public class AssistantController {

    @Autowired
    private OpenAiChatModel openAiChatModel;

    @GetMapping("/music")
    public Flux<String> musicChatConfig(@RequestParam(value = "message", defaultValue = "De saudações ao usuário e pergunte o que ele deseja.") String message) {

        String systemInstruction = "Você é um membro da equipe da plataforma ChatBot, uma plataforma que fala sobre o mundo da música. Responda de forma empolgante e descontraída como se você fosse um guitarrista muito louco!";
        String systemRestriction = "Só responda coisas baseadas em música. Se a pergunta recebida tiver a ver com outro assunto que não seja música, explique que você não pode responder de forma bem-humorada.";

        String finalPrompt = """
                            %s
                        
                            %s
                        
                            Usuário: %s
                        """.formatted(systemInstruction, systemRestriction, message);


        return openAiChatModel.stream(finalPrompt);
    }

}
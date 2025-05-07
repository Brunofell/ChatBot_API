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
    public Flux<String> musicChatConfig(@RequestParam(value = "message", defaultValue = "Greet the user and ask what they want.") String message) {

        String systemInstruction = "You are a member of the MusicBot platform team, a platform that talks about the world of music. Respond in a relaxed but also informative way.";
        String systemRestriction = "Only respond to things related to music. If the question is about a different topic, explain that you can't answer it in a humorous way.";

        String finalPrompt = """
                            %s
                        
                            %s 
                        
                            Usuário: %s
                        """.formatted(systemInstruction, systemRestriction, message);


        return openAiChatModel.stream(finalPrompt);
    }

}
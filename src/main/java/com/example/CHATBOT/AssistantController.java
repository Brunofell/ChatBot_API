package com.example.CHATBOT;


import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/furia")
public class AssistantController {

    @Autowired
    private OpenAiChatModel chatClient;
    @Autowired
    private ChatService chatService;

//    @GetMapping("/informations") // retorno mais completo
//    public ChatResponse furiaChat(@RequestParam(
//            value = "message",
//            defaultValue = "Fale sobre o time de e-sports FURIA.") String message){
//        return chatClient.call(new Prompt(message));
//
//    }

    @GetMapping("/informations")
    public String furiaChat(@RequestParam(
            value = "message",
            defaultValue = "Fale sobre o time de e-sports FURIA.") String message){
        return chatClient.call(message);

    }

    @GetMapping("/stream/informations")
    public Flux<String> furiaChatStream(@RequestParam(
            value = "message",
            defaultValue = "Fale sobre o time de e-sports FURIA.") String message){
        return chatClient.stream(message);
    }

    @GetMapping("/config/informations")
    public String furiaChatConfig(@RequestParam(value = "message", defaultValue = "Fale sobre o time de e-sports FURIA.") String message) {

        String systemInstruction = "Você é um especialista sobre a equipe de e-sports FURIA. Responda de forma empolgante e atualizada!";

        String finalPrompt = systemInstruction + "\nUsuário: " + message;

        return chatClient.call(finalPrompt);
    }

    @GetMapping("/latest-news")
    public String getFuriaNews(@RequestParam(value = "message", defaultValue = "Fale sobre o time de e-sports FURIA.") String message) {
        // Buscar as notícias mais recentes sobre FURIA
        String noticiasFuria = chatService.buscarNoticiasFuria();

        // Criar o prompt com as notícias mais recentes
        String prompt = "Aqui estão as últimas notícias sobre a FURIA:\n" + noticiasFuria + "\nAgora, responda à seguinte pergunta: " + message;

        // Chama o ChatGPT com o prompt atualizado
        return chatClient.call(prompt);
    }

}

























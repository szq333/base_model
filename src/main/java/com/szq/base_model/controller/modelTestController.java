package com.szq.base_model.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author szq
 **/
@RestController
@Slf4j
@RequestMapping("/modelTest")
public class modelTestController {

    private final ChatClient dashCopeChatClient;
    private final ChatClient deepSeekChatClient;
    private final ChatClient openAiChatClient;

    //建议采用构造的方法注入
    public modelTestController(@Qualifier("myDashScopeChatModel") ChatModel dashCopeChatModel,
                               @Qualifier("myDeepSeekChatModel") ChatModel deepSeekChatModel,
                               @Qualifier("myOpenAiChatModel") ChatModel openAiChatModel
    ) {
        this.dashCopeChatClient = ChatClient.builder(dashCopeChatModel).build();
        this.deepSeekChatClient = ChatClient.builder(deepSeekChatModel).build();
        this.openAiChatClient = ChatClient.builder(openAiChatModel).build();
    }

    @GetMapping("/test-dashScopeClient")
    public String testDashScopeClient(@RequestParam(value = "userQuestion",defaultValue = "先告诉我你是谁，简短评价两眼一睁就是“瓦”") String userQuestion){
        log.info("用户问题：{}",userQuestion);
        String result = dashCopeChatClient.prompt().user(userQuestion).call().content();
        log.info("模型结果：{}",result);
        return result;
    }
    @GetMapping("/test-deepSeekClient")
    public String testDeepSeekChatClient(@RequestParam(value = "userQuestion",defaultValue = "先告诉我你是谁，简短评价两眼一睁就是“瓦”") String userQuestion){
        log.info("用户问题：{}",userQuestion);
        String result = deepSeekChatClient.prompt().user(userQuestion).call().content();
        log.info("模型结果：{}",result);
        return result;
    } @GetMapping("/test-openAiClient")
    public String testOpenAiChatClient(@RequestParam(value = "userQuestion",defaultValue = "先告诉我你是谁，简短评价两眼一睁就是“瓦”") String userQuestion){
        log.info("用户问题：{}",userQuestion);
        String result = openAiChatClient.prompt().user(userQuestion).call().content();
        log.info("模型结果：{}",result);
        return result;
    }
}

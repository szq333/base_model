package com.szq.base_model.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.deepseek.DeepSeekChatModel;
import org.springframework.ai.deepseek.DeepSeekChatOptions;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author szq
 **/
@Configuration
public class MyClientConfig {

    @Value("${spring.ai.dashscope.chat.options.model}")
    private String dashScopeModel;

    @Value("${spring.ai.deepseek.chat.options.model}")
    private String deepSeekModel;

    @Value("${spring.ai.openai.chat.options.model}")
    private String openAiModel;

    @Bean("myDashScopeChatModel")
    public DashScopeChatModel dashScopeChatModel(@Qualifier("myDashScopeApi") DashScopeApi dashScopeApi) {
        return DashScopeChatModel.builder()
                .dashScopeApi(dashScopeApi)
                .defaultOptions(DashScopeChatOptions.builder().withModel(dashScopeModel).build())
                .build();
    }

    @Bean("myDeepSeekChatModel")
    public DeepSeekChatModel deepseekChatModel(@Qualifier("myDeepSeekApi") DeepSeekApi deepSeekApi) {
        return DeepSeekChatModel.builder()
                .deepSeekApi(deepSeekApi)
                .defaultOptions(DeepSeekChatOptions.builder().model(deepSeekModel).build())
                .build();
    }

    @Bean("myOpenAiChatModel")
    public OpenAiChatModel openAiChatModel(@Qualifier("myOpenAiApi") OpenAiApi openAiApi) {
        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(OpenAiChatOptions.builder().model(openAiModel).build())
                .build();
    }
}

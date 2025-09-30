package com.szq.base_model.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 打印所有 ChatModel Bean 的名称
 */
@Configuration
public class BeanNamePrinter {

    @Bean
    public CommandLineRunner printBeans(ApplicationContext applicationContext) {
        return args -> {
            System.out.println("\n========== 所有的 ChatModel Bean ==========");
            String[] beanNames = applicationContext.getBeanNamesForType(ChatModel.class);
            for (String beanName : beanNames) {
                System.out.println("找到 Bean: " + beanName + " - 类型: " + applicationContext.getBean(beanName).getClass().getSimpleName());
            }
            System.out.println("总共找到 " + beanNames.length + " 个 ChatModel Bean");
            System.out.println("==========================================\n");
        };
    }
}

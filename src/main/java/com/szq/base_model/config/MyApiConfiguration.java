package com.szq.base_model.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import org.springframework.ai.deepseek.api.DeepSeekApi;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyApiConfiguration {

	@Value("${spring.ai.dashscope.api-key}")
	private String myDashScopeApiKey;

	@Value("${spring.ai.dashscope.base-url}")
	private String myDashScopeBaseUrl;

	@Value("${spring.ai.deepseek.api-key}")
	private String myDeepSeekApiKey;

	@Value("${spring.ai.deepseek.base-url}")
	private String myDeepSeekBaseUrl;

	@Value("${spring.ai.openai.api-key}")
	private String myOpenAiApiKey;

	@Value("${spring.ai.openai.base-url}")
	private String myOpenAiBaseUrl;

	/**
	 * 百炼调用时需要配置 DashScope API，对 dashScopeApi 强依赖。
	 * @return
	 */

	@Bean("myDashScopeApi")
	public DashScopeApi dashScopeApi() {
		return DashScopeApi.builder()
				.apiKey(myDashScopeApiKey)
				.baseUrl(myDashScopeBaseUrl)
				.build();
	}

	@Bean("myDeepSeekApi")
	public DeepSeekApi deepSeekApi() {
		return DeepSeekApi.builder()
				.apiKey(myDeepSeekApiKey)
				.baseUrl(myDeepSeekBaseUrl)
				.build();
	}

	@Bean("myOpenAiApi")
	public OpenAiApi openAiApi() {
		return OpenAiApi.builder()
				.apiKey(myOpenAiApiKey)
				.baseUrl(myOpenAiBaseUrl)
				.build();
	}


}
# base_model

一个基于 Spring Boot 3 + Spring AI 的多模型示例工程，集成了 DashScope（阿里百炼）、DeepSeek、OpenAI 兼容接口（如硅基流动）。

## 环境要求
- JDK 21
- Maven 3.9+（项目已提供 `mvnw/mvnw.cmd`）

## 快速开始
1. 配置密钥与地址：编辑 `src/main/resources/application.yml`，填入你的 API Key 与 base-url。
2. 启动应用（Windows）：
```bash
.\mvnw.cmd spring-boot:run
```
或打包运行：
```bash
.\mvnw.cmd -DskipTests package
java -jar target/base_model-0.0.1-SNAPSHOT.jar
```
3. 访问示例接口（默认端口 8080）：
- DashScope: `GET http://localhost:8080/modelTest/test-dashScopeClient?userQuestion=你好`
- DeepSeek: `GET http://localhost:8080/modelTest/test-deepSeekClient?userQuestion=你好`
- OpenAI 兼容: `GET http://localhost:8080/modelTest/test-openAiClient?userQuestion=你好`

示例 curl：
```bash
curl "http://localhost:8080/modelTest/test-openAiClient?userQuestion=你好"
```

## 配置说明（关键片段）
`application.yml` 中需配置三家服务的 base-url、api-key 和默认模型：
```yaml
spring:
  ai:
    dashscope:
      base-url: https://dashscope.aliyuncs.com/compatible-mode/v1
      api-key: <你的DashScopeKey>
      chat:
        options:
          model: qwen-plus
    deepseek:
      base-url: https://api.deepseek.com/v1
      api-key: <你的DeepSeekKey>
      chat:
        options:
          model: deepseek-chat
    openai:
      base-url: https://api.siliconflow.cn
      api-key: <你的OpenAI兼容Key>
      chat:
        options:
          model: deepseek-ai/DeepSeek-V3.1
```
> 注意：不同网关/代理的 `base-url` 及 `model` 名称可能不同，请以服务商文档为准。

## 代码结构
- `com.szq.base_model.config.MyApiConfiguration`：构建各平台的 Api（读取 base-url 与 api-key）。
- `com.szq.base_model.config.MyClientConfig`：构建各平台 ChatModel，并将 `application.yml` 中的模型名注入为默认选项。
- `com.szq.base_model.controller.modelTestController`：提供三个演示接口，分别调用不同模型。

## 常见问题
- 启动报错找不到 Bean：检查 `@Qualifier` 名称与配置类 Bean 名是否一致（本项目为 `myDashScopeChatModel`、`myDeepSeekChatModel`、`myOpenAiChatModel`）。
- 实际请求发到非预期域名：确保自定义的 Api builder 已设置 `baseUrl`，并确认 `application.yml` 的 `spring.ai.*.base-url` 正确。
- 返回 400 "Model does not exist"：通常是模型名与服务商不匹配。请核对 `application.yml` 的模型名是否为该平台真实可用的模型 ID。

## 调试建议
- 开启调试日志：在启动参数中添加 `--debug` 查看条件装配报告。
- 建议引入 Actuator 并访问 `/actuator/beans` 查看最终创建的 Bean 名称与来源（可选）。

## 许可证
仅示例用途，请根据实际需要增补。

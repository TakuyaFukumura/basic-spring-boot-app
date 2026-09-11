package com.example.myapplication.service.impl;

import com.example.myapplication.service.AiService;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.Map;

/** Gemini APIを利用するAIサービスの実装。 */
@Service
public class AiServiceImpl implements AiService {

    private static final Logger logger = LoggerFactory.getLogger(AiServiceImpl.class);

    private final WebClient webClient;
    private final ObjectMapper objectMapper;

    @Value("${app.ai.gemini.api-key:}")
    private String apiKey;

    @Value("${app.ai.gemini.model:gemini-2.5-flash-lite}")
    private String model;

    public AiServiceImpl(WebClient webClient, ObjectMapper objectMapper) {
        this.webClient = webClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getTrivia() {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            logger.error("Gemini APIキーが設定されていません");
            throw new IllegalStateException("APIキーが設定されていません。");
        }

        try {
            String response = webClient.post()
                    .uri("https://generativelanguage.googleapis.com/v1beta/models/" + model + ":generateContent")
                    .header("Content-Type", "application/json")
                    .header("X-Goog-Api-Key", apiKey)
                    .bodyValue(createGeminiRequestBody())
                    .retrieve()
                    .bodyToMono(String.class)
                    .timeout(Duration.ofSeconds(30))
                    .block();
            return parseGeminiResponse(response);
        } catch (WebClientResponseException e) {
            logger.error("Gemini API呼び出しでHTTPエラーが発生", e);
            throw new RuntimeException("API呼び出しに失敗しました。");
        } catch (Exception e) {
            logger.error("Gemini API呼び出しで予期しないエラーが発生", e);
            throw new RuntimeException("API呼び出しで予期しないエラーが発生しました。");
        }
    }

    private static Map<String, Object> createGeminiRequestBody() {
        return Map.of(
                "contents", new Object[]{Map.of("parts", new Object[]{
                        Map.of("text", "100文字程度の日本語で豆知識を教えてください。")
                })},
                "generationConfig", Map.of("temperature", 2.0, "maxOutputTokens", 200));
    }

    private String parseGeminiResponse(String response) {
        try {
            JsonNode candidates = objectMapper.readTree(response).get("candidates");
            if (candidates != null && candidates.isArray() && !candidates.isEmpty()) {
                JsonNode content = candidates.get(0).get("content");
                if (content != null) {
                    JsonNode parts = content.get("parts");
                    if (parts != null && parts.isArray() && !parts.isEmpty()) {
                        JsonNode text = parts.get(0).get("text");
                        if (text != null) {
                            return text.asText().trim();
                        }
                    }
                }
            }
            throw new RuntimeException("AIからの応答を解析できませんでした。期待される形式ではありません。");
        } catch (Exception e) {
            logger.error("Gemini APIレスポンスの解析中にエラーが発生", e);
            throw new RuntimeException("AIからの応答を解析できませんでした。");
        }
    }
}

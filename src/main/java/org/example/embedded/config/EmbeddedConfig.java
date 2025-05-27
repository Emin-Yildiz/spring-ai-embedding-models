package org.example.embedded.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.transformers.TransformersEmbeddingModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class EmbeddedConfig {

    @Bean
    public EmbeddingModel embeddingModel() {
        TransformersEmbeddingModel model = new TransformersEmbeddingModel();
        model.setTokenizerResource("classpath:/onnx/all-MiniLM-L6-v2/tokenizer.json");
        model.setModelResource("classpath:/onnx/all-MiniLM-L6-v2/model.onnx");
        model.setResourceCacheDirectory("/tmp/onnx-zoo");
        model.setTokenizerOptions(Map.of("padding", "true"));

        return model;
    }

}

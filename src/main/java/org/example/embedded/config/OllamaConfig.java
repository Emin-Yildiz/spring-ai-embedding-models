package org.example.embedded.config;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OllamaConfig {

    @Bean
    public OllamaApi ollamaApi() {
        return OllamaApi.builder().build();
    }

    @Bean
    public OllamaOptions ollamaOptions(){
        return OllamaOptions.builder()
                .model("chroma/all-minilm-l6-v2-f32")
                .truncate(false)
                .build();
    }

    @Bean
    public ObservationRegistry observationRegistry() {
        return ObservationRegistry.create();
    }

    @Bean
    public ModelManagementOptions modelManagementOptions() {
        return ModelManagementOptions.builder().build();
    }

    @Bean
    public OllamaEmbeddingModel ollamaEmbeddingModel(OllamaApi ollamaApi, OllamaOptions ollamaOptions, ObservationRegistry observationRegistry, ModelManagementOptions modelManagementOptions) {
        return new OllamaEmbeddingModel(ollamaApi,ollamaOptions,observationRegistry,modelManagementOptions);
    }
}

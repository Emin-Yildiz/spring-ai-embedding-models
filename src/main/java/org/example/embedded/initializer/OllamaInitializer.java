package org.example.embedded.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class OllamaInitializer implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(OllamaInitializer.class);

    private final OllamaApi ollamaApi;

    public OllamaInitializer(OllamaApi ollamaApi) {
        this.ollamaApi = ollamaApi;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        OllamaApi.PullModelRequest pullModelRequest = new OllamaApi.PullModelRequest("chroma/all-minilm-l6-v2-f32");
        var progressResponseFlux =  ollamaApi.pullModel(pullModelRequest).doOnNext(progress -> {
                    logger.info("İlerleme: " + progress);
                })
                .doOnComplete(() -> {
                    logger.info("✅ Model indirildi!");
                })
                .doOnError(error -> {
                    logger.error("❌ Hata: " + error.getMessage());
                })
                .subscribe();
    }
}

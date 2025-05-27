package org.example.embedded.servis;

import org.example.embedded.util.VectorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmbeddedService implements ApplicationRunner {

    private final EmbeddingModel embeddingModel;

    private final OllamaApi ollamaApi;

    private final OllamaOptions ollamaOptions;

    private final OllamaEmbeddingModel ollamaEmbeddingModel;

    private final Logger logger = LoggerFactory.getLogger(EmbeddedService.class);

    public EmbeddedService(EmbeddingModel embeddingModel, OllamaApi ollamaApi, OllamaOptions ollamaOptions, OllamaEmbeddingModel ollamaEmbeddingModel) {
        this.embeddingModel = embeddingModel;
        this.ollamaApi = ollamaApi;
        this.ollamaOptions = ollamaOptions;
        this.ollamaEmbeddingModel = ollamaEmbeddingModel;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        EmbeddingRequest embeddingRequest = new EmbeddingRequest(List.of("Hello world"),ollamaOptions);
        logger.info("Selamlarrr");
        List<float[]> onnxEmbeddings = embeddingModel.embed(List.of("Hello world"));
        EmbeddingResponse embeddingResponse = ollamaEmbeddingModel.call(embeddingRequest);
        float[] ollamaEmbeddings = embeddingResponse.getResult().getOutput();
        var result = VectorUtils.cosineSimilarity(onnxEmbeddings.getFirst(),ollamaEmbeddings);
        logger.info("Vector Similarity Ratio: {}",result);
    }
}

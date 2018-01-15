package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.model.Recommendation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// TODO add RxJava
@FeignClient(name = "recommendation-service", fallback = RecommendationClient.RecommendationClientFallback.class)
public interface RecommendationClient {

    @RequestMapping(method = RequestMethod.GET, value = "/recommendation/{productId}")
    ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable("productId") Long productId);

    @Component
    class RecommendationClientFallback implements RecommendationClient {

        Logger LOG = LoggerFactory.getLogger(RecommendationClientFallback.class);

        @Override
        public ResponseEntity<List<Recommendation>> getRecommendations(@PathVariable("productId") Long productId) {
            LOG.warn("fallback => getRecommendations(" + productId + ")");
            return ResponseEntity.ok(Collections.singletonList(new Recommendation(0L, "Recommendation Fallback")));
        }
    }
}

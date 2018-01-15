package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import br.com.makeshiftonjava.productcomposite.model.Recommendation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductAggregatedService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductAggregatedService.class);

    private final RecommendationClient recommendationClient;
    private final ProductClient productClient;

    @Autowired
    public ProductAggregatedService(RecommendationClient recommendationClient, ProductClient client) {
        this.recommendationClient = recommendationClient;
        this.productClient = client;
    }

    public ProductAggregated getProduct(Long productId) {
        LOG.info("init - getProduct(" + productId + ")");
        return productClient.getProduct(productId).getBody();
    }

    public List<Recommendation> getRecommendations(Long productId) {
        LOG.info("init - getRecommendations(" + productId + ")");
        return recommendationClient.getRecommendations(productId).getBody();
    }
}

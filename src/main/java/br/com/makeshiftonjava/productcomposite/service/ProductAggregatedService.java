package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductAggregatedService {

    private static final Logger LOG = LoggerFactory.getLogger(ProductAggregatedService.class);

    private final CrossSellingClient crossSellingClient;
    private final ProductClient productClient;

    @Autowired
    public ProductAggregatedService(CrossSellingClient crossSellingClient, ProductClient client) {
        this.crossSellingClient = crossSellingClient;
        this.productClient = client;
    }

    public ProductAggregated getProduct(Long productId) {
        LOG.info("init - getProduct(" + productId + ")");
        return productClient.getProduct(productId).getBody();
    }

    public ProductAggregated getCrossSelling(Long productId) {
        LOG.info("init - getCrossSelling(" + productId + ")");
        return crossSellingClient.getCrossSelling(productId).getBody();
    }
}

package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "cross-selling-service", fallback = CrossSellingClient.CrossSellingClientFallback.class)
public interface CrossSellingClient {

    @RequestMapping(method = RequestMethod.GET, value = "/cross-selling/{productId}")
    ResponseEntity<ProductAggregated> getCrossSelling(@PathVariable("productId") Long productId);

    @Component
    class CrossSellingClientFallback implements CrossSellingClient {

        Logger LOG = LoggerFactory.getLogger(CrossSellingClientFallback.class);

        @Override
        public ResponseEntity<ProductAggregated> getCrossSelling(@PathVariable("productId") Long productId) {
            LOG.warn("fallback => getCrossSelling(" + productId + ")");
            return ResponseEntity.ok(new ProductAggregated("Cross Selling Fallback", 707070));
        }
    }
}

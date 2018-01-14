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


@FeignClient(name = "product-service", fallback = ProductClient.ProductClientFallback.class)
interface ProductClient {

    @RequestMapping(method = RequestMethod.GET, value = "/products/{productId}")
    ResponseEntity<ProductAggregated> getProduct(@PathVariable("productId") Long productId);

    @Component
    class ProductClientFallback implements ProductClient {

        Logger LOG = LoggerFactory.getLogger(ProductClientFallback.class);

        @Override
        public ResponseEntity<ProductAggregated> getProduct(@PathVariable("productId") Long productId) {
            LOG.warn("fallback => getProduct(" + productId + ")");
            return ResponseEntity.ok(new ProductAggregated("Product Fallback", 3213213));
        }
    }
}
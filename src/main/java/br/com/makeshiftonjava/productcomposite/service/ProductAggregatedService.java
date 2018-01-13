package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.ProductCompositeApplication;
import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductAggregatedService {

    private final LoadBalancerClient loadBalancer;
    private final RestTemplate restTemplate;

    @Autowired
    public ProductAggregatedService(LoadBalancerClient loadBalancer, RestTemplate restTemplate) {
        this.loadBalancer = loadBalancer;
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultProduct")
    public ProductAggregated getProduct(Long productId) {

        final ServiceInstance productInstance = loadBalancer.choose("product-service");
        final String productUrl = productInstance.getUri().toString() + "/products/" + productId;
        return restTemplate.getForEntity(productUrl, ProductAggregated.class).getBody();
    }

    @HystrixCommand(fallbackMethod = "defaultCrossSelling")
    public ProductAggregated getCrossSelling(Long productId) {
        final ServiceInstance crossSelingInstance = loadBalancer.choose("cross-selling-service");
        final String crossSellingUrl = crossSelingInstance.getUri().toString() + "/cross-selling/" + productId;
        return restTemplate.getForEntity(crossSellingUrl, ProductAggregated.class).getBody();
    }

    public ProductAggregated defaultProduct(Long productId) {
        return new ProductAggregated("Product Fallback", 0);
    }

    public ProductAggregated defaultCrossSelling(Long productId) {
        return new ProductAggregated("", 666);
    }
}

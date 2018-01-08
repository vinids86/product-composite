package br.com.makeshiftonjava.productcomposite.service;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
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

    public ProductAggregated getProductAggregated(Long productId) {
        final ServiceInstance instance = loadBalancer.choose("product-service");
        final String url = instance.getUri().toString() + "/products/" + productId;
        return restTemplate.getForEntity(url, ProductAggregated.class).getBody();
    }
}

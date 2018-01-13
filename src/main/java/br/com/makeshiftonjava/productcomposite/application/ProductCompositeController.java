package br.com.makeshiftonjava.productcomposite.application;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import br.com.makeshiftonjava.productcomposite.service.ProductAggregatedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-composite")
public class ProductCompositeController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeController.class);

    private ProductAggregatedService service;

    @Autowired
    public ProductCompositeController(ProductAggregatedService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductAggregated> retrieveProductComposite(@PathVariable Long productId) {
        LOG.info("ProductComposite: product-composite/productId/" + productId);
        final ProductAggregated productAggregated = service.getProduct(productId);
        final ProductAggregated crossSelling = service.getCrossSelling(productId);
        productAggregated.setProductId(crossSelling.getProductId());
        LOG.info("returned productAggregated: " + productAggregated.getName() + " " + productAggregated.getProductId());
        return ResponseEntity.ok(productAggregated);
    }
}

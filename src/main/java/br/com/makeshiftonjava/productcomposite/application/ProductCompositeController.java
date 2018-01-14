package br.com.makeshiftonjava.productcomposite.application;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import br.com.makeshiftonjava.productcomposite.service.ProductAggregatedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class ProductCompositeController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductCompositeController.class);

    private ProductAggregatedService service;

    @Autowired
    public ProductCompositeController(ProductAggregatedService service) {
        this.service = service;
    }

    @PreAuthorize("hasAuthority('FOO_READ')")
    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductAggregated> retrieveProductComposite(
            @PathVariable Long productId,
            @RequestHeader(value = "Authorization") String authorizationHeader,
            Principal currentUser) {
        LOG.info("ProductComposite: product-composite/productId/" + productId);
        final ProductAggregated productAggregated = service.getProduct(productId);
        final ProductAggregated crossSelling = service.getCrossSelling(productId);
        productAggregated.setProductId(crossSelling.getProductId());
        LOG.info("returned productAggregated: " + productAggregated.getName() + " " + productAggregated.getProductId());
        return ResponseEntity.ok(productAggregated);
    }
}

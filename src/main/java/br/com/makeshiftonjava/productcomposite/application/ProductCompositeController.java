package br.com.makeshiftonjava.productcomposite.application;

import br.com.makeshiftonjava.productcomposite.model.ProductAggregated;
import br.com.makeshiftonjava.productcomposite.service.ProductAggregatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product-composite")
public class ProductCompositeController {

    private ProductAggregatedService service;

    @Autowired
    public ProductCompositeController(ProductAggregatedService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{productId}", method = RequestMethod.GET)
    public ResponseEntity<ProductAggregated> retrieveProductComposite(@PathVariable Long productId) {

        final ProductAggregated productAggregated = service.getProductAggregated(productId);
        return ResponseEntity.ok(productAggregated);
    }
}

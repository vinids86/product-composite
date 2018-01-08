package br.com.makeshiftonjava.productcomposite.model;

public class ProductAggregated {

    private Long productId;
    private String name;

    public ProductAggregated() {
    }

    public ProductAggregated(Long productId) {
        this.productId = productId;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

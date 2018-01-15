package br.com.makeshiftonjava.productcomposite.model;

import java.util.List;

public class ProductAggregated {

    private Long id;
    private String name;

    private List<Recommendation> recommendations;

    public ProductAggregated() {
    }

    public ProductAggregated(Long id) {
        this.id = id;
    }

    public ProductAggregated(String name, Long id) {
        this.name = name;
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recommendation> getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
    }
}

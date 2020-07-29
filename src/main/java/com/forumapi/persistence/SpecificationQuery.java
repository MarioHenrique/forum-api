package com.forumapi.persistence;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

public class SpecificationQuery<T> {

    private Pagination pagination;

    public SpecificationQuery(Pagination pagination) {
        this.pagination = pagination;
    }

    private Specification<T> emptySpecification(){
        return (root, query, builder) -> null;
    }

    public Specification<T> equal(String field, Object value){
        if(value == null){
            return emptySpecification();
        }

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.equal(root.get(field), value);
    }

    public Specification<T> like(String field, String value){
        if(value == null){
            return emptySpecification();
        }

        return (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get(field), "%"+value+"%");
    }

    public PageRequest getPagination(){
        return PageRequest.of(this.pagination.getPage() -1, this.pagination.getSize());
    }

}

package com.io.dropwizardhibernate;

import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.core.Product;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProductMapper {

    public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product apiToEntity(ProductRequest request);

    com.io.dropwizardhibernate.api.Product entityToApi(Product product);

    void mergeOldAndSavingEntity(Product old, @MappingTarget Product saving);
}

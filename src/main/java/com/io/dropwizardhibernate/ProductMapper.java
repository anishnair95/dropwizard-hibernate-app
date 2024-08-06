package com.io.dropwizardhibernate;

import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.core.Product;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface ProductMapper {

    public static ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    Product apiToEntity(ProductRequest request);

    com.io.dropwizardhibernate.api.Product entityToApi(Product product);

    void mergeOldAndSavingEntity(Product old, @MappingTarget Product saving);

    @Mapping(target = "productName", source = "productName", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "description", source = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "sku", source = "sku", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "effectStartDate", source = "effectStartDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "effectEndDate", source = "effectEndDate", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "category", source = "category", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    @Mapping(target = "productNumber", source = "productNumber", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL)
    void apiToEntityExisting(com.io.dropwizardhibernate.api.ProductRequest request, @MappingTarget Product saving);
}

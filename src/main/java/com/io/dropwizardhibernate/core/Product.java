package com.io.dropwizardhibernate.core;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "product", uniqueConstraints = {@UniqueConstraint(columnNames = {"tenant_id", "sku"}),
                                              @UniqueConstraint(columnNames = {"tenant_id", "product_number"})})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "created_by", nullable = false)
    private String createdBy;

    @Column(name = "created_on")
    private Timestamp createdOn;

    @Column(name = "updated_by", nullable = false)
    private String updatedBy;

    @Column(name = "updated_on")
    private Timestamp updatedOn;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @Column(name = "description")
    private String description;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "effect_start_date")
    private Date effectStartDate;

    @Column(name = "effect_end_date")
    private Date effectEndDate;

    @Column(name = "deleted", columnDefinition = "boolean default false")
    private Boolean deleted;

    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "product_number", length = 100, nullable = false)
    private String productNumber;

}

package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public final class Product {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("brand")
    private String brand;

    @JsonProperty("category")
    private String category;

    @JsonProperty("variant")
    private String variant;

    @JsonProperty("position")
    private int position;

    @JsonProperty("price")
    private BigDecimal price;

    @JsonProperty("quantity")
    private BigDecimal quantity;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    public String getId() {
        return id;
    }

    public Product setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Product setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public Product setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getVariant() {
        return variant;
    }

    public Product setVariant(String variant) {
        this.variant = variant;
        return this;
    }

    public int getPosition() {
        return position;
    }

    public Product setPosition(int position) {
        this.position = position;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Product setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public Product setCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Product setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public Product setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }
}

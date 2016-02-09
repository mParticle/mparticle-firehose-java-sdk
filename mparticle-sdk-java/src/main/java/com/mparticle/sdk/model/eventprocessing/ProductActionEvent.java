package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public final class ProductActionEvent extends Event {

    @JsonProperty(value="action", required=true)
    private Action action;

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("affiliation")
    private String affiliation;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("tax_amount")
    private BigDecimal taxAmount;

    @JsonProperty("shipping_amount")
    private BigDecimal shippingAmount;

    @JsonProperty("currency_code")
    private String currencyCode;

    @JsonProperty("coupon_code")
    private String couponCode;

    @JsonProperty("products")
    private List<Product> products;

    public ProductActionEvent() {
        super(Type.PRODUCT_ACTION);
    }

    public Action getAction() {
        return action;
    }

    public ProductActionEvent setAction(Action action) {
        this.action = action;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public ProductActionEvent setTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public ProductActionEvent setAffiliation(String affiliation) {
        this.affiliation = affiliation;
        return this;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public ProductActionEvent setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public ProductActionEvent setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
        return this;
    }

    public BigDecimal getShippingAmount() {
        return shippingAmount;
    }

    public ProductActionEvent setShippingAmount(BigDecimal shippingAmount) {
        this.shippingAmount = shippingAmount;
        return this;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public ProductActionEvent setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public ProductActionEvent setCouponCode(String couponCode) {
        this.couponCode = couponCode;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public ProductActionEvent setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public enum Action {
        ADD_TO_WISHLIST,
        REMOVE_FROM_WISH_LIST,
        ADD_TO_CART,
        REMOVE_FROM_CART,
        CHECKOUT,
        PURCHASE,
        REFUND,
        CHECKOUT_OPTION,
        CLICK,
        VIEW_DETAIL;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }

}


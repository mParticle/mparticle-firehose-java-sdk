package com.mparticle.sdk.model.eventprocessing;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public final class PromotionActionEvent extends Event {

    @JsonProperty(value = "action", required = true)
    private Action action;

    @JsonProperty("attributes")
    private Map<String, String> attributes;

    @JsonProperty("promotions")
    private List<Promotion> promotions;

    public PromotionActionEvent() {
        super(Type.PROMOTION_ACTION);
    }

    public Action getAction() {
        return action;
    }

    public PromotionActionEvent setAction(Action action) {
        this.action = action;
        return this;
    }

    public List<Promotion> getPromotions() {
        return this.promotions;
    }

    public PromotionActionEvent setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
        return this;
    }

    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public PromotionActionEvent setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
        return this;
    }

    public enum Action {
        UNKNOWN,
        VIEW,
        CLICK;

        @Override
        public String toString() {
            return this.name().toLowerCase();
        }
    }
}
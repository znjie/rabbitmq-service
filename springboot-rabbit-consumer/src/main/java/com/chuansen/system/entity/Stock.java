package com.chuansen.system.entity;

import java.io.Serializable;
import java.time.Instant;

public class Stock implements Serializable {
    public static final long serialVersionUID=1L;

    private String id;
    private Instant created;
    private Instant updated;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 剩余库存
     */
    private Integer overStock;

    public Stock() {
    }

    public Stock(String id, Instant created, Instant updated, String orderNumber, Integer overStock) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.orderNumber = orderNumber;
        this.overStock = overStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getOverStock() {
        return overStock;
    }

    public void setOverStock(Integer overStock) {
        this.overStock = overStock;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", orderNumber='" + orderNumber + '\'' +
                ", overStock=" + overStock +
                '}';
    }
}

package com.chuansen.system.entity;

import java.io.Serializable;
import java.time.Instant;

public class Order implements Serializable {
    public static final long serialVersionUID=1L;

    private String id;
    private Instant created;
    private Instant updated;
    /**
     * 订单编号
     */
    private String orderNumber;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 订单金额
     */
    private Double money;
    /**
     * 收货地址
     */
    private String address;

    public String getId() {
        return id;
    }

    public Instant getCreated() {
        return created;
    }

    public Instant getUpdated() {
        return updated;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Double getMoney() {
        return money;
    }

    public String getAddress() {
        return address;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public void setUpdated(Instant updated) {
        this.updated = updated;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Order() {
    }

    public Order(String id, Instant created, Instant updated) {
        this.id = id;
        this.created = created;
        this.updated = updated;
    }

    public Order(String id, Instant created, Instant updated, String orderNumber, String name, Integer number, Double money, String address) {
        this.id = id;
        this.created = created;
        this.updated = updated;
        this.orderNumber = orderNumber;
        this.name = name;
        this.number = number;
        this.money = money;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", created=" + created +
                ", updated=" + updated +
                ", orderNumber='" + orderNumber + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", money=" + money +
                ", address='" + address + '\'' +
                '}';
    }
}

package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.cloudage.membercenter.util.DateRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders extends DateRecord {
        String ordersID; // 订单号
        String ordersState; // 订单状态，如：发货中，等待收货等

        Goods goods; // 商品
        User buyer; // 用户
        Date payTime; // 付款时间
        String paySum; // 实付款

        @Column(unique = true)
        public String getOrdersID() {
                return ordersID;
        }

        public void setOrdersID(String ordersID) {
                this.ordersID = ordersID;
        }

        public String getOrdersState() {
                return ordersState;
        }

        public void setOrdersState(String ordersState) {
                this.ordersState = ordersState;
        }

        public Date getPayTime() {
                return payTime;
        }

        public void setPayTime(Date payTime) {
                this.payTime = payTime;
        }

        public String getPaySum() {
                return paySum;
        }

        public void setPaySum(String paySum) {
                this.paySum = paySum;
        }

        @ManyToOne(optional = false)
        @JsonIgnore
        public Goods getGoods() {
                return goods;
        }

        public void setGoods(Goods goods) {
                this.goods = goods;
        }
        
        @ManyToOne(optional = false)
        @JsonIgnore
        public User getBuyer() {
                return buyer;
        }

        public void setBuyer(User buyer) {
                this.buyer = buyer;
        }

        // 商品编号
        @Transient
        public Integer getGoodsID() {
                return goods.getId();
        }

        // 商品名字
        @Transient
        public String getGoodsName() {
                return goods.getGoodsName();
        }

        // 商品数量
        @Transient
        public String getGoodsCount() {
                return goods.getGoodsCount();
        }

        // 商品单价
        @Transient
        public String getGoodsPrice() {
                return goods.getGoodsPrice();
        }
}

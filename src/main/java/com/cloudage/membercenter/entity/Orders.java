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
        int ordersID;                                     // 订单号
        int ordersState;                              // 订单状态，如：发货中，等待收货等
        
        Goods goods;                                        // 商品
        String goodsQTY;                                //购买数量
        String goodsSum;                               //商品总额
        
        User buyer;                                          // 用户
        String buyerName;                            //客户姓名
        String buyerPhoneNum;                 //联系方式
        String buyerAddress;                       //客户地址
        
        String paySum;                                  // 实付款

        
        //订单号
        @Column(unique = true, nullable=false)
        public int getOrdersID() {
                return ordersID;
        }

        public void setOrdersID(int ordersID) {
                this.ordersID = ordersID;
        }

        //订单状态
        @Column(nullable=false)
        public int getOrdersState() {
                return ordersState;
        }

        public void setOrdersState(int ordersState) {
                this.ordersState = ordersState;
        }

        //实付款
        public String getPaySum() {
                return paySum;
        }

        public void setPaySum(String paySum) {
                this.paySum = paySum;
        }

        //商品
        @ManyToOne(optional = false)
        @JsonIgnore
        public Goods getGoods() {
                return goods;
        }

        public void setGoods(Goods goods) {
                this.goods = goods;
        }
        
        // 商品编号
        @Transient
        public Integer getGoodsID() {
                return goods.getId();
        }

        //商品名
        @Transient
        public String getGoodsName() {
                return goods.getGoodsName();
        }

        //购买数量
        @Column(nullable=false)
        public String getGoodsQTY() {
                return goodsQTY;
        }

        public void setGoodsQTY(String goodsQTY) {
                this.goodsQTY = goodsQTY;
        }
        
        //商品总额
        @Column(nullable=false)
        public String getGoodsSum() {
                return goodsSum;
        }
        
        public void setGoodsSum(String goodsSum) {
                this.goodsSum = goodsSum;
        }
        
        //买家
        @ManyToOne(optional = false)
        @JsonIgnore
        public User getBuyer() {
                return buyer;
        }

        public void setBuyer(User buyer) {
                this.buyer = buyer;
        }
        
        //客户姓名
        @Column(nullable=false)
        public String getBuyerName() {
                return buyerName;
        }
        
        public void setBuyerName(String buyerName) {
                this.buyerName = buyerName;
        }
        
        //联系方式
        @Column(nullable=false)
        public String getBuyerPhoneNum() {
                return buyerPhoneNum;
        }
        
        public void setBuyerPhoneNum(String buyerPhoneNum) {
                this.buyerPhoneNum = buyerPhoneNum;
        }
        
        //客户地址
        @Column(nullable=false)
        public String getBuyerAddress() {
                return buyerAddress;
        }
        
        public void setBuyerAddress(String buyerAddress) {
                this.buyerAddress = buyerAddress;
        }

}

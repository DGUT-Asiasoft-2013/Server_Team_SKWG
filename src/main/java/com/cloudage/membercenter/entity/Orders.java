package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cloudage.membercenter.util.DateRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders extends DateRecord {
        String ordersID;                                     // 订单号
        int ordersState;                              // 订单状态，  0：已取消订单  1完成订单,2：已下单待付款   3：已付款待发货   4：已发货待收货  5：已收货 6 退货中 7.已退货 
        
        Goods goods;                                        // 商品
        int goodsQTY;                                //购买数量
        double goodsSum;                               //商品总额
        
        User buyer;                                          // 用户
        String buyerName;                            //客户姓名
        String buyerPhoneNum;                 //联系方式
        String buyerAddress;                       //客户地址
        String postCode;		// 邮政编码
        String paySum;                                  // 实付款
        Date deliverDate;	// 发货时间
        Date payDate;		// 付款时间
        Date completeDate;	// 订单完成时间
        //订单号
        @Column(unique = true, nullable=false)
        public String getOrdersID() {
                return ordersID;
        }

        public void setOrdersID(String ordersID) {
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
        public Goods getGoods() {
                return goods;
        }

        public void setGoods(Goods goods) {
                this.goods = goods;
        }
        
        //购买数量
        @Column(nullable=true)
        public int getGoodsQTY() {
                return goodsQTY;
        }

        public void setGoodsQTY(int goodsQTY) {
                this.goodsQTY = goodsQTY;
        }
        
        //商品总额
        @Column(nullable=true)
        public double getGoodsSum() {
                return goodsSum;
        }
        
        public void setGoodsSum(double goodsSum) {
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
        @Column(nullable=true)
        public String getBuyerName() {
                return buyerName;
        }
        
        public void setBuyerName(String buyerName) {
                this.buyerName = buyerName;
        }
        
        //联系方式
        @Column(nullable=true)
        public String getBuyerPhoneNum() {
                return buyerPhoneNum;
        }
        
        public void setBuyerPhoneNum(String buyerPhoneNum) {
                this.buyerPhoneNum = buyerPhoneNum;
        }
        
        //客户地址
        @Column(nullable=true)
        public String getBuyerAddress() {
                return buyerAddress;
        }
        
        public void setBuyerAddress(String buyerAddress) {
                this.buyerAddress = buyerAddress;
        }

        @Column(nullable=true)
		public String getPostCode() {
			return postCode;
		}

		public void setPostCode(String postCode) {
			this.postCode = postCode;
		}

		public Date getDeliverDate() {
			return deliverDate;
		}

		public void setDeliverDate(Date deliverDate) {
			this.deliverDate = deliverDate;
		}

		public Date getPayDate() {
			return payDate;
		}

		public void setPayDate(Date payDate) {
			this.payDate = payDate;
		}

		public Date getCompleteDate() {
			return completeDate;
		}

		public void setCompleteDate(Date completeDate) {
			this.completeDate = completeDate;
		}
        
        
}

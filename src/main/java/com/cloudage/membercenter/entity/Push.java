package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Push extends DateRecord {
        Shop shop;
        User receiver;
        String content;

        @ManyToOne(optional = false)
        public Shop getShop() {
                return shop;
        }

        public void setShop(Shop shop) {
                this.shop = shop;
        }

        @ManyToOne(optional = false)
        public User getReceiver() {
                return receiver;
        }

        public void setReceiver(User receiver) {
                this.receiver = receiver;
        }

        public String getContent() {
                return content;
        }

        public void setContent(String content) {
                this.content = content;
        }
}

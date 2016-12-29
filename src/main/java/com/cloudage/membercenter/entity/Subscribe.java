package com.cloudage.membercenter.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Subscribe {

        @Embeddable
        public static class Key implements Serializable{
                Shop shop;
                User user;
                
                @ManyToOne(optional=false)
                public Shop getShop() {
                        return shop;
                }
                public void setShop(Shop shop) {
                        this.shop = shop;
                }
                @ManyToOne(optional=false)
                public User getUser() {
                        return user;
                }
                public void setUser(User user) {
                        this.user = user;
                }
        }
        
        Key id;
        Date createDate;
        
        @EmbeddedId
        public Key getId() {
                return id;
        }
        public void setId(Key id) {
                this.id = id;
        }
        @Column(updatable=false)
        public Date getCreateDate() {
                return createDate;
        }
        public void setCreateDate(Date createDate) {
                this.createDate = createDate;
        }
        @PrePersist
        void onPrePersist(){
            createDate = new Date();
        }
}

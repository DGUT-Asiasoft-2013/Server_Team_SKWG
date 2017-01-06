package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cloudage.membercenter.util.DateRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class BookComment extends DateRecord {

        String text;// 评论
        User author;// 评论者
        Goods goods;// 商品

        public String getText() {
                return text;
        }

        public void setText(String text) {
                this.text = text;
        }

        @ManyToOne(optional = false)
        @JsonIgnore
        public User getAuthor() {
                return author;
        }

        public void setAuthor(User author) {
                this.author = author;
        }

        @Transient
        public String getAuthorName() {
                return author.name;
        }
        @Transient
        public String getAuthorAvatar(){
        	return author.avatar;
        }
        

        @ManyToOne(optional = false)
        @JsonIgnore
        public Goods getGoods() {
                return goods;
        }

        public void setGoods(Goods goods) {
                this.goods = goods;
        }

        @Transient
        public int getGoodsId() {
                return goods.getId();
        }

}

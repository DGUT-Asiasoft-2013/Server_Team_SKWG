package com.cloudage.membercenter.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

import com.cloudage.membercenter.util.DateRecord;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Comment extends DateRecord {

        String text;
        User author;
        Article article;

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

        @Transient
        public String getAuthorName() {
                return author.name;
        }

        @Transient
        public String getAuthorAvatar() {
                return author.avatar;
        }

        public void setAuthor(User author) {
                this.author = author;
        }

        @ManyToOne(optional = false,cascade={CascadeType.ALL})
        @JsonIgnore
        public Article getArticle() {
                return article;
        }

        public void setArticle(Article article) {
                this.article = article;
        }
        
        @Transient
        public String getArticleTitle() {
        	return article.title;
        }

}

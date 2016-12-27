package com.cloudage.membercenter.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.cloudage.membercenter.util.DateRecord;

@Entity
public class Chat extends DateRecord{
        User sender;
        User receiver;
        String content;
        
        @ManyToOne(optional = false)
        public User getSender() {
                return sender;
        }
        public void setSender(User sender) {
                this.sender = sender;
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

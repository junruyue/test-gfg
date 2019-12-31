package com.cetc27.gfg.xml.entity;

import com.cetc27.gfg.xml.adapter.DateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;

@XmlRootElement(name = "FileHead")
@XmlAccessorType(XmlAccessType.FIELD)
public class FileHead {
    private String messageType;
    private String messageID;
    private String originatorAddress;
    private String recipientAddress;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date creationTime;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageID() {
        return messageID;
    }

    public void setMessageID(String messageID) {
        this.messageID = messageID;
    }

    public String getOriginatorAddress() {
        return originatorAddress;
    }

    public void setOriginatorAddress(String originatorAddress) {
        this.originatorAddress = originatorAddress;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
}

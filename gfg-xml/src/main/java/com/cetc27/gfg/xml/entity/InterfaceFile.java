package com.cetc27.gfg.xml.entity;

import com.cetc27.gfg.xml.adapter.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "InterfaceFile")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({DaProFileAchNotify.class})//解决泛型问题
public class InterfaceFile<T> {

    @XmlElement(name = "FileHead")
    private FileHead fileHead;

    @XmlAnyElement(lax = true)
    private T fileBody;

    public FileHead getFileHead() {
        return fileHead;
    }

    public void setFileHead(FileHead fileHead) {
        this.fileHead = fileHead;
    }

    public T getFileBody() {
        return fileBody;
    }

    public void setFileBody(T fileBody) {
        this.fileBody = fileBody;
    }
}

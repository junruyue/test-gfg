package com.cetc27.gfg.xml.entity;

import javax.xml.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 *
 *产品归档完成通知
 */
@XmlRootElement(name = "FileBody")
@XmlAccessorType(XmlAccessType.FIELD)//按照属性排序
public class DaProFileAchNotify {

    private String satelliteID;
    private String dataType;
    private String proPlanID;
    private String taskID;
    private String reqID;
    private String folderPath;

    @XmlElementWrapper(name = "fileList")
    @XmlElement(name = "file")
    private List<FileInfo> fileList;

    public String getSatelliteID() {
        return satelliteID;
    }

    public void setSatelliteID(String satelliteID) {
        this.satelliteID = satelliteID;
    }


    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getProPlanID() {
        return proPlanID;
    }

    public void setProPlanID(String proPlanID) {
        this.proPlanID = proPlanID;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public List<FileInfo> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileInfo> fileList) {
        this.fileList = fileList;
    }
}

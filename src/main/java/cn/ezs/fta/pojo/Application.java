package cn.ezs.fta.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * <p>申请书  </p>
 *
 * @author : 1998Gang
 * @date : 2020-08-10 23:56
 **/
public class Application {
    /**
     * 申请ID
     */
    private int aid;

    /**
     * 申请原因
     */
    private String applicationReason;

    /**
     * 申请日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date applicationDate;

    /**
     * 要申请的物品或者项目
     */
    private String applicationItem;

    /**
     * 申请状态 u代表还没有审核，a代表已经审核
     * 默认未审核
     */
    private char applicationStatus ='u';
    /**
     * 代表审核是否通过 p:代表通过，n代表未通过
     * 默认不通过
     */
    private char applicationPass='n';
    /**
     * 申请人 对应userName
     */
    private String applicant;


    @Override
    public String toString() {
        return "Application{" +
                "aid=" + aid +
                ", applicationReason='" + applicationReason + '\'' +
                ", applicationDate=" + applicationDate +
                ", applicationItem='" + applicationItem + '\'' +
                ", applicationStstus=" + applicationStatus +
                ", applicationPass=" + applicationPass +
                ", applicant='" + applicant + '\'' +
                '}';
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public String getApplicationReason() {
        return applicationReason;
    }

    public void setApplicationReason(String applicationReason) {
        this.applicationReason = applicationReason;
    }

    public Date getApplicationDate() {
        return applicationDate;
    }

    public void setApplicationDate(Date applicationDate) {
        this.applicationDate = applicationDate;
    }

    public String getApplicationItem() {
        return applicationItem;
    }

    public void setApplicationItem(String applicationItem) {
        this.applicationItem = applicationItem;
    }

    public char getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(char applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public char getApplicationPass() {
        return applicationPass;
    }

    public void setApplicationPass(char applicationPass) {
        this.applicationPass = applicationPass;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
}

package com.zym.memorymasterserver.bean;

/**
 * Created by 12390 on 2019/3/5.
 */
public class BookContent {
    private Integer bookContentId;
    private String contentQ;
    private String contentA;
    private String contentHint;
    private Integer rootBookInformationId;
    private String startRememberTime;
    private Integer rememberAmount;
    private Integer rootChapter;
    private Integer isOkOnMorning;
    private Integer isOkOnEve;

    public Integer getBookContentId() {
        return bookContentId;
    }

    public void setBookContentId(Integer bookContentId) {
        this.bookContentId = bookContentId;
    }

    public String getContentQ() {
        return contentQ;
    }

    public void setContentQ(String contentQ) {
        this.contentQ = contentQ;
    }

    public String getContentA() {
        return contentA;
    }

    public void setContentA(String contentA) {
        this.contentA = contentA;
    }

    public String getContentHint() {
        return contentHint;
    }

    public void setContentHint(String contentHint) {
        this.contentHint = contentHint;
    }

    public Integer getRootBookInformationId() {
        return rootBookInformationId;
    }

    public void setRootBookInformationId(Integer rootBookInformationId) {
        this.rootBookInformationId = rootBookInformationId;
    }

    public String getStartRememberTime() {
        return startRememberTime;
    }

    public void setStartRememberTime(String startRememberTime) {
        this.startRememberTime = startRememberTime;
    }

    public Integer getRememberAmount() {
        return rememberAmount;
    }

    public void setRememberAmount(Integer rememberAmount) {
        this.rememberAmount = rememberAmount;
    }

    public Integer getRootChapter() {
        return rootChapter;
    }

    public void setRootChapter(Integer rootChapter) {
        this.rootChapter = rootChapter;
    }

    public Integer getIsOkOnMorning() {
        return isOkOnMorning;
    }

    public void setIsOkOnMorning(Integer isOkOnMorning) {
        this.isOkOnMorning = isOkOnMorning;
    }

    public Integer getIsOkOnEve() {
        return isOkOnEve;
    }

    public void setIsOkOnEve(Integer isOkOnEve) {
        this.isOkOnEve = isOkOnEve;
    }
}

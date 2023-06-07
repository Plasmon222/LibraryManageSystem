package com.chun.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther:Plasmon222
 * @Date: 2023/6/1/10:02
 * @Description:
 */
public class Notice {
    private Integer id; //公告id
    private String topic;//主题
    private String content;//内容
    private String author;//创建者
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date createDate; //创建时间

    public Notice() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    @Override
    public String toString() {
        return "Notice{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

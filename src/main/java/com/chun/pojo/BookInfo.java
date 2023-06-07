package com.chun.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:20
 * @Description:
 */
public class BookInfo {
    private Integer id; //id
    private String isbn; //图书编号
    private String name; //图书名字
    private BookTypeInfo bookTypeInfo; //书籍类型信息
    private String author; //作者
    private String price; //价格
    private String language; //语言
    private String publish; //出版社
    private String introduction; //简介
    private int typeId; //分类id

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishDate; //出版日期

    private int status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookTypeInfo getBookTypeInfo() {
        return bookTypeInfo;
    }

    public void setBookTypeInfo(BookTypeInfo bookTypeInfo) {
        this.bookTypeInfo = bookTypeInfo;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "id=" + id +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", bookTypeInfo=" + bookTypeInfo +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", language='" + language + '\'' +
                ", publish='" + publish + '\'' +
                ", introduction='" + introduction + '\'' +
                ", typeId=" + typeId +
                ", publishDate=" + publishDate +
                ", status=" + status +
                '}';
    }
}

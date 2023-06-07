package com.chun.pojo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/23/10:28
 * @Description:
 */
public class Lend {
    private int id; //id
    private int bookId;//图书Id
    private int readerId;//读者id
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date lendDate;//借书时间
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private Date backDate;//还书时间
    private int backType;//还书类型
    private String exceptRemarks; //备注信息

    private BookInfo bookInfo; //图书实体类
    private ReaderInfo readerInfo;//读者实体类
    private String readerNumber;//读者卡号
//    private String status;
//    private String name;
    public Lend() {
    }

    public Lend(int id, int bookId, int readerId, Date lendDate, Date backDate, int backType, String exceptRemarks, BookInfo bookInfo, ReaderInfo readerInfo, String readerNumber) {
        this.id = id;
        this.bookId = bookId;
        this.readerId = readerId;
        this.lendDate = lendDate;
        this.backDate = backDate;
        this.backType = backType;
        this.exceptRemarks = exceptRemarks;
        this.bookInfo = bookInfo;
        this.readerInfo = readerInfo;
        this.readerNumber = readerNumber;
    }

    public Lend(int bookId, int readerId) {
        this.bookId = bookId;
        this.readerId = readerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public Date getLendDate() {
        return lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getBackDate() {
        return backDate;
    }

    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    public int getBackType() {
        return backType;
    }

    public void setBackType(int backType) {
        this.backType = backType;
    }

    public String getExceptRemarks() {
        return exceptRemarks;
    }

    public void setExceptRemarks(String exceptRemarks) {
        this.exceptRemarks = exceptRemarks;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public ReaderInfo getReaderInfo() {
        return readerInfo;
    }

    public void setReaderInfo(ReaderInfo readerInfo) {
        this.readerInfo = readerInfo;
    }

    public String getReaderNumber() {
        return readerNumber;
    }

    public void setReaderNumber(String readerNumber) {
        this.readerNumber = readerNumber;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                ", backType=" + backType +
                ", exceptRemarks='" + exceptRemarks + '\'' +
                ", bookInfo=" + bookInfo +
                ", readerInfo=" + readerInfo +
                ", readerNumber='" + readerNumber + '\'' +
                '}';
    }
}

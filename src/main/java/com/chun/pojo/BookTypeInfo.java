package com.chun.pojo;

/**
 * @Auther:Plasmon222
 * @Date: 2023/5/16/15:37
 * @Description:
 */
public class BookTypeInfo {
    private Integer id;
    private String name;
    private String remarks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public BookTypeInfo() {
    }

    public BookTypeInfo(Integer id, String name, String remarks) {
        this.id = id;
        this.name = name;
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "BookTypeInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}

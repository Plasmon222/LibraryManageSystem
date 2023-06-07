package com.chun.pojo;

public class Analysis {
    private Integer counts;
    private String name;

    public Analysis() {
    }

    public Analysis(Integer counts, String name) {
        this.counts = counts;
        this.name = name;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "counts=" + counts +
                ", name='" + name + '\'' +
                '}';
    }
}

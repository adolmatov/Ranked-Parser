package com.ranked.entities;

import java.util.Date;

/**
 * Created by aleksandrdolmatov on 18.12.17.
 */
public class Record implements Comparable<Record>{
    private String name;
    private String url;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Record(){

    }

    public String toString(){
        return date + " " + name + " " + url;
    }

    public int compareTo(Record record) {
        if(this.date.before(record.getDate()))
            return -1;
        else if(this.date.after(record.getDate()))
            return +1;
        else if(this.date.equals(record.getDate()))
            return 0;
        return 0;
    }
}

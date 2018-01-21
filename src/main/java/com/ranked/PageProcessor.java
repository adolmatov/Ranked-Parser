package com.ranked;

import com.ranked.entities.Record;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by aleksandrdolmatov on 11.12.17.
 */
public class PageProcessor {
    int i = 0;

    static Elements elements = new Elements();
    private String baseUrl;
    private String relativeUrl = "/ranking/225/";

    public Elements processTablePage(String url) {
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        elements.addAll(document.getElementsByAttributeValue("class", "row1"));
        elements.addAll(document.getElementsByAttributeValue("class", "row2"));
        int a = Integer.parseInt(document.getElementsByAttributeValue("class", "current").get(0).text()) + 1;
        if (!document.getElementsByAttributeValue("href", relativeUrl + a).isEmpty()) {
            processTablePage(baseUrl +  relativeUrl +a);

        }

        return elements;
    }


    PageProcessor(String url) {
        this.baseUrl = url;
    }

    public Record processPlayerPage(String url){
        Record record = new Record();
        Document document = null;
        try {
             document = Jsoup.connect(baseUrl + url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        record.setUrl(baseUrl + url);
        String lastUpdate = document.getElementsByAttributeValue("class", "table").get(0).
                getElementsByAttributeValue("class", "row0").get(0).getElementsByAttributeValue("class", "date").text();
//        record.setDate(TransformElementToRecord.transformStringToDate(lastUpdate));
        return record;
    }


}

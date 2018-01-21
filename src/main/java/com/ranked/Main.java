package com.ranked;

import com.ranked.entities.Record;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.util.Collections;
import java.util.List;

/**
 * Created by aleksandrdolmatov on 11.12.17.
 */
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        PageProcessor pageProcessor = new PageProcessor("http://pokerdb.thehendonmob.com");
        Elements elements = pageProcessor.processTablePage("http://pokerdb.thehendonmob.com/ranking/225/");
        List<Record> list  = TransformElementToRecord.transformElementToRecord(elements);
        Collections.sort(list);
        System.out.println(list.size());
        for (Record record : list) {
            System.out.println(record);
        }
    }



}

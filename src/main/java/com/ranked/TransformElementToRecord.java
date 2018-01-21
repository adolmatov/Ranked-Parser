package com.ranked;

import com.ranked.entities.Record;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TransformElementToRecord {
    public static String baseUrl;
    public static PageProcessor pageProcessor = new PageProcessor("http://pokerdb.thehendonmob.com");
    public static final SimpleDateFormat DAY_MONTH_YEAR_FORMAT;
    public static final SimpleDateFormat MONTH_YEAR_FORMAT;
    public static Pattern pattern;

    public TransformElementToRecord() {
    }

    public static List<Record> transformElementToRecord(Elements elements) {
        ArrayList recordList = new ArrayList();
        Iterator var2 = elements.iterator();

        while(var2.hasNext()) {
            Element element = (Element)var2.next();
            Record record = pageProcessor.processPlayerPage(((Element)element.getElementsByAttribute("href").get(0)).attributes().get("href"));
            record.setName(element.getElementsByAttributeValue("class", "name").text());
            recordList.add(record);
        }

        return recordList;
    }

    public void setBaseUrl(String baseUrl) {
        baseUrl = baseUrl;
    }

    public static Date transformStringToDate(String stringDate) {
        Matcher matcher = pattern.matcher(stringDate);
        Date date = null;
        SimpleDateFormat simpleDateFormat = null;

        try {
            if(matcher.matches()) {
                if(matcher.group("a") != null) {
                    simpleDateFormat = DAY_MONTH_YEAR_FORMAT;
                } else {
                    simpleDateFormat = MONTH_YEAR_FORMAT;
                }
            }

            date = simpleDateFormat.parse(stringDate);
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return date;
    }

    static {
        DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        MONTH_YEAR_FORMAT = new SimpleDateFormat("MMM-yyyy", Locale.ENGLISH);
        pattern = Pattern.compile("((?<a>\\d{2}-\\w{3}-\\d{4})|(?<b>\\w{3}-\\d{4}))");
    }
}

package com.codepath.nytimessearch;

import com.loopj.android.http.RequestParams;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by liliachang on 6/23/16.
 */
@Parcel
public class SearchFilters {
    public String begin_date;
    public String begin_year;
    public String begin_month;
    public String begin_day;
    public String sort;
    public ArrayList<String> newsDeskItems;
    public String url;
    public RequestParams params;

    public SearchFilters() {
        // empty for parceler
    }

    public SearchFilters(String begin, String which_sort, ArrayList<String> items, String filteredUrl) {
        params = new RequestParams();
        begin_date = begin;
        begin_year = begin;
        begin_month = begin;
        begin_day = begin;
        sort = which_sort;
        newsDeskItems = items;
        url = filteredUrl;
    }

    public void addNewsDeskItem(String newsDeskItem) {
        newsDeskItems.add(newsDeskItem);
    }

    public void setUrl() {
        if (newsDeskItems.size() > 0 ) {
            String newsDeskItemsStr =
                    android.text.TextUtils.join(" ", newsDeskItems);
            String newsDeskParamValue =
                    String.format("news_desk:(%s)", newsDeskItemsStr);
            params.put("fq", newsDeskParamValue);
        }
        params.put("begin_date", begin_date);
        params.put("sort", sort);
    }

    public RequestParams getParams() {

        return params;
    }
}

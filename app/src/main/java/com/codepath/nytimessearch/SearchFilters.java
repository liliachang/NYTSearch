package com.codepath.nytimessearch;

import com.loopj.android.http.RequestParams;

import org.parceler.Parcel;

import java.util.ArrayList;

/**
 * Created by liliachang on 6/23/16.
 */
@Parcel
public class SearchFilters {
    public int begin_date;
    public String sort;
    public ArrayList<String> newsDeskItems;
    public String url;
    public RequestParams params;

    public SearchFilters() {
        // empty for parceler
    }

    public SearchFilters(int begin, String which_sort, ArrayList<String> items, String filteredUrl) {
        begin_date = begin;
        sort = which_sort;
        newsDeskItems = items;
        url = filteredUrl;
    }

    public void addNewsDeskItem(String newsDeskItem) {
        newsDeskItems.add(newsDeskItem);
    }

    public void setUrl() {
        params = new RequestParams();
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

package com.codepath.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

import com.codepath.nytimessearch.R;
import com.codepath.nytimessearch.SearchFilters;

import org.parceler.Parcels;

public class FilterActivity extends AppCompatActivity {

    public SearchFilters filter;
    //public DatePicker dpDate;
    public Spinner spMonth;
    public Spinner spDate;
    public Spinner spYear;
    public Spinner spSort;
    private static final int RESULT_OK = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        spDate = (Spinner) findViewById(R.id.spDay);
        spMonth = (Spinner) findViewById(R.id.spMonth);
        spYear = (Spinner) findViewById(R.id.spYear);
        spSort = (Spinner) findViewById(R.id.spSort);
        filter = (SearchFilters) Parcels.unwrap(getIntent().getParcelableExtra("filter"));
        /*findViewById(dpDate).setOnClickListener(

        );*/
    }

    public void getYear() {
        /*int date = Integer.valueOf(spDate.getSelectedItem().toString());
        int month = Integer.valueOf(spMonth.getSelectedItem().toString());
        int year = Integer.valueOf(spYear.getSelectedItem().toString());
        filter.begin_date = year * 1000 + month * 10 + date;*/
        int date = (int) spDate.getSelectedItem();
        int month = (int) spMonth.getSelectedItem();
        int year = (int) spYear.getSelectedItem();
        filter.begin_date = year * 1000 + month * 10 + date;
    }

    public void getSortPreference() {
        String value = ((Spinner)findViewById(R.id.spSort)).getSelectedItem().toString();
        //String value = spSort.getSelectedItem().toString();
        if (value.equals("Most recent")) {
            filter.sort = "newest";
        } else {
            filter.sort = "oldest";
        }
    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbArts:
                if (checked)
                    filter.addNewsDeskItem("\"Arts\"");
                break;
            case R.id.cbFashion:
                if (checked)
                    filter.addNewsDeskItem("\"Fashion & Style\"");
                break;
            case R.id.cbSports:
                if (checked)
                    filter.addNewsDeskItem("\"Sports\"");
                break;
        }
    }

    public void onSubmit(View view) {
        getYear();
        getSortPreference();
        Intent i = new Intent(FilterActivity.this, SearchActivity.class);
        filter.setUrl();
        i.putExtra("updatedFilter", Parcels.wrap(filter));
        setResult(RESULT_OK, i);
        this.finish();
    }
}

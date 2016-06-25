package com.codepath.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

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
    public CheckBox cbArts;
    public CheckBox cbFashion;
    public CheckBox cbSports;
    private static final int RESULT_OK = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        spDate = (Spinner) findViewById(R.id.spDay);
        spMonth = (Spinner) findViewById(R.id.spMonth);
        spYear = (Spinner) findViewById(R.id.spYear);
        spSort = (Spinner) findViewById(R.id.spSort);
        cbArts = (CheckBox) findViewById(R.id.cbArts);
        cbFashion = (CheckBox) findViewById(R.id.cbFashion);
        cbSports = (CheckBox) findViewById(R.id.cbSports);
        filter = Parcels.unwrap(getIntent().getParcelableExtra("filter"));
        loadFormFromFilters();
    }

    public void loadFormFromFilters() {
        if (filter.sort.equals("newest")) {
            spSort.setSelection(0);
        } else if (filter.sort.equals("oldest")) {
            spSort.setSelection(1);
        }
        if (!filter.begin_date.equals("")) {
            setSpinnerToValue(spDate, String.valueOf(filter.begin_day));
            setSpinnerToValue(spMonth, String.valueOf(filter.begin_month));
            setSpinnerToValue(spYear, String.valueOf(filter.begin_year));
        }

        for (String str : filter.newsDeskItems) {
            if (str.equals("\"Arts\"")) {
                cbArts.setChecked(true);
            }
            if (str.equals("\"Fashion & Style\"")) {
                cbFashion.setChecked(true);
            }
            if (str.equals("\"Sports\"")) {
                cbSports.setChecked(true);
            }
        }
    }

    public void setSpinnerToValue(Spinner spinner, String value) {
        int index = 0;
        SpinnerAdapter adapter = spinner.getAdapter();
        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).equals(value)) {
                index = i;
                break; // terminate loop
            }
        }
        spinner.setSelection(index);
    }

    public void getYear() {
        /*filter.begin_day = Integer.valueOf(spDate.getSelectedItem().toString());
        filter.begin_month = Integer.valueOf(spMonth.getSelectedItem().toString());
        filter.begin_year = Integer.valueOf(spYear.getSelectedItem().toString());
        filter.begin_date = filter.begin_year * 10000 + filter.begin_month * 100 + filter.begin_day;*/
        filter.begin_day = spDate.getSelectedItem().toString();
        filter.begin_month = spMonth.getSelectedItem().toString();
        filter.begin_year = spYear.getSelectedItem().toString();
        filter.begin_date = filter.begin_year + filter.begin_month + filter.begin_day;
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
                else
                    filter.newsDeskItems.remove("\"Arts\"");
                break;
            case R.id.cbFashion:
                if (checked)
                    filter.addNewsDeskItem("\"Fashion & Style\"");
                else
                    filter.newsDeskItems.remove("\"Fashion & Style\"");
                break;
            case R.id.cbSports:
                if (checked)
                    filter.addNewsDeskItem("\"Sports\"");
                else
                    filter.newsDeskItems.remove("\"Sports\"");
                break;
        }
    }

    public void onSubmit(View view) {
        getYear();
        getSortPreference();
        // Launching new activity
        // intent = new Intent(Stukfeft)
        // startActivity(intent);
        // OTHER
        // intent = new Intent()
        // setResult
        //finish


        Intent i = new Intent();
        filter.setUrl();
        i.putExtra("updatedFilter", Parcels.wrap(filter));
        setResult(RESULT_OK, i);
        this.finish();
    }
}

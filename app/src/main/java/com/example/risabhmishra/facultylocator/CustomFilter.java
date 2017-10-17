package com.example.risabhmishra.facultylocator;

import android.widget.Filter;

import java.util.ArrayList;

/**
 * Created by Risabh Mishra on 8/5/2017.
 */

public class CustomFilter extends Filter {

    MyAdapter adapter;
    ArrayList<faculty> filterlist;
    FilterResults results = new FilterResults();

    public CustomFilter(ArrayList<faculty> filterlist, MyAdapter adapter) {
        this.adapter = adapter;
        this.filterlist = filterlist;
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();
        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();
            ArrayList<faculty> filteredfaculty = new ArrayList<>();
            for (int i = 0; i < filteredfaculty.size(); i++) {
                if (filterlist.get(i).getName().toUpperCase().contains(constraint)) {
                    filteredfaculty.add(filterlist.get(i));
                }

            }

            results.count = filteredfaculty.size();
            results.values = filteredfaculty;

        } else {

            results.count = filterlist.size();
            results.values = filterlist;
        }


        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        adapter.faculties = (ArrayList<faculty>) results.values;
        adapter.notifyDataSetChanged();
    }
}

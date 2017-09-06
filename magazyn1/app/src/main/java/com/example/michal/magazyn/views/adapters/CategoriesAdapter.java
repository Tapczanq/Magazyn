package com.example.michal.magazyn.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import com.example.michal.magazyn.activities.UpdatableActivity;
import com.example.michal.magazyn.data.beams.Category;
import com.example.michal.magazyn.views.CategoryItem;



public class CategoriesAdapter extends ArrayAdapter<Category> {
    private UpdatableActivity activity;

    public CategoriesAdapter(Context context, UpdatableActivity activity) {
        super(context, 0);
        this.activity = activity;
    }

    public void update(List<Category> categories) {
        clear();

        addAll(categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Category category = getItem(position);

        return new CategoryItem(getContext(), category, this.activity);
    }
}

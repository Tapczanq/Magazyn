package com.example.michal.magazyn.views.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import com.example.michal.magazyn.activities.UpdatableActivity;
import com.example.michal.magazyn.data.beams.Provider;
import com.example.michal.magazyn.views.ProviderItem;



public class ProvidersAdapter extends ArrayAdapter<Provider> {
    private UpdatableActivity activity;

    public ProvidersAdapter(Context context, UpdatableActivity activity) {
        super(context, 0);
        this.activity = activity;
    }

    public void update(List<Provider> providers) {
        clear();
        addAll(providers);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Provider provider = getItem(position);

        return new ProviderItem(getContext(), provider, this.activity);
    }
}

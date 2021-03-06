package com.example.michal.magazyn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import com.example.michal.magazyn.R;
import com.example.michal.magazyn.data.beams.Category;
import com.example.michal.magazyn.data.stores.IStore;
import com.example.michal.magazyn.data.stores.StoreFactory;

public class CategoryActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Intent intent = getIntent();
        final int id = intent.getIntExtra(EXTRA_ID, 0);


        final EditText nameTextEdit = (EditText) findViewById(R.id.name);
        final Spinner parentSpinner = (Spinner) findViewById(R.id.parents);

        IStore<Category> store = StoreFactory.createCategoriesStore();
        List<Category> parents = new ArrayList<>();
        parents.add(new Category(0, "Brak", null));
        parents.addAll(store.getWithoutId(id));

        ArrayAdapter<Category> parentsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, parents);
        parentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        parentSpinner.setAdapter(parentsAdapter);

        if(id != 0) {
            Category category = store.getById(id);
            nameTextEdit.setText(category.getName());

            int parentId = 0;
            if(category.getParentId() != null) {
                parentId = category.getParentId();
            }

            int parentPosition = Category.getPositionById(parentId, parents);
            if(parentPosition == -1) {
                parentPosition = 0;
            }

            parentSpinner.setSelection(parentPosition);
        }

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTextEdit.getText().toString();

                IStore<Category> store = StoreFactory.createCategoriesStore();

                Category parent = (Category) parentSpinner.getSelectedItem();

                if(id != 0) {
                    if (parent.getId() == 0) {
                        store.update(new Category(id, name, null));
                    } else {
                        store.update(new Category(id, name, parent.getId()));
                    }
                } else {
                    if (parent.getId() == 0) {
                        store.add(new Category(0, name, null));
                    } else {
                        store.add(new Category(0, name, parent.getId()));
                    }
                }

                finish();
            }
        });
    }
}

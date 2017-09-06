package com.example.michal.magazyn.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.michal.magazyn.R;
import com.example.michal.magazyn.data.beams.Provider;
import com.example.michal.magazyn.data.stores.IStore;
import com.example.michal.magazyn.data.stores.StoreFactory;

public class ProviderActivity extends AppCompatActivity {
    public static final String EXTRA_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);

        Intent intent = getIntent();
        final int id = intent.getIntExtra(EXTRA_ID, 0);


        final EditText nameTextEdit = (EditText) findViewById(R.id.name);
        final EditText telephoneTextEdit = (EditText) findViewById(R.id.telephone);
        final EditText addressTextEdit = (EditText) findViewById(R.id.address);

        if(id != 0) {
            IStore<Provider> store = StoreFactory.createProvidersStore();

            Provider provider = store.getById(id);
            nameTextEdit.setText(provider.getName());
            telephoneTextEdit.setText(provider.getTelephone());
            addressTextEdit.setText(provider.getAddress());
        }

        Button button = (Button) findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameTextEdit.getText().toString();
                String telephone = telephoneTextEdit.getText().toString();
                String address = addressTextEdit.getText().toString();

                IStore<Provider> store = StoreFactory.createProvidersStore();

                if(id != 0) {
                    store.update(new Provider(id, name, telephone, address));
                } else {
                    store.add(new Provider(0, name, telephone, address));
                }

                finish();
            }
        });
    }
}

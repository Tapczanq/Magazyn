package com.example.michal.magazyn.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michal.magazyn.R;
import com.example.michal.magazyn.activities.ArticleActivity;
import com.example.michal.magazyn.activities.UpdatableActivity;
import com.example.michal.magazyn.data.beams.Article;
import com.example.michal.magazyn.data.stores.IStore;
import com.example.michal.magazyn.data.stores.StoreFactory;



public class ArticleItem extends LinearLayout {
    public ArticleItem(final Context context, final Article article, final UpdatableActivity activity) {
        super(context);
        inflate(context, R.layout.article_row, this);

        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(article.getName());

        TextView priceTextView = (TextView) findViewById(R.id.price);
        priceTextView.setText(article.getPrice() + "");

        TextView categoryTextView = (TextView) findViewById(R.id.categoryLabel);
        categoryTextView.setText(article.getCategory().getName());

        TextView providerTextView = (TextView) findViewById(R.id.provider);
        providerTextView.setText(article.getProvider().getName());

        Button edit = (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ArticleActivity.class);
                intent.putExtra(ArticleActivity.EXTRA_ID, article.getId());

                activity.startActivity(intent);
            }
        });

        Button delete = (Button) findViewById(R.id.delete);
        delete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setMessage(R.string.remove_message)
                        .setTitle(R.string.remove_title);

                builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        IStore<Article> store = StoreFactory.createArticlesStore(StoreFactory.createCategoriesStore(), StoreFactory.createProvidersStore());
                        store.remove(article.getId());
                        activity.update();
                    }
                });
                builder.setNegativeButton(R.string.no, null);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
}

package com.example.michal.magazyn.data.stores;

import android.content.Context;

import com.example.michal.magazyn.Application;
import com.example.michal.magazyn.data.beams.Article;
import com.example.michal.magazyn.data.beams.Category;
import com.example.michal.magazyn.data.beams.Provider;
import com.example.michal.magazyn.data.stores.database.ArticlesStore;
import com.example.michal.magazyn.data.stores.database.CategoriesStore;
import com.example.michal.magazyn.data.stores.database.DatabaseHelper;
import com.example.michal.magazyn.data.stores.database.ProvidersStore;



public class StoreFactory {
    public static IStore<Category> createCategoriesStore() {
        return new CategoriesStore(new DatabaseHelper(Application.get().getApplicationContext()));
    }

    public static IStore<Provider> createProvidersStore() {
        return new ProvidersStore(new DatabaseHelper(Application.get().getApplicationContext()));
    }

    public static IStore<Article> createArticlesStore(IStore<Category> categoriesStore, IStore<Provider> providersStore) {
        return new ArticlesStore(new DatabaseHelper(Application.get().getApplicationContext()), categoriesStore, providersStore);
    }

    public static IStore<Category> createCategoriesStore(Context context) {
        return new CategoriesStore(new DatabaseHelper(context));
    }

    public static IStore<Provider> createProvidersStore(Context context) {
        return new ProvidersStore(new DatabaseHelper(context));
    }

    public static IStore<Article> createArticlesStore(Context context, IStore<Category> categoriesStore, IStore<Provider> providersStore) {
        return new ArticlesStore(new DatabaseHelper(context), categoriesStore, providersStore);
    }
}

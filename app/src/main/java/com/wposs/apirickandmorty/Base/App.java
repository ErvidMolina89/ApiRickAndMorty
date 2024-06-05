package com.wposs.apirickandmorty.Base;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.appcompat.app.AppCompatActivity;

public class App extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ContextApp.setContext(getApplicationContext());
    }

    public void changeActivity(Class<?> targetActivity) {
        Intent intent = new Intent(this, targetActivity);
        startActivity(intent);
    }
    public void changeActivityPutExtra(Class<?> endActivity, Parcelable object, String key) {
        Intent intent = new Intent(this, endActivity);
        intent.putExtra(key, object);
        startActivity(intent);
    }
}

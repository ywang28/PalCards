package com.yalinwang.palcards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void startNewGameClick(View view) {
        Intent intent = new Intent(this, NewGameActivity.class);
        startActivity(intent);
    }
}

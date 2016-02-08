package com.yalinwang.palcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NewGameActivity extends AppCompatActivity {

    @Bind(R.id.images_grid)
    GridView images_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        ButterKnife.bind(this);
        images_grid.setAdapter(new ImageGridViewAdapter(this, 12));

    }
}

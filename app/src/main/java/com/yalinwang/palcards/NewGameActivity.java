package com.yalinwang.palcards;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class NewGameActivity extends AppCompatActivity {
    private final int CARD_COUNT = 16;
    private static final String IMAGE_PREFIX = "xianjian_";
    private final List<Integer> imageIds = new ArrayList<>();
    // record current flipped cards
    private int flipPosition;

    @Bind(R.id.images_grid)
    GridView images_grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        ButterKnife.bind(this);
        images_grid.setAdapter(new ImageGridViewAdapter(this, CARD_COUNT));
        flipPosition = -1;
        prepareImage();
    }

    @OnItemClick(R.id.images_grid)
    public void onCardItemClick(int position) {
        final ImageView card = (ImageView) images_grid.getChildAt(position);//
        card.setImageResource(imageIds.get(position));

        // The first card being flipped
        if (flipPosition == -1)  {
//            card.setImageResource(imageIds.get(position));
            flipPosition = position;
        }
        // The second card flipped at different position
        else if (position != flipPosition) {

            // show the second image
//            card.setImageResource(imageIds.get(position));

            if (imageIds.get(position).toString().equals(imageIds.get(flipPosition).toString())) {
                Toast.makeText(this, "You made it!", Toast.LENGTH_SHORT).show();
                flipPosition = -1;
            }
            // wrong flip. recover both to card back
            else {

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // wait for while before flipping back the cards

                        try {
                            Thread.sleep(500);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        final ImageView firstCard = (ImageView) images_grid.getChildAt(flipPosition);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                firstCard.setImageResource(R.drawable.card_back);
                                card.setImageResource(R.drawable.card_back);
                            }
                        });
                        // reset flipPosition for next play
                        flipPosition = -1;

                    }
                });

                thread.start();

            }

        }

    }


    private void prepareImage() {
        for (int i = 1; i <= CARD_COUNT/2; i++)  {
            int imageId = getResources()
                    .getIdentifier(IMAGE_PREFIX + i, "drawable", getPackageName());
            // need to add duplicate images because memory game requires paired cards
            imageIds.add(imageId);
            imageIds.add(imageId);
        }

        Collections.shuffle(imageIds);
    }


}

/*
 *
 * @author           Heinrich Elsigan
 * @version          V 1.3.4
 * @since            JDK 1.2.1
 *
 */
/*
   Copyright (C) 2000 - 2023 Heinrich Elsigan

   Poker java Aoo is free software; you can redistribute it and/or
   modify it under the terms of the GNU Library General Public License as
   published by the Free Software Foundation; either version 2 of the
   License, or (at your option) any later version.
   See the GNU Library General Public License for more details.

*/
package work.darkstar.android.wearos.poker;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import work.darkstar.android.wearos.poker.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView text, text_player, text_computer;
    private ImageView imageView0, imageView1, imageView2, imageView3, imageView4;
    private ImageView card0, card1, card2, card3, card4;
    private Button button_start, button_stop;
    private ActivityMainBinding binding;

    private Game aGame = null;
    GlobalAppSettings globalVariable;
    int errNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        text = binding.text;
        text_computer = binding.textComputer;
        text_player =  binding.textPlayer;

        imageView0 = binding.imageView0;
        imageView1 = binding.imageView1;
        imageView2 = binding.imageView2;
        imageView3 = binding.imageView3;
        imageView4 = binding.imageView4;

        card0 = binding.card0;
        card1 = binding.card1;
        card2 = binding.card2;
        card3 = binding.card3;
        card4 = binding.card4;

        button_start = binding.buttonStart;
        button_stop = binding.buttonStop;
        button_start.setOnClickListener(this::button_start_Clicked);
        button_stop.setOnClickListener(this::button_stop_Clicked);

        try {
            globalVariable = (GlobalAppSettings) getApplicationContext();
        } catch (Exception exGlobal) {
            errHandler(exGlobal);
        }
    }

    /**
     * button_start_Clicked
     * @param arg0 view that was clicked
     */
    public void button_start_Clicked(View arg0) {
        try {
            startGame();
        } catch (Exception e) {
            this.errHandler(e);
        }
    }

    /**
     * button_stop_Clicked
     * @param arg0 view that was clicked
     */
    public void button_stop_Clicked(View arg0) {
        try {
            stopGame();
        } catch (Exception e) {
            this.errHandler(e);
        }
    }

    /**
     * startGame
     */
    protected void startGame() {
        errNum = 0;
        aGame = new Game(getApplicationContext());

        text_player.setText(String.valueOf(aGame.gambler.points));
        text_computer.setText(String.valueOf(aGame.computer.points));
        text.setText(getString(R.string.toplayout_clickon_card));
        button_start.setEnabled(false);
        button_stop.setEnabled(true);

        showPlayersCards();
    }


    /**
     * startGame
     */
    protected void stopGame() {
        aGame.destroyGame();

        text_player.setText(getString(R.string.player_money));
        text_computer.setText(getString(R.string.computer_money));
        text.setText(getString(R.string.ending_game));
        button_start.setEnabled(true);
        button_stop.setEnabled(false);

        showPlayersCards();
        aGame = null;
    }


    /**
     * showPlayersCards
     */
    protected void showPlayersCards() {
        try {
            Drawable normalShape = ResourcesCompat.getDrawable(getResources(), R.drawable.shape, null);

            Card handCard = (aGame != null && aGame.gambler != null && aGame.gambler.hand[0].isValidCard()) ?
                    aGame.gambler.hand[0] : aGame.emptyTmpCard;
            card0.setBackground(normalShape);
            card0.setImageDrawable(handCard.getDrawable());
            card0.setVisibility(View.VISIBLE);

            handCard =  (aGame != null && aGame.gambler != null && aGame.gambler.hand[1].isValidCard()) ?
                    aGame.gambler.hand[1] : aGame.emptyTmpCard;
            card1.setBackground(normalShape);
            card1.setImageDrawable(handCard.getDrawable());
            card1.setVisibility(View.VISIBLE);

            handCard = (aGame != null && aGame.gambler != null && aGame.gambler.hand[2].isValidCard()) ?
                    aGame.gambler.hand[2] :  aGame.emptyTmpCard;
            card2.setBackground(normalShape);
            card2.setImageDrawable(handCard.getDrawable());
            card2.setVisibility(View.VISIBLE);

            handCard = (aGame != null && aGame.gambler != null && aGame.gambler.hand[3].isValidCard()) ?
                    aGame.gambler.hand[3] : aGame.emptyTmpCard;
            card3.setBackground(normalShape);
            card3.setImageDrawable(handCard.getDrawable());
            card3.setVisibility(View.VISIBLE);

            handCard = (aGame != null && aGame.gambler != null && aGame.gambler.hand[4].isValidCard()) ?
                    aGame.gambler.hand[4] : aGame.emptyTmpCard;
            card4.setBackground(normalShape);
            card4.setImageDrawable(handCard.getDrawable());
            card4.setVisibility(View.VISIBLE);

        } catch (Exception exp) {
            this.errHandler(exp);
        }
    }



    /**
     * setTextMessage shows a new Toast dynamic message
     * @param stext to display
     */
    private void setTextMessage(CharSequence stext) {
        Context context = getApplicationContext();
        if (stext != null && stext != "") {
            text.setText(stext);
        }
    }


    /**
     * Error handler
     * @param myErr java.lang.Throwable
     */
    private void errHandler(java.lang.Throwable myErr) {
        String sErrMsg = "CRITICAL ERROR #" + ++errNum + " " +
                myErr.getMessage() +
                "\n" + myErr.toString() +
                "\nMessage: "+ myErr.getLocalizedMessage() + "\n";
        // sErrMsg += myErr.toString();
        myErr.printStackTrace();
        // Toast toast = new Toast(getApplicationContext());
        // toast.setText(sErrMsg);
        // toast.show();
        // text.setText(sErrMsg);
    }
}
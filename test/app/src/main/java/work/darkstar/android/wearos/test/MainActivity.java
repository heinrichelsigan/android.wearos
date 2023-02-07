package work.darkstar.android.wearos.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

import work.darkstar.android.wearos.test.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button button_next;
    private ActivityMainBinding binding;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        button_next = binding.buttonNext;
        mTextView = binding.text;
        button_next.setOnClickListener(this::next_Clicked);
    }

    /**
     * next_Clicked
     * @param arg0 view that was clicked
     */
    public void next_Clicked(View arg0) {
        int randint = getRandom(10, false);
        String resName = "fortune";
        int resId = -1;

        if (randint < 10) {
            resName = "fortune0" +  String.valueOf(randint);
        } else {
            resName = "fortune" +  String.valueOf(randint);
        }

        resId = getApplicationContext().getResources().getIdentifier(resName, "string", getApplicationContext().getPackageName());
        String sFortune = getString(resId);
        mTextView.setText(sFortune);
    }

    /**
     * gets a positive random number
     * @param modulo modulo %
     * @param generate true for generate a new Random, false for use existing one
     * @return random as int
     */
    int getRandom(int modulo, boolean generate) {
        int rand = 0;
        if (random == null || generate)
            random = new Random();
        if ((rand = random.nextInt()) < 0)
            rand = -rand;

        modulo = (modulo < 2) ? 20 : modulo;
        rand %= modulo;

        return rand;
    }

}
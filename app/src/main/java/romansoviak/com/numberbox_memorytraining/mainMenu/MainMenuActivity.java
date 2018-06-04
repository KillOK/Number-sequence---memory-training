package romansoviak.com.numberbox_memorytraining.mainMenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import romansoviak.com.numberbox_memorytraining.R;
import romansoviak.com.numberbox_memorytraining.gameProcess.GameActivity;

/**
 * Created by Soviak Roman on 24.05.2018.
 */

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu_activity);
        initElements();
    }

    private void initElements() {
        Button mNewGameButton = findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startActivityIntent = new Intent(MainMenuActivity.this, GameActivity.class);
                MainMenuActivity.this.startActivity(startActivityIntent);
            }
        });
    }
}




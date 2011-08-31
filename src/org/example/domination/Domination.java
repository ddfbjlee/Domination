package org.example.domination;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import android.app.Activity;
import android.os.Bundle;

public class Domination extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        View playButton = findViewById(R.id.play_button);
        playButton.setOnClickListener(this);
        
        View aboutButton = findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        
        View howToPlayButton = findViewById(R.id.how_to_play_button);
        howToPlayButton.setOnClickListener(this);
        
        View quitButton = findViewById(R.id.quit_button);
        quitButton.setOnClickListener(this);
    }
    
    public void onClick(View v) {
    	switch (v.getId()) {
    	case R.id.about_button:
    		Intent i = new Intent(this, About.class);
    		startActivity(i);
    		break;
    	case R.id.quit_button:
    		finish();
    		break;
    	}
    }
}
package org.example.domination;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Game extends Activity {
	private int board[];
	
	private BoardView boardView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		boardView = new BoardView(this);
		setContentView(boardView);
		boardView.requestFocus();
	}
}

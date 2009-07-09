package org.andjong;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class Andjong extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // new button �������̃C�x���g�o�^����
        View newButton = this.findViewById(R.id.new_button);  
        newButton.setOnClickListener(this);

        // setting button �������̃C�x���g�o�^����
        View settingButton = this.findViewById(R.id.setting_button);  
        settingButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch(v.getId()){
        case R.id.new_button:
        	openNewGameDialog();
        	break;
        case R.id.setting_button:
//       	Intent i = new Intent(this, Settings.class);
//        	startActivity(i);
        	startActivity(new Intent(this, Settings.class));
        	break;
        }
    }
 
    private void openNewGameDialog() {
    	// �_�C�A���O����
    	new AlertDialog.Builder(this)  
    	      .setTitle(R.string.new_game_title)  
    	      .setItems(R.array.difficulty,   
    	      new DialogInterface.OnClickListener() {  
    	       public void onClick(DialogInterface dialoginterface, int i) {  
    	        startGame(i);  
    	       }  
    	      })  
    	      .show();  
    }

    private void startGame(int i) {  
      // start game here  
    }
}
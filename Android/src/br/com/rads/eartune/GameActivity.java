package br.com.rads.eartune;

import java.util.Random;

import android.app.Service;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class GameActivity extends SherlockActivity implements OnClickListener {

	private int[] musicalNotes = { R.raw.note_do, R.raw.note_re, R.raw.note_mi,
			R.raw.note_fa, R.raw.note_sol, R.raw.note_la, R.raw.note_si };

	private SoundPool soundPool;
	private int soundID;
	private int noteID;
	
	private Button hearButton;
	private Button chooseButton;
	private RadioGroup rg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		int random = new Random().nextInt(6);
		
		switch (random) {
		case 0:
			noteID = R.id.button_do;
			break;

		case 1:
			noteID = R.id.button_re;
			break;
			
		case 2:
			noteID = R.id.button_mi;
			break;
			
		case 3:
			noteID = R.id.button_fa;
			break;
			
		case 4:
			noteID = R.id.button_sol;
			break;
			
		case 5:
			noteID = R.id.button_la;
			break;
			
		case 6:
			noteID = R.id.button_si;
			break;
		}
		
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);
		soundID = soundPool.load(this,  musicalNotes[random], 1);
		
		rg = (RadioGroup) findViewById(R.id.options);
		hearButton = (Button) findViewById(R.id.hear_button);
		chooseButton = (Button) findViewById(R.id.button_choose);
		
		hearButton.setOnClickListener(this);
		chooseButton.setOnClickListener(this);
	}

	public void onClick(View view) {

		AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);

		float actualVolume = audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;

		switch (view.getId()) {
		case R.id.hear_button:
			soundPool.play(soundID, volume, volume, 1, 0, 1);
			break;
			
		case R.id.button_choose:
			
			Log.d("SOUND", "id: " + rg.getCheckedRadioButtonId());
			Log.d("CHOOSE", "id: " + noteID);
			
			if (rg.getCheckedRadioButtonId() == noteID) 
				Toast.makeText(this, "Parabens champs, voce acertou", Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Errou!", Toast.LENGTH_LONG).show();
			
			break;
		}

	}
}

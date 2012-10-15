package br.com.rads.eartune;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;

public class MainActivity extends SherlockActivity implements OnClickListener {

	private SoundPool soundPool;
	private int doID;
	private int reID;
	private int miID;
	private int faID;
	private int solID;
	private int laID;
	private int siID;
	
//	private int[] notes = new int[]{doID,reID,miID,faID,solID,laID,siID};
	
	//views
	private Button doButton;
	private Button reButton;
	private Button miButton;
	private Button faButton;
	private Button solButton;
	private Button laButton;
	private Button siButton;
	private Button playButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);
		
		doID = soundPool.load(this, R.raw.note_do, 1);
		reID = soundPool.load(this, R.raw.note_re, 1);
		miID = soundPool.load(this, R.raw.note_mi, 1);
		faID = soundPool.load(this, R.raw.note_fa, 1);
		solID = soundPool.load(this, R.raw.note_sol, 1);
		laID = soundPool.load(this, R.raw.note_la, 1);
		siID = soundPool.load(this, R.raw.note_si, 1);
		
		doButton = (Button) findViewById(R.id.button_do);
		reButton = (Button) findViewById(R.id.button_re);
		miButton = (Button) findViewById(R.id.button_mi);
		faButton = (Button) findViewById(R.id.button_fa);
		solButton = (Button) findViewById(R.id.button_sol);
		laButton = (Button) findViewById(R.id.button_la);
		siButton = (Button) findViewById(R.id.button_si);
		playButton = (Button) findViewById(R.id.play);
		
		doButton.setOnClickListener(this);
		reButton.setOnClickListener(this);
		miButton.setOnClickListener(this);
		faButton.setOnClickListener(this);
		solButton.setOnClickListener(this);
		laButton.setOnClickListener(this);
		siButton.setOnClickListener(this);
		playButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSherlock().getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClick(View view) {

		AudioManager audioManager = (AudioManager) getSystemService(Service.AUDIO_SERVICE);
		
		float actualVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;
		
		switch (view.getId()) {
		
		case R.id.play:
			startActivity( new Intent(this, GameActivity.class));
			break;
		
		case R.id.button_do:
			if (doID != 0)
				soundPool.play(doID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_re:
			if (reID != 0)
				soundPool.play(reID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_mi:
			if (miID != 0)
				soundPool.play(miID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_fa:
			if (faID != 0)
				soundPool.play(faID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_sol:
			if (solID != 0)
				soundPool.play(solID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_la:
			if (laID != 0)
				soundPool.play(laID, volume, volume, 1, 0, 1);
			break;

		case R.id.button_si:
			if (siID != 0)
				soundPool.play(siID, volume, volume, 1, 0, 1);
			break;
		}
	}
}

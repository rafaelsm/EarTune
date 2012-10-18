package br.com.rads.eartune.util;

import android.app.Activity;
import android.app.Service;
import android.media.AudioManager;
import android.media.SoundPool;
import br.com.rads.eartune.R;
import br.com.rads.eartune.constants.MusicalNotes;

public class SoundManager {

	private Activity activity;
	private SoundPool soundPool;
	private int doID;
	private int reID;
	private int miID;
	private int faID;
	private int solID;
	private int laID;
	private int siID;

	public SoundManager(Activity activity) {
		super();
		this.activity = activity;
		this.activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);

		doID = soundPool.load(this.activity, R.raw.note_do, 1);
		reID = soundPool.load(this.activity, R.raw.note_re, 1);
		miID = soundPool.load(this.activity, R.raw.note_mi, 1);
		faID = soundPool.load(this.activity, R.raw.note_fa, 1);
		solID = soundPool.load(this.activity, R.raw.note_sol, 1);
		laID = soundPool.load(this.activity, R.raw.note_la, 1);
		siID = soundPool.load(this.activity, R.raw.note_si, 1);

	}

	public void playSound(MusicalNotes.Note note) {

		AudioManager audioManager = (AudioManager) activity
				.getSystemService(Service.AUDIO_SERVICE);

		float actualVolume = audioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float maxVolume = audioManager
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = actualVolume / maxVolume;

		switch (note) {

		case DO:
			if (doID != 0)
				soundPool.play(doID, volume, volume, 1, 0, 1);
			break;

		case RE:
			if (reID != 0)
				soundPool.play(reID, volume, volume, 1, 0, 1);
			break;

		case MI:
			if (miID != 0)
				soundPool.play(miID, volume, volume, 1, 0, 1);
			break;

		case FA:
			if (faID != 0)
				soundPool.play(faID, volume, volume, 1, 0, 1);
			break;

		case SOL:
			if (solID != 0)
				soundPool.play(solID, volume, volume, 1, 0, 1);
			break;

		case LA:
			if (laID != 0)
				soundPool.play(laID, volume, volume, 1, 0, 1);
			break;

		case SI:
			if (siID != 0)
				soundPool.play(siID, volume, volume, 1, 0, 1);
			break;
		}
	}

}

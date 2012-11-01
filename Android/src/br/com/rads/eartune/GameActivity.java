package br.com.rads.eartune;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Service;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import br.com.rads.eartune.model.Score;

import com.actionbarsherlock.app.SherlockActivity;

public class GameActivity extends SherlockActivity implements OnClickListener {

	private static final String TAG = "GAME";

	private Score score;

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

		String difficult = getIntent().getExtras().getString("difficult");

		TextView difficultText = (TextView) findViewById(R.id.difficult_text);
		difficultText.setText(difficult);

		Log.d(TAG, "Dificuldade: " + difficult);

		rg = (RadioGroup) findViewById(R.id.options);
		hearButton = (Button) findViewById(R.id.hear_button);
		chooseButton = (Button) findViewById(R.id.button_choose);

		hearButton.setOnClickListener(this);
		chooseButton.setOnClickListener(this);

		setupForDifficult(difficult);

		score = new Score();
	}

	private void setupForDifficult(String difficult) {

		if (difficult.equals("Easy")) {
			createAnswers(3);
		} else if (difficult.equals("Medium")) {
			createAnswers(5);
		} else {
			createAnswers(7);
		}

	}

	private void createAnswers(int i) {

		int[] notes = notesToUse(i);
		int correctNote = new Random().nextInt(i);
		
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);
		soundID = soundPool.load(this, musicalNotes[correctNote], 1);

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		for (int j = 0; j < i; j++) {

			RadioButton radioButton = new RadioButton(this);
			
			if (j == correctNote) {
				radioButton.setTag("right");
			}else{
				radioButton.setTag("wrong");
			}

			String noteName = null;
			switch (notes[j]) {
			case R.raw.note_do:
				noteName = "DO";
				break;

			case R.raw.note_re:
				noteName = "RE";
				break;

			case R.raw.note_mi:
				noteName = "MI";
				break;

			case R.raw.note_fa:
				noteName = "FA";
				break;

			case R.raw.note_sol:
				noteName = "SOL";
				break;

			case R.raw.note_la:
				noteName = "LA";
				break;

			case R.raw.note_si:
				noteName = "SI";
				break;
			}

			radioButton.setText(noteName);
			rg.addView(radioButton, params);

		}

		rg.invalidate();
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

			int radioButtonID = rg.getCheckedRadioButtonId();
			View radioButton = rg.findViewById(radioButtonID);
			
			if (radioButton.getTag() == "right")
				Toast.makeText(this, "Parabens champs, voce acertou",
						Toast.LENGTH_LONG).show();
			else
				Toast.makeText(this, "Errou!", Toast.LENGTH_LONG).show();

			break;
		}

	}

	private int[] notesToUse(int numberOfNotes) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();

		for (int i = 0; i < numberOfNotes; i++) {
			numbers.add(i);
		}

		Collections.shuffle(numbers);

		int[] notes = new int[numberOfNotes];

		for (int i = 0; i < notes.length; i++) {
			notes[i] = musicalNotes[numbers.get(i)];
		}

		return notes;

	}

}

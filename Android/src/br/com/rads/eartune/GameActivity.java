package br.com.rads.eartune;

import java.util.Date;

import android.app.Service;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import br.com.rads.eartune.fragments.GameFragment;
import br.com.rads.eartune.model.Score;
import br.com.rads.eartune.util.DataManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;

public class GameActivity extends SherlockFragmentActivity implements
		OnClickListener {

	private static final String TAG = "GAME";

	// Views
	private ImageButton hearButton;
	private Button chooseButton;
	private TextView hitText;
	private TextView errorText;

	// iv
	private Score score;
	private int hits;
	private int errors;
	private SoundPool soundPool;
	private int soundID;
	private GameFragment gameFrag;

	private long startTime;
	private long endTime;

	private String difficult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		difficult = getIntent().getExtras().getString("difficult");

		TextView difficultText = (TextView) findViewById(R.id.difficult_text);
		difficultText.setText(difficult);

		Log.d(TAG, "Dificuldade: " + difficult);

		hearButton = (ImageButton) findViewById(R.id.hear_button);
		chooseButton = (Button) findViewById(R.id.button_choose);
		hitText = (TextView) findViewById(R.id.hit_text);
		errorText = (TextView) findViewById(R.id.error_text);
		gameFrag = (GameFragment) getSupportFragmentManager().findFragmentById(
				R.id.options);

		hearButton.setOnClickListener(this);
		chooseButton.setOnClickListener(this);

		gameFrag.setDifficult(difficult);

		score = new Score(difficult);
		startTime = new Date().getTime();
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

			RadioGroup rg = gameFrag.getRg();

			int radioButtonID = rg.getCheckedRadioButtonId();
			View radioButton = rg.findViewById(radioButtonID);

			Log.d("RADIO", "id: " + radioButtonID);

			if (radioButtonID <= 0 ) {
				return;
			}

			if (radioButton.getTag() == "right") {

				Toast.makeText(this, "Parabens champs, voce acertou",
						Toast.LENGTH_LONG).show();
				hits++;
				score.setHits(hits);
				hitText.setText("Acertos: " + hits);

				gameFrag.newQuestion();

			} else {
				Toast.makeText(this, "Errou!", Toast.LENGTH_LONG).show();
				errors++;
				score.setErrors(errors);
				errorText.setText("Erros: " + errors);
			}

			break;
		}

	}

	public SoundPool getSoundPool() {
		return soundPool;
	}

	public void setSoundPool(SoundPool soundPool) {
		this.soundPool = soundPool;
	}

	public void setSoundID(int soundID) {
		this.soundID = soundID;
	}

	@Override
	public void finish() {
		super.finish();

		endTime = new Date().getTime();
		score.setPlaytime(new Date(endTime - startTime));

		if (score.getErrors() > 0 || score.getHits() > 0) {
			DataManager manager = new DataManager(this);
			manager.saveScore(score);
		}

	}

}

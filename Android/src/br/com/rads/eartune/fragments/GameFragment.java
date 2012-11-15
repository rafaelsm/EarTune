package br.com.rads.eartune.fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import br.com.rads.eartune.GameActivity;
import br.com.rads.eartune.R;

public class GameFragment extends Fragment {

	// logs
	private static final String TAG = "GameFragment";

	// Views
	private RadioGroup rg;
	private GameActivity parentActivity;

	// iv
	private int[] musicalNotes = { R.raw.note_do, R.raw.note_re, R.raw.note_mi,
			R.raw.note_fa, R.raw.note_sol, R.raw.note_la, R.raw.note_si };

	private int soundID;

	private String difficult = "Easy";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		parentActivity = (GameActivity) activity;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d(TAG, "onCreateView - begin");
		View view = inflater.inflate(R.layout.fragment_game, container, true);

		rg = (RadioGroup) view.findViewById(R.id.options);

		return view;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		setupForDifficult(difficult);
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

		parentActivity.setSoundPool(new SoundPool(7, AudioManager.STREAM_MUSIC,
				0));
		soundID = parentActivity.getSoundPool().load(getActivity(),
				notes[correctNote], 1);
		parentActivity.setSoundID(soundID);

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT);

		for (int j = 0; j < i; j++) {

			RadioButton radioButton = new RadioButton(getActivity());

			if (j == correctNote) {
				radioButton.setTag("right");
			} else {
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

	private int[] notesToUse(int numberOfNotes) {

		ArrayList<Integer> numbers = new ArrayList<Integer>();

		int range = new Random().nextInt(3);
		for (int i = 0; i < numberOfNotes; i++) {
			numbers.add(i+range);
		}

		Log.d(TAG, "Numbers before shuffle: " + numbers);
		Collections.shuffle(numbers);
		Log.d(TAG, "Numbers after shuffle: " + numbers);

		int[] notes = new int[numberOfNotes];

		for (int i = 0; i < notes.length; i++) {
			notes[i] = musicalNotes[numbers.get(i)];
		}

		return notes;

	}

	public RadioGroup getRg() {
		return rg;
	}

	public int getSoundID() {
		return soundID;
	}

	public void setDifficult(String difficult) {
		this.difficult  = difficult;
	}

	public void newQuestion() {
		rg.removeAllViews();
		setupForDifficult(difficult);
		rg.invalidate();
	}
}

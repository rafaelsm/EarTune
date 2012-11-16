package br.com.rads.eartune.fragments;

import java.util.Collections;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import br.com.rads.eartune.GameActivity;
import br.com.rads.eartune.R;
import br.com.rads.eartune.constants.Difficult;
import br.com.rads.eartune.model.Score;
import br.com.rads.eartune.util.DataManager;
import br.com.rads.eartune.util.ScoreAdapter;

public class TrainingFragment extends Fragment implements
		OnItemSelectedListener, OnClickListener {

	private Button trainingButton;
	private Spinner spinner;
	private ListView list;
	private TextView noScoreText;
	private Button deleteScoreButton;

	private List<Score> scoreEasy;
	private List<Score> scoreMedium;
	private List<Score> scoreHard;
	private List<Score> currentScore;
	private String currentDifficult;
	private ScoreAdapter listAdapter;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		loadAllScores();

		DataManager dataManager = new DataManager(getActivity());
		currentScore = dataManager.loadScore(Difficult.EASY);
		currentDifficult = Difficult.EASY;

		Collections.reverse(currentScore);

		View view = inflater.inflate(R.layout.fragment_training, container,
				false);

		spinner = (Spinner) view.findViewById(R.id.difficult_spinner);
		list = (ListView) view.findViewById(R.id.score_list);
		noScoreText = (TextView) view.findViewById(R.id.textview_no_score);
		trainingButton = (Button) view.findViewById(R.id.traning_button);
		deleteScoreButton = (Button) view.findViewById(R.id.delete_score);
		
		trainingButton.setOnClickListener(this);
		deleteScoreButton.setOnClickListener(this);

		listAdapter = new ScoreAdapter(getActivity(), R.layout.score_row,
				currentScore);
		list.setAdapter(listAdapter);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				getActivity(), R.array.difficult_array,
				android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);


		return view;
	}

	@Override
	public void onResume() {
		super.onResume();

		loadAllScores();
		checkScore(currentDifficult);

	}

	private void loadAllScores() {
		DataManager dataManager = new DataManager(getActivity());
		scoreEasy = dataManager.loadScore(Difficult.EASY);
		scoreMedium = dataManager.loadScore(Difficult.MEDIUM);
		scoreHard = dataManager.loadScore(Difficult.HARD);
	}

	private void checkScore(String difficult) {

		if (difficult.equalsIgnoreCase(Difficult.EASY)) {
			currentScore = scoreEasy;
		} else if (difficult.equalsIgnoreCase(Difficult.MEDIUM)) {
			currentScore = scoreMedium;
		} else {
			currentScore = scoreHard;
		}

		if (currentScore.size() < 1) {
			noScoreText.setVisibility(View.VISIBLE);
			deleteScoreButton.setEnabled(false);
		} else {
			noScoreText.setVisibility(View.GONE);
			deleteScoreButton.setEnabled(true);

			Collections.reverse(currentScore);

			listAdapter.clear();
			listAdapter.addAll(currentScore);
			listAdapter.notifyDataSetChanged();

		}
	}

	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {

		String[] dif = getResources().getStringArray(R.array.difficult_array);
		currentDifficult = dif[position];
		checkScore(currentDifficult);

	}

	public void onNothingSelected(AdapterView<?> arg0) {
	}

	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.traning_button:
			Intent intent = new Intent(getActivity(), GameActivity.class);
			intent.putExtra("difficult", (String) spinner.getSelectedItem());

			startActivity(intent);
			break;

		case R.id.delete_score:
			DataManager manager = new DataManager(getActivity());
			
			if (manager.deleteScore(currentDifficult)) {
				loadAllScores();
				checkScore(currentDifficult);
				Toast.makeText(getActivity(), "Devia ter deletado",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(getActivity(), "Deu merda", Toast.LENGTH_LONG)
						.show();
			}
			break;
		}

	}
}

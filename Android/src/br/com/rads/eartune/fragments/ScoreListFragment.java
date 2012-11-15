package br.com.rads.eartune.fragments;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import br.com.rads.eartune.R;
import br.com.rads.eartune.constants.Difficult;
import br.com.rads.eartune.model.Score;
import br.com.rads.eartune.util.DataManager;
import br.com.rads.eartune.util.ScoreAdapter;

public class ScoreListFragment extends ListFragment{

	
	private List<Score> scoreEasy;
	private List<Score> scoreMedium;
	private List<Score> scoreHard;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		DataManager dataManager = new DataManager(getActivity());
		scoreEasy = dataManager.loadScore(Difficult.EASY);
		scoreMedium = dataManager.loadScore(Difficult.MEDIUM);
		scoreHard = dataManager.loadScore(Difficult.HARD);
		
		ScoreAdapter adapter = new ScoreAdapter(getActivity(), R.layout.score_row, scoreEasy);
		
		setListAdapter(adapter);
	}

	
}

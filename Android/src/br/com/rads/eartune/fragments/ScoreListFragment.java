package br.com.rads.eartune.fragments;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import br.com.rads.eartune.model.Score;
import br.com.rads.eartune.util.ScoreAdapter;

public class ScoreListFragment extends ListFragment{

	private int[] errors = {12,33,2,4,7,89};
	private int[] corrects = {12,133,12,14,17,189};
//	private String[] playTime = {"12:12:01","33:12:01","2:12:01","41:12:01","7:12:01","89:12:01"};
	
	private List<Score> score;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		loadDummyScore();
		ScoreAdapter adapter = new ScoreAdapter(getActivity(), android.R.layout.simple_list_item_1, score);
		
		setListAdapter(adapter);
	}

	private void loadDummyScore() {

		score = new ArrayList<Score>();
		
		for (int i = 0; i < errors.length; i++) {
			
			Score s = new Score();
			s.setErrors(errors[i]);
			s.setHits(corrects[i]);
			s.setPlaytime( new Date());
			
			score.add(s);
		}
		
	}
	
}

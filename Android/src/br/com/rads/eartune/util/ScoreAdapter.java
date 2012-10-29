package br.com.rads.eartune.util;

import java.util.List;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.rads.eartune.R;
import br.com.rads.eartune.model.Score;

public class ScoreAdapter extends ArrayAdapter<Score> {

	private List<Score> scores;
	
	public ScoreAdapter(Context context, int textViewResourceId,
			List<Score> objects) {
		super(context, textViewResourceId, objects);
		
		this.scores = objects;
		
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
		
		View row = inflater.inflate(R.layout.score_row, parent,false);
		
		TextView textHits = (TextView) row.findViewById(R.id.hit_text);
		TextView textErrors = (TextView) row.findViewById(R.id.error_text);
		TextView playTime = (TextView) row.findViewById(R.id.time_text);
		
		Score score = this.scores.get(position);
		
		textHits.setText(score.getHits());
		textErrors.setText(score.getErrors());
		playTime.setText(DateHelper.dateToString(score.getPlaytime()));
		
		return row;
	}
	
}

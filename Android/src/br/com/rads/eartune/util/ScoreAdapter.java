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

		View row = convertView;

		if (row == null) {

			LayoutInflater inflater = (LayoutInflater) getContext()
					.getSystemService(Service.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.score_row, parent, false);
			
			ViewHolder holder = new ViewHolder();
			holder.textHits = (TextView) row.findViewById(R.id.hit_text);
			holder.textErrors = (TextView) row.findViewById(R.id.error_text);
			holder.playTime = (TextView) row.findViewById(R.id.time_text);
			
			row.setTag(holder);
			
		}

		Score score = this.scores.get(position);

		ViewHolder viewHolder = (ViewHolder) row.getTag();
		viewHolder.textHits.setText("" + score.getHits());
		viewHolder.textErrors.setText("" + score.getErrors());
		viewHolder.playTime.setText(DateHelper.dateToString(score.getPlaytime()));
		
		return row;
	}

	private class ViewHolder {
		TextView textHits;
		TextView textErrors;
		TextView playTime;
	}

}

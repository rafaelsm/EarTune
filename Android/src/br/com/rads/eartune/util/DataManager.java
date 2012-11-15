package br.com.rads.eartune.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import br.com.rads.eartune.model.Score;

public class DataManager {

	private Context context;
	private Score score;

	public DataManager(Context context) {
		this.context = context;
	}

	public void saveScore(Score score) {

		this.score = score;

		String jsonFile = loadJSONFile(this.score.getDifficult());

		try {
			JSONObject json = createJsonFromScore(jsonFile, score);

			FileOutputStream out = context.openFileOutput(score.getDifficult()
					+ ".json", Context.MODE_PRIVATE);
			out.write(json.toString().getBytes());
			out.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Score> loadScore(String scoreDifficult) {

		ArrayList<Score> allScores = new ArrayList<Score>();

		try {

			String jsonFile = loadJSONFile(scoreDifficult);

			if (jsonFile != null) {
				
				JSONObject rootJSON = new JSONObject(jsonFile);
				JSONArray scoresArray = rootJSON.getJSONArray(scoreDifficult);

				for (int i = 0; i < scoresArray.length(); i++) {

					JSONObject json = scoresArray.getJSONObject(i);

					Score s = new Score(scoreDifficult);
					s.setHits(json.getInt("hit"));
					s.setErrors(json.getInt("error"));
					s.setPlaytime(new Date(json.getLong("time")));

					allScores.add(s);
				}
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return allScores;
	}
	
	public boolean deleteScore(String scoreDifficult){
		
		return context.deleteFile(scoreDifficult + ".json");
		
	}

	private String loadJSONFile(String difficult) {

		StringBuilder sb = null;
		try {
			FileInputStream in = context.openFileInput(difficult + ".json");

			sb = new StringBuilder();

			byte[] buffer = new byte[1024];

			while ((in.read(buffer)) != -1) {
				sb.append(new String(buffer));
			}

		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	private JSONObject createJsonFromScore(String jsonFile, Score score)
			throws JSONException {

		JSONObject root = null;
		JSONArray array = null;

		if (jsonFile == null) {
			root = new JSONObject();
			array = new JSONArray();
		} else {
			root = new JSONObject(jsonFile);
			array = root.getJSONArray(score.getDifficult());
		}

		JSONObject jsonScore = new JSONObject();
		jsonScore.put("hit", score.getHits());
		jsonScore.put("error", score.getErrors());
		jsonScore.put("time", score.getPlaytime().getTime());

		array.put(jsonScore);
		root.put(score.getDifficult(), array);

		return root;
	}

}

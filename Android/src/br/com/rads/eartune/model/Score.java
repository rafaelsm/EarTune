package br.com.rads.eartune.model;

import java.io.Serializable;
import java.util.Date;

public @SuppressWarnings("serial")
class Score implements Serializable {
	
	private int hits;
	private int errors;
	private Date playtime;
	private String difficult;
	
	public Score(String difficult) {
		super();
		this.setDifficult(difficult);
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getErrors() {
		return errors;
	}

	public void setErrors(int errors) {
		this.errors = errors;
	}

	public Date getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Date playtime) {
		this.playtime = playtime;
	}

	public String getDifficult() {
		return difficult;
	}

	public void setDifficult(String difficult) {
		this.difficult = difficult;
	}
	
}

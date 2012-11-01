package br.com.rads.eartune.model;

import java.util.Date;

public class Score {
	
	private int hits;
	private int errors;
	private Date playtime;
	
	public Score() {
		super();
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
	
}

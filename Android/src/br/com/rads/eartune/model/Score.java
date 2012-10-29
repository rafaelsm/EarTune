package br.com.rads.eartune.model;

import java.util.Date;

public class Score {
	
	private String hits;
	private String errors;
	private Date playtime;
	
	public Score() {
		super();
	}

	public String getHits() {
		return hits;
	}

	public void setHits(String hits) {
		this.hits = hits;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public Date getPlaytime() {
		return playtime;
	}

	public void setPlaytime(Date playtime) {
		this.playtime = playtime;
	}
	
}

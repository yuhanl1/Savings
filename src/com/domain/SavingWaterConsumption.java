package com.domain;

import java.sql.Timestamp;

public class SavingWaterConsumption {
	private float volume;
	private Timestamp timeBegin;
	private Timestamp timeEnd;

	/**
	 * @return the timeBegin
	 */
	public Timestamp getTimeBegin() {
		return timeBegin;
	}

	/**
	 * @param timeBegin the timeBegin to set
	 */
	public void setTimeBegin(Timestamp timeBegin) {
		this.timeBegin = timeBegin;
	}

	/**
	 * @return the timeEnd
	 */
	public Timestamp getTimeEnd() {
		return timeEnd;
	}

	/**
	 * @param timeEnd the timeEnd to set
	 */
	public void setTimeEnd(Timestamp timeEnd) {
		this.timeEnd = timeEnd;
	}

	/**
	 * @return the volume
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * @param volume the volume to set
	 */
	public void setVolume(float volume) {
		this.volume = volume;
	}
}
package com.domain;

import java.sql.Timestamp;

public class SavingEleHour {
	private float energy;
	private Timestamp timeBegin;
	private Timestamp timeEnd;
	/**
	 * @return the energy
	 */
	public float getEnergy() {
		return energy;
	}
	/**
	 * @param energy the energy to set
	 */
	public void setEnergy(float energy) {
		this.energy = energy;
	}
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

}

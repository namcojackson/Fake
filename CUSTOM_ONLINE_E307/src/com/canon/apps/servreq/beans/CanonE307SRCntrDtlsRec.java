package com.canon.apps.servreq.beans;

public class CanonE307SRCntrDtlsRec {

	private String ctrName;
	private double ctrRd;
	private String ReadDate;
	private double avgMonthlyCpVol;
	
	public CanonE307SRCntrDtlsRec(){
	}
	
	public CanonE307SRCntrDtlsRec(String ctrName, double ctrRd,
			String readDate, double avgMonthlyCpVol) {
		super();
		this.ctrName = ctrName;
		this.ctrRd = ctrRd;
		this.ReadDate = readDate;
		this.avgMonthlyCpVol = avgMonthlyCpVol;
	}

	public String getCtrName() {
		return ctrName;
	}

	public void setCtrName(String ctrName) {
		this.ctrName = ctrName;
	}

	public double getCtrRd() {
		return ctrRd;
	}

	public void setCtrRd(double ctrRd) {
		this.ctrRd = ctrRd;
	}

	public String getReadDate() {
		return ReadDate;
	}

	public void setReadDate(String readDate) {
		this.ReadDate = readDate;
	}

	public double getAvgMonthlyCpVol() {
		return avgMonthlyCpVol;
	}

	public void setAvgMonthlyCpVol(double avgMonthlyCpVol) {
		this.avgMonthlyCpVol = avgMonthlyCpVol;
	}

	@Override
	public String toString() {
		return "CanonE307SRCntrDtlsRec [ctrName=" + ctrName + ", ctrRd="
				+ ctrRd + ", ReadDate=" + ReadDate + ", avgMonthlyCpVol="
				+ avgMonthlyCpVol + "]";
	}


}

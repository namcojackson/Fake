package com.canon.apps.servreq.beans;

import java.util.ArrayList;
import java.util.List;

public class CanonE307DebriefResolutionCodes {
	private ArrayList problemCodeList = null;
	private ArrayList correctionCodeList =null;
	private ArrayList reasonCodeList =null;
	private ArrayList locationCodeList =null;
	private ArrayList iwrCheckList=null;
	public CanonE307DebriefResolutionCodes() {
		// TODO Auto-generated constructor stub 
	}
	public ArrayList getProblemCodeList() {
		return problemCodeList;
	}
	public void setProblemCodeList(ArrayList probList) {
		this.problemCodeList = (ArrayList) probList;
	}
	public ArrayList getCorrectionCodeList() {
		return correctionCodeList;
	}
	public void setCorrectionCodeList(ArrayList corrList) {
		this.correctionCodeList = (ArrayList) corrList;
	}
	public ArrayList getReasonCodeList() {
		return reasonCodeList;
	}
	public void setReasonCodeList(ArrayList reasonCodeList) {
		this.reasonCodeList = reasonCodeList;
	}
	public ArrayList getLocationCodeList() {
		return locationCodeList;
	}
	public void setLocationCodeList(ArrayList locationCodeList) {
		this.locationCodeList = locationCodeList;
	}
	public ArrayList getIwrCheckList() {
		return iwrCheckList;
	}
	public void setIwrCheckList(ArrayList iwrCheckList) {
		this.iwrCheckList = iwrCheckList;
	}

}

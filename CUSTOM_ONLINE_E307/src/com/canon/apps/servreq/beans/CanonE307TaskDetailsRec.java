package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307TaskDetailsRec implements SQLData {

	public CanonE307TaskDetailsRec() {
		// TODO Auto-generated constructor stub
	}
	private String strTaskNum;
	private String strTaskTypeCd;
	private String strTaskTypeNm;
	private String strTaskStatus;
	private String strTaskStsCd;
	private String strAssgnCd;
	private String strAssignee;	
	private String strAssigneeTp;
	private String strAssignTpcd;
	private String strLastUptBy;
	private String strCreatDt;
	private String strSchdStart;
	private String strSchdEnd;
	private String strActaulStart;
	private String strActualEnd;
	private String strEarlyStart;
	private String strLateStart;
	private String strResManagerCd;
	private String strResManager;
	private String strBranch;
	private String strVisitNum;
	private String strTskUpdFlg;
	private String strMdlSkills;
	private String strEstFrom;
	private String strEstTo;
	private String strCnclrsn;
	private String strCnclNote;
	private String strDutyMngr;
	private String strRecallType;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strAttribute4;
	private String strAttribute5;	
	
	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_TASK_INFO_REC";
	}
	

	public CanonE307TaskDetailsRec(String strTaskNum, String strTaskTypeCd,
			String strTaskTypeNm, String strTaskStatus, String strTaskStsCd,
			String strAssgnCd, String strAssignee, String strAssigneeTp,
			String strAssignTpcd, String strLastUptBy, String strCreatDt,
			String strSchdStart, String strSchdEnd, String strActaulStart,
			String strActualEnd, String strEarlyStart, String strLateStart,
			String strResManagerCd, String strResManager, String strBranch,
			String strVisitNum, String strTskUpdFlg, String strMdlSkills,
			String strEstFrom, String strEstTo, String strCnclrsn,
			String strCnclNote, String strDutyMngr, String strRecallType,
			String strAttribute1, String strAttribute2, String strAttribute3,
			String strAttribute4, String strAttribute5) {
		super();
		this.strTaskNum = strTaskNum;
		this.strTaskTypeCd = strTaskTypeCd;
		this.strTaskTypeNm = strTaskTypeNm;
		this.strTaskStatus = strTaskStatus;
		this.strTaskStsCd = strTaskStsCd;
		this.strAssgnCd = strAssgnCd;
		this.strAssignee = strAssignee;
		this.strAssigneeTp = strAssigneeTp;
		this.strAssignTpcd = strAssignTpcd;
		this.strLastUptBy = strLastUptBy;
		this.strCreatDt = strCreatDt;
		this.strSchdStart = strSchdStart;
		this.strSchdEnd = strSchdEnd;
		this.strActaulStart = strActaulStart;
		this.strActualEnd = strActualEnd;
		this.strEarlyStart = strEarlyStart;
		this.strLateStart = strLateStart;
		this.strResManagerCd = strResManagerCd;
		this.strResManager = strResManager;
		this.strBranch = strBranch;
		this.strVisitNum = strVisitNum;
		this.strTskUpdFlg = strTskUpdFlg;
		this.strMdlSkills = strMdlSkills;
		this.strEstFrom = strEstFrom;
		this.strEstTo = strEstTo;
		this.strCnclrsn = strCnclrsn;
		this.strCnclNote = strCnclNote;
		this.strDutyMngr = strDutyMngr;
		this.strRecallType = strRecallType;
		this.strAttribute1 = strAttribute1;
		this.strAttribute2 = strAttribute2;
		this.strAttribute3 = strAttribute3;
		this.strAttribute4 = strAttribute4;
		this.strAttribute5 = strAttribute5;
	}





	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		strTaskNum = stream.readString();
		strTaskTypeCd = stream.readString();
		strTaskTypeNm = stream.readString();
		strTaskStatus = stream.readString();
		strTaskStsCd = stream.readString();
		strAssgnCd = stream.readString();		
		strAssignee = stream.readString();
		strAssigneeTp = stream.readString();
		strAssignTpcd = stream.readString();
		strLastUptBy = stream.readString();
		strCreatDt = stream.readString();
		strSchdStart = stream.readString();
		strSchdEnd = stream.readString();
		strActaulStart = stream.readString();
		strActualEnd = stream.readString();
		strEarlyStart = stream.readString();
		strLateStart = stream.readString();
		strResManagerCd = stream.readString();
		strResManager = stream.readString();
		strBranch = stream.readString();
		strVisitNum=stream.readString();
		strTskUpdFlg= stream.readString();
		strMdlSkills = stream.readString();
		strEstFrom = stream.readString();
		strEstTo = stream.readString();
		strCnclrsn = stream.readString();
		strCnclNote = stream.readString();
		strDutyMngr = stream.readString();
		strRecallType = stream.readString();
		strAttribute1 = stream.readString();
		strAttribute2 = stream.readString();
		strAttribute3 = stream.readString();
		strAttribute4 = stream.readString();
		strAttribute5 = stream.readString();
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString(strTaskNum);
		stream.writeString(strTaskTypeCd);
		stream.writeString(strTaskTypeNm);
		stream.writeString(strTaskStatus);
		stream.writeString(strTaskStsCd);
		stream.writeString(strAssgnCd);		
		stream.writeString(strAssignee);
		stream.writeString(strAssigneeTp);
		stream.writeString(strAssignTpcd);
		stream.writeString(strLastUptBy);
		stream.writeString(strCreatDt);
		stream.writeString(strSchdStart);
		stream.writeString(strSchdEnd);
		stream.writeString(strActaulStart);
		stream.writeString(strActualEnd);
		stream.writeString(strEarlyStart);
		stream.writeString(strLateStart);
		stream.writeString(strResManagerCd);
		stream.writeString(strResManager);
		stream.writeString(strBranch);
		stream.writeString(strVisitNum);
		stream.writeString(strTskUpdFlg);
		stream.writeString(strMdlSkills);
		stream.writeString(strEstFrom);
		stream.writeString(strEstTo);
		stream.writeString(strCnclrsn);
		stream.writeString(strCnclNote);
		stream.writeString(strDutyMngr);
		stream.writeString(strRecallType);
		stream.writeString(strAttribute1);
		stream.writeString(strAttribute2);
		stream.writeString(strAttribute3);
		stream.writeString(strAttribute4);
		stream.writeString(strAttribute5);		
	}


	public String getStrTaskNum() {
		return strTaskNum;
	}


	public void setStrTaskNum(String strTaskNum) {
		this.strTaskNum = strTaskNum;
	}


	public String getStrTaskTypeCd() {
		return strTaskTypeCd;
	}


	public void setStrTaskTypeCd(String strTaskTypeCd) {
		this.strTaskTypeCd = strTaskTypeCd;
	}


	public String getStrTaskTypeNm() {
		return strTaskTypeNm;
	}


	public void setStrTaskTypeNm(String strTaskTypeNm) {
		this.strTaskTypeNm = strTaskTypeNm;
	}


	public String getStrTaskStatus() {
		return strTaskStatus;
	}


	public void setStrTaskStatus(String strTaskStatus) {
		this.strTaskStatus = strTaskStatus;
	}


	public String getStrTaskStsCd() {
		return strTaskStsCd;
	}


	public void setStrTaskStsCd(String strTaskStsCd) {
		this.strTaskStsCd = strTaskStsCd;
	}


	public String getStrAssignee() {
		return strAssignee;
	}


	public void setStrAssignee(String strAssignee) {
		this.strAssignee = strAssignee;
	}


	public String getStrAssigneeTp() {
		return strAssigneeTp;
	}


	public void setStrAssigneeTp(String strAssigneeTp) {
		this.strAssigneeTp = strAssigneeTp;
	}


	public String getStrAssignTpcd() {
		return strAssignTpcd;
	}


	public void setStrAssignTpcd(String strAssignTpcd) {
		this.strAssignTpcd = strAssignTpcd;
	}


	public String getStrLastUptBy() {
		return strLastUptBy;
	}


	public void setStrLastUptBy(String strLastUptBy) {
		this.strLastUptBy = strLastUptBy;
	}


	public String getStrCreatDt() {
		return strCreatDt;
	}


	public void setStrCreatDt(String strCreatDt) {
		this.strCreatDt = strCreatDt;
	}


	public String getStrSchdStart() {
		return strSchdStart;
	}


	public void setStrSchdStart(String strSchdStart) {
		this.strSchdStart = strSchdStart;
	}


	public String getStrSchdEnd() {
		return strSchdEnd;
	}


	public void setStrSchdEnd(String strSchdEnd) {
		this.strSchdEnd = strSchdEnd;
	}


	public String getStrActaulStart() {
		return strActaulStart;
	}


	public void setStrActaulStart(String strActaulStart) {
		this.strActaulStart = strActaulStart;
	}


	public String getStrActualEnd() {
		return strActualEnd;
	}


	public void setStrActualEnd(String strActualEnd) {
		this.strActualEnd = strActualEnd;
	}


	public String getStrEarlyStart() {
		return strEarlyStart;
	}


	public void setStrEarlyStart(String strEarlyStart) {
		this.strEarlyStart = strEarlyStart;
	}


	public String getStrLateStart() {
		return strLateStart;
	}


	public void setStrLateStart(String strLateStart) {
		this.strLateStart = strLateStart;
	}


	public String getStrResManager() {
		return strResManager;
	}


	public void setStrResManager(String strResManager) {
		this.strResManager = strResManager;
	}


	public String getStrBranch() {
		return strBranch;
	}


	public void setStrBranch(String strBranch) {
		this.strBranch = strBranch;
	}


	public String getStrVisitNum() {
		return strVisitNum;
	}


	public void setStrVisitNum(String strVisitNum) {
		this.strVisitNum = strVisitNum;
	}

	public String getStrAssgnCd() {
		return strAssgnCd;
	}

	public void setStrAssgnCd(String strAssgnCd) {
		this.strAssgnCd = strAssgnCd;
	}

	public String getStrTskUpdFlg() {
		return strTskUpdFlg;
	}

	public void setStrTskUpdFlg(String strTskUpdFlg) {
		this.strTskUpdFlg = strTskUpdFlg;
	}

	public String getStrMdlSkills() {
		return strMdlSkills;
	}

	public void setStrMdlSkills(String strMdlSkills) {
		this.strMdlSkills = strMdlSkills;
	}
	

	public String getStrResManagerCd() {
		return strResManagerCd;
	}

	public void setStrResManagerCd(String strResManagerCd) {
		this.strResManagerCd = strResManagerCd;
	}

	public String getStrEstFrom() {
		return strEstFrom;
	}

	public void setStrEstFrom(String strEstFrom) {
		this.strEstFrom = strEstFrom;
	}

	public String getStrEstTo() {
		return strEstTo;
	}

	public void setStrEstTo(String strEstTo) {
		this.strEstTo = strEstTo;
	}
	

	public String getStrCnclrsn() {
		return strCnclrsn;
	}


	public void setStrCnclrsn(String strCnclrsn) {
		this.strCnclrsn = strCnclrsn;
	}


	public String getStrCnclNote() {
		return strCnclNote;
	}


	public void setStrCnclNote(String strCnclNote) {
		this.strCnclNote = strCnclNote;
	}

	public String getStrDutyMngr() {
		return strDutyMngr;
	}


	public void setStrDutyMngr(String strDutyMngr) {
		this.strDutyMngr = strDutyMngr;
	}


	public String getStrRecallType() {
		return strRecallType;
	}


	public void setStrRecallType(String strRecallType) {
		this.strRecallType = strRecallType;
	}


	public String getStrAttribute1() {
		return strAttribute1;
	}

	public void setStrAttribute1(String strAttribute1) {
		this.strAttribute1 = strAttribute1;
	}

	public String getStrAttribute2() {
		return strAttribute2;
	}

	public void setStrAttribute2(String strAttribute2) {
		this.strAttribute2 = strAttribute2;
	}

	public String getStrAttribute3() {
		return strAttribute3;
	}

	public void setStrAttribute3(String strAttribute3) {
		this.strAttribute3 = strAttribute3;
	}


	public String getStrAttribute4() {
		return strAttribute4;
	}


	public void setStrAttribute4(String strAttribute4) {
		this.strAttribute4 = strAttribute4;
	}


	public String getStrAttribute5() {
		return strAttribute5;
	}


	public void setStrAttribute5(String strAttribute5) {
		this.strAttribute5 = strAttribute5;
	}


	@Override
	public String toString() {
		return "CanonE307TaskDetailsRec [strTaskNum=" + strTaskNum
				+ ", strTaskTypeCd=" + strTaskTypeCd + ", strTaskTypeNm="
				+ strTaskTypeNm + ", strTaskStatus=" + strTaskStatus
				+ ", strTaskStsCd=" + strTaskStsCd + ", strAssgnCd="
				+ strAssgnCd + ", strAssignee=" + strAssignee
				+ ", strAssigneeTp=" + strAssigneeTp + ", strAssignTpcd="
				+ strAssignTpcd + ", strLastUptBy=" + strLastUptBy
				+ ", strCreatDt=" + strCreatDt + ", strSchdStart="
				+ strSchdStart + ", strSchdEnd=" + strSchdEnd
				+ ", strActaulStart=" + strActaulStart + ", strActualEnd="
				+ strActualEnd + ", strEarlyStart=" + strEarlyStart
				+ ", strLateStart=" + strLateStart + ", strResManagerCd="
				+ strResManagerCd + ", strResManager=" + strResManager
				+ ", strBranch=" + strBranch + ", strVisitNum=" + strVisitNum
				+ ", strTskUpdFlg=" + strTskUpdFlg + ", strMdlSkills="
				+ strMdlSkills + ", strEstFrom=" + strEstFrom + ", strEstTo="
				+ strEstTo + ", strCnclrsn=" + strCnclrsn + ", strCnclNote="
				+ strCnclNote + ", strDutyMngr=" + strDutyMngr
				+ ", strRecallType=" + strRecallType + ", strAttribute1="
				+ strAttribute1 + ", strAttribute2=" + strAttribute2
				+ ", strAttribute3=" + strAttribute3 + ", strAttribute4="
				+ strAttribute4 + ", strAttribute5=" + strAttribute5 + "]";
	}



}

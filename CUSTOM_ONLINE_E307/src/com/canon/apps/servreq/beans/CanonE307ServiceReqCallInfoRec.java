package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;

import canon.apps.common.CanonConstants;

public class CanonE307ServiceReqCallInfoRec   implements SQLData{
	
	// changeReason , POFlgUpld  , verUpldPOFlg  

		private String  creationChannel ;
		private String  creationChannelCd;
		private String  taskTypeName;
		private String  taskTypeCode;
		private String  billCode;
		private String  billCodeCd;
		private String  customerPoFlg;
		private String  customerPoNum;
		private String  lineOfBusiness;
		private String  branch; 
		private String  branchCd ;
		private String  ahTskTpe;
		private String  ahTskCd;
	    private String  slsDate;
	    private String  ccReqFlag;
	    private String  callAvoidFlg;
	    private String  attribute1;
	    private String  attribute2;
	    private String  attribute3;
	    private String  attribute4;
		private String  attribute5;

	    public String getCreationChannel() {
			return creationChannel;
		}

		public void setCreationChannel(String creationChannel) {
			this.creationChannel = creationChannel;
		}


		public String getCreationChannelCd() {
			return creationChannelCd;
		}


		public void setCreationChannelCd(String creationChannelCd) {
			this.creationChannelCd = creationChannelCd;
		}


		public String getTaskTypeName() {
			return taskTypeName;
		}


		public void setTaskTypeName(String taskTypeName) {
			this.taskTypeName = taskTypeName;
		}


		public String getTaskTypeCode() {
			return taskTypeCode;
		}


		public void setTaskTypeCode(String taskTypeCode) {
			this.taskTypeCode = taskTypeCode;
		}


		public String getBillCode() {
			return billCode;
		}


		public void setBillCode(String billCode) {
			this.billCode = billCode;
		}


		public String getBillCodeCd() {
			return billCodeCd;
		}


		public void setBillCodeCd(String billCodeCd) {
			this.billCodeCd = billCodeCd;
		}


		public String getCustomerPoFlg() {
			return customerPoFlg;
		}


		public void setCustomerPoFlg(String customerPoFlg) {
			this.customerPoFlg = customerPoFlg;
		}


		public String getCustomerPoNum() {
			return customerPoNum;
		}


		public void setCustomerPoNum(String customerPoNum) {
			this.customerPoNum = customerPoNum;
		}


		public String getLineOfBusiness() {
			return lineOfBusiness;
		}


		public void setLineOfBusiness(String lineOfBusiness) {
			this.lineOfBusiness = lineOfBusiness;
		}


		public String getBranch() {
			return branch;
		}


		public void setBranch(String branch) {
			this.branch = branch;
		}


		public String getBranchCd() {
			return branchCd;
		}


		public void setBranchCd(String branchCd) {
			this.branchCd = branchCd;
		}


		public String getAhTskTpe() {
			return ahTskTpe;
		}


		public void setAhTskTpe(String ahTskTpe) {
			this.ahTskTpe = ahTskTpe;
		}


		public String getAhTskCd() {
			return ahTskCd;
		}


		public void setAhTskCd(String ahTskCd) {
			this.ahTskCd = ahTskCd;
		}


		public String getSlsDate() {
			return slsDate;
		}


		public void setSlsDate(String slsDate) {
			this.slsDate = slsDate;
		}


		public String getCcReqFlag() {
			return ccReqFlag;
		}


		public void setCcReqFlag(String ccReqFlag) {
			this.ccReqFlag = ccReqFlag;
		}


	   public String getCallAvoidFlg() {
			return callAvoidFlg;
		}


		public void setCallAvoidFlg(String callAvoidFlg) {
			this.callAvoidFlg = callAvoidFlg;
		}

		

	    public String getAttribute1() {
			return attribute1;
		}


		public void setAttribute1(String attribute1) {
			this.attribute1 = attribute1;
		}


		public String getAttribute2() {
			return attribute2;
		}


		public void setAttribute2(String attribute2) {
			this.attribute2 = attribute2;
		}


		public String getAttribute3() {
			return attribute3;
		}


		public void setAttribute3(String attribute3) {
			this.attribute3 = attribute3;
		}


		public String getAttribute4() {
			return attribute4;
		}


		public void setAttribute4(String attribute4) {
			this.attribute4 = attribute4;
		}


		public String getAttribute5() {
			return attribute5;
		}


		public void setAttribute5(String attribute5) {
			this.attribute5 = attribute5;
		}


	public CanonE307ServiceReqCallInfoRec(){
		
	}
	
	// changeReason , POFlgUpld  , verUpldPOFlg 


	public void writeSQL(SQLOutput stream) throws SQLException {
		 stream.writeString(   creationChannel   );
		 stream.writeString(   creationChannelCd );
		 stream.writeString(   taskTypeName );
		 stream.writeString(   taskTypeCode  );
		 stream.writeString(   billCode  );
		 stream.writeString(   billCodeCd);
		 stream.writeString(   customerPoFlg);
		 stream.writeString(   customerPoNum);
		 stream.writeString(   lineOfBusiness);
		 stream.writeString(   branch   ); 
		 stream.writeString(   branchCd );
		 stream.writeString(   ahTskTpe );
		 stream.writeString(   ahTskCd );
		 stream.writeString(   slsDate );
		 stream.writeString(   ccReqFlag);
		 stream.writeString(   callAvoidFlg); 
		 stream.writeString(   attribute1);
		 stream.writeString(   attribute2);
		 stream.writeString(   attribute3);
		 stream.writeString(   attribute4);
		 stream.writeString(   attribute5);
	  }
	
	public CanonE307ServiceReqCallInfoRec(String creationChannel,
			String creationChannelCd, String taskTypeName, String taskTypeCode,
			String billCode, String billCodeCd, String customerPoFlg,
			String customerPoNum, String lineOfBusiness, String branch,
			String branchCd, String ahTskTpe, String ahTskCd, String slsDate,
			String ccReqFlag, String callAvoidFlg, String attribute1,
			String attribute2, String attribute3, String attribute4,
			String attribute5) {
		super();
		this.creationChannel = creationChannel;
		this.creationChannelCd = creationChannelCd;
		this.taskTypeName = taskTypeName;
		this.taskTypeCode = taskTypeCode;
		this.billCode = billCode;
		this.billCodeCd = billCodeCd;
		this.customerPoFlg = customerPoFlg;
		this.customerPoNum = customerPoNum;
		this.lineOfBusiness = lineOfBusiness;
		this.branch = branch;
		this.branchCd = branchCd;
		this.ahTskTpe = ahTskTpe;
		this.ahTskCd = ahTskCd;
		this.slsDate = slsDate;
		this.ccReqFlag = ccReqFlag;
		this.callAvoidFlg = callAvoidFlg;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
	}


	public void readSQL(SQLInput stream, String type) throws SQLException {
		  creationChannel         =stream.readString();
		  creationChannelCd       =stream.readString();
		  taskTypeName            =stream.readString();
		  taskTypeCode            =stream.readString();
		  billCode                =stream.readString();
		  billCodeCd              =stream.readString();
		  customerPoFlg           =stream.readString();
		  customerPoNum           =stream.readString();
		  lineOfBusiness          =stream.readString();
		  branch                  =stream.readString(); 
		  branchCd                =stream.readString();
		  ahTskTpe                =stream.readString();
		  ahTskCd				  =stream.readString();
		  slsDate				  =stream.readString();
		  ccReqFlag				  =stream.readString();
		  callAvoidFlg			  =stream.readString();
		  attribute1			  =stream.readString();
		  attribute2			  =stream.readString();
		  attribute3			  =stream.readString();
		  attribute4			  =stream.readString();
		  attribute5			  =stream.readString();
	}
	
	
	public String getSQLTypeName() {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_CALL_INFO_REC";
    }


	@Override
	public String toString() {
		return "CanonE307ServiceReqCallInfoRec [creationChannel="
				+ creationChannel + ", creationChannelCd=" + creationChannelCd
				+ ", taskTypeName=" + taskTypeName + ", taskTypeCode="
				+ taskTypeCode + ", billCode=" + billCode + ", billCodeCd="
				+ billCodeCd + ", customerPoFlg=" + customerPoFlg
				+ ", customerPoNum=" + customerPoNum + ", lineOfBusiness="
				+ lineOfBusiness + ", branch=" + branch + ", branchCd="
				+ branchCd + ", ahTskTpe=" + ahTskTpe + ", ahTskCd=" + ahTskCd
				+ ", slsDate=" + slsDate + ", ccReqFlag=" + ccReqFlag
				+ ", callAvoidFlg=" + callAvoidFlg + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5="
				+ attribute5 + "]";
	}


}

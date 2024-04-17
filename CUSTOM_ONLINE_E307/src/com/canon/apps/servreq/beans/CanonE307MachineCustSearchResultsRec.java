package com.canon.apps.servreq.beans;

import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Date;

import canon.apps.common.CanonConstants;

public class CanonE307MachineCustSearchResultsRec implements SQLData{
	   
	private String   svcMachMstrPk                ;
	private String   model                        ; 
	private String   serNum                       ;
	private String   custMachCtrlNum              ;
	private String   solutionName                 ;
	private String   shipToAcctNo                 ;
	private String   shipToCustName               ;
	private String   shipToAddress1               ;
	private String   shipToAddress2               ;
	private String   shipToCity                   ;
	private String   shipToState                  ;
	private String   shipToPostalCd               ;
	private String   address	                  ;	
	private String   ownerAcctNo                  ;
	private String   billToCusNo                  ;
	private String   sellToCustNo                 ;
	private String   currLocNo                    ;
	private String   currLocAcctNo                ;
	private String   bizHrsWeekdays               ;
	private String   bizHrsSat                    ;
	private String   bizHrsSun                    ;
	private String   lastServiceCallDt            ;
	private String   totalSvcVisitCount           ;
	private String   lastTechVisitDt              ;
	private String   prefTechCd                   ;
	private String   reqTechCd                    ;
	private String   fldSvcBrCd                   ;
	private String   emailAddress                 ;
	private String   custTelNumber                ;
	private String   custTelExtn                  ;
	private String   custTelNum1				  ;
	private String   custTelNum2				  ;
	private String   custTelNum3				  ;	
	private String   caller                       ;
	private String   serSpecialMsg                ;
	private String   serSpecialMsgTyp             ;
	private String   contact					  ;
	private String   callrTelNumber				  ;
	private String   callrTelExtn				  ;
	private String   countryCd					  ;
	private String   mobileNum					  ;	
	private String   eolEndDt					  ;
	private String   hardHoldFlg				  ;
	private String   crtSRFlg					  ;
	private String   crossSrvcFlg				  ;
	private String   attribute1					  ;
	private String   attribute2					  ;
	private String   attribute3					  ;
	private String   attribute4					  ;
	private String   attribute5					  ;	
	
	public CanonE307MachineCustSearchResultsRec() {
		// TODO Auto-generated constructor stub
	}
	
	
	public String getSvcMachMstrPk() {
		return svcMachMstrPk;
	}

	public void setSvcMachMstrPk(String svcMachMstrPk) {
		this.svcMachMstrPk = svcMachMstrPk;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerNum() {
		return serNum;
	}

	public void setSerNum(String serNum) {
		this.serNum = serNum;
	}

	public String getCustMachCtrlNum() {
		return custMachCtrlNum;
	}

	public void setCustMachCtrlNum(String custMachCtrlNum) {
		this.custMachCtrlNum = custMachCtrlNum;
	}

	public String getSolutionName() {
		return solutionName;
	}

	public void setSolutionName(String solutionName) {
		this.solutionName = solutionName;
	}

	public String getShipToAcctNo() {
		return shipToAcctNo;
	}

	public void setShipToAcctNo(String shipToAcctNo) {
		this.shipToAcctNo = shipToAcctNo;
	}

	public String getShipToCustName() {
		return shipToCustName;
	}

	public void setShipToCustName(String shipToCustName) {
		this.shipToCustName = shipToCustName;
	}

	public String getShipToAddress1() {
		return shipToAddress1;
	}

	public void setShipToAddress1(String shipToAddress1) {
		this.shipToAddress1 = shipToAddress1;
	}

	public String getShipToAddress2() {
		return shipToAddress2;
	}

	public void setShipToAddress2(String shipToAddress2) {
		this.shipToAddress2 = shipToAddress2;
	}

	public String getShipToCity() {
		return shipToCity;
	}

	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}

	public String getShipToState() {
		return shipToState;
	}

	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}

	public String getShipToPostalCd() {
		return shipToPostalCd;
	}

	public void setShipToPostalCd(String shipToPostalCd) {
		this.shipToPostalCd = shipToPostalCd;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOwnerAcctNo() {
		return ownerAcctNo;
	}

	public void setOwnerAcctNo(String ownerAcctNo) {
		this.ownerAcctNo = ownerAcctNo;
	}

	public String getBillToCusNo() {
		return billToCusNo;
	}

	public void setBillToCusNo(String billToCusNo) {
		this.billToCusNo = billToCusNo;
	}

	public String getSellToCustNo() {
		return sellToCustNo;
	}

	public void setSellToCustNo(String sellToCustNo) {
		this.sellToCustNo = sellToCustNo;
	}

	public String getCurrLocNo() {
		return currLocNo;
	}

	public void setCurrLocNo(String currLocNo) {
		this.currLocNo = currLocNo;
	}

	public String getCurrLocAcctNo() {
		return currLocAcctNo;
	}

	public void setCurrLocAcctNo(String currLocAcctNo) {
		this.currLocAcctNo = currLocAcctNo;
	}

	public String getBizHrsWeekdays() {
		return bizHrsWeekdays;
	}

	public void setBizHrsWeekdays(String bizHrsWeekdays) {
		this.bizHrsWeekdays = bizHrsWeekdays;
	}

	public String getBizHrsSat() {
		return bizHrsSat;
	}

	public void setBizHrsSat(String bizHrsSat) {
		this.bizHrsSat = bizHrsSat;
	}

	public String getBizHrsSun() {
		return bizHrsSun;
	}

	public void setBizHrsSun(String bizHrsSun) {
		this.bizHrsSun = bizHrsSun;
	}

	public String getLastServiceCallDt() {
		return lastServiceCallDt;
	}

	public void setLastServiceCallDt(String lastServiceCallDt) {
		this.lastServiceCallDt = lastServiceCallDt;
	}

	public String getTotalSvcVisitCount() {
		return totalSvcVisitCount;
	}

	public void setTotalSvcVisitCount(String totalSvcVisitCount) {
		this.totalSvcVisitCount = totalSvcVisitCount;
	}

	public String getLastTechVisitDt() {
		return lastTechVisitDt;
	}

	public void setLastTechVisitDt(String lastTechVisitDt) {
		this.lastTechVisitDt = lastTechVisitDt;
	}

	public String getPrefTechCd() {
		return prefTechCd;
	}

	public void setPrefTechCd(String prefTechCd) {
		this.prefTechCd = prefTechCd;
	}

	public String getReqTechCd() {
		return reqTechCd;
	}

	public void setReqTechCd(String reqTechCd) {
		this.reqTechCd = reqTechCd;
	}

	public String getFldSvcBrCd() {
		return fldSvcBrCd;
	}

	public void setFldSvcBrCd(String fldSvcBrCd) {
		this.fldSvcBrCd = fldSvcBrCd;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCustTelNumber() {
		return custTelNumber;
	}

	public void setCustTelNumber(String custTelNumber) {
		this.custTelNumber = custTelNumber;
	}

	public String getCustTelExtn() {
		return custTelExtn;
	}

	public void setCustTelExtn(String custTelExtn) {
		this.custTelExtn = custTelExtn;
	}

	public String getCustTelNum1() {
		return custTelNum1;
	}

	public void setCustTelNum1(String custTelNum1) {
		this.custTelNum1 = custTelNum1;
	}

	public String getCustTelNum2() {
		return custTelNum2;
	}

	public void setCustTelNum2(String custTelNum2) {
		this.custTelNum2 = custTelNum2;
	}

	public String getCustTelNum3() {
		return custTelNum3;
	}

	public void setCustTelNum3(String custTelNum3) {
		this.custTelNum3 = custTelNum3;
	}

	public String getCaller() {
		return caller;
	}

	public void setCaller(String caller) {
		this.caller = caller;
	}

	public String getSerSpecialMsg() {
		return serSpecialMsg;
	}

	public void setSerSpecialMsg(String serSpecialMsg) {
		this.serSpecialMsg = serSpecialMsg;
	}

	public String getSerSpecialMsgTyp() {
		return serSpecialMsgTyp;
	}

	public void setSerSpecialMsgTyp(String serSpecialMsgTyp) {
		this.serSpecialMsgTyp = serSpecialMsgTyp;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCallrTelNumber() {
		return callrTelNumber;
	}

	public void setCallrTelNumber(String callrTelNumber) {
		this.callrTelNumber = callrTelNumber;
	}

	public String getCallrTelExtn() {
		return callrTelExtn;
	}

	public void setCallrTelExtn(String callrTelExtn) {
		this.callrTelExtn = callrTelExtn;
	}

	public String getCountryCd() {
		return countryCd;
	}

	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getEolEndDt() {
		return eolEndDt;
	}

	public void setEolEndDt(String eolEndDt) {
		this.eolEndDt = eolEndDt;
	}
	

	public String getHardHoldFlg() {
		return hardHoldFlg;
	}


	public void setHardHoldFlg(String hardHoldFlg) {
		this.hardHoldFlg = hardHoldFlg;
	}


	public String getCrtSRFlg() {
		return crtSRFlg;
	}


	public void setCrtSRFlg(String crtSRFlg) {
		this.crtSRFlg = crtSRFlg;
	}


	public String getCrossSrvcFlg() {
		return crossSrvcFlg;
	}


	public void setCrossSrvcFlg(String crossSrvcFlg) {
		this.crossSrvcFlg = crossSrvcFlg;
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


	public CanonE307MachineCustSearchResultsRec(String svcMachMstrPk,
			String model, String serNum, String custMachCtrlNum,
			String solutionName, String shipToAcctNo, String shipToCustName,
			String shipToAddress1, String shipToAddress2, String shipToCity,
			String shipToState, String shipToPostalCd, String address,
			String ownerAcctNo, String billToCusNo, String sellToCustNo,
			String currLocNo, String currLocAcctNo, String bizHrsWeekdays,
			String bizHrsSat, String bizHrsSun, String lastServiceCallDt,
			String totalSvcVisitCount, String lastTechVisitDt,
			String prefTechCd, String reqTechCd, String fldSvcBrCd,
			String emailAddress, String custTelNumber, String custTelExtn,
			String custTelNum1, String custTelNum2, String custTelNum3,
			String caller, String serSpecialMsg, String serSpecialMsgTyp,
			String contact, String callrTelNumber, String callrTelExtn,
			String countryCd, String mobileNum, String eolEndDt,
			String hardHoldFlg, String crtSRFlg, String crossSrvcFlg,
			String attribute1, String attribute2, String attribute3,
			String attribute4, String attribute5) {
		super();
		this.svcMachMstrPk = svcMachMstrPk;
		this.model = model;
		this.serNum = serNum;
		this.custMachCtrlNum = custMachCtrlNum;
		this.solutionName = solutionName;
		this.shipToAcctNo = shipToAcctNo;
		this.shipToCustName = shipToCustName;
		this.shipToAddress1 = shipToAddress1;
		this.shipToAddress2 = shipToAddress2;
		this.shipToCity = shipToCity;
		this.shipToState = shipToState;
		this.shipToPostalCd = shipToPostalCd;
		this.address = address;
		this.ownerAcctNo = ownerAcctNo;
		this.billToCusNo = billToCusNo;
		this.sellToCustNo = sellToCustNo;
		this.currLocNo = currLocNo;
		this.currLocAcctNo = currLocAcctNo;
		this.bizHrsWeekdays = bizHrsWeekdays;
		this.bizHrsSat = bizHrsSat;
		this.bizHrsSun = bizHrsSun;
		this.lastServiceCallDt = lastServiceCallDt;
		this.totalSvcVisitCount = totalSvcVisitCount;
		this.lastTechVisitDt = lastTechVisitDt;
		this.prefTechCd = prefTechCd;
		this.reqTechCd = reqTechCd;
		this.fldSvcBrCd = fldSvcBrCd;
		this.emailAddress = emailAddress;
		this.custTelNumber = custTelNumber;
		this.custTelExtn = custTelExtn;
		this.custTelNum1 = custTelNum1;
		this.custTelNum2 = custTelNum2;
		this.custTelNum3 = custTelNum3;
		this.caller = caller;
		this.serSpecialMsg = serSpecialMsg;
		this.serSpecialMsgTyp = serSpecialMsgTyp;
		this.contact = contact;
		this.callrTelNumber = callrTelNumber;
		this.callrTelExtn = callrTelExtn;
		this.countryCd = countryCd;
		this.mobileNum = mobileNum;
		this.eolEndDt = eolEndDt;
		this.hardHoldFlg = hardHoldFlg;
		this.crtSRFlg = crtSRFlg;
		this.crossSrvcFlg = crossSrvcFlg;
		this.attribute1 = attribute1;
		this.attribute2 = attribute2;
		this.attribute3 = attribute3;
		this.attribute4 = attribute4;
		this.attribute5 = attribute5;
	}



	@Override
	public String toString() {
		return "CanonE307MachineCustSearchResultsRec [svcMachMstrPk="
				+ svcMachMstrPk + ", model=" + model + ", serNum=" + serNum
				+ ", custMachCtrlNum=" + custMachCtrlNum + ", solutionName="
				+ solutionName + ", shipToAcctNo=" + shipToAcctNo
				+ ", shipToCustName=" + shipToCustName + ", shipToAddress1="
				+ shipToAddress1 + ", shipToAddress2=" + shipToAddress2
				+ ", shipToCity=" + shipToCity + ", shipToState=" + shipToState
				+ ", shipToPostalCd=" + shipToPostalCd + ", address=" + address
				+ ", ownerAcctNo=" + ownerAcctNo + ", billToCusNo="
				+ billToCusNo + ", sellToCustNo=" + sellToCustNo
				+ ", currLocNo=" + currLocNo + ", currLocAcctNo="
				+ currLocAcctNo + ", bizHrsWeekdays=" + bizHrsWeekdays
				+ ", bizHrsSat=" + bizHrsSat + ", bizHrsSun=" + bizHrsSun
				+ ", lastServiceCallDt=" + lastServiceCallDt
				+ ", totalSvcVisitCount=" + totalSvcVisitCount
				+ ", lastTechVisitDt=" + lastTechVisitDt + ", prefTechCd="
				+ prefTechCd + ", reqTechCd=" + reqTechCd + ", fldSvcBrCd="
				+ fldSvcBrCd + ", emailAddress=" + emailAddress
				+ ", custTelNumber=" + custTelNumber + ", custTelExtn="
				+ custTelExtn + ", custTelNum1=" + custTelNum1
				+ ", custTelNum2=" + custTelNum2 + ", custTelNum3="
				+ custTelNum3 + ", caller=" + caller + ", serSpecialMsg="
				+ serSpecialMsg + ", serSpecialMsgTyp=" + serSpecialMsgTyp
				+ ", contact=" + contact + ", callrTelNumber=" + callrTelNumber
				+ ", callrTelExtn=" + callrTelExtn + ", countryCd=" + countryCd
				+ ", mobileNum=" + mobileNum + ", eolEndDt=" + eolEndDt
				+ ", hardHoldFlg=" + hardHoldFlg + ", crtSRFlg=" + crtSRFlg
				+ ", crossSrvcFlg=" + crossSrvcFlg + ", attribute1="
				+ attribute1 + ", attribute2=" + attribute2 + ", attribute3="
				+ attribute3 + ", attribute4=" + attribute4 + ", attribute5="
				+ attribute5 + "]";
	}


	public String getSQLTypeName() throws SQLException {
		return CanonConstants.SCHEMA_NAME+".CANON_E307_MAC_SER_REC";
	}
	
	
	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		 svcMachMstrPk                =stream.readString();
		 model                        =stream.readString(); 
		 serNum                       =stream.readString();
		 custMachCtrlNum              =stream.readString();
		 solutionName                 =stream.readString();
		 shipToAcctNo                 =stream.readString();
		 shipToCustName               =stream.readString();
		 shipToAddress1               =stream.readString();
		 shipToAddress2               =stream.readString();
		 shipToCity                   =stream.readString();
		 shipToState                  =stream.readString();
		 shipToPostalCd               =stream.readString();
		 address					  =stream.readString();
		 ownerAcctNo                  =stream.readString();
		 billToCusNo                  =stream.readString();
		 sellToCustNo                 =stream.readString();
		 currLocNo                    =stream.readString();
		 currLocAcctNo                =stream.readString();
		 bizHrsWeekdays               =stream.readString();
		 bizHrsSat                    =stream.readString();
		 bizHrsSun                    =stream.readString();
		 lastServiceCallDt            =stream.readString();
		 totalSvcVisitCount           =stream.readString();
		 lastTechVisitDt              =stream.readString();
		 prefTechCd                   =stream.readString();
		 reqTechCd                    =stream.readString();
		 fldSvcBrCd                   =stream.readString();
		 emailAddress                 =stream.readString();
		 custTelNumber                =stream.readString();
		 custTelExtn                  =stream.readString();
		 custTelNum1				  =stream.readString();
		 custTelNum2				  =stream.readString();
		 custTelNum3				  =stream.readString();
		 caller                       =stream.readString();
		 serSpecialMsg                =stream.readString();
		 serSpecialMsgTyp             =stream.readString();
		 contact					  =stream.readString();
		 callrTelNumber				  =stream.readString();
		 callrTelExtn				  =stream.readString();
		 countryCd					  =stream.readString();
		 mobileNum				  	  =stream.readString();
		 eolEndDt					  =stream.readString();
		 hardHoldFlg				  =stream.readString();
		 crtSRFlg					  =stream.readString();
		 crossSrvcFlg				  =stream.readString();
		 attribute1				      =stream.readString();
		 attribute2				      =stream.readString();
		 attribute3				      =stream.readString();
		 attribute4				      =stream.readString();		 
		 attribute5				      =stream.readString();		 
	}
	public void writeSQL(SQLOutput stream) throws SQLException {
		stream.writeString( svcMachMstrPk                );
		stream.writeString( model                        ); 
		stream.writeString( serNum                       );
		stream.writeString( custMachCtrlNum              );
		stream.writeString( solutionName                 );
		stream.writeString( shipToAcctNo                 );
		stream.writeString( shipToCustName               );
		stream.writeString( shipToAddress1               );
		stream.writeString( shipToAddress2               );
		stream.writeString( shipToCity                   );
		stream.writeString( shipToState                  );
		stream.writeString( shipToPostalCd               );
		stream.writeString( address                      );
		stream.writeString( ownerAcctNo                  );
		stream.writeString( billToCusNo                  );
		stream.writeString( sellToCustNo                 );
		stream.writeString( currLocNo                    );
		stream.writeString( currLocAcctNo                );
		stream.writeString( bizHrsWeekdays               );
		stream.writeString( bizHrsSat                    );
		stream.writeString( bizHrsSun                    );
		stream.writeString( lastServiceCallDt            );
		stream.writeString( totalSvcVisitCount           );
		stream.writeString( lastTechVisitDt              );
		stream.writeString( prefTechCd                   );
		stream.writeString( reqTechCd                    );
		stream.writeString( fldSvcBrCd                   );
		stream.writeString( emailAddress                 );
		stream.writeString( custTelNumber                );
		stream.writeString( custTelExtn                  );
		stream.writeString( custTelNum1                  );
		stream.writeString( custTelNum2					 );
		stream.writeString( custTelNum3                  );		
		stream.writeString( caller                       );
		stream.writeString( serSpecialMsg                );
		stream.writeString( serSpecialMsgTyp             );
		stream.writeString( contact					     );
		stream.writeString( callrTelNumber				 );	
		stream.writeString( callrTelExtn				 );
		stream.writeString( countryCd					 );
		stream.writeString( mobileNum				 	 );
		stream.writeString( eolEndDt                     );
		stream.writeString( hardHoldFlg					 );
		stream.writeString( crtSRFlg                     );
		stream.writeString( crossSrvcFlg                 );
		stream.writeString( attribute1                   );
		stream.writeString( attribute2                   );
		stream.writeString( attribute3                   );
		stream.writeString( attribute4                   );
		stream.writeString( attribute5                   );
	}
	
}

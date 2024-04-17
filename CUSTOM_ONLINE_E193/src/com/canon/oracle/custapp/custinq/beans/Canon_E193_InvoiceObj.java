package com.canon.oracle.custapp.custinq.beans;

import java.math.BigDecimal;

/**
 * Canon_E193_InvoiceObj Contains Invoice Header information
 * Creation date: (8/23/2005 6:05:38 PM)
 * @author:
 */
public class Canon_E193_InvoiceObj {
	private long iCustTrxId;
	private String strTrxNum;
	private String strTrxDate;
	private String strTrxType;
	private String strContNum;
	private String strContNumMod;
	private String strOrdrNum;
	private String strOrdrType;
	private String strTicketNo;
	private String strPurchageOrder;
	//Basudev - Defect ID# 6001576 - Start.
	private String iBillToSiteUseId;
	private String iShipToSiteUseId;
	//Basudev - Defect ID# 6001576 - End.
	private String strDueDate;
	private double iAmtDueOrig;
	private double iAmtDueRemaining;
	private double iAmtAppld;
	private double iAmtAdjusted;
	private double iAmtCredited;
	private double iFreightOrig;
	private double iTaxOrig;
	private String strStatus;
	private String strSalesRep;
	private String strAttribute1;
	private String strAttribute2;
	private String strAttribute3;
	private String strAttribute4;
	private String strAttribute5;
	private String strAttribute6;
	private String strAttribute7;
	private String consolidateBillNum;
	private String aggContractSts;

	/**
	 * Canon_E193_InvoiceObj constructor comment.
	 */
	public Canon_E193_InvoiceObj() {
		iAmtAdjusted = 0;
		iAmtAppld = 0;
		iAmtCredited = 0;
		iAmtDueOrig = 0;
		iAmtDueRemaining = 0;
		iBillToSiteUseId = "";
		iCustTrxId = 0;
		iFreightOrig = 0;
		iShipToSiteUseId = "";
		iTaxOrig = 0;
		strContNum = "";
		strContNumMod = "";
		strOrdrNum = "";
		strOrdrType = "";
		strDueDate = "";
		strTrxDate = "";
		strTrxNum = "";
		strTrxType = "";
		strStatus = "";
		strTicketNo = "";
		strPurchageOrder = "";
		strSalesRep = "";
		strAttribute1 = "";
		strAttribute2 = "";
		strAttribute3 = "";
		strAttribute4 = "";
		strAttribute5 = "";
	}

	/**
	 * Get iAmtAdjusted.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @return double.
	 */
	public double getIAmtAdjusted() {
		return iAmtAdjusted;
	}

	/**
	 * Set iAmtAdjusted.
	 * Creation date: (8/23/2005 6:06:55 PM)
	 * @param amtAdjusted double.
	 */
	public void setIAmtAdjusted(double amtAdjusted) {
		iAmtAdjusted = amtAdjusted;
	}

	/**
	 * @return Returns the iAmtAppld.
	 */
	public double getIAmtAppld() {
		return iAmtAppld;
	}

	/**
	 * @param amtAppld The iAmtAppld to set.
	 */
	public void setIAmtAppld(double amtAppld) {
		iAmtAppld = amtAppld;
	}

	/**
	 * @return Returns the iAmtCredited.
	 */
	public double getIAmtCredited() {
		return iAmtCredited;
	}

	/**
	 * @param amtCredited The iAmtCredited to set.
	 */
	public void setIAmtCredited(double amtCredited) {
		iAmtCredited = amtCredited;
	}

	/**
	 * @return Returns the iAmtDueOrig.
	 */
	public double getIAmtDueOrig() {
		return iAmtDueOrig;
	}

	/**
	 * @param amtDueOrig The iAmtDueOrig to set.
	 */
	public void setIAmtDueOrig(double amtDueOrig) {
		iAmtDueOrig = amtDueOrig;
	}

	/**
	 * @return Returns the iAmtDueRemaining.
	 */
	public double getIAmtDueRemaining() {
		return iAmtDueRemaining;
	}

	/**
	 * @param amtDueRemaining The iAmtDueRemaining to set.
	 */
	public void setIAmtDueRemaining(double amtDueRemaining) {
		iAmtDueRemaining = amtDueRemaining;
	}
	//Basudev - Defect ID# 6001576 - Start.
	/**
	 * @return Returns the iBillToSiteUseId.
	 */
	public String getIBillToSiteUseId() {
		return iBillToSiteUseId;
	}

	/**
	 * @param billToSiteUseId The iBillToSiteUseId to set.
	 */
	public void setIBillToSiteUseId(String billToSiteUseId) {
		iBillToSiteUseId = billToSiteUseId;
	}
	//Basudev - Defect ID# 6001576 - End.
	/**
	 * @return Returns the iCustTrxId.
	 */
	public long getICustTrxId() {
		return iCustTrxId;
	}

	/**
	 * @param custTrxId The iCustTrxId to set.
	 */
	public void setICustTrxId(long custTrxId) {
		iCustTrxId = custTrxId;
	}

	/**
	 * @return Returns the iFreightOrig.
	 */
	public double getIFreightOrig() {
		return iFreightOrig;
	}

	/**
	 * @param freightOrig The iFreightOrig to set.
	 */
	public void setIFreightOrig(double freightOrig) {
		iFreightOrig = freightOrig;
	}
	//Basudev - Defect ID# 6001576 - Start.
	/**
	 * @return Returns the iShipToSiteUseId.
	 */
	public String getIShipToSiteUseId() {
		return iShipToSiteUseId;
	}

	/**
	 * @param shipToSiteUseId The iShipToSiteUseId to set.
	 */
	public void setIShipToSiteUseId(String shipToSiteUseId) {
		iShipToSiteUseId = shipToSiteUseId;
	}
	//Basudev - Defect ID# 6001576 - End.
	/**
	 * @return Returns the iTaxOrig.
	 */
	public double getITaxOrig() {
		return iTaxOrig;
	}

	/**
	 * @param taxOrig The iTaxOrig to set.
	 */
	public void setITaxOrig(double taxOrig) {
		iTaxOrig = taxOrig;
	}

	/**
	 * @return Returns the strContNum.
	 */
	public String getStrContNum() {
		return strContNum;
	}

	/**
	 * @param newContNum The strContNum to set.
	 */
	public void setStrContNum(String newContNum) {
		this.strContNum = newContNum;
	}

	/**
	 * @return Returns the strContNumMod.
	 */
	public String getStrContNumMod() {
		return strContNumMod;
	}

	/**
	 * @param newContNumMod The strContNumMod to set.
	 */
	public void setStrContNumMod(String newContNumMod) {
		this.strContNumMod = newContNumMod;
	}

	/**
	 * @return Returns the strOrdrNum.
	 */
	public String getStrOrdrNum() {
		return strOrdrNum;
	}

	/**
	 * @param newOrdrNum The strOrdrNum to set.
	 */
	public void setStrOrdrNum(String newOrdrNum) {
		this.strOrdrNum = newOrdrNum;
	}

	/**
	 * @return Returns the strOrdrType.
	 */
	public String getStrOrdrType() {
		return strOrdrType;
	}

	/**
	 * @param newOrdrType The strOrdrType to set.
	 */
	public void setStrOrdrType(String newOrdrType) {
		this.strOrdrType = newOrdrType;
	}

	/**
	 * @return Returns the strDueDate.
	 */
	public String getStrDueDate() {
		return strDueDate;
	}

	/**
	 * @param newDueDate The strDueDate to set.
	 */
	public void setStrDueDate(String newDueDate) {
		this.strDueDate = newDueDate;
	}

	/**
	 * @return Returns the strTrxDate.
	 */
	public String getStrTrxDate() {
		return strTrxDate;
	}

	/**
	 * @param newTrxDate The strTrxDate to set.
	 */
	public void setStrTrxDate(String newTrxDate) {
		this.strTrxDate = newTrxDate;
	}

	/**
	 * @return Returns the strTrxNum.
	 */
	public String getStrTrxNum() {
		return strTrxNum;
	}

	/**
	 * @param newTrxNum The strTrxNum to set.
	 */
	public void setStrTrxNum(String newTrxNum) {
		this.strTrxNum = newTrxNum;
	}

	/**
	 * @return Returns the strTrxType.
	 */
	public String getStrTrxType() {
		return strTrxType;
	}

	/**
	 * @param newTrxType The strTrxType to set.
	 */
	public void setStrTrxType(String newTrxType) {
		this.strTrxType = newTrxType;
	}

	/**
	 * Get strStatus.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrStatus() {
		return strStatus;
	}

	/**
	 * Set strStatus.
	 * Creation date: Sep 27, 2005.
	 * @param newStrStatus java.lang.String.
	 */
	public void setStrStatus(String newStrStatus) {
		this.strStatus = newStrStatus;
	}

	/**
	 * Get strPurchageOrder.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrPurchageOrder() {
		return strPurchageOrder;
	}

	/**
	 * Set strPurchageOrder.
	 * Creation date: Sep 27, 2005.
	 * @param newStrPurchageOrder String.
	 */
	public void setStrPurchageOrder(String newStrPurchageOrder) {
		this.strPurchageOrder = newStrPurchageOrder;
	}

	/**
	 * Get strTicketNo.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrTicketNo() {
		return strTicketNo;
	}

	/**
	 * Set strTicketNo.
	 * Creation date: Sep 27, 2005.
	 * @param newStrTicketNo String.
	 */
	public void setStrTicketNo(String newStrTicketNo) {
		this.strTicketNo = newStrTicketNo;
	}

	/**
	 * Get strSalesRep.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrSalesRep() {
		return strSalesRep;
	}

	/**
	 * Set strSalesRep.
	 * Creation date: Sep 27, 2005.
	 * @param newStrSalesRep String.
	 */
	public void setStrSalesRep(String newStrSalesRep) {
		this.strSalesRep = newStrSalesRep;
	}

	/**
	 * Get strAttribute1.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute1() {
		return strAttribute1;
	}

	/**
	 * Set strAttribute1.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute1 String.
	 */
	public void setStrAttribute1(String newStrAttribute1) {
		this.strAttribute1 = newStrAttribute1;
	}

	/**
	 * Get strAttribute2.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute2() {
		return strAttribute2;
	}

	/**
	 * Set strAttribute2.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute2 String.
	 */
	public void setStrAttribute2(String newStrAttribute2) {
		this.strAttribute2 = newStrAttribute2;
	}

	/**
	 * Get strAttribute3.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute3() {
		return strAttribute3;
	}

	/**
	 * Set strAttribute3.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute3 String.
	 */
	public void setStrAttribute3(String newStrAttribute3) {
		this.strAttribute3 = newStrAttribute3;
	}

	/**
	 * Get strAttribute4.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute4() {
		return strAttribute4;
	}

	/**
	 * Set strAttribute4.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute4 String.
	 */
	public void setStrAttribute4(String newStrAttribute4) {
		this.strAttribute4 = newStrAttribute4;
	}

	/**
	 * Get strAttribute5.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute5() {
		return strAttribute5;
	}

	/**
	 * Set strAttribute5.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute5 String.
	 */
	public void setStrAttribute5(String newStrAttribute5) {
		this.strAttribute5 = newStrAttribute5;
	}
	/**
	 * Get strAttribute6.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute6() {
		return strAttribute6;
	}

	/**
	 * Set strAttribute6.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute6 String.
	 */
	public void setStrAttribute6(String newStrAttribute6) {
		this.strAttribute6 = newStrAttribute6;
	}
	/**
	 * Get strAttribute7.
	 * Creation date: Sep 27, 2005.
	 * @return String.
	 */

	public String getStrAttribute7() {
		return strAttribute7;
	}

	/**
	 * Set strAttribute7.
	 * Creation date: Sep 27, 2005.
	 * @param newStrAttribute7 String.
	 */
	public void setStrAttribute7(String newStrAttribute7) {
		this.strAttribute7 = newStrAttribute7;
	}
	
	/**
	 * Get consolidateBillNum.
	 * Creation date: Mar 28 2012.
	 * @return String.
	 */

	public String getConsolidateBillNum() {
		return consolidateBillNum;
	}

	/**
	 * Set consolidateBillNum.
	 * Creation date: Mar 28, 2012.
	 * @param consolidateBillNum String.
	 */
	public void setConsolidateBillNum(String consolidateBillNum) {
		this.consolidateBillNum = consolidateBillNum;
	}
		/**
	 * Get aggContractSts.
	 * Creation date: Mar 28 2012.
	 * @return String.
	 */

	public String getAggContractSts() {
		return aggContractSts;
	}

	/**
	 * Set aggContractSts.
	 * Creation date: Mar 28, 2012.
	 * @param aggContractSts String.
	 */
	public void setAggContractSts(String aggContractSts) {
		this.aggContractSts = aggContractSts;
	}

	@Override
	public String toString() {
		return "Canon_E193_InvoiceObj [iCustTrxId=" + iCustTrxId
				+ ", strTrxNum=" + strTrxNum + ", strTrxDate=" + strTrxDate
				+ ", strTrxType=" + strTrxType + ", strContNum=" + strContNum
				+ ", strContNumMod=" + strContNumMod + ", strOrdrNum="
				+ strOrdrNum + ", strOrdrType=" + strOrdrType
				+ ", strTicketNo=" + strTicketNo + ", strPurchageOrder="
				+ strPurchageOrder + ", iBillToSiteUseId=" + iBillToSiteUseId
				+ ", iShipToSiteUseId=" + iShipToSiteUseId + ", strDueDate="
				+ strDueDate + ", iAmtDueOrig=" + iAmtDueOrig
				+ ", iAmtDueRemaining=" + iAmtDueRemaining + ", iAmtAppld="
				+ iAmtAppld + ", iAmtAdjusted=" + iAmtAdjusted
				+ ", iAmtCredited=" + iAmtCredited + ", iFreightOrig="
				+ iFreightOrig + ", iTaxOrig=" + iTaxOrig + ", strStatus="
				+ strStatus + ", strSalesRep=" + strSalesRep
				+ ", strAttribute1=" + strAttribute1 + ", strAttribute2="
				+ strAttribute2 + ", strAttribute3=" + strAttribute3
				+ ", strAttribute4=" + strAttribute4 + ", strAttribute5="
				+ strAttribute5 + ", strAttribute6=" + strAttribute6
				+ ", strAttribute7=" + strAttribute7 + ", consolidateBillNum="
				+ consolidateBillNum + ", aggContractSts=" + aggContractSts
				+ "]";
	}
	
}

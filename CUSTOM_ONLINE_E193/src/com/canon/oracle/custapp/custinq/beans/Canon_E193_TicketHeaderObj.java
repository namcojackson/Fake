package com.canon.oracle.custapp.custinq.beans;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;

/**
 * Title:		Canon_E193_TicketHeaderObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (07-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 * 18-Dec-2006  Kireet K Bollam    ITG# 73987 : CBS Consolidation Changes
 * </pre>
 */

public class Canon_E193_TicketHeaderObj implements SQLData, Serializable {

	private static final long serialVersionUID = 1L;
	private int ticketId;
	private String ticketNo;
	private String category;
	private String date;
	private String status;
	private String acctName;
	private String acctNo;
	private String contactName;
	private String contactNo;
	private String origEmail;
	private String origName;
	private String origPhNo;
	private String origType;
	private String custContact;
	private String custEmail;
	private String custPhNo;
	private int custAcctId;
	private String customerNo;
	private String customerName;
	private String invoiceNo;
	private String contractNo;
	private String contractModifier;
	private int orderNo;
	private String orderType;
	private String jtfNotesFlag;
	private int catId;
	private String catIdDesc;
	private String recurring;
	private String billingIssue;
	private int orgId;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;

    /*ITG# 73987 - Begin */
	private String attribute6;
	private String attribute7;
	private String attribute8;
	private String attribute9;
	private String attribute10;
	private String attribute11;
	private String attribute12;
	private String attribute13;
	private String attribute14;
	private String attribute15;
    /*ITG# 73987 - End */

	private String creationDate;
	private String ownerDeptCode;
	private String ownerRoleId;
	//Start Changes for S21 by Mangala
	//private int ownerResId;
	private String ownerResId;
	//End Changes for S21 by Mangala
	private String ownerResName;
	private String createdBy;
	private String createdByDeptCode;
	private String createdByRoleId;
	//Start Changes for S21 by Mangala
	//private int createdByResId;
	private String createdByResId;
	//End Changes for S21 by Mangala
	private String createdByResName;
	private String lastUpdateDate;
	private String lastUpdatedBy;
	private String catCode;
	private int iDaysOpen;
	private String aggregateContractNum;
	private String consolidateBillNum;
	//Start Changes for S21 by Mangala
	private String sendEmailFlag;
	private String collectorName;
	private String resolutionCode;
	//End Changes for S21 by Mangala
	private String sqlTypeName;

	/**
	 * Canon_E193_TicketHeaderObj constructor comment.
	 */

	public Canon_E193_TicketHeaderObj() {
		super();
		ticketId = 0;
	    ticketNo = "";
	    category = ""; 
	    date = "";
	    status = "";
	    acctName = "";
	    acctNo = "";
	    contactName = "";
	    contactNo = "";
	    origEmail = "";
	    origName = "";
	    origPhNo = "";
	    origType = "";
	    custContact = "";
	    custEmail = "";
		custPhNo = ""; 
	    custAcctId  = 0;
	    customerNo = "";
	    customerName = "";
		invoiceNo = "";
		contractNo = "";
		contractModifier = "";
		orderNo = 0;
		orderType = "";
		jtfNotesFlag = "";
		catId = 0;
		catIdDesc = "";
		recurring = "";
		billingIssue = "";
		orgId = 0;
		attribute1 = "";
		attribute2 = "";
		attribute3 = "";
		attribute4 = "";
		attribute5 = "";
		attribute6 = "";
		attribute7 = "";
		attribute8 = "";
		attribute9 = ""; 
		attribute10 = "";
		attribute11 = "";
		attribute12 = "";
		attribute13 = "";
		attribute14 = "";
		attribute15 = "";
		creationDate = "";
		ownerDeptCode = "";
		ownerRoleId = "";
		//Start changes for S21 by Mangala
		ownerResId = "";
		//End changes for S21 by Mangala
		ownerResName = "";
		createdBy = "";
		createdByDeptCode = "";
		createdByRoleId = "";
		//Start Changes for S21 by Mangala
		//createdByResId = 0;
		createdByResId = "";
		collectorName = "";
		//End Changes for S21 by Mangala
		createdByResName = "";
		lastUpdateDate = "";
		lastUpdatedBy = "";
		catCode = "";
		iDaysOpen = 0;
		aggregateContractNum = "";
		consolidateBillNum = "";
		sendEmailFlag = "";
		resolutionCode ="";
		

		sqlTypeName = "";
	}
	
	public String getAggregateContractNum() {
		return aggregateContractNum;
	}

	public void setAggregateContractNum(String aggContNum) {
		this.aggregateContractNum = aggContNum;
	}
	
	public String getConsolidateBillNum() {
		return consolidateBillNum;
	}

	public void setConsolidateBillNum(String consolBillNum) {
		this.consolidateBillNum = consolBillNum;
	}
	
	public String getsendEmailFlag() {
		return sendEmailFlag;
	}

	public void setsendEmailFlag(String sendEmailFg) {
		this.sendEmailFlag = sendEmailFg;
	}
	
	public String getresolutionCode() {
		return resolutionCode;
	}

	public void setresolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
	}
	
	
	
	/**
	 * Get acctName.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getAcctName() {
		return acctName;
	}

	/**
	 * Set acctName.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newAcctName java.lang.String
	 */
	public void setAcctName(String newAcctName) {
		this.acctName = newAcctName;
	}

	/**
	 * Get acctNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getAcctNo() {
		return acctNo;
	}

	/**
	 * Set acctNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newAcctNo java.lang.String
	 */
	public void setAcctNo(String newAcctNo) {
		this.acctNo = newAcctNo;
	}

	/**
	 * Get category.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Set ategory.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newCategory java.lang.String.
	 */
	public void setCategory(String newCategory) {
		this.category = newCategory;
	}

	/**
	 * Get contactName.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getContactName() {
		return contactName;
	}

	/**
	 * Set contactName.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newContactName java.lang.String
	 */
	public void setContactName(String newContactName) {
		this.contactName = newContactName;
	}

	/**
	 * Get contactNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * Set contactNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newContactNo java.lang.String
	 */
	public void setContactNo(String newContactNo) {
		this.contactNo = newContactNo;
	}

	/**
	 * Get contractNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * Set contractNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newContractNo java.lang.String
	 */
	public void setContractNo(String newContractNo) {
		this.contractNo = newContractNo;
	}

	/**
	 * Get date.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Set date.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newDate java.lang.String
	 */
	public void setDate(String newDate) {
		this.date = newDate;
	}

	/**
	 * Get invoiceNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * Set invoiceNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newInvoiceNo java.lang.String
	 */
	public void setInvoiceNo(String newInvoiceNo) {
		this.invoiceNo = newInvoiceNo;
	}

	/**
	 * Get orderNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return int
	 */
	public int getOrderNo() {
		return orderNo;
	}

	/**
	 * Set orderNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newOrderNo int
	 */
	public void setOrderNo(int newOrderNo) {
		this.orderNo = newOrderNo;
	}

	/**
	 * Get status.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set status.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newStatus java.lang.String
	 */
	public void setStatus(String newStatus) {
		this.status = newStatus;
	}

	/**
	 * Get ticketNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @return java.lang.String
	 */
	public String getTicketNo() {
		return ticketNo;
	}

	/**
	 * Set ticketNo.
	 * Creation date: (9/7/2005 4:30:45 PM)
	 * @param newTicketNo java.lang.String
	 */
	public void setTicketNo(String newTicketNo) {
		this.ticketNo = newTicketNo;
	}

	/**
	 * Get attribute1.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute1() {
		return attribute1;
	}

	/**
	 * Set attribute1.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute1 java.lang.String.
	 */
	public void setAttribute1(String newAttribute1) {
		this.attribute1 = newAttribute1;
	}

	/**
	 * Get attribute2.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute2() {
		return attribute2;
	}

	/**
	 * Set attribute2.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute2 java.lang.String.
	 */
	public void setAttribute2(String newAttribute2) {
		this.attribute2 = newAttribute2;
	}

	/**
	 * Get attribute3.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute3() {
		return attribute3;
	}

	/**
	 * Set attribute3.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute3 java.lang.String.
	 */
	public void setAttribute3(String newAttribute3) {
		this.attribute3 = newAttribute3;
	}

	/**
	 * Get attribute4.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute4() {
		return attribute4;
	}

	/**
	 * Set attribute4.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute4 java.lang.String.
	 */
	public void setAttribute4(String newAttribute4) {
		this.attribute4 = newAttribute4;
	}

	/**
	 * Get attribute5.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute5() {
		return attribute5;
	}

	/**
	 * Set attribute5.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute5(String newAttribute5) {
		this.attribute5 = newAttribute5;
	}


	/*ITG# 73987 - Begin ******************/

	/**
	 * Get attribute6.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute6() {
		return attribute6;
	}

	/**
	 * Set attribute6.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute6(String newAttribute6) {
		this.attribute6 = newAttribute6;
	}


	/**
	 * Get attribute7.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute7() {
		return attribute7;
	}

	/**
	 * Set attribute7.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute7(String newAttribute7) {
		this.attribute7 = newAttribute7;
	}


	/**
	 * Get attribute8.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute8() {
		return attribute8;
	}

	/**
	 * Set attribute8.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute8(String newAttribute8) {
		this.attribute8 = newAttribute8;
	}


	/**
	 * Get attribute9.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getAttribute9() {
		return attribute9;
	}

	/**
	 * Set attribute9.
	 * Creation date: Sep 19, 2005.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute9(String newAttribute9) {
		this.attribute9 = newAttribute9;
	}


	/**
	 * Get attribute10.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute10() {
		return attribute10;
	}

	/**
	 * Set attribute10.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute10(String newAttribute10) {
		this.attribute10 = newAttribute10;
	}

	/**
	 * Get attribute11.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute11() {
		return attribute11;
	}

	/**
	 * Set attribute11.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute11(String newAttribute11) {
		this.attribute11 = newAttribute11;
	}


	/**
	 * Get attribute12.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute12() {
		return attribute12;
	}

	/**
	 * Set attribute12.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute12(String newAttribute12) {
		this.attribute12 = newAttribute12;
	}


	/**
	 * Get attribute13.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute13() {
		return attribute13;
	}

	/**
	 * Set attribute13.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute13(String newAttribute13) {
		this.attribute13 = newAttribute13;
	}

	/**
	 * Get attribute14.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute14() {
		return attribute14;
	}

	/**
	 * Set attribute14.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute14(String newAttribute14) {
		this.attribute14 = newAttribute14;
	}


	/**
	 * Get attribute15.
	 * Creation date: Dec 19, 2006.
	 * @return String.
	 */

	public String getAttribute15() {
		return attribute15;
	}

	/**
	 * Set attribute15.
	 * Creation date: Dec 19, 2006.
	 * @param newAttribute5 java.lang.String.
	 */
	public void setAttribute15(String newAttribute15) {
		this.attribute15 = newAttribute15;
	}

	/*ITG# 73987 - End **************************/


	/**
	 * Get billingIssue.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getBillingIssue() {
		return billingIssue;
	}

	/**
	 * Set billingIssue.
	 * Creation date: Sep 19, 2005.
	 * @param newBillingIssue java.lang.String.
	 */
	public void setBillingIssue(String newBillingIssue) {
		this.billingIssue = newBillingIssue;
	}

	/**
	 * Get catId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getCatId() {
		return catId;
	}

	/**
	 * Set catId.
	 * Creation date: Sep 19, 2005.
	 * @param newCatId int.
	 */
	public void setCatId(int newCatId) {
		this.catId = newCatId;
	}

	/**
	 * Get catIdDesc.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getCatIdDesc() {
		return catIdDesc;
	}

	/**
	 * Set catIdDesc.
	 * Creation date: Sep 20, 2005.
	 * @param newCatIdDesc java.lang.String.
	 */
	public void setCatIdDesc(String newCatIdDesc) {
		this.catIdDesc = newCatIdDesc;
	}

	/**
	 * Get contractModifier.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getContractModifier() {
		return contractModifier;
	}

	/**
	 * Set contractModifier.
	 * Creation date: Sep 19, 2005.
	 * @param newContractModifier java.lang.String.
	 */
	public void setContractModifier(String newContractModifier) {
		this.contractModifier = newContractModifier;
	}

	/**
	 * Get createdBy.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Set createdBy.
	 * Creation date: Sep 19, 2005.
	 * @param newCreatedBy int.
	 */
	//Start Changes for S21 by Mangala
	//public void setCreatedBy(int newCreatedBy) {
	public void setCreatedBy(String newCreatedBy) {
	//End Changes for S21 by Mangala	
		this.createdBy = newCreatedBy;
	}

	/**
	 * Get createdByDeptCode.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCreatedByDeptCode() {
		return createdByDeptCode;
	}

	/**
	 * Set createdByDeptCode.
	 * Creation date: Sep 19, 2005.
	 * @param newcreatedByDeptCode java.lang.String.
	 */
	public void setCreatedByDeptCode(String newCreatedByDeptCode) {
		this.createdByDeptCode = newCreatedByDeptCode;
	}

	/**
	 * Get createdByResId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */
	//Start Changes for S21 by Mangala
	//public int getCreatedByResId() {
	public String getCreatedByResId() {
	//End Changes for S21 by Mangala
		return createdByResId;
	}
	


	/**
	 * Set createdByResId.
	 * Creation date: Sep 19, 2005.
	 * @param newCreatedByResId int.
	 */
	//Start Changes for S21 by Mangala
	//public void setCreatedByResId(int newCreatedByResId) {
	public void setCreatedByResId(String newCreatedByResId) {
	//End Changes for S21 by Mangala
		this.createdByResId = newCreatedByResId;
	}
	
	//Start Changes for S21 by Mangala
	public String getCollectorName() {
			return collectorName;
		}
    
	public void setCollectorName(String newCollectorName) {
			this.collectorName = newCollectorName;
		}
	//End Changes for S21 by Mangala
	/**
	 * Get createdByResName.
	 * Creation date: Sep 19, 2005.
	 * @return java.lang.String.
	 */

	public String getCreatedByResName() {
		return createdByResName;
	}

	/**
	 * Set createdByResName.
	 * Creation date: Sep 19, 2005.
	 * @param newCreatedByResName java.lang.String.
	 */
	public void setCreatedByResName(String newCreatedByResName) {
		this.createdByResName = newCreatedByResName;
	}

	/**
	 * Get creationDate.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Set creationDate.
	 * Creation date: Sep 19, 2005.
	 * @param newCreationDate java.lang.String.
	 */
	public void setCreationDate(String newCreationDate) {
		this.creationDate = newCreationDate;
	}

	/**
	 * Get custAcctId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getCustAcctId() {
		return custAcctId;
	}

	/**
	 * Set custAcctId.
	 * Creation date: Sep 19, 2005.
	 * @param newCustAcctId int.
	 */
	public void setCustAcctId(int newCustAcctId) {
		this.custAcctId = newCustAcctId;
	}

	/**
	 * Get custContact.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCustContact() {
		return custContact;
	}

	/**
	 * Set custContact.
	 * Creation date: Sep 19, 2005.
	 * @param newCustContact java.lang.String.
	 */
	public void setCustContact(String newCustContact) {
		this.custContact = newCustContact;
	}

	/**
	 * Get custEmail.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCustEmail() {
		return custEmail;
	}

	/**
	 * Set custEmail.
	 * Creation date: Sep 19, 2005.
	 * @param newCustEmail java.lang.String.
	 */
	public void setCustEmail(String newCustEmail) {
		this.custEmail = newCustEmail;
	}

	/**
	 * Get customerName.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set customerName.
	 * Creation date: Sep 19, 2005.
	 * @param newCustomerName java.lang.String.
	 */
	public void setCustomerName(String newCustomerName) {
		this.customerName = newCustomerName;
	}

	/**
	 * Get customerNo.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCustomerNo() {
		return customerNo;
	}

	/**
	 * Set customerNo.
	 * Creation date: Sep 19, 2005.
	 * @param newCustomerNo java.lang.String.
	 */
	public void setCustomerNo(String newCustomerNo) {
		this.customerNo = newCustomerNo;
	}

	/**
	 * Get custPhNo.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getCustPhNo() {
		return custPhNo;
	}

	/**
	 * Set custPhNo.
	 * Creation date: Sep 19, 2005.
	 * @param newCustPhNo java.lang.String.
	 */
	public void setCustPhNo(String newCustPhNo) {
		this.custPhNo = newCustPhNo;
	}

	/**
	 * Get jtfNotesFlag.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getJtfNotesFlag() {
		return jtfNotesFlag;
	}

	/**
	 * Set jtfNotesFlag.
	 * Creation date: Sep 19, 2005.
	 * @param newJtfNotesFlag java.lang.String.
	 */
	public void setJtfNotesFlag(String newJtfNotesFlag) {
		this.jtfNotesFlag = newJtfNotesFlag;
	}

	/**
	 * Get lastUpdateDate.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	/**
	 * Set lastUpdateDate.
	 * Creation date: Sep 19, 2005.
	 * @param newLastUpdateDate java.lang.String.
	 */
	public void setLastUpdateDate(String newLastUpdateDate) {
		this.lastUpdateDate = newLastUpdateDate;
	}

	/**
	 * Get orderType.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOrderType() {
		return orderType;
	}

	/**
	 * Set orderType.
	 * Creation date: Sep 19, 2005.
	 * @param newOrderType java.lang.String.
	 */
	public void setOrderType(String newOrderType) {
		this.orderType = newOrderType;
	}

	/**
	 * Get orgId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getOrgId() {
		return orgId;
	}

	/**
	 * Set orgId.
	 * Creation date: Sep 19, 2005.
	 * @param newOrgId int.
	 */
	public void setOrgId(int newOrgId) {
		this.orgId = newOrgId;
	}

	/**
	 * Get origEmail.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOrigEmail() {
		return origEmail;
	}

	/**
	 * Set origEmail.
	 * Creation date: Sep 19, 2005.
	 * @param newOrigEmail java.lang.String.
	 */
	public void setOrigEmail(String newOrigEmail) {
		this.origEmail = newOrigEmail;
	}

	/**
	 * Get origName.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOrigName() {
		return origName;
	}

	/**
	 * Set origName.
	 * Creation date: Sep 19, 2005.
	 * @param newOrigName java.lang.String.
	 */
	public void setOrigName(String newOrigName) {
		this.origName = newOrigName;
	}

	/**
	 * Get origPhNo.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOrigPhNo() {
		return origPhNo;
	}

	/**
	 * Set origPhNo.
	 * Creation date: Sep 19, 2005.
	 * @param newOrigPhNo java.lang.String.
	 */
	public void setOrigPhNo(String newOrigPhNo) {
		this.origPhNo = newOrigPhNo;
	}

	/**
	 * Get origType.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOrigType() {
		return origType;
	}

	/**
	 * Set origType.
	 * Creation date: Sep 19, 2005.
	 * @param newOrigType java.lang.String.
	 */
	public void setOrigType(String newOrigType) {
		this.origType = newOrigType;
	}

	/**
	 * Get ownerDeptCode.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getOwnerDeptCode() {
		return ownerDeptCode;
	}

	/**
	 * Set ownerDeptCode.
	 * Creation date: Sep 19, 2005.
	 * @param newOwnerDeptCode java.lang.String.
	 */
	public void setOwnerDeptCode(String newOwnerDeptCode) {
		this.ownerDeptCode = newOwnerDeptCode;
	}

	/**
	 * Get ownerResId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */
	//Start changes for S21 by Mangala
	//public int getOwnerResId() {
	public String getOwnerResId() {
		//End changes for S21 by Mangala
		return ownerResId;
	}

	/**
	 * Set ownerResId.
	 * Creation date: Sep 19, 2005.
	 * @param newOwnerResId int.
	 */
	//Start changes for S21 by Mangala
	//public void setOwnerResId(int newOwnerResId) {
	public void setOwnerResId(String newOwnerResId) {
		//End changes for S21 by Mangala
		this.ownerResId = newOwnerResId;
	}

	/**
	 * Get ownerResName.
	 * Creation date: Sep 19, 2005.
	 * @return java.lang.String.
	 */

	public String getOwnerResName() {
		return ownerResName;
	}

	/**
	 * Set ownerResName.
	 * Creation date: Sep 19, 2005.
	 * @param newOwnerResName java.lang.String.
	 */
	public void setOwnerResName(String newOwnerResName) {
		this.ownerResName = newOwnerResName;
	}

	/**
	 * Get recurring.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getRecurring() {
		return recurring;
	}

	/**
	 * Set recurring.
	 * Creation date: Sep 19, 2005.
	 * @param newRecurring java.lang.String.
	 */
	public void setRecurring(String newRecurring) {
		this.recurring = newRecurring;
	}

	/**
	 * Get ticketId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getTicketId() {
		return ticketId;
	}

	/**
	 * Set ticketId.
	 * Creation date: Sep 19, 2005.
	 * @param newTicketId int.
	 */
	public void setTicketId(int newTicketId) {
		this.ticketId = newTicketId;
	}

	/**
	 * Get createdByRoleId.
	 * Creation date: Sep 23, 2005.
	 * @return int.
	 */

	public String getCreatedByRoleId() {
		return createdByRoleId;
	}

	/**
	 * Set createdByRoleId.
	 * Creation date: Sep 23, 2005.
	 * @param newCreatedByRoleId int.
	 */
	public void setCreatedByRoleId(String newCreatedByRoleId) {
		this.createdByRoleId = newCreatedByRoleId;
	}

	/**
	 * Get lastUpdatedBy.
	 * Creation date: Sep 23, 2005.
	 * @return int.
	 */

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	/**
	 * Set lastUpdatedBy.
	 * Creation date: Sep 23, 2005.
	 * @param newLastUpdatedBy int.
	 */
	public void setLastUpdatedBy(String newLastUpdatedBy) {
		this.lastUpdatedBy = newLastUpdatedBy;
	}

	/**
	 * Get ownerRoleId.
	 * Creation date: Sep 23, 2005.
	 * @return int.
	 */

	public String getOwnerRoleId() {
		return ownerRoleId;
	}

	/**
	 * Set ownerRoleId.
	 * Creation date: Sep 23, 2005.
	 * @param newOwnerRoleId int.
	 */
	public void setOwnerRoleId(String newOwnerRoleId) {
		this.ownerRoleId = newOwnerRoleId;
	}

	/**
	 * Get catCode.
	 * Creation date: Oct 24, 2005.
	 * @return String.
	 */

	public String getCatCode() {
		return catCode;
	}

	/**
	 * Set catCode.
	 * Creation date: Oct 24, 2005.
	 * @param catCode String.
	 */
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}

	/**
	 * Get iDaysOpen.
	 * Creation date: Sep 23, 2005.
	 * @return int.
	 */

	public int getIDaysOpen() {
		return iDaysOpen;
	}

	/**
	 * Set iDaysOpen.
	 * Creation date: Sep 23, 2005.
	 * @param newIDaysOpen int.
	 */
	public void setIDaysOpen(int newIDaysOpen) {
		this.iDaysOpen = newIDaysOpen;
	}

	/**
	 * Get sqlTypeName.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */

	public String getSqlTypeName() {
		return sqlTypeName;
	}

	/**
	 * Set sqlTypeName.
	 * Creation date: Sep 28, 2005.
	 * @param newSqlTypeName String.
	 */
	public void setSqlTypeName(String newSqlTypeName) {
		this.sqlTypeName = newSqlTypeName;
	}

	public java.lang.String getSQLTypeName() throws SQLException {
		return sqlTypeName;
	}

	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setSqlTypeName(typeName);
		ticketId = stream.readInt();
		ticketNo = stream.readString();
		catId = stream.readInt();
		status = stream.readString();
		recurring = stream.readString();
		billingIssue = stream.readString();
		orgId = stream.readInt();
		custAcctId = stream.readInt();
		customerName = stream.readString();
		customerNo = stream.readString();
		invoiceNo = stream.readString();
		contractNo = stream.readString();
		contractModifier = stream.readString();
		orderNo = stream.readInt();
		orderType = stream.readString();
		origName = stream.readString();
		origPhNo = stream.readString();
		origEmail = stream.readString();
		origType = stream.readString();
		custContact = stream.readString();
		custPhNo = stream.readString();
		custEmail = stream.readString();
		jtfNotesFlag = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
		attribute4 = stream.readString();
		attribute5 = stream.readString();

		/*ITG# 73987 - Begin */
		attribute6 = stream.readString();
		attribute7 = stream.readString();
		attribute8 = stream.readString();
		attribute9 = stream.readString();
		attribute10 = stream.readString();
		attribute11 = stream.readString();
		attribute12 = stream.readString();
		attribute13 = stream.readString();
		attribute14 = stream.readString();
		attribute15 = stream.readString();
		/*ITG# 73987 - End */

		ownerRoleId = stream.readString();
		//Start changes for S21 by Mangala
		//ownerResId = stream.readInt();
		ownerResId = stream.readString();
		//Start changes for S21 by Mangala
		ownerDeptCode = stream.readString();
		createdBy = stream.readString();
		createdByRoleId = stream.readString();
		//Start Changes for S21 by Mangala
		//createdByResId = stream.readInt();
		createdByResId = stream.readString();
		//End Changes for S21 by Mangala
		createdByDeptCode = stream.readString();
		creationDate = stream.readString();
		lastUpdateDate = stream.readString();
		lastUpdatedBy = stream.readString();
		category = stream.readString();
		catIdDesc = stream.readString();
		createdByResName = stream.readString();
		ownerResName = stream.readString();
		aggregateContractNum = stream.readString();
		consolidateBillNum = stream.readString();
		sendEmailFlag = stream.readString();
		resolutionCode = stream.readString();
    }

	public void writeSQL(java.sql.SQLOutput stream) throws SQLException {
		writeRecObjInt(stream, getTicketId());
		writeRecObjString(stream, getTicketNo());
		writeRecObjInt(stream, getCatId());
		writeRecObjString(stream, getStatus());
		writeRecObjString(stream, getRecurring());
		writeRecObjString(stream, getBillingIssue());
		writeRecObjInt(stream, getOrgId());
		writeRecObjInt(stream, getCustAcctId());
		writeRecObjString(stream, getCustomerName());
		writeRecObjString(stream, getCustomerNo());
		writeRecObjString(stream, getInvoiceNo());
		writeRecObjString(stream, getContractNo());
		writeRecObjString(stream, getContractModifier());
		writeRecObjInt(stream, getOrderNo());
		writeRecObjString(stream, getOrderType());
		writeRecObjString(stream, getOrigName());
		writeRecObjString(stream, getOrigPhNo());
		writeRecObjString(stream, getOrigEmail());
		writeRecObjString(stream, getOrigType());
		writeRecObjString(stream, getCustContact());
		writeRecObjString(stream, getCustPhNo());
		writeRecObjString(stream, getCustEmail());
		writeRecObjString(stream, getJtfNotesFlag());
		writeRecObjString(stream, getAttribute1());
		writeRecObjString(stream, getAttribute2());
		writeRecObjString(stream, getAttribute3());
		writeRecObjString(stream, getAttribute4());
		writeRecObjString(stream, getAttribute5());

		/*ITG# 73987 - Begin */
		writeRecObjString(stream,  getAttribute6());
		writeRecObjString(stream,  getAttribute7());
		writeRecObjString(stream,  getAttribute8());
		writeRecObjString(stream,  getAttribute9());
		writeRecObjString(stream,  getAttribute10());
		writeRecObjString(stream,  getAttribute11());
		writeRecObjString(stream,  getAttribute12());
		writeRecObjString(stream,  getAttribute13());
		writeRecObjString(stream,  getAttribute14());
		writeRecObjString(stream,  getAttribute15());

		/*ITG# 73987 - End */


		writeRecObjString(stream, getOwnerRoleId());
		//Start changes for S21 by Mangala
		//writeRecObjInt(stream, getOwnerResId());
		writeRecObjString(stream, getOwnerResId());
		//End changes for S21 by Mangala
		writeRecObjString(stream, getOwnerDeptCode());
		writeRecObjString(stream, getCreatedBy());
		writeRecObjString(stream, getCreatedByRoleId());
		//Start Changes for S21 by Mangala
		//writeRecObjInt(stream, getCreatedByResId());
		writeRecObjString(stream, getCreatedByResId());
		//End Changes for S21 by Mangala
		writeRecObjString(stream, getCreatedByDeptCode());
		writeRecObjString(stream, getCreationDate());
		writeRecObjString(stream, getLastUpdateDate());
		writeRecObjString(stream, getLastUpdatedBy());
		writeRecObjString(stream, getCategory());
		writeRecObjString(stream, getCatIdDesc());
		writeRecObjString(stream, getCreatedByResName());
		writeRecObjString(stream, getOwnerResName());
		writeRecObjString(stream, getAggregateContractNum());
		writeRecObjString(stream, getConsolidateBillNum());
		writeRecObjString(stream, getsendEmailFlag());
		writeRecObjString(stream, getresolutionCode());
	}

	private void writeRecObjString(java.sql.SQLOutput stream, String value) throws SQLException {
		if (value != null) stream.writeString(value);
		else stream.writeObject(null);
	}

	private void writeRecObjInt(java.sql.SQLOutput stream, int value) throws SQLException {
		if (new Integer(value)!= null) stream.writeInt(value);
		else stream.writeObject(null);
	}

	/**
		 * toString methode: creates a String representation of the object
		 * @return the String representation
		 * @author info.vancauwenberge.tostring plugin

		 */
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Canon_E193_TicketHeaderObj[");
			buffer.append("serialVersionUID = ").append(serialVersionUID);
			buffer.append(", ticketNo = ").append(ticketNo);
			buffer.append(", category = ").append(category);
			buffer.append(", date = ").append(date);
			buffer.append(", status = ").append(status);
			buffer.append(", acctName = ").append(acctName);
			buffer.append(", acctNo = ").append(acctNo);
			buffer.append(", contactName = ").append(contactName);
			buffer.append(", contactNo = ").append(contactNo);
			buffer.append(", invoiceNo = ").append(invoiceNo);
			buffer.append(", contractNo = ").append(contractNo);
			buffer.append(", orderNo = ").append(orderNo);
			buffer.append(", attribute1 = ").append(attribute1);
			buffer.append(", attribute2 = ").append(attribute2);
			buffer.append(", attribute3 = ").append(attribute3);
			buffer.append(", attribute4 = ").append(attribute4);
			buffer.append(", attribute5 = ").append(attribute5);

			/*ITG# 73987 - Begin */
			buffer.append(", attribute6 = ").append(attribute6);
			buffer.append(", attribute7 = ").append(attribute6);
			buffer.append(", attribute8 = ").append(attribute6);
			buffer.append(", attribute9 = ").append(attribute6);
			buffer.append(", attribute10 = ").append(attribute6);
			buffer.append(", attribute11 = ").append(attribute6);
			buffer.append(", attribute12 = ").append(attribute6);
			buffer.append(", attribute13 = ").append(attribute6);
			buffer.append(", attribute14 = ").append(attribute6);
			buffer.append(", attribute15 = ").append(attribute6);
			/*ITG# 73987 - End */

			buffer.append(", billingIssue = ").append(billingIssue);
			buffer.append(", catId = ").append(catId);
			buffer.append(", catIdDesc = ").append(catIdDesc);
			buffer.append(", contractModifier = ").append(contractModifier);
			buffer.append(", createdBy = ").append(createdBy);
			buffer.append(", createdByRoleId = ").append(createdByRoleId);
			buffer.append(", createdByDeptCode = ").append(createdByDeptCode);
			buffer.append(", createdByResId = ").append(createdByResId);
			buffer.append(", creationDate = ").append(creationDate);
			buffer.append(", custAcctId = ").append(custAcctId);
			buffer.append(", custContact = ").append(custContact);
			buffer.append(", custEmail = ").append(custEmail);
			buffer.append(", custPhNo = ").append(custPhNo);
			buffer.append(", customerName = ").append(customerName);
			buffer.append(", customerNo = ").append(customerNo);
			buffer.append(", jtfNotesFlag = ").append(jtfNotesFlag);
			buffer.append(", lastUpdateDate = ").append(lastUpdateDate);
			buffer.append(", lastUpdatedBy = ").append(lastUpdatedBy);
			buffer.append(", orderType = ").append(orderType);
			buffer.append(", orgId = ").append(orgId);
			buffer.append(", origEmail = ").append(origEmail);
			buffer.append(", origName = ").append(origName);
			buffer.append(", origPhNo = ").append(origPhNo);
			buffer.append(", origType = ").append(origType);
			buffer.append(", ownerRoleId = ").append(ownerRoleId);
			buffer.append(", ownerDeptCode = ").append(ownerDeptCode);
			buffer.append(", ownerResId = ").append(ownerResId);
			buffer.append(", recurring = ").append(recurring);
			buffer.append(", ticketId = ").append(ticketId);
			buffer.append(", aggregateContractNum = ").append(aggregateContractNum);
			buffer.append(", consolidateBillNum = ").append(consolidateBillNum);
			buffer.append(", sendEmailFlag = ").append(sendEmailFlag);
			buffer.append(", resolutionCode = ").append(resolutionCode);
			buffer.append(", sqlTypeName = ").append(sqlTypeName);
			buffer.append("]");
			return buffer.toString();
		}
}

package com.canon.oracle.custapp.custinq.beans;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.util.Hashtable;

/**
 * Title:		Canon_E193_OKSBillingDtlsObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (28-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  		Date     	  By         Description
 * ----  		-----------	 ---------- ---------------------------------------------
 * ITG#434465	Jan-30-2013	 Hema		Modified for Non-Serialized Accessories 
 *
 * </pre>
 */

public class Canon_E193_OKSBillingDtlsObj implements SQLData, Serializable {

	private static final long serialVersionUID = 1L;
	private String contractId;
	private String contractNumber;
	private int authoringOrgId;
	private String fleetContract;
	private String servieBranch;
	private long customerTrxId;
	private String trxType;
	private String trxNumber;
	private String trxDate;
	private String accountNumber;
	private String customerName;
	private String dealerCode;
	private String dealerName;
	private String ediFlag;
	private String printFlag; 
	//Basudev - Defect ID# 6001576 - Start.
	private String biltoSiteId;
	private String shipToSiteId;
	//Basudev - Defect ID# 6001576 - End.
	private String bilToAttn;
	private String shipToAttn;
	private String poNumber;
	private int lseId;
	private String contractLineId;
	private String itemCode;
	private String model;
	private int baseCopyVolume;
	private int bwBaseCopyVolume;
	private int colorBaseCopyVolume; 
	private int smBaseCopyVolume;
	private int tier1CopyVolume;
	private double tier1Rate;
	private int tier2CopyVolume;
	private double tier2Rate;
	private int tier3CopyVolume;
	private double tier3Rate;
	private int tier4CopyVolume;
	private double tier4Rate;
	private String lineDescription;
	private double baseAmount;
	private double overageAmount;
	private double taxAmount;
	private String dateBilledFrom;
	private String dateBilledTo;
	private String fleetSeriaNumber;
	private int instanceId;
	private String serialNumber;
	private long billingCounterId;
	private String billingCounterName; 
	private int startReading;
	private int endReading;
	private String startMeterReadDate;
	private String endMeterReadDate;
	private int startCtrGrpLogId;
	private int endCtrGrpLogId;
	private int startTotalHardRead;
	private int endTotalHardRead;
	private int startBwHardRead;
	private int endBwHardRead;
	private int startColorRead; 
	private int endColorRead;
	private int testCopies;
	private String testCopiesIndicator;
	private String creationDate;
	private String invoiceType;
	private String serviceProgram;	//MW Project Changes
	private boolean chekcboxRCheck;
	private boolean chekcboxPCheck;
	private boolean chekcboxBCheck;   // for base check 
	private Hashtable htAttribute;
	private String billMeterType;
	
	private String sqlTypeName;
	private String aggregateContractNumber;
	private String testCopyInd;
	private String productNumber;
	private int svcinvlinePk;
	
	private String physMtrLbCd;
	private String counterDesc;
	
	/**
	 * Canon_E193_OKSBillingDtlsObj constructor comment.
	 */
	public Canon_E193_OKSBillingDtlsObj() {
		super();
	}


	/**
	 * Get htAttribute.
	 * Creation date: Oct 11, 2005.
	 * @return Hashtable.
	 */
	
	public Hashtable getHtAttribute() {
		return htAttribute;
	}


	/**
	 * Set htAttribute.
	 * Creation date: Oct 11, 2005.
	 * @param htAttribute Hashtable.
	 */
	public void setHtAttribute(Hashtable htAttribute) {
		this.htAttribute = htAttribute;
	}

	/**
	 * Get chekcboxPCheck.
	 * Creation date: Oct 17, 2005.
	 * @return boolean.
	 */
	
	public boolean isChekcboxPCheck() {
		return chekcboxPCheck;
	}


	/**
	 * Set chekcboxPCheck.
	 * Creation date: Oct 17, 2005.
	 * @param chekcboxPCheck boolean.
	 */
	public void setChekcboxPCheck(boolean chekcboxPCheck) {
		this.chekcboxPCheck = chekcboxPCheck;
	}


	/**
	 * Get chekcboxRCheck.
	 * Creation date: Oct 17, 2005.
	 * @return boolean.
	 */
	
	public boolean isChekcboxRCheck() {
		return chekcboxRCheck;
	}


	/**
	 * Set chekcboxRCheck.
	 * Creation date: Oct 17, 2005.
	 * @param chekcboxRCheck boolean.
	 */
	public void setChekcboxRCheck(boolean chekcboxRCheck) {
		this.chekcboxRCheck = chekcboxRCheck;
	}
	
	/**
	 * Get chekcboxBCheck.
	 * Creation date: Oct 17, 2005.
	 * @return boolean.
	 */
	// spothuri  --- added for Base check 
	public boolean isChekcboxBCheck() {
		return chekcboxBCheck;
	}


	/**
	 * Set chekcboxBCheck.
	 * Creation date: Oct 17, 2005.
	 * @param chekcboxRCheck boolean.
	 */
	public void setChekcboxBCheck(boolean chekcboxBCheck) {
		this.chekcboxBCheck = chekcboxBCheck;
	}


	/**
	 * Get accountNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Set accountNumber.
	 * Creation date: Oct 4, 2005.
	 * @param accountNumber String.
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * Get authoringOrgId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getAuthoringOrgId() {
		return authoringOrgId;
	}

	/**
	 * Set authoringOrgId.
	 * Creation date: Oct 4, 2005.
	 * @param authoringOrgId int.
	 */
	public void setAuthoringOrgId(int authoringOrgId) {
		this.authoringOrgId = authoringOrgId;
	}

	/**
	 * Get baseAmount.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getBaseAmount() {
		return baseAmount;
	}

	/**
	 * Set baseAmount.
	 * Creation date: Oct 4, 2005.
	 * @param baseAmount double.
	 */
	public void setBaseAmount(double baseAmount) {
		this.baseAmount = baseAmount;
	}

	/**
	 * Get baseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getBaseCopyVolume() {
		return baseCopyVolume;
	}

	/**
	 * Set baseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param baseCopyVolume int.
	 */
	public void setBaseCopyVolume(int baseCopyVolume) {
		this.baseCopyVolume = baseCopyVolume;
	}

	/**
	 * Get billingCounterId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public long getBillingCounterId() {
		return billingCounterId;
	}

	/**
	 * Set billingCounterId.
	 * Creation date: Oct 4, 2005.
	 * @param billingCounterId int.
	 */
	public void setBillingCounterId(long billingCounterId) {
		this.billingCounterId = billingCounterId;
	}

	/**
	 * Get billingCounterName.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getBillingCounterName() {
		return billingCounterName;
	}

	/**
	 * Set billingCounterName.
	 * Creation date: Oct 4, 2005.
	 * @param billingCounterName String.
	 */
	public void setBillingCounterName(String billingCounterName) {
		this.billingCounterName = billingCounterName;
	}

	/**
	 * Get bilToAttn.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getBilToAttn() {
		return bilToAttn;
	}

	/**
	 * Set bilToAttn.
	 * Creation date: Oct 4, 2005.
	 * @param bilToAttn String.
	 */
	public void setBilToAttn(String bilToAttn) {
		this.bilToAttn = bilToAttn;
	}

	//Basudev - Defect ID# 6001576 - Start.
	/**
	 * Get biltoSiteId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public String getBiltoSiteId() {
		return biltoSiteId;
	}

	/**
	 * Set biltoSiteId.
	 * Creation date: Oct 4, 2005.
	 * @param biltoSiteId int.
	 */
	public void setBiltoSiteId(String biltoSiteId) {
		this.biltoSiteId = biltoSiteId;
	}
	//Basudev - Defect ID# 6001576 - End.
	/**
	 * Get bwBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getBwBaseCopyVolume() {
		return bwBaseCopyVolume;
	}

	/**
	 * Set bwBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param bwBaseCopyVolume int.
	 */
	public void setBwBaseCopyVolume(int bwBaseCopyVolume) {
		this.bwBaseCopyVolume = bwBaseCopyVolume;
	}

	/**
	 * Get colorBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getColorBaseCopyVolume() {
		return colorBaseCopyVolume;
	}

	/**
	 * Set colorBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param colorBaseCopyVolume int.
	 */
	public void setColorBaseCopyVolume(int colorBaseCopyVolume) {
		this.colorBaseCopyVolume = colorBaseCopyVolume;
	}

	/**
	 * Get contractId.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getContractId() {
		return contractId;
	}

	/**
	 * Set contractId.
	 * Creation date: Oct 4, 2005.
	 * @param contractId String.
	 */
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	/**
	 * Get contractLineId.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getContractLineId() {
		return contractLineId;
	}

	/**
	 * Set contractLineId.
	 * Creation date: Oct 4, 2005.
	 * @param contractLineId String.
	 */
	public void setContractLineId(String contractLineId) {
		this.contractLineId = contractLineId;
	}

	/**
	 * Get contractNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getContractNumber() {
		return contractNumber;
	}

	/**
	 * Set contractNumber.
	 * Creation date: Oct 4, 2005.
	 * @param contractNumber String.
	 */
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	/**
	 * Get creationDate.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * Set creationDate.
	 * Creation date: Oct 4, 2005.
	 * @param creationDate String.
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Get customerName.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Set customerName.
	 * Creation date: Oct 4, 2005.
	 * @param customerName String.
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Get customerTrxId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public long getCustomerTrxId() {
		return customerTrxId;
	}

	/**
	 * Set customerTrxId.
	 * Creation date: Oct 4, 2005.
	 * @param customerTrxId int.
	 */
	public void setCustomerTrxId(long customerTrxId) {
		this.customerTrxId = customerTrxId;
	}

	/**
	 * Get dateBilledFrom.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getDateBilledFrom() {
		return dateBilledFrom;
	}

	/**
	 * Set dateBilledFrom.
	 * Creation date: Oct 4, 2005.
	 * @param dateBilledFrom String.
	 */
	public void setDateBilledFrom(String dateBilledFrom) {
		this.dateBilledFrom = dateBilledFrom;
	}

	/**
	 * Get dateBilledTo.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getDateBilledTo() {
		return dateBilledTo;
	}

	/**
	 * Set dateBilledTo.
	 * Creation date: Oct 4, 2005.
	 * @param dateBilledTo String.
	 */
	public void setDateBilledTo(String dateBilledTo) {
		this.dateBilledTo = dateBilledTo;
	}

	/**
	 * Get dealerCode.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getDealerCode() {
		return dealerCode;
	}

	/**
	 * Set dealerCode.
	 * Creation date: Oct 4, 2005.
	 * @param dealerCode String.
	 */
	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	/**
	 * Get dealerName.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getDealerName() {
		return dealerName;
	}

	/**
	 * Set dealerName.
	 * Creation date: Oct 4, 2005.
	 * @param dealerName String.
	 */
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	/**
	 * Get ediFlag.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getEdiFlag() {
		return ediFlag;
	}

	/**
	 * Set ediFlag.
	 * Creation date: Oct 4, 2005.
	 * @param ediFlag String.
	 */
	public void setEdiFlag(String ediFlag) {
		this.ediFlag = ediFlag;
	}

	/**
	 * Get endBwHardRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getEndBwHardRead() {
		return endBwHardRead;
	}

	/**
	 * Set endBwHardRead.
	 * Creation date: Oct 4, 2005.
	 * @param endBwHardRead int.
	 */
	public void setEndBwHardRead(int endBwHardRead) {
		this.endBwHardRead = endBwHardRead;
	}

	/**
	 * Get endColorRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getEndColorRead() {
		return endColorRead;
	}

	/**
	 * Set endColorRead.
	 * Creation date: Oct 4, 2005.
	 * @param endColorRead int.
	 */
	public void setEndColorRead(int endColorRead) {
		this.endColorRead = endColorRead;
	}

	/**
	 * Get endCtrGrpLogId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getEndCtrGrpLogId() {
		return endCtrGrpLogId;
	}

	/**
	 * Set endCtrGrpLogId.
	 * Creation date: Oct 4, 2005.
	 * @param endCtrGrpLogId int.
	 */
	public void setEndCtrGrpLogId(int endCtrGrpLogId) {
		this.endCtrGrpLogId = endCtrGrpLogId;
	}

	/**
	 * Get endMeterReadDate.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getEndMeterReadDate() {
		return endMeterReadDate;
	}

	/**
	 * Set endMeterReadDate.
	 * Creation date: Oct 4, 2005.
	 * @param endMeterReadDate String.
	 */
	public void setEndMeterReadDate(String endMeterReadDate) {
		this.endMeterReadDate = endMeterReadDate;
	}

	/**
	 * Get endReading.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getEndReading() {
		return endReading;
	}

	/**
	 * Set endReading.
	 * Creation date: Oct 4, 2005.
	 * @param endReading int.
	 */
	public void setEndReading(int endReading) {
		this.endReading = endReading;
	}

	/**
	 * Get endTotalHardRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getEndTotalHardRead() {
		return endTotalHardRead;
	}

	/**
	 * Set endTotalHardRead.
	 * Creation date: Oct 4, 2005.
	 * @param endTotalHardRead int.
	 */
	public void setEndTotalHardRead(int endTotalHardRead) {
		this.endTotalHardRead = endTotalHardRead;
	}

	/**
	 * Get fleetContract.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getFleetContract() {
		return fleetContract;
	}

	/**
	 * Set fleetContract.
	 * Creation date: Oct 4, 2005.
	 * @param fleetContract String.
	 */
	public void setFleetContract(String fleetContract) {
		this.fleetContract = fleetContract;
	}

	/**
	 * Get fleetSeriaNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getFleetSeriaNumber() {
		return fleetSeriaNumber;
	}

	/**
	 * Set fleetSeriaNumber.
	 * Creation date: Oct 4, 2005.
	 * @param fleetSeriaNumber String.
	 */
	public void setFleetSeriaNumber(String fleetSeriaNumber) {
		this.fleetSeriaNumber = fleetSeriaNumber;
	}

	/**
	 * Get instanceId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getInstanceId() {
		return instanceId;
	}

	/**
	 * Set instanceId.
	 * Creation date: Oct 4, 2005.
	 * @param instanceId int.
	 */
	public void setInstanceId(int instanceId) {
		this.instanceId = instanceId;
	}

	/**
	 * Get invoiceType.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getInvoiceType() {
		return invoiceType;
	}

	/**
	 * Set invoiceType.
	 * Creation date: Oct 4, 2005.
	 * @param invoiceType String.
	 */
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	/**
	 * MW Project Changes
	 * Get serviceProgram.
	 * Creation date: Oct 1, 2009.
	 * @return String.
	 */
	
	public String getServiceProgram() {
		return serviceProgram;
	}

	/**
	 * MW Project Changes
	 * Set invoiceType.
	 * Creation date: Oct 1, 2009.
	 * @param invoiceType String.
	 */
	public void setServiceProgram(String serviceProgram) {
		this.serviceProgram = serviceProgram;
	}

	/**
	 * Get itemCode.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getItemCode() {
		return itemCode;
	}

	/**
	 * Set itemCode.
	 * Creation date: Oct 4, 2005.
	 * @param itemCode String.
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	/**
	 * Get lineDescription.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getLineDescription() {
		return lineDescription;
	}

	/**
	 * Set lineDescription.
	 * Creation date: Oct 4, 2005.
	 * @param lineDescription String.
	 */
	public void setLineDescription(String lineDescription) {
		this.lineDescription = lineDescription;
	}

	/**
	 * Get lseId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getLseId() {
		return lseId;
	}

	/**
	 * Set lseId.
	 * Creation date: Oct 4, 2005.
	 * @param lseId int.
	 */
	public void setLseId(int lseId) {
		this.lseId = lseId;
	}

	/**
	 * Get model.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getModel() {
		return model;
	}

	/**
	 * Set model.
	 * Creation date: Oct 4, 2005.
	 * @param model String.
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Get overageAmount.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getOverageAmount() {
		return overageAmount;
	}

	/**
	 * Set overageAmount.
	 * Creation date: Oct 4, 2005.
	 * @param overageAmount double.
	 */
	public void setOverageAmount(double overageAmount) {
		this.overageAmount = overageAmount;
	}

	/**
	 * Get poNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getPoNumber() {
		return poNumber;
	}

	/**
	 * Set poNumber.
	 * Creation date: Oct 4, 2005.
	 * @param poNumber String.
	 */
	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	/**
	 * Get printFlag.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getPrintFlag() {
		return printFlag;
	}

	/**
	 * Set printFlag.
	 * Creation date: Oct 4, 2005.
	 * @param printFlag String.
	 */
	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	/**
	 * Get serialNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Set serialNumber.
	 * Creation date: Oct 4, 2005.
	 * @param serialNumber String.
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	/**
	 * Get productNumber.
	 * Creation date: Jan 30, 2013.
	 * @return String.
	 */
	
	public String getProductNumber() {
		return productNumber;
	}

	/**
	 * Set productNumber.
	 * Creation date: Jan 30, 2013.
	 * @param instanceNumber String.
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}	

	/**
	 * Get servieBranch.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getServieBranch() {
		return servieBranch;
	}

	/**
	 * Set servieBranch.
	 * Creation date: Oct 4, 2005.
	 * @param servieBranch String.
	 */
	public void setServieBranch(String servieBranch) {
		this.servieBranch = servieBranch;
	}

	/**
	 * Get shipToAttn.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getShipToAttn() {
		return shipToAttn;
	}

	/**
	 * Set shipToAttn.
	 * Creation date: Oct 4, 2005.
	 * @param shipToAttn String.
	 */
	public void setShipToAttn(String shipToAttn) {
		this.shipToAttn = shipToAttn;
	}

	//Basudev - Defect ID# 6001576 - Start.
	/**
	 * Get shipToSiteId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public String getShipToSiteId() {
		return shipToSiteId;
	}

	/**
	 * Set shipToSiteId.
	 * Creation date: Oct 4, 2005.
	 * @param shipToSiteId int.
	 */
	public void setShipToSiteId(String shipToSiteId) {
		this.shipToSiteId = shipToSiteId;
	}
	//Basudev - Defect ID# 6001576 - End.
	/**
	 * Get smBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getSmBaseCopyVolume() {
		return smBaseCopyVolume;
	}

	/**
	 * Set smBaseCopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param smBaseCopyVolume int.
	 */
	public void setSmBaseCopyVolume(int smBaseCopyVolume) {
		this.smBaseCopyVolume = smBaseCopyVolume;
	}

	/**
	 * Get startBwHardRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getStartBwHardRead() {
		return startBwHardRead;
	}

	/**
	 * Set startBwHardRead.
	 * Creation date: Oct 4, 2005.
	 * @param startBwHardRead int.
	 */
	public void setStartBwHardRead(int startBwHardRead) {
		this.startBwHardRead = startBwHardRead;
	}

	/**
	 * Get startColorRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getStartColorRead() {
		return startColorRead;
	}

	/**
	 * Set startColorRead.
	 * Creation date: Oct 4, 2005.
	 * @param startColorRead int.
	 */
	public void setStartColorRead(int startColorRead) {
		this.startColorRead = startColorRead;
	}

	/**
	 * Get startCtrGrpLogId.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getStartCtrGrpLogId() {
		return startCtrGrpLogId;
	}

	/**
	 * Set startCtrGrpLogId.
	 * Creation date: Oct 4, 2005.
	 * @param startCtrGrpLogId int.
	 */
	public void setStartCtrGrpLogId(int startCtrGrpLogId) {
		this.startCtrGrpLogId = startCtrGrpLogId;
	}

	/**
	 * Get startMeterReadDate.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getStartMeterReadDate() {
		return startMeterReadDate;
	}

	/**
	 * Set startMeterReadDate.
	 * Creation date: Oct 4, 2005.
	 * @param startMeterReadDate String.
	 */
	public void setStartMeterReadDate(String startMeterReadDate) {
		this.startMeterReadDate = startMeterReadDate;
	}

	/**
	 * Get startReading.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getStartReading() {
		return startReading;
	}

	/**
	 * Set startReading.
	 * Creation date: Oct 4, 2005.
	 * @param startReading int.
	 */
	public void setStartReading(int startReading) {
		this.startReading = startReading;
	}

	/**
	 * Get startTotalHardRead.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getStartTotalHardRead() {
		return startTotalHardRead;
	}

	/**
	 * Set startTotalHardRead.
	 * Creation date: Oct 4, 2005.
	 * @param startTotalHardRead int.
	 */
	public void setStartTotalHardRead(int startTotalHardRead) {
		this.startTotalHardRead = startTotalHardRead;
	}

	/**
	 * Get taxAmount.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * Set taxAmount.
	 * Creation date: Oct 4, 2005.
	 * @param taxAmount double.
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * Get testCopies.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getTestCopies() {
		return testCopies;
	}

	/**
	 * Set testCopies.
	 * Creation date: Oct 4, 2005.
	 * @param testCopies int.
	 */
	public void setTestCopies(int testCopies) {
		this.testCopies = testCopies;
	}

	/**
	 * Get testCopiesIndicator.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getTestCopiesIndicator() {
		return testCopiesIndicator;
	}

	/**
	 * Set testCopiesIndicator.
	 * Creation date: Oct 4, 2005.
	 * @param testCopiesIndicator String.
	 */
	public void setTestCopiesIndicator(String testCopiesIndicator) {
		this.testCopiesIndicator = testCopiesIndicator;
	}

	/**
	 * Get tier1CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getTier1CopyVolume() {
		return tier1CopyVolume;
	}

	/**
	 * Set tier1CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param tier1CopyVolume int.
	 */
	public void setTier1CopyVolume(int tier1CopyVolume) {
		this.tier1CopyVolume = tier1CopyVolume;
	}

	/**
	 * Get tier1Rate.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getTier1Rate() {
		return tier1Rate;
	}

	/**
	 * Set tier1Rate.
	 * Creation date: Oct 4, 2005.
	 * @param tier1Rate double.
	 */
	public void setTier1Rate(double tier1Rate) {
		this.tier1Rate = tier1Rate;
	}

	/**
	 * Get tier2CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getTier2CopyVolume() {
		return tier2CopyVolume;
	}

	/**
	 * Set tier2CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param tier2CopyVolume int.
	 */
	public void setTier2CopyVolume(int tier2CopyVolume) {
		this.tier2CopyVolume = tier2CopyVolume;
	}

	/**
	 * Get tier2Rate.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getTier2Rate() {
		return tier2Rate;
	}

	/**
	 * Set tier2Rate.
	 * Creation date: Oct 4, 2005.
	 * @param tier2Rate double.
	 */
	public void setTier2Rate(double tier2Rate) {
		this.tier2Rate = tier2Rate;
	}

	/**
	 * Get tier3CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getTier3CopyVolume() {
		return tier3CopyVolume;
	}

	/**
	 * Set tier3CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param tier3CopyVolume int.
	 */
	public void setTier3CopyVolume(int tier3CopyVolume) {
		this.tier3CopyVolume = tier3CopyVolume;
	}

	/**
	 * Get tier3Rate.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getTier3Rate() {
		return tier3Rate;
	}

	/**
	 * Set tier3Rate.
	 * Creation date: Oct 4, 2005.
	 * @param tier3Rate double.
	 */
	public void setTier3Rate(double tier3Rate) {
		this.tier3Rate = tier3Rate;
	}

	/**
	 * Get tier4CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @return int.
	 */
	
	public int getTier4CopyVolume() {
		return tier4CopyVolume;
	}

	/**
	 * Set tier4CopyVolume.
	 * Creation date: Oct 4, 2005.
	 * @param tier4CopyVolume int.
	 */
	public void setTier4CopyVolume(int tier4CopyVolume) {
		this.tier4CopyVolume = tier4CopyVolume;
	}

	/**
	 * Get tier4Rate.
	 * Creation date: Oct 4, 2005.
	 * @return double.
	 */
	
	public double getTier4Rate() {
		return tier4Rate;
	}

	/**
	 * Set tier4Rate.
	 * Creation date: Oct 4, 2005.
	 * @param tier4Rate double.
	 */
	public void setTier4Rate(double tier4Rate) {
		this.tier4Rate = tier4Rate;
	}

	/**
	 * Get trxDate.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getTrxDate() {
		return trxDate;
	}

	/**
	 * Set trxDate.
	 * Creation date: Oct 4, 2005.
	 * @param trxDate String.
	 */
	public void setTrxDate(String trxDate) {
		this.trxDate = trxDate;
	}

	/**
	 * Get trxNumber.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getTrxNumber() {
		return trxNumber;
	}

	/**
	 * Set trxNumber.
	 * Creation date: Oct 4, 2005.
	 * @param trxNumber String.
	 */
	public void setTrxNumber(String trxNumber) {
		this.trxNumber = trxNumber;
	}

	/**
	 * Get trxType.
	 * Creation date: Oct 4, 2005.
	 * @return String.
	 */
	
	public String getTrxType() {
		return trxType;
	}

	/**
	 * Set trxType.
	 * Creation date: Oct 4, 2005.
	 * @param trxType String.
	 */
	public void setTrxType(String trxType) {
		this.trxType = trxType;
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
	
	public String getAggregateContractNumber() {
		return aggregateContractNumber;
	}
	/**
	 * Set aggregateContractNumber.
	 * Creation date: Mar 28, 2012.
	 * @param aggregateContractNumber String.
	 */
	public void setAggregateContractNumber(String aggregateContractNumber) {
		this.aggregateContractNumber = aggregateContractNumber;
	}
	
	public String getBillMeterType(){
		return billMeterType;
	}
	public void setBillMeterType(String billMeterType){
		this.billMeterType = billMeterType;
	}
	public String getTestCopyInd(){
		return testCopyInd;
	}
	public void setTestCopyInd(String testCopyInd){
		this.testCopyInd = testCopyInd;
	}
			
	
	public int getSvcinvlinePk() {
		return svcinvlinePk;
	}


	public void setSvcinvlinePk(int svcinvlinePk) {
		this.svcinvlinePk = svcinvlinePk;
	}


	public String getPhysMtrLbCd() {
		return physMtrLbCd;
	}


	public void setPhysMtrLbCd(String physMtrLbCd) {
		this.physMtrLbCd = physMtrLbCd;
	}


	public String getCounterDesc() {
		return counterDesc;
	}


	public void setCounterDesc(String counterDesc) {
		this.counterDesc = counterDesc;
	}


	public void readSQL(SQLInput stream, String typeName) throws SQLException {
		setSqlTypeName(typeName);
		contractId = stream.readString();
		contractNumber = stream.readString();
		authoringOrgId  = stream.readInt();
		fleetContract = stream.readString();
		servieBranch = stream.readString();
		customerTrxId = stream.readLong();
		trxType = stream.readString();
		trxNumber = stream.readString();
		trxDate = stream.readString();
		accountNumber = stream.readString();
		customerName = stream.readString();
		dealerCode = stream.readString();
		dealerName = stream.readString();
		ediFlag = stream.readString();
		printFlag = stream.readString(); 
		biltoSiteId = stream.readString(); //Basudev - Defect ID# 6001576 - Start.
		shipToSiteId = stream.readString();//Basudev - Defect ID# 6001576 - End.
		bilToAttn = stream.readString();
		shipToAttn = stream.readString();
		poNumber = stream.readString();
		lseId = stream.readInt();
		contractLineId = stream.readString();
		itemCode = stream.readString();
		model = stream.readString();
		baseCopyVolume = stream.readInt(); 
		bwBaseCopyVolume = stream.readInt();
		colorBaseCopyVolume = stream.readInt();
		smBaseCopyVolume = stream.readInt();
		tier1CopyVolume = stream.readInt();
		tier1Rate = stream.readDouble();
		tier2CopyVolume = stream.readInt();
		tier2Rate = stream.readDouble();
		tier3CopyVolume = stream.readInt();
		tier3Rate = stream.readDouble();
		tier4CopyVolume = stream.readInt();
		tier4Rate = stream.readDouble();
		lineDescription = stream.readString();
		baseAmount = stream.readDouble();
		overageAmount = stream.readDouble();
		taxAmount = stream.readInt();
		dateBilledFrom = stream.readString();
		dateBilledTo = stream.readString();
		fleetSeriaNumber = stream.readString();
		instanceId = stream.readInt();
		serialNumber = stream.readString();
		billingCounterId = stream.readLong();
		billingCounterName = stream.readString(); 
		startReading = stream.readInt();
		endReading = stream.readInt();
		startMeterReadDate = stream.readString();
		endMeterReadDate = stream.readString();
		startCtrGrpLogId = stream.readInt();
		endCtrGrpLogId = stream.readInt();
		startTotalHardRead = stream.readInt();
		endTotalHardRead = stream.readInt();
		startBwHardRead = stream.readInt();
		endBwHardRead = stream.readInt();
		startColorRead = stream.readInt();
		endColorRead = stream.readInt();
		testCopies = stream.readInt();
		testCopiesIndicator = stream.readString();
		creationDate = stream.readString();
		invoiceType = stream.readString();
		serviceProgram = stream.readString();	//MW Project Changes
		
		//chekcboxRCheck = stream.readBoolean();
		//chekcboxPCheck = stream.readBoolean();
		//chekcboxBCheck = stream.readBoolean();
		//billMeterType = stream.readString();
		//testCopyInd = stream.readString();
		
		aggregateContractNumber = stream.readString();//MW Project Changes
		productNumber = stream.readString();
		svcinvlinePk=stream.readInt();
		
		//physMtrLbCd = stream.readString();
		//counterDesc = stream.readString();
    }
	
	public void writeSQL(java.sql.SQLOutput stream) throws SQLException {
		writeRecObjString(stream, getContractId());
		writeRecObjString(stream, getContractNumber());
		writeRecObjInt(stream, getAuthoringOrgId());
		writeRecObjString(stream, getFleetContract());
		writeRecObjString(stream, getServieBranch());
		writeRecObjLong(stream, getCustomerTrxId());
		writeRecObjString(stream, getTrxType());
		writeRecObjString(stream, getTrxNumber());
		writeRecObjString(stream, getTrxDate());
		writeRecObjString(stream, getAccountNumber());
		writeRecObjString(stream, getCustomerName());
		writeRecObjString(stream, getDealerCode());
		writeRecObjString(stream, getDealerName());
		writeRecObjString(stream, getEdiFlag());
		writeRecObjString(stream, getPrintFlag()); 
		writeRecObjString(stream, getBiltoSiteId());    //Basudev - Defect ID# 6001576 - Start.
		writeRecObjString(stream, getShipToSiteId());   //Basudev - Defect ID# 6001576 - End.
		writeRecObjString(stream, getBilToAttn());
		writeRecObjString(stream, getShipToAttn());
		writeRecObjString(stream, getPoNumber());
		writeRecObjInt(stream, getLseId());
		writeRecObjString(stream, getContractLineId());
		writeRecObjString(stream, getItemCode());
		writeRecObjString(stream, getModel());
		writeRecObjInt(stream, getBaseCopyVolume());
		writeRecObjInt(stream, getBwBaseCopyVolume());
		writeRecObjInt(stream, getColorBaseCopyVolume());
		writeRecObjInt(stream, getSmBaseCopyVolume());
		writeRecObjInt(stream, getTier1CopyVolume());
		writeRecObjDouble(stream, getTier1Rate());
		writeRecObjInt(stream, getTier2CopyVolume());
		writeRecObjDouble(stream, getTier2Rate());
		writeRecObjInt(stream, getTier3CopyVolume());
		writeRecObjDouble(stream, getTier3Rate());
		writeRecObjInt(stream, getTier4CopyVolume());
		writeRecObjDouble(stream, getTier4Rate());
		writeRecObjString(stream, getLineDescription());
		writeRecObjDouble(stream, getBaseAmount());
		writeRecObjDouble(stream, getOverageAmount());
		writeRecObjDouble(stream, getTaxAmount());
		writeRecObjString(stream, getDateBilledFrom());
		writeRecObjString(stream, getDateBilledTo());
		writeRecObjString(stream, getFleetSeriaNumber());
		writeRecObjInt(stream, getInstanceId ());
		writeRecObjString(stream, getSerialNumber());
		writeRecObjLong(stream, getBillingCounterId());
		writeRecObjString(stream, getBillingCounterName()); 
		writeRecObjInt(stream, getStartReading());
		writeRecObjInt(stream, getEndReading());
		writeRecObjString(stream, getStartMeterReadDate());
		writeRecObjString(stream, getEndMeterReadDate());
		writeRecObjInt(stream, getStartCtrGrpLogId());
		writeRecObjInt(stream, getEndCtrGrpLogId());
		writeRecObjInt(stream, getStartTotalHardRead());
		writeRecObjInt(stream, getEndTotalHardRead());
		writeRecObjInt(stream, getStartBwHardRead());
		writeRecObjInt(stream, getEndBwHardRead());
		writeRecObjInt(stream, getStartColorRead());
		writeRecObjInt(stream, getEndColorRead());
		writeRecObjInt(stream, getTestCopies());
		writeRecObjString(stream, getTestCopiesIndicator());
		writeRecObjString(stream, getCreationDate());
		writeRecObjString(stream, getInvoiceType());
		writeRecObjString(stream, getServiceProgram());		//MW Project Changes
		
		//writeRecObjBoolean(stream, isChekcboxRCheck());
		//writeRecObjBoolean(stream, isChekcboxPCheck());
		//writeRecObjBoolean(stream, isChekcboxBCheck());
		//writeRecObjString(stream, getBillMeterType());
		
		writeRecObjString(stream, getAggregateContractNumber());
		writeRecObjString(stream, getProductNumber());	
		writeRecObjInt(stream, getSvcinvlinePk());
		
		//writeRecObjString(stream, getTestCopyInd());
		//writeRecObjString(stream, getPhysMtrLbCd()); 
		//writeRecObjString(stream, getCounterDesc());
	}
	
	private void writeRecObjLong(SQLOutput stream, long value)throws SQLException {
		// TODO Auto-generated method stub
		if (new Long(value)!= null) stream.writeLong(value);
		else stream.writeObject(null);
		
	}


	private void writeRecObjString(SQLOutput stream, String value) throws SQLException {
		if (value != null) stream.writeString(value);
		else stream.writeObject(null);
	}
	
	private void writeRecObjInt(SQLOutput stream, int value) throws SQLException {
		if (new Integer(value)!= null) stream.writeInt(value);
		else stream.writeObject(null);
	}

	private void writeRecObjDouble(SQLOutput stream, double value) throws SQLException {
		if (new Double(value)!= null) stream.writeDouble(value);
		else stream.writeObject(null);
	}
	
	/*private void writeRecObjBoolean(SQLOutput stream, boolean value) throws SQLException {
		if (new Boolean(value)!= null) stream.writeBoolean(value);
		else stream.writeBoolean(Boolean.FALSE);
	}*/


	/**
		 * toString methode: creates a String representation of the object
		 * @return the String representation
		 * @author info.vancauwenberge.tostring plugin
	
		 */
		public String toString() {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Canon_E193_OKSBillingDtlsObj[");
			buffer.append("serialVersionUID = ").append(serialVersionUID);
			buffer.append(", contractId = ").append(contractId);
			buffer.append(", contractNumber = ").append(contractNumber);
			buffer.append(", authoringOrgId = ").append(authoringOrgId);
			buffer.append(", fleetContract = ").append(fleetContract);
			buffer.append(", servieBranch = ").append(servieBranch);
			buffer.append(", customerTrxId = ").append(customerTrxId);
			buffer.append(", trxType = ").append(trxType);
			buffer.append(", trxNumber = ").append(trxNumber);
			buffer.append(", trxDate = ").append(trxDate);
			buffer.append(", accountNumber = ").append(accountNumber);
			buffer.append(", customerName = ").append(customerName);
			buffer.append(", dealerCode = ").append(dealerCode);
			buffer.append(", dealerName = ").append(dealerName);
			buffer.append(", ediFlag = ").append(ediFlag);
			buffer.append(", printFlag = ").append(printFlag);
			buffer.append(", biltoSiteId = ").append(biltoSiteId);
			buffer.append(", shipToSiteId = ").append(shipToSiteId);
			buffer.append(", bilToAttn = ").append(bilToAttn);
			buffer.append(", shipToAttn = ").append(shipToAttn);
			buffer.append(", poNumber = ").append(poNumber);
			buffer.append(", lseId = ").append(lseId);
			buffer.append(", contractLineId = ").append(contractLineId);
			buffer.append(", itemCode = ").append(itemCode);
			buffer.append(", model = ").append(model);
			buffer.append(", baseCopyVolume = ").append(baseCopyVolume);
			buffer.append(", bwBaseCopyVolume = ").append(bwBaseCopyVolume);
			buffer.append(", colorBaseCopyVolume = ").append(colorBaseCopyVolume);
			buffer.append(", smBaseCopyVolume = ").append(smBaseCopyVolume);
			buffer.append(", tier1CopyVolume = ").append(tier1CopyVolume);
			buffer.append(", tier1Rate = ").append(tier1Rate);
			buffer.append(", tier2CopyVolume = ").append(tier2CopyVolume);
			buffer.append(", tier2Rate = ").append(tier2Rate);
			buffer.append(", tier3CopyVolume = ").append(tier3CopyVolume);
			buffer.append(", tier3Rate = ").append(tier3Rate);
			buffer.append(", tier4CopyVolume = ").append(tier4CopyVolume);
			buffer.append(", tier4Rate = ").append(tier4Rate);
			buffer.append(", lineDescription = ").append(lineDescription);
			buffer.append(", baseAmount = ").append(baseAmount);
			buffer.append(", overageAmount = ").append(overageAmount);
			buffer.append(", taxAmount = ").append(taxAmount);
			buffer.append(", dateBilledFrom = ").append(dateBilledFrom);
			buffer.append(", dateBilledTo = ").append(dateBilledTo);
			buffer.append(", fleetSeriaNumber = ").append(fleetSeriaNumber);
			buffer.append(", instanceId = ").append(instanceId);
			buffer.append(", serialNumber = ").append(serialNumber);
			buffer.append(", billingCounterId = ").append(billingCounterId);
			buffer.append(", billingCounterName = ").append(billingCounterName);
			buffer.append(", startReading = ").append(startReading);
			buffer.append(", endReading = ").append(endReading);
			buffer.append(", startMeterReadDate = ").append(startMeterReadDate);
			buffer.append(", endMeterReadDate = ").append(endMeterReadDate);
			buffer.append(", startCtrGrpLogId = ").append(startCtrGrpLogId);
			buffer.append(", endCtrGrpLogId = ").append(endCtrGrpLogId);
			buffer.append(", startTotalHardRead = ").append(startTotalHardRead);
			buffer.append(", endTotalHardRead = ").append(endTotalHardRead);
			buffer.append(", startBwHardRead = ").append(startBwHardRead);
			buffer.append(", endBwHardRead = ").append(endBwHardRead);
			buffer.append(", startColorRead = ").append(startColorRead);
			buffer.append(", endColorRead = ").append(endColorRead);
			buffer.append(", testCopies = ").append(testCopies);
			buffer.append(", testCopiesIndicator = ").append(testCopiesIndicator);
			buffer.append(", creationDate = ").append(creationDate);
			buffer.append(", invoiceType = ").append(invoiceType);
			buffer.append(", chekcboxRCheck = ").append(chekcboxRCheck);
			buffer.append(", chekcboxPCheck = ").append(chekcboxPCheck);
			buffer.append(", chekcboxBCheck = ").append(chekcboxBCheck);
			buffer.append(", billMeterType = ").append(billMeterType);
			buffer.append(", htAttribute = ").append(htAttribute);
			buffer.append(", sqlTypeName = ").append(sqlTypeName);
			buffer.append(", serviceProgram = ").append(serviceProgram);	//MW Project Changes
			buffer.append(", aggregateContractNumber = ").append(aggregateContractNumber);
			buffer.append(", testCopyInd = ").append(testCopyInd);
			buffer.append(", productNumber = ").append(productNumber);
			buffer.append(", svcinvlinePk = ").append(svcinvlinePk);
			buffer.append(", physMtrLbCd = ").append(physMtrLbCd);
			buffer.append(", counterDesc = ").append(counterDesc);
			buffer.append("]");
			return buffer.toString();
		}

}

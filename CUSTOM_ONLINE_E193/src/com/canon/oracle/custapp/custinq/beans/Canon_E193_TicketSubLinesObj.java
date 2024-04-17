package com.canon.oracle.custapp.custinq.beans;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;

/**
 * Title:		Canon_E193_TicketSubLinesObj<br>
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
 * Flag  	  Date       	By         Description
 * ----  	  ---------- 	---------- ---------------------------------------------
 *ITG#434465  05-Feb-2013	Hema		To handle Non-Serialized accessories.
 * </pre>
 */

public class Canon_E193_TicketSubLinesObj implements SQLData, Serializable {

	private static final long serialVersionUID = 1L;
	private int subLineId;
	private int lineId;
	private int catId;
	private String catCode;
	private String catDesc;
	private String parentCatId;
	private String newFlag;
	private String crFlag;
	private String companyMoveFlag;
	private String cancelEquipFlag;
	private String taxExamption;
	private String creditReason;
	private String serialNo;
	private String objectType;
	private String objectValue;
	private String currentValue;
	private String newValue;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private String invoiceNumbers;
	private String productNumber;
	private String fleetFlag;
	
	private String sqlTypeName;
	
	
	
	/**
	 * Canon_E193_TicketSubLinesObj constructor comment.
	 */
	public Canon_E193_TicketSubLinesObj() {
		super();
	}
	
	public String getInvoiceNumbers() {
		return invoiceNumbers;
	}

	public void setInvoiceNumbers(String invNumber) {
		this.invoiceNumbers = invNumber;
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
	 * Get catDesc.
	 * Creation date: Oct 24, 2005.
	 * @return String.
	 */
	
	public String getCatDesc() {
		return catDesc;
	}

	/**
	 * Set catDesc.
	 * Creation date: Oct 24, 2005.
	 * @param catDesc String.
	 */
	public void setCatDesc(String catDesc) {
		this.catDesc = catDesc;
	}

	/**
	 * Get parentCatId.
	 * Creation date: Oct 24, 2005.
	 * @return String.
	 */
	
	public String getParentCatId() {
		return parentCatId;
	}

	/**
	 * Set parentCatId.
	 * Creation date: Oct 24, 2005.
	 * @param parentCatId String.
	 */
	public void setParentCatId(String parentCatId) {
		this.parentCatId = parentCatId;
	}

	/**
	 * Get attribute1.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getAttribute1() {
		return attribute1;
	}

	/**
	 * Set attribute1.
	 * Creation date: Sep 28, 2005.
	 * @param newAttribute1 String.
	 */
	public void setAttribute1(String newAttribute1) {
		this.attribute1 = newAttribute1;
	}

	/**
	 * Get attribute2.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getAttribute2() {
		return attribute2;
	}

	/**
	 * Set attribute2.
	 * Creation date: Sep 28, 2005.
	 * @param newAttribute2 String.
	 */
	public void setAttribute2(String newAttribute2) {
		this.attribute2 = newAttribute2;
	}

	/**
	 * Get attribute3.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getAttribute3() {
		return attribute3;
	}

	/**
	 * Set attribute3.
	 * Creation date: Sep 28, 2005.
	 * @param newAttribute3 String.
	 */
	public void setAttribute3(String newAttribute3) {
		this.attribute3 = newAttribute3;
	}

	/**
	 * Get attribute4.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getAttribute4() {
		return attribute4;
	}

	/**
	 * Set attribute4.
	 * Creation date: Sep 28, 2005.
	 * @param newAttribute4 String.
	 */
	public void setAttribute4(String newAttribute4) {
		this.attribute4 = newAttribute4;
	}

	/**
	 * Get attribute5.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getAttribute5() {
		return attribute5;
	}

	/**
	 * Set attribute5.
	 * Creation date: Sep 28, 2005.
	 * @param newAttribute5 String.
	 */
	public void setAttribute5(String newAttribute5) {
		this.attribute5 = newAttribute5;
	}

	/**
	 * Get cancelEquipFlag.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getCancelEquipFlag() {
		return cancelEquipFlag;
	}

	/**
	 * Get taxExamption.
	 * Creation date: Sep 29, 2005.
	 * @return String.
	 */
	
	public String getTaxExamption() {
		return taxExamption;
	}
	/**
	 * Set taxExamption.
	 * Creation date: Sep 29, 2005.
	 * @param newTaxExamption String.
	 */
	public void setTaxExamption(String newTaxExamption) {
		this.taxExamption = newTaxExamption;
	}
	/**
	 * Set cancelEquipFlag.
	 * Creation date: Sep 28, 2005.
	 * @param newCancelEquipFlag String.
	 */
	public void setCancelEquipFlag(String newCancelEquipFlag) {
		this.cancelEquipFlag = newCancelEquipFlag;
	}

	/**
	 * Get catId.
	 * Creation date: Sep 28, 2005.
	 * @return int.
	 */
	
	public int getCatId() {
		return catId;
	}

	/**
	 * Set catId.
	 * Creation date: Sep 28, 2005.
	 * @param newCatId int.
	 */
	public void setCatId(int newCatId) {
		this.catId = newCatId;
	}

	/**
	 * Get companyMoveFlag.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getCompanyMoveFlag() {
		return companyMoveFlag;
	}

	/**
	 * Set companyMoveFlag.
	 * Creation date: Sep 28, 2005.
	 * @param newCompanyMoveFlag String.
	 */
	public void setCompanyMoveFlag(String newCompanyMoveFlag) {
		this.companyMoveFlag = newCompanyMoveFlag;
	}

	/**
	 * Get creditReason.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getCreditReason() {
		return creditReason;
	}

	/**
	 * Set creditReason.
	 * Creation date: Sep 28, 2005.
	 * @param newCreditReason String.
	 */
	public void setCreditReason(String newCreditReason) {
		this.creditReason = newCreditReason;
	}

	/**
	 * Get crFlag.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getCrFlag() {
		return crFlag;
	}

	/**
	 * Set crFlag.
	 * Creation date: Sep 28, 2005.
	 * @param newCrFlag String.
	 */
	public void setCrFlag(String newCrFlag) {
		this.crFlag = newCrFlag;
	}

	/**
	 * Get currentValue.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getCurrentValue() {
		return currentValue;
	}

	/**
	 * Set currentValue.
	 * Creation date: Sep 28, 2005.
	 * @param newCurrentValue String.
	 */
	public void setCurrentValue(String newCurrentValue) {
		this.currentValue = newCurrentValue;
	}

	/**
	 * Get lineId.
	 * Creation date: Sep 28, 2005.
	 * @return int.
	 */
	
	public int getLineId() {
		return lineId;
	}

	/**
	 * Set lineId.
	 * Creation date: Sep 28, 2005.
	 * @param newLineId int.
	 */
	public void setLineId(int newLineId) {
		this.lineId = newLineId;
	}

	/**
	 * Get newFlag.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getNewFlag() {
		return newFlag;
	}

	/**
	 * Set newFlag.
	 * Creation date: Sep 28, 2005.
	 * @param newNewFlag String.
	 */
	public void setNewFlag(String newNewFlag) {
		this.newFlag = newNewFlag;
	}

	/**
	 * Get newValue.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getNewValue() {
		return newValue;
	}

	/**
	 * Set newValue.
	 * Creation date: Sep 28, 2005.
	 * @param newNewValue String.
	 */
	public void setNewValue(String newNewValue) {
		this.newValue = newNewValue;
	}

	/**
	 * Get objectType.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getObjectType() {
		return objectType;
	}

	/**
	 * Set objectType.
	 * Creation date: Sep 28, 2005.
	 * @param newObjectType String.
	 */
	public void setObjectType(String newObjectType) {
		this.objectType = newObjectType;
	}

	/**
	 * Get objectValue.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getObjectValue() {
		return objectValue;
	}

	/**
	 * Set objectValue.
	 * Creation date: Sep 28, 2005.
	 * @param newObjectValue String.
	 */
	public void setObjectValue(String newObjectValue) {
		this.objectValue = newObjectValue;
	}

	/**
	 * Get serialNo.
	 * Creation date: Sep 28, 2005.
	 * @return String.
	 */
	
	public String getSerialNo() {
		return serialNo;
	}

	/**
	 * Set serialNo.
	 * Creation date: Sep 28, 2005.
	 * @param newSerialNo String.
	 */
	public void setSerialNo(String newSerialNo) {
		this.serialNo = newSerialNo;
	}

	/**
	 * Get productNumber.
	 * Creation date: Jan 29, 2013.
	 * @return String.
	 */
	
	public String getProductNumber() {
		return productNumber;
	}

	/**
	 * Set productNumber.
	 * Creation date: Jan 29, 2013.
	 * @param productNumber String.
	 */
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	/**
	 * Get fleetFlag.
	 * Creation date: Feb 04, 2013.
	 * @return String.
	 */
	
	public String getFleetFlag() {
		return fleetFlag;
	}

	/**
	 * Set fleetFlag.
	 * Creation date: Feb 04, 2013.
	 * @param fleetFlag String.
	 */
	public void setFleetFlag(String fleetFlag) {
		this.fleetFlag = fleetFlag;
	}	
	/**
	 * Get subLineId.
	 * Creation date: Sep 28, 2005.
	 * @return int.
	 */
	
	public int getSubLineId() {
		return subLineId;
	}

	/**
	 * Set subLineId.
	 * Creation date: Sep 28, 2005.
	 * @param newSubLineId int.
	 */
	public void setSubLineId(int newSubLineId) {
		this.subLineId = newSubLineId;
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
		subLineId = stream.readInt();
		lineId = stream.readInt();
		catId = stream.readInt();
		newFlag = stream.readString();
		crFlag = stream.readString();
		companyMoveFlag = stream.readString();
		cancelEquipFlag = stream.readString();
		taxExamption = stream.readString();
		creditReason = stream.readString();
		serialNo = stream.readString();
		objectType = stream.readString();
		objectValue = stream.readString();
		currentValue = stream.readString();
		newValue = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
		attribute4 = stream.readString();
		attribute5 = stream.readString();
		invoiceNumbers = stream.readString();
		productNumber = stream.readString();
    }
	
	public void writeSQL(java.sql.SQLOutput stream) throws SQLException {
		writeRecObjInt(stream, getSubLineId());
		writeRecObjInt(stream, getLineId());
		writeRecObjInt(stream, getCatId());
		writeRecObjString(stream, getNewFlag());
		writeRecObjString(stream, getCrFlag());
		writeRecObjString(stream, getCompanyMoveFlag());
		writeRecObjString(stream, getCancelEquipFlag());
		writeRecObjString(stream, getTaxExamption());
		writeRecObjString(stream, getCreditReason());
		writeRecObjString(stream, getSerialNo());
		writeRecObjString(stream, getObjectType());		
		writeRecObjString(stream, getObjectValue());
		writeRecObjString(stream, getCurrentValue());
		writeRecObjString(stream, getNewValue());
		writeRecObjString(stream, getAttribute1());
		writeRecObjString(stream, getAttribute2());
		writeRecObjString(stream, getAttribute3());
		writeRecObjString(stream, getAttribute4());
		writeRecObjString(stream, getAttribute5());
		writeRecObjString(stream, getInvoiceNumbers());
		writeRecObjString(stream, getProductNumber());		
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
			buffer.append("Canon_E193_TicketSubLinesObj[");
			buffer.append("serialVersionUID = ").append(serialVersionUID);
			buffer.append(", subLineId = ").append(subLineId);
			buffer.append(", lineId = ").append(lineId);
			buffer.append(", catId = ").append(catId);
			buffer.append(", newFlag = ").append(newFlag);
			buffer.append(", crFlag = ").append(crFlag);
			buffer.append(", companyMoveFlag = ").append(companyMoveFlag);
			buffer.append(", cancelEquipFlag = ").append(cancelEquipFlag);
			buffer.append(", taxExamption = ").append(taxExamption);
			buffer.append(", creditReason = ").append(creditReason);
			buffer.append(", serialNo = ").append(serialNo);
			buffer.append(", productNumber = ").append(productNumber);			
			buffer.append(", objectType = ").append(objectType);
			buffer.append(", objectValue = ").append(objectValue);
			buffer.append(", currentValue = ").append(currentValue);
			buffer.append(", newValue = ").append(newValue);
			buffer.append(", attribute1 = ").append(attribute1);
			buffer.append(", attribute2 = ").append(attribute2);
			buffer.append(", attribute3 = ").append(attribute3);
			buffer.append(", attribute4 = ").append(attribute4);
			buffer.append(", attribute5 = ").append(attribute5);
			buffer.append(", invoiceNumber = ").append(invoiceNumbers);
			buffer.append(", sqlTypeName = ").append(sqlTypeName);
			buffer.append("]");
			return buffer.toString();
		}
}

package com.canon.oracle.custapp.custinq.beans;

import java.io.Serializable;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;

/**
 * Title:		Canon_E193_TicketLinesObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (19-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Date   			By         				Description
 * ----   		---------- 			---------------------------------------------
 *01/30/2016	Mangala Shenoy		Modified for S21 Changes
 * </pre>
 */

public class Canon_E193_TicketLinesObj implements SQLData, Serializable {

	private static final long serialVersionUID = 1L;
	private int lineId;
	private int ticketId;
	private int catId;
	private String catCode;
	private String catIdDesc;
	private int parentCatId;
	private int lineNo;
	private String lineStatus;
	private String severity;
	private String reasonCd;
	private String reason;
	private String jtfNotesFlag;
	private String attribute1;
	private String attribute2;
	private String attribute3;
	private String attribute4;
	private String attribute5;
	private int orgId;
	private String createdBy;
	private String creationDate;
	private String lastUpdatedDate;
	private String lastUpdatedById;
	private String lastUpdatedByName;
	private String deptCode;
	private String deptName;
	private String roleId;
	private String roleCd;
	private String roleName;
	//Start Changes for S21 by Mangala
	//private int resourceId;
	private String resourceId;
	//End Changes for S21 by Mangala
	private String resourceName;
	private int noteId;
	private String notes;


	private String sqlTypeName;

	/**
	 * Canon_E193_TicketLinesObj constructor comment.
	 */
	public Canon_E193_TicketLinesObj() {
		super();
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
	public void setCreatedBy(String newCreatedBy) {
		this.createdBy = newCreatedBy;
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
	 * Get lastUpdatedDate.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * Set lastUpdatedDate.
	 * Creation date: Sep 19, 2005.
	 * @param newLastUpdatedDate java.lang.String.
	 */
	public void setLastUpdatedDate(String newLastUpdatedDate) {
		this.lastUpdatedDate = newLastUpdatedDate;
	}

	/**
	 * Get lineId.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getLineId() {
		return lineId;
	}

	/**
	 * Set lineId.
	 * Creation date: Sep 19, 2005.
	 * @param newLineId int.
	 */
	public void setLineId(int newLineId) {
		this.lineId = newLineId;
	}

	/**
	 * Get lineNo.
	 * Creation date: Sep 19, 2005.
	 * @return int.
	 */

	public int getLineNo() {
		return lineNo;
	}

	/**
	 * Set lineNo.
	 * Creation date: Sep 19, 2005.
	 * @param newLineNo int.
	 */
	public void setLineNo(int newLineNo) {
		this.lineNo = newLineNo;
	}

	/**
	 * Get lineStatus.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getLineStatus() {
		return lineStatus;
	}

	/**
	 * Set lineStatus.
	 * Creation date: Sep 19, 2005.
	 * @param newLineStatus java.lang.String.
	 */
	public void setLineStatus(String newLineStatus) {
		this.lineStatus = newLineStatus;
	}

	/**
	 * Get reason.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getReason() {
		return reason;
	}

	/**
	 * Set reason.
	 * Creation date: Sep 19, 2005.
	 * @param newReason java.lang.String.
	 */
	public void setReason(String newReason) {
		this.reason = newReason;
	}

	/**
	 * Get reasonCd.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getReasonCd() {
		return reasonCd;
	}

	/**
	 * Set reasonCd.
	 * Creation date: Sep 19, 2005.
	 * @param newReasonCd java.lang.String.
	 */
	public void setReasonCd(String newReasonCd) {
		this.reasonCd = newReasonCd;
	}

	/**
	 * Get severity.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getSeverity() {
		return severity;
	}

	/**
	 * Set severity.
	 * Creation date: Sep 19, 2005.
	 * @param newSeverity java.lang.String.
	 */
	public void setSeverity(String newSeverity) {
		this.severity = newSeverity;
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
	 * Get notes.
	 * Creation date: Sep 19, 2005.
	 * @return String.
	 */

	public String getNotes() {
		return notes;
	}

	/**
	 * Set notes.
	 * Creation date: Sep 19, 2005.
	 * @param newNotes String.
	 */
	public void setNotes(String newNotes) {
		this.notes = newNotes;
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
	 * Get deptCode.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getDeptCode() {
		return deptCode;
	}

	/**
	 * Set deptCode.
	 * Creation date: Sep 20, 2005.
	 * @param newDeptCode java.lang.String.
	 */
	public void setDeptCode(String newDeptCode) {
		this.deptCode = newDeptCode;
	}

	/**
	 * Get deptName.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getDeptName() {
		return deptName;
	}

	/**
	 * Set deptName.
	 * Creation date: Sep 20, 2005.
	 * @param newDeptName java.lang.String.
	 */
	public void setDeptName(String newDeptName) {
		this.deptName = newDeptName;
	}

	/**
	 * Get roleCd.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getRoleCd() {
		return roleCd;
	}

	/**
	 * Set roleCd.
	 * Creation date: Sep 20, 2005.
	 * @param newRoleCd java.lang.String.
	 */
	public void setRoleCd(String newRoleCd) {
		this.roleCd = newRoleCd;
	}

	/**
	 * Get roleId.
	 * Creation date: Sep 20, 2005.
	 * @return int.
	 */

	public String getRoleId() {
		return roleId;
	}

	/**
	 * Set roleId.
	 * Creation date: Sep 20, 2005.
	 * @param newRoleId int.
	 */
	public void setRoleId(String newRoleId) {
		this.roleId = newRoleId;
	}

	/**
	 * Get roleName.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getRoleName() {
		return roleName;
	}

	/**
	 * Set roleName.
	 * Creation date: Sep 20, 2005.
	 * @param newRoleName java.lang.String.
	 */
	public void setRoleName(String newRoleName) {
		this.roleName = newRoleName;
	}

	/**
	 * Get orgId.
	 * Creation date: Sep 20, 2005.
	 * @return int.
	 */

	public int getOrgId() {
		return orgId;
	}

	/**
	 * Set orgId.
	 * Creation date: Sep 20, 2005.
	 * @param newOrgId int.
	 */
	public void setOrgId(int newOrgId) {
		this.orgId = newOrgId;
	}

	/**
	 * Get resourceId.
	 * Creation date: Sep 20, 2005.
	 * @return int.
	 */
	//Start Changes for S21 by Mangala
	/*public int getResourceId() {
		return resourceId;
	}
	public void setResourceId(int newResourceId) {
	this.resourceId = newResourceId;
	}*/
	public String getResourceId() {
		return resourceId;
	}

	/**
	 * Set resourceId.
	 * Creation date: Sep 20, 2005.
	 * @param newResourceId int.
	 */
	public void setResourceId(String newResourceId) {
		this.resourceId = newResourceId;
	}
	//End Changes for S21 by Mangala
	/**
	 * Get resourceName.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getResourceName() {
		return resourceName;
	}

	/**
	 * Set resourceName.
	 * Creation date: Sep 20, 2005.
	 * @param newResourceName java.lang.String.
	 */
	public void setResourceName(String newResourceName) {
		this.resourceName = newResourceName;
	}

	/**
	 * Get noteId.
	 * Creation date: Sep 21, 2005.
	 * @return int.
	 */

	public int getNoteId() {
		return noteId;
	}

	/**
	 * Set noteId.
	 * Creation date: Sep 21, 2005.
	 * @param newNoteId int.
	 */
	public void setNoteId(int newNoteId) {
		this.noteId = newNoteId;
	}

	/**
	 * Get parentCatId.
	 * Creation date: Sep 21, 2005.
	 * @return int.
	 */

	public int getParentCatId() {
		return parentCatId;
	}

	/**
	 * Set parentCatId.
	 * Creation date: Sep 21, 2005.
	 * @param newParentCatId int.
	 */
	public void setParentCatId(int newParentCatId) {
		this.parentCatId = newParentCatId;
	}

	/**
	 * Get catCode.
	 * Creation date: Oct 3, 2005.
	 * @return String.
	 */

	public String getCatCode() {
		return catCode;
	}

	/**
	 * Set catCode.
	 * Creation date: Oct 3, 2005.
	 * @param newCatCode String.
	 */
	public void setCatCode(String newCatCode) {
		this.catCode = newCatCode;
	}

	/**
	 * Get lastUpdatedById.
	 * Creation date: Sep 20, 2005.
	 * @return int.
	 */

	public String getLastUpdatedById() {
		return lastUpdatedById;
	}

	/**
	 * Set lastUpdatedById.
	 * Creation date: Sep 20, 2005.
	 * @param newLastUpdatedById int.
	 */
	public void setLastUpdatedById(String newLastUpdatedById) {
		this.lastUpdatedById = newLastUpdatedById;
	}

	/**
	 * Get lastUpdatedByName.
	 * Creation date: Sep 20, 2005.
	 * @return String.
	 */

	public String getLastUpdatedByName() {
		return lastUpdatedByName;
	}

	/**
	 * Set lastUpdatedByName.
	 * Creation date: Sep 20, 2005.
	 * @param newLastUpdatedByName java.lang.String.
	 */
	public void setLastUpdatedByName(String newLastUpdatedByName) {
		this.lastUpdatedByName = newLastUpdatedByName;
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
		lineId = stream.readInt();
		ticketId = stream.readInt();
		catId = stream.readInt();
		lineNo = stream.readInt();
		lineStatus = stream.readString();
		severity = stream.readString();
		reasonCd = stream.readString();
		reason = stream.readString();
		jtfNotesFlag = stream.readString();
		attribute1 = stream.readString();
		attribute2 = stream.readString();
		attribute3 = stream.readString();
		attribute4 = stream.readString();
		attribute5 = stream.readString();
		createdBy = stream.readString();
		creationDate = stream.readString();
		lastUpdatedDate = stream.readString();
		catCode = stream.readString();
		catIdDesc = stream.readString();
		deptCode = stream.readString();
		deptName = stream.readString();
		resourceId = stream.readString();
		resourceName = stream.readString();
		roleId  = stream.readString();
		roleName = stream.readString();
		lastUpdatedById = stream.readString();
		lastUpdatedByName = stream.readString();
		noteId = stream.readInt();
		notes = stream.readString();

    }

	public void writeSQL(java.sql.SQLOutput stream) throws SQLException {
		writeRecObjInt(stream, getLineId());
		writeRecObjInt(stream, getTicketId());
		writeRecObjInt(stream, getCatId());
		writeRecObjInt(stream, getLineNo());
		writeRecObjString(stream, getLineStatus());
		writeRecObjString(stream, getSeverity());
		writeRecObjString(stream, getReasonCd());
		writeRecObjString(stream, getReason());
		writeRecObjString(stream, getJtfNotesFlag());
		writeRecObjString(stream, getAttribute1());
		writeRecObjString(stream, getAttribute2());
		writeRecObjString(stream, getAttribute3());
		writeRecObjString(stream, getAttribute4());
		writeRecObjString(stream, getAttribute5());
		writeRecObjString(stream, getCreatedBy());
		writeRecObjString(stream, getCreationDate());
		writeRecObjString(stream, getLastUpdatedDate());
		writeRecObjString(stream, getCatCode());
		writeRecObjString(stream, getCatIdDesc());
		writeRecObjString(stream, getDeptCode());
		writeRecObjString(stream, getDeptName());
		writeRecObjString(stream, getResourceId());
		writeRecObjString(stream, getResourceName());
		writeRecObjString(stream, getRoleId());
		writeRecObjString(stream, getRoleName());
		writeRecObjString(stream, getLastUpdatedById());
		writeRecObjString(stream, getLastUpdatedByName());
		writeRecObjInt(stream, getNoteId());
		writeRecObjString(stream, getNotes());
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
			buffer.append("Canon_E193_TicketLinesObj[");
			buffer.append("serialVersionUID = ").append(serialVersionUID);
			buffer.append(", lineId = ").append(lineId);
			buffer.append(", ticketId = ").append(ticketId);
			buffer.append(", catCode = ").append(catCode);
			buffer.append(", catId = ").append(catId);
			buffer.append(", lineNo = ").append(lineNo);
			buffer.append(", lineStatus = ").append(lineStatus);
			buffer.append(", severity = ").append(severity);
			buffer.append(", reasonCd = ").append(reasonCd);
			buffer.append(", reason = ").append(reason);
			buffer.append(", jtfNotesFlag = ").append(jtfNotesFlag);
			buffer.append(", attribute1 = ").append(attribute1);
			buffer.append(", attribute2 = ").append(attribute2);
			buffer.append(", attribute3 = ").append(attribute3);
			buffer.append(", attribute4 = ").append(attribute4);
			buffer.append(", attribute5 = ").append(attribute5);
			buffer.append(", createdBy = ").append(createdBy);
			buffer.append(", creationDate = ").append(creationDate);
			buffer.append(", lastUpdatedDate = ").append(lastUpdatedDate);
			buffer.append(", notes = ").append(notes);
			buffer.append(", orgId = ").append(orgId);
			buffer.append(", catIdDesc = ").append(catIdDesc);
			buffer.append(", deptCode = ").append(deptCode);
			buffer.append(", deptName = ").append(deptName);
			buffer.append(", roleId = ").append(roleId);
			buffer.append(", roleCd = ").append(roleCd);
			buffer.append(", roleName = ").append(roleName);
			buffer.append(", resourceId = ").append(resourceId);
			buffer.append(", resourceName = ").append(resourceName);
			buffer.append(", noteId = ").append(noteId);
			buffer.append(", parentCatId = ").append(parentCatId);
			buffer.append(", sqlTypeName = ").append(sqlTypeName);
			buffer.append("]");
			return buffer.toString();
		}
}

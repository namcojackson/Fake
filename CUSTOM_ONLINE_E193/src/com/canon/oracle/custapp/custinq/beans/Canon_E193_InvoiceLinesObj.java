package com.canon.oracle.custapp.custinq.beans;


/**
 * Title:		Canon_E193_InvoiceLinesObj<br>
 * Description:	bean classes.<br>
 * <p>
 * CopyRight:	Canon<br>
 * Company:		Canon<br>
 * @author:		Subba<br>
 * @version:	1.0, (13-Sep-2005)<br>
 * @modified by:<br>
 * @version:<br>
 * @modification Detail:<br>
 * <pre>
 * Flag  Date       By         Description
 * ----  ---------- ---------- ---------------------------------------------
 *
 * </pre>
 */

public class Canon_E193_InvoiceLinesObj {
	
	private int lineNo;
	private String itemNo;
	private String lineDesc;
	private double lineTotal;
	private int quantity;
	private String uom;
	private double unitPrice;
	private String custTrxId;
	private String custTrxLineId;
	
	private boolean chekcboxCheck;
	private String strReasonCd;
	private String strCreditQty;
	private String strCreditAmt;
	/**
	 * CCanon_E193_TicketHeaderObj constructor comment.
	 */

	public Canon_E193_InvoiceLinesObj() {
		super();
	}

	/**
	 * Get strReasonCd.
	 * Creation date: Oct 25, 2005.
	 * @return String.
	 */
	
	public String getStrReasonCd() {
		return strReasonCd;
	}

	/**
	 * Set strReasonCd.
	 * Creation date: Oct 25, 2005.
	 * @param newStrReasonCd String.
	 */
	public void setStrReasonCd(String newStrReasonCd) {
		this.strReasonCd = newStrReasonCd;
	}

	/**
	 * Get chekcboxCheck.
	 * Creation date: Oct 25, 2005.
	 * @return boolean.
	 */
	
	public boolean isChekcboxCheck() {
		return chekcboxCheck;
	}

	/**
	 * Set chekcboxCheck.
	 * Creation date: Oct 25, 2005.
	 * @param chekcboxCheck boolean.
	 */
	public void setChekcboxCheck(boolean chekcboxCheck) {
		this.chekcboxCheck = chekcboxCheck;
	}

	/**
	 * Get strCreditAmt.
	 * Creation date: Oct 25, 2005.
	 * @return String.
	 */
	
	public String getStrCreditAmt() {
		return strCreditAmt;
	}

	/**
	 * Set strCreditAmt.
	 * Creation date: Oct 25, 2005.
	 * @param strCreditAmt String.
	 */
	public void setStrCreditAmt(String strCreditAmt) {
		this.strCreditAmt = strCreditAmt;
	}

	/**
	 * Get strCreditQty.
	 * Creation date: Oct 25, 2005.
	 * @return String.
	 */
	
	public String getStrCreditQty() {
		return strCreditQty;
	}

	/**
	 * Set strCreditQty.
	 * Creation date: Oct 25, 2005.
	 * @param strCreditQty String.
	 */
	public void setStrCreditQty(String strCreditQty) {
		this.strCreditQty = strCreditQty;
	}

	/**
	 * Get itemNo.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return java.lang.String.
	 */
	public String getItemNo() {
		return itemNo;
	}

	/**
	 * Set itemNo.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newItemNo java.lang.String
	 */
	public void setItemNo(String newItemNo) {
		this.itemNo = newItemNo;
	}

	/**
	 * Get lineDesc.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return java.lang.String.
	 */
	public String getLineDesc() {
		return lineDesc;
	}

	/**
	 * Set lineDesc.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newLineDesc java.lang.String.
	 */
	public void setLineDesc(String newLineDesc) {
		this.lineDesc = newLineDesc;
	}

	/**
	 * Get lineNo.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return int.
	 */
	public int getLineNo() {
		return lineNo;
	}

	/**
	 * Set lineNo.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newLineNo int.
	 */
	public void setLineNo(int newLineNo) {
		this.lineNo = newLineNo;
	}

	/**
	 * Get lineTotal.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return double.
	 */
	public double getLineTotal() {
		return lineTotal;
	}

	/**
	 * Set lineTotal.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newLineTotal int.
	 */
	public void setLineTotal(double newLineTotal) {
		this.lineTotal = newLineTotal;
	}

	/**
	 * Get custTrxId.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return int.
	 */
	public String getCustTrxId() {
		return custTrxId;
	}

	/**
	 * Set custTrxId.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newCustTrxId int.
	 */
	public void setCustTrxId(String newCustTrxId) {
		this.custTrxId = newCustTrxId;
	}

	/**
	 * Get custTrxLineId.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return int.
	 */
	public String getCustTrxLineId() {
		return custTrxLineId;
	}

	/**
	 * Set custTrxLineId.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newCustTrxLineId int.
	 */
	public void setCustTrxLineId(String newCustTrxLineId) {
		this.custTrxLineId = newCustTrxLineId;
	}

	/**
	 * Get quantity.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return int.
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * Set quantity.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newQuantity int.
	 */
	public void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}

	/**
	 * Get unitPrice.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return double.
	 */
	public double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * Set unitPrice.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newUnitPrice double.
	 */
	public void setUnitPrice(double newUnitPrice) {
		this.unitPrice = newUnitPrice;
	}

	/**
	 * Get uom.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @return java.lang.String.
	 */
	public String getUom() {
		return uom;
	}

	/**
	 * Set uom.
	 * Creation date: (9/13/2005 4:30:45 PM)
	 * @param newUom java.lang.String.
	 */
	public void setUom(String newUom) {
		this.uom = newUom;
	}

	@Override
	public String toString() {
		return "Canon_E193_InvoiceLinesObj [lineNo=" + lineNo + ", itemNo="
				+ itemNo + ", lineDesc=" + lineDesc + ", lineTotal="
				+ lineTotal + ", quantity=" + quantity + ", uom=" + uom
				+ ", unitPrice=" + unitPrice + ", custTrxId=" + custTrxId
				+ ", custTrxLineId=" + custTrxLineId + ", chekcboxCheck="
				+ chekcboxCheck + ", strReasonCd=" + strReasonCd
				+ ", strCreditQty=" + strCreditQty + ", strCreditAmt="
				+ strCreditAmt + "]";
	}
	
	

}

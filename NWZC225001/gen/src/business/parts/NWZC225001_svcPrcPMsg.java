//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20210130090728000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC225001_svcPrcPMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWZC225001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC225001_svcPrcPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ORD_SRC_REF_NUM*/
	public final EZDPStringItem              ordSrcRefNum;

    /** DS_IMPT_SVC_LINE_NUM*/
	public final EZDPBigDecimalItem              dsImptSvcLineNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** ORD_SRC_REF_LINE_NUM*/
	public final EZDPStringItem              ordSrcRefLineNum;

    /** MDL_ID*/
	public final EZDPBigDecimalItem              mdlId;

    /** PRC_MTR_PKG_PK*/
	public final EZDPBigDecimalItem              prcMtrPkgPk;

    /** BASE_PRC_DEAL_AMT*/
	public final EZDPBigDecimalItem              basePrcDealAmt;

    /** PRC_TIER_SVC_OFFER_CD*/
	public final EZDPStringItem              prcTierSvcOfferCd;

    /** BILL_TO_LOC_NUM*/
	public final EZDPStringItem              billToLocNum;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;


	/**
	 * NWZC225001_svcPrcPMsg is constructor.
	 * The initialization when the instance of NWZC225001_svcPrcPMsg is generated.
	 */
	public NWZC225001_svcPrcPMsg() {
		this(false, -1);
	}

	/**
	 * NWZC225001_svcPrcPMsg is constructor.
	 * The initialization when the instance of NWZC225001_svcPrcPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC225001_svcPrcPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		ordSrcRefNum = (EZDPStringItem)newItem("ordSrcRefNum");
		dsImptSvcLineNum = (EZDPBigDecimalItem)newItem("dsImptSvcLineNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		ordSrcRefLineNum = (EZDPStringItem)newItem("ordSrcRefLineNum");
		mdlId = (EZDPBigDecimalItem)newItem("mdlId");
		prcMtrPkgPk = (EZDPBigDecimalItem)newItem("prcMtrPkgPk");
		basePrcDealAmt = (EZDPBigDecimalItem)newItem("basePrcDealAmt");
		prcTierSvcOfferCd = (EZDPStringItem)newItem("prcTierSvcOfferCd");
		billToLocNum = (EZDPStringItem)newItem("billToLocNum");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC225001_svcPrcPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC225001_svcPrcPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"ordSrcRefNum", "ordSrcRefNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"dsImptSvcLineNum", "dsImptSvcLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"ordSrcRefLineNum", "ordSrcRefLineNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"prcMtrPkgPk", "prcMtrPkgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"basePrcDealAmt", "basePrcDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTierSvcOfferCd", "prcTierSvcOfferCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"billToLocNum", "billToLocNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ORD_SRC_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefNum
        {"DS_IMPT_SVC_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptSvcLineNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"ORD_SRC_REF_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcRefLineNum
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd
        {"BILL_TO_LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToLocNum
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
	};

	/**
	 * get Array of common (basic) data.
	 * @return Array of common (basis) data
	 */
	protected String[][] getBaseContents() {
		return BASE_CONTENTS;
	}

	/**
	 * get Array of Display Field.
	 * @return Array of  Display  Fields
	 */
	protected String[][] getDispContents() {
		return DISP_CONTENTS;
	}

}


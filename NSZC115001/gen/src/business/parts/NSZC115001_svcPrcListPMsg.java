//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180823154148000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC115001_svcPrcListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC115001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC115001_svcPrcListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RQST_TP_CD*/
	public final EZDPStringItem              xxRqstTpCd;

    /** SHELL_LINE_NUM*/
	public final EZDPBigDecimalItem              shellLineNum;

    /** DS_ORD_POSN_NUM*/
	public final EZDPStringItem              dsOrdPosnNum;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** MDL_ID*/
	public final EZDPBigDecimalItem              mdlId;

    /** MAINT_PRC_CATG_CD*/
	public final EZDPStringItem              maintPrcCatgCd;

    /** PRC_MTR_PKG_PK*/
	public final EZDPBigDecimalItem              prcMtrPkgPk;

    /** BASE_PRC_DEAL_AMT*/
	public final EZDPBigDecimalItem              basePrcDealAmt;

    /** DEAL_PRC_LIST_PRC_AMT*/
	public final EZDPBigDecimalItem              dealPrcListPrcAmt;

    /** PRC_TIER_SVC_OFFER_CD*/
	public final EZDPStringItem              prcTierSvcOfferCd;

    /** CORP_ADV_PRC_FLG*/
	public final EZDPStringItem              corpAdvPrcFlg;

    /** BILL_TO_CUST_CD*/
	public final EZDPStringItem              billToCustCd;

    /** SELL_TO_CUST_CD*/
	public final EZDPStringItem              sellToCustCd;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;


	/**
	 * NSZC115001_svcPrcListPMsg is constructor.
	 * The initialization when the instance of NSZC115001_svcPrcListPMsg is generated.
	 */
	public NSZC115001_svcPrcListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC115001_svcPrcListPMsg is constructor.
	 * The initialization when the instance of NSZC115001_svcPrcListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC115001_svcPrcListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRqstTpCd = (EZDPStringItem)newItem("xxRqstTpCd");
		shellLineNum = (EZDPBigDecimalItem)newItem("shellLineNum");
		dsOrdPosnNum = (EZDPStringItem)newItem("dsOrdPosnNum");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		mdlId = (EZDPBigDecimalItem)newItem("mdlId");
		maintPrcCatgCd = (EZDPStringItem)newItem("maintPrcCatgCd");
		prcMtrPkgPk = (EZDPBigDecimalItem)newItem("prcMtrPkgPk");
		basePrcDealAmt = (EZDPBigDecimalItem)newItem("basePrcDealAmt");
		dealPrcListPrcAmt = (EZDPBigDecimalItem)newItem("dealPrcListPrcAmt");
		prcTierSvcOfferCd = (EZDPStringItem)newItem("prcTierSvcOfferCd");
		corpAdvPrcFlg = (EZDPStringItem)newItem("corpAdvPrcFlg");
		billToCustCd = (EZDPStringItem)newItem("billToCustCd");
		sellToCustCd = (EZDPStringItem)newItem("sellToCustCd");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC115001_svcPrcListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC115001_svcPrcListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRqstTpCd", "xxRqstTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"shellLineNum", "shellLineNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"mdlId", "mdlId", null, null, TYPE_SEISU_SYOSU, "22", "0"},
	{"maintPrcCatgCd", "maintPrcCatgCd", null, null, TYPE_HANKAKUEISU, "10", null},
	{"prcMtrPkgPk", "prcMtrPkgPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"basePrcDealAmt", "basePrcDealAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"dealPrcListPrcAmt", "dealPrcListPrcAmt", null, null, TYPE_SEISU_SYOSU, "19", "4"},
	{"prcTierSvcOfferCd", "prcTierSvcOfferCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"corpAdvPrcFlg", "corpAdvPrcFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"billToCustCd", "billToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"sellToCustCd", "sellToCustCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RQST_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRqstTpCd
        {"SHELL_LINE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shellLineNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"MDL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdlId
        {"MAINT_PRC_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maintPrcCatgCd
        {"PRC_MTR_PKG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcMtrPkgPk
        {"BASE_PRC_DEAL_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//basePrcDealAmt
        {"DEAL_PRC_LIST_PRC_AMT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dealPrcListPrcAmt
        {"PRC_TIER_SVC_OFFER_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//prcTierSvcOfferCd
        {"CORP_ADV_PRC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//corpAdvPrcFlg
        {"BILL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//billToCustCd
        {"SELL_TO_CUST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//sellToCustCd
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
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

//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220328112903000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC046001_xxDsContrCrCardPoListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC046001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC046001_xxDsContrCrCardPoListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_CONTR_CR_CARD_PO_PK*/
	public final EZDPBigDecimalItem              dsContrCrCardPoPk;

    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDPBigDecimalItem              dsContrBllgMtrPk;

    /** CR_CARD_CUST_REF_NUM*/
	public final EZDPStringItem              crCardCustRefNum;

    /** CR_CARD_EXPR_YR_MTH*/
	public final EZDPDateItem              crCardExprYrMth;

    /** CUST_PO_NUM*/
	public final EZDPStringItem              custPoNum;

    /** PO_DT*/
	public final EZDPDateItem              poDt;

    /** LEASE_CMPY_FLG*/
	public final EZDPStringItem              leaseCmpyFlg;

    /** DS_CONTR_MACH_LVL_NUM*/
	public final EZDPStringItem              dsContrMachLvlNum;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** BLLG_MTR_LB_CD*/
	public final EZDPStringItem              bllgMtrLbCd;

    /** XX_MSG_ID*/
	public final EZDPStringItem              xxMsgId;

    /** XX_DS_MULT_MSG_DPLY_TXT*/
	public final EZDPStringItem              xxDsMultMsgDplyTxt;


	/**
	 * NSZC046001_xxDsContrCrCardPoListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrCrCardPoListPMsg is generated.
	 */
	public NSZC046001_xxDsContrCrCardPoListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC046001_xxDsContrCrCardPoListPMsg is constructor.
	 * The initialization when the instance of NSZC046001_xxDsContrCrCardPoListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC046001_xxDsContrCrCardPoListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsContrCrCardPoPk = (EZDPBigDecimalItem)newItem("dsContrCrCardPoPk");
		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		dsContrBllgMtrPk = (EZDPBigDecimalItem)newItem("dsContrBllgMtrPk");
		crCardCustRefNum = (EZDPStringItem)newItem("crCardCustRefNum");
		crCardExprYrMth = (EZDPDateItem)newItem("crCardExprYrMth");
		custPoNum = (EZDPStringItem)newItem("custPoNum");
		poDt = (EZDPDateItem)newItem("poDt");
		leaseCmpyFlg = (EZDPStringItem)newItem("leaseCmpyFlg");
		dsContrMachLvlNum = (EZDPStringItem)newItem("dsContrMachLvlNum");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		bllgMtrLbCd = (EZDPStringItem)newItem("bllgMtrLbCd");
		xxMsgId = (EZDPStringItem)newItem("xxMsgId");
		xxDsMultMsgDplyTxt = (EZDPStringItem)newItem("xxDsMultMsgDplyTxt");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC046001_xxDsContrCrCardPoListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC046001_xxDsContrCrCardPoListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsContrCrCardPoPk", "dsContrCrCardPoPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"crCardCustRefNum", "crCardCustRefNum", null, null, TYPE_HANKAKUEISU, "40", null},
	{"crCardExprYrMth", "crCardExprYrMth", null, null, TYPE_NENTSUKI, "6", null},
	{"custPoNum", "custPoNum", null, null, TYPE_HANKAKUEISU, "35", null},
	{"poDt", "poDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"leaseCmpyFlg", "leaseCmpyFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrMachLvlNum", "dsContrMachLvlNum", null, null, TYPE_HANKAKUEISU, "1", null},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"xxMsgId", "xxMsgId", null, null, TYPE_HANKAKUEISU, "9", null},
	{"xxDsMultMsgDplyTxt", "xxDsMultMsgDplyTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_CONTR_CR_CARD_PO_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCrCardPoPk
        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"CR_CARD_CUST_REF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardCustRefNum
        {"CR_CARD_EXPR_YR_MTH",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//crCardExprYrMth
        {"CUST_PO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//custPoNum
        {"PO_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//poDt
        {"LEASE_CMPY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//leaseCmpyFlg
        {"DS_CONTR_MACH_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrMachLvlNum
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"XX_MSG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxMsgId
        {"XX_DS_MULT_MSG_DPLY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDsMultMsgDplyTxt
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

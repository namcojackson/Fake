//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20130319125906000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *ZZIL0110CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.ZZIL0110;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZIL0110 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class ZZIL0110CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** ITRL_INTFC_ID*/
	public final EZDCStringItem              itrlIntfcId;

    /** ITRL_INTFC_TRX_CONFIG_ID_PS*/
	public final EZDCStringItem              itrlIntfcTrxConfigId_PS;

    /** ITRL_INTFC_TRX_CONFIG_ID_PC*/
	public final EZDCStringItemArray              itrlIntfcTrxConfigId_PC;

    /** ITRL_INTFC_TRX_CONFIG_NM_PC*/
	public final EZDCStringItemArray              itrlIntfcTrxConfigNm_PC;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.ZZIL0110.ZZIL0110_ACMsgArray              A;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** XX_RSLT_FLG*/
	public final EZDCStringItem              xxRsltFlg;

    /** ITRL_INTFC_ID_B1*/
	public final EZDCStringItem              itrlIntfcId_B1;

    /** ITRL_INTFC_TRX_CONFIG_ID_BS*/
	public final EZDCStringItem              itrlIntfcTrxConfigId_BS;

    /** ITRL_INTFC_TRX_CONFIG_ID_BC*/
	public final EZDCStringItemArray              itrlIntfcTrxConfigId_BC;

    /** ITRL_INTFC_TRX_CONFIG_NM_BC*/
	public final EZDCStringItemArray              itrlIntfcTrxConfigNm_BC;

    /** ITRL_INTFC_TRX_TBL_ID_B1*/
	public final EZDCStringItem              itrlIntfcTrxTblId_B1;

    /** XX_PAGE_SHOW_FROM_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowFromNum_B;

    /** XX_PAGE_SHOW_TO_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowToNum_B;

    /** XX_PAGE_SHOW_OF_NUM_B*/
	public final EZDCBigDecimalItem              xxPageShowOfNum_B;

    /** B*/
	public final business.blap.ZZIL0110.ZZIL0110_BCMsgArray              B;

    /** XX_SORT_TBL_NM_B*/
	public final EZDCStringItem              xxSortTblNm_B;

    /** XX_SORT_ITEM_NM_B*/
	public final EZDCStringItem              xxSortItemNm_B;

    /** XX_SORT_ORD_BY_TXT_B*/
	public final EZDCStringItem              xxSortOrdByTxt_B;

    /** XX_RSLT_FLG_B*/
	public final EZDCStringItem              xxRsltFlg_B;


	/**
	 * ZZIL0110CMsg is constructor.
	 * The initialization when the instance of ZZIL0110CMsg is generated.
	 */
	public ZZIL0110CMsg() {
		this(false, -1);
	}

	/**
	 * ZZIL0110CMsg is constructor.
	 * The initialization when the instance of ZZIL0110CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZIL0110CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		itrlIntfcId = (EZDCStringItem)newItem("itrlIntfcId");
		itrlIntfcTrxConfigId_PS = (EZDCStringItem)newItem("itrlIntfcTrxConfigId_PS");
		itrlIntfcTrxConfigId_PC = (EZDCStringItemArray)newItemArray("itrlIntfcTrxConfigId_PC");
		itrlIntfcTrxConfigNm_PC = (EZDCStringItemArray)newItemArray("itrlIntfcTrxConfigNm_PC");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.ZZIL0110.ZZIL0110_ACMsgArray)newMsgArray("A");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		xxRsltFlg = (EZDCStringItem)newItem("xxRsltFlg");
		itrlIntfcId_B1 = (EZDCStringItem)newItem("itrlIntfcId_B1");
		itrlIntfcTrxConfigId_BS = (EZDCStringItem)newItem("itrlIntfcTrxConfigId_BS");
		itrlIntfcTrxConfigId_BC = (EZDCStringItemArray)newItemArray("itrlIntfcTrxConfigId_BC");
		itrlIntfcTrxConfigNm_BC = (EZDCStringItemArray)newItemArray("itrlIntfcTrxConfigNm_BC");
		itrlIntfcTrxTblId_B1 = (EZDCStringItem)newItem("itrlIntfcTrxTblId_B1");
		xxPageShowFromNum_B = (EZDCBigDecimalItem)newItem("xxPageShowFromNum_B");
		xxPageShowToNum_B = (EZDCBigDecimalItem)newItem("xxPageShowToNum_B");
		xxPageShowOfNum_B = (EZDCBigDecimalItem)newItem("xxPageShowOfNum_B");
		B = (business.blap.ZZIL0110.ZZIL0110_BCMsgArray)newMsgArray("B");
		xxSortTblNm_B = (EZDCStringItem)newItem("xxSortTblNm_B");
		xxSortItemNm_B = (EZDCStringItem)newItem("xxSortItemNm_B");
		xxSortOrdByTxt_B = (EZDCStringItem)newItem("xxSortOrdByTxt_B");
		xxRsltFlg_B = (EZDCStringItem)newItem("xxRsltFlg_B");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZIL0110CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZIL0110CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"itrlIntfcId", "itrlIntfcId", null, null, TYPE_HANKAKUEISU, "30", null},
	{"itrlIntfcTrxConfigId_PS", "itrlIntfcTrxConfigId_PS", "PS", null, TYPE_HANKAKUEISU, "24", null},
	{"itrlIntfcTrxConfigId_PC", "itrlIntfcTrxConfigId_PC", "PC", "50", TYPE_HANKAKUEISU, "24", null},
	{"itrlIntfcTrxConfigNm_PC", "itrlIntfcTrxConfigNm_PC", "PC", "50", TYPE_HANKAKUEISU, "100", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "40", "business.blap.ZZIL0110.ZZIL0110_ACMsgArray", null, null},
	
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"xxRsltFlg", "xxRsltFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"itrlIntfcId_B1", "itrlIntfcId_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"itrlIntfcTrxConfigId_BS", "itrlIntfcTrxConfigId_BS", "BS", null, TYPE_HANKAKUEISU, "24", null},
	{"itrlIntfcTrxConfigId_BC", "itrlIntfcTrxConfigId_BC", "BC", "5", TYPE_HANKAKUEISU, "24", null},
	{"itrlIntfcTrxConfigNm_BC", "itrlIntfcTrxConfigNm_BC", "BC", "5", TYPE_HANKAKUEISU, "100", null},
	{"itrlIntfcTrxTblId_B1", "itrlIntfcTrxTblId_B1", "B1", null, TYPE_HANKAKUEISU, "28", null},
	{"xxPageShowFromNum_B", "xxPageShowFromNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum_B", "xxPageShowToNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum_B", "xxPageShowOfNum_B", "B", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"B", "B", null, "40", "business.blap.ZZIL0110.ZZIL0110_BCMsgArray", null, null},
	
	{"xxSortTblNm_B", "xxSortTblNm_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm_B", "xxSortItemNm_B", "B", null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt_B", "xxSortOrdByTxt_B", "B", null, TYPE_HANKAKUEISU, "4", null},
	{"xxRsltFlg_B", "xxRsltFlg_B", "B", null, TYPE_HANKAKUEISU, "1", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId
        {"ITRL_INTFC_TRX_CONFIG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigId_PS
        {"ITRL_INTFC_TRX_CONFIG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigId_PC
        {"ITRL_INTFC_TRX_CONFIG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigNm_PC
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg
        {"ITRL_INTFC_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcId_B1
        {"ITRL_INTFC_TRX_CONFIG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigId_BS
        {"ITRL_INTFC_TRX_CONFIG_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigId_BC
        {"ITRL_INTFC_TRX_CONFIG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxConfigNm_BC
        {"ITRL_INTFC_TRX_TBL_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//itrlIntfcTrxTblId_B1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum_B
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum_B
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum_B
		null,	//B
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm_B
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm_B
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt_B
        {"XX_RSLT_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRsltFlg_B
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


//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20180314025107000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL0240BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL0240;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL0240 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL0240BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FILE_DATA*/
	public final EZDBMimeSourceItem              xxFileData;

    /** MDSE_ITEM_TP_CD*/
	public final EZDBStringItem              mdseItemTpCd;

    /** MDSE_ITEM_TP_CD_L*/
	public final EZDBStringItemArray              mdseItemTpCd_L;

    /** MDSE_ITEM_TP_DESC_TXT_L*/
	public final EZDBStringItemArray              mdseItemTpDescTxt_L;

    /** MDSE_ITEM_TP_DESC_TXT*/
	public final EZDBStringItem              mdseItemTpDescTxt;

    /** MDSE_CD*/
	public final EZDBStringItem              mdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDBStringItem              mdseDescShortTxt;

    /** COA_MDSE_TP_CD*/
	public final EZDBStringItem              coaMdseTpCd;

    /** COA_MDSE_TP_CD_L*/
	public final EZDBStringItemArray              coaMdseTpCd_L;

    /** COA_PROJ_DESC_TXT_L*/
	public final EZDBStringItemArray              coaProjDescTxt_L;

    /** COA_PROJ_DESC_TXT*/
	public final EZDBStringItem              coaProjDescTxt;

    /** XX_SCR_ITEM_61_TXT*/
	public final EZDBStringItem              xxScrItem61Txt;

    /** ACTV_FLG*/
	public final EZDBStringItem              actvFlg;

    /** XX_CHK_BOX*/
	public final EZDBStringItem              xxChkBox;

    /** CMPSN_REVN_NUM_A*/
	public final EZDBBigDecimalItem              cmpsnRevnNum_A;

    /** CMPSN_REVN_CMNT_TXT_A*/
	public final EZDBStringItem              cmpsnRevnCmntTxt_A;

    /** EFF_FROM_DT_A*/
	public final EZDBDateItem              effFromDt_A;

    /** EFF_THRU_DT_A*/
	public final EZDBDateItem              effThruDt_A;

    /** EFF_FROM_DT_C*/
	public final EZDBDateItem              effFromDt_C;

    /** XX_CELL_IDX_A*/
	public final EZDBBigDecimalItem              xxCellIdx_A;

    /** A*/
	public final business.servlet.NMAL0240.NMAL0240_ABMsgArray              A;

    /** CMPSN_REVN_NUM_B*/
	public final EZDBBigDecimalItem              cmpsnRevnNum_B;

    /** CMPSN_REVN_CMNT_TXT_B*/
	public final EZDBStringItem              cmpsnRevnCmntTxt_B;

    /** EFF_FROM_DT_B*/
	public final EZDBDateItem              effFromDt_B;

    /** EFF_THRU_DT_B*/
	public final EZDBDateItem              effThruDt_B;

    /** XX_CELL_IDX_B*/
	public final EZDBBigDecimalItem              xxCellIdx_B;

    /** B*/
	public final business.servlet.NMAL0240.NMAL0240_BBMsgArray              B;

    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** P*/
	public final business.servlet.NMAL0240.NMAL0240_PBMsgArray              P;

    /** C*/
	public final business.servlet.NMAL0240.NMAL0240_CBMsgArray              C;

    /** O*/
	public final business.servlet.NMAL0240.NMAL0240_OBMsgArray              O;

    /** R*/
	public final business.servlet.NMAL0240.NMAL0240_RBMsgArray              R;


	/**
	 * NMAL0240BMsg is constructor.
	 * The initialization when the instance of NMAL0240BMsg is generated.
	 */
	public NMAL0240BMsg() {
		this(false, -1);
	}

	/**
	 * NMAL0240BMsg is constructor.
	 * The initialization when the instance of NMAL0240BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL0240BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFileData = (EZDBMimeSourceItem)newItem("xxFileData");
		mdseItemTpCd = (EZDBStringItem)newItem("mdseItemTpCd");
		mdseItemTpCd_L = (EZDBStringItemArray)newItemArray("mdseItemTpCd_L");
		mdseItemTpDescTxt_L = (EZDBStringItemArray)newItemArray("mdseItemTpDescTxt_L");
		mdseItemTpDescTxt = (EZDBStringItem)newItem("mdseItemTpDescTxt");
		mdseCd = (EZDBStringItem)newItem("mdseCd");
		mdseDescShortTxt = (EZDBStringItem)newItem("mdseDescShortTxt");
		coaMdseTpCd = (EZDBStringItem)newItem("coaMdseTpCd");
		coaMdseTpCd_L = (EZDBStringItemArray)newItemArray("coaMdseTpCd_L");
		coaProjDescTxt_L = (EZDBStringItemArray)newItemArray("coaProjDescTxt_L");
		coaProjDescTxt = (EZDBStringItem)newItem("coaProjDescTxt");
		xxScrItem61Txt = (EZDBStringItem)newItem("xxScrItem61Txt");
		actvFlg = (EZDBStringItem)newItem("actvFlg");
		xxChkBox = (EZDBStringItem)newItem("xxChkBox");
		cmpsnRevnNum_A = (EZDBBigDecimalItem)newItem("cmpsnRevnNum_A");
		cmpsnRevnCmntTxt_A = (EZDBStringItem)newItem("cmpsnRevnCmntTxt_A");
		effFromDt_A = (EZDBDateItem)newItem("effFromDt_A");
		effThruDt_A = (EZDBDateItem)newItem("effThruDt_A");
		effFromDt_C = (EZDBDateItem)newItem("effFromDt_C");
		xxCellIdx_A = (EZDBBigDecimalItem)newItem("xxCellIdx_A");
		A = (business.servlet.NMAL0240.NMAL0240_ABMsgArray)newMsgArray("A");
		cmpsnRevnNum_B = (EZDBBigDecimalItem)newItem("cmpsnRevnNum_B");
		cmpsnRevnCmntTxt_B = (EZDBStringItem)newItem("cmpsnRevnCmntTxt_B");
		effFromDt_B = (EZDBDateItem)newItem("effFromDt_B");
		effThruDt_B = (EZDBDateItem)newItem("effThruDt_B");
		xxCellIdx_B = (EZDBBigDecimalItem)newItem("xxCellIdx_B");
		B = (business.servlet.NMAL0240.NMAL0240_BBMsgArray)newMsgArray("B");
		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		P = (business.servlet.NMAL0240.NMAL0240_PBMsgArray)newMsgArray("P");
		C = (business.servlet.NMAL0240.NMAL0240_CBMsgArray)newMsgArray("C");
		O = (business.servlet.NMAL0240.NMAL0240_OBMsgArray)newMsgArray("O");
		R = (business.servlet.NMAL0240.NMAL0240_RBMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL0240BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL0240BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"mdseItemTpCd", "mdseItemTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpCd_L", "mdseItemTpCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"mdseItemTpDescTxt_L", "mdseItemTpDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"mdseItemTpDescTxt", "mdseItemTpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"mdseCd", "mdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"coaMdseTpCd", "coaMdseTpCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"coaMdseTpCd_L", "coaMdseTpCd_L", "L", "99", TYPE_HANKAKUEISU, "2", null},
	{"coaProjDescTxt_L", "coaProjDescTxt_L", "L", "99", TYPE_HANKAKUEISU, "50", null},
	{"coaProjDescTxt", "coaProjDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxScrItem61Txt", "xxScrItem61Txt", null, null, TYPE_HANKAKUEISU, "61", null},
	{"actvFlg", "actvFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxChkBox", "xxChkBox", null, null, TYPE_HANKAKUEIJI, "1", null},
	{"cmpsnRevnNum_A", "cmpsnRevnNum_A", "A", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnRevnCmntTxt_A", "cmpsnRevnCmntTxt_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"effFromDt_A", "effFromDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_A", "effThruDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_C", "effFromDt_C", "C", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCellIdx_A", "xxCellIdx_A", "A", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"A", "A", null, "200", "business.servlet.NMAL0240.NMAL0240_ABMsgArray", null, null},
	
	{"cmpsnRevnNum_B", "cmpsnRevnNum_B", "B", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"cmpsnRevnCmntTxt_B", "cmpsnRevnCmntTxt_B", "B", null, TYPE_HANKAKUEISU, "90", null},
	{"effFromDt_B", "effFromDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_B", "effThruDt_B", "B", null, TYPE_NENTSUKIHI, "8", null},
	{"xxCellIdx_B", "xxCellIdx_B", "B", null, TYPE_SEISU_SYOSU, "4", "0"},
	{"B", "B", null, "200", "business.servlet.NMAL0240.NMAL0240_BBMsgArray", null, null},
	
	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"P", "P", null, "99", "business.servlet.NMAL0240.NMAL0240_PBMsgArray", null, null},
	
	{"C", "C", null, "2", "business.servlet.NMAL0240.NMAL0240_CBMsgArray", null, null},
	
	{"O", "O", null, "30", "business.servlet.NMAL0240.NMAL0240_OBMsgArray", null, null},
	
	{"R", "R", null, "15", "business.servlet.NMAL0240.NMAL0240_RBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FILE_DATA",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd
        {"MDSE_ITEM_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpCd_L
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt_L
        {"MDSE_ITEM_TP_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseItemTpDescTxt
        {"MDSE_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd
        {"COA_MDSE_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaMdseTpCd_L
        {"COA_PROJ_DESC_TXT",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt_L
        {"COA_PROJ_DESC_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//coaProjDescTxt
        {"XX_SCR_ITEM_61_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrItem61Txt
        {"ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actvFlg
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox
        {"CMPSN_REVN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnRevnNum_A
        {"CMPSN_REVN_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnRevnCmntTxt_A
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_A
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_A
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_C
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx_A
		null,	//A
        {"CMPSN_REVN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnRevnNum_B
        {"CMPSN_REVN_CMNT_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cmpsnRevnCmntTxt_B
        {"EFF_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_B
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_B
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx_B
		null,	//B
        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
		null,	//P
		null,	//C
		null,	//O
		null,	//R
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


//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20230627111118000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NWAL1600BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NWAL1600;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NWAL1600 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NWAL1600BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_MODE_CD*/
	public final EZDBStringItem              xxModeCd;

    /** CPO_ORD_NUM*/
	public final EZDBStringItem              cpoOrdNum;

    /** CPO_ORD_NUM_BK*/
	public final EZDBStringItem              cpoOrdNum_BK;

    /** DS_ORD_POSN_NUM*/
	public final EZDBStringItem              dsOrdPosnNum;

    /** DS_ORD_POSN_NUM_BK*/
	public final EZDBStringItem              dsOrdPosnNum_BK;

    /** XX_COMN_SCR_COL_VAL_TXT*/
	public final EZDBStringItem              xxComnScrColValTxt;

    /** XX_COMN_SCR_COL_VAL_TXT_BK*/
	public final EZDBStringItem              xxComnScrColValTxt_BK;

    /** XX_DEAL_SLS_PCT*/
	public final EZDBBigDecimalItem              xxDealSlsPct;

    /** XX_LAST_BTN_NM*/
	public final EZDBStringItem              xxLastBtnNm;

    /** XX_SFX_KEY_TXT*/
	public final EZDBStringItem              xxSfxKeyTxt;

    /** XX_ERR_FLG*/
	public final EZDBStringItem              xxErrFlg;

    /** XX_COND_CD*/
	public final EZDBStringItem              xxCondCd;

    /** XX_CELL_IDX*/
	public final EZDBBigDecimalItem              xxCellIdx;

    /** XX_SCR_EVENT_NM*/
	public final EZDBStringItem              xxScrEventNm;

    /** XX_DTL_CNT*/
	public final EZDBBigDecimalItem              xxDtlCnt;

    /** A*/
	public final business.servlet.NWAL1600.NWAL1600_ABMsgArray              A;

    /** B*/
	public final business.servlet.NWAL1600.NWAL1600_BBMsgArray              B;

    /** C*/
	public final business.servlet.NWAL1600.NWAL1600_CBMsgArray              C;

    /** O*/
	public final business.servlet.NWAL1600.NWAL1600_OBMsgArray              O;

    /** P*/
	public final business.servlet.NWAL1600.NWAL1600_PBMsgArray              P;

    /** S*/
	public final business.servlet.NWAL1600.NWAL1600_SBMsgArray              S;


	/**
	 * NWAL1600BMsg is constructor.
	 * The initialization when the instance of NWAL1600BMsg is generated.
	 */
	public NWAL1600BMsg() {
		this(false, -1);
	}

	/**
	 * NWAL1600BMsg is constructor.
	 * The initialization when the instance of NWAL1600BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWAL1600BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxModeCd = (EZDBStringItem)newItem("xxModeCd");
		cpoOrdNum = (EZDBStringItem)newItem("cpoOrdNum");
		cpoOrdNum_BK = (EZDBStringItem)newItem("cpoOrdNum_BK");
		dsOrdPosnNum = (EZDBStringItem)newItem("dsOrdPosnNum");
		dsOrdPosnNum_BK = (EZDBStringItem)newItem("dsOrdPosnNum_BK");
		xxComnScrColValTxt = (EZDBStringItem)newItem("xxComnScrColValTxt");
		xxComnScrColValTxt_BK = (EZDBStringItem)newItem("xxComnScrColValTxt_BK");
		xxDealSlsPct = (EZDBBigDecimalItem)newItem("xxDealSlsPct");
		xxLastBtnNm = (EZDBStringItem)newItem("xxLastBtnNm");
		xxSfxKeyTxt = (EZDBStringItem)newItem("xxSfxKeyTxt");
		xxErrFlg = (EZDBStringItem)newItem("xxErrFlg");
		xxCondCd = (EZDBStringItem)newItem("xxCondCd");
		xxCellIdx = (EZDBBigDecimalItem)newItem("xxCellIdx");
		xxScrEventNm = (EZDBStringItem)newItem("xxScrEventNm");
		xxDtlCnt = (EZDBBigDecimalItem)newItem("xxDtlCnt");
		A = (business.servlet.NWAL1600.NWAL1600_ABMsgArray)newMsgArray("A");
		B = (business.servlet.NWAL1600.NWAL1600_BBMsgArray)newMsgArray("B");
		C = (business.servlet.NWAL1600.NWAL1600_CBMsgArray)newMsgArray("C");
		O = (business.servlet.NWAL1600.NWAL1600_OBMsgArray)newMsgArray("O");
		P = (business.servlet.NWAL1600.NWAL1600_PBMsgArray)newMsgArray("P");
		S = (business.servlet.NWAL1600.NWAL1600_SBMsgArray)newMsgArray("S");
	}

	/**
	 * get the type of array which is stored
	 * @return NWAL1600BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWAL1600BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"cpoOrdNum_BK", "cpoOrdNum_BK", "BK", null, TYPE_HANKAKUEISU, "8", null},
	{"dsOrdPosnNum", "dsOrdPosnNum", null, null, TYPE_HANKAKUEISU, "6", null},
	{"dsOrdPosnNum_BK", "dsOrdPosnNum_BK", "BK", null, TYPE_HANKAKUEISU, "6", null},
	{"xxComnScrColValTxt", "xxComnScrColValTxt", null, null, TYPE_HANKAKUEISU, "4000", null},
	{"xxComnScrColValTxt_BK", "xxComnScrColValTxt_BK", "BK", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxDealSlsPct", "xxDealSlsPct", null, null, TYPE_SEISU_SYOSU, "9", "5"},
	{"xxLastBtnNm", "xxLastBtnNm", null, null, TYPE_HANKAKUEISU, "13", null},
	{"xxSfxKeyTxt", "xxSfxKeyTxt", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxErrFlg", "xxErrFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxCondCd", "xxCondCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"xxCellIdx", "xxCellIdx", null, null, TYPE_SEISU_SYOSU, "4", "0"},
	{"xxScrEventNm", "xxScrEventNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxDtlCnt", "xxDtlCnt", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "100", "business.servlet.NWAL1600.NWAL1600_ABMsgArray", null, null},
	
	{"B", "B", null, "100", "business.servlet.NWAL1600.NWAL1600_BBMsgArray", null, null},
	
	{"C", "C", null, "200", "business.servlet.NWAL1600.NWAL1600_CBMsgArray", null, null},
	
	{"O", "O", null, "200", "business.servlet.NWAL1600.NWAL1600_OBMsgArray", null, null},
	
	{"P", "P", null, "15", "business.servlet.NWAL1600.NWAL1600_PBMsgArray", null, null},
	
	{"S", "S", null, "10", "business.servlet.NWAL1600.NWAL1600_SBMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_MODE_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"CPO_ORD_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum_BK
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum
        {"DS_ORD_POSN_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsOrdPosnNum_BK
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt
        {"XX_COMN_SCR_COL_VAL_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnScrColValTxt_BK
        {"XX_DEAL_SLS_PCT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDealSlsPct
        {"XX_LAST_BTN_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLastBtnNm
        {"XX_SFX_KEY_TXT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSfxKeyTxt
        {"XX_ERR_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxErrFlg
        {"XX_COND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCondCd
        {"XX_CELL_IDX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCellIdx
        {"XX_SCR_EVENT_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrEventNm
        {"XX_DTL_CNT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCnt
		null,	//A
		null,	//B
		null,	//C
		null,	//O
		null,	//P
		null,	//S
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


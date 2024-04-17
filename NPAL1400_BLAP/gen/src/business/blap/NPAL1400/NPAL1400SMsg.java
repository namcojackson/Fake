//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20161219012222000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NPAL1400SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NPAL1400;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NPAL1400 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NPAL1400SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;

    /** SRCH_OPT_PK_PD*/
	public final EZDSBigDecimalItemArray              srchOptPk_PD;

    /** SRCH_OPT_NM_PD*/
	public final EZDSStringItemArray              srchOptNm_PD;

    /** SRCH_OPT_PK_SL*/
	public final EZDSBigDecimalItem              srchOptPk_SL;

    /** SRCH_OPT_NM_TX*/
	public final EZDSStringItem              srchOptNm_TX;

    /** SRCH_OPT_USR_ID_U1*/
	public final EZDSStringItem              srchOptUsrId_U1;

    /** RTL_WH_CD*/
	public final EZDSStringItem              rtlWhCd;

    /** RTL_WH_NM*/
	public final EZDSStringItem              rtlWhNm;

    /** RTL_SWH_CD*/
	public final EZDSStringItem              rtlSwhCd;

    /** RTL_SWH_NM*/
	public final EZDSStringItem              rtlSwhNm;

    /** RMNF_ORD_NUM*/
	public final EZDSStringItem              rmnfOrdNum;

    /** RMNF_ORD_STS_CD_PD*/
	public final EZDSStringItemArray              rmnfOrdStsCd_PD;

    /** RMNF_ORD_STS_NM_PD*/
	public final EZDSStringItemArray              rmnfOrdStsNm_PD;

    /** RMNF_ORD_STS_CD_SL*/
	public final EZDSStringItem              rmnfOrdStsCd_SL;

    /** RMNF_START_DT_FR*/
	public final EZDSDateItem              rmnfStartDt_FR;

    /** RMNF_START_DT_TO*/
	public final EZDSDateItem              rmnfStartDt_TO;

    /** RMNF_MAIN_UNIT_SER_NUM*/
	public final EZDSStringItem              rmnfMainUnitSerNum;

    /** T_MDL_NM*/
	public final EZDSStringItem              t_MdlNm;

    /** RMNF_END_DT_FR*/
	public final EZDSDateItem              rmnfEndDt_FR;

    /** RMNF_END_DT_TO*/
	public final EZDSDateItem              rmnfEndDt_TO;

    /** RMNF_MAIN_UNIT_MDSE_CD*/
	public final EZDSStringItem              rmnfMainUnitMdseCd;

    /** MDSE_DESC_SHORT_TXT*/
	public final EZDSStringItem              mdseDescShortTxt;

    /** TECH_TOC_CD*/
	public final EZDSStringItem              techTocCd;

    /** TECH_NM*/
	public final EZDSStringItem              techNm;

    /** XX_COMN_COL_ORD_TXT*/
	public final EZDSStringItem              xxComnColOrdTxt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NPAL1400.NPAL1400_ASMsgArray              A;

    /** XX_POP_PRM_EV*/
	public final EZDSStringItem              xxPopPrm_EV;

    /** XX_POP_PRM_P0*/
	public final EZDSStringItem              xxPopPrm_P0;

    /** XX_POP_PRM_P1*/
	public final EZDSStringItem              xxPopPrm_P1;

    /** XX_POP_PRM_P2*/
	public final EZDSStringItem              xxPopPrm_P2;

    /** XX_POP_PRM_P3*/
	public final EZDSStringItem              xxPopPrm_P3;

    /** XX_POP_PRM_P4*/
	public final EZDSStringItem              xxPopPrm_P4;

    /** XX_POP_PRM_P5*/
	public final EZDSStringItem              xxPopPrm_P5;

    /** XX_POP_PRM_P6*/
	public final EZDSStringItem              xxPopPrm_P6;

    /** XX_POP_PRM_P7*/
	public final EZDSStringItem              xxPopPrm_P7;

    /** XX_POP_PRM_P8*/
	public final EZDSStringItem              xxPopPrm_P8;

    /** XX_POP_PRM_P9*/
	public final EZDSStringItem              xxPopPrm_P9;

    /** XX_POP_PRM_PA*/
	public final EZDSStringItem              xxPopPrm_PA;

    /** R*/
	public final business.blap.NPAL1400.NPAL1400_RSMsgArray              R;


	/**
	 * NPAL1400SMsg is constructor.
	 * The initialization when the instance of NPAL1400SMsg is generated.
	 */
	public NPAL1400SMsg() {
		this(false, -1);
	}

	/**
	 * NPAL1400SMsg is constructor.
	 * The initialization when the instance of NPAL1400SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NPAL1400SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
		srchOptPk_PD = (EZDSBigDecimalItemArray)newItemArray("srchOptPk_PD");
		srchOptNm_PD = (EZDSStringItemArray)newItemArray("srchOptNm_PD");
		srchOptPk_SL = (EZDSBigDecimalItem)newItem("srchOptPk_SL");
		srchOptNm_TX = (EZDSStringItem)newItem("srchOptNm_TX");
		srchOptUsrId_U1 = (EZDSStringItem)newItem("srchOptUsrId_U1");
		rtlWhCd = (EZDSStringItem)newItem("rtlWhCd");
		rtlWhNm = (EZDSStringItem)newItem("rtlWhNm");
		rtlSwhCd = (EZDSStringItem)newItem("rtlSwhCd");
		rtlSwhNm = (EZDSStringItem)newItem("rtlSwhNm");
		rmnfOrdNum = (EZDSStringItem)newItem("rmnfOrdNum");
		rmnfOrdStsCd_PD = (EZDSStringItemArray)newItemArray("rmnfOrdStsCd_PD");
		rmnfOrdStsNm_PD = (EZDSStringItemArray)newItemArray("rmnfOrdStsNm_PD");
		rmnfOrdStsCd_SL = (EZDSStringItem)newItem("rmnfOrdStsCd_SL");
		rmnfStartDt_FR = (EZDSDateItem)newItem("rmnfStartDt_FR");
		rmnfStartDt_TO = (EZDSDateItem)newItem("rmnfStartDt_TO");
		rmnfMainUnitSerNum = (EZDSStringItem)newItem("rmnfMainUnitSerNum");
		t_MdlNm = (EZDSStringItem)newItem("t_MdlNm");
		rmnfEndDt_FR = (EZDSDateItem)newItem("rmnfEndDt_FR");
		rmnfEndDt_TO = (EZDSDateItem)newItem("rmnfEndDt_TO");
		rmnfMainUnitMdseCd = (EZDSStringItem)newItem("rmnfMainUnitMdseCd");
		mdseDescShortTxt = (EZDSStringItem)newItem("mdseDescShortTxt");
		techTocCd = (EZDSStringItem)newItem("techTocCd");
		techNm = (EZDSStringItem)newItem("techNm");
		xxComnColOrdTxt = (EZDSStringItem)newItem("xxComnColOrdTxt");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NPAL1400.NPAL1400_ASMsgArray)newMsgArray("A");
		xxPopPrm_EV = (EZDSStringItem)newItem("xxPopPrm_EV");
		xxPopPrm_P0 = (EZDSStringItem)newItem("xxPopPrm_P0");
		xxPopPrm_P1 = (EZDSStringItem)newItem("xxPopPrm_P1");
		xxPopPrm_P2 = (EZDSStringItem)newItem("xxPopPrm_P2");
		xxPopPrm_P3 = (EZDSStringItem)newItem("xxPopPrm_P3");
		xxPopPrm_P4 = (EZDSStringItem)newItem("xxPopPrm_P4");
		xxPopPrm_P5 = (EZDSStringItem)newItem("xxPopPrm_P5");
		xxPopPrm_P6 = (EZDSStringItem)newItem("xxPopPrm_P6");
		xxPopPrm_P7 = (EZDSStringItem)newItem("xxPopPrm_P7");
		xxPopPrm_P8 = (EZDSStringItem)newItem("xxPopPrm_P8");
		xxPopPrm_P9 = (EZDSStringItem)newItem("xxPopPrm_P9");
		xxPopPrm_PA = (EZDSStringItem)newItem("xxPopPrm_PA");
		R = (business.blap.NPAL1400.NPAL1400_RSMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NPAL1400SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NPAL1400SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"srchOptPk_PD", "srchOptPk_PD", "PD", "99", TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_PD", "srchOptNm_PD", "PD", "99", TYPE_HANKAKUEISU, "50", null},
	{"srchOptPk_SL", "srchOptPk_SL", "SL", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"srchOptNm_TX", "srchOptNm_TX", "TX", null, TYPE_HANKAKUEISU, "50", null},
	{"srchOptUsrId_U1", "srchOptUsrId_U1", "U1", null, TYPE_HANKAKUEISU, "16", null},
	{"rtlWhCd", "rtlWhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm", "rtlWhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rtlSwhCd", "rtlSwhCd", null, null, TYPE_HANKAKUEISU, "20", null},
	{"rtlSwhNm", "rtlSwhNm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"rmnfOrdNum", "rmnfOrdNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"rmnfOrdStsCd_PD", "rmnfOrdStsCd_PD", "PD", "99", TYPE_HANKAKUEISU, "2", null},
	{"rmnfOrdStsNm_PD", "rmnfOrdStsNm_PD", "PD", "99", TYPE_HANKAKUEISU, "20", null},
	{"rmnfOrdStsCd_SL", "rmnfOrdStsCd_SL", "SL", null, TYPE_HANKAKUEISU, "2", null},
	{"rmnfStartDt_FR", "rmnfStartDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfStartDt_TO", "rmnfStartDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfMainUnitSerNum", "rmnfMainUnitSerNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"t_MdlNm", "t_MdlNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"rmnfEndDt_FR", "rmnfEndDt_FR", "FR", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfEndDt_TO", "rmnfEndDt_TO", "TO", null, TYPE_NENTSUKIHI, "8", null},
	{"rmnfMainUnitMdseCd", "rmnfMainUnitMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"mdseDescShortTxt", "mdseDescShortTxt", null, null, TYPE_HANKAKUEISU, "250", null},
	{"techTocCd", "techTocCd", null, null, TYPE_HANKAKUEISU, "8", null},
	{"techNm", "techNm", null, null, TYPE_HANKAKUEISU, "45", null},
	{"xxComnColOrdTxt", "xxComnColOrdTxt", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "999", "business.blap.NPAL1400.NPAL1400_ASMsgArray", null, null},
	
	{"xxPopPrm_EV", "xxPopPrm_EV", "EV", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P0", "xxPopPrm_P0", "P0", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P1", "xxPopPrm_P1", "P1", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P2", "xxPopPrm_P2", "P2", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P3", "xxPopPrm_P3", "P3", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P4", "xxPopPrm_P4", "P4", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P5", "xxPopPrm_P5", "P5", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P6", "xxPopPrm_P6", "P6", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P7", "xxPopPrm_P7", "P7", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P8", "xxPopPrm_P8", "P8", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_P9", "xxPopPrm_P9", "P9", null, TYPE_HANKAKUEISU, "300", null},
	{"xxPopPrm_PA", "xxPopPrm_PA", "PA", null, TYPE_HANKAKUEISU, "300", null},
	{"R", "R", null, "20", "business.blap.NPAL1400.NPAL1400_RSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_PD
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_PD
        {"SRCH_OPT_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptPk_SL
        {"SRCH_OPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptNm_TX
        {"SRCH_OPT_USR_ID",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//srchOptUsrId_U1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm
        {"RTL_SWH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhCd
        {"RTL_SWH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlSwhNm
        {"RMNF_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdNum
        {"RMNF_ORD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsCd_PD
        {"RMNF_ORD_STS_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsNm_PD
        {"RMNF_ORD_STS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfOrdStsCd_SL
        {"RMNF_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfStartDt_FR
        {"RMNF_START_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfStartDt_TO
        {"RMNF_MAIN_UNIT_SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitSerNum
        {"T_MDL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//t_MdlNm
        {"RMNF_END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfEndDt_FR
        {"RMNF_END_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfEndDt_TO
        {"RMNF_MAIN_UNIT_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rmnfMainUnitMdseCd
        {"MDSE_DESC_SHORT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mdseDescShortTxt
        {"TECH_TOC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techTocCd
        {"TECH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//techNm
        {"XX_COMN_COL_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxComnColOrdTxt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_EV
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P0
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P1
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P2
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P3
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P4
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P5
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P6
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P7
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P8
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_P9
        {"XX_POP_PRM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPopPrm_PA
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


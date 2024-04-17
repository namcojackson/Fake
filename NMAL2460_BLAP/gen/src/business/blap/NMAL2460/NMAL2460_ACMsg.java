//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20171129110645000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2460_ACMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2460;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2460 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2460_ACMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_ROW_NUM_A*/
	public final EZDCBigDecimalItem              xxRowNum_A;

    /** LOC_NUM_A*/
	public final EZDCStringItem              locNum_A;

    /** DS_ACCT_NUM_A*/
	public final EZDCStringItem              dsAcctNum_A;

    /** DS_ACCT_TP_DESC_TXT_A*/
	public final EZDCStringItem              dsAcctTpDescTxt_A;

    /** DS_ACCT_NM_A*/
	public final EZDCStringItem              dsAcctNm_A;

    /** XX_ALL_LINE_ADDR_A*/
	public final EZDCStringItem              xxAllLineAddr_A;

    /** CTY_ADDR_A*/
	public final EZDCStringItem              ctyAddr_A;

    /** ST_NM_A*/
	public final EZDCStringItem              stNm_A;

    /** POST_CD_A*/
	public final EZDCStringItem              postCd_A;

    /** ORG_NM_A1*/
	public final EZDCStringItem              orgNm_A1;

    /** TRTY_GRP_TP_DESC_TXT_A*/
	public final EZDCStringItem              trtyGrpTpDescTxt_A;

    /** LINE_BIZ_ROLE_TP_DESC_TXT_A*/
	public final EZDCStringItem              lineBizRoleTpDescTxt_A;

    /** PSN_NUM_A*/
	public final EZDCStringItem              psnNum_A;

    /** XX_PSN_FIRST_MID_LAST_NM_A*/
	public final EZDCStringItem              xxPsnFirstMidLastNm_A;

    /** PSN_CD_A*/
	public final EZDCStringItem              psnCd_A;

    /** XX_CRAT_DT_A*/
	public final EZDCDateItem              xxCratDt_A;

    /** DS_CUST_SIC_CD_A*/
	public final EZDCStringItem              dsCustSicCd_A;

    /** DS_ACCT_CLS_DESC_TXT_A*/
	public final EZDCStringItem              dsAcctClsDescTxt_A;

    /** FIRST_DS_ACCT_GRP_TP_CD_A*/
	public final EZDCStringItem              firstDsAcctGrpTpCd_A;

    /** SCD_DS_ACCT_GRP_TP_CD_A*/
	public final EZDCStringItem              scdDsAcctGrpTpCd_A;

    /** THIRD_DS_ACCT_GRP_TP_CD_A*/
	public final EZDCStringItem              thirdDsAcctGrpTpCd_A;

    /** FRTH_DS_ACCT_GRP_TP_CD_A*/
	public final EZDCStringItem              frthDsAcctGrpTpCd_A;

    /** FIFTH_DS_ACCT_GRP_TP_CD_A*/
	public final EZDCStringItem              fifthDsAcctGrpTpCd_A;

    /** MAN_ENTRY_FLG_A*/
	public final EZDCStringItem              manEntryFlg_A;

    /** ORG_NM_A2*/
	public final EZDCStringItem              orgNm_A2;

    /** ORG_CD_A*/
	public final EZDCStringItem              orgCd_A;


	/**
	 * NMAL2460_ACMsg is constructor.
	 * The initialization when the instance of NMAL2460_ACMsg is generated.
	 */
	public NMAL2460_ACMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2460_ACMsg is constructor.
	 * The initialization when the instance of NMAL2460_ACMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2460_ACMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRowNum_A = (EZDCBigDecimalItem)newItem("xxRowNum_A");
		locNum_A = (EZDCStringItem)newItem("locNum_A");
		dsAcctNum_A = (EZDCStringItem)newItem("dsAcctNum_A");
		dsAcctTpDescTxt_A = (EZDCStringItem)newItem("dsAcctTpDescTxt_A");
		dsAcctNm_A = (EZDCStringItem)newItem("dsAcctNm_A");
		xxAllLineAddr_A = (EZDCStringItem)newItem("xxAllLineAddr_A");
		ctyAddr_A = (EZDCStringItem)newItem("ctyAddr_A");
		stNm_A = (EZDCStringItem)newItem("stNm_A");
		postCd_A = (EZDCStringItem)newItem("postCd_A");
		orgNm_A1 = (EZDCStringItem)newItem("orgNm_A1");
		trtyGrpTpDescTxt_A = (EZDCStringItem)newItem("trtyGrpTpDescTxt_A");
		lineBizRoleTpDescTxt_A = (EZDCStringItem)newItem("lineBizRoleTpDescTxt_A");
		psnNum_A = (EZDCStringItem)newItem("psnNum_A");
		xxPsnFirstMidLastNm_A = (EZDCStringItem)newItem("xxPsnFirstMidLastNm_A");
		psnCd_A = (EZDCStringItem)newItem("psnCd_A");
		xxCratDt_A = (EZDCDateItem)newItem("xxCratDt_A");
		dsCustSicCd_A = (EZDCStringItem)newItem("dsCustSicCd_A");
		dsAcctClsDescTxt_A = (EZDCStringItem)newItem("dsAcctClsDescTxt_A");
		firstDsAcctGrpTpCd_A = (EZDCStringItem)newItem("firstDsAcctGrpTpCd_A");
		scdDsAcctGrpTpCd_A = (EZDCStringItem)newItem("scdDsAcctGrpTpCd_A");
		thirdDsAcctGrpTpCd_A = (EZDCStringItem)newItem("thirdDsAcctGrpTpCd_A");
		frthDsAcctGrpTpCd_A = (EZDCStringItem)newItem("frthDsAcctGrpTpCd_A");
		fifthDsAcctGrpTpCd_A = (EZDCStringItem)newItem("fifthDsAcctGrpTpCd_A");
		manEntryFlg_A = (EZDCStringItem)newItem("manEntryFlg_A");
		orgNm_A2 = (EZDCStringItem)newItem("orgNm_A2");
		orgCd_A = (EZDCStringItem)newItem("orgCd_A");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2460_ACMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2460_ACMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRowNum_A", "xxRowNum_A", "A", null, TYPE_SEISU_SYOSU, "5", "0"},
	{"locNum_A", "locNum_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctNum_A", "dsAcctNum_A", "A", null, TYPE_HANKAKUEISU, "20", null},
	{"dsAcctTpDescTxt_A", "dsAcctTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"dsAcctNm_A", "dsAcctNm_A", "A", null, TYPE_HANKAKUEISU, "360", null},
	{"xxAllLineAddr_A", "xxAllLineAddr_A", "A", null, TYPE_HANKAKUEISU, "400", null},
	{"ctyAddr_A", "ctyAddr_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"stNm_A", "stNm_A", "A", null, TYPE_HANKAKUEISU, "25", null},
	{"postCd_A", "postCd_A", "A", null, TYPE_HANKAKUEISU, "15", null},
	{"orgNm_A1", "orgNm_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"trtyGrpTpDescTxt_A", "trtyGrpTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"lineBizRoleTpDescTxt_A", "lineBizRoleTpDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"psnNum_A", "psnNum_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"xxPsnFirstMidLastNm_A", "xxPsnFirstMidLastNm_A", "A", null, TYPE_HANKAKUEISU, "90", null},
	{"psnCd_A", "psnCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	{"xxCratDt_A", "xxCratDt_A", "A", null, TYPE_NENTSUKIHI, "8", null},
	{"dsCustSicCd_A", "dsCustSicCd_A", "A", null, TYPE_HANKAKUEISU, "30", null},
	{"dsAcctClsDescTxt_A", "dsAcctClsDescTxt_A", "A", null, TYPE_HANKAKUEISU, "50", null},
	{"firstDsAcctGrpTpCd_A", "firstDsAcctGrpTpCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"scdDsAcctGrpTpCd_A", "scdDsAcctGrpTpCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"thirdDsAcctGrpTpCd_A", "thirdDsAcctGrpTpCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"frthDsAcctGrpTpCd_A", "frthDsAcctGrpTpCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"fifthDsAcctGrpTpCd_A", "fifthDsAcctGrpTpCd_A", "A", null, TYPE_HANKAKUEISU, "28", null},
	{"manEntryFlg_A", "manEntryFlg_A", "A", null, TYPE_HANKAKUEISU, "1", null},
	{"orgNm_A2", "orgNm_A2", "A2", null, TYPE_HANKAKUEISU, "50", null},
	{"orgCd_A", "orgCd_A", "A", null, TYPE_HANKAKUEISU, "8", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_ROW_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRowNum_A
        {"LOC_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNum_A
        {"DS_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNum_A
        {"DS_ACCT_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctTpDescTxt_A
        {"DS_ACCT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctNm_A
        {"XX_ALL_LINE_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAllLineAddr_A
        {"CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ctyAddr_A
        {"ST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stNm_A
        {"POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//postCd_A
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A1
        {"TRTY_GRP_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//trtyGrpTpDescTxt_A
        {"LINE_BIZ_ROLE_TP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizRoleTpDescTxt_A
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_A
        {"XX_PSN_FIRST_MID_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnFirstMidLastNm_A
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_A
        {"XX_CRAT_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratDt_A
        {"DS_CUST_SIC_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustSicCd_A
        {"DS_ACCT_CLS_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctClsDescTxt_A
        {"FIRST_DS_ACCT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//firstDsAcctGrpTpCd_A
        {"SCD_DS_ACCT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//scdDsAcctGrpTpCd_A
        {"THIRD_DS_ACCT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//thirdDsAcctGrpTpCd_A
        {"FRTH_DS_ACCT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frthDsAcctGrpTpCd_A
        {"FIFTH_DS_ACCT_GRP_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//fifthDsAcctGrpTpCd_A
        {"MAN_ENTRY_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//manEntryFlg_A
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_A2
        {"ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgCd_A
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


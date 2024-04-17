//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20151113002952000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NLBL3100SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NLBL3100;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NLBL3100 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NLBL3100SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD_G1*/
	public final EZDSStringItem              glblCmpyCd_G1;

    /** RTL_WH_CD_H1*/
	public final EZDSStringItem              rtlWhCd_H1;

    /** RTL_WH_NM_H1*/
	public final EZDSStringItem              rtlWhNm_H1;

    /** SCHD_COORD_PSN_CD_H1*/
	public final EZDSStringItem              schdCoordPsnCd_H1;

    /** PSN_FIRST_NM_H1*/
	public final EZDSStringItem              psnFirstNm_H1;

    /** PSN_LAST_NM_H1*/
	public final EZDSStringItem              psnLastNm_H1;

    /** XX_PSN_NM_H1*/
	public final EZDSStringItem              xxPsnNm_H1;

    /** MGR_PSN_CD_H1*/
	public final EZDSStringItem              mgrPsnCd_H1;

    /** PSN_FIRST_NM_H2*/
	public final EZDSStringItem              psnFirstNm_H2;

    /** PSN_LAST_NM_H2*/
	public final EZDSStringItem              psnLastNm_H2;

    /** XX_PSN_NM_H2*/
	public final EZDSStringItem              xxPsnNm_H2;

    /** SUPV_PSN_CD_H1*/
	public final EZDSStringItem              supvPsnCd_H1;

    /** PSN_FIRST_NM_H3*/
	public final EZDSStringItem              psnFirstNm_H3;

    /** PSN_LAST_NM_H3*/
	public final EZDSStringItem              psnLastNm_H3;

    /** XX_PSN_NM_H3*/
	public final EZDSStringItem              xxPsnNm_H3;

    /** CARR_CD_H1*/
	public final EZDSStringItem              carrCd_H1;

    /** PSN_FIRST_NM_H4*/
	public final EZDSStringItem              psnFirstNm_H4;

    /** PSN_LAST_NM_H4*/
	public final EZDSStringItem              psnLastNm_H4;

    /** XX_PSN_NM_H4*/
	public final EZDSStringItem              xxPsnNm_H4;

    /** ST_CD_H1*/
	public final EZDSStringItem              stCd_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDSDateItem              effFromDt_H1;

    /** EFF_THRU_DT_H1*/
	public final EZDSDateItem              effThruDt_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDSBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDSBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDSBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NLBL3100.NLBL3100_ASMsgArray              A;


	/**
	 * NLBL3100SMsg is constructor.
	 * The initialization when the instance of NLBL3100SMsg is generated.
	 */
	public NLBL3100SMsg() {
		this(false, -1);
	}

	/**
	 * NLBL3100SMsg is constructor.
	 * The initialization when the instance of NLBL3100SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NLBL3100SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd_G1 = (EZDSStringItem)newItem("glblCmpyCd_G1");
		rtlWhCd_H1 = (EZDSStringItem)newItem("rtlWhCd_H1");
		rtlWhNm_H1 = (EZDSStringItem)newItem("rtlWhNm_H1");
		schdCoordPsnCd_H1 = (EZDSStringItem)newItem("schdCoordPsnCd_H1");
		psnFirstNm_H1 = (EZDSStringItem)newItem("psnFirstNm_H1");
		psnLastNm_H1 = (EZDSStringItem)newItem("psnLastNm_H1");
		xxPsnNm_H1 = (EZDSStringItem)newItem("xxPsnNm_H1");
		mgrPsnCd_H1 = (EZDSStringItem)newItem("mgrPsnCd_H1");
		psnFirstNm_H2 = (EZDSStringItem)newItem("psnFirstNm_H2");
		psnLastNm_H2 = (EZDSStringItem)newItem("psnLastNm_H2");
		xxPsnNm_H2 = (EZDSStringItem)newItem("xxPsnNm_H2");
		supvPsnCd_H1 = (EZDSStringItem)newItem("supvPsnCd_H1");
		psnFirstNm_H3 = (EZDSStringItem)newItem("psnFirstNm_H3");
		psnLastNm_H3 = (EZDSStringItem)newItem("psnLastNm_H3");
		xxPsnNm_H3 = (EZDSStringItem)newItem("xxPsnNm_H3");
		carrCd_H1 = (EZDSStringItem)newItem("carrCd_H1");
		psnFirstNm_H4 = (EZDSStringItem)newItem("psnFirstNm_H4");
		psnLastNm_H4 = (EZDSStringItem)newItem("psnLastNm_H4");
		xxPsnNm_H4 = (EZDSStringItem)newItem("xxPsnNm_H4");
		stCd_H1 = (EZDSStringItem)newItem("stCd_H1");
		effFromDt_H1 = (EZDSDateItem)newItem("effFromDt_H1");
		effThruDt_H1 = (EZDSDateItem)newItem("effThruDt_H1");
		xxPageShowFromNum = (EZDSBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDSBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDSBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NLBL3100.NLBL3100_ASMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NLBL3100SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NLBL3100SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd_G1", "glblCmpyCd_G1", "G1", null, TYPE_HANKAKUEISU, "4", null},
	{"rtlWhCd_H1", "rtlWhCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"rtlWhNm_H1", "rtlWhNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"schdCoordPsnCd_H1", "schdCoordPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnFirstNm_H1", "psnFirstNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H1", "psnLastNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H1", "xxPsnNm_H1", "H1", null, TYPE_HANKAKUEISU, "62", null},
	{"mgrPsnCd_H1", "mgrPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnFirstNm_H2", "psnFirstNm_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H2", "psnLastNm_H2", "H2", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H2", "xxPsnNm_H2", "H2", null, TYPE_HANKAKUEISU, "62", null},
	{"supvPsnCd_H1", "supvPsnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnFirstNm_H3", "psnFirstNm_H3", "H3", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H3", "psnLastNm_H3", "H3", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H3", "xxPsnNm_H3", "H3", null, TYPE_HANKAKUEISU, "62", null},
	{"carrCd_H1", "carrCd_H1", "H1", null, TYPE_HANKAKUEISU, "20", null},
	{"psnFirstNm_H4", "psnFirstNm_H4", "H4", null, TYPE_HANKAKUEISU, "30", null},
	{"psnLastNm_H4", "psnLastNm_H4", "H4", null, TYPE_HANKAKUEISU, "30", null},
	{"xxPsnNm_H4", "xxPsnNm_H4", "H4", null, TYPE_HANKAKUEISU, "62", null},
	{"stCd_H1", "stCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "999", "business.blap.NLBL3100.NLBL3100_ASMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_G1
        {"RTL_WH_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhCd_H1
        {"RTL_WH_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rtlWhNm_H1
        {"SCHD_COORD_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//schdCoordPsnCd_H1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H1
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H1
        {"MGR_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mgrPsnCd_H1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H2
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H2
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H2
        {"SUPV_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//supvPsnCd_H1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H3
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H3
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H3
        {"CARR_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrCd_H1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H4
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H4
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H4
        {"ST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//stCd_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
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

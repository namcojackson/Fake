//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20160610110504000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2570CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2570;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2570 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2570CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_PSN_NM_H1*/
	public final EZDCStringItem              xxPsnNm_H1;

    /** PSN_LAST_NM_H1*/
	public final EZDCStringItem              psnLastNm_H1;

    /** PSN_FIRST_NM_H1*/
	public final EZDCStringItem              psnFirstNm_H1;

    /** PSN_CD_H1*/
	public final EZDCStringItem              psnCd_H1;

    /** JOB_TTL_CD_H1*/
	public final EZDCStringItem              jobTtlCd_H1;

    /** PSN_NUM_H1*/
	public final EZDCStringItem              psnNum_H1;

    /** ORG_FUNC_ROLE_TP_CD_H1*/
	public final EZDCStringItem              orgFuncRoleTpCd_H1;

    /** ORG_FUNC_ROLE_TP_CD_H2*/
	public final EZDCStringItemArray              orgFuncRoleTpCd_H2;

    /** ORG_FUNC_ROLE_TP_NM_H1*/
	public final EZDCStringItemArray              orgFuncRoleTpNm_H1;

    /** ORG_NM_H1*/
	public final EZDCStringItem              orgNm_H1;

    /** EFF_FROM_DT_H1*/
	public final EZDCDateItem              effFromDt_H1;

    /** EFF_FROM_DT_H2*/
	public final EZDCDateItem              effFromDt_H2;

    /** EFF_THRU_DT_H1*/
	public final EZDCDateItem              effThruDt_H1;

    /** EFF_THRU_DT_H2*/
	public final EZDCDateItem              effThruDt_H2;

    /** XX_CHK_BOX_H1*/
	public final EZDCStringItem              xxChkBox_H1;

    /** XX_CHK_BOX_H2*/
	public final EZDCStringItem              xxChkBox_H2;

    /** XX_MODE_CD_H1*/
	public final EZDCStringItem              xxModeCd_H1;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDCBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDCBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDCBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.blap.NMAL2570.NMAL2570_ACMsgArray              A;

    /** B*/
	public final business.blap.NMAL2570.NMAL2570_BCMsgArray              B;

    /** XX_TBL_NM_P1*/
	public final EZDCStringItem              xxTblNm_P1;

    /** XX_TBL_CD_COL_NM_P1*/
	public final EZDCStringItem              xxTblCdColNm_P1;

    /** XX_TBL_NM_COL_NM_P1*/
	public final EZDCStringItem              xxTblNmColNm_P1;

    /** XX_TBL_SORT_COL_NM_P1*/
	public final EZDCStringItem              xxTblSortColNm_P1;

    /** XX_SCR_NM_P1*/
	public final EZDCStringItem              xxScrNm_P1;

    /** XX_HDR_CD_LB_NM_P1*/
	public final EZDCStringItem              xxHdrCdLbNm_P1;

    /** XX_HDR_NM_LB_NM_P1*/
	public final EZDCStringItem              xxHdrNmLbNm_P1;

    /** XX_DTL_CD_LB_NM_P1*/
	public final EZDCStringItem              xxDtlCdLbNm_P1;

    /** XX_DTL_NM_LB_NM_P1*/
	public final EZDCStringItem              xxDtlNmLbNm_P1;

    /** JOB_TTL_CD_G1*/
	public final EZDCStringItem              jobTtlCd_G1;

    /** JOB_TTL_NM_G1*/
	public final EZDCStringItem              jobTtlNm_G1;


	/**
	 * NMAL2570CMsg is constructor.
	 * The initialization when the instance of NMAL2570CMsg is generated.
	 */
	public NMAL2570CMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2570CMsg is constructor.
	 * The initialization when the instance of NMAL2570CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2570CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxPsnNm_H1 = (EZDCStringItem)newItem("xxPsnNm_H1");
		psnLastNm_H1 = (EZDCStringItem)newItem("psnLastNm_H1");
		psnFirstNm_H1 = (EZDCStringItem)newItem("psnFirstNm_H1");
		psnCd_H1 = (EZDCStringItem)newItem("psnCd_H1");
		jobTtlCd_H1 = (EZDCStringItem)newItem("jobTtlCd_H1");
		psnNum_H1 = (EZDCStringItem)newItem("psnNum_H1");
		orgFuncRoleTpCd_H1 = (EZDCStringItem)newItem("orgFuncRoleTpCd_H1");
		orgFuncRoleTpCd_H2 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpCd_H2");
		orgFuncRoleTpNm_H1 = (EZDCStringItemArray)newItemArray("orgFuncRoleTpNm_H1");
		orgNm_H1 = (EZDCStringItem)newItem("orgNm_H1");
		effFromDt_H1 = (EZDCDateItem)newItem("effFromDt_H1");
		effFromDt_H2 = (EZDCDateItem)newItem("effFromDt_H2");
		effThruDt_H1 = (EZDCDateItem)newItem("effThruDt_H1");
		effThruDt_H2 = (EZDCDateItem)newItem("effThruDt_H2");
		xxChkBox_H1 = (EZDCStringItem)newItem("xxChkBox_H1");
		xxChkBox_H2 = (EZDCStringItem)newItem("xxChkBox_H2");
		xxModeCd_H1 = (EZDCStringItem)newItem("xxModeCd_H1");
		xxPageShowFromNum = (EZDCBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDCBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDCBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.blap.NMAL2570.NMAL2570_ACMsgArray)newMsgArray("A");
		B = (business.blap.NMAL2570.NMAL2570_BCMsgArray)newMsgArray("B");
		xxTblNm_P1 = (EZDCStringItem)newItem("xxTblNm_P1");
		xxTblCdColNm_P1 = (EZDCStringItem)newItem("xxTblCdColNm_P1");
		xxTblNmColNm_P1 = (EZDCStringItem)newItem("xxTblNmColNm_P1");
		xxTblSortColNm_P1 = (EZDCStringItem)newItem("xxTblSortColNm_P1");
		xxScrNm_P1 = (EZDCStringItem)newItem("xxScrNm_P1");
		xxHdrCdLbNm_P1 = (EZDCStringItem)newItem("xxHdrCdLbNm_P1");
		xxHdrNmLbNm_P1 = (EZDCStringItem)newItem("xxHdrNmLbNm_P1");
		xxDtlCdLbNm_P1 = (EZDCStringItem)newItem("xxDtlCdLbNm_P1");
		xxDtlNmLbNm_P1 = (EZDCStringItem)newItem("xxDtlNmLbNm_P1");
		jobTtlCd_G1 = (EZDCStringItem)newItem("jobTtlCd_G1");
		jobTtlNm_G1 = (EZDCStringItem)newItem("jobTtlNm_G1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2570CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2570CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxPsnNm_H1", "xxPsnNm_H1", "H1", null, TYPE_HANKAKUEISU, "62", null},
	{"psnLastNm_H1", "psnLastNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnFirstNm_H1", "psnFirstNm_H1", "H1", null, TYPE_HANKAKUEISU, "30", null},
	{"psnCd_H1", "psnCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"jobTtlCd_H1", "jobTtlCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"psnNum_H1", "psnNum_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"orgFuncRoleTpCd_H1", "orgFuncRoleTpCd_H1", "H1", null, TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpCd_H2", "orgFuncRoleTpCd_H2", "H2", "300", TYPE_HANKAKUEISU, "8", null},
	{"orgFuncRoleTpNm_H1", "orgFuncRoleTpNm_H1", "H1", "300", TYPE_HANKAKUEISU, "50", null},
	{"orgNm_H1", "orgNm_H1", "H1", null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt_H1", "effFromDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effFromDt_H2", "effFromDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H1", "effThruDt_H1", "H1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_H2", "effThruDt_H2", "H2", null, TYPE_NENTSUKIHI, "8", null},
	{"xxChkBox_H1", "xxChkBox_H1", "H1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_H2", "xxChkBox_H2", "H2", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxModeCd_H1", "xxModeCd_H1", "H1", null, TYPE_HANKAKUEISU, "2", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "200", "business.blap.NMAL2570.NMAL2570_ACMsgArray", null, null},
	
	{"B", "B", null, "20", "business.blap.NMAL2570.NMAL2570_BCMsgArray", null, null},
	
	{"xxTblNm_P1", "xxTblNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblCdColNm_P1", "xxTblCdColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblNmColNm_P1", "xxTblNmColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxTblSortColNm_P1", "xxTblSortColNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxScrNm_P1", "xxScrNm_P1", "P1", null, TYPE_HANKAKUEISU, "70", null},
	{"xxHdrCdLbNm_P1", "xxHdrCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxHdrNmLbNm_P1", "xxHdrNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlCdLbNm_P1", "xxDtlCdLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"xxDtlNmLbNm_P1", "xxDtlNmLbNm_P1", "P1", null, TYPE_HANKAKUEISU, "30", null},
	{"jobTtlCd_G1", "jobTtlCd_G1", "G1", null, TYPE_HANKAKUEISU, "8", null},
	{"jobTtlNm_G1", "jobTtlNm_G1", "G1", null, TYPE_HANKAKUEISU, "50", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H1
        {"PSN_LAST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnLastNm_H1
        {"PSN_FIRST_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnFirstNm_H1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H1
        {"JOB_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlCd_H1
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H1
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_H1
        {"ORG_FUNC_ROLE_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpCd_H2
        {"ORG_FUNC_ROLE_TP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgFuncRoleTpNm_H1
        {"ORG_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgNm_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_H2
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_H2
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_H2
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd_H1
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
		null,	//A
		null,	//B
        {"XX_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNm_P1
        {"XX_TBL_CD_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblCdColNm_P1
        {"XX_TBL_NM_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblNmColNm_P1
        {"XX_TBL_SORT_COL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxTblSortColNm_P1
        {"XX_SCR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm_P1
        {"XX_HDR_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrCdLbNm_P1
        {"XX_HDR_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxHdrNmLbNm_P1
        {"XX_DTL_CD_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlCdLbNm_P1
        {"XX_DTL_NM_LB_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtlNmLbNm_P1
        {"JOB_TTL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlCd_G1
        {"JOB_TTL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jobTtlNm_G1
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


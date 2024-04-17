//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20160622132518000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL2720SMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL2720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL2720 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL2720SMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** BIZ_AREA_ORG_CD_H*/
	public final EZDSStringItem              bizAreaOrgCd_H;

    /** BIZ_AREA_ORG_CD_P*/
	public final EZDSStringItemArray              bizAreaOrgCd_P;

    /** BIZ_AREA_ORG_DESC_TXT_P*/
	public final EZDSStringItemArray              bizAreaOrgDescTxt_P;

    /** XX_PSN_NM_H*/
	public final EZDSStringItem              xxPsnNm_H;

    /** PSN_NUM_H*/
	public final EZDSStringItem              psnNum_H;

    /** ORG_DESC_TXT_H*/
	public final EZDSStringItem              orgDescTxt_H;

    /** PSN_CD_H*/
	public final EZDSStringItem              psnCd_H;

    /** XX_CHK_BOX_AH*/
	public final EZDSStringItem              xxChkBox_AH;

    /** A*/
	public final business.blap.NMAL2720.NMAL2720_ASMsgArray              A;

    /** PSN_NUM_D1*/
	public final EZDSStringItem              psnNum_D1;

    /** PSN_CD_D1*/
	public final EZDSStringItem              psnCd_D1;

    /** EFF_FROM_DT_D1*/
	public final EZDSDateItem              effFromDt_D1;

    /** EFF_THRU_DT_D1*/
	public final EZDSDateItem              effThruDt_D1;

    /** RQST_RSLT_CMNT_TXT_D1*/
	public final EZDSStringItem              rqstRsltCmntTxt_D1;

    /** XX_FILE_DATA*/
	public final EZDSMimeSourceItem              xxFileData;

    /** XX_SORT_TBL_NM*/
	public final EZDSStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDSStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDSStringItem              xxSortOrdByTxt;

    /** R*/
	public final business.blap.NMAL2720.NMAL2720_RSMsgArray              R;


	/**
	 * NMAL2720SMsg is constructor.
	 * The initialization when the instance of NMAL2720SMsg is generated.
	 */
	public NMAL2720SMsg() {
		this(false, -1);
	}

	/**
	 * NMAL2720SMsg is constructor.
	 * The initialization when the instance of NMAL2720SMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL2720SMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		bizAreaOrgCd_H = (EZDSStringItem)newItem("bizAreaOrgCd_H");
		bizAreaOrgCd_P = (EZDSStringItemArray)newItemArray("bizAreaOrgCd_P");
		bizAreaOrgDescTxt_P = (EZDSStringItemArray)newItemArray("bizAreaOrgDescTxt_P");
		xxPsnNm_H = (EZDSStringItem)newItem("xxPsnNm_H");
		psnNum_H = (EZDSStringItem)newItem("psnNum_H");
		orgDescTxt_H = (EZDSStringItem)newItem("orgDescTxt_H");
		psnCd_H = (EZDSStringItem)newItem("psnCd_H");
		xxChkBox_AH = (EZDSStringItem)newItem("xxChkBox_AH");
		A = (business.blap.NMAL2720.NMAL2720_ASMsgArray)newMsgArray("A");
		psnNum_D1 = (EZDSStringItem)newItem("psnNum_D1");
		psnCd_D1 = (EZDSStringItem)newItem("psnCd_D1");
		effFromDt_D1 = (EZDSDateItem)newItem("effFromDt_D1");
		effThruDt_D1 = (EZDSDateItem)newItem("effThruDt_D1");
		rqstRsltCmntTxt_D1 = (EZDSStringItem)newItem("rqstRsltCmntTxt_D1");
		xxFileData = (EZDSMimeSourceItem)newItem("xxFileData");
		xxSortTblNm = (EZDSStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDSStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDSStringItem)newItem("xxSortOrdByTxt");
		R = (business.blap.NMAL2720.NMAL2720_RSMsgArray)newMsgArray("R");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL2720SMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL2720SMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"bizAreaOrgCd_H", "bizAreaOrgCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgCd_P", "bizAreaOrgCd_P", "P", "99", TYPE_HANKAKUEISU, "8", null},
	{"bizAreaOrgDescTxt_P", "bizAreaOrgDescTxt_P", "P", "99", TYPE_HANKAKUEISU, "50", null},
	{"xxPsnNm_H", "xxPsnNm_H", "H", null, TYPE_HANKAKUEISU, "62", null},
	{"psnNum_H", "psnNum_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"orgDescTxt_H", "orgDescTxt_H", "H", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_H", "psnCd_H", "H", null, TYPE_HANKAKUEISU, "8", null},
	{"xxChkBox_AH", "xxChkBox_AH", "AH", null, TYPE_HANKAKUEIJI, "1", null},
	{"A", "A", null, "1000", "business.blap.NMAL2720.NMAL2720_ASMsgArray", null, null},
	
	{"psnNum_D1", "psnNum_D1", "D1", null, TYPE_HANKAKUEISU, "50", null},
	{"psnCd_D1", "psnCd_D1", "D1", null, TYPE_HANKAKUEISU, "8", null},
	{"effFromDt_D1", "effFromDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_D1", "effThruDt_D1", "D1", null, TYPE_NENTSUKIHI, "8", null},
	{"rqstRsltCmntTxt_D1", "rqstRsltCmntTxt_D1", "D1", null, TYPE_HANKAKUEISU, "4000", null},
	{"xxFileData", "xxFileData", null, null, TYPE_UPLOAD, null, null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"R", "R", null, "20", "business.blap.NMAL2720.NMAL2720_RSMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_H
        {"BIZ_AREA_ORG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgCd_P
        {"BIZ_AREA_ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bizAreaOrgDescTxt_P
        {"XX_PSN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPsnNm_H
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_H
        {"ORG_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//orgDescTxt_H
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_H
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_AH
		null,	//A
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum_D1
        {"PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnCd_D1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_D1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_D1
        {"RQST_RSLT_CMNT_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//rqstRsltCmntTxt_D1
        {"XX_FILE_DATA",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxFileData
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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

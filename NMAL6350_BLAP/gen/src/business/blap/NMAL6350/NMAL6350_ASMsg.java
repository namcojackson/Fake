//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180904145143000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6350_ASMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6350;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6350 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6350_ASMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_A1*/
	public final EZDSStringItem              xxChkBox_A1;

    /** GLBL_CMPY_CD_A1*/
	public final EZDSStringItem              glblCmpyCd_A1;

    /** PKG_UOM_SORT_NUM_A1*/
	public final EZDSBigDecimalItem              pkgUomSortNum_A1;

    /** PKG_UOM_CD_A1*/
	public final EZDSStringItem              pkgUomCd_A1;

    /** PKG_UOM_DESC_TXT_A1*/
	public final EZDSStringItem              pkgUomDescTxt_A1;

    /** PKG_UOM_NM_A1*/
	public final EZDSStringItem              pkgUomNm_A1;

    /** PKG_UOM_STD_FLG_A1*/
	public final EZDSStringItem              pkgUomStdFlg_A1;

    /** PKG_UOM_MND_FLG_A1*/
	public final EZDSStringItem              pkgUomMndFlg_A1;

    /** PKG_UOM_PKG_LVL_NUM_A1*/
	public final EZDSStringItem              pkgUomPkgLvlNum_A1;

    /** PKG_UOM_CLS_CD_A1*/
	public final EZDSStringItem              pkgUomClsCd_A1;

    /** _EZUpdateDateTime_A1*/
	public final EZDSStringItem              ezUpTime_A1;

    /** _EZUpTimeZone_A1*/
	public final EZDSStringItem              ezUpTimeZone_A1;


	/**
	 * NMAL6350_ASMsg is constructor.
	 * The initialization when the instance of NMAL6350_ASMsg is generated.
	 */
	public NMAL6350_ASMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6350_ASMsg is constructor.
	 * The initialization when the instance of NMAL6350_ASMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6350_ASMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_A1 = (EZDSStringItem)newItem("xxChkBox_A1");
		glblCmpyCd_A1 = (EZDSStringItem)newItem("glblCmpyCd_A1");
		pkgUomSortNum_A1 = (EZDSBigDecimalItem)newItem("pkgUomSortNum_A1");
		pkgUomCd_A1 = (EZDSStringItem)newItem("pkgUomCd_A1");
		pkgUomDescTxt_A1 = (EZDSStringItem)newItem("pkgUomDescTxt_A1");
		pkgUomNm_A1 = (EZDSStringItem)newItem("pkgUomNm_A1");
		pkgUomStdFlg_A1 = (EZDSStringItem)newItem("pkgUomStdFlg_A1");
		pkgUomMndFlg_A1 = (EZDSStringItem)newItem("pkgUomMndFlg_A1");
		pkgUomPkgLvlNum_A1 = (EZDSStringItem)newItem("pkgUomPkgLvlNum_A1");
		pkgUomClsCd_A1 = (EZDSStringItem)newItem("pkgUomClsCd_A1");
		ezUpTime_A1 = (EZDSStringItem)newItem("ezUpTime_A1");
		ezUpTimeZone_A1 = (EZDSStringItem)newItem("ezUpTimeZone_A1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6350_ASMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6350_ASMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_A1", "xxChkBox_A1", "A1", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_A1", "glblCmpyCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"pkgUomSortNum_A1", "pkgUomSortNum_A1", "A1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"pkgUomCd_A1", "pkgUomCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"pkgUomDescTxt_A1", "pkgUomDescTxt_A1", "A1", null, TYPE_HANKAKUEISU, "50", null},
	{"pkgUomNm_A1", "pkgUomNm_A1", "A1", null, TYPE_HANKAKUEISU, "30", null},
	{"pkgUomStdFlg_A1", "pkgUomStdFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomMndFlg_A1", "pkgUomMndFlg_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomPkgLvlNum_A1", "pkgUomPkgLvlNum_A1", "A1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomClsCd_A1", "pkgUomClsCd_A1", "A1", null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpTime_A1", "ezUpTime_A1", "A1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_A1", "ezUpTimeZone_A1", "A1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_A1
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_A1
        {"PKG_UOM_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomSortNum_A1
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_A1
        {"PKG_UOM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomDescTxt_A1
        {"PKG_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomNm_A1
        {"PKG_UOM_STD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomStdFlg_A1
        {"PKG_UOM_MND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomMndFlg_A1
        {"PKG_UOM_PKG_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomPkgLvlNum_A1
        {"PKG_UOM_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomClsCd_A1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_A1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_A1
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

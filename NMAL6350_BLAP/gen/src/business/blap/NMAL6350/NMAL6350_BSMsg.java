//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20180904145143000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6350_BSMsg.java  Copyright  FUJITSU LIMITED 2007
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
public class NMAL6350_BSMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_CHK_BOX_B1*/
	public final EZDSStringItem              xxChkBox_B1;

    /** GLBL_CMPY_CD_B1*/
	public final EZDSStringItem              glblCmpyCd_B1;

    /** PKG_UOM_SORT_NUM_B1*/
	public final EZDSBigDecimalItem              pkgUomSortNum_B1;

    /** PKG_UOM_CD_B1*/
	public final EZDSStringItem              pkgUomCd_B1;

    /** PKG_UOM_DESC_TXT_B1*/
	public final EZDSStringItem              pkgUomDescTxt_B1;

    /** PKG_UOM_NM_B1*/
	public final EZDSStringItem              pkgUomNm_B1;

    /** PKG_UOM_STD_FLG_B1*/
	public final EZDSStringItem              pkgUomStdFlg_B1;

    /** PKG_UOM_MND_FLG_B1*/
	public final EZDSStringItem              pkgUomMndFlg_B1;

    /** PKG_UOM_PKG_LVL_NUM_B1*/
	public final EZDSStringItem              pkgUomPkgLvlNum_B1;

    /** PKG_UOM_CLS_CD_B1*/
	public final EZDSStringItem              pkgUomClsCd_B1;

    /** _EZUpdateDateTime_B1*/
	public final EZDSStringItem              ezUpTime_B1;

    /** _EZUpTimeZone_B1*/
	public final EZDSStringItem              ezUpTimeZone_B1;


	/**
	 * NMAL6350_BSMsg is constructor.
	 * The initialization when the instance of NMAL6350_BSMsg is generated.
	 */
	public NMAL6350_BSMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6350_BSMsg is constructor.
	 * The initialization when the instance of NMAL6350_BSMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6350_BSMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxChkBox_B1 = (EZDSStringItem)newItem("xxChkBox_B1");
		glblCmpyCd_B1 = (EZDSStringItem)newItem("glblCmpyCd_B1");
		pkgUomSortNum_B1 = (EZDSBigDecimalItem)newItem("pkgUomSortNum_B1");
		pkgUomCd_B1 = (EZDSStringItem)newItem("pkgUomCd_B1");
		pkgUomDescTxt_B1 = (EZDSStringItem)newItem("pkgUomDescTxt_B1");
		pkgUomNm_B1 = (EZDSStringItem)newItem("pkgUomNm_B1");
		pkgUomStdFlg_B1 = (EZDSStringItem)newItem("pkgUomStdFlg_B1");
		pkgUomMndFlg_B1 = (EZDSStringItem)newItem("pkgUomMndFlg_B1");
		pkgUomPkgLvlNum_B1 = (EZDSStringItem)newItem("pkgUomPkgLvlNum_B1");
		pkgUomClsCd_B1 = (EZDSStringItem)newItem("pkgUomClsCd_B1");
		ezUpTime_B1 = (EZDSStringItem)newItem("ezUpTime_B1");
		ezUpTimeZone_B1 = (EZDSStringItem)newItem("ezUpTimeZone_B1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6350_BSMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6350_BSMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxChkBox_B1", "xxChkBox_B1", "B1", null, TYPE_HANKAKUEIJI, "1", null},
	{"glblCmpyCd_B1", "glblCmpyCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"pkgUomSortNum_B1", "pkgUomSortNum_B1", "B1", null, TYPE_SEISU_SYOSU, "3", "0"},
	{"pkgUomCd_B1", "pkgUomCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"pkgUomDescTxt_B1", "pkgUomDescTxt_B1", "B1", null, TYPE_HANKAKUEISU, "50", null},
	{"pkgUomNm_B1", "pkgUomNm_B1", "B1", null, TYPE_HANKAKUEISU, "30", null},
	{"pkgUomStdFlg_B1", "pkgUomStdFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomMndFlg_B1", "pkgUomMndFlg_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomPkgLvlNum_B1", "pkgUomPkgLvlNum_B1", "B1", null, TYPE_HANKAKUEISU, "1", null},
	{"pkgUomClsCd_B1", "pkgUomClsCd_B1", "B1", null, TYPE_HANKAKUEISU, "4", null},
	{"ezUpTime_B1", "ezUpTime_B1", "B1", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_B1", "ezUpTimeZone_B1", "B1", null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_B1
        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd_B1
        {"PKG_UOM_SORT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomSortNum_B1
        {"PKG_UOM_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomCd_B1
        {"PKG_UOM_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomDescTxt_B1
        {"PKG_UOM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomNm_B1
        {"PKG_UOM_STD_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomStdFlg_B1
        {"PKG_UOM_MND_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomMndFlg_B1
        {"PKG_UOM_PKG_LVL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomPkgLvlNum_B1
        {"PKG_UOM_CLS_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//pkgUomClsCd_B1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_B1
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_B1
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

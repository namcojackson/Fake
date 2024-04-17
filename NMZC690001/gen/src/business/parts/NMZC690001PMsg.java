//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170815134330000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMZC690001PMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.parts;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMZC690001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NMZC690001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** PSN_NUM*/
	public final EZDPStringItem              psnNum;

    /** XX_EXTN_PTY_NM*/
	public final EZDPStringItem              xxExtnPtyNm;

    /** XX_EXTN_STR_ADDR*/
	public final EZDPStringItem              xxExtnStrAddr;

    /** XX_EXTN_CTY_ADDR*/
	public final EZDPStringItem              xxExtnCtyAddr;

    /** XX_EXTN_ST_ADDR*/
	public final EZDPStringItem              xxExtnStAddr;

    /** XX_EXTN_POST_CD*/
	public final EZDPStringItem              xxExtnPostCd;

    /** XX_EXTN_IND_MNG_CD*/
	public final EZDPStringItem              xxExtnIndMngCd;

    /** XX_EXTN_IND_SPCL_CD*/
	public final EZDPStringItem              xxExtnIndSpclCd;

    /** XX_EXTN_FM_DEPT_NM*/
	public final EZDPStringItem              xxExtnFmDeptNm;

    /** XX_EXTN_SORT_ORD_TXT*/
	public final EZDPStringItem              xxExtnSortOrdTxt;

    /** XX_EXTN_SORT_FLD_TXT*/
	public final EZDPStringItem              xxExtnSortFldTxt;

    /** XX_EXTN_PTY_NUM*/
	public final EZDPStringItem              xxExtnPtyNum;

    /** XX_EXTN_PTY_SITE_NUM*/
	public final EZDPStringItem              xxExtnPtySiteNum;

    /** xxCustList*/
	public final business.parts.NMZC690001_xxCustListPMsgArray              xxCustList;

    /** xxMsgIdList*/
	public final business.parts.NMZC690001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NMZC690001PMsg is constructor.
	 * The initialization when the instance of NMZC690001PMsg is generated.
	 */
	public NMZC690001PMsg() {
		this(false, -1);
	}

	/**
	 * NMZC690001PMsg is constructor.
	 * The initialization when the instance of NMZC690001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMZC690001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		psnNum = (EZDPStringItem)newItem("psnNum");
		xxExtnPtyNm = (EZDPStringItem)newItem("xxExtnPtyNm");
		xxExtnStrAddr = (EZDPStringItem)newItem("xxExtnStrAddr");
		xxExtnCtyAddr = (EZDPStringItem)newItem("xxExtnCtyAddr");
		xxExtnStAddr = (EZDPStringItem)newItem("xxExtnStAddr");
		xxExtnPostCd = (EZDPStringItem)newItem("xxExtnPostCd");
		xxExtnIndMngCd = (EZDPStringItem)newItem("xxExtnIndMngCd");
		xxExtnIndSpclCd = (EZDPStringItem)newItem("xxExtnIndSpclCd");
		xxExtnFmDeptNm = (EZDPStringItem)newItem("xxExtnFmDeptNm");
		xxExtnSortOrdTxt = (EZDPStringItem)newItem("xxExtnSortOrdTxt");
		xxExtnSortFldTxt = (EZDPStringItem)newItem("xxExtnSortFldTxt");
		xxExtnPtyNum = (EZDPStringItem)newItem("xxExtnPtyNum");
		xxExtnPtySiteNum = (EZDPStringItem)newItem("xxExtnPtySiteNum");
		xxCustList = (business.parts.NMZC690001_xxCustListPMsgArray)newMsgArray("xxCustList");
		xxMsgIdList = (business.parts.NMZC690001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NMZC690001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMZC690001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"psnNum", "psnNum", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxExtnPtyNm", "xxExtnPtyNm", null, null, TYPE_HANKAKUEISU, "1440", null},
	{"xxExtnStrAddr", "xxExtnStrAddr", null, null, TYPE_HANKAKUEISU, "2000", null},
	{"xxExtnCtyAddr", "xxExtnCtyAddr", null, null, TYPE_HANKAKUEISU, "240", null},
	{"xxExtnStAddr", "xxExtnStAddr", null, null, TYPE_HANKAKUEISU, "240", null},
	{"xxExtnPostCd", "xxExtnPostCd", null, null, TYPE_HANKAKUEISU, "240", null},
	{"xxExtnIndMngCd", "xxExtnIndMngCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxExtnIndSpclCd", "xxExtnIndSpclCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"xxExtnFmDeptNm", "xxExtnFmDeptNm", null, null, TYPE_HANKAKUEISU, "80", null},
	{"xxExtnSortOrdTxt", "xxExtnSortOrdTxt", null, null, TYPE_HANKAKUEISU, "10", null},
	{"xxExtnSortFldTxt", "xxExtnSortFldTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxExtnPtyNum", "xxExtnPtyNum", null, null, TYPE_HANKAKUEISU, "120", null},
	{"xxExtnPtySiteNum", "xxExtnPtySiteNum", null, null, TYPE_HANKAKUEISU, "120", null},
	{"xxCustList", "xxCustList", null, "1000", "business.parts.NMZC690001_xxCustListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NMZC690001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"PSN_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//psnNum
        {"XX_EXTN_PTY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnPtyNm
        {"XX_EXTN_STR_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnStrAddr
        {"XX_EXTN_CTY_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnCtyAddr
        {"XX_EXTN_ST_ADDR",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnStAddr
        {"XX_EXTN_POST_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnPostCd
        {"XX_EXTN_IND_MNG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnIndMngCd
        {"XX_EXTN_IND_SPCL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnIndSpclCd
        {"XX_EXTN_FM_DEPT_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnFmDeptNm
        {"XX_EXTN_SORT_ORD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnSortOrdTxt
        {"XX_EXTN_SORT_FLD_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnSortFldTxt
        {"XX_EXTN_PTY_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnPtyNum
        {"XX_EXTN_PTY_SITE_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxExtnPtySiteNum
		null,	//xxCustList
		null,	//xxMsgIdList
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

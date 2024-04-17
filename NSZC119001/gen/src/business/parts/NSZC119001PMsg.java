//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20180214171638000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC119001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC119001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC119001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** ACT_DESC_TXT_H1*/
	public final EZDPStringItem              actDescTxt_H1;

    /** ACT_DESC_TXT_H2*/
	public final EZDPStringItem              actDescTxt_H2;

    /** XX_CRAT_BY_TXT*/
	public final EZDPStringItem              xxCratByTxt;

    /** XX_LAST_UPD_BY_TXT*/
	public final EZDPStringItem              xxLastUpdByTxt;

    /** XX_EMP_NM_TXT*/
	public final EZDPStringItem              xxEmpNmTxt;

    /** XX_EMP_TEL_NUM*/
	public final EZDPStringItem              xxEmpTelNum;

    /** XX_SUB_CMPY_NM_TXT*/
	public final EZDPStringItem              xxSubCmpyNmTxt;

    /** XX_DEPT_NM_TXT*/
	public final EZDPStringItem              xxDeptNmTxt;

    /** XX_CUST_NUM*/
	public final EZDPStringItem              xxCustNum;

    /** XX_CUST_NM_TXT*/
	public final EZDPStringItem              xxCustNmTxt;

    /** XX_CUST_CTAC_NM_TXT*/
	public final EZDPStringItem              xxCustCtacNmTxt;

    /** XX_CUST_CTAC_TEL_NUM*/
	public final EZDPStringItem              xxCustCtacTelNum;

    /** DTL_NOTE_TXT*/
	public final EZDPStringItem              dtlNoteTxt;

    /** DESC_VAL_TXT_H1*/
	public final EZDPStringItem              descValTxt_H1;

    /** DESC_VAL_TXT_H2*/
	public final EZDPStringItem              descValTxt_H2;

    /** DESC_VAL_TXT_H3*/
	public final EZDPStringItem              descValTxt_H3;

    /** DESC_VAL_TXT_H4*/
	public final EZDPStringItem              descValTxt_H4;

    /** DESC_VAL_TXT_H5*/
	public final EZDPStringItem              descValTxt_H5;

    /** DESC_VAL_TXT_H6*/
	public final EZDPStringItem              descValTxt_H6;

    /** DESC_VAL_TXT_H7*/
	public final EZDPStringItem              descValTxt_H7;

    /** DESC_VAL_TXT_H8*/
	public final EZDPStringItem              descValTxt_H8;

    /** DESC_VAL_TXT_H9*/
	public final EZDPStringItem              descValTxt_H9;

    /** DESC_VAL_TXT_HA*/
	public final EZDPStringItem              descValTxt_HA;

    /** XX_ATT_TP_TXT*/
	public final EZDPStringItem              xxAttTpTxt;

    /** XX_ATT_NM_TXT*/
	public final EZDPStringItem              xxAttNmTxt;

    /** MSTR_ATT_DATA_DESC_TXT*/
	public final EZDPStringItem              mstrAttDataDescTxt;

    /** xxMsgIdList*/
	public final business.parts.NSZC119001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NSZC119001PMsg is constructor.
	 * The initialization when the instance of NSZC119001PMsg is generated.
	 */
	public NSZC119001PMsg() {
		this(false, -1);
	}

	/**
	 * NSZC119001PMsg is constructor.
	 * The initialization when the instance of NSZC119001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC119001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		actDescTxt_H1 = (EZDPStringItem)newItem("actDescTxt_H1");
		actDescTxt_H2 = (EZDPStringItem)newItem("actDescTxt_H2");
		xxCratByTxt = (EZDPStringItem)newItem("xxCratByTxt");
		xxLastUpdByTxt = (EZDPStringItem)newItem("xxLastUpdByTxt");
		xxEmpNmTxt = (EZDPStringItem)newItem("xxEmpNmTxt");
		xxEmpTelNum = (EZDPStringItem)newItem("xxEmpTelNum");
		xxSubCmpyNmTxt = (EZDPStringItem)newItem("xxSubCmpyNmTxt");
		xxDeptNmTxt = (EZDPStringItem)newItem("xxDeptNmTxt");
		xxCustNum = (EZDPStringItem)newItem("xxCustNum");
		xxCustNmTxt = (EZDPStringItem)newItem("xxCustNmTxt");
		xxCustCtacNmTxt = (EZDPStringItem)newItem("xxCustCtacNmTxt");
		xxCustCtacTelNum = (EZDPStringItem)newItem("xxCustCtacTelNum");
		dtlNoteTxt = (EZDPStringItem)newItem("dtlNoteTxt");
		descValTxt_H1 = (EZDPStringItem)newItem("descValTxt_H1");
		descValTxt_H2 = (EZDPStringItem)newItem("descValTxt_H2");
		descValTxt_H3 = (EZDPStringItem)newItem("descValTxt_H3");
		descValTxt_H4 = (EZDPStringItem)newItem("descValTxt_H4");
		descValTxt_H5 = (EZDPStringItem)newItem("descValTxt_H5");
		descValTxt_H6 = (EZDPStringItem)newItem("descValTxt_H6");
		descValTxt_H7 = (EZDPStringItem)newItem("descValTxt_H7");
		descValTxt_H8 = (EZDPStringItem)newItem("descValTxt_H8");
		descValTxt_H9 = (EZDPStringItem)newItem("descValTxt_H9");
		descValTxt_HA = (EZDPStringItem)newItem("descValTxt_HA");
		xxAttTpTxt = (EZDPStringItem)newItem("xxAttTpTxt");
		xxAttNmTxt = (EZDPStringItem)newItem("xxAttNmTxt");
		mstrAttDataDescTxt = (EZDPStringItem)newItem("mstrAttDataDescTxt");
		xxMsgIdList = (business.parts.NSZC119001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC119001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC119001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"actDescTxt_H1", "actDescTxt_H1", "H1", null, TYPE_HANKAKUEISU, "512", null},
	{"actDescTxt_H2", "actDescTxt_H2", "H2", null, TYPE_HANKAKUEISU, "512", null},
	{"xxCratByTxt", "xxCratByTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxLastUpdByTxt", "xxLastUpdByTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	{"xxEmpNmTxt", "xxEmpNmTxt", null, null, TYPE_HANKAKUEISU, "500", null},
	{"xxEmpTelNum", "xxEmpTelNum", null, null, TYPE_HANKAKUEISU, "500", null},
	{"xxSubCmpyNmTxt", "xxSubCmpyNmTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxDeptNmTxt", "xxDeptNmTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxCustNum", "xxCustNum", null, null, TYPE_HANKAKUEISU, "500", null},
	{"xxCustNmTxt", "xxCustNmTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCustCtacNmTxt", "xxCustCtacNmTxt", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"xxCustCtacTelNum", "xxCustCtacTelNum", null, null, TYPE_HANKAKUEISU, "1000", null},
	{"dtlNoteTxt", "dtlNoteTxt", null, null, TYPE_HANKAKUEISU, "7000000", null},
	{"descValTxt_H1", "descValTxt_H1", "H1", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H2", "descValTxt_H2", "H2", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H3", "descValTxt_H3", "H3", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H4", "descValTxt_H4", "H4", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H5", "descValTxt_H5", "H5", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H6", "descValTxt_H6", "H6", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H7", "descValTxt_H7", "H7", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H8", "descValTxt_H8", "H8", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_H9", "descValTxt_H9", "H9", null, TYPE_HANKAKUEISU, "2000", null},
	{"descValTxt_HA", "descValTxt_HA", "HA", null, TYPE_HANKAKUEISU, "2000", null},
	{"xxAttTpTxt", "xxAttTpTxt", null, null, TYPE_HANKAKUEISU, "100", null},
	{"xxAttNmTxt", "xxAttNmTxt", null, null, TYPE_HANKAKUEISU, "300", null},
	{"mstrAttDataDescTxt", "mstrAttDataDescTxt", null, null, TYPE_HANKAKUEISU, "7000000", null},
	{"xxMsgIdList", "xxMsgIdList", null, "100", "business.parts.NSZC119001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"ACT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actDescTxt_H1
        {"ACT_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//actDescTxt_H2
        {"XX_CRAT_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCratByTxt
        {"XX_LAST_UPD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLastUpdByTxt
        {"XX_EMP_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEmpNmTxt
        {"XX_EMP_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxEmpTelNum
        {"XX_SUB_CMPY_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSubCmpyNmTxt
        {"XX_DEPT_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDeptNmTxt
        {"XX_CUST_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustNum
        {"XX_CUST_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustNmTxt
        {"XX_CUST_CTAC_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCtacNmTxt
        {"XX_CUST_CTAC_TEL_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxCustCtacTelNum
        {"DTL_NOTE_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dtlNoteTxt
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H1
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H2
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H3
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H4
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H5
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H6
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H7
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H8
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_H9
        {"DESC_VAL_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//descValTxt_HA
        {"XX_ATT_TP_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAttTpTxt
        {"XX_ATT_NM_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxAttNmTxt
        {"MSTR_ATT_DATA_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mstrAttDataDescTxt
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


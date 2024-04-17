//This file was automatically generated by Text File Layout Definition Document and XLA200710010.
// Generated on    :20150514102131000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :TextFileLayoutDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0530F00FMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.file;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0530F00 File Layout Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0530F00FMsg extends EZDFMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_SLN_SQ_A*/
	public final EZDFBigDecimalItem              svcSlnSq_A;

    /** SVC_SLN_NM_A*/
	public final EZDFStringItem              svcSlnNm_A;

    /** XX_LOC_NM_A1*/
	public final EZDFStringItem              xxLocNm_A1;

    /** XX_LOC_NM_A2*/
	public final EZDFStringItem              xxLocNm_A2;

    /** XX_LOC_NM_A3*/
	public final EZDFStringItem              xxLocNm_A3;

    /** XX_DT_TM_AC*/
	public final EZDFStringItem              xxDtTm_AC;

    /** SVC_SLN_CRAT_PSN_CD_A*/
	public final EZDFStringItem              svcSlnCratPsnCd_A;

    /** XX_DT_TM_AU*/
	public final EZDFStringItem              xxDtTm_AU;


	/**
	 * NSAL0530F00FMsg is constructor.
	 * The initialization when the instance of NSAL0530F00FMsg is generated.
	 */
	public NSAL0530F00FMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0530F00FMsg is constructor.
	 * The initialization when the instance of NSAL0530F00FMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0530F00FMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcSlnSq_A = (EZDFBigDecimalItem)newItem("svcSlnSq_A");
		svcSlnNm_A = (EZDFStringItem)newItem("svcSlnNm_A");
		xxLocNm_A1 = (EZDFStringItem)newItem("xxLocNm_A1");
		xxLocNm_A2 = (EZDFStringItem)newItem("xxLocNm_A2");
		xxLocNm_A3 = (EZDFStringItem)newItem("xxLocNm_A3");
		xxDtTm_AC = (EZDFStringItem)newItem("xxDtTm_AC");
		svcSlnCratPsnCd_A = (EZDFStringItem)newItem("svcSlnCratPsnCd_A");
		xxDtTm_AU = (EZDFStringItem)newItem("xxDtTm_AU");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0530F00FMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0530F00FMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcSlnSq_A", "svcSlnSq_A", "A", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcSlnNm_A", "svcSlnNm_A", "A", null, TYPE_HANKAKUEISU, "100", null},
	{"xxLocNm_A1", "xxLocNm_A1", "A1", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLocNm_A2", "xxLocNm_A2", "A2", null, TYPE_HANKAKUEISU, "60", null},
	{"xxLocNm_A3", "xxLocNm_A3", "A3", null, TYPE_HANKAKUEISU, "60", null},
	{"xxDtTm_AC", "xxDtTm_AC", "AC", null, TYPE_HANKAKUEISU, "23", null},
	{"svcSlnCratPsnCd_A", "svcSlnCratPsnCd_A", "A", null, TYPE_HANKAKUEISU, "32", null},
	{"xxDtTm_AU", "xxDtTm_AU", "AU", null, TYPE_HANKAKUEISU, "23", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_SLN_SQ",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSlnSq_A
        {"SVC_SLN_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSlnNm_A
        {"XX_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocNm_A1
        {"XX_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocNm_A2
        {"XX_LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxLocNm_A3
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AC
        {"SVC_SLN_CRAT_PSN_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSlnCratPsnCd_A
        {"XX_DT_TM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxDtTm_AU
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

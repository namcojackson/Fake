//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20171221112218000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NWZC226001PMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NWZC226001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NWZC226001PMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDPStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDPDateItem              slsDt;

    /** XX_MODE_CD*/
	public final EZDPStringItem              xxModeCd;

    /** DS_IMPT_ORD_PK*/
	public final EZDPBigDecimalItem              dsImptOrdPk;

    /** DS_IMPT_ORD_CONFIG_PK*/
	public final EZDPBigDecimalItem              dsImptOrdConfigPk;

    /** ORD_SRC_IMPT_TS*/
	public final EZDPStringItem              ordSrcImptTs;

    /** CPO_ORD_NUM*/
	public final EZDPStringItem              cpoOrdNum;

    /** DS_CPO_CRAT_TS*/
	public final EZDPStringItem              dsCpoCratTs;

    /** xxMsgPrmList*/
	public final business.parts.NWZC226001_xxMsgPrmListPMsgArray              xxMsgPrmList;

    /** xxMsgIdList*/
	public final business.parts.NWZC226001_xxMsgIdListPMsgArray              xxMsgIdList;


	/**
	 * NWZC226001PMsg is constructor.
	 * The initialization when the instance of NWZC226001PMsg is generated.
	 */
	public NWZC226001PMsg() {
		this(false, -1);
	}

	/**
	 * NWZC226001PMsg is constructor.
	 * The initialization when the instance of NWZC226001PMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NWZC226001PMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDPStringItem)newItem("glblCmpyCd");
		slsDt = (EZDPDateItem)newItem("slsDt");
		xxModeCd = (EZDPStringItem)newItem("xxModeCd");
		dsImptOrdPk = (EZDPBigDecimalItem)newItem("dsImptOrdPk");
		dsImptOrdConfigPk = (EZDPBigDecimalItem)newItem("dsImptOrdConfigPk");
		ordSrcImptTs = (EZDPStringItem)newItem("ordSrcImptTs");
		cpoOrdNum = (EZDPStringItem)newItem("cpoOrdNum");
		dsCpoCratTs = (EZDPStringItem)newItem("dsCpoCratTs");
		xxMsgPrmList = (business.parts.NWZC226001_xxMsgPrmListPMsgArray)newMsgArray("xxMsgPrmList");
		xxMsgIdList = (business.parts.NWZC226001_xxMsgIdListPMsgArray)newMsgArray("xxMsgIdList");
	}

	/**
	 * get the type of array which is stored
	 * @return NWZC226001PMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NWZC226001PMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxModeCd", "xxModeCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"dsImptOrdPk", "dsImptOrdPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsImptOrdConfigPk", "dsImptOrdConfigPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"ordSrcImptTs", "ordSrcImptTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"cpoOrdNum", "cpoOrdNum", null, null, TYPE_HANKAKUEISU, "8", null},
	{"dsCpoCratTs", "dsCpoCratTs", null, null, TYPE_HANKAKUSUJI, "17", null},
	{"xxMsgPrmList", "xxMsgPrmList", null, "200", "business.parts.NWZC226001_xxMsgPrmListPMsgArray", null, null},
	
	{"xxMsgIdList", "xxMsgIdList", null, "200", "business.parts.NWZC226001_xxMsgIdListPMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"XX_MODE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxModeCd
        {"DS_IMPT_ORD_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdPk
        {"DS_IMPT_ORD_CONFIG_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsImptOrdConfigPk
        {"ORD_SRC_IMPT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ordSrcImptTs
        {"CPO_ORD_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//cpoOrdNum
        {"DS_CPO_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCpoCratTs
		null,	//xxMsgPrmList
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


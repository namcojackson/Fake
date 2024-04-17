//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20130904115954000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZPL0030_BBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZPL0030;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZPL0030 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZPL0030_BBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_RPT_CRAT_TM*/
	public final EZDBStringItem              xxRptCratTm;

    /** XX_RPT_TTL_NM*/
	public final EZDBStringItem              xxRptTtlNm;

    /** XX_RPT_FILE_ID*/
	public final EZDBBigDecimalItem              xxRptFileId;


	/**
	 * ZZPL0030_BBMsg is constructor.
	 * The initialization when the instance of ZZPL0030_BBMsg is generated.
	 */
	public ZZPL0030_BBMsg() {
		this(false, -1);
	}

	/**
	 * ZZPL0030_BBMsg is constructor.
	 * The initialization when the instance of ZZPL0030_BBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZPL0030_BBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxRptCratTm = (EZDBStringItem)newItem("xxRptCratTm");
		xxRptTtlNm = (EZDBStringItem)newItem("xxRptTtlNm");
		xxRptFileId = (EZDBBigDecimalItem)newItem("xxRptFileId");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZPL0030_BBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZPL0030_BBMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxRptCratTm", "xxRptCratTm", null, null, TYPE_HANKAKUEISU, "30", null},
	{"xxRptTtlNm", "xxRptTtlNm", null, null, TYPE_HANKAKUEISU, "200", null},
	{"xxRptFileId", "xxRptFileId", null, null, TYPE_SEISU_SYOSU, "20", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_RPT_CRAT_TM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptCratTm
        {"XX_RPT_TTL_NM",  NO,  null,null,"0", NO, NO, NO, NO,"120",null,null, null,  NO,  NO},	//xxRptTtlNm
        {"XX_RPT_FILE_ID",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRptFileId
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


//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20160218170222000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NSAL0770BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NSAL0770;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSAL0770 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NSAL0770BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_TRK_TP_CD*/
	public final EZDBStringItem              dsContrTrkTpCd;

    /** DS_CONTR_PK*/
	public final EZDBBigDecimalItem              dsContrPk;

    /** DS_CONTR_DTL_PK*/
	public final EZDBBigDecimalItem              dsContrDtlPk;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDBBigDecimalItem              dsContrBllgMtrPk;

    /** XX_FROM_DT*/
	public final EZDBDateItem              xxFromDt;

    /** XX_PAGE_SHOW_FROM_NUM*/
	public final EZDBBigDecimalItem              xxPageShowFromNum;

    /** XX_PAGE_SHOW_TO_NUM*/
	public final EZDBBigDecimalItem              xxPageShowToNum;

    /** XX_PAGE_SHOW_OF_NUM*/
	public final EZDBBigDecimalItem              xxPageShowOfNum;

    /** A*/
	public final business.servlet.NSAL0770.NSAL0770_ABMsgArray              A;


	/**
	 * NSAL0770BMsg is constructor.
	 * The initialization when the instance of NSAL0770BMsg is generated.
	 */
	public NSAL0770BMsg() {
		this(false, -1);
	}

	/**
	 * NSAL0770BMsg is constructor.
	 * The initialization when the instance of NSAL0770BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSAL0770BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrTrkTpCd = (EZDBStringItem)newItem("dsContrTrkTpCd");
		dsContrPk = (EZDBBigDecimalItem)newItem("dsContrPk");
		dsContrDtlPk = (EZDBBigDecimalItem)newItem("dsContrDtlPk");
		dsContrBllgMtrPk = (EZDBBigDecimalItem)newItem("dsContrBllgMtrPk");
		xxFromDt = (EZDBDateItem)newItem("xxFromDt");
		xxPageShowFromNum = (EZDBBigDecimalItem)newItem("xxPageShowFromNum");
		xxPageShowToNum = (EZDBBigDecimalItem)newItem("xxPageShowToNum");
		xxPageShowOfNum = (EZDBBigDecimalItem)newItem("xxPageShowOfNum");
		A = (business.servlet.NSAL0770.NSAL0770_ABMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSAL0770BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSAL0770BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrTrkTpCd", "dsContrTrkTpCd", null, null, TYPE_HANKAKUEISU, "1", null},
	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxFromDt", "xxFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"xxPageShowFromNum", "xxPageShowFromNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowToNum", "xxPageShowToNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"xxPageShowOfNum", "xxPageShowOfNum", null, null, TYPE_SEISU_SYOSU, "5", "0"},
	{"A", "A", null, "100", "business.servlet.NSAL0770.NSAL0770_ABMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_TRK_TP_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrTrkTpCd
        {"DS_CONTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_DTL_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"XX_FROM_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,SEP, SEIREKI4, YES,  NO},	//xxFromDt
        {"XX_PAGE_SHOW_FROM_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowFromNum
        {"XX_PAGE_SHOW_TO_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowToNum
        {"XX_PAGE_SHOW_OF_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxPageShowOfNum
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

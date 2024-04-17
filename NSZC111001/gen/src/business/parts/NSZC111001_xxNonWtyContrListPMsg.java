//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20170424202734000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC111001_xxNonWtyContrListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC111001 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC111001_xxNonWtyContrListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CONTR_PK*/
	public final EZDPBigDecimalItem              dsContrPk;

    /** DS_CONTR_NUM*/
	public final EZDPStringItem              dsContrNum;

    /** DS_CONTR_SQ_NUM*/
	public final EZDPStringItem              dsContrSqNum;

    /** DS_CONTR_CATG_CD*/
	public final EZDPStringItem              dsContrCatgCd;

    /** DS_CONTR_DTL_PK*/
	public final EZDPBigDecimalItem              dsContrDtlPk;

    /** SVC_MACH_MSTR_PK*/
	public final EZDPBigDecimalItem              svcMachMstrPk;

    /** CONTR_EFF_FROM_DT*/
	public final EZDPDateItem              contrEffFromDt;

    /** CONTR_EFF_THRU_DT*/
	public final EZDPDateItem              contrEffThruDt;

    /** SVC_PGM_MDSE_CD*/
	public final EZDPStringItem              svcPgmMdseCd;

    /** DS_CONTR_BLLG_MTR_PK*/
	public final EZDPBigDecimalItem              dsContrBllgMtrPk;

    /** BLLG_MTR_LB_CD*/
	public final EZDPStringItem              bllgMtrLbCd;


	/**
	 * NSZC111001_xxNonWtyContrListPMsg is constructor.
	 * The initialization when the instance of NSZC111001_xxNonWtyContrListPMsg is generated.
	 */
	public NSZC111001_xxNonWtyContrListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC111001_xxNonWtyContrListPMsg is constructor.
	 * The initialization when the instance of NSZC111001_xxNonWtyContrListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC111001_xxNonWtyContrListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsContrPk = (EZDPBigDecimalItem)newItem("dsContrPk");
		dsContrNum = (EZDPStringItem)newItem("dsContrNum");
		dsContrSqNum = (EZDPStringItem)newItem("dsContrSqNum");
		dsContrCatgCd = (EZDPStringItem)newItem("dsContrCatgCd");
		dsContrDtlPk = (EZDPBigDecimalItem)newItem("dsContrDtlPk");
		svcMachMstrPk = (EZDPBigDecimalItem)newItem("svcMachMstrPk");
		contrEffFromDt = (EZDPDateItem)newItem("contrEffFromDt");
		contrEffThruDt = (EZDPDateItem)newItem("contrEffThruDt");
		svcPgmMdseCd = (EZDPStringItem)newItem("svcPgmMdseCd");
		dsContrBllgMtrPk = (EZDPBigDecimalItem)newItem("dsContrBllgMtrPk");
		bllgMtrLbCd = (EZDPStringItem)newItem("bllgMtrLbCd");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC111001_xxNonWtyContrListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC111001_xxNonWtyContrListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsContrPk", "dsContrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsContrNum", "dsContrNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"dsContrSqNum", "dsContrSqNum", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrCatgCd", "dsContrCatgCd", null, null, TYPE_HANKAKUEISU, "3", null},
	{"dsContrDtlPk", "dsContrDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"svcMachMstrPk", "svcMachMstrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"contrEffFromDt", "contrEffFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"contrEffThruDt", "contrEffThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcPgmMdseCd", "svcPgmMdseCd", null, null, TYPE_HANKAKUEISU, "16", null},
	{"dsContrBllgMtrPk", "dsContrBllgMtrPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CONTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrPk
        {"DS_CONTR_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrNum
        {"DS_CONTR_SQ_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrSqNum
        {"DS_CONTR_CATG_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrCatgCd
        {"DS_CONTR_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrDtlPk
        {"SVC_MACH_MSTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcMachMstrPk
        {"CONTR_EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffFromDt
        {"CONTR_EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//contrEffThruDt
        {"SVC_PGM_MDSE_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcPgmMdseCd
        {"DS_CONTR_BLLG_MTR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsContrBllgMtrPk
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
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

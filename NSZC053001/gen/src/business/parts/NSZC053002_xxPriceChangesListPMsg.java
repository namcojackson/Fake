//This file was automatically generated by Common Business Component Interface Definition Document and XLA200710010.
// Generated on    :20220429144017000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :CommonBusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSZC053002_xxPriceChangesListPMsg.java  Copyright  FUJITSU LIMITED 2007
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
 * It is NSZC053002 Common Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSZC053002_xxPriceChangesListPMsg extends EZDPMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** SVC_CR_REBIL_DTL_PK*/
	public final EZDPBigDecimalItem              svcCrRebilDtlPk;

    /** ORIG_SVC_INV_NUM*/
	public final EZDPStringItem              origSvcInvNum;

    /** SER_NUM*/
	public final EZDPStringItem              serNum;

    /** BLLG_MTR_LB_CD*/
	public final EZDPStringItem              bllgMtrLbCd;

    /** MTR_BLLG_FROM_DT*/
	public final EZDPDateItem              mtrBllgFromDt;

    /** MTR_BLLG_THRU_DT*/
	public final EZDPDateItem              mtrBllgThruDt;

    /** OLD_XS_COPY_QTY*/
	public final EZDPBigDecimalItem              oldXsCopyQty;

    /** OLD_XS_MTR_AMT_RATE*/
	public final EZDPBigDecimalItem              oldXsMtrAmtRate;

    /** NEW_XS_COPY_QTY*/
	public final EZDPBigDecimalItem              newXsCopyQty;

    /** NEW_XS_MTR_AMT_RATE*/
	public final EZDPBigDecimalItem              newXsMtrAmtRate;

    /** OLD_UNIT_XS_COPY_QTY*/
	public final EZDPBigDecimalItem              oldUnitXsCopyQty;

    /** NEW_UNIT_XS_COPY_QTY*/
	public final EZDPBigDecimalItem              newUnitXsCopyQty;


	/**
	 * NSZC053002_xxPriceChangesListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxPriceChangesListPMsg is generated.
	 */
	public NSZC053002_xxPriceChangesListPMsg() {
		this(false, -1);
	}

	/**
	 * NSZC053002_xxPriceChangesListPMsg is constructor.
	 * The initialization when the instance of NSZC053002_xxPriceChangesListPMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSZC053002_xxPriceChangesListPMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		svcCrRebilDtlPk = (EZDPBigDecimalItem)newItem("svcCrRebilDtlPk");
		origSvcInvNum = (EZDPStringItem)newItem("origSvcInvNum");
		serNum = (EZDPStringItem)newItem("serNum");
		bllgMtrLbCd = (EZDPStringItem)newItem("bllgMtrLbCd");
		mtrBllgFromDt = (EZDPDateItem)newItem("mtrBllgFromDt");
		mtrBllgThruDt = (EZDPDateItem)newItem("mtrBllgThruDt");
		oldXsCopyQty = (EZDPBigDecimalItem)newItem("oldXsCopyQty");
		oldXsMtrAmtRate = (EZDPBigDecimalItem)newItem("oldXsMtrAmtRate");
		newXsCopyQty = (EZDPBigDecimalItem)newItem("newXsCopyQty");
		newXsMtrAmtRate = (EZDPBigDecimalItem)newItem("newXsMtrAmtRate");
		oldUnitXsCopyQty = (EZDPBigDecimalItem)newItem("oldUnitXsCopyQty");
		newUnitXsCopyQty = (EZDPBigDecimalItem)newItem("newUnitXsCopyQty");
	}

	/**
	 * get the type of array which is stored
	 * @return NSZC053002_xxPriceChangesListPMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSZC053002_xxPriceChangesListPMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"svcCrRebilDtlPk", "svcCrRebilDtlPk", null, null, TYPE_SEISU_SYOSU, "28", "0"},
	{"origSvcInvNum", "origSvcInvNum", null, null, TYPE_HANKAKUEISU, "13", null},
	{"serNum", "serNum", null, null, TYPE_HANKAKUEISU, "30", null},
	{"bllgMtrLbCd", "bllgMtrLbCd", null, null, TYPE_HANKAKUEISU, "2", null},
	{"mtrBllgFromDt", "mtrBllgFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"mtrBllgThruDt", "mtrBllgThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"oldXsCopyQty", "oldXsCopyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"oldXsMtrAmtRate", "oldXsMtrAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"newXsCopyQty", "newXsCopyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newXsMtrAmtRate", "newXsMtrAmtRate", null, null, TYPE_SEISU_SYOSU, "19", "6"},
	{"oldUnitXsCopyQty", "oldUnitXsCopyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"newUnitXsCopyQty", "newUnitXsCopyQty", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"SVC_CR_REBIL_DTL_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcCrRebilDtlPk
        {"ORIG_SVC_INV_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//origSvcInvNum
        {"SER_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//serNum
        {"BLLG_MTR_LB_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//bllgMtrLbCd
        {"MTR_BLLG_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgFromDt
        {"MTR_BLLG_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//mtrBllgThruDt
        {"OLD_XS_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldXsCopyQty
        {"OLD_XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldXsMtrAmtRate
        {"NEW_XS_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newXsCopyQty
        {"NEW_XS_MTR_AMT_RATE",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newXsMtrAmtRate
        {"OLD_UNIT_XS_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//oldUnitXsCopyQty
        {"NEW_UNIT_XS_COPY_QTY",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//newUnitXsCopyQty
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

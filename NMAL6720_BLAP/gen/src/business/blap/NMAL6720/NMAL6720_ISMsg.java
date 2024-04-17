//This file was automatically generated by Business Component Interface Definition Document (Define Business Application Global Area) and XLA200710010.
// Generated on    :20231108093943000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_ISMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Business Application Global Area Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_ISMsg extends EZDSMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CUST_SHPG_DEF_PK_I1*/
	public final EZDSBigDecimalItem              dsCustShpgDefPk_I1;

    /** DS_ACCT_CARR_PK_I1*/
	public final EZDSBigDecimalItem              dsAcctCarrPk_I1;

    /** XX_CHK_BOX_I1*/
	public final EZDSStringItem              xxChkBox_I1;

    /** XX_CHK_BOX_ID*/
	public final EZDSStringItem              xxChkBox_ID;

    /** LINE_BIZ_TP_CD_P3*/
	public final EZDSStringItem              lineBizTpCd_P3;

    /** DS_BIZ_AREA_CD_P2*/
	public final EZDSStringItem              dsBizAreaCd_P2;

    /** FRT_COND_CD_I1*/
	public final EZDSStringItemArray              frtCondCd_I1;

    /** FRT_COND_NM_I1*/
	public final EZDSStringItemArray              frtCondNm_I1;

    /** FRT_COND_CD_P1*/
	public final EZDSStringItem              frtCondCd_P1;

    /** SHPG_SVC_LVL_CD_I1*/
	public final EZDSStringItemArray              shpgSvcLvlCd_I1;

    /** SHPG_SVC_LVL_NM_I1*/
	public final EZDSStringItemArray              shpgSvcLvlNm_I1;

    /** SHPG_SVC_LVL_CD_P1*/
	public final EZDSStringItem              shpgSvcLvlCd_P1;

    /** VND_CD_I1*/
	public final EZDSStringItemArray              vndCd_I1;

    /** LOC_NM_I2*/
	public final EZDSStringItemArray              locNm_I2;

    /** VND_CD_I3*/
	public final EZDSStringItem              vndCd_I3;

    /** CARR_NM_I3*/
	public final EZDSStringItem              carrNm_I3;

    /** DS_CARR_ACCT_NUM_I1*/
	public final EZDSStringItem              dsCarrAcctNum_I1;

    /** EFF_FROM_DT_I1*/
	public final EZDSDateItem              effFromDt_I1;

    /** EFF_THRU_DT_I1*/
	public final EZDSDateItem              effThruDt_I1;

    /** _EZUpdateDateTime_IS*/
	public final EZDSStringItem              ezUpTime_IS;

    /** _EZUpTimeZone_IS*/
	public final EZDSStringItem              ezUpTimeZone_IS;

    /** _EZUpdateDateTime_IC*/
	public final EZDSStringItem              ezUpTime_IC;

    /** _EZUpTimeZone_IC*/
	public final EZDSStringItem              ezUpTimeZone_IC;

    /** XX_REC_HIST_CRAT_TS_I1*/
	public final EZDSStringItem              xxRecHistCratTs_I1;

    /** XX_REC_HIST_CRAT_BY_NM_I1*/
	public final EZDSStringItem              xxRecHistCratByNm_I1;

    /** XX_REC_HIST_UPD_TS_I1*/
	public final EZDSStringItem              xxRecHistUpdTs_I1;

    /** XX_REC_HIST_UPD_BY_NM_I1*/
	public final EZDSStringItem              xxRecHistUpdByNm_I1;

    /** XX_REC_HIST_TBL_NM_I1*/
	public final EZDSStringItem              xxRecHistTblNm_I1;


	/**
	 * NMAL6720_ISMsg is constructor.
	 * The initialization when the instance of NMAL6720_ISMsg is generated.
	 */
	public NMAL6720_ISMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_ISMsg is constructor.
	 * The initialization when the instance of NMAL6720_ISMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_ISMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCustShpgDefPk_I1 = (EZDSBigDecimalItem)newItem("dsCustShpgDefPk_I1");
		dsAcctCarrPk_I1 = (EZDSBigDecimalItem)newItem("dsAcctCarrPk_I1");
		xxChkBox_I1 = (EZDSStringItem)newItem("xxChkBox_I1");
		xxChkBox_ID = (EZDSStringItem)newItem("xxChkBox_ID");
		lineBizTpCd_P3 = (EZDSStringItem)newItem("lineBizTpCd_P3");
		dsBizAreaCd_P2 = (EZDSStringItem)newItem("dsBizAreaCd_P2");
		frtCondCd_I1 = (EZDSStringItemArray)newItemArray("frtCondCd_I1");
		frtCondNm_I1 = (EZDSStringItemArray)newItemArray("frtCondNm_I1");
		frtCondCd_P1 = (EZDSStringItem)newItem("frtCondCd_P1");
		shpgSvcLvlCd_I1 = (EZDSStringItemArray)newItemArray("shpgSvcLvlCd_I1");
		shpgSvcLvlNm_I1 = (EZDSStringItemArray)newItemArray("shpgSvcLvlNm_I1");
		shpgSvcLvlCd_P1 = (EZDSStringItem)newItem("shpgSvcLvlCd_P1");
		vndCd_I1 = (EZDSStringItemArray)newItemArray("vndCd_I1");
		locNm_I2 = (EZDSStringItemArray)newItemArray("locNm_I2");
		vndCd_I3 = (EZDSStringItem)newItem("vndCd_I3");
		carrNm_I3 = (EZDSStringItem)newItem("carrNm_I3");
		dsCarrAcctNum_I1 = (EZDSStringItem)newItem("dsCarrAcctNum_I1");
		effFromDt_I1 = (EZDSDateItem)newItem("effFromDt_I1");
		effThruDt_I1 = (EZDSDateItem)newItem("effThruDt_I1");
		ezUpTime_IS = (EZDSStringItem)newItem("ezUpTime_IS");
		ezUpTimeZone_IS = (EZDSStringItem)newItem("ezUpTimeZone_IS");
		ezUpTime_IC = (EZDSStringItem)newItem("ezUpTime_IC");
		ezUpTimeZone_IC = (EZDSStringItem)newItem("ezUpTimeZone_IC");
		xxRecHistCratTs_I1 = (EZDSStringItem)newItem("xxRecHistCratTs_I1");
		xxRecHistCratByNm_I1 = (EZDSStringItem)newItem("xxRecHistCratByNm_I1");
		xxRecHistUpdTs_I1 = (EZDSStringItem)newItem("xxRecHistUpdTs_I1");
		xxRecHistUpdByNm_I1 = (EZDSStringItem)newItem("xxRecHistUpdByNm_I1");
		xxRecHistTblNm_I1 = (EZDSStringItem)newItem("xxRecHistTblNm_I1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_ISMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_ISMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"dsCustShpgDefPk_I1", "dsCustShpgDefPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"dsAcctCarrPk_I1", "dsAcctCarrPk_I1", "I1", null, TYPE_SEISU_SYOSU, "28", "0"},
	{"xxChkBox_I1", "xxChkBox_I1", "I1", null, TYPE_HANKAKUEIJI, "1", null},
	{"xxChkBox_ID", "xxChkBox_ID", "ID", null, TYPE_HANKAKUEIJI, "1", null},
	{"lineBizTpCd_P3", "lineBizTpCd_P3", "P3", null, TYPE_HANKAKUEISU, "8", null},
	{"dsBizAreaCd_P2", "dsBizAreaCd_P2", "P2", null, TYPE_HANKAKUEISU, "2", null},
	{"frtCondCd_I1", "frtCondCd_I1", "I1", "99", TYPE_HANKAKUEISU, "3", null},
	{"frtCondNm_I1", "frtCondNm_I1", "I1", "99", TYPE_HANKAKUEISU, "30", null},
	{"frtCondCd_P1", "frtCondCd_P1", "P1", null, TYPE_HANKAKUEISU, "3", null},
	{"shpgSvcLvlCd_I1", "shpgSvcLvlCd_I1", "I1", "99", TYPE_HANKAKUEISU, "2", null},
	{"shpgSvcLvlNm_I1", "shpgSvcLvlNm_I1", "I1", "99", TYPE_HANKAKUEISU, "20", null},
	{"shpgSvcLvlCd_P1", "shpgSvcLvlCd_P1", "P1", null, TYPE_HANKAKUEISU, "2", null},
	{"vndCd_I1", "vndCd_I1", "I1", "200", TYPE_HANKAKUEISU, "20", null},
	{"locNm_I2", "locNm_I2", "I2", "200", TYPE_HANKAKUEISU, "60", null},
	{"vndCd_I3", "vndCd_I3", "I3", null, TYPE_HANKAKUEISU, "20", null},
	{"carrNm_I3", "carrNm_I3", "I3", null, TYPE_HANKAKUEISU, "60", null},
	{"dsCarrAcctNum_I1", "dsCarrAcctNum_I1", "I1", null, TYPE_HANKAKUEISU, "20", null},
	{"effFromDt_I1", "effFromDt_I1", "I1", null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt_I1", "effThruDt_I1", "I1", null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime_IS", "ezUpTime_IS", "IS", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_IS", "ezUpTimeZone_IS", "IS", null, TYPE_HANKAKUEISU, "5", null},
	{"ezUpTime_IC", "ezUpTime_IC", "IC", null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone_IC", "ezUpTimeZone_IC", "IC", null, TYPE_HANKAKUEISU, "5", null},
	{"xxRecHistCratTs_I1", "xxRecHistCratTs_I1", "I1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistCratByNm_I1", "xxRecHistCratByNm_I1", "I1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistUpdTs_I1", "xxRecHistUpdTs_I1", "I1", null, TYPE_HANKAKUSUJI, "17", null},
	{"xxRecHistUpdByNm_I1", "xxRecHistUpdByNm_I1", "I1", null, TYPE_HANKAKUEISU, "150", null},
	{"xxRecHistTblNm_I1", "xxRecHistTblNm_I1", "I1", null, TYPE_HANKAKUEISU, "60", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"DS_CUST_SHPG_DEF_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustShpgDefPk_I1
        {"DS_ACCT_CARR_PK",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCarrPk_I1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_I1
        {"XX_CHK_BOX",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_ID
        {"LINE_BIZ_TP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_P3
        {"DS_BIZ_AREA_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_P2
        {"FRT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd_I1
        {"FRT_COND_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondNm_I1
        {"FRT_COND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd_P1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_I1
        {"SHPG_SVC_LVL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlNm_I1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_P1
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_I1
        {"LOC_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_I2
        {"VND_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_I3
        {"CARR_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_I3
        {"DS_CARR_ACCT_NUM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCarrAcctNum_I1
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt_I1
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt_I1
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IS
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IS
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IC
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IC
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_I1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_I1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_I1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_I1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_I1
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

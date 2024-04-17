//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20231108082939000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *NMAL6720_IBMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.NMAL6720;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NMAL6720 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class NMAL6720_IBMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** DS_CUST_SHPG_DEF_PK_I1*/
	public final EZDBBigDecimalItem              dsCustShpgDefPk_I1;

    /** DS_ACCT_CARR_PK_I1*/
	public final EZDBBigDecimalItem              dsAcctCarrPk_I1;

    /** XX_CHK_BOX_I1*/
	public final EZDBStringItem              xxChkBox_I1;

    /** XX_CHK_BOX_ID*/
	public final EZDBStringItem              xxChkBox_ID;

    /** LINE_BIZ_TP_CD_P3*/
	public final EZDBStringItem              lineBizTpCd_P3;

    /** DS_BIZ_AREA_CD_P2*/
	public final EZDBStringItem              dsBizAreaCd_P2;

    /** FRT_COND_CD_I1*/
	public final EZDBStringItemArray              frtCondCd_I1;

    /** FRT_COND_NM_I1*/
	public final EZDBStringItemArray              frtCondNm_I1;

    /** FRT_COND_CD_P1*/
	public final EZDBStringItem              frtCondCd_P1;

    /** SHPG_SVC_LVL_CD_I1*/
	public final EZDBStringItemArray              shpgSvcLvlCd_I1;

    /** SHPG_SVC_LVL_NM_I1*/
	public final EZDBStringItemArray              shpgSvcLvlNm_I1;

    /** SHPG_SVC_LVL_CD_P1*/
	public final EZDBStringItem              shpgSvcLvlCd_P1;

    /** VND_CD_I1*/
	public final EZDBStringItemArray              vndCd_I1;

    /** LOC_NM_I2*/
	public final EZDBStringItemArray              locNm_I2;

    /** VND_CD_I3*/
	public final EZDBStringItem              vndCd_I3;

    /** CARR_NM_I3*/
	public final EZDBStringItem              carrNm_I3;

    /** DS_CARR_ACCT_NUM_I1*/
	public final EZDBStringItem              dsCarrAcctNum_I1;

    /** EFF_FROM_DT_I1*/
	public final EZDBDateItem              effFromDt_I1;

    /** EFF_THRU_DT_I1*/
	public final EZDBDateItem              effThruDt_I1;

    /** _EZUpdateDateTime_IS*/
	public final EZDBStringItem              ezUpTime_IS;

    /** _EZUpTimeZone_IS*/
	public final EZDBStringItem              ezUpTimeZone_IS;

    /** _EZUpdateDateTime_IC*/
	public final EZDBStringItem              ezUpTime_IC;

    /** _EZUpTimeZone_IC*/
	public final EZDBStringItem              ezUpTimeZone_IC;

    /** XX_REC_HIST_CRAT_TS_I1*/
	public final EZDBStringItem              xxRecHistCratTs_I1;

    /** XX_REC_HIST_CRAT_BY_NM_I1*/
	public final EZDBStringItem              xxRecHistCratByNm_I1;

    /** XX_REC_HIST_UPD_TS_I1*/
	public final EZDBStringItem              xxRecHistUpdTs_I1;

    /** XX_REC_HIST_UPD_BY_NM_I1*/
	public final EZDBStringItem              xxRecHistUpdByNm_I1;

    /** XX_REC_HIST_TBL_NM_I1*/
	public final EZDBStringItem              xxRecHistTblNm_I1;


	/**
	 * NMAL6720_IBMsg is constructor.
	 * The initialization when the instance of NMAL6720_IBMsg is generated.
	 */
	public NMAL6720_IBMsg() {
		this(false, -1);
	}

	/**
	 * NMAL6720_IBMsg is constructor.
	 * The initialization when the instance of NMAL6720_IBMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NMAL6720_IBMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		dsCustShpgDefPk_I1 = (EZDBBigDecimalItem)newItem("dsCustShpgDefPk_I1");
		dsAcctCarrPk_I1 = (EZDBBigDecimalItem)newItem("dsAcctCarrPk_I1");
		xxChkBox_I1 = (EZDBStringItem)newItem("xxChkBox_I1");
		xxChkBox_ID = (EZDBStringItem)newItem("xxChkBox_ID");
		lineBizTpCd_P3 = (EZDBStringItem)newItem("lineBizTpCd_P3");
		dsBizAreaCd_P2 = (EZDBStringItem)newItem("dsBizAreaCd_P2");
		frtCondCd_I1 = (EZDBStringItemArray)newItemArray("frtCondCd_I1");
		frtCondNm_I1 = (EZDBStringItemArray)newItemArray("frtCondNm_I1");
		frtCondCd_P1 = (EZDBStringItem)newItem("frtCondCd_P1");
		shpgSvcLvlCd_I1 = (EZDBStringItemArray)newItemArray("shpgSvcLvlCd_I1");
		shpgSvcLvlNm_I1 = (EZDBStringItemArray)newItemArray("shpgSvcLvlNm_I1");
		shpgSvcLvlCd_P1 = (EZDBStringItem)newItem("shpgSvcLvlCd_P1");
		vndCd_I1 = (EZDBStringItemArray)newItemArray("vndCd_I1");
		locNm_I2 = (EZDBStringItemArray)newItemArray("locNm_I2");
		vndCd_I3 = (EZDBStringItem)newItem("vndCd_I3");
		carrNm_I3 = (EZDBStringItem)newItem("carrNm_I3");
		dsCarrAcctNum_I1 = (EZDBStringItem)newItem("dsCarrAcctNum_I1");
		effFromDt_I1 = (EZDBDateItem)newItem("effFromDt_I1");
		effThruDt_I1 = (EZDBDateItem)newItem("effThruDt_I1");
		ezUpTime_IS = (EZDBStringItem)newItem("ezUpTime_IS");
		ezUpTimeZone_IS = (EZDBStringItem)newItem("ezUpTimeZone_IS");
		ezUpTime_IC = (EZDBStringItem)newItem("ezUpTime_IC");
		ezUpTimeZone_IC = (EZDBStringItem)newItem("ezUpTimeZone_IC");
		xxRecHistCratTs_I1 = (EZDBStringItem)newItem("xxRecHistCratTs_I1");
		xxRecHistCratByNm_I1 = (EZDBStringItem)newItem("xxRecHistCratByNm_I1");
		xxRecHistUpdTs_I1 = (EZDBStringItem)newItem("xxRecHistUpdTs_I1");
		xxRecHistUpdByNm_I1 = (EZDBStringItem)newItem("xxRecHistUpdByNm_I1");
		xxRecHistTblNm_I1 = (EZDBStringItem)newItem("xxRecHistTblNm_I1");
	}

	/**
	 * get the type of array which is stored
	 * @return NMAL6720_IBMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NMAL6720_IBMsgArray();
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
	{"vndCd_I1", "vndCd_I1", "I1", "99", TYPE_HANKAKUEISU, "20", null},
	{"locNm_I2", "locNm_I2", "I2", "99", TYPE_HANKAKUEISU, "60", null},
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

        {"DS_CUST_SHPG_DEF_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCustShpgDefPk_I1
        {"DS_ACCT_CARR_PK",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsAcctCarrPk_I1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_I1
        {"XX_CHK_BOX",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxChkBox_ID
        {"LINE_BIZ_TP_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//lineBizTpCd_P3
        {"DS_BIZ_AREA_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsBizAreaCd_P2
        {"FRT_COND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd_I1
        {"FRT_COND_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondNm_I1
        {"FRT_COND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//frtCondCd_P1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_I1
        {"SHPG_SVC_LVL_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlNm_I1
        {"SHPG_SVC_LVL_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//shpgSvcLvlCd_P1
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_I1
        {"LOC_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//locNm_I2
        {"VND_CD",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//vndCd_I3
        {"CARR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//carrNm_I3
        {"DS_CARR_ACCT_NUM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//dsCarrAcctNum_I1
        {"EFF_FROM_DT", YES,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effFromDt_I1
        {"EFF_THRU_DT",  NO,  null,null,"1", NO, NO, NO, NO,null,null,UNI, SEIREKI4,  NO, YES},	//effThruDt_I1
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IS
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IS
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime_IC
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone_IC
        {"XX_REC_HIST_CRAT_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratTs_I1
        {"XX_REC_HIST_CRAT_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistCratByNm_I1
        {"XX_REC_HIST_UPD_TS",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdTs_I1
        {"XX_REC_HIST_UPD_BY_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistUpdByNm_I1
        {"XX_REC_HIST_TBL_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxRecHistTblNm_I1
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

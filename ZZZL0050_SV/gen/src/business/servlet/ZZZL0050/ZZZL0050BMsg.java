//This file was automatically generated by Screen Field Definition Document and XLA200710010.
// Generated on    :20110107161238000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :ScreenFieldDefinitionDocument_AddIn_XLS200710010.xla XLA200710010
/*
 *ZZZL0050BMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.servlet.ZZZL0050;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is ZZZL0050 Screen Data Message class.
 * @author
 * @version XLA200710010
 */
public class ZZZL0050BMsg extends EZDBMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** XX_SCR_NM*/
	public final EZDBStringItem              xxScrNm;

    /** GLBL_CMPY_CD*/
	public final EZDBStringItem              glblCmpyCd;

    /** JVM_NM_C*/
	public final EZDBStringItemArray              jvmNm_C;

    /** JVM_NM_D*/
	public final EZDBStringItemArray              jvmNm_D;

    /** JVM_NM_S*/
	public final EZDBStringItem              jvmNm_S;

    /** CUR_PLD_QUEUE_NUM*/
	public final EZDBBigDecimalItem              curPldQueueNum;

    /** PLNG_INTVL_SCD*/
	public final EZDBStringItem              plngIntvlScd;

    /** START_THRD_NUM*/
	public final EZDBBigDecimalItem              startThrdNum;

    /** MAX_QUEUE_NUM*/
	public final EZDBStringItem              maxQueueNum;

    /** ONL_PROC_ACTV_FLG_C*/
	public final EZDBStringItemArray              onlProcActvFlg_C;

    /** ONL_PROC_ACTV_FLG_D*/
	public final EZDBStringItemArray              onlProcActvFlg_D;

    /** ONL_PROC_ACTV_FLG_S*/
	public final EZDBStringItem              onlProcActvFlg_S;

    /** _EZUpdateDateTime*/
	public final EZDBStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDBStringItem              ezUpTimeZone;


	/**
	 * ZZZL0050BMsg is constructor.
	 * The initialization when the instance of ZZZL0050BMsg is generated.
	 */
	public ZZZL0050BMsg() {
		this(false, -1);
	}

	/**
	 * ZZZL0050BMsg is constructor.
	 * The initialization when the instance of ZZZL0050BMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public ZZZL0050BMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		xxScrNm = (EZDBStringItem)newItem("xxScrNm");
		glblCmpyCd = (EZDBStringItem)newItem("glblCmpyCd");
		jvmNm_C = (EZDBStringItemArray)newItemArray("jvmNm_C");
		jvmNm_D = (EZDBStringItemArray)newItemArray("jvmNm_D");
		jvmNm_S = (EZDBStringItem)newItem("jvmNm_S");
		curPldQueueNum = (EZDBBigDecimalItem)newItem("curPldQueueNum");
		plngIntvlScd = (EZDBStringItem)newItem("plngIntvlScd");
		startThrdNum = (EZDBBigDecimalItem)newItem("startThrdNum");
		maxQueueNum = (EZDBStringItem)newItem("maxQueueNum");
		onlProcActvFlg_C = (EZDBStringItemArray)newItemArray("onlProcActvFlg_C");
		onlProcActvFlg_D = (EZDBStringItemArray)newItemArray("onlProcActvFlg_D");
		onlProcActvFlg_S = (EZDBStringItem)newItem("onlProcActvFlg_S");
		ezUpTime = (EZDBStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDBStringItem)newItem("ezUpTimeZone");
	}

	/**
	 * get the type of array which is stored
	 * @return ZZZL0050BMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new ZZZL0050BMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"xxScrNm", "xxScrNm", null, null, TYPE_HANKAKUEISU, "70", null},
	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"jvmNm_C", "jvmNm_C", "C", "20", TYPE_HANKAKUEISU, "20", null},
	{"jvmNm_D", "jvmNm_D", "D", "20", TYPE_HANKAKUEISU, "20", null},
	{"jvmNm_S", "jvmNm_S", "S", null, TYPE_HANKAKUEISU, "20", null},
	{"curPldQueueNum", "curPldQueueNum", null, null, TYPE_SEISU_SYOSU, "10", "0"},
	{"plngIntvlScd", "plngIntvlScd", null, null, TYPE_HANKAKUSUJI, "3", null},
	{"startThrdNum", "startThrdNum", null, null, TYPE_SEISU_SYOSU, "3", "0"},
	{"maxQueueNum", "maxQueueNum", null, null, TYPE_HANKAKUEISU, "10", null},
	{"onlProcActvFlg_C", "onlProcActvFlg_C", "C", "2", TYPE_HANKAKUEISU, "1", null},
	{"onlProcActvFlg_D", "onlProcActvFlg_D", "D", "2", TYPE_HANKAKUEISU, "1", null},
	{"onlProcActvFlg_S", "onlProcActvFlg_S", "S", null, TYPE_HANKAKUEISU, "1", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"XX_SCR_NM",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxScrNm
        {"GLBL_CMPY_CD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"JVM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_C
        {"JVM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_D
        {"JVM_NM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//jvmNm_S
        {"CUR_PLD_QUEUE_NUM",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//curPldQueueNum
        {"PLNG_INTVL_SCD", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//plngIntvlScd
        {"START_THRD_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//startThrdNum
        {"MAX_QUEUE_NUM", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//maxQueueNum
        {"ONL_PROC_ACTV_FLG",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//onlProcActvFlg_C
        {"ONL_PROC_ACTV_FLG",  NO,  null,null,"0", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//onlProcActvFlg_D
        {"ONL_PROC_ACTV_FLG", YES,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//onlProcActvFlg_S
        {"_EZUpdateDateTime",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,"1", NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
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


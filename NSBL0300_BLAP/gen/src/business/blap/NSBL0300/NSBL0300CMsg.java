//This file was automatically generated by Business Component Interface Definition Document (Business Component Interface Message Data Definition) and XLA200710010.
// Generated on    :20161206104714000
// Velocity macro:EZDMsg.vm V200607010
// Excel Add-in :BusinessComponentInterfaceDefinitionDocumentAddIn_XLS200710010.xla XLA200710010
/*
 *NSBL0300CMsg.java  Copyright  FUJITSU LIMITED 2007
 *Outline :
 *    1.
 *Release:
 *    No.  Date  Ver  Updater  Content
 *    1
 *
 */
package business.blap.NSBL0300;

import parts.common.*;
import parts.common.EZDSchemaItemDefines;

/**
 * It is NSBL0300 Business Component Interface Message class.
 * @author
 * @version XLA200710010
 */
public class NSBL0300CMsg extends EZDCMsg implements EZDSchemaItemDefines {

	// Serial Version UID
	private static final long serialVersionUID = 1L;

	// Data Definition
    /** GLBL_CMPY_CD*/
	public final EZDCStringItem              glblCmpyCd;

    /** SLS_DT*/
	public final EZDCDateItem              slsDt;

    /** SVC_SKILL_LVL_GRP_CD_H1*/
	public final EZDCStringItemArray              svcSkillLvlGrpCd_H1;

    /** SVC_SKILL_LVL_GRP_NM_H2*/
	public final EZDCStringItemArray              svcSkillLvlGrpNm_H2;

    /** SVC_SKILL_LVL_GRP_CD_H3*/
	public final EZDCStringItem              svcSkillLvlGrpCd_H3;

    /** SVC_SKILL_LVL_GRP_DESC_TXT*/
	public final EZDCStringItem              svcSkillLvlGrpDescTxt;

    /** EFF_FROM_DT*/
	public final EZDCDateItem              effFromDt;

    /** EFF_THRU_DT*/
	public final EZDCDateItem              effThruDt;

    /** _EZUpdateDateTime*/
	public final EZDCStringItem              ezUpTime;

    /** _EZUpTimeZone*/
	public final EZDCStringItem              ezUpTimeZone;

    /** PROC_FLG*/
	public final EZDCStringItem              procFlg;

    /** XX_SORT_TBL_NM*/
	public final EZDCStringItem              xxSortTblNm;

    /** XX_SORT_ITEM_NM*/
	public final EZDCStringItem              xxSortItemNm;

    /** XX_SORT_ORD_BY_TXT*/
	public final EZDCStringItem              xxSortOrdByTxt;

    /** A*/
	public final business.blap.NSBL0300.NSBL0300_ACMsgArray              A;


	/**
	 * NSBL0300CMsg is constructor.
	 * The initialization when the instance of NSBL0300CMsg is generated.
	 */
	public NSBL0300CMsg() {
		this(false, -1);
	}

	/**
	 * NSBL0300CMsg is constructor.
	 * The initialization when the instance of NSBL0300CMsg is generated.
	 * @param child  Flag whether it is child message
	 * @param eleNo  Index Number of array
	 */
	public NSBL0300CMsg(boolean child, int eleNo) {
		super(child, eleNo);

		// Initialization of item

		glblCmpyCd = (EZDCStringItem)newItem("glblCmpyCd");
		slsDt = (EZDCDateItem)newItem("slsDt");
		svcSkillLvlGrpCd_H1 = (EZDCStringItemArray)newItemArray("svcSkillLvlGrpCd_H1");
		svcSkillLvlGrpNm_H2 = (EZDCStringItemArray)newItemArray("svcSkillLvlGrpNm_H2");
		svcSkillLvlGrpCd_H3 = (EZDCStringItem)newItem("svcSkillLvlGrpCd_H3");
		svcSkillLvlGrpDescTxt = (EZDCStringItem)newItem("svcSkillLvlGrpDescTxt");
		effFromDt = (EZDCDateItem)newItem("effFromDt");
		effThruDt = (EZDCDateItem)newItem("effThruDt");
		ezUpTime = (EZDCStringItem)newItem("ezUpTime");
		ezUpTimeZone = (EZDCStringItem)newItem("ezUpTimeZone");
		procFlg = (EZDCStringItem)newItem("procFlg");
		xxSortTblNm = (EZDCStringItem)newItem("xxSortTblNm");
		xxSortItemNm = (EZDCStringItem)newItem("xxSortItemNm");
		xxSortOrdByTxt = (EZDCStringItem)newItem("xxSortOrdByTxt");
		A = (business.blap.NSBL0300.NSBL0300_ACMsgArray)newMsgArray("A");
	}

	/**
	 * get the type of array which is stored
	 * @return NSBL0300CMsgArray
	 */
	public EZDMsgArray getMyArray() {
		return new NSBL0300CMsgArray();
	}


	/**
	 * Array of schema data(Basic data)
	 */
	private static final String[][] BASE_CONTENTS = {

	{"glblCmpyCd", "glblCmpyCd", null, null, TYPE_HANKAKUEISU, "4", null},
	{"slsDt", "slsDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"svcSkillLvlGrpCd_H1", "svcSkillLvlGrpCd_H1", "H1", "99", TYPE_HANKAKUEISU, "2", null},
	{"svcSkillLvlGrpNm_H2", "svcSkillLvlGrpNm_H2", "H2", "99", TYPE_HANKAKUEISU, "30", null},
	{"svcSkillLvlGrpCd_H3", "svcSkillLvlGrpCd_H3", "H3", null, TYPE_HANKAKUEISU, "2", null},
	{"svcSkillLvlGrpDescTxt", "svcSkillLvlGrpDescTxt", null, null, TYPE_HANKAKUEISU, "50", null},
	{"effFromDt", "effFromDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"effThruDt", "effThruDt", null, null, TYPE_NENTSUKIHI, "8", null},
	{"ezUpTime", "ezUpTime", null, null, TYPE_HANKAKUEISU, "17", null},
	{"ezUpTimeZone", "ezUpTimeZone", null, null, TYPE_HANKAKUEISU, "5", null},
	{"procFlg", "procFlg", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortTblNm", "xxSortTblNm", null, null, TYPE_HANKAKUEISU, "1", null},
	{"xxSortItemNm", "xxSortItemNm", null, null, TYPE_HANKAKUEISU, "50", null},
	{"xxSortOrdByTxt", "xxSortOrdByTxt", null, null, TYPE_HANKAKUEISU, "4", null},
	{"A", "A", null, "200", "business.blap.NSBL0300.NSBL0300_ACMsgArray", null, null},
	
	};

	/**
	 * Array of schema data(Visible Field)
	 */
	private static final String[][] DISP_CONTENTS = {

        {"GLBL_CMPY_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//glblCmpyCd
        {"SLS_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//slsDt
        {"SVC_SKILL_LVL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpCd_H1
        {"SVC_SKILL_LVL_GRP_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpNm_H2
        {"SVC_SKILL_LVL_GRP_CD",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpCd_H3
        {"SVC_SKILL_LVL_GRP_DESC_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//svcSkillLvlGrpDescTxt
        {"EFF_FROM_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effFromDt
        {"EFF_THRU_DT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//effThruDt
        {"_EZUpdateDateTime",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTime
        {"_EZUpTimeZone",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//ezUpTimeZone
        {"PROC_FLG",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//procFlg
        {"XX_SORT_TBL_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortTblNm
        {"XX_SORT_ITEM_NM",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortItemNm
        {"XX_SORT_ORD_BY_TXT",  NO,  null,null,null, NO, NO, NO, NO,null,null,null, null,  NO,  NO},	//xxSortOrdByTxt
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

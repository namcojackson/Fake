/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830.common;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APL_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APL_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APL_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APR_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APR_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_APR_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_CLR_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_CLR_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_CLR_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DEL_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DEL_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DEL_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DWL_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DWL_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_DWL_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RJT_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RJT_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RJT_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RST_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RST_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RST_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RTN_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RTN_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_RTN_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SAV_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SAV_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SAV_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SUB_BTN_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SUB_EVENT_NM;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.BTN_CMN_SUB_LABEL;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.PRM_LENGTH_NMAL2530;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.PRM_LENGTH_NMAL2570;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.PRM_LENGTH_NMAL2630;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.PRM_LENGTH_NMAL6760;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2830.NMAL2830BMsg;
import business.servlet.NMAL2830.NMAL2830_ABMsgArray;
import business.servlet.NMAL2830.constant.NMAL2830Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROS_RVW_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * NMAL2830CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV_BTN_NM, BTN_CMN_SAV_EVENT_NM, BTN_CMN_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APL_BTN_NM, BTN_CMN_APL_EVENT_NM, BTN_CMN_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_APR_BTN_NM, BTN_CMN_APR_EVENT_NM, BTN_CMN_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RJT_BTN_NM, BTN_CMN_RJT_EVENT_NM, BTN_CMN_RJT_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DWL_BTN_NM, BTN_CMN_DWL_EVENT_NM, BTN_CMN_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_DEL_BTN_NM, BTN_CMN_DEL_EVENT_NM, BTN_CMN_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_CLR_BTN_NM, BTN_CMN_CLR_EVENT_NM, BTN_CMN_CLR_LABEL, 1, null);
        handler.setButtonProperties(BTN_CMN_RST_BTN_NM, BTN_CMN_RST_EVENT_NM, BTN_CMN_RST_LABEL, 0, null);
        handler.setButtonProperties(BTN_CMN_RTN_BTN_NM, BTN_CMN_RTN_EVENT_NM, BTN_CMN_RTN_LABEL, 1, null);
    }

    /**
     * Set Common Button properties.
     * @param handler Event Handler
     * @param btnProp Button Properties in Constant class
     * @param sts Status (0:Inactive, 1:Active)
     */
    public static void setCmnBtnProp(S21CommonHandler handler, String[] btnProp, int sts) {
        handler.setButtonProperties(btnProp[0], btnProp[1], btnProp[2], sts, null);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2830BMsg
     * @param scrnAMsgAry NMAL2830_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2830BMsg scrnMsg, NMAL2830_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * @param scrnMsg NMAL2830BMsg
     * @param scrnAMsgAry NMAL2830_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     * @param grpRows color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2830BMsg scrnMsg, NMAL2830_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color
     * back to the initial color.
     * @param scrnMsg NMAL2830BMsg
     * @param scrnAMsgAry NMAL2830_ABMsgArray
     * @param tblId HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2830BMsg scrnMsg, NMAL2830_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * isUpdateUser
     * @param userProfileService S21UserProfileService
     * @return boolean
     */
    public static boolean isUpdateUser(S21UserProfileService userProfileService) {
        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(NMAL2830Constant.BIZ_ID);
        return functionIds.contains(NMAL2830Constant.FUNC_ID_UPDATE);
    }

    /**
     * controlScreenFields
     * @param usrPrfSvc S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2830BMsg
     */
    public static final void controlScreenFields(S21UserProfileService usrPrfSvc, EZDCommonHandler handler, NMAL2830BMsg scrnMsg) {

        if (scrnMsg.A.getValidCount() == 0) {
            handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
        } else {
            if (isUpdateUser(usrPrfSvc)) {
                handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 1, null);
            } else {
                handler.setButtonProperties(BTN_CMN_SUB_BTN_NM, BTN_CMN_SUB_EVENT_NM, BTN_CMN_SUB_LABEL, 0, null);
            }
        }

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // Detail（Common）
            scrnMsg.A.no(i).xxRowId_AN.setInputProtected(true);
            // Detail（Prospect）
            scrnMsg.A.no(i).dsAcctNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsXrefAcctCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctTpNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A1.setInputProtected(true);
            scrnMsg.A.no(i).stCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxAsgTrtyNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_E1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_P1.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_D1.setInputProtected(true);
            // Detail（Requested Merge To）
            scrnMsg.A.no(i).dsAcctNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A2.setInputProtected(true);
            scrnMsg.A.no(i).dsXrefAcctCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctTpNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A2.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A2.setInputProtected(true);
            scrnMsg.A.no(i).stCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxAsgTrtyNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_E2.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_P2.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_D2.setInputProtected(true);
            // Detail（Duplicate）
            scrnMsg.A.no(i).dsAcctNum_A3.setInputProtected(true);
            scrnMsg.A.no(i).locNum_A3.setInputProtected(true);
            scrnMsg.A.no(i).dsXrefAcctCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctTpNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxAllLineAddr_A3.setInputProtected(true);
            scrnMsg.A.no(i).ctyAddr_A3.setInputProtected(true);
            scrnMsg.A.no(i).stCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).postCd_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxAsgTrtyNm_A3.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_E3.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_P3.setInputProtected(true);
            scrnMsg.A.no(i).xxChkBox_D3.setInputProtected(true);
        }
    }

    /**
     * checkMandatoryHeader
     * @param scrnMsg NMAL2830BMsg
     */
    public static void checkMandatoryHeader(NMAL2830BMsg scrnMsg) {

        if (!ZYPCommonFunc.hasValue(scrnMsg.xxFromDt) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNm) && !ZYPCommonFunc.hasValue(scrnMsg.fill65Txt_RN) && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_TN) && !ZYPCommonFunc.hasValue(scrnMsg.xxToDt)
                && !ZYPCommonFunc.hasValue(scrnMsg.dsXrefAcctCd) && !ZYPCommonFunc.hasValue(scrnMsg.psnCd) && !ZYPCommonFunc.hasValue(scrnMsg.orgNm_ON) && !ZYPCommonFunc.hasValue(scrnMsg.xxAllLineAddr)
                && !ZYPCommonFunc.hasValue(scrnMsg.ctyAddr) && !ZYPCommonFunc.hasValue(scrnMsg.stCd) && !ZYPCommonFunc.hasValue(scrnMsg.postCd) && !ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum) && !ZYPCommonFunc.hasValue(scrnMsg.locNum)) {

            scrnMsg.xxFromDt.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.dsAcctNm.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.fill65Txt_RN.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.orgNm_TN.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.xxToDt.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.dsXrefAcctCd.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.psnCd.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.orgNm_ON.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.xxAllLineAddr.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.ctyAddr.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.stCd.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.postCd.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.dsAcctNum.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
            scrnMsg.locNum.setErrorInfo(1, NMAL2830Constant.NMAM0192E);
        }
    }

    /**
     * AddCheckItemHeader
     * @param scrnMsg NMAL2830BMsg
     */
    public static void addCheckItemHeader(NMAL2830BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);
        scrnMsg.addCheckItem(scrnMsg.xxFromDt);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.fill65Txt_RN);
        scrnMsg.addCheckItem(scrnMsg.orgNm_TN);
        scrnMsg.addCheckItem(scrnMsg.xxTpCd_D);
        scrnMsg.addCheckItem(scrnMsg.xxToDt);
        scrnMsg.addCheckItem(scrnMsg.dsXrefAcctCd);
        scrnMsg.addCheckItem(scrnMsg.psnCd);
        scrnMsg.addCheckItem(scrnMsg.orgNm_ON);
        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RT);
        scrnMsg.addCheckItem(scrnMsg.xxAllLineAddr);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr);
        scrnMsg.addCheckItem(scrnMsg.stCd);
        scrnMsg.addCheckItem(scrnMsg.postCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.locNum);
    }

    /**
     * AddCheckItemDetail
     * @param scrnMsg NMAL2830BMsg
     */
    public static void addCheckItemDetail(NMAL2830BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).locNum_M);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_M);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_AM);
        }
    }

    /**
     * clearParam
     * @param scrnMsg NMAL2830BMsg
     */
    public static void clearParam(NMAL2830BMsg scrnMsg) {

        scrnMsg.xxPopPrm_01.clear();
        scrnMsg.xxPopPrm_02.clear();
        scrnMsg.xxPopPrm_03.clear();
        scrnMsg.xxPopPrm_04.clear();
        scrnMsg.xxPopPrm_05.clear();
        scrnMsg.xxPopPrm_06.clear();
        scrnMsg.xxPopPrm_07.clear();
        scrnMsg.xxPopPrm_08.clear();
        scrnMsg.xxPopPrm_09.clear();
        scrnMsg.xxPopPrm_10.clear();
        scrnMsg.xxPopPrm_11.clear();
        scrnMsg.xxPopPrm_12.clear();
        scrnMsg.xxPopPrm_13.clear();
        scrnMsg.xxPopPrm_14.clear();
        scrnMsg.xxPopPrm_15.clear();
        scrnMsg.xxPopPrm_16.clear();
        scrnMsg.xxPopPrm_17.clear();
        scrnMsg.xxPopPrm_18.clear();
        scrnMsg.xxPopPrm_19.clear();
        scrnMsg.xxPopPrm_20.clear();
        scrnMsg.xxPopPrm_21.clear();
        scrnMsg.xxPopPrm_22.clear();
        scrnMsg.xxPopPrm_23.clear();
        scrnMsg.xxPopPrm_24.clear();
        scrnMsg.xxPopPrm_25.clear();
        scrnMsg.xxPopPrm_26.clear();
        scrnMsg.xxPopPrm_27.clear();
        scrnMsg.xxPopPrm_28.clear();
        scrnMsg.xxPopPrm_29.clear();
        scrnMsg.xxPopPrm_30.clear();
        scrnMsg.xxPopPrm_31.clear();
        scrnMsg.xxPopPrm_32.clear();
        scrnMsg.xxPopPrm_33.clear();
        scrnMsg.xxPopPrm_34.clear();
        scrnMsg.xxPopPrm_35.clear();
    }

    /**
     * setParamForTerritoryPopup
     * @param scrnMsg NMAL2830BMsg
     * @return Object[]
     */
    public static Object[] setParamForTerritoryPopup(NMAL2830BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.orgNm_TN);

        Object[] params = new Object[PRM_LENGTH_NMAL2630];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;

        return params;
    }

    /**
     * setParamForOrganizationPopup
     * @param scrnMsg NMAL2830BMsg
     * @return Object[]
     */
    public static Object[] setParamForOrganizationPopup(NMAL2830BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.orgNm_ON);

        Object[] params = new Object[PRM_LENGTH_NMAL2530];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;

        return params;
    }

    /**
     * setParamForResourceLookupPopup
     * @param scrnMsg NMAL2830BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceLookupPopup(NMAL2830BMsg scrnMsg) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.psnCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_04, scrnMsg.fill65Txt_RN);

        Object[] params = new Object[PRM_LENGTH_NMAL2570];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;

        return params;
    }

    /**
     * setParamForProspectReviewPopup
     * @param scrnMsg NMAL2830BMsg
     * @return Object[]
     */
    public static Object[] setParamForProspectReviewPopup(NMAL2830BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Prospect Review Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    DARP.GLBL_CMPY_CD");
        sb.append("   ,DARP.EZCANCELFLAG");
        sb.append("   ,DARP.RVW_PROS_NUM");
        sb.append("   ,DARP.AFT_DS_ACCT_NM ");
        sb.append("FROM");
        sb.append("    DS_ACCT_RVW_PROS DARP ");
        sb.append("WHERE");
        sb.append("    DARP.PROS_RVW_STS_CD     = '").append(PROS_RVW_STS.U).append("'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Prospect Review#";
        whereArray0[1] = "RVW_PROS_NUM";
        whereArray0[2] = scrnMsg.dsXrefAcctCd.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Prospect Name";
        whereArray1[1] = "AFT_DS_ACCT_NM";
        whereArray1[2] = null;
        whereArray1[3] = "Y";
        whereList.add(whereArray1);
        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Prospect Review#";
        columnArray0[1] = "RVW_PROS_NUM";
        columnArray0[2] = BigDecimal.valueOf(30);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Prospect Name";
        columnArray1[1] = "AFT_DS_ACCT_NM";
        columnArray1[2] = BigDecimal.valueOf(360);
        columnArray1[3] = "N";
        columnList.add(columnArray1);
        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = "RVW_PROS_NUM";
        sortConditionArray[1] = "ASC";
        sortConditionList.add(sortConditionArray);
        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }

    /**
     * setParamForResourceLookupPopup
     * @param scrnMsg NMAL2830BMsg
     * @param scrEventNm String
     * @return Object[]
     */
    public static Object[] setParamForAccountSearchPopup(NMAL2830BMsg scrnMsg, String scrEventNm) {
        return setParamForAccountSearchPopup(scrnMsg, scrEventNm, 0);
    }

    /**
     * setParamForResourceLookupPopup
     * @param scrnMsg NMAL2830BMsg
     * @param scrEventNm String
     * @param rowNum int
     * @return Object[]
     */
    public static Object[] setParamForAccountSearchPopup(NMAL2830BMsg scrnMsg, String scrEventNm, int rowNum) {

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, scrEventNm);

        if (NMAL2830Constant.LINK_PROS_NM_EVENT_NM.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_02, scrnMsg.dsAcctNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_35, DS_ACCT_TP.PROSPECT);

        } else if (NMAL2830Constant.LINK_ACCT_NUM_EVENT_NM.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_01, scrnMsg.dsAcctNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_35, DS_ACCT_TP.PROSPECT);

        } else if (NMAL2830Constant.LINK_LOC_NUM_EVENT_NM.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.locNum);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_34, ZYPConstant.FLG_ON_Y);

        } else if (NMAL2830Constant.LINK_MERGE_TO_EVENT_NM.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_03, scrnMsg.A.no(rowNum).locNum_M);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_34, ZYPConstant.FLG_ON_Y);

        }

        Object[] params = new Object[PRM_LENGTH_NMAL6760];

        int i = 0;
        params[i++] = scrnMsg.xxPopPrm_01;
        params[i++] = scrnMsg.xxPopPrm_02;
        params[i++] = scrnMsg.xxPopPrm_03;
        params[i++] = scrnMsg.xxPopPrm_04;
        params[i++] = scrnMsg.xxPopPrm_05;
        params[i++] = scrnMsg.xxPopPrm_06;
        params[i++] = scrnMsg.xxPopPrm_07;
        params[i++] = scrnMsg.xxPopPrm_08;
        params[i++] = scrnMsg.xxPopPrm_09;
        params[i++] = scrnMsg.xxPopPrm_10;
        params[i++] = scrnMsg.xxPopPrm_11;
        params[i++] = scrnMsg.xxPopPrm_12;
        params[i++] = scrnMsg.xxPopPrm_13;
        params[i++] = scrnMsg.xxPopPrm_14;
        params[i++] = scrnMsg.xxPopPrm_15;
        params[i++] = scrnMsg.xxPopPrm_16;
        params[i++] = scrnMsg.xxPopPrm_17;
        params[i++] = scrnMsg.xxPopPrm_18;
        params[i++] = scrnMsg.xxPopPrm_19;
        params[i++] = scrnMsg.xxPopPrm_20;
        params[i++] = scrnMsg.xxPopPrm_21;
        params[i++] = scrnMsg.xxPopPrm_22;
        params[i++] = scrnMsg.xxPopPrm_23;
        params[i++] = scrnMsg.xxPopPrm_24;
        params[i++] = scrnMsg.xxPopPrm_25;
        params[i++] = scrnMsg.xxPopPrm_26;
        params[i++] = scrnMsg.xxPopPrm_27;
        params[i++] = scrnMsg.xxPopPrm_28;
        params[i++] = scrnMsg.xxPopPrm_29;
        params[i++] = scrnMsg.xxPopPrm_30;
        params[i++] = scrnMsg.xxPopPrm_31;
        params[i++] = scrnMsg.xxPopPrm_32;
        params[i++] = scrnMsg.xxPopPrm_33;
        params[i++] = scrnMsg.xxPopPrm_34;
        params[i++] = scrnMsg.xxPopPrm_35;

        return params;
    }
}

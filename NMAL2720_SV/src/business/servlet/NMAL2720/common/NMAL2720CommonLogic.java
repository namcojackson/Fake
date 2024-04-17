/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2720.common;

import static business.servlet.NMAL2720.constant.NMAL2720Constant.BIZ_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_SAV;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_SUB;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_APL;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_APR;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_RJT;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_DWL;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_DEL;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_CLR;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_RST;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.BTN_CMN_RTN;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_DTL;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_CD;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_NAME;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.EVENT_NM_RESRC_LOOK_UP_FOR_NUM;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_EMP_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_EMP_NM;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_MASS_UPD_RSN;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_RQST_DATE;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_RQST_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_RQST_STS;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_COL_NM_RQST_TP;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_EMP_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_EMP_NM;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_MASS_UPD_RSN;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_RQST_DATE;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_RQST_ID;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_RQST_STS;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_LABEL_NM_RQST_TP;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.POPUP_SORT_KEY_DESC;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.PRM_LENGTH_NMAL2530;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.PRM_LENGTH_NMAL2570;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.PRM_LENGTH_NWAL1130;
import static business.servlet.NMAL2720.constant.NMAL2720Constant.SCRN_ID_00;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDCommonConst;
import parts.common.EZDMessageInfo;

import business.servlet.NMAL2720.NMAL2720BMsg;
import business.servlet.NMAL2720.NMAL2720_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL2720CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL2720CommonLogic {

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     */
    public static void initCmnBtnProp(S21CommonHandler handler) {
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);

    }

    /**
     * Set Common Button properties.
     * 
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
     * 
     * @param scrnMsg     NMAL2720BMsg
     * @param scrnAMsgAry NMAL2720_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL2720BMsg scrnMsg, NMAL2720_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL2720BMsg
     * @param scrnAMsgAry NMAL2720_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL2720BMsg scrnMsg, NMAL2720_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL2720BMsg
     * @param scrnAMsgAry NMAL2720_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL2720BMsg scrnMsg, NMAL2720_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }


        if (funcList.contains("NMAL2720T020")) {
            return true;
        }

        return false;
    }

    /**
     * setScrnCtrl.
     * @param scrnMsg NMAL2720BMsg
     */
    public static void setScrnCtrl(NMAL2720BMsg scrnMsg) {
        scrnMsg.psnNum_D1.setInputProtected(true);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).orgNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).orgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).bizAreaOrgDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).psnNum_A1.setInputProtected(true);
            scrnMsg.A.no(i).orgFuncRoleTpDescTxt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
        }
    }

    /**
     * setParamForOrganizationPopup
     * @param scrnMsg NMAL2720BMsg
     * @return Object[]
     */
    public static Object[] setParamForOrganizationPopup(NMAL2720BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.orgNm_H);

        Object[] params = new Object[PRM_LENGTH_NMAL2530];

        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;
        params[4] = scrnMsg.P.no(4).xxPopPrm;
        params[5] = scrnMsg.P.no(5).xxPopPrm;
        params[6] = scrnMsg.P.no(6).xxPopPrm;

        return params;
    }

    /**
     * setParamForResourceLookup.
     * @param scrnMsg NMAL2720BMsg
     * @return Object[]
     */
    public static Object[] setParamForResourceLookup(NMAL2720BMsg scrnMsg) {

        ZYPTableUtil.clear(scrnMsg.P);

        String flg = ZYPConstant.FLG_ON_Y;

        if (!BIZ_AREA_ORG.SALES.equals(scrnMsg.bizAreaOrgCd_H.getValue())) {
            flg = ZYPConstant.FLG_OFF_N;
        }

        if (EVENT_NM_RESRC_LOOK_UP_FOR_CD.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm, scrnMsg.psnCd_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_FOR_NAME.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.xxPsnNm_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_FOR_NUM.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, scrnMsg.psnNum_H);
        } else if (EVENT_NM_RESRC_LOOK_UP_DTL.equals(scrnMsg.xxScrEventNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(3).xxPopPrm, scrnMsg.xxPsnNm_D1);
            flg = ZYPConstant.FLG_OFF_N;
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(2).xxPopPrm, flg);

        Object[] params = new Object[PRM_LENGTH_NMAL2570];

        params[0] = scrnMsg.P.no(0).xxPopPrm;
        params[1] = scrnMsg.P.no(1).xxPopPrm;
        params[2] = scrnMsg.P.no(2).xxPopPrm;
        params[3] = scrnMsg.P.no(3).xxPopPrm;

        return params;
    }

    /**
     * setParamForRequestHistory
     * @param scrnMsg NMAL2720BMsg
     * @param glblCmpyCd String
     * @return Object[]
     */
    public static Object[] setParamForRequestHistory(NMAL2720BMsg scrnMsg, String glblCmpyCd) {

        Object[] params = new Object[PRM_LENGTH_NWAL1130];

        params[0] = "";
        params[1] = "Request History Search";
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT ");
        sb.append("     MORH.MOVE_ORG_RQST_HDR_PK");
        sb.append("    ,MORH.RQST_USR_ID");
        sb.append("    ,AP.FIRST_NM || ' ' || AP.LAST_NM  AS RQST_USR_NM ");
        sb.append("    ,TO_CHAR( TO_TIMESTAMP(MORH.RQST_CRAT_TS, 'YYYYMMDDHH24MISSFF3'), '" + ZYPDateUtil.getDateFormatString(true) + " HH24:MI:SS') AS RQST_CRAT_TS");
        sb.append("    ,MORH.RQST_RSLT_CMNT_TXT ");
        sb.append("    ,MORH.GLBL_CMPY_CD ");
        sb.append("    ,MORH.EZCANCELFLAG ");
        sb.append("    ,RT.RQST_RSLT_TP_DESC_TXT ");
        sb.append("    ,ST.RQST_CRAT_SYS_TP_DESC_TXT ");
        sb.append(" FROM MOVE_ORG_RQST_HDR MORH ");
        sb.append("    , AUTH_PSN          AP ");
        sb.append("    , RQST_RSLT_TP      RT ");
        sb.append("    , RQST_CRAT_SYS_TP  ST ");
        sb.append(" WHERE MORH.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
        sb.append("   AND MORH.EZCANCELFLAG = '0'");
        sb.append("   AND MORH.GLBL_CMPY_CD = AP.GLBL_CMPY_CD");
        sb.append("   AND MORH.EZCANCELFLAG = AP.EZCANCELFLAG");
        sb.append("   AND MORH.RQST_USR_ID  = AP.USR_NM");
        sb.append("   AND MORH.GLBL_CMPY_CD = RT.GLBL_CMPY_CD");
        sb.append("   AND MORH.EZCANCELFLAG = RT.EZCANCELFLAG");
        sb.append("   AND MORH.RQST_RSLT_TP_CD = RT.RQST_RSLT_TP_CD");
        sb.append("   AND MORH.GLBL_CMPY_CD = ST.GLBL_CMPY_CD");
        sb.append("   AND MORH.EZCANCELFLAG = ST.EZCANCELFLAG");
        sb.append("   AND MORH.RQST_CRAT_SYS_TP_CD = ST.RQST_CRAT_SYS_TP_CD");
        sb.append(" ORDER BY MORH.RQST_CRAT_TS DESC");
        params[2] = sb.toString();

        List<Object> whereList = new ArrayList<Object>();

        // Request ID
        Object[] whereObj1 = {POPUP_LABEL_NM_RQST_ID, POPUP_COL_NM_RQST_ID, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);

        // Employee ID
        Object[] whereObj2 = {POPUP_LABEL_NM_EMP_ID, POPUP_COL_NM_EMP_ID, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj2);

        // Employee Name
        Object[] whereObj3 = {POPUP_LABEL_NM_EMP_NM, POPUP_COL_NM_EMP_NM, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj3);

        // Request Date
        Object[] whereObj4 = {POPUP_LABEL_NM_RQST_DATE, POPUP_COL_NM_RQST_DATE, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj4);

        // Request Status
        Object[] whereObj5 = {POPUP_LABEL_NM_RQST_STS, POPUP_COL_NM_RQST_STS, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj5);

        // Request Type
        Object[] whereObj6 = {POPUP_LABEL_NM_RQST_TP, POPUP_COL_NM_RQST_TP, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj6);

        // Mass Update Reason
        Object[] whereObj7 = {POPUP_LABEL_NM_MASS_UPD_RSN, POPUP_COL_NM_MASS_UPD_RSN, null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj7);

        params[3] = whereList;

        List<Object> colList = new ArrayList<Object>();

        // Request ID
        Object[] colObj1 = {POPUP_LABEL_NM_RQST_ID, POPUP_COL_NM_RQST_ID, new BigDecimal("8"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);

        // Employee ID
        Object[] colObj2 = {POPUP_LABEL_NM_EMP_ID, POPUP_COL_NM_EMP_ID, new BigDecimal("8"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj2);

        // Employee Name
        Object[] colObj3 = {POPUP_LABEL_NM_EMP_NM, POPUP_COL_NM_EMP_NM, new BigDecimal("16"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj3);

        // Request Date
        Object[] colObj4 = {POPUP_LABEL_NM_RQST_DATE, POPUP_COL_NM_RQST_DATE, new BigDecimal("14"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj4);

        // Request Status
        Object[] colObj5 = {POPUP_LABEL_NM_RQST_STS, POPUP_COL_NM_RQST_STS, new BigDecimal("15"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj5);

        // Request Type
        Object[] colObj6 = {POPUP_LABEL_NM_RQST_TP, POPUP_COL_NM_RQST_TP, new BigDecimal("9"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj6);

        // Mass Update Reason
        Object[] colObj7 = {POPUP_LABEL_NM_MASS_UPD_RSN, POPUP_COL_NM_MASS_UPD_RSN, new BigDecimal("22"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj7);

        params[4] = colList;

        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {POPUP_COL_NM_RQST_DATE, POPUP_SORT_KEY_DESC};

        sortList.add(sortObj1);
        params[5] = sortList;

        params[6] = scrnMsg.P;

        return params;
    }

    /**
     * addCheckItemScreen.
     * @param scrnMsg NMAL2720BMsg
     * @param isMandatory boolean
     */
    public static void addCheckItemScreen(NMAL2720BMsg scrnMsg, boolean isMandatory) {
        if (!isMandatory) {
            if (scrnMsg.xxPsnNm_D1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("xxPsnNm_D1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.xxPsnNm_D1.clearErrorInfo();
                }
            }

            if (scrnMsg.effFromDt_D1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt_D1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.effFromDt_D1.clearErrorInfo();
                }
            }
        }

        scrnMsg.addCheckItem(scrnMsg.bizAreaOrgCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H);
        scrnMsg.addCheckItem(scrnMsg.psnNum_H);
        scrnMsg.addCheckItem(scrnMsg.orgNm_H);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);

        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_D1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_D1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_D1);
        scrnMsg.addCheckItem(scrnMsg.massUpdRsnCmntTxt_D1);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
        }

        scrnMsg.putErrorScreen();
    }

}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070.common;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.SCRN_ID_00;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_APL;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_APR;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_RST;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_NEW_REGISTRATION;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_SEARCH;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.BTN_VIEW_DETAIL;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.CMN_PRM_SORT_NUM;
import static business.servlet.NMAL7070.constant.NMAL7070Constant.CMN_PRM_WHERE_NUM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL7070.NMAL7070BMsg;
import business.servlet.NMAL7070.NMAL7070_ABMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070CommonLogic {

    /**
     * setPage
     * @param scrnMsg NMAL7070BMsg
     * @param page int
     */
    public static void setPage(NMAL7070BMsg scrnMsg, int page) {
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.setValue(page);
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();
    }

    /**
     * initControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NMAL7070BMsg scrnMsg) {
        scrnMsg.clearAllGUIAttribute(SCRN_ID_00);
        initCommonButton(handler);
        initButton(handler);
        scrnMsg.setFocusItem(scrnMsg.splyAgmtPlnNm);
    }

    /**
     * searchControlScreen
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    public static void searchControlScreen(EZDCommonHandler handler, NMAL7070BMsg scrnMsg) {
        searchScreenField(handler, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.splyAgmtPlnNm);

    }

    /**
     * searchScreenField
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7070BMsg
     */
    private static void searchScreenField(EZDCommonHandler handler, NMAL7070BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7070_ABMsg scrnMsgA = scrnMsg.A.no(i);
            scrnMsgA.splyAgmtPlnNm_A.setInputProtected(true);
            scrnMsgA.splyAgmtPlnShortNm_A.setInputProtected(true);
            scrnMsgA.splyAgmtPlnDescTxt_A.setInputProtected(true);
            scrnMsgA.splyAgmtPlnTpNm_A.setInputProtected(true);
            scrnMsgA.splyAgmtDocTpNm_A.setInputProtected(true);
            scrnMsgA.xxFullNm_CB.setInputProtected(true);
            scrnMsgA.xxFullNm_UB.setInputProtected(true);
        }
        if(scrnMsg.A.getValidCount() > 0){
            handler.setButtonEnabled(BTN_VIEW_DETAIL[0], true);
        } else {
            handler.setButtonEnabled(BTN_VIEW_DETAIL[0], false);
        }
        

    }

    /**
     * Set Button Enable Init
     * @param handler EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
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
     * initButton
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_NEW_REGISTRATION[0], BTN_NEW_REGISTRATION[1], BTN_NEW_REGISTRATION[2], 1, null);
        handler.setButtonProperties(BTN_VIEW_DETAIL[0], BTN_VIEW_DETAIL[1], BTN_VIEW_DETAIL[2], 0, null);
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NMAL7070BMsg
     */
    public static void commonAddCheckItem(NMAL7070BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm);

        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnNm);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnShortNm);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnDescTxt);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnTpCd);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtDocTpCd);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
        scrnMsg.addCheckItem(scrnMsg.dsAcctNm);
        scrnMsg.addCheckItem(scrnMsg.csmpNum);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.splyAgmtPlnStsCd);
    }

    /**
     * setCsmpPopupParam
     * @param scrnMsg NMAL7070BMsg
     * @return Object[]
     */
    public static Object[] setCsmpPopupParam(NMAL7070BMsg scrnMsg) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "CSMP Search";

        tblNmP2.append("CSMP_CONTR");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "CSMP Number";
        whereArray0[1] = "CSMP_NUM";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "Account Number";
        whereArray1[1] = "DS_ACCT_NUM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
        whereArray2[0] = "Account Name";
        whereArray2[1] = "DS_ACCT_NM";
        whereArray2[2] = null;
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray2);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "CSMP Number";
        columnArray0[1] = "CSMP_NUM";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "Account Number";
        columnArray1[1] = "DS_ACCT_NUM";
        columnArray1[2] = BigDecimal.valueOf(20);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray2[0] = "Account Name";
        columnArray2[1] = "DS_ACCT_NM";
        columnArray2[2] = BigDecimal.valueOf(50);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray2);

        Object[] sortConditionArray0 = new Object[CMN_PRM_SORT_NUM];
        sortConditionArray0[0] = "DS_ACCT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * setBranchPopupParam
     * @param scrnMsg NMAL7070BMsg
     * @return Object[]
     */
    public static Object[] setBranchPopupParam(NMAL7070BMsg scrnMsg) {
        Object[] param = new Object[7];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        scrnMsg.R.clear();

        suffixP0 = "";

        scrnNmP1 = "COA Branch Search";

        tblNmP2.append("COA_BR");

        Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
        whereArray0[0] = "COA Branch Code";
        whereArray0[1] = "COA_BR_CD";
        whereArray0[2] = null;
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray0);

        Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
        whereArray1[0] = "COA Branch Name";
        whereArray1[1] = "COA_BR_NM";
        whereArray1[2] = null;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereListP3.add(whereArray1);

        Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray0[0] = "COA Branch Code";
        columnArray0[1] = "COA_BR_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnListP4.add(columnArray0);

        Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
        columnArray1[0] = "COA Branch Name";
        columnArray1[1] = "COA_BR_NM";
        columnArray1[2] = BigDecimal.valueOf(70);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnListP4.add(columnArray1);

        Object[] sortConditionArray0 = new Object[CMN_PRM_SORT_NUM];
        sortConditionArray0[0] = "COA_BR_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionListP5.add(sortConditionArray0);

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640.common;

import static business.servlet.NSAL0640.constant.NSAL0640Constant.BTN_BR;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.BTN_REP;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.CONTR_BR_FIRST_ORG_CD;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SCREEN_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0640.NSAL0640BMsg;
import business.servlet.NSAL0640.constant.NSAL0640Constant.BTN_LBL;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Update Contract Branch/Representative
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/28   Hitachi         M.Gotou         Update          QC#4702,4703,4915
 * 2016/03/31   Hitachi         M.Gotou         Update          QC#4916
 * 2016/12/08   Hitachi         T.Mizuki        Update          QC#4210
 * 2017/02/10   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/02/23   Hitachi         A.Kohinata      Update          QC#15112
 * 2019/02/12   Hitachi         S.Kitamura      Update          QC#30264
 *</pre>
 */
public class NSAL0640CommonLogic {

    /**
     * addCheckItem
     * @param scrnMsg NSAL0640BMsg
     */
    public static void addCheckItem(NSAL0640BMsg scrnMsg) {
        // mod start 2016/03/28 CSA Defect#4702,4703,4915
        //scrnMsg.addCheckItem(scrnMsg.tocCd_H);
        //scrnMsg.addCheckItem(scrnMsg.tocNm_H);
        // mod start 2016/03/31 CSA Defect#4916
        //scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxDplyByCdNmCnctTxt_H);
        // mod end 2016/03/31 CSA Defect#4916
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H);
        // mod end 2016/03/28 CSA Defect#4702,4703,4915
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxGenlFldAreaTxt_A2);
            // mod start 2016/03/28 CSA Defect#4702,4703,4915
            //scrnMsg.addCheckItem(scrnMsg.A.no(i).tocNm_A2);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxPsnNm_A2);
            // mod end 2016/03/28 CSA Defect#4702,4703,4915
        }
    }

    /**
     * clearPopupParam
     * @param scrnMsg NSAL0640BMsg
     */
    public static void clearPopupParam(NSAL0640BMsg scrnMsg) {
        scrnMsg.svcRgNm_P.clear();
        scrnMsg.svcLineBizDescTxt_P.clear();
        scrnMsg.svcContrBrCd_P.clear();
        scrnMsg.svcContrBrDescTxt_P.clear();
        scrnMsg.xxGenlFldAreaTxt_P.clear();
        scrnMsg.orgFuncRoleTpNm_P.clear();
        scrnMsg.svcRgPk_P.clear();
        scrnMsg.psnCd_P.clear();
        scrnMsg.svcLineBizCd_P.clear();
        scrnMsg.xxScrEventNm.clear();
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0640BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlDetailButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0640BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
        handler.setButtonProperties(BTN_LBL.SAVE.getOrgNm(), BTN_LBL.SAVE.getGuardNm(), BTN_LBL.SAVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.SUBMIT.getOrgNm(), BTN_LBL.SUBMIT.getGuardNm(), BTN_LBL.SUBMIT.getLblNm(), 1, null);
        handler.setButtonProperties(BTN_LBL.APPLY.getOrgNm(), BTN_LBL.APPLY.getGuardNm(), BTN_LBL.APPLY.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.APPROVE.getOrgNm(), BTN_LBL.APPROVE.getGuardNm(), BTN_LBL.APPROVE.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.REJECT.getOrgNm(), BTN_LBL.REJECT.getGuardNm(), BTN_LBL.REJECT.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DOWNLOAD.getOrgNm(), BTN_LBL.DOWNLOAD.getGuardNm(), BTN_LBL.DOWNLOAD.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.DELETE.getOrgNm(), BTN_LBL.DELETE.getGuardNm(), BTN_LBL.DELETE.getLblNm(), 0, null);
     // START 2017/02/10 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_LBL.CLEAR.getOrgNm(), BTN_LBL.CLEAR.getGuardNm(), BTN_LBL.CLEAR.getLblNm(), 0, null);
        handler.setButtonProperties(BTN_LBL.RESET.getOrgNm(), BTN_LBL.RESET.getGuardNm(), BTN_LBL.RESET.getLblNm(), 1, null);
     // END 2017/02/10 K.Ochiai [QC#16331, MOD]
        handler.setButtonProperties(BTN_LBL.RETURN.getOrgNm(), BTN_LBL.RETURN.getGuardNm(), BTN_LBL.RETURN.getLblNm(), 1, null);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0640BMsg
     */
    // mod start 2016/12/08 CSA QC#4210
    public static final void controlScreenFields(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
    // mod end 2016/12/08 CSA QC#4210
        controlScreenHeaderFields(handler, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            controlScreenDetailFields(handler, scrnMsg);
            setRowColors(scrnMsg);
        }
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0640BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
        // mod start 2016/03/31 CSA Defect#4916
        // mod start 2016/03/28 CSA Defect#4702,4703,4915
        //scrnMsg.tocCd_H.setInputProtected(false);
        //scrnMsg.tocNm_H.setInputProtected(true);
        //scrnMsg.psnCd_H.setInputProtected(false);
        scrnMsg.xxDplyByCdNmCnctTxt_H.setInputProtected(false);
        scrnMsg.xxPsnNm_H.setInputProtected(false);
        // mod end 2016/03/28 CSA Defect#4702,4703,4915
        //scrnMsg.svcContrBrCd_H.setInputProtected(false);
        //scrnMsg.svcContrBrDescTxt_H.setInputProtected(true);
        scrnMsg.svcMemoRsnCd_H.setInputProtected(false);
        scrnMsg.svcCmntTxt_H.setInputProtected(false);
        scrnMsg.xxChkBox_AL.setInputProtected(false);
        // mod end 2016/03/31 CSA Defect#4916

    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0640BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            }
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setInputProtected(true);
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setInputProtected(true);
            // mod start 2016/03/28 CSA Defect#4702,4703,4915
            //scrnMsg.A.no(i).tocNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            // mod start 2016/03/31 CSA Defect#4916
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A2.setInputProtected(true);
                scrnMsg.A.no(i).xxPsnNm_A2.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxGenlFldAreaTxt_A2.setInputProtected(false);
                scrnMsg.A.no(i).xxPsnNm_A2.setInputProtected(false);
            }
            //scrnMsg.A.no(i).tocNm_A2.setInputProtected(true);
            // mod end 2016/03/31 CSA Defect#4916
            // mod end 2016/03/28 CSA Defect#4702,4703,4915
            scrnMsg.A.no(i).dsMsgTxt_A1.setInputProtected(true);
        }
    }

    /**
     * controlDetailButton
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0640BMsg
     */
    // mod start 2016/12/08 CSA QC#4210
    public static final void controlDetailButton(EZDCommonHandler handler, NSAL0640BMsg scrnMsg) {
    // mod end 2016/12/08 CSA QC#4210
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxDplyCtrlFlg_A1.getValue())) {
                handler.setButtonEnabled(BTN_BR, i, false);
             // mod start 2016/04/04 CSA Defect#4916
                handler.setButtonEnabled(BTN_REP, i, false);
            } else {
                handler.setButtonEnabled(BTN_BR, i, true);
                handler.setButtonEnabled(BTN_REP, i, true);
                // mod end 2016/04/04 CSA Defect#4916
            }
        }
    }

    /**
     * setRowColors
     * @param scrnMsg NSAL0640BMsg
     */
    private static void setRowColors(NSAL0640BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // add start 2016/04/04 CSA Defect#4916
    /**
     * getSearchConditionSettingDtl
     * @param scrnMsg NSAL0640BMsg
     * @param index index
     * @return List
     */
    public static List<Object> getSearchConditionSettingDtl(NSAL0640BMsg scrnMsg, int index) {
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.psnCd_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.xxPsnNm_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.A.no(index).svcLineBizCd_A2.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.A.no(index).svcContrBrCd_A2.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.A.no(index).svcContrBrDescTxt_A2.getValue(), ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        return whereList;
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NSAL0640BMsg
     * @return List
     */
    public static List<Object> getSearchConditionSetting(NSAL0640BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.psnCd_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.xxPsnNm_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.svcLineBizCd_H.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.svcContrBrCd_H.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.svcContrBrDescTxt_H.getValue(), ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        return whereList;
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NSAL0640BMsg
     * @return List
     */
    public static List<Object> getDisplayColumnSetting(NSAL0640BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Person Code", "PSN_CD", new BigDecimal("9"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Person Name", "PSN_NM", new BigDecimal("42"), ZYPConstant.FLG_OFF_N };
        Object[] colObj3 = {"Branch Code", "SVC_CONTR_BR_CD", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Branch Name", "SVC_CONTR_BR_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        return colList;
    }

    /**
     * getSortSetting
     * @param scrnMsg NSAL0640BMsg
     * @return List
     */
    public static List<Object> getSortSetting(NSAL0640BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"PSN_CD", "ASC" };
        Object[] sortObj2 = {"SVC_LINE_BIZ_CD", "ASC" };
        Object[] sortObj3 = {"SVC_CONTR_BR_CD", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        sortList.add(sortObj3);
        return sortList;
    }

    /**
     * getSelectSQL
     * @param scrnMsg NSAL0640BMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return String
     */
    public static String getSelectSQL(NSAL0640BMsg scrnMsg, String glblCmpyCd, String slsDt) {
        String firstOrgCd = ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, glblCmpyCd);
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT");
        sb.append("      A.GLBL_CMPY_CD                         AS GLBL_CMPY_CD");
        sb.append("     ,A.EZCANCELFLAG                         AS EZCANCELFLAG");
        sb.append("     ,A.PSN_CD                               AS PSN_CD");
        sb.append("     ,A.PSN_FIRST_NM || ' ' || A.PSN_LAST_NM AS PSN_NM");
        sb.append("     ,E.SVC_LINE_BIZ_CD                      AS SVC_LINE_BIZ_CD");
        sb.append("     ,C.SVC_CONTR_BR_CD                      AS SVC_CONTR_BR_CD");
        sb.append("     ,C.SVC_CONTR_BR_DESC_TXT                AS SVC_CONTR_BR_DESC_TXT");
        sb.append(" FROM");
        sb.append("      S21_PSN           A");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              B1.GLBL_CMPY_CD");
        sb.append("             ,B1.PSN_CD");
        sb.append("             ,B1.SVC_CONTR_BR_CD");
        sb.append("             ,B1.EZCANCELFLAG");
        sb.append("             ,B1.EFF_FROM_DT");
        sb.append("             ,NVL(B1.EFF_THRU_DT, #maxDt#)  AS EFF_THRU_DT");
        // add start 2017/02/23 CSA Defect#15112
        sb.append("             ,B1.SVC_ORG_FUNC_ROLE_TP_CD");
        // add end 2017/02/23 CSA Defect#15112
        sb.append("         FROM");
        sb.append("             SVC_BR_RESRC_RELN B1");
        sb.append("         WHERE");
        sb.append("             B1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND B1.EZCANCELFLAG          = '0'");
        sb.append("         AND B1.SVC_BR_RESRC_ACTV_FLG = 'Y'");
        sb.append("      ) B");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              C1.GLBL_CMPY_CD");
        sb.append("             ,C1.SVC_CONTR_BR_CD");
        sb.append("             ,C1.SVC_CONTR_BR_DESC_TXT");
        sb.append("             ,C1.EZCANCELFLAG");
        sb.append("             ,C1.EFF_FROM_DT");
        sb.append("             ,NVL(C1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
        sb.append("         FROM");
        sb.append("             SVC_CONTR_BR C1");
        sb.append("         WHERE");
        sb.append("             C1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND C1.EZCANCELFLAG          = '0'");
        sb.append("         AND C1.SVC_CONTR_BR_ACTV_FLG = 'Y'");
        sb.append("      ) C");
        sb.append("     ,SVC_RG_BR_RELN D");
        sb.append("     ,(");
        sb.append("         SELECT");
        sb.append("              E1.GLBL_CMPY_CD");
        sb.append("             ,E1.SVC_RG_PK");
        sb.append("             ,E1.SVC_LINE_BIZ_CD");
        sb.append("             ,E1.EZCANCELFLAG");
        sb.append("             ,E1.EFF_FROM_DT");
        sb.append("             ,NVL(E1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
        sb.append("         FROM");
        sb.append("             SVC_RG E1");
        sb.append("         WHERE");
        sb.append("             E1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND E1.EZCANCELFLAG          = '0'");
        sb.append("         AND E1.SVC_RG_ACTV_FLG       = 'Y'");
        sb.append("      ) E");
        // add start 2017/02/23 CSA Defect#15112
        sb.append("     ,ORG_FUNC_ROLE_TP F");
        // add end 2017/02/23 CSA Defect#15112
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append(" AND A.EZCANCELFLAG          = '0'");
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // add start 2017/02/23 CSA Defect#15112
        // sb.append(" AND A.PSN_TP_CD             = '#psnTpCd#'");
        // add end 2017/02/23 CSA Defect#15112
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        sb.append(" AND A.GLBL_CMPY_CD          = B.GLBL_CMPY_CD     (+)");
        sb.append(" AND A.PSN_CD                = B.PSN_CD           (+)");
        sb.append(" AND B.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND B.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND B.EFF_THRU_DT       (+) >= '#slsDt#'");
        sb.append(" AND B.GLBL_CMPY_CD          = C.GLBL_CMPY_CD     (+)");
        sb.append(" AND B.SVC_CONTR_BR_CD       = C.SVC_CONTR_BR_CD  (+)");
        sb.append(" AND C.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND C.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND C.EFF_THRU_DT       (+) >= '#slsDt#'");
        sb.append(" AND C.GLBL_CMPY_CD          = D.GLBL_CMPY_CD     (+)");
        sb.append(" AND C.SVC_CONTR_BR_CD       = D.SVC_CONTR_BR_CD  (+)");
        sb.append(" AND D.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND D.GLBL_CMPY_CD          = E.GLBL_CMPY_CD     (+)");
        sb.append(" AND D.SVC_RG_PK             = E.SVC_RG_PK        (+)");
        sb.append(" AND E.EZCANCELFLAG      (+) = '0'");
        sb.append(" AND E.EFF_FROM_DT       (+) <= '#slsDt#'");
        sb.append(" AND E.EFF_THRU_DT       (+) >= '#slsDt#'");
        // add start 2017/02/23 CSA Defect#15112
        sb.append(" AND B.GLBL_CMPY_CD              = F.GLBL_CMPY_CD");
        sb.append(" AND B.SVC_ORG_FUNC_ROLE_TP_CD   = F.ORG_FUNC_ROLE_TP_CD");
        sb.append(" AND F.EZCANCELFLAG              = '0'");
        sb.append(" AND F.ASG_CONTR_FLG             = 'Y'");
        sb.append(" AND F.FIRST_ORG_CD              = '#firstOrgCd#'");
        // add end 2017/02/23 CSA Defect#15112

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#maxDt#", "29991231");
        sql = sql.replaceAll("#slsDt#", slsDt);
        // START 2019/02/12 S.Kitamura [QC#30264, DEL]
        // add start 2017/02/23 CSA Defect#15112
        // sql = sql.replaceAll("#psnTpCd#", PSN_TP.EMPLOYEE);
        // add end 2017/02/23 CSA Defect#15112
        // END 2019/02/12 S.Kitamura [QC#30264, DEL]
        sql = sql.replaceAll("#firstOrgCd#", firstOrgCd);
        return sql;
    }
    // add end 2016/04/04 CSA Defect#4916
}

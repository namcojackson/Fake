/*
 * <pre>Copyright (c) 202 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1410.common;

import static business.servlet.NSAL1410.constant.NSAL1410Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;

import business.servlet.NSAL1410.NSAL1410BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Contract Branch Rep Update
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2022/06/07   Hitachi         A.Kohinata      Create          QC#60080
 *</pre>
 */
public class NSAL1410CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL1410BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL1410BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    private static final void initCommonButton(EZDCommonHandler handler, NSAL1410BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0], BTN_CMN_DELETE[1], BTN_CMN_DELETE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId()) {
            if (!MODE_AFTER_SUBMIT.equals(scrnMsg.xxModeCd.getValue()) && scrnMsg.A.getValidCount() > 0) {
                handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
            }
        }
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 1, null);
        }
    }

    private static final void controlScreenFields(EZDCommonHandler handler, NSAL1410BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
        setRowColors(scrnMsg);
        if (!MODE_AFTER_SUBMIT.equals(scrnMsg.xxModeCd.getValue()) && scrnMsg.A.getValidCount() > 0) {
            if (hasValue(scrnMsg.xxRowNum)) {
                scrnMsg.setFocusItem(scrnMsg.A.no(scrnMsg.xxRowNum.getValueInt()).psnCd_A2);
            } else {
                scrnMsg.setFocusItem(scrnMsg.A.no(0).psnCd_A2);
            }
        } else {
            scrnMsg.setFocusItem(scrnMsg.dsContrNum_H);
        }
    }

    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL1410BMsg scrnMsg) {
        scrnMsg.dsContrNum_H.setInputProtected(false);
        scrnMsg.svcContrBrCd_H.setInputProtected(false);
        scrnMsg.svcContrBrDescTxt_H.setInputProtected(false);
        scrnMsg.psnCd_H.setInputProtected(false);
        scrnMsg.xxPsnNm_H.setInputProtected(false);
        scrnMsg.billToCustCd_H.setInputProtected(false);
        scrnMsg.locNm_H.setInputProtected(false);
        handler.setButtonEnabled("Search", true);
        handler.setButtonEnabled("Upload", true);
    }

    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL1410BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.A.no(i).dsContrNum_A.setInputProtected(true);
            scrnMsg.A.no(i).billToCustCd_A.setInputProtected(true);
            scrnMsg.A.no(i).locNm_A.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBrCd_A.setInputProtected(true);
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).psnCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A1.setInputProtected(true);
            scrnMsg.A.no(i).psnCd_A2.setInputProtected(true);
            scrnMsg.A.no(i).xxPsnNm_A2.setInputProtected(true);
            scrnMsg.A.no(i).vldMsgTxt_A.setInputProtected(true);
            handler.setButtonEnabled("OpenWin_Rep", i, false);
            if (!MODE_AFTER_SUBMIT.equals(scrnMsg.xxModeCd.getValue())) {
                scrnMsg.A.no(i).psnCd_A2.setInputProtected(false);
                handler.setButtonEnabled("OpenWin_Rep", i, true);
            }
        }
    }

    private static void setRowColors(NSAL1410BMsg scrnMsg) {
        S21TableColorController control = new S21TableColorController(SCREEN_ID, scrnMsg);
        control.setAlternateRowsBG("A", scrnMsg.A);
    }

    private static boolean hasUpdateFuncId() {
        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Contract Branch Rep Update (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }
        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }
        return false;
    }

    /**
     * addCheckItemHeader
     * @param scrnMsg NSAL1410BMsg
     */
    public static void addCheckItemHeader(NSAL1410BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_H);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrCd_H);
        scrnMsg.addCheckItem(scrnMsg.svcContrBrDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.psnCd_H);
        scrnMsg.addCheckItem(scrnMsg.xxPsnNm_H);
        scrnMsg.addCheckItem(scrnMsg.billToCustCd_H);
        scrnMsg.addCheckItem(scrnMsg.locNm_H);
    }

    /**
     * addCheckItemDetail
     * @param scrnMsg NSAL1410BMsg
     */
    public static void addCheckItemDetail(NSAL1410BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).psnCd_A2);
        }
    }

    /**
     * getSelectSQL
     * @param scrnMsg NSAL1410BMsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @return String
     */
    public static String getSelectSQL(NSAL1410BMsg scrnMsg, String glblCmpyCd, String slsDt) {
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
        sb.append("             ,NVL(B1.EFF_THRU_DT, #slsDt#)  AS EFF_THRU_DT");
        sb.append("             ,B1.SVC_ORG_FUNC_ROLE_TP_CD");
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
        sb.append("             ,NVL(C1.EFF_THRU_DT, '#slsDt#')  AS EFF_THRU_DT");
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
        sb.append("             ,NVL(E1.EFF_THRU_DT, '#slsDt#')  AS EFF_THRU_DT");
        sb.append("         FROM");
        sb.append("             SVC_RG E1");
        sb.append("         WHERE");
        sb.append("             E1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append("         AND E1.EZCANCELFLAG          = '0'");
        sb.append("         AND E1.SVC_RG_ACTV_FLG       = 'Y'");
        sb.append("      ) E");
        sb.append("     ,ORG_FUNC_ROLE_TP F");
        sb.append(" WHERE");
        sb.append("     A.GLBL_CMPY_CD          = '#glblCmpyCd#'");
        sb.append(" AND A.EZCANCELFLAG          = '0'");
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
        sb.append(" AND B.GLBL_CMPY_CD              = F.GLBL_CMPY_CD");
        sb.append(" AND B.SVC_ORG_FUNC_ROLE_TP_CD   = F.ORG_FUNC_ROLE_TP_CD");
        sb.append(" AND F.EZCANCELFLAG              = '0'");
        sb.append(" AND F.ASG_CONTR_FLG             = 'Y'");
        sb.append(" AND F.FIRST_ORG_CD              = '#firstOrgCd#'");

        String sql = sb.toString();
        sql = sql.replaceAll("#glblCmpyCd#", glblCmpyCd);
        sql = sql.replaceAll("#slsDt#", slsDt);
        sql = sql.replaceAll("#firstOrgCd#", firstOrgCd);
        return sql;
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NSAL1410BMsg
     * @return List
     */
    public static List<Object> getSearchConditionSetting(NSAL1410BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Person Code", "PSN_CD", scrnMsg.psnCd_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Person Name", "PSN_NM", scrnMsg.xxPsnNm_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Line Of Business", "SVC_LINE_BIZ_CD", scrnMsg.svcLineBizCd_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj4 = {"Branch Code", "SVC_CONTR_BR_CD", scrnMsg.svcContrBrCd_P.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj5 = {"Branch Name", "SVC_CONTR_BR_DESC_TXT", scrnMsg.svcContrBrDescTxt_P.getValue(), ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        return whereList;
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NSAL1410BMsg
     * @return List
     */
    public static List<Object> getDisplayColumnSetting(NSAL1410BMsg scrnMsg) {
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
     * @param scrnMsg NSAL1410BMsg
     * @return List
     */
    public static List<Object> getSortSetting(NSAL1410BMsg scrnMsg) {
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
     * clear PopupData For ScrnMsg
     * @param scrnMsg NSAL1410BMsg
     */
    public static final void clearPopupDataForScrnMsg(NSAL1410BMsg scrnMsg) {
        scrnMsg.xxPopPrm_00.clear();
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
    }
}

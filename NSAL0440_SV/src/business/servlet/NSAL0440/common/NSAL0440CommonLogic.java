/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0440.common;

import static business.servlet.NSAL0440.constant.NSAL0440Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0440.NSAL0440BMsg;
import business.servlet.NSAL0440.NSAL0440Bean;
import business.servlet.NSAL0440.NSAL0440_ABMsg;
import business.servlet.NSAL0440.constant.NSAL0440Constant;

/**
 *<pre>
 * Terms & Conditions
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/25   Hitachi         T.Iwamoto       Create          N/A
 * 2016/02/17   Hitachi         K.Kasai         Update          QC#3018
 * 2016/03/28   Hitachi         T.Tomita        Update          QC#3018
 * 2016/09/29   Hitachi         A.Kohinata      Update          QC#12898
 * 2016/11/01   Hitachi         T.Kanasaka      Update          QC#15136
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 * 2018/07/31   Hitachi         A.Kohinata      Update          QC#26659
 * 2022/08/03   Hitachi         N.Takatsu       Update          QC#60077
 *</pre>
 */
public class NSAL0440CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0440BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NSAL0440BMsg scrnMsg) {
        initCommonButton(handler, scrnMsg);
        controlScreenFields(handler, scrnMsg);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param handler EZ Common Handler
     * @param scrnMsg NSAL0440BMsg
     */
    private static final void initCommonButton(EZDCommonHandler handler, NSAL0440BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK6[0], BTN_CMN_BLANK6[1], BTN_CMN_BLANK6[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        // START 2022/08/03 N.Takatsu [QC#60077, MOD]
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 1, null);
        // END 2022/08/03 N.Takatsu [QC#60077, MOD]      
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
        if (hasUpdateFuncId() && MODE_UPDATE.equals(scrnMsg.xxModeCd_P.getValue())) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0440BMsg
     */
    private static final void controlScreenFields(EZDCommonHandler handler, NSAL0440BMsg scrnMsg) {
        controlScreenHeaderFields(handler, scrnMsg);
        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * hasRegistFuncId
     * @return boolean true:upedate false:reference
     */
    private static boolean hasUpdateFuncId() {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (funcList == null || funcList.isEmpty()) {
            throw new S21AbendException("You can't operate Terms & Conditions (" + BUSINESS_ID + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
        }

        for (String func : funcList) {
            if (AUTH_UPDATE.equals(func)) {
                return true;
            }
        }

        return false;
    }

    /**
     * controlScreenHeaderFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0440BMsg
     */
    private static final void controlScreenHeaderFields(EZDCommonHandler handler, NSAL0440BMsg scrnMsg) {
        // add start 2016/02/17 CSA Defect#3018
        // mod start 2016/03/28 CSA Defect#3018
        scrnMsg.serNum.setInputProtected(false);
        scrnMsg.serNum_AR.setInputProtected(false);
        // START 2022/08/03 N.Takatsu [QC#60077, MOD]
        // scrnMsg.svcTermCondCatgNm_PS.setInputProtected(false);
        for (int i = 0;i < scrnMsg.X.getValidCount();i++) {
            scrnMsg.X.no(i).svcTermCondCatgDescTxt_X.setInputProtected(true);
        }
        // END 2022/08/03 N.Takatsu [QC#60077, MOD]
        scrnMsg.xxChkBox_H1.setInputProtected(false);
        scrnMsg.xxChkBox_H2.setInputProtected(false);
        handler.setButtonEnabled(NSAL0440Constant.BTN_SEARCH, true);
        if (isProtectHdr(scrnMsg)) {
            handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        }
        // mod end 2016/03/28 CSA Defect#3018
        // add end 2016/02/17 CSA Defect#3018
    }

    // add start 2016/02/17 CSA Defect#3018
    /**
     * is Protect Header
     * @param scrnMsg
     */
    private static boolean isProtectHdr(NSAL0440BMsg scrnMsg) {
        // mod start 2016/03/28 CSA Defect#3018
        String dsContrStsCd = scrnMsg.A.no(0).dsContrStsCd_A.getValue();
        if (DS_CONTR_STS.TERMINATED.equals(dsContrStsCd) || DS_CONTR_STS.EXPIRED.equals(dsContrStsCd)
                || DS_CONTR_STS.CANCELLED.equals(dsContrStsCd)) {
            return true;
        }
        return false;
        // mod end 2016/03/28 CSA Defect#3018
    }
    /**
     * is Protect Detail
     * @param scrnMsg
     */
    private static boolean isProtectDtl(NSAL0440_ABMsg detailMsg) {
        // mod start 2016/03/28 CSA Defect#3018
        String dsContrDtlStsCd = detailMsg.dsContrDtlStsCd_A.getValue();
        if (!ZYPCommonFunc.hasValue(dsContrDtlStsCd)) {
            return false;
        }
        if (DS_CONTR_DTL_STS.TERMINATED.equals(dsContrDtlStsCd) || DS_CONTR_DTL_STS.EXPIRED.equals(dsContrDtlStsCd)
                || DS_CONTR_DTL_STS.CANCELLED.equals(dsContrDtlStsCd)) {
            return true;
        }
        return false;
        // mod end 2016/03/28 CSA Defect#3018
    }
    // add end 2016/02/17 CSA Defect#3018

    /**
     * controlScreenDetailFields
     * @param scrnMsg NSAL0440BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NSAL0440BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // mod start 2016/02/17 CSA Defect#3018
            NSAL0440_ABMsg detailMsg = scrnMsg.A.no(i);
            // START 2016/11/01 T.Kanasaka [QC#15136, MOD]
//            if (notProtectContrLineA(scrnMsg) && !isProtectHdr(scrnMsg) && !isProtectDtl(detailMsg)) {
            if (notProtectContrLineA(scrnMsg) && !isProtectHdr(scrnMsg) && !isProtectDtl(detailMsg) && !ZYPConstant.FLG_OFF_N.equals(detailMsg.attrbUpdAvalFlg_A.getValue())) {
            // END 2016/11/01 T.Kanasaka [QC#15136, MOD]
            // mod end 2016/02/17 CSA Defect#3018
                scrnMsg.A.no(i).svcTermAttrbMapValCd_A.setInputProtected(false);
                scrnMsg.A.no(i).svcTermCondDataValCd_PS.setInputProtected(false);
                scrnMsg.A.no(i).condValNum_A.setInputProtected(false);
                scrnMsg.A.no(i).xxTrxDt_A.setInputProtected(false);
                scrnMsg.A.no(i).svcTermAttrbMapValCd_M.setInputProtected(false);
                scrnMsg.A.no(i).svcTermCondDataValCd_MS.setInputProtected(false);
                scrnMsg.A.no(i).condValNum_M.setInputProtected(false);
                scrnMsg.A.no(i).xxTrxDt_M.setInputProtected(false);
                // add start 2018/06/25 QC#17369
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(scrnMsg.A.no(i).svcTermDataTpCd_A.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbMapValCd_A.setInputProtected(true);
                }
                if (SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(scrnMsg.A.no(i).svcTermDataTpCd_M.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbMapValCd_M.setInputProtected(true);
                }
                // add end 2018/06/25 QC#17369
                // START 2016/09/29 A.Kohinata [QC#12898, ADD]
                setProtectedMachineLevel(scrnMsg, detailMsg);
                // END 2016/09/29 A.Kohinata [QC#12898, ADD]
            } else {
                scrnMsg.A.no(i).svcTermAttrbMapValCd_A.setInputProtected(true);
                scrnMsg.A.no(i).svcTermCondDataValCd_PS.setInputProtected(true);
                scrnMsg.A.no(i).condValNum_A.setInputProtected(true);
                scrnMsg.A.no(i).xxTrxDt_A.setInputProtected(true);
                scrnMsg.A.no(i).svcTermAttrbMapValCd_M.setInputProtected(true);
                scrnMsg.A.no(i).svcTermCondDataValCd_MS.setInputProtected(true);
                scrnMsg.A.no(i).condValNum_M.setInputProtected(true);
                scrnMsg.A.no(i).xxTrxDt_M.setInputProtected(true);
            }
            scrnMsg.A.no(i).svcTermCondCatgDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).svcTermAttrbDescTxt_A.setInputProtected(true);
            scrnMsg.A.no(i).serNum_A.setInputProtected(true);
            scrnMsg.A.no(i).condValNum_A.setAppFracDigit(0);
            scrnMsg.A.no(i).condValNum_M.setAppFracDigit(0);
        }
    }

    /**
     * notProtectContrLine
     * @param scrnMsg
     */
    private static boolean notProtectContrLineA(NSAL0440BMsg scrnMsg) {
        if (!hasUpdateFuncId()) {
            return false;
        }
        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_P.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkInputForTable
     * @param scrnMsg NSAL0440BMsg
     */
    public static void checkInputForTable(NSAL0440BMsg scrnMsg) {
        scrnMsg.A.setCheckParam(new String[] {NSAL0440Bean.svcTermAttrbMapValCd_A, NSAL0440Bean.condValNum_A, NSAL0440Bean.xxTrxDt_A, NSAL0440Bean.svcTermAttrbMapValCd_M, NSAL0440Bean.condValNum_M, NSAL0440Bean.xxTrxDt_M }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);

        // add start 2018/07/31 QC#26659
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(scrnMsg.A.no(i).svcTermDataTpCd_A.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermAttrbMapValCd_A) && !ZYPCommonFunc.isNumberCheck(scrnMsg.A.no(i).svcTermAttrbMapValCd_A.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbMapValCd_A.setErrorInfo(1, ZZM9004E, new String[] {"Contract T & Cs" });
                }
            }
            if (SVC_TERM_ATTRB_DATA_TP.TEXT_NUMERIC.equals(scrnMsg.A.no(i).svcTermDataTpCd_M.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).svcTermAttrbMapValCd_M) && !ZYPCommonFunc.isNumberCheck(scrnMsg.A.no(i).svcTermAttrbMapValCd_M.getValue())) {
                    scrnMsg.A.no(i).svcTermAttrbMapValCd_M.setErrorInfo(1, ZZM9004E, new String[] {"Machine T & Cs" });
                }
            }
        }
        // add end 2018/07/31 QC#26659
    }

    // START 2016/09/29 A.Kohinata [QC#12898, ADD]
    private static void setProtectedMachineLevel(NSAL0440BMsg scrnMsg, NSAL0440_ABMsg detailMsg) {
        for (int idx = 0; idx < scrnMsg.Y.getValidCount(); idx++) {
            if (detailMsg.svcTermCondAttrbPk_A.getValue().compareTo(scrnMsg.Y.no(idx).svcTermCondAttrbPk_Y.getValue()) == 0) {
                detailMsg.svcTermAttrbMapValCd_M.setInputProtected(true);
                detailMsg.svcTermCondDataValCd_MS.setInputProtected(true);
                detailMsg.condValNum_M.setInputProtected(true);
                detailMsg.xxTrxDt_M.setInputProtected(true);
                break;
            }
        }
    }
    // END 2016/09/29 A.Kohinata [QC#12898, ADD]

    // add start 2018/06/25 QC#17369
    /**
     * createPopupParams
     * @param scrnMsg NSAL0440BMsg
     * @param selectNumber int
     * @return Object[]
     */
    public static Object[] createPopupParams(NSAL0440BMsg scrnMsg, int selectNumber) {
        NSAL0440_ABMsg abMsg = scrnMsg.A.no(selectNumber);

        Object[] params = new Object[7];
        params[0] = "";

        StringBuilder sb = new StringBuilder();
        sb.append(abMsg.logicMaintTrgtTblNm_A.getValue()).append(" Search");
        params[1] = sb.toString();

        sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     GLBL_CMPY_CD ");
        sb.append("    ,EZCANCELFLAG ");
        sb.append("    ,").append(abMsg.physMaintTrgtColNm_A.getValue()).append(" ");
        if (!abMsg.physMaintTrgtColNm_A.getValue().equals(abMsg.physDplyTrgtColNm_A.getValue())) {
            sb.append("    ,").append(abMsg.physDplyTrgtColNm_A.getValue()).append(" ");
        }
        sb.append("FROM ");
        sb.append("    ").append(abMsg.physMaintTrgtTblNm_A.getValue()).append(" ");
        sb.append("WHERE ");
        sb.append("        GLBL_CMPY_CD  = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("    AND EZCANCELFLAG  = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        if (abMsg.logicDplyTrgtColNm_A.getValue().length() > 20) {
            whereArray0[0] = abMsg.logicDplyTrgtColNm_A.getValue().substring(0, 20);
        } else {
            whereArray0[0] = abMsg.logicDplyTrgtColNm_A.getValue();
        }
        whereArray0[1] = abMsg.physDplyTrgtColNm_A.getValue();
        if ("OpenWin_ContrCondVal".equals(scrnMsg.xxScrEventNm.getValue())) {
            whereArray0[2] = scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_A.getValue();
        } else {
            whereArray0[2] = scrnMsg.A.no(selectNumber).svcTermAttrbMapValCd_M.getValue();
        }
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        if (abMsg.logicMaintTrgtColNm_A.getValue().length() > 30) {
            columnArray0[0] = abMsg.logicMaintTrgtColNm_A.getValue().substring(0, 30);
        } else {
            columnArray0[0] = abMsg.logicMaintTrgtColNm_A.getValue();
        }
        columnArray0[1] = abMsg.physMaintTrgtColNm_A.getValue();
        columnArray0[2] = BigDecimal.valueOf(45);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        if (!abMsg.physMaintTrgtColNm_A.getValue().equals(abMsg.physDplyTrgtColNm_A.getValue())) {
            Object[] columnArray1 = new Object[4];
            if (abMsg.logicDplyTrgtColNm_A.getValue().length() > 30) {
                columnArray1[0] = abMsg.logicDplyTrgtColNm_A.getValue().substring(0, 30);
            } else {
                columnArray1[0] = abMsg.logicDplyTrgtColNm_A.getValue();
            }
            columnArray1[1] = abMsg.physDplyTrgtColNm_A.getValue();
            columnArray1[2] = BigDecimal.valueOf(45);
            columnArray1[3] = "N";
            columnList.add(columnArray1);
        }

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = abMsg.physMaintTrgtColNm_A.getValue();
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
    // add end 2018/06/25 QC#17369
}

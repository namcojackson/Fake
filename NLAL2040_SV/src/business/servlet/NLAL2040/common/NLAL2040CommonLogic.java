/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040.common;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.*;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_MDL_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_MDL_NM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_MDL_NM_V;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_MDL_POP;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_T_MDL_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.P_T_MDL_NM;
import business.servlet.NLAL2040.NLAL2040BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040CommonLogic {

    /**
     * Do addCheckItem for items
     * @param scrnMsg NPAL2040BMsg
     */
    public static void addCheckItem(NLAL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effFromDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).effThruDt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).t_MdlNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).svcSegDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaMdseTpCd_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromMtrCnt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).toMtrCnt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).fromElpsMthAot_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).toElpsMthAot_A1);
        }
    }


    /**
     * Controll Screen Activate
     * @param handler S21CommonHandler
     * @param scrnMsg NPAL2040BMsg
     */
    public static void cntrlScreen(S21CommonHandler handler, NLAL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)
                && ZYPDateUtil.compare(scrnMsg.A.no(i).effThruDt_A1.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) < 0) {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(true);
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
                scrnMsg.A.no(i).svcSegDescTxt_A1.setInputProtected(true);
                scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(true);
                scrnMsg.A.no(i).fromMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).toMtrCnt_A1.setInputProtected(true);
                scrnMsg.A.no(i).fromElpsMthAot_A1.setInputProtected(true);
                scrnMsg.A.no(i).toElpsMthAot_A1.setInputProtected(true);
                scrnMsg.A.no(i).rtlSwhCd_AS.setInputProtected(true);
                scrnMsg.A.no(i).thirdPtyDspTpCd_AS.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
                scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(false);
                scrnMsg.A.no(i).svcSegDescTxt_A1.setInputProtected(false);
                scrnMsg.A.no(i).coaMdseTpCd_A1.setInputProtected(false);
                scrnMsg.A.no(i).fromMtrCnt_A1.setInputProtected(false);
                scrnMsg.A.no(i).toMtrCnt_A1.setInputProtected(false);
                scrnMsg.A.no(i).fromElpsMthAot_A1.setInputProtected(false);
                scrnMsg.A.no(i).toElpsMthAot_A1.setInputProtected(false);
                scrnMsg.A.no(i).rtlSwhCd_AS.setInputProtected(false);
                scrnMsg.A.no(i).thirdPtyDspTpCd_AS.setInputProtected(false);
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).t_MdlNm_A1) && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).sqId_A1)) {
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(true);
                handler.setButtonEnabled(EVENT_OPEN_WIN_MDSE_DETAIL, i, false);
            } else {
                scrnMsg.A.no(i).t_MdlNm_A1.setInputProtected(false);
                handler.setButtonEnabled(EVENT_OPEN_WIN_MDSE_DETAIL, i, true);
            }
            scrnMsg.A.no(i).svcSegDescTxt_A1.setInputProtected(true);
        }
    }


    /**
     * The method explanation: set initial parameter to call popup.(NMAL6050)
     * @param scrnMsg NLAL2040BMsg
     */
    public static void setInitParamForItemMasterPopup(NLAL2040BMsg scrnMsg) {
        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.xxCondCd_P1.clear();
        scrnMsg.xxCondNm_P1.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, P_MDL_NM_V);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, P_T_MDL_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, P_T_MDL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, P_T_MDL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, P_MDL_POP);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, P_MDL_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, P_MDL_NM);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, P_MDL_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, P_MDL_NM);
    }


    /**
     * The method explanation: set parameter to call popup.(NMAL6050)
     * @param scrnMsg NLAL2040BMsg
     * @return Object[]
     */
    public static Object[] setParamForItemMasterPopup(NLAL2040BMsg scrnMsg) {
        Object[] params = new Object[P11];
        params[P0] = scrnMsg.xxTblNm_P1;
        params[P1] = scrnMsg.xxTblCdColNm_P1;
        params[P2] = scrnMsg.xxTblNmColNm_P1;
        params[P3] = scrnMsg.xxTblSortColNm_P1;
        params[P4] = scrnMsg.xxScrNm_P1;
        params[P5] = scrnMsg.xxHdrCdLbNm_P1;
        params[P6] = scrnMsg.xxHdrNmLbNm_P1;
        params[P7] = scrnMsg.xxDtlCdLbNm_P1;
        params[P8] = scrnMsg.xxDtlNmLbNm_P1;
        params[P9] = scrnMsg.xxCondCd_P1;
        params[P10] = scrnMsg.xxCondNm_P1;
        return params;
    }

}

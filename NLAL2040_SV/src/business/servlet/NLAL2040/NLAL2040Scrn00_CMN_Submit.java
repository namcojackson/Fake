/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2040;

import static business.servlet.NLAL2040.constant.NLAL2040Constant.AGE_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.AGE_TO;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.BIZ_APP_ID;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.DT_ACT;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.DT_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.METER_FROM;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.METER_TO;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.MODEL;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.NLAM1296E;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.NLBM1231E;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.NMAM0836E;
import static business.servlet.NLAL2040.constant.NLAL2040Constant.TERMINATED_EFF_DT;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL2040.NLAL2040CMsg;
import business.servlet.NLAL2040.common.NLAL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        inputCheck(scrnMsg);
        NLAL2040CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg = new NLAL2040CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NLAL2040BMsg scrnMsg = (NLAL2040BMsg) bMsg;
        NLAL2040CMsg bizMsg  = (NLAL2040CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NLAL2040CommonLogic.cntrlScreen(this, scrnMsg);
        NLAL2040CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        scrnMsg.setFocusItem(scrnMsg.t_MdlNm);
    }

    /**
     * @param scrnMsg NLAL2040BMsg
     * @return boolean
     */
    private boolean inputCheck(NLAL2040BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effFromDt_A1)) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAM0836E, new String[] {DT_FROM });
                return false;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).t_MdlNm_A1)) {
                scrnMsg.A.no(i).t_MdlNm_A1.setErrorInfo(1, NMAM0836E, new String[] {MODEL });
                return false;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1)
                 && ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue())) < 0) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NLBM1231E, new String[] {DT_FROM });
                return false;
            }

            String effThruDt = TERMINATED_EFF_DT;
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).effThruDt_A1)) {
                effThruDt = scrnMsg.A.no(i).effThruDt_A1.getValue();
            }

            if (ZYPDateUtil.compare(scrnMsg.A.no(i).effFromDt_A1.getValue(), effThruDt) > 0) {
                scrnMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NLAM1296E, new String[] {DT_ACT });
                return false;
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromMtrCnt_A1) && (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).fromMtrCnt_A1.getValue()) > 0)) {
                scrnMsg.A.no(i).fromMtrCnt_A1.setErrorInfo(1, "NWBM0062E", new String[] {METER_FROM });
                return false;
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).toMtrCnt_A1) && (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).toMtrCnt_A1.getValue()) > 0)) {
                scrnMsg.A.no(i).toMtrCnt_A1.setErrorInfo(1, "NWBM0062E", new String[] {METER_TO });
                return false;
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromElpsMthAot_A1) && (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).fromElpsMthAot_A1.getValue()) > 0)) {
                scrnMsg.A.no(i).fromElpsMthAot_A1.setErrorInfo(1, "NWBM0062E", new String[] {AGE_FROM });
                return false;
            }

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).toElpsMthAot_A1) && (BigDecimal.ZERO.compareTo(scrnMsg.A.no(i).toElpsMthAot_A1.getValue()) > 0)) {
                scrnMsg.A.no(i).toElpsMthAot_A1.setErrorInfo(1, "NWBM0062E", new String[] {AGE_TO });
                return false;
            }


        }

        return true;
    }

}

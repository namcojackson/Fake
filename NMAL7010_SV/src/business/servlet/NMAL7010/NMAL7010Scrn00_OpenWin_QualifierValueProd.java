/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7010;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7010.common.NMAL7010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7010Scrn00_OpenWin_QualifierValueProd
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         M.Nakamura      Create          N/A
 *</pre>
 */
public class NMAL7010Scrn00_OpenWin_QualifierValueProd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7010BMsg scrnMsg = (NMAL7010BMsg) bMsg;

        if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(getButtonSelectNumber()).prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueProd-1");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(getButtonSelectNumber()).prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueProd-2");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(getButtonSelectNumber()).prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueProd-3");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(getButtonSelectNumber()).prcQlfyTpCd_PA.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueProd-4");
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm_DH, "OpenWin_QualifierValueProd-5");
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxCellIdx, new BigDecimal(getButtonSelectNumber()));

        setArgForSubScreen(NMAL7010CommonLogic.setArgumentNWAL1130(scrnMsg, scrnMsg.xxScrEventNm_DH.getValue(), getButtonSelectNumber(), getGlobalCompanyCode()));

    }
}

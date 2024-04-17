/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7040;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7040.common.NMAL7040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_QLFY_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Price List Filter Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/23   SRA             E.Inada         Create          N/A
 *</pre>
 */
public class NMAL7040Scrn00_OpenWin_QualifierValueProd extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        //
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7040BMsg scrnMsg = (NMAL7040BMsg) bMsg;

        if (PRC_QLFY_TP.PRODUCT_HIERARCHY_1.equals(scrnMsg.prcQlfyTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_QualifierValueProd-1");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_2.equals(scrnMsg.prcQlfyTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_QualifierValueProd-2");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_3.equals(scrnMsg.prcQlfyTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_QualifierValueProd-3");
        } else if (PRC_QLFY_TP.PRODUCT_HIERARCHY_4.equals(scrnMsg.prcQlfyTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_QualifierValueProd-4");
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, "OpenWin_QualifierValueProd-5");
        }

        setArgForSubScreen(NMAL7040CommonLogic.setArgumentNWAL1130(scrnMsg, scrnMsg.xxScrEventNm.getValue(), getButtonSelectNumber(), getGlobalCompanyCode()));
    }
}

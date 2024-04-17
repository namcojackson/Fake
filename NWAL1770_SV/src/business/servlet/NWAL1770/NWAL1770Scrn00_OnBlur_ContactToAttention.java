/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NWAL1770.NWAL1770CMsg;
//import business.servlet.NWAL1770.constant.NWAL1770Constant;

import business.servlet.NWAL1770.constant.NWAL1770Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/10/18   Fujitsu         W.Honda         Create          S21_NA#20246-1(L3 Sol#454)
 *</pre>
 */
public class NWAL1770Scrn00_OnBlur_ContactToAttention extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        int row = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.sellToFirstRefCmntTxt)) {

            if (CTAC_TP.ORDER_CONTACT.equals(scrnMsg.A.no(row).ctacPsnTpCd_A.getValue())
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(row).ctacPsnFirstNm_A)
                    && ZYPCommonFunc.hasValue(scrnMsg.A.no(row).ctacPsnLastNm_A)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.sellToFirstRefCmntTxt, scrnMsg.A.no(row).ctacPsnFirstNm_A.getValue()
                        + NWAL1770Constant.SPACE
                        + scrnMsg.A.no(row).ctacPsnLastNm_A.getValue());
            }

        }

    }
}

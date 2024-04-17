/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL7230.constant.NMAL7230Constant.EVENT_NM_OPENWIN_CUSTGRP;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7230_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NMAL7230_NWAL1130 extends S21CommonHandler {

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

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        int index = getButtonSelectNumber();

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if (EVENT_NM_OPENWIN_CUSTGRP.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).prcGrpNm_A1, scrnMsg.P.no(0).xxComnScrColValTxt);

            }

        }
        scrnMsg.setFocusItem(scrnMsg.A.no(index).prcGrpNm_A1);
    }
}

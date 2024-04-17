/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/17   Fujitsu         Kamide          Create          N/A
 * 2016/09/12   Hitachi         K.Kojima        Update          QC#13257
 *</pre>
 */
public class NFDL0060_NMAL6760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2016/09/12 K.Kojima [QC#13257,ADD]
        NFDL0060BMsg scrnMsg = (NFDL0060BMsg) bMsg;
        if (!"CMN_Close".equals(getLastGuard())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.M.no(0).xxPopPrm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.xxQueryFltrTxt_H2)) {
                    if ((scrnMsg.xxQueryFltrTxt_H2.getValue().length() + scrnMsg.M.no(0).xxPopPrm.getValue().length() + 1) <= 200) {
                        scrnMsg.xxQueryFltrTxt_H2.setValue(scrnMsg.xxQueryFltrTxt_H2.getValue().concat(",").concat(scrnMsg.M.no(0).xxPopPrm.getValue()));
                    }
                } else {
                    scrnMsg.xxQueryFltrTxt_H2.setValue(scrnMsg.M.no(0).xxPopPrm.getValue());
                }
                scrnMsg.setFocusItem(scrnMsg.xxQueryFltrTxt_H2);
            }
        }
        // END 2016/09/12 K.Kojima [QC#13257,ADD]
    }
}

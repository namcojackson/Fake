/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/31   Hitachi         T.Tsuchida      Create          QC#19574
 *</pre>
 */
public class NFDL0010_NFDL0170 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFDL0010BMsg scrnMsg = (NFDL0010BMsg) bMsg;
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
    }
}

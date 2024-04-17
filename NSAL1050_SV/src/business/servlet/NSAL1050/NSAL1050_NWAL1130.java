/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * T&C Attributes Setup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   CUSA            T.Mizuki         Create          N/A
 *</pre>
 */
public class NSAL1050_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        int index = scrnMsg.xxNum.getValueInt();
        String txt = scrnMsg.B.no(0).xxComnScrColValTxt.getValue();
        String cd = scrnMsg.B.no(1).xxComnScrColValTxt.getValue();

        if (!"CMN_Close".equals(getLastGuard())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).svcTermCondSrcDescTxt_A, txt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).svcTermCondDataSrcCd_A, cd);
        }
        // focus
        scrnMsg.setFocusItem(scrnMsg.A.no(index).svcTermAttrbSortNum_A);
    }
}

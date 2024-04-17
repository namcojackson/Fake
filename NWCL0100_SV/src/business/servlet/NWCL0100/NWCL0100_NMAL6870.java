/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.NWCM0129W;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0100_NMAL6870
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0100_NMAL6870 extends S21CommonHandler {

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

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        StringBuilder mdl = new StringBuilder();
        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (i != 0) {
                mdl.append(",");
            }
            mdl.append(scrnMsg.P.no(i).xxComnScrColValTxt_1.getValue());
        }

        if (mdl.length() <= scrnMsg.getAttr(NWCL0100Bean.xxBillToAcctCdSrchTxt_H0).getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt_H0, mdl.toString());
        } else {
            scrnMsg.setMessageInfo(NWCM0129W, new String[] {scrnMsg.xxBillToAcctCdSrchTxt_H0.getNameForMessage() });
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt_H0, mdl.toString().substring(0, scrnMsg.getAttr(NWCL0100Bean.xxBillToAcctCdSrchTxt_H0).getDigit()));
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxBillToAcctCdSrchTxt_H0, mdl.toString());

        scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt_H0);
    }
}

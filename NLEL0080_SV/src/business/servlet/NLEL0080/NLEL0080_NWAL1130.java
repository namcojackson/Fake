/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import static business.servlet.NLEL0080.constant.NLEL0080Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/04   Hitachi         J.Kim           Create          N/A
 * 2016/07/20   Hitachi         T.Tsuchida      Update          QC#8092
 * 2016/09/15   Hitachi         J.Kim           Update          QC#10360
 *</pre>
 */
public class NLEL0080_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        int index = getButtonSelectNumber();
        if (index >= 0) {
            // START 2016/09/14 J.Kim [QC#10360,MOD]
            if (OPENWIN_VENDOR.equals(scrnMsg.xxScrEventNm.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).vndTpDescTxt, scrnMsg.P.no(IDX_1).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(index).vndTpDescTxt);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).slsRepTocCd, scrnMsg.P.no(IDX_0).xxComnScrColValTxt);
                scrnMsg.setFocusItem(scrnMsg.A.no(index).slsRepTocCd);
            }
            // END 2016/09/14 J.Kim [QC#10360,MOD]
        }
    }
}

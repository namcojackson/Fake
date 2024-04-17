/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/04/11   Hitachi         T.Tsuchida      Update          QC#6823
 * 2016/05/20   Hitachi         T.Tsuchida      Update          QC#8096
 * 2016/09/15   Hitachi         J.Kim           Update          QC#10360
 *</pre>
 */
public class NLEL0080_NMAL6760 extends S21CommonHandler {

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
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).sellToCustCd, scrnMsg.xxPopPrm_P0.getValue());
            // START 2016/09/15 J.Kim [QC#10360,ADD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).firstLineAddr, scrnMsg.xxPopPrm_P4.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).ctyAddr, scrnMsg.xxPopPrm_P5.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).stCd, scrnMsg.xxPopPrm_P6.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(index).postCd, scrnMsg.xxPopPrm_P7.getValue());
            // END 2016/09/15 J.Kim [QC#10360,ADD]
            scrnMsg.setFocusItem(scrnMsg.A.no(index).sellToCustCd);
        }
    }
}

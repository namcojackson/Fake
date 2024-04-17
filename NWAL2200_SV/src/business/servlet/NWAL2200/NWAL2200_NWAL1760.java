/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.TAB_LINE_CONFIG;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200_NWAL1760
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 *</pre>
 */
public class NWAL2200_NWAL1760 extends S21CommonHandler {

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

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        int slctLine = scrnMsg.xxCellIdx.getValueInt();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (slctLine < 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.P.no(2).xxPopPrm);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).prcCatgNm_LL, scrnMsg.prcCatgNm);
            }
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).prcCatgNm_RL, scrnMsg.prcCatgNm);
            }
            scrnMsg.setFocusItem(scrnMsg.custIssPoNum);
        } else {
            if (TAB_LINE_CONFIG.equals(dplyTab)) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).prcCatgNm_LL, scrnMsg.P.no(2).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).prcCatgNm_LL);
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(slctLine).prcCatgNm_RL, scrnMsg.P.no(2).xxPopPrm);
                scrnMsg.setFocusItem(scrnMsg.D.no(slctLine).prcCatgNm_RL);
            }
        }
    }
}

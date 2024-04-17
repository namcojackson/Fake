/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0450;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import static business.servlet.NSBL0450.constant.NSBL0450Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Supplemental Task
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/05   Hitachi         T.Iwamoto         Create          N/A
 *</pre>
 */
public class NSBL0450_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
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
        NSBL0450BMsg scrnMsg = (NSBL0450BMsg) bMsg;

        getArgForSubScreen();

        if (OPENWIN_TECH.equals(scrnMsg.xxScrEventNm.getValue())) {
            setScrPrmTechnician(scrnMsg);
        } else if (OPENWIN_MANAGER.equals(scrnMsg.xxScrEventNm.getValue())) {
            setScrPrmParentManager(scrnMsg);
        }
    }

    private void setScrPrmTechnician(NSBL0450BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            if ("PSN_NM".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.techPsnNm_S, scrnMsg.X.no(i).xxComnScrColValTxt);
                } else {
                    scrnMsg.techPsnNm_S.clear();
                }
            }
        }
    }

    private void setScrPrmParentManager(NSBL0450BMsg scrnMsg) {
        for (int i = 0; i < scrnMsg.X.length(); i++) {
            if ("PSN_NM".equals(scrnMsg.X.no(i).xxComnScrQueryColNm.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.X.no(i).xxComnScrColValTxt)) {
                    setValue(scrnMsg.mgrNm_S, scrnMsg.X.no(i).xxComnScrColValTxt);
                } else {
                    scrnMsg.mgrNm_S.clear();
                }
            }
        }
    }
}

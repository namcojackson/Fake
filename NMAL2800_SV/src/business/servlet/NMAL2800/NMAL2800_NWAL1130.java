/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BTN_CMN_CLS;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800_NWAL1130
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 *</pre>
 */
public class NMAL2800_NWAL1130 extends S21CommonHandler {

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

        if (BTN_CMN_CLS.equals(getLastGuard())) {
            return;
        }

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);

        if (!ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_AB)) {
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsXrefAcctCd_A1, scrnMsg.Q.no(0).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(aBMsg.aftDsXrefAcctCd_A1);
        }
    }
}

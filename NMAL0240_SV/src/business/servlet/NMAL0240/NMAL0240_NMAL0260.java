/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0240;

import static business.servlet.NMAL0240.constant.NMAL0240Constant.BIZ_ID;
import static business.servlet.NMAL0240.constant.NMAL0240Constant.FUNC_CD_20;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0240.NMAL0240CMsg;
import business.servlet.NMAL0240.common.NMAL0240CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL0240_NMAL0260
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Fujitsu         C.Tanaka        Create          CSA
 * 2016/02/18   SRAA            Y.Chen          Update          QC#2645
 *</pre>
 */
public class NMAL0240_NMAL0260 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;

        if (0 < scrnMsg.A.getValidCount()) {
            String newFromDt = scrnMsg.C.no(0).effFromDt_C1.getValue();
            String endDt = ZYPDateUtil.addDays(newFromDt, -1);
            String effThruDt = scrnMsg.effThruDt_A.getValue();
            String effFromDt = scrnMsg.effFromDt_A.getValue();
            if ((0 <= ZYPDateUtil.compare(newFromDt, effFromDt) && 0 < ZYPDateUtil.compare(effThruDt, endDt)) || !ZYPCommonFunc.hasValue(scrnMsg.effThruDt_A)) {
                effThruDt = endDt;
            }

            if (0 > ZYPDateUtil.compare(effThruDt, effFromDt)) {
                effThruDt = effFromDt;
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_B, effThruDt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_C, scrnMsg.C.no(0).effFromDt_C1);
        }

        NMAL0240CMsg bizMsg = new NMAL0240CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NMAL0240BMsg scrnMsg = (NMAL0240BMsg) bMsg;
        NMAL0240CMsg bizMsg = (NMAL0240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        ZYPEZDItemValueSetter.setValue(scrnMsg.effFromDt_A, scrnMsg.C.no(0).effFromDt_C1.getValue());

        scrnMsg.setFocusItem(scrnMsg.effFromDt_A);

        NMAL0240CommonLogic.controlFields(this, scrnMsg, true);
        NMAL0240CommonLogic.controlRevisionFields(this, scrnMsg);
        // QC#2645
        NMAL0240CommonLogic.controlTableRowColor(scrnMsg);

        scrnMsg.C.clear();
    }
}

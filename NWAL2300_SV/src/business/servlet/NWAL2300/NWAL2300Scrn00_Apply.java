/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2300Scrn00_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2300BMsg scrnMsg = (NWAL2300BMsg) bMsg;

        BigDecimal radio = scrnMsg.xxRadioBtn_H1.getValue();
        if (ZYPCommonFunc.hasValue(radio)) {
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {

                if (DS_ORD_LINE_DRCTN.OUTBOUND.equals(scrnMsg.C.no(i).dsOrdLineDrctnCd_C1.getValue())
                        && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.C.no(i).openFlg_C1.getValue())) {
                    // Outbound and Closed Line 
                    scrnMsg.C.no(i).xxTpCd_C1.setValue(radio.toString());
                }
            }
        }
    }
}

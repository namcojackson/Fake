/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2300;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2300.common.NWAL2300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWAL2300Scrn00_OnChange_Action extends S21CommonHandler {

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
        int idx = getButtonSelectNumber();
        NWAL2300_CBMsg dtlSelect = scrnMsg.C.no(idx);
        if (!"000".equals(dtlSelect.cpoDtlLineSubNum_C1.getValue())) {
            return;
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            NWAL2300_CBMsg dtl2 = scrnMsg.C.no(i);
            if (DS_ORD_LINE_DRCTN.INBOUND.equals(dtl2.dsOrdLineDrctnCd_C1.getValue()) || ZYPConstant.FLG_ON_Y.equals(dtl2.openFlg_C1.getValue())) {
                continue;
            }
            if (NWAL2300CommonLogic.isSetCompnent(scrnMsg, dtl2)) {
                ZYPEZDItemValueSetter.setValue(dtl2.xxTpCd_C1, dtlSelect.xxTpCd_C1);
            }
        }
    }
}

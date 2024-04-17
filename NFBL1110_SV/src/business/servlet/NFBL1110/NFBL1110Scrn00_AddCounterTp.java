/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL1110.NFBL1110CMsg;
import business.servlet.NFBL1110.common.NFBL1110CommonLogic;
import business.servlet.NFBL1110.constant.NFBL1110Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * AP Invoice Maintenance Batch Entry
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   Fujitsu         T.Murai         Create          S21_NA#12830
 *</pre>
 */
public class NFBL1110Scrn00_AddCounterTp extends S21CommonHandler implements NFBL1110Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        boolean chkFlg = false;
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFBL1110_ABMsg lineMsg = scrnMsg.A.no(i);

            // has check from Serial# Line
            if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.xxGrpFlg_A1.getValue()) && ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A1.getValue())) {
                chkFlg = true;
            }
        }
        if (!chkFlg) {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(i).xxGrpFlg_A1.getValue())) {
                    scrnMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NFBM0236E, new String[] {"Serial Line" });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A1);
                }
            }
        }
        if (scrnMsg.A.getValidCount() >= scrnMsg.A.length()) {
            scrnMsg.setMessageInfo(NFAM0072E, new String[] {String.valueOf(scrnMsg.A.length()) });
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID("NFBL1110");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        /** Initialize input control */
        NFBL1110CommonLogic.initControl(this, scrnMsg);

        /** Set focus */
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NFBL1110_ABMsg lineMsg = scrnMsg.A.no(i);

            // has check from Serial# Line
            if (!ZYPConstant.FLG_ON_Y.equals(lineMsg.xxGrpFlg_A1.getValue()) //
                    && ZYPConstant.CHKBOX_ON_Y.equals(lineMsg.xxChkBox_A1.getValue())) {
                
                for (int j = i + 1; j < scrnMsg.A.getValidCount(); j++) {
                    if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.A.no(j).xxGrpFlg_A1.getValue())) {
                        /** Set focus */
                        scrnMsg.setFocusItem(scrnMsg.A.no(j-1).cntrTpCd_A1);
                        break;
                    }
                }
            }
        }

    }
}

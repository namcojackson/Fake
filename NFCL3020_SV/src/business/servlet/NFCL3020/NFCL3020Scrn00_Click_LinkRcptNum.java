/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3020;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Entry
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2015   CSAI            K.Lee           Create          Initial
 * 02/17/2016   Fujitsu         T.Tanaka        Update          Def#3295
 *</pre>
 */
public class NFCL3020Scrn00_Click_LinkRcptNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
//        if(!scrnMsg.arBatRcptStsCd_H.getValue().equals(AR_BAT_RCPT_STS.CLOSED)) {
//            int selectIdx = getButtonSelectNumber();
//            scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).xxChkBox_B);
//            scrnMsg.B.no(selectIdx).xxChkBox_B.setErrorInfo(1, "NFCM0775E");
//            scrnMsg.putErrorScreen();
//        }

        int selectIdx = getButtonSelectNumber();
        if(!ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).rcptNum_B.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).xxChkBox_B);
            scrnMsg.B.no(selectIdx).xxChkBox_B.setErrorInfo(1, "NFCM0508E");
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3020BMsg scrnMsg = (NFCL3020BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[1];
        params[0] = scrnMsg.B.no(selectIdx).rcptNum_B;
        setArgForSubScreen(params);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_BAT_RCPT_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Batch Receipt Inquiry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/21/2015   CSAI            K.Lee           Create          Initial
 * 02/22/2016   Fujitsu         T.Tanaka        Update          Def#2631
 * 2018/03/22   Fujitsu         H.Ikeda         Update          QC#21737
 *</pre>
 */
public class NFCL3010Scrn00_Click_LinkRcptNum extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/03/22 H.Ikeda [QC#21737, DEL]
        //FCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        // END   2018/03/22 H.Ikeda [QC#21737, DEL]
//        int selectIdx = getButtonSelectNumber();
//        if(ZYPCommonFunc.hasValue(scrnMsg.B.no(selectIdx).arBatRcptStsCd_B) && !scrnMsg.B.no(selectIdx).arBatRcptStsCd_B.getValue().equals(AR_BAT_RCPT_STS.CLOSED)) {
////            scrnMsg.addCheckItem(scrnMsg.B.no(selectIdx).rcptNum_B);
////            scrnMsg.B.no(selectIdx).rcptNum_B.setErrorInfo(1, "NFCM0723E");
////            scrnMsg.putErrorScreen();
//            
//            scrnMsg.setMessageInfo("NFCM0775E");
//            throw new EZDFieldErrorException();
//        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3010BMsg scrnMsg = (NFCL3010BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();

        Object[] params = new Object[1];
        // START 2018/03/22 H.Ikeda [QC#21737, MOD]
        //params[0] = scrnMsg.B.no(selectIdx).rcptNum_B;
        params[0] = scrnMsg.A.no(selectIdx).rcptNum_A;
        // START 2018/03/22 H.Ikeda [QC#21737, MOD]
        setArgForSubScreen(params);
    }
}

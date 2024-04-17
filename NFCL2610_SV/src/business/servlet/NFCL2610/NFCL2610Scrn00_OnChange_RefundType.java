/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL2610;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/10   Hitachi         J.Kim           Create          N/A
 * 2016/04/19   Hitachi         T.Tsuchida      Update          QC#4758
 * 2018/02/06   Hitachi         T.Tsuchida      Update          QC#23990
 *</pre>
 */
public class NFCL2610Scrn00_OnChange_RefundType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2018/02/06 T.Tsuchida [QC#23990,DEL]
//        NFCL2610BMsg scrnMsg = (NFCL2610BMsg) bMsg;
//
//        scrnMsg.xxChkBox_1.clear();
//        scrnMsg.xxChkBox_2.clear();
//        String rfTpCd = scrnMsg.arRfTpCd_H.getValue();
//        if (AR_RF_TP.CFS.equals(rfTpCd)) {
//            scrnMsg.xxChkBox_1.setValue(ZYPConstant.CHKBOX_ON_Y);
//            scrnMsg.xxChkBox_1.setInputProtected(true);
//            scrnMsg.xxChkBox_2.setInputProtected(true);
//
//        }
//        if (AR_RF_TP.CUSTOMER.equals(rfTpCd)) {
//            scrnMsg.xxChkBox_1.setValue(ZYPConstant.CHKBOX_ON_Y);
//            scrnMsg.xxChkBox_2.setValue(ZYPConstant.CHKBOX_ON_Y);
//            scrnMsg.xxChkBox_1.setInputProtected(false);
//            scrnMsg.xxChkBox_2.setInputProtected(false);
//        }
     // END 2018/02/06 T.Tsuchida [QC#23990,DEL]
    }
}

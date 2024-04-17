/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3120;

import static business.servlet.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3120.NLBL3120CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/04   Fujitsu         S.Yoshida       Create          N/A
 * 2017/09/19   CITS            S.Katsuma       Update          Sol#032(QC#13117)
 *</pre>
 */
public class NLBL3120Scrn00_MoveWin_CoordWrkBench extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = new NLBL3120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3120BMsg scrnMsg = (NLBL3120BMsg) bMsg;
        NLBL3120CMsg bizMsg = (NLBL3120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            for (int k = 0; k < scrnMsg.A.getValidCount(); k++) {
                scrnMsg.addCheckItem(scrnMsg.A.no(k).xxChkBox_A2);
            }
            scrnMsg.putErrorScreen();
        }

        int i = 0;

        // Create Parameter
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, scrnMsg.trxHdrNum_P);
        // START 2017/09/19 S.Katsuma [Sol#032(QC#13117),DEL]
//        scrnMsg.P.no(i++).xxPopPrm.clear();
//        scrnMsg.P.no(i++).xxPopPrm.clear();
//        scrnMsg.P.no(i++).xxPopPrm.clear();
//        scrnMsg.P.no(i++).xxPopPrm.clear();
//        scrnMsg.P.no(i++).xxPopPrm.clear();
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "1");
        // END 2017/09/19 S.Katsuma [Sol#032(QC#13117),DEL]

        scrnMsg.P.setValidCount(i);
        Object[] params = new Object[i];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}

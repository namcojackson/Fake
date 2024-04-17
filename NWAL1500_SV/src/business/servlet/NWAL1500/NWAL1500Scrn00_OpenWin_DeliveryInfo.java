/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1500;

import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_0;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_1;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_2;
import static business.servlet.NWAL1500.constant.NWAL1500Constant.IDX_3;

import java.util.Arrays;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1500.NWAL1500CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class NWAL1500Scrn00_OpenWin_DeliveryInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;

        NWAL1500CMsg bizMsg = new NWAL1500CMsg();
        bizMsg.setBusinessID("NWAL1500");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1500BMsg scrnMsg = (NWAL1500BMsg) bMsg;
        NWAL1500CMsg bizMsg = (NWAL1500CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_LC);
        }
        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).xxChkBox_LL);
        }
        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_RC);
        }
        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.D.no(i).xxChkBox_RL);
        }
        scrnMsg.putErrorScreen();

        // set param
        Object[] params = new Object[IDX_3];
        params[IDX_0] = scrnMsg.cpoOrdNum;
        // QC#2478
        if (ZYPCommonFunc.hasValue(scrnMsg.dsOrdPosnNum_AW)) {
            params[IDX_1] = Arrays.asList(scrnMsg.dsOrdPosnNum_AW.getValue().split(","));
        }
        params[IDX_2] = scrnMsg.configCatgCd_AW;
        setArgForSubScreen(params);
        openMultiModeScreen();
    }
}

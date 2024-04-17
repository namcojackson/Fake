/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1140;

import static business.servlet.NPAL1140.constant.NPAL1140Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NPAL1140.NPAL1140CMsg;
import business.servlet.NPAL1140.common.NPAL1140CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * ACK Error Correction
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/30/2013   Hitachi         K.Kishimoto     Create          N/A
 * 06/11/2013   Hitachi         K.Kishimoto     Update          1233
 * 07/30/2013   Hitachi         A.Kohinata      Update          1388
 *</pre>
 */
public class NPAL1140Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        NPAL1140CommonLogic.addCheckItemHeader(scrnMsg);

        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        ZYPTableUtil.clear(scrnMsg.B);
        scrnMsg.openPoAckWrkFlg.clear();
        NPAL1140CommonLogic.underTabClear(scrnMsg);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NPAL1140CMsg bizMsg = new NPAL1140CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1140BMsg scrnMsg = (NPAL1140BMsg) bMsg;
        NPAL1140CMsg bizMsg = (NPAL1140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.xxTsDsp19Txt_HR.clear();
        for (int idx = 0; idx < scrnMsg.A.getValidCount(); idx++) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxTsDsp19Txt_AR, NPAL1140CommonLogic.formatDateTs(bizMsg.A.no(idx).ediRcvDateTs_A0.getValue()));
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxTsDsp19Txt_AU, NPAL1140CommonLogic.formatDateTs(bizMsg.A.no(idx).ordLastUpdTs_A0.getValue()));
        }

        NPAL1140CommonLogic.itemCtrlDataDisp(this, scrnMsg, bizMsg);
        NPAL1140CommonLogic.setUnderTabHeader(scrnMsg, bizMsg);
        NPAL1140CommonLogic.addCheckItemHeader(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}

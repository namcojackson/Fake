/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0730.NSAL0730CMsg;
import business.servlet.NSAL0730.common.NSAL0730CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.custPoNum);
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poFromDt);
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        scrnMsg.addCheckItem(scrnMsg.poDt);
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        NSAL0730CMsg bizMsg = new NSAL0730CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;
        NSAL0730CMsg bizMsg = (NSAL0730CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0730CommonLogic.addCheckItemForAll(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        } else {
            NSAL0730CommonLogic.initInactiveCommonButton(this, scrnMsg);
        }
    }
}

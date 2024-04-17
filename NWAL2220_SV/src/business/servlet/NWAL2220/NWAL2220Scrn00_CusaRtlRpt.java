/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.BIZ_ID;
import static business.servlet.NWAL2220.constant.NWAL2220Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2220.NWAL2220CMsg;
import business.servlet.NWAL2220.common.NWAL2220CommonLogic;
import business.servlet.NWAL2220.constant.NWAL2220Constant.FUNC;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/14   SRAA            K.Aratani       Create          QC#9971
 *</pre>
 */
public class NWAL2220Scrn00_CusaRtlRpt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        NWAL2220CMsg bizMsg = new NWAL2220CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.UPDATE.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = (NWAL2220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NWAL2220CommonLogic.initialControlScreen(this, scrnMsg);
        NWAL2220CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}

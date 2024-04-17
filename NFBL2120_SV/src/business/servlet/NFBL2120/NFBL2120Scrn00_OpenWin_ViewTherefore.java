/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2120;

import static business.servlet.NFBL2120.constant.NFBL2120Constant.BIZ_ID;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.EVENT_NM_VIEW_THEREFORE;
import static business.servlet.NFBL2120.constant.NFBL2120Constant.NFBM0261E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2120.NFBL2120CMsg;
import business.servlet.NFBL2120.common.NFBL2120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/14   Fujitsu         W.Honda         Create          N/A
 *</pre>
 */
public class NFBL2120Scrn00_OpenWin_ViewTherefore extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;

        scrnMsg.docMgtDocId.clear();
        scrnMsg.xxSrvUrlTxt.clear();

        if (scrnMsg.A.getValidCount() == 0) {
            scrnMsg.setMessageInfo(NFBM0261E);
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;

        NFBL2120CMsg bizMsg = new NFBL2120CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2120BMsg scrnMsg = (NFBL2120BMsg) bMsg;
        NFBL2120CMsg bizMsg  = (NFBL2120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFBL2120CommonLogic.controlScreenFields(this, scrnMsg);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, EVENT_NM_VIEW_THEREFORE);
    }
}

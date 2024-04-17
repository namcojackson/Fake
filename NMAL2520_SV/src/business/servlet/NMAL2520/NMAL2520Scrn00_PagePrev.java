/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2520;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.servlet.NMAL2520.common.NMAL2520CommonLogic;
import business.servlet.NMAL2520.constant.NMAL2520Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/14   Hitachi         J.Kim           Create          QC#18924
 *</pre>
 */
public class NMAL2520Scrn00_PagePrev extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;

        if (scrnMsg.C.getValidCount() == 0) {
            return null;
        } else {

            NMAL2520CMsg bizMsg = new NMAL2520CMsg();
            bizMsg.setBusinessID(NMAL2520Constant.BIZ_ID);
            bizMsg.setFunctionCode(NMAL2520Constant.FUNCTION_CD);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2520BMsg scrnMsg = (NMAL2520BMsg) bMsg;
        NMAL2520CMsg bizMsg = (NMAL2520CMsg) cMsg;

        if (bizMsg == null) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2520CommonLogic.convertTreeInfo(scrnMsg, bizMsg.T);
        NMAL2520CommonLogic.controlOrgLink(scrnMsg);
        NMAL2520CommonLogic.controlContract(scrnMsg);
        NMAL2520CommonLogic.controlRscLink(scrnMsg);
        NMAL2520CommonLogic.controlBusinessAreaFields(scrnMsg);
        NMAL2520CommonLogic.controlAttachmentsButton(this, scrnMsg);
    }
}

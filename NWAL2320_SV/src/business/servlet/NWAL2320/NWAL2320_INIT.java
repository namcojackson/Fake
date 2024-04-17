/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2320;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2320.NWAL2320CMsg;
import business.servlet.NWAL2320.common.NWAL2320CommonLogic;
import business.servlet.NWAL2320.constant.NWAL2320Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/25/2016   Fujitsu         M.Hara          Create          N/A
 *</pre>
 */
public class NWAL2320_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NWAL2320Constant.BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;

        NWAL2320CommonLogic.setInitRequestData(scrnMsg);

        NWAL2320CMsg bizMsg = new NWAL2320CMsg();
        bizMsg.setBusinessID(NWAL2320Constant.BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2320BMsg scrnMsg = (NWAL2320BMsg) bMsg;
        NWAL2320CMsg bizMsg  = (NWAL2320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setInputProtected(scrnMsg);
        NWAL2320CommonLogic.initCmnBtnProp(this);
//        scrnMsg.putErrorScreen();
    }

    @Override
    protected void setNameForMessage(EZDBMsg msg) {
    }

    private void setInputProtected(NWAL2320BMsg scrnMsg) {

        scrnMsg.A.setInputProtected(true);
        scrnMsg.B.setInputProtected(true);
        scrnMsg.C.setInputProtected(true);
        scrnMsg.D.setInputProtected(true);

        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith("E")) {
            this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD, false);
            this.setButtonEnabled(NWAL2320Constant.BTN_VALIDATE, false);
            this.setButtonEnabled(NWAL2320Constant.BTN_DOWNLOAD, false);
            this.setButtonEnabled(NWAL2320Constant.BTN_CANCEL_UPLOAD, false);
        }
        this.setButtonEnabled(NWAL2320Constant.BTN_UPLOAD_LINES, false);
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0170;

import static business.servlet.NMAL0170.constant.NMAL0170Constant.BIZ_ID;
import static business.servlet.NMAL0170.constant.NMAL0170Constant.EVENT_INIT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0170.NMAL0170CMsg;
import business.servlet.NMAL0170.common.NMAL0170CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL0170_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/02   Fujitsu         T.Arima         Create          N/A
 * 2016/02/23   CITS            S.Tanikawa      Update          QC#2669
 *</pre>
 */
public class NMAL0170_INIT extends S21INITCommonHandler {

    @Override
    /*
     * Check Input Event - Check Business App Granted.
     */
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    /*
     * Set Request Date Event - Create Pull down List.
     */
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;

        NMAL0170CMsg bizMsg = new NMAL0170CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    /*
     * Do Process Event - Set Focus Item - Item Number.
     */
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CMsg bizMsg = (NMAL0170CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL0170CommonLogic.initCmnBtnProp(this);
        NMAL0170CommonLogic.setCmnBtnProp(this, scrnMsg, EVENT_INIT);

        // Focus : Item Number
        scrnMsg.setFocusItem(scrnMsg.supdToMdseCd);
    }

    @Override
    /*
     * Set Name for Message Event.
     */
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL0170BMsg scrnMsg = (NMAL0170BMsg) bMsg;
        NMAL0170CommonLogic.setNameForMessage(scrnMsg);

        // ADD START 2016/02/23 QC#2669
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        // ADD END 2016/02/23 QC#2669
    }

}

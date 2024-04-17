/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1840;

import static business.servlet.NWAL1840.constant.NWAL1840Constant.BIZ_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.FUNC_CD_UPDT;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.SCREEN_ID;
import static business.servlet.NWAL1840.constant.NWAL1840Constant.TAB_CUSTOMER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1840.NWAL1840CMsg;
import business.servlet.NWAL1840.common.NWAL1840CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/29   Fujitsu         T.Murai         Create          N/A
 * 2017/11/21   Fujitsu         A.Kosai         Update          S21_NA#22388
 *</pre>
 */
public class NWAL1840Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        // 2017/11/21 S21_NA#22388 Add Start
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);
        // 2017/11/21 S21_NA#22388 Add End
        NWAL1840CommonLogic.checkItemAllFields(scrnMsg);
        NWAL1840CommonLogic.checkCustPoField(scrnMsg);
        NWAL1840CommonLogic.checkContactField(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;

        NWAL1840CMsg bizMsg = new NWAL1840CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1840BMsg scrnMsg = (NWAL1840BMsg) bMsg;
        NWAL1840CMsg bizMsg = (NWAL1840CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1840CommonLogic.setProtect(this, scrnMsg);
        NWAL1840CommonLogic.checkItemAllFields(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
            return;
        }
        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        scrnMsg.xxDplyTab.setValue(TAB_CUSTOMER);
        scrnMsg.setFocusItem(scrnMsg.schdAgmtNum);
        scrnMsg.clearAllGUIAttribute(SCREEN_ID);

        NWAL1840CommonLogic.setTblBackColorForContact(scrnMsg);
        NWAL1840CommonLogic.setTblBackColorForSummary(scrnMsg);
    }
}

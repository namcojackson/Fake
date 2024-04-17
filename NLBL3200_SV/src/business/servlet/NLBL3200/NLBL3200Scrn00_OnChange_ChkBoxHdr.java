/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import static business.servlet.NLBL3200.constant.NLBL3200Constant.BUSINESS_ID;
import static business.servlet.NLBL3200.constant.NLBL3200Constant.FUNC_SRCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLBL3200.NLBL3200CMsg;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.common.NLBL3200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NLBL3200Scrn00_OnChange_ChkBoxHdr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//        no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;

        int eventLine = getButtonSelectNumber();
        scrnMsg.xxNum_EV.setValue(eventLine);

        NLBL3200CMsg bizMsg = new NLBL3200CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        NLBL3200CMsg bizMsg = (NLBL3200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Screen Ctrl
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        NLBL3200CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);

        // Set Focus
        int eventLine = getButtonSelectNumber();
        scrnMsg.setFocusItem(scrnMsg.A.no(eventLine).xxExstFlg_A1);
    }
}

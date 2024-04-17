/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0380;

import static business.servlet.NSBL0380.common.NSBL0380CommonLogic.addCheckItem;
import static business.servlet.NSBL0380.common.NSBL0380CommonLogic.initialControlScreen;
import static business.servlet.NSBL0380.constant.NSBL0380Constant.BUSINESS_ID;
import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0380.NSBL0380CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * History Level Report
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/03   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0380_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSBL0380BMsg scrnMsg = (NSBL0380BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        int index = 0;
        ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd_HT, (EZDBStringItem) params[index++]);
        ZYPEZDItemValueSetter.setValue(scrnMsg.cratDt_H, (EZDBDateItem) params[index++]);

        NSBL0380CMsg bizMsg = new NSBL0380CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSBL0380BMsg scrnMsg = (NSBL0380BMsg) bMsg;
        NSBL0380CMsg bizMsg  = (NSBL0380CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if ("E".equals(bizMsg.getMessageKind()) || ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }
    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0380BMsg scrnMsg = (NSBL0380BMsg) bMsg;
        scrnMsg.orgCd_HT.setNameForMessage("Service Group");
        scrnMsg.orgDescTxt_H.setNameForMessage("Service Group Name");
        scrnMsg.xxScrItem7Txt_H.setNameForMessage("As of Date");
    }
}

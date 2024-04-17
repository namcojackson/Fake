/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import static business.servlet.NWCL0100.constant.NWCL0100Constant.BIZ_ID;
import static business.servlet.NWCL0100.constant.NWCL0100Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWCL0100.NWCL0100CMsg;
import business.servlet.NWCL0100.common.NWCL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWCL0100Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 *</pre>
 */
public class NWCL0100Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        NWCL0100CMsg bizMsg = new NWCL0100CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;
        NWCL0100CMsg bizMsg = (NWCL0100CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }

        NWCL0100CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");
        NWCL0100CommonLogic.controlItem(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.xxBillToAcctCdSrchTxt_H0);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        NWCL0100CommonLogic.addCheckItemBizLayerErr(scrnMsg);
        scrnMsg.putErrorScreen();
    }
}

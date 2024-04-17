/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6160;

import static business.servlet.NMAL6160.constant.NMAL6160Constant.BIZ_ID;
import static business.servlet.NMAL6160.constant.NMAL6160Constant.ZZM9000E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6160.NMAL6160CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Multi Candinate Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/15   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL6160Scrn00_Filter extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxDsMsgEntryTxt);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDsMsgEntryTxt)) {
            scrnMsg.xxDsMsgEntryTxt.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.xxDsMsgEntryTxt.getNameForMessage() });
        }

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;

        NMAL6160CMsg bizMsg = new NMAL6160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6160BMsg scrnMsg = (NMAL6160BMsg) bMsg;
        NMAL6160CMsg bizMsg = (NMAL6160CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
    }
}

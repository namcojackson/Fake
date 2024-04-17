/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2340;

import static business.servlet.NWAL2340.constant.NWAL2340Constant.BIZ_APP_ID;
import static business.servlet.NWAL2340.constant.NWAL2340Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2340.NWAL2340CMsg;
import business.servlet.NWAL2340.common.NWAL2340CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * SOM Address Mass Apply Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/22   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */
public class NWAL2340Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;

        NWAL2340CMsg bizMsg = new NWAL2340CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2340BMsg scrnMsg = (NWAL2340BMsg) bMsg;
        NWAL2340CMsg bizMsg = (NWAL2340CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2340CommonLogic.addCheckItem(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        Serializable arg = getArgForSubScreen();

        Object[] params = (Object[]) arg;
        List< ? > paramList = (List< ? >) params[0];
        int idx = 0;
        for (Object param : paramList) {
            int column = 0;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) ((Object[]) param)[column++], scrnMsg.A.no(idx).addrLbTxt_A);
            column++;
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) ((Object[]) param)[column++], scrnMsg.A.no(idx).shipToCustAcctCd_A);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) ((Object[]) param)[column++], scrnMsg.A.no(idx).shipToCustCd_A);
            idx++;
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import static business.servlet.NSAL0560.common.NSAL0560CommonLogic.initialControlScreen;
import static business.servlet.NSAL0560.constant.NSAL0560Constant.*;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0560.NSAL0560CMsg;

import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2020/01/15   Hitachi         Y.Takeno        Update          QC#55434
 *</pre>
 */
public class NSAL0560Scrn00_InsertRow extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2020/01/15 [QC#55434, ADD]
        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // END   2020/01/15 [QC#55434, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        NSAL0560CMsg bizMsg = new NSAL0560CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg  = (NSAL0560CMsg) cMsg;

        // START 2020/01/15 [QC#55434, ADD]
        boolean hasError = false;
        if (scrnMsg.A.getValidCount() == bizMsg.A.getValidCount()) {
            hasError = true;
        }
        // END   2020/01/15 [QC#55434, ADD]

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2020/01/15 [QC#55434, ADD]
        if (hasError) {
            NSAL0560CommonLogic.addCheckItem(scrnMsg);
            scrnMsg.putErrorScreen();
            if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
                throw new EZDFieldErrorException();
            }
        }
        // END   2020/01/15 [QC#55434, ADD]

        initialControlScreen(this, scrnMsg);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_A, new BigDecimal(scrnMsg.A.getValidCount() - 1));
    }
}

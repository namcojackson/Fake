/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3030.NFCL3030CMsg;
import business.servlet.NFCL3030.constant.NFCL3030Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/11/11   Hitachi         J.Kim           Update          QC#15756
 *</pre>
 */
public class NFCL3030Scrn00_Click_CashApplication extends S21CommonHandler implements NFCL3030Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // START 2016/11/11 J.Kim [QC#15756,ADD]
        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        NFCL3030CMsg bizMsg = new NFCL3030CMsg();
        bizMsg.setBusinessID("NFCL3030");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2016/11/11 J.Kim [QC#15756,ADD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {


        NFCL3030BMsg scrnMsg = (NFCL3030BMsg) bMsg;
        // START 2016/11/11 J.Kim [QC#15756,ADD]
        NFCL3030CMsg bizMsg  = (NFCL3030CMsg) cMsg;

        if (MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // END 2016/11/11 J.Kim [QC#15756,ADD]

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeInd, MODE_CASH_APPLICATION_ALL);

        Object[] params = new Object[2];
        params[0] = scrnMsg.xxModeInd;
        params[1] = scrnMsg.rcptNum_H;

        setArgForSubScreen(params);
    }
}

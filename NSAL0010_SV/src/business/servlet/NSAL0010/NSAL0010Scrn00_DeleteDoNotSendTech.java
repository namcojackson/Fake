/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.constant.NSAL0010Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 *</pre>
 */
public class NSAL0010Scrn00_DeleteDoNotSendTech extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_E.getValue())) {
            scrnMsg.setMessageInfo("NFCM0094E");
        }
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            throw new EZDFieldErrorException();
        }
        // END 2015/11/16 T.Tomita [QC#647, MOD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg  = (NSAL0010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }
}

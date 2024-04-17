/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2810;

import static business.servlet.NMAL2810.constant.NMAL2810Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2810.NMAL2810CMsg;
import business.servlet.NMAL2810.common.NMAL2810CommonLogic;
import business.servlet.NMAL2810.constant.NMAL2810Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2810Scrn00_AddressValidation
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/27   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2810Scrn00_AddressValidation extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;
        NMAL2810CMsg bizMsg = new NMAL2810CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2810BMsg scrnMsg = (NMAL2810BMsg) bMsg;
        NMAL2810CMsg bizMsg = (NMAL2810CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2810CommonLogic.setBgColorForAPI(scrnMsg);

        if (NMAL2810Constant.NMZC003001_RTRN_E.equals(scrnMsg.xxRsltCd.getValue())) {
            scrnMsg.setMessageInfo(NMAL2810Constant.NMAM8499E);
            throw new EZDFieldErrorException();
        }
    }
}

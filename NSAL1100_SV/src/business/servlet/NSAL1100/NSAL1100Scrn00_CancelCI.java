/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1100;

import static business.servlet.NSAL1100.constant.NSAL1100Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL1100.NSAL1100CMsg;
import business.servlet.NSAL1100.common.NSAL1100CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Approval List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/26   Hitachi         A.Kohinata      Create          N/A
 * 2017/10/19   Hitachi         K.Kojima        Update          QC#21260
 *</pre>
 */
public class NSAL1100Scrn00_CancelCI extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1100BMsg scrnMsg = (NSAL1100BMsg) bMsg;
        NSAL1100CMsg bizMsg = new NSAL1100CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1100BMsg scrnMsg = (NSAL1100BMsg) bMsg;
        NSAL1100CMsg bizMsg = (NSAL1100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        // START 2017/10/19 K.Kojima [QC#21260,DEL]
        // Object[] params = new Object[NSAL1090_PRM_LENGTH];
        // params[0] = scrnMsg.custIncdtId_H;
        // 
        // setArgForJump(params);
        // END 2017/10/19 K.Kojima [QC#21260,DEL]

        // START 2017/10/19 K.Kojima [QC#21260,ADD]
        NSAL1100CommonLogic.initialControlScreen(this, scrnMsg);
        // END 2017/10/19 K.Kojima [QC#21260,ADD]
    }
}

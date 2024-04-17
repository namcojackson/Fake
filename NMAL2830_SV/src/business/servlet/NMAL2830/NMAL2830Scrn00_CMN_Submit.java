/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2830;

import static business.servlet.NMAL2830.constant.NMAL2830Constant.BIZ_ID;
import static business.servlet.NMAL2830.constant.NMAL2830Constant.NZZM0002I;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2830.NMAL2830CMsg;
import business.servlet.NMAL2830.common.NMAL2830CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

// import
// com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * NMAL2830Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/09   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public class NMAL2830Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        NMAL2830CommonLogic.addCheckItemDetail(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;

        NMAL2830CMsg bizMsg = new NMAL2830CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2830BMsg scrnMsg = (NMAL2830BMsg) bMsg;
        NMAL2830CMsg bizMsg = (NMAL2830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2830CommonLogic.addCheckItemDetail(scrnMsg);
        scrnMsg.putErrorScreen();

        NMAL2830CommonLogic.controlScreenFields(getUserProfileService(), this, scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}

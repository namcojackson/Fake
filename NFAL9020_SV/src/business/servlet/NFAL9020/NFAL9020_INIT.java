/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9020;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL9020.NFAL9020CMsg;
import business.servlet.NFAL9020.common.NFAL9020CommonLogic;
import business.servlet.NFAL9020.constant.NFAL9020Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Class name: NFAL9020_INIT
 * <dd>The class explanation: Business processing for Component ID :
 * NFAL9020_INIT
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */
public class NFAL9020_INIT extends S21INITCommonHandler implements NFAL9020Constant {

    /** Singleton instance. */
    NFAL9020CommonLogic common = new NFAL9020CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        NFAL9020CMsg bizMsg = new NFAL9020CMsg();
        bizMsg.setBusinessID("NFAL9020");
        bizMsg.setFunctionCode("20");

        // get argments from main screen.
        Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    scrnMsg.ajeId.setValue(((EZDBStringItem) param[i]).getValue());
                }
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        NFAL9020CMsg bizMsg = (NFAL9020CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL9020CommonLogic.setButtonProperties(ctx, this);
        scrnMsg.setFocusItem(scrnMsg.ajeId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL9020BMsg scrnMsg = (NFAL9020BMsg) bMsg;
        NFAL9020CommonLogic.setNameForMessage(scrnMsg);
    }

}

/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL9010;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL9010.NFAL9010CMsg;
import business.servlet.NFAL9010.common.NFAL9010CommonLogic;
import business.servlet.NFAL9010.constant.NFAL9010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Class name: Screen Component ID : NFAL9010_INIT
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 * <pre>
 * COA Account Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/05   CSAI            K.Uramori       Update          CSA Modification
 * </pre>
 */
public class NFAL9010_INIT extends S21INITCommonHandler implements NFAL9010Constant {

    /**
     * Method name: checkInput
     * <dd>The method explanation: Check Input Data.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL9010BMsg scrnMsg = (NFAL9010BMsg)bMsg;
    }

    /**
     * Method name: setRequestData
     * <dd>The method explanation: Call business logic.
     * <dd>Remarks:
     * @param ctx Screen Application Context Class
     * @param bMsg Object which input value from screen is stored
     */
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CMsg bizMsg = new NFAL9010CMsg();

        bizMsg.setBusinessID("NFAL9010");
        bizMsg.setFunctionCode("20");

        //---- start mod 2016/04/04 not to set parameters
        // get argments from main screen.
        /*Object[] param = (Object[]) getArgForSubScreen();
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                if (i == 0) {
                    scrnMsg.coaAcctCd.setValue(((EZDBStringItem) param[i]).getValue());
                } else if (i == 1) {
                    scrnMsg.coaAcctNm.setValue(((EZDBStringItem) param[i]).getValue());
                }
            }
        }
        */
        //---- end 
        
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    /**
     * Method name: doProcess
     * <dd>The method explanation: Display and edit for next page
     * contents.
     * <dd>Remarks:
     * @param ctx Screen Application Context
     * @param bMsg Object which input value from screen is stored
     * @param cMsg Object which input value from business is stored
     */
    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CMsg bizMsg = (NFAL9010CMsg) cMsg;

        NFAL9010CommonLogic.setDisplayName(scrnMsg);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL9010CommonLogic.setButtonProperties(ctx, this);

        scrnMsg.setFocusItem(scrnMsg.coaAcctCd);

        return;
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL9010BMsg scrnMsg = (NFAL9010BMsg) bMsg;
        NFAL9010CommonLogic.setNameForMessage(scrnMsg);
    }
}

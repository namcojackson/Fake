/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0060.NFAL0060CMsg;
import business.servlet.NFAL0060.common.NFAL0060CommonLogic;
import business.servlet.NFAL0060.constant.NFAL0060Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Class name: Screen Component ID : NFAL0060Scrn00_CMN_Submit
 * <dd>Remarks:
 * @version 1.0
 * @author N.Sasaki
 */

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen Detail
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/24   CSAI            K.Uramori       Update          QC#5849 remove AJE_INTFC_TP from key
 *</pre>
 */
public class NFAL0060Scrn00_CMN_Submit extends S21CommonHandler implements NFAL0060Constant {

    /** Singleton instance. */
    private NFAL0060CommonLogic common = new NFAL0060CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = new NFAL0060CMsg();
        bizMsg.setBusinessID("NFAL0060");
        bizMsg.setFunctionCode("40");

        //---- start 2016/03/24 delete
        //common.clearHeaderFileds(scrnMsg);
        //---- end 2016/03/24
        scrnMsg.ajePtrnIndTpCd_T.clear();
        //scrnMsg.ajeIntfcTpCd_TS.clear();

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0060BMsg scrnMsg = (NFAL0060BMsg) bMsg;
        NFAL0060CMsg bizMsg = (NFAL0060CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NFAL0060CommonLogic.setInputProtectedTextFiled(scrnMsg);

        int meg = scrnMsg.getMessageType();
        // When no error message
        if (meg != ERROR) {
            scrnMsg.setMessageInfo("ZZM8100I");
        }
        //---- start add 2016/03/24 delete
        //common.clearAddRow(scrnMsg, this);
        //---- end 2016/03/24
        common.clearDeletedRow(scrnMsg);
        
        //---- start add 2016/03/24
        NFAL0060CommonLogic.controlInputFields(scrnMsg, this, false);
        //---- end 2016/03/24

        //scrnMsg.setFocusItem(scrnMsg.ajeIntfcTpCd_3S);
        scrnMsg.setFocusItem(scrnMsg.ajePtrnIndTpCd_3);
        
    }

}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0650;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NLCL0650.NLCL0650CMsg;
//import business.servlet.NLCL0650.constant.NLCL0650Constant;

import business.servlet.NLCL0650.common.NLCL0650CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0650 Tech-PI Count ReCount / Counting Completed
 * Function Name : Init
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/15/2016   CITS            Makoto Okigami  Create          N/A
 * 12/17/2019   Fujitsu         T.Ogura         Update          QC#54986
 *</pre>
 */
public class NLCL0650_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // no check function id

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0650BMsg scrnMsg = (NLCL0650BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            // START 2019/12/17 T.Ogura [QC#54986,MOD]
//            String param0 = (String) params[0];
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm, param0);
//            String param1 = (String) params[1];
//            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt, param1);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt, (String) params[0]);
            // END   2019/12/17 T.Ogura [QC#54986,MOD]
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0650BMsg scrnMsg = (NLCL0650BMsg) bMsg;

        NLCL0650CommonLogic.ctrlScrnItemDisp(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {

        NLCL0650CommonLogic.setNameForMessage((NLCL0650BMsg) scrnMsg);
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.blap.NFAL0200.constant.NFAL0200Constant.CoaSegmentTabs;
import business.servlet.NFAL0200.constant.NFAL0200Constant;


import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2017/02/07   CUSA            Hitachi         Update          QC#16904
 *</pre>
 */
public class NFAL0200Scrn00_OpenWin_Hrch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        //NFAL0200CMsg bizMsg = new NFAL0200CMsg();
        //bizMsg.setBusinessID("NFAL0200");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;    
        
        Object[] params = new Object[1];
        // START 2017/02/07 E.Kameishi [QC#16904,MOD]
        if (NFAL0200Constant.CH_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_CH");
        } else if (NFAL0200Constant.BR_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_BR");
        } else if (NFAL0200Constant.CC_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_CC");
        } else if (NFAL0200Constant.ACCT_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_ACCT");
        } else if (NFAL0200Constant.PROJ_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_PROJ");
        } else if (NFAL0200Constant.PROD_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_PROD");
        } else if (NFAL0200Constant.AFFL_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_AFFL");
        } else if (NFAL0200Constant.EXTN_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            scrnMsg.coaSegNm.setValue("COA_EXTN");
        }
        // END 2017/02/07 E.Kameishi [QC#16904,MOD]
        
        params[0] = scrnMsg.coaSegNm;
        
        setArgForSubScreen(params);
    }
}

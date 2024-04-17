/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0200.NFAL0200CMsg;
import business.servlet.NFAL0200.constant.NFAL0200Constant;

import business.servlet.NFAL0200.common.NFAL0200CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/08/26   Hitachi         J.Kim           Update          QC#13515
 *</pre>
 */
public class NFAL0200Scrn00_SearchBtn extends S21CommonHandler implements ZYPConstant{

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        
        scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        scrnMsg.addCheckItem(scrnMsg.coaCcCd);
        scrnMsg.addCheckItem(scrnMsg.coaAcctCd);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd);
        scrnMsg.addCheckItem(scrnMsg.coaChCd);
        scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
        scrnMsg.addCheckItem(scrnMsg.coaProjCd);
        scrnMsg.addCheckItem(scrnMsg.coaExtnCd);
        
        scrnMsg.putErrorScreen();
        
        // mandatory check
        if (!NFAL0200CommonLogic.inputCheckForSearch(scrnMsg)) {
            scrnMsg.coaBrCd.setErrorInfo(1, "NFCM0050E");
            scrnMsg.addCheckItem(scrnMsg.coaBrCd);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;

        NFAL0200CMsg bizMsg = new NFAL0200CMsg();
        bizMsg.setBusinessID("NFAL0200");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0200BMsg scrnMsg = (NFAL0200BMsg) bMsg;
        NFAL0200CMsg bizMsg  = (NFAL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        // check error
        if( "E".equals( bizMsg.getMessageKind() ) ) {
            return;
        }

        //---- start 2016/06/29 remove this codes since hierarchy buttom to be activated always
        /*
        if (!FLG_ON_Y.equals(scrnMsg.xxChkBox.getValue())) {
            // protect tabs with no search criteria
            NFAL0200CommonLogic.setTabProtected(scrnMsg);
        } else {
            // enable tabs
            NFAL0200CommonLogic.setTabUnprotected(scrnMsg);
        }
        */
        //---- end 2016/06/29
        
        if (NFAL0200Constant.BR_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            NFAL0200CommonLogic.submitBtnControl(this, true);
        } else if (NFAL0200Constant.ACCT_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            NFAL0200CommonLogic.submitBtnControl(this, true);
            // START 2016/08/26 J.Kim [QC#13515,ADD]
        } else if (NFAL0200Constant.CC_TAB.equals(scrnMsg.xxDplyTab.getValue())) {
            NFAL0200CommonLogic.submitBtnControl(this, true);
            // END 2016/08/26 J.Kim [QC#13515,ADD]
        } else {
            NFAL0200CommonLogic.submitBtnControl(this, false);
        }
        
        NFAL0200CommonLogic.setDownloadBtnControll(this, scrnMsg);
        
    }
}

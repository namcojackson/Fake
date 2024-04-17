/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3120;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3120.NFCL3120CMsg;
import business.servlet.NFCL3120.constant.NFCL3120Constant;

import business.servlet.NFCL3120.common.NFCL3120CommonLogic;

//import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         T.Tanaka        Create          N/A
 * 2016/07/27   Hitachi         K.Kojima        Update          QC#4870
 * 2016/08/02   Hitachi         K.Kojima        Update          QC#5521
 * 2016/12/27   Fujitsu         H.Ikeda         Update          QC#12865
 *</pre>
 */
public class NFCL3120_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        // Start 2016/12/27 H.Ikeda [QC#12865,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NFCL3120Constant.BUSINESS_ID);
        // End   2016/12/27 H.Ikeda [QC#12865,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;

        NFCL3120CMsg bizMsg = new NFCL3120CMsg();
        bizMsg.setBusinessID("NFCL3120");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        NFCL3120CMsg bizMsg  = (NFCL3120CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        
        NFCL3120CommonLogic.initialize(this, scrnMsg, getUserProfileService());

        scrnMsg.setFocusItem(scrnMsg.dsAcctNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        
        NFCL3120BMsg scrnMsg = (NFCL3120BMsg) bMsg;
        
        scrnMsg.dsAcctNum_H1.setNameForMessage("Customer Account");
        
        scrnMsg.xxRadioBtn.setValue(1);
        
        scrnMsg.setFocusItem(scrnMsg.xxChkBox_H1);
        
        // START 2016/07/27 K.Kojima [QC#4870,ADD]
        scrnMsg.dsAcctNm_H1.setNameForMessage("Customer Account Name");
        scrnMsg.dsBankAcctNm_H1.setNameForMessage("Bank Name");
        scrnMsg.dsBankBrNm_H1.setNameForMessage("Branch Name");
        // END 2016/07/27 K.Kojima [QC#4870,ADD]

        // START 2016/08/02 K.Kojima [QC#5521,ADD]
        scrnMsg.srchOptPk_S.setNameForMessage("Saved Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
        // END 2016/08/02 K.Kojima [QC#5521,ADD]
    }
}

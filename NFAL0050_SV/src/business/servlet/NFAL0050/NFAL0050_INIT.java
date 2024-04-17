/*
 * <Pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NFAL0050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0050.NFAL0050CMsg;
import business.servlet.NFAL0050.common.NFAL0050CommonLogic;
import business.servlet.NFAL0050.constant.NFAL0050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2015   Fujitsu         N.Sasaki        Create          N/A
 * 2016/12/20   Fujitsu         H.Ikeda         Update          QC#12865
 * 
 *</pre>
 */
public class NFAL0050_INIT extends S21INITCommonHandler implements NFAL0050Constant {

    /** Singleton instance. */
    private NFAL0050CommonLogic common = new NFAL0050CommonLogic();

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = new NFAL0050CMsg();

        bizMsg.setBusinessID("NFAL0050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

        // return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CMsg bizMsg = (NFAL0050CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // Start 2016/12/20 H.Ikeda [QC#12865,MOD]
        NFAL0050CommonLogic.initCommonButton(scrnMsg, this);
        // END   2016/12/20 H.Ikeda [QC#12865,MOD]
        NFAL0050CommonLogic.protectParmanentFields(scrnMsg);
        common.resetAllFileds(scrnMsg, this);
        common.setFocus(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0050BMsg scrnMsg = (NFAL0050BMsg) bMsg;
        NFAL0050CommonLogic.setNameForMessage(scrnMsg);
    }

}

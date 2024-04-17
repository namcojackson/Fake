/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3130;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3130.NFCL3130CMsg;
import business.servlet.NFCL3130.common.NFCL3130CommonLogic;
import business.servlet.NFCL3130.constant.NFCL3130Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            CUSA            Y.Miyauchi      New
 * 2016/07/11   Hitachi         K.Kojima        Update          QC#11576
 * 2016/07/12   Hitachi         K.Kojima        Update          QC#11576
 *</pre>
 */
public class NFCL3130_INIT extends S21INITCommonHandler implements NFCL3130Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/07/11 K.Kojima [QC#11576,ADD]
        checkBusinessAppGranted(getContextUserInfo().getUserId(), "NFCL3130");
        // END 2016/07/11 K.Kojima [QC#11576,ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;

        NFCL3130CMsg bizMsg = new NFCL3130CMsg();
        bizMsg.setBusinessID("NFCL3130");
        bizMsg.setFunctionCode("20");

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) bMsg;
        NFCL3130CMsg bizMsg  = (NFCL3130CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFCL3130CommonLogic.initialize(this, scrnMsg);
    }

    // START 2016/07/11 K.Kojima [QC#11576,ADD]
    @Override
    protected void setNameForMessage(EZDBMsg arg0) {
        NFCL3130BMsg scrnMsg = (NFCL3130BMsg) arg0;
        scrnMsg.arRcptSrcNm.setNameForMessage("Name");
        scrnMsg.arRcptSrcDescTxt.setNameForMessage("Discription");
        // START 2016/07/12 K.Kojima [QC#11576,ADD]
        scrnMsg.arRcptSrcTpCd_S1.setNameForMessage("Type");
        // END 2016/07/12 K.Kojima [QC#11576,ADD]
    }
    // END 2016/07/11 K.Kojima [QC#11576,ADD]
}

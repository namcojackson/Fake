/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0670;

import static business.servlet.NSAL0670.common.NSAL0670CommonLogic.initialControlScreen;
import static business.servlet.NSAL0670.constant.NSAL0670Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0670.NSAL0670CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Cancel Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         K.Kasai         Create          N/A
 * 2015/11/19   Hitachi         T.Tsuchida      Update          QC#883
 *</pre>
 */
public class NSAL0670_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0670BMsg scrnMsg = (NSAL0670BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                    EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.P, null);
                }
            }
        }

        NSAL0670CMsg bizMsg = new NSAL0670CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0670BMsg scrnMsg = (NSAL0670BMsg) bMsg;
        NSAL0670CMsg bizMsg = (NSAL0670CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0670BMsg scrnMsg = (NSAL0670BMsg) bMsg;
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H1.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).serNum_A1.setNameForMessage("Serial#");
            scrnMsg.A.no(i).dsContrCtrlStsNm_AH.setNameForMessage("Status");
            scrnMsg.A.no(i).t_MdlNm_A1.setNameForMessage("Model");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).xxGenlFldAreaTxt_A1.setNameForMessage("Return Message");
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0630;

import static business.servlet.NSAL0630.common.NSAL0630CommonLogic.initialControlScreen;
import static business.servlet.NSAL0630.constant.NSAL0630Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0630.NSAL0630CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Contract On Hold
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   Hitachi         T.Tsuchida      Create          N/A
 * 2015/11/19   Hitachi         T.Tsuchida      Update          QC#883
 *</pre>
 */
public class NSAL0630_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0630BMsg scrnMsg = (NSAL0630BMsg) bMsg;

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

        NSAL0630CMsg bizMsg = new NSAL0630CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0630BMsg scrnMsg = (NSAL0630BMsg) bMsg;
        NSAL0630CMsg bizMsg  = (NSAL0630CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        initialControlScreen(this, scrnMsg);

    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0630BMsg scrnMsg = (NSAL0630BMsg) bMsg;
        scrnMsg.svcMemoRsnCd_H.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrNumTxt_A.setNameForMessage("Contract#");
            scrnMsg.A.no(i).dsAcctNm_A.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).billToCustLocAddr_A.setNameForMessage("Bill To");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A.setNameForMessage("End Date");
            scrnMsg.A.no(i).dsContrStsDescTxt_A.setNameForMessage("Status");
            scrnMsg.A.no(i).dsMsgTxt_A.setNameForMessage("Return Message");
        }
    }
}

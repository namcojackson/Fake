/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0690;

import static business.servlet.NSAL0690.common.NSAL0690CommonLogic.initialControlScreen;
import static business.servlet.NSAL0690.constant.NSAL0690Constant.BUSINESS_ID;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0690.NSAL0690CMsg;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Renew Contract or Machine on Contract
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         K.Kasai         Create          N/A
 * 2015/11/19   Hitachi         T.Tsuchida      Update          QC#883
 *</pre>
 */
public class NSAL0690_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;

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

        NSAL0690CMsg bizMsg = new NSAL0690CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        NSAL0690CMsg bizMsg = (NSAL0690CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0690BMsg scrnMsg = (NSAL0690BMsg) bMsg;
        scrnMsg.xxNum_H1.setNameForMessage("Duration");
        scrnMsg.bllgCycleUomCd_H3.setNameForMessage("Period");
        scrnMsg.svcMemoRsnCd_H3.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H1.setNameForMessage("Notes");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxScrItem34Txt_A1.setNameForMessage("Contract#");
            scrnMsg.A.no(i).contrVrsnEffFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrVrsnEffThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Customer Name");
            scrnMsg.A.no(i).xxNum_A1.setNameForMessage("Duration");
            scrnMsg.A.no(i).bllgCycleUomCd_A3.setNameForMessage("Period");
            scrnMsg.A.no(i).basePrcDealAmt_A1.setNameForMessage("Amount");
            scrnMsg.A.no(i).newBaseDealAmt_A1.setNameForMessage("New Amount");
        }
        for (int j = 0; j < scrnMsg.B.length(); j++) {
            scrnMsg.B.no(j).mdseCd_B1.setNameForMessage("Product#");
            scrnMsg.B.no(j).serNum_B1.setNameForMessage("Serial#");
            scrnMsg.B.no(j).t_MdlNm_B1.setNameForMessage("Model");
            scrnMsg.B.no(j).xxScrItem8Txt_B1.setNameForMessage("Type");
            scrnMsg.B.no(j).dsContrCtrlStsNm_B1.setNameForMessage("Status");
            scrnMsg.B.no(j).contrEffFromDt_B1.setNameForMessage("Start Date");
            scrnMsg.B.no(j).xxDiscRatio_B1.setNameForMessage("%");
            scrnMsg.B.no(j).contrEffThruDt_B1.setNameForMessage("End Date");
            scrnMsg.B.no(j).xxThruDt_B1.setNameForMessage("New Period End");
            scrnMsg.B.no(j).basePrcDealAmt_B1.setNameForMessage("Amount");
            scrnMsg.B.no(j).newBaseDealAmt_B1.setNameForMessage("New Amount");
            scrnMsg.B.no(j).xxGenlFldAreaTxt_B1.setNameForMessage("Return Message");
        }
    }
}

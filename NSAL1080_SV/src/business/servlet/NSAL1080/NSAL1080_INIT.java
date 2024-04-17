/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1080;

import static business.servlet.NSAL1080.constant.NSAL1080Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL1080.NSAL1080CMsg;
import business.servlet.NSAL1080.common.NSAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Credit Rebill Main Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         A.Kohinata      Create          N/A
 * 2017/09/21   CITS            M.Naito         Update          CSA QC#18758
 *</pre>
 */
public class NSAL1080_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        NSAL1080CMsg bizMsg = new NSAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_SEARCH);

        // add start 2017/09/21 CSA Defect#18758
        String custIncdtId = null;
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
                    custIncdtId = ((EZDBStringItem) prm[0]).getValue();
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.custIncdtId, custIncdtId);
        // add end 2017/09/21 CSA Defect#18758

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        NSAL1080CMsg bizMsg = (NSAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL1080CommonLogic.initialControlScreen(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.custIncdtId);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL1080BMsg scrnMsg = (NSAL1080BMsg) bMsg;
        scrnMsg.custIncdtId.setNameForMessage("Customer Incident#");
        scrnMsg.svcInvNum.setNameForMessage("Invoice#");
        scrnMsg.dsContrNum.setNameForMessage("Contract#");
        scrnMsg.conslBillPk.setNameForMessage("Consolidated Bill#");
        scrnMsg.serNum.setNameForMessage("Serial#");
        scrnMsg.bllgPerFromDt.setNameForMessage("Billed From Date");
        scrnMsg.bllgPerToDt.setNameForMessage("Billed Thru Date");
    }
}

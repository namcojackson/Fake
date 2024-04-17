/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL0750;

import static business.servlet.NFCL0750.constant.NFCL0750Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL0750.NFCL0750CMsg;
import business.servlet.NFCL0750.common.NFCL0750CommonLogic;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Auto Write-Off Result Inquiry Screen
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/09   Hitachi         T.Tsuchida      Create          N/A
 * 2018/03/01   Hitachi         J.Kim           Update          QC#21143
 *</pre>
 */
public class NFCL0750_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg = new NFCL0750CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        NFCL0750CMsg bizMsg = (NFCL0750CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NFCL0750CommonLogic.initialControlScreen(this, scrnMsg);
        NFCL0750CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NFCL0750BMsg scrnMsg = (NFCL0750BMsg) bMsg;
        scrnMsg.wrtOffRqstUsrId_H.setNameForMessage("User Id");
        scrnMsg.wrtOffRqstGrpCd_H.setNameForMessage("Write Off Request#");
        scrnMsg.arAdjRsnDescTxt_SV.setNameForMessage("Reason");
        scrnMsg.arAdjTpDescTxt_SV.setNameForMessage("Activity");
        scrnMsg.dsAcctNm_H.setNameForMessage("Customer Name");
        scrnMsg.dsAcctNum_H.setNameForMessage("Customer Number");
        scrnMsg.arWrtOffRqstTpDescTxt_SV.setNameForMessage("Request Type");
        scrnMsg.xxFromDt_H.setNameForMessage("Request Create Date (From)");
        scrnMsg.xxThruDt_H.setNameForMessage("Request Create Date (To)");
        scrnMsg.xxChkBox_R.setNameForMessage("Option - Generate Report Only");
        scrnMsg.xxChkBox_A.setNameForMessage("Option - Create Adjustment");
        // START 2018/02/28 J.Kim [QC#21143,MOD]
        // scrnMsg.procStsDescTxt_SV.setNameForMessage("Process Status");
        scrnMsg.arDsWfStsDescTxt_SV.setNameForMessage("Workflow Status");
        // END 2018/02/28 J.Kim [QC#21143,MOD]
        // START 2018/04/20 J.Kim [QC#24885,ADD]
        scrnMsg.cltDispTpCd_H.setNameForMessage("Display");
        // END 2018/04/20 J.Kim [QC#24885,ADD]
    }

}

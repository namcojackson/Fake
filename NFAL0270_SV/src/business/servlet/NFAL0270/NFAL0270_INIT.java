/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFAL0270;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFAL0270.NFAL0270CMsg;
import static business.servlet.NFAL0270.constant.NFAL0270Constant.*;
import business.servlet.NFAL0270.common.NFAL0270CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/17   Hitachi         G.Quan          Create          QC#61387
 *</pre>
 */
public class NFAL0270_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPL_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;

        NFAL0270CMsg bizMsg = new NFAL0270CMsg();
        bizMsg.setBusinessID(BUSINESS_APPL_ID);
        bizMsg.setFunctionCode(FUNC_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;
        NFAL0270CMsg bizMsg  = (NFAL0270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFAL0270CommonLogic.initialControlScreen(this, scrnMsg, bizMsg.getUserID());

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NFAL0270BMsg scrnMsg = (NFAL0270BMsg) bMsg;

        scrnMsg.mdlGrpId_H.setNameForMessage("Model Group ID");
        scrnMsg.mdlGrpNm_H.setNameForMessage("Model Group Name");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
             scrnMsg.A.no(i).mdlGrpId_A.setNameForMessage("Model Group ID");
             scrnMsg.A.no(i).mdlGrpNm_A.setNameForMessage("Model Group Name");
             scrnMsg.A.no(i).svcInvChrgTpCd_A.setNameForMessage("Charge Type");
             scrnMsg.A.no(i).svcAllocTpCd_A.setNameForMessage("Allocation Type");
             scrnMsg.A.no(i).equipAllocPct_A.setNameForMessage("Equipment Percentage");
             scrnMsg.A.no(i).svcAllocPct_A.setNameForMessage("Service Percentage");
             scrnMsg.A.no(i).splyAllocPct_A.setNameForMessage("Supply Percentage");
        }
    }
}

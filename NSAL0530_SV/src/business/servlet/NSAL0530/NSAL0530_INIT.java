/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0530;

import static business.servlet.NSAL0530.constant.NSAL0530Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0530.NSAL0530CMsg;
import business.servlet.NSAL0530.common.NSAL0530CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12   Hitachi         T.Tomita        Create          N/A
 * 2016/05/10   Hitachi         T.Tomita        Update          QC#7976
 * 2016/09/21   Hitachi         Y.Zhang         Update          QC#12582
 *</pre>
 */
public class NSAL0530_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CMsg bizMsg = NSAL0530CommonLogic.createCMsgForSearch();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        NSAL0530CMsg bizMsg  = (NSAL0530CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0530CommonLogic.screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.serNum_H);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        // START 2016/05/10 T.Tomita [QC#7976, MOD]
        NSAL0530BMsg scrnMsg = (NSAL0530BMsg) bMsg;
        scrnMsg.serNum_H.setNameForMessage("Serial#");
        scrnMsg.xxScrItem29Txt_HS.setNameForMessage("Solution#");
        scrnMsg.svcSlnNm_H.setNameForMessage("Solution Name");
        scrnMsg.xxScrItem29Txt_HC.setNameForMessage("Configuration#");
        scrnMsg.svcMachMstrPk_H.setNameForMessage("Install Base ID");
        scrnMsg.xxLocNm_H1.setNameForMessage("Account Owner");
        scrnMsg.xxLocNm_H2.setNameForMessage("Account Bill To");
        scrnMsg.xxLocNm_H3.setNameForMessage("Account Ship To");
        scrnMsg.istlDt_H.setNameForMessage("Install Date");
        scrnMsg.mdlNm_H.setNameForMessage("Svc Model Name");
        // START 2016/09/21 Y.Zhang [QC#12582, Modify]
        scrnMsg.mdseCd_H.setNameForMessage("Item Code");
        // END 2016/09/21 Y.Zhang [QC#12582, Modify]
        scrnMsg.coaMdseTpCd_HS.setNameForMessage("Mdse Type");
        scrnMsg.cpoOrdNum_H.setNameForMessage("Sales Order#");
        scrnMsg.custMachCtrlNum_H.setNameForMessage("External Ref#");
        scrnMsg.custIssPoNum_H.setNameForMessage("PO#");
        scrnMsg.dsContrNum_H.setNameForMessage("Contract#");
        // END 2016/05/10 T.Tomita [QC#7976, MOD]
    }
}

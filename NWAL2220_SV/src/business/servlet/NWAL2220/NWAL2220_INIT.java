/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2220;

import static business.servlet.NWAL2220.constant.NWAL2220Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2220.NWAL2220CMsg;
import business.servlet.NWAL2220.common.NWAL2220CommonLogic;
import business.servlet.NWAL2220.constant.NWAL2220Constant.FUNC;

import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Import  Search & Result
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/16   Hitachi         T.Tsuchida      Create          N/A
 * 2017/09/29   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NWAL2220_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = new NWAL2220CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.SEARCH.getFunc());

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;
        NWAL2220CMsg bizMsg = (NWAL2220CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NWAL2220CommonLogic.initialControlScreen(this, scrnMsg);
        NWAL2220CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL2220BMsg scrnMsg = (NWAL2220BMsg) bMsg;

        scrnMsg.cpoSrcTpCd_SV.setNameForMessage("Source Name");
        scrnMsg.ordSrcRefNum.setNameForMessage("Source Reference Num");
        scrnMsg.imptStsCd_SV.setNameForMessage("Import Status");
        scrnMsg.tocNm.setNameForMessage("Sales Rep Name");
        scrnMsg.ordSrcImptDt_FM.setNameForMessage("Import Date From");
        scrnMsg.ordSrcImptDt_TO.setNameForMessage("Import Date To");
        scrnMsg.coaExtnCd_SV.setNameForMessage("Sales Business Unit");
        scrnMsg.coaBrDescTxt.setNameForMessage("Sales Branch");
        scrnMsg.cpoOrdNum.setNameForMessage("CPO Order Num");
        scrnMsg.sellToCustCd.setNameForMessage("Sold To : Customer Num");
        scrnMsg.dsAcctNm_OT.setNameForMessage("Sold To : Customer Name");
        // Mod Start 2017/09/29 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To : Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To : Code");
        // Mod End 2017/09/29 QC#19922
        scrnMsg.billToCustAcctCd.setNameForMessage("Bill To : Customer Num");
        scrnMsg.dsAcctNm_BT.setNameForMessage("Bill To : Customer Name");
        // Mod Start 2017/09/29 QC#19922
        //scrnMsg.billToCustCd.setNameForMessage("Bill To : Location");
        scrnMsg.billToCustCd.setNameForMessage("Bill To : Code");
        // Mod End 2017/09/29 QC#19922
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To : Customer Num");
        scrnMsg.dsAcctNm_HT.setNameForMessage("Ship To : Customer Name");
        // Mod Start 2017/09/29 QC#19922
        //scrnMsg.shipToCustCd.setNameForMessage("Ship To : Location");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To : Code");
        // Mod End 2017/09/29 QC#19922
        scrnMsg.xxPageShowCurNum.setNameForMessage("Showing Current Number");
    }

}

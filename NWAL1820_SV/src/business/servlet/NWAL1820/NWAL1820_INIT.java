/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1820;

import static business.servlet.NWAL1820.constant.NWAL1820Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1820.NWAL1820CMsg;
import business.servlet.NWAL1820.common.NWAL1820CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1820_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/14   Fujitsu         Y.Taoka         Create          N/A
 * 2016/08/10   Hitachi         Y.Takeno        Update          QC#12658
 * 2017/09/21   Fujitsu         H.Sugawara      Update          QC#19922
 *</pre>
 */
public class NWAL1820_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;
        NWAL1820CMsg bizMsg = new NWAL1820CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;
        NWAL1820CMsg bizMsg = (NWAL1820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1820CommonLogic.initCmnBtnProp(this);
        NWAL1820CommonLogic.setControlField(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1820BMsg scrnMsg = (NWAL1820BMsg) bMsg;

        //Save Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Saved Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
        // Header
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Customer Number");
        scrnMsg.shipToCustAcctNm.setNameForMessage("Customer Name");
        // Mod Start 2017/09/21 QC#19922
        //scrnMsg.addShipToCustCd.setNameForMessage("Customer Location");
        scrnMsg.addShipToCustCd.setNameForMessage("Customer Ship To Code");
        // Mod End 2017/09/21 QC#19922
        scrnMsg.aquNum.setNameForMessage("Acquisition Number ");
        scrnMsg.serNum.setNameForMessage("Serial Number  ");
        scrnMsg.coaBrDescTxt.setNameForMessage("Sales Branch");
        // 2016/05/30 S21_NA#7861 Mod Start
//        scrnMsg.slsRepPsnCd.setNameForMessage("Employee ID");
// 2016/08/10 QC#12658 Mod Start
//        scrnMsg.psnNum.setNameForMessage("Resource#");
        scrnMsg.psnNum.setNameForMessage("Sales Rep");
// 2016/08/10 QC#12658 Mod End
        // 2016/05/30 S21_NA#7861 Mod End
        scrnMsg.dsOrdCatgCd.setNameForMessage("Order Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Order Reason");
        scrnMsg.dsOrdRsnCd.setNameForMessage("Sub Reason");
        scrnMsg.actlShipDt_FR.setNameForMessage("Shipped Date From");
        scrnMsg.actlShipDt_TO.setNameForMessage("Shipped Date Through");
        scrnMsg.xxTrxDt_FR.setNameForMessage("Loan Due Date From");
        scrnMsg.xxTrxDt_TO.setNameForMessage("Loan Due Date Through");

    }
}

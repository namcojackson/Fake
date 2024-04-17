/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1850;

import static business.servlet.NWAL1850.constant.NWAL1850Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1850.NWAL1850CMsg;
import business.servlet.NWAL1850.common.NWAL1850CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1850_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         Y.Taoka         Create          N/A
 * 2016/05/09   Fujitsu         T.Murai         Update          QC#7670
 * 2017/09/21   Fujitsu         H.Sugawara      Update          QC#19922
 * 2022/06/02   Hitachi         K.Kitachi       Update          QC#60037
 *</pre>
 */
public class NWAL1850_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        NWAL1850CMsg bizMsg = new NWAL1850CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        NWAL1850CMsg bizMsg = (NWAL1850CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL1850CommonLogic.initCmnBtnProp(this);
        NWAL1850CommonLogic.setFeildControl(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1850BMsg scrnMsg = (NWAL1850BMsg) bMsg;
        //Save Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Saved Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");
        // Header
        scrnMsg.schdAgmtNum.setNameForMessage("Schedule Number");
        // Add Start QC#7670
        // START 2022/06/02 K.Kitachi [QC#60037, MOD]
//        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.cpoOrdNum.setNameForMessage("Source Ref #");
        // END 2022/06/02 K.Kitachi [QC#60037, MOD]
        // Add End QC#7670
        scrnMsg.sellToCustCd.setNameForMessage("Sold To Customer Number");
        // Mod Start 2017/09/21 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Customer Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Code");
        // Mod End 2017/09/21 QC#19922
        scrnMsg.dsAcctNm_SO.setNameForMessage("Sold To Customer Name");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To Customer Number");
        // Mod Start 2017/09/21 QC#19922
        //scrnMsg.shipToCustCd.setNameForMessage("Ship To Customer Location");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Code");
        // Mod End 2017/09/21 QC#19922
        scrnMsg.dsAcctNm_SI.setNameForMessage("Ship To Customer Name");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.dsOrdCatgCd.setNameForMessage("Category");
        scrnMsg.dsOrdTpCd.setNameForMessage("Reason");
        scrnMsg.schdAgmtCratDt_FR.setNameForMessage("Schedule Date(From)");
        scrnMsg.schdAgmtCratDt_TO.setNameForMessage("Schedule Date(Through)");
        scrnMsg.schdAgmtStsCd.setNameForMessage("Schedule Status");
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO Number");
        scrnMsg.adminPsnCd.setNameForMessage("Created By");
        // START 2022/06/02 K.Kitachi [QC#60037, ADD]
        scrnMsg.serNum.setNameForMessage("Serial #");
        scrnMsg.xxScrItem30Txt.setNameForMessage("Configuration ID");
        scrnMsg.dsContrNum.setNameForMessage("Contract #");
        scrnMsg.schdAgmtDelyHldCd.setNameForMessage("Delivery Hold");
        // END 2022/06/02 K.Kitachi [QC#60037, ADD]

        scrnMsg.xxPageShowCurNum.setNameForMessage("Showing Page");

    }
}

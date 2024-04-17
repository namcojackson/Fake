/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1780;

import static business.servlet.NWAL1780.constant.NWAL1780Constant.BIZ_ID;
import static business.servlet.NWAL1780.constant.NWAL1780Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1780.NWAL1780CMsg;
import business.servlet.NWAL1780.common.NWAL1780CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * Supply Quote Search Init
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/02   Fujitsu         T.Murai         Create          N/A
 * 2017/09/15   Fujitsu         H.Sugawara      Update          QC#19922
 * 2018/03/02   Fujitsu         K.Ishizuka      Update          QC#22956
 *</pre>
 */
public class NWAL1780_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        NWAL1780CMsg bizMsg = new NWAL1780CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;
        NWAL1780CMsg bizMsg = (NWAL1780CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.splyQuoteNum);

        NWAL1780CommonLogic.initCommonButton(this);
        NWAL1780CommonLogic.initProtect(scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1780BMsg scrnMsg = (NWAL1780BMsg) bMsg;

        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        // Header
        scrnMsg.splyQuoteNum.setNameForMessage("Quote Number");
        scrnMsg.sellToCustCd.setNameForMessage("Sold To Customer Number");
        // Mod Start 2017/09/15 QC#19922
        //scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Customer Location");
        scrnMsg.soldToCustLocCd.setNameForMessage("Sold To Code");
        // Mod End 2017/09/15 QC#19922
        scrnMsg.dsAcctNm_SO.setNameForMessage("Sold To Customer Name");
        scrnMsg.shipToCustAcctCd.setNameForMessage("Ship To Customer Number");
        // Mod Start 2017/09/15 QC#19922
        //scrnMsg.shipToCustCd.setNameForMessage("Ship To Customer Location");
        scrnMsg.shipToCustCd.setNameForMessage("Ship To Code");
        // Mod End 2017/09/15 QC#19922
        scrnMsg.dsAcctNm_SI.setNameForMessage("Ship To Customer Name");
        scrnMsg.mdseCd.setNameForMessage("Item Number");
        scrnMsg.dsOrdCatgDescTxt.setNameForMessage("Category (*)");
        scrnMsg.dsOrdTpCd.setNameForMessage("Reason Code");
        scrnMsg.splyQuoteDt_FR.setNameForMessage("Quote Date(From)");
        scrnMsg.splyQuoteDt_TO.setNameForMessage("Quote Date(To)");
        scrnMsg.splyQuoteStsCd.setNameForMessage("Quote Status");
        scrnMsg.custIssPoNum.setNameForMessage("Customer PO");
        scrnMsg.adminPsnCd.setNameForMessage("Created By");
        scrnMsg.xxPageShowCurNum.setNameForMessage("Showing Current Number");
        scrnMsg.splyQuoteNm.setNameForMessage("Quote Name"); // 2018/03/02 S21_NA#22956 Add

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NWAL1780_ABMsg dtlMsg = scrnMsg.A.no(i);
            dtlMsg.splyQuoteNum_A.setNameForMessage("Quote Number");
            dtlMsg.sellToCustCd_A.setNameForMessage("Sold To Customer Number");
            // Mod Start 2017/09/15 QC#19922
            //dtlMsg.soldToCustLocCd_A.setNameForMessage("Sold To Customer Location");
            dtlMsg.soldToCustLocCd_A.setNameForMessage("Sold To Code");
            // Mod End 2017/09/15 QC#19922
            dtlMsg.dsAcctNm_AO.setNameForMessage("Sold To Customer Name");
            dtlMsg.xxAllLineAddr_SO.setNameForMessage("Sold To Customer Address");
            dtlMsg.shipToCustAcctCd_A.setNameForMessage("Ship To Customer Number");
            // Mod Start 2017/09/15 QC#19922
            //dtlMsg.shipToCustCd_A.setNameForMessage("Ship To Customer Location");
            dtlMsg.shipToCustCd_A.setNameForMessage("Ship To Code");
            // Mod End 2017/09/15 QC#19922
            dtlMsg.dsAcctNm_AI.setNameForMessage("Ship To Customer Name");
            dtlMsg.xxAllLineAddr_SI.setNameForMessage("Ship To Customer Address");
            dtlMsg.dsOrdCatgDescTxt_A.setNameForMessage("Category Code");
            dtlMsg.dsOrdTpDescTxt_A.setNameForMessage("Reason Code");
            dtlMsg.splyQuoteDt_A.setNameForMessage("Quote Date");
            dtlMsg.splyQuoteStsDescTxt_A.setNameForMessage("Quote Status");
            dtlMsg.custIssPoNum_A.setNameForMessage("Customer PO");
            dtlMsg.cpoOrdNum_A.setNameForMessage("Order #");
            dtlMsg.xxPsnNm_A.setNameForMessage("Created By");
        }
    }
}

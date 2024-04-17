/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7240;

import static business.servlet.NMAL7240.constant.NMAL7240Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7240.NMAL7240CMsg;
import business.servlet.NMAL7240.common.NMAL7240CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7240_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#8303
 * 2016/05/17   Fujitsu         W.Honda         Update          QC#7040
 *</pre>
 */
public class NMAL7240_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check. if popup then delete blow
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        NMAL7240CMsg bizMsg = new NMAL7240CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;
        NMAL7240CMsg bizMsg = (NMAL7240CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7240CommonLogic.controlScreen(this, scrnMsg);
        NMAL7240CommonLogic.setFocusRule(scrnMsg);

        setAppFracDigit(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7240BMsg scrnMsg = (NMAL7240BMsg) bMsg;

        // Search Option
        // QC#7040 2016/05/17 Mod start
//        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptPk_S.setNameForMessage("Saved Search Options");
        // QC#7040 2016/05/17 Mod end
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        // Header
        scrnMsg.lineBizTpCd.setNameForMessage("Line of Business");
        scrnMsg.frtZoneNum.setNameForMessage("Zone");
        scrnMsg.xxDsMultMsgDplyTxt_HL.setNameForMessage("Shipping Service Level");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.actvFlg.setNameForMessage("Active");
        scrnMsg.xxFileData_UP.setNameForMessage("Upload Request");
        // QC#7040 2016/05/17 Add start
        scrnMsg.xxLinkAncr_DW.setNameForMessage("Template Download");
        // QC#7040 2016/05/17 Add end

        // Detail
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).lineBizTpDescTxt_A1.setNameForMessage("Line of Business");
            scrnMsg.A.no(i).frtZoneNum_A1.setNameForMessage("Zone");
            scrnMsg.A.no(i).shpgSvcLvlDescTxt_A1.setNameForMessage("Shipping Service Level");
            scrnMsg.A.no(i).fromSclQty_A1.setNameForMessage("From Scale Quantity");
            scrnMsg.A.no(i).qtyUnitTpCd_A1.setNameForMessage("UOM");
            scrnMsg.A.no(i).shpgFrtRate_A1.setNameForMessage("Rate");
            scrnMsg.A.no(i).frtRateCcyCd_A1.setNameForMessage("Unit");
            scrnMsg.A.no(i).frtRatePerNum_A1.setNameForMessage("Per");
            scrnMsg.A.no(i).perUnitTpCd_A1.setNameForMessage("UOM");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Date To");
        }
    }

    private void setAppFracDigit(NMAL7240BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).shpgFrtRate_A1.setAppFracDigit(2);
        }
    }
}

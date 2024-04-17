/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7230;

import static business.servlet.NMAL7230.constant.NMAL7230Constant.BIZ_ID;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7230.NMAL7230CMsg;
import business.servlet.NMAL7230.common.NMAL7230CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NMAL7230_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Fujitsu         W.Honda         Create          N/A
 * 2016/05/13   Fujitsu         W.Honda         Update          QC#8303
 *</pre>
 */
public class NMAL7230_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        NMAL7230CMsg bizMsg = new NMAL7230CMsg();

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;
        NMAL7230CMsg bizMsg = (NMAL7230CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7230CommonLogic.controlScreen(this, scrnMsg);
        NMAL7230CommonLogic.setFocusRule(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.lineBizTpCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL7230BMsg scrnMsg = (NMAL7230BMsg) bMsg;

        // Search Option
        scrnMsg.srchOptPk_S.setNameForMessage("Search Option");
        scrnMsg.srchOptNm_S.setNameForMessage("Search Option Name");

        scrnMsg.lineBizTpCd.setNameForMessage("Line of Business");
        scrnMsg.frtZoneNum.setNameForMessage("Zone");
        scrnMsg.xxDsMultMsgDplyTxt_HS.setNameForMessage("State");
        scrnMsg.xxDsMultMsgDplyTxt_HC.setNameForMessage("Country");
        scrnMsg.xxDsMultMsgDplyTxt_HA.setNameForMessage("Customer");
        scrnMsg.xxDsMultMsgDplyTxt_HG.setNameForMessage("Customer Group");
        scrnMsg.shipToFromPostCd.setNameForMessage("Postal Code From");
        scrnMsg.shipToThruPostCd.setNameForMessage("Postal Code To");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.actvFlg.setNameForMessage("Active");
        scrnMsg.xxFileData_UP.setNameForMessage("Upload Request");
        // QC#8302 2016/05/13 Add start
        scrnMsg.xxLinkAncr_DW.setNameForMessage("Template Download");
        // QC#8302 2016/05/13 Add end

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setNameForMessage("Check Box");
            scrnMsg.A.no(i).lineBizTpDescTxt_A1.setNameForMessage("LOB");
            scrnMsg.A.no(i).dsAcctNm_A1.setNameForMessage("Customer");
            scrnMsg.A.no(i).prcGrpNm_A1.setNameForMessage("Customer Group");
            scrnMsg.A.no(i).frtZoneNum_A1.setNameForMessage("Zone");
            scrnMsg.A.no(i).shipToStCd_A1.setNameForMessage("State");
            scrnMsg.A.no(i).shipToCtryCd_A1.setNameForMessage("Country");
            scrnMsg.A.no(i).shipToFromPostCd_A1.setNameForMessage("Postal From");
            scrnMsg.A.no(i).shipToThruPostCd_A1.setNameForMessage("Postal To");
            scrnMsg.A.no(i).effFromDt_A1.setNameForMessage("Effective Date From");
            scrnMsg.A.no(i).effThruDt_A1.setNameForMessage("Effective Date To");
        }
    }
}

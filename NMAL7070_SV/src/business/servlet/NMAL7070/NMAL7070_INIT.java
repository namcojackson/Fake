/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7070;

import static business.servlet.NMAL7070.constant.NMAL7070Constant.BIZ_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7070.NMAL7070CMsg;
import business.servlet.NMAL7070.common.NMAL7070CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Supply Agreement Plan Search
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/08   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public class NMAL7070_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;

        NMAL7070CMsg bizMsg = new NMAL7070CMsg();
        NMAL7070CommonLogic.setPage(scrnMsg, 1);
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;
        NMAL7070CMsg bizMsg = (NMAL7070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL7070CommonLogic.initControlScreen(this, scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7070BMsg scrnMsg = (NMAL7070BMsg) bMsg;

        scrnMsg.srchOptPk.setNameForMessage("Save Search Option");
        scrnMsg.srchOptNm.setNameForMessage("Search Option Name");
        scrnMsg.splyAgmtPlnNm.setNameForMessage("Plan Name");
        scrnMsg.splyAgmtPlnShortNm.setNameForMessage("Plan Short Name");
        scrnMsg.splyAgmtPlnDescTxt.setNameForMessage("Plan Description/Notes");
        scrnMsg.splyAgmtPlnTpCd.setNameForMessage("Plan Type");
        scrnMsg.splyAgmtDocTpCd.setNameForMessage("Document Type");
        scrnMsg.dsAcctNum.setNameForMessage("Account Number");
        scrnMsg.dsAcctNm.setNameForMessage("Account Name");
        scrnMsg.csmpNum.setNameForMessage("CSMP#");
        scrnMsg.coaBrCd.setNameForMessage("Branch#");
        scrnMsg.lineBizTpCd.setNameForMessage("Line Of Business");
        scrnMsg.effFromDt.setNameForMessage("Effective Date From");
        scrnMsg.effThruDt.setNameForMessage("Effective Date To");
        scrnMsg.splyAgmtPlnStsCd.setNameForMessage("Status");
    }
}

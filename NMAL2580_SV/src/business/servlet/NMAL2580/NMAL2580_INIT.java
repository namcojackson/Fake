/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2580;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2580.NMAL2580CMsg;
import business.servlet.NMAL2580.common.NMAL2580CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2017/12/08   Fujitsu         N.Sugiura       Update          QC#21692
 *</pre>
 */
public class NMAL2580_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;
        NMAL2580CMsg bizMsg = new NMAL2580CMsg();

        // IN Parameter Get
        String[] params = (String[]) getArgForSubScreen();
        ZYPEZDItemValueSetter.setValue(scrnMsg.ezInAplID, params[0]);

        bizMsg.setBusinessID("NMAL2580");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;
        NMAL2580CMsg bizMsg = (NMAL2580CMsg) cMsg;

        NMAL2580CommonLogic.initCmnBtnProp(this);
        NMAL2580CommonLogic.initialControlScreen(this, scrnMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            return;
        }

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL2580BMsg scrnMsg = (NMAL2580BMsg) bMsg;

        scrnMsg.trtyUpdRqstHdrPk.setNameForMessage("Request ID");
        scrnMsg.rqstUsrId.setNameForMessage("Employee ID");
        scrnMsg.fill103Txt.setNameForMessage("Employee Name");
        scrnMsg.rqstCratSysTpCd.setNameForMessage("Request Status");
        scrnMsg.rqstRsltTpCd.setNameForMessage("Request Type");
        scrnMsg.rqstRsltCmntTxt.setNameForMessage("Mass Update Reason");
        scrnMsg.effFromDt.setNameForMessage("Request Date From");
        scrnMsg.effToDt.setNameForMessage("Request Date To");

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).trtyUpdRqstHdrPk_A.setNameForMessage("Request ID");
            scrnMsg.A.no(i).rqstUsrId_A.setNameForMessage("Employee ID");
            scrnMsg.A.no(i).fill103Txt_A.setNameForMessage("Employee Name");
            scrnMsg.A.no(i).rqstDtTmTsTxt_A.setNameForMessage("Request Date");
            scrnMsg.A.no(i).rqstCratSysTpDescTxt_A.setNameForMessage("Request Status");
            scrnMsg.A.no(i).rqstRsltTpDescTxt_A.setNameForMessage("Request Type");
            // Mod Start 2017/12/08 QC#21692
            scrnMsg.A.no(i).rqstRsltCmntTxt_A.setNameForMessage("Upload Result");
            scrnMsg.A.no(i).massUpdRsnCmntTxt_A.setNameForMessage("Mass Update Reason");
            // Mod End 2017/12/08 QC#21692
        }
    }
}

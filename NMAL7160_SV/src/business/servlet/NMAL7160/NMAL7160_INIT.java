/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7160;

import static business.servlet.NMAL7160.constant.NMAL7160Constant.BIZ_ID;
import static business.servlet.NMAL7160.constant.NMAL7160Constant.FUNCTION_CD;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL7160.NMAL7160CMsg;
import business.servlet.NMAL7160.common.NMAL7160CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/17   Fujitsu         T.Murai         Create          N/A
 *</pre>
 */
public class NMAL7160_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;

        NMAL7160CMsg bizMsg = new NMAL7160CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;
        NMAL7160CMsg bizMsg = (NMAL7160CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

        NMAL7160CommonLogic.initialControlScreen(this, getUserProfileService(), scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NMAL7160BMsg scrnMsg = (NMAL7160BMsg) bMsg;
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.mdseCd.setNameForMessage("Item Code");
        scrnMsg.invNum.setNameForMessage("Invoice Number");
        scrnMsg.csmpContrNum.setNameForMessage("CSMP Number");
        scrnMsg.dlrRefNum.setNameForMessage("CSA Number");
        scrnMsg.csmpInvErrDt_FR.setNameForMessage("Error Date From");
        scrnMsg.csmpInvErrDt_TO.setNameForMessage("Error Date To");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NMAL7160_ABMsg contactMsg = scrnMsg.A.no(i);
            contactMsg.xxChkBox_A.setNameForMessage("Line Selection Check Box");
        }
    }
}

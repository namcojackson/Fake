/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0910;

import static business.servlet.NSAL0910.constant.NSAL0910Constant.*;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0910.NSAL0910CMsg;
import business.servlet.NSAL0910.common.NSAL0910CommonLogic;

import com.canon.cusa.s21.framework.online.pagenation.S21BatchSearchPagenationScrnSupport;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/10   Hitachi         K.Kasai         Create          N/A
 *</pre>
 */
public class NSAL0910_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;

        NSAL0910CMsg bizMsg = new NSAL0910CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;
        NSAL0910CMsg bizMsg  = (NSAL0910CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0910CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NSAL0910CommonLogic.activateButtonsByFuncList(this, scrnMsg, functionList);
        NSAL0910CommonLogic.activateScreenItemsByFuncList(this, functionList, scrnMsg);
        NSAL0910CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0910CommonLogic.focusItem(scrnMsg);

    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0910BMsg scrnMsg = (NSAL0910BMsg) bMsg;
        scrnMsg.dsAcctDlrCd.setNameForMessage("Dealer Code");
        scrnMsg.procDt_FR.setNameForMessage("As of Date(From)");
        scrnMsg.procDt_TO.setNameForMessage("As of Date(To)");
        scrnMsg.cfsInvProcStsCd.setNameForMessage("Status");
        scrnMsg.xxCratDt_FR.setNameForMessage("Creation Date(From)");
        scrnMsg.xxCratDt_TO.setNameForMessage("Creation Date(To)");
        scrnMsg.csaInvNum.setNameForMessage("Invoice Number");
        scrnMsg.csaContrNum.setNameForMessage("Contract Number");
        scrnMsg.cfsSerNum.setNameForMessage("Serial Number");
        scrnMsg.xxPageShowCurNum.setNameForMessage(S21BatchSearchPagenationScrnSupport.getCurrentPageFieldName());
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).csaInvNum_A.setNameForMessage("Invoice Number");
            scrnMsg.A.no(i).csaContrNum_A.setNameForMessage("Contract Number");
            scrnMsg.A.no(i).cfsSerNum_A.setNameForMessage("Serial Number");
        }
    }
}

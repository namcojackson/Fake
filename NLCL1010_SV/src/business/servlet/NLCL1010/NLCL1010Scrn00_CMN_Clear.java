/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL1010;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL1010.NLCL1010CMsg;
import business.servlet.NLCL1010.common.NLCL1010CommonLogic;
import business.servlet.NLCL1010.constant.NLCL1010Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/19   Fujitsu         Tozuka          Create          R-WH002
 *</pre>
 */
public class NLCL1010Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLCL1010Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;

        NLCL1010CMsg bizMsg = new NLCL1010CMsg();
        bizMsg.setBusinessID(NLCL1010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NLCL1010Constant.FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL1010BMsg scrnMsg = (NLCL1010BMsg) bMsg;
        NLCL1010CMsg bizMsg = (NLCL1010CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NLCL1010Constant.BUSINESS_ID);
        NLCL1010CommonLogic.clearGUIAttribute(scrnMsg, scrnMsg.A.no(0).getBaseContents());
        NLCL1010CommonLogic.setInitialScrnFieldsState(this, scrnMsg, functionList);

        if (NLCL1010Constant.MESSAGE_KIND_ERROR.equals(bizMsg.getMessageKind())) {
            return;
        }
        scrnMsg.setFocusItem(scrnMsg.serNum_C0);
    }
}

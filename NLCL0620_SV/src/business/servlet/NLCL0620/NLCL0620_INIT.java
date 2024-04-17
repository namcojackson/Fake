/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620;

import static business.servlet.NLCL0620.constant.NLCL0620Constant.BIZ_APP_ID;
import static business.servlet.NLCL0620.constant.NLCL0620Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0620.NLCL0620CMsg;
import business.servlet.NLCL0620.common.NLCL0620CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : Init
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/02/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0620_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        NLCL0620CMsg bizMsg = new NLCL0620CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;
        NLCL0620CMsg bizMsg  = (NLCL0620CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NLCL0620CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // set focus
        scrnMsg.setFocusItem(scrnMsg.techTocCd);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NLCL0620CommonLogic.setNameForMessage((NLCL0620BMsg) scrnMsg);
    }

}
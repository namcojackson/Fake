/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.BIZ_APP_ID;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLCL0600.NLCL0600CMsg;
import business.servlet.NLCL0600.common.NLCL0600CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Init
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/24/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0600_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length == 1) {
            EZDBStringItem param01 = (EZDBStringItem) params[0];
            ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyNum_IP, param01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyNum, param01);
        }

        NLCL0600CMsg bizMsg = new NLCL0600CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;
        NLCL0600CMsg bizMsg  = (NLCL0600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NLCL0600CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NLCL0600CommonLogic.setNameForMessage((NLCL0600BMsg) scrnMsg);
    }

}

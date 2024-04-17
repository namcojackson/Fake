/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1520;

import static business.servlet.NPAL1520.constant.NPAL1520Constant.BIZ_APP_ID;
import static business.servlet.NPAL1520.constant.NPAL1520Constant.FUNCTION_CD_SEARCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1520.NPAL1520CMsg;
import business.servlet.NPAL1520.common.NPAL1520CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Business ID : NPAL1520 Min-Max Planning Inquiry
 * Function Name : Init
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/11/2016   CITS            Keiichi Masaki  Create          N/A
 *</pre>
 */
public class NPAL1520_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;

        NPAL1520CMsg bizMsg = new NPAL1520CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        // Set UserID
        ZYPEZDItemValueSetter.setValue(bizMsg.srchOptUsrId_U1, getContextUserInfo().getUserId());

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1520BMsg scrnMsg = (NPAL1520BMsg) bMsg;
        NPAL1520CMsg bizMsg  = (NPAL1520CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1520CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Focus (Plan Name)
        scrnMsg.setFocusItem(scrnMsg.mrpPlnNm);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NPAL1520CommonLogic.setNameForMessage((NPAL1520BMsg) scrnMsg);

    }
}

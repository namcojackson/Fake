/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1270;

import static business.servlet.NPAL1270.constant.NPAL1270Constant.BIZ_APP_ID;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.BTN_NEW;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1270.constant.NPAL1270Constant.PO_R_ENTRY_BIZ_APP_ID;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1270.NPAL1270CMsg;
import business.servlet.NPAL1270.common.NPAL1270CommonLogic;
import business.servlet.NPAL1270.constant.NPAL1270Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;


/** 
 *<pre>
 * PO Requisition List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/12/2016   CITS            R Shimamoto     Create          N/A
 *</pre>
 */
public class NPAL1270_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        NPAL1270CMsg bizMsg = new NPAL1270CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1270BMsg scrnMsg = (NPAL1270BMsg) bMsg;
        NPAL1270CMsg bizMsg  = (NPAL1270CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // PO Requisition Entry Check
        if (!isBusinessAppGranted(getContextUserInfo().getUserId(), PO_R_ENTRY_BIZ_APP_ID)) {
            setButtonEnabled(BTN_NEW, false);
        }
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        NPAL1270CommonLogic.ctrlScrnItemDispInit(this, scrnMsg, funcList);

        // Set Forcus
        scrnMsg.setFocusItem(scrnMsg.prchReqNum);

        // Default Display Level
        scrnMsg.xxScrDply.setValue(NPAL1270Constant.DEFAULT_DISPLAY_LEVEL);

    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {

        NPAL1270CommonLogic.setNameForMessage((NPAL1270BMsg) scrnMsg);
    }
}

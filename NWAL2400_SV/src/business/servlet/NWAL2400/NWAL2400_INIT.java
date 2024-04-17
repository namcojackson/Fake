/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2400;

import static business.servlet.NWAL2400.constant.NWAL2400Constant.BIZ_APP_ID;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.CHKBOX_APPEND;
import static business.servlet.NWAL2400.constant.NWAL2400Constant.FUNCTION_CD_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL2400.NWAL2400CMsg;
import business.servlet.NWAL2400.common.NWAL2400CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 * <pre>
 * Business ID : NWAL2400 CUSA Retail Dealer Maintenance
 * </pre>
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/12   CITS            T.Gotoda        Create          N/A
 *</pre>
 */
public class NWAL2400_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;

        // Default value
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxSelRadioBtnObj, CHKBOX_APPEND);

        NWAL2400CMsg bizMsg = new NWAL2400CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2400BMsg scrnMsg = (NWAL2400BMsg) bMsg;
        NWAL2400CMsg bizMsg = (NWAL2400CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NWAL2400CommonLogic.ctrlScrnItemDispInit(this, scrnMsg);
        NWAL2400CommonLogic.ctrlDetailButton(this, scrnMsg);
        NWAL2400CommonLogic.setTableScreen(scrnMsg);

        // Set Focus
        scrnMsg.setFocusItem(scrnMsg.rtlDlrCd);
    }

    @Override
    protected void setNameForMessage(EZDBMsg scrnMsg) {
        NWAL2400CommonLogic.setNameForMessage((NWAL2400BMsg) scrnMsg);
    }
}

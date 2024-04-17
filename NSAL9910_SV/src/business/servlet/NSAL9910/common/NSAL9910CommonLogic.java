/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9910.common;

import static business.servlet.NSAL9910.constant.NSAL9910Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.process.S21SelectedProcessInfo;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL9910.NSAL9910BMsg;

/**
 *<pre>
 * General Business Master Maintenance List
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/29   Hitachi         A.Kohinata      Create          N/A
 * 2018/06/04   CITS            M.Naito         Update          QC#24320
 *</pre>
 */
public class NSAL9910CommonLogic {

    /**
     * get FuncId
     * @param scrnMsg NSAL9910BMsg
     */
    public static void getFuncId(NSAL9910BMsg scrnMsg) {

        List<String> funcList = S21UserProfileServiceFactory.getInstance().getService().getFunctionCodeListForBizAppId(BUSINESS_ID_NSAL9900);
        if (funcList == null || funcList.isEmpty()) {
            // START 2018/06/04 M.Naito [QC#24320, MOD]
            throw new S21AbendException("You can't operate Master Maintenance (" + BUSINESS_ID_NSAL9900 + "). UserID is -> " + S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId());
            // END 2018/06/04 M.Naito [QC#24320, MOD]
        }

        int i = 0;
        for (String funcID : funcList) {
            setValue(scrnMsg.B.no(i).bizFuncId, funcID);
            i++;
        }
        scrnMsg.B.setValidCount(i);
    }

    /**
     * get ProcessId
     * @param ctx EZDApplicationContext
     * @param scrnMsg NSAL9910BMsg
     */
    public static void getProcessId(EZDApplicationContext ctx, NSAL9910BMsg scrnMsg) {

        S21SelectedProcessInfo selectInfo = (S21SelectedProcessInfo) ctx.getAttribute(CONTEXTKEY_PROCESS_INFO);
        if (selectInfo != null && selectInfo.getProcessId() != null) {
            setValue(scrnMsg.bizFuncGrpId, selectInfo.getProcessId());
        }
    }

    /**
     * The initial state of the screen item is set.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL9910BMsg
     */
    public static void initialControlScreen(EZDCommonHandler handler, NSAL9910BMsg scrnMsg) {

        initControlCommonButton(handler);
        setTableBGColor(scrnMsg);
    }

    private static void initControlCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_SAVE[0], BTN_CMN_SAVE[1], BTN_CMN_SAVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_SUBMIT[0], BTN_CMN_SUBMIT[1], BTN_CMN_SUBMIT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0], BTN_CMN_APPLY[1], BTN_CMN_APPLY[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0], BTN_CMN_APPROVE[1], BTN_CMN_APPROVE[2], 0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0], BTN_CMN_REJECT[1], BTN_CMN_REJECT[2], 0, null);
        handler.setButtonProperties(BTN_CMN_DOWNROAD[0], BTN_CMN_DOWNROAD[1], BTN_CMN_DOWNROAD[2], 0, null);
        handler.setButtonProperties(BTN_CMN_BLANK7[0], BTN_CMN_BLANK7[1], BTN_CMN_BLANK7[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RESET[0], BTN_CMN_RESET[1], BTN_CMN_RESET[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0], BTN_CMN_RETURN[1], BTN_CMN_RETURN[2], 1, null);
    }

    private static void setTableBGColor(NSAL9910BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.clearRowsBG("A", scrnMsg.A);
            tblColor.setAlternateRowsBG("A", scrnMsg.A);
        } else {
            tblColor.clearRowsBG("A", scrnMsg.A);
        }
    }
}

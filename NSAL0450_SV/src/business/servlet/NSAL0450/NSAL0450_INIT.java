/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0450;

import static business.servlet.NSAL0450.constant.NSAL0450Constant.*;
import static business.servlet.NSAL0450.common.NSAL0450CommonLogic.*;

import java.io.Serializable;
import java.math.BigDecimal;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NSAL0450.NSAL0450CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/13   Hitachi         J.Kim           Create          N/A
 * 2015/12/08   Hitachi         T.Kanasaka      Update          QC1621
 * 2016/04/27   Hitachi         T.Iwamoto       Update          QC#1759
 * 2019/01/21   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public class NSAL0450_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
        scrnMsg.putErrorScreen();
    }

    // START 2015/12/08 T.Kanasaka [QC1621, MOD]
    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;

        BigDecimal dsContrPk = null;
        String xxModeCd = null;
        Serializable ser = getArgForSubScreen();

        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    dsContrPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
                if (prm.length > 1 && prm[1] != null && prm[1] instanceof EZDBStringItem) {
                    xxModeCd = ((EZDBStringItem) prm[1]).getValue();
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrPk_H, dsContrPk);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, xxModeCd);

        NSAL0450CMsg bizMsg = new NSAL0450CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }
    // END 2015/12/08 T.Kanasaka [QC1621, MOD]

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;
        NSAL0450CMsg bizMsg  = (NSAL0450CMsg) cMsg;

        activateButtons(this, scrnMsg);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // ADD start 2016/04/27 CSA Defect#1759
        setRowColors(scrnMsg);
        // ADD end 2016/04/27 CSA Defect#1759
        // Add Start 2019/01/22 QC#29782
        cotrolDetailField(scrnMsg);
        // Add End 2019/01/22 QC#29782

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0450BMsg scrnMsg = (NSAL0450BMsg) bMsg;

        scrnMsg.coaAfflAcctNm_H.setNameForMessage("Default Account");
    }

    /**
     * Initialize the items and buttons on the screen.
     * @param handler EZDCommonHandler
     * @param scrnMsg NSAL0580BMsg
     * @param functionList List<String>
     */
    private static void activateButtons(EZDCommonHandler handler, NSAL0450BMsg scrnMsg) {

        // Default Account
        scrnMsg.coaAfflAcctNm_H.setInputProtected(true);

        // set button property
        // common button
        handler.setButtonProperties(SAVE[0], SAVE[1], SAVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(SUBMIT[0], SUBMIT[1], SUBMIT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPLY[0], APPLY[1], APPLY[2], BTN_INACTIVE, null);
        handler.setButtonProperties(APPROVE[0], APPROVE[1], APPROVE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(REJECT[0], REJECT[1], REJECT[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DOWNLOAD[0], DOWNLOAD[1], DOWNLOAD[2], BTN_INACTIVE, null);
        handler.setButtonProperties(DELETE[0], DELETE[1], DELETE[2], BTN_INACTIVE, null);
        handler.setButtonProperties(CLEAR[0], CLEAR[1], CLEAR[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RESET[0], RESET[1], RESET[2], BTN_INACTIVE, null);
        handler.setButtonProperties(RETURN[0], RETURN[1], RETURN[2], BTN_ACTIVE, null);
    }
}

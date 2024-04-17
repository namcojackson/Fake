/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0600;

import static business.servlet.NSAL0600.common.NSAL0600CommonLogic.*;
import static business.servlet.NSAL0600.constant.NSAL0600Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0600.NSAL0600CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Cascade Date
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Hitachi         T.Tomita        Create          N/A
 * 2016/01/25   Hitachi         T.Tomita        Update          CSA QC#2721
 *</pre>
 */
public class NSAL0600_INIT extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check of this screen.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL0600BMsg scrnMsg = (NSAL0600BMsg) bMsg;
        NSAL0600CMsg bizMsg = createCMsgForSearch();

        // Set Parameter
        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
            setValue(scrnMsg.dsContrPk_P, param00);
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0600BMsg scrnMsg = (NSAL0600BMsg) bMsg;
        NSAL0600CMsg bizMsg = (NSAL0600CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        screenControlProcess(this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.xxFromDt_H);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0600BMsg scrnMsg = (NSAL0600BMsg) bMsg;

        // Header
        scrnMsg.xxFromDt_H.setNameForMessage("New Start Date");
        scrnMsg.xxThruDt_H.setNameForMessage("New End Date");
        scrnMsg.svcMemoRsnCd_HS.setNameForMessage("Reason Code");
        scrnMsg.svcCmntTxt_H.setNameForMessage("Notes");
    }
}

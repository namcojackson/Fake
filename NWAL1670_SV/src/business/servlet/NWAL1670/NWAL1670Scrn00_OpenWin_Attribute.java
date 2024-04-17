/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1670;

import static business.servlet.NWAL1670.constant.NWAL1670Constant.SCRRN_TRANS_COND_NMAL6050;
import static business.servlet.NWAL1670.constant.NWAL1670Constant.SCRRN_TRANS_COND_NMAL6760;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1670.common.NWAL1670CommonLogic;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_TEAM_ATTRB_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/07   Hitachi         O.Okuma         Create          N/A
 *</pre>
 */
public class NWAL1670Scrn00_OpenWin_Attribute extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1670BMsg scrnMsg = (NWAL1670BMsg) bMsg;

        int index = getButtonSelectNumber();
        setValue(scrnMsg.xxNum_PO, new BigDecimal(index));
        addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1670BMsg scrnMsg = (NWAL1670BMsg) bMsg;

        int index = scrnMsg.xxNum_PO.getValueInt();

        NWAL1670CommonLogic.clearParam(scrnMsg);

        String ordTeamAttrbTpCd = scrnMsg.C.no(index).ordTeamAttrbTpCd_C.getValue();
        Object[] params = null;

        if (ORD_TEAM_ATTRB_TP.CUSTOMER_NUMBER.equals(ordTeamAttrbTpCd)) {

            params = NWAL1670CommonLogic.setParamForAccountPopup(scrnMsg);
            this.setResult(SCRRN_TRANS_COND_NMAL6760);

        } else if (ORD_TEAM_ATTRB_TP.LINE_OF_BUSINESS.equals(ordTeamAttrbTpCd)) {

            params = NWAL1670CommonLogic.setParamForCommonCodePopup(scrnMsg, true);
            this.setResult(SCRRN_TRANS_COND_NMAL6050);

        } else if (ORD_TEAM_ATTRB_TP.BRANCH.equals(ordTeamAttrbTpCd)) {

            params = NWAL1670CommonLogic.setParamForCommonCodePopup(scrnMsg, false);
            this.setResult(SCRRN_TRANS_COND_NMAL6050);
        } else {
            return;
        }

        setArgForSubScreen(params);
    }

    private void addCheckItem(NWAL1670BMsg scrnMsg) {
        int index = scrnMsg.xxNum_PO.getValueInt();
        scrnMsg.addCheckItem(scrnMsg.C.no(index).ordTeamAttrbTpCd_C);
    }
}

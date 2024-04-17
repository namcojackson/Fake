/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import static business.servlet.NLEL0080.constant.NLEL0080Constant.*;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NLEL0080.NLEL0080CMsg;
import business.servlet.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/25   Hitachi         J.Kim           Create          N/A
 * 2016/05/17   Hitachi         K.Kojima        Update          QC#8101
 * 2016/09/15   Hitachi         J.Kim           Update          QC#10360
 * 2017/07/03   CITS            K.Fukumura      Update          QC#19671
 *</pre>
 */
public class NLEL0080Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;
        // START 2016/09/14 J.Kim [QC#10360,MOD]
        if (ASSET_SRC_TP.PROCURED.equals(scrnMsg.xxScrDply.getValue())) {
            // START 2016/05/17 K.Kojima [QC#8101,ADD]
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).xxScrItem81Txt)) {
                    scrnMsg.A.no(i).xxScrItem81Txt.setErrorInfo(1, ZZM9000E, new String[] {"Expence Acct" });
                }
            }
            // END 2016/05/17 K.Kojima [QC#8101,ADD]
        } else {
            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                scrnMsg.A.no(i).xxScrItem81Txt.clearErrorInfo();
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).serNum)) {
                    scrnMsg.A.no(i).serNum.setErrorInfo(1, ZZM9000E, new String[] {"Serial Number" });
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).depcMthNum)) {
                    if (!scrnMsg.A.no(i).depcMthNum.getValue().matches(REGEX_NUMBER)) {
                        scrnMsg.A.no(i).depcMthNum.setErrorInfo(1, ZZM9015E, new String[] {"Life in Months" });
                    }
                }
            }
        }
        // END 2016/09/14 J.Kim [QC#10360,MOD]

        NLEL0080CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        NLEL0080CMsg bizMsg = NLEL0080CommonLogic.setRequestData_SubmitCommon();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;
        NLEL0080CMsg bizMsg = (NLEL0080CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLEL0080CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        } else {
            NLEL0080CommonLogic.setListInactive(this, scrnMsg);
        }
        NLEL0080CommonLogic.initAppFracDigit(scrnMsg);
        NLEL0080CommonLogic.setFocusRule(scrnMsg);
    }
}

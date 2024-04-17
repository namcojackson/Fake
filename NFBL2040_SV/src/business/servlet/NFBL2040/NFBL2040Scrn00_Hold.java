/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_40;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.MESSAGE_KIND_E;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.NFBM0238E;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.NLAM0047E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL2040.NFBL2040CMsg;
import business.servlet.NFBL2040.common.NFBL2040CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/08/26   Hitachi         Y.Tsuchimoto    Create          QC#12043
 *</pre>
 */
public class NFBL2040Scrn00_Hold extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {


        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Name" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldCd_H1);
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.H.no(i).pmtHldRsnCd_H1.getValue())) {
                    scrnMsg.H.no(i).pmtHldRsnCd_H1.setErrorInfo(1, NLAM0047E, new String[] {"Hold Reason" });
                    scrnMsg.addCheckItem(scrnMsg.H.no(i).pmtHldRsnCd_H1);
                }
            }
        }
        int validCount = 0;
        for (int i = 0; i < scrnMsg.H.getValidCount(); i++) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.H.no(i).xxInsUpdDelFlg_H1.getValue())) {
                if (!scrnMsg.H.no(i).xxChkBox_H2.isInputProtected()) {
                    validCount = validCount + 1;
                }
            }
        }
        if (validCount == 0) {
            scrnMsg.setMessageInfo(NFBM0238E);
            throw new EZDFieldErrorException();
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;

        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_40);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NFBL2040CommonLogic.commonAddCheckHoldItem(scrnMsg);
        scrnMsg.putErrorScreen();
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        NFBL2040CommonLogic.setInputResetForAfterHold(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.cmApInvTpCd_S);
        NFBL2040CommonLogic.setButtonAfterHold(this, scrnMsg);
        NFBL2040CommonLogic.setInputProtectedForAfterHold(scrnMsg);

        NFBL2040CommonLogic.clearRowsBG_NoSelectTab(bMsg);

    }
}

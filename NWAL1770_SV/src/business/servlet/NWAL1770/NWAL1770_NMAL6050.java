/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/18   Fujitsu         T.Yoshida       Create          N/A
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 *</pre>
 */
public class NWAL1770_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2017/09/28 S21_NA#21121
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscDescTxt)) {
            scrnMsg.pmtTermCashDiscCd.clear();
            return null;
        }

        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // Add End 2017/09/28 S21_NA#21121
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // Add Start 2017/09/28 S21_NA#21121
        if (!ZYPCommonFunc.hasValue(scrnMsg.pmtTermCashDiscDescTxt)) {

            scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscDescTxt);
            scrnMsg.putErrorScreen();

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                throw new EZDFieldErrorException();
            }
            scrnMsg.setFocusItem(scrnMsg.pmtTermCashDiscDescTxt);

            NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
            return;
        }

        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add End 2017/09/28 S21_NA#21121

        scrnMsg.addCheckItem(scrnMsg.pmtTermCashDiscDescTxt);
        scrnMsg.putErrorScreen();

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            throw new EZDFieldErrorException();
        }

        // Add Start 2017/09/28 S21_NA#21121
        if (PMT_TERM_CASH_DISC.CREDIT_CARD.equals(scrnMsg.pmtTermCashDiscCd.getValue())) {
            scrnMsg.dsPmtMethCd.setValue(DS_PMT_METH.CREDIT_CARD);
        }
        // Add End 2017/09/28 S21_NA#21121
        scrnMsg.setFocusItem(scrnMsg.pmtTermCashDiscDescTxt);

        NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_UPDT;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.SCREEN_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;
import business.servlet.NWAL1770.common.NWAL1770CommonLogicForScrnFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         T.Yoshida       Create          N/A
 * 2017/09/28   Fujitsu         T.Murai         Update          S21_NA#21121
 * 2017/10/17   Hitachi         J.Kim           Update          QC#21760
 *</pre>
 */
public class NWAL1770Scrn00_MoveWin_CreditCard extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2017/09/28 S21_NA#21121
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        // has Searched Number
        if (!ZYPCommonFunc.hasValue(scrnMsg.splyQuoteNum_BK)) {
            scrnMsg.clearAllGUIAttribute(SCREEN_ID);
            NWAL1770CommonLogic.checkItemAllFields(scrnMsg);
            NWAL1770CommonLogic.checkPmtTermsField(scrnMsg);
            NWAL1770CommonLogic.checkCustPoField(scrnMsg);
            NWAL1770CommonLogic.checkContactField(scrnMsg);
            // START 2017/10/17 J.Kim [QC#21760,DEL]
            // NWAL1770CommonLogic.checkRddField(scrnMsg);
            // END 2017/10/17 J.Kim [QC#21760,DEL]
        }
        // Add End 2017/09/28 S21_NA#21121
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // Add Start 2017/09/28 S21_NA#21121
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.splyQuoteNum_BK)) {

            NWAL1770CMsg bizMsg = new NWAL1770CMsg();
            bizMsg.setBusinessID(BIZ_ID);
            bizMsg.setFunctionCode(FUNC_CD_UPDT);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);

            return bizMsg;
        }
        // Add End 2017/09/28 S21_NA#21121

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;

        // Add Start 2017/09/28 S21_NA#21121
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.clearAllGUIAttribute(SCREEN_ID);

            NWAL1770CommonLogic.checkItemAllFields(scrnMsg);

            if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                setResult(ZYPConstant.FLG_OFF_N);
                throw new EZDFieldErrorException();
            } else if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
                setResult(ZYPConstant.FLG_OFF_N);
                return;
            }

            NWAL1770CommonLogicForScrnFields.setProtect(this, scrnMsg);
        }
        setResult(ZYPConstant.FLG_ON_Y);
        // Add End 2017/09/28 S21_NA#21121

        Object[] params = NWAL1770CommonLogic.getParamNWAL2010(scrnMsg);
        setArgForSubScreen(params);
    }
}

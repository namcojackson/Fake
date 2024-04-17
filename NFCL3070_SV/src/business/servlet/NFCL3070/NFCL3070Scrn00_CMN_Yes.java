/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3070;

import static business.servlet.NFCL3070.constant.NFCL3070Constant.BIZ_ID;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.FUNC_CD_UPD;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.NFCM0788E;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.NZZM0002I;
import static business.servlet.NFCL3070.constant.NFCL3070Constant.RQST_TP_CREDIT_ONLY;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFCL3070.NFCL3070CMsg;
import business.servlet.NFCL3070.common.NFCL3070CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NFCL3070Scrn00_CMN_Yes
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/25   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/19   Fujitsu         S.Fujita        Update          N/A
 * 2016/06/28   Fujitsu         S.Fujita        Update          QC#10942
 * 2019/07/18   Fujitsu         S.Ohki          Update          QC#51614
 * 2019/08/29   Fujitsu         T.Murai         Update          QC#52945
 *</pre>
 */
public class NFCL3070Scrn00_CMN_Yes extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;

        // check duplicate input of Percentage and Amount of credit
        if (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn)
                && RQST_TP_CREDIT_ONLY.compareTo(scrnMsg.xxRadioBtn.getValue()) == 0) {
//          // START 2019/08/29 [QC#52945,DEL]
//          // START 2019/07/18 S.Ohki [QC#51614,ADD]
//            if (AR_CR_TP.TAX.equals(scrnMsg.arCrTpCd.getValue())) {
//                NFCL3070CommonLogic.isCheckTypeTax(scrnMsg);
//            }
//          // END 2019/07/18 S.Ohki [QC#51614,ADD]
//          // END 2019/08/29 [QC#52945,DEL]
            NFCL3070CommonLogic.isDuplicateCrPercentAndAmount(scrnMsg);
        }

        // error if Comment are not in
        if (!ZYPCommonFunc.hasValue(scrnMsg.arCrRebilAddlCmntTxt)) {
            scrnMsg.arCrRebilAddlCmntTxt.setErrorInfo(1, NFCM0788E);
            scrnMsg.putErrorScreen();
        }

        NFCL3070CommonLogic.addCheckItemAllItems(scrnMsg);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        NFCL3070CMsg bizMsg = new NFCL3070CMsg();

        scrnMsg.allwFlg.setValue(ZYPConstant.FLG_ON_Y);

        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3070BMsg scrnMsg = (NFCL3070BMsg) bMsg;
        NFCL3070CMsg bizMsg  = (NFCL3070CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/06/28 S.Fujita [QC#10942,ADD]
        // if mode is the CreditOnly, crCredit Amount restore negative number
        if (bizMsg.xxPgFlg_C.getValue().equals(ZYPConstant.FLG_ON_Y) && ZYPCommonFunc.hasValue(bizMsg.crRebilAmt_CO)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.crRebilAmt_CO, scrnMsg.crRebilAmt_CO.getValue().negate());
        }
        // END   2016/06/28 S.Fujita [QC#10942,ADD]

        // check Error
        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            return;
        }

        // protect all field
        NFCL3070CommonLogic.protectAllField(this, scrnMsg);
        scrnMsg.setMessageInfo(NZZM0002I);

        scrnMsg.setFocusItem(scrnMsg.xxRadioBtn);
    }
}

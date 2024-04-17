/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0320;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0320.NSAL0320CMsg;
import business.servlet.NSAL0320.common.NSAL0320CommonLogic;
import business.servlet.NSAL0320.constant.NSAL0320Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   CUSA            SRAA            Create          N/A
 * 2015/10/13   Hitachi         T.Tomita        Update          N/A
 * 2016/07/12   Hitachi         M.Gotou         Update          QC#11527
 * 2016/12/09   Hitachi         K.Kojima        Update          QC#16461
 *</pre>
 */
public class NSAL0320_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0320Constant.BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;

        BigDecimal dsContrDtlPk = null;
        // START 2015/10/13 T.Tomita [N/A, ADD]
        String xxModeCd = null;
        // END 2015/10/13 T.Tomita [N/A, ADD]

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBBigDecimalItem) {
                    dsContrDtlPk = ((EZDBBigDecimalItem) prm[0]).getValue();
                }
                // START 2015/10/13 T.Tomita [N/A, ADD]
                if (prm.length > 1 && prm[1] != null && prm[1] instanceof EZDBStringItem) {
                    xxModeCd = ((EZDBStringItem) prm[1]).getValue();
                }
                // END 2015/10/13 T.Tomita [N/A, ADD]
            }
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, dsContrDtlPk);
        // START 2015/10/13 T.Tomita [N/A, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, xxModeCd);
        // END 2015/10/13 T.Tomita [N/A, ADD]

        NSAL0320CMsg bizMsg = new NSAL0320CMsg();
        bizMsg.setBusinessID(NSAL0320Constant.BIZ_ID);
        bizMsg.setFunctionCode(NSAL0320Constant.EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        NSAL0320CMsg bizMsg = (NSAL0320CMsg) cMsg;

        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0320Constant.BIZ_ID);
        NSAL0320CommonLogic.activateButtons(this, scrnMsg, functionList);

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0320CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
        // START 2016/12/09 K.Kojima [QC#16461,DEL]
        // if ("E".equals(bizMsg.getMessageKind())) {
        // throw new EZDFieldErrorException();
        // }
        // END 2016/12/09 K.Kojima [QC#16461,DEL]

        NSAL0320CommonLogic.setupScreenItems(scrnMsg, functionList);

        // START 2016/12/09 K.Kojima [QC#16461,ADD]
        if ("E".equals(bizMsg.getMessageKind())) {
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_1[0], NSAL0320Constant.BTN_CMN_BTN_1[1], NSAL0320Constant.BTN_CMN_BTN_1[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_SUBMIT[0], NSAL0320Constant.BTN_CMN_SUBMIT[1], NSAL0320Constant.BTN_CMN_SUBMIT[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_3[0], NSAL0320Constant.BTN_CMN_BTN_3[1], NSAL0320Constant.BTN_CMN_BTN_3[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_4[0], NSAL0320Constant.BTN_CMN_BTN_4[1], NSAL0320Constant.BTN_CMN_BTN_4[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_5[0], NSAL0320Constant.BTN_CMN_BTN_5[1], NSAL0320Constant.BTN_CMN_BTN_5[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_6[0], NSAL0320Constant.BTN_CMN_BTN_6[1], NSAL0320Constant.BTN_CMN_BTN_6[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_7[0], NSAL0320Constant.BTN_CMN_BTN_7[1], NSAL0320Constant.BTN_CMN_BTN_7[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_BTN_8[0], NSAL0320Constant.BTN_CMN_BTN_8[1], NSAL0320Constant.BTN_CMN_BTN_8[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_RESET[0], NSAL0320Constant.BTN_CMN_RESET[1], NSAL0320Constant.BTN_CMN_RESET[2], 0, null);
            this.setButtonProperties(NSAL0320Constant.BTN_CMN_RETURN[0], NSAL0320Constant.BTN_CMN_RETURN[1], NSAL0320Constant.BTN_CMN_RETURN[2], 1, null);
            this.setButtonEnabled(NSAL0320Constant.BTN_ONCHANGE_SERVICEPROGRAM, false);
            scrnMsg.setInputProtected(true);
        }
        // END 2016/12/09 K.Kojima [QC#16461,ADD]
        // START 2016/07/12 M.Gotou [QC#11527, ADD]
        scrnMsg.setFocusItem(scrnMsg.prcMtrPkgPk_B);
        // END 2016/07/12 M.Gotou [QC#11527, ADD]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0320BMsg scrnMsg = (NSAL0320BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).bllblFlg.setNameForMessage("Billable");
            scrnMsg.A.no(i).contrMtrMultRate.setNameForMessage("Multiplier");
            scrnMsg.A.no(i).bllgMtrLbCd.setNameForMessage("Billing Counter");
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0340;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0340.NSAL0340CMsg;
import business.servlet.NSAL0340.common.NSAL0340CommonLogic;
import business.servlet.NSAL0340.constant.NSAL0340Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/09   Fujitsu         T.Yoshida       Create          N/A
 * 2015/10/20   Hitachi         T.Tomita        Update          N/A
 *</pre>
 */
public class NSAL0340_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NSAL0340Constant.BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0340BMsg scrnMsg = (NSAL0340BMsg) bMsg;

        NSAL0340CMsg bizMsg = new NSAL0340CMsg();
        bizMsg.setBusinessID(NSAL0340Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0340Constant.FUNCTION_SEARCH);

        // set Invoker screen value.
        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length > 0) {
                EZDBBigDecimalItem param0 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk, param0.getValue());

                if (params.length > 1) {
                    EZDBStringItem param1 = (EZDBStringItem) params[1];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.svcInvChrgTpCd, param1.getValue());
                }

                // START 2015/10/20 T.Tomita [N/A, ADD]
                if (params.length > 2) {
                    EZDBStringItem param2 = (EZDBStringItem) params[2];
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, param2.getValue());
                }
                // END 2015/10/20 T.Tomita [N/A, ADD]
            }
        }

        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0340BMsg scrnMsg = (NSAL0340BMsg) bMsg;
        NSAL0340CMsg bizMsg = (NSAL0340CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSAL0340CommonLogic.initControlCommonButton(this);

        if (scrnMsg.A.getValidCount() > 0) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.actvFlg.getValue()) && NSAL0340CommonLogic.hasUpdateFuncId()) {
                NSAL0340CommonLogic.initCommonButton(this, true);
            } else {
                NSAL0340CommonLogic.initCommonButton(this, false);
                NSAL0340CommonLogic.inActiveDtlField(scrnMsg);
            }
            NSAL0340CommonLogic.setTblBackColor(scrnMsg);
            scrnMsg.setFocusItem(scrnMsg.A.no(0).skipRecovTpCd_A0);

        } else {
            NSAL0340CommonLogic.initCommonButton(this, false);
        }
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NSAL0340BMsg scrnMsg = (NSAL0340BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).skipRecovTpCd_A0.setNameForMessage(NSAL0340Constant.ITEM_NM_SKIP_TP);
        }
    }
}

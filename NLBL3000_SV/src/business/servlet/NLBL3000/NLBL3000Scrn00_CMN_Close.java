/*
 * <Pre>Copyright(c)2012 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NLBL3000;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3000.NLBL3000CMsg;
import business.servlet.NLBL3000.common.NLBL3000CommonLogic;
import business.servlet.NLBL3000.constant.NLBL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *  Serial Number Entry NLBL3000Scrn00_CMN_Close
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/03/2012   Fujitsu         T.Ishii         Create          N/A
 * 02/20/2013   Fujitsu         F.Saito         Update          WDS Defect#714
 * 11/28/2015   CSAI            Y.Imazu         Update          CSA
 *</pre>
 */
public class NLBL3000Scrn00_CMN_Close extends S21CommonHandler implements NLBL3000Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3000CommonLogic.ezCheck(bMsg);

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;

        for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {

            if (!NLBL3000CommonLogic.isSameVal(scrnMsg.S.no(i).serNum, scrnMsg.S.no(i).serNum_S1)) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.xxWrnSkipFlg_H1, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(scrnMsg.S.no(i).serNum_S1, scrnMsg.S.no(i).serNum.getValue());
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg_H1, ZYPConstant.FLG_OFF_N);
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;
        NLBL3000CMsg bizMsg = null;

        if (SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue())) {

            bizMsg = NLBL3000CommonLogic.setRequestData_NLBL3000Scrn00_Search();
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
        }

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3000BMsg scrnMsg = (NLBL3000BMsg) bMsg;
        NLBL3000CMsg bizMsg = (NLBL3000CMsg) cMsg;

        if (bizMsg != null) {

            // copy data from CMsg onto BMsg
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NLBL3000CommonLogic.setTableBGColor(scrnMsg);
        NLBL3000CommonLogic.initDisplayInfo(this, scrnMsg);
        NLBL3000CommonLogic.ezCheck(scrnMsg);

        if (!SCR_EDT_TP_EDIT.equals(scrnMsg.xxScrEdtTpCd_H1.getValue())) {

            return;
        }

        /* 11/28/2015 CSAI Y.Imazu Add CSA START */
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxErrFlg_H1.getValue())) {

            int index = new Integer(scrnMsg.xxLineNum_H1.getValue()).intValue();
            scrnMsg.setFocusItem(scrnMsg.S.no(index).serNum);
            throw new EZDFieldErrorException();
        }
        /* 11/28/2015 CSAI Y.Imazu Add CSA END */

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            NLBL3000CommonLogic.setOutputParam((Object[]) arg, scrnMsg.S);
        }
    }
}

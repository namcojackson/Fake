/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0560;

import static business.servlet.NSAL0560.common.NSAL0560CommonLogic.*;
import static business.servlet.NSAL0560.constant.NSAL0560Constant.*;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0560.NSAL0560CMsg;
import business.servlet.NSAL0560.common.NSAL0560CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Hitachi         K.Kasai         Create          N/A
 * 2017/08/21   Hitachi         K.Kitachi       Update          QC#20061
 *</pre>
 */
public class NSAL0560_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 1) {
            if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param00 = (EZDBBigDecimalItem) params[0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrDtlPk_H1, param00);
            }

            if (params.length > 1 && params[1] != null && params[1] instanceof EZDBStringItem) {
                EZDBStringItem param01 = (EZDBStringItem) params[1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_H1, param01);
            }

        }

        NSAL0560CMsg bizMsg = new NSAL0560CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        NSAL0560CMsg bizMsg = (NSAL0560CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NSAL0560CommonLogic.copyAMsgToBMsg(scrnMsg);

        initialControlScreen(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0560BMsg scrnMsg = (NSAL0560BMsg) bMsg;
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).contrPrcEffFromDt_A1.setNameForMessage("Start Date");
            scrnMsg.A.no(i).contrPrcEffThruDt_A1.setNameForMessage("End Date");
            scrnMsg.A.no(i).bllgCycleCd_A3.setNameForMessage("Frequency");
            scrnMsg.A.no(i).basePrcDealAmt_A1.setNameForMessage("Price/Period");
            // START 2017/08/21 K.Kitachi [QC#20061, ADD]
            scrnMsg.A.no(i).basePrcTermDealAmtRate_A1.setNameForMessage("Term Amount");
            // END 2017/08/21 K.Kitachi [QC#20061, ADD]
        }
    }
}

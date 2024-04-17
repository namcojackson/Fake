/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLAL1100.NLAL1100CMsg;
import business.servlet.NLAL1100.common.NLAL1100CommonLogic;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CSA             M.Ito           Create          N/A
 * 05/29/2018   CITS            S.Katsuma       Update          QC#25232
 *</pre>
 */
public class NLAL1100_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), NLAL1100Constant.BIZ_APP_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        NLAL1100CMsg bizMsg = new NLAL1100CMsg();

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;

            ZYPEZDItemValueSetter.setValue(scrnMsg.rmaNum_IP, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.soNum_IP, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rwsNum_IP, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_IP, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustCd_IP, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.serNum_IP, (EZDBStringItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_IP, (EZDBStringItem) params[6]);
            // START 2018/05/29 S.Katsuma [QC#25232,MOD]
            if (params.length >= 9 && params[8] != null) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcConfigMstrPk_IP, (EZDBBigDecimalItem) params[8]);
            }
            // END 2018/05/29 S.Katsuma [QC#25232,MOD]
        }

        bizMsg.setBusinessID(NLAL1100Constant.BIZ_APP_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;
        NLAL1100CMsg bizMsg = (NLAL1100CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NLAL1100CommonLogic.initialControlScreen(getUserProfileService(), this, scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.rmaNum_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NLAL1100CommonLogic.setNameForMessage((NLAL1100BMsg) arg0);
    }
}

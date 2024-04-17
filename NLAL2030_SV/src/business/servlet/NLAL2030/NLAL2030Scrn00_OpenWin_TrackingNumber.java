/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL2030;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Tracking Number Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            T.Hakodate      Create          QC#21913
 *</pre>
 */
public class NLAL2030Scrn00_OpenWin_TrackingNumber extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL2030BMsg scrnMsg = (NLAL2030BMsg) bMsg;

        int index = getButtonSelectNumber();
        NLAL2030_BBMsg bizMsgLine = scrnMsg.B.no(index);

        Object[] params = new Object[2];

        String trxHdrNum = null;

        if (ZYPCommonFunc.hasValue(bizMsgLine.rwsRefNum_B1)) {
            if (bizMsgLine.rwsRefNum_B1.getValue().length() > 8) {
                trxHdrNum = bizMsgLine.rwsRefNum_B1.getValue().substring(0, 8);
            } else {
                trxHdrNum = bizMsgLine.rwsRefNum_B1.getValue();
            }
        }

        params[0] = trxHdrNum;
        params[1] = ZYPConstant.FLG_OFF_N;
        setArgForSubScreen(params);
    }
}

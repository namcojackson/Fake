/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 10/17/2016   CITS            K.Ogino         Update          QC#10406
 *</pre>
 */
public class NLBL2020_NLBL3000 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // No Process.

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            int index = getButtonSelectNumber();
            NLBL2020_ABMsg selectRecord = scrnMsg.A.no(index);

            int maxLength = selectRecord.getAttr("serNum_A1").getDigit();
            StringBuilder sb = new StringBuilder();
            boolean setSerVal = false;

            selectRecord.serNum_AH.clear();
            for (int i = 0; i < scrnMsg.S.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(selectRecord.serNum_AH.no(i), scrnMsg.S.no(i).serNum_SN);

                if (ZYPCommonFunc.hasValue(selectRecord.serNum_AH.no(i))) {

                    if (sb.length() > 0) {
                        sb.append(",");
                    }
                    sb.append(selectRecord.serNum_AH.no(i).getValue());

                    if (sb.length() <= maxLength) {
                        ZYPEZDItemValueSetter.setValue(selectRecord.serNum_A1, sb.toString());
                    } else {
                        ZYPEZDItemValueSetter.setValue(selectRecord.serNum_A1, sb.toString().substring(0, maxLength));
                    }
                    setSerVal = true;
                } else {
                    if (!setSerVal) {
                        selectRecord.serNum_A1.clear();
                    }
                }
            }

            scrnMsg.setFocusItem(selectRecord.serNum_A1);
        }
    }
}

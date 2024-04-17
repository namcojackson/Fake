/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NLBL3000
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/18/2016   CITS            Makoto Okigami  Create          N/A
 * 04/06/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0020_NLBL3000 extends S21CommonHandler {

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

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;

        int eventRowIndex = getButtonSelectNumber();

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < scrnMsg.T.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.T.no(i).serNum_T1)) {
                if (sb.length() > 0) {
                    sb.append(SCREEN_CTRL_VALUE_SERIAL_NUM_DELIM);
                }
                sb.append(scrnMsg.T.no(i).serNum_T1.getValue());
            }
        }
        if (sb.length() != 0) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1, sb.toString());
        } else {
            scrnMsg.A.no(eventRowIndex).xxLogDtlTxt_A1.clear();
        }

    }
}

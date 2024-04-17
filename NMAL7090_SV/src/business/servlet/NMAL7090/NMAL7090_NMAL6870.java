/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID  : NMAL7090 Item Supersessions Mass Add
 * Function Name : price list Popup(NMAL6870)
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   CITS            S.Tanikawa      Create          N/A
 *</pre>
 */

public class NMAL7090_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7090BMsg scrnMsg = (NMAL7090BMsg) bMsg;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scrnMsg.P.getValidCount(); i++) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(scrnMsg.P.no(i).xxComnScrColValTxt_0.getValue());
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(scrnMsg.xxNum_SL.getValueInt()).prcListsDescTxt_B, sb.toString());
    }
}

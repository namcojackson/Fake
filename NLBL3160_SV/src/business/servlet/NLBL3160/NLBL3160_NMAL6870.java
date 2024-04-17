/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3160;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3160.constant.NLBL3160Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Distribution Coordinator Work Bench
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/13   CITS            S.Katsuma       Create          N/A
 *</pre>
 */
public class NLBL3160_NMAL6870 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3160BMsg scrnMsg = (NLBL3160BMsg) bMsg;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
            if (i != 0) {
                sb.append(NLBL3160Constant.XX_SPL_CHAR_TXT);
            }
            sb.append(scrnMsg.Z.no(i).xxComnScrColValTxt_1.getValue());
        }

        String srchtxt = sb.toString();
        if (NLBL3160Constant.SRCH_TXT_MAX < sb.length()) {
            scrnMsg.setMessageInfo("NWAM0860W", new String[] {scrnMsg.dsOrdCatgDescTxt.getNameForMessage()});
            srchtxt = sb.substring(0, NLBL3160Constant.SRCH_TXT_MAX);
        }
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdCatgDescTxt, srchtxt);
        scrnMsg.setFocusItem(scrnMsg.dsOrdCatgDescTxt);
    }
}

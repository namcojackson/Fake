/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1420;

import static business.servlet.NPAL1420.constant.NPAL1420Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1420 Reman Task Entry
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 5/17/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1420_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            NPAL1420BMsg scrnMsg = (NPAL1420BMsg) bMsg;
            for (int i = 0; i < scrnMsg.R.length(); i++) {
                if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("TECH_TOC_CD")) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, scrnMsg.R.no(i).xxComnScrColValTxt);
                } else if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("TECH_NM")) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_L, scrnMsg.R.no(i).xxComnScrColValTxt);
                }
            }
            scrnMsg.setFocusItem(scrnMsg.techTocCd);
        }
    }
}

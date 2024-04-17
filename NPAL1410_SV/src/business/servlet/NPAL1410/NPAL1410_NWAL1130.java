/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NPAL1410;

import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_LOC_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NAME_TECH_POPUP;
import static business.servlet.NPAL1410.constant.NPAL1410Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1410 Reman Workbench
 * Function Name : 
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 4/05/2016   CITS       Yasushi Nomura       Create          N/A
 *</pre>
 */
public class NPAL1410_NWAL1130 extends S21CommonHandler {

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
            NPAL1410BMsg scrnMsg = (NPAL1410BMsg) bMsg;
            if (EVENT_NAME_TECH_POPUP.equals(scrnMsg.eventNm.getValue())) {
                for (int i = 0; i < scrnMsg.R.length(); i++) {
                    if ("TECH_TOC_CD".equals(scrnMsg.R.no(i).xxComnScrQueryColNm.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.ownrTechTocCd_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    } else if ("TECH_NM".equals(scrnMsg.R.no(i).xxComnScrQueryColNm.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    }
                }
                scrnMsg.setFocusItem(scrnMsg.ownrTechTocCd_H1);
            } else if (EVENT_NAME_LOC_POPUP.equals(scrnMsg.eventNm.getValue())) {
                for (int i = 0; i < scrnMsg.R.length(); i++) {
                    if ("WH_LOCTR_CD".equals(scrnMsg.R.no(i).xxComnScrQueryColNm.getValue())) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.whLoctrCd_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    }
                }
                scrnMsg.setFocusItem(scrnMsg.whLoctrCd_H1);
            }
        }
    }
}

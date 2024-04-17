/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0600;

import static business.servlet.NLCL0600.constant.NLCL0600Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WINDOW_TITLE_SUB_WH_SEARCH;
import static business.servlet.NLCL0600.constant.NLCL0600Constant.WINDOW_TITLE_WH_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0600 PI Entry
 * Function Name : Return Action from NWAL1130
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   CITS            Makoto Okigami  Create          N/A
 *</pre>
 */
public class NLCL0600_NWAL1130 extends S21CommonHandler {

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

        NLCL0600BMsg scrnMsg = (NLCL0600BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (WINDOW_TITLE_WH_SEARCH.equals(scrnMsg.xxScrNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);

            } else if (WINDOW_TITLE_SUB_WH_SEARCH.equals(scrnMsg.xxScrNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhNm, scrnMsg.R.no(1).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.invtyLocCd, scrnMsg.R.no(2).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.rtlSwhCd);
            }

        }

    }
}

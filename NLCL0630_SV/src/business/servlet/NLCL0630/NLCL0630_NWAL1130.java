/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0630;

import static business.servlet.NLCL0630.constant.NLCL0630Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_TECHNICIAN_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_TECH_PHYSICAL_SEARCH;
import static business.servlet.NLCL0630.constant.NLCL0630Constant.WINDOW_TITLE_LOCATION_SEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0630 Tech PI Inquiry
 * Function Name :  Return Action from NWAL1130
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/29/2016   CITS            Makoto Okigami  Create          N/A
 * 12/11/2018   Fujitsu         T.Ogura         Update          QC#28755
 *</pre>
 */
public class NLCL0630_NWAL1130 extends S21CommonHandler {

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

        NLCL0630BMsg scrnMsg = (NLCL0630BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (WINDOW_TITLE_TECHNICIAN_SEARCH.equals(scrnMsg.xxScrNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.techNm, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.techTocCd);

            } else if (WINDOW_TITLE_TECH_PHYSICAL_SEARCH.equals(scrnMsg.xxScrNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.physInvtyCtrlNm, scrnMsg.R.no(0).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.physInvtyCtrlNm);

            // START 2018/12/11 T.Ogura [QC#28755,ADD]
            } else if (WINDOW_TITLE_LOCATION_SEARCH.equals(scrnMsg.xxScrNm.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.R.no(0).xxComnScrColValTxt);
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.R.no(1).xxComnScrColValTxt);

                // Set Focus
                scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
            // END   2018/12/11 T.Ogura [QC#28755,ADD]
            }
        }

    }
}

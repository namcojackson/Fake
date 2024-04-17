/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLCL0620;

import static business.servlet.NLCL0620.constant.NLCL0620Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * <pre>
 * Business ID : NLCL0620 Tech PI Entry
 * Function Name : Return Action from NWAL1130
 * </pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/08/2016   CITS            Makoto Okigami  Create          N/A
 * 12/11/2018   Fujitsu         S.Ohki          Update          QC#28755
 *</pre>
 */
public class NLCL0620_NWAL1130 extends S21CommonHandler {

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

        NLCL0620BMsg scrnMsg = (NLCL0620BMsg) bMsg;

        // START 2018/12/11 S.Ohki [QC#28755,MOD]
//        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
//
//            ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, scrnMsg.R.no(0).xxComnScrColValTxt);
//            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm, scrnMsg.R.no(1).xxComnScrColValTxt);
//
//            // Set Focus
//            scrnMsg.setFocusItem(scrnMsg.techTocCd);
//        }

        if (EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
        	return;
        }

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if ("OpenWin_Technician".equals(scrEventNm)) {
        	ZYPEZDItemValueSetter.setValue(scrnMsg.techTocCd, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.techNm, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.rtlWhCd.clear();
            scrnMsg.rtlWhNm.clear();
            scrnMsg.setFocusItem(scrnMsg.techTocCd);
        } else {
        	ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd, scrnMsg.R.no(0).xxComnScrColValTxt);
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm, scrnMsg.R.no(1).xxComnScrColValTxt);
            scrnMsg.setFocusItem(scrnMsg.rtlWhCd);
        }
        // END 2018/12/11 S.Ohki [QC#28755,MOD]
    }
}

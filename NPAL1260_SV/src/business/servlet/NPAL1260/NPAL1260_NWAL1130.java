/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1260;

import static business.servlet.NPAL1260.constant.NPAL1260Constant.EVENT_NM_CMN_CLOSE;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPAL1260 Tech Parts Request Inquiry
 * Function Name : Return Action from NWAL1130
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/02/2015   CITS       Yasushi Nomura       Create          N/A
 * 2019/01/10   Fujitsu         S.Takami        Update          QC#29890
 *</pre>
 */
public class NPAL1260_NWAL1130 extends S21CommonHandler {

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
        NPAL1260BMsg scrnMsg = (NPAL1260BMsg) bMsg;
        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {
            if ("OpenWin_SupplierAndSite".equals(scrnMsg.eventNm.getValue())) {
                for (int i = 0; i < scrnMsg.R.length(); i++) {
                    if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("PRNT_VND_NM")) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndNm_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    } else if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("LOC_NM")) {
                        ZYPEZDItemValueSetter.setValue(scrnMsg.locNm_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    }
                }
            } else if ("OpenWin_Tech".equals(scrnMsg.eventNm.getValue())) {
                for (int i = 0; i < scrnMsg.R.length(); i++) {
                    // 2019/01/10 QC#29890 Mod Start
//                    if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("FULL_PSN_NM")) {
                    if (scrnMsg.R.no(i).xxComnScrQueryColNm.getValue().equals("TECH_NM")) {
                        // 2019/01/10 QC#29890 Mod End
                        ZYPEZDItemValueSetter.setValue(scrnMsg.techNm_H1, scrnMsg.R.no(i).xxComnScrColValTxt);
                    }
                }
            }
        }
    }
}

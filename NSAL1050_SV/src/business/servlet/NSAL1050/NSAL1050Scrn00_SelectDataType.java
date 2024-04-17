/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1050;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TERM_ATTRB_DATA_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/11   Hitachi         T.Mizuki        Create          N/A
 * 2016/06/02   Hitachi         T.Tomita        Update          QC#5489
 * 2018/06/25   Hitachi         A.Kohinata      Update          QC#17369
 *</pre>
 */
public class NSAL1050Scrn00_SelectDataType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1050BMsg scrnMsg = (NSAL1050BMsg) bMsg;
        // START 2016/06/02 T.Tomita [QC#5489, MOD]
//        NSAL1050CommonLogic.controlLovDdfList(scrnMsg, this);
        int idx = getButtonSelectNumber();
        scrnMsg.A.no(idx).svcTermCondSrcDescTxt_A.clear();
        scrnMsg.A.no(idx).svcTermCondDataSrcCd_A.clear();
        String svcTermDataTpCd = scrnMsg.A.no(idx).svcTermDataTpCd_1V.getValue();
        // mod start 2018/06/25 QC#17369
        if (!SVC_TERM_ATTRB_DATA_TP.DROPDOWN.equals(svcTermDataTpCd) && !SVC_TERM_ATTRB_DATA_TP.LOOKUP.equals(svcTermDataTpCd) && !SVC_TERM_ATTRB_DATA_TP.LOOKUP_POPUP.equals(svcTermDataTpCd)) {
        // mod end 2018/06/25 QC#17369
            setButtonEnabled("OpenWin_TandC_Source", idx, false);
        } else {
            setButtonEnabled("OpenWin_TandC_Source", idx, true);
        }
        // END 2016/06/02 T.Tomita [QC#5489, MOD]
    }
}

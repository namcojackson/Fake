/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7260;

import static business.servlet.NMAL7260.constant.NMAL7260Constant.BIZ_ID;
import static business.servlet.NMAL7260.constant.NMAL7260Constant.OPENWIN_ITEMSEARCH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import business.blap.NMAL7260.NMAL7260CMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7260_NMAL6800
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/08   Fujitsu         H.Ikeda         Create          N/A
 * 2018/06/15   Fujitsu         M.Ishii         Update          QC#22594
 * 2018/07/18   Fujitsu         W.Honda         Update          QC#20267
 *</pre>
 */
public class NMAL7260_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;

        NMAL7260CMsg bizMsg = new NMAL7260CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // 2018/07/18 QC#20267 Mod Start
        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        NMAL7260CMsg bizMsg = (NMAL7260CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int idx = scrnMsg.xxCellIdx.getValueInt();
        if ("CMN_Close".equals(getLastGuard())) {
            if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.srchOrigItemFlg_MF.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).prcRuleCondFromTxt_10, scrnMsg.B.no(idx).mnfItemCd_10);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(idx).mnfItemCd_10, scrnMsg.P.no(20).xxPopPrm);
            }
        }
        scrnMsg.srchOrigItemFlg_MF.clear();

        // 2018/06/15 QC#22594 Add Start
//        NMAL7260BMsg scrnMsg = (NMAL7260BMsg) bMsg;
        String scrEvntNm = scrnMsg.xxScrEventNm.getValue();
//        int idx = scrnMsg.xxCellIdx.getValueInt();
        if (OPENWIN_ITEMSEARCH.equals(scrEvntNm)) {
            scrnMsg.setFocusItem(scrnMsg.B.no(idx).prcRuleCondFromTxt_10);
        }
        // 2018/06/15 QC#22594 Add End
        // 2018/07/18 QC#20267 Mod End
    }
}

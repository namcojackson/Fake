/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0090;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFDL0090.NFDL0090CMsg;
import business.servlet.NFDL0090.common.NFDL0090CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * Write Off
 *
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/12    Fujitsu         M.Nakamura      Create          N/A
 * 2018/07/17    Hitachi         Y.Takeno        Update          QC#26989
 * 2018/07/19    Hitachi         Y.Takeno        Update          QC#26989
 * 2018/09/11    Hitachi         Y.Takeno        Update          QC#24884
 *</pre>
 */
public class NFDL0090Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/07/19 [QC#26989, MOD]
        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        NFDL0090CMsg bizMsg = new NFDL0090CMsg();
        bizMsg.setBusinessID("NFDL0090");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END   2018/07/19 [QC#26989, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0090BMsg scrnMsg = (NFDL0090BMsg) bMsg;
        // START 2018/07/19 [QC#26989, ADD]
        NFDL0090CMsg bizMsg = (NFDL0090CMsg) cMsg;
        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }
        // END   2018/07/19 [QC#26989, ADD]

        // START 2018/07/19 [QC#26989, DEL]
        // ZYPTableUtil.unSelectAll(scrnMsg.A, "xxChkBox_A1");
        // scrnMsg.arWrtOffNoteTxt_FS.clear();
        // END   2018/07/19 [QC#26989, DEL]

        // START 2018/07/17 [QC#26989, DEL]
        // for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
        //     if (AR_TRX_TP.INVOICE.equals(scrnMsg.A.no(i).arTrxTpCd_A1.getValue())) {
        //         scrnMsg.A.no(i).xxDealApplyAmtNum_A1.clear();
        //     }
        // }
        // END  2018/07/17 [QC#26989, DEL]

        NFDL0090CommonLogic.initCmnBtnProp(this, scrnMsg);
        // START 2018/09/11 [QC#24884, ADD]
        NFDL0090CommonLogic.protectHeader(scrnMsg);
        // END   2018/09/11 [QC#24884, ADD]
        // START 2018/07/19 [QC#26989, ADD]
        NFDL0090CommonLogic.protectDetail(scrnMsg);
        // END   2018/07/19 [QC#26989, ADD]

        if (scrnMsg.A.getValidCount() > 0) {
            scrnMsg.setFocusItem(scrnMsg.A.no(0).xxDealApplyAmtNum_A1);
        }
    }
}

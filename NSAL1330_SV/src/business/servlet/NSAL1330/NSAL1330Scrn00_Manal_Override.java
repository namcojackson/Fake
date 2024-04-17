/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL1330.common.NSAL1330CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 * 2018/05/07   Fujitsu         A.Kosai         Update          QC#22482
 *</pre>
 */
public class NSAL1330Scrn00_Manal_Override extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

//        if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.manContrOvrdFlg.getValue())) {
//            for (int i = 0; i < scrnMsg.Z.getValidCount(); i++) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).contrMtrMultRate_Z, scrnMsg.Z.no(i).contrMtrMultRate_BK);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).minCopyVolCnt_Z, scrnMsg.Z.no(i).minCopyVolCnt_BK);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).maxCopyVolCnt_Z, scrnMsg.Z.no(i).maxCopyVolCnt_BK);
//                ZYPEZDItemValueSetter.setValue(scrnMsg.Z.no(i).xsMtrAmtRate_Z, scrnMsg.Z.no(i).xsMtrAmtRate_BK);
//            }
//            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).dealPrcListPrcAmt_B, scrnMsg.B.no(i).addlBasePrcDealAmt_BB);
//            }
//            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
//                ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(i).dealPrcListPrcAmt_C, scrnMsg.C.no(i).addlChrgPrcDealAmt_CB);
//            }
//        }
        NSAL1330CommonLogic.overrideProtected(this, scrnMsg);
        // 2018/05/07 QC#22482 Del Start
//        NSAL1330CommonLogic.setScrnManOvrdDspCtrl(scrnMsg);
        // 2018/05/07 QC#22482 Del End
    }
}

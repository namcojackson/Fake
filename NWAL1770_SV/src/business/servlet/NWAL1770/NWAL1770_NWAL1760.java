/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1770;

import static business.servlet.NWAL1770.constant.NWAL1770Constant.BIZ_ID;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.FUNC_CD_SRCH;
import static business.servlet.NWAL1770.constant.NWAL1770Constant.NO_SLCT_DTL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL1770.NWAL1770CMsg;
import business.servlet.NWAL1770.common.NWAL1770CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2023/07/20   Hitachi         T.Fukuta        Update          CSA-QC#61467
 *</pre>
 */
public class NWAL1770_NWAL1760 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
//        return null;
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = new NWAL1770CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]
        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        NWAL1770CMsg bizMsg = (NWAL1770CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NWAL1770CommonLogic.checkItemLineWarning(scrnMsg);
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,ADD]

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        // START 2023/07/20 T.Fukuta [CSA-QC#61467,DEL]
//        NWAL1770BMsg scrnMsg = (NWAL1770BMsg) bMsg;
        // END 2023/07/20 T.Fukuta [CSA-QC#61467,DEL]
        int slctLine = scrnMsg.xxCellIdx.getValueInt();

        if (NO_SLCT_DTL == slctLine) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgCd, scrnMsg.xxPopPrm_01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm, scrnMsg.xxPopPrm_02);
            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).prcCatgCd_B, scrnMsg.xxPopPrm_01);
                ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(i).prcCatgNm_B, scrnMsg.xxPopPrm_02);
            }
            scrnMsg.setFocusItem(scrnMsg.prcCatgNm);

        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).prcCatgNm_B, scrnMsg.xxPopPrm_01);
            ZYPEZDItemValueSetter.setValue(scrnMsg.B.no(slctLine).prcCatgNm_B, scrnMsg.xxPopPrm_02);
            scrnMsg.setFocusItem(scrnMsg.B.no(slctLine).prcCatgNm_B);
        }
    }
}
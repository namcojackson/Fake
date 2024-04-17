/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2040;

import static business.servlet.NFBL2040.constant.NFBL2040Constant.BIZ_ID;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.FUNC_CD_20;
import static business.servlet.NFBL2040.constant.NFBL2040Constant.MESSAGE_KIND_E;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NFBL2040.NFBL2040CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AP_LINE_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/13   Hitachi         Y.Takeno        Create          QC#24274
 * 2018/04/04   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/07/17   CITS            S.Katsuma       Update          QC#27078
 * 2018/08/16   Hitachi         Y.Takeno        Update          QC#27247
 * 2018/08/17   Hitachi         Y.Takeno        Update          QC#27247
 *</pre>
 */

public class NFBL2040Scrn00_OnChange_InvAmt extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        int idx = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1)
                || AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            return;
        }
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxInvAmt_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1)
                || AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            return null;
        }
        NFBL2040CMsg bizMsg = new NFBL2040CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        NFBL2040CMsg bizMsg  = (NFBL2040CMsg) cMsg;
        int idx = getButtonSelectNumber();

        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(idx).apLineTpCd_A1)
                || AP_LINE_TP.ITEM.equals(scrnMsg.A.no(idx).apLineTpCd_A1.getValue())) {
            // START 2018/08/17 [QC#27247, DEL]
            // START 2018/08/16 [QC#27247, ADD]
            // scrnMsg.setFocusItem(scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1);
            // END   2018/08/16 [QC#27247, ADD]
            // END   2018/08/17 [QC#27247, DEL]
            return;
        }
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).xxInvAmt_A1);
        scrnMsg.putErrorScreen();
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(idx).xxInvAmt_A1, bizMsg.A.no(idx).xxInvAmt_A1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotPrcAmt_F, bizMsg.xxTotPrcAmt_F);
        // START 2018/08/16 [QC#27247, DEL]
        // START 2018/07/17 S.Katsuma [QC#27078,MOD]
        // START 2018/04/04 [QC#20316, DEL]
        // scrnMsg.setFocusItem(scrnMsg.A.no(idx).vndUomCd_A1);
        // END   2018/04/04 [QC#20316, DEL]
        // END 2018/07/17 S.Katsuma [QC#27078,MOD]
        // END   2018/08/16 [QC#27247, DEL]
    }
}

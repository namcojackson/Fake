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

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/03/13   Hitachi         Y.Takeno        Create          QC#24274
 * 2018/04/04   Hitachi         Y.Takeno        Update          QC#20316
 * 2018/07/17   CITS            S.Katsuma       Update          QC#27078
 * 2018/08/16   Hitachi         Y.Takeno        Update          QC#27247
 *</pre>
 */
public class NFBL2040Scrn00_OnChange_BillQty extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        int idx = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).apBillQty_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NFBL2040BMsg scrnMsg = (NFBL2040BMsg) bMsg;
        scrnMsg.xxCellIdx.setValue(getButtonSelectNumber());
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

        scrnMsg.addCheckItem(scrnMsg.A.no(idx).dealGrsUnitPrcAmt_A1);
        scrnMsg.addCheckItem(scrnMsg.A.no(idx).apBillQty_A1);
        scrnMsg.putErrorScreen();
        if (MESSAGE_KIND_E.equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }

        EZDMsg.copy(bizMsg.A.no(idx), null, scrnMsg.A.no(idx), null);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTotPrcAmt_F, bizMsg.xxTotPrcAmt_F);
        // START 2018/08/16 [QC#27247, DEL]
        // START 2018/07/17 S.Katsuma [QC#27078,MOD]
        // START 2018/04/04 [QC#20316, DEL]
        // scrnMsg.setFocusItem(scrnMsg.A.no(idx).xxInvQty_A1);
        // END   2018/04/04 [QC#20316, DEL]
        // END 2018/07/17 S.Katsuma [QC#27078,MOD]
        // END 2018/08/16 [QC#27247, DEL]
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPBL0020;

import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_1;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_2;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_3;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_4;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_5;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_6;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_7;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_8;
import static business.servlet.NPBL0020.constant.NPBL0020Constant.IDX_9;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NPBL0020 Inventory Request Entry
 * Function Name : Return Action from NMAL2550
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/18/2016   CITS            Makoto Okigami  Create          N/A
 * 04/08/2016   CITS            K.Ogino         Update          N/A
 *</pre>
 */
public class NPBL0020_NMAL2550 extends S21CommonHandler {

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

        NPBL0020BMsg scrnMsg = (NPBL0020BMsg) bMsg;
        int eventRowIndex = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCmpyCd_A1, scrnMsg.P.no(IDX_1).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAfflCd_A1, scrnMsg.P.no(IDX_2).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaBrCd_A1, scrnMsg.P.no(IDX_3).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaCcCd_A1, scrnMsg.P.no(IDX_4).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaAcctCd_A1, scrnMsg.P.no(IDX_5).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProdCd_A2, scrnMsg.P.no(IDX_6).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaChCd_A1, scrnMsg.P.no(IDX_7).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaProjCd_A1, scrnMsg.P.no(IDX_8).xxPopPrm);
        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).coaExtnCd_A1, scrnMsg.P.no(IDX_9).xxPopPrm);

        StringBuilder chargeAccount = new StringBuilder();
        chargeAccount.append(scrnMsg.P.no(1).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(3).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(4).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(5).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(6).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(7).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(2).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(8).xxPopPrm.getValue());
        chargeAccount.append(".");
        chargeAccount.append(scrnMsg.P.no(9).xxPopPrm.getValue());

        ZYPEZDItemValueSetter.setValue(scrnMsg.A.no(eventRowIndex).xxScrItem50Txt_A1, chargeAccount.toString());
    }
}

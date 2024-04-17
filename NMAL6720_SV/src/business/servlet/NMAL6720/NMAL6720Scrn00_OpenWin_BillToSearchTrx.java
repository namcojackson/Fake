/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6720;

import static business.servlet.NMAL6720.constant.NMAL6720Constant.EVENT_BILL_TO_SEARCH_TRX;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL6720.common.NMAL6720CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/04/13   SRAA            Y.Chen          Update          QC#6011
 *</pre>
 */
public class NMAL6720Scrn00_OpenWin_BillToSearchTrx extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

         NMAL6720BMsg scrnMsg = (NMAL6720BMsg) bMsg;

         scrnMsg.P.clear();

//         List<EZDBStringItem> params = new ArrayList<EZDBStringItem>();

         scrnMsg.xxScrEventNm_P.setValue(EVENT_BILL_TO_SEARCH_TRX);

// QC#6011
//         scrnMsg.P.no(0).xxPopPrm_P.setValue("99");
//
//         int index = getButtonSelectNumber();
//         if (0 <= index) {
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(1).xxPopPrm_P, scrnMsg.D.no(index).dsDefBillToCd_D1.getValue());
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(4).xxPopPrm_P, scrnMsg.dsAcctNum_P1);
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(20).xxPopPrm_P, ZYPConstant.FLG_ON_Y);
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(21).xxPopPrm_P, ZYPConstant.FLG_ON_Y);
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(22).xxPopPrm_P, ZYPConstant.FLG_OFF_N);
//             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(23).xxPopPrm_P, ZYPConstant.FLG_ON_Y);
//         }
//
//         params.add(scrnMsg.P.no(0).xxPopPrm_P);
//         params.add(scrnMsg.P.no(1).xxPopPrm_P);
//         params.add(scrnMsg.P.no(2).xxPopPrm_P);
//         params.add(scrnMsg.P.no(3).xxPopPrm_P);
//         params.add(scrnMsg.P.no(4).xxPopPrm_P);
//         params.add(scrnMsg.P.no(5).xxPopPrm_P);
//         params.add(scrnMsg.P.no(6).xxPopPrm_P);
//         params.add(scrnMsg.P.no(7).xxPopPrm_P);
//         params.add(scrnMsg.P.no(8).xxPopPrm_P);
//         params.add(scrnMsg.P.no(9).xxPopPrm_P);
//         params.add(scrnMsg.P.no(10).xxPopPrm_P);
//         params.add(scrnMsg.P.no(11).xxPopPrm_P);
//         params.add(scrnMsg.P.no(12).xxPopPrm_P);
//         params.add(scrnMsg.P.no(13).xxPopPrm_P);
//         params.add(scrnMsg.P.no(14).xxPopPrm_P);
//         params.add(scrnMsg.P.no(15).xxPopPrm_P);
//         params.add(scrnMsg.P.no(16).xxPopPrm_P);
//         params.add(scrnMsg.P.no(17).xxPopPrm_P);
//         params.add(scrnMsg.P.no(18).xxPopPrm_P);
//         params.add(scrnMsg.P.no(19).xxPopPrm_P);
//         params.add(scrnMsg.P.no(20).xxPopPrm_P);
//         params.add(scrnMsg.P.no(21).xxPopPrm_P);
//         params.add(scrnMsg.P.no(22).xxPopPrm_P);
//         params.add(scrnMsg.P.no(23).xxPopPrm_P);
//
//         setArgForSubScreen(params.toArray(new EZDBStringItem[0]));
         scrnMsg.dsAcctNm_P1.clear();

         scrnMsg.P.no(0).xxPopPrm_P.setValue(scrnMsg.dsAcctNum_H1.getValue());
         int index = getButtonSelectNumber();
         if (0 <= index) {
             ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(15).xxPopPrm_P, scrnMsg.D.no(index).dsDefBillToCd_D1.getValue());
         }
         scrnMsg.P.no(12).xxPopPrm_P.setValue("02"); // Bill To Only
         scrnMsg.P.no(24).xxPopPrm_P.setValue(ZYPConstant.FLG_OFF_N);
         scrnMsg.P.no(25).xxPopPrm_P.setValue(ZYPConstant.FLG_OFF_N);
         scrnMsg.P.no(33).xxPopPrm_P.setValue(ZYPConstant.FLG_ON_Y); // Category: Active

         Object[] params = NMAL6720CommonLogic.toArray_popup(scrnMsg.P, 35);
         params[1] = scrnMsg.dsAcctNm_P1;

         setArgForSubScreen(params);
    }
}

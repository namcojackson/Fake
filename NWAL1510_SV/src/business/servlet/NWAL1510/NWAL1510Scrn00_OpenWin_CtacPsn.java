/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_1;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_15;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_16;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_17;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_19;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_4;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_31;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_6;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.POP_PAR_7;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_CUST_REF_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_OpenWin_CtacPsn
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         S.Ohki          Create          N/A
 * 2016/02/15   Fujitsu         S.Ohki          Update          S21_NA#3257
 * 2016/03/02   Fujitsu         S.Ohki          Update          S21_NA#3257
 * 2016/03/28   Fujitsu         S.Takami        Update          S21_NA#4594
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/03/09   Fujitsu         A.Kosai         Update          S21_NA#22387
 *</pre>
 */
public class NWAL1510Scrn00_OpenWin_CtacPsn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        int idx = getButtonSelectNumber();

        ZYPTableUtil.clear(scrnMsg.P);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_1).xxPopPrm, scrnMsg.C.no(idx).ctacPsnTpCd_C0); // QC#16452 add
        //QC#16452 del Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_3).xxPopPrm, scrnMsg.shipToCustAcctCd_H0.getValue());
//        // START 2016/02/15 S.Ohki [Defect#3257 Mod]
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.locNum_H0.getValue()); // 2016/03/28 S21_NA#4594 come back
        //QC#16452 del End
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.shipToCustLocCd_H0.getValue()); 2016/03/28 S21_NA#4594 Del
        // END 2016/02/15 S.Ohki [Defect#3257 Mod]
        // 2018/03/09 S21_NA#22387 Add Start
        if (CTAC_CUST_REF_TP.SOLD_TO.equals(scrnMsg.C.no(idx).ctacCustRefTpCd_C0.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.sellToLocNum_H0);
        } else if (CTAC_CUST_REF_TP.BILL_TO.equals(scrnMsg.C.no(idx).ctacCustRefTpCd_C0.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.billToLocNum_H0);
        } else if (CTAC_CUST_REF_TP.SHIP_TO.equals(scrnMsg.C.no(idx).ctacCustRefTpCd_C0.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_4).xxPopPrm, scrnMsg.shipToLocNum_H0);
        }
        // 2018/03/09 S21_NA#22387 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_6).xxPopPrm, scrnMsg.C.no(idx).ctacPsnFirstNm_C0);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_7).xxPopPrm, scrnMsg.C.no(idx).ctacPsnLastNm_C0);
        // QC#16452 del Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_21).xxPopPrm, ZYPConstant.FLG_ON_Y); // 2016/03/02 [Defect#3257 Add]
        // QC#16452 del End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_31).xxPopPrm, ZYPConstant.FLG_ON_Y); // Disable Include Location QC#16452 add

        // QC#16452 mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(POP_PAR_25).xxPopPrm, String.valueOf(idx));
        scrnMsg.xxCellIdx_CO.setValue(idx);
        // QC#16452 mod End


        // QC#16452 mod Start
//        Object[] params = new Object[POP_PAR_22];
        Object[] params = new Object[POP_PAR_31 + 1];
        // QC#16452 mod End

        for (int i = 0; i < POP_PAR_15; i++) {
            params[i] = scrnMsg.P.no(i).xxPopPrm;
        }

        params[POP_PAR_15] = scrnMsg.P.no(POP_PAR_15).dsCtacPntPk;
        params[POP_PAR_16] = scrnMsg.P.no(POP_PAR_16).dsCtacPntPk;
        params[POP_PAR_17] = scrnMsg.P.no(POP_PAR_17).dsCtacPntPk;
        // QC#16452 mod Start
//        params[POP_PAR_19] = scrnMsg.P.no(POP_PAR_19).dsCtacPntPk;
        params[POP_PAR_19] = scrnMsg.C.no(idx).ctacPsnPk_C0;
        // QC#16452 mod End
        // QC#16452 del Start
//        params[POP_PAR_21] = scrnMsg.P.no(POP_PAR_21).xxPopPrm; // 2016/03/02 [Defect#3257 Add]
        // QC#16452 del End
        params[POP_PAR_31] = scrnMsg.P.no(POP_PAR_31).xxPopPrm; // QC#16452 add

        setArgForSubScreen(params);
    }
}

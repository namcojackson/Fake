/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL2800.constant.NMAL2800Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800_NMAL2810
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/10/03   Hitachi         T.Mizuki        Update          QC#12418 
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 *</pre>
 */
public class NMAL2800_NMAL2810 extends S21CommonHandler {

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

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);

        if (ZYPCommonFunc.hasValue(aBMsg.prosRvwStsCd_AB)) {
            return;
        }

        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFirstLineAddr_A1, scrnMsg.firstLineAddr_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocScdLineAddr_A1, scrnMsg.scdLineAddr_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocThirdLineAddr_A1, scrnMsg.thirdLineAddr_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFrthLineAddr_A1, scrnMsg.frthLineAddr_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocCtyAddr_A1, scrnMsg.ctyAddr_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocStCd_A1, scrnMsg.stCd_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftLocPostCd_A1, scrnMsg.postCd_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftTelNum_A1, scrnMsg.telNum_R1.getValue());
        // mod start 2016/10/04 CSA QC#12418
        ZYPEZDItemValueSetter.setValue(aBMsg.aftFaxNum_A1, scrnMsg.faxNum_R1.getValue());
        // mod end 2016/10/04 CSA QC#12418
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsAcctDunsNm_A1, scrnMsg.dsAcctDunsNm_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDunsNum_A1, scrnMsg.dunsNum_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsUltDunsNum_A1, scrnMsg.dsUltDunsNum_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCustSicCd_A1, scrnMsg.dsCustSicCd_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCustSicDescTxt_A1, scrnMsg.dsCustSicDescTxt_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsLocRevAmt_A1, scrnMsg.dsLocRevAmt_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsLocEmpNum_A1, scrnMsg.dsLocEmpNum_R1.getValue());
        // Del Start 2018/03/28 QC#23145
//        ZYPEZDItemValueSetter.setValue(aBMsg.aftGlnNum_A1, scrnMsg.glnNum_R1.getValue());
        // Del End 2018/03/28 QC#23145
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsPrntDunsNum_A1, scrnMsg.dsPrntDunsNum_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftHqDunsNum_A1, scrnMsg.hqDunsNum_R1.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCmpySicCd_A1, scrnMsg.dsCustSicCd_R2.getValue());
        ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCmpySicDescTxt_A1, scrnMsg.dsCustSicDescTxt_R2.getValue());

        if ((ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_1) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_1.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_2) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_2.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_3) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_3.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_4) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_4.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_5) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_5.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_6) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_6.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_7) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_7.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_8) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_8.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_10) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_10.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_11) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_11.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_12) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_12.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_13) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_13.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_14) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_14.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_15) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_15.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_16) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_16.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_17) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_17.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_18) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_18.getValue()) == 0)) {
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFirstLineAddr_AH, scrnMsg.firstLineAddr_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocScdLineAddr_AH, scrnMsg.scdLineAddr_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocThirdLineAddr_AH, scrnMsg.thirdLineAddr_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocFrthLineAddr_AH, scrnMsg.frthLineAddr_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocCtyAddr_AH, scrnMsg.ctyAddr_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocStCd_AH, scrnMsg.stCd_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftLocPostCd_AH, scrnMsg.postCd_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftTelNum_AH, scrnMsg.telNum_R1.getValue());
            // mod start 2016/10/04 CSA QC#12418
            ZYPEZDItemValueSetter.setValue(aBMsg.aftFaxNum_AH, scrnMsg.faxNum_R1.getValue());
            // mod end 2016/10/04 CSA QC#12418
            // Del Start 2018/03/28 QC#23145
//            ZYPEZDItemValueSetter.setValue(aBMsg.aftGlnNum_AH, scrnMsg.glnNum_R1.getValue());
            // Del End 2018/03/28 QC#23145
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDunsNum_AH, scrnMsg.dunsNum_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsUltDunsNum_AH, scrnMsg.dsUltDunsNum_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCustSicCd_AH, scrnMsg.dsCustSicCd_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCustSicDescTxt_AH, scrnMsg.dsCustSicDescTxt_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsLocRevAmt_AH, scrnMsg.dsLocRevAmt_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsLocEmpNum_AH, scrnMsg.dsLocEmpNum_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsPrntDunsNum_AH, scrnMsg.dsPrntDunsNum_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftHqDunsNum_AH, scrnMsg.hqDunsNum_R1.getValue());
        }
        if ((ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_9) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_9.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_19) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_19.getValue()) == 0) //
                || (ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_20) && NMAL2800Constant.RADIO_PROS.compareTo(scrnMsg.xxRadioBtn_20.getValue()) == 0)) {
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsAcctDunsNm_AH, scrnMsg.dsAcctDunsNm_R1.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCmpySicCd_AH, scrnMsg.dsCustSicCd_R2.getValue());
            ZYPEZDItemValueSetter.setValue(aBMsg.aftDsCmpySicDescTxt_AH, scrnMsg.dsCustSicDescTxt_R2.getValue());
        }

        scrnMsg.setFocusItem(aBMsg.aftLocFirstLineAddr_A1);
    }
}

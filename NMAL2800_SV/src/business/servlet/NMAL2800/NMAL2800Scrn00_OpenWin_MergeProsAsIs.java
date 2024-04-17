/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2800;

import static business.servlet.NMAL2800.constant.NMAL2800Constant.BIZ_ID;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.FUNC_CD_SRCH;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.XTRNL_PROS_RVW_CMNT_TXT;
import static business.servlet.NMAL2800.constant.NMAL2800Constant.ZZM9000E;

import java.math.BigDecimal;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2800.NMAL2800CMsg;
import business.servlet.NMAL2800.common.NMAL2800CommonLogic;
import business.servlet.NMAL2800.constant.NMAL2800Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL2800Scrn00_OpenWin_MergeProsAsIs
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Fujitsu         C.Tanaka        Create          N/A
 * 2016/07/26   Fujitsu         N.Sugiura       Update          QC#12417
 * 2016/11/02   Fujitsu         C.Yokoi         Update          QC#15294
 * 2018/03/28   Fujitsu         R.Nakamura      Update          QC#23145
 *</pre>
 */
public class NMAL2800Scrn00_OpenWin_MergeProsAsIs extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/11/02 CSA-QC#15294 Add Start
        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        int index = getButtonSelectNumber();
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRowNum, BigDecimal.valueOf(index));

        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);
        if (!ZYPCommonFunc.hasValue(aBMsg.aftDsXrefAcctCd_A1)) {
            aBMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, ZZM9000E, new String[] {XTRNL_PROS_RVW_CMNT_TXT });
        }

        NMAL2800CommonLogic.addCheckItemAfterLocationAddress(scrnMsg, aBMsg);
        scrnMsg.putErrorScreen();
        // 2016/11/02 CSA-QC#15294 Add End
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // 2016/11/02 CSA-QC#15294 Mod Start
        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;

        NMAL2800CMsg bizMsg = new NMAL2800CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_SRCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // return null;
        // 2016/11/02 CSA-QC#15294 Mod End
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2800BMsg scrnMsg = (NMAL2800BMsg) bMsg;
        // 2016/11/02 CSA-QC#15294 Mod Start
        NMAL2800CMsg bizMsg = (NMAL2800CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int index = getButtonSelectNumber();
        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);

        scrnMsg.addCheckItem(aBMsg.aftDsXrefAcctCd_A1);
        scrnMsg.putErrorScreen();

        scrnMsg.P.clear();

//        int index = getButtonSelectNumber();
//        NMAL2800_ABMsg aBMsg = scrnMsg.A.no(index);
//
//        if (!ZYPCommonFunc.hasValue(aBMsg.aftDsXrefAcctCd_A1)) {
//            aBMsg.aftDsXrefAcctCd_A1.setErrorInfo(1, ZZM9000E, new String[] {XTRNL_PROS_RVW_CMNT_TXT });
//            scrnMsg.addCheckItem(aBMsg.aftDsXrefAcctCd_A1);
//            scrnMsg.putErrorScreen();
//        }
        // 2016/11/02 CSA-QC#15294 Mod End

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_R1, NMAL2800Constant.MODE_SET_PROS);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rvwProsNum_R1, aBMsg.rvwProsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.locNum_R1, aBMsg.aftDsXrefAcctCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_R1, aBMsg.aftLocFirstLineAddr_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_R1, aBMsg.aftLocScdLineAddr_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_R1, aBMsg.aftLocThirdLineAddr_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_R1, aBMsg.aftLocFrthLineAddr_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_R1, aBMsg.aftLocCtyAddr_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_R1, aBMsg.aftLocStCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_R1, aBMsg.aftLocPostCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.telNum_R1, aBMsg.aftTelNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsAcctDunsNm_R1, aBMsg.aftDsAcctDunsNm_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dunsNum_R1, aBMsg.aftDunsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsUltDunsNum_R1, aBMsg.aftDsUltDunsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustSicCd_R1, aBMsg.aftDsCustSicCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustSicDescTxt_R1, aBMsg.aftDsCustSicDescTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsLocRevAmt_R1, aBMsg.aftDsLocRevAmt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsLocEmpNum_R1, aBMsg.aftDsLocEmpNum_A1.getValue());
        // Del Start 2018/03/28 QC#23145
//        ZYPEZDItemValueSetter.setValue(scrnMsg.glnNum_R1, aBMsg.aftGlnNum_A1.getValue());
        // Del End 2018/03/28 QC#23145
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsPrntDunsNum_R1, aBMsg.aftDsPrntDunsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.hqDunsNum_R1, aBMsg.aftHqDunsNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustSicCd_R2, aBMsg.aftDsCmpySicCd_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsCustSicDescTxt_R2, aBMsg.aftDsCmpySicDescTxt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_1, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_2, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_3, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_4, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_5, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_6, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_7, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_8, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_9, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_10, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_11, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_12, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_13, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_14, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_15, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_16, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_17, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_18, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_19, NMAL2800Constant.RADIO_MRG_TO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxRadioBtn_20, NMAL2800Constant.RADIO_MRG_TO);

        Object[] params = new Object[63];
        params[0] = scrnMsg.xxModeCd_R1;
        params[1] = scrnMsg.rvwProsNum_R1;
        params[2] = scrnMsg.locNum_R1;
        params[3] = scrnMsg.firstLineAddr_R1;
        params[4] = scrnMsg.scdLineAddr_R1;
        params[5] = scrnMsg.thirdLineAddr_R1;
        params[6] = scrnMsg.frthLineAddr_R1;
        params[7] = scrnMsg.ctyAddr_R1;
        params[8] = scrnMsg.stCd_R1;
        params[9] = scrnMsg.postCd_R1;
        params[10] = scrnMsg.telNum_R1;
        params[11] = scrnMsg.dsAcctDunsNm_R1;
        params[12] = scrnMsg.dunsNum_R1;
        params[13] = scrnMsg.dsUltDunsNum_R1;
        params[14] = scrnMsg.dsCustSicCd_R1;
        params[15] = scrnMsg.dsCustSicDescTxt_R1;
        params[16] = scrnMsg.dsLocRevAmt_R1;
        params[17] = scrnMsg.dsLocEmpNum_R1;
        params[18] = scrnMsg.glnNum_R1;
        params[19] = scrnMsg.dsPrntDunsNum_R1;
        params[20] = scrnMsg.hqDunsNum_R1;
        params[21] = scrnMsg.dsCustSicCd_R2;
        params[22] = scrnMsg.dsCustSicDescTxt_R2;

        params[43] = scrnMsg.xxRadioBtn_1;
        params[44] = scrnMsg.xxRadioBtn_2;
        params[45] = scrnMsg.xxRadioBtn_3;
        params[46] = scrnMsg.xxRadioBtn_4;
        params[47] = scrnMsg.xxRadioBtn_5;
        params[48] = scrnMsg.xxRadioBtn_6;
        params[49] = scrnMsg.xxRadioBtn_7;
        params[50] = scrnMsg.xxRadioBtn_8;
        params[51] = scrnMsg.xxRadioBtn_9;
        params[52] = scrnMsg.xxRadioBtn_10;
        params[53] = scrnMsg.xxRadioBtn_11;
        params[54] = scrnMsg.xxRadioBtn_12;
        params[55] = scrnMsg.xxRadioBtn_13;
        params[56] = scrnMsg.xxRadioBtn_14;
        params[57] = scrnMsg.xxRadioBtn_15;
        params[58] = scrnMsg.xxRadioBtn_16;
        params[59] = scrnMsg.xxRadioBtn_17;
        params[60] = scrnMsg.xxRadioBtn_18;
        params[61] = scrnMsg.xxRadioBtn_19;
        params[62] = scrnMsg.xxRadioBtn_20;

        setArgForSubScreen(params);
    }
}

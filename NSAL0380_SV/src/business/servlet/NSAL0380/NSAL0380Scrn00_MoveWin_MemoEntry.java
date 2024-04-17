/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0380;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg; // import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext; // import
                                                  // business.blap.NSAL0380.NSAL0380CMsg;
// import business.servlet.NSAL0380.constant.NSAL0380Constant;

import business.servlet.NSAL0380.constant.NSAL0380Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/15   Hitachi         O.Okuma         Create          QC3029
 *</pre>
 */
public class NSAL0380Scrn00_MoveWin_MemoEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

         NSAL0380BMsg scrnMsg = (NSAL0380BMsg) bMsg;

         // event line
         final int eventLine = getButtonSelectNumber();
         scrnMsg.xxNum_EV.setValue(eventLine);

         return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0380BMsg scrnMsg  = (NSAL0380BMsg) bMsg;
        int index = scrnMsg.xxNum_EV.getValueInt();
        NSAL0380_ABMsg abMsg = scrnMsg.A.no(index);

        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoCatgCd_P1, SVC_MEMO_CATG.CONTRACT_MEMO);
        ZYPEZDItemValueSetter.setValue(scrnMsg.svcMemoTpCd_P1, SVC_MEMO_TP.RENEWAL_RULES);

        // Label
        setScrColLbTxtLabel(scrnMsg, abMsg);

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrColLbTxt_L1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_H1, "Contract #");
        } else {
            scrnMsg.xxComnScrColLbTxt_H1.clear();
            scrnMsg.xxComnScrColLbTxt_L1.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrColLbTxt_L2)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_H2, "Serial #");
        } else {
            scrnMsg.xxComnScrColLbTxt_H2.clear();
            scrnMsg.xxComnScrColLbTxt_L2.clear();
        }

        if (ZYPCommonFunc.hasValue(scrnMsg.xxComnScrColLbTxt_L3)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_H3, "BASE/OVERAGE");
        } else {
            scrnMsg.xxComnScrColLbTxt_H3.clear();
            scrnMsg.xxComnScrColLbTxt_L3.clear();
        }

        scrnMsg.xxComnScrColLbTxt_H4.clear();
        scrnMsg.xxComnScrColLbTxt_L4.clear();
        scrnMsg.xxComnScrColLbTxt_H5.clear();
        scrnMsg.xxComnScrColLbTxt_L5.clear();

        // Search criteria
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_C1, "DS_CONTR_PK");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_V1, abMsg.dsContrPk_A.getValue().toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_C2, "SVC_MEMO_TRX_NUM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_V2, abMsg.dsContrRnwEsclPk_A.getValue().toString());
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_C3, "SVC_MEMO_TRX_NM");
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_V3, NSAL0380Constant.DS_CONTR_RNW_ESCL_PK);
        scrnMsg.xxComnScrColLbTxt_C4.clear();
        scrnMsg.xxComnScrColLbTxt_V4.clear();
        scrnMsg.xxComnScrColLbTxt_C5.clear();
        scrnMsg.xxComnScrColLbTxt_V5.clear();

        Object[] args = new Object[NSAL0380Constant.PARAM_LENGTH_NSAL0160];

        int i = 0;
        args[i++] = scrnMsg.svcMemoCatgCd_P1;
        args[i++] = scrnMsg.svcMemoTpCd_P1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_H5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_L5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V1;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V2;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V3;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V4;
        args[i++] = scrnMsg.xxComnScrColLbTxt_C5;
        args[i++] = scrnMsg.xxComnScrColLbTxt_V5;

        setArgForSubScreen(args);
    }

    private void setScrColLbTxtLabel(NSAL0380BMsg scrnMsg,  NSAL0380_ABMsg abMsg) {

        scrnMsg.xxComnScrColLbTxt_L1.clear();
        scrnMsg.xxComnScrColLbTxt_L2.clear();
        scrnMsg.xxComnScrColLbTxt_L3.clear();

        if (NSAL0380Constant.MACH_LVL_NUM_1.equals(abMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L1, abMsg.dsContrNum_A);

        } else if (NSAL0380Constant.MACH_LVL_NUM_2.equals(abMsg.xxDplyByCtrlAncrLvlCd_A.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L2, abMsg.serNum_A);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (abMsg.dsContrPk_A.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(i).dsContrNum_A);
                    break;
                }
            }
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L3, abMsg.dsContrBaseUsgNm_A);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (abMsg.dsContrPk_A.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A.getValue()) == 0
                        && NSAL0380Constant.MACH_LVL_NUM_1.equals(scrnMsg.A.no(i).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(i).dsContrNum_A);

                } else if (abMsg.prntDsContrDtlPk_A.getValue().compareTo(scrnMsg.A.no(i).prntDsContrDtlPk_A.getValue()) == 0
                        && NSAL0380Constant.MACH_LVL_NUM_2.equals(scrnMsg.A.no(i).xxDplyByCtrlAncrLvlCd_A.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxComnScrColLbTxt_L2, scrnMsg.A.no(i).serNum_A);
                    break;
                }
            }
        }
    }
}

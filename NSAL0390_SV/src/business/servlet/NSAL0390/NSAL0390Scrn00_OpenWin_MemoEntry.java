/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0390;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static business.servlet.NSAL0390.constant.NSAL0390Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/18   Hitachi         A.Kohinata      Create          QC#4212
 * 2018/03/16   CITS            M.Naito         Update          QC#20496
 *</pre>
 */
public class NSAL0390Scrn00_OpenWin_MemoEntry extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0390BMsg scrnMsg = (NSAL0390BMsg) bMsg;

        int index = getButtonSelectNumber();
        NSAL0390_ABMsg abMsg = scrnMsg.A.no(index);

        setValue(scrnMsg.svcMemoCatgCd_P1, SVC_MEMO_CATG.CONTRACT_MEMO);
        setValue(scrnMsg.svcMemoTpCd_P1, SVC_MEMO_TP.UPDATE_CREDIT_CARD_INFO);

        // Label
        setScrColLbTxtLabel(scrnMsg, abMsg);

        if (hasValue(scrnMsg.xxComnScrColLbTxt_L1)) {
            setValue(scrnMsg.xxComnScrColLbTxt_H1, "Contract #");
        } else {
            scrnMsg.xxComnScrColLbTxt_H1.clear();
            scrnMsg.xxComnScrColLbTxt_L1.clear();
        }

        if (hasValue(scrnMsg.xxComnScrColLbTxt_L2)) {
            setValue(scrnMsg.xxComnScrColLbTxt_H2, "Serial #");
        } else {
            scrnMsg.xxComnScrColLbTxt_H2.clear();
            scrnMsg.xxComnScrColLbTxt_L2.clear();
        }

        if (hasValue(scrnMsg.xxComnScrColLbTxt_L3)) {
            setValue(scrnMsg.xxComnScrColLbTxt_H3, "BASE/OVERAGE");
        } else {
            scrnMsg.xxComnScrColLbTxt_H3.clear();
            scrnMsg.xxComnScrColLbTxt_L3.clear();
        }

        scrnMsg.xxComnScrColLbTxt_H4.clear();
        scrnMsg.xxComnScrColLbTxt_L4.clear();
        scrnMsg.xxComnScrColLbTxt_H5.clear();
        scrnMsg.xxComnScrColLbTxt_L5.clear();

        // Search criteria
        setValue(scrnMsg.xxComnScrColLbTxt_C1, "DS_CONTR_PK");
        setValue(scrnMsg.xxComnScrColLbTxt_V1, abMsg.dsContrPk_A0.getValue().toString());
        setValue(scrnMsg.xxComnScrColLbTxt_C2, "SVC_MEMO_TRX_NUM");
        if (hasValue(abMsg.dsContrCrCardPoPk_A0)) {
            setValue(scrnMsg.xxComnScrColLbTxt_V2, abMsg.dsContrCrCardPoPk_A0.getValue().toString());
        // START 2018/03/16 M.Naito [QC#20496, ADD]
        } else {
            scrnMsg.xxComnScrColLbTxt_V2.clear();
        }
        // END 2018/03/16 M.Naito [QC#20496, ADD]
        setValue(scrnMsg.xxComnScrColLbTxt_C3, "SVC_MEMO_TRX_NM");
        setValue(scrnMsg.xxComnScrColLbTxt_V3, "DS_CONTR_CR_CARD_PO_PK");
        scrnMsg.xxComnScrColLbTxt_C4.clear();
        scrnMsg.xxComnScrColLbTxt_V4.clear();
        scrnMsg.xxComnScrColLbTxt_C5.clear();
        scrnMsg.xxComnScrColLbTxt_V5.clear();

        Object[] args = new Object[PARAM_LENGTH_NSBL0160];

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

    private void setScrColLbTxtLabel(NSAL0390BMsg scrnMsg, NSAL0390_ABMsg abMsg) {

        scrnMsg.xxComnScrColLbTxt_L1.clear();
        scrnMsg.xxComnScrColLbTxt_L2.clear();
        scrnMsg.xxComnScrColLbTxt_L3.clear();

        String machLvlNum = abMsg.dsContrMachLvlNum_A0.getValue();

        if (MACH_LVL_NUM_1.equals(machLvlNum)) {
            setValue(scrnMsg.xxComnScrColLbTxt_L1, abMsg.dsContrNum_A0);

        } else if (MACH_LVL_NUM_2.equals(machLvlNum)) {
            setValue(scrnMsg.xxComnScrColLbTxt_L2, abMsg.serNum_A0);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (MACH_LVL_NUM_1.equals(scrnMsg.A.no(i).dsContrMachLvlNum_A0.getValue()) && abMsg.dsContrPk_A0.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A0.getValue()) == 0) {
                    setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(i).dsContrNum_A0);
                    break;
                }
            }
        } else {
            setValue(scrnMsg.xxComnScrColLbTxt_L3, abMsg.mtrLbDescTxt_A0);

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                if (MACH_LVL_NUM_1.equals(scrnMsg.A.no(i).dsContrMachLvlNum_A0.getValue()) && abMsg.dsContrPk_A0.getValue().compareTo(scrnMsg.A.no(i).dsContrPk_A0.getValue()) == 0) {
                    setValue(scrnMsg.xxComnScrColLbTxt_L1, scrnMsg.A.no(i).dsContrNum_A0);
                    continue;
                }
                if (MACH_LVL_NUM_2.equals(scrnMsg.A.no(i).dsContrMachLvlNum_A0.getValue()) && abMsg.dsContrDtlPk_A0.getValue().compareTo(scrnMsg.A.no(i).dsContrDtlPk_A0.getValue()) == 0) {
                    setValue(scrnMsg.xxComnScrColLbTxt_L2, scrnMsg.A.no(i).serNum_A0);
                    break;
                }
            }
        }
    }
}

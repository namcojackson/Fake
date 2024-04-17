/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;

import parts.common.EZDMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NSZC057001PMsg;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/09   Hitachi         A.Kohinata      Create          N/A
 * 2015/11/27   Hitachi         A.Kohinata      Update          QC1234
 * 2016/01/06   Hitachi         T.Tomita        Update          QC2781
 * 2016/04/28   Hitachi         A.Kohinata      Update          QC1088
 * 2016/10/25   Hitachi         K.Kishimoto     Update          QC15519
 * 2017/01/30   Hitachi         K.Kitachi       Update          QC#17332
 * 2018/01/22   Hitachi         K.Kojima        Update          QC#23588
 * 2018/05/10   CITS            M.Naito         Update          QC#23212
 * 2018/06/19   CITS            T.Wada          Update          QC#26658
 * 2022/06/22   CITS            E.Sanchez       Update          QC#59804
 * 2024/01/11   Hitachi         S.Moriai        Update          QC#62117
 * </pre>
 */
public class CheckStartRead {

    /**
     * checkValidStartRead
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkValidStartRead(NSZC057001 mainClass, NSZC057001PMsg param) {

        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
        // START 2018/01/22 K.Kojima [QC#23588,DEL]
        // if (!dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.REGULAR) && !dsContrTMsg.dsContrCatgCd.getValue().equals(DS_CONTR_CATG.AGGREGATE)) {
        //     return rtrnTMsgArray;
        // }
        // END 2018/01/22 K.Kojima [QC#23588,DEL]

        // get number constant
        BigDecimal startMtrReadWindowBefDays = ZYPCodeDataUtil.getNumConstValue(START_MTR_READ_WINDOW_BEF_DAYS, param.glblCmpyCd.getValue());
        int intStartMtrReadWindowBefDays = 0;
        if (hasValue(startMtrReadWindowBefDays)) {
            intStartMtrReadWindowBefDays = startMtrReadWindowBefDays.intValue();
        }

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (!DS_CONTR_DTL_TP.USAGE_ONLY.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue()) && !DS_CONTR_DTL_TP.BASE_AND_USAGE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            // START 2018/06/19 T.Wada [QC#26658, DEL]
//            // START 2018/05/10 M.Naito [QC#23212, ADD]
//            String dsContrCtrlStsCd = getDsContrCtrlStsCd(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue());
//            if (!DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd) && !DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd)) {
//                continue;
//            }
//            // END 2018/05/10 M.Naito [QC#23212, ADD]
            // END 2018/06/19 T.Wada [QC#26658, DEL]

            // START 2018/06/19 T.Wada [QC#26658, ADD]
            if (isExistInvoicedBllg(mainClass, param.glblCmpyCd.getValue(),dsContrDtlTMsg.dsContrDtlPk.getValue())) {
                continue;
            }
            // END 2018/06/19 T.Wada [QC#26658, ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            SVC_PHYS_MTR_READTMsg svcPhysMtrReadTMsg = getSvcPhysMtrRead(param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue());

            // Mod Start 2016/10/25 <QC#15519>
            // START 2016/01/06 T.Tomita [QC2781, MOD]
            // START 2024/01/11 S.Moriai [QC62117, MOD]
            //if (svcPhysMtrReadTMsg == null) {
            if (svcPhysMtrReadTMsg == null &&
                    ZYPDateUtil.getSalesDate(param.glblCmpyCd.getValue()).compareTo(ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffFromDt.getValue(), -intStartMtrReadWindowBefDays))>=0) {
            // END 2024/01/11 S.Moriai [QC62117, MOD]
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                // START 2016/04/28 [QC1088, MOD]
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0781E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
                // END 2016/04/28 [QC1088, MOD]
            // START 2017/01/30 K.Kitachi [QC#17332, MOD]
            } else if (svcPhysMtrReadTMsg != null
                    && (svcPhysMtrReadTMsg.mtrReadDt.getValue().compareTo(ZYPDateUtil.addDays(dsContrDtlTMsg.contrEffFromDt.getValue(), -intStartMtrReadWindowBefDays)) < 0
                            || svcPhysMtrReadTMsg.mtrReadDt.getValue().compareTo(dsContrDtlTMsg.contrEffFromDt.getValue()) > 0)) {
            // END 2017/01/30 K.Kitachi [QC#17332, MOD]
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0782E), SVC_PHYS_MTR_READ_PK, svcPhysMtrReadTMsg.svcPhysMtrReadPk.getValue());
            }
            // START 2022/06/22 E.Sanchez [QC#59804, ADD]
            else if (svcPhysMtrReadTMsg != null 
                    && svcPhysMtrReadTMsg.carryOverTpCd.getValue().compareTo(CARRY_OVER_TP_CD_ON) == 0) {
                setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
                setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM0782E),
                        DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            // END 2022/06/22 E.Sanchez [QC#59804, ADD]
            // END 2016/01/06 T.Tomita [QC2781, MOD]
            // Mod End   2016/10/25 <QC#15519>

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
}

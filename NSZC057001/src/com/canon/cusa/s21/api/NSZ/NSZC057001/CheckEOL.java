/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC057001;

import static com.canon.cusa.s21.api.NSZ.NSZC057001.NSZC057001CommonLogic.*;
import static com.canon.cusa.s21.api.NSZ.NSZC057001.constant.NSZC057001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.Map;

import parts.common.EZDMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsg;
import business.db.DS_CONTR_VLD_RSLT_WRKTMsgArray;
import business.parts.NSZC057001PMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.EndOfLifeBean;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetEndOfLifeInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_VLD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MDL_EOL_STS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Contract Validation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/25   Hitachi         T.Tomita        Create          QC#3051
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22611
 * 2018/06/13   Hitachi         A.Kohinata      Update          QC#26028
 * 2019/10/10   Hitachi         K.Fujimoto      Update          QC#54073
 * </pre>
 */
public class CheckEOL {

    // del start 2018/06/13 QC#26028
//    /**
//     * checkEOL
//     * @param mainClass NSZC057001
//     * @param param NSZC057001PMsg
//     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
//     */
//    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkEOL(NSZC057001 mainClass, NSZC057001PMsg param) {
//        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
//        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
//        int arrayIdx = 0;
//
//        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
//
//        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
//        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);
//
//        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
//            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);
//
//            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
//                continue;
//            }
//
//            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
//                continue;
//            }
//
//            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
//            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
//            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
//            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);
//
//            List<Map<String, Object>> eolList = getEndOfLife(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue());
//            if (eolList.size() > 0) {
//                if (existDsContrTrk(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.dsContrDtlPk.getValue())) {
//                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1069W), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
//                    // START 2018/03/15 K.Kojima [QC#22611,DEL]
//                    // setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.WARNING);
//                    // END 2018/03/15 K.Kojima [QC#22611,DEL]
//                } else {
//                    setValueTMsg(rtrnTMsg, S21MessageFunc.clspGetMessage(NSZM1070E), DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
//                }
//            }
//
//            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
//            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
//        }
//
//        return rtrnTMsgArray;
//    }
    // del end 2018/06/13 QC#26028

    // add start 2018/06/13 QC#26028
    /**
     * checkEOLNoContract
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkEOLNoContract(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            // START 2019/10/10 K.Fujimoto [QC#54073,ADD]
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END   2019/10/10 K.Fujimoto [QC#54073,ADD]
            
            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // START 2019/10/10 K.Fujimoto [QC#54073,ADD]
            String eolErrMsg = getEolErrMsg(param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), dsContrDtlTMsg.contrEffFromDt.getValue(), DS_MDL_EOL_STS.EOL_NO_CONTRACT);
            if (ZYPCommonFunc.hasValue(eolErrMsg)) {
                setValueTMsg(rtrnTMsg, eolErrMsg, DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            // END   2019/10/10 K.Fujimoto [QC#54073,ADD]
            // START 2019/10/10 K.Fujimoto [QC#54073,DEL]
//            if (eol != null) {
//                String message = S21MessageFunc.clspGetMessage(NSZM1338E, new String[] {"No-Contract", "the contract start date" });
//                if (ZYPCommonFunc.hasValue((String) eol.get("EOL_SVC_CONTR_CMNT_TXT"))) {
//                    message = S21StringUtil.concatStrings(message, " ", (String) eol.get("EOL_SVC_CONTR_CMNT_TXT"));
//                }
//                setValueTMsg(rtrnTMsg, message, DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
//            }
            // END   2019/10/10 K.Fujimoto [QC#54073,DEL]

            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }

    /**
     * checkEOLNoService
     * @param mainClass NSZC057001
     * @param param NSZC057001PMsg
     * @return DS_CONTR_VLD_RSLT_WRKTMsgArray
     */
    public static DS_CONTR_VLD_RSLT_WRKTMsgArray checkEOLNoService(NSZC057001 mainClass, NSZC057001PMsg param) {
        DS_CONTR_VLD_RSLT_WRKTMsgArray rtrnTMsgArray = new DS_CONTR_VLD_RSLT_WRKTMsgArray();
        rtrnTMsgArray.setMsgList(new DS_CONTR_VLD_RSLT_WRKTMsg[MAX_SIZE]);
        int arrayIdx = 0;

        DS_CONTR_VLD_RSLT_WRKTMsg rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();

        DS_CONTRTMsg dsContrTMsg = mainClass.dsContrCache.get(KEY);
        DS_CONTR_DTLTMsgArray dsContrDtlTMsgArray = mainClass.dsContrDtlArrayCache.get(KEY);

        for (int i = 0; i < dsContrDtlTMsgArray.getValidCount(); i++) {
            DS_CONTR_DTLTMsg dsContrDtlTMsg = dsContrDtlTMsgArray.no(i);

            if (DS_CONTR_CATG.FLEET.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrTMsg.dsContrCatgCd.getValue()) && DS_CONTR_DTL_TP.AGGREGATE.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }

            // START 2019/10/10 K.Fujimoto [QC#54073,ADD]
            if (DS_CONTR_DTL_TP.ACCESSORIES.equals(dsContrDtlTMsg.dsContrDtlTpCd.getValue())) {
                continue;
            }
            // END   2019/10/10 K.Fujimoto [QC#54073,ADD]

            rtrnTMsg = new DS_CONTR_VLD_RSLT_WRKTMsg();
            setValue(rtrnTMsg.dsContrPk, dsContrTMsg.dsContrPk);
            setValue(rtrnTMsg.dsContrDtlPk, dsContrDtlTMsg.dsContrDtlPk);
            setValue(rtrnTMsg.dsContrVldStsCd, DS_CONTR_VLD_STS.SUCCESS);

            // START 2019/10/10 K.Fujimoto [QC#54073,ADD]
            String eolErrMsg = getEolErrMsg(param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue(), DS_MDL_EOL_STS.EOL_NO_SERVICE);
            if (ZYPCommonFunc.hasValue(eolErrMsg)) {
                setValueTMsg(rtrnTMsg, eolErrMsg, DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
            }
            // END   2019/10/10 K.Fujimoto [QC#54073,ADD]
            // START 2019/10/10 K.Fujimoto [QC#54073,DEL]
//            Map<String, Object> eol = getEndOfLife(mainClass, param.glblCmpyCd.getValue(), dsContrDtlTMsg.svcMachMstrPk.getValue(), dsContrDtlTMsg.contrEffThruDt.getValue(), DS_MDL_EOL_STS.EOL_NO_SERVICE);
//            if (eol != null) {
//                String message = S21MessageFunc.clspGetMessage(NSZM1338E, new String[] {"Service", "the contract end date" });
//                if (ZYPCommonFunc.hasValue((String) eol.get("EOL_SVC_CONTR_CMNT_TXT"))) {
//                    message = S21StringUtil.concatStrings(message, " ", (String) eol.get("EOL_SVC_CONTR_CMNT_TXT"));
//                }
//                setValueTMsg(rtrnTMsg, message, DS_CONTR_DTL_PK, dsContrDtlTMsg.dsContrDtlPk.getValue());
//            }
            // END   2019/10/10 K.Fujimoto [QC#54073,DEL]
            EZDMsg.copy(rtrnTMsg, null, rtrnTMsgArray.no(arrayIdx++), null);
            rtrnTMsgArray.setValidCount(rtrnTMsgArray.getValidCount() + 1);
        }

        return rtrnTMsgArray;
    }
    // add end 2018/06/13 QC#26028
    // START 2019/10/10 K.Fujimoto [QC#54073,ADD]
    /**
     * isEol
     * 
     */
    private static final String getEolErrMsg(String glblCmpyCd, BigDecimal svcMachMstrPk, String eolDt, String dsMdlEolSts) {
        EndOfLifeBean infoBean = new EndOfLifeBean();
        infoBean.setGlblCmpyCd(glblCmpyCd);
        infoBean.setSvcMachMstrPk(svcMachMstrPk);
        infoBean.setEolDt(eolDt);
        //Other Error
        if (!NSXC001001GetEndOfLifeInfo.getEndOfLifeInfo(infoBean)) {
            return S21MessageFunc.clspGetMessage(infoBean.getXxMsgId());
        }

        //EOL - No Contract
        if (DS_MDL_EOL_STS.EOL_NO_CONTRACT.equals(dsMdlEolSts) && ZYPConstant.FLG_OFF_N.equals(infoBean.getContrAvalFlg())) {
            String message = S21MessageFunc.clspGetMessage(NSZM1338E, new String[] {"No-Contract", "the contract start date" });
            return addMsgToCmnt(message, infoBean.getEolSvcContrCmntTxt());
        }

        //EOL - No Service
        if (DS_MDL_EOL_STS.EOL_NO_SERVICE.equals(dsMdlEolSts) && ZYPConstant.FLG_OFF_N.equals(infoBean.getSvcAvalFlg())) {
            String message = S21MessageFunc.clspGetMessage(NSZM1338E, new String[] {"Service", "the contract end date" });
            return addMsgToCmnt(message, infoBean.getEolSvcContrCmntTxt());
        }

        //No Error
        return null;
    }

    private static final String addMsgToCmnt(String errMsg, String eolSvcContrCmntTxt) {
        if (ZYPCommonFunc.hasValue(eolSvcContrCmntTxt)) {
            return S21StringUtil.concatStrings(errMsg, " ", eolSvcContrCmntTxt);
        }
        return errMsg;
    }
    // END   2019/10/10 K.Fujimoto [QC#54073,ADD]
}

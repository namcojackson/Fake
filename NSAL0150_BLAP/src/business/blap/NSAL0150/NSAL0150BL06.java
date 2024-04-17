/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0150;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0150.common.NSAL0150CommonLogic;
import business.blap.NSAL0150.constant.NSAL0150Constant;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.db.SVC_PHYS_MTR_READTMsgArray;
import business.parts.NSZC010001PMsg;
import business.parts.NSZC010001_APMsg;

import com.canon.cusa.s21.api.NSZ.NSZC010001.NSZC010001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_MTR_READ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/02/03   CUSA            SRAA            Create          N/A
 * 2015/11/26   Hitachi         K.Kasai         Update          Unit Test #71
 * 2015/12/17   Hitachi         K.Kasai         Update          QC#2072
 * 2016/01/27   Hitachi         K.Kasai         Update          QC#3757
 * 2016/01/29   Hitachi         K.Kishimoto     Update          QC#3865
 * 2016/03/24   Hitachi         K.Yamada        Update          QC#4402
 * 2016/03/29   Hitachi         T.Tomita        Update          QC#4396
 * 2016/07/26   Hitachi         K.Kishimoto     Update          QC#5699
 * 2016/10/03   Hitachi         K.Kishimoto     Update          QC#12274
 * 2017/03/13   Hitachi         K.Kitachi       Update          QC#15163
 * 2017/05/12   Hitachi         K.Kitachi       Update          QC#18563
 * 2017/09/07   Hitachi         A.Kohinata      Update          QC#15134
 * 2017/09/15   Hitachi         K.Kojima        Update          QC#21125
 * 2017/10/06   Hitachi         U.Kim           Update          QC#21125
 * 2018/06/05   Hitachi         K.Kitachi       Update          QC#26048
 * 2019/04/09   Hitachi         K.Kitachi       Update          QC#31089
 * 2019/09/05   Hitachi         K.Fujimoto      Update          QC#53294
 * 2019/10/29   Hitachi         K.Fujimoto      Update          QC#54402
 * 2020/02/07   Hitachi         A.Kohinata      Update          QC#55812
 * 2020/03/03   Hitachi         K.Kishimoto     Update          QC#56123
 * 2022/08/12   Hitachi         H.Watanabe      Update          QC#60046
 * 2024/03/26   Hitachi         K.Watanabe      Update          QC#63549
 *</pre>
 */
public class NSAL0150BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0150CMsg cMsg = (NSAL0150CMsg) ezdCMsg;
            NSAL0150SMsg sMsg = (NSAL0150SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0150Scrn00_CMN_Submit".equals(screenAplId)) {
                NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
                doProcess_NSAL0150Scrn00_CMN_Submit(cMsg, sMsg);
            // DEL START 2022/08/12 H.Watanabe [QC#60046]
//          } else if ("NSAL0150Scrn00_InsertAsActual".equals(screenAplId)) {
//              NSAL0150CommonLogic.copyCMsgToSMsg(cMsg, sMsg);
//              doProcess_NSAL0150Scrn00_InsertAsActual(cMsg, sMsg);
            // DEL END 2022/08/12 H.Watanabe [QC#60046]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    // DEL START 2022/08/12 H.Watanabe [QC#60046]
//    private void doProcess_NSAL0150Scrn00_InsertAsActual(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {
//        if (ZYPCommonFunc.hasValue(cMsg.xxRowNum)) {
//            String glblCmpyCd = getGlobalCompanyCode();
//            String slsDt = NSAL0150CommonLogic.getBizIdSlsDt();
//            String userId = getContextUserInfo().getUserId();
//            NSZC010001PMsg pMsg = new NSZC010001PMsg();
//            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
//            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
//            ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
//            // MOD START 2015/12/17 K.Kasai [QC#2072]
//            ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.PERIODIC_METER_READING);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.ACTUAL);
//            // MOD END 2015/12/17 K.Kasai [QC#2072]
//            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
//            ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, cMsg.dsMtrReadTpGrpCd_BS);
//            ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, cMsg.xxWrnSkipFlg);
//            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
//            pMsg.fsrNum.clear();
//            pMsg.fsrVisitNum.clear();

//            // START 2017/03/13 K.Kitachi [QC#15163, DEL]
//            BigDecimal svcPhysMtrReadGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ");
//            // END 2017/03/13 K.Kitachi [QC#15163, DEL]

//            int rowNum = cMsg.xxRowNum.getValueInt();
//            BigDecimal grpSq = cMsg.E.no(rowNum).svcPhysMtrReadGrpSq_E.getValue();
//            int cnt = 0;
//            // ADD START 2015/12/17 K.Kasai [QC2120]
//            // START 2017/05/12 K.Kitachi [QC#18563, DEL]
//            String dsMtrReadTpCd = null;
//            // END 2017/05/12 K.Kitachi [QC#18563, DEL]
//            // ADD END 2015/12/17 K.Kasai [QC2120]
//            for (int i = 0; i < cMsg.E.getValidCount(); i++) {
//                BigDecimal cmpSq = cMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue();
//                if (NSAL0150CommonLogic.isEqualNum(grpSq, cmpSq)) {
//                    pMsg.A.no(cnt).physMtrId.clear();
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, cMsg.E.no(i).mtrReadDt_E);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, slsDt);
//                    pMsg.A.no(cnt).svcPhysMtrReadPk.clear();
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, cMsg.E.no(i).readMtrCnt_E);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, BigDecimal.ZERO);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, userId);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, ZYPConstant.FLG_OFF_N);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, ZYPConstant.FLG_OFF_N);
//                    // START 2017/03/13 K.Kitachi [QC#15163, DEL]
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
//                    // END 2017/03/13 K.Kitachi [QC#15163, DEL]
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, cMsg.E.no(i).svcPhysMtrPk_E);
//                    // mod start 2017/09/07 QC#15134
//                    //ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, cMsg.E.no(i).mtrEntryCmntTxt_E);
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, getMtrEntryCmntTxt(glblCmpyCd, cMsg.E.no(i).svcPhysMtrReadPk_E.getValue()));
//                    // mod end 2017/09/07 QC#15134
//                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, ZYPConstant.FLG_ON_Y);
//                    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
//                    if (DS_MTR_READ_TP.INITIAL_METER_READING.equals(cMsg.E.no(i).dsMtrReadTpCd_E.getValue())) {
//                        ZYPEZDItemValueSetter.setValue(pMsg.xxStartReadFlg, ZYPConstant.FLG_ON_Y);
//                        pMsg.dsContrDtlPk.clear();
//                    }
//                    // ADD END 2015/11/26 K.Kasai [Unit Test #71]
//                    // ADD START 2015/12/17 K.Kasai [QC2120]
//                    // START 2017/05/12 K.Kitachi [QC#18563, DEL]
//                    if (!ZYPCommonFunc.hasValue(dsMtrReadTpCd)) {
//                        dsMtrReadTpCd = cMsg.E.no(i).dsMtrReadTpGrpCd_E.getValue();
//                    }
//                    // END 2017/05/12 K.Kitachi [QC#18563, DEL]
//                    // ADD END 2015/12/17 K.Kasai [QC2120]
//                    cnt++;
//                }
//            }
//            // ADD START 2015/12/17 K.Kasai [QC2120]
//            // START 2017/05/12 K.Kitachi [QC#18563, MOD]
//            ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, dsMtrReadTpCd);
//            ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, DS_MTR_READ_TP_GRP.BILLABLE_READS);
//            // END 2017/05/12 K.Kitachi [QC#18563, MOD]
//            // ADD END 2015/12/17 K.Kasai [QC2120]
//            pMsg.A.setValidCount(cnt);

//            if (pMsg.A.getValidCount() > 0) {
//                new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                for (S21ApiMessage msg : msgList) {
//                    String msgId = msg.getXxMsgid();
//                    String[] prm = msg.getXxMsgPrmArray();
//                    if (msgId.endsWith("E")) {
//                        cMsg.setMessageInfo(msgId, prm);
//                        return;
//                    }
//                    // ADD START 2015/11/26 K.Kasai [Unit Test #71]
//                    if (msgId.endsWith("W")) {
//                        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
//                        cMsg.setMessageInfo(msgId, prm);
//                        return;
//                    }
//                    // ADD END 2015/11/26 K.Kasai [Unit Test #71]
//                }
//            }
//
//            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
//            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
//            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
//            cMsg.setMessageInfo(NSAL0150Constant.NZZM0002I);
//        }
//    }
    // DEL END 2022/08/12 H.Watanabe [QC#60046]

    private void doProcess_NSAL0150Scrn00_CMN_Submit(NSAL0150CMsg cMsg, NSAL0150SMsg sMsg) {

        NSAL0150CommonLogic.setupSubmitParameter(cMsg);
        // START 2020/03/03 [QC#56123, ADD]
        Map<String, Object>maxUpTm =  NSAL0150Query.getInstance().getMaxUpTm(getGlobalCompanyCode(), cMsg.svcMachMstrPk.getValue());
        String chkUpTm = null;
        String chkUpTmZn= null;
        if (maxUpTm != null) {
            chkUpTm = (String)maxUpTm.get("EZUPTIME");
            chkUpTmZn = (String)maxUpTm.get("EZUPTIMEZONE");
        }
        if (ZYPCommonFunc.hasValue(sMsg.ezUpTime_MR)) {
            if (chkUpTm == null) {
                cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                return;
            }
            if (!ZYPDateUtil.isSameTimeStamp(sMsg.ezUpTime_MR.getValue(), sMsg.ezUpTimeZone_MR.getValue(), chkUpTm, chkUpTmZn)) {
                cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                return;
            }
        } else {
            if (chkUpTm != null) {
                cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                return;
            }
        }
        // END   2020/03/03 [QC#56123, ADD]

        // START 2016/03/24 K.Yamada [QC#4402, MOD]
        NSAL0150CommonLogic.checkSubmitParameter(cMsg, sMsg);
        // END 2016/03/24 K.Yamada [QC#4402, MOD]
        if (NSAL0150CommonLogic.hasError(cMsg)) {
            return;
        }
        // START 2024/03/26 K.Watanabe [QC#63549, ADD]
        if (NSAL0150CommonLogic.checkMeterReadWrn(cMsg)) {
            return;
        }
        // END   2024/03/26 K.Watanabe [QC#63549, ADD]

        NSAL0150Query query = NSAL0150Query.getInstance();
        String glblCmpyCd = getGlobalCompanyCode();
        String slsDt = NSAL0150CommonLogic.getBizIdSlsDt();
        String userId = getContextUserInfo().getUserId();

        // START 2019/04/09 K.Kitachi [QC#31089, ADD]
        if (!NSAL0150CommonLogic.isEqualStr(cMsg.svcCmntTxt.getValue(), cMsg.svcCmntTxt_HD.getValue())) {
            if (ZYPCommonFunc.hasValue(cMsg.svcMemoPk)) {
                // Update
                SVC_MEMOTMsg updMsg = query.getSvcMemoForUpdateNoWait(glblCmpyCd, cMsg.svcMemoPk.getValue());
                if (updMsg == null) {
                    cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                    return;
                }
                if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime_SM.getValue(), cMsg.ezUpTimeZone_SM.getValue(), updMsg.ezUpTime.getValue(), updMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                    return;
                }
                ZYPEZDItemValueSetter.setValue(updMsg.svcCmntTxt, cMsg.svcCmntTxt);
                ZYPEZDItemValueSetter.setValue(updMsg.lastUpdUsrId, userId);
                ZYPEZDItemValueSetter.setValue(updMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
                S21FastTBLAccessor.update(updMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(updMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAL0150Constant.NSAM0031E, new String[] {updMsg.getTableName() });
                    return;
                }
            } else {
                // Insert
                BigDecimal count = query.getSvcMemoCnt(glblCmpyCd, cMsg.svcMachMstrPk.getValue());
                if (count.compareTo(BigDecimal.ZERO) > 0) {
                    cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                    return;
                }
                SVC_MEMOTMsg insMsg = new SVC_MEMOTMsg();
                ZYPEZDItemValueSetter.setValue(insMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(insMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
                ZYPEZDItemValueSetter.setValue(insMsg.svcMemoCatgCd, SVC_MEMO_CATG.METER_ENTRY_MEMO);
                ZYPEZDItemValueSetter.setValue(insMsg.svcCmntTxt, cMsg.svcCmntTxt);
                ZYPEZDItemValueSetter.setValue(insMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(insMsg.lastUpdUsrId, userId);
                ZYPEZDItemValueSetter.setValue(insMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT));
                S21FastTBLAccessor.insert(insMsg);
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insMsg.getReturnCode())) {
                    cMsg.setMessageInfo(NSAL0150Constant.NSAM0032E, new String[] {insMsg.getTableName() });
                    return;
                }
            }
        }
        // END 2019/04/09 K.Kitachi [QC#31089, ADD]

        // START 2017/10/06 U.Kim [QC#21125,DEL]
        // // valid meter flag
        // // Mod Start 07/26/2016 <QC#5699>
        // if (sMsg.E.getValidCount() > 0) {
        //     for (int i = sMsg.E.getValidCount() - 1; i >= 0; i--) {
        //         SVC_PHYS_MTR_READTMsg tMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue());
        //         if (!NSAL0150CommonLogic.isEqualStr(tMsg.vldMtrFlg.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue())) {
        //             if (!ZYPDateUtil.isSameTimeStamp(sMsg.E.no(i).ezUpTime_E.getValue(), sMsg.E.no(i).ezUpTimeZone_E.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
        //                 cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
        //                 return;
        //             }
        //             // START 2017/09/15 K.Kojima [QC#21125,ADD]
        //             if (!ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).vldMtrFlg_E.getValue())) {
        //                 if (!NSAL0150CommonLogic.checkInvalid(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue(), sMsg.E, i)) {
        //                     cMsg.setMessageInfo(NSAL0150Constant.NSAM0699E);
        //                     return;
        //                 }
        //             }
        //             // END 2017/09/15 K.Kojima [QC#21125,ADD]
        // 
        //             // MOD START 2016/01/26 K.Kasai [QC3757]
//      //               ZYPEZDItemValueSetter.setValue(tMsg.vldMtrFlg, sMsg.E.no(i).vldMtrFlg_E);
//      //               S21FastTBLAccessor.update(tMsg);
//      //               String rtnCd = tMsg.getReturnCode();
//      //               if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
//      //                   // MOD START 2015/11/26 K.Kasai [Unit Test #71]
////    //                     cMsg.setMessageInfo(NSAL0300Constant.NSAM0031E, new String[] {"Service Physical Meter Read" });
//      //                   cMsg.setMessageInfo(NSAL0150Constant.NSAM0031E, new String[] {"Service Physical Meter Read" });
//      //                   // MOD END 2015/11/26 K.Kasai [Unit Test #71]
//      //                   return;
//      //               }
        //             NSZC010001PMsg pMsg = new NSZC010001PMsg();
        //             ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        //             ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
        //             ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
        //             ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
        //             ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, tMsg.dsMtrReadTpCd);
        //             ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, tMsg.dsMtrReadTpGrpCd);
        //             int cnt = 0;
        //             for (int j = i; j >= 0 && sMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue().compareTo(sMsg.E.no(j).svcPhysMtrReadGrpSq_E.getValue()) == 0; j--) {
        //                 SVC_PHYS_MTR_READTMsg jTMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(j).svcPhysMtrReadPk_E.getValue());
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadPk, jTMsg.svcPhysMtrReadPk);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, jTMsg.rgtnMtrDt);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, jTMsg.readMtrCnt);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, jTMsg.testMtrCnt);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, jTMsg.mtrReadDt);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, jTMsg.invProcFlg);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, jTMsg.rgtnUsrId);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, jTMsg.estFlg);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrLbCd, jTMsg.mtrLbCd);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrClsTpCd, jTMsg.mtrClsTpCd);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).dsMdlMtrPk, jTMsg.dsMdlMtrPk);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, jTMsg.svcPhysMtrPk);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, jTMsg.mtrEntryCmntTxt);
        //                 ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, sMsg.E.no(j).vldMtrFlg_E.getValue());
        //                 cnt++;
        //             }
        //             pMsg.A.setValidCount(cnt);
        //             i = i - cnt + 1;
        //             new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
        //             List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
        //             for (S21ApiMessage msg : msgList) {
        //                 String msgId = msg.getXxMsgid();
        //                 String[] prm = msg.getXxMsgPrmArray();
        //                 if (msgId.endsWith("E")) {
        //                     cMsg.setMessageInfo(msgId, prm);
        //                     return;
        //                 }
        //                 if (msgId.endsWith("W")) {
        //                     ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
        //                     cMsg.setMessageInfo(msgId, prm);
        //                     return;
        //                 }
        //             }
        //             // MOD END 2016/01/26 K.Kasai [QC3757]
        //         }
        //     }
        //  // Mod End   07/26/2016 <QC#5699>
        // }
        // END 2017/10/06 U.Kim [QC#21125,DEL]

        // START 2017/10/06 U.Kim [QC#21125,ADD]
        // valid meter flag(N->Y)
        if (sMsg.E.getValidCount() > 0) {
            for (int i = sMsg.E.getValidCount() - 1; i >= 0; i--) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    continue;
                }
                SVC_PHYS_MTR_READTMsg tMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue());
                if (!NSAL0150CommonLogic.isEqualStr(tMsg.vldMtrFlg.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.E.no(i).ezUpTime_E.getValue(), sMsg.E.no(i).ezUpTimeZone_E.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                        return;
                    }
                    NSZC010001PMsg pMsg = new NSZC010001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, tMsg.dsMtrReadTpCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, tMsg.dsMtrReadTpGrpCd);
                    // add start 2020/03/03 QC#56123
                    ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, cMsg.xxWrnSkipFlg);
                    // add end 2020/03/03 QC#56123
                    int cnt = 0;
                    for (int j = i; j >= 0 && sMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue().compareTo(sMsg.E.no(j).svcPhysMtrReadGrpSq_E.getValue()) == 0; j--) {
                        SVC_PHYS_MTR_READTMsg jTMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(j).svcPhysMtrReadPk_E.getValue());
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadPk, jTMsg.svcPhysMtrReadPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, jTMsg.rgtnMtrDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, jTMsg.readMtrCnt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, jTMsg.testMtrCnt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, jTMsg.mtrReadDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, jTMsg.invProcFlg);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, jTMsg.rgtnUsrId);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, jTMsg.estFlg);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrLbCd, jTMsg.mtrLbCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrClsTpCd, jTMsg.mtrClsTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).dsMdlMtrPk, jTMsg.dsMdlMtrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, jTMsg.svcPhysMtrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, jTMsg.mtrEntryCmntTxt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, sMsg.E.no(j).vldMtrFlg_E.getValue());
                        cnt++;
                    }
                    pMsg.A.setValidCount(cnt);
                    // add start 2020/02/07 QC#55812
                    addLackMtrToPMsg(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue(), pMsg);
                    // add end 2020/02/07 QC#55812
                    i = i - cnt + 1;
                    new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (S21ApiMessage msg : msgList) {
                        String msgId = msg.getXxMsgid();
                        String[] prm = msg.getXxMsgPrmArray();
                        if (msgId.endsWith("E")) {
                            cMsg.setMessageInfo(msgId, prm);
                            return;
                        }
                        if (msgId.endsWith("W")) {
                            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                            cMsg.setMessageInfo(msgId, prm);
                            return;
                        }
                    }
                }
            }
        }

        if (sMsg.E.getValidCount() > 0) {
            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    continue;
                }
                SVC_PHYS_MTR_READTMsg tMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue());
                if (!NSAL0150CommonLogic.isEqualStr(tMsg.vldMtrFlg.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.E.no(i).ezUpTime_E.getValue(), sMsg.E.no(i).ezUpTimeZone_E.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                        return;
                    }
                    if (!ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                        if (!NSAL0150CommonLogic.checkInvalid(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue(), sMsg.E, i)) {
                            cMsg.setMessageInfo(NSAL0150Constant.NSAM0699E);
                            return;
                        }
                    }
                }
            }
        }

        if (sMsg.E.getValidCount() > 0) {
            for (int i = 0; i < sMsg.E.getValidCount(); i++) {
                if (ZYPConstant.FLG_ON_Y.equals(sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    continue;
                }
                SVC_PHYS_MTR_READTMsg tMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadPk_E.getValue());
                if (!NSAL0150CommonLogic.isEqualStr(tMsg.vldMtrFlg.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue())) {
                    if (!ZYPDateUtil.isSameTimeStamp(sMsg.E.no(i).ezUpTime_E.getValue(), sMsg.E.no(i).ezUpTimeZone_E.getValue(), tMsg.ezUpTime.getValue(), tMsg.ezUpTimeZone.getValue())) {
                        cMsg.setMessageInfo(NSAL0150Constant.ZZZM9004E);
                        return;
                    }
                    NSZC010001PMsg pMsg = new NSZC010001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
                    ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, tMsg.dsMtrReadTpCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, tMsg.dsMtrReadTpGrpCd);
                    // add start 2020/03/03 QC#56123
                    ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, cMsg.xxWrnSkipFlg);
                    // add end 2020/03/03 QC#56123
                    int cnt = 0;
                    for (int j = i; j < sMsg.E.getValidCount() && sMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue().compareTo(sMsg.E.no(j).svcPhysMtrReadGrpSq_E.getValue()) == 0; j++) {
                        SVC_PHYS_MTR_READTMsg jTMsg = query.getSvcPhysMtrRead(glblCmpyCd, sMsg.E.no(j).svcPhysMtrReadPk_E.getValue());
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadPk, jTMsg.svcPhysMtrReadPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, jTMsg.rgtnMtrDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, jTMsg.readMtrCnt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, jTMsg.testMtrCnt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, jTMsg.mtrReadDt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, jTMsg.invProcFlg);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, jTMsg.rgtnUsrId);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, jTMsg.estFlg);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrLbCd, jTMsg.mtrLbCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrClsTpCd, jTMsg.mtrClsTpCd);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).dsMdlMtrPk, jTMsg.dsMdlMtrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, jTMsg.svcPhysMtrPk);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, jTMsg.mtrEntryCmntTxt);
                        ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, sMsg.E.no(j).vldMtrFlg_E.getValue());
                        cnt++;
                    }
                    pMsg.A.setValidCount(cnt);
                    // add start 2020/02/07 QC#55812
                    addLackMtrToPMsg(glblCmpyCd, sMsg.E.no(i).svcPhysMtrReadGrpSq_E.getValue(), sMsg.E.no(i).vldMtrFlg_E.getValue(), pMsg);
                    // add end 2020/02/07 QC#55812
                    i = i + cnt - 1;
                    new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
                    List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
                    for (S21ApiMessage msg : msgList) {
                        String msgId = msg.getXxMsgid();
                        String[] prm = msg.getXxMsgPrmArray();
                        if (msgId.endsWith("E")) {
                            cMsg.setMessageInfo(msgId, prm);
                            return;
                        }
                        if (msgId.endsWith("W")) {
                            ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                            cMsg.setMessageInfo(msgId, prm);
                            return;
                        }
                    }
                }
            }
        }
        // END 2017/10/06 U.Kim [QC#21125,ADD]

        // DEL START 2015/11/26 K.Kasai [Unit Test #71]
        // check input
//        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//
//            BigDecimal svcPhysMtrPk = cMsg.B.no(i).svcPhysMtrPk_B.getValue();
//            String mtrReadDt = cMsg.B.no(i).mtrReadDt_B.getValue();
//            BigDecimal inpReadMtrCnt = cMsg.B.no(i).readMtrCnt_B.getValue();
//
//            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).readMtrCnt_B)) {
//
//                BigDecimal minAllwReadMtrCnt = NSXC003001SvcPhysMtrRead.getMinAllwMtrCnt(glblCmpyCd, svcPhysMtrPk, mtrReadDt);
//                if (minAllwReadMtrCnt != null && inpReadMtrCnt.compareTo(minAllwReadMtrCnt) < 0) {
//                    EZDDebugOutput.println(5, String.format("%s: %s < %s", NSAL0150Constant.NSZM0542E, inpReadMtrCnt.toPlainString(), minAllwReadMtrCnt.toPlainString()), NSAL0150BL06.class);
//                    cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSZM0542E);
//                    cMsg.setMessageInfo(NSAL0150Constant.NSZM0542E);
//                }
//
//                BigDecimal maxAllwReadMtrCnt = NSXC003001SvcPhysMtrRead.getMaxAllwMtrCnt(glblCmpyCd, svcPhysMtrPk, mtrReadDt);
//                if (maxAllwReadMtrCnt != null && inpReadMtrCnt.compareTo(maxAllwReadMtrCnt) > 0) {
//                    EZDDebugOutput.println(5, String.format("%s: %s > %s", NSAL0150Constant.NSZM0542E, inpReadMtrCnt.toPlainString(), maxAllwReadMtrCnt.toPlainString()), NSAL0150BL06.class);
//                    cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, NSAL0150Constant.NSZM0542E);
//                    cMsg.setMessageInfo(NSAL0150Constant.NSZM0542E);
//                }
//            }
//        }
//
//        if (NSAL0150CommonLogic.isMtrRead(cMsg)) {
//
//            // get latest meter read information before registering
//            // new meter reading.
//            TreeMap<BigDecimal, SvcPhysMtrReadInfoBean> ltstMtrReadMap = new TreeMap<BigDecimal, SvcPhysMtrReadInfoBean>();
//            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                BigDecimal svcPhysMtrPk = cMsg.B.no(i).svcPhysMtrPk_B.getValue();
//                SvcPhysMtrReadInfoBean ltstMtrReadInfo = query.getLtstMtrRead(glblCmpyCd, svcPhysMtrPk);
//                if (ltstMtrReadInfo != null) {
//                    ltstMtrReadMap.put(svcPhysMtrPk, ltstMtrReadInfo);
//                }
//            }
//
//            String thruDt = cMsg.B.no(0).mtrReadDt_B.getValue();
//            TreeMap<String, List<SvcPhysMtrReadInfoBean>> mtrReadMap = new TreeMap<String, List<SvcPhysMtrReadInfoBean>>();
//
//            // estimate
//            for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//
//                BigDecimal svcPhysMtrPk = cMsg.B.no(i).svcPhysMtrPk_B.getValue();
//
//                if (ltstMtrReadMap.containsKey(svcPhysMtrPk)) {
//
//                    SvcPhysMtrReadInfoBean ltstMtrReadInfo = ltstMtrReadMap.get(svcPhysMtrPk);
//
//                    SvcPhysMtrReadInfoBean inpMtrReadInfo = new SvcPhysMtrReadInfoBean();
//                    inpMtrReadInfo.setSvcPhysMtrPk(cMsg.B.no(i).svcPhysMtrPk_B.getValue());
//                    inpMtrReadInfo.setMtrReadDt(cMsg.B.no(i).mtrReadDt_B.getValue());
//                    inpMtrReadInfo.setReadMtrCnt(cMsg.B.no(i).readMtrCnt_B.getValue());
//
//                    S21SsmEZDResult ssmRslt = query.getDsContrBllgSchd(glblCmpyCd, svcPhysMtrPk, ltstMtrReadInfo.getMtrReadDt(), thruDt);
//                    if (ssmRslt != null && ssmRslt.isCodeNormal()) {
//                        List<Map<String, Object>> bllgSchdList = (List<Map<String, Object>>) ssmRslt.getResultObject();
//                        for (Map<String, Object> bllgSchdInfo : bllgSchdList) {
//                            String bllgSchdThruDt = (String) bllgSchdInfo.get("BLLG_SCHD_THRU_DT");
//                            BigDecimal estMtrCnt = NSXC003001SvcPhysMtrRead.estMtrCnt(bllgSchdThruDt, ltstMtrReadInfo, inpMtrReadInfo);
//                            if (estMtrCnt != null && estMtrCnt.compareTo(BigDecimal.ZERO) > 0) {
//                                addEstMtr(mtrReadMap, svcPhysMtrPk, bllgSchdThruDt, estMtrCnt);
//                            }
//                        }
//                    }
//                }
//            }
//        
//
//            List<List<SvcPhysMtrReadInfoBean>> mtrReadGrpList = new ArrayList<List<SvcPhysMtrReadInfoBean>>(mtrReadMap.values());
//
//            NSZC010001PMsg pMsg = buildPMsg(cMsg.svcMachMstrPk.getValue());
//            for (List<SvcPhysMtrReadInfoBean> mtrReadGrp : mtrReadGrpList) {
//                BigDecimal svcPhysMtrReadGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ");
//                for (SvcPhysMtrReadInfoBean mtrReadInfo : mtrReadGrp) {
//                    addEstMtr(pMsg, svcPhysMtrReadGrpSq, mtrReadInfo.getSvcPhysMtrPk(), mtrReadInfo.getMtrReadDt(), mtrReadInfo.getReadMtrCnt());
//                }
//            }
//
//            if (pMsg.A.getValidCount() > 0) {
//                new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                for (S21ApiMessage msg : msgList) {
//                    String msgId = msg.getXxMsgid();
//                    String[] prm = msg.getXxMsgPrmArray();
//                    if (msgId.endsWith("E")) {
//                        cMsg.setMessageInfo(msgId, prm);
//                        return;
//                    }
//                }
//            }
//        }
        // DEL END 2015/11/26 K.Kasai [Unit Test #71]

        // user input
        if (NSAL0150CommonLogic.isMtrRead(cMsg)) {

            // START 2017/03/13 K.Kitachi [QC#15163, DEL]
//            BigDecimal svcPhysMtrReadGrpSq = ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_PHYS_MTR_READ_GRP_SQ");
            // END 2017/03/13 K.Kitachi [QC#15163, DEL]

            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
            int cnt = 0;
            NSZC010001PMsg pMsg = new NSZC010001PMsg();
            // Add Start 2016/10/003 <QC#12274>
            if (ZYPCommonFunc.hasValue(cMsg.svcTaskNum_B)) {
                ZYPEZDItemValueSetter.setValue(pMsg.svcTaskNum, cMsg.svcTaskNum_B);
                ZYPEZDItemValueSetter.setValue(pMsg.dsTestCopyClsCd, cMsg.dsTestCopyClsCd_BS);
                ZYPEZDItemValueSetter.setValue(pMsg.fsrNum, cMsg.fsrNum_B);
                ZYPEZDItemValueSetter.setValue(pMsg.fsrVisitNum, cMsg.fsrVisitNum_B);
            }
            // Add End   2016/10/003 <QC#12274>
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
            for (int i = 0; i < cMsg.B.getValidCount(); i++) {

                // DEL START 2015/11/26 K.Kasai [Unit Test #71]
//                NSZC010001PMsg pMsg = new NSZC010001PMsg();
                // DEL END 2015/11/26 K.Kasai [Unit Test #71]
                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, cMsg.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
                ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, cMsg.B.no(0).dsMtrReadTpCd_B);
                // ADD START 2015/11/26 K.Kasai [Unit Test #71]
                ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpGrpCd, cMsg.dsMtrReadTpGrpCd_BS);
                ZYPEZDItemValueSetter.setValue(pMsg.xxWrnSkipFlg, cMsg.xxWrnSkipFlg);
                // ADD END 2015/11/26 K.Kasai [Unit Test #71]

                // MOD START 2015/11/26 K.Kasai [Unit Test #71]
//                pMsg.A.no(0).physMtrId.clear();
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).mtrReadDt, cMsg.B.no(i).mtrReadDt_B);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).rgtnMtrDt, slsDt);
//                pMsg.A.no(0).svcPhysMtrReadPk.clear();
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).readMtrCnt, cMsg.B.no(i).readMtrCnt_B);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).testMtrCnt, BigDecimal.ZERO);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).rgtnUsrId, userId);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).estFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).invProcFlg, ZYPConstant.FLG_OFF_N);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).svcPhysMtrPk, cMsg.B.no(i).svcPhysMtrPk_B);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).mtrEntryCmntTxt, cMsg.B.no(i).mtrEntryCmntTxt_B);
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(0).vldMtrFlg, ZYPConstant.FLG_ON_Y);
//
//                pMsg.A.setValidCount(1);
//
//                new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
//                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
//                for (S21ApiMessage msg : msgList) {
//                    String msgId = msg.getXxMsgid();
//                    String[] prm = msg.getXxMsgPrmArray();
//                    if (msgId.endsWith("E")) {
//                        cMsg.B.no(i).readMtrCnt_B.setErrorInfo(1, msgId, prm);
//                        cMsg.setMessageInfo(msgId, prm);
//                        return;
//                    }
//                }
                pMsg.A.no(cnt).physMtrId.clear();
                // add start 2017/09/07 QC#15134
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).dsMtrReadTpCd, cMsg.B.no(i).dsMtrReadTpCd_B);
                // add end 2017/09/07 QC#15134
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, cMsg.B.no(i).mtrReadDt_B);
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, slsDt);
                pMsg.A.no(cnt).svcPhysMtrReadPk.clear();
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, cMsg.B.no(i).readMtrCnt_B);
                // Del Start 2016/10/03 <QC#12274>
//                // mod start 2016/03/29 CSA Defect#4396
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, cMsg.B.no(i).testMtrCnt_B);
//                // mod end 2016/03/29 CSA Defect#4396
                // Del End   2016/10/03 <QC#12274>
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, userId);
                //Mod Start 01/29/2016 <QC#3865>
                if (DS_MTR_READ_TP.ESTIMATED.equals(cMsg.B.no(i).dsMtrReadTpCd_B.getValue())) {
                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, ZYPConstant.FLG_OFF_N);
                }
                //Mod End 01/29/2016 <QC#3865>
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, ZYPConstant.FLG_OFF_N);
                // START 2017/03/13 K.Kitachi [QC#15163, DEL]
//                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
                // END 2017/03/13 K.Kitachi [QC#15163, DEL]
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, cMsg.B.no(i).svcPhysMtrPk_B);
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, cMsg.B.no(i).mtrEntryCmntTxt_B);
                ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, ZYPConstant.FLG_ON_Y);
                cnt++;

                pMsg.A.setValidCount(cnt);
                // MOD END 2015/11/26 K.Kasai [Unit Test #71]
            }
            // ADD START 2015/11/26 K.Kasai [Unit Test #71]
            new NSZC010001().execute(pMsg, ONBATCH_TYPE.ONLINE);
            // MOD START 2019/09/05 K.Fujimoto QC#53294
            boolean existWrn = false;
            // START 2019/10/29 K.Fujimoto [QC#54402, ADD]
            boolean existErr = false;
            // END   2019/10/29 K.Fujimoto [QC#54402, ADD]
            List<String> lineMsgList = new ArrayList<String>();
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NSZC010001_APMsg apMsg = pMsg.A.no(i);
                String msgId = apMsg.xxMsgId.getValue();
                lineMsgList.add(msgId);
            }
            
            // Set error/warning msg(Header Level).
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (S21ApiMessage msg : msgList) {
                String msgId = msg.getXxMsgid();
                String[] prm = msg.getXxMsgPrmArray();
                if (msgId.endsWith("E")) {
                    cMsg.setMessageInfo(msgId, prm);
                    ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
                    existErr = true;
                    // START 2019/10/29 K.Fujimoto [QC#54402, DEL]
                    // return;
                    // END 2019/10/29 K.Fujimoto [QC#54402, DEL]
                }
                // ADD START 2015/11/26 K.Kasai [Unit Test #71]
                if (msgId.endsWith("W") && !existErr) {
                    ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_ON_Y);
                    cMsg.setMessageInfo(msgId, prm);
                    // START 2019/10/29 K.Fujimoto [QC#54402, DEL]
                    // START 2018/06/05 K.Kitachi [QC#26048, ADD]
//                    if(!lineMsgList.contains(msgId)) {
//                        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
//                            cMsg.B.no(i).readMtrCnt_B.setErrorInfo(2, msgId, prm);
//                        }
//                    }
                    // END 2018/06/05 K.Kitachi [QC#26048, ADD]
                    // END 2019/10/29 K.Fujimoto [QC#54402, DEL]
                    // return;
                    existWrn = true;
                    // START 2019/10/29 K.Fujimoto [QC#54402, DEL]
                    // break;
                    // END 2019/10/29 K.Fujimoto [QC#54402, DEL]
                }
                // ADD END 2015/11/26 K.Kasai [Unit Test #71]
            }
            // ADD END 2015/11/26 K.Kasai [Unit Test #71]
            // Set warning msg(Line Level).
            for (int i = 0; i < pMsg.A.getValidCount(); i++) {
                NSZC010001_APMsg apMsg = pMsg.A.no(i);
                // START 2019/10/29 K.Fujimoto [QC#54402, ADD]
                setMsgDtl(cMsg, apMsg, existErr);
                // END   2019/10/29 K.Fujimoto [QC#54402, ADD]
                // START 2019/10/29 K.Fujimoto [QC#54402, DEL]
//                String msgId = apMsg.xxMsgId.getValue();
//                if (ZYPCommonFunc.hasValue(msgId)) {
//                    for (int j = 0; j < cMsg.B.getValidCount(); j++) {
//                        NSAL0150_BCMsg bcMsg = cMsg.B.no(j);
//                        if(bcMsg.svcPhysMtrPk_B.getValue().compareTo(apMsg.svcPhysMtrPk.getValue()) == 0) {
//                            bcMsg.readMtrCnt_B.setErrorInfo(2, msgId);
//                        }
//                    }
//                    if (msgId.equals(NSAL0150Constant.NSZM1335W)) {
//                        cMsg.setMessageInfo(msgId);
//                    }
//                }
                // END   2019/10/29 K.Fujimoto [QC#54402, DEL]
            }
            // START 2019/10/29 K.Fujimoto [QC#54402, MOD]
            // if (warningFlg) {
            if (existErr || existWrn) {
            // END   2019/10/29 K.Fujimoto [QC#54402, MOD]
                return;
            }
        }
        // MOD END   2019/09/05 K.Fujimoto QC#53294
        cMsg.setMessageInfo(NSAL0150Constant.NZZM0002I);
        // ADD START 2015/11/26 K.Kasai [Unit Test #71]
        ZYPEZDItemValueSetter.setValue(cMsg.xxWrnSkipFlg, ZYPConstant.FLG_OFF_N);
        // ADD END 2015/11/26 K.Kasai [Unit Test #71]
    }
    
    // DEL START 2015/11/26 K.Kasai [Unit Test #71]
//    private NSZC010001PMsg buildPMsg(BigDecimal svcMachMstrPk) {
//        NSZC010001PMsg pMsg = new NSZC010001PMsg();
//        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, getGlobalCompanyCode());
//        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, NSAL0150CommonLogic.getBizIdSlsDt());
//        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrPk);
//        ZYPEZDItemValueSetter.setValue(pMsg.mtrReadSrcTpCd, MTR_READ_SRC_TP.S21);
//        ZYPEZDItemValueSetter.setValue(pMsg.dsMtrReadTpCd, DS_MTR_READ_TP.PERIODIC_METER_READING);
//        pMsg.fsrNum.clear();
//        pMsg.fsrVisitNum.clear();
//        return pMsg;
//    }
//
//    private void addEstMtr(TreeMap<String, List<SvcPhysMtrReadInfoBean>> mtrReadMap, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
//        List<SvcPhysMtrReadInfoBean> mtrReadGrp = null;
//        if (mtrReadMap.containsKey(mtrReadDt)) {
//            mtrReadGrp = mtrReadMap.get(mtrReadDt);
//        } else {
//            mtrReadGrp = new ArrayList<SvcPhysMtrReadInfoBean>();
//            mtrReadMap.put(mtrReadDt, mtrReadGrp);
//        }
//        SvcPhysMtrReadInfoBean mtrReadInfo = new SvcPhysMtrReadInfoBean();
//        mtrReadInfo.setSvcPhysMtrPk(svcPhysMtrPk);
//        mtrReadInfo.setMtrReadDt(mtrReadDt);
//        mtrReadInfo.setReadMtrCnt(readMtrCnt);
//        mtrReadGrp.add(mtrReadInfo);
//    }
//
//    private void addEstMtr(NSZC010001PMsg pMsg, BigDecimal svcPhysMtrReadGrpSq, BigDecimal svcPhysMtrPk, String mtrReadDt, BigDecimal readMtrCnt) {
//        int i = pMsg.A.getValidCount();
//        pMsg.A.no(i).physMtrId.clear();
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).mtrReadDt, mtrReadDt);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).rgtnMtrDt, NSAL0150CommonLogic.getBizIdSlsDt());
//        pMsg.A.no(i).svcPhysMtrReadPk.clear();
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).readMtrCnt, readMtrCnt);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).testMtrCnt, BigDecimal.ZERO);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).rgtnUsrId, getContextUserInfo().getUserId());
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).estFlg, ZYPConstant.FLG_ON_Y);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).invProcFlg, ZYPConstant.FLG_OFF_N);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).svcPhysMtrReadGrpSq, svcPhysMtrReadGrpSq);
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).svcPhysMtrPk, svcPhysMtrPk);
//        pMsg.A.no(i).mtrEntryCmntTxt.clear();
//        ZYPEZDItemValueSetter.setValue(pMsg.A.no(i).vldMtrFlg, ZYPConstant.FLG_ON_Y);
//        pMsg.A.setValidCount(i + 1);
//    }
    // DEL END 2015/11/26 K.Kasai [Unit Test #71]

    // START 2019/10/29 K.Fujimoto [QC#54402, ADD]
    private void setMsgDtl(NSAL0150CMsg cMsg, NSZC010001_APMsg apMsg, boolean existErr) {
        String msgId = apMsg.xxMsgId.getValue();
        if (!ZYPCommonFunc.hasValue(msgId) || msgId.endsWith("I")) {
            return;
        }
        boolean isErr = false;
        if (msgId.endsWith("E")) {
            isErr = true;
        }
        for (int j = 0; j < cMsg.B.getValidCount(); j++) {
            NSAL0150_BCMsg bcMsg = cMsg.B.no(j);
            if(bcMsg.svcPhysMtrPk_B.getValue().compareTo(apMsg.svcPhysMtrPk.getValue()) == 0) {
                if (isErr) {
                    bcMsg.readMtrCnt_B.setErrorInfo(1, msgId);
                    break;
                }
                if (msgId.equals(NSAL0150Constant.NSZM1335W)) {
                    bcMsg.readMtrCnt_B.setErrorInfo(2, msgId);
                }
                if (bcMsg.readMtrCnt_B.getErrorCode() == 0) {
                    bcMsg.readMtrCnt_B.setErrorInfo(2, msgId);
                }
            }
        }
        if (!existErr && msgId.equals(NSAL0150Constant.NSZM1335W)) {
            cMsg.setMessageInfo(msgId);
        }
    }
    // END   2019/10/29 K.Fujimoto [QC#54402, ADD]
    // add start 2017/09/07 QC#15134
    private String getMtrEntryCmntTxt(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {
        SVC_PHYS_MTR_READTMsg tMsg = NSAL0150Query.getInstance().getSvcPhysMtrRead(glblCmpyCd, svcPhysMtrReadPk);
        if (tMsg == null) {
            return null;
        }
        return tMsg.mtrEntryCmntTxt.getValue();
    }
    // add end 2017/09/07 QC#15134

    // add start 2020/02/07 QC#55812
    private void addLackMtrToPMsg(String glblCmpyCd, BigDecimal svcPhysMtrReadGrpSq, String vldMtrFlg, NSZC010001PMsg pMsg) {
        SVC_PHYS_MTR_READTMsgArray tMsgArray = NSAL0150Query.getInstance().getSvcPhysMtrReadByGrpSq(glblCmpyCd, svcPhysMtrReadGrpSq);
        int cnt = pMsg.A.getValidCount();

        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            SVC_PHYS_MTR_READTMsg tMsg = tMsgArray.no(i);
            boolean existsFlg = false;
            for (int j = 0; j < pMsg.A.getValidCount(); j++) {
                if (tMsg.svcPhysMtrReadPk.getValue().equals(pMsg.A.no(j).svcPhysMtrReadPk.getValue())) {
                    existsFlg = true;
                    break;
                }
            }
            if (existsFlg) {
                continue;
            }
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrReadPk, tMsg.svcPhysMtrReadPk);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnMtrDt, tMsg.rgtnMtrDt);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).readMtrCnt, tMsg.readMtrCnt);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).testMtrCnt, tMsg.testMtrCnt);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrReadDt, tMsg.mtrReadDt);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).invProcFlg, tMsg.invProcFlg);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).rgtnUsrId, tMsg.rgtnUsrId);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).estFlg, tMsg.estFlg);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrLbCd, tMsg.mtrLbCd);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrClsTpCd, tMsg.mtrClsTpCd);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).dsMdlMtrPk, tMsg.dsMdlMtrPk);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).svcPhysMtrPk, tMsg.svcPhysMtrPk);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).mtrEntryCmntTxt, tMsg.mtrEntryCmntTxt);
            ZYPEZDItemValueSetter.setValue(pMsg.A.no(cnt).vldMtrFlg, vldMtrFlg);
            cnt++;
        }
        pMsg.A.setValidCount(cnt);
    }
    // add end 2020/02/07 QC#55812
}

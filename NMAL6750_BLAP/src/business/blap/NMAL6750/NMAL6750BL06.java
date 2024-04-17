/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL6750;

import static business.blap.NMAL6750.constant.NMAL6750Constant.DS_CTAC_PNT;
import static business.blap.NMAL6750.constant.NMAL6750Constant.DS_CTAC_PSN_RELN;
import static business.blap.NMAL6750.constant.NMAL6750Constant.MAX_EFF_THRU_DT;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM0208E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8105E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8111E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8333I;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8368E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NMAM8673E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.NZZM0003E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.ZZZM9004E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.ZZZM9012E;
import static business.blap.NMAL6750.constant.NMAL6750Constant.ZZZM9013E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDSBigDecimalItem;
import parts.common.EZDSMsg;
import parts.common.EZDSStringItem;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CTAC_PNTTMsg;
import business.db.DS_CTAC_PSN_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * NMAL6750BL06
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/05   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/05   Fujitsu         C.Tanaka        Update          CSA
 * 2016/02/16   Fujitsu         C.Tanaka        Update          QC#4345
 * 2016/02/25   Fujitsu         C.Tanaka        Update          QC#4337
 * 2016/03/02   Fujitsu         C.Tanaka        Update          QC#4779
 * 2016/04/20   SRAA            Y.Chen          Update          QC#6183
 * 2017/08/10   Fujitsu         H.Nagashima     Update          QC#8598
 * 2017/08/29   Fujitsu         N.Sugiura       Update          QC#20770
 * 2017/10/26   Fujitsu         M.Ohno          Update          QC#21996
 * 2017/12/18   Fujitsu         Hd.Sugawara     Update          QC#22286
 * 2018/07/31   Fujitsu         H.Tomimatsu     Update          S21_NA#26565
 * 2019/06/05   Fujitsu         S.Kosaka        Update          QC#50651
 * </pre>
 */
public class NMAL6750BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL6750CMsg bizMsg = (NMAL6750CMsg) cMsg;
            NMAL6750SMsg glblMsg = (NMAL6750SMsg) sMsg;

            if ("NMAL6750Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NPAL6750_CMN_Submit(bizMsg, glblMsg);
            } else {
                return;
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NPAL6750_CMN_Submit(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        if (!checkInputChange(cMsg, sMsg)) {
            cMsg.setMessageInfo(NMAM8333I);
            return;
        }

        // QC#8598 add Start
        if (!ContactPointMandatoryCheck(cMsg, sMsg)) {
            return;
        }
        // QC#8598 add End


        String glblCmpyCd = getGlobalCompanyCode();
// QC#20770 Del Start
//        String effThruDt = "99991231";
//        NMAL6750_ACMsg aCMsg;
//        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            aCMsg = cMsg.A.no(i);
//            if (ZYPCommonFunc.hasValue(aCMsg.effThruDt_A1)) {
//                effThruDt = aCMsg.effThruDt_A1.getValue();
//            }
//            Map<String, Object> queryParam = new HashMap<String, Object>();
//            queryParam.put("glblCmpyCd", glblCmpyCd);
//            queryParam.put("dsAcctNum", aCMsg.dsAcctNum_A1.getValue());
//            queryParam.put("locNum", aCMsg.locNum_A1.getValue());
//            queryParam.put("dsPrimLocFlg", ZYPConstant.FLG_ON_Y);
//            queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
//            queryParam.put("effFromDt", aCMsg.effFromDt_A1.getValue());
//            queryParam.put("effThruDt", effThruDt);
//            queryParam.put("rowNum", 1);
//
//            S21SsmEZDResult ssmResult = getQuery().getOtherPrimCtac(queryParam);
//
//            if (!ssmResult.isCodeNormal()) {
//            } else {
//                if (ZYPConstant.FLG_ON_Y.equals(aCMsg.dsPrimLocFlg_A1.getValue())) {
//                    if (!ZYPConstant.FLG_ON_1.equals(cMsg.xxPgFlg.getValue())) {
//                        cMsg.xxPgFlg.setValue(ZYPConstant.FLG_ON_1);
//                        cMsg.setMessageInfo(NMAM8261W);
//                        return;
//                    } else {
//                        Map<String, Object> requestMap = (Map<String, Object>) ssmResult.getResultObject();
//
//                        DS_CTAC_PSN_RELNTMsg primCtacRelnTMsg = new DS_CTAC_PSN_RELNTMsg();
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.glblCmpyCd, glblCmpyCd);
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.dsCtacPsnRelnPk, (BigDecimal) requestMap.get("DS_CTAC_PSN_RELN_PK"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.ctacPsnPk, (BigDecimal) requestMap.get("CTAC_PSN_PK"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.dsAcctNum, (String) requestMap.get("DS_ACCT_NUM"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.locNum, (String) requestMap.get("LOC_NUM"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.effFromDt, (String) requestMap.get("EFF_FROM_DT"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.effThruDt, (String) requestMap.get("EFF_THRU_DT"));
//                        ZYPEZDItemValueSetter.setValue(primCtacRelnTMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
//
//                        S21FastTBLAccessor.update(primCtacRelnTMsg);
//                        if (!RTNCD_NORMAL.equals(primCtacRelnTMsg.getReturnCode())) {
//                            cMsg.setMessageInfo(ZZZM9013E, new String[] {primCtacRelnTMsg.getReturnCode() });
//                            return;
//                        }
//                    }
//                }
//            }
//        }
// QC#20770 Del End

        CTAC_PSNTMsg ctacPsnTMsg = new CTAC_PSNTMsg();
        ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.glblCmpyCd, glblCmpyCd);

        //QC#8598 add Start
        //Search contact Person by account/location,Name
        BigDecimal ctacPsnPk = cMsg.ctacPsnPk_H1.getValue();
        Map<String, Object> queryParam = new HashMap<String, Object>();
        S21SsmEZDResult ssmResult;
        if (!ZYPCommonFunc.hasValue(ctacPsnPk)) {
            queryParam.put("glblCmpyCd", getGlobalCompanyCode());
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(0).locNum_A1)) {
                queryParam.put("dsAcctNum", cMsg.A.no(0).dsAcctNum_A1.getValue());
            } else {
                queryParam.put("locNum", cMsg.A.no(0).locNum_A1.getValue());
            }
            queryParam.put("ctacPsnFirstNm", cMsg.ctacPsnFirstNm_H1.getValue().toUpperCase());
            queryParam.put("ctacPsnLastNm", cMsg.ctacPsnLastNm_H1.getValue().toUpperCase());
            queryParam.put("slsDt", ZYPDateUtil.getSalesDate());
            queryParam.put("maxEffThruDt", MAX_EFF_THRU_DT);
            queryParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
            // 2019/06/05 QC#50651 Add Start
            queryParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
            // 2019/06/05 QC#50651 Add End
            ssmResult = NMAL6750Query.getInstance().searchContactByName(queryParam);
            if (ssmResult.isCodeNormal()) {
                // 2017/10/26 QC#21996 mod start
//                ctacPsnPk = (BigDecimal) ssmResult.getResultObject();
                cMsg.setMessageInfo(NMAM8673E);
                return;
                // 2017/10/26 QC#21996 mod end
            }
        }
        //QC#8598 add End

        //QC#8598 mod Start
//        if (!ZYPCommonFunc.hasValue(cMsg.ctacPsnNum_H1)) {
        if (!ZYPCommonFunc.hasValue(ctacPsnPk)) {
//            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CTAC_PSN_SQ"));
            ctacPsnPk = ZYPOracleSeqAccessor.getNumberBigDecimal("CTAC_PSN_SQ");
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPk, ctacPsnPk);
            //QC#8598 mod End
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnFirstNm, cMsg.ctacPsnFirstNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnLastNm, cMsg.ctacPsnLastNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnCmntTxt, cMsg.ctacPsnCmntTxt_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPvtFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnRcvMlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnScrTrgtFlg, ZYPConstant.FLG_OFF_N);

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.ctacPsnActvFlg_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnActvFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnDeptCd, cMsg.dsCtacPsnDeptCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnSaltCd, cMsg.dsCtacPsnSaltCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnTtlCd, cMsg.dsCtacPsnTtlCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsPrimCtacTpCd, cMsg.dsCtacPntTpCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnNum, ctacPsnTMsg.ctacPsnPk.getValue().toString());

            S21FastTBLAccessor.insert(ctacPsnTMsg);
            if (!RTNCD_NORMAL.equals(ctacPsnTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9012E, new String[] {ctacPsnTMsg.getReturnCode() });
                return;
            }

            ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNum_P1, ctacPsnTMsg.ctacPsnNum.getValue());

        } else {
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnPk, cMsg.ctacPsnPk_H1.getValue());
            ctacPsnTMsg = (CTAC_PSNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(ctacPsnTMsg);
            if (ctacPsnTMsg == null) {
                cMsg.setMessageInfo(NMAM8105E);
                return;
            }

            if (!ZYPDateUtil.isSameTimeStamp(cMsg.ezUpTime.getValue(), cMsg.ezUpTimeZone.getValue(), ctacPsnTMsg.ezUpTime.getValue(), ctacPsnTMsg.ezUpTimeZone.getValue())) {
                cMsg.setMessageInfo(ZZZM9004E);
                return;
            }

            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnFirstNm, cMsg.ctacPsnFirstNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnLastNm, cMsg.ctacPsnLastNm_H1.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnCmntTxt, cMsg.ctacPsnCmntTxt_H1.getValue());

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.ctacPsnActvFlg_H1.getValue())) {
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnActvFlg, ZYPConstant.FLG_ON_Y);
            } else {
                ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.ctacPsnActvFlg, ZYPConstant.FLG_OFF_N);
            }

            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnDeptCd, cMsg.dsCtacPsnDeptCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnSaltCd, cMsg.dsCtacPsnSaltCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsCtacPsnTtlCd, cMsg.dsCtacPsnTtlCd_H2.getValue());
            ZYPEZDItemValueSetter.setValue(ctacPsnTMsg.dsPrimCtacTpCd, cMsg.dsCtacPntTpCd_H2.getValue());

            S21FastTBLAccessor.update(ctacPsnTMsg);
            if (!RTNCD_NORMAL.equals(ctacPsnTMsg.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {ctacPsnTMsg.getReturnCode() });
                return;
            }
        }

        // QC#8598 mod Start
//        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.glblCmpyCd, glblCmpyCd);
        List<DS_CTAC_PSN_RELNTMsg> delTmsgList = new ArrayList<DS_CTAC_PSN_RELNTMsg>();

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
//            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).dsCtacPsnRelnPk_A1)) {
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, cMsg.A.no(i).dsCtacPsnRelnPk_A1.getValue());
//                dsCtacPsnReln = (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKey(dsCtacPsnReln);
//                if (dsCtacPsnReln == null) {
//                    cMsg.setMessageInfo(NMAM8105E);
//                    return;
//                }
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effFromDt, cMsg.A.no(i).effFromDt_A1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effThruDt, cMsg.A.no(i).effThruDt_A1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacTpCd, cMsg.ctacTpCd_H2.getValue());
//                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).dsPrimLocFlg_A1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
//                }
//
//                S21FastTBLAccessor.update(dsCtacPsnReln);
//                if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
//                    cMsg.setMessageInfo(ZZZM9013E, new String[] {dsCtacPsnReln.getReturnCode() });
//                    return;
//                }
//            } else {
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CTAC_PSN_RELN_SQ"));
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacPsnPk, ctacPsnTMsg.ctacPsnPk.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.locNum, cMsg.A.no(i).locNum_A1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effFromDt, cMsg.A.no(i).effFromDt_A1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effThruDt, cMsg.A.no(i).effThruDt_A1.getValue());
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacTpCd, cMsg.ctacTpCd_H2.getValue());
//                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNum_A1)) {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsAcctNum, cMsg.A.no(i).dsAcctNum_A1.getValue());
//                }
//                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(i).dsPrimLocFlg_A1.getValue())) {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
//                } else {
//                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
//                }
//
//                S21FastTBLAccessor.insert(dsCtacPsnReln);
//                if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
//                    cMsg.setMessageInfo(ZZZM9012E, new String[] {dsCtacPsnReln.getReturnCode() });
//                    return;
//                }
//            }

            for (int j = 0; j < cMsg.R.getValidCount(); j++) {
                DS_CTAC_PSN_RELNTMsg dsCtacPsnReln = new DS_CTAC_PSN_RELNTMsg();
                ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.glblCmpyCd, glblCmpyCd);

                String ctacTpCd = cMsg.R.no(j).ctacTpCd_R1.getValue();
                String xxChk = cMsg.R.no(j).xxChkBox_R1.getValue();

                // get DS_CTAC_PSN_RELN_PK
                BigDecimal dsCtacPsnRelnPk = null;
                queryParam = new HashMap<String, Object>();
                queryParam.put("glblCmpyCd", getGlobalCompanyCode());
                if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNum_A1)) {
                    queryParam.put("dsAcctNum", cMsg.A.no(i).dsAcctNum_A1.getValue());
                } else {
                    queryParam.put("locNum", cMsg.A.no(i).locNum_A1.getValue());
                }
                queryParam.put("ctacPsnPk", ctacPsnTMsg.ctacPsnPk.getValue());
                queryParam.put("ctacTpCd", ctacTpCd);

                ssmResult = NMAL6750Query.getInstance().getContactRelationPK(queryParam);
                if (ssmResult.isCodeNormal()) {
                    dsCtacPsnRelnPk = (BigDecimal) ssmResult.getResultObject();
                }

                if (dsCtacPsnRelnPk != null) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
                    dsCtacPsnReln = (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKey(dsCtacPsnReln);

                }
                if (ZYPConstant.FLG_ON_Y.equals(xxChk)) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacPsnPk, ctacPsnTMsg.ctacPsnPk.getValue());
                    if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).locNum_A1)) {
                        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsAcctNum, cMsg.A.no(i).dsAcctNum_A1.getValue());
                    }
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.locNum, cMsg.A.no(i).locNum_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effFromDt, cMsg.A.no(i).effFromDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effThruDt, cMsg.A.no(i).effThruDt_A1.getValue());
                    ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacTpCd, ctacTpCd);
                    // QC#20770 Del Start
                    // if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.A.no(0).dsPrimLocFlg_A1.getValue())) {
                    //     ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_ON_Y);
                    // } else {
                    //     ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                    // }
                    // QC#20770 Del End
                    if (dsCtacPsnRelnPk == null) {
                        // insert
                        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CTAC_PSN_RELN_SQ"));
                        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
                        S21FastTBLAccessor.insert(dsCtacPsnReln);
                        if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
                            cMsg.setMessageInfo(ZZZM9012E, new String[] {dsCtacPsnReln.getReturnCode() });
                            return;
                        }

                    } else {
                        // update
                        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
                        S21FastTBLAccessor.update(dsCtacPsnReln);
                        if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
                            cMsg.setMessageInfo(ZZZM9013E, new String[] {dsCtacPsnReln.getReturnCode() });
                            return;
                        }
                    }

                } else {
                    if (dsCtacPsnRelnPk != null) {
                        // delete
                        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
                        delTmsgList.add(dsCtacPsnReln);
                    }
                }
            }
            // QC#8598 mod End

            // Add Start 2017/12/18 QC#22286
            // Insert or update DsCtacPsnReln(DELIVERY / INSTALL)
            boolean result = deliverDeliveryInstall(cMsg, cMsg.A.no(i), glblCmpyCd, ctacPsnTMsg);
            if (!result) {
                return;
            }
            // Add End 2017/12/18 QC#22286
        }
        //QC#8598 add Start
        DS_CTAC_PSN_RELNTMsg[] dsCtacPsnRelnTmsgs;
        int cnt;
        // delete
        if (!delTmsgList.isEmpty()) {
            dsCtacPsnRelnTmsgs = new DS_CTAC_PSN_RELNTMsg[delTmsgList.size()];
            for (int i = 0; i < delTmsgList.size(); i++) {
                DS_CTAC_PSN_RELNTMsg tmsg = delTmsgList.get(i);
                dsCtacPsnRelnTmsgs[i] = tmsg;
            }
            cnt = S21FastTBLAccessor.removeLogical(dsCtacPsnRelnTmsgs);
            if (cnt != delTmsgList.size()) {
                cMsg.setMessageInfo(NMAM0208E, new String[] {DS_CTAC_PSN_RELN });
                return;
            }
        }
        //QC#8598 add End

        //QC#8598 del Start
//        delCtacPsnReln(cMsg, sMsg);
        //QC#8598 del End

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            DS_CTAC_PNTTMsg dsCtacPnt = new DS_CTAC_PNTTMsg();
            ZYPEZDItemValueSetter.setValue(dsCtacPnt.glblCmpyCd, glblCmpyCd);

            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).dsCtacPntPk_B1)) {
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntPk, cMsg.B.no(i).dsCtacPntPk_B1.getValue());

                dsCtacPnt = (DS_CTAC_PNTTMsg) S21FastTBLAccessor.findByKey(dsCtacPnt);
                if (dsCtacPnt == null) {
                    cMsg.setMessageInfo(NMAM8105E);
                    return;
                }

                // QC#4350
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntTpCd, cMsg.B.no(i).dsCtacPntTpCd_B1.getValue());

                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntValTxt, cMsg.B.no(i).dsCtacPntValTxt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPsnExtnNum, cMsg.B.no(i).dsCtacPsnExtnNum_B1.getValue());

                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).dsOpsOutFlg_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsOpsOutFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).dsCtacPntActvFlg_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntActvFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.ctacPntFmtTxt, cMsg.B.no(i).ctacPntFmtTxt_B1.getValue());  // QC#8598 add

                S21FastTBLAccessor.update(dsCtacPnt);
                if (!RTNCD_NORMAL.equals(dsCtacPnt.getReturnCode())) {
                    cMsg.setMessageInfo(ZZZM9013E, new String[] {dsCtacPnt.getReturnCode() });
                    return;
                }
            } else {
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CTAC_PNT_SQ"));
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.ctacPsnPk, ctacPsnTMsg.ctacPsnPk.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntTpCd, cMsg.B.no(i).dsCtacPntTpCd_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntValTxt, cMsg.B.no(i).dsCtacPntValTxt_B1.getValue());
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPsnExtnNum, cMsg.B.no(i).dsCtacPsnExtnNum_B1.getValue());

                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).dsOpsOutFlg_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsOpsOutFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsOpsOutFlg, ZYPConstant.FLG_OFF_N);
                }
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.B.no(i).dsCtacPntActvFlg_B1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntActvFlg, ZYPConstant.FLG_ON_Y);
                } else {
                    ZYPEZDItemValueSetter.setValue(dsCtacPnt.dsCtacPntActvFlg, ZYPConstant.FLG_OFF_N);
                }
                ZYPEZDItemValueSetter.setValue(dsCtacPnt.ctacPntFmtTxt, cMsg.B.no(i).ctacPntFmtTxt_B1.getValue());  // QC#8598 add

                S21FastTBLAccessor.insert(dsCtacPnt);
                if (!RTNCD_NORMAL.equals(dsCtacPnt.getReturnCode())) {
                    cMsg.setMessageInfo(ZZZM9012E, new String[] {dsCtacPnt.getReturnCode() });
                    return;
                }
            }
        }

        delCtacPnt(cMsg, sMsg);

        // QC#8598 add Start
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNum_P1, ctacPsnTMsg.ctacPsnNum);
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnPk_H1, ctacPsnTMsg.ctacPsnPk);
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnNum_H1, ctacPsnTMsg.ctacPsnNum);
        // QC#8598 add End
    }

    // Add Start 2017/12/18 QC#22286
    /**
     * @param cMsg NMAL6750CMsg
     * @param aCMsg NMAL6750_ACMsg
     * @param glblCmpyCd String
     * @param ctacPsnTMsg CTAC_PSNTMsg
     * @return boolean
     */
    private boolean deliverDeliveryInstall(NMAL6750CMsg cMsg, NMAL6750_ACMsg aCMsg, String glblCmpyCd, CTAC_PSNTMsg ctacPsnTMsg) {
        boolean result = false;
        String ctacTpCd = CTAC_TP.DELIVERY_INSTALL;
        DS_CTAC_PSN_RELNTMsg dsCtacPsnReln = new DS_CTAC_PSN_RELNTMsg();

        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.glblCmpyCd, glblCmpyCd);

        // get DS_CTAC_PSN_RELN_PK
        BigDecimal dsCtacPsnRelnPk = getDsCtacPsnRelnPk(aCMsg, glblCmpyCd, ctacPsnTMsg, ctacTpCd);

        if (dsCtacPsnRelnPk != null) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, dsCtacPsnRelnPk);
            dsCtacPsnReln = (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKey(dsCtacPsnReln);
        }

        result = deliverDsCtacPsnReln(cMsg, aCMsg, glblCmpyCd, ctacPsnTMsg, dsCtacPsnReln, ctacTpCd, dsCtacPsnRelnPk);

        return result;
    }

    /**
     * @param aCMsg NMAL6750_ACMsg
     * @param glblCmpyCd String
     * @param ctacPsnTMsg CTAC_PSNTMsg
     * @param ctacTpCd String
     * @return BigDecimal
     */
    private BigDecimal getDsCtacPsnRelnPk(NMAL6750_ACMsg aCMsg, String glblCmpyCd, CTAC_PSNTMsg ctacPsnTMsg, String ctacTpCd) {
        BigDecimal dsCtacPsnRelnPk = null;
        HashMap<String, Object> queryParam = new HashMap<String, Object>();

        queryParam.put("glblCmpyCd", glblCmpyCd);

        if (!ZYPCommonFunc.hasValue(aCMsg.locNum_A1)) {
            queryParam.put("dsAcctNum", aCMsg.dsAcctNum_A1.getValue());
        } else {
            queryParam.put("locNum", aCMsg.locNum_A1.getValue());
        }

        queryParam.put("ctacPsnPk", ctacPsnTMsg.ctacPsnPk.getValue());
        queryParam.put("ctacTpCd", ctacTpCd);

        S21SsmEZDResult ssmResult = NMAL6750Query.getInstance().getContactRelationPK(queryParam);
        if (ssmResult.isCodeNormal()) {
            dsCtacPsnRelnPk = (BigDecimal) ssmResult.getResultObject();
        }

        return dsCtacPsnRelnPk;
    }

    /**
     * @param cMsg NMAL6750CMsg
     * @param aCMsg NMAL6750_ACMsg
     * @param glblCmpyCd String
     * @param ctacPsnTMsg CTAC_PSNTMsg
     * @param ctacTpCd String
     * @param dsCtacPsnRelnPk BigDecimal
     * @return boolean
     */
    private boolean deliverDsCtacPsnReln(NMAL6750CMsg cMsg, NMAL6750_ACMsg aCMsg, String glblCmpyCd, CTAC_PSNTMsg ctacPsnTMsg, DS_CTAC_PSN_RELNTMsg dsCtacPsnReln, String ctacTpCd, BigDecimal dsCtacPsnRelnPk) {
        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacPsnPk, ctacPsnTMsg.ctacPsnPk.getValue());

        if (!ZYPCommonFunc.hasValue(aCMsg.locNum_A1)) {
            ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsAcctNum, aCMsg.dsAcctNum_A1.getValue());
        }

        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.locNum, aCMsg.locNum_A1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effFromDt, aCMsg.effFromDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.effThruDt, aCMsg.effThruDt_A1.getValue());
        ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.ctacTpCd, ctacTpCd);

        if (dsCtacPsnRelnPk == null) {
            // insert
            ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("DS_CTAC_PSN_RELN_SQ"));
            ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);

            S21FastTBLAccessor.insert(dsCtacPsnReln);
            if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9012E, new String[] {dsCtacPsnReln.getReturnCode() });
                return false;
            }
        } else {
            // update
            ZYPEZDItemValueSetter.setValue(dsCtacPsnReln.dsCtacPsnRelnPk, dsCtacPsnRelnPk);

            S21FastTBLAccessor.update(dsCtacPsnReln);
            if (!RTNCD_NORMAL.equals(dsCtacPsnReln.getReturnCode())) {
                cMsg.setMessageInfo(ZZZM9013E, new String[] {dsCtacPsnReln.getReturnCode() });
                return false;
            }
        }

        return true;
    }
    // Add End 2017/12/18 QC#22286

//QC#8598 del Start
//    private void delCtacPsnReln(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {
//
//        int len = sMsg.X.getValidCount();
//        if (len > 0) {
//            DS_CTAC_PSN_RELNTMsg[] dsCtacPsnRelnArr = new DS_CTAC_PSN_RELNTMsg[len];
//            DS_CTAC_PSN_RELNTMsg dsCtacPsnRelnTMsg = new DS_CTAC_PSN_RELNTMsg();
//            ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTMsg.glblCmpyCd, getGlobalCompanyCode());
//            NMAL6750_XSMsg xSMsg;
//            for (int i = 0; i < sMsg.X.getValidCount(); i++) {
//                xSMsg = sMsg.X.no(i);
//                ZYPEZDItemValueSetter.setValue(dsCtacPsnRelnTMsg.dsCtacPsnRelnPk, xSMsg.dsCtacPsnRelnPk_X1.getValue());
//                dsCtacPsnRelnTMsg = (DS_CTAC_PSN_RELNTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCtacPsnRelnTMsg);
//
//                if (dsCtacPsnRelnTMsg == null) {
//                    cMsg.setMessageInfo(NMAM8111E, new String[] {DS_CTAC_PSN_RELN });
//                    return;
//                }
//
//                if (!ZYPDateUtil.isSameTimeStamp(xSMsg.ezUpTime_X1.getValue(), xSMsg.ezUpTimeZone_X1.getValue(), dsCtacPsnRelnTMsg.ezUpTime.getValue(), dsCtacPsnRelnTMsg.ezUpTimeZone.getValue())) {
//                    cMsg.setMessageInfo(NZZM0003E);
//                    return;
//                }
//
//                dsCtacPsnRelnArr[i] = dsCtacPsnRelnTMsg;
//            }
//
//            int result = S21FastTBLAccessor.removeLogical(dsCtacPsnRelnArr);
//            if (result != len) {
//                cMsg.setMessageInfo(NMAM0208E, new String[] {DS_CTAC_PSN_RELN });
//                return;
//            }
//        }
//    }
//QC#8598 del Start

    private void delCtacPnt(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        int len = sMsg.Y.getValidCount();
        if (len > 0) {
            DS_CTAC_PNTTMsg[] dsCtacPntArr = new DS_CTAC_PNTTMsg[len];
            DS_CTAC_PNTTMsg dsCtacPntTMsg = new DS_CTAC_PNTTMsg();
//            ZYPEZDItemValueSetter.setValue(dsCtacPntTMsg.glblCmpyCd, getGlobalCompanyCode());
            NMAL6750_YSMsg ySMsg;
            for (int i = 0; i < sMsg.Y.getValidCount(); i++) {
                ySMsg = sMsg.Y.no(i);
                dsCtacPntTMsg = new DS_CTAC_PNTTMsg();
                ZYPEZDItemValueSetter.setValue(dsCtacPntTMsg.glblCmpyCd, getGlobalCompanyCode());
                ZYPEZDItemValueSetter.setValue(dsCtacPntTMsg.dsCtacPntPk, ySMsg.dsCtacPntPk_Y1.getValue());
                dsCtacPntTMsg = (DS_CTAC_PNTTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(dsCtacPntTMsg);

                if (dsCtacPntTMsg == null) {
                    cMsg.setMessageInfo(NMAM8111E, new String[] {DS_CTAC_PNT });
                    return;
                }

                if (!ZYPDateUtil.isSameTimeStamp(ySMsg.ezUpTime_Y1.getValue(), ySMsg.ezUpTimeZone_Y1.getValue(), dsCtacPntTMsg.ezUpTime.getValue(), dsCtacPntTMsg.ezUpTimeZone.getValue())) {
                    cMsg.setMessageInfo(NZZM0003E);
                    return;
                }

                dsCtacPntArr[i] = dsCtacPntTMsg;
            }

            int result = S21FastTBLAccessor.removeLogical(dsCtacPntArr);
            if (result != len) {
                cMsg.setMessageInfo(NMAM0208E, new String[] {DS_CTAC_PNT });
                return;
            }
        }
    }

    private static NMAL6750Query getQuery() {

        return NMAL6750Query.getInstance();
    }

    private boolean checkInputChange(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        if (!cMsg.ctacPsnFirstNm_H1.getValue().equals(sMsg.ctacPsnFirstNm_H1.getValue())) {
            return true;
        }
        if (!cMsg.ctacPsnLastNm_H1.getValue().equals(sMsg.ctacPsnLastNm_H1.getValue())) {
            return true;
        }
        if (!cMsg.dsCtacPsnSaltCd_H2.getValue().equals(sMsg.dsCtacPsnSaltCd_H2.getValue())) {
            return true;
        }
        if (!cMsg.dsCtacPsnDeptCd_H2.getValue().equals(sMsg.dsCtacPsnDeptCd_H2.getValue())) {
            return true;
        }
        if (!cMsg.dsCtacPsnTtlCd_H2.getValue().equals(sMsg.dsCtacPsnTtlCd_H2.getValue())) {
            return true;
        }
        if (!cMsg.ctacTpCd_H2.getValue().equals(sMsg.ctacTpCd_H2.getValue())) {
            return true;
        }
        if (!isEqualFlag(cMsg.ctacPsnActvFlg_H1, sMsg.ctacPsnActvFlg_H1)) {
            return true;
        }
        if (!cMsg.dsCtacPntTpCd_H2.getValue().equals(sMsg.dsCtacPntTpCd_H2.getValue())) {
            return true;
        }

        if (!cMsg.ctacPsnCmntTxt_H1.getValue().equals(sMsg.ctacPsnCmntTxt_H1.getValue())) {
            return true;
        }

        if (cMsg.A.getValidCount() != sMsg.C.getValidCount()) {
            return true;
        }

        if (cMsg.B.getValidCount() != sMsg.D.getValidCount()) {
            return true;
        }

        NMAL6750_ACMsg aCMsg;
        NMAL6750_CSMsg cSMsg;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            aCMsg = cMsg.A.no(i);
            cSMsg = sMsg.C.no(i);

            if (!isEqualBigDecimal(aCMsg.dsCtacPsnRelnPk_A1, cSMsg.dsCtacPsnRelnPk_A1)) {
                return true;
            }
            if (!aCMsg.dsAcctNm_A1.getValue().equals(cSMsg.dsAcctNm_A1.getValue())) {
                return true;
            }
            if (!aCMsg.dsAcctNum_A1.getValue().equals(cSMsg.dsAcctNum_A1.getValue())) {
                return true;
            }
            if (!aCMsg.locNum_A1.getValue().equals(cSMsg.locNum_A1.getValue())) {
                return true;
            }
            if (!aCMsg.firstLineAddr_A1.getValue().equals(cSMsg.firstLineAddr_A1.getValue())) {
                return true;
            }
            if (!aCMsg.scdLineAddr_A1.getValue().equals(cSMsg.scdLineAddr_A1.getValue())) {
                return true;
            }
            if (!aCMsg.ctyAddr_A1.getValue().equals(cSMsg.ctyAddr_A1.getValue())) {
                return true;
            }
            if (!aCMsg.stCd_A1.getValue().equals(cSMsg.stCd_A1.getValue())) {
                return true;
            }
            if (!aCMsg.postCd_A1.getValue().equals(cSMsg.postCd_A1.getValue())) {
                return true;
            }
            if (!aCMsg.effFromDt_A1.getValue().equals(cSMsg.effFromDt_A1.getValue())) {
                return true;
            }
            if (!aCMsg.effThruDt_A1.getValue().equals(cSMsg.effThruDt_A1.getValue())) {
                return true;
            }
            // QC#20770 Del Start
            // if (!isEqualFlag(aCMsg.dsPrimLocFlg_A1, cSMsg.dsPrimLocFlg_A1)) {
            //     return true;
            // }
            // QC#20770 Del End
        }

        NMAL6750_BCMsg bCMsg;
        NMAL6750_DSMsg dSMsg;
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            bCMsg = cMsg.B.no(i);
            dSMsg = sMsg.D.no(i);

            if (!isEqualBigDecimal(bCMsg.dsCtacPntPk_B1, dSMsg.dsCtacPntPk_B1)) {
                return true;
            }
            if (!bCMsg.dsCtacPntTpCd_B1.getValue().equals(dSMsg.dsCtacPntTpCd_B1.getValue())) {
                return true;
            }
            // QC#8598 add Start
            if (!bCMsg.ctacPntFmtTxt_B1.getValue().equals(dSMsg.ctacPntFmtTxt_B1.getValue())) {
                return true;
            }
            // QC#8598 add End
            if (!bCMsg.dsCtacPntValTxt_B1.getValue().equals(dSMsg.dsCtacPntValTxt_B1.getValue())) {
                return true;
            }
            if (!bCMsg.dsCtacPsnExtnNum_B1.getValue().equals(dSMsg.dsCtacPsnExtnNum_B1.getValue())) {
                return true;
            }
            if (!isEqualFlag(bCMsg.dsOpsOutFlg_B1, dSMsg.dsOpsOutFlg_B1)) {
                return true;
            }
            if (!bCMsg.dsCtacPntActvFlg_B1.getValue().equals(dSMsg.dsCtacPntActvFlg_B1.getValue())) {
                return true;
            }
        }

        // QC#8598 add Start
        NMAL6750_RCMsg rCMsg;
        NMAL6750_SSMsg sSMsg;
        for (int i = 0; i < cMsg.R.getValidCount(); i++) {
            rCMsg = cMsg.R.no(i);
            sSMsg = sMsg.S.no(i);
            if (!isEqualFlag(rCMsg.xxChkBox_R1, sSMsg.xxChkBox_R1)) {
                return true;
            }
        }
        // QC#8598 add End

        // Add START 2018/07/31 H.Tomimatsu S21_NA#26565
        if (!isExistContactRelation(cMsg)) {
            return true;
        }
        // Add END 2018/07/31 H.Tomimatsu S21_NA#26565
        return false;
    }

    private boolean isEqualBigDecimal(EZDCBigDecimalItem item1, EZDSBigDecimalItem item2) {

        if (ZYPCommonFunc.hasValue(item1) && !ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (!ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2)) {
            return false;
        } else if (ZYPCommonFunc.hasValue(item1) && ZYPCommonFunc.hasValue(item2) && item1.getValue().compareTo(item2.getValue()) != 0) {
            return false;
        }
        return true;
    }

    private boolean isEqualFlag(EZDCStringItem item1, EZDSStringItem item2) {

        String flg1 = "N";
        String flg2 = "N";
        if (ZYPCommonFunc.hasValue(item1)) {
            flg1 = item1.getValue();
        }
        if (ZYPCommonFunc.hasValue(item2)) {
            flg2 = item2.getValue();
        }

        if (!flg1.equals(flg2)) {
            return false;
        }
        return true;
    }

    // QC#8598 add Start
    @SuppressWarnings("unchecked")
    private boolean ContactPointMandatoryCheck(NMAL6750CMsg cMsg, NMAL6750SMsg sMsg) {

        List<String> ctacTpCdList = new ArrayList<String>();
        for (int j = 0; j < cMsg.R.getValidCount(); j++) {
            if (ZYPConstant.FLG_ON_Y.equals(cMsg.R.no(j).xxChkBox_R1.getValue())) {
                ctacTpCdList.add(cMsg.R.no(j).ctacTpCd_R1.getValue());
            }
        }

        if (!ctacTpCdList.isEmpty()) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", getGlobalCompanyCode());
            queryParam.put("ctacTpCdList", ctacTpCdList);
            S21SsmEZDResult ssmResult = NMAL6750Query.getInstance().getMandatoryContactPointTypeList(queryParam);

            List<String> mndCtacPntTpCdList = new ArrayList<String>();
            if (ssmResult.isCodeNormal()) {
                mndCtacPntTpCdList = (List<String>) ssmResult.getResultObject();
            }

            List<String> errCtacPntTpList = new ArrayList<String>();
            for (String mndCtacPntTpCd : mndCtacPntTpCdList) {

                boolean errflg = true;
                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    if (mndCtacPntTpCd.equals(cMsg.B.no(i).dsCtacPntTpCd_B1.getValue())) {
                        errflg = false;
                        break;
                    }
                }
                if (errflg) {
                    errCtacPntTpList.add(mndCtacPntTpCd);
                }
            }

            if (!errCtacPntTpList.isEmpty()) {
                StringBuffer sb = new StringBuffer();
                String ctacPntTpNm = null;
                for (String errCtacPntTpCd : errCtacPntTpList) {
                    for (int i = 0; i < cMsg.dsCtacPntTpCd_H1.length(); i++) {
                        if (errCtacPntTpCd.equals(cMsg.dsCtacPntTpCd_H1.no(i).getValue())) {
                            ctacPntTpNm = cMsg.dsCtacPntTpNm_H1.no(i).getValue();
                            break;
                        }
                    }
                    if (ctacPntTpNm != null) {
                        if (sb.length() > 0) {
                            sb.append(", ");
                        }
                        sb.append(ctacPntTpNm);
                    }
                }
                cMsg.setMessageInfo(NMAM8368E, new String[] {"Contact Type [" + sb.toString() + "]"});
                return false;
            }
        }
        return true;
    }
    // QC#8598 add End

    // Add START 2018/07/31 H.Tomimatsu S21_NA#26565
    /**
     * @param  cMsg NMAL6750CMsg
     * @return boolean
     */
    private boolean isExistContactRelation(NMAL6750CMsg cMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", getGlobalCompanyCode());
        queryParam.put("ctacPsnPk", cMsg.ctacPsnPk_H1.getValue());
        if (ZYPCommonFunc.hasValue(cMsg.dsAcctNum_P1)) {
            queryParam.put("locNum", null);
            queryParam.put("dsAcctNum", cMsg.dsAcctNum_P1.getValue());
        } else if (ZYPCommonFunc.hasValue(cMsg.locNum_P1)) {
            queryParam.put("locNum", cMsg.locNum_P1.getValue());
            queryParam.put("dsAcctNum", null);
        }
        return getQuery().isExistContactRelation(queryParam);
    }
    // Add END 2018/07/31 H.Tomimatsu S21_NA#26565

}

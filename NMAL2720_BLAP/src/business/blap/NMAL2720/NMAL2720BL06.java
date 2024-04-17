/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2720;

import static business.blap.NMAL2720.constant.NMAL2720Constant.HIGH_DT;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM0011E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM0073E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM0176E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8121E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8440E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8594E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8595E;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.MOVE_ORG_RQST_DTLTMsg;
import business.db.MOVE_ORG_RQST_HDRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_CRAT_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_DTL_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RQST_RSLT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL2720BL06
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/07/20   Fujitsu         N.Sugiura       Update          QC#12084
 * 2016/09/28   Fujitsu         Mz.Takahashi    Update          QC#12175
 * 2016/10/28   Fujitsu         M.Ohno          Update          S21_NA#15310
 * 2016/11/01   Fujitsu         C.Yokoi         Update          QC#15492
 * 2017/06/13   Hitachi         J.Kim           Update          QC#19030
 *</pre>
 */
public class NMAL2720BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2720CMsg bizMsg = (NMAL2720CMsg) cMsg;

            if ("NMAL2720Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_CMN_Submit(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_CMN_Submit(NMAL2720CMsg bizMsg) {

        // 2016/07/20 QC#12084 Del Start
        // Effective From Date Check.
        // if (ZYPDateUtil.compare(bizMsg.effFromDt_D1.getValue(), ZYPDateUtil.getSalesDate()) <= 0) {
        //     bizMsg.effFromDt_D1.setErrorInfo(1, NMAM0185E);
        // }
        // 2016/07/20 QC#12084 Del End

        // Master Check.
        boolean isError = false;
        if (!checkResrcInfo(bizMsg)) {
            isError = true;
        }

        // Effective Check.
        if (!checkMovableResrc(bizMsg)) {
            isError = true;
        }

        if (isError) {
            return;
        }

        // Insert MOVE_ORG_RQST_HDR/DTL
        BigDecimal moveOrgRqstHdrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MOVE_ORG_RQST_HDR_SQ);
        // 2016/11/01 CSA-QC#15492 Mod Start
        if (!insMoveOrgRqstHdr(bizMsg, moveOrgRqstHdrPk)) {
            return;
        }
        // insMoveOrgRqstHdr(bizMsg, moveOrgRqstHdrPk);
        // 2016/11/01 CSA-QC#15492 Mod End
        insMoveOrgRqstDtl(bizMsg, moveOrgRqstHdrPk);
    }

    private boolean checkResrcInfo(NMAL2720CMsg bizMsg) {

        boolean isSuccess = true;
        boolean isEffDtSuccess = false;

        S21SsmEZDResult ssmResult = NMAL2720Query.getInstance().getResrcInfo(bizMsg);
        if (!ssmResult.isCodeNormal()) {
            bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM0011E, new String[] {"Move Organization To(Resource)"});
            isSuccess = false;
            return isSuccess;
        }

        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) ssmResult.getResultObject();
        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);

        String  salesDt = ZYPDateUtil.getSalesDate();
        for (Map<String, Object> rsltMap : rsltMapList) {
            //Revenue
            String fromDt = (String) rsltMap.get("EFF_FROM_DT");
            String toDt = (String) rsltMap.get("EFF_THRU_DT");
            // START 2017/06/13 J.Kim [QC#19030,MOD]
            // isSuccess = false;
            // if (ZYPCommonFunc.hasValue(fromDt)) {
            // String salesDt = ZYPDateUtil.getSalesDate();
            //     if (fromDt.compareTo(salesDt) <= 0){
            //         if (ZYPCommonFunc.hasValue(toDt)){
            //             if (toDt.compareTo(salesDt) >= 0){
            //                 isSuccess = true;
            //             }
            //         } else {
            //             isSuccess = true;
            //         }
            //     }
            // }
            // if (!isSuccess){
            // bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8594E);
            // return isSuccess;
            // }
            boolean checkEffDt = false;
            if (ZYPCommonFunc.hasValue(fromDt)) {
                 if (fromDt.compareTo(salesDt) <= 0){
                     if (ZYPCommonFunc.hasValue(toDt)){
                         if (toDt.compareTo(salesDt) >= 0){
                             checkEffDt = true;
                         }
                     } else {
                         checkEffDt = true;
                     }
                 }
             }

            if (!checkEffDt) {
                continue;
            } else {
                isEffDtSuccess = true;
            }
            // END 2017/06/13 J.Kim [QC#19030,MOD]

            // Physical Address.
            if (ZYPCommonFunc.hasValue((String) rsltMap.get("CTRY_CD"))) {
                if (CTRY.UNITED_STATES_OF_AMERICA.equals((String) rsltMap.get("CTRY_CD"))) {
                    if (!ZYPCommonFunc.hasValue((String) rsltMap.get("POST_CD"))
                            || !ZYPCommonFunc.hasValue((String) rsltMap.get("ST_CD"))
                            || !ZYPCommonFunc.hasValue((String) rsltMap.get("CTY_ADDR"))) {
                        bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8121E, new String[] {"Move Organization To(Resource)'s Physical Address"});
                        isSuccess = false;
                    }
                } else {
                    if (!ZYPCommonFunc.hasValue((String) rsltMap.get("CTY_ADDR"))) {
                        bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8121E, new String[] {"Move Organization To(Resource)'s Physical Address"});
                        isSuccess = false;
                    }
                }
            } else {
                // Mod Start 2016/10/28 M.Ohno S21_NA#15310
                int count = (Integer) NMAL2720Query.getInstance().countBizAreaOrg(bizMsg).getResultObject();
                if (count > 0) {
                    bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8121E, new String[] {"Move Organization To(Resource)'s Physical Address" });
                    isSuccess = false;
                }
                // Mod End   2016/10/28 M.Ohno S21_NA#15310
            }

            for (Integer idx : selectedList) {
                String bizAreaOrgCd = bizMsg.A.no(idx).bizAreaOrgCd_A1.getValue();

                if (!ZYPCommonFunc.hasValue(bizAreaOrgCd)){
                    continue;
                }

                if (bizAreaOrgCd.equals(BIZ_AREA_ORG.SALES)
                        || bizAreaOrgCd.equals(BIZ_AREA_ORG.SERVICE)) {
                    if (!ZYPCommonFunc.hasValue(bizMsg.A.no(idx).lineBizTpDescTxt_A1)) {
                        bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM0011E, new String[] {"Move Organization To(Resource)'s Line Of Business"});
                        isSuccess = false;
                        break;
                    }
                }
            }

            if (isSuccess) {
                ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_D1, (String) rsltMap.get("PSN_NUM"));
            }
        }

        // START 2017/06/13 J.Kim [QC#19030,ADD]
        if (!isEffDtSuccess) {
            bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8594E);
            isSuccess = false;
        }
        // END 2017/06/13 J.Kim [QC#19030,ADD]

        return isSuccess;
    }

    private boolean checkMovableResrc(NMAL2720CMsg bizMsg) {

        boolean isSuccess = true;

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        String thruDt = null;
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_D1)) {
            thruDt = bizMsg.effThruDt_D1.getValue();
        } else {
            thruDt = HIGH_DT;
        }

        for (Integer idx : selectedList) {
            S21SsmEZDResult ssmResult = NMAL2720Query.getInstance().getMovableResrc(bizMsg.A.no(idx));
            if (!ssmResult.isCodeNormal()) {
                bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8121E, new String[] {"Old Resource"});
                isSuccess = false;
                continue;
            }

            Map<String, Object> rsltMap = (Map<String, Object>) ssmResult.getResultObject();
            if (ZYPDateUtil.compare(ZYPDateUtil.addDays(bizMsg.effFromDt_D1.getValue(), -1), (String) rsltMap.get("EFF_FROM_DT_TOSR")) < 0) {
                bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM0073E, new String[] {"Old Resource Effective Date Thru", "Move Effective Date From is before Old Resource Effective Date From"});
                isSuccess = false;
                continue;
            }

            if (ZYPDateUtil.compare((String) rsltMap.get("EFF_FROM_DT_DOU"), bizMsg.effFromDt_D1.getValue()) > 0
                    || ZYPDateUtil.compare((String) rsltMap.get("EFF_THRU_DT_DOU"), thruDt) < 0) {
                bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8595E);
                isSuccess = false;
                continue;
            }

            S21SsmEZDResult ssmResult2 = NMAL2720Query.getInstance().getMoveBizArea(bizMsg, bizMsg.A.no(idx));
            if (ssmResult2.isCodeNormal()) {
                Map<String, Object> rsltMap2 = (Map<String, Object>) ssmResult2.getResultObject();
                if (rsltMap2 != null) {
                    if (BIZ_AREA_ORG.SALES.equals(rsltMap2.get("BIZ_AREA_ORG_CD"))) {
                        bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8440E, new String[] {"This Resource"});
                        isSuccess = false;
                        continue;
                    }
                }
            }

            S21SsmEZDResult ssmResult3 = NMAL2720Query.getInstance().getSameResrc(bizMsg, bizMsg.A.no(idx), (String) rsltMap.get("ORG_FUNC_ROLE_TP_CD"));
            if (ssmResult3.isCodeNormal()) {
                bizMsg.A.no(idx).xxChkBox_A1.setErrorInfo(1, NMAM8440E, new String[] {"Move Resource"});
                isSuccess = false;
                continue;
            }
        }

        return isSuccess;
    }

    private boolean insMoveOrgRqstHdr(NMAL2720CMsg bizMsg, BigDecimal moveOrgRqstHdrPk) {

        MOVE_ORG_RQST_HDRTMsg insTMsg = new MOVE_ORG_RQST_HDRTMsg();

        ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstHdrPk, moveOrgRqstHdrPk);
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstUsrId, getContextUserInfo().getUserId());
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstCratTs, ZYPDateUtil.getSalesDate() + ZYPDateUtil.getCurrentSystemTime("HHmmssSSS"));
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstCratSysTpCd, RQST_CRAT_SYS_TP.ONLINE);
        ZYPEZDItemValueSetter.setValue(insTMsg.rqstRsltTpCd, RQST_RSLT_TP.SUBMITTED);
        insTMsg.rqstRsltCmntTxt.clear();

        EZDTBLAccessor.insert(insTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
            // 2016/11/01 CSA-QC#15492 Add
            bizMsg.setMessageInfo(NMAM0176E, new String[]{"MOVE_ORG_RQST_HDR"});
            return false;
        }
        return true;
    }

    private boolean insMoveOrgRqstDtl(NMAL2720CMsg bizMsg, BigDecimal moveOrgRqstHdrPk) {

        MOVE_ORG_RQST_DTLTMsg insTMsg = new MOVE_ORG_RQST_DTLTMsg();

        List<Integer> selectedList = ZYPTableUtil.getSelectedRows(bizMsg.A, "xxChkBox_A1", ZYPConstant.FLG_ON_Y);
        for (Integer idx : selectedList) {
            ZYPEZDItemValueSetter.setValue(insTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstDtlPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.MOVE_ORG_RQST_DTL_SQ));
            ZYPEZDItemValueSetter.setValue(insTMsg.moveOrgRqstHdrPk, moveOrgRqstHdrPk);
            ZYPEZDItemValueSetter.setValue(insTMsg.curTocCd, bizMsg.A.no(idx).tocCd_A1);
            ZYPEZDItemValueSetter.setValue(insTMsg.curOrgCd, bizMsg.A.no(idx).orgCd_A1);
            ZYPEZDItemValueSetter.setValue(insTMsg.curPsnNum, bizMsg.A.no(idx).psnNum_A1);
            ZYPEZDItemValueSetter.setValue(insTMsg.movePsnNum, bizMsg.psnNum_D1);
            ZYPEZDItemValueSetter.setValue(insTMsg.moveEffFromDtTxt, ZYPDateUtil.convertFormat(bizMsg.effFromDt_D1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.getJavaDateFormatString(), '/'));
            if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_D1)) {
                ZYPEZDItemValueSetter.setValue(insTMsg.moveEffThruDtTxt, ZYPDateUtil.convertFormat(bizMsg.effThruDt_D1.getValue(), ZYPDateUtil.TYPE_YYYYMMDD, ZYPDateUtil.getJavaDateFormatString(), '/'));
            } else {
                insTMsg.moveEffThruDtTxt.clear();
            }
            ZYPEZDItemValueSetter.setValue(insTMsg.massUpdRsnCmntTxt, bizMsg.massUpdRsnCmntTxt_D1);
            ZYPEZDItemValueSetter.setValue(insTMsg.rqstDtlRsltTpCd, RQST_DTL_RSLT_TP.SUCCESS);

            insTMsg.rqstDtlRsltCmntTxt.clear();

            EZDTBLAccessor.insert(insTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(insTMsg.getReturnCode())) {
                // 2016/11/01 CSA-QC#15492 Add
                bizMsg.setMessageInfo(NMAM0176E, new String[]{"MOVE_ORG_RQST_DTL"});
                return false;
            }
        }
        return true;
    }
}

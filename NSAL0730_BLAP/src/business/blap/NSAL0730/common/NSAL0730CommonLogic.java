/**
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0730.common;

import static business.blap.NSAL0730.constant.NSAL0730Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NSAL0730.NSAL0730CMsg;
import business.blap.NSAL0730.NSAL0730Query;
import business.blap.NSAL0730.NSAL0730SMsg;
import business.blap.NSAL0730.NSAL0730_ACMsg;
import business.blap.NSAL0730.NSAL0730_ACMsgArray;
import business.blap.NSAL0730.NSAL0730_ASMsg;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;
import business.parts.NSZC077001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.api.NSZ.NSZC077001.NSZC077001;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrValidation;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TRK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2015/12/07   Hitachi         T.Tsuchida      Update          QC#1542
 * 2015/12/10   Hitachi         T.Tsuchida      Update          QC#1731
 * 2015/12/21   Hitachi         T.Tsuchida      Update          QC#2296
 * 2016/02/19   Hitachi         T.Aoyagi        Update          QC3694
 * 2016/02/29   Hitachi         K.Kasai         Update          QC#2684
 * 2016/08/08   Hitachi         A.Kohinata      Update          QC#12001
 * 2016/11/11   Hitachi         T.Mizuki        Update          QC#4210
 * 2016/12/08   Hitachi         N.Arai          Create          QC#14204
 * 2017/02/27   Hitachi         K.Ochiai        Update          QC#4210
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 * 2020/06/25   CITS            T.Wada          Update          QC#55590
 * 2023/05/24   Hitachi         K.Watanabe      Update          QC#61498
 *</pre>
 */
public class NSAL0730CommonLogic {

    /**
     * Service Memo Reason Pull down
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void setServiceMemoReasonInfo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        SVC_MEMO_RSNTMsgArray smrTMsgArray = NSAL0730Query.getInstance().getServiceMemoReasonInfo(cMsg);
        if (smrTMsgArray.getValidCount() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "svcMemoRsnCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "svcMemoRsnNm");
            ZYPPulldownValueSetter.set(smrTMsgArray, tMsgKeys, cMsg.svcMemoRsnCd_H1, cMsg.svcMemoRsnNm_H2);
        }
    }

    /**
     * Contract Detail Information
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void setDetailListInfo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        // START 2016/02/19 T.Aoyagi [QC3694, ADD]
        List<BigDecimal> dsContrPkList = new ArrayList<BigDecimal>();
        Map<BigDecimal, List<BigDecimal>> dsContrDtlPkMap = new HashMap<BigDecimal, List<BigDecimal>>();

        for (int i = 0; i < cMsg.P.getValidCount(); i++) {
            BigDecimal dsContrPk = cMsg.P.no(i).dsContrPk_P1.getValue();
            BigDecimal dsContrDtlPk = cMsg.P.no(i).dsContrDtlPk_P1.getValue();

            // add DS_CONTR_PK
            if (!dsContrPkList.contains(dsContrPk)) {
                dsContrPkList.add(dsContrPk);
            }

            // add DS_CONTR_DTL_PK
            if (dsContrDtlPkMap.containsKey(dsContrPk)) {
                List<BigDecimal> dsContrDtlPkList = dsContrDtlPkMap.get(dsContrPk);
                if (!dsContrDtlPkList.contains(dsContrDtlPk)) {
                    dsContrDtlPkList.add(dsContrDtlPk);
                }
            } else {
                List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
                if (ZYPCommonFunc.hasValue(dsContrDtlPk)) {
                    dsContrDtlPkList.add(dsContrDtlPk);
                }
                dsContrDtlPkMap.put(dsContrPk, dsContrDtlPkList);
            }
        }
        // END 2016/02/19 T.Aoyagi [QC3694, ADD]
        // START 2016/02/19 T.Aoyagi [QC3694, MOD]
        for (BigDecimal dsContrPk : dsContrPkList) {

            // Contract Level
            S21SsmEZDResult ssmResult = NSAL0730Query.getInstance().getDSContractInfo(cMsg, dsContrPk);
            if (ssmResult.isCodeNormal()) {
                // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//                @SuppressWarnings("unchecked")
//                Map<String, Object> dsContractInfo = (Map<String, Object>) ssmResult.getResultObject();
//                String dsContrNum = (String) dsContractInfo.get("DS_CONTR_NUM");
//                copyCMsgToSMsg(sMsg, cMsg, dsContractInfo);
                String dsContrNum = null;
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> dsContractInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();
                for (Map<String, Object> dsContractInfo : dsContractInfoList) {
                    dsContrNum = (String) dsContractInfo.get("DS_CONTR_NUM");
                    copyCMsgToSMsg(sMsg, cMsg, dsContractInfo);
                    if (sMsg.A.getValidCount() >= sMsg.A.length()) {
                        cMsg.setMessageInfo(NZZM0001W);
                        break;
                    }
                }
                // END 2019/01/10 K.Kitachi [QC#26928, MOD]
                // START 2017/02/27 K.Ochiai [QC#4210, ADD]
                if (sMsg.A.getValidCount() >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                // END 2017/02/27 K.Ochiai [QC#4210, ADD]

                List<BigDecimal> dsContrDtlPkList = dsContrDtlPkMap.get(dsContrPk);

                // Contract Detail Level
                S21SsmEZDResult ssmResultDetail = NSAL0730Query.getInstance().getDSContractDetailInfo(cMsg, dsContrPk, dsContrDtlPkList);
                // Contract Detail Level
                S21SsmEZDResult ssmResultBo = NSAL0730Query.getInstance().getDSContractDetailBaseOverageInfo(cMsg, dsContrPk, dsContrDtlPkList);
                if (ssmResultDetail.isCodeNormal() && ssmResultBo.isCodeNormal()) {

                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> dsContractDetailInfo = (List<Map<String, Object>>) ssmResultDetail.getResultObject();
                    @SuppressWarnings("unchecked")
                    List<Map<String, Object>> dsContractDetailBOInfo = (List<Map<String, Object>>) ssmResultBo.getResultObject();
                    setDsContractDetailInfo(cMsg, sMsg, dsContractDetailInfo, dsContractDetailBOInfo, dsContrPk, dsContrNum);
                }
                // START 2017/02/27 K.Ochiai [QC#4210, ADD]
                if (sMsg.A.getValidCount() >= sMsg.A.length()) {
                    cMsg.setMessageInfo(NZZM0001W);
                    break;
                }
                // END 2017/02/27 K.Ochiai [QC#4210, ADD]
            } else {
                cMsg.setMessageInfo(NSAM0013E, null);
                return;
            }
        }
        // END 2016/02/19 T.Aoyagi [QC3694, MOD]
        cMsg.A.setValidCount(sMsg.A.getValidCount());
        // mod start 2016/11/07 CSA QC#4210
        ZYPEZDItemValueSetter.setValue(cMsg.xxRowNum, new BigDecimal(cMsg.A.getValidCount()));
        // mod end 2016/11/07 CSA QC#4210
    }

    private static void setDsContractDetailInfo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, List<Map<String, Object>> cdMapList, List<Map<String, Object>> boMapList, BigDecimal dsContrPk, String dsContrNum) {

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        Map<String, Object> preCdMap = null;
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        int index = sMsg.A.getValidCount();
        for (int i = 0; i < cdMapList.size(); i++) {

            Map<String, Object> cdMap = cdMapList.get(i);
            BigDecimal dsContrDtlPkDt = (BigDecimal) cdMap.get("DS_CONTR_DTL_PK");
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRowNum_A, BigDecimal.valueOf(index));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrDtlPk, dsContrDtlPkDt);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachMstrPk, (BigDecimal) cdMap.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrDtlStsCd, (String) cdMap.get("DS_CONTR_DTL_STS_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).serNum, (String) cdMap.get("SER_NUM"));
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).custPoNum_D1, (String) cdMap.get("CUST_PO_NUM"));
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poDt_D1, (String) cdMap.get("PO_DT"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).custPoNum_A, (String) cdMap.get("CUST_PO_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poFromDt_A, (String) cdMap.get("PO_FROM_DT"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poDt_A, (String) cdMap.get("PO_DT"));
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).leaseCmpyFlg, (String) cdMap.get("LEASE_CMPY_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrMachLvlNum, (String) cdMap.get("DS_CONTR_MACH_LVL_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxExstFlg_D2, setExstFlgDsContrDtl(cMsg, dsContrDtlPkDt, dsContrDtlPkDt));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxChkBox_D3, (String) cdMap.get("LEASE_CMPY_FLG"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrPk_P, dsContrPk);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrNum, dsContrNum);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrCrCardPoPk, (BigDecimal) cdMap.get("DS_CONTR_CR_CARD_PO_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTime, (String) cdMap.get("EZUPTIME"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTimeZone, (String) cdMap.get("EZUPTIMEZONE"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDsMsgEntryTxt, cMsg.A.no(0).xxDsMsgEntryTxt);
// START 2016/12/08 N.Arai [QC#14204, MOD]
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratTs_A, (String) cdMap.get("XX_REC_HIST_CRAT_TS"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratByNm_A, (String) cdMap.get("XX_REC_HIST_CRAT_BY_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdTs_A, (String) cdMap.get("XX_REC_HIST_UPD_TS"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdByNm_A, (String) cdMap.get("XX_REC_HIST_UPD_BY_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistTblNm_A, (String) cdMap.get("XX_REC_HIST_TBL_NM"));
            // START 2019/01/10 K.Kitachi [QC#26928, ADD]
            if (preCdMap != null) {
                if (LVL_NUM_2.equals((String) preCdMap.get("DS_CONTR_MACH_LVL_NUM")) && dsContrDtlPkDt.compareTo((BigDecimal) preCdMap.get("DS_CONTR_DTL_PK")) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
                    sMsg.A.no(index).serNum.clear();
                }
            }
            preCdMap = cdMap;
            // END 2019/01/10 K.Kitachi [QC#26928, ADD]
//END 2016/12/08 N.Arai [QC#14204, MOD]
            index++;
            // START 2017/02/27 K.Ochiai [QC#4210, ADD]
            if (index >= sMsg.A.length()) {
                break;
            }
            // END 2017/02/27 K.Ochiai [QC#4210, ADD]

            // START 2019/01/10 K.Kitachi [QC#26928, ADD]
            if (i < cdMapList.size() - 1) {
                Map<String, Object> nextCdMap = cdMapList.get(i + 1);
                BigDecimal nextDsContrDtlPk = (BigDecimal) nextCdMap.get("DS_CONTR_DTL_PK");
                if (dsContrDtlPkDt.compareTo(nextDsContrDtlPk) == 0) {
                    continue;
                }
            }

            Map<String, Object> preBoMap = null;
            // END 2019/01/10 K.Kitachi [QC#26928, ADD]

            for (int j = 0; j < boMapList.size(); j++) {
                Map<String, Object> boMap = boMapList.get(j);
                BigDecimal dsContrDtlPkDtBo = (BigDecimal) boMap.get("DS_CONTR_DTL_PK");
                BigDecimal dsContrBllgMtrPk = (BigDecimal) boMap.get("DS_CONTR_BLLG_MTR_PK");
                if (dsContrDtlPkDt.compareTo(dsContrDtlPkDtBo) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRowNum_A, BigDecimal.valueOf(index));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrDtlPk, (BigDecimal) boMap.get("DS_CONTR_DTL_PK"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrDtlTpCd, (String) boMap.get("DS_CONTR_DTL_TP_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrBllgMtrPk, (BigDecimal) boMap.get("DS_CONTR_BLLG_MTR_PK"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).bllgMtrLbCd, (String) boMap.get("BLLG_MTR_LB_CD"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrBllgMtrStsCd, (String) boMap.get("DS_CONTR_BLLG_MTR_STS_CD"));
                    // mod start 2016/02/29 CSA Defect#2684
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mtrLbDescTxt, (String) boMap.get("MTR_LB_DESC_TXT"));
                    // mod end 2016/02/29 CSA Defect#2684
                    // START 2019/01/10 K.Kitachi [QC#26928, DEL]
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).crCardCustRefNum, (String) boMap.get("CR_CARD_CUST_REF_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).crCardExprYrMth, (String) boMap.get("CR_CARD_EXPR_YR_MTH"));
                    // END 2019/01/10 K.Kitachi [QC#26928, DEL]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).leaseCmpyFlg, (String) boMap.get("LEASE_CMPY_FLG"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrMachLvlNum, (String) boMap.get("DS_CONTR_MACH_LVL_NUM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
                    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
                    // END 2019/01/10 K.Kitachi [QC#26928, ADD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxExstFlg_D3, getContrBllgMtrStsV(cMsg, dsContrDtlPkDtBo, dsContrBllgMtrPk));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxChkBox_D3, (String) boMap.get("LEASE_CMPY_FLG"));
                    // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).custPoNum_D1, (String) boMap.get("CUST_PO_NUM"));
//                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poDt_D1, (String) boMap.get("PO_DT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).custPoNum_A, (String) boMap.get("CUST_PO_NUM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poFromDt_A, (String) boMap.get("PO_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).poDt_A, (String) boMap.get("PO_DT"));
                    // END 2019/01/10 K.Kitachi [QC#26928, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrPk_P, dsContrPk);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrNum, dsContrNum);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrCrCardPoPk, (BigDecimal) boMap.get("DS_CONTR_CR_CARD_PO_PK"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTime, (String) boMap.get("EZUPTIME"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).ezUpTimeZone, (String) boMap.get("EZUPTIMEZONE"));
// START 2016/12/08 N.Arai [QC#14204, MOD]
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratTs_A, (String) boMap.get("XX_REC_HIST_CRAT_TS"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistCratByNm_A, (String) boMap.get("XX_REC_HIST_CRAT_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdTs_A, (String) boMap.get("XX_REC_HIST_UPD_TS"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistUpdByNm_A, (String) boMap.get("XX_REC_HIST_UPD_BY_NM"));
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxRecHistTblNm_A, (String) boMap.get("XX_REC_HIST_TBL_NM"));
//END 2016/12/08 N.Arai [QC#14204, MOD]
                    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
                    if (preBoMap != null) {
                        if (LVL_NUM_3.equals((String) preBoMap.get("DS_CONTR_MACH_LVL_NUM"))) {
                            if ((!ZYPCommonFunc.hasValue(sMsg.A.no(index).dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue((BigDecimal) preBoMap.get("DS_CONTR_BLLG_MTR_PK")))
                                    || ((ZYPCommonFunc.hasValue(sMsg.A.no(index).dsContrBllgMtrPk) && ZYPCommonFunc.hasValue((BigDecimal) preBoMap.get("DS_CONTR_BLLG_MTR_PK"))) && sMsg.A.no(index).dsContrBllgMtrPk.getValue().compareTo(
                                            (BigDecimal) preBoMap.get("DS_CONTR_BLLG_MTR_PK")) == 0)) {
                                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
                                sMsg.A.no(index).mtrLbDescTxt.clear();
                            }
                        }
                    }
                    preBoMap = boMap;
                    // END 2019/01/10 K.Kitachi [QC#26928, ADD]
                    index++;
                    // START 2017/02/27 K.Ochiai [QC#4210, ADD]
                    if (index >= sMsg.A.length()) {
                        break;
                    }
                }
            }
            if (index >= sMsg.A.length()) {
                break;
            }
            // END 2017/02/27 K.Ochiai [QC#4210, ADD]
        }
        sMsg.A.setValidCount(index);
    }

    private static void copyCMsgToSMsg(NSAL0730SMsg sMsg, NSAL0730CMsg cMsg, Map<String, Object> dsContractInfo) {

        int idx = sMsg.A.getValidCount();
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxRowNum_A, BigDecimal.valueOf(idx));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxScrItem34Txt, (String) dsContractInfo.get("CONTR_CATG_CD_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrCatgCd, (String) dsContractInfo.get("DS_CONTR_CATG_CD"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrNum, (String) dsContractInfo.get("DS_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrStsCd, (String) dsContractInfo.get("DS_CONTR_STS_CD"));
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).custPoNum_D1, (String) dsContractInfo.get("CUST_PO_NUM"));
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).poDt_D1, (String) dsContractInfo.get("PO_DT"));
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).custPoNum_D2, (String) dsContractInfo.get("CUST_PO_NUM"));
//        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).poDt_D2, (String) dsContractInfo.get("PO_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).custPoNum_A, (String) dsContractInfo.get("CUST_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).poFromDt_A, (String) dsContractInfo.get("PO_FROM_DT"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).poDt_A, (String) dsContractInfo.get("PO_DT"));
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).leaseCmpyFlg, (String) dsContractInfo.get("LEASE_CMPY_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrCrCardPoPk, (BigDecimal) dsContractInfo.get("DS_CONTR_CR_CARD_PO_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrMachLvlNum, (String) dsContractInfo.get("DS_CONTR_MACH_LVL_NUM"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).ezUpTime, (String) dsContractInfo.get("EZUPTIME"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).ezUpTimeZone, (String) dsContractInfo.get("EZUPTIMEZONE"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxChkBox_D3, (String) dsContractInfo.get("LEASE_CMPY_FLG"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxExstFlg_D1, setExstFlgDsContr(cMsg, (BigDecimal) dsContractInfo.get("DS_CONTR_PK")));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).dsContrPk_P, (BigDecimal) dsContractInfo.get("DS_CONTR_PK"));
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxDsMsgEntryTxt, cMsg.A.no(0).xxDsMsgEntryTxt);
// START 2016/12/08 N.Arai [QC#14204, MOD]
        setValue(sMsg.A.no(idx).xxRecHistCratTs_A, (String) dsContractInfo.get("XX_REC_HIST_CRAT_TS"));
        setValue(sMsg.A.no(idx).xxRecHistCratByNm_A, (String) dsContractInfo.get("XX_REC_HIST_CRAT_BY_NM"));
        setValue(sMsg.A.no(idx).xxRecHistUpdTs_A, (String) dsContractInfo.get("XX_REC_HIST_UPD_TS"));
        setValue(sMsg.A.no(idx).xxRecHistUpdByNm_A, (String) dsContractInfo.get("XX_REC_HIST_UPD_BY_NM"));
        setValue(sMsg.A.no(idx).xxRecHistTblNm_A, (String) dsContractInfo.get("XX_REC_HIST_TBL_NM"));
//END 2016/12/08 N.Arai [QC#14204, MOD]

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        if (idx > 0) {
            if (sMsg.A.no(idx).dsContrPk_P.getValue().compareTo(sMsg.A.no(idx - 1).dsContrPk_P.getValue()) == 0) {
                sMsg.A.no(idx).xxScrItem34Txt.clear();
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
            }
        }
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        sMsg.A.setValidCount(idx + 1);
    }

    private static String setExstFlgDsContr(NSAL0730CMsg cMsg, BigDecimal dsContrPk) {

        BigDecimal result = (BigDecimal) NSAL0730Query.getInstance().getContStsV(cMsg, dsContrPk).getResultObject();
        if (BigDecimal.ZERO.compareTo(result) < 0) {
            return ZYPConstant.CHKBOX_ON_1;
        } else {
            return ZYPConstant.FLG_OFF_0;
        }
    }

    private static String setExstFlgDsContrDtl(NSAL0730CMsg cMsg, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {

        BigDecimal result = (BigDecimal) NSAL0730Query.getInstance().getContrDtlStsV(cMsg, dsContrPk, dsContrDtlPk).getResultObject();
        if (BigDecimal.ZERO.compareTo(result) < 0) {
            return ZYPConstant.CHKBOX_ON_1;
        } else {
            return ZYPConstant.FLG_OFF_0;
        }
    }

    private static String getContrBllgMtrStsV(NSAL0730CMsg cMsg, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrStsPk) {

        if (dsContrBllgMtrStsPk == null) {
            return ZYPConstant.FLG_OFF_0;
        } else {
            BigDecimal result = (BigDecimal) NSAL0730Query.getInstance().getContrBllgMtrStsV(cMsg, dsContrDtlPk, dsContrBllgMtrStsPk).getResultObject();
            if (BigDecimal.ZERO.compareTo(result) < 0) {
                return ZYPConstant.CHKBOX_ON_1;
            } else {
                return ZYPConstant.FLG_OFF_0;
            }
        }
    }

    /**
     * Submit Contract Info.
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void submitContractInfo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        boolean updateFlg1 = false;
        boolean updateFlg2 = false;
        boolean errFlg = false;
        BigDecimal dsContrPkFlg = BigDecimal.ZERO;
        BigDecimal dsContrDtlPkFlg = BigDecimal.ZERO;
        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {

            // mod start 2016/08/08 CSA Defect#12001
            String xxChkBox01 = sMsg.A.no(idx).xxChkBox_D1.getValue();
            String xxChkBox02 = sMsg.A.no(idx).xxChkBox_D2.getValue();
            String flgNm = sMsg.A.no(idx).xxFlgNm.getValue();
            BigDecimal dsContrPk = sMsg.A.no(idx).dsContrPk_P.getValue();
            BigDecimal dsContrDtlPk = sMsg.A.no(idx).dsContrDtlPk.getValue();
            String dsContrMachLvlNum = sMsg.A.no(idx).dsContrMachLvlNum.getValue();

            if (ZYPConstant.FLG_ON_1.equals(flgNm)) {
                continue;
            }

            if (LVL_NUM_1.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                dsContrPkFlg = sMsg.A.no(idx).dsContrPk_P.getValue();
                updateFlg1 = true;
            } else if (LVL_NUM_1.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                dsContrPkFlg = sMsg.A.no(idx).dsContrPk_P.getValue();
                updateFlg1 = false;
            }

            if (LVL_NUM_2.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                dsContrDtlPkFlg = sMsg.A.no(idx).dsContrDtlPk.getValue();
                updateFlg2 = true;
            } else if (LVL_NUM_2.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox01)) {
                dsContrDtlPkFlg = sMsg.A.no(idx).dsContrDtlPk.getValue();
                updateFlg2 = false;
            }

            if ((LVL_NUM_1.equals(dsContrMachLvlNum) && updateFlg1)
                    || (LVL_NUM_2.equals(dsContrMachLvlNum) && ((updateFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0) || (updateFlg2 && dsContrDtlPk.compareTo(dsContrDtlPkFlg) == 0)))) {
                if (!updateDsContrCrCardPo(cMsg, sMsg, idx)) {
                    errFlg = true;
                }
            }

            if (LVL_NUM_3.equals(dsContrMachLvlNum) && !ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox02) && ((updateFlg1 && dsContrPk.compareTo(dsContrPkFlg) == 0) || (updateFlg2 && dsContrDtlPk.compareTo(dsContrDtlPkFlg) == 0))) {
                if (!updateDsContrCrCardPo(cMsg, sMsg, idx)) {
                    errFlg = true;
                }
            }

            if (LVL_NUM_3.equals(dsContrMachLvlNum) && ZYPConstant.CHKBOX_ON_Y.equals(xxChkBox02)) {
                if (!updateDsContrCrCardPo(cMsg, sMsg, idx)) {
                    errFlg = true;
                }
            }
            // mod end 2016/08/08 CSA Defect#12001
        }
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        for (int dsMsgIdx = 0; dsMsgIdx < sMsg.D.getValidCount(); dsMsgIdx++) {
            DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.dsContrCrCardPoPk, sMsg.D.no(dsMsgIdx).dsContrCrCardPoPk);
            EZDTBLAccessor.logicalRemove(inMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
                EZDConnectionMgr.getInstance().rollback();
                errFlg = true;
                continue;
            }
            EZDConnectionMgr.getInstance().commit();
        }
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        // START 2017/10/04 K.Kojima [QC#20523,ADD]
        if (!errFlg) {
            releaseRenewalHoldForPO(cMsg, ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue()));
        }
        // END 2017/10/04 K.Kojima [QC#20523,ADD]
        if (errFlg) {
            cMsg.setMessageInfo(NSAM0394W);
        } else {
            cMsg.setMessageInfo(NSAM0200I);
        }
    }

    /**
     * Submit Service Memo
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    private static boolean submitSvcMemo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, BigDecimal dsContrPk, int index) {

        S21SsmEZDResult ssmResult = NSAL0730Query.getInstance().getSvcMemo(sMsg, dsContrPk);

        SVC_MEMOTMsg svcMemoMap = (SVC_MEMOTMsg) ssmResult.getResultObject();
        if (svcMemoMap == null) {

            SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MEMO_SQ));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoCatgCd, SVC_MEMO_CATG.CONTRACT_MEMO);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoTpCd, SVC_MEMO_TP.UPDATE_PO_NUM);
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcCmntTxt, cMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, dsContrPk);
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(insertTMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(insertTMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
            S21FastTBLAccessor.insert(insertTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxDsMsgEntryTxt, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
        } else {

            SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
            ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, svcMemoMap.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(inMsg.svcMemoPk, svcMemoMap.svcMemoPk);

            SVC_MEMOTMsg outMsg = (SVC_MEMOTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
            ZYPEZDItemValueSetter.setValue(outMsg.svcCmntTxt, cMsg.svcCmntTxt);
            ZYPEZDItemValueSetter.setValue(outMsg.lastUpdUsrId, cMsg.getUserID());
            ZYPEZDItemValueSetter.setValue(outMsg.lastUpdTs, ZYPDateUtil.getCurrentSystemTime(DT_FORMAT_TS));
            ZYPEZDItemValueSetter.setValue(outMsg.svcMemoRsnCd, cMsg.svcMemoRsnCd_H3);
            S21FastTBLAccessor.update(outMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(index).xxDsMsgEntryTxt, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
        }
        return true;
    }

    // mod start 2016/11/07 CSA QC#4210
    /**
     * check Select
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return boolean
     */
    public static boolean checkSelect(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        boolean rtnVal = true;
        List<Integer> selectedRowsContract = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D1", ZYPConstant.CHKBOX_ON_Y);
        List<Integer> selectedRowsSerial = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D2", ZYPConstant.CHKBOX_ON_Y);
    // mod end 2016/11/07 CSA QC#4210
        if (selectedRowsContract.isEmpty() && selectedRowsSerial.isEmpty()) {
            cMsg.setMessageInfo(NSAM0015E);
            rtnVal = false;
        }
        return rtnVal;
    }

    /**
     * Update DS_CONTR_CR_CARD_PO
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return boolean
     */
    private static boolean updateDsContrCrCardPo(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, int index) {

        // mod start 2016/08/08 CSA Defect#12001
        NSAL0730_ASMsg asMsg = sMsg.A.no(index);

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        if (!ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_D1.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_D2.getValue())) {
            return true;
        }
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
        ZYPEZDItemValueSetter.setValue(inMsg.glblCmpyCd, cMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inMsg.dsContrCrCardPoPk, asMsg.dsContrCrCardPoPk);

        DS_CONTR_CR_CARD_POTMsg outMsg = (DS_CONTR_CR_CARD_POTMsg) S21FastTBLAccessor.findByKeyForUpdateNoWait(inMsg);
        if (outMsg == null) {
            DS_CONTR_CR_CARD_POTMsg insertTMsg = new DS_CONTR_CR_CARD_POTMsg();
            ZYPEZDItemValueSetter.setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrCrCardPoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_CR_CARD_PO_SQ));
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrPk, asMsg.dsContrPk_P);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrDtlPk, asMsg.dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrBllgMtrPk, asMsg.dsContrBllgMtrPk);
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            ZYPEZDItemValueSetter.setValue(insertTMsg.custPoNum, asMsg.custPoNum_D2);
//            ZYPEZDItemValueSetter.setValue(insertTMsg.poDt, asMsg.poDt_D2);
            ZYPEZDItemValueSetter.setValue(insertTMsg.custPoNum, asMsg.custPoNum_A);
            ZYPEZDItemValueSetter.setValue(insertTMsg.poFromDt, asMsg.poFromDt_A);
            ZYPEZDItemValueSetter.setValue(insertTMsg.poDt, asMsg.poDt_A);
            ZYPEZDItemValueSetter.setValue(insertTMsg.crCardFlg, ZYPConstant.FLG_OFF_N);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            if (ZYPCommonFunc.hasValue(asMsg.leaseCmpyFlg)) {
                ZYPEZDItemValueSetter.setValue(insertTMsg.leaseCmpyFlg, asMsg.leaseCmpyFlg);
            } else {
                ZYPEZDItemValueSetter.setValue(insertTMsg.leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
            }
            ZYPEZDItemValueSetter.setValue(insertTMsg.dsContrMachLvlNum, asMsg.dsContrMachLvlNum);
            S21FastTBLAccessor.insert(insertTMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(insertTMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDsMsgEntryTxt, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
        } else if (isSameTimeStamp(asMsg, outMsg, cMsg)) {
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            ZYPEZDItemValueSetter.setValue(outMsg.custPoNum, asMsg.custPoNum_D2);
//            ZYPEZDItemValueSetter.setValue(outMsg.poDt, asMsg.poDt_D2);
            ZYPEZDItemValueSetter.setValue(outMsg.custPoNum, asMsg.custPoNum_A);
            ZYPEZDItemValueSetter.setValue(outMsg.poFromDt, asMsg.poFromDt_A);
            ZYPEZDItemValueSetter.setValue(outMsg.poDt, asMsg.poDt_A);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            ZYPEZDItemValueSetter.setValue(outMsg.leaseCmpyFlg, asMsg.leaseCmpyFlg);
            S21FastTBLAccessor.update(outMsg);

            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(outMsg.getReturnCode())) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDsMsgEntryTxt, RTRN_MSG_FAILED);
                EZDConnectionMgr.getInstance().rollback();
                return false;
            }
        }

        BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
        if (!submitSvcMemo(cMsg, sMsg, dsContrPk, index)) {
            return false;
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDsMsgEntryTxt, RTRN_MSG_SUCCESS);
        EZDConnectionMgr.getInstance().commit();
        return true;
        // mod end 2016/08/08 CSA Defect#12001
    }

    /**
     * Execute Contraction
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void executeContraction(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        int targetIdxNum = cMsg.xxNum.getValueInt();
        String lvlNum = cMsg.A.no(targetIdxNum).dsContrMachLvlNum.getValue();
        BigDecimal dsContrPk = cMsg.A.no(targetIdxNum).dsContrPk_P.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(targetIdxNum).dsContrDtlPk.getValue();
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetIdxNum).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetIdxNum).xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
        copyCurrentPageToSMsg(cMsg, sMsg);

        // mod start 2016/11/11 CSA QC#4210
        int cnt = 0;
        if (LVL_NUM_1.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                String lvlNumDetail = sMsg.A.no(j).dsContrMachLvlNum.getValue();
                BigDecimal dsContrPkDetail = sMsg.A.no(j).dsContrPk_P.getValue();
                if (!LVL_NUM_1.equals(lvlNumDetail) && dsContrPk.compareTo(dsContrPkDetail) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxBtnFlg, ZYPConstant.FLG_ON_Y);
                    cnt++;
                }
            }
        }

        if (LVL_NUM_2.equals(lvlNum)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String lvlNumDetail = sMsg.A.no(i).dsContrMachLvlNum.getValue();
                BigDecimal dsContrPkDetail = sMsg.A.no(i).dsContrPk_P.getValue();
                BigDecimal dsContrDtlPkDetail = sMsg.A.no(i).dsContrDtlPk.getValue();
                if (LVL_NUM_3.equals(lvlNumDetail) && dsContrPk.compareTo(dsContrPkDetail) == 0 && dsContrDtlPk.compareTo(dsContrDtlPkDetail) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxBtnFlg, ZYPConstant.FLG_ON_Y);
                    cnt++;
                }
            }
        }

        copyFromSMsgOntoCmsg(cMsg, sMsg);

        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.getValidCount() - 1);
        if (cMsg.xxPageShowOfNum.getValueInt() - cnt >= 1) {
            cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() - cnt);
        } else {
            cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowToNum.getValueInt());
        }
        // mod end 2016/11/11 CSA QC#4210
    }

    /**
     * Execute Expansion
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void executeExpansion(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        int targetIdxNum = cMsg.xxNum.getValueInt();
        BigDecimal dsContrPk = cMsg.A.no(targetIdxNum).dsContrPk_P.getValue();
        String lvlNum = cMsg.A.no(targetIdxNum).dsContrMachLvlNum.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(targetIdxNum).dsContrDtlPk.getValue();
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetIdxNum).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(cMsg.A.no(targetIdxNum).xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
        copyCurrentPageToSMsg(cMsg, sMsg);

        // mod start 2016/11/11 CSA QC#4210
        int cnt = 0;
        if (LVL_NUM_1.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                String lvlNumDetail = sMsg.A.no(j).dsContrMachLvlNum.getValue();
                BigDecimal dsContrPkDetail = sMsg.A.no(j).dsContrPk_P.getValue();
                if (!LVL_NUM_1.equals(lvlNumDetail) && dsContrPk.compareTo(dsContrPkDetail) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxBtnFlg, ZYPConstant.FLG_OFF_N);
                    cnt++;

                    if (LVL_NUM_2.equals(lvlNumDetail)) {
                        // START 2019/01/10 K.Kitachi [QC#26928, DEL]
//                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxDplyCtrlFlg, ZYPConstant.FLG_ON_Y);
                        // END 2019/01/10 K.Kitachi [QC#26928, DEL]
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(j).xxSmryLineFlg, ZYPConstant.FLG_OFF_N);
                    }
                }
            }
        }

        if (LVL_NUM_2.equals(lvlNum)) {
            for (int i = 0; i < sMsg.A.getValidCount(); i++) {
                String lvlNumDetail = sMsg.A.no(i).dsContrMachLvlNum.getValue();
                BigDecimal dsContrPkDetail = sMsg.A.no(i).dsContrPk_P.getValue();
                BigDecimal dsContrDtlPkDetail = sMsg.A.no(i).dsContrDtlPk.getValue();
                if (LVL_NUM_3.equals(lvlNumDetail) && dsContrPk.compareTo(dsContrPkDetail) == 0 && dsContrDtlPk.compareTo(dsContrDtlPkDetail) == 0) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxBtnFlg, ZYPConstant.FLG_OFF_N);
                    cnt++;
                }
            }
        }

        copyFromSMsgOntoCmsg(cMsg, sMsg);
        cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.getValidCount() - 1);
        cMsg.xxPageShowOfNum.setValue(cMsg.xxPageShowOfNum.getValueInt() + cnt);
        // mod end 2016/11/11 CSA QC#4210
    }

    /**
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        // mod start 2016/11/11 CSA QC#4210
        int noCmsg = cMsg.A.no(0).xxRowNum_A.getValueInt();
        cMsg.A.clear();
        int count = 0;
        for (int i = noCmsg; i < sMsg.A.getValidCount(); i++) {
            String flg = sMsg.A.no(i).xxBtnFlg.getValue();
            if (count < cMsg.A.length()) {
                if (!ZYPConstant.FLG_ON_Y.equals(flg)) {
                    EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(count), null);
                    count++;
                }
            } else {
                break;
            }
            // mod end 2016/11/11 CSA QC#4210
        }
        cMsg.A.setValidCount(count);
    }

    // mod start 2016/08/08 CSA Defect#12001
    /**
     * copyCurrentPageToSMsg
     * @param cMsg sMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void copyCurrentPageToSMsg(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {
    // mod end 2016/08/08 CSA Defect#12001

        NSAL0730_ACMsgArray acMsgArray = cMsg.A;
        for (int i = 0; i < acMsgArray.getValidCount(); i++) {
            NSAL0730_ACMsg acMsg = acMsgArray.no(i);
            int targetIdxNum = acMsg.xxRowNum_A.getValueInt();

            NSAL0730_ASMsg asMsg = sMsg.A.no(targetIdxNum);
            setValue(asMsg.xxDplyCtrlFlg, acMsg.xxDplyCtrlFlg);
            setValue(asMsg.xxSmryLineFlg, acMsg.xxSmryLineFlg);
            setValue(asMsg.xxChkBox_D1, acMsg.xxChkBox_D1);
            setValue(asMsg.xxChkBox_D2, acMsg.xxChkBox_D2);
            // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//            setValue(asMsg.custPoNum_D2, acMsg.custPoNum_D2);
//            setValue(asMsg.poDt_D2, acMsg.poDt_D2);
            setValue(asMsg.custPoNum_A, acMsg.custPoNum_A);
            setValue(asMsg.poFromDt_A, acMsg.poFromDt_A);
            setValue(asMsg.poDt_A, acMsg.poDt_A);
            // END 2019/01/10 K.Kitachi [QC#26928, MOD]
            setValue(asMsg.xxChkBox_D3, acMsg.xxChkBox_D3);
        }
    }

    // mod start 2016/08/08 CSA Defect#12001
    /**
     * @param asMsg NSAL0730_ASMsg
     * @param tMsg DS_CONTR_CR_CARD_POTMsg
     * @param cMsg NSAL0730CMsg
     * @return true/false
     */
    private static boolean isSameTimeStamp(NSAL0730_ASMsg asMsg, DS_CONTR_CR_CARD_POTMsg tMsg, NSAL0730CMsg cMsg) {

        String preUpTime = asMsg.ezUpTime.getValue();
        String preTimeZone = asMsg.ezUpTimeZone.getValue();
        String currUpTime = tMsg.ezUpTime.getValue();
        String currTimeZone = tMsg.ezUpTimeZone.getValue();
        if (!ZYPDateUtil.isSameTimeStamp(preUpTime, preTimeZone, currUpTime, currTimeZone)) {
            cMsg.setMessageInfo(NZZM0003E);
            return false;
        }
        return true;
    }
    // mod end 2016/08/08 CSA Defect#12001

    // START 2017/10/04 K.Kojima [QC#20523,ADD]
    private static void releaseRenewalHoldForPO(NSAL0730CMsg cMsg, String slsDt) {
        BigDecimal preDsContrPk = BigDecimal.ZERO;
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).dsContrPk_P)) {
                continue;
            }
            BigDecimal dsContrPk = cMsg.A.no(i).dsContrPk_P.getValue();
            if (preDsContrPk.compareTo(cMsg.A.no(i).dsContrPk_P.getValue()) == 0) {
                continue;
            }
            releaseRenewalHoldForPOTargetContract(cMsg.glblCmpyCd.getValue(), dsContrPk, slsDt);
            preDsContrPk = dsContrPk;
        }
    }

    private static void releaseRenewalHoldForPOTargetContract(String glblCmpyCd, BigDecimal dsContrPk, String slsDt) {
        // QC#55590 Mod Start
        String dsPoReqFlg = (String) NSAL0730Query.getInstance().getDsPoReqFlg(glblCmpyCd, dsContrPk);
        List<BigDecimal> dsContrPrcEffPkList = new ArrayList<BigDecimal>();
        if (ZYPConstant.FLG_OFF_N.equals(dsPoReqFlg)) {
            dsContrPrcEffPkList = (List<BigDecimal>) NSAL0730Query.getInstance().getRenewalHoldforPoReleaseDsContrPrcEffPk4NonPo(glblCmpyCd, dsContrPk).getResultObject();
        } else {
            dsContrPrcEffPkList = (List<BigDecimal>) NSAL0730Query.getInstance().getRenewalHoldforPoReleaseDsContrPrcEffPk(glblCmpyCd, dsContrPk).getResultObject();
        }
        // QC#55590 Mod End
        if (dsContrPrcEffPkList == null || dsContrPrcEffPkList.size() == 0) {
            return;
        }
        List<BigDecimal> dsContrDtlPkList = new ArrayList<BigDecimal>();
        for (int i = 0; i < dsContrPrcEffPkList.size(); i++) {
            BigDecimal dsContrPrcEffPk = dsContrPrcEffPkList.get(i);
            DS_CONTR_PRC_EFFTMsg tMsg = NSAL0730Query.getInstance().getDsContrPrcEff(glblCmpyCd, dsContrPrcEffPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffStsCd, getNewStatus(slsDt, tMsg.contrPrcEffFromDt.getValue()));
            EZDTBLAccessor.update(tMsg);
            if (ZYPConstant.FLG_ON_Y.equals(tMsg.baseChrgFlg.getValue())) {
                callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffPk, tMsg.contrPrcEffFromDt.getValue(), tMsg.contrPrcEffThruDt.getValue(), DS_CONTR_TRK_TP.BASE_CHARGE_PE);
            } else {
                callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue(), tMsg.dsContrBllgMtrPk.getValue(), dsContrPrcEffPk, tMsg.contrPrcEffFromDt.getValue(), tMsg.contrPrcEffThruDt.getValue(), DS_CONTR_TRK_TP.USAGE_CHARGE_PE);
            }
            if (!dsContrDtlPkList.contains(tMsg.dsContrDtlPk.getValue())) {
                dsContrDtlPkList.add(tMsg.dsContrDtlPk.getValue());
            }
        }
        if (dsContrDtlPkList.size() == 0) {
            return;
        }
        for (int i = 0; i < dsContrDtlPkList.size(); i++) {
            BigDecimal dsContrDtlPk = dsContrDtlPkList.get(i);
            DS_CONTR_DTLTMsg tMsg = NSAL0730Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlStsCd, getNewStatus(slsDt, tMsg.contrEffFromDt.getValue()));
            EZDTBLAccessor.update(tMsg);
            callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsg.dsContrDtlPk.getValue());
            if (!dsContrDtlPkList.contains(tMsg.dsContrDtlPk.getValue())) {
                dsContrDtlPkList.add(tMsg.dsContrDtlPk.getValue());
            }
            if (DS_CONTR_DTL_TP.FLEET.equals(tMsg.dsContrDtlTpCd.getValue())) {
                List<BigDecimal> dsContrDtlPkListForFleetMachine = (List<BigDecimal>) NSAL0730Query.getInstance().getRenewalHoldforPoReleaseDsContrDtlPk(glblCmpyCd, dsContrDtlPk).getResultObject();
                if (dsContrDtlPkListForFleetMachine == null || dsContrDtlPkListForFleetMachine.size() == 0) {
                    continue;
                }
                for (int m = 0; m < dsContrDtlPkListForFleetMachine.size(); m++) {
                    dsContrDtlPk = dsContrDtlPkListForFleetMachine.get(m);
                    DS_CONTR_DTLTMsg tMsgMachine = NSAL0730Query.getInstance().getDsContrDtl(glblCmpyCd, dsContrDtlPk);
                    ZYPEZDItemValueSetter.setValue(tMsgMachine.dsContrDtlStsCd, getNewStatus(slsDt, tMsgMachine.contrEffFromDt.getValue()));
                    EZDTBLAccessor.update(tMsgMachine);
                    callContractTrackingAPIforRenewalHold(glblCmpyCd, dsContrPk, tMsgMachine.dsContrDtlPk.getValue());
                }
            }
        }
    }

    private static boolean callContractTrackingAPIforRenewalHold(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, DS_CONTR_TRK_TP.BASE_CHARGE);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private static boolean callContractTrackingAPIforRenewalHold(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, String contrPrcEffFromDt, String contrPrcEffThruDt, String dsContrTrkTpCd) {
        NSZC077001PMsg pMsg = new NSZC077001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ContrTrkProcMode.CONTRACT_MODE_CHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrTrkTpCd, dsContrTrkTpCd);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPk, dsContrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffFromDt, contrPrcEffFromDt);
        ZYPEZDItemValueSetter.setValue(pMsg.contrPrcEffThruDt, contrPrcEffThruDt);
        NSZC077001 api = new NSZC077001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(pMsg)) {
            return false;
        }
        return true;
    }

    private static String getNewStatus(String slsDt, String effFromDt) {
        String dsContrDtlStsCd = null;
        if (slsDt.compareTo(effFromDt) >= 0) {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.ACTIVE;
        } else {
            dsContrDtlStsCd = DS_CONTR_DTL_STS.SIGNED;
        }
        return dsContrDtlStsCd;
    }
    // END 2017/10/04 K.Kojima [QC#20523,ADD]

    // START 2019/01/10 K.Kitachi [QC#26928, ADD]
    /**
     * Is Exist Multiple Row
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @param sMsgIdx int
     * @return true/false
     */
    public static boolean isExistMultRow(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, int sMsgIdx) {

        String lvlNum = sMsg.A.no(sMsgIdx).dsContrMachLvlNum.getValue();
        BigDecimal dsContrPk = sMsg.A.no(sMsgIdx).dsContrPk_P.getValue();
        BigDecimal dsContrDtlPk = sMsg.A.no(sMsgIdx).dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = sMsg.A.no(sMsgIdx).dsContrBllgMtrPk.getValue();

        if (LVL_NUM_1.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (sMsgIdx == j) {
                    continue;
                }
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0) {
                    return true;
                }
            }
        }
        if (LVL_NUM_2.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (sMsgIdx == j) {
                    continue;
                }
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0 && dsContrDtlPk.compareTo(sMsg.A.no(j).dsContrDtlPk.getValue()) == 0) {
                    return true;
                }
            }
        }
        if (LVL_NUM_3.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (sMsgIdx == j) {
                    continue;
                }
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0 && dsContrDtlPk.compareTo(sMsg.A.no(j).dsContrDtlPk.getValue()) == 0) {
                    if ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(sMsg.A.no(j).dsContrBllgMtrPk))
                            || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(sMsg.A.no(j).dsContrBllgMtrPk) && dsContrBllgMtrPk.compareTo(sMsg.A.no(j).dsContrBllgMtrPk.getValue()) == 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Is First Row
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return true/false
     */
    public static boolean isFirstRow(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        int targetIdxNum = cMsg.xxNum.getValueInt();
        String lvlNum = cMsg.A.no(targetIdxNum).dsContrMachLvlNum.getValue();
        BigDecimal dsContrPk = cMsg.A.no(targetIdxNum).dsContrPk_P.getValue();
        BigDecimal dsContrDtlPk = cMsg.A.no(targetIdxNum).dsContrDtlPk.getValue();
        BigDecimal dsContrBllgMtrPk = cMsg.A.no(targetIdxNum).dsContrBllgMtrPk.getValue();
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt();
        int sMsgIdx = targetIdxNum + pageShowFromNum - 1;

        if (LVL_NUM_1.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0) {
                    if (j == sMsgIdx) {
                        return true;
                    }
                    return false;
                }
            }
        }
        if (LVL_NUM_2.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0 && dsContrDtlPk.compareTo(sMsg.A.no(j).dsContrDtlPk.getValue()) == 0) {
                    if (j == sMsgIdx) {
                        return true;
                    }
                    return false;
                }
            }
        }
        if (LVL_NUM_3.equals(lvlNum)) {
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (lvlNum.equals(sMsg.A.no(j).dsContrMachLvlNum.getValue()) && dsContrPk.compareTo(sMsg.A.no(j).dsContrPk_P.getValue()) == 0 && dsContrDtlPk.compareTo(sMsg.A.no(j).dsContrDtlPk.getValue()) == 0) {
                    if ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(sMsg.A.no(j).dsContrBllgMtrPk))
                            || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(sMsg.A.no(j).dsContrBllgMtrPk) && dsContrBllgMtrPk.compareTo(sMsg.A.no(j).dsContrBllgMtrPk.getValue()) == 0)) {
                        if (j == sMsgIdx) {
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Add Row
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @param addRow int
     */
    public static void addRow(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, int addRow) {
        addRow(cMsg, sMsg, addRow, null, null, null);
    }

    /**
     * Add Row
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @param addRow int
     * @param custPoNum String
     * @param poFromDt String
     * @param poDt String
     */
    public static void addRow(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, int addRow, String custPoNum, String poFromDt, String poDt) {
        int asMsgLastIdx = sMsg.A.getValidCount();
        for (int idx = asMsgLastIdx; idx >= addRow; idx--) {
            EZDMsg.copy(sMsg.A.no(idx - 1), null, sMsg.A.no(idx), null);
        }
        sMsg.A.setValidCount(asMsgLastIdx + 1);

        sMsg.A.no(addRow).dsContrCrCardPoPk.clear();
        sMsg.A.no(addRow).xxScrItem34Txt.clear();
        sMsg.A.no(addRow).serNum.clear();
        sMsg.A.no(addRow).mtrLbDescTxt.clear();
        sMsg.A.no(addRow).custPoNum_A.clear();
        sMsg.A.no(addRow).poFromDt_A.clear();
        sMsg.A.no(addRow).poDt_A.clear();
        sMsg.A.no(addRow).xxChkBox_D1.clear();
        sMsg.A.no(addRow).xxChkBox_D2.clear();
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow).leaseCmpyFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_N);
        if (ZYPCommonFunc.hasValue(custPoNum)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow).custPoNum_A, custPoNum);
        }
        if (ZYPCommonFunc.hasValue(poFromDt)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow).poFromDt_A, poFromDt);
        }
        if (ZYPCommonFunc.hasValue(poDt)) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(addRow).poDt_A, poDt);
        }

        for (int idx = addRow; idx < sMsg.A.getValidCount(); idx++) {
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(idx).xxRowNum_A, sMsg.A.no(idx).xxRowNum_A.getValue().add(BigDecimal.ONE));
        }
    }

    /**
     * Get Add Row
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return int
     */
    public static int getAddRow(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        String lvlNum = null;
        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        BigDecimal dsContrBllgMtrPk = null;

        int targetIdxNum = cMsg.xxNum.getValueInt();
        int pageShowFromNum = cMsg.xxPageShowFromNum.getValueInt();
        int sMsgIdx = targetIdxNum + pageShowFromNum - 1;
        int asMsgLastIdx = sMsg.A.getValidCount();

        String targetLvlNum = sMsg.A.no(sMsgIdx).dsContrMachLvlNum.getValue();
        BigDecimal targetDsContrPk = sMsg.A.no(sMsgIdx).dsContrPk_P.getValue();
        BigDecimal targetDsContrDtlPk = sMsg.A.no(sMsgIdx).dsContrDtlPk.getValue();
        BigDecimal targetDsContrBllgMtrPk = sMsg.A.no(sMsgIdx).dsContrBllgMtrPk.getValue();

        for (int i = sMsgIdx + 1; i < asMsgLastIdx; i++) {
            lvlNum = sMsg.A.no(i).dsContrMachLvlNum.getValue();
            dsContrPk = sMsg.A.no(i).dsContrPk_P.getValue();
            dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValue();
            dsContrBllgMtrPk = sMsg.A.no(i).dsContrBllgMtrPk.getValue();

            if (LVL_NUM_1.equals(lvlNum)) {
                if (lvlNum.equals(targetLvlNum) && dsContrPk.compareTo(targetDsContrPk) == 0) {
                    continue;
                }
            }
            if (LVL_NUM_2.equals(lvlNum)) {
                if (lvlNum.equals(targetLvlNum) && dsContrPk.compareTo(targetDsContrPk) == 0 && dsContrDtlPk.compareTo(targetDsContrDtlPk) == 0) {
                    continue;
                }
            }
            if (LVL_NUM_3.equals(lvlNum)) {
                if (lvlNum.equals(targetLvlNum)
                        && dsContrPk.compareTo(targetDsContrPk) == 0
                        && dsContrDtlPk.compareTo(targetDsContrDtlPk) == 0
                        && ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(targetDsContrBllgMtrPk)) || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(targetDsContrBllgMtrPk) && dsContrBllgMtrPk
                                .compareTo(targetDsContrBllgMtrPk) == 0))) {
                    continue;
                }
            }
            return i;
        }
        return asMsgLastIdx;
    }

    /**
     * Get Add Rows
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return int
     */
    public static List<Integer> getAddRows(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        String lvlNum = null;
        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        BigDecimal dsContrBllgMtrPk = null;
        String targetLvlNum = null;
        BigDecimal targetDsContrPk = null;
        BigDecimal targetDsContrDtlPk = null;
        BigDecimal targetDsContrBllgMtrPk = null;

        List<Integer> selectedRows = new ArrayList<Integer>();
        List<Integer> d1SelectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D1", ZYPConstant.CHKBOX_ON_Y);
        for (int d1Row : d1SelectedRows) {
            selectedRows.add(d1Row);
        }
        List<Integer> d2SelectedRows = ZYPTableUtil.getSelectedRows(sMsg.A, "xxChkBox_D2", ZYPConstant.CHKBOX_ON_Y);
        for (int d2Row : d2SelectedRows) {
            selectedRows.add(d2Row);
        }

        int asMsgLastIdx = sMsg.A.getValidCount();
        List<Integer> addRows = new ArrayList<Integer>();
        for (int selectedRow : selectedRows) {

            targetLvlNum = sMsg.A.no(selectedRow).dsContrMachLvlNum.getValue();
            targetDsContrPk = sMsg.A.no(selectedRow).dsContrPk_P.getValue();
            targetDsContrDtlPk = sMsg.A.no(selectedRow).dsContrDtlPk.getValue();
            targetDsContrBllgMtrPk = sMsg.A.no(selectedRow).dsContrBllgMtrPk.getValue();

            for (int i = selectedRow + 1; i < asMsgLastIdx; i++) {
                lvlNum = sMsg.A.no(i).dsContrMachLvlNum.getValue();
                dsContrPk = sMsg.A.no(i).dsContrPk_P.getValue();
                dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValue();
                dsContrBllgMtrPk = sMsg.A.no(i).dsContrBllgMtrPk.getValue();

                if (LVL_NUM_1.equals(lvlNum)) {
                    if (lvlNum.equals(targetLvlNum) && dsContrPk.compareTo(targetDsContrPk) == 0) {
                        continue;
                    }
                }
                if (LVL_NUM_2.equals(lvlNum)) {
                    if (lvlNum.equals(targetLvlNum) && dsContrPk.compareTo(targetDsContrPk) == 0 && dsContrDtlPk.compareTo(targetDsContrDtlPk) == 0) {
                        continue;
                    }
                }
                if (LVL_NUM_3.equals(lvlNum)) {
                    if (lvlNum.equals(targetLvlNum)
                            && dsContrPk.compareTo(targetDsContrPk) == 0
                            && dsContrDtlPk.compareTo(targetDsContrDtlPk) == 0
                            && ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(targetDsContrBllgMtrPk)) || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(targetDsContrBllgMtrPk) && dsContrBllgMtrPk
                                    .compareTo(targetDsContrBllgMtrPk) == 0))) {
                        continue;
                    }
                }
                addRows.add(i);
                break;
            }
        }
        if (selectedRows.size() != addRows.size()) {
            addRows.add(asMsgLastIdx);
        }
        Collections.sort(addRows);
        Collections.reverse(addRows);
        return addRows;
    }

    /**
     * Select Hidden Check Box
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     */
    public static void selectHiddenCheckBox(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        String lvlNum = null;
        BigDecimal dsContrPk = null;
        BigDecimal dsContrDtlPk = null;
        BigDecimal dsContrBllgMtrPk = null;
        String preLvlNum = null;
        BigDecimal preDsContrPk = null;
        BigDecimal preDsContrDtlPk = null;
        BigDecimal preDsContrBllgMtrPk = null;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            lvlNum = sMsg.A.no(i).dsContrMachLvlNum.getValue();
            dsContrPk = sMsg.A.no(i).dsContrPk_P.getValue();
            dsContrDtlPk = sMsg.A.no(i).dsContrDtlPk.getValue();
            dsContrBllgMtrPk = sMsg.A.no(i).dsContrBllgMtrPk.getValue();

            if (ZYPCommonFunc.hasValue(preDsContrPk)) {
                if (LVL_NUM_1.equals(lvlNum)) {
                    if (lvlNum.equals(preLvlNum) && dsContrPk.compareTo(preDsContrPk) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_D1, sMsg.A.no(i - 1).xxChkBox_D1);
                    }
                }
                if (LVL_NUM_2.equals(lvlNum)) {
                    if (lvlNum.equals(preLvlNum) && dsContrPk.compareTo(preDsContrPk) == 0 && dsContrDtlPk.compareTo(preDsContrDtlPk) == 0) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_D1, sMsg.A.no(i - 1).xxChkBox_D1);
                    }
                }
                if (LVL_NUM_3.equals(lvlNum)) {
                    if (lvlNum.equals(preLvlNum)
                            && dsContrPk.compareTo(preDsContrPk) == 0
                            && dsContrDtlPk.compareTo(preDsContrDtlPk) == 0
                            && ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(preDsContrBllgMtrPk)) || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(preDsContrBllgMtrPk) && dsContrBllgMtrPk
                                    .compareTo(preDsContrBllgMtrPk) == 0))) {
                        ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxChkBox_D2, sMsg.A.no(i - 1).xxChkBox_D2);
                    }
                }
            }

            preLvlNum = lvlNum;
            preDsContrPk = dsContrPk;
            preDsContrDtlPk = dsContrDtlPk;
            preDsContrBllgMtrPk = dsContrBllgMtrPk;
        }
    }

    /**
     * Relation Check
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @return true/false
     */
    public static boolean relationCheck(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg) {

        boolean rtrnFlg = true;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NSAL0730_ASMsg asMsg = sMsg.A.no(i);
            String lvlNum = asMsg.dsContrMachLvlNum.getValue();
            BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
            BigDecimal dsContrDtlPk = asMsg.dsContrDtlPk.getValue();
            BigDecimal dsContrBllgMtrPk = asMsg.dsContrBllgMtrPk.getValue();
            String poFromDt = asMsg.poFromDt_A.getValue();
            String poDt = asMsg.poDt_A.getValue();
            if (!ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_D1.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(asMsg.xxChkBox_D2.getValue())) {
                continue;
            }
            boolean isError = false;
            // START 2023/05/24 K.Watanabe [QC#61498, MOD]
            boolean dsPoReqFlg = getPoReqForContr(cMsg.glblCmpyCd.getValue(), asMsg);
//            if (isExistMultRow(cMsg, sMsg, i)) {
            if (dsPoReqFlg && LVL_NUM_1.equals(lvlNum) && isExistMultRow(cMsg, sMsg, i)) {
            // END 2023/05/24 K.Watanabe [QC#61498, MOD]
                if (!ZYPCommonFunc.hasValue(asMsg.custPoNum_A)) {
                    asMsg.custPoNum_A.setErrorInfo(1, NSAM0645E, new String[] {"New PO#" });
                    isError = true;
                }
                if (!ZYPCommonFunc.hasValue(asMsg.poFromDt_A)) {
                    asMsg.poFromDt_A.setErrorInfo(1, NSAM0645E, new String[] {"From Date" });
                    isError = true;
                }
                if (!ZYPCommonFunc.hasValue(asMsg.poDt_A)) {
                    asMsg.poDt_A.setErrorInfo(1, NSAM0645E, new String[] {"Thru Date" });
                    isError = true;
                }
            }
            if (!NSXC001001ContrValidation.checkConsistentPoDt(poFromDt, poDt)) {
                asMsg.poFromDt_A.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
                asMsg.poDt_A.setErrorInfo(1, NSAM0743E, new String[] {"Thru Date", "From Date" });
                isError = true;
            }
            if (isError) {
                rtrnFlg = false;
                continue;
            }
            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }
                NSAL0730_ASMsg cmpAsMsg = sMsg.A.no(j);
                String cmpLvlNum = cmpAsMsg.dsContrMachLvlNum.getValue();
                BigDecimal cmpDsContrPk = cmpAsMsg.dsContrPk_P.getValue();
                BigDecimal cmpDsContrDtlPk = cmpAsMsg.dsContrDtlPk.getValue();
                BigDecimal cmpDsContrBllgMtrPk = cmpAsMsg.dsContrBllgMtrPk.getValue();
                String cmpPoFromDt = cmpAsMsg.poFromDt_A.getValue();
                String cmpPoDt = cmpAsMsg.poDt_A.getValue();
                if (!ZYPConstant.CHKBOX_ON_Y.equals(cmpAsMsg.xxChkBox_D1.getValue()) && !ZYPConstant.CHKBOX_ON_Y.equals(cmpAsMsg.xxChkBox_D2.getValue())) {
                    continue;
                }
                boolean isSameLvl = false;
                if (LVL_NUM_1.equals(lvlNum)) {
                    if (lvlNum.equals(cmpLvlNum) && dsContrPk.compareTo(cmpDsContrPk) == 0) {
                        isSameLvl = true;
                    }
                }
                if (LVL_NUM_2.equals(lvlNum)) {
                    if (lvlNum.equals(cmpLvlNum) && dsContrPk.compareTo(cmpDsContrPk) == 0 && dsContrDtlPk.compareTo(cmpDsContrDtlPk) == 0) {
                        isSameLvl = true;
                    }
                }
                if (LVL_NUM_3.equals(lvlNum)) {
                    if (lvlNum.equals(cmpLvlNum)
                            && dsContrPk.compareTo(cmpDsContrPk) == 0
                            && dsContrDtlPk.compareTo(cmpDsContrDtlPk) == 0
                            && ((!ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && !ZYPCommonFunc.hasValue(cmpDsContrBllgMtrPk)) || (ZYPCommonFunc.hasValue(dsContrBllgMtrPk) && ZYPCommonFunc.hasValue(cmpDsContrBllgMtrPk) && dsContrBllgMtrPk
                                    .compareTo(cmpDsContrBllgMtrPk) == 0))) {
                        isSameLvl = true;
                    }
                }
                if (!isSameLvl) {
                    continue;
                }
                if (ZYPDateUtil.compare(poFromDt, cmpPoDt) <= 0 && ZYPDateUtil.compare(poDt, cmpPoFromDt) >= 0) {
                    asMsg.poFromDt_A.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
                    asMsg.poDt_A.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
                    cmpAsMsg.poFromDt_A.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
                    cmpAsMsg.poDt_A.setErrorInfo(1, NSAM0035E, new String[] {"PO Date" });
                    rtrnFlg = false;
                }
            }
        }
        return rtrnFlg;
    }

    /**
     * Pagenation
     * @param cMsg NSAL0730CMsg
     * @param sMsg NSAL0730SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL0730CMsg cMsg, NSAL0730SMsg sMsg, int pageFrom) {

        int sMsgIdx = pageFrom;
        int cMsgIdx = 0;
        for (; cMsgIdx < cMsg.A.length();) {
            if (sMsgIdx < sMsg.A.getValidCount()) {
                if (!ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(sMsgIdx).xxBtnFlg.getValue())) {
                    EZDMsg.copy(sMsg.A.get(sMsgIdx), null, cMsg.A.get(cMsgIdx), null);
                    cMsgIdx++;
                }
                sMsgIdx++;
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(cMsgIdx);

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());

        int count = 0;
        for (int idx = 0; idx < sMsg.A.getValidCount(); idx++) {
            if (ZYPConstant.FLG_ON_Y.equals(sMsg.A.no(idx).xxBtnFlg.getValue())) {
                count++;
            }
        }
        cMsg.xxPageShowOfNum.setValue(sMsg.A.getValidCount() - count);
   }
    // END 2019/01/10 K.Kitachi [QC#26928, ADD]

    // START 2023/05/24 K.Watanabe [QC#61498,ADD]
    public static boolean getPoReqForContr(String glblCmpyCd, NSAL0730_ASMsg asMsg) {
        BigDecimal dsContrPk = asMsg.dsContrPk_P.getValue();
        if (!hasValue(dsContrPk)) {
            return false;
        }

        boolean reqFlg = false;
        String slsDt = ZYPDateUtil.getSalesDate(glblCmpyCd, BIZ_ID);

        DS_CONTRTMsg dsContrTMsg = NSAL0730Query.getInstance().getDsContr(glblCmpyCd, dsContrPk);
        if (dsContrTMsg == null) {
            return reqFlg;
        }

        String dsAcctNum = dsContrTMsg.dsAcctNum.getValue();
        String billToCustCd = dsContrTMsg.altPayerCustCd.getValue();
        if (hasValue(billToCustCd)) {
            reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);
        }

        billToCustCd = dsContrTMsg.leaseCmpyCd.getValue();
        if (!reqFlg && hasValue(billToCustCd)) {
            reqFlg = NSXC001001ContrValidation.checkPoRequired(glblCmpyCd, slsDt, dsAcctNum, billToCustCd, ONBATCH_TYPE.ONLINE);
        }
        return reqFlg;
    }
    // END 2023/05/24 K.Watanabe [QC#61498, ADD]
}

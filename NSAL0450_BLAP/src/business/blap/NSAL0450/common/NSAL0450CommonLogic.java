/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0450.common;

import static business.blap.NSAL0450.constant.NSAL0450Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsgArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsgArray;

import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdForOutListInfoBean;
import com.canon.cusa.s21.common.NSX.NSXC004001.NSXC004001GetDefCoaTrxCd;
import com.canon.cusa.s21.common.NSX.NSXC004001.GetDefCoaTrxCdInfoBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

import business.blap.NSAL0450.NSAL0450CMsg;
import business.blap.NSAL0450.NSAL0450Query;
import business.blap.NSAL0450.NSAL0450SMsg;
import business.blap.NSAL0450.NSAL0450_ACMsg;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/12   Hitachi         J.Kim           Create          N/A
 * 2015/12/15   Hitachi         T.Kanasaka      Update          QC#1758
 * 2016/08/04   Hitachi         T.Mizuki        Update          QC#12767
 * 2016/08/26   Hitachi         A.Kohinata      Update          QC#13778
 * 2016/10/26   Hitachi         T.Kanasaka      Update          QC#15520
 * 2019/01/21   Fujitsu         R.Nakamura      Update          QC#29782
 *</pre>
 */
public class NSAL0450CommonLogic {

    /**
     * Search DS Contract Info.
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void searchDSContractInfo(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        NSAL0450Query query = NSAL0450Query.getInstance();

        S21SsmEZDResult ssmResult = query.getDSContractInfo(cMsg);
        if (ssmResult == null || !ssmResult.isCodeNormal()) {
            return;
        } else {

            List<Map<String, Object>> dSContractInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();

            for (Map<String, Object> dSContractInfo : dSContractInfoList) {

                setContractInfo(ssmResult, sMsg, dSContractInfoList);
                String dsContrCatgCd = (String) dSContractInfo.get("DS_CONTR_CATG_CD");
                // 'REG'(Non Fleet)
                if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                    searchNonFleetMachineInfo(cMsg, sMsg);
                    // 'FLT'(Fleet)
                } else if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                    searchFleetMachineInfo(cMsg, sMsg);
                    // 'AGG'(Aggregate)
                } else if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
                    searchAggregateMachineInfo(cMsg, sMsg);
                }
            }
        }
    }

    private static void searchNonFleetMachineInfo(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0450Query.getInstance().searchNonFleetMachineInfo(cMsg, sMsg, SEARCH_LIMIT_COUNT + 1);

        if (ssmResult.isCodeNormal()) {
            // result > 10000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()) });
            } else {
                setMachineInfo(ssmResult, sMsg);
            }
        }
    }

    private static void searchFleetMachineInfo(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0450Query.getInstance().searchFleetMachineInfo(cMsg, sMsg, SEARCH_LIMIT_COUNT + 1);

        if (ssmResult.isCodeNormal()) {
            // result > 10000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()) });
            } else {
                setMachineInfo(ssmResult, sMsg);
            }
        }

    }

    private static void searchAggregateMachineInfo(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        S21SsmEZDResult ssmResult = NSAL0450Query.getInstance().searchAggregateMachineInfo(cMsg, sMsg, SEARCH_LIMIT_COUNT + 1);

        if (ssmResult.isCodeNormal()) {
            // result > 10000
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NSAM0024W, new String[] {Integer.toString(sMsg.A.length()) });
            } else {
                setMachineInfo(ssmResult, sMsg);
            }
        }
    }

    /**
     * setDetailList
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void setDetailList(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            String dsContrCatgCd = sMsg.A.no(i).csaPrcCatgCd.getValue();
            // START 2016/10/26 T.Kanasaka [QC#15520, MOD]
//            if (hasValue(sMsg.A.no(i).serNum)) {
            if (LEVEL_NB_2.equals(sMsg.A.no(i).xxFlgNm.getValue())) {
            // END 2016/10/26 T.Kanasaka [QC#15520, MOD]
                if (!setDefaultBranchAccount(cMsg, sMsg, i, dsContrCatgCd)) {
                    return;
                }
            }
        }

        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaBrCd_L, sMsg.A.no(1).coaBrCd_L);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaAfflAcctNm, sMsg.A.no(1).coaAfflAcctNm);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaCmpyCd, sMsg.A.no(1).coaCmpyCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaExtnCd, sMsg.A.no(1).coaExtnCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaBrCd, sMsg.A.no(1).coaBrCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaCcCd, sMsg.A.no(1).coaCcCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaAcctCd, sMsg.A.no(1).coaAcctCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaProjCd, sMsg.A.no(1).coaProjCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaProdCd, sMsg.A.no(1).coaProdCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaAfflCd, sMsg.A.no(1).coaAfflCd);
        ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).coaChCd, sMsg.A.no(1).coaChCd);
        ZYPEZDItemValueSetter.setValue(sMsg.coaAfflAcctNm_H, sMsg.A.no(1).coaAfflAcctNm);
    }

    private static boolean setDefaultBranchAccount(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg, int index, String dsContrCatgCd) {

        // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
        GetDefCoaTrxCdInfoBean gdctciInfoBeean = new GetDefCoaTrxCdInfoBean();
        gdctciInfoBeean.setGlblCmpyCd(cMsg.glblCmpyCd.getValue());
        if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            gdctciInfoBeean.setSvcMachMstrPk(null);
            gdctciInfoBeean.setDsContrDtlPk(sMsg.A.no(index).dsContrDtlPk.getValue());
            gdctciInfoBeean.setDsContrBllgMtrPk(null);
            gdctciInfoBeean.setDsContrAddlChrgPk(null);
            gdctciInfoBeean.setMdseCd(null);
            gdctciInfoBeean.setDsAcctNum(sMsg.A.no(index).dsAcctNum.getValue());
        } else {
            gdctciInfoBeean.setSvcMachMstrPk(new BigDecimal(sMsg.A.no(index).svcMachMstrPk.getValueInt()));
            gdctciInfoBeean.setDsContrDtlPk(sMsg.A.no(index).dsContrDtlPk.getValue());
            gdctciInfoBeean.setDsContrBllgMtrPk(null);
            gdctciInfoBeean.setDsContrAddlChrgPk(null);
            gdctciInfoBeean.setMdseCd(sMsg.A.no(index).mdseCd.getValue());
            gdctciInfoBeean.setDsAcctNum(sMsg.A.no(index).dsAcctNum.getValue());
        }
        gdctciInfoBeean.setBaseChrgFlg(ZYPConstant.FLG_ON_Y);
        gdctciInfoBeean.setUsgChrgFlg(ZYPConstant.FLG_OFF_N);
        gdctciInfoBeean.setAddlChrgFlg(ZYPConstant.FLG_OFF_N);

        // NSXC004001
        GetDefCoaTrxCdInfoBean outListInfoBean = NSXC004001GetDefCoaTrxCd.getDefCoaTrxCd(gdctciInfoBeean);
        if (outListInfoBean == null) {
            return false;
        }

        if(setDefaulAccountInfo(outListInfoBean.getOutLisstInfoBean(), sMsg, cMsg, index, INV_LINE_SPL_TP.SERVICE)){
            return true;
        }
        if(setDefaulAccountInfo(outListInfoBean.getOutLisstInfoBean(), sMsg, cMsg, index, INV_LINE_SPL_TP.EQUIPMENT)){
            return true;
        }
        if(setDefaulAccountInfo(outListInfoBean.getOutLisstInfoBean(), sMsg, cMsg, index, INV_LINE_SPL_TP.SUPPLY)){
            return true;
        }
        return false;
        // END 2015/12/15 T.Kanasaka [QC#1758, MOD]
    }

    // START 2015/12/15 T.Kanasaka [QC#1758, ADD]
    private static boolean setDefaulAccountInfo(List<GetDefCoaTrxCdForOutListInfoBean> defCoaTrxCdList, NSAL0450SMsg sMsg, NSAL0450CMsg cMsg, int index, String invLineSplTpCd){
        for (int i = 0; i < defCoaTrxCdList.size(); i++) {
            GetDefCoaTrxCdForOutListInfoBean defCoaTrxCdForOutInfoBean = defCoaTrxCdList.get(i);
            if (defCoaTrxCdForOutInfoBean.getInvLineSplTpCd().equals(invLineSplTpCd)) {
                setDefaulAccountInfo(sMsg, cMsg, index, defCoaTrxCdForOutInfoBean);
                return true;
            }
        }
        return false;
    }
    // END 2015/12/15 T.Kanasaka [QC#1758, ADD]

    private static void setDefaulAccountInfo(NSAL0450SMsg sMsg, NSAL0450CMsg cMsg, int index, GetDefCoaTrxCdForOutListInfoBean bean) {

        String constValue = ZYPCodeDataUtil.getVarCharConstValue("CONT_DELIMITER", cMsg.glblCmpyCd.getValue());

        // mod start 2016/08/04 CSA QC#12767
        // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
        StringBuffer sb = new StringBuffer();
          sb.append(convertNullValue(bean.getCoaCmpyCd()));
          sb.append(constValue);
          sb.append(COA_BR);
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaCcCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaAcctCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaProdCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaChCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaAfflCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaProjCd()));
          sb.append(constValue);
          sb.append(convertNullValue(bean.getCoaExtnCd()));
        // END 2015/12/15 T.Kanasaka [QC#1758, MOD]
        // mod end 2016/08/04 CSA QC#12767
        // START 2016/10/26 T.Kanasaka [QC#15520, MOD]
//        for (int i = index; i < index + 3; i++) {
        for (int i = index; i < sMsg.A.getValidCount(); i++) {
            if (i > index && LEVEL_NB_2.equals(sMsg.A.no(i).xxFlgNm.getValue())) {
                break;
            }
            // END 2016/10/26 T.Kanasaka [QC#15520, MOD]

            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaBrCd_L, bean.getCoaBrCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAfflAcctNm, sb.toString());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCmpyCd, bean.getCoaCmpyCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaExtnCd, bean.getCoaExtnCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaBrCd, COA_BR);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaCcCd, bean.getCoaCcCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAcctCd, bean.getCoaAcctCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProjCd, bean.getCoaProjCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaProdCd, bean.getCoaProdCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaAfflCd, bean.getCoaAfflCd());
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).coaChCd, bean.getCoaChCd());
        }
    }

    // START 2015/12/15 T.Kanasaka [QC#1758, ADD]
    private static String convertNullValue(String val){
        if(hasValue(val)){
            return val;
        }
        return "";
    }
    // END 2015/12/15 T.Kanasaka [QC#1758, ADD]

    private static void setContractInfo(S21SsmEZDResult ssmResult, NSAL0450SMsg sMsg, List<Map<String, Object>> resultList) {

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> dSContractInfo = resultList.get(i);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrPk, (BigDecimal) dSContractInfo.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrNum, (String) dSContractInfo.get("DS_CONTR_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).csaPrcCatgCd, (String) dSContractInfo.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).dsContrDtlPk, (BigDecimal) dSContractInfo.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk, (BigDecimal) dSContractInfo.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcInvChrgTpCd, (String) dSContractInfo.get("SVC_INV_CHRG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).baseChrgFlg, setCount(dSContractInfo.get("DCBA_COUNT")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).usgChrgFlg, setCount(dSContractInfo.get("DCSA_COUNT")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg, setSmryLineFlg(dSContractInfo.get("COLLAPSE")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxFlgNm, setString(dSContractInfo.get("LEVEL_NB")));
        }
    }

    private static void setMachineInfo(S21SsmEZDResult ssmResult, NSAL0450SMsg sMsg) {

        List<Map<String, Object>> dSMachineInfoList = (List<Map<String, Object>>) ssmResult.getResultObject();

        int index = 1;
        for (int i = 0; i < dSMachineInfoList.size(); i++) {
            Map<String, Object> dSContractInfo = dSMachineInfoList.get(i);
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrPk, (BigDecimal) dSContractInfo.get("DS_CONTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).csaPrcCatgCd, (String) dSContractInfo.get("DS_CONTR_CATG_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsAcctNum, (String) dSContractInfo.get("DS_ACCT_NUM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).dsContrDtlPk, (BigDecimal) dSContractInfo.get("DS_CONTR_DTL_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcMachMstrPk, (BigDecimal) dSContractInfo.get("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).serNum, setDetailIndex((String) dSContractInfo.get("SER_NUM"), setString(dSContractInfo.get("LEVEL_NB"))));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseCd, (String) dSContractInfo.get("MDSE_CD"));
            // Add Start 2019/01/21 QC#29782
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).mdseDescShortTxt, setDetailIndex((String) dSContractInfo.get("MDSE_DESC_SHORT_TXT"), setString(dSContractInfo.get("LEVEL_NB"))));
            // Add End 2019/01/21 QC#29782
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).svcInvChrgTpCd, (String) dSContractInfo.get("SVC_INV_CHRG_TP_CD"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).invConslNum, (String) dSContractInfo.get("SVC_INV_CHRG_TP_NM"));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).baseChrgFlg, setCount(dSContractInfo.get("DCBA_COUNT")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).usgChrgFlg, setCount(dSContractInfo.get("DCSA_COUNT")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxSmryLineFlg, setSmryLineFlg(dSContractInfo.get("COLLAPSE")));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxDplyCtrlFlg, setDetailIndex(DPLY_CTRL_FLG, setString(dSContractInfo.get("LEVEL_NB"))));
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(index).xxFlgNm, setString(dSContractInfo.get("LEVEL_NB")));
            index++;
        }
        sMsg.A.setValidCount(dSMachineInfoList.size() + 1);
    }

    private static String setDetailIndex(String num, String level) {

        if (LEVEL_NB_3.equals(level)) {
            return "";
        }
        return num;
    }

    private static String setSmryLineFlg(Object flg) {

        if (flg == null) {
            return "";
        }
        return ZYPConstant.FLG_ON_1;
    }

    private static String setCount(Object cnt) {

        BigDecimal dsContractCount = (BigDecimal) cnt;
        if (dsContractCount.compareTo(BigDecimal.ZERO) > 0) {
            return ZYPConstant.FLG_ON_1;
        }
        return ZYPConstant.FLG_OFF_0;
    }

    private static String setString(Object vaule) {

        if (vaule == null) {
            return "";
        }
        return vaule.toString();
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void copyCMsgToSMsg(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {
        copyCMsgToSMsg(cMsg, sMsg, cMsg.xxPageShowFromNum.getValueInt() - 1);
    }

    /**
     * copyCMsgToSMsg
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @param fromCnt From Count
     */
    public static void copyCMsgToSMsg(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg, int fromCnt) {
        int curCnt = fromCnt;
        int cMsgCnt = 0;
        for (int i = curCnt; i < (curCnt + cMsg.A.getValidCount()); i++) {
            EZDMsg.copy(cMsg.A.no(cMsgCnt), null, sMsg.A.no(i), null);
            cMsgCnt++;
        }
    }

    /**
     * Pagenation
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @param pageFrom int
     */
    public static void pagenation(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg, int pageFrom) {

        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = pageFrom;
        int count = 0;
        for (; i < sAry.getValidCount() && count < cAry.length(); i++) {
            if (hasValue(sMsg.A.no(i).xxSmryLineFlg)) {
                EZDMsg.copy(sAry.get(i), null, cAry.get(count), null);
                count++;
            }
        }
        cAry.setValidCount(count);
        cMsg.coaAfflAcctNm_H.setValue(sMsg.coaAfflAcctNm_H.getValue());

        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cAry.getValidCount());
        cMsg.xxPageShowOfNum.setValue(getTotalPage(cMsg, sMsg));
    }

    /**
     * copySMsgToCMsg
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void copySMsgToCMsg(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {
        // 1page copy(SMsg -> CMsg)
        int count = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (i == cMsg.A.length()) {
                break;
            }
            if (hasValue(sMsg.A.no(i).xxSmryLineFlg)) {
                count++;
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
        }
        cMsg.coaAfflAcctNm_H.setValue(sMsg.coaAfflAcctNm_H.getValue());
    }

    /**
     * TotalPage Count
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     * @return totalCount
     */
    public static int getTotalPage(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        int totalCount = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            if (hasValue(sMsg.A.no(i).xxSmryLineFlg)) {
                totalCount++;
            }
        }
        return totalCount;
    }

    /**
     * copyFromSMsgOntoCmsg
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void copyFromSMsgOntoCmsg(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // copy data from SMsg onto CMsg
        int pagenationFrom = cMsg.xxPageShowFromNum.getValueInt();
        int i = pagenationFrom;
        for (; i < pagenationFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i - pagenationFrom), null);
            } else {
                break;
            }
        }
        cMsg.A.setValidCount(i - pagenationFrom);
        setPageNum(cMsg, (pagenationFrom + 1), (pagenationFrom + cMsg.A.getValidCount()), sMsg.A.getValidCount());
    }

    /**
     * setPageNum
     * @param cMsg NSAL0450CMsg
     * @param fromCnt PageShowFromNum
     * @param toCnt PageShowToNum
     * @param allCnt PageShowOfNum
     */
    public static void setPageNum(NSAL0450CMsg cMsg, int fromCnt, int toCnt, int allCnt) {
        cMsg.xxPageShowFromNum.setValue(fromCnt);
        cMsg.xxPageShowToNum.setValue(toCnt);
        cMsg.xxPageShowOfNum.setValue(allCnt);
    }

    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void copyCurrentPageToSMsgExpansion(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // 1page copy(CMsg -> SMsg)
        int targetIdxNum = cMsg.xxNum.getValueInt();
        NSAL0450_ACMsg acMsg = cMsg.A.no(targetIdxNum);
        BigDecimal contrDtlPkCMsg = acMsg.dsContrDtlPk.getValue();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            BigDecimal contrDtlPkSMsg = sMsg.A.no(i).dsContrDtlPk.getValue();
            if (hasValue(contrDtlPkSMsg) && contrDtlPkCMsg.compareTo(contrDtlPkSMsg) == 0) {
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg, ZYPConstant.FLG_OFF_0);
                if (LEVEL_NB_3.equals(sMsg.A.no(i).xxFlgNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_0);
                }
            }
        }

        // 1page copy(SMsg -> CMsg)
        // add start 2016/08/26 CSA Defect#13778
        ZYPTableUtil.clear(cMsg.A);
        // add end 2016/08/26 CSA Defect#13778
        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int count = 0;
        for (; i < sAry.getValidCount() && count < cAry.length(); i++) {
            String smryLineFlg = sMsg.A.no(i).xxSmryLineFlg.getValue();
            String level = sMsg.A.no(i).xxFlgNm.getValue();
            if ((!LEVEL_NB_3.equals(level)) || (LEVEL_NB_3.equals(level) && ZYPConstant.FLG_OFF_0.equals(smryLineFlg))) {
                EZDMsg.copy(sAry.get(i), null, cAry.get(count), null);
                count++;
            }
        }

        cMsg.A.setValidCount(count);
        cMsg.coaAfflAcctNm_H.setValue(sMsg.coaAfflAcctNm_H.getValue());
    }

    /**
     * copy To BSMsg for Current Page Info
     * @param cMsg NSAL0450CMsg
     * @param sMsg NSAL0450SMsg
     */
    public static void copyCurrentPageToSMsgContraction(NSAL0450CMsg cMsg, NSAL0450SMsg sMsg) {

        // 1page copy(CMsg -> SMsg)
        int targetIdxNum = cMsg.xxNum.getValueInt();
        NSAL0450_ACMsg acMsg = cMsg.A.no(targetIdxNum);
        BigDecimal contrDtlPkCMsg = acMsg.dsContrDtlPk.getValue();

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            BigDecimal contrDtlPkSMsg = sMsg.A.no(i).dsContrDtlPk.getValue();
            if (hasValue(contrDtlPkSMsg) && contrDtlPkCMsg.compareTo(contrDtlPkSMsg) == 0) {
                if (LEVEL_NB_3.equals(sMsg.A.no(i).xxFlgNm.getValue())) {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg, "");
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg, ZYPConstant.FLG_OFF_0);
                } else {
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxSmryLineFlg, ZYPConstant.FLG_ON_1);
                    ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxDplyCtrlFlg, ZYPConstant.FLG_ON_1);
                }
            }
        }

        // 1page copy(SMsg -> CMsg)
        // add start 2016/08/26 CSA Defect#13778
        ZYPTableUtil.clear(cMsg.A);
        // add end 2016/08/26 CSA Defect#13778
        EZDCMsgArray cAry = null;
        EZDSMsgArray sAry = null;

        cAry = cMsg.A;
        sAry = sMsg.A;

        int i = cMsg.xxPageShowFromNum.getValueInt() - 1;
        int count = 0;
        for (; i < sAry.getValidCount() && count < cAry.length(); i++) {
            String smryLineFlg = sMsg.A.no(i).xxSmryLineFlg.getValue();
            String level = sMsg.A.no(i).xxFlgNm.getValue();
            if ((!LEVEL_NB_3.equals(level)) || (LEVEL_NB_3.equals(level) && ZYPConstant.FLG_OFF_0.equals(smryLineFlg))) {
                EZDMsg.copy(sAry.get(i), null, cAry.get(count), null);
                count++;
            }
        }

        cMsg.A.setValidCount(count);
        cMsg.coaAfflAcctNm_H.setValue(sMsg.coaAfflAcctNm_H.getValue());
    }
}

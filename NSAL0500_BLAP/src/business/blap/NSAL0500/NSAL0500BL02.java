/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0500;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static business.blap.NSAL0500.constant.NSAL0500Constant.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import parts.common.EZDCMsg;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0500.common.NSAL0500CommonLogic;
import business.db.DS_CONTR_DTLTMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/01/06   Hitachi         T.Tsuchida      Update          N/A
 * 2016/01/12   Hitachi         T.Tsuchida      Update          QC#2889
 * 2016/01/13   Hitachi         T.Tsuchida      Update          QC#2820
 * 2016/02/09   Hitachi         K.Kasai         Update          QC#3704,3705
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2017/01/19   Hitachi         K.Ochiai        Update          QC#16331
 * 2017/12/04   Hitachi         M.Kidokoro      Update          QC#22598
 * 2018/12/10   Hitachi         K.Fujimoto      Update          QC#29079
 *</pre>
 */
public class NSAL0500BL02 extends S21BusinessHandler implements ZYPConstant {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0500CMsg cMsg = (NSAL0500CMsg) arg0;
        NSAL0500SMsg sMsg = (NSAL0500SMsg) arg1;
        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0500_INIT".equals(screenAplID)) {
                doProcess_NSAL0500_INIT(cMsg, sMsg);
            } else if ("NSAL0500Scrn00_Search_VndNm".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_Search_VndNm(cMsg, sMsg);
            } else if ("NSAL0500Scrn00_CreateNew".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_CreateNew(cMsg, sMsg);
            } else if ("NSAL0500Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_PagePrev(cMsg, sMsg);
            } else if ("NSAL0500Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0500Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_CMN_Submit(cMsg, sMsg);
            // START 2017/01/19 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL0500Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_CMN_Reset(cMsg, sMsg);
            // END 2017/01/19 K.Ochiai [QC#16331,MOD]
            } else if ("NSAL0500_NWAL1130".equals(screenAplID)) {
                doProcess_NSAL0500Scrn00_NWAL1130(cMsg, sMsg);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NSAL0500_INIT(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        // set common information
        setValue(cMsg.glblCmpyCd, getGlobalCompanyCode());
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        setValue(cMsg.slsDt, ZYPDateUtil.getSalesDate());
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
        NSAL0500CommonLogic.createBllgCyclePullDown(cMsg);

        // get recent Sub Contract PK
        if (!hasValue(cMsg.dsSubContrPk)) {
            String salesDt = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
            NSAL0500Query.getInstance().getRecentSubContrPk(cMsg, salesDt);
        }
        NSAL0500Query.getInstance().getContrInfo(cMsg);
        // add start 2016/02/09 CSA Defect#3704,3705
        if (!hasValue(cMsg.effFromDt)) {
            setValue(cMsg.effFromDt, cMsg.contrEffFromDt);
        }
        if (!hasValue(cMsg.effThruDt)) {
            setValue(cMsg.effThruDt, cMsg.contrEffThruDt);
        }
        setDisplayMode(cMsg);
        // add end 2016/02/09 CSA Defect#3704,3705

        // get Service Memo
        if (hasValue(cMsg.dsSubContrPk)) {
            List<String> svcMemoList = NSAL0500Query.getInstance().getSvcCmntTxt(cMsg);
            setCmntTxt(svcMemoList, cMsg.svcCmntTxt_71
                                    , cMsg.svcCmntTxt_72
                                    , cMsg.svcCmntTxt_73
                                    , cMsg.svcCmntTxt_74
                                    , cMsg.svcCmntTxt_75
                                    , cMsg.svcCmntTxt_76
                                    , cMsg.svcCmntTxt_77
                                    , cMsg.svcCmntTxt_78);
        }
        clearItemValues(cMsg.svcCmntTxt_AD);

        // get Billing History
        S21SsmEZDResult ssmResult = NSAL0500Query.getInstance().getContrDtlInfo(cMsg);
        if (!ssmResult.isCodeNormal()) {
            return;
        }
        BigDecimal dsContrPk = getDsContrPk(ssmResult);
        getCustHistInfo(cMsg, sMsg, dsContrPk);
        getMtrHistInfo(cMsg, sMsg);
        getDealHistInfo(cMsg, sMsg);
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        // get Billing Couter of SubContract Pricing.
        BigDecimal dsContrDtlPk = NSAL0500CommonLogic.getDsContrDtlPk(ssmResult, cMsg);
        if (dsContrDtlPk != null) {
            NSAL0500Query.getInstance().getSubContrMtrInfo(cMsg, dsContrDtlPk);
        }
        // Add End   2018/12/10 K.Fujimoto QC#29079
    }

    // add start 2016/02/10 CSA Defect#3704
    /**
     * set Display Mode
     * @param cMsg NSAL0500CMsg
     */
    private void setDisplayMode(NSAL0500CMsg cMsg) {
        String dsContrCtrlStsCd = cMsg.dsContrCtrlStsCd.getValue();
        if (DS_CONTR_CTRL_STS.DRAFT.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.QA_HOLD.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.ENTERED.equals(dsContrCtrlStsCd) || DS_CONTR_CTRL_STS.CANCELLED.equals(dsContrCtrlStsCd)) {
            setValue(cMsg.xxModeCd_FU, DISPLAY_MODE_UPDATE);
        } else {
            setValue(cMsg.xxModeCd_FU, DISPLAY_MODE_FREEZE);
        }
    }
    // add end 2016/02/10 CSA Defect#3704

    private BigDecimal getDsContrPk(S21SsmEZDResult ssmResult) {
        BigDecimal rtnVal = null;
        String dsContrCatgCd = null;

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
        if (resultList.size() > 0) {
            Map<String, Object> resultMap = (Map<String, Object>) resultList.get(0);
            dsContrCatgCd = (String) resultMap.get(DS_CONTR_CATG_CD.toString());
            if (DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
                rtnVal = (BigDecimal) resultMap.get(DS_CONTR_PK.toString());
            }
        }
        return rtnVal;
    }

    private void getCustHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg, BigDecimal dsContrPk) {
        S21SsmEZDResult ssmResult = NSAL0500Query.getInstance().getCustHistInfo(cMsg, sMsg, dsContrPk);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.A.length();
            }

            // START 2017/12/04 M.Kidokoro [QC#22598, ADD]
            BigDecimal invLineDealNetAmt;
            for (int index = 0; index < sMsg.A.getValidCount(); index++) {
                if (INV_TP.CREDIT_MEMO.equals(sMsg.A.no(index).invTpCd_A0.getValue())) {
                    invLineDealNetAmt = sMsg.A.no(index).invLineDealNetAmt_A0.getValue().negate();
                    setValue(sMsg.A.no(index).invLineDealNetAmt_A0, invLineDealNetAmt);
                }
            }
            // END 2017/12/04 M.Kidokoro [QC#22598, ADD]

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
            }
            cMsg.A.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum_A0.setValue(1);
            cMsg.xxPageShowToNum_A0.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum_A0.setValue(queryResCnt);

        } else {
            // No result
            cMsg.xxPageShowFromNum_A0.clear();
            cMsg.xxPageShowToNum_A0.clear();
            cMsg.xxPageShowOfNum_A0.clear();
        }
    }

    private void getMtrHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0500Query.getInstance().getMtrHistInfo(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.B.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.B.length();
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.B.getValidCount(); i++) {
                if (i == cMsg.B.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(i), null);
            }
            cMsg.B.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum_B0.setValue(1);
            cMsg.xxPageShowToNum_B0.setValue(cMsg.B.getValidCount());
            cMsg.xxPageShowOfNum_B0.setValue(queryResCnt);

        } else {
            // No result
            cMsg.xxPageShowFromNum_B0.clear();
            cMsg.xxPageShowToNum_B0.clear();
            cMsg.xxPageShowOfNum_B0.clear();
        }
    }

    private void getDealHistInfo(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL0500Query.getInstance().getDealHistInfo(cMsg, sMsg);
        if (ssmResult.isCodeNormal()) {
            // Result > 200
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.C.length()) {
                cMsg.setMessageInfo(NZZM0001W);
                queryResCnt = sMsg.C.length();
            }

            // Copy one page from SMsg to CMsg
            int i = 0;
            for (; i < sMsg.C.getValidCount(); i++) {
                if (i == cMsg.C.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.C.no(i), null, cMsg.C.no(i), null);
            }
            cMsg.C.setValidCount(i);

            // Set page#
            cMsg.xxPageShowFromNum_C0.setValue(1);
            cMsg.xxPageShowToNum_C0.setValue(cMsg.C.getValidCount());
            cMsg.xxPageShowOfNum_C0.setValue(queryResCnt);

        } else {
            // No result
            cMsg.xxPageShowFromNum_C0.clear();
            cMsg.xxPageShowToNum_C0.clear();
            cMsg.xxPageShowOfNum_C0.clear();
        }
    }

    private void doProcess_NSAL0500Scrn00_Search_VndNm(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        NSAL0500CommonLogic.searchVndNm(cMsg);
        NSAL0500CommonLogic.searchDefTech(cMsg);
    }

    private void doProcess_NSAL0500Scrn00_CreateNew(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        // screen item
        clearItemValues(cMsg.vndCd
                        , cMsg.prntVndNm
                        , cMsg.techTocCd
                        , cMsg.contrTrmnFlg
                        , cMsg.effThruDt
                        , cMsg.svcCmntTxt_71
                        , cMsg.svcCmntTxt_72
                        , cMsg.svcCmntTxt_73
                        , cMsg.svcCmntTxt_74
                        , cMsg.svcCmntTxt_75
                        , cMsg.svcCmntTxt_76
                        , cMsg.svcCmntTxt_77
                        , cMsg.svcCmntTxt_78
                        , cMsg.svcCmntTxt_AD
                        , cMsg.basePrcDealAmt
                        , cMsg.adminPrcDealAmt
                        , cMsg.prepdMaintFlg
                        // Del Start 2018/12/10 K.Fujimoto QC#29079
                        // , cMsg.bwMtrAlwncCnt
                        // , cMsg.colorMtrAlwncCnt
                        // , cMsg.bwMtrPrcAmtRate
                        // , cMsg.colorMtrPrcAmtRate
                        // Del End   2018/12/10 K.Fujimoto QC#29079
                        , cMsg.splyInclFlg
                        , cMsg.bllgCycleCd
                        , cMsg.dlrFleetFlg
                        , cMsg.dlrFleetNum);
        // hidden item
        clearItemValues(cMsg.dsSubContrPk
                        , cMsg.ezUpTime
                        , cMsg.ezUpTimeZone
                        , cMsg.attFileNm);

        if (hasValue(cMsg.effThruDt_BK)) {
            setValue(cMsg.effFromDt, addDays(cMsg.effThruDt_BK.getValue(), 1));
        } else {
            setValue(cMsg.effFromDt, cMsg.contrEffFromDt);
        }
        // Add Start 2018/12/10 K.Fujimoto QC#29079
        for (int i = 0; i < cMsg.E.getValidCount(); i++) {
            clearItemValues(cMsg.E.no(i).mtrAlwncCnt_E0
                            , cMsg.E.no(i).prcMtrRate_E0
            );
        }
        // Add End   2018/12/10 K.Fujimoto QC#29079
    }

    private void doProcess_NSAL0500Scrn00_PagePrev(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            int pagenationFrom = cMsg.xxPageShowFromNum_A0.getValueInt() - cMsg.A.length() - 1;
            NSAL0500CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            int pagenationFrom = cMsg.xxPageShowFromNum_B0.getValueInt() - cMsg.B.length() - 1;
            NSAL0500CommonLogic.pagenation_B(cMsg, sMsg, pagenationFrom);
        } else {
            int pagenationFrom = cMsg.xxPageShowFromNum_C0.getValueInt() - cMsg.C.length() - 1;
            NSAL0500CommonLogic.pagenation_C(cMsg, sMsg, pagenationFrom);
        }
    }

    private void doProcess_NSAL0500Scrn00_PageNext(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {

        if (TABLE_A.equals(cMsg.xxModeInd.getValue())) {
            int pagenationFrom = cMsg.xxPageShowFromNum_A0.getValueInt() + cMsg.A.length() - 1;
            NSAL0500CommonLogic.pagenation_A(cMsg, sMsg, pagenationFrom);
        } else if (TABLE_B.equals(cMsg.xxModeInd.getValue())) {
            int pagenationFrom = cMsg.xxPageShowFromNum_B0.getValueInt() + cMsg.B.length() - 1;
            NSAL0500CommonLogic.pagenation_B(cMsg, sMsg, pagenationFrom);
        } else {
            int pagenationFrom = cMsg.xxPageShowFromNum_C0.getValueInt() + cMsg.C.length() - 1;
            NSAL0500CommonLogic.pagenation_C(cMsg, sMsg, pagenationFrom);
        }
    }

    private void doProcess_NSAL0500Scrn00_CMN_Submit(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        doProcess_NSAL0500_INIT(cMsg, sMsg);
    }

    // START 2017/01/19 K.Ochiai [QC#16331,MOD]
    private void doProcess_NSAL0500Scrn00_CMN_Reset(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        doProcess_NSAL0500_INIT(cMsg, sMsg);
    }
    // END 2017/01/19 K.Ochiai [QC#16331,MOD]

    private void doProcess_NSAL0500Scrn00_NWAL1130(NSAL0500CMsg cMsg, NSAL0500SMsg sMsg) {
        NSAL0500CommonLogic.searchDefTech(cMsg);
    }

    private void setCmntTxt(List<String> svcCmntList, EZDCStringItem... items) {

        for (int i = 0; i < svcCmntList.size(); i++) {
            setValue(items[i], svcCmntList.get(i));
        }
    }

    private void clearItemValues(EZDCItem... items) {
        for (EZDCItem item : items) {
            item.clear();
        }
    }

    private String addDays(String date, int days) {

        if (hasValue(date)) {
            return ZYPDateUtil.addDays(date, days);
        }
        return date;
    }
}

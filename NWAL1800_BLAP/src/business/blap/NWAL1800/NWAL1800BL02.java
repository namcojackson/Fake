/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1800;

import static business.blap.NWAL1800.constant.NWAL1800Constant.COVERAGE_DETAIL_Y;
import static business.blap.NWAL1800.constant.NWAL1800Constant.FEAT_SUPPLY_INCLUSIVE;
import static business.blap.NWAL1800.constant.NWAL1800Constant.NZZM0001W;
import static business.blap.NWAL1800.constant.NWAL1800Constant.ZZZM9005W;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NWAL1800.common.NWAL1800CommonLogic;

import com.canon.cusa.s21.common.NSX.NSXC001001.CovTmplInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetCovTmpl;
import com.canon.cusa.s21.common.NSX.NSXC001001.OutputCovTmplInfo;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NWAL1800BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/01   Fujitsu         M.Suzuki        Create          N/A
 * 2016/09/07   Fujitsu         R.Nakamura      Update          QC#11614
 *</pre>
 */
public class NWAL1800BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NWAL1800CMsg bizMsg = (NWAL1800CMsg) cMsg;
            NWAL1800SMsg glblMsg = (NWAL1800SMsg) sMsg;

            if ("NWAL1800_INIT".equals(screenAplID)) {
                doProcess_NWAL1800_INIT(bizMsg, glblMsg);
            } else if ("NWAL1800Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NWAL1800Scrn00_PageNext(bizMsg, glblMsg);
            } else if ("NWAL1800Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NWAL1800Scrn00_PagePrev(bizMsg, glblMsg);
            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL1800_INIT(NWAL1800CMsg bizMsg, NWAL1800SMsg glblMsg) {
        glblMsg.clear();
        ZYPTableUtil.clear(glblMsg.A);
        NWAL1800Query.getInstance().search(bizMsg, getGlobalCompanyCode(), new SearchContracList(bizMsg, glblMsg, getGlobalCompanyCode()));
    }

    /**
     * SearchContracList
     */
    private static class SearchContracList extends S21SsmBooleanResultSetHandlerSupport {
        /** NWAL1800 Business Component Interface Message class */
        private NWAL1800CMsg bizMsg;

        /** NWAL1800 Business Application Global Area Message class */
        private NWAL1800SMsg glblMsg;

        /** GlobalCompanyCode */
        private String gcCode;

        public SearchContracList(NWAL1800CMsg cMsg, NWAL1800SMsg glblMsg, String gcCode) {
            this.bizMsg = cMsg;
            this.glblMsg = glblMsg;
            this.gcCode = gcCode;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected Boolean doProcessQueryResult(ResultSet result) throws SQLException {
            if (!result.next()) {
                bizMsg.setMessageInfo(ZZZM9005W);
                bizMsg.xxPageShowFromNum.clear();
                bizMsg.xxPageShowToNum.clear();
                bizMsg.xxPageShowOfNum.clear();
                bizMsg.setCommitSMsg(true);
                return Boolean.FALSE;
            } else {

                int recordCount = 0;
                String slsDt = ZYPDateUtil.getSalesDate();
                NSXC001001GetCovTmpl covTmple = new NSXC001001GetCovTmpl();
                do {
                    CovTmplInfo info = new CovTmplInfo();
                    info.setGlblCmpyCd(gcCode);
                    info.setSvcPgmMdseCd((String) result.getString("MDSE_CD"));
                    info.setSlsDt(slsDt);
                    covTmple.getCovTmpl(info);
                    List<OutputCovTmplInfo> outInfoList = info.getOutputCovTmplInfoList();

                    boolean covFlag = false;
                    if (outInfoList != null) {
                        for (OutputCovTmplInfo outInfo : outInfoList) {
                            if (FEAT_SUPPLY_INCLUSIVE.equals(outInfo.getSvcCovFeatCd()) && COVERAGE_DETAIL_Y.equals(outInfo.getSvcCovDtlValTxt())) {
                                covFlag = true;
                                break;
                            }
                        }
                    }

                    if (covFlag) {
                        recordCount++;
                        if (recordCount > glblMsg.A.length()) {
                            bizMsg.setMessageInfo(NZZM0001W);
                            break;
                        }

                        int idx = recordCount - 1;
                        ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).dsContrNum_A, result.getString("CONTR_NUM"));
                        String mdsecd10 = result.getString("MDSE_CD10");
                        if (!ZYPCommonFunc.hasValue(mdsecd10)) {
                            String mdsecd8 = result.getString("MDSE_CD8");
                            if (ZYPCommonFunc.hasValue(mdsecd8)) {
                                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseCd_A, mdsecd8);
                            }
                            // Mod Start 2016/09/07 QC#11614
//                            String mdseNm8 = result.getString("MDSE_NM8");
                            String mdseNm8 = result.getString("MDSE_DESC_SHORT_TXT8");
                            // Mod End 2016/09/07 QC#11614
                            if (ZYPCommonFunc.hasValue(mdseNm8)) {
                                // Mod Start 2016/09/07 QC#11614
//                                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseNm_A, mdseNm8);
                                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseDescShortTxt_A, mdseNm8);
                                // Mod End 2016/09/07 QC#11614
                            }
                        } else {
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseCd_A, mdsecd10);
                            // Mod Start 2016/09/07 QC#11614
//                            String mdseNm10 = result.getString("MDSE_NM10");
                            String mdseNm10 = result.getString("MDSE_DESC_SHORT_TXT10");
                            // Mod End 2016/09/07 QC#11614
                            if (ZYPCommonFunc.hasValue(mdseNm10)) {
                                // Mod Start 2016/09/07 QC#11614
//                                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseNm_A, mdseNm10);
                                ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).mdseDescShortTxt_A, mdseNm10);
                                // Mod End 2016/09/07 QC#11614
                            }
                        }

                        String contrNum = result.getString("CONTR_NUM");
                        String contrSqNum = result.getString("CONTR_SQ_NUM");
                        S21SsmEZDResult ssmScheduleResult = NWAL1800Query.getInstance().getSchedulingNum(contrNum, contrSqNum, glblMsg.A.no(idx).mdseCd_A.getValue());
                        if (!ssmScheduleResult.isCodeNotFound()) {
                            Map<String, Object> resultSchedule = (Map<String, Object>) ssmScheduleResult.getResultObject();
                            ZYPEZDItemValueSetter.setValue(glblMsg.A.no(idx).schdAgmtNum_A, (String) resultSchedule.get("SCHD_AGMT_NUM"));
                        }

                        glblMsg.A.setValidCount(recordCount);
                    }
                } while (result.next());

                if (recordCount == 0) {
                    bizMsg.setMessageInfo(ZZZM9005W);
                    bizMsg.xxPageShowFromNum.clear();
                    bizMsg.xxPageShowToNum.clear();
                    bizMsg.xxPageShowOfNum.clear();
                    bizMsg.setCommitSMsg(true);
                    return Boolean.FALSE;
                }

                bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
                NWAL1800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
            }

            return Boolean.TRUE;
        }
    }

    /**
     * PageNext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1800Scrn00_PageNext(NWAL1800CMsg bizMsg, NWAL1800SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowToNum.getValueInt() + 1);
        NWAL1800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }

    /**
     * PagePrev Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NWAL1800Scrn00_PagePrev(NWAL1800CMsg bizMsg, NWAL1800SMsg glblMsg) {

        // copy data from glblMsg onto bizMsg
        bizMsg.xxPageShowFromNum.setValue(bizMsg.xxPageShowFromNum.getValueInt() - bizMsg.A.length());
        NWAL1800CommonLogic.loadOnePageToCMsg(bizMsg, bizMsg.A, glblMsg.A);
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2560;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import parts.common.EZDFStringItem;
import business.blap.NMAL2560.common.NMAL2560CommonLogic;
import business.blap.NMAL2560.constant.NMAL2560Constant;
import business.file.NMAL2560F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmBooleanResultSetHandlerSupport;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2560Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/13   Fujitsu         T.Ogura         Create          N/A
 *</pre>
 */
public final class NMAL2560Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2560Query MY_INSTANCE = new NMAL2560Query();

    /**
     * Private constructor
     */
    private NMAL2560Query() {
        super();
    }

    /**
     * Get the NMAL2560Query instance.
     * @return NMAL2560Query instance
     */
    public static NMAL2560Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getSearchList
     * @param bizMsg NMAL2560CMsg
     * @param glblMsg NMAL2560SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchList(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizAreaOrgCd", bizMsg.bizAreaOrgCd.getValue());
        params.put("xxChkBox", bizMsg.xxChkBox.getValue());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("rowNum", glblMsg.C.length() + 1);

        return getSsmEZDClient().queryEZDMsgArray("getSearchList", params, glblMsg.C);
    }

    /**
     * getOrgHrchStruDfnList
     * @param glblMsg NMAL2560SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrgHrchStruDfnList(NMAL2560SMsg glblMsg) {
        Set<String> bizAreaOrgCdList = new HashSet<String>();
        for (int i = 0; i < glblMsg.A.getValidCount(); i++) {
            bizAreaOrgCdList.add(glblMsg.A.no(i).bizAreaOrgCd_A.getValue());
        }

        String[] paramBizAreaOrgCdList = new String[bizAreaOrgCdList.size()];
        bizAreaOrgCdList.toArray(paramBizAreaOrgCdList);

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("orgTierCd", ORG_TIER._1);
        params.put("bizAreaOrgCd", paramBizAreaOrgCdList);

        return getSsmEZDClient().queryObjectList("getOrgHrchStruDfnList", params);
    }

    /**
     * createDetailCSV
     * @param bizMsg NMAL2560CMsg
     * @param glblMsg NMAL2560SMsg
     */
    public void createDetailCSV(NMAL2560CMsg bizMsg, NMAL2560SMsg glblMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("bizAreaOrgCd", bizMsg.bizAreaOrgCd.getValue());
        params.put("xxChkBox", bizMsg.xxChkBox.getValue());
        params.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        S21SsmBatchClient.getClient(this.getClass()).queryObject("getSearchList", params, new CsvCreator(bizMsg));
    }

    /**
     * CsvCreator
     */
    private static final class CsvCreator extends S21SsmBooleanResultSetHandlerSupport {

        /** CSV Header */
        private static final String[] CSV_HEADER = {"Business Area", "Structure Name", "Effective From", "Effective To", "Tier1", "Tier2", "Tier3", "Tier4", "Tier5", "Tier6", "Tier7", "Tier8", "Tier9", "Tier10", "Created By", "Created On",
                "Last Updated By", "Last Updated On" };

        /** NMAL2560CMsg */
        private NMAL2560CMsg bizMsg;

        private CsvCreator(NMAL2560CMsg bizMsg) {
            this.bizMsg = bizMsg;
        }

        @Override
        protected Boolean doProcessQueryResult(ResultSet rs) throws SQLException {
            if (!rs.next()) {
                return false;
            }

            createCsvFile(rs);
            return true;
        }

        private void createCsvFile(ResultSet rs) throws SQLException {

            // CSV file path.
            final String csvFileNm = ZYPCSVOutFile.createCSVOutFileNm(NMAL2560Constant.BIZ_ID);
            bizMsg.xxFileData.setTempFilePath(null, csvFileNm, ".csv");

            // CSV file writer.
            final NMAL2560F00FMsg fMsg = new NMAL2560F00FMsg();
            final ZYPCSVOutFile fileWriter = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
            fileWriter.writeHeader(CSV_HEADER);

            String wkBizAreaOrgCd = "";
            String wkEffFromDt = "";
            int csvIdx = 0;
            int loopIdx = 0;

            do {

                if (loopIdx == 0) {
                    writeCsvLine(fMsg, rs);
                    wkBizAreaOrgCd = rs.getString("BIZ_AREA_ORG_CD");
                    wkEffFromDt = rs.getString("EFF_FROM_DT");
                } else {

                    if (!wkBizAreaOrgCd.equals(rs.getString("BIZ_AREA_ORG_CD")) || !wkEffFromDt.equals(rs.getString("EFF_FROM_DT"))) {
                        ++csvIdx;
                        if (csvIdx > NMAL2560Constant.MAX_DOWNLOAD_CNT) {
                            break;
                        }
                        fileWriter.write();
                    }
                    writeCsvLine(fMsg, rs);
                    wkBizAreaOrgCd = rs.getString("BIZ_AREA_ORG_CD");
                    wkEffFromDt = rs.getString("EFF_FROM_DT");
                }
                ++loopIdx;

            } while (rs.next());

            ++csvIdx;
            if (csvIdx > NMAL2560Constant.MAX_DOWNLOAD_CNT) {
                fileWriter.close();
                return;
            }

            fileWriter.write();
            fileWriter.close();
        }

        private void writeCsvLine(NMAL2560F00FMsg fMsg, ResultSet rs) throws SQLException {

            if (ORG_TIER._1.equals(rs.getString("ORG_TIER_CD"))) {

                for (int i = 0; i < bizMsg.bizAreaOrgCd_L.length(); i++) {
                    if (bizMsg.bizAreaOrgCd_L.no(i).getValue().equals(rs.getString("BIZ_AREA_ORG_CD"))) {
                        ZYPEZDItemValueSetter.setValue(fMsg.bizAreaOrgDescTxt_A, bizMsg.bizAreaOrgDescTxt_L.no(i).getValue());
                        break;
                    } else {
                        ZYPEZDItemValueSetter.setValue(fMsg.bizAreaOrgDescTxt_A, "");
                    }
                }
                ZYPEZDItemValueSetter.setValue(fMsg.orgHrchStruDfnNm_A, rs.getString("ORG_HRCH_STRU_DFN_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_F, NMAL2560CommonLogic.formatEzd8ToDisp(rs.getString("EFF_FROM_DT")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_T, NMAL2560CommonLogic.formatEzd8ToDisp(rs.getString("EFF_THRU_DT")));
                setStruDfnDescTxt(fMsg.struDfnDescTxt_1, rs);
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkMaxDescTxt_IN, rs.getString("CREATED_BY"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_IN, NMAL2560CommonLogic.formatDt14ToDisp(rs.getString("EZINTIME")));
                ZYPEZDItemValueSetter.setValue(fMsg.xxChkMaxDescTxt_UP, rs.getString("LAST_UPDATED_BY"));
                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTm_UP, NMAL2560CommonLogic.formatDt14ToDisp(rs.getString("EZUPTIME")));
            } else if (ORG_TIER._2.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_2, rs);
            } else if (ORG_TIER._3.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_3, rs);
            } else if (ORG_TIER._4.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_4, rs);
            } else if (ORG_TIER._5.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_5, rs);
            } else if (ORG_TIER._6.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_6, rs);
            } else if (ORG_TIER._7.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_7, rs);
            } else if (ORG_TIER._8.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_8, rs);
            } else if (ORG_TIER._9.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_9, rs);
            } else if (ORG_TIER._10.equals(rs.getString("ORG_TIER_CD"))) {
                setStruDfnDescTxt(fMsg.struDfnDescTxt_10, rs);
            }
        }

        private void setStruDfnDescTxt(EZDFStringItem struDfnDescTxt, ResultSet rs) throws SQLException {

            for (int i = 0; i < bizMsg.struDfnCd_L.length(); i++) {
                if (bizMsg.struDfnCd_L.no(i).getValue().equals(rs.getString("STRU_DFN_CD"))) {
                    ZYPEZDItemValueSetter.setValue(struDfnDescTxt, bizMsg.struDfnDescTxt_L.no(i).getValue());
                    break;
                } else {
                    ZYPEZDItemValueSetter.setValue(struDfnDescTxt, "");
                }
            }
        }
    }
}

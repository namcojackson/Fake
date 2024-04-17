/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0520;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0520.common.NSAL0520CommonLogic;
import business.blap.NSAL0520.constant.NSAL0520Constant;
import business.file.NSAL0520F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/01   CUSA            SRAA            Create          N/A
 * 2016/09/20   Hitachi         N.Arai          Update          QC#11616
 * 2019/09/13   Hitachi         K.Fujimoto      Update          QC#28240
 * 2023/05/19   Hitachi         R.Takau         Update          QC#61409
 *</pre>
 */
public class NSAL0520BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg ezdCMsg, EZDSMsg ezdSMsg) {
        super.preDoProcess(ezdCMsg, ezdSMsg);
        try {
            NSAL0520CMsg cMsg = (NSAL0520CMsg) ezdCMsg;
            NSAL0520SMsg sMsg = (NSAL0520SMsg) ezdSMsg;
            String screenAplId = cMsg.getScreenAplID();
            if ("NSAL0520_INIT".equals(screenAplId)) {
                doProcess_NSAL0520_INIT(cMsg, sMsg);
            } else if ("NSAL0520Scrn00_PageNext".equals(screenAplId)) {
                doProcess_NSAL0520Scrn00_PageNext(cMsg, sMsg);
            } else if ("NSAL0520Scrn00_PagePrev".equals(screenAplId)) {
                doProcess_NSAL0520Scrn00_PagePrev(cMsg, sMsg);
                // START 2023/05/15 R.Takau [ADD,QC#61409]
            } else if ("NSAL0520Scrn00_Download".equals(screenAplId)) {
                doProcess_NSAL0520Scrn00_Download(cMsg, sMsg);
                // END 2023/05/15 R.Takau [ADD,QC#61409]
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplId);
            }
        } finally {
            super.postDoProcess(ezdCMsg, ezdSMsg);
        }
    }

    @SuppressWarnings("unchecked")
    private void doProcess_NSAL0520_INIT(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.svcMachMstrPk)) {
            cMsg.setMessageInfo(NSAL0520Constant.NSAM0362E, new String[] {"Service Machie Master PK" });
            return;
        }

        ZYPTableUtil.clear(sMsg.A);
        ZYPTableUtil.clear(cMsg.A);

        NSAL0520Query query = NSAL0520Query.getInstance();
        S21SsmEZDResult rslt = query.getConfigHist(getGlobalCompanyCode(), cMsg.svcMachMstrPk.getValue(), sMsg.A.length() + 1);
        if (rslt != null && rslt.isCodeNormal()) {
            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) rslt.getResultObject();
            int rsltCnt = rslt.getQueryResultCount();
            if (rsltCnt > sMsg.A.length()) {
                rsltCnt = sMsg.A.length();
                cMsg.setMessageInfo(NSAL0520Constant.ZZSM4102W, new String[] {String.valueOf(sMsg.A.length()) });
            }
            BigDecimal preSvcConfigMstrPk = BigDecimal.ONE.negate();
            int xxRowNum = 0;
            for (int i = 0; i < rsltCnt; i++) {
                Map<String, Object> rsltMap = rsltList.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcConfigMstrDtlPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_DTL_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcConfigMstrPk_A, (BigDecimal) rsltMap.get("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachMstrPk_A, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).serNum_A, (String) rsltMap.get("SER_NUM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseCd_A, (String) rsltMap.get("MDSE_CD"));
                // START 2016/09/20 N.Arai [QC#11616, MOD]
                // ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseNm_A,
                // (String) rsltMap.get("MDSE_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdseDescShortTxt_A, (String) rsltMap.get("MDSE_DESC_SHORT_TXT"));
                // END 2016/09/20 N.Arai [QC#11616, MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdlId_A, (BigDecimal) rsltMap.get("MDL_ID"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mdlNm_A, (String) rsltMap.get("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).istlDt_A, (String) rsltMap.get("ISTL_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).svcMachRmvDt_A, (String) rsltMap.get("SVC_MACH_RMV_DT"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).billToCustCd_A, (String) rsltMap.get("BILL_TO_CUST_CD"));
                // START 2018/09/13 K.fujimoto [QC#28240, MOD]
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).billToDsAcctNm_A, (String) rsltMap.get("BILL_TO_DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).sellToCustCd_A, (String) rsltMap.get("SELL_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).sellDsAcctNm_A, (String) rsltMap.get("SELL_DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipToCustCd_A, (String) rsltMap.get("SHIP_TO_CUST_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).shipDsAcctNm_A, (String) rsltMap.get("SHIP_DS_ACCT_NM"));
                String addr = NSAL0520CommonLogic.formatAddress((String) rsltMap.get("FIRST_LINE_ADDR"), (String) rsltMap.get("SCD_LINE_ADDR"), (String) rsltMap.get("THIRD_LINE_ADDR"), (String) rsltMap.get("FRTH_LINE_ADDR"),
                        (String) rsltMap.get("CTY_ADDR"), (String) rsltMap.get("ST_CD"), (String) rsltMap.get("POST_CD"));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxGenlFldAreaTxt_A, addr);

                if (!NSAL0520CommonLogic.isEqualNum(preSvcConfigMstrPk, sMsg.A.no(i).svcConfigMstrPk_A.getValue())) {
                    xxRowNum++;
                    preSvcConfigMstrPk = sMsg.A.no(i).svcConfigMstrPk_A.getValue();
                }
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRowNum_A, BigDecimal.valueOf(xxRowNum));
            }
            sMsg.A.setValidCount(rsltCnt);
            NSAL0520CommonLogic.paginateTableA(cMsg, sMsg, 0);
        }
    }

    private void doProcess_NSAL0520Scrn00_PageNext(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg) {
        NSAL0520CommonLogic.paginateTableA(cMsg, sMsg, (cMsg.xxPageShowFromNum.getValueInt() - 1) + cMsg.A.length());
    }

    private void doProcess_NSAL0520Scrn00_PagePrev(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg) {
        NSAL0520CommonLogic.paginateTableA(cMsg, sMsg, (cMsg.xxPageShowFromNum.getValueInt() - 1) - cMsg.A.length());
    }

    // START 2023/05/15 R.Takau [ADD,QC#61409]
    private void doProcess_NSAL0520Scrn00_Download(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg) {
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            NSAL0520Query query = NSAL0520Query.getInstance();
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            // create CSV file
            cMsg.xxFileData_D.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(NSAL0520Constant.BIZ_ID + "_" + getUserProfileService().getContextUserInfo().getUserId()), ".csv");
            Map<String, Object> params = setQueryParam(cMsg, sMsg);
            stmtSelect = ssmLLClient.createPreparedStatement("getConfigHist", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                cMsg.setMessageInfo(NSAL0520Constant.NZZM0000E, null);
                return;
            }
            NSAL0520F00FMsg fMsg = new NSAL0520F00FMsg();

            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData_D.getTempFilePath(), fMsg);

            // write header
            writeCsvFileHeader(csvOutFile, fMsg, cMsg);

            // write data records
            int recordCount = 0;
            do {
                if (recordCount >= NSAL0520Constant.LIMIT_DOWNLOAD) {
                    cMsg.setMessageInfo(NSAL0520Constant.NZZM0001W);
                    break;
                }

                writeCsvRecord(cMsg, sMsg, rs, fMsg, csvOutFile);
                recordCount++;

            } while (rs.next());

            csvOutFile.close();

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    // END 2023/05/15 R.Takau [ADD,QC#61409]

    // START 2023/05/15 R.Takau [ADD,QC#61409]
    private Map<String, Object> setQueryParam(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("svcMachMstrPk", cMsg.svcMachMstrPk.getValue());
        params.put("rowNum", NSAL0520Constant.LIMIT_DOWNLOAD);

        return params;
    }

    // END 2023/05/15 R.Takau [ADD,QC#61409]

    // START 2023/05/18 R.Takau [ADD,QC#61409]
    private void writeCsvFileHeader(ZYPCSVOutFile csvOutFile, NSAL0520F00FMsg fMsg, NSAL0520CMsg cMsg) {
        csvOutFile.writeHeader(createCsvFileHeaderNameArray(cMsg));
    }

    // END 2023/05/18 R.Takau [ADD,QC#61409]

    // START 2023/05/18 R.Takau [ADD,QC#61409]
    private String[] createCsvFileHeaderNameArray(NSAL0520CMsg cMsg) {
        List<String> hdrElementList = new ArrayList<String>();
        hdrElementList.add("Config#");
        hdrElementList.add("IB ID");
        hdrElementList.add("Serial#");
        hdrElementList.add("Item Code");
        hdrElementList.add("Item Name");
        hdrElementList.add("Svc Model");
        hdrElementList.add("Install Date");
        hdrElementList.add("Remove Date");
        hdrElementList.add("IB Owner");
        hdrElementList.add("IB Bill To");
        hdrElementList.add("IB Ship To Acct#");
        hdrElementList.add("IB Ship To");
        hdrElementList.add("IB Ship To Address");

        return hdrElementList.toArray(new String[hdrElementList.size()]);
    }

    // END 2023/05/18 R.Takau [ADD,QC#61409]

    // START 2023/05/18 R.Takau [ADD,QC#61409]
    private void writeCsvRecord(NSAL0520CMsg cMsg, NSAL0520SMsg sMsg, ResultSet rs, NSAL0520F00FMsg fMsg, ZYPCSVOutFile csvOutFile) throws SQLException {

        ZYPEZDItemValueSetter.setValue(fMsg.svcConfigMstrPk_A, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcMachMstrPk_A, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        ZYPEZDItemValueSetter.setValue(fMsg.serNum_A, rs.getString("SER_NUM"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseCd_A, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdseDescShortTxt_A, rs.getString("MDSE_DESC_SHORT_TXT"));
        ZYPEZDItemValueSetter.setValue(fMsg.mdlNm_A, rs.getString("MDL_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.istlDt_A, rs.getString("ISTL_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.svcMachRmvDt_A, rs.getString("SVC_MACH_RMV_DT"));
        ZYPEZDItemValueSetter.setValue(fMsg.billToDsAcctNm_A, rs.getString("BILL_TO_DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.sellDsAcctNm_A, rs.getString("SELL_DS_ACCT_NM"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipToCustCd_A, rs.getString("SHIP_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.shipDsAcctNm_A, rs.getString("SHIP_DS_ACCT_NM"));
        String addr = NSAL0520CommonLogic.formatAddress(rs.getString("FIRST_LINE_ADDR"), rs.getString("SCD_LINE_ADDR"), rs.getString("THIRD_LINE_ADDR"), rs.getString("FRTH_LINE_ADDR"), rs.getString("CTY_ADDR"), rs.getString("ST_CD"), rs
                .getString("POST_CD"));
        ZYPEZDItemValueSetter.setValue(fMsg.xxGenlFldAreaTxt_A, addr);

        csvOutFile.write();
    }
    // END 2023/05/19 R.Takau [ADD,QC#61409]
}

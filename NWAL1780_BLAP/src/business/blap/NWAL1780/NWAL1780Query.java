/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1780;

import static business.blap.NWAL1780.constant.NWAL1780Constant.BIZ_ID;
import static business.blap.NWAL1780.constant.NWAL1780Constant.FETCH_SIZE_MAX;
import static business.blap.NWAL1780.constant.NWAL1780Constant.LIMIT_DOWNLOAD;
import static business.blap.NWAL1780.constant.NWAL1780Constant.QUOTE_BIZ_ID;
import static business.blap.NWAL1780.constant.NWAL1780Constant.ZZZM9001E;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import business.file.NWAL1780F00FMsg;

import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/03   Fujitsu         T.Murai         Create          N/A
 * 2017/03/08   Fujitsu         K.Ishizuka      Update          QC#13856
 * </pre>
 */
public final class NWAL1780Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1780Query MY_INSTANCE = new NWAL1780Query();

    /**
     * Constructor.
     */
    private NWAL1780Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1780Query
     */
    public static NWAL1780Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get DS Order Category List
     * @param bizMsg NWAL1780CMsg
     * @return DS Order Category List
     */
    public S21SsmEZDResult getDsOrdCatgList(NWAL1780CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgDescTxt", bizMsg.dsOrdCatgDescTxt.getValue());
        params.put("bizId", QUOTE_BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdCatgList", params);
    }

    /**
     * Get DS Order Type List
     * @param bizMsg NWAL1780CMsg
     * @return DS Order Type List
     */
    public S21SsmEZDResult getDsOrdTpTxt(NWAL1780CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        params.put("bizId", QUOTE_BIZ_ID);

        return getSsmEZDClient().queryObjectList("getDsOrdTpTxt", params);
    }

    /**
     * <pre>
     * Get Quote Data. 
     * </pre>
     * @param cMsg NWAL1780CMsg
     * @param sMsg NWAL1780SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getQuote(NWAL1780CMsg cMsg, NWAL1780SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("msg", cMsg);
        ssmParam.put("rowNum", sMsg.A.length() + 2);

        return getSsmEZDClient().queryObjectList("getQuote", ssmParam);
    }

    // ADS START S21_NA QC#13856
    /**
     * <pre>
     * Get Quote Data. 
     * </pre>
     * @param cMsg NWAL1780CMsg
     * @param sMsg NWAL1780SMsg
     * @return S21SsmEZDResult
     */
    public void getQuoteForCsv(NWAL1780CMsg cMsg, NWAL1780SMsg sMsg) {

        ResultSet rs = null;
        PreparedStatement ps = null;
        NWAL1780Query query = NWAL1780Query.getInstance();

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("msg", cMsg);
            ssmParam.put("rowNum", LIMIT_DOWNLOAD);

            S21SsmExecutionParameter ssmExecParam = new S21SsmExecutionParameter();
            ssmExecParam.setFetchSize(FETCH_SIZE_MAX);
            ssmExecParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            ssmExecParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            ssmExecParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(query.getClass());

            ps = ssmLLClient.createPreparedStatement("getQuote", ssmParam, ssmExecParam);
            rs = ps.executeQuery();
            
            if (!rs.next()) {
                cMsg.setMessageInfo(ZZZM9001E, null);
                return;
            }

            NWAL1780F00FMsg fMsg = new NWAL1780F00FMsg();
            ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(cMsg.xxFileData.getTempFilePath(), fMsg);
            fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(cMsg));
            fMsg.addExclusionItem("xxChkBox_D1");
            NWAL1780BL02.writeCsvFileForHeaderTab(cMsg, sMsg, rs, fMsg, csvOutFile);
            
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }
    // ADD END S21_NA QC#13856

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }
}

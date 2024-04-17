/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2720;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;



import business.blap.NMAL2720.common.NMAL2720CommonLogic;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;

import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV;
import static business.blap.NMAL2720.constant.NMAL2720Constant.CSV_FILE_NAME;
import static business.blap.NMAL2720.constant.NMAL2720Constant.FETCH_SIZE;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM0009E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NMAM8489E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NZZM0000E;
import static business.blap.NMAL2720.constant.NMAL2720Constant.NZZM0001W;

import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;

/**
 *<pre>
 * NMAL2720BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/11/01   Fujitsu         C.Yokoi         Fujitsu         QC#15492
 *</pre>
 */
public class NMAL2720BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL2720CMsg bizMsg = (NMAL2720CMsg) cMsg;

            if ("NMAL2720_INIT".equals(screenAplID)) {
                doProcess_NMAL2720_INIT(bizMsg);

            } else if ("NMAL2720Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_CMN_Clear(bizMsg);

            } else if ("NMAL2720Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_CMN_Download(bizMsg);

            } else if ("NMAL2720Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_CMN_Submit(bizMsg);

            } else if ("NMAL2720Scrn00_GetPsnNum".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_GetPsnNum(bizMsg);

            } else if ("NMAL2720Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_Search(bizMsg);

            } else if ("NMAL2720Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NMAL2720Scrn00_TBLColumnSort(bizMsg);

            } else {
                return;
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * INIT Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720_INIT(NMAL2720CMsg bizMsg) {

        bizMsg.clear();
        ZYPTableUtil.clear(bizMsg.A);
        ZYPTableUtil.clear(bizMsg.P);

        // Pulldown - Business Area
        S21SsmEZDResult ssmResult = NMAL2720Query.getInstance().getBizArea(bizMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> rsltMapList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (int i = 0; i < rsltMapList.size(); i++) {
                if (bizMsg.bizAreaOrgCd_P.length() <= i) {
                    break;
                }
                ZYPEZDItemValueSetter.setValue(bizMsg.bizAreaOrgCd_P.no(i), (String) rsltMapList.get(i).get("BIZ_AREA_ORG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.bizAreaOrgDescTxt_P.no(i), (String) rsltMapList.get(i).get("BIZ_AREA_ORG_DESC_TXT"));
            }
        }

    }

    /**
     * CMN_Clear Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_CMN_Clear(NMAL2720CMsg bizMsg) {
        doProcess_NMAL2720_INIT(bizMsg);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_CMN_Download(NMAL2720CMsg bizMsg) {

        ResultSet rs = null;
        PreparedStatement stmtSelect = null;

        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL2720Query.getInstance().getClass());
            bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("flgY", ZYPConstant.FLG_ON_Y);
            params.put("flgN", ZYPConstant.FLG_OFF_N);
            params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
            params.put("gnrnTpCdFut", GNRN_TP.FUTURE);

            params.put("orgNm", bizMsg.orgNm_H.getValue());
            params.put("bizAreaOrgCd", bizMsg.bizAreaOrgCd_H.getValue());
            params.put("psnCd", bizMsg.psnCd_H.getValue());
            params.put("psnNum", bizMsg.psnNum_H.getValue());
            params.put("psnNm", bizMsg.xxPsnNm_H.getValue());

            stmtSelect = ssmLLClient.createPreparedStatement("getDownloadRecord", params, execParam);
            rs = stmtSelect.executeQuery();

            NMAL2720CommonLogic.writeCsvFile(bizMsg, rs, getGlobalCompanyCode());

        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
    }

    /**
     * CMN_Submit Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_CMN_Submit(NMAL2720CMsg bizMsg) {
        // 2016/11/01 CSA-QC#15492 Add Start
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // 2016/11/01 CSA-QC#15492 Add End

        // search
        search(bizMsg, true);
    }

    /**
     * GetPsnNum Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_GetPsnNum(NMAL2720CMsg bizMsg) {
        S21SsmEZDResult ssmResult = NMAL2720Query.getInstance().getPsnNum(bizMsg);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt >= 2) {
                bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM8489E, new String[] {"Move Organization To"});
            } else {
                List<Map<String, Object>> resultList = (List<Map<String, Object>>) ssmResult.getResultObject();
                ZYPEZDItemValueSetter.setValue(bizMsg.psnNum_D1, (String) resultList.get(0).get("PSN_NUM"));
            }
        } else {
            // No result
            bizMsg.xxPsnNm_D1.setErrorInfo(1, NMAM0009E, new String[] {"Move Organization To"});
        }
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_Search(NMAL2720CMsg bizMsg) {
        // search
        search(bizMsg, false);
    }

    /**
     * TBLColumnSort Event
     * @param bizMsg Business Msg
     */
    private void doProcess_NMAL2720Scrn00_TBLColumnSort(NMAL2720CMsg bizMsg) {
        String sortTblNm = bizMsg.xxSortTblNm.getValue();
        String sortItemNm = bizMsg.xxSortItemNm.getValue();
        String sortOrdBy  = bizMsg.xxSortOrdByTxt.getValue();

        // Table:A
        if ("A".equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(bizMsg.A, bizMsg.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, bizMsg.A.getValidCount());

        }
    }

    /**
     * search
     * @param bizMsg Business  Message
     */
    private void search(NMAL2720CMsg bizMsg, boolean isSubmit) {

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NMAL2720Query.getInstance().search(bizMsg);

        if (ssmResult.isCodeNotFound()) {

            if (!isSubmit) {
                bizMsg.setMessageInfo(NZZM0000E);
            }

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length() - 1) {
                if (!isSubmit) {
                    bizMsg.setMessageInfo(NZZM0001W);
                }
            }

        }
    }

}

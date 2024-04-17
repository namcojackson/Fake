/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7180;

import static business.blap.NMAL7180.constant.NMAL7180Constant.CSV;
import static business.blap.NMAL7180.constant.NMAL7180Constant.CSV_FILE_NAME;
import static business.blap.NMAL7180.constant.NMAL7180Constant.CSV_HDR;
import static business.blap.NMAL7180.constant.NMAL7180Constant.CSV_LIMIT_COUNT;
import static business.blap.NMAL7180.constant.NMAL7180Constant.FETCH_SIZE;
import static business.blap.NMAL7180.constant.NMAL7180Constant.HIGH_VAL_DT;
import static business.blap.NMAL7180.constant.NMAL7180Constant.NZZM0000E;
import static business.blap.NMAL7180.constant.NMAL7180Constant.NZZM0001W;
import static business.blap.NMAL7180.constant.NMAL7180Constant.YYYYMMDD_LENGTH;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NMAL7180.common.NMAL7180CommonLogic;
import business.db.PRC_GRP_TRGT_ATTRBTMsg;
import business.db.PRC_GRP_TRGT_ATTRBTMsgArray;
import business.file.NMAL7180F00FMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPGUITableColumn;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * NMAL7180BL02
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/05   Fujitsu         W.Honda         Create          N/A
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2018/02/21   Fujitsu         M.Ohno          Update          QC#22575
 * 2020/02/19   Fujitsu         C.Hara          Update          QC#55203
 *</pre>
 */
public class NMAL7180BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);
        try {
            String screenAplID = cMsg.getScreenAplID();
            NMAL7180CMsg bizMsg = (NMAL7180CMsg) cMsg;
            NMAL7180SMsg glblMsg = (NMAL7180SMsg) sMsg;

            if ("NMAL7180_INIT".equals(screenAplID)) {
                doProcess_NMAL7180_INIT(bizMsg, glblMsg);
                ZYPGUITableColumn.getColData(bizMsg, glblMsg);

            } else if ("NMAL7180Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NMAL7180Scrn00_CMN_Download(bizMsg, glblMsg);

            } else if ("NMAL7180Scrn00_CMN_Clear".equals(screenAplID)) {
                doProcess_NMAL7180Scrn00_CMN_Clear(bizMsg, glblMsg);

            } else if ("NMAL7180Scrn00_OnChange_TrgtContext".equals(screenAplID)) {
                doProcess_NMAL7180Scrn00_OnChange_TrgtContext(bizMsg, glblMsg);

            } else if ("NMAL7180Scrn00_Search".equals(screenAplID)) {
                doProcess_NMAL7180Scrn00_Search(bizMsg, glblMsg);

            } else if ("NMAL7180_NMAL7190".equals(screenAplID)) {
                doProcess_NMAL7180_NMAL7190(bizMsg, glblMsg);

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
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180_INIT(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TP.class, bizMsg.prcGrpTpCd_P, bizMsg.prcGrpTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRGT_TP.class, bizMsg.prcGrpTrgtTpCd_P, bizMsg.prcGrpTrgtTpDescTxt_P);
        ZYPCodeDataUtil.createPulldownList(PRC_GRP_TRX_TP.class, bizMsg.prcGrpTrxTpCd_P, bizMsg.prcGrpTrxTpDescTxt_P); // QC#18785

        // Initial Set.
        ZYPEZDItemValueSetter.setValue(bizMsg.actvFlg, ZYPConstant.FLG_ON_Y);
    }

    /**
     * CMN_Download Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180Scrn00_CMN_Download(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {

        bizMsg.xxFileData.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm(CSV_FILE_NAME), CSV);
        NMAL7180F00FMsg fMsg = new NMAL7180F00FMsg();
        ZYPCSVOutFile csvOutFile = new ZYPCSVOutFile(bizMsg.xxFileData.getTempFilePath(), fMsg);
        fMsg.setItemOrder(ZYPGUITableColumn.getColOrder(bizMsg));

        fMsg.addExclusionItem("xxRadioBtn");

        //write header
        csvOutFile.writeHeader(CSV_HDR, fMsg, ZYPGUITableColumn.getColOrder(bizMsg));
        // 2018/02/21 QC#22575 add start
        ResultSet rs = null;
        PreparedStatement stmtSelect = null;
        try {
            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
            execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
            execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

            S21SsmLowLevelCodingClient ssmLLClient = S21SsmLowLevelCodingClient.getClient(NMAL7180Query.getInstance().getClass());
            HashMap<String, Object> params = new HashMap<String, Object>();
            params.put("glblCmpyCd", getGlobalCompanyCode());
            params.put("rowNum", CSV_LIMIT_COUNT + 1);
            params.put("highValDt", HIGH_VAL_DT);
            params.put("prcGrpNm", bizMsg.prcGrpNm.getValue());
            params.put("prcGrpDescTxt", bizMsg.prcGrpDescTxt.getValue());
            params.put("prcGrpTpCd", bizMsg.prcGrpTpCd.getValue());
            params.put("prcGrpTrgtTpCd", bizMsg.prcGrpTrgtTpCd.getValue());
            params.put("prcGrpTrgtAttrbCd", bizMsg.prcGrpTrgtAttrbCd.getValue());
            params.put("prcGrpFromTxt", bizMsg.prcGrpFromTxt.getValue());
            params.put("prcGrpThruTxt", bizMsg.prcGrpThruTxt);
            params.put("actvFlg", bizMsg.actvFlg.getValue());
            params.put("effFromDt", bizMsg.effFromDt.getValue());
            params.put("effThruDt", bizMsg.effThruDt.getValue());
            params.put("prcGrpTrxTpCd", bizMsg.prcGrpTrxTpCd.getValue());
            stmtSelect = ssmLLClient.createPreparedStatement("getPriceGroup", params, execParam);
            rs = stmtSelect.executeQuery();
            if (!rs.next()) {
                bizMsg.setMessageInfo(NZZM0000E);
                return;
            }

            do {
                if (rs.getRow() >= CSV_LIMIT_COUNT) {
                    bizMsg.setMessageInfo(NZZM0001W);
                    break;
                }

                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpNm_A1, rs.getString("PRC_GRP_NM"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpDescTxt_A1, rs.getString("PRC_GRP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTpDescTxt_A1, rs.getString("PRC_GRP_TP_DESC_TXT"));
                ZYPEZDItemValueSetter.setValue(fMsg.actvFlg_A1, rs.getString("ACTV_FLG"));
                // 2020/02/19 QC#55203 Del Start
                //ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTrgtTpDescTxt_A1, rs.getString("PRC_GRP_TRGT_TP_DESC_TXT"));
                //ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTrgtAttrbDescTxt_A1, rs.getString("PRC_GRP_TRGT_ATTRB_DESC_TXT"));
                //ZYPEZDItemValueSetter.setValue(fMsg.prcGrpOpDescTxt_A1, rs.getString("PRC_GRP_OP_DESC_TXT"));
                //ZYPEZDItemValueSetter.setValue(fMsg.prcGrpFromTxt_A1, rs.getString("PRC_GRP_FROM_TXT"));
                //ZYPEZDItemValueSetter.setValue(fMsg.prcGrpThruTxt_A1, rs.getString("PRC_GRP_THRU_TXT"));
                // 2020/02/19 QC#55203 Del End
                fMsg.xxDtTxt_FR.clear();
                if (ZYPCommonFunc.hasValue(rs.getString("EFF_FROM_DT"))) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, formatDt(rs.getString("EFF_FROM_DT")));
                }
                fMsg.xxDtTxt_TH.clear();
                if (ZYPCommonFunc.hasValue(rs.getString("EFF_THRU_DT"))) {
                    ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, formatDt(rs.getString("EFF_THRU_DT")));
                }
                ZYPEZDItemValueSetter.setValue(fMsg.prcGrpTrxTpDescTxt_A1, rs.getString("PRC_GRP_TRX_TP_DESC_TXT"));
                // ZYPEZDItemValueSetter.setValue(fMsg.prcGrpPrcdNum_A1, rs.getBigDecimal("PRC_GRP_PRCD_NUM")); // 2020/02/19 QC#55203 Del

                csvOutFile.write();
            } while (rs.next());

            csvOutFile.close();
        } catch (SQLException e) {
            throw new S21AbendException(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmtSelect, rs);
        }
        // 2018/02/21 QC#22575 add end
        // 2018/02/21 QC#22575 del start
//        //write contents
//        for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
//            //sMsg -> fMsg
//            EZDMsg.copy(bizMsg.A.no(i), null, fMsg, null);
//            fMsg.xxDtTxt_FR.clear();
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).effFromDt_A1)) {
//                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_FR, formatDt(bizMsg.A.no(i).effFromDt_A1.getValue()));
//            }
//            fMsg.xxDtTxt_TH.clear();
//            if (ZYPCommonFunc.hasValue(bizMsg.A.no(i).effThruDt_A1)) {
//                ZYPEZDItemValueSetter.setValue(fMsg.xxDtTxt_TH, formatDt(bizMsg.A.no(i).effThruDt_A1.getValue()));
//            }
//            csvOutFile.write();
//        }
//
//        csvOutFile.close();
        // 2018/02/21 QC#22575 del end
    }

    /**
     * CMN_Reset Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180Scrn00_CMN_Clear(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
        doProcess_NMAL7180_INIT(bizMsg, glblMsg);
    }

    /**
     * OnChange_TrgtContext Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180Scrn00_OnChange_TrgtContext(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
        bizMsg.prcGrpTrgtAttrbDescTxt_P.clear();
        bizMsg.prcGrpTrgtAttrbCd_P.clear();
        bizMsg.prcGrpTrgtAttrbCd.clear();

        if (ZYPCommonFunc.hasValue(bizMsg.prcGrpTrgtTpCd)) {
            searchTrgtContext(bizMsg);
        }
    }

    /**
     * Search Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180Scrn00_Search(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * NMAL7190 Event
     * @param bizMsg Business Msg
     * @param glblMsg Global Msg
     */
    private void doProcess_NMAL7180_NMAL7190(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {
        // search
        search(bizMsg, glblMsg);
    }

    /**
     * search
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void search(NMAL7180CMsg bizMsg, NMAL7180SMsg glblMsg) {

        ZYPTableUtil.clear(bizMsg.A);

        S21SsmEZDResult ssmResult = NMAL7180Query.getInstance().search(bizMsg, glblMsg);

        if (ssmResult.isCodeNotFound()) {

            bizMsg.setMessageInfo(NZZM0000E);

        } else {
            if (ssmResult.getQueryResultCount() > bizMsg.A.length()) {
                bizMsg.setMessageInfo(NZZM0001W);
                bizMsg.A.setValidCount(bizMsg.A.length());
            } else {
                bizMsg.A.setValidCount(ssmResult.getQueryResultCount());
            }

            NMAL7180CommonLogic.setPrcGrp(ssmResult, bizMsg);
        }
    }

    /**
     * searchTrgtContext
     * @param bizMsg Business Component Interface Message
     * @param glblMsg Global area information
     */
    private void searchTrgtContext(NMAL7180CMsg bizMsg) {
        PRC_GRP_TRGT_ATTRBTMsg prcGrpTrgtAttrb = new PRC_GRP_TRGT_ATTRBTMsg();
        PRC_GRP_TRGT_ATTRBTMsgArray resultList = new PRC_GRP_TRGT_ATTRBTMsgArray();
        ZYPEZDItemValueSetter.setValue(prcGrpTrgtAttrb.prcGrpTrgtTpCd, bizMsg.prcGrpTrgtTpCd.getValue());
        resultList = (PRC_GRP_TRGT_ATTRBTMsgArray) S21CodeTableAccessor.findByCondition(prcGrpTrgtAttrb);

        if (resultList.length() > 0) {
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, "prcGrpTrgtAttrbCd");
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, "prcGrpTrgtAttrbDescTxt");
            ZYPPulldownValueSetter.set(resultList, tMsgKeys, bizMsg.prcGrpTrgtAttrbCd_P, bizMsg.prcGrpTrgtAttrbDescTxt_P);
        }
    }

    /**
     * readCsvHeader_UPLOAD
     * @param dt String
     * @return String formated
     */
    private static String formatDt(String dt) {

        if (!ZYPCommonFunc.hasValue(dt)) {
            return "";
        } else if (dt.length() > YYYYMMDD_LENGTH) {
            dt = dt.substring(0, YYYYMMDD_LENGTH);
        }

        return ZYPDateUtil.formatEzd8ToDisp(dt, true);
    }
}

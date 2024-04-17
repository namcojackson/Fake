/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB245001;

import static com.canon.cusa.s21.batch.NWA.NWAB245001.constant.NWAB245001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsgArray;
import parts.common.EZDPMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CSA_ORD_HDR_VIEW_SNAPTMsg;
import business.db.CSA_ORD_ITEM_VIEW_SNAPTMsg;
import business.db.CSA_ORD_MSG_VIEW_SNAPTMsg;
import business.db.CSA_ORD_SER_VIEW_SNAPTMsg;
import business.db.CSA_ORD_USG_VIEW_SNAPTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DELY_INFOTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.DS_IMPT_ORD_ERRTMsg;
import business.db.DS_IMPT_ORD_SLS_CRTMsg;
import business.db.DS_IMPT_PRC_CALC_BASETMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsgArray;
import business.db.MDSETMsg;
import business.db.TOCTMsg;
import business.parts.NMZC260001PMsg;
import business.parts.NMZC260001_defSlsRepListPMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_EligibleCheckListPMsg;
import business.parts.NWZC157001PMsg;
import business.parts.NWZC157001_xxMsgIdListPMsg;
import business.parts.NWZC157001_xxPrcListPMsgArray;
import business.parts.NWZC157002PMsg;
import business.parts.NWZC157002_xxMsgIdListPMsg;
import business.parts.NWZC157003PMsg;
import business.parts.NWZC157004PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC260001.NMZC260001;
import com.canon.cusa.s21.api.NWZ.NWZC157001.NWZC157001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Exchange;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmount;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeAmoutData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangeData;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001ExchangePriceData;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.NWXC150001DsCheck;
import com.canon.cusa.s21.common.NWX.NWXC245001.NWXC245001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_PROJ;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_SRC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHIP_TO_ADDR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.USR_DLR_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21ResultSetMapper;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * CUSA Retail Order Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CITS            S.Tanikawa      Create          N/A
 * 07/14/2016   Fujitsu         T.Ishii         Update          QC#11561
 * 08/30/2016   SRAA            K.Aratani       Update          QC#14021
 * 09/26/2016   CITS            S.Tanikawa      Update          QC#11113
 * 11/29/2016   Fujitsu         S.Iidaka        Update          S21_NA#16266
 * 12/19/2016   Fujitsu         M.Ohno          Update          S21_NA#16460
 * 01/31/2017   Fujitsu         M.Ohno          Update          S21_NA#17314,S21_NA#17297
 * 02/01/2017   Fujitsu         M.Ohno          Update          S21_NA#17398
 * 02/07/2017   Fujitsu         N.Aoyama        Update          QC#16575
 * 02/07/2017   Fujitsu         M.Ohno          Update          S21_NA#17302
 * 02/15/2017   Fujitsu         N.Aoyama        Update          QC#16575
 * 02/28/2017   Fujitsu         K.Ishizuka      Update          QC#12380
 * 03/14/2017   Fujitsu         M.Ohno          Update          S21_NA#16855
 * 04/12/2017   Fujitsu         Y.Kanefusa      Update          S21_NA#18235
 * 06/09/2017   Fujitsu         N.Aoyama        Update          S21_NA#18296
 * 11/24/2017   Fujitsu         T.Murai         Update          S21_NA#22624
 * 12/14/2017   Fujitsu         Hd.Sugawara     Update          QC#19804(Sol#349)
 * 01/04/2018   Fujitsu         Hd.Sugawara     Update          QC#22371
 * 02/26/2018   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 09/18/2018   Fujitsu         T.Noguchi       Update          QC#9700
 * 12/14/2018   Fujitsu         K.Kato          Update          QC#29315
 * 01/08/2019   Fujitsu         S.Kosaka        Update          QC#29753
 * 01/08/2019   Fujitsu         K.Kato          Update          QC#29241
 * </pre>
 */
public class NWAB245001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** SSM Low Level Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String salesDate = null;

    // -- Count of processing -------------------
    /** Total count of CPO_ORD_HDR_NUM */
    private int totalOrdHdrCnt = 0;

    /** Total error count */
    private int totalErrorCnt = 0;

    /**
     * A main method for batch application start.
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB245001().executeBatch(NWAB245001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
            this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

            // Global Company Code
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
            }

            // Sales Date
            this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");

        // get EDI Trading Partner Code
        ArrayList<String> listBillToCust = getTradPtnr();

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            // *****************************************************************
            // Get CSA_ORD_HDR_V
            // *****************************************************************
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
            ssmParam.put(COL_SALES_DATE, salesDate); // ADD S21_NA QC#12380

            stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdHdrV", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                String cpoOrdNum = rsSet.getString(COL_CPO_ORD_NUM);
                // *****************************************************************
                // Set CSA_ORD_ITEM_VIEW_SNAP from CSA_ORD_ITEM_V
                // *****************************************************************
                List<CSA_ORD_ITEM_VIEW_SNAPTMsg> listOrdItemTMsg = createCsaOrdItemViewSnap(cpoOrdNum, execParam, listBillToCust);

                if (listOrdItemTMsg.size() > 0) {
                    // *****************************************************************
                    // Set CSA_ORD_HDR_VIEW_SNAP
                    // *****************************************************************
                    CSA_ORD_HDR_VIEW_SNAPTMsg ordHdrTMsg = new CSA_ORD_HDR_VIEW_SNAPTMsg();
                    S21ResultSetMapper.map(rsSet, ordHdrTMsg);

                    // set primary key
                    ZYPEZDItemValueSetter.setValue(ordHdrTMsg.csaOrdHdrViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_ORD_HDR_VIEW_SNAP_SQ));

                    S21FastTBLAccessor.insert(ordHdrTMsg);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordHdrTMsg.getReturnCode())) {
                        writeErrLog(NMAM0176E, new String[] {String.format(MSG_CPO_ORD_NUM, cpoOrdNum) });
                        this.totalErrorCnt++;
                        throw new S21AbendException(NWAM0728E, new String[] {"CSA_ORD_HDR_VIEW_SNAP" });
                    }

                    // *****************************************************************
                    // Set CSA_ORD_MSG_VIEW_SNAP from CSA_ORD_MSG_V
                    // *****************************************************************
                    createCsaOrdMsgViewSnap(cpoOrdNum, execParam);

                    // *****************************************************************
                    // Set CSA_ORD_SER_VIEW_SNAP from CSA_ORD_SER_V
                    // *****************************************************************
                    createCsaOrdSerViewSnap(cpoOrdNum, listOrdItemTMsg, execParam);

                    // *****************************************************************
                    // Set CSA_ORD_USG_VIEW_SNAP from CSA_ORD_USG_V
                    // *****************************************************************
                    createCsaOrdUsgViewSnap(cpoOrdNum, listOrdItemTMsg, execParam);

                    // *****************************************************************
                    // Create DS_IMPT_ORD Tables
                    // *****************************************************************
                    createDsImptOrdTables(ordHdrTMsg, execParam);

                    commit();
                    totalOrdHdrCnt++;
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");
        try {
            TERM_CD termCd = TERM_CD.NORMAL_END;

            if (totalErrorCnt > 0) {
                termCd = TERM_CD.WARNING_END;
            }
            setTermState(termCd, totalOrdHdrCnt, totalErrorCnt, totalOrdHdrCnt + totalErrorCnt);
        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    /**
     * createDsImport
     * @param ordHdrTMsg
     * @param listOrdItemTMsg
     * @param listOrdSerTMsg
     */
    private void createDsImptOrdTables(CSA_ORD_HDR_VIEW_SNAPTMsg ordHdrTMsg, S21SsmExecutionParameter execParam) {
        writeStartLogLn("createDsImptOrdTables");
        // (2) ORD_CATG_BIZ_CTX
        Map<String, Object> mapOrdCatgBizCtx = NWXC245001.getOrdCatgBizCtx(this.glblCmpyCd, ordHdrTMsg.rtlDivCd.getValue());
        // (4) DS_RTL_INTG_ITEM
        Map<String, Object> mapDsRtlIntgItemMap = NWXC245001.getDsRtlIntgItemMap(this.glblCmpyCd, ordHdrTMsg.rtlDivCd.getValue(), this.salesDate);
        // get INSTL_CD & ISTL_SUB_LOC_CD
        List<Map<String, Object>> mapInstlCdList = getInstlCdList(ordHdrTMsg.cpoOrdNum.getValue());

        // Loop by the combination of( CPO_ORD_NUM, INSTL_CD, ISTL_SUB_LOC_CD)
        for (int i = 0; i < mapInstlCdList.size(); i++) {
            // initialize Params
            DsImptOrdErrBean errBean = new DsImptOrdErrBean();
            int cntConfig = 0;

            // initialize Inserting TMsgs
            DS_IMPT_ORDTMsg dsImptOrdTMsg = new DS_IMPT_ORDTMsg();
            DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrTMsg = new DS_IMPT_ORD_SLS_CRTMsg();
            DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoTMsg = new DS_IMPT_ORD_DELY_INFOTMsg();
            List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg = new ArrayList<DS_IMPT_ORD_CONFIGTMsg>();
            List<DsImptOrdDtlBean> listDsImptOrdDtlBean = new ArrayList<DsImptOrdDtlBean>();

            PreparedStatement stmt = null;
            ResultSet rsSetOrdItem = null;

            try {
                Map<String, Object> ssmParam = new HashMap<String, Object>();
                ssmParam.put(COL_GLBL_CMPY_CD_CUSA, VAL_GLBL_CMPY_CD_CUSA);
                ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CSA);                 // UPDATE 2016/09/26 QC#11113
                ssmParam.put(COL_CPO_ORD_NUM, ordHdrTMsg.cpoOrdNum.getValue());
                ssmParam.put(COL_INSTL_CD, (String) mapInstlCdList.get(i).get(COL_INSTL_CD));
                ssmParam.put(COL_ISTL_SUB_LOC_CD, (String) mapInstlCdList.get(i).get(COL_ISTL_SUB_LOC_CD));
                ssmParam.put(COL_SALES_DATE, this.salesDate); // 2017/02/01 S21_NA#17398 Add
                ArrayList<String> listBillTpCd = new ArrayList<String>();
                listBillTpCd.add(BIZ_TP_CP);
                listBillTpCd.add(BIZ_TP_DP);
                listBillTpCd.add(BIZ_TP_FL);
                listBillTpCd.add(BIZ_TP_LT);
                listBillTpCd.add(BIZ_TP_P);
                listBillTpCd.add(BIZ_TP_R);
                listBillTpCd.add(BIZ_TP_TP);
                listBillTpCd.add(BIZ_TP_TR);
                ssmParam.put(COL_BIZ_TP, listBillTpCd);

                stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdItemVSnap", ssmParam, execParam);
                rsSetOrdItem = stmt.executeQuery();

                // this loop continues per DS_IMPT_ORD Table and its related Detail Tables
                while (rsSetOrdItem.next()) {
                    // (1) DS_RTL_DLR_INFO
                    Map<String, Object> mapDsRtlDlrInfo = NWXC245001.getDsRtlDlrInfo(this.glblCmpyCd, rsSetOrdItem.getString(COL_SVC_DLR_CD), ordHdrTMsg.rtlDivCd.getValue(), this.salesDate);

                    // when first ResultSet Only,
                    if (rsSetOrdItem.isFirst()) {
                        // *****************************************************************
                        // Create DS_IMPT_ORD
                        // *****************************************************************
                        dsImptOrdTMsg = createDsImptOrd(ordHdrTMsg, rsSetOrdItem, mapDsRtlDlrInfo, mapOrdCatgBizCtx, errBean);
                        // *****************************************************************
                        // Create DS_IMPT_ORD_SLS_CR
                        // *****************************************************************
                        dsImptOrdSlsCrTMsg = createDsImptOrdSlsCr(dsImptOrdTMsg);
                        // *****************************************************************
                        // Create DS_IMPT_ORD_DELY_INFO
                        // *****************************************************************
                        dsImptOrdDelyInfoTMsg = createDsImptOrdDelyInfo(dsImptOrdTMsg, rsSetOrdItem);

                        // when first item is not MainMachine, create new DS_IMPT_ORD_CONFIG
                        // UPDATE 2016/09/26 QC#11113
                        // if (!MDL_TP.MACHINE_MODEL.equals(rsSetOrdItem.getString(COL_MACH_MDL_TP_CD))) {
                        if (!VAL_PRNT_MDSE.equals(rsSetOrdItem.getString(COL_IS_PRNT_MDSE))) {
                            // *****************************************************************
                            // Create DS_IMPT_ORD_CONFIG without MainMachine
                            // *****************************************************************
                            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = createDsImptOrdConfig(dsImptOrdTMsg, rsSetOrdItem, cntConfig, false);
                            listDsImptOrdConfigTMsg.add(dsImptOrdConfigTMsg);

                            // Config count up
                            cntConfig++;

                            // set DS_IMPT_ORD.
                            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.imptStsCd, IMPT_STS.ERROR);
                        }
                    }

                    // get Serial Number map
                    List<Map<String, Object>> serialMap = new ArrayList<Map<String, Object>>();
                    if (isContrBizTp(rsSetOrdItem.getString(COL_CONTR_BIZ_TP_CD))) {
                        if (isSerTake(rsSetOrdItem.getString(COL_MDSE_CD))) {
                            serialMap = getSerNum(rsSetOrdItem.getString(COL_RCPO_DTL_SQ));

                            if (rsSetOrdItem.getInt(COL_ORD_QTY) > (serialMap.size())) {
                                // add Serial# for ORD_QTY
                                for (int j = 0; j < rsSetOrdItem.getInt(COL_ORD_QTY); j++) {
                                    if (j >= serialMap.size()) {
                                        Map<String, Object> serial = new HashMap<String, Object>();
                                        serial.put(COL_SER_NUM, "");
                                        serialMap.add(serial);
                                    }
                                }
                            }
                        }
                    }

                    // when item is MainMachine(IS_PRNT_MDSE = 1), create new DS_IMPT_ORD_CONFIG
                    // UPDATE 2016/09/26 QC#11113
                    // if (MDL_TP.MACHINE_MODEL.equals(rsSetOrdItem.getString(COL_MACH_MDL_TP_CD))) {
                    if (VAL_PRNT_MDSE.equals(rsSetOrdItem.getString(COL_IS_PRNT_MDSE))) {

                        for (int j = 0; j < rsSetOrdItem.getInt(COL_ORD_QTY); j++) {
                            // *****************************************************************
                            // Create DS_IMPT_ORD_CONFIG with MainMachine
                            // *****************************************************************
                            DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = createDsImptOrdConfig(dsImptOrdTMsg, rsSetOrdItem, cntConfig, true);
                            listDsImptOrdConfigTMsg.add(dsImptOrdConfigTMsg);

                            // Config count up
                            cntConfig++;

                            // *****************************************************************
                            // Create DS_IMPT_ORD_DTL of MainMachien
                            // *****************************************************************
                            if (isContrBizTp(rsSetOrdItem.getString(COL_CONTR_BIZ_TP_CD))) {
                                if (isSerTake(rsSetOrdItem.getString(COL_MDSE_CD))) {
                                    DsImptOrdDtlBean dsImptOrdDtl = createDsImptOrdDtl(//
                                            MODE_NRML_DTL //
                                            , dsImptOrdTMsg //
                                            , dsImptOrdConfigTMsg //
                                            , rsSetOrdItem //
                                            , mapDsRtlDlrInfo //
                                            , mapDsRtlIntgItemMap //
                                            , 1 //
                                            , (String) serialMap.get(j).get(COL_SER_NUM) //
                                            , j + 1);
                                    listDsImptOrdDtlBean.add(dsImptOrdDtl);

                                } else {
                                    DsImptOrdDtlBean dsImptOrdDtl = createDsImptOrdDtl(//
                                            MODE_NRML_DTL //
                                            , dsImptOrdTMsg //
                                            , dsImptOrdConfigTMsg //
                                            , rsSetOrdItem //
                                            , mapDsRtlDlrInfo //
                                            , mapDsRtlIntgItemMap //
                                            , 1 //
                                            , null //
                                            , j + 1);
                                    listDsImptOrdDtlBean.add(dsImptOrdDtl);
                                }
                            }
                        }
                        // when item is not MainMachine
                    } else {
                        // *****************************************************************
                        // Create DS_IMPT_ORD_DTL
                        // *****************************************************************
                        if (isContrBizTp(rsSetOrdItem.getString(COL_CONTR_BIZ_TP_CD))) {
                            // when item takes serial numbers,
                            if (isSerTake(rsSetOrdItem.getString(COL_MDSE_CD)) || rsSetOrdItem.getInt(COL_ORD_QTY) < cntConfig) {

                                // set relation of config and detail
                                int[] listConfig = setRelation(rsSetOrdItem.getInt(COL_ORD_QTY), cntConfig);

                                // loop through Item Qty
                                for (int j = 0; j < rsSetOrdItem.getInt(COL_ORD_QTY); j++) {

                                    String serNum = null;
                                    if (isSerTake(rsSetOrdItem.getString(COL_MDSE_CD))) {
                                        serNum = (String) serialMap.get(j).get(COL_SER_NUM);
                                    }

                                    DsImptOrdDtlBean dsImptOrdDtl = createDsImptOrdDtl(//
                                            MODE_NRML_DTL //
                                            , dsImptOrdTMsg //
                                            , listDsImptOrdConfigTMsg.get(listConfig[j]) //
                                            , rsSetOrdItem //
                                            , mapDsRtlDlrInfo //
                                            , mapDsRtlIntgItemMap //
                                            , 1 //
                                            , serNum //
                                            , j + 1);
                                    listDsImptOrdDtlBean.add(dsImptOrdDtl);
                                }
                            } else {
                                // loop through the number of MainMachine
                                for (int k = 0; k < cntConfig; k++) {
                                    int qty = rsSetOrdItem.getInt(COL_ORD_QTY) / cntConfig;
                                    if (k == 0) {
                                        qty = qty + rsSetOrdItem.getInt(COL_ORD_QTY) % cntConfig;
                                    }
                                    DsImptOrdDtlBean dsImptOrdDtl = createDsImptOrdDtl(//
                                            MODE_NRML_DTL //
                                            , dsImptOrdTMsg //
                                            , listDsImptOrdConfigTMsg.get(k) //
                                            , rsSetOrdItem //
                                            , mapDsRtlDlrInfo //
                                            , mapDsRtlIntgItemMap //
                                            , qty //
                                            , null //
                                            , k + 1);
                                    listDsImptOrdDtlBean.add(dsImptOrdDtl);
                                }
                            }
                        }
                    }
                }
                // *****************************************************************
                // Create DS_IMPT_ORD_DTL FINDING FEE
                // *****************************************************************
                DsImptOrdDtlBean dsImptOrdDtlFndFee = createDsImptOrdDtl(//
                        MODE_FNDG_FEE //
                        , dsImptOrdTMsg //
                        , listDsImptOrdConfigTMsg.get(listDsImptOrdConfigTMsg.size() - 1) //
                        , rsSetOrdItem //
                        , null //
                        , mapDsRtlIntgItemMap //
                        , 1 //
                        , null //
                        , 0);
                listDsImptOrdDtlBean.add(dsImptOrdDtlFndFee);
                // *****************************************************************
                // Create DS_IMPT_ORD_DTL INSTALL FEE
                // *****************************************************************
                DsImptOrdDtlBean dsImptOrdDtlIstFee = createDsImptOrdDtl(//
                        MODE_INST_FEE //
                        , dsImptOrdTMsg //
                        , listDsImptOrdConfigTMsg.get(listDsImptOrdConfigTMsg.size() - 1) //
                        , rsSetOrdItem //
                        , null //
                        , mapDsRtlIntgItemMap //
                        , 1 //
                        , null //
                        , 0);
                listDsImptOrdDtlBean.add(dsImptOrdDtlIstFee);

                // sum up fndgDlrCompAmt to negoDealAmt
                Map<String, BigDecimal> mapFndgIstlCompAmtDlrInfo = getFndgDlrIstlCompAmt(ordHdrTMsg.cpoOrdNum.getValue() //
                        , (String) mapInstlCdList.get(i).get(COL_INSTL_CD) //
                        , (String) mapInstlCdList.get(i).get(COL_ISTL_SUB_LOC_CD));
                BigDecimal negoDealAmt = mapFndgIstlCompAmtDlrInfo.get(COL_FNDG_DLR_COMP_AMT);

                BigDecimal rate = BigDecimal.ONE;
                if (ZYPCommonFunc.hasValue((BigDecimal) mapDsRtlIntgItemMap.get(COL_FNDG_FEE_MULT_RATE))) {
                    rate = (BigDecimal) mapDsRtlIntgItemMap.get(COL_FNDG_FEE_MULT_RATE);
                }
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.negoDealAmt, negoDealAmt.multiply(rate));
                // *****************************************************************
                // Pricing API
                // *****************************************************************
                NWZC157001PMsg prcApiPMsg = callPricingAPI04(dsImptOrdTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, mapDsRtlIntgItemMap, mapFndgIstlCompAmtDlrInfo, errBean);
                setOrdDtlPricingInfo(prcApiPMsg, listDsImptOrdDtlBean);
                // *****************************************************************
                // Create DS_IMPT_PRC_CALC_BASE
                // *****************************************************************
                List<DS_IMPT_PRC_CALC_BASETMsg> listDsImptPrcCalcBaseTMsg = createDsImptPrcCalcBase(prcApiPMsg, dsImptOrdTMsg, listDsImptOrdDtlBean);
                // *****************************************************************
                // Insert lists into DS_IMPT_ORD Tables
                // *****************************************************************
                insertTMsgs(dsImptOrdTMsg, dsImptOrdSlsCrTMsg, dsImptOrdDelyInfoTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, listDsImptPrcCalcBaseTMsg, errBean);
                // *****************************************************************
                // Insert lists into DS_IMPT_ORD_ERR
                // *****************************************************************
                insertDsImptOrdErr(errBean);

            } catch (SQLException e) {
                sqlExceptionHandler(e);
            } finally {
                S21SsmLowLevelCodingClient.closeResource(stmt, rsSetOrdItem);
            }
        }
        writeEndLogLn("createDsImptOrdTables");
    }

    List<Map<String, Object>> getInstlCdList(String cpoOrdNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
        ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);

        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getInstlCd", ssmParam);

        return listMap;
    }

    List<Map<String, Object>> getSerNum(String rcpoDtlSq) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
        ssmParam.put(COL_RCPO_DTL_SQ, rcpoDtlSq);

        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getSerNum", ssmParam);

        return listMap;
    }

    private boolean isContrBizTp(String contrBizTpCd) {
        String[] typeList = {BIZ_TP_P, BIZ_TP_TP, BIZ_TP_R, BIZ_TP_FL, BIZ_TP_LT, BIZ_TP_TR, BIZ_TP_CP, BIZ_TP_DP };
        for (String type : typeList) {
            if (type.equals(contrBizTpCd)) {
                return true;
            }
        }
        return false;
    }

    private boolean isSerTake(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(COL_MDSE_CD, mdseCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getShpgSerTakeFlg", ssmParam);

        if (map == null) {
            return false;
        } else if (ZYPConstant.FLG_ON_Y.equals(map.get(COL_SHPG_SER_TAKE_FLG))) {
            return true;
        }
        return false;
    }

    private int[] setRelation(int ordQty, int cntConfig) {
        int quo = ordQty / cntConfig;
        int mod = ordQty % cntConfig;
        int cnt = 1;
        int[] listConfig = new int[ordQty];

        int l = 0;
        for (int m = 0; m < cntConfig; m++) {
            for (; l < ordQty; l++) {
                if (quo >= cnt) {
                    listConfig[l] = m;
                    cnt++;
                } else if (mod > 0) {
                    listConfig[l] = m;
                    mod--;
                    l++;
                    break;
                } else {
                    break;
                }
            }
            cnt = 1;
        }
        return listConfig;

    }

    private Map<String, BigDecimal> getFndgDlrIstlCompAmt(String cpoOrdNum, String instlCd, String istlSubLocCd) {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
        ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);
        ssmParam.put(COL_INSTL_CD, instlCd);
        ssmParam.put(COL_ISTL_SUB_LOC_CD, istlSubLocCd);

        Map<String, BigDecimal> map = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getFndgDlrIstlCompAmt", ssmParam);

        return map;
    }

    private ArrayList<String> getTradPtnr() {

        ArrayList<String> listTradPtnr = new ArrayList<String>();
        String[] listStr = getVarCharConst("CUSA_EDI_TRD_PTNR_CD").split(",", 0);
        for (int i = 0; i < listStr.length; i++) {
            listTradPtnr.add(listStr[i]);
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
        ssmParam.put(COL_EDI_CUST_TP_NM, VAL_EDI_CUST_TP_NM);
        ssmParam.put(COL_EDI_TRD_PTNR_CD, listTradPtnr);

        List<Map<String, String>> mapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getTradPtnr", ssmParam);

        ArrayList<String> listBillToCust = new ArrayList<String>();
        for (int i = 0; i < mapList.size(); i++) {
            listBillToCust.add(mapList.get(i).get(COL_BILL_TO_CUST_CD));
        }
        return listBillToCust;
    }

    // ///////////////////////////////////////////////////////////////
    // CREATE SNAPSHOTS
    // ///////////////////////////////////////////////////////////////
    private void createCsaOrdMsgViewSnap(String cpoOrdNum, S21SsmExecutionParameter execParam) {
        writeStartLogLn("createCsaOrdMsgViewSnap");

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
            ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);

            stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdMsgV", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                CSA_ORD_MSG_VIEW_SNAPTMsg ordMsgTMsg = new CSA_ORD_MSG_VIEW_SNAPTMsg();
                S21ResultSetMapper.map(rsSet, ordMsgTMsg);

                // set primary key
                ZYPEZDItemValueSetter.setValue(ordMsgTMsg.csaOrdMsgViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_ORD_MSG_VIEW_SNAP_SQ));

                S21FastTBLAccessor.insert(ordMsgTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordMsgTMsg.getReturnCode())) {
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_RTL_MSG_PK_TRX_HDR_NUM, ordMsgTMsg.rtlMsgPk.getValue().toString(), ordMsgTMsg.trxHdrNum.getValue()) });
                    this.totalErrorCnt++;
                    throw new S21AbendException(NWAM0728E, new String[] {"CSA_ORD_MSG_VIEW_SNAP" });
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            writeEndLogLn("createCsaOrdMsgViewSnap");
        }
    }

    private List<CSA_ORD_ITEM_VIEW_SNAPTMsg> createCsaOrdItemViewSnap(String cpoOrdNum, S21SsmExecutionParameter execParam, ArrayList<String> listBillToCust) {
        writeStartLogLn("createCsaOrdItemViewSnap");
        List<CSA_ORD_ITEM_VIEW_SNAPTMsg> list = new ArrayList<CSA_ORD_ITEM_VIEW_SNAPTMsg>();

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
            ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);
            ssmParam.put(COL_USR_DLR_TP_CD, USR_DLR_TP.DEALER);
            ssmParam.put(COL_CPO_DTL_CANC_FLG, ZYPConstant.FLG_OFF_N);
            // Update Start 2016/06/29 QC#10123
            // ArrayList<String> billToCustCdList = new ArrayList<String>();
            // billToCustCdList.add("A450");
            // billToCustCdList.add("A658");
            // billToCustCdList.add("D114");
            // ssmParam.put(COL_BILL_TO_CUST_CD, billToCustCdList);
            ssmParam.put(COL_BILL_TO_CUST_CD, listBillToCust);
            // Update End 2016/0629 QC#10123

            stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdItemV", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {

                CSA_ORD_ITEM_VIEW_SNAPTMsg ordItemTMsg = new CSA_ORD_ITEM_VIEW_SNAPTMsg();
                S21ResultSetMapper.map(rsSet, ordItemTMsg);

                // set primary key
                ZYPEZDItemValueSetter.setValue(ordItemTMsg.csaOrdItemViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_ORD_ITEM_VIEW_SNAP_SQ));

                // get ROSS_ORD_TP_TXT
                ZYPEZDItemValueSetter.setValue(ordItemTMsg.rossOrdTpMsgTxt, //
                        getRossOrdTpMsgTxt(ordItemTMsg.cpoOrdNum.getValue() //
                                , ordItemTMsg.instlCd.getValue() //
                                , ordItemTMsg.istlSubLocCd.getValue()));

                S21FastTBLAccessor.insert(ordItemTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordItemTMsg.getReturnCode())) {
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_CPO_ORD_NUM_INSTL_CD, cpoOrdNum, ordItemTMsg.instlCd.getValue(), ordItemTMsg.istlSubLocCd.getValue()) });
                    this.totalErrorCnt++;
                    throw new S21AbendException(NWAM0728E, new String[] {"CSA_ORD_ITEM_VIEW_SNAP" });
                }
                list.add(ordItemTMsg);
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            writeEndLogLn("createCsaOrdItemViewSnap");
        }
        return list;
    }

    private String getRossOrdTpMsgTxt(String cpoOrdNum, String instlCd, String istlSubLocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD_CUSA, VAL_GLBL_CMPY_CD_CUSA);
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);
        ssmParam.put(COL_INSTL_CD, instlCd);
        ssmParam.put(COL_ISTL_SUB_LOC_CD, istlSubLocCd);
        ArrayList<String> listBillTpCd = new ArrayList<String>();
        listBillTpCd.add(BIZ_TP_CP);
        listBillTpCd.add(BIZ_TP_DP);
        listBillTpCd.add(BIZ_TP_FL);
        listBillTpCd.add(BIZ_TP_LT);
        listBillTpCd.add(BIZ_TP_P);
        listBillTpCd.add(BIZ_TP_R);
        listBillTpCd.add(BIZ_TP_TP);
        listBillTpCd.add(BIZ_TP_TR);
        ssmParam.put(COL_BIZ_TP, listBillTpCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getRossOrdTpMsgTxt", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_ROSS_ORD_TP_MSG_TXT);
    }

    private void createCsaOrdSerViewSnap(String cpoOrdNum, List<CSA_ORD_ITEM_VIEW_SNAPTMsg> listItem, S21SsmExecutionParameter execParam) {
        writeStartLogLn("createCsaOrdSerViewSnap");

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
            ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);
            ssmParam.put(COL_RCPO_DTL_SQ, setRcpoDtlSq(listItem));

            stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdSerV", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                CSA_ORD_SER_VIEW_SNAPTMsg ordSerTMsg = new CSA_ORD_SER_VIEW_SNAPTMsg();
                S21ResultSetMapper.map(rsSet, ordSerTMsg);

                // set primary key
                ZYPEZDItemValueSetter.setValue(ordSerTMsg.csaOrdSerViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_ORD_SER_VIEW_SNAP_SQ));

                S21FastTBLAccessor.insert(ordSerTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordSerTMsg.getReturnCode())) {
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_CPO_ORD_NUM_RCPO_DTL_SQ, cpoOrdNum, ordSerTMsg.rcpoDtlSq.getValue().toString()) });
                    this.totalErrorCnt++;
                    throw new S21AbendException(NWAM0728E, new String[] {"CSA_ORD_SER_VIEW_SNAP" });
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            writeEndLogLn("createCsaOrdSerViewSnap");
        }
    }

    private void createCsaOrdUsgViewSnap(String cpoOrdNum, List<CSA_ORD_ITEM_VIEW_SNAPTMsg> listItem, S21SsmExecutionParameter execParam) {
        writeStartLogLn("createCsaOrdUsgViewSnap");

        PreparedStatement stmt = null;
        ResultSet rsSet = null;

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(COL_GLBL_CMPY_CD, VAL_GLBL_CMPY_CD_CUSA);
            ssmParam.put(COL_CPO_ORD_NUM, cpoOrdNum);
            ssmParam.put(COL_RCPO_DTL_SQ, setRcpoDtlSq(listItem));

            stmt = this.ssmLLClient.createPreparedStatement("getCsaOrdUsgV", ssmParam, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                CSA_ORD_USG_VIEW_SNAPTMsg ordUsgTMsg = new CSA_ORD_USG_VIEW_SNAPTMsg();
                S21ResultSetMapper.map(rsSet, ordUsgTMsg);

                // set primary key
                ZYPEZDItemValueSetter.setValue(ordUsgTMsg.csaOrdUsgViewSnapPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.CSA_ORD_USG_VIEW_SNAP_SQ));

                S21FastTBLAccessor.insert(ordUsgTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(ordUsgTMsg.getReturnCode())) {
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_CPO_ORD_NUM_RCPO_DTL_SQ, cpoOrdNum, ordUsgTMsg.rcpoDtlSq.getValue().toString()) });
                    this.totalErrorCnt++;
                    throw new S21AbendException(NWAM0728E, new String[] {"CSA_ORD_USG_VIEW_SNAP" });
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
            writeEndLogLn("createCsaOrdUsgViewSnap");
        }
    }

    private ArrayList<BigDecimal> setRcpoDtlSq(List<CSA_ORD_ITEM_VIEW_SNAPTMsg> listItem) {
        ArrayList<BigDecimal> rcpoDtlSqList = new ArrayList<BigDecimal>();
        for (int i = 0; i < listItem.size(); i++) {
            rcpoDtlSqList.add(listItem.get(i).rcpoDtlSq.getValue());
        }
        return rcpoDtlSqList;
    }

    private static <T> T[] toArray(T... param) {
        return param;
    }

    // ///////////////////////////////////////////////////////////////
    // CREATE DS_IMPT_ORDTMsgs
    // ///////////////////////////////////////////////////////////////

    private DS_IMPT_ORDTMsg createDsImptOrd(CSA_ORD_HDR_VIEW_SNAPTMsg ordHdrTMsg, ResultSet rsSetOrdItem, Map<String, Object> mapDsRtlDlrInfo, Map<String, Object> mapOrdCatgBizCtx, DsImptOrdErrBean errBean) throws SQLException {

        DS_IMPT_ORDTMsg dsImptOrdTMsg = new DS_IMPT_ORDTMsg();

        // (3) DS_XREF_ACCT
        Map<String, Object> mapDsXrefAcct = NWXC245001.getDsXrefAcct(this.glblCmpyCd, rsSetOrdItem.getString(COL_INSTL_CD), rsSetOrdItem.getString(COL_ISTL_SUB_LOC_CD));

        BigDecimal dsImptOrdPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.cpoSrcTpCd, CPO_SRC_TP.CUSA_NAD_OR_GMD);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMNSSFFF));
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.ordSrcRefNum, ordHdrTMsg.cpoOrdNum.getValue() + "-" + rsSetOrdItem.getString(COL_INSTL_CD) + "-" + rsSetOrdItem.getString(COL_ISTL_SUB_LOC_CD));
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.sysSrcCd, SYS_SRC.S21_ORDER);
        if (SHIP_TO_ADDR_TP.INSTALL_LOCATION.equals(rsSetOrdItem.getString(COL_SHIP_TO_ADDR_TP_CD))) {
            if (mapOrdCatgBizCtx != null) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsOrdCatgCd, (String) mapOrdCatgBizCtx.get(COL_DS_ORD_CATG_CD));
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsOrdTpCd, (String) mapOrdCatgBizCtx.get(COL_DS_ORD_TP_CD));
            }
        } else {
            if (mapDsRtlDlrInfo != null) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsOrdCatgCd, (String) mapDsRtlDlrInfo.get(COL_DS_ORD_CATG_CD));
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dsOrdTpCd, (String) mapDsRtlDlrInfo.get(COL_DS_ORD_TP_CD));
            }
        }
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.cpoOrdTpCd, getCpoOrdTpCd(dsImptOrdTMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.custIssPoNum, ordHdrTMsg.custIssPoNum);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.custIssPoDt, ordHdrTMsg.rtlCustPoDt);
        if (mapDsRtlDlrInfo != null) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.billToCustCd, (String) mapDsRtlDlrInfo.get(COL_BILL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.billToCustAcctCd, getSellToCustCd(dsImptOrdTMsg.billToCustCd.getValue()));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.sellToCustCd, dsImptOrdTMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.soldToCustLocCd, dsImptOrdTMsg.billToCustCd);
        }
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
        if (mapDsXrefAcct != null) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToCustAcctCd, (String) mapDsXrefAcct.get(COL_SELL_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToCustCd, (String) mapDsXrefAcct.get(COL_SHIP_TO_CUST_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToLocNm, (String) mapDsXrefAcct.get(COL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToAddlLocNm, (String) mapDsXrefAcct.get(COL_ADDL_LOC_NM));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToFirstLineAddr, (String) mapDsXrefAcct.get(COL_FIRST_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToScdLineAddr, (String) mapDsXrefAcct.get(COL_SCD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToThirdLineAddr, (String) mapDsXrefAcct.get(COL_THIRD_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToFrthLineAddr, (String) mapDsXrefAcct.get(COL_FRTH_LINE_ADDR));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToCtyAddr, (String) mapDsXrefAcct.get(COL_CTY_ADDR));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToStCd, (String) mapDsXrefAcct.get(COL_ST_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToProvNm, (String) mapDsXrefAcct.get(COL_PROV_NM));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToCntyNm, getCntyNm((BigDecimal) mapDsXrefAcct.get(COL_CNTY_PK)));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToPostCd, (String) mapDsXrefAcct.get(COL_POST_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipToCtryCd, (String) mapDsXrefAcct.get(COL_CTRY_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipTo01RefCmntTxt, (String) mapDsXrefAcct.get(COL_FIRST_REF_CMNT_TXT));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shipTo02RefCmntTxt, (String) mapDsXrefAcct.get(COL_SCD_REF_CMNT_TXT));
        }
        Map<String, String> mapFrtCond = getFrtCond(dsImptOrdTMsg.dsOrdTpCd.getValue());
        if (mapFrtCond != null) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.frtCondCd, mapFrtCond.get(COL_FRT_COND_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.frtChrgToCd, mapFrtCond.get(COL_FRT_CHRG_TO_CD));
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.frtChrgMethCd, mapFrtCond.get(COL_FRT_CHRG_METH_CD));
        }
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.prcBaseDt, this.salesDate);
        // 2017/01/31 S21_NA#17314 Mod Start
        if (S21StringUtil.isEquals(FRT_COND.COLLECT, dsImptOrdTMsg.frtCondCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.carrSvcLvlCd, getDsRtlDefInfoValTxt("CARRIER_SERVICE_LEVEL"));
        }
        // 2017/01/31 S21_NA#17314 Mod End
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.shpgSvcLvlCd, getDsRtlDefInfoValTxt("SHIPPING_SERVICE_LEVEL"));

        if (!setPrcCatgCd(dsImptOrdTMsg, errBean)) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.prcCatgCd, getDsRtlDefInfoValTxt("PRICE_LIST"));
        }
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.flPrcListCd, dsImptOrdTMsg.prcCatgCd.getValue());
        if (!setDefSlsRep(dsImptOrdTMsg, errBean)) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.slsRepTocCd, getDsRtlDefInfoValTxt("SALES_REP"));
        }
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.aquNum, dsImptOrdTMsg.ordSrcRefNum);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.sendInvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);

        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.imptStsCd, IMPT_STS.NOT_PROCESSED);
        // 2016/11/29 S21_NA#16266 Add Start
        ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.maintOnlyFlg, ZYPConstant.FLG_OFF_N);
        // 2016/11/29 S21_NA#16266 Add End

        return dsImptOrdTMsg;
    }

    private boolean setPrcCatgCd(DS_IMPT_ORDTMsg dsImptOrdTMsg, DsImptOrdErrBean errBean) {

        writeStartLogLn("setPrcCatgCd");

        try {
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();

            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_PRICE_LIST);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, dsImptOrdTMsg.dsOrdCatgCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, dsImptOrdTMsg.dsOrdTpCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, NWXC150001DsCheck.getLineBizTpCd(this.glblCmpyCd, this.salesDate, dsImptOrdTMsg.dsOrdTpCd.getValue()));
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, dsImptOrdTMsg.sellToCustCd.getValue());
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, ZYPConstant.FLG_ON_Y);

            new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);

            createErrPrcAPI(errBean, prcApiPMsg, dsImptOrdTMsg.dsImptOrdPk.getValue());

            if (ZYPCommonFunc.hasValue(prcApiPMsg.defPrcCatgCd)) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.prcCatgCd, prcApiPMsg.defPrcCatgCd.getValue());
                return true;
            }

            return false;
        } finally {
            writeEndLogLn("setPrcCatgCd");
        }
    }

    private boolean setDefSlsRep(DS_IMPT_ORDTMsg dsImptOrdTMsg, DsImptOrdErrBean errBean) {
        writeStartLogLn("setDefSlsRep");

        try {
            boolean ret = false;
            if (ZYPCommonFunc.hasValue(dsImptOrdTMsg.shipToCustCd.getValue())) {
                NMZC260001PMsg apiNMZC260001PMsg = callDefSlsRepApi(dsImptOrdTMsg, errBean);

                if (hasValidValue(apiNMZC260001PMsg.defSlsRepList)) {
                    NMZC260001_defSlsRepListPMsg slsRepPMsg;
                    // 2017/03/14 S21_NA#16855 Add Start
                    // Mod Start 2017/12/14 QC#19804(Sol#349)
                    //String trtyGrpTpCd = getTrtyGrpTpCdFromDsOrdTpCd(dsImptOrdTMsg.dsOrdTpCd.getValue(), this.glblCmpyCd);
                    List<String> trtyGrpTpCdList = getTrtyGrpTpCdFromDsOrdTpCd(dsImptOrdTMsg.dsOrdTpCd.getValue(), this.glblCmpyCd);
                    // Mod End 2017/12/14 QC#19804(Sol#349)

                    LINE_BIZ_ROLE_TPTMsg lineBizRoleTpTMsg = new LINE_BIZ_ROLE_TPTMsg();
                    lineBizRoleTpTMsg.setSQLID("001");
                    lineBizRoleTpTMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
                    lineBizRoleTpTMsg.setConditionValue("primRepRoleFlg01", ZYPConstant.FLG_ON_Y);
                    LINE_BIZ_ROLE_TPTMsgArray TMsgArray = (LINE_BIZ_ROLE_TPTMsgArray) EZDTBLAccessor.findByCondition(lineBizRoleTpTMsg);
                    List<String> targetWriterList = new ArrayList<String>();
                    if (TMsgArray != null && TMsgArray.length() > 0) {
                        for (int i = 0; i < TMsgArray.length(); i++) {
                            LINE_BIZ_ROLE_TPTMsg tMsg = TMsgArray.no(i);
                            targetWriterList.add(tMsg.lineBizRoleTpCd.getValue());
                        }
                    }
      
                    String lineBizTpCd = NWXC150001DsCheck.getLineBizTpCd(this.glblCmpyCd, this.salesDate, dsImptOrdTMsg.dsOrdTpCd.getValue());
                    // 2017/03/14 S21_NA#16855 Add End

                    for (int i = 0; i < apiNMZC260001PMsg.defSlsRepList.getValidCount(); i++) {
                        slsRepPMsg = apiNMZC260001PMsg.defSlsRepList.no(i);

                        // 2017/03/14 S21_NA#16855 Mod Start
                        // if (ZYPCommonFunc.hasValue(slsRepPMsg.tocCd)) {
                        // Mod Start 2017/12/14 QC#19804(Sol#349)
                        //if (slsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd) //
                        //        && (!ZYPCommonFunc.hasValue(trtyGrpTpCd) //
                        //        || trtyGrpTpCd.equals(slsRepPMsg.trtyGrpTpCd.getValue())) //
                        //        && targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                        boolean matchFlag = false;
                        if (trtyGrpTpCdList != null) {
                            if (trtyGrpTpCdList.contains(slsRepPMsg.trtyGrpTpCd.getValue())) {
                                matchFlag = true;
                            }
                        } else {
                            if (slsRepPMsg.lineBizTpCd.getValue().equals(lineBizTpCd)) {
                                matchFlag = true;
                            }
                        }

                        if (matchFlag && targetWriterList.contains(slsRepPMsg.lineBizRoleTpCd.getValue())) {
                            // Mod End 2017/12/14 QC#19804(Sol#349)
                        // 2017/03/14 S21_NA#16855 Mod End
                            ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.slsRepTocCd, slsRepPMsg.tocCd.getValue());
                            ret = true;
                            break;
                        }
                    }
                }
            }
            return ret;
        } finally {
            writeEndLogLn("setDefSlsRep");
        }
    }

    /**
     * callDefSlsReqApi
     * @param hdrBean ImptHdrBean
     * @return NMZC260001PMsg
     */
    private NMZC260001PMsg callDefSlsRepApi(DS_IMPT_ORDTMsg dsImptOrdTMsg, DsImptOrdErrBean errBean) {
        writeStartLogLn("callDefSlsRepApi");

        try {
            NMZC260001PMsg pMsg = new NMZC260001PMsg();
            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.shipToCustCd, dsImptOrdTMsg.shipToCustCd.getValue());

            // call NMZC2600 Dafault Sales Rep API
            new NMZC260001().execute(pMsg, ONBATCH_TYPE.BATCH);

            addErrorMsgList(dsImptOrdTMsg, pMsg, errBean);

            return pMsg;
        } finally {
            writeEndLogLn("callDefSlsRepApi");
        }
    }

    private void addErrorMsgList(DS_IMPT_ORDTMsg dsImptOrdTMsg, EZDPMsg pMsg, DsImptOrdErrBean errBean) {

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    setValidErrTMsg(errBean, dsImptOrdTMsg.dsImptOrdPk.getValue(), null, null, msgId, S21MessageFunc.clspGetMessage(msgId, msg.getXxMsgPrmArray()));
                }
            }
        }
    }

    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {
        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
    }

    private DS_IMPT_ORD_SLS_CRTMsg createDsImptOrdSlsCr(DS_IMPT_ORDTMsg dsImptOrdTMsg) {
        DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrTMsg = new DS_IMPT_ORD_SLS_CRTMsg();

        BigDecimal dsImptOrdSlsCrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SLS_CR_SQ);
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.dsImptOrdSlsCrPk, dsImptOrdSlsCrPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.dsImptOrdPk, dsImptOrdTMsg.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.slsRepTocCd, dsImptOrdTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.slsRepRoleTpCd, getOrgFuncRoleTpCd(dsImptOrdTMsg.slsRepTocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.slsRepCrPct, BigDecimal.valueOf(100));
        ZYPEZDItemValueSetter.setValue(dsImptOrdSlsCrTMsg.slsCrQuotFlg, ZYPConstant.FLG_ON_Y);

        return dsImptOrdSlsCrTMsg;
    }

    private DS_IMPT_ORD_DELY_INFOTMsg createDsImptOrdDelyInfo(DS_IMPT_ORDTMsg dsImptOrdTMsg, ResultSet rsSetOrdItem) throws SQLException {
        DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoTMsg = new DS_IMPT_ORD_DELY_INFOTMsg();

        // (6) DELY_ADDL_CMNT_TXT
        String delyAddlCmntTxt = NWXC245001.getDelyAddlCmntTxt(this.glblCmpyCd, rsSetOrdItem.getString(COL_INSTL_CD), rsSetOrdItem.getString(COL_ISTL_SUB_LOC_CD));

        BigDecimal dsImptOrdDelyInfoPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DELY_INFO_SQ);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.dsImptOrdDelyInfoPk, dsImptOrdDelyInfoPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.dsImptOrdPk, dsImptOrdTMsg.dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.loadDockAvalFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.stairCrawReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.elevAvalFlg, ZYPConstant.FLG_OFF_N);

        ZYPEZDItemValueSetter.setValue(dsImptOrdDelyInfoTMsg.delyAddlCmntTxt, delyAddlCmntTxt);

        return dsImptOrdDelyInfoTMsg;
    }

    private DS_IMPT_ORD_CONFIGTMsg createDsImptOrdConfig(DS_IMPT_ORDTMsg dsImptOrdTMsg, ResultSet rsSetOrdItem, int cntConfig, boolean isMainMachine) throws SQLException {
        DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = new DS_IMPT_ORD_CONFIGTMsg();

        BigDecimal dsImptOrdConfigPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsImptOrdPk, dsImptOrdTMsg.dsImptOrdPk);

        Integer dsOrdPosnNum = cntConfig + 1;
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dsOrdPosnNum, dsOrdPosnNum.toString());

        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
        if (isMainMachine) {
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.configTpCd, CONFIG_TP.NEW);
        } else {
            ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.configTpCd, CONFIG_TP.ADD_TO_CONFIG);

            // (5) SVC_CONFIG_MSTR_PK
            BigDecimal svcConfigMstrPk = NWXC245001.getSvcConfigMstrPk(this.glblCmpyCd, rsSetOrdItem.getString(COL_CPO_ORD_NUM), rsSetOrdItem.getString(COL_INSTL_CD), rsSetOrdItem.getString(COL_ISTL_SUB_LOC_CD));

            if (ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.svcConfigMstrPk, svcConfigMstrPk);
            }
        }

        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.billToCustAcctCd, dsImptOrdTMsg.billToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.billToCustLocCd, dsImptOrdTMsg.billToCustCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToCustAcctCd, dsImptOrdTMsg.shipToCustAcctCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToCustLocCd, dsImptOrdTMsg.shipToCustCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.dropShipFlg, dsImptOrdTMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToLocNm, dsImptOrdTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToAddlLocNm, dsImptOrdTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToFirstLineAddr, dsImptOrdTMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToScdLineAddr, dsImptOrdTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToThirdLineAddr, dsImptOrdTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToFrthLineAddr, dsImptOrdTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToFirstRefCmntTxt, dsImptOrdTMsg.shipTo01RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToScdRefCmntTxt, dsImptOrdTMsg.shipTo02RefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToCtyAddr, dsImptOrdTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToStCd, dsImptOrdTMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToProvNm, dsImptOrdTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToCntyNm, dsImptOrdTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToPostCd, dsImptOrdTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.shipToCtryCd, dsImptOrdTMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dsImptOrdConfigTMsg.configCratTs, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMNSSFFF));

        return dsImptOrdConfigTMsg;
    }

    /**
     * @param mode String
     * @param dsImptOrdTMsg DS_IMPT_ORDTMsg
     * @param dsImptOrdConfigTMsg DS_IMPT_ORD_CONFIGTMsg
     * @param rsSetOrdItem ResultSet
     * @param mapDsRtlDlrInfo Map<String, Object>
     * @param mapDsRtlIntgItemMap Map<String, Object>
     * @param qty int
     * @param serNum String
     * @param seq Integer
     * @return DS_IMPT_ORD_DTLTMsg
     * @throws SQLException
     */
    private DsImptOrdDtlBean createDsImptOrdDtl(//
            String mode //
            , DS_IMPT_ORDTMsg dsImptOrdTMsg //
            , DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg //
            , ResultSet rsSetOrdItem //
            , Map<String, Object> mapDsRtlDlrInfo //
            , Map<String, Object> mapDsRtlIntgItemMap //
            , int qty //
            , String serNum //
            , Integer seq //
    ) throws SQLException {
        DsImptOrdDtlBean dtlBean = new DsImptOrdDtlBean();

        BigDecimal dsImptOrdDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ);
        ZYPEZDItemValueSetter.setValue(dtlBean.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.dsImptOrdDtlPk, dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(dtlBean.dsImptOrdConfigPk, dsImptOrdConfigTMsg.dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(dtlBean.dsImptOrdPk, dsImptOrdTMsg.dsImptOrdPk);

        if (MODE_NRML_DTL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlBean.ordSrcRefLineNum, rsSetOrdItem.getString(COL_RCPO_DTL_SQ) + "-" + seq.toString());
        } else {
            ZYPEZDItemValueSetter.setValue(dtlBean.ordSrcRefLineNum, dsImptOrdDtlPk.toString());
        }

        // 2017/01/31 S21_NA#17297 Add Start
        if (MODE_FNDG_FEE.equals(mode) || MODE_INST_FEE.equals(mode)) {
            dtlBean.ordSrcRefLineSubNum.clear();
        } else {
            ZYPEZDItemValueSetter.setValue(dtlBean.ordSrcRefLineSubNum, DEF_SUB_LINE_NUM);
        }
        // 2017/01/31 S21_NA#17297 Add End

        if (MODE_NRML_DTL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlBean.mdseCd, rsSetOrdItem.getString(COL_MDSE_CD));
        } else if (MODE_FNDG_FEE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlBean.mdseCd, (String) mapDsRtlIntgItemMap.get(COL_FNDG_MDSE_CD));
        } else if (MODE_INST_FEE.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlBean.mdseCd, (String) mapDsRtlIntgItemMap.get(COL_ISTL_MDSE_CD));
        }
        ZYPEZDItemValueSetter.setValue(dtlBean.mdseNm, getMdseNm(dtlBean.mdseCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlBean.dropShipFlg, dsImptOrdConfigTMsg.dropShipFlg);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToCustCd, dsImptOrdConfigTMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToLocNm, dsImptOrdConfigTMsg.shipToLocNm);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToAddlLocNm, dsImptOrdConfigTMsg.shipToAddlLocNm);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToFirstLineAddr, dsImptOrdConfigTMsg.shipToFirstLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToScdLineAddr, dsImptOrdConfigTMsg.shipToScdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToThirdLineAddr, dsImptOrdConfigTMsg.shipToThirdLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToFrthLineAddr, dsImptOrdConfigTMsg.shipToFrthLineAddr);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToFirstRefCmntTxt, dsImptOrdConfigTMsg.shipToFirstRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToScdRefCmntTxt, dsImptOrdConfigTMsg.shipToScdRefCmntTxt);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToCtyAddr, dsImptOrdConfigTMsg.shipToCtyAddr);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToStCd, dsImptOrdConfigTMsg.shipToStCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToProvNm, dsImptOrdConfigTMsg.shipToProvNm);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToCntyNm, dsImptOrdConfigTMsg.shipToCntyNm);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToPostCd, dsImptOrdConfigTMsg.shipToPostCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.shipToCtryCd, dsImptOrdConfigTMsg.shipToCtryCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.ordQty, BigDecimal.valueOf(qty));
        ZYPEZDItemValueSetter.setValue(dtlBean.ordCustUomQty, BigDecimal.valueOf(qty));
        ZYPEZDItemValueSetter.setValue(dtlBean.prcCatgCd, dsImptOrdTMsg.prcCatgCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.flPrcListCd, dsImptOrdTMsg.flPrcListCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.prcBaseDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(dtlBean.ccyCd, getCcyCd(dtlBean.prcCatgCd.getValue()));
        ZYPEZDItemValueSetter.setValue(dtlBean.rddDt, dsImptOrdTMsg.rddDt);
        ZYPEZDItemValueSetter.setValue(dtlBean.uomCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.stkStsCd, STK_STS.GOOD);
        ZYPEZDItemValueSetter.setValue(dtlBean.slsRepOrSlsTeamTocCd, dsImptOrdTMsg.slsRepTocCd);
        ZYPEZDItemValueSetter.setValue(dtlBean.custUomCd, PKG_UOM.EACH);
        ZYPEZDItemValueSetter.setValue(dtlBean.setItemShipCpltFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.dsOrdPosnNum, dsImptOrdConfigTMsg.dsOrdPosnNum);
        BigDecimal unitNetWt = getUnitNetWt(dtlBean.mdseCd.getValue(), dtlBean.custUomCd.getValue(), dtlBean.ordCustUomQty.getValue());
        ZYPEZDItemValueSetter.setValue(dtlBean.unitNetWt, unitNetWt);
        ZYPEZDItemValueSetter.setValue(dtlBean.configItemFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.baseCmptFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.custIstlFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.svcConfigMstrPk, dsImptOrdConfigTMsg.svcConfigMstrPk);
        if (MODE_NRML_DTL.equals(mode)) {
            if (ZYPCommonFunc.hasValue(serNum)) {
                // 2017/02/07 QC#16575 UPD START
                // ZYPEZDItemValueSetter.setValue(dtlBean.svcMachMstrPk, getSvcMachMstrPk(serNum));
                ZYPEZDItemValueSetter.setValue(dtlBean.svcMachMstrPk, getSvcMachMstrPk(serNum, dtlBean.mdseCd.getValue()));
                // 2017/02/207 QC#16575 UPD E N D
                ZYPEZDItemValueSetter.setValue(dtlBean.serNum, serNum);
            }
        }

        if (MODE_NRML_DTL.equals(mode)) {
            ZYPEZDItemValueSetter.setValue(dtlBean.dsOrdLineCatgCd, getDsOrdLineCatgCd(dsImptOrdTMsg.dsOrdTpCd.getValue()));
        } else {
            ZYPEZDItemValueSetter.setValue(dtlBean.dsOrdLineCatgCd, (String) mapDsRtlIntgItemMap.get(COL_DS_ORD_LINE_CATG_CD));
        }
        if (MODE_NRML_DTL.equals(mode)) {
            // 2017/11/24 S21_NA#22624 Mod Start
//            if (SHIP_TO_ADDR_TP.INSTALL_LOCATION.equals(rsSetOrdItem.getString(COL_SHIP_TO_ADDR_TP_CD))) {
            if (SHIP_TO_ADDR_TP.INSTALL_LOCATION.equals(rsSetOrdItem.getString(COL_SHIP_TO_ADDR_TP_CD))//
                    || SHIP_TO_ADDR_TP.OTHER.equals(rsSetOrdItem.getString(COL_SHIP_TO_ADDR_TP_CD))) {
            // 2017/11/24 S21_NA#22624 Mod End
                ZYPEZDItemValueSetter.setValue(dtlBean.ordLineSrcCd, ORD_LINE_SRC.CUSA_DROP_SHIP);
                ZYPEZDItemValueSetter.setValue(dtlBean.rtlWhCd, getVarCharConst("DROP_SHIP_RTL_WH_CD"));
            } else {
                ZYPEZDItemValueSetter.setValue(dtlBean.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
                if (mapDsRtlDlrInfo != null) {
                    ZYPEZDItemValueSetter.setValue(dtlBean.rtlWhCd, (String) mapDsRtlDlrInfo.get(COL_WH_CD));
                }
                ZYPEZDItemValueSetter.setValue(dtlBean.rtlSwhCd, "NEW");
            }
            ZYPEZDItemValueSetter.setValue(dtlBean.invtyLocCd, dtlBean.rtlWhCd.getValue() + dtlBean.rtlSwhCd.getValue());
        } else {
            ZYPEZDItemValueSetter.setValue(dtlBean.ordLineSrcCd, ORD_LINE_SRC.INTERNAL);
        }

        ZYPEZDItemValueSetter.setValue(dtlBean.supdLockFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(dtlBean.imptLineFlg, ZYPConstant.FLG_ON_Y); // QC#11561
        // 2016/11/29 S21_NA#16266 Add Start
        ZYPEZDItemValueSetter.setValue(dtlBean.preExistFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(dtlBean.finItemLineFlg, ZYPConstant.FLG_OFF_N);
        // 2016/11/29 S21_NA#16266 Add End

        return dtlBean;
    }

    private List<DS_IMPT_PRC_CALC_BASETMsg> createDsImptPrcCalcBase(NWZC157001PMsg prcApiPMsg, DS_IMPT_ORDTMsg dsImptOrdTMsg, List<DsImptOrdDtlBean> listDsImptOrdDtlBean) {
        List<DS_IMPT_PRC_CALC_BASETMsg> listDsImptPrcCalcBaseTMsg = new ArrayList<DS_IMPT_PRC_CALC_BASETMsg>();

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            for (int j = 0; j < prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.getValidCount(); j++) {
                DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBaseTMsg = new DS_IMPT_PRC_CALC_BASETMsg();

                NWZC157003PMsg prcApi03PMsg = prcApiPMsg.NWZC157002PMsg.no(i).NWZC157003PMsg.no(j);

                DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg = new DS_IMPT_ORD_DTLTMsg();

                for (int k = 0; k < listDsImptOrdDtlBean.size(); k++) {
                    if (prcApi03PMsg.trxLineNum.getValue().equals(listDsImptOrdDtlBean.get(k).getTrxLineNum()) && prcApi03PMsg.trxLineSubNum.getValue().equals(listDsImptOrdDtlBean.get(k).getTrxLineSubNum())) {
                        dsImptOrdDtlTMsg = listDsImptOrdDtlBean.get(k);
                        break;
                    }
                }
                BigDecimal dsImptPrcCalcBasePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_PRC_CALC_BASE_SQ);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.dsImptPrcCalcBasePk, dsImptPrcCalcBasePk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.dsImptOrdPk, dsImptOrdDtlTMsg.dsImptOrdPk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.dsImptOrdDtlPk, dsImptOrdDtlTMsg.dsImptOrdDtlPk);

                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCondTpCd, prcApi03PMsg.prcCondTpCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcDtlGrpCd, prcApi03PMsg.prcDtlGrpCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcJrnlGrpCd, prcApi03PMsg.prcJrnlGrpCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.pkgUomCd, prcApi03PMsg.pkgUomCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCondUnitCd, prcApi03PMsg.prcCondUnitCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCalcMethCd, prcApi03PMsg.prcCalcMethCd);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.dsMdsePrcPk, prcApi03PMsg.dsMdsePrcPk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.specCondPrcPk, prcApi03PMsg.specCondPrcPk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.frtPerWtPk, prcApi03PMsg.frtPerWtPk);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCondManEntryFlg, prcApi03PMsg.prcCondManEntryFlg);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCondManAddFlg, prcApi03PMsg.prcCondManAddFlg);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcCondManDelFlg, prcApi03PMsg.prcCondManDelFlg);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.autoPrcAmtRate, prcApi03PMsg.autoPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.maxPrcAmtRate, prcApi03PMsg.maxPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.minPrcAmtRate, prcApi03PMsg.minPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.manPrcAmtRate, prcApi03PMsg.manPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.calcPrcAmtRate, prcApi03PMsg.calcPrcAmtRate);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.unitPrcAmt, prcApi03PMsg.unitPrcAmt);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.autoPrcCcyCd, prcApi03PMsg.autoPrcCcyCd);
                // 2018/09/18 QC#9700 Add Start
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcRuleApplyFlg, prcApi03PMsg.prcRuleApplyFlg);
                ZYPEZDItemValueSetter.setValue(dsImptPrcCalcBaseTMsg.prcRulePrcdPk, prcApi03PMsg.prcRulePrcdPk);
                // 2018/09/18 QC#9700 Add End
                listDsImptPrcCalcBaseTMsg.add(dsImptPrcCalcBaseTMsg);
            }
        }
        return listDsImptPrcCalcBaseTMsg;
    }

    private BigDecimal exchFuncUnitPrice(String ccyCd, BigDecimal dealAmt) {
        BigDecimal funcAmt = null;
        NWXC001001ExchangeData exchData = new NWXC001001ExchangeData();
        exchData.setGlblCmpyCd(this.glblCmpyCd);
        exchData.setSlsDt(this.salesDate);
        exchData.setCcyCd(ccyCd);
        List<NWXC001001ExchangePriceData> priceDataList = new ArrayList<NWXC001001ExchangePriceData>();
        NWXC001001ExchangeAmoutData exchAmtData = new NWXC001001ExchangeAmoutData();

        NWXC001001ExchangeAmount grsAmt = new NWXC001001ExchangeAmount();
        grsAmt.setDealAmt(dealAmt);

        exchAmtData.setGrsAmt(grsAmt);
        priceDataList.add(exchAmtData);

        exchData.setPriceData(priceDataList);

        NWXC001001Exchange.exchFuncUnitPrice(exchData);
        if (!exchData.getXxMsgIdList().isEmpty()) {
            return null;
        }
        for (int i = 0; i < exchData.getPriceData().size(); i++) {
            NWXC001001ExchangePriceData prcData = exchData.getPriceData().get(i);
            for (int j = 0; j < prcData.getAmountList().size(); j++) {
                funcAmt = prcData.getAmountList().get(j).getFuncAmt();
            }
        }
        return funcAmt;
    }

    private String getCpoOrdTpCd(String dsOrdTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_DS_ORD_TP_CD, dsOrdTpCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getCpoOrdTpCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_CPO_ORD_TP_CD);
    }

    private String getSellToCustCd(String billToCustCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_BILL_TO_CUST_CD, billToCustCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getSellToCustCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_SELL_TO_CUST_CD);
    }

    private String getCntyNm(BigDecimal cntyPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(COL_CNTY_PK, cntyPk);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getCntyNm", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_CNTY_NM);
    }

    private Map<String, String> getFrtCond(String dsOrdTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_DS_ORD_TP_CD, dsOrdTpCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getFrtCond", ssmParam);

        return map;
    }

    private String getDsRtlDefInfoValTxt(String dsRtlDefInfoNm) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_DS_RTL_DEF_INFO_NM, dsRtlDefInfoNm);
        ssmParam.put(COL_SALES_DATE, this.salesDate);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDsRtlDefInfoValTxt", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_DS_RTL_DEF_INFO_VAL_TXT);
    }

    private String getOrgFuncRoleTpCd(String slsRepTocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_TOC_CD, slsRepTocCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getOrgFuncRoleTpCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_ORG_FUNC_ROLE_TP_CD);
    }

    private String getCcyCd(String prcCatgCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_PRC_CATG_CD, prcCatgCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getCcyCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_CCY_CD);
    }

    private BigDecimal getUnitNetWt(String mdseCd, String custUomCd, BigDecimal ordCustUomQty) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_CUST_UOM_CD, custUomCd);
        ssmParam.put(COL_MDSE_CD, mdseCd);

        Map<String, BigDecimal> map = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getUnitNetWt", ssmParam);
        if (map == null) {
            return BigDecimal.ZERO;
        }

        return ordCustUomQty.multiply(map.get(COL_IN_POUND_WT));
    }

        // 2017/02/07 QC#16575 UPD START
    private BigDecimal getSvcMachMstrPk(String serNum, String mdseCd) {
        // 2017/02/07 QC#16575 UPD E N D
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_SER_NUM, serNum);
        // 2017/02/07 QC#16575 ADD START
        ssmParam.put(COL_MDSE_CD, mdseCd);
        // 2017/02/07 QC#16575 ADD E N D

        Map<String, BigDecimal> map = (Map<String, BigDecimal>) ssmBatchClient.queryObject("getSvcMachMstrPk", ssmParam);
        if (map == null) {
            return null;
        }

        return map.get(COL_SVC_MACH_MSTR_PK);
    }

    private String getDsOrdLineCatgCd(String dsOrdTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_DS_ORD_TP_CD, dsOrdTpCd);
        ssmParam.put(COL_PRIM_LINE_CATG_FLG, ZYPConstant.FLG_ON_Y);
        ssmParam.put(COL_DS_ORD_LINE_DRCTN_CD, DS_ORD_LINE_DRCTN.OUTBOUND);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getDsOrdLineCatgCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_DS_ORD_LINE_CATG_CD);
    }

    private String getVarCharConst(String varCharConstNm) {
        String val = ZYPCodeDataUtil.getVarCharConstValue(varCharConstNm, this.glblCmpyCd);
        return val;
    }

    private String getCoaExtnCd(String slsRepTocCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_TOC_CD, slsRepTocCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getCoaExtnCd", ssmParam);
        if (map == null) {
            return "";
        }
        return map.get(COL_COA_EXTN_CD);

    }

    private String getMdseNm(String mdseCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_MDSE_CD, mdseCd);

        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getMdseNm", ssmParam);
        if (map == null) {
            return null;
        }
        return map.get(COL_MDSE_NM);

    }

    private void insertTMsgs(//
            DS_IMPT_ORDTMsg dsImptOrdTMsg //
            , DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrTMsg //
            , DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoTMsg //
            , List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg //
            , List<DsImptOrdDtlBean> listDsImptOrdDtlBean //
            , List<DS_IMPT_PRC_CALC_BASETMsg> listDsImptPrcCalcBaseTMsg //
            , DsImptOrdErrBean errBean) {
        writeStartLogLn("insertTMsgs");

        execDsCheck(dsImptOrdTMsg, dsImptOrdSlsCrTMsg, dsImptOrdDelyInfoTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, listDsImptPrcCalcBaseTMsg, errBean);

        // set IMPT_STS error
        for (int i = 0; i < errBean.getDsImptOrdErrList().size(); i++) {
            DS_IMPT_ORD_ERRTMsg errTMsg = errBean.getDsImptOrdErrList().get(i);
            if (errTMsg.dsImptOrdPk.getValue().equals(dsImptOrdTMsg.dsImptOrdPk.getValue())) {
                ZYPEZDItemValueSetter.setValue(dsImptOrdTMsg.imptStsCd, IMPT_STS.ERROR);
                break;
            }
        }

        try {
            // DS_IMPT_ORD
            EZDTBLAccessor.insert(dsImptOrdTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptOrdTMsg.getReturnCode())) {
                writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD" });
                writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });

                throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD" });
            }

            // DS_IMPT_ORD_CONFIG
            for (int i = 0; i < listDsImptOrdConfigTMsg.size(); i++) {
                DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = listDsImptOrdConfigTMsg.get(i);
                EZDTBLAccessor.insert(dsImptOrdConfigTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptOrdConfigTMsg.getReturnCode())) {
                    writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD_CONFIG" });
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });
                    throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD_CONFIG" });
                }
            }

            // DS_IMPT_ORD_SLS_CR
            EZDTBLAccessor.insert(dsImptOrdSlsCrTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptOrdSlsCrTMsg.getReturnCode())) {
                writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD_SLS_CR" });
                writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });
                throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD_SLS_CR" });
            }

            // DS_IMPT_ORD_DELY_INFO
            EZDTBLAccessor.insert(dsImptOrdDelyInfoTMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptOrdDelyInfoTMsg.getReturnCode())) {
                writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD_DELY_INFO" });
                writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });
                throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD_DELY_INFO" });
            }

            // DS_IMPT_ORD_DTL
            for (int i = 0; i < listDsImptOrdDtlBean.size(); i++) {
                DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg = listDsImptOrdDtlBean.get(i);
                EZDTBLAccessor.insert(dsImptOrdDtlTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptOrdDtlTMsg.getReturnCode())) {
                    writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD_DTL" });
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });
                    throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD_DTL" });
                }
            }

            // DS_IMPT_PRC_CALC_BASE
            for (int i = 0; i < listDsImptPrcCalcBaseTMsg.size(); i++) {
                DS_IMPT_PRC_CALC_BASETMsg dsImptPrcCalcBaseTMsg = listDsImptPrcCalcBaseTMsg.get(i);
                EZDTBLAccessor.insert(dsImptPrcCalcBaseTMsg);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(dsImptPrcCalcBaseTMsg.getReturnCode())) {
                    writeErrLog(NWAM0728E, new String[] {"DS_IMPT_PRC_CALC_BASE" });
                    writeErrLog(NMAM0176E, new String[] {String.format(MSG_ORD_SRC_REF_NUM, dsImptOrdTMsg.ordSrcRefNum.getValue()) });
                    throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_PRC_CALC_BASE" });
                }
            }

        } finally {
            writeEndLogLn("insertTMsgs");
        }
    }

    private void insertDsImptOrdErr(DsImptOrdErrBean errBean) {
        for (int j = 0; j < errBean.getDsImptOrdErrList().size(); j++) {
            DS_IMPT_ORD_ERRTMsg errTMsg = errBean.getDsImptOrdErrList().get(j);
            EZDTBLAccessor.insert(errTMsg);
            this.totalErrorCnt++;
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(errTMsg.getReturnCode())) {
                writeErrLog(NWAM0728E, new String[] {"DS_IMPT_ORD_ERR" });
                throw new S21AbendException(NWAM0728E, new String[] {"DS_IMPT_ORD_ERR" });
            }
        }
    }

    // ///////////////////////////////////////////////////////////////
    // PRICING API
    // ///////////////////////////////////////////////////////////////
    /**
     * callPricingAPI04
     * @param dsImptOrdTMsg
     * @param listDsImptOrdConfigTMsg
     * @param listDsImptOrdDtlTMsg
     * @return
     */
    private NWZC157001PMsg callPricingAPI04(DS_IMPT_ORDTMsg dsImptOrdTMsg //
            , List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg //
            , List<DsImptOrdDtlBean> listDsImptOrdDtlBean //
            , Map<String, Object> mapDsRtlIntgItemMap //
            , Map<String, BigDecimal> mapFndgIstlCompAmtDlrInfo, DsImptOrdErrBean errBean) {

        writeStartLogLn("callPricingAPI");

        try {
            NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
            // Global Company Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
            // Process Mode
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, NWZC157001.GET_ORDER_ALL);
            // Price Based Date
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
            // Price Context Type Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, PRC_CTX_TP.SALES_PRICING);
            // Request Date
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.orgRqstDelyDt, this.salesDate);
            // DS Order Category Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, dsImptOrdTMsg.dsOrdCatgCd);
            // DS Order Type Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, dsImptOrdTMsg.dsOrdTpCd);
            // Line Business Type Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd, NWXC150001DsCheck.getLineBizTpCd(this.glblCmpyCd, this.salesDate, dsImptOrdTMsg.dsOrdTpCd.getValue()));
            // Sold to Account Number (Sold To Customer Code)
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, dsImptOrdTMsg.sellToCustCd);
            // CPO Source Type Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.cpoSrcTpCd, dsImptOrdTMsg.cpoSrcTpCd);
            // Customer Issue PO Number(Customer PO)
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.custIssPoNum, dsImptOrdTMsg.custIssPoNum);
            // Negotiated Deal Amount
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.negoDealAmt, dsImptOrdTMsg.negoDealAmt);
            // Tax Flag
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.taxCalcFlg, ZYPConstant.FLG_ON_Y);
            // Special Handling Type Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.spclHdlgTpCd, dsImptOrdTMsg.spclHdlgTpCd);
            // Lease End Term Purchase Option Code
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.leasePrchOptCd, dsImptOrdTMsg.leasePrchOptCd);
            // Summary Line Flag
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxSmryLineFlg, ZYPConstant.FLG_ON_Y);
            NWZC157002PMsg prcApi2PMsg;

            Integer lineNum = 1;
            Integer lineSubNum = 1;
            boolean skipFlg = false;
            for (int i = 0; i < listDsImptOrdDtlBean.size(); i++) {
                DsImptOrdDtlBean dsImptOrdDtlBean = listDsImptOrdDtlBean.get(i);
                DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg = listDsImptOrdDtlBean.get(i);

                DS_IMPT_ORD_CONFIGTMsg dsImptOrdConfigTMsg = getConfigTMsg(dsImptOrdDtlTMsg, listDsImptOrdConfigTMsg);

                prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(i);

                if (lineSubNum > 999) {
                    lineNum++;
                    lineSubNum = 1;
                }
                // Transaction Line Number
                dsImptOrdDtlBean.setTrxLineNum(lineNum.toString());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineNum, dsImptOrdDtlBean.getTrxLineNum());
                // Transaction Line Sub Number
                dsImptOrdDtlBean.setTrxLineSubNum(lineSubNum.toString());
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.trxLineSubNum, dsImptOrdDtlBean.getTrxLineSubNum());
                // Config Category Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
                // Bill to Customer Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.billToCustCd, dsImptOrdConfigTMsg.billToCustLocCd);
                // Ship to Customer Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shipToCustCd, dsImptOrdConfigTMsg.shipToCustLocCd);
                // Ship to Account Number
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_SH, dsImptOrdConfigTMsg.shipToCustAcctCd);
                // Bill to Account Number
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsAcctNum_BL, dsImptOrdConfigTMsg.billToCustAcctCd);
                // Price Category Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCatgCd, dsImptOrdDtlBean.prcCatgCd);
                // Currency Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ccyCd, dsImptOrdDtlBean.ccyCd);
                // Merchandise Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdseCd, dsImptOrdDtlBean.mdseCd);
                // Unit of Measure Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.pkgUomCd, dsImptOrdDtlBean.custUomCd);
                // DS CPO Line Category Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.dsOrdLineCatgCd, dsImptOrdDtlBean.dsOrdLineCatgCd);
                // Order Quantity
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordQty, dsImptOrdDtlBean.ordQty);
                // UOM Quantity
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ordCustUomQty, dsImptOrdDtlBean.ordCustUomQty);
                // Invoiced Qty
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.invQty, BigDecimal.ZERO);
                // Model ID
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.mdlId, dsImptOrdConfigTMsg.mdlId);
                // Ship-To City Address
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctyAddr_SH, dsImptOrdDtlBean.shipToCtyAddr);
                // Ship-To State Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.stCd_SH, dsImptOrdDtlBean.shipToStCd);
                // Ship-To Country Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.ctryCd_SH, dsImptOrdDtlBean.shipToCtryCd);
                // Sales Rep or Sales Team TOC Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.slsRepOrSlsTeamTocCd, dsImptOrdDtlBean.slsRepOrSlsTeamTocCd);
                // Retail Warehouse Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlWhCd, dsImptOrdDtlBean.rtlWhCd);
                // Add Start 2018/01/04 QC#22371
                // Retail Sub Warehouse Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.rtlSwhCd, dsImptOrdDtlBean.rtlSwhCd);
                // Add End 2018/01/04 QC#22371
                // Freight Condition Code(Freight Term)
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.frtCondCd, dsImptOrdTMsg.frtCondCd);
                // Shipping Service Level Code
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.shpgSvcLvlCd, dsImptOrdTMsg.shpgSvcLvlCd);
                // Mannual Price
                if (mapDsRtlIntgItemMap.get(COL_FNDG_MDSE_CD).equals(dsImptOrdDtlBean.mdseCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, mapFndgIstlCompAmtDlrInfo.get(COL_FNDG_DLR_COMP_AMT));
                } else if (mapDsRtlIntgItemMap.get(COL_ISTL_MDSE_CD).equals(dsImptOrdDtlBean.mdseCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.prcCondManEntryFlg, ZYPConstant.FLG_ON_Y);
                    ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxUnitPrc, mapFndgIstlCompAmtDlrInfo.get(COL_ISTL_COMP_AMT));
                }
                // Line Close Flag
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.xxPrcCloFlg, ZYPConstant.FLG_OFF_N);
                // COA Extension Code (Business Unit)
                ZYPEZDItemValueSetter.setValue(prcApi2PMsg.coaExtnCd, getCoaExtnCd(dsImptOrdTMsg.slsRepTocCd.getValue()));

                lineSubNum++;
                prcApiPMsg.NWZC157002PMsg.setValidCount(prcApiPMsg.NWZC157002PMsg.getValidCount() + 1);
                
                // 2017/02/07 S21_NA#17302 Add Start
                if (!ZYPCommonFunc.hasValue(prcApi2PMsg.shipToCustCd.getValue())) {
                    skipFlg = true;
                }
                // 2017/02/07 S21_NA#17302 Add End
            }

            if (!skipFlg) { // 2017/02/07 S21_NA#17302  Start
                // execute NWZC157001 mode: 04
                new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
    
                createErrPrcAPI(errBean, prcApiPMsg, dsImptOrdTMsg.dsImptOrdPk.getValue());
            }

            return prcApiPMsg;
        } finally {
            writeEndLogLn("callPricingAPI");
        }
    }

    private void createErrPrcAPI(DsImptOrdErrBean errBean, NWZC157001PMsg prcApiPMsg, BigDecimal dsImptOrdPk) {
        for (int i = 0; i < prcApiPMsg.xxMsgIdList.getValidCount(); i++) {
            NWZC157001_xxMsgIdListPMsg errMsg = prcApiPMsg.xxMsgIdList.no(i);
            setValidErrTMsg(errBean, dsImptOrdPk, null, null, errMsg.xxMsgId.getValue(), S21MessageFunc.clspGetMessage(errMsg.xxMsgId.getValue(), toArray(errMsg.xxMsgPrmTxt_0.getValue(), errMsg.xxMsgPrmTxt_1.getValue(),
                    errMsg.xxMsgPrmTxt_2.getValue())));
        }

        for (int i = 0; i < prcApiPMsg.NWZC157002PMsg.getValidCount(); i++) {
            NWZC157002PMsg prcApi2PMsg = prcApiPMsg.NWZC157002PMsg.no(i);
            for (int j = 0; j < prcApi2PMsg.xxMsgIdList.getValidCount(); j++) {
                NWZC157002_xxMsgIdListPMsg errMsg = prcApi2PMsg.xxMsgIdList.no(j);
                setValidErrTMsg(errBean, dsImptOrdPk, null, null, errMsg.xxMsgId.getValue(), S21MessageFunc.clspGetMessage(errMsg.xxMsgId.getValue(), toArray(errMsg.xxMsgPrmTxt_0.getValue(), errMsg.xxMsgPrmTxt_1.getValue(),
                        errMsg.xxMsgPrmTxt_2.getValue())));
            }
        }
    }

    private void setOrdDtlPricingInfo(NWZC157001PMsg prcApiPMsg, List<DsImptOrdDtlBean> listDsImptOrdDtlBean) {
        for (int i = 0; i < prcApiPMsg.NWZC157004PMsg.getValidCount(); i++) {
        	for (int j = 0; j < listDsImptOrdDtlBean.size(); j++) {
                if (prcApiPMsg.NWZC157004PMsg.no(i).trxLineNum.getValue().equals(listDsImptOrdDtlBean.get(j).getTrxLineNum()) //
                        && prcApiPMsg.NWZC157004PMsg.no(i).trxLineSubNum.getValue().equals(listDsImptOrdDtlBean.get(j).getTrxLineSubNum())) {
                    NWZC157004PMsg prcApi04PMsg = prcApiPMsg.NWZC157004PMsg.no(i);
                    DS_IMPT_ORD_DTLTMsg dsImptOrdDtlTMsg =listDsImptOrdDtlBean.get(j);

                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.dealPrcListPrcAmt, prcApi04PMsg.xxUnitGrsPrcAmt.getValue());
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.funcPrcListPrcAmt, exchFuncUnitPrice(dsImptOrdDtlTMsg.ccyCd.getValue(), dsImptOrdDtlTMsg.dealPrcListPrcAmt.getValue()));
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.entDealNetUnitPrcAmt, prcApi04PMsg.xxUnitNetPrcAmt);
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.entFuncNetUnitPrcAmt, exchFuncUnitPrice(dsImptOrdDtlTMsg.ccyCd.getValue(), dsImptOrdDtlTMsg.entDealNetUnitPrcAmt.getValue()));

                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.cpoDtlDealNetAmt, dsImptOrdDtlTMsg.dealPrcListPrcAmt.getValue().multiply(dsImptOrdDtlTMsg.ordQty.getValue()));
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.cpoDtlDealSlsAmt, dsImptOrdDtlTMsg.entDealNetUnitPrcAmt.getValue().multiply(dsImptOrdDtlTMsg.ordQty.getValue()));
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.cpoDtlFuncNetAmt, exchFuncUnitPrice(dsImptOrdDtlTMsg.ccyCd.getValue(), dsImptOrdDtlTMsg.cpoDtlDealNetAmt.getValue()));
                    ZYPEZDItemValueSetter.setValue(dsImptOrdDtlTMsg.cpoDtlFuncSlsAmt, exchFuncUnitPrice(dsImptOrdDtlTMsg.ccyCd.getValue(), dsImptOrdDtlTMsg.cpoDtlDealSlsAmt.getValue()));
                }
            }
        }
    }

    // ///////////////////////////////////////////////////////////////
    // VALIDATION
    // ///////////////////////////////////////////////////////////////

    private void execDsCheck(DS_IMPT_ORDTMsg dsImptOrdTMsg //
            , DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrTMsg //
            , DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoTMsg //
            , List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg //
            , List<DsImptOrdDtlBean> listDsImptOrdDtlBean //
            , List<DS_IMPT_PRC_CALC_BASETMsg> listDsImptPrcCalcBaseTMsg //
            , DsImptOrdErrBean errBean //
    ) {
        // this Validation Check is Based on NWZC150001
        writeStartLogLn("execDsCheck");

        if (checkEssentialForHeader(dsImptOrdTMsg, errBean)) {
            return;
        }

        if (checkEssentialForConfig(listDsImptOrdConfigTMsg, errBean)) {
            return;
        }

        if (checkEssentialForDetail(listDsImptOrdDtlBean, errBean)) {
            return;
        }

        if (checkMasterForHeader(dsImptOrdTMsg, errBean)) {
            return;
        }

        if (checkMasterForDetail(listDsImptOrdDtlBean, errBean)) {
            return;
        }

        if (checkMasterForConfig(listDsImptOrdConfigTMsg, errBean)) {
            return;
        }

        otherCheck(dsImptOrdTMsg, dsImptOrdSlsCrTMsg, dsImptOrdDelyInfoTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, listDsImptPrcCalcBaseTMsg, errBean);

        writeEndLogLn("execDsCheck");
    }

    private boolean checkEssentialForHeader(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
        boolean ret = false;
        if (!ZYPCommonFunc.hasValue(tMsg.glblCmpyCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM0011E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.dsOrdCatgCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1401E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.dsOrdTpCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1253E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.billToCustAcctCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1377E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.billToCustCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM0617E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(tMsg.shipToCustCd)) { // 2017/02/07 S21_NA#17302 Mod
            // setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1379E);
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWAM0929E); // 2017/02/07 S21_NA#17302 Mod
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.sellToCustCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1402E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.soldToCustLocCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1403E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.shpgSvcLvlCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM0049E);
            ret = true;
        }
        // 2017/02/07 S21_NA#17302 Del Start
//        if (!ZYPCommonFunc.hasValue(tMsg.shipToCustCd)) {
//            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM0619E);
//            ret = true;
//        }
        // 2017/02/07 S21_NA#17302 Del End
        if (!ZYPCommonFunc.hasValue(tMsg.slsRepTocCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM0688E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.prcCatgCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1405E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.flPrcListCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1406E);
            ret = true;
        }
        if (!ZYPCommonFunc.hasValue(tMsg.frtCondCd)) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1266E);
            ret = true;
        }

        return ret;
    }

    private boolean checkEssentialForConfig(List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg, DsImptOrdErrBean errBean) {

        boolean ret = false;

        for (int i = 0; i < listDsImptOrdConfigTMsg.size(); i++) {

            DS_IMPT_ORD_CONFIGTMsg tMsg = listDsImptOrdConfigTMsg.get(i);

            if (!ZYPCommonFunc.hasValue(tMsg.dsOrdPosnNum)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1408E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.configCatgCd)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1409E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.configTpCd)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1410E);
                ret = true;
            }

            if (!ZYPCommonFunc.hasValue(tMsg.billToCustAcctCd)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1377E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.billToCustLocCd)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM0617E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.shipToCustAcctCd) || !ZYPCommonFunc.hasValue(tMsg.shipToCustLocCd)) { // 2017/02/07 S21_NA#17302 Mod
                //setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1379E);
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWAM0929E); // 2017/02/07 S21_NA#17302 Mod Start
                ret = true;
            }
            // 2017/02/07 S21_NA#17302 Del Start
//            if (!ZYPCommonFunc.hasValue(tMsg.shipToCustLocCd)) {
//                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM0619E);
//                ret = true;
//            }
            // 2017/02/07 S21_NA#17302 Del End
        }
        return ret;
    }

    private boolean checkEssentialForDetail(List<DsImptOrdDtlBean> listDsImptOrdDtlBean, DsImptOrdErrBean errBean) {
        boolean ret = false;

        for (int i = 0; i < listDsImptOrdDtlBean.size(); i++) {

            DS_IMPT_ORD_DTLTMsg tMsg = listDsImptOrdDtlBean.get(i);

            if (!ZYPCommonFunc.hasValue(tMsg.dsOrdLineCatgCd)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1413E);
                ret = true;
            }
            if (!ZYPCommonFunc.hasValue(tMsg.baseCmptFlg)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1414E);
                ret = true;
            }
        }
        return ret;
    }

    private boolean checkMasterForHeader(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
        boolean ret = false;

        if (!NWXC150001DsCheck.existsDsOrdCatg(this.glblCmpyCd, tMsg.dsOrdCatgCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1415E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAccount(this.glblCmpyCd, this.salesDate, tMsg.billToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1416E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAccount(this.glblCmpyCd, this.salesDate, tMsg.shipToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1417E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsSoldToLocation(this.glblCmpyCd, this.salesDate, tMsg.soldToCustLocCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1418E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsSalesRep(this.glblCmpyCd, tMsg.slsRepTocCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWAM0054E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsPrcCatg(this.glblCmpyCd, tMsg.prcCatgCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1419E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsFlPrcList(this.glblCmpyCd, tMsg.flPrcListCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1420E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsAssnPgm(this.glblCmpyCd, this.salesDate, tMsg.prcContrNum.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1421E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeasePrchOpt(this.glblCmpyCd, tMsg.leasePrchOptCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1422E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeaseTerm(this.glblCmpyCd, tMsg.leaseTermCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1423E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsLeasePmtFreq(this.glblCmpyCd, tMsg.leasePmtFreqCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1424E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsOrdLogTp(this.glblCmpyCd, tMsg.ordLogTpCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1425E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsFrtCond(this.glblCmpyCd, tMsg.frtCondCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1426E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsCarrSvcLvl(this.glblCmpyCd, tMsg.carrSvcLvlCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1427E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsSpclHdlgTp(this.glblCmpyCd, tMsg.spclHdlgTpCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1428E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsPrePmtTp(this.glblCmpyCd, tMsg.prePmtTpCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1429E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsCrRebilRsnCatg(this.glblCmpyCd, tMsg.crRebilRsnCatgCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1430E);
            ret = true;
        }
        if (!NWXC150001DsCheck.existsCarrSvcLvl(this.glblCmpyCd, tMsg.carrSvcLvlCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1427E);
            ret = true;
        }

        return ret;
    }

    private boolean checkMasterForDetail(List<DsImptOrdDtlBean> listDsImptOrdDtlBean, DsImptOrdErrBean errBean) {
        boolean ret = false;

        for (int i = 0; i < listDsImptOrdDtlBean.size(); i++) {
            DS_IMPT_ORD_DTLTMsg tMsg = listDsImptOrdDtlBean.get(i);

            if (!NWXC150001DsCheck.existsDsOrdLineCatg(this.glblCmpyCd, tMsg.dsOrdLineCatgCd.getValue(), DS_ORD_LINE_DRCTN.OUTBOUND)) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1431E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsOrdLineSrc(this.glblCmpyCd, tMsg.ordLineSrcCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1432E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsRtlWh(this.glblCmpyCd, this.salesDate, tMsg.rtlWhCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1433E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsRtlSubWh(this.glblCmpyCd, this.salesDate, tMsg.rtlWhCd.getValue(), tMsg.rtlSwhCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1434E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsFlPrcList(this.glblCmpyCd, tMsg.flPrcListCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1420E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsCrRebil(this.glblCmpyCd, tMsg.crRebilCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1435E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsSplyTp(this.glblCmpyCd, tMsg.splyTpCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1436E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsSbstItem(this.glblCmpyCd, tMsg.sbstMdseCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1439E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsPrcCatg(this.glblCmpyCd, tMsg.prcCatgCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), tMsg.dsImptOrdDtlPk.getValue(), NWZM1419E);
                ret = true;
            }
        }
        return ret;
    }

    private boolean checkMasterForConfig(List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg, DsImptOrdErrBean errBean) {

        boolean ret = false;

        for (int i = 0; i < listDsImptOrdConfigTMsg.size(); i++) {
            DS_IMPT_ORD_CONFIGTMsg tMsg = listDsImptOrdConfigTMsg.get(i);

            if (!NWXC150001DsCheck.existsConfigCatg(this.glblCmpyCd, tMsg.configCatgCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1440E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsConfigTp(this.glblCmpyCd, tMsg.configTpCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1441E);
                ret = true;
            }

            // Out bound Not Y N N
            if (!NWXC150001DsCheck.matchConfigTp(this.glblCmpyCd, tMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, false)) {
                if (!ZYPCommonFunc.hasValue(tMsg.svcConfigMstrPk) || !NWXC150001DsCheck.existsSvcConfigMstr(this.glblCmpyCd, tMsg.svcConfigMstrPk.getValue())) {
                    setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1442E);
                    ret = true;
                }
            }
            if (!NWXC150001DsCheck.existsMdlId(this.glblCmpyCd, tMsg.mdlId.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1443E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsAccount(this.glblCmpyCd, this.salesDate, tMsg.billToCustAcctCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1416E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsBillToLocation(this.glblCmpyCd, this.salesDate, tMsg.billToCustLocCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1444E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsAccount(this.glblCmpyCd, this.salesDate, tMsg.shipToCustAcctCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1417E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsShipToLocation(this.glblCmpyCd, this.salesDate, tMsg.shipToCustLocCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1445E);
                ret = true;
            }
            if (!NWXC150001DsCheck.existsState(this.glblCmpyCd, tMsg.shipToStCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1446E);
                ret = true;
            }

            if (!NWXC150001DsCheck.existsCtry(this.glblCmpyCd, tMsg.shipToCtryCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1448E);
                ret = true;
            }
            if (NWXC150001DsCheck.matchConfigTp(this.glblCmpyCd, tMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
                if (!ZYPCommonFunc.hasValue(tMsg.svcConfigMstrPk) || !SVC_MACH_USG_STS.AT_CUSTOMER.equals(NWXC150001DsCheck.getMachineUsageStatus(this.glblCmpyCd, tMsg.svcConfigMstrPk.getValue()))) {
                    setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), tMsg.dsImptOrdConfigPk.getValue(), null, NWZM1887E);
                    ret = true;
                }
            }
        }
        return ret;
    }

    private void otherCheck(//
            DS_IMPT_ORDTMsg dsImptOrdTMsg //
            , DS_IMPT_ORD_SLS_CRTMsg dsImptOrdSlsCrTMsg //
            , DS_IMPT_ORD_DELY_INFOTMsg dsImptOrdDelyInfoTMsg //
            , List<DS_IMPT_ORD_CONFIGTMsg> listDsImptOrdConfigTMsg //
            , List<DsImptOrdDtlBean> listDsImptOrdDtlBean //
            , List<DS_IMPT_PRC_CALC_BASETMsg> listDsImptPrcCalcBaseTMsg //
            , DsImptOrdErrBean errBean) {

        otherHeaderCheck(dsImptOrdTMsg, errBean);

        otherConfigCheck(dsImptOrdTMsg, listDsImptOrdConfigTMsg, errBean);

        otherCheckDuplicateMachine(dsImptOrdTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, errBean);

        otherDetailCheck(dsImptOrdTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, errBean);

        otherSvcExchCheck(dsImptOrdTMsg, listDsImptOrdConfigTMsg, listDsImptOrdDtlBean, errBean);

        otherQtyCheck(dsImptOrdTMsg, listDsImptOrdDtlBean, errBean);

        otherEasyPackCheck(dsImptOrdTMsg, listDsImptOrdDtlBean, errBean);

        Map<Map<String, String>, Map<String, String>> mdseInfoCacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        otherMdseStsCheck(dsImptOrdTMsg, listDsImptOrdDtlBean, errBean, mdseInfoCacheMap);
    }

    private void otherHeaderCheck(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
        if (NWXC150001DsCheck.checkDsOrdTpAndDsOrdCatgRelation(this.glblCmpyCd, tMsg.dsOrdTpCd.getValue(), tMsg.dsOrdCatgCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1450E);
        }
        if (NWXC150001DsCheck.checkCpoOrdTpAndDsOrdRsnRelation(this.glblCmpyCd, this.salesDate, tMsg.dsOrdTpCd.getValue(), tMsg.dsOrdRsnCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1451E);
        }
        checkBillSellShipRelation(tMsg, errBean);

        if (NWXC150001DsCheck.checkCarrSvcLvlAndShpgSvcLvlRelation(//
                this.glblCmpyCd //
                , tMsg.shpgSvcLvlCd.getValue() //
                , tMsg.carrSvcLvlCd.getValue())) {

            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1457E);
        }
        // 2018/12/12 QC#29315 Mod Start
        //if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(//
        //        this.glblCmpyCd //
        //        , this.salesDate //
        //        , tMsg.dsOrdTpCd.getValue() //
        //        , tMsg.frtCondCd.getValue() //
        //        , tMsg.shpgSvcLvlCd.getValue() //
        //        , tMsg.carrSvcLvlCd.getValue())) {
        if (NWXC150001DsCheck.checkFrtCondSvcLvlRelation(//
                this.glblCmpyCd //
                , this.salesDate //
                , tMsg.dsOrdTpCd.getValue() //
                , tMsg.frtCondCd.getValue() //
                , tMsg.shpgSvcLvlCd.getValue() //
                , tMsg.carrSvcLvlCd.getValue()
                , tMsg.shipToCustAcctCd.getValue()
                , getLocNum(this.glblCmpyCd, tMsg.shipToCustCd.getValue()))) {
            // 2018/12/12 QC#29315 Mod End
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1458E);
        }
        if (NWXC150001DsCheck.checkAddlCarrAcctNumRelation(this.glblCmpyCd, tMsg.carrAcctNum.getValue(), tMsg.frtCondCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1459E);
        }
        if (NWXC150001DsCheck.checkCsmpRelation(this.glblCmpyCd, tMsg.csmpContrNum.getValue(), tMsg.sellToCustCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1461E);
        }
        if (NWXC150001DsCheck.checkCsaNumRelation(this.glblCmpyCd, tMsg.dlrRefNum.getValue(), tMsg.sellToCustCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1462E);
        }
        if (NWXC150001DsCheck.checkSalesRepRelation(this.glblCmpyCd, tMsg.slsRepTocCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1463E);
        }
        if (NWXC150001DsCheck.checkPrcCatgRelation(this.glblCmpyCd, tMsg.dsOrdTpCd.getValue(), this.salesDate, tMsg.prcCatgCd.getValue())) {
            NWZC157001PMsg prcApiPMsg //
            = callPricingApiForGetPrcList(//
                    tMsg //
                    , NWZC157001.GET_PRICE_LIST //
                    , PRC_CTX_TP.SALES_PRICING //
                    , ZYPConstant.FLG_ON_Y);

            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                List<String> ml = S21ApiUtil.getXxMsgIdList(prcApiPMsg);
                for (String msgId : ml) {
                    setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, msgId);
                }
                return;
            }
            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, tMsg.prcCatgCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1464E);
            }
        }
        if (NWXC150001DsCheck.checkFlPrcListRelation(this.glblCmpyCd, tMsg.dsOrdTpCd.getValue(), this.salesDate, tMsg.flPrcListCd.getValue())) {
            NWZC157001PMsg prcApiPMsg //
            = callPricingApiForGetPrcList(//
                    tMsg //
                    , NWZC157001.GET_PRICE_LIST //
                    , PRC_CTX_TP.FLOOR_PRICE //
                    , ZYPConstant.FLG_ON_Y);
            if (S21ApiUtil.isXxMsgId(prcApiPMsg)) {
                List<String> ml = S21ApiUtil.getXxMsgIdList(prcApiPMsg);
                for (String msgId : ml) {
                    setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, msgId);
                }
                return;
            }
            if (checkIncludePrcCatg(prcApiPMsg.xxPrcList, tMsg.flPrcListCd.getValue())) {
                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1465E);
            }
        }
    }

    private void checkBillSellShipRelation(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
        if (NWXC150001DsCheck.checkBillToRalation(this.glblCmpyCd, tMsg.billToCustCd.getValue(), tMsg.billToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1452E);
        }
        if (NWXC150001DsCheck.checkShipToRalation(this.glblCmpyCd, tMsg.shipToCustCd.getValue(), tMsg.shipToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1453E);
        }
        if (NWXC150001DsCheck.checkSoldToRalation(this.glblCmpyCd, tMsg.soldToCustLocCd.getValue(), tMsg.sellToCustCd.getValue())) {
            setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1454E);
        }
        // Mod Start 2018/02/26 QC#22967
        //if (checkBillShipSoldRelation(tMsg, errBean)) {
        //    setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, NWZM1455E);
        //}
        checkBillShipSoldRelation(tMsg, errBean);
        // Mod End 2018/02/26 QC#22967

    }

    // Mod Start 2018/02/26 QC#22967
    //private boolean checkBillShipSoldRelation(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
    private void checkBillShipSoldRelation(DS_IMPT_ORDTMsg tMsg, DsImptOrdErrBean errBean) {
        // Mod End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                tMsg.soldToCustLocCd.getValue(), tMsg.shipToCustAcctCd.getValue(), //
                tMsg.dsImptOrdPk.getValue(), null, errBean, NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                tMsg.billToCustCd.getValue(), tMsg.sellToCustCd.getValue(), //
                tMsg.dsImptOrdPk.getValue(), null, errBean, NWZM2255E);
        // Add End 2018/02/26 QC#22967

        // Del Start 2018/02/26 QC#22967
//        NMZC610001PMsg custInfoGetApiMsg = NWXC150001DsCheck.callCustInfoGetApi(//
//                this.glblCmpyCd //
//                , tMsg.billToCustCd.getValue() //
//                // 2017/06/09 S21_NA#18296 Mod Start
//                //, tMsg.shipToCustCd.getValue() //
//                , tMsg.sellToCustCd.getValue()
//                // 2017/06/09 S21_NA#18296 Mod End
//                //, tMsg.sellToCustCd.getValue() //// QC#18235 2017/04/12 Mod
//                , tMsg.shipToCustAcctCd.getValue() //
//                , ONBATCH_TYPE.BATCH);
//        if (S21ApiUtil.isXxMsgId(custInfoGetApiMsg)) {
//            List<String> ml = S21ApiUtil.getXxMsgIdList(custInfoGetApiMsg);
//            for (String msgId : ml) {
//                setValidErrTMsg(errBean, tMsg.dsImptOrdPk.getValue(), null, null, msgId);
//            }
//            return true;
//        }
//        for (int i = 0; i < custInfoGetApiMsg.EligibleCheckList.getValidCount(); i++) {
//            NMZC610001_EligibleCheckListPMsg eligiblePMsg = custInfoGetApiMsg.EligibleCheckList.no(i);
//
//            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
//                    // 2017/06/09 S21_NA#18296 Mod Start
//                    // || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
//                    || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
//                // 2017/06/09 S21_NA#18296 Mod End
//                return true;
//            }
//        }
//        return false;
        // Del End 2018/02/26 QC#22967
    }

    /**
     * callPricingApiForGetPrcList
     * @param tMsg DS_IMPT_ORDTMsg
     * @param modeCd String
     * @param prcCtxTpCd String
     * @param prcCatgOpeFlg String (when check then set to "Y")
     * @return NWZC157001PMsg
     */
    private NWZC157001PMsg callPricingApiForGetPrcList(DS_IMPT_ORDTMsg tMsg, String modeCd, String prcCtxTpCd, String prcCatgOpeFlg) {
        NWZC157001PMsg prcApiPMsg = new NWZC157001PMsg();
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxModeCd, modeCd);
        if (ZYPCommonFunc.hasValue(tMsg.prcBaseDt)) {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, tMsg.prcBaseDt);
        } else {
            ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcBaseDt, this.salesDate);
        }
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcCtxTpCd, prcCtxTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdCatgCd, tMsg.dsOrdCatgCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsOrdTpCd, tMsg.dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.lineBizTpCd //
                , NWXC150001DsCheck.getLineBizTpCd(this.glblCmpyCd, this.salesDate, tMsg.dsOrdTpCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dsAcctNum, tMsg.sellToCustCd);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.csmpNum, tMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.dlrRefNum, tMsg.dlrRefNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.prcContrNum, tMsg.prcContrNum);
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.coaBrCd, getCoaBrCd(tMsg.slsRepTocCd.getValue()));
        ZYPEZDItemValueSetter.setValue(prcApiPMsg.xxPrcCatgOpFlg, prcCatgOpeFlg);

        new NWZC157001().execute(prcApiPMsg, ONBATCH_TYPE.BATCH);
        return prcApiPMsg;
    }

    private String getCoaBrCd(String slsRepCd) {
        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);

        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return "";
        }
        return tMsg.coaBrCd.getValue();
    }

    /**
     * checkIncludePrcCatg
     * @param xxPrcList
     * @param value
     * @return if not include then return true.
     */
    private static boolean checkIncludePrcCatg(NWZC157001_xxPrcListPMsgArray xxPrcList, String value) {
        for (int i = 0; i < xxPrcList.getValidCount(); i++) {
            if (value.equals(xxPrcList.no(i).prcCatgCd.getValue())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Other Check for Config
     * @param pMsg DS CPO Update API Parameter
     * @param pMsg2List Outbound Error Message List
     * @param pMsg3List Inbound error Message List<- 2016/01/13 S21_NA#2726 Add
     */
    private void otherConfigCheck(DS_IMPT_ORDTMsg ordTMsg, List<DS_IMPT_ORD_CONFIGTMsg> listConfigTMsg, DsImptOrdErrBean errBean) {
        for (int i = 0; i < listConfigTMsg.size(); i++) {
            DS_IMPT_ORD_CONFIGTMsg configTMsg = listConfigTMsg.get(i);
            checkConfigBillSellShip(ordTMsg, configTMsg, errBean);
        }
    }

    private void checkConfigBillSellShip(DS_IMPT_ORDTMsg ordTMsg, DS_IMPT_ORD_CONFIGTMsg configTMsg, DsImptOrdErrBean errBean) {
        if (NWXC150001DsCheck.checkBillToRalation(this.glblCmpyCd, configTMsg.billToCustLocCd.getValue(), configTMsg.billToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1452E);
        }
        if (NWXC150001DsCheck.checkShipToRalation(this.glblCmpyCd, configTMsg.shipToCustLocCd.getValue(), configTMsg.shipToCustAcctCd.getValue())) {
            setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1453E);
        }

        // Del Start 2018/02/26 QC#22967
        //NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApi(//
        //        this.glblCmpyCd //
        //        , ordTMsg.billToCustCd.getValue() //
        //        // 2017/06/09 S21_NA#18296 Mod Start
        //        //, ordTMsg.shipToCustCd.getValue() //
        //        , ordTMsg.sellToCustCd.getValue()
        //        // 2017/06/09 S21_NA#18296 Mod End
        //      //, ordTMsg.sellToCustCd.getValue() //// QC#18235 2017/04/12 Mod
        //        , ordTMsg.shipToCustAcctCd.getValue() //
        //        , ONBATCH_TYPE.BATCH);
        //if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
        //    List<String> ml = S21ApiUtil.getXxMsgIdList(rsltPMsg);
        //    for (String msgId : ml) {
        //        setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, msgId);
        //    }
        //    setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1455E);
        //}
        //
        //for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
        //    NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
        //    if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue()) //
        //            // 2017/06/09 S21_NA#18296 Mod Start
        //           // || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnShipToFlg_S.getValue())) {
        //            || !ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnRecipFlg.getValue())) {
        //        // 2017/06/09 S21_NA#18296 Mod End
        //        setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1455E);
        //    }
        //}
        // Del End 2018/02/26 QC#22967

        // Add Start 2018/02/26 QC#22967
        // Check Sold To(Customer Code) <- Ship To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                ordTMsg.soldToCustLocCd.getValue(), ordTMsg.shipToCustAcctCd.getValue(), //
                configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), errBean, NWZM2254E);

        // Check Bill To(Customer Code) <- Sold To(Account Number)
        // relation.
        callCustInfoGetApiForCheckRelation( //
                ordTMsg.billToCustCd.getValue(), ordTMsg.sellToCustCd.getValue(), //
                configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), errBean, NWZM2255E);
        // Add End 2018/02/26 QC#22967

        if (NWXC150001DsCheck.checkConfigIdEssential(this.glblCmpyCd, configTMsg.configTpCd.getValue(), configTMsg.svcConfigMstrPk.getValue())) {
            if (CONFIG_CATG.OUTBOUND.equals(configTMsg.configCatgCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(configTMsg.svcConfigMstrPk)) {
                    setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1466E);
                }
            }
        }
        // Del Start 2016/12/19 M.Ohno S21_NA#16460
//        if (NWXC150001DsCheck.checkConfigShiptoLocation(this.glblCmpyCd, configTMsg.configTpCd.getValue(), configTMsg.svcConfigMstrPk.getValue(), ordTMsg.shipToCustCd.getValue())) {
//            setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1467E);
//        }
        // Del End   2016/12/19 M.Ohno S21_NA#16460
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * @param billToCustCd String
     * @param acctNum String
     * @param dsImptOrdPk BigDecimal
     * @param dsImptOrdConfigPk BigDecimal
     * @param errBean DsImptOrdErrBean
     * @param errMsgId String
     */
    private void callCustInfoGetApiForCheckRelation(String billToCustCd, String acctNum, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, DsImptOrdErrBean errBean, String errMsgId) {
        NMZC610001PMsg rsltPMsg = NWXC150001DsCheck.callCustInfoGetApiBillTo( //
                this.glblCmpyCd, billToCustCd, acctNum, ONBATCH_TYPE.BATCH);

        if (S21ApiUtil.isXxMsgId(rsltPMsg)) {
            List<String> ml = S21ApiUtil.getXxMsgIdList(rsltPMsg);
            for (String msgId : ml) {
                setValidErrTMsg(errBean, dsImptOrdPk, dsImptOrdConfigPk, null, msgId);
            }
            setValidErrTMsg(errBean, dsImptOrdPk, dsImptOrdConfigPk, null, errMsgId);
        }

        for (int elgChkCnt = 0; elgChkCnt < rsltPMsg.EligibleCheckList.getValidCount(); elgChkCnt++) {
            NMZC610001_EligibleCheckListPMsg eligiblePMsg = rsltPMsg.EligibleCheckList.no(elgChkCnt);
            if (!ZYPConstant.FLG_ON_Y.equals(eligiblePMsg.dsAcctRelnBillToFlg_B.getValue())) {
                setValidErrTMsg(errBean, dsImptOrdPk, dsImptOrdConfigPk, null, errMsgId);
            }
        }
    }
    // Add End 2018/02/26 QC#22967

    private void otherCheckDuplicateMachine(DS_IMPT_ORDTMsg ordTMsg, List<DS_IMPT_ORD_CONFIGTMsg> listConfigTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean) {

        List<DS_IMPT_ORD_DTLTMsg> duplMachineList = new ArrayList<DS_IMPT_ORD_DTLTMsg>(0); // 2016/03/04 S21_NA#5000 (#28) Add
        for (int i = 0; i < listConfigTMsg.size(); i++) {
            DS_IMPT_ORD_CONFIGTMsg configTMsg = listConfigTMsg.get(i);

            String configPosnNum = configTMsg.dsOrdPosnNum.getValue();
            duplMachineList.clear();
            if (CONFIG_CATG.OUTBOUND.equals(configTMsg.configCatgCd.getValue())) {
                for (int j = 0; j < listDtlBean.size(); j++) {
                    DS_IMPT_ORD_DTLTMsg dtlTMsg = listDtlBean.get(j);

                    if (configPosnNum.equals(dtlTMsg.dsOrdPosnNum.getValue()) //
                            && NWXC150001DsCheck.getCoaMdseTp(this.glblCmpyCd, dtlTMsg.mdseCd.getValue()).equals(COA_PROJ.MACHINE)) {
                        duplMachineList.add(dtlTMsg);
                    }
                }
                if (duplMachineList.size() > 1) {
                    for (DS_IMPT_ORD_DTLTMsg dtlTMsg : duplMachineList) {
                        setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1921E);
                    }
                }
            }
        }
    }

    private void otherDetailCheck(DS_IMPT_ORDTMsg ordTMsg, List<DS_IMPT_ORD_CONFIGTMsg> listConfigTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean) {

        List<DS_IMPT_ORD_DTLTMsg> dsLineNumByConfig = new ArrayList<DS_IMPT_ORD_DTLTMsg>();
        String prevDsCpoPosnNum = null;
        List<Map<String, String>> componentExistsFlgList = getComponentExistsFlgList();
        for (int i = 0; i < listDtlBean.size(); i++) {
            DS_IMPT_ORD_DTLTMsg dtlTMsg = listDtlBean.get(i);

            if (NWXC150001DsCheck.checkDetailMdseRelation(//
                    this.glblCmpyCd //
                    , dtlTMsg.mdseCd.getValue() //
                    , dtlTMsg.custMdseCd.getValue() //
                    , ordTMsg.sellToCustCd.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1468E);
            }
            if (NWXC150001DsCheck.checkDetailLineCatgRelation(//
                    this.glblCmpyCd //
                    , dtlTMsg.dsOrdLineCatgCd.getValue() //
                    , ordTMsg.dsOrdTpCd.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1469E);
            }
            if (NWXC150001DsCheck.checkRetailWhRelation(//
                    this.glblCmpyCd //
                    , this.salesDate, ordTMsg.dsOrdTpCd.getValue() //
                    , dtlTMsg.rtlWhCd.getValue() //
                    , dtlTMsg.rtlSwhCd.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1470E);
            }

            if (ZYPCommonFunc.hasValue(dtlTMsg.serNum)) {
                // 02/15/2017 QC#16575 UPD START
                // Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(this.glblCmpyCd, dtlTMsg.serNum.getValue(),dtlTMsg.mdseCd.getValue());
                Map<String, Object> map = NWXC150001DsCheck.getSerNumInfo(this.glblCmpyCd, dtlTMsg.serNum.getValue(), dtlTMsg.mdseCd.getValue());
                // 02/15/2017 QC#16575 UPD E N D
                if (map != null) {
                    DS_IMPT_ORD_CONFIGTMsg configTMsg = getConfigTMsg(dtlTMsg, listConfigTMsg);
                    if (checkSerNumForOrderDetail(//
                            this.glblCmpyCd//
                            , dtlTMsg//
                            , configTMsg.configTpCd.getValue() //
                            , (String) map.get(COL_SVC_MACH_USG_STS_CD) //
                            , (String) map.get(COL_SVC_MACH_MSTR_STS_CD) //
                            , (String) map.get(COL_ALLC_FLG) //
                            , componentExistsFlgList)) { //
                        setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1472E);
                    }
                    if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, true, false, true)) {
                        if (NWXC150001DsCheck.checkSerNumRtlWhRelation(//
                                this.glblCmpyCd //
                                , dtlTMsg.rtlWhCd.getValue() //
                                , dtlTMsg.rtlSwhCd.getValue() //
                                , (String) map.get(COL_CUR_LOC_NUM))) {
                            setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1474E);
                        }
                    }
                }
            }
            if (NWXC150001DsCheck.checkSbstRelation(this.glblCmpyCd, dtlTMsg.origMdseCd.getValue(), dtlTMsg.sbstMdseCd.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1475E);
            }

            if (!S21StringUtil.isEquals(dtlTMsg.dsOrdPosnNum.getValue(), prevDsCpoPosnNum)) {
                prevDsCpoPosnNum = dtlTMsg.dsOrdPosnNum.getValue();
                dsLineNumByConfig.clear();
                for (int currentLine = i; currentLine < listDtlBean.size(); currentLine++) {
                    if (!S21StringUtil.isEquals(prevDsCpoPosnNum, listDtlBean.get(currentLine).dsOrdPosnNum.getValue())) {
                        break;
                    }
                    dsLineNumByConfig.add(listDtlBean.get(currentLine));
                }
            }

            MDSETMsg mdseTMsg = getMdseTMsg(dtlTMsg.mdseCd.getValue());
            if (NWXC150001DsCheck.checkDealWh(//
                    this.glblCmpyCd //
                    , dtlTMsg.rtlWhCd.getValue() //
                    , dtlTMsg.dsOrdLineCatgCd.getValue() //
                    , mdseTMsg.invtyCtrlFlg.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1478E);
            }
        }
    }

    private List<Map<String, String>> getComponentExistsFlgList() {
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_ORD_LINE_CTX_TP_CD, "COMPONENT_EXISTS_FLG");

        List<Map<String, String>> mapList = (List<Map<String, String>>) ssmBatchClient.queryObjectList("getComponentExistsFlg", ssmParam);

        return mapList;
    }

    private DS_IMPT_ORD_CONFIGTMsg getConfigTMsg(DS_IMPT_ORD_DTLTMsg dtlTMsg, List<DS_IMPT_ORD_CONFIGTMsg> listConfigTMsg) {
        for (int i = 0; i < listConfigTMsg.size(); i++) {
            DS_IMPT_ORD_CONFIGTMsg configTMsg = listConfigTMsg.get(i);
            if (dtlTMsg.dsOrdPosnNum.getValue().equals(configTMsg.dsOrdPosnNum.getValue()) //
                    && dtlTMsg.dsImptOrdConfigPk.getValue().equals(configTMsg.dsImptOrdConfigPk.getValue())) {
                return configTMsg;
            }
        }
        return new DS_IMPT_ORD_CONFIGTMsg();
    }

    private static boolean checkSerNumForOrderDetail(//
            String glblCmpyCd//
            , DS_IMPT_ORD_DTLTMsg dtlTMsg //
            , String configTp //
            , String svcMachUsgStsCd //
            , String svcMachMstrStsCd //
            , String allocFlg //
            , List<Map<String, String>> componentExistsFlgList) {

        if (NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTp, CONFIG_CATG.OUTBOUND, true, false, true)) {
            if ((SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd)) || SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd)) {
                if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                    return false;
                }
            }
            return true;
        } else {
            boolean rc = true; // true:NG
            if (isMdseAtCust(componentExistsFlgList, dtlTMsg.dsOrdLineCatgCd.getValue())) {
                if (SVC_MACH_USG_STS.AT_CUSTOMER.equals(svcMachUsgStsCd)) {
                    if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsCd) //
                            || SVC_MACH_MSTR_STS.DEALER_SERVICE.equals(svcMachMstrStsCd)) {
                        if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                            rc = false;
                        }
                    }
                }

                if (SVC_MACH_USG_STS.IN_TRANSIT.equals(svcMachUsgStsCd)) {
                    if (SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL.equals(svcMachMstrStsCd)) {
                        if (ZYPConstant.FLG_OFF_N.equals(allocFlg)) {
                            rc = false;
                        }
                    }
                }
                if (rc) {
                    return rc;
                }
            }
            if (isMdseAtWh(componentExistsFlgList, dtlTMsg.dsOrdLineCatgCd.getValue())) {
                if ((SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd)) || (SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd) && SVC_MACH_MSTR_STS.REMOVED.equals(svcMachMstrStsCd))) {
                    if (ZYPConstant.FLG_ON_Y.equals(allocFlg)) {
                        rc = false;
                    }
                }
                if (rc) {
                    return rc;
                }
            }
            return false;
        }
    }

    private static boolean isMdseAtCust(List<Map<String, String>> componentExistsFlgList, String dsOrdLineCatgCd) {
        if (componentExistsFlgList == null || componentExistsFlgList.size() == 0) {
            return false;
        }
        for (Map<String, String> componentExistsFlg : componentExistsFlgList) {
            if (ZYPCommonFunc.hasValue(componentExistsFlg.get(COL_DS_ORD_LINE_CATG_CD)) && dsOrdLineCatgCd.equals(componentExistsFlg.get(COL_DS_ORD_LINE_CATG_CD))) {
                if (ZYPConstant.FLG_ON_Y.equals(componentExistsFlg.get(COL_FIRST_BIZ_CTX_ATTRB_TXT))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private static boolean isMdseAtWh(List<Map<String, String>> componentExistsFlgList, String dsOrdLineCatgCd) {
        if (componentExistsFlgList == null || componentExistsFlgList.size() == 0) {
            return false;
        }
        for (Map<String, String> componentExistsFlg : componentExistsFlgList) {
            if (ZYPCommonFunc.hasValue(componentExistsFlg.get(COL_DS_ORD_LINE_CATG_CD)) && dsOrdLineCatgCd.equals(componentExistsFlg.get(COL_DS_ORD_LINE_CATG_CD))) {
                if (ZYPConstant.FLG_ON_Y.equals(componentExistsFlg.get(COL_SCD_BIZ_CTX_ATTRB_TXT))) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    private void otherSvcExchCheck(DS_IMPT_ORDTMsg ordTMsg, List<DS_IMPT_ORD_CONFIGTMsg> listConfigTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean) {
        //QC#14021
        //if (!NWXC150001DsCheck.isSvcExch(this.glblCmpyCd, ordTMsg.dsOrdCatgCd.getValue())) {
        if (!NWXC150001DsCheck.isSvcExch(this.glblCmpyCd, ordTMsg.dsOrdCatgCd.getValue(), ordTMsg.dsOrdTpCd.getValue())) {
            return;
        }

        List<DS_IMPT_ORD_CONFIGTMsg> outConfigPMsgList = new ArrayList<DS_IMPT_ORD_CONFIGTMsg>();
        for (int i = 0; i < listConfigTMsg.size(); i++) {
            DS_IMPT_ORD_CONFIGTMsg configTMsg = listConfigTMsg.get(i);
            if (CONFIG_CATG.OUTBOUND.equals(configTMsg.configCatgCd.getValue()) //
                    // Out bound N Y N
                    && NWXC150001DsCheck.matchConfigTp(glblCmpyCd, configTMsg.configTpCd.getValue(), CONFIG_CATG.OUTBOUND, false, true, false)) {
                outConfigPMsgList.add(configTMsg);
            } else {
                setValidErrTMsg(errBean, configTMsg.dsImptOrdPk.getValue(), configTMsg.dsImptOrdConfigPk.getValue(), null, NWZM1527E);
                return;
            }
        }
    }

    private void otherQtyCheck(DS_IMPT_ORDTMsg ordTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean) {

        for (int i = 0; i < listDtlBean.size(); i++) {
            DS_IMPT_ORD_DTLTMsg dtlTMsg = listDtlBean.get(i);
            MDSETMsg mdseTMsg = getMdseTMsg(dtlTMsg.mdseCd.getValue());

            //QC#14021
            //if (NWXC150001DsCheck.chekcQtyForSerializedItem(this.glblCmpyCd, ordTMsg.dsOrdCatgCd.getValue(), dtlTMsg.mdseCd.getValue(), dtlTMsg.ordQty.getValueInt())) {
            if (NWXC150001DsCheck.chekcQtyForSerializedItem(this.glblCmpyCd, ordTMsg.dsOrdCatgCd.getValue(), ordTMsg.dsOrdTpCd.getValue(), ordTMsg.dsOrdRsnCd.getValue(), dtlTMsg.mdseCd.getValue(), dtlTMsg.ordQty.getValueInt())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1917E);
            }
            if (NWXC150001DsCheck.checkSerialQty(dtlTMsg.serNum.getValue(), dtlTMsg.ordQty.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1484E);
            }
            if (NWXC150001DsCheck.checkLicenseItemQty(mdseTMsg.thirdPtyVndDropFlg.getValue(), dtlTMsg.ordQty.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1485E);
            }
            if (NWXC150001DsCheck.checkMinusQty(dtlTMsg.crRebilCd.getValue(), dtlTMsg.ordQty.getValue())) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1486E);
            }
            // 2019/01/08 QC#29241 Mod Start
            if (NWXC150001DsCheck.checkOrdQtyVldFlg(this.glblCmpyCd, ordTMsg.dsOrdTpCd.getValue(), dtlTMsg.dsOrdLineCatgCd.getValue(), this.salesDate)) {
                if (NWXC150001DsCheck.checkMinOrdQty(mdseTMsg.cpoMinOrdQty.getValue(), dtlTMsg.ordQty.getValue())) {
                    setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1488E);
                }
                if (NWXC150001DsCheck.checkMaxOrdQty(mdseTMsg.cpoMaxOrdQty.getValue(), dtlTMsg.ordQty.getValue())) {
                    setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1489E);
                }
                if (NWXC150001DsCheck.checkIncrOrdQty(mdseTMsg.cpoIncrOrdQty.getValue(), dtlTMsg.ordQty.getValue())) {
                    setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1492E);
                }
            }
            // 2019/01/08 QC#29241 Mod End
        }
    }

    private void otherEasyPackCheck(DS_IMPT_ORDTMsg ordTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean) {
        if (NWXC150001DsCheck.isEasyPack(this.glblCmpyCd, ordTMsg.dsOrdCatgCd.getValue(), ordTMsg.dsOrdTpCd.getValue())) {
            for (int i = 0; i < listDtlBean.size(); i++) {
                DS_IMPT_ORD_DTLTMsg dtlTMsg = listDtlBean.get(i);
                MDSETMsg tMsg = new MDSETMsg();
                ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, dtlTMsg.mdseCd.getValue());
                tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

                if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.easyPackTpCd)) {
                    setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1530E);
                    continue;
                }

                if (!NWXC150001DsCheck.isSplyPgmContr(this.glblCmpyCd, this.salesDate, ordTMsg.billToCustAcctCd.getValue(), ordTMsg.billToCustCd.getValue())) {
                    setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWZM1532E);
                }
            }
        }
    }

    private void otherMdseStsCheck(DS_IMPT_ORDTMsg ordTMsg, List<DsImptOrdDtlBean> listDtlBean, DsImptOrdErrBean errBean, Map<Map<String, String>, Map<String, String>> mdseInfoCacheMap) {
        Map<Map<String, String>, Map<String, String>> ordProcTpInfoCacheMap = new HashMap<Map<String, String>, Map<String, String>>();
        Map<Map<String, String>, BigDecimal> costPctInfoCacheMap = new HashMap<Map<String, String>, BigDecimal>();
        for (int i = 0; i < listDtlBean.size(); i++) {
            DS_IMPT_ORD_DTLTMsg dtlTMsg = listDtlBean.get(i);
            Map<String, String> mdseInfoMap = getMdseItemStsInfo(dtlTMsg.mdseCd.getValue(), mdseInfoCacheMap);
            Map<String, String> ordProcTpInfoMap = getOrdProcTpInfo(//
                    ordTMsg.dsOrdTpCd.getValue() //
                    , dtlTMsg.dsOrdLineCatgCd.getValue() //
                    , this.salesDate //
                    , ordProcTpInfoCacheMap);

            if (ordProcTpInfoMap == null || mdseInfoMap == null) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWAM0037E);
                continue;
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get(COL_ITRL_ORD_PROC_FLG))) {
                continue;
            }
            // 2019/01/08 QC#29753 Add Start
            if(ZYPConstant.FLG_OFF_N.equals((String) mdseInfoMap.get(COL_PRCH_AVAL_FLG))
                    && NWXC150001DsCheck.checkOrdLineSrcCatg(this.glblCmpyCd, dtlTMsg.ordLineSrcCd.getValue(), ORD_LINE_SRC_CATG.EXTERNAL)) {
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWAM0965E);
            }
            // 2019/01/08 QC#29753 Add End
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get(COL_CUST_ORD_PROC_FLG))) {
                // Dummy WH
                if (!ZYPCommonFunc.hasValue(dtlTMsg.rtlSwhCd)) {
                    continue;
                }
                BigDecimal costPct = getCostPctInfo(//
                        dtlTMsg.rtlSwhCd.getValue() //
                        , this.salesDate //
                        , costPctInfoCacheMap);

                if (PCT_100.compareTo(costPct) == 0) {
                    if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get(COL_CUST_ORD_ENTRY_AVAL_FLG))) {
                        continue;
                    }
                } else {
                    if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get(COL_USED_ONLY_AVAL_FLG))) {
                        continue;
                    }
                }
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWAM0037E);
            }
            if (ZYPConstant.FLG_ON_Y.equals((String) ordProcTpInfoMap.get(COL_WS_ORD_PROC_FLG))) {
                if (ZYPConstant.FLG_ON_Y.equals((String) mdseInfoMap.get(COL_WS_ORD_ENTRY_AVAL_FLG))) {
                    continue;
                }
                setValidErrTMsg(errBean, dtlTMsg.dsImptOrdPk.getValue(), dtlTMsg.dsImptOrdConfigPk.getValue(), dtlTMsg.dsImptOrdDtlPk.getValue(), NWAM0037E);
            }
        }
    }

    private Map<String, String> getMdseItemStsInfo(String mdseCd, Map<Map<String, String>, Map<String, String>> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_MDSE_CD, mdseCd + "%");

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getMdseItemStsInfo", ssmParam);
        cacheMap.put(ssmParam, map);

        return map;
    }

    private Map<String, String> getOrdProcTpInfo(//
            String dsOrdTpCd //
            , String dsOrdLineCatgCd //
            , String slsDt //
            , Map<Map<String, String>, Map<String, String>> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(COL_GLBL_CMPY_CD, this.glblCmpyCd);
        ssmParam.put(COL_DS_ORD_TP_CD, dsOrdTpCd);
        ssmParam.put(COL_DS_ORD_LINE_CATG_CD, dsOrdLineCatgCd);
        ssmParam.put(COL_SALES_DATE, slsDt);

        if (cacheMap != null && cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        Map<String, String> map = (Map<String, String>) ssmBatchClient.queryObject("getOrdProcTpInfo", ssmParam);
        if (cacheMap != null) {
            cacheMap.put(ssmParam, map);
        }

        return map;
    }

    private BigDecimal getCostPctInfo(String rtlSwhCd, String slsDt, Map<Map<String, String>, BigDecimal> cacheMap) {

        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(COL_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(COL_RTL_SWH_CD, rtlSwhCd);
        ssmParam.put(COL_SALES_DATE, slsDt);

        if (cacheMap.containsKey(ssmParam)) {
            return cacheMap.get(ssmParam);
        }
        BigDecimal costPct = (BigDecimal) ssmBatchClient.queryObject("getCostPctInfo", ssmParam);
        cacheMap.put(ssmParam, costPct);

        return costPct;
    }

    private MDSETMsg getMdseTMsg(String mdseCd) {
        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(this.glblCmpyCd, mdseCd);

        if (mdseTMsg == null) {
            mdseTMsg = new MDSETMsg();

        }
        return mdseTMsg;
    }

    private void setValidErrTMsg(DsImptOrdErrBean errBean, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, BigDecimal dsImptOrdDtlPk, String errMsdId) {

        DS_IMPT_ORD_ERRTMsg errTMsg = new DS_IMPT_ORD_ERRTMsg();

        BigDecimal dsImptOrdErrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ERR_SQ);

        ZYPEZDItemValueSetter.setValue(errTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrPk, dsImptOrdErrPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrMsgId, errMsdId);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrTxt, S21MessageFunc.clspGetMessage(errMsdId));
        errBean.getDsImptOrdErrList().add(errTMsg);
    }

    private void setValidErrTMsg(DsImptOrdErrBean errBean, BigDecimal dsImptOrdPk, BigDecimal dsImptOrdConfigPk, BigDecimal dsImptOrdDtlPk, String errMsdId, String errMsgTxt) {

        DS_IMPT_ORD_ERRTMsg errTMsg = new DS_IMPT_ORD_ERRTMsg();

        BigDecimal dsImptOrdErrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_ERR_SQ);

        ZYPEZDItemValueSetter.setValue(errTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrPk, dsImptOrdErrPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdPk, dsImptOrdPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdConfigPk, dsImptOrdConfigPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdDtlPk, dsImptOrdDtlPk);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrMsgId, errMsdId);
        ZYPEZDItemValueSetter.setValue(errTMsg.dsImptOrdErrTxt, errMsgTxt);
        errBean.getDsImptOrdErrList().add(errTMsg);
    }

    // ///////////////////////////////////////////////////////////////
    // LOGGING
    // ///////////////////////////////////////////////////////////////
    /**
     * writeStartLogLn
     * @param methodNm
     */
    private static void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private static void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    /**
     * writeErrLog
     * @param msgId
     */
    private static void writeErrLog(String msgId) {
        writeLogLn(S21MessageFunc.clspGetMessage(msgId));
    }

    /**
     * writeErrIdLog
     * @param msgId
     */
    private static void writeErrLog(String msgId, String[] list) {
        writeLogLn(S21MessageFunc.clspGetMessage(msgId, list));
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", PROGRAM_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    // 2017/03/14 S21_NA#16855 Add Start
    // Mod Start 2017/12/14 QC#19804(Sol#349)
    ///**
    // * <pre>
    // * Get Territory Group Type Code From DS Order Type Code
    // * @param String dsOrdTpCd
    // * </pre>
    // */
    //private static String getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
    /**
     * <pre>
     * Get Territory Group Type Code From DS Order Type Code
     * @param String dsOrdTpCd
     * </pre>
     */
    private static List<String> getTrtyGrpTpCdFromDsOrdTpCd(String dsOrdTpCd, String glblCmpyCd) {
        // Mod End 2017/12/14 QC#19804(Sol#349)

        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }

        DS_ORD_TP_PROC_DFNTMsg dsOrdTpProcDfnTMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpProcDfnTMsg.dsOrdTpCd, dsOrdTpCd);

        dsOrdTpProcDfnTMsg = (DS_ORD_TP_PROC_DFNTMsg) S21FastTBLAccessor.findByKey(dsOrdTpProcDfnTMsg);
        if (dsOrdTpProcDfnTMsg != null) {
            // Mod Start 2017/12/14 QC#19804(Sol#349)
            //return dsOrdTpProcDfnTMsg.trtyGrpTpCd.getValue();
            String trtyGrpTpTxt = dsOrdTpProcDfnTMsg.trtyGrpTpTxt.getValue();

            List<String> trtyGrpTpCdList =  null;
            if (ZYPCommonFunc.hasValue(trtyGrpTpTxt)){
                trtyGrpTpCdList = Arrays.asList(trtyGrpTpTxt.split(","));
            }

            return trtyGrpTpCdList;
            // Mod End 2017/12/14 QC#19804(Sol#349)
        }

        return null;
    }
    // 2017/03/14 S21_NA#16855 Add End

    // 2018/12/12 QC#29315 Add Start
    /**
     * @param glblCmpyCd String
     * @param shipToCustCd String
     * @return String
     */
    public String getLocNum(String glblCmpyCd, String shipToCustCd) {

        String locNum = "";
        
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("shipToCustCd", shipToCustCd);

        Map<String, Object> resultMap = (Map<String, Object>) ssmBatchClient.queryObject("getLocNumFromShipTo", ssmParam);

        if (resultMap != null && !resultMap.isEmpty()) {
            locNum = (String) resultMap.get("LOC_NUM");
        }
        return locNum;

    }
    // 2018/12/12 QC#29315 Add End

}

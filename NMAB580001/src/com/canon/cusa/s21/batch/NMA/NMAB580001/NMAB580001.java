/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB580001;

import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_ARCS_PAY_GRP_LKUP_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_ARCS_VND_TP_LKUP_TXT;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_CTRY_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_CTY_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_EFF_FROM_DT;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_FIRST_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_FRTH_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_LOC_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_PRNT_VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_PRNT_VND_NM;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_SCD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_ST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_TAX_PAYER_ID;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_THIRD_LINE_ADDR;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_VND_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.COL_VND_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.LEN_MAX_POST_CD;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_ID_ZZBM0009I;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_ID_ZZM9000E;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_ID_ZZZM9012E;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_ID_ZZZM9013E;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_STR_COMP_CODE;
import static com.canon.cusa.s21.batch.NMA.NMAB580001.constant.NMAB580001Constant.MSG_STR_INTERFACE_ID;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.VNDTMsg;
import business.db.NMAI1120_01TMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>    
 * Send Supplier Info TO ARCS Batch
 *  
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/28/2016   CITS           T.Wada           Create
 * 09/02/2016   CITS           T.Gotoda         Update          QC#11907
 * 2021/01/07   CITS           R.Kurahashi      Update          QC#57955
 * 
 *</pre>
 */
public class NMAB580001 extends S21BatchMain {

    // -- Input parameters ----------------------
    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    /** Sales Date */
    private String slsDt;

    // -- Count of processing -------------------
    /** The number of cases : Select */
    private int selectCount;

    /** The number of cases : Insert */
    private int insertCount;

    /** The number of case : Skip */
    private int skipCount;

    // -- Other Internal Variable ---------------
    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** SSM Batch Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Termination code */
    private TERM_CD termCd;

    /** Transaction table accessor */
    private S21TransactionTableAccessor trxAccess;

    @Override
    protected void initRoutine() {

        // Initialization of SQL Accessor
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());

        // Initialization of variable
        selectCount = 0;
        termCd = TERM_CD.NORMAL_END;

        // Initialization of S21UserProfileService
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        // The Transaction ID is obtained
        trxAccess = new S21TransactionTableAccessor();

        // Check on input parameter
        checkParameter();

    }

    @Override
    protected void mainRoutine() {
        sendSupplierInfoToARCS();
    }

    @Override
    protected void termRoutine() {

        String[] tmp = null;

        // The number of cases : Insert is output
        tmp = new String[] {interfaceId, "Insert", Integer.toString(insertCount) };
        S21InfoLogOutput.println(MSG_ID_ZZBM0009I, tmp);

        // Setting of termination code
        setTermState(termCd, selectCount, skipCount, insertCount);

    }

    /**
     * <pre>
     * Check processing of input parameters.
     * </pre>
     */
    private void checkParameter() {

        // Global Company Code
        glblCmpyCd = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_COMP_CODE });
        }

        // Interface ID
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(MSG_ID_ZZM9000E, new String[] {MSG_STR_INTERFACE_ID });
        }

        // Sales Date
        slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

    }

    /**
     * <pre>
     * sendSupplierInfoToARCS
     * </pre>
     * @param key SSM key.
     * @param params SSM parameter.
     */
    private void sendSupplierInfoToARCS() {

        S21SsmExecutionParameter execParam = null;
        // Set the fetch size.
        execParam = new S21SsmExecutionParameter();

        // Get Transaction ID)
        BigDecimal trxId = trxAccess.getNextTransactionId();

        // Create Interface data.
        createInterface(trxId, execParam);

    }

    /**
     * <pre>
     * createInterfaceData
     * </pre>
     * @param trxId TransactionId.
     * @param execParam SSM parameter.
     */
    private void createInterface(BigDecimal trxId, S21SsmExecutionParameter execParam) {

        PreparedStatement prdStmt = null;
        ResultSet rs = null;
        try {
            Map<String, String> param = new HashMap<String, String>();
            param.put("GLBL_CMPY_CD", glblCmpyCd);
            param.put("PVT_SEND_ARCS_FLG", ZYPConstant.FLG_ON_Y);
            param.put("DV_SEND_ARCS_FLG", ZYPConstant.FLG_OFF_N);
            prdStmt = ssmLLClient.createPreparedStatement("getSupplierInfo", param, execParam);
            rs = prdStmt.executeQuery();

            int seqNo = 0;
            BigDecimal wkVndPK = null;
            while (rs.next()) {
                selectCount++;
                seqNo++;

                wkVndPK = rs.getBigDecimal(COL_VND_PK);

                // create I/F Data
                createIFData(rs, trxId, seqNo);
                insertCount++;
                
                // update SEND_ARCS_FLG
                updateSendArcsFlg(glblCmpyCd, wkVndPK, ZYPConstant.FLG_ON_Y);

            }
            // Create Transaction table.
            if (seqNo > 0) {
                trxAccess.createIntegrationRecordForBatch(interfaceId, trxId);
            }
            commit();

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prdStmt, rs);
        }
    }

    /**
     * createIFData
     * @param rs
     * @param trxId
     * @param seqNo
     * @return
     * @throws SQLException
     */
    private int createIFData(ResultSet rs, BigDecimal trxId, int seqNo) throws SQLException {
        NMAI1120_01TMsg nmai112001TMsg = new NMAI1120_01TMsg();
        // INTERFACE_ID
        nmai112001TMsg.interfaceId.setValue(this.interfaceId);
        // TRANSACTION_ID
        nmai112001TMsg.transactionId.setValue(trxId);
        // SEGMENT_ID
        nmai112001TMsg.segmentId.setValue(1);
        // UNIT_ID
        nmai112001TMsg.unitId.setValue(1);
        // SEQ_NUMBER
        nmai112001TMsg.seqNumber.setValue(seqNo);

        // FILL_8_TXT = NULL

        // PRNT_VND_CD
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.prntVndCd, rs.getString(COL_PRNT_VND_CD));

        // PRNT_VND_NM
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.prntVndNm, rs.getString(COL_PRNT_VND_NM));

        // ARCS_VND_TP_LKUP_TXT
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.arcsVndTpLkupTxt, rs.getString(COL_ARCS_VND_TP_LKUP_TXT));

        // EFF_FROM_DT
        if (ZYPCommonFunc.hasValue(rs.getString(COL_EFF_FROM_DT))) {
            ZYPEZDItemValueSetter.setValue(nmai112001TMsg.effFromDt, rs.getString(COL_EFF_FROM_DT));
        } else {
            ZYPEZDItemValueSetter.setValue(nmai112001TMsg.effFromDt, this.slsDt);
        }

        // FILL_6_TXT = NULL

        // CTRY_CD
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.ctryCd, rs.getString(COL_CTRY_CD));

        // VND_ADDR_ALL_TXT_01
        String addr1 = "";
        String addr2 = "";
        String addr3 = "";
        String addr4 = "";

        if (ZYPCommonFunc.hasValue(rs.getString(COL_FIRST_LINE_ADDR))) {
            addr1 = repNullToStr(rs.getString(COL_FIRST_LINE_ADDR));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_SCD_LINE_ADDR))) {
            addr2 = repNullToStr(rs.getString(COL_SCD_LINE_ADDR));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_THIRD_LINE_ADDR))) {
            addr3 = repNullToStr(rs.getString(COL_THIRD_LINE_ADDR));
        }
        if (ZYPCommonFunc.hasValue(rs.getString(COL_FRTH_LINE_ADDR))) {
            addr4 = repNullToStr(rs.getString(COL_FRTH_LINE_ADDR));
        }
        // START 2021/01/07 R.Kurahashi [QC#57955, MOD]
        //ZYPEZDItemValueSetter.setValue(nmai112001TMsg.vndAddrAllTxt_01, addr1 + addr2 + addr3 + addr4);
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.vndAddrAllTxt_01, addr1 + addr2);
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.vndAddrAllTxt_02, addr3 + addr4);
        // END 2021/01/07 R.Kurahashi [QC#57955, MOD]

        // VND_ADDR_ALL_TXT_02 = NULL
        // VND_ADDR_ALL_TXT_03 = NULL

        // CTY_ADDR
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.ctyAddr, rs.getString(COL_CTY_ADDR));
        // ST_CD
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.stCd, rs.getString(COL_ST_CD));

        // POST_CD_TXT
        if (ZYPCommonFunc.hasValue(rs.getString(COL_POST_CD))) {
            String postCd = null;
            if (rs.getString(COL_POST_CD).length() > LEN_MAX_POST_CD) {
                postCd = rs.getString(COL_POST_CD).substring(0, LEN_MAX_POST_CD);
            } else {
                postCd = rs.getString(COL_POST_CD);
            }
            ZYPEZDItemValueSetter.setValue(nmai112001TMsg.postCdTxt, postCd);
        }

        // ALT_VND_ADDR_ALL_TXT = NULL

        // TAX_PAYER_ID
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.taxPayerId, rs.getString(COL_TAX_PAYER_ID));

        // ARCS_SPLY_SITE_CD
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.arcsSplySiteCd, rs.getString(COL_LOC_NM));

        // ARCS_PAY_GRP_LKUP_TXT
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.arcsPayGrpLkupTxt, rs.getString(COL_ARCS_PAY_GRP_LKUP_TXT));

        // ARCS_SPLY_SITE_ATTRB_TXT_05
        ZYPEZDItemValueSetter.setValue(nmai112001TMsg.arcsSplySiteAttrbTxt_05, rs.getString(COL_VND_CD));

        EZDTBLAccessor.insert(nmai112001TMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(nmai112001TMsg.getReturnCode())) {
            S21InfoLogOutput.println("NMAI1120_01TMsg : " + nmai112001TMsg.toString());
            throw new S21AbendException(MSG_ID_ZZZM9012E, new String[] {nmai112001TMsg.getReturnCode()});
        }
        return 1;
    }

    /**
     * updateSendArcsFlg
     * @param gCmpyCd
     * @param vndPk
     * @param sendArcsFlg
     */
    private void updateSendArcsFlg(String gCmpyCd, BigDecimal vndPk, String sendArcsFlg) {
        VNDTMsg updateTblData = new VNDTMsg();
        ZYPEZDItemValueSetter.setValue(updateTblData.glblCmpyCd, gCmpyCd);
        ZYPEZDItemValueSetter.setValue(updateTblData.vndPk, vndPk);

        updateTblData = (VNDTMsg) EZDTBLAccessor.findByKeyForUpdate(updateTblData);
        updateTblData.sendArcsFlg.setValue(sendArcsFlg);

        EZDTBLAccessor.update(updateTblData);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(updateTblData.getReturnCode())) {
            S21InfoLogOutput.println("VNDTMsg : " + updateTblData.toString());
            throw new S21AbendException(MSG_ID_ZZZM9013E, new String[] {updateTblData.getReturnCode()});
        }
    }

    /**
     * repNullToStr
     * @param chkStr
     * @return
     */
    private String repNullToStr(String chkStr) {
        if (chkStr == null) {
            return "";
        } else {
            return chkStr;
        }
    }


    /**
     * This main method is called from batch shell
     * @param args arguments
     */
    public static void main(String[] args) {
        // Initialization of S21BatchMain
        new NMAB580001().executeBatch(NMAB580001.class.getSimpleName());
    }

}

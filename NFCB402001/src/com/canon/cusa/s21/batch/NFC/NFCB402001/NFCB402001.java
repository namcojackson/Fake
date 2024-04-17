/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NFC.NFCB402001;

import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.COMP_PROC_STS_COMPLETE;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.CONST_PAST_COMP_DAYS_AOT;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.CRLF;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.DS_ORD_CATG_MISC;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.DS_ORD_TP_LFS_MISC;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.MAX_COMMIT_NUMBER;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.MAX_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.NFC;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.NFCM0531E;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.PERCENT;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.SQL_STATEMENT_GET_COMP_INFO;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.SQL_STATEMENT_GET_COMP_INFO_RMA;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.SUBSTR_DT_LNGTH;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD;
import static com.canon.cusa.s21.batch.NFC.NFCB402001.constant.NFCB402001Constant.ZZMM0007E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import business.db.IMG_SPLY_COMP_ORDTMsg;
import business.db.INV_LINETMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACCT_TEAM_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRTY_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Copensation IF(IS)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/13   Hitachi         Y.Zhang         Create          N/A
 * 2018/02/15   Fujitsu         S.Ohki          Update          QC#23230
 * 2018/03/09   Fujitsu         S.Ohki          Update          QC#24659
 * 2018/05/25   Fujitsu         Hd.Sugawara     Update          QC#21841(Sol#495)
 * 2018/08/07   Fujitsu         H.Kumagai       Update          QC#26406
 * 2018/09/11   Fujitsu         T.Noguchi       Update          QC#28118,28123
 * 2018/09/25   Fujitsu         T.Noguchi       Update          QC#28231
 * 2018/10/17   Fujitsu         Hd.Sugawara     Update          QC#28842
 * 2018/10/30   Fujitsu         T.Noguchi       Update          QC#29033
 * 2018/11/09   Fujitsu         Mz.Takahashi    Update          QC#29131
 * 2019/06/03   Fujitsu         M.Ishii         Update          QC#50637
 * 2019/09/05   Fujitsu         Hd.Sugawara     Update          QC#51704
 * 2019/09/13   Fujitsu         Hd.Sugawara     Update          QC#52943
 * 2019/11/01   Fujitsu         C.Hara          Update          QC#53905
 * 2019/12/12   Fujitsu         M.Ohno          Update          QC#54880
 * 2020/04/23   Fujitsu         M.Ohno          Update          QC#56638
 * </pre>
 */

public class NFCB402001 extends S21BatchMain {

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Normal Count */
    private int normalCount = 0;

    /** Error Count */
    private int errorCount = 0;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;

    /** Commit Number */
    private int commitNumber = 0;

    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Send Compensation Complete Code */
    private String sendCompCpltCd = null;

    /** Send Compensation PPS Business Type Code */
    private String compIsBizTpCd = null;
    
    // 2020/04/23 QC#56638 Add Start
    /** Mail Detail List */
    private List<String> dtlList = new ArrayList<String>();

    @Override
    protected void initRoutine() {
        // Get Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {"Global Company Code" });
        }

        // Get Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, this.getClass().getSimpleName());
        // Mod Start 2018/10/17 QC#28842
        //if (!hasValue(this.glblCmpyCd)) {
        if (!hasValue(this.slsDt)) {
            // Mod End 2018/10/17 QC#28842
            throw new S21AbendException(NFCM0531E, new String[] {"Sales Date" });
        }

        // Get Commit Number
        this.commitNumber = getCommitCount();
        if (this.commitNumber <= 0 || this.commitNumber >= MAX_COMMIT_NUMBER) {
            this.commitNumber = MAX_COMMIT_NUMBER;
        }

        // Get Var Char Const (Send Compensation Complete Code)
        this.sendCompCpltCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.sendCompCpltCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_SEND_COMP_CPLT_CD });
        }

        // Get Var Char Const (Compensation PPS Business Type Code)
        this.compIsBizTpCd = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD, this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.compIsBizTpCd)) {
            throw new S21AbendException(NFCM0531E, new String[] {VAR_CHAR_CONST_KEY_COMP_IS_BIZ_TP_CD });
        }

        // Initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {

        // 2018/09/25 QC#28231 Mod Start
        //createIsCompOrd();
        createIsCompOrd(SQL_STATEMENT_GET_COMP_INFO);
        createIsCompOrd(SQL_STATEMENT_GET_COMP_INFO_RMA);
        // 2018/09/25 QC#28231 Mod End
        // 2020/04/23 QC#56638 Add Start
        if (this.dtlList.size() > 0) {
            sendMail();
        }
        // 2020/04/23 QC#56638 Add End

        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }
    }

    @Override
    protected void termRoutine() {
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NFCB402001().executeBatch(NFCB402001.class.getSimpleName());

    }

    // 2018/09/25 QC#28231 Mod Start
    //private void createIsCompOrd() {
    private void createIsCompOrd(String statementId) {
    // 2018/09/25 QC#28231 Mod End

        List<IMG_SPLY_COMP_ORDTMsg> isCompOrdTMsgList = new ArrayList<IMG_SPLY_COMP_ORDTMsg>();
        List<INV_LINETMsg> invLineTMsgList = new ArrayList<INV_LINETMsg>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Map<String, Object> preInvMap = new HashMap<String, Object>();
        Map<String, Object> errInvMap = new HashMap<String, Object>();

        try {
            // get Compensation info
            // 2018/09/25 QC#28231 Mod Start
            //stmt = this.ssmLLClient.createPreparedStatement("getCompInfo", getParamMap(), getExecParam());
            stmt = this.ssmLLClient.createPreparedStatement(statementId, getParamMap(), getExecParam());
            // 2018/09/25 QC#28231 Mod End
            rs = stmt.executeQuery();

            while (rs.next()) {

                // 2020/04/23 QC#56638 Del Start
//                IMG_SPLY_COMP_ORDTMsg isCompOrdTMsg = setIsCompOrd(rs, null);
//                isCompOrdTMsgList.add(isCompOrdTMsg);
                // 2020/04/23 QC#56638 Del End

                // Add Start 2019/09/05 QC#51704
                String ordBookDt = (String) rs.getString("ORD_BOOK_DT");
                // Add End 2019/09/05 QC#51704
                // 2018/02/15 S21_NA#23230 Add Start
                // 2019/12/12 QC#54880 Mod Start
//                String rootOrgCd = getRootOrgCd(rs.getString("SLS_REP_PSN_CD"));
                // 2020/04/23 QC#56638 Mod Start
//                String rootOrgCd = getRootOrgCd(rs.getString("SLS_REP_PSN_CD"), ordBookDt);
                String rootOrgCd = getRootOrgCd(rs.getString("SLS_REP_PSN_CD"), (String) rs.getString("SOLD_TO_CUST_LOC_CD"));
                // 2020/04/23 QC#56638 Mod End
                // 2019/12/12 QC#54880 Mod End
                // 2018/03/09 S21_NA#24659 Add Start
                // 2018/09/25 QC#28231 Mod Start
                //if (!ZYPCommonFunc.hasValue(rootOrgCd)) {
                //	continue;
                //}
                if (ZYPCommonFunc.hasValue(rootOrgCd)) {
                // 2018/09/25 QC#28231 Mod End
                // 2018/03/09 S21_NA#24659 Add End
                    // Mod Start 2019/09/05 QC#51704
                    //List<Map<String, Object>> orgCdList = getOrgCdList(rootOrgCd);
                    List<Map<String, Object>> orgCdList = getOrgCdList(rootOrgCd, ordBookDt);
                    // Mod End 2019/09/05 QC#51704
                    orgloop : for (Map<String, Object> orgCdMap : orgCdList) {
                        String orgCd = (String) orgCdMap.get("ORG_CD");
                        // Mod Start 2019/09/05 QC#51704
                        //List<Map<String, Object>> psnCdList = getPsnCdList(orgCd);
                        List<Map<String, Object>> psnCdList = getPsnCdList(orgCd, ordBookDt);
                        // Mod End 2019/09/05 QC#51704
                        for (Map<String, Object> psnCdMap : psnCdList) {
                            String psnCd = (String) psnCdMap.get("PSN_CD");
                            String trtyGrpTpCd = (String) psnCdMap.get("TRTY_GRP_TP_CD");

                            if (!ZYPCommonFunc.hasValue(trtyGrpTpCd) || !TRTY_GRP_TP.IS.equals(trtyGrpTpCd)) {
                                break orgloop;
                            }

                            if (ZYPCommonFunc.hasValue(psnCd)) {
                                IMG_SPLY_COMP_ORDTMsg compOrdTMsg = setIsCompOrd(rs, psnCd);
                                // 2019/11/01 QC#53905 Add Start
                                boolean existCheck = true;
                                for (IMG_SPLY_COMP_ORDTMsg listCompOrdTMsg : isCompOrdTMsgList) {
                                    if (listCompOrdTMsg.invNum.getValue().equals(compOrdTMsg.invNum.getValue()) && listCompOrdTMsg.invBolLineNum.getValue().equals(compOrdTMsg.invBolLineNum.getValue())
                                            && listCompOrdTMsg.invLineNum.getValue().equals(compOrdTMsg.invLineNum.getValue()) && listCompOrdTMsg.invLineSubNum.getValue().equals(compOrdTMsg.invLineSubNum.getValue())
                                            && listCompOrdTMsg.slsRepPsnCd.getValue().equals(compOrdTMsg.slsRepPsnCd.getValue())) {
                                        existCheck = false;
                                        break;
                                    }
                                }
                                if (existCheck) {
                                // 2019/11/01 QC#53905 Add End
                                    isCompOrdTMsgList.add(compOrdTMsg);
                                }
                            }
                        }
                    }
                // 2018/09/25 QC#28231 Mod Start
                // 2020/04/23 QC#56638 Add Start
                } else {
                    StringBuilder msg = new StringBuilder();
                    msg.append(String.format("%-12s", (String) rs.getString("INV_NUM")));
                    msg.append(String.format("%-12s", (String) rs.getString("CPO_ORD_NUM")));
                    msg.append((String) rs.getString("SOLD_TO_CUST_LOC_CD"));
                    dtlList.add(msg.toString());
                    IMG_SPLY_COMP_ORDTMsg isCompOrdTMsg = setIsCompOrd(rs, null);
                    isCompOrdTMsgList.add(isCompOrdTMsg);
                }
                // 2018/09/25 QC#28231 Mod End
                // 2018/02/15 S21_NA#23230 Add End
                // 2020/04/23 QC#56638 Add End

                if (changePreInv(rs, preInvMap) && !isErrInv(preInvMap, errInvMap)) {
                    INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                    invLineTMsgList.add(invLineTMsg);
                    errInvMap = new HashMap<String, Object>();
                }

                if (isCompOrdTMsgList.size() >= this.commitNumber) {
                    int insertCount = insertIsCompOrd(isCompOrdTMsgList);
                    if (insertCount == isCompOrdTMsgList.size()) {
                        if (updateInvLine(invLineTMsgList)) {
                            this.normalCount += insertCount;
                            commit();
                        } else {
                            this.errorCount += isCompOrdTMsgList.size();
                            rollback();
                            errInvMap = setInvMap(rs);
                        }
                    } else {
                        this.errorCount += isCompOrdTMsgList.size();
                        rollback();
                        errInvMap = setInvMap(rs);
                    }
                    isCompOrdTMsgList.clear();
                    invLineTMsgList.clear();
                    insertCount = 0;

                }
                preInvMap = setInvMap(rs);
            }

            if (!preInvMap.isEmpty() && !isErrInv(preInvMap, errInvMap)) {
                INV_LINETMsg invLineTMsg = setInvLine(preInvMap);
                invLineTMsgList.add(invLineTMsg);
            }

            if (isCompOrdTMsgList.size() > 0) {
                int insertCount = insertIsCompOrd(isCompOrdTMsgList);
                if (insertCount == isCompOrdTMsgList.size()) {
                    if (updateInvLine(invLineTMsgList)) {
                        this.normalCount += insertCount;
                        commit();
                    } else {
                        this.errorCount += isCompOrdTMsgList.size();
                        rollback();
                    }
                } else {
                    this.errorCount += isCompOrdTMsgList.size();
                    rollback();
                }
            }

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private Map<String, Object> getParamMap() throws SQLException {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // get WMS_MTR_READ_PAST_DAY_AOT
        BigDecimal aotDays = ZYPCodeDataUtil.getNumConstValue(CONST_PAST_COMP_DAYS_AOT, this.glblCmpyCd);
        int days = 0;
        if (aotDays != null) {
            days = aotDays.intValue();
        }
        String rsDt = ZYPDateUtil.addDays(this.slsDt, -days);
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.slsDt);
        paramMap.put("aotDay", rsDt);
        // 2018/02/15 S21_NA#23230 Mod Start
//        paramMap.put("lineBizTpCd", this.compIsBizTpCd);
        paramMap.put("lineBizTpCd", PERCENT + this.compIsBizTpCd + PERCENT);
        // 2018/02/15 S21_NA#23230 Mod End
        List<String> sendCompCpltCdList = S21StringUtil.toList(this.sendCompCpltCd);
        paramMap.put("compProcStsCdList", sendCompCpltCdList);
        // Add Start 2018/05/25 QC#21841(Sol#495)
        paramMap.put("invLineCatgCd", INV_LINE_CATG.ITEM);
        // Add End 2018/05/25 QC#21841(Sol#495)
        // 2018/09/11 QC#28118,28123 Add Start
        paramMap.put("crCdCredit", CR_REBIL.CREDIT);
        paramMap.put("crCdRebill", CR_REBIL.REBILL);
        // 2018/09/11 QC#28118,28123 Add End
        // 2018/09/25 QC#28231 Add Start
        paramMap.put("invLineCatgCharge", INV_LINE_CATG.CHARGE);
        paramMap.put("invLineCatgFreight", INV_LINE_CATG.FREIGHT);
        // 2018/09/25 QC#28231 Add End
        // 2018/10/30 QC#29033 Add Start
        paramMap.put("invTrxSrcTpWS", TRX_SRC_TP.WHOLE_SALES);
        paramMap.put("invTrxSrcTpWSReturn", TRX_SRC_TP.WHOLE_SALES_RETURN);
        // 2018/10/30 QC#29033 Add End
        // 2018/11/09 QC#29131 Add End
        paramMap.put("creditMemo", INV_TP.CREDIT_MEMO);
        // 2018/11/09 QC#29131 Add End
        // Add Start 2019/09/05 QC#51704
        paramMap.put("substrDtLngth", SUBSTR_DT_LNGTH);
        // Add End 2019/09/05 QC#51704
        // Add Start 2019/09/13 QC#52943
        paramMap.put("flgOnY", ZYPConstant.FLG_ON_Y);
        // Add End 2019/09/13 QC#52943
        // 2020/04/23 QC#56638 Add Start
        paramMap.put("nfc", NFC);
        paramMap.put("miscCd", DS_ORD_CATG_MISC);
        paramMap.put("lfsMiscCd", DS_ORD_TP_LFS_MISC);
        // 2020/04/23 QC#56638 Add End

        return paramMap;
    }

    private S21SsmExecutionParameter getExecParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(MAX_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private Map<String, Object> setInvMap(ResultSet rs) throws SQLException {
        Map<String, Object> invMap = new HashMap<String, Object>();
        invMap.put("INV_NUM", rs.getString("INV_NUM"));
        invMap.put("INV_BOL_LINE_NUM", rs.getString("INV_BOL_LINE_NUM"));
        invMap.put("INV_LINE_NUM", rs.getString("INV_LINE_NUM"));
        invMap.put("INV_LINE_SUB_NUM", rs.getString("INV_LINE_SUB_NUM"));
        invMap.put("INV_LINE_SUB_TRX_NUM", rs.getString("INV_LINE_SUB_TRX_NUM"));
        return invMap;
    }

    private boolean changePreInv(ResultSet rs, Map<String, Object> preInvMap) throws SQLException {
        if (preInvMap.isEmpty()) {
            return false;
        }
        if (!rs.getString("INV_NUM").equals((String) preInvMap.get("INV_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_BOL_LINE_NUM").equals((String) preInvMap.get("INV_BOL_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_NUM").equals((String) preInvMap.get("INV_LINE_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_NUM").equals((String) preInvMap.get("INV_LINE_SUB_NUM"))) {
            return true;
        }
        if (!rs.getString("INV_LINE_SUB_TRX_NUM").equals((String) preInvMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return true;
        }
        return false;
    }

    private boolean isErrInv(Map<String, Object> preInvMap, Map<String, Object> errInvMap) throws SQLException {
        if (errInvMap.isEmpty()) {
            return false;
        }
        if (!((String) preInvMap.get("INV_NUM")).equals((String) errInvMap.get("INV_NUM"))) {
            return false;
        }
        if (!((String) preInvMap.get("INV_BOL_LINE_NUM")).equals((String) errInvMap.get("INV_BOL_LINE_NUM"))) {
            return false;
        }
        if (!((String) preInvMap.get("INV_LINE_NUM")).equals((String) errInvMap.get("INV_LINE_NUM"))) {
            return false;
        }
        if (!((String) preInvMap.get("INV_LINE_SUB_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_NUM"))) {
            return false;
        }
        if (!((String) preInvMap.get("INV_LINE_SUB_TRX_NUM")).equals((String) errInvMap.get("INV_LINE_SUB_TRX_NUM"))) {
            return false;
        }
        return true;
    }

    // 2018/02/15 S21_NA#23230 Mod Start
//    private IMG_SPLY_COMP_ORDTMsg setIsCompOrd(ResultSet rs) throws SQLException {
    private IMG_SPLY_COMP_ORDTMsg setIsCompOrd(ResultSet rs, String psnCd) throws SQLException {
    // 2018/02/15 S21_NA#23230 Mod End
        IMG_SPLY_COMP_ORDTMsg tMsg = new IMG_SPLY_COMP_ORDTMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.imgSplyCompOrdPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.IMG_SPLY_COMP_ORD_SQ));
        setValue(tMsg.invNum, rs.getString("INV_NUM"));
        setValue(tMsg.invLineNum, rs.getString("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, rs.getString("INV_LINE_SUB_NUM"));
        setValue(tMsg.invDt, rs.getString("INV_DT"));
        setValue(tMsg.cpoOrdNum, rs.getString("CPO_ORD_NUM"));
        setValue(tMsg.sellToCustCd, rs.getString("SELL_TO_CUST_CD"));
        setValue(tMsg.t_MdlNm, rs.getString("T_MDL_NM"));
        setValue(tMsg.mdseCd, rs.getString("MDSE_CD"));
        setValue(tMsg.dsCmsnGrpCd, rs.getString("DS_CMSN_GRP_CD"));
        setValue(tMsg.shipQty, rs.getBigDecimal("SHIP_QTY"));
        setValue(tMsg.compUnitAmt, rs.getBigDecimal("COMP_UNIT_AMT"));
        // 2018/02/15 S21_NA#23230 Mod Start
//        setValue(tMsg.slsRepPsnCd, rs.getString("SLS_REP_PSN_CD"));
        if (ZYPCommonFunc.hasValue(psnCd)) {
            setValue(tMsg.slsRepPsnCd, psnCd);
        } else {
            setValue(tMsg.slsRepPsnCd, rs.getString("SLS_REP_PSN_CD"));
        }
        // 2018/02/15 S21_NA#23230 Mod End
        setValue(tMsg.slsRepRoleTpCd, rs.getString("SLS_REP_ROLE_TP_CD"));
        setValue(tMsg.serNum, rs.getString("SER_NUM"));
        setValue(tMsg.sellToCustAcctNm, rs.getString("DS_ACCT_NM"));
        setValue(tMsg.dsOrdCatgCd, rs.getString("DS_ORD_CATG_CD"));
        setValue(tMsg.dsOrdTpCd, rs.getString("DS_ORD_TP_CD"));
        // Add Start 2018/10/17 QC#28842
        setValue(tMsg.invBolLineNum, rs.getString("INV_BOL_LINE_NUM"));
        // Add End 2018/10/17 QC#28842
        return tMsg;
    }

    private INV_LINETMsg setInvLine(Map<String, Object> invMap) {
        INV_LINETMsg tMsg = new INV_LINETMsg();
        setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(tMsg.invNum, (String) invMap.get("INV_NUM"));
        setValue(tMsg.invBolLineNum, (String) invMap.get("INV_BOL_LINE_NUM"));
        setValue(tMsg.invLineNum, (String) invMap.get("INV_LINE_NUM"));
        setValue(tMsg.invLineSubNum, (String) invMap.get("INV_LINE_SUB_NUM"));
        setValue(tMsg.invLineSubTrxNum, (String) invMap.get("INV_LINE_SUB_TRX_NUM"));
        return tMsg;
    }

    private int insertIsCompOrd(List<IMG_SPLY_COMP_ORDTMsg> tMsgList) {
        int insertCount = S21FastTBLAccessor.insert(tMsgList.toArray(new IMG_SPLY_COMP_ORDTMsg[tMsgList.size()]));
        return insertCount;
    }

    private boolean updateInvLine(List<INV_LINETMsg> tMsgList) {
        if (tMsgList.size() == 0) {
            return true;
        }

        List<INV_LINETMsg> updTMsgList = new ArrayList<INV_LINETMsg>(tMsgList.size());
        for (INV_LINETMsg tMsg : tMsgList) {
            tMsg = (INV_LINETMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (tMsg == null) {
                continue;
            }
            setValue(tMsg.compProcStsCd, COMP_PROC_STS_COMPLETE);
            updTMsgList.add(tMsg);
        }

        if (updTMsgList.size() == 0) {
            return false;
        }
        int updateCount = S21FastTBLAccessor.update(updTMsgList.toArray(new INV_LINETMsg[updTMsgList.size()]));
        if (updateCount != updTMsgList.size()) {
            return false;
        }
        return true;
    }

    // 2018/02/15 S21_NA#23230 Add Start
    /**
     * Get Root Organization Code
     * @param psnCd String
     * @param ordBookDt String
     * @return String
     */
    // 2020/04/23 QC#56638 Mod Start
    // 2019/12/12 QC#54880 Mod Start
//    private String getRootOrgCd(String psnCd) {
//    private String getRootOrgCd(String psnCd, String ordBookDt) {
    private String getRootOrgCd(String psnCd, String soldToCustCd) {
    // 2019/12/12 QC#54880 Mod End
    // 2020/04/23 QC#56638 Mod End
        // Get ShipToAddrInfo
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("psnCd", psnCd);
        ssmParam.put("trtyGrpTpCd", TRTY_GRP_TP.IS);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // 2018/08/07 QC#26406 Add Start
        // 2019/06/03 QC#50637 Del Start
//        ssmParam.put("slsDt", this.slsDt);
        // 2019/06/03 QC#50637 Del End
        ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        // 2018/08/07 QC#26406 Add End
        // 2020/04/23 QC#56638 Del Start
        // 2019/12/12 QC#54880 Add Start
//        ssmParam.put("ordBookDt", ordBookDt);
        // 2019/12/12 QC#54880 Add End
        // 2020/04/23 QC#56638 Del End
        // 2020/04/23 QC#56638 Add Start
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("soldToCustCd", soldToCustCd);
        // 2020/04/23 QC#56638 Add End

        // 2020/04/23 QC#56638 Mod Start
//        String rootOrgCd = (String) ssmBatchClient.queryObject("getRootOrgCd", ssmParam);
        List<String> rootOrgCdList = (List<String>) ssmBatchClient.queryObjectList("getRootOrgCd", ssmParam);
        if (rootOrgCdList!= null && rootOrgCdList.size() == 1) {
            return rootOrgCdList.get(0);
        } else {
            return null;
        }
//        return rootOrgCd;
        // 2020/04/23 QC#56638 Mod End
    }

    // Mod Start 2019/09/05 QC#51704
    ///**
    // * Get Organization Code List
    // * @param rootOrgCd String
    // * @return String
    // */
    //private List<Map<String, Object>> getOrgCdList(String rootOrgCd) {
    /**
     * Get Organization Code List
     * @param rootOrgCd String
     * @param ordBookDt String
     * @return String
     */
    private List<Map<String, Object>> getOrgCdList(String rootOrgCd, String ordBookDt) {
        // Mod End 2019/09/05 QC#51704
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("orgCd", rootOrgCd);
        // 2018/08/07 QC#26406 Add Start
        // Mod Start 2019/09/05 QC#51704
        //ssmParam.put("slsDt", this.slsDt);
        //ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
        ssmParam.put("ordBookDt", ordBookDt);
        ssmParam.put("gnrnTpCdDelete", GNRN_TP.DELETE);
        ssmParam.put("gnrnTpCdTerminated", GNRN_TP.TERMINATED);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // Mod End 2019/09/05 QC#51704
        // 2020/04/23 QC#56638 Add Start
        ssmParam.put("slsDt", this.slsDt);
        // 2020/04/23 QC#56638 Add End

        // 2018/08/07 QC#26406 Add End
        List<Map<String, Object>> orgCdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrgCdList", ssmParam);
        return orgCdList;
    }

    // Mod Start 2019/09/05 QC#51704
    ///**
    // * Get Person Code List
    // * @param orgCd String
    // * @return String
    // */
    //private List<Map<String, Object>> getPsnCdList(String orgCd) {
    /**
     * Get Person Code List
     * @param orgCd String
     * @param ordBookDt String
     * @return String
     */
    private List<Map<String, Object>> getPsnCdList(String orgCd, String ordBookDt) {
        // Mod End 2019/09/05 QC#51704
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", this.glblCmpyCd);
        ssmParam.put("orgCd", orgCd);
        ssmParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);

        List<String> acctTeamRoleTpCdList = new ArrayList<String>();
        acctTeamRoleTpCdList.add(ACCT_TEAM_ROLE_TP.SPECIALIST);
        acctTeamRoleTpCdList.add(ACCT_TEAM_ROLE_TP.MANAGER);
        ssmParam.put("acctTeamRoleTpCdList", acctTeamRoleTpCdList);
        // 2018/08/07 QC#26406 Add Start
        // Mod Start 2019/09/05 QC#51704
        //ssmParam.put("slsDt", this.slsDt);
        //ssmParam.put("gnrnTpCd", GNRN_TP.CURRENT);
//        ssmParam.put("ordBookDt", ordBookDt);
        // 2020/04/23 QC#56638 Add Start
        ssmParam.put("slsDt", this.slsDt);
        ssmParam.put("slsDt1st", this.slsDt.substring(0,6) + "01");
        // 2020/04/23 QC#56638 Add End
        // Mod End 2019/09/05 QC#51704
        // 2018/08/07 QC#26406 Add End

        List<Map<String, Object>> psnCdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPsnCdList", ssmParam);
        return psnCdList;
    }
    // 2018/02/15 S21_NA#23230 Add End
    // 2020/04/23 QC#56638 Add Start
    private void sendMail() {
        // Get From Mail Address.
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, "FROM0003");
        groupFrom.setMailKey1("NFC");
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();
        if (addrFromList == null || addrFromList.isEmpty()) {
            throw new S21AbendException(ZZMM0007E, new String[] {"From Address" });
        }
        S21MailAddress mailAddrFrom = addrFromList.get(0);

        // Get To Mail Address.
        S21MailGroup groupTo = new S21MailGroup(this.glblCmpyCd, "NFCB4020");
        groupTo.setMailKey1("TO");
        List<S21MailAddress> mailAddrToList = groupTo.getMailAddress();
        if (mailAddrToList == null || mailAddrToList.isEmpty()) {
            throw new S21AbendException(ZZMM0007E, new String[] {"To Address" });
        }

        // Get Template
        S21MailTemplate maiTemplate = new S21MailTemplate(glblCmpyCd, "NFCB4020M001");
        if (!ZYPCommonFunc.hasValue(maiTemplate.getBody())) {
            throw new S21AbendException(ZZMM0007E, new String[] {"Mail Template" });
        }

        List<S21MailAddress> addrToList = new ArrayList<S21MailAddress>(mailAddrToList);

        // Set Message
        maiTemplate.setTemplateParameter("slsDt", this.slsDt);
        maiTemplate.setTemplateParameter("content", createMessage());

        // Set e-Mail
        S21Mail mail = new S21Mail(this.glblCmpyCd);
        mail.setFromAddress(mailAddrFrom);
        mail.setToAddressList(addrToList);
        mail.setMailTemplate(maiTemplate);
        mail.setSubject(maiTemplate.getSubject(), "ISO-8859-1");
        mail.postMail();
    }

    private String createMessage() {
        StringBuilder msg = new StringBuilder();
        msg.append(String.format("%-12s", "Invoice#"));
        msg.append(String.format("%-12s", "Order#"));
        msg.append("Sold To Location");
        msg.append(CRLF);
        msg.append("-----------------------------------------");
        msg.append(CRLF);

        List<String> dtlListDelDup = new ArrayList<String>(new LinkedHashSet<String>(this.dtlList));
        for (int i = 0; i < dtlListDelDup.size(); i++) {
            msg.append(dtlListDelDup.get(i));
            msg.append(CRLF);
        }
        return msg.toString();
    }
    // 2020/04/23 QC#56638 Add End
}

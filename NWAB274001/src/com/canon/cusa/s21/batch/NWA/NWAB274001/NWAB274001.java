/*
 * <pre>Copyright (c) 2023 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB274001;

import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.BATCH_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CARR_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CPO_DTL_LINE_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CPO_DTL_LINE_SUB_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CPO_ORD_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CPO_ORD_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CTAC_PSN_EML_ADDR;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.CUST_NTFY_SHIP_INFO_PK;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.DS_ORD_CATG_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.FROM_GRP_ID_01;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.FROM_GRP_ID_02;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.MDL_NM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.MDSE_DESC_SHORT_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TAG_BR;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TAG_B_BR;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_01;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_02;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_03;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_04;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_05;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_06;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_07;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_08;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_09;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_10;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_ID_01;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ML_TMPL_ID_02;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ORD_CTAC_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ORD_QTY;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.PROC_STS_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.PRO_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ROW_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.SELL_TO_FIRST_REF_CMNT_TXT;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.SER_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.SHIP_TO_CUST_CD;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.SHIP_TO_INFO;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.SHPG_PLN_NUM;
import static com.canon.cusa.s21.batch.NWA.NWAB274001.constant.NWAB274001Constant.ZZM9000E;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CUST_NTFY_SHIP_INFOTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * Customer Notification of Shipment info Send Batch
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/06/02   Hitachi         H.Watanabe      Create          QC#61383
 * 2023/09/20   Hitachi         H.Watanabe      Update          QC#61782
 *</pre>
 */
public class NWAB274001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Sales Date */
    private String slsDt = null;


    // -- Other Internal Variable ---------------
    /** SSM Low Level Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;


    /** Execute Param */
    S21SsmExecutionParameter excParam = null;

    /** Term code */
    private TERM_CD termCd = null;

    /** total search count */
    private int searchCnt = 0;

    /** Task success count */
    private int infoSccessCnt = 0;

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB274001().executeBatch(NWAB274001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        // Initialization
        ssmLLClient = S21SsmLowLevelCodingClient.getClient(getClass());
        termCd = TERM_CD.NORMAL_END;

        // Get the Global Company Code.
        // If an error occurs, throw Exception.
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!hasValue(glblCmpyCd)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Global Company Code" });
        }

        // Get the Sales Date.
        // If an error occurs, throw Exception.
        this.slsDt = ZYPDateUtil.getSalesDate(this.glblCmpyCd, BATCH_ID);
        if (!hasValue(slsDt)) {
            throw new S21AbendException(ZZM9000E, new String[] {"Sales Date" });
        }

        excParam = new S21SsmExecutionParameter();
        excParam.setFetchSize(FETCH_SIZE_MAX);
        excParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        excParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        excParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
    }

    @Override
    protected void mainRoutine() {
        doProcess();
    }

    @Override
    protected void termRoutine() {
        // Set term code and total count.
        setTermState(this.termCd, this.infoSccessCnt, this.searchCnt - this.infoSccessCnt, this.searchCnt);
    }

    /**
     * doProcess
     */
    private void doProcess() {

        PreparedStatement prcIncrStmt = null;
        ResultSet rs = null;

        try {

            prcIncrStmt = ssmLLClient.createPreparedStatement("getTargetList", setParamForTargetList(), excParam);
            rs = prcIncrStmt.executeQuery();

            String targetLob = null;
            String targetAddress = null;
            List<Map<String, Object>> targetGroupList = new ArrayList<Map<String, Object>>();

            while (rs.next()) {
                this.searchCnt++;
                if (targetLob == null || targetAddress == null) {
                    if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD))) {
                        targetLob = rs.getString(LINE_BIZ_TP_CD);
                    }
                    if (ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
                        targetAddress = rs.getString(CTAC_PSN_EML_ADDR);
                    }
                    targetGroupList = addTargetGroupList(targetGroupList, rs);
                    continue;
                }
                if (!hasChangeTargetGroup(rs, targetLob, targetAddress)) {
                    targetGroupList = addTargetGroupList(targetGroupList, rs);
                    continue;
                }

                if (!hasGetAddress(targetGroupList)) {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.ERROR);
                    this.termCd = TERM_CD.WARNING_END;
                    targetGroupList.removeAll(targetGroupList);
                    targetGroupList = addTargetGroupList(targetGroupList, rs);
                    if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD))) {
                        targetLob = rs.getString(LINE_BIZ_TP_CD);
                    }
                    if (ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
                        targetAddress = rs.getString(CTAC_PSN_EML_ADDR);
                    }
                    commit();
                    continue;
                }

                if (sendMailProcess(targetGroupList)) {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.COMPLEATED);
                    this.infoSccessCnt += targetGroupList.size();
                    targetGroupList.removeAll(targetGroupList);
                    targetGroupList = addTargetGroupList(targetGroupList, rs);
                    if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD))) {
                        targetLob = rs.getString(LINE_BIZ_TP_CD);
                    }
                    if (ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
                        targetAddress = rs.getString(CTAC_PSN_EML_ADDR);
                    }
                    commit();
                } else {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.ERROR);
                    this.termCd = TERM_CD.WARNING_END;
                    targetGroupList.removeAll(targetGroupList);
                    targetGroupList = addTargetGroupList(targetGroupList, rs);
                    if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD))) {
                        targetLob = rs.getString(LINE_BIZ_TP_CD);
                    }
                    if (ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
                        targetAddress = rs.getString(CTAC_PSN_EML_ADDR);
                    }
                    commit();
                }

            }
            if (targetGroupList.size() > 0) {
                if (!hasGetAddress(targetGroupList)) {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.ERROR);
                    this.termCd = TERM_CD.WARNING_END;
                    commit();
                } else if (sendMailProcess(targetGroupList)) {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.COMPLEATED);
                    this.infoSccessCnt += targetGroupList.size();
                    commit();
                } else {
                    updCustNtfyShipInfo(targetGroupList, PROC_STS.ERROR);
                    this.termCd = TERM_CD.WARNING_END;
                    commit();
                }
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(prcIncrStmt, rs);
        }
    }

    private Map<String, Object> setParamForTargetList() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        // 2023/09/20 QC#61782 Mod Start
//        paramMap.put("ctacTpCd", ORD_CTAC_TXT);
        String[] ctacTpCd = new String[] {CTAC_TP.ORDER_CONTACT, CTAC_TP.MYCSAUSER};
        paramMap.put("ctacTpCd", ctacTpCd);
        // 2023/09/20 QC#61782 Mod End
        paramMap.put("procStsCd", PROC_STS.IN_COMPLETED);
        return paramMap;
    }

    private List<Map<String, Object>> addTargetGroupList(List<Map<String, Object>> targetGroupList, ResultSet rs) throws SQLException {
        Map<String, Object> rsMap = new HashMap<String, Object>();
        if (ZYPCommonFunc.hasValue(rs.getBigDecimal(CUST_NTFY_SHIP_INFO_PK))) {
            rsMap.put(CUST_NTFY_SHIP_INFO_PK, rs.getBigDecimal(CUST_NTFY_SHIP_INFO_PK));
        } else {
            rsMap.put(CUST_NTFY_SHIP_INFO_PK, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(PROC_STS_CD))) {
            rsMap.put(PROC_STS_CD, rs.getString(PROC_STS_CD));
        } else {
            rsMap.put(PROC_STS_CD, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(SHPG_PLN_NUM))) {
            rsMap.put(SHPG_PLN_NUM, rs.getString(SHPG_PLN_NUM));
        } else {
            rsMap.put(SHPG_PLN_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(DS_ORD_CATG_CD))) {
            rsMap.put(DS_ORD_CATG_CD, rs.getString(DS_ORD_CATG_CD));
        } else {
            rsMap.put(DS_ORD_CATG_CD, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD))) {
            rsMap.put(LINE_BIZ_TP_CD, rs.getString(LINE_BIZ_TP_CD));
        } else {
            rsMap.put(LINE_BIZ_TP_CD, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
            rsMap.put(CTAC_PSN_EML_ADDR, rs.getString(CTAC_PSN_EML_ADDR));
        } else {
            rsMap.put(CTAC_PSN_EML_ADDR, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(SER_NUM))) {
            rsMap.put(SER_NUM, rs.getString(SER_NUM));
        } else {
            rsMap.put(SER_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(MDL_NM))) {
            rsMap.put(MDL_NM, rs.getString(MDL_NM));
        } else {
            rsMap.put(MDL_NM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CPO_ORD_NUM))) {
            rsMap.put(CPO_ORD_NUM, rs.getString(CPO_ORD_NUM));
        } else {
            rsMap.put(CPO_ORD_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CPO_ORD_DT))) {
            rsMap.put(CPO_ORD_DT, rs.getString(CPO_ORD_DT));
        } else {
            rsMap.put(CPO_ORD_DT, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(SELL_TO_FIRST_REF_CMNT_TXT))) {
            rsMap.put(SELL_TO_FIRST_REF_CMNT_TXT, rs.getString(SELL_TO_FIRST_REF_CMNT_TXT));
        } else {
            rsMap.put(SELL_TO_FIRST_REF_CMNT_TXT, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(MDSE_DESC_SHORT_TXT))) {
            rsMap.put(MDSE_DESC_SHORT_TXT, rs.getString(MDSE_DESC_SHORT_TXT));
        } else {
            rsMap.put(MDSE_DESC_SHORT_TXT, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(ORD_QTY))) {
            rsMap.put(ORD_QTY, rs.getString(ORD_QTY));
        } else {
            rsMap.put(ORD_QTY, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CARR_NM))) {
            rsMap.put(CARR_NM, rs.getString(CARR_NM));
        } else {
            rsMap.put(CARR_NM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(PRO_NUM))) {
            rsMap.put(PRO_NUM, rs.getString(PRO_NUM));
        } else {
            rsMap.put(PRO_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(SHIP_TO_INFO))) {
            rsMap.put(SHIP_TO_INFO, rs.getString(SHIP_TO_INFO));
        } else {
            rsMap.put(SHIP_TO_INFO, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(ROW_NUM))) {
            rsMap.put(ROW_NUM, rs.getString(ROW_NUM));
        } else {
            rsMap.put(ROW_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CPO_DTL_LINE_NUM))) {
            rsMap.put(CPO_DTL_LINE_NUM, rs.getString(CPO_DTL_LINE_NUM));
        } else {
            rsMap.put(CPO_DTL_LINE_NUM, null);
        }
        if (ZYPCommonFunc.hasValue(rs.getString(CPO_DTL_LINE_SUB_NUM))) {
            rsMap.put(CPO_DTL_LINE_SUB_NUM, rs.getString(CPO_DTL_LINE_SUB_NUM));
        } else {
            rsMap.put(CPO_DTL_LINE_SUB_NUM, null);
        }
        targetGroupList.add(rsMap);
        return targetGroupList;
    }

    private boolean hasChangeTargetGroup(ResultSet rs, String targetLob, String targetAddress) throws SQLException {
        if (ZYPCommonFunc.hasValue(rs.getString(LINE_BIZ_TP_CD)) && ZYPCommonFunc.hasValue(rs.getString(CTAC_PSN_EML_ADDR))) {
            if (targetLob.equals(rs.getString(LINE_BIZ_TP_CD)) && targetAddress.equals(rs.getString(CTAC_PSN_EML_ADDR))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasGetAddress(List<Map<String, Object>> targetGroupList) throws SQLException {
        for (int i = 0; i < targetGroupList.size(); i++) {
            if (targetGroupList.get(i).get(CTAC_PSN_EML_ADDR) == null) {
                return false;
            }
        }
        return true;
    }

    private boolean sendMailProcess(List<Map<String, Object>> targetGroupList) throws SQLException {

        S21Mail mail = new S21Mail(this.glblCmpyCd);
        String mailShppedItemInfoParam = createMailShppedItemInfoParam(targetGroupList);

        // Mail Send
        // Mail Template
        S21MailTemplate s21MailTemplate = null;
        
        if (LINE_BIZ_TP.PPS.equals(targetGroupList.get(0).get(LINE_BIZ_TP_CD))) {
            s21MailTemplate = new S21MailTemplate(this.glblCmpyCd, ML_TMPL_ID_01);
        } else {
            s21MailTemplate = new S21MailTemplate(this.glblCmpyCd, ML_TMPL_ID_02);
        }
        if (s21MailTemplate == null) {
            return false;
        }
        s21MailTemplate.setTemplateParameter("ShppedItemInfo", mailShppedItemInfoParam);
        String year = slsDt.substring(0, 4);
        s21MailTemplate.setTemplateParameter("Year", year);
        mail.setMailTemplate(s21MailTemplate);

        // From Address
        S21MailAddress fromAddress = getSendMailAddress(targetGroupList);
        if (fromAddress == null) {
            return false;
        }
        mail.setFromAddress(fromAddress);

        // To Address
        S21MailAddress toAddress = new S21MailAddress(this.glblCmpyCd, targetGroupList.get(0).get(CTAC_PSN_EML_ADDR).toString());
        if (toAddress == null) {
            return false;
        }
        mail.setToAddress(toAddress);

        mail.postMail();
        return true;
    }

    private String createMailShppedItemInfoParam(List<Map<String, Object>> targetGroupList) throws SQLException {
        StringBuilder mailShppedItemInfoParam = new StringBuilder();
        String targetShipToInfo = null;
        for (int i = 0; i < targetGroupList.size(); i++) {
            if (!ZYPCommonFunc.hasValue(targetShipToInfo) || !targetShipToInfo.equals(targetGroupList.get(i).get(SHIP_TO_INFO))) {
                targetShipToInfo = targetGroupList.get(i).get(SHIP_TO_INFO).toString();
                mailShppedItemInfoParam.append(ML_TMPL_10);
                if (targetGroupList.get(i).get(SHIP_TO_INFO) != null) {
                    mailShppedItemInfoParam.append(targetGroupList.get(i).get(SHIP_TO_INFO));
                }
                mailShppedItemInfoParam.append(ML_TAG_B_BR);
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);
            if (DS_ORD_CATG.CONTRACT_SUPPLY_CSA.equals(targetGroupList.get(i).get(DS_ORD_CATG_CD))) {
                mailShppedItemInfoParam.append(ML_TMPL_01);
                if (targetGroupList.get(i).get(MDL_NM) != null) {
                    mailShppedItemInfoParam.append(targetGroupList.get(i).get(MDL_NM));
                }
                mailShppedItemInfoParam.append(ML_TMPL_02);
                if (targetGroupList.get(i).get(SER_NUM) != null) {
                    mailShppedItemInfoParam.append(targetGroupList.get(i).get(SER_NUM));
                }
                mailShppedItemInfoParam.append(ML_TAG_B_BR);
            }

            mailShppedItemInfoParam.append(ML_TMPL_03);
            if (targetGroupList.get(i).get(CPO_ORD_DT) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(CPO_ORD_DT));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_04);
            if (targetGroupList.get(i).get(CPO_ORD_NUM) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(CPO_ORD_NUM));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_05);
            if (targetGroupList.get(i).get(SELL_TO_FIRST_REF_CMNT_TXT) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(SELL_TO_FIRST_REF_CMNT_TXT));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_06);
            if (targetGroupList.get(i).get(MDSE_DESC_SHORT_TXT) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(MDSE_DESC_SHORT_TXT));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_07);
            if (targetGroupList.get(i).get(ORD_QTY) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(ORD_QTY));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_08);
            if (targetGroupList.get(i).get(CARR_NM) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(CARR_NM));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TMPL_09);
            if (targetGroupList.get(i).get(PRO_NUM) != null) {
                mailShppedItemInfoParam.append(targetGroupList.get(i).get(PRO_NUM));
            }
            mailShppedItemInfoParam.append(ML_TAG_B_BR);

            mailShppedItemInfoParam.append(ML_TAG_BR);
        }
        return mailShppedItemInfoParam.toString();
    }

    private S21MailAddress getSendMailAddress(List<Map<String, Object>> targetGroupList) throws SQLException {
        S21MailGroup groupFrom = null;
        List<S21MailAddress> addrFromList = null;

        if (LINE_BIZ_TP.PPS.equals(targetGroupList.get(0).get(LINE_BIZ_TP_CD))) {
            groupFrom = new S21MailGroup(this.glblCmpyCd, FROM_GRP_ID_01);
            addrFromList = groupFrom.getMailAddress();
        } else {
            groupFrom = new S21MailGroup(this.glblCmpyCd, FROM_GRP_ID_02);
            addrFromList = groupFrom.getMailAddress();
        }
        if (addrFromList != null && !addrFromList.isEmpty()) {
            return addrFromList.get(0);
        }
        return null;
    }

    private void updCustNtfyShipInfo(List<Map<String, Object>> targetGroupList, String procStsCd) throws SQLException {
        for (int i = 0; i < targetGroupList.size(); i++) {
            CUST_NTFY_SHIP_INFOTMsg custNtfySgipInfoTMsg = new CUST_NTFY_SHIP_INFOTMsg();
            ZYPEZDItemValueSetter.setValue(custNtfySgipInfoTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(custNtfySgipInfoTMsg.custNtfyShipInfoPk, (BigDecimal) targetGroupList.get(i).get(CUST_NTFY_SHIP_INFO_PK));
            custNtfySgipInfoTMsg = (CUST_NTFY_SHIP_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(custNtfySgipInfoTMsg);
            ZYPEZDItemValueSetter.setValue(custNtfySgipInfoTMsg.procStsCd, procStsCd);
            EZDTBLAccessor.update(custNtfySgipInfoTMsg);
        }
    }
}

/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB164001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.PRCH_REQ_DTLTMsg;
import business.db.RTL_WHTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB164001.constant.NPAB164001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MRP_INFO_RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRCH_REQ_REC_TP;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeData;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeException;
import com.canon.cusa.s21.framework.ZYP.localtime.ZYPLocalTimeUtil;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * Business ID : NPAB1640 Tech Request Notification Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/29/2016   CITS       Yasushi Nomura        Create          N/A
 * 10/31/2016   CITS       T.Wada                Update          QC#15207
 *</pre>
 */
public class NPAB164001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Sales Date */
    private String salesDate = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int commitCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    /** SQL Access Parts */
    private S21SsmBatchClient ssmBatchClient = null;

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB164001Constant.NPAM1479E);
        }

        salesDate = ZYPDateUtil.getSalesDate(globalCompanyCode);
        if (!ZYPCommonFunc.hasValue(salesDate)) {
            throw new S21AbendException(NPAB164001Constant.NPAM1480E);
        }
    }

    @Override
    protected void mainRoutine() {
        String mode = getUserVariable1();
        if (!ZYPCommonFunc.hasValue(mode) || (PRCH_REQ_REC_TP.TECH_REQUEST.equals(mode))) {
            techRequestProcess();
        }
        if (!ZYPCommonFunc.hasValue(mode) || (PRCH_REQ_REC_TP.TECH_RETURN.equals(mode))) {
            techReturnProcess();
        }
        if ((errorCount != 0) && (commitCount == 0)) {
            throw new S21AbendException(NPAB164001Constant.NPAM1265E);
        }
    }

    private void techRequestProcess() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB164001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB164001Constant.DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_REQUEST);
            paramMap.put(NPAB164001Constant.DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_0, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_0);
            paramMap.put(NPAB164001Constant.DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_9, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_9);
            paramMap.put(NPAB164001Constant.DB_PARAM_EFF_FROM_DT, salesDate);
            paramMap.put(NPAB164001Constant.DB_PARAM_EFF_THRU_DT, salesDate);
            paramMap.put(NPAB164001Constant.DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);
            paramMap.put(NPAB164001Constant.DB_PARAM_LOC_STS_CD_1, LOC_STS.DC_STOCK);
            paramMap.put(NPAB164001Constant.DB_PARAM_LOC_STS_CD_2, LOC_STS.IN_TRANSIT);
            paramMap.put(NPAB164001Constant.DB_PARAM_MRP_ENBL_FLG, ZYPConstant.FLG_ON_Y);
            paramMap.put(NPAB164001Constant.DB_PARAM_MRP_INFO_RGTN_STS_CD, MRP_INFO_RGTN_STS.AVAILABLE);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("searchRequest", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            String prchReqNum = null;
            String prTp = null;
            String techNM = null;
            String rtlWhNm = null;
            String rtlSwhNm = null;
            String requestDt = null;
            String emlAddr1 = null;
            String emlAddr2 = null;
            StringBuilder sb = null;
            List<String[]> sendList = null;
            while (resultSet.next()) {
                String prchReqNumTemp = resultSet.getString(NPAB164001Constant.PRCH_REQ_NUM);
                if ((prchReqNum == null) || (!prchReqNum.equals(prchReqNumTemp))) {
                    if (prchReqNum != null) {
                        if (ZYPCommonFunc.hasValue(emlAddr1) && ZYPCommonFunc.hasValue(emlAddr2)) {
                            // send mail.
                            sendMail4Request(emlAddr1, emlAddr2, sb, prchReqNum, prTp, techNM, rtlWhNm, rtlSwhNm, requestDt);
                            updateFlag4Request(sendList, false);
                            commitCount += sendList.size();
                        } else {
                            updateFlag4Request(sendList, true);
                            errorCount += sendList.size();
                        }
                        commit();
                    }
                    sb = new StringBuilder();
                    prchReqNum = prchReqNumTemp;
                    String rqstRcvDt = resultSet.getString(NPAB164001Constant.RQST_RCV_DT);
                    String rqstRcvTm = resultSet.getString(NPAB164001Constant.RQST_RCV_TM);

                    // QC#15207
                    String postCd = null;
                    String ctryCd = null;
//                    Map<String, Object> locInfo =  getTechLocation(globalCompanyCode, resultSet.getString("TECH_TOC_CD"));
                    Map<String, Object> locInfo =  getTechLocation(globalCompanyCode, resultSet.getString("DEST_RTL_WH_CD"));
                    if (locInfo != null) {
                        postCd = (String) locInfo.get("POST_CD");
                        ctryCd = (String) locInfo.get("CTRY_CD");
                    }
                    String lclTZId = null;
                    if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                        try {
                            lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                        } catch (ZYPLocalTimeException e) {
                            lclTZId = null;
                        }
                    } else {
                        S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + resultSet.getString("TECH_TOC_CD"));
                    }
                    if (lclTZId != null) {
                        ZYPLocalTimeData lcl = convertTimeSys2Lcl(rqstRcvDt, rqstRcvTm, lclTZId);
                        if (lcl != null) {
                            requestDt = convertTime2ViewText4Request(lcl.getTime());
                        }
                    } else {
                        String wkRequestDt = convertTimeSys(rqstRcvDt, rqstRcvTm);
                        requestDt = convertTime2ViewText4Request(wkRequestDt);
                    }
                    prTp = resultSet.getString(NPAB164001Constant.PRCH_REQ_TP_DESC_TXT);
                    techNM = resultSet.getString(NPAB164001Constant.TECH_NM);
                    rtlWhNm = resultSet.getString(NPAB164001Constant.DEST_RTL_WH_CD);
                    rtlSwhNm = resultSet.getString(NPAB164001Constant.DEST_RTL_SWH_CD);
                    emlAddr1 = resultSet.getString(NPAB164001Constant.EML_ADDR_1);
                    emlAddr2 = resultSet.getString(NPAB164001Constant.EML_ADDR_2);
                    sendList = new ArrayList<String[]>();
                }
                // get line data.
                String prchReqLineNum = resultSet.getString(NPAB164001Constant.PRCH_REQ_LINE_NUM);
                if (prchReqLineNum == null) {
                    prchReqLineNum = "";
                }
                BigDecimal prchReqLineSubNum = resultSet.getBigDecimal(NPAB164001Constant.PRCH_REQ_LINE_SUB_NUM);
                if (prchReqLineSubNum == null) {
                    prchReqLineSubNum = BigDecimal.ZERO;
                }
                String mdseCd = resultSet.getString(NPAB164001Constant.MDSE_CD);
                if (mdseCd == null) {
                    mdseCd = "";
                }
                BigDecimal prchReqQty = resultSet.getBigDecimal(NPAB164001Constant.PRCH_REQ_QTY);
                if (prchReqQty == null) {
                    prchReqQty = BigDecimal.ZERO;
                }
                String prchReqLineTpDescTxt = resultSet.getString(NPAB164001Constant.PRCH_REQ_LINE_TP_DESC_TXT);
                if (prchReqLineTpDescTxt == null) {
                    prchReqLineTpDescTxt = "";
                }
                String mdseDescShortTxt = resultSet.getString(NPAB164001Constant.MDSE_DESC_SHORT_TXT);
                if (mdseDescShortTxt == null) {
                    mdseDescShortTxt = "";
                }
                BigDecimal invtyQty1 = resultSet.getBigDecimal(NPAB164001Constant.INVTY_QTY_1);
                if (invtyQty1 == null) {
                    invtyQty1 = BigDecimal.ZERO;
                }
                BigDecimal invtyQty2 = resultSet.getBigDecimal(NPAB164001Constant.INVTY_QTY_2);
                if (invtyQty2 == null) {
                    invtyQty2 = BigDecimal.ZERO;
                }
                BigDecimal minOrdQty = resultSet.getBigDecimal(NPAB164001Constant.MIN_ORD_QTY);
                BigDecimal maxInvtyQty = resultSet.getBigDecimal(NPAB164001Constant.MAX_INVTY_QTY);
                if (minOrdQty == null) {
                    minOrdQty = BigDecimal.ZERO;
                }
                if (maxInvtyQty == null) {
                    maxInvtyQty = BigDecimal.ZERO;
                }
                // get msg line.
                String line = String.format(NPAB164001Constant.ML_FMT_RQST, prchReqLineNum, prchReqLineSubNum.toString(), prchReqLineTpDescTxt, mdseCd, mdseDescShortTxt, prchReqQty.toString(), invtyQty1.toString(), invtyQty2.toString(),
                        minOrdQty.toString(), maxInvtyQty.toString());
                sb.append(line);
                sb.append(System.getProperty(NPAB164001Constant.LINE_SEPARATOR));
                sendList.add(new String[] {prchReqNum, prchReqLineNum, prchReqLineSubNum.toString() });
            }
            if (prchReqNum != null) {
                if (ZYPCommonFunc.hasValue(emlAddr1) && ZYPCommonFunc.hasValue(emlAddr2)) {
                    // send mail.
                    sendMail4Request(emlAddr1, emlAddr2, sb, prchReqNum, prTp, techNM, rtlWhNm, rtlSwhNm, requestDt);
                    updateFlag4Request(sendList, false);
                    commitCount += sendList.size();
                } else {
                    updateFlag4Request(sendList, true);
                    errorCount += sendList.size();
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    private void techReturnProcess() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB164001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB164001Constant.DB_PARAM_PRCH_REQ_REC_TP_CD, PRCH_REQ_REC_TP.TECH_RETURN);
            paramMap.put(NPAB164001Constant.DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_0, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_0);
            paramMap.put(NPAB164001Constant.DB_PARAM_TECH_RQST_NTC_ML_PROC_CD_9, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_9);
            paramMap.put(NPAB164001Constant.DB_PARAM_PRCH_REQ_LINE_STS_CD, PRCH_REQ_LINE_STS.RECEIVED);
            paramMap.put(NPAB164001Constant.DB_PARAM_EFF_FROM_DT, salesDate);
            paramMap.put(NPAB164001Constant.DB_PARAM_EFF_THRU_DT, salesDate);
            paramMap.put(NPAB164001Constant.DB_PARAM_DEL_FLG, ZYPConstant.FLG_OFF_N);

            S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
            execParam.setFetchSize(FETCH_SIZE);
            execParam.setMaxRows(0);
            preparedStatement = ssmLlcClient.createPreparedStatement("searchReturn", paramMap, execParam);
            resultSet = preparedStatement.executeQuery();

            String prchReqNum = null;
            String techNM = null;
            String cratDt = null;
            String rtlWhNm = null;
            String rtlSwhNm = null;
            String emlAddr1 = null;
            String emlAddr2 = null;
            StringBuilder sb = null;
            List<String[]> sendList = null;
            while (resultSet.next()) {
                String prchReqNumTemp = resultSet.getString(NPAB164001Constant.PRCH_REQ_NUM);
                if ((prchReqNum == null) || (!prchReqNum.equals(prchReqNumTemp))) {
                    if (prchReqNum != null) {
                        if (ZYPCommonFunc.hasValue(emlAddr1) && ZYPCommonFunc.hasValue(emlAddr2)) {
                            // send mail.
                            sendMail4Return(emlAddr1, emlAddr2, sb, prchReqNum, techNM, rtlWhNm, rtlSwhNm, cratDt);
                            updateFlag4Return(sendList, false);
                            commitCount += sendList.size();
                        } else {
                            updateFlag4Return(sendList, true);
                            errorCount += sendList.size();
                        }
                        commit();
                    }
                    sb = new StringBuilder();
                    prchReqNum = prchReqNumTemp;
                    String rqstRcvDt = resultSet.getString(NPAB164001Constant.PRCH_REQ_CRAT_DT);
                    // QC#15207
                    String postCd = null;
                    String ctryCd = null;
//                    Map<String, Object> locInfo =  getTechLocation(globalCompanyCode, resultSet.getString("TECH_TOC_CD"));
                    Map<String, Object> locInfo =  getTechLocation(globalCompanyCode, resultSet.getString("DEST_RTL_WH_CD"));
                    if (locInfo != null) {
                        postCd = (String) locInfo.get("POST_CD");
                        ctryCd = (String) locInfo.get("CTRY_CD");
                    }
                    String lclTZId = null;
                    if (ZYPCommonFunc.hasValue(ctryCd) && ZYPCommonFunc.hasValue(postCd)) {
                        try {
                            lclTZId = ZYPLocalTimeUtil.getTZId(ctryCd, postCd);
                        } catch (ZYPLocalTimeException e) {
                            lclTZId = null;
                        }
                    } else {
                        S21InfoLogOutput.println("Address information of the technician is not registered. TECH_TOC_CD = " + resultSet.getString("TECH_TOC_CD"));
                    }
                    if (lclTZId != null) {
                        ZYPLocalTimeData lcl = convertTimeSys2Lcl(rqstRcvDt, null, lclTZId);
                        if (lcl != null) {
                            cratDt = convertTime2ViewText4Return(lcl.getTime());
                        }
                    } else {
                        String wkCratDt = convertTimeSys(rqstRcvDt, null);
                        cratDt = convertTime2ViewText4Return(wkCratDt);
                    }
                    techNM = resultSet.getString(NPAB164001Constant.TECH_NM);
                    rtlWhNm = resultSet.getString(NPAB164001Constant.SRC_RTL_WH_CD);
                    rtlSwhNm = resultSet.getString(NPAB164001Constant.SRC_RTL_SWH_CD);
                    emlAddr1 = resultSet.getString(NPAB164001Constant.EML_ADDR_1);
                    emlAddr2 = resultSet.getString(NPAB164001Constant.EML_ADDR_2);
                    sendList = new ArrayList<String[]>();
                }

                // get line data.
                String prchReqLineNum = resultSet.getString(NPAB164001Constant.PRCH_REQ_LINE_NUM);
                if (prchReqLineNum == null) {
                    prchReqLineNum = "";
                }
                BigDecimal prchReqLineSubNum = resultSet.getBigDecimal(NPAB164001Constant.PRCH_REQ_LINE_SUB_NUM);
                if (prchReqLineSubNum == null) {
                    prchReqLineSubNum = BigDecimal.ZERO;
                }
                String mdseCd = resultSet.getString(NPAB164001Constant.MDSE_CD);
                if (mdseCd == null) {
                    mdseCd = "";
                }
                BigDecimal prchReqQty = resultSet.getBigDecimal(NPAB164001Constant.PRCH_REQ_QTY);
                if (prchReqQty == null) {
                    prchReqQty = BigDecimal.ZERO;
                }
                String prchReqLineTpDescTxt = resultSet.getString(NPAB164001Constant.PRCH_REQ_LINE_TP_DESC_TXT);
                if (prchReqLineTpDescTxt == null) {
                    prchReqLineTpDescTxt = "";
                }
                String mdseDescShortTxt = resultSet.getString(NPAB164001Constant.MDSE_DESC_SHORT_TXT);
                if (mdseDescShortTxt == null) {
                    mdseDescShortTxt = "";
                }
                BigDecimal rwsPutAwayQty = resultSet.getBigDecimal(NPAB164001Constant.RWS_PUT_AWAY_QTY);
                if (rwsPutAwayQty == null) {
                    rwsPutAwayQty = BigDecimal.ZERO;
                }
                String destRtlWhCd = resultSet.getString(NPAB164001Constant.DEST_RTL_WH_CD);
                if (destRtlWhCd == null) {
                    destRtlWhCd = "";
                }
                String destRtlSwhCd = resultSet.getString(NPAB164001Constant.DEST_RTL_SWH_CD);
                if (destRtlSwhCd == null) {
                    destRtlSwhCd = "";
                }
                String rwsCloDtTmTs = resultSet.getString(NPAB164001Constant.RWS_CLO_DT_TM_TS);
                if (rwsCloDtTmTs == null) {
                    rwsCloDtTmTs = "";
                }
                // get msg line.
                String line = String.format(NPAB164001Constant.ML_FMT_RET, prchReqLineNum, prchReqLineSubNum.toString(), prchReqLineTpDescTxt, mdseCd, mdseDescShortTxt, prchReqQty.toString(), rwsPutAwayQty.toString(), destRtlWhCd,
                        destRtlSwhCd, rwsCloDtTmTs);
                sb.append(line);
                sb.append(System.getProperty(NPAB164001Constant.LINE_SEPARATOR));
                sendList.add(new String[] {prchReqNum, prchReqLineNum, prchReqLineSubNum.toString() });
            }
            if (prchReqNum != null) {
                if (ZYPCommonFunc.hasValue(emlAddr1) && ZYPCommonFunc.hasValue(emlAddr2)) {
                    // send mail.
                    sendMail4Return(emlAddr1, emlAddr2, sb, prchReqNum, techNM, rtlWhNm, rtlSwhNm, cratDt);
                    updateFlag4Return(sendList, false);
                    commitCount += sendList.size();
                } else {
                    updateFlag4Return(sendList, true);
                    errorCount += sendList.size();
                }
                commit();
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    private ZYPLocalTimeData convertTimeSys2Lcl(String date, String time, String lclTZId) {
        if (time == null) {
            time = "000000";
        } else if (time.length() == "000".length()) {
            time = "0" + time + "00";
        } else if (time.length() == "0000".length()) {
            time = time + "00";
        }
        date = date + time;
        if (date.length() != NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS.length()) {
            return null;
        }
//        return ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, date + time, NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS);
        return ZYPLocalTimeUtil.convertTimeSys2Lcl(lclTZId, date, NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS);
    }
    private String convertTimeSys(String date, String time) {
        if (time == null) {
            time = "000000";
        } else if (time.length() == "000".length()) {
            time = "0" + time + "00";
        } else if (time.length() == "0000".length()) {
            time = time + "00";
        }
        date = date + time;
        if (date.length() != NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS.length()) {
            return null;
        }
        return date;
    }

    private String convertTime2ViewText4Request(String time) {
        SimpleDateFormat sdf1 = null;
        if (time.length() == NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS.length()) {
            sdf1 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS);
        } else {
            sdf1 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSSSSS);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_MM_DD_YYYY_HH_MM);
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    private String convertTime2ViewText4Return(String time) {
        SimpleDateFormat sdf1 = null;
        if (time.length() == NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS.length()) {
            sdf1 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSS);
        } else {
            sdf1 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_YYYYMMDDHHMMSSSSS);
        }
        SimpleDateFormat sdf2 = new SimpleDateFormat(NPAB164001Constant.TIME_FORMAT_MM_DD_YYYY);
        try {
            return sdf2.format(sdf1.parse(time));
        } catch (ParseException e) {
            return null;
        }
    }

    private void sendMail4Request(String addr1, String addr2, StringBuilder msg, String prNum, String prTp, String techNM, String techWh, String techSwh, String requestDt) {
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB164001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        toAddrList.add(new S21MailAddress(globalCompanyCode, addr1));
        toAddrList.add(new S21MailAddress(globalCompanyCode, addr2));

        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB164001Constant.MAIL_TEMPLATE_ID_RQST);
        if (template == null) {
            rollback();
            throw new S21AbendException(NPAB164001Constant.NPAM0064E, new String[] {NPAB164001Constant.MAIL_TEMPLATE_ID_RQST });
        }
        // Set template parameter
        template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prNum);
        if (prTp != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_PRCH_REQ_TP_DESC_TXT, prTp);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_PRCH_REQ_TP_DESC_TXT, "");
        }
        if (techNM != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_TECH_NM, techNM);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_TECH_NM, "");
        }
        if (techWh != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_WH_NM, techWh);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_WH_NM, "");
        }
        if (techSwh != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_SWH_NM, techSwh);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_SWH_NM, "");
        }
        if (requestDt != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_DATE_REQ, requestDt);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_DATE_REQ, "");
        }
        template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_MESSAGE, msg.toString());

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB164001Constant.ML_LANG), NPAB164001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    private void sendMail4Return(String addr1, String addr2, StringBuilder msg, String prNum, String techNM, String techWh, String techSwh, String cratDt) {
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB164001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = new ArrayList<S21MailAddress>();
        toAddrList.add(new S21MailAddress(globalCompanyCode, addr1));
        toAddrList.add(new S21MailAddress(globalCompanyCode, addr2));

        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB164001Constant.MAIL_TEMPLATE_ID_RET);
        if (template == null) {
            rollback();
            throw new S21AbendException(NPAB164001Constant.NPAM0064E, new String[] {NPAB164001Constant.MAIL_TEMPLATE_ID_RET });
        }
        // Set template parameter
        template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_PRCH_REQ_NUM, prNum);
        if (techNM != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_TECH_NM, techNM);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_TECH_NM, "");
        }
        if (techWh != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_WH_NM, techWh);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_WH_NM, "");
        }
        if (techSwh != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_SWH_NM, techSwh);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_RTL_SWH_NM, "");
        }
        if (cratDt != null) {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_DATE_RET, cratDt);
        } else {
            template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_DATE_RET, "");
        }
        template.setTemplateParameter(NPAB164001Constant.EMAIL_PARAM_MESSAGE, msg.toString());

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB164001Constant.ML_LANG), NPAB164001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }

    private void updateFlag4Request(List<String[]> list, boolean isError) {
        for (int i = 0; i < list.size(); i++) {
            PRCH_REQ_DTLTMsg tMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, list.get(i)[0]);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineNum, list.get(i)[1]);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineSubNum, new BigDecimal(list.get(i)[2]));
            ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            tMsg = (PRCH_REQ_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (isError) {
                ZYPEZDItemValueSetter.setValue(tMsg.techRqstNtcMlProcCd, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_9);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.techRqstNtcMlProcCd, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_1);
            }
            S21FastTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NPAB164001Constant.NPAM1172E, new String[] {"PRCH_REQ_DTL" });
            }
        }
    }

    private void updateFlag4Return(List<String[]> list, boolean isError) {
        for (int i = 0; i < list.size(); i++) {
            PRCH_REQ_DTLTMsg tMsg = new PRCH_REQ_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqNum, list.get(i)[0]);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineNum, list.get(i)[1]);
            ZYPEZDItemValueSetter.setValue(tMsg.prchReqLineSubNum, new BigDecimal(list.get(i)[2]));
            ZYPEZDItemValueSetter.setValue(tMsg.ezCancelFlag, ZYPConstant.FLG_OFF_0);
            tMsg = (PRCH_REQ_DTLTMsg) S21FastTBLAccessor.findByKeyForUpdate(tMsg);
            if (isError) {
                ZYPEZDItemValueSetter.setValue(tMsg.techRtnNtcMlProcCd, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_9);
            } else {
                ZYPEZDItemValueSetter.setValue(tMsg.techRtnNtcMlProcCd, NPAB164001Constant.TECH_RQST_NTC_ML_PROC_CD_1);
            }
            S21FastTBLAccessor.update(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                rollback();
                throw new S21AbendException(NPAB164001Constant.NPAM1172E, new String[] {"PRCH_REQ_DTL" });
            }
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, commitCount, errorCount, commitCount + errorCount);
    }
    /**
     * getTechLocation
     * @param glblCmpyCd
     * @param rtlWhCd
     * @return
     */
    private Map<String, Object> getTechLocation(String glblCmpyCd, String rtlWhCd) {

        // 1st Step
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPCommonFunc.hasValue(tMsg.ctryCd) && ZYPCommonFunc.hasValue(tMsg.postCd)) {
            Map<String, Object> rslt = new HashMap<String, Object>();
            rslt.put("POST_CD", tMsg.postCd.getValue());
            rslt.put("CTRY_CD", tMsg.ctryCd.getValue());
            // Get Location Info!
            return rslt;
        }

//        // 2nd Step
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("techTocCd", rtlWhCd);
//        ssmParam.put("effFromDt", salesDate);
//        ssmParam.put("effThruDt", salesDate);
//        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getTechLocation", ssmParam);
//
//        if (rsltList != null && rsltList.size() > 0) {
//            // Get Location Info!
//            return rsltList.get(0);
//        }
//
        return null;
    }
    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB164001().executeBatch(NPAB164001.class.getSimpleName());
    }

}

/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NPA.NPAB144001;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CVI_ORD_STS_INFOTMsg;
import business.db.PO_ACK_DTL_WRKTMsg;
import business.db.PO_ACK_HDR_WRKTMsg;

import com.canon.cusa.s21.batch.NPA.NPAB144001.constant.NPAB144001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ACK_EDI_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 * <pre>
 * NPAB144001:Update PO Ack From CVI Sales Order Batch
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/31   CITS            Y.Nomura        Create          N/A
 *</pre>
 */
public class NPAB144001 extends S21BatchMain {
    /** Global Company Code */
    private String globalCompanyCode = null;

    /** User Profile Service */
    private S21UserProfileService profileService = null;

    /** Interface ID. */
    private String interfaceId = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** commit count */
    private int commitCount = 0;

    /** error count */
    private int errorCount = 0;

    /** fetchSize */
    private static final int FETCH_SIZE = 1000;

    @Override
    protected void initRoutine() {
        profileService = S21UserProfileServiceFactory.getInstance().getService();

        globalCompanyCode = profileService.getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(globalCompanyCode)) {
            throw new S21AbendException(NPAB144001Constant.NPAM1479E);
        }

        /** Parameter Check : Interface ID */
        interfaceId = getInterfaceID();
        if (!ZYPCommonFunc.hasValue(interfaceId)) {
            throw new S21AbendException(NPAB144001Constant.ZZXM0020E, new String[] {NPAB144001Constant.MSG_TXT_INTERFACE_ID });
        }
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            S21SsmLowLevelCodingClient ssmLlcClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
            HashMap<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(NPAB144001Constant.DB_PARAM_GLBL_CMPY_CD, globalCompanyCode);
            paramMap.put(NPAB144001Constant.DB_PARAM_PROC_STS_CD, PROC_STS.IN_COMPLETED);

            preparedStatement = ssmLlcClient.createPreparedStatement("getCviInfo", paramMap, getSsmParam());
            resultSet = preparedStatement.executeQuery();

            BigDecimal poAckHdrWrkPk = null;
            PO_ACK_HDR_WRKTMsg headerInfo = null;
            while (resultSet.next()) {
                boolean saveDetailFlag = true;
                String poAckLineStsCd = resultSet.getString(NPAB144001Constant.PO_ACK_LINE_STS_CD);
                if (!ZYPCommonFunc.hasValue(poAckLineStsCd)) {
                    // ACK Status is not found.
                    errorCount++;
                    saveDetailFlag = false;
                }
                String poNum = resultSet.getString(NPAB144001Constant.PO_ORD_NUM);
                if (saveDetailFlag && (!ZYPCommonFunc.hasValue(poNum))) {
                    errorCount++;
                    saveDetailFlag = false;
                }
                if (saveDetailFlag) {
                    if ((headerInfo != null) && (!poNum.equals(headerInfo.ediPoOrdNum.getValue()))) {
                        EZDTBLAccessor.insert(headerInfo);
                        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerInfo.getReturnCode())) {
                            errorProcess(S21MessageFunc.clspGetMessage(NPAB144001Constant.NPAM1172E, new String[] {"PO_ACK_HDR_WRK" }));
                        }
                        poAckHdrWrkPk = null;
                    }
                    if (poAckHdrWrkPk == null) {
                        poAckHdrWrkPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PO_ACK_HDR_WRK_SQ);
                    }
                    // detail
                    PO_ACK_DTL_WRKTMsg detailInfo = getAckDtl(poAckHdrWrkPk, poAckLineStsCd, resultSet);
                    EZDTBLAccessor.insert(detailInfo);
                    if (!EZDTBLAccessor.RTNCD_NORMAL.equals(detailInfo.getReturnCode())) {
                        errorProcess(S21MessageFunc.clspGetMessage(NPAB144001Constant.NPAM1172E, new String[] {"PO_ACK_DTL_WRK" }));
                    }
                    // header
                    headerInfo = getAckHdr(poAckHdrWrkPk, detailInfo);
                    commitCount++;
                }
                // update Status
                updateStatus(resultSet.getBigDecimal(NPAB144001Constant.CVI_ORD_STS_INFO_PK));
            }
            if (headerInfo != null) {
                EZDTBLAccessor.insert(headerInfo);
                if (!EZDTBLAccessor.RTNCD_NORMAL.equals(headerInfo.getReturnCode())) {
                    errorProcess(S21MessageFunc.clspGetMessage(NPAB144001Constant.NPAM1172E, new String[] {"PO_ACK_HDR_WRK" }));
                }
            }
            commit();
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(preparedStatement, resultSet);
        }
    }

    @Override
    protected void termRoutine() {
        // Set EndCode and CommitCount
        setTermState(termCd, commitCount, errorCount, commitCount + errorCount);
    }

    /**
     * main method
     * @param args String[]
     */
    public static void main(String[] args) {
        // Initialize S21BatchMain
        new NPAB144001().executeBatch(NPAB144001.class.getSimpleName());
    }

    private S21SsmExecutionParameter getSsmParam() {
        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execParam;
    }

    private PO_ACK_DTL_WRKTMsg getAckDtl(BigDecimal poAckHdrWrkPk, String poAckLineStsCd, ResultSet rs) throws SQLException {
        PO_ACK_DTL_WRKTMsg ret = new PO_ACK_DTL_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ret.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ret.poAckDtlWrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.PO_ACK_DTL_WRK_SQ));
        ZYPEZDItemValueSetter.setValue(ret.poOrdDtlSubLineNum, NPAB144001Constant.DEF_PO_SUB_NUM);
        ZYPEZDItemValueSetter.setValue(ret.shpgPlnDplyLineNum, rs.getString(NPAB144001Constant.PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.poAckLineStsCd, poAckLineStsCd);
        ZYPEZDItemValueSetter.setValue(ret.vndPoAckLineStsTxt, rs.getString(NPAB144001Constant.CVI_SHPG_STS_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(ret.mdseCd, rs.getString(NPAB144001Constant.SPLY_ITEM_NUM));
        ZYPEZDItemValueSetter.setValue(ret.mdseNm, rs.getString(NPAB144001Constant.MDSE_NM));
        ZYPEZDItemValueSetter.setValue(ret.trdPtnrSkuCd, rs.getString(NPAB144001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(ret.ordQty, rs.getBigDecimal(NPAB144001Constant.CVI_SHIP_QTY));
        ZYPEZDItemValueSetter.setValue(ret.thisMthFobCostAmt, rs.getBigDecimal(NPAB144001Constant.THIS_MTH_FOB_AMT));
        ZYPEZDItemValueSetter.setValue(ret.ccyCd, NPAB144001Constant.CCY_CD);
        ZYPEZDItemValueSetter.setValue(ret.etaDt, rs.getString(NPAB144001Constant.ETA_DT));

        String cviEtdDt = null;
        if (ZYPCommonFunc.hasValue(rs.getString(NPAB144001Constant.CVI_ETD_TS))) {
            cviEtdDt = ZYPDateUtil.DateFormatter(rs.getString(NPAB144001Constant.CVI_ETD_TS), NPAB144001Constant.DATE_FORMAT_SYSDATE, NPAB144001Constant.DATE_FORMAT_YYYYMMDD);
        }
        ZYPEZDItemValueSetter.setValue(ret.etdDt, cviEtdDt);

        ZYPEZDItemValueSetter.setValue(ret.vndCpoOrdNum, rs.getString(NPAB144001Constant.VND_ISS_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.origTrdPtnrSkuCd, rs.getString(NPAB144001Constant.MDSE_CD));
        ZYPEZDItemValueSetter.setValue(ret.origOrdQty, rs.getBigDecimal(NPAB144001Constant.CVI_ORD_QTY));
        ZYPEZDItemValueSetter.setValue(ret.origVndMdseCd, rs.getString(NPAB144001Constant.SPLY_ITEM_NUM));
        ZYPEZDItemValueSetter.setValue(ret.poAckHdrWrkPk, poAckHdrWrkPk);
        ZYPEZDItemValueSetter.setValue(ret.ediPoOrdNum, rs.getString(NPAB144001Constant.PO_ORD_NUM));
        ZYPEZDItemValueSetter.setValue(ret.ediPoOrdDtlLineNum, rs.getString(NPAB144001Constant.PO_DTL_LINE_NUM));
        ZYPEZDItemValueSetter.setValue(ret.vndShpgMethNm, rs.getString(NPAB144001Constant.CVI_SHIP_METH_DESC_TXT));
        ZYPEZDItemValueSetter.setValue(ret.proNum, rs.getString(NPAB144001Constant.CVI_BL_NUM));
        ZYPEZDItemValueSetter.setValue(ret.vndSoNum, rs.getString(NPAB144001Constant.CVI_DELY_NUM));
        ZYPEZDItemValueSetter.setValue(ret.vndSoSlpNum, rs.getString(NPAB144001Constant.CVI_DELY_LINE_NUM));
        return ret;
    }

    private PO_ACK_HDR_WRKTMsg getAckHdr(BigDecimal poAckHdrWrkPk, PO_ACK_DTL_WRKTMsg ackDtl) {
        PO_ACK_HDR_WRKTMsg ret = new PO_ACK_HDR_WRKTMsg();
        ZYPEZDItemValueSetter.setValue(ret.glblCmpyCd, this.globalCompanyCode);
        ZYPEZDItemValueSetter.setValue(ret.poAckHdrWrkPk, poAckHdrWrkPk);
        ZYPEZDItemValueSetter.setValue(ret.ackEdiProcStsCd, ACK_EDI_PROC_STS.SAVED);
        ZYPEZDItemValueSetter.setValue(ret.ediRcvDateTs, ZYPDateUtil.getCurrentSystemTime(NPAB144001Constant.DATE_FORMAT_SYSDATE));
        ZYPEZDItemValueSetter.setValue(ret.itrlIntfcId, interfaceId);
        ZYPEZDItemValueSetter.setValue(ret.vndCpoOrdNum, ackDtl.vndCpoOrdNum);
        ZYPEZDItemValueSetter.setValue(ret.poAckUpdProcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(ret.ediPoOrdNum, ackDtl.ediPoOrdNum);
        return ret;
    }

    private void updateStatus(BigDecimal pk) {
        CVI_ORD_STS_INFOTMsg tMsg = new CVI_ORD_STS_INFOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.cviOrdStsInfoPk, pk);
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, globalCompanyCode);
        tMsg = (CVI_ORD_STS_INFOTMsg) EZDTBLAccessor.findByKeyForUpdateWait(tMsg);
        ZYPEZDItemValueSetter.setValue(tMsg.procStsCd, PROC_STS.COMPLEATED);
        EZDTBLAccessor.update(tMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
            errorProcess(S21MessageFunc.clspGetMessage(NPAB144001Constant.NPAM1171E, new String[] {"CVI_ORD_STS_INFO" }));
        }
    }

    private void errorProcess(String message) {
        rollback();
        sendMail(message);
        commit();
        termCd = TERM_CD.ABNORMAL_END;
        throw new S21AbendException(message);
    }

    private void sendMail(String message) {
        S21Mail mail = new S21Mail(globalCompanyCode);
        // Get mail group
        S21MailGroup groupTo = new S21MailGroup(globalCompanyCode, NPAB144001Constant.MAIL_TO_GROUP_ID);
        // Get mail group
        S21MailGroup groupFrom = new S21MailGroup(globalCompanyCode, NPAB144001Constant.MAIL_FROM_GROUP_ID);

        // Set address
        List<S21MailAddress> toAddrList = groupTo.getMailAddress();
        if (toAddrList.isEmpty()) {
            throw new S21AbendException(NPAB144001Constant.NPZM0161E);
        }
        mail.setToAddressList(toAddrList);
        List<S21MailAddress> fromAddrList = groupFrom.getMailAddress();
        mail.setFromAddress(fromAddrList.get(0));

        S21MailTemplate template = new S21MailTemplate(globalCompanyCode, NPAB144001Constant.MAIL_TEMPLATE_ID);
        if ((template == null) || (!ZYPCommonFunc.hasValue(template.getBody()))) {
            throw new S21AbendException(NPAB144001Constant.NPZM0161E);
        }
        // Set template parameter
        SimpleDateFormat sdfDate = new SimpleDateFormat();
        sdfDate.applyPattern(NPAB144001Constant.DATE_FORMAT);

        template.setTemplateParameter(NPAB144001Constant.EMAIL_PARAM_BATCH_ID, NPAB144001Constant.BATCH_ID);
        template.setTemplateParameter(NPAB144001Constant.EMAIL_PARAM_ERR_DATE, sdfDate.format(new Date()));
        template.setTemplateParameter(NPAB144001Constant.EMAIL_PARAM_MESSAGE, message);

        // Set mail subject
        mail.setSubject(template.getSubject(NPAB144001Constant.ML_LANG), NPAB144001Constant.ML_LANG_CD);
        mail.setMailTemplate(template);

        // Post
        mail.postMail();
    }
}

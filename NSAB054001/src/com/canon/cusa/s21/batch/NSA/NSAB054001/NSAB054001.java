/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NSA.NSAB054001;

import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.BIZ_APP_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.COLON;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.COMMA;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.DS_MDL_EOL_STS_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.ERR_MSG_CRLF;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.ERR_MSG_SPACE;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MAIL_GRP_ID_FROM;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MAX_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MDL_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MDSE_CD;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MSG_ITEM_GLOBAL_COMPANY_CODE;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.MSG_ITEM_SALES_DATE;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.NSAM0178E;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.NSAM0469E;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.NSAM0470E;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.NSZM0392E;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.NZZM0003E;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.PERCENT;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.SET_MAIL_TEMPLATE_ID;
import static com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant.TXT_LENGTH_8;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;

import parts.common.EZDMsgArray;
import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MDL_EOLTMsg;
import business.db.MDSETMsg;
import business.db.MDSETMsgArray;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.SVC_BAT_ERR_LOGTMsg;

import com.canon.cusa.s21.batch.NSA.NSAB054001.constant.NSAB054001Constant;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;
import com.canon.cusa.s21.framework.security.helpers.S21SessionHelper;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfAttribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfKeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Attribute;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.Event;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.KeyReference;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.OPERATIONTYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.type.REFERENCETYPES;
import com.canon.usa.s21.integration.service.masterdata.messaging.wrapper.MasterDataMessagingServiceProxy;

/**
 * <pre>
 * Merchandise Item Status Update Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/19/2016   Hitachi         Y.Osawa          Create          N/A
 * 06/27/2016   Hitachi         M.Gotou          Update          QC#7886
 * </pre>
 */
public class NSAB054001 extends S21BatchMain {

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** System TimeStamp */
    private String sysTime = null;

    /** Sales Date */
    private String salesDate = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** Error Massage */
    private String errMsg = null;

    /** Error Message list */
    private List<String> errMsgList = new ArrayList<String>();

    /** Normal Count */
    private int normalCount;

    /** Error Count */
    private int errorCount;

    /** Termination Code */
    private TERM_CD termCd = null;

    /** Mail Template ID */
    private String mailTemplateId = null;

    @Override
    protected void initRoutine() {
        // Get GLBL_CMPY_CD
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSZM0392E, new String[] {MSG_ITEM_GLOBAL_COMPANY_CODE });
        }

        // Get Sales Date
        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(this.salesDate)) {
            this.termCd = TERM_CD.ABNORMAL_END;
            throw new S21AbendException(NSAM0178E, new String[] {MSG_ITEM_SALES_DATE });
        }

        // Mail Template
        this.mailTemplateId = SET_MAIL_TEMPLATE_ID;

        // initialize
        this.sysTime = ZYPDateUtil.getCurrentSystemTime(S21SessionHelper.EZD_TIME_FORMAT);

        this.termCd = TERM_CD.NORMAL_END;
        this.normalCount = 0;
        this.errorCount = 0;
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("glblCmpyCd", this.glblCmpyCd);
        paramMap.put("slsDt", this.salesDate);
        paramMap.put("maxDate", MAX_DATE);
        paramMap.put("mdseItemStsUpdProcFlg", ZYPConstant.FLG_OFF_N);

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(FETCH_SIZE_MAX);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);

        try {
            stmt = this.ssmLLClient.createPreparedStatement("getMerchandiseItem", paramMap, execParam);
            rs = stmt.executeQuery();

            String mdseCdBefore = null;
            String mdseCdCurrent = null;

            boolean updateChkFlg = false;

            ORD_TAKE_MDSETMsg otmTmsg = null;

            while (rs.next()) {

                mdseCdCurrent = rs.getString("PRNT_MDSE_CD");
                if (!updateChkFlg || mdseCdBefore == null || !mdseCdCurrent.equals(mdseCdBefore)) {
                    updateChkFlg = false;
                    otmTmsg = getOrderTakeMerchandise(mdseCdCurrent);
                    if (otmTmsg != null) {
                        if (!updateMdseItemOrderTake(otmTmsg.ordTakeMdseCd.getValue(), rs)) {
                            this.errorCount++;
                            rollback();
                            // insert SVC_BAT_ERR_LOG
                            errorProcess(mdseCdCurrent, rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
                            continue;
                        }
                    } else {
                        if (!updateMdseItem(mdseCdCurrent, rs)) {
                            this.errorCount++;
                            rollback();
                            // insert SVC_BAT_ERR_LOG
                            errorProcess(mdseCdCurrent, rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
                            continue;
                        }
                    }
                    updateChkFlg = true;
                } else {
                    DS_MDL_EOLTMsg dsMdlEolTMsg = getDsMdlEol(rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
                    if (dsMdlEolTMsg == null) {
                        this.errorCount++;
                        // insert SVC_BAT_ERR_LOG
                        errorProcess(mdseCdCurrent, rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
                        continue;
                    }
                    setValue(dsMdlEolTMsg.mdseItemStsUpdProcFlg, ZYPConstant.FLG_ON_Y);
                    S21FastTBLAccessor.update(dsMdlEolTMsg);
                    if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolTMsg.getReturnCode())) {
                        this.errorCount++;
                        StringBuilder msgBuf = new StringBuilder();
                        msgBuf.append(MDL_ID);
                        msgBuf.append(COLON);
                        msgBuf.append(rs.getBigDecimal("MDL_ID"));
                        msgBuf.append(COMMA);
                        msgBuf.append(DS_MDL_EOL_STS_CD);
                        msgBuf.append(COLON);
                        msgBuf.append(rs.getString("MDSE_ITEM_STS_CD"));
                        String errorMessage = msgBuf.toString();
                        addErrMsg(NSAM0470E, new String[] {"DS_MDL_EOL", errorMessage });
                        // insert SVC_BAT_ERR_LOG
                        errorProcess(mdseCdCurrent, rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
                        continue;
                    }
                }
                mdseCdBefore = mdseCdCurrent;

                commit();

                this.normalCount++;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rs);
        }
    }

    private ORD_TAKE_MDSETMsg getOrderTakeMerchandise(String mdseCd) {
        if (mdseCd.length() > TXT_LENGTH_8) {
            return null;
        }

        ORD_TAKE_MDSETMsg inMsg = new ORD_TAKE_MDSETMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.ordTakeMdseCd, mdseCd);

        ORD_TAKE_MDSETMsg outMsg = (ORD_TAKE_MDSETMsg) EZDTBLAccessor.findByKey(inMsg);

        if (outMsg == null) {
            return null;
        }
        return outMsg;
    }

    private boolean updateMdseItem(String mdseCd, ResultSet rs) {
        try {
            // get MDSE
            MDSETMsg mdseTMsg = getMdse(mdseCd);
            // get DS_MDL_EOL
            DS_MDL_EOLTMsg dsMdlEolTMsg = getDsMdlEol(rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));

            if (mdseTMsg == null) {
                return false;
            }
            if (dsMdlEolTMsg == null) {
                return false;
            }

            // update MDSE
            setValue(mdseTMsg.sellHldFlg, rs.getString("SELL_HLD_FLG"));
            setValue(mdseTMsg.dsctnFlg, rs.getString("DSCTN_FLG"));
            setValue(mdseTMsg.rgtnStsCd, rs.getString("RGTN_STS_CD"));
            setValue(mdseTMsg.mdseItemStsCd, rs.getString("MDSE_ITEM_STS_CD"));
            S21FastTBLAccessor.update(mdseTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mdseTMsg.getReturnCode())) {
                StringBuilder msgBuf = new StringBuilder();
                msgBuf.append(MDSE_CD);
                msgBuf.append(COLON);
                msgBuf.append(mdseCd);
                String errorMessage = msgBuf.toString();
                addErrMsg(NSAM0470E, new String[] {"MDSE", errorMessage });
                return false;
            }
            // add start 2016/06/27 CSA Defect#7886
            invokeMasterDataMessaging(mdseTMsg.ezUpTime.getValue(), mdseTMsg.mdseCd.getValue());
            // add end 2016/06/27 CSA Defect#7886

            // update DS_MDL_EOL
            setValue(dsMdlEolTMsg.mdseItemStsUpdProcFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(dsMdlEolTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolTMsg.getReturnCode())) {
                StringBuilder msgBuf = new StringBuilder();
                msgBuf.append(MDL_ID);
                msgBuf.append(COLON);
                msgBuf.append(rs.getBigDecimal("MDL_ID"));
                msgBuf.append(COMMA);
                msgBuf.append(DS_MDL_EOL_STS_CD);
                msgBuf.append(COLON);
                msgBuf.append(rs.getString("MDSE_ITEM_STS_CD"));
                String errorMessage = msgBuf.toString();
                addErrMsg(NSAM0470E, new String[] {"DS_MDL_EOL", errorMessage });
                return false;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return true;
    }

    private boolean updateMdseItemOrderTake(String mdseCd, ResultSet rs) {
        try {
            // get MDSE
            MDSETMsgArray mdseTMsgArray = getMdseOrderTake(mdseCd);
            if (!hasValidValue(mdseTMsgArray)) {
                return false;
            }

            // get DS_MDL_EOL
            DS_MDL_EOLTMsg dsMdlEolTMsg = getDsMdlEol(rs.getBigDecimal("MDL_ID"), rs.getString("DS_MDL_EOL_STS_CD"));
            if (dsMdlEolTMsg == null) {
                return false;
            }

            // update MDSE
            for (int i = 0; i < mdseTMsgArray.getValidCount(); i++) {
                setValue(mdseTMsgArray.no(i).sellHldFlg, rs.getString("SELL_HLD_FLG"));
                setValue(mdseTMsgArray.no(i).dsctnFlg, rs.getString("DSCTN_FLG"));
                setValue(mdseTMsgArray.no(i).rgtnStsCd, rs.getString("RGTN_STS_CD"));
                setValue(mdseTMsgArray.no(i).mdseItemStsCd, rs.getString("MDSE_ITEM_STS_CD"));
                S21FastTBLAccessor.update(mdseTMsgArray.no(i));
                if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(mdseTMsgArray.no(i).getReturnCode())) {
                    StringBuilder msgBuf = new StringBuilder();
                    msgBuf.append(MDSE_CD);
                    msgBuf.append(COLON);
                    msgBuf.append(mdseTMsgArray.no(i).mdseCd.getValue());
                    String errorMessage = msgBuf.toString();
                    addErrMsg(NSAM0470E, new String[] {"MDSE", errorMessage });
                    return false;
                }
                // add start 2016/06/27 CSA Defect#7886
                invokeMasterDataMessaging(mdseTMsgArray.no(i).ezUpTime.getValue(), mdseTMsgArray.no(i).mdseCd.getValue());
                // add end 2016/06/27 CSA Defect#7886
            }

            // update DS_MDL_EOL
            setValue(dsMdlEolTMsg.mdseItemStsUpdProcFlg, ZYPConstant.FLG_ON_Y);
            S21FastTBLAccessor.update(dsMdlEolTMsg);
            if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(dsMdlEolTMsg.getReturnCode())) {
                StringBuilder msgBuf = new StringBuilder();
                msgBuf.append(MDL_ID);
                msgBuf.append(COLON);
                msgBuf.append(rs.getBigDecimal("MDL_ID"));
                msgBuf.append(COMMA);
                msgBuf.append(DS_MDL_EOL_STS_CD);
                msgBuf.append(COLON);
                msgBuf.append(rs.getString("MDSE_ITEM_STS_CD"));
                String errorMessage = msgBuf.toString();
                addErrMsg(NSAM0470E, new String[] {"DS_MDL_EOL", errorMessage });
                return false;
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        }
        return true;
    }

    private MDSETMsg getMdse(String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.mdseCd, mdseCd);

        inMsg = (MDSETMsg) EZDTBLAccessor.findByKeyForUpdateWait(inMsg);

        if (inMsg == null) {
            addErrMsg(NZZM0003E, new String[] {"MDSE" });
            return null;
        }

        return inMsg;
    }

    private MDSETMsgArray getMdseOrderTake(String mdseCd) {
        MDSETMsg inMsg = new MDSETMsg();
        inMsg.setSQLID("080");
        inMsg.setConditionValue("glblCmpyCd01", this.glblCmpyCd);
        inMsg.setConditionValue("mdseCd01", mdseCd.concat(PERCENT));

        MDSETMsgArray mdseTMsgArray = (MDSETMsgArray) EZDTBLAccessor.findByConditionForUpdateWait(inMsg);

        if (!hasValidValue(mdseTMsgArray)) {
            addErrMsg(NZZM0003E, new String[] {"MDSE" });
            return null;
        }

        return mdseTMsgArray;
    }

    private DS_MDL_EOLTMsg getDsMdlEol(BigDecimal mdlId, String dsMdlEolStsCd) {

        DS_MDL_EOLTMsg dsMdlEolTMsg = new DS_MDL_EOLTMsg();
        setValue(dsMdlEolTMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(dsMdlEolTMsg.mdlId, mdlId);
        setValue(dsMdlEolTMsg.dsMdlEolStsCd, dsMdlEolStsCd);

        dsMdlEolTMsg = (DS_MDL_EOLTMsg) EZDTBLAccessor.findByKeyForUpdateWait(dsMdlEolTMsg);

        if (dsMdlEolTMsg == null) {
            addErrMsg(NZZM0003E, new String[] {"DS_MDL_EOL" });
            return null;
        }
        return dsMdlEolTMsg;
    }

    private void addErrMsg(String msgId, String... param) {
        this.errMsg = S21MessageFunc.clspGetMessage(msgId, param);
        if (!this.errMsgList.contains(this.errMsg)) {
            this.errMsgList.add(errMsg);
        }
    }

    private void errorProcess(String mdseCd, BigDecimal mdlId, String mdseEolStsCd) {
        this.termCd = TERM_CD.WARNING_END;

        SVC_BAT_ERR_LOGTMsg inMsg = new SVC_BAT_ERR_LOGTMsg();
        setValue(inMsg.glblCmpyCd, this.glblCmpyCd);
        setValue(inMsg.svcBatErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_BAT_ERR_LOG_SQ));
        setValue(inMsg.bizAppId, BIZ_APP_ID);
        setValue(inMsg.svcBatErrLogTs, this.sysTime);
        setValue(inMsg.svcBatErrKeyNum_01, mdseCd);
        setValue(inMsg.svcBatErrKeyNum_02, String.valueOf(mdlId));
        setValue(inMsg.svcBatErrKeyNum_03, mdseEolStsCd);
        setValue(inMsg.svcBatErrKeyNm_01, MDSE_CD);
        setValue(inMsg.svcBatErrKeyNm_02, MDL_ID);
        setValue(inMsg.svcBatErrKeyNm_03, DS_MDL_EOL_STS_CD);
        setValue(inMsg.svcBatErrMsgTxt, this.errMsg);
        S21FastTBLAccessor.insert(inMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(inMsg.getReturnCode())) {
            addErrMsg(NSAM0469E, new String[] {"SVC_BAT_ERR_LOG" });
        }
        commit();
    }

    private void postErrorMail() {

        // *****************************************************************
        // Deriving From Mail Address
        // *****************************************************************
        S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, MAIL_GRP_ID_FROM);
        List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

        if (addrFromList.size() <= 0) {
            return;
        }

        S21MailAddress from = addrFromList.get(0);

        // *****************************************************************
        // Deriving To Mail Address
        // *****************************************************************
        S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, BIZ_APP_ID);
        List<S21MailAddress> addrToList = groupTo.getMailAddress();

        if (addrToList.size() <= 0) {
            return;
        }

        // *****************************************************************
        // Create Mail Body
        // *****************************************************************
        S21MailTemplate template = new S21MailTemplate(glblCmpyCd, this.mailTemplateId);
        if (template == null) {
            return;
        }
        template.setTemplateParameter("batchId", this.getClass().getSimpleName());
        template.setTemplateParameter("errDate", ZYPDateUtil.formatEzd17ToDisp(this.sysTime));

        StringBuilder msgBuf = new StringBuilder();
        for (String tmpErrMsg : this.errMsgList) {
            msgBuf.append(tmpErrMsg);
            msgBuf.append(ERR_MSG_CRLF);
            msgBuf.append(ERR_MSG_SPACE);
        }
        String errorMessage = msgBuf.toString();

        template.setTemplateParameter("message", errorMessage);

        // *****************************************************************
        // Post mail
        // *****************************************************************
        S21Mail mail;
        for (S21MailAddress addr : addrToList) {
            mail = new S21Mail(this.glblCmpyCd);
            mail.setFromAddress(from);
            mail.setToAddress(addr);
            mail.setMailTemplate(template);
            mail.postMail();
        }
        return;
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
            // Send error mail
            postErrorMail();
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NSAB054001().executeBatch(NSAB054001.class.getSimpleName());
    }

    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {
        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
    }

    // add start 2016/06/27 CSA Defect#7886
    private static Logger logger = Logger.getLogger(NSAB054001.class);

    /**
    * invokeMasterDataMessaging
    * @param ezUpTime String
    * @param mdse String
    */
    public static void invokeMasterDataMessaging(String ezUpTime, String mdse) {

        try {
            Event event = new Event();
            event.setReferencedEntity(NSAB054001Constant.REF_ENTITY_ITEM);

            event.setOperationType(OPERATIONTYPES.UPDATE);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSSSS");
            Date formatDate = sdf.parse(ezUpTime);

            GregorianCalendar c = new GregorianCalendar();
            c.setTime(formatDate);
            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
            event.setCreateStamp(date2);

            event.setId(1);

            ArrayOfKeyReference ak = new ArrayOfKeyReference();

            // Set Key Model ID
            KeyReference kr = new KeyReference();
            kr.setType(REFERENCETYPES.TECHNICAL_MNEMONICAL);

            ArrayOfAttribute aa = new ArrayOfAttribute();
            Attribute a = new Attribute();
            a.setName(NSAB054001Constant.KEY_NAME_MDSE_CD);
            a.setValue(mdse);
            aa.getAttribute().add(a);
            kr.setAttributes(aa);

            ak.getKeyReference().add(kr);

            event.setKeyReferences(ak);

            ArrayOfEvent ae = new ArrayOfEvent();
            ae.getEvent().add(event);

            printRequest(ae);

            logger.debug("Proxy invocation starting");

            // Invoke the proxy
            new MasterDataMessagingServiceProxy().synchronizationMessage(ae);

            logger.debug("Proxy invocation complete");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    private static void printRequest(ArrayOfEvent ae) throws JAXBException {

        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ArrayOfEvent.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("com.canon.usa.s21.integration.service.masterdata.messaging.type.ArrayOfEvent", "ArrayOfEvent");
        JAXBElement<ArrayOfEvent> root = new JAXBElement<ArrayOfEvent>(qName, ArrayOfEvent.class, ae);

        jaxbMarshaller.marshal(root, stringWriter);

        String result = stringWriter.toString();

        logger.debug(result);
    }
    // add end 2016/06/27 CSA Defect#7886
}

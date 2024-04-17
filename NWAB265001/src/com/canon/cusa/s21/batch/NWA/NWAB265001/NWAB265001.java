package com.canon.cusa.s21.batch.NWA.NWAB265001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import parts.common.EZDMsg;
import parts.common.EZDTBigDecimalItem;
import parts.common.EZDTMsg;
import parts.common.EZDTStringItem;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CPO_SRC_TPTMsg;
import business.db.DS_IMPT_DTL_EXT_ATTRBTMsg;
import business.db.DS_IMPT_EXT_ATTRBTMsg;
import business.db.DS_IMPT_ORDTMsg;
import business.db.DS_IMPT_ORD_CONFIGTMsg;
import business.db.DS_IMPT_ORD_DTLTMsg;
import business.db.NWAI1200_01TMsg;
import business.db.NWAI1200_02TMsg;
import business.db.NWAI1200_03TMsg;
import business.db.NWAI1200_04TMsg;
import business.db.NWAI1200_05TMsg;
import business.db.NWAI1200_06TMsg;
import business.db.NWAI1200_09TMsg;
import business.db.NWAI1200_10TMsg;
import business.db.NWAI1200_11TMsg;
import business.db.NWAI1200_12TMsg;
import business.db.NWAI1200_13TMsg;
import business.db.NWAI1200_15TMsg;

import com.canon.cusa.s21.batch.NWA.NWAB265001.constant.NWAB265001constant;
import com.canon.cusa.s21.batch.NWA.NWAB265001.constant.NWAB265001constant.MSG_ID;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMPT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SYS_SRC;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.mail.S21Mail;
import com.canon.cusa.s21.framework.mail.S21MailAddress;
import com.canon.cusa.s21.framework.mail.S21MailGroup;
import com.canon.cusa.s21.framework.mail.S21MailTemplate;

/**
 * <pre>
 * EDI Order Import Batch
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * 07/06/2016   Fujitsu         T.Ishii         Update          S21_NA#9917
 * 07/14/2016   Fujitsu         T.Ishii         Update          S21_NA#11561
 * 10/13/2016   Fujitsu         T.Ishii         Update          S21_NA#15086
 * 11/29/2016   Fujitsu         S.Iidaka        Update          S21_NA#16266
 * 11/06/2017   Fujitsu         S.Ohki          Update          S21_NA#22153
 * 11/08/2017   Fujitsu         S.Ohki          Update          S21_NA#22274
 * 01/12/2018   Fujitsu         N.Sugiura       Update          S21_NA#22205
 * 01/15/2018   Fujitsu         Hd.Sugawara     Update          QC#22204
 * 01/23/2018   Fujitsu         M.Ohno          Update          QC#22776
 * 07/02/2018   Fujitsu         A.Kosai         Update          S21_NA#26908
 * 07/06/2018   Fujitsu         A.Kosai         Update          S21_NA#26936
 * 2018/12/25   Fujitsu         M.Yamada        Update          QC#29513
 * </pre>
 */
public class NWAB265001 extends S21BatchMain {

    /** SSM Batch Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

//    /** Sales Date */
//    private String salesDate = null;

    /** Mail Template ID */
    private String mailTemplateId = null;

    /** Header Bean List */
    private List<EdiOrdImptHdrBean> hdrBeanList = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWAB265001().executeBatch(NWAB265001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        writeStartLogLn("initRoutine");

        try {
            this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

            // Global Company Code
            this.glblCmpyCd = getGlobalCompanyCode();
            if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Global Company Code"));
            }

//            // Sales Date
//            this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);

            // Mail Template
            this.mailTemplateId = getUserVariable1();
            if (!ZYPCommonFunc.hasValue(this.mailTemplateId)) {
                throw new S21AbendException(MSG_ID.ZZZM9025E.toString(), toArray("Mail Template ID"));
            }

            this.hdrBeanList = new ArrayList<EdiOrdImptHdrBean>();

        } finally {
            writeEndLogLn("initRoutine");
        }
    }

    @Override
    protected void mainRoutine() {
        writeStartLogLn("mainRoutine");

        try {
            List<String> interfaceIdList = Arrays.asList(NWAB265001constant.INTERFACE_ID_MG, NWAB265001constant.INTERFACE_ID_ARC, NWAB265001constant.INTERFACE_ID_NCR, NWAB265001constant.INTERFACE_ID_JPMC);

            for (String itrcId : interfaceIdList) {
                registDsImptTable(itrcId);
            }
        } finally {
            writeEndLogLn("mainRoutine");
        }
    }

    @Override
    protected void termRoutine() {
        writeStartLogLn("termRoutine");

        try {
            int normalRecCnt = 0;
            int errRecCnt = 0;
            TERM_CD termCd = TERM_CD.NORMAL_END;

            if (hasValueList(this.hdrBeanList)) {
                for (EdiOrdImptHdrBean hdrBean : this.hdrBeanList) {
                    if (hdrBean.hasError()) {
                        postErrorMail(hdrBean);
                    }

                    if (hdrBean.hasError()) {
                        errRecCnt++;
                    } else {
                        normalRecCnt++;
                    }
                }

                if (errRecCnt > 0) {
                    termCd = TERM_CD.WARNING_END;
                }
            }

            setTermState(termCd, normalRecCnt, errRecCnt, normalRecCnt + errRecCnt);
        } finally {
            writeEndLogLn("termRoutine");
        }
    }

    private void postErrorMail(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("postErrorMail", hdrBean);

        try {

            // *****************************************************************
            // Deriving From Mail Address
            // *****************************************************************
            S21MailGroup groupFrom = new S21MailGroup(this.glblCmpyCd, NWAB265001constant.MAIL_GRP_ID_FROM);
            List<S21MailAddress> addrFromList = groupFrom.getMailAddress();

            if (!hasValueList(addrFromList)) {
                return;
            }

            S21MailAddress from = addrFromList.get(0);

            // *****************************************************************
            // Deriving To Mail Address
            // *****************************************************************
            S21MailGroup groupTo = new S21MailGroup(glblCmpyCd, NWAB265001constant.PROGRAM_ID);
            List<S21MailAddress> addrToList = groupTo.getMailAddress();

            if (!hasValueList(addrToList)) {
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
            template.setTemplateParameter("batchNm", "");
            String errMsg = hdrBean.getAllErrMsg();
            template.setTemplateParameter("message", errMsg);

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
        } finally {
            writeEndLogLn("postErrorMail", hdrBean);
        }
    }

    private void registDsImptTable(String interfaceId) {
        writeStartLogLn("registDsImptTable", interfaceId);

        try {
            // *****************************************************************
            // Deriving Transaction ID
            // *****************************************************************
            S21TransactionTableAccessor transactionTableAccessor = new S21TransactionTableAccessor();

            BigDecimal[] trxIdAry = transactionTableAccessor.getIntegrationRecord(interfaceId);

            if (trxIdAry == null || trxIdAry.length == 0) {
                return;
            }

            NWAI1200_01TMsg inTMsg = new NWAI1200_01TMsg();
            NWAI1200_01TMsg pkTMsg;
            List<?> itfcNWAI1200x01List, itfcNWAI1200x01ReGetList;
            Map<?, ?> itfcNWAI1200x01Map;

            // *****************************************************************
            // Deriving Auto Order Import Flag
            // *****************************************************************
            CPO_SRC_TPTMsg cpoSrcTMsg = new CPO_SRC_TPTMsg();
            ZYPEZDItemValueSetter.setValue(cpoSrcTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(cpoSrcTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            cpoSrcTMsg = (CPO_SRC_TPTMsg) S21FastTBLAccessor.findByKey(cpoSrcTMsg);
            if (cpoSrcTMsg != null) {
                EdiOrdImptHdrBean.autoOrdImptFlg = cpoSrcTMsg.autoOrdImptFlg.getValue();
            }

            ZYPEZDItemValueSetter.setValue(inTMsg.interfaceId, interfaceId);
            for (int i = 0; i < trxIdAry.length; i++) {
                // *************************************************************
                // Deriving NWAI1200_01
                // *************************************************************
                ZYPEZDItemValueSetter.setValue(inTMsg.transactionId, trxIdAry[i]);
                itfcNWAI1200x01List = getIntfHdrTMsgList(inTMsg, inTMsg.getTableName());

                if (!hasValueList(itfcNWAI1200x01List)) {
                    // S21_NA#15086 add start
                    transactionTableAccessor.endIntegrationProcess(inTMsg.interfaceId.getValue(), inTMsg.transactionId.getValue());
                    commit();
                    // S21_NA#15086 add end
                    continue;
                }

                for (int j = 0; j < itfcNWAI1200x01List.size(); j++) {
                    itfcNWAI1200x01Map = (Map<?, ?>) itfcNWAI1200x01List.get(j);

                    pkTMsg = new NWAI1200_01TMsg();

                    ZYPEZDItemValueSetter.setValue(pkTMsg.interfaceId, inTMsg.interfaceId);
                    ZYPEZDItemValueSetter.setValue(pkTMsg.transactionId, inTMsg.transactionId);
                    ZYPEZDItemValueSetter.setValue(pkTMsg.segmentId, convToBigDecimal(itfcNWAI1200x01Map.get("SEGMENT_ID")));
                    ZYPEZDItemValueSetter.setValue(pkTMsg.unitId, convToBigDecimal(itfcNWAI1200x01Map.get("UNIT_ID")));
                    ZYPEZDItemValueSetter.setValue(pkTMsg.seqNumber, convToBigDecimal(itfcNWAI1200x01Map.get("SEQ_NUMBER")));
                    ZYPEZDItemValueSetter.setValue(pkTMsg.idocPoDocNum, convToString(itfcNWAI1200x01Map.get("IDOC_PO_DOC_NUM")));

                    EdiOrdImptHdrBean hdrBean = registDsImptOrder(pkTMsg);
                    this.hdrBeanList.add(hdrBean);
                    if (!hdrBean.hasError()) {
                        commit();
                    } else {
                        rollback();
                    }
                }

                itfcNWAI1200x01ReGetList = getIntfHdrTMsgList(inTMsg, inTMsg.getTableName());
                // *****************************************************
                // Update Processed Flag
                // *****************************************************
                if (!hasValueList(itfcNWAI1200x01ReGetList)) {
                    transactionTableAccessor.endIntegrationProcess(inTMsg.interfaceId.getValue(), inTMsg.transactionId.getValue());
                    commit();
                }
            }
        } finally {
            writeEndLogLn("registDsImptTable", interfaceId);
        }
    }

    private EdiOrdImptHdrBean registDsImptOrder(NWAI1200_01TMsg pkTMsg) {
        writeStartLogLn("registImptOrd", pkTMsg);

        try {

            // *****************************************************************
            // create EDI Order Import Header Bean
            // *****************************************************************
            EdiOrdImptHdrBean hdrBean = createEdiOrdImptHdrBean(pkTMsg);

            if (hdrBean.hasError()) {
                return hdrBean;
            }

            // *****************************************************************
            // create EDI Order Import Detail Bean
            // *****************************************************************
            if (!createEdiOrdImptDtlBean(hdrBean)) {
                return hdrBean;
            }

            // *****************************************************************
            // Regist DS_IMPT_ORD / DS_IMPT_ORD_CONFIG /
            // DS_IMPT_EXT_ATTRB
            // *****************************************************************
            if (!registImptHdr(hdrBean)) {
                return hdrBean;
            }

            // *****************************************************************
            // Regist DS_IMPT_ORD_DTL / DS_IMPT_DTL_EXT_ATTRB
            // *****************************************************************
            for (EdiOrdImptDtlBean dtlBean : hdrBean.ordImptDtlBeanMap.values()) {
                if (!registImptDtl(dtlBean)) {
                    return hdrBean;
                }
            }

            // *****************************************************************
            // Remove Interface Data
            // *****************************************************************
            if (!removeInterfaceData(hdrBean)) {
                return hdrBean;
            }

            return hdrBean;
        } finally {
            writeEndLogLn("registImptOrd", pkTMsg);
        }
    }

    private List<?> getIntfHdrTMsgList(NWAI1200_01TMsg inTMsg, String tableNm) {
        writeStartLogLn("getIntfHdrTMsgList", inTMsg);

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("interfaceId", inTMsg.interfaceId.getValue());
            ssmParam.put("transactionId", inTMsg.transactionId.getValue());
            // 2018/07/06 S21_NA#26936 Add Start
            if (ZYPCommonFunc.hasValue(inTMsg.segmentId)) {
                ssmParam.put("segmentId", inTMsg.segmentId.getValue());
            }
            if (ZYPCommonFunc.hasValue(inTMsg.unitId)) {
                ssmParam.put("unitId", inTMsg.unitId.getValue());
            }
            // 2018/07/06 S21_NA#26936 Add End
            ssmParam.put("idocPoDocNum", inTMsg.idocPoDocNum.getValue());
            ssmParam.put("intfTable", tableNm);

            return (List<?>) ssmBatchClient.queryObjectList("getNWAI1200_HDR", ssmParam);

        } finally {
            writeEndLogLn("getIntfHdrTMsgList", inTMsg);
        }
    }

    private List<?> getIntfDtlTMsgList(NWAI1200_10TMsg inTMsg, String tableNm) {
        writeStartLogLn("getIntfDtlTMsgList", inTMsg);

        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();

            ssmParam.put("interfaceId", inTMsg.interfaceId.getValue());
            ssmParam.put("transactionId", inTMsg.transactionId.getValue());
            // 2018/07/06 S21_NA#26936 Add Start
            ssmParam.put("segmentId", inTMsg.segmentId.getValue());
            ssmParam.put("unitId", inTMsg.unitId.getValue());
            // 2018/07/06 S21_NA#26936 Add End
            ssmParam.put("idocPoDocNum", inTMsg.idocPoDocNum.getValue());
            ssmParam.put("idocPoDtlLineRefNum", inTMsg.idocPoDtlLineRefNum.getValue());
            ssmParam.put("intfTable", tableNm);

            return (List<?>) ssmBatchClient.queryObjectList("getNWAI1200_DTL", ssmParam);

        } finally {
            writeEndLogLn("getIntfDtlTMsgList", inTMsg);
        }
    }

    private EdiOrdImptHdrBean createEdiOrdImptHdrBean(NWAI1200_01TMsg pkTMsg) {
        writeStartLogLn("createEdiOrdImptHdrBean", pkTMsg);

        try {
            EdiOrdImptHdrBean hdrBean;
            NWAI1200_01TMsg find01Msg = (NWAI1200_01TMsg) findHdrInterfaceTMsgFromPk(null, pkTMsg);
            if (find01Msg == null) {
                NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(null, MSG_ID.NWAM0809E, pkTMsg.getTableName());

                hdrBean = new EdiOrdImptHdrBean(pkTMsg);
                hdrBean.errorInfoList.add(errorInfo);
                return hdrBean;
            }

            hdrBean = new EdiOrdImptHdrBean(find01Msg);

            List<EZDTMsg> findMsg;
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_02TMsg.class);
            hdrBean.addInterfaceTMsgList("02", findMsg);
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_03TMsg.class);
            hdrBean.addInterfaceTMsgList("03", findMsg);
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_04TMsg.class);
            hdrBean.addInterfaceTMsgList("04", findMsg);
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_05TMsg.class);
            hdrBean.addInterfaceTMsgList("05", findMsg);
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_06TMsg.class);
            hdrBean.addInterfaceTMsgList("06", findMsg);
            findMsg = findHdrInterfaceTMsgList(hdrBean, pkTMsg, NWAI1200_09TMsg.class);
            Collections.sort(findMsg, new Comparator<EZDTMsg>() {
                @Override
                public int compare(EZDTMsg o1, EZDTMsg o2) {
                    return ((NWAI1200_09TMsg) o1).idocPoNoteLineNum.getValue().compareTo(((NWAI1200_09TMsg) o2).idocPoNoteLineNum.getValue());
                }
            });
            hdrBean.addInterfaceTMsgList("09", findMsg);

            return hdrBean;
        } finally {
            writeEndLogLn("createEdiOrdImptHdrBean", pkTMsg);
        }
    }

    private boolean createEdiOrdImptDtlBean(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("createEdiOrdImptDtlBean", hdrBean);

        try {
            Map<?, ?> itfcNWAI1200x10Map;
            NWAI1200_10TMsg pkDtlTMsg, find10Msg;
            EdiOrdImptDtlBean dtlBean;

            // *************************************************************
            // Get NWAI1200_10
            // *************************************************************
            NWAI1200_10TMsg inDtlTMsg = new NWAI1200_10TMsg();

            ZYPEZDItemValueSetter.setValue(inDtlTMsg.interfaceId, hdrBean.srcTMsg.interfaceId);
            ZYPEZDItemValueSetter.setValue(inDtlTMsg.transactionId, hdrBean.srcTMsg.transactionId);
            // 2018/07/06 S21_NA#26936 Add Start
            ZYPEZDItemValueSetter.setValue(inDtlTMsg.segmentId, hdrBean.srcTMsg.segmentId);
            ZYPEZDItemValueSetter.setValue(inDtlTMsg.unitId, hdrBean.srcTMsg.unitId);
            // 2018/07/06 S21_NA#26936 Add End
            ZYPEZDItemValueSetter.setValue(inDtlTMsg.idocPoDocNum, hdrBean.srcTMsg.idocPoDocNum);

            List<?> itfcNWAI1200x10List = getIntfDtlTMsgList(inDtlTMsg, inDtlTMsg.getTableName());

            for (int k = 0; k < itfcNWAI1200x10List.size(); k++) {
                itfcNWAI1200x10Map = (Map<?, ?>) itfcNWAI1200x10List.get(k);

                // *************************************************************
                // Get NWAI1200_10 - 15
                // *************************************************************
                pkDtlTMsg = new NWAI1200_10TMsg();

                ZYPEZDItemValueSetter.setValue(pkDtlTMsg.interfaceId, hdrBean.srcTMsg.interfaceId);
                ZYPEZDItemValueSetter.setValue(pkDtlTMsg.transactionId, hdrBean.srcTMsg.transactionId);
                ZYPEZDItemValueSetter.setValue(pkDtlTMsg.segmentId, convToBigDecimal(itfcNWAI1200x10Map.get("SEGMENT_ID")));
                ZYPEZDItemValueSetter.setValue(pkDtlTMsg.unitId, convToBigDecimal(itfcNWAI1200x10Map.get("UNIT_ID")));
                ZYPEZDItemValueSetter.setValue(pkDtlTMsg.seqNumber, convToBigDecimal(itfcNWAI1200x10Map.get("SEQ_NUMBER")));

                find10Msg = (NWAI1200_10TMsg) findDtlInterfaceTMsgFromPk(null, pkDtlTMsg);
                if (find10Msg == null) {
                    dtlBean = new EdiOrdImptDtlBean(hdrBean, pkDtlTMsg);
                    NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(dtlBean, MSG_ID.NWAM0809E, pkDtlTMsg.getTableName());

                    dtlBean.errorInfoList.add(errorInfo);
                    return false;
                }

                dtlBean = new EdiOrdImptDtlBean(hdrBean, find10Msg);

                List<EZDTMsg> findMsg;
                findMsg = findDtlInterfaceTMsgList(dtlBean, pkDtlTMsg, NWAI1200_11TMsg.class);
                dtlBean.addInterfaceTMsgList("11", findMsg);
                findMsg = findDtlInterfaceTMsgList(dtlBean, pkDtlTMsg, NWAI1200_12TMsg.class);
                dtlBean.addInterfaceTMsgList("12", findMsg);
                findMsg = findDtlInterfaceTMsgList(dtlBean, pkDtlTMsg, NWAI1200_13TMsg.class);
                dtlBean.addInterfaceTMsgList("13", findMsg);
                findMsg = findDtlInterfaceTMsgList(dtlBean, pkDtlTMsg, NWAI1200_15TMsg.class);
                dtlBean.addInterfaceTMsgList("15", findMsg);
            }

            return true;
        } finally {
            writeEndLogLn("createEdiOrdImptDtlBean", hdrBean);
        }
    }

    private <T extends EZDTMsg> List<EZDTMsg> findHdrInterfaceTMsgList(EdiOrdImptHdrBean hdrBean, NWAI1200_01TMsg pkTMsg, Class<T> findTMsg) {
        writeStartLogLn("findHdrInterfaceTMsgList", pkTMsg);

        try {
            EZDTMsg itfcTMsg = null;

            try {
                itfcTMsg = findTMsg.newInstance();
            } catch (Exception e) {
            }

            String tableNm = itfcTMsg.getTableName();
            List<?> intfcList = getIntfHdrTMsgList(pkTMsg, tableNm);

            List<EZDTMsg> resultList = new ArrayList<EZDTMsg>();

            if (!hasValueList(intfcList)) {
//                NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(hdrBean, MSG_ID.NWAM0809E, tableNm);
//
//                hdrBean.errorInfoList.add(errorInfo);
                return resultList;
            }

            Map<?, ?> intfc;
            NWAI1200_01TMsg findPkTMsg = new NWAI1200_01TMsg();
            for (int i = 0; i < intfcList.size(); i++) {
                // Find Interface TMsg From PK.
                intfc = (Map<?, ?>) intfcList.get(i);
                ZYPEZDItemValueSetter.setValue(findPkTMsg.interfaceId, hdrBean.srcTMsg.interfaceId);
                ZYPEZDItemValueSetter.setValue(findPkTMsg.transactionId, hdrBean.srcTMsg.transactionId);
                ZYPEZDItemValueSetter.setValue(findPkTMsg.segmentId, convToBigDecimal(intfc.get("SEGMENT_ID")));
                ZYPEZDItemValueSetter.setValue(findPkTMsg.unitId, convToBigDecimal(intfc.get("UNIT_ID")));
                ZYPEZDItemValueSetter.setValue(findPkTMsg.seqNumber, convToBigDecimal(intfc.get("SEQ_NUMBER")));

                try {
                    itfcTMsg = findTMsg.newInstance();
                } catch (Exception e) {
                }
                EZDMsg.copy(findPkTMsg, null, itfcTMsg, null);

                itfcTMsg = findHdrInterfaceTMsgFromPk(hdrBean, itfcTMsg);

                resultList.add(itfcTMsg);
            }

            return resultList;
        } finally {
            writeEndLogLn("findHdrInterfaceTMsgList", hdrBean);
        }
    }

    private <T extends EZDTMsg> List<EZDTMsg> findDtlInterfaceTMsgList(EdiOrdImptDtlBean dtlBean, NWAI1200_10TMsg pkDtlTMsg, Class<T> findTMsg) {
        writeStartLogLn("findDtlInterfaceTMsgList", pkDtlTMsg);

        try {
            EZDTMsg itfcTMsg = null;

            try {
                itfcTMsg = findTMsg.newInstance();
            } catch (Exception e) {
            }

            String tableNm = itfcTMsg.getTableName();
            List<?> intfcList = getIntfDtlTMsgList(pkDtlTMsg, tableNm);

            List<EZDTMsg> resultList = new ArrayList<EZDTMsg>();

            if (!hasValueList(intfcList)) {
//                NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(dtlBean, MSG_ID.NWAM0809E, tableNm);
//
//                dtlBean.errorInfoList.add(errorInfo);
                return resultList;
            }

            Map<?, ?> intfc;
            NWAI1200_10TMsg findDtlPkTMsg = new NWAI1200_10TMsg();
            for (int i = 0; i < intfcList.size(); i++) {
                // Find Interface TMsg From PK.
                intfc = (Map<?, ?>) intfcList.get(i);
                ZYPEZDItemValueSetter.setValue(findDtlPkTMsg.interfaceId, dtlBean.srcTMsg.interfaceId);
                ZYPEZDItemValueSetter.setValue(findDtlPkTMsg.transactionId, dtlBean.srcTMsg.transactionId);
                ZYPEZDItemValueSetter.setValue(findDtlPkTMsg.segmentId, convToBigDecimal(intfc.get("SEGMENT_ID")));
                ZYPEZDItemValueSetter.setValue(findDtlPkTMsg.unitId, convToBigDecimal(intfc.get("UNIT_ID")));
                ZYPEZDItemValueSetter.setValue(findDtlPkTMsg.seqNumber, convToBigDecimal(intfc.get("SEQ_NUMBER")));
                try {
                    itfcTMsg = findTMsg.newInstance();
                } catch (Exception e) {
                }

                EZDMsg.copy(findDtlPkTMsg, null, itfcTMsg, null);

                itfcTMsg = findDtlInterfaceTMsgFromPk(dtlBean, itfcTMsg);

                resultList.add(itfcTMsg);
            }

            return resultList;
        } finally {
            writeEndLogLn("findDtlInterfaceTMsgList", dtlBean);
        }
    }

    private <T extends EZDTMsg> EZDTMsg findHdrInterfaceTMsgFromPk(EdiOrdImptHdrBean hdrBean, T findTMsg) {
        writeStartLogLn("findHdrInterfaceTMsgFromPk", hdrBean);

        try {

            EZDTMsg result = S21FastTBLAccessor.findByKey(findTMsg);

            if (!isNormalEnd(result) && hdrBean != null) {
                NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(hdrBean, MSG_ID.NWAM0809E, findTMsg.getTableName());

                hdrBean.errorInfoList.add(errorInfo);
            }

            return result;

        } finally {
            writeEndLogLn("findHdrInterfaceTMsgFromPk", hdrBean);
        }
    }

    private <T extends EZDTMsg> EZDTMsg findDtlInterfaceTMsgFromPk(EdiOrdImptDtlBean dtlBean, T findTMsg) {
        writeStartLogLn("findHdrInterfaceTMsgFromPk", dtlBean);

        try {

            EZDTMsg result = S21FastTBLAccessor.findByKey(findTMsg);

            if (result == null && dtlBean != null) {
                NWAB265001ErrorInfo errorInfo = new NWAB265001ErrorInfo(dtlBean, MSG_ID.NWAM0809E, findTMsg.getTableName());

                dtlBean.errorInfoList.add(errorInfo);
            }

            return result;

        } finally {
            writeEndLogLn("findHdrInterfaceTMsgFromPk", dtlBean);
        }
    }

    private boolean registImptHdr(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("registImptHdr", hdrBean);

        try {
            // *****************************************************************
            // Insert DS_IMPT_ORD
            // *****************************************************************
            if (!insertDsImptOrdCommon(hdrBean, true)) {
                return false;
            }

            // *****************************************************************
            // Insert DS_IMPT_ORD_CONFIG
            // *****************************************************************
            if (!insertDsImptOrdConfigCommon(hdrBean, true)) {
                return false;
            }

            // *****************************************************************
            // Insert DS_IMPT_EXT_ATTRB
            // *****************************************************************
            if (!insertDsImptExtAttrb(hdrBean)) {
                return false;
            }

            return true;

        } finally {
            writeEndLogLn("registImptHdr", hdrBean);
        }
    }

    private boolean insertDsImptOrdCommon(EdiOrdImptHdrBean hdrBean, boolean doExcute) {
        writeStartLogLn("insertDsImptOrdCommon", hdrBean);

        try {
            DS_IMPT_ORDTMsg ordTMsg = new DS_IMPT_ORDTMsg();
            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_SQ);

            ZYPEZDItemValueSetter.setValue(ordTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordTMsg.dsImptOrdPk, pk);
            ZYPEZDItemValueSetter.setValue(ordTMsg.cpoSrcTpCd, CPO_SRC_TP.EDI);
            ZYPEZDItemValueSetter.setValue(ordTMsg.ordSrcRefNum, hdrBean.getOrdSrcRefNum());
            ZYPEZDItemValueSetter.setValue(ordTMsg.ordSrcImptTs, ZYPDateUtil.getCurrentSystemTime(NWAB265001constant.YYYYMMDDHHMNSSFFF));
            String imptStsCd;
            if (ZYPConstant.FLG_ON_Y.equals(EdiOrdImptHdrBean.autoOrdImptFlg)) {
                imptStsCd = IMPT_STS.NOT_PROCESSED;
            } else if (ZYPConstant.FLG_ON_Y.equals(EdiOrdImptHdrBean.autoOrdImptFlg)) {
                imptStsCd = IMPT_STS.PENDING_OM_REVIEW;
            } else {
                imptStsCd = IMPT_STS.ERROR;
            }
            ZYPEZDItemValueSetter.setValue(ordTMsg.imptStsCd, imptStsCd);
            ZYPEZDItemValueSetter.setValue(ordTMsg.sysSrcCd, SYS_SRC.S21_ORDER);
            ZYPEZDItemValueSetter.setValue(ordTMsg.dropShipFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordTMsg.sendInvFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(ordTMsg.ordHdrEdtblFlg, ZYPConstant.FLG_ON_Y);
            // 2016/11/29 S21_NA#16266 Add Start
            ZYPEZDItemValueSetter.setValue(ordTMsg.maintOnlyFlg, ZYPConstant.FLG_OFF_N);
            // 2016/11/29 S21_NA#16266 Add End

            if (doExcute) {
                S21FastTBLAccessor.insert(ordTMsg);
                if (!isNormalEnd(ordTMsg)) {
                    NWAB265001ErrorInfo errorInfo = createDbAccessErr(hdrBean, ordTMsg, "Insert");

                    hdrBean.errorInfoList.add(errorInfo);
                    return false;
                }
            }

            hdrBean.dsImptOrdTMsg = ordTMsg;

            return true;

        } finally {
            writeEndLogLn("insertDsImptOrdCommon", hdrBean);
        }
    }

    private boolean insertDsImptOrdConfigCommon(EdiOrdImptHdrBean hdrBean, boolean doExcute) {
        writeStartLogLn("insertDsImptOrdConfigCommon", hdrBean);

        try {
            DS_IMPT_ORD_CONFIGTMsg configTMsg = new DS_IMPT_ORD_CONFIGTMsg();
            hdrBean.dsImptOrdConfigTMsg = configTMsg;
            DS_IMPT_ORDTMsg imptOrdTMsg = hdrBean.dsImptOrdTMsg;

            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_CONFIG_SQ);

            ZYPEZDItemValueSetter.setValue(configTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.dsImptOrdConfigPk, pk);
            ZYPEZDItemValueSetter.setValue(configTMsg.dsImptOrdPk, hdrBean.dsImptOrdTMsg.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(configTMsg.dsOrdPosnNum, "1");
            ZYPEZDItemValueSetter.setValue(configTMsg.configCatgCd, CONFIG_CATG.OUTBOUND);
            ZYPEZDItemValueSetter.setValue(configTMsg.configTpCd, CONFIG_TP.NEW);
            ZYPEZDItemValueSetter.setValue(configTMsg.billToCustAcctCd, imptOrdTMsg.billToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.billToCustLocCd, imptOrdTMsg.billToCustCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustAcctCd, imptOrdTMsg.shipToCustAcctCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCustLocCd, imptOrdTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.dropShipFlg, imptOrdTMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToLocNm, imptOrdTMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToAddlLocNm, imptOrdTMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstLineAddr, imptOrdTMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdLineAddr, imptOrdTMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToThirdLineAddr, imptOrdTMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFrthLineAddr, imptOrdTMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToFirstRefCmntTxt, imptOrdTMsg.shipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToScdRefCmntTxt, imptOrdTMsg.shipTo02RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtyAddr, imptOrdTMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToStCd, imptOrdTMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToProvNm, imptOrdTMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCntyNm, imptOrdTMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToPostCd, imptOrdTMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(configTMsg.shipToCtryCd, imptOrdTMsg.shipToCtryCd);

            if (doExcute) {
                S21FastTBLAccessor.insert(configTMsg);
                if (!isNormalEnd(configTMsg)) {
                    NWAB265001ErrorInfo errorInfo = createDbAccessErr(hdrBean, configTMsg, "Insert");

                    hdrBean.errorInfoList.add(errorInfo);

                    return false;
                }
            }

            return true;

        } finally {
            writeEndLogLn("insertDsImptOrdConfigCommon", hdrBean);
        }
    }

    private boolean insertDsImptExtAttrb(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrb", hdrBean);

        try {
            insertDsImptExtAttrbCommon(hdrBean);

            String interfaceId = hdrBean.srcTMsg.interfaceId.getValue();
            if (NWAB265001constant.INTERFACE_ID_ARC.equals(interfaceId)) {
                insertDsImptExtAttrbArc(hdrBean);
            } else if (NWAB265001constant.INTERFACE_ID_MG.equals(interfaceId)) {
                insertDsImptExtAttrbMg(hdrBean);
            } else if (NWAB265001constant.INTERFACE_ID_JPMC.equals(interfaceId)) {
                insertDsImptExtAttrbJpmc(hdrBean);
            } else if (NWAB265001constant.INTERFACE_ID_NCR.equals(interfaceId)) {
                insertDsImptExtAttrbNcr(hdrBean);
            }

            // Execute Insert DS_IMPT_EXT_ATTRB
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = hdrBean.dsExtAttrbTMsg;
            S21FastTBLAccessor.insert(extAttrbTMsg);
            if (!isNormalEnd(extAttrbTMsg)) {
                NWAB265001ErrorInfo errorInfo = createDbAccessErr(hdrBean, extAttrbTMsg, "Insert");
                hdrBean.errorInfoList.add(errorInfo);
                return false;
            }

            return true;

        } finally {
            writeEndLogLn("insertDsImptExtAttrb", hdrBean);
        }
    }

    private void insertDsImptExtAttrbCommon(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrbCommon", hdrBean);

        try {
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = new DS_IMPT_EXT_ATTRBTMsg();
            DS_IMPT_ORDTMsg imptOrdTMsg = hdrBean.dsImptOrdTMsg;
            hdrBean.dsExtAttrbTMsg = extAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptOrdPk, imptOrdTMsg.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.cpoSrcTpCd, imptOrdTMsg.cpoSrcTpCd);
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.ordSrcRefNum, hdrBean.srcTMsg.idocPoDocNum);
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.attrbCtrlId, "01");
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoDocNum, hdrBean.srcTMsg.idocPoDocNum);
//            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoRcpntPtnrNum, hdrBean.srcTMsg.idocPoRcpntPtnrNum); // QC#29513
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoRcpntPtnrNum, getPoRcpntPtnrNum(hdrBean)); // QC#29513

            List<EZDTMsg> intfcTMsgList = hdrBean.intfcTableMap.get("02");

            NWAI1200_02TMsg intfc02TMsg;
            String idocPoOrgTpCd;
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc02TMsg = (NWAI1200_02TMsg) tMsg;
                idocPoOrgTpCd = intfc02TMsg.idocPoOrgTpCd.getValue();
                if ("012".equals(idocPoOrgTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoOrgValTxt_01, intfc02TMsg.idocPoOrgValTxt);
                } else if ("ERR".equals(idocPoOrgTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoOrgValTxt_02, intfc02TMsg.idocPoOrgValTxt);
                }
            }

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoOrdRsnCd, hdrBean.srcTMsg.idocPoOrdRsnCd);

            NWAI1200_05TMsg intfc05TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("05");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc05TMsg = (NWAI1200_05TMsg) tMsg;
                if ("001".equals(intfc05TMsg.idocPoCustRefTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoCustRefNum, intfc05TMsg.idocPoCustRefNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoCustRefDt, intfc05TMsg.idocPoCustRefDt);
                }
            }
            NWAI1200_04TMsg intfc04TMsg;
            String idocPoPtnrTpCd;
            intfcTMsgList = hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;

                idocPoPtnrTpCd = intfc04TMsg.idocPoPtnrTpCd.getValue();
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AG.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_01, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_01, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_01, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_01, intfc04TMsg.idocPoPtnrId);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_01, intfc04TMsg.idocPtnrCtacNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_01, intfc04TMsg.idocPtnrTelNum);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_RE.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_02, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_02, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_02, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_02, intfc04TMsg.idocPoPtnrId);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_02, intfc04TMsg.idocPtnrCtacNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_02, intfc04TMsg.idocPtnrTelNum);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_WE.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_03, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_03, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_03, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_03, intfc04TMsg.idocPoPtnrId);
                    // Mod Start 2018/01/15 QC#22204
                    //ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_03, intfc04TMsg.idocPtnrCtacNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_03, intfc04TMsg.idocFirstPtnrNm);
                    // Mod End 2018/01/15 QC#22204
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_03, intfc04TMsg.idocPtnrTelNum);

                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocFirstLineAddr, intfc04TMsg.idocFirstLineAddr);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocScdLineAddr, intfc04TMsg.idocScdLineAddr);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtyNm, intfc04TMsg.idocPtnrCtyNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrStCd, intfc04TMsg.idocPtnrStCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrPostCd, intfc04TMsg.idocPtnrPostCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtryCd, intfc04TMsg.idocPtnrCtryCd);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_SP.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_04, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_04, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_04, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_04, intfc04TMsg.idocPoPtnrId);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_04, intfc04TMsg.idocPtnrCtacNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_04, intfc04TMsg.idocPtnrTelNum);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AP.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_05, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_05, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_05, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_05, intfc04TMsg.idocPoPtnrId);
                    // 2018/01/23 QC#22776 mod start
                    // ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_05, intfc04TMsg.idocPtnrCtacNm);
                    // 2018/07/02 S21_NA#26908 Mod Start
//                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_05, intfc04TMsg.idocFirstPtnrNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_05, getPtnrNm(intfc04TMsg));
                    // 2018/07/02 S21_NA#26908 Mod End
                    // 2018/01/23 QC#22776 mod end
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_05, intfc04TMsg.idocPtnrTelNum);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_YP.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_06, intfc04TMsg.idocPoPtnrTpCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_06, intfc04TMsg.idocPoPtnrNum);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_06, intfc04TMsg.idocPtnrCustRefTxt);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_06, intfc04TMsg.idocPoPtnrId);
                    // 2018/01/23 QC#22776 mod start
                    // ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_06, intfc04TMsg.idocPtnrCtacNm);
                    // 2018/07/02 S21_NA#26908 Mod Start
//                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_06, intfc04TMsg.idocFirstPtnrNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_06, getPtnrNm(intfc04TMsg));
                    // 2018/07/02 S21_NA#26908 Mod End
                    // 2018/01/23 QC#22776 mod end
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_06, intfc04TMsg.idocPtnrTelNum);
                }
            }

            NWAI1200_03TMsg intfc03TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("03");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc03TMsg = (NWAI1200_03TMsg) tMsg;
                if ("002".equals(intfc03TMsg.idocPoDtTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoDtValTxt, intfc03TMsg.idocPoDtValTxt);
                }
            }

            NWAI1200_06TMsg intfc06TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("06");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc06TMsg = (NWAI1200_06TMsg) tMsg;
                if ("001".equals(intfc06TMsg.idocPoDelyCondTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoDelyCondCd, intfc06TMsg.idocPoDelyCondCd);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoDelyCondNm, intfc06TMsg.idocPoDelyCondNm);
                }
            }

        } finally {
            writeEndLogLn("insertDsImptExtAttrbCommon", hdrBean);
        }
    }

    // QC#29513
    private String getPoRcpntPtnrNum(EdiOrdImptHdrBean hdrBean) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("intfcXrefCtxTp", INTFC_XREF_CTX_TP.ACK_PARTNER_NUMBER_MAPPING);
        ssmParam.put("srcAttrbTxt01", "MG");
        ssmParam.put("cpoSrcTpCd", CPO_SRC_TP.EDI);
        ssmParam.put("intfcId", hdrBean.srcTMsg.interfaceId.getValue());
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());

        return (String) ssmBatchClient.queryObject("getPoRcpntPtnrNum", ssmParam);
    }

    private void insertDsImptExtAttrbArc(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrbArc", hdrBean);

        try {
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = hdrBean.dsExtAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.ediTrdPtnrCd, "ARC");

            NWAI1200_04TMsg intfc04TMsg;
            String idocPoPtnrTpCd;
            List<EZDTMsg> intfcTMsgList = hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;

                idocPoPtnrTpCd = intfc04TMsg.idocPoPtnrTpCd.getValue();
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AG.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_02, intfc04TMsg.idocPtnrCustRefTxt);
                }
            }

            NWAI1200_09TMsg intfc09TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("09");
            String idocPoNoteLineTxt = "";
            String lineSeparator = System.getProperty("line.separator"); // 01/12/2018 S21_NA#22205 Add
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc09TMsg = (NWAI1200_09TMsg) tMsg;
                if ("ZZH3".equals(intfc09TMsg.idocPoNoteTpCd.getValue())) {
                    // 01/12/2018 S21_NA#22205 Mod Start
                    // idocPoNoteLineTxt += intfc09TMsg.idocPoNoteLineTxt.getValue();
                    if (ZYPCommonFunc.hasValue(idocPoNoteLineTxt)) {
                        idocPoNoteLineTxt = idocPoNoteLineTxt + lineSeparator + intfc09TMsg.idocPoNoteLineTxt.getValue();
                    } else {
                        idocPoNoteLineTxt = intfc09TMsg.idocPoNoteLineTxt.getValue();
                    }
                    // 01/12/2018 S21_NA#22205 Mod End
                }
            }
            if (idocPoNoteLineTxt.length() > 240) {
                idocPoNoteLineTxt = idocPoNoteLineTxt.substring(0, 240);
            }
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoNoteTxt, idocPoNoteLineTxt);

        } finally {
            writeEndLogLn("insertDsImptExtAttrbArc", hdrBean);
        }
    }

    private void insertDsImptExtAttrbMg(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrbMg", hdrBean);

        try {
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = hdrBean.dsExtAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.ediTrdPtnrCd, "MG");

            NWAI1200_04TMsg intfc04TMsg;
            String idocPoPtnrTpCd;
            List<EZDTMsg> intfcTMsgList = hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;

                idocPoPtnrTpCd = intfc04TMsg.idocPoPtnrTpCd.getValue();
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AP.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_01, intfc04TMsg.idocPtnrCustRefTxt);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AG.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_02, intfc04TMsg.idocPtnrCustRefTxt);
                }
            }

            NWAI1200_09TMsg intfc09TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("09");
            String idocPoNoteLineTxt = "";
            String lineSeparator = System.getProperty("line.separator"); // 01/12/2018 S21_NA#22205 Add
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc09TMsg = (NWAI1200_09TMsg) tMsg;
                if ("ZZH3".equals(intfc09TMsg.idocPoNoteTpCd.getValue())) {
                    // 01/12/2018 S21_NA#22205 Mod Start
                    // idocPoNoteLineTxt += intfc09TMsg.idocPoNoteLineTxt.getValue();
                    if (ZYPCommonFunc.hasValue(idocPoNoteLineTxt)) {
                        idocPoNoteLineTxt = idocPoNoteLineTxt + lineSeparator + intfc09TMsg.idocPoNoteLineTxt.getValue();
                    } else {
                        idocPoNoteLineTxt = intfc09TMsg.idocPoNoteLineTxt.getValue();
                    }
                    // 01/12/2018 S21_NA#22205 Mod End
                }
            }
            if (idocPoNoteLineTxt.length() > 240) {
                idocPoNoteLineTxt = idocPoNoteLineTxt.substring(0, 240);
            }
            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoNoteTxt, idocPoNoteLineTxt);

        } finally {
            writeEndLogLn("insertDsImptExtAttrbMg", hdrBean);
        }
    }

    private void insertDsImptExtAttrbJpmc(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrbJpmc", hdrBean);

        try {
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = hdrBean.dsExtAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.ediTrdPtnrCd, "JPMC");

        } finally {
            writeEndLogLn("insertDsImptExtAttrbJpmc", hdrBean);
        }
    }

    private void insertDsImptExtAttrbNcr(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("insertDsImptExtAttrbNcr", hdrBean);

        try {
            DS_IMPT_EXT_ATTRBTMsg extAttrbTMsg = hdrBean.dsExtAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(extAttrbTMsg.ediTrdPtnrCd, "NCR");

            NWAI1200_04TMsg intfc04TMsg;
            String idocPoPtnrTpCd;
            List<EZDTMsg> intfcTMsgList = hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;

                idocPoPtnrTpCd = intfc04TMsg.idocPoPtnrTpCd.getValue();
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_AG.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_01, intfc04TMsg.idocPtnrCtacNm);
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_02, intfc04TMsg.idocPtnrCustRefTxt);
                } else if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_ZG.equals(idocPoPtnrTpCd)) {
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.dsImptAttrbTxt_03, intfc04TMsg.idocPoPtnrId);
                }
            }

            NWAI1200_05TMsg intfc05TMsg;
            intfcTMsgList = hdrBean.intfcTableMap.get("05");
            String idocPoNoteLineTxt = "";
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc05TMsg = (NWAI1200_05TMsg) tMsg;
                if ("069".equals(intfc05TMsg.idocPoCustRefTpCd.getValue())) {
                    idocPoNoteLineTxt = String.format("USE %s ACCOUNT #: %s\nCustomer Engineer: %s", extAttrbTMsg.idocPoPtnrNum_04.getValue(), extAttrbTMsg.idocPoDelyCondNm.getValue(), intfc05TMsg.idocPoCustRefNum.getValue());
                    ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoNoteTxt, idocPoNoteLineTxt);
                }
            }

            // S21_NA#15561 add start
            for (EdiOrdImptDtlBean dtlBean : hdrBean.ordImptDtlBeanMap.values()) {

                intfcTMsgList = dtlBean.intfcTableMap.get("13");
                if (intfcTMsgList == null) {

                    continue;
                }
                for (EZDTMsg tMsg : intfcTMsgList) {

                    NWAI1200_13TMsg intfc13TMsg = (NWAI1200_13TMsg) tMsg;
                    if (intfc13TMsg == null) {

                        continue;
                    }
                    idocPoPtnrTpCd = intfc13TMsg.idocPoPtnrTpCd.getValue();
                    if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_WE.equals(idocPoPtnrTpCd)) {

                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrTpCd_03, intfc13TMsg.idocPoPtnrTpCd);
                        // 2017/11/06 S21_NA#22153 Mod Start
//                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_03, intfc13TMsg.idocPoPtnrNum);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_03, intfc13TMsg.idocPtnrUnldPntNm);
                        // 2017/11/06 S21_NA#22153 Mod End
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCustRefTxt_03, intfc13TMsg.idocPtnrCustRefTxt);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrId_03, intfc13TMsg.idocPoPtnrId);
                        // Mod Start 2018/01/15 QC#22204
                        //ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_03, intfc13TMsg.idocPtnrCtacNm);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtacNm_03, intfc13TMsg.idocFirstPtnrNm);
                        // Mod End 2018/01/15 QC#22204
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrTelNum_03, intfc13TMsg.idocPtnrTelNum);

                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocFirstLineAddr, intfc13TMsg.idocFirstLineAddr);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocScdLineAddr, intfc13TMsg.idocScdLineAddr);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtyNm, intfc13TMsg.idocPtnrCtyNm);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrStCd, intfc13TMsg.idocPtnrStCd);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrPostCd, intfc13TMsg.idocPtnrPostCd);
                        ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPtnrCtryCd, intfc13TMsg.idocPtnrCtryCd);
                    }
                    // 2017/11/08 S21_NA#22274 Add Start
                    if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_SP.equals(idocPoPtnrTpCd)) {
                    	ZYPEZDItemValueSetter.setValue(extAttrbTMsg.idocPoPtnrNum_04, intfc13TMsg.idocPoPtnrNum);
                    }
                    // 2017/11/08 S21_NA#22274 Add End
                }
            }
            // S21_NA#15561 add end

        } finally {
            writeEndLogLn("insertDsImptExtAttrbNcr", hdrBean);
        }
    }

    private boolean registImptDtl(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("registImptDtl", dtlBean);

        try {
            // *****************************************************************
            // Insert DS_IMPT_ORD_DTL
            // *****************************************************************
            if (!insertDsImptOrdDtlCommon(dtlBean, true)) {
                return false;
            }

            // *****************************************************************
            // Insert DS_IMPT_DTL_EXT_ATTRB
            // *****************************************************************
            if (!insertDsImptDtlExtAttrb(dtlBean)) {
                return false;
            }

            return true;

        } finally {
            writeEndLogLn("registImptDtl", dtlBean);
        }
    }

    private boolean insertDsImptOrdDtlCommon(EdiOrdImptDtlBean dtlBean, boolean doExcute) {
        writeStartLogLn("insertDsImptOrdDtlCommon", dtlBean);

        try {
            DS_IMPT_ORD_DTLTMsg ordDtlTMsg = new DS_IMPT_ORD_DTLTMsg();
            DS_IMPT_ORDTMsg ordTMsg = dtlBean.hdrBean.dsImptOrdTMsg;
            DS_IMPT_ORD_CONFIGTMsg configTMsg = dtlBean.hdrBean.dsImptOrdConfigTMsg;
            dtlBean.dsImptOrdDtlTMsg = ordDtlTMsg;

            BigDecimal pk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_IMPT_ORD_DTL_SQ);

            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dsImptOrdPk, ordTMsg.dsImptOrdPk);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dsImptOrdConfigPk, configTMsg.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dsImptOrdDtlPk, pk);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.ordSrcRefLineNum, dtlBean.getOrdSrcRefLineNum()); // S21_NA_#9917
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dropShipFlg, ordTMsg.dropShipFlg);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToCustCd, ordTMsg.shipToCustCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToLocNm, ordTMsg.shipToLocNm);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToAddlLocNm, ordTMsg.shipToAddlLocNm);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToFirstLineAddr, ordTMsg.shipToFirstLineAddr);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToScdLineAddr, ordTMsg.shipToScdLineAddr);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToThirdLineAddr, ordTMsg.shipToThirdLineAddr);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToFrthLineAddr, ordTMsg.shipToFrthLineAddr);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToFirstRefCmntTxt, ordTMsg.shipTo01RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToScdRefCmntTxt, ordTMsg.shipTo02RefCmntTxt);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToCtyAddr, ordTMsg.shipToCtyAddr);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToStCd, ordTMsg.shipToStCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToProvNm, ordTMsg.shipToProvNm);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToCntyNm, ordTMsg.shipToCntyNm);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToPostCd, ordTMsg.shipToPostCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.shipToCtryCd, ordTMsg.shipToCtryCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.prcCatgCd, ordTMsg.prcCatgCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.flPrcListCd, ordTMsg.flPrcListCd);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.rddDt, ordTMsg.rddDt);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.uomCpltFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.setItemShipCpltFlg, ZYPConstant.FLG_ON_Y);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dsOrdPosnNum, "1");
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.configItemFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.baseCmptFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.custIstlFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.dsImptOrdConfigPk, dtlBean.hdrBean.dsImptOrdConfigTMsg.dsImptOrdConfigPk);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.supdLockFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.imptLineFlg, ZYPConstant.FLG_ON_Y); // S21_NA#11561
            // 2016/11/29 S21_NA#16266 Add Start
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.preExistFlg, ZYPConstant.FLG_OFF_N);
            ZYPEZDItemValueSetter.setValue(ordDtlTMsg.finItemLineFlg, ZYPConstant.FLG_OFF_N);
            // 2016/11/29 S21_NA#16266 Add End

            if (doExcute) {
                S21FastTBLAccessor.insert(ordDtlTMsg);
                if (!isNormalEnd(ordDtlTMsg)) {
                    NWAB265001ErrorInfo errorInfo = createDbAccessErr(dtlBean, ordDtlTMsg, "Insert");

                    dtlBean.errorInfoList.add(errorInfo);

                    return false;
                }
            }

            return true;

        } finally {
            writeEndLogLn("insertDsImptOrdDtlCommon", dtlBean);
        }
    }

    private boolean insertDsImptDtlExtAttrb(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("insertDsImptDtlExtAttrb", dtlBean);

        try {
            insertDsImptDtlExtAttrbCommon(dtlBean);

            String interfaceId = dtlBean.srcTMsg.interfaceId.getValue();
            if (NWAB265001constant.INTERFACE_ID_ARC.equals(interfaceId)) {
                insertDsImptDtlExtAttrbArc(dtlBean);
            } else if (NWAB265001constant.INTERFACE_ID_MG.equals(interfaceId)) {
                insertDsImptDtlExtAttrbMg(dtlBean);
            } else if (NWAB265001constant.INTERFACE_ID_JPMC.equals(interfaceId)) {
                insertDsImptDtlExtAttrbJpmc(dtlBean);
            } else if (NWAB265001constant.INTERFACE_ID_NCR.equals(interfaceId)) {
                insertDsImptDtlExtAttrbNcr(dtlBean);
            }

            // Excute Insert DS_IMPT_DTL_EXT_ATTRB
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlExtAttrbTMsg = dtlBean.dsImptDtlExtAttrbTMsg;
            S21FastTBLAccessor.insert(dtlExtAttrbTMsg);
            if (!isNormalEnd(dtlExtAttrbTMsg)) {
                NWAB265001ErrorInfo errorInfo = createDbAccessErr(dtlBean, dtlExtAttrbTMsg, "Insert");
                dtlBean.errorInfoList.add(errorInfo);
                return false;
            }

            return true;

        } finally {
            writeEndLogLn("insertDsImptDtlExtAttrb", dtlBean);
        }
    }

    private void insertDsImptDtlExtAttrbCommon(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("insertDsImptDtlExtAttrbCommon", dtlBean);

        try {
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlExtAttrbTMsg = new DS_IMPT_DTL_EXT_ATTRBTMsg();
            DS_IMPT_ORD_DTLTMsg imptOrdDtlTMsg = dtlBean.dsImptOrdDtlTMsg;
            NWAI1200_10TMsg intfc10TMsg = dtlBean.srcTMsg;
            dtlBean.dsImptDtlExtAttrbTMsg = dtlExtAttrbTMsg;

            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptOrdDtlPk, imptOrdDtlTMsg.dsImptOrdDtlPk);
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptOrdPk, imptOrdDtlTMsg.dsImptOrdPk);

            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.ordSrcRefLineNum, intfc10TMsg.idocPoDtlLineRefNum);
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.attrbCtrlId, "01");
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_01, convToString(intfc10TMsg.idocPoDtlUnitPrcAmt.getValue()));
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_08, intfc10TMsg.idocPoDtlLineRefNum);

            NWAI1200_15TMsg intfc15TMsg;
            String idocPoDtlMatTpCd;
            List<EZDTMsg> intfcTMsgList = dtlBean.intfcTableMap.get("15");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc15TMsg = (NWAI1200_15TMsg) tMsg;
                idocPoDtlMatTpCd = intfc15TMsg.idocPoDtlMatTpCd.getValue();
                if ("001".equals(idocPoDtlMatTpCd)) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.idocPoDtlMatNum_01, intfc15TMsg.idocPoDtlMatNum);
                } else if ("002".equals(idocPoDtlMatTpCd)) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.idocPoDtlMatNum_02, intfc15TMsg.idocPoDtlMatNum);
                }
            }

            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.idocPoDtlOrdQty, intfc10TMsg.idocPoDtlOrdQty);
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.idocPoDtlUomCd, intfc10TMsg.idocPoDtlUomCd);
            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.idocPoDtlDelyPrtyNm, intfc10TMsg.idocPoDtlDelyPrtyNm);

        } finally {
            writeEndLogLn("insertDsImptDtlExtAttrbCommon", dtlBean);
        }
    }

    private void insertDsImptDtlExtAttrbArc(EdiOrdImptDtlBean dtlBean) {
        // Nothing
        // writeStartLogLn("insertDsImptDtlExtAttrbArc", dtlBean);
        //
        // try {
        // } finally {
        // writeEndLogLn("insertDsImptDtlExtAttrbArc", dtlBean);
        // }
    }

    private void insertDsImptDtlExtAttrbMg(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("insertDsImptDtlExtAttrbMg", dtlBean);

        try {
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlExtAttrbTMsg = dtlBean.dsImptDtlExtAttrbTMsg;

            NWAI1200_11TMsg intfc11TMsg;
            List<EZDTMsg> intfcTMsgList = dtlBean.intfcTableMap.get("11");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc11TMsg = (NWAI1200_11TMsg) tMsg;
                if ("044".equals(intfc11TMsg.idocPoCustRefTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_03, intfc11TMsg.idocPoCustRefNum);
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_04, intfc11TMsg.idocPoCustRefDt.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_05, intfc11TMsg.idocPoCustRefItemNum);
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_06, intfc11TMsg.idocPoCustRefTxt);
                }
            }

        } finally {
            writeEndLogLn("insertDsImptDtlExtAttrbMg", dtlBean);
        }
    }

    private void insertDsImptDtlExtAttrbJpmc(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("insertDsImptDtlExtAttrbJpmc", dtlBean);

        try {
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlExtAttrbTMsg = dtlBean.dsImptDtlExtAttrbTMsg;

            NWAI1200_04TMsg intfc04TMsg;
            List<EZDTMsg> intfcTMsgList = dtlBean.hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_WE.equals(intfc04TMsg.idocPoPtnrTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_02, intfc04TMsg.idocPoPtnrId);
                }
            }

            NWAI1200_11TMsg intfc11TMsg;
            intfcTMsgList = dtlBean.intfcTableMap.get("11");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc11TMsg = (NWAI1200_11TMsg) tMsg;
                if ("044".equals(intfc11TMsg.idocPoCustRefTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_03, intfc11TMsg.idocPoCustRefNum);
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_04, intfc11TMsg.idocPoCustRefDt.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_05, intfc11TMsg.idocPoCustRefItemNum);
                }
            }

        } finally {
            writeEndLogLn("insertDsImptDtlExtAttrbJpmc", dtlBean);
        }
    }

    private void insertDsImptDtlExtAttrbNcr(EdiOrdImptDtlBean dtlBean) {
        writeStartLogLn("insertDsImptDtlExtAttrbNcr", dtlBean);

        try {
            DS_IMPT_DTL_EXT_ATTRBTMsg dtlExtAttrbTMsg = dtlBean.dsImptDtlExtAttrbTMsg;

            NWAI1200_04TMsg intfc04TMsg;
            List<EZDTMsg> intfcTMsgList = dtlBean.hdrBean.intfcTableMap.get("04");
            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc04TMsg = (NWAI1200_04TMsg) tMsg;
                if (NWAB265001constant.IDOC_PO_PTNR_TP_CD_WE.equals(intfc04TMsg.idocPoPtnrTpCd.getValue())) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_02, intfc04TMsg.idocPtnrUnldPntNm);
                }
            }

            NWAI1200_11TMsg intfc11TMsg;
            intfcTMsgList = dtlBean.intfcTableMap.get("11");
            String idocPoCustRefTpCd, idocPoCustRefNum;
            String techNum = "";
            String invCode = "";
            String repCode = "";

            for (EZDTMsg tMsg : intfcTMsgList) {
                intfc11TMsg = (NWAI1200_11TMsg) tMsg;
                idocPoCustRefTpCd = intfc11TMsg.idocPoCustRefTpCd.getValue();
                idocPoCustRefNum = intfc11TMsg.idocPoCustRefNum.getValue();
                if ("044".equals(idocPoCustRefTpCd)) {
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_03, intfc11TMsg.idocPoCustRefNum);
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_04, intfc11TMsg.idocPoCustRefDt.getValue());
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_05, intfc11TMsg.idocPoCustRefItemNum);
                    ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_06, intfc11TMsg.idocPoCustRefTxt);
                } else if ("069".equals(idocPoCustRefTpCd)) {
                    techNum = String.format("Tech : %s\n", idocPoCustRefNum);
                } else if ("067".equals(idocPoCustRefTpCd)) {
                    invCode = String.format("Inv Code : %s\n", idocPoCustRefNum);
                } else if ("076".equals(idocPoCustRefTpCd)) {
                    repCode = String.format("Rep Code : %s\n", idocPoCustRefNum);
                }
            }

            ZYPEZDItemValueSetter.setValue(dtlExtAttrbTMsg.dsImptDtlAttrbTxt_07, techNum + invCode + repCode);

        } finally {
            writeEndLogLn("insertDsImptDtlExtAttrbNcr", dtlBean);
        }
    }

    private boolean removeInterfaceData(EdiOrdImptHdrBean hdrBean) {
        writeStartLogLn("removeInterfaceData", hdrBean);

        try {
            // *****************************************************************
            // Remove NWAI1200_01 - NWAI1200_10
            // *****************************************************************
            if (!removeItfcData(hdrBean, hdrBean.srcTMsg, new NWAI1200_01TMsg[1])) {
                return false;
            }

            List<EZDTMsg> intfcTMsgList = hdrBean.intfcTableMap.get("02");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_02TMsg[intfcTMsgList.size()])) {
                return false;
            }

            intfcTMsgList = hdrBean.intfcTableMap.get("03");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_03TMsg[intfcTMsgList.size()])) {
                return false;
            }

            intfcTMsgList = hdrBean.intfcTableMap.get("04");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_04TMsg[intfcTMsgList.size()])) {
                return false;
            }

            intfcTMsgList = hdrBean.intfcTableMap.get("05");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_05TMsg[intfcTMsgList.size()])) {
                return false;
            }

            intfcTMsgList = hdrBean.intfcTableMap.get("06");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_06TMsg[intfcTMsgList.size()])) {
                return false;
            }

            intfcTMsgList = hdrBean.intfcTableMap.get("09");
            if (!removeItfcData(hdrBean, intfcTMsgList, new NWAI1200_09TMsg[intfcTMsgList.size()])) {
                return false;
            }

            for (EdiOrdImptDtlBean dtlBean : hdrBean.ordImptDtlBeanMap.values()) {
                // *****************************************************************
                // Remove NWAI1200_10 - NWAI1200_15
                // *****************************************************************
                if (!removeItfcData(dtlBean, dtlBean.srcTMsg, new NWAI1200_10TMsg[1])) {
                    return false;
                }

                intfcTMsgList = dtlBean.intfcTableMap.get("11");
                if (!removeItfcData(dtlBean, intfcTMsgList, new NWAI1200_11TMsg[intfcTMsgList.size()])) {
                    return false;
                }

                intfcTMsgList = dtlBean.intfcTableMap.get("12");
                if (!removeItfcData(dtlBean, intfcTMsgList, new NWAI1200_12TMsg[intfcTMsgList.size()])) {
                    return false;
                }

                intfcTMsgList = dtlBean.intfcTableMap.get("13");
                if (!removeItfcData(dtlBean, intfcTMsgList, new NWAI1200_13TMsg[intfcTMsgList.size()])) {
                    return false;
                }

                intfcTMsgList = dtlBean.intfcTableMap.get("15");
                if (!removeItfcData(dtlBean, intfcTMsgList, new NWAI1200_15TMsg[intfcTMsgList.size()])) {
                    return false;
                }

            }

            return true;

        } finally {
            writeEndLogLn("removeInterfaceData", hdrBean);
        }
    }

    public <T extends EZDTMsg> boolean removeItfcData(EdiOrdImptBaseBean ediBean, EZDTMsg removeTMsg, T[] removeClass) {
        return removeItfcData(ediBean, Arrays.asList(removeTMsg), removeClass);
    }

    public <T extends EZDTMsg> boolean removeItfcData(EdiOrdImptBaseBean ediBean, List<EZDTMsg> removeTMsgList, T[] removeClass) {

        if (!hasValueList(removeTMsgList)) {
            return true;
        }

        int removeNum = S21FastTBLAccessor.removeLogical(removeTMsgList.toArray(removeClass));

        if (removeNum != removeTMsgList.size()) {
            NWAB265001ErrorInfo errorInfo = createDbAccessErr(ediBean, removeTMsgList.get(0), "remove");

            ediBean.errorInfoList.add(errorInfo);

            return false;
        }

        return true;
    }

    private static NWAB265001ErrorInfo createDbAccessErr(EdiOrdImptBaseBean ediBean, EZDTMsg tMsg, String operation) {
        return new NWAB265001ErrorInfo(ediBean, MSG_ID.NMAM8010E, tMsg.getTableName(), operation);
    }

    /**
     * Convert To BigDecimal
     * @param val Object
     * @return BigDecimal
     */
    public static BigDecimal convToBigDecimal(Object val) {
        if (null == val) {
            return null;
        } else if (val instanceof BigDecimal) {
            return (BigDecimal) val;
        } else if (val instanceof EZDBStringItem) {
            return convToBigDecimal(((EZDBStringItem) val).getValue());
        } else if (val instanceof EZDBBigDecimalItem) {
            return ((EZDBBigDecimalItem) val).getValue();
        } else if (val instanceof EZDCStringItem) {
            return convToBigDecimal(((EZDCStringItem) val).getValue());
        } else if (val instanceof EZDTStringItem) {
            return convToBigDecimal(((EZDTStringItem) val).getValue());
        } else if (val instanceof EZDCBigDecimalItem) {
            return ((EZDCBigDecimalItem) val).getValue();
        } else if (val instanceof EZDTBigDecimalItem) {
            return ((EZDTBigDecimalItem) val).getValue();
        } else {
            return new BigDecimal(val.toString());
        }
    }

    /**
     * Convert To String
     * @param val Object
     * @return String
     */
    public static String convToString(Object val) {
        return convToString(val, null);
    }


    /**
     * Convert To String
     * @param val Object
     * @param defVal String
     * @return String
     */
    public static String convToString(Object val, String defVal) {
        if (null == val) {
            return defVal;
        } else if (val instanceof BigDecimal) {
            return ((BigDecimal) val).toPlainString();
        } else {
            return val.toString();
        }
    }


    /**
     * Create Array.
     * @param <T>
     * @param param
     * @return
     */
    private static <T> T[] toArray(T... param) {
        return param;
    }

//    private static <T extends EZDMsgArray> boolean hasValidValue(T msgEzArray) {
//        return (msgEzArray != null && msgEzArray.getValidCount() > 0);
//    }

    private static <T extends List<?>> boolean hasValueList(T list) {
        return (list != null && list.size() > 0);
    }

    private static <T extends EZDTMsg> boolean isNormalEnd(T tMsg) {
        return EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode());
    }

    /**
     * writeStartLogLn
     * @param methodNm
     */
    private static void writeStartLogLn(String methodNm) {
        writeLogLn("[START] %s", methodNm);
    }

    private static void writeStartLogLn(String methodNm, Object target) {
        writeLogLn("[START] %s(%s)", methodNm, getTargetKey(target));
    }

    /**
     * writeEndLogLn
     * @param methodNm
     */
    private static void writeEndLogLn(String methodNm) {
        writeLogLn("[END] %s\r\n", methodNm);
    }

    private static void writeEndLogLn(String methodNm, Object target) {
        writeLogLn("[END] %s(%s)\r\n", methodNm, getTargetKey(target));
    }

    private static String getTargetKey(Object target) {
        StringBuilder sb = new StringBuilder();

        if (target == null) {
            sb.append("Target:null");
        } else if (target instanceof EdiOrdImptHdrBean) {
            EdiOrdImptHdrBean hdrBean = (EdiOrdImptHdrBean) target;
            sb.append(String.format("interfaceId:%s - transactionId:%.0f - idocPoDocNum:%s", hdrBean.srcTMsg.interfaceId.getValue(), hdrBean.srcTMsg.transactionId.getValue(), hdrBean.srcTMsg.idocPoDocNum.getValue()));
        } else if (target instanceof EdiOrdImptDtlBean) {
            EdiOrdImptDtlBean dtlBean = (EdiOrdImptDtlBean) target;
            sb.append(String.format("interfaceId:%s - transactionId:%.0f - idocPoDocNum:%s - idocPoDtlLineRefNum:%s", dtlBean.srcTMsg.interfaceId.getValue(), dtlBean.srcTMsg.transactionId.getValue(), dtlBean.srcTMsg.idocPoDocNum.getValue(), dtlBean.srcTMsg.idocPoDtlLineRefNum.getValue()));
        } else if (target instanceof NWAI1200_01TMsg) {
            NWAI1200_01TMsg tMsg = (NWAI1200_01TMsg) target;
            sb.append(String.format("interfaceId:%s - transactionId:%.0f - idocPoDocNum:%s", tMsg.interfaceId.getValue(), tMsg.transactionId.getValue(), tMsg.idocPoDocNum.getValue()));
        } else if (target instanceof NWAI1200_10TMsg) {
            NWAI1200_10TMsg tMsg = (NWAI1200_10TMsg) target;
            sb.append(String.format("interfaceId:%s - transactionId:%.0f - idocPoDocNum:%s - idocPoDtlLineRefNum:%s", tMsg.interfaceId.getValue(), tMsg.transactionId.getValue(), tMsg.idocPoDocNum.getValue(), tMsg.idocPoDtlLineRefNum.getValue()));
        } else {
            sb.append(String.format("Target:%s", target.toString()));
        }

        return sb.toString();
    }

    /**
     * WriteLogLn
     * @param format
     * @param param
     */
    private static void writeLogLn(String format, Object... param) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("[%s]", NWAB265001constant.PROGRAM_ID));

        if (param.length > 0) {
            sb.append(String.format(format, param));
        } else {
            sb.append(format);
        }

        S21InfoLogOutput.println(sb.toString());
    }

    // 2018/07/02 S21_NA#26908 Add Start
    private String getPtnrNm(NWAI1200_04TMsg intfc04TMsg) {

        if (ZYPCommonFunc.hasValue(intfc04TMsg.idocFirstPtnrNm)) {
            return intfc04TMsg.idocFirstPtnrNm.getValue();
        } else if (ZYPCommonFunc.hasValue(intfc04TMsg.idocScdPtnrNm)) {
            return intfc04TMsg.idocScdPtnrNm.getValue();
        } else if (ZYPCommonFunc.hasValue(intfc04TMsg.idocThirdPtnrNm)) {
            return intfc04TMsg.idocThirdPtnrNm.getValue();
        } else if (ZYPCommonFunc.hasValue(intfc04TMsg.idocFrthPtnrNm)) {
            return intfc04TMsg.idocFrthPtnrNm.getValue();
        } else {
            return null;
        }
    }
    // 2018/07/02 S21_NA#26908 Add End

}

/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWC.NWCB030001;

import static com.canon.cusa.s21.batch.NWC.NWCB030001.constant.NWCB030001Constant.*;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.db.DS_CPO_CTAC_PSNTMsg;
import business.db.DS_CPO_CTAC_PSNTMsgArray;
import business.db.INV_PRT_CTRLTMsg;
import business.db.NEAI0940_01TMsg;
import business.db.NEAI0940_02TMsg;
import business.db.NEAI0940_04TMsg;
import business.db.NEAI0940_05TMsg;
import business.db.NEAI0940_06TMsg;
import business.db.NEAI0940_10TMsg;
import business.db.NEAI0940_12TMsg;
import business.db.NEAI0940_13TMsg;
import business.db.NEAI0940_14TMsg;
import business.db.NEAI0940_15TMsg;
import business.db.NEAI0940_17TMsg;
import business.db.NEAI0940_19TMsg;
import business.db.NEAI0940_20TMsg;
import business.db.NEAI0940_21TMsg;
import business.db.NEAI0940_23TMsg;
import business.db.NEAI0940_24TMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Mail;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001MailSubstituteString;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CPO_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CTAC_PNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INTFC_XREF_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_CATG_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.integration.S21TransactionTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Invoice EDI Extract for SAP Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/01   Hitachi         K.Kojima        Create          N/A
 * 2016/06/02   SRAA            K.Aratani       Update          QC#9463
 * 2016/06/09   SRAA            K.Aratani       Update          QC#9763
 * 2016/09/29   SRAA            K.Aratani       Update          QC#14551
 * 2017/12/22   Fujitsu         Hd.Sugawara     Update          QC#22152
 * 2017/12/26   Fujitsu         N.Sugiura       Update          QC#22366
 * 2018/01/26   Fujitsu         A.Kosai         Update          QC#22718
 * 2018/04/23   Fujitsu         S.Ohki          Update          QC#25771,QC#25772
 * 2018/04/26   Fujitsu         S.Ohki          Update          QC#25815,QC#25816
 * 2018/05/08   Fujitsu         S.Ohki          Update          QC#25986
 * 2018/05/24   Fujitsu         T.Aoi           Update          QC#21841
 * 2018/06/13   Fujitsu         S.Ohki          Update          QC#26472
 * 2018/08/27   Fujitsu         K.Ishizuka      Update          QC#25001
 * 2018/09/07   Fujitsu         T.Aoi           Update          QC#28083
 * 2018/09/18   Fujitsu         S.Takami        Update          QC#28159
 * 2018/10/03   Fujitsu         S.Takami        Update          QC#27032
 * 2018/10/05   Fujitsu         K.Ishizuka      Update          QC#28548
 * 2018/12/25   Fujitsu         H.Kumagai       Update          QC#29517
 * 2019/01/10   Fujitsu         S.Ohki          Update          QC#29534
 * 2019/01/16   Fujitsu         T.Noguchi       Update          QC#29535
 * 2019/01/17   Fujitsu         T.Murai         Update          QC#29533
 * 2019/02/05   Fujitsu         T.Murai         Update          QC#30173,30175
 * 2019/02/06   Fujitsu         T.Murai         Update          QC#30187
 * 2019/02/12   Fujitsu         T.Murai         Update          QC#30316
 * 2019/02/18   Fujitsu         T.Murai         Update          QC#30385
 * 2019/02/27   Fujitsu         T.Murai         Update          QC#30559
 * 2019/04/19   Fujitsu         Y.Kanefusa      Update          QC#30973
 * 2019/05/10   Fujitsu         T.Murai         Update          QC#50326
 * 2019/11/12   Fujitsu         K.Kato          Update          QC#54139
 * </pre>
 */
public class NWCB030001 extends S21BatchMain {

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM-Client */
    private S21SsmBatchClient ssmBatchClient = null;

    /** termination code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Error Count */
    private int errorCount = 0;

    /** Normal Count */
    private int normalCount = 0;

    // Add Start 2017/12/22 QC#22152
    /** Sales Date */
    private String slsDt = null;
    // Add End 2017/12/22 QC#22152

    /** Transaction Id Map */
    private Map<String, BigDecimal> transactionIdMap = null;

    /** Unit Id Map */
    private Map<String, BigDecimal> unitIdMap = null;

    /** TMsgList : NEAI0940_01TMsg */
    private List<NEAI0940_01TMsg> tMsg01 = null;

    /** TMsgList : NEAI0940_02TMsg */
    private List<NEAI0940_02TMsg> tMsg02 = null;

    /** TMsgList : NEAI0940_04TMsg */
    private List<NEAI0940_04TMsg> tMsg04 = null;

    /** TMsgList : NEAI0940_05TMsg */
    private List<NEAI0940_05TMsg> tMsg05 = null;

    /** TMsgList : NEAI0940_06TMsg */
    private List<NEAI0940_06TMsg> tMsg06 = null;

    /** TMsgList : NEAI0940_10TMsg */
    private List<NEAI0940_10TMsg> tMsg10 = null;

    /** TMsgList : NEAI0940_12TMsg */
    private List<NEAI0940_12TMsg> tMsg12 = null;

    /** TMsgList : NEAI0940_13TMsg */
    private List<NEAI0940_13TMsg> tMsg13 = null;

    /** TMsgList : NEAI0940_14TMsg */
    private List<NEAI0940_14TMsg> tMsg14 = null;

    /** TMsgList : NEAI0940_15TMsg */
    private List<NEAI0940_15TMsg> tMsg15 = null;

    /** TMsgList : NEAI0940_17TMsg */
    private List<NEAI0940_17TMsg> tMsg17 = null;

    /** TMsgList : NEAI0940_19TMsg */
    private List<NEAI0940_19TMsg> tMsg19 = null;

    /** TMsgList : NEAI0940_20TMsg */
    private List<NEAI0940_20TMsg> tMsg20 = null;

    /** TMsgList : NEAI0940_21TMsg */
    private List<NEAI0940_21TMsg> tMsg21 = null;

    /** TMsgList : NEAI0940_23TMsg */
    private List<NEAI0940_23TMsg> tMsg23 = null;

    /** TMsgList : NEAI0940_24TMsg */
    private List<NEAI0940_24TMsg> tMsg24 = null;

    /** TMsgList : INV_PRT_CTRLTMsg */
    private List<INV_PRT_CTRLTMsg> invPrtCtrl = null;

    /** Error Mail Message List */
    private List<String> errMsgList = null;

    // 2018/10/03 QC#27032 Add Start
    /** PNTP Price Rule Header PK List */
    private List<BigDecimal> pntnPrcRuleHdrPkList = null;

    /** Special Handling Price Rule Header PK List */
    private List<BigDecimal> spclHdlgPrcRuleHdrPkList = null;
    // 2018/10/03 QC#27032 Add End
    @Override
    protected void initRoutine() {

        // "Global Company Code" Mandatory
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            throw new S21AbendException(NWCM0059E, new String[] {"Global Company Code" });
        }

        // Add Start 2017/12/22 QC#22152
        // Sales Date
        this.slsDt = ZYPDateUtil.getSalesDate();
        if (!ZYPCommonFunc.hasValue(this.slsDt)) {
            throw new S21AbendException(NWCM0059E, new String[] {"Sales Date" });
        }
        // Add End 2017/12/22 QC#22152

        // initialize
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.transactionIdMap = new HashMap<String, BigDecimal>();
        this.unitIdMap = new HashMap<String, BigDecimal>();
        this.errMsgList = new ArrayList<String>(1);
    }

    @Override
    protected void mainRoutine() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // Get EDI target data
            ps = getEDITargetData();
            rs = ps.executeQuery();

            while (rs.next()) {
                this.tMsg01 = new ArrayList<NEAI0940_01TMsg>(1);
                this.tMsg02 = new ArrayList<NEAI0940_02TMsg>(1);
                this.tMsg04 = new ArrayList<NEAI0940_04TMsg>(1);
                this.tMsg05 = new ArrayList<NEAI0940_05TMsg>(2);
                this.tMsg06 = new ArrayList<NEAI0940_06TMsg>(3);
                this.tMsg12 = new ArrayList<NEAI0940_12TMsg>(1);
                this.tMsg23 = new ArrayList<NEAI0940_23TMsg>(1);
                this.invPrtCtrl = new ArrayList<INV_PRT_CTRLTMsg>(1);

                BigDecimal seqNumber = BigDecimal.ONE;

                String dsEdiTrdPtnrCd = rs.getString(DS_EDI_TRD_PTNR_CD);
                // 2018/01/26 QC#22718 Add Start
                String sapEdiTrdPtnrCd = rs.getString(SAP_EDI_TRD_PTNR_CD);
                // 2018/01/26 QC#22718 Add End
                String invNum = rs.getString(INV_NUM);
                String invType = rs.getString(INV_TYPE);

                // Set interfaceId
                String interfaceId = rs.getString(INV_INTFC_ID);

                // Set transactionId
                BigDecimal transactionId = null;
                if (transactionIdMap.containsKey(interfaceId)) {
                    transactionId = transactionIdMap.get(interfaceId);
                } else {
                    S21TransactionTableAccessor accessor = new S21TransactionTableAccessor();
                    transactionId = accessor.getNextTransactionId();
                    transactionIdMap.put(interfaceId, transactionId);
                    accessor.createIntegrationRecordForBatch(interfaceId, transactionId);
                }

                // Set unitId
                BigDecimal unitId = null;
                if (unitIdMap.containsKey(interfaceId)) {
                    unitId = unitIdMap.get(interfaceId).add(BigDecimal.ONE);
                } else {
                    unitId = BigDecimal.ONE;
                }
                unitIdMap.put(interfaceId, unitId);

                // Get header data
                Map<String, Object> headerData = getHeaderData(invNum, dsEdiTrdPtnrCd);
                String invDt = (String) headerData.get(INV_DT);

                // Check EasyPack1
                boolean easyPack = false;
                if (TYPE_OM.equals(invType)) {
                    easyPack = isEasyPack(headerData);
                }

                // Create NEAI0940_01 data
                createNEAI094001(headerData, interfaceId, transactionId, unitId, seqNumber, invNum);
                seqNumber = seqNumber.add(BigDecimal.ONE);

                // Create NEAI0940_02 data
                // 2018/06/13 QC#26472 Mod Start
//                createNEAI094002(interfaceId, transactionId, unitId, seqNumber);
//                seqNumber = seqNumber.add(BigDecimal.ONE);
                if (DS_EDI_TRD_PTNR_CD_FEDEX.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_NOV.equals(dsEdiTrdPtnrCd)) {
                    createNEAI094002(interfaceId, transactionId, unitId, seqNumber);
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                }
                // 2018/06/13 QC#26472 Mod End

                // Create NEAI0940_04 data
                // 2018/05/08 QC#25986 Mod Start
//                createNEAI094004(headerData, interfaceId, transactionId, unitId, seqNumber);
                createNEAI094004(headerData, interfaceId, transactionId, unitId, seqNumber, dsEdiTrdPtnrCd);
                // 2018/05/08 QC#25986 Mod End
                seqNumber = seqNumber.add(BigDecimal.ONE);

                // Create NEAI0940_05 data
                createNEAI094005(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_REF_DOC_CD_INV, invNum);
                seqNumber = seqNumber.add(BigDecimal.ONE);
                createNEAI094005(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_REF_DOC_CD_CPO, (String) headerData.get(CUST_ISS_PO_NUM));
                seqNumber = seqNumber.add(BigDecimal.ONE);

                // Create NEAI0940_06 data
                createNEAI094006(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_DT_SEG_CD_BILL, invDt);
                seqNumber = seqNumber.add(BigDecimal.ONE);
                //QC#9763
                // 2018/04/23 QC#25771 Del Start
//                if (DS_EDI_TRD_PTNR_CD_FEDEX.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_NCR.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_NOV.equals(dsEdiTrdPtnrCd)) {
                // 2018/04/23 QC#25771 Del End
                //if (DS_EDI_TRD_PTNR_CD_JPMC.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_MG.equals(dsEdiTrdPtnrCd)) {
                    createNEAI094006(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_DT_SEG_CD_DOC, invDt);
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                // 2018/04/23 QC#25771 Del Start
//                }
                // 2018/04/23 QC#25771 Del End
                //QC#9763
                if (DS_EDI_TRD_PTNR_CD_JPMC.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_MG.equals(dsEdiTrdPtnrCd)) {
                //if (DS_EDI_TRD_PTNR_CD_FEDEX.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_NCR.equals(dsEdiTrdPtnrCd) || DS_EDI_TRD_PTNR_CD_NOV.equals(dsEdiTrdPtnrCd)) {
                    createNEAI094006(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_DT_SEG_CD_ORD, (String) headerData.get(CUST_ISS_PO_DT));
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                }

                // Create NEAI0940_10 data
                List<Map<String, Object>> paymentTermData = getPaymentTermData((String) headerData.get(PMT_TERM_CD), (String) headerData.get(NET_DUE_DT));
                this.tMsg10 = new ArrayList<NEAI0940_10TMsg>(paymentTermData.size());
                for (int i = 0; i < paymentTermData.size(); i++) {
                    createNEAI094010(interfaceId, transactionId, unitId, seqNumber, (BigDecimal) paymentTermData.get(i).get(PMT_TERM_AOT), (String) paymentTermData.get(i).get(PMT_TERM_TXT));
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                }

                // Create NEAI0940_12 data
                createNEAI094012(interfaceId, transactionId, unitId, seqNumber, (String) headerData.get(DS_INV_TP_CD));
                seqNumber = seqNumber.add(BigDecimal.ONE);

                // Get InvoiceLine data
                // 2018/01/26 QC#22718 Mod Start
                //List<Map<String, Object>> lineDataList = null;
                List<Map<String, Object>> lineDataList = new ArrayList<Map<String,Object>>();
                // 2018/01/26 QC#22718 Mod End
                if (TYPE_OM.equals(invType)) {
                    // 2018/01/26 QC#22718 Mod Start
//                    lineDataList = getLineDataOm(invNum, dsEdiTrdPtnrCd, easyPack);
                    lineDataList = getLineDataOm(invNum, sapEdiTrdPtnrCd, easyPack);
                    // 2018/01/26 QC#22718 Mod End
                } else if (TYPE_SERVICE.equals(invType) && DS_CONTR_CATG.FLEET.equals((String) headerData.get(DS_CONTR_CATG_CD))) {
                    // 2018/01/26 QC#22718 Mod Start
//                    lineDataList = getLineDataFleet(invNum, invDt, dsEdiTrdPtnrCd);
                    lineDataList = getLineDataFleet(invNum, invDt, sapEdiTrdPtnrCd);
                    // 2018/01/26 QC#22718 Mod End
                } else if (TYPE_SERVICE.equals(invType) && (DS_CONTR_CATG.REGULAR.equals((String) headerData.get(DS_CONTR_CATG_CD)) || DS_CONTR_CATG.AGGREGATE.equals((String) headerData.get(DS_CONTR_CATG_CD)))) {
                    // 2018/01/26 QC#22718 Mod Start
//                    lineDataList = getLineDataRegularOrAggregate(invNum, invDt, dsEdiTrdPtnrCd);
                    lineDataList = getLineDataRegularOrAggregate(invNum, invDt, sapEdiTrdPtnrCd);
                    // 2018/01/26 QC#22718 Mod End
                } else if (TYPE_SERVICE.equals(invType) && SVC_INV_SRC_TP.DISPATCH.equals((String) headerData.get(SVC_INV_SRC_TP_CD))) {
                    // 2018/01/26 QC#22718 Mod Start
//                    lineDataList = getLineDataDispatch(invNum, invDt, dsEdiTrdPtnrCd);
                    lineDataList = getLineDataDispatch(invNum, invDt, sapEdiTrdPtnrCd);
                    // 2018/01/26 QC#22718 Mod End
                }

                String invTpCd = (String) headerData.get(INV_TP_CD);

                // 2019/01/16 QC#29535 Add Start
                String telNum = "";
                // Dropship=Y DS_CPO_CTAC_PSN.CTAC_PSN_TEL_NUM
                if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, (String) headerData.get(ADD_DROP_SHIP_FLG))) {
                    DS_CPO_CTAC_PSNTMsg inTMsg = new DS_CPO_CTAC_PSNTMsg();
                    inTMsg.setSQLID("011");
                    inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    inTMsg.setConditionValue("cpoOrdNum01", (String) headerData.get(CPO_ORD_NUM));
                    inTMsg.setConditionValue("ctacPsnTpCd01", CTAC_TP.ORDER_CONTACT);
                    DS_CPO_CTAC_PSNTMsgArray tMsgArray = (DS_CPO_CTAC_PSNTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                    if (tMsgArray != null && 0 < tMsgArray.getValidCount()) {
                        telNum = tMsgArray.no(0).ctacPsnTelNum.getValue();
                    }
                }
                // Dropship=N SHIP_TO_CUST.TEL_NUM
                else {
                    SHIP_TO_CUSTTMsg inTMsg = new SHIP_TO_CUSTTMsg();
                    inTMsg.setSQLID("030");
                    inTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
                    inTMsg.setConditionValue("shipToCustCd01", (String) headerData.get(ADD_SHIP_TO_CUST_CD));
                    SHIP_TO_CUSTTMsgArray tMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(inTMsg);
                    if (tMsgArray != null && 0 < tMsgArray.getValidCount()) {
                        telNum = tMsgArray.no(0).telNum.getValue();
                    }
                }
                // 2019/01/16 QC#29535 Add End

                this.tMsg13 = new ArrayList<NEAI0940_13TMsg>(lineDataList.size());
                this.tMsg14 = new ArrayList<NEAI0940_14TMsg>(lineDataList.size());
                // Add Start QC#30559 2019/02/27
                boolean aggSummryFlg = Boolean.TRUE;
                // Add End QC#30559 2019/02/27
                this.tMsg15 = new ArrayList<NEAI0940_15TMsg>(lineDataList.size());
                this.tMsg17 = new ArrayList<NEAI0940_17TMsg>(lineDataList.size());
                this.tMsg19 = new ArrayList<NEAI0940_19TMsg>(lineDataList.size());
                this.tMsg20 = new ArrayList<NEAI0940_20TMsg>(lineDataList.size());
                this.tMsg21 = new ArrayList<NEAI0940_21TMsg>(lineDataList.size());
                this.tMsg24 = new ArrayList<NEAI0940_24TMsg>(lineDataList.size());  //QC#14551
                for (int i = 0; i < lineDataList.size(); i++) {
                    Map<String, Object> lineData = lineDataList.get(i);
                    String idocLineNum = createIdocLineNum(i + 1);

                    String invBolLineNum = (String) lineData.get(INV_BOL_LINE_NUM);
                    String invLineNum = (String) lineData.get(INV_LINE_NUM);
                    String invLineSubNum = (String) lineData.get(INV_LINE_SUB_NUM);
                    String invLineSubTrxNum = (String) lineData.get(INV_LINE_SUB_TRX_NUM);

                    // CUST_MDSE_XREF Search
                    Map<String, Object> custMdseXrefInfo = getCustMdseXrefInfo((String) lineData.get(INV_LINE_MDSE_CD), (String) headerData.get(SELL_TO_CUST_CD), invDt);
                    if (custMdseXrefInfo != null && (String) custMdseXrefInfo.get(CUST_MDSE_CD) != null && ((String) custMdseXrefInfo.get(CUST_MDSE_CD)).length() != 0) {
                        lineData.put(MDSE_CD, custMdseXrefInfo.get(CUST_MDSE_CD));
                        lineData.put(MDSE_NM, custMdseXrefInfo.get(CUST_MDSE_NM));
                    }

                    // Create NEAI0940_13 data
                    // 2018/04/26 QC#25815 Mod Start
//                    createNEAI094013(lineData, interfaceId, transactionId, unitId, seqNumber, idocLineNum, invTpCd);
                    String rtlWhCd = getRtlWhCd(invNum, invBolLineNum);
                    // 2018/10/05 S21_NA#28548 Add Start
                    if (!ZYPCommonFunc.hasValue(rtlWhCd) && DS_EDI_TRD_PTNR_CD_JPMC.equals(dsEdiTrdPtnrCd)) {
                        rtlWhCd = ZYPCodeDataUtil.getVarCharConstValue(IDOC_PLANT_CD_FOR_JPMC, this.glblCmpyCd);
                    }
                    // 2018/12/25 Mod Start QC#29517
                    // 2018/10/05 S21_NA#28548 Add End
//                    createNEAI094013(lineData, interfaceId, transactionId, unitId, seqNumber, idocLineNum, invTpCd, rtlWhCd);
                    createNEAI094013(lineData, interfaceId, transactionId, unitId, seqNumber, idocLineNum, invTpCd, rtlWhCd, invType);
                    // 2018/04/26 QC#25815 Mod End
                    // 2018/12/25 Mod End QC#29517
                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    // Create NEAI0940_14 data
                    if (TYPE_SERVICE.equals(invType) && SVC_INV_SRC_TP.CONTRACT.equals((String) headerData.get(SVC_INV_SRC_TP_CD))) {
                        // Add Start QC#30559 2019/02/27
                        String svcInvChrgTpCd = (String) lineData.get(SVC_INV_CHRG_TP_CD); // Add  2019/05/10 QC#50326
                        String lineRecTpCd = getIdocLineRecTpCd((String) lineData.get(SVC_INV_CHRG_TP_CD), (String) headerData.get(DS_CONTR_CATG_CD), (String) lineData.get(INV_PRINT_SVC_PGM_SRC_NM));

                        BigDecimal meterSummaryALL = BigDecimal.ZERO;
                        // Mod Start 2019/05/10 QC#50326
                        if (DS_CONTR_CATG.AGGREGATE.equals((String) headerData.get(DS_CONTR_CATG_CD)) && aggSummryFlg && SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
//                            if (DS_CONTR_CATG.AGGREGATE.equals((String) headerData.get(DS_CONTR_CATG_CD)) && aggSummryFlg && IDOC_LINE_REC_TP_CD_EXCESS_USAGE.equals(lineRecTpCd)) {
                        // Mod End 2019/05/10 QC#50326
                            meterSummaryALL = getMeterSummaryAGG(invNum);
                            aggSummryFlg = Boolean.FALSE;
                        }
                        // Add End QC#30559 2019/02/27
                        BigDecimal meterSummary = getMeterSummary(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum);
                        // Mod Start QC#30559 2019/02/27
                        // QC#30973 2019/04/19 Add Start
                        // createNEAI094014(lineData, interfaceId, transactionId, unitId, seqNumber, invTpCd, meterSummary, (String) headerData.get(DS_CONTR_CATG_CD), meterSummaryALL, lineRecTpCd);
                        // createNEAI094014(lineData, interfaceId, transactionId, unitId, seqNumber, invTpCd, meterSummary, (String) headerData.get(DS_CONTR_CATG_CD));
                        if (getInvLineXsMtrCnt(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum).compareTo(BigDecimal.ONE) > 0) {
                            createNEAI094014ForMultiTier(lineData, interfaceId, transactionId, unitId, seqNumber, invTpCd, lineRecTpCd);
                        } else {
                            createNEAI094014(lineData, interfaceId, transactionId, unitId, seqNumber, invTpCd, meterSummary, (String) headerData.get(DS_CONTR_CATG_CD), meterSummaryALL, lineRecTpCd);
                        }
                        // QC#30973 2019/04/19 Add End
                        // Mod End QC#30559 2019/02/27
                        seqNumber = seqNumber.add(BigDecimal.ONE);
                    }

                    // Create NEAI0940_15 data
                    if (TYPE_SERVICE.equals(invType)) {
                        List<Map<String, Object>> meterData = null;
                        if (DS_CONTR_CATG.FLEET.equals((String) headerData.get(DS_CONTR_CATG_CD))) {
                            meterData = getMeterDataFleet(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum);
                        } else if ((DS_CONTR_CATG.REGULAR.equals((String) headerData.get(DS_CONTR_CATG_CD)) || DS_CONTR_CATG.AGGREGATE.equals((String) headerData.get(DS_CONTR_CATG_CD)))) {
                            meterData = getMeterDataRegularOrAggregate(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum);
                        }
                        if (meterData != null) {
                            // 2019/01/10 QC#29534 Add Start
                            BigDecimal readMtrCntPrev = BigDecimal.ZERO;
                            BigDecimal readMtrCntOrig = BigDecimal.ZERO;
                            // 2019/01/10 QC#29534 Add End
                            for (int num15 = 0; num15 < meterData.size(); num15++) {
                                Map<String, Object> data = meterData.get(num15);
                              // 2019/01/10 QC#29534 Mod Start
//                              createNEAI094015(interfaceId, transactionId, unitId, seqNumber, (BigDecimal) data.get(PREV_TOT_COPY_QTY), (BigDecimal) data.get(TOT_COPY_QTY));
//                              seqNumber = seqNumber.add(BigDecimal.ONE);
                              readMtrCntPrev = readMtrCntPrev.add((BigDecimal) data.get(READ_MTR_CNT_PR));
                              readMtrCntOrig = readMtrCntOrig.add((BigDecimal) data.get(READ_MTR_CNT_OR));
                              // 2019/01/10 QC#29534 Mod End
                            }
                            // 2019/01/10 QC#29534 Add Start
                            createNEAI094015(interfaceId, transactionId, unitId, seqNumber, readMtrCntPrev, readMtrCntOrig);
                            seqNumber = seqNumber.add(BigDecimal.ONE);
                            // 2019/01/10 QC#29534 Add End
                        }
                    }

                    // Create NEAI0940_17 data
                    // 2018/04/23 QC#25772 Mod Start
//                    createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) headerData.get(CUST_ISS_PO_NUM), idocLineNum);
//                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    // 2019/02/06 QC#30187 Mod Start
                    // createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) headerData.get(CUST_ISS_PO_NUM), idocLineNum, IDOC_QLFY_REF_DOC_CD_CUST_PO);
                    createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) lineData.get(CUST_ISS_PO_NUM_L), idocLineNum, IDOC_QLFY_REF_DOC_CD_CUST_PO);
                    // 2019/02/06 QC#30187 Mod End
                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) headerData.get(CPO_ORD_NUM), idocLineNum, IDOC_QLFY_REF_DOC_CD_VND_ORD);
                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    // 2019/02/06 QC#30187 Mod Start
                    if (TYPE_SERVICE.equals(invType)) {
                        createNEAI094017(interfaceId, transactionId, unitId, seqNumber, null, idocLineNum, IDOC_QLFY_REF_DOC_CD_SHIP_TO_PO);
                    } else {
                        createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) lineData.get(CUST_ISS_PO_NUM_L), idocLineNum, IDOC_QLFY_REF_DOC_CD_SHIP_TO_PO);
                    }
//                  createNEAI094017(interfaceId, transactionId, unitId, seqNumber, (String) headerData.get(CUST_ISS_PO_NUM), idocLineNum, IDOC_QLFY_REF_DOC_CD_SHIP_TO_PO);
                    // 2019/02/06 QC#30187 Mod End
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                    // 2018/04/23 QC#25772 Mod End

                    // Create NEAI0940_19 data
                    createNEAI094019(interfaceId, transactionId, unitId, seqNumber, (String) lineData.get(MDSE_CD), (String) lineData.get(MDSE_NM));
                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    // Create NEAI0940_20 data
                    //QC#14551
                    //createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_GROSS_PRICE, (BigDecimal) lineData.get(DEAL_GRS_UNIT_PRC_AMT), invTpCd);
                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_NET_PRICE, (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), invTpCd); // 2018/10/03 QC#27032 Change Const Name
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                    //QC#14551
                    //createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_GROSS_VALUE, (BigDecimal) lineData.get(DEAL_GRS_TOT_PRC_AMT), invTpCd);
                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_NET_LINE_VALUE, (BigDecimal) lineData.get(INV_LINE_DEAL_NET_AMT), invTpCd); // 2018/10/03 QC#27032 Change Const Name
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                    //QC#14551
                    //createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_NET_VALUE, (BigDecimal) lineData.get(INV_LINE_DEAL_NET_AMT), invTpCd);
                    //seqNumber = seqNumber.add(BigDecimal.ONE);
                    // 2018/10/03 QC#27032 Del Start ZSF
//                    if (i == 0 && TYPE_OM.equals(invType)) {
//                        createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_HANDLING_CHARGE, (BigDecimal) lineData.get(SHIP_DEAL_HDLG_CHRG_AMT), invTpCd);
//                        seqNumber = seqNumber.add(BigDecimal.ONE);
//                    }
                    // 2018/10/03 QC#27032 Del End ZSF
                    // 2018/10/03 QC#27032 Add Start ZSF
                    if (TYPE_OM.equals(invType))  {
                        BigDecimal lineZsfAmt = getLineZsfSpecialChargeAmt(invNum, lineData);
                        createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_HANDLING_CHARGE, lineZsfAmt, invTpCd);
                        seqNumber = seqNumber.add(BigDecimal.ONE);
                    }
                    // 2018/10/03 QC#27032 Add End ZSF
                    // 2018/10/03 QC#27032 Del Start
//                    BigDecimal idocTotSmryAmtEdiTxt = (BigDecimal) lineData.get(INV_LINE_DEAL_TAX_AMT);
//                    if (i == 0 && TYPE_OM.equals(invType)) {
//                        BigDecimal invTotDealTaxAmt = (BigDecimal) lineData.get(FRT_DEAL_TAX_AMT);
//                        idocTotSmryAmtEdiTxt = add(idocTotSmryAmtEdiTxt, invTotDealTaxAmt);
//                    }
                    // 2018/10/03 QC#27032 Del End
                    // 2018/04/26 QC#25816 Add Start
                    // 2018/10/03 QC#27032 Del Start
//                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_NET_UNIT_PRC_AMT, (BigDecimal) lineData.get(FUNC_NET_UNIT_PRC_AMT), invTpCd);
                    // 2018/10/03 QC#27032 Del End
                    // 2018/10/03 QC#27032 Add Start
                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_NET_LINE_AMT, (BigDecimal) lineData.get(INV_LINE_DEAL_NET_AMT), invTpCd);
                    // 2018/10/03 QC#27032 Add End
                    seqNumber = seqNumber.add(BigDecimal.ONE);
                    // 2018/04/26 QC#25816 Add End
                    // 2018/10/03 QC#27032 Del Start
//                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_TOTAL_TAX_AMOUNT, idocTotSmryAmtEdiTxt, invTpCd);
                    // 2018/10/03 QC#27032 Del End
                    // 2018/10/03 QC#27032 Add Start
                    BigDecimal lineFrtAmt = getLineDealNetFrtAmt(invNum, lineData);
                    BigDecimal lineZutAmt = add((BigDecimal) lineData.get(INV_LINE_DEAL_NET_AMT), lineFrtAmt);
                    createNEAI094020(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_AMT_EDI_CD_LINE_TOTAL_AMOUNT, lineZutAmt, invTpCd);
                    // 2018/10/03 QC#27032 Add End
                    seqNumber = seqNumber.add(BigDecimal.ONE);

                    // Create NEAI0940_21 data
                    List<Map<String, Object>> shipDataList = null;
                    if (TYPE_OM.equals(invType)) {
                        shipDataList = getShipDataOm(invNum, invBolLineNum);
                    } else if (TYPE_SERVICE.equals(invType) && SVC_INV_SRC_TP.CONTRACT.equals((String) headerData.get(SVC_INV_SRC_TP_CD))) {
                        shipDataList = getShipDataContract(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum, invDt);
                    } else if (TYPE_SERVICE.equals(invType) && SVC_INV_SRC_TP.DISPATCH.equals((String) headerData.get(SVC_INV_SRC_TP_CD))) {
                        shipDataList = getShipDataDispatch(invNum, invBolLineNum, invLineNum, invLineSubNum, invLineSubTrxNum, invDt);
                    }
                    for (int num21 = 0; num21 < shipDataList.size(); num21++) {
                        Map<String, Object> shipData = shipDataList.get(num21);
                        // 2019/02/05 QC#30173 Add Start
                        if (TYPE_SERVICE.equals(invType)) {
                            telNum = substring((String) shipData.get(DS_CTAC_PNT_VAL_TXT_1), 0, 25);
                        }
                        // 2019/02/05 QC#30173 Add End
                        // 2019/01/16 QC#29535 Mod Start
                        //createNEAI094021(shipData, interfaceId, transactionId, unitId, seqNumber);
                        createNEAI094021(shipData, interfaceId, transactionId, unitId, seqNumber, telNum);
                        // 2019/01/16 QC#29535 Mod End
                        seqNumber = seqNumber.add(BigDecimal.ONE);
                    }
                    //QC#14551
                    if (DS_EDI_TRD_PTNR_CD_NCR.equals(dsEdiTrdPtnrCd)) {
                        // 2018/12/25 Mod Start QC#29517
//                        createNEAI094024(interfaceId, transactionId, unitId, seqNumber, IDOC_SRCRG_DISC_CD_POSITIVE, IDOC_COND_TP_CD_CSTP, IDOC_COND_TP_NM_COST_BASED,
//                                (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), (String) lineData.get(UOM_CD));
                        createNEAI094024(interfaceId, transactionId, unitId, seqNumber, IDOC_SRCRG_DISC_CD_POSITIVE, IDOC_COND_TP_CD_CSTP, IDOC_COND_TP_NM_COST_BASED,
                                (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), (String) lineData.get(UOM_CD), invType);
                        // 2018/12/25 Mod End QC#29517
                        seqNumber = seqNumber.add(BigDecimal.ONE);
                    }
                }

                // Create NEAI0940_23 data
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_TOTAL_SALES_TAX, (BigDecimal) headerData.get(INV_TOT_DEAL_TAX_AMT), invTpCd);
                seqNumber = seqNumber.add(BigDecimal.ONE);
                // 2018/10/03 QC#27032 Del Start ZSF.
//                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_HANDLING_CHARGE, (BigDecimal) headerData.get(SHIP_DEAL_HDLG_CHRG_AMT), invTpCd);
                // 2018/10/03 QC#27032 Del End ZSF.
                // 2018/10/03 QC#27032 Add Start ZSF.
                BigDecimal hdrZsfAmt = getHdrZsfSpecialChargeAmt(invNum);
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_HANDLING_CHARGE, hdrZsfAmt, invTpCd);
                // 2018/10/03 QC#27032 Add End ZSF.
                seqNumber = seqNumber.add(BigDecimal.ONE);
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_FREIGHT, (BigDecimal) headerData.get(SHIP_DEAL_FRT_AMT), invTpCd);
                seqNumber = seqNumber.add(BigDecimal.ONE);
                // 2018/10/03 QC#27032 Del Start
//                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_INVOICE_SUB_TOTAL, add((BigDecimal) headerData.get(INV_TOT_DEAL_SLS_AMT), (BigDecimal) headerData.get(INV_TOT_DEAL_DISC_AMT)), invTpCd);
                // 2018/10/03 QC#27032 Del End
                // 2018/10/03 QC#27032 Add Start
                BigDecimal pntpNetAmount = getPntpNetAmount(invNum);
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_INVOICE_SUB_TOTAL, pntpNetAmount, invTpCd);
                // 2018/10/03 QC#27032 Add End
                seqNumber = seqNumber.add(BigDecimal.ONE);
                // 2018/10/03 QC#27032 Mod Start E2EDS01:ZUT
//                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_TOTAL_TAX_AMONT, (BigDecimal) headerData.get(INV_TOT_DEAL_TAX_AMT), invTpCd);
                // Total amount Befor Tax (Deal Net Unit Price * qty + Freight)
                BigDecimal zutAmt = subtract((BigDecimal) headerData.get(INV_TOT_DEAL_NET_AMT), (BigDecimal) headerData.get(INV_TOT_DEAL_TAX_AMT));
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_TOTAL_TAX_AMONT, zutAmt, invTpCd);
                // 2018/10/03 QC#27032 Mod Start E2EDS01:ZUT
                seqNumber = seqNumber.add(BigDecimal.ONE);
                createNEAI094023(interfaceId, transactionId, unitId, seqNumber, IDOC_QLFY_TOT_SEG_CD_INVOICE_TOTAL, (BigDecimal) headerData.get(INV_TOT_DEAL_NET_AMT), invTpCd);
                seqNumber = seqNumber.add(BigDecimal.ONE);

                // Update INV_PRT_CTRL data
                List<Map<String, Object>> updateTarget = getUpdateTarget(invNum);
                for (int numUp = 0; numUp < updateTarget.size(); numUp++) {
                    INV_PRT_CTRLTMsg tMsg = findByKeyForUpdateInvPrtCtrl((BigDecimal) updateTarget.get(numUp).get(INV_PRT_CTRL_PK));
                    ZYPEZDItemValueSetter.setValue(tMsg.invEdiProcStsCd, INV_PROC_STS.PROCESSED);
                    // Add Start 2017/12/22 QC#22152
                    ZYPEZDItemValueSetter.setValue(tMsg.invEdiProcDt, this.slsDt);
                    // Add End 2017/12/22 QC#22152
                    this.invPrtCtrl.add(tMsg);
                }

                boolean result = executionInsertUpdate(invNum);
                if (result == false) {
                    unitId = unitId.subtract(BigDecimal.ONE);
                    unitIdMap.put(interfaceId, unitId);
                }
            }
        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.errorCount > 0) {
            if (!postErrorMail()) {
                throw new S21AbendException(NWCM0131E, new String[] {"Invoice EDI Extract for SAP Batch:mail sending process" });
            }
            this.termCd = TERM_CD.ABNORMAL_END;
        }
        setTermState(this.termCd, this.normalCount, this.errorCount);
    }

    /**
     * main
     * @param args String[]
     */
    public static void main(String[] args) {
        new NWCB030001().executeBatch(NWCB030001.class.getSimpleName());
    }

    /**
     * execParam
     * @return S21SsmExecutionParameter
     */
    private S21SsmExecutionParameter execParam() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    /**
     * getEDITargetData
     * @return PreparedStatement
     * @throws SQLException SQLException
     */
    private PreparedStatement getEDITargetData() throws SQLException {
        Map<String, Object> stesParam = new HashMap<String, Object>();
        stesParam.put("glblCmpyCd", this.glblCmpyCd);
        stesParam.put("invEdiProcStsCd", INV_PROC_STS.NOT_PROCESSED);
        stesParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.TRADING_PARTNER_CODE_MAPPING_FOR_SAP);
        stesParam.put("intfcId", XIX_INTFC_ID);
        stesParam.put("typeOm", TYPE_OM);
        stesParam.put("typeService", TYPE_SERVICE);
        return this.ssmLLClient.createPreparedStatement("getEDITargetData", stesParam, execParam());
    }

    /**
     * getHeaderData
     * @param invNum invNum
     * @param dsEdiTrdPtnrCd invNum
     * @return Map<String, Object>
     */
    private Map<String, Object> getHeaderData(String invNum, String dsEdiTrdPtnrCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("cpoSrcTpCd", CPO_SRC_TP.EDI);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("intfcXrefCtxTpCdC0", INTFC_XREF_CTX_TP.PAYMENT_TERM_MAPPING);
        queryParam.put("intfcXrefCtxTpCdC1", INTFC_XREF_CTX_TP.BILL_TYPE_MAPPING);
        queryParam.put("intfcId", XIX_INTFC_ID);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getHeaderData", queryParam, execParam());
        return result;
    }

    /**
     * isEasyPack
     * @param headerData Map<String, Object>
     * @return boolean
     */
    private boolean isEasyPack(Map<String, Object> headerData) {
        // 2017/12/26 QC#22366 Add Start
        if (!ZYPCommonFunc.hasValue((String) headerData.get(DS_ORD_CATG_CD))) {
            return false;
        }
        // 2017/12/26 QC#22366 Add End
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("dcOrdCatgCd", (String) headerData.get(DS_ORD_CATG_CD));
        queryParam.put("dsOrdTpCd", (String) headerData.get(DS_ORD_TP_CD));
        queryParam.put("dsOrdRsnCd", (String) headerData.get(DS_ORD_RSN_CD));
        queryParam.put("easyPack1", ORD_CATG_CTX_TP.EASY_PAC1);
        queryParam.put("easyPack1Return", ORD_CATG_CTX_TP.EASY_PAC1_RETURN);
        queryParam.put("billtoCustAcctCd", (String) headerData.get(BILL_TO_CUST_ACCT_CD));
        String ordDt = (String) headerData.get(ORD_DT);
        String invDt = (String) headerData.get(INV_DT);
        if (ordDt != null && ordDt.length() != 0) {
            queryParam.put("effDt", ordDt);
        } else {
            queryParam.put("effDt", invDt);
        }
        queryParam.put("limitDate", LIMIT_DATE);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getEasyPack", queryParam, execParam());
        if (result == null) {
            return false;
        }
        return true;
    }

    /**
     * getPaymentTermData
     * @param pmtTermCd String
     * @param netDueDate String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getPaymentTermData(String pmtTermCd, String netDueDate) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("pmtTermCd", pmtTermCd);
        queryParam.put("netDueDate", netDueDate);
        queryParam.put("pmtTermTxt1", "Up to ");
        queryParam.put("pmtTermTxt2", " without deduction ");
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPaymentTermData", queryParam, execParam());
        return result;
    }

    /**
     * getLineDataOm
     * @param invNum String
     * @param dsEdiTrdPtnrCd String
     * @param easyPack boolean
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getLineDataOm(String invNum, String dsEdiTrdPtnrCd, boolean easyPack) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("pkgUomCd", PKG_UOM.EACH);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.UOM_MAPPING_2);
        queryParam.put("invLineCatgFrt", INV_LINE_CATG.FREIGHT); // 2018/05/24 QC#21841 Add
        if (easyPack) {
            queryParam.put("easyPack", "easyPack");
        }
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLineDataOm", queryParam, execParam());
        return result;
    }

    /**
     * getLineDataFleet
     * @param invNum String
     * @param invoiceDate String
     * @param dsEdiTrdPtnrCd String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getLineDataFleet(String invNum, String invoiceDate, String dsEdiTrdPtnrCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("dsContrCatgCdFleet", DS_CONTR_CATG.FLEET);
        queryParam.put("invoiceDate", invoiceDate);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.UOM_MAPPING_2);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLineDataFleet", queryParam, execParam());
        return result;
    }

    /**
     * getLineDataRegularOrAggregate
     * @param invNum String
     * @param invoiceDate String
     * @param dsEdiTrdPtnrCd String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getLineDataRegularOrAggregate(String invNum, String invoiceDate, String dsEdiTrdPtnrCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("dsContrCatgCdFleet", DS_CONTR_CATG.FLEET);
        queryParam.put("invoiceDate", invoiceDate);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.UOM_MAPPING_2);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLineDataRegularOrAggregate", queryParam, execParam());
        return result;
    }

    /**
     * getLineDataDispatch
     * @param invNum String
     * @param invoiceDate String
     * @param dsEdiTrdPtnrCd String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getLineDataDispatch(String invNum, String invoiceDate, String dsEdiTrdPtnrCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invoiceDate", invoiceDate);
        queryParam.put("dsEdiTrdPtnrCd", dsEdiTrdPtnrCd);
        queryParam.put("intfcXrefCtxTpCd", INTFC_XREF_CTX_TP.UOM_MAPPING_2);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getLineDataDispatch", queryParam, execParam());
        return result;
    }

    /**
     * getCustMdseXrefInfo
     * @param mdseCd String
     * @param dsAcctNum String
     * @param effFromDt String
     * @return Map<String, Object>
     */
    private Map<String, Object> getCustMdseXrefInfo(String mdseCd, String dsAcctNum, String effFromDt) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("mdseCd", mdseCd);
        queryParam.put("dsAcctNum", dsAcctNum);
        queryParam.put("effFromDt", effFromDt);
        Map<String, Object> result = (Map<String, Object>) this.ssmBatchClient.queryObject("getCustMdseXrefInfo", queryParam, execParam());
        return result;
    }

    /**
     * getMeterSummary
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return BigDecimal
     */
    private BigDecimal getMeterSummary(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getMeterSummary", queryParam, execParam());
        return result;
    }
    // Add Start 2019/02/27 QC#30559
    /**
     * getMeterSummary for Aggregate
     * @param invNum String
     * @return BigDecimal
     */
    private BigDecimal getMeterSummaryAGG(String invNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getMeterSummaryAGG", queryParam, execParam());
        return result;
    }
    // Add End 2019/02/27 QC#30559

    /**
     * getMeterDataFleet
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMeterDataFleet(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        queryParam.put("svcInvSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        queryParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMeterDataFleet", queryParam, execParam());
        return result;
    }

    /**
     * getMeterDataRegularOrAggregate
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getMeterDataRegularOrAggregate(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        queryParam.put("svcInvSrcTpCd", SVC_INV_SRC_TP.CONTRACT);
        queryParam.put("dsContrCatgCd", DS_CONTR_CATG.FLEET);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getMeterDataRegularOrAggregate", queryParam, execParam());
        return result;
    }

    /**
     * getShipDataOm
     * @param invNum String
     * @param invBolLineNum String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getShipDataOm(String invNum, String invBolLineNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("dsCtacPntTpCdPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("dsCtacPntTpCdPhoneMobile", DS_CTAC_PNT_TP.PHONE_MOBILE);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipDataOm", queryParam, execParam());
        return result;
    }

    /**
     * getShipDataContract
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @param invDt String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getShipDataContract(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum, String invDt) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        queryParam.put("invDt", invDt);
        queryParam.put("limitDt", LIMIT_DATE);
        queryParam.put("dsCtacPntTpCdPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("dsCtacPntTpCdPhoneMobile", DS_CTAC_PNT_TP.PHONE_MOBILE);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipDataContract", queryParam, execParam());
        return result;
    }

    /**
     * getShipDataDispatch
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @param effDt String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getShipDataDispatch(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum, String invDt) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        queryParam.put("invDt", invDt);
        queryParam.put("limitDt", LIMIT_DATE);
        queryParam.put("dsCtacPntTpCdPhoneWork", DS_CTAC_PNT_TP.PHONE_WORK);
        queryParam.put("dsCtacPntTpCdPhoneMobile", DS_CTAC_PNT_TP.PHONE_MOBILE);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getShipDataDispatch", queryParam, execParam());
        return result;
    }

    /**
     * getUpdateTarget
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @param effDt String
     * @return List<Map<String, Object>>
     */
    private List<Map<String, Object>> getUpdateTarget(String invNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invEdiProcStsCd", INV_PROC_STS.NOT_PROCESSED);
        List<Map<String, Object>> result = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getUpdateTarget", queryParam, execParam());
        return result;
    }

    /**
     * getInvPrtCtrl
     * @param invPrtCtrlPk BigDecimal
     * @return INV_PRT_CTRLTMsg
     */
    private INV_PRT_CTRLTMsg findByKeyForUpdateInvPrtCtrl(BigDecimal invPrtCtrlPk) {
        INV_PRT_CTRLTMsg tMsg = new INV_PRT_CTRLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.invPrtCtrlPk, invPrtCtrlPk);
        return (INV_PRT_CTRLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    /**
     * createNEAI094001
     * @param headerData Map<String, Object>
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param invNum String
     */
    private void createNEAI094001(Map<String, Object> headerData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String invNum) {
        NEAI0940_01TMsg tMsg = new NEAI0940_01TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_01);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPmtTermCd, substring((String) headerData.get(PMT_TERM_CD_TXT), 0, 17));
        ZYPEZDItemValueSetter.setValue(tMsg.idocDocTpCd, "");

        String invTpCd = (String) headerData.get(INV_TP_CD);
        String idocDocTpCd = null;
        if (INV_TP.INVOICE.equals(invTpCd) || INV_TP.DEBIT_MEMO.equals(invTpCd)) {
            idocDocTpCd = IDOC_DOC_TP_CD_INVO;
        } else {
            idocDocTpCd = IDOC_DOC_TP_CD_CRME;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.idocDocTpCd, idocDocTpCd);

        ZYPEZDItemValueSetter.setValue(tMsg.idocDocNum, invNum);
        tMsg01.add(tMsg);
    }

    /**
     * createNEAI094002
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     */
    private void createNEAI094002(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber) {
        NEAI0940_02TMsg tMsg = new NEAI0940_02TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_02);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPrintLineDtlNum, IDOC_PRINT_LINE_DTL_NUM_COMPONENT);
        //QC#9763
        ZYPEZDItemValueSetter.setValue(tMsg.idocPrintDolDtlNum, IDOC_PRINT_DOL_DTL_NUM_COMPONENT);
        this.tMsg02.add(tMsg);
    }

    /**
     * createNEAI094004
     * @param headerData Map<String, Object>
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param seqNumber BigDecimal
     */
    // 2018/05/08 QC#25986 Mod Start
//    private void createNEAI094004(Map<String, Object> headerData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber) {
    private void createNEAI094004(Map<String, Object> headerData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String dsEdiTrdPtnrCd) {
    // 2018/05/08 QC#25986 Mod End
        NEAI0940_04TMsg tMsg = new NEAI0940_04TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_04);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrFuncNum, IDOC_PTNR_FUNC_NUM_RE);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNum, substring((String) headerData.get(BILL_TO_CUST_ACCT_CD), 0, 17));

        String billToCustAcctNm = (String) headerData.get(BILL_TO_CUST_ACCT_NM);
        // Mod Start 2019/02/18 QC#30385
        //        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_01, substring(billToCustAcctNm, 0, 35));
        //        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_02, substring(billToCustAcctNm, 35, 70));
        //        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_03, substring(billToCustAcctNm, 70, 105));
        //        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_04, substring(billToCustAcctNm, 105, 140));
        setPartitionAcctName(tMsg, billToCustAcctNm);
        // Mod End 2019/02/18 QC#30385

        String address = (String) headerData.get(ADDRESS);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_01, substring(address, 0, 35));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_02, substring(address, 35, 70));

        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_04, (String) headerData.get(RCPNT_CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrPostCd, substringPost((String) headerData.get(RCPNT_POST_CD)));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrCtryCd, (String) headerData.get(RCPNT_CTRY_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrRgCd, (String) headerData.get(RCPNT_ST_CD));
        ZYPEZDItemValueSetter.setValue(tMsg.idocRefNum, substring((String) headerData.get(DS_IMPT_ATTRB_TXT_02), 0, 30));
        if (DS_EDI_TRD_PTNR_CD_MG.equals(dsEdiTrdPtnrCd) && !ZYPCommonFunc.hasValue(tMsg.idocRefNum)) {
            ZYPEZDItemValueSetter.setValue(tMsg.idocRefNum, IDOC_REF_NM_DEF_MG);
        }
        this.tMsg04.add(tMsg);
    }

    /**
     * createNEAI094005
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocQlfyRefDocCd String
     * @param idocDocNum String
     */
    private void createNEAI094005(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocQlfyRefDocCd, String idocDocNum) {
        NEAI0940_05TMsg tMsg = new NEAI0940_05TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_05);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyRefDocCd, idocQlfyRefDocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocDocNum, idocDocNum);
        this.tMsg05.add(tMsg);
    }

    /**
     * createNEAI094006
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocQlfyDtSegCd String
     * @param idocEdiDt String
     */
    private void createNEAI094006(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocQlfyDtSegCd, String idocEdiDt) {
        NEAI0940_06TMsg tMsg = new NEAI0940_06TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_06);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyDtSegCd, idocQlfyDtSegCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocEdiDt, idocEdiDt);
        this.tMsg06.add(tMsg);
    }

    /**
     * createNEAI094010
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocPmtAotTermTxt BigDecimal
     * @param idocPmtTermDescTxt String
     */
    private void createNEAI094010(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, BigDecimal idocPmtAotTermTxt, String idocPmtTermDescTxt) {
        NEAI0940_10TMsg tMsg = new NEAI0940_10TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_10);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyPmtTermCd, IDOC_QLFY_PMT_TERM_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPmtAotTermTxt, formatNumber(null, idocPmtAotTermTxt, 8, 0));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPmtTermDescTxt, idocPmtTermDescTxt);
        this.tMsg10.add(tMsg);
    }

    /**
     * createNEAI094012
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocOrgTxt String
     */
    private void createNEAI094012(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocOrgTxt) {
        NEAI0940_12TMsg tMsg = new NEAI0940_12TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_12);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyOrgCd, IDOC_QLFY_ORG_CD);
        ZYPEZDItemValueSetter.setValue(tMsg.idocOrgTxt, substring(idocOrgTxt, 0, 35));
        this.tMsg12.add(tMsg);
    }

    /**
     * createNEAI094013
     * @param lineData Map<String, Object>
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocLineNum String
     * @param invTpCd String
     * @param rtlWhCd String
     */
    // 2018/12/25 Mod Start QC#29517
    // 2018/04/26 QC#25815 Mod Start
//    private void createNEAI094013(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocLineNum, String invTpCd) {
//    private void createNEAI094013(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocLineNum, String invTpCd, String rtlWhCd) {
    private void createNEAI094013(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocLineNum, String invTpCd, String rtlWhCd, String invType) {
    // 2018/04/26 QC#25815 Mod End
    // 2018/12/25 Mod End QC#29517
        NEAI0940_13TMsg tMsg = new NEAI0940_13TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_13);
        ZYPEZDItemValueSetter.setValue(tMsg.idocLineNum, idocLineNum);
        // ZYPEZDItemValueSetter.setValue(tMsg.idocInvQtyEdiTxt, formatNumber(invTpCd, (BigDecimal) lineData.get(SHIP_QTY), 15, 3)); // 2018/08/27 S21_NA#25001 Mod
        // 2018/09/07 QC#28083 Mod Start
        //ZYPEZDItemValueSetter.setValue(tMsg.idocInvQtyEdiTxt, formatNumber(invTpCd, ((BigDecimal) lineData.get(SHIP_QTY)).divide((BigDecimal) lineData.get(IN_EACH_QTY)), 15, 3));
        BigDecimal eachQty = (BigDecimal) lineData.get(IN_EACH_QTY);
        if (eachQty != null) {
            ZYPEZDItemValueSetter.setValue(tMsg.idocInvQtyEdiTxt, formatNumber(invTpCd, ((BigDecimal) lineData.get(SHIP_QTY)).divide(eachQty), 15, 3));
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.idocInvQtyEdiTxt, formatNumber(invTpCd, (BigDecimal) lineData.get(SHIP_QTY), 15, 3));
        }
        // 2018/09/07 QC#28083 Mod End
        // 2018/12/25 Mod Start QC#29517
        if (TYPE_SERVICE.equals(invType)) {
            ZYPEZDItemValueSetter.setValue(tMsg.idocUomCd, PKG_UOM.EACH);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.idocUomCd, substring((String) lineData.get(UOM_CD), 0, 3));
        }
        // 2018/12/25 Mod End QC#29517
        ZYPEZDItemValueSetter.setValue(tMsg.idocNetWt, (BigDecimal) lineData.get(TOTAL_IN_POUND_WEIGHT));
        // 2018/04/26 QC#25815 Add Start
        ZYPEZDItemValueSetter.setValue(tMsg.idocPlantCd, rtlWhCd);
        // 2018/04/26 QC#25815 Add End
        this.tMsg13.add(tMsg);
    }

    /**
     * createNEAI094014
     * @param lineData Map<String, Object>
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param invTpCd String
     * @param idocTotCopyAmtEdiCnt BigDecimal
     * @param dsContrCatgCd String
     */
    // Mod Start 2019/02/27 QC#30559
    private void createNEAI094014(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String invTpCd, BigDecimal idocTotCopyAmtEdiCnt, String dsContrCatgCd, BigDecimal meterCountAll, String lineRecTpCd) {
//  private void createNEAI094014(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String invTpCd, BigDecimal idocTotCopyAmtEdiCnt, String dsContrCatgCd) {
    // Mod End 2019/02/27 QC#30559
        NEAI0940_14TMsg tMsg = new NEAI0940_14TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_14);
        // Mod Start 2019/02/27 QC#30559
        ZYPEZDItemValueSetter.setValue(tMsg.idocLineRecTpCd, lineRecTpCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.idocLineRecTpCd, getIdocLineRecTpCd((String) lineData.get(SVC_INV_CHRG_TP_CD), dsContrCatgCd, (String) lineData.get(INV_PRINT_SVC_PGM_SRC_NM)));
        // Mod End 2019/02/27 QC#30559
        ZYPEZDItemValueSetter.setValue(tMsg.idocConfigTpNm, substring((String) lineData.get(MDL_DESC_TXT), 0, 40));
        // 2019/02/05 QC#30175 Mod Start
//        ZYPEZDItemValueSetter.setValue(tMsg.idocFromDt, (String) lineData.get(CONTR_EFF_FROM_DT));
//        ZYPEZDItemValueSetter.setValue(tMsg.idocThruDt, (String) lineData.get(CONTR_EFF_THRU_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.idocFromDt, (String) lineData.get(BLLG_PER_FROM_DT));
        ZYPEZDItemValueSetter.setValue(tMsg.idocThruDt, (String) lineData.get(BLLG_PER_THRU_DT));
        // 2019/02/05 QC#30175 Mod End
        // 2019/02/27 QC#30559 Mod Start
        // Mod Start 2019/05/10 QC#50326
        String svcInvChrgTpCd = (String) lineData.get(SVC_INV_CHRG_TP_CD);
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
        // if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd) && IDOC_LINE_REC_TP_CD_EXCESS_USAGE.equals(lineRecTpCd)) {
        // Mod End 2019/05/10 QC#50326
            ZYPEZDItemValueSetter.setValue(tMsg.idocTotCopyAmtEdiCnt, meterCountAll);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.idocTotCopyAmtEdiCnt, idocTotCopyAmtEdiCnt);
        }
//        ZYPEZDItemValueSetter.setValue(tMsg.idocTotCopyAmtEdiCnt, idocTotCopyAmtEdiCnt);
        // 2019/02/27 QC#30559 Mod End
        // 2018/12/25 Mod Start QC#29517
        BigDecimal UnitPrice = (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT);
        if (idocTotCopyAmtEdiCnt != null && idocTotCopyAmtEdiCnt.signum() != 0) {
            // 2019/02/12 Mod Start QC#30316
//            BigDecimal UnitPriceDivide = UnitPrice.divide(idocTotCopyAmtEdiCnt, 4, BigDecimal.ROUND_HALF_UP);
//            ZYPEZDItemValueSetter.setValue(tMsg.idocUnitPrcAmtEdiTxt, substringRight(formatNumber(invTpCd, UnitPriceDivide, 13, 2), 13));
            BigDecimal UnitPriceDivide = UnitPrice.divide(idocTotCopyAmtEdiCnt, 6, BigDecimal.ROUND_HALF_UP);
            ZYPEZDItemValueSetter.setValue(tMsg.idocUnitPrcAmtEdiTxt, substringRight(formatNumber(invTpCd, UnitPriceDivide, 13, 6), 13));
            // 2019/02/12 Mod End QC#30316
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.idocUnitPrcAmtEdiTxt, substringRight(formatNumber(invTpCd, (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), 13, 2), 13));
        }
        // 2018/12/25 Mod End QC#29517
        ZYPEZDItemValueSetter.setValue(tMsg.idocMdlNum, substring((String) lineData.get(MDL_GRP_NM), 0, 18));
        ZYPEZDItemValueSetter.setValue(tMsg.idocSerNum, substring((String) lineData.get(SER_NUM), 0, 10));
        this.tMsg14.add(tMsg);
    }

    private void createNEAI094014ForMultiTier(Map<String, Object> lineData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String invTpCd, String lineRecTpCd) {
            NEAI0940_14TMsg tMsg = new NEAI0940_14TMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
            ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
            ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
            ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
            ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
            ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_14);
            ZYPEZDItemValueSetter.setValue(tMsg.idocLineRecTpCd, lineRecTpCd);
            ZYPEZDItemValueSetter.setValue(tMsg.idocConfigTpNm, substring((String) lineData.get(MDL_DESC_TXT), 0, 40));
            ZYPEZDItemValueSetter.setValue(tMsg.idocFromDt, (String) lineData.get(BLLG_PER_FROM_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.idocThruDt, (String) lineData.get(BLLG_PER_THRU_DT));
            ZYPEZDItemValueSetter.setValue(tMsg.idocTotCopyAmtEdiCnt, BigDecimal.ONE);
            ZYPEZDItemValueSetter.setValue(tMsg.idocUnitPrcAmtEdiTxt, substringRight(formatNumber(invTpCd, (BigDecimal) lineData.get(DEAL_NET_UNIT_PRC_AMT), 13, 2), 13));
            ZYPEZDItemValueSetter.setValue(tMsg.idocMdlNum, substring((String) lineData.get(MDL_GRP_NM), 0, 18));
            ZYPEZDItemValueSetter.setValue(tMsg.idocSerNum, substring((String) lineData.get(SER_NUM), 0, 10));
            this.tMsg14.add(tMsg);
        }
    /**
     * createNEAI094015
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocPrevMtrReadCnt BigDecimal
     * @param idocMtrReadCnt BigDecimal
     */
    private void createNEAI094015(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, BigDecimal idocPrevMtrReadCnt, BigDecimal idocMtrReadCnt) {
        NEAI0940_15TMsg tMsg = new NEAI0940_15TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_15);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPrevMtrReadCnt, idocPrevMtrReadCnt);
        ZYPEZDItemValueSetter.setValue(tMsg.idocMtrReadCnt, idocMtrReadCnt);
        this.tMsg15.add(tMsg);
    }

    /**
     * createNEAI094017
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocDocNum String
     * @param idocLineNum String
     * @param idocQlfyRefDocCd String
     */
    // 2018/04/23 QC#25772 Mod Start
//    private void createNEAI094017(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocDocNum, String idocLineNum) {
    private void createNEAI094017(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocDocNum, String idocLineNum, String idocQlfyRefDocCd) {
    // 2018/04/23 QC#25772 Mod End
        NEAI0940_17TMsg tMsg = new NEAI0940_17TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_17);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyRefDocCd, idocQlfyRefDocCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocDocNum, idocDocNum);
        ZYPEZDItemValueSetter.setValue(tMsg.idocLineNum, idocLineNum);
        this.tMsg17.add(tMsg);
    }

    /**
     * createNEAI094019
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocMatId String
     * @param idocMatDescTxt String
     */
    private void createNEAI094019(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocMatId, String idocMatDescTxt) {
        NEAI0940_19TMsg tMsg = new NEAI0940_19TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_19);
        ZYPEZDItemValueSetter.setValue(tMsg.idocObjEdiId, IDOC_OBJ_EDI_ID);
        ZYPEZDItemValueSetter.setValue(tMsg.idocMatId, idocMatId);
        ZYPEZDItemValueSetter.setValue(tMsg.idocMatDescTxt, substring(idocMatDescTxt, 0, 70));
        this.tMsg19.add(tMsg);
    }

    /**
     * createNEAI094020
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocQlfyAmtEdiCd String
     * @param idocTotSmryAmtEdiTxt BigDecimal
     * @param invTpCd String
     */
    private void createNEAI094020(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocQlfyAmtEdiCd, BigDecimal idocTotSmryAmtEdiTxt, String invTpCd) {
        NEAI0940_20TMsg tMsg = new NEAI0940_20TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_20);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyAmtEdiCd, idocQlfyAmtEdiCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocTotSmryAmtEdiTxt, substringRight(formatNumber(invTpCd, idocTotSmryAmtEdiTxt, 18, 2), 18));
        this.tMsg20.add(tMsg);
    }
    /**
     * createNEAI094021
     * @param shipData Map<String, Object>
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param telNum String
     */
    // 2019/01/16 QC#29535 Mod Start
    //private void createNEAI094021(Map<String, Object> shipData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber) {
    private void createNEAI094021(Map<String, Object> shipData, String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String telNum) {
    // 2019/01/16 QC#29535 Mod End
        NEAI0940_21TMsg tMsg = new NEAI0940_21TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_21);
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrFuncNum, IDOC_PTNR_FUNC_NUM);
        // 2018/09/18 QC#28159 Mod Start Should be INV_BOL.SHIP_TO_CUST_ACCT_CD
//        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNum, substring((String) shipData.get(SHIP_TO_CUST_CD), 0, 17));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNum, substring((String) shipData.get(SHIP_TO_CUST_ACCT_CD), 0, 17));
        // 2018/09/18 QC#28159 Mod End

        String dsAcctNm = (String) shipData.get(DS_ACCT_NM);
        // 2018/01/26 QC#22718 Add Start
        if (S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, (String) shipData.get(DROP_SHIP_FLG))) {
            dsAcctNm = (String) shipData.get(SHIP_TO_LOC_NM);
        }
        // 2018/01/26 QC#22718 Add End
        // Mod Start 2019/02/18 QC#30385
//        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_01, substring(dsAcctNm, 0, 35));
//        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrNm_02, substring(dsAcctNm, 35, 70));
        setPartitionAcctName(tMsg, dsAcctNm);
        // Mod Start 2019/02/18 QC#30385

        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_01, substring((String) shipData.get(FIRST_LINE_ADDR), 0, 35));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_02, substring((String) shipData.get(ADDRESS), 0, 35));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrAddr_04, (String) shipData.get(CTY_ADDR));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrPostCd, substringPost((String) shipData.get(POST_CD)));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrCtryCd, (String) shipData.get(CTRY_CD));
        // 2019/01/16 QC#29535 Mod Start
        //ZYPEZDItemValueSetter.setValue(tMsg.idocCtacTelNum_01, substring((String) shipData.get(DS_CTAC_PNT_VAL_TXT_1), 0, 25));
        ZYPEZDItemValueSetter.setValue(tMsg.idocCtacTelNum_01, telNum);
        // 2019/01/16 QC#29535 Mod End
        ZYPEZDItemValueSetter.setValue(tMsg.idocCtacTelNum_02, substring((String) shipData.get(DS_CTAC_PNT_VAL_TXT_2), 0, 25));
        ZYPEZDItemValueSetter.setValue(tMsg.idocDstrTxt, (String) shipData.get(CNTY_NM));
        ZYPEZDItemValueSetter.setValue(tMsg.idocPtnrRgCd, (String) shipData.get(ST_CD));
        this.tMsg21.add(tMsg);
    }

    /**
     * createNEAI094023
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param idocQlfyTotSegCd String
     * @param idocSegSmryAmtEdiTxt BigDecimal
     * @param invTpCd String
     */
    private void createNEAI094023(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber, String idocQlfyTotSegCd, BigDecimal idocSegSmryAmtEdiTxt, String invTpCd) {
        NEAI0940_23TMsg tMsg = new NEAI0940_23TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_23);
        ZYPEZDItemValueSetter.setValue(tMsg.idocQlfyTotSegCd, idocQlfyTotSegCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegSmryAmtEdiTxt, substringRight(formatNumber(invTpCd, idocSegSmryAmtEdiTxt, 18, 2), 18));
        this.tMsg23.add(tMsg);
    }

    //QC#14551
    /**
     * createNEAI094024
     * @param interfaceId String
     * @param transactionId BigDecimal
     * @param unitId BigDecimal
     * @param seqNumber BigDecimal
     * @param iDocSrcrgDiscCd String
     * @param idocCondTpCd String
     * @param idocCondTpNm String
     * @param idocGrsSrcrgAmtDiscTxt BigDecimal
     * @param idocCondAmtEdiTxt BigDecimal
     * @param idocUomCd String
     */
    // 2018/12/25 Mod Start QC#29517
//    private void createNEAI094024(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber,
//            String iDocSrcrgDiscCd, String idocCondTpCd, String idocCondTpNm, BigDecimal idocGrsSrcrgAmtDiscTxt, BigDecimal idocCondAmtEdiTxt, String idocUomCd) {
    private void createNEAI094024(String interfaceId, BigDecimal transactionId, BigDecimal unitId, BigDecimal seqNumber,
            String iDocSrcrgDiscCd, String idocCondTpCd, String idocCondTpNm, BigDecimal idocGrsSrcrgAmtDiscTxt, BigDecimal idocCondAmtEdiTxt, String idocUomCd, String invType) {
    // 2018/12/25 Mod Start QC#29517
        NEAI0940_24TMsg tMsg = new NEAI0940_24TMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.interfaceId, interfaceId);
        ZYPEZDItemValueSetter.setValue(tMsg.transactionId, transactionId);
        ZYPEZDItemValueSetter.setValue(tMsg.segmentId, SEGMENT_ID_LEVEL_1);
        ZYPEZDItemValueSetter.setValue(tMsg.unitId, unitId);
        ZYPEZDItemValueSetter.setValue(tMsg.seqNumber, seqNumber);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSegNm, IDOC_SEG_NM_24);
        ZYPEZDItemValueSetter.setValue(tMsg.idocSrcrgDiscCd, iDocSrcrgDiscCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocCondTpCd, idocCondTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.idocCondTpNm, idocCondTpNm);
        if (idocGrsSrcrgAmtDiscTxt == null) {
            idocGrsSrcrgAmtDiscTxt = BigDecimal.ZERO;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.idocGrsSrcrgAmtDiscTxt, idocGrsSrcrgAmtDiscTxt.toString());
        if (idocCondAmtEdiTxt == null) {
            idocCondAmtEdiTxt = BigDecimal.ZERO;
        }
        ZYPEZDItemValueSetter.setValue(tMsg.idocCondAmtEdiTxt, idocCondAmtEdiTxt.toString());
        ZYPEZDItemValueSetter.setValue(tMsg.idocPrcUnitTxt, "1");
        // 2018/12/25 Mod Start QC#29517
        if (TYPE_SERVICE.equals(invType)) {
            //PKG_UOM.EACH)
            ZYPEZDItemValueSetter.setValue(tMsg.idocUomCd, PKG_UOM.EACH);
        } else {
            ZYPEZDItemValueSetter.setValue(tMsg.idocUomCd, idocUomCd);
        }
        // 2018/12/25 Mod Start QC#29517

        this.tMsg24.add(tMsg);
    }

    /**
     * executionInsertUpdate
     */
    private boolean executionInsertUpdate(String invNum) {
        boolean errorFlag = false;
        if (errorFlag == false && this.tMsg01.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg01.toArray(new NEAI0940_01TMsg[this.tMsg01.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_01, resultCount, this.tMsg01.size());
        }
        if (errorFlag == false && this.tMsg02.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg02.toArray(new NEAI0940_02TMsg[this.tMsg02.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_02, resultCount, this.tMsg02.size());
        }
        if (errorFlag == false && this.tMsg04.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg04.toArray(new NEAI0940_04TMsg[this.tMsg04.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_04, resultCount, this.tMsg04.size());
        }
        if (errorFlag == false && this.tMsg05.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg05.toArray(new NEAI0940_05TMsg[this.tMsg05.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_05, resultCount, this.tMsg05.size());
        }
        if (errorFlag == false && this.tMsg06.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg06.toArray(new NEAI0940_06TMsg[this.tMsg06.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_06, resultCount, this.tMsg06.size());
        }
        if (errorFlag == false && this.tMsg10.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg10.toArray(new NEAI0940_10TMsg[this.tMsg10.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_10, resultCount, this.tMsg10.size());
        }
        if (errorFlag == false && this.tMsg12.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg12.toArray(new NEAI0940_12TMsg[this.tMsg12.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_12, resultCount, this.tMsg12.size());
        }
        if (errorFlag == false && this.tMsg13.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg13.toArray(new NEAI0940_13TMsg[this.tMsg13.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_13, resultCount, this.tMsg13.size());
        }
        if (errorFlag == false && this.tMsg14.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg14.toArray(new NEAI0940_14TMsg[this.tMsg14.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_14, resultCount, this.tMsg14.size());
        }
        if (errorFlag == false && this.tMsg15.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg15.toArray(new NEAI0940_15TMsg[this.tMsg15.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_15, resultCount, this.tMsg15.size());
        }
        if (errorFlag == false && this.tMsg17.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg17.toArray(new NEAI0940_17TMsg[this.tMsg17.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_17, resultCount, this.tMsg17.size());
        }
        if (errorFlag == false && this.tMsg19.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg19.toArray(new NEAI0940_19TMsg[this.tMsg19.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_19, resultCount, this.tMsg19.size());
        }
        if (errorFlag == false && this.tMsg20.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg20.toArray(new NEAI0940_20TMsg[this.tMsg20.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_20, resultCount, this.tMsg20.size());
        }
        if (errorFlag == false && this.tMsg21.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg21.toArray(new NEAI0940_21TMsg[this.tMsg21.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_21, resultCount, this.tMsg21.size());
        }
        if (errorFlag == false && this.tMsg24.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg24.toArray(new NEAI0940_24TMsg[this.tMsg24.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_24, resultCount, this.tMsg24.size());
        }
        if (errorFlag == false && this.tMsg23.size() > 0) {
            int resultCount = S21FastTBLAccessor.insert(this.tMsg23.toArray(new NEAI0940_23TMsg[this.tMsg23.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, NEAI0940_23, resultCount, this.tMsg23.size());
        }

        if (errorFlag == false && this.invPrtCtrl.size() > 0) {
            int resultCount = S21FastTBLAccessor.update(this.invPrtCtrl.toArray(new INV_PRT_CTRLTMsg[this.invPrtCtrl.size()]));
            errorFlag = isErrorInsertUpdateResult(invNum, NWCM0132E, INV_PRT_CTRL, resultCount, this.invPrtCtrl.size());
        }
        if (errorFlag == true) {
            rollback();
            this.errorCount++;
            return false;
        } else {
            commit();
            this.normalCount++;
            return true;
        }
    }

    /**
     * substringPost
     * @param str String
     * @return String
     */
    private String substringPost(String str) {
        if (str == null) {
            return str;
        }
        str = ZYPCommonFunc.replaceAll(str, "-", "");
        return substring(str, 0, 9);
    }

    /**
     * substring
     * @param str String
     * @param beginIndex int
     * @param endIndex int
     * @return String
     */
    private String substring(String str, int beginIndex, int endIndex) {
        if (str == null) {
            return null;
        }
        if (str.length() - 1 < beginIndex) {
            return null;
        }
        if (str.length() - 1 < endIndex) {
            return str.substring(beginIndex);
        }
        return str.substring(beginIndex, endIndex);
    }

    /**
     * substringRight
     * @param str String
     * @param beginIndex int
     * @param endIndex int
     * @return String
     */
    private String substringRight(String str, int length) {
        if (str == null) {
            return null;
        }
        if (str.length() - 1 < length) {
            return str;
        }
        return str.substring(str.length() - length);
    }

    /**
     * createIdocLineNum
     * @param num int
     * @return String
     */
    private String createIdocLineNum(int num) {
        return ZYPCommonFunc.leftPad(String.valueOf(num), 6, "0");
    }

    /**
     * formatNumber
     * @param invTpCd String
     * @param decimal BigDecimal
     * @param columnSize int
     * @param underSize int
     * @return String
     */
    private String formatNumber(String invTpCd, BigDecimal decimal, int columnSize, int underSize) {
        if (decimal == null) {
            return null;
        }
        if (invTpCd != null && INV_TP.CREDIT_MEMO.equals(invTpCd)) {
            decimal = decimal.multiply(new BigDecimal(-1));
        }
        String format = "0";
        if (underSize > 0) {
            format = format + "." + ZYPCommonFunc.rightPad("", underSize, "0");
        }
        format = ZYPCommonFunc.leftPad("", columnSize - format.length() - 1, "#") + format;
        return new DecimalFormat(format).format(decimal.doubleValue());
    }

    /**
     * add
     * @param decimal1 BigDecimal
     * @param decimal2 BigDecimal
     * @return BigDecimal
     */
    private BigDecimal add(BigDecimal decimal1, BigDecimal decimal2) {
        if (decimal1 != null && decimal2 != null) {
            return decimal1.add(decimal2);
        } else if (decimal1 != null) {
            return decimal1;
        } else {
            return decimal2;
        }
    }

    /**
     * getIdocLineRecTpCd
     * @param svcInvChrgTpCd String
     * @param dsContrCatgCd String
     * @param invPrintSvcPgmSrcNm String
     * @return String
     */
    private String getIdocLineRecTpCd(String svcInvChrgTpCd, String dsContrCatgCd, String invPrintSvcPgmSrcNm) {
        // Mod Start QC#50326 2019/05/10 
//        if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
//            if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
//                return IDOC_LINE_REC_TP_CD_POOLING;
//            } else if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
//                if ("RENTAL".equals(invPrintSvcPgmSrcNm)) {
//                    return IDOC_LINE_REC_TP_CD_RENTAL;
//                } else {
//                    return IDOC_LINE_REC_TP_CD_LEASE;
//                }
//            }
//        } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
//            return IDOC_LINE_REC_TP_CD_EXCESS_USAGE;
//        }
        if (DS_CONTR_CATG.AGGREGATE.equals(dsContrCatgCd)) {
            return IDOC_LINE_REC_TP_CD_POOLING;
        } else {
            if (SVC_INV_CHRG_TP.BASE_CHARGE.equals(svcInvChrgTpCd)) {
                if (DS_CONTR_CATG.REGULAR.equals(dsContrCatgCd)) {
                    if ("RENTAL".equals(invPrintSvcPgmSrcNm)) {
                        return IDOC_LINE_REC_TP_CD_RENTAL;
                    } else {
                        return IDOC_LINE_REC_TP_CD_SERVICE;
                    }
                }
            } else if (SVC_INV_CHRG_TP.METER_CHARGE.equals(svcInvChrgTpCd)) {
                return IDOC_LINE_REC_TP_CD_EXCESS_USAGE;
            }
        }
        // Mod End QC#50326 2019/05/10 
        return null;
    }

    /**
     * postErrorMail
     * @return
     */
    private boolean postErrorMail() {

        final List<NWXC001001MailSubstituteString> sbsStrList = new ArrayList<NWXC001001MailSubstituteString>(3);

        NWXC001001MailSubstituteString sbsStr;

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchId");
        sbsStr.setSbstStr(BUSINESS_ID);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchNm");
        sbsStr.setSbstStr(BATCH_NAME);
        sbsStrList.add(sbsStr);

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("batchProcLogId");
        sbsStr.setSbstStr(super.getBatchProcessLogID());
        sbsStrList.add(sbsStr);

        StringBuffer msg = new StringBuffer();
        for (int i = 0; i < this.errMsgList.size(); i++) {
            msg.append(this.errMsgList.get(i));
            msg.append(LINE_FEED_CODE);
        }

        sbsStr = new NWXC001001MailSubstituteString();
        sbsStr.setSbstKey("ErrorInfo");
        sbsStr.setSbstStr(msg.toString());
        sbsStrList.add(sbsStr);

        boolean isNormalEnd = new NWXC001001Mail().postMail(this.glblCmpyCd, BUSINESS_ID, MAIL_TEMPLATE_ID, sbsStrList);

        return isNormalEnd;

    }

    /**
     * isErrorInsertUpdateResult
     * @param invNum
     * @param resultCount
     * @param msgCount
     * @return boolean (normal:false,error:true)
     */
    private boolean isErrorInsertUpdateResult(String invNum, String msgId, String tableName, int resultCount, int msgCount) {
        if (resultCount != msgCount) {
            if (this.errMsgList.size() == 0) {
                this.errMsgList.add("Invoice#");
            }
            this.errMsgList.add("    " + invNum + " : " + getMessage(msgId, tableName));
            return true;
        }
        return false;
    }

    private String getMessage(String msgId, String tableName) {
        String message = S21MessageFunc.clspGetMessage(msgId, new String[] {tableName });
        if (message != null && message.length() > 10) {
            message = message.substring(10);
        }
        return message;
    }

    // 2018/04/26 QC#25815 Add Start
    /**
     * getRtlWhCd
     * @param invNum String
     * @param invBolLineNum String
     * @return BigDecimal
     */
    private String getRtlWhCd(String invNum, String invBolLineNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        String result = (String) this.ssmBatchClient.queryObject("getRtlWhCd", queryParam);
        return result;
    }
    // 2018/04/26 QC#25815 Add End

    // 2018/10/03 QC#27032 Add Start
    /**
     * <pre>Getting PNTP net amount
     * @param glblCmpyCd Global Company Code
     * @param invNum Invoice Number
     * @return PNTP net amount (total in one invoice)
     * </pre>
     */
    private BigDecimal getPntpNetAmount(String invNum) {

        if (this.pntnPrcRuleHdrPkList == null) {
            String pntnPrcRuleHdrPks = ZYPCodeDataUtil.getVarCharConstValue(NWCB0300_PNTP_PRC_RULE_HDR_PK, glblCmpyCd);
            if (pntnPrcRuleHdrPks == null) {
                return BigDecimal.ZERO;
            }
            this.pntnPrcRuleHdrPkList = new ArrayList<BigDecimal>(0);
            String[] pntnPrcRuleHdrPkArray = pntnPrcRuleHdrPks.split(",");
            for (String pntnPrcRuleHdrPkStr : pntnPrcRuleHdrPkArray) {
                try {
                    BigDecimal pntnPrcRuleHdrPk = new BigDecimal(pntnPrcRuleHdrPkStr);
                    this.pntnPrcRuleHdrPkList.add(pntnPrcRuleHdrPk);
                } catch (Exception ex) {
                    // NOP
                }
            }
        }
        if (this.pntnPrcRuleHdrPkList.isEmpty()) {
            return BigDecimal.ZERO;
        }
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invLineCatgItem", INV_LINE_CATG.ITEM);
        queryParam.put("pntpPrcRuleHdrPkList", this.pntnPrcRuleHdrPkList);

        Map<String, Object> queryRslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getPntpNetAmount", queryParam);
        if (queryRslt == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) queryRslt.get("PNTP_NET_AMT");
    }

    private BigDecimal getLineDealNetFrtAmt(String invNum, Map<String, Object> lineData) {

        if (!S21StringUtil.isEquals(INV_LINE_CATG.ITEM, (String) lineData.get(INV_LINE_CATG_CD))) {
            return BigDecimal.ZERO;
        }

        // 2019/11/12 QC#54139 Add Start
        if (!ZYPCommonFunc.hasValue((String) lineData.get(CPO_ORD_NUM)) || !ZYPCommonFunc.hasValue((String) lineData.get(CPO_DTL_LINE_NUM)) || !ZYPCommonFunc.hasValue((String) lineData.get(CPO_DTL_LINE_SUB_NUM))) {
            return BigDecimal.ZERO;
        }
        // 2019/11/12 QC#54139 Add End

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", (String) lineData.get(INV_BOL_LINE_NUM));
        queryParam.put("cpoOrdNum", (String) lineData.get(CPO_ORD_NUM));
        queryParam.put("cpoDtlLineNum", (String) lineData.get(CPO_DTL_LINE_NUM));
        queryParam.put("cpoDtlLineSubNum", (String) lineData.get(CPO_DTL_LINE_SUB_NUM));
        queryParam.put("invLineCatgFrt", (String) INV_LINE_CATG.FREIGHT);

        Map<String, Object> queryRslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getLineDealNetFrtAmt", queryParam);
        if (queryRslt == null) {
            return BigDecimal.ZERO;
        }
        return (BigDecimal) queryRslt.get("INV_LINE_DEAL_NET_FRT_AMT");
    }

    private BigDecimal subtract(BigDecimal decimal1, BigDecimal decimal2) {
        if (decimal1 != null && decimal2 != null) {
            return decimal1.subtract(decimal2);
        } else if (decimal1 != null) {
            return decimal1;
        } else {
            return decimal2.negate();
        }
    }

    private BigDecimal getLineZsfSpecialChargeAmt(String invNum, Map<String, Object> lineData) {

        if (!S21StringUtil.isEquals(INV_LINE_CATG.ITEM, (String) lineData.get(INV_LINE_CATG_CD))) {
            return BigDecimal.ZERO;
        }

        // 2019/11/12 QC#54139 Add Start
        if (!ZYPCommonFunc.hasValue((String) lineData.get(CPO_ORD_NUM)) || !ZYPCommonFunc.hasValue((String) lineData.get(CPO_DTL_LINE_NUM)) || !ZYPCommonFunc.hasValue((String) lineData.get(CPO_DTL_LINE_SUB_NUM))) {
            return BigDecimal.ZERO;
        }
        // 2019/11/12 QC#54139 Add End

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", (String) lineData.get(INV_BOL_LINE_NUM));
        queryParam.put("cpoOrdNum", (String) lineData.get(CPO_ORD_NUM));
        queryParam.put("cpoDtlLineNum", (String) lineData.get(CPO_DTL_LINE_NUM));
        queryParam.put("cpoDtlLineSubNum", (String) lineData.get(CPO_DTL_LINE_SUB_NUM));
        queryParam.put("invLineCatgCharge", (String) INV_LINE_CATG.CHARGE);

        setSpclHdlgPrcRuleHdrPkList();
        if (this.spclHdlgPrcRuleHdrPkList == null || this.spclHdlgPrcRuleHdrPkList.isEmpty()) {
            return BigDecimal.ZERO;
        }
        queryParam.put("spclHdlgPrcRuleHdrPkList", this.spclHdlgPrcRuleHdrPkList);
        BigDecimal rslt = BigDecimal.ZERO;
        Map<String, Object> queryRslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getLineZsfSpecialChargeAmt", queryParam);
        if (queryRslt != null) {
            rslt = (BigDecimal) queryRslt.get("ZSF_NET_AMT");
        }
        return rslt;
    }

    private BigDecimal getHdrZsfSpecialChargeAmt(String invNum) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invLineCatgCharge", (String) INV_LINE_CATG.CHARGE);

        setSpclHdlgPrcRuleHdrPkList();
        if (this.spclHdlgPrcRuleHdrPkList == null || this.spclHdlgPrcRuleHdrPkList.isEmpty()) {
            return BigDecimal.ZERO;
        }
        queryParam.put("spclHdlgPrcRuleHdrPkList", this.spclHdlgPrcRuleHdrPkList);

        BigDecimal rslt = BigDecimal.ZERO;
        Map<String, Object> queryRslt = (Map<String, Object>) this.ssmBatchClient.queryObject("getHdrZsfSpecialChargeAmt", queryParam);
        if (queryRslt != null) {
            rslt = (BigDecimal) queryRslt.get("ZSF_NET_AMT");
        }
        return rslt;
    }

    private void setSpclHdlgPrcRuleHdrPkList() {

        if (this.spclHdlgPrcRuleHdrPkList == null) {
            this.spclHdlgPrcRuleHdrPkList = new ArrayList<BigDecimal>(0);
            String pntnPrcRuleHdrPks = ZYPCodeDataUtil.getVarCharConstValue(NWCB0300_SPCL_HDLG_RULE_HDR_PK, glblCmpyCd);
            if (pntnPrcRuleHdrPks == null) {
                return;
            }
            String[] pntnPrcRuleHdrPkArray = pntnPrcRuleHdrPks.split(",");
            for (String pntnPrcRuleHdrPkStr : pntnPrcRuleHdrPkArray) {
                try {
                    BigDecimal pntnPrcRuleHdrPk = new BigDecimal(pntnPrcRuleHdrPkStr);
                    this.spclHdlgPrcRuleHdrPkList.add(pntnPrcRuleHdrPk);
                } catch (Exception ex) {
                    // NOP
                }
            }
        }
    }
    // 2018/10/03 QC#27032 Add End
    // 2019/02/18 QC#30385 Add Start
    private void setPartitionAcctName(EZDTMsg tMsg, String acctNm) {
        if (acctNm == null) {
            return;
        }
        List<String> returnStrings = new ArrayList<String>();
        String[] splitStrings = acctNm.split(" ");

        if (splitStrings == null || splitStrings.length == 0) {
            return;
        }
        String returnStr = splitStrings[0];
        for (int i = 1; i < splitStrings.length + 1; i++) {
            if (i == splitStrings.length) {
                if (returnStr.length() > 35) {
                    i--;
                    splitStrings[i] = returnStr.substring(35);
                    returnStr = returnStr.substring(0, 35);
                    returnStrings.add(returnStr);
                    returnStr = splitStrings[i];
                }
            } else {
                if (returnStr.concat(" ").concat(splitStrings[i]).length() > 35) {
                    if (returnStr.length() > 35) {
                        i--;
                        splitStrings[i] = returnStr.substring(35);
                        returnStr = returnStr.substring(0, 35);
                    }
                    returnStrings.add(returnStr);
                    returnStr = splitStrings[i];

                } else {
                    returnStr = returnStr.concat(" ").concat(splitStrings[i]);
                }
            }

        }
        returnStrings.add(returnStr);
        for (int i = 1; i < returnStrings.size() + 1; i++) {
            if (i > 4) {
                return;
            }
            tMsg.setDBValue("idocPtnrNm_0" + i, returnStrings.get(i - 1));
        }
    }
    // 2019/02/18 QC#30385 Add End
    // QC#30973 2019/04/19 Add Start
    /**
     * getInvLineXsMtrCnt
     * @param invNum String
     * @param invBolLineNum String
     * @param invLineNum String
     * @param invLineSubNum String
     * @param invLineSubTrxNum String
     * @return BigDecimal
     */
    private BigDecimal getInvLineXsMtrCnt(String invNum, String invBolLineNum, String invLineNum, String invLineSubNum, String invLineSubTrxNum) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", this.glblCmpyCd);
        queryParam.put("invNum", invNum);
        queryParam.put("invBolLineNum", invBolLineNum);
        queryParam.put("invLineNum", invLineNum);
        queryParam.put("invLineSubNum", invLineSubNum);
        queryParam.put("invLineSubTrxNum", invLineSubTrxNum);
        BigDecimal result = (BigDecimal) this.ssmBatchClient.queryObject("getInvLineXsMtrCnt", queryParam, execParam());
        if (result == null) {
            return BigDecimal.ZERO;
        }
        return result;
    }
    // QC#30973 2019/04/19 Add End
}

/**
 * <Pre>Copyright(c)2018 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.batch.NWA.NWAB270001;

import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.DEF_EFF_THRU_DT;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.FETCH_SIZE_MAX;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.NWAM0323W;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.NWZM1023E;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.PROGRAM_ID;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.SVC_MACH_TRX_TP_CD_LEN;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NWA.NWAB270001.constant.NWAB270001Constant.NWAM0991E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDDebugOutput;
import parts.dbcommon.EZDTBLAccessor;
import business.db.GLBL_CMPYTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_CTAC_PSNTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSZC001001PMsg;
import business.parts.NWZC033001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC001001.NSZC001001;
import com.canon.cusa.s21.api.NSZ.NSZC001001.ProcessMode;
import com.canon.cusa.s21.api.NWZ.NWZC033001.NWZC033001;
import com.canon.cusa.s21.common.NWX.NWXC412001.NWXC412001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_REL_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_RSN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_DPLY_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TRX_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Intangible Install Base Creation & Install Batch
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/12/07   Fujitsu         T.Murai         Create          QC#29488
 * 2019/01/15   Fujitsu         T.Murai         Update          QC#29879
 * 2019/05/27   Fujitsu         M.Ohno          Update          QC#50462
 * 2019/05/31   Fujitsu         M.Ohno          Update          QC#50551
 * 2024/02/15   CITS            R.Tamura        Update          CSA-QC#63393
 *</pre>
 */
public class NWAB270001 extends S21BatchMain {

    /** Sales Date */
    private String salesDate;

    /** Error Record Count */
    private int errRecCnt;

    /** records */
    private int totalRecCnt;

    /** message list */
    private List<String> msgList = new ArrayList<String>();

    /** API Error message list */
    private List<String> apiErrMsgList = new ArrayList<String>();

    /** SSM-Client */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** SSM */
    private S21SsmBatchClient ssmBatchClient = null;

    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Term Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /**
     * @param args String[] batch parameters
     */
    public static void main(String[] args) {
        new NWAB270001().executeBatch();
    }

    @Override
    protected void initRoutine() {
        this.errRecCnt = 0;
        this.termCd = TERM_CD.NORMAL_END;

        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());

        // Global Company Code
        this.glblCmpyCd = getGlobalCompanyCode();
        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E.toString(), new String[] {"Global Company Code" });
        }

        EZDDebugOutput.println(1, PROGRAM_ID + ">>>>> glblCmpyCd:" + this.glblCmpyCd, this);
        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        this.salesDate = ZYPDateUtil.getSalesDate(this.glblCmpyCd);
    }

    @Override
    protected void mainRoutine() { // Search Target Data
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", this.glblCmpyCd);
            ssmParam.put("shpgStsCdArrived", SHPG_STS.ARRIVED);
            // 2019/05/31 QC#50462 Add Start
            ssmParam.put("shpgStsCdInstalled", SHPG_STS.INSTALLED);
            // 2019/05/31 QC#50462 Add End
            ssmParam.put("flgOffN", ZYPConstant.FLG_OFF_N);
            ssmParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
            ssmParam.put("svcMachTpM", SVC_MACH_TP.MACHINE);
            ssmParam.put("svcMachTpA", SVC_MACH_TP.ACCESSORY);
            // 2019/01/15 S21_NA#29879 Del Start
//            ssmParam.put("hldRsnArrivedWait4Instl", HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
            // 2019/01/15 S21_NA#29879 Del End
            // 2019/05/31 QC#50462 Add Start
            ssmParam.put("lineStsClose", ORD_LINE_STS.CLOSED);
            ssmParam.put("lineStsCancelled", ORD_LINE_STS.CANCELLED);
            ssmParam.put("dplyStsOnLoan", ORD_LINE_DPLY_STS.ON_LOAN);

            ssmParam.put("trxSrcTpCd", TRX_SRC_TP.WHOLE_SALES);
            // 2019/05/27 QC#50462 Add Start
            String crAndBillOnlyDummyWhCd = ZYPCodeDataUtil.getVarCharConstValue("CR_AND_BILL_ONLY_DUMMY_WH_CD", this.glblCmpyCd);
            if (S21StringUtil.isNotEmpty(crAndBillOnlyDummyWhCd)) {
                List<String> crAndBillOnlyDummyWhCdList = S21StringUtil.toList(crAndBillOnlyDummyWhCd);
                ssmParam.put("rtlWhCdList", crAndBillOnlyDummyWhCdList.toArray(new String[0]));
            } else {
                ssmParam.put("rtlWhCdList", null);
            }
            // 2019/05/27 QC#50462 Add End

            ps = this.ssmLLClient.createPreparedStatement("getTargetData", ssmParam, getExecPrm());
            rs = ps.executeQuery();

            List<SVC_MACH_MSTRTMsg> machMstrMsgList;
            while (rs.next()) {
                Boolean rslt = Boolean.TRUE;

                this.apiErrMsgList = new ArrayList<String>();
                machMstrMsgList = new ArrayList<SVC_MACH_MSTRTMsg>();
                this.totalRecCnt++;

                BigDecimal svcMachMstrPk = rs.getBigDecimal("SVC_MACH_MSTR_PK");
                if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
                    List<Map<String, Object>> configMdseList = getMdseCdForConfig(rs.getString("TRX_HDR_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));

                    int ordQty = rs.getBigDecimal("ORD_QTY").intValue();
                    for (int i = 0; i < ordQty; i++) {

                        // Create IB NSZC0010 - INSERT_WAREHOUSE Mode
                        SVC_MACH_MSTRTMsg svcMachmstrMsg = new SVC_MACH_MSTRTMsg();
                        NSZC001001PMsg pMsg = createSvcMachMstr(rs, configMdseList);

                        if (isApiError(pMsg, rs.getString("SHPG_PLN_NUM"))) {
                            this.msgList.addAll(this.apiErrMsgList);
                            this.errRecCnt++;
                            rslt = false;
                            rollback();
                            break;
                        }
                        setMachMstrData(svcMachmstrMsg, pMsg);
                        machMstrMsgList.add(svcMachmstrMsg);
                    }
                    if (!rslt) {
                        continue;
                    }

                    for (SVC_MACH_MSTRTMsg tMsg : machMstrMsgList) {
                        // START 2024/02/15 R.Tamura [CSA-QC#63393,MOD]
                        if (isSvcExchange(rs.getString("DS_ORD_TP_CD"))) {
                            configMdseList = getMdseCdForSvcExchng(rs.getString("TRX_HDR_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"), rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                            if (!existsMainMach(configMdseList)) {
                                setErrorInfo(NWAM0991E, new String[] {"glblCmpyCd=" + this.glblCmpyCd + ", svcConfigMstrPk=" + rs.getBigDecimal("SVC_CONFIG_MSTR_PK") });
                                this.msgList.addAll(this.apiErrMsgList);
                                this.errRecCnt++;
                                rslt = false;
                                break;
                            }
                            NSZC001001PMsg pMsg = setApiParamForSvcExchange(rs, tMsg, configMdseList);
                            // Call API
                            NSZC001001 api = new NSZC001001();
                            api.execute(pMsg, ONBATCH_TYPE.BATCH);
                            if (isApiError(pMsg, rs.getString("SHPG_PLN_NUM"))) {
                                this.msgList.addAll(this.apiErrMsgList);
                                this.errRecCnt++;
                                rslt = false;
                                rollback();
                                break;
                            }
                        } else {
                            // Ship IB NSZC0010 - SHIPMENT Mode
                            rslt = shipmentSvcMachMstr(rs, tMsg, configMdseList);
                            if (!rslt) {
                                this.msgList.addAll(this.apiErrMsgList);
                                this.errRecCnt++;
                                rollback();
                                break;
                            }
                        }
                        // END 2024/02/15 R.Tamura [CSA-QC#63393,MOD]
                    }
                    if (!rslt) {
                        continue;
                    }
                }

                String svcMachMstrStsMach = rs.getString("SVC_MACH_MSTR_STS_CD_M");
                if (SVC_MACH_MSTR_STS.INSTALLED.equals(svcMachMstrStsMach)) {
                    // Install IB NSZC0010 - INSTALLATION Mode
                    rslt = installSvcMachMstr(rs, machMstrMsgList);

                    if (!rslt) {
                        this.msgList.addAll(this.apiErrMsgList);
                        this.errRecCnt++;
                        rollback();
                        continue;
                    }
                    if (!hldRelease(rs)) {
                        this.errRecCnt++;
                        rollback();
                        continue;
                    }
                } else if (S21StringUtil.isEmpty(svcMachMstrStsMach)) {
                    if (!hldRelease(rs)) {
                        this.errRecCnt++;
                        rollback();
                        continue;
                    }
                }
                // Line Level- Machine Master Level
                commit();

            }

            if (errRecCnt > 0) {
                this.termCd = TERM_CD.WARNING_END;
            }

        } catch (SQLException e) {
            sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(ps, rs);
        }

    }

    private NSZC001001PMsg createSvcMachMstr(ResultSet rs, List<Map<String, Object>> configMdseList) throws SQLException {

        NSZC001001PMsg pMsg = new NSZC001001PMsg();

        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSERT_WAREHOUSE.code);
        //            ZYPEZDItemValueSetter.setValue(pMsg.serNum, null);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rs.getString("MDSE_CD"));

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.CREATED);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, rs.getString("SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, rs.getString("ACTL_SHIP_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.effFromDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        //        ZYPEZDItemValueSetter.setValue(pMsg.svcByLineBizTpCd, LINE_BIZ_TP.LFS);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachQty, BigDecimal.ONE);
        String curLocNum = rs.getString("INVTY_LOC_CD");
        if (!ZYPCommonFunc.hasValue(curLocNum)) {
            curLocNum = rs.getString("SHIP_TO_CUST_CD");
        }
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, curLocNum);
        setCmptMachList(pMsg, configMdseList);

        // Call API
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        return pMsg;

    }

    private boolean shipmentSvcMachMstr(ResultSet rs, SVC_MACH_MSTRTMsg tMsg, List<Map<String, Object>> configMdseList) throws SQLException {

        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.SHIPMENT.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, tMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, rs.getString("SVC_MACH_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, rs.getString("SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, rs.getString("SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, rs.getString("SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.thirdLineAddr, rs.getString("SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.frthLineAddr, rs.getString("SHIP_TO_FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, rs.getString("SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, rs.getString("SHIP_TO_ST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.provNm, rs.getString("SHIP_TO_PROV_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, rs.getString("SHIP_TO_CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, rs.getString("SHIP_TO_POST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, rs.getString("SHIP_TO_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, rs.getString("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, rs.getString("ACTL_SHIP_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, rs.getString("TRX_HDR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, rs.getString("TRX_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, rs.getString("TRX_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, rs.getString("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, rs.getString("SHPG_PLN_NUM"));
        //        ZYPEZDItemValueSetter.setValue(pMsg.soNum, rs.getString("STK_STS_CD"));
        //        ZYPEZDItemValueSetter.setValue(pMsg.soSlpNum, rs.getString("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, rs.getString("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, rs.getString("DS_ORD_RSN_CD"));

        BigDecimal svcConfigMstrDtlPk = getConfigMstrDtl(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"), rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        if (ZYPCommonFunc.hasValue(svcConfigMstrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
        }

        SVC_CONFIG_MSTRTMsg svcConfigMsrTmsg = getConfigMstrForPk(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
        if (svcConfigMsrTmsg != null) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, svcConfigMsrTmsg.svcMachMstrPk);

            SVC_MACH_MSTRTMsg svcMachMstrTmsg = getSvcMachMstr(svcConfigMsrTmsg.svcMachMstrPk.getValue());
            if (svcMachMstrTmsg != null) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
            }
        }

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, getSvcMachTrxTpCd(rs.getString("DS_ORD_TP_CD")));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_01, rs.getString("FIRST_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_02, rs.getString("SCD_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_03, rs.getString("THIRD_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_04, rs.getString("FRTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_05, rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_06, rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, rs.getString("PRC_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, rs.getString("HARD_DRIVE_RMV_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, rs.getString("HARD_DRIVE_ERASE_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, rs.getString("LEASE_RTRN_FEE_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.svcLicCnt, BigDecimal.ZERO);

        ZYPEZDItemValueSetter.setValue(pMsg.pickSvcConfigMstrPk, rs.getBigDecimal("PICK_SVC_CONFIG_MSTR_PK"));

        setCmptMachList(pMsg, configMdseList);

        //        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, data.getSvcMachMstrLocStsCd());
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, rs.getString("BILL_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, rs.getString("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, rs.getString("SOLD_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, rs.getString("SHIP_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, rs.getString("SHIP_TO_CUST_LOC_CD"));

        // Dealer Sales
        if (isDealerOrd(rs.getString("DS_ORD_TP_CD"))) {
            pMsg.svcConfigMstrPk.clear();
            pMsg.pickSvcConfigMstrPk.clear();
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.DEALER_SERVICE);
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);
        } else {
            List<Map<String, Object>> ctxMapList = getIbOwnerCd(rs.getString("TRX_HDR_NUM"), "IB_OWNER_OVERRIDE");
            if (ctxMapList.size() > 0) {
                // Rental/Loan/EMSD
                String firstBizCtxAttrbTxt = (String) ctxMapList.get(0).get("FIRST_BIZ_CTX_ATTRB_TXT");
                ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, getSellToCustCd(firstBizCtxAttrbTxt));
                ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, firstBizCtxAttrbTxt);
            }
        }
        NSZC001001 api = new NSZC001001();
        api.execute(pMsg, ONBATCH_TYPE.BATCH);

        if (isApiError(pMsg, rs.getString("SHPG_PLN_NUM"))) {
            return false;
        }

        // Create Service Machine Contact
        List<Map<String, Object>> cpoCtacList = getCpoCtacInfo(rs.getString("TRX_HDR_NUM"), rs.getBigDecimal("DS_CPO_CONFIG_PK"));
        insertSvcMachCtacPsn(tMsg.svcMachMstrPk.getValue(), cpoCtacList);
        return true;
    }

    private boolean installSvcMachMstr(ResultSet rs, List<SVC_MACH_MSTRTMsg> svcMachMstrList) throws SQLException {

        if (svcMachMstrList.size() > 0) {
            List<NSZC001001PMsg> pMsgList = new ArrayList<NSZC001001PMsg>();

            for (SVC_MACH_MSTRTMsg svcMachMstrMsg : svcMachMstrList) {

                NSZC001001PMsg pMsg = new NSZC001001PMsg();

                ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSTALLATION.code);

                ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, svcMachMstrMsg.svcMachMstrPk);
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
                ZYPEZDItemValueSetter.setValue(pMsg.istlDt, salesDate);
                ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
                ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);

                pMsgList.add(pMsg);
            }
            // Call API
            NSZC001001 api = new NSZC001001();
            api.execute(pMsgList, ONBATCH_TYPE.BATCH);

            boolean isErrorEnd = false;
            for (NSZC001001PMsg pMsg : pMsgList) {
                if (isApiError(pMsg, rs.getString("SHPG_PLN_NUM"))) {
                    isErrorEnd = true;
                }

            }
            return !isErrorEnd;
        } else {
            NSZC001001PMsg pMsg = new NSZC001001PMsg();

            ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pMsg.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.INSTALLATION.code);

            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.INSTALLED);
            ZYPEZDItemValueSetter.setValue(pMsg.istlDt, salesDate);
            ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.AT_CUSTOMER);

            // Call API
            NSZC001001 api = new NSZC001001();
            api.execute(pMsg, ONBATCH_TYPE.BATCH);

            if (isApiError(pMsg, rs.getString("SHPG_PLN_NUM"))) {
                return false;
            }
            return true;
        }
    }

    private boolean hldRelease(ResultSet rs) throws SQLException {

        // Search Hold Data
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("cpoOrdNum", rs.getString("TRX_HDR_NUM"));
        queryParam.put("cpoOrdLineNum", rs.getString("TRX_LINE_NUM"));
        queryParam.put("cpoOrdLineSubNum", rs.getString("TRX_LINE_SUB_NUM"));
        queryParam.put("dsCpoConfigPk", rs.getBigDecimal("DS_CPO_CONFIG_PK"));
        queryParam.put("shpgPlnNum", rs.getString("SHPG_PLN_NUM"));
        queryParam.put("hldRsnS03", HLD_RSN.ARRIVED_WAITING_FOR_INSTALLATION);
        queryParam.put("relflgN", ZYPConstant.FLG_OFF_N);
        List<BigDecimal> hldPkList = (ArrayList<BigDecimal>) ssmBatchClient.queryObjectList("searchHoldReleaseTarget", queryParam);

        for (BigDecimal hldPk : hldPkList) {
            NWZC033001PMsg pmsg = new NWZC033001PMsg();
            ZYPEZDItemValueSetter.setValue(pmsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pmsg.hldPk, hldPk);
            ZYPEZDItemValueSetter.setValue(pmsg.cpoOrdNum, rs.getString("TRX_HDR_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineNum, rs.getString("TRX_LINE_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.cpoDtlLineSubNum, rs.getString("TRX_LINE_SUB_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.shpgPlnNum, rs.getString("SHPG_PLN_NUM"));
            ZYPEZDItemValueSetter.setValue(pmsg.slsDt, salesDate);
            ZYPEZDItemValueSetter.setValue(pmsg.hldRelRsnCd, HLD_REL_RSN.AUTO);

            new NWZC033001().execute(pmsg, ONBATCH_TYPE.BATCH);
            if (pmsg.xxMsgIdList.getValidCount() > 0) {
                for (int i = 0; i < pmsg.xxMsgIdList.getValidCount(); i++) {
                    setErrorInfo(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                    S21InfoLogOutput.println(pmsg.xxMsgIdList.no(i).xxMsgId.getValue());
                }
                return false;
            }
        }
        return true;
    }

    private List<Map<String, Object>> getMdseCdForConfig(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);
        param.put("svcMachTpCdMach", SVC_MACH_TP.MACHINE);
        param.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getMdseCdForConfig", param);
        return rtnList;
    }

    private void setCmptMachList(NSZC001001PMsg pMsg, List<Map<String, Object>> configMdseList) {
        int i = 0;
        for (Map<String, Object> mdse : configMdseList) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).mdseCd, (String) mdse.get("MDSE_CD"));
            ZYPEZDItemValueSetter.setValue(pMsg.xxCmptMachList.no(i).svcMachTpCd, (String) mdse.get("SVC_MACH_TP_CD"));
            i++;
        }
        pMsg.xxCmptMachList.setValidCount(i);
    }

    private void setMachMstrData(SVC_MACH_MSTRTMsg svcMachMstrMsg, NSZC001001PMsg pMsg) {
        ZYPEZDItemValueSetter.setValue(svcMachMstrMsg.svcMachMstrPk, pMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(svcMachMstrMsg.serNum, pMsg.serNum);
    }

    private BigDecimal getConfigMstrDtl(BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) || !ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("svcMachMstrPk", svcMachMstrPk);

        return (BigDecimal) ssmBatchClient.queryObject("getConfigMstrDtl", param);
    }

    private SVC_CONFIG_MSTRTMsg getConfigMstrForPk(BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return null;
        }
        SVC_CONFIG_MSTRTMsg param = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcConfigMstrPk, svcConfigMstrPk);

        return (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(param);
    }

    private SVC_MACH_MSTRTMsg getSvcMachMstr(BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk)) {
            return null;
        }
        SVC_MACH_MSTRTMsg param = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(param.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(param);
    }

    private String getSvcMachTrxTpCd(String dsOrdTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            return null;
        }
        List<Map<String, Object>> rsltMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "IB_TRX_ORD_MAP");

        if (rsltMapList.size() == 0) {
            return null;
        }

        String svcMachTrxTpCd = (String) rsltMapList.get(0).get("FIRST_BIZ_CTX_ATTRB_TXT");
        if (ZYPCommonFunc.hasValue(svcMachTrxTpCd) && svcMachTrxTpCd.length() > SVC_MACH_TRX_TP_CD_LEN) {
            svcMachTrxTpCd = svcMachTrxTpCd.substring(0, SVC_MACH_TRX_TP_CD_LEN);
        }

        return svcMachTrxTpCd;
    }

    private List<Map<String, Object>> getOrdCatgBizCtxForDsOrdTp(String dsOrdTpCd, String ordCatgCtxTpCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdTpCd) || !ZYPCommonFunc.hasValue(ordCatgCtxTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsaOrdTpCd", dsOrdTpCd);
        param.put("ordCatgCtxTpCd", ordCatgCtxTpCd);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getOrdCatgBizCtxForDsOrdTp", param);
        return rtnMapList;
    }

    private boolean isDealerOrd(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "DEALER_ORDER");
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }

    private String getSellToCustCd(String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("billToCustCd", billToCustCd);

        return (String) ssmBatchClient.queryObject("getSellToCustCd", param);
    }

    private List<Map<String, Object>> getCpoCtacInfo(String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getCpoCtacInfo", param);
        return rtnList;
    }

    private void insertSvcMachCtacPsn(BigDecimal svcMachMstrPk, List<Map<String, Object>> cpoCtacList) {
        if (!ZYPCommonFunc.hasValue(svcMachMstrPk) || cpoCtacList == null) {
            return;
        }

        for (Map<String, Object> ctac : cpoCtacList) {
            // check SVC_MACH_CTAC_PSN data
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("glblCmpyCd", this.glblCmpyCd);
            param.put("svcMachMstrPk", svcMachMstrPk);
            param.put("dsCtacPntPk", (BigDecimal) ctac.get("DS_CTAC_PNT_PK"));
            param.put("svcCtacTpCd", (String) ctac.get("SVC_CTAC_TP_CD"));
            param.put("slsDt", this.salesDate);
            param.put("defEffThruDt", DEF_EFF_THRU_DT);
            BigDecimal chkSvcMachCtacPsn = (BigDecimal) ssmBatchClient.queryObject("countSvcMachCtacPsn", param);
            if (BigDecimal.ZERO.compareTo(chkSvcMachCtacPsn) < 0) {
                continue;
            }

            SVC_MACH_CTAC_PSNTMsg tMsg = new SVC_MACH_CTAC_PSNTMsg();
            ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachCtacPsnPk, ZYPOracleSeqAccessor.getNumberBigDecimal("SVC_MACH_CTAC_PSN_SQ"));
            ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
            ZYPEZDItemValueSetter.setValue(tMsg.dsCtacPntPk, (BigDecimal) ctac.get("DS_CTAC_PNT_PK"));
            ZYPEZDItemValueSetter.setValue(tMsg.svcCtacTpCd, (String) ctac.get("SVC_CTAC_TP_CD"));
            ZYPEZDItemValueSetter.setValue(tMsg.effFromDt, this.salesDate);
            ZYPEZDItemValueSetter.setValue(tMsg.dsPrimLocFlg, ZYPConstant.FLG_OFF_N);
            EZDTBLAccessor.insert(tMsg);
            if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
                setErrorInfo(NWZM1023E, new String[] {"SVC_MACH_CTAC_PSN", "svcMachMstrPk=" + tMsg.svcMachMstrPk.getValue() });
            }
        }
    }

    private List<Map<String, Object>> getIbOwnerCd(String cpoOrdNum, String ordCatgCtxTpCd) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(ordCatgCtxTpCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("ordCatgCtxTpCd", ordCatgCtxTpCd);

        @SuppressWarnings("unchecked")
        List<Map<String, Object>> rtnMapList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getIbOwnerCd", param);
        return rtnMapList;
    }

    private boolean isApiError(NSZC001001PMsg pMsg, String shpgPlnNum) {
        if (pMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
                setApiErrorInfo(shpgPlnNum, "NSZC001001", pMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            return true;
        }
        return false;
    }

    private void setApiErrorInfo(String shpgPlnNum, String apiBizId, String xxMsgId) {
        String[] params = new String[] {apiBizId, xxMsgId, "shpgPlnNum=" + shpgPlnNum };
        setErrorInfo(NWAM0323W, params);
    }

    private void setErrorInfo(String msgId, String[] params) {
        S21InfoLogOutput.println(msgId, params);
        this.apiErrMsgList.add(S21MessageFunc.clspGetMessage(msgId, params));
    }

    private void setErrorInfo(String msgId) {
        S21InfoLogOutput.println(msgId);
        this.apiErrMsgList.add(S21MessageFunc.clspGetMessage(msgId));
    }

    private S21SsmExecutionParameter getExecPrm() {
        S21SsmExecutionParameter execPrm = new S21SsmExecutionParameter();
        execPrm.setFetchSize(FETCH_SIZE_MAX);
        execPrm.setFetchDirection(ResultSet.FETCH_FORWARD);
        execPrm.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execPrm.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        return execPrm;
    }

    @Override
    protected void termRoutine() {
        setTermState(termCd, totalRecCnt - errRecCnt, errRecCnt, totalRecCnt);
    }

    // START 2024/02/15 R.Tamura [CSA-QC#63393,ADD]
    private boolean isSvcExchange(String dsOrdTpCd) {
        List<Map<String, Object>> ctxMapList = getOrdCatgBizCtxForDsOrdTp(dsOrdTpCd, "SERVICE_EXCHANGE");
        if (ctxMapList.size() > 0) {
            return true;
        }
        return false;
    }

    private boolean existsMainMach(List<Map<String, Object>> configMdseList) {
        String svcMachTpCd = null;
        for (Map<String, Object> data : configMdseList) {
            svcMachTpCd = (String) data.get("SVC_MACH_TP_CD");
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                return true;
            }
        }
        return false;
    }

    private NSZC001001PMsg setApiParamForSvcExchange(ResultSet rs, SVC_MACH_MSTRTMsg tMsg, List<Map<String, Object>> configMdseList) throws SQLException {
        NSZC001001PMsg pMsg = new NSZC001001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pMsg.slsDt, this.salesDate);
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, ProcessMode.SERVICE_EXCHANGE.code);
        ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrPk, getSvcConfigMstrPk(rs.getBigDecimal("DS_CPO_CONFIG_PK"), rs.getBigDecimal("SVC_CONFIG_MSTR_PK"), rs.getString("SVC_MACH_TP_CD")));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, tMsg.svcMachMstrPk);
        ZYPEZDItemValueSetter.setValue(pMsg.serNum, tMsg.serNum);
        ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, rs.getString("MDSE_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTpCd, getSvcMachTpForSvcExchng(rs.getString("MDSE_CD"), configMdseList));
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrStsCd, SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        ZYPEZDItemValueSetter.setValue(pMsg.locNm, rs.getString("SHIP_TO_LOC_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.firstLineAddr, rs.getString("SHIP_TO_FIRST_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.scdLineAddr, rs.getString("SHIP_TO_SCD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.thirdLineAddr, rs.getString("SHIP_TO_THIRD_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.frthLineAddr, rs.getString("SHIP_TO_FRTH_LINE_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctyAddr, rs.getString("SHIP_TO_CTY_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.stCd, rs.getString("SHIP_TO_ST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.provNm, rs.getString("SHIP_TO_PROV_ADDR"));
        ZYPEZDItemValueSetter.setValue(pMsg.cntyNm, rs.getString("SHIP_TO_CNTY_NM"));
        ZYPEZDItemValueSetter.setValue(pMsg.postCd, rs.getString("SHIP_TO_POST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctryCd, rs.getString("SHIP_TO_CTRY_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.stkStsCd, rs.getString("STK_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipFromWhCd, rs.getString("INVTY_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.shipDt, rs.getString("ACTL_SHIP_DT"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoOrdNum, rs.getString("TRX_HDR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineNum, rs.getString("TRX_LINE_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.cpoDtlLineSubNum, rs.getString("TRX_LINE_SUB_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.custIssPoNum, rs.getString("CUST_ISS_PO_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.shpgPlnNum, rs.getString("SHPG_PLN_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.autoCratFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, rs.getString("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.dsOrdRsnCd, rs.getString("DS_ORD_RSN_CD"));

        BigDecimal svcConfigMstrDtlPk = getConfigMstrDtl(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"), rs.getBigDecimal("SVC_MACH_MSTR_PK"));
        if (ZYPCommonFunc.hasValue(svcConfigMstrDtlPk)) {
            ZYPEZDItemValueSetter.setValue(pMsg.svcConfigMstrDtlPk, svcConfigMstrDtlPk);
        }
        if (SVC_MACH_TP.MACHINE.equals(pMsg.svcMachTpCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, rs.getBigDecimal("SVC_MACH_MSTR_PK"));
            ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, rs.getString("SER_NUM"));
        } else {
            String configSvcMachTpCd;
            BigDecimal prntSvcMachMstrPk = null;
            for (Map<String, Object> configMdse : configMdseList) {
                configSvcMachTpCd = (String) configMdse.get("SVC_MACH_TP_CD");
                if (SVC_MACH_TP.MACHINE.equals(configSvcMachTpCd)) {
                    prntSvcMachMstrPk = (BigDecimal) configMdse.get("SVC_MACH_MSTR_PK");
                    break;
                }
            }

            SVC_MACH_MSTRTMsg svcMachMstrTmsg;
            if (ZYPCommonFunc.hasValue(prntSvcMachMstrPk)) {
                ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, prntSvcMachMstrPk);
                svcMachMstrTmsg = getSvcMachMstr(prntSvcMachMstrPk);
                if (svcMachMstrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                }
            } else {
                SVC_CONFIG_MSTRTMsg svcConfigMsrTmsg = getConfigMstrForPk(rs.getBigDecimal("SVC_CONFIG_MSTR_PK"));
                if (svcConfigMsrTmsg != null) {
                    ZYPEZDItemValueSetter.setValue(pMsg.prntSvcMachMstrPk, svcConfigMsrTmsg.svcMachMstrPk);

                    svcMachMstrTmsg = getSvcMachMstr(svcConfigMsrTmsg.svcMachMstrPk.getValue());
                    if (svcMachMstrTmsg != null) {
                        ZYPEZDItemValueSetter.setValue(pMsg.prntSerNum, svcMachMstrTmsg.serNum);
                    }
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachUsgStsCd, SVC_MACH_USG_STS.IN_TRANSIT);
        ZYPEZDItemValueSetter.setValue(pMsg.svcMachTrxTpCd, rs.getString("DS_ORD_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_01, rs.getString("FIRST_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_02, rs.getString("SCD_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_03, rs.getString("THIRD_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_04, rs.getString("FRTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_05, rs.getString("FIFTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.ctrlFldTxt_06, rs.getString("SIXTH_BLLG_ATTRB_VAL_TXT"));
        ZYPEZDItemValueSetter.setValue(pMsg.prcContrNum, rs.getString("PRC_CONTR_NUM"));
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveRmvInclFlg, rs.getString("HARD_DRIVE_RMV_INCL_FLG"));
        ZYPEZDItemValueSetter.setValue(pMsg.hardDriveEraseInclFlg, rs.getString("HARD_DRIVE_ERASE_INCL_FLG"));
        String leaseRtrnFeeInclFlg = rs.getString("LEASE_RTRN_FEE_INCL_FLG");
        if (ZYPCommonFunc.hasValue(leaseRtrnFeeInclFlg)) {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, leaseRtrnFeeInclFlg);
        } else {
            ZYPEZDItemValueSetter.setValue(pMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
        }
        ZYPEZDItemValueSetter.setValue(pMsg.dsKeyAcctFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pMsg.svcLicCnt, BigDecimal.ZERO);

        setCmptMachList(pMsg, configMdseList);

        ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrLocStsCd, rs.getString("SVC_MACH_MSTR_LOC_STS_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ownrAcctNum, rs.getString("BILL_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.ownrLocNum, rs.getString("BILL_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToAcctNum, rs.getString("SELL_TO_CUST_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.billToLocNum, rs.getString("SOLD_TO_CUST_LOC_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocAcctNum, rs.getString("SHIP_TO_CUST_ACCT_CD"));
        ZYPEZDItemValueSetter.setValue(pMsg.curLocNum, rs.getString("SHIP_TO_CUST_LOC_CD"));
        return pMsg;
    }

    private List<Map<String, Object>> getMdseCdForSvcExchng(String cpoOrdNum, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(dsCpoConfigPk) || !ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return new ArrayList<Map<String, Object>>();
        }
        boolean existsMachFlg = false;
        // Exists Config Machine
        List<Map<String, Object>> rtnList = getExistConfigMachForSvcExchng(cpoOrdNum, svcConfigMstrPk);
        String svcMachTpCd = null;
        for (Map<String, Object> existsData : rtnList) {
            svcMachTpCd = (String) existsData.get("SVC_MACH_TP_CD");
            if (SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
                existsMachFlg = true;
                break;
            }
        }
        List<Map<String, Object>> newConfigList = getNewConfigMachForSvcExchng(cpoOrdNum, dsCpoConfigPk, svcConfigMstrPk);
        for (Map<String, Object> newData : newConfigList) {
            if (existsMachFlg) {
                newData.put("SVC_MACH_TP_CD", SVC_MACH_TP.ACCESSORY);
            }
            rtnList.add(newData);
        }

        return rtnList;
    }

    private List<Map<String, Object>> getExistConfigMachForSvcExchng(String cpoOrdNum, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        return NWXC412001.autoCast(ssmBatchClient.queryObjectList("getExistConfigMachForSvcExchng", param));
    }

    private List<Map<String, Object>> getNewConfigMachForSvcExchng(String cpoOrdNum, BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("cpoOrdNum", cpoOrdNum);
        param.put("dsCpoConfigPk", dsCpoConfigPk);
        param.put("svcConfigMstrPk", svcConfigMstrPk);
        param.put("svcMachTpCdMach", SVC_MACH_TP.MACHINE);
        param.put("svcMachTpCdAcc", SVC_MACH_TP.ACCESSORY);
        param.put("configCatgOut", CONFIG_CATG.OUTBOUND);
        return NWXC412001.autoCast(ssmBatchClient.queryObjectList("getNewConfigMachForSvcExchng", param));
    }

    private String getSvcMachTpForSvcExchng(String mdseCd, List<Map<String, Object>> configMdseList) {
        String svcMachTpCd = SVC_MACH_TP.ACCESSORY;
        String configMdseCd;
        for (Map<String, Object> data : configMdseList) {
            configMdseCd = (String) data.get("MDSE_CD");
            if (mdseCd.equals(configMdseCd)) {
                svcMachTpCd = (String) data.get("SVC_MACH_TP_CD");
            }
        }
        return svcMachTpCd;
    }

    private BigDecimal getSvcConfigMstrPk(BigDecimal dsCpoConfigPk, BigDecimal svcConfigMstrPk, String svcMachTpCd) {
        if (ZYPCommonFunc.hasValue(svcConfigMstrPk) || SVC_MACH_TP.MACHINE.equals(svcMachTpCd)) {
            return svcConfigMstrPk;
        }

        // get Accessory SVC_CONFIG_MSTR_PK
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", this.glblCmpyCd);
        param.put("dsCpoConfigPk", dsCpoConfigPk);

        return (BigDecimal) ssmBatchClient.queryObject("getSvcConfigMstrPkOfAcc", param);
    }
    // END 2024/02/15 R.Tamura [CSA-QC#63393,ADD]

}

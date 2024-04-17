/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NMA.NMAB701001;

import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010001;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010002;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010003;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010004;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010005;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010006;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010007;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.CSV_ID_NMA7010008;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.DEFAULT_FETCH_SIZE;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.EXTN_KEY;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.MAX_SORT_NUM;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.MSG_UPLD_CSV_RQST_PK;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NMAM0176E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NMAM0177E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NMAM8214E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NMAM8228E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NYXM0001I;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.NYXM0002E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.RESULT_MSG_ERR;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.RESULT_MSG_INS;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.RESULT_MSG_UPD;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.ZYPM0181E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.ZZZM9025E;
import static com.canon.cusa.s21.batch.NMA.NMAB701001.constant.NMAB701001Constant.ZZZM9026E;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.ART10FMsg;
import business.db.GLBL_CMPYTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDL_NMTMsgArray;
import business.db.MKT_MDL_SEGTMsg;
import business.db.MKT_MDL_SEGTMsgArray;
import business.db.PRC_ADDL_CHRG_TPTMsg;
import business.db.PRC_ADDL_CHRG_TPTMsgArray;
import business.db.PRC_CATGTMsg;
import business.db.PRC_CATGTMsgArray;
import business.db.PRC_EQUIP_CONTR_ATTRBTMsg;
import business.db.PRC_EQUIP_TPTMsg;
import business.db.PRC_EQUIP_TPTMsgArray;
import business.db.PRC_FMLATMsg;
import business.db.PRC_FMLATMsgArray;
import business.db.PRC_GENL_CONTR_ATTRBTMsg;
import business.db.PRC_IN_OUT_RGTMsg;
import business.db.PRC_IN_OUT_RGTMsgArray;
import business.db.PRC_LIST_ADDL_CHRGTMsg;
import business.db.PRC_LIST_BANDTMsg;
import business.db.PRC_LIST_BANDTMsgArray;
import business.db.PRC_LIST_EQUIPTMsg;
import business.db.PRC_LIST_LEASE_RATETMsg;
import business.db.PRC_LIST_LEASE_RTRNTMsg;
import business.db.PRC_LIST_SPLY_PGMTMsg;
import business.db.PRC_LIST_SVCTMsg;
import business.db.PRC_LIST_SVC_TIERTMsg;
import business.db.PRC_LIST_TI_VALTMsg;
import business.db.PRC_LIST_TPTMsg;
import business.db.PRC_LIST_TPTMsgArray;
import business.db.PRC_PGM_TPTMsg;
import business.db.PRC_PGM_TPTMsgArray;
import business.db.PRC_QLFY_TPTMsg;
import business.db.PRC_QLFY_TPTMsgArray;
import business.db.PRC_RATE_TPTMsg;
import business.db.PRC_RATE_TPTMsgArray;
import business.db.PRC_SVC_CONTR_ATTRBTMsg;
import business.db.PRC_SVC_CONTR_TPTMsg;
import business.db.PRC_SVC_CONTR_TPTMsgArray;
import business.db.PRC_SVC_PLN_TPTMsg;
import business.db.PRC_SVC_PLN_TPTMsgArray;
import business.db.PRC_SVC_TIER_CONTR_ATTRBTMsg;
import business.db.PRC_SVC_TIER_TPTMsg;
import business.db.PRC_SVC_TIER_TPTMsgArray;
import business.db.PRC_SVC_ZONETMsg;
import business.db.PRC_SVC_ZONETMsgArray;
import business.db.PRC_TERM_UOMTMsg;
import business.db.PRC_TERM_UOMTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SPLY_AGMT_PLNTMsg;
import business.db.SPLY_AGMT_PLNTMsgArray;
import business.db.SVC_SEGTMsg;
import business.db.SVC_SEGTMsgArray;
import business.db.UPLD_CSV_RQSTTMsg;

import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceListCheckUtil;
import com.canon.cusa.s21.common.NMX.NMXC105001.NMXC105001PriceListCheckUtilCd;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.ZYP.csvupload.ZYPCSVUploadMessenger;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.batch.S21BatchMain;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper;
import com.canon.cusa.s21.framework.batch.S21RequestBatchHelper.REQUEST_STATUS;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.exec.context.S21SsmExecutionParameter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 *<pre>
 * Mass Upload Price List
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/12   Fujitsu         M.Ohno          Create
 * 2016/06/16   Hitachi         Y.Takeno        Update          QC#8156
 * 2017/08/28   Fujitsu         H.Sugawara      Update          QC#19873
 * 2018/03/14   Fujitsu         Mz.Takahashi    Update          QC#23399
 * 2018/05/08   Fujitsu         H.Kumagai       Update          QC#20269
 * 2018/05/17   Fujitsu         H.Kumagai       Update          QC#25367
 * 2018/06/06   Fujitsu         H.Kumagai       Update          QC#26292
 * 2018/07/17   Fujitsu         H.Kumagai       Update          QC#20267
 * 2018/07/26   Fujitsu         W.Honda         Update          QC#26677
 * 2018/08/16   Fujitsu         K.Ishizuka      Update          QC#27716
 * 2018/08/28   Fujitsu         T.Noguchi       Update          QC#27994
 * 2018/09/04   Fujitsu         T.Noguchi       Update          QC#28019
 * 2018/11/26   Fujitsu         H.Kumagai       Update          QC#29300
 * 2023/08/22   Hitachi         H.Watanabe      Update          QC#61385
 *</pre>
 */
public class NMAB701001 extends S21BatchMain {
    /** Global Company Code */
    private String glblCmpyCd = null;

    /** Termination Code */
    private TERM_CD termCd = TERM_CD.NORMAL_END;

    /** Total Count */
    private int totalCount = 0;

    /** Total Normal Count */
    private int totalNmlCount = 0;

    /** Total Error Count */
    private int totalErrCount = 0;

    // Add Start 2018/07/30 QC#27488
    /** Commit Count */
    private int commitConunt = 0;
    // Add End 2018/07/30 QC#27488

    /** process date time */
    private String procDt = null;

    /** ccy Code */
    private String stdCcyCd = null;

    /** SQL access parts */
    private S21SsmLowLevelCodingClient ssmLLClient = null;

    /** S21RequestBatchHelper */
    private S21RequestBatchHelper reqbatch = null;

    /** SQL access parts */
    private S21SsmBatchClient ssmBatchClient = null;

    /** ZYPCSVUploadMessenger */
    private ZYPCSVUploadMessenger messenger = null;

    /**
     * @param args String[]
     */
    public static void main(String[] args) {

        new NMAB701001().executeBatch(NMAB701001.class.getSimpleName());
    }

    @Override
    protected void initRoutine() {
        this.glblCmpyCd = super.getGlobalCompanyCode();

        if (!ZYPCommonFunc.hasValue(this.glblCmpyCd)) {
            throw new S21AbendException(ZZZM9025E, new String[] {"Global Company Code" });
        }

        GLBL_CMPYTMsg glblCmpyTMsg = new GLBL_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(glblCmpyTMsg.glblCmpyCd, this.glblCmpyCd);
        glblCmpyTMsg = (GLBL_CMPYTMsg) S21CacheTBLAccessor.findByKey(glblCmpyTMsg);

        if (glblCmpyTMsg == null) {
            throw new S21AbendException(ZZZM9026E, new String[] {"Global Company Code" });
        }
        this.stdCcyCd = glblCmpyTMsg.stdCcyCd.getValue();
        this.procDt = ZYPDateUtil.getBatProcDate();
        this.reqbatch = new S21RequestBatchHelper();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
        this.ssmLLClient = S21SsmLowLevelCodingClient.getClient(this.getClass());
        // Add Start 2018/07/30 QC#27488	
        this.commitConunt = getCommitCount();
        // Add End 2018/07/30 QC#27488
    }

    @Override
    protected void mainRoutine() {
        if (!this.reqbatch.isRecord()) {
            this.termCd = TERM_CD.WARNING_END;
            return;
        }

        for (ART10FMsg reqmsg : this.reqbatch.getRequestList()) {
            this.doProcess(reqmsg);
        }
    }

    @Override
    protected void termRoutine() {
        if (this.totalErrCount > 0) {
            this.termCd = TERM_CD.WARNING_END;
        }

        super.setTermState(this.termCd, this.totalNmlCount, this.totalErrCount, this.totalCount);

    }

    /**
     * doProcess
     * @param reqMsg ART10FMsg
     */
    protected void doProcess(final ART10FMsg reqMsg) {
        BigDecimal uploadRequestPk = getUploadRequestPK(reqMsg);
        UPLD_CSV_RQSTTMsg upldCSVRqstTMsg = getUpldCSVRqst(uploadRequestPk);
        String csvId = upldCSVRqstTMsg.upldCsvId.getValue();
        this.messenger = new ZYPCSVUploadMessenger(csvId, uploadRequestPk);
        this.termCd = TERM_CD.NORMAL_END;

        S21SsmExecutionParameter execParam = new S21SsmExecutionParameter();
        execParam.setFetchSize(DEFAULT_FETCH_SIZE);
        execParam.setMaxRows(0);
        execParam.setFetchDirection(ResultSet.FETCH_FORWARD);
        execParam.setResultSetType(ResultSet.TYPE_FORWARD_ONLY);
        execParam.setResultSetConcurrency(ResultSet.CONCUR_READ_ONLY);
        if (CSV_ID_NMA7010001.equals(csvId)) {
            // Equip
            doProcessEquip(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010002.equals(csvId)) {
            // Svc
            doProcessSvc(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010003.equals(csvId)) {
            // SvcTier
            doProcessSvcTier(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010004.equals(csvId)) {
            // AddlChrg
            doProcessAddlChrg(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010005.equals(csvId)) {
            // SplyPgm
            doProcessSplyPgm(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010006.equals(csvId)) {
            // LeaseRate
            doProcessLeaseRate(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010007.equals(csvId)) {
            // LeaseRtrn
            doProcessLeaseRtrn(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        } else if (CSV_ID_NMA7010008.equals(csvId)) {
            // TradeIn
            doProcessTradeIn(upldCSVRqstTMsg.upldCsvRqstPk.getValue(), execParam);
        }

        if (TERM_CD.WARNING_END.equals(this.termCd)) {
            this.messenger.uploadMessage();
            reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.WARING_END);
        } else if (TERM_CD.ABNORMAL_END.equals(this.termCd)) {
            this.messenger.uploadMessage();
            reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.ABNOMAL_END);
        } else {
            // START 2016/06/16 [QC#8156,ADD]
            this.messenger.uploadMessage();
            // END 2016/06/16 [QC#8156,ADD]
            this.reqbatch.updateProcessStatus(reqMsg, REQUEST_STATUS.NOMAL_END);
            commit();
        }
    }

    /**
     * doProcessEquip
     * @param uploadRequestPk String
     */
    private void doProcessEquip(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getEquip
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListEquipWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Add Start 2018/07/17 QC#20267
            String MnfMdseCd = null;
            // Add End 2018/07/17 QC#20267
            // 2018/11/26 Add Start QC#29300
            String PkgUomDescTxt = null;
            // 2018/11/26 Add Ens QC#29300
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListEquip(bean, this.glblCmpyCd, this.procDt, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_EQUIP_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListEquipWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Del Start 2017/08/28 QC#19873
                //boolean notUpdateFlg = false;
                // Del End 2017/08/28 QC#19873

                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//                if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Mod Start 2018/07/17 QC#20267
                MnfMdseCd= null;
                bean.setMnfMdseCd(null);
                // Add Start 2018/05/08 QC#20269
                // 2018/09/04 QC#28019 Add Start
                String pkgUomClsCd = null;
                bean.setPkgUomClsCd(null);
                // 2018/09/04 QC#28019 Add End
                // 2018/11/26 Add Start QC#29300
                PkgUomDescTxt = null;
                // 2018/11/26 Add Ens QC#29300
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListEquip(bean, this.glblCmpyCd, this.procDt, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }

                if (ZYPCommonFunc.hasValue(bean.getMnfMdseCd())) {
                    MnfMdseCd = bean.getMnfMdseCd();
                }
                // 2018/09/04 QC#28019 Add Start
                if (ZYPCommonFunc.hasValue(bean.getPkgUomClsCd())) {
                    pkgUomClsCd = bean.getPkgUomClsCd();
                }
                // 2018/09/04 QC#28019 Add End
                // 2018/11/26 Add Start QC#29300
                if (ZYPCommonFunc.hasValue(bean.getPkgUomDescTxt())) {
                    PkgUomDescTxt = bean.getPkgUomDescTxt();
                }
                // 2018/11/26 Add Ens QC#29300

                bean.allClear();
                // Add End 2018/05/08 QC#20269
                bean.setMnfMdseCd(MnfMdseCd);
                // Mod End 2018/07/17 QC#20267
                // 2018/09/04 QC#28019 Add Start
                bean.setPkgUomClsCd(pkgUomClsCd);
                // 2018/09/04 QC#28019 Add End
                // 2018/11/26 Add Start QC#29300
                bean.setPkgUomDescTxt(PkgUomDescTxt);
                // 2018/11/26 Add Ens QC#29300

                getEquipCdTable(rsSet, bean);

                // Del Start 2017/08/28 QC#19873
                //if (bean.getPrcCatgCd() != null && insertPrcNmMap.get(rsSet.getObject("PRC_CATG_NM")) == null) {
                //    // SearchEquip
                //    Map<String, Object> paramMap = new HashMap<String, Object>();
                //    paramMap.put("glblCmpyCd", this.glblCmpyCd);
                //    paramMap.put("prcCatgCd", bean.getPrcCatgCd());
                //    paramMap.put("prcListEquipConfigNum", rsSet.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
                //    paramMap.put("prcListEquipConfigNm", rsSet.getString("PRC_LIST_EQUIP_CONFIG_NM"));
                //    paramMap.put("prcQlfyTpCd", bean.getPrcQlfyTpCd());
                //    paramMap.put("prcQlfyValTxt", rsSet.getString("PRC_QLFY_VAL_TXT"));
                //    paramMap.put("pkgUomCd", bean.getPkgUomCd());
                //    paramMap.put("prcTermUomCd", bean.getPrcTermUomCd());
                //    paramMap.put("prcTermAot", rsSet.getBigDecimal("PRC_TERM_AOT"));
                //    paramMap.put("effFromDt", rsSet.getString("EFF_FROM_DT"));
                //    paramMap.put("effThruDt", toHighValDate(rsSet.getString("EFF_THRU_DT")));
                //    List<BigDecimal> ssmResult = (List<BigDecimal>) this.ssmBatchClient.queryObjectList("getEquip", paramMap);
                //    if (ssmResult.size() > 0) {
                //        // continue;
                //        notUpdateFlg = true;
                //    }
                //}
                // Del End 2017/08/28 QC#19873

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                    // insert GenlAttrb
                    executeGenlAttrbTMsg(rsSet, bean, prcCatgCd);

                    // insert EquipAttrb
                    executeEquipAttrbTMsg(rsSet, bean, prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                    // if
                    // (!nowPrcNm.equals(rsSet.getString("PRC_CATG_NM")))
                    // {
                    // // update GenlAttrb
                    // executeGenlAttrbTMsg(rsSet, bean, prcCatgCd);
                    //
                    // // update EquipAttrb
                    // executeEquipAttrbTMsg(rsSet, bean, prcCatgCd);
                    // }
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateEquipThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateEquip(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert Equip
                // Del Start 2017/08/28 QC#19873
                //if (!notUpdateFlg) {
                    // Del End 2017/08/28 QC#19873
                    PRC_LIST_EQUIPTMsg inTMsg = new PRC_LIST_EQUIPTMsg();
                    ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                    BigDecimal prcListEquipPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_EQUIP_SQ);
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcListEquipPk, prcListEquipPk);
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcListEquipConfigNum, rsSet.getBigDecimal("PRC_LIST_EQUIP_CONFIG_NUM"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcListEquipConfigNm, rsSet.getString("PRC_LIST_EQUIP_CONFIG_NM"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcQlfyTpCd, bean.getPrcQlfyTpCd());
                    // Mod Start 2018/07/17 QC#20267
                    if (ZYPCommonFunc.hasValue(bean.getMnfMdseCd())) {
                        ZYPEZDItemValueSetter.setValue(inTMsg.prcQlfyValTxt, bean.getMnfMdseCd());
                    } else {
                        ZYPEZDItemValueSetter.setValue(inTMsg.prcQlfyValTxt, rsSet.getString("PRC_QLFY_VAL_TXT"));
                    }
                    // Mod End 2018/07/17 QC#20267
                    ZYPEZDItemValueSetter.setValue(inTMsg.pkgUomCd, bean.getPkgUomCd());
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcListEquipPrcAmt, rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.minPrcAmt, rsSet.getBigDecimal("MIN_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.maxPrcAmt, rsSet.getBigDecimal("MAX_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcTermUomCd, bean.getPrcTermUomCd());
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcTermAot, rsSet.getBigDecimal("PRC_TERM_AOT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.custBidQty, rsSet.getBigDecimal("CUST_BID_QTY"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.mlyPmtAmt, rsSet.getBigDecimal("MLY_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.minLeasePmtAmt, rsSet.getBigDecimal("MIN_LEASE_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.maxLeasePmtAmt, rsSet.getBigDecimal("MAX_LEASE_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.prcFmlaPk, bean.getPrcFmlaPk());
                    // Add Start 2018/06/06 QC#26292
                    ZYPEZDItemValueSetter.setValue(inTMsg.openMktFlg, rsSet.getString("OPEN_MKT_DESC_TXT"));
                    // Add End 2018/06/06 QC#26292
                    ZYPEZDItemValueSetter.setValue(inTMsg.quotRevAmt, rsSet.getBigDecimal("QUOT_REV_AMT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.compCd, rsSet.getString("COMP_CD"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                    ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                    executeTMsg(inTMsg, "PRC_LIST_EQUIP", true);
                    // Del Start 2017/08/28 QC#19873
                //}
                    // Del End 2017/08/28 QC#19873
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
                bean.allClear(); // 2018/08/16 S21_NA#27716 Add
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;

            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_EQUIP_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_EQUIP_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateEquip
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateEquipThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateEquip(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateEquip(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
        // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
        // 2023/08/22 QC#61385 Mod Start
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_EQUIPTMsg pleTMsg = new PRC_LIST_EQUIPTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListEquipPk, (BigDecimal) resultMap.get("PRC_LIST_EQUIP_PK"));
            PRC_LIST_EQUIPTMsg updtTMsg = (PRC_LIST_EQUIPTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcListEquipPrcAmt, rsSet.getBigDecimal("PRC_LIST_EQUIP_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minPrcAmt, rsSet.getBigDecimal("MIN_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxPrcAmt, rsSet.getBigDecimal("MAX_PRC_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.custBidQty, rsSet.getBigDecimal("CUST_BID_QTY"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mlyPmtAmt, rsSet.getBigDecimal("MLY_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minLeasePmtAmt, rsSet.getBigDecimal("MIN_LEASE_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxLeasePmtAmt, rsSet.getBigDecimal("MAX_LEASE_PMT_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcFmlaPk, bean.getPrcFmlaPk());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.openMktFlg, rsSet.getString("OPEN_MKT_DESC_TXT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.quotRevAmt, rsSet.getBigDecimal("QUOT_REV_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.compCd, rsSet.getString("COMP_CD"));
                    executeTMsg(updtTMsg, "PRC_LIST_EQUIP", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessSvc
     * @param uploadRequestPk String
     */
    private void doProcessSvc(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getSvc
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSvcWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvc(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSvcWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvc(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getSvcCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                    // insert GenlAttrb
                    executeGenlAttrbTMsg(rsSet, bean, prcCatgCd);

                    // insert SvcAttrb
                    executeSvcAttrbTMsg(rsSet, bean, prcCatgCd);

                }
                else {
                    prcCatgCd = bean.getPrcCatgCd();
//                    if (!nowPrcNm.equals(rsSet.getString("PRC_CATG_NM"))) {
//                         // update GenlAttrb
//                         executeGenlAttrbTMsg(rsSet, bean,
//                         prcCatgCd);
//                        
//                         // update SvcAttrb
//                         executeSvcAttrbTMsg(rsSet, bean,
//                         prcCatgCd);
//                    }
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateSvcThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateSvc(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert Svc
                PRC_LIST_SVCTMsg inTMsg = new PRC_LIST_SVCTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListSvcPk, prcListSvcPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcRateTpCd, bean.getPrcRateTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgPk, bean.getPrcMtrPkgPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcPlnTpCd, bean.getPrcSvcPlnTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcContrTpCd, bean.getPrcSvcContrTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.bllgMtrLbCd, bean.getMtrLbCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListBandCd, bean.getPrcListBandCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.baseAmt, rsSet.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minBaseAmt, rsSet.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxBaseAmt, rsSet.getBigDecimal("MAX_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termFromMthAot, rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termThruMthAot, rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcZoneCd, bean.getPrcSvcZoneCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.quotRevAmt, rsSet.getBigDecimal("QUOT_REV_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.compCd, rsSet.getString("COMP_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListMdseCd, rsSet.getString("PRC_LIST_MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_SVC", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;

            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateSvc
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateSvcThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateSvc(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateSvc(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
        // 2023/08/22 QC#61385 Mod Start
//          return;
          return true;
        // 2023/08/22 QC#61385 Mod Start
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_SVCTMsg pleTMsg = new PRC_LIST_SVCTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListSvcPk, (BigDecimal) resultMap.get("PRC_LIST_SVC_PK"));
            PRC_LIST_SVCTMsg updtTMsg = (PRC_LIST_SVCTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcRateTpCd, bean.getPrcRateTpCd());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.baseAmt, rsSet.getBigDecimal("BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minBaseAmt, rsSet.getBigDecimal("MIN_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxBaseAmt, rsSet.getBigDecimal("MAX_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcSvcZoneCd, bean.getPrcSvcZoneCd());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.quotRevAmt, rsSet.getBigDecimal("QUOT_REV_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.compCd, rsSet.getString("COMP_CD"));
                    executeTMsg(updtTMsg, "PRC_LIST_SVC", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessSvc
     * @param uploadRequestPk String
     */
    private void doProcessSvcTier(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getSvcTier
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSvcTierWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvcTier(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_TIER_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                //this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSvcTierWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvcTier(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add Start 2018/05/08 QC#20269

                getSvcTierCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                    // insert GenlAttrb
                    executeGenlAttrbTMsg(rsSet, bean, prcCatgCd);

                    // insert SvcTierAttrb
                    executeSvcTierAttrbTMsg(rsSet, bean, prcCatgCd);

                }
                else {
                    prcCatgCd = bean.getPrcCatgCd();
//                    if (!nowPrcNm.equals(rsSet.getString("PRC_CATG_NM"))) {
//                        // // update GenlAttrb
//                        // executeGenlAttrbTMsg(rsSet, bean,
//                        // prcCatgCd);
//                        //
//                        // // update SvcTierAttrb
//                        // executeSvcTierAttrbTMsg(rsSet, bean,
//                        // prcCatgCd);
//                    }
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateSvcTierThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateSvcTier(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert SvcTier
                PRC_LIST_SVC_TIERTMsg inTMsg = new PRC_LIST_SVC_TIERTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListSvcPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SVC_TIER_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListSvcTierPk, prcListSvcPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcTierTpCd, bean.getPrcSvcTierTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgPk, bean.getPrcMtrPkgPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcPlnTpCd, bean.getPrcSvcPlnTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcContrTpCd, bean.getPrcSvcContrTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.bllgMtrLbCd, bean.getMtrLbCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.avgCopyVolCnt, rsSet.getBigDecimal("AVG_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListBandCd, bean.getPrcListBandCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.baseAmt, rsSet.getBigDecimal("BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minBaseAmt, rsSet.getBigDecimal("MIN_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxBaseAmt, rsSet.getBigDecimal("MAX_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termFromMthAot, rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termThruMthAot, rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_SVC_TIER", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;

            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_TIER_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    //this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SVC_TIER_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateSvc
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateSvcTierThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateSvcTier(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateSvcTier(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
            // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
            // 2023/08/22 QC#61385 Mod End
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_SVC_TIERTMsg pleTMsg = new PRC_LIST_SVC_TIERTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListSvcTierPk, (BigDecimal) resultMap.get("PRC_LIST_SVC_TIER_PK"));
            PRC_LIST_SVC_TIERTMsg updtTMsg = (PRC_LIST_SVC_TIERTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT")))
                        || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.avgCopyVolCnt, rsSet.getBigDecimal("AVG_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.baseAmt, rsSet.getBigDecimal("BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minBaseAmt, rsSet.getBigDecimal("MIN_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxBaseAmt, rsSet.getBigDecimal("MAX_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                    executeTMsg(updtTMsg, "PRC_LIST_SVC_TIER", false);
                    return false;
                }
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessSplyPgm
     * @param uploadRequestPk String
     */
    private void doProcessSplyPgm(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getSplyPgm
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSplyPgmWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSplyPgm(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }

//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SPLY_PGM_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListSplyPgmWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSplyPgm(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getSplyPgmCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateSplyPgmThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateSplyPgm(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert SplyPgm
                PRC_LIST_SPLY_PGMTMsg inTMsg = new PRC_LIST_SPLY_PGMTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListSplyPgmPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_SPLY_PGM_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListSplyPgmPk, prcListSplyPgmPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgPk, bean.getPrcMtrPkgPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcPlnTpCd, bean.getPrcSvcPlnTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcContrTpCd, bean.getPrcSvcContrTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.bllgMtrLbCd, bean.getMtrLbCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListBandCd, bean.getPrcListBandCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.totBaseAmt, rsSet.getBigDecimal("TOT_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.splyBaseAmt, rsSet.getBigDecimal("SPLY_BASE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termFromMthAot, rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termThruMthAot, rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcSvcZoneCd, bean.getPrcSvcZoneCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.splyAgmtPlnPk, bean.getSplyAgmtPlnPk());
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_SPLY_PGM", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;
            
            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SPLY_PGM_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_SPLY_PGM_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateSplyPgm
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateSplyPgmThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateSplyPgm(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateSplyPgm(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
            // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
            // 2023/08/22 QC#61385 Mod End
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_SPLY_PGMTMsg pleTMsg = new PRC_LIST_SPLY_PGMTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListSplyPgmPk, (BigDecimal) resultMap.get("PRC_LIST_SPLY_PGM_PK"));
            PRC_LIST_SPLY_PGMTMsg updtTMsg = (PRC_LIST_SPLY_PGMTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCopyVolCnt, rsSet.getBigDecimal("MIN_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCopyVolCnt, rsSet.getBigDecimal("MAX_COPY_VOL_CNT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.totBaseAmt, rsSet.getBigDecimal("TOT_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.splyBaseAmt, rsSet.getBigDecimal("SPLY_BASE_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.cpcAmtRate, rsSet.getBigDecimal("CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minCpcAmtRate, rsSet.getBigDecimal("MIN_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxCpcAmtRate, rsSet.getBigDecimal("MAX_CPC_AMT_RATE"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcMtrPkgBllgMtrPk, bean.getPrcMtrPkgBllgMtrPk());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcSvcZoneCd,  bean.getPrcSvcZoneCd());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.splyAgmtPlnPk, bean.getSplyAgmtPlnPk());
                    executeTMsg(updtTMsg, "PRC_LIST_SPLY_PGM", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessAddlChrg
     * @param uploadRequestPk String
     */
    private void doProcessAddlChrg(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getSplyPgm
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListAddlChrgWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvcSpecChrg(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_ADDL_CHRG_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListAddlChrgWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListSvcSpecChrg(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getAddlChrgCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateAddlChrgThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873
                boolean isCreateFlg = updateAddlChrg(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End

                // insert AddlChrg
                PRC_LIST_ADDL_CHRGTMsg inTMsg = new PRC_LIST_ADDL_CHRGTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListAddlChrgPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_ADDL_CHRG_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListAddlChrgPk, prcListAddlChrgPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.mdseCd, rsSet.getString("MDSE_CD"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcAddlChrgTpCd, bean.getPrcAddlChrgTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.mktMdlSegCd, bean.getMktMdlSegCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.addlChrgPrcAmt, rsSet.getBigDecimal("ADDL_CHRG_PRC_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_ADDL_CHRG", true);
                bean.allClear();
                //2023/08/22 QC#61385 Add Start
                }
                //2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;

            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_ADDL_CHRG_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_ADDL_CHRG_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateAddlChrg
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateAddlChrgThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateAddlChrg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateAddlChrg(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
            // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
            // 2023/08/22 QC#61385 Mod End
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_ADDL_CHRGTMsg pleTMsg = new PRC_LIST_ADDL_CHRGTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListAddlChrgPk, (BigDecimal) resultMap.get("PRC_LIST_ADDL_CHRG_PK"));
            PRC_LIST_ADDL_CHRGTMsg updtTMsg = (PRC_LIST_ADDL_CHRGTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.prcAddlChrgTpCd, bean.getPrcAddlChrgTpCd());
                    ZYPEZDItemValueSetter.setValue(updtTMsg.addlChrgPrcAmt, rsSet.getBigDecimal("ADDL_CHRG_PRC_AMT"));
                    executeTMsg(updtTMsg, "PRC_LIST_ADDL_CHRG", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessAddlChrg
     * @param uploadRequestPk String
     */
    private void doProcessLeaseRate(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getLeaseRate
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRateWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListLeaseRate(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RATE_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRateWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListLeaseRate(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getLeaseRateCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateLeaseRateThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateLeaseRate(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert LeaseRate
                PRC_LIST_LEASE_RATETMsg inTMsg = new PRC_LIST_LEASE_RATETMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListLeaseRatePk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RATE_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListLeaseRatePk, prcListLeaseRatePk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcLeaseCmpyAbbrNm, rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNum, bean.getSellToCustCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.dsAcctNm, rsSet.getString("DS_ACCT_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.prcPgmTpCd, bean.getPrcPgmTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcEquipTpCd, bean.getPrcEquipTpCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.minTotFinAmt, rsSet.getBigDecimal("MIN_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.maxTotFinAmt, rsSet.getBigDecimal("MAX_TOT_FIN_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termFromMthAot, rsSet.getBigDecimal("TERM_FROM_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.termThruMthAot, rsSet.getBigDecimal("TERM_THRU_MTH_AOT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.leaseRate, rsSet.getBigDecimal("LEASE_RATE"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_LEASE_RATE", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;

            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RATE_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RATE_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateLeaseRate
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateLeaseRateThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateLeaseRate(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateLeaseRate(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
            // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
            // 2023/08/22 QC#61385 Mod End
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_LEASE_RATETMsg pleTMsg = new PRC_LIST_LEASE_RATETMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListLeaseRatePk, (BigDecimal) resultMap.get("PRC_LIST_LEASE_RATE_PK"));
            PRC_LIST_LEASE_RATETMsg updtTMsg = (PRC_LIST_LEASE_RATETMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.minTotFinAmt, rsSet.getBigDecimal("MIN_TOT_FIN_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.maxTotFinAmt, rsSet.getBigDecimal("MAX_TOT_FIN_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.leaseRate, rsSet.getBigDecimal("LEASE_RATE"));
                    executeTMsg(updtTMsg, "PRC_LIST_LEASE_RATE", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessAddlChrg
     * @param uploadRequestPk String
     */
    private void doProcessLeaseRtrn(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getLeaseRtrn
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRtrnWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListLeaseRtrnFee(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RTRN_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListLeaseRtrnWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListLeaseRtrnFee(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getLeaseRtrnCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateLeaseRtrnThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateLeaseRtrn(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert LeaseRtrn
                PRC_LIST_LEASE_RTRNTMsg inTMsg = new PRC_LIST_LEASE_RTRNTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListLeaseRtrnPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_LEASE_RTRN_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListLeaseRtrnPk, prcListLeaseRtrnPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcLeaseCmpyAbbrNm, rsSet.getString("PRC_LEASE_CMPY_ABBR_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.svcSegCd, bean.getSvcSegCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.prcInOutRgCd, bean.getPrcInOutRgCd());
                ZYPEZDItemValueSetter.setValue(inTMsg.dstMileAmt, rsSet.getBigDecimal("DST_MILE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.rtrnFeeAmt, rsSet.getBigDecimal("RTRN_FEE_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_LEASE_RTRN", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;
            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RTRN_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //   // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_LEASE_RTRN_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }

    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateLeaseRtrn
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateLeaseRtrnThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateLeaseRtrn(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod Start
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateLeaseRtrn(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
            // 2023/08/22 QC#61385 Mod Start
//            return;
            return true;
            // 2023/08/22 QC#61385 Mod End
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_LEASE_RTRNTMsg pleTMsg = new PRC_LIST_LEASE_RTRNTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListLeaseRtrnPk, (BigDecimal) resultMap.get("PRC_LIST_LEASE_RTRN_PK"));
            PRC_LIST_LEASE_RTRNTMsg updtTMsg = (PRC_LIST_LEASE_RTRNTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.rtrnFeeAmt, rsSet.getBigDecimal("RTRN_FEE_AMT"));
                    executeTMsg(updtTMsg, "PRC_LIST_LEASE_RTRN", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * doProcessAddlChrg
     * @param uploadRequestPk String
     */
    private void doProcessTradeIn(BigDecimal uploadRequestPk, S21SsmExecutionParameter execParam) {

        PreparedStatement stmt = null;
        ResultSet rsSet = null;
        Map<String, String> insertPrcNmMap = new HashMap<String, String>();

        Map<String, Object> ssmParamGetEquip = new HashMap<String, Object>();

        ssmParamGetEquip.put("glblCmpyCd", this.glblCmpyCd);
        ssmParamGetEquip.put("uploadRequestPk", uploadRequestPk);

        try {
            // getTradeIn
            // Del Start 2018/05/08 QC#20269
//            stmt = this.ssmLLClient.createPreparedStatement("getPrcListTradeInWrk", ssmParamGetEquip, execParam);
//            rsSet = stmt.executeQuery();
            // Del End 2018/05/08 QC#20269
            NMXC105001PriceListCheckUtilCd bean = new NMXC105001PriceListCheckUtilCd();
            boolean isSuccess = false;
            int nowError = 0;
            int nowNomal = 0;
            // Del Start 2018/05/08 QC#20269
//            while (rsSet.next()) {
//                bean.setRsSet(rsSet);
//                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListTradeIn(bean, this.glblCmpyCd, this.messenger);
//                if (isSuccess) {
//                    nowNomal++;
//                } else {
//                    nowError++;
//                }
//                bean.allClear();
//            }
//
//            int nowTotal = nowNomal + nowError;
//            this.totalCount = this.totalCount + nowTotal;
//            this.totalNmlCount = this.totalNmlCount + nowNomal;
//            this.totalErrCount = this.totalErrCount + nowError;
//            if (nowTotal <= 0) {
//                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_TI_VAL_WRK");
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
//
//            if (nowError > 0) {
//                // START 2016/06/16 [QC#8156,MOD]
//                // this.messenger.addMessageForFile(NMAM8457E, null);
//                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
//                // END 2016/06/16 [QC#8156,MOD]
//                this.termCd = TERM_CD.WARNING_END;
//                return;
//            }
            // Del End 2018/05/08 QC#20269

            // 2018/08/28 QC#27994 Add Start
            int dispTotalCount = 0;
            int dispErrorCount = 0;
            // 2018/08/28 QC#27994 Add End

            // rsSet.first();
            stmt = this.ssmLLClient.createPreparedStatement("getPrcListTradeInWrk", ssmParamGetEquip, execParam);
            rsSet = stmt.executeQuery();
            while (rsSet.next()) {
                // Add Start 2018/07/26 QC#26677
                // Mod Start 2018/07/30 QC#27488
//              if (DEFAULT_COMMIT_UNIT <= nowNomal) {
                if (this.commitConunt <= nowNomal) {
                // Mod End 2018/07/30 QC#27488
                    commit();
                    this.totalCount = this.totalCount + nowNomal + nowError;
                    this.totalNmlCount = this.totalNmlCount + nowNomal;
                    this.totalErrCount = this.totalErrCount + nowError;
                    // 2018/08/28 QC#27994 Add Start
                    dispTotalCount = dispTotalCount + nowNomal + nowError;
                    dispErrorCount = dispErrorCount + nowError;
                    // 2018/08/28 QC#27994 Add End
                    nowNomal = 0;
                    nowError = 0;
                }
                // Add End 2018/07/26 QC#26677

                // Add Start 2018/05/08 QC#20269
                bean.setRsSet(rsSet);
                isSuccess = NMXC105001PriceListCheckUtil.checkPrcListTradeIn(bean, this.glblCmpyCd, this.messenger);
                if (isSuccess) {
                    nowNomal++;
                } else {
                    nowError++;
                    bean.allClear(); // 2018/08/16 S21_NA#27716 Add
                    continue;
                }
                bean.allClear();
                // Add End 2018/05/08 QC#20269

                getTradeInCdTable(rsSet, bean);

                String prcCatgCd = "";
                if (bean.getPrcCatgCd() == null) {
                    prcCatgCd = ZYPExtnNumbering.getUniqueID(getGlobalCompanyCode(), EXTN_KEY);
                    executeTMsg(insertPrcCatg(rsSet, bean, prcCatgCd), "PRC_CATG", true);
                    insertPrcNmMap.put(rsSet.getString("PRC_CATG_NM"), prcCatgCd);

                } else {
                    prcCatgCd = bean.getPrcCatgCd();
                }

                // 2023/08/22 QC#61385 Mod Start
                // Add Start 2017/08/28 QC#19873
//                updateTradeInThruDt(rsSet, bean);
                // Add End 2017/08/28 QC#19873

                boolean isCreateFlg = updateTradeIn(rsSet, bean);
                if (isCreateFlg) {
                // 2023/08/22 QC#61385 Mod End
                // insert TradeIn
                PRC_LIST_TI_VALTMsg inTMsg = new PRC_LIST_TI_VALTMsg();
                ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, this.glblCmpyCd);
                BigDecimal prcListTiValPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.PRC_LIST_TI_VAL_SQ);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcListTiValPk, prcListTiValPk);
                ZYPEZDItemValueSetter.setValue(inTMsg.prcCatgCd, prcCatgCd);
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlId, bean.getMdlId());
                ZYPEZDItemValueSetter.setValue(inTMsg.mdlNm, rsSet.getString("MDL_NM"));
                ZYPEZDItemValueSetter.setValue(inTMsg.tiAmt, rsSet.getBigDecimal("TI_AMT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.mtrRngReqFlg, rsSet.getString("MTR_RNG_REQ_FLG"));
                ZYPEZDItemValueSetter.setValue(inTMsg.fromMtrCopyVolCnt, rsSet.getBigDecimal("FROM_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.thruMtrCopyVolCnt, rsSet.getBigDecimal("THRU_MTR_COPY_VOL_CNT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effFromDt, rsSet.getString("EFF_FROM_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.effThruDt, rsSet.getString("EFF_THRU_DT"));
                ZYPEZDItemValueSetter.setValue(inTMsg.delFlg, ZYPConstant.FLG_OFF_N);
                executeTMsg(inTMsg, "PRC_LIST_TI_VAL", true);
                bean.allClear();
                // 2023/08/22 QC#61385 Add Start
                }
                // 2023/08/22 QC#61385 Add End
            }

            // Mod Start 2018/05/08 QC#20269
            int nowTotal = nowNomal + nowError;
            this.totalCount = this.totalCount + nowTotal;
            this.totalNmlCount = this.totalNmlCount + nowNomal;
            this.totalErrCount = this.totalErrCount + nowError;
            // 2018/08/28 QC#27994 Mod Start
            //if (nowTotal <= 0) {
            //    this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_TI_VAL_WRK");
            //    this.termCd = TERM_CD.WARNING_END;
            //}
            //
            //if (nowError > 0) {
            //    // START 2016/06/16 [QC#8156,MOD]
            //    // this.messenger.addMessageForFile(NMAM8457E, null);
            //    this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, nowError));
            //    // END 2016/06/16 [QC#8156,MOD]
            //    this.termCd = TERM_CD.WARNING_END;
            //} else {
            //    // START 2016/06/16 [QC#8156,ADD]
            //    this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(nowTotal, 0, nowError));
            //    // END 2016/06/16 [QC#8156,ADD]
            //}
            dispTotalCount = dispTotalCount + nowTotal;
            dispErrorCount = dispErrorCount + nowError;

            if (dispTotalCount <= 0) {
                this.messenger.addMessageForFile(NMAM8214E, "PRC_LIST_TI_VAL_WRK");
                this.termCd = TERM_CD.WARNING_END;
                return;
            }

            if (dispErrorCount > 0) {
                this.messenger.addMessageForFile(NYXM0002E, createResultMessageArg(0, 0, dispErrorCount));
                this.termCd = TERM_CD.WARNING_END;
            } else {
                this.messenger.addMessageForFile(NYXM0001I, createResultMessageArg(dispTotalCount, 0, dispErrorCount));
            }
            // 2018/08/28 QC#27994 Mod End
            // Mod End 2018/05/08 QC#20269

        } catch (SQLException e) {
            super.sqlExceptionHandler(e);
        } finally {
            S21SsmLowLevelCodingClient.closeResource(stmt, rsSet);
        }
    }

    // Add Start 2017/08/28 QC#19873
    /**
     * updateTradeIn
     * @param rsSet ResultSet
     * @param bean NMXC105001PriceListCheckUtilCd
     * @return boolean
     * @throws SQLException
     */
    // 2023/08/22 QC#61385 Mod Start
//    private void updateTradeInThruDt(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    private boolean updateTradeIn(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
    // 2023/08/22 QC#61385 Mod End   
        List<Map<String, Object>> resultList = NMXC105001PriceListCheckUtil.getUpdateTradeIn(rsSet, bean, this.glblCmpyCd);

        if (resultList == null) {
          // 2023/08/22 QC#61385 Mod Start
//          return;
          return true;
          // 2023/08/22 QC#61385 Mod Start
        }

        for (int i = 0; i < resultList.size(); i++) {
            Map<String, Object> resultMap = resultList.get(i);

            PRC_LIST_TI_VALTMsg pleTMsg = new PRC_LIST_TI_VALTMsg();
            ZYPEZDItemValueSetter.setValue(pleTMsg.glblCmpyCd, this.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pleTMsg.prcListTiValPk, (BigDecimal) resultMap.get("PRC_LIST_TI_VAL_PK"));
            PRC_LIST_TI_VALTMsg updtTMsg = (PRC_LIST_TI_VALTMsg) EZDTBLAccessor.findByKeyForUpdateWait(pleTMsg);
            // 2023/08/22 QC#61385 Add Start
            if (ZYPDateUtil.compare((String) resultMap.get("EFF_FROM_DT"), rsSet.getString("EFF_FROM_DT")) == 0) {
                if ((!ZYPCommonFunc.hasValue((String) resultMap.get("EFF_THRU_DT")) && !ZYPCommonFunc.hasValue(rsSet.getString("EFF_THRU_DT"))) 
                    || (ZYPDateUtil.compare((String) resultMap.get("EFF_THRU_DT"), rsSet.getString("EFF_THRU_DT")) == 0)) {
                    ZYPEZDItemValueSetter.setValue(updtTMsg.tiAmt, rsSet.getBigDecimal("TI_AMT"));
                    ZYPEZDItemValueSetter.setValue(updtTMsg.mtrRngReqFlg, rsSet.getString("MTR_RNG_REQ_FLG"));
                    executeTMsg(updtTMsg, "PRC_LIST_TI_VAL", false);
                    return false;
                } 
            }
            // 2023/08/22 QC#61385 Add End
            String effFromDt = (String) rsSet.getString("EFF_FROM_DT");
            ZYPEZDItemValueSetter.setValue(updtTMsg.effThruDt, ZYPDateUtil.addDays(effFromDt, -1));
            EZDTBLAccessor.update(updtTMsg);
        }
        // 2023/08/22 QC#61385 Add Start
        return true;
        // 2023/08/22 QC#61385 Add End
    }
    // Add End 2017/08/28 QC#19873

    /**
     * @param fMsg ART10FMsg
     * @return Upload CSV ID
     */
    private BigDecimal getUploadRequestPK(ART10FMsg reqMsg) {

        String upldCsvReqPk = reqMsg.EZREQCondition.getValue();
        String[] msg = {MSG_UPLD_CSV_RQST_PK};
        String removeDQRequestPK = S21RequestBatchHelper.removeDoubleQuotation(upldCsvReqPk);

        if (S21StringUtil.isEmpty(removeDQRequestPK)) {
            throw new S21AbendException(NMAM8228E, msg);
        }
        return new BigDecimal(removeDQRequestPK);
    }

    /**
     * @param upldCsvReqPk upload CSV Request Pk
     * @return UPLD_CSV_RQSTTMsg
     */
    private UPLD_CSV_RQSTTMsg getUpldCSVRqst(BigDecimal upldCsvRqstPk) {

        UPLD_CSV_RQSTTMsg inTMsg = new UPLD_CSV_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(inTMsg.upldCsvRqstPk, upldCsvRqstPk);

        UPLD_CSV_RQSTTMsg outTMsg = (UPLD_CSV_RQSTTMsg) EZDTBLAccessor.findByKey(inTMsg);
        if (outTMsg == null) {
            throw new S21AbendException(ZYPM0181E);
        }

        return outTMsg;
    }

    /**
     * excecuteUpdate
     * @param array arrayEZDTMsg[]
     */
    private void executeTMsg(EZDTMsg array, String msg, boolean isInsert) {
        if (isInsert) {
            S21FastTBLAccessor.insert(array);
        } else {
            S21FastTBLAccessor.update(array);
        }

        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(array.getReturnCode())) {
            super.rollback();
            if (isInsert) {
                throw new S21AbendException(NMAM0176E, new String[] {msg });
            } else {
                throw new S21AbendException(NMAM0177E, new String[] {msg });
            }

        }
    }

    // Del Start 2017/08/28 QC#19873
    ///**
    // * toHighValDate.
    // * @param dateVal String
    // * @return String
    // */
    //private String toHighValDate(String dateVal) {
    //    if (ZYPCommonFunc.hasValue(dateVal)) {
    //        return dateVal;
    //    }
    //    return HIGH_VAL_DT;
    //}
    // Del End 2017/08/28 QC#19873

    private PRC_CATGTMsg insertPrcCatg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
        // insert PrcCatg
        PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(pcTMsg.glblCmpyCd, this.glblCmpyCd);

        ZYPEZDItemValueSetter.setValue(pcTMsg.prcCatgCd, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(pcTMsg.prcCatgNm, rsSet.getString("PRC_CATG_NM"));

        ZYPEZDItemValueSetter.setValue(pcTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pcTMsg.prcCatgCd, prcCatgCd);
        ZYPEZDItemValueSetter.setValue(pcTMsg.prcListTpCd, bean.getPrcListTpCd());
        ZYPEZDItemValueSetter.setValue(pcTMsg.effFromDt, this.procDt);
        ZYPEZDItemValueSetter.setValue(pcTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
        ZYPEZDItemValueSetter.setValue(pcTMsg.custRgtnReqFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pcTMsg.delFlg, ZYPConstant.FLG_OFF_N);
        
        // getMaxSortNum
        Map<String, Object> getSortNum = new HashMap<String, Object>();
        getSortNum.put("glblCmpyCd", this.glblCmpyCd);
        BigDecimal maxSortNum = (BigDecimal) this.ssmBatchClient.queryObject("getPrcCatgMaxSortNum", getSortNum);
        if (maxSortNum == null) {
            maxSortNum = BigDecimal.ONE;
        }
        if (maxSortNum.intValue() >= MAX_SORT_NUM.intValue()) {
            maxSortNum = MAX_SORT_NUM;
        } else {
            maxSortNum = maxSortNum.add(BigDecimal.ONE);
        }
        ZYPEZDItemValueSetter.setValue(pcTMsg.prcCatgSortNum, maxSortNum);

        ZYPEZDItemValueSetter.setValue(pcTMsg.ccyCd, this.stdCcyCd);
        return pcTMsg;
    }

    // Del Start 2017/08/28 QC#19873
    //private PRC_CATGTMsg insertDsPrcCatg(NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
    //    PRC_CATGTMsg dpcTMsg = new PRC_CATGTMsg();
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.glblCmpyCd, this.glblCmpyCd);
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.prcCatgCd, prcCatgCd);
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.prcListTpCd, bean.getPrcListTpCd());
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.effFromDt, this.procDt);
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.actvFlg, ZYPConstant.FLG_ON_Y);
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.custRgtnReqFlg, ZYPConstant.FLG_OFF_N);
    //    ZYPEZDItemValueSetter.setValue(dpcTMsg.delFlg, ZYPConstant.FLG_OFF_N);
    //    return dpcTMsg;
    //}
    // Del End 2017/08/28 QC#19873

    private void executeGenlAttrbTMsg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
        PRC_GENL_CONTR_ATTRBTMsg pgcaTMsg = new PRC_GENL_CONTR_ATTRBTMsg();
        boolean isInsert = true;
        ZYPEZDItemValueSetter.setValue(pgcaTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pgcaTMsg.prcCatgCd, prcCatgCd);
        // if (bean.getPrcCatgCd() != null) {
        // isInsert = false;
        // pgcaTMsg = (PRC_GENL_CONTR_ATTRBTMsg)
        // EZDTBLAccessor.findByKeyForUpdateWait(pgcaTMsg);
        // }
        ZYPEZDItemValueSetter.setValue(pgcaTMsg.notToExcdFlg, ZYPConstant.FLG_OFF_N);
        pgcaTMsg.custBidNum.clear();
//      ZYPEZDItemValueSetter.setValue(pgcaTMsg.allwPrcDvaFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pgcaTMsg.allwPrcDevnFlg, ZYPConstant.FLG_OFF_N);
        executeTMsg(pgcaTMsg, "PRC_GENL_CONTR_ATTRB", isInsert);
    }

    private void executeEquipAttrbTMsg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
        boolean isInsert = true;
        PRC_EQUIP_CONTR_ATTRBTMsg pecaTMsg = new PRC_EQUIP_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(pecaTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.prcCatgCd, prcCatgCd);
        // if (bean.getPrcCatgCd() != null) {
        // isInsert = false;
        // pecaTMsg = (PRC_EQUIP_CONTR_ATTRBTMsg)
        // EZDTBLAccessor.findByKeyForUpdateWait(pecaTMsg);
        // }
        // ZYPEZDItemValueSetter.setValue(pecaTMsg.prcLeaseTpCd,
        // rsSet.getString("PRC_LEASE_TP_CD"));
        ZYPEZDItemValueSetter.setValue(pecaTMsg.leaseRtrnFeeInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.addlShpgFeeInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.reloInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.hardDriveEraseInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.hardDriveRmvInclFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pecaTMsg.notExcdContrPrcFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(pecaTMsg.spclCsmpExclArNm,
        // rsSet.getString("SPCL_CSMP_EXCL_AR_NM"));
        executeTMsg(pecaTMsg, "PRC_EQUIP_CONTR_ATTRB", isInsert);
    }

    private void executeSvcAttrbTMsg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
        boolean isInsert = true;
        PRC_SVC_CONTR_ATTRBTMsg pscaTMsg = new PRC_SVC_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(pscaTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.prcCatgCd, prcCatgCd);
        // if (bean.getPrcCatgCd() != null) {
        // isInsert = false;
        // pscaTMsg = (PRC_SVC_CONTR_ATTRBTMsg)
        // EZDTBLAccessor.findByKeyForUpdateWait(pscaTMsg);
        // }
        ZYPEZDItemValueSetter.setValue(pscaTMsg.wavBookPrcFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.wavAllFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.allowNewAggrContrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.allowNewFleetContrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.allowAddFleetContrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.allowAddAggrContrFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(pscaTMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
        // ZYPEZDItemValueSetter.setValue(pscaTMsg.grsPrcPct,
        // rsSet.getBigDecimal("GRS_PRC_PCT"));
        executeTMsg(pscaTMsg, "PRC_SVC_CONTR_ATTRB", isInsert);
    }

    private void executeSvcTierAttrbTMsg(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean, String prcCatgCd) throws SQLException {
        boolean isInsert = true;
        PRC_SVC_TIER_CONTR_ATTRBTMsg pstcaTMsg = new PRC_SVC_TIER_CONTR_ATTRBTMsg();
        ZYPEZDItemValueSetter.setValue(pstcaTMsg.glblCmpyCd, this.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pstcaTMsg.prcCatgCd, prcCatgCd);
        // if (bean.getPrcCatgCd() != null) {
        // isInsert = false;
        // pstcaTMsg = (PRC_SVC_TIER_CONTR_ATTRBTMsg)
        // EZDTBLAccessor.findByKeyForUpdateWait(pstcaTMsg);
        // }
        // ZYPEZDItemValueSetter.setValue(pstcaTMsg.prcTierSvcOfferCd,
        // bean.getPrcTierSvcOfferCd());
        ZYPEZDItemValueSetter.setValue(pstcaTMsg.allowSvcToDclnFlg, ZYPConstant.FLG_OFF_N);
        executeTMsg(pstcaTMsg, "PRC_SVC_TIER_CONTR_ATTRB", isInsert);
    }

    private void getEquipCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399

            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);
            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_QLFY_TP_DESC_TXT"))) {
            PRC_QLFY_TPTMsg pqtMsg = new PRC_QLFY_TPTMsg();
            PRC_QLFY_TPTMsgArray pqtMsgArray = new PRC_QLFY_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pqtMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pqtMsg.prcQlfyTpDescTxt, rsSet.getString("PRC_QLFY_TP_DESC_TXT"));
            pqtMsgArray = (PRC_QLFY_TPTMsgArray) S21CodeTableAccessor.findByCondition(pqtMsg);

            if (pqtMsgArray.length() > 0) {
                bean.setPrcQlfyTpCd(pqtMsgArray.no(0).prcQlfyTpCd.getValue());
            }
        }

        // 2018/11/26 Mod Start QC#29300
        if (ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT")) || ZYPCommonFunc.hasValue(bean.getPkgUomDescTxt())) {
            // Mod Start 2018/05/17 QC#25367

            // 2018/08/16 S21_NA#27716 Mod Start
            // PKG_UOMTMsg puMsg = new PKG_UOMTMsg();
            // PKG_UOMTMsgArray puMsgArray = new PKG_UOMTMsgArray();
            // ZYPEZDItemValueSetter.setValue(puMsg.glblCmpyCd, glblCmpyCd);
            // ZYPEZDItemValueSetter.setValue(puMsg.pkgUomDescTxt, rsSet.getString("PKG_UOM_DESC_TXT"));
            // puMsgArray = (PKG_UOMTMsgArray) S21CodeTableAccessor.findByCondition(puMsg);
            // 2018/09/04 QC#28019 Mod Start
            //String pkgUomCd = NMXC105001PriceListCheckUtil.getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"));
//            String pkgUomCd = NMXC105001PriceListCheckUtil.getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"), bean.getPkgUomClsCd());
            String pkgUomCd = null;
            if (ZYPCommonFunc.hasValue(rsSet.getString("PKG_UOM_DESC_TXT"))) {
                pkgUomCd = NMXC105001PriceListCheckUtil.getPkgUomCd(rsSet.getString("PKG_UOM_DESC_TXT"), bean.getPkgUomClsCd());
            } else if (ZYPCommonFunc.hasValue(bean.getPkgUomDescTxt())) {
                pkgUomCd = NMXC105001PriceListCheckUtil.getPkgUomCd(bean.getPkgUomDescTxt(), bean.getPkgUomClsCd());
            }
         // 2018/11/26 Mod End QC#29300
            // 2018/09/04 QC#28019 Mod End
            // 2018/08/16 S21_NA#27716 Mod End

//            if (puMsgArray.length() > 0) {
//                bean.setPkgUomCd(puMsgArray.no(0).pkgUomCd.getValue());
//            }

            // 2018/08/16 S21_NA#27716 Mod Start
            // if (puMsgArray.length() > 0) {
            if (ZYPCommonFunc.hasValue(pkgUomCd)) {
            // 2018/08/16 S21_NA#27716 Mod End
                // Mod Start 2018/07/31 QC#27488
//                PreparedStatement stmt = null;
                Map<String, String> params = new HashMap<String, String>();
                params.put("glblCmpyCd", glblCmpyCd);
                // 2018/08/16 S21_NA#27716 Mod Start
                // params.put("pkgUomCd", puMsgArray.no(0).pkgUomCd.getValue());
                params.put("pkgUomCd", pkgUomCd);
                // 2018/08/16 S21_NA#27716 Mod End
                // Mod Start 2018/07/17 QC#20267
                if (ZYPCommonFunc.hasValue(bean.getMnfMdseCd())) {
                    params.put("prcQlfyValTxt", bean.getMnfMdseCd());
                } else {
                    params.put("prcQlfyValTxt", rsSet.getString("PRC_QLFY_VAL_TXT"));
                }
                // Mod End 2018/07/17 QC#20267

//                ResultSet rs = null;
//
//                stmt = ssmLLClient.createPreparedStatement("getPkgUomCd", params);
//                rs = stmt.executeQuery();
//
//                rs.last();
//                int number_of_row = rs.getRow();
//
//                if (number_of_row == 1) {
//                    bean.setPkgUomCd(rs.getString("PKG_UOM_CD"));
//                } else if (puMsgArray.length() > 0) {
//                    bean.setPkgUomCd(puMsgArray.no(0).pkgUomCd.getValue());
//                }
                List<Map<String, Object>> pkgUomCdList = (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getPkgUomCd", params);

                // 2018/09/04 QC#28019 Add Start
                bean.setPkgUomCd(pkgUomCd);
                // 2018/09/04 QC#28019 Add End
                if (pkgUomCdList != null && !pkgUomCdList.isEmpty()) {
                    if (1 == pkgUomCdList.size()) {
                        bean.setPkgUomCd((String) pkgUomCdList.get(0).get("PKG_UOM_CD"));
                        // 2018/09/04 QC#28019 Del Start
                        //return;
                        // 2018/09/04 QC#28019 Del End
                    }
                }

                // 2018/08/16 S21_NA#27716 Mod Start
                // bean.setPkgUomCd(puMsgArray.no(0).pkgUomCd.getValue());
                // 2018/09/04 QC#28019 Del Start
                //bean.setPkgUomCd(pkgUomCd);
                // 2018/09/04 QC#28019 Del End
                // 2018/08/16 S21_NA#27716 Mod End
                // Mod End 2018/07/31 QC#27488
            }
            // Mod End 2018/05/17 QC#25367
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_TERM_UOM_DESC_TXT"))) {
            PRC_TERM_UOMTMsg ptuMsg = new PRC_TERM_UOMTMsg();
            PRC_TERM_UOMTMsgArray ptuMsgArray = new PRC_TERM_UOMTMsgArray();
            ZYPEZDItemValueSetter.setValue(ptuMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ptuMsg.prcTermUomDescTxt, rsSet.getString("PRC_TERM_UOM_DESC_TXT"));
            ptuMsgArray = (PRC_TERM_UOMTMsgArray) S21CodeTableAccessor.findByCondition(ptuMsg);

            if (ptuMsgArray.length() > 0) {
                bean.setPrcTermUomCd(ptuMsgArray.no(0).prcTermUomCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_FMLA_NM"))) {
            PRC_FMLATMsg pdTMsg = new PRC_FMLATMsg();
            pdTMsg.setSQLID("002");
            pdTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            pdTMsg.setConditionValue("prcFmlaNm01", rsSet.getString("PRC_FMLA_NM"));
            PRC_FMLATMsgArray pdTMsgArray = (PRC_FMLATMsgArray) EZDTBLAccessor.findByCondition(pdTMsg);
            if (pdTMsgArray.length() > 0) {
                bean.setPrcFmlaPk(pdTMsgArray.no(0).prcFmlaPk.getValue());
            }
        }
    }

    private void getSvcCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);

            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399

            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_RATE_TP_DESC_TXT"))) {
            PRC_RATE_TPTMsg prtMsg = new PRC_RATE_TPTMsg();
            PRC_RATE_TPTMsgArray prtMsgArray = new PRC_RATE_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(prtMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(prtMsg.prcRateTpDescTxt, rsSet.getString("PRC_RATE_TP_DESC_TXT"));
            prtMsgArray = (PRC_RATE_TPTMsgArray) S21CodeTableAccessor.findByCondition(prtMsg);

            if (prtMsgArray.length() > 0) {
                bean.setPrcRateTpCd(prtMsgArray.no(0).prcRateTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_ZONE_DESC_TXT"))) {
            PRC_SVC_ZONETMsg pszMsg = new PRC_SVC_ZONETMsg();
            PRC_SVC_ZONETMsgArray pszMsgArray = new PRC_SVC_ZONETMsgArray();
            ZYPEZDItemValueSetter.setValue(pszMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pszMsg.prcSvcZoneDescTxt, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
            pszMsgArray = (PRC_SVC_ZONETMsgArray) S21CodeTableAccessor.findByCondition(pszMsg);

            if (pszMsgArray.length() > 0) {
                bean.setPrcSvcZoneCd(pszMsgArray.no(0).prcSvcZoneCd.getValue());
            }
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = NMXC105001PriceListCheckUtil.getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = NMXC105001PriceListCheckUtil.getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            bean.setMtrLbCd(mtrLbCd);
        }

        // Price Meter Package Billing Meter
        if (PRC_RATE_TP.CPC.equals(bean.getPrcRateTpCd()) && ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = NMXC105001PriceListCheckUtil.getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);

        }
    }

    private void getSvcTierCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"))) {
            PRC_SVC_TIER_TPTMsg psttMsg = new PRC_SVC_TIER_TPTMsg();
            PRC_SVC_TIER_TPTMsgArray psttMsgArray = new PRC_SVC_TIER_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psttMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psttMsg.prcSvcTierTpDescTxt, rsSet.getString("PRC_SVC_TIER_TP_DESC_TXT"));
            psttMsgArray = (PRC_SVC_TIER_TPTMsgArray) S21CodeTableAccessor.findByCondition(psttMsg);

            if (psttMsgArray.length() > 0) {
                bean.setPrcSvcTierTpCd(psttMsgArray.no(0).prcSvcTierTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            }
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = NMXC105001PriceListCheckUtil.getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = NMXC105001PriceListCheckUtil.getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            bean.setMtrLbCd(mtrLbCd);
        }

        // Price Meter Package Billing Meter
        if (ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = NMXC105001PriceListCheckUtil.getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);

        }

        // if
        // (ZYPCommonFunc.hasValue(rsSet.getString("PRC_TIER_SVC_OFFER_DESC_TXT")))
        // {
        // PRC_TIER_SVC_OFFERTMsg ptsoMsg = new
        // PRC_TIER_SVC_OFFERTMsg();
        // PRC_TIER_SVC_OFFERTMsgArray ptsoMsgArray = new
        // PRC_TIER_SVC_OFFERTMsgArray();
        // ZYPEZDItemValueSetter.setValue(ptsoMsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(ptsoMsg.prcTierSvcOfferDescTxt,
        // rsSet.getString("PRC_TIER_SVC_OFFER_DESC_TXT"));
        // ptsoMsgArray = (PRC_TIER_SVC_OFFERTMsgArray)
        // S21CodeTableAccessor.findByCondition(ptsoMsg);
        //
        // if (ptsoMsgArray.length() > 0) {
        // bean.setPrcTierSvcOfferCd(ptsoMsgArray.no(0).prcTierSvcOfferCd.getValue());
        // }
        // }
    }

    private void getSplyPgmCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"))) {
            PRC_SVC_PLN_TPTMsg psptMsg = new PRC_SVC_PLN_TPTMsg();
            PRC_SVC_PLN_TPTMsgArray psptMsgArray = new PRC_SVC_PLN_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psptMsg.prcSvcPlnTpDescTxt, rsSet.getString("PRC_SVC_PLN_TP_DESC_TXT"));
            psptMsgArray = (PRC_SVC_PLN_TPTMsgArray) S21CodeTableAccessor.findByCondition(psptMsg);

            if (psptMsgArray.length() > 0) {
                bean.setPrcSvcPlnTpCd(psptMsgArray.no(0).prcSvcPlnTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"))) {
            PRC_SVC_CONTR_TPTMsg psctMsg = new PRC_SVC_CONTR_TPTMsg();
            PRC_SVC_CONTR_TPTMsgArray psctMsgArray = new PRC_SVC_CONTR_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcSvcContrTpDescTxt, rsSet.getString("PRC_SVC_CONTR_TP_DESC_TXT"));
            psctMsgArray = (PRC_SVC_CONTR_TPTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcSvcContrTpCd(psctMsgArray.no(0).prcSvcContrTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_BAND_DESC_TXT"))) {
            PRC_LIST_BANDTMsg psctMsg = new PRC_LIST_BANDTMsg();
            PRC_LIST_BANDTMsgArray psctMsgArray = new PRC_LIST_BANDTMsgArray();
            ZYPEZDItemValueSetter.setValue(psctMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(psctMsg.prcListBandDescTxt, rsSet.getString("PRC_LIST_BAND_DESC_TXT"));
            psctMsgArray = (PRC_LIST_BANDTMsgArray) S21CodeTableAccessor.findByCondition(psctMsg);

            if (psctMsgArray.length() > 0) {
                bean.setPrcListBandCd(psctMsgArray.no(0).prcListBandCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_SVC_ZONE_DESC_TXT"))) {
            PRC_SVC_ZONETMsg pszMsg = new PRC_SVC_ZONETMsg();
            PRC_SVC_ZONETMsgArray pszMsgArray = new PRC_SVC_ZONETMsgArray();
            ZYPEZDItemValueSetter.setValue(pszMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pszMsg.prcSvcZoneDescTxt, rsSet.getString("PRC_SVC_ZONE_DESC_TXT"));
            pszMsgArray = (PRC_SVC_ZONETMsgArray) S21CodeTableAccessor.findByCondition(pszMsg);

            if (pszMsgArray.length() > 0) {
                bean.setPrcSvcZoneCd(pszMsgArray.no(0).prcSvcZoneCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("SPLY_AGMT_PLN_NM"))) {
            SPLY_AGMT_PLNTMsg sapTMsg = new SPLY_AGMT_PLNTMsg();
            sapTMsg.setSQLID("001");
            sapTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            sapTMsg.setConditionValue("splyAgmtPlnNm01", rsSet.getString("SPLY_AGMT_PLN_NM"));
            SPLY_AGMT_PLNTMsgArray sapTMsgArray = (SPLY_AGMT_PLNTMsgArray) EZDTBLAccessor.findByCondition(sapTMsg);
            if (sapTMsgArray.length() > 0) {
                bean.setSplyAgmtPlnPk(sapTMsgArray.no(0).splyAgmtPlnPk.getValue());
            }
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }

        // Meter Package Name
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_MTR_PKG_NM"))) {
            BigDecimal mtrPkgPk = NMXC105001PriceListCheckUtil.getBigDecimalAnyItem("PRC_MTR_PKG", rsSet.getString("PRC_MTR_PKG_NM"));
            bean.setPrcMtrPkgPk(mtrPkgPk);
        }

        // Meter Type
        if (ZYPCommonFunc.hasValue(rsSet.getString("MTR_LB_NM"))) {
            String mtrLbCd = NMXC105001PriceListCheckUtil.getBllgMtrLb(rsSet.getString("MTR_LB_NM"));
            bean.setMtrLbCd(mtrLbCd);
        }

        // Price Meter Package Billing Meter
        if (ZYPCommonFunc.hasValue(bean.getPrcMtrPkgPk()) && ZYPCommonFunc.hasValue(bean.getMtrLbCd()) && ZYPCommonFunc.hasValue(bean.getMdlId())) {
            BigDecimal prcMtrPkgBllgMtPk = NMXC105001PriceListCheckUtil.getPrcMtrPkgBllgMtr(bean.getPrcMtrPkgPk(), bean.getMtrLbCd(), bean.getMdlId());
            bean.setPrcMtrPkgBllgMtrPk(prcMtrPkgBllgMtPk);

        }
    }

    private void getAddlChrgCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_ADDL_CHRG_TP_DESC_TXT"))) {
            PRC_ADDL_CHRG_TPTMsg pactMsg = new PRC_ADDL_CHRG_TPTMsg();
            PRC_ADDL_CHRG_TPTMsgArray pactMsgArray = new PRC_ADDL_CHRG_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pactMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pactMsg.prcAddlChrgTpDescTxt, rsSet.getString("PRC_ADDL_CHRG_TP_DESC_TXT"));
            pactMsgArray = (PRC_ADDL_CHRG_TPTMsgArray) S21CodeTableAccessor.findByCondition(pactMsg);

            if (pactMsgArray.length() > 0) {
                bean.setPrcAddlChrgTpCd(pactMsgArray.no(0).prcAddlChrgTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("MKT_MDL_SEG_DESC_TXT"))) {
            MKT_MDL_SEGTMsg mmsMsg = new MKT_MDL_SEGTMsg();
            MKT_MDL_SEGTMsgArray mmsMsgArray = new MKT_MDL_SEGTMsgArray();
            ZYPEZDItemValueSetter.setValue(mmsMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(mmsMsg.mktMdlSegDescTxt, rsSet.getString("MKT_MDL_SEG_DESC_TXT"));
            mmsMsgArray = (MKT_MDL_SEGTMsgArray) S21CodeTableAccessor.findByCondition(mmsMsg);

            if (mmsMsgArray.length() > 0) {
                bean.setMktMdlSegCd(mmsMsgArray.no(0).mktMdlSegCd.getValue());
            }
        }
        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }
    }

    private void getLeaseRateCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_PGM_TP_DESC_TXT"))) {
            PRC_PGM_TPTMsg pptMsg = new PRC_PGM_TPTMsg();
            PRC_PGM_TPTMsgArray pptMsgArray = new PRC_PGM_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pptMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pptMsg.prcPgmTpDescTxt, rsSet.getString("PRC_PGM_TP_DESC_TXT"));
            pptMsgArray = (PRC_PGM_TPTMsgArray) S21CodeTableAccessor.findByCondition(pptMsg);

            if (pptMsgArray.length() > 0) {
                bean.setPrcPgmTpCd(pptMsgArray.no(0).prcPgmTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_EQUIP_TP_DESC_TXT"))) {
            PRC_EQUIP_TPTMsg petMsg = new PRC_EQUIP_TPTMsg();
            PRC_EQUIP_TPTMsgArray petMsgArray = new PRC_EQUIP_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(petMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(petMsg.prcEquipTpDescTxt, rsSet.getString("PRC_EQUIP_TP_DESC_TXT"));
            petMsgArray = (PRC_EQUIP_TPTMsgArray) S21CodeTableAccessor.findByCondition(petMsg);

            if (petMsgArray.length() > 0) {
                bean.setPrcEquipTpCd(petMsgArray.no(0).prcEquipTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("DS_ACCT_NM"))) {
            SELL_TO_CUSTTMsg dacTMsg = new SELL_TO_CUSTTMsg();
            dacTMsg.setSQLID("505");
            dacTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            dacTMsg.setConditionValue("dsAcctNm01", rsSet.getString("DS_ACCT_NM"));
            SELL_TO_CUSTTMsgArray dacTMsgArray = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(dacTMsg);
            bean.setSellToCustCd(dacTMsgArray.no(0).sellToCustCd.getValue());
        }
        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }
    }

    private void getLeaseRtrnCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("SVC_SEG_DESC_TXT"))) {
            SVC_SEGTMsg ssTMsg = new SVC_SEGTMsg();
            SVC_SEGTMsgArray ssTMsgArray = new SVC_SEGTMsgArray();
            ZYPEZDItemValueSetter.setValue(ssTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(ssTMsg.svcSegDescTxt, rsSet.getString("SVC_SEG_DESC_TXT"));
            ssTMsgArray = (SVC_SEGTMsgArray) S21CodeTableAccessor.findByCondition(ssTMsg);

            if (ssTMsgArray.length() > 0) {
                bean.setSvcSegCd(ssTMsgArray.no(0).svcSegCd.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"))) {
            PRC_IN_OUT_RGTMsg piorTMsg = new PRC_IN_OUT_RGTMsg();
            PRC_IN_OUT_RGTMsgArray piorTMsgArray = new PRC_IN_OUT_RGTMsgArray();
            ZYPEZDItemValueSetter.setValue(piorTMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(piorTMsg.prcInOutRgDescTxt, rsSet.getString("PRC_IN_OUT_RG_DESC_TXT"));
            piorTMsgArray = (PRC_IN_OUT_RGTMsgArray) S21CodeTableAccessor.findByCondition(piorTMsg);

            if (piorTMsgArray.length() > 0) {
                bean.setPrcInOutRgCd(piorTMsgArray.no(0).prcInOutRgCd.getValue());
            }
        }
    }

    private void getTradeInCdTable(ResultSet rsSet, NMXC105001PriceListCheckUtilCd bean) throws SQLException {
        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_LIST_TP_DESC_TXT"))) {
            PRC_LIST_TPTMsg pltMsg = new PRC_LIST_TPTMsg();
            PRC_LIST_TPTMsgArray pltMsgArray = new PRC_LIST_TPTMsgArray();
            ZYPEZDItemValueSetter.setValue(pltMsg.glblCmpyCd, glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(pltMsg.prcListTpDescTxt, rsSet.getString("PRC_LIST_TP_DESC_TXT"));
            pltMsgArray = (PRC_LIST_TPTMsgArray) S21CodeTableAccessor.findByCondition(pltMsg);

            if (pltMsgArray.length() > 0) {
                bean.setPrcListTpCd(pltMsgArray.no(0).prcListTpCd.getValue());
            }
        }

        if (ZYPCommonFunc.hasValue(rsSet.getString("PRC_CATG_NM"))) {
            PRC_CATGTMsg pcTMsg = new PRC_CATGTMsg();
            pcTMsg.setSQLID("001");
            pcTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            // Add Start 2018/03/14 QC#23399
            String prcCatgNm = rsSet.getString("PRC_CATG_NM");

            if (S21StringUtil.isNotEmpty(prcCatgNm)){
                prcCatgNm = prcCatgNm.toUpperCase();
            }
            // Add End 2018/03/14 QC#23399
            pcTMsg.setConditionValue("prcCatgNm01", prcCatgNm);
            PRC_CATGTMsgArray pcTMsgArray = (PRC_CATGTMsgArray) EZDTBLAccessor.findByCondition(pcTMsg);

            if (pcTMsgArray.length() > 0) {
                bean.setPrcCatgCd(pcTMsgArray.no(0).prcCatgCd.getValue());
            }
        }

        // Service Model
        if (ZYPCommonFunc.hasValue(rsSet.getString("MDL_NM"))) {
            MDL_NMTMsg mdlInTMsg = new MDL_NMTMsg();
            mdlInTMsg.setSQLID("001");
            mdlInTMsg.setConditionValue("t_GlblCmpyCd01", glblCmpyCd);
            mdlInTMsg.setConditionValue("t_MdlNm01", rsSet.getString("MDL_NM"));
            MDL_NMTMsgArray rsltTMsg = (MDL_NMTMsgArray) EZDTBLAccessor.findByCondition(mdlInTMsg);
            bean.setMdlId(rsltTMsg.no(0).t_MdlId.getValue());
        }
    }

    // START 2016/06/16 [QC#8156,ADD]
    private static String createResultMessageArg(int insCount, int updCount, int errCount) {
        StringBuilder builder = new StringBuilder();
        if (insCount> 0) {
            builder.append(String.format(RESULT_MSG_INS, insCount));
        }
        if (updCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_UPD, updCount));
        }
        if (errCount > 0) {
            if (builder.length() > 0) {
                builder.append(" ");
            }
            builder.append(String.format(RESULT_MSG_ERR, errCount));
        }
        return builder.toString();
    }
    // END 2016/06/16 [QC#8156,ADD]
}
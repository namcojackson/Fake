/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC062001;

import static com.canon.cusa.s21.api.NSZ.NSZC062001.constant.NSZC062001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.api.NSZ.NSZC043001.constant.NSZC043001Constant.MODE_CREATE_FSR;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.FLD_RQST_DEF_RULETMsg;
import business.db.FSRTMsg;
import business.db.XTRNL_SYS_CRAT_ERR_LOGTMsg;
import business.db.XTRNL_SYS_CRAT_LOGTMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC062001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.common.NSX.NSXC001001.constant.DS_CONTR_CTRL_STS;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Service Call Creation for ITSM API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/17   Hitachi         T.Mizuki        Create          N/A
 * 2016/03/28   Hitachi         T.Iwamoto       Create          QC#5710,5968
 * 2016/04/21   Hitachi         T.Iwamoto       Create          QC#5971
 * 2022/12/12   Hitachi         K.Kitachi       Update          QC#60911
 * </pre>
 */
public class NSZC062001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /**
     * Constructor
     */
    public NSZC062001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC062001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC062001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {

            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC062001PMsg pMsg = (NSZC062001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (!hasValue(pMsg.slsDt) && hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.fldRqstSrcPgmCd, NSZM0707E);
        mandatoryCheck(msgMap, pMsg.serNum, NSZM0653E);
        mandatoryCheck(msgMap, pMsg.dsSvcCallTpCd, NSZM0418E);
        mandatoryCheck(msgMap, pMsg.svcPblmTpCd, NSZM0702E);
        if (!ITSM.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            mandatoryCheck(msgMap, pMsg.psnCd, NSZM0052E);
        }

        if (msgMap.getMsgMgr().isXxMsgId()) {
            // insert
            insertXtrnlSysCratErrLog(msgMap, pMsg);
            return false;
        }
        return true;
    }

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC062001PMsg pMsg) {
        Map<String, Object> rsSvcMachMstr = getSvcMachMstr(pMsg);
        if (rsSvcMachMstr == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0703E, new String[] {pMsg.serNum.getValue() });
            insertXtrnlSysCratErrLog(msgMap, pMsg);
            return;
        }
        if (ITSM.equals(pMsg.fldRqstSrcPgmCd.getValue()) && FLG_OFF_N.equals((String) rsSvcMachMstr.get("SVC_CALL_AVAL_FLG"))) {
            msgMap.addXxMsgIdWithPrm(NSZM0704E, new String[] {pMsg.serNum.getValue() });
            insertXtrnlSysCratErrLog(msgMap, pMsg);
            return;
        }
        String bypsPrfTechFlg = null;
        String machDownFlg = null;
        String svcCallSrcTpCd = null;
        String svcMemoTpCd = null;

        FLD_RQST_DEF_RULETMsg rsTMsgByBypsPrfTechFlg = fldRqstDefRUle(pMsg, BYPS_PRF_TECH_FLG);
        if (rsTMsgByBypsPrfTechFlg != null) {
            bypsPrfTechFlg = rsTMsgByBypsPrfTechFlg.fsrUpdPrmValTxt.getValue();
        } else {
            msgMap.addXxMsgIdWithPrm(NSZM0706E, new String[] {"FSR Update Parameter Field Text" });
        }
        FLD_RQST_DEF_RULETMsg rsTMsgMachDownFlg = fldRqstDefRUle(pMsg, MACH_DOWN_FLG);
        if (rsTMsgMachDownFlg != null) {
            machDownFlg = rsTMsgMachDownFlg.fsrUpdPrmValTxt.getValue();
        } else {
            msgMap.addXxMsgIdWithPrm(NSZM0706E, new String[] {"FSR Update Parameter Field Text" });
        }
        FLD_RQST_DEF_RULETMsg rsSvcCallSrcTpCd = fldRqstDefRUle(pMsg, SVC_CALL_SRC_TP_CD);
        if (rsSvcCallSrcTpCd != null) {
            svcCallSrcTpCd = rsSvcCallSrcTpCd.fsrUpdPrmValTxt.getValue();
        } else {
            msgMap.addXxMsgIdWithPrm(NSZM0706E, new String[] {"FSR Update Parameter Field Text" });
        }
        FLD_RQST_DEF_RULETMsg rsSvcMemoTpCd = fldRqstDefRUle(pMsg, SVC_MEMO_TP_CD);
        if (rsSvcMemoTpCd != null) {
            svcMemoTpCd = rsSvcMemoTpCd.fsrUpdPrmValTxt.getValue();
        } else {
            msgMap.addXxMsgIdWithPrm(NSZM0706E, new String[] {"FSR Update Parameter Field Text" });
        }
        if (msgMap.getMsgMgr().isXxMsgId()) {
            insertXtrnlSysCratErrLog(msgMap, pMsg);
            return;
        }
        String[] stringParam = new String[LENGTH_4];
        stringParam[PRAM_0] = bypsPrfTechFlg;
        stringParam[PRAM_1] = machDownFlg;
        stringParam[PRAM_2] = svcCallSrcTpCd;
        stringParam[PRAM_3] = svcMemoTpCd;

        String billCode = null;
        Map<String, Object> svcBlTpCdMap = null;
        if (hasValue(pMsg.xtrnlSysBillTpCd)) {
            svcBlTpCdMap = getSvcBlTpCd(pMsg);
        }

        if (null != svcBlTpCdMap) {
            billCode = (String) svcBlTpCdMap.get("SVC_BILL_TP_CD");
        }
        if (!hasValue(billCode)) {
            Map<String, Object> billCodeMap = getBillCode(pMsg);
            if (null != billCodeMap) {
                billCode = SVC_BILL_TP._4_COVERED_UNDER_CONTRACT;
            } else {
                billCode = SVC_BILL_TP._1_CHARGE_FOR_PARTS_AND_LABOR;
            }
        }

        Map<String, Object> customInfMap = getCustomInf(pMsg);
        Map<String, Object> contactInfMap = getContactInf(pMsg);

        NSZC043001PMsg rsFsrApiMsg = callFsrUpdateAPI(pMsg, rsSvcMachMstr, stringParam, contactInfMap, billCode);
        if (rsFsrApiMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < rsFsrApiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(rsFsrApiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
            }
            // insert
            insertXtrnlSysCratErrLog(msgMap, pMsg);
        } else {
            // insert
            insertXtrnlSysCratLog(msgMap, pMsg, customInfMap, rsFsrApiMsg);
            // Field Service Report update
            FSRTMsg tmsg = new FSRTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, pMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue(tmsg.fsrNum, rsFsrApiMsg.fsrNum);

            FSRTMsg fsrtmsg = (FSRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tmsg);
            ZYPEZDItemValueSetter.setValue(fsrtmsg.pblmSmryTxt, pMsg.pblmSmryTxt);
            ZYPEZDItemValueSetter.setValue(fsrtmsg.xtrnlSysIncdtNum, pMsg.xtrnlSysIncdtNum);
            ZYPEZDItemValueSetter.setValue(fsrtmsg.xtrnlSysTaskNum, pMsg.xtrnlSysTaskNum);
            S21ApiTBLAccessor.update(fsrtmsg);

            // START 2016/04/21 [QC#5971, MOD]
            EZDConnectionMgr.getInstance().commit();
            // END 2016/04/21 [QC#5971, MOD]
        }

        setOutputParam(pMsg, rsSvcMachMstr, rsFsrApiMsg);
    }

    private Map<String, Object> getSvcMachMstr(NSZC062001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstr", param);
        return rs;
    }

    private Map<String, Object> getSvcBlTpCd(NSZC062001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("xtrnlSysBillCd", pMsg.xtrnlSysBillTpCd.getValue());
        param.put("enblFlg", FLG_ON_Y);
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcBlTpCd", param);
        return rs;
    }

    private Map<String, Object> getBillCode(NSZC062001PMsg pMsg) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("dsContrCtrlStsCd", DS_CONTR_CTRL_STS.ACTIVE);
        param.put("csvCovDtlActvFlag", FLG_ON_Y);
        param.put("laberChargeFlag", FLG_OFF_N);
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getBillCode", param);
        return rs;
    }

    private Map<String, Object> getCustomInf(NSZC062001PMsg pMsg) {
        if (!hasValue(pMsg.shipToCustCd)) {
            return null;
        }
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("shipToCustCd", pMsg.shipToCustCd.getValue());
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getCustomInf", param);
        return rs;
    }

    private Map<String, Object> getContactInf(NSZC062001PMsg pMsg) {
        // START 2016/03/28 [QC#5968, MOD]
        if (!hasValue(pMsg.xtrnlSysCtacNm)) {
            return null;
        }
        // END 2016/03/28 [QC#5968, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("salesDate", pMsg.slsDt);
        // START 2016/03/28 [QC#5968, MOD]
        if (hasValue(pMsg.xtrnlSysCtacTelNum)) {
            param.put("ctacTelNum", pMsg.xtrnlSysCtacTelNum.getValue());
        }
        // END 2016/03/28 [QC#5968, MOD]
        param.put("ctacPntActvFlg", FLG_ON_Y);
        param.put("ctacNm", pMsg.xtrnlSysCtacNm.getValue());
        param.put("percent", PERCENT);
        Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getContactInf", param);
        return rs;
    }

    private FLD_RQST_DEF_RULETMsg fldRqstDefRUle(NSZC062001PMsg pMsg, String searchKey) {
        FLD_RQST_DEF_RULETMsg inMsg = new FLD_RQST_DEF_RULETMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.fldRqstSrcPgmCd, pMsg.fldRqstSrcPgmCd);
        setValue(inMsg.fsrUpdPrmFldTxt, searchKey);
        inMsg = (FLD_RQST_DEF_RULETMsg) S21ApiTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            return null;
        }
        return inMsg;
    }

    private NSZC043001PMsg callFsrUpdateAPI(NSZC062001PMsg pMsg, Map<String, Object> rsSvcMachMstr, String[] stringParam, Map<String, Object> contractInfo, String billCode) {
        NSZC043001PMsg fsrPMsg = new NSZC043001PMsg();
        setValue(fsrPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrPMsg.slsDt, pMsg.slsDt);
        setValue(fsrPMsg.xxModeCd, MODE_CREATE_FSR);
        setValue(fsrPMsg.userId, pMsg.psnCd);
        setValue(fsrPMsg.bypsPrfTechFlg, stringParam[PRAM_0]);
        setValue(fsrPMsg.svcMachMstrPk, (BigDecimal) rsSvcMachMstr.get("SVC_MACH_MSTR_PK"));
        if (contractInfo != null) {
            setValue(fsrPMsg.custTelNum, (String) contractInfo.get("DS_CTAC_PNT_VAL_TXT"));
            setValue(fsrPMsg.custTelExtnNum, (String) contractInfo.get("DS_CTAC_PSN_EXTN_NUM"));

            StringBuilder svcCustAttnTxt = new StringBuilder();
            svcCustAttnTxt.append(contractInfo.get("CTAC_PSN_FIRST_NM"));
            svcCustAttnTxt.append(contractInfo.get(" "));
            if (null != contractInfo.get("CTAC_PSN_MID_NM")) {
                svcCustAttnTxt.append(contractInfo.get("CTAC_PSN_MID_NM"));
                svcCustAttnTxt.append(contractInfo.get(" "));
            }
            svcCustAttnTxt.append(contractInfo.get("CTAC_PSN_LAST_NM"));
            svcCustAttnTxt.append(contractInfo.get(" "));
            setValue(fsrPMsg.svcCustAttnTxt, svcCustAttnTxt.toString());
        }
        setValue(fsrPMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        setValue(fsrPMsg.svcBillTpCd, billCode);
        String sysDateTime = ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSS);
        setValue(fsrPMsg.svcTaskRcvDt, sysDateTime.substring(0, TIME_START_KEY));
        setValue(fsrPMsg.svcTaskRcvTm, sysDateTime.substring(TIME_START_KEY));
        setValue(fsrPMsg.machDownFlg, stringParam[PRAM_1]);
        setValue(fsrPMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        setValue(fsrPMsg.svcCallSrcTpCd, stringParam[PRAM_2]);
        setValue(fsrPMsg.taskList.no(0).techCd, pMsg.psnCd);
        // START 2022/12/12 K.Kitachi [QC#60911, ADD]
        setValue(fsrPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        // END 2022/12/12 K.Kitachi [QC#60911, ADD]
        setValue(fsrPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        setValue(fsrPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        setValue(fsrPMsg.svcMemoList.no(0).svcMemoTpCd, stringParam[PRAM_3]);
        setValue(fsrPMsg.svcMemoList.no(0).svcCmntTxt, pMsg.svcCmntTxt);

        fsrPMsg.taskList.setValidCount(1);
        fsrPMsg.svcMemoList.setValidCount(1);
        NSZC043001 api = new NSZC043001();
        api.execute(fsrPMsg, this.onBatchType);
        return fsrPMsg;

    }

    private void setOutputParam(NSZC062001PMsg pMsg, Map<String, Object> rsSvcMachMstr, NSZC043001PMsg rsFsrApiMsg) {
        if (rsSvcMachMstr != null) {
            setValue(pMsg.locNm, (String) rsSvcMachMstr.get("LOC_NM"));
            setValue(pMsg.addlLocNm, (String) rsSvcMachMstr.get("ADDL_LOC_NM"));
            setValue(pMsg.firstLineAddr, (String) rsSvcMachMstr.get("FIRST_LINE_ADDR"));
            setValue(pMsg.scdLineAddr, (String) rsSvcMachMstr.get("SCD_LINE_ADDR"));
            setValue(pMsg.thirdLineAddr, (String) rsSvcMachMstr.get("THIRD_LINE_ADDR"));
            setValue(pMsg.frthLineAddr, (String) rsSvcMachMstr.get("FRTH_LINE_ADDR"));
            setValue(pMsg.ctyAddr, (String) rsSvcMachMstr.get("CTY_ADDR"));
            setValue(pMsg.stCd, (String) rsSvcMachMstr.get("ST_CD"));
            setValue(pMsg.postCd, (String) rsSvcMachMstr.get("POST_CD"));
            setValue(pMsg.ctryCd, (String) rsSvcMachMstr.get("CTRY_CD"));
        }
        setValue(pMsg.svcTaskNum, rsFsrApiMsg.taskList.no(0).svcTaskNum);
        setValue(pMsg.fsrNum, rsFsrApiMsg.fsrNum);
    }

    private void insertXtrnlSysCratLog(S21ApiMessageMap msgMap, NSZC062001PMsg pMsg, Map<String, Object> customInfMap, NSZC043001PMsg rsFsrApiMsg) {

        XTRNL_SYS_CRAT_LOGTMsg xtrnlSysCratLogTMsg = new XTRNL_SYS_CRAT_LOGTMsg();

        setValue(xtrnlSysCratLogTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCratLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.XTRNL_SYS_CRAT_LOG_SQ));
        setValue(xtrnlSysCratLogTMsg.slsDt, pMsg.slsDt);
        setValue(xtrnlSysCratLogTMsg.fldRqstSrcPgmCd, pMsg.fldRqstSrcPgmCd);
        setValue(xtrnlSysCratLogTMsg.serNum, pMsg.serNum);
        setValue(xtrnlSysCratLogTMsg.svcCallTpCd, pMsg.dsSvcCallTpCd);
        setValue(xtrnlSysCratLogTMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysPsnCd, pMsg.psnCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysPsnNm, pMsg.xxAllPsnNm);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysNoteTxt, pMsg.svcCmntTxt);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysTaskNum, pMsg.xtrnlSysTaskNum);
        setValue(xtrnlSysCratLogTMsg.shipToCustCd, pMsg.shipToCustCd);
        setValue(xtrnlSysCratLogTMsg.pblmSmryTxt, pMsg.pblmSmryTxt);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysBillTpCd, pMsg.xtrnlSysBillTpCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysUsrCd, pMsg.xtrnlSysUsrCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysIncdtNum, pMsg.xtrnlSysIncdtNum);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCtacNm, pMsg.xtrnlSysCtacNm);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCtacTelNum, pMsg.xtrnlSysCtacTelNum);
        if (customInfMap != null) {
            setValue(xtrnlSysCratLogTMsg.locNm, (String) customInfMap.get("LOC_NM"));
            setValue(xtrnlSysCratLogTMsg.addlLocNm, (String) customInfMap.get("ADDL_LOC_NM"));
            setValue(xtrnlSysCratLogTMsg.firstLineAddr, (String) customInfMap.get("FIRST_LINE_ADDR"));
            setValue(xtrnlSysCratLogTMsg.scdLineAddr, (String) customInfMap.get("SCD_LINE_ADDR"));
            setValue(xtrnlSysCratLogTMsg.thirdLineAddr, (String) customInfMap.get("THIRD_LINE_ADDR"));
            setValue(xtrnlSysCratLogTMsg.frthLineAddr, (String) customInfMap.get("FRTH_LINE_ADDR"));
            setValue(xtrnlSysCratLogTMsg.ctyAddr, (String) customInfMap.get("CTY_ADDR"));
            setValue(xtrnlSysCratLogTMsg.stCd, (String) customInfMap.get("ST_CD"));
            setValue(xtrnlSysCratLogTMsg.postCd, (String) customInfMap.get("POST_CD"));
            setValue(xtrnlSysCratLogTMsg.ctryCd, (String) customInfMap.get("CTRY_CD"));
        }
        setValue(xtrnlSysCratLogTMsg.svcTaskNum, rsFsrApiMsg.taskList.no(0).svcTaskNum);
        setValue(xtrnlSysCratLogTMsg.fsrNum, rsFsrApiMsg.fsrNum);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysMsgId, pMsg.xxMsgIdList.no(0).xxMsgId);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysMsgTxt, pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0);

        S21ApiTBLAccessor.insert(xtrnlSysCratLogTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xtrnlSysCratLogTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0923E);
        }
    }

    private void insertXtrnlSysCratErrLog(S21ApiMessageMap msgMap, NSZC062001PMsg pMsg) {

        // START 2016/04/21 [QC#5971, MOD]
        EZDConnectionMgr.getInstance().rollback();
        // END 2016/04/21 [QC#5971, MOD]

        msgMap.flush();

        XTRNL_SYS_CRAT_ERR_LOGTMsg xtrnlSysCratLogTMsg = new XTRNL_SYS_CRAT_ERR_LOGTMsg();

        setValue(xtrnlSysCratLogTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCratErrLogPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.XTRNL_SYS_CRAT_ERR_LOG_SQ));
        setValue(xtrnlSysCratLogTMsg.slsDt, pMsg.slsDt);
        setValue(xtrnlSysCratLogTMsg.fldRqstSrcPgmCd, pMsg.fldRqstSrcPgmCd);
        setValue(xtrnlSysCratLogTMsg.serNum, pMsg.serNum);
        setValue(xtrnlSysCratLogTMsg.svcCallTpCd, pMsg.dsSvcCallTpCd);
        setValue(xtrnlSysCratLogTMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysPsnCd, pMsg.psnCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysPsnNm, pMsg.xxAllPsnNm);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysNoteTxt, pMsg.svcCmntTxt);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysTaskNum, pMsg.xtrnlSysTaskNum);
        setValue(xtrnlSysCratLogTMsg.shipToCustCd, pMsg.shipToCustCd);
        setValue(xtrnlSysCratLogTMsg.pblmSmryTxt, pMsg.pblmSmryTxt);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysBillTpCd, pMsg.xtrnlSysBillTpCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysUsrCd, pMsg.xtrnlSysUsrCd);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysIncdtNum, pMsg.xtrnlSysIncdtNum);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCtacNm, pMsg.xtrnlSysCtacNm);
        setValue(xtrnlSysCratLogTMsg.xtrnlSysCtacTelNum, pMsg.xtrnlSysCtacTelNum);
        String errMsg = null;
        if (hasValue(pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0)) {
            String[] msgPrm = {pMsg.xxMsgIdList.no(0).xxMsgPrmTxt_0.getValue() };
            errMsg = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue(), msgPrm);
        } else {
            errMsg = S21MessageFunc.clspGetMessage(pMsg.xxMsgIdList.no(0).xxMsgId.getValue());
        }
        String msgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
        setValue(xtrnlSysCratLogTMsg.xtrnlSysMsgId, msgId);
        errMsg = errMsg.substring(msgId.length()).trim();
        setValue(xtrnlSysCratLogTMsg.xtrnlSysMsgTxt, errMsg);
        S21ApiTBLAccessor.insert(xtrnlSysCratLogTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(xtrnlSysCratLogTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0924E);
        }

        // START 2016/04/21 [QC#5971, MOD]
        EZDConnectionMgr.getInstance().commit();
        // END 2016/04/21 [QC#5971, MOD]
    }

}

/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC075001;

import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.BYPS_PRF_TECH_FLG;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.CUR_LOC_ACCT_NUM;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.FIN_BR_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.FLD_RQST_SRC_PGM_CLICK;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.FLD_RQST_SRC_PGM_PARTS;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.FLD_RQST_SRC_PGM_WMS;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.FLD_SVC_BR_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.MACH_DOWN_FLG;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSAM0128E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSBM0120E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZC0750_FAILED;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZC0750_NOT_EXIST_EML;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZC0750_SUCCESS;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0001E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0052E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0102E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0653E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0703E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0704E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0705E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0706E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0707E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM0829E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM1072E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM1073E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM1074E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.NSZM1075E;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.RELN_BILL_TO_CUST_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.SVC_BY_LINE_BIZ_TP_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.ISTL_BY_SVC_PRVD_PTY_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.SVC_BY_SVC_PRVD_PTY_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.SVC_CALL_SRC_TP_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.SVC_MEMO_TP_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.TIME_START_KEY;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.XX_MODE_CD;
import static com.canon.cusa.s21.api.NSZ.NSZC075001.constant.NSZC075001Constant.YYYYMMDDHHMMSS;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDPItem;
import parts.dbcommon.EZDConnectionMgr;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CLICK_FLD_RQSTTMsg;
import business.db.FLD_RQST_DEF_RULETMsg;
import business.db.S21_PSNTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTR_TRKTMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NSZC043001PMsg;
import business.parts.NSZC075001PMsg;
import business.parts.NSZC107001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NSZ.NSZC043001.NSZC043001;
import com.canon.cusa.s21.api.NSZ.NSZC107001.NSZC107001;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCd;
import com.canon.cusa.s21.common.NSX.NSXC002001.NSXC002001GetBrCdBean;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROC_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ASG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PRVD_PTY;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqConstant;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21AbendException;

/**
 * <pre>
 * Receive Field Request from Click Software API.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/06/2015   Hitachi         T.Iwamoto       Create          NA#Receive Field Request from Click Software API
 * 12/28/2015   Fujitsu         S.Nakai         Update          insert table
 * 05/24/2016   Hitachi         T.Iwamoto       Update          CSA QC#8809
 * 06/17/2016   Hitach          A.Kohinata      Update          CSA QC#9677
 * 07/01/2016   Hitachi         T.Iwamoto       Update          CSA QC#9677
 * 09/07/2016   Hitachi         T.Tomita        Update          CSA QC#10568
 * 10/06/2016   Hitachi         K.Yamada        Update          CSA QC#14913
 * 10/06/2016   Hitachi         K.Yamada        Update          CSA QC#14950
 * 10/06/2016   Fujitsu         S.Nakai         Update          CSA QC#14950-1
 * 11/07/2016   Hitachi         K.Ochiai        Update          CSA QC#15819
 * 11/10/2016   Hitachi         A.Kohinata      Update          CSA QC#15859
 * 11/24/2016   Hitachi         N.Arai          Update          QC#15829
 * 2016/12/12   Hitachi         K.Kojima        Update          QC#16300
 * 01/11/2017   Hitachi         Y.Takeno        Update          CSA QC#17052
 * 2017/02/20   Hitachi         K.Kojima        Update          QC#16301
 * 2018/09/10   Hitachi         K.Kitachi       Update          QC#26260
 * 2019/02/27   Hitachi         S.Kitamura      Update          QC#30545
 * 2019/07/26   Hitachi         T.Aoyagi        Update          QC#52029
 * 2019/09/20   Hitachi         K.Fujimoto      Update          QC#53507
 * 2019/11/18   Hitachi         K.Kitachi       Update          QC#54704
 * 2022/02/17   CITS            R.Cabral        Update          QC#59692
 * 2022/12/12   Hitachi         K.Kitachi       Update          QC#60911
 * 2023/05/16   Hitachi         K.Kitachi       Update          QC#61085
 * 2023/10/06   Hitachi         K.Watanabe      Update          QC#54186
 * </pre>
 */
public class NSZC075001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /** onBatchType */
    private ONBATCH_TYPE onBatchType = null;

    /** success */
    private String successStsTxt = null;

    /** failed */
    private String failedStsTxt = null;

    /** not exists eml addr */
    private String emlAddr = null;

    /**
     * Constructor
     */
    public NSZC075001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * execute
     * @param param NSZC075001PMsg
     * @param onBatchTp ONBATCH_TYPE
     */
    public void execute(final NSZC075001PMsg param, final ONBATCH_TYPE onBatchTp) {
        this.onBatchType = onBatchTp;
        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        if (checkParameter(msgMap)) {
            getvarCharConst(param);
            doProcess(msgMap, param);
        }

        msgMap.flush();
    }

    private boolean checkParameter(S21ApiMessageMap msgMap) {
        NSZC075001PMsg pMsg = (NSZC075001PMsg) msgMap.getPmsg();

        mandatoryCheck(msgMap, pMsg.glblCmpyCd, NSZM0001E);
        if (!hasValue(pMsg.slsDt) && hasValue(pMsg.glblCmpyCd)) {
            setValue(pMsg.slsDt, (String) ZYPDateUtil.getSalesDate(pMsg.glblCmpyCd.getValue()));
        }
        mandatoryCheck(msgMap, pMsg.serNum, NSZM0653E);
        // mod start 2016/11/07 QC#15819
        decideSvcCallTp(msgMap, pMsg.dsSvcCallTpCd.getValue(), pMsg.xtrnlCallTpRefTxt.getValue());
        decideSvcPblmTpCd(msgMap, pMsg.svcPblmTpCd.getValue(), pMsg.xtrnlPblmTpRefTxt.getValue());
        // mod end 2016/11/07 QC#15819
        mandatoryCheck(msgMap, pMsg.fldRqstSrcPgmCd, NSZM0707E);
        mandatoryCheck(msgMap, pMsg.psnCd, NSZM0052E);

        if (msgMap.getMsgMgr().isXxMsgId()) {
            return false;
        }
        return true;
    }

    // add start 2016/11/07 QC#15819
    private void decideSvcCallTp(S21ApiMessageMap msgMap, String dsSvcCallTpCd, String xtrnlCallTpRefTxt) {
        if (hasValue(dsSvcCallTpCd)) {
            return;
        }
        if (!hasValue(xtrnlCallTpRefTxt)) {
            msgMap.addXxMsgId(NSZM1072E);
            return;
        }
        NSZC075001PMsg pMsg = (NSZC075001PMsg) msgMap.getPmsg();
        dsSvcCallTpCd = getSvcCallTp(pMsg.glblCmpyCd.getValue(), xtrnlCallTpRefTxt);
        if (!hasValue(dsSvcCallTpCd)) {
            msgMap.addXxMsgId(NSZM1074E);
            return;
        }
        setValue(pMsg.dsSvcCallTpCd, dsSvcCallTpCd);
    }

    private String getSvcCallTp(String glblCmpyCd, String xtrnlCallTpRefTxt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlCallTpRefTxt", xtrnlCallTpRefTxt);
        return (String) this.ssmBatchClient.queryObject("getSvcCallTp", param);
    }

    private void decideSvcPblmTpCd(S21ApiMessageMap msgMap, String svcPblmTpCd, String xtrnlPblmTpRefTxt) {
        if (hasValue(svcPblmTpCd)) {
            return;
        }
        if (!hasValue(xtrnlPblmTpRefTxt)) {
            msgMap.addXxMsgId(NSZM1073E);
            return;
        }
        NSZC075001PMsg pMsg = (NSZC075001PMsg) msgMap.getPmsg();
        svcPblmTpCd = getSvcPblmTpCd(pMsg.glblCmpyCd.getValue(), xtrnlPblmTpRefTxt);
        if (!hasValue(svcPblmTpCd)) {
            msgMap.addXxMsgId(NSZM1075E);
            return;
        }
        setValue(pMsg.svcPblmTpCd, svcPblmTpCd);
    }

    private String getSvcPblmTpCd(String glblCmpyCd, String xtrnlPblmTpRefTxt) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("xtrnlPblmTpRefTxt", xtrnlPblmTpRefTxt);
        return (String) this.ssmBatchClient.queryObject("getSvcPblmTpCd", param);
    }
    // end 2016/11/07 QC#15819

    private void mandatoryCheck(S21ApiMessageMap msgMap, EZDPItem targetItem, String messageId) {
        if (!hasValue(targetItem)) {
            msgMap.addXxMsgId(messageId);
        }
    }

    private void doProcess(S21ApiMessageMap msgMap, NSZC075001PMsg pMsg) {
        // START 2019/02/27 S.Kitamura [QC#30545,MOD]
        String isFromClick = FLG_OFF_N;
        if (FLD_RQST_SRC_PGM_CLICK.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            isFromClick = FLG_ON_Y;
        }
        // START 2019/11/18 K.Kitachi [QC#54704, ADD]
        String isFromPartsOrder = FLG_OFF_N;
        if (FLD_RQST_SRC_PGM_PARTS.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            isFromPartsOrder = FLG_ON_Y;
        }
        // END 2019/11/18 K.Kitachi [QC#54704, ADD]
        // Map<String, Object> rsSvcMachMstr = getSvcMachMstr(pMsg);
        // START 2019/07/26 T.Aoyagi [QC#52029,MOD]
        // Map<String, Object> rsSvcMachMstr = getSvcMachMstr(pMsg, isFromClick);
        Map<String, Object> rsSvcMachMstr = null;
        // START 2019/11/18 K.Kitachi [QC#54704, MOD]
//        List<Map<String, Object>> machList = getSvcMachMstr(pMsg, isFromClick);
        List<Map<String, Object>> machList = getSvcMachMstr(pMsg, isFromClick, isFromPartsOrder);
        // END 2019/11/18 K.Kitachi [QC#54704, MOD]

        if (machList.size() == 1) {
            rsSvcMachMstr = machList.get(0);
        } else if (machList.size() > 1)  {
            // START 2019/11/18 K.Kitachi [QC#54704, MOD]
//            machList = getSvcMachMstrBySerNumAndMdlNm(pMsg, isFromClick);
            machList = getSvcMachMstrBySerNumAndMdlNm(pMsg, isFromClick, isFromPartsOrder);
            // END 2019/11/18 K.Kitachi [QC#54704, MOD]

            if (machList.size() == 1) {
                rsSvcMachMstr = machList.get(0);
            } else {
                msgMap.addXxMsgId(NSAM0128E);
                return;
            }
        }
        // END 2019/07/26 T.Aoyagi [QC#52029,MOD]

        // END 2019/02/27 S.Kitamura [QC#30545,MOD]
        if (rsSvcMachMstr == null) {
            msgMap.addXxMsgIdWithPrm(NSZM0703E, new String[] {pMsg.serNum.getValue() });
            return;
        }
        if (FLD_RQST_SRC_PGM_CLICK.equals(pMsg.fldRqstSrcPgmCd.getValue()) && FLG_OFF_N.equals(rsSvcMachMstr.get("SVC_CALL_AVAL_FLG").toString())) {
            msgMap.addXxMsgIdWithPrm(NSZM0704E, new String[] {pMsg.serNum.getValue() });
            return;
        }
        if (FLD_RQST_SRC_PGM_WMS.equals(pMsg.fldRqstSrcPgmCd.getValue()) && FLG_OFF_N.equals(rsSvcMachMstr.get("WH_CALL_AVAL_FLG").toString())) {
            msgMap.addXxMsgIdWithPrm(NSZM0705E, new String[] {pMsg.serNum.getValue() });
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
            return;
        }

        // add start 2016/09/07 CSA Defect#10568
        // START 2017/01/11 [QC#17052, MOD]
        if (FLD_RQST_SRC_PGM_WMS.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            updateSvcMachMstr(pMsg, rsSvcMachMstr);
        }
        // END   2017/01/11 [QC#17052, MOD]
        // add end 2016/09/07 CSA Defect#10568

        // add start 2016/10/06 CSA Defect#14950
        String billToCustCd = getBillToCust(pMsg, rsSvcMachMstr);
        // add start 2016/10/06 CSA Defect#14950

        String errStsTxt = successStsTxt;
        NSZC043001PMsg rsFsrApiMsg = callFsrUpdateAPI(pMsg, rsSvcMachMstr, bypsPrfTechFlg, machDownFlg, svcCallSrcTpCd, svcMemoTpCd, billToCustCd);
        if (rsFsrApiMsg.xxMsgIdList.getValidCount() != 0) {
            for (int i = 0; i < rsFsrApiMsg.xxMsgIdList.getValidCount(); i++) {
                msgMap.addXxMsgId(rsFsrApiMsg.xxMsgIdList.no(i).xxMsgId.getValue());
                errStsTxt = failedStsTxt;
            }
//            return;
        }
        // insert
        // START 2016/12/12 K.Kojima [QC#16300,ADD]
        if (!FLD_RQST_SRC_PGM_PARTS.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            // END 2016/12/12 K.Kojima [QC#16300,ADD]
            insertClickFldRqst(msgMap, pMsg, rsSvcMachMstr, rsFsrApiMsg, errStsTxt);
            // START 2016/12/12 K.Kojima [QC#16300,ADD]
        }
        // END 2016/12/12 K.Kojima [QC#16300,ADD]

        setOutputParam(pMsg, rsSvcMachMstr, rsFsrApiMsg);

        // add start 2016/06/17 QC#9677
        // START 2022/02/17 R.Cabral [QC#59692,MOD]
//        if (!FLG_OFF_N.equals(pMsg.xxRqstSendFlg.getValue())) {
        if (!FLG_OFF_N.equals(pMsg.xxRqstSendFlg.getValue())
                && rsFsrApiMsg.xxMsgIdList.getValidCount() == 0) {
        // END   2022/02/17 R.Cabral [QC#59692,MOD]
            // START 2019/09/20 K.Fujimoto [QC#53507,MOD]
            try {
                callSendTaskInfoApi(msgMap, pMsg, rsFsrApiMsg);
            } catch (Throwable e) {
                e.printStackTrace();
            } 
        }
        if (!ZYPCommonFunc.hasValue(pMsg.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(pMsg.xxRqstFlg_NC.getValue())) {
            // This API is called directly from Clicksoft that will not control any transactions in S21.
            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
            EZDConnectionMgr.getInstance().commit();
        }
        // END   2019/09/20 K.Fujimoto [QC#53507,MOD] 
        // add end 2016/06/17 QC#9677

    }
    // START 2019/02/27 S.Kitamura [QC#30545,MOD]
    // private Map<String, Object> getSvcMachMstr(NSZC075001PMsg pMsg) {
    // START 2019/07/26 T.Aoyagi [QC#52029,MOD]
    // private Map<String, Object> getSvcMachMstr(NSZC075001PMsg pMsg, String isFromClick) {
    // START 2019/11/18 K.Kitachi [QC#54704, MOD]
//    private List<Map<String, Object>> getSvcMachMstr(NSZC075001PMsg pMsg, String isFromClick) {
    private List<Map<String, Object>> getSvcMachMstr(NSZC075001PMsg pMsg, String isFromClick, String isFromPartsOrder) {
    // END 2019/11/18 K.Kitachi [QC#54704, MOD]
    // END 2019/07/26 T.Aoyagi [QC#52029,MOD]
    // END 2019/02/27 S.Kitamura [QC#30545,MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        // START 2019/02/27 S.Kitamura [QC#30545,ADD]
        param.put("machTpCd", SVC_MACH_TP.MACHINE);
        param.put("isFromClick", isFromClick);
        // END 2019/02/27 S.Kitamura [QC#30545,ADD]
        // START 2019/11/18 K.Kitachi [QC#54704, ADD]
        param.put("isFromPartsOrder", isFromPartsOrder);
        // END 2019/11/18 K.Kitachi [QC#54704, ADD]
        @SuppressWarnings("unchecked")
        // START 2019/07/26 T.Aoyagi [QC#52029,MOD]
        // Map<String, Object> rs = (Map<String, Object>) this.ssmBatchClient.queryObject("getSvcMachMstr", param);
        List<Map<String, Object>> rs = (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMstr", param);
        // END 2019/07/26 T.Aoyagi [QC#52029,MOD]
        return rs;
    }

    // START 2019/07/26 T.Aoyagi [QC#52029,ADD]
    @SuppressWarnings("unchecked")
    // START 2019/11/18 K.Kitachi [QC#54704, MOD]
//    private List<Map<String, Object>> getSvcMachMstrBySerNumAndMdlNm(NSZC075001PMsg pMsg, String isFromClick) {
    private List<Map<String, Object>> getSvcMachMstrBySerNumAndMdlNm(NSZC075001PMsg pMsg, String isFromClick, String isFromPartsOrder) {
    // END 2019/11/18 K.Kitachi [QC#54704, MOD]
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        param.put("serNum", pMsg.serNum.getValue());
        param.put("machTpCd", SVC_MACH_TP.MACHINE);
        param.put("isFromClick", isFromClick);
        // START 2019/11/18 K.Kitachi [QC#54704, ADD]
        param.put("isFromPartsOrder", isFromPartsOrder);
        // END 2019/11/18 K.Kitachi [QC#54704, ADD]
        param.put("t_MdlNm", pMsg.t_MdlNm.getValue());
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getSvcMachMstrBySerNumAndMdlNm", param);
    }
    // END 2019/07/26 T.Aoyagi [QC#52029,ADD]

    private FLD_RQST_DEF_RULETMsg fldRqstDefRUle(NSZC075001PMsg pMsg, String searchKey) {
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

    private NSZC043001PMsg callFsrUpdateAPI(NSZC075001PMsg pMsg, Map<String, Object> rsSvcMachMstr, String bypsPrfTechFlg, String machDownFlg, String svcCallSrcTpCd, String svcMemoTpCd, String billToCustCd) {
        NSZC043001PMsg fsrPMsg = new NSZC043001PMsg();
        setValue(fsrPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(fsrPMsg.xxModeCd, XX_MODE_CD);
        // mod start 2016/05/24 CSA Defect#8809
        setValue(fsrPMsg.slsDt, pMsg.slsDt);
        // mod end 2016/05/24 CSA Defect#8809
        setValue(fsrPMsg.userId, pMsg.psnCd);
        setValue(fsrPMsg.bypsPrfTechFlg, bypsPrfTechFlg);
        setValue(fsrPMsg.svcMachMstrPk, (BigDecimal) rsSvcMachMstr.get("SVC_MACH_MSTR_PK"));
        setValue(fsrPMsg.dsSvcCallTpCd, pMsg.dsSvcCallTpCd);
        setValue(fsrPMsg.svcTaskRcvDt, ZYPDateUtil.getCurrentSystemTime(ZYPDateUtil.TYPE_YYYYMMDD));
        setValue(fsrPMsg.svcTaskRcvTm, ZYPDateUtil.getCurrentSystemTime(YYYYMMDDHHMMSS).substring(TIME_START_KEY));
        setValue(fsrPMsg.machDownFlg, machDownFlg);
        setValue(fsrPMsg.svcPblmTpCd, pMsg.svcPblmTpCd);
        setValue(fsrPMsg.svcCallSrcTpCd, svcCallSrcTpCd);
        // mod start 2016/07/01 QC#9677
        setValue(fsrPMsg.xxRqstSendFlg, ZYPConstant.FLG_OFF_N);
        // mod end 2016/07/01 QC#9677
        setValue(fsrPMsg.taskList.no(0).techCd, pMsg.psnCd);
        // START 2022/12/12 K.Kitachi [QC#60911, ADD]
        setValue(fsrPMsg.taskList.no(0).svcAsgTpCd, SVC_ASG_TP.REQUIRED);
        // END 2022/12/12 K.Kitachi [QC#60911, ADD]
        setValue(fsrPMsg.taskList.no(0).schdDisptEmlFlg, ZYPConstant.FLG_OFF_N);
        // mod start 2016/07/01 QC#9677
        // if (FLD_RQST_SRC_PGM_CLICK.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
        setValue(fsrPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_ON_Y);
        // } else {
        //     setValue(fsrPMsg.taskList.no(0).mblIntfcFlg, ZYPConstant.FLG_OFF_N);
        // }
        // mod end 2016/07/01 QC#9677
        setValue(fsrPMsg.custEmlAddr, emlAddr);

        setValue(fsrPMsg.svcMemoList.no(0).svcMemoTpCd, svcMemoTpCd);
        setValue(fsrPMsg.svcMemoList.no(0).svcCmntTxt, pMsg.svcCmntTxt);
        // add start 2016/10/06 CSA Defect#14950
        if (hasValue(billToCustCd)) {
            setValue(fsrPMsg.billToCustUpdFlg, ZYPConstant.FLG_OFF_N);
            setValue(fsrPMsg.billToUpdCustCd, billToCustCd);
        }
        // add end 2016/10/06 CSA Defect#14950

        fsrPMsg.taskList.setValidCount(1);
        fsrPMsg.svcMemoList.setValidCount(1);
        NSZC043001 api = new NSZC043001();
        api.execute(fsrPMsg, this.onBatchType);
        return fsrPMsg;

    }

    // add start 2016/06/17 QC#9677
    private void callSendTaskInfoApi(S21ApiMessageMap msgMap, NSZC075001PMsg pMsg, NSZC043001PMsg rsFsrApiMsg) {
        String svcTaskNum = rsFsrApiMsg.taskList.no(0).svcTaskNum.getValue();
        if (!hasValue(svcTaskNum)) {
            return;
        }
        NSZC107001PMsg apiPMsg = new NSZC107001PMsg();
        setValue(apiPMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(apiPMsg.slsDt, pMsg.slsDt);
        setValue(apiPMsg.svcTaskNum, svcTaskNum);
        // START 2023/05/16 K.Kitachi [QC#61085, ADD]
        setValue(apiPMsg.forceSendMultOpFlg, ZYPConstant.FLG_ON_Y);
        // END 2023/05/16 K.Kitachi [QC#61085, ADD]

        // Call Send Task Info to Click Software
        NSZC107001 api = new NSZC107001();
        api.execute(apiPMsg, this.onBatchType);

        if (S21ApiUtil.isXxMsgId(apiPMsg)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(apiPMsg);
            for (S21ApiMessage msg : msgList) {
                if (hasValue(msg.getXxMsgid()) && msg.getXxMsgid().endsWith("E")) {
                    msgMap.addXxMsgIdWithPrm(msg.getXxMsgid(), msg.getXxMsgPrmArray());
                }
            }
        }
    }
    // add end 2016/06/17 QC#9677

    private void setOutputParam(NSZC075001PMsg pMsg, Map<String, Object> rsSvcMachMstr, NSZC043001PMsg rsFsrApiMsg) {
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
        setValue(pMsg.svcTaskNum, rsFsrApiMsg.taskList.no(0).svcTaskNum);
    }

    private void insertClickFldRqst(S21ApiMessageMap msgMap, NSZC075001PMsg pMsg, Map<String, Object> rsSvcMachMstr, NSZC043001PMsg rsFsrApiMsg, String errStsTxt) {
        // START 2017/02/20 K.Kojima [QC#16301,ADD]
        if (!ZYPCommonFunc.hasValue(pMsg.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(pMsg.xxRqstFlg_NC.getValue())) {
            if (errStsTxt.equals(failedStsTxt)) {
                // This API is called directly from Clicksoft that will not control any transactions in S21.
                // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
                EZDConnectionMgr.getInstance().rollback();
            }
        }
        // END 2017/02/20 K.Kojima [QC#16301,ADD]
        CLICK_FLD_RQSTTMsg clickFlgRqstTMsg = new CLICK_FLD_RQSTTMsg();
        setValue(clickFlgRqstTMsg.glblCmpyCd, pMsg.glblCmpyCd.getValue());
        setValue(clickFlgRqstTMsg.clickFldRqstPk, ZYPOracleSeqAccessor.getNumberBigDecimal("CLICK_FLD_RQST_SQ"));
        setValue(clickFlgRqstTMsg.serNum, pMsg.serNum.getValue());
        setValue(clickFlgRqstTMsg.svcCmntTxt, pMsg.svcCmntTxt.getValue());
        setValue(clickFlgRqstTMsg.locNm, (String) rsSvcMachMstr.get("LOC_NM"));
        setValue(clickFlgRqstTMsg.addlLocNm, (String) rsSvcMachMstr.get("ADDL_LOC_NM"));
        setValue(clickFlgRqstTMsg.firstLineAddr, (String) rsSvcMachMstr.get("FIRST_LINE_ADDR"));
        setValue(clickFlgRqstTMsg.scdLineAddr, (String) rsSvcMachMstr.get("SCD_LINE_ADDR"));
        setValue(clickFlgRqstTMsg.thirdLineAddr, (String) rsSvcMachMstr.get("THIRD_LINE_ADDR"));
        setValue(clickFlgRqstTMsg.frthLineAddr, (String) rsSvcMachMstr.get("FRTH_LINE_ADDR"));
        setValue(clickFlgRqstTMsg.ctyAddr, (String) rsSvcMachMstr.get("CTY_ADDR"));
        setValue(clickFlgRqstTMsg.stCd, (String) rsSvcMachMstr.get("ST_CD"));
        setValue(clickFlgRqstTMsg.postCd, (String) rsSvcMachMstr.get("POST_CD"));
        setValue(clickFlgRqstTMsg.ctryCd, (String) rsSvcMachMstr.get("CTRY_CD"));
        // START 2022/02/17 R.Cabral [QC#59692,MOD]
        if (!errStsTxt.equals(failedStsTxt)) {
            setValue(clickFlgRqstTMsg.svcTaskNum,
                rsFsrApiMsg.taskList.no(0).svcTaskNum);
        }
        // END   2022/02/17 R.Cabral [QC#59692,MOD]
        setValue(clickFlgRqstTMsg.clickFldRqstStsNm, errStsTxt);
        setValue(clickFlgRqstTMsg.clickKeyTxt, pMsg.clickKeyTxt.getValue());
        // mod start 11/10/2016 CSA Defect#15859
        if (hasValue(pMsg.clickKeyTxt)) {
            setValue(clickFlgRqstTMsg.procStsCd, PROC_STS.IN_COMPLETED);
        } else {
            setValue(clickFlgRqstTMsg.procStsCd, PROC_STS.COMPLEATED);
        }
        // mod end 11/10/2016 CSA Defect#15859

        S21FastTBLAccessor.insert(clickFlgRqstTMsg);
        if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(clickFlgRqstTMsg.getReturnCode())) {
            msgMap.addXxMsgId(NSZM0829E);
        }
     // START 2019/09/20 K.Fujimoto [QC#53507,DEL]
        // START 2017/02/20 K.Kojima [QC#16301,ADD]
//        if (!ZYPCommonFunc.hasValue(pMsg.xxRqstFlg_NC) || !ZYPConstant.FLG_ON_Y.equals(pMsg.xxRqstFlg_NC.getValue())) {
//            // This API is called directly from Clicksoft that will not control any transactions in S21.
//            // Therefore, Commit / Rollback is necessary in this API although it violates the implementation rule.
//            EZDConnectionMgr.getInstance().commit();
//        }
        // END 2017/02/20 K.Kojima [QC#16301,ADD]
        // END   2019/09/20 K.Fujimoto [QC#53507,DEL]
    }

    private void getvarCharConst(NSZC075001PMsg param) {
        successStsTxt = ZYPCodeDataUtil.getVarCharConstValue(NSZC0750_SUCCESS, param.glblCmpyCd.getValue());
        if (!hasValue(successStsTxt)) {
            throw new S21AbendException(NSZM0102E);
        }
        failedStsTxt = ZYPCodeDataUtil.getVarCharConstValue(NSZC0750_FAILED, param.glblCmpyCd.getValue());
        if (!hasValue(failedStsTxt)) {
            throw new S21AbendException(NSZM0102E);
        }
        emlAddr = ZYPCodeDataUtil.getVarCharConstValue(NSZC0750_NOT_EXIST_EML, param.glblCmpyCd.getValue());
        if (!hasValue(emlAddr)) {
            throw new S21AbendException(NSZM0102E);
        }
    }

    // add start 2016/09/07 CSA Defect#10568
    private void updateSvcMachMstr(NSZC075001PMsg pMsg, Map<String, Object> svcMachMstr) {
        S21_PSNTMsg s21PsnTMsg = getS21Psn(pMsg.glblCmpyCd.getValue(), pMsg.psnCd.getValue());
        if (s21PsnTMsg == null) {
            return;
        }

        String lineBizTpCd = s21PsnTMsg.lineBizTpCd.getValue();
        // mod start 2016/10/06 CSA Defect#14913
        String svcLineBizCd = convLineBizTpToSvcLineBiz(pMsg.glblCmpyCd.getValue(), lineBizTpCd);
        String postCd = (String) svcMachMstr.get("POST_CD");
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        String sldByLineBizTpCd = (String) svcMachMstr.get("SLD_BY_LINE_BIZ_TP_CD");
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]
        // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, postCd, svcLineBizCd);
        NSXC002001GetBrCdBean brCdBean = getBrCd(pMsg, postCd, svcLineBizCd, sldByLineBizTpCd);
        // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        // mod end 2016/10/06 CSA Defect#14913

        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcMachMstrPk, (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK"));
        SVC_MACH_MSTRTMsg targetTMsg = (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
        if (targetTMsg == null) {
            return;
        }
// START 2016/11/24 N.Arai [QC#15829, MOD]
        BigDecimal paramSvcMachMstrPk = (BigDecimal) svcMachMstr.get("SVC_MACH_MSTR_PK");
        Map<String, String> beforParamMap = new HashMap<String, String>();
        beforParamMap.put(SVC_BY_LINE_BIZ_TP_CD, targetTMsg.svcByLineBizTpCd.getValue());
        beforParamMap.put(FLD_SVC_BR_CD, targetTMsg.fldSvcBrCd.getValue());
        beforParamMap.put(FIN_BR_CD, targetTMsg.finBrCd.getValue());
        beforParamMap.put(CUR_LOC_ACCT_NUM, targetTMsg.curLocAcctNum.getValue());
// END 2016/11/24 N.Arai [QC#15829, MOD]
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        beforParamMap.put(ISTL_BY_SVC_PRVD_PTY_CD, targetTMsg.svcByLineBizTpCd.getValue());
        beforParamMap.put(SVC_BY_SVC_PRVD_PTY_CD, targetTMsg.svcByLineBizTpCd.getValue());
        // END 2018/10/06 K.Watanabe [QC#54186, ADD]

        // mod start 2016/10/06 CSA Defect#14913
        setValue(targetTMsg.svcByLineBizTpCd, svcLineBizCd);
        // mod end 2016/10/06 CSA Defect#14913
        // START 2023/10/06 K.Watanabe [QC#54186, ADD]
        if (LINE_BIZ_TP.ESS.equals(svcLineBizCd)) {
            setValue(targetTMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.ESS_DIRECT);
            setValue(targetTMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.ESS_DIRECT);
        } else if (LINE_BIZ_TP.LFS.equals(svcLineBizCd)) {
            setValue(targetTMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.LFS_DIRECT);
            setValue(targetTMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.LFS_DIRECT);
        } else if (LINE_BIZ_TP.PPS.equals(svcLineBizCd)) {
            setValue(targetTMsg.istlBySvcPrvdPtyCd, SVC_PRVD_PTY.PPS_DIRECT);
            setValue(targetTMsg.svcBySvcPrvdPtyCd, SVC_PRVD_PTY.PPS_DIRECT);
        }
        // END 2018/10/06 K.Watanabe [QC#54186, ADD]
        setValue(targetTMsg.fldSvcBrCd, brCdBean.getFldSvcBrCd());
        setValue(targetTMsg.finBrCd, brCdBean.getFinBrCd());
        setValue(targetTMsg.curLocAcctNum, getShipToAcctCust(pMsg, targetTMsg));
        S21ApiTBLAccessor.update(targetTMsg);
// START 2016/11/24 N.Arai [QC#15829, MOD]
        if (!insertSvcMachMstrTrk(pMsg, paramSvcMachMstrPk, beforParamMap, targetTMsg)) {
            throw new S21AbendException(NSBM0120E, new String[] {"SVC_MACH_MSTR_TRK"});
        }
//END 2016/11/24 N.Arai [QC#15829, MOD]
    }

    private S21_PSNTMsg getS21Psn(String glblCmpyCd, String psnCd) {
        S21_PSNTMsg inMsg = new S21_PSNTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.psnCd, psnCd);
        return (S21_PSNTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    // START 2018/09/10 K.Kitachi [QC#26260, MOD]
//    private NSXC002001GetBrCdBean getBrCd(NSZC075001PMsg pMsg, String postCd, String lineBizTpCd) {
    private NSXC002001GetBrCdBean getBrCd(NSZC075001PMsg pMsg, String postCd, String lineBizTpCd, String sldByLineBizTpCd) {
    // END 2018/09/10 K.Kitachi [QC#26260, MOD]
        NSXC002001GetBrCdBean brCdBean = new NSXC002001GetBrCdBean();
        brCdBean.setGlblCmpyCd(pMsg.glblCmpyCd.getValue());
        brCdBean.setPostCd(postCd);
        brCdBean.setSvcLineBizCd(lineBizTpCd);
        brCdBean.setSalesDate(pMsg.slsDt.getValue());
        // START 2018/09/10 K.Kitachi [QC#26260, ADD]
        brCdBean.setSldByLineBizTpCd(sldByLineBizTpCd);
        // END 2018/09/10 K.Kitachi [QC#26260, ADD]
        NSXC002001GetBrCd.getBrCd(brCdBean);
        return brCdBean;
    }
    // add end 2016/09/07 CSA Defect#10568
    // add start 2016/10/06 CSA Defect#14913
    private String convLineBizTpToSvcLineBiz(String glblCmpyCd, String lineBizTpCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("lineBizTpCd", lineBizTpCd);
        param.put("nullString", "NULL");
        return (String) this.ssmBatchClient.queryObject("convLineBizTpToSvcLineBiz", param);
    }
    // add end 2016/10/06 CSA Defect#14913
    // add start 2016/10/06 CSA Defect#14950
    private String getBillToCust(NSZC075001PMsg pMsg, Map<String, Object> svcMachMstr) {
        if (!FLD_RQST_SRC_PGM_WMS.equals(pMsg.fldRqstSrcPgmCd.getValue())) {
            return null;
        }
        // Get Bill To Customer Information
        NMZC610001PMsg billToCustPmsg = new NMZC610001PMsg();
        setValue(billToCustPmsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(billToCustPmsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
        setValue(billToCustPmsg.dsAcctNum_I1, (String) svcMachMstr.get("SELL_TO_CUST_CD"));
        new NMZC610001().execute(billToCustPmsg, ONBATCH_TYPE.BATCH);
        if (S21ApiUtil.isXxMsgId(billToCustPmsg)) {
            List<String> msgIdList = S21ApiUtil.getXxMsgIdList(billToCustPmsg);
            for (String msgId : msgIdList) {
                if (msgId.endsWith("E")) {
                    break;
                }
            }
        }
        if (billToCustPmsg.DefaultBillShipList.getValidCount() > 0) {
            return billToCustPmsg.DefaultBillShipList.no(0).billToCustCd.getValue();
        }
//        String relnBillToCust = getRelnBillToCust(pMsg, svcMachMstr);
//        if (hasValue(relnBillToCust)) {
//            return relnBillToCust;
//        }

        FLD_RQST_DEF_RULETMsg rsTMsgRelnBillTo =  fldRqstDefRUle(pMsg, RELN_BILL_TO_CUST_CD);
        if (rsTMsgRelnBillTo != null) {
            return rsTMsgRelnBillTo.fsrUpdPrmValTxt.getValue();
        }
        return null;
    }

//    private String getRelnBillToCust(NSZC075001PMsg pMsg, Map<String, Object> svcMachMstr) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
//        param.put("shipToCustCd", (String) svcMachMstr.get("CUR_LOC_NUM"));
//        return (String) this.ssmBatchClient.queryObject("getRelnBillToCust", param);
//    }
    // add end 2016/10/06 CSA Defect#14950
    private String getShipToAcctCust(NSZC075001PMsg pMsg, SVC_MACH_MSTRTMsg machTMsg) {
        if (ZYPCommonFunc.hasValue(machTMsg.curLocAcctNum)) {
            return machTMsg.curLocAcctNum.getValue();
        }
        if (!ZYPCommonFunc.hasValue(machTMsg.curLocNum)) {
            return null;
        }
        SHIP_TO_CUSTTMsg shipTMsg = new SHIP_TO_CUSTTMsg();
        shipTMsg.setSQLID("004");
        shipTMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        shipTMsg.setConditionValue("shipToCustCd01", machTMsg.curLocNum.getValue());
        SHIP_TO_CUSTTMsgArray shipTMsgArray = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipTMsg);
        if (shipTMsgArray.getValidCount() > 0) {
            return shipTMsgArray.no(0).sellToCustCd.getValue();
        }
        return null;
    }

// START 2016/11/24 N.Arai [QC#15829, MOD]
     private boolean insertSvcMachMstrTrk(NSZC075001PMsg param, BigDecimal svcMachMstrPk, Map<String, String> beforMap, SVC_MACH_MSTRTMsg newTMsg) {

         if (!setSvcMachMstrTrk(param, svcMachMstrPk, SVC_BY_LINE_BIZ_TP_CD, beforMap.get(SVC_BY_LINE_BIZ_TP_CD), newTMsg.svcByLineBizTpCd.getValue())) {
             return false;
         }
         if (!setSvcMachMstrTrk(param, svcMachMstrPk, FLD_SVC_BR_CD, beforMap.get(FLD_SVC_BR_CD), newTMsg.fldSvcBrCd.getValue())) {
             return false;
         }
         if (!setSvcMachMstrTrk(param, svcMachMstrPk, FIN_BR_CD, beforMap.get(FIN_BR_CD), newTMsg.finBrCd.getValue())) {
             return false;
         }
         if (!setSvcMachMstrTrk(param, svcMachMstrPk, CUR_LOC_ACCT_NUM, beforMap.get(CUR_LOC_ACCT_NUM), newTMsg.curLocAcctNum.getValue())) {
             return false;
         }
         // START 2023/10/06 K.Watanabe [QC#54186, ADD]
         if (!setSvcMachMstrTrk(param, svcMachMstrPk, ISTL_BY_SVC_PRVD_PTY_CD, beforMap.get(ISTL_BY_SVC_PRVD_PTY_CD), newTMsg.istlBySvcPrvdPtyCd.getValue())) {
             return false;
         }
         if (!setSvcMachMstrTrk(param, svcMachMstrPk, SVC_BY_SVC_PRVD_PTY_CD, beforMap.get(SVC_BY_SVC_PRVD_PTY_CD), newTMsg.svcBySvcPrvdPtyCd.getValue())) {
             return false;
         }
         // END 2023/10/06 K.Watanabe [QC#54186, ADD]
         return true;

     }

     private boolean setSvcMachMstrTrk(NSZC075001PMsg param, BigDecimal svcMachMstrPk, String updFld, String oldVal, String newVal) {

         if (!ZYPCommonFunc.hasValue(oldVal) && !ZYPCommonFunc.hasValue(newVal)) {
             return true;
         }
         if (ZYPCommonFunc.hasValue(oldVal) && newVal.equals(oldVal)) {
             return true;
         }
         if (ZYPCommonFunc.hasValue(newVal) && oldVal.equals(newVal)) {
             return true;
         }

         SVC_MACH_MSTR_TRKTMsg tMsg = new SVC_MACH_MSTR_TRKTMsg();

         setValue(tMsg.glblCmpyCd, param.glblCmpyCd);
         setValue(tMsg.svcMachMstrTrkPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.SVC_MACH_MSTR_TRK_SQ));
         setValue(tMsg.svcMachMstrPk, svcMachMstrPk);
         setValue(tMsg.trxRgtnDt, param.slsDt);
         setValue(tMsg.updFldTxt, updFld);
         setValue(tMsg.oldValTxt, oldVal);
         setValue(tMsg.newValTxt, newVal);
         setValue(tMsg.updUsrId, param.psnCd);
         setValue(tMsg.noteExistFlg, ZYPConstant.FLG_OFF_N);
         EZDTBLAccessor.create(tMsg);
         if (!S21FastTBLAccessor.RTNCD_NORMAL.equals(tMsg.getReturnCode())) {
             return false;
         }
         return true;
     }
// END 2016/11/24 N.Arai [QC#15829, MOD]
}

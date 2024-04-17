/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL0610;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.NSAL0610.common.NSAL0610CommonLogic;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsg;
import business.db.CONTR_PHYS_BLLG_MTR_RELNTMsgArray;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsgArray;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BR_ALLOCTMsg;
import business.db.DS_CONTR_BR_ALLOCTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_CR_CARD_POTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsg;
import business.db.DS_CONTR_PRC_ALLOCTMsgArray;
import business.db.DS_CONTR_RNW_ESCLTMsg;
import business.db.DS_CONTR_RNW_ESCLTMsgArray;
import business.db.DS_CONTR_SEG_ALLOCTMsg;
import business.db.DS_CONTR_SEG_ALLOCTMsgArray;
import business.db.SVC_MEMOTMsg;
import business.db.SVC_MEMOTMsgArray;
import business.db.SVC_TERM_CONDTMsg;
import business.db.SVC_TERM_CONDTMsgArray;
import business.parts.NSZC047001PMsg;
import business.parts.NSZC047001_xxMtrLineListPMsg;
import business.parts.NSZC047011PMsg;
import static business.blap.NSAL0610.constant.NSAL0610Constant.*;

import com.canon.cusa.s21.api.NSZ.NSZC047001.NSZC047001;
import com.canon.cusa.s21.api.NSZ.NSZC077001.ContrTrkProcMode;
import com.canon.cusa.s21.common.NSX.NSXC001001.ContrDurationInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcUplftInfo;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContrDurationCalculator;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001ContractTracking;
import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001GetUplftFromDt;
import com.canon.cusa.s21.common.NSX.NSXC001001.UpliftInfoBean;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_INTFC_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.ZYP.numbering.ZYPExtnNumbering;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Copy Contract
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Hitachi         T.Iwamoto       Create          N/A
 * 2016/05/11   Hitachi         T.Iwamoto       Update          QC#4425
 * 2016/07/21   Hitachi         A.Kohinata      Update          QC#11720
 * 2016/09/23   Hitachi         T.Kanasaka      Update          QC#9905
 * 2017/02/28   Hitachi         K.Kishimoto     Update          QC#17809
 * 2017/06/19   Hitachi         A.Kohinata      Update          QC#19036
 * 2018/08/29   CITS            M.Naito         Update          QC#27102
 * 2018/11/21   Hitachi         K.Fujimoto      Update          QC#28638
 * 2019/01/11   Hitachi         K.Morita        Update          QC#26928
 * 2019/01/23   Fujitsu         W.Honda         Update          QC#29371
 * 2019/03/11   Hitachi         S.Kitamura      Update          QC#30721
 * 2019/06/12   Hitachi         A.Kohinata      Update          QC#50807
 * 2022/03/22   Hitachi         D.Yoshida       Update          QC#59683
 *</pre>
 */
public class NSAL0610BL06 extends S21BusinessHandler {

    /** newDsContrNum */
    private String newDsContrNum = null;

    /** newDsContrPk */
    private BigDecimal newDsContrPk = null;

    /** nextEffThruDt */
    private String nextEffThruDt = null;

    // /** maxSeq */
    // private int maxSeq = 0;
    //
    // /** batchNum */
    // private String batchNum = null;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        super.preDoProcess(cMsg, sMsg);

        try {
            String screenAplID = cMsg.getScreenAplID();

            if ("NSAL0610Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0610Scrn00_CMN_Submit((NSAL0610CMsg) cMsg, (NSAL0610SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * do process (Submit)
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     */
    private void doProcess_NSAL0610Scrn00_CMN_Submit(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        NSAL0610CommonLogic.copyCurrentPageToSMsgA(cMsg, sMsg);
        NSAL0610CommonLogic.copyCurrentPageToSMsgN(cMsg, sMsg);

        if (!checkInputData(cMsg, sMsg)) {
            return;
        }

        // mod Start 2016/05/11 CSA Defect#4425
        // createContractInterfaceData(cMsg, sMsg);
        createContractData(cMsg, sMsg);
        // mod End 2016/05/11 CSA Defect#4425

        if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
            setValue(cMsg.dsContrNum_H2, newDsContrNum);
            cMsg.setMessageInfo(NSAM0467I, new String[] {newDsContrNum });
        }
    }

    /**
     * check Input
     * @param cMsg NSAL0610CMsg
     * @param sMsg NSAL0610SMsg
     * @return Change Line : true
     */
    private boolean checkInputData(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        if (sMsg.O.getValidCount() == 0) {
            cMsg.setMessageInfo(NSAM0396E);
            return false;
        }

        // Fleet or Agregate only error
        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
            if (LEVEL_1.equals(sMsg.O.no(i).xxDplyByCtrlAncrLvlCd_N.getValue())) {
                if (!DS_CONTR_DTL_TP.FLEET.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue()) && !DS_CONTR_DTL_TP.AGGREGATE.equals(sMsg.O.no(i).dsContrDtlTpCd_N.getValue())) {
                    return true;
                }
            }
        }
        cMsg.setMessageInfo(NSAM0396E);
        return false;
    }

    private void createContractData(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {

        this.newDsContrNum = ZYPExtnNumbering.getUniqueID(cMsg.glblCmpyCd.getValue(), DS_CONTR_NUM);
        this.newDsContrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_SQ);

        // 2-1.DS_CONTR
        if (!insertDSContract(cMsg)) {
            return;
        }

        // Case DS_CONTR_DTL_PK == null
        // 2-6.DS_CONTR_CR_CARD_PO
        if (!insertDSContractCreditCardPO(cMsg, null, null, null)) {
            return;
        }
        // 2-7.DS_CONTR_RNW_ESCL
        if (!insertDSContractRenewalEscalation(cMsg, null, null)) {
            return;
        }
        // 2-8.SVC_TERM_COND
        if (!insertServiceTermCondition(cMsg, null, null)) {
            return;
        }
        // 2-9.DS_CONTR_BR_ALLOC
        if (!insertDSContractBranchAllocation(cMsg, null, null)) {
            return;
        }
        // 2-10.DS_CONTR_SEG_ALLOC
        if (!insertDSContractSegmentAllocation(cMsg, null, null)) {
            return;
        }
        // 2-11.DS_CONTR_PRC_ALLOC
        if (!insertDSContractPriceAllocation(cMsg, null, null)) {
            return;
        }
        // 2-12.SVC_MEMO
        if (!insertServiceMemo(cMsg, null, null)) {
            return;
        }
        // 2-13.DS_CONTR_ADDL_CHRG
        if (!insertDsContrAddlChrg(cMsg, null, null)) {
            return;
        }

        // dsContrDetailPk New-Old mapping
        List<Map<String, Object>> dtlPkList = new ArrayList<Map<String, Object>>();

        // Case DS_CONTR_DTL_PK != null
        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
            NSAL0610_OSMsg osMsg = sMsg.O.no(i);
            BigDecimal newDsContrDtlPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_DTL_SQ);
            BigDecimal oldDsContrDtlPk = sMsg.O.no(i).dsContrDtlPk_N.getValue();

            if (ZYPConstant.CHKBOX_ON_Y.equals(osMsg.xxDplyCtrlFlg_N.getValue())) {
                // 2-2.DS_CONTR_DTL
                if (!insertDSContractDetail(cMsg, oldDsContrDtlPk, newDsContrDtlPk, dtlPkList)) {
                    return;
                }

                // 2-7.DS_CONTR_RNW_ESCL
                if (!insertDSContractRenewalEscalation(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-8.SVC_TERM_COND
                if (!insertServiceTermCondition(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-9.DS_CONTR_BR_ALLOC
                if (!insertDSContractBranchAllocation(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-10.DS_CONTR_SEG_ALLOC
                if (!insertDSContractSegmentAllocation(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-11.DS_CONTR_PRC_ALLOC
                if (!insertDSContractPriceAllocation(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-12.SVC_MEMO
                if (!insertServiceMemo(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
                // 2-13.DS_CONTR_ADDL_CHRG
                if (!insertDsContrAddlChrg(cMsg, oldDsContrDtlPk, newDsContrDtlPk)) {
                    return;
                }
            }
        }

        // mod start 2016/06/03 CSA Defect#1523, 4624
        // 2-14.BillngScheduleAPI
        if (!callBillngScheduleAPI(cMsg)) {
            return;
        }

        callContrTrkAPI(cMsg);
        // mod end 2016/06/03 CSA Defect#1523, 4624
    }

    // Mod Start 2016/02/28 <QC#17809>
    // mod start 2016/06/03 CSA Defect#1523, 4624
    private boolean callBillngScheduleAPI(NSAL0610CMsg cMsg) {

        if (DS_CONTR_CATG.WARRANTY.equals(cMsg.dsContrCatgCd.getValue())) {
            return true;
        }

        BigDecimal aggLinePk = null;
        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getDetailInfo(newDsContrPk);
        if (ssmResult.isCodeNormal()) {
            List<DS_CONTR_DTLTMsg> dtlArray = (List<DS_CONTR_DTLTMsg>) ssmResult.getResultObject();
            if (DS_CONTR_CATG.AGGREGATE.equals(cMsg.dsContrCatgCd.getValue())) {
                aggLinePk = dtlArray.get(0).dsContrDtlPk.getValue();
            }

            // Base
            for (DS_CONTR_DTLTMsg dtlTMsg : dtlArray) {
                String dsContrDtlTpCd = dtlTMsg.dsContrDtlTpCd.getValue();
                BigDecimal dsContrDtlPk = dtlTMsg.dsContrDtlPk.getValue();

                if (isUnderFleet(cMsg.dsContrCatgCd.getValue(), dsContrDtlTpCd)) {
                    continue;
                }

                if (isBaseChrg(cMsg.glblCmpyCd.getValue(), dsContrDtlTpCd)) {
                    NSZC047001PMsg pMsg = new NSZC047001PMsg();
                    setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
                    setValue(pMsg.xxModeCd, "01");
                    setValue(pMsg.slsDt, cMsg.slsDt);
                    setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
                    setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_ON_Y);
                    setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_OFF_N);
                    setValue(pMsg.contrCloDay, dtlTMsg.contrCloDay);
                    setValue(pMsg.baseBllgTmgCd, dtlTMsg.baseBllgTmgCd);
                    setValue(pMsg.contrBllgDay, dtlTMsg.contrBllgDay);
                    setValue(pMsg.contrEffFromDt, dtlTMsg.contrEffFromDt);
                    setValue(pMsg.contrEffThruDt, dtlTMsg.contrEffThruDt);

                    setValue(pMsg.xxBaseLineList.no(0).baseBllgCycleCd_BL, dtlTMsg.baseBllgCycleCd);
                    setValue(pMsg.xxBaseLineList.no(0).basePrcDealAmt_BL, dtlTMsg.basePrcDealAmt);
                    setValue(pMsg.xxBaseLineList.no(0).basePrcTermDealAmtRate_BL, dtlTMsg.basePrcTermDealAmtRate);
                    pMsg.xxBaseLineList.setValidCount(1);

                    NSZC047001 api = new NSZC047001();
                    api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                    if (pMsg.xxMsgIdList.getValidCount() > 0) {
                        S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                        String msgId = msg.getXxMsgid();
                        String[] msgPrms = msg.getXxMsgPrmArray();
                        cMsg.setMessageInfo(msgId, msgPrms);
                        return false;
                    }
                }

                // Usage
                if (isUsageChrg(cMsg.glblCmpyCd.getValue(), dsContrDtlTpCd)) {
                    DS_CONTR_BLLG_MTRTMsgArray bllgMtrTMsgArray = getDsContrBllgMtr(cMsg.glblCmpyCd.getValue(), dsContrDtlPk);

                    for (int i = 0; i < bllgMtrTMsgArray.getValidCount(); i++) {
                        NSZC047001PMsg pMsg = new NSZC047001PMsg();
                        setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd.getValue());
                        setValue(pMsg.xxModeCd, "01");
                        setValue(pMsg.slsDt, cMsg.slsDt);
                        setValue(pMsg.dsContrDtlPk, dsContrDtlPk);
                        setValue(pMsg.baseChrgFlg, ZYPConstant.FLG_OFF_N);
                        setValue(pMsg.usgChrgFlg, ZYPConstant.FLG_ON_Y);
                        setValue(pMsg.mtrCloDay, dtlTMsg.mtrCloDay);
                        setValue(pMsg.mtrBllgTmgCd, dtlTMsg.mtrBllgTmgCd);
                        setValue(pMsg.mtrBllgDay, dtlTMsg.mtrBllgDay);
                        setValue(pMsg.contrEffFromDt, dtlTMsg.contrEffFromDt);
                        setValue(pMsg.contrEffThruDt, dtlTMsg.contrEffThruDt);

                        CONTR_XS_COPYTMsgArray xsCopyTMsgArray = getContrXsCopy(cMsg.glblCmpyCd.getValue(), bllgMtrTMsgArray.no(i).dsContrBllgMtrPk.getValue());

                        for (int j = 0; j < xsCopyTMsgArray.getValidCount(); j++) {
                            NSZC047001_xxMtrLineListPMsg mtrLine = pMsg.xxMtrLineList.no(j);
                            CONTR_XS_COPYTMsg xsCopyTMsg = xsCopyTMsgArray.no(j);

                            setValue(mtrLine.mtrBllgCycleCd_ML, bllgMtrTMsgArray.no(i).bllgMtrBllgCycleCd);
                            setValue(mtrLine.dsContrBllgMtrPk_ML, xsCopyTMsg.dsContrBllgMtrPk);
                            setValue(mtrLine.contrXsCopyPk_ML, xsCopyTMsg.contrXsCopyPk);
                            setValue(mtrLine.xsMtrCopyQty_ML, xsCopyTMsg.xsMtrCopyQty);
                            setValue(mtrLine.xsMtrAmtRate_ML, xsCopyTMsg.xsMtrAmtRate);
                            setValue(mtrLine.xsMtrFirstFlg_ML, xsCopyTMsg.xsMtrFirstFlg);
                        }
                        pMsg.xxMtrLineList.setValidCount(xsCopyTMsgArray.getValidCount());

                        NSZC047001 api = new NSZC047001();
                        api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                        if (pMsg.xxMsgIdList.getValidCount() > 0) {
                            S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                            String msgId = msg.getXxMsgid();
                            String[] msgPrms = msg.getXxMsgPrmArray();
                            cMsg.setMessageInfo(msgId, msgPrms);
                            return false;
                        }
                    }
                }
            }
            if (ZYPCommonFunc.hasValue(aggLinePk)) {
                NSZC047011PMsg pMsg = new NSZC047011PMsg();
                setValue(pMsg.glblCmpyCd, cMsg.glblCmpyCd);
                setValue(pMsg.xxModeCd, "11");
                setValue(pMsg.slsDt, cMsg.slsDt);
                setValue(pMsg.dsContrDtlPk, aggLinePk);
                NSZC047001 api = new NSZC047001();
                api.execute(pMsg, ONBATCH_TYPE.ONLINE);
                if (pMsg.xxMsgIdList.getValidCount() > 0) {
                    S21ApiMessage msg = S21ApiUtil.getXxMsgList(pMsg).get(0);
                    String msgId = msg.getXxMsgid();
                    String[] msgPrms = msg.getXxMsgPrmArray();
                    cMsg.setMessageInfo(msgId, msgPrms);
                    return false;
                }
            }
        }
        return true;
    }
    // mod end 2016/06/03 CSA Defect#1523, 4624
    // Mod End   2016/02/28 <QC#17809>

    private boolean isUnderFleet(String dsContrCatgCd, String dsContrDtlTpCd) {
        if (!DS_CONTR_CATG.FLEET.equals(dsContrCatgCd)) {
            return false;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dsContrDtlTpCd)) {
            return false;
        }
        return true;
    }

    private boolean isBaseChrg(String glblCmpyCd, String dsContrDtlTpCd) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("GLBL_CMPY_CD", glblCmpyCd);
        key.put("DS_CONTR_DTL_TP_CD", dsContrDtlTpCd);
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(DS_CONTR_DTL_TP.class.getSimpleName(), key);
        return ZYPConstant.FLG_ON_Y.equals(dtlTp.baseChrgFlg.getValue());
    }

    private boolean isUsageChrg(String glblCmpyCd, String dsContrDtlTpCd) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("GLBL_CMPY_CD", glblCmpyCd);
        key.put("DS_CONTR_DTL_TP_CD", dsContrDtlTpCd);
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) S21CodeTableAccessor.findByKey(DS_CONTR_DTL_TP.class.getSimpleName(), key);
        return ZYPConstant.FLG_ON_Y.equals(dtlTp.usgChrgFlg.getValue());
    }

    private String getNextThruDt(String nextFromDt, String oldFromDt, String oldThruDt) {
        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getNextThruDt(nextFromDt, oldFromDt, oldThruDt);
        if (ssmResult.isCodeNormal()) {
            return (String) ssmResult.getResultObject();
        }
        return null;
    }

    private boolean insertDSContract(NSAL0610CMsg cMsg) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        inMsg.setConditionValue("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrPk", cMsg.dsContrPk.getValue());
        inMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR" });
            return false;
        }

        DS_CONTRTMsg insertTMsg = new DS_CONTRTMsg();
        setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insertTMsg.dsContrPk, newDsContrPk);
        setValue(insertTMsg.dsContrNum, newDsContrNum);
        setValue(insertTMsg.dsContrSqNum, DEF_DS_CONTR_SQ_NUM);
        setValue(insertTMsg.dsContrTpCd, DS_CONTR_TP.SERVICE);
        setValue(insertTMsg.dsContrStsCd, DS_CONTR_STS.DRAFT);
        setValue(insertTMsg.contrVrsnEffFromDt, cMsg.slsDt);
        this.nextEffThruDt = getNextThruDt(cMsg.slsDt.getValue(), inMsg.contrVrsnEffFromDt.getValue(), inMsg.contrVrsnEffThruDt.getValue());
        setValue(insertTMsg.contrVrsnEffThruDt, this.nextEffThruDt);
        setValue(insertTMsg.altPayerCustCd, inMsg.altPayerCustCd);
        setValue(insertTMsg.dsContrCatgCd, inMsg.dsContrCatgCd);
        setValue(insertTMsg.pmtTermCashDiscCd, inMsg.pmtTermCashDiscCd);
        setValue(insertTMsg.svcContrRefCmntTxt, inMsg.dsContrNum);
        setValue(insertTMsg.bllgApvlReqFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.ccyCd, inMsg.ccyCd);
        setValue(insertTMsg.preInvReqFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.rnwCpltFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.invSeptBaseUsgFlg, inMsg.invSeptBaseUsgFlg);
        // START 2022/03/22 [QC#59683, ADD]
        setValue(insertTMsg.dsInvTgtrTpCd, inMsg.dsInvTgtrTpCd);
        // END   2022/03/22 [QC#59683, ADD]
        setValue(insertTMsg.dsContrCratDt, cMsg.slsDt);
        String userId = S21UserProfileServiceFactory.getInstance().getService().getContextUserInfo().getUserId();
        setValue(insertTMsg.dsContrCratPsnCd, userId);
        setValue(insertTMsg.dsContrLastUpdDt, cMsg.slsDt);
        setValue(insertTMsg.dsContrLastUpdPsnCd, userId);
        setValue(insertTMsg.leaseCmpyCd, inMsg.leaseCmpyCd);
        setValue(insertTMsg.dsAcctNum, inMsg.dsAcctNum);
        setValue(insertTMsg.tocCd, inMsg.tocCd);
        setValue(insertTMsg.baseChrgToLeaseCmpyFlg, inMsg.baseChrgToLeaseCmpyFlg);
        setValue(insertTMsg.usgChrgToLeaseCmpyFlg, inMsg.usgChrgToLeaseCmpyFlg);
        setValue(insertTMsg.mtrEstTpCd, inMsg.mtrEstTpCd);
        setValue(insertTMsg.prcAllocByMachQtyFlg, inMsg.prcAllocByMachQtyFlg);
        setValue(insertTMsg.svcContrBrCd, inMsg.svcContrBrCd);
        setValue(insertTMsg.dsContrClsCd, inMsg.dsContrClsCd);
        setValue(insertTMsg.ctacPsnPk, inMsg.ctacPsnPk);
        setValue(insertTMsg.contrInvCmntTxt, inMsg.contrInvCmntTxt);
        setValue(insertTMsg.svcLineBizCd, inMsg.svcLineBizCd);
        // START 2019/03/11 S.Kitamura [QC#30721,MOD]
        // setValue(insertTMsg.dsContrSrcTpCd, inMsg.dsContrSrcTpCd);
        setValue(insertTMsg.dsContrSrcTpCd, DS_CONTR_SRC_TP.COPY_CONTRACT);
        // END 2019/03/11 S.Kitamura [QC#30721,MOD]
        setValue(insertTMsg.dsContrEdiCd, inMsg.dsContrEdiCd);
        setValue(insertTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.svcPgmMdseCd, inMsg.svcPgmMdseCd);
        setValue(insertTMsg.contrAdminPsnCd, inMsg.contrAdminPsnCd);
        setValue(insertTMsg.dsContrRptGrpNum, inMsg.dsContrRptGrpNum);
        setValue(insertTMsg.baseBllgCycleCd, inMsg.baseBllgCycleCd);
        setValue(insertTMsg.mtrBllgCycleCd, inMsg.mtrBllgCycleCd);
        setValue(insertTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.maxContrRnwCnt, inMsg.maxContrRnwCnt);
        setValue(insertTMsg.maxPrcUpRatio, inMsg.maxPrcUpRatio);

        // add start 2016/07/21 CSA Defect#11720
        ContrDurationInfo param = new ContrDurationInfo();
        param.setGlblCmpyCd(getGlobalCompanyCode());
        param.setContrEffFromDt(cMsg.slsDt.getValue());
        param.setContrEffThruDt(this.nextEffThruDt);
        NSXC001001ContrDurationCalculator calculator = new NSXC001001ContrDurationCalculator(param);
        calculator.calcDuration();

        ZYPEZDItemValueSetter.setValue(insertTMsg.contrDurnAot, param.getContrDurnNum());
        ZYPEZDItemValueSetter.setValue(insertTMsg.bllgCycleUomCd, param.getCycleUomCd());
        // add end 2016/07/21 CSA Defect#11720

        ZYPEZDItemValueSetter.setValue(insertTMsg.manContrOvrdFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(insertTMsg.applyEquipBillToFlg, ZYPConstant.FLG_OFF_N);
        // add start 2017/06/19 CSA Defect#19036
        ZYPEZDItemValueSetter.setValue(insertTMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        // add end 2017/06/19 CSA Defect#19036

        EZDTBLAccessor.insert(insertTMsg);
        String rtnCd = insertTMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR" });
        }
        return true;
    }

    private boolean insertDSContractDetail(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk, List<Map<String, Object>> dtlPkList) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        inMsg.setConditionValue("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk", oldDsContrDtlPk);
        inMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
        if (inMsg == null) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_DTL" });
            return false;
        }

        DS_CONTR_DTLTMsg insertTMsg = new DS_CONTR_DTLTMsg();
        setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
        setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
        setValue(insertTMsg.dsContrPk, newDsContrPk);
        setValue(insertTMsg.dsContrDtlTpCd, inMsg.dsContrDtlTpCd);
        setValue(insertTMsg.dsContrDtlStsCd, DS_CONTR_DTL_STS.SAVED);
        // setValue(insertTMsg.cpoOrdNum, inMsg.cpoOrdNum);
        // setValue(insertTMsg.cpoDtlLineNum, inMsg.cpoDtlLineNum);
        // setValue(insertTMsg.cpoDtlLineSubNum, inMsg.cpoDtlLineSubNum);
        setValue(insertTMsg.dsOrdTpCd, inMsg.dsOrdTpCd);
        setValue(insertTMsg.dsOrdRsnCd, inMsg.dsOrdRsnCd);
        setValue(insertTMsg.svcConfigMstrPk, inMsg.svcConfigMstrPk);
        setValue(insertTMsg.svcMachMstrPk, inMsg.svcMachMstrPk);
        setValue(insertTMsg.svcConfigMstrPk, inMsg.svcConfigMstrPk);
        setValue(insertTMsg.prntDsContrDtlPk, getPrntDsContrDtlPk(inMsg.prntDsContrDtlPk.getValue(), dtlPkList));
        setValue(insertTMsg.contrCloDay, inMsg.contrCloDay);
        setValue(insertTMsg.baseBllgCycleCd, inMsg.baseBllgCycleCd);
        setValue(insertTMsg.baseBllgTmgCd, inMsg.baseBllgTmgCd);
        setValue(insertTMsg.contrBllgDay, inMsg.contrBllgDay);
        setValue(insertTMsg.mtrBllgCycleCd, inMsg.mtrBllgCycleCd);
        setValue(insertTMsg.mtrBllgTmgCd, inMsg.mtrBllgTmgCd);
        setValue(insertTMsg.basePrcDealAmt, inMsg.basePrcDealAmt);
        setValue(insertTMsg.svcConfigMstrPk, inMsg.svcConfigMstrPk);
        setValue(insertTMsg.contrEffFromDt, cMsg.slsDt);
        setValue(insertTMsg.contrEffThruDt, this.nextEffThruDt);
        // START 2016/09/23 T.Kanasaka [QC#9905, ADD]
        setValue(insertTMsg.shipToCustCd, inMsg.shipToCustCd);
        // END 2016/09/23 T.Kanasaka [QC#9905, ADD]
        setValue(insertTMsg.baseBillToCustCd, inMsg.baseBillToCustCd);
        setValue(insertTMsg.usgBillToCustCd, inMsg.usgBillToCustCd);
        setValue(insertTMsg.mtrBllgDay, inMsg.mtrBllgDay);
        setValue(insertTMsg.mtrCloDay, inMsg.mtrCloDay);
        setValue(insertTMsg.supprCrFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.mtrReadMethCd, inMsg.mtrReadMethCd);
        setValue(insertTMsg.contrRnwTotCnt, BigDecimal.ZERO);
        setValue(insertTMsg.ctacPsnPk, inMsg.ctacPsnPk);
        setValue(insertTMsg.basePrcTermDealAmtRate, inMsg.basePrcTermDealAmtRate);
        setValue(insertTMsg.maxContrRnwCnt, inMsg.maxContrRnwCnt);
        setValue(insertTMsg.maxPrcUpRatio, inMsg.maxPrcUpRatio);
        setValue(insertTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.svcPgmMdseCd, inMsg.svcPgmMdseCd);
        setValue(insertTMsg.prcMtrPkgPk, inMsg.prcMtrPkgPk);
        setValue(insertTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
        // mod start 2019/06/12 QC#50807
        setValue(insertTMsg.baseDplyPerEndDay, inMsg.baseDplyPerEndDay);
        setValue(insertTMsg.mtrDplyPerEndDay, inMsg.mtrDplyPerEndDay);
        // mod end 2019/06/12 QC#50807
        setValue(insertTMsg.dtlBillToCustCd, inMsg.dtlBillToCustCd);
        setValue(insertTMsg.addContrFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.addAsryFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.billWithEquipFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.billWithEquipInvdFlg, ZYPConstant.FLG_OFF_N);
        setValue(insertTMsg.corpAdvPrcFlg, ZYPConstant.FLG_OFF_N);
        // START 2019/01/23 W.Honda [QC#29371, ADD]
        setValue(insertTMsg.svcInvMergeTpCd, inMsg.svcInvMergeTpCd);
        // END 2019/01/23 W.Honda [QC#29371, ADD]
        // START 2018/08/29 M.Naito [QC#27102, ADD]
        DS_CONTRTMsg dsContrTMsg = new DS_CONTRTMsg();
        dsContrTMsg.setConditionValue("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        dsContrTMsg.setConditionValue("dsContrPk", newDsContrPk);
        dsContrTMsg = (DS_CONTRTMsg) EZDTBLAccessor.findByKey(dsContrTMsg);
        if (dsContrTMsg != null) {
            String uplftFromDt = NSXC001001GetUplftFromDt.getUplftFromDt(cMsg.glblCmpyCd.getValue(), insertTMsg.contrEffFromDt.getValue(), CONTR_INTFC_SRC_TP.ORDER, dsContrTMsg.dsContrClsCd.getValue(), dsContrTMsg.svcLineBizCd.getValue());
            if (ZYPCommonFunc.hasValue(uplftFromDt)) {
                ZYPEZDItemValueSetter.setValue(insertTMsg.uplftFromDt, uplftFromDt);
            }
        }
        // END 2018/08/29 M.Naito [QC#27102, ADD]

        EZDTBLAccessor.insert(insertTMsg);
        String rtnCd = insertTMsg.getReturnCode();
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(rtnCd)) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_DTL" });
        }

        // set detailPkList
        Map<String, Object> dtlPkMap = new HashMap<String, Object>();
        dtlPkMap.put(NEW_DTL_PK, newDsContrDtlPk);
        dtlPkMap.put(OLD_DTL_PK, oldDsContrDtlPk);
        dtlPkList.add(dtlPkMap);

        // BillingMeterPk New-Old mapping
        List<Map<String, Object>> bllgMtrPkList = new ArrayList<Map<String, Object>>();

        // 2-3.DS_CONTR_BLLG_MTR, 2-5.CONTR_XS_COPY
        if (!insertDSContractBillingMeter(cMsg, oldDsContrDtlPk, newDsContrDtlPk, bllgMtrPkList)) {
            return false;
        }
        // 2-4.CONTR_PHYS_BLLG_MTR_RELN
        if (!insertContractPhysicalBillingMeterRelation(cMsg, oldDsContrDtlPk, newDsContrDtlPk, bllgMtrPkList)) {
            return false;
        }
        // 2-6.DS_CONTR_CR_CARD_PO
        if (!insertDSContractCreditCardPO(cMsg, oldDsContrDtlPk, newDsContrDtlPk, bllgMtrPkList)) {
            return false;
        }
        return true;
    }

    private BigDecimal getPrntDsContrDtlPk(BigDecimal prntDsContrDtlPk, List<Map<String, Object>> dtlPkList) {
        if (!ZYPCommonFunc.hasValue(prntDsContrDtlPk)) {
            return null;
        }
        for (Map<String, Object> dtlPkMap : dtlPkList) {
            BigDecimal oldDsContrDtlPk = (BigDecimal) dtlPkMap.get(OLD_DTL_PK);
            if (prntDsContrDtlPk.compareTo(oldDsContrDtlPk) == 0) {
                return (BigDecimal) dtlPkMap.get(NEW_DTL_PK);
            }
        }
        return null;
    }

    private boolean insertDSContractBillingMeter(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk, List<Map<String, Object>> bllgMtrPkList) {

        DS_CONTR_BLLG_MTRTMsgArray outMsgArray = getDsContrBllgMtr(cMsg.glblCmpyCd.getValue(), oldDsContrDtlPk);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_BLLG_MTRTMsg> insertTMsgList = new ArrayList<DS_CONTR_BLLG_MTRTMsg>();
        List<CONTR_XS_COPYTMsg> insertXsTMsgList = new ArrayList<CONTR_XS_COPYTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_BLLG_MTRTMsg insertTMsg = new DS_CONTR_BLLG_MTRTMsg();
            BigDecimal newDsContrBllgMtrPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_BLLG_MTR_SQ);
            setValue(insertTMsg.glblCmpyCd, cMsg.glblCmpyCd);
            setValue(insertTMsg.dsContrBllgMtrPk, newDsContrBllgMtrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            setValue(insertTMsg.bllgMtrLbCd, outMsgArray.no(i).bllgMtrLbCd);
            setValue(insertTMsg.bllgMtrBillToCustCd, outMsgArray.no(i).bllgMtrBillToCustCd);
            setValue(insertTMsg.bllgMtrBllgCycleCd, outMsgArray.no(i).bllgMtrBllgCycleCd);
            setValue(insertTMsg.xsChrgTpCd, outMsgArray.no(i).xsChrgTpCd);
            setValue(insertTMsg.dsContrBllgMtrStsCd, DS_CONTR_DTL_STS.SAVED);
            setValue(insertTMsg.bllgMinCnt, outMsgArray.no(i).bllgMinCnt);
            setValue(insertTMsg.bllgMinAmtRate, outMsgArray.no(i).bllgMinAmtRate);
            setValue(insertTMsg.bllgRollOverRatio, outMsgArray.no(i).bllgRollOverRatio);
            setValue(insertTMsg.bllgFreeCopyCnt, outMsgArray.no(i).bllgFreeCopyCnt);
            setValue(insertTMsg.ctacPsnPk, outMsgArray.no(i).ctacPsnPk);
            setValue(insertTMsg.rollOverCnt, BigDecimal.ZERO);
            setValue(insertTMsg.intgMdseCd, outMsgArray.no(i).intgMdseCd);
            setValue(insertTMsg.qltyAsrnHldFlg, ZYPConstant.FLG_OFF_N);
            setValue(insertTMsg.mtrHldFlg, ZYPConstant.FLG_OFF_N);
            setValue(insertTMsg.contrHldFlg, ZYPConstant.FLG_OFF_N);
            setValue(insertTMsg.bllgHldFlg, ZYPConstant.FLG_OFF_N);
            setValue(insertTMsg.qltyAsrnHldPendApvlFlg, ZYPConstant.FLG_OFF_N);
            insertTMsgList.add(insertTMsg);

            // set bllgMtrPkList
            Map<String, Object> bllgMtrPkMap = new HashMap<String, Object>();
            bllgMtrPkMap.put(NEW_BLLG_MTR_PK, newDsContrBllgMtrPk);
            bllgMtrPkMap.put(OLD_BLLG_MTR_PK, outMsgArray.no(i).dsContrBllgMtrPk.getValue());
            bllgMtrPkList.add(bllgMtrPkMap);

            // set CONTR_XS_COPY data
            setXsTMsgList(insertXsTMsgList, newDsContrBllgMtrPk, outMsgArray.no(i).dsContrBllgMtrPk.getValue(), cMsg);
        }

        DS_CONTR_BLLG_MTRTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_BLLG_MTRTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_BLLG_MTR" });
            return false;
        }

        // 2-5.CONTR_XS_COPY
        if (!insertContractExcessCopy(cMsg, insertXsTMsgList)) {
            return false;
        }
        return true;
    }

    private DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtr(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        return (DS_CONTR_BLLG_MTRTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private void setXsTMsgList(List<CONTR_XS_COPYTMsg> insertXsTMsgList, BigDecimal newDsContrBllgMtrPk, BigDecimal oldDsContrBllgMtrPk, NSAL0610CMsg cMsg) {

        CONTR_XS_COPYTMsgArray outMsgArray = getContrXsCopy(cMsg.glblCmpyCd.getValue(), oldDsContrBllgMtrPk);

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            CONTR_XS_COPYTMsg insertTMsg = new CONTR_XS_COPYTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.contrXsCopyPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CONTR_XS_COPY_SQ));
            setValue(insertTMsg.dsContrBllgMtrPk, newDsContrBllgMtrPk);
            insertXsTMsgList.add(insertTMsg);
        }
    }

    private CONTR_XS_COPYTMsgArray getContrXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    private boolean insertContractExcessCopy(NSAL0610CMsg cMsg, List<CONTR_XS_COPYTMsg> insertXsTMsgList) {
        if (insertXsTMsgList.size() == 0) {
            return true;
        }

        CONTR_XS_COPYTMsg[] inMsgArray;
        inMsgArray = new CONTR_XS_COPYTMsg[insertXsTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertXsTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"CONTR_XS_COPY" });
            return false;
        }
        return true;
    }

    private boolean insertContractPhysicalBillingMeterRelation(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk, List<Map<String, Object>> bllgMtrPkList) {
        CONTR_PHYS_BLLG_MTR_RELNTMsg inMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        CONTR_PHYS_BLLG_MTR_RELNTMsgArray outMsgArray = (CONTR_PHYS_BLLG_MTR_RELNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<CONTR_PHYS_BLLG_MTR_RELNTMsg> insertTMsgList = new ArrayList<CONTR_PHYS_BLLG_MTR_RELNTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            CONTR_PHYS_BLLG_MTR_RELNTMsg insertTMsg = new CONTR_PHYS_BLLG_MTR_RELNTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.contrPhysBllgMtrRelnPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.CONTR_PHYS_BLLG_MTR_RELN_SQ));
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            setValue(insertTMsg.dsContrBllgMtrPk, getNewBllgMtrPk(outMsgArray.no(i).dsContrBllgMtrPk.getValue(), bllgMtrPkList));
            insertTMsgList.add(insertTMsg);
        }

        CONTR_PHYS_BLLG_MTR_RELNTMsg[] inMsgArray;
        inMsgArray = new CONTR_PHYS_BLLG_MTR_RELNTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"CONTR_PHYS_BLLG_MTR_RELN" });
            return false;
        }

        return true;
    }

    private BigDecimal getNewBllgMtrPk(BigDecimal dsContrBllgMtrPk, List<Map<String, Object>> bllgMtrPkList) {
        if (!ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return null;
        }
        for (Map<String, Object> bllgMtrPkMap : bllgMtrPkList) {
            BigDecimal oldDsContrBllgMtrPk = (BigDecimal) bllgMtrPkMap.get(OLD_BLLG_MTR_PK);
            if (dsContrBllgMtrPk.compareTo(oldDsContrBllgMtrPk) == 0) {
                return (BigDecimal) bllgMtrPkMap.get(NEW_BLLG_MTR_PK);
            }
        }
        return null;
    }

    private boolean insertDSContractCreditCardPO(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk, List<Map<String, Object>> bllgMtrPkList) {
        DS_CONTR_CR_CARD_POTMsg inMsg = new DS_CONTR_CR_CARD_POTMsg();
        
        // mod end 2019/01/10 K.Morita QC#26928
//      if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
//          inMsg.setSQLID("003");
//          inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
//          inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
//      } else {
//          inMsg.setSQLID("004");
//          inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
//          inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
//      }
      // mod end 2019/01/10 K.Morita QC#26928
        
     // mod start 2019/01/10 K.Morita QC#26928
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("203");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("205");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
     // mod end 2019/01/10 K.Morita QC#26928
       
        DS_CONTR_CR_CARD_POTMsgArray outMsgArray = (DS_CONTR_CR_CARD_POTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_CR_CARD_POTMsg> insertTMsgList = new ArrayList<DS_CONTR_CR_CARD_POTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_CR_CARD_POTMsg insertTMsg = new DS_CONTR_CR_CARD_POTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrCrCardPoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_CR_CARD_PO_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            setValue(insertTMsg.dsContrBllgMtrPk, getNewBllgMtrPk(outMsgArray.no(i).dsContrBllgMtrPk.getValue(), bllgMtrPkList));
         // mod start 2019/01/10 K.Morita QC#26928
         //   insertTMsg.custPoNum.clear();
         //   insertTMsg.poDt.clear();
         // mod end 2019/01/10 K.Morita QC#26928
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_CR_CARD_POTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_CR_CARD_POTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_CR_CARD_PO" });
            return false;
        }

        return true;
    }

    private boolean insertDSContractRenewalEscalation(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        DS_CONTR_RNW_ESCLTMsg inMsg = new DS_CONTR_RNW_ESCLTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("003");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("004");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        DS_CONTR_RNW_ESCLTMsgArray outMsgArray = (DS_CONTR_RNW_ESCLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_RNW_ESCLTMsg> insertTMsgList = new ArrayList<DS_CONTR_RNW_ESCLTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_RNW_ESCLTMsg insertTMsg = new DS_CONTR_RNW_ESCLTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrRnwEsclPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_RNW_ESCL_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            // add start 2018/11/21 K.Fujimoto QC#28638
            BigDecimal fixTermInMthAot = outMsgArray.no(i).fixTermInMthAot.getValue();
            if (fixTermInMthAot != null && BigDecimal.ZERO.compareTo(fixTermInMthAot) < 0){
                UpliftInfoBean bean = new UpliftInfoBean();
                bean.setContrEffFromDt(cMsg.slsDt.getValue());
                bean.setContrEffThruDt(this.nextEffThruDt);
                bean.setFixTermInMthAot(fixTermInMthAot);
                NSXC001001CalcUplftInfo.calcUplftInfoByChngFixTermInMthAot(bean);
                String uplftFixedDt = bean.getUplftFixedDt();
                String uplftPcyDt = bean.getUplftPcyDt();
                if(ZYPCommonFunc.hasValue(uplftFixedDt) && ZYPCommonFunc.hasValue(uplftPcyDt)){
                    bean.setFixTermInMthAot(fixTermInMthAot);
                    setValue(insertTMsg.uplftFixedDt, uplftFixedDt);
                    setValue(insertTMsg.uplftPcyDt, uplftPcyDt);
                }
            }
            // add end 2018/11/21 K.Fujimoto QC#28638
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_RNW_ESCLTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_RNW_ESCLTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_RNW_ESCL" });
            return false;
        }

        return true;
    }

    private boolean insertServiceTermCondition(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        SVC_TERM_CONDTMsg inMsg = new SVC_TERM_CONDTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("005");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("006");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        SVC_TERM_CONDTMsgArray outMsgArray = (SVC_TERM_CONDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<SVC_TERM_CONDTMsg> insertTMsgList = new ArrayList<SVC_TERM_CONDTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            SVC_TERM_CONDTMsg insertTMsg = new SVC_TERM_CONDTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.svcTermCondPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_TERM_COND_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            insertTMsgList.add(insertTMsg);
        }

        SVC_TERM_CONDTMsg[] inMsgArray;
        inMsgArray = new SVC_TERM_CONDTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_TERM_COND" });
            return false;
        }

        return true;
    }

    private boolean insertDSContractBranchAllocation(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        DS_CONTR_BR_ALLOCTMsg inMsg = new DS_CONTR_BR_ALLOCTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("004");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("005");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        DS_CONTR_BR_ALLOCTMsgArray outMsgArray = (DS_CONTR_BR_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_BR_ALLOCTMsg> insertTMsgList = new ArrayList<DS_CONTR_BR_ALLOCTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_BR_ALLOCTMsg insertTMsg = new DS_CONTR_BR_ALLOCTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrBrAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_BR_ALLOC_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_BR_ALLOCTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_BR_ALLOCTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_BR_ALLOC" });
            return false;
        }

        return true;
    }

    private boolean insertDSContractSegmentAllocation(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        DS_CONTR_SEG_ALLOCTMsg inMsg = new DS_CONTR_SEG_ALLOCTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("004");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("005");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        DS_CONTR_SEG_ALLOCTMsgArray outMsgArray = (DS_CONTR_SEG_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_SEG_ALLOCTMsg> insertTMsgList = new ArrayList<DS_CONTR_SEG_ALLOCTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_SEG_ALLOCTMsg insertTMsg = new DS_CONTR_SEG_ALLOCTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrSegAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_SEG_ALLOC_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_SEG_ALLOCTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_SEG_ALLOCTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_SEG_ALLOC" });
            return false;
        }

        return true;
    }

    private boolean insertDSContractPriceAllocation(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        DS_CONTR_PRC_ALLOCTMsg inMsg = new DS_CONTR_PRC_ALLOCTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("005");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("006");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        DS_CONTR_PRC_ALLOCTMsgArray outMsgArray = (DS_CONTR_PRC_ALLOCTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_PRC_ALLOCTMsg> insertTMsgList = new ArrayList<DS_CONTR_PRC_ALLOCTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_PRC_ALLOCTMsg insertTMsg = new DS_CONTR_PRC_ALLOCTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrPrcAllocPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_PRC_ALLOC_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_PRC_ALLOCTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_PRC_ALLOCTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_PRC_ALLOC" });
            return false;
        }

        return true;
    }

    private boolean insertServiceMemo(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        SVC_MEMOTMsg inMsg = new SVC_MEMOTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("008");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("009");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        SVC_MEMOTMsgArray outMsgArray = (SVC_MEMOTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<SVC_MEMOTMsg> insertTMsgList = new ArrayList<SVC_MEMOTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            SVC_MEMOTMsg insertTMsg = new SVC_MEMOTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.svcMemoPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.SVC_MEMO_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            insertTMsgList.add(insertTMsg);
        }

        SVC_MEMOTMsg[] inMsgArray;
        inMsgArray = new SVC_MEMOTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"SVC_MEMO" });
            return false;
        }

        return true;
    }

    private boolean insertDsContrAddlChrg(NSAL0610CMsg cMsg, BigDecimal oldDsContrDtlPk, BigDecimal newDsContrDtlPk) {
        DS_CONTR_ADDL_CHRGTMsg inMsg = new DS_CONTR_ADDL_CHRGTMsg();
        if (ZYPCommonFunc.hasValue(oldDsContrDtlPk)) {
            inMsg.setSQLID("001");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrDtlPk01", oldDsContrDtlPk);
        } else {
            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
            inMsg.setConditionValue("dsContrPk01", cMsg.dsContrPk.getValue());
        }
        DS_CONTR_ADDL_CHRGTMsgArray outMsgArray = (DS_CONTR_ADDL_CHRGTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
        if (outMsgArray.getValidCount() == 0) {
            return true;
        }

        List<DS_CONTR_ADDL_CHRGTMsg> insertTMsgList = new ArrayList<DS_CONTR_ADDL_CHRGTMsg>();

        for (int i = 0; i < outMsgArray.getValidCount(); i++) {
            DS_CONTR_ADDL_CHRGTMsg insertTMsg = new DS_CONTR_ADDL_CHRGTMsg();
            EZDMsg.copy(outMsgArray.no(i), null, insertTMsg, null);
            setValue(insertTMsg.dsContrAddlChrgPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.DS_CONTR_ADDL_CHRG_SQ));
            setValue(insertTMsg.dsContrPk, newDsContrPk);
            setValue(insertTMsg.dsContrDtlPk, newDsContrDtlPk);
            setValue(insertTMsg.effFromDt, cMsg.slsDt);
            setValue(insertTMsg.effThruDt, this.nextEffThruDt);
            insertTMsg.trmnDt.clear();
            insertTMsg.invUpToDt.clear();
            insertTMsgList.add(insertTMsg);
        }

        DS_CONTR_ADDL_CHRGTMsg[] inMsgArray;
        inMsgArray = new DS_CONTR_ADDL_CHRGTMsg[insertTMsgList.size()];
        int insertCount = S21FastTBLAccessor.insert(insertTMsgList.toArray(inMsgArray));

        if (insertCount != inMsgArray.length) {
            cMsg.setMessageInfo(NSAM0032E, new String[] {"DS_CONTR_ADDL_CHRG" });
            return false;
        }

        return true;
    }

    // add start 2016/06/03 CSA Defect#1523, 4624
    private boolean callContrTrkAPI(NSAL0610CMsg cMsg) {
        String glblCmpyCd = cMsg.glblCmpyCd.getValue();
        String userId = getContextUserInfo().getUserId();
        String slsDt = cMsg.slsDt.getValue();
        String stsMemoTxt = ZYPCodeDataUtil.getVarCharConstValue("NSZC0770_NOTES_COPY_CONTR", glblCmpyCd);
        if (!NSXC001001ContractTracking.callContrTrk(glblCmpyCd, ContrTrkProcMode.USER_OPERATION.code, this.newDsContrPk, userId, slsDt, null, stsMemoTxt, ONBATCH_TYPE.ONLINE)) {
            cMsg.setMessageInfo(NSXC001001ContractTracking.ERR_MSG_ID);
            return false;
        }
        return true;
    }
    // add end 2016/06/03 CSA Defect#1523, 4624

     //Del 2016/04/26 CSA Defect#4425
//    /**
//     * create DS Contract Interface
//     * @param cMsg NSAL0610CMsg
//     * @param sMsg NSAL0610SMsg
//     * @return Change Line : true
//     */
//    private void createContractInterfaceData(NSAL0610CMsg cMsg, NSAL0610SMsg sMsg) {
//
//        dsContrNum = ZYPExtnNumbering.getUniqueID(cMsg.glblCmpyCd.getValue(), DS_CONTR_NUM);
//
//        maxSeq = NSAL0610Query.getInstance().getTodayMaxSeq(cMsg.slsDt.getValue());
//        batchNum = cMsg.slsDt.getValue() + ZYPCommonFunc.leftPad(String.valueOf(maxSeq), LPAD_LEN, PAD_STR);
//
//        // AadditionalChargeForContract
//        if (!insertDsAdditionalChargeIfForContract(cMsg)) {
//            return;
//        }
//
//        for (int i = 0; i < sMsg.O.getValidCount(); i++) {
//            NSAL0610_OSMsg osMsg = sMsg.O.no(i);
//
//            if (ZYPConstant.CHKBOX_ON_Y.equals(osMsg.xxDplyCtrlFlg_N.getValue())) {
//                // ContractInterface
//                if (!insertDsContractIf(cMsg, osMsg)) {
//                    return;
//                }
//                // AadditionalCharge
//                if (!insertDsAdditionalChargeIfForDetail(cMsg, osMsg)) {
//                    return;
//                }
//
//                if (!DS_CONTR_DTL_TP.ACCESSORIES.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//                    // PhysicalBillingMeterRelation
//                    if (!insertPhysicalBillingMeterRelationIf(cMsg, osMsg)) {
//                        return;
//                    }
//                    // ExcessCopy
//                    if (!insertExcessCopyIf(cMsg, osMsg)) {
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * create DS Contract Interface For Regular
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     * @param string
//     */
//    private boolean insertDsContractIf(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//
//        S21SsmEZDResult ssmResult = new S21SsmEZDResult();
//
//        if (DS_CONTR_DTL_TP.FLEET.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//            // Fleet
//            ssmResult = NSAL0610Query.getInstance().getContractIfData(cMsg, osMsg);
//
//        } else if (DS_CONTR_DTL_TP.AGGREGATE.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//            // Aggregate
//            ssmResult = NSAL0610Query.getInstance().getContractIfData(cMsg, osMsg);
//
//        } else if (!DS_CONTR_DTL_TP.ACCESSORIES.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//            // Regular
//            ssmResult = NSAL0610Query.getInstance().getContractIfData(cMsg, osMsg);
//
//        } else if (DS_CONTR_DTL_TP.ACCESSORIES.equals(osMsg.dsContrDtlTpCd_N.getValue())) {
//            // Accessories
//            ssmResult = NSAL0610Query.getInstance().getAccessoriesData(cMsg, osMsg);
//        }
//
//        if (ssmResult.isCodeNormal()) {
//            List<DS_CONTR_INTFCTMsg> inTMsgList = new ArrayList<DS_CONTR_INTFCTMsg>();
//
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//            for (int i = 0; i < rsltList.size(); i++) {
//                DS_CONTR_INTFCTMsg tMsg = new DS_CONTR_INTFCTMsg();
//                Map<String, Object> rsltMap = rsltList.get(i);
//
//                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
//                setValue(tMsg.dsContrIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_CONTR_INTFC_SQ));
//                setValue(tMsg.dsContrIntfcBatNum, batchNum);
//                setValue(tMsg.dsContrIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
//                setValue(tMsg.dsContrSrcRefNum, cMsg.dsContrNum_H1);
//                setValue(tMsg.dsContrNum, dsContrNum);
//                setValue(tMsg.dsContrIntfcActCd, DS_CONTR_INTFC_ACT.CREATE);
//                setValue(tMsg.dsContrProcStsCd, DS_CONTR_PROC_STS.IN_PROCESS);
//                setValue(tMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.COPY_CONTRACT);
//                setValue(tMsg.contrIntfcDtlTpCd, (String) rsltMap.get("CONTR_INTFC_DTL_TP_CD"));
//                setValue(tMsg.dsAcctNum, (String) rsltMap.get("DS_ACCT_NUM"));
//                setValue(tMsg.billToCustCd, (String) rsltMap.get("BILL_TO_CUST_CD"));
//                setValue(tMsg.billToLocNum, (String) rsltMap.get("LOC_NUM"));
//                setValue(tMsg.baseChrgToLeaseCmpyFlg, (String) rsltMap.get("BASE_CHRG_TO_LEASE_CMPY_FLG"));
//                setValue(tMsg.usgChrgToLeaseCmpyFlg, (String) rsltMap.get("USG_CHRG_TO_LEASE_CMPY_FLG"));
//                setValue(tMsg.leaseCmpyCd, (String) rsltMap.get("LEASE_CMPY_CD"));
//                setValue(tMsg.dsContrCatgCd, (String) rsltMap.get("DS_CONTR_CATG_CD"));
//                setValue(tMsg.dsContrStsCd, (String) rsltMap.get("DS_CONTR_STS_CD"));
//                setValue(tMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//                setValue(tMsg.svcContrBrCd, (String) rsltMap.get("SVC_CONTR_BR_CD"));
//                setValue(tMsg.tocCd, (String) rsltMap.get("TOC_CD"));
//                setValue(tMsg.custPoNum, (String) rsltMap.get("CUST_PO_NUM"));
//                setValue(tMsg.poDt, (String) rsltMap.get("PO_DT"));
//                setValue(tMsg.crCardCustRefNum, (String) rsltMap.get("CR_CARD_CUST_REF_NUM"));
//                setValue(tMsg.crCardExprYrMth, (String) rsltMap.get("CR_CARD_EXPR_YR_MTH"));
//                setValue(tMsg.contrAutoRnwTpCd, (String) rsltMap.get("CONTR_AUTO_RNW_TP_CD"));
//                setValue(tMsg.rnwPrcMethCd, (String) rsltMap.get("RNW_PRC_METH_CD"));
//                setValue(tMsg.befEndRnwDaysAot, (BigDecimal) rsltMap.get("BEF_END_RNW_DAYS_AOT"));
//                setValue(tMsg.rnwPrcUpRatio, (BigDecimal) rsltMap.get("RNW_PRC_UP_RATIO"));
//                setValue(tMsg.contrUplftTpCd, (String) rsltMap.get("CONTR_UPLFT_TP_CD"));
//                setValue(tMsg.uplftPrcMethCd, (String) rsltMap.get("UPLFT_PRC_METH_CD"));
//                setValue(tMsg.uplftPrcUpRatio, (BigDecimal) rsltMap.get("UPLFT_PRC_UP_RATIO"));
//                setValue(tMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//                setValue(tMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
//                setValue(tMsg.serNum, (String) rsltMap.get("SER_NUM"));
//                setValue(tMsg.mdlId, (BigDecimal) rsltMap.get("T_MDL_ID"));
//                setValue(tMsg.mdlNm, (String) rsltMap.get("T_MDL_NM"));
//                setValue(tMsg.bllgCycleCd, (String) rsltMap.get("BLLG_CYCLE_CD"));
//                setValue(tMsg.bllgMtrLbNm, (String) rsltMap.get("BLLG_MTR_LB_NM"));
//                setValue(tMsg.bllgMtrLbCd, (String) rsltMap.get("BLLG_MTR_LB_CD"));
//                setValue(tMsg.xsChrgTpCd, (String) rsltMap.get("XS_CHRG_TP_CD"));
//                setValue(tMsg.mtrReadMethCd, (String) rsltMap.get("MTR_READ_METH_CD"));
//                setValue(tMsg.xsMtrCopyQty, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
//                setValue(tMsg.bllgRollOverRatio, (BigDecimal) rsltMap.get("BLLG_ROLL_OVER_RATIO"));
//                setValue(tMsg.basePrcDealAmt, (BigDecimal) rsltMap.get("BASE_PRC_DEAL_AMT"));
//                setValue(tMsg.xsMtrAmtRate, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
//                setValue(tMsg.printDtlFlg, ZYPConstant.FLG_OFF_N);
//                setValue(tMsg.basePrcTermDealAmtRate, (BigDecimal) rsltMap.get("BASE_PRC_TERM_DEAL_AMT_RATE"));
//                setValue(tMsg.dsContrClsCd, (String) rsltMap.get("DS_CONTR_CLS_CD"));
//                setValue(tMsg.ctacPsnPk, (BigDecimal) rsltMap.get("CTAC_PSN_PK"));
//                setValue(tMsg.ctacPsnNm, (String) rsltMap.get("CTAC_PSN_NM"));
//                setValue(tMsg.mtrEstTpCd, (String) rsltMap.get("MTR_EST_TP_CD"));
//                setValue(tMsg.prcAllocByMachQtyFlg, (String) rsltMap.get("PRC_ALLOC_BY_MACH_QTY_FLG"));
//                setValue(tMsg.svcPgmMdseCd, (String) rsltMap.get("SVC_PGM_MDSE_CD"));
//                setValue(tMsg.invSeptBaseUsgFlg, (String) rsltMap.get("INV_SEPT_BASE_USG_FLG"));
//                setValue(tMsg.pmtTermCashDiscCd, (String) rsltMap.get("PMT_TERM_CASH_DISC_CD"));
//                setValue(tMsg.svcLineBizCd, (String) rsltMap.get("SVC_LINE_BIZ_CD"));
//                setValue(tMsg.contrCloDay, (String) rsltMap.get("CONTR_CLO_DAY"));
//                setValue(tMsg.contrBllgDay, (String) rsltMap.get("CONTR_BLLG_DAY"));
//                setValue(tMsg.bllgTmgTpCd, (String) rsltMap.get("BLLG_TMG_CD"));
//                setValue(tMsg.dsContrEdiCd, (String) rsltMap.get("DS_CONTR_EDI_CD"));
//                setValue(tMsg.dsContrIntfcDt, cMsg.slsDt);
//                setValue(tMsg.dsContrIntfcBatSq, BigDecimal.valueOf(maxSeq));
//
//                inTMsgList.add(tMsg);
//            }
//
//            if (!insertDsContractIf(inTMsgList)) {
//                cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_CONTR_INTFC });
//                return false;
//            }
//            return true;
//        }
//        cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_CONTR_INTFC });
//        return false;
//    }
//
//    private boolean insertDsContractIf(List<DS_CONTR_INTFCTMsg> inMsgLst) {
//        DS_CONTR_INTFCTMsg[] inMsgArray;
//        inMsgArray = new DS_CONTR_INTFCTMsg[inMsgLst.size()];
//        int insertCount = S21FastTBLAccessor.insert(inMsgLst.toArray(inMsgArray));
//
//        if (insertCount != inMsgArray.length) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * create DS Additional Charge Interface
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     */
//    private boolean insertDsAdditionalChargeIfForDetail(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getAdditionalChargeForDetail(cMsg, osMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            return true;
//        }
//        if (ssmResult.isCodeNormal()) {
//            return insertDsAddtionalChargeIf(cMsg, ssmResult);
//        }
//        cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_ADDL_CHRG_INTFC });
//        return false;
//    }
//
//    /**
//     * create DS Additional Charge Interface For Contract
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     */
//    private boolean insertDsAdditionalChargeIfForContract(NSAL0610CMsg cMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getAdditionalChargeForContract(cMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            return true;
//        }
//        if (ssmResult.isCodeNormal()) {
//            return insertDsAddtionalChargeIf(cMsg, ssmResult);
//        }
//        cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_ADDL_CHRG_INTFC });
//        return false;
//    }
//
//    private boolean insertDsAddtionalChargeIf(NSAL0610CMsg cMsg, S21SsmEZDResult ssmResult) {
//        List<DS_ADDL_CHRG_INTFCTMsg> inTMsgList = new ArrayList<DS_ADDL_CHRG_INTFCTMsg>();
//
//        @SuppressWarnings("unchecked")
//        List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//        for (int i = 0; i < rsltList.size(); i++) {
//            DS_ADDL_CHRG_INTFCTMsg tMsg = new DS_ADDL_CHRG_INTFCTMsg();
//            Map<String, Object> rsltMap = rsltList.get(i);
//
//            setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
//            setValue(tMsg.dsAddlChrgIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ADDL_CHRG_INTFC_SQ));
//            setValue(tMsg.dsContrIntfcBatNum, batchNum);
//            setValue(tMsg.addChrgIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
//            setValue(tMsg.dsContrSrcRefNum, cMsg.dsContrNum_H1);
//            setValue(tMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.COPY_CONTRACT);
//            setValue(tMsg.serNum, (String) rsltMap.get("SER_NUM"));
//            setValue(tMsg.chrgLvlTpCd, (String) rsltMap.get("CHRG_LVL_TP_CD"));
//            setValue(tMsg.addlChrgTpCd, (String) rsltMap.get("ADDL_CHRG_TP_CD"));
//            setValue(tMsg.addlChrgFlatDealPrcAmt, (BigDecimal) rsltMap.get("ADDL_CHRG_FLAT_DEAL_PRC_AMT"));
//            setValue(tMsg.addlChrgAplcPct, (BigDecimal) rsltMap.get("ADDL_CHRG_APLC_PCT"));
//            setValue(tMsg.addlChrgInvTpCd, (String) rsltMap.get("ADDL_CHRG_INV_TP_CD"));
//            setValue(tMsg.printDtlFlg, (String) rsltMap.get("PRINT_DTL_FLG"));
//            setValue(tMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
//            setValue(tMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//
//            inTMsgList.add(tMsg);
//        }
//
//        DS_ADDL_CHRG_INTFCTMsg[] inMsgArray;
//        inMsgArray = new DS_ADDL_CHRG_INTFCTMsg[inTMsgList.size()];
//        int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
//
//        if (insertCount != inMsgArray.length) {
//            cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_ADDL_CHRG_INTFC });
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * create DS PhysicalBillingMeterRelation Interface
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     */
//    private boolean insertPhysicalBillingMeterRelationIf(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getPhysicalBillingMeterRelation(cMsg, osMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            return true;
//        }
//        if (ssmResult.isCodeNormal()) {
//            List<DS_ACTL_CNT_INTFCTMsg> inTMsgList = new ArrayList<DS_ACTL_CNT_INTFCTMsg>();
//
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//            for (int i = 0; i < rsltList.size(); i++) {
//                DS_ACTL_CNT_INTFCTMsg tMsg = new DS_ACTL_CNT_INTFCTMsg();
//                Map<String, Object> rsltMap = rsltList.get(i);
//
//                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
//                setValue(tMsg.dsActlCntIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_ACTL_CNT_INTFC_SQ));
//                setValue(tMsg.dsContrIntfcBatNum, batchNum);
//                setValue(tMsg.actlCntIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
//                setValue(tMsg.dsContrSrcRefNum, cMsg.dsContrNum_H1);
//                setValue(tMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.COPY_CONTRACT);
//                setValue(tMsg.serNum, (String) rsltMap.get("SER_NUM"));
//                setValue(tMsg.physMtrLbCd, (String) rsltMap.get("PHYS_MTR_LB_CD"));
//                setValue(tMsg.bllblFlg, (String) rsltMap.get("BLLBL_FLG"));
//                setValue(tMsg.contrMtrMultRate, (BigDecimal) rsltMap.get("CONTR_MTR_MULT_RATE"));
//                setValue(tMsg.bllgMtrLbCd, (String) rsltMap.get("BLLG_MTR_LB_CD"));
//                setValue(tMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
//                setValue(tMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//
//                inTMsgList.add(tMsg);
//            }
//
//            DS_ACTL_CNT_INTFCTMsg[] inMsgArray;
//            inMsgArray = new DS_ACTL_CNT_INTFCTMsg[inTMsgList.size()];
//            int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
//
//            if (insertCount != inMsgArray.length) {
//                cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_ACTL_CNT_INTFC });
//                return false;
//            }
//            return true;
//        }
//        cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_ACTL_CNT_INTFC });
//        return false;
//    }
//
//    /**
//     * create DS Excess Copy Interface
//     * @param cMsg NSAL0610CMsg
//     * @param osMsg NSAL0610_OSMsg
//     */
//    private boolean insertExcessCopyIf(NSAL0610CMsg cMsg, NSAL0610_OSMsg osMsg) {
//
//        S21SsmEZDResult ssmResult = NSAL0610Query.getInstance().getExcessCopy(cMsg, osMsg);
//
//        if (ssmResult.isCodeNotFound()) {
//            return true;
//        }
//        if (ssmResult.isCodeNormal()) {
//            List<DS_XS_COPY_INTFCTMsg> inTMsgList = new ArrayList<DS_XS_COPY_INTFCTMsg>();
//
//            @SuppressWarnings("unchecked")
//            List<Map<String, Object>> rsltList = (List<Map<String, Object>>) ssmResult.getResultObject();
//
//            for (int i = 0; i < rsltList.size(); i++) {
//                DS_XS_COPY_INTFCTMsg tMsg = new DS_XS_COPY_INTFCTMsg();
//                Map<String, Object> rsltMap = rsltList.get(i);
//
//                setValue(tMsg.glblCmpyCd, cMsg.glblCmpyCd);
//                setValue(tMsg.dsXsCopyIntfcPk, ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqConstant.DS_XS_COPY_INTFC_SQ));
//                setValue(tMsg.dsContrIntfcBatNum, batchNum);
//                setValue(tMsg.xsCopyIntfcStsCd, DS_CONTR_INTFC_STS.NORMAL);
//                setValue(tMsg.dsContrSrcRefNum, cMsg.dsContrNum_H1);
//                setValue(tMsg.contrIntfcSrcTpCd, CONTR_INTFC_SRC_TP.COPY_CONTRACT);
//                setValue(tMsg.serNum, (String) rsltMap.get("SER_NUM"));
//                setValue(tMsg.xsMtrCopyQty, (BigDecimal) rsltMap.get("XS_MTR_COPY_QTY"));
//                setValue(tMsg.xsMtrAmtRate, (BigDecimal) rsltMap.get("XS_MTR_AMT_RATE"));
//                setValue(tMsg.bllgMtrLbCd, (String) rsltMap.get("BLLG_MTR_LB_CD"));
//                setValue(tMsg.mdseCd, (String) rsltMap.get("MDSE_CD"));
//                setValue(tMsg.svcMachMstrPk, (BigDecimal) rsltMap.get("SVC_MACH_MSTR_PK"));
//
//                inTMsgList.add(tMsg);
//            }
//
//            DS_XS_COPY_INTFCTMsg[] inMsgArray;
//            inMsgArray = new DS_XS_COPY_INTFCTMsg[inTMsgList.size()];
//            int insertCount = S21FastTBLAccessor.insert(inTMsgList.toArray(inMsgArray));
//
//            if (insertCount != inMsgArray.length) {
//                cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_XS_COPY_INTFC });
//                return false;
//            }
//            return true;
//        }
//        cMsg.setMessageInfo(NSAM0032E, new String[] {TBL_DS_XS_COPY_INTFC });
//        return false;
//    }
//
}

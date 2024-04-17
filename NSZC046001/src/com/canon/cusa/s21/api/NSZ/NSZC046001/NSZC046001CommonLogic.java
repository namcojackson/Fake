/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC046001;

import static com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.COA_ACCTTMsg;
import business.db.COA_AFFLTMsg;
import business.db.COA_BRTMsg;
import business.db.COA_CCTMsg;
import business.db.COA_CHTMsg;
import business.db.COA_CMPYTMsg;
import business.db.COA_EXTNTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.CR_CARD_TRXTMsg;
import business.db.CTAC_PSNTMsg;
import business.db.DS_CONTR_CTRL_STSTMsg;
import business.db.DS_CONTR_CTRL_STSTMsgArray;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTRTMsgArray;
import business.db.DS_CONTR_CR_CARD_POTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTL_TPTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CR_CARDTMsg;
import business.db.DS_CR_CARDTMsgArray;
import business.db.MDSETMsg;
import business.db.S21_ORGTMsg;
import business.db.S21_ORGTMsgArray;
import business.db.SVC_CONTR_BRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.parts.NSZC046001PMsg;
import business.parts.NSZC046001_xxContrDtlListPMsg;
import business.parts.NSZC046001_xxContrXsCopyListPMsg;
import business.parts.NSZC046001_xxDsContrBllgMtrListPMsg;
import business.parts.NSZC046001_xxDsContrCrCardPoListPMsg;

import com.canon.cusa.s21.api.NSZ.NSZC046001.constant.NSZC046001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ADDL_CHRG_INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CYCLE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_AUTO_RNW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONTR_UPLFT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_AUTH_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TRX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTAC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_EDI;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_EST_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_LB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_READ_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PMT_TERM_CASH_DISC;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_BILL_BY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.XS_CHRG_TP;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Service Contract Data Update API
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/13   Hitachi         K.Kasai         Create          N/A
 * 2016/01/21   Hitachi         T.Iwamoto       Update          QC#3201
 * 2016/01/25   Hitachi         T.Iwamoto       Update          QC#3531
 * 2016/03/14   Hitachi         T.Iwamoto       Update          QC#5298
 * 2016/03/23   Hitachi         T.Iwamoto       Update          QC#5828,5035
 * 2016/03/25   Hitachi         T.Iwamoto       Update          QC#5662
 * 2016/04/08   Hitachi         A.Kohinata      Update          QC#6622
 * 2016/08/25   Hitachi         T.Tomita        Update          QC#12136
 * 2016/09/02   Hitachi         A.Kohinata      Update          QC#11243
 * 2016/09/26   Hitachi         K.Kishimoto     Update          QC#14428
 * 2016/09/28   Hitachi         K.Kishimoto     Update          QC#9905
 * 2017/07/26   Hitachi         M.Kidokoro      Update          QC#18122
 * 2017/08/09   Hitachi         T.Kanasaka      Update          QC#18193,18195
 * 2018/04/03   Hitachi         U.Kim           Update          QC#23559(Sol358)
 * 2019/04/12   Hitachi         A.Kohinata      Update          QC#31179
 * </pre>
 */
public class NSZC046001CommonLogic {
    // add start 2016/08/25 CSA Defect#12136
    /** ssm client */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NSZC046001CommonLogic.class);
    // add end 2016/08/25 CSA Defect#12136

    protected static boolean isExistSellTo(String glblCmpyCd, String dsAcctNum) {
        SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("sellToCustCd01", dsAcctNum);
        tMsg.setMaxCount(1);
        tMsg.setSQLID("100");

        SELL_TO_CUSTTMsgArray tMsgs = (SELL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgs == null || tMsgs.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    protected static boolean isExistToc(String glblCmpyCd, String tocCd) {
        S21_ORGTMsg tMsg = new S21_ORGTMsg();
        // mod start 2019/04/12 QC#31179
        //tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        //tMsg.setConditionValue("tocCd01", tocCd);
        //tMsg.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        //tMsg.setMaxCount(1);
        //tMsg.setSQLID("003");

        //S21_ORGTMsgArray tMsgs = (S21_ORGTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        //if (tMsgs == null || tMsgs.getValidCount() == 0) {
        //    return false;
        //}
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, tocCd);
        tMsg = (S21_ORGTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        // mod end 2019/04/12 QC#31179
        return true;
    }

    protected static boolean isExistContrBr(String glblCmpyCd, String svcContrBrCd) {
        SVC_CONTR_BRTMsg tMsg = new SVC_CONTR_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcContrBrCd, svcContrBrCd);

        tMsg = (SVC_CONTR_BRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistBillTo(String glblCmpyCd, String billToCustCd) {
        BILL_TO_CUSTTMsg tMsg = new BILL_TO_CUSTTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("billToCustCd01", billToCustCd);
        tMsg.setMaxCount(1);
        tMsg.setSQLID("003");

        BILL_TO_CUSTTMsgArray tMsgs = (BILL_TO_CUSTTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgs == null || tMsgs.getValidCount() == 0) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCtacPsn(String glblCmpyCd, BigDecimal ctacPsnPk, String slsDt) {
        // START 2018/04/03 U.Kim [QC#23559(Sol358), ADD]
        String [] ctacTpList = {CTAC_TP.BILL_TO_CONTACT, CTAC_TP.BILL_TO_CONTACT_CONTRACTS};
        // END 2018/04/03 U.Kim [QC#23559(Sol358), ADD]
        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("ctacPsnPk", ctacPsnPk);
        // START 2018/04/03 U.Kim [QC#23559(Sol358), MOD]
        // ssmPrm.put("ctacTpCdBillTo", CTAC_TP.BILL_TO_CONTACT);
        ssmPrm.put("ctacTpList", ctacTpList);
        // END 2018/04/03 U.Kim [QC#23559(Sol358), MOD]
        ssmPrm.put("maxEndDt", NSZC046001Constant.MAX_END_DT);
        ssmPrm.put("slsDt", slsDt);
        BigDecimal countCtacTpBillTo = (BigDecimal) ssmBatchClient.queryObject("countCtacTpBillTo", ssmPrm);
        if (BigDecimal.ZERO.compareTo(countCtacTpBillTo) == 0) {
            return false;
        }
        return true;
    }

    protected static boolean isExistMdse(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);

        tMsg = (MDSETMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = getMachMstr(glblCmpyCd, svcMachMstrPk);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static SVC_MACH_MSTRTMsg getMachMstr(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        SVC_MACH_MSTRTMsg tMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcMachMstrPk, svcMachMstrPk);

        return (SVC_MACH_MSTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    protected static boolean isExistCcRefNum(String glblCmpyCd, String crCardRefNum) {
        DS_CR_CARDTMsg tMsg = getCrCard(glblCmpyCd, crCardRefNum);

        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static DS_CR_CARDTMsg getCrCard(String glblCmpyCd, String crCardRefNum) {
        DS_CR_CARDTMsg tMsg = new DS_CR_CARDTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("crCardCustRefNum01", crCardRefNum);
        tMsg.setMaxCount(1);
        tMsg.setSQLID("001");

        DS_CR_CARDTMsgArray tMsgs = (DS_CR_CARDTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgs != null) {
            return tMsgs.no(0);
        }
        return null;
    }

    // add start 2016/09/02 CSA Defect#11243
    protected static boolean isExistCcRefNum(String glblCmpyCd, NSZC046001_xxDsContrCrCardPoListPMsg ccMsg) {
        if (!ZYPCommonFunc.hasValue(ccMsg.dsContrPk) && !ZYPCommonFunc.hasValue(ccMsg.dsContrDtlPk) && !ZYPCommonFunc.hasValue(ccMsg.dsContrBllgMtrPk)) {
            return isExistCcRefNum(glblCmpyCd, ccMsg.crCardCustRefNum.getValue());
        }

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", ccMsg.dsContrPk.getValue());
        params.put("dsContrDtlPk", ccMsg.dsContrDtlPk.getValue());
        params.put("dsContrBllgMtrPk", ccMsg.dsContrBllgMtrPk.getValue());
        params.put("crCardCustRefNum", ccMsg.crCardCustRefNum.getValue());

        int count = 0;
        if (ZYPCommonFunc.hasValue(ccMsg.dsContrPk) && ZYPCommonFunc.hasValue(ccMsg.dsContrDtlPk) && ZYPCommonFunc.hasValue(ccMsg.dsContrBllgMtrPk)) {
            count = (Integer) ssmBatchClient.queryObject("getCcRefNumCntForMeter", params);
        } else if (ZYPCommonFunc.hasValue(ccMsg.dsContrPk) && ZYPCommonFunc.hasValue(ccMsg.dsContrDtlPk)) {
            count = (Integer) ssmBatchClient.queryObject("getCcRefNumCntForDetail", params);
        } else if (ZYPCommonFunc.hasValue(ccMsg.dsContrPk)) {
            count = (Integer) ssmBatchClient.queryObject("getCcRefNumCntForHeader", params);
        }
        if (count > 0) {
            return true;
        }
        return false;
    }
    // add end 2016/09/02 CSA Defect#11243

    protected static boolean isExistCoaCmpy(String glblCmpyCd, String coaCmpyCd) {
        COA_CMPYTMsg tMsg = new COA_CMPYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaCmpyCd, coaCmpyCd);

        tMsg = (COA_CMPYTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaAffl(String glblCmpyCd, String coaAfflCd) {
        COA_AFFLTMsg tMsg = new COA_AFFLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaAfflCd, coaAfflCd);

        tMsg = (COA_AFFLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaBr(String glblCmpyCd, String coaBrCd) {
        COA_BRTMsg tMsg = new COA_BRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaBrCd, coaBrCd);

        tMsg = (COA_BRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaCh(String glblCmpyCd, String coaChCd) {
        COA_CHTMsg tMsg = new COA_CHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaChCd, coaChCd);

        tMsg = (COA_CHTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaCc(String glblCmpyCd, String coaCcCd) {
        COA_CCTMsg tMsg = new COA_CCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaCcCd, coaCcCd);

        tMsg = (COA_CCTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaAcct(String glblCmpyCd, String coaAcctCd) {
        COA_ACCTTMsg tMsg = new COA_ACCTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaAcctCd, coaAcctCd);

        tMsg = (COA_ACCTTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaProd(String glblCmpyCd, String coaProdCd) {
        COA_PRODTMsg tMsg = new COA_PRODTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaProdCd, coaProdCd);

        tMsg = (COA_PRODTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaProj(String glblCmpyCd, String coaProjCd) {
        COA_PROJTMsg tMsg = new COA_PROJTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaProjCd, coaProjCd);

        tMsg = (COA_PROJTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static boolean isExistCoaExtn(String glblCmpyCd, String coaExtnCd) {
        COA_EXTNTMsg tMsg = new COA_EXTNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.coaExtnCd, coaExtnCd);

        tMsg = (COA_EXTNTMsg) S21ApiTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static DS_CONTRTMsg getDsContr(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPk, dsContrPk);

        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    protected static DS_CONTRTMsg getDsContr(String glblCmpyCd, String dsContrNum) {
        DS_CONTRTMsg tMsg = new DS_CONTRTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsContrNum01", dsContrNum);
        tMsg.setConditionValue("dsContrSqNum01", "00");
        tMsg.setMaxCount(1);
        tMsg.setSQLID("002");

        DS_CONTRTMsgArray tMsgs = (DS_CONTRTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgs == null || tMsgs.getValidCount() == 0) {
            return null;
        }
        return tMsgs.no(0);
    }

    protected static boolean isExistDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = getDsContrDtl(glblCmpyCd, dsContrDtlPk);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static DS_CONTR_DTLTMsg getDsContrDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg tMsg = new DS_CONTR_DTLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrDtlPk, dsContrDtlPk);

        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(tMsg);
    }

    protected static CONTR_XS_COPYTMsgArray getXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg tMsg = new CONTR_XS_COPYTMsg();
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        tMsg.setMaxCount(0);
        tMsg.setSQLID("002");

        CONTR_XS_COPYTMsgArray tMsgs = (CONTR_XS_COPYTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgs == null || tMsgs.getValidCount() == 0) {
            return null;
        }
        return tMsgs;
    }

    protected static DS_CONTR_PRC_EFFTMsg getPrcEffForUpdate(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_PRC_EFFTMsg tMsg = new DS_CONTR_PRC_EFFTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsContrPrcEffPk, dsContrPrcEffPk);

        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(tMsg);
    }

    protected static boolean isExistDsContrSts(String glblCmpyCd, String dsContrStsCd) {
        return isExistCode(DS_CONTR_STS.class.getSimpleName(), glblCmpyCd, dsContrStsCd);
    }

    protected static boolean isExistDsContrCatg(String glblCmpyCd, String dsContrCargCd) {
        return isExistCode(DS_CONTR_CATG.class.getSimpleName(), glblCmpyCd, dsContrCargCd);
    }

    protected static boolean isExistPmtTerm(String glblCmpyCd, String pmtTermCashDiscCd) {
        return isExistCode(PMT_TERM_CASH_DISC.class.getSimpleName(), glblCmpyCd, pmtTermCashDiscCd);
    }

    protected static boolean isExistContrUplftTp(String glblCmpyCd, String contrUplftTpCd) {
        return isExistCode(CONTR_UPLFT_TP.class.getSimpleName(), glblCmpyCd, contrUplftTpCd);
    }

    protected static boolean isExistMtrEstTp(String glblCmpyCd, String mtrEstTpCd) {
        return isExistCode(MTR_EST_TP.class.getSimpleName(), glblCmpyCd, mtrEstTpCd);
    }

    protected static boolean isExistContrCls(String glblCmpyCd, String dsContrClsCd) {
        return isExistCode(DS_CONTR_CLS.class.getSimpleName(), glblCmpyCd, dsContrClsCd);
    }

    protected static boolean isExistContrEDI(String glblCmpyCd, String dsContrEdiCd) {
        return isExistCode(DS_CONTR_EDI.class.getSimpleName(), glblCmpyCd, dsContrEdiCd);
    }

    protected static boolean isExistContrDtlTp(String glblCmpyCd, String dsContrDtlTpCd) {
        return isExistCode(DS_CONTR_DTL_TP.class.getSimpleName(), glblCmpyCd, dsContrDtlTpCd);
    }

    protected static boolean isExistContrDtlSts(String glblCmpyCd, String dsContrDtlStsCd) {
        return isExistCode(DS_CONTR_DTL_STS.class.getSimpleName(), glblCmpyCd, dsContrDtlStsCd);
    }

    protected static boolean isExistBllgCycle(String glblCmpyCd, String bllgCycleCd) {
        return isExistCode(BLLG_CYCLE.class.getSimpleName(), glblCmpyCd, bllgCycleCd);
    }

    protected static boolean isExistBllgTmg(String glblCmpyCd, String bllgTmgTpCd) {
        return isExistCode(BLLG_TMG_TP.class.getSimpleName(), glblCmpyCd, bllgTmgTpCd);
    }

    protected static boolean isExistMtrReadMeth(String glblCmpyCd, String mtrReadMethCd) {
        return isExistCode(MTR_READ_METH.class.getSimpleName(), glblCmpyCd, mtrReadMethCd);
    }

    protected static boolean isExistMtrLb(String glblCmpyCd, String mtrLbCd) {
        return isExistCode(MTR_LB.class.getSimpleName(), glblCmpyCd, mtrLbCd);
    }

    protected static boolean isExistXsChrgTp(String glblCmpyCd, String xsChrgTpCd) {
        return isExistCode(XS_CHRG_TP.class.getSimpleName(), glblCmpyCd, xsChrgTpCd);
    }

    protected static boolean isExistAutoRnwTp(String glblCmpyCd, String contrAutoRnwTpCd) {
        return isExistCode(CONTR_AUTO_RNW_TP.class.getSimpleName(), glblCmpyCd, contrAutoRnwTpCd);
    }

    protected static boolean isExistAddlChrgTp(String glblCmpyCd, String addlChrgTpCd) {
        // Mod Start 2016/09/26 <QC#14428>
//        return isExistCode(ADDL_CHRG_TP.class.getSimpleName(), glblCmpyCd, addlChrgTpCd);
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("addlChrgTpCd", addlChrgTpCd);
        Map<String, Object> resultMap = (Map<String, Object>)ssmBatchClient.queryObject("getAddlChrgTpV", params);
        if (resultMap != null) {
            return true;
        }
        return false;
        // Mod Start 2016/09/26 <QC#14428>
    }

    protected static boolean isExistAddlChrgInvTp(String glblCmpyCd, String addlChrgInvTpCd) {
        return isExistCode(ADDL_CHRG_INV_TP.class.getSimpleName(), glblCmpyCd, addlChrgInvTpCd);
    }

    protected static boolean isExistUplftTp(String glblCmpyCd, String contrUplftTpCd) {
        return isExistCode(CONTR_UPLFT_TP.class.getSimpleName(), glblCmpyCd, contrUplftTpCd);
    }

    protected static boolean isExistSvcInvChrgTp(String glblCmpyCd, String svcInvChrgTpCd) {
        return isExistCode(SVC_INV_CHRG_TP.class.getSimpleName(), glblCmpyCd, svcInvChrgTpCd);
    }

    protected static boolean isExistSvcBillByTp(String glblCmpyCd, String svcBillByTpCd) {
        return isExistCode(SVC_BILL_BY_TP.class.getSimpleName(), glblCmpyCd, svcBillByTpCd);
    }

    protected static boolean isFleetLine(NSZC046001_xxContrDtlListPMsg dtl) {
        return DS_CONTR_DTL_TP.FLEET.equals(dtl.dsContrDtlTpCd.getValue());
    }

    protected static boolean isExistCode(String tblName, String glblCmpyCd, String codeVal) {
        if (!ZYPCommonFunc.hasValue(codeVal)) {
            return true;
        }
        EZDTMsg tMsg = getCodeTbl(tblName, glblCmpyCd, codeVal);
        if (tMsg == null) {
            return false;
        }
        return true;
    }

    protected static EZDTMsg getCodeTbl(String tblName, String glblCmpyCd, String codeVal) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("GLBL_CMPY_CD", glblCmpyCd);
        key.put(tblName + "_CD", codeVal);

        return S21CodeTableAccessor.findByKey(tblName, key);
    }

    protected static boolean isFleetMach(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!DS_CONTR_CATG.FLEET.equals(hdr.dsContrCatgCd.getValue())) {
            return false;
        }
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), hdr.glblCmpyCd.getValue()
                , dtl.dsContrDtlTpCd.getValue());

        return FLG_ON_Y.equals(dtlTp.mainUnitLineFlg.getValue());
    }

    protected static boolean isUnderFleet(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!DS_CONTR_CATG.FLEET.equals(hdr.dsContrCatgCd.getValue())) {
            return false;
        }
        if (DS_CONTR_DTL_TP.FLEET.equals(dtl.dsContrDtlTpCd.getValue())) {
            return false;
        }
        return true;
    }

    protected static boolean isAggLine(NSZC046001_xxContrDtlListPMsg dtl) {
        return DS_CONTR_DTL_TP.AGGREGATE.equals(dtl.dsContrDtlTpCd.getValue());
    }

    protected static boolean isAggMach(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!DS_CONTR_CATG.AGGREGATE.equals(hdr.dsContrCatgCd.getValue())) {
            return false;
        }
        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), hdr.glblCmpyCd.getValue()
                , dtl.dsContrDtlTpCd.getValue());

        return FLG_ON_Y.equals(dtlTp.mainUnitLineFlg.getValue());
    }

    protected static boolean isUnderAgg(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!DS_CONTR_CATG.AGGREGATE.equals(hdr.dsContrCatgCd.getValue())) {
            return false;
        }
        if (DS_CONTR_DTL_TP.AGGREGATE.equals(dtl.dsContrDtlTpCd.getValue())) {
            return false;
        }
        return true;
    }

    protected static boolean isRegular(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        //fix judge condition
        //if (!DS_CONTR_CATG.REGULAR.equals(hdr.dsContrCatgCd.getValue())) {
        //    return false;
        //}
        //DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), hdr.glblCmpyCd.getValue()
        //        , dtl.dsContrDtlTpCd.getValue());

        //return FLG_ON_Y.equals(dtlTp.mainUnitLineFlg.getValue());
        return DS_CONTR_CATG.REGULAR.equals(hdr.dsContrCatgCd.getValue());
    }

    protected static boolean isAccessory(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        SVC_MACH_MSTRTMsg tMsg = getMachMstr(hdr.glblCmpyCd.getValue(), dtl.svcMachMstrPk.getValue());

        if (tMsg == null) {
            return false;
        }
        return SVC_MACH_TP.ACCESSORY.equals(tMsg.svcMachTpCd.getValue());
    }

    // 2016/03/23 [QC#5035, ADD]
    protected static boolean isWarranty(NSZC046001PMsg hdr) {
        return DS_CONTR_CATG.WARRANTY.equals(hdr.dsContrCatgCd.getValue());
    }

    protected static boolean isUsgChrg(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        // START 2016/03/23 [QC#5828, ADD]
        if (hdr.xxDsContrBllgMtrList.getValidCount() == 0) {
            return false;
        }
        // END 2016/03/23 [QC#5828, ADD]

        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), hdr.glblCmpyCd.getValue()
                , dtl.dsContrDtlTpCd.getValue());
        return FLG_ON_Y.equals(dtlTp.usgChrgFlg.getValue());
    }

    protected static boolean isBaseChrg(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {

        DS_CONTR_DTL_TPTMsg dtlTp = (DS_CONTR_DTL_TPTMsg) getCodeTbl(DS_CONTR_DTL_TP.class.getSimpleName(), hdr.glblCmpyCd.getValue()
                , dtl.dsContrDtlTpCd.getValue());
        return FLG_ON_Y.equals(dtlTp.baseChrgFlg.getValue());
    }

    protected static void sortXsCopy(List<NSZC046001_xxContrXsCopyListPMsg> xsList) {
        Collections.sort(xsList, new XsComparator());
    }

    /**
     * Xs Copy sort class
     *
     */
    private static final class XsComparator implements Comparator<NSZC046001_xxContrXsCopyListPMsg> {
        @Override
        public int compare(NSZC046001_xxContrXsCopyListPMsg o1, NSZC046001_xxContrXsCopyListPMsg o2) {
            BigDecimal o1Qty = o1.xsMtrCopyQty.getValue();
            BigDecimal o2Qty = o2.xsMtrCopyQty.getValue();
            return o1Qty.compareTo(o2Qty);
        }
    }

    /**
     * isBaseRnw
     * @param hdr NSZC046001PMsg
     * @param dtl NSZC046001_xxContrDtlListPMsg
     * @return boolean
     */
    public static boolean isBaseRnw(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!MODE_RENEWAL.equals(hdr.xxModeCd.getValue())) {
            return false;
        }

        // START 2016/01/25 T.Iwamoto [QC#3531, MOD]
//        boolean isBaseRnw = false;
//        List<EZDPItem> targets = Arrays.asList(dtl.contrEffThruDt, dtl.basePrcDealAmt, dtl.basePrcTermDealAmtRate);
//        for (EZDPItem target : targets) {
//            if (ZYPCommonFunc.hasValue(target)) {
//                isBaseRnw = true;
//                break;
//            }
//        }
        if (ZYPConstant.FLG_ON_Y.equals(dtl.rnwBaseFlg.getValue())) {
            return true;
        }
        return false;
        // END 2016/01/25 T.Iwamoto [QC#3531, MOD]
    }

    /**
     * isUsgRnw
     * @param hdr NSZC046001PMsg
     * @param dtl NSZC046001_xxContrDtlListPMsg
     * @return boolean
     */
    public static boolean isUsgRnw(NSZC046001PMsg hdr, NSZC046001_xxContrDtlListPMsg dtl) {
        if (!MODE_RENEWAL.equals(hdr.xxModeCd.getValue())) {
            return false;
        }

        List<NSZC046001_xxDsContrBllgMtrListPMsg> bllgMtrList = new ArrayList<NSZC046001_xxDsContrBllgMtrListPMsg>();
        for (int i = 0; i < hdr.xxDsContrBllgMtrList.getValidCount(); i++) {
            NSZC046001_xxDsContrBllgMtrListPMsg bllgMtr = hdr.xxDsContrBllgMtrList.no(i);
            if (dtl.dsContrDtlPk.getValue().compareTo(bllgMtr.dsContrDtlPk.getValue()) == 0) {
                bllgMtrList.add(bllgMtr);
            }
        }

        // START 2016/01/25 T.Iwamoto [QC#3531, MOD]
        if (bllgMtrList.size() > 0 && ZYPConstant.FLG_ON_Y.equals(dtl.rnwUsgFlg.getValue())) {
            return true;
        }
        // END 2016/01/25 T.Iwamoto [QC#3531, MOD]
        return false;
    }

    // 2016/03/25 [QC#5662, ADD]
    public static String getMsgTxt(String messageId, String... param) {
        if (!ZYPCommonFunc.hasValue(messageId)) {
            return null;
        }
        String msgTxt = S21MessageFunc.clspGetMessage(messageId, param);
        msgTxt = msgTxt.substring(messageId.length()).trim();
        return msgTxt;
    }

    // START 2016/04/08 [QC#6622, ADD]
    /**
     * check Status Code
     * @param targetStsCd String
     * @param validFlg String (Y: check OK Status, N: check NG Status)
     * @param validStsCdList String...
     * @return boolean
     */
    public static boolean checkStsCd(String targetStsCd, String validFlg, String... validStsCdList) {
        boolean result;
        if (ZYPConstant.FLG_ON_Y.equals(validFlg)) {
            result = false;
            for (String validStsCd : validStsCdList) {
                if (validStsCd.equals(targetStsCd)) {
                    result = true;
                    break;
                }
            }
        } else {
            result = true;
            for (String validStsCd : validStsCdList) {
                if (validStsCd.equals(targetStsCd)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
    // END 2016/04/08 [QC#6622, ADD]

    // add start 2016/08/25 CSA Defect#12136
    // Mod Start 2016/09/28 <QC#9905>
    /**
     * getBllgMtrLvl
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcPhysMtrPk BigDecimal
     * @param bllgMtrLbCd String
     * @return String
     */
    public static String getBllgMtrLvl(NSZC046001PMsg hdr, String glblCmpyCd, String slsDt, BigDecimal svcPhysMtrPk, String bllgMtrLbCd) {
        if (!ZYPCommonFunc.hasValue(svcPhysMtrPk) || !ZYPCommonFunc.hasValue(bllgMtrLbCd)) {
            return null;
        }

        Map<String, Object> ssmPrm = new HashMap<String, Object>();
        ssmPrm.put("glblCmpyCd", glblCmpyCd);
        ssmPrm.put("slsDt", slsDt);
        ssmPrm.put("endDt", MAX_END_DT);
        ssmPrm.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmPrm.put("svcPhysMtrPk", svcPhysMtrPk);
        // START 2017/08/09 T.Kanasaka [QC#18193,18195,DEL]
//        String regFlg = FLG_OFF_N;
//        String fltFlg = FLG_OFF_N;
//        String aggFlg = FLG_OFF_N;
//        if (DS_CONTR_CATG.REGULAR.equals(hdr.dsContrCatgCd.getValue())) {
//            regFlg = FLG_ON_Y;
//        } else if (DS_CONTR_CATG.FLEET.equals(hdr.dsContrCatgCd.getValue())) {
//            fltFlg = FLG_ON_Y;
//        } else if (DS_CONTR_CATG.AGGREGATE.equals(hdr.dsContrCatgCd.getValue())) {
//            aggFlg = FLG_ON_Y;
//        }
//        ssmPrm.put("regFlg", regFlg);
//        ssmPrm.put("fltFlg", fltFlg);
//        ssmPrm.put("aggFlg", aggFlg);
        // END 2017/08/09 T.Kanasaka [QC#18193,18195,DEL]

        return (String) ssmBatchClient.queryObject("getBllgMtrLvl", ssmPrm);
    }
    // Mod End   2016/09/28 <QC#9905>
    // add end 2016/08/25 CSA Defect#12136

    // add start 2016/09/02 CSA Defect#11243
    /**
     * getCrCardTrxTp
     * @param tMsg DS_CONTR_CR_CARD_POTMsg
     * @return String
     */
    public static String getCrCardTrxTp(DS_CONTR_CR_CARD_POTMsg tMsg) {
        String crCardTrxTp = null;
        if (ZYPCommonFunc.hasValue(tMsg.dsContrPk) && ZYPCommonFunc.hasValue(tMsg.dsContrDtlPk) && ZYPCommonFunc.hasValue(tMsg.dsContrBllgMtrPk)) {
            crCardTrxTp = CR_CARD_TRX_TP.CPONTRACT_METER;
        } else if (ZYPCommonFunc.hasValue(tMsg.dsContrPk) && ZYPCommonFunc.hasValue(tMsg.dsContrDtlPk)) {
            crCardTrxTp = CR_CARD_TRX_TP.CONTRACT_DETAIL;
        } else if (ZYPCommonFunc.hasValue(tMsg.dsContrPk)) {
            crCardTrxTp = CR_CARD_TRX_TP.CONTRACT_HEADER;
        }
        return crCardTrxTp;
    }

    /**
     * getCrCardTrxPk
     * @param param NSZC046001PMsg
     * @param tMsg DS_CONTR_CR_CARD_POTMsg
     * @return List<BigDecimal>
     */
    public static List<BigDecimal> getCrCardTrxPk(NSZC046001PMsg param, DS_CONTR_CR_CARD_POTMsg tMsg) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", param.glblCmpyCd.getValue());
        params.put("crCardTrxTp", getCrCardTrxTp(tMsg));
        params.put("firstTrxInfoNum", tMsg.dsContrPk.getValue());
        params.put("scdTrxInfoNum", tMsg.dsContrDtlPk.getValue());
        params.put("thirdTrxInfoNum", tMsg.dsContrBllgMtrPk.getValue());
        params.put("crCardAuthStsSaved", CR_CARD_AUTH_STS.SAVED);
        return (List<BigDecimal>) ssmBatchClient.queryObjectList("getCrCardTrxPk", params);
    }

    /**
     * getSellToCustCd
     * @param param NSZC046001PMsg
     * @param tMsg DS_CONTR_CR_CARD_POTMsg
     * @return String
     */
    public static String getSellToCustCd(NSZC046001PMsg param, DS_CONTR_CR_CARD_POTMsg tMsg) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", param.glblCmpyCd.getValue());
        params.put("dsContrPk", tMsg.dsContrPk.getValue());
        params.put("dsContrDtlPk", tMsg.dsContrDtlPk.getValue());
        params.put("dsContrDtlPk", tMsg.dsContrDtlPk.getValue());
        params.put("dsContrBllgMtrPk", tMsg.dsContrBllgMtrPk.getValue());

        String sellToCustCd = null;
        if (ZYPCommonFunc.hasValue(tMsg.dsContrPk) && ZYPCommonFunc.hasValue(tMsg.dsContrDtlPk) && ZYPCommonFunc.hasValue(tMsg.dsContrBllgMtrPk)) {
            sellToCustCd = (String) ssmBatchClient.queryObject("getSellToCustCdForMeter", params);
        } else if (ZYPCommonFunc.hasValue(tMsg.dsContrPk) && ZYPCommonFunc.hasValue(tMsg.dsContrDtlPk)) {
            sellToCustCd = (String) ssmBatchClient.queryObject("getSellToCustCdForDetail", params);
        } else if (ZYPCommonFunc.hasValue(tMsg.dsContrPk)) {
            sellToCustCd = (String) ssmBatchClient.queryObject("getSellToCustCdForHeader", params);
        }
        return sellToCustCd;
    }

    /**
     * getCrCardTrx
     * @param glblCmpyCd String
     * @param crCardTrxPk BigDecimal
     * @return DS_CR_CARDTMsg
     */
    public static CR_CARD_TRXTMsg getCrCardTrx(String glblCmpyCd, BigDecimal crCardTrxPk) {
        CR_CARD_TRXTMsg prmTMsg = new CR_CARD_TRXTMsg();
        setValue(prmTMsg.glblCmpyCd, glblCmpyCd);
        setValue(prmTMsg.crCardTrxPk, crCardTrxPk);
        return (CR_CARD_TRXTMsg) S21ApiTBLAccessor.findByKey(prmTMsg);
    }
    // add end 2016/09/02 CSA Defect#11243

    // START 2017/07/26 M.Kidokoro [QC#18122, ADD]
    /**
     * getDsContrCtrlSts
     * @param glblCmpyCd String
     * @param validFlg String
     * @return String[]
     */
    public static String[] getDsContrCtrlSts(String glblCmpyCd, String validFlg) {
        String[] stsList = null;
        DS_CONTR_CTRL_STSTMsg tInMsg = new DS_CONTR_CTRL_STSTMsg();
        tInMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tInMsg.setSQLID("001");
        DS_CONTR_CTRL_STSTMsgArray tOutMsgArray = (DS_CONTR_CTRL_STSTMsgArray) S21ApiTBLAccessor.findByCondition(tInMsg);

        if (tOutMsgArray == null || tOutMsgArray.getValidCount() == 0) {
            return stsList;
        }

        ArrayList<String> stsArrayList = new ArrayList<String>();
        DS_CONTR_CTRL_STSTMsg tOutMsg;
        String stsCd = "";

        for (int i = 0; i < tOutMsgArray.length(); i++) {
            tOutMsg = tOutMsgArray.no(i);
            stsCd = tOutMsg.dsContrCtrlStsCd.getValue();
            if (validFlg.equals(tOutMsg.contrTrmnAvalFlg.getValue())) {
                if (validFlg.equals(ZYPConstant.FLG_ON_Y)) {
                    stsArrayList.add(stsCd);
                } else {
                    if (stsCd.equals(DS_CONTR_DTL_STS.CANCELLED) || stsCd.equals(DS_CONTR_DTL_STS.TERMINATED) || stsCd.equals(DS_CONTR_DTL_STS.EXPIRED)) {
                        stsArrayList.add(stsCd);
                    }
                }
            }
        }
        stsList = (String[]) stsArrayList.toArray(new String[] {});

        return stsList;
    }
    // START 2017/07/26 M.Kidokoro [QC#18122, ADD]
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/06   Fujitsu         S.Takami        Update          S21_NA#18459
 * 2017/11/16   Fujitsu         T.Aoi           Update          S21_NA#22620
 * 2019/04/08   Fujitsu         K.Ishizuka      Update          S21_NA#31111
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CARR_SVC_LVLTMsg;
import business.db.CCYTMsg;
import business.db.CONFIG_CATGTMsg;
import business.db.CONFIG_TPTMsg;
import business.db.CPO_ORD_TPTMsg;
import business.db.CR_REBILTMsg;
import business.db.CR_REBIL_RSN_CATGTMsg;
import business.db.CTAC_TPTMsg;
import business.db.CTRYTMsg;
import business.db.CUST_MDSE_XREFTMsg;
import business.db.CUST_MDSE_XREFTMsgArray;
import business.db.DS_MDLTMsg;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_LINE_PROC_DFNTMsg;
import business.db.DS_ORD_RSNTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.DS_ORD_TP_PROC_DFNTMsg;
import business.db.DS_PMT_METHTMsg;
import business.db.FRT_CHRG_METHTMsg;
import business.db.FRT_CHRG_TOTMsg;
import business.db.FRT_CONDTMsg;
import business.db.HDD_RMVTMsg;
import business.db.HRCH_EFF_LVL_TPTMsg;
import business.db.LEASE_PMT_FREQTMsg;
import business.db.LEASE_PRCH_OPTTMsg;
import business.db.LEASE_TERMTMsg;
import business.db.LINE_BIZ_ROLE_TPTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_FRT_CLSTMsg;
import business.db.MDSE_PRC_GRPTMsg;
import business.db.MDSE_TP_VAL_SETTMsgArray;
import business.db.ORD_LINE_SRCTMsg;
import business.db.ORD_LOG_TPTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.db.PKG_UOMTMsg;
import business.db.PMT_TERM_CASH_DISCTMsg;
import business.db.PRC_CATGTMsg;
import business.db.PRE_PMT_TPTMsg;
import business.db.RTL_SWHTMsg;
import business.db.RTL_WHTMsg;
import business.db.RTRN_RSNTMsg;
import business.db.SHPG_SVC_LVLTMsg;
import business.db.SPCL_HDLG_TPTMsg;
import business.db.SPLY_QUOTE_SRC_TPTMsg;
import business.db.SPLY_TPTMsg;
import business.db.STK_STSTMsg;
import business.db.STTMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.THIRD_PTY_DSP_TPTMsg;
import business.db.TOCTMsg;
import business.parts.NLZC215001PMsg;
import business.parts.NMZC610001PMsg;
import business.parts.NMZC610001_RelatedBillShipListPMsg;
import business.parts.NWZC180001PMsg;

import com.canon.cusa.s21.api.NLZ.NLZC215001.NLZC215001;
import com.canon.cusa.s21.api.NLZ.NLZC215001.Constant.NLZC215001Constant;
import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.api.NWZ.NWZC180001.NWZC180001;
import com.canon.cusa.s21.api.NWZ.NWZC180001.constant.NWZC180001Constant;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.cache.ConfigTpCache;
import com.canon.cusa.s21.common.NWX.NWXC150001.cache.FindCondition;
import com.canon.cusa.s21.common.NWX.NWXC150001.cache.MdseTpValSetCache;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_REBIL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ACCT_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BIZ_AREA;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_PMT_METH;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HRCH_EFF_LVL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_USG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MDL_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * NWXC150001DsCheck
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/06/14   Fujitsu         S.Takami        Update          S21_NA#18623
 * 2017/07/03   Fujitsu         R.Nakamura      Update          S21_NA#19709
 * 2017/11/02   Fujitsu         T.Aoi           Update          S21_NA#20146(Sol#92)
 * 2017/12/04   Fujitsu         M.Yamada        Update          S21_NA#21471
 * 2017/12/26   Fujitsu         S.Takami        Update          S21_NA#22986
 * 2018/01/11   Fujitsu         S.Takami        Update          S21_NA#23329
 * 2018/02/13   Fujitsu         M.Ohno          Update          S21_NA#22717
 * 2018/02/26   Fujitsu         Hd.Sugawara     Update          QC#22967
 * 2018/03/28   Fujitsu         A.Kosai         Update          S21_NA#24853
 * 2018/05/18   Fujitsu         H.Tomimatsu     Update          S21_NA#25192
 * 2018/05/20   Fujitsu         S.Takami        Update          S21_NA#25604
 * 2018/06/14   Fujitsu         M.Yamada        Update          QC#26650
 * 2018/06/14   Fujitus         K.Ishizuka      Update          S21_NA#24294
 * 2018/06/18   Fujitsu         H.Kumagai       Update          QC#14307
 * 2018/06/26   Fujitus         H.Nagashima     Update          S21_NA#23726
 * 2018/07/09   Fujitsu         S.Takami        Update          S21_NA#26895
 * 2018/07/10   Fujitsu         T.Noguchi       Update          S21_NA#26661,26713
 * 2018/07/13   Fujitsu         S.Takami        Update          S21_NA#27228
 * 2018/07/30   Fujitsu         K.Ishizuka      Update          S21_NA#26181
 * 2018/07/31   Fujitsu         M.Yamada        Update          S21_NA#26410
 * 2018/09/25   Fujitsu         K.Ishizuka      Update          S21_NA#28482
 * 2018/11/09   Fujitsu         H.Kumagai       Update          QC#28683
 * 2018/12/10   Fujitsu         M.Ohno          Update          S21_NA#29315
 * 2019/01/08   Fujitsu         K.Kato          Update          QC#29241
 * 2019/12/14   Fujitsu         Mz.Takahashi    Update          QC#53588
 * 2022/08/18   Hitachi         H.Watanabe      Update          QC#60255
 * 2023/12/06   CITS            K.Ikeda         Update          QC#61281
 * </pre>
 */
public final class NWXC150001DsCheck {

    /**
     * <pre>
     * checkAddlCarrAcctNumRelation
     * @param glblCmpyCd        glblCmpyCd
     * @param addlCarrAcctNum   addlCarrAcctNum
     * @param frtCondCd         frtCondCd
     * @return boolean if error then return true.
     * </pre>
     */
    public static boolean checkAddlCarrAcctNumRelation(String glblCmpyCd, String addlCarrAcctNum, String frtCondCd) {
        String val = ZYPCodeDataUtil.getVarCharConstValue("CLT_FRT_COND", glblCmpyCd);
        // if (!ZYPCommonFunc.hasValue(val)){
        // return true;
        // }
        List<String> collectList = S21StringUtil.toList(val);
        if (collectList.contains(frtCondCd)) {
            return (!ZYPCommonFunc.hasValue(addlCarrAcctNum));
        }
        return false;

    }

    // Add Start 2016/09/06 S21_NA#11595
    /**
     * <pre>
     * checkCarrSvcLevelRelation
     * @param glblCmpyCd        glblCmpyCd
     * @param carrSvcLeveCd     carrSvcLeveCd
     * @param frtCondCd         frtCondCd
     * @return boolean if error then return true.
     * </pre>
     */
    public static boolean checkCarrSvcLevelRelation(String glblCmpyCd, String carrSvcLeveCd, String frtCondCd) {
        String val = ZYPCodeDataUtil.getVarCharConstValue("CLT_FRT_COND", glblCmpyCd);

        List<String> collectList = S21StringUtil.toList(val);
        if (collectList.contains(frtCondCd)) {
            return (!ZYPCommonFunc.hasValue(carrSvcLeveCd));
        }
        return false;

    }

    // Add End 2016/09/06 S21_NA#11595

    /**
     * checkFrtCondSvcLvlRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param frtCondCd frtCondCd
     * @param addShpgSvcLvlCd addShpgSvcLvlCd
     * @param carrSvcLvlCd carrSvcLvlCd
     * @return boolean if error then return true.
     */
    public static boolean checkFrtCondSvcLvlRelation(String glblCmpyCd, String slsDt, String dsOrdTpCd, String frtCondCd, String addShpgSvcLvlCd, String carrSvcLvlCd, String dsAcctNum, String locNum) {
        // 2018/12/10 S21_NA#29315 Add Start
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
        tMsg = (DS_ORD_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        String dsBizAreaCd = getDsBizArea(glblCmpyCd, tMsg.dsOrdCatgCd.getValue(), dsOrdTpCd, null);
        String lineBizTpCd = getLineBizTpCd(glblCmpyCd, slsDt, dsOrdTpCd);
        // 2018/12/10 S21_NA#29315 Add End
        // 2018/12/10 S21_NA#29315 Add Mod
//        Integer cnt = NWXC150001Query.getInstance().checkFrtCondSvcLvlRelation(glblCmpyCd, slsDt, dsOrdTpCd, frtCondCd, addShpgSvcLvlCd, carrSvcLvlCd);
        Integer cnt = NWXC150001Query.getInstance().checkFrtCondSvcLvlRelation(glblCmpyCd, slsDt, dsOrdTpCd, frtCondCd, addShpgSvcLvlCd, carrSvcLvlCd, dsBizAreaCd, dsAcctNum, locNum, lineBizTpCd);
        // 2018/12/10 S21_NA#29315 Add End
        return (cnt == 0);
    }

    /**
     * checkShpgSvcLvlRelation
     * @param glblCmpyCd glblCmpyCd
     * @param addShpgSvcLvlCd addShpgSvcLvlCd
     * @param carrSvcLvlCd carrSvcLvlCd
     * @return boolean if error then return true.
     */
    public static boolean checkCarrSvcLvlAndShpgSvcLvlRelation(String glblCmpyCd, String addShpgSvcLvlCd, String carrSvcLvlCd) {
        if (!ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            return false;
        }

        Integer cnt = NWXC150001Query.getInstance().checkCarrSvcLvlAndShpgSvcLvlRelation(glblCmpyCd, addShpgSvcLvlCd, carrSvcLvlCd);
        return (cnt == 0);
    }

    /**
     * checkFrtTermRelation
     * @param glblCmpyCd glblCmpyCd
     * @param frtCondCd frtCondCd
     * @param addFrtChrgToCd addFrtChrgToCd
     * @param addFrtChrgMethCd addFrtChrgMethCd
     * @return boolean if error then return true.
     */
    public static boolean checkFrtTermRelation(//
            String glblCmpyCd //
            , String frtCondCd //
            , String addFrtChrgToCd //
            , String addFrtChrgMethCd) {
        if (!ZYPCommonFunc.hasValue(frtCondCd) //
                || !ZYPCommonFunc.hasValue(addFrtChrgToCd) //
                || !ZYPCommonFunc.hasValue(addFrtChrgMethCd)) {
            return false;
        }
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, frtCondCd);

        tMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return true;
        } else if (!tMsg.frtChrgMethCd.getValue().equals(addFrtChrgMethCd)) {
            return true;
        } else if (!tMsg.frtChrgToCd.getValue().equals(addFrtChrgToCd)) {
            return true;
        }
        return false;
    }

    /**
     * checkSoldTo
     * @param glblCmpyCd glblCmpyCd
     * @param soldToCustLocCd soldToCustLocCd
     * @param sellToCustCd sellToCustCd
     * @return boolean if error then return true.
     */
    public static boolean checkSoldToRalation(String glblCmpyCd, String soldToCustLocCd, String sellToCustCd) {
        Integer cnt = NWXC150001Query.getInstance().checkSoldToRelation(glblCmpyCd, soldToCustLocCd, sellToCustCd);
        return (cnt == 0);
    }

    /**
     * checkShipTo
     * @param glblCmpyCd glblCmpyCd
     * @param addShipToCustCd addShipToCustCd
     * @param shipToCustAcctCd shipToCustAcctCd
     * @return boolean if error then return true.
     */
    public static boolean checkShipToRalation(String glblCmpyCd, String addShipToCustCd, String shipToCustAcctCd) {
        Integer cnt = NWXC150001Query.getInstance().checkShipToRelation(glblCmpyCd, addShipToCustCd, shipToCustAcctCd);
        return (cnt == 0);
    }

    /**
     * checkBillTo
     * @param glblCmpyCd glblCmpyCd
     * @param billToCustCd billToCustCd
     * @param sellToCustCd sellToCustCd
     * @return boolean if error then return true.
     */
    public static boolean checkBillToRalation(String glblCmpyCd, String billToCustCd, String sellToCustCd) {
        Integer cnt = NWXC150001Query.getInstance().checkBillToRelation(glblCmpyCd, billToCustCd, sellToCustCd);
        return (cnt == 0);
    }

    /**
     * checkDsOrdRsn
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param dsOrdRsngCd dsOrdRsngCd
     * @return boolean if error then return true.
     */
    public static boolean checkCpoOrdTpAndDsOrdRsnRelation(String glblCmpyCd, String slsDt, String dsOrdTpCd, String dsOrdRsngCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdRsngCd)) {
            return false;
        }
        Integer cnt = NWXC150001Query.getInstance().checkCpoOrdTpAndDsOrdRsnRelation(glblCmpyCd, slsDt, dsOrdTpCd, dsOrdRsngCd);
        return (cnt == 0);
    }

    /**
     * checkDsOrdTp
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdTpCd dsOrdTpCd
     * @param dsOrdCatgCd dsOrdCatgCd
     * @return boolean if error then return true.
     */
    public static boolean checkDsOrdTpAndDsOrdCatgRelation(String glblCmpyCd, String dsOrdTpCd, String dsOrdCatgCd) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return true;
        }
        if (!tMsg.dsOrdCatgCd.getValue().equals(dsOrdCatgCd)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * @param glblCmpyCd        glblCmpyCd
     * @param thirdPtyDspTpCd   thirdPtyDspTpCd
     * @return boolean if no records found then return false.
     * </pre>
     */
    public static boolean existsThirdPtyDspTp(String glblCmpyCd, String thirdPtyDspTpCd) {
        if (!ZYPCommonFunc.hasValue(thirdPtyDspTpCd)) {
            return true;
        }
        THIRD_PTY_DSP_TPTMsg tMsg = new THIRD_PTY_DSP_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.thirdPtyDspTpCd, thirdPtyDspTpCd);
        tMsg = (THIRD_PTY_DSP_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * <pre>
     * @param glblCmpyCd glblCmpyCd
     * @param rtrnRsnCd  rtrnRsnCd
     * @return boolean if no records found then return false.
     * </pre>
     */
    public static boolean existsRtrnRsn(String glblCmpyCd, String rtrnRsnCd) {
        if (!ZYPCommonFunc.hasValue(rtrnRsnCd)) {
            return true;
        }
        RTRN_RSNTMsg tMsg = new RTRN_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtrnRsnCd, rtrnRsnCd);
        tMsg = (RTRN_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * <pre>
     * @param glblCmpyCd glblCmpyCd
     * @param hddRmvCd   hddRmvCd
     * @return boolean if no records found then return false.
     * </pre>
     */
    public static boolean existsHddRmv(String glblCmpyCd, String hddRmvCd) {
        if (!ZYPCommonFunc.hasValue(hddRmvCd)) {
            return true;
        }
        HDD_RMVTMsg tMsg = new HDD_RMVTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.hddRmvCd, hddRmvCd);
        tMsg = (HDD_RMVTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsCtry
     * @param glblCmpyCd glblCmpyCd
     * @param ctryCd ctryCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCtry(String glblCmpyCd, String ctryCd) {
        if (!ZYPCommonFunc.hasValue(ctryCd)) {
            return true;
        }
        CTRYTMsg tMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctryCd, ctryCd);
        tMsg = (CTRYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsPost
     * @param glblCmpyCd glblCmpyCd
     * @param postCd postCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsPost(String glblCmpyCd, String postCd) {
        if (!ZYPCommonFunc.hasValue(postCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getPostCnt(glblCmpyCd, postCd);
        return (cnt > 0);
    }

    /**
     * existsState
     * @param glblCmpyCd glblCmpyCd
     * @param stCd stCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsState(String glblCmpyCd, String stCd) {
        if (!ZYPCommonFunc.hasValue(stCd)) {
            return true;
        }
        STTMsg tMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, stCd);
        tMsg = (STTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsShipToLocation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param shipToCustCd shipToCustCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsShipToLocation(String glblCmpyCd, String slsDt, String shipToCustCd) {
        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getShipToCustCnt(glblCmpyCd, slsDt, shipToCustCd);
        return (cnt > 0);
    }

    /**
     * existsBillToLocation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param billToCustCd billToCustCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsBillToLocation(String glblCmpyCd, String slsDt, String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getBillToCustCnt(glblCmpyCd, slsDt, billToCustCd);
        return (cnt > 0);
    }

    /**
     * existsMdlId
     * @param glblCmpyCd glblCmpyCd
     * @param mdlId mdlId
     * @return boolean if no records found then return false.
     */
    public static boolean existsMdlId(String glblCmpyCd, BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId) //
                || BigDecimal.ZERO.compareTo(mdlId) == 0) {
            return true;
        }
        DS_MDLTMsg tMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdlId, mdlId);
        tMsg = (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSvcConfigMstr
     * @param glblCmpyCd glblCmpyCd
     * @param svcConfigMstrPk svcConfigMstrPk
     * @return boolean if no records found then return false.
     */
    public static boolean existsSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) //
                || BigDecimal.ZERO.compareTo(svcConfigMstrPk) == 0) {
            return true;
        }
        SVC_CONFIG_MSTRTMsg tMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.svcConfigMstrPk, svcConfigMstrPk);
        tMsg = (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsConfigTp
     * @param glblCmpyCd glblCmpyCd
     * @param configTpCd configTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsConfigTp(String glblCmpyCd, String configTpCd) {
        if (!ZYPCommonFunc.hasValue(configTpCd)) {
            return true;
        }
        CONFIG_TPTMsg tMsg = new CONFIG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.configTpCd, configTpCd);
        tMsg = (CONFIG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsConfigCatg
     * @param glblCmpyCd glblCmpyCd
     * @param configCatgCd configCatgCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsConfigCatg(String glblCmpyCd, String configCatgCd) {
        if (!ZYPCommonFunc.hasValue(configCatgCd)) {
            return true;
        }
        CONFIG_CATGTMsg tMsg = new CONFIG_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.configCatgCd, configCatgCd);
        tMsg = (CONFIG_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSbstItem
     * @param glblCmpyCd glblCmpyCd
     * @param sbstMdseCd sbstMdseCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSbstItem(String glblCmpyCd, String sbstMdseCd) {
        if (!ZYPCommonFunc.hasValue(sbstMdseCd)) {
            return true;
        }

        // QC1698/3235
        MDSETMsg tMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, sbstMdseCd);

        // MDSETMsg tMsg = new MDSETMsg();
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, sbstMdseCd);
        // tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return (RGTN_STS.READY_FOR_ORDER_TAKING.equals(tMsg.rgtnStsCd.getValue()));
    }

    /**
     * existsBilgAttrCustAcct
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsAcctCd dsAcctCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsBilgAttrCustAcct(String glblCmpyCd, String slsDt, String dsAcctCd) {
        if (!ZYPCommonFunc.hasValue(dsAcctCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getDsAcctCnt(glblCmpyCd, slsDt, dsAcctCd);
        return (cnt > 0);
    }

    /**
     * existsSplyTp
     * @param glblCmpyCd glblCmpyCd
     * @param splyTpCd splyTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSplyTp(String glblCmpyCd, String splyTpCd) {
        if (!ZYPCommonFunc.hasValue(splyTpCd)) {
            return true;
        }
        SPLY_TPTMsg tMsg = new SPLY_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.splyTpCd, splyTpCd);
        tMsg = (SPLY_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsCrRebil
     * @param glblCmpyCd glblCmpyCd
     * @param crRebilCd crRebilCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCrRebil(String glblCmpyCd, String crRebilCd) {
        if (!ZYPCommonFunc.hasValue(crRebilCd)) {
            return true;
        }
        CR_REBILTMsg tMsg = new CR_REBILTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crRebilCd, crRebilCd);
        tMsg = (CR_REBILTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsRtlSubWh
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param rtlWhCd rtlWhCd
     * @param rtlSwhCd rtlSwhCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsRtlSubWh(String glblCmpyCd, String slsDt, String rtlWhCd, String rtlSwhCd) {
        if (!ZYPCommonFunc.hasValue(rtlSwhCd)) {
            return true;
        }
        boolean ret = true;
        RTL_SWHTMsg tMsg = new RTL_SWHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlSwhCd, rtlSwhCd);
        tMsg = (RTL_SWHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            ret = false;
        } else if (ZYPDateUtil.compare(slsDt, tMsg.effFromDt.getValue()) < 0) {
            ret = false;
        } else if (ZYPDateUtil.compare(slsDt, tMsg.effThruDt.getValue()) > 0) {
            ret = false;
        }
        return ret;
    }

    /**
     * existsRtlWh
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param rtlWhCd rtlWhCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsRtlWh(String glblCmpyCd, String slsDt, String rtlWhCd) {
        if (!ZYPCommonFunc.hasValue(rtlWhCd)) {
            return true;
        }
        boolean ret = true;
        RTL_WHTMsg tMsg = new RTL_WHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCd, rtlWhCd);
        tMsg = (RTL_WHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            ret = false;
        } else if (ZYPDateUtil.compare(slsDt, tMsg.effFromDt.getValue()) < 0) {
            ret = false;
        } else if (ZYPDateUtil.compare(slsDt, tMsg.effThruDt.getValue()) > 0) {
            ret = false;
        }
        return ret;
    }

    /**
     * existsOrdLineSrc
     * @param glblCmpyCd glblCmpyCd
     * @param ordLineSrcCd ordLineSrcCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsOrdLineSrc(String glblCmpyCd, String ordLineSrcCd) {
        if (!ZYPCommonFunc.hasValue(ordLineSrcCd)) {
            return true;
        }
        ORD_LINE_SRCTMsg tMsg = new ORD_LINE_SRCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordLineSrcCd, ordLineSrcCd);
        tMsg = (ORD_LINE_SRCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsDsOrdLineCatg
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdLineCatgCd dsOrdLineCatgCd
     * @param dsOrdLineDrctnCd DS_ORD_LINE_DRCTN_CD
     * @return boolean if no records found then return false.
     */
    public static boolean existsDsOrdLineCatg(String glblCmpyCd, String dsOrdLineCatgCd, String dsOrdLineDrctnCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdLineCatgCd)) {
            return true;
        }
        DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
        tMsg = (DS_ORD_LINE_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }

        return (dsOrdLineDrctnCd.equals(tMsg.dsOrdLineDrctnCd.getValue()));
    }

    /**
     * existsCrRebilRsnCatg
     * @param glblCmpyCd glblCmpyCd
     * @param crRebilRsnCatgCd crRebilRsnCatgCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCrRebilRsnCatg(String glblCmpyCd, String crRebilRsnCatgCd) {
        if (!ZYPCommonFunc.hasValue(crRebilRsnCatgCd)) {
            return true;
        }
        CR_REBIL_RSN_CATGTMsg tMsg = new CR_REBIL_RSN_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.crRebilRsnCatgCd, crRebilRsnCatgCd);
        tMsg = (CR_REBIL_RSN_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsPrePmtTp
     * @param glblCmpyCd glblCmpyCd
     * @param prePmtTpCd prePmtTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsPrePmtTp(String glblCmpyCd, String prePmtTpCd) {
        if (!ZYPCommonFunc.hasValue(prePmtTpCd)) {
            return true;
        }
        PRE_PMT_TPTMsg tMsg = new PRE_PMT_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prePmtTpCd, prePmtTpCd);
        tMsg = (PRE_PMT_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSpclHdlgTp
     * @param glblCmpyCd glblCmpyCd
     * @param spclHdlgTpCd spclHdlgTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSpclHdlgTp(String glblCmpyCd, String spclHdlgTpCd) {
        if (!ZYPCommonFunc.hasValue(spclHdlgTpCd)) {
            return true;
        }
        SPCL_HDLG_TPTMsg tMsg = new SPCL_HDLG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.spclHdlgTpCd, spclHdlgTpCd);
        tMsg = (SPCL_HDLG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsCarrSvcLvl
     * @param glblCmpyCd glblCmpyCd
     * @param carrSvcLvlCd carrSvcLvlCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCarrSvcLvl(String glblCmpyCd, String carrSvcLvlCd) {
        if (!ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            return true;
        }
        CARR_SVC_LVLTMsg tMsg = new CARR_SVC_LVLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.carrSvcLvlCd, carrSvcLvlCd);
        tMsg = (CARR_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsFrtCond
     * @param glblCmpyCd glblCmpyCd
     * @param frtCondCd frtCondCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsFrtCond(String glblCmpyCd, String frtCondCd) {
        if (!ZYPCommonFunc.hasValue(frtCondCd)) {
            return true;
        }
        FRT_CONDTMsg tMsg = new FRT_CONDTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtCondCd, frtCondCd);
        tMsg = (FRT_CONDTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsOrdLogTp
     * @param glblCmpyCd glblCmpyCd
     * @param ordLogTpCd ordLogTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsOrdLogTp(String glblCmpyCd, String ordLogTpCd) {
        if (!ZYPCommonFunc.hasValue(ordLogTpCd)) {
            return true;
        }
        ORD_LOG_TPTMsg tMsg = new ORD_LOG_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordLogTpCd, ordLogTpCd);
        tMsg = (ORD_LOG_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsLeasePmtFreq
     * @param glblCmpyCd glblCmpyCd
     * @param leasePmtFreqCd leasePmtFreqCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsLeasePmtFreq(String glblCmpyCd, String leasePmtFreqCd) {
        if (!ZYPCommonFunc.hasValue(leasePmtFreqCd)) {
            return true;
        }
        LEASE_PMT_FREQTMsg tMsg = new LEASE_PMT_FREQTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.leasePmtFreqCd, leasePmtFreqCd);
        tMsg = (LEASE_PMT_FREQTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsLeaseTerm
     * @param glblCmpyCd glblCmpyCd
     * @param leaseTermCd leaseTermCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsLeaseTerm(String glblCmpyCd, String leaseTermCd) {
        if (!ZYPCommonFunc.hasValue(leaseTermCd)) {
            return true;
        }
        LEASE_TERMTMsg tMsg = new LEASE_TERMTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.leaseTermCd, leaseTermCd);
        tMsg = (LEASE_TERMTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsLeasePrchOpt
     * @param glblCmpyCd glblCmpyCd
     * @param leasePrchOptCd leasePrchOptCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsLeasePrchOpt(String glblCmpyCd, String leasePrchOptCd) {
        if (!ZYPCommonFunc.hasValue(leasePrchOptCd)) {
            return true;
        }
        LEASE_PRCH_OPTTMsg tMsg = new LEASE_PRCH_OPTTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.leasePrchOptCd, leasePrchOptCd);
        tMsg = (LEASE_PRCH_OPTTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsAssnPgm
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param assnPgmCd assnPgmCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsAssnPgm(String glblCmpyCd, String slsDt, String assnPgmCd) {
        if (!ZYPCommonFunc.hasValue(assnPgmCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getAssnPgmCnt(glblCmpyCd, slsDt, assnPgmCd);
        return (cnt > 0);
    }

    /**
     * existsFlPrcList
     * @param glblCmpyCd glblCmpyCd
     * @param flPrcListCd flPrcListCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsFlPrcList(String glblCmpyCd, String flPrcListCd) {
        return (existsPrcCatg(glblCmpyCd, flPrcListCd));
    }

    /**
     * existsPrcCatg
     * @param glblCmpyCd glblCmpyCd
     * @param prcCatgCd prcCatgCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsPrcCatg(String glblCmpyCd, String prcCatgCd) {
        if (!ZYPCommonFunc.hasValue(prcCatgCd)) {
            return true;
        }
        PRC_CATGTMsg tMsg = new PRC_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.prcCatgCd, prcCatgCd);
        tMsg = (PRC_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSalesRep
     * @param glblCmpyCd glblCmpyCd
     * @param slsRepCd slsRepCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSalesRep(String glblCmpyCd, String slsRepCd) {
        if (!ZYPCommonFunc.hasValue(slsRepCd)) {
            return true;
        }
        TOCTMsg tMsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.tocCd, slsRepCd);
        tMsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSoldToLocation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param soldToLocCd soldToLocCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSoldToLocation(String glblCmpyCd, String slsDt, String soldToLocCd) {
        if (!ZYPCommonFunc.hasValue(soldToLocCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getSoldToCnt(glblCmpyCd, slsDt, soldToLocCd);
        return (cnt > 0);
    }

    /**
     * existsAccount
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param acctCd acctCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsAccount(String glblCmpyCd, String slsDt, String acctCd) {
        if (!ZYPCommonFunc.hasValue(acctCd)) {
            return true;
        }
        Integer cnt = NWXC150001Query.getInstance().getAcctCnt(glblCmpyCd, slsDt, acctCd);
        return (cnt > 0);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdCatgCd dsOrdCatgCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsDsOrdCatg(String glblCmpyCd, String dsOrdCatgCd) {
        if (!ZYPCommonFunc.hasValue(dsOrdCatgCd)) {
            return true;
        }
        DS_ORD_CATGTMsg tMsg = new DS_ORD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, dsOrdCatgCd);
        tMsg = (DS_ORD_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    // 2016/01/19 S21_NA UT#48 Add Start
    /**
     * checkCpoOrdTp
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdTpCd cpoOrdTpCd
     * @return boolean if error then return true.
     */
    public static boolean checkCpoOrdTp(String glblCmpyCd, String cpoOrdTpCd) {

        if (!ZYPCommonFunc.hasValue(cpoOrdTpCd)) {
            return true;
        }

        CPO_ORD_TPTMsg tMsg = new CPO_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.cpoOrdTpCd, cpoOrdTpCd);

        tMsg = (CPO_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsDsPmtMeth
     * @param glblCmpyCd glblCmpyCd
     * @param dsPmtMethCd dsPmtMethCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsDsPmtMeth(String glblCmpyCd, String dsPmtMethCd) {
        if (!ZYPCommonFunc.hasValue(dsPmtMethCd)) {
            return true;
        }
        DS_PMT_METHTMsg tMsg = new DS_PMT_METHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsPmtMethCd, dsPmtMethCd);
        tMsg = (DS_PMT_METHTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        return (tMsg != null);
    }

    /**
     * existsPrePmtTp
     * @param glblCmpyCd glblCmpyCd
     * @param pmtTermCashDisc Payment Term Cash Discount Code
     * @return boolean if no records found then return false.
     */
    public static boolean existsPmtTermCashDisc(String glblCmpyCd, String pmtTermCashDisc) {
        if (!ZYPCommonFunc.hasValue(pmtTermCashDisc)) {
            return true;
        }
        PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, pmtTermCashDisc);
        tMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsShpgSvcLvl
     * @param glblCmpyCd glblCmpyCd
     * @param shpgSvcLvlCd shpgSvcLvlCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsShpgSvcLvl(String glblCmpyCd, String shpgSvcLvlCd) {
        if (!ZYPCommonFunc.hasValue(shpgSvcLvlCd)) {
            return true;
        }
        SHPG_SVC_LVLTMsg tMsg = new SHPG_SVC_LVLTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.shpgSvcLvlCd, shpgSvcLvlCd);
        tMsg = (SHPG_SVC_LVLTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsFrtChrgTo
     * @param glblCmpyCd glblCmpyCd
     * @param frtChrgToCd frtChrgToCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsFrtChrgTo(String glblCmpyCd, String frtChrgToCd) {
        if (!ZYPCommonFunc.hasValue(frtChrgToCd)) {
            return true;
        }
        FRT_CHRG_TOTMsg tMsg = new FRT_CHRG_TOTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtChrgToCd, frtChrgToCd);
        tMsg = (FRT_CHRG_TOTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsFrtChrgMeth
     * @param glblCmpyCd glblCmpyCd
     * @param frtChrgMeth frtChrgMeth
     * @return boolean if no records found then return false.
     */
    public static boolean existsFrtChrgMeth(String glblCmpyCd, String frtChrgMeth) {
        if (!ZYPCommonFunc.hasValue(frtChrgMeth)) {
            return true;
        }
        FRT_CHRG_METHTMsg tMsg = new FRT_CHRG_METHTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.frtChrgMethCd, frtChrgMeth);
        tMsg = (FRT_CHRG_METHTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSplyQuoteCatg
     * @param glblCmpyCd glblCmpyCd
     * @param splyQuoteCatgCd Supply Quote Category Code
     * @return boolean if no records found then return false.
     */
    public static boolean existsSplyQuoteCatg(String glblCmpyCd, String splyQuoteCatgCd) {
        if (!ZYPCommonFunc.hasValue(splyQuoteCatgCd)) {
            return true;
        }
        DS_ORD_CATGTMsg tMsg = new DS_ORD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdCatgCd, splyQuoteCatgCd);
        tMsg = (DS_ORD_CATGTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsSplyTp
     * @param glblCmpyCd glblCmpyCd
     * @param splyQuoteSrcTpCd splyQuoteSrcTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsSplyQuoteSrcTp(String glblCmpyCd, String splyQuoteSrcTpCd) {
        if (!ZYPCommonFunc.hasValue(splyQuoteSrcTpCd)) {
            return true;
        }
        SPLY_QUOTE_SRC_TPTMsg tMsg = new SPLY_QUOTE_SRC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.splyQuoteSrcTpCd, splyQuoteSrcTpCd);
        tMsg = (SPLY_QUOTE_SRC_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsDsOrdTp
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdTp dsOrdTp
     * @return boolean if no records found then return false.
     */
    public static boolean existsDsOrdTp(String glblCmpyCd, String dsOrdTp) {
        if (!ZYPCommonFunc.hasValue(dsOrdTp)) {
            return true;
        }
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTp);
        tMsg = (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsDsOrdRsn
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdRsn dsOrdRsn
     * @return boolean if no records found then return false.
     */
    public static boolean existsDsOrdRsn(String glblCmpyCd, String dsOrdRsn) {
        if (!ZYPCommonFunc.hasValue(dsOrdRsn)) {
            return true;
        }
        DS_ORD_RSNTMsg tMsg = new DS_ORD_RSNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdRsnCd, dsOrdRsn);
        tMsg = (DS_ORD_RSNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsPkgUom
     * @param glblCmpyCd glblCmpyCd
     * @param pkgUomCd pkgUomCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsPkgUom(String glblCmpyCd, String pkgUomCd) {
        if (!ZYPCommonFunc.hasValue(pkgUomCd)) {
            return true;
        }
        PKG_UOMTMsg tMsg = new PKG_UOMTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pkgUomCd, pkgUomCd);
        tMsg = (PKG_UOMTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsCcy
     * @param glblCmpyCd glblCmpyCd
     * @param ccyCd ccyCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCcy(String glblCmpyCd, String ccyCd) {
        if (!ZYPCommonFunc.hasValue(ccyCd)) {
            return true;
        }
        CCYTMsg tMsg = new CCYTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ccyCd, ccyCd);
        tMsg = (CCYTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsStkSts
     * @param glblCmpyCd glblCmpyCd
     * @param stkStsCd stkStsCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsStkSts(String glblCmpyCd, String stkStsCd) {
        if (!ZYPCommonFunc.hasValue(stkStsCd)) {
            return true;
        }
        STK_STSTMsg tMsg = new STK_STSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stkStsCd, stkStsCd);
        tMsg = (STK_STSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsMdseFrtCls
     * @param glblCmpyCd glblCmpyCd
     * @param mdseFrtClsCd mdseFrtClsCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsMdseFrtCls(String glblCmpyCd, String mdseFrtClsCd) {
        if (!ZYPCommonFunc.hasValue(mdseFrtClsCd)) {
            return true;
        }
        MDSE_FRT_CLSTMsg tMsg = new MDSE_FRT_CLSTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseFrtClsCd, mdseFrtClsCd);
        tMsg = (MDSE_FRT_CLSTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsMdsePrcGrp
     * @param glblCmpyCd glblCmpyCd
     * @param mdsePrcGrpCd mdsePrcGrpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsMdsePrcGrp(String glblCmpyCd, String mdsePrcGrpCd) {
        if (!ZYPCommonFunc.hasValue(mdsePrcGrpCd)) {
            return true;
        }
        MDSE_PRC_GRPTMsg tMsg = new MDSE_PRC_GRPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdsePrcGrpCd, mdsePrcGrpCd);
        tMsg = (MDSE_PRC_GRPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsLineBizRoleTp
     * @param glblCmpyCd glblCmpyCd
     * @param lineBizRoleTpCd lineBizRoleTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsLineBizRoleTp(String glblCmpyCd, String lineBizRoleTpCd) {
        if (!ZYPCommonFunc.hasValue(lineBizRoleTpCd)) {
            return true;
        }
        LINE_BIZ_ROLE_TPTMsg tMsg = new LINE_BIZ_ROLE_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.lineBizRoleTpCd, lineBizRoleTpCd);
        tMsg = (LINE_BIZ_ROLE_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    /**
     * existsCtacTp
     * @param glblCmpyCd glblCmpyCd
     * @param ctacTpCd ctacTpCd
     * @return boolean if no records found then return false.
     */
    public static boolean existsCtacTp(String glblCmpyCd, String ctacTpCd) {
        if (!ZYPCommonFunc.hasValue(ctacTpCd)) {
            return true;
        }
        CTAC_TPTMsg tMsg = new CTAC_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ctacTpCd, ctacTpCd);
        tMsg = (CTAC_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        return (tMsg != null);
    }

    // 2016/01/19 S21_NA UT#48 Add End

    /**
     * checkPmntMethRelation
     * @param dsPmtMethCd ds Payment Method Code
     * @param dsCrCardPk dsCrCardPk
     * @return boolean if error then return true.
     */
    public static boolean checkPmntMethRelation(String dsPmtMethCd, BigDecimal dsCrCardPk) {
        if (DS_PMT_METH.CREDIT_CARD.equals(dsPmtMethCd)) {
            if (!ZYPCommonFunc.hasValue(dsCrCardPk)) {
                return true;
            }
        }
        if (ZYPCommonFunc.hasValue(dsCrCardPk)) {
            if (!DS_PMT_METH.CREDIT_CARD.equals(dsPmtMethCd)) {
                return true;
            }
        }
        return false;
    }

    // 2016/08/30 S21_NA#11547 Add Start
    /**
     * checkPmntMethAndTermRelation
     * @param glblCmpyCd Global Company Code
     * @param dsPmtMethCd ds Payment Method Code
     * @param dsPmtTermCd ds Payment Term Code
     * @return boolean if error then return true.
     */
    public static boolean checkPmntMethAndTermRelation(String glblCmpyCd, String dsPmtMethCd, String dsPmtTermCd) {

        if (!ZYPCommonFunc.hasValue(dsPmtTermCd)) {
            return true;
        }

        PMT_TERM_CASH_DISCTMsg tMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.pmtTermCashDiscCd, dsPmtTermCd);
        tMsg = (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return true;
        }
        // 2023/12/06 QC#61281 K.Ikeda START
        if (!DS_PMT_METH.CREDIT_CARD.equals(dsPmtTermCd) && DS_PMT_METH.NO_CREDIT_CARD.equals(dsPmtMethCd)) {
            return true;
        }
        
        if (DS_PMT_METH.CREDIT_CARD.equals(dsPmtTermCd) && DS_PMT_METH.NO_CREDIT_CARD.equals(dsPmtMethCd)) {
            return false;
        }
        // 2023/12/06 QC#61281 K.Ikeda END

        if (DS_PMT_METH.CREDIT_CARD.equals(dsPmtMethCd)) {
            if (!ZYPConstant.FLG_ON_Y.equals(tMsg.pmtCcFlg.getValue())) {
                return true;
            }
            
            
        }
        if (ZYPConstant.FLG_ON_Y.equals(tMsg.pmtCcFlg.getValue())) {
            if (!DS_PMT_METH.CREDIT_CARD.equals(dsPmtMethCd)) {
                return true;
            }
        }
        return false;
    }

    // 2016/08/30 S21_NA#11547 Add End

    /**
     * checkCsmpRelation
     * @param glblCmpyCd glblCmpyCd
     * @param csmpContrNum csmpContrNum
     * @param dsAcctNum dsAcctNum
     * @return boolean if error then return true.
     */
    public static boolean checkCsmpRelation(String glblCmpyCd, String csmpContrNum, String dsAcctNum) {
        if (!ZYPCommonFunc.hasValue(csmpContrNum)) {
            return false;
        }
        Integer cnt = NWXC150001Query.getInstance().checkCsmpRelation(glblCmpyCd, csmpContrNum, dsAcctNum);
        return (cnt == 0);
    }

    /**
     * checkCsaNumRelation
     * @param glblCmpyCd glblCmpyCd
     * @param dlrRefNum dlrRefNum
     * @param dsAcctNum dsAcctNum
     * @return boolean if error then return true.
     */
    public static boolean checkCsaNumRelation(String glblCmpyCd, String dlrRefNum, String dsAcctNum) {
        if (!ZYPCommonFunc.hasValue(dlrRefNum)) {
            return false;
        }
        Integer cnt = NWXC150001Query.getInstance().checkCsaNumRelation(glblCmpyCd, dlrRefNum, dsAcctNum);
        return (cnt == 0);
    }

    /**
     * checkSalesRepRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsRepCd slsRepCd
     * @return boolean if error then return true.
     */
    public static boolean checkSalesRepRelation(String glblCmpyCd, String slsRepCd) {
        Integer cnt = NWXC150001Query.getInstance().checkSalesRepRelation(glblCmpyCd, slsRepCd);
        return (cnt == 0);
    }

    /**
     * <pre>
     * checkPrcCatgRelation
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @param slsDt         slsDt
     * @param prcCatgCd     prcCatgCd
     * @return boolean if error then return true.
     * </pre>
     */
    public static boolean checkPrcCatgRelation(String glblCmpyCd, String dsOrdTpCd, String slsDt, String prcCatgCd) {
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return true;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.actvFlg.getValue())) {
            return true;
        }
        if ((ZYPDateUtil.compare(tMsg.effFromDt.getValue(), slsDt) > 0)) {
            return true;
        }

        // S21_NA#8297
        if (ZYPCommonFunc.hasValue(tMsg.effThruDt) && (ZYPDateUtil.compare(tMsg.effThruDt.getValue(), slsDt) < 0)) {
            return true;
        }

        if (prcCatgCd.equals(tMsg.defPrcCatgCd.getValue())) {
            return false;
        }
        return true;
    }

    /**
     * checkFlPrcListRelation
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdTpCd dsOrdTpCd
     * @param slsDt slsDt
     * @param flPrcListCd flPrcListCd
     * @return boolean if error then return true.
     */
    public static boolean checkFlPrcListRelation(String glblCmpyCd, String dsOrdTpCd, String slsDt, String flPrcListCd) {
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return true;
        }
        if (!ZYPConstant.FLG_ON_Y.equals(tMsg.actvFlg.getValue())) {
            return true;
        }
        if ((ZYPDateUtil.compare(tMsg.effFromDt.getValue(), slsDt) > 0)) {
            return true;
        }
        // S21_NA#8297
        if (ZYPCommonFunc.hasValue(tMsg.effThruDt) && (ZYPDateUtil.compare(tMsg.effThruDt.getValue(), slsDt) < 0)) {
            return true;
        }

        if (flPrcListCd.equals(tMsg.defPrcCatgCd.getValue())) {
            return false;
        }
        return true;
    }

    // Add Start 2018/02/26 QC#22967
    /**
     * <pre>
     * callCustInfoGetApiBillTo
     * @param glblCmpyCd global company code
     * @param billToCustCd bill to customer code
     * @param acctNum account number
     * @param onBatchType ONBATCH_TYPE (Online or Batch)
     * @return NMZC610001PMsg
     * </pre>
     */
    public static NMZC610001PMsg callCustInfoGetApiBillTo(String glblCmpyCd, String billToCustCd, String acctNum, ONBATCH_TYPE onBatchType) {
        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, billToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I2, acctNum);

        NMZC610001 nmzc610001 = new NMZC610001();
        nmzc610001.execute(custInfoGetApiMsg, onBatchType);

        return custInfoGetApiMsg;
    }
    // Add End 2018/02/26 QC#22967

    /**
     * <pre>
     * callCustInfoGetApi
     * @param glblCmpyCd global company code
     * @param billToCustCd bill to customer code
     * @param sellToCustCd sell to cosutomer code (sold to customer)
     * @param shipToCustAcctCd ship to customer account code
     * @param onBatchType ONBATCH_TYPE (Online or Batch)
     * @return bool if error then return true.
     * </pre>
     */
//    public static NMZC610001PMsg callCustInfoGetApi(String glblCmpyCd, String billToCustCd, String shipToCustCd, String sellToCustCd, ONBATCH_TYPE onBatchType) {
    public static NMZC610001PMsg callCustInfoGetApi(String glblCmpyCd, String billToCustCd, String sellToCustCd, String shipToCustAcctCd, ONBATCH_TYPE onBatchType) {
        NMZC610001PMsg custInfoGetApiMsg = new NMZC610001PMsg();

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_ELIGIBLE_CHECK);

        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.billToCustCd, billToCustCd);
        // 2017/06/06 S21_NA#18459 Mod Start
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.shipToCustCd, shipToCustCd);
//        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I2, sellToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I1, sellToCustCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiMsg.dsAcctNum_I2, shipToCustAcctCd);
        // 2017/06/06 S21_NA#18459 Mod End

        NMZC610001 nmzc610001 = new NMZC610001();

        nmzc610001.execute(custInfoGetApiMsg, onBatchType);

        return custInfoGetApiMsg;
    }

    /**
     * checkConfigIdEssential
     * @param glblCmpyCd glblCmpyCd
     * @param configTpCd configTpCd
     * @param svcConfigMstrPk svcConfigMstrPk
     * @return boolean if error then return true.
     */
    public static boolean checkConfigIdEssential(String glblCmpyCd, String configTpCd, BigDecimal svcConfigMstrPk) {
        // if ((CONFIG_TP.ADD_TO_CONFIG.equals(configTpCd) ||
        // CONFIG_TP.EXISTING.equals(configTpCd)) //
        // && !(ZYPCommonFunc.hasValue(svcConfigMstrPk))) {
        // return true;
        // }
        // S21_NA#955
        // Out bound N Y Y
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) && matchConfigTp(glblCmpyCd, configTpCd, CONFIG_CATG.OUTBOUND, false, true, true)) {
            return true;
        }
        // In bound N Y N
        if (!ZYPCommonFunc.hasValue(svcConfigMstrPk) && matchConfigTp(glblCmpyCd, configTpCd, CONFIG_CATG.INBOUND, false, true, false)) {
            return true;
        }
        return false;
    }

    /**
     * checkConfigShiptoLocation
     * @param glblCmpyCd glblCmpyCd
     * @param configTpCd configTpCd
     * @param svcConfigMstrPk svcConfigMstrPk
     * @param shipToLocCd shipToLocCd
     * @return boolean if error then return true.
     */
    public static boolean checkConfigShiptoLocation(String glblCmpyCd, String configTpCd, BigDecimal svcConfigMstrPk, String shipToLocCd) {
        // if (!CONFIG_TP.ADD_TO_CONFIG.equals(configTpCd)) {
        // return false;
        // }
        // S21_NA#955
        // Out bound Not N Y N
        if (!matchConfigTp(glblCmpyCd, configTpCd, CONFIG_CATG.OUTBOUND, false, true, false)) {
            return false;
        }

        String istlLocCd = NWXC150001Query.getInstance().checkConfigShiptoLocation(glblCmpyCd, svcConfigMstrPk);
        return !(shipToLocCd.equals(istlLocCd));
    }

    /**
     * checkDetailMdseRelation
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @param custMdseCd custMdseCd
     * @param sellToAcctNum sellToAcctNum
     * @return boolean if error then return true.
     */
    public static boolean checkDetailMdseRelation(String glblCmpyCd, String mdseCd, String custMdseCd, String sellToAcctNum) {
        if (!ZYPCommonFunc.hasValue(custMdseCd)) {
            return false;
        }
        // S21_NA#9761 2016/09/27 MOD START
        // 2016/09/20 S21_NA#11655 Add Start
        // MDSETMsg mdseTMsg = new MDSETMsg();
        // ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        // mdseTMsg = (MDSETMsg)
        // S21CacheTBLAccessor.findByKey(mdseTMsg);
        // if (mdseTMsg == null) {
        // ORD_TAKE_MDSETMsg ordTakeMdseTmsg = new
        // ORD_TAKE_MDSETMsg();
        // ZYPEZDItemValueSetter.setValue(ordTakeMdseTmsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(ordTakeMdseTmsg.ordTakeMdseCd,
        // mdseCd);
        // ordTakeMdseTmsg = (ORD_TAKE_MDSETMsg)
        // S21CacheTBLAccessor.findByKey(ordTakeMdseTmsg);
        // if (ordTakeMdseTmsg != null) {
        // mdseCd = ordTakeMdseTmsg.mdseCd.getValue();
        // }
        // }
        // 2016/09/20 S21_NA#11655 Add End

        CUST_MDSE_XREFTMsg tMsg = new CUST_MDSE_XREFTMsg();
        // ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.custMdseCd,
        // custMdseCd);
        // ZYPEZDItemValueSetter.setValue(tMsg.dsAcctNum,
        // sellToAcctNum);
        // tMsg = (CUST_MDSE_XREFTMsg)
        // S21CacheTBLAccessor.findByKey(tMsg);
        tMsg.setSQLID("003");
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setConditionValue("mdseCd01", mdseCd + "%");
        tMsg.setConditionValue("custMdseCd01", custMdseCd);
        tMsg.setConditionValue("dsAcctNum01", sellToAcctNum);
        CUST_MDSE_XREFTMsgArray tMsgArray = (CUST_MDSE_XREFTMsgArray) EZDTBLAccessor.findByCondition(tMsg);
        if (tMsgArray == null || tMsgArray.getValidCount() == 0) {
            return true;
        }
        for (int i = 0; i < tMsgArray.getValidCount(); i++) {
            CUST_MDSE_XREFTMsg custMdseXref = tMsgArray.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(custMdseXref.custMdseXrefEnblFlg.getValue())) {
                return false;
            }
        }
        // return
        // !(ZYPConstant.FLG_ON_Y.equals(tMsg.custMdseXrefEnblFlg.getValue()));
        return true;
        // S21_NA#9761 2016/09/27 MOD END
    }

    /**
     * checkDetailLineCatgRelation
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdLineCatgCd dsOrdLineCatgCd
     * @param dsOrdTpCd dsOrdTpCd
     * @return boolean if error then return true.
     */
    public static boolean checkDetailLineCatgRelation(String glblCmpyCd, String dsOrdLineCatgCd, String dsOrdTpCd) {
        DS_ORD_LINE_PROC_DFNTMsg tMsg = new DS_ORD_LINE_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdLineCatgCd, dsOrdLineCatgCd);
        tMsg = (DS_ORD_LINE_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return true;
        }
        return !(ZYPConstant.FLG_ON_Y.equals(tMsg.actvFlg.getValue()));
    }

    /**
     * checkRetailWhRelation
     * @param glblCmpyCd glblCmpyCd
     * @param slsDt slsDt
     * @param dsOrdTpCd dsOrdTpCd
     * @param rtlWhCd rtlWhCd
     * @param rtlSwhCd rtlSwhCd
     * @return boolean if error then return true.
     */
    public static boolean checkRetailWhRelation(//
            String glblCmpyCd //
            , String slsDt //
            , String dsOrdTpCd //
            , String rtlWhCd //
            , String rtlSwhCd) {
        if (!ZYPCommonFunc.hasValue(rtlWhCd) || !ZYPCommonFunc.hasValue(rtlSwhCd)) {
            return false;
        }
        Integer cnt = NWXC150001Query.getInstance().checkRetailWhRelation(glblCmpyCd, slsDt, dsOrdTpCd, rtlWhCd, rtlSwhCd);
        return (cnt == 0);
    }

    /**
     * getSerNumInfo
     * @param glblCmpyCd glblCmpyCd
     * @param serNum serNum
     * @param mdseCd mdseCd
     * @return serial number information
     */
    public static Map<String, Object> getSerNumInfo(String glblCmpyCd, String serNum, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(serNum) && !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        return NWXC150001Query.getInstance().getSerNumInfo(glblCmpyCd, serNum, null, mdseCd);
    }

    // 2016/06/02 S21_NA#9273 Modify Start
    /**
     * getSerNumInfo
     * @param glblCmpyCd glblCmpyCd
     * @param serNum serNum
     * @param mdseCd mdseCd
     * @param svcMachMstrPk svcMachMstrPk
     * @return serial number information
     */
    public static Map<String, Object> getSerNumInfo(String glblCmpyCd, String serNum, String mdseCd, BigDecimal svcMachMstrPk) {
        if (!ZYPCommonFunc.hasValue(serNum) && !ZYPCommonFunc.hasValue(svcMachMstrPk) && !ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        return NWXC150001Query.getInstance().getSerNumInfo(glblCmpyCd, serNum, svcMachMstrPk, mdseCd);
    }

    // 2016/06/02 S21_NA#9273 Modify End

    /**
     * <pre>
     * @param svcMachUsgStsCd   svcMachUsgStsCd
     * @param svcMachMstrStsCd  svcMachMstrStsCd
     * @param allocFlg          allocFlg
     * @param cpoOrdNum         cpoOrdNum
     * @param cpoDtlLineNum     cpoDtlLineNum
     * @param cpoDtlLineSubNum  cpoDtlLineSubNum
     * @return  if error then return true.
     * </pre>
     */
    public static boolean checkSerNumExistingConfig(//
            String svcMachUsgStsCd //
            , String svcMachMstrStsCd //
            , String allocFlg //
            , String cpoOrdNum //
            , String cpoDtlLineNum //
            , String cpoDtlLineSubNum) {
        if (!(SVC_MACH_USG_STS.IN_INVENTORY.equals(svcMachUsgStsCd) //
        || SVC_MACH_USG_STS.RETURNED.equals(svcMachUsgStsCd))) {
            return true;
        }
        if (!SVC_MACH_MSTR_STS.CREATED.equals(svcMachMstrStsCd)) {
            return true;
        }
        return (ZYPConstant.FLG_ON_Y.equals(allocFlg));
    }

    /**
     * checkSerNumSvcConfigMstrPkRelation
     * @param prmConfigMstrPk prmConfigMstrPk
     * @param dbConfigMstrPk dbConfigMstrPk
     * @return boolean if error then return true.
     */
    public static boolean checkSerNumSvcConfigMstrPkRelation(BigDecimal prmConfigMstrPk, BigDecimal dbConfigMstrPk) {
        return (!ZYPCommonFunc.hasValue(dbConfigMstrPk) || prmConfigMstrPk.compareTo(dbConfigMstrPk) != 0); // 2016/07/27
        // S21_NA#12560
        // Modify
    }

    /**
     * checkSerNumRtlWhRelation
     * @param glblCmpyCd glblCmpyCd
     * @param rtlWhCd rtlWhCd
     * @param rtlSwhCd rtlSwhCd
     * @param curLocNum curLocNum
     * @return boolean if error then return true.
     */
    public static boolean checkSerNumRtlWhRelation(String glblCmpyCd, String rtlWhCd, String rtlSwhCd, String curLocNum) {
        Map<String, String> map = NWXC150001Query.getInstance().getRtlSwhInfo(glblCmpyCd, curLocNum);
        if (map == null) {
            return true;
        }

        if (rtlWhCd.equals((String) map.get("RTL_WH_CD")) //
                && rtlSwhCd.equals((String) map.get("RTL_SWH_CD"))) {
            return false;
        }
        return true;
    }

    /**
     * checkSbstRelation
     * @param glblCmpyCd glblCmpyCd
     * @param fromMdseCd fromMdseCd
     * @param toMdseCd toMdseCd
     * @return boolean if error then return true.
     */
    public static boolean checkSbstRelation(String glblCmpyCd, String fromMdseCd, String toMdseCd) {
        if (!ZYPCommonFunc.hasValue(fromMdseCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(toMdseCd)) {
            return false;
        }

        // 2016/03/05 S21_NA#1698 Mod Start
        // QC#1698/3235
        // String mdse8LenCd = fromMdseCd;
        //        
        // MDSETMsg mdseTMsg =
        // NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd,
        // mdse8LenCd);
        // MDSETMsg mdseTMsg =
        // NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd,
        // fromMdseCd);
        // if (mdseTMsg == null ||
        // !asList(RGTN_STS.READY_FOR_ORDER_TAKING,
        // RGTN_STS.TERMINATED).contains(mdseTMsg.rgtnStsCd.getValue()))
        // {
        // return true;
        // }
        // mdse8LenCd = mdseTMsg.mdseCd.getValue();
        // if (mdse8LenCd.length() > 8) {
        // mdse8LenCd = mdse8LenCd.substring(0, 8);
        // }
        //
        // String subMdse8LenCd = toMdseCd;
        // if (subMdse8LenCd.length() > 8) {
        // subMdse8LenCd = subMdse8LenCd.substring(0, 8);
        // }
        //
        // Integer cnt =
        // NWXC150001Query.getInstance().checkSbstRelation(glblCmpyCd,
        // mdse8LenCd, subMdse8LenCd);
        // 2016/07/02 S21_NA#1698,3235 Del Start
        // Integer cnt =
        // NWXC150001Query.getInstance().checkSbstRelation(glblCmpyCd,
        // fromMdseCd, toMdseCd);
        // 2016/03/05 S21_NA#1698 Mod End

        // return (cnt == 0);
        // 2016/07/02 S21_NA#1698,3235 Del End

        return (getSbstRelation(glblCmpyCd, fromMdseCd, toMdseCd).size() == 0);
    }

    // 2016/07/02 S21_NA#1698,3235 Add Start
    /**
     * getSbstRelation
     * @param glblCmpyCd glblCmpyCd
     * @param mdseCd mdseCd
     * @param sbstMdseCd sbstMdseCd
     * @return List<String> Substitute Mdse Cd List.
     */
    public static List<String> getSbstRelation(String glblCmpyCd, String mdseCd, String sbstMdseCd) {

        // 2017/06/14 S21_NA#18623 Add Start
        int ordTakeLen = getOrdTakeMdseLen();
        // 2017/06/14 S21_NA#18623 Add End

        List<String> retSbstRelList = new ArrayList<String>();

        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return retSbstRelList;
        }
        if (!ZYPCommonFunc.hasValue(sbstMdseCd)) {
            return retSbstRelList;
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
        if (mdseTMsg == null) {
            return retSbstRelList;
        }

        // 2017/06/14 S21_NA#18623 Add Start
        MDSETMsg sbstMdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, sbstMdseCd);
        if (sbstMdseTMsg == null) {
            return retSbstRelList;
        }
        // 2017/06/14 S21_NA#18623 Add End

        String mdse8LenCd = mdseTMsg.mdseCd.getValue();
        if (mdse8LenCd.length() > ordTakeLen) {
            mdse8LenCd = mdse8LenCd.substring(0, ordTakeLen);
        } else {
            mdse8LenCd = mdseTMsg.mdseCd.getValue();
        }
        // 2017/06/14 S21_NA#18623 Add Start
        String mdseFullCd = mdseTMsg.mdseCd.getValue();
        // 2017/06/14 S21_NA#18623 Add End

        // 2017/06/14 S21_NA#18623 Add Start
//        String sbstMdse8LenCd = sbstMdseCd;
//        if (sbstMdse8LenCd.length() > 8) {
//            sbstMdse8LenCd = sbstMdse8LenCd.substring(0, 8);
//        }
        String sbstMdse8LenCd = sbstMdseTMsg.mdseCd.getValue();
        if (sbstMdse8LenCd.length() > ordTakeLen) {
            sbstMdse8LenCd = sbstMdse8LenCd.substring(0, ordTakeLen);
        } else {
            sbstMdse8LenCd = sbstMdseTMsg.mdseCd.getValue();
        }
        String sbstMdseFullCd = sbstMdseTMsg.mdseCd.getValue();
        // 2017/06/14 S21_NA#18623 Add End

        // 2017/06/14 S21_NA#18623 Mod Start
//        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
//        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
//        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdse8LenCd);
//        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
//
//        if (tMsg == null) {
//            // Not Find In ORD_TAKE_MDSE
//            mdse8LenCd = mdseTMsg.mdseCd.getValue();
//            sbstMdse8LenCd = sbstMdseCd;
//        }

        boolean isSbstOrdTake = false;
        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, sbstMdse8LenCd);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg != null && S21StringUtil.isEquals(sbstMdse8LenCd, sbstMdseCd)) {
            isSbstOrdTake = true;
        }
        // 2017/06/14 S21_NA#18623 Mod End

        List< ? > sbstRelList = NWXC150001Query.getInstance().getSbstRelation(glblCmpyCd, mdse8LenCd, sbstMdse8LenCd, mdseFullCd, sbstMdseFullCd);

        String resultSbstMdse;
        // 2017/06/14 S21_NA#18623 Del Start
//        int sbstMdseCdLen = sbstMdseCd.length();
        // 2017/06/14 S21_NA#18623 Del End
        if (sbstRelList != null) {
            for (int i = 0; i < sbstRelList.size(); i++) {
                resultSbstMdse = (String) sbstRelList.get(i);

                // 2017/06/14 S21_NA#18623 Mod Start
//                // 2016/07/28 S21_NA#1698,3235
//                // if (!retSbstRelList.contains(resultSbstMdse)) {
//                if (!retSbstRelList.contains(resultSbstMdse) && resultSbstMdse.indexOf(sbstMdseCd) != -1) {
//                    if (tMsg == null || ((tMsg != null) && (sbstMdseCdLen == 8 || sbstMdseCdLen == 10))) {
//                        retSbstRelList.add(resultSbstMdse);
//                    }
//                }
                if (isSbstOrdTake) {
                    if (resultSbstMdse.length() > ordTakeLen) {
                        resultSbstMdse = resultSbstMdse.substring(0, ordTakeLen);
                    }
                    ORD_TAKE_MDSETMsg ordTkMdseTMsg = new ORD_TAKE_MDSETMsg();
                    ZYPEZDItemValueSetter.setValue(ordTkMdseTMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(ordTkMdseTMsg.ordTakeMdseCd, resultSbstMdse);
                    ordTkMdseTMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);
                    if (ordTkMdseTMsg != null) {
                        retSbstRelList.add(ordTkMdseTMsg.ordTakeMdseCd.getValue());
                    } else {
                        retSbstRelList.add(resultSbstMdse);
                    }
                } else {
                    retSbstRelList.add(resultSbstMdse);
                }
                // 2017/06/14 S21_NA#18623 Mod End
            }
        }

        return retSbstRelList;
    }

    // 2016/07/02 S21_NA#1698,3235 Add End

    // S21NA#4098 start
    /**
     * checkLineRefNum
     * @param glblCmpyCd glblCmpyCd
     * @param dplyLineRefNum dplyLineRefNum
     * @param configCatgCd configCatgCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean checkLineRefNum(String glblCmpyCd, String dplyLineRefNum, String configCatgCd, String cpoOrdNum) {

        String dsOrdPosnNum = null;
        BigDecimal dsCpoLineNum = null;
        BigDecimal dsCpoLineSubNum = null;

        if (!ZYPCommonFunc.hasValue(dplyLineRefNum)) {
            return false;
        }
        List<String> splitLineNum = S21StringUtil.toList(dplyLineRefNum, "\\.");
        if (splitLineNum.size() < 2) {
            return true;
        }

        dsOrdPosnNum = splitLineNum.get(0);
        dsCpoLineNum = new BigDecimal(splitLineNum.get(1));
        if (splitLineNum.size() == 3) {
            dsCpoLineSubNum = new BigDecimal(splitLineNum.get(2));
        }

        if (CONFIG_CATG.OUTBOUND.equals(configCatgCd)) {
            // Outbound
            String sts = NWXC150001Query.getInstance().getLineStatus(//
                    glblCmpyCd //
                    , cpoOrdNum //
                    , dsOrdPosnNum //
                    , dsCpoLineNum);

            if (sts == null) {
                return false;
            }
            if (ORD_LINE_STS.CANCELLED.equals(sts)) {
                return true;
            }
        } else {
            // Inbound
            String sts = NWXC150001Query.getInstance().getLineStatusForInbound(//
                    glblCmpyCd //
                    , cpoOrdNum //
                    , dsOrdPosnNum //
                    , dsCpoLineNum //
                    , dsCpoLineSubNum);

            if (sts == null) {
                return false;
            }
            if (RTRN_LINE_STS.CANCELLED.equals(sts)) {
                return true;
            }
        }
        return false;
    }

    // /**
    // * checkLineRefNum
    // * @param glblCmpyCd glblCmpyCd
    // * @param ordSrcRefLineNum ordSrcRefLineNum
    // * @param dsOrdPosnNum dsOrdPosnNum
    // * @param dsCpoLineNum dsCpoLineNum
    // * @param cpoOrdNum cpoOrdNum
    // * @return boolean if error then return true.
    // */
    // public static boolean checkLineRefNum(String glblCmpyCd, String
    // ordSrcRefLineNum, String dsOrdPosnNum, BigDecimal dsCpoLineNum,
    // String cpoOrdNum) {
    // if (!ZYPCommonFunc.hasValue(ordSrcRefLineNum)) {
    // return false;
    // }
    // List<String> sl = S21StringUtil.toList(ordSrcRefLineNum, ".");
    // if (sl.size() != 2) {
    // return true;
    // }
    // if (!sl.get(0).equals(dsOrdPosnNum)) {
    // return true;
    // }
    // if (!sl.get(1).equals(dsCpoLineNum.toString())) {
    // return true;
    // }
    // String sts = NWXC150001Query.getInstance().getLineStatus(//
    // glblCmpyCd //
    // , cpoOrdNum //
    // , dsOrdPosnNum //
    // , dsCpoLineNum);
    // if (sts == null) {
    // return true;
    // }
    // if (ORD_LINE_STS.CANCELLED.equals(sts)) {
    // return true;
    // }
    // return false;
    // }
    // S21NA#4098 end

    /**
     * checkDealWh
     * @param glblCmpyCd glblCmpyCd
     * @param rtlWhCd rtlWhCd
     * @param dsOrdLineCatgCd dsOrdLineCatgCd
     * @param invtyCtrlFlg invtyCtrlFlg
     * @return boolean if error then return true.
     */
    public static boolean checkDealWh(String glblCmpyCd, String rtlWhCd, String dsOrdLineCatgCd, String invtyCtrlFlg) {
        if (ZYPConstant.FLG_OFF_N.equals(invtyCtrlFlg)) {
            return false;
        }
        List<Map<String, String>> ml = NWXC150001Query.getInstance().getDealWhMapping(glblCmpyCd);
        if (ml == null) {
            return false;
        }

        boolean isError = false;
        for (Map<String, String> map : ml) {
            String wh = (String) map.get("SCD_BIZ_CTX_ATTRB_TXT");
            String dbLineCatgCd = (String) map.get("DS_ORD_LINE_CATG_CD");
            if (wh.equals(rtlWhCd)) {
                isError = true;
                if (dbLineCatgCd.equals(dsOrdLineCatgCd)) {
                    return false;
                }
            }
        }
        return isError;
    }

    /**
     * checkSvcConfigMstrPkEssential
     * @param value svcConfigMstrPk
     * @return boolean if error then return true.
     */
    public static boolean checkSvcConfigMstrPkEssential(BigDecimal value) {
        if (!ZYPCommonFunc.hasValue(value)) {
            return true;
        }
        return (BigDecimal.ZERO.compareTo(value) == 0);
    }

    /**
     * checkSerNumEssential
     * @param value serNum
     * @return boolean if error then return true.
     */
    public static boolean checkSerNumEssential(String value) {
        return (!ZYPCommonFunc.hasValue(value));
    }

    /**
     * checkBaseCmptFlg
     * @param flgList baseCmptFlgList
     * @return boolean if error then return true.
     */
    public static boolean checkBaseCmptFlg(List<String> flgList) {
        if (flgList == null) {
            return true;
        }
        return !flgList.contains(ZYPConstant.FLG_ON_Y);
    }

    /**
     * checkMachQty
     * @param coaMdseTpList coaMdseTpList
     * @param coaMdseTpCd coaMdseTpCd
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkMachQty(List<String> coaMdseTpList, String coaMdseTpCd, BigDecimal ordQty) {
        if (coaMdseTpList == null) {
            return false;
        }
        if (!coaMdseTpList.contains(coaMdseTpCd)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return !(BigDecimal.ONE.compareTo(ordQty) == 0);
    }

    // 2016/02/17 S21_NA#1703 Add Start
    /**
     * Check Qty For Serialized Item
     * @param glblCmpyCd NWZC150001PMsg
     * @param dsOrdCatgCd NWZC150001_APMsg
     * @param dsOrdTpCd DS Order Type Code (Order Reason)
     * @param dsOrdRsnCd DS Order Reason Code (Order Sub Reason)
     * @param mdseCd NWZC150001_APMsg
     * @param ordQty Order Qty
     * @return boolean if error then return true.
     * @return
     */
    // QC#14021
    // public static boolean chekcQtyForSerializedItem(String
    // glblCmpyCd, String dsOrdCatgCd, String mdseCd, int ordQty) {
    public static boolean chekcQtyForSerializedItem(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, String mdseCd, int ordQty) {

        // if (ordQty == 1 || !isRetailEquipOrder(glblCmpyCd,
        // dsOrdCatgCd)) {
        if (ordQty == 1 || !isRetailEquipOrder(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd)) {
            return false;
        }

        MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);

        if (mdseTMsg == null || ZYPConstant.FLG_OFF_N.equals(mdseTMsg.shpgSerTakeFlg.getValue())) {
            return false;
        }

        return true;
    }

    // 2016/02/17 S21_NA#1703 Add End

    /**
     * checkSerialQty
     * @param serNum serNum
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkSerialQty(String serNum, BigDecimal ordQty) {
        if (!ZYPCommonFunc.hasValue(serNum)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return !(BigDecimal.ONE.compareTo(ordQty) == 0);
    }

    /**
     * checkLicenseItemQty
     * @param thirdPtyVndDropFlg thirdPtyVndDropFlg
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkLicenseItemQty(String thirdPtyVndDropFlg, BigDecimal ordQty) {
        if (ZYPConstant.FLG_OFF_N.equals(thirdPtyVndDropFlg)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return !(BigDecimal.ONE.compareTo(ordQty) == 0);
    }

    /**
     * checkMinusQty
     * @param crRebilCd crRebilCd
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkMinusQty(String crRebilCd, BigDecimal ordQty) {
        if (!CR_REBIL.CREDIT.equals(crRebilCd)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return !(BigDecimal.ONE.compareTo(ordQty) > 0);
    }

    /**
     * checkMinOrdQty
     * @param minOrdQty minOrdQty
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkMinOrdQty(BigDecimal minOrdQty, BigDecimal ordQty) {
        if (!ZYPCommonFunc.hasValue(minOrdQty)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return (minOrdQty.compareTo(ordQty) > 0);
    }

    /**
     * checkMaxOrdQty
     * @param maxOrdQty maxOrdQty
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkMaxOrdQty(BigDecimal maxOrdQty, BigDecimal ordQty) {
        if (!ZYPCommonFunc.hasValue(maxOrdQty)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return (maxOrdQty.compareTo(ordQty) < 0);
    }

    /**
     * checkIncrOrdQty
     * @param incrOrdQty incrOrdQty
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkIncrOrdQty(BigDecimal incrOrdQty, BigDecimal ordQty) {
        if (!ZYPCommonFunc.hasValue(incrOrdQty)) {
            return false;
        }
        if (ordQty == null) {
            return true;
        }
        return ((ordQty.remainder(incrOrdQty)).compareTo(BigDecimal.ZERO) != 0);
    }

    /**
     * checkChangeQty
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @param cpoDtlLineNum cpoDtlLineNum
     * @param cpoDtlLineSubNum cpoDtlLineSubNum
     * @param ordQty ordQty
     * @return boolean if error then return true.
     */
    public static boolean checkChangeQty(//
            String glblCmpyCd //
            , String cpoOrdNum //
            , String cpoDtlLineNum //
            , String cpoDtlLineSubNum //
            , BigDecimal ordQty) {
        if (!ZYPCommonFunc.hasValue(ordQty)) {
            return true;
        }
        BigDecimal allcQty = NWXC150001Query.getInstance().getAllocQty(glblCmpyCd, cpoOrdNum, cpoDtlLineNum, cpoDtlLineSubNum);
        return (allcQty.compareTo(ordQty) > 0);
    }

    /**
     * checkOtherCsmpRelation
     * @param defPrcCatgCd defPrcCatgCd
     * @param csmpPrcListCd csmpPrcListCd
     * @param csmpPrcList csmpPrcList
     * @return boolean if error then return true.
     */
    public static boolean checkOtherCsmpRelation(String defPrcCatgCd, String csmpPrcListCd, List<String> csmpPrcList) {
        if (ZYPCommonFunc.hasValue(defPrcCatgCd)) {
            if (defPrcCatgCd.equals(csmpPrcListCd)) {
                return false;
            }
        }
        if (csmpPrcList == null || csmpPrcList.size() == 0) {
            return true;
        }
        return !(csmpPrcList.contains(csmpPrcListCd));
    }

    /**
     * checkDiChecked
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @param cpoUpdVrsnNum cpoUpdVrsnNum
     * @return boolean if error then return true.
     */
    public static boolean checkDiChecked(String glblCmpyCd, String cpoOrdNum, BigDecimal cpoUpdVrsnNum) {
        Integer cnt = NWXC150001Query.getInstance().checkDiChecked(glblCmpyCd, cpoOrdNum, cpoUpdVrsnNum);
        return (cnt == 0);
    }

    /**
     * checkAllLineCancelled
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean checkAllLineCancelled(String glblCmpyCd, String cpoOrdNum) {
        Integer cnt = NWXC150001Query.getInstance().getActiveLineCnt(glblCmpyCd, cpoOrdNum);
        return (cnt == 0);
    }

    /**
     * <pre>
     * getLineBizTpCd
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         slsDt
     * @param dsOrdTpgCd   dsOrdTpgCd
     * @return default LOB
     * </pre>
     */
    public static String getLineBizTpCd(String glblCmpyCd, String slsDt, String dsOrdTpgCd) {
        String lineBizTpCd = NWXC150001Query.getInstance().getLineBizTpCd(glblCmpyCd, slsDt, dsOrdTpgCd);
        if (lineBizTpCd == null) {
            return "";
        }
        return lineBizTpCd;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param billToCustCd  billToCustCd
     * @return String DefaultLocPmtTerm
     * </pre>
     */
    public static String getDefaultLocPmtTerm(String glblCmpyCd, String billToCustCd) {
        return NWXC150001Query.getInstance().getDefaultLocPmtTerm(glblCmpyCd, billToCustCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param shipToCustCd  shipToCustCd
     * @return String getDefaultAcctFromShipToCust
     * </pre>
     */
    public static String getDefaultAcctFromShipToCust(String glblCmpyCd, String shipToCustCd) {
        return NWXC150001Query.getInstance().getDefaultAcctFromShipToCust(glblCmpyCd, shipToCustCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param billToCustCd  billToCustCd
     * @return String getDefaultAcctFromBillToCust
     * </pre>
     */
    public static String getDefaultAcctFromBillToCust(String glblCmpyCd, String billToCustCd) {
        return NWXC150001Query.getInstance().getDefaultAcctFromBillToCust(glblCmpyCd, billToCustCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param billToCustCd  billToCustCd
     * @return String DefaultAcctPmtTerm
     * </pre>
     */
    public static String getDefaultAcctPmtTerm(String glblCmpyCd, String billToCustCd) {
        return NWXC150001Query.getInstance().getDefaultAcctPmtTerm(glblCmpyCd, billToCustCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdCatgCd   dsOrdCatgCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @return if easy pack order then return true.
     * </pre>
     */
    public static boolean isEzPackOrder(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Integer cnt = NWXC150001Query.getInstance().getEzPackCtxCnt(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * Check Order Retail Equipment Order or not
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdCatgCd   DS Order Category Code
     * @param dsOrdTpCd     DS Order Type COde (Order reason)
     * @param dsOrdRsnCd    DS Order Reason Code (Order Sub Reason)
     * @return if retail equip order then return true.
     * </pre>
     */
    // QC#14021
    // public static boolean isRetailEquipOrder(String glblCmpyCd,
    // String dsOrdCatgCd) {
    public static boolean isRetailEquipOrder(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Integer cnt = NWXC150001Query.getInstance().getRetailEquipOrderCtxCnt(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @return  CoaMdseTpCd
     * </pre>
     */
    public static String getCoaMdseTpCd(String glblCmpyCd) {
        return NWXC150001Query.getInstance().getCoaMdseTpCd(glblCmpyCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param prcCatgCd     prcCatgCd
     * @return  DefaultHddEraseRemovalFlg Map
     * </pre>
     */
    public static Map<String, String> getDefaultHddEraseRemovalFlg(String glblCmpyCd, String prcCatgCd) {
        return NWXC150001Query.getInstance().getDefaultHddEraseRemovalFlg(glblCmpyCd, prcCatgCd);
    }

    // QC#14593 2016/09/28 Add Start
    /**
     * <pre>
     * @param glblCmpyCd String
     * @param cpoOrdNum String
     * @param dsCpoConfigPk BigDecimal
     * @return String
     * </pre>
     */
    public static String getSvcIstlRuleFlg(String glblCmpyCd, String cpoOrdNum, BigDecimal dsCpoConfigPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum)) {
            return null;
        }
        String custIstlFlg = NWXC150001Query.getInstance().getSvcIstlRuleFlg(glblCmpyCd, cpoOrdNum, dsCpoConfigPk);
        if (!ZYPCommonFunc.hasValue(custIstlFlg)) {
            custIstlFlg = NWXC150001Query.getInstance().getSvcIstlRuleFlg(glblCmpyCd, cpoOrdNum, null);
        }
        return custIstlFlg;
    }

    // QC#14593 2016/09/28 Add End

    /**
     * <pre>
     * @param glblCmpyCd    prcCatgCd
     * @param mdlId         mdlId
     * @return  DefaultCustIstlFlg
     * </pre>
     */
    public static String getDefaultCustIstlFlg(String glblCmpyCd, BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return NWXC150001Query.getInstance().getDefaultCustIstlFlg(glblCmpyCd, mdlId);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param mdseCd        mdseCd
     * @return  DefaultConfigFlg
     * </pre>
     */
    public static String getDefaultConfigFlg(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return ZYPConstant.FLG_OFF_N;
        }
        return tMsg.configFlg.getValue();
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param mdseCd        mdseCd
     * @return  CoaMdseTp
     * </pre>
     */
    public static String getCoaMdseTp(String glblCmpyCd, String mdseCd) {
        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, mdseCd);
        tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return "";
        }
        return tMsg.coaMdseTpCd.getValue();
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param mdlId         mdlId
     * @return  DefaultShopItemFlg
     * </pre>
     */
    public static String getDefaultShopItemFlg(String glblCmpyCd, BigDecimal mdlId) {
        if (!ZYPCommonFunc.hasValue(mdlId)) {
            return ZYPConstant.FLG_OFF_N;
        }
        return NWXC150001Query.getInstance().getDefaultShopItemFlg(glblCmpyCd, mdlId);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @return  if machine master create then return true.
     * </pre>
     */
    public static boolean isMachMstrCrat(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return false;
        }
        return ZYPConstant.FLG_ON_Y.equals(tMsg.machMstrCratFlg.getValue());
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdCatgCd   dsOrdCatgCd
     * @param dsOrdTpCd     DS Order Type Code (Order Reason)
     * @return  isSvcExch
     * </pre>
     */
    // QC#14021
    // public static boolean isSvcExch(String glblCmpyCd, String
    // dsOrdCatgCd) {
    public static boolean isSvcExch(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        // QC#14021
        // Integer cnt =
        // NWXC150001Query.getInstance().getSrvExchCnt(glblCmpyCd,
        // dsOrdCatgCd);
        Integer cnt = NWXC150001Query.getInstance().getSrvExchCnt(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * @param glblCmpyCd        glblCmpyCd
     * @param svcConfigMstrPk   svcConfigMstrPk
     * @param svcMachMstrPk     svcMachMstrPk
     * @return  isExistsSvcConfigMstr
     * </pre>
     */
    public static boolean isExistsSvcConfigMstr(String glblCmpyCd, BigDecimal svcConfigMstrPk, BigDecimal svcMachMstrPk) {
        Integer cnt = NWXC150001Query.getInstance().getSrvConfigMstrCnt(glblCmpyCd, svcConfigMstrPk, svcMachMstrPk);
        return (cnt > 0);
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param svcMachMstrPk svcMachMstrPk
     * @param dsOrdCatgCd   dsOrdCatgCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @return  checkRtnMMOrdTpAndSvcExchRsnRelation
     * </pre>
     */
    public static boolean checkRtnMMOrdTpAndSvcExchRsnRelation(String glblCmpyCd, BigDecimal svcMachMstrPk, String dsOrdCatgCd, String dsOrdTpCd) {
        Map<String, String> map = NWXC150001Query.getInstance().getOrdCatgBizCtx(glblCmpyCd, svcMachMstrPk);
        if (map == null) {
            return true;
        }

        String dbDsOrdCatgCd = map.get("FIRST_BIZ_CTX_ATTRB_TXT");
        String dbDsOrdTpCd = map.get("SCD_BIZ_CTX_ATTRB_TXT");

        return !(dbDsOrdCatgCd.equals(dsOrdCatgCd) && dbDsOrdTpCd.equals(dsOrdTpCd));
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdCatgCd   dsOrdCatgCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @return  isEasyPack
     * </pre>
     */
    public static boolean isEasyPack(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        Integer cnt = NWXC150001Query.getInstance().getEasyPackCnt(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * @param glblCmpyCd        glblCmpyCd
     * @param slsDt             slsDt
     * @param billToCustAcctCd  billToCustAcctCd
     * @param billToCustCd      billToCustCd
     * @return  isSplyPgmContr
     * </pre>
     */
    public static boolean isSplyPgmContr(String glblCmpyCd, String slsDt, String billToCustAcctCd, String billToCustCd) {
        Integer cnt = NWXC150001Query.getInstance().getSplyPgmContrCnt(glblCmpyCd, slsDt, billToCustAcctCd, billToCustCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * convert from CPO Line Sub Number to DS CPO Line Sub Number.
     * @param cpoLineSubNum cpoLineSubNum
     * @return  DS CPO Line Number.
     * </pre>
     */
    public static BigDecimal convCpoLineSubNumToDs(String cpoLineSubNum) {
        if ("000".equals(cpoLineSubNum)) {
            return null;
        }
        if (cpoLineSubNum.matches("^[0-9]{3}")) {
            return new BigDecimal(cpoLineSubNum);
        }
        return null;
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param dsOrdTpCd     dsOrdTpCd
     * @return  DS_ORD_TP_PROC_DFN.LINE_BIZ_TP_CD
     * </pre>
     */
    public static String getLineBizTpCdFromDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);
        if (tMsg == null) {
            return null;
        }
        return tMsg.lineBizTpCd.getValue();
    }

    /**
     * <pre>
     * @param glblCmpyCd    glblCmpyCd
     * @param slsDt         Sales Date
     * @param dsOrdTpCd     dsOrdTpCd
     * @return  DS_ORD_TP_PROC_DFN.LINE_BIZ_TP_CD
     * </pre>
     */
    public static String getLineBizRoleTpCdForWriter(String glblCmpyCd, String slsDt, String dsOrdTpCd) {
        return NWXC150001Query.getInstance().getLineBizRoleTpCdForWriter(glblCmpyCd, slsDt, dsOrdTpCd);
    }

    /**
     * <pre>
     * @param glblCmpyCd glblCmpyCd
     * @param dsOrdTpCd DS Order Type Code
     * @return Order Type Code
     * </pre>
     */
    public static String getCpoOrdTpCdFromDsOrdTp(String glblCmpyCd, String dsOrdTpCd) {
        DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();

        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return null;
        }
        return tMsg.cpoOrdTpCd.getValue();
    }

    /**
     * <pre>
     * @param dsOrdPosnNum      dsOrdPosnNum
     * @param dsCpoLineNum      dsCpoLineNum
     * @param dsCpoLineSubNum   dsCpoLineSubNum
     * @return  edited line# dsOrdPosnNum.dsCpoLineNum.dsCpoLineSubNum or dsOrdPosnNum.dsCpoLineNum
     * </pre>
     */
    public static String editDtlLineNum(String dsOrdPosnNum, BigDecimal dsCpoLineNum, BigDecimal dsCpoLineSubNum) {

        if (!ZYPCommonFunc.hasValue(dsCpoLineNum)) {
            return "";
        }
        StringBuilder lineNum = new StringBuilder();
        concatWithSeparator(lineNum, dsOrdPosnNum, ".");
        concatWithSeparator(lineNum, dsCpoLineNum.toPlainString(), ".");
        if (ZYPCommonFunc.hasValue(dsCpoLineSubNum)) {
            concatWithSeparator(lineNum, dsCpoLineSubNum.toPlainString(), ".");
        }
        return lineNum.toString();
    }

    private static void concatWithSeparator(StringBuilder addressBuffer, String element, String separator) {

        if (!S21StringUtil.isEmpty(element)) {
            if (addressBuffer.length() > 0) {
                addressBuffer.append(separator);
            }
            addressBuffer.append(element);
        }
    }

    /**
     * <pre>
     * Match Configuration Type
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Configuration Type Code
     * @param configCatgCd Configuration Category Code
     * @param configNewFlg Configuration New Flag
     * @param configExstAtCustFlg Configuration Existing At Customer Flag
     * @param configExstWhFlg Configuration Existing Warehouse Flag
     * @return Match Configuration Type : true
     * </pre>
     */
    public static boolean matchConfigTp(String glblCmpyCd, String configTpCd, String configCatgCd, boolean configNewFlg, boolean configExstAtCustFlg, boolean configExstWhFlg) {

        // For Performance QC#8166 Mod Start
        // CONFIG_TPTMsg configTp = new CONFIG_TPTMsg();

        // ZYPEZDItemValueSetter.setValue(configTp.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(configTp.configTpCd,
        // configTpCd);
        // configTp = (CONFIG_TPTMsg)
        // S21FastTBLAccessor.findByKey(configTp);
        CONFIG_TPTMsg configTp = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);
        // For Performance QC#8166 Mod End

        if (configTp == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(configTp.configCatgCd.getValue(), configCatgCd)) {
            return false;
        }
        if (configNewFlg && S21StringUtil.isEquals(configTp.configNewFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        if (configExstAtCustFlg && S21StringUtil.isEquals(configTp.configExstAtCustFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        if (configExstWhFlg && S21StringUtil.isEquals(configTp.configExstWhFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    // 2016/10/27 S21_NA#14897 Add Start
    /**
     * <pre>
     * is require Base Component flag at the Configuration Type
     * Check Configuration Type Base Component Require flag
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Configuration Type Code
     * @return Configuration Type don't require Base Component Flag : true
     *          Configuration Type require Base Component Flag : false
     * </pre>
     */
    public static boolean isReqBaseCmptFlgAtConfigTp(String glblCmpyCd, String configTpCd) {

        CONFIG_TPTMsg configTp = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (configTp == null) {
            return false;
        }
        if (S21StringUtil.isEquals(configTp.baseCmptReqFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    // 2016/10/27 S21_NA#14897 Add End

    // 2016/01/13 S21_NA#2726 Add Start
    /**
     * <pre>
     * If svcConfigMstrPk is not null and configTpCd's CONFIG_NEW_FLG = 'Y', this method return true (means error)
     * @param glblCmpyCd Global Compnay Code
     * @param configTpCd Config Type Code
     * @param svcConfigMstrPk Service Configuration Master Primary Key
     * @return true: error false: no error
     * </pre>
     */
    public static boolean isErrorConfigTpConfigIdRalation(String glblCmpyCd, String configTpCd, BigDecimal svcConfigMstrPk) {

        // For Performance QC#8166 Mod Start
        // CONFIG_TPTMsg queryKey = new CONFIG_TPTMsg();
        // ZYPEZDItemValueSetter.setValue(queryKey.glblCmpyCd,
        // glblCmpyCd);
        // ZYPEZDItemValueSetter.setValue(queryKey.configTpCd,
        // configTpCd);

        // CONFIG_TPTMsg rsltTMsg = (CONFIG_TPTMsg)
        // S21ApiTBLAccessor.findByKey(queryKey);
        CONFIG_TPTMsg rsltTMsg = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);
        // For Performance QC#8166 Mod End

        String configNewFlg = rsltTMsg.configNewFlg.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(configNewFlg) && ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Check parameter Item is IB Trackable and not in directed Service Configuration.
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Merchandise Code
     * @param svcConfigMstrPk Service Configuration Master Primary Key
     * @return true: IB Trackable and not in Service Configuration (Error), false no error;
     * </pre>
     */
    public static boolean isIbInstallableAndNotInConfig(String glblCmpyCd, String mdseCd, BigDecimal svcConfigMstrPk) {
        Integer queryRslt = NWXC150001Query.getInstance().getIbTrackableNotInSvcConfig(glblCmpyCd, mdseCd, svcConfigMstrPk);
        boolean rslt = false;
        if (queryRslt.compareTo(Integer.valueOf(0)) != 0) {
            rslt = true;
        }
        return rslt;
    }

    // 2016/01/13 S21_NA#2726 Add End

    // 2016/01/25 S21_NA#3505 Add Start
    /**
     * <pre>
     * When the Add Config, check that IB is a Customer site.
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Service Configuration Master Primary Key
     * @return Machine master usage status code of Main machine.
     * </pre>
     */
    public static String getMachineUsageStatus(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        String machUsgStsCd = NWXC150001Query.getInstance().getMachUsgStatus(glblCmpyCd, svcConfigMstrPk);
        return machUsgStsCd;
    }

    // 2016/01/25 S21_NA#3505 Add End
    
    // 2018/09/25 S21_NA#28482 Add Start
    /**
     * <pre>
     * When the Add Config, check that IB is a Customer site.
     * @param glblCmpyCd Global Company Code
     * @param svcConfigMstrPk Service Configuration Master Primary Key
     * @return Machine master status code of Main machine.
     * </pre>
     */
    public static String getMachineMasterStatus(String glblCmpyCd, BigDecimal svcConfigMstrPk) {
        String machMstrStsCd = NWXC150001Query.getInstance().getMachMstrStatus(glblCmpyCd, svcConfigMstrPk);
        return machMstrStsCd;
    }
    // 2018/09/25 S21_NA#28482 Add End
    
    // 2018/05/18 S21_NA#25192 Add Start
    /**
     * <pre>
     * Check the Customer Order No is exist on service machine master when service exchange.
     * @param glblCmpyCd Global Company Code
     * @param cpoOrdNum cpoOrdNum
     * @param svcConfigMstrPk Service Configuration Master Primary Key
     * @return true: not exists. / False: exists Customer Order No as TRX_HDR_NUM on SVC_MACH_MSTR table.
     * </pre>
     */
    public static boolean getTrxHdrNumMatchCnt(String glblCmpyCd, String cpoOrdNum, BigDecimal svcConfigMstrPk){
        Integer cnt =  NWXC150001Query.getInstance().getTrxHdrNumMatchCnt(glblCmpyCd, cpoOrdNum, svcConfigMstrPk);
        if (cnt == null) { // QC#26650 add
            return true;
        }
        return (cnt == 0);
    }
    // 2018/05/18 S21_NA#25192 Add End

    // 2016/04/28 S21_NA#7631 Add Start
    /**
     * <pre>
     * Check specified Order Reason (DS_ORD_TP_CD) requires DI Check or not.
     * @param glblCmpyCd Global Company Code
     * @param dsOrdTpCd Order Reason (DS_ORD_TP_CD)
     * @return true: need DI check false: no need DI check.
     * </pre>
     */
    public static boolean isNecessaryDICheck(String glblCmpyCd, String dsOrdTpCd) {

        DS_ORD_TP_PROC_DFNTMsg tMsg = new DS_ORD_TP_PROC_DFNTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.dsOrdTpCd, dsOrdTpCd);

        tMsg = (DS_ORD_TP_PROC_DFNTMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (null == tMsg) {
            return true;
        }
        return S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, tMsg.diChkNodeUsgFlg.getValue());
    }

    // 2016/04/28 S21_NA#7631 Add End

    // 2016/05/20 S21_NA#5335 Add Start
    /**
     * checkAllLineCancelled
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean checkAllRmaLineCancelled(String glblCmpyCd, String cpoOrdNum) {
        Integer cnt = NWXC150001Query.getInstance().getActiveRmaLineCnt(glblCmpyCd, cpoOrdNum);
        return (cnt == 0);
    }

    // 2016/05/20 S21_NA#5335 Add End

    // 2016/06/07 S21_NA#9277 Add Start
    /**
     * <pre>
     * Match Configuration Type for Expand Component
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Configuration Type Code
     * @param configCatgCd Configuration Category Code
     * @param xpndConfigFlg Expand Config Flag
     * @return Match Configuration Type : true
     * </pre>
     */
    public static boolean isExpandCmptMatchConfigTp(String glblCmpyCd, String configTpCd, String configCatgCd, boolean xpndConfigFlg) {

        CONFIG_TPTMsg rsltTMsg = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (rsltTMsg == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(rsltTMsg.configCatgCd.getValue(), configCatgCd)) {
            return false;
        }
        if (xpndConfigFlg && S21StringUtil.isEquals(rsltTMsg.xpndConfigFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    // 2016/06/07 S21_NA#9277 Add End

    // 2016/11/08 S21_NA#7749 Add Start
    /**
     * <pre>
     * Check specified Config is IB Qty Forced or not.
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Configuration Type Code
     * @param configCatgCd Configuration Category Code
     * @return Match Configuration Type : true
     * </pre>
     */
    public static boolean isIBQtyFrceConfigTp(String glblCmpyCd, String configTpCd, String configCatgCd) {

        CONFIG_TPTMsg rsltTMsg = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (rsltTMsg == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(rsltTMsg.configCatgCd.getValue(), configCatgCd)) {
            return false;
        }
        if (S21StringUtil.isEquals(rsltTMsg.istlBaseQtyFrceFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Check specified Config is All Component Required or not.
     * @param glblCmpyCd Global Company Code
     * @param configTpCd Configuration Type Code
     * @param configCatgCd Configuration Category Code
     * @return Match Configuration Type : true
     * </pre>
     */
    public static boolean isAllCmptReqConfigTp(String glblCmpyCd, String configTpCd, String configCatgCd) {

        CONFIG_TPTMsg rsltTMsg = ConfigTpCache.getInstance().getTMsgByKey(glblCmpyCd, configTpCd);

        if (rsltTMsg == null) {
            return false;
        }
        if (!S21StringUtil.isEquals(rsltTMsg.configCatgCd.getValue(), configCatgCd)) {
            return false;
        }
        if (S21StringUtil.isEquals(rsltTMsg.allCmptReqFlg.getValue(), ZYPConstant.FLG_ON_Y)) {
            return true;
        }
        return false;
    }

    /**
     * <pre>
     * Check Qty For IB Trackable Item
     * @param glblCmpyCd   Global Company Code
     * @param dsOrdCatgCd  DS Order Category Code
     * @param dsOrdTpCd    DS Order Type Code (Order Reason)
     * @param dsOrdRsnCd   DS Order Reason Code (Order Sub Reason)
     * @param mdseCd       Merchandise Code
     * @param ordQty       Order Quantity
     * @return boolean if error then return true.
     * </pre>
     */
    public static boolean chekcQtyForIBCtrlItem(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, String mdseCd, int ordQty) {

        if (ordQty == 1) {
            return false;
        }

        if (!isRetailEquipOrder(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd)) {
            return false;
        }

        MDSETMsg tMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.mdseCd, getMdse8(glblCmpyCd, mdseCd));
        tMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null || ZYPConstant.FLG_OFF_N.equals(tMsg.instlBaseCtrlFlg.getValue())) {
            return false;
        }

        return true;
    }

    /**
     * convert mdse cd length 8 to 10.
     * @param glblCmpyCd Global Company Code
     * @param mdseCd Item Code (Merchandise Code)
     * @return boolean if error then return true.
     */
    public static String getMdse8(String glblCmpyCd, String mdseCd) {
        // 2017/06/14 S21_NA#18623 Add Start
        int ordTakeMdseLen = getOrdTakeMdseLen();
        // 2017/06/14 S21_NA#18623 Add End
        String mdse8LenCd = mdseCd;
        // 2017/06/14 S21_NA#18623 Mod Start
//        if (mdse8LenCd.length() > 8) {
//            mdse8LenCd = mdse8LenCd.substring(0, 8);
//        }
        if (mdse8LenCd.length() > ordTakeMdseLen) {
            mdse8LenCd = mdse8LenCd.substring(0, ordTakeMdseLen);
        }
        // 2017/06/14 S21_NA#18623 Mod End

        ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();


        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.ordTakeMdseCd, mdse8LenCd);
        tMsg = (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(tMsg);

        if (tMsg == null || !ZYPCommonFunc.hasValue(tMsg.mdseCd)) {
            // tMsg.mdseCd is empty or not found in ORD_TAKE_MDSE
            mdse8LenCd = mdseCd;
        } else {
            mdse8LenCd = tMsg.mdseCd.getValue();
        }
        return mdse8LenCd;
    }

    // 2016/11/08 S21_NA#7749 Add End

    /**
     * <pre>
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param slsDt Sales Date
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order Type Code (Order Reason Code)
     * @param dsRtrnRsnCd DE Return Reason Code
     * @param postCd Post Code
     * @param mdseCd Merchandise Code (Item Code)
     * @param ordQty Order Quantity
     * @param svcConfigMstrPk Service Config Master PK
     * @param svcMachMstrPk Service Machine Master PK
     * @param rtlWhCd Retail Warehouse Code
     * @param serNum Serial Number
     * @param isDefWhRequested flag
     * @return Map Data
     */
    public static Map<String, Object> getBaseComponentInfoForRma(String glblCmpyCd, String slsDt, String dsOrdCatgCd, String dsOrdTpCd, String dsRtrnRsnCd, String postCd, String mdseCd, BigDecimal ordQty, BigDecimal svcConfigMstrPk,
            BigDecimal svcMachMstrPk, String rtlWhCd, String serNum, boolean isDefWhRequested) {

        Map<String, Object> resut = new HashMap<String, Object>();

        // get service machine master PK by component serial & MDSE
        if (svcMachMstrPk == null) {
            if (S21StringUtil.isNotEmpty(mdseCd) && S21StringUtil.isNotEmpty(serNum)) {

                // get service machine master PK by component
                // serial & MDSE
                BigDecimal svcMachMstrPkForComponent = NWXC150001Query.getInstance().getSvcMachMstrPkBySerNumAndMdseCd(glblCmpyCd, slsDt, serNum, mdseCd);
                resut.put("SVC_MACH_MSTR_PK_FOR_COMPONENT", svcMachMstrPkForComponent);
            }
        } else {

            resut.put("SVC_MACH_MSTR_PK_FOR_COMPONENT", svcMachMstrPk);
        }

        // get base component information
        BigDecimal svcMachMstrPkForComponent = (BigDecimal) resut.get("SVC_MACH_MSTR_PK_FOR_COMPONENT");
        if (svcConfigMstrPk != null || svcMachMstrPkForComponent != null) {

            Map<String, Object> baseComponent = NWXC150001Query.getInstance().getBaseComponentInformation(glblCmpyCd, slsDt, svcConfigMstrPk, svcMachMstrPkForComponent);
            if (baseComponent == null) {

                return resut;
            }
            mdseCd = (String) baseComponent.get("MDSE_CD");
            resut.put("SVC_MACH_MSTR_PK", baseComponent.get("SVC_MACH_MSTR_PK"));
            resut.put("MDSE_CD", baseComponent.get("MDSE_CD"));
            resut.put("SER_NUM", baseComponent.get("SER_NUM"));
            if (equalsBigDecimal((BigDecimal) resut.get("SVC_MACH_MSTR_PK"), svcMachMstrPkForComponent)) {

                resut.put("BASE_CMPT_FLG", ZYPConstant.FLG_ON_Y);
            }

            if (isDefWhRequested || S21StringUtil.isEquals((String) resut.get("BASE_CMPT_FLG"), ZYPConstant.FLG_ON_Y)) {

                BigDecimal scvMachMstrPkForBaseComponent = (BigDecimal) resut.get("SVC_MACH_MSTR_PK");

                // get default warehouse and sub warehouse by base
                // component.
                if (S21StringUtil.isEmpty(rtlWhCd) && S21StringUtil.isNotEmpty(dsRtrnRsnCd) && S21StringUtil.isNotEmpty(mdseCd) && S21StringUtil.isNotEmpty(postCd) && ordQty != null) {

                    NWZC180001PMsg pMsg = new NWZC180001PMsg();
                    ZYPEZDItemValueSetter.setValue(pMsg.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NWZC180001Constant.PROC_MODE_INBD);
                    ZYPEZDItemValueSetter.setValue(pMsg.slsDt, slsDt);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdCatgCd, dsOrdCatgCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsOrdTpCd, dsOrdTpCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.dsRtrnRsnCd, dsRtrnRsnCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.mdseCd, mdseCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.postCd, postCd);
                    ZYPEZDItemValueSetter.setValue(pMsg.ordQty, ordQty);
                    ZYPEZDItemValueSetter.setValue(pMsg.svcMachMstrPk, scvMachMstrPkForBaseComponent);

                    // call NWZC180001 default retail Warehouse API
                    new NWZC180001().execute(pMsg, ONBATCH_TYPE.ONLINE);

                    if (S21ApiUtil.isXxMsgId(pMsg)) {

                        return resut;
                    }

                    resut.put("RTL_WH_CD", pMsg.rtlWhCd.getValue());
                    resut.put("RTL_SWH_CD", pMsg.rtlSwhCd.getValue());
                    resut.put("THIRD_PTY_DSP_TP_CD", pMsg.thirdPtyDspTpCd.getValue());
                } else {
                    if (S21StringUtil.isNotEmpty(rtlWhCd) && S21StringUtil.isNotEmpty(mdseCd)) {

                        NLZC215001PMsg defSubWhApiPMsg = new NLZC215001PMsg();
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.slsDt, slsDt);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.xxModeCd, NLZC215001Constant.MODE_RMA);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.mdseCd, mdseCd);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.dsOrdCatgCd, dsOrdCatgCd);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.destRtlWhCd, rtlWhCd);
                        ZYPEZDItemValueSetter.setValue(defSubWhApiPMsg.svcMachMstrPk, scvMachMstrPkForBaseComponent);

                        // call NLZC2150 Get Default Sub Warehouse API
                        new NLZC215001().execute(defSubWhApiPMsg, ONBATCH_TYPE.ONLINE);

                        if (S21ApiUtil.isXxMsgId(defSubWhApiPMsg)) {

                            return resut;
                        }
                        resut.put("RTL_WH_CD", rtlWhCd);
                        resut.put("RTL_SWH_CD", defSubWhApiPMsg.destRtlSwhCd.getValue());
                        resut.put("THIRD_PTY_DSP_TP_CD", defSubWhApiPMsg.thirdPtyDspTpCd.getValue());
                    }
                }

                if (S21StringUtil.isNotEmpty((String) resut.get("RTL_WH_CD"))) {

                    // get warehouse name
                    RTL_WHTMsg rtlWh = new RTL_WHTMsg();
                    ZYPEZDItemValueSetter.setValue(rtlWh.glblCmpyCd, glblCmpyCd);
                    ZYPEZDItemValueSetter.setValue(rtlWh.rtlWhCd, (String) resut.get("RTL_WH_CD"));
                    rtlWh = (RTL_WHTMsg) S21FastTBLAccessor.findByKey(rtlWh);
                    if (rtlWh != null) {

                        resut.put("RTL_WH_NM", rtlWh.rtlWhNm.getValue());
                        resut.put("RTL_WH_DESC_TXT", rtlWh.rtlWhDescTxt.getValue());
                    }
                    if (S21StringUtil.isNotEmpty((String) resut.get("RTL_SWH_CD"))) {

                        // get sub warehouse name
                        RTL_SWHTMsg rtlSWh = new RTL_SWHTMsg();
                        ZYPEZDItemValueSetter.setValue(rtlSWh.glblCmpyCd, glblCmpyCd);
                        ZYPEZDItemValueSetter.setValue(rtlSWh.rtlWhCd, (String) resut.get("RTL_WH_CD"));
                        ZYPEZDItemValueSetter.setValue(rtlSWh.rtlSwhCd, (String) resut.get("RTL_SWH_CD"));
                        rtlSWh = (RTL_SWHTMsg) S21FastTBLAccessor.findByKey(rtlSWh);
                        if (rtlSWh != null) {

                            resut.put("RTL_SWH_NM", rtlSWh.rtlSwhNm.getValue());
                            resut.put("RTL_SWH_DESC_TXT", rtlSWh.rtlSwhDescTxt.getValue());
                            // Add Start 2017/07/03 QC#19709
                            if (ZYPCommonFunc.hasValue(rtlSWh.invtyLocCd)) {
                                resut.put("INVTY_LOC_CD", rtlSWh.invtyLocCd.getValue());
                            } else {
                                StringBuilder invtyLocCd = new StringBuilder();
                                invtyLocCd.append(rtlSWh.rtlWhCd.getValue());
                                invtyLocCd.append(rtlSWh.rtlSwhCd.getValue());
                                resut.put("INVTY_LOC_CD", invtyLocCd.toString());
                            }
                            // Add End 2017/07/03 QC#19709
                        }
                    }
                }
            }
        }

        return resut;
    }

    private static boolean equalsBigDecimal(BigDecimal value1, BigDecimal value2) {

        if (value1 == null) {

            return false;
        } else if (value2 == null) {

            return false;
        } else {

            return value1.compareTo(value2) == 0;
        }
    }
    // QC#17474 2017/02/21 Add Start
    /**
     * getOvrdPmtTermFlg
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param billToCustCd String
     * @return String
     */
    public static String getOvrdPmtTermFlg(String glblCmpyCd, String dsAcctNum, String billToCustCd) {
        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            return null;
        }
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return null;
        }
        return NWXC150001Query.getInstance().getOvrdPmtTermFlg(glblCmpyCd, dsAcctNum, billToCustCd);
    }

    /**
     * checkCcReq
     * @param glblCmpyCd String
     * @param dsAcctNum String
     * @param billToCustCd String
     * @param pmtTermCashDiscCd String
     * @return boolean
     */
    public static boolean checkCcReq(String glblCmpyCd, String dsAcctNum, String billToCustCd, String pmtTermCashDiscCd) {
        if (!ZYPCommonFunc.hasValue(dsAcctNum)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(billToCustCd)) {
            return false;
        }
        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = getPmtTerm(glblCmpyCd, pmtTermCashDiscCd);
        if (pmtTermTMsg == null) {
            return false;
        }

        String cashOrCcReqFlg = NWXC150001Query.getInstance().getCashOrCcReqFlg(glblCmpyCd, dsAcctNum, billToCustCd);
        String pmtCcFlg = pmtTermTMsg.pmtCcFlg.getValue();
        String pmtCashFlg = pmtTermTMsg.pmtCashFlg.getValue();

        if (ZYPConstant.FLG_ON_Y.equals(cashOrCcReqFlg)) {
            if (ZYPConstant.FLG_OFF_N.equals(pmtCcFlg) && ZYPConstant.FLG_OFF_N.equals(pmtCashFlg)) {
                return true;
            }
        }

        return false;
    }

    private static PMT_TERM_CASH_DISCTMsg getPmtTerm(String glblCmpyCd, String pmtTermCashDiscCd) {

        PMT_TERM_CASH_DISCTMsg pmtTermTMsg = new PMT_TERM_CASH_DISCTMsg();
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(pmtTermTMsg.pmtTermCashDiscCd, pmtTermCashDiscCd);

        return (PMT_TERM_CASH_DISCTMsg) S21CacheTBLAccessor.findByKey(pmtTermTMsg);
    }
    // QC#17474 2017/02/21 Add End

    // 2017/06/14 S21_NA#18623 Add Start
    private static int getOrdTakeMdseLen() {
      ORD_TAKE_MDSETMsg tMsg = new ORD_TAKE_MDSETMsg();

      int ordTakeMdseLen = tMsg.getAttr("ordTakeMdseCd").getDigit();
      tMsg = null;

      return ordTakeMdseLen;
    }
    // 2017/06/14 S21_NA#18623 Add End

    // 2017/11/02 S21_NA#20146 Add Start
    /**
     * <pre>
     * @param glblCmpyCd     Global Company Code
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param dsOrdCatgCd    DS Order Category Code
     * @param dsOrdTpCd      DS Order Type Code
     * @return result flag
     * </Pre>
     */
    public static String getOrdCatgBizCtxFstAttbTxt(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd) {
        String resultFlg = NWXC150001Query.getInstance().getOrdCatgBizCtxFstAttbTxt(glblCmpyCd, ordCatgCtxTpCd, dsOrdCatgCd, dsOrdTpCd); // 2017/11/16 S21_NA#22620 Mod
        return resultFlg;
    }
    // 2017/11/02 S21_NA#20146 Add End

    // QC#21471
    /**
     * <pre>
     * @param glblCmpyCd   Global Company Code
     * @param billToAcctCd Bill To Account Code
     * @param slsDt        Sales Date
     * @return true: exists bill to account code as CFS on SELL_TO_CUST table. False: not exists.
     * </pre>
     */
    public static boolean isCfsBillToAcct(String glblCmpyCd, String billToAcctCd, String slsDt) {
        Integer cnt = NWXC150001Query.getInstance().existsCfsBillToAcct(glblCmpyCd, billToAcctCd, slsDt);
        return cnt > 0;
    }
    // 2017/12/26 S21_NA#22986 Add Start
    /**
     * check the order line source code for ITT_HLD
     * @param glblCmpyCd global company code
     * @param ordLineSrcCd order line source code
     * @return true: ITT_HLD order line source, false: not
     */
    public static boolean isOrdLineSrcItt(String glblCmpyCd, String ordLineSrcCd) {

        ORD_LINE_SRCTMsg ordLineSrcTMsg = getOrdLineSrcTMsg(glblCmpyCd, ordLineSrcCd);
        if (ordLineSrcTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, ordLineSrcTMsg.ittHldFlg.getValue());
    }

    /**
     * get order line source tMessage
     * @param glblCmpyCd Global Company Code
     * @param ordLineSrcCd Order Line Source Code
     * @return
     */
    private static ORD_LINE_SRCTMsg getOrdLineSrcTMsg(String glblCmpyCd, String ordLineSrcCd) {

        ORD_LINE_SRCTMsg ordLineSrcTMsg = new ORD_LINE_SRCTMsg();
        ZYPEZDItemValueSetter.setValue(ordLineSrcTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordLineSrcTMsg.ordLineSrcCd, ordLineSrcCd);

        return (ORD_LINE_SRCTMsg) S21CacheTBLAccessor.findByKey(ordLineSrcTMsg);
    }

    /**
     * get minimum order line source code not populates ITT_HLD
     * @param glblCmpyCd Global Company Code
     * @return minimum order line source code which will not populates ITT_HLD
     */
    public static String getMinOrdLineSrcNotIttHld(String glblCmpyCd) {

        return NWXC150001Query.getInstance().getMinOrdLineSrcNotIttHld(glblCmpyCd);
    }
    // 2017/12/26 S21_NA#22986 Add End

    // 2018/01/11 S21_NA#23329 Add Start
    /**
     * <pre>
     * This method compare origMdseCd and CompMdseCd. If there are equal or the order take merchandise code
     * of them are equal, this method return true.
     * @param glblCmpyCd Global Company Code
     * @param origMdseCd merchandise Code
     * @param compMdseCd compare merchandise Code
     * @return true: origMdseCd and compMdseCd are equals, false: not equals.
     * <pre>
     */
    public static boolean isNearEqualsItem(String glblCmpyCd, String origMdseCd, String compMdseCd) {

        int ordTakeSize = getOrdTakeMdseLen();
        String ordTakeOrigMdseCd = new String(origMdseCd);
        if (ordTakeOrigMdseCd.length() > ordTakeSize) {
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOrdTakeMdseTMsg(glblCmpyCd, ordTakeOrigMdseCd.substring(0, ordTakeSize));
            if (ordTakeMdseTMsg != null) {
                ordTakeOrigMdseCd = ordTakeMdseTMsg.ordTakeMdseCd.getValue();
            }
        }

        String ordTakeCompMdseCd = new String(compMdseCd);
        if (ordTakeCompMdseCd.length() > ordTakeSize) {
            ORD_TAKE_MDSETMsg ordTakeMdseTMsg = getOrdTakeMdseTMsg(glblCmpyCd, ordTakeCompMdseCd.substring(0, ordTakeSize));
            if (ordTakeMdseTMsg != null) {
                ordTakeCompMdseCd = ordTakeMdseTMsg.ordTakeMdseCd.getValue();
            }
        }

        if (S21StringUtil.isEquals(origMdseCd, compMdseCd) //
                || S21StringUtil.isEquals(ordTakeOrigMdseCd, ordTakeCompMdseCd)) {
            return true;
        }
        return false;
    }

    private static ORD_TAKE_MDSETMsg getOrdTakeMdseTMsg(String glblCmpyCd, String ordTakeMdseCd) {

        ORD_TAKE_MDSETMsg ordTakeMdseTMsg = new ORD_TAKE_MDSETMsg();
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(ordTakeMdseTMsg.ordTakeMdseCd, ordTakeMdseCd);
        return (ORD_TAKE_MDSETMsg) S21CacheTBLAccessor.findByKey(ordTakeMdseTMsg);
    }

    /**
     * <pre>
     * Check the model is Software model Or nut.
     * @param glblCmpyCd Global Company Code
     * @param mdlId Model ID
     * @return true: Software model, false: Not software model
     */
    public static boolean isModelSoftware(String glblCmpyCd, BigDecimal mdlId) {

        return isModelSoftware(getDsMdlTMsg(glblCmpyCd, mdlId));
    }

    /**
     * <pre>
     * Check the model is Software model Or nut.
     * @param mdlTMsg DS Model TMessage
     * @return true: Software model, false: Not software model
     */
    public static boolean isModelSoftware(DS_MDLTMsg mdlTMsg) {

        if (mdlTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(SVC_MDL_TP.SOFTWARE, mdlTMsg.svcMdlTpCd.getValue());
    }

    /**
     * <pre>
     * Check the model is Software model Or nut.
     * @param svcMdlTpCd Service Model Type Code
     * @return true: Software model, false: Not software model
     */
    public static boolean isModelSoftware(String svcMdlTpCd) {

        return S21StringUtil.isEquals(SVC_MDL_TP.SOFTWARE, svcMdlTpCd);
    }

    /**
     * <pre>
     * Get DS_MDLTMsg by primary key: global company code and mdl id
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param mdlId Model ID
     * @return DS_MDLTMsg
     */
    public static DS_MDLTMsg getDsMdlTMsg(String glblCmpyCd, BigDecimal mdlId) {

        DS_MDLTMsg mdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(mdlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdlTMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(mdlTMsg);
    }
    // 2018/01/11 S21_NA#23329 Add End

    // 2018/02/13 S21_NA#22717 add start
    public static boolean checkAssetCratTrgt(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String coaMdseTpCd) {
        String flg = NWXC150001Query.getInstance().getAssetCratProcFlg(glblCmpyCd, dsOrdTpCd, dsOrdLineCatgCd);

        if (ZYPConstant.FLG_ON_Y.equals(flg)) {
            int cnt = NWXC150001Query.getInstance().existsAssetCratTrgt(glblCmpyCd, coaMdseTpCd);
            return cnt <= 0;
        }
        return false;
    }
    // 2018/02/13 S21_NA#22717 add end

    // 2018/03/28 S21_NA#24853 Add Start
    public static boolean checkInternalOrder(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {

        String flg = NWXC150001Query.getInstance().getInternalOrdProcFlg(glblCmpyCd, dsOrdTpCd, dsOrdLineCatgCd, slsDt);

        if (ZYPCommonFunc.hasValue(flg)) {
            return ZYPConstant.FLG_ON_Y.equals(flg);
        }
        return false;
    }
    // 2018/03/28 S21_NA#24853 Add End
    // 2018/05/20 S21_NA#25604 Add Start
    /**
     * <pre>
     * Check detected order is related to Order Category Context Type.
     * @param glblCmpyCd Global Company Code
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order Type Code (it means Order Reason)
     * @param dsOrdRsnCd DS Order Reason Code (it means Order Sub Reason(
     * @return
     * </pre>
     */
    public static boolean isAvalOrderCtxType(String glblCmpyCd, String ordCatgCtxTpCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        Integer cnt = NWXC150001Query.getInstance().getOrderCtxCnt(glblCmpyCd, ordCatgCtxTpCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd);
        return (cnt > 0);
    }

    /**
     * <pre>
     * check coaMdseTpCd is main machine or not.
     * @param glblCmpyCd Global Company Code
     * @param coaMdseTpCd COA Merchandise Code
     * @return true: main machine, false: not main machine
     * </pre>
     */
    public static boolean isMainMachineType(String glblCmpyCd, String coaMdseTpCd) {

        List<String> mainMachineMdseTypeList = getMainMachineMdseTypeList(glblCmpyCd);
        return mainMachineMdseTypeList.contains(coaMdseTpCd);
    }

    /**
     * <pre>
     * get main machine COA_MDSE_TP_CD
     * @param glblCmpyCd Global Company Code
     * @return List<String> Main Machine COA_MDSE_TP_CD List
     * </pre>
     */
    public static List<String> getMainMachineMdseTypeList(String glblCmpyCd) {

        FindCondition findCondition = new FindCondition("002");
        findCondition.addCondition("glblCmpyCd01", glblCmpyCd);
        findCondition.addCondition("mdseTpCtxTpCd01", "MAIN_MACH");

        MDSE_TP_VAL_SETTMsgArray mdseTpValSetTMsgArray = MdseTpValSetCache.getInstance().getTMsgArray(findCondition);

        List<String> mainMachineMdseTypeList = new ArrayList<String>(0);
        if (mdseTpValSetTMsgArray != null && mdseTpValSetTMsgArray.getValidCount() > 0) {
           for (int i = 0; i < mdseTpValSetTMsgArray.getValidCount(); i++) {
               mainMachineMdseTypeList.add(mdseTpValSetTMsgArray.no(i).coaMdseTpCd.getValue());
           }
        }
        return mainMachineMdseTypeList;
    }
    // 2018/05/20 S21_NA#25604 Add End
    
    // 2018/06/14 S21_NA#24294 Add Start
    /**
     * <pre>
     * Get Default Warehouse Template.
     * @param glblCmpyCd Global Company Code
     * @param ordCatgCtxTpCd Order Category Context Type Code
     * @param dsOrdCatgCd DS Order Category Code
     * @param dsOrdTpCd DS Order Type Code (it means Order Reason)
     * @return
     * </pre>
     */
    public static Map<String, String> getWhMpngTmpl(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd) {
        return NWXC150001Query.getInstance().getWhMpngTmpl(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd);
    }
    // 2018/06/14 S21_NA#24294 Add End
    // 2018/06/18 QC#14307 Add Start
    /**
     * <pre>
     * SpclInstnDisplayDetermination
     * @param glblCmpyCd Global Company Code
     * @param dsTrxRuleTpCd Transaction Type
     * @param dsBizAreaCd Business Area
     * @param dsAcctNum Account Number
     * @param billToCustCd Bill to Cust Code
     * @param shipToCustCd Ship to Cust Code
     * @param funcID Function ID
     * @param funcCatgID Function Category ID
     * @param lineBizTpCd Line of Business Type Code
     * @return boolean rslt
     * </pre>
     */
    // 2018/07/10 S21_NA#26661,26713 Mod Start
    // public static boolean spclInstnDisplayDetermination(String glblCmpyCd, String dsTrxRuleTpCd, String dsBizAreaCd, String dsAcctNum, String billToCustCd, String shipToCustCd, String funcID, String funcCatgID) {
    public static boolean spclInstnDisplayDetermination(String glblCmpyCd, String dsTrxRuleTpCd, String dsBizAreaCd, String dsAcctNum, String billToCustCd, String shipToCustCd, String funcID, String funcCatgID, String lineBizTpCd) {
    // 2018/07/10 S21_NA#26661,26713 Mod End
        boolean rslt = false;

        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(dsAcctNum) && !ZYPCommonFunc.hasValue(billToCustCd) && !ZYPCommonFunc.hasValue(shipToCustCd)) {
            return false;
        }

        NWXC150001CSpclInstnDispDeteminatnSBean sBean = new NWXC150001CSpclInstnDispDeteminatnSBean();
        NMZC610001 custInfoGetApi = null;
        NMZC610001PMsg custInfoGetApiPMsg = null;

        // getLocation Number/Account Number
        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            Map<String, Object> acctRslt = NWXC150001Query.getInstance().getAcctNum(dsAcctNum, glblCmpyCd);
            // 2018/08/07 QC#14307 Mod Start
//            if (!acctRslt.isEmpty()) {
            if (acctRslt != null && !acctRslt.isEmpty()) {
            // 2018/08/07 QC#14307 Mod End
                sBean.setdsAcctNum_S1(dsAcctNum);
                sBean.setdsAcctNum_S2((String) acctRslt.get("SELL_TO_CUST_CD"));
//                sBean.setlocNum_S2((String) acctRslt.get("LOC_NUM"));
            }
        }

        if (ZYPCommonFunc.hasValue(billToCustCd)) {
            Map<String, Object> billtRslt = NWXC150001Query.getInstance().getLocNumFromBill(billToCustCd, glblCmpyCd);
            // 2018/08/07 QC#14307 Mod Start
//            if (!billtRslt.isEmpty()) {
            if (billtRslt != null && !billtRslt.isEmpty()) {
            // 2018/08/07 QC#14307 Mod End
                sBean.setdsAcctNum_S1(dsAcctNum);
                sBean.setbillToCustCd_S1(billToCustCd);
                sBean.setdsAcctNum_S2((String) billtRslt.get("SELL_TO_CUST_CD"));
                sBean.setlocNum_S2((String) billtRslt.get("LOC_NUM"));
            }
        }

        if (ZYPCommonFunc.hasValue(shipToCustCd)) {
            Map<String, Object> shiptRslt = NWXC150001Query.getInstance().getLocNumFromShip(shipToCustCd, glblCmpyCd);
            // 2018/08/07 QC#14307 Mod Start
//            if (!shiptRslt.isEmpty()) {
                if (shiptRslt != null && !shiptRslt.isEmpty()) {
            // 2018/08/07 QC#14307 Mod End
                sBean.setdsAcctNum_S1(dsAcctNum);
                sBean.setshipToCustCd_S1(shipToCustCd);
                sBean.setdsAcctNum_S2((String) shiptRslt.get("SELL_TO_CUST_CD"));
                sBean.setlocNum_S2((String) shiptRslt.get("LOC_NUM"));
            }
        }

        // getSearchRec(bizMsg, sharedMsg);
        List<NWXC150001CSpclInstnDispDeteminatnRBean> rBeanList = new ArrayList<NWXC150001CSpclInstnDispDeteminatnRBean>();
        int searchCnt = 0;
        if (ZYPCommonFunc.hasValue(sBean.getlocNum_S2())) {
            NWXC150001CSpclInstnDispDeteminatnRBean rBean = new NWXC150001CSpclInstnDispDeteminatnRBean();

            rBean.setdsAcctNum_R1(sBean.getdsAcctNum_S2());
            rBean.setlocNum_R1(sBean.getlocNum_S2());
            rBean.sethrchEffLvlTpCd_R1(HRCH_EFF_LVL_TP.LOC);
            rBean.sethrchEffLvlTpNm_R1(getHrchEffLvlTpNm(rBean.gethrchEffLvlTpCd_R1(), glblCmpyCd));
            rBeanList.add(rBean);
            searchCnt++;
        }

        // Create Api Parameter
        custInfoGetApi = new NMZC610001();
        custInfoGetApiPMsg = new NMZC610001PMsg();
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, "07");
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxChildRelnFlg, ZYPConstant.FLG_OFF_N);
        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, sBean.getdsAcctNum_S2());

        // Call Api
        custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);

        String duplChkNum = "";
        String duplTgtNum = "";
        for (int i = 0; i < custInfoGetApiPMsg.RelatedBillShipList.getValidCount(); i++) {
            NMZC610001_RelatedBillShipListPMsg inPmsg = custInfoGetApiPMsg.RelatedBillShipList.no(i);
            duplTgtNum = S21StringUtil.concatStrings(inPmsg.dsAcctRelnTpCd.getValue(), inPmsg.dsAcctNum.getValue(), inPmsg.relnDsAcctNum.getValue());
            if (!duplChkNum.equals(duplTgtNum)) {
                if (!(ZYPCommonFunc.hasValue(inPmsg.dsAcctRelnTpCd) && DS_ACCT_RELN_TP.RELATED_ACCOUNT.equals(inPmsg.dsAcctRelnTpCd.getValue()) || DS_ACCT_RELN_TP.LEASE_ACCOUNT.equals(inPmsg.dsAcctRelnTpCd.getValue()))) {
                    NWXC150001CSpclInstnDispDeteminatnRBean rBean = new NWXC150001CSpclInstnDispDeteminatnRBean();
                    rBean.setdsAcctRelnTpCd_R1(inPmsg.dsAcctRelnTpCd.getValue());
                    rBean.setdsAcctNum_R1(inPmsg.dsAcctNum.getValue());
                    rBean.setrelnDsAcctNum_R1(inPmsg.relnDsAcctNum.getValue());
                    rBean.sethrchEffLvlTpCd_R1(getHrchEffLvlTpCd(inPmsg.dsAcctRelnTpCd.getValue(), inPmsg.relnDsAcctNum.getValue(), glblCmpyCd));
                    rBean.sethrchEffLvlTpNm_R1(getHrchEffLvlTpNm(rBean.gethrchEffLvlTpCd_R1(), glblCmpyCd));
                    rBeanList.add(rBean);
                    searchCnt++;
                }
                duplChkNum = duplTgtNum;
            }
        }

        // 2018/11/09 Del Start   QC#28683
//        // Display / non-display setting
//        Map<String, Object> transactionRslt = chkDisplayFlg(funcID, funcCatgID, rBeanList, "03", glblCmpyCd, "1");
//        if (transactionRslt != null && transactionRslt.size() > 0) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean transactionBean : rBeanList) {
//                // 2018/07/10 S21_NA#26661,26713 Mod Start
//                // if (!chkDisplayAval(transactionRslt)) {
//                if (!chkDisplayAval(transactionRslt, transactionBean.gethrchEffLvlTpCd_R1())) {
//                // 2018/07/10 S21_NA#26661,26713 Mod End
//                    transactionBean.setxxYesNoCd_R1(ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//
//        Map<String, Object> referenceRslt = chkDisplayFlg(funcID, funcCatgID, rBeanList, "04", glblCmpyCd, "2");
//        if (referenceRslt != null && referenceRslt.size() > 0) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean transactionBean : rBeanList) {
//                // 2018/07/10 S21_NA#26661,26713 Mod Start
//                // if (!chkDisplayAval(referenceRslt)) {
//                if (!chkDisplayAval(referenceRslt, transactionBean.gethrchEffLvlTpCd_R1())) {
//                // 2018/07/10 S21_NA#26661,26713 Mod End
//                    transactionBean.setxxYesNoCd_R2(ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
//
//        Map<String, Object> handlingRslt = chkDisplayFlg(funcID, funcCatgID, rBeanList, "06", glblCmpyCd, "3");
//        if (handlingRslt != null && handlingRslt.size() > 0) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean transactionBean : rBeanList) {
//                // 2018/07/10 S21_NA#26661,26713 Mod Start
//                // if (!chkDisplayAval(handlingRslt)) {
//                if (!chkDisplayAval(handlingRslt, transactionBean.gethrchEffLvlTpCd_R1())) {
//                // 2018/07/10 S21_NA#26661,26713 Mod End
//                    transactionBean.setxxYesNoCd_R3(ZYPConstant.FLG_OFF_N);
//                }
//            }
//        }
        // 2018/11/09 Del End   QC#28683

        Map<String, Object> instructionRslt = chkDisplayFlg(funcID, funcCatgID, rBeanList, "05", glblCmpyCd, "4");
        if (instructionRslt != null && instructionRslt.size() > 0) {
            for (NWXC150001CSpclInstnDispDeteminatnRBean transactionBean : rBeanList) {
                // 2018/07/10 S21_NA#26661,26713 Mod Start
                // if (!chkDisplayAval(instructionRslt)) {
                if (!chkDisplayAval(instructionRslt, transactionBean.gethrchEffLvlTpCd_R1())) {
                // 2018/07/10 S21_NA#26661,26713 Mod End
                    transactionBean.setxxYesNoCd_R4(ZYPConstant.FLG_OFF_N);
                }
            }
        }

        // 2018/11/09 Del Start   QC#28683
//        // getTrxDriverSection
//        for (NWXC150001CSpclInstnDispDeteminatnRBean rBeanl : rBeanList) {
//            if (0 >= rBeanList.size()) {
//                break;
//            }
//
//            if (ZYPConstant.FLG_ON_Y.equals(rBeanl.getxxYesNoCd_R1())) {
//                String custRefSearchKey = "";
//
//                S21SsmEZDResult trxDivSecRslt;
//                if (ZYPCommonFunc.hasValue(rBeanl.getlocNum_R1())) {
//                    custRefSearchKey = rBeanl.getlocNum_R1();
//                    trxDivSecRslt = NWXC150001Query.getInstance().getCustSpclInstnForLoc(funcID, funcCatgID, custRefSearchKey, glblCmpyCd);
//                    if (trxDivSecRslt.isCodeNormal()) {
//                        rslt = true;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(rBeanl.getrelnDsAcctNum_R1())) {
//                        custRefSearchKey = rBeanl.getrelnDsAcctNum_R1();
//                    } else {
//                        custRefSearchKey = rBeanl.getdsAcctNum_R1();
//                    }
//                    trxDivSecRslt = NWXC150001Query.getInstance().getCustSpclInstnForAcct(funcID, funcCatgID, custRefSearchKey, glblCmpyCd);
//                    if (trxDivSecRslt.isCodeNormal()) {
//                        rslt = true;
//                    }
//                }
//            }
//        }
        // 2018/11/09 Del End   QC#28683

        // 2018/07/10 S21_NA#26661,26713 Del Start
        //NWXC150001CSpclInstnDispDeteminatnRBean transactionBean = rBeanList.get(0);
        //if (ZYPCommonFunc.hasValue(dsTrxRuleTpCd)) {
        //    if (ZYPConstant.FLG_ON_Y.equals(transactionBean.getxxYesNoCd_R1())) {
        //        custInfoGetApi = new NMZC610001();
        //        // Create Api Parameter
        //        custInfoGetApiPMsg = new NMZC610001PMsg();
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_TRANSACTION);
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsTrxRuleTpCd, dsTrxRuleTpCd);
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, sBean.getdsAcctNum_S1());
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, sBean.getbillToCustCd_S1());
        //        ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.shipToCustCd, sBean.getshipToCustCd_S1());
        //
        //        // Call Api
        //        custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
        //
        //        NMZC610001_TransactionRuleListPMsgArray trxRulePMsgArray = custInfoGetApiPMsg.TransactionRuleList;
        //        if (trxRulePMsgArray.getValidCount() > 0) {
        //            rslt = true;
        //        }
        //    }
        //}
        // 2018/07/10 S21_NA#26661,26713 Del End

        // 2018/11/09 Del Start   QC#28683
//        // getCustReferenceSection
//        for (NWXC150001CSpclInstnDispDeteminatnRBean rBeanl : rBeanList) {
//            if (0 >= rBeanList.size()) {
//                break;
//            }
//
//            if (ZYPConstant.FLG_ON_Y.equals(rBeanl.getxxYesNoCd_R2())) {
//                String custRefSearchKey = "";
//
//                S21SsmEZDResult custReferenceSectionRslt;
//                if (ZYPCommonFunc.hasValue(rBeanl.getlocNum_R1())) {
//                    custRefSearchKey = rBeanl.getlocNum_R1();
//                    custReferenceSectionRslt = NWXC150001Query.getInstance().getAcctRefAttrbForLoc(custRefSearchKey, glblCmpyCd);
//                    if (custReferenceSectionRslt.isCodeNormal()) {
//                        rslt = true;
//                    }
//                } else {
//                    if (ZYPCommonFunc.hasValue(rBeanl.getrelnDsAcctNum_R1())) {
//                        custRefSearchKey = rBeanl.getrelnDsAcctNum_R1();
//                    } else {
//                        custRefSearchKey = rBeanl.getdsAcctNum_R1();
//                    }
//                    custReferenceSectionRslt = NWXC150001Query.getInstance().getAcctRefAttrbForAcct(custRefSearchKey, glblCmpyCd);
//                    if (custReferenceSectionRslt.isCodeNormal()) {
//                        rslt = true;
//                    }
//                }
//            }
//        }
//
//        // getSpclHandlingSection
//        for (NWXC150001CSpclInstnDispDeteminatnRBean rBeanl : rBeanList) {
//            if (0 >= rBeanList.size()) {
//                break;
//            }
//
//            if (ZYPConstant.FLG_ON_Y.equals(rBeanl.getxxYesNoCd_R3())) {
//                S21SsmEZDResult spclInstRslt;
//
//                String spcHandsearchKey = "";
//                if (ZYPCommonFunc.hasValue(rBeanl.getrelnDsAcctNum_R1())) {
//                    spcHandsearchKey = rBeanl.getrelnDsAcctNum_R1();
//                } else {
//                    spcHandsearchKey = rBeanl.getdsAcctNum_R1();
//                }
//                spclInstRslt = NWXC150001Query.getInstance().getSpclHdlg(spcHandsearchKey, ZYPDateUtil.getSalesDate(glblCmpyCd), glblCmpyCd);
//                if (spclInstRslt.isCodeNormal()) {
//                    rslt = true;
//                }
//            }
//        }
        // 2018/11/09 Del End   QC#28683

        // getSpclMessageSection
        for (NWXC150001CSpclInstnDispDeteminatnRBean rBeanl : rBeanList) {
            if (0 >= rBeanList.size()) {
                break;
            }

            // 2018/07/10 S21_NA#26661,26713 Mod Start
            // if (ZYPConstant.FLG_ON_Y.equals(rBeanl.getxxYesNoCd_R1())) {
            if (ZYPConstant.FLG_ON_Y.equals(rBeanl.getxxYesNoCd_R4())) {
            // 2018/07/10 S21_NA#26661,26713 Mod End
                String custRefSearchKey = "";

                S21SsmEZDResult spclMsgRslt;
                if (ZYPCommonFunc.hasValue(rBeanl.getlocNum_R1())) {
                    custRefSearchKey = rBeanl.getlocNum_R1();
                    // 2018/07/10 S21_NA#26661,26713 Mod Start
                    // spclMsgRslt = NWXC150001Query.getInstance().getCustSpclMsgForLoc(funcID, funcCatgID, custRefSearchKey, glblCmpyCd);
                    spclMsgRslt = NWXC150001Query.getInstance().getCustSpclMsgForLoc(funcID, funcCatgID, custRefSearchKey, glblCmpyCd, lineBizTpCd);
                    // 2018/07/10 S21_NA#26661,26713 Mod End
                    if (spclMsgRslt.isCodeNormal()) {
                        rslt = true;
                    }
                } else {
                    if (ZYPCommonFunc.hasValue(rBeanl.getrelnDsAcctNum_R1())) {
                        custRefSearchKey = rBeanl.getrelnDsAcctNum_R1();
                    } else {
                        custRefSearchKey = rBeanl.getdsAcctNum_R1();
                    }
                    // 2018/07/10 S21_NA#26661,26713 Mod Start
                    // spclMsgRslt = NWXC150001Query.getInstance().getCustSpclMsgForAcct(funcID, funcCatgID, custRefSearchKey, glblCmpyCd);
                    spclMsgRslt = NWXC150001Query.getInstance().getCustSpclMsgForAcct(funcID, funcCatgID, custRefSearchKey, glblCmpyCd, lineBizTpCd);
                    // 2018/07/10 S21_NA#26661,26713 Mod End
                    if (spclMsgRslt.isCodeNormal()) {
                        rslt = true;
                    }
                }
            }
        }

        // 2018/07/10 S21_NA#26661,26713 Del Start
        //NWXC150001CSpclInstnDispDeteminatnRBean spclMsgBean = rBeanList.get(0);
        //if (ZYPCommonFunc.hasValue(dsTrxRuleTpCd)) {
        //    if (ZYPConstant.FLG_ON_Y.equals(spclMsgBean.getxxYesNoCd_R4())) {
        //        custInfoGetApi = new NMZC610001();
        //        S21SsmEZDResult spclMsgRslt = null;
        //        spclMsgRslt = NWXC150001Query.getInstance().getLobTypeWithMessageType(glblCmpyCd);
        //        if (spclMsgRslt.isCodeNormal()) {
        //            List<Map<String, String>> resultList = (List<Map<String, String>>) spclMsgRslt.getResultObject();
        //            for (Map<String, String> map : resultList) {
        //                // Create Api Parameter
        //                custInfoGetApiPMsg = new NMZC610001PMsg();
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.glblCmpyCd, glblCmpyCd);
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.xxModeCd, NMZC610001Constant.PROCESS_MODE_INSTRUCTION);
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.lineBizTpCd, map.get("LINE_BIZ_TP_CD"));
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsBizAreaCd, dsBizAreaCd);
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsCustMsgTpCd, map.get("DS_CUST_MSG_TP_CD"));
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.dsAcctNum_I1, sBean.getdsAcctNum_S1());
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.billToCustCd, sBean.getbillToCustCd_S1());
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.shipToCustCd, sBean.getshipToCustCd_S1());
        //                ZYPEZDItemValueSetter.setValue(custInfoGetApiPMsg.slsDt, ZYPDateUtil.getSalesDate(glblCmpyCd));
        //
        //                // Call Api
        //                custInfoGetApi.execute(custInfoGetApiPMsg, ONBATCH_TYPE.ONLINE);
        //
        //                NMZC610001_TransactionRuleListPMsgArray trxRulePMsgArray = custInfoGetApiPMsg.TransactionRuleList;
        //                if (trxRulePMsgArray.getValidCount() > 0) {
        //                    rslt = true;
        //                }
        //            }
        //        }
        //    }
        //}
        // 2018/07/10 S21_NA#26661,26713 Del End

        return rslt;
    }

    /**
     * getHrchEffLvlTpNm
     * @param hrchEffLvlTpCd String
     * @param glblCmpyCd String
     * @return String
     */
    private static String getHrchEffLvlTpNm(String hrchEffLvlTpCd, String glblCmpyCd) {

        HRCH_EFF_LVL_TPTMsg hrchEffLvlTpTMsg = new HRCH_EFF_LVL_TPTMsg();
        ZYPEZDItemValueSetter.setValue(hrchEffLvlTpTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(hrchEffLvlTpTMsg.hrchEffLvlTpCd, hrchEffLvlTpCd);
        hrchEffLvlTpTMsg = (HRCH_EFF_LVL_TPTMsg) EZDTBLAccessor.findByKey(hrchEffLvlTpTMsg);
        if (hrchEffLvlTpTMsg == null) {
            return "";
        }
        return hrchEffLvlTpTMsg.hrchEffLvlTpNm.getValue();
    }

    /**
     * getHrchEffLvlTpCd
     * @param dsAcctRelnTpCd String
     * @param relnDsAcctNum relnDsAcctNumString
     * @param glblCmpyCd String
     * @return String
     */
    private static String getHrchEffLvlTpCd(String dsAcctRelnTpCd, String relnDsAcctNum, String glblCmpyCd) {
        String hrchEffLvlTpCd = "";

        if (!ZYPCommonFunc.hasValue(dsAcctRelnTpCd)) {
            hrchEffLvlTpCd = HRCH_EFF_LVL_TP.ACCT;
        } else {
            Integer cnt = NWXC150001Query.getInstance().chkHrchEffLvlTp(relnDsAcctNum, glblCmpyCd);

            if (cnt > 0) {
                hrchEffLvlTpCd = HRCH_EFF_LVL_TP.PARENT;
            } else {
                hrchEffLvlTpCd = HRCH_EFF_LVL_TP.TOP;
            }
        }
        return hrchEffLvlTpCd;
    }

    /**
     * setTransactionDisplayFlg
     * @param String FuncID
     * @param String FuncCatgID
     * @param List<NWXC150001CSpclInstnDispDeteminatnRBean> sBeanList
     * @param String custSpclInstnCtxTpCd
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    private static Map<String, Object> chkDisplayFlg(String funcID,
                                                       String funcCatgID,
                                                       List<NWXC150001CSpclInstnDispDeteminatnRBean> sBeanList,
                                                       String custSpclInstnCtxTpCd,
                                                       String glblCmpyCd,
                                                       String flgNum) {

        Map<String, Object> rslt = null;
        // 2018/11/09 Del Start   QC#28683
//        if ("1".equals(flgNum)) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean sBean : sBeanList) {
//                sBean.setxxYesNoCd_R1(ZYPConstant.FLG_ON_Y);
//            }
//        }
//
//        if ("2".equals(flgNum)) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean sBean : sBeanList) {
//                sBean.setxxYesNoCd_R2(ZYPConstant.FLG_ON_Y);
//            }
//        }
//
//        if ("3".equals(flgNum)) {
//            for (NWXC150001CSpclInstnDispDeteminatnRBean sBean : sBeanList) {
//                sBean.setxxYesNoCd_R3(ZYPConstant.FLG_ON_Y);
//            }
//        }
        // 2018/11/09 Del End   QC#28683

        if ("4".equals(flgNum)) {
            for (NWXC150001CSpclInstnDispDeteminatnRBean sBean : sBeanList) {
                sBean.setxxYesNoCd_R4(ZYPConstant.FLG_ON_Y);
            }
        }

        Integer cnt = NWXC150001Query.getInstance().chkDisplay(funcID, funcCatgID, custSpclInstnCtxTpCd, glblCmpyCd);

        if (cnt > 0) {
            rslt = NWXC150001Query.getInstance().getDisplayAvalCd(custSpclInstnCtxTpCd, glblCmpyCd);
            if (rslt.size() > 0) {
                // 2018/07/10 S21_NA#26661,26713 Mod Start
                // rslt = NWXC150001Query.getInstance().getDisplayAvalFlgForTrxDriver(funcID, funcCatgID, custSpclInstnCtxTpCd, glblCmpyCd);
                Map<String, Object> rsltDispFlg = NWXC150001Query.getInstance().getDisplayAvalFlgForTrxDriver(funcID, funcCatgID, custSpclInstnCtxTpCd, glblCmpyCd);
                rslt.putAll(rsltDispFlg);
                // 2018/07/10 S21_NA#26661,26713 Mod End
            }
        } 
        return rslt;
    }

    /**
     * chkDisplayAval
     * @param Map<String, Object> rslt
     * @param String hrchEffLvlTpCd
     * @return boolean
     */
    // 2018/07/10 S21_NA#26661,26713 Mod Start
    //private static boolean chkDisplayAval(Map<String, Object> rslt) {
    //
    //    if (ZYPConstant.FLG_ON_Y.equals(rslt.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
    //        return true;
    //    }
    //
    //    if (ZYPConstant.FLG_ON_Y.equals(rslt.get("SCD_BIZ_CTX_ATTRB_TXT"))) {
    //        return true;
    //    }
    //
    //    if (ZYPConstant.FLG_ON_Y.equals(rslt.get("THIRD_BIZ_CTX_ATTRB_TXT"))) {
    //        return true;
    //    }
    //
    //    if (ZYPConstant.FLG_ON_Y.equals(rslt.get("FRTH_BIZ_CTX_ATTRB_TXT"))) {
    //        return true;
    //    }
    //
    //    return false;
    //}
    private static boolean chkDisplayAval(Map<String, Object> rslt, String hrchEffLvlTpCd) {

        if (hrchEffLvlTpCd.equals(rslt.get("FIRST_BIZ_CTX_ATTRB_TXT_DISP"))) {
            if (ZYPConstant.FLG_ON_Y.equals(rslt.get("FIRST_BIZ_CTX_ATTRB_TXT"))) {
                return true;
            }
        }

        if (hrchEffLvlTpCd.equals(rslt.get("SCD_BIZ_CTX_ATTRB_TXT_DISP"))) {
            if (ZYPConstant.FLG_ON_Y.equals(rslt.get("SCD_BIZ_CTX_ATTRB_TXT"))) {
                return true;
            }
        }

        if (hrchEffLvlTpCd.equals(rslt.get("THIRD_BIZ_CTX_ATTRB_TXT_DISP"))) {
            if (ZYPConstant.FLG_ON_Y.equals(rslt.get("THIRD_BIZ_CTX_ATTRB_TXT"))) {
                return true;
            }
        }

        if (hrchEffLvlTpCd.equals(rslt.get("FRTH_BIZ_CTX_ATTRB_TXT_DISP"))) {
            if (ZYPConstant.FLG_ON_Y.equals(rslt.get("FRTH_BIZ_CTX_ATTRB_TXT"))) {
                return true;
            }
        }

        return false;
    }
    // 2018/06/18 QC#14307 Add End
    //QC#23726 2018/06/25 add Start
    /**
     * check Customer Carrier Service Level Relation
     * @param glblCmpyCd    Global Company Code
     * @param dsOrdCatgCd   DS Order Category Code
     * @param dsOrdTpgCd    DS Order Type Code
     * @param dsOrdRsnCd    DS Order Reason Code
     * @param dsAcctNum     DS Account Number
     * @param carrSvcLvlCd  Carrier Service Level Code
     * @param frtCondCd     Freight Condition Code
     * @return boolean if error then return true.
     */
    public static boolean checkCustCarrSvcLvlRelation(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd, String dsAcctNum, String carrSvcLvlCd, String frtCondCd) {
        return checkCustCarrSvcLvlRelation(glblCmpyCd, getDsBizArea(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd, dsOrdRsnCd), dsAcctNum, carrSvcLvlCd, frtCondCd);
    }
    /**
     * check Customer Carrier Service Level Relation
     * @param glblCmpyCd    Global Company Code
     * @param dsBizAreaCd   DS Business Area Code
     * @param dsAcctNum     DS Account Number
     * @param carrSvcLvlCd  Carrier Service Level Code
     * @param frtCondCd     Freight Condition Code
     * @return boolean if error then return true.
     */
    public static boolean checkCustCarrSvcLvlRelation(String glblCmpyCd, String dsBizAreaCd, String dsAcctNum, String carrSvcLvlCd, String frtCondCd) {

        if (!FRT_COND.COLLECT.equals(frtCondCd) && !ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(dsBizAreaCd)
                || !ZYPCommonFunc.hasValue(dsAcctNum)
                || !ZYPCommonFunc.hasValue(carrSvcLvlCd)) {
            return true;
        }

        List<String> carrSvcLvlCdList = NWXC150001Query.getInstance().getCustCarrScvLvlReln(glblCmpyCd, dsBizAreaCd, dsAcctNum);

        if (carrSvcLvlCdList == null || carrSvcLvlCdList.isEmpty()) {
            return false;

        } else {
            for (String svcLvlCd : carrSvcLvlCdList) {
                if (carrSvcLvlCd.equals(svcLvlCd)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static String getDsBizArea(String glblCmpyCd, String dsOrdCatgCd, String dsOrdTpCd, String dsOrdRsnCd) {
        return NWXC150001Query.getInstance().getDsBizArea(glblCmpyCd, dsOrdCatgCd, dsOrdTpCd,dsOrdRsnCd);
    }
    //QC#23726 2018/06/25 add End

    // 2018/07/09 S21_NA#26895 Add Start
    /**
     * <pre>
     * Check parameter rtlWhCd allows overwrite other retail warehouse code
     * @param glblCmpyCd Global Company Code
     * @param rtlWhCd Retail Warehouse Code
     * @return true: overwrite available, false: overwrite disable.
     * </pre>
     */
    public static boolean isAvalableOverWriteRtlWhCd(String glblCmpyCd, String rtlWhCd) {

        List<String> exemptRtlWhCdList = NWXC150001Query.getInstance().getOverWriteExemptRtlWhCdList(glblCmpyCd);
        if (exemptRtlWhCdList == null) {
            return true;
        }
        return !exemptRtlWhCdList.contains(rtlWhCd);
    }
    // 2018/07/09 S21_NA#26895 Add End

    // 2018/07/13 S21_NA#27228 Add Start
    /**
     * <pre>
     * Check parameter base component rtlWhCd is allowed to  overwrite other retail warehouse code
     * @param glblCmpyCd Global Company Code
     * @param rtlWhCd Retail Warehouse Code
     * @return true: overwrite available, false: overwrite disable.
     * </pre>
     */
    public static boolean isAvalableOverWriteBaseRtlWhCd(String glblCmpyCd, String rtlWhCd) {

        List<String> exemptBaseRtlWhCdList = NWXC150001Query.getInstance().getOverWriteExemptBaseRtlWhCdList(glblCmpyCd);
        if (exemptBaseRtlWhCdList == null) {
            return true;
        }
        return !exemptBaseRtlWhCdList.contains(rtlWhCd);
    }
    // 2018/07/13 S21_NA#27228 Add Start
    
    // 2018/07/30 S21_NA#26181 Add Start
    /**
     * alreadyExistOpenRmaLine
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean alreadyExistOpenRmaLine(String glblCmpyCd, BigDecimal svcMachMstrPk, String serNum, String mdseCd, String cpoOrdNum) {
        Integer cnt = NWXC150001Query.getInstance().alreadyExistOpenRmaLine(glblCmpyCd, svcMachMstrPk, serNum, mdseCd, cpoOrdNum);
        return (cnt != 0);
    }
    // 2018/07/30 S21_NA#26181 Add End
    
    // QC#26410
    public static boolean isAvalableItemForOrderLine(String glblCmpyCd, String mdseCd, String crRebilCd) {
        if (ZYPCommonFunc.hasValue(crRebilCd)) {
            return true;
        }
        String flg = NWXC150001Query.getInstance().isAvalableItemForOrderLine(glblCmpyCd, mdseCd);
        return S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, flg);
    }
    // QC#29262 2018/11/27 Add Start
    public static boolean checkOrdLineSrcCatg(String glblCmpyCd, String ordLineSrcCd, String ordLineSrcCatgCd) {
        ORD_LINE_SRCTMsg ordLineSrcTMsg = getOrdLineSrcTMsg(glblCmpyCd, ordLineSrcCd);
        if (ordLineSrcTMsg == null) {
            return false;
        }
        return S21StringUtil.isEquals(ordLineSrcCatgCd, ordLineSrcTMsg.ordLineSrcCatgCd.getValue());
    }
    // QC#29262 2018/11/27 Add End

    // QC#29248
    public static boolean hasBillableCounter(String glblCmpyCd, BigDecimal mdlId, BigDecimal prcMtrPkgPk, String slsDt) {
        if (!ZYPCommonFunc.hasValue(prcMtrPkgPk)) {
            return false;
        }
        return ((Integer) NWXC150001Query.getInstance().cntBillableCounter(glblCmpyCd, mdlId, prcMtrPkgPk, slsDt) > 0);
    }
    
    // 2019/01/08 QC#29241 Add Start
    /**
     * checkOrdQtyVldFlg
     * @param glblCmpyCd String
     * @param dsOrdTpCd String
     * @param dsOrdLineCatgCd String
     * @param slsDt String
     * @return boolean
     */
    public static boolean checkOrdQtyVldFlg(String glblCmpyCd, String dsOrdTpCd, String dsOrdLineCatgCd, String slsDt) {
        String ordQtyVldFlg = NWXC150001Query.getInstance().getOrdQtyVldFlg(glblCmpyCd, dsOrdTpCd, dsOrdLineCatgCd, slsDt);

        if (ZYPCommonFunc.hasValue(ordQtyVldFlg) && ZYPConstant.FLG_OFF_N.equals(ordQtyVldFlg)) {
            return false;
        } else {
            return true;
        }
    }
    // 2019/01/08 QC#29241 Add End
    
    // 2019/04/08 S21_NA#31111 Add Start
    public static boolean hasSvcConfigPkInSameTrx(String glblCmpyCd, String cpoOrdNum, String configCatgCd, BigDecimal svcConfigMstrPk) {
        if (!ZYPCommonFunc.hasValue(cpoOrdNum) || !ZYPCommonFunc.hasValue(configCatgCd) || !ZYPCommonFunc.hasValue(svcConfigMstrPk)) {
            return false;
        }
        return ((Integer) NWXC150001Query.getInstance().getConfigCnt4Exch(glblCmpyCd, cpoOrdNum, configCatgCd, svcConfigMstrPk) > 0);
    }
    // 2019/04/08 S21_NA#31111 Add End

    // 2019/12/14 QC#53588 Add Start
    /**
     * checkAllLineCancelledOrClosed
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean checkAllLineCancelledOrClosed(String glblCmpyCd, String cpoOrdNum) {
        Integer cnt = NWXC150001Query.getInstance().getActiveLineCntEx(glblCmpyCd, cpoOrdNum);
        return (cnt == 0);
    }

    /**
     * isActiveLine
     * @param glblCmpyCd
     * @param cpoOrdNum
     * @param posNum
     * @param lineNum
     * @return
     */
    public static boolean  isActiveLine(String glblCmpyCd, String cpoOrdNum, String posNum, String lineNum) {
        Integer cnt = NWXC150001Query.getInstance().isActiveLine(glblCmpyCd, cpoOrdNum, posNum, lineNum);
        return (cnt > 0);
    }

    /**
     * checkAllRmaLineCancelledOrClosed
     * @param glblCmpyCd glblCmpyCd
     * @param cpoOrdNum cpoOrdNum
     * @return boolean if error then return true.
     */
    public static boolean checkAllRmaLineCancelledOrClosed(String glblCmpyCd, String cpoOrdNum) {
        Integer cnt = NWXC150001Query.getInstance().getActiveRmaLineCntEx(glblCmpyCd, cpoOrdNum);
        return (cnt == 0);
    }
    
    // 2019/12/14 QC#53588 Add End

    // ADD START 2022/08/19 H.Watanabe [QC#60255]
    /**
     * chkCarrierAccountNumberNeedValidation
     * @param glblCmpyCd
     * @param carrAcctNum
     * @param shpgSvcLvlCd
     * @param carrSvcLvlCd
     * @return
     */
    public static boolean chkCarrierAccountNumberNeedValidation(String glblCmpyCd, String carrAcctNum, String shpgSvcLvlCd, String carrSvcLvlCd) {
        if (!ZYPCommonFunc.hasValue(glblCmpyCd) || !ZYPCommonFunc.hasValue(shpgSvcLvlCd) || !ZYPCommonFunc.hasValue(carrSvcLvlCd) || !ZYPCommonFunc.hasValue(carrAcctNum)) {
            return true;
        }
        Map<String, Object> vldInfo = (Map<String, Object>) NWXC150001Query.getInstance().getCarrAcctNumVld(glblCmpyCd, shpgSvcLvlCd, carrSvcLvlCd);
        if (vldInfo == null) {
            return true;
        }
        BigDecimal lgMinNum = (BigDecimal) vldInfo.get("LG_MIN_NUM");
        BigDecimal lgMaxNum = (BigDecimal) vldInfo.get("LG_MAX_NUM");
        String vldPtrmTxt = (String) vldInfo.get("VLD_PTRN_TXT");
        if (carrAcctNum.length() < lgMinNum.intValue() || carrAcctNum.length() > lgMaxNum.intValue()) {
            return false;
        }
        if (!carrAcctNum.matches(vldPtrmTxt)) {
            return false;
        }
        return true;
    }
    // ADD END   2022/08/19 H.Watanabe [QC#60255]
}

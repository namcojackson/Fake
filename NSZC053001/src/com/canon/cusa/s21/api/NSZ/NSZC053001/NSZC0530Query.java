/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NSZ.NSZC053001;

import static com.canon.cusa.s21.api.NSZ.NSZC053001.constant.NSZC053001Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDTMsg;
import business.db.BLLG_CYCLETMsg;
import business.db.CONTR_XS_COPYTMsg;
import business.db.CONTR_XS_COPYTMsgArray;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHDTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsg;
import business.db.DS_CONTR_BLLG_SCHD_MTRTMsgArray;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsg;
import business.db.DS_CONTR_BLLG_SCHD_SMRYTMsgArray;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_PRC_EFFTMsg;
import business.db.DS_CONTR_PRC_EFFTMsgArray;
import business.db.DS_CONTR_PRC_EFF_MTRTMsg;
import business.db.DS_CONTR_PRC_EFF_MTRTMsgArray;
import business.db.SVC_CONTR_BLLGTMsg;
import business.db.SVC_CR_REBILTMsg;
import business.db.SVC_CR_REBILTMsgArray;
import business.db.SVC_CR_REBIL_BASE_BLLGTMsg;
import business.db.SVC_CR_REBIL_BASE_BLLGTMsgArray;
import business.db.SVC_CR_REBIL_DTLTMsg;
import business.db.SVC_CR_REBIL_DTLTMsgArray;
import business.db.SVC_CR_REBIL_MTR_BLLGTMsg;
import business.db.SVC_CR_REBIL_MTR_BLLGTMsgArray;
import business.db.SVC_CR_REBIL_MTR_READTMsg;
import business.db.SVC_CR_REBIL_XS_MTR_BLLGTMsg;
import business.db.SVC_CR_REBIL_XS_MTR_BLLGTMsgArray;
import business.db.SVC_INVTMsg;
import business.db.SVC_INVTMsgArray;
import business.db.SVC_PHYS_MTR_READTMsg;
import business.parts.NSZC053001PMsg;
import business.parts.NSZC053001_xxCrRebilDtlListPMsg;

import com.canon.cusa.s21.common.NSX.NSXC001001.NSXC001001CalcDate;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_TMG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_BLLG_SCHD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Credit Rebill Update API
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 02/01/2016   Hitachi         T.Aoyagi        Create          N/A
 * 03/24/2016   Hitachi         T.Aoyagi        Update          QC#5901
 * 03/25/2016   Hitachi         T.Aoyagi        Update          QC#5435
 * 04/07/2016   Hitachi         T.Aoyagi        Update          QC#6715
 * 04/13/2016   Hitachi         K.Yamada        Update          QC#6512
 * 04/18/2016   Hitachi         T.Aoyagi        Update          QC#7056
 * 04/25/2016   Hitachi         T.Kanasaka      Update          QC#7056
 * 04/26/2016   Hitachi         T.Aoyagi        Update          QC#7578
 * 04/27/2016   Hitachi         K.Yamada        Update          QC#7056
 * 05/10/2016   Hitachi         T.Aoyagi        Update          QC#7734
 * 05/11/2016   Hitachi         K.Kishimoto     Update          QC#7764
 * 09/12/2016   Hitachi         K.Kishimoto     Update          QC#5566
 * 09/25/2017   Hitachi         K.Yamada        Update          QC#21219
 * 2017/09/26   Hitachi         K.Kitachi       Update          QC#21212
 * 2017/10/13   Hitachi         M.Kidokoro      Update          QC#21792
 * 2017/10/13   Hitachi         K.Kojima        Update          QC#21790
 * 2017/10/17   Hitachi         K.Kitachi       Update          QC#21792
 * 2017/10/17   Hitachi         K.Kojima        Update          QC#21795
 * 2017/10/23   Hitachi         K.Kitachi       Update          QC#21966
 * 2017/10/19   Hitachi         T.Tomita        Update          QC#21815
 * 2017/10/24   Hitachi         K.Kitachi       Update          QC#21966
 * 2017/12/19   Hitachi         K.Kojima        Update          QC#22385
 * 2018/03/12   Hitachi         U.Kim           Update          QC#22032
 * 2018/03/15   Hitachi         K.Kojima        Update          QC#22669
 * 2018/03/27   CITS            M.Naito         Update          QC#18672
 * 2018/04/20   Hitachi         K.Kojima        Update          QC#25595
 * 2018/05/30   Hitachi         K.Kitachi       Update          QC#22336
 * 2018/06/05   Hitachi         K.Kojima        Update          QC#21974
 * 2018/06/25   Hitachi         K.Kitachi       Update          QC#22245
 * 2018/07/12   Hitachi         K.Kim           Update          QC#26726
 * 2018/07/18   Hitachi         K.Kojima        Update          QC#26791
 * 2018/08/21   Hitachi         K.Kojima        Update          QC#27769
 * 2018/08/31   Hitachi         K.Kojima        Update          QC#27960
 * 2018/09/05   Hitachi         K.Kishimoto     Update          QC#24555
 * 2019/07/18   Hitachi         K.Kishimoto     Update          QC#51706
 * 2019/09/30   Hitachi         K.Kim           Update          QC#52859
 * 2019/10/01   Hitachi         A.Kohinata      Update          QC#53643
 * 2020/01/08   Hitachi         K.Kim           Update          QC#55170
 * 2020/03/18   Hitachi         K.Kitachi       Update          QC#55693
 * 2020/06/25   Hitachi         K.Kitachi       Update          QC#56211
 * 2021/04/05   CITS            T.Wada          Update          QC#58177-2
 * 2021/06/28   CITS            T.Wada          Update          QC#58177-5
 * 2022/04/04   CITS            E.Sanchez       Update          QC#59914
 * 2022/05/27   Hitachi         K.Kitachi       Update          QC#60121
 * 2022/09/01   CITS            L.Mandanas      Update          QC#58350
 * 2022/09/06   Hitachi         B.Amarsanaa     Update          QC#60049
 * 2022/09/27   CITS            L.Mandanas      Update          QC#58427
 * 2022/10/23   CITS            T.Suzuki        Update          QC#58427
 * 2022/11/02   CITS            L.Mandanas      Update          QC#60652
 * 2022/12/23   CITS            R.Jin           Update          QC#60969
 * 2023/01/16   CITS            R.Jin           Update          QC#58890
 *</pre>
 */
public final class NSZC0530Query extends S21SsmEZDQuerySupport implements ZYPConstant {

    /**
     * Singleton instance.
     */
    private static final NSZC0530Query INSTANCE = new NSZC0530Query();

    /**
     * Constructor.
     */
    private NSZC0530Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSAL0530Query
     */
    public static NSZC0530Query getInstance() {
        return INSTANCE;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return SVC_CR_REBILTMsg
     */
    public SVC_CR_REBILTMsg getSvcCrRebilTMsgByIncdtId(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("custIncdtId01", pMsg.custIncdtId.getValue());
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_CR_REBILTMsgArray svcCrRebilTMsgArray = (SVC_CR_REBILTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (svcCrRebilTMsgArray.length() > 0) {
            return svcCrRebilTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return SVC_INVTMsg
     */
    public SVC_INVTMsg getSvcInvTMsg(String glblCmpyCd, String svcInvNum) {

        SVC_INVTMsg inMsg = new SVC_INVTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcInvNum01", svcInvNum);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_INVTMsgArray svcInvTMsgArray = (SVC_INVTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (svcInvTMsgArray.length() > 0) {
            return svcInvTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return SVC_CR_REBILTMsg
     */
    public SVC_CR_REBILTMsg getSvcCrRebilTMsg(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        return (SVC_CR_REBILTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return SVC_CR_REBILTMsg
     */
    public SVC_CR_REBILTMsg getSvcCrRebilTMsgForUpdate(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcCrRebilPk, pMsg.svcCrRebilPk);
        return (SVC_CR_REBILTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @return SVC_CR_REBIL_DTLTMsg
     */
    public SVC_CR_REBIL_DTLTMsg getSvcCrRebilDtlTMsgForUpdate(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBIL_DTLTMsg inMsg = new SVC_CR_REBIL_DTLTMsg();
        setValue(inMsg.glblCmpyCd, pMsg.glblCmpyCd);
        setValue(inMsg.svcCrRebilDtlPk, svcCrRebilDtlPk);
        return (SVC_CR_REBIL_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContrTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrPk) {

        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_DTLTMsg
     */
    public DS_CONTR_DTLTMsg getDsContrDtlTMsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @return SVC_CR_REBILTMsg
     */
    public SVC_CR_REBILTMsg getSvcCrRebilTMsgByIncdtLineIdForUpdate(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        SVC_CR_REBILTMsg inMsg = new SVC_CR_REBILTMsg();
        inMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("custIncdtId01", pMsg.custIncdtId.getValue());
        inMsg.setConditionValue("custIncdtLineId01", pMsg.custIncdtLineId.getValue());
        inMsg.setSQLID("002");

        SVC_CR_REBILTMsgArray svcCrRebilTMsgArray = (SVC_CR_REBILTMsgArray) S21ApiTBLAccessor.findByConditionForUpdate(inMsg);

        if (svcCrRebilTMsgArray.length() > 0) {
            return svcCrRebilTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcPhysMtrReadPk BigDecimal
     * @return SVC_PHYS_MTR_READTMsg
     */
    public SVC_PHYS_MTR_READTMsg getSvcPhysMtrReadTMsg(String glblCmpyCd, BigDecimal svcPhysMtrReadPk) {

        SVC_PHYS_MTR_READTMsg inMsg = new SVC_PHYS_MTR_READTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcPhysMtrReadPk, svcPhysMtrReadPk);
        return (SVC_PHYS_MTR_READTMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return String
     */
    public String getDsContrCatgCd(String glblCmpyCd, String svcInvNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        return (String) getSsmEZDClient().queryObject("getDsContrCatgCd", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcInvLineForBase(String glblCmpyCd, String svcInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        // START 04/07/2016 T.Aoyagi [QC#6715, ADD]
        ssmParam.put("dsContrCatgCdFlt", DS_CONTR_CATG.FLEET);
        ssmParam.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        // END 04/07/2016 T.Aoyagi [QC#6715, ADD]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcInvLineForBase", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcInvLineForBllgMtr(String glblCmpyCd, String svcInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcInvLineForBllgMtr", ssmParam).getResultObject();
    }

    // START 04/18/2016 T.Aoyagi [QC#7056, MOD]
    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @param serNum String
     * @param bllgMtrLbCd String
     * @param bllgPerFromDt String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcInvLineForXsMtr(String glblCmpyCd, String svcInvNum, String serNum, String bllgMtrLbCd, String bllgPerFromDt) {
        // START 2017/10/23 K.Kitachi [QC#21966, ADD]
        // START 2017/10/24 K.Kitachi [QC#21966, MOD]
        if (!hasValue(bllgMtrLbCd) || !hasValue(bllgPerFromDt)) {
        // END 2017/10/24 K.Kitachi [QC#21966, MOD]
            return new ArrayList<Map<String, Object>>();
        }
        // END 2017/10/23 K.Kitachi [QC#21966, ADD]
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("serNum", serNum);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmParam.put("bllgPerFromDt", bllgPerFromDt);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcInvLineForXsMtr", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcInvLineForMtrRead(String glblCmpyCd, String svcInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcInvLineForMtrRead", ssmParam).getResultObject();
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, MOD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilMtrReadPk BigDecimal
     * @param prevFlg String
     * @return Map<String, BigDecimal>
     */
    public Map<String, BigDecimal> getPrevNextMtrRead(String glblCmpyCd, BigDecimal svcCrRebilMtrReadPk, String prevFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilMtrReadPk", svcCrRebilMtrReadPk);
        ssmParam.put("prevFlg", prevFlg);
        return (Map<String, BigDecimal>) getSsmEZDClient().queryObject("getPrevNextMtrRead", ssmParam).getResultObject();
    }

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcInvLineForAddlChrg(String glblCmpyCd, BigDecimal svcInvLinePk, String addlChrgInvTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prntSvcInvLinePk", svcInvLinePk);
        ssmParam.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.ADDITIONAL_CHARGE);
        ssmParam.put("addlChrgInvTpCd", addlChrgInvTpCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcInvLineForAddlChrg", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getAddlChrgNotExistSvcInvLine(String glblCmpyCd, BigDecimal svcInvLinePk, String addlChrgInvTpCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvLinePk", svcInvLinePk);
        ssmParam.put("addlChrgInvTpCd", addlChrgInvTpCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAddlChrgNotExistSvcInvLine", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcInvLinePk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getBllgSchdInfoForCalcAddlChrg(String glblCmpyCd, BigDecimal svcInvLinePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvLinePk", svcInvLinePk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBllgSchdInfoForCalcAddlChrg", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param effFromDt String
     * @param dsContrAddlChrgPk BigDecimal
     * @return String
     */
    public String getPreCalcAddlNextBllgDt(String glblCmpyCd, String effFromDt, BigDecimal dsContrAddlChrgPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrAddlChrgPk", dsContrAddlChrgPk);
        ssmParam.put("effFromDt", effFromDt);
        String preCalcThruDt = (String) getSsmEZDClient().queryObject("getPreCalcAddlNextBllgDt",  ssmParam).getResultObject();
        if (!hasValue(preCalcThruDt)) {
            preCalcThruDt ="";
        }
        return preCalcThruDt;
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilMtrReadPk BigDecimal
     * @param prevFlg String
     * @return Map<String, Object>
     */
    public Map<String, Object> getSvcInvForPrevNextMtrRead(String glblCmpyCd, BigDecimal svcCrRebilMtrReadPk, String prevFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilMtrReadPk", svcCrRebilMtrReadPk);
        ssmParam.put("prevFlg", prevFlg);
        // START 2018/03/12 U.Kim [QC#22032, ADD]
        ssmParam.put("invTpCd", INV_TP.CREDIT_MEMO);
        // END 2018/03/12 U.Kim [QC#22032, ADD]
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcInvForPrevNextMtrRead", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcInvNum String
     * @return SVC_CR_REBIL_DTLTMsg
     */
    public SVC_CR_REBIL_DTLTMsg getSvcCrRebilDtlTMsg(String glblCmpyCd, BigDecimal svcCrRebilPk, String svcInvNum) {

        SVC_CR_REBIL_DTLTMsg inMsg = new SVC_CR_REBIL_DTLTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilPk01", svcCrRebilPk);
        inMsg.setConditionValue("origSvcInvNum01", svcInvNum);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_CR_REBIL_DTLTMsgArray svcCrRebilDtlTMsgArray = (SVC_CR_REBIL_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (svcCrRebilDtlTMsgArray.length() > 0) {
            return svcCrRebilDtlTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return svcCrRebilDtlTMsgArray
     */
    public SVC_CR_REBIL_DTLTMsgArray getSvcCrRebilDtlTMsg(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        SVC_CR_REBIL_DTLTMsg inMsg = new SVC_CR_REBIL_DTLTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilPk01", svcCrRebilPk);
        inMsg.setSQLID("002");
        return (SVC_CR_REBIL_DTLTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @return SVC_CR_REBIL_MTR_BLLGTMsg
     */
    public SVC_CR_REBIL_MTR_BLLGTMsg getSvcCrRebilMtrBllgTMsg(String glblCmpyCd, BigDecimal svcCrRebilDtlPk, String bllgMtrLbCd) {

        SVC_CR_REBIL_DTLTMsg inMsg = new SVC_CR_REBIL_DTLTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilDtlPk01", svcCrRebilDtlPk);
        inMsg.setConditionValue("bllgMtrLbCd01", bllgMtrLbCd);
        inMsg.setMaxCount(1);
        inMsg.setSQLID("001");

        SVC_CR_REBIL_MTR_BLLGTMsgArray svcCrRebilMtrBllgTMsgArray = (SVC_CR_REBIL_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);

        if (svcCrRebilMtrBllgTMsgArray.length() > 0) {
            return svcCrRebilMtrBllgTMsgArray.no(0);
        } else {
            return null;
        }
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @return SVC_CR_REBIL_BASE_BLLGTMsgArray
     */
    public SVC_CR_REBIL_BASE_BLLGTMsgArray getSvcCrRebilBaseBllgTMsg(String glblCmpyCd, BigDecimal svcCrRebilDtlPk) {

        SVC_CR_REBIL_BASE_BLLGTMsg inMsg = new SVC_CR_REBIL_BASE_BLLGTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilDtlPk01", svcCrRebilDtlPk);
        inMsg.setSQLID("001");
        return (SVC_CR_REBIL_BASE_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilBaseBllgPk BigDecimal
     * @return SVC_CR_REBIL_BASE_BLLGTMsg
     */
    public SVC_CR_REBIL_BASE_BLLGTMsg getSvcCrRebilBaseBllgTMsgForUpdate(String glblCmpyCd, BigDecimal svcCrRebilBaseBllgPk) {

        SVC_CR_REBIL_BASE_BLLGTMsg inMsg = new SVC_CR_REBIL_BASE_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcCrRebilBaseBllgPk, svcCrRebilBaseBllgPk);
        return (SVC_CR_REBIL_BASE_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @return SVC_CR_REBIL_MTR_BLLGTMsgArray
     */
    public SVC_CR_REBIL_MTR_BLLGTMsgArray getSvcCrRebilMtrBllgTMsg(String glblCmpyCd, BigDecimal svcCrRebilDtlPk) {

        SVC_CR_REBIL_MTR_BLLGTMsg inMsg = new SVC_CR_REBIL_MTR_BLLGTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilDtlPk01", svcCrRebilDtlPk);
        inMsg.setSQLID("002");
        return (SVC_CR_REBIL_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilXsMtrBllgPk BigDecimal
     * @return SVC_CR_REBIL_XS_MTR_BLLGTMsg
     */
    public SVC_CR_REBIL_XS_MTR_BLLGTMsg getSvcCrRebilXsMtrBllgTMsgForUpdate(String glblCmpyCd, BigDecimal svcCrRebilXsMtrBllgPk) {

        SVC_CR_REBIL_XS_MTR_BLLGTMsg inMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcCrRebilXsMtrBllgPk, svcCrRebilXsMtrBllgPk);
        return (SVC_CR_REBIL_XS_MTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilMtrReadPk BigDecimal
     * @return SVC_CR_REBIL_MTR_READTMsg
     */
    public SVC_CR_REBIL_MTR_READTMsg getSvcCrRebilMtrReadBllgTMsgForUpdate(String glblCmpyCd, BigDecimal svcCrRebilMtrReadPk) {

        SVC_CR_REBIL_MTR_READTMsg inMsg = new SVC_CR_REBIL_MTR_READTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcCrRebilMtrReadPk, svcCrRebilMtrReadPk);
        return (SVC_CR_REBIL_MTR_READTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilDtlPk BigDecimal
     * @return SVC_CR_REBIL_XS_MTR_BLLGTMsgArray
     */
    public SVC_CR_REBIL_XS_MTR_BLLGTMsgArray getSvcCrRebilXsMtrBllgTMsg(String glblCmpyCd, BigDecimal svcCrRebilDtlPk) {

        SVC_CR_REBIL_XS_MTR_BLLGTMsg inMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilDtlPk01", svcCrRebilDtlPk);
        inMsg.setSQLID("001");
        return (SVC_CR_REBIL_XS_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsgArray
     */
    public DS_CONTR_PRC_EFFTMsgArray getDsContrPrcEffTMsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setSQLID("001");
        return (DS_CONTR_PRC_EFFTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsgArray
     */
    public DS_CONTR_BLLG_MTRTMsgArray getDsContrBllgMtrTMsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {

        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrDtlPk01", dsContrDtlPk);
        inMsg.setSQLID("001");
        return (DS_CONTR_BLLG_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_PRC_EFFTMsg
     */
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEffTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {

        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_MTRTMsg
     */
    public DS_CONTR_BLLG_MTRTMsg getDsContrBllgMtrTMsgForUpdate(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {

        DS_CONTR_BLLG_MTRTMsg inMsg = new DS_CONTR_BLLG_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
        return (DS_CONTR_BLLG_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    // START 04/11/2016 T.Aoyagi [QC#6715, MOD]
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @param dsContrDtlTp String
     * @return List<Map<String, Object>>
     */
    // START 04/26/2016 T.Aoyagi [QC#7578, MOD]
    public List<Map<String, Object>> getSchdAndDsContr(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk, String dsContrDtlTp) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCd", dsContrDtlTp);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSchdAndDsContr", ssmParam).getResultObject();
    }
    // END 04/26/2016 T.Aoyagi [QC#7578, MOD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @param dsContrDtlTp String
     * @return Map<String, Object>
     */
    public Map<String, Object> getSvcInvAndDsContr(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk, String dsContrDtlTp) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCd", dsContrDtlTp);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getSvcInvAndDsContr", ssmParam).getResultObject();
    }
    // END 04/11/2016 T.Aoyagi [QC#6715, MOD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getBaseDealAmt(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("getBaseDealAmt", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getMtrChrgDealAmt(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("getMtrChrgDealAmt", ssmParam).getResultObject();
    }

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getAddlChrgDealAmt(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (BigDecimal) getSsmEZDClient().queryObject("getAddlChrgDealAmt", ssmParam).getResultObject();
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcContrMtrInfo(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcContrMtrInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcContrXsMtrInfo(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        // START 2016/03/24 T.Aoyagi [QC#5901, ADD]
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        // END 2016/03/24 T.Aoyagi [QC#5901, ADD]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcContrXsMtrInfo", ssmParam).getResultObject();
    }

    // START 2018/03/27 M.Naito [QC#18672, ADD]
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param svcCrRebilDtlPk BigDecimal
     * @param addlChrgInvTpCd String
     * @param prntSvcInvLinePk BigDecimal
     * @return List<Map<String, Object>>
     */
    // START 2022/05/27 K.Kitachi [QC#60121, MOD]
//    public List<Map<String, Object>> getCrRebilAddlInfoForUpdate(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk, String addlChrgInvTpCd) {
    public List<Map<String, Object>> getCrRebilAddlInfoForUpdate(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal svcCrRebilDtlPk, String addlChrgInvTpCd, BigDecimal prntSvcInvLinePk) {
    // END 2022/05/27 K.Kitachi [QC#60121, MOD]

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("addlChrgInvTpCd", addlChrgInvTpCd);
        // START 2022/05/27 K.Kitachi [QC#60121, ADD]
        ssmParam.put("prntSvcInvLinePk", prntSvcInvLinePk);
        // END 2022/05/27 K.Kitachi [QC#60121, ADD]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getCrRebilAddlInfoForUpdate", ssmParam).getResultObject();
    }
    // END 2018/03/27 M.Naito [QC#18672, ADD]

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getDsContrDtlBySvcCrRebilPk(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlBySvcCrRebilPk", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param baseBllgFromDt String
     * @param serNum String
     * @param dsContrDtlPk BigDecimal
     * @return BigDecimal
     */
    // START 2022/04/04 E.Sanchez [QC#59914,MOD]
//    public BigDecimal getCrRebilBasePk(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, String baseBllgFromDt, String serNum) {
    public BigDecimal getCrRebilBasePk(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, String baseBllgFromDt, String serNum, BigDecimal dsContrDtlPk) {
    // END 2022/04/04 E.Sanchez [QC#59914,MOD]

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("baseBllgFromDt", baseBllgFromDt);
        ssmParam.put("serNum", serNum);
        // START 2022/04/04 E.Sanchez [QC#59914,ADD]
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        // END 2022/04/04 E.Sanchez [QC#59914,ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("getCrRebilBasePk", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @param oldXsCopyQty BigDecimal
     * @param serNum String
     * @return BigDecimal
     */
    public BigDecimal getCrRebilXsMtrPk(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, String bllgMtrLbCd, BigDecimal oldXsCopyQty, String serNum) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmParam.put("oldXsCopyQty", oldXsCopyQty);
        ssmParam.put("serNum", serNum);
        return (BigDecimal) getSsmEZDClient().queryObject("getCrRebilXsMtrPk", ssmParam).getResultObject();
    }

    // START 2016/04/25 T.Kanasaka [QC#7056, MOD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param physMtrLbCd String
     * @param serNum String
     * @return BigDecimal
     */
    public BigDecimal getCrRebilMtrReadPk(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, String physMtrLbCd, String serNum) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("physMtrLbCd", physMtrLbCd);
        ssmParam.put("serNum", serNum);
        return (BigDecimal) getSsmEZDClient().queryObject("getCrRebilMtrReadPk", ssmParam).getResultObject();
    }
    // END 2016/04/25 T.Kanasaka [QC#7056, MOD]

    /**
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getCrRebilMtrInfo(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilPk", pMsg.svcCrRebilPk.getValue());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getCrRebilMtrInfo", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getUpdTargetMtrRead(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getUpdTargetMtrRead", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getUpdFirstMtrRead(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, BigDecimal dsContrDtlPk) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getUpdFirstMtrRead", ssmParam).getResultObject();
    }

    // START 03/25/2016 T.Aoyagi [QC#5435, ADD]
    // mod start 2019/10/01 QC#53643
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return Map<String, Object>
     */
    //public BigDecimal getInvTotDealNetAmt(String glblCmpyCd, BigDecimal svcCrRebilPk) {
    public Map<String, Object> getInvTotDealNetAmt(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        //return (BigDecimal) getSsmEZDClient().queryObject("getInvTotDealNetAmt", ssmParam).getResultObject();
        return (Map<String, Object>) getSsmEZDClient().queryObject("getInvTotDealNetAmt", ssmParam).getResultObject();
    }
    // mod end 2019/10/01 QC#53643
    // add start 2022/9/01 QC#60049
    public Map<String, Object> getCrRblNoticationEmailInfo(String glblCmpyCd, String custIncdtId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);     
        ssmParam.put("custIncdtId", custIncdtId);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getCrRblNoticationEmailInfo", ssmParam).getResultObject();
    }
    // add end 2022/9/01 QC#60049
    /**
     * @param msgMap S21ApiMessageMap
     * @return BigDecimal
     */
    public BigDecimal getSvcRgPk(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilPk", pMsg.svcCrRebilPk.getValue());
        ssmParam.put("slsDt", pMsg.slsDt.getValue());
        ssmParam.put("maxDate", MAX_DATE);
        return (BigDecimal) getSsmEZDClient().queryObject("getSvcRgPk", ssmParam).getResultObject();
    }
    // END 03/25/2016 T.Aoyagi [QC#5435, ADD]

    // add start 2016/04/13 CSA Defect#6512
    /**
     * getTotCrAmt
     * @param msgMap S21ApiMessageMap
     * @return BigDecimal
     */
    public BigDecimal getTotCrAmt(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilPk", pMsg.svcCrRebilPk.getValue());
        return (BigDecimal) getSsmEZDClient().queryObject("getTotCrAmt", ssmParam).getResultObject();
    }

    /**
     * getContrInfo
     * @param msgMap S21ApiMessageMap
     * @return Map<String, String>
     */
    public Map<String, String> getContrInfo(S21ApiMessageMap msgMap) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilPk", pMsg.svcCrRebilPk.getValue());
        return (Map<String, String>) getSsmEZDClient().queryObject("getContrInfo", ssmParam).getResultObject();
    }
    // add end 2016/04/13 CSA Defect#6512

    // START 04/18/2016 T.Aoyagi [QC#7056, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @return Map<String, Object>
     */
    public Map<String, Object> getBaseInfoForAggLine(S21ApiMessageMap msgMap, String svcInvNum) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getBaseInfoForAggLine", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getBllgMtrInfoForAggLine(S21ApiMessageMap msgMap, String svcInvNum) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getBllgMtrInfoForAggLine", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @param mtrLbCd String
     * @param bllgSchdFromDt String
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getXsMtrInfoForAggLine(S21ApiMessageMap msgMap, String svcInvNum, String mtrLbCd, String bllgSchdFromDt) {

        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvNum", svcInvNum);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
        ssmParam.put("mtrLbCd", mtrLbCd);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getXsMtrInfoForAggLine", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param mtrBllgFromDt String
     * @return BigDecimal
     */
    public BigDecimal getCrRebilMtrBllgPk(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, BigDecimal dsContrBllgMtrPk, String mtrBllgFromDt) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("mtrBllgFromDt", mtrBllgFromDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getCrRebilMtrBllgPk", ssmParam).getResultObject();
    }
    // END 04/18/2016 T.Aoyagi [QC#7056, ADD]

    // add start 2016/04/27 CSA Defect#7056
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getAggLineXsRate(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAggLineXsRate", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @param bllgMtrLbCd String
     * @param tierCnt BigDecimal
     * @param newXsMtrAmtRate BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getAggMachDiffRate(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk, String bllgMtrLbCd, BigDecimal tierCnt, BigDecimal newXsMtrAmtRate) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrCatgCdAgg", DS_CONTR_CATG.AGGREGATE);
        ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
        ssmParam.put("tierCnt", tierCnt);
        ssmParam.put("newXsMtrAmtRate", newXsMtrAmtRate);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getAggMachDiffRate", ssmParam).getResultObject();
    }
    // add end 2016/04/27 CSA Defect#7056
    // add start 2016/04/28 CSA Defect#7056
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcCrXsMtrBllg(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcCrXsMtrBllg", ssmParam).getResultObject();
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param svcCrRebilDtlPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getSvcCrBaseBllg(S21ApiMessageMap msgMap, BigDecimal svcCrRebilDtlPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilDtlPk", svcCrRebilDtlPk);
        ssmParam.put("dsContrDtlTpCdAgg", DS_CONTR_DTL_TP.AGGREGATE);
        ssmParam.put("dsContrDtlTpCdFlt", DS_CONTR_DTL_TP.FLEET);
        // START 2019/09/30 [QC#52859, ADD]
        ssmParam.put("dsContrDtlTpCdAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        // END 2019/09/30 [QC#52859, ADD]
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcCrBaseBllg", ssmParam).getResultObject();
    }
    // add end 2016/04/28 CSA Defect#7056
    // START 05/10/2016 T.Aoyagi [QC#7734, ADD]
    /**
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @param curSvcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getRegisteredSvcCrRebilPk(S21ApiMessageMap msgMap, String svcInvNum, BigDecimal curSvcCrRebilPk) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("origSvcInvNum", svcInvNum);
        ssmParam.put("svcCrRebilStsCd", SVC_CR_REBIL_STS.CANCELLED);
        ssmParam.put("svcCrRebilPk", curSvcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getRegisteredSvcCrRebilPk", ssmParam).getResultObject();
    }
    // END 05/10/2016 T.Aoyagi [QC#7734, ADD]

    // Add Start 05/11/2016 <QC#7764>
    /**
     * @param msgMap S21ApiMessageMap
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getAdjMtrGrpSqInfo(S21ApiMessageMap msgMap) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcCrRebilPk", pMsg.svcCrRebilPk.getValue());
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAdjMtrGrpSqInfo", ssmParam).getResultObject();
    }
    // Add End 05/11/2016 <QC#7764>

    // START 2018/08/21 K.Kojima [QC#27769,DEL]
    // // Add Start 09/12/2016 <QC#5566>
    // public List<Map<String, Object>> getApproverList(SVC_CR_REBILTMsg  svcCrRebilTMsg) {
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put("glblCmpyCd", svcCrRebilTMsg.glblCmpyCd.getValue());
    //     ssmParam.put("svcContrBrCd", svcCrRebilTMsg.svcContrBrCd.getValue());
    //     ssmParam.put("psnCd", svcCrRebilTMsg.custIncdtAsgPsnCd.getValue());
    //     ssmParam.put("firstOrgCd", ZYPCodeDataUtil.getVarCharConstValue(CONTR_BR_FIRST_ORG_CD, svcCrRebilTMsg.glblCmpyCd.getValue()));
    //     return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getApproverList", ssmParam).getResultObject();
    // }
    // // Add End   09/12/2016 <QC#5566>
    // END 2018/08/21 K.Kojima [QC#27769,DEL]

    // add start 09/25/2017 QC#21219
    public List<Map<String, BigDecimal>> fillupParameter(S21ApiMessageMap msgMap, NSZC053001_xxCrRebilDtlListPMsg inPMsg) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvNum", inPMsg.origSvcInvNum.getValue());
        ssmParam.put("meterCharge", SVC_INV_CHRG_TP.METER_CHARGE);
        ssmParam.put("serNum", inPMsg.serNum.getValue());
        ssmParam.put("bllgMtrLbCd", inPMsg.bllgMtrLbCd.getValue());
        ssmParam.put("xsMtrCopyQty", inPMsg.oldXsCopyQty.getValue());
        ssmParam.put("xsMtrAmtRate", inPMsg.oldXsMtrAmtRate.getValue());
        return (List<Map<String, BigDecimal>>) getSsmEZDClient().queryObjectList("getOrigInvXsInfo", ssmParam).getResultObject();
    }
    // add end 09/25/2017 QC#21219

    // START 2017/09/26 K.Kitachi [QC#21212, ADD]
    /**
     * getTrgtSvcInvNumList
     * @param msgMap S21ApiMessageMap
     * @param svcInvNum String
     * @return List<String>
     */
    public List<String> getTrgtSvcInvNumList(S21ApiMessageMap msgMap, String svcInvNum) {
        NSZC053001PMsg pMsg = (NSZC053001PMsg) msgMap.getPmsg();
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", pMsg.glblCmpyCd.getValue());
        ssmParam.put("svcInvNum", svcInvNum);
        // START 2017/10/13 K.Kojima [QC#21790,ADD]
        ssmParam.put("dsBllgSchdTpRegular", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("dsBllgSchdTpRebill", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        // END 2017/10/13 K.Kojima [QC#21790,ADD]
        // START 2018/03/27 M.Naito [QC#18672, ADD]
        ssmParam.put("svcCrRebilStsCd", SVC_CR_REBIL_STS.CANCELLED);
        // END 2018/03/27 M.Naito [QC#18672, ADD]
        return (List<String>) getSsmEZDClient().queryObjectList("getTrgtSvcInvNumList", ssmParam).getResultObject();
    }

    // Mod Start 2017/10/19 QC#21815
    /**
     * getPrcEffForBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param svcCrRebilPk BigDecimal
     * @return Map<String, Object>
     */
//    public Map<String, Object> getPrcEffForBase(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
    public Map<String, Object> getPrcEffForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getPrcEffForBase", ssmParam).getResultObject();
    }

    /**
     * getPrcEffForUsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param svcCrRebilPk BigDecimal
     * @return Map<String, Object>
     */
//    public Map<String, Object> getPrcEffForUsg(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
    public Map<String, Object> getPrcEffForUsg(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getPrcEffForUsg", ssmParam).getResultObject();
    }
    // Mod End 2017/10/19 QC#21815

    // START 2022/10/23 T.Suzuki [QC#58427, ADD]
    /**
     * getPrcEffForBase
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getCurrentPrcEffForBase(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("baseChrgFlg", FLG_ON_Y);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getCurrentPrcEffForBase", ssmParam).getResultObject();
    }

    /**
     * getCurrentPrcEffForUsg
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return Map<String, Object>
     */
    public Map<String, Object> getCurrentPrcEffForUsg(String glblCmpyCd, String slsDt, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("usgChrgFlg", FLG_ON_Y);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getCurrentPrcEffForUsg", ssmParam).getResultObject();
    }
    // END 2022/10/23 T.Suzuki [QC#58427, ADD]

    /**
     * getXsCopy
     * @param glblCmpyCd String
     * @param dsContrBllgMtrPk BigDecimal
     * @return CONTR_XS_COPYTMsgArray
     */
    public CONTR_XS_COPYTMsgArray getXsCopy(String glblCmpyCd, BigDecimal dsContrBllgMtrPk) {
        CONTR_XS_COPYTMsg inMsg = new CONTR_XS_COPYTMsg();
        inMsg.setSQLID("002");
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        return (CONTR_XS_COPYTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    /**
     * getAggLinePk
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getAggLinePk(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("aggregate", DS_CONTR_DTL_TP.AGGREGATE);
        return (BigDecimal) getSsmEZDClient().queryObject("getAggLinePk", ssmParam).getResultObject();
    }

    /**
     * getCodeTbl
     * @param tblName String
     * @param glblCmpyCd String
     * @param codeVal String
     * @return EZDTMsg
     */
    public EZDTMsg getCodeTbl(String tblName, String glblCmpyCd, String codeVal) {
        Map<String, String> key = new HashMap<String, String>();
        key.put("GLBL_CMPY_CD", glblCmpyCd);
        key.put(tblName + "_CD", codeVal);
        return S21CodeTableAccessor.findByKey(tblName, key);
    }

    /**
     * getDsContrPk
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal getDsContrPk(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getDsContrPk", ssmParam).getResultObject();
    }

    /**
     * getDsContrTMsg
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return DS_CONTRTMsg
     */
    public DS_CONTRTMsg getDsContrTMsg(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) S21ApiTBLAccessor.findByKey(inMsg);
    }

    /**
     * getCrRebilBase
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param targetDt String
     * @return BigDecimal
     */
    public BigDecimal getCrRebilBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String targetDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("targetDt", targetDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getCrRebilBase", ssmParam).getResultObject();
    }

    // START 2020/03/18 K.Kitachi [QC#55693, DEL]
//    /**
//     * getCrRebilUsage
//     * @param glblCmpyCd String
//     * @param svcCrRebilPk BigDecimal
//     * @param dsContrDtlPk BigDecimal
//     * @param dsContrBllgMtrPk BigDecimal
//     * @param contrXsCopyPk BigDecimal
//     * @return Map<String, Object>
//     */
//    public Map<String, Object> getCrRebilUsage(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal contrXsCopyPk) {
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
//        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
//        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
//        ssmParam.put("contrXsCopyPk", contrXsCopyPk);
//        return (Map<String, Object>) getSsmEZDClient().queryObject("getCrRebilUsage", ssmParam).getResultObject();
//    }
    // END 2020/03/18 K.Kitachi [QC#55693, DEL]

    /**
     * countCrRebilBaseBllg
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countCrRebilBaseBllg(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countCrRebilBaseBllg", ssmParam).getResultObject();
    }

    /**
     * countCrRebilMtrBllg
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countCrRebilMtrBllg(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countCrRebilMtrBllg", ssmParam).getResultObject();
    }

    /**
     * countCrRebilXsMtrBllg
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countCrRebilXsMtrBllg(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countCrRebilXsMtrBllg", ssmParam).getResultObject();
    }
    // END 2017/09/26 K.Kitachi [QC#21212, ADD]

    // START 2017/10/17 K.Kitachi [QC#21792, ADD]
    /**
     * getTrgtPrcEffForUsgList
     * @param glblCmpyCd String
     * @param slsDt String
     * @param svcCrRebilPk BigDecimal
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getTrgtPrcEffForUsgList(String glblCmpyCd, String slsDt, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTrgtPrcEffForUsgList", ssmParam).getResultObject();
    }

    /**
     * getPrcEffTMsgArray
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_PRC_EFF_MTRTMsgArray
     */
    public DS_CONTR_PRC_EFF_MTRTMsgArray getPrcEffTMsgArray(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_PRC_EFF_MTRTMsg inMsg = new DS_CONTR_PRC_EFF_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (DS_CONTR_PRC_EFF_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2017/10/17 K.Kitachi [QC#21792, ADD]

    // START 2018/04/20 K.Kojima [QC#25595,ADD]
    /**
     * getDsContrBllgSchdMtrTMsgArray
     * @param glblCmpyCd String
     * @param dsContrBllgSchdSmryPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_MTRTMsgArray
     */
    public DS_CONTR_BLLG_SCHD_MTRTMsgArray getDsContrBllgSchdMtrTMsgArray(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgSchdSmryPk01", dsContrBllgSchdSmryPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (DS_CONTR_BLLG_SCHD_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END 2018/04/20 K.Kojima [QC#25595,ADD]

    // START 2017/10/17 K.Kojima [QC#21795,ADD]
    /**
     * countSvcCrRebilBaseBillg
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countSvcCrRebilBaseBillg(String glblCmpyCd, BigDecimal svcCrRebilPk, List<BigDecimal> svcCrRebilDtlPkList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPkList", svcCrRebilDtlPkList);
        return (BigDecimal) getSsmEZDClient().queryObject("countSvcCrRebilBaseBillg", ssmParam).getResultObject();
    }

    /**
     * countSvcCrRebilMtrBillg
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return BigDecimal
     */
    public BigDecimal countSvcCrRebilMtrBillg(String glblCmpyCd, BigDecimal svcCrRebilPk, List<BigDecimal> svcCrRebilDtlPkList) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("svcCrRebilDtlPkList", svcCrRebilDtlPkList);
        return (BigDecimal) getSsmEZDClient().queryObject("countSvcCrRebilMtrBillg", ssmParam).getResultObject();
    }
    // END 2017/10/17 K.Kojima [QC#21795,ADD]
    // Add Start 2017/10/19 QC#21815
    public List<Map<String, Object>> getTargetRbilSchdForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTargetRbilSchdForBase", ssmParam).getResultObject();
    }

    public BigDecimal getTargetPrcEffPkForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String targetDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("targetDt", targetDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getTargetPrcEffPkForBase", ssmParam).getResultObject();
    }

    public BigDecimal getTargetTopSchdPkForBase(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrPrcEffSqNum, String targetDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrPrcEffSqNum", dsContrPrcEffSqNum);
        ssmParam.put("targetDt", targetDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getTargetTopSchdPkForBase", ssmParam).getResultObject();
    }

    public DS_CONTR_BLLG_SCHD_SMRYTMsg getDsContrBllgSchdSmryForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        if (!hasValue(dsContrBllgSchdSmryPk)) {
            return null;
        }
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdSmryPk, dsContrBllgSchdSmryPk);
        return (DS_CONTR_BLLG_SCHD_SMRYTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    public DS_CONTR_BLLG_SCHDTMsg getDsContrBllgSchdForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        if (!hasValue(dsContrBllgSchdPk)) {
            return null;
        }
        DS_CONTR_BLLG_SCHDTMsg inMsg = new DS_CONTR_BLLG_SCHDTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdPk, dsContrBllgSchdPk);
        return (DS_CONTR_BLLG_SCHDTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    public Map<String, Object> getRbilTopSchdAmtForBase(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk, String startDt, String endDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("startDt", startDt);
        ssmParam.put("endDt", endDt);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getRbilTopSchdAmtForBase", ssmParam).getResultObject();
    }

    public BigDecimal getRbilPrcEffAmtForBase(String glblCmpyCd, BigDecimal dsContrPrcEffPk, String startDt, String endDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("startDt", startDt);
        ssmParam.put("endDt", endDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getRbilPrcEffAmtForBase", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getTargetRbilSchdForUsage(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REBILL_CREDIT_AND_REBILL);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTargetRbilSchdForUsage", ssmParam).getResultObject();
    }

    public BigDecimal getTargetPrcEffPkForUsage(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String targetDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("targetDt", targetDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getTargetPrcEffPkForUsage", ssmParam).getResultObject();
    }

    public BigDecimal getTargetTopSchdPkForUsage(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrPrcEffSqNum, String targetDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrPrcEffSqNum", dsContrPrcEffSqNum);
        ssmParam.put("targetDt", targetDt);
        return (BigDecimal) getSsmEZDClient().queryObject("getTargetTopSchdPkForUsage", ssmParam).getResultObject();
    }

    public DS_CONTR_BLLG_SCHD_MTRTMsgArray getBllgSchdMtrTMsgArray(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk, BigDecimal dsContrBllgMtrPk) {
        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrBllgSchdSmryPk01", dsContrBllgSchdSmryPk);
        inMsg.setConditionValue("dsContrBllgMtrPk01", dsContrBllgMtrPk);
        inMsg.setSQLID("002");
        return (DS_CONTR_BLLG_SCHD_MTRTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // START 2018/08/27 [QC#24555, ADD]
    public SVC_CR_REBIL_XS_MTR_BLLGTMsgArray getBllgSchdMtrTMsgArray(String glblCmpyCd, BigDecimal svcCrRebilMtrBllgPk) {
        SVC_CR_REBIL_XS_MTR_BLLGTMsg inMsg = new SVC_CR_REBIL_XS_MTR_BLLGTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("svcCrRebilMtrBllgPk01", svcCrRebilMtrBllgPk);
        inMsg.setSQLID("002");
        return (SVC_CR_REBIL_XS_MTR_BLLGTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }
    // END  2018/08/27 [QC#24555, ADD]

    public List<BigDecimal> getDsContrDtlBySvcCrRebilPkForSchdApi(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.AGGREGATE);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlBySvcCrRebilPkForSchdApi", ssmParam).getResultObject();
    }

    public BigDecimal getSchdAmtForAggLine(String glblCmpyCd, BigDecimal dsContrBllgSchdPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdPk", dsContrBllgSchdPk);
        return (BigDecimal) getSsmEZDClient().queryObject("getSchdAmtForAggLine", ssmParam).getResultObject();
    }

    public BigDecimal getDtlTermDealAmt(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        List<String> dsContrPrcEffStsCdList = new ArrayList<String>();
        dsContrPrcEffStsCdList.add(DS_CONTR_DTL_STS.CANCELLED);
        return (BigDecimal) getSsmEZDClient().queryObject("getDtlTermDealAmt", ssmParam).getResultObject();
    }

    public String getNextDsContrBllgSchdSq(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        BigDecimal rtnVal = (BigDecimal) getSsmEZDClient().queryObject("getNextDsContrBllgSchdSq", ssmParam).getResultObject();
        if (!hasValue(rtnVal)) {
            return null;
        }
        return rtnVal.toPlainString();
    }

    public BigDecimal getCurPeBasePrcDealAmt(String glblCmpyCd, BigDecimal dsContrDtlPk, String slsDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("cancelSts", DS_CONTR_DTL_STS.CANCELLED);
        return (BigDecimal) getSsmEZDClient().queryObject("getCurPeBasePrcDealAmt", ssmParam).getResultObject();
    }
    // Add End 2017/10/19 QC#21815

    // START 2017/12/19 K.Kojima [QC#22385,ADD]
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return String
     */
    public List<BigDecimal> getSvcPhysMtrReadGrpSq(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcPhysMtrReadGrpSq", ssmParam).getResultObject();
    }
    // END 2017/12/19 K.Kojima [QC#22385,ADD]

    // START 2018/03/15 K.Kojima [QC#22669,ADD]
    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return String
     */
    public BigDecimal countNoInvoiceCrRebilCount(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countNoInvoiceCrRebilCount", ssmParam).getResultObject();
    }
    // END 2018/03/15 K.Kojima [QC#22669,ADD]

    // START 2018/05/30 K.Kitachi [QC#22336, ADD]
    /**
     * @param ssmParam Map<String, Object>
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getHrchList(Map<String, Object> ssmParam) {
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getHrchList", ssmParam).getResultObject();
    }
    // END 2018/05/30 K.Kitachi [QC#22336, ADD]

    // START 2018/06/05 K.Kojima [QC#21974,ADD]
    /**
     * @param glblCmpyCd String
     * @param svcInvNum String
     * @return BigDecimal
     */
    public BigDecimal countFreeCopyRollOverUsed(String glblCmpyCd, String svcInvNum, String prevSvcInvNum, String nextSvcInvNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        List<String> svcInvNumList = new ArrayList<String>();
        svcInvNumList.add(svcInvNum);
        if (hasValue(prevSvcInvNum)) {
            svcInvNumList.add(prevSvcInvNum);
        }
        if (hasValue(nextSvcInvNum)) {
            svcInvNumList.add(nextSvcInvNum);
        }
        ssmParam.put("svcInvNumList", svcInvNumList);
        return (BigDecimal) getSsmEZDClient().queryObject("countFreeCopyRollOverUsed", ssmParam).getResultObject();
    }
    // END 2018/06/05 K.Kojima [QC#21974,ADD]

    // START 2018/06/25 K.Kitachi [QC#22245, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedUsgChrgForDtl(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrgForDtl", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrg", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedCrRebilForBase(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        // START 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        // END 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedCrRebilForBase", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param dsContrDtlPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedCrRebilForMtr(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        // START 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        // END 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedCrRebilForMtr", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedCrRebil(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("dsContrPk", dsContrPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        // START 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        // END 2018/07/12 K.Kim [QC#26726, ADD]
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedCrRebil", param).getResultObject();
    }
    // END 2018/06/25 K.Kitachi [QC#22245, ADD]

    // START 2018/07/18 K.Kojima [QC#26791,ADD]
    public List<Map<String, Object>> getAdjustTargetMachine(String glblCmpyCd, BigDecimal svcCrRebilPk, String origSvcInvNum) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("origSvcInvNum", origSvcInvNum);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAdjustTargetMachine", param).getResultObject();
    }

    public List<Map<String, Object>> getAdjustTargetMtrRead(String glblCmpyCd, BigDecimal svcCrRebilPk, String origSvcInvNum, BigDecimal svcMachMstrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("origSvcInvNum", origSvcInvNum);
        param.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAdjustTargetMtrRead", param).getResultObject();
    }

    public List<Map<String, Object>> getMtrFmlaInfo(String glblCmpyCd, BigDecimal svcMachMstrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getMtrFmlaInfo", ssmParam).getResultObject();
    }

    public BigDecimal countNegativeRead(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countNegativeRead", ssmParam).getResultObject();
    }
    // END 2018/07/18 K.Kojima [QC#26791,ADD]
    // START  2018/09/05 [QC#24555, ADD]
    public DS_CONTR_PRC_EFFTMsg getDsContrPrcEffForUpdate(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        if (!hasValue(dsContrPrcEffPk)) {
            return null;
        }
        DS_CONTR_PRC_EFFTMsg inMsg = new DS_CONTR_PRC_EFFTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPrcEffPk, dsContrPrcEffPk);
        return (DS_CONTR_PRC_EFFTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }

    public DS_CONTR_BLLG_SCHD_MTRTMsg getDsContrBllgSchdMtrForUpdate(String glblCmpyCd, BigDecimal dsContrBllgSchdMtrPk) {
        if (!hasValue(dsContrBllgSchdMtrPk)) {
            return null;
        }
        DS_CONTR_BLLG_SCHD_MTRTMsg inMsg = new DS_CONTR_BLLG_SCHD_MTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrBllgSchdMtrPk, dsContrBllgSchdMtrPk);
        return (DS_CONTR_BLLG_SCHD_MTRTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }
    // END   2018/09/05 [QC#24555, ADD]

    // START 2018/08/27 [QC#24555]
    public Map<String, Object> getDsContrBllgSchdInfo(String glblCmpyCd, BigDecimal svcInvLinePk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcInvLinePk", svcInvLinePk);
        return (Map<String, Object>) getSsmEZDClient().queryObject("getDsContrBllgSchdInfo", ssmParam).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrDtlPk BigDecimal
     * @param dsContrBllgMtrPk BigDecimal
     * @param baseChrgFlg String
     * @return String
     */
    public String getInvSchdMaxThruDt(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
            ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        }
        ssmParam.put("stsClose", DS_BLLG_SCHD_STS.CLOSE);
        ssmParam.put("dsBllgSchdTpCd", DS_BLLG_SCHD_TP.REGULAR);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (String) getSsmEZDClient().queryObject("getInvSchdMaxThruDt", ssmParam).getResultObject();
    }

    // END   2018/08/27 [QC#24555]
    // START 2018/09/05 [QC#24555, ADD]
    public List<BigDecimal> getDsContrDtlPkFromBaseRebil(String glblCmpyCd, BigDecimal svcCrRebilPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrDtlPkFromBaseRebil", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getRoundAdjustBaseForCiEntry(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRoundAdjustBaseForCiEntry", ssmParam).getResultObject();
    }

    public List<BigDecimal> getDsContrBllgMtrPkFromRebilMtr(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getDsContrBllgMtrPkFromRebilMtr", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getRoundAdjustMtrBllgForCiEntry(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRoundAdjustMtrBllgForCiEntry", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getRoundAdjustXsMtrForCiEntry(String glblCmpyCd, BigDecimal svcCrRebilMtrBllgPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilMtrBllgPk", svcCrRebilMtrBllgPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRoundAdjustXsMtrForCiEntry", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getRoundAdjustBaseForApproval(String glblCmpyCd, BigDecimal svcCrRebilPk, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getRoundAdjustBaseForApproval", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getTargetStubBase(String glblCmpyCd, BigDecimal dsContrDtlPk, String targetFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("targetFromDt", targetFromDt);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTargetStubBase", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getTargetStubUsage(String glblCmpyCd, BigDecimal dsContrBllgMtrPk, String targetFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        ssmParam.put("targetFromDt", targetFromDt);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTargetStubUsage", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getTargetXsCopy(String glblCmpyCd, BigDecimal dsContrPrcEffPk, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrPrcEffPk", dsContrPrcEffPk);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        ssmParam.put("invTpCd", INV_TP.INVOICE);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getTargetXsCopy", ssmParam).getResultObject();
    }

    public List<Map<String, Object>> getAdjustTopSchd(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg, String effFromDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("effFromDt", effFromDt);
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getAdjustTopSchd", ssmParam).getResultObject();
    }

    public List<BigDecimal> getLogicalRemoveTopSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getLogicalRemoveTopSchdPk", ssmParam).getResultObject();
    }

    public List<BigDecimal> getLogicalRemovePrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String effFromDt, String effThruDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("effFromDt", effFromDt);
        ssmParam.put("effThruDt", effThruDt);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getLogicalRemovePrcEffPk", ssmParam).getResultObject();
    }

    /**
     * getDsContrBllgSchdSmryTMsgArray
     * @param glblCmpyCd String
     * @param dsContrPrcEffPk BigDecimal
     * @return DS_CONTR_BLLG_SCHD_SMRYTMsgArray
     */
    public DS_CONTR_BLLG_SCHD_SMRYTMsgArray getDsContrBllgSchdSmryTMsgArray(String glblCmpyCd, BigDecimal dsContrPrcEffPk) {
        DS_CONTR_BLLG_SCHD_SMRYTMsg inMsg = new DS_CONTR_BLLG_SCHD_SMRYTMsg();
        inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        inMsg.setConditionValue("dsContrPrcEffPk01", dsContrPrcEffPk);
        inMsg.setSQLID("001");
        return (DS_CONTR_BLLG_SCHD_SMRYTMsgArray) S21ApiTBLAccessor.findByCondition(inMsg);
    }

    // END   2018/09/05 [QC#24555, ADD]

    // START 2018/08/31 K.Kojima [QC#27960,ADD]
    public List<Map<String, Object>> getReplacePrcEffPkSkipMonth(String glblCmpyCd, BigDecimal dsContrDtlPk, String baseChrgFlg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getReplacePrcEffPkSkipMonth", ssmParam).getResultObject();
    }

    public List<BigDecimal> getReplaceDsContrBllgSchdSqNumTarget(String glblCmpyCd, BigDecimal dsContrBllgSchdSmryPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrBllgSchdSmryPk", dsContrBllgSchdSmryPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getReplaceDsContrBllgSchdSqNumTarget", ssmParam).getResultObject();
    }
    // END 2018/08/31 K.Kojima [QC#27960,ADD]
    // START 2019/07/18 [QC#51706,ADD]
    public boolean hasManualHld(String glblCmpyCd, BigDecimal dsContrPk, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk) {
        
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("dsContrDtlPk", dsContrDtlPk);
        param.put("dsContrBllgMtrPk", dsContrBllgMtrPk);

        BigDecimal manulaHldCnt = (BigDecimal) getSsmEZDClient().queryObject("getManualHldCnt", param).getResultObject();
        if (manulaHldCnt == null || manulaHldCnt.compareTo(BigDecimal.ZERO) == 0) {
            return false;
        }
        return true;
    }
    // END   2019/07/18 [QC#51706,ADD]

    // START 2020/01/08 [QC#55170, ADD]
    public List<Map<BigDecimal, Object>> getPrntSvcContrBllgForRegAcc(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("svcCrRebilPk", svcCrRebilPk);
        param.put("dsContrCatgCdReg", DS_CONTR_CATG.REGULAR);
        param.put("dsContrDtlTpCdAcc", DS_CONTR_DTL_TP.ACCESSORIES);
        return (List<Map<BigDecimal, Object>>) getSsmEZDClient().queryObjectList("getPrntSvcContrBllgForRegAcc", param).getResultObject();
    }

    public SVC_CONTR_BLLGTMsg getSvcContrBllgTMsgForUpdate(String glblCmpyCd, BigDecimal svcContrBllgPk) {
        SVC_CONTR_BLLGTMsg inMsg = new SVC_CONTR_BLLGTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.svcContrBllgPk, svcContrBllgPk);
        return (SVC_CONTR_BLLGTMsg) S21ApiTBLAccessor.findByKeyForUpdate(inMsg);
    }
    // END 2020/01/08 [QC#55170, ADD]

    // add start 2020/06/09 QC#56945
    public List<BigDecimal> getLogicalRemoveDuplicatePrcEffPk(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String baseChrgFlg, String effFromDt, String effThruDt, BigDecimal newDsContrPrcEffPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        if (FLG_ON_Y.equals(baseChrgFlg)) {
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("usgChrgFlg", FLG_OFF_N);
        } else {
            ssmParam.put("baseChrgFlg", FLG_OFF_N);
            ssmParam.put("usgChrgFlg", FLG_ON_Y);
        }
        ssmParam.put("effFromDt", effFromDt);
        ssmParam.put("effThruDt", effThruDt);
        ssmParam.put("dsContrPrcEffPk", newDsContrPrcEffPk);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getLogicalRemoveDuplicatePrcEffPk", ssmParam).getResultObject();
    }
    // add end 2020/06/09 QC#56945

    // START 2020/06/25 K.Kitachi [QC#56211, ADD]
    /**
     * getInvalidCustAcct
     * @param glblCmpyCd String
     * @param svcCrRebilPk BigDecimal
     * @return List<String>
     */
    public List<String> getInvalidCustAcct(String glblCmpyCd, BigDecimal svcCrRebilPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcCrRebilPk", svcCrRebilPk);
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (List<String>) getSsmEZDClient().queryObjectList("getInvalidCustAcct", ssmParam).getResultObject();
    }
    // END 2020/06/25 K.Kitachi [QC#56211, ADD]

    // add start 2021/04/05 QC#58177-2
    /**
     * countChangeMtrAmtRate
     */
    public BigDecimal countChangeMtrAmtRate(String glblCmpyCd, String custIncdtId, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custIncdtId", custIncdtId);
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countChangeMtrAmtRate", ssmParam).getResultObject();
    }
    public BigDecimal countChangeMtrAmtRate4Agg(String glblCmpyCd, String custIncdtId, BigDecimal dsContrBllgMtrPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custIncdtId", custIncdtId);
        // START 2022/12/23 R.Jin [QC#60969, ADD]
        ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
        // END 2022/12/23 R.Jin [QC#60969, ADD]
        return (BigDecimal) getSsmEZDClient().queryObject("countChangeMtrAmtRate4Agg", ssmParam).getResultObject();
    }
    public BigDecimal countChangeBaseUnitAmt(String glblCmpyCd, String custIncdtId, BigDecimal dsContrDtlPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("custIncdtId", custIncdtId);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        return (BigDecimal) getSsmEZDClient().queryObject("countChangeBaseUnitAmt", ssmParam).getResultObject();
    }
    // add end 2021/04/05 QC#58177-2
    
    // Add Start 2021/6/28 QC#58177-5
    public Map<String, Object> getPrntBllgSchdPk(String glblCmpyCd, BigDecimal dsContrDtlPk, String bllgSchdFromDt, String bllgSchdThruDt, String invTpCd, BigDecimal childDsContrDtlPk) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsContrDtlPk", dsContrDtlPk);
            ssmParam.put("baseChrgFlg", FLG_ON_Y);
            ssmParam.put("bllgSchdFromDt", bllgSchdFromDt);
            ssmParam.put("bllgSchdThruDt", bllgSchdThruDt);
            ssmParam.put("invTpCd", invTpCd);
            ssmParam.put("bllgTmgTpAdvance", BLLG_TMG_TP.ADVANCE);
            ssmParam.put("bllgTmgTpArrears", BLLG_TMG_TP.ARREARS);
            int baseBllgCycleMthAot = getBaseBllgCycleMthAot(glblCmpyCd, childDsContrDtlPk);
            String nextBllgSchdFromDt = NSXC001001CalcDate.addMonths(bllgSchdFromDt, baseBllgCycleMthAot);
            String prevBllgSchdThruDt = NSXC001001CalcDate.addMonths(bllgSchdThruDt, -baseBllgCycleMthAot);
            ssmParam.put("nextBllgSchdFromDt", nextBllgSchdFromDt);
            ssmParam.put("prevBllgSchdThruDt", prevBllgSchdThruDt);
            ssmParam.put("childDsContrDtlPk", childDsContrDtlPk);
            return (Map<String, Object>)getSsmEZDClient().queryObject("getPrntBllgSchdPk", ssmParam).getResultObject();
    }
    private int getBaseBllgCycleMthAot(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        
        DS_CONTR_DTLTMsg dsContrDtlTMsg = getDsContrDtlTMsg(glblCmpyCd, dsContrDtlPk);
        if (dsContrDtlTMsg == null) {
            return 0;
        }
        BLLG_CYCLETMsg bllgCycleTMsg = getBllgCycleTMsg(glblCmpyCd, dsContrDtlTMsg.baseBllgCycleCd.getValue());
        if (bllgCycleTMsg == null) {
            return 0;
        }
        return bllgCycleTMsg.bllgCycleMthAot.getValueInt();
    }
    public BLLG_CYCLETMsg getBllgCycleTMsg(String glblCmpyCd, String bllgCycleCd) {

        BLLG_CYCLETMsg inMsg = new BLLG_CYCLETMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.bllgCycleCd, bllgCycleCd);
        return (BLLG_CYCLETMsg) S21CacheTBLAccessor.findByKey(inMsg);
    }
    public List<Map<String, Object>> getPrntlessBllgSchdList(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrPk ) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrPk", dsContrPk);
        ssmParam.put("dsContrDtlTpCd", DS_CONTR_DTL_TP.ACCESSORIES);
        ssmParam.put("dsContrDtlTpCdMacb", DS_CONTR_DTL_TP.BASE_ONLY);
        ssmParam.put("dsContrDtlTpCdMacbu", DS_CONTR_DTL_TP.BASE_AND_USAGE);

        return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getPrntlessBllgSchd", ssmParam).getResultObject();
    }
    // Add End 2021/6/28 QC#58177-5

    // START 2022/09/01 L.Mandanas [QC#58350, ADD]
    /**
    * @param glblCmpyCd String
    * @param svcCrRebilPk BigDecimal
    * @return List<Map<String, Object>>
    */
   public List<Map<String, Object>> getSvcCrRebilDtlSorted(String glblCmpyCd, BigDecimal svcCrRebilPk) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("svcCrRebilPk", svcCrRebilPk);
       return (List<Map<String, Object>>) getSsmEZDClient().queryObjectList("getSvcCrRebilDtlSorted", ssmParam).getResultObject();
   }
   // END 2022/09/01 L.Mandanas [QC#58350, ADD]

   // START 2022/09/27 L.Mandanas [QC#58427, MOD]
   /**
    * getBaseRnwlHold
    * @param glblCmpyCd String
    * @param dsContrDtlPk BigDecimal
    * @param contrEffThruDt String
    * @return String
    */
   public String getBaseRnwlHold(String glblCmpyCd, BigDecimal dsContrDtlPk, String contrEffThruDt) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("dsContrDtlPk", dsContrDtlPk);
       ssmParam.put("dsContrPrcEffStsCd_RNWH", DS_CONTR_DTL_STS.RENEWAL_HOLD);
       ssmParam.put("dsContrPrcEffStsCd_RNPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
       ssmParam.put("baseChrgFlg", FLG_ON_Y);
       ssmParam.put("contrEffThruDt", contrEffThruDt);
       return (String) getSsmEZDClient().queryObject("getBaseRnwlHold", ssmParam).getResultObject();
   }

   /**
    * getUsgRnwlHold
    * @param glblCmpyCd String
    * @param dsContrDtlPk BigDecimal
    * @param dsContrBllgMtrPk BigDecimal
    * @param contrEffThruDt String
    * @return String
    */
   public String getUsgRnwlHold(String glblCmpyCd, BigDecimal dsContrDtlPk, BigDecimal dsContrBllgMtrPk, String contrEffThruDt) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("dsContrDtlPk", dsContrDtlPk);
       ssmParam.put("dsContrBllgMtrPk", dsContrBllgMtrPk);
       ssmParam.put("dsContrPrcEffStsCd_RNWH", DS_CONTR_DTL_STS.RENEWAL_HOLD);
       ssmParam.put("dsContrPrcEffStsCd_RNPO", DS_CONTR_DTL_STS.RENEWAL_HOLD_FOR_PO);
       ssmParam.put("usgChrgFlg", FLG_ON_Y);
       ssmParam.put("contrEffThruDt", contrEffThruDt);
       return (String) getSsmEZDClient().queryObject("getUsgRnwlHold", ssmParam).getResultObject();
   }
   // END 2022/09/27 L.Mandanas [QC#58427, MOD]

   // START 2022/11/02 L.Mandanas [QC#60652, ADD]
   /**
    * getInvalidBillToCust
    * @param glblCmpyCd String
    * @param svcCrRebilPk BigDecimal
    * @return List<String>
    */
   public List<String> getInvalidBillToCust(String glblCmpyCd, BigDecimal svcCrRebilPk) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("svcCrRebilPk", svcCrRebilPk);
       ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
       return (List<String>) getSsmEZDClient().queryObjectList("getInvalidBillToCust", ssmParam).getResultObject();
   }

   /**
    * getInvalidShipToCust
    * @param glblCmpyCd String
    * @param svcCrRebilPk BigDecimal
    * @return List<String>
    */
   public List<String> getInvalidShipToCust(String glblCmpyCd, BigDecimal svcCrRebilPk) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("svcCrRebilPk", svcCrRebilPk);
       ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
       return (List<String>) getSsmEZDClient().queryObjectList("getInvalidShipToCust", ssmParam).getResultObject();
   }
   // END 2022/11/02 L.Mandanas [QC#60652, ADD]
   // START 2023/01/16 R.Jin [QC#58890, ADD]
   /**
    * getInvalidShipToCust
    * @param glblCmpyCd String
    * @param svcCrRebilPk BigDecimal
    * @return List<String>
    */
   public List<Integer> getAggLineXscopyChangeTirInfo(String glblCmpyCd, String serNum, String bllgMtrLbCd, BigDecimal xsMtrCopyQty) {
       Map<String, Object> ssmParam = new HashMap<String, Object>();
       ssmParam.put("glblCmpyCd", glblCmpyCd);
       ssmParam.put("serNum", serNum);
       ssmParam.put("bllgMtrLbCd", bllgMtrLbCd);
       ssmParam.put("xsMtrCopyQty", xsMtrCopyQty);
       return (List<Integer>) getSsmEZDClient().queryObjectList("getAggLineXscopyChangeTirInfo", ssmParam).getResultObject();
   }
   // END 2023/01/16 R.Jin [QC#58890, ADD]

}

package com.canon.cusa.s21.common.NSX.NSXC004001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.ADDL_CHRG_INV_TPTMsg;
import business.db.AJE_COA_PROD_MAPTMsg;
import business.db.DS_CONTR_ADDL_CHRGTMsg;
import business.db.DS_CONTR_BLLG_MTRTMsg;
import business.db.DS_CONTR_DTLTMsg;
import business.db.DS_CONTR_DTLTMsgArray;
import business.db.MDSETMsg;
import business.db.S21_ORGTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.db.SELL_TO_CUSTTMsgArray;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.TOCTMsg;
import business.parts.NMZC610001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC610001.NMZC610001;
import com.canon.cusa.s21.api.NMZ.NMZC610001.constant.NMZC610001Constant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CM_DEF_ACCT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TRX_RULE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_LINE_SPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_INV_CHRG_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Hitachi         K.Kishimoto     Create          N/A
 * 2015/12/15   Hitachi         T.Kanasaka      Update          QC#1758
 * 2016/02/03   Hitachi         Y.Takeno        Update          QC#871
 * 2016/02/10   Hitachi         A.Kohinata      Update          QC#3186
 * 2016/04/07   Hitachi         A.Kohinata      Update          QC#6566
 * 2016/05/24   Hitachi         T.Kanasaka      Update          QC#8591
 * 2016/05/27   Hitachi         T.Kanasaka      Update          QC#8335
 * 2016/06/20   Hitachi         T.Aoyagi        Update          QC#9907
 * 2016/06/27   Hitachi         T.Kanasaka      Update          QC#10585
 * 2016/08/24   Hitachi         T.Tomita        Update          QC#10787
 * 2016/09/27   Hitachi         T.Kanasaka      Update          QC#9905
 * 2016/10/18   Hitachi         T.Kanasaka      Update          QC#13009
 * 2016/11/10   Hitachi         K.Kishimoto     Update          QC#15789
 * 2017/02/02   Hitachi         T.Kanasaka      Update          QC#17334
 * 2017/09/22   Fujitsu         M.Ohno          Update          QC#17116
 * 2018/07/04   Hitachi         K.Kojima        Update          QC#23685-1
 * 2018/07/05   Hitachi         K.Kojima        Update          QC#23685-1
 * 2018/07/09   Hitachi         K.Kojima        Update          QC#23685-1
 *</pre>
 */
public class NSXC004001GetDefCoaTrxCd {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient SSM_CLNT = S21SsmBatchClient.getClient(NSXC004001GetDefCoaTrxCd.class);

    /**
     * Variable Character Constant : COA_CMPY_CD
     */
    private static final String COA_CMPY_CD = "COA_CMPY_CD";

    /**
     * Variable Character Constant : SPCL_FLT_MDSE_CD
     */
    private static final String SPCL_FLT_MDSE_CD = "SPCL_FLT_MDSE_CD";

    /**
     * Variable Character Constant : SVC_COA_PROJ_CD
     */
    private static final String SVC_COA_PROJ_CD = "SVC_COA_PROJ_CD";

    /**
     * Variable Character Constant : SPLY_COA_PROJ_CD
     */
    private static final String SPLY_COA_PROJ_CD = "SPLY_COA_PROJ_CD";

    /**
     * Variable Character Constant : EQUIP_COA_PROJ_CD
     */
    private static final String EQUIP_COA_PROJ_CD = "EQUIP_COA_PROJ_CD";

    /** Variable Character Constant : NSXC0040_LOG */
    private static final String NSXC0040_LOG = "NSXC0040_LOG";

    /** Variable Character Constant : NSXC0040_LOG */
    private static String logMsg = null;

    /** The model can not be found (SVC_MACH_MSTR_PK[@]). */
    private static final String NSXM0116W = "NSXM0116W";
    /** The Service Program can not be found. */
    private static final String NSXM0117W = "NSXM0117W";
    /** The Service Program can not be found (DS_CONTR_DTL_PK[@]). */
    private static final String NSXM0118W = "NSXM0118W";
    /** The MDSE can not be found (MDSE_CD[@]). */
    private static final String NSXM0119W = "NSXM0119W";
    /** The SVC_ALLOC_TP_CD in MDSE can not be found (MDSE_CD[@]). */
    private static final String NSXM0120W = "NSXM0120W";
    /** The AJE_INV_LINE_ALLOC can not be found (SVC_ALLOC_TP_CD[@], MDL_ID[@], SVC_CHRG_TP_CD[@], ADDL_CHRG_FLG[@]). */
    private static final String NSXM0121W = "NSXM0121W";

    // START 2016/02/03 Y.Takeno [QC#871, ADD]

    /**
     * mode code : 01
     */
    public static final String XX_MODE_01 = "01";

    /**
     * mode code : 02
     */
    public static final String XX_MODE_02 = "02";

    // END   2016/02/03 Y.Takeno [QC#871, ADD]

    /**
     * Get default chart of account transaction code
     * @param bean DefCoaTrxCdInfoBean
     * @return Default chart of account transaction code
     */
    public static GetDefCoaTrxCdInfoBean getDefCoaTrxCd(GetDefCoaTrxCdInfoBean bean) {
        if (!mandatoryCheck(bean)) {
            return null;
        }
        logMsg = ZYPCodeDataUtil.getVarCharConstValue(NSXC0040_LOG, bean.getGlblCmpyCd());
        if (!ZYPCommonFunc.hasValue(logMsg)) {
            logMsg = ZYPConstant.FLG_ON_Y;
        }

        // get Allocation Info
        String mdseCd = getMdseCd(bean);
        // START 2017/02/02 T.Kanasaka [QC#17334, ADD]
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            return null;
        }
        // END 2017/02/02 T.Kanasaka [QC#17334, ADD]

        Map<String, Object> allocInfo = getAllocInfo(bean, mdseCd);
        if (allocInfo == null) {
            setAllocLog(bean, mdseCd);
            return null;
        }

        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
        SELL_TO_CUSTTMsg sellToCustTMsg = getSellToCustTMsg(bean);
        if (sellToCustTMsg == null) {
            return null;
        }

        // START 2018/07/05 K.Kojima [QC#23685-1,ADD]
        Map<String, Object> coaAfflCdAndCoaChCd = getCoaAfflCdAndCoaChCd(bean);
        // END 2018/07/05 K.Kojima [QC#23685-1,ADD]

        // get Default Chart of Account Transaction Code
        List<GetDefCoaTrxCdForOutListInfoBean> outBeanList = new ArrayList<GetDefCoaTrxCdForOutListInfoBean>();
//        GetDefCoaTrxCdForOutListInfoBean  equipment = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.EQUIPMENT);
        // START 2018/07/05 K.Kojima [QC#23685-1,MOD]
        // GetDefCoaTrxCdForOutListInfoBean  equipment = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.EQUIPMENT, sellToCustTMsg);
        GetDefCoaTrxCdForOutListInfoBean  equipment = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.EQUIPMENT, sellToCustTMsg, coaAfflCdAndCoaChCd);
        // END 2018/07/05 K.Kojima [QC#23685-1,MOD]
        if (equipment != null) {
            outBeanList.add(equipment);
        }
//        GetDefCoaTrxCdForOutListInfoBean  supply = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SUPPLY);
        // START 2018/07/05 K.Kojima [QC#23685-1,MOD]
        // GetDefCoaTrxCdForOutListInfoBean  supply = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SUPPLY, sellToCustTMsg);
        GetDefCoaTrxCdForOutListInfoBean  supply = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SUPPLY, sellToCustTMsg, coaAfflCdAndCoaChCd);
        // END 2018/07/05 K.Kojima [QC#23685-1,MOD]
        if (supply != null) {
            outBeanList.add(supply);
        }
//        GetDefCoaTrxCdForOutListInfoBean  service = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SERVICE);
        // START 2018/07/05 K.Kojima [QC#23685-1,MOD]
        // GetDefCoaTrxCdForOutListInfoBean  service = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SERVICE, sellToCustTMsg);
        GetDefCoaTrxCdForOutListInfoBean  service = setOutParam(bean, allocInfo, mdseCd, INV_LINE_SPL_TP.SERVICE, sellToCustTMsg, coaAfflCdAndCoaChCd);
        // END 2018/07/05 K.Kojima [QC#23685-1,MOD]
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (service != null) {
            outBeanList.add(service);
        }
        if (outBeanList.size() == 0) {
            return null;
        }
        bean.setOutListInfoBean(outBeanList);
        return bean;
    }

    // Add Start 2016/11/10 <QC#15789>
    public static String GetAjeInvLineAllocCd(String glblCmpyCd, String svcInvChrgTpCd, String svcPgmMdseCd, BigDecimal mdlId) {
        String prmMdlId = null;
        if (ZYPCommonFunc.hasValue(mdlId)) {
            prmMdlId = mdlId.toString();
        }
        Map<String, Object> allocInfo = getAllocInfoForAjeInvLineAllocCd(glblCmpyCd, svcInvChrgTpCd, svcPgmMdseCd, prmMdlId);
        if(allocInfo != null) {
            return (String) allocInfo.get("AJE_INV_LINE_ALLOC_CD");
        }
        allocInfo = getAllocInfoForAjeInvLineAllocCd(glblCmpyCd, svcInvChrgTpCd, svcPgmMdseCd, "");
         if(allocInfo != null) {
             return (String) allocInfo.get("AJE_INV_LINE_ALLOC_CD");
         }
         return null;
    }

    public static String GetAjeInvLineAllocCdForAddl(String glblCmpyCd, String svcInvChrgTpCd) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("svcInvChrgTpCd", svcInvChrgTpCd);
        return (String) SSM_CLNT.queryObject("getAjeInvLineAllocCdForAddl", prm);
    }

    private static Map<String, Object> getAllocInfoForAjeInvLineAllocCd(String glblCmpyCd, String svcInvChrgTpCd, String mdseCd, String mdlId) {
        String ssmId = "getAllocInfo";

        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", glblCmpyCd);
        prm.put("mdseCd", mdseCd);
        prm.put("mdlId", mdlId);
        prm.put("addlChrgFlg", ZYPConstant.FLG_ON_Y);
        prm.put("paramAddlChrgFlg", ZYPConstant.FLG_OFF_N);
        prm.put("svcInvChrgTpCd", svcInvChrgTpCd);
        prm.put("defAcctCd", CM_DEF_ACCT.SERVICE_SUPPLY_COA); //Add 09/22/2017 <QC#17116>
        @SuppressWarnings("unchecked")
        Map<String, Object>outMap = (Map<String, Object>) SSM_CLNT.queryObject(ssmId, prm);
        return outMap;
    }
    // Add End   2016/11/10 <QC#15789>

    private static boolean mandatoryCheck(GetDefCoaTrxCdInfoBean bean) {

        // START 2016/02/03 Y.Takeno [QC#871, ADD]
        if (XX_MODE_02.equals(bean.getXxModeCd())) {
            // execute mode 02
            return mandatoryCheckMode02(bean);

        } else if(bean.getXxModeCd() == null || XX_MODE_01.equals(bean.getXxModeCd())) {
            // execute mode 01
            bean.setXxModeCd(XX_MODE_01);

        } else {
            // illegal mode
            return false;
        }
        // END   2016/02/03 Y.Takeno [QC#871, ADD]
        
        if (!ZYPCommonFunc.hasValue(bean.getGlblCmpyCd())) {
            return false;
        }
//        if (isRegOrAggMachContr(bean)) {
//            if (!ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
//                return false;
//            }
//        }
        if (!ZYPCommonFunc.hasValue(bean.getDsContrDtlPk())) {
            return false;
        }
        // START 2017/02/02 T.Kanasaka [QC#17334, DEL]
//        if (!ZYPCommonFunc.hasValue(bean.getMdseCd())) {
//            return false;
//        }
        // END 2017/02/02 T.Kanasaka [QC#17334, DEL]
        if (!ZYPCommonFunc.hasValue(bean.getDsAcctNum())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bean.getBaseChrgFlg())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bean.getUsgChrgFlg())) {
            return false;
        //Del Start 05/27/2016 <QC#8335>
//        } else {
//            // Usage Charge
//            if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg()) && !ZYPCommonFunc.hasValue(bean.getDsContrBllgMtrPk())) {
//                return false;
//            }
        //Del End   05/27/2016 <QC#8335>
        }
        if (!ZYPCommonFunc.hasValue(bean.getAddlChrgFlg())) {
            return false;
        } else {
            // Additional Charge
            if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg()) && !ZYPCommonFunc.hasValue(bean.getDsContrAddlChrgPk())) {
                return false;
            }
        }
        return true;
    }

    private static boolean mandatoryCheckMode02(GetDefCoaTrxCdInfoBean bean) {
        if (!ZYPCommonFunc.hasValue(bean.getGlblCmpyCd())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bean.getBaseChrgFlg())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bean.getUsgChrgFlg())) {
            return false;
        //Del Start 05/27/2016 <QC#8335>
//        } else {
//            // Usage Charge
//            if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg()) && !ZYPCommonFunc.hasValue(bean.getDsContrBllgMtrPk())) {
//                return false;
//            }
        //Del End   05/27/2016 <QC#8335>
        }
        // Additional Charge
        if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg()) && !ZYPCommonFunc.hasValue(bean.getDsContrAddlChrgPk())) {
            return false;
        }
        if (!ZYPCommonFunc.hasValue(bean.getTocCd())) {
            return false;
        }
        // START 2016/04/07 [QC#6566, ADD]
        if (!ZYPCommonFunc.hasValue(bean.getFleetLineFlg())) {
            return false;
        }
        if (ZYPConstant.FLG_ON_Y.equals(bean.getFleetLineFlg()) && !ZYPCommonFunc.hasValue(bean.getSvcLineBizCd())) {
            return false;
        }
        // END 2016/04/07 [QC#6566, ADD]
        return true;
    }
    
    private static boolean isRegOrAggMachContr(GetDefCoaTrxCdInfoBean bean) {
        BigDecimal regContrCnt = getRegContrCnt(bean);
        BigDecimal aggMachContrCnt = getAggMachContrCnt(bean);
        if (regContrCnt.add(aggMachContrCnt).compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        return false;
    }

    private static BigDecimal getRegContrCnt(GetDefCoaTrxCdInfoBean bean) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("dsContrDtlPk", bean.getDsContrDtlPk());
        prm.put("dsContrCatgCd", DS_CONTR_CATG.REGULAR);
        return (BigDecimal) SSM_CLNT.queryObject("getRegContrCnt", prm);
    }

    private static BigDecimal getAggMachContrCnt(GetDefCoaTrxCdInfoBean bean) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("dsContrDtlPk", bean.getDsContrDtlPk());
        prm.put("dsContrCatgCd", DS_CONTR_CATG.AGGREGATE);
        return (BigDecimal) SSM_CLNT.queryObject("getAggMachContrCnt", prm);
    }

    //Mod Start 05/27/2016 <QC#8335>
    private static String getMdseCd(GetDefCoaTrxCdInfoBean bean) {
//        // START 2016/02/03 Y.Takeno [QC#871, ADD]
//        if (XX_MODE_02.equals(bean.getXxModeCd())) {
//            if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseChrgFlg())) {
//                return bean.getSvcPgmMdseCd();
//            } else if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg())) {
//                return bean.getIntgMdseCd();
//            }
//            return null;
//        }
//        // END   2016/02/03 Y.Takeno [QC#871, ADD]
//        if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseChrgFlg())) {
//            return getMdseCdFromBaseChrg(bean);
//        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg())) {
//            return getMdseCdFromUsgChrg(bean);
//        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg())) {
//            return getMdseCdFromAddlChrg(bean);
//        }
//        return null;

        if (XX_MODE_02.equals(bean.getXxModeCd())) {
            return bean.getSvcPgmMdseCd();
        } else {
            return getMdseCdFromBaseChrg(bean);
        }
    }
    //Mod End   05/27/2016 <QC#8335>

    private static Map<String, Object> getAllocInfo(GetDefCoaTrxCdInfoBean bean, String mdseCd) {
        // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
        Map<String, Object> allocInfo = null;
        if (ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
            String mdlId = getMdlId(bean);
            if (!ZYPCommonFunc.hasValue(mdlId)) {
                setLogMsg(NSXM0116W, bean.getSvcMachMstrPk().toString());
            }
            allocInfo = getAllocInfo(bean, mdseCd, mdlId);
        }
        if(allocInfo != null) {
            return allocInfo;
        }
        return getAllocInfo(bean, mdseCd, "");
        // END 2015/12/15 T.Kanasaka [QC#1758, MOD]
    }

    private static String getMdseCdFromBaseChrg(GetDefCoaTrxCdInfoBean bean) {
        // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
        // DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
        // ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        // ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
        // tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
        DS_CONTR_DTLTMsg tmsg = null;
        if (bean.getDsContrDtlTMsg() != null) {
            tmsg = bean.getDsContrDtlTMsg();
        } else {
            tmsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
            tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
        }
        // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
        if (tmsg == null) {
            return null;
        }

        //Add Start 05/27/2016 <QC#8335>
        if (!ZYPCommonFunc.hasValue(tmsg.svcPgmMdseCd) && ZYPCommonFunc.hasValue(tmsg.prntDsContrDtlPk)) {
            // For Fleet Machine
            BigDecimal prntDsContrDtlPk = tmsg.prntDsContrDtlPk.getValue();
            tmsg = new DS_CONTR_DTLTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, prntDsContrDtlPk);
            tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
            if (tmsg == null) {
                return null;
            }

            // For Fleet Accessory
            if (!ZYPCommonFunc.hasValue(tmsg.svcPgmMdseCd) && ZYPCommonFunc.hasValue(tmsg.prntDsContrDtlPk)) {
                prntDsContrDtlPk = tmsg.prntDsContrDtlPk.getValue();
                tmsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, tmsg.prntDsContrDtlPk);
                tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
                if (tmsg == null) {
                    return null;
                }
            }
        }
        //Add End   05/27/2016 <QC#8335>

        return tmsg.svcPgmMdseCd.getValue();
    }

    //Del Start 05/27/2016 <QC#8335>
//    private static String getMdseCdFromUsgChrg(GetDefCoaTrxCdInfoBean bean) {
//        DS_CONTR_BLLG_MTRTMsg tmsg = new DS_CONTR_BLLG_MTRTMsg();
//        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(tmsg.dsContrBllgMtrPk, bean.getDsContrBllgMtrPk());
//        tmsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(tmsg);
//        if (tmsg == null) {
//            return null;
//        }
//        return tmsg.intgMdseCd.getValue();
//    }
//
//    private static String getMdseCdFromAddlChrg(GetDefCoaTrxCdInfoBean bean) {
//        DS_CONTR_ADDL_CHRGTMsg tmsg = new DS_CONTR_ADDL_CHRGTMsg();
//        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(tmsg.dsContrAddlChrgPk, bean.getDsContrAddlChrgPk());
//        tmsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(tmsg);
//        if (tmsg == null || !ZYPCommonFunc.hasValue(tmsg.addlChrgTpCd.getValue())) {
//            return null;
//        }
//        ADDL_CHRG_TPTMsg tpTmsg = new ADDL_CHRG_TPTMsg();
//        ZYPEZDItemValueSetter.setValue(tpTmsg.glblCmpyCd, bean.getGlblCmpyCd());
//        ZYPEZDItemValueSetter.setValue(tpTmsg.addlChrgTpCd, tmsg.addlChrgTpCd.getValue());
//        tpTmsg = (ADDL_CHRG_TPTMsg) EZDTBLAccessor.findByKey(tpTmsg);
//        if (tpTmsg == null) {
//            return null;
//        }
//        return tpTmsg.intgMdseCd.getValue();
//    }
    //Del End   05/27/2016 <QC#8335>

    private static String getMdlId(GetDefCoaTrxCdInfoBean bean) {
        // START 2018/07/09 K.Kojima [QC#23685-1,ADD]
        if (ZYPCommonFunc.hasValue(bean.getMdlId())) {
            return bean.getMdlId().toString();
        }
        // END 2018/07/09 K.Kojima [QC#23685-1,ADD]
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("svcMachMstrPk", bean.getSvcMachMstrPk());
        return (String) SSM_CLNT.queryObject("getMdlId", prm);
    }

    //Mod Start 05/24/2016 <QC#8591>
    private static Map<String, Object> getAllocInfo(GetDefCoaTrxCdInfoBean bean, String mdseCd, String mdlId) {
        String ssmId = "getAllocInfo";

        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("mdseCd", mdseCd);
        prm.put("mdlId", mdlId);
        prm.put("addlChrgFlg", ZYPConstant.FLG_ON_Y);
        prm.put("paramAddlChrgFlg", bean.getAddlChrgFlg());
        if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseChrgFlg())) {
            prm.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.BASE_CHARGE);
        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg())) {
            prm.put("svcInvChrgTpCd", SVC_INV_CHRG_TP.METER_CHARGE);
        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg())) {
            prm.put("svcInvChrgTpCd", getSvcInvChrgTpCdFromAddlChrg(bean));
            ssmId = "getAllocInfoForAddl";
        }
        prm.put("defAcctCd", CM_DEF_ACCT.SERVICE_SUPPLY_COA); //Add 09/22/2017 <QC#17116>
        @SuppressWarnings("unchecked")
        Map<String, Object>outMap = (Map<String, Object>) SSM_CLNT.queryObject(ssmId, prm);
        return outMap;
    }
    //Mod End   05/24/2016 <QC#8591>

    private static String getSvcInvChrgTpCdFromAddlChrg(GetDefCoaTrxCdInfoBean bean) {
        DS_CONTR_ADDL_CHRGTMsg tmsg = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrAddlChrgPk, bean.getDsContrAddlChrgPk());
        tmsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(tmsg);
        if (tmsg == null || !ZYPCommonFunc.hasValue(tmsg.addlChrgInvTpCd.getValue())) {
            return null;
        }
        ADDL_CHRG_INV_TPTMsg tpTmsg = new ADDL_CHRG_INV_TPTMsg();
        ZYPEZDItemValueSetter.setValue(tpTmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tpTmsg.addlChrgInvTpCd, tmsg.addlChrgInvTpCd.getValue());
        // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
        // tpTmsg = (ADDL_CHRG_INV_TPTMsg) EZDTBLAccessor.findByKey(tpTmsg);
        tpTmsg = (ADDL_CHRG_INV_TPTMsg) S21CacheTBLAccessor.findByKey(tpTmsg);
        // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
        if (tpTmsg == null) {
            return null;
        }
        return tpTmsg.svcInvChrgTpCd.getValue();
    }

    // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
    // private static Map<String, Object> getContrInfo(GetDefCoaTrxCdInfoBean bean) {
    public static Map<String, Object> getContrInfo(GetDefCoaTrxCdInfoBean bean) {
    // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
        // START 2018/07/04 K.Kojima [QC#23685-1,ADD]
        if (!ZYPCommonFunc.hasValue(bean.getDsContrDtlPk())) {
            return null;
        }
        // END 2018/07/04 K.Kojima [QC#23685-1,ADD]
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("dsContrDtlPk", bean.getDsContrDtlPk());
        @SuppressWarnings("unchecked")
        Map<String, Object>outMap = (Map<String, Object>) SSM_CLNT.queryObject("getContrInfo", prm);
        return outMap;
    }

    private static S21_ORGTMsg getS21OrgInfo(GetDefCoaTrxCdInfoBean bean, String tocCd) {
        S21_ORGTMsg tmsg = new S21_ORGTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        return (S21_ORGTMsg) EZDTBLAccessor.findByKey(tmsg);
        return (S21_ORGTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
    }

    private static String getCoaExtnCd(GetDefCoaTrxCdInfoBean bean, String tocCd) {
        TOCTMsg tmsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        tmsg = (TOCTMsg) EZDTBLAccessor.findByKey(tmsg);
        tmsg = (TOCTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (tmsg != null) {
            return tmsg.coaExtnCd.getValue();
        }
        return null;

    }

    // START 2016/02/10 [QC#3186, ADD]
    private static TOCTMsg getTocInfo(GetDefCoaTrxCdInfoBean bean, String tocCd) {
        TOCTMsg tmsg = new TOCTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.tocCd, tocCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        return (TOCTMsg) EZDTBLAccessor.findByKey(tmsg);
        return (TOCTMsg) S21CacheTBLAccessor.findByKey(tmsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
    }
    // END 2016/02/10 [QC#3186, ADD]

    // START 2016/08/24 T.Tomita [QC#10787, ADD]
    private static Map<String, Object> getCoaAfflCdAndCoaChCd(GetDefCoaTrxCdInfoBean bean) {

        BigDecimal dsContrDtlPk = bean.getDsContrDtlPk();
        BigDecimal dsContrBllgMtrPk = bean.getDsContrBllgMtrPk();
        if (!ZYPCommonFunc.hasValue(dsContrDtlPk) && !ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            return null;
        }

        String billToCustCd = null;
        if (ZYPCommonFunc.hasValue(dsContrBllgMtrPk)) {
            // Usage
            DS_CONTR_BLLG_MTRTMsg dsContrBllgMtrTMsg = new DS_CONTR_BLLG_MTRTMsg();
            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(dsContrBllgMtrTMsg.dsContrBllgMtrPk, dsContrBllgMtrPk);
            dsContrBllgMtrTMsg = (DS_CONTR_BLLG_MTRTMsg) EZDTBLAccessor.findByKey(dsContrBllgMtrTMsg);
            if (dsContrBllgMtrTMsg == null) {
                return null;
            }
            billToCustCd = dsContrBllgMtrTMsg.bllgMtrBillToCustCd.getValue();
        } else {
            // Base
            // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
            // DS_CONTR_DTLTMsg dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
            // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, bean.getGlblCmpyCd());
            // ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
            // dsContrDtlTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(dsContrDtlTMsg);
            DS_CONTR_DTLTMsg dsContrDtlTMsg = null;
            if (bean.getDsContrDtlTMsg() != null) {
                dsContrDtlTMsg = bean.getDsContrDtlTMsg();
            } else {
                dsContrDtlTMsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.glblCmpyCd, bean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(dsContrDtlTMsg.dsContrDtlPk, dsContrDtlPk);
                dsContrDtlTMsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(dsContrDtlTMsg);
            }
            // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
            if (dsContrDtlTMsg == null) {
                return null;
            }
            billToCustCd = dsContrDtlTMsg.baseBillToCustCd.getValue();
        }

        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("billToCustCd", billToCustCd);
        return (Map<String, Object>) SSM_CLNT.queryObject("getCoaAfflCdAndCoaChCd", prm);
    }
    // END 2016/08/24 T.Tomita [QC#10787, ADD]

    private static String getCoaBrCd(GetDefCoaTrxCdInfoBean bean, TOCTMsg tocInfo, String invLineSplTpCd) {
        if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd) || INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            // Supply, Service
            //Mod Start 06/27/2016 <QC#10585>
            return getCoaBrCdFromFleetLineOrNonFleetLine(bean, invLineSplTpCd);
            //Mod End   06/27/2016 <QC#10585>
        } else if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Equipment
            // START 2016/02/10 [QC#3186, MOD]
            if (tocInfo != null) {
                return tocInfo.coaBrCd.getValue();
            }
            // END 2016/02/10 [QC#3186, MOD]
        }
        return null;
    }

    //Mod Start 06/27/2016 <QC#10585>
    private static String getCoaBrCdFromFleetLineOrNonFleetLine(GetDefCoaTrxCdInfoBean bean, String invLineSplTpCd) {
        if (ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
            // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
            SVC_MACH_MSTRTMsg tmsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, bean.getSvcMachMstrPk());
            // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//            tmsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tmsg);
            tmsg = (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(tmsg);
            // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
            if (tmsg != null) {
                // START 2016/02/10 [QC#3186, MOD]
                return tmsg.finBrCd.getValue();
                // END 2016/02/10 [QC#3186, MOD]
            }
        }

//        FleetBrShipToCustCdInfoBean NSXC003001Bean = new FleetBrShipToCustCdInfoBean();
//        NSXC003001Bean.setGlblCmpyCd(bean.getGlblCmpyCd());
//        NSXC003001Bean.setBillToCustCd(bean.getDsAcctNum());
//        NSXC003001Bean = NSXC003001FleetBrShipToCustCd.getFleetBrShipToCustCd(NSXC003001Bean);
//        if (NSXC003001Bean == null) {
//            return null;
//        }
//        return NSXC003001Bean.getSvcBrCd();
        // END 2015/12/15 T.Kanasaka [QC#1758, MOD]

        // START 2016/09/27 T.Kanasaka [QC#9905, MOD]
        String shipToCustCd = null;
        if (ZYPCommonFunc.hasValue(bean.getDsContrDtlPk())) {
            // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
            // DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
            // ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
            // ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
            // tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
            DS_CONTR_DTLTMsg tmsg = null;
            if (bean.getDsContrDtlTMsg() != null) {
                tmsg = bean.getDsContrDtlTMsg();
            } else {
                tmsg = new DS_CONTR_DTLTMsg();
                ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
                ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
                tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
            }
            // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
            if (tmsg != null) {
                shipToCustCd = tmsg.shipToCustCd.getValue();
            }
        }

        if (ZYPCommonFunc.hasValue(bean.getDsContrPk())) {
            DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
            inMsg.setSQLID("06");
            inMsg.setConditionValue("glblCmpyCd01", bean.getGlblCmpyCd());
            inMsg.setConditionValue("dsContrPk01", bean.getDsContrPk());
            inMsg.setConditionValue("dsContrDtlTpCd01", DS_CONTR_DTL_TP.FLEET);
            DS_CONTR_DTLTMsgArray outArray = (DS_CONTR_DTLTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
            if (outArray.getValidCount() > 0) {
                shipToCustCd = outArray.no(0).shipToCustCd.getValue();
            }
        }

        if (!ZYPCommonFunc.hasValue(shipToCustCd)) {
            NMZC610001PMsg apiMsg = new NMZC610001PMsg();
            ZYPEZDItemValueSetter.setValue(apiMsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(apiMsg.xxModeCd, NMZC610001Constant.PROCESS_DEFAULT_BILL_SHIP);
            ZYPEZDItemValueSetter.setValue(apiMsg.dsAcctNum_I1, bean.getDsAcctNum());
            if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
                ZYPEZDItemValueSetter.setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SERVICE);
            } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
                ZYPEZDItemValueSetter.setValue(apiMsg.dsTrxRuleTpCd, DS_TRX_RULE_TP.SUPPLIES);
            }

            NMZC610001 api = new NMZC610001();
            api.execute(apiMsg, ONBATCH_TYPE.BATCH);
            if (S21ApiUtil.isXxMsgId(apiMsg)) {
                return null;
            }
            shipToCustCd = apiMsg.DefaultBillShipList.no(0).shipToCustCd.getValue();
        }
        // END 2016/09/27 T.Kanasaka [QC#9905, MOD]
        return getFleetBrCd(bean, shipToCustCd);
    }
    //Mod End   06/27/2016 <QC#10585>

    private static String getCoaCcCd(GetDefCoaTrxCdInfoBean bean, TOCTMsg tocInfo, Map<String, Object> allocInfo, String fleetLineFlg, String svcByLineBizCd, String invLineSplTpCd) {
        //Mod Start 06/27/2016 <QC#10585>
        if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Service, Equipment
            // START 2016/02/10 [QC#3186, MOD]
            if (tocInfo != null) {
                return tocInfo.coaCcCd.getValue();
            }
            // END 2016/02/10 [QC#3186, MOD]
        } else if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            return (String) allocInfo.get("SVC_COA_CC_CD");
        //Mod End   06/27/2016 <QC#10585>
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            // Supply
            String svcByLineBizTpCd;
            // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
            SVC_MACH_MSTRTMsg tmsg = new SVC_MACH_MSTRTMsg();
            ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
            ZYPEZDItemValueSetter.setValue(tmsg.svcMachMstrPk, bean.getSvcMachMstrPk());
            // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//            tmsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tmsg);
            tmsg = (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(tmsg);
            // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
            if (tmsg != null) {
                svcByLineBizTpCd = tmsg.svcByLineBizTpCd.getValue();
            } else {
                svcByLineBizTpCd = svcByLineBizCd;
            }
            // END 2015/12/15 T.Kanasaka [QC#1758, MOD]
            return getCoaCcCd(allocInfo, svcByLineBizTpCd);
        }
        return null;
    }

    private static String getCoaCcCd(Map<String, Object> allocInfo, String svcByLineBizTpCd) {
        if (!ZYPCommonFunc.hasValue(svcByLineBizTpCd)) {
            return null;
        } else if (LINE_BIZ_TP.LFS.equals(svcByLineBizTpCd)) {
            // Business Type is LFS
            return (String) allocInfo.get("SPLY_LFS_COA_CC_CD");
        } else if ((LINE_BIZ_TP.PPS.equals(svcByLineBizTpCd))) {
            // Business Type is PPS
            return (String) allocInfo.get("SPLY_PPS_COA_CC_CD");
        } else if ((LINE_BIZ_TP.ESS.equals(svcByLineBizTpCd))) {
            // Business Type is ESS
            return (String) allocInfo.get("SPLY_ESS_COA_CC_CD");
        }
        return null;
    }

    private static SELL_TO_CUSTTMsg getSellToCustTMsg(GetDefCoaTrxCdInfoBean bean) {
        SELL_TO_CUSTTMsg tmsg = new SELL_TO_CUSTTMsg();
        SELL_TO_CUSTTMsgArray tmsgAryRes;
        tmsg.setSQLID("001");
        tmsg.setConditionValue("glblCmpyCd01", bean.getGlblCmpyCd());
        tmsg.setConditionValue("sellToCustCd01", bean.getDsAcctNum());
        tmsgAryRes = (SELL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(tmsg);
        if (tmsgAryRes.getValidCount() == 0) {
            return null;
        }
        return tmsgAryRes.no(0);
    }

    private static String getCoaProdCd(GetDefCoaTrxCdInfoBean bean, String mdseCd, String fleetLineFlg) {
        // START 2015/12/15 T.Kanasaka [QC#1758, MOD]
        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(svcMachMstrTMsg.svcMachMstrPk, bean.getSvcMachMstrPk());
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(svcMachMstrTMsg);
        svcMachMstrTMsg = (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(svcMachMstrTMsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        String prmMdseCd = "";
        if (svcMachMstrTMsg != null) {
            prmMdseCd = svcMachMstrTMsg.mdseCd.getValue();
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(fleetLineFlg)) {
                // Fleet
                prmMdseCd = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, bean.getGlblCmpyCd());
            } else if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg())) {
                // Additional Charge
                prmMdseCd = mdseCd;
            }
        }

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, prmMdseCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        mdseTMsg = (MDSETMsg) EZDTBLAccessor.findByKey(mdseTMsg);
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (mdseTMsg == null) {
            return null;
        }

        AJE_COA_PROD_MAPTMsg ajeCoaProdMapTMsg = new AJE_COA_PROD_MAPTMsg();
        ZYPEZDItemValueSetter.setValue(ajeCoaProdMapTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(ajeCoaProdMapTMsg.ajeCoaProdMapCd, mdseTMsg.coaProdCd.getValue());
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//        ajeCoaProdMapTMsg = (AJE_COA_PROD_MAPTMsg) EZDTBLAccessor.findByKey(ajeCoaProdMapTMsg);
        ajeCoaProdMapTMsg = (AJE_COA_PROD_MAPTMsg) S21CacheTBLAccessor.findByKey(ajeCoaProdMapTMsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (ajeCoaProdMapTMsg != null) {
            return ajeCoaProdMapTMsg.ajeCoaProdMapToCd.getValue();
        }

        return mdseTMsg.coaProdCd.getValue();
        // END 2015/12/15 T.Kanasaka [QC#1758, MOD]
    }

    private static String getCoaProjCd(GetDefCoaTrxCdInfoBean bean, String mdseCd, String invLineSplTpCd) {
        if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            // Service
            //Mod Start 06/27/2016 <QC#10585>
//            if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseChrgFlg())) {
//                // Base Charge
//                return getCoaMdseTpCdFromBaseChrg(bean, mdseCd);
//            } else if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg())) {
//                // Usage Charge
//                return ZYPCodeDataUtil.getVarCharConstValue(SVC_COA_PROJ_CD, bean.getGlblCmpyCd());
//            } else if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg())) {
//                // Additional Charge
//                String addlChrgInvTpCd = getAddlChrgInvTpCd(bean, mdseCd);
//                if (ADDL_CHRG_INV_TP.BASE.equals(addlChrgInvTpCd)) { // TODO
//                    return getCoaMdseTpCdFromBaseChrg(bean, mdseCd);
//                } else if (ADDL_CHRG_INV_TP.USAGE.equals(addlChrgInvTpCd)) { // TODO
//                    return ZYPCodeDataUtil.getVarCharConstValue(SVC_COA_PROJ_CD, bean.getGlblCmpyCd());
//                }
//            }

            String mdseCdMach = null;
            if (XX_MODE_02.equals(bean.getXxModeCd())) {
                if (ZYPConstant.FLG_ON_Y.equals(bean.getFleetLineFlg())) {
                    mdseCdMach = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, bean.getGlblCmpyCd());
                } else if (ZYPCommonFunc.hasValue(bean.getMdseCd())) {
                    mdseCdMach = bean.getMdseCd();
                }
            } else if (ZYPCommonFunc.hasValue(bean.getDsContrDtlPk())) {
                // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
                // DS_CONTR_DTLTMsg tmsg = new DS_CONTR_DTLTMsg();
                // ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
                // ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
                // tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
                DS_CONTR_DTLTMsg tmsg = null;
                if (bean.getDsContrDtlTMsg() != null) {
                    tmsg = bean.getDsContrDtlTMsg();
                } else {
                    tmsg = new DS_CONTR_DTLTMsg();
                    ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
                    ZYPEZDItemValueSetter.setValue(tmsg.dsContrDtlPk, bean.getDsContrDtlPk());
                    tmsg = (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(tmsg);
                }
                // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
                if (tmsg != null) {
                    if (DS_CONTR_DTL_TP.FLEET.equals(tmsg.dsContrDtlTpCd.getValue())) {
                        mdseCdMach = ZYPCodeDataUtil.getVarCharConstValue(SPCL_FLT_MDSE_CD, bean.getGlblCmpyCd());
                    } else {
                        SVC_MACH_MSTRTMsg tmsgMM = new SVC_MACH_MSTRTMsg();
                        ZYPEZDItemValueSetter.setValue(tmsgMM.glblCmpyCd, bean.getGlblCmpyCd());
                        ZYPEZDItemValueSetter.setValue(tmsgMM.svcMachMstrPk, tmsg.svcMachMstrPk.getValue());
                        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//                        tmsgMM = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(tmsgMM);
                        tmsgMM = (SVC_MACH_MSTRTMsg) S21CacheTBLAccessor.findByKey(tmsgMM);
                        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
                        if (tmsgMM != null) {
                            mdseCdMach = tmsgMM.mdseCd.getValue();
                        }
                    }
                }
            }
            if (mdseCdMach == null) {
                return null;
            }
            return getCoaMdseTpCdFromBaseChrg(bean, mdseCdMach);
            //Mod End   06/27/2016 <QC#10585>
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            // Supply
            return ZYPCodeDataUtil.getVarCharConstValue(SPLY_COA_PROJ_CD, bean.getGlblCmpyCd());
        } else if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Equipment
            return ZYPCodeDataUtil.getVarCharConstValue(EQUIP_COA_PROJ_CD, bean.getGlblCmpyCd());
        }
        return null;
    }

    private static String getCoaMdseTpCdFromBaseChrg(GetDefCoaTrxCdInfoBean bean, String mdseCd) {
        MDSETMsg tmsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.mdseCd, mdseCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
        tmsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(tmsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (tmsg != null) {
            return tmsg.coaMdseTpCd.getValue();
        }
        return null;
    }

    private static String getAddlChrgInvTpCd(GetDefCoaTrxCdInfoBean bean, String mdseCd) {
        DS_CONTR_ADDL_CHRGTMsg tmsg = new DS_CONTR_ADDL_CHRGTMsg();
        ZYPEZDItemValueSetter.setValue(tmsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(tmsg.dsContrAddlChrgPk, bean.getDsContrAddlChrgPk());
        tmsg = (DS_CONTR_ADDL_CHRGTMsg) EZDTBLAccessor.findByKey(tmsg);
        if (tmsg != null) {
            return tmsg.addlChrgInvTpCd.getValue();
        }
        return null;
    }

    private static BigDecimal getPrcAllocPct(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String invLineSplTpCd) {
        if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Equipment
            return (BigDecimal) allocInfo.get("EQUIP_ALLOC_PCT");
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            // Supply
            return (BigDecimal) allocInfo.get("SPLY_ALLOC_PCT");
        } else if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            // Service
            return (BigDecimal) allocInfo.get("SVC_ALLOC_PCT");
        }
        return null;
    }

    private static String getTrxCd(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String invLineSplTpCd) {
        if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Equipment
            return (String) allocInfo.get("EQUIP_TRX_CD");
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            // Supply
            return (String) allocInfo.get("SPLY_TRX_CD");
        } else if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            // Service
            return (String) allocInfo.get("SVC_TRX_CD");
        }
        return null;
    }

    private static String getTrxRsnCd(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String invLineSplTpCd) {
        if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            // Equipment
            return (String) allocInfo.get("EQUIP_TRX_RSN_CD");
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            // Supply
            return (String) allocInfo.get("SPLY_TRX_RSN_CD");
        } else if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            // Service
            return (String) allocInfo.get("SVC_TRX_RSN_CD");
        }
        return null;
    }

    // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
//    private static GetDefCoaTrxCdForOutListInfoBean setOutParam(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String mdseCd, String invLineSplTpCd) {
    // START 2018/07/05 K.Kojima [QC#23685-1,MOD]
    // private static GetDefCoaTrxCdForOutListInfoBean setOutParam(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String mdseCd, String invLineSplTpCd, SELL_TO_CUSTTMsg sellToCustTMsg) {
    private static GetDefCoaTrxCdForOutListInfoBean setOutParam(GetDefCoaTrxCdInfoBean bean, Map<String, Object> allocInfo, String mdseCd, String invLineSplTpCd, SELL_TO_CUSTTMsg sellToCustTMsg, Map<String, Object> coaAfflCdAndCoaChCd) {
    // END 2018/07/05 K.Kojima [QC#23685-1,MOD]
    // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        GetDefCoaTrxCdForOutListInfoBean outBean = new GetDefCoaTrxCdForOutListInfoBean();

        // START 2016/04/07 [QC#6566, MOD]
        String tocCd = null;
        String fleetLineFlg = null;
        String svcLineBizCd = null;
        if (XX_MODE_02.equals(bean.getXxModeCd())) {
            tocCd = bean.getTocCd();
            fleetLineFlg = bean.getFleetLineFlg();
            svcLineBizCd = bean.getSvcLineBizCd();
        } else {
            // START 2018/07/04 K.Kojima [QC#23685-1,MOD]
            // Map<String, Object> contrInfo = getContrInfo(bean);
            Map<String, Object> contrInfo = null;
            if (bean.getContrInfo() != null) {
                contrInfo = bean.getContrInfo();
            } else {
                contrInfo = getContrInfo(bean);
            }
            // END 2018/07/04 K.Kojima [QC#23685-1,MOD]
            if (contrInfo == null) {
                return null;
            }
            tocCd = (String) contrInfo.get("TOC_CD");
            fleetLineFlg = (String) contrInfo.get("FLEET_LINE_FLG");
            svcLineBizCd = (String) contrInfo.get("SVC_LINE_BIZ_CD");
        }
        // END 2016/04/07 [QC#6566, MOD]
        // START 2016/02/10 [QC#3186, MOD]
        //S21_ORGTMsg s21OrgInfo = getS21OrgInfo(bean, tocCd);
        TOCTMsg tocInfo = getTocInfo(bean, tocCd);
        if (tocInfo == null) {
            return null;
        }
        // END 2016/02/10 [QC#3186, MOD]

        outBean.setCoaCmpyCd(ZYPCodeDataUtil.getVarCharConstValue(COA_CMPY_CD, bean.getGlblCmpyCd()));
        // START 2016/02/10 [QC#3186, MOD]
        outBean.setCoaBrCd(getCoaBrCd(bean, tocInfo, invLineSplTpCd));
        // END 2016/02/10 [QC#3186, MOD]
        // START 2016/08/24 T.Tomita [QC#10787, MOD]
        outBean.setCoaAfflCd(sellToCustTMsg.coaAfflCd.getValue());
        outBean.setCoaChCd(sellToCustTMsg.coaChCd.getValue());
        // START 2018/07/05 K.Kojima [QC#23685-1,DEL]
        // Map<String, Object> coaAfflCdAndCoaChCd = getCoaAfflCdAndCoaChCd(bean);
        // END 2018/07/05 K.Kojima [QC#23685-1,DEL]
        if (coaAfflCdAndCoaChCd != null) {
            String coaAfflCd = (String) coaAfflCdAndCoaChCd.get("COA_AFFL_CD");
            if (ZYPCommonFunc.hasValue(coaAfflCd)) {
                outBean.setCoaAfflCd(coaAfflCd);
            }
            String coaChCd = (String) coaAfflCdAndCoaChCd.get("COA_CH_CD");
            if (ZYPCommonFunc.hasValue(coaChCd)) {
                outBean.setCoaChCd(coaChCd);
            }
        }
        // END 2016/08/24 T.Tomita [QC#10787, MOD]
        // START 2016/02/10 [QC#3186, MOD]
        outBean.setCoaCcCd(getCoaCcCd(bean, tocInfo, allocInfo, fleetLineFlg, svcLineBizCd, invLineSplTpCd));
        // END 2016/02/10 [QC#3186, MOD]
        // START 2016/06/20 T.Aoyagi [QC#9907, MOD]
        //Mod Start 06/27/2016 <QC#10585>
        if (INV_LINE_SPL_TP.EQUIPMENT.equals(invLineSplTpCd)) {
            outBean.setCoaAcctCd((String) allocInfo.get("EQUIP_COA_ACCT_CD"));
            outBean.setDfrdAcctgRuleCd((String) allocInfo.get("EQUIP_ACCTG_RULE_CD"));
        } else if (INV_LINE_SPL_TP.SUPPLY.equals(invLineSplTpCd)) {
            outBean.setCoaAcctCd((String) allocInfo.get("SPLY_COA_ACCT_CD"));
            outBean.setDfrdAcctgRuleCd((String) allocInfo.get("SPLY_ACCTG_RULE_CD"));
        } else if (INV_LINE_SPL_TP.SERVICE.equals(invLineSplTpCd)) {
            outBean.setCoaAcctCd((String) allocInfo.get("SVC_COA_ACCT_CD"));
            outBean.setDfrdAcctgRuleCd((String) allocInfo.get("SVC_ACCTG_RULE_CD"));
        }
        //Mod End   06/27/2016 <QC#10585>
        // END 2016/06/20 T.Aoyagi [QC#9907, MOD]
        outBean.setCoaProdCd(getCoaProdCd(bean, mdseCd, fleetLineFlg));
        outBean.setCoaProjCd(getCoaProjCd(bean, mdseCd, invLineSplTpCd));
        outBean.setCoaExtnCd(getCoaExtnCd(bean, tocCd)); // TODO
        outBean.setInvLineSplTpCd(invLineSplTpCd);
        outBean.setPrcAllocPct(getPrcAllocPct(bean, allocInfo, invLineSplTpCd));
        outBean.setTrxCd(getTrxCd(bean, allocInfo, invLineSplTpCd));
        outBean.setTrxRsnCd(getTrxRsnCd(bean, allocInfo, invLineSplTpCd));
        //Del Start 06/27/2016 <QC#10585>
//        outBean.setDfrdAcctgRuleCd((String) allocInfo.get("DFRD_ACCTG_RULE_CD"));
//        outBean.setDfrdAcctgRuleDurnAot((BigDecimal) allocInfo.get("DFRD_ACCTG_RULE_DURN_AOT"));
        //Del End   06/27/2016 <QC#10585>
        return outBean;
    }
    //Add Start 06/27/2016 <QC#10585>
    private static String getFleetBrCd(GetDefCoaTrxCdInfoBean bean, String shipToCustCd) {
        Map<String, Object> prm = new HashMap<String, Object>();
        prm.put("glblCmpyCd", bean.getGlblCmpyCd());
        prm.put("shipToCustCd", shipToCustCd);
        return (String) SSM_CLNT.queryObject("getFleetBrCd", prm);
    }
    //Add End   06/27/2016 <QC#10585>
    private static void setLogMsg(String msgId, String... param) {
        if (logMsg == null || ZYPConstant.FLG_ON_Y.equals(logMsg)) {
            String msg = S21MessageFunc.clspGetMessage(msgId, param);
            S21InfoLogOutput.println(msg);
        }
    }
    private static void setAllocLog(GetDefCoaTrxCdInfoBean bean, String mdseCd) {
        if (!ZYPCommonFunc.hasValue(mdseCd)) {
            if (XX_MODE_02.equals(bean.getXxModeCd())) {
                setLogMsg(NSXM0117W);
                return;
            } else {
                setLogMsg(NSXM0118W, bean.getDsContrDtlPk().toString());
                return;
            }
        }
        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, bean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, mdseCd);
        // START 2016/10/18 T.Kanasaka [QC#13009, MOD]
        mdseTMsg = (MDSETMsg) S21CacheTBLAccessor.findByKey(mdseTMsg);
        // END 2016/10/18 T.Kanasaka [QC#13009, MOD]
        if (mdseTMsg == null) {
            setLogMsg(NSXM0119W, mdseCd);
            return;
        }
        if (!ZYPCommonFunc.hasValue(mdseTMsg.svcAllocTpCd)) {
            setLogMsg(NSXM0120W, mdseCd);
            return;
        }
        String svcInvChrgTpCd = null;
        if (ZYPConstant.FLG_ON_Y.equals(bean.getBaseChrgFlg())) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.BASE_CHARGE;
        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getUsgChrgFlg())) {
            svcInvChrgTpCd = SVC_INV_CHRG_TP.METER_CHARGE;
        } else if (ZYPConstant.FLG_ON_Y.equals(bean.getAddlChrgFlg())) {
            svcInvChrgTpCd = getSvcInvChrgTpCdFromAddlChrg(bean);
        }
        String mdlId = null;
        if (ZYPCommonFunc.hasValue(bean.getSvcMachMstrPk())) {
            mdlId = getMdlId(bean);
        }
        setLogMsg(NSXM0121W, mdseTMsg.svcAllocTpCd.getValue(), mdlId, svcInvChrgTpCd, bean.getAddlChrgFlg());

        return;
    }
}

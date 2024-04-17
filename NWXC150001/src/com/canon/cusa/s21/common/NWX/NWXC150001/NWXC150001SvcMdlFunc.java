/*
 * <pre>Copyright (c) 2018 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NWX.NWXC150001;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MDLTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsgArray;
import business.parts.NSZC048001PMsg;

import com.canon.cusa.s21.api.NSZ.NSZC048001.NSZC048001;
import com.canon.cusa.s21.common.NWX.NWXC004001.NWXMdseTMsgThreadLocalCache;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * <pre>
 * NWXC150001SvcMdlFunc: Service Model API wrapping Class

 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/05/20   Fujitsu         S.Takami        Create          S21_NA#25604
 * </pre>
 */
public class NWXC150001SvcMdlFunc {

    /**
     * <pre>
     * get model id and set it funcBean.mdlId.
     * this method consider service exchange model if funcBean has svcConfigMstrPk
     * and in-bound and out-bound item list.
     * @param funcBean 
     * </pre>
     */
    public static void getModel(NWXC150001SvcMdlFuncParamBean funcBean) {

        String glblCmpyCd = funcBean.getGlblCmpyCd();
        if (!ZYPCommonFunc.hasValue(funcBean.getPrntMdseCd())) {
            funcBean.setBaseCmptMdseCd(getBaseCmptItem(glblCmpyCd, funcBean.getOutBndChildMdseCdList()));
        }

        NSZC048001PMsg svcMdlApiPMsg = getSvcMdlApiPMsg(funcBean);
        NSZC048001 svcMdlApi = new NSZC048001();
        svcMdlApi.execute(svcMdlApiPMsg, funcBean.getOnBatchType());

        boolean hasConfigId = ZYPCommonFunc.hasValue(funcBean.getSvcConfigMstrPk());
        if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
            // retry without parent item.
            if (!hasConfigId && ZYPCommonFunc.hasValue(svcMdlApiPMsg.prntMdseCd)) {
                int idx = svcMdlApiPMsg.xxChildMdseCdList.getValidCount();
                String prntMdseCd = svcMdlApiPMsg.prntMdseCd.getValue();
                if (ZYPCommonFunc.hasValue(prntMdseCd)) {
                    svcMdlApiPMsg.xxChildMdseCdList.no(idx++).childMdseCd.setValue(prntMdseCd);
                    svcMdlApiPMsg.xxChildMdseCdList.setValidCount(idx);
                    svcMdlApiPMsg.prntMdseCd.clear();

                    ZYPTableUtil.clear(svcMdlApiPMsg.xxMsgIdList);
                    svcMdlApi.execute(svcMdlApiPMsg, funcBean.getOnBatchType());
                }
            } else if (hasConfigId) {
                funcBean.setBaseCmptMdseCd(getBaseCmptItem(glblCmpyCd, funcBean.getOutBndChildMdseCdList()));
                String baseCmptMdseCd = funcBean.getBaseCmptMdseCd();
                ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, baseCmptMdseCd);
                List<String> chiledMdseCdList = new ArrayList<String>(0);
                if (ZYPCommonFunc.hasValue(baseCmptMdseCd)) {
                    for (int i = 0; i < svcMdlApiPMsg.xxChildMdseCdList.getValidCount(); i++) {
                        String childMdseCd = svcMdlApiPMsg.xxChildMdseCdList.no(i).childMdseCd.getValue();
                        if (NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, baseCmptMdseCd, childMdseCd)) {
                            continue;
                        }
                        chiledMdseCdList.add(childMdseCd);
                    }
                }
                int idx = 0;
                ZYPTableUtil.clear(svcMdlApiPMsg.xxChildMdseCdList);
                ZYPTableUtil.clear(svcMdlApiPMsg.xxMsgIdList);
                for (String childMdseCd : chiledMdseCdList) {
                    svcMdlApiPMsg.xxChildMdseCdList.no(idx++).childMdseCd.setValue(childMdseCd);
                }
                svcMdlApiPMsg.xxChildMdseCdList.setValidCount(idx);

                svcMdlApi.execute(svcMdlApiPMsg, funcBean.getOnBatchType());
            }
            if (S21ApiUtil.isXxMsgId(svcMdlApiPMsg)) {
                List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(svcMdlApiPMsg);
                if (msgList.size() > 0 && hasConfigId) {
                    for (S21ApiMessage apiMessage : msgList) {
                        funcBean.addErrMsgIdList(apiMessage.getXxMsgid());
                    }
                    return;
                }
            }
        }
        BigDecimal mdlId = svcMdlApiPMsg.mdlId.getValue();
        funcBean.setMdlId(mdlId);

        DS_MDLTMsg dsMdlTMsg = getDsMdl(glblCmpyCd, mdlId);
        if (dsMdlTMsg != null) {
            funcBean.setMdlDescTxt(dsMdlTMsg.mdlDescTxt.getValue());
            funcBean.setSvcMdlTpCd(dsMdlTMsg.svcMdlTpCd.getValue());
        }

        MDL_NMTMsg mdlNmTMsg = getMdlNm(glblCmpyCd, mdlId);
        if (mdlNmTMsg != null) {
            funcBean.setMdlNm(mdlNmTMsg.t_MdlNm.getValue());
        }
        if (!hasConfigId) {
            funcBean.setPrntMdseCd(svcMdlApiPMsg.prntMdseCd.getValue());
        }
    }

    /**
     * <pre>
     * get merchandise code which should be set base component flag 'Y' in the list.
     * if there is no base component flag item, this method will return null.
     * @param glblCmpyCd Global Company Code
     * @param outBndChildMdseCdList item list.
     * @return item code which should be set base component flag 'Y'
     * </pre>
     */
    public static String getBaseCmptItem(String glblCmpyCd, List<String> outBndChildMdseCdList) {

        List<String> mainMachMdseTpList = NWXC150001DsCheck.getMainMachineMdseTypeList(glblCmpyCd);

        if (mainMachMdseTpList == null //
                || mainMachMdseTpList.isEmpty()) {
            return null;
        }

        String baseCmptMdseCd = null;
        for (String mdseCd : outBndChildMdseCdList) {
            MDSETMsg mdseTMsg = NWXMdseTMsgThreadLocalCache.getInstance().get(glblCmpyCd, mdseCd);
            if (mdseTMsg == null) {
                continue;
            }
            if (S21StringUtil.isEquals(MDSE_TP.SET, mdseTMsg.mdseTpCd.getValue())) {
                continue;
            }
            if (mainMachMdseTpList.contains(mdseTMsg.coaMdseTpCd.getValue())) {
                return mdseCd;
            }
            if (baseCmptMdseCd == null //
                    && S21StringUtil.isEquals(ZYPConstant.FLG_ON_Y, mdseTMsg.instlBaseCtrlFlg.getValue())) {
                baseCmptMdseCd = mdseCd;
            }
        }
        return baseCmptMdseCd;
    }

    private static NSZC048001PMsg getSvcMdlApiPMsg(NWXC150001SvcMdlFuncParamBean funcBean) {

        String glblCmpyCd = funcBean.getGlblCmpyCd();
        BigDecimal svcConfigMstrPk = funcBean.getSvcConfigMstrPk();
        NSZC048001PMsg svcMdlApiPMsg = new NSZC048001PMsg();

        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.glblCmpyCd, funcBean.getGlblCmpyCd());
        ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.slsDt, funcBean.getSlsDt());

        List<SVC_MACH_MSTRTMsg> relatedSvcMachMstrTMsgList = null;
        int idx = 0;
        boolean hasConfigId = ZYPCommonFunc.hasValue(svcConfigMstrPk);
        if (hasConfigId) {
            BigDecimal parentIBId = parentItemOfIb(glblCmpyCd,  svcConfigMstrPk);
            relatedSvcMachMstrTMsgList = getRelatedSvcMachMstrArray(glblCmpyCd, svcConfigMstrPk);
            for (SVC_MACH_MSTRTMsg svcMachMstrTMsg : relatedSvcMachMstrTMsgList) {
                if (isWillbeReturnedItem(funcBean, svcMachMstrTMsg)) {
                    continue;
                }
                if (isEqualsBigDecimal(parentIBId, svcMachMstrTMsg.svcMachMstrPk.getValue())) {
                    ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.prntMdseCd, svcMachMstrTMsg.mdseCd);
                } else {
                    ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.xxChildMdseCdList.no(idx++).childMdseCd, svcMachMstrTMsg.mdseCd);
                }
            }
            for (String itemCode : funcBean.getOutBndChildMdseCdList()) {
                ZYPEZDItemValueSetter.setValue(svcMdlApiPMsg.xxChildMdseCdList.no(idx++).childMdseCd, itemCode);
            }
        } else {
            String prntMdseCd = funcBean.getPrntMdseCd();
            String baseCmptItemCode = funcBean.getBaseCmptMdseCd();
            if (!ZYPCommonFunc.hasValue(prntMdseCd) //
                    && !ZYPCommonFunc.hasValue(baseCmptItemCode)) {
                baseCmptItemCode = getBaseCmptItem(glblCmpyCd, funcBean.getOutBndChildMdseCdList());
            }
            if (ZYPCommonFunc.hasValue(prntMdseCd)) {
                svcMdlApiPMsg.prntMdseCd.setValue(prntMdseCd);
            }
            for (String itemCode : funcBean.getOutBndChildMdseCdList()) {
                if (!ZYPCommonFunc.hasValue(prntMdseCd) //
                        && ZYPCommonFunc.hasValue(baseCmptItemCode) //
                        && NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd, baseCmptItemCode, itemCode)) {
                    svcMdlApiPMsg.prntMdseCd.setValue(itemCode);
                } else {
                    svcMdlApiPMsg.xxChildMdseCdList.no(idx++).childMdseCd.setValue(itemCode);
                }
            }
        }
        svcMdlApiPMsg.xxChildMdseCdList.setValidCount(idx);
        return svcMdlApiPMsg;
    }

    private static List<SVC_MACH_MSTRTMsg> getRelatedSvcMachMstrArray(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        List<SVC_MACH_MSTRTMsg> rsltList = new ArrayList<SVC_MACH_MSTRTMsg>(0);

        SVC_MACH_MSTRTMsg svcMachMstrTMsg = new SVC_MACH_MSTRTMsg();
        svcMachMstrTMsg.setSQLID("016");
        svcMachMstrTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        svcMachMstrTMsg.setConditionValue("svcConfigMstrPk01", svcConfigMstrPk);

        SVC_MACH_MSTRTMsgArray svcMachMstrTmsgArray = (SVC_MACH_MSTRTMsgArray) EZDTBLAccessor.findByCondition(svcMachMstrTMsg);
        if (svcMachMstrTmsgArray != null) {
            for (int i = 0; i < svcMachMstrTmsgArray.getValidCount(); i++) {
                String svcMachMstrStsCd = svcMachMstrTmsgArray.no(i).svcMachMstrStsCd.getValue();
                if (S21StringUtil.isEquals(SVC_MACH_MSTR_STS.REMOVED, svcMachMstrStsCd) //
                        || S21StringUtil.isEquals(SVC_MACH_MSTR_STS.WAITING_FOR_REMOVAL, svcMachMstrStsCd) //
                        || S21StringUtil.isEquals(SVC_MACH_MSTR_STS.TERMINATED, svcMachMstrStsCd)) {
                    continue;
                }
                rsltList.add(svcMachMstrTmsgArray.no(i));
            }
        }
        return rsltList;
    }

    private static boolean isWillbeReturnedItem(NWXC150001SvcMdlFuncParamBean funcBean, SVC_MACH_MSTRTMsg svcMachMstrTMsg) {

        if (funcBean.getInBndChildBeanList() == null //
                || funcBean.getInBndChildBeanList().isEmpty()) {
            return false;
        }

        String glblCmpyCd = funcBean.getGlblCmpyCd();
        String mdseCd = svcMachMstrTMsg.mdseCd.getValue();
        String serNum = svcMachMstrTMsg.serNum.getValue();
        BigDecimal svcMachMstrPk = svcMachMstrTMsg.svcMachMstrPk.getValue();

        for (NWXC150001SvcMdlFuncInbndChildBean inbndChildBean : funcBean.getInBndChildBeanList()) {
            String compMdseCd = inbndChildBean.getMdseCd();
            String compSerNum = inbndChildBean.getSerNum();
            BigDecimal compSvcMachMstrPk = inbndChildBean.getSvcMachMstrPk();
            if (NWXC150001DsCheck.isNearEqualsItem(glblCmpyCd,  mdseCd, compMdseCd) //
                    && isEqualsBigDecimal(svcMachMstrPk, compSvcMachMstrPk) //
                    && isEqualsString(serNum, compSerNum)) {
                return true;
            }
        }
        return false;
    }

    private static BigDecimal parentItemOfIb(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = getSvcConfigMstrTMsg(glblCmpyCd, svcConfigMstrPk);
        if (svcConfigMstrTMsg != null) {
            return svcConfigMstrTMsg.svcMachMstrPk.getValue();
        } else {
            return null;
        }
    }

    private static SVC_CONFIG_MSTRTMsg getSvcConfigMstrTMsg(String glblCmpyCd, BigDecimal svcConfigMstrPk) {

        SVC_CONFIG_MSTRTMsg svcConfigMstrTMsg = new SVC_CONFIG_MSTRTMsg();
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(svcConfigMstrTMsg.svcConfigMstrPk, svcConfigMstrPk);
        return (SVC_CONFIG_MSTRTMsg) S21CacheTBLAccessor.findByKey(svcConfigMstrTMsg);
    }

    private static boolean isEqualsBigDecimal(BigDecimal cmp1, BigDecimal cmp2) {

        if (cmp1 == null && cmp2 == null) {
            return true;
        }
        if (cmp1 == null && cmp2 != null) {
            return false;
        }
        if (cmp1 != null && cmp2 == null) {
            return false;
        }
        return cmp1.compareTo(cmp2) == 0;
    }

    private static boolean isEqualsString(String cmp1, String cmp2) {

        if (cmp1 == null && cmp2 == null) {
            return true;
        }
        if (cmp1 == null && cmp2 != null) {
            return false;
        }
        if (cmp1 != null && cmp2 == null) {
            return false;
        }
        return cmp1.equals(cmp2);
    }

    private static DS_MDLTMsg getDsMdl(String glblCmpyCd, BigDecimal mdlId) {

        DS_MDLTMsg dsMdlTMsg = new DS_MDLTMsg();
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsMdlTMsg.mdlId, mdlId);
        return (DS_MDLTMsg) S21CacheTBLAccessor.findByKey(dsMdlTMsg);
    }

    private static MDL_NMTMsg getMdlNm(String glblCmpyCd, BigDecimal mdlId) {

        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_GlblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_MdlId, mdlId);
        return (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(mdlNmTMsg);
    }
}

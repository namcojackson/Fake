/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.SVC_CONFIG_MSTRTMsg;
import business.db.SVC_MACH_MSTRTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get End Of Life Info
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/16   Hitachi         K.Kasai         Create          N/A
 * 07/21/2016   Hitachi         T.Iwamoto       Update          QC#14705
 * 10/05/2016   Hitachi         T.Tomita        Update          QC#14145
 * 06/08/2018   Hitachi         U.Kim           Update          QC#25449
 * 2018/06/13   Hitachi         A.Kohinata      Update          QC#26028
 * 2018/08/24   CITS            M.Naito         Update          QC#27901
 *</pre>
 */
public class NSXC001001GetEndOfLifeInfo {

    /**
     * S21SsmBatchClient object.
     */
    private static final S21SsmBatchClient ssmClient = S21SsmBatchClient.getClient(NSXC001001GetEndOfLifeInfo.class);

    /** Input parameter "Global Company Code" is a mandatory field. */
    private static final String NSXM0003E = "NSXM0003E";

    /** Input parameter "Service Machine Master PK" is a mandatory field. */
    private static final String NSXM0101E = "NSXM0101E";

    /** Input parameter "Eol Date" is a mandatory field. */
    private static final String NSXM0102E = "NSXM0102E";

    /** The date format is not YYYYMMDD. Please check the format of "Eol Date". */
    private static final String NSXM0103E = "NSXM0103E";

    /** The Machine is not existed. Please check the "Service Machine Master PK". */
    private static final String NSXM0104E = "NSXM0104E";

    /** Model for the entered Machine Merchandise has not yet been registered. */
    private static final String NSXM0105E = "NSXM0105E";

    /**
     * getEndOfLifeInfo
     * @param infoBean EndOfLifeBean
     * @return boolean
     */
    public static boolean getEndOfLifeInfo(EndOfLifeBean infoBean) {

        //input check & date format check
        inputCheck(infoBean);
        if (hasValue(infoBean.getXxMsgId())) {
            return false;
        }

        boolean existMdlInfo;
        BigDecimal mdlId = null;
        SVC_MACH_MSTRTMsg outMsg = null;
        SVC_CONFIG_MSTRTMsg outConfMsg = null;
        //get Model Id from SVC_MACH_MSTR ,SVC_CONFIG_MSTR
        outMsg = getSvcMachMstr(infoBean);
        if (outMsg == null) {
            infoBean.setXxMsgId(NSXM0104E);
            return false;
        }
        if (!hasValue(outMsg.svcConfigMstrPk)) {
            existMdlInfo = false;
        } else {
            outConfMsg = getSvcConfigMstr(infoBean, outMsg);
            if (outConfMsg == null || !hasValue(outConfMsg.mdlId)) {
                existMdlInfo = false;
            } else {
                existMdlInfo = true;
                mdlId = outConfMsg.mdlId.getValue();
            }
        }

        // START 2018/08/24 M.Naito [QC#27901, DEL]
//        // add start 2016/10/05 CSA Defect#14145
//        if (!existMdlInfo && !hasValue(outMsg.svcConfigMstrPk)) {
//            mdlId = (BigDecimal) ssmClient.queryObject("getMdlIdFromConfigMstrDtl", setParamForConfigMstrDtl(outMsg));
//            if (mdlId == null) {
//                existMdlInfo = false;
//            } else {
//                existMdlInfo = true;
//            }
//        }
//        // add end 2016/10/05 CSA Defect#14145
        // END 2018/08/24 M.Naito [QC#27901, DEL]

        // add start 2016/09/28 CSA Defect#14705
        //if Model Id doesn't get from SVC_CONFIG_MSTR, get Model Id CPO
        if (!existMdlInfo) {
            mdlId = (BigDecimal) ssmClient.queryObject("getMdlIdFromCpo", setParamForCpo(infoBean, outMsg));
            if (mdlId == null) {
                existMdlInfo = false;
            } else {
                existMdlInfo = true;
            }
        }
        // add end 2016/09/28 CSA Defect#14705

        //if Model Id doesn't get from SVC_CONFIG_MSTR & CPO, get Model Id DS_MDL_CONFIG
        if (!existMdlInfo) {
            mdlId = (BigDecimal) ssmClient.queryObject("getMdlIdFromMdlConfig", setParamForMdlConfig(infoBean, outMsg));
            if (mdlId == null) {
                infoBean.setXxMsgId(NSXM0105E);
                return false;
            }
        }

        //judge contract, service is available
        boolean isOverEndOfLife = false;
        // mod start 2018/06/13 QC#26028
//        Map<String, String> getAvalFlgFromEol = (Map<String, String>) ssmClient.queryObject("getAvalFlgFromEol", setParam(infoBean, mdlId, outMsg));
//        if (getAvalFlgFromEol != null && !getAvalFlgFromEol.isEmpty()) {
//            isOverEndOfLife = true;
//            infoBean.setContrAvalFlg(getAvalFlgFromEol.get("CONTR_AVAL_FLG"));
//            infoBean.setSvcAvalFlg(getAvalFlgFromEol.get("SVC_AVAL_FLG"));
//            if (hasValue(outMsg.serNum)) {
//                List<Map<String, String>> getAvalFlgFromEolExList = (List<Map<String, String>>) ssmClient.queryObjectList("getAvalFlgFromEolEx", setParam(infoBean, mdlId, outMsg));
//                if (getAvalFlgFromEolExList != null && !getAvalFlgFromEolExList.isEmpty()) {
//                    boolean isExistEolEx = false;
//                    for (Map<String, String> getAvalFlgFromEolEx : getAvalFlgFromEolExList) {
//                        //exist EOL_EX data
//                        // mod start 2018/06/13 QC#26028
//                        // START 2018/06/08 U.Kim [QC#25449, MOD]
//                        // if ((ZYPDateUtil.compare(getAvalFlgFromEolEx.get("DS_MDL_EOL_DT"), infoBean.getEolDt()) <= 0)
//                        if ((ZYPDateUtil.compare(getAvalFlgFromEolEx.get("DS_MDL_EOL_DT"), infoBean.getEolDt()) < 0)
//                                // END 2018/06/08 U.Kim [QC#25449, MOD]
//                                && (ZYPDateUtil.compare(getAvalFlgFromEol.get("DS_MDL_EOL_DT"), getAvalFlgFromEolEx.get("DS_MDL_EOL_DT")) <= 0)) {
//                            isOverEndOfLife = true;
//                            isExistEolEx = true;
//                            infoBean.setContrAvalFlg(getAvalFlgFromEolEx.get("CONTR_AVAL_FLG"));
//                            infoBean.setSvcAvalFlg(getAvalFlgFromEolEx.get("SVC_AVAL_FLG"));
//                            break;
//                        }
//                    }
//                    if (!isExistEolEx) {
//                        isOverEndOfLife = false;
//                        infoBean.setContrAvalFlg(ZYPConstant.FLG_ON_Y);
//                        infoBean.setSvcAvalFlg(ZYPConstant.FLG_ON_Y);
//                    }
//                }
//            }
//        } else {
//            infoBean.setContrAvalFlg(ZYPConstant.FLG_ON_Y);
//            infoBean.setSvcAvalFlg(ZYPConstant.FLG_ON_Y);
//        }
        BigDecimal count = (BigDecimal) ssmClient.queryObject("countEOL", setParam(infoBean, mdlId, "contrAvalFlg"));
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            isOverEndOfLife = true;
            infoBean.setContrAvalFlg(ZYPConstant.FLG_OFF_N);
        } else {
            infoBean.setContrAvalFlg(ZYPConstant.FLG_ON_Y);
        }
        count = (BigDecimal) ssmClient.queryObject("countEOL", setParam(infoBean, mdlId, "svcAvalFlg"));
        if (count.compareTo(BigDecimal.ZERO) > 0) {
            isOverEndOfLife = true;
            infoBean.setSvcAvalFlg(ZYPConstant.FLG_OFF_N);
        } else {
            infoBean.setSvcAvalFlg(ZYPConstant.FLG_ON_Y);
        }
        // mod end 2018/06/13 QC#26028

        //get EOL comment from DS_MDL
        if (isOverEndOfLife) {
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
            queryParam.put("mdlId", mdlId);
            queryParam.put("eolDt", infoBean.getEolDt());
            List<Map<String, String>> eolCommentList = (List<Map<String, String>>) ssmClient.queryObjectList("getEolComment", queryParam);
            if (eolCommentList != null && !eolCommentList.isEmpty()) {
                Map<String, String> eolComment = eolCommentList.get(0);
                infoBean.setEolSvcContrCmntTxt(eolComment.get("EOL_SVC_CONTR_CMNT_TXT"));
                infoBean.setEolTmMatCmntTxt(eolComment.get("EOL_TM_MAT_CMNT_TXT"));
                infoBean.setEolTechSprtCmntTxt(eolComment.get("EOL_TECH_SPRT_CMNT_TXT"));
                infoBean.setEolOthCmntTxt(eolComment.get("EOL_OTH_CMNT_TXT"));
            }
        }
        return true;
    }

    private static Map<String, Object> setParamForMdlConfig(EndOfLifeBean infoBean, SVC_MACH_MSTRTMsg outMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("mdseCd", outMsg.mdseCd.getValue());
        queryParam.put("eolDt", infoBean.getEolDt());
        queryParam.put("maxDate", "20991231");
        return queryParam;
    }

    // add start 2016/09/28 CSA Defect#14705
    private static Map<String, Object> setParamForCpo(EndOfLifeBean infoBean, SVC_MACH_MSTRTMsg outMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("svcMachMstrPk", outMsg.svcMachMstrPk.getValue());
        return queryParam;
    }
    // add end 2016/09/28 CSA Defect#14705

    // add start 2016/10/05 CSA Defect#14145
    private static  Map<String, Object> setParamForConfigMstrDtl(SVC_MACH_MSTRTMsg outMsg) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", outMsg.glblCmpyCd.getValue());
        queryParam.put("svcMachMstrPk", outMsg.svcMachMstrPk.getValue());
        return queryParam;
    }
    // add end 2016/10/05 CSA Defect#14145

    private static SVC_CONFIG_MSTRTMsg getSvcConfigMstr(EndOfLifeBean infoBean, SVC_MACH_MSTRTMsg outMsg) {
        SVC_CONFIG_MSTRTMsg outConfMsg;
        SVC_CONFIG_MSTRTMsg inConfMsg = new SVC_CONFIG_MSTRTMsg();
        setValue(inConfMsg.glblCmpyCd, infoBean.getGlblCmpyCd());
        setValue(inConfMsg.svcConfigMstrPk, outMsg.svcConfigMstrPk);
        outConfMsg = (SVC_CONFIG_MSTRTMsg) EZDTBLAccessor.findByKey(inConfMsg);
        return outConfMsg;
    }

    private static SVC_MACH_MSTRTMsg getSvcMachMstr(EndOfLifeBean infoBean) {
        SVC_MACH_MSTRTMsg outMsg;
        SVC_MACH_MSTRTMsg inMsg = new SVC_MACH_MSTRTMsg();
        setValue(inMsg.glblCmpyCd, infoBean.getGlblCmpyCd());
        setValue(inMsg.svcMachMstrPk, infoBean.getSvcMachMstrPk());
        outMsg = (SVC_MACH_MSTRTMsg) EZDTBLAccessor.findByKey(inMsg);
        return outMsg;
    }

    // mod start 2018/06/13 QC#26028
    private static Map<String, Object> setParam(EndOfLifeBean infoBean, BigDecimal mdlId, String avlFlgParamName) {
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", infoBean.getGlblCmpyCd());
        queryParam.put("mdlId", mdlId);
        queryParam.put("svcMachMstrPk", infoBean.getSvcMachMstrPk());
        queryParam.put("eolDt", infoBean.getEolDt());
        queryParam.put(avlFlgParamName, ZYPConstant.FLG_OFF_N);
        return queryParam;
    }
    // mod end 2018/06/13 QC#26028

    private static void inputCheck(EndOfLifeBean infoBean) {

        if (!ZYPCommonFunc.hasValue(infoBean.getGlblCmpyCd())) {
            infoBean.setXxMsgId(NSXM0003E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(infoBean.getSvcMachMstrPk())) {
            infoBean.setXxMsgId(NSXM0101E);
            return;
        }
        if (!ZYPCommonFunc.hasValue(infoBean.getEolDt())) {
            infoBean.setXxMsgId(NSXM0102E);
            return;
        } else {
            if (!ZYPDateUtil.checkDate(infoBean.getEolDt())) {
                infoBean.setXxMsgId(NSXM0103E);
                return;
            }
        }
    }
}

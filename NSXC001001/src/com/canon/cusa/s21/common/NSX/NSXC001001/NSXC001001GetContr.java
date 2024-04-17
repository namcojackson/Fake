/**
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_CONTRTMsg;
import business.db.DS_CONTR_DTLTMsg;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_CONTR_DTL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MAN_TRMN_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;

/**
 * <pre>
 * Get Contract
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 10/19/2015   Hitachi         T.Tsuchida      Create          N/A
 * 08/25/2016   Hitachi         A.Kohinata      Update          QC#13301
 * 09/20/2016   Hitachi         A.Kohinata      Update          QC#12898
 * 10/17/2016   Hitachi         A.Kohinata      Update          QC#15293
 * 12/07/2016   Hitachi         Y.Takeno        Update          QC#15200
 * 2017/01/04   Hitachi         K.Kojima        Update          QC#16513
 * 2018/05/25   Hitachi         K.Kim           Update          QC#15410(Sol#246)
 * 2018/07/25   Hitachi         T.Tomita        Update          QC#27414
 * 2024/04/01   Hitachi         T.Fukuta        Update          QC#63548
 *</pre>
 */
public class NSXC001001GetContr {

    /**
     * SSM Batch Client
     */
    private static final S21SsmBatchClient SSM_CLIENT = S21SsmBatchClient.getClient(NSXC001001GetContr.class);

    /**
     * Get Direct Sales Contract Detail By Machine Master Pk
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getContrDtlByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlByMachMstrPk", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
    }

    /**
     * Get Direct Sales Contract Detail By Machine Master Pk Include Warranty
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getContrDtlByMachMstrPkInclWarranty(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlByMachMstrPk", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
    }

    // START 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]
    /**
     * Get Direct Sales Contract Detail By Machine Master Pk List
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return List<DS_CONTR_DTLTMsg>
     */
    public static List<DS_CONTR_DTLTMsg> getContrDtlByMachMstrPkList(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]

        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getContrDtlByMachMstrPkList", ssmParam);

        if (rsltMapList.size() == 0) {
            return new ArrayList<DS_CONTR_DTLTMsg>();
        }

        List<DS_CONTR_DTLTMsg> contrDtlByMachMstrPkList = new ArrayList<DS_CONTR_DTLTMsg>();
        // Mod Start 2018/07/25 QC#27414
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        for (int i = 0; i < rsltMapList.size(); i++) {
            Map<String, Object> contrMap = (Map<String, Object>) rsltMapList.get(i);
            // contrDtlByMachMstrPkList.add(getDsContrDtlTmsg((String) contrMap.get("GLBL_CMPY_CD"), (BigDecimal) contrMap.get("DS_CONTR_DTL_PK")));
            dsContrDtlTMsg = getDsContrDtlTmsg((String) contrMap.get("GLBL_CMPY_CD"), (BigDecimal) contrMap.get("DS_CONTR_DTL_PK"));
            if (dsContrDtlTMsg != null) {
                contrDtlByMachMstrPkList.add(dsContrDtlTMsg);
            }
        }
        // Mod End 2018/07/25 QC#27414
        return contrDtlByMachMstrPkList;
    }

    /**
     * Get Direct Sales Contract Detail By Machine Master Pk Include Warranty List
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return List<DS_CONTR_DTLTMsg>
     */
    public static List<DS_CONTR_DTLTMsg> getContrDtlByMachMstrPkInclWarrantyList(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]

        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getContrDtlByMachMstrPkList", ssmParam);

        if (rsltMapList.size() == 0) {
            return new ArrayList<DS_CONTR_DTLTMsg>();
        }

        List<DS_CONTR_DTLTMsg> contrDtlByMachMstrPkList = new ArrayList<DS_CONTR_DTLTMsg>();
        // Mod Start 2018/07/25 QC#27414
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        for (int i = 0; i < rsltMapList.size(); i++) {
            Map<String, Object> contrMap = (Map<String, Object>) rsltMapList.get(i);
            // contrDtlByMachMstrPkList.add(getDsContrDtlTmsg((String) contrMap.get("GLBL_CMPY_CD"), (BigDecimal) contrMap.get("DS_CONTR_DTL_PK")));
            dsContrDtlTMsg = getDsContrDtlTmsg((String) contrMap.get("GLBL_CMPY_CD"), (BigDecimal) contrMap.get("DS_CONTR_DTL_PK"));
            if (dsContrDtlTMsg != null) {
                contrDtlByMachMstrPkList.add(dsContrDtlTMsg);
            }
        }
        // Mod End 2018/07/25 QC#27414
        return contrDtlByMachMstrPkList;
    }
    // END 2018/05/25 K.Kim [QC#15410(Sol#246),ADD]

    // mod start 2016/12/07 CSA Defect#15200
    // mod start 2016/10/17 CSA Defect#15293
    // add start 2016/09/20 CSA Defect#12898
    /**
     * Get Direct Sales Contract Detail Include Warranty For Supply Order
     * 
     * @param glblCmpyCd Global Company Code
     * @param dsContrDtlPk DS Contract Detail PK
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getContrDtlInclWarrantyForSplyOrd(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("dsContrDtlPk", dsContrDtlPk);
        ssmParam.put("dsContrDtlTpFleet", DS_CONTR_DTL_TP.FLEET);
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlInclWarrantyForSplyOrd", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
    }
    // add end 2016/09/20 CSA Defect#12898
    // mod end 2016/10/17 CSA Defect#15293
    // mod end 2016/12/07 CSA Defect#15200

    // add start 2016/10/17 CSA Defect#15293
    /**
     * Get Direct Sales Contract By Machine Master Pk
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return DS_CONTRTMsg
     */
    public static DS_CONTRTMsg getContrByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlByMachMstrPk", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_PK"));
    }
    // add end 2016/10/17 CSA Defect#15293

    /**
     * Get Direct Sales Contract By Machine Master Pk Include Warranty
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return DS_CONTRTMsg
     */
    public static DS_CONTRTMsg getContrByMachMstrPkInclWarranty(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlByMachMstrPk", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_PK"));
    }

    /**
     * Get Direct Sales Contract Detail By Serial#
     * 
     * @param glblCmpyCd Global Company Code
     * @param serNum Serial number
     * @param trxDt Transaction Date
     * @return DS_CONTR_DTLTMsg
     */
    public static DS_CONTR_DTLTMsg getContrDtlBySerNum(String glblCmpyCd, String serNum, String trxDt) {
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("serNum", serNum);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);

        Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getContrDtlBySerNum", ssmParam);

        if (rsltMap == null) {
            return null;
        }
        return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
    }

    // add start 2016/08/25 CSA Defect#13301
    /**
     * Get Entitlement Available Direct Sales Contract Detail By Machine Master Pk
     * 
     * @param glblCmpyCd Global Company Code
     * @param svcMachMstrPk Service Machine Master PK
     * @param trxDt Transaction Date
     * @return DS_CONTR_DTLTMsg
     */
    // START 2017/01/04 K.Kojima [QC#16513,MOD]
    // public static DS_CONTR_DTLTMsg getEttlAvalContrDtlByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
    public static List<DS_CONTR_DTLTMsg> getEttlAvalContrDtlByMachMstrPk(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
    // END 2017/01/04 K.Kojima [QC#16513,MOD]
        final Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("svcMachMstrPk", svcMachMstrPk);
        ssmParam.put("contrEffFromDt", trxDt);
        ssmParam.put("contrEffThruDt", trxDt);
        // START 2017/01/04 K.Kojima [QC#16513,DEL]
        // ssmParam.put("dsContrCatgCd", DS_CONTR_CATG.WARRANTY);
        // END 2017/01/04 K.Kojima [QC#16513,DEL]
        // START 2024/04/01 T.Fukuta [QC#63548,ADD]
        ssmParam.put("manTrmnTpCd", MAN_TRMN_TP.ALL_PERIOD);
        // END 2024/04/01 T.Fukuta [QC#63548,ADD]

        // START 2017/01/04 K.Kojima [QC#16513,MOD]
        // Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getEttlAvalContrDtlByMachMstrPk", ssmParam);
        List<Map<String, Object>> rsltMapList = (List<Map<String, Object>>) SSM_CLIENT.queryObjectList("getEttlAvalContrDtlByMachMstrPk", ssmParam);
        List<DS_CONTR_DTLTMsg> tMsgList = new ArrayList<DS_CONTR_DTLTMsg>(rsltMapList.size());
        // END 2017/01/04 K.Kojima [QC#16513,MOD]

        if (rsltMapList == null) {
            // START 2017/01/04 K.Kojima [QC#16513,MOD]
            // return null;
            return tMsgList;
            // END 2017/01/04 K.Kojima [QC#16513,MOD]
        }
        // START 2017/01/04 K.Kojima [QC#16513,ADD]
        // Mod Start 2018/07/25 QC#27414
        DS_CONTR_DTLTMsg dsContrDtlTMsg;
        for (int i = 0; i < rsltMapList.size(); i++) {
            Map<String, Object> rsltMap = rsltMapList.get(i);
            // tMsgList.add(getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK")));
            dsContrDtlTMsg = getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
            if (dsContrDtlTMsg != null) {
                tMsgList.add(dsContrDtlTMsg);
            }
        }
        // Mod End 2018/07/25 QC#27414
        // END 2017/01/04 K.Kojima [QC#16513,ADD]
        // START 2017/01/04 K.Kojima [QC#16513,MOD]
        // return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
        return tMsgList;
        // END 2017/01/04 K.Kojima [QC#16513,MOD]
    }

    // START 2017/01/04 K.Kojima [QC#16513,DEL]
    // /**
    //  * Get Entitlement Available Direct Sales Contract Detail By Machine Master Pk Include Warranty
    //  * 
    //  * @param glblCmpyCd Global Company Code
    //  * @param svcMachMstrPk Service Machine Master PK
    //  * @param trxDt Transaction Date
    //  * @return DS_CONTR_DTLTMsg
    //  */
    // public static DS_CONTR_DTLTMsg getEttlAvalContrDtlByMachMstrPkInclWarranty(String glblCmpyCd, BigDecimal svcMachMstrPk, String trxDt) {
    //     final Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     ssmParam.put("glblCmpyCd", glblCmpyCd);
    //     ssmParam.put("svcMachMstrPk", svcMachMstrPk);
    //     ssmParam.put("contrEffFromDt", trxDt);
    //     ssmParam.put("contrEffThruDt", trxDt);
    // 
    //     Map<String, Object> rsltMap = (Map<String, Object>) SSM_CLIENT.queryObject("getEttlAvalContrDtlByMachMstrPk", ssmParam);
    // 
    //     if (rsltMap == null) {
    //         return null;
    //     }
    //     return getDsContrDtlTmsg((String) rsltMap.get("GLBL_CMPY_CD"), (BigDecimal) rsltMap.get("DS_CONTR_DTL_PK"));
    // }
    // END 2017/01/04 K.Kojima [QC#16513,DEL]
    // add end 2016/08/25 CSA Defect#13301

    private static DS_CONTR_DTLTMsg getDsContrDtlTmsg(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        DS_CONTR_DTLTMsg inMsg = new DS_CONTR_DTLTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrDtlPk, dsContrDtlPk);
        return (DS_CONTR_DTLTMsg) EZDTBLAccessor.findByKey(inMsg);
    }

    private static DS_CONTRTMsg getDsContrTmsg(String glblCmpyCd, BigDecimal dsContrPk) {
        DS_CONTRTMsg inMsg = new DS_CONTRTMsg();
        setValue(inMsg.glblCmpyCd, glblCmpyCd);
        setValue(inMsg.dsContrPk, dsContrPk);
        return (DS_CONTRTMsg) EZDTBLAccessor.findByKey(inMsg);
    }
}

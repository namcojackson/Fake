/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL2620.common;

import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM0007I;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM0179E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8361E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8397E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8448E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8450E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8511E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NMAM8637E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.NZZM0007E;
import static business.blap.NMAL2620.constant.NMAL2620Constant.SLS_FLG;
import static business.blap.NMAL2620.constant.NMAL2620Constant.ZZM9037E;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCItem;
import business.blap.NMAL2620.NMAL2620CMsg;
import business.blap.NMAL2620.NMAL2620Query;
import business.blap.NMAL2620.NMAL2620SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Territory Mass Update
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2016/04/26   Fujitsu         C.Yokoi         Update          CSA-QC#7594
 * 2016/07/21   Hitachi         J.Kim           Update          CSA-QC#11908
 *</pre>
 */
public class NMAL2620CommonLogic {

    /**
     * Create Pull Down
     * @param cMsg NMAL2620CMsg
     */
    public static void createPullDown(NMAL2620CMsg cMsg) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgStruTpCd", ORG_STRU_TP.TERRITORY_STRUCTURE);
        queryParam.put("slsFlg", SLS_FLG);
        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().getBizAreaList(queryParam);
        if (!ssmResult.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
        for (int i = 0; i < resultList.size(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList.get(i);
            cMsg.bizAreaOrgDescTxt_D.no(i).setValue(resultMap.get("BIZ_AREA_ORG_DESC_TXT"));
            cMsg.bizAreaOrgCd_C.no(i).setValue(resultMap.get("BIZ_AREA_ORG_CD"));
        }

        S21SsmEZDResult ssmResult2 = NMAL2620Query.getInstance().getSelectModeList(queryParam);
        if (!ssmResult2.isCodeNormal()) {
            return;
        }

        List<Map<String, String>> resultList2 = (List<Map<String, String>>) ssmResult2.getResultObject();
        for (int i = 0; i < resultList2.size(); i++) {
            Map<String, String> resultMap = (Map<String, String>) resultList2.get(i);
            cMsg.trtyUpdModeTpCd_C.no(i).setValue(resultMap.get("TRTY_UPD_MODE_TP_CD"));
            cMsg.trtyUpdModeTpDescTxt_D.no(i).setValue(resultMap.get("TRTY_UPD_MODE_TP_DESC_TXT"));
        }

    }

    /**
     * CheckEffectiveDate
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @param moveEffThruDt String
     * @return boolean
     */
    public static boolean checkEffectiveDate(NMAL2620CMsg cMsg, int no, String moveEffThruDt) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkEffectiveDate(queryParam);
        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {

                String fromDt = resultMap.get("EFF_FROM_DT");
                String toDt = resultMap.get("EFF_THRU_DT");

                if (Integer.parseInt(fromDt) > Integer.parseInt(cMsg.xxFromDt.getValue())) {
                    cMsg.setMessageInfo(NMAM8448E);
                    return false;
                } else if (ZYPCommonFunc.hasValue(toDt)) {
                    if (Integer.parseInt(toDt) < Integer.parseInt(moveEffThruDt)) {
                        cMsg.setMessageInfo(NMAM8448E);
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    return true;
                }
            }
            cMsg.setMessageInfo(NMAM8448E);
            return false;
        } else {
            cMsg.setMessageInfo(NMAM8448E);
            return false;
        }
    }

    /**
     * checkExistMoveToRsrcTrtyReln
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @param moveEffToDate String
     * @return boolean
     */
    public static boolean checkExistMoveToRsrcTrtyReln(NMAL2620CMsg cMsg, int no, String moveEffToDate) {
    // 2016/05/13 CSA-QC#8255 Add Start
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        queryParam.put("gnrnTp_current", GNRN_TP.CURRENT);
        queryParam.put("gnrnTp_future", GNRN_TP.FUTURE);
        queryParam.put("maxDt", "99991231");
        queryParam.put("psnNum", cMsg.psnNum_D.getValue());

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkMoveToResrcTrtyRelnExist(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> relnTermMap : resultList) {

                if (cMsg.xxFromDt.getValue().compareTo(relnTermMap.get("EFF_FROM_DT")) >= 0 
                    && cMsg.xxFromDt.getValue().compareTo(relnTermMap.get("EFF_THRU_DT")) <= 0) {
                    cMsg.A.no(no).xxChkBox_A.setErrorInfo(1, NMAM8511E);
                    cMsg.setMessageInfo(NMAM8511E);
                    return false;
                }
                if (moveEffToDate.compareTo(relnTermMap.get("EFF_FROM_DT")) >= 0
                        && moveEffToDate.compareTo(relnTermMap.get("EFF_THRU_DT")) <= 0) {
                    cMsg.A.no(no).xxChkBox_A.setErrorInfo(1, NMAM8511E);
                    cMsg.setMessageInfo(NMAM8511E);
                    return false;
                }
                if (cMsg.A.no(no).effFromDt_A.getValue().compareTo(relnTermMap.get("EFF_FROM_DT")) <= 0
                        &&  moveEffToDate.compareTo(relnTermMap.get("EFF_THRU_DT")) >= 0) {
                    cMsg.A.no(no).xxChkBox_A.setErrorInfo(1, NMAM8511E);
                    cMsg.setMessageInfo(NMAM8511E);
                    return false;
                }
            }
        }

        return true;
        // 2016/05/13 CSA-QC#8255 Add End
    }

    /**
     * CheckBusinessArea
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean checkBusinessArea(NMAL2620CMsg cMsg, int no) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkBusinessArea(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {

                String bizAreaTxt = resultMap.get("BIZ_AREA_ORG_DESC_TXT");
                if (!bizAreaTxt.equals(cMsg.A.no(no).bizAreaOrgDescTxt_A.getValue())) {
                    cMsg.setMessageInfo(NMAM8397E, new String[] {"selected resource name's"});
                    return false;
                } else {
                    return true;
                }
            }
            cMsg.setMessageInfo(NMAM8397E, new String[] {"selected resource name's"});
            return false;
        } else {
            cMsg.setMessageInfo(NMAM8397E, new String[] {"selected resource name's"});
            return false;
        }
    }

    /**
     * checkTargetTerritories
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean checkTargetTerritories(NMAL2620CMsg cMsg, int no) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("slsDate", ZYPDateUtil.getSalesDate());
        queryParam.put("hitDate", "99991231");
        queryParam.put("bizAreaOrgCd", cMsg.A.no(no).bizAreaOrgDescTxt_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkTargetTerritories(queryParam);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NMAM0179E, new String[] {"End Territory Date", "inactive Territory"});
            return false;
        } else {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {

                String effFromDt = resultMap.get("EFF_FROM_DT");
                if (Integer.parseInt(effFromDt) > Integer.parseInt(cMsg.endDt.getValue())) { // 2016/04/26 CSA-QC#7594 Mod
                    cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Territory Effective Date From"});
                    return false;
                }
            }
            return true;
        }
    }

    /**
     * checkActiveChildTerritory
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean checkActiveChildTerritory(NMAL2620CMsg cMsg, int no, List<Integer> checkList) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        queryParam.put("maxEffThruDt", "99991231");

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkActiveChildOrganization(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (Map<String, String> resultMap : resultList) {
                // if child expires in same request, continue
                for (int i : checkList) {
                    if (cMsg.A.no(i).orgCd_A.getValue().equals((String)resultMap.get("ORG_CD"))) {
                        continue;
                    }
                }

                if (cMsg.endDt.getValue().compareTo((String)resultMap.get("EFF_THRU_DT")) < 0) {
                    cMsg.A.no(no).xxChkBox_A.setErrorInfo(1, NMAM8361E);
                    cMsg.setMessageInfo(ZZM9037E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * checkActiveChildTerritory
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean checkActiveParentTerritory(NMAL2620CMsg cMsg, int no, List<Integer> checkList) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("flgOnY", ZYPConstant.FLG_ON_Y);
        queryParam.put("maxEffThruDt", "99991231");

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().checkParentRelation(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();

            for (Map<String, String> resultMap : resultList) {
                // if child expires in same request, continue
                for (int i : checkList) {
                    if (cMsg.A.no(i).orgCd_A.getValue().equals((String)resultMap.get("ORG_CD"))) {
                        continue;
                    }
                }

                if (cMsg.endDt.getValue().compareTo((String)resultMap.get("EFF_THRU_DT")) > 0) {
                    cMsg.A.no(no).xxChkBox_A.setErrorInfo(1, NMAM8637E);
                    cMsg.setMessageInfo(ZZM9037E);
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * getEffFrDt
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean getEffFrDt(NMAL2620CMsg cMsg, int no) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("slsDate", ZYPDateUtil.getSalesDate());
        queryParam.put("hitDate", "99991231");
        queryParam.put("bizAreaOrgCd", cMsg.A.no(no).bizAreaOrgDescTxt_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        List<String> gnrnTpCd = new ArrayList<String>();
        gnrnTpCd.add("2");
        gnrnTpCd.add("3");
        queryParam.put("gnrnTpCd", gnrnTpCd);
        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().getEffFrDtRsrcRltn(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {
                String effFromDt = resultMap.get("EFF_FROM_DT");

                if (Integer.parseInt(effFromDt) > Integer.parseInt(cMsg.endDt.getValue())) { // 2016/04/26 CSA-QC#7594 Mod
                    cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Territory Effective Date From"});
                    return false;
                }
                // 2016/07/21 CSA-QC#11908 Add Start
                String effThruDt = resultMap.get("EFF_THRU_DT");
                if (ZYPCommonFunc.hasValue(effThruDt) && ZYPCommonFunc.hasValue(cMsg.endDt)) {
                    if (ZYPDateUtil.compare(effThruDt, cMsg.endDt.getValue()) < 0) {
                        cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Territory Effective Date From" });
                        return false;
                    }
                }
                // 2016/07/21 CSA-QC#11908 Add End
            }

        // 2016/04/26 CSA-QC#7594 Delete Start
        // } else {
        //     cMsg.setMessageInfo("NMAM8450E", new String[] {"End Terriories Date ", "Before Territory Effective Date From"});
        //     return false;
        // 2016/04/26 CSA-QC#7594 Delete End
        }

        S21SsmEZDResult ssmResult2 = NMAL2620Query.getInstance().getEffFrDtTrrtryRl(queryParam);
        if (ssmResult2.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {
                String effFromDt = resultMap.get("EFF_FROM_DT");
                if (Integer.parseInt(effFromDt) > Integer.parseInt(cMsg.endDt.getValue())) { // 2016/04/26 CSA-QC#7594 Mod
                    cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Territory Effective Date From"});
                    return false;
                }
            }
        // 2016/04/26 CSA-QC#7594 Delete Start
        // } else {
        //    cMsg.setMessageInfo("NMAM8450E", new String[] {"End Terriories Date ", "Before Territory Effective Date From"});
        //    return false;
        // 2016/04/26 CSA-QC#7594 Delete End
        }

        return true;
    }

    /**
     * getRelnDt
     * @param cMsg NMAL2620CMsg
     * @param no int
     * @return boolean
     */
    public static boolean getRelnDt(NMAL2620CMsg cMsg, int no) {

        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        queryParam.put("orgCd", cMsg.A.no(no).orgCd_A.getValue());
        queryParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        List<String> gnrnTpCd = new ArrayList<String>();
        gnrnTpCd.add(GNRN_TP.CURRENT);
        gnrnTpCd.add(GNRN_TP.FUTURE);
        queryParam.put("gnrnTpCd", gnrnTpCd);
        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().getRelnDt(queryParam);

        if (ssmResult.isCodeNormal()) {
            List<Map<String, String>> resultList = (List<Map<String, String>>) ssmResult.getResultObject();
            for (Map<String, String> resultMap : resultList) {
                String effFromDt = resultMap.get("EFF_FROM_DT");
                if (Integer.parseInt(effFromDt) > Integer.parseInt(cMsg.endDt.getValue())) { // 2016/04/26 CSA-QC#7594 Mod
                    cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Relation Effective Date From"});
                    return false;
                }
            }
        } else {
            cMsg.setMessageInfo(NMAM8450E, new String[] {"End Terriories Date ", "Before Relation Effective Date From"});
            return false;
        }

        return true;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL0780CMsg
     * @param sMsg NSAL0780SMsg
     */
    public static void getSearchData(NMAL2620CMsg cMsg, NMAL2620SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL2620Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0007E);
                cMsg.setCommitSMsg(true);
            }
        } else {
            // No result
            cMsg.setMessageInfo(NMAM0007I);
        }
    }

    /**
     * Clear Detail Control
     * @param cMsg NSAL0780CMsg
     */
    public static void clearDetailControl(NMAL2620CMsg cMsg) {
        // Select Mode
        cMsg.trtyUpdModeTpCd_V.clear();

        // Move Resource mode
        cMsg.xxPsnNm_D.clear();
        cMsg.psnNum_D.clear();
        cMsg.xxFromDt.clear();
        cMsg.xxThruDt.clear();

        // End Territories mode
        cMsg.endDt.clear();
        cMsg.rqstRsltCmntTxt.clear();
    }

    /**
     * 
     * @param sMsg NMAL2620SMsg
     * @return String
     */
    public static final NMAL2620SMsg formatPsnName(NMAL2620SMsg sMsg) {

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            StringBuilder name = new StringBuilder();
            if (ZYPCommonFunc.hasValue(sMsg.A.no(i).xxPsnNm_A)) {
                name.append(sMsg.A.no(i).xxPsnNm_A.getValue());
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).psnLastNm_A)) {
                    name.append(' ');
                    name.append(sMsg.A.no(i).psnLastNm_A.getValue());
                }
            } else {
                if (ZYPCommonFunc.hasValue(sMsg.A.no(i).psnLastNm_A)) {
                    name.append(sMsg.A.no(i).psnLastNm_A.getValue());
                } else {
                    continue;
                }
            }
            ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxPsnNm_A, name.toString());
        }
        return sMsg;
    }

    /**
     * hasValueItems
     * @param items EZDCItem
     * @return boolean
     */
    public static boolean hasValueItems(EZDCItem... items) {

        for (EZDCItem item : items) {
            if (ZYPCommonFunc.hasValue(item)) {
                return true;
            }
        }
        return false;
    }
}

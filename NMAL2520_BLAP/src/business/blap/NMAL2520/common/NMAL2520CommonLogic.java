/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2520.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSDateItem;
import business.blap.NMAL2520.NMAL2520CMsg;
import business.blap.NMAL2520.NMAL2520Query;
import business.blap.NMAL2520.NMAL2520SMsg;
import business.blap.NMAL2520.NMAL2520_ACMsg;
import business.blap.NMAL2520.NMAL2520_ASMsg;
import business.blap.NMAL2520.NMAL2520_BCMsg;
import business.blap.NMAL2520.NMAL2520_CSMsg;
import business.blap.NMAL2520.constant.NMAL2520Constant;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.S21_PSNTMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_TIER;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;

/**
 * <pre>
 * Business ID : NMAL2520 Org Structure Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/26   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/02   Fujitsu         C.Yokoi         Update          CSA_QC#2867
 * 2016/02/03   Fujitsu         C.Yokoi         Update          CSA_QC#1930
 * 2016/02/05   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/22   Fujitsu         C.Yokoi         Update          CSA-QC#3280
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/02/29   Fujitsu         C.Yokoi         Update          CSA-QC#3859
 * 2016/03/01   Fujitsu         C.Yokoi         Update          CSA-QC#2766
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#4654
 * 2016/03/17   Fujitsu         C.Yokoi         Update          CSA-QC#5654
 * 2016/03/18   Fujitsu         C.Yokoi         Update          CSA-QC#5718
 * 2016/03/22   Fujitsu         C.Yokoi         Update          CSA-QC#3280
 * 2016/03/28   SRAA            Y.Chen          Update          CSA-QC#3509
 * 2016/04/04   Fujitsu         C.Yokoi         Update          CSA-QC#5187
 * 2016/04/13   Fujitsu         C.Yokoi         Update          CSA-QC#6960
 * 2016/05/02   SRAA            Y.Chen          Update          CSA-QC#5672
 * 2016/07/06   Fujitsu         C.Tanaka        Update          CSA-QC#11454
 * 2016/07/08   Fujitsu         C.Tanaka        Update          CSA-QC#11624
 * 2016/07/09   Fujitsu         C.Yokoi         Update          CSA-QC#11480
 * 2016/08/17   SRAA            Y.Chen          Update          CSA-QC#3859
 * 2016/08/31   SRAA            Y.Chen          Update          QC#7966
 * 2016/09/30   Hitachi         Y.Takeno        Update          CSA-QC#14809
 * 2016/10/17   Fujitsu         N.Sugiura       Update          CSA-QC#15140
 * 2016/11/30   Fujitsu         M.Ohno          Update          CSA-QC#16236
 * 2017/06/14   Hitachi         J.Kim           Update          QC#18924
 * 2017/09/27   Fujitsu         T.Ogura         Update          CSA-QC#21350
 * 2017/10/04   Fujitsu         H.Ikeda         Update          QC#21344,QC#21359
 * 2017/10/13   Fujitsu         H.Sugawara      Update          QC#21753
 * 2017/11/21   Fujitsu         M.Ohno          Update          QC#21350
 * 2017/12/05   Fujitsu         N.Sugiura       Update          QC#21270
 * 2018/01/10   Fujitsu         M.Ohno          Update          QC#20233
 * 2018/01/31   Fujitsu         Mz.Takahashi    Update          QC#22889 
 * 2018/02/14   Fujitsu         Hd.Sugawara     Update          QC#23905
 * 2018/03/07   Fujitsu         M.Ohno          Update          QC#20233-1
 * 2018/04/13   Fujitsu         Hd.Sugawara     Update          QC#23867
 * 2018/06/12   Fujitsu         T.Noguchi       Update          QC#20220
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 *</pre>
 */
public class NMAL2520CommonLogic {

    /**
     * clear All Details
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    public static void clearAllDetails(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.T.clear();
        cMsg.T.setValidCount(0);
    }

    /**
     * @param cd EZDCStringItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCStringItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (String) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * @param cd EZDBBigDecimalItemArray
     * @param value EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    public static void createPulldownList(EZDCBigDecimalItemArray cd, EZDCStringItemArray value, List<Map> pullDownList, String[] dbColumn) {

        for (int i = 0; i < pullDownList.size(); i++) {
            Map pullDownData = pullDownList.get(i);

            ZYPEZDItemValueSetter.setValue(cd.no(i), (BigDecimal) pullDownData.get(dbColumn[0]));
            ZYPEZDItemValueSetter.setValue(value.no(i), (String) pullDownData.get(dbColumn[1]));

            if (i >= cd.length()) {
                break;
            }
        }
    }

    /**
     * @param cMsg NMAL2520CMsg
     * @param glblCmpyCd String
     * @param bizAreaOrgCd String
     */
    public static void createOrgFuncRoleTpPullDown(NMAL2520CMsg cMsg, String glblCmpyCd, String bizAreaOrgCd) {
        // 2016/03/17 CSA-QC#5654 Add Start
        // Add Start 2016/11/30 M.Ohno S21_NA#16236
        cMsg.orgFuncRoleTpCd_C1.clear();
        cMsg.orgFuncRoleTpNm_C1.clear();
        // Add End   2016/11/30 M.Ohno S21_NA#16236

        // Organization Function Role Type
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("globalCmpyCd", glblCmpyCd);
        ssmParam.put("defaultBusinessArea", bizAreaOrgCd);
        S21SsmEZDResult orgFuncRoleTpPulldownList = NMAL2520Query.getInstance().getOrgFuncRoleTpPulldownList(ssmParam);

        if (orgFuncRoleTpPulldownList.isCodeNormal()) {
            List<Map> orgFuncRoleTpList = (List<Map>) orgFuncRoleTpPulldownList.getResultObject();
            NMAL2520CommonLogic.createPulldownList(cMsg.orgFuncRoleTpCd_C1, cMsg.orgFuncRoleTpNm_C1, orgFuncRoleTpList, new String[] {NMAL2520Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN, NMAL2520Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
        }
        // 2016/03/17 CSA-QC#5654 Add End
    }

    /**
     * checkInputHeaderForSubmit
     * @param cMsg Business Msg
     * @return boolean
     */
    public static boolean checkInputHeaderForSubmit(NMAL2520CMsg cMsg) {

        if (!ZYPCommonFunc.hasValue(cMsg.bizAreaOrgCd_P1)) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.bizAreaOrgCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8267E);
            // 2016/03/03 CSA-QC#4545 Add End
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8267E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.orgNm_H1)) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.orgNm_H1.setErrorInfo(1, NMAL2520Constant.NMAM8267E);
            // 2016/03/03 CSA-QC#4545 Add End
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8267E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.effFromDt_H1)) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.effFromDt_H1.setErrorInfo(1, NMAL2520Constant.NMAM8267E);
            // 2016/03/03 CSA-QC#4545 Add End
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8267E);
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
            // 2016/03/03 CSA-QC#4545 Add Start
            cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM0014E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORG_TIER_CD });
            // 2016/03/03 CSA-QC#4545 Add End
            cMsg.setMessageInfo(NMAL2520Constant.NMAM0014E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORG_TIER_CD });
            return false;
        }

        if (BIZ_AREA_ORG.CONTRACT.equals(cMsg.bizAreaOrgCd_P1.getValue())) {
            if (!ZYPCommonFunc.hasValue(cMsg.lineBizTpCd_P1)) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.lineBizTpCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8268E);
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8268E);
                return false;
            }
        }

        if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
            if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.effFromDt_H1.getValue()) < 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.effFromDt_H1.setErrorInfo(1, NMAL2520Constant.NMAM8269E);
                cMsg.effThruDt_H1.setErrorInfo(1, NMAL2520Constant.NMAM8269E);
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8269E);
                return false;
            }

            // S21_NA#1930 Delete Start
            // if
            // (cMsg.effThruDt_H1.getValue().compareTo(ZYPDateUtil.getSalesDate())
            // < 0) {
            // cMsg.setMessageInfo(NMAL2520Constant.NMAM8274E);
            // return false;
            // }
            // S21_NA#1930 Delete End

            // 2016/07/06 CSA-QC#11454 Add Start
            if (!cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue()) && !checkActiveChildList(cMsg, 0, ZYPConstant.FLG_ON_Y)) {
                cMsg.effThruDt_H1.setErrorInfo(1, NMAL2520Constant.NMAM8361E);
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8361E);
                return false;
            }
            // 2016/07/06 CSA-QC#11454 Add End
        }

        if (!checkDuplicateOrganizationName(cMsg)) {
            // 2017/12/05 QC#21270 Mod Start
            // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.orgNm_H1.setErrorInfo(1, NMAL2520Constant.NMAM0834E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORG_NM, NMAL2520Constant.NAME_FOR_MESSAGE_ORG_MST });
            // 2016/03/03 CSA-QC#4545 Add End
            // cMsg.setMessageInfo(NMAL2520Constant.NMAM0834E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORG_NM, NMAL2520Constant.NAME_FOR_MESSAGE_ORG_MST });
            cMsg.orgNm_H1.setErrorInfo(1, NMAL2520Constant.NMAM8635E,
                    new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION, NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION, NMAL2520Constant.NAME_FOR_MESSAGE_ORGANIZATION});
            cMsg.setMessageInfo(NMAL2520Constant.ZZM9037E);
            // 2017/12/05 QC#21270 Mod End
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1)) {
            cMsg.xxChkBox_H1.setValue(ZYPConstant.FLG_OFF_N);
        }

        if (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_HB)) {
            cMsg.xxChkBox_HB.setValue(ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    /**
     * checkInputHeaderForSubmit
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return boolean
     */
    public static boolean checkInputMandantoryItem(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        boolean successFlg = true;

        // ### Build Hierarchy ###
        // Parent Organization / Team
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A1)) {
                // ZZM9000E=0,[@] field is mandatory.
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_BUILD_HIERARCHY);
                successFlg = false;
            }
        }

        // ### Research Assign ###
        // Resource Assignment(s)
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effFromDt_C1)) {
                // ZZM9000E=0,[@] field is mandatory.
                sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                cMsg.xxDplyTab.setValue(NMAL2520Constant.TAB_RESRC_ASIGN);
                successFlg = false;
            }
        }

        return successFlg;
    }

    /**
     * checkDuplicateOrganizationName
     * @param cMsg Business Msg
     * @return boolean
     */
    public static boolean checkDuplicateOrganizationName(NMAL2520CMsg cMsg) {
        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().checkDuplicateOrganizationName(cMsg);
        if (ssmResult.isCodeNormal()) {
            List<Map> resultList = (List<Map>) ssmResult.getResultObject();
            for (int j = 0; j < resultList.size(); j++) {
                Map map = (Map) resultList.get(j);
                String orgCd = (String) map.get("ORG_CD");

                if (!orgCd.equals(cMsg.orgCd_H1.getValue())) {
                    return false;
                }
            }
        }
        return true;
        // 2016/04/13 CSA-QC#6402 Del Start
        // if (resCnt.compareTo(0) > 0) {
        // if (!ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
        // return true;
        // }
        // }
        //
        // return false;
        // 2016/04/13 CSA-QC#6402 Del End
    }

    /**
     * checkActiveChildOrganization
     * @param cMsg Business Msg
     * @param suffix String
     * @param i int
     * @return boolean
     */
    public static boolean checkActiveChildOrganization(NMAL2520CMsg cMsg, String suffix, int i) {
        // 2016/02/19 CSA-QC#3280 Add Start
        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().checkActiveChildOrganization(cMsg, suffix, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();

        if (resCnt > 0) {
            return false;
        }
        return true;
        // 2016/02/19 CSA-QC#3280 Add Start
    }

    /**
     * checkInputBuildForSubmit
     * @param cMsg Business Msg
     * @param sMsg NMAL2520SMsg
     * @return boolean
     */
    public static boolean checkInputBuildForSubmit(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        int validParent = 0;
        boolean isSuccess = true;
        // 2016/07/06 CSA-QC#11454 Del Start
        // boolean isAllOrgRelnExpired = true;
        // 2016/07/06 CSA-QC#11454 Del End
        List<Integer> orgRelnExpiredIndex = new ArrayList<Integer>();

        // QC#5672
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().getOrganizationByName(cMsg, i);
            if (!ssmResult.isCodeNormal()) {
                cMsg.A.no(i).orgNm_A1.setErrorInfo(1, NMAL2520Constant.NMAM8186E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_ORG_NM });
                isSuccess = false;
            } else {
                List resultList = (List) ssmResult.getResultObject();
                String orgCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.ORG_CD_DBCOLUMN);
                String orgTierCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.ORG_TIER_CD_DBCOLUMN);
                String firstOrgCd = (String) ((Map) resultList.get(0)).get(NMAL2520Constant.FIRST_ORG_CD_DBCOLUMN);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgCd_A1, orgCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgTierCd_A1, orgTierCd);
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).firstOrgCd_A1, firstOrgCd);
            }
        }
        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2520Constant.ZZM9037E);
            return false;
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A1)) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM0014E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).orgTierCd_A1.getValue())) {
                    if (new Integer(cMsg.orgTierCd_P1.getValue()).compareTo(new Integer(cMsg.A.no(i).orgTierCd_A1.getValue())) <= 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8273E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        isSuccess = false;
                    }
                }
            }

            if (!cMsg.bizAreaOrgCd_P1.getValue().equals(cMsg.A.no(i).firstOrgCd_A1.getValue())) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.bizAreaOrgCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8272E);
                // 2016/03/03 CSA-QC#4545 Add Start
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(cMsg.effFromDt_H1)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A1)) {
                    if (cMsg.effFromDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A1.getValue()) > 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8271E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        isSuccess = false;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1)) {
                    if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effThruDt_A1.getValue()) < 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8271E);
                        // 2016/03/03 CSA-QC#4545 Add Start
                        isSuccess = false;
                    }
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1)) {
                if (cMsg.A.no(i).effThruDt_A1.getValue().compareTo(cMsg.A.no(i).effFromDt_A1.getValue()) < 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8269E);
                    cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8269E);
                    // 2016/03/03 CSA-QC#4545 Add Start
                    isSuccess = false;
                }
            }

            // Mod Start 2017/10/13 QC#21753
            //if (checkActiveDate(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), ZYPDateUtil.getSalesDate())) {
            //    validParent++;
            //    if (validParent > 1) {
            //        // 2016/03/03 CSA-QC#4545 Add Start
            //        cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
            //        cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
            //        // 2016/03/03 CSA-QC#4545 Add End
            //        isSuccess = false;
            //    }
            //}
            if (isDuplicateParentTerm(cMsg, i)) {
                isSuccess = false;
            }
            // Mod End 2017/10/13 QC#21753

            // 2016/02/19 CSA-QC#3280 Add Start
            // 2016/07/06 CSA-QC#11454 Del Start
            // if (isAllOrgRelnExpired) {
            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1) && !cMsg.A.no(i).effThruDt_A1.getValue().equals(cMsg.A.no(i).effThruDt_AB.getValue())) {
                orgRelnExpiredIndex.add(i);
                // } else {
                // isAllOrgRelnExpired = false;
            }
            // }
            // 2016/07/06 CSA-QC#11454 Del End
            // 2016/02/19 CSA-QC#3280 Add End

            // 2016/02/19 CSA-QC#5718 Add Start
            if (cMsg.orgCd_H1.getValue().equals(cMsg.A.no(i).orgCd_A1.getValue())) {
                cMsg.A.no(i).xxChkBox_A1.setErrorInfo(1, NMAL2520Constant.NMAM8400E);
                isSuccess = false;
            }

            // 2018/01/10 QC#20233 add start
            if (!checkActiveChildList(cMsg, i, ZYPConstant.FLG_OFF_N)) {
                if (!checkNextStartParentOrg(cMsg, i)) {
                    cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8361E);
                    isSuccess = false;
                }
            }
            // 2018/01/10 QC#20233 add end
            // 2016/02/19 CSA-QC#5718 Add End
        }

        // 2016/03/22 CSA-QC#3280 Mod Start
        // 2016/07/06 CSA-QC#11454 Mod Start
        // if (isAllOrgRelnExpired) {
        // 2018/01/10 QC#20233 del start
//        for (int i = 0; i < orgRelnExpiredIndex.size(); i++) {
//            //if (!checkActiveChildOrganization(cMsg, NMAL2520Constant.SUFFIX_A, orgRelnExpiredIndex.get(i))) {
//            if (!checkActiveChildList(cMsg, orgRelnExpiredIndex.get(i), ZYPConstant.FLG_OFF_N)) {
//                cMsg.A.no(orgRelnExpiredIndex.get(i)).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8361E);
//                isSuccess = false;
//            }
//        }
        // 2018/01/10 QC#20233 del end
        // }
        // 2016/07/06 CSA-QC#11454 Mod End
        // 2016/03/22 CSA-QC#3280 Mod End

        // 2016/07/06 CSA-QC#11454 Del Start
        // isAllOrgRelnExpired = true;
        // 2016/07/06 CSA-QC#11454 Del End
        orgRelnExpiredIndex.clear();

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            if (checkActiveDate(cMsg.B.no(i).effFromDt_B1.getValue(), cMsg.B.no(i).effThruDt_B1.getValue(), ZYPDateUtil.getSalesDate())) {
                if (cMsg.effFromDt_H1.getValue().compareTo(cMsg.B.no(i).effFromDt_B1.getValue()) > 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.B.no(i).effFromDt_B1.setErrorInfo(1, NMAL2520Constant.NMAM8275E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }

            if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)) {
                if (compareDate(cMsg.B.no(i).effThruDt_B1.getValue(), cMsg.effThruDt_H1.getValue())) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.B.no(i).effThruDt_B1.setErrorInfo(1, NMAL2520Constant.NMAM8275E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }

            if (!cMsg.bizAreaOrgCd_P1.getValue().equals(cMsg.B.no(i).firstOrgCd_B1.getValue())) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.B.no(i).firstOrgCd_B1.setErrorInfo(1, NMAL2520Constant.NMAM8277E);
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(cMsg.orgTierCd_P1)) {
                if (ZYPCommonFunc.hasValue(cMsg.B.no(i).orgTierCd_B1.getValue())) {
                    if (new Integer(cMsg.orgTierCd_P1.getValue()).compareTo(new Integer(cMsg.B.no(i).orgTierCd_B1.getValue())) >= 0) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.orgTierCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8278E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        isSuccess = false;
                    }
                }
            }

            // 2016/02/19 CSA-QC#3280 Add Start
            // 2016/07/06 CSA-QC#11454 Del Start
            // if (isAllOrgRelnExpired) {
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B1) && !cMsg.B.no(i).effThruDt_B1.getValue().equals(cMsg.B.no(i).effThruDt_BB.getValue())) {
                orgRelnExpiredIndex.add(i);
                // } else {
                // isAllOrgRelnExpired = false;
            }
            // }
            // 2016/07/06 CSA-QC#11454 Del End
            // 2016/02/19 CSA-QC#3280 Add End
        }

        // 2016/03/22 CSA-QC#3280 Add Start
        // 2016/07/06 CSA-QC#11454 Mod Start
        // if (isAllOrgRelnExpired) {
        for (int i = 0; i < orgRelnExpiredIndex.size(); i++) {
            if (!checkActiveChildOrganization(cMsg, NMAL2520Constant.SUFFIX_B, orgRelnExpiredIndex.get(i))) {
                cMsg.B.no(orgRelnExpiredIndex.get(i)).effThruDt_B1.setErrorInfo(1, NMAL2520Constant.NMAM8361E);
                isSuccess = false;
            }
        }
        // }
        // 2016/07/06 CSA-QC#11454 Mod End
        // 2016/03/22 CSA-QC#3280 Add End

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2520Constant.ZZM9037E);
            return false;
        }
        return true;
    }

    /**
     * checkInputAsignForSubmit
     * @param cMsg Business Msg
     * @param glblCmpyCd glblCmpyCd
     * @return boolean
     */
    public static boolean checkInputAsignForSubmit(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult;
        int activeSalesRollCnt;
        int activeSalesRepRollCnt;
        // int countManagerRole = 0;
        // boolean isChangeToManagerRole = false;
        boolean isSuccess = true;
        boolean hasActiveResource = false;

        // START 2017/06/14 J.Kim [QC#18924,MOD]
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {

            activeSalesRollCnt = 0;
            activeSalesRepRollCnt = 0;

            // 2015/04/13 CSA-QC#6960 Add Start
            if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
                hasActiveResource = true;
            }
            // 2015/04/13 CSA-QC#6960 Add End

            if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effFromDt_C1)) {
                // 2016/03/03 CSA-QC#4545 Add Start
                sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM0014E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }

            if (ZYPCommonFunc.hasValue(sMsg.C.no(i).orgFuncRoleTpCd_P1)) {
                // Mod Start 2018/02/14 QC#23905
                //if (!checkBusinessAreaRoll(sMsg.bizAreaOrgCd_P1.getValue(), sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue())) {
                if (!checkBusinessAreaRoll(cMsg.bizAreaOrgCd_P1.getValue(), sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue())) {
                    // Mod End 2018/02/14 QC#23905
                    // 2016/03/03 CSA-QC#4545 Add Start
                    sMsg.C.no(i).orgFuncRoleTpCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8285E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }

            // 2017/09/27 QC#21350 Mod Start
//            if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(sMsg.effFromDt_H1.getValue()) < 0) {
            if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(cMsg.effFromDt_H1.getValue()) < 0) {
            // 2017/09/27 QC#21350 Mod End
                // 2016/03/03 CSA-QC#4545 Add Start
                sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8280E);
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8280E);
                return false;
            }

            if (ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1)) {
                if (sMsg.C.no(i).effThruDt_C1.getValue().compareTo(sMsg.C.no(i).effFromDt_C1.getValue()) < 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8279E);
                    sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8279E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    isSuccess = false;
                }
            }

            // 2017/09/27 QC#21350 Mod Start
//            if (compareDate(sMsg.C.no(i).effThruDt_C1.getValue(), sMsg.effThruDt_H1.getValue())) {
            if (compareDate(sMsg.C.no(i).effThruDt_C1.getValue(), cMsg.effThruDt_H1.getValue())) {
            // 2017/09/27 QC#21350 Mod End
                // 2016/03/03 CSA-QC#4545 Add Start
                sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8280E);
                // 2016/03/03 CSA-QC#4545 Add End
                isSuccess = false;
            }

            // 2016/09/30 CSA-QC#14809 Add Start
            // Assign start date < Employment Date From
            if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(sMsg.C.no(i).effFromDt_U4.getValue()) < 0) {
                sMsg.C.no(i).effFromDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8241E);
                isSuccess = false;
            }

            // Assign end date > Employment Date To
            if (compareDate(sMsg.C.no(i).effThruDt_C1.getValue(), sMsg.C.no(i).effThruDt_U4.getValue())) {
                sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM8242E);
                isSuccess = false;
            }
            // 2016/09/30 CSA-QC#14809 Add End

            // QC#3509
            boolean isActiveSalesRepRoll = (Integer) NMAL2520Query.getInstance().checkActiveSalesRepRoll(sMsg, i).getResultObject() > 0;

            // Del Start 2019/02/27 QC#30564
//            for (int j = 0; j < sMsg.C.getValidCount(); j++) {
//                if (sMsg.C.no(i).psnNum_C1.getValue().equals(sMsg.C.no(j).psnNum_C1.getValue())) {
//
//                    // Duplicate Resource
//                    if (sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue().equals(sMsg.C.no(j).orgFuncRoleTpCd_P1.getValue())) {
//                        if (sMsg.C.no(i).effFromDt_C1.getValue().equals(sMsg.C.no(j).effFromDt_C1.getValue())) {
//                            if (i != j) {
//                                // 2016/03/03 CSA-QC#4545 Add Start
//                                sMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8281E);
//                                sMsg.C.no(j).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8281E);
//                                // 2016/03/03 CSA-QC#4545 Add End
//                                isSuccess = false;
//                            }
//                        } else if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
//                            if (checkActiveDate(sMsg.C.no(j).effFromDt_C1.getValue(), sMsg.C.no(j).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
//                                if (i != j) {
//                                    // 2016/03/03 CSA-QC#4545 Add
//                                    // Start
//                                    sMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM0834E, new String[] {NMAL2520Constant.ACTIVE_ASSIGN, NMAL2520Constant.SAME_RESOURCE });
//                                    sMsg.C.no(j).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM0834E, new String[] {NMAL2520Constant.ACTIVE_ASSIGN, NMAL2520Constant.SAME_RESOURCE });
//                                    // 2016/03/03 CSA-QC#4545 Add End
//                                    isSuccess = false;
//                                }
//
//                            } else if (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1)) {
//                                // 2016/03/03 CSA-QC#4545 Add Start
//                                sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM0014E, new String[] {NMAL2520Constant.CURRENT + NMAL2520Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
//                                // 2016/03/03 CSA-QC#4545 Add Start
//                                isSuccess = false;
//
//                            } else if (sMsg.C.no(i).effThruDt_C1.getValue().compareTo(sMsg.C.no(j).effFromDt_C1.getValue()) > 0) {
//                                // 2016/03/03 CSA-QC#4545 Add Start
//                                sMsg.C.no(i).effThruDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM0044E, new String[] {NMAL2520Constant.NEW + NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
//                                        NMAL2520Constant.CURRENT + NMAL2520Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
//                                sMsg.C.no(j).effFromDt_C1.setErrorInfo(1, NMAL2520Constant.NMAM0044E, new String[] {NMAL2520Constant.NEW + NMAL2520Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
//                                        NMAL2520Constant.CURRENT + NMAL2520Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
//                                // 2016/03/03 CSA-QC#4545 Add End
//                                isSuccess = false;
//                            }
//                        }
//                    }
//                }
//            }
            // Del End 2019/02/27 QC#30564

            // Add Start 2019/02/27 QC#30564
            if (isCurrentOrFuturePeriod(sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
                // Duplicate resource check
                for (int j = 0; j < sMsg.C.getValidCount(); j++) {
                    if (i == j) {
                        continue;
                    }

                    if (sMsg.C.no(i).psnNum_C1.getValue().equals(sMsg.C.no(j).psnNum_C1.getValue()) && //
                            sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue().equals(sMsg.C.no(j).orgFuncRoleTpCd_P1.getValue())) {
                        if (isPeriodOverlap(sMsg.C.no(i).effFromDt_C1, sMsg.C.no(i).effThruDt_C1, //
                                sMsg.C.no(j).effFromDt_C1, sMsg.C.no(j).effThruDt_C1)) {
                            sMsg.C.no(i).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8281E);
                            sMsg.C.no(j).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8281E);
                            isSuccess = false;
                        }
                    }
                }
            }
            // Add End 2019/02/27 QC#30564

            // QC#3509
            if (BIZ_AREA_ORG.SALES.equals(cMsg.bizAreaOrgCd_P1.getValue()) && isActiveSalesRepRoll) {
                S21SsmEZDResult reslut = NMAL2520Query.getInstance().getActiveSalesRepRollByPsnCd(cMsg, sMsg.C.no(i).psnCd_C1.getValue(), cMsg.bizAreaOrgCd_P1.getValue());
                if (reslut.isCodeNormal()) {
                    List resultList = (List) reslut.getResultObject();
                    for (int j = 0; j < resultList.size(); j++) {
                        Map map = (Map) resultList.get(j);
                        String orgCd = (String) map.get("ORG_CD");
                        String effFromDt = (String) map.get("EFF_FROM_DT");
                        String effThruDt = (String) map.get("EFF_THRU_DT");
                        if (!cMsg.orgCd_H1.getValue().equals(orgCd)
                                && isPeriodOverlap(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), effFromDt, effThruDt)) {

                            sMsg.C.no(i).orgFuncRoleTpCd_P1.setErrorInfo(1, NMAL2520Constant.NMAM8237E);
                            isSuccess = false;
                        }
                        // 2016/10/17 S21_NA#15140 Mod End
                    }
                }
            }

            // S21_NA QC#2867 Add Start
            // Check Line of business for selected resource
            if (BIZ_AREA_ORG.SALES.equals(cMsg.bizAreaOrgCd_P1.getValue()) || BIZ_AREA_ORG.SERVICE.equals(cMsg.bizAreaOrgCd_P1.getValue())) {
                if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
                    if (!checkResourceSpecific(sMsg, i, glblCmpyCd)) {
                        isSuccess = false;
                    }
                }
            }
            // S21_NA QC#2867 Add End

            // Active Sales Rep Role must be assigned.
            if (BIZ_AREA_ORG.SALES.equals(cMsg.bizAreaOrgCd_P1.getValue())) {
                // 2017/10/04 QC#21344, 21359 Add Start
                if (isSuccess) {
                // 2017/10/04 QC#21344, 21359 Add End
                    ssmResult = NMAL2520Query.getInstance().checkSalesRoll(sMsg, i);
                    activeSalesRollCnt = (Integer) ssmResult.getResultObject();
                    if (activeSalesRollCnt == 0) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM8282E);
                        return false;
                    }
                // 2017/10/04 QC#21344, 21359 Add Start
                }
                // 2017/10/04 QC#21344, 21359 Add End

                // 2018/01/31 QC#22889 Mod Start
                // Mod Start 2018/02/14 QC#23905
                //if (checkActiveDate(cMsg.C.no(i).effFromDt_C1.getValue(), cMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())){
                if (checkActiveDate(sMsg.C.no(i).effFromDt_C1.getValue(), sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())){
                    // Mod End 2018/02/14 QC#23905
                    // Revenue account must be assigned
                    if (!checkActiveResourceRevenue(sMsg, i)) {
                        // 2017/10/04 QC#21344, 21359 Mod Start
                        //cMsg.setMessageInfo(NMAL2520Constant.NMAM8422E);
                        //return false;
                        isSuccess = false;
                        // 2017/10/04 QC#21344, 21359 Mod End
                    }
                }
                // 2018/01/31 QC#22889 Mod End
            }
        // END 2017/06/14 J.Kim [QC#18924,MOD]
// QC#3859
//            // 2016/02/29 CSA-QC#3859 Add Start
//            // Warning if More than 2 Managers were set to organization
//            if (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_H1.getValue())) {
//                if (checkActiveDate(cMsg.C.no(i).effFromDt_C1.getValue(), cMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
//                    if (NMAL2520Query.getInstance().isManagerRole(cMsg, i)) {
//                        countManagerRole++;
//                        if (!cMsg.C.no(i).orgFuncRoleTpCd_P1.getValue().equals(cMsg.C.no(i).orgFuncRoleTpCd_CB.getValue())) {
//                            isChangeToManagerRole = true;
//                        }
//                    }
//                }
//            }
        }

        if (!isSuccess) {
            cMsg.setMessageInfo(NMAL2520Constant.ZZM9037E);
            return false;
        }

        // 2015/04/13 CSA-QC#6960 Add Start
        // Error if Organization structure are not connected to Top
        // Tier(business area)
        if (hasActiveResource) {
            // 2015/07/08 CSA-QC#11624 Add Start
            if (!ORG_TIER._1.equals(cMsg.orgTierCd_P1.getValue()) && cMsg.A.getValidCount() == 0) {
                cMsg.setMessageInfo(NMAL2520Constant.NMAM8624E, new String[] {"Organization"});
                return false;
            }
            // 2015/07/08 CSA-QC#11624 Add End

            boolean checkedOrgStru = false;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (checkActiveDate(cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue(), ZYPDateUtil.getSalesDate())) {
                    ssmResult = NMAL2520Query.getInstance().getTopTierParentOrganization(cMsg, i, true);
                    if (ssmResult.isCodeNormal()) {
                        if (!cMsg.bizAreaOrgCd_P1.getValue().equals((String) ssmResult.getResultObject())) {
                            cMsg.setMessageInfo(NMAL2520Constant.NMAM0307E);
                            return false;
                        }
                    } else {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0307E);
                        return false;
                    }
                    checkedOrgStru = true;
                }
            }

            if (!checkedOrgStru && ZYPCommonFunc.hasValue(cMsg.orgCd_H1)) {
                ssmResult = NMAL2520Query.getInstance().getTopTierParentOrganization(cMsg, 0, false);
                if (ssmResult.isCodeNormal()) {
                    if (!cMsg.bizAreaOrgCd_P1.getValue().equals((String) ssmResult.getResultObject())) {
                        cMsg.setMessageInfo(NMAL2520Constant.NMAM0307E);
                        return false;
                    }
                } else {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM0307E);
                    return false;
                }
            }
        }
        // 2015/04/13 CSA-QC#6960 Add End
        
        // QC#3859
//        if (isChangeToManagerRole && countManagerRole > 1) {
//            cMsg.setMessageInfo(NMAL2520Constant.NMAM8382W);
//            cMsg.xxWrnSkipFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
//            return false;
//        }
//        // 2016/02/29 CSA-QC#3859 Add End
        String dupMgrChkReqTpCd = "";
        if (ZYPCommonFunc.hasValue(cMsg.bizAreaOrgCd_P1.getValue())) {
            BIZ_AREA_ORGTMsg tMsg = new BIZ_AREA_ORGTMsg();
            tMsg.glblCmpyCd.setValue(glblCmpyCd);
            tMsg.bizAreaOrgCd.setValue(cMsg.bizAreaOrgCd_P1.getValue());
            tMsg = (BIZ_AREA_ORGTMsg) S21CodeTableAccessor.findByKey(tMsg);
            if (tMsg != null) {
                dupMgrChkReqTpCd = tMsg.dupMgrChkReqTpCd.getValue();
            }
        }
        if (NMAL2520Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd) ||
                (NMAL2520Constant.DUP_MGR_CHK_WARN.equals(dupMgrChkReqTpCd) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_H1.getValue()))) {

            boolean isDupMgrErr = false;
            List<NMAL2520_CSMsg> listCurFutMrg = new ArrayList<NMAL2520_CSMsg>();
            for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                if (isCurrentOrFuturePeriod(sMsg.C.no(i).effThruDt_C1.getValue(), ZYPDateUtil.getSalesDate())) {
                    if (NMAL2520Query.getInstance().isManagerRole(sMsg, i)) {
                        listCurFutMrg.add(sMsg.C.no(i));
                    }
                }
            }
            for (int i = 0; i < listCurFutMrg.size(); i++) {
                for (int j = i + 1; j < listCurFutMrg.size(); j++) {
                    NMAL2520_CSMsg curRow = listCurFutMrg.get(i);
                    NMAL2520_CSMsg nextRow = listCurFutMrg.get(j);
                    if (!curRow.psnCd_C1.getValue().equals(nextRow.psnCd_C1.getValue())) { // 2016/12/05 CSA-QC#15140-3 Add
                        if (isPeriodOverlap(curRow.effFromDt_C1, curRow.effThruDt_C1, nextRow.effFromDt_C1, nextRow.effThruDt_C1)) {
                            if (isRoleChanged(curRow) || isRoleChanged(nextRow)){  
                                isDupMgrErr = true;
                                if(NMAL2520Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd)){
                                    curRow.xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8641E);
                                    nextRow.xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8641E);
                                } else {
                                    curRow.xxChkBox_C1.setErrorInfo(2, NMAL2520Constant.NMAM8382W);
                                    nextRow.xxChkBox_C1.setErrorInfo(2, NMAL2520Constant.NMAM8382W);
                                }
                            }
                        }
                    }
                }
            }
            if (isDupMgrErr) {
                if (NMAL2520Constant.DUP_MGR_CHK_WARN.equals(dupMgrChkReqTpCd)) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8382W);
                    cMsg.xxWrnSkipFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
                } else if (NMAL2520Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd)) {
                    cMsg.setMessageInfo(NMAL2520Constant.NMAM8641E);
                }
                return false;
            }
        }

        return true;
    }
    
    private static boolean isRoleChanged(NMAL2520_CSMsg row){
        if(!row.orgFuncRoleTpCd_P1.getValue().equals(row.orgFuncRoleTpCd_CB.getValue())
        || !row.effFromDt_C1.getValue().equals(row.effFromDt_CB.getValue())
        || !row.effThruDt_C1.getValue().equals(row.effThruDt_CB.getValue())
                ){
            return true;
        }
        return false;
    }

    /**
     * checkActiveDate
     * @param effFromDt String
     * @param effThruDt String
     * @param currentDt String
     * @return boolean
     */
    public static boolean checkActiveDate(String effFromDt, String effThruDt, String currentDt) {

        if (effFromDt.compareTo(currentDt) <= 0) {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return true;
            } else if (effThruDt.compareTo(currentDt) >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * check if from date is bigger than sales date
     * @param effFromDt String
     * @return boolean
     */
    public static boolean checkFromDate(String effFromDt) {

        if (effFromDt.compareTo(ZYPDateUtil.getSalesDate()) >= 0) {
            return true;
        }
        return false;

    }

    /**
     * checkBusinessAreaRoll
     * @param bizAreaOrgCd String
     * @param orgFuncRoleTpCd String
     * @return boolean
     */
    public static boolean checkBusinessAreaRoll(String bizAreaOrgCd, String orgFuncRoleTpCd) {

        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().checkBusinessAreaRoll(bizAreaOrgCd, orgFuncRoleTpCd);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt.compareTo(0) > 0) {
            return true;
        }
        return false;

    }

    /**
     * setBusinessAreaProtection
     * @param cMsg NMAL2500CMsg
     */
    public static void setBusinessAreaProtection(NMAL2520CMsg cMsg) {
        // 2016/03/04 CSA-QC#4654 Add Start
        if (NMAL2520Query.getInstance().isOrgCurrentOrFuture(cMsg)) {
            cMsg.actvFlg_BA.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            cMsg.actvFlg_BA.clear();
        }
        // 2016/03/04 CSA-QC#4654 Add End
    }

    /**
     * checkActiveResourceRevenue
     * @param sMsg NMAL2520SMsg
     * @param idx Integer
     * @return boolean
     */
    public static boolean checkActiveResourceRevenue(NMAL2520SMsg sMsg, Integer idx) {
        // 2016/04/04 CSA-QC#5187 Delete Start
        // boolean flgRevenue = false;
        // boolean flgFreight = false;
        // boolean flgReceivables = false;
        // 2016/04/04 CSA-QC#5187 Delete End
        String currentRevTp = null;

        S21SsmEZDResult ssmResult = NMAL2520Query.getInstance().checkActiveResourceRevenue(sMsg, idx);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            for (int j = 0; j < resultList.size(); j++) {
                Map map = (Map) resultList.get(j);
                currentRevTp = (String) map.get(NMAL2520Constant.REV_ACCT_TP_CD_DBCOLUMN);
                if (NMAL2520Constant.REV_ACCT_TP_CD_REVENUE.equals(currentRevTp)) {
                    // 2016/04/04 CSA-QC#5187 Mod Start
                    return true;
                }
            }
        }
        // 2017/10/04 QC#21344, 21359 Add Start
        sMsg.C.no(idx).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8422E);
        // 2017/10/04 QC#21344, 21359 Add End

        // 2016/04/04 CSA-QC#5187 Mod end

        // 2016/04/04 CSA-QC#5187 Delete Start
        // flgRevenue = true;
        // } else if
        // (NMAL2520Constant.REV_ACCT_TP_CD_FREIGHT.equals(currentRevTp))
        // {
        // flgFreight = true;
        // } else if
        // (NMAL2520Constant.REV_ACCT_TP_CD_RECEIVABLES.equals(currentRevTp))
        // {
        // flgReceivables = true;
        // }
        // }
        // }
        // if (flgRevenue && flgFreight && flgReceivables) {
        // return true;
        // }
        // 2016/04/04 CSA-QC#5187 Delete Start

        return false;
    }

    /**
     * checkResourceSpecific
     * @param cMsg NMAL2500CMsg
     * @param idx Integer
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean checkResourceSpecific(NMAL2520SMsg sMsg, Integer idx, String glblCmpyCd) {
        S21_PSNTMsg tMsg = new S21_PSNTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.psnCd.setValue(sMsg.C.no(idx).psnCd_C1.getValue());

        tMsg = (S21_PSNTMsg) S21FastTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            sMsg.C.no(idx).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.ZZZM9006E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_RESOURCE });
            return false;
        }

        // 2016/07/27 CSA-QC#11480 Delete Start
        // if (!ZYPCommonFunc.hasValue(tMsg.geoCd)) {
        //     cMsg.C.no(idx).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8338E);
        //     return false;
        // }
        // 2016/07/27 CSA-QC#11480 Delete End

        // 2016/03/01 CSA-QC#2766 Add Start
        if (!ZYPCommonFunc.hasValue(tMsg.lineBizTpCd)) {
            sMsg.C.no(idx).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8385E, new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_LINE_OF_BIZ, NMAL2520Constant.NAME_FOR_MESSAGE_SALES_OR_SERVICE });
            return false;
        }
        // 2016/03/01 CSA-QC#2766 Add End

        // 2016/07/27 CSA-QC#11480 Add Start
        boolean isAddrError = false;
        if (ZYPCommonFunc.hasValue(tMsg.ctryCd)) {
            if (CTRY.UNITED_STATES_OF_AMERICA.equals(tMsg.ctryCd.getValue())) {
                if (!ZYPCommonFunc.hasValue(tMsg.ctyAddr) || !ZYPCommonFunc.hasValue(tMsg.cntyPk) || !ZYPCommonFunc.hasValue(tMsg.stCd) || !ZYPCommonFunc.hasValue(tMsg.postCd)) {
                    isAddrError = true;
                }
            } else {
                if (!ZYPCommonFunc.hasValue(tMsg.ctyAddr)) {
                    isAddrError = true;
                }
            }
        } else {
            isAddrError = true;
        }

        if (isAddrError) {
            sMsg.C.no(idx).xxChkBox_C1.setErrorInfo(1, NMAL2520Constant.NMAM8385E
                    , new String[] {NMAL2520Constant.NAME_FOR_MESSAGE_PHYSICAL_LOC, NMAL2520Constant.NAME_FOR_MESSAGE_SALES_OR_SERVICE});
            return false;
        }
        // 2016/07/27 CSA-QC#11480 Add End
        return true;
    }

    /**
     * getDefaultRevenue
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @param i int
     * @return ret boolean
     */
    public static boolean getDefaultRevenue(NMAL2520SMsg sMsg, int i) {

        boolean ret = true;

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getDefaultRevenue(sMsg, i);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2520Constant.COA_BR_CD_DBCOLUMN) != null) {
                sMsg.C.no(i).coaBrCd_U2.setValue((String) map.get(NMAL2520Constant.COA_BR_CD_DBCOLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2520Constant.COA_EXTN_CD_DBCOLUMN) != null) {
                sMsg.C.no(i).coaExtnCd_U2.setValue((String) map.get(NMAL2520Constant.COA_EXTN_CD_DBCOLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2520Constant.COA_CC_CD_DBCOLUMN) != null) {
                sMsg.C.no(i).coaCcCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CC_CD_DBCOLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2520Constant.COA_CMPY_CD_DBCOLUMN) != null) {
                sMsg.C.no(i).coaCmpyCd_U2.setValue((String) map.get(NMAL2520Constant.COA_CMPY_CD_DBCOLUMN));
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }

        return ret;
    }

    /**
     * isChangedPrevTab
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @return boolean
     */
    public static boolean isChangedPrevTab(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        boolean changedFlg = false;

        if (ZYPCommonFunc.hasValue(cMsg.xxHldFlg_H0) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxHldFlg_H0.getValue())) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_OFF_N);
            return changedFlg;
        }

        if (ZYPCommonFunc.hasValue(sMsg.orgCd_H1)) {
            if (!cMsg.bizAreaOrgCd_P1.getValue().equals(sMsg.bizAreaOrgCd_P1.getValue()) || !cMsg.lineBizTpCd_P1.getValue().equals(sMsg.lineBizTpCd_P1.getValue()) || !cMsg.orgNm_H1.getValue().equals(sMsg.orgNm_H1.getValue())
                    || !cMsg.orgShortNm_H1.getValue().equals(sMsg.orgShortNm_H1.getValue()) || !cMsg.orgDescTxt_H1.getValue().equals(sMsg.orgDescTxt_H1.getValue()) || !cMsg.orgTierCd_P1.getValue().equals(sMsg.orgTierCd_P1.getValue())
                    || !cMsg.effFromDt_H1.getValue().equals(sMsg.effFromDt_H1.getValue()) || !cMsg.effThruDt_H1.getValue().equals(sMsg.effThruDt_H1.getValue()) || !cMsg.csrRgTpCd_P1.getValue().equals(sMsg.csrRgTpCd_P1.getValue())
                    || (!ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue()))) {
                changedFlg = true;
            }
        }

        if (NMAL2520Constant.TAB_BUILD_HIERARCHY.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.A.getValidCount() != cMsg.A.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    NMAL2520_ACMsg acMsg = cMsg.A.no(i);
                    NMAL2520_ASMsg asMsg = sMsg.A.no(i);
                    if (!acMsg.orgCd_A1.getValue().equals(asMsg.orgCd_A1.getValue()) //
                            || !acMsg.orgNm_A1.getValue().equals(asMsg.orgNm_A1.getValue()) //
                            || !acMsg.effFromDt_A1.getValue().equals(asMsg.effFromDt_A1.getValue()) //
                            || !acMsg.effThruDt_A1.getValue().equals(asMsg.effThruDt_A1.getValue()) //
                    ) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2520Constant.TAB_RESRC_ASIGN.equals(cMsg.xxDplyTab.getValue())) {
            // START 2017/06/14 J.Kim [QC#18924,MOD]
            if (sMsg.C.getValidCount() != cMsg.xxTotCnt.getValueInt()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < sMsg.C.getValidCount(); i++) {
                    NMAL2520_CSMsg csMsg = sMsg.C.no(i);
                    if (!csMsg.psnNum_C1.getValue().equals(csMsg.psnNum_C1.getValue()) //
                            || !csMsg.orgFuncRoleTpCd_CB.getValue().equals(csMsg.orgFuncRoleTpCd_P1.getValue()) //
                            || !csMsg.psnLastNm_CB.getValue().equals(csMsg.psnLastNm_C1.getValue()) //
                            || !csMsg.psnFirstNm_CB.getValue().equals(csMsg.psnFirstNm_C1.getValue()) //
                            || !csMsg.asgCustFromNm_CB.getValue().equals(csMsg.asgCustFromNm_C1.getValue()) //
                            || !csMsg.asgCustToNm_CB.getValue().equals(csMsg.asgCustToNm_C1.getValue()) //
                            || !csMsg.effFromDt_CB.getValue().equals(csMsg.effFromDt_C1.getValue()) //
                            || !csMsg.effThruDt_CB.getValue().equals(csMsg.effThruDt_C1.getValue())) {
                        changedFlg = true;
                    }
                }
            }
            // END 2017/06/14 J.Kim [QC#18924,MOD]
        }

        if (changedFlg == true) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NMAL2520Constant.NMAM8286W);
        }
        return changedFlg;

    }

    /**
     * clearHeader
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    public static void clearHeader(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        cMsg.orgCd_H1.clear();
        cMsg.lineBizTpCd_P1.clear();
        cMsg.orgShortNm_H1.clear();
        cMsg.orgDescTxt_H1.clear();
        cMsg.orgTierCd_P1.clear();
        cMsg.effFromDt_H1.clear();
        cMsg.struDfnDescTxt_H1.clear();
        cMsg.effThruDt_H1.clear();
        cMsg.csrRgTpCd_P1.clear();
        cMsg.xxChkBox_H1.clear();
        sMsg.orgCd_H1.clear();
        sMsg.lineBizTpCd_P1.clear();
        sMsg.orgShortNm_H1.clear();
        sMsg.orgDescTxt_H1.clear();
        sMsg.orgTierCd_P1.clear();
        sMsg.effFromDt_H1.clear();
        sMsg.effThruDt_H1.clear();
        sMsg.csrRgTpCd_P1.clear();
        sMsg.autoEstFlg_H1.clear();
    }

    /**
     * initialforAddChild
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    public static void initialforAddChild(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        // QC#5672
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(cMsg.X);
        ZYPTableUtil.clear(sMsg.X);

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2520Query.getInstance().getOrganizationForAddChild(cMsg);

        if (!ssmResult.isCodeNormal()) {
            cMsg.setMessageInfo(NMAL2520Constant.NMAM0038I);
        } else {

            Map map = (Map) ssmResult.getResultObject();

            String orgTierCd = (String) (map.get(NMAL2520Constant.ORG_TIER_CD_DBCOLUMN));
            if (orgTierCd != null) {
                int neworgTierCd = Integer.parseInt(orgTierCd) + 1;
                ZYPEZDItemValueSetter.setValue(cMsg.orgTierCd_P1, new Integer(neworgTierCd).toString());
            }

            String orgCd = (String) (map.get(NMAL2520Constant.ORG_CD_DBCOLUMN));
            if (orgCd != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(0).orgCd_A1, orgCd);
            }

            String firstOrgCd = (String) (map.get(NMAL2520Constant.FIRST_ORG_CD_DBCOLUMN));
            if (orgCd != null) {
                ZYPEZDItemValueSetter.setValue(cMsg.A.no(0).firstOrgCd_A1, firstOrgCd);
            }
        }

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(0).orgNm_A1, cMsg.orgNm_H1);
        cMsg.orgNm_H1.clear();

        ZYPEZDItemValueSetter.setValue(cMsg.A.no(0).effFromDt_A1, ZYPDateUtil.getSalesDate());

        cMsg.A.setValidCount(1);
    }

    /**
     * setBackUp
     * @param cMsg NMAL2520CMsg
     */
    public static void setBackUp(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        // QC#7966
        setBackUpHeader(cMsg);
        setBackUpParent(cMsg);
        setBackUpChild(cMsg);
        setBackUpResource(cMsg, sMsg);
    }
    
    // QC#7966
    public static void setBackUpHeader(NMAL2520CMsg cMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.orgCd_HB, cMsg.orgCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.bizAreaOrgCd_HB, cMsg.bizAreaOrgCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_HB, cMsg.lineBizTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgNm_HB, cMsg.orgNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgShortNm_HB, cMsg.orgShortNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgDescTxt_HB, cMsg.orgDescTxt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgTierCd_HB, cMsg.orgTierCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.csrRgTpCd_HB, cMsg.csrRgTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.autoEstFlg_HB, cMsg.autoEstFlg_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.orgStruTpCd_HB, cMsg.orgStruTpCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_HB, cMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_HB, cMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxChkBox_HB, cMsg.xxChkBox_H1);
    }
    
    public static void setBackUpParent(NMAL2520CMsg cMsg) {
        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgNm_AB, cMsg.A.no(i).orgNm_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgCd_AB, cMsg.A.no(i).orgCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effFromDt_AB, cMsg.A.no(i).effFromDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_AB, cMsg.A.no(i).effThruDt_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).gnrnTpCd_AB, cMsg.A.no(i).gnrnTpCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgTierCd_AB, cMsg.A.no(i).orgTierCd_A1);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).firstOrgCd_AB, cMsg.A.no(i).firstOrgCd_A1);
        }
    }

    public static void setBackUpChild(NMAL2520CMsg cMsg) {
        int i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgNm_BB, cMsg.B.no(i).orgNm_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgCd_BB, cMsg.B.no(i).orgCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effFromDt_BB, cMsg.B.no(i).effFromDt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effThruDt_BB, cMsg.B.no(i).effThruDt_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgTierCd_BB, cMsg.B.no(i).orgTierCd_B1);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).firstOrgCd_BB, cMsg.B.no(i).firstOrgCd_B1);
        }
    }

    public static void setBackUpResource(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        int i = 0;
        for (; i < sMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).psnNum_CB, sMsg.C.no(i).psnNum_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).orgFuncRoleTpCd_CB, sMsg.C.no(i).orgFuncRoleTpCd_P1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).psnLastNm_CB, sMsg.C.no(i).psnLastNm_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).psnFirstNm_CB, sMsg.C.no(i).psnFirstNm_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).asgCustFromNm_CB, sMsg.C.no(i).asgCustFromNm_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).asgCustToNm_CB, sMsg.C.no(i).asgCustToNm_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effFromDt_CB, sMsg.C.no(i).effFromDt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).effThruDt_CB, sMsg.C.no(i).effThruDt_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).psnCd_CB, sMsg.C.no(i).psnCd_C1);
            ZYPEZDItemValueSetter.setValue(sMsg.C.no(i).gnrnTpCd_CB, sMsg.C.no(i).gnrnTpCd_C1);
        }
    }

    // Add Start 2018/02/14 QC#23905
    /**
     * isChangeResrcRecord
     * @param resrc
     * @return boolean
     */
    public static boolean isChangeResrcRecord(NMAL2520_CSMsg resrc) {
        boolean result = false;

        if (isNotEquals(resrc.psnNum_CB.getValue(), resrc.psnNum_C1.getValue()) || //
                isNotEquals(resrc.orgFuncRoleTpCd_CB.getValue(), resrc.orgFuncRoleTpCd_P1.getValue()) || //
                isNotEquals(resrc.psnLastNm_CB.getValue(), resrc.psnLastNm_C1.getValue()) || //
                isNotEquals(resrc.psnFirstNm_CB.getValue(), resrc.psnFirstNm_C1.getValue()) || //
                isNotEquals(resrc.asgCustFromNm_CB.getValue(), resrc.asgCustFromNm_C1.getValue()) || //
                isNotEquals(resrc.asgCustToNm_CB.getValue(), resrc.asgCustToNm_C1.getValue()) || //
                isNotEquals(resrc.effFromDt_CB.getValue(), resrc.effFromDt_C1.getValue()) || //
                isNotEquals(resrc.effThruDt_CB.getValue(), resrc.effThruDt_C1.getValue()) || //
                isNotEquals(resrc.psnCd_CB.getValue(), resrc.psnCd_C1.getValue()) || //
                isNotEquals(resrc.gnrnTpCd_CB.getValue(), resrc.gnrnTpCd_C1.getValue())) {
            result = true;
        }

        return result;
    }

    /**
     * clearResouceAssignTab
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    public static void clearResouceAssignTab(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {
        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        sMsg.C.clear();
        sMsg.C.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
    }

    /**
     * jumpToErrorOrWarningPage
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     */
    public static void jumpToErrorOrWarningPage(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        int pageFrom = -1;
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).xxChkBox_C1.getErrorCode() != 0) {
                pageFrom = (i / cMsg.C.length()) * cMsg.C.length();
                break;
            }
        }

        if (pageFrom < 0) {
            if (cMsg.getMessageCode().endsWith("E") || cMsg.getMessageCode().endsWith("W")) {
                pageFrom = 0;
            }
        }

        if (pageFrom > -1) {
            pagenation(cMsg, sMsg, pageFrom);
        }
    }
    // Add End 2018/02/14 QC#23905

    /**
     * checkChangeParentOrganization
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangeParentOrganization(NMAL2520CMsg cMsg, int i) {
        if (cMsg.A.no(i).effFromDt_A1.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1) || cMsg.A.no(i).effThruDt_A1.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (isNotEquals(cMsg.A.no(i).orgCd_AB.getValue(), cMsg.A.no(i).orgCd_A1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkChangedFieldsForDsOrgReln
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public static boolean checkChangedFieldsForDsOrgUnit(NMAL2520CMsg cMsg) {
        if (isNotEquals(cMsg.effFromDt_HB.getValue(), cMsg.effFromDt_H1.getValue()) || isNotEquals(cMsg.effThruDt_HB.getValue(), cMsg.effThruDt_H1.getValue()) || isNotEquals(cMsg.orgNm_HB.getValue(), cMsg.orgNm_H1.getValue())
                || isNotEquals(cMsg.orgDescTxt_HB.getValue(), cMsg.orgDescTxt_H1.getValue()) || isNotEquals(cMsg.orgTierCd_HB.getValue(), cMsg.orgTierCd_P1.getValue())
                || isNotEquals(cMsg.orgShortNm_HB.getValue(), cMsg.orgShortNm_H1.getValue()) || isNotEquals(cMsg.lineBizTpCd_HB.getValue(), cMsg.lineBizTpCd_P1.getValue())
                || isNotEquals(cMsg.bizAreaOrgCd_HB.getValue(), cMsg.bizAreaOrgCd_P1.getValue()) || isNotEquals(cMsg.xxChkBox_HB.getValue(), cMsg.xxChkBox_H1.getValue())
                || isNotEquals(cMsg.csrRgTpCd_HB.getValue(), cMsg.csrRgTpCd_P1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkChangedFieldsForTocOrgStruReln
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForTocOrgStruReln(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue()) || isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
            return true;
        }
        return false;
    }

    // Add Start 2017/10/13 QC#21753
    /**
     * checkChangedFieldsForOrgTocChngRqst
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForOrgTocChngRqst(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue()) || isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
            return true;
        }
        // Add Start 2019/02/27 QC#30564
        if (isChangedResrcAsignRsrcNum(sMsg, i)) {
            return true;
        }
        if (isChangedResrcAsignRoleAndOther(sMsg, i)) {
            return true;
        }
        // Add End 2019/02/27 QC#30564
        return false;
    }
    // Add End 2017/10/13 QC#21753

    // Add Start 2018/04/13 QC#23867
    /**
     * checkChangedFieldsForOrgFuncAsg
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForOrgFuncAsg(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue()) || isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
            return true;
        }
        // Add Start 2019/02/27 QC#30564
        if (isChangedResrcAsignRsrcNum(sMsg, i)) {
            return true;
        }
        // Add End 2019/02/27 QC#30564
        return false;
    }

    /**
     * checkChangedResrcAsignStartDate
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedResrcAsignStartDate(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue())) {
            return true;
        }
        return false;
    }
    // Add End 2018/04/13 QC#23867

    // Add Start 2019/02/27 QC#30564
    /**
     * checkChangedResrcAsignRsrcNum
     * @param sMsg NMAL2610SMsg
     * @param i int
     * @return boolean
     */
    public static boolean isChangedResrcAsignRsrcNum(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).psnCd_CB.getValue(), sMsg.C.no(i).psnCd_C1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isChangedResrcAsignRoleAndOther
     * @param sMsg NMAL2610SMsg
     * @param i int
     * @return boolean
     */
    public static boolean isChangedResrcAsignRoleAndOther(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).orgFuncRoleTpCd_CB.getValue(), sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue()) || //
                isNotEquals(sMsg.C.no(i).asgCustFromNm_CB.getValue(), sMsg.C.no(i).asgCustFromNm_C1.getValue()) || //
                isNotEquals(sMsg.C.no(i).asgCustToNm_CB.getValue(), sMsg.C.no(i).asgCustToNm_C1.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * isChangedResrcAsignDate
     * @param sMsg NMAL2610SMsg
     * @param i int
     * @return boolean
     */
    public static boolean isChangedResrcAsignDate(NMAL2520SMsg sMsg, int i) {
        if (isNotEquals(sMsg.C.no(i).effFromDt_CB.getValue(), sMsg.C.no(i).effFromDt_C1.getValue()) || //
                isNotEquals(sMsg.C.no(i).effThruDt_CB.getValue(), sMsg.C.no(i).effThruDt_C1.getValue())) {
            return true;
        }
        return false;
    }
    // Add End 2019/02/27 QC#30564

    /**
     * checkChangeOrganization
     * @param cMsg NMAL2610CMsg
     * @return boolean
     */
    public static boolean checkChangeOrganization(NMAL2520CMsg cMsg) {
        if (cMsg.effFromDt_HB.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(cMsg.effThruDt_H1) || cMsg.effThruDt_H1.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (isNotEquals(cMsg.orgNm_HB.getValue(), cMsg.orgNm_H1.getValue())) {
                return true;
            }
        }

        for (int i = 0; i < cMsg.A.getValidCount(); i++) {
            if (checkChangeParentOrganization(cMsg, i)) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkChangeResourceAssignSpec
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangeActiveResourceAssign(NMAL2520SMsg sMsg, int i) {
        if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1) || sMsg.C.no(i).effThruDt_C1.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (isNotEquals(sMsg.C.no(i).orgFuncRoleTpCd_CB.getValue(), sMsg.C.no(i).orgFuncRoleTpCd_P1.getValue()) || isNotEquals(sMsg.C.no(i).asgCustFromNm_CB.getValue(), sMsg.C.no(i).asgCustFromNm_C1.getValue())
                    || isNotEquals(sMsg.C.no(i).asgCustToNm_CB.getValue(), sMsg.C.no(i).asgCustToNm_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * checkChangeActivePersonCode
     * @param cMsg NMAL2610CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangeActivePersonCode(NMAL2520SMsg sMsg, int i) {
        if (sMsg.C.no(i).effFromDt_C1.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(sMsg.C.no(i).effThruDt_C1) || sMsg.C.no(i).effThruDt_C1.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (isNotEquals(sMsg.C.no(i).psnCd_CB.getValue(), sMsg.C.no(i).psnCd_C1.getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * compare date
     * @param cmprFrom String
     * @param cmprTo String
     * @return boolean true when "Compare From Date" is Bigger
     */
    public static boolean compareDate(String cmprFrom, String cmprTo) {
        if (!ZYPCommonFunc.hasValue(cmprFrom)) {
            cmprFrom = "99991231";
        }
        if (!ZYPCommonFunc.hasValue(cmprTo)) {
            cmprTo = "99991231";
        }

        if (cmprFrom.compareTo(cmprTo) > 0) {
            return true;
        }
        return false;
    }

    /**
     * isNotEquals
     * @param backUp String
     * @param orig String
     * @return boolean
     */
    public static boolean isNotEquals(String backUp, String orig) {
        if (!nvl(orig).equals(nvl(backUp))) {
            return true;
        }
        return false;
    }

    /**
     * isNotEquals
     * @param orig BigDecimal
     * @param backUp BigDecimal
     * @return boolean
     */
    public static boolean isNotEquals(BigDecimal orig, BigDecimal backUp) {
        if (orig == null) {
            if (backUp == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if (backUp == null) {
                return true;
            }
        }
        if (orig.compareTo(backUp) != 0) {
            return true;
        }
        return false;
    }

    /**
     * nvl
     * @param val String
     * @return String
     */
    private static String nvl(String val) {
        if (!ZYPCommonFunc.hasValue(val)) {
            return "";
        }
        return val;
    }

    // QC#3509
    private static boolean isPeriodOverlap(EZDSDateItem effFromDtFrom, EZDSDateItem effThruDtFrom, EZDSDateItem effFromDtTo, EZDSDateItem effThruDtTo) {
        return isPeriodOverlap(effFromDtFrom.getValue(), effThruDtFrom.getValue(), effFromDtTo.getValue(), effThruDtTo.getValue());
    }

    private static boolean isPeriodOverlap(String effFromDtFrom, String effThruDtFrom, String effFromDtTo, String effThruDtTo) {
        if (!ZYPCommonFunc.hasValue(effThruDtFrom)) {
            effThruDtFrom = NMAL2520Constant.DB_PARAM_MAX_EFF_DT;
        }
        if (!ZYPCommonFunc.hasValue(effThruDtTo)) {
            effThruDtTo = NMAL2520Constant.DB_PARAM_MAX_EFF_DT;
        }
        if (isDateInPeriod(effFromDtFrom, effFromDtTo, effThruDtTo)) {
            return true;
        }
        if (isDateInPeriod(effThruDtFrom, effFromDtTo, effThruDtTo)) {
            return true;
        }
        if (isDateInPeriod(effFromDtTo, effFromDtFrom, effThruDtFrom)) {
            return true;
        }
        if (isDateInPeriod(effThruDtTo, effFromDtFrom, effThruDtFrom)) {
            return true;
        }
        return false;
    }

    private static boolean isDateInPeriod(String date, String effFromDt, String effThruDt) {
        if (date.compareTo(effFromDt) >= 0 && date.compareTo(effThruDt) <= 0) {
            return true;
        }
        return false;
    }

    // 2016/07/07 CSA-QC#11454 Add Start
    /**
     * checkActiveChildList
     * @param cMsg NMAL2520CMsg
     * @param idx int
     * @param hdrFlg String
     * @return boolean
     */
    private static boolean checkActiveChildList(NMAL2520CMsg cMsg, int idx, String hdrFlg) {

        String slsDt = ZYPDateUtil.getSalesDate();
        String endDt = cMsg.effThruDt_H1.getValue();
        if (ZYPConstant.FLG_OFF_N.equals(hdrFlg)) {
            endDt = cMsg.A.no(idx).effThruDt_A1.getValue();
        }

        for (int i = 0; i < cMsg.B.getValidCount(); i++) {
            NMAL2520_BCMsg bCMSg = cMsg.B.no(i);
            String effThruDt = "99991231";
            if (ZYPCommonFunc.hasValue(bCMSg.effThruDt_B1)) {
                effThruDt = bCMSg.effThruDt_B1.getValue();
            }

            if (ZYPDateUtil.compare(slsDt, bCMSg.effFromDt_B1.getValue()) >= 0 && ZYPDateUtil.compare(slsDt, effThruDt) <= 0 //
                    && ZYPDateUtil.compare(endDt, bCMSg.effFromDt_B1.getValue()) >= 0 && ZYPDateUtil.compare(endDt, effThruDt) < 0) {
                return false;
            }
        }
        return true;
    }
    // 2016/07/07 CSA-QC#11454 End Start

    // QC#3859
    public static boolean isCurrentOrFuturePeriod(String thruDt, String slsDt){
        if(ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(slsDt) < 0){
            return false;
        }
        return true;
    }

    // START 2017/06/14 J.Kim [QC#18924,ADD]
    /**
     * Pagenation
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @param pageFrom int
     */
    public static void pagenation(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.C.length(); i++) {
            if (i < sMsg.C.getValidCount()) {
                EZDMsg.copy(sMsg.C.get(i), null, cMsg.C.get(i - pageFrom), null);
            } else {
                break;
            }
        }
        cMsg.C.setValidCount(i - pageFrom);

        cMsg.xxTotCnt.setValue(sMsg.C.getValidCount());
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.C.getValidCount());
    }

    /**
     * setPagenation
     * @param cMsg NMAL2520CMsg
     * @param sMsg NMAL2520SMsg
     * @param pageFrom int
     */
    public static void setPagenation(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg, int pageFrom) {

        int cnt = pageFrom;
        for (; cnt < pageFrom + cMsg.C.length(); cnt++) {
            if (cnt < pageFrom + cMsg.C.getValidCount()) {
                EZDMsg.copy(cMsg.C.get(cnt - pageFrom), null, sMsg.C.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param page int
     * @return page
     */
    public static int convertPageNumToFirstRowIndex(int rowsPerPage, int page) {
        if (page <= 0) {
            return 0;
        }
        return rowsPerPage * (page - 1);
    }

    /**
     * @param maxDispalyRows int
     * @param rowNum int
     * @return int
     */
    public static int getPageNum(int maxDispalyRows, int rowNum) {
        return ((rowNum - 1) / maxDispalyRows + 1);
    }

    /**
     * convertPageNumToFirstRowIndex
     * @param rowsPerPage int
     * @param sMsgCount int
     * @return int
     */
    public static int convertFirstRowIndex(int rowsPerPage, int sMsgCount) {

        int insertPage = (sMsgCount / rowsPerPage) * rowsPerPage;
        return insertPage;
    }

    public static void jumpToErrorPage(NMAL2520CMsg cMsg, NMAL2520SMsg sMsg) {

        // 2018/06/12 QC#20220 Mod Start
        //int errPage = 1;
        int errPage = 0;
        // 2018/06/12 QC#20220 Mod End
        for (int i = 0; i < sMsg.C.getValidCount(); i++) {
            if (sMsg.C.no(i).xxChkBox_C1.isError() || sMsg.C.no(i).psnNum_C1.isError() //
                    || sMsg.C.no(i).orgFuncRoleTpCd_P1.isError() || sMsg.C.no(i).psnLastNm_C1.isError() //
                    || sMsg.C.no(i).psnFirstNm_C1.isError() || sMsg.C.no(i).asgCustFromNm_C1.isError() //
                    || sMsg.C.no(i).asgCustToNm_C1.isError() || sMsg.C.no(i).effFromDt_C1.isError() //
                    || sMsg.C.no(i).effThruDt_C1.isError() || sMsg.C.no(i).psnCd_C1.isError() //
                    || sMsg.C.no(i).gnrnTpCd_C1.isError()
                    // 2017/10/04 QC#21359 Add Start
                    // 2018/06/12 QC#20220 Del Start
                    // || cMsg.getMessageCode().endsWith("E") || cMsg.getMessageCode().endsWith("W")
                    // 2018/06/12 QC#20220 Del End
                    // 2017/10/04 QC#21359 Add Start
            ) {
                errPage = (i / cMsg.C.length()) * cMsg.C.length();
                break;
            }
        }

        cMsg.xxPageShowFromNum.setValue(errPage + 1);
        int pageFrom = cMsg.xxPageShowFromNum.getValueInt() - 1;
        pagenation(cMsg, sMsg, pageFrom);
    }
    // END 2017/06/14 J.Kim [QC#18924,ADD]

    // Add Start 2017/10/13 QC#21753
    /**
     * @param cMsg NMAL2520CMsg
     * @param index int
     * @return boolean
     */
    private static boolean isDuplicateParentTerm(NMAL2520CMsg cMsg, int index) {
        boolean result = false;

        for (int i = index + 1; i < cMsg.A.getValidCount(); i++) {
            // Check duplicate active parent territory
            if (!checkDuplicateTerm(cMsg.A.no(index).effFromDt_A1.getValue(), cMsg.A.no(index).effThruDt_A1.getValue(), cMsg.A.no(i).effFromDt_A1.getValue(), cMsg.A.no(i).effThruDt_A1.getValue())) {
                cMsg.A.no(index).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
                cMsg.A.no(index).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
                cMsg.A.no(i).effFromDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
                cMsg.A.no(i).effThruDt_A1.setErrorInfo(1, NMAL2520Constant.NMAM8270E);
                result = true;
            }
        }

        return result;
    }

    /**
     * checkDuplicateTerm
     * @param effFromDt String
     * @param effThruDt String
     * @param cmprEffFromDt String
     * @param cmprEffThruDt String
     * @return boolean
     */
    public static boolean checkDuplicateTerm(String effFromDt, String effThruDt, String cmprEffFromDt, String cmprEffThruDt) {

        if (ZYPCommonFunc.hasValue(cmprEffThruDt) && effFromDt.compareTo(cmprEffThruDt) > 0) {
            return true;
        } else {
            if (!ZYPCommonFunc.hasValue(effThruDt)) {
                return false;
            } else if (effThruDt.compareTo(cmprEffFromDt) < 0) {
                return true;
            }
        }
        return false;
    }
    // Add End 2017/10/13 QC#21753

    // 2017/11/21 QC#21350 add start
    public static String copyHdrEndDt(String hdrThruDt, String dtlFromDt, String dtlThruDt) {
        if (hdrThruDt.compareTo(dtlFromDt) < 0) {
            return "";
        } else if (!ZYPCommonFunc.hasValue(dtlThruDt) || hdrThruDt.compareTo(dtlThruDt) <= 0) {
            return hdrThruDt;
        }

        return dtlThruDt;
    }
    // 2017/11/21 QC#21350 add end

    // 2018/01/10 QC#20233 add start
    public static boolean checkNextStartParentOrg(NMAL2520CMsg cMsg, int idx) {
        // 2018/03/07 QC#20233-1 mod start
//        String date = cMsg.A.no(idx).effThruDt_A1.getValue();
        String date = ZYPDateUtil.addDays(cMsg.A.no(idx).effThruDt_A1.getValue(), 1);
        // 2018/03/07 QC#20233-1 mod end

        if (!ZYPCommonFunc.hasValue(date)) {
            return true;
        }

        for (int i=0; i < cMsg.A.getValidCount(); i++) {
            if (date.equals(cMsg.A.no(i).effFromDt_A1.getValue())) {
                return true;
            }
        }
        return false;
    }
    // 2018/01/10 QC#20233 add end
}

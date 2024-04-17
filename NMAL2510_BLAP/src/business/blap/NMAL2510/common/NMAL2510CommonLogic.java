/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2510.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCBigDecimalItemArray;
import parts.common.EZDCDateItem;
import parts.common.EZDCStringItemArray;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.blap.NMAL2510.NMAL2510Query;
import business.blap.NMAL2510.NMAL2510SMsg;
import business.blap.NMAL2510.NMAL2510_ACMsg;
import business.blap.NMAL2510.NMAL2510_ASMsg;
import business.blap.NMAL2510.NMAL2510_BCMsg;
import business.blap.NMAL2510.NMAL2510_BSMsg;
import business.blap.NMAL2510.NMAL2510_CCMsg;
import business.blap.NMAL2510.NMAL2510_CSMsg;
import business.blap.NMAL2510.constant.NMAL2510Constant;
import business.db.BIZ_AREA_ORGTMsg;
import business.db.DEF_DPLY_COA_INFOTMsg;
import business.db.ORG_FUNC_ASGTMsg;
import business.db.ORG_FUNC_ROLE_TPTMsg;
import business.db.ORG_TOC_CHNG_RQSTTMsg;
import business.db.S21_PSNTMsg;
import business.parts.NFZC102001PMsg;
import business.parts.NFZC102001_xxMsgIdListPMsg;
import business.parts.NMXC107001PMsg;

import com.canon.cusa.s21.api.NFA.NFZC102001.NFZC102001;
import com.canon.cusa.s21.api.NMX.NMXC107001.NMXC107001;
import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_EXTN;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_FUNC_ROLE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPOracleSeqAccessor;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;

/**
 * <pre>
 * Business ID : NMAL2510 Resource Maintenance
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/19   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/02/02   Fujitsu         C.Yokoi         Update          CSA-QC#2867
 * 2016/02/03   Fujitsu         C.Yokoi         Update          CSA-QC#1930
 * 2016/02/08   Fujitsu         C.Yokoi         Update          CSA-QC#2869
 * 2016/02/23   Fujitsu         C.Yokoi         Update          CSA-QC#4336
 * 2016/02/29   Fujitsu         C.Yokoi         Update          CSA-QC#1946, CSA-QC#4080, CSA-QC#3859
 * 2016/03/01   Fujitsu         C.Yokoi         Update          CSA-QC#2766
 * 2016/03/03   Fujitsu         W.Honda         Update          CSA-QC#4545
 * 2016/03/04   Fujitsu         C.Yokoi         Update          CSA-QC#3858, CSA-QC#4654
 * 2016/03/16   Fujitsu         C.Yokoi         Update          CSA-QC#5415
 * 2016/03/18   Fujitsu         C.Yokoi         Update          CSA-QC#5658, CSA-QC#5415
 * 2016/03/22   Fujitsu         C.Yokoi         Update          CSA-QC#5658
 * 2016/03/28   SRAA            Y.Chen          Update          CSA-QC#3509
 * 2016/04/04   Fujitsu         C.Yokoi         Update          CSA-QC#5187
 * 2016/04/05   Fujitsu         C.Yokoi         Update          CSA-QC#5411, CSA-QC#6003
 * 2016/04/13   Fujitsu         C.Yokoi         Update          CSA-QC#6960
 * 2016/04/14   Fujitsu         C.Yokoi         Update          CSA-QC#4080
 * 2016/06/20   Hitachi         A.Kohinata      Update          CSA-QC#10424
 * 2016/06/29   Hitachi         A.Kohinata      Update          CSA-QC#11087
 * 2016/07/27   Fujitsu         C.Yokoi         Update          CSA-QC#11480
 * 2016/08/16   SRAA            Y.Chen          Update          CSA-QC#3859
 * 2016/08/23   Fujitsu         C.Yokoi         Update          CSA-QC#13277
 * 2016/08/26   SRAA            Y.Chen          Update          CSA-QC#10364
 * 2016/09/01   Fujitsu         C.Yokoi         Update          CSA-QC#11604
 * 2016/09/05   Fujitsu         C.Yokoi         Update          CSA-QC#4099
 * 2016/09/30   Hitachi         Y.Takeno        Update          CSA-QC#14809
 * 2016/10/04   Fujitsu         Mz.Takahashi    Update          CSA-QC#4099
 * 2016/10/07   Fujitsu         Mz.Takahashi    Update          CSA-QC#15033
 * 2016/10/12   Fujitsu         C.Yokoi         Update          CSA-QC#4096
 * 2016/10/17   Fujitsu         N.Sugiura       Update          CSA-QC#15140
 * 2016/11/09   Fujitsu         N.Sugiura       Update          CSA-QC#15341
 * 2016/11/10   Fujitsu         C.Yokoi         Update          CSA-QC#14219
 * 2016/11/21   Fujitsu         C.Yokoi         Update          CSA-QC#16113
 * 2016/12/02   Fujitsu         N.Sugiura       Update          CSA-QC#15140-3
 * 2016/12/06   Fujitsu         C.Yokoi         Update          CSA-QC#16362
 * 2017/06/23   Fujitsu         H.Ikeda         Update          CSA-QC#18925
 * 2017/10/17   Fujitsu         H.Sugawara      Update          QC#21753
 * 2018/01/23   Hitachi         J.Kim           Update          QC#23374
 * 2018/03/29   Fujitsu         K.Ishizuka      Update          QC#23171
 * 2018/07/26   Fujitsu         R.Nakamura      Update          CSA-QC#20237
 * 2018/10/02   Fujitsu         H.Kumagai       Update          QC#24923
 * 2019/02/13   Fujitsu         Hd.Sugawara     Update          QC#29668
 * 2019/02/27   Fujitsu         Hd.Sugawara     Update          QC#30564
 * 2019/05/28   Fujitsu         M.Ohno          Update          QC#50379
 * 2019/11/29   Fujitsu         C.Hara          Update          QC#54234
 * 2019/12/26   Fujitsu         C.Hara          Update          QC#55129
 * 2020/01/10   Fujitsu         M.Ohno          Update          QC#55356
 *</pre>
 */
public class NMAL2510CommonLogic {

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
     * Mandantory input item check
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    // 2018/10/02 Update Start QC#24923
//    public static boolean checkInputMandantoryForSubmit(NMAL2510CMsg cMsg) {
    public static boolean checkInputMandantoryForSubmit(NMAL2510CMsg cMsg, String globalCompanyCode) {
    // 2018/10/02 Update End QC#24923
        boolean successFlg = true;

        // ## Hierarychy ##
        for (int i = 0; i < cMsg.A.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).bizAreaOrgCd_P2)) {
                cMsg.A.no(i).bizAreaOrgCd_P2.setErrorInfo(1, "ZZM9000E", new String[] {"Business Area" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).orgFuncRoleTpCd_P2)) {
                cMsg.A.no(i).orgFuncRoleTpCd_P2.setErrorInfo(1, "ZZM9000E", new String[] {"Role" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).orgNm_A2)) {
                cMsg.A.no(i).orgNm_A2.setErrorInfo(1, "ZZM9000E", new String[] {"Organization Name" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effFromDt_A2)) {
                cMsg.A.no(i).effFromDt_A2.setErrorInfo(1, "ZZM9000E", new String[] {"start date" });
                successFlg = false;
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_HIERARCHY);
            return successFlg;
        }

        // ## Territory ##
        for (int i = 0; i < cMsg.B.getValidCount(); i++) {

            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).bizAreaOrgCd_P3)) {
                cMsg.B.no(i).bizAreaOrgCd_P3.setErrorInfo(1, "ZZM9000E", new String[] {"Business Area" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).orgNm_B2)) {
                cMsg.B.no(i).orgNm_B2.setErrorInfo(1, "ZZM9000E", new String[] {"Territory Name" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).effFromDt_B2)) {
                cMsg.B.no(i).effFromDt_B2.setErrorInfo(1, "ZZM9000E", new String[] {"start Date" });
                successFlg = false;
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_TERRITORY);
            return successFlg;
        }

        // ## Revenue Data ##
        // 2018/10/02 Add Start QC#24923
        String DefaultCoaBuCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2510Constant.VAR_CHAR_CONST_NM_DEFAULT_COA_BU_CD, globalCompanyCode);
        String DefaultCoaBrCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2510Constant.VAR_CHAR_CONST_NM_DEFAULT_COA_BR_CD, globalCompanyCode);
        String DefaultCoaCcCd = ZYPCodeDataUtil.getVarCharConstValue(NMAL2510Constant.VAR_CHAR_CONST_NM_DEFAULT_COA_CC_CD, globalCompanyCode);
        // 2018/10/02 Add End QC#24923
        
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).revAcctTpCd_P1)) {
                cMsg.C.no(i).revAcctTpCd_P1.setErrorInfo(1, "ZZM9000E", new String[] {"Revenue Account Type" });
                successFlg = false;
            }

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).coaCmpyCd_C2)) {
                cMsg.C.no(i).coaCmpyCd_C2.setErrorInfo(1, "ZZM9000E", new String[] {"COA Company" });
                successFlg = false;
            }

            // 2018/10/02 Update Start QC#24923
            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).coaExtnCd_C2)) {
                cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, "ZZM9000E", new String[] {"COA BU" });
                successFlg = false;
            } else if (cMsg.psnTpCd_P1.getValue().equals(PSN_TP.EMPLOYEE)) {
                if (cMsg.C.no(i).coaExtnCd_C2.getValue().equals(DefaultCoaBuCd)) {
                    // 2019/12/26 QC#55129 Mod Start
                    // cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, "NMAM0179E", new String[] {DefaultCoaBuCd, "COA BU" });
                    cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, NMAL2510Constant.NMAM8682E, new String[] {DefaultCoaBuCd+"(DEFAULT)", "Type", "Employee" });
                    // 2019/12/26 QC#55129 Mod Start
                    successFlg = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).coaBrCd_C2)) {
                cMsg.C.no(i).coaBrCd_C2.setErrorInfo(1, "ZZM9000E", new String[] {"COA Brunch" });
                successFlg = false;
            } else if (cMsg.psnTpCd_P1.getValue().equals(PSN_TP.EMPLOYEE)) {
                if (cMsg.C.no(i).coaBrCd_C2.getValue().equals(DefaultCoaBrCd)) {
                    // 2019/12/26 QC#55129 Mod Start
                    // cMsg.C.no(i).coaBrCd_C2.setErrorInfo(1, "NMAM0179E", new String[] {DefaultCoaBrCd, "COA Brunch" });
                    cMsg.C.no(i).coaBrCd_C2.setErrorInfo(1, NMAL2510Constant.NMAM8682E, new String[] {DefaultCoaBrCd+"(DEFAULT)", "Type", "Employee" });
                    // 2019/12/26 QC#55129 Mod End
                    successFlg = false;
                }
            }

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).coaCcCd_C2)) {
                cMsg.C.no(i).coaCcCd_C2.setErrorInfo(1, "ZZM9000E", new String[] {"COA CC" });
                successFlg = false;
            } else if (cMsg.psnTpCd_P1.getValue().equals(PSN_TP.EMPLOYEE)) {
                if (cMsg.C.no(i).coaCcCd_C2.getValue().equals(DefaultCoaCcCd)) {
                    // 2019/12/26 QC#55129 Mod Start
                    // cMsg.C.no(i).coaCcCd_C2.setErrorInfo(1, "NMAM0179E", new String[] {DefaultCoaCcCd, "COA CC" });
                    cMsg.C.no(i).coaCcCd_C2.setErrorInfo(1, NMAL2510Constant.NMAM8682E, new String[] {DefaultCoaCcCd+"(DEFAULT)", "Type", "Employee" });
                    // 2019/12/26 QC#55129 Mod End
                    successFlg = false;
                }
            }
            // 2018/10/02 Update End   QC#24923

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).effFromDt_C2)) {
                cMsg.C.no(i).effFromDt_C2.setErrorInfo(1, "ZZM9000E", new String[] {"Start Date" });
                successFlg = false;
            }
        }

        if (!successFlg) {
            cMsg.xxDplyTab.setValue(NMAL2510Constant.TAB_REVENUE);
        }
        return successFlg;
    }

    /**
     * check Input Header For Submit
     * @param cMsg NMAL2510CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    public static boolean checkInputHeaderForSubmit(NMAL2510CMsg cMsg, String globalCompanyCode) {
        // 2016/11/10 CSA-QC#14219 Add Stat
        if (isNotEquals(cMsg.hrSupvId_H1.getValue(), cMsg.hrSupvId_HB.getValue())) {
            if (!isExistsS21Psn(globalCompanyCode, cMsg)) {
                cMsg.hrSupvId_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9006E, new String[]{NMAL2510Constant.NAME_FOR_MESSAGE_HR_SUPV_ID});
                return false;
            }
        }

        return true;
        // 2016/11/10 CSA-QC#14219 Add Stat
        // 2016/04/14 CSA-QC#4080 Del Start
        // // 2016/02/29 CSA-QC#4080 Add Start
        // // Employee end date < today then error
        // if (ZYPCommonFunc.hasValue(cMsg.effThruDt_H1)
        // &&
        // !cMsg.effThruDt_H1.getValue().equals(cMsg.effThruDt_HB.getValue()))
        // {
        // if
        // (cMsg.effThruDt_H1.getValue().compareTo(ZYPDateUtil.getSalesDate())
        // < 0) {
        // cMsg.effThruDt_H1.setErrorInfo(1,
        // NMAL2510Constant.NMAM0044E, new String[]
        // {NMAL2510Constant.NAME_FOR_MESSAGE_EMPLOYMENT_DATE_TO,
        // NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
        // return false;
        // }
        // }
        // return true;
        // // 2016/02/29 CSA-QC#4080 Add End
        // 2016/04/14 CSA-QC#4080 Del End
    }

    // 2016/06/20 CSA-QC#10424 Add Start
    /**
     * isExistsS21Psn
     * @param globalCompanyCode String
     * @param orgFuncRoleTpCd String
     * @return boolean
     */
    public static boolean isExistsS21Psn(String globalCompanyCode, NMAL2510CMsg cMsg) {
        // 2016/11/10 CSA-QC#14219 Add Stat
        String psnCd = cMsg.hrSupvId_H1.getValue();
        if (!ZYPCommonFunc.hasValue(psnCd)) {
            return true;
        }

        S21_PSNTMsg tMsg = new S21_PSNTMsg();
        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        tMsg.psnCd.setValue(psnCd);

        tMsg = (S21_PSNTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            String psnNm = S21StringUtil.subStringByLength(tMsg.psnFirstNm.getValue() + " " + tMsg.psnLastNm.getValue(), 0, NMAL2510Constant.COLUMN_LEN_HR_SUPV_NM);
            ZYPEZDItemValueSetter.setValue(cMsg.hrSupvNm_H1, psnNm);
            return true;
        }
        return false;
        // 2016/11/10 CSA-QC#14219 Add Stat
    }

    /**
     * check Input Hierarchy For Submit
     * @param cMsg NMAL2500CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    // 2016/06/20 CSA-QC#10424 Mod Start
    public static boolean checkInputHierarchyForSubmit(NMAL2510CMsg cMsg, String globalCompanyCode, boolean isChangePsnEndDt) {
        // 2016/06/20 CSA-QC#10424 Mod End

        int i = 0;
        int activeSalesRollCnt = 0;
        boolean isSalesOrServiceRole = false;
        // 2017/06/23 CSA-QC#18925 Add Start
        boolean typeSales = false;
        String orgFuncRole = null;
        // 2017/06/23 CSA-QC#18925 Add End
        List<Integer> errorIndex = new ArrayList<Integer>();
        String orgRelnStartDate = null;
        boolean isError = false;

        for (; i < cMsg.A.getValidCount(); i++) {
            // 2017/09/29 CSA-QC#21361 Add Start
            if (isChangePsnEndDt) {
                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effFromDt_A2.getValue()) < 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_A2, cMsg.A.no(i).effFromDt_A2);
                } else if (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2.getValue()) || cMsg.effThruDt_H1.getValue().compareTo(cMsg.A.no(i).effThruDt_A2.getValue()) <= 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_A2, cMsg.effThruDt_H1);
                }
            }
            // 2017/09/29 CSA-QC#21361 Add End

            if (!checkBusinessAreaRoll(cMsg.A.no(i).bizAreaOrgCd_P2.getValue(), cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue())) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8285E);
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8285E);
                return false;
            }

            if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2)) {
                // Assign from date > Assign start date then error
                if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(cMsg.A.no(i).effThruDt_A2.getValue()) > 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.A.no(i).effFromDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8239E);
                    cMsg.A.no(i).effThruDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8239E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8239E);
                    return false;
                }

                // 2016/02/03 CSA-QC#1930 Delete Start
                // Assign end date < today then error
                // if
                // (cMsg.A.no(i).effThruDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate())
                // < 0) {
                // cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new
                // String[] {NMAL2510Constant.RESOURCE_ASSIGN +
                // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
                // NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return false;
                // }
                // 2016/02/03 CSA-QC#1930 Delete End
            }

            // Assign end date > Organization end date then error
            if (compareDate(cMsg.A.no(i).effThruDt_A2.getValue(), cMsg.A.no(i).effThruDt_A3.getValue())) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).effThruDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8236E);
                cMsg.A.no(i).effThruDt_A3.setErrorInfo(1, NMAL2510Constant.NMAM8236E);
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8236E);
                return false;
            }

            // Assign start date < organization start date then error
            if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(cMsg.A.no(i).effFromDt_A3.getValue()) < 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).effFromDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8236E);
                cMsg.A.no(i).effFromDt_A3.setErrorInfo(1, NMAL2510Constant.NMAM8236E);
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8236E);
                return false;
            }

            // 2016/09/30 CSA-QC#14809 Add Start
            // Assign start date < Employment Date From
            if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(cMsg.effFromDt_H1.getValue()) < 0) {
                cMsg.A.no(i).effFromDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8241E);
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8241E);
                return false;
            }

            // Assign end date > Employment Date To
            if (compareDate(cMsg.A.no(i).effThruDt_A2.getValue(), cMsg.effThruDt_H1.getValue())) {
                cMsg.A.no(i).effThruDt_A2.setErrorInfo(1, NMAL2510Constant.NMAM8242E);
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8242E);
                return false;
            }
            // 2016/09/30 CSA-QC#14809 Add End

            S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().checkBusinessAreaSalesForOrganization(cMsg, i);
            Integer resCnt = (Integer) ssmResult.getResultObject();
            // Only Check if business area is Sales.
            if (resCnt.compareTo(0) > 0) {

                // Multiple active sales role check
                // QC#3509
                // if
                // (checkActiveDate(cMsg.A.no(i).effFromDt_A2.getValue(),
                // cMsg.A.no(i).effThruDt_A2.getValue(),
                // ZYPDateUtil.getSalesDate())) {
                if (isCurrentOrFuturePeriod(cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                    ssmResult = NMAL2510Query.getInstance().checkActiveSalesRepRoll(cMsg, i); // 2016/12/02 CSA-QC#15140-2 Mod

                    // 2016/03/03 CSA-QC#3858 Add Start
                    if ((Integer) ssmResult.getResultObject() > 0) {
                        errorIndex.add(i);
                    }
                    // 2016/03/03 CSA-QC#3858 Add End
                }

                // QC#3509
                // }

                // 2016/03/16 CSA-QC#5415 Delete Start
                // Revenue account must be assigned
                // if (!checkActiveResourceRevenue(cMsg)) {
                // cMsg.setMessageInfo(NMAL2510Constant.NMAM8238E);
                // return false;
                // }
                // 2016/03/16 CSA-QC#5415 Delete End

            }

            // For Manual Input pressed Add button
            ssmResult = NMAL2510Query.getInstance().getHierachyDataforManualInput(cMsg, i);
            if (!ssmResult.isCodeNormal()) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.A.no(i).orgNm_A2.setErrorInfo(1, NMAL2510Constant.NMAM8186E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8186E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME });
                return false;
            } else {
                List resultList = (List) ssmResult.getResultObject();
                Map map = (Map) resultList.get(0);

                if (ZYPCommonFunc.hasValue((String) map.get("ORG_CD"))) {
                    cMsg.A.no(i).orgCd_A2.setValue((String) map.get("ORG_CD"));
                } else {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.A.no(i).orgNm_A2.setErrorInfo(1, NMAL2510Constant.NMAM8186E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME });
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8186E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME });
                    return false;
                }

                // Selected Organization should be within the business
                // area.
                if (ZYPCommonFunc.hasValue((String) map.get("FIRST_ORG_CD"))) {
                    if (!cMsg.A.no(i).bizAreaOrgCd_P2.getValue().equals((String) map.get("FIRST_ORG_CD"))) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.A.no(i).bizAreaOrgCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8288E);
                        // 2016/03/03 CSA-QC#4545 Add End
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM8288E);
                        return false;
                    }
                } else {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.A.no(i).bizAreaOrgCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8288E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8288E);
                    return false;
                }

                if (ZYPCommonFunc.hasValue((String) map.get("ORG_STRU_TP_CD"))) {
                    cMsg.A.no(i).orgStruTpCd_A2.setValue((String) map.get("ORG_STRU_TP_CD"));
                } else {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.A.no(i).orgStruTpCd_A2.setErrorInfo(1, NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_STRUCTURE_TYPE });
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_STRUCTURE_TYPE });
                    return false;
                }
            }

            // Active Sales Role must be assigned.
            if (resCnt.compareTo(0) > 0) {
                ssmResult = NMAL2510Query.getInstance().checkSalesRoll(cMsg, i);
                activeSalesRollCnt = activeSalesRollCnt + (Integer) ssmResult.getResultObject();
                if (activeSalesRollCnt == 0) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8282E);
                    return false;
                }
            }

            // 2016/04/13 CSA-QC#6960 Add Start
            if (checkActiveDate(cMsg.A.no(i).effFromDt_A2.getValue(), cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                ssmResult = NMAL2510Query.getInstance().getTopTierParentOrganization(cMsg, i);
                if (ssmResult.isCodeNormal()) {
                    if (!cMsg.A.no(i).bizAreaOrgCd_P2.getValue().equals((String) ssmResult.getResultObject())) {
                        cMsg.A.no(i).orgNm_A2.setErrorInfo(1, NMAL2510Constant.NMAM0307E);
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0307E);
                        return false;
                    }
                } else {
                    cMsg.A.no(i).orgNm_A2.setErrorInfo(1, NMAL2510Constant.NMAM0307E);
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0307E);
                    return false;
                }
            }
            // 2016/04/13 CSA-QC#6960 Add End

            // 2016/03/01 CSA-QC#2766 Add Start
            // check Business Area of changed role are Sales or
            // Service
            if (!isSalesOrServiceRole) {
                if (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(i).bizAreaOrgCd_P2.getValue()) || BIZ_AREA_ORG.SERVICE.equals(cMsg.A.no(i).bizAreaOrgCd_P2.getValue())) {
                    isSalesOrServiceRole = true;
                    // 2017/06/23 CSA-QC#18925 Add Start
                    if (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(i).bizAreaOrgCd_P2.getValue())){
                        typeSales = true;
                        orgFuncRole = cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue();
                    } else {
                        typeSales = false;
                    }
                    // 2017/06/23 CSA-QC#18925 Add End
                }
            }
            // 2016/03/01 CSA-QC#2766 Add End

            // QC#3859
            // // 2016/02/29 CSA-QC#3859 Add Start
            // // Warning if More than 2 Managers were set to
            // organization
            // if
            // (!ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_H1.getValue())
            // &&
            // (!cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue().equals(cMsg.A.no(i).orgFuncRoleTpCd_AB.getValue())))
            // {
            // if (NMAL2510Query.getInstance().isManagerRole(cMsg, i))
            // {
            // ssmResult =
            // NMAL2510Query.getInstance().countManagerInSameOrganization(cMsg,
            // i);
            // if (ssmResult.isCodeNormal()) {
            // if ((Integer) ssmResult.getResultObject() > 0) {
            // cMsg.A.no(i).orgFuncRoleTpCd_P2.setErrorInfo(2,
            // NMAL2510Constant.NMAM8382W);
            // isManagerSetToOrg = true;
            // }
            // }
            // }
            // }

            // 2016/03/16 CSA-QC#5415 Add Start
            // 2016/06/20 CSA-QC#10424 Mod Start
            // if
            // (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(i).bizAreaOrgCd_P2.getValue()))
            // {
            if (isTargetTocReq(globalCompanyCode, cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue())) {
                // 2016/06/20 CSA-QC#10424 Mod End
                if (checkActiveDate(cMsg.A.no(i).effFromDt_A2.getValue(), cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                    if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) > 0) {
                        if (!ZYPCommonFunc.hasValue(orgRelnStartDate) || orgRelnStartDate.compareTo(cMsg.A.no(i).effFromDt_A2.getValue()) > 0) {
                            orgRelnStartDate = cMsg.A.no(i).effFromDt_A2.getValue();
                        }
                    } else {
                        if (!ZYPCommonFunc.hasValue(orgRelnStartDate) || orgRelnStartDate.compareTo(ZYPDateUtil.getSalesDate()) <= 0) {
                            orgRelnStartDate = ZYPDateUtil.getSalesDate();
                        }
                    }
                }
            }
            // 2016/03/16 CSA-QC#5415 Add End

            // 2016/12/06 CSA-QC#16362 Add Start
            for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
                if (i == idx) {
                    continue;
                }

                if (isCurrentOrFuturePeriod(cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                    if (cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue().equals(cMsg.A.no(idx).orgFuncRoleTpCd_P2.getValue())
                        && cMsg.A.no(i).orgNm_A2.getValue().equals(cMsg.A.no(idx).orgNm_A2.getValue())) {
                        if (isPeriodOverlap(cMsg.A.no(i).effFromDt_A2.getValue(), cMsg.A.no(i).effThruDt_A2.getValue()
                                , cMsg.A.no(idx).effFromDt_A2.getValue(), cMsg.A.no(idx).effThruDt_A2.getValue())){
                            cMsg.A.no(i).orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8661E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ROLE, NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME});
                            cMsg.A.no(i).orgNm_A2.setErrorInfo(1, NMAL2510Constant.NMAM8661E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ROLE, NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_NAME});
                            isError = true;
                        }
                    }
                }
            }
            // 2016/12/06 CSA-QC#16362 Add End
        }

        if (isError) {
            cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
            return false;
        }

        // 2016/03/16 CSA-QC#5415 Add Start
        if (ZYPCommonFunc.hasValue(orgRelnStartDate)) {
            // Revenue account must be assigned
            if (!checkActiveResourceRevenue(cMsg, orgRelnStartDate)) {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8422E);
                return false;
            }
        }
        // 2016/03/16 CSA-QC#5415 Add End

        // 2016/03/03 CSA-QC#3858 Add Start
        // QC#3509
        // for (Integer idx : errorIndex) {
        // // 2016/03/03 CSA-QC#4545 Add Start
        // cMsg.A.no(idx).orgFuncRoleTpCd_P2.setErrorInfo(1,
        // NMAL2510Constant.NMAM8237E);
        // // 2016/03/03 CSA-QC#4545 Add End
        // }
        // Check duplicate sales rep in Screen
        for (int j = 0; j < errorIndex.size(); j++) {
            for (int k = j + 1; k < errorIndex.size(); k++) {
                String effFromDt_01 = cMsg.A.no(errorIndex.get(j)).effFromDt_A2.getValue();
                String effThruDt_01 = cMsg.A.no(errorIndex.get(j)).effThruDt_A2.getValue();
                String effFromDt_02 = cMsg.A.no(errorIndex.get(k)).effFromDt_A2.getValue();
                String effThruDt_02 = cMsg.A.no(errorIndex.get(k)).effThruDt_A2.getValue();
                if (isPeriodOverlap(effFromDt_01, effThruDt_01, effFromDt_02, effThruDt_02)) {
                    cMsg.A.no(errorIndex.get(j)).orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8237E);
                    cMsg.A.no(errorIndex.get(k)).orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8237E);
                    isError = true;
                }
            }
        }

        if (isError) {
            cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
            return false;
        }

//        // Check duplicate sales rep in DB
//        for (int j = 0; j < errorIndex.size(); j++) {
//            NMAL2510_ACMsg curRow = cMsg.A.no(errorIndex.get(j));
//            if (isRoleChanged(curRow)) {
//                S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getMgrCntByOrg(cMsg, errorIndex.get(j), ZYPConstant.FLG_OFF_N);
//                if (ssmResult.isCodeNormal()) {
//                    List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmResult.getResultObject();
//                    for (Map<String, Object> map : listMap) {
//                        String tocCd = (String) map.get("TOC_CD");
//                        String effFromDt = (String) map.get("EFF_FROM_DT");
//                        String effThruDt = (String) map.get("EFF_THRU_DT");
//                        if (!curRow.tocCd_A2.getValue().equals(tocCd)) {
//                            if (isPeriodOverlap(curRow.effFromDt_A2.getValue(), curRow.effThruDt_A2.getValue(), effFromDt, effThruDt)) {
//                                curRow.orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8237E);
//                                isError = true;
//                            }
//                        }
//                    }
//                }
//            }
//        }
        if (isError) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8237E);
            return false;
        }
        // 2016/03/03 CSA-QC#3858 Add End

        // QC#3859 BEG
        // if (isManagerSetToOrg) {
        // cMsg.setMessageInfo(NMAL2510Constant.NMAM8382W);
        // cMsg.xxWrnSkipFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
        // return false;
        // }
        // // 2016/02/29 CSA-QC#3859 Add End

        // Create list only contain current/future manager role
        boolean isDupMgrErr = false;
        boolean isDupMgrWarn = false;
        List<NMAL2510_ACMsg> listCurFutMrg = new ArrayList<NMAL2510_ACMsg>();
        for (i = 0; i < cMsg.A.getValidCount(); i++) {
            if (isCurrentOrFuturePeriod(cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                if (NMAL2510Query.getInstance().isManagerRole(cMsg, i, ZYPConstant.FLG_ON_Y)) {
                    listCurFutMrg.add(cMsg.A.no(i));
                }
            }
        }
        for (i = 0; i < listCurFutMrg.size(); i++) {
            NMAL2510_ACMsg curRow = listCurFutMrg.get(i);

            // Get manager duplicate manager check control flag
            String dupMgrChkReqTpCd = "";
            if (ZYPCommonFunc.hasValue(curRow.bizAreaOrgCd_P2.getValue())) {
                BIZ_AREA_ORGTMsg tMsg = new BIZ_AREA_ORGTMsg();
                tMsg.glblCmpyCd.setValue(globalCompanyCode);
                // 2016/11/21 CSA-QC#16113 Mod Start
                tMsg.bizAreaOrgCd.setValue(curRow.bizAreaOrgCd_P2.getValue());
                // tMsg.bizAreaOrgCd.setValue(cMsg.A.no(i).bizAreaOrgCd_P2.getValue());
                // 2016/11/21 CSA-QC#16113 Mod End
                tMsg = (BIZ_AREA_ORGTMsg) S21CodeTableAccessor.findByKey(tMsg);
                if (tMsg != null) {
                    dupMgrChkReqTpCd = tMsg.dupMgrChkReqTpCd.getValue();
                }
            }

            if (NMAL2510Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd) || (NMAL2510Constant.DUP_MGR_CHK_WARN.equals(dupMgrChkReqTpCd) && !ZYPConstant.FLG_ON_Y.equals(cMsg.xxWrnSkipFlg_H1.getValue()))) {

//                // Check duplicate in screen
//                for (int j = i + 1; j < listCurFutMrg.size(); j++) {
//                    NMAL2510_ACMsg nextRow = listCurFutMrg.get(j);
//                    if (curRow.orgCd_A2.getValue().equals(nextRow.orgCd_A2.getValue())) {
//                        if (isPeriodOverlap(curRow.effFromDt_A2, curRow.effThruDt_A2, nextRow.effFromDt_A2, nextRow.effThruDt_A2)) {
//                            if (isRoleChanged(curRow) || isRoleChanged(nextRow)) {
//                                if (NMAL2510Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd)) {
//                                    isDupMgrErr = true;
//                                    curRow.orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8641E);
//                                    nextRow.orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8641E);
//                                } else {
//                                    isDupMgrWarn = true;
//                                    curRow.orgFuncRoleTpCd_P2.setErrorInfo(2, NMAL2510Constant.NMAM8382W);
//                                    nextRow.orgFuncRoleTpCd_P2.setErrorInfo(2, NMAL2510Constant.NMAM8382W);
//                                }
//                            }
//                        }
//                    }
//                }

                // Check duplicate manager in DB
                if (isRoleChanged(curRow)) {
                    S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getMgrCntByOrg(cMsg, i, ZYPConstant.FLG_ON_Y);
                    if (ssmResult.isCodeNormal()) {
                        List<Map<String, Object>> listMap = (List<Map<String, Object>>) ssmResult.getResultObject();
                        for (Map<String, Object> map : listMap) {
                            String tocCd = (String) map.get("TOC_CD");
                            String effFromDt = (String) map.get("EFF_FROM_DT");
                            String effThruDt = (String) map.get("EFF_THRU_DT");
                            if (!isTocInScrn(listCurFutMrg, tocCd)) {
                                if (isPeriodOverlap(curRow.effFromDt_A2.getValue(), curRow.effThruDt_A2.getValue(), effFromDt, effThruDt)) {
                                    if (NMAL2510Constant.DUP_MGR_CHK_ERR.equals(dupMgrChkReqTpCd)) {
                                        isDupMgrErr = true;
                                        curRow.orgFuncRoleTpCd_P2.setErrorInfo(1, NMAL2510Constant.NMAM8641E);
                                    } else {
                                        isDupMgrWarn = true;
                                        curRow.orgFuncRoleTpCd_P2.setErrorInfo(2, NMAL2510Constant.NMAM8382W);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        // Handler check result
        if (isDupMgrErr) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8641E);
            return false;
        }
        if (isDupMgrWarn) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8382W);
            cMsg.xxWrnSkipFlg_H1.setValue(ZYPConstant.FLG_ON_Y);
            return false;
        }
        // QC#3859 END

        boolean isAddrError = false;
        // 2016/03/01 CSA-QC#2766 Add Start
        if (isSalesOrServiceRole) {
            if (!ZYPCommonFunc.hasValue(cMsg.lineBizTpCd_P1)) {
                cMsg.lineBizTpCd_P1.setErrorInfo(1, NMAL2510Constant.NMAM8385E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_LINE_OF_BIZ, NMAL2510Constant.NAME_FOR_MESSAGE_SALES_OR_SERVICE });
                cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
                return false;
            }
            // 2017/06/23 CSA-QC#18925 Mod Start
            if (!typeSales && PSN_TP._3RD_PARTY_REP.equals(cMsg.psnTpCd_P1.getValue())) {
                // no Check
                isAddrError = false;
            } else {
                if (typeSales && isSalesRep(globalCompanyCode, orgFuncRole) || !typeSales) {
                    // 2016/07/27 CSA-QC#11480 Add Start
                    if (ZYPCommonFunc.hasValue(cMsg.ctryCd_H1)){
                        if (CTRY.UNITED_STATES_OF_AMERICA.equals(cMsg.ctryCd_H1.getValue())) {
                            if (!ZYPCommonFunc.hasValue(cMsg.ctyAddr_H1) || !ZYPCommonFunc.hasValue(cMsg.cntyNm_H1) || !ZYPCommonFunc.hasValue(cMsg.stCd_H1) || !ZYPCommonFunc.hasValue(cMsg.postCd_H1)) {
                                isAddrError = true;
                            }
                        } else {
                            if (!ZYPCommonFunc.hasValue(cMsg.ctyAddr_H1)) {
                                isAddrError = true;
                            }
                        }
                    } else {
                        isAddrError = true;
                    }
                }
            }
            if (isAddrError) {
                if (typeSales) {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8669E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_SALES_REP});
                } else {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8669E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_SERVICE});
                }
                return false;
            }
            // 2016/07/27 CSA-QC#11480 Add End
            // 2017/06/23 CSA-QC#18925 Mod End
        }
        // 2016/03/01 CSA-QC#2766 Add End

        if (!ZYPCommonFunc.hasValue(cMsg.dsInsdCtyLimitFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsInsdCtyLimitFlg_C1, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    private static boolean isRoleChanged(NMAL2510_ACMsg row) {
        if (!row.orgFuncRoleTpCd_P2.getValue().equals(row.orgFuncRoleTpCd_AB.getValue()) || !row.orgCd_A2.getValue().equals(row.orgCd_AB.getValue()) || !row.effFromDt_A2.getValue().equals(row.effFromDt_AB.getValue())
                || !row.effThruDt_A2.getValue().equals(row.effThruDt_AB.getValue())) {
            return true;
        }
        return false;
    }

    private static boolean isTocInScrn(List<NMAL2510_ACMsg> list, String tocCd) {
        for (NMAL2510_ACMsg row : list) {
            if (row.tocCd_A2.getValue().equals(tocCd)) {
                return true;
            }
        }
        return false;
    }

    /**
     * setBusinessAreaProtection
     * @param cMsg NMAL2510CMsg
     * @param i int
     */
    public static void setBusinessAreaProtection(NMAL2510CMsg cMsg, int i) {
        // 2016/03/04 CSA-QC#4654 Add Start
        if (NMAL2510Query.getInstance().isOrgCurrentOrFuture(cMsg, i)) {
            cMsg.A.no(i).actvFlg_BA.setValue(ZYPConstant.FLG_ON_Y);
        } else {
            cMsg.A.no(i).actvFlg_BA.clear();
        }
        // 2016/03/04 CSA-QC#4654 Add End
    }

    /**
     * master check
     * @param cMsg NMAL2500CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    // 2016/06/29 CSA-QC#11087 Mod Start
    public static boolean checkInputTerritoryForSubmit(NMAL2510CMsg cMsg, String globalCompanyCode, boolean isChangePsnEndDt) {
        // 2016/06/29 CSA-QC#11087 Mod End

        boolean hasOrgFuncRoleSales = false;
        String orgFuncRole = null;
        for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
            if (checkActiveDate(cMsg.A.no(idx).effFromDt_A2.getValue(), cMsg.A.no(idx).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                // 2016/03/22 CSA-QC#5658 Mod Start
                // if (isOrgFuncRoleSales(cMsg, idx)) {
                // QC#3509
                // if
                // (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(i).bizAreaOrgCd_P2.getValue()))
                // {
                // 2016/12/06 CSA-QC#15140-4 Mod Start
                if (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(idx).bizAreaOrgCd_P2.getValue())) {
                    // 2016/03/22 CSA-QC#5658 Mod mnd
                    hasOrgFuncRoleSales = true;
                    if (NMAL2510Query.getInstance().isManagerRole(cMsg, idx, ZYPConstant.FLG_OFF_N)) {
                        orgFuncRole = cMsg.A.no(idx).orgFuncRoleTpCd_P2.getValue();
                        break;
                    }

                }
                orgFuncRole = cMsg.A.no(idx).orgFuncRoleTpCd_P2.getValue();
                // 2016/12/06 CSA-QC#15140-4 Mod End
            }
        }

        // 2019/05/28 QC#50379 Add Start
        String hierarchyOrgFuncRole = orgFuncRole;
        // 2019/05/28 QC#50379 Add End

        int i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            // 2017/09/29 CSA-QC#21361 Add Start
            if (isChangePsnEndDt) {
                if (cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effFromDt_B2.getValue()) < 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effThruDt_B2, cMsg.B.no(i).effFromDt_B2);
                } else if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B2.getValue()) || cMsg.effThruDt_H1.getValue().compareTo(cMsg.B.no(i).effThruDt_B2.getValue()) <= 0) {
                    ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effThruDt_B2, cMsg.effThruDt_H1);
                }
            }
            // 2017/09/29 CSA-QC#21361 Add End
            
            if (ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B2)) {
                // Assign from date > Assign start date then error
                if (cMsg.B.no(i).effFromDt_B2.getValue().compareTo(cMsg.B.no(i).effThruDt_B2.getValue()) > 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.B.no(i).effFromDt_B2.setErrorInfo(1, NMAL2510Constant.NMAM8239E);
                    cMsg.B.no(i).effThruDt_B2.setErrorInfo(1, NMAL2510Constant.NMAM8239E);
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8239E);
                    return false;
                }

                // 2016/02/03 CSA-QC#1930 Delete Start
                // Assign end date < today then error
                // if
                // (cMsg.B.no(i).effThruDt_B2.getValue().compareTo(ZYPDateUtil.getSalesDate())
                // < 0) {
                // cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new
                // String[] {NMAL2510Constant.RESOURCE_ASSIGN +
                // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
                // NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                // return false;
                // }
                // 2016/02/03 CSA-QC#1930 Delete End
            }

            // Assign end date > Territory end date then error
            // 2016/09/05 CSA-QC#4099 Delete Start
            // if (compareDate(cMsg.B.no(i).effThruDt_B2.getValue(),
            // cMsg.B.no(i).effThruDt_B3.getValue())) {
            // // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.B.no(i).effThruDt_B2.setErrorInfo(1,
            // NMAL2510Constant.NMAM0043E, new String[]
            // {NMAL2510Constant.RESOURCE_ASSIGN +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
            // NMAL2510Constant.TERRITORY +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
            // // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0043E, new
            // String[] {NMAL2510Constant.RESOURCE_ASSIGN +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
            // NMAL2510Constant.TERRITORY +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
            // return false;
            // }
            // 2016/09/05 CSA-QC#4099 Delete End

            // Assign start date < Territory start date then error
            if (cMsg.B.no(i).effFromDt_B2.getValue().compareTo(cMsg.B.no(i).effFromDt_B3.getValue()) < 0) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.B.no(i).effFromDt_B2.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.RESOURCE_ASSIGN + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                        NMAL2510Constant.TERRITORY + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                cMsg.B.no(i).effFromDt_B3.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.RESOURCE_ASSIGN + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                        NMAL2510Constant.TERRITORY + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.RESOURCE_ASSIGN + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT, NMAL2510Constant.TERRITORY + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                return false;
            }

            // 2017/09/29 CSA-QC#21361 Add Start
            // 2018/03/29 S21_NA#23171 Del Start
            // if (!checkActiveDate(cMsg.B.no(i).effFromDt_B2.getValue(), cMsg.B.no(i).effThruDt_B2.getValue(), ZYPDateUtil.getSalesDate())) {
            //     continue;
            // }
            // 2018/03/29 S21_NA#23171 Del End
            // 2017/09/29 CSA-QC#21361 Add End

            S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().checkBusinessAreaSalesForTerritory(cMsg, i);
            // 2016/03/16 CSA-QC#5415 Delete Start
            // Integer resCnt = (Integer) ssmResult.getResultObject();
            // Only Check if business area is Sales.
            // if (resCnt.compareTo(0) > 0) {
            // // Revenue account must be assigned
            // if (!checkActiveResourceRevenue(cMsg)) {
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM8238E);
            // return false;
            // }
            // }
            // 2016/03/16 CSA-QC#5415 Delete End

            // For Manual Input pressed Add button
            ssmResult = NMAL2510Query.getInstance().getTerritoryDataforManualInput(cMsg, i);

            if (!ssmResult.isCodeNormal()) {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.B.no(i).orgNm_B2.setErrorInfo(1, NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TERRITORY_NAME });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TERRITORY_NAME });
                return false;
            // 2020/01/10 S21_NA#55356 Mod Start
//            } else {
            }
            // 2020/01/10 S21_NA#55356 Mod End
            List resultList = (List) ssmResult.getResultObject();
            Map map = (Map) resultList.get(0);

            // 2018/03/29 S21_NA#23171 Add Start
            // 2020/01/10 S21_NA#55356 Del Start
            // if (!checkActiveDate((String)
            // map.get(NMAL2510Constant.EFF_FROM_DT_DBCOLUMN),
            // (String)
            // map.get(NMAL2510Constant.EFF_THRU_DT_DBCOLUMN),
            // ZYPDateUtil.getSalesDate())) {
            // continue;
            // }
            // 2020/01/10 S21_NA#55356 Del End
            // 2018/03/29 S21_NA#23171 Add End

            if (ZYPCommonFunc.hasValue((String) map.get(NMAL2510Constant.ORG_CD_DBCOLUMN))) {
                cMsg.B.no(i).orgCd_B2.setValue((String) map.get(NMAL2510Constant.ORG_CD_DBCOLUMN));
            } else {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.B.no(i).orgNm_B2.setErrorInfo(1, NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TERRITORY_NAME });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TERRITORY_NAME });
                return false;
            }

            if (ZYPCommonFunc.hasValue((String) map.get(NMAL2510Constant.ORG_STRU_TP_CD_DBCOLUMN))) {
                cMsg.B.no(i).orgStruTpCd_B2.setValue((String) map.get(NMAL2510Constant.ORG_STRU_TP_CD_DBCOLUMN));
            } else {
                // 2016/03/03 CSA-QC#4545 Add Start
                cMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TERRITORY_NAME });
                // 2016/03/03 CSA-QC#4545 Add End
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_ORGANIZATION_STRUCTURE_TYPE });
                return false;
            }
            // }

            // 2019/05/28 QC#50379 Add Start
            if (!ZYPCommonFunc.hasValue(orgFuncRole) && ZYPDateUtil.compare(cMsg.B.no(i).effThruDt_B2.getValue(), ZYPDateUtil.getSalesDate()) < 0) {
                // Set default because not used anymore "DS_ORG_RESRC_RELN.ORG_FUNC_ROLE_TP_CD".
                orgFuncRole = "ADJSP";
            }
            // 2019/05/28 QC#50379 Add End

            // 2016/03/18 QC-CSA#5658 Add Start
            if (ZYPCommonFunc.hasValue(orgFuncRole)) {
                cMsg.B.no(i).orgFuncRoleTpCd_B2.setValue(orgFuncRole);
            } else {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM8282E);
                // 2019/05/28 QC#50379 Add Start
                return false;
                // 2019/05/28 QC#50379 Add End
            }
            // 2016/03/18 QC-CSA#5658 Add End

            // 2020/01/10 S21_NA#55356 Add Start
            if (!checkActiveDate((String) map.get(NMAL2510Constant.EFF_FROM_DT_DBCOLUMN), (String) map.get(NMAL2510Constant.EFF_THRU_DT_DBCOLUMN), ZYPDateUtil.getSalesDate())) {
                continue;
            }
            // 2020/01/10 S21_NA#55356 Add End

            // 2016/03/18 QC-CSA#5658 Delete Strt
            // if (ZYPCommonFunc.hasValue(cMsg.psnCd_HB)) {
            // For Manual Input pressed Add button
            // ssmResult =
            // NMAL2510Query.getInstance().getOrganizationFunctionRoleTypeDataforManualInput(cMsg,
            // i);
            // if (!ssmResult.isCodeNormal()) {
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new
            // String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TOC_CODE
            // });
            // return false;
            // } else {
            //    
            // List resultList = (List) ssmResult.getResultObject();
            // Map map = (Map) resultList.get(0);
            //    
            // if (ZYPCommonFunc.hasValue((String)
            // map.get(NMAL2510Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN)))
            // {
            // cMsg.B.no(i).orgFuncRoleTpCd_B2.setValue((String)
            // map.get(NMAL2510Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN));
            // } else {
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new
            // String[] {NMAL2510Constant.NAME_FOR_MESSAGE_TOC_CODE
            // });
            // return false;
            // }
            // }
            // }
            // 2016/03/18 QC-CSA#5658 Delete End

            // Check is active territory sales
            if (!hasOrgFuncRoleSales) {
                if (checkActiveDate(cMsg.B.no(i).effFromDt_B2.getValue(), cMsg.B.no(i).effThruDt_B2.getValue(), ZYPDateUtil.getSalesDate())) {
                    if (BIZ_AREA_ORG.SALES_2.equals(cMsg.B.no(i).bizAreaOrgCd_P3.getValue())) {
                        cMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM8398E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_SALES });
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM8398E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_SALES });
                        return false;
                    }
                }
            }

            // error if multiple salesrep are set to territory
            // 2016/04/04 CSA-QC#6003 Add Start
            if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).effThruDt_B2) || cMsg.B.no(i).effThruDt_B2.getValue().compareTo(ZYPDateUtil.getSalesDate()) > 0) {
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).acctTeamRoleTpCd_P3)) {
                    ssmResult = NMAL2510Query.getInstance().countSalesRepInTerritory(cMsg, i);
                    Integer resCnt = (Integer) ssmResult.getResultObject();
                    if (resCnt > 0) {
                        cMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM8423E);
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM8423E);
                        return false;
                    }
                }
            }
            // 2016/04/04 CSA-QC#6003 Add End

            // 2016/06/29 CSA-QC#11087 Add Start
            if (checkActiveDate(cMsg.B.no(i).effFromDt_B2.getValue(), cMsg.B.no(i).effThruDt_B2.getValue(), ZYPDateUtil.getSalesDate())) {
                if (!ZYPCommonFunc.hasValue(cMsg.B.no(i).acctTeamRoleTpCd_P3) && !isSalesRep(globalCompanyCode, orgFuncRole)) {
                    cMsg.B.no(i).xxChkBox_B2.setErrorInfo(1, NMAL2510Constant.NMAM8282E);
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM8282E);
                    return false;
                }
            }
            // 2016/06/29 CSA-QC#11087 Add End

            // 2019/05/28 QC#50379 Add Start
            orgFuncRole = hierarchyOrgFuncRole;
            // 2019/05/28 QC#50379 Add End
        }

        if (!ZYPCommonFunc.hasValue(cMsg.dsInsdCtyLimitFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsInsdCtyLimitFlg_C1, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    /**
     * getDefaultRevenue
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return ret boolean
     */
    public static boolean getDefaultRevenue(NMAL2510CMsg cMsg) {

        boolean ret = true;

        S21SsmEZDResult ssmResult;
        ssmResult = NMAL2510Query.getInstance().getDefaultRevenue(cMsg);
        if (ssmResult.isCodeNormal()) {
            List resultList = (List) ssmResult.getResultObject();

            Map map = (Map) resultList.get(0);

            if (map.get(NMAL2510Constant.COA_BR_CD_DB_COLUMN) != null) {
                cMsg.coaBrCd_H1.setValue((String) map.get(NMAL2510Constant.COA_BR_CD_DB_COLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2510Constant.COA_EXTN_CD_DB_COLUMN) != null) {
                cMsg.coaExtnCd_H1.setValue((String) map.get(NMAL2510Constant.COA_EXTN_CD_DB_COLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2510Constant.COA_CC_CD_DB_COLUMN) != null) {
                cMsg.coaCcCd_H1.setValue((String) map.get(NMAL2510Constant.COA_CC_CD_DB_COLUMN));
            } else {
                ret = false;
            }

            if (map.get(NMAL2510Constant.COA_CMPY_CD_DB_COLUMN) != null) {
                cMsg.coaCmpyCd_H1.setValue((String) map.get(NMAL2510Constant.COA_CMPY_CD_DB_COLUMN));
            } else {
                ret = false;
            }
        } else {
            ret = false;
        }

        return ret;
    }

    /**
     * master check
     * @param cMsg NMAL2500CMsg
     * @param globalCompanyCode String
     * @return boolean
     */
    public static boolean checkInputRevenueForSubmit(NMAL2510CMsg cMsg, String globalCompanyCode, boolean isChangePsnEndDt) {

        int i = 0;
        boolean isError = false;

        // 2016/10/12 CSA-QC#4096 Add Start
        if (ZYPCommonFunc.hasValue(cMsg.geoCd_C1) && !cMsg.geoCd_C1.getValue().equals(cMsg.geoCd_CB.getValue())) {
            if (!checkExistsGeoCodeByVertex(cMsg)) {
                return false;
            }
        }
        // 2016/10/12 CSA-QC#4096 Add End

        // 2016/11/09 CSA-QC#15341 Add Start
        DEF_DPLY_COA_INFOTMsg defDplyCoaInfoMsg = getDefDplyCoaInfo(globalCompanyCode, NMAL2510Constant.BIZ_ID);
        if (defDplyCoaInfoMsg == null) {
            cMsg.setMessageInfo(NMAL2510Constant.NZZM0000E);
        }
        // 2016/11/09 CSA-QC#15341 Add End

        for (; i < cMsg.C.getValidCount(); i++) {
            // 2017/09/29 CSA-QC#21361 Add Start
            if (isChangePsnEndDt) {
                if (compareDate(ZYPDateUtil.getSalesDate(), cMsg.effThruDt_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).effThruDt_C2, ZYPDateUtil.getSalesDate());
                } else {
                    ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).effThruDt_C2, cMsg.effThruDt_H1);
                }
            }
            // 2017/09/29 CSA-QC#21361 Add End

            if (!ZYPCommonFunc.hasValue(cMsg.C.no(i).xxChkBox_C3)) {
                cMsg.C.no(i).xxChkBox_C3.setValue(ZYPConstant.FLG_OFF_N);
            }

            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C2)) {
                // Revenue from date > Revenue start date then error
                if (cMsg.C.no(i).effFromDt_C2.getValue().compareTo(cMsg.C.no(i).effThruDt_C2.getValue()) > 0) {
                    // 2016/03/03 CSA-QC#4545 Add Start
                    cMsg.C.no(i).effFromDt_C2.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                    cMsg.C.no(i).effThruDt_C2.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                    // 2016/03/03 CSA-QC#4545 Add End
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
                    return false;
                }
            }

            // 2016/09/01 CSA-QC#11604 Add Start
            if (compareDate(ZYPDateUtil.getSalesDate(), cMsg.effThruDt_H1.getValue())) {
                continue;
            }
            // 2016/09/01 CSA-QC#11604 Add End

            // 2016/09/01 CSA-QC#11604 Delete Start
            // Revenue end date > Employee end date then error
            // if (compareDate(cMsg.C.no(i).effThruDt_C2.getValue(),
            // cMsg.effThruDt_H1.getValue())) {
            // // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.C.no(i).effThruDt_C2.setErrorInfo(1,
            // NMAL2510Constant.NMAM0043E, new String[]
            // {NMAL2510Constant.REVENUE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
            // NMAL2510Constant.EMPLOYEE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
            // // 2016/03/03 CSA-QC#4545 Add End
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0043E, new
            // String[] {NMAL2510Constant.REVENUE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT,
            // NMAL2510Constant.EMPLOYEE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT });
            // return false;
            // }
            // 2016/09/01 CSA-QC#11604 Delete End

            // Revenue end date < today then error
            if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C2) && !cMsg.C.no(i).effThruDt_C2.getValue().equals(cMsg.C.no(i).effThruDt_CB.getValue())) {
                if (compareDate(ZYPDateUtil.getSalesDate(), cMsg.C.no(i).effThruDt_C2.getValue())) {
                    // 2016/10/04 CSA-QC#4099 Add Start
                    if (!isExitsFutureFromDate(cMsg, cMsg.C.no(i).effThruDt_C2.getValue(), i)) {
                        // 2016/03/03 CSA-QC#4545 Add Start
                        cMsg.C.no(i).effThruDt_C2.setErrorInfo(1, NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.RESOURCE_ASSIGN + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                        // 2016/03/03 CSA-QC#4545 Add End
                        cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new String[] {NMAL2510Constant.RESOURCE_ASSIGN + NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT, NMAL2510Constant.NAME_FOR_MESSAGE_CURRENT_DT });
                        return false;
                    }
                    // 2016/10/04 CSA-QC#4099 Add End
                }
            }

            // 2016/03/18 CSA-QC#5415 Delete Start
            // // Revenue start date < Employee start date then error
            // if
            // (cMsg.C.no(i).effFromDt_C2.getValue().compareTo(cMsg.effFromDt_H1.getValue())
            // < 0) {
            // // 2016/03/03 CSA-QC#4545 Add Start
            // cMsg.C.no(i).effFromDt_C2.setErrorInfo(1,
            // NMAL2510Constant.NMAM0044E, new String[]
            // {NMAL2510Constant.REVENUE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
            // NMAL2510Constant.EMPLOYEE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
            // // 2016/03/03 CSA-QC#4545 Add End
            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E, new
            // String[] {NMAL2510Constant.REVENUE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
            // NMAL2510Constant.EMPLOYEE +
            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT });
            // return false;
            // }
            // 2016/03/18 CSA-QC#5415 Delete End

            // Multiple Active Revenue is not allowed
            for (int j = 0; j < cMsg.C.getValidCount(); j++) {
                if (cMsg.C.no(i).revAcctTpCd_P1.getValue().equals(cMsg.C.no(j).revAcctTpCd_P1.getValue())) {
                    // 2016/08/23 CSA-QC#13277 Mod Start
                    // if
                    // (cMsg.C.no(i).effFromDt_C2.getValue().equals(cMsg.C.no(j).effFromDt_C2.getValue()))
                    // {
                    // Mod Start 2017/10/17 QC#21753
                    //if (isDateInPeriod(cMsg.C.no(i).effFromDt_C2.getValue(), cMsg.C.no(j).effFromDt_C2.getValue(), cMsg.C.no(j).effThruDt_C2.getValue())
                    //        || isDateInPeriod(cMsg.C.no(i).effThruDt_C2.getValue(), cMsg.C.no(j).effFromDt_C2.getValue(), cMsg.C.no(j).effThruDt_C2.getValue())) {
                    if (isPeriodOverlap(cMsg.C.no(i).effFromDt_C2, cMsg.C.no(i).effThruDt_C2, cMsg.C.no(j).effFromDt_C2, cMsg.C.no(j).effThruDt_C2)) {
                        // Mod End 2017/10/17 QC#21753
                        // 2016/08/23 CSA-QC#13277 Mod End
                        if (i != j) {
                            // 2016/03/03 CSA-QC#4545 Add Start
                            cMsg.C.no(i).xxChkBox_C2.setErrorInfo(1, NMAL2510Constant.NMAM8244E);
                            cMsg.C.no(j).xxChkBox_C2.setErrorInfo(1, NMAL2510Constant.NMAM8244E);
                            // 2016/03/03 CSA-QC#4545 Add End
                            cMsg.setMessageInfo(NMAL2510Constant.NMAM8244E);
                            return false;
                        }
                    } else if (checkActiveDate(cMsg.C.no(i).effFromDt_C2.getValue(), cMsg.C.no(i).effThruDt_C2.getValue(), ZYPDateUtil.getSalesDate())) {
                        if (checkActiveDate(cMsg.C.no(j).effFromDt_C2.getValue(), cMsg.C.no(j).effThruDt_C2.getValue(), ZYPDateUtil.getSalesDate())) {
                            if (i != j) {
                                // 2016/03/03 CSA-QC#4545 Add Start
                                cMsg.C.no(j).xxChkBox_C2.setErrorInfo(1, NMAL2510Constant.NMAM8293E);
                                cMsg.C.no(j).xxChkBox_C2.setErrorInfo(1, NMAL2510Constant.NMAM8293E);
                                // 2016/03/03 CSA-QC#4545 Add End
                                cMsg.setMessageInfo(NMAL2510Constant.NMAM8293E);
                                return false;
                            }
                            // 2016/08/23 CSA-QC#13277 Delete Start
                            // } else if
                            // (!ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C2))
                            // {
                            // // 2016/03/03 CSA-QC#4545 Add Start
                            // cMsg.C.no(j).effThruDt_C2.setErrorInfo(1,
                            // NMAL2510Constant.NMAM0014E, new
                            // String[] {NMAL2510Constant.CURRENT +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT
                            // });
                            // // 2016/03/03 CSA-QC#4545 Add End
                            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E,
                            // new String[] {NMAL2510Constant.CURRENT
                            // +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT
                            // });
                            // return false;
                            // } else if
                            // (compareDate(cMsg.C.no(i).effThruDt_C2.getValue(),
                            // cMsg.C.no(j).effFromDt_C2.getValue()))
                            // {
                            // // 2016/03/03 CSA-QC#4545 Add Start
                            // cMsg.C.no(j).effThruDt_C2.setErrorInfo(1,
                            // NMAL2510Constant.NMAM0044E, new
                            // String[] {NMAL2510Constant.NEW +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                            // NMAL2510Constant.CURRENT +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT
                            // });
                            // cMsg.C.no(j).effFromDt_C2.setErrorInfo(1,
                            // NMAL2510Constant.NMAM0044E, new
                            // String[] {NMAL2510Constant.NEW +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                            // NMAL2510Constant.CURRENT +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT
                            // });
                            // // 2016/03/03 CSA-QC#4545 Add End
                            // cMsg.setMessageInfo(NMAL2510Constant.NMAM0044E,
                            // new String[] {NMAL2510Constant.NEW +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_FROM_DT,
                            // NMAL2510Constant.CURRENT +
                            // NMAL2510Constant.NAME_FOR_MESSAGE_EFF_THRU_DT
                            // });
                            // return false;
                            // 2016/08/23 CSA-QC#13277 Delete End
                        }
                    }
                }
            }

            // 2019/11/29 QC#54234 Add Start
            if (PSN_TP._3RD_PARTY_REP.equals(cMsg.psnTpCd_P1.getValue())) {
                if (COA_EXTN.DEFAULT.equals(cMsg.C.no(i).coaExtnCd_C2.getValue())) {
                    cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, NMAL2510Constant.NMAM8682E, new String[] {"000(DEFAULT)", "Type", "3rd Party Rep"});
                    cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
                }
            }
            // 2019/11/29 QC#54234 Add End

            if (checkActiveDate(cMsg.C.no(i).effFromDt_C2.getValue(), cMsg.C.no(i).effThruDt_C2.getValue(), ZYPDateUtil.getSalesDate())) {
                // GL Code Combination Check API
                if (ZYPConstant.FLG_ON_Y.equals(defDplyCoaInfoMsg.cmbnApiCallFlg.getValue())) { // 2016/11/09 CSA-QC#15341 Add
                    NFZC102001 afzc102001 = new NFZC102001();
                    NFZC102001PMsg paramMsg = new NFZC102001PMsg();

                    paramMsg.glblCmpyCd.setValue(globalCompanyCode);
                    paramMsg.xxMstChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                    paramMsg.xxGlCmbnChkFlg.setValue(ZYPConstant.FLG_ON_Y);
                    paramMsg.xxArcsApiChkFlg.setValue("");
                    paramMsg.coaCmpyCd.setValue(cMsg.C.no(i).coaCmpyCd_C2.getValue());
                    paramMsg.coaAfflCd.setValue(defDplyCoaInfoMsg.coaAfflCd.getValue()); // 2016/11/09 CSA-QC#15341 Mod
                    paramMsg.coaBrCd.setValue(cMsg.C.no(i).coaBrCd_C2.getValue());
                    paramMsg.coaCcCd.setValue(cMsg.C.no(i).coaCcCd_C2.getValue());
                    paramMsg.coaAcctCd.setValue(defDplyCoaInfoMsg.coaAcctCd.getValue()); // 2016/11/09 CSA-QC#15341 Mod
                    paramMsg.coaProdCd.setValue(defDplyCoaInfoMsg.coaProdCd.getValue()); // 2016/11/09 CSA-QC#15341 Mod
                    paramMsg.coaChCd.setValue(defDplyCoaInfoMsg.coaChCd.getValue()); // 2016/11/09 CSA-QC#15341 Mod
                    paramMsg.coaProjCd.setValue(defDplyCoaInfoMsg.coaProjCd.getValue()); // 2016/11/09 CSA-QC#15341 Mod
                    paramMsg.coaExtnCd.setValue(cMsg.C.no(i).coaExtnCd_C2.getValue());
                    paramMsg.resrcObjNm.setValue(NMAL2510Constant.BIZ_ID);
                    afzc102001.execute(paramMsg, ONBATCH_TYPE.ONLINE);

                    // START 2018/01/23 J.Kim [QC#23374,MOD]
                    //List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(paramMsg);
                    //for (int j = 0; j < msgList.size(); j++) {
                    //    S21ApiMessage msg = msgList.get(j);
                    //    String msgId = msg.getXxMsgid();
                    //    String[] msgPrms = msg.getXxMsgPrmArray();
                    //
                    //    // 2015/02/29 CSA-QC#1946 Mod Start
                    //    if (msgPrms != null && msgPrms.length > 0) {
                    //        if (NMAL2510Constant.MSG_COA_CMPY_CD.equals(msgPrms[0])) {
                    //            cMsg.C.no(i).coaCmpyCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_CMPY });
                    //            isError = true;
                    //        } else if (NMAL2510Constant.MSG_COA_BR_CD.equals(msgPrms[0])) {
                    //            cMsg.C.no(i).coaBrCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_BR });
                    //            isError = true;
                    //        } else if (NMAL2510Constant.MSG_COA_CC_CD.equals(msgPrms[0])) {
                    //            cMsg.C.no(i).coaCcCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_CC });
                    //            isError = true;
                    //        } else if (NMAL2510Constant.MSG_COA_EXTN_CD.equals(msgPrms[0])) {
                    //            cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_BU });
                    //            isError = true;
                    //        } else {
                    //            cMsg.C.no(i).xxChkBox_C2.setErrorInfo(1, msgId, new String[] {msgPrms[0] });
                    //            isError = true;
                    //        }
                    //    }
                    //}

                    for (int j = 0; j < paramMsg.xxMsgIdList.getValidCount(); j++) {

                        NFZC102001_xxMsgIdListPMsg msg = paramMsg.xxMsgIdList.no(j);
                        String msgId = msg.xxMsgId.getValue();
                        String msgCoaInfo = msg.xxCoaTpCd.getValue();

                        if (NMAL2510Constant.MSG_COA_CMPY_CD.equals(msgCoaInfo)) {
                            cMsg.C.no(i).coaCmpyCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_CMPY });
                            isError = true;
                        } else if (NMAL2510Constant.MSG_COA_BR_CD.equals(msgCoaInfo)) {
                            cMsg.C.no(i).coaBrCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_BR });
                            isError = true;
                        } else if (NMAL2510Constant.MSG_COA_CC_CD.equals(msgCoaInfo)) {
                            cMsg.C.no(i).coaCcCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_CC });
                            isError = true;
                        } else if (NMAL2510Constant.MSG_COA_EXTN_CD.equals(msgCoaInfo)) {
                            cMsg.C.no(i).coaExtnCd_C2.setErrorInfo(1, msgId, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_COA_BU });
                            isError = true;
                        } else {
                            cMsg.C.no(i).xxChkBox_C2.setErrorInfo(1, msgId, new String[] {msgCoaInfo });
                            isError = true;
                        }
                    }
                    // END 2018/01/23 J.Kim [QC#23374,MOD]
                }
            }
        }

        if (isError) {
            if (!ZYPCommonFunc.hasValue(cMsg.getMessageCode())) {
                cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
            }
            return false;
        }
        // 2015/02/29 CSA-QC#1946 Mod End

        // 2016/07/27 CSA-QC#10480 Delete Start
        // // 2015/02/02 CSA-QC#2867 Add Start
        // if (!ZYPCommonFunc.hasValue(cMsg.geoCd_C1)) {
        // for (int idx = 0; idx < cMsg.A.getValidCount(); idx++) {
        // if
        // (BIZ_AREA_ORG.SALES.equals(cMsg.A.no(idx).bizAreaOrgCd_P2.getValue())
        // ||
        // BIZ_AREA_ORG.SERVICE.equals(cMsg.A.no(idx).bizAreaOrgCd_P2.getValue()))
        // {
        // if (checkActiveDate(cMsg.A.no(i).effFromDt_A2.getValue(),
        // cMsg.A.no(i).effThruDt_A2.getValue(),
        // ZYPDateUtil.getSalesDate())) {
        // cMsg.geoCd_C1.setErrorInfo(1, NMAL2510Constant.NMAM8338E);
        // cMsg.setMessageInfo(NMAL2510Constant.ZZM9037E);
        // return false;
        // }
        // }
        // }
        // }
        // // 2015/02/02 CSA-QC#2867 Add End
        // 2016/07/27 CSA-QC#10480 Delete End

        if (!ZYPCommonFunc.hasValue(cMsg.dsInsdCtyLimitFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsInsdCtyLimitFlg_C1, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    /**
     * master check
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    public static boolean checkInputMiscForSubmit(NMAL2510CMsg cMsg) {

        if (!checkContract(cMsg)) {
            return false;
        }

        if (!ZYPCommonFunc.hasValue(cMsg.dsInsdCtyLimitFlg_C1)) {
            ZYPEZDItemValueSetter.setValue(cMsg.dsInsdCtyLimitFlg_C1, ZYPConstant.FLG_OFF_N);
        }

        return true;
    }

    /**
     * checkExistsGeoCodeByVertex
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    public static boolean checkExistsGeoCodeByVertex(NMAL2510CMsg cMsg) {
        // 2016/10/12 CSA-QC#4096 Add Start
        NMXC107001PMsg pMsg = new NMXC107001PMsg();
        ZYPEZDItemValueSetter.setValue(pMsg.xxModeCd, NMXC107001.CHECK_EXISTS_GEO_CODE_MODE);
        ZYPEZDItemValueSetter.setValue(pMsg.geoCd, cMsg.geoCd_C1);

        // Get Geo Code
        NMXC107001 api = new NMXC107001();
        api.execute(pMsg, ONBATCH_TYPE.ONLINE);

        if (!S21ApiUtil.getXxMsgIdList(pMsg).isEmpty()) {
            String msgId = pMsg.xxMsgIdList.no(0).xxMsgId.getValue();
            cMsg.setMessageInfo(msgId);
            return false;
        }

        return true;
        // 2016/10/12 CSA-QC#4096 Add End
    }

    /**
     * checkChangeActiveOrgCd
     * @param cMsg NMAL2500CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangeActiveOrgCd(NMAL2510CMsg cMsg, int i) {
        // 2016/02/24 CSA-QC#4627 Add Start
        if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2) || cMsg.A.no(i).effThruDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).orgCd_AB.getValue(), cMsg.A.no(i).orgCd_A2.getValue())) {
                return true;
            }
            // Del Start 2019/02/13 QC#29668
            //return true;
            // Del End 2019/02/13 QC#29668
        }
        return false;
        // 2016/02/24 CSA-QC#4627 Add End
    }

    // Add Start 2019/02/13 QC#29668
    /**
     * isActiveOrg
     * @param cMsg NMAL2500CMsg
     * @param i int
     * @return boolean
     */
    public static boolean isActiveOrg(NMAL2510CMsg cMsg, int i) {
        if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && //
                (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2) || cMsg.A.no(i).effThruDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            return true;
        }
        return false;
    }
    // Add End 2019/02/13 QC#29668

    /**
     * checkChangeActiveOrgFuncRoleType
     * @param cMsg NMAL2500CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangeActiveOrgFuncRoleType(NMAL2510CMsg cMsg, int i) {
        // 2016/02/24 CSA-QC#4627 Add Start
        if (cMsg.A.no(i).effFromDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A2) || cMsg.A.no(i).effThruDt_A2.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
            if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).orgFuncRoleTpCd_AB.getValue(), cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue())) {
                return true;
            }
        }
        return false;
        // 2016/02/24 CSA-QC#4627 Add End
    }

    /**
     * checkUpdateActivePersonRevenue
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    public static boolean checkUpdateActivePersonRevenue(NMAL2510CMsg cMsg) {
        // 2016/02/24 CSA-QC#4627 Add Start
        if (isNotEquals(cMsg.psnFirstNm_H1.getValue(), cMsg.psnFirstNm_HB.getValue()) || isNotEquals(cMsg.psnLastNm_H1.getValue(), cMsg.psnLastNm_HB.getValue())) {
            return true;
        }

        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            if (NMAL2510Constant.REV_ACCT_TP_CD_REVENUE.equals(cMsg.C.no(i).revAcctTpCd_P1.getValue())) {
                if (cMsg.C.no(i).effFromDt_C2.getValue().compareTo(ZYPDateUtil.getSalesDate()) <= 0 && (!ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C2) || cMsg.C.no(i).effThruDt_C2.getValue().compareTo(ZYPDateUtil.getSalesDate()) >= 0)) {
                    if (NMAL2510CommonLogic.isNotEquals(cMsg.C.no(i).coaExtnCd_CB.getValue(), cMsg.C.no(i).coaExtnCd_C2.getValue()) || NMAL2510CommonLogic.isNotEquals(cMsg.C.no(i).coaBrCd_CB.getValue(), cMsg.C.no(i).coaBrCd_C2.getValue())
                            || NMAL2510CommonLogic.isNotEquals(cMsg.C.no(i).coaCcCd_CB.getValue(), cMsg.C.no(i).coaCcCd_C2.getValue())
                            || NMAL2510CommonLogic.isNotEquals(cMsg.C.no(i).coaCmpyCd_CB.getValue(), cMsg.C.no(i).coaCmpyCd_C2.getValue())) {
                        return true;
                    }
                }
            }
        }
        return false;
        // 2016/02/24 CSA-QC#4627 Add End
    }

    /**
     * checkBusinessAreaRoll
     * @param bizAreaOrgCd String
     * @param orgFuncRoleTpCd String
     * @return boolean
     */

    public static boolean checkBusinessAreaRoll(String bizAreaOrgCd, String orgFuncRoleTpCd) {

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().checkBusinessAreaRoll(bizAreaOrgCd, orgFuncRoleTpCd);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt.compareTo(0) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * checkBusinessAreaOrg
     * @param bizAreaOrgCd String
     * @param orgCd String
     * @return boolean
     */
    public static boolean checkBusinessAreaOrg(String bizAreaOrgCd, String orgCd) {

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().checkBusinessAreaOrg(bizAreaOrgCd, orgCd);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt.compareTo(0) > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * isOrgFuncRoleSales
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean isOrgFuncRoleSales(NMAL2510CMsg cMsg, int i) {

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().isOrgFuncRoleSales(cMsg, i);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        if (resCnt.compareTo(0) > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 2016/06/20 CSA-QC#10424 Add Start
    /**
     * isTargetTocReq
     * @param globalCompanyCode String
     * @param orgFuncRoleTpCd String
     * @return boolean
     */
    public static boolean isTargetTocReq(String globalCompanyCode, String orgFuncRoleTpCd) {

        ORG_FUNC_ROLE_TPTMsg tMsg = new ORG_FUNC_ROLE_TPTMsg();
        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        tMsg.orgFuncRoleTpCd.setValue(orgFuncRoleTpCd);

        tMsg = (ORG_FUNC_ROLE_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.tocReqFlg.getValue())) {
            return true;
        }
        return false;
    }

    // 2016/06/20 CSA-QC#10424 Add End

    // 2016/06/29 CSA-QC#11087 Add Start
    /**
     * isSalesRep
     * @param globalCompanyCode String
     * @param orgFuncRoleTpCd String
     * @return boolean
     */
    public static boolean isSalesRep(String globalCompanyCode, String orgFuncRoleTpCd) {
        if (!ZYPCommonFunc.hasValue(orgFuncRoleTpCd)) {
            return false;
        }

        ORG_FUNC_ROLE_TPTMsg tMsg = new ORG_FUNC_ROLE_TPTMsg();
        tMsg.glblCmpyCd.setValue(globalCompanyCode);
        tMsg.orgFuncRoleTpCd.setValue(orgFuncRoleTpCd);

        tMsg = (ORG_FUNC_ROLE_TPTMsg) S21FastTBLAccessor.findByKey(tMsg);
        if (tMsg != null && ZYPConstant.FLG_ON_Y.equals(tMsg.slsRepFlg.getValue())) {
            return true;
        }
        return false;
    }

    // 2016/06/29 CSA-QC#11087 Add End

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
     * @param date String
     * @return boolean
     */
    public static boolean isDateFuture(String date) {
        if (!ZYPCommonFunc.hasValue(date)) {
            date = NMAL2510Constant.MAX_EFF_THRU_DT;
        }

        if (date.compareTo(ZYPDateUtil.getSalesDate()) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    // 2016/10/04 CSA-QC#4099 Add Start
    /**
     * isExitsFutureDate
     * @param cMsg NMAL2510CMsg
     * @param targetDate String
     * @param curIndex int
     * @return boolean
     */
    public static boolean isExitsFutureFromDate(NMAL2510CMsg cMsg, String targetDate, int curIndex) {
        boolean ret = false;
        //String thruDt = cMsg.C.no(curIndex).effThruDt_C2.getValue();

        if (!ZYPCommonFunc.hasValue(targetDate)) {
            return ret;
        }

        int max = cMsg.C.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            if (cnt == curIndex) {
                continue;
            }

            String fromDt = cMsg.C.no(cnt).effFromDt_C2.getValue();

            if (!ZYPCommonFunc.hasValue(fromDt)) {
                continue;
            }

            if (compareDate(fromDt, targetDate)) {
                ret = true;
                break;
            }
        }

        return ret;
    }
    // 2016/10/04 CSA-QC#4099 Add End

    // Add Start 2018/07/26 QC#20237
    /**
     * isExitsFutureDate
     * @param cMsg NMAL2510CMsg
     * @param targetDate String
     * @param curIndex int
     * @return boolean
     */
    public static boolean isExitsFutureFromDateAddLine(NMAL2510CMsg cMsg, String targetDate, int curIndex) {
        boolean ret = false;
        //String thruDt = cMsg.C.no(curIndex).effThruDt_C2.getValue();

        if (!ZYPCommonFunc.hasValue(targetDate)) {
            return true;
        }

        int max = cMsg.C.getValidCount();

        for (int cnt = 0; cnt < max; cnt++) {
            if (cnt == curIndex) {
                continue;
            }

            String fromDt = cMsg.C.no(cnt).effFromDt_C2.getValue();

            if (!ZYPCommonFunc.hasValue(fromDt)) {
                continue;
            }

            if (compareDate(fromDt, targetDate)) {
                ret = true;
                break;
            }
        }

        return ret;
    }
    // Add End 2018/07/26 QC#20237

    /**
     * checkActiveDate
     * @param cMsg NMAL2500CMsg
     * @param orgRelnStartDate String
     * @return boolean
     */
    public static boolean checkActiveResourceRevenue(NMAL2510CMsg cMsg, String orgRelnStartDate) {
        // boolean flgRevenue = false;
        // boolean flgFreight = false;
        // boolean flgReceivables = false;

        // check if Revenue, Freight and Receivable is set
        for (int i = 0; i < cMsg.C.getValidCount(); i++) {
            // 2016/03/16 CSA-QC#5415 Add Start
            if (orgRelnStartDate.compareTo(cMsg.C.no(i).effFromDt_C2.getValue()) < 0 && !checkActiveDate(cMsg.C.no(i).effFromDt_C2.getValue(), cMsg.C.no(i).effThruDt_C2.getValue(), ZYPDateUtil.getSalesDate())) {
                continue;
            }
            // 2016/03/16 CSA-QC#5415 Add End

            if (NMAL2510Constant.REV_ACCT_TP_CD_REVENUE.equals(cMsg.C.no(i).revAcctTpCd_P1.getValue())) {
                return true;
            }
        }
        return false;
        // if
        // (NMAL2510Constant.REV_ACCT_TP_CD_REVENUE.equals(cMsg.C.no(i).revAcctTpCd_P1.getValue()))
        // {
        // flgRevenue = true;
        // } else if
        // (NMAL2510Constant.REV_ACCT_TP_CD_FREIGHT.equals(cMsg.C.no(i).revAcctTpCd_P1.getValue()))
        // {
        // flgFreight = true;
        // } else if
        // (NMAL2510Constant.REV_ACCT_TP_CD_RECEIVABLES.equals(cMsg.C.no(i).revAcctTpCd_P1.getValue()))
        // {
        // flgReceivables = true;
        // }
        // }
        //
        // if (flgRevenue && flgFreight && flgReceivables) {
        // return true;
        // }

        // 2016/03/16 CSA-QC#5415 Delete Start
        // check if Revenue, Freight and Receivable is registered
        // String currentRevTp = null;
        // S21SsmEZDResult ssmResult =
        // NMAL2510Query.getInstance().checkActiveResourceRevenue(cMsg);
        // if (!ssmResult.isCodeNormal()) {
        // cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new
        // String[]
        // {NMAL2510Constant.NAME_FOR_MESSAGE_REVENUE_ACCOUNT_TYPE });
        // return false;
        // } else {
        // List resultList = (List) ssmResult.getResultObject();
        //
        // for (int j = 0; j < resultList.size(); j++) {
        // Map map = (Map) resultList.get(j);
        // currentRevTp = (String) map.get("REV_ACCT_TP_CD");
        // if
        // (NMAL2510Constant.REV_ACCT_TP_CD_REVENUE.equals(currentRevTp))
        // {
        // flgRevenue = true;
        // } else if
        // (NMAL2510Constant.REV_ACCT_TP_CD_FREIGHT.equals(currentRevTp))
        // {
        // flgFreight = true;
        // } else if
        // (NMAL2510Constant.REV_ACCT_TP_CD_RECEIVABLES.equals(currentRevTp))
        // {
        // flgReceivables = true;
        // }
        // }
        // }
        //
        // if (flgRevenue && flgFreight && flgReceivables) {
        // return true;
        // }
        // 2016/03/16 CSA-QC#5415 Delete End
        // return false;
    }

    /**
     * checkContract
     * @param cMsg NMAL2500CMsg
     * @return boolean
     */
    public static boolean checkContract(NMAL2510CMsg cMsg) {

        if (ZYPCommonFunc.hasValue(cMsg.ctacPsnPk_D1)) {
            // 2016/04/05 CSA-QC#5411 Add Start
            Integer resCnt = 0;
            for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                if (checkActiveDate(cMsg.A.no(i).effFromDt_A2.getValue(), cMsg.A.no(i).effThruDt_A2.getValue(), ZYPDateUtil.getSalesDate())) {
                    S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getThirdPtyTechFlg(cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue());
                    resCnt = (Integer) ssmResult.getResultObject();
                }
            }
            // 2016/04/05 CSA-QC#5411 Add End

            // 2016/04/05 CSA-QC#5411 Delete Start
            // S21SsmEZDResult ssmResult =
            // NMAL2510Query.getInstance().getThirdPtyTechFlg(cMsg);
            // Integer resCnt = (Integer) ssmResult.getResultObject();
            // 2016/04/05 CSA-QC#5411 Delete End

            if (resCnt > 0) {
                S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getSplyIndFlg(cMsg);
                resCnt = (Integer) ssmResult.getResultObject();
                if (resCnt > 0) {
                    return true;
                } else {
                    cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_SPLY_IND_FLG });
                    return false;
                }
            } else {
                cMsg.setMessageInfo(NMAL2510Constant.NMAM0014E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_THIRD_PTY_TECH_FLG });
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * isChangedPrevTab
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return boolean
     */
    public static boolean isChangedPrevTab(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        boolean changedFlg = false;

        if (ZYPCommonFunc.hasValue(cMsg.xxHldFlg_H0) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxHldFlg_H0.getValue())) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_OFF_N);
            return changedFlg;
        }

        if (ZYPCommonFunc.hasValue(sMsg.psnCd_H1)) {
            if (!cMsg.psnNum_H1.getValue().equals(sMsg.psnNum_H1.getValue()) || !cMsg.psnFirstNm_H1.getValue().equals(sMsg.psnFirstNm_H1.getValue()) || !cMsg.psnTpCd_P1.getValue().equals(sMsg.psnTpCd_P1.getValue())
                    || !cMsg.psnLastNm_H1.getValue().equals(sMsg.psnLastNm_H1.getValue()) || !cMsg.psnCd_H1.getValue().equals(sMsg.psnCd_H1.getValue()) || !cMsg.hrPsnCmpyNm_H1.getValue().equals(sMsg.hrPsnCmpyNm_H1.getValue())
                    || !cMsg.effFromDt_H1.getValue().equals(sMsg.effFromDt_H1.getValue()) || !cMsg.jobTtlCd_H1.getValue().equals(sMsg.jobTtlCd_H1.getValue()) || !cMsg.effThruDt_H1.getValue().equals(sMsg.effThruDt_H1.getValue())
                    || !cMsg.hrTtlNm_H1.getValue().equals(sMsg.hrTtlNm_H1.getValue()) || !cMsg.workTelNum_H1.getValue().equals(sMsg.workTelNum_H1.getValue()) || !cMsg.hrSupvId_H1.getValue().equals(sMsg.hrSupvId_H1.getValue())
                    || !cMsg.cellTelNum_H1.getValue().equals(sMsg.cellTelNum_H1.getValue()) || !cMsg.hrSupvNm_H1.getValue().equals(sMsg.hrSupvNm_H1.getValue()) || !cMsg.emlAddr_H1.getValue().equals(sMsg.emlAddr_H1.getValue())
                    || !cMsg.lineBizTpCd_P1.getValue().equals(sMsg.lineBizTpCd_P1.getValue()) || !cMsg.xxAllLineAddr_H1.getValue().equals(sMsg.xxAllLineAddr_H1.getValue()) || !cMsg.geoCd_C1.getValue().equals(sMsg.geoCd_C1.getValue())
                    || (!ZYPCommonFunc.hasValue(cMsg.dsInsdCtyLimitFlg_C1) && ZYPConstant.FLG_ON_Y.equals(cMsg.dsInsdCtyLimitFlg_C1.getValue())) || cMsg.moveOrdLimitAmt_D1.getValueInt() != sMsg.moveOrdLimitAmt_D1.getValueInt()
                    || !cMsg.tmZoneCd_P1.getValue().equals(sMsg.tmZoneCd_P1.getValue()) || cMsg.costPerHourAmt_D1.getValueInt() != sMsg.costPerHourAmt_D1.getValueInt() || cMsg.ctacPsnPk_D1.getValueInt() != sMsg.ctacPsnPk_D1.getValueInt()
                    || !cMsg.xxPsnNm_D1.getValue().equals(sMsg.xxPsnNm_D1.getValue())) {
                changedFlg = true;
            }
        }

        if (NMAL2510Constant.TAB_HIERARCHY.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.A.getValidCount() != cMsg.A.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.A.getValidCount(); i++) {
                    NMAL2510_ACMsg acMsg = cMsg.A.no(i);
                    NMAL2510_ASMsg asMsg = sMsg.A.no(i);
                    if (!acMsg.bizAreaOrgCd_P2.getValue().equals(asMsg.bizAreaOrgCd_P2.getValue()) //
                            || !acMsg.orgFuncRoleTpCd_P2.getValue().equals(asMsg.orgFuncRoleTpCd_P2.getValue()) //
                            || !acMsg.orgNm_A2.getValue().equals(asMsg.orgNm_A2.getValue()) //
                            || !acMsg.effFromDt_A2.getValue().equals(asMsg.effFromDt_A2.getValue()) //
                            || !acMsg.effThruDt_A2.getValue().equals(asMsg.effThruDt_A2.getValue()) //
                    ) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2510Constant.TAB_TERRITORY.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.B.getValidCount() != cMsg.B.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.B.getValidCount(); i++) {
                    NMAL2510_BCMsg bcMsg = cMsg.B.no(i);
                    NMAL2510_BSMsg bsMsg = sMsg.B.no(i);
                    if (!bcMsg.bizAreaOrgCd_P3.getValue().equals(bsMsg.bizAreaOrgCd_P3.getValue()) //
                            || !bcMsg.orgNm_B2.getValue().equals(bsMsg.orgNm_B2.getValue()) //
                            || !bcMsg.acctTeamRoleTpCd_P3.getValue().equals(bsMsg.acctTeamRoleTpCd_P3.getValue()) // 
                            || !bcMsg.effFromDt_B2.getValue().equals(bsMsg.effFromDt_B2.getValue()) //
                            || !bcMsg.effThruDt_B2.getValue().equals(bsMsg.effThruDt_B2.getValue()) //
                            || (!ZYPCommonFunc.hasValue(bcMsg.xxChkBox_B2) && ZYPConstant.FLG_ON_Y.equals(bsMsg.xxChkBox_B2.getValue()))) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2510Constant.TAB_REVENUE.equals(cMsg.xxDplyTab.getValue())) {
            if (sMsg.C.getValidCount() != cMsg.C.getValidCount()) {
                changedFlg = true;
            } else {
                for (int i = 0; i < cMsg.C.getValidCount(); i++) {
                    NMAL2510_CCMsg ccMsg = cMsg.C.no(i);
                    NMAL2510_CSMsg csMsg = sMsg.C.no(i);
                    if (!ccMsg.revAcctTpCd_P1.getValue().equals(csMsg.revAcctTpCd_P1.getValue()) //
                            || !ccMsg.coaCmpyCd_C2.getValue().equals(csMsg.coaCmpyCd_C2.getValue()) //
                            || !ccMsg.coaExtnCd_C2.getValue().equals(csMsg.coaExtnCd_C2.getValue()) //
                            || !ccMsg.coaBrCd_C2.getValue().equals(csMsg.coaBrCd_C2.getValue()) //
                            || !ccMsg.coaCcCd_C2.getValue().equals(csMsg.coaCcCd_C2.getValue()) //
                            || !ccMsg.effFromDt_C2.getValue().equals(csMsg.effFromDt_C2.getValue()) //
                            || !ccMsg.effThruDt_C2.getValue().equals(csMsg.effThruDt_C2.getValue()) //
                            || (!ZYPCommonFunc.hasValue(ccMsg.xxChkBox_C3) && ZYPConstant.FLG_ON_Y.equals(csMsg.xxChkBox_C3.getValue()))) {
                        changedFlg = true;
                    }
                }
            }
        } else if (NMAL2510Constant.TAB_MISC.equals(cMsg.xxDplyTab.getValue())) {
            if (cMsg.moveOrdLimitAmt_D1.getValueInt() != sMsg.moveOrdLimitAmt_D1.getValueInt() //
                    || !cMsg.tmZoneCd_P1.getValue().equals(sMsg.tmZoneCd_P1.getValue()) //
                    || cMsg.costPerHourAmt_D1.getValueInt() != sMsg.costPerHourAmt_D1.getValueInt() //
                    || cMsg.ctacPsnPk_D1.getValueInt() != sMsg.ctacPsnPk_D1.getValueInt() //
                    || !cMsg.xxPsnNm_D1.getValue().equals(sMsg.xxPsnNm_D1.getValue()) //
            ) {
                changedFlg = true;
            }
        }

        if (changedFlg == true) {
            cMsg.xxHldFlg_H0.setValue(ZYPConstant.FLG_ON_Y);
            cMsg.setMessageInfo(NMAL2510Constant.NMAM8286W);
        }
        return changedFlg;

    }

    /**
     * isChangedPrevTab
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     */
    public static void clearHierarchy(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        sMsg.T.clear();
        cMsg.T.clear();
        cMsg.xxWrnSkipFlg_H1.clear();
        // sMsg.orgNm_1.clear();
        // sMsg.orgNm_2.clear();
        // sMsg.orgNm_3.clear();
        // sMsg.orgNm_4.clear();
        // sMsg.orgNm_5.clear();
        // sMsg.orgNm_6.clear();
        // sMsg.orgNm_7.clear();
        // sMsg.orgNm_8.clear();
        // sMsg.orgNm_9.clear();
        // sMsg.orgNm_10.clear();
        // cMsg.orgNm_1.clear();
        // cMsg.orgNm_2.clear();
        // cMsg.orgNm_3.clear();
        // cMsg.orgNm_4.clear();
        // cMsg.orgNm_5.clear();
        // cMsg.orgNm_6.clear();
        // cMsg.orgNm_7.clear();
        // cMsg.orgNm_8.clear();
        // cMsg.orgNm_9.clear();
        // cMsg.orgNm_10.clear();
    }

    /**
     * isChangedPrevTab
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     */
    public static void clearHeader(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        // sMsg.psnFirstNm_H1.clear();
        // sMsg.psnTpCd_P1.clear();
        // sMsg.psnLastNm_H1.clear();
        sMsg.psnCd_H1.clear();
        sMsg.hrPsnCmpyNm_H1.clear();
        // sMsg.effFromDt_H1.clear();
        sMsg.jobTtlCd_H1.clear();
        sMsg.hrTtlNm_H1.clear();
        sMsg.effThruDt_H1.clear();
        sMsg.hrSupvId_H1.clear();
        sMsg.hrSupvNm_H1.clear();
        sMsg.workTelNum_H1.clear();
        sMsg.emlAddr_H1.clear();
        sMsg.cellTelNum_H1.clear();
        sMsg.trtyGrpTpCd_P1.clear();
        sMsg.lineBizTpCd_P1.clear();
        sMsg.firstLineAddr_H1.clear();
        sMsg.scdLineAddr_H1.clear();
        sMsg.thirdLineAddr_H1.clear();
        sMsg.frthLineAddr_H1.clear();
        sMsg.ctyAddr_H1.clear();
        sMsg.cntyPk_H1.clear();
        sMsg.cntyNm_H1.clear();
        sMsg.provNm_H1.clear();
        sMsg.stCd_H1.clear();
        sMsg.postCd_H1.clear();
        sMsg.ctryCd_H1.clear();
        sMsg.xxAllLineAddr_H1.clear();
        sMsg.ezUpTime_H1.clear();
        sMsg.ezUpTimeZone_H1.clear();
        sMsg.coaCmpyCd_H1.clear();
        sMsg.coaExtnCd_H1.clear();
        sMsg.coaBrCd_H1.clear();
        sMsg.coaCcCd_H1.clear();
        sMsg.otherResrcTrtyFlg_H1.clear();
        sMsg.geoCd_C1.clear();
        sMsg.dsInsdCtyLimitFlg_C1.clear();
        sMsg.moveOrdLimitAmt_D1.clear();
        sMsg.tmZoneCd_P1.clear();
        sMsg.costPerHourAmt_D1.clear();
        sMsg.ctacPsnPk_D1.clear();
        sMsg.ctacTpCd_D1.clear();
        sMsg.ctacPsnFirstNm_D1.clear();
        sMsg.ctacPsnLastNm_D1.clear();
        sMsg.ctacPsnNum_D1.clear();
        sMsg.xxPsnNm_D1.clear();
        // cMsg.psnFirstNm_H1.clear();
        // cMsg.psnTpCd_P1.clear();
        // cMsg.psnLastNm_H1.clear();
        cMsg.psnCd_H1.clear();
        cMsg.hrPsnCmpyNm_H1.clear();
        // cMsg.effFromDt_H1.clear();
        cMsg.jobTtlCd_H1.clear();
        cMsg.hrTtlNm_H1.clear();
        cMsg.effThruDt_H1.clear();
        cMsg.hrSupvId_H1.clear();
        cMsg.hrSupvNm_H1.clear();
        cMsg.workTelNum_H1.clear();
        cMsg.emlAddr_H1.clear();
        cMsg.cellTelNum_H1.clear();
        // QC#10364
        cMsg.hrPsnIntfcLocCd_H1.clear();
        cMsg.trtyGrpTpCd_P1.clear();
        cMsg.lineBizTpCd_P1.clear();
        cMsg.firstLineAddr_H1.clear();
        cMsg.scdLineAddr_H1.clear();
        cMsg.thirdLineAddr_H1.clear();
        cMsg.frthLineAddr_H1.clear();
        cMsg.ctyAddr_H1.clear();
        cMsg.cntyPk_H1.clear();
        cMsg.cntyNm_H1.clear();
        cMsg.provNm_H1.clear();
        cMsg.stCd_H1.clear();
        cMsg.postCd_H1.clear();
        cMsg.ctryCd_H1.clear();
        cMsg.xxAllLineAddr_H1.clear();
        cMsg.ezUpTime_H1.clear();
        cMsg.ezUpTimeZone_H1.clear();
        cMsg.coaCmpyCd_H1.clear();
        cMsg.coaExtnCd_H1.clear();
        cMsg.coaBrCd_H1.clear();
        cMsg.coaCcCd_H1.clear();
        cMsg.otherResrcTrtyFlg_H1.clear();
        cMsg.geoCd_C1.clear();
        cMsg.dsInsdCtyLimitFlg_C1.clear();
        cMsg.moveOrdLimitAmt_D1.clear();
        cMsg.tmZoneCd_P1.clear();
        cMsg.costPerHourAmt_D1.clear();
        cMsg.ctacPsnPk_D1.clear();
        cMsg.ctacTpCd_D1.clear();
        cMsg.ctacPsnFirstNm_D1.clear();
        cMsg.ctacPsnLastNm_D1.clear();
        cMsg.ctacPsnNum_D1.clear();
        cMsg.xxPsnNm_D1.clear();

    }

    /**
     * createPulldownforHierarchy
     * @param cMsg NMAL2510CMsg
     * @param globalCompanyCode String
     * @param i int
     */
    public static void createPulldownforHierarchy(NMAL2510CMsg cMsg, String globalCompanyCode, int i) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(NMAL2510Constant.DB_PARAM_CMSG, cMsg);
        ssmParam.put("globalCmpyCd", globalCompanyCode);
        ssmParam.put("trtyStruFlg", ZYPConstant.FLG_OFF_N);

        // Business Area
        S21SsmEZDResult bizAreaOrgPulldownList = NMAL2510Query.getInstance().getBizAreaOrgPulldownList(ssmParam);
        if (bizAreaOrgPulldownList.isCodeNormal()) {
            List<Map> bizAreaOrgList = (List<Map>) bizAreaOrgPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.A.no(i).bizAreaOrgCd_A1, cMsg.A.no(i).bizAreaOrgNm_A1, bizAreaOrgList, new String[] {NMAL2510Constant.BIZ_AREA_ORG_CD_DBCOLUMN, NMAL2510Constant.BIZ_AREA_ORG_NM_DBCOLUMN });
        }

        // Organization Function Role Type
        if (ZYPCommonFunc.hasValue(cMsg.A.no(i).bizAreaOrgCd_P2)) {
            ssmParam.put("bizAreaOrgCd", cMsg.A.no(i).bizAreaOrgCd_P2.getValue());
        } else {
            ssmParam.put("bizAreaOrgCd", BIZ_AREA_ORG.SALES);
        }

        S21SsmEZDResult orgFuncRoleTpPulldownList = NMAL2510Query.getInstance().getOrgFuncRoleTpWithBusinessAreaPulldownList(ssmParam);

        if (orgFuncRoleTpPulldownList.isCodeNormal()) {
            List<Map> orgFuncRoleTpList = (List<Map>) orgFuncRoleTpPulldownList.getResultObject();
            NMAL2510CommonLogic.createPulldownList(cMsg.A.no(i).orgFuncRoleTpCd_A2, cMsg.A.no(i).orgFuncRoleTpNm_A2, orgFuncRoleTpList, new String[] {NMAL2510Constant.ORG_FUNC_ROLE_TP_CD_DBCOLUMN,
                    NMAL2510Constant.ORG_FUNC_ROLE_TP_NM_DBCOLUMN });
        }
    }

    /**
     * clearforHierachySearch
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     */
    public static void clearforHierachySearch(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        cMsg.A.clear();
        cMsg.A.setValidCount(0);
        cMsg.X.clear();
        cMsg.X.setValidCount(0);
        sMsg.X.clear();
        sMsg.X.setValidCount(0);
    }

    /**
     * clearforTerritorySearch
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     */
    public static void clearforTerritorySearch(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        cMsg.B.clear();
        cMsg.B.setValidCount(0);
        cMsg.Y.clear();
        cMsg.Y.setValidCount(0);
        sMsg.Y.clear();
        sMsg.Y.setValidCount(0);
    }

    /**
     * clearforRevenueSearch
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     */
    public static void clearforRevenueSearch(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        cMsg.C.clear();
        cMsg.C.setValidCount(0);
        cMsg.Z.clear();
        cMsg.Z.setValidCount(0);
        sMsg.Z.clear();
        sMsg.Z.setValidCount(0);
    }

    /**
     * checkDuplicateResourceNumber
     * @param cMsg NMAL2510CMsg
     * @param sMsg NMAL2510SMsg
     * @return boolean
     */
    public static boolean checkDuplicatePsnNum(NMAL2510CMsg cMsg, NMAL2510SMsg sMsg) {

        S21SsmEZDResult ssmResult = NMAL2510Query.getInstance().getDuplicatePsnNum(cMsg);
        Integer resCnt = (Integer) ssmResult.getResultObject();
        // QC#6402
        // if (resCnt > 1) {
        if (resCnt > 0) {
            cMsg.setMessageInfo(NMAL2510Constant.NMAM0297E);
            return true;
        }
        return false;
    }

    /**
     * setBackUp
     * @param cMsg NMAL2510CMsg
     */
    public static void setBackUp(NMAL2510CMsg cMsg) {
        // ## Header ##
        ZYPEZDItemValueSetter.setValue(cMsg.psnNum_HB, cMsg.psnNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.psnFirstNm_HB, cMsg.psnFirstNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.psnTpCd_HB, cMsg.psnTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.psnLastNm_HB, cMsg.psnLastNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.psnCd_HB, cMsg.psnCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.hrPsnCmpyNm_HB, cMsg.hrPsnCmpyNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effFromDt_HB, cMsg.effFromDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.jobTtlCd_HB, cMsg.jobTtlCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.hrTtlNm_HB, cMsg.hrTtlNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.effThruDt_HB, cMsg.effThruDt_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.hrSupvId_HB, cMsg.hrSupvId_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.hrSupvNm_HB, cMsg.hrSupvNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.workTelNum_HB, cMsg.workTelNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.emlAddr_HB, cMsg.emlAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.cellTelNum_HB, cMsg.cellTelNum_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.lineBizTpCd_HB, cMsg.lineBizTpCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr_HB, cMsg.firstLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr_HB, cMsg.scdLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.thirdLineAddr_HB, cMsg.thirdLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.frthLineAddr_HB, cMsg.frthLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr_HB, cMsg.ctyAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.cntyPk_HB, cMsg.cntyPk_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.cntyNm_HB, cMsg.cntyNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.provNm_HB, cMsg.provNm_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.stCd_HB, cMsg.stCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.postCd_HB, cMsg.postCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.ctryCd_HB, cMsg.ctryCd_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxAllLineAddr_HB, cMsg.xxAllLineAddr_H1);
        ZYPEZDItemValueSetter.setValue(cMsg.geoCd_CB, cMsg.geoCd_C1);
        ZYPEZDItemValueSetter.setValue(cMsg.dsInsdCtyLimitFlg_CB, cMsg.dsInsdCtyLimitFlg_C1);
        ZYPEZDItemValueSetter.setValue(cMsg.moveOrdLimitAmt_DB, cMsg.moveOrdLimitAmt_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.tmZoneCd_DB, cMsg.tmZoneCd_P1);
        ZYPEZDItemValueSetter.setValue(cMsg.costPerHourAmt_DB, cMsg.costPerHourAmt_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.ctacPsnPk_DB, cMsg.ctacPsnPk_D1);
        ZYPEZDItemValueSetter.setValue(cMsg.xxPsnNm_DB, cMsg.xxPsnNm_D1);

        // ## Hierarchy ##
        int i = 0;
        for (; i < cMsg.A.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).bizAreaOrgCd_AB, cMsg.A.no(i).bizAreaOrgCd_P2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgFuncRoleTpCd_AB, cMsg.A.no(i).orgFuncRoleTpCd_P2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgCd_AB, cMsg.A.no(i).orgCd_A2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgStruTpCd_AB, cMsg.A.no(i).orgStruTpCd_A2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).orgNm_AB, cMsg.A.no(i).orgNm_A2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effFromDt_AB, cMsg.A.no(i).effFromDt_A2);
            ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_AB, cMsg.A.no(i).effThruDt_A2);
        }

        // ## Territory ##
        i = 0;
        for (; i < cMsg.B.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).bizAreaOrgCd_BB, cMsg.B.no(i).bizAreaOrgCd_P3);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgNm_BB, cMsg.B.no(i).orgNm_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgCd_BB, cMsg.B.no(i).orgCd_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).orgFuncRoleTpCd_BB, cMsg.B.no(i).orgFuncRoleTpCd_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).acctTeamRoleTpCd_BB, cMsg.B.no(i).acctTeamRoleTpCd_P3);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effFromDt_BB, cMsg.B.no(i).effFromDt_B2);
            ZYPEZDItemValueSetter.setValue(cMsg.B.no(i).effThruDt_BB, cMsg.B.no(i).effThruDt_B2);
        }

        // ## Revenue ##
        i = 0;
        for (; i < cMsg.C.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).revAcctTpCd_CB, cMsg.C.no(i).revAcctTpCd_P1);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).coaCmpyCd_CB, cMsg.C.no(i).coaCmpyCd_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).coaCcCd_CB, cMsg.C.no(i).coaCcCd_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).coaExtnCd_CB, cMsg.C.no(i).coaExtnCd_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).coaBrCd_CB, cMsg.C.no(i).coaBrCd_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).effFromDt_CB, cMsg.C.no(i).effFromDt_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).effThruDt_CB, cMsg.C.no(i).effThruDt_C2);
            ZYPEZDItemValueSetter.setValue(cMsg.C.no(i).xxChkBox_CB, cMsg.C.no(i).xxChkBox_C3);
        }
    }

    /**
     * checkChangedFieldsForS21Psn
     * @param cMsg NMAL2510CMsg
     * @return boolean
     */
    public static boolean checkChangedFieldsForS21Psn(NMAL2510CMsg cMsg) {
        if (isNotEquals(cMsg.psnFirstNm_H1.getValue(), cMsg.psnFirstNm_HB.getValue()) || isNotEquals(cMsg.psnLastNm_H1.getValue(), cMsg.psnLastNm_HB.getValue()) || isNotEquals(cMsg.hrTtlNm_H1.getValue(), cMsg.hrTtlNm_HB.getValue())
                || isNotEquals(cMsg.psnTpCd_P1.getValue(), cMsg.psnTpCd_HB.getValue()) || isNotEquals(cMsg.emlAddr_H1.getValue(), cMsg.emlAddr_HB.getValue()) || isNotEquals(cMsg.jobTtlCd_H1.getValue(), cMsg.jobTtlCd_HB.getValue())
                || isNotEquals(cMsg.effFromDt_H1.getValue(), cMsg.effFromDt_HB.getValue()) || isNotEquals(cMsg.effThruDt_H1.getValue(), cMsg.effThruDt_HB.getValue())
                || isNotEquals(cMsg.cellTelNum_H1.getValue(), cMsg.cellTelNum_HB.getValue()) || isNotEquals(cMsg.workTelNum_H1.getValue(), cMsg.workTelNum_HB.getValue()) || isNotEquals(cMsg.psnNum_H1.getValue(), cMsg.psnNum_HB.getValue())
                || isNotEquals(cMsg.lineBizTpCd_P1.getValue(), cMsg.lineBizTpCd_HB.getValue()) || isNotEquals(cMsg.hrSupvId_H1.getValue(), cMsg.hrSupvId_HB.getValue())
                || isNotEquals(cMsg.hrSupvNm_H1.getValue(), cMsg.hrSupvNm_H1.getValue()) || isNotEquals(cMsg.hrPsnCmpyNm_H1.getValue(), cMsg.hrPsnCmpyNm_HB.getValue())
                || isNotEquals(cMsg.firstLineAddr_H1.getValue(), cMsg.firstLineAddr_HB.getValue()) || isNotEquals(cMsg.scdLineAddr_H1.getValue(), cMsg.scdLineAddr_HB.getValue())
                || isNotEquals(cMsg.thirdLineAddr_H1.getValue(), cMsg.thirdLineAddr_HB.getValue()) || isNotEquals(cMsg.frthLineAddr_H1.getValue(), cMsg.frthLineAddr_HB.getValue())
                || isNotEquals(cMsg.ctyAddr_H1.getValue(), cMsg.ctyAddr_HB.getValue()) || isNotEquals(cMsg.cntyPk_H1.getValue(), cMsg.cntyPk_HB.getValue()) || isNotEquals(cMsg.provNm_H1.getValue(), cMsg.provNm_HB.getValue())
                || isNotEquals(cMsg.stCd_H1.getValue(), cMsg.stCd_HB.getValue()) || isNotEquals(cMsg.postCd_H1.getValue(), cMsg.postCd_HB.getValue()) || isNotEquals(cMsg.ctryCd_H1.getValue(), cMsg.ctryCd_HB.getValue())
                || isNotEquals(cMsg.geoCd_C1.getValue(), cMsg.geoCd_CB.getValue()) || isNotEquals(cMsg.dsInsdCtyLimitFlg_C1.getValue(), cMsg.dsInsdCtyLimitFlg_CB.getValue())
                || isNotEquals(cMsg.moveOrdLimitAmt_D1.getValue(), cMsg.moveOrdLimitAmt_DB.getValue()) || isNotEquals(cMsg.tmZoneCd_P1.getValue(), cMsg.tmZoneCd_DB.getValue())
                || isNotEquals(cMsg.costPerHourAmt_D1.getValue(), cMsg.costPerHourAmt_DB.getValue()) || isNotEquals(cMsg.ctacPsnPk_D1.getValue(), cMsg.ctacPsnPk_DB.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkChangedFieldsForTocOrgStruReln
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForTocOrgStruReln(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue()) || isNotEquals(cMsg.A.no(i).orgCd_AB.getValue(), cMsg.A.no(i).orgCd_A2.getValue())
                || isNotEquals(cMsg.A.no(i).effThruDt_AB.getValue(), cMsg.A.no(i).effThruDt_A2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkChangedFieldsForOrgTocChngRqst
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForOrgTocChngRqst(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.psnFirstNm_H1.getValue(), cMsg.psnFirstNm_HB.getValue()) || isNotEquals(cMsg.psnLastNm_H1.getValue(), cMsg.psnLastNm_HB.getValue())
                || isNotEquals(cMsg.A.no(i).orgFuncRoleTpCd_AB.getValue(), cMsg.A.no(i).orgFuncRoleTpCd_P2.getValue()) || isNotEquals(cMsg.A.no(i).orgCd_AB.getValue(), cMsg.A.no(i).orgCd_A2.getValue())
                || isNotEquals(cMsg.coaBrCd_HB.getValue(), cMsg.coaBrCd_H1.getValue()) || isNotEquals(cMsg.coaCcCd_HB.getValue(), cMsg.coaCcCd_H1.getValue()) || isNotEquals(cMsg.coaExtnCd_HB.getValue(), cMsg.coaExtnCd_H1.getValue())
                    // Mod Start 2017/10/17 QC#21753
                //|| isNotEquals(cMsg.coaCmpyCd_HB.getValue(), cMsg.coaCmpyCd_H1.getValue())) {
                || isNotEquals(cMsg.coaCmpyCd_HB.getValue(), cMsg.coaCmpyCd_H1.getValue()) 
                || isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue()) 
                || isNotEquals(cMsg.A.no(i).effThruDt_AB.getValue(), cMsg.A.no(i).effThruDt_A2.getValue())) {
            // Mod End 2017/10/17 QC#21753
            return true;
        }
        return false;
    }

    // Add Start 2019/02/27 QC#30564
    /**
     * isChangedHierarchyDate
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean isChangedHierarchyDate(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue()) || //
                isNotEquals(cMsg.A.no(i).effThruDt_AB.getValue(), cMsg.A.no(i).effThruDt_A2.getValue())) {
            return true;
        }
        return false;
    }
    // Add End 2019/02/27 QC#30564

    // Mod Start 2019/02/13 QC#29668
    ///**
    // * createOrgFuncAsg
    // * @param cMsg NMAL2510CMsg
    // * @param orgFuncAsgTMsg ORG_FUNC_ASGTMsg
    // * @param glblCmpyCd String
    // * @param i int
    // * @return boolean
    // */
    //public static boolean createOrgFuncAsg(NMAL2510CMsg cMsg, ORG_FUNC_ASGTMsg orgFuncAsgTMsg, String glblCmpyCd, int i) {
    /**
     * createOrgFuncAsg
     * @param cMsg NMAL2510CMsg
     * @param orgFuncAsgTMsg ORG_FUNC_ASGTMsg
     * @param glblCmpyCd String
     * @param i int
     * @param salesDate String
     * @return boolean
     */
    public static boolean createOrgFuncAsg(NMAL2510CMsg cMsg, ORG_FUNC_ASGTMsg orgFuncAsgTMsg, String glblCmpyCd, int i, String salesDate) {
        // Mod End 2019/02/13 QC#29668
        orgFuncAsgTMsg = new ORG_FUNC_ASGTMsg();
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.tocCd, cMsg.A.no(i).tocCd_A2);

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.psnCd, cMsg.psnCd_H1);

        // Mod Start 2019/02/13 QC#29668
        //ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, cMsg.A.no(i).effFromDt_A2);
        String effFromDt = null;

        if (NMAL2510CommonLogic.isNotEquals(cMsg.A.no(i).effFromDt_AB.getValue(), cMsg.A.no(i).effFromDt_A2.getValue())) {
            effFromDt = cMsg.A.no(i).effFromDt_A2.getValue();
        } else {
            effFromDt = salesDate;
        }

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effFromDt, effFromDt);
        // Mod End 2019/02/13 QC#29668

        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.effThruDt, cMsg.A.no(i).effThruDt_A2);
        ZYPEZDItemValueSetter.setValue(orgFuncAsgTMsg.gnrnTpCd, GNRN_TP.FUTURE);

        EZDTBLAccessor.create(orgFuncAsgTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgFuncAsgTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * createOrgTocChngRqst
     * @param cMsg NMAL2510CMsg
     * @param orgTocChngRqstTMsg ORG_TOC_CHNG_RQSTTMsg
     * @param glblCmpyCd String
     * @param i int
     * @return boolean
     */
    public static boolean createOrgTocChngRqst(NMAL2510CMsg cMsg, ORG_TOC_CHNG_RQSTTMsg orgTocChngRqstTMsg, String glblCmpyCd, int i) {
        orgTocChngRqstTMsg = new ORG_TOC_CHNG_RQSTTMsg();
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocCd, cMsg.A.no(i).tocCd_A2);

        BigDecimal orgChngRqstPk = ZYPOracleSeqAccessor.getNumberBigDecimal(ZYPOracleSeqAccessor.ORG_CHNG_RQST_SQ);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgChngRqstPk, orgChngRqstPk);

        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.tocNm, S21StringUtil.subStringByLength(S21StringUtil.concatStrings(cMsg.psnFirstNm_H1.getValue(), " ", cMsg.psnLastNm_H1.getValue()), 0, NMAL2510Constant.COLUMN_LEN_TOC_NM));
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgFuncRoleTpCd, cMsg.A.no(i).orgFuncRoleTpCd_P2);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.orgCd, cMsg.A.no(i).orgCd_A2);
        ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.rgtnStsCd, RGTN_STS.PENDING_COMPLETION);

        if (NMAL2510CommonLogic.getDefaultRevenue(cMsg)) {
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaBrCd, cMsg.coaBrCd_H1);
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaExtnCd, cMsg.coaExtnCd_H1);
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCcCd, cMsg.coaCcCd_H1);
            ZYPEZDItemValueSetter.setValue(orgTocChngRqstTMsg.coaCmpyCd, cMsg.coaCmpyCd_H1);
        }

        EZDTBLAccessor.create(orgTocChngRqstTMsg);
        if (!EZDTBLAccessor.RTNCD_NORMAL.equals(orgTocChngRqstTMsg.getReturnCode())) {
            return false;
        }
        return true;
    }

    /**
     * checkChangedFieldsForOrgFuncAsg
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForOrgFuncAsg(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.A.no(i).effThruDt_AB.getValue(), cMsg.A.no(i).effThruDt_A2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkChangedFieldsForDsOrgResrcReln
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForDsOrgResrcReln(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.B.no(i).effThruDt_BB.getValue(), cMsg.B.no(i).effThruDt_B2.getValue()) || isNotEquals(cMsg.B.no(i).acctTeamRoleTpCd_BB.getValue(), cMsg.B.no(i).acctTeamRoleTpCd_P3.getValue())
                || isNotEquals(cMsg.B.no(i).orgCd_BB.getValue(), cMsg.B.no(i).orgCd_B2.getValue()) || isNotEquals(cMsg.B.no(i).orgFuncRoleTpCd_BB.getValue(), cMsg.B.no(i).orgFuncRoleTpCd_B2.getValue())
                || isNotEquals(cMsg.B.no(i).effFromDt_BB.getValue(), cMsg.B.no(i).effFromDt_B2.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * checkChangedFieldsForDsOrgResrcRev
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @return boolean
     */
    public static boolean checkChangedFieldsForDsOrgResrcRev(NMAL2510CMsg cMsg, int i) {
        if (isNotEquals(cMsg.C.no(i).coaExtnCd_CB.getValue(), cMsg.C.no(i).coaExtnCd_C2.getValue()) || isNotEquals(cMsg.C.no(i).coaBrCd_CB.getValue(), cMsg.C.no(i).coaBrCd_C2.getValue())
                || isNotEquals(cMsg.C.no(i).coaCcCd_CB.getValue(), cMsg.C.no(i).coaCcCd_C2.getValue()) || isNotEquals(cMsg.C.no(i).coaCmpyCd_CB.getValue(), cMsg.C.no(i).coaCmpyCd_C2.getValue())
                // 2016/10/07 CSA-QC#15033 Add Start
                || isNotEquals(cMsg.C.no(i).effFromDt_CB.getValue(), cMsg.C.no(i).effFromDt_C2.getValue())
                // 2016/10/07 CSA-QC#15033 Add End
                || isNotEquals(cMsg.C.no(i).effThruDt_CB.getValue(), cMsg.C.no(i).effThruDt_C2.getValue()) || isNotEquals(cMsg.C.no(i).xxChkBox_CB.getValue(), cMsg.C.no(i).xxChkBox_C3.getValue())) {
            return true;
        }
        return false;
    }

    // Add Start 2019/02/13 QC#29668
    /**
     * @param cMsg NMAL2510CMsg
     * @param i int
     * @param slsDate
     * 
     * @return boolean
     */
    public static boolean isChangeExistingActiveGLString(NMAL2510CMsg cMsg, int i, String slsDate) {

        if (ZYPConstant.FLG_ON_Y.equals(cMsg.C.no(i).tocRgtnReqFlg_C2.getValue())) {
            return false;
        }

        if (cMsg.C.no(i).effFromDt_C2.getValue().compareTo(slsDate) > 0) {
            return false;
        }

        if (ZYPCommonFunc.hasValue(cMsg.C.no(i).effThruDt_C2) && cMsg.C.no(i).effThruDt_C2.getValue().compareTo(slsDate) < 0) {
            return false;
        }

        if (isNotEquals(cMsg.C.no(i).coaCmpyCd_CB.getValue(), cMsg.C.no(i).coaCmpyCd_C2.getValue()) || //
                isNotEquals(cMsg.C.no(i).coaExtnCd_CB.getValue(), cMsg.C.no(i).coaExtnCd_C2.getValue()) || //
                isNotEquals(cMsg.C.no(i).coaBrCd_CB.getValue(), cMsg.C.no(i).coaBrCd_C2.getValue()) || //
                isNotEquals(cMsg.C.no(i).coaCcCd_CB.getValue(), cMsg.C.no(i).coaCcCd_C2.getValue())) {
            return true;
        }

        return false;
    }
    // Add End 2019/02/13 QC#29668

    /**
     * compare date
     * @param cmprFrom String
     * @param cmprTo String
     * @return boolean true when "Compare From Date" is Bigger
     */
    public static boolean compareDate(String cmprFrom, String cmprTo) {
        if (!ZYPCommonFunc.hasValue(cmprFrom)) {
            cmprFrom = NMAL2510Constant.MAX_EFF_THRU_DT;
        }
        if (!ZYPCommonFunc.hasValue(cmprTo)) {
            cmprTo = NMAL2510Constant.MAX_EFF_THRU_DT;
        }

        if (cmprFrom.compareTo(cmprTo) > 0) {
            return true;
        }
        return false;
    }

    /**
     * isNotEquals
     * @param orig String
     * @param backUp String
     * @return boolean
     */
    public static boolean isNotEquals(String orig, String backUp) {
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
    private static boolean isPeriodOverlap(EZDCDateItem effFromDtFrom, EZDCDateItem effThruDtFrom, EZDCDateItem effFromDtTo, EZDCDateItem effThruDtTo) {
        return isPeriodOverlap(effFromDtFrom.getValue(), effThruDtFrom.getValue(), effFromDtTo.getValue(), effThruDtTo.getValue());
    }

    private static boolean isPeriodOverlap(String effFromDt_01, String effThruDt_01, String effFromDt_02, String effThruDt_02) {
        if (!ZYPCommonFunc.hasValue(effThruDt_01)) {
            effThruDt_01 = NMAL2510Constant.MAX_EFF_THRU_DT;
        }
        if (!ZYPCommonFunc.hasValue(effThruDt_02)) {
            effThruDt_02 = NMAL2510Constant.MAX_EFF_THRU_DT;
        }
        if (isDateInPeriod(effFromDt_01, effFromDt_02, effThruDt_02)) {
            return true;
        }
        if (isDateInPeriod(effThruDt_01, effFromDt_02, effThruDt_02)) {
            return true;
        }
        if (isDateInPeriod(effFromDt_02, effFromDt_01, effThruDt_01)) {
            return true;
        }
        if (isDateInPeriod(effThruDt_02, effFromDt_01, effThruDt_01)) {
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

    // QC#3859
    /**
     * isCurrentOrFuturePeriod
     * @param thruDt String
     * @param slsDt String
     * @return boolean
     */
    public static boolean isCurrentOrFuturePeriod(String thruDt, String slsDt) {
        if (ZYPCommonFunc.hasValue(thruDt) && thruDt.compareTo(slsDt) < 0) {
            return false;
        }
        return true;
    }
    // 2016/11/09 CSA-QC#15341 Add Start
    /**
     * Get Default Display COA Information
     * @param glblCmpyCd String
     * @param bizAppId String
     * @return DEF_DPLY_COA_INFOTMsg
     */
    private static DEF_DPLY_COA_INFOTMsg getDefDplyCoaInfo(String glblCmpyCd, String appFuncId) {

        DEF_DPLY_COA_INFOTMsg tMsg = new DEF_DPLY_COA_INFOTMsg();
        tMsg.glblCmpyCd.setValue(glblCmpyCd);
        tMsg.appFuncId.setValue(appFuncId);

        return (DEF_DPLY_COA_INFOTMsg) S21FastTBLAccessor.findByKey(tMsg);
    }
    // 2016/11/09 CSA-QC#15341 Add End
}

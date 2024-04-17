package business.blap.NLBL3090.common;

import static business.blap.NLBL3090.constant.NLBL3090Constant.COND_VAL_CARR_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.COND_VAL_GLBL_CMPY_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.COND_VAL_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CTAC_EML_ADDR;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_CARR_CTAC_TEL_NUM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EFF_FROM_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EFF_THRU_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EZUPTIME;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_EZUPTIMEZONE;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_LOC_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_MGR_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_FIRST_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_FIRST_NM_C;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_FIRST_NM_M;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_FIRST_NM_S;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_LAST_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_LAST_NM_C;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_LAST_NM_M;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_PSN_LAST_NM_S;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_ROWID;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_RTL_WH_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_RTL_WH_NM;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_SCHD_COORD_ASG_RELN_PK;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_SCHD_COORD_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_ST_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_SUPV_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_COLUMN_WH_MGR_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_EEFF_THRU_DT_DEFALUT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_EFF_FROM_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_EFF_THRU_DT;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_MGR_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_SCHD_COORD_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.DB_PARAM_SUPV_PSN_CD;
import static business.blap.NLBL3090.constant.NLBL3090Constant.MAX_DATE;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1232I;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1343E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1344E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1345E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.NLBM1346E;
import static business.blap.NLBL3090.constant.NLBL3090Constant.OTBD_CARR_SQL001;
import static business.blap.NLBL3090.constant.NLBL3090Constant.S21_PSN_SQL001;
import static business.blap.NLBL3090.constant.NLBL3090Constant.ZZM9037E;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.blap.NLBL3090.NLBL3090CMsg;
import business.blap.NLBL3090.NLBL3090Query;
import business.blap.NLBL3090.NLBL3090SMsg;
import business.blap.NLBL3090.NLBL3090_ASMsg;
import business.blap.NLBL3090.NLBL3090_BSMsg;
import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.SCHD_COORD_ASG_RELNTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public class NLBL3090CommonLogic {
    /**
     * <pre>
     * Search Assign.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     * @param glblCmpyCd String
     */
    public static void searchAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg, String glblCmpyCd) {
        ZYPTableUtil.clear(cMsg.A);
        ZYPTableUtil.clear(sMsg.A);
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_H1);
        ssmParam.put(DB_PARAM_MGR_PSN_CD, cMsg.mgrPsnCd_H1);
        ssmParam.put(DB_PARAM_SUPV_PSN_CD, cMsg.supvPsnCd_H1);
        ssmParam.put(DB_PARAM_SCHD_COORD_PSN_CD, cMsg.schdCoordPsnCd_H1);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, cMsg.effFromDt_H1);
        ssmParam.put(DB_PARAM_EFF_THRU_DT, cMsg.effThruDt_H1);
        ssmParam.put(DB_PARAM_EEFF_THRU_DT_DEFALUT, MAX_DATE);

        // Execute
        S21SsmEZDResult result = NLBL3090Query.getInstance().searchAssign(ssmParam);

        if (result.isCodeNormal()) {
            int addLineCount = 0;
            String strMg = null;
            String strSv = null;
            String strCo = null;
            int mgCnt = 0;
            int svCnt = 0;
            int coCnt = 0;
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.A.length() - 1) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).mgrPsnCd_AM, (String) recode.get(DB_COLUMN_MGR_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fullPsnNm_AM, connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM_M), (String) recode.get(DB_COLUMN_PSN_LAST_NM_M)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).supvPsnCd_AS, (String) recode.get(DB_COLUMN_SUPV_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fullPsnNm_AS, connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM_S), (String) recode.get(DB_COLUMN_PSN_LAST_NM_S)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordPsnCd_AC, (String) recode.get(DB_COLUMN_SCHD_COORD_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).fullPsnNm_AC, connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM_C), (String) recode.get(DB_COLUMN_PSN_LAST_NM_C)));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).stCd_A, (String) recode.get(DB_COLUMN_ST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effFromDt_A, (String) recode.get(DB_COLUMN_EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).effThruDt_A, (String) recode.get(DB_COLUMN_EFF_THRU_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCd_A, (String) recode.get(DB_COLUMN_CARR_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).locNm_A, (String) recode.get(DB_COLUMN_LOC_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCtacEmlAddr_A, (String) recode.get(DB_COLUMN_CARR_CTAC_EML_ADDR));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).carrCtacTelNum_A, (String) recode.get(DB_COLUMN_CARR_CTAC_TEL_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).rtlWhCd_A, (String) recode.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).schdCoordAsgRelnPk_A, (BigDecimal) recode.get(DB_COLUMN_SCHD_COORD_ASG_RELN_PK));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTs_A, (String) recode.get(DB_COLUMN_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.A.no(i).xxRqstTz_A, (String) recode.get(DB_COLUMN_EZUPTIMEZONE));

                // set visibility Grouping Number by Manager and Supervisor
                // A1 : RowNum (Add Line is -1)
                // A2 : Group Number by Manager
                // A3 : Group Number by Manager and Supervisor (reset by manager)
                if (i == 0) {
                    strMg = sMsg.A.no(i).mgrPsnCd_AM.getValue();
                    strSv = sMsg.A.no(i).supvPsnCd_AS.getValue();
                    strCo = sMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                    mgCnt = 1;
                    svCnt = 1;
                    coCnt = 1;

                } else {
                    if (strMg.equals(sMsg.A.no(i).mgrPsnCd_AM.getValue())) {

                        if (strSv.equals(sMsg.A.no(i).supvPsnCd_AS.getValue())) {

                            if (!strCo.equals(sMsg.A.no(i).schdCoordPsnCd_AC.getValue())) {
                                strCo = sMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                                coCnt++;
                            }
                        } else {
                            strSv = sMsg.A.no(i).supvPsnCd_AS.getValue();
                            strCo = sMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                            svCnt++;
                            coCnt = 1;

                        }
                    } else {
                        strMg = sMsg.A.no(i).mgrPsnCd_AM.getValue();
                        strSv = sMsg.A.no(i).supvPsnCd_AS.getValue();
                        strCo = sMsg.A.no(i).schdCoordPsnCd_AC.getValue();
                        mgCnt++;
                        svCnt = 1;
                        coCnt = 1;
                    }
                }
                sMsg.A.no(i).xxNewRowNum_A1.setValue(i);
                sMsg.A.no(i).xxNewRowNum_A2.setValue(mgCnt);
                sMsg.A.no(i).xxNewRowNum_A3.setValue(svCnt);
                sMsg.A.no(i).xxNewRowNum_A4.setValue(coCnt);

                addLineCount++;
            }
            sMsg.A.setValidCount(addLineCount);
            cMsg.xxPageShowFromNum_P1.setValue(BigDecimal.ONE);
        } else {
            cMsg.setMessageInfo(NLBM1232I);
//            cMsg.rtlWhCd_H1.setErrorInfo(1, NLBM1063E);
//            // blank row
//            sMsg.A.no(0).xxNewRowNum_A1.setValue(-1);
//            sMsg.A.no(0).xxNewRowNum_A2.setValue(1);
//            sMsg.A.no(0).xxNewRowNum_A3.setValue(1);
//            ZYPEZDItemValueSetter.setValue(sMsg.A.no(0).rtlWhCd_A, cMsg.rtlWhCd_H1);
//            sMsg.A.setValidCount(1);
//            cMsg.xxPageShowFromNum_P1.setValue(BigDecimal.ZERO);
//            cMsg.xxPageShowToNum_P1.setValue(BigDecimal.ZERO);
//            cMsg.xxPageShowOfNum_P1.setValue(BigDecimal.ZERO);
        }
        copyFromSMsgOntoCmsgAssign(cMsg, sMsg);
    }

    /**
     * <pre>
     * Search Coordination.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     * @param glblCmpyCd String
     * @param tabFlg boolean
     * @param coordPsnCdList List<String>
     */
    public static void searchCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg, String glblCmpyCd, boolean tabFlg, List<String> coordPsnCdList) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);

        ZYPTableUtil.clear(cMsg.B);
        ZYPTableUtil.clear(sMsg.B);

        setCheckBoxForAssignTab(sMsg);
        List<Map> schdCoordPsnList = getSchdCoordPsnCdListSelectedInAssignTab(sMsg);
        List<String> schdCoordPsnCdList = new ArrayList<String>();
        for (Map<String, String> m : schdCoordPsnList) {
            schdCoordPsnCdList.add(m.get("coordPsnCd"));
        }

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_H1);
        ssmParam.put(DB_PARAM_SCHD_COORD_PSN_CD, cMsg.schdCoordPsnCd_H1);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, cMsg.effFromDt_H1);
        ssmParam.put(DB_PARAM_EFF_THRU_DT, cMsg.effThruDt_H1);
        ssmParam.put(DB_PARAM_EEFF_THRU_DT_DEFALUT, MAX_DATE);
        ssmParam.put("schdCoordPsnCdList", schdCoordPsnCdList);
        if (tabFlg) {
            ssmParam.put("schdCoordPsnCdList", coordPsnCdList);
        } else {
            ssmParam.put("schdCoordPsnCdList", schdCoordPsnCdList);
        }

        // Execute
        S21SsmEZDResult result = NLBL3090Query.getInstance().searchCoordination(ssmParam);

        if (result.isCodeNormal()) {
            int addLineCount = 0;
            String strCo = null;
            int coCnt = 0;
            List<Map> resultMap = (List<Map>) result.getResultObject();
            for (int i = 0; i < resultMap.size(); i++) {
                if ((sMsg.B.length() - 1) <= i) {
                    break;
                }
                Map<String, Object> recode = (Map<String, Object>) resultMap.get(i);

                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).schdCoordPsnCd_BC, (String) recode.get(DB_COLUMN_SCHD_COORD_PSN_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).fullPsnNm_BC, connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM_C), (String) recode.get(DB_COLUMN_PSN_LAST_NM_C)));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).stCd_B, (String) recode.get(DB_COLUMN_ST_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).effFromDt_B, (String) recode.get(DB_COLUMN_EFF_FROM_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).effThruDt_B, (String) recode.get(DB_COLUMN_EFF_THRU_DT));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrCd_B, (String) recode.get(DB_COLUMN_CARR_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).locNm_B, (String) recode.get(DB_COLUMN_LOC_NM));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrCtacEmlAddr_B, (String) recode.get(DB_COLUMN_CARR_CTAC_EML_ADDR));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).carrCtacTelNum_B, (String) recode.get(DB_COLUMN_CARR_CTAC_TEL_NUM));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).rtlWhCd_B, (String) recode.get(DB_COLUMN_RTL_WH_CD));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRqstTs_B, (String) recode.get(DB_COLUMN_EZUPTIME));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRqstTz_B, (String) recode.get(DB_COLUMN_EZUPTIMEZONE));
                ZYPEZDItemValueSetter.setValue(sMsg.B.no(i).xxRowId_B, (String) recode.get(DB_COLUMN_ROWID));

                // set visibility Grouping Number by Coordinator
                // B1 : RowNum (Add Line is -1)
                // B2 : Group Number by Coordinator
                if (i == 0) {
                    strCo = sMsg.B.no(i).schdCoordPsnCd_BC.getValue();
                    coCnt = 1;
                } else {
                    if (!strCo.equals(sMsg.B.no(i).schdCoordPsnCd_BC.getValue())) {
                        strCo = sMsg.B.no(i).schdCoordPsnCd_BC.getValue();
                        coCnt++;
                    }
                }
                sMsg.B.no(i).xxNewRowNum_B1.setValue(i);
                sMsg.B.no(i).xxNewRowNum_B2.setValue(coCnt);

                addLineCount++;
            }
            sMsg.B.setValidCount(addLineCount);
            cMsg.xxPageShowFromNum_P2.setValue(BigDecimal.ONE);
        } else {
            cMsg.setMessageInfo(NLBM1232I);

        }
        copyFromSMsgOntoCmsgCoordination(cMsg, sMsg);
    }

    /**
     * <pre>
     * get Coordination Count.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     * @param glblCmpyCd String
     * @param schdCoordPsnCd String
     * @return BigDecimal
     */
    public static BigDecimal getCoordinationCount(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg, String glblCmpyCd, String schdCoordPsnCd) {
        NLBL3090CommonLogic.copyFromCmsgOntoSmsgAssign(cMsg, sMsg);

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_H1);
        ssmParam.put(DB_PARAM_SCHD_COORD_PSN_CD, schdCoordPsnCd);
        ssmParam.put(DB_PARAM_EFF_FROM_DT, cMsg.effFromDt_H1);
        ssmParam.put(DB_PARAM_EFF_THRU_DT, cMsg.effThruDt_H1);
        ssmParam.put(DB_PARAM_EEFF_THRU_DT_DEFALUT, MAX_DATE);

        return NLBL3090Query.getInstance().getCoordinationCount(ssmParam);
    }

    /**
     * <pre>
     * setCheckBoxForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     */
    public static void setCheckBoxForAssignTab(NLBL3090SMsg sMsg) {
        int mgNo = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((mgNo != 0) && (mgNo == bizMsg.xxNewRowNum_A2.getValueInt())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AM.getValue())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
                mgNo = bizMsg.xxNewRowNum_A2.getValueInt();
            } else {
                mgNo = 0;
            }
        }
        mgNo = 0;
        int svNo = 0;
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if ((svNo != 0) && (mgNo == bizMsg.xxNewRowNum_A2.getValueInt()) && (svNo == bizMsg.xxNewRowNum_A3.getValueInt())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
            } else if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_AS.getValue())) {
                bizMsg.xxChkBox_AC.setValue(ZYPConstant.FLG_ON_Y);
                mgNo = bizMsg.xxNewRowNum_A2.getValueInt();
                svNo = bizMsg.xxNewRowNum_A3.getValueInt();
            } else {
                mgNo = 0;
                svNo = 0;
            }
        }
    }

    /**
     * <pre>
     * setCheckBoxForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return List<Map>
     */
    public static List<Map> getSchdCoordPsnCdListSelectedInAssignTab(NLBL3090SMsg sMsg) {
        List<Map> schdCoordPsnCdList = new ArrayList<Map>();
        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg dtlMsg = sMsg.A.no(i);
            if (ZYPConstant.FLG_ON_Y.equals(dtlMsg.xxChkBox_AC.getValue())) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("coordPsnCd", dtlMsg.schdCoordPsnCd_AC.getValue());
                map.put("coordPsnNm", dtlMsg.fullPsnNm_AC.getValue());
                schdCoordPsnCdList.add(map);
            }
        }
        return schdCoordPsnCdList;
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void setPagePosAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum_P1.setValue(0);
            cMsg.xxPageShowToNum_P1.setValue(0);
            cMsg.xxPageShowOfNum_P1.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum_P1)) {
            startRowCount = cMsg.xxPageShowFromNum_P1.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum_P1.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum_P1.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum_P1.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum_P1.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum_P1.setValue(cMsg.xxPageShowFromNum_P1.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum_P1.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum_P1.setValue(allRowCount);
    }

    private static int getLastPageStartCount(int allRowCount, int pageRowCount) {
        if ((allRowCount <= 0) || (pageRowCount <= 0)) {
            return 0;
        }
        if (allRowCount <= pageRowCount) {
            return 1;
        }
        if (allRowCount % pageRowCount == 0) {
            return allRowCount - pageRowCount + 1;
        }
        return ((int) (allRowCount / pageRowCount)) * pageRowCount + 1;
    }

    /**
     * <pre>
     * Set page pos.
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void setPagePosCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if ((cMsg.B.length() == 0) || (sMsg.B.length() == 0) || (sMsg.B.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum_P2.setValue(0);
            cMsg.xxPageShowToNum_P2.setValue(0);
            cMsg.xxPageShowOfNum_P2.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum_P2)) {
            startRowCount = cMsg.xxPageShowFromNum_P2.getValueInt();
        }
        int allRowCount = sMsg.B.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum_P2.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum_P2.setValue(getLastPageStartCount(allRowCount, cMsg.B.length()));
        } else {
            if ((startRowCount % cMsg.B.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.B.length()) + 1;
            }
            cMsg.xxPageShowFromNum_P2.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum_P2.getValueInt() + cMsg.B.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum_P2.setValue(cMsg.xxPageShowFromNum_P2.getValueInt() + cMsg.B.length() - 1);
        } else {
            cMsg.xxPageShowToNum_P2.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum_P2.setValue(allRowCount);
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void copyFromSMsgOntoCmsgAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.A);
        setPagePosAssign(cMsg, sMsg);

        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_P1.getValueInt() - 1; i < cMsg.xxPageShowToNum_P1.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From Smsg Onto Cmsg
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param sMsg NLBL3090SMsg
     */
    public static void copyFromSMsgOntoCmsgCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        ZYPTableUtil.clear(cMsg.B);
        setPagePosCoordination(cMsg, sMsg);

        if (0 < sMsg.B.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum_P2.getValueInt() - 1; i < cMsg.xxPageShowToNum_P2.getValueInt(); i++) {
                EZDMsg.copy(sMsg.B.no(i), null, cMsg.B.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.B.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @param cMsg NLBL3090CMsg
     */
    public static void copyFromCmsgOntoSmsgAssign(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (cMsg.A.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum_P1.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum_P1.getValueInt(); j++, k++) {
            EZDMsg.copy(cMsg.A.no(k), null, sMsg.A.no(j), null);
        }
    }

    /**
     * <pre>
     * updateValueForInvisibleFieldsAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     */
    public static void updateValueForInvisibleFieldsAssignTab(NLBL3090SMsg sMsg) {
        int prevMngRowNum = 0;
        int prevSupvRowNum = 0;
        int prevCoordRowNum = 0;
        String prevMngPsnCd = "";
        String prevSupvPsnCd = "";
        String prevCoordPsnCd = "";

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg bizMsg = sMsg.A.no(i);
            if (prevMngRowNum == bizMsg.xxNewRowNum_A2.getValueInt()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.mgrPsnCd_AM, prevMngPsnCd);

                if (prevSupvRowNum == bizMsg.xxNewRowNum_A3.getValueInt()) {
                    ZYPEZDItemValueSetter.setValue(bizMsg.supvPsnCd_AS, prevSupvPsnCd);

                    if (prevCoordRowNum == bizMsg.xxNewRowNum_A4.getValueInt()) {
                        ZYPEZDItemValueSetter.setValue(bizMsg.schdCoordPsnCd_AC, prevCoordPsnCd);
                    } else {
                        prevCoordRowNum = bizMsg.xxNewRowNum_A4.getValueInt();
                        prevCoordPsnCd = bizMsg.schdCoordPsnCd_AC.getValue();
                    }

                } else {
                    prevSupvRowNum = bizMsg.xxNewRowNum_A3.getValueInt();
                    prevSupvPsnCd = bizMsg.supvPsnCd_AS.getValue();

                    prevCoordRowNum = bizMsg.xxNewRowNum_A4.getValueInt();
                    prevCoordPsnCd = bizMsg.schdCoordPsnCd_AC.getValue();
                }

            } else {
                prevMngRowNum = bizMsg.xxNewRowNum_A2.getValueInt();
                prevMngPsnCd = bizMsg.mgrPsnCd_AM.getValue();

                prevSupvRowNum = bizMsg.xxNewRowNum_A3.getValueInt();
                prevSupvPsnCd = bizMsg.supvPsnCd_AS.getValue();

                prevCoordRowNum = bizMsg.xxNewRowNum_A4.getValueInt();
                prevCoordPsnCd = bizMsg.schdCoordPsnCd_AC.getValue();
            }
        }
    }

    /**
     * <pre>
     * updateValueForInvisibleFieldsCoordinationTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     */
    public static void updateValueForInvisibleFieldsCoordinationTab(NLBL3090SMsg sMsg) {
        int prevCoordRowNum = 0;
        String prevCoordPsnCd = "";

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg dtlMsg = sMsg.B.no(i);
            if (prevCoordRowNum == dtlMsg.xxNewRowNum_B2.getValueInt()) {
                ZYPEZDItemValueSetter.setValue(dtlMsg.schdCoordPsnCd_BC, prevCoordPsnCd);

            } else {
                prevCoordRowNum = dtlMsg.xxNewRowNum_B2.getValueInt();
                prevCoordPsnCd = dtlMsg.schdCoordPsnCd_BC.getValue();
            }
        }
    }

    /**
     * <pre>
     * checkMasterExistenceAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @param glblCmpyCd String
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkMasterExistenceAssignTab(NLBL3090SMsg sMsg, String glblCmpyCd) {
        int firstErrIndex = -1;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg dtlMsg = sMsg.A.no(i);
            // Manager
            if (!NLBL3090Query.getInstance().existS21PsnV(glblCmpyCd, dtlMsg.mgrPsnCd_AM.getValue())) {
                dtlMsg.mgrPsnCd_AM.setErrorInfo(1, NLBM1346E, new String[] {"Manager Code"});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
            // Supervisor
            if (!NLBL3090Query.getInstance().existS21PsnV(glblCmpyCd, dtlMsg.supvPsnCd_AS.getValue())) {
                dtlMsg.supvPsnCd_AS.setErrorInfo(1, NLBM1346E, new String[] {"Supervisor Code"});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
            // Coordinator
            if (!NLBL3090Query.getInstance().existS21PsnV(glblCmpyCd, dtlMsg.schdCoordPsnCd_AC.getValue())) {
                dtlMsg.schdCoordPsnCd_AC.setErrorInfo(1, NLBM1346E, new String[] {"Coordinator Code"});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
        }
        return firstErrIndex;
    }


    /**
     * <pre>
     * checkMasterExistenceCoordinationTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @param glblCmpyCd String
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkMasterExistenceCoordinationTab(NLBL3090SMsg sMsg, String glblCmpyCd) {
        int firstErrIndex = -1;

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg dtlMsg = sMsg.B.no(i);
            // Coordinator
            if (!NLBL3090Query.getInstance().existS21PsnV(glblCmpyCd, dtlMsg.schdCoordPsnCd_BC.getValue())) {
                dtlMsg.schdCoordPsnCd_BC.setErrorInfo(1, NLBM1346E, new String[] {"Coordinator Code"});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
            // ST
            if (!NLBL3090Query.getInstance().existSt(glblCmpyCd, dtlMsg.stCd_B.getValue())) {
                dtlMsg.stCd_B.setErrorInfo(1, NLBM1346E, new String[] {"State"});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
            // Carrier
            if (ZYPCommonFunc.hasValue(dtlMsg.carrCd_B)) {
                if (!NLBL3090Query.getInstance().existCarrier(glblCmpyCd, dtlMsg.carrCd_B.getValue())) {
                    dtlMsg.carrCd_B.setErrorInfo(1, NLBM1346E, new String[] {"Carrier Code" });
                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                }
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicationForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicationForAssignTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;
        int checkResult;

        checkResult = checkDuplicatedMgrPsnCdForAssignTab(sMsg);
        if (checkResult != -1) {
            firstErrIndex = checkResult;
        }

        checkResult = checkDuplicatedSupvPsnCdForAssignTab(sMsg);
        if (checkResult != -1) {
            if (firstErrIndex == -1 || checkResult < firstErrIndex) {
                firstErrIndex = checkResult;
            }
        }

        checkResult = checkDuplicatedSchdCoodPsnCdForAssignTab(sMsg);
        if (checkResult != -1) {
            if (firstErrIndex == -1 || checkResult < firstErrIndex) {
                firstErrIndex = checkResult;
            }
        }

        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicatedMgrPsnCdForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicatedMgrPsnCdForAssignTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.A.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            int checkMgrRowNum = sMsg.A.no(i).xxNewRowNum_A2.getValueInt();
            String checkMgrPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).mgrPsnCd_AM.getValue());

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                int compareMgrRowNum = sMsg.A.no(j).xxNewRowNum_A2.getValueInt();
                String compareMgrPsnCd = sMsg.A.no(j).mgrPsnCd_AM.getValue();

                if (checkMgrRowNum != compareMgrRowNum && checkMgrPsnCd.equals(compareMgrPsnCd)) {
                    sMsg.A.no(i).mgrPsnCd_AM.setErrorInfo(1, NLBM1344E, new String[] {"Manager"});

                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    break;
                }
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicatedSupvPsnCdForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicatedSupvPsnCdForAssignTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.A.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            int checkMgrRowNum = sMsg.A.no(i).xxNewRowNum_A2.getValueInt();
            int checkSupvRowNum = sMsg.A.no(i).xxNewRowNum_A3.getValueInt();
            String checkSupvPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).supvPsnCd_AS.getValue());

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {

                if (i == j) {
                    continue;
                }

                int compareMgrRowNum = sMsg.A.no(j).xxNewRowNum_A2.getValueInt();
                int compareSupvRowNum = sMsg.A.no(j).xxNewRowNum_A3.getValueInt();
                String compareSupvPsnCd = sMsg.A.no(j).supvPsnCd_AS.getValue();

                if (checkMgrRowNum == compareMgrRowNum
                        && checkSupvRowNum != compareSupvRowNum
                        && checkSupvPsnCd.equals(compareSupvPsnCd)) {
                    sMsg.A.no(i).supvPsnCd_AS.setErrorInfo(1, NLBM1343E, new String[] {"Supervisor", "Manager"});

                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    break;
                }
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicatedSchdCoodPsnCdForAssignTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicatedSchdCoodPsnCdForAssignTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.A.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            int checkMgrRowNum = sMsg.A.no(i).xxNewRowNum_A2.getValueInt();
            int checkSupvRowNum = sMsg.A.no(i).xxNewRowNum_A3.getValueInt();
            int checkCoordRowNum = sMsg.A.no(i).xxNewRowNum_A4.getValueInt();
            String checkSchdCoordPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).schdCoordPsnCd_AC.getValue());

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                int compareMgrRowNum = sMsg.A.no(j).xxNewRowNum_A2.getValueInt();
                int compareSupvRowNum = sMsg.A.no(j).xxNewRowNum_A3.getValueInt();
                int compareCoordRowNum = sMsg.A.no(j).xxNewRowNum_A4.getValueInt();
                String compareSchdCoordPsnCd = sMsg.A.no(j).schdCoordPsnCd_AC.getValue();

                if (checkMgrRowNum == compareMgrRowNum
                        && checkSupvRowNum == compareSupvRowNum
                        && checkCoordRowNum != compareCoordRowNum
                        && checkSchdCoordPsnCd.equals(compareSchdCoordPsnCd)) {
                    sMsg.A.no(i).schdCoordPsnCd_AC.setErrorInfo(1, NLBM1343E, new String[] {"Coordinator", "Manager and Supervisor"});

                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    break;
                }
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicationForCoordinationTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicationForCoordinationTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;
        int checkResult;

        checkResult = checkDuplicatedSchdCoodPsnCdForCoordinationTab(sMsg);
        if (checkResult != -1) {
            firstErrIndex = checkResult;
        }

        checkResult = checkDuplicatedStCdForCoordinationTab(sMsg);
        if (checkResult != -1) {
            if (firstErrIndex == -1 || checkResult < firstErrIndex) {
                firstErrIndex = checkResult;
            }
        }

        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicatedSchdCoodPsnCdForCoordinationTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicatedSchdCoodPsnCdForCoordinationTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.B.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            int checkCoordRowNum = sMsg.B.no(i).xxNewRowNum_B2.getValueInt();
            String checkCoordPsnCd = getBlankStringIfValueIsNull(sMsg.B.no(i).schdCoordPsnCd_BC.getValue());

            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                int compareCoordRowNum = sMsg.B.no(j).xxNewRowNum_B2.getValueInt();
                String compareCoordPsnCd = sMsg.B.no(j).schdCoordPsnCd_BC.getValue();

                if (checkCoordRowNum != compareCoordRowNum && checkCoordPsnCd.equals(compareCoordPsnCd)) {
                    sMsg.B.no(i).schdCoordPsnCd_BC.setErrorInfo(1, NLBM1344E, new String[] {"Coordinator"});

                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    break;
                }
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicatedStCdForCoordinationTab
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicatedStCdForCoordinationTab(NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.B.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            int checkCoordRowNum = sMsg.B.no(i).xxNewRowNum_B2.getValueInt();

            String checkStCd = getBlankStringIfValueIsNull(sMsg.B.no(i).stCd_B.getValue());

            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
                if (i == j) {
                    continue;
                }

                int compareCoordRowNum = sMsg.B.no(j).xxNewRowNum_B2.getValueInt();
                String compareStCd = sMsg.B.no(j).stCd_B.getValue();

                if (checkCoordRowNum == compareCoordRowNum && checkStCd.equals(compareStCd)) {
                    sMsg.B.no(i).stCd_B.setErrorInfo(1, NLBM1343E, new String[] {"State", "Coordinator"});

                    if (firstErrIndex == -1) {
                        firstErrIndex = i;
                    }
                    break;
                }
            }
        }
        return firstErrIndex;
    }

//    /**
//     * <pre>
//     * checkDuplicatedMgrPsnCdForAdd
//     * </pre>
//     * @param sMsg NLBL3090SMsg
//     * @param mgrPsnCd EZDCStringItem
//     * @return boolean (true : No error / false : Error)
//     */
//    public static boolean checkDuplicatedMgrPsnCdForAdd(NLBL3090SMsg sMsg, EZDCStringItem mgrPsnCd) {
//        boolean noErrFlg = true;
//
//        if (!ZYPCommonFunc.hasValue(mgrPsnCd)) {
//            return noErrFlg;
//        }
//
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//            String compareMgrPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).mgrPsnCd_AM.getValue());
//
//            if (compareMgrPsnCd.equals(mgrPsnCd.getValue())) {
//                noErrFlg = false;
//                mgrPsnCd.setErrorInfo(1, NLBM1344E, new String[] {"Manager"});
//                break;
//            }
//        }
//        return noErrFlg;
//    }
//
//    /**
//     * <pre>
//     * checkDuplicatedSupvPsnCdForAdd
//     * </pre>
//     * @param sMsg NLBL3090SMsg
//     * @param mgrPsnCd String
//     * @param supvPsnCd EZDCStringItem
//     * @return boolean (true : No error / false : Error)
//     */
//    public static boolean checkDuplicatedSupvPsnCdForAdd(NLBL3090SMsg sMsg, String mgrPsnCd, EZDCStringItem supvPsnCd) {
//        boolean noErrFlg = true;
//
//        if (!ZYPCommonFunc.hasValue(supvPsnCd)) {
//            return noErrFlg;
//        }
//
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//            String compareMgrPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).mgrPsnCd_AM.getValue());
//            String compareSupvPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).supvPsnCd_AS.getValue());
//
//            if (compareMgrPsnCd.equals(mgrPsnCd) && compareSupvPsnCd.equals(supvPsnCd.getValue())) {
//                noErrFlg = false;
//                supvPsnCd.setErrorInfo(1, NLBM1344E, new String[] {"Supervisor"});
//                break;
//            }
//        }
//        return noErrFlg;
//    }
//
//    /**
//     * <pre>
//     * checkDuplicatedSchdCoodPsnCdForAdd
//     * </pre>
//     * @param sMsg NLBL3090SMsg
//     * @param mgrPsnCd String
//     * @param supvPsnCd String
//     * @param schdCoordPsnCd EZDCStringItem
//     * @return boolean (true : No error / false : Error)
//     */
//    public static boolean checkDuplicatedSchdCoodPsnCdForAdd(NLBL3090SMsg sMsg, String mgrPsnCd, String supvPsnCd, EZDCStringItem schdCoordPsnCd) {
//        boolean noErrFlg = true;
//
//        if (!ZYPCommonFunc.hasValue(schdCoordPsnCd)) {
//            return noErrFlg;
//        }
//
//        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
//
//            String compareMgrPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).mgrPsnCd_AM.getValue());
//            String compareSupvPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).supvPsnCd_AS.getValue());
//            String compareSchdCoordPsnCd = getBlankStringIfValueIsNull(sMsg.A.no(i).schdCoordPsnCd_AC.getValue());
//
//            if (compareMgrPsnCd.equals(mgrPsnCd) && compareSupvPsnCd.equals(supvPsnCd) && compareSchdCoordPsnCd.equals(schdCoordPsnCd.getValue())) {
//                noErrFlg = false;
//                schdCoordPsnCd.setErrorInfo(1, NLBM1344E, new String[] {"Coordinator"});
//                break;
//            }
//        }
//        return noErrFlg;
//    }

    /**
     * <pre>
     * checkDuplicationWithDBForAssignTab
     * </pre>
     * @param glblCmpyCd String
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicationWithDBForAssignTab(String glblCmpyCd, NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.A.getValidCount() == 0) {
            return firstErrIndex;
        }

        List<SCHD_COORD_ASG_RELNTMsg> schdCoordAsgRelnTMsgList = createAssignTMsgListForDuplicationCheck(glblCmpyCd, sMsg);

        if (schdCoordAsgRelnTMsgList == null || schdCoordAsgRelnTMsgList.size() == 0) {
            return firstErrIndex;
        }

        int prevMgrRowNum = 0;
        int prevSupvRowNum = 0;
        int prevCoordRowNum = 0;

        for (int i = 0; i < sMsg.A.getValidCount(); i++) {
            NLBL3090_ASMsg dtlMsg = sMsg.A.no(i);

            if (prevMgrRowNum != sMsg.A.no(i).xxNewRowNum_A2.getValueInt()
                    || prevSupvRowNum != sMsg.A.no(i).xxNewRowNum_A3.getValueInt()
                    || prevCoordRowNum != sMsg.A.no(i).xxNewRowNum_A4.getValueInt()) {

//                S21SsmEZDResult ssmResult = NLBL3090Query.getInstance().countSchdCoordAsgReln(glblCmpyCd, dtlMsg.schdCoordAsgRelnPk_A.getValue(), dtlMsg.rtlWhCd_A.getValue(), dtlMsg.mgrPsnCd_AM.getValue(), dtlMsg.supvPsnCd_AS.getValue(), dtlMsg.schdCoordPsnCd_AC.getValue());
//                if ((Integer) ssmResult.getResultObject() > 0) {
//                    dtlMsg.mgrPsnCd_AM.setErrorInfo(1, NLBM1345E, new String[] {dtlMsg.rtlWhCd_A.getValue()});
//                    dtlMsg.supvPsnCd_AS.setErrorInfo(1, NLBM1345E, new String[] {dtlMsg.rtlWhCd_A.getValue()});
//                    dtlMsg.schdCoordPsnCd_AC.setErrorInfo(1, NLBM1345E, new String[] {dtlMsg.rtlWhCd_A.getValue()});
//                    if (firstErrIndex == -1) {
//                        firstErrIndex = i;
//                    }
//                }

                for (int j = 0; j < schdCoordAsgRelnTMsgList.size(); j++) {
                    SCHD_COORD_ASG_RELNTMsg tMsg = schdCoordAsgRelnTMsgList.get(j);

                    if (ZYPCommonFunc.hasValue(dtlMsg.schdCoordAsgRelnPk_A)
                            && dtlMsg.schdCoordAsgRelnPk_A.getValue().compareTo(tMsg.schdCoordAsgRelnPk.getValue()) == 0) {
                        continue;
                    }

                    if (dtlMsg.mgrPsnCd_AM.getValue().equals(tMsg.mgrPsnCd.getValue())
                            && dtlMsg.supvPsnCd_AS.getValue().equals(tMsg.supvPsnCd.getValue())
                            && dtlMsg.schdCoordPsnCd_AC.getValue().equals(tMsg.schdCoordPsnCd.getValue())) {

                        dtlMsg.mgrPsnCd_AM.setErrorInfo(1, NLBM1345E, new String[] {"Manager, Supervisor and Coordinator", dtlMsg.rtlWhCd_A.getValue()});
                        dtlMsg.supvPsnCd_AS.setErrorInfo(1, NLBM1345E, new String[] {"Manager, Supervisor and Coordinator", dtlMsg.rtlWhCd_A.getValue()});
                        dtlMsg.schdCoordPsnCd_AC.setErrorInfo(1, NLBM1345E, new String[] {"Manager, Supervisor and Coordinator", dtlMsg.rtlWhCd_A.getValue()});
                        if (firstErrIndex == -1) {
                            firstErrIndex = i;
                        }
                        break;
                    }
                }
                prevMgrRowNum = sMsg.A.no(i).xxNewRowNum_A2.getValueInt();
                prevSupvRowNum = sMsg.A.no(i).xxNewRowNum_A3.getValueInt();
                prevCoordRowNum = sMsg.A.no(i).xxNewRowNum_A4.getValueInt();
            }
        }
        return firstErrIndex;
    }

    /**
     * <pre>
     * checkDuplicationWithDBForCoordinationTab
     * </pre>
     * @param glblCmpyCd String
     * @param sMsg NLBL3090SMsg
     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
     */
    public static int checkDuplicationWithDBForCoordinationTab(String glblCmpyCd, NLBL3090SMsg sMsg) {
        int firstErrIndex = -1;

        if (sMsg.B.getValidCount() == 0) {
            return firstErrIndex;
        }

        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
            NLBL3090_BSMsg dtlMsg = sMsg.B.no(i);

            S21SsmEZDResult ssmResult = NLBL3090Query.getInstance().countSchdCoordAsg(glblCmpyCd, dtlMsg.xxRowId_B.getValue(), dtlMsg.rtlWhCd_B.getValue(), dtlMsg.schdCoordPsnCd_BC.getValue(), dtlMsg.stCd_B.getValue(), dtlMsg.effFromDt_B.getValue());
            if ((Integer) ssmResult.getResultObject() > 0) {
                dtlMsg.schdCoordPsnCd_BC.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
                dtlMsg.stCd_B.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
                dtlMsg.effFromDt_B.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
                if (firstErrIndex == -1) {
                    firstErrIndex = i;
                }
            }
        }
        return firstErrIndex;
    }
//    /**
//     * <pre>
//     * checkDuplicationWithDBForCoordinationTab
//     * </pre>
//     * @param glblCmpyCd String
//     * @param sMsg NLBL3090SMsg
//     * @return int (-1 : No error / Other than -1 : First error index at sMsg)
//     */
//    public static int checkDuplicationWithDBForCoordinationTab(String glblCmpyCd, NLBL3090SMsg sMsg) {
//        int firstErrIndex = -1;
//
//        if (sMsg.B.getValidCount() == 0) {
//            return firstErrIndex;
//        }
//
//        List<NLBL3090_BSMsg> coordinationList = createCoordinationListForDuplicationCheck(glblCmpyCd, sMsg);
//
//        if (coordinationList == null || coordinationList.size() == 0) {
//            return firstErrIndex;
//        }
//
//        int prevCoordRowNum = 0;
//
//        for (int i = 0; i < sMsg.B.getValidCount(); i++) {
//            NLBL3090_BSMsg dtlMsg = sMsg.B.no(i);
//
//            if (prevCoordRowNum != sMsg.B.no(i).xxNewRowNum_B2.getValueInt()) {
//
//                for (int j = 0; j < coordinationList.size(); j++) {
//                    NLBL3090_BSMsg bSMsg = coordinationList.get(j);
//
//                    if (ZYPCommonFunc.hasValue(dtlMsg.xxRowId_B)
//                            && dtlMsg.xxRowId_B.getValue().equals(bSMsg.xxRowId_B.getValue())) {
//                        continue;
//                    }
//
//                    if (dtlMsg.schdCoordPsnCd_BC.getValue().equals(bSMsg.schdCoordPsnCd_BC.getValue())
//                            && dtlMsg.stCd_B.getValue().equals(bSMsg.stCd_B.getValue())
//                            && dtlMsg.effFromDt_B.getValue().equals(bSMsg.effFromDt_B.getValue())) {
//
//                        dtlMsg.schdCoordPsnCd_BC.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
//                        dtlMsg.stCd_B.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
//                        dtlMsg.effFromDt_B.setErrorInfo(1, NLBM1345E, new String[] {"Coordinator, State and Effective(From) Date", dtlMsg.rtlWhCd_B.getValue()});
//
//                        if (firstErrIndex == -1) {
//                            firstErrIndex = i;
//                        }
//                        break;
//                    }
//                }
//                prevCoordRowNum = sMsg.B.no(i).xxNewRowNum_B2.getValueInt();
//            }
//        }
//        return firstErrIndex;
//    }

    /**
     * <pre>
     * createAssignListForDuplicationCheck
     * </pre>
     * @param glblCmpyCd String
     * @param sMsg NLBL3090SMsg
     * @return List<SCHD_COORD_ASG_RELNTMsg>
     */
    @SuppressWarnings("unchecked")
    public static List<SCHD_COORD_ASG_RELNTMsg> createAssignTMsgListForDuplicationCheck(String glblCmpyCd, NLBL3090SMsg sMsg) {

        if (sMsg.A.getValidCount() == 0) {
            return null;
        }

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rtlWhCd", sMsg.A.no(0).rtlWhCd_A.getValue());

        S21SsmEZDResult ssmResult = NLBL3090Query.getInstance().getAssignTMsgListForDuplicationCheck(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }

        List<SCHD_COORD_ASG_RELNTMsg> schdCoordAsgRelnTMsgList = (List<SCHD_COORD_ASG_RELNTMsg>) ssmResult.getResultObject();
        List<SCHD_COORD_ASG_RELNTMsg> schdCoordAsgRelnTMsgListForDuplicationCheck = new ArrayList<SCHD_COORD_ASG_RELNTMsg>();

        for (int i = 0; i < schdCoordAsgRelnTMsgList.size(); i++) {
            SCHD_COORD_ASG_RELNTMsg tMsg = schdCoordAsgRelnTMsgList.get(i);

            for (int j = 0; j < sMsg.A.getValidCount(); j++) {
                NLBL3090_ASMsg dtlMsg = sMsg.A.no(j);
                if (ZYPCommonFunc.hasValue(dtlMsg.schdCoordAsgRelnPk_A) && tMsg.schdCoordAsgRelnPk.getValue().compareTo(dtlMsg.schdCoordAsgRelnPk_A.getValue()) == 0) {
                    ZYPEZDItemValueSetter.setValue(tMsg.mgrPsnCd, dtlMsg.mgrPsnCd_AM);
                    ZYPEZDItemValueSetter.setValue(tMsg.supvPsnCd, dtlMsg.supvPsnCd_AS);
                    ZYPEZDItemValueSetter.setValue(tMsg.schdCoordPsnCd, dtlMsg.schdCoordPsnCd_AC);
                    break;
                }
            }
            schdCoordAsgRelnTMsgListForDuplicationCheck.add(tMsg);
        }
        return schdCoordAsgRelnTMsgListForDuplicationCheck;
    }

    /**
     * <pre>
     * createCoordinationListForDuplicationCheck
     * </pre>
     * @param glblCmpyCd String
     * @param sMsg NLBL3090SMsg
     * @return List<NLBL3090_BSMsg>
     */
    @SuppressWarnings("unchecked")
    public static List<NLBL3090_BSMsg> createCoordinationListForDuplicationCheck(String glblCmpyCd, NLBL3090SMsg sMsg) {

        if (sMsg.B.getValidCount() == 0) {
            return null;
        }

        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("rtlWhCd", sMsg.B.no(0).rtlWhCd_B.getValue());

        S21SsmEZDResult ssmResult = NLBL3090Query.getInstance().getCoordinationListForDuplicationCheck(queryParam);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (List<NLBL3090_BSMsg>) ssmResult.getResultObject();
//
//        List<NLBL3090_BSMsg> coordinationList = (List<NLBL3090_BSMsg>) ssmResult.getResultObject();
//        List<NLBL3090_BSMsg> coordinationListForDuplicationCheck = new ArrayList<NLBL3090_BSMsg>();
//
//        for (int i = 0; i < coordinationList.size(); i++) {
//            NLBL3090_BSMsg bSMsg = coordinationList.get(i);
//
//            for (int j = 0; j < sMsg.B.getValidCount(); j++) {
//                NLBL3090_BSMsg dtlMsg = sMsg.B.no(j);
//                if (ZYPCommonFunc.hasValue(dtlMsg.xxRowId_B) && dtlMsg.xxRowId_B.getValue().equals(bSMsg.xxRowId_B.getValue())) {
//
//                    ZYPEZDItemValueSetter.setValue(bSMsg.schdCoordPsnCd_BC, dtlMsg.schdCoordPsnCd_BC);
//                    ZYPEZDItemValueSetter.setValue(bSMsg.stCd_B, dtlMsg.stCd_B);
//                    ZYPEZDItemValueSetter.setValue(bSMsg.effFromDt_B, dtlMsg.effFromDt_B);
//                    break;
//                }
//            }
//            coordinationListForDuplicationCheck.add(bSMsg);
//        }
//        return coordinationListForDuplicationCheck;
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param sMsg NLBL3090SMsg
     * @param cMsg NLBL3090CMsg
     */
    public static void copyFromCmsgOntoSmsgCoordination(NLBL3090CMsg cMsg, NLBL3090SMsg sMsg) {
        if (cMsg.B.getValidCount() == 0) {
            return;
        }
        for (int j = cMsg.xxPageShowFromNum_P2.getValueInt() - 1, k = 0; j < cMsg.xxPageShowToNum_P2.getValueInt(); j++, k++) {
            EZDMsg.copy(cMsg.B.no(k), null, sMsg.B.no(j), null);
        }
    }

    /**
     * <pre>
     * get Blank String if value is null
     * </pre>
     * @param str String
     * @return connecting string
     */
    public static String getBlankStringIfValueIsNull(String str) {
        String ret = "";
        if (str == null) {
            ret = "";
        } else {
            ret = str;
        }
        return ret;
    }

    /**
     * <pre>
     * connectString
     * </pre>
     * @param str1 String
     * @param str2 String
     * @return connecting string
     */
    public static String connectString(String str1, String str2) {
        String ret = "";
        if (str1 != null) {
            ret = str1;
        }
        if (str2 != null) {
            ret += " " + str2;
        }
        return ret;
    }

    /**
     * <pre>
     * get person name from S21_PSN_V
     * </pre>
     * @param psnCd String
     * @param glblCmpyCd String
     * @return psnNm string
     */
    public static  String getPsnNm(String psnCd, String glblCmpyCd) {

        String psnNm = "";

        S21_PSN_VTMsg cond = new S21_PSN_VTMsg();

        cond.setSQLID(S21_PSN_SQL001);
        cond.setConditionValue(COND_VAL_GLBL_CMPY_CD, glblCmpyCd);
        cond.setConditionValue(COND_VAL_PSN_CD, psnCd);
        S21_PSN_VTMsgArray outMsg = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(cond);

        if (outMsg.length() != 0) {
            psnNm = outMsg.no(0).fullPsnNm.getValue();
        }

        return psnNm;
    }

    /**
     * <pre>
     * get carrier name from OTBD_CARR_V
     * </pre>
     * @param carrCd String
     * @param glblCmpyCd String
     * @return psnNm string
     */
    public static  String getCarrNm(String carrCd, String glblCmpyCd) {

        String carrNm = "";

        OTBD_CARR_VTMsg cond = new OTBD_CARR_VTMsg();

        cond.setSQLID(OTBD_CARR_SQL001);
        cond.setConditionValue(COND_VAL_GLBL_CMPY_CD, glblCmpyCd);
        cond.setConditionValue(COND_VAL_CARR_CD, carrCd);
        OTBD_CARR_VTMsgArray outMsg = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(cond);

        if (outMsg.length() != 0) {
            carrNm = outMsg.no(0).carrNm.getValue();
        }

        return carrNm;
    }


    /**
     * <pre>
     * Search Header
     * </pre>
     * @param cMsg NLBL3090CMsg
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean searchHeader(NLBL3090CMsg cMsg, String glblCmpyCd) {

        boolean noErrFlg = true;

        // WH
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMsg.rtlWhCd_H1);
        S21SsmEZDResult result = NLBL3090Query.getInstance().searchWh(ssmParam);
        if (result.isCodeNormal()) {
            List<Map> resultMap = (List<Map>) result.getResultObject();
            Map<String, Object> recode = (Map<String, Object>) resultMap.get(0);
            ZYPEZDItemValueSetter.setValue(cMsg.rtlWhNm_H1, (String) recode.get(DB_COLUMN_RTL_WH_NM));
            ZYPEZDItemValueSetter.setValue(cMsg.psnCd_H1, (String) recode.get(DB_COLUMN_WH_MGR_PSN_CD));
            ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_H1, NLBL3090CommonLogic.connectString((String) recode.get(DB_COLUMN_PSN_FIRST_NM), (String) recode.get(DB_COLUMN_PSN_LAST_NM)));
        } else {
            noErrFlg = false;
            cMsg.rtlWhNm_H1.clear();
            cMsg.psnCd_H1.clear();
            cMsg.fullPsnNm_H1.clear();
            cMsg.rtlWhCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Warehouse Code"});
        }

        // Manager
        if (ZYPCommonFunc.hasValue(cMsg.mgrPsnCd_H1)) {
            String mgrNm = NLBL3090CommonLogic.getPsnNm(cMsg.mgrPsnCd_H1.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(mgrNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HM, mgrNm);
            } else {
                noErrFlg = false;
                cMsg.fullPsnNm_HM.clear();
                cMsg.mgrPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Manager Code"});
            }
        } else {
            cMsg.fullPsnNm_HM.clear();
        }

        // Supervisor
        if (ZYPCommonFunc.hasValue(cMsg.supvPsnCd_H1)) {
            String supvNm = NLBL3090CommonLogic.getPsnNm(cMsg.supvPsnCd_H1.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(supvNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HS, supvNm);
            } else {
                noErrFlg = false;
                cMsg.fullPsnNm_HS.clear();
                cMsg.supvPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Supervisor Code"});
            }
        } else {
            cMsg.fullPsnNm_HS.clear();
        }

        // Coordinator
        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd_H1)) {
            String schdCoordNm = NLBL3090CommonLogic.getPsnNm(cMsg.schdCoordPsnCd_H1.getValue(), glblCmpyCd);
            if (ZYPCommonFunc.hasValue(schdCoordNm)) {
                ZYPEZDItemValueSetter.setValue(cMsg.fullPsnNm_HC, schdCoordNm);
            } else {
                noErrFlg = false;
                cMsg.fullPsnNm_HC.clear();
                cMsg.schdCoordPsnCd_H1.setErrorInfo(1, NLBM1346E, new String[] {"Coordinator Code"});
            }
        } else {
            cMsg.fullPsnNm_HC.clear();
        }

        if (!noErrFlg) {
            cMsg.setMessageInfo(ZZM9037E);
        }
        return noErrFlg;
    }
}

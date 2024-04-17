/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL2040.common;

import static business.blap.NLAL2040.constant.NLAL2040Constant.COA_MDSE_TP_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.COA_PROD_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_FROM_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.GLBL_CMPY_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.INVTY_OWNR_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MAX_DISP_LINE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MDL_ID;
import static business.blap.NLAL2040.constant.NLAL2040Constant.MTR_REQ_MDL_FLG;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NLAL2040_DEF_EFF_THRU_DT;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NMAM0038I;
import static business.blap.NLAL2040.constant.NLAL2040Constant.NMAM8181W;
import static business.blap.NLAL2040.constant.NLAL2040Constant.ROWNUM;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SALES_DATE;
import static business.blap.NLAL2040.constant.NLAL2040Constant.SVC_SEG_CD;
import static business.blap.NLAL2040.constant.NLAL2040Constant.T_MDL_NM;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDMsg;
import business.blap.NLAL2040.NLAL2040CMsg;
import business.blap.NLAL2040.NLAL2040Query;
import business.blap.NLAL2040.NLAL2040SMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Business ID : NLAL2040 MODELS-CLICKS Inventory Valuation Entry
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/18/2016   CITS            T.Kikuhara      Create          N/A
 *</pre>
 */
public class NLAL2040CommonLogic {
    /**
     * Search
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     * @param eventName String
     */
    public static void search(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg, String eventName) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(T_MDL_NM, cMsg.t_MdlNm.getValue());
        ssmParam.put(SVC_SEG_CD, cMsg.svcSegCd_PS.getValue());
        ssmParam.put(COA_MDSE_TP_CD, cMsg.coaMdseTpCd_PS.getValue());
        ssmParam.put(COA_PROD_CD, cMsg.coaProdCd_PS.getValue());
        ssmParam.put(EFF_FROM_DT, cMsg.effFromDt.getValue());
        ssmParam.put(EFF_THRU_DT, cMsg.effThruDt.getValue());
        ssmParam.put(ROWNUM, sMsg.A.length() + 1);
        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox)) {
            String salseDate = ZYPDateUtil.getSalesDate(cMsg.glblCmpyCd.getValue());
            ssmParam.put(SALES_DATE, salseDate);
        }
        S21SsmEZDResult result = NLAL2040Query.getInstance().search(ssmParam, sMsg);

        if (result.isCodeNormal()) {
            // Max Recode Over
            int queryResCnt = result.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NMAM8181W, new String[]{Integer.toString(sMsg.A.length()), Integer.toString(sMsg.A.length())});
                queryResCnt = sMsg.A.length();
            }

            // Copy 1 page Data(SMsg -> CMsg)
            int i = 0;
            String effThruDt = ZYPCodeDataUtil.getVarCharConstValue(NLAL2040_DEF_EFF_THRU_DT, cMsg.glblCmpyCd.getValue());
            for (; i < sMsg.A.getValidCount(); i++) {
                if (i == cMsg.A.length()) {
                    break;
                }
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(i), null);
                if (ZYPCommonFunc.hasValue(cMsg.A.no(i).effThruDt_A1) && effThruDt.equals(cMsg.A.no(i).effThruDt_A1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(cMsg.A.no(i).effThruDt_A1, "");
                }
            }
            cMsg.A.setValidCount(i);

            // Setting Next Page
            cMsg.xxPageShowFromNum.setValue(1);
            cMsg.xxPageShowToNum.setValue(cMsg.A.getValidCount());
            cMsg.xxPageShowOfNum.setValue(queryResCnt);

        } else {
            cMsg.setMessageInfo(NMAM0038I);
            cMsg.xxPageShowFromNum.clear();
            cMsg.xxPageShowToNum.clear();
            cMsg.xxPageShowOfNum.clear();
        }

        return;
    }

    /**
     * <pre>
     * copyFromCmsgOntoSmsg
     * Copy data From CMsg Onto SMsg
     * </pre>
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    public static void copyFromCmsgOntoSmsg(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        if (0 < cMsg.A.getValidCount()) {
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1, j = 0; i < cMsg.xxPageShowToNum.getValueInt(); i++, j++) {
                EZDMsg.copy(cMsg.A.no(j), null, sMsg.A.no(i), null);
            }
        }
    }

    /**
     * <pre>
     * copyFromSmsgOntoCmsg
     * Copy data From sMsg Onto cMsg
     * </pre>
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    public static void copyFromSmsgOntoCmsg(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        if (0 < sMsg.A.getValidCount()) {
            int cMsgCount = 0;
            for (int i = cMsg.xxPageShowFromNum.getValueInt() - 1; i < cMsg.xxPageShowToNum.getValueInt(); i++) {
                EZDMsg.copy(sMsg.A.no(i), null, cMsg.A.no(cMsgCount), null);
                cMsgCount++;
            }
            cMsg.A.setValidCount(cMsgCount);
        }
    }

    /**
     * <pre>
     * Set page position
     * </pre>
     * @param cMsg NLAL2040CMsg
     * @param sMsg NLAL2040SMsg
     */
    public static void setPagePos(NLAL2040CMsg cMsg, NLAL2040SMsg sMsg) {
        if ((cMsg.A.length() == 0) || (sMsg.A.length() == 0) || (sMsg.A.getValidCount() <= 0)) {
            cMsg.xxPageShowFromNum.setValue(0);
            cMsg.xxPageShowToNum.setValue(0);
            cMsg.xxPageShowOfNum.setValue(0);
            return;
        }
        int startRowCount = 0;
        if (ZYPCommonFunc.hasValue(cMsg.xxPageShowFromNum)) {
            startRowCount = cMsg.xxPageShowFromNum.getValueInt();
        }
        int allRowCount = sMsg.A.getValidCount();
        if (startRowCount == 0) {
            cMsg.xxPageShowFromNum.setValue(1);
        } else if ((startRowCount < 0) || ((allRowCount <= startRowCount))) {
            // last page
            cMsg.xxPageShowFromNum.setValue(getLastPageStartCount(allRowCount, cMsg.A.length()));
        } else {
            if ((startRowCount % cMsg.A.length()) != 1) {
                startRowCount = startRowCount - (startRowCount % cMsg.A.length()) + 1;
            }
            cMsg.xxPageShowFromNum.setValue(startRowCount);
        }
        if ((cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1) < allRowCount) {
            cMsg.xxPageShowToNum.setValue(cMsg.xxPageShowFromNum.getValueInt() + cMsg.A.length() - 1);
        } else {
            cMsg.xxPageShowToNum.setValue(allRowCount);
        }
        cMsg.xxPageShowOfNum.setValue(allRowCount);
    }

    /**
     * <pre>
     * Get Last Page Start Count
     * </pre>
     * @param int allRowCount
     * @param int pageRowCount
     */
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
     * Set Error Page Line
     * </pre>
     * @param sMsg NLAL2040SMsg
     * @param cMsg NLAL2040CMsg
     * @param int i
     */
    public static void setErrPageLine(NLAL2040SMsg sMsg, NLAL2040CMsg cMsg, int i) {
        if (MAX_DISP_LINE > i) {
            cMsg.xxPageShowFromNum.setValue(1);
            if (MAX_DISP_LINE > sMsg.A.getValidCount()) {
                cMsg.xxPageShowToNum.setValue(sMsg.A.getValidCount());
            } else {
                cMsg.xxPageShowToNum.setValue(MAX_DISP_LINE);
            }
        } else {
            cMsg.xxPageShowFromNum.setValue(((i / MAX_DISP_LINE) * MAX_DISP_LINE) + 1);
            if (((i / MAX_DISP_LINE) * MAX_DISP_LINE) + MAX_DISP_LINE > sMsg.A.getValidCount()) {
                cMsg.xxPageShowToNum.setValue(sMsg.A.getValidCount());
            } else {
                cMsg.xxPageShowToNum.setValue(((i / MAX_DISP_LINE) * MAX_DISP_LINE) + MAX_DISP_LINE);
            }
        }
        ZYPTableUtil.clear(cMsg.A);
        NLAL2040CommonLogic.copyFromSmsgOntoCmsg(cMsg, sMsg);
    }
    
    /**
     * checkStartZero
     * @param cMsg
     * @param mdlNm
     * @param ownrCd
     * @return
     */
    public static boolean checkStartZero(NLAL2040CMsg cMsg, BigDecimal mdlId, String ownrCd, String mtrFlg) {

    	boolean reslut;

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(GLBL_CMPY_CD, cMsg.glblCmpyCd.getValue());
        ssmParam.put(MDL_ID, mdlId);
        ssmParam.put(INVTY_OWNR_CD, ownrCd);
        ssmParam.put(MTR_REQ_MDL_FLG, mtrFlg);

        S21SsmEZDResult result = NLAL2040Query.getInstance().checkStartZero(ssmParam);

        int DBcheckCnt = 0;
        if (result.isCodeNormal()) {
        	DBcheckCnt = (Integer) result.getResultObject();
        } else {
        	DBcheckCnt = 0;
        }

        if (DBcheckCnt == 0) {
        	reslut = false;
        } else {
        	reslut = true;
        }

        return reslut;
    }
}

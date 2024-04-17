/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL1150.common;

import static business.blap.NSAL1150.constant.NSAL1150Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDMsg;
import business.blap.NSAL1150.NSAL1150CMsg;
import business.blap.NSAL1150.NSAL1150Query;
import business.blap.NSAL1150.NSAL1150SMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * Supply Watch Used / Allowed
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Hitachi         T.Kanasaka      Create          N/A
 *</pre>
 */
public class NSAL1150CommonLogic {

    /**
     * Clear Message
     * @param cMsg NSAL1150CMsg
     * @param sMsg NSAL1150SMsg
     */
    public static void clearMsg(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {
        String condSqlTxtCU = cMsg.condSqlTxt_CU.getValue();
        String dsAcctNm = cMsg.dsAcctNm.getValue();
        String condSqlTxtCO = cMsg.condSqlTxt_CO.getValue();

        cMsg.clear();
        ZYPTableUtil.clear(cMsg.A);

        sMsg.clear();
        ZYPTableUtil.clear(sMsg.A);

        ZYPEZDItemValueSetter.setValue(cMsg.condSqlTxt_CU, condSqlTxtCU);
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm, dsAcctNm);
        ZYPEZDItemValueSetter.setValue(cMsg.condSqlTxt_CO, condSqlTxtCO);
    }

    /**
     * Set Initialize Parameters
     * @param cMsg NSAL1150CMsg
     * @param sMsg NSAL1150SMsg
     */
    public static void setInitParams(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {
        ZYPEZDItemValueSetter.setValue(cMsg.svcTermCondAttrbNm, ZYPCodeDataUtil.getVarCharConstValue(TERM_TONER_ALLOWANCE_PCT_NM, cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_TT, ZYPCodeDataUtil.getVarCharConstValue(NSAL1150_TOTAL_LINE_TITLE, cMsg.glblCmpyCd.getValue()));
        ZYPEZDItemValueSetter.setValue(cMsg.xxScrItem20Txt_TC, ZYPCodeDataUtil.getVarCharConstValue(NSAL1150_TOTAL_LINE_CONTR_NUM, cMsg.glblCmpyCd.getValue()));

        BigDecimal svcTermCondAttrbPk = NSAL1150Query.getInstance().getSvcTermCondAttrbPk(cMsg);
        if (svcTermCondAttrbPk != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.svcTermCondAttrbPk, svcTermCondAttrbPk);
        }
    }

    /**
     * Check search condition
     * @param cMsg NSAL1150CMsg
     * @return true: has search condition, false: no search condition
     */
    public static boolean hasSearchCondition(NSAL1150CMsg cMsg) {
        if (ZYPCommonFunc.hasValue(cMsg.condSqlTxt_CU) || ZYPCommonFunc.hasValue(cMsg.dsAcctNm) || ZYPCommonFunc.hasValue(cMsg.condSqlTxt_CO)) {
            return true;
        }
        return false;
    }

    /**
     * Get Search Data
     * @param cMsg NSAL1150CMsg
     * @param sMsg NSAL1150SMsg
     */
    public static void getSearchData(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg) {
        S21SsmEZDResult ssmResult = NSAL1150Query.getInstance().getSearchData(cMsg, sMsg, sMsg.A.length() + 1);
        if (ssmResult.isCodeNormal()) {
            int queryResCnt = ssmResult.getQueryResultCount();
            if (queryResCnt > sMsg.A.length()) {
                cMsg.setMessageInfo(NZZM0001W);
            }
        } else {
            cMsg.setMessageInfo(ZZZM9001E);
        }
    }

    /**
     * setPagenation <dd>The method explanation: The business
     * peculiarity processing is executed.
     * @param cMsg NSAL1150CMsg
     * @param sMsg NSAL1150SMsg
     * @param pagenationFrom int
     */
    public static void setPagenation(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg, int pagenationFrom) {

        int cnt = pagenationFrom;
        for (; cnt < pagenationFrom + cMsg.A.length(); cnt++) {
            if (cnt < pagenationFrom + cMsg.A.getValidCount()) {
                EZDMsg.copy(cMsg.A.get(cnt - pagenationFrom), null, sMsg.A.get(cnt), null);
            } else {
                break;
            }
        }
    }

    /**
     * pagenation <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     * @param pageFrom int
     */
    public static void pagenation(NSAL1150CMsg cMsg, NSAL1150SMsg sMsg, int pageFrom) {

        int i = pageFrom;
        for (; i < pageFrom + cMsg.A.length(); i++) {
            if (i < sMsg.A.getValidCount()) {
                EZDMsg.copy(sMsg.A.get(i), null, cMsg.A.get(i - pageFrom), null);
            } else {
                break;
            }
        }

        cMsg.A.setValidCount(i - pageFrom);
        cMsg.xxPageShowFromNum.setValue(pageFrom + 1);
        cMsg.xxPageShowToNum.setValue(pageFrom + cMsg.A.getValidCount());
    }

    /**
     * convertCSVtoList
     * @param csvData String
     * @return List<String>
     */
    public static List<String> convertCSVtoList(String csvData) {
        List<String> retList = new ArrayList<String>();
        String[] str = csvData.split(",");
        for (int i = 0; i < str.length; i++) {
            str[i] = str[i].trim();
            retList.add(str[i]);
        }
        return retList;
    }

}

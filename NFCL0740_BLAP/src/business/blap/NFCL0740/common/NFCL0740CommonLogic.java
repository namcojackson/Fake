/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NFCL0740.common;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import business.blap.NFCL0740.NFCL0740CMsg;
import business.blap.NFCL0740.NFCL0740Query;
import business.blap.NFCL0740.constant.NFCL0740Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.AR_ADJ_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INV_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 *<pre>
 * write-Off Request Creation
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/01   Fujitsu         T.Tanaka        Create          Initial
 * 2016/05/13   Fujitsu         C.Naito         Update          QC#7971
 * 2023/06/30   Hitachi         S.Fujita        Update          QC#60397
 * 2023/08/02   Hitachi         S.Fujita        Update          QC#60397
 *</pre>
 */
public class NFCL0740CommonLogic implements NFCL0740Constant, ZYPConstant {

    /**
     * 
     * @param pulldownCd
     * @param pulldownName
     * @param pullDownList
     * @param dbColumn
     */
    private static void initPullDownCreate(EZDCStringItemArray pulldownCd, EZDCStringItemArray pulldownName, List<Map> pullDownList, String[] dbColumn) {

        pulldownCd.clear();
        pulldownName.clear();

        for (int i = 0; i < pullDownList.size(); i++) {

            Map pullDownData = pullDownList.get(i);

            pulldownCd.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            pulldownName.no(i).setValue((String) pullDownData.get(dbColumn[1]));

            // [QC#7971] INSERT start
            if (i >= pulldownCd.length()) {
                // SQLResult over 99(pulldown rimite)
                break;
            }
            // [QC#7971] INSERT end
        }
    }

    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param bizMsg NFCL0740CMsg
     */
    public static void createPulldownListArAdjRsn(String glblCmpyCd, NFCL0740CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        S21SsmEZDResult arRcptSrc = NFCL0740Query.getInstance().getArAdjRsnPullDownList(bizMsg, ssmParam);

        if (arRcptSrc.isCodeNormal()) {
            List<Map> arRcptSrcList = (List<Map>) arRcptSrc.getResultObject();
            initPullDownCreate(bizMsg.arAdjRsnCd_LC, bizMsg.arAdjRsnNm_LD, arRcptSrcList, new String[] {"AR_ADJ_RSN_CD", "AR_ADJ_RSN_NM" });
        }
    }

    // [QC#7971] INSERT start
    /**
     * @param glblCmpyCd Global Company Code
     * @param bizMsg NFCL0740CMsg
     */
    public static void createPulldownListArAdjTp(String glblCmpyCd, NFCL0740CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("arAdjCatgCd_ADJ", AR_ADJ_CATG.ADJUSTMENT);
        S21SsmEZDResult arAdjTpRslt = NFCL0740Query.getInstance().getArAdjTpPullDownList(bizMsg, ssmParam);

        if (arAdjTpRslt.isCodeNormal()) {
            List<Map> arAdjTpRsltList = (List<Map>) arAdjTpRslt.getResultObject();
            initPullDownCreate(bizMsg.arAdjTpCd_LC, bizMsg.arAdjTpDescTxt_LD, arAdjTpRsltList, new String[] {"AR_ADJ_TP_CD", "AR_ADJ_TP_DESC_TXT" });
        }
    }
    // [QC#7971] INSERT end

    /**
     * @param bizMsg NFCL0740CMsg
     */
    public static void createPulldownForInvCls(NFCL0740CMsg bizMsg) {
        bizMsg.invTpCd_LC.no(0).setValue(INV_TP.INVOICE);
        bizMsg.invTpNm_LD.no(0).setValue("Invoice");

        bizMsg.invTpCd_LC.no(1).setValue(INV_TP.CREDIT_MEMO);
        bizMsg.invTpNm_LD.no(1).setValue("Credit Memo");
    }

    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param dsAcctNum DS Account Number
     * @return DS Account Name
     */
    public static String getDsAcctNm(String glblCmpyCd, String dsAcctNum) {

        if (ZYPCommonFunc.hasValue(dsAcctNum)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("dsAcctNum", dsAcctNum);
            ssmParam.put("rowNum", String.valueOf(BigDecimal.ONE));
            S21SsmEZDResult dsAcct = NFCL0740Query.getInstance().getDsAcctNm(null, ssmParam);
            if (dsAcct.isCodeNormal()) {
                return (String) dsAcct.getResultObject();
            } else {
                return null;
            }
        }
        return null;
    }

    /**
     * 
     * @param val value
     * @return String
     */
    public static String getChkBoxVal(String val) {

        if (FLG_ON_Y.equals(val)) {
            return FLG_ON_Y;
        }

        return FLG_OFF_N;
    }

    // START 2023/06/30 S.Fujita [QC#60397,ADD]
    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param psnCd PSN Code
     * @return HR Title Name
     */
    public static String getHrTtlNm(String glblCmpyCd, String psnCd) {

        if (ZYPCommonFunc.hasValue(psnCd)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("psnCd", psnCd);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.ADJUSTMENT);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            String hrTtlNm = NFCL0740Query.getInstance().getHrTtlNm(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlNm)); {
                return  hrTtlNm;
            }
        }
        return null;
    }

    /**
     * 
     * @param glblCmpyCd Global Company Code
     * @param hrTtlNm HR Title Name
     * @return HR Title Approval Limit PK
     */
    public static BigDecimal getHrTtlApvlLimitPk(String glblCmpyCd, String hrTtlNm) {

        if (ZYPCommonFunc.hasValue(hrTtlNm)) {
            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put("glblCmpyCd", glblCmpyCd);
            ssmParam.put("hrTtlNm", hrTtlNm);
            // START 2023/08/02 S.Fujita [QC#60397,ADD]
            ssmParam.put("catgCd", AR_ADJ_CATG.ADJUSTMENT);
            // END 2023/08/02 S.Fujita [QC#60397,ADD]
            BigDecimal hrTtlApvlLimitPk = NFCL0740Query.getInstance().getHrTtlApvlLimitPk(null, ssmParam);
            if (ZYPCommonFunc.hasValue(hrTtlApvlLimitPk)); {
                return  hrTtlApvlLimitPk;
            }
        }
        return null;
    }

    // END 2023/06/30 S.Fujita [QC#60397,ADD]
}

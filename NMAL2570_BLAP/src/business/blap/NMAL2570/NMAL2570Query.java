/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2570;

import static business.blap.NMAL2570.constant.NMAL2570Constant.HIGH_VAL_DT;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/03/03   Fujitsu         C.Tanaka        Create          QC#4551
 * 2016/06/07   SRAA            Y.Chen          Update          QC#7781
 *</pre>
 */
public final class NMAL2570Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NMAL2570Query INSTANCE = new NMAL2570Query();

    /**
     * Constructor.
     */
    private NMAL2570Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NMAL2570Query
     */
    public static NMAL2570Query getInstance() {
        return INSTANCE;
    }

    /**
     * Organization Search.
     * @param cMsg NMAL2570CMsg
     * @param sMsg NMAL2570SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResource(NMAL2570CMsg cMsg, NMAL2570SMsg sMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("xxPsnNm", cMsg.xxPsnNm_H1.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());
        ssmParam.put("jobTtlCd", cMsg.jobTtlCd_H1.getValue());
        ssmParam.put("psnNum", cMsg.psnNum_H1.getValue());
        ssmParam.put("orgFuncRoleTpCd", cMsg.orgFuncRoleTpCd_H1.getValue());
        ssmParam.put("orgNm", cMsg.orgNm_H1.getValue());
        ssmParam.put("effFromDtFrom", cMsg.effFromDt_H1.getValue());
        ssmParam.put("effFromDtTo", cMsg.effFromDt_H2.getValue());
        ssmParam.put("effThruDtFrom", cMsg.effThruDt_H1.getValue());
        ssmParam.put("effThruDtTo", cMsg.effThruDt_H2.getValue());
        ssmParam.put("rgtnStsCdP20", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("rgtnStsCdP01", RGTN_STS.PENDING_COMPLETION);
        ssmParam.put("gnrnTpCdCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpCdFuture", GNRN_TP.FUTURE);
        ssmParam.put("rowNum", sMsg.A.length() + 1);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate());
        ssmParam.put("highValDt", HIGH_VAL_DT);
        ssmParam.put("firstOrgCdSales", BIZ_AREA_ORG.SALES);


        if (ZYPCommonFunc.hasValue(cMsg.xxChkBox_H1) && ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H1.getValue())) {
            ssmParam.put("trtyStruFlg", ZYPConstant.FLG_ON_Y);
        }
        
        // QC#7781
        if (ZYPConstant.FLG_ON_Y.equals(cMsg.xxChkBox_H2.getValue())) {
            ssmParam.put("hasEmlAddrFlg", ZYPConstant.FLG_ON_Y);
        }

        return getSsmEZDClient().queryEZDMsgArray("getResource", ssmParam, sMsg.A);
    }

    /**
     * @param cMsg NMAL2570CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRoleName(NMAL2570CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        return getSsmEZDClient().queryObjectList("getRoleName", ssmParam);
    }
    
    // QC#7781
    /**
     * @param cMsg NMAL2570CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPerson(NMAL2570CMsg cMsg, String psnCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("psnCd", psnCd);
        return getSsmEZDClient().queryObjectList("getPerson", ssmParam);
    }
}

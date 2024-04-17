/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2720;

import static business.blap.NMAL2720.constant.NMAL2720Constant.HIGH_DT;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2720Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/22   Fujitsu         M.Nakamura      Create          N/A
 * 2016/10/28   Fujitsu         M.Ohno          Update          S21_NA#15310
 *</pre>
 */
public final class NMAL2720Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2720Query MY_INSTANCE = new NMAL2720Query();

    /**
     * Private constructor
     */
    private NMAL2720Query() {
        super();
    }

    /**
     * Get the NMAL2720Query instance.
     * @return NMAL2720Query instance
     */
    public static NMAL2720Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getBizArea.
     * @param bizMsg NMAL2720CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBizArea(NMAL2720CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        return getSsmEZDClient().queryObjectList("getBizArea", params);
    }

    /**
     * <pre>
     * getPsnNum
     * </pre>
     * @param bizMsg NMAL2720CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNum(NMAL2720CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNm", bizMsg.xxPsnNm_D1.getValue());
        return getSsmEZDClient().queryObjectList("getPsnNum", params);
    }

    /**
     * Search
     * @param bizMsg NMAL2720CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL2720CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      params.put("flgY", ZYPConstant.FLG_ON_Y);
      params.put("flgN", ZYPConstant.FLG_OFF_N);
      params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
      params.put("gnrnTpCdFut", GNRN_TP.FUTURE);

      params.put("orgNm", bizMsg.orgNm_H.getValue());
      params.put("bizAreaOrgCd", bizMsg.bizAreaOrgCd_H.getValue());
      params.put("psnCd", bizMsg.psnCd_H.getValue());
      params.put("psnNum", bizMsg.psnNum_H.getValue());
      params.put("psnNm", bizMsg.xxPsnNm_H.getValue());
      params.put("rowNum", bizMsg.A.length() + 1);

      return getSsmEZDClient().queryEZDMsgArray("search", params, bizMsg.A);
    }

    /**
     * <pre>
     * getResrcInfo
     * </pre>
     * @param bizMsg NMAL2720CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getResrcInfo(NMAL2720CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNm", bizMsg.xxPsnNm_D1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("slsDt", ZYPDateUtil.getSalesDate());

        return getSsmEZDClient().queryObjectList("getResrcInfo", params);
    }

    // Add Start 2016/10/28 M.Ohno S21_NA#15310
    /**
     * <pre>
     * countBizAreaOrg
     * </pre>
     * @param bizMsg NMAL2720CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countBizAreaOrg(NMAL2720CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNm", bizMsg.xxPsnNm_D1.getValue());
        params.put("cdSales", BIZ_AREA_ORG.SALES);
        params.put("cdService", BIZ_AREA_ORG.SERVICE);
        params.put("cdSales2", BIZ_AREA_ORG.SALES_2);

        return getSsmEZDClient().queryObject("countBizAreaOrg", params);
    }
    // Add End   2016/10/28 M.Ohno S21_NA#15310

    /**
     * <pre>
     * getMovableResrc
     * </pre>
     * @param lineMsg NMAL2720_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMovableResrc(NMAL2720_ACMsg lineMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
        params.put("gnrnTpCdFut", GNRN_TP.FUTURE);
        params.put("highDt", HIGH_DT);

        params.put("orgNm", lineMsg.orgNm_A1.getValue());
        params.put("bizAreaOrgCd", lineMsg.bizAreaOrgCd_A1.getValue());
        params.put("tocCd", lineMsg.tocCd_A1.getValue());
        params.put("psnNum", lineMsg.psnNum_A1.getValue());

        return getSsmEZDClient().queryObject("getMovableResrc", params);
    }

    /**
     * getMoveBizArea.
     * @param bizMsg NMAL2720CMsg
     * @param lineMsg NMAL2720_ACMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMoveBizArea(NMAL2720CMsg bizMsg, NMAL2720_ACMsg lineMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNm", bizMsg.xxPsnNm_D1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
        params.put("gnrnTpCdFut", GNRN_TP.FUTURE);
        params.put("effFromDt", bizMsg.effFromDt_D1.getValue());
        params.put("bizAreaOrgCd", lineMsg.bizAreaOrgCd_A1.getValue());
        params.put("orgNm", lineMsg.orgNm_A1.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_D1)) {
            params.put("effThruDt", bizMsg.effThruDt_D1.getValue());
        } else {
            params.put("effThruDt", HIGH_DT);
        }

        return getSsmEZDClient().queryObject("getMoveBizArea", params);
    }

    /**
     * getSameResrc.
     * @param bizMsg NMAL2720CMsg
     * @param lineMsg NMAL2720_ACMsg
     * @param orgFuncRoleTpCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSameResrc(NMAL2720CMsg bizMsg, NMAL2720_ACMsg lineMsg, String orgFuncRoleTpCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("psnNm", bizMsg.xxPsnNm_D1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        params.put("flgN", ZYPConstant.FLG_OFF_N);
        params.put("gnrnTpCdCur", GNRN_TP.CURRENT);
        params.put("gnrnTpCdFut", GNRN_TP.FUTURE);
        params.put("effFromDt", bizMsg.effFromDt_D1.getValue());
        params.put("bizAreaOrgCd", lineMsg.bizAreaOrgCd_A1.getValue());
        params.put("orgNm", lineMsg.orgNm_A1.getValue());
        params.put("tocCd", lineMsg.tocCd_A1.getValue());
        params.put("orgFuncRoleTpCd", orgFuncRoleTpCd);
        if (ZYPCommonFunc.hasValue(bizMsg.effThruDt_D1)) {
            params.put("effThruDt", bizMsg.effThruDt_D1.getValue());
        } else {
            params.put("effThruDt", HIGH_DT);
        }

        return getSsmEZDClient().queryObject("getSameResrc", params);
    }
}

package business.blap.NMAL7100;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MKT_PRMO_AUDC_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_PRMO_QLFY_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import static business.blap.NMAL7100.constant.NMAL7100Constant.HIGH_VAL_DT;
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/15/2015   Fujitsu         M.Hara          Create          CSA
 * 01/19/2016   Fujitsu         M.Hara          Update          QC#3002
 * 2019/12/06   Fujitsu         S.Kosaka        Update          QC#54215
 *</pre>
 */
public final class NMAL7100Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NMAL7100Query MY_INSTANCE = new NMAL7100Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NMAL7100Query() {
        super();
    }

    /**
     * <pre>
     * Get the NMAL7100Query instance.
     * </pre>
     * @return NMAL7100Query instance
     */
    public static NMAL7100Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getPrmoAudcCatg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrmoAudcCatg() {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        return getSsmEZDClient().queryObjectList("getPrmoAudcCatg", params);
    }

    /**
     * getPrcMktPrmoTp
     * @param bizMsg NMAL7100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcMktPrmoTp(NMAL7100CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMktPrmoTpCd", bizMsg.prcMktPrmoTpCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getPrcMktPrmoTp", params);
    }

    /**
     * searchMktPgmList
     * @param bizMsg  NMAL7100SMsg
     * @return  S21SsmEZDResult
     */
    public S21SsmEZDResult searchMktPgmHdr(NMAL7100CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMktPrmoPk", bizMsg.prcMktPrmoPk_H1.getValue());

        return getSsmEZDClient().queryEZDMsg("getMktPgmHdr", params, bizMsg);
    }

    /**
     * searchMktPgmList
     * @param bizMsg NMAL7100CMsg
     * @param glblMsg NMAL7100SMsg
     * @param isAplly boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchMktPgmList(NMAL7100CMsg bizMsg, NMAL7100SMsg glblMsg, boolean isAplly) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMktPrmoPk", bizMsg.prcMktPrmoPk_H1.getValue());
        params.put("prcPrmoQlfyTpModel", PRC_PRMO_QLFY_TP.SERVICE_MODEL);
        // 2019/12/06 QC#54215 Add Start
        if (ZYPCommonFunc.hasValue(bizMsg.prcMktPrmoCd_E1)) {
            params.put("prcMktPrmoCd", bizMsg.prcMktPrmoCd_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.mdseDescShortTxt_E1)) {
            params.put("mdseDescShortTxt", bizMsg.mdseDescShortTxt_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcPrmoQlfyTpCd_E1)) {
            params.put("prcPrmoQlfyTpCd", bizMsg.prcPrmoQlfyTpCd_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.prcQlfyValTxt_E1)) {
            if ((PRC_PRMO_QLFY_TP.SERVICE_MODEL.equals(bizMsg.prcPrmoQlfyTpCd_E1.getValue()))) {
                params.put("mdlNm", bizMsg.prcQlfyValTxt_E1.getValue());
            } else if (PRC_PRMO_QLFY_TP.ITEM_CODE.equals((bizMsg.prcPrmoQlfyTpCd_E1.getValue()))
                    || PRC_PRMO_QLFY_TP.BUNDLE.equals((bizMsg.prcPrmoQlfyTpCd_E1.getValue()))) {
                params.put("prcQlfyValTxt", bizMsg.prcQlfyValTxt_E1.getValue());
            }
        }
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_E1)) {
            params.put("mdseCd", bizMsg.mdseCd_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.prmoAmt_E1)) {
            params.put("prmoAmt", bizMsg.prmoAmt_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_E1)) {
            params.put("effFromDtFr", bizMsg.effFromDt_E1.getValue());
        }
        if (ZYPCommonFunc.hasValue(bizMsg.effFromDt_E2)) {
            params.put("effFromDtTo", bizMsg.effFromDt_E2.getValue());
        }
        // 2019/12/06 QC#54215 Add End

        if (isAplly) {
            params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
            params.put("highValDt", HIGH_VAL_DT);
            params.put("prcMktPrmoStsCd_DH", bizMsg.prcMktPrmoStsCd_DH.getValue());
            params.put("prcMktPrmoCd_DH", bizMsg.prcMktPrmoCd_DH.getValue());
        }

        return getSsmEZDClient().queryEZDMsgArray("getMktPgmList", params, glblMsg.A);
    }

    /**
     * searchCannotBeUsedList
     * @param bizMsg NMAL7100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchCannotBeUsedList(NMAL7100CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMktPrmoPk", bizMsg.prcMktPrmoPk_H1);

        return getSsmEZDClient().queryEZDMsgArray("getCannotBeUsedList", params, bizMsg.Y);
    }

    /**
     * searchMktAudList
     * @param bizMsg NMAL7100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchMktAudList(NMAL7100CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcMktPrmoPk", bizMsg.prcMktPrmoPk_H1.getValue());
        params.put("pub", MKT_PRMO_AUDC_CATG.PUBLIC);
        params.put("lineBizTpCd", MKT_PRMO_AUDC_CATG.BUSINESS_LINE);
        params.put("dsAcctNum", MKT_PRMO_AUDC_CATG.ACCOUNT_NUM);
        params.put("coaChCd", MKT_PRMO_AUDC_CATG.COA_CHANNEL);
        params.put("dsAcctGrpCd", MKT_PRMO_AUDC_CATG.ACCOUNT_GROUP);
        params.put("coaBrCd", MKT_PRMO_AUDC_CATG.COA_BRANCH);
        params.put("csmpNum", MKT_PRMO_AUDC_CATG.CSMP_NUM);
        params.put("prcGrpPk", MKT_PRMO_AUDC_CATG.CUSTOMER_PRICE_GROUP);
        params.put("mktPrcGrp", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);

        return getSsmEZDClient().queryEZDMsgArray("getMktAudList", params, bizMsg.X);
    }

    /**
     * getPrcGrpNm.
     * @param value String
     * @param prcGrpTpCd String
     * @param isPk boolean
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcGrp(String value, String prcGrpTpCd, boolean isPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (isPk) {
            params.put("prcGrpPk", new BigDecimal(value));
            params.put("prcGrpNm", null);
        } else {
            params.put("prcGrpPk", null);
            params.put("prcGrpNm", value);
        }
        params.put("prcGrpTpCd", prcGrpTpCd);

        return getSsmEZDClient().queryObject("getPrcGrp", params);
    }

    /**
     * getPromItmCd
     * @param glblCmpyCd String
     * @param mdseCd String
     * @param sign String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPromItmCd(String glblCmpyCd, String mdseCd, String sign) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("mdseCd", mdseCd);
        params.put("mdseTpCtxTpCd", "PRC_MKT_PRMO_ITEMS");
        params.put("sign", sign);

        return getSsmEZDClient().queryObject("getPromItmCd", params);
    }

    // START QC#3002 01/19/2016 ADD

    /**
     * getDsAcctNm
     * @param dsAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNm(String dsAcctNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("dsAcctNum", dsAcctNum);

        return getSsmEZDClient().queryObject("getDsAcctNm", params);
    }

    /**
     * getDsAcctNmByCsmp
     * @param csmpNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDsAcctNmByCsmp(String csmpNum) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("csmpNum", csmpNum);

        return getSsmEZDClient().queryObject("getDsAcctNmByCsmp", params);
    }

    /**
     * getMdlId
     * @param tMdlNm String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMdlId(String tMdlNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("tMdlNm", tMdlNm);

        return getSsmEZDClient().queryObject("getMdlId", params);
    }

    // END QC#3002 01/19/2016 ADD

}

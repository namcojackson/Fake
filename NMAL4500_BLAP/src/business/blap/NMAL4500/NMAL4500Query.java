/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NMAL4500;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCBigDecimalItem;
import parts.common.EZDCStringItem;
import business.blap.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/29   CITS            S.Endo          Update          QC#10840
 *</pre>
 */
public class NMAL4500Query extends S21SsmEZDQuerySupport implements NMAL4500Constant {
    /**
     * Singleton instance.
     */
    private static final NMAL4500Query myInstance = new NMAL4500Query();

    /**
     * Constructor.
     */
    private NMAL4500Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return  NMAL4530Query
     */
    public static NMAL4500Query getInstance() {
        return myInstance;
    }
    // DEL START 2013/08/05 QC1469
    // public S21SsmEZDResult get_ST_CD( NMAL4500CMsg cMsg, NMAL4500SMsg sMsg, String globalCompanyCode,String postCd ) {
    //            
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    //     
    //     ssmParam.put( "globalCompanyCode", globalCompanyCode );
    //     ssmParam.put( "postCode", postCd );
    //     ssmParam.put( "cMsg", cMsg );
    //       
    //     S21SsmEZDResult xxRes = getSsmEZDClient().queryEZDMsgArray( "getST_CD", ssmParam, sMsg.B );
    //     return xxRes;
    // }
    // public S21SsmEZDResult get_CNTY( NMAL4500CMsg cMsg, NMAL4500SMsg sMsg, String globalCompanyCode, String postCd) {
    //     
    // //String orderBy = getOrderBy( cMsg );
    //     
    //     Map<String, Object> ssmParam = new HashMap<String, Object>();
    // 
    //     ssmParam.put( "globalCompanyCode", globalCompanyCode );
    //     ssmParam.put( "postCode", postCd );
    //     ssmParam.put( "cMsg", cMsg );
    //       
    //     S21SsmEZDResult xxRes2 = getSsmEZDClient().queryEZDMsgArray( "getCNTY", ssmParam, sMsg.C );
    //     return xxRes2;
    // }
    // DEL END 2013/08/05 QC1469
    // ADD START 2013/08/05 QC1469

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult cntStCd(NMAL4500CMsg cMsg, String globalCompanyCode) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("globalCompanyCode", globalCompanyCode);
        ssmParam.put("stCd", cMsg.stCd_01.getValue());

        return getSsmEZDClient().queryObject("cntStCd", ssmParam);
    }
    // ADD END 2013/08/05 QC1469

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSameLocationVenderList(NMAL4500CMsg cMsg, String globalCompanyCode) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("globalCompanyCode", globalCompanyCode);
        ssmParam.put("vndCd", cMsg.vndCd_01.getValue());
        setNotNullParam(ssmParam, "firstLineAddr", cMsg.firstLineAddr_01);
        setNotNullParam(ssmParam, "scdLineAddr", cMsg.scdLineAddr_01);
        setNotNullParam(ssmParam, "thirdLineAddr", cMsg.thirdLineAddr_01);
        setNotNullParam(ssmParam, "frthLineAddr", cMsg.frthLineAddr_01);
        setNotNullParam(ssmParam, "ctryCd", cMsg.ctryCd_03);
        setNotNullParam(ssmParam, "postCd", cMsg.postCd_01);
        setNotNullParam(ssmParam, "ctyAddr", cMsg.ctyAddr_01);
        setNotNullParam(ssmParam, "cntyPk", cMsg.cntyPk_03);
        setNotNullParam(ssmParam, "stCd", cMsg.stCd_01);
        setNotNullParam(ssmParam, "provNm", cMsg.provNm_01);
        ssmParam.put("rowNum", 1);

        return getSsmEZDClient().queryObjectList("getSameLocationVenderList", ssmParam);
    }

    /**
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAffiliation(NMAL4500CMsg cMsg, String globalCompanyCode) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("globalCompanyCode", globalCompanyCode);
        ssmParam.put("coaAfflCd", cMsg.coaAfflCd_01.getValue());

        return getSsmEZDClient().queryObjectList("getAffiliation", ssmParam);
    }

    /**
     * @param globalCompanyCode globalCompanyCode
     * @param ptyCd ptyCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getImportInvoiceConsigneeByPartyCode(String globalCompanyCode, String ptyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("globalCompanyCode", globalCompanyCode);
        ssmParam.put("ptyCd", ptyCd);
// D7893(All1), updated by itoga, start. 2010/07/22
        ssmParam.put("locTpCd", LOC_TP.INBOUND_CONSIGNEE);
// D7893(All1), updated by itoga, end. 2010/07/22        
        return getSsmEZDClient().queryObjectList("getImportInvoiceConsigneeByPartyCode", ssmParam);
    }

    /**
     * @param ssmParam ssmParam
     * @param key key
     * @param item item
     */
    private void setNotNullParam(Map<String, Object> ssmParam, String key, EZDCStringItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            ssmParam.put(key, item.getValue());
        }
    }

    private void setNotNullParam(Map<String, Object> ssmParam, String key, EZDCBigDecimalItem item) {
        if (ZYPCommonFunc.hasValue(item)) {
            ssmParam.put(key, item.getValue());
        }
    }
    // <defect#5206 T.Ishii 20100405>
    /**
     * @param glblCmpyCd glblCmpyCd
     * @param whCd whCd
     * @param vndTpCd vndTpCd
     * @param vndPk vndPk
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVenderCountByWHCdAndVndtpCd(String glblCmpyCd, String whCd, String vndTpCd, BigDecimal vndPk) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("whCd", whCd);
        ssmParam.put("vndTpCd", vndTpCd);
        ssmParam.put("vndPk", vndPk);

        return getSsmEZDClient().queryObject("getVenderCountByWHCdAndVndtpCd", ssmParam);
    }

    // 10/22/2015 add start
    /**
     * @param glblCmpyCd glblCmpyCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getVndTp(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("vndTpCd", VND_TP.OUTBOUND_CARRIER);
        return getSsmEZDClient().queryObjectList("getVndTp", ssmParam);
    }

    /**
     * @param glblCmpyCd glblCmpyCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrTp(String glblCmpyCd) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        return getSsmEZDClient().queryObjectList("getCarrTp", ssmParam);
    }
    // 10/22/2015 add end

    /**
     * getAddrByPost
     * @param glblCmpyCd String
     * @param postCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrByPost(String glblCmpyCd, String postCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("postCd", postCd);

        return getSsmEZDClient().queryObjectList("getAddrByPost", params);
    }

    /**
     * getAddrInfo
     * @param cMsg cMsg
     * @param globalCompanyCode globalCompanyCode
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAddrInfo(NMAL4500CMsg cMsg, String globalCompanyCode) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", globalCompanyCode);
        params.put("stCd", cMsg.stCd_01.getValue());
        params.put("cntyNm", cMsg.cntyNm_02.get(0));

        return getSsmEZDClient().queryObject("getAddrInfo", params);
    }
}

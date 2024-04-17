/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NMZ.NMZC003001;

import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.HYPHEN;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.NMAM8357E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.NMAM8499E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.NMAM8500E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.NMAM8501E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.NMZM0009E;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_NO_ERR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.CNTYTMsg;
import business.db.CNTYTMsgArray;
import business.db.CTRYTMsg;
import business.db.STTMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.FAULT_CD;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.message.ZYPEZDMessageInfoUtil;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.api.S21ApiMessageMap;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.usa.s21.integration.service.bods.input.DataServicesAddressRequest;
import com.canon.usa.s21.integration.service.bods.output.DataServicesAddressReply;
import com.canon.usa.s21.integration.service.bods.wrapper.BodsAddressValidationProxy;

/**
 * <pre>
 * Address Validation API
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/15   Fujitsu         N.Sugiura       Create          N/A
 * 2016/05/09   SRAA            Y.Chen          Update          QC#4505, QC#5360
 * 2016/06/01   SRAA            Y.Chen          Update          QC#5360-2
 * 2016/08/23   Fujitsu         N.Sugiura       Update          QC#13560
 * 2016/08/31   Fujitsu         N.Sugiura       Update          QC#13091
 *</pre>
 */
public class NMZC003001 extends S21ApiCommonBase {

    /** S21SsmBatchClient */
    private S21SsmBatchClient ssmBatchClient = null;

    /**
     * Constructor
     */
    public NMZC003001() {
        super();
        this.ssmBatchClient = S21SsmBatchClient.getClient(this.getClass());
    }

    /**
     * <pre>
     * Address Validation API (List)
     * </pre>
     * @param params Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(List<NMZC003001PMsg> params, final ONBATCH_TYPE onBatchType) {
        for (NMZC003001PMsg msg : params) {
            execute(msg, onBatchType);
        }
    }

    /**
     * <pre>
     * Address Validation API
     * </pre>
     * @param param Input parameter list
     * @param onBatchType Type of Online or Batch.
     */
    public void execute(final NMZC003001PMsg param, final ONBATCH_TYPE onBatchType) {

        S21ApiMessageMap msgMap = new S21ApiMessageMap(param);

        // Output Parameter Default Set
        setOutputParameterDefault(msgMap);

        if (checkCommon(msgMap, param)) {

            if (chkCtryUs(param.ctryCd.getValue(), param.glblCmpyCd.getValue())) {
                if (checkInput(msgMap)) {
                    // main
                    doProcess(msgMap);
                }
            } else {
                checkInputForNotUS(msgMap);
            }
        }
        // end
        msgMap.flush();
        // 2016/08/31 QC#13091 Add Start
        setMessageTxt((NMZC003001PMsg) msgMap.getPmsg());
        // 2016/08/31 QC#13091 Add End

    }

    /**
     * setOutputParameterDefault
     * @param msgMap S21ApiMessageMap
     */
    private void setOutputParameterDefault(S21ApiMessageMap msgMap) {
        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();
        param.xxVldStsCd_01.setValue(RTRN_CD_NO_ERR); // 1st Address Status Code
        param.xxVldStsCd_02.setValue(RTRN_CD_NO_ERR); // 2nd Address Status Code
        param.xxVldStsCd_03.setValue(RTRN_CD_NO_ERR); // City Name Status Code
        param.xxVldStsCd_04.setValue(RTRN_CD_NO_ERR); // State Status Code
        param.xxVldStsCd_05.setValue(RTRN_CD_NO_ERR); // Postail Code Statuc Code
        param.xxVldStsCd_06.setValue(RTRN_CD_NO_ERR); // Country Status Code
        param.xxVldStsCd_07.setValue(RTRN_CD_NO_ERR); // County Status Code
    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInput(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.firstLineAddr)) {
            param.xxVldStsCd_01.setValue(RTRN_CD_ERROR); // 1st Address Status Code
            //msgMap.addXxMsgId(NMZM0035E);
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.ctyAddr)) {
            param.xxVldStsCd_03.setValue(RTRN_CD_ERROR); // City Name Status Code
            //msgMap.addXxMsgId(NMZM0036E);
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.stCd)) {
            param.xxVldStsCd_04.setValue(RTRN_CD_ERROR); // State Status Code
            //msgMap.addXxMsgId(NMZM0037E);
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.postCd)) {
            param.xxVldStsCd_05.setValue(RTRN_CD_ERROR); // Postail Code Statuc Code
            //msgMap.addXxMsgId(NMZM0038E);
            returnValue = false;
        }
        return returnValue;
    }

    /**
     * Input parameter check.
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkInputForNotUS(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();

        if (!ZYPCommonFunc.hasValue(param.firstLineAddr)) {
            param.xxVldStsCd_01.setValue(RTRN_CD_ERROR); // 1st Address Status Code
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.ctyAddr)) {
            param.xxVldStsCd_03.setValue(RTRN_CD_ERROR); // City Name Status Code
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.postCd)) {
            param.xxVldStsCd_05.setValue(RTRN_CD_ERROR); // Postail Code Statuc Code
            returnValue = false;
        }
        // QC#5360
        if(!ZYPCommonFunc.hasValue(param.stCd)){
// QC#5360-2
//          List<Map<String, Object>> listCnty = getStByCtry(param.glblCmpyCd.getValue(), param.ctryCd.getValue());
//          if (listCnty.size() > 0) {
            if (CTRY.UNITED_STATES_OF_AMERICA.equals(param.ctryCd.getValue())) {
                param.xxVldStsCd_04.setValue(RTRN_CD_ERROR); // State Status Code
                returnValue = false;  
            }
        }

        return returnValue;
    }

    private boolean checkCommon(S21ApiMessageMap msgMap, NMZC003001PMsg param) {
        boolean returnValue = true;

        // Common mandatory check
        if (!ZYPCommonFunc.hasValue(param.glblCmpyCd)) {
            msgMap.addXxMsgId(NMZM0009E);
            returnValue = false;
        }
        if (!ZYPCommonFunc.hasValue(param.ctryCd)) {
            param.xxVldStsCd_06.setValue(RTRN_CD_ERROR); // Country Status Code
            //msgMap.addXxMsgId(NMZM0039E);
            returnValue = false;
        } else {
            if (!chkCtry(param.ctryCd.getValue(), param.glblCmpyCd.getValue())) {
                param.xxVldStsCd_06.setValue(RTRN_CD_ERROR); // Country Status Code
                //msgMap.addXxMsgId(NMZM0064E);
                returnValue = false;
            }
        }
        return returnValue;
    }

    /**
     * Main process
     * @param msgMap S21ApiMessageMap
     */
    protected void doProcess(S21ApiMessageMap msgMap) {
        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();

        // Master check
        if (!checkMaster(msgMap)) {
            return;
        }

        // QC#4505
        if (ZYPConstant.FLG_ON_Y.equals(param.xxAddrVldFlg.getValue())) {
            if (!addressValidationForWmb(msgMap)) {
                return;
            }
        } else {
            if (!addressValidationForVertex(msgMap)) {
                return;
            }
        }
    }

    /**
     * checkMaster
     * @param msgMap S21ApiMessageMap
     * @return Results of the check.(false:error)
     */
    private boolean checkMaster(S21ApiMessageMap msgMap) {

        boolean returnValue = true;

        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();
        String glblCmpyCd = param.glblCmpyCd.getValue();

        // State Code
        if (ZYPCommonFunc.hasValue(param.stCd.getValue())) {
            if (!chkSt(param.stCd.getValue(), glblCmpyCd)) {
                param.xxVldStsCd_04.setValue(RTRN_CD_ERROR); // State Status Code
                //msgMap.addXxMsgId(NMZM0065E);
                returnValue = false;
            }
        }
        // County Name
        if (ZYPCommonFunc.hasValue(param.cntyNm.getValue())) {
            if (!chkCntyNm(param.cntyNm.getValue(), glblCmpyCd)) {
                param.xxVldStsCd_07.setValue(RTRN_CD_ERROR); // County Status Code
                //msgMap.addXxMsgId(NMZM0066E);
                returnValue = false;
            }
        }

        return returnValue;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCtry(String ctryCd, String glblCmpyCd) {

        CTRYTMsg chkCtryTMsg = new CTRYTMsg();
        ZYPEZDItemValueSetter.setValue(chkCtryTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkCtryTMsg.ctryCd, ctryCd);
        chkCtryTMsg = (CTRYTMsg) EZDTBLAccessor.findByKey(chkCtryTMsg);
        if (chkCtryTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param stCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkSt(String stCd, String glblCmpyCd) {

        STTMsg chkStTMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(chkStTMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(chkStTMsg.stCd, stCd);
        chkStTMsg = (STTMsg) EZDTBLAccessor.findByKey(chkStTMsg);
        if (chkStTMsg == null) {
            return false;
        }
        return true;
    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param chkCntynM String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCntyNm(String cntyNm, String glblCmpyCd) {

        AddrValidationBean addrVadBean = null;

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("cntyNm", cntyNm);

        addrVadBean = (AddrValidationBean) this.ssmBatchClient.queryObject("getCnty", param);
        if (addrVadBean == null || !ZYPCommonFunc.hasValue(addrVadBean.getCntyPk())) {
            return false;
        } else {
            return true;
        }

    }

    /**
     * @param msgMap S21ApiMessageMap
     * @param ctryCd String
     * @param glblCmpyCd String
     * @return returnValue
     */
    private boolean chkCtryUs(String ctryCd, String glblCmpyCd) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("ctryCd", ctryCd);

        Integer resCnt = (Integer) this.ssmBatchClient.queryObject("cntCtryUs", param);
        if (resCnt < 1) {
            return false;
        }
        return true;
    }

    /**
     * addressValidationForWmb
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    
    private boolean addressValidationForWmb(S21ApiMessageMap msgMap) {

         if (!globalAddressServiceAPI(msgMap)) {
             return false;
         }

        return true;
    }

    /**
     * globalAddressServiceAPI.
     * @param msgMap S21ApiMessageMap
     * @return boolean
     */
    private boolean globalAddressServiceAPI(S21ApiMessageMap msgMap) {

        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();

        BodsAddressValidationProxy bodsProxy = new BodsAddressValidationProxy();
        DataServicesAddressReply response = null;
        try {
            response = bodsProxy.rtsCSAUSAddressValidation(getDataServicesAddressRequest(param));
        } catch (Throwable t) {
            t.printStackTrace();
            msgMap.addXxMsgId(NMAM8357E);
            return false;
        }

        String faultOrStatusCode = response.getFaultOrStatusCode();
        if (ZYPCommonFunc.hasValue(faultOrStatusCode) && faultOrStatusCode.startsWith("E")) {
            setErrCd(param, faultOrStatusCode);
            return false;
        }

        String primaryAddress = toUpperCase(response.getPrimaryAddress());
        if (!equalsIgnoreCase(primaryAddress, param.firstLineAddr.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.firstLineAddr, primaryAddress);
            ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_01, RTRN_CD_SUGGEST);
        }

        String secondaryAddress = toUpperCase(response.getSecondaryAddress());
        if (!equalsIgnoreCase(secondaryAddress, param.scdLineAddr.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.scdLineAddr, secondaryAddress);
            ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_02, RTRN_CD_SUGGEST);
        }

        String locality1Alternate = toUpperCase(response.getLocality1Alternate());
        if (!equalsIgnoreCase(locality1Alternate, param.ctyAddr.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.ctyAddr, locality1Alternate);
            ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_03, RTRN_CD_SUGGEST);
        }

        String region1 = toUpperCase(response.getRegion1());
        if (!equalsIgnoreCase(region1, param.stCd.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.stCd, region1);
            ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_04, RTRN_CD_SUGGEST);
        }

        String postcode = null;
        String postcode1 = response.getPostcode1();
        String postcode2 = response.getPostcode2();
        if (ZYPCommonFunc.hasValue(postcode1)) {
            if (ZYPCommonFunc.hasValue(postcode2)) {
                postcode = postcode1 + HYPHEN + postcode2;
            } else {
                postcode = postcode1;
            }
            if (!postcode.equals(param.postCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(param.postCd, postcode);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_05, RTRN_CD_SUGGEST);
            }
        }

        String countyName = toUpperCase(response.getCountyName());
        if (!equalsIgnoreCase(countyName, param.cntyNm.getValue())) {
            ZYPEZDItemValueSetter.setValue(param.cntyNm, countyName);
            ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_07, RTRN_CD_SUGGEST);
        }

        CNTYTMsg cntyTMsg = new CNTYTMsg();
        cntyTMsg.setSQLID("002");
        cntyTMsg.setConditionValue("glblCmpyCd01", param.glblCmpyCd.getValue());
        cntyTMsg.setConditionValue("cntyNm01", param.cntyNm.getValue());
        cntyTMsg.setConditionValue("stCd01", param.stCd.getValue());
        CNTYTMsgArray cntyTMsgArray = (CNTYTMsgArray) EZDTBLAccessor.findByCondition(cntyTMsg);
        if (cntyTMsgArray.length() > 0) {
            CNTYTMsg tMsg = cntyTMsgArray.no(0);
            ZYPEZDItemValueSetter.setValue(param.cntyPk, tMsg.cntyPk.getValue());
        }

        return true;
    }

    private String toUpperCase(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    private boolean equalsIgnoreCase(String str1, String str2) {
        if (S21StringUtil.isEquals(str1, str2)) {
            return true;
        }
        if (S21StringUtil.isNotEmpty(str1) && S21StringUtil.isNotEmpty(str2)) {
            if (str1.equalsIgnoreCase(str2)) {
                return true;
            }
        }
        return false;
    }

    private void setErrCd(NMZC003001PMsg param, String faultOrStatusCode) {
        FAULT_CD faultCd = getFaultCd(faultOrStatusCode);
        switch (faultCd) {
            case E101:
            case E430:
            case E500:
            case E501:
            case E502:
            case E504:
            case E505:
            case E600:
            case E601:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_01, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_02, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_03, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_04, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_05, RTRN_CD_ERROR);
                break;
            case E212:
            case E214:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_03, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_05, RTRN_CD_ERROR);
                break;
            case E213:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_03, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_04, RTRN_CD_ERROR);
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_05, RTRN_CD_ERROR);
                break;
            case E216:
            case E428:
            case E503:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_05, RTRN_CD_ERROR);
                break;
            case E302:
            case E412:
            case E413:
            case E420:
            case E421:
            case E422:
            case E423:
            case E425:
            case E427:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_01, RTRN_CD_ERROR);
                break;
            case E429:
            case E431:
                ZYPEZDItemValueSetter.setValue(param.xxVldStsCd_03, RTRN_CD_ERROR);
                break;
            default:
                break;
        }
    }
    private FAULT_CD getFaultCd(String faultOrStatusCode) {
        for (FAULT_CD cd : FAULT_CD.values()) {
            if (cd.toString().equals(faultOrStatusCode)) {
                return cd;
            }
        }
        return null;
    }
    private DataServicesAddressRequest getDataServicesAddressRequest(NMZC003001PMsg param) {
        DataServicesAddressRequest req = new DataServicesAddressRequest();
        req.setPrimaryAddress(param.firstLineAddr.getValue());
        req.setSecondaryAddress(param.scdLineAddr.getValue());
        req.setLocality1(param.ctyAddr.getValue());
        req.setRegion1(param.stCd.getValue());
        String[] postCd = param.postCd.getValue().split(HYPHEN);
        if (postCd.length == 1) {
            req.setPostcode1(postCd[0]);
        } else if (postCd.length >= 2) {
            req.setPostcode1(postCd[0]);
            req.setPostcode2(postCd[1]);
        }
        return req;
    }
    
    // QC#4505
    private boolean addressValidationForVertex(S21ApiMessageMap msgMap) {
        NMZC003001PMsg param = (NMZC003001PMsg) msgMap.getPmsg();

        List<Map<String, Object>> listPost = getPost(param.glblCmpyCd.getValue(), param.postCd.getValue(), param.ctyAddr.getValue(), param.stCd.getValue());
        if (listPost.size() == 0) {
            if (param.postCd.getValue().length() > 5) {
                listPost = getPost(param.glblCmpyCd.getValue(), param.postCd.getValue().substring(0, 5), param.ctyAddr.getValue(), param.stCd.getValue());
            }
        }
        if (listPost.size() == 0) {
            msgMap.addXxMsgId(NMAM8499E);
            param.xxVldStsCd_03.setValue(RTRN_CD_ERROR); // City Name Status Code
            param.xxVldStsCd_04.setValue(RTRN_CD_ERROR); // State Status Code
            param.xxVldStsCd_05.setValue(RTRN_CD_ERROR); // Postal Code Status Code
            return false;
        }

        Map<String, Object> mapPost = listPost.get(0);
        BigDecimal postPk = (BigDecimal) mapPost.get("POST_PK");

        List<Map<String, Object>> listCnty = getCntyPostReln(param.glblCmpyCd.getValue(), postPk, param.cntyNm.getValue());

        // 2016/08/23 QC#13560 Mod Start
        if (listCnty.size() == 1) {
            Map<String, Object> mapCnty = listCnty.get(0);
            ZYPEZDItemValueSetter.setValue(param.cntyPk, (BigDecimal) mapCnty.get("CNTY_PK"));
            ZYPEZDItemValueSetter.setValue(param.cntyNm, (String) mapCnty.get("CNTY_NM"));
        } else {
            if (ZYPConstant.FLG_ON_Y.equals(param.xxCntyVldErrExclFlg.getValue())) {
                param.cntyPk.clear();
                param.cntyNm.clear();

            } else {
                if (listCnty.size() == 0) {
                    msgMap.addXxMsgId(NMAM8500E);
                    param.xxVldStsCd_07.setValue(RTRN_CD_ERROR); // County Status Code
                    return false;
                } else if (listCnty.size() > 1) {
                    param.xxVldStsCd_07.setValue(RTRN_CD_ERROR); // County Status Code
                    msgMap.addXxMsgId(NMAM8501E);
                    return false;
                }
            }
        }
        // 2016/08/23 QC#13560 Mod End

        return true;
    }

    private List<Map<String, Object>> getPost(String glblCmpyCd, String postCd, String ctyAddr, String stCd) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("postCd", postCd);
        param.put("ctyAddr", ctyAddr);
        param.put("stCd", stCd);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getPost", param);
    }

    private List<Map<String, Object>> getCntyPostReln(String glblCmpyCd, BigDecimal postPk, String cntyNm) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("postPk", postPk);
        param.put("cntyNm", cntyNm);
        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getCntyPostReln", param);
    }
    
// QC#5360-2
//    private List<Map<String, Object>> getStByCtry(String glblCmpyCd, String ctryCd) {
//        Map<String, Object> param = new HashMap<String, Object>();
//        param.put("glblCmpyCd", glblCmpyCd);
//        param.put("ctryCd", ctryCd);
//        return (List<Map<String, Object>>) this.ssmBatchClient.queryObjectList("getStByCtry", param);
//    }
    // 2016/08/31 QC#13091 Add Start
    /**
     * set Message Text
     * @param pMsg NMZC003001PMsg
     */
    private void setMessageTxt(NMZC003001PMsg pMsg) {
        ZYPEZDMessageInfoUtil util = new ZYPEZDMessageInfoUtil();
        for (int i = 0; i < pMsg.xxMsgIdList.getValidCount(); i++) {
            ZYPEZDItemValueSetter.setValue(pMsg.xxMsgIdList.no(i).xxMsgTxt, util.getI18nMessage(null, pMsg.xxMsgIdList.no(i).xxMsgId.getValue(), null));
        }
    }
    // 2016/08/31 QC#13091 Add End
}

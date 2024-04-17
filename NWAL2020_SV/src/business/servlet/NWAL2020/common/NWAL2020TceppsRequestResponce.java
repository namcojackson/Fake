package business.servlet.NWAL2020.common;

import static business.servlet.NWAL2020.constant.NWAL2020Constant.TCEPPS_AX;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.TCEPPS_DI;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.TCEPPS_DS;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.TCEPPS_MC;
import static business.servlet.NWAL2020.constant.NWAL2020Constant.TCEPPS_VI;

import java.util.HashMap;
import java.util.Map;

import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL2020.NWAL2020BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CR_CARD_TP;
import com.canon.cusa.s21.framework.common.S21StringUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.usa.app.security.APPSecurityManager;
import com.canon.usa.tcepps.property.TCEPPSPropertyManager;
import com.canon.usa.tcepps.property.exception.TCEPPSPropertiesFileNotFoundException;
import com.canon.usa.tcepps.property.exception.TCEPPSPropertyNotDefinedException;
import com.canon.usa.tcepps.security.TCEPPSSecurityManager;
import com.canon.usa.tcepps.security.exception.DecryptionFailureException;
import com.canon.usa.tcepps.security.exception.EncryptionFailureException;
import com.canon.usa.tcepps.security.exception.SecretKeyGenerationFailureException;
import com.canon.usa.tcepps.security.exception.URLDecodeFailureException;
import com.canon.usa.tcepps.security.exception.URLEncodeFailureException;
import com.fujitsu.uji.http.HttpDispatchContext;
import com.fujitsu.uji.util.Parameters;

/**
 *<pre>
 * NWAL2020 TCEPPS Request responce
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2024/04/10   CITS            M.Kobayashi     Create          QC#63757
 *</pre>
 */
public class NWAL2020TceppsRequestResponce {

    /**
     * Gets the request params.
     * @return the request params
     */
    public void loadRequestParams(NWAL2020BMsg bMsg, String requestUrl) {

        java.util.Map<String, String> tceppsRequest = new java.util.HashMap<String, String>();

        java.util.Map<String, String> tceppsEncryptedRequestMap = new HashMap<String, String>();
        try {
            String appToken = APPSecurityManager.getInstance().generateToken();
            tceppsRequest.put("app." + APPSecurityManager.APP_TOKEN, appToken);
            tceppsRequest.put("tcepps.proxyUrl", getResponseProxyUrl(requestUrl));

            tceppsEncryptedRequestMap = TCEPPSSecurityManager.getInstance().encryptUrlEncodeTceppsParameters(tceppsRequest);
            tceppsEncryptedRequestMap.put(APPSecurityManager.APP_TOKEN, appToken);
            bMsg.xxGenlFldAreaTxt_T1.setValue(tceppsEncryptedRequestMap.get(TCEPPSSecurityManager.TCEPPS_ENCRYPTED_REQUEST));
            bMsg.xxGenlFldAreaTxt_T2.setValue(tceppsEncryptedRequestMap.get(TCEPPSSecurityManager.TCEPPS_ENCRYPTED_REQUEST_KEY));
            bMsg.xxGenlFldAreaTxt_T3.setValue(appToken);

        } catch (SecretKeyGenerationFailureException e) {
            e.printStackTrace();
        } catch (URLEncodeFailureException e) {
            e.printStackTrace();
        } catch (EncryptionFailureException e) {
            e.printStackTrace();
        }
    }

    /**
     * Load response params.
     * @param ctx the ctx
     * @param msg the msg
     */
    public void loadResponseParams(EZDApplicationContext ctx, NWAL2020BMsg bMsg, String globalCompanyCode) {

        String tceppsApp = bMsg.appNm_T.getValue();
        Parameters param = ((HttpDispatchContext) ctx.getDispatchContext()).getParameters();
        String tceppsEncryptedResponse = param.getSingleValue("tceppsEncryptedResponse");
        String tceppsEncryptedResponseKey = param.getSingleValue("tceppsEncryptedResponseKey");
        Map<String, String> tceppsResponse = null;
        String customerRefNum = null;
        String ccAccountNum = null;
        String crCardTpCd = null;
        String crCardHldNm = null;
        String crCardExprYrMth = null;
        String firstLineAddr = null;
        String scdLineAddr = null;
        String ctyAddr = null;
        String postCd = null;
        String stCd = null;
        String ctryCd = null;

        try {
            tceppsResponse = TCEPPSSecurityManager.getInstance().urlDecodeDecryptTceppsParameters(tceppsApp, tceppsEncryptedResponse, tceppsEncryptedResponseKey);
            customerRefNum = tceppsResponse.get("ppa.customerRefNum");
            ccAccountNum = tceppsResponse.get("ppa.ccAccountNum");
            crCardTpCd = tceppsResponse.get("ppa.cardBrand");
            crCardHldNm = tceppsResponse.get("ppa.customerName");

            // no conversion required for BAMS.
            crCardExprYrMth = tceppsResponse.get("ppa.ccExp");

            firstLineAddr = tceppsResponse.get("ppa.customerAddress1");
            scdLineAddr = tceppsResponse.get("ppa.customerAddress2");
            ctyAddr = tceppsResponse.get("ppa.customerCity");
            postCd = tceppsResponse.get("ppa.customerZIP");
            stCd = tceppsResponse.get("ppa.customerState");
            ctryCd = tceppsResponse.get("ppa.customerCountryCode");

            // change credit card,because tcepps and S21 Credi Card have different value
            if (ZYPCommonFunc.hasValue(crCardTpCd)) {
                if (crCardTpCd.equals(TCEPPS_VI)) {
                    crCardTpCd = CR_CARD_TP.VISA;
                } else if (crCardTpCd.equals(TCEPPS_DI)) {
                    crCardTpCd = CR_CARD_TP.DIN;
                } else if (crCardTpCd.equals(TCEPPS_MC)) {
                    crCardTpCd = CR_CARD_TP.MSTR;
                } else if (crCardTpCd.equals(TCEPPS_AX)) {
                    crCardTpCd = CR_CARD_TP.AMEX;
                } else if (crCardTpCd.equals(TCEPPS_DS)) {
                    crCardTpCd = CR_CARD_TP.DSCVR;
                }
            }

            bMsg.crCardCustRefNum_C.setValue(S21StringUtil.isEmpty(customerRefNum) ? "" : customerRefNum);
            bMsg.xxPmtcAcctNum_C.setValue(S21StringUtil.isEmpty(ccAccountNum) ? "" : ccAccountNum);
            bMsg.crCardTpCd_C.setValue(S21StringUtil.isEmpty(crCardTpCd) ? "" : crCardTpCd);
            bMsg.crCardHldNm_C.setValue(S21StringUtil.isEmpty(crCardHldNm) ? "" : crCardHldNm);
            bMsg.crCardExprYrMth_C.setValue(S21StringUtil.isEmpty(crCardExprYrMth) ? "" : crCardExprYrMth);
            bMsg.firstLineAddr_C.setValue(S21StringUtil.isEmpty(firstLineAddr) ? "" : firstLineAddr);
            bMsg.scdLineAddr_C.setValue(S21StringUtil.isEmpty(scdLineAddr) ? "" : scdLineAddr);
            bMsg.ctyAddr_C.setValue(S21StringUtil.isEmpty(ctyAddr) ? "" : ctyAddr);
            bMsg.postCd_C.setValue(S21StringUtil.isEmpty(postCd) ? "" : postCd);
            bMsg.stCd_C.setValue(S21StringUtil.isEmpty(stCd) ? "" : stCd);
            bMsg.ctryCd_C.setValue(S21StringUtil.isEmpty(ctryCd) ? "" : ctryCd);

        } catch (DecryptionFailureException e) {
            e.printStackTrace();
        } catch (URLDecodeFailureException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get responseProxyUrl
     * @param requestUrl
     * @return responseProxyUrl
     */
    private String getResponseProxyUrl(String requestUrl) {
        requestUrl = requestUrl.substring(0, requestUrl.lastIndexOf("/") + 1);
        try {
            String tceppsUrl = TCEPPSPropertyManager.getInstance().getTceppsUrl();
            String tceppsProtocol = tceppsUrl.split("://")[0];
            String requestProtocol = requestUrl.split("://")[0];
            requestUrl = requestUrl.replaceFirst(requestProtocol, tceppsProtocol);
        } catch (TCEPPSPropertiesFileNotFoundException e) {
            throw new S21AbendException(e);
        } catch (TCEPPSPropertyNotDefinedException e) {
            throw new S21AbendException(e);
        }
        return requestUrl + "jsp/common/tceppsResponseProxy.jsp";
    }
}

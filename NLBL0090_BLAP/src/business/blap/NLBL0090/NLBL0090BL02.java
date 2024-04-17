/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLBL0090;

import static business.blap.NLBL0090.constant.NLBL0090Constant.ASTERISK;
import static business.blap.NLBL0090.constant.NLBL0090Constant.COLON;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_CMSG;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_DATA_SECURITY_LIST;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_GLBL_CMPY_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_OTBD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_OUTBOUND_CARRIER;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_POD_INNER_JOIN;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_POD_STS_CD_LIST;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_POD_STS_DATE_FLG;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_POD_STS_TP_FOR_SCR_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_RGTN_STS_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_ROWNUM;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_RTL_WH_CD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_SHIPPED_DATE_FLG;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_SMSG;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_TIME_MAX;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_TIME_MIN;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_VALUE_FOR_HHMMSS_MAX;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DB_PARAM_VALUE_FOR_HHMMSS_MIN;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DEFAULT_ADD_DAYS;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DT_TP_POD;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DT_TP_PULDW_LIST;
import static business.blap.NLBL0090.constant.NLBL0090Constant.DT_TP_SHIP;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NLBM0012E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NLBM0014E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NLBM1096E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NZZM0000E;
import static business.blap.NLBL0090.constant.NLBL0090Constant.NZZM0001W;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_DBCOLUMN;
import static business.blap.NLBL0090.constant.NLBL0090Constant.POD_STS_TP_DBCOLUMN;
import static business.blap.NLBL0090.constant.NLBL0090Constant.REPLACE_CHAR;
import static business.blap.NLBL0090.constant.NLBL0090Constant.TBL_NAME_A;
import static business.blap.NLBL0090.constant.NLBL0090Constant.TBL_NAME_B;
import static business.blap.NLBL0090.constant.NLBL0090Constant.WH_CD;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import parts.common.EZDCMsg;
import parts.common.EZDCStringItemArray;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import business.blap.NLBL0090.common.NLBL0090CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.online.common.sort.S21EZDMsgArraySort;
import com.canon.cusa.s21.framework.online.common.sort.S21SortKey;
import com.canon.cusa.s21.framework.online.common.sort.S21SortTarget;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2009/07/22   Fujitsu         Mori            Create          N/A
 * 2010/06/15   Fujitsu         Mori            Update          6918 CSV Download
 * 2014/01/13   CSAI            K.Lee           Update          QC3321
 * 2016/04/07   CITS            Y.Nomura        Update          for CSA
 *</pre>
 */
public class NLBL0090BL02 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {

        try {
            // get EventID
            String screenAplID = cMsg.getScreenAplID();

            // dispatch Event
            if ("NLBL0090_INIT".equals(screenAplID)) {
                doProcess_NLBL0090_INIT((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_CarrierButton".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_CarrierButton((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_SellToButton".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_SellToButton((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_ShipToButton".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_ShipToButton((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_PODStatusTypeForSearch".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_PODStatusTypeForSearch((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_BOLTrackingSearch".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_BOLTrackingSearch((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_PagePrev".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_PagePrev((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_PageNext".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_PageNext((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_TAB_BOLDetail".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_TAB_BOLDetail((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_TAB_BOLTracking".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_TAB_BOLTracking((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_TBLColumnSort".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_TBLColumnSort((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_CMN_Download".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_CMN_Download((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else if ("NLBL0090Scrn00_CMN_Reset".equals(screenAplID)) {
                doProcess_NLBL0090Scrn00_CMN_Reset((NLBL0090CMsg) cMsg, (NLBL0090SMsg) sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * <pre>
     * Process of init screen event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090_INIT(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        initializeViewData(cMessage, sMessage);
    }

    /**
     * <pre>
     * Process of reset screen event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_CMN_Reset(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        initializeViewData(cMessage, sMessage);
    }

    private void initializeViewData(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        cMessage.clear();
        sMessage.clear();

        ZYPEZDItemValueSetter.setValue(cMessage.glblCmpyCd_Z1, getGlobalCompanyCode());

        // ********** get user's warehouse <data security>
        // ************
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        S21DataSecurityProfile dataSecurityProfile = profileService.getDataSecurityProfileFor(cMessage.getBusinessID());
        List<S21DataSecurityAttributeData> dataSecurityAttrList = dataSecurityProfile.getDataSecurityAttributeDataListFor(S21DataSecurityAttributeData.NAME_WAREHOUSE);

        // ********** create Pulldown List ************

        if (!dataSecurityAttrList.isEmpty()) {

            Map<String, Object> ssmParam = new HashMap<String, Object>();
            ssmParam.put(DB_PARAM_CMSG, cMessage);

            // If DataSecurity list has "*", WarehouseCode is not used
            // as search condition.
            if (!hasAllWHAuthority(dataSecurityAttrList)) {
                ssmParam.put(DB_PARAM_DATA_SECURITY_LIST, dataSecurityAttrList);
            }
        }

        // POD Status Type
        S21SsmEZDResult podStsTpRs = NLBL0090Query.getInstance().getPODStatusTypePullDownList(cMessage);
        if (podStsTpRs.isCodeNormal()) {
            List<Map> podStsTpPulldownList = (List<Map>) podStsTpRs.getResultObject();
            initPullDownCreate(cMessage.podStsTpForScrCd_H1, cMessage.xxEdtCdNm_H2, podStsTpPulldownList, POD_STS_TP_DBCOLUMN);
        }

        // POD Status
        S21SsmEZDResult podStsRs = NLBL0090Query.getInstance().getPODStatusPullDownList(cMessage);
        if (podStsRs.isCodeNormal()) {
            List<Map> podStsPulldownList = (List<Map>) podStsRs.getResultObject();
            initPullDownCreate(cMessage.podStsCd_H1, cMessage.xxEdtCdNm_H3, podStsPulldownList, POD_STS_DBCOLUMN);
        }

        // Date Type
        NLBL0090CommonLogic.initPullDownCreate(cMessage.xxDtTpCd_H1, cMessage.xxEdtCdNm_H4, DT_TP_PULDW_LIST);
        // ADD Default Value for response
        String endDT = ZYPDateUtil.getSalesDate();
        String startDT = ZYPDateUtil.addDays(endDT, DEFAULT_ADD_DAYS);
        cMessage.podStsDt_H1.setValue(startDT);
        cMessage.podStsDt_H2.setValue(endDT);
        cMessage.xxDtTpCd_H2.setValue(DT_TP_SHIP);
        sMessage.podStsDt_H1.setValue(startDT);
        sMessage.podStsDt_H2.setValue(endDT);
        sMessage.xxDtTpCd_H2.setValue(DT_TP_SHIP);
    }

    /**
     * <pre>
     * Process of onChange "POD Status Type For Search" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_PODStatusTypeForSearch(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        String podStsTpForScrCd = cMessage.podStsTpForScrCd_H2.getValue();

        createPodStsPulldownList(cMessage, podStsTpForScrCd);
    }

    private void createPodStsPulldownList(NLBL0090CMsg cMessage, String podStsTpForScrCd) {
        // create Pulldown List : "POD Status"
        Map<String, String> ssmParam = new HashMap<String, String>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, cMessage.glblCmpyCd_Z1.getValue());

        if (ZYPCommonFunc.hasValue(podStsTpForScrCd)) {
            ssmParam.put(DB_PARAM_POD_STS_TP_FOR_SCR_CD, podStsTpForScrCd);
        }

        S21SsmEZDResult podStsRs = NLBL0090Query.getInstance().getPODStatusListByPODStatusTypeCd(ssmParam);
        if (podStsRs.isCodeNormal()) {
            // get "POD Status" value list that related to
            // "POD Status Type For Search"'s selection value.
            List<Map> podStsPulldownList = (List<Map>) podStsRs.getResultObject();
            cMessage.podStsCd_H1.clear();
            cMessage.podStsCd_H2.clear();
            cMessage.xxEdtCdNm_H3.clear();
            initPullDownCreate(cMessage.podStsCd_H1, cMessage.xxEdtCdNm_H3, podStsPulldownList, POD_STS_DBCOLUMN);
        }
    }

    /**
     * <pre>
     * Process of onClick "Carrier Button" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_CarrierButton(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        checkCarrierName(cMessage);
    }

    /**
     * <pre>
     * Process of onClick "SellTo Button" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_SellToButton(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        checkSellTo(cMessage);
    }

    /**
     * <pre>
     * Process of onClick "ShipTo Button" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_ShipToButton(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        checkShipTo(cMessage);
    }

    private boolean checkCarrierName(NLBL0090CMsg cMessage) {

        cMessage.carrNm_H1.clear();

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMessage);
        ssmParam.put(DB_PARAM_OUTBOUND_CARRIER, VND_TP.OUTBOUND_CARRIER);

        S21SsmEZDResult rs = NLBL0090Query.getInstance().getCarrierName(ssmParam, cMessage);
        if (rs.isCodeNotFound()) {
            cMessage.carrCd_H2.setErrorInfo(1, NLBM1096E, new String[] {"Carrier" });
            return false;
        }
        return true;
    }

    private boolean checkSellTo(NLBL0090CMsg cMessage) {
        cMessage.dsAcctNm_H2.clear();

        S21SsmEZDResult rs = searchSellToName(cMessage);
        if (rs.isCodeNotFound()) {
            cMessage.sellToCustCd_H2.setErrorInfo(1, NLBM0012E);
            return false;
        }
        return true;
    }

    private S21SsmEZDResult searchSellToName(NLBL0090CMsg cMessage) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMessage);

        S21SsmEZDResult rs = NLBL0090Query.getInstance().getSellToName(ssmParam, cMessage);
        return rs;
    }

    private boolean checkShipTo(NLBL0090CMsg cMessage) {

        cMessage.dsAcctNm_H3.clear();

        S21SsmEZDResult rs = searchShipToName(cMessage);
        if (rs.isCodeNotFound()) {
            cMessage.shipToCustCd_H2.setErrorInfo(1, NLBM0014E);
            return false;
        }
        return true;
    }

    private boolean checkWH(NLBL0090CMsg cMessage) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_GLBL_CMPY_CD, getGlobalCompanyCode());
        ssmParam.put(DB_PARAM_RTL_WH_CD, cMessage.whCd_H1.getValue());

        S21SsmEZDResult rs = NLBL0090Query.getInstance().getWH(ssmParam);
        if (rs.isCodeNotFound()) {
            cMessage.whCd_H1.setErrorInfo(1, NLBM1096E, new String[] {WH_CD });
            return false;
        }
        return true;
    }

    private S21SsmEZDResult searchShipToName(NLBL0090CMsg cMessage) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_CMSG, cMessage);
        ssmParam.put(DB_PARAM_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        S21SsmEZDResult rs = NLBL0090Query.getInstance().getShipToName(ssmParam, cMessage);
        return rs;
    }

    /**
     * <pre>
     * Process of onClick "Search Button" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMsg Global area information
     */
    private void doProcess_NLBL0090Scrn00_BOLTrackingSearch(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        // Clear the name attribute of search master area.
        cMessage.carrNm_H1.clear();
        cMessage.dsAcctNm_H2.clear();
        cMessage.dsAcctNm_H3.clear();

        sMessage.clear();

        // ******* Master check ************
        boolean masterTblNotExistFlg = false;

        // Carrier
        if (ZYPCommonFunc.hasValue(cMessage.carrCd_H2)) {
            if (!checkCarrierName(cMessage)) {
                masterTblNotExistFlg = true;
            }
        }

        // Sell to
        if (ZYPCommonFunc.hasValue(cMessage.sellToCustCd_H2)) {
            if (!checkSellTo(cMessage)) {
                masterTblNotExistFlg = true;
            }
        }

        // Ship to
        if (ZYPCommonFunc.hasValue(cMessage.shipToCustCd_H2)) {
            if (!checkShipTo(cMessage)) {
                masterTblNotExistFlg = true;
            }
        }

        // WH
        if (ZYPCommonFunc.hasValue(cMessage.whCd_H1)) {
            if (!checkWH(cMessage)) {
                masterTblNotExistFlg = true;
            }
        }

        if (masterTblNotExistFlg) {
            return;
        }

        EZDMsg.copy(cMessage, null, sMessage, null);

        // ******* create search criteria ************
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_SMSG, sMessage);

        // "POD Status For Search"
        List<String> podStsCdList = getPodStsCdListForCriteria(sMessage);
        ssmParam.put(DB_PARAM_POD_STS_CD_LIST, podStsCdList);

        // If one of these items have value, POD is searched by
        // INNER-JOIN. If not, searched by OUTER-JOIN.
        if (!podStsCdList.isEmpty()) {
            ssmParam.put(DB_PARAM_POD_INNER_JOIN, ZYPConstant.FLG_ON_Y);
        } else {
            if (DT_TP_POD.equals(sMessage.xxDtTpCd_H2.getValue())) {
                if (ZYPCommonFunc.hasValue(sMessage.podStsDt_H1) || ZYPCommonFunc.hasValue(sMessage.podStsDt_H2)) {
                    ssmParam.put(DB_PARAM_POD_INNER_JOIN, ZYPConstant.FLG_ON_Y);
                }
            }
        }

        if (DT_TP_SHIP.equals(sMessage.xxDtTpCd_H2.getValue())) {
            ssmParam.put(DB_PARAM_SHIPPED_DATE_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_TIME_MIN, DB_PARAM_VALUE_FOR_HHMMSS_MIN);
            ssmParam.put(DB_PARAM_TIME_MAX, DB_PARAM_VALUE_FOR_HHMMSS_MAX);
        } else if (DT_TP_POD.equals(sMessage.xxDtTpCd_H2.getValue())) {
            ssmParam.put(DB_PARAM_POD_STS_DATE_FLG, ZYPConstant.FLG_ON_Y);
        }

        // Clear Table info of CMsg after master check process.
        clearTrckngTableInfo(cMessage);
        clearDetailTableInfo(cMessage);

        // ******* execute search ************
        ssmParam.put("USING_GROUPBY", ZYPConstant.FLG_ON_Y);

        // Performance
        ssmParam.put(DB_PARAM_ROWNUM, sMessage.A.length());

        S21SsmEZDResult ssmResult = NLBL0090Query.getInstance().getBOLTrackingList(ssmParam, sMessage);

        if (ssmResult.isCodeNotFound()) {
            // search result not found
            cMessage.setMessageInfo(NZZM0000E);
            return;
        }

        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMessage.A.length()) {
            cMessage.setMessageInfo(NZZM0001W);
            queryResCnt = sMessage.A.length();
        }

        // Edit Link URL
        for (int j = 0; j < sMessage.A.getValidCount(); j++) {
            editCarrTrkUrl(sMessage, j);
        }

        // ***** Copy to one page's retrieval result (SMsg -> CMsg)
        // *****
        int j = 0;
        for (; j < sMessage.A.getValidCount(); j++) {
            if (j == cMessage.A.length()) {
                break;
            }

            EZDMsg.copy(sMessage.A.no(j), null, cMessage.A.no(j), null);
        }
        cMessage.A.setValidCount(j);

        cMessage.xxPageShowFromNum_A1.setValue(1);
        cMessage.xxPageShowToNum_A1.setValue(cMessage.A.getValidCount());
        cMessage.xxPageShowOfNum_A1.setValue(queryResCnt);
    }

    private List<String> getPodStsCdListForCriteria(NLBL0090SMsg sMessage) {
        List<String> podStsCdList = new ArrayList<String>();
        if (ZYPCommonFunc.hasValue(sMessage.podStsCd_H2)) {
            // If "POD Status For Search" has value, add it to the
            // search criteria.
            podStsCdList.add(sMessage.podStsCd_H2.getValue());
        } else {
            // If "POD Status For Search" has no value,

            if (ZYPCommonFunc.hasValue(sMessage.podStsTpForScrCd_H2)) {
                // Add "POD Status For Search"'s value to search
                // criteria which is related with the selected value
                // in the "POD Status Type For Search".
                Map<String, String> ssmParam = new HashMap<String, String>();
                ssmParam.put(DB_PARAM_GLBL_CMPY_CD, sMessage.glblCmpyCd_Z1.getValue());
                ssmParam.put(DB_PARAM_POD_STS_TP_FOR_SCR_CD, sMessage.podStsTpForScrCd_H2.getValue());
                S21SsmEZDResult podStsRs = NLBL0090Query.getInstance().getPODStatusListByPODStatusTypeCd(ssmParam);
                if (podStsRs.isCodeNormal()) {
                    List<Map> podStsList = (List<Map>) podStsRs.getResultObject();
                    for (int i = 0; i < podStsList.size(); i++) {
                        Map m = podStsList.get(i);
                        podStsCdList.add((String) m.get(POD_STS_DBCOLUMN[0]));
                    }
                }
            }
        }
        return podStsCdList;
    }

    private void editCarrTrkUrl(NLBL0090SMsg sMessage, int j) {
        if (!ZYPCommonFunc.hasValue(sMessage.A.no(j).carrTrkUrl_A1)) {
            return;
        }
        String url = sMessage.A.no(j).carrTrkUrl_A1.getValue();
        Pattern pattern = Pattern.compile(REPLACE_CHAR);
        Matcher matcher = pattern.matcher(url);
        String str = matcher.replaceAll(sMessage.A.no(j).proNum_A1.getValue());
        sMessage.A.no(j).carrTrkUrl_A1.setValue(str);
    }

    /**
     * <pre>
     * Process of onClick "PagePrev" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_PagePrev(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        if (TBL_NAME_A.equals(cMessage.xxTblNm_Z1.getValue())) {
            // copy data from SMsg onto CMsg
            int pagenationFrom = cMessage.xxPageShowFromNum_A1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMessage.A.length(); i++) {
                if (i < sMessage.A.getValidCount()) {
                    EZDMsg.copy(sMessage.A.no(i), null, cMessage.A.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMessage.A.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            cMessage.xxPageShowFromNum_A1.setValue(pagenationFrom);
            cMessage.xxPageShowToNum_A1.setValue(pagenationFrom + cMessage.A.getValidCount() - 1);

            resetSearchCondition(cMessage, sMessage);

        } else {
            // copy data from SMsg onto CMsg
            int pagenationFrom = cMessage.xxPageShowFromNum_B1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMessage.B.length(); i++) {
                if (i < sMessage.B.getValidCount()) {
                    EZDMsg.copy(sMessage.B.no(i), null, cMessage.B.no(i - pagenationFrom), null);
                } else {
                    break;
                }
            }
            cMessage.B.setValidCount(i - pagenationFrom);

            // set value to pagenation items
            pagenationFrom = pagenationFrom + 1;
            cMessage.xxPageShowFromNum_B1.setValue(pagenationFrom);
            cMessage.xxPageShowToNum_B1.setValue(pagenationFrom + cMessage.B.getValidCount() - 1);

        }
    }

    /**
     * <pre>
     * Process of onClick "PageNext" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_PageNext(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        if (TBL_NAME_A.equals(cMessage.xxTblNm_Z1.getValue())) {
            int pagenationFrom = cMessage.xxPageShowFromNum_A1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMessage.A.length(); i++) {
                if (i < sMessage.A.getValidCount()) {
                    EZDMsg.copy(sMessage.A.no(i), null, cMessage.A.no(i - pagenationFrom), null);
                } else {
                    cMessage.A.setValidCount(i - pagenationFrom);
                    break;
                }
            }

            cMessage.A.setValidCount(i - pagenationFrom);
            // set value to pagenation items
            cMessage.xxPageShowFromNum_A1.setValue(pagenationFrom + 1);
            cMessage.xxPageShowToNum_A1.setValue(pagenationFrom + cMessage.A.getValidCount());

            resetSearchCondition(cMessage, sMessage);

        } else {
            int pagenationFrom = cMessage.xxPageShowFromNum_B1.getValueInt();
            int i = pagenationFrom;
            for (; i < pagenationFrom + cMessage.B.length(); i++) {
                if (i < sMessage.B.getValidCount()) {
                    EZDMsg.copy(sMessage.B.no(i), null, cMessage.B.no(i - pagenationFrom), null);
                } else {
                    cMessage.B.setValidCount(i - pagenationFrom);
                    break;
                }
            }
            cMessage.B.setValidCount(i - pagenationFrom);
            // set value to pagenation items
            cMessage.xxPageShowFromNum_B1.setValue(pagenationFrom + 1);
            cMessage.xxPageShowToNum_B1.setValue(pagenationFrom + cMessage.B.getValidCount());

        }
    }

    /**
     * <pre>
     * Process of onClick "B/L Detail Tab" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_TAB_BOLDetail(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        // select index at Radio Button
        int index = cMessage.xxRadioBtn_A1.getValue().intValue();

        // ******* create search criteria ************
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("bolNum_A1", cMessage.A.no(index).bolNum_A1.getValue());
        ssmParam.put("whCd_A1", cMessage.A.no(index).whCd_A1.getValue());
        if (ZYPCommonFunc.hasValue(cMessage.A.no(index).proNum_A1)) {
            ssmParam.put("proNum_A1", cMessage.A.no(index).proNum_A1.getValue());
        }
        if (ZYPCommonFunc.hasValue(cMessage.A.no(index).carrCd_A1)) {
            ssmParam.put("carrCd_A1", cMessage.A.no(index).carrCd_A1.getValue());
        }

        sMessage.B.clear();
        sMessage.B.setValidCount(0);
        sMessage.xxPageShowFromNum_B1.setValue(0);
        sMessage.xxPageShowToNum_B1.setValue(0);
        sMessage.xxPageShowOfNum_B1.setValue(0);

        ssmParam.put(DB_PARAM_OTBD, INBD_OTBD.OUTBOUND);
        ssmParam.put(DB_PARAM_SMSG, sMessage);

        // ******* execute search ************
        S21SsmEZDResult ssmResult = NLBL0090Query.getInstance().getBOLTrackingDetailList(ssmParam, sMessage);

        if (ssmResult.isCodeNotFound()) {
            cMessage.setMessageInfo(NZZM0000E);
            return;
        }

        int queryResCnt = ssmResult.getQueryResultCount();
        if (queryResCnt > sMessage.B.length()) {
            cMessage.setMessageInfo(NZZM0001W);
            queryResCnt = sMessage.B.length();
        }

        // Copy search result SMsg -> CMsg (one page)
        int j = 0;
        for (; j < sMessage.B.getValidCount(); j++) {
            if (j == cMessage.B.length()) {
                break;
            }
            EZDMsg.copy(sMessage.B.no(j), null, cMessage.B.no(j), null);
        }
        cMessage.B.setValidCount(j);

        cMessage.xxPageShowFromNum_B1.setValue(1);
        cMessage.xxPageShowToNum_B1.setValue(cMessage.B.getValidCount());
        cMessage.xxPageShowOfNum_B1.setValue(queryResCnt);

        // ******* set Header items ************
        setHeaderItems(cMessage, sMessage, index);
    }

    private void setHeaderItems(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage, int index) {
        // set selected row's data to Header items
        cMessage.bolNum_H1.setValue(cMessage.A.no(index).bolNum_A1.getValue());
        cMessage.proNum_H1.setValue(cMessage.A.no(index).proNum_A1.getValue());
        cMessage.carrCd_H2.setValue(cMessage.A.no(index).carrCd_A1.getValue());
        cMessage.carrNm_H1.setValue(cMessage.A.no(index).locNm_A1.getValue());
        cMessage.whCd_H1.setValue(cMessage.A.no(index).whCd_A1.getValue());
        cMessage.sellToCustCd_H2.setValue(cMessage.A.no(index).sellToCustCd_A1.getValue());

        cMessage.dsAcctNm_H2.clear();
        searchSellToName(cMessage);

        cMessage.shipToCustCd_H2.setValue(cMessage.A.no(index).shipToCustCd_A1.getValue());

        cMessage.dsAcctNm_H3.clear();
        searchShipToName(cMessage);

        cMessage.podStsTpForScrCd_H2.setValue(cMessage.A.no(index).podStsTpForScrCd_A1.getValue());

        // create PodStsPulldownList which is related with
        // podStsTpForScrCd
        String podStsTpForScrCd = cMessage.A.no(index).podStsTpForScrCd_A1.getValue();
        createPodStsPulldownList(cMessage, podStsTpForScrCd);

        cMessage.podStsCd_H2.setValue(cMessage.A.no(index).podStsCd_A2.getValue());

        // set search conditon to Header items
        cMessage.podStsDt_H1.setValue(sMessage.podStsDt_H1.getValue());
        cMessage.podStsDt_H2.setValue(sMessage.podStsDt_H2.getValue());
    }

    /**
     * <pre>
     * Process of onClick "B/L Tracking Tab" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_TAB_BOLTracking(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {
        resetSearchCondition(cMessage, sMessage);
    }

    /**
     * <pre>
     * Process of onClick "sort link" event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_TBLColumnSort(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        String sortTblNm = cMessage.xxSortTblNm.getValue();
        String sortItemNm = cMessage.xxSortItemNm.getValue();
        String sortOrdBy = cMessage.xxSortOrdByTxt.getValue();

        // Table:A
        if (TBL_NAME_A.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMessage.A, sMessage.A.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMessage.A.getValidCount());

            int i = 0;
            for (; i < sMessage.A.getValidCount(); i++) {
                if (i == cMessage.A.length()) {
                    break;
                }
                EZDMsg.copy(sMessage.A.no(i), null, cMessage.A.no(i), null);
            }
            cMessage.A.setValidCount(i);

            // display first page
            cMessage.xxPageShowFromNum_A1.setValue(1);
            cMessage.xxPageShowToNum_A1.setValue(cMessage.A.getValidCount());

        } else if (TBL_NAME_B.equals(sortTblNm)) {

            // execute column sort function
            S21SortTarget sortTarget = new S21SortTarget(sMessage.B, sMessage.B.no(0).getBaseContents());
            S21SortKey sortKey = new S21SortKey();
            sortKey.add(sortItemNm, sortOrdBy);
            S21EZDMsgArraySort.sort(sortTarget, sortKey, 0, sMessage.B.getValidCount());

            // SMsg -> CMsg
            int i = 0;
            for (; i < sMessage.B.getValidCount(); i++) {
                if (i == cMessage.B.length()) {
                    break;
                }
                EZDMsg.copy(sMessage.B.no(i), null, cMessage.B.no(i), null);
            }
            cMessage.B.setValidCount(i);

            // display first page
            cMessage.xxPageShowFromNum_B1.setValue(1);
            cMessage.xxPageShowToNum_B1.setValue(cMessage.B.getValidCount());
        }
    }

    /**
     * <pre>
     * Process of onClick "Download" button event
     * </pre>
     * @param cMessage Business Component Interface Message
     * @param sMessage Global area information
     */
    private void doProcess_NLBL0090Scrn00_CMN_Download(NLBL0090CMsg cMessage, NLBL0090SMsg sMessage) {

        resetSearchCondition(cMessage, sMessage);

        // ******* create search criteria ************
        // get search criteria from last search event
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(DB_PARAM_SMSG, sMessage);

        // "POD Status For Search"
        List<String> podStsCdList = getPodStsCdListForCriteria(sMessage);
        ssmParam.put(DB_PARAM_POD_STS_CD_LIST, podStsCdList);

        // condition for SCE_ORDER_TYPE
        ssmParam.put(DB_PARAM_OTBD, INBD_OTBD.OUTBOUND);

        // If one of these items have value, POD searched by
        // INNER-JOIN. If not, searched by OUTER-JOIN.
        if (!podStsCdList.isEmpty()) {
            ssmParam.put(DB_PARAM_POD_INNER_JOIN, ZYPConstant.FLG_ON_Y);
        } else {
            if (DT_TP_POD.equals(sMessage.xxDtTpCd_H2.getValue())) {
                if (ZYPCommonFunc.hasValue(sMessage.podStsDt_H1) || ZYPCommonFunc.hasValue(sMessage.podStsDt_H2)) {
                    ssmParam.put(DB_PARAM_POD_INNER_JOIN, ZYPConstant.FLG_ON_Y);
                }
            }
        }

        if (DT_TP_SHIP.equals(sMessage.xxDtTpCd_H2.getValue())) {
            ssmParam.put(DB_PARAM_SHIPPED_DATE_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(DB_PARAM_TIME_MIN, DB_PARAM_VALUE_FOR_HHMMSS_MIN);
            ssmParam.put(DB_PARAM_TIME_MAX, DB_PARAM_VALUE_FOR_HHMMSS_MAX);
        } else if (DT_TP_POD.equals(sMessage.xxDtTpCd_H2.getValue())) {
            ssmParam.put(DB_PARAM_POD_STS_DATE_FLG, ZYPConstant.FLG_ON_Y);
        }

        final boolean isNormalEnd = NLBL0090Query.getInstance().getBOLTrackingListForCsv(ssmParam, cMessage);

        // nothing data.
        if (!isNormalEnd) {
            return;
        }
    }

    /**
     * <pre>
     * Parameter has All warehouse authority, or not?
     * </pre>
     * @param dataSecurityAttrList user's warehouse <data security>
     * @return true / parameter : data security List has All warehouse
     * authority, false / has not All warehouse authority
     */
    private static boolean hasAllWHAuthority(List<S21DataSecurityAttributeData> dataSecurityAttrList) {
        for (int i = 0; i < dataSecurityAttrList.size(); i++) {
            if (ASTERISK.equals(dataSecurityAttrList.get(i).getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Create Pulldown List for initialleze process. It makes
     * [Code:Value].
     * @param cMsgValue1 EZDCStringItemArray
     * @param cMsgValue2 EZDCStringItemArray
     * @param pullDownList List<Map>
     * @param dbColumn String[]
     */
    private static void initPullDownCreate(EZDCStringItemArray cMsgValue1, EZDCStringItemArray cMsgValue2, List<Map> pullDownList, String[] dbColumn) {

        int j = 0;
        for (int i = 0; i < cMsgValue1.length(); i++) {

            if (ZYPCommonFunc.hasValue(cMsgValue1.no(i))) {
                continue;
            }

            if (j >= pullDownList.size()) {
                break;
            }

            Map pullDownData = pullDownList.get(j);

            cMsgValue1.no(i).setValue((String) pullDownData.get(dbColumn[0]));
            cMsgValue2.no(i).setValue(ZYPCommonFunc.concatString((String) pullDownData.get(dbColumn[0]), COLON, (String) pullDownData.get(dbColumn[1])));
            j = j + 1;
        }
    }

    /**
     * The method explanation: updates the search condition area's
     * input value by the input value when the search button was
     * pressed <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param cMsg Business Component Interface Message
     * @param sMsg Global area information
     */
    private void resetSearchCondition(NLBL0090CMsg cMsg, NLBL0090SMsg sMsg) {

        ZYPEZDItemValueSetter.setValue(cMsg.bolNum_H1, sMsg.bolNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.proNum_H1, sMsg.proNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.carrCd_H2, sMsg.carrCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.carrNm_H1, sMsg.carrNm_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.whCd_H1, sMsg.whCd_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.sellToCustCd_H2, sMsg.sellToCustCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H2, sMsg.dsAcctNm_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.shipToCustCd_H2, sMsg.shipToCustCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.dsAcctNm_H3, sMsg.dsAcctNm_H3.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.podStsTpForScrCd_H2, sMsg.podStsTpForScrCd_H2.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.trxHdrNum_H1, sMsg.trxHdrNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.soNum_H1, sMsg.soNum_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.podStsDt_H1, sMsg.podStsDt_H1.getValue());
        ZYPEZDItemValueSetter.setValue(cMsg.podStsDt_H2, sMsg.podStsDt_H2.getValue());

        // create PodStsPulldownList which is related with
        // podStsTpForScrCd
        String podStsTpForScrCd = sMsg.podStsTpForScrCd_H2.getValue();
        createPodStsPulldownList(cMsg, podStsTpForScrCd);

        ZYPEZDItemValueSetter.setValue(cMsg.podStsCd_H2, sMsg.podStsCd_H2.getValue());
    }

    /**
     * Crear Tracking Table Info ( Table:'A' ).
     * @param bMessage
     */
    private void clearTrckngTableInfo(NLBL0090CMsg cMsg) {
        cMsg.xxRadioBtn_A1.clear();
        ZYPTableUtil.clear(cMsg.A);
        cMsg.xxPageShowFromNum_A1.clear();
        cMsg.xxPageShowToNum_A1.clear();
        cMsg.xxPageShowOfNum_A1.clear();
    }

    /**
     * Crear Tracking Table Info ( 'B' table ).
     * @param bMessage
     */
    private void clearDetailTableInfo(NLBL0090CMsg cMsg) {
        ZYPTableUtil.clear(cMsg.B);
        cMsg.xxPageShowFromNum_B1.clear();
        cMsg.xxPageShowToNum_B1.clear();
        cMsg.xxPageShowOfNum_B1.clear();
    }
}

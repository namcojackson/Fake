/*
 * <Pre>Copyright(c)2015 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3120;

import static business.blap.NLBL3120.constant.NLBL3120Constant.BIZ_ID;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SEARCH_RWS;
import static business.blap.NLBL3120.constant.NLBL3120Constant.SEARCH_SO;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLBL3120.common.NLBL3120CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SO_CUST_DATA_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21InfoLogOutput;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Workload Balancing and Monitor
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/03   Fujitsu         S.Yoshida       Create          N/A
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#18993
 * 2017/06/28   CITS            T.Kikuhara      Update          QC#19137
 * 2018/10/25   CITS            M.Naito         Update          QC#28867
 *</pre>
 */
public final class NLBL3120Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3120Query MY_INSTANCE = new NLBL3120Query();

    /**
     * Constructor.
     */
    private NLBL3120Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLBL3120Query
     */
    public static NLBL3120Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getMgrAndSupvPsnCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMgrAndSupvPsnCd() {
        // Create Parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("loginPsnId", getContextUserInfo().getUserId());

        return getSsmEZDClient().queryObject("getMgrAndSupvPsnCd", ssmParam);
    }

    /**
     * searchSchd
     * @param cMsg NLBL3120CMsg
     * @param sMsg NLBL3120SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchSchd(NLBL3120CMsg cMsg, NLBL3120SMsg sMsg) {
        // Create Parameter
        Map<String, Object> ssmParam = createSearchCond(cMsg, getGlobalCompanyCode());
        ssmParam.put("rownum", sMsg.A.length() + 1);

        // START 2018/10/25 M.Naito [QC#28867,ADD]
        S21InfoLogOutput.println("### Set Parameters (SQLID: searchSchd) ###################################");
        S21InfoLogOutput.println(ssmParam.toString());
        // END 2018/10/25 M.Naito [QC#28867,ADD]
        return getSsmEZDClient().queryObjectList("searchSchd", ssmParam);
    }

    /**
     * getRwsSchdDtlForUpd
     * @param sMsgALine NLBL3120_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRwsSchdDtlForUpd(NLBL3120_ASMsg sMsgALine) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("rwsNum", sMsgALine.rwsNum_A1.getValue());
        ssmParam.put("schdPickUpDt", sMsgALine.schdPickUpDt_AK.getValue());
        ssmParam.put("shpgSvcLvlCd", sMsgALine.shpgSvcLvlCd_AK.getValue());
        ssmParam.put("carrCd", sMsgALine.carrCd_AK.getValue());
        ssmParam.put("schdCoordPsnCd", sMsgALine.schdCoordPsnCd_AK.getValue());
        ssmParam.put("schdCoordStsCd", sMsgALine.schdCoordStsCd_AK.getValue());

        return getSsmEZDClient().queryObjectList("getRwsSchdDtlForUpd", ssmParam);
    }


    /**
     * getCarrCd
     * @param carrNm Carrier Name
     * @return S21SsmEZDResult
     */
    public String getCarrCd(String carrNm) {
        Map<String, String> carrMap = getCarr(null, carrNm);
        if (carrMap == null) {
            return null;
        }
        return carrMap.get("VND_CD");
    }

    /**
     * getCarrNm
     * @param carrCd Carrier Code
     * @return S21SsmEZDResult
     */
    public String getCarrNm(String carrCd) {
        Map<String, String> carrMap = getCarr(carrCd, null);
        if (carrMap == null) {
            return null;
        }
        return carrMap.get("LOC_NM");
    }

    /**
     * getCarrNm
     * @param carrCd Carrier Code
     * @param carrNm Carrier Name
     * @return S21SsmEZDResult
     */
    public Map<String, String> getCarr(String carrCd, String carrNm) {
        Map<String, Object> params = new HashMap<String, Object>();
        String glblCmpyCd = getGlobalCompanyCode();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        params.put("carrCd", carrCd);
        params.put("carrNm", carrNm);

        Map<String, String> vndTpCd = new HashMap<String, String>();
        vndTpCd.put("otbdCarr", VND_TP.OUTBOUND_CARRIER);
        params.put("vndTpCd", vndTpCd);

        Map<String, String> rgtnStsCd = new HashMap<String, String>();
        rgtnStsCd.put("readyOrdTake", RGTN_STS.READY_FOR_ORDER_TAKING);
        params.put("rgtnStsCd", rgtnStsCd);

        S21SsmEZDResult res = getSsmEZDClient().queryObject("getCarr", params);
        if (!S21SsmEZDResult.RESULT_CODE_NORMAL.equals(res.getResultCode())) {
            return null;
        }

        return (Map<String, String>) res.getResultObject();
    }

    /**
     * getSavedSearchOptionList
     * @param usrId user id
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

    /**
     * createSearchCond
     * @param cMsg NLBL3120CMsg
     * @param glblCmpyCd String
     * @return Map<String, Object>
     */
    public Map<String, Object> createSearchCond(NLBL3120CMsg cMsg, String glblCmpyCd) {
        // Create Parameter
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        createSearchCondInit(ssmParam, glblCmpyCd);

        ssmParam.put("trxHdrNum", cMsg.trxHdrNum.getValue());
        ssmParam.put("dsOrdCatgCd", cMsg.dsOrdCatgCd.getValue());
        ssmParam.put("dsOrdTpCd", cMsg.dsOrdTpCd.getValue());
        ssmParam.put("t_MdlNm", cMsg.t_MdlNm.getValue());
        ssmParam.put("svcConfigMstrPk", cMsg.svcConfigMstrPk.getValue());
        ssmParam.put("soNum", cMsg.soNum.getValue());
        ssmParam.put("dsSoLineStsCd", cMsg.dsSoLineStsCd.getValue());
        ssmParam.put("rwsNum", cMsg.rwsNum.getValue());
        ssmParam.put("rwsStsCd", cMsg.rwsStsCd.getValue());
        ssmParam.put("schdCoordStsCd", cMsg.schdCoordStsCd.getValue());
        ssmParam.put("rtlWHCd", cMsg.rtlWhCd.getValue());
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));

        if (ZYPCommonFunc.hasValue(cMsg.schdCoordPsnCd)) {
            ssmParam.put("schdCoordPsnCd", cMsg.schdCoordPsnCd.getValue());

        } else {

            if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_CA.getValue())) {
                if (!ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_CN.getValue())) {
                    ssmParam.put("coordAsg", ZYPConstant.CHKBOX_ON_Y);
                }

            } else {
                if (ZYPConstant.CHKBOX_ON_Y.equals(cMsg.xxChkBox_CN.getValue())) {
                    ssmParam.put("coordNotAsg", ZYPConstant.CHKBOX_ON_Y);
                }
            }
        }

        ssmParam.put("supvPsnCd", cMsg.supvPsnCd.getValue());
        ssmParam.put("mgrPsnCd", cMsg.mgrPsnCd.getValue());
        ssmParam.put("slsRepOrSlsTeamTocCd", cMsg.slsRepOrSlsTeamTocCd.getValue());
        ssmParam.put("rddDt_FR", cMsg.rddDt_FR.getValue());
        ssmParam.put("rddDt_TO", cMsg.rddDt_TO.getValue());
        ssmParam.put("schdCoordDt_FR", cMsg.schdCoordDt_FR.getValue());
        ssmParam.put("schdCoordDt_TO", cMsg.schdCoordDt_TO.getValue());

        // set Search Condition
        int srchCond = NLBL3120CommonLogic.selectSearchCondition(cMsg);

        if (SEARCH_SO == srchCond) {
            ssmParam.put("dispSo", ZYPConstant.CHKBOX_ON_Y);
        } else if (SEARCH_RWS == srchCond) {
            ssmParam.put("dispRws", ZYPConstant.CHKBOX_ON_Y);
        } else {
            ssmParam.put("dispSo", ZYPConstant.CHKBOX_ON_Y);
            ssmParam.put("dispRws", ZYPConstant.CHKBOX_ON_Y);
        }

        return ssmParam;
    }

    /**
     * createSearchCondInit
     * @param ssmParam
     * @param glblCmpyCd
     * @return ssmParam
     */
    private static Map<String, Object> createSearchCondInit(Map<String, Object> ssmParam, String glblCmpyCd) {
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", ZYPDateUtil.getSalesDate(glblCmpyCd));
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("soCustDataTpShipTo", SO_CUST_DATA_TP.SHIP_TO);
        ssmParam.put("shpgStsValidated", SHPG_STS.VALIDATED);
        return ssmParam;
    }

    /**
     * getAcctCarrCnt
     * @param glblCmpyCd String
     * @param slsDt String
     * @param dsAcctNum String
     * @param carrCd String
     * @param carrAcctNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctCarrCnt(String glblCmpyCd, String slsDt, String dsAcctNum, String carrCd, String carrAcctNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("slsDt", slsDt);
        ssmParam.put("dsAcctNum", dsAcctNum);
        ssmParam.put("carrCd", carrCd);
        ssmParam.put("carrAcctNum", carrAcctNum);

        return getSsmEZDClient().queryObject("getAcctCarrCnt", ssmParam);
    }

    // QC#18993 ADD START
    /**
     * check coordinator exist
     * @param glblCmpyCd String
     * @param sMsgALine NLBL3120_ASMsg
     * @param slsDt String
     * @return BigDecimal
     */
    public S21SsmEZDResult chkCoord(String glblCmpyCd, NLBL3120_ASMsg sMsgALine, String slsDt) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("schdCoordPsnCd01", sMsgALine.schdCoordPsnCd_A1.getValue());
        params.put("rtlWhCd01", sMsgALine.rtlWhCd_A1.getValue());
        params.put("effFromDt01", slsDt);
        params.put("effThruDt01", slsDt);
        return getSsmEZDClient().queryObject("chkCoord", params);
    }
    // QC#18993 ADD END

    // QC#19137 ADD START
    /**
     * getSchdCoordStsList
     * @param glblCmpyCd String
     * @param sMsgALine NLBL3120_ASMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSchdCoordStsList(String glblCmpyCd, NLBL3120_ASMsg sMsgALine) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("schdCoordStsCd", sMsgALine.schdCoordStsCd_A1.getValue());
        params.put("manUpdPrtyNum", sMsgALine.manUpdPrtyNum_A1.getValue());
        params.put("flgY", ZYPConstant.FLG_ON_Y);
        return getSsmEZDClient().queryObjectList("getSchdCoordStsList", params);
    }
    // QC#19137 ADD END

}
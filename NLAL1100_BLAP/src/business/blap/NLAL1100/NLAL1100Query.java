/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NLAL1100;

import java.util.HashMap;
import java.util.Map;

import business.blap.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BIZ_AREA_ORG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.GNRN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MSG_CTRL_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLAL1100 Manage RMA Orders
 * Function Name : the data base access processing by SSM
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/21/2015   CITS            M.Ito           Create          N/A
 * 05/16/2016   CSAI            Y.Imazu         Update          QC#7972
 * 12/07/2023   Hitachi         K.Ishizuka      Update          QC#61300
 * 
 *</pre>
 */
public final class NLAL1100Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLAL1100Query MY_INSTANCE = new NLAL1100Query();

    /**
     * Constructor.
     */
    private NLAL1100Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NLAL1100Query
     */
    public static NLAL1100Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Pulldown list of SearchOption
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSearchOptionPulldownList(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("srchOptAplId", NLAL1100Constant.BIZ_APP_ID);
        ssmParam.put("srchOptUsrId", cMsg.srchOptUsrId_U1.getValue());

        return getSsmEZDClient().queryObjectList("getSearchOptionPulldownList", ssmParam);
    }

    /**
     * Get Pulldown list of Order Aging Bucket
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdAgingBcktPulldownList(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());

        return getSsmEZDClient().queryObjectList("getOrdAgingBcktPulldownList", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getAcctNm" in [NLAL1100Query.xml]
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAcctNm(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("shipToCustCd", cMsg.shipToCustCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getAcctNm", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getCarrNm" in [NLAL1100Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param carrCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrNm(String glblCmpyCd, String carrCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("carrCd", carrCd);

        return getSsmEZDClient().queryObjectList("getCarrNm", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getCarrSvcLvlCnt" in [NLAL1100Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param carrCd String
     * @param pickUpSvcLvlCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCarrSvcLvlCnt(String glblCmpyCd, String carrCd, String pickUpSvcLvlCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("carrCd", carrCd);
        ssmParam.put("pickUpSvcLvlCd", pickUpSvcLvlCd);

        return getSsmEZDClient().queryObject("getCarrSvcLvlCnt", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="search" in [NLAL1100Query.xml]
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @param sMsg NLAL1100SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NLAL1100CMsg cMsg, NLAL1100SMsg sMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("slsDt", cMsg.slsDt_G1.getValue());
        ssmParam.put("flgOn", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgOff", ZYPConstant.FLG_OFF_N);
        ssmParam.put("subSysId", "NWZ");
        ssmParam.put("procId", "OM");
        ssmParam.put("docTpCc", "RT");
        ssmParam.put("eventId", "RMA Closed");
        ssmParam.put("rownum", sMsg.A.length());

        return getSsmEZDClient().queryEZDMsgArray("search", ssmParam, sMsg.A);
    }

    /**
     * searchComment
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchComment(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("msgCtrlTpCd", MSG_CTRL_TP.RMA_COMMENT);
        ssmParam.put("flgOff", ZYPConstant.FLG_OFF_N);
        ssmParam.put("rownum", cMsg.C.length());

        return getSsmEZDClient().queryEZDMsgArray("searchComment", ssmParam, cMsg.C);
    }

    /**
     * getPsnNm
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNm(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("psnCd", cMsg.psnCd_H1.getValue());

        return getSsmEZDClient().queryObject("getPsnNm", ssmParam);
    }

    /**
     * Get Pulldown list of Return Line Display Status
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtrnLineDplyStsPulldownList(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());

        return getSsmEZDClient().queryObjectList("getRtrnLineDplyStsPulldownList", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getRtlWhNm" in [NLAL1100Query.xml]
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhNm(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rtlWhCd", cMsg.rtlWhCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getRtlWhNm", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getRtlSwhNm" in [NLAL1100Query.xml]
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlSwhNm(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rtlWhCd", cMsg.rtlWhCd_H1.getValue());
        ssmParam.put("rtlSwhCd", cMsg.rtlSwhCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getRtlSwhNm", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="getSlsRepName" in [NLAL1100Query.xml]
     * </pre>
     * @param cMsg NLAL1100CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSlsRepName(NLAL1100CMsg cMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("tocCd", cMsg.slsRepBrCd_H1.getValue());

        return getSsmEZDClient().queryObjectList("getSlsRepName", ssmParam);
    }
    
    // START 2023/12/07 K.Ishizuka [QC#61300,ADD]
    /**
     * getEmlAddrFromTaskSvcBrMgr
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getEmlAddrFromTaskSvcBrMgr(NLAL1100CMsg cMsg, String rmaNum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rmaNum", rmaNum);
        return getSsmEZDClient().queryObject("getEmlAddrFromTaskSvcBrMgr", ssmParam);
    }
    
    /**
     * getSvcBrMngrEmlAddr
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcBrMngrEmlAddr(NLAL1100CMsg cMsg, String rmaNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rmaNum", rmaNum);
        ssmParam.put("orgFuncRoleTp", NLAL1100Constant.ORG_FUNC_ROLE_TP_BR_SVC_MGR);
        ssmParam.put("gnrnTpCurrent", GNRN_TP.CURRENT);
        ssmParam.put("gnrnTpFuture", GNRN_TP.FUTURE);
        ssmParam.put("gnrnTpDelete", GNRN_TP.DELETE);
        ssmParam.put("service", BIZ_AREA_ORG.SERVICE);
        ssmParam.put("slsDt", cMsg.slsDt_G1.getValue());
        return getSsmEZDClient().queryObject("getSvcBrMngrEmlAddr", ssmParam);
    }
    
    /**
     * getSvcTaskInfoFromRmaNum
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcTaskInfoFromRmaNum(NLAL1100CMsg cMsg, String rmaNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rmaNum", rmaNum);

        return getSsmEZDClient().queryObject("getSvcTaskInfoFromRmaNum", ssmParam);
    }
    
    /**
     * getDeliveryScheduleMailInfo
     * @param cMsg NLAL1100CMsg
     * @param rmaNum String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDeliveryScheduleMailInfo(NLAL1100CMsg cMsg, String rmaNum) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd_G1.getValue());
        ssmParam.put("rmaNum", rmaNum);
        return getSsmEZDClient().queryObjectList("getDeliveryScheduleMailInfo", ssmParam);
    }
    // END   2023/12/07 K.Ishizuka [QC#61300,ADD]
}
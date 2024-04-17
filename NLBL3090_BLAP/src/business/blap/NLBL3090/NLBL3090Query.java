package business.blap.NLBL3090;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.OTBD_CARR_VTMsg;
import business.db.OTBD_CARR_VTMsgArray;
import business.db.S21_PSN_VTMsg;
import business.db.S21_PSN_VTMsgArray;
import business.db.STTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Business ID : NLBL3090 Call Coordinator Assignment Setup
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 12/18/2015   CITS            Yasushi Nomura  Create          N/A
 * 04/01/2016   CSAI            D.Fukaya        Update          QC#6090
 *</pre>
 */
public final class NLBL3090Query extends S21SsmEZDQuerySupport {
    /** Singleton instance. */
    private static final NLBL3090Query MY_INSTANCE = new NLBL3090Query();

    /**
     * <pre>
     * Constructor.
     * </pre>
     */
    private NLBL3090Query() {
        super();
    }

    /**
     * <pre>
     * Get the NLBL3090Query instance.
     * </pre>
     * @return NLBL3090Query instance
     */
    public static NLBL3090Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * search Assign Tab
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchAssign(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchAssign", ssmParam);
    }

    /**
     * search Coordination Tab
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchCoordination(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchCoordination", ssmParam);
    }

    /**
     * get Coordination Count
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public BigDecimal getCoordinationCount(Map<String, Object> ssmParam) {
        return (BigDecimal) getSsmEZDClient().queryObject("getCoordinationCount", ssmParam).getResultObject();
    }

    /**
     * getCoordinatorAssign by row id
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoordinatorAssign(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("getCoordinatorAssign", ssmParam);
    }

    /**
     * searchWh
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchWh(Map<String, Object> ssmParam) {
        return getSsmEZDClient().queryObjectList("searchWh", ssmParam);
    }

//    /**
//     * <pre>
//     * execute SSM id="countSchdCoordAsgReln" in [NMAL6820Query.xml]
//     * </pre>
//     * @param glblCmpyCd String
//     * @param schdCoordAsgRelnPk BigDecimal
//     * @param rtlWhCd String
//     * @param mgrPsnCd String
//     * @param supvPsnCd String
//     * @param schdCoodPsnCd String
//     * @return S21SsmEZDResult
//     */
//    public S21SsmEZDResult countSchdCoordAsgReln(String glblCmpyCd, BigDecimal schdCoordAsgRelnPk, String rtlWhCd, String mgrPsnCd, String supvPsnCd, String schdCoodPsnCd) {
//
//        Map<String, Object> ssmParam = new HashMap<String, Object>();
//
//        ssmParam.put("glblCmpyCd", glblCmpyCd);
//        ssmParam.put("schdCoordAsgRelnPk", schdCoordAsgRelnPk);
//        ssmParam.put("rtlWhCd", rtlWhCd);
//        ssmParam.put("mgrPsnCd", mgrPsnCd);
//        ssmParam.put("supvPsnCd", supvPsnCd);
//        ssmParam.put("schdCoodPsnCd", schdCoodPsnCd);
//
//        return getSsmEZDClient().queryObject("countSchdCoordAsgReln", ssmParam);
//    }

    /**
     * <pre>
     * getAssignTMsgListForDuplicationCheck
     * </pre>
     * 
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAssignTMsgListForDuplicationCheck(Map<String, String> queryParam) {

        return getSsmEZDClient().queryObjectList("getAssignTMsgListForDuplicationCheck", queryParam);
    }


    /**
     * <pre>
     * getCoordinationListForDuplicationCheck
     * </pre>
     * 
     * @param queryParam SSM Query Parameter
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getCoordinationListForDuplicationCheck(Map<String, String> queryParam) {

        return getSsmEZDClient().queryObjectList("getCoordinationListForDuplicationCheck", queryParam);
    }

    /**
     * <pre>
     * execute SSM id="countSchdCoordAsg" in [NMAL6820Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param xxRowId String
     * @param rtlWhCd String
     * @param schdCoordPsnCd String
     * @param stCd String
     * @param effFromDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countSchdCoordAsg(String glblCmpyCd, String xxRowId, String rtlWhCd, String schdCoordPsnCd, String stCd, String effFromDt) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("xxRowId", xxRowId);
        ssmParam.put("rtlWhCd", rtlWhCd);
        ssmParam.put("schdCoordPsnCd", schdCoordPsnCd);
        ssmParam.put("stCd", stCd);
        ssmParam.put("effFromDt", effFromDt);

        return getSsmEZDClient().queryObject("countSchdCoordAsg", ssmParam);
    }

    /**
     * <pre>
     * execute SSM id="countS21PsnV" in [NLBL3090Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param psnCd String
     * @return boolean
     */
    public boolean existS21PsnV(String glblCmpyCd, String psnCd) {

        S21_PSN_VTMsg tMsg = new S21_PSN_VTMsg();
        tMsg.setConditionValue("psnCd01", psnCd);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setSQLID("001");

        S21_PSN_VTMsgArray tMsgAry = (S21_PSN_VTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgAry == null || tMsgAry.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * execute SSM id="countSt" in [NLBL3090Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param stCd String
     * @return boolean
     */
    public boolean existSt(String glblCmpyCd, String stCd) {

        STTMsg tMsg = new STTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.stCd, stCd);

        tMsg = (STTMsg) EZDTBLAccessor.findByKey(tMsg);

        if (tMsg == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * <pre>
     * execute SSM id="countCarrier" in [NLBL3090Query.xml]
     * </pre>
     * @param glblCmpyCd String
     * @param carrCd String
     * @return boolean
     */
    public boolean existCarrier(String glblCmpyCd, String carrCd) {

        OTBD_CARR_VTMsg tMsg = new OTBD_CARR_VTMsg();
        tMsg.setConditionValue("carrCd01", carrCd);
        tMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        tMsg.setSQLID("001");

        OTBD_CARR_VTMsgArray tMsgAry = (OTBD_CARR_VTMsgArray) EZDTBLAccessor.findByCondition(tMsg);

        if (tMsgAry == null || tMsgAry.length() == 0) {
            return false;
        } else {
            return true;
        }
    }
}

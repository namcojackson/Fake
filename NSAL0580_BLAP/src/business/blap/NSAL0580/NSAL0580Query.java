/**
 * <Pre>Copyright(c)2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0580;

import static business.blap.NSAL0580.constant.NSAL0580Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.SVC_MEMO_RSNTMsg;
import business.db.SVC_MEMO_RSNTMsgArray;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BLLG_CPLT_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CR_REBIL_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MEMO_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/06   Hitachi         J.Kim           Create          N/A
 * 2016/02/26   Hitachi         K.Kishimoto     Update          QC#2011
 * 2018/08/26   Hitachi         K.Kim           Update          QC#22987
 * 2019/07/18   Hitachi         A.Kohinata      Update          QC#51706
 *</pre>
 */
public final class NSAL0580Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance
     */
    private static final NSAL0580Query INSTANCE = new NSAL0580Query();

    /**
     * Private constructor
     */
    private NSAL0580Query() {
        super();
    }

    /**
     * Singleton instance getter
     * @return NSAL0580Query
     */
    public static NSAL0580Query getInstance() {
        return INSTANCE;
    }

    /**
     * getDSContractInfo
     * @param bizMsg NSAL0580CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractInfo(NSAL0580CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", bizMsg.dsContrPk);
        return getSsmEZDClient().queryObjectList("getDSContractInfo", params);
    }

    /**
     * Service Memo Reason
     * @param cMsg NSAL0580CMsg
     * @return SVC_MEMO_RSNTMsgArray
     */
    public SVC_MEMO_RSNTMsgArray getServiceMemoReasonInfo(NSAL0580CMsg cMsg) {
        SVC_MEMO_RSNTMsg inMsg = new SVC_MEMO_RSNTMsg();
        inMsg.setSQLID("001");
        inMsg.setConditionValue("glblCmpyCd01", cMsg.glblCmpyCd.getValue());
        inMsg.setConditionValue("svcMemoTpCd01", SVC_MEMO_TP.STATUS_CHANGE);
        return (SVC_MEMO_RSNTMsgArray) EZDTBLAccessor.findByCondition(inMsg);
    }

    /**
     * DS Contract Control Status
     * @param sMsg NSAL0580SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractControlStatus(NSAL0580SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        //Mod Start 02/26/2016 <QC#2011>
        params.put("dsContrCtrlStsCd", sMsg.dsContrCtrlStsCd_H);
        //Mod End   02/26/2016 <QC#2011>
        return getSsmEZDClient().queryObject("getDSContractControlStatus", params);
    }

    /**
     * DS Contract Detail
     * @param sMsg NSAL0580SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDSContractDetail(NSAL0580SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("dsContrPk", sMsg.dsContrPk);
        return getSsmEZDClient().queryObjectList("getDSContractDetail", params);
    }

    /**
     * Service Memo
     * @param sMsg NSAL0580SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSvcMemo(NSAL0580SMsg sMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", sMsg.glblCmpyCd.getValue());
        params.put("dsContractPk", sMsg.dsContrPk);
        params.put("ContractMemo", SVC_MEMO_CATG.CONTRACT_MEMO);
        params.put("StatusChange", SVC_MEMO_TP.STATUS_CHANGE);
        return getSsmEZDClient().queryObjectList("getSvcMemo", params);
    }

    //Add Start 02/26/2016 <QC#2011>
    /**
     * DS Contract Detail Info List
     * @param sMsg NSAL0580SMsg
     * @param dsContrPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContrDtlList(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrPk", dsContrPk);
        return getSsmEZDClient().queryObjectList("getContrDtlList", params);
    }

    /**
     * DS Contract Billing Meter Info List
     * @param sMsg NSAL0580SMsg
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBllgMtrList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getBllgMtrList", params);
    }

    /**
     * DS Contract Price Effectivity Info List
     * @param sMsg NSAL0580SMsg
     * @param dsContrDtlPk BigDecimal
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcEffList(String glblCmpyCd, BigDecimal dsContrDtlPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", glblCmpyCd);
        params.put("dsContrDtlPk", dsContrDtlPk);
        return getSsmEZDClient().queryObjectList("getPrcEffList", params);
    }
    //Add End   02/26/2016 <QC#2011>

    // START 2018/08/26 [QC#22987, ADD]
    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedUsgChrg(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("bllgCpltStsCd", BLLG_CPLT_STS.SCHEDULED);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedUsgChrg", param).getResultObject();
    }

    /**
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getUnapprovedCrRebil(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        List<String> svcCrRebilStsCdList = new ArrayList<String>();
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.APPROVED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.PROCESSED);
        svcCrRebilStsCdList.add(SVC_CR_REBIL_STS.CANCELLED);
        param.put("svcCrRebilStsCdList", svcCrRebilStsCdList);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getUnapprovedCrRebil", param).getResultObject();
    }
    // END 2018/08/26 [QC#22987, ADD]

    // add start 2019/07/18 QC#51706
    /**
     * getSvcMemoForManBllgHld
     * @param glblCmpyCd String
     * @param dsContrPk BigDecimal
     * @return List<BigDecimal>
     */
    public List<BigDecimal> getSvcMemoForManBllgHld(String glblCmpyCd, BigDecimal dsContrPk) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("glblCmpyCd", glblCmpyCd);
        param.put("dsContrPk", dsContrPk);
        param.put("svcMemoCatgCd", SVC_MEMO_CATG_FOR_MAN_BLLG_HLD);
        param.put("svcMemoTpCd", SVC_MEMO_TP_FOR_MAN_BLLG_HLD);
        param.put("svcMemoRsnCd", SVC_MEMO_RSN_FOR_MAN_BLLG_HLD);
        return (List<BigDecimal>) getSsmEZDClient().queryObjectList("getSvcMemoForManBllgHld", param).getResultObject();
    }
    // add end 2019/07/18 QC#51706
}

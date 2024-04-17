/*
 * <Pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC181001;

import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.STR_AUTO_SUPPLY;
import static com.canon.cusa.s21.api.NWZ.NWZC181001.constant.NWZC181001Constant.STR_INITIAL_SUPPLY;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_MDL_SPLY_RELNTMsg;
import business.db.DS_MDL_SPLY_RELNTMsgArray;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.ORD_TAKE_MDSETMsg;
import business.parts.NWZC181001PMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiTBLAccessor;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Auto Add Supply API Query
 * </pre>
 * 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/15   Fujitsu         E.Yoshitake     Create          N/A
 * 2016/02/08   Fujitsu         Y.Taoka         Update          QC#1867
 * 2016/06/20   Fujitsu         H.Nagashima     Update          QC#10386
 *</pre>
 */
public class NWZC181001Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWZC181001Query INSTANCE = new NWZC181001Query();

    /**
     * Constructor.
     */
    public NWZC181001Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWZC181001Query
     */
    public static NWZC181001Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get DS_ORD_CATGT by PK
     * @param pMsg NWZC181001PMsg
     * @return DS_ORD_CATGTMsg
     */
    public DS_ORD_CATGTMsg getDsOrdCatg(NWZC181001PMsg pMsg) {

        DS_ORD_CATGTMsg dsOrdCatgTMsg = new DS_ORD_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdCatgTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdCatgTMsg.dsOrdCatgCd, pMsg.dsOrdCatgCd);
        return (DS_ORD_CATGTMsg) S21CacheTBLAccessor.findByKey(dsOrdCatgTMsg);
    }

    /**
     * Get DS_ORD_TP by PK
     * @param pMsg NWZC181001PMsg
     * @return DS_ORD_TPTMsg
     */
    public DS_ORD_TPTMsg getDsOrdTp(NWZC181001PMsg pMsg) {

        DS_ORD_TPTMsg dsOrdTpTMsg = new DS_ORD_TPTMsg();
        ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.glblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(dsOrdTpTMsg.dsOrdTpCd, pMsg.dsOrdTpCd);
        return (DS_ORD_TPTMsg) S21CacheTBLAccessor.findByKey(dsOrdTpTMsg);
    }

    /**
     * Get MDL_NM by PK
     * @param pMsg NWZC181001PMsg
     * @return MDL_NMTMsg
     */
    public MDL_NMTMsg getMdlNm(NWZC181001PMsg pMsg) {

        MDL_NMTMsg mdlNmTMsg = new MDL_NMTMsg();
        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_GlblCmpyCd, pMsg.glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(mdlNmTMsg.t_MdlId, pMsg.mdlId);
        return (MDL_NMTMsg) S21CacheTBLAccessor.findByKey(mdlNmTMsg);
    }

    /**
     * checking auto supply info
     * @param pMsg NWZC181001PMsg
     * @return result
     */
    public boolean isAutoSplyInfo(NWZC181001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("ordCatgCtxTpCd", STR_AUTO_SUPPLY);
        BigDecimal count = (BigDecimal) getSsmEZDClient().queryObject("countAutoSplyInfo", ssmParam).getResultObject();

        if (BigDecimal.ZERO.compareTo(count) < 0) {
            return true;
        }

        return false;
    }

    /**
     * get Supply Info by Model Id
     * @param pMsg NWZC181001PMsg
     * @return DS_MDL_SPLY_RELNTMsgArray
     */
    public DS_MDL_SPLY_RELNTMsgArray getSupplyInfo(NWZC181001PMsg pMsg) {

        DS_MDL_SPLY_RELNTMsg tMsg = new DS_MDL_SPLY_RELNTMsg();
        tMsg.setSQLID("002");
        tMsg.setConditionValue("glblCmpyCd01", pMsg.glblCmpyCd.getValue());
        tMsg.setConditionValue("mdlId01", pMsg.mdlId.getValue());
        tMsg.setConditionValue("effFromDt01", pMsg.slsDt.getValue());
        tMsg.setConditionValue("effThruDt01", pMsg.slsDt.getValue());
        DS_MDL_SPLY_RELNTMsgArray tMsgArray = (DS_MDL_SPLY_RELNTMsgArray) S21ApiTBLAccessor.findByCondition(tMsg);
        if (tMsgArray.getValidCount() == 0) {
// QC#10386 mod Start
//            return null;
            return tMsgArray;
// QC#10386 mod End
        } else if (!EZDTBLAccessor.RTNCD_NORMAL.equals(tMsgArray.no(0).getReturnCode())) {
            return null;
        }

        return tMsgArray;
    }

    /**
     * Get Contract Type for Order Entory
     * @param pMsg NWZC181001PMsg
     * @return Contract Type
     */
    public String getContractTypeForOdrEnt(NWZC181001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("ordLineCtxTpCd", STR_AUTO_SUPPLY);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getContractTypeForOrdEntry", ssmParam);

        if (ssmResult == null) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * Get Contract Type for Order Import
     * @param pMsg NWZC181001PMsg
     * @return Contract Type
     */
    public String getContractTypeForOdrImp(NWZC181001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("ordLineCtxTpCd", STR_AUTO_SUPPLY);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getContractTypeForOrdImp", ssmParam);

        if (ssmResult == null) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * Get Line Category for Contract Supply
     * @param pMsg NWZC181001PMsg
     * @param contrTpCd Contract Type Code
     * @return Line Category
     */
    public String getLineCatg(NWZC181001PMsg pMsg, String contrTpCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("ordLineCtxTpCd", STR_AUTO_SUPPLY);
        ssmParam.put("thirdBizCtxAttrbTxt", contrTpCd);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLineCatg", ssmParam);

        if (ssmResult == null) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }

    /**
     * Get Line Category for Initial Supply
     * @param pMsg NWZC181001PMsg
     * @return Line Category
     */
    public String getLineCatgForInitSply(NWZC181001PMsg pMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("pMsg", pMsg);
        ssmParam.put("ordLineCtxTpCd", STR_INITIAL_SUPPLY);
        S21SsmEZDResult ssmResult = getSsmEZDClient().queryObject("getLineCatgForInitSply", ssmParam);

        if (ssmResult == null) {
            return null;
        }

        return (String) ssmResult.getResultObject();
    }
    // QC#23192 2018/02/09 Add Start
    /**
     * getChildMdseList
     * @param queryParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getChildMdseList(Map<String, Object> queryParam) {
        int size = new ORD_TAKE_MDSETMsg().getAttr("ordTakeMdseCd").getDigit();
        queryParam.put("ordTakeLen", new BigDecimal(size));
        return getSsmEZDClient().queryObjectList("getChildMdseList", queryParam);
    }
    // QC#23192 2018/02/09 Add End
}

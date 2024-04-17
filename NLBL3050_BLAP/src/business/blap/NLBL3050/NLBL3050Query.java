/*
 *  <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NLBL3050;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NLBL3050.constant.NLBL3050Constant;

import com.canon.cusa.s21.common.NLB.NLBC001001.SCE_ORD_TP;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HLD_LVL;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.INBD_OTBD;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_LINE_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Distribution Coordinator Work Bench
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/19/2010   Fujitsu         W.Honda         Create          N/A
 * 01/21/2016   CSAI            Y.Imazu         Update          QC#2048
 * 01/27/2017   CITS            R.Shimamoto     Update          QC#16146
 * 01/27/2017   CITS            T.Kikuhara      Update          QC#15650
 *</pre>
 */
public final class NLBL3050Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLBL3050Query myInstance = new NLBL3050Query();

    /**
     * Constructor.
     */
    private NLBL3050Query() {

        super();
    }

    /**
     * Singleton instance getter.
     * @return  NLBL3050Query
     */
    public static NLBL3050Query getInstance() {

        return myInstance;
    }

    /**
     * getBackOrder
     * @param cMsg NLBL3050CMsg
     * @param srchRtlWhList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBackOrder(NLBL3050CMsg cMsg, List<String> srchRtlWhList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("cpoNum", cMsg.cpoNum_H.getValue());
        ssmParam.put("srchRtlWhList", srchRtlWhList);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);
        ssmParam.put("shpgStsValidated", SHPG_STS.VALIDATED);
        // 2017/01/27 QC#16146 Add.
        ssmParam.put("shpgStsHardAllocated", SHPG_STS.HARD_ALLOCATED);
        ssmParam.put("rtrnLineBooked", RTRN_LINE_STS.BOOKED);
        ssmParam.put("asgTaskFlg", cMsg.asgTaskFlg.getValue());
        ssmParam.put("userId", cMsg.usrId.getValue());
        ssmParam.put("dsCondConstGrpId", NLBL3050Constant.NLBL3080_NOT_ALLC_WH);
        // QC#15650 add start
        ssmParam.put("relFlg", ZYPConstant.FLG_OFF_N);
        ssmParam.put("relPntHardAllocFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("hldLvlCd", HLD_LVL.CPO_DETAIL_LEVEL);
        ssmParam.put("whTpCd", WH_TP.COMMON);
        // QC#15650 add end

        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.asgTaskFlg.getValue())) {

            ssmParam.put("psnCd", cMsg.psnCd_H.getValue());
        }

        return getSsmEZDClient().queryObjectList("getBackOrder", ssmParam);
    }

    /**
     * getOrder
     * @param cMsg NLBL3050CMsg
     * @param srchRtlWhList List<String>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrder(NLBL3050CMsg cMsg, List<String> srchRtlWhList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", cMsg.glblCmpyCd.getValue());
        ssmParam.put("slsDt", cMsg.slsDt.getValue());
        ssmParam.put("cpoNum", cMsg.cpoNum_H.getValue());
        ssmParam.put("srchRtlWhList", srchRtlWhList);
        ssmParam.put("asgTaskFlg", cMsg.asgTaskFlg.getValue());
        ssmParam.put("userId", cMsg.usrId.getValue());
        ssmParam.put("inbdOtbdCd", INBD_OTBD.OUTBOUND);
        ssmParam.put("sceOrdTpRT", SCE_ORD_TP.RETURN);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        ssmParam.put("flgN", ZYPConstant.FLG_OFF_N);

        if (!ZYPConstant.FLG_ON_Y.equals(cMsg.asgTaskFlg.getValue())) {

            ssmParam.put("psnCd", cMsg.psnCd_H.getValue());
        }

        return getSsmEZDClient().queryObjectList("getOrder", ssmParam);
    }

    /**
     * getRtlWhList
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getRtlWhList(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObjectList("getRtlWhList", queryParams);
    }

    /**
     * getPsnNm
     * @param ssmParam Map<String, Object>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPsnNm(Map<String, Object> queryParams) {
        return getSsmEZDClient().queryObject("getPsnNm", queryParams);
    }
}

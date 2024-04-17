/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSBL0060;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NSBL0060.constant.NSBL0060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_TEST_COPY_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MTR_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_TASK_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/20/2013   Hitachi         T.Aoyagi        Create          N/A
 * 08/05/2013   Hitachi         Y.Igarashi      Update          QC1339
 * 06/22/2017   Hitachi         T.Mizuki        Update          QC#18613
 * 07/30/2018   CITS            T.Wada          Update          QC#18613-2
 *</pre>
 */
public final class NSBL0060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NSBL0060Query INSTANCE = new NSBL0060Query();

    /**
     * Constructor.
     */
    private NSBL0060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NSBL0060Query
     */
    public static NSBL0060Query getInstance() {
        return INSTANCE;
    }

    /**
     * Get machine information.
     * @param cMsg NSBL0060CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMachineInfo(NSBL0060CMsg cMsg) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cMsg", cMsg);
        paramMap.put("orgStruTp", ORG_STRU_TP.TERRITORY_STRUCTURE);
        // mod start 2017/06/22 CSA QC#18613
        paramMap.put("svcTaskNum_HD", cMsg.svcTaskNum_HD.getValue());
        // mod end 2017/06/22 CSA QC#18613
        return getSsmEZDClient().queryEZDMsg("getMachineInfo", paramMap, cMsg);
    }

    // mod start 2017/06/28 CSA QC#18613
    /**
     * Get Average meter count.
     * @param cMsg NSBL0060CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getAvgMtrCnt(NSBL0060CMsg cMsg) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cMsg", cMsg);

        return getSsmEZDClient().queryObject("getAvgMtrCnt", paramMap);

    }
    // mod end 2017/06/28 CSA QC#18613

    /**
     * Get response count and average.
     * @param cMsg NSBL0060CMsg
     * @return Map<String, BigDecimal>
     */
    @SuppressWarnings("unchecked")
    public Map<String, BigDecimal> getResCntAndAvg(NSBL0060CMsg cMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        // mod start 2017/06/28 CSA QC#18613
        List<String> svcTaskStsCdList = new ArrayList<String>();
        svcTaskStsCdList.add(SVC_TASK_STS.COMPLETED);
        svcTaskStsCdList.add( SVC_TASK_STS.CLOSED);
        ssmParam.put("svcTaskStsCdList",svcTaskStsCdList);
        // mod end 2017/06/28 CSA QC#18613

        return (Map<String, BigDecimal>) getSsmEZDClient().queryObject("getResCntAndAvg", ssmParam).getResultObject();
    }

    /**
     * Get service task information.
     * @param cMsg NSBL0060CMsg
     * @param sMsg NSBL0060SMsg
     * @param fromDt String
     * @param toDt String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getServiceTaskInfo(NSBL0060CMsg cMsg, NSBL0060SMsg sMsg, String fromDt, String toDt) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("cMsg", cMsg);
        ssmParam.put("fromDt", fromDt);
        ssmParam.put("toDt", toDt);
        ssmParam.put("mtrClsTpCd", MTR_CLS_TP.TOTAL_METER);
        // QC1339 Add start
        ssmParam.put("rgtnStsCd", RGTN_STS.READY_FOR_ORDER_TAKING);
        ssmParam.put("asterisk", NSBL0060Constant.ASTERISK);
        // QC1339 Add end
        ssmParam.put("rowNum", String.valueOf(sMsg.A.length() + 2));

        // QC18613-2 Add start
        ssmParam.put("testCopyOut", DS_TEST_COPY_CLS.TEST_COPY_OUT);
        ssmParam.put("flgY", ZYPConstant.FLG_ON_Y);
        // QC18613-2 Add end

        return getSsmEZDClient().queryEZDMsgArray("getServiceTaskInfo", ssmParam, sMsg.A);
    }
}

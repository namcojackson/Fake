/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWWL0040;

import static business.blap.NWWL0040.constant.NWWL0040Constant.MAX_DT;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWWL0040Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/27   Fujitsu         M.Ohno          Create          N/A
 *</pre>
 */
public final class NWWL0040Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWWL0040Query MY_INSTANCE = new NWWL0040Query();

    /**
     * Private constructor
     */
    private NWWL0040Query() {
        super();
    }

    /**
     * Get the NWWL0040Query instance.
     * @return NWWL0040Query instance
     */
    public static NWWL0040Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getNtfyHdr
     * @param bizMsg NWWL0040CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getNtfyHdr(NWWL0040CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("ntfyHdrNm", bizMsg.ntfyHdrNm.getValue());
        params.put("ntfyHdrDescTxt", bizMsg.ntfyHdrDescTxt.getValue());
        params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd_N.getValue());
        params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd_N.getValue());
        params.put("effFromDt", bizMsg.effFromDt_N.getValue());
        params.put("effThruDt", bizMsg.effThruDt_N.getValue());
        params.put("ntfyHdActvFlg", bizMsg.ntfyHdrActvFlg.getValue());
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        params.put("maxDt", MAX_DT);

        return getSsmEZDClient().queryObjectList("searchNtfyHdr", params);
    }

    /**
     * getDistList
     * @param bizMsg NWWL0040CMsg
     * @param glblMsg NWWL0040SMsg
     * @param hdrPkList List<BigDecimal>
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getDistList(NWWL0040CMsg bizMsg, NWWL0040SMsg glblMsg, List<BigDecimal> hdrPkList) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        if (hdrPkList != null && hdrPkList.size() > 0) {
            BigDecimal[] hdrPk = (BigDecimal[]) hdrPkList.toArray(new BigDecimal[0]);
            params.put("hdrPk", hdrPk);
        } else {
            params.put("hdrPk", null);
        }

        params.put("ntfyDistListNm", bizMsg.ntfyDistListNm.getValue());
        params.put("ntfyDistListDescTxt", bizMsg.ntfyDistListDescTxt.getValue());
        params.put("ntfyBizAreaTpCd", bizMsg.ntfyBizAreaTpCd_D.getValue());
        params.put("ntfySubAreaTpCd", bizMsg.ntfySubAreaTpCd_D.getValue());
        params.put("effFromDt", bizMsg.effFromDt_D.getValue());
        params.put("effThruDt", bizMsg.effThruDt_D.getValue());
        params.put("ntfyDistListActvFlg", bizMsg.ntfyDistListActvFlg.getValue());
        params.put("rowNum", glblMsg.A.length() + 1);
        params.put("maxDt", MAX_DT);
        params.put("slsDt", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));

        return getSsmEZDClient().queryObjectList("searchDistList", params);
    }
}

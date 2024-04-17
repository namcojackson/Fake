/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1340;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RATE_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NSAL1340Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/05/09   Hitachi         Y.Osawa         Create          N/A
 *</pre>
 */
public final class NSAL1340Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1340Query MY_INSTANCE = new NSAL1340Query();

    /**
     * Private constructor
     */
    private NSAL1340Query() {
        super();
    }

    /**
     * Get the NSAL1340Query instance.
     * @return NSAL1340Query instance
     */
    public static NSAL1340Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NSAL1340CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getBandList(NSAL1340CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcCatgCd", bizMsg.prcCatgCd.getValue());
        params.put("mdlId", bizMsg.mdlId.getValue());
        params.put("mdlNm", bizMsg.mdlNm.getValue());
        params.put("prcMtrPkgPk", bizMsg.prcMtrPkgPk.getValue());
        params.put("prcSvcPlnTpCd", bizMsg.prcSvcPlnTpCd.getValue());
        params.put("prcSvcContrTpCd", bizMsg.prcSvcContrTpCd.getValue());
        params.put("bllgMtrLbCd", bizMsg.bllgMtrLbCd.getValue());
        params.put("prcBaseDt", bizMsg.prcBaseDt.getValue());
        params.put("flgN", ZYPConstant.FLG_OFF_N);

        params.put("termMthNum" //
                , bizMsg.toPerMthNum_I.getValue().subtract(bizMsg.fromPerMthNum_I.getValue()).add(BigDecimal.ONE));

        params.put("annual", PRC_RATE_TP.ANNUAL);
        params.put("cpc", PRC_RATE_TP.CPC);

        return getSsmEZDClient().queryEZDMsgArray("getBandList", params, bizMsg.A);
    }

}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL2580;

import static business.blap.NMAL2580.constant.NMAL2580Constant.DEFALT_FROM_DATE;
import static business.blap.NMAL2580.constant.NMAL2580Constant.DEFALT_TO_DATE;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2460;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2620;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2710;
import static business.blap.NMAL2580.constant.NMAL2580Constant.FUNCTION_ID_NMAL2720;
import static business.blap.NMAL2580.constant.NMAL2580Constant.TIMESTAMP_FROM;
import static business.blap.NMAL2580.constant.NMAL2580Constant.TIMESTAMP_TO;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NMAL2580Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/08   Fujitsu         R.Nakamura      Create          N/A
 * 2016/07/28   Fujitsu         R.Nakamura      Update          QC#11871
 *</pre>
 */
public final class NMAL2580Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL2580Query MY_INSTANCE = new NMAL2580Query();

    /**
     * Private constructor
     */
    private NMAL2580Query() {
        super();
    }

    /**
     * Get the NMAL2580Query instance.
     * @return NMAL2580Query instance
     */
    public static NMAL2580Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL2580CMsg
     * @param glblMsg NMAL2580SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL2580CMsg bizMsg, NMAL2580SMsg glblMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());

        if (FUNCTION_ID_NMAL2460.equals(bizMsg.ezInAplID.getValue())) {
            params.put("funcIdNMAL2460", ZYPConstant.FLG_ON_Y);
        } else if (FUNCTION_ID_NMAL2620.equals(bizMsg.ezInAplID.getValue())) {
            params.put("funcIdNMAL2620", ZYPConstant.FLG_ON_Y);
        } else if (FUNCTION_ID_NMAL2710.equals(bizMsg.ezInAplID.getValue())) {
            params.put("funcIdNMAL2710", ZYPConstant.FLG_ON_Y);
        } else if (FUNCTION_ID_NMAL2720.equals(bizMsg.ezInAplID.getValue())) {
            params.put("funcIdNMAL2720", ZYPConstant.FLG_ON_Y);
        }

        params.put("rqstId", bizMsg.trtyUpdRqstHdrPk.getValue());
        params.put("userId", bizMsg.rqstUsrId.getValue());
        params.put("userNm", bizMsg.fill103Txt.getValue());
        params.put("rqstStsCd", bizMsg.rqstCratSysTpCd.getValue());
        params.put("rqstTpCd", bizMsg.rqstRsltTpCd.getValue());
        params.put("updtRsn", bizMsg.rqstRsltCmntTxt.getValue());
        StringBuilder tsFrom = new StringBuilder();
        if (!ZYPCommonFunc.hasValue(bizMsg.effFromDt)) {
            tsFrom.append(DEFALT_FROM_DATE);
        } else {
            tsFrom.append(bizMsg.effFromDt.getValue());
        }
        tsFrom.append(TIMESTAMP_FROM);
        params.put("rqstDateFrom", tsFrom.toString());
        StringBuilder tsTo = new StringBuilder();
        if (!ZYPCommonFunc.hasValue(bizMsg.effToDt)) {
            tsTo.append(DEFALT_TO_DATE);
        } else {
            tsTo.append(bizMsg.effToDt.getValue());
        }
        tsTo.append(TIMESTAMP_TO);
        params.put("rqstDateTo", tsTo.toString());
        // Mod Start 2016/07/28 QC#11871
//        params.put("maxRowNum", MAX_ROW_NUM);
        params.put("maxRowNum", glblMsg.A.length() + 1);
        // Mod End 2016/07/28 QC#11871

        return getSsmEZDClient().queryEZDMsgArray("search", params, glblMsg.A);
    }

}

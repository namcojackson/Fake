/*
 * <Pre>Copyright (c) 2019 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NWAL1500;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2019/12/18   Fujitsu         S.Takami        Create          S21_NA#54223
 * </pre>
 */
public final class NWAL1500QueryForDeleteShell extends S21SsmEZDQuerySupport {

    /** Singleton instance. */
    private static final NWAL1500QueryForDeleteShell MY_INSTANCE = new NWAL1500QueryForDeleteShell();

    /**
     * Constructor.
     */
    private NWAL1500QueryForDeleteShell() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1500Query
     */
    public static NWAL1500QueryForDeleteShell getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Freight Term Information List
     * @param bizMsg NWAL1500CMsg
     * @return Freight Term Information List
     */
    public S21SsmEZDResult getDetailRelatedShell(NWAL1500CMsg bizMsg) {

        Map<String, String> params = new HashMap<String, String>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum.getValue());
        params.put("flgOnY", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObjectList("getDetailRelatedShell", params);
    }

    /**
     * <pre>
     * Get Shell Allowed Item Type on Config type = Add To Config
     * @param bizMsg Biz Message
     * @return S21SsmEZDResult (Result is Array of COA_MDSE_TP_CD)
     * </pre>
     */
    public S21SsmEZDResult getSvcAddlMdseTpList(NWAL1500CMsg bizMsg) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("cpoSvcAddlBaseItems", MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS);

        return getSsmEZDClient().queryObjectList("getSvcAddlMdseTpList", params);
    }
    /**
     * <pre>
     * @param bizMsg    NWAL1500CMsg
     * @return  S21SsmEZDResult
     * </pre>
     */
    public BigDecimal getBillByTpCount(NWAL1500CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("dsOrdCatgCd", bizMsg.dsOrdCatgCd.getValue());
        params.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());

        return (BigDecimal) getSsmEZDClient().queryObject("getBillByTpCount", params).getResultObject();
    }
}

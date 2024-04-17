/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NSAL1350;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CONFIG_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_TP_CTX_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORD_HDR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SHPG_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

import static business.blap.NSAL1350.constant.NSAL1350Constant.BILL_ONLY_WH;

/**
 *<pre>
 * NSAL1350Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/26   Hitachi         T.Mizuki        Create          N/A
 * 2019/11/22   Fujitsu         C.Hara          Update          QC#54213
 *</pre>
 */
public final class NSAL1350Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NSAL1350Query MY_INSTANCE = new NSAL1350Query();

    /**
     * Private constructor
     */
    private NSAL1350Query() {
        super();
    }

    /**
     * Get the NSAL1350Query instance.
     * @return NSAL1350Query instance
     */
    public static NSAL1350Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NSAL1350CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NSAL1350CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("cpoOrdNum", bizMsg.cpoOrdNum_I.getValue());
        params.put("dsOrdPosnNum", bizMsg.dsOrdPosnNum.getValue());
        if (ZYPCommonFunc.hasValue(bizMsg.mdlId)) {
            params.put("mdlId", bizMsg.mdlId.getValue().toString());
        } else {
            params.put("mdlId", null);
        }
        params.put("mdseCd", bizMsg.mdseCd.getValue());
        params.put("mdseItemTpCd", bizMsg.mdseItemTpCd.getValue());
        params.put("saved", ORD_HDR_STS.SAVED);
        params.put("validated", ORD_HDR_STS.VALIDATED);

        params.put("shpgStsArrived", SHPG_STS.ARRIVED);

        params.put("limitRownum", bizMsg.A.length() + 1);

        params.put("dclnSvcCdDcln", ZYPConstant.CHKBOX_ON_1);

        params.put("addAsryFlg", bizMsg.addAsryFlg.getValue());
        params.put("dsContrNum", bizMsg.dsContrNum_I);
        List<String> mdseTpCtxTpList = new ArrayList<String>(2);
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.addAsryFlg.getValue())) {
            mdseTpCtxTpList.add(MDSE_TP_CTX_TP.CPO_SERVICE_ADDITIONAL_BASE_ITEMS);
        } else {
            mdseTpCtxTpList.add(MDSE_TP_CTX_TP.CPO_SERVICE_CONFIGURATION_ITEMS);
        }
        params.put("mdseTpCtxTpList", mdseTpCtxTpList.toArray(new String[mdseTpCtxTpList.size()]));
        params.put("configTpAddToConfig", CONFIG_TP.ADD_TO_CONFIG);
        params.put("rtlWhCd", BILL_ONLY_WH); // 2019/11/22 QC#54213 Add

        return getSsmEZDClient().queryObjectList("getSearchRslt", params);
    }
}

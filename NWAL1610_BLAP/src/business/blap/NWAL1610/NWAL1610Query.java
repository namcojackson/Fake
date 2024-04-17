/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1610;

import static business.blap.NWAL1610.constant.NWAL1610Constant.LINE_MODE;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_ORD_LINE_DRCTN;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWAL1610Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/11   Fujitsu         C.Yokoi         Create          N/A
 *</pre>
 */
public final class NWAL1610Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWAL1610Query MY_INSTANCE = new NWAL1610Query();

    /**
     * Private constructor
     */
    private NWAL1610Query() {
        super();
    }

    /**
     * Get the NWAL1610Query instance.
     * @return NWAL1610Query instance
     */
    public static NWAL1610Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * getOrdLineCatg
     * @param bizMsg NWAL1610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdLineCatg(NWAL1610CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        if (LINE_MODE.equals(bizMsg.xxModeCd.getValue())) {
            ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.OUTBOUND);
        } else {
            ssmParam.put("dsOrdLineDrctnCd", DS_ORD_LINE_DRCTN.INBOUND);
        }
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        ssmParam.put("dsOrdTpCd", bizMsg.dsOrdTpCd.getValue());
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        ssmParam.put("ordTimeStamp", bizMsg.cpoOrdTs.getValue());

        return getSsmEZDClient().queryObjectList("getOrdLineCatg", ssmParam);
    }

    /**
     * getOrdLineSrc
     * @param bizMsg NWAL1610CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOrdLineSrc(NWAL1610CMsg bizMsg) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());

        return getSsmEZDClient().queryObjectList("getOrdLineSrc", ssmParam);
    }

    /**
     * getOrdLineSrc
     * @param glblCmpyCd Global Company Code
     * @param prcCatgNm Price Category Code
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPrcCatg(String glblCmpyCd, String prcCatgNm) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("prcCatgNm", prcCatgNm);

        return getSsmEZDClient().queryObjectList("getPrcCatg", ssmParam);
    }
}

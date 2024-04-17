/**
 *<pre>
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 *</pre>
 */
package business.blap.NLCL1060;

import static business.blap.NLCL1060.constant.NLCL1060Constant.BIND_AVAL_LOC_CD_ARRAY;
import static business.blap.NLCL1060.constant.NLCL1060Constant.GLBL_CMPY_CD;
import static business.blap.NLCL1060.constant.NLCL1060Constant.MDSE_CD;
import static business.blap.NLCL1060.constant.NLCL1060Constant.ROW_NUM;
import static business.blap.NLCL1060.constant.NLCL1060Constant.STK_STS_CD;
import static business.blap.NLCL1060.constant.NLCL1060Constant.VND_CD;
import static business.blap.NLCL1060.constant.NLCL1060Constant.CUSA_PRT_VND_CD;
import static business.blap.NLCL1060.constant.NLCL1060Constant.XX_CHK_BOX;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.STK_STS;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * 3rd Party Onhand Inventory Inquiry Popup
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2023/05/15   Hitachi         S.Dong          Create          QC#61398
 *</pre>
 */
public final class NLCL1060Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NLCL1060Query MY_INSTANCE = new NLCL1060Query();

    /**
     * Constructor.
     */
    private NLCL1060Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NABL0020Query
     */
    public static NLCL1060Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * @param bizMsg NLCL1060CMsg
     * @param sMsg NLCL1060SMsg
     * @param glblCmpyCd String
     * @param rownum int
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getOnhandInventoryInquiry(NLCL1060CMsg bizMsg, NLCL1060SMsg sMsg, String glblCmpyCd, int rownum) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();

        String avalLocCd = ZYPCodeDataUtil.getVarCharConstValue("NLZC3000_PARTS_LOC_LIST", glblCmpyCd);
        if (ZYPCommonFunc.hasValue(avalLocCd)) {
            String[] avalLocCdArray = avalLocCd.split(",");
            ssmParam.put(BIND_AVAL_LOC_CD_ARRAY, avalLocCdArray);
        }
        String cusaPrtVndCd = ZYPCodeDataUtil.getVarCharConstValue("CUSA_PRT_VND_CD", glblCmpyCd);
        if (!ZYPCommonFunc.hasValue(cusaPrtVndCd)) {
            cusaPrtVndCd = "0000900335";
        }
        ssmParam.put(XX_CHK_BOX, bizMsg.xxChkBox.getValue());
        ssmParam.put(ROW_NUM, rownum);
        ssmParam.put(GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(MDSE_CD, bizMsg.mdseCd_H.getValue());
        ssmParam.put(CUSA_PRT_VND_CD, cusaPrtVndCd);
        ssmParam.put(VND_CD, bizMsg.vndCd.getValue());
        ssmParam.put(STK_STS_CD, STK_STS.GOOD);

        return getSsmEZDClient().queryEZDMsgArray("getOnhandInventoryInquiry", ssmParam, sMsg.A);
    }
}

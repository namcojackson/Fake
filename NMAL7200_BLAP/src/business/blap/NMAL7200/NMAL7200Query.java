/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NMAL7200;

import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG1;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG2;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG3;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG4;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG_N;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG_NO;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG_Y;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ACTV_FLG_YES;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ATTRBLIST;
import static business.blap.NMAL7200.constant.NMAL7200Constant.GLOBAL_CMPY_CODE;
import static business.blap.NMAL7200.constant.NMAL7200Constant.PRC_GRP_DESC_TXT;
import static business.blap.NMAL7200.constant.NMAL7200Constant.PRC_GRP_NM;
import static business.blap.NMAL7200.constant.NMAL7200Constant.PRC_GRP_PK;
import static business.blap.NMAL7200.constant.NMAL7200Constant.ROWNUM;
import static business.blap.NMAL7200.constant.NMAL7200Constant.SELECT_MAX_ROW_SIZE;
import static business.blap.NMAL7200.constant.NMAL7200Constant.USAGE_TYPE1;
import static business.blap.NMAL7200.constant.NMAL7200Constant.USAGE_TYPE2;
import static business.blap.NMAL7200.constant.NMAL7200Constant.USAGE_TYPE_LIST;
import static business.blap.NMAL7200.constant.NMAL7200Constant.USAGE_TYPE_RULE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_RULE_ATTRB;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;


/**
 *<pre>
 * NMAL7200Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/22   Fujitsu         M.Suzuki        Create          N/A
 * 2017/08/09   Fujitsu         Y.Kanefusa      Update          S21_NA#20249
 *</pre>
 */
public final class NMAL7200Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NMAL7200Query MY_INSTANCE = new NMAL7200Query();

    /**
     * Private constructor
     */
    private NMAL7200Query() {
        super();
    }

    /**
     * Get the NMAL7200Query instance.
     * @return NMAL7200Query instance
     */
    public static NMAL7200Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Search
     * @param bizMsg NMAL7200CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult search(NMAL7200CMsg bizMsg) {
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("glblCmpyCd", getGlobalCompanyCode());
      return getSsmEZDClient().queryEZDMsgArray("getSampleToSMsg", params, bizMsg.A);
    }

    /**
     * Get Condition Group
     * @param bizMsg NMAL7200CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getPriceGroupUsage(NMAL7200CMsg bizMsg) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put(GLOBAL_CMPY_CODE, getGlobalCompanyCode());
        params.put(PRC_GRP_PK, bizMsg.xxScrItem29Txt.getValue());
        params.put(PRC_GRP_NM, bizMsg.prcGrpNm.getValue());
        params.put(PRC_GRP_DESC_TXT, bizMsg.prcGrpDescTxt.getValue());
        ArrayList<String> attrbList = new ArrayList<String>();
        attrbList.add(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SHIP_TO);
        attrbList.add(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP);
        attrbList.add(PRC_RULE_ATTRB.TRANSACTION_GROUP);
        attrbList.add(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_BILL_TO);
        attrbList.add(PRC_RULE_ATTRB.CUSTOMER_PRICE_GROUP_SOLD_TO);
        attrbList.add(PRC_RULE_ATTRB.MATERIAL_PRICE_GROUP_QTYBREAK); // QC#20249 2017/08/09 Add
        params.put(ATTRBLIST, attrbList);
        params.put(USAGE_TYPE1, USAGE_TYPE_LIST);
        params.put(USAGE_TYPE2, USAGE_TYPE_RULE);
        params.put(ACTV_FLG1, ACTV_FLG_Y);
        params.put(ACTV_FLG2, ACTV_FLG_YES);
        params.put(ACTV_FLG3, ACTV_FLG_N);
        params.put(ACTV_FLG4, ACTV_FLG_NO);

        params.put(ROWNUM, SELECT_MAX_ROW_SIZE);

        S21SsmEZDResult ssmResult = getSsmEZDClient().queryEZDMsgArray("getPriceGroupUsage", params, bizMsg.A);
        return ssmResult;
    }

}

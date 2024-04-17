/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWCL0100;

import static business.blap.NWCL0100.constant.NWCL0100Constant.BIZ_ID;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 *<pre>
 * NWCL0100Query
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/23   Fujitsu         S.Ohki          Create          N/A
 * 2016/09/07   Hitachi         Y.Takeno        Update          QC#13337
 * 2016/09/08   Hitachi         Y.Takeno        Update          QC#13341
 * 2017/11/14   Fujitsu         Mz.Takahashi    Update          Sol#265(QC#18788)
 *</pre>
 */
public final class NWCL0100Query extends S21SsmEZDQuerySupport {

    /** Singleton instance */
    private static final NWCL0100Query MY_INSTANCE = new NWCL0100Query();

    /**
     * Private constructor
     */
    private NWCL0100Query() {
        super();
    }

    /**
     * Get the NWCL0100Query instance.
     * @return NWCL0100Query instance
     */
    public static NWCL0100Query getInstance() {
        return MY_INSTANCE;
    }

    /**
     * Get Contract List
     * @param bizMsg NWCL0100CMsg
     * @param glblMsg NWCL0100SMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getContractList(NWCL0100CMsg bizMsg, NWCL0100SMsg glblMsg) {
        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("rowNum", glblMsg.A.length() + 1);
        ssmParam.put("glblCmpyCd", getGlobalCompanyCode());
        ssmParam.put("prcGrpTrgttpCd", PRC_GRP_TRGT_TP.CUSTOMER);
        ssmParam.put("prcGrpOpCd", PRC_GRP_OP.EQ);
        ssmParam.put("prcGrpTrgtAttrbCd", PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER);
        ssmParam.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        ssmParam.put("actvFlg", ZYPConstant.FLG_ON_Y);
        // 2017/11/14 Sol#265(QC#18788) Add Start
        ssmParam.put("prcGrpTrxTpCd", PRC_GRP_TRX_TP.SUPPLIES);
        // 2017/11/14 Sol#265(QC#18788) Add End

        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox_H0.getValue())) {
            ssmParam.put("salesDate", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        }

        List<String> billToCustCdList = new ArrayList<String>();
        String[] billToCdList = bizMsg.xxBillToAcctCdSrchTxt_H0.getValue().split(",", 0);

        for (int i = 0; i < billToCdList.length; i++) {
            if (ZYPCommonFunc.hasValue(billToCdList[i])) {
                billToCustCdList.add(billToCdList[i]);
            }
        }
        if (billToCustCdList.size() > 0) {
            ssmParam.put("billToCustCdList", billToCustCdList);
        }

        return getSsmEZDClient().queryEZDMsgArray("getContractList", ssmParam, glblMsg.A);
    }

    /**
     * getSavedSearchOptionList
     * @param usrId String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSavedSearchOptionList(String usrId) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("srchOptAplId", BIZ_ID);
        params.put("srchOptUsrId", usrId);

        return getSsmEZDClient().queryObjectList("getSavedSearchOptionList", params);
    }

// 2016/09/07 QC#13337 Add Start
    /**
     * getSplyPgmContrByAcctCd
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getSplyPgmContrByAcctCd(String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);

        return getSsmEZDClient().queryObjectList("getSplyPgmContrByAcctCd", params);
    }
 // 2016/09/07 QC#13337 Add End

// 2016/09/08 QC#13341 Add Start
    /**
     * countupBillToCustPk
     * @param billToCustCd String
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countupBillToCustPk(String billToCustCd) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("billToCustCd", billToCustCd);

        return getSsmEZDClient().queryObject("countupBillToCustPk", params);
    }
 // 2016/09/08 QC#13341 Add End

 // 2017/11/14 Sol#265(QC#18788) Add Start
    /**
     * countupPrcGrpPk
     * @param prcGrpPk 
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult countupPrcGrpPk(BigDecimal prcGrpPk) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", getGlobalCompanyCode());
        params.put("prcGrpPk", prcGrpPk);
        params.put("prcGrpTpCd", PRC_GRP_TP.CUSTOMER_PRICING_GROUP);
        params.put("prcGrpTrxTpCd", PRC_GRP_TRX_TP.SUPPLIES);
        params.put("actvFlg", ZYPConstant.FLG_ON_Y);

        return getSsmEZDClient().queryObject("countupPrcGrpPk", params);
    }
    // 2017/11/14 Sol#265(QC#18788) Add End
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1770;

import java.util.HashMap;
import java.util.Map;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/12   Fujitsu         T.Yoshida       Create          N/A
 * 2016/05/16   Fujitsu         T.Murai         Update          S21_NA#7861
 * 2016/10/24   Fujitsu         S.Iidaka        Update          S21_NA#14607
 * </pre>
 */
public final class NWAL1770QueryForSalesCredit extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NWAL1770QueryForSalesCredit MY_INSTANCE = new NWAL1770QueryForSalesCredit();

    /**
     * Constructor.
     */
    private NWAL1770QueryForSalesCredit() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NWAL1770Query
     */
    public static NWAL1770QueryForSalesCredit getInstance() {
        return MY_INSTANCE;
    }

    /**
     * get Sales Rep Info
     * @param bizMsg NWAL1770CMsg
     * @param slsRepTocCd Sales Rep TOC Code
     * @return Sales Rep Info
     */
    public S21SsmEZDResult getSlsRepInfo(NWAL1770CMsg bizMsg, String slsRepTocCd) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsRepTocCd", slsRepTocCd);
        // 2016/10/21 S21_NA#14607 Add Start
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2016/10/21 S21_NA#14607 Add End

        return getSsmEZDClient().queryObject("getSlsRepInfo", params);
    }

    /**
     * get Sales Rep Info List
     * @param bizMsg NWAL1770CMsg
     * @param isCallName Called Name Field
     * @return Sales Rep Info List
     */
    public S21SsmEZDResult getSlsRepInfoList(NWAL1770CMsg bizMsg, boolean isCallName) {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("glblCmpyCd", bizMsg.glblCmpyCd.getValue());
        params.put("slsDt", bizMsg.slsDt.getValue());
        params.put("slsRepFlg", ZYPConstant.FLG_ON_Y);
        params.put("orgStruTpCd", ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY);
        // 2016/10/21 S21_NA#14607 Add Start
        params.put("rgtnStsCd", RGTN_STS.TERMINATED);
        // 2016/10/21 S21_NA#14607 Add End

        if (isCallName) {
            params.put("slsRepTocNm", bizMsg.slsRepTocNm.getValue());
        } else {
            params.put("psnNum", bizMsg.psnNum.getValue()); // S21_NA#7861 Mod
        }

        return getSsmEZDClient().queryObjectList("getSlsRepInfoList", params);
    }
}

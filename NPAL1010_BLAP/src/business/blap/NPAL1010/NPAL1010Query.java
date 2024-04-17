/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1010;

import java.util.HashMap;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;

import business.db.COA_PROJTMsg;
import business.db.RTL_WH_CATGTMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.online.ssm.S21SsmEZDQuerySupport;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2011/08/16   Fujitsu         S.Noguchi       Create          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 2016/02/23   CSAI            D.Fukaya        Update          QC#2378
 *</pre>
 */
public final class NPAL1010Query extends S21SsmEZDQuerySupport {

    /**
     * Singleton instance.
     */
    private static final NPAL1010Query MYINSTANCE = new NPAL1010Query();

    /**
     * Constructor.
     */
    private NPAL1010Query() {
        super();
    }

    /**
     * Singleton instance getter.
     * @return NPAL1010Query
     */
    public static NPAL1010Query getInstance() {
        return MYINSTANCE;
    }

    /**
     * Search Install Location
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchLocation(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryEZDMsgArray("searchLocation", ssmParam, bizMsg.A);
    }
    /**
     * Search Install Location
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchUsgPullDown(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchUsgPullDown", ssmParam);
    }
    /**
     * Search WH Category
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchCatgPullDown(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchCatgPullDown", ssmParam);
    }
    /**
     * Search Inventory Account
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchInvtyAcctPullDown(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchInvtyAcctPullDown", ssmParam);
    }
    /**
     * Search Inventory Ownership
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchInvtyOwnerPullDown(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchInvtyOwnerPullDown", ssmParam);
    }
    /**
     * Search WH Operation
     * @param ssmParam Map<String, Object>
     * @param bizMsg NPAL1010CMsg
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult searchWhOperationPullDown(Map<String, Object> ssmParam, NPAL1010CMsg bizMsg) {
        return getSsmEZDClient().queryObjectList("searchWhOperationPullDown", ssmParam);
    }

    /**
     * <pre>
     * Get Manager Name
     * </pre>
     * @param cMsg
     * @param glblCmpyCd
     */
    public void getLocTp(NPAL1010CMsg cMsg, String glblCmpyCd) {
        RTL_WH_CATGTMsg tMsg = new RTL_WH_CATGTMsg();
        ZYPEZDItemValueSetter.setValue(tMsg.glblCmpyCd, glblCmpyCd);
        ZYPEZDItemValueSetter.setValue(tMsg.rtlWhCatgCd, cMsg.rtlWhCatgCd);
        tMsg = (RTL_WH_CATGTMsg) EZDTBLAccessor.findByKey(tMsg);
        if (tMsg != null) {
            ZYPEZDItemValueSetter.setValue(cMsg.locTpCd_H1, tMsg.locTpCd.getValue());
        } else {
            cMsg.locTpCd_H1.clear();
        }
    }

    /**
     * <pre>
     * Get Manager Name
     * </pre>
     * @param cMsg
     * @param glblCmpyCd
     * @return S21SsmEZDResult
     */
    public S21SsmEZDResult getMgrNm(NPAL1010CMsg cMsg, String glblCmpyCd) {
        Map<String, String> queryParam = new HashMap<String, String>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("whMgrPsnCd", cMsg.whMgrPsnCd_H1.getValue());
        if (LOC_TP.TECHNICIAN.equals(cMsg.locTpCd_H1.getValue())) {
            queryParam.put("techFlg", ZYPConstant.FLG_ON_Y);
        } else {
            queryParam.put("techFlg", ZYPConstant.FLG_OFF_N);
        }
        return getSsmEZDClient().queryObject("getMgrNm", queryParam);
    }
}

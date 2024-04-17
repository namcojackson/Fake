/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL1610.common;

import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.blap.NWAL1610.NWAL1610CMsg;
import business.blap.NWAL1610.NWAL1610Query;
import business.db.BILL_TO_CUSTTMsg;
import business.db.BILL_TO_CUSTTMsgArray;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INVTY_LOC_VTMsgArray;
import business.db.MDSETMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import static business.blap.NWAL1610.constant.NWAL1610Constant.BUSINESS_ID_NWAL1500;

/**
 *<pre>
 * NWAL1610CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         C.Yokoi         Create          N/A
 * 2019/12/20   Fujitsu         S.Kosaka        Update          QC#54999
 *</pre>
 */
public class NWAL1610CommonLogic {

    /**
     * Method name: getOrdLineCatg
     * @param bizMsg NWAL1610CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getOrdLineCatg(NWAL1610CMsg bizMsg) {

        S21SsmEZDResult ssmResultLineCatg = NWAL1610Query.getInstance().getOrdLineCatg(bizMsg);

        if (!ssmResultLineCatg.isCodeNormal()) {
            return false;
        }

        List<Map<String, String>> resultLineCatgList = (List<Map<String, String>>) ssmResultLineCatg.getResultObject();
        int idx = 0;

        for (Map<String, String> map : resultLineCatgList) {
            // has over max limit of Array
            if (idx > bizMsg.dsOrdLineCatgCd_CD.length()) {
                break;
            }
            if (!map.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgCd_CD.no(idx), map.get("DS_ORD_LINE_CATG_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.dsOrdLineCatgNm_DI.no(idx), map.get("DS_ORD_LINE_CATG_DESC_TXT"));
            }
            idx++;
        }

        return true;
    }

    /**
     * Method name: getOrdLineSrc
     * @param bizMsg NWAL1610CMsg
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public static boolean getOrdLineSrc(NWAL1610CMsg bizMsg) {

        S21SsmEZDResult ssmResultLineSrc = NWAL1610Query.getInstance().getOrdLineSrc(bizMsg);

        if (!ssmResultLineSrc.isCodeNormal()) {
            return false;
        }

        List<Map<String, String>> resultLineSrcList = (List<Map<String, String>>) ssmResultLineSrc.getResultObject();
        int idx = 0;

        for (Map<String, String> map : resultLineSrcList) {
            // has over max limit of Array
            if (idx > bizMsg.ordLineSrcCd_CD.length()) {
                break;
            }
            if (!map.isEmpty()) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcCd_CD.no(idx), map.get("ORD_LINE_SRC_CD"));
                ZYPEZDItemValueSetter.setValue(bizMsg.ordLineSrcNm_DI.no(idx), map.get("ORD_LINE_SRC_NM"));
            }
            idx++;
        }

        return true;
    }

    /**
     * Method name: existsShipToCustCd
     * @param shipToCustCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsShipToCustCd(String shipToCustCd, String glblCmpyCd) {

        SHIP_TO_CUSTTMsg shipToCustTMsg = new SHIP_TO_CUSTTMsg();
        shipToCustTMsg.setSQLID("004");
        shipToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        shipToCustTMsg.setConditionValue("shipToCustCd01", shipToCustCd);

        SHIP_TO_CUSTTMsgArray shipToRslt = (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(shipToCustTMsg);
        if (shipToRslt.getValidCount() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Method name: existsShipToCustCd
     * @param billToCustCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsBillToCustCd(String billToCustCd, String glblCmpyCd) {

        BILL_TO_CUSTTMsg billToCustTMsg = new BILL_TO_CUSTTMsg();
        billToCustTMsg.setSQLID("003");
        billToCustTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        billToCustTMsg.setConditionValue("billToCustCd01", billToCustCd);

        BILL_TO_CUSTTMsgArray billToRslt = (BILL_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(billToCustTMsg);
        if (billToRslt.length() == 0) {
            return false;
        }

        return true;
    }

    /**
     * Method name: existsRtlWh
     * @param rtlWhCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsRtlWh(String rtlWhCd, String glblCmpyCd) {

        DS_INVTY_LOC_VTMsg dsInvtyLocTMsg = new DS_INVTY_LOC_VTMsg();
        dsInvtyLocTMsg.setSQLID("003");
        dsInvtyLocTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsInvtyLocTMsg.setConditionValue("rtlWhCd01", rtlWhCd);

        DS_INVTY_LOC_VTMsgArray dsInvtyLocTMsgArry = (DS_INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(dsInvtyLocTMsg);
        if (dsInvtyLocTMsgArry.getValidCount() < 1) {
            return false;
        }

        return true;
    }

    /**
     * Method name: existsRtlSubWh
     * @param rtlWhCd Strings
     * @param rtlSwhCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsRtlSubWh(String rtlWhCd, String rtlSwhCd, String glblCmpyCd) {

        DS_INVTY_LOC_VTMsg dsInvtyLocTMsg = new DS_INVTY_LOC_VTMsg();
        dsInvtyLocTMsg.setSQLID("004");
        dsInvtyLocTMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsInvtyLocTMsg.setConditionValue("rtlWhCd01", rtlWhCd);
        dsInvtyLocTMsg.setConditionValue("rtlSwhCd01", rtlSwhCd);

        DS_INVTY_LOC_VTMsgArray dsInvtyLocTMsgArry = (DS_INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(dsInvtyLocTMsg);
        if (dsInvtyLocTMsgArry.getValidCount() < 1) {
            return false;
        }

        return true;
    }

    /**
     * Method name: existsSbstItem
     * @param ediAckSbstItemCd String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsSbstItem(String ediAckSbstItemCd, String glblCmpyCd) {

        MDSETMsg mdseTMsg = new MDSETMsg();
        ZYPEZDItemValueSetter.setValue(mdseTMsg.mdseCd, ediAckSbstItemCd);
        ZYPEZDItemValueSetter.setValue(mdseTMsg.glblCmpyCd, glblCmpyCd);

        return (MDSETMsg) S21FastTBLAccessor.findByKey(mdseTMsg) != null;
    }

    /**
     * Method name: existsPrcList
     * @param prcCatgNm String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsPrcCatg(String prcCatgNm, String glblCmpyCd) {

        S21SsmEZDResult ssmResultLineSrc = NWAL1610Query.getInstance().getPrcCatg(glblCmpyCd, prcCatgNm);
        if (!ssmResultLineSrc.isCodeNormal()) {
            return false;
        }

        return true;
    }

    /**
     * Method name: existsFlrPrcList
     * @param flPrcListDescTxt String
     * @param glblCmpyCd String
     * @return boolean
     */
    public static boolean existsFlrPrcList(String flPrcListDescTxt, String glblCmpyCd) {

        S21SsmEZDResult ssmResultLineSrc = NWAL1610Query.getInstance().getPrcCatg(glblCmpyCd, flPrcListDescTxt);
        if (!ssmResultLineSrc.isCodeNormal()) {
            return false;
        }

        return true;
    }

    // 2019/12/20 QC#54999 Add Start
    /**
     * Set Authority
     * @param bizMsg NWAL1610CMsg
     * @param userProfileService S21UserProfileService
     */
    public static void setAuthority(NWAL1610CMsg bizMsg, S21UserProfileService userProfileService) {

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID_NWAL1500);

        int funcIdCnt = 0;
        for (String functionId : functionIds) {
            bizMsg.Z.no(funcIdCnt++).xxFuncId.setValue(functionId);
        }
        bizMsg.Z.setValidCount(funcIdCnt);
    }
    // 2019/12/20 QC#54999 Add End
}

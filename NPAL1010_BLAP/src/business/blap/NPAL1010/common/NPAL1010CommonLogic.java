/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */

package business.blap.NPAL1010.common;

import static business.blap.NPAL1010.constant.NPAL1010Constant.BLANK;
import static business.blap.NPAL1010.constant.NPAL1010Constant.MAX_DATE;
import static business.blap.NPAL1010.constant.NPAL1010Constant.NZZM0000E;
import static business.blap.NPAL1010.constant.NPAL1010Constant.NZZM0001W;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_ACTIVE_FLG;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_ADDL_LOC_NM;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_CTY_ADDR;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_DATA_SEC_FLG;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_DATA_SEC_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_EFF_THRU_DT_DEFALUT;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_FIRST_LINE_ADDR;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_FULL_PSN_NM;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_GLBL_CMPY_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_INVTY_ACCT_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_INVTY_LOC_CD_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_INVTY_LOC_NM_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_INVTY_OWNR_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_LOC_ROLE_TP;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_LOC_ROLE_TP_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_LOC_TP_TECHNICIAN;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_ONLY_RTL_WH_FLG;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_POST_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RGTN_STS_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RTL_SWH_CD_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RTL_SWH_NM_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RTL_WH_CATG;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RTL_WH_CD_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_RTL_WH_NM_LIST;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_SALES_DATE;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_ST_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_VIRTUAL_WH_FLG;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_WH_MGR_PSN_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_WH_OWNR_CD;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_WH_SYS_TP_CD_NA;
import static business.blap.NPAL1010.constant.NPAL1010Constant.SQL_KEY_DS_LOC_NM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.blap.NPAL1010.NPAL1010CMsg;
import business.blap.NPAL1010.NPAL1010Query;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 2012/09/13   Fujitsu         Y.Kamide        Update          N/A
 * 2013/05/14   Fujitsu         H.Mizutani      Update          N/A
 * 2015/04/28   CITS            H.Sugawara      Update          N/A
 * 2016/02/23   CSAI            D.Fukaya        Update          QC#2378
 * 2016/08/03   CITS            R.Shimamoto     Update          QC#10213
 *</pre>
 */
public class NPAL1010CommonLogic {

    /**
     * Search Install Location
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @param userProfile List<String>
     */
    public static void searchInstallLcoation(NPAL1010CMsg bizMsg, String glblCmpyCd, List<String> userProfile) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();

        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(SQL_KEY_RGTN_STS_CD, RGTN_STS.READY_FOR_ORDER_TAKING);

        // WDS R-WH025 Inventory Transaction Mizutani Start
        ssmParam.put(SQL_KEY_WH_SYS_TP_CD_NA, WH_SYS_TP.NA);
        // WDS R-WH025 Inventory Transaction Mizutani End

        // WDS R-WH025 Inventory Transaction Nakazawa Start
//        ssmParam.put(MSG_KEY_DS_WH_SRC_TP_CD_WAREHOUSE, SQL_VALUE_DS_WH_SRC_TP_CD_WAREHOUSE);
//        ssmParam.put(MSG_KEY_DS_WH_SRC_TP_CD_TECHNICIAN, SQL_VALUE_DS_WH_SRC_TP_CD_TECHNICIAN);
//        ssmParam.put(MSG_KEY_DS_WH_SRC_TP_CD_VENDOR_WAREHOUSE, SQL_VALUE_DS_WH_SRC_TP_CD_VENDOR_WAREHOUSE);
//        ssmParam.put(MSG_KEY_DS_WH_SRC_TP_CD_VENDOR_PARTS, SQL_VALUE_DS_WH_SRC_TP_CD_VENDOR_PARTS);
        // WDS R-WH025 Inventory Transaction Nakazawa End

        ssmParam.put(SQL_KEY_INVTY_LOC_CD_LIST, splitStringVal(bizMsg.xxRtrnInvtyLocSrchTxt.getValue()));

        // WDS R-WH025 Inventory Transaction Mizutani Start
        ssmParam.put(SQL_KEY_LOC_ROLE_TP, bizMsg.locRoleTpCd.getValue());

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkInpValFlg)) {

            ssmParam.put(SQL_KEY_DATA_SEC_FLG, bizMsg.xxChkInpValFlg.getValue());

        } else {

            ssmParam.put(SQL_KEY_DATA_SEC_FLG, ZYPConstant.FLG_OFF_N);
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkInpValFlg) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkInpValFlg.getValue())) {

            ssmParam.put(SQL_KEY_DATA_SEC_LIST, userProfile);

        } else {

            ssmParam.put(SQL_KEY_DATA_SEC_LIST, new ArrayList<String>());
        }

        if (!ZYPCommonFunc.hasValue(bizMsg.locRoleTpCd)) {

            List<String> locRoleTpList = new ArrayList<String>();

            for (int i = 0; i <= bizMsg.locRoleTpCd_A2.length() - 1; i++) {

                if (BLANK.equals(bizMsg.locRoleTpCd_A2.no(i).getValue())) {

                    break;
                }

                locRoleTpList.add(bizMsg.locRoleTpCd_A2.no(i).getValue());
            }

            ssmParam.put(SQL_KEY_LOC_ROLE_TP_LIST, locRoleTpList);

        } else {

            ssmParam.put(SQL_KEY_LOC_ROLE_TP_LIST, new ArrayList<String>());
        }

        if (ZYPCommonFunc.hasValue(bizMsg.xxSelFlg)) {

            ssmParam.put(SQL_KEY_VIRTUAL_WH_FLG, bizMsg.xxSelFlg.getValue());

        } else {

            ssmParam.put(SQL_KEY_VIRTUAL_WH_FLG, ZYPConstant.FLG_ON_Y);
        }
        // WDS R-WH025 Inventory Transaction Mizutani End

        // START 2015/04/28 H.Sugawara E850 Warehouse Setup
        ssmParam.put(SQL_KEY_RTL_WH_CATG, bizMsg.rtlWhCatgCd.getValue());
        // END 2015/04/28 H.Sugawara E850 Warehouse Setup

        ssmParam.put(SQL_KEY_INVTY_LOC_NM_LIST, splitStringVal(bizMsg.xxRtrnInvtyLocSrchTxt.getValue()));
        ssmParam.put(SQL_KEY_ADDL_LOC_NM, bizMsg.addlLocNm.getValue());
        ssmParam.put(SQL_KEY_FIRST_LINE_ADDR, bizMsg.firstLineAddr.getValue());
        ssmParam.put(SQL_KEY_POST_CD, bizMsg.postCd.getValue());
        ssmParam.put(SQL_KEY_CTY_ADDR, bizMsg.ctyAddr.getValue());
        ssmParam.put(SQL_KEY_ST_CD, bizMsg.stCd.getValue());

        // 10/09/2015 add start
        ssmParam.put(SQL_KEY_RTL_WH_CD_LIST, splitStringVal(bizMsg.xxRtrnInvtyLocSrchTxt_RW.getValue()));
        ssmParam.put(SQL_KEY_RTL_WH_NM_LIST, splitStringVal(bizMsg.rtlWhNmSrchTxt_RW.getValue()));
        ssmParam.put(SQL_KEY_RTL_SWH_CD_LIST, splitStringVal(bizMsg.xxRtrnInvtyLocSrchTxt_SW.getValue()));
        ssmParam.put(SQL_KEY_RTL_SWH_NM_LIST, splitStringVal(bizMsg.rtlWhNmSrchTxt_SW.getValue()));

        if (ZYPCommonFunc.hasValue(bizMsg.xxChkBox) && ZYPConstant.FLG_ON_Y.equals(bizMsg.xxChkBox.getValue())) {

            ssmParam.put(SQL_KEY_ONLY_RTL_WH_FLG, bizMsg.xxChkBox.getValue());

        } else {

            ssmParam.put(SQL_KEY_ONLY_RTL_WH_FLG, ZYPConstant.FLG_OFF_N);
        }
        // 10/09/2015 add end

        // 10/28/2015 add start
        ssmParam.put(SQL_KEY_INVTY_ACCT_CD, bizMsg.invtyAcctCd.getValue());
        // 10/28/2015 add end

        ssmParam.put(SQL_KEY_INVTY_OWNR_CD, bizMsg.invtyOwnrCd.getValue());
        ssmParam.put(SQL_KEY_WH_OWNR_CD, bizMsg.whOwnrCd.getValue());
        ssmParam.put(SQL_KEY_WH_MGR_PSN_CD, bizMsg.whMgrPsnCd_H1.getValue());
        ssmParam.put(SQL_KEY_FULL_PSN_NM, bizMsg.fullPsnNm_H1.getValue());
        ssmParam.put(SQL_KEY_LOC_TP_TECHNICIAN, LOC_TP.TECHNICIAN);

        // 08/03/2016 add QC#10213
        if (ZYPConstant.FLG_ON_Y.equals(bizMsg.actvFlg.getValue())) {
            ssmParam.put(SQL_KEY_ACTIVE_FLG, ZYPConstant.FLG_ON_Y);
            ssmParam.put(SQL_KEY_SALES_DATE, ZYPDateUtil.getSalesDate(glblCmpyCd));
            ssmParam.put(SQL_KEY_EFF_THRU_DT_DEFALUT, MAX_DATE);
        } else {
            ssmParam.put(SQL_KEY_ACTIVE_FLG, ZYPConstant.FLG_OFF_N);
        }

        // 2017/08/14 QC#20555 ADD BEGIN
        ssmParam.put(SQL_KEY_DS_LOC_NM, bizMsg.rtlWhNmSrchTxt.getValue());
        // 2017/08/14 QC#20555 ADD END

        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchLocation(ssmParam, bizMsg);

        if (ssmResult.isCodeNormal()) {

            // Set up Over Max retrieve data
            int queryResCnt = ssmResult.getQueryResultCount();

            if (queryResCnt > bizMsg.A.length()) {

                bizMsg.A.setValidCount(bizMsg.A.length());
                bizMsg.setMessageInfo(NZZM0001W);
            }

        } else {

            bizMsg.setMessageInfo(NZZM0000E);
        }

        ZYPEZDItemValueSetter.setValue(bizMsg.xxChkBox_RS, bizMsg.xxChkBox.getValue());
    }

    /**
     * Split String Value
     * @param val String
     * @return ArrayList<String>
     */
    private static ArrayList<String> splitStringVal(String val) {

        if (ZYPCommonFunc.hasValue(val)) {

            ArrayList<String> splitValList = new ArrayList<String>();

            List<String> valList = Arrays.asList(val.split(","));

            for (int i = 0; i <= valList.size() - 1; i++) {

                if (ZYPCommonFunc.hasValue(valList.get(i).trim())) {

                    splitValList.add(valList.get(i).trim());
                }
            }

            if (splitValList != null && !splitValList.isEmpty()) {

                splitValList.add(val);

                return splitValList;
            }
        }

        return null;
    }

    /**
     * Search Install Location
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @param locRoleTpCdList List<String>
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchUsgPullDown(NPAL1010CMsg bizMsg, String glblCmpyCd, List<String> locRoleTpCdList) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        ssmParam.put(SQL_KEY_LOC_ROLE_TP_LIST, locRoleTpCdList);
        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchUsgPullDown(ssmParam, bizMsg);
        return ssmResult;
    }

    /**
     * Search WH Category
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchCatgPullDown(NPAL1010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchCatgPullDown(ssmParam, bizMsg);
        return ssmResult;
    }

    // 10/28/2015 add start
    /**
     * Search Inventory Account
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchInvtyAcctPullDown(NPAL1010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchInvtyAcctPullDown(ssmParam, bizMsg);
        return ssmResult;
    }
    // 10/28/2015 add end}

    /**
     * Search Inventory Ownership
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchInvtyOwnerPullDown(NPAL1010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchInvtyOwnerPullDown(ssmParam, bizMsg);
        return ssmResult;
    }

    /**
     * Search WH Operation
     * @param bizMsg NPAL1010CMsg
     * @param glblCmpyCd String
     * @return S21SsmEZDResult
     */
    public static S21SsmEZDResult searchWhOperationPullDown(NPAL1010CMsg bizMsg, String glblCmpyCd) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put(SQL_KEY_GLBL_CMPY_CD, glblCmpyCd);
        S21SsmEZDResult ssmResult = NPAL1010Query.getInstance().searchWhOperationPullDown(ssmParam, bizMsg);
        return ssmResult;
    }
    /**
     * <pre>
     * Get WH Manager Name
     * </pre>
     * @param glblCmpyCd
     * @param locNum
     * @return String Manager Name
     */
    public static String getMgrNm(NPAL1010CMsg bizMsg, String glblCmpyCd) {

        S21SsmEZDResult ssmResult =  NPAL1010Query.getInstance().getMgrNm(bizMsg, glblCmpyCd);
        if (ssmResult.isCodeNotFound()) {
            return null;
        }
        return (String)ssmResult.getResultObject();
    }
}
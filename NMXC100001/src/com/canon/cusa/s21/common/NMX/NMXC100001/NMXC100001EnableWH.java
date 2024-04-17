package com.canon.cusa.s21.common.NMX.NMXC100001;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.dbcommon.EZDTBLAccessor;
import business.db.DS_INVTY_LOC_VTMsg;
import business.db.DS_INVTY_LOC_VTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmBatchClient;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/07/2013   Fujitsu         H.Mizutani      Create          N/A
 * 05/07/2013   Fujitsu         H.Mizutani      Create          N/A
 * </pre>
 */
public class NMXC100001EnableWH extends S21ApiCommonBase {

    /**
     * The specified Location cannot be used.
     */
    private static final String NMXM0009E = "NMXM0009E";

    /**
     * The specified Location doesn’t  exist.
     */
    private static final String NMXM0010E = "NMXM0010E";

    /**
     * Global Company Code is not set
     */
    private static final String NMXM0006E = "NMXM0006E";

    /**
     * Inventry Location Code is not set
     */
    private static final String NMXM0007E = "NMXM0007E";

    /**
     * Business Application ID is not set
     */
    private static final String NMXM0008E = "NMXM0008E";

    /**
     * It is a computational method that shows multiplication.
     */
    private static final String MULTIPLICATION = "*";

    /**
     * COMMA.
     */
    private static final String COMMA = ",";

    /**
     * Being able to use Location usage without limit
     */
    private static final String NO_LIMIT = "";

    /**
     * Blank
     */
    private static final String BLANK = "";

    /**
     * Being able to use Location usage without limit
     */
    private static final List<String> NO_LIMIT_LIST = new ArrayList<String>();

    /**
     * ssm Batch Client Accessor
     */
    private static S21SsmBatchClient ssmBatchClient = S21SsmBatchClient.getClient(NMXC100001EnableWH.class);

    /**
     * <pre>
     * This API convert Inventry Location code into Inventry Location Name
     * if acquired code is not available of exist, return Error code.
     * </pre>
     * @param glblCmpyCd String
     * @param invtyLocCd invtyLocCd
     * @param bizAppId String
     * @param dataSecurityList List<S21DataSecurityAttributeData>
     * @param virtualWHFlg String
     * @param bizAppKeyIdArr String...
     * @return chkEnableWHData NMXC100001EnableWHData
     */
    public static NMXC100001EnableWHData checkEnableWH(String glblCmpyCd, String invtyLocCd, String bizAppId, List<S21DataSecurityAttributeData> dataSecurityList, String virtualWHFlg, String... bizAppKeyIdArr) {

        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return doExit(NMXM0006E);
        }

        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            return doExit(NMXM0007E);
        }

        if (!ZYPCommonFunc.hasValue(bizAppId)) {
            return doExit(NMXM0008E);
        }

        // 10/06/2015 mod start
        // NMXC100001EnableWHData chkEnableWHData = getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, dataSecurityList, virtualWHFlg);
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        NMXC100001EnableWHData chkEnableWHData = getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, dataSecurityList, virtualWHFlg, bizAppKeyId);
        // 10/06/2015 mod end
        if (null != chkEnableWHData) {
            return chkEnableWHData;

        } else {
            if (chkInvtyExist(glblCmpyCd, invtyLocCd)) {
                return doExit(NMXM0009E);
            } else {
                return doExit(NMXM0010E);
            }
        }

    }

    /**
     * <pre>
     * This API convert Inventry Location code into Inventry Location Name
     * if acquired code is not available of exist, return Error code.
     * </pre>
     * @param glblCmpyCd String
     * @param invtyLocCd invtyLocCd
     * @param bizAppId String
     * @param locationTypeList List<String>
     * @param dataSecurityList List<S21DataSecurityAttributeData>
     * @param virtualWHFlg String
     * @param bizAppKeyIdArr String...
     * @return chkEnableWHData NMXC100001EnableWHData
     */
    public static NMXC100001EnableWHData checkEnableWH(String glblCmpyCd, String invtyLocCd, String bizAppId, List<String> locationTypeList, List<S21DataSecurityAttributeData> dataSecurityList, String virtualWHFlg, String... bizAppKeyIdArr) {

        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return doExit(NMXM0006E);
        }

        if (!ZYPCommonFunc.hasValue(invtyLocCd)) {
            return doExit(NMXM0007E);
        }

        if (!ZYPCommonFunc.hasValue(bizAppId)) {
            return doExit(NMXM0008E);
        }

        // 10/06/2015 mod start
        // NMXC100001EnableWHData chkEnableWHData = getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, locationTypeList, dataSecurityList, virtualWHFlg);
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        NMXC100001EnableWHData chkEnableWHData = getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, locationTypeList, dataSecurityList, virtualWHFlg, bizAppKeyId);
        // 10/06/2015 mod end
        if (null != chkEnableWHData) {
            return chkEnableWHData;

        } else {
            if (chkInvtyExist(glblCmpyCd, invtyLocCd)) {
                return doExit(NMXM0009E);
            } else {
                return doExit(NMXM0010E);
            }
        }

    }

    /**
     * <pre>
     * This API search available Location Role Type and return one of outcome.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param funcId Function ID
     * @param bizAppKeyIdArr String...
     * @return Location Role Type
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd, String funcId, String... bizAppKeyIdArr) {
        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }

        // Function ID Check
        if (!ZYPCommonFunc.hasValue(funcId)) {
            return null;
        }

        // 10/06/2015 mod start
        /*
        AVAL_INVTY_LOC_ROLETMsg avalInvtyLocRoleTmsg = new AVAL_INVTY_LOC_ROLETMsg();
        avalInvtyLocRoleTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        avalInvtyLocRoleTmsg.setConditionValue("bizAppId01", funcId);
        avalInvtyLocRoleTmsg.setSQLID("001");
        AVAL_INVTY_LOC_ROLETMsgArray avalInvtyLocRoleTmsgArray = (AVAL_INVTY_LOC_ROLETMsgArray) EZDTBLAccessor.findByCondition(avalInvtyLocRoleTmsg);
        int queryResCnt = avalInvtyLocRoleTmsgArray.getValidCount();
        if (queryResCnt == 0) {
            return NO_LIMIT;
        } else {
            StringBuilder locRoleTpList = new StringBuilder();
            for (int i = 0; i < queryResCnt; i++) {
                locRoleTpList.append(avalInvtyLocRoleTmsgArray.no(i).locRoleTpCd.getValue());
                if (i != queryResCnt - 1) {
                    locRoleTpList.append(COMMA);
                }
            }
            return locRoleTpList.toString();
        }
        */
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        List<Map<String, Object>> locRoleTpList =  getLocRoleTpList(glblCmpyCd, funcId, bizAppKeyId);

        if (null == locRoleTpList || 0 == locRoleTpList.size()) {
            return NO_LIMIT;
        } else {
            StringBuilder sbLocRoleTpList = new StringBuilder();
            for (int i = 0; i < locRoleTpList.size(); i++) {
                Map<String, Object> locRoleTp = (Map<String, Object>) locRoleTpList.get(i);
                sbLocRoleTpList.append((String) locRoleTp.get("LOC_ROLE_TP_CD"));
                if (i != locRoleTpList.size() - 1) {
                    sbLocRoleTpList.append(COMMA);
                }
            }
            return sbLocRoleTpList.toString();
        }
        // 10/06/2015 mod end
    }

    /**
     * <pre>
     * This API search available Location Role Type and return one of outcome.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param funcId Function ID
     * @param locationTypeList List<String>
     * @param bizAppKeyIdArr String...
     * @return Location Role Type
     */
    public static String getLocRoleTpForPopup(String glblCmpyCd, String funcId, List<String> locationTypeList, String... bizAppKeyIdArr) {
        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }

        // Function ID Check
        if (!ZYPCommonFunc.hasValue(funcId)) {
            return null;
        }

        // 10/06/2015 mod start
        // List<Map<String, Object>> locRoleTpList = getLocRoleTpList(glblCmpyCd, funcId, locationTypeList);
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        List<Map<String, Object>> locRoleTpList = getLocRoleTpList(glblCmpyCd, funcId, locationTypeList, bizAppKeyId);
        // 10/06/2015 mod end
        if (null == locRoleTpList || 0 == locRoleTpList.size()) {
            return NO_LIMIT;
        } else {
            StringBuilder locRoleTpListComma = new StringBuilder();
            for (Map<String, Object> locRoleTp : locRoleTpList) {
                locRoleTpListComma.append((String) locRoleTp.get("LOC_ROLE_TP_CD"));
                locRoleTpListComma.append(COMMA);
            }
            locRoleTpListComma.deleteCharAt(locRoleTpListComma.lastIndexOf(COMMA));
            return locRoleTpListComma.toString();
        }
    }

    /**
     * <pre>
     * This API search available Location Role Type and return one of outcome.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param funcId Function ID
     * @param bizAppKeyIdArr String...
     * @return Locatin Role Type
     */
    public static List<String> getLocRoleTp(String glblCmpyCd, String funcId, String... bizAppKeyIdArr) {
        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }
        // Function ID Check
        if (!ZYPCommonFunc.hasValue(funcId)) {
            return null;
        }
        // 10/06/2015 mod start
        /*
        AVAL_INVTY_LOC_ROLETMsg avalInvtyLocRoleTmsg = new AVAL_INVTY_LOC_ROLETMsg();
        avalInvtyLocRoleTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        avalInvtyLocRoleTmsg.setConditionValue("bizAppId01", funcId);
        avalInvtyLocRoleTmsg.setSQLID("001");
        AVAL_INVTY_LOC_ROLETMsgArray avalInvtyLocRoleTmsgArray = (AVAL_INVTY_LOC_ROLETMsgArray) EZDTBLAccessor.findByCondition(avalInvtyLocRoleTmsg);
        int queryResCnt = avalInvtyLocRoleTmsgArray.getValidCount();
        if (queryResCnt == 0) {
            return NO_LIMIT_LIST;
        } else {
            List<String> locRoleTpList = new ArrayList<String>();
            for (int i = 0; i < queryResCnt; i++) {
                locRoleTpList.add(avalInvtyLocRoleTmsgArray.no(i).locRoleTpCd.getValue());
            }
            return locRoleTpList;
        }
        */
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        List<Map<String, Object>> locRoleTpRsList =  getLocRoleTpList(glblCmpyCd, funcId, bizAppKeyId);
        if (null == locRoleTpRsList || 0 == locRoleTpRsList.size()) {
            return NO_LIMIT_LIST;
        } else {
            List<String> locRoleTpList = new ArrayList<String>();
            for (int i = 0; i < locRoleTpRsList.size(); i++) {
                Map<String, Object> locRoleTp = (Map<String, Object>) locRoleTpRsList.get(i);
                locRoleTpList.add((String) locRoleTp.get("LOC_ROLE_TP_CD"));
            }
            return locRoleTpList;
        }
        // 10/06/2015 mod end
    }

    /**
     * <pre>
     * This API search available Location Role Type and return one of outcome.
     * </pre>
     * @param glblCmpyCd Global Company Code
     * @param funcId Function ID
     * @param locationTypeList List<String>
     * @param bizAppKeyIdArr String...
     * @return Locatin Role Type
     */
    public static List<String> getLocRoleTp(String glblCmpyCd, String funcId, List<String> locationTypeList, String... bizAppKeyIdArr) {
        // check parameter.
        // Data Company Code Check
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            return null;
        }
        // Function ID Check
        if (!ZYPCommonFunc.hasValue(funcId)) {
            return null;
        }

        // 10/06/2015 mod start
        // List<Map<String, Object>> locRoleTpList = getLocRoleTpList(glblCmpyCd, funcId, locationTypeList);
        String bizAppKeyId = getBizAppKeyIdFromArray(bizAppKeyIdArr);
        List<Map<String, Object>> locRoleTpList = getLocRoleTpList(glblCmpyCd, funcId, locationTypeList, bizAppKeyId);
        // 10/06/2015 mod end
        if (null == locRoleTpList || 0 == locRoleTpList.size()) {
            return NO_LIMIT_LIST;
        } else {
            List<String> locRoleTpArrayList = new ArrayList<String>();
            for (Map<String, Object> locRoleTp : locRoleTpList) {
                locRoleTpArrayList.add((String) locRoleTp.get("LOC_ROLE_TP_CD"));
            }
            return locRoleTpArrayList;
        }
    }

    /**
     * <pre>
     * Get Available Inventry Information.
     * </pre>
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @param bizAppId String
     * @param locationTypeList List<String>
     * @param dataSecurityList List<S21DataSecurityAttributeData>
     * @param virtualWHFlg String
     * @param bizAppKeyId String
     * @return NMXC100001EnableWHData
     */
    @SuppressWarnings("unchecked")
    private static NMXC100001EnableWHData getInvtyNmAval(String glblCmpyCd, String invtyLocCd, String bizAppId, List<String> locationTypeList, List<S21DataSecurityAttributeData> dataSecurityList, String virtualWHFlg, String bizAppKeyId) {

        String isDataSecuritySet = ZYPConstant.FLG_ON_Y;
        String isLocationTypeSet = ZYPConstant.FLG_ON_Y;
        String dataSecurity = null;
        List<String> dataSecurityListString = new ArrayList<String>();

        if (null == dataSecurityList) {
            isDataSecuritySet = ZYPConstant.FLG_OFF_N;
        } else {
            for (S21DataSecurityAttributeData dataSecurityAttributeData : dataSecurityList) {
                dataSecurity = dataSecurityAttributeData.getValue();
                if (MULTIPLICATION.equals(dataSecurity)) {
                    isDataSecuritySet = ZYPConstant.FLG_OFF_N;
                }
                dataSecurityListString.add(dataSecurity);
            }

        }

        if (null == locationTypeList) {
            isLocationTypeSet = ZYPConstant.FLG_OFF_N;
        }

        NMXC100001EnableWHData chkEnableWHData = new NMXC100001EnableWHData();

        // Searching Inventory location name
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", glblCmpyCd);
        queryParam.put("invtyLocCd", invtyLocCd);
        queryParam.put("bizAppId", bizAppId);

        queryParam.put("isLocationTypeSet", isLocationTypeSet);
        queryParam.put("locationTypeList", locationTypeList);

        queryParam.put("isDataSecuritySet", isDataSecuritySet);
        queryParam.put("dataSecurityList", dataSecurityListString);
        queryParam.put("virtualWHFlg", virtualWHFlg);
        queryParam.put("readyForOrderTaking", RGTN_STS.READY_FOR_ORDER_TAKING);
        queryParam.put("na", WH_SYS_TP.NA);
        queryParam.put("bizAppKeyId", bizAppKeyId); // 10/06/2015 add
        Map<String, Object> ssmRtn = (Map<String, Object>) ssmBatchClient.queryObject("getInvtyNmAval", queryParam);
        if (null == ssmRtn) {
            return null;
        } else {
            chkEnableWHData.setInvtyLocNm(((String) ssmRtn.get("INVTY_LOC_NM")));
            chkEnableWHData.setLocRoleTpCd(((String) ssmRtn.get("LOC_ROLE_TP_CD")));
            return chkEnableWHData;
        }
    }

    /**
     * <pre>
     * Get Available Inventry Information.
     * </pre>
     * @param glblCmpyCd String
     * @param invtyLocCd String
     * @param bizAppId String
     * @param dataSecurityList List<S21DataSecurityAttributeData>
     * @param virtualWHFlg String
     * @param bizAppKeyId String
     * @return NMXC100001EnableWHData
     */
    private static NMXC100001EnableWHData getInvtyNmAval(String glblCmpyCd, String invtyLocCd, String bizAppId, List<S21DataSecurityAttributeData> dataSecurityList, String virtualWHFlg, String bizAppKeyId) {
        // 10/06/2015 mod start
        // return getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, null, dataSecurityList, virtualWHFlg);
        return getInvtyNmAval(glblCmpyCd, invtyLocCd, bizAppId, null, dataSecurityList, virtualWHFlg, bizAppKeyId);
        // 10/06/2015 mod end
    }

    /**
     * <pre>
     * Checking if inventry exists.
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param boolean
     */
    private static boolean chkInvtyExist(String glblCmpyCd, String invtyLocCd) {

        DS_INVTY_LOC_VTMsg dsInvtyLocVTmsg = new DS_INVTY_LOC_VTMsg();
        dsInvtyLocVTmsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
        dsInvtyLocVTmsg.setConditionValue("invtyLocCd01", invtyLocCd);
        dsInvtyLocVTmsg.setSQLID("002");
        DS_INVTY_LOC_VTMsgArray dsInvtyLocVTmsgArray = (DS_INVTY_LOC_VTMsgArray) EZDTBLAccessor.findByCondition(dsInvtyLocVTmsg);
        if (dsInvtyLocVTmsgArray.getValidCount() == 0) {
            return false;
        } else {

            return true;
        }
    }

    /**
     * <pre>
     * Exit process.
     * </pre>
     * @param msgMap S21ApiMessageMap
     * @param msgId String
     */
    private static NMXC100001EnableWHData doExit(String msgId) {
        NMXC100001EnableWHData chkEnableWHData = new NMXC100001EnableWHData();

        if (msgId != null) {
            chkEnableWHData.setXxMsgId(msgId);
        }
        return chkEnableWHData;
    }

    /**
     * get Location Role Type List
     * @param glblCmpyCd String
     * @param funcId String
     * @param locationTypeList List<String>
     * @param bizAppKeyId String
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getLocRoleTpList(String glblCmpyCd, String funcId, List<String> locationTypeList, String bizAppKeyId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("funcId", funcId);
        ssmParam.put("locationTypeList", locationTypeList);
        ssmParam.put("bizAppKeyId", bizAppKeyId); // 10/06/2015 add

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getLocRoleTpList", ssmParam);
    }

    // 10/06/2015 add start
    /**
     * get Location Role Type List
     * @param glblCmpyCd String
     * @param funcId String
     * @param bizAppKeyId String
     */
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> getLocRoleTpList(String glblCmpyCd, String funcId, String bizAppKeyId) {

        Map<String, Object> ssmParam = new HashMap<String, Object>();
        ssmParam.put("glblCmpyCd", glblCmpyCd);
        ssmParam.put("funcId", funcId);
        ssmParam.put("bizAppKeyId", bizAppKeyId);

        return (List<Map<String, Object>>) ssmBatchClient.queryObjectList("getLocRoleTpList2", ssmParam);
    }

    /**
     * Get BIZ_APP_KEY_ID from array 
     * @param bizAppKeyIdArr String[]
     * @return bizAppKeyId String
     */
    private static String getBizAppKeyIdFromArray(String[] bizAppKeyIdArr) {
        String bizAppKeyId;
        if (bizAppKeyIdArr.length == 0) {
            bizAppKeyId = BLANK;
        } else {
            bizAppKeyId = bizAppKeyIdArr[0];
        }
        return bizAppKeyId;
    }
    // 10/06/2015 add end
}

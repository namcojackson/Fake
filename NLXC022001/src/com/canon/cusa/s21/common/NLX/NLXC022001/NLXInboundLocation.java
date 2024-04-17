/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 08/11/2009   Fujitsu         FAP)T.Ishioka   Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC022001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsgArray;
import business.db.VNDTMsg;
import business.db.VNDTMsgArray;
import business.db.WHTMsg;
import business.db.WHTMsgArray;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityProfile;

/**
 * <pre>
 *</pre>
 */
public class NLXInboundLocation {

    /**
     * Normal termination of result (pull-down making OK)
     */
    public static final int RESULT_OK = 0;

    /**
     * Normal termination of result (pull-down making NG result 0)
     */
    public static final int RESULT_0 = 1;

    /**
     * Result error end
     */
    public static final int RESULT_ERR = 2;

    /**
     * It is 00 in the head: ALL is not set.
     */
    public static final int ALL_N = 0;

    /**
     * It is 00 in the head: ALL is set (invalidity when the pull-down
     * number is one).
     */
    public static final int ALL_Y = 1;

    /**
     * PulldownValue WH_CD
     */
    private static final String PULL_DOWN_VALUE_WH_CD = "whCd";

    /**
     * PulldownValue VND_CD
     */
    private static final String PULL_DOWN_VALUE_VND_CD = "vndCd";

    /**
     * PulldownValue LOC_NM
     */
    private static final String PULL_DOWN_VALUE_LOC_NM = "locNm";

    /**
     * Asterisk
     */
    private static final String ASTERISK = "*";

    /**
     * Colon
     */
    private static final String COLON = ":";

    /**
     * The first value of Cd
     */
    private static final String FIRST_VALUE_CD = "00";

    /**
     * The first value of Nm
     */
    private static final String FIRST_VALUE_NM = "ALL";

    /**
     * WH table column name WH_CD
     */
    private static final String TBL_COL_WH_CD = "WH_CD";

    /**
     * VND table column name VND_CD
     */
    private static final String TBL_COL_VND_CD = "VND_CD";

    /**
     * WH and VND table column name LOC_NM
     */
    private static final String TBL_COL_LOC_NM = "LOC_NM";

    /**
     * Map key name glblCmpyCd
     */
    private static final String GLBL_CMPY_CD = "glblCmpyCd";

    /**
     * Map key name rgtnStsCd
     */
    private static final String RGTN_STS_CD = "rgtnStsCd";

    /**
     * Map key name whCd
     */
    private static final String WH_CD = "whCd";

    /**
     * Map key name vndTpCd
     */
    private static final String VND_TP_CD = "vndTpCd";

    /**
     * Map key name vndCd
     */
    private static final String VND_CD = "vndCd";

    /**
     * Number of WH setting digits
     */
    private static final int WH_LENGTH = 13;

    /**
     * Number of VND setting digits
     */
    private static final int VND_LENGTH = 25;

    /**
     * WH table retrieval SSM call function name
     */
    private static final String FIND_WH = "findWhListForPullDown";

    /**
     * VND table retrieval SSM call function name
     */
    private static final String FIND_VND = "findVndListForPullDown";

    /**
     * Number of data set to PullDown
     */
    private static int pullDownValidCount = 0;

    /**
     * Acquisition key WH from S21DataSecurityProfile
     */
    private static final String ATTR_NM_WH = "WH";

    /**
     * Acquisition key VND from S21DataSecurityProfile
     */
    private static final String ATTR_NM_VND = "VND";

    /**
     * CreateWhListForPullDown method;PullDown of Warehouse is made.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param profile S21DataSecurityProfile
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param allFlg int
     * @return int (Normal termination: RESULT_OK and RESULT_0 error:
     * RESULT_ERR)
     */
    public static int createWhListForPullDown(String glblCmpyCd, S21DataSecurityProfile profile, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, int allFlg) {

        EZDDebugOutput.println(1, "NLXInboundLocation_createWhListForPullDown================================START", "");

        // The parameter is checked.
        if (!checkParam(glblCmpyCd, profile, valueItemArray, dispItemArray, allFlg)) {
            return RESULT_ERR;
        }

        // The list of WH is acquired from the profile acquisition
        // parts.
        List<String> cdWhList = getSecurityAttributeData(profile, ATTR_NM_WH);
        if (cdWhList == null) {
            return RESULT_ERR;
        }

        int result = RESULT_OK;

        try {

            // PullDown of Warehouse is made.
            result = createWhListForPullDown(glblCmpyCd, cdWhList, valueItemArray, dispItemArray, true, allFlg);

        } catch (SQLException e) {

            return RESULT_ERR;

        } finally {
            EZDDebugOutput.println(1, "NLXInboundLocation_createWhListForPullDown================================END", "");
        }

        return result;
    }

    /**
     * CreateVndListForPullDown method;PullDown of Vendor is made.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param profile S21DataSecurityProfile
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param allFlg int
     * @return int (Normal termination: RESULT_OK and RESULT_0 error:
     * RESULT_ERR)
     */
    public static int createVndListForPullDown(String glblCmpyCd, S21DataSecurityProfile profile, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, int allFlg) {

        EZDDebugOutput.println(1, "NLXInboundLocation_createVndListForPullDown================================START", "");

        // The parameter is checked.
        if (!checkParam(glblCmpyCd, profile, valueItemArray, dispItemArray, allFlg)) {
            return RESULT_ERR;
        }

        // The list of VND is acquired from the profile acquisition
        // parts.
        List<String> cdVndList = getSecurityAttributeData(profile, ATTR_NM_VND);
        if (cdVndList == null) {
            return RESULT_ERR;
        }

        int result = RESULT_OK;

        try {

            // PullDown of Vendor is made.
            result = createVndListForPullDown(glblCmpyCd, cdVndList, valueItemArray, dispItemArray, true, allFlg);

        } catch (SQLException e) {

            return RESULT_ERR;

        } finally {
            EZDDebugOutput.println(1, "NLXInboundLocation_createVndListForPullDown================================END", "");
        }

        return result;
    }

    /**
     * CreateWhAndVndListForPullDown method;Warehouse + PullDown of
     * Vendor is made.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param profile S21DataSecurityProfile
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param allFlg int
     * @return int (Normal termination: RESULT_OK and RESULT_0 error:
     * RESULT_ERR)
     */
    public static int createWhAndVndListForPullDown(String glblCmpyCd, S21DataSecurityProfile profile, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, int allFlg) {

        EZDDebugOutput.println(1, "NLXInboundLocation_createWhAndVndListForPullDown================================START", "");

        // The parameter is checked.
        if (!checkParam(glblCmpyCd, profile, valueItemArray, dispItemArray, allFlg)) {
            return RESULT_ERR;
        }

        int result = RESULT_OK;

        try {

            // The list of WH is acquired from the profile acquisition
            // parts.
            List<String> cdWhList = getSecurityAttributeData(profile, ATTR_NM_WH);
            if (cdWhList != null) {

                // PullDown of Warehouse is made.
                result = createWhListForPullDown(glblCmpyCd, cdWhList, valueItemArray, dispItemArray, false, allFlg);

                // At the error
                // - To retrieve VND as follows when the WH retrieval
                // is 0, retrun is not done here.
                if (result == RESULT_ERR) {
                    return result;
                }

            } else {
                pullDownValidCount = 0;
            }

            // The list of VND is acquired from the profile
            // acquisition parts.
            List<String> cdVndList = getSecurityAttributeData(profile, ATTR_NM_VND);
            if (cdVndList == null) {

                // When 2 or more a set number, and allFlg are ALL_Y
                if (pullDownValidCount > 1 && allFlg == ALL_Y) {

                    // It is 00 in the head: ALL is set.
                    ZYPPulldownValueSetter.insertFirstData(FIRST_VALUE_CD, valueItemArray, FIRST_VALUE_NM, dispItemArray, COLON);
                }

                // When WH is a unmaking
                if (pullDownValidCount < 1) {
                    return RESULT_ERR;

                    // When you have made only WH
                } else {
                    return RESULT_OK;
                }
            }

            // PullDown of Vendor is made.
            result = createVndListForPullDown(glblCmpyCd, cdVndList, valueItemArray, dispItemArray, false, allFlg);

            // In case of normally
            if (result != RESULT_OK) {

                // When 2 or more a set number, and allFlg are ALL_Y
                if (pullDownValidCount > 1 && allFlg == ALL_Y) {

                    // It is 00 in the head: ALL is set.
                    ZYPPulldownValueSetter.insertFirstData(FIRST_VALUE_CD, valueItemArray, FIRST_VALUE_NM, dispItemArray, COLON);
                }

                // When WH is a unmaking
                if (pullDownValidCount < 1) {
                    return RESULT_0;

                    // When you have made only WH
                } else {
                    return RESULT_OK;
                }
            }

            // Sort does the coexistence data of WH and VND.
            String[] cd = new String[pullDownValidCount];
            String[] nm = new String[pullDownValidCount];

            for (int i = 0; i < pullDownValidCount; i++) {
                cd[i] = valueItemArray.no(i).getValue();
                nm[i] = dispItemArray.no(i).getValue();
            }
            Arrays.sort(cd);
            Arrays.sort(nm);
            for (int i = 0; i < pullDownValidCount; i++) {
                valueItemArray.no(i).setValue(cd[i]);
                dispItemArray.no(i).setValue(nm[i]);
            }

        } catch (SQLException e) {

            return RESULT_ERR;

        } finally {

            EZDDebugOutput.println(1, "NLXInboundLocation_createWhAndVndListForPullDown================================END", "");
        }

        return result;
    }

    /**
     * CreateWhListForPullDown method;PullDown of Warehouse is made.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param cdList List<String>
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param flg boolean
     * @param allFlg int
     * @return int (Normal termination: RESULT_OK and RESULT_0 error:
     * RESULT_ERR)
     * @throws SQLException SQLException
     */
    private static int createWhListForPullDown(String glblCmpyCd, List<String> cdList, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, boolean flg, int allFlg) throws SQLException {

        EZDDebugOutput.println(1, "NLXInboundLocation_createWhListForPullDown================================START", "");

        // WH table retrieval
        WHTMsgArray tMsgArray = findWhListForPullDown(glblCmpyCd, cdList, valueItemArray.length());

        // When the retrieval result is 0
        if (tMsgArray == null) {
            return RESULT_0;
        }

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, PULL_DOWN_VALUE_WH_CD);
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, PULL_DOWN_VALUE_LOC_NM);

        // Initialization of number of data set to present PullDown
        pullDownValidCount = 0;

        // Only WH
        int length = WH_LENGTH;

        // Setting of display digit
        // WH and VND mixture
        if (!flg) {
            length = VND_LENGTH;
        }

        // Pulldown-List is generated from EZDTMsgArray.
        set(tMsgArray, tMsgKeys, valueItemArray, dispItemArray, length);

        // When 2 or more a set number when PullDown only of WH is
        // made, and allFlg are ALL_Y
        if (flg && pullDownValidCount > 1 && allFlg == ALL_Y) {

            // It is 00 in the head: ALL is set.
            ZYPPulldownValueSetter.insertFirstData(FIRST_VALUE_CD, valueItemArray, FIRST_VALUE_NM, dispItemArray, COLON);
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_createWhListForPullDown================================END", "");

        return RESULT_OK;
    }

    /**
     * CreateVndListForPullDown method;PullDown of Vendor is made.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param cdList List<String>
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param flg boolean
     * @param allFlg int
     * @return int (Normal termination: RESULT_OK and RESULT_0 error:
     * RESULT_ERR)
     * @throws SQLException SQLException
     */
    private static int createVndListForPullDown(String glblCmpyCd, List<String> cdList, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, boolean flg, int allFlg) throws SQLException {

        EZDDebugOutput.println(1, "NLXInboundLocation_createVndListForPullDown================================START", "");

        // VND table retrieval
        VNDTMsgArray tMsgArray = findVndListForPullDown(glblCmpyCd, cdList, valueItemArray.length());

        // When the retrieval result is 0
        if (tMsgArray == null) {
            return RESULT_0;
        }

        Map<String, String> tMsgKeys = new HashMap<String, String>();
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, PULL_DOWN_VALUE_VND_CD);
        tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, PULL_DOWN_VALUE_LOC_NM);

        // When you make PullDown only of VND
        if (flg) {

            // Initialization of number of data set to present
            // PullDown
            pullDownValidCount = 0;
        }

        // Pulldown-List is generated from EZDTMsgArray.
        set(tMsgArray, tMsgKeys, valueItemArray, dispItemArray, VND_LENGTH);

        // When 2 or more a set number, and allFlg are ALL_Y
        if (pullDownValidCount > 1 && allFlg == ALL_Y) {

            // It is 00 in the head: ALL is set.
            ZYPPulldownValueSetter.insertFirstData(FIRST_VALUE_CD, valueItemArray, FIRST_VALUE_NM, dispItemArray, COLON);
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_createVndListForPullDown================================END", "");

        return RESULT_OK;
    }

    /**
     * Method of checking parameter;The argument putter meter is
     * checked.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param profile S21DataSecurityProfile
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param allFlg int
     * @return boolean
     */
    private static boolean checkParam(String glblCmpyCd, S21DataSecurityProfile profile, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, int allFlg) {

        EZDDebugOutput.println(1, "NLXInboundLocation_checkParam================================START", "");

        // When glblCmpyCd is a unsetting
        if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {
            EZDDebugOutput.println(1, "String glblCmpyCd == null", "");
            return false;
        }

        // When profile is a unsetting
        if (profile == null) {
            EZDDebugOutput.println(1, "S21DataSecurityProfile profile == null", "");
            return false;
        }

        if (valueItemArray == null) {
            EZDDebugOutput.println(1, "EZDCStringItemArray valueItemArray == null", "");
            return false;
        }

        if (dispItemArray == null) {
            EZDDebugOutput.println(1, "EZDCStringItemArray dispItemArray == null", "");
            return false;
        }

        if (ALL_N != allFlg && ALL_Y != allFlg) {
            EZDDebugOutput.println(1, "int allFlg != ALL_N && int allFlg != ALL_Y", "");
            return false;
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_checkParam================================END", "");

        return true;
    }

    /**
     * Method of acquiring profile list;The list of specification
     * attrNm from the profile acquisition parts it is acquired.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param profile S21DataSecurityProfile
     * @param attrNm String
     * @return List<String>
     */
    private static List<String> getSecurityAttributeData(S21DataSecurityProfile profile, String attrNm) {

        EZDDebugOutput.println(1, "NLXInboundLocation_getSecurityAttributeData================================START", "");

        // The list of specification attrNm from the profile
        // acquisition parts it is acquired.
        List<S21DataSecurityAttributeData> attrDataList = profile.getDataSecurityAttributeDataListFor(attrNm);

        if (attrDataList == null || attrDataList.isEmpty()) {
            return null;
        }

        List<String> cdList = new ArrayList<String>();
        for (int i = 0; i < attrDataList.size(); i++) {
            cdList.add(attrDataList.get(i).getValue());
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_getSecurityAttributeData================================END", "");

        return cdList;
    }

    /**
     * Method of retrieving WH table;The WH table is retrieved, and
     * the result is returned.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param cdList List<String>
     * @param valueItemArrayLength int
     * @return WHTMsgArray
     * @throws SQLException
     */
    private static WHTMsgArray findWhListForPullDown(String glblCmpyCd, List<String> cdList, int valueItemArrayLength) throws SQLException {

        EZDDebugOutput.println(1, "NLXInboundLocation_findWhListForPullDown================================START", "");

        S21SsmLowLevelCodingClient ssmLowLev = S21SsmLowLevelCodingClient.getClient(NLXInboundLocation.class);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(GLBL_CMPY_CD, glblCmpyCd);
        map.put(RGTN_STS_CD, new String[] {RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED });

        // When "*" is included
        if (cdList.contains(ASTERISK)) {
            map.put(WH_CD, null);

            // Things except "*"
        } else {

            String[] whList = (String[]) cdList.toArray(new String[0]);
            Arrays.sort(whList);
            map.put(WH_CD, whList);
        }

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<WHTMsg> whList = null;
        WHTMsgArray tMsgArray = null;

        try {
            pstmt = ssmLowLev.createPreparedStatement(FIND_WH, map);
            resultSet = pstmt.executeQuery();

            int resultCount = 0;
            while (resultSet.next()) {
                resultCount++;
            }

            int outLength = resultCount;

            // When the retrieval result is 0
            if (outLength < 1) {
                return null;
            }

            // The pull-down setting number is acquired.
            int length = getLength(outLength, valueItemArrayLength);

            whList = new ArrayList<WHTMsg>();
            tMsgArray = new WHTMsgArray();

            // The cursor is set immediately before the first line.
            resultSet.beforeFirst();
            int count = 0;
            while (resultSet.next()) {

                if (count == length) {
                    break;
                }

                String whCd = resultSet.getString(TBL_COL_WH_CD);
                String locNm = resultSet.getString(TBL_COL_LOC_NM);

                if (ZYPCommonFunc.hasValue(whCd) && ZYPCommonFunc.hasValue(locNm)) {
                    WHTMsg whMsg = new WHTMsg();
                    whMsg.whCd.setValue(whCd);
                    whMsg.locNm.setValue(locNm);
                    whList.add(whMsg);

                    count++;
                }
            }

            tMsgArray.setMsgList(whList.toArray(new WHTMsg[0]));
            tMsgArray.setValidCount(whList.size());

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_findWhListForPullDown================================END", "");

        return tMsgArray;
    }

    /**
     * Method of retrieving VND table;The VND table is retrieved, and
     * the result is returned.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param glblCmpyCd String
     * @param cdList List<String>
     * @param valueItemArrayLength int
     * @return VNDTMsgArray
     * @throws SQLException
     */
    private static VNDTMsgArray findVndListForPullDown(String glblCmpyCd, List<String> cdList, int valueItemArrayLength) throws SQLException {

        EZDDebugOutput.println(1, "NLXInboundLocation_findVndListForPullDown================================START", "");

        S21SsmLowLevelCodingClient ssmLowLev = S21SsmLowLevelCodingClient.getClient(NLXInboundLocation.class);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put(GLBL_CMPY_CD, glblCmpyCd);
        map.put(RGTN_STS_CD, new String[] {RGTN_STS.READY_FOR_ORDER_TAKING, RGTN_STS.TERMINATED });
        map.put(VND_TP_CD, new String[] {VND_TP.CROSSDOCK, VND_TP.DIVERSION, VND_TP.TRANSLOADING });

        // When "*" is included
        if (cdList.contains(ASTERISK)) {
            map.put(VND_CD, null);

            // Things except "*"
        } else {

            String[] vndList = (String[]) cdList.toArray(new String[0]);
            Arrays.sort(vndList);
            map.put(VND_CD, vndList);
        }

        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<VNDTMsg> vndList = null;
        VNDTMsgArray tMsgArray = null;

        try {
            pstmt = ssmLowLev.createPreparedStatement(FIND_VND, map);
            resultSet = pstmt.executeQuery();

            int resultCount = 0;
            while (resultSet.next()) {
                resultCount++;
            }

            int outLength = resultCount;

            // When the retrieval result is 0
            if (outLength < 1) {
                return null;
            }

            // The pull-down setting number is acquired.
            int length = getLength(outLength, valueItemArrayLength);

            vndList = new ArrayList<VNDTMsg>();
            tMsgArray = new VNDTMsgArray();

            // The cursor is set immediately before the first line.
            resultSet.beforeFirst();
            int count = 0;
            while (resultSet.next()) {

                if (count == length) {
                    break;
                }

                String vndCd = resultSet.getString(TBL_COL_VND_CD);
                String locNm = resultSet.getString(TBL_COL_LOC_NM);

                if (ZYPCommonFunc.hasValue(vndCd) && ZYPCommonFunc.hasValue(locNm)) {
                    VNDTMsg vndMsg = new VNDTMsg();
                    vndMsg.vndCd.setValue(vndCd);
                    vndMsg.locNm.setValue(locNm);
                    vndList.add(vndMsg);

                    count++;
                }
            }

            tMsgArray.setMsgList(vndList.toArray(new VNDTMsg[0]));
            tMsgArray.setValidCount(vndList.size());

        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, resultSet);
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_findVndListForPullDown================================END", "");

        return tMsgArray;
    }

    /**
     * Method of acquiring pull-down setting number;The number of
     * cases set to the pull-down is returned.
     * <dd>The method explanation: The business peculiarity
     * processing is executed.
     * @param outLength int
     * @param arrayLength int
     * @return int
     */
    private static int getLength(int outLength, int arrayLength) {

        EZDDebugOutput.println(1, "NLXInboundLocation_getLength================================START", "");

        // When the acquisition number is larger, the pull-down
        // storage maximum number is returned.
        if (outLength > arrayLength) {
            return arrayLength;

            // When the acquisition number and the pull-down storage
            // maximum number are the same
            // To add 00 to the head, it returns it in number -1 of
            // maximum storage.
        } else if (outLength == arrayLength) {
            return arrayLength - 1;
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_getLength================================END", "");

        // The acquisition number is returned excluding the
        // above-mentioned.
        return outLength;
    }

    /**
     * Pulldown-List is generated from EZDTMsgArray. <br>
     * The value assumed to be "display" is assumed to be a value in
     * which the values of "value" and "display" are connected by
     * "separator" of the argument. <br>
     * The order of the row of Pulldown-List is assumed like the order
     * of the row of the element of EZDTMsgArray. <br>
     * <br>
     * <br>
     * Please notify which data that EZDTMsgArray maintains to make
     * Pulldowon-List with tMsgKeys of the argument. <br>
     * For instance, it is value of "PmtTermCd" of EZDTMsg, and < br >
     * as for "value" of Pulldown-List It is "PmtTermNm. " of EZDTMsg
     * as for "display" of Pulldown-List It drinks and to generate it
     * with [atai], tMsgKeys is generated as follows. <br>
     * <br>
     * <code>
     * Map<String, String> tMsgKeys = new HashMap<String, String>();<br>
     * tMsgKeys.put( ZYPPulldownValueSetter.KEY_VALUE, "pmtTermCd" );<br>
     * tMsgKeys.put( ZYPPulldownValueSetter.KEY_DISPLAY, "pmtTermNm" );<br>
     * </code> <br>
     * @param tMsgArray EZDTMsgArray
     * @param tMsgKeys Map<String, String>
     * @param valueItemArray EZDCStringItemArray
     * @param dispItemArray EZDCStringItemArray
     * @param strLength int
     */
    private static void set(EZDTMsgArray tMsgArray, Map<String, String> tMsgKeys, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, int strLength) {

        EZDDebugOutput.println(1, "NLXInboundLocation_set================================START", "");

        if (pullDownValidCount == 0) {
            valueItemArray.clear();
            dispItemArray.clear();
        }

        String valueItemKey = tMsgKeys.get(ZYPPulldownValueSetter.KEY_VALUE);
        String displayItemKey = tMsgKeys.get(ZYPPulldownValueSetter.KEY_DISPLAY);

        if (tMsgArray != null) {

            // value duplicate check
            List<String> valueList = new ArrayList<String>();
            List<String> displayList = new ArrayList<String>();
            for (int i = 0; i < tMsgArray.length(); i++) {
                if (i < valueItemArray.length()) {
                    String value = tMsgArray.get(i).getValueString(valueItemKey, -1);
                    String display = tMsgArray.get(i).getValueString(displayItemKey, -1);
                    if (!valueList.contains(value)) {
                        valueList.add(value);
                        displayList.add(display);
                    }

                } else {
                    break;
                }
            }

            // set Pull-down List
            for (int i = 0; i < valueList.size(); i++) {
                if (pullDownValidCount < valueItemArray.length()) {

                    String value = ZYPCommonFunc.trimTail(valueList.get(i));
                    String disp = ZYPCommonFunc.trimTail(ZYPCommonFunc.concatString(valueList.get(i), COLON, displayList.get(i)));

                    valueItemArray.no(pullDownValidCount).setValue(checkLength(value, strLength));
                    dispItemArray.no(pullDownValidCount).setValue(checkLength(disp, strLength));

                    pullDownValidCount++;

                } else {
                    break;
                }
            }
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_set================================END", "");
    }

    /**
     * Data digit number check When the number of characters is more
     * than that of < dd > specified bytes long, it returns it by the
     * number of specified bytes. < dd > Remarks:
     * @param str String
     * @param byteLength int
     * @return String
     */
    private static String checkLength(String str, int byteLength) {

        EZDDebugOutput.println(1, "NLXInboundLocation_checkLength================================START", "");

        if (byteLength != 0 && str.getBytes().length > byteLength) {
            return ZYPCommonFunc.subByteString(str, byteLength);
        }

        EZDDebugOutput.println(1, "NLXInboundLocation_checkLength================================END", "");

        return str;
    }

}

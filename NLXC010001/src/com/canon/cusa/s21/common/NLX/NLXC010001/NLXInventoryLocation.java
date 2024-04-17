/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Fujitsu         FAP)D.Ushida    Create          N/A
 * 05/01/2013   Fujitsu         Y.Taoka         Update          OCE WDS R-WH001
 *</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC010001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.common.EZDTMsgArray;
import business.db.WHTMsg;
import business.db.WHTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC010001.constant.NLXInventoryLocationConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;
import com.canon.cusa.s21.framework.dao.ssm.ezd.client.S21SsmLowLevelCodingClient;
import com.canon.cusa.s21.framework.userprofile.S21DataSecurityAttributeData;

/**
 * <pre>
 *</pre>
 */
public class NLXInventoryLocation extends S21ApiCommonBase implements NLXInventoryLocationConstant {

    /**
     * @param glblCmpyCd String
     * @param whList List<S21DataSecurityAttributeData>
     * @param valueItemArray EZDCStringItemArray -Pulldown-List code
     * @param dispItemArray EZDCStringItemArray -Pulldown-List display
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, List<S21DataSecurityAttributeData> whList, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {

        return exec(glblCmpyCd, whList, true, valueItemArray, dispItemArray);

    }

    /**
     * @param glblCmpyCd String
     * @param whList List<S21DataSecurityAttributeData>
     * @param valueItemArray EZDCStringItemArray -Pulldown-List code
     * @param dispItemArray EZDCStringItemArray -Pulldown-List display
     * @param includeWhForAdj boolean -include WH for adjustment
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, List<S21DataSecurityAttributeData> whList, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, boolean includeWhForAdj) {

        return exec(glblCmpyCd, whList, true, valueItemArray, dispItemArray, includeWhForAdj);

    }

    /**
     * @param glblCmpyCd String
     * @param whList List<S21DataSecurityAttributeData>
     * @param rgtnCheck boolean
     * @param valueItemArray EZDCStringItemArray -Pulldown-List code
     * @param dispItemArray EZDCStringItemArray -Pulldown-List display
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, List<S21DataSecurityAttributeData> whList, boolean rgtnCheck, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {
        return exec(glblCmpyCd, whList, rgtnCheck, valueItemArray, dispItemArray, false);

    }

    /**
     * @param glblCmpyCd String
     * @param whList List<S21DataSecurityAttributeData>
     * @param rgtnCheck boolean
     * @param valueItemArray EZDCStringItemArray -Pulldown-List code
     * @param dispItemArray EZDCStringItemArray -Pulldown-List display
     * @param includeWhForAdj boolean -include WH for adjustment
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, List<S21DataSecurityAttributeData> whList, boolean rgtnCheck, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray, boolean includeWhForAdj) {

        EZDDebugOutput.println(1, "NLXInventoryLocation_exec================================START", "");

        try {

            if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

                EZDDebugOutput.println(1, "glblCmpyCd is Not set", "");

                return MSG_ID_GENERAL_ERROR;
            }

            if (whList == null || whList.isEmpty()) {

                EZDDebugOutput.println(1, "whList is Not set", "");

                return MSG_ID_GENERAL_ERROR;
            }

            for (int i = 0; i < whList.size(); i++) {
                if (!ZYPCommonFunc.hasValue(whList.get(i).getValue())) {

                    EZDDebugOutput.println(1, "whList'Contents is Not set" + i, "");

                    return MSG_ID_GENERAL_ERROR;
                }
            }

            String[] rgtnStsCdArray = null;
            String[] whCdArray = null;
            String[] whCdAdjArray = null;

            if (rgtnCheck) {
                rgtnStsCdArray = new String[]{RGTN_STS.PENDING_COMPLETION, RGTN_STS.TERMINATED};
            }

            List<String> whCdList = new ArrayList<String>();
            if (!ASTERISK.equals(whList.get(0).getValue())) {
                for (S21DataSecurityAttributeData whData : whList) {
                    whCdList.add(whData.getValue());
                }
                whCdArray = whCdList.toArray(new String[]{});
            }
            if (includeWhForAdj) {
                String whCdAdjs = ZYPCodeDataUtil.getVarCharConstValue(VAR_CHAR_CONST_NM_ADDL_WH_ADJ, glblCmpyCd);
                if (ZYPCommonFunc.hasValue(whCdAdjs)) {
                    whCdAdjArray = whCdAdjs.split(VAR_CHAR_CONST_NM_ADDL_WH_ADJ_DELIMITER);
                    if (!ASTERISK.equals(whList.get(0).getValue())) {
                        List<String> whCdAdjList = new ArrayList<String>();
                        for (int i = 0; i < whCdAdjArray.length; i++) {
                            if (whCdList.contains(whCdAdjArray[i])) {
                                whCdAdjList.add(whCdAdjArray[i]);
                            }
                        }
                        if (whCdAdjList.isEmpty()) {
                            whCdAdjArray = null;
                        } else {
                            whCdAdjArray = whCdAdjList.toArray(new String[]{});
                        }
                    }
                }
            }

            Map<String, Object> ssmParamMap = new HashMap<String, Object>();
            ssmParamMap.put("glblCmpyCd", glblCmpyCd);
            ssmParamMap.put("whSysTpCdNA", WH_SYS_TP.NA);
            ssmParamMap.put("rgtnStsCdArray", rgtnStsCdArray);
            ssmParamMap.put("whCdArray", whCdArray);
            ssmParamMap.put("whCdAdjArray", whCdAdjArray);
            EZDTMsgArray tMsgArray = new WHTMsgArray();
            try {
                tMsgArray = getWhInfoArray(ssmParamMap);
            } catch (SQLException e) {
                EZDDebugOutput.println(1, "SQLException occurred", "");
                return MSG_ID_GENERAL_ERROR;
            }
            if (tMsgArray.length() == 0) {

                EZDDebugOutput.println(1, "WH table No Search : conditions[glblCmpyCd]=" + glblCmpyCd + ",[whCd]=ALL", "");

                return MSG_ID_GENERAL_ERROR;
            } else {

                WHTMsg whTMsg = null;

                for (int i = 0; i < tMsgArray.length(); i++) {
                    whTMsg = (WHTMsg) tMsgArray.get(i);

                    if (WH_CD_LENGTH < whTMsg.whCd.getValue().length()) { //MOD 05/01/2013 R-WH001

                        EZDDebugOutput.println(1, "WH Code exceeded the length of " + WH_CD_LENGTH + " digits. [whCd]=" + whTMsg.whCd.getValue(), ""); //MOD 05/01/2013 R-WH001

                        return MSG_ID_GENERAL_ERROR;

                    }
                }
            }
            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE, WH_WH_CD);
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, WH_LOC_NM);

            ZYPPulldownValueSetter.set(tMsgArray, tMsgKeys, valueItemArray, dispItemArray, COLON);

            return null;

        } finally {
            EZDDebugOutput.println(1, "NLXInventoryLocation_exec================================END", "");
        }
    }

    /**
     * @param ssmParamMap Map<String, Object>
     * @return EZDTMsgArray(not null)
     */
    private static EZDTMsgArray getWhInfoArray(Map<String, Object> ssmParamMap) throws SQLException {

        S21SsmLowLevelCodingClient ssmLowLevelCodingClient = S21SsmLowLevelCodingClient.getClient(NLXInventoryLocation.class);
        PreparedStatement pstmt = ssmLowLevelCodingClient.createPreparedStatement("getWhInfoArray", ssmParamMap);
        ResultSet ssmResult = pstmt.executeQuery();
        List<WHTMsg> resultWhTMsgList = new ArrayList<WHTMsg>();
        EZDTMsgArray tMsgArray = new WHTMsgArray();
        try {
            while (ssmResult.next()) {
                WHTMsg resultWhTMsg = new WHTMsg();
                ZYPEZDItemValueSetter.setValue(resultWhTMsg.whCd, ssmResult.getString("WH_CD"));
                ZYPEZDItemValueSetter.setValue(resultWhTMsg.locNm, ssmResult.getString("LOC_NM"));
                resultWhTMsgList.add(resultWhTMsg);
            }
            tMsgArray.setMsgList(resultWhTMsgList.toArray(new EZDTMsg[0]));
        } finally {
            S21SsmLowLevelCodingClient.closeResource(pstmt, ssmResult);
        }
        return tMsgArray;
    }

}

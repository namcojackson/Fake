/**
 * <Pre>
 * 
 * Copyright(c)2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 */
/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/30/2009   Fujitsu         FAP)D.Ushida    Create          N/A
 *</pre>
 */
package com.canon.cusa.s21.common.NLX.NLXC011001;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.AVAL_INVTY_APP_IDTMsgArray;
import business.db.LOC_STSTMsg;
import business.db.LOC_STSTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC011001.constant.NLXLocationStatusConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/** 
 *<pre>
 *</pre>
 */
public class NLXLocationStatus extends S21ApiCommonBase implements NLXLocationStatusConstant {

    /**
     * @param glblCmpyCd String
     * @param srnId String
     * @param valueItemArray EZDCStringItemArray - Pulldown-List code
     * @param dispItemArray EZDCStringItemArray - Pulldown-List display
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, String srnId, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {

        EZDDebugOutput.println(1, "NLXLocationStatus_exec================================START", "");

        try {

            if (!ZYPCommonFunc.hasValue(glblCmpyCd)) {

                EZDDebugOutput.println(1, "glblCmpyCd is Not set", "");

                return MSG_ID_GENERAL_ERROR;
            } else {
                // do nothing
            }

            if (!ZYPCommonFunc.hasValue(srnId)) {

                EZDDebugOutput.println(1, "scrId is Not set", "");

                return MSG_ID_GENERAL_ERROR;
            } else {
                // do nothing
            }

            AVAL_INVTY_APP_IDTMsg inMsg = new AVAL_INVTY_APP_IDTMsg();
            inMsg.setSQLID("002");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("bizAppId01", srnId);
            AVAL_INVTY_APP_IDTMsgArray outMsgList = (AVAL_INVTY_APP_IDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (outMsgList.getValidCount() == 0) {

                EZDDebugOutput.println(1, "AVAL_INVTY_APP_ID table No Search : conditions[glblCmpyCd]=" + glblCmpyCd + ",[bizAppId]=" + srnId, "");

                return MSG_ID_GENERAL_ERROR;
            } else {
                // do nothing
            }

            TreeMap<String, LOC_STSTMsg> treeMap = new TreeMap<String, LOC_STSTMsg>();

            for (int i = 0; i < outMsgList.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(outMsgList.no(i).locStsCd)) {
                    return MSG_ID_GENERAL_ERROR;
                } else {
                    // do nothing
                }

                LOC_STSTMsg tMsg = (LOC_STSTMsg) ZYPCodeDataUtil.findByCode(TBL_LOC_STS, glblCmpyCd, outMsgList.no(i).locStsCd.getValue());

                if (tMsg != null) {
                    treeMap.put(ZYPCommonFunc.leftPad(String.valueOf(tMsg.locStsSortNum.getValueInt()), MAX_LENGTH, PADDING_STR), tMsg);
                } else {

                    EZDDebugOutput.println(1, "LOC_STS table No Search : conditions[glblCmpyCd]=" + glblCmpyCd + ",[locStsCd]=" + outMsgList.no(i).locStsCd.getValue(), "");

                    return MSG_ID_GENERAL_ERROR;
                }
            }

            LOC_STSTMsgArray tMsgArray = new LOC_STSTMsgArray();
            tMsgArray.setMsgList(treeMap.values().toArray(new EZDTMsg[0]));
            tMsgArray.setValidCount(treeMap.size());

            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE,   LOC_STS_LOC_STS_CD);
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, LOC_STS_LOC_STS_NM);
            ZYPPulldownValueSetter.set(tMsgArray, tMsgKeys, valueItemArray, dispItemArray, COLON);

            return null;

        } finally {
            EZDDebugOutput.println(1, "NLXLocationStatus_exec================================END", "");
        }
    }
}


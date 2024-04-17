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
package com.canon.cusa.s21.common.NLX.NLXC012001;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import parts.common.EZDCStringItemArray;
import parts.common.EZDDebugOutput;
import parts.common.EZDTMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.AVAL_INVTY_APP_IDTMsg;
import business.db.AVAL_INVTY_APP_IDTMsgArray;
import business.db.STK_STSTMsg;
import business.db.STK_STSTMsgArray;

import com.canon.cusa.s21.common.NLX.NLXC012001.constant.NLXStockStatusConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPPulldownValueSetter;
import com.canon.cusa.s21.framework.api.S21ApiCommonBase;

/** 
 *<pre>
 *</pre>
 */
public class NLXStockStatus extends S21ApiCommonBase implements NLXStockStatusConstant {

    /**
     * @param glblCmpyCd String
     * @param srnId String
     * @param valueItemArray EZDCStringItemArray - Pulldown-List code
     * @param dispItemArray EZDCStringItemArray - Pulldown-List display
     * @return error:MssageCode,normal:null
     */
    public static String exec(String glblCmpyCd, String srnId, EZDCStringItemArray valueItemArray, EZDCStringItemArray dispItemArray) {

        EZDDebugOutput.println(1, "NLXStockStatus_exec================================START", "");

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
            inMsg.setSQLID("003");
            inMsg.setConditionValue("glblCmpyCd01", glblCmpyCd);
            inMsg.setConditionValue("bizAppId01", srnId);
            AVAL_INVTY_APP_IDTMsgArray outMsgList = (AVAL_INVTY_APP_IDTMsgArray) EZDTBLAccessor.findByCondition(inMsg);

            if (outMsgList.getValidCount() == 0) {

                EZDDebugOutput.println(1, "AVAL_INVTY_APP_ID table No Search : conditions[glblCmpyCd]=" + glblCmpyCd + ",[bizAppId]=" + srnId, "");

                return MSG_ID_GENERAL_ERROR;
            } else {
                // do nothing
            }

            TreeMap<String, STK_STSTMsg> treeMap = new TreeMap<String, STK_STSTMsg>();

            for (int i = 0; i < outMsgList.getValidCount(); i++) {

                if (!ZYPCommonFunc.hasValue(outMsgList.no(i).stkStsCd)) {
                    return MSG_ID_GENERAL_ERROR;
                } else {
                    // do nothing
                }

                STK_STSTMsg tMsg = (STK_STSTMsg) ZYPCodeDataUtil.findByCode(TBL_STK_STS, glblCmpyCd, outMsgList.no(i).stkStsCd.getValue());

                if (tMsg != null) {
                    treeMap.put(ZYPCommonFunc.leftPad(String.valueOf(tMsg.stkStsSortNum.getValueInt()), MAX_LENGTH, PADDING_STR), tMsg);
                } else {

                    EZDDebugOutput.println(1, "STK_STS table No Search : conditions[glblCmpyCd]=" + glblCmpyCd + ",[stkStsCd]=" + outMsgList.no(i).stkStsCd.getValue(), "");

                    return MSG_ID_GENERAL_ERROR;
                }
            }

            STK_STSTMsgArray tMsgArray = new STK_STSTMsgArray();
            tMsgArray.setMsgList(treeMap.values().toArray(new EZDTMsg[0]));
            tMsgArray.setValidCount(treeMap.size());

            Map<String, String> tMsgKeys = new HashMap<String, String>();
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_VALUE,   STK_STS_STK_STS_CD);
            tMsgKeys.put(ZYPPulldownValueSetter.KEY_DISPLAY, STK_STS_STK_STS_NM);
            ZYPPulldownValueSetter.set(tMsgArray, tMsgKeys, valueItemArray, dispItemArray, COLON);

            return null;

        } finally {
            EZDDebugOutput.println(1, "NLXStockStatus_exec================================END", "");
        }
    }
}


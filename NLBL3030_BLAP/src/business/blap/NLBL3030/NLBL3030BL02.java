/*
 * <Pre> Copyright (c) 2013 Canon USA Inc. All rights reserved. </Pre>
 */
package business.blap.NLBL3030;

import java.util.HashMap;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.db.MSG_CTRL_TPTMsg;
import business.db.MSG_CTRL_TPTMsgArray;

import com.canon.cusa.s21.framework.internal.codetable.S21CodeTableAccessor;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 * <pre>
 * Message Entry PopUo
 * 
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/16   Fujitsu         C.Naito         Create          (From NATL6050)
 * </pre>
 */
public class NLBL3030BL02 extends S21BusinessHandler implements NLBL3030BLConstant {

    /**
     * Main process
     */
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NLBL3030_INIT".equals(screenAplID)) {
                doProcess_NLBL3030_INIT(cMsg, sMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }

        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * Init Process
     * @param cMsg EZDCMsg
     * @param sMsg EZDSMsg
     */
    private void doProcess_NLBL3030_INIT(EZDCMsg cMsg, EZDSMsg sMsg) {
        NLBL3030CMsg bizMsg = (NLBL3030CMsg) cMsg;
        MSG_CTRL_TPTMsg msgCtrlTpTMsg = new MSG_CTRL_TPTMsg();

        if (EDIT_MODE.equals(bizMsg.xxOpsTp.getValue()) || INQUIRY_MODE.equals(bizMsg.xxOpsTp.getValue())) {
            MSG_CTRL_TPTMsg msgCtrlTpTMsgRes;
            msgCtrlTpTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
            msgCtrlTpTMsg.msgCtrlTpCd.setValue(bizMsg.A.no(0).msgCtrlTpCd.getValue());
            msgCtrlTpTMsgRes = (MSG_CTRL_TPTMsg) S21CodeTableAccessor.findByKey(msgCtrlTpTMsg);

            if (msgCtrlTpTMsgRes == null) {
                bizMsg.setMessageInfo("NAZM0033E", new String[] {"Message Type" });
                return;
            }

            bizMsg.xxDsMultMsgDplyTxt.setValue(bizMsg.A.no(0).xxDsMsgEntryTxt.getValue());
            bizMsg.msgCtrlTpDescTxt.setValue(msgCtrlTpTMsgRes.msgCtrlTpDescTxt.getValue());
            if (EDIT_MODE.equals(bizMsg.xxOpsTp.getValue())) {
                bizMsg.msgMaxLineNum.setValue(msgCtrlTpTMsgRes.msgMaxLineNum.getValue());
                bizMsg.msgMaxTxtNum.setValue(msgCtrlTpTMsgRes.msgMaxTxtNum.getValue());
            }
            return;

        } else {
            MSG_CTRL_TPTMsgArray msgCtrlTpTMsgAryRes;
            Map<String, MSG_CTRL_TPTMsg> msgCtrlTpTMsgMap = new HashMap<String, MSG_CTRL_TPTMsg>();
            msgCtrlTpTMsg.setSQLID("001");
            msgCtrlTpTMsg.setConditionValue("glblCmpyCd01", getGlobalCompanyCode());
            msgCtrlTpTMsgAryRes = (MSG_CTRL_TPTMsgArray) S21CodeTableAccessor.findByCondition(msgCtrlTpTMsg);
            for (int i = 0; i < msgCtrlTpTMsgAryRes.length(); i++) {
                msgCtrlTpTMsgMap.put(msgCtrlTpTMsgAryRes.no(i).msgCtrlTpCd.getValue(), msgCtrlTpTMsgAryRes.no(i));
            }

            StringBuffer msgTxtSb = new StringBuffer();
            MSG_CTRL_TPTMsg msgCtrlTpTMsgRes;
            for (int i = 0; i < bizMsg.A.getValidCount(); i++) {
                msgCtrlTpTMsgRes = msgCtrlTpTMsgMap.get(bizMsg.A.no(i).msgCtrlTpCd.getValue());

                if (msgCtrlTpTMsgRes != null) {
                    msgTxtSb = msgTxtSb.append('[').append(msgCtrlTpTMsgRes.msgCtrlTpDescTxt.getValue()).append("]\n");
                } else {
                    bizMsg.setMessageInfo("NAZM0033E", new String[] {"Message Type" });
                    msgTxtSb = msgTxtSb.append('[').append("No Title").append("]\n");
                }

                msgTxtSb = msgTxtSb.append(bizMsg.A.no(i).xxDsMsgEntryTxt.getValue()).append("\n\n");
            }

            String msg = msgTxtSb.toString();
            msg = msg.substring(0, Math.min(msg.length(), bizMsg.getAttr("xxDsMultMsgDplyTxt").getDigit()));
            bizMsg.xxDsMultMsgDplyTxt.setValue(msg);
        }

    }

}

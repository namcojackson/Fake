package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDPMsg;

import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;

/**
 * <pre>
 * NWXC220001ErrorInfoHelper
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWXC220001ErrorInfoHelper {

    public static List<NWXC220001ErrorInfo> getErrorInfoForEZDPMsg(EZDPMsg pMsg) {

        List<NWXC220001ErrorInfo> errList = new ArrayList<NWXC220001ErrorInfo>();

        if (S21ApiUtil.isXxMsgId(pMsg)) {
            S21ApiMessage msg;
            String msgId;
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(pMsg);
            for (int i = 0; i < msgList.size(); i++) {
                msg = msgList.get(i);
                msgId = msg.getXxMsgid();

                if (msgId.endsWith("E")) {
                    errList.add(new NWXC220001ErrorInfo(msgId, msg.getXxMsgPrmArray()));
                }
            }
        }

        return errList;
    }

}

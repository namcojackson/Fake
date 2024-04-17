package com.canon.cusa.s21.common.NWX.NWXC220001;

import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/** <pre>
 * ErrorInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWXC220001ErrorInfo {

    private String errMsgId;
    
    private String[] paramArray;

    private String errMsgText;

    public NWXC220001ErrorInfo(NWXC220001Constant.MSG_ID msgId, Object... param) {
        this(msgId.toString(), param);
    }

    public NWXC220001ErrorInfo(String errMsgId, Object... param) {
        this.paramArray = new String[param.length];

        for (int i = 0; i < paramArray.length; i++) {
            if (param[i] != null) {
                paramArray[i] = param[i].toString();
            } else {
                paramArray[i] = "";
            }
        }

        this.errMsgId = errMsgId;
        this.errMsgText = S21MessageFunc.clspGetMessage(errMsgId, paramArray);
    }

    public NWXC220001ErrorInfo(String errMsgId, String[] paramArray) {
        this.paramArray = paramArray;
        this.errMsgId = errMsgId;
        this.errMsgText = S21MessageFunc.clspGetMessage(errMsgId, paramArray);
    }

    public String getErrMsgId() {
        return errMsgId;
    }

    public String[] getParamArray() {
        return paramArray;
    }

    public String getErrMsgText() {
        return errMsgText;
    }

    @Override
    public String toString() {
        return errMsgText;
    }
}

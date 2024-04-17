package com.canon.cusa.s21.batch.NWA.NWAB265001;

import com.canon.cusa.s21.batch.NWA.NWAB265001.constant.NWAB265001constant;
import com.canon.cusa.s21.framework.log.S21MessageFunc;

/** <pre>
 * NWAB265001ErrorInfo
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 04/04/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWAB265001ErrorInfo {

    final private EdiOrdImptBaseBean ownerBean;

    private String errMsgId;

    private String[] paramArray;

    private String errMsgText;

    public NWAB265001ErrorInfo(EdiOrdImptBaseBean ownerBean, NWAB265001constant.MSG_ID msgId, Object... param) {
        this(ownerBean, msgId.toString(), param);
    }

    public NWAB265001ErrorInfo(EdiOrdImptBaseBean ownerBean, String errMsgId, Object... param) {
        this.ownerBean = ownerBean;
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

    public EdiOrdImptBaseBean getOwnerBean() {
        return ownerBean;
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

}

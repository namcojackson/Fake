package com.canon.cusa.s21.common.NWX.NWXC220001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.common.NWX.NWXC220001.constant.NWXC220001Constant;

/** <pre>
 * NWXC220001Result
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/24/2016   Fujitsu         M.Hara          Create          N/A
 * </pre>
 */
public class NWXC220001Result<T> {

    private final List<NWXC220001ErrorInfo> errInfoList;

    private T resultObject;

    public NWXC220001Result() {
        this.errInfoList = new ArrayList<NWXC220001ErrorInfo>();
    }

    public NWXC220001Result(T result) {
        this();
        this.resultObject = result;
    }

    public NWXC220001Result(T result, NWXC220001Constant.MSG_ID msgId, Object... param) {
        this(result, msgId.toString(), param);
    }

    public NWXC220001Result(T result, String errMsgId, Object... param) {
        this();
        this.resultObject = result;
        this.addErrorInfo(errMsgId, param);
    }

    public boolean hasError() {
        return (this.errInfoList.size() > 0);
    }

    public boolean hasResultObject() {
        return (this.resultObject != null);
    }

    public NWXC220001ErrorInfo addErrorInfo(NWXC220001Constant.MSG_ID msgId, Object... param) {
        return addErrorInfo(msgId.toString(), param);
    }

    public NWXC220001ErrorInfo addErrorInfo(String errMsgId, Object... param) {
        NWXC220001ErrorInfo errorInfo = new NWXC220001ErrorInfo (errMsgId, param);
        this.errInfoList.add(errorInfo);

        return errorInfo;
    }

    public void addErrorInfo(NWXC220001ErrorInfo errorInfo) {
        this.errInfoList.add(errorInfo);
    }

    public void addErrorInfo(List<NWXC220001ErrorInfo> errorInfoList) {
        this.errInfoList.addAll(errorInfoList);
    }

    public List<NWXC220001ErrorInfo> getErrInfoList() {
        return errInfoList;
    }

    public T getResultObject() {
        return resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("[Error Info]\n");
        for (NWXC220001ErrorInfo errorInfo : this.errInfoList) {
            sb.append(errorInfo.toString()).append("\n");
        }

        sb.append("[Result Info]\n");
        if (resultObject != null) {
            sb.append(resultObject.toString()).append("\n");
        }

        return sb.toString();
    }

}

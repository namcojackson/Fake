package com.canon.cusa.s21.common.NWX.NWXC001001;

import java.io.Serializable;

public class NWXC001001MailSubstituteString implements Serializable {

    private static final long serialVersionUID = 1L;

    private String      sbstKey;
    private String      sbstStr;
    
    public String getSbstKey() {
        return sbstKey;
    }
    public void setSbstKey(String sbstKey) {
        this.sbstKey = sbstKey;
    }
    public String getSbstStr() {
        return sbstStr;
    }
    public void setSbstStr(String sbstStr) {
        this.sbstStr = sbstStr;
    }
    
}

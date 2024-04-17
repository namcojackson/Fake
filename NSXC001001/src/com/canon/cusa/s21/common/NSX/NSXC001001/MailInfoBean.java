/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.common.NSX.NSXC001001;

import java.util.List;

/**
 *<pre>
 * MailInfoBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/12/19   Fujitsu         T.Yoshida       Create          N/A
 *</pre>
 */
public class MailInfoBean {

    /** Global Company Code */
    private String glblCmpyCd;

    /** Interface ID */
    private String interfaceId;

    /** Interface Name */
    private String interfaceNm;

    /** Message ID List */
    private List<String> msgIdList;

    /** Technician Code */
    private String techCd;

    /**
     * Get Global Company Code
     * @return Global Company Code
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * Set Global Company Code
     * @param glblCmpyCd Global Company Code
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * Get Interface ID
     * @return Interface ID
     */
    public String getInterfaceId() {
        return interfaceId;
    }

    /**
     * Set Interface ID
     * @param interfaceId Interface ID
     */
    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }

    /**
     * Get Interface Name
     * @return Interface Name
     */
    public String getInterfaceNm() {
        return interfaceNm;
    }

    /**
     * Set Interface Name
     * @param interfaceNm Interface Name
     */
    public void setInterfaceNm(String interfaceNm) {
        this.interfaceNm = interfaceNm;
    }

    /**
     * Get Message ID List
     * @return Message ID List
     */
    public List<String> getMsgIdList() {
        return msgIdList;
    }

    /**
     * Set Message ID List
     * @param msgIdList Message ID List
     */
    public void setMsgIdList(List<String> msgIdList) {
        this.msgIdList = msgIdList;
    }

    /**
     * Get Technician Code
     * @return Technician Code
     */
    public String getTechCd() {
        return techCd;
    }

    /**
     * Set Technician Code
     * @param techCd Technician Code
     */
    public void setTechCd(String techCd) {
        this.techCd = techCd;
    }
}

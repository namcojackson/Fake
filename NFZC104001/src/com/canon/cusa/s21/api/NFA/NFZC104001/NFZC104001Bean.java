/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.api.NFA.NFZC104001;

/**
 * <pre>
 * Journal Entry Creation API
 * NFZC104001Bean.
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/08   Fujitsu         S.Fujita        Create          N/A.
 * </pre>
 */
public class NFZC104001Bean {

    /** System Source Name */
    private String sysSrcNm;

    /** Currency Code */
    private String ccyCd;

    /**
     * @return sysSrcNm
     */
    public String getSysSrcNm() {
        return sysSrcNm;
    }

    /**
     * @return ccyCd
     */
    public String getCcyCd() {
        return ccyCd;
    }

    /**
     * @param sysSrcNm String
     */
    public void setSysSrcNm(String sysSrcNm) {
        this.sysSrcNm = sysSrcNm;
    }

    /**
     * @param ccyCd String
     */
    public void setCcyCd(String ccyCd) {
        this.ccyCd = ccyCd;
    }
}

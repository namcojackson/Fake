package business.parts;

public class RtlWhAcct {
	 /** WH Code */
    private String rtlWhCd;
    
    /** Account Type Code */
    private String rtlWhAcctTpCd;
    
    /** COA Branch Code */
    private String coaBrCd;
    
    
    /** COA Account Code */
    private String coaAcctCd;
    
    /** Account Type Name */
    private String rtlWhAcctTpNm;

    /**
     * setRtlWjCd
     * @param rtlWhCd String
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }

    /**
     * getRtlWhCd
     * @return String
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }
    
    /**
     * setRtlWhAcctTpCd
     * @param rtlWhAcctTpCd String
     */
    public void setRtlWhAcctTpCd(String rtlWhAcctTpCd) {
        this.rtlWhAcctTpCd = rtlWhAcctTpCd;
    }

    /**
     * getRtlWhAcctTpCd
     * @return String
     */
    public String getRtlWhAcctTpCd() {
        return rtlWhAcctTpCd;
    }
    
    /**
     * setCoaBrCd
     * @param coaBrCd String
     */
    public void setCoaBrCd(String coaBrCd) {
        this.coaBrCd = coaBrCd;
    }

    /**
     * getCoaBrCd
     * @return String
     */
    public String getCoaBrCd() {
        return coaBrCd;
    }
    
    /**
     * setCoaAcctCd
     * @param coaAcctCd String
     */
    public void setCoaAcctCd(String coaAcctCd) {
        this.coaAcctCd = coaAcctCd;
    }

    /**
     * getCoaAcctCd
     * @return String
     */
    public String getCoaAcctCd() {
        return coaAcctCd;
    }
    
    /**
     * setRtlWhAcctTpNm
     * @param rtlWhAcctTpNm String
     */
    public void setRtlWhAcctTpNm(String rtlWhAcctTpNm) {
        this.rtlWhAcctTpNm = rtlWhAcctTpNm;
    }

    /**
     * getRtlWhAcctTpNm
     * @return String
     */
    public String getRtlWhAcctTpNm() {
        return rtlWhAcctTpNm;
    }
}

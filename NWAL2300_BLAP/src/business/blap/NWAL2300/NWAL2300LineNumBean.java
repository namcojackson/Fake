/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.blap.NWAL2300;

/**
 *<pre>
 * NWAL2300LineNumBean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/06/02   Fujitsu         H.Ikeda         Create          N/A
 *</pre>
 */
public class NWAL2300LineNumBean {

    /** Default serial Version */
    private static final long serialVersionUID = 1L;

    /** cpoDtlLineNum(Credit) */
    private String cpoDtlLineNumCre = "";
    /** cpoDtlLineSubNum(Credit) */
    private String cpoDtlLineSubNumCre = "";
    /** cpoDtlLineNum(Rebil) */
    private String cpoDtlLineNumRbl = "";
    /** cpoDtlLineSubNum(Rebil) */
    private String cpoDtlLineSubNumRbl = "";
    /** dsCpoLineNum(Credit) */
    private int dsCpoLineNumCre = 0;
    /** dsCpoLineNum(Credit) */
    private int dsCpoLineSubNumCre = 0;
    /** dsCpoLineNum(Rebil) */
    private int dsCpoLineNumRbl = 0;
    /** dsCpoLineNum(Rebil) */
    private int dsCpoLineSubNumRbl = 0;
    /** cpoDtlLineNum(Credit) */
    private String cpoDtlLineNumRma = "";
    /** cpoDtlLineSubNum(Credit) */
    private String cpoDtlLineSubNumRma = "";
    /** dsCpoLineNum(Rebil) */
    private int dsCpoLineNumRma = 0;
    /** dsCpoLineNum(Rebil) */
    private int dsCpoLineSubNumRma = 0;

    /**
     * getCpoDtlLineNumCre
     * 
     * @return String
     */
    public String getCpoDtlLineNumCre() {
        return cpoDtlLineNumCre;
    }

    /**
     * setCpoDtlLineNumCre
     * 
     * @param cpoDtlLineNumCre String
     */
    public void setCpoDtlLineNumCre(String cpoDtlLineNumCre) {
        this.cpoDtlLineNumCre = cpoDtlLineNumCre;
    }

    /**
     * getCpoDtlLineSubNumCre
     * 
     * @return String
     */
    public String getCpoDtlLineSubNumCre() {
        return cpoDtlLineSubNumCre;
    }

    /**
     * setCpoDtlLineSubNumCre
     * 
     * @param cpoDtlLineSubNumCre String
     */
    public void setCpoDtlLineSubNumCre(String cpoDtlLineSubNumCre) {
        this.cpoDtlLineSubNumCre = cpoDtlLineSubNumCre;
    }

    /**
     * getCpoDtlLineSubNumCre
     * 
     * @return String
     */
    public String getCpoDtlLineNumRbl() {
        return cpoDtlLineNumRbl;
    }

    /**
     * setCpoDtlLineNumRbl
     * 
     * @param cpoDtlLineNumRbl String
     */
    public void setCpoDtlLineNumRbl(String cpoDtlLineNumRbl) {
        this.cpoDtlLineNumRbl = cpoDtlLineNumRbl;
    }

    /**
     * getCpoDtlLineSubNumRbl
     * 
     * @return String
     */
    public String getCpoDtlLineSubNumRbl() {
        return cpoDtlLineSubNumRbl;
    }

    /**
     * setCpoDtlLineNumRbl
     * 
     * @param cpoDtlLineSubNumRbl String
     */
    public void setCpoDtlLineSubNumRbl(String cpoDtlLineSubNumRbl) {
        this.cpoDtlLineSubNumRbl = cpoDtlLineSubNumRbl;
    }

    /**
     * getDsCpoLineNumCre
     * 
     * @return int
     */
    public int getDsCpoLineNumCre() {
        return dsCpoLineNumCre;
    }

    /**
     * setDsCpoLineNumCre
     * 
     * @param dsCpoLineNumCre int
     */
    public void setDsCpoLineNumCre(int dsCpoLineNumCre) {
        this.dsCpoLineNumCre = dsCpoLineNumCre;
    }

    /**
     * getDsCpoLineSubNumCre
     * 
     * @return int
     */
    public int getDsCpoLineSubNumCre() {
        return dsCpoLineSubNumCre;
    }

    /**
     * setDsCpoLineSubNumCre
     * 
     * @param dsCpoLineSubNumCre int
     */
    public void setDsCpoLineSubNumCre(int dsCpoLineSubNumCre) {
        this.dsCpoLineSubNumCre = dsCpoLineSubNumCre;
    }

    /**
     * getDsCpoLineNumRbl
     * 
     * @return int
     */
    public int getDsCpoLineNumRbl() {
        return dsCpoLineNumRbl;
    }

    /**
     * setDsCpoLineSubNumCre
     * 
     * @param dsCpoLineNumRbl int
     */
    public void setDsCpoLineNumRbl(int dsCpoLineNumRbl) {
        this.dsCpoLineNumRbl = dsCpoLineNumRbl;
    }

    /**
     * getDsCpoLineNumRbl
     * 
     * @return int
     */
    public int getDsCpoLineSubNumRbl() {
        return dsCpoLineSubNumRbl;
    }

    /**
     * setDsCpoLineSubNumRbl
     * 
     * @param dsCpoLineSubNumRbl int
     */
    public void setDsCpoLineSubNumRbl(int dsCpoLineSubNumRbl) {
        this.dsCpoLineSubNumRbl = dsCpoLineSubNumRbl;
    }

    /**
     * getCpoDtlLineNumRma
     * 
     * @return String
     */
    public String getCpoDtlLineNumRma() {
        return cpoDtlLineNumRma;
    }

    /**
     * setCpoDtlLineNumRma
     * 
     * @param cpoDtlLineNumRma String
     */
    public void setCpoDtlLineNumRma(String cpoDtlLineNumRma) {
        this.cpoDtlLineNumRma = cpoDtlLineNumRma;
    }

    /**
     * getCpoDtlLineSubNumRma
     * 
     * @return String
     */
    public String getCpoDtlLineSubNumRma() {
        return cpoDtlLineSubNumRma;
    }

    /**
     * setCpoDtlLineSubNumRma
     * 
     * @param cpoDtlLineSubNumRma String
     */
    public void setCpoDtlLineSubNumRma(String cpoDtlLineSubNumRma) {
        this.cpoDtlLineSubNumRma = cpoDtlLineSubNumRma;
    }

    /**
     * getDsCpoLineNumRma
     * 
     * @return int
     */
    public int getDsCpoLineNumRma() {
        return dsCpoLineNumRma;
    }

    /**
     * setDsCpoLineNumRma
     * 
     * @param dsCpoLineNumRma int
     */
    public void setDsCpoLineNumRma(int dsCpoLineNumRma) {
        this.dsCpoLineNumRma = dsCpoLineNumRma;
    }

    /**
     * getDsCpoLineSubNumRma
     * 
     * @return int
     */
    public int getDsCpoLineSubNumRma() {
        return dsCpoLineSubNumRma;
    }

    /**
     * setDsCpoLineSubNumRma
     * 
     * @param dsCpoLineSubNumRma int
     */
    public void setDsCpoLineSubNumRma(int dsCpoLineSubNumRma) {
        this.dsCpoLineSubNumRma = dsCpoLineSubNumRma;
    }

}

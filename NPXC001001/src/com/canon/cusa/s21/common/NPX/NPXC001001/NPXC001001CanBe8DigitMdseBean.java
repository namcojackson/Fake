/*
 * <Pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</Pre>
 */
package com.canon.cusa.s21.common.NPX.NPXC001001;

import java.util.List;


/**
 * <pre>
 * CanBe8DigitMdse
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/03/2014   Hitachi         T.Kawazu        Create          CSA-339
 *</pre>
 */
public class NPXC001001CanBe8DigitMdseBean {
    /** Global Company Code */
    private String glblCmpyCd;

    /** Merchandise Code */
    private String mdseCd;

    /** Mode */
    private String mode;

    /** 8DigitMdse Flg */
    private boolean canBe8DigitMdseFlg;

    /** mdseCdList */
    private List<String> mdseCdList;

    /** mdseCdList */
    private List<String> errList;

    /**
     * @return String
     */
    public String getMode() {
        return mode;
    }

    /**
     * 
     * @param mode String
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

    /**
     * @param glblCmpyCd String
     */
    public void setGlblCmpyCd(String glblCmpyCd) {
        this.glblCmpyCd = glblCmpyCd;
    }

    /**
     * @return glblCmpyCd
     */
    public String getGlblCmpyCd() {
        return glblCmpyCd;
    }

    /**
     * @param mdseCd String
     */
    public void setMdseCd(String mdseCd) {
        this.mdseCd = mdseCd;
    }

    /**
     * @return mdseCd
     */
    public String getMdseCd() {
        return mdseCd;
    }

    /**
     * @param value boolean
     */
    public void setCanBe8DigitMdseFlg(boolean value) {
        canBe8DigitMdseFlg = value;
    }

    /**
     * @return canBe8DigitMdseFlg
     */
    public boolean isCanBe8DigitMdseFlg() {
        return canBe8DigitMdseFlg;
    }

    /**
     * @param mdseCdList List<String>
     */
    public void setMdseCdList(List<String> mdseCdList) {
        this.mdseCdList = mdseCdList;
    }

    /**
     * @return mdseCdList
     */
    public List<String> getMdseCdList() {
        return mdseCdList;
    }

    /**
     * @param errList List<String>
     */
    public void setErrList(List<String> errList) {
        this.errList = errList;
    }

    /**
     * @return errList
     */
    public List<String> getErrList() {
        return errList;
    }

}

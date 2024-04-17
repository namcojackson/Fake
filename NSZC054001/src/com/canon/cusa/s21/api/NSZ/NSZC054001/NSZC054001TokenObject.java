/* <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre> */
package com.canon.cusa.s21.api.NSZ.NSZC054001;

import java.util.ArrayList;
import java.util.List;

import com.canon.cusa.s21.framework.nwf.util.token.S21NwfUtilTokenObj;

/**
 * <pre>
 * NSZC054001TokenObject
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/30/2016   Hitachi         T.Aoyagi        Create          QC#1467
 *</pre>
 */
public class NSZC054001TokenObject extends S21NwfUtilTokenObj {

    /** serialVersionUID */
    private static final long serialVersionUID = 1L;
    /** lineData */
    List<NSZC054001TokenObjectLine> lineData = new ArrayList<NSZC054001TokenObjectLine>();

    /**
     * @param data S21NwfUtilTokenObjExtLine
     */
    public void addLineData(NSZC054001TokenObjectLine data) {
        lineData.add(data);
    }

    /**
     * @param index int
     * @return S21NwfUtilTokenObjExtLine
     */
    public NSZC054001TokenObjectLine getLineData(int index) {
        NSZC054001TokenObjectLine ret = null;

        if (lineData.size() > index) {
            ret = lineData.get(index);
        }

        return ret;
    }

    /** hdrAttrb1 */
    private String hdrAttrb1;

    /** hdrAttrb2 */
    private String hdrAttrb2;

    /** hdrAttrb3 */
    private String hdrAttrb3;

    /** hdrAttrb4 */
    private String hdrAttrb4;

    /** hdrAttrb5 */
    private String hdrAttrb5;

    /** hdrAttrb6 */
    private String hdrAttrb6;

    /** hdrAttrb7 */
    private String hdrAttrb7;

    /** hdrAttrb8 */
    private String hdrAttrb8;

    /** hdrAttrb9 */
    private String hdrAttrb9;

    /** hdrAttrb10 */
    private String hdrAttrb10;

    /** hdrAttrb11 */
    private String hdrAttrb11;

    /** hdrAttrb12 */
    private String hdrAttrb12;

    /** hdrAttrb13 */
    private String hdrAttrb13;

    /** hdrAttrb14 */
    private String hdrAttrb14;

    /** hdrAttrb15 */
    private String hdrAttrb15;

    /** hdrAttrb16 */
    private String hdrAttrb16;

    /** hdrAttrb17 */
    private String hdrAttrb17;

    /** hdrAttrb18 */
    private String hdrAttrb18;

    /** hdrAttrb19 */
    private String hdrAttrb19;

    /** hdrAttrb20 */
    private String hdrAttrb20;

    /** hdrAttrb21 */
    private String hdrAttrb21;

    /** hdrAttrb22 */
    private String hdrAttrb22;

    /** hdrAttrb23 */
    private String hdrAttrb23;

    /** hdrAttrb24 */
    private String hdrAttrb24;

    /** hdrAttrb25 */
    private String hdrAttrb25;

    /** hdrAttrb26 */
    private String hdrAttrb26;

    /** hdrAttrb27 */
    private String hdrAttrb27;

    /** hdrAttrb28 */
    private String hdrAttrb28;

    /** hdrAttrb29 */
    private String hdrAttrb29;

    /** hdrAttrb30 */
    private String hdrAttrb30;

    /** hdrAttrb31 */
    private String hdrAttrb31;

    /** hdrAttrb32 */
    private String hdrAttrb32;

    /** hdrAttrb33 */
    private String hdrAttrb33;

    /** hdrAttrb34 */
    private String hdrAttrb34;

    /**
     * @return lineData
     */
    public List<NSZC054001TokenObjectLine> getLineData() {
        return lineData;
    }

    /**
     * @return hdrAttrb1
     */
    public String getHdrAttrb1() {
        return hdrAttrb1;
    }

    /**
     * @return hdrAttrb2
     */
    public String getHdrAttrb2() {
        return hdrAttrb2;
    }

    /**
     * @return hdrAttrb3
     */
    public String getHdrAttrb3() {
        return hdrAttrb3;
    }

    /**
     * @return hdrAttrb4
     */
    public String getHdrAttrb4() {
        return hdrAttrb4;
    }

    /**
     * @return hdrAttrb5
     */
    public String getHdrAttrb5() {
        return hdrAttrb5;
    }

    /**
     * @return hdrAttrb6
     */
    public String getHdrAttrb6() {
        return hdrAttrb6;
    }

    /**
     * @return hdrAttrb7
     */
    public String getHdrAttrb7() {
        return hdrAttrb7;
    }

    /**
     * @return hdrAttrb8
     */
    public String getHdrAttrb8() {
        return hdrAttrb8;
    }

    /**
     * @return hdrAttrb9
     */
    public String getHdrAttrb9() {
        return hdrAttrb9;
    }

    /**
     * @return hdrAttrb10
     */
    public String getHdrAttrb10() {
        return hdrAttrb10;
    }

    /**
     * @return hdrAttrb11
     */
    public String getHdrAttrb11() {
        return hdrAttrb11;
    }

    /**
     * @return hdrAttrb12
     */
    public String getHdrAttrb12() {
        return hdrAttrb12;
    }

    /**
     * @return hdrAttrb13
     */
    public String getHdrAttrb13() {
        return hdrAttrb13;
    }

    /**
     * @return hdrAttrb14
     */
    public String getHdrAttrb14() {
        return hdrAttrb14;
    }

    /**
     * @return hdrAttrb15
     */
    public String getHdrAttrb15() {
        return hdrAttrb15;
    }

    /**
     * @return hdrAttrb16
     */
    public String getHdrAttrb16() {
        return hdrAttrb16;
    }

    /**
     * @return hdrAttrb17
     */
    public String getHdrAttrb17() {
        return hdrAttrb17;
    }

    /**
     * @return hdrAttrb18
     */
    public String getHdrAttrb18() {
        return hdrAttrb18;
    }

    /**
     * @return hdrAttrb19
     */
    public String getHdrAttrb19() {
        return hdrAttrb19;
    }

    /**
     * @return hdrAttrb20
     */
    public String getHdrAttrb20() {
        return hdrAttrb20;
    }

    /**
     * @return hdrAttrb21
     */
    public String getHdrAttrb21() {
        return hdrAttrb21;
    }

    /**
     * @return hdrAttrb22
     */
    public String getHdrAttrb22() {
        return hdrAttrb22;
    }

    /**
     * @return hdrAttrb23
     */
    public String getHdrAttrb23() {
        return hdrAttrb23;
    }

    /**
     * @return hdrAttrb24
     */
    public String getHdrAttrb24() {
        return hdrAttrb24;
    }

    /**
     * @return hdrAttrb25
     */
    public String getHdrAttrb25() {
        return hdrAttrb25;
    }

    /**
     * @return hdrAttrb26
     */
    public String getHdrAttrb26() {
        return hdrAttrb26;
    }

    /**
     * @return hdrAttrb27
     */
    public String getHdrAttrb27() {
        return hdrAttrb27;
    }

    /**
     * @return hdrAttrb28
     */
    public String getHdrAttrb28() {
        return hdrAttrb28;
    }

    /**
     * @return hdrAttrb29
     */
    public String getHdrAttrb29() {
        return hdrAttrb29;
    }

    /**
     * @return hdrAttrb30
     */
    public String getHdrAttrb30() {
        return hdrAttrb30;
    }

    /**
     * @return hdrAttrb31
     */
    public String getHdrAttrb31() {
        return hdrAttrb31;
    }

    /**
     * @return hdrAttrb32
     */
    public String getHdrAttrb32() {
        return hdrAttrb32;
    }

    /**
     * @return hdrAttrb33
     */
    public String getHdrAttrb33() {
        return hdrAttrb33;
    }

    /**
     * @return hdrAttrb34
     */
    public String getHdrAttrb34() {
        return hdrAttrb34;
    }

    /**
     * @param lineData set lineData
     */
    public void setLineData(List<NSZC054001TokenObjectLine> lineData) {
        this.lineData = lineData;
    }

    /**
     * @param hdrAttrb1 set hdrAttrb1
     */
    public void setHdrAttrb1(String hdrAttrb1) {
        this.hdrAttrb1 = hdrAttrb1;
    }

    /**
     * @param hdrAttrb2 set hdrAttrb2
     */
    public void setHdrAttrb2(String hdrAttrb2) {
        this.hdrAttrb2 = hdrAttrb2;
    }

    /**
     * @param hdrAttrb3 set hdrAttrb3
     */
    public void setHdrAttrb3(String hdrAttrb3) {
        this.hdrAttrb3 = hdrAttrb3;
    }

    /**
     * @param hdrAttrb4 set hdrAttrb4
     */
    public void setHdrAttrb4(String hdrAttrb4) {
        this.hdrAttrb4 = hdrAttrb4;
    }

    /**
     * @param hdrAttrb5 set hdrAttrb5
     */
    public void setHdrAttrb5(String hdrAttrb5) {
        this.hdrAttrb5 = hdrAttrb5;
    }

    /**
     * @param hdrAttrb6 set hdrAttrb6
     */
    public void setHdrAttrb6(String hdrAttrb6) {
        this.hdrAttrb6 = hdrAttrb6;
    }

    /**
     * @param hdrAttrb7 set hdrAttrb7
     */
    public void setHdrAttrb7(String hdrAttrb7) {
        this.hdrAttrb7 = hdrAttrb7;
    }

    /**
     * @param hdrAttrb8 set hdrAttrb8
     */
    public void setHdrAttrb8(String hdrAttrb8) {
        this.hdrAttrb8 = hdrAttrb8;
    }

    /**
     * @param hdrAttrb9 set hdrAttrb9
     */
    public void setHdrAttrb9(String hdrAttrb9) {
        this.hdrAttrb9 = hdrAttrb9;
    }

    /**
     * @param hdrAttrb10 set hdrAttrb10
     */
    public void setHdrAttrb10(String hdrAttrb10) {
        this.hdrAttrb10 = hdrAttrb10;
    }

    /**
     * @param hdrAttrb11 set hdrAttrb11
     */
    public void setHdrAttrb11(String hdrAttrb11) {
        this.hdrAttrb11 = hdrAttrb11;
    }

    /**
     * @param hdrAttrb12 set hdrAttrb12
     */
    public void setHdrAttrb12(String hdrAttrb12) {
        this.hdrAttrb12 = hdrAttrb12;
    }

    /**
     * @param hdrAttrb13 set hdrAttrb13
     */
    public void setHdrAttrb13(String hdrAttrb13) {
        this.hdrAttrb13 = hdrAttrb13;
    }

    /**
     * @param hdrAttrb14 set hdrAttrb14
     */
    public void setHdrAttrb14(String hdrAttrb14) {
        this.hdrAttrb14 = hdrAttrb14;
    }

    /**
     * @param hdrAttrb15 set hdrAttrb15
     */
    public void setHdrAttrb15(String hdrAttrb15) {
        this.hdrAttrb15 = hdrAttrb15;
    }

    /**
     * @param hdrAttrb16 set hdrAttrb16
     */
    public void setHdrAttrb16(String hdrAttrb16) {
        this.hdrAttrb16 = hdrAttrb16;
    }

    /**
     * @param hdrAttrb17 set hdrAttrb17
     */
    public void setHdrAttrb17(String hdrAttrb17) {
        this.hdrAttrb17 = hdrAttrb17;
    }

    /**
     * @param hdrAttrb18 set hdrAttrb18
     */
    public void setHdrAttrb18(String hdrAttrb18) {
        this.hdrAttrb18 = hdrAttrb18;
    }

    /**
     * @param hdrAttrb19 set hdrAttrb19
     */
    public void setHdrAttrb19(String hdrAttrb19) {
        this.hdrAttrb19 = hdrAttrb19;
    }

    /**
     * @param hdrAttrb20 set hdrAttrb20
     */
    public void setHdrAttrb20(String hdrAttrb20) {
        this.hdrAttrb20 = hdrAttrb20;
    }

    /**
     * @param hdrAttrb21 set hdrAttrb21
     */
    public void setHdrAttrb21(String hdrAttrb21) {
        this.hdrAttrb21 = hdrAttrb21;
    }

    /**
     * @param hdrAttrb22 set hdrAttrb22
     */
    public void setHdrAttrb22(String hdrAttrb22) {
        this.hdrAttrb22 = hdrAttrb22;
    }

    /**
     * @param hdrAttrb23 set hdrAttrb23
     */
    public void setHdrAttrb23(String hdrAttrb23) {
        this.hdrAttrb23 = hdrAttrb23;
    }

    /**
     * @param hdrAttrb24 set hdrAttrb24
     */
    public void setHdrAttrb24(String hdrAttrb24) {
        this.hdrAttrb24 = hdrAttrb24;
    }

    /**
     * @param hdrAttrb25 set hdrAttrb25
     */
    public void setHdrAttrb25(String hdrAttrb25) {
        this.hdrAttrb25 = hdrAttrb25;
    }

    /**
     * @param hdrAttrb26 set hdrAttrb26
     */
    public void setHdrAttrb26(String hdrAttrb26) {
        this.hdrAttrb26 = hdrAttrb26;
    }

    /**
     * @param hdrAttrb27 set hdrAttrb27
     */
    public void setHdrAttrb27(String hdrAttrb27) {
        this.hdrAttrb27 = hdrAttrb27;
    }

    /**
     * @param hdrAttrb28 set hdrAttrb28
     */
    public void setHdrAttrb28(String hdrAttrb28) {
        this.hdrAttrb28 = hdrAttrb28;
    }

    /**
     * @param hdrAttrb29 set hdrAttrb29
     */
    public void setHdrAttrb29(String hdrAttrb29) {
        this.hdrAttrb29 = hdrAttrb29;
    }

    /**
     * @param hdrAttrb30 set hdrAttrb30
     */
    public void setHdrAttrb30(String hdrAttrb30) {
        this.hdrAttrb30 = hdrAttrb30;
    }

    /**
     * @param hdrAttrb31 set hdrAttrb31
     */
    public void setHdrAttrb31(String hdrAttrb31) {
        this.hdrAttrb31 = hdrAttrb31;
    }

    /**
     * @param hdrAttrb32 set hdrAttrb32
     */
    public void setHdrAttrb32(String hdrAttrb32) {
        this.hdrAttrb32 = hdrAttrb32;
    }

    /**
     * @param hdrAttrb33 set hdrAttrb33
     */
    public void setHdrAttrb33(String hdrAttrb33) {
        this.hdrAttrb33 = hdrAttrb33;
    }

    /**
     * @param hdrAttrb34 set hdrAttrb34
     */
    public void setHdrAttrb34(String hdrAttrb34) {
        this.hdrAttrb34 = hdrAttrb34;
    }
}

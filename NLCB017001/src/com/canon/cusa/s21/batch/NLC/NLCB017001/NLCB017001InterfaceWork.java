/**
 * <pre>Copyright (c) 2009 Canon USA Inc. All rights reserved.</pre>
 */
package com.canon.cusa.s21.batch.NLC.NLCB017001;

import java.math.BigDecimal;

import com.canon.cusa.s21.framework.common.S21StringUtil;

/**
 * <pre>
 * SCM/DB Daily Inventory Result to Canon,Inc. (Interface Work)
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/29/2010   Fujitsu         M.Yamada        Create          N/A
 * 09/27/2012   CSAI            M.Takahashi     Update          S21_PRD#410759
 * 09/19/2013   Fujitsu         S.Yoshida       Update          CMEX localize
 * </pre>
 */
public class NLCB017001InterfaceWork {

    /**  */
    private String amendCd;
    /**  */
    private String glblCmpy3Cd;
    /**  */
    private String mdse12Cd;
    /**  */
    private String localMdseCd;
    /**  */
    private String intfcWhCd;
    /**  */
    private String adminYrMth;
    /**  */
    private String adminDt;

    /** QTY-INV-INTRAN-INC CA20 */
    private BigDecimal scmDbQty01;

    /** QTY-INV-INTRAN-ETA */
    private BigDecimal scmDbQty02;

    /** QTY-INV-INTRAN-WAIT */
    private BigDecimal scmDbQty03;

    /** QTY-INV-INTRAN-OTHR CA22 */
    private BigDecimal scmDbQty04;

    /** QTY-INV-GOOD AA10 */
    private BigDecimal scmDbQty05;

    /** QTY-INV-SEMI-GOOD BA10 */
    private BigDecimal scmDbQty06;

    /** QTY-INV-DEFECTIVE BB10 */
    private BigDecimal scmDbQty07;

    /** QTY-INV-REFURBISH BB20 */
    private BigDecimal scmDbQty08;

    /** QTY-INV-REWORK */
    private BigDecimal scmDbQty09;

    /** QTY-INV-ASSEMBLE BA20 */
    private BigDecimal scmDbQty10;

    /** QTY-INV-TRIAL BA21 */
    private BigDecimal scmDbQty11;

    /** QTY-INV-RENTAL */
    private BigDecimal scmDbQty12;

    /** QTY-INV-INSTALL BA24 */
    private BigDecimal scmDbQty13;

    /** QTY-INV-SHED BA25 */
    private BigDecimal scmDbQty14;

    /** QTY-INV-FIXED-RTL */
    private BigDecimal scmDbQty15;

    /** QTY-INV-FIXED-NOR ZA21 */
    private BigDecimal scmDbQty16;

    /** QTY-INV-FIXED-OTHR */
    private BigDecimal scmDbQty17;

//START DEL S.Yoshida [CMEX localize]
//    /**  */
//    private String defIntfcWhCd;
//
//    /**  */
//    private BigDecimal scmDbQty18;
//
//    /**  */
//    private BigDecimal scmDbQty19;
//END   DEL S.Yoshida [CMEX localize]

    /**  */
    private String fill25Txt;

    /**
     * constructor
     */
    public NLCB017001InterfaceWork() {

        amendCd = "";
        glblCmpy3Cd = "";
        mdse12Cd = "";
        localMdseCd = "";
        intfcWhCd = "";
        adminYrMth = "";
        adminDt = "";
        scmDbQty01 = BigDecimal.ZERO;
        scmDbQty02 = BigDecimal.ZERO;
        scmDbQty03 = BigDecimal.ZERO;
        scmDbQty04 = BigDecimal.ZERO;
        scmDbQty05 = BigDecimal.ZERO;
        scmDbQty06 = BigDecimal.ZERO;
        scmDbQty07 = BigDecimal.ZERO;
        scmDbQty08 = BigDecimal.ZERO;
        scmDbQty09 = BigDecimal.ZERO;
        scmDbQty10 = BigDecimal.ZERO;
        scmDbQty11 = BigDecimal.ZERO;
        scmDbQty12 = BigDecimal.ZERO;
        scmDbQty13 = BigDecimal.ZERO;
        scmDbQty14 = BigDecimal.ZERO;
        scmDbQty15 = BigDecimal.ZERO;
        scmDbQty16 = BigDecimal.ZERO;
        scmDbQty17 = BigDecimal.ZERO;
//START DEL S.Yoshida [CMEX localize]
//        defIntfcWhCd = "";
//        scmDbQty18 = BigDecimal.ZERO;
//        scmDbQty19 = BigDecimal.ZERO;
//END   DEL S.Yoshida [CMEX localize]
        fill25Txt = "";
    }

    /**
     * NLCB017001InterfaceWork
     * @param ifWrk NLCB017001InterfaceWork
     */
    public NLCB017001InterfaceWork(NLCB017001InterfaceWork ifWrk) {

        amendCd = ifWrk.amendCd;
        glblCmpy3Cd = ifWrk.glblCmpy3Cd;
        mdse12Cd = ifWrk.mdse12Cd;
        localMdseCd = ifWrk.localMdseCd;
        intfcWhCd = ifWrk.intfcWhCd;
        adminYrMth = ifWrk.adminYrMth;
        adminDt = ifWrk.adminDt;
        scmDbQty01 = ifWrk.scmDbQty01;
        scmDbQty02 = ifWrk.scmDbQty02;
        scmDbQty03 = ifWrk.scmDbQty03;
        scmDbQty04 = ifWrk.scmDbQty04;
        scmDbQty05 = ifWrk.scmDbQty05;
        scmDbQty06 = ifWrk.scmDbQty06;
        scmDbQty07 = ifWrk.scmDbQty07;
        scmDbQty08 = ifWrk.scmDbQty08;
        scmDbQty09 = ifWrk.scmDbQty09;
        scmDbQty10 = ifWrk.scmDbQty10;
        scmDbQty11 = ifWrk.scmDbQty11;
        scmDbQty12 = ifWrk.scmDbQty12;
        scmDbQty13 = ifWrk.scmDbQty13;
        scmDbQty14 = ifWrk.scmDbQty14;
        scmDbQty15 = ifWrk.scmDbQty15;
        scmDbQty16 = ifWrk.scmDbQty16;
        scmDbQty17 = ifWrk.scmDbQty17;
//START DEL S.Yoshida [DCMEX localize]
//        defIntfcWhCd = ifWrk.defIntfcWhCd;
//        scmDbQty18 = ifWrk.scmDbQty18;
//        scmDbQty19 = ifWrk.scmDbQty19;
//END   DEL S.Yoshida [CMEX localize]
        fill25Txt = ifWrk.fill25Txt;
    }

    /**
     * addScmDbQty01
     * @param qty BigDecimal
     */
    public void addScmDbQty01(BigDecimal qty) {
        setScmDbQty01(getScmDbQty01().add(qty));
    }

    /**
     * addScmDbQty01
     * @param qty BigDecimal
     */
    public void addScmDbQty02(BigDecimal qty) {
        setScmDbQty02(getScmDbQty02().add(qty));
    }

    /**
     * addScmDbQty03
     * @param qty BigDecimal
     */
    public void addScmDbQty03(BigDecimal qty) {
        setScmDbQty03(getScmDbQty03().add(qty));
    }

    /**
     * addScmDbQty04
     * @param qty BigDecimal
     */
    public void addScmDbQty04(BigDecimal qty) {
        setScmDbQty04(getScmDbQty04().add(qty));
    }

    /**
     * addScmDbQty05
     * @param qty BigDecimal
     */
    public void addScmDbQty05(BigDecimal qty) {
        setScmDbQty05(getScmDbQty05().add(qty));
    }

    /**
     * addScmDbQty06
     * @param qty BigDecimal
     */
    public void addScmDbQty06(BigDecimal qty) {
        setScmDbQty06(getScmDbQty06().add(qty));
    }

    /**
     * addScmDbQty07
     * @param qty BigDecimal
     */
    public void addScmDbQty07(BigDecimal qty) {
        setScmDbQty07(getScmDbQty07().add(qty));
    }

    /**
     * addScmDbQty08
     * @param qty BigDecimal
     */
    public void addScmDbQty08(BigDecimal qty) {
        setScmDbQty08(getScmDbQty08().add(qty));
    }

    /**
     * addScmDbQty09
     * @param qty BigDecimal
     */
    public void addScmDbQty09(BigDecimal qty) {
        setScmDbQty09(getScmDbQty09().add(qty));
    }

    /**
     * addScmDbQty10
     * @param qty BigDecimal
     */
    public void addScmDbQty10(BigDecimal qty) {
        setScmDbQty10(getScmDbQty10().add(qty));
    }

    /**
     * addScmDbQty11
     * @param qty BigDecimal
     */
    public void addScmDbQty11(BigDecimal qty) {
        setScmDbQty11(getScmDbQty11().add(qty));
    }

    /**
     * addScmDbQty12
     * @param qty BigDecimal
     */
    public void addScmDbQty12(BigDecimal qty) {
        setScmDbQty12(getScmDbQty12().add(qty));
    }

    /**
     * addScmDbQty13
     * @param qty BigDecimal
     */
    public void addScmDbQty13(BigDecimal qty) {
        setScmDbQty13(getScmDbQty13().add(qty));
    }

    /**
     * addScmDbQty14
     * @param qty BigDecimal
     */
    public void addScmDbQty14(BigDecimal qty) {
        setScmDbQty14(getScmDbQty14().add(qty));
    }

    /**
     * addScmDbQty15
     * @param qty BigDecimal
     */
    public void addScmDbQty15(BigDecimal qty) {
        setScmDbQty15(getScmDbQty15().add(qty));
    }

    /**
     * addScmDbQty16
     * @param qty BigDecimal
     */
    public void addScmDbQty16(BigDecimal qty) {
        setScmDbQty16(getScmDbQty16().add(qty));
    }

    /**
     * addScmDbQty17
     * @param qty BigDecimal
     */
    public void addScmDbQty17(BigDecimal qty) {
        setScmDbQty17(getScmDbQty17().add(qty));
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * addScmDbQty18
//     * @param qty BigDecimal
//     */
//    public void addScmDbQty18(BigDecimal qty) {
//        setScmDbQty18(getScmDbQty18().add(qty));
//    }
//
//    /**
//     * addScmDbQty19
//     * @param qty BigDecimal
//     */
//    public void addScmDbQty19(BigDecimal qty) {
//        setScmDbQty19(getScmDbQty19().add(qty));
//    }
//END   DEL S.Yoshida [CMEX localize]

    /**
     * clear NLCB017001InterfaceWork
     */
    public void clearNLCB017001InterfaceWork() {

        setAmendCd("");
        setGlblCmpy3Cd("");
        setMdse12Cd("");
        setLocalMdseCd("");
        setIntfcWhCd("");
        setAdminYrMth("");
        setAdminDt("");
        setScmDbQty01(BigDecimal.ZERO);
        setScmDbQty02(BigDecimal.ZERO);
        setScmDbQty03(BigDecimal.ZERO);
        setScmDbQty04(BigDecimal.ZERO);
        setScmDbQty05(BigDecimal.ZERO);
        setScmDbQty06(BigDecimal.ZERO);
        setScmDbQty07(BigDecimal.ZERO);
        setScmDbQty08(BigDecimal.ZERO);
        setScmDbQty09(BigDecimal.ZERO);
        setScmDbQty10(BigDecimal.ZERO);
        setScmDbQty11(BigDecimal.ZERO);
        setScmDbQty12(BigDecimal.ZERO);
        setScmDbQty13(BigDecimal.ZERO);
        setScmDbQty14(BigDecimal.ZERO);
        setScmDbQty15(BigDecimal.ZERO);
        setScmDbQty16(BigDecimal.ZERO);
        setScmDbQty17(BigDecimal.ZERO);
//START DEL S.Yoshida [CMEX localize]
//        setDefIntfcWhCd("");
//        setScmDbQty18(BigDecimal.ZERO);
//        setScmDbQty19(BigDecimal.ZERO);
//END   DEL S.Yoshida [CMEX localize]
        setFill25Txt("");
    }

    /**
     * @return adminDt
     */
    public String getAdminDt() {
        return adminDt;
    }

    /**
     * @return adminYrMth
     */
    public String getAdminYrMth() {
        return adminYrMth;
    }

    /**
     * getAdminYrMth
     * @param size int
     * @return String
     */
    public String getAdminYrMth(int size) {
        return S21StringUtil.subStringByLength(getAdminYrMth(), 0, size);
    }

    /**
     * @return amendCd
     */
    public String getAmendCd() {
        return amendCd;
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * @return defIntfcWhCd
//     */
//    public String getDefIntfcWhCd() {
//        return defIntfcWhCd;
//    }
//END   DEL S.Yoshida [CMEX localize]

    /**
     * @return fill25Txt
     */
    public String getFill25Txt() {
        return fill25Txt;
    }

    /**
     * @return glblCmpy3Cd
     */
    public String getGlblCmpy3Cd() {
        return glblCmpy3Cd;
    }

    /**
     * getGlblCmpy3Cd
     * @param size int
     * @return String
     */
    public String getGlblCmpy3Cd(int size) {
        return S21StringUtil.subStringByLength(getGlblCmpy3Cd(), 0, size);
    }

    /**
     * @return intfcWhCd
     */
    public String getIntfcWhCd() {
        return intfcWhCd;
    }

    /**
     * @return localMdseCd
     */
    public String getLocalMdseCd() {
        return localMdseCd;
    }

    /**
     * @return mdse12Cd
     */
    public String getMdse12Cd() {
        return mdse12Cd;
    }

    /**
     * getMdse12Cd
     * @param size int
     * @return String
     */
    public String getMdse12Cd(int size) {
        return S21StringUtil.subStringByLength(getMdse12Cd(), 0, size);
    }

    /**
     * @return scmDbQty01
     */
    public BigDecimal getScmDbQty01() {
        return scmDbQty01;
    }

    /**
     * @return scmDbQty02
     */
    public BigDecimal getScmDbQty02() {
        return scmDbQty02;
    }

    /**
     * @return scmDbQty03
     */
    public BigDecimal getScmDbQty03() {
        return scmDbQty03;
    }

    /**
     * @return scmDbQty04
     */
    public BigDecimal getScmDbQty04() {
        return scmDbQty04;
    }

    /**
     * @return scmDbQty05
     */
    public BigDecimal getScmDbQty05() {
        return scmDbQty05;
    }

    /**
     * @return scmDbQty06
     */
    public BigDecimal getScmDbQty06() {
        return scmDbQty06;
    }

    /**
     * @return scmDbQty07
     */
    public BigDecimal getScmDbQty07() {
        return scmDbQty07;
    }

    /**
     * @return scmDbQty08
     */
    public BigDecimal getScmDbQty08() {
        return scmDbQty08;
    }

    /**
     * @return scmDbQty09
     */
    public BigDecimal getScmDbQty09() {
        return scmDbQty09;
    }

    /**
     * @return scmDbQty10
     */
    public BigDecimal getScmDbQty10() {
        return scmDbQty10;
    }

    /**
     * @return scmDbQty11
     */
    public BigDecimal getScmDbQty11() {
        return scmDbQty11;
    }

    /**
     * @return scmDbQty12
     */
    public BigDecimal getScmDbQty12() {
        return scmDbQty12;
    }

    /**
     * @return scmDbQty13
     */
    public BigDecimal getScmDbQty13() {
        return scmDbQty13;
    }

    /**
     * @return scmDbQty14
     */
    public BigDecimal getScmDbQty14() {
        return scmDbQty14;
    }

    /**
     * @return scmDbQty15
     */
    public BigDecimal getScmDbQty15() {
        return scmDbQty15;
    }

    /**
     * @return scmDbQty16
     */
    public BigDecimal getScmDbQty16() {
        return scmDbQty16;
    }

    /**
     * @return scmDbQty17
     */
    public BigDecimal getScmDbQty17() {
        return scmDbQty17;
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * @return scmDbQty18
//     */
//    public BigDecimal getScmDbQty18() {
//        return scmDbQty18;
//    }
//
//    /**
//     * @return scmDbQty19
//     */
//    public BigDecimal getScmDbQty19() {
//        return scmDbQty19;
//    }
//END   DEL S.Yoshida [CMEX localize]

    /**
     * @param adminDt set adminDt
     */
    public void setAdminDt(String adminDt) {
        this.adminDt = adminDt;
    }

    /**
     * @param adminYrMth set adminYrMth
     */
    public void setAdminYrMth(String adminYrMth) {
        this.adminYrMth = adminYrMth;
    }

    /**
     * @param amendCd set amendCd
     */
    public void setAmendCd(String amendCd) {
        this.amendCd = amendCd;
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * @param defIntfcWhCd set defIntfcWhCd
//     */
//    public void setDefIntfcWhCd(String defIntfcWhCd) {
//        this.defIntfcWhCd = defIntfcWhCd;
//    }
//END   DEL S.Yoshida [CMEX localize]

    /**
     * @param fill25Txt set fill25Txt
     */
    public void setFill25Txt(String fill25Txt) {
        this.fill25Txt = fill25Txt;
    }

    /**
     * @param glblCmpy3Cd set glblCmpy3Cd
     */
    public void setGlblCmpy3Cd(String glblCmpy3Cd) {
        this.glblCmpy3Cd = glblCmpy3Cd;
    }

    /**
     * @param intfcWhCd set intfcWhCd
     */
    public void setIntfcWhCd(String intfcWhCd) {
        this.intfcWhCd = intfcWhCd;
    }

    /**
     * @param localMdseCd set localMdseCd
     */
    public void setLocalMdseCd(String localMdseCd) {
        this.localMdseCd = localMdseCd;
    }

    /**
     * @param mdse12Cd set mdse12Cd
     */
    public void setMdse12Cd(String mdse12Cd) {
        this.mdse12Cd = mdse12Cd;
    }

    /**
     * @param scmDbQty01 set scmDbQty01
     */
    public void setScmDbQty01(BigDecimal scmDbQty01) {
        this.scmDbQty01 = scmDbQty01;
    }

    /**
     * @param scmDbQty02 set scmDbQty02
     */
    public void setScmDbQty02(BigDecimal scmDbQty02) {
        this.scmDbQty02 = scmDbQty02;
    }

    /**
     * @param scmDbQty03 set scmDbQty03
     */
    public void setScmDbQty03(BigDecimal scmDbQty03) {
        this.scmDbQty03 = scmDbQty03;
    }

    /**
     * @param scmDbQty04 set scmDbQty04
     */
    public void setScmDbQty04(BigDecimal scmDbQty04) {
        this.scmDbQty04 = scmDbQty04;
    }

    /**
     * @param scmDbQty05 set scmDbQty05
     */
    public void setScmDbQty05(BigDecimal scmDbQty05) {
        this.scmDbQty05 = scmDbQty05;
    }

    /**
     * @param scmDbQty06 set scmDbQty06
     */
    public void setScmDbQty06(BigDecimal scmDbQty06) {
        this.scmDbQty06 = scmDbQty06;
    }

    /**
     * @param scmDbQty07 set scmDbQty07
     */
    public void setScmDbQty07(BigDecimal scmDbQty07) {
        this.scmDbQty07 = scmDbQty07;
    }

    /**
     * @param scmDbQty08 set scmDbQty08
     */
    public void setScmDbQty08(BigDecimal scmDbQty08) {
        this.scmDbQty08 = scmDbQty08;
    }

    /**
     * @param scmDbQty09 set scmDbQty09
     */
    public void setScmDbQty09(BigDecimal scmDbQty09) {
        this.scmDbQty09 = scmDbQty09;
    }

    /**
     * @param scmDbQty10 set scmDbQty10
     */
    public void setScmDbQty10(BigDecimal scmDbQty10) {
        this.scmDbQty10 = scmDbQty10;
    }

    /**
     * @param scmDbQty11 set scmDbQty11
     */
    public void setScmDbQty11(BigDecimal scmDbQty11) {
        this.scmDbQty11 = scmDbQty11;
    }

    /**
     * @param scmDbQty12 set scmDbQty12
     */
    public void setScmDbQty12(BigDecimal scmDbQty12) {
        this.scmDbQty12 = scmDbQty12;
    }

    /**
     * @param scmDbQty13 set scmDbQty13
     */
    public void setScmDbQty13(BigDecimal scmDbQty13) {
        this.scmDbQty13 = scmDbQty13;
    }

    /**
     * @param scmDbQty14 set scmDbQty14
     */
    public void setScmDbQty14(BigDecimal scmDbQty14) {
        this.scmDbQty14 = scmDbQty14;
    }

    /**
     * @param scmDbQty15 set scmDbQty15
     */
    public void setScmDbQty15(BigDecimal scmDbQty15) {
        this.scmDbQty15 = scmDbQty15;
    }

    /**
     * @param scmDbQty16 set scmDbQty16
     */
    public void setScmDbQty16(BigDecimal scmDbQty16) {
        this.scmDbQty16 = scmDbQty16;
    }

    /**
     * @param scmDbQty17 set scmDbQty17
     */
    public void setScmDbQty17(BigDecimal scmDbQty17) {
        this.scmDbQty17 = scmDbQty17;
    }

//START DEL S.Yoshida [CMEX localize]
//    /**
//     * @param scmDbQty18 set scmDbQty18
//     */
//    public void setScmDbQty18(BigDecimal scmDbQty18) {
//        this.scmDbQty18 = scmDbQty18;
//    }
//
//    /**
//     * @param scmDbQty19 set scmDbQty19
//     */
//    public void setScmDbQty19(BigDecimal scmDbQty19) {
//        this.scmDbQty19 = scmDbQty19;
//    }
//END   DEL S.Yoshida [CMEX localize]
}

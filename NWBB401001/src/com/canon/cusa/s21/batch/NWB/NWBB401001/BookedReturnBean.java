package com.canon.cusa.s21.batch.NWB.NWBB401001;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/** <pre>
 * Order Close Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   Fujitsu         M.Hara          Create          N/A
 * 2017/09/21   Fujitsu         S.Takami        Update          S21_NA#21151
 * 2017/10/13   Fujitsu         S.Takami        Update          S21_NA#21151-2
 * 2017/12/13   Fujitsu         S.Takami        Update          S21_NA#21151-4 (delete #getErrorHeaderGroupingKey(DsRtrnRwsApiBean param))
 * 2018/05/28   Fujitsu         K.Ishizuka      Update          S21_NA#25854
 * 2018/09/13   Fujitsu         K.Ishizuka      Update          S21_NA#28182
 * 2019/03/17   Fujitsu         K.Ishizuka      Update          S21_NA#30713
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 * </pre>
 */
/**
 * @author q05163
 *
 */
public class BookedReturnBean {

    /** CPO_ORD_NUM */
    private String cpoOrdNum;
    /** DS_ORD_POSN_NUM */
    private String dsOrdPosnNum;
    /** RTL_WH_CD */
    private String rtlWhCd;
    /** DS_CPO_RTRN_LINE_NUM */
    private String dsCpoRtrnLineNum;
    /** DS_CPO_RTRN_LINESUB__NUM */
    private String dsCpoRtrnLineSubNum;
    /** ORD_QTY */
    private BigDecimal ordQty;
    /** SER_NUM */
    private String serNum;
    // 2017/09/21 S21_NA#21151 Add Start
    /** Service Machine Master PK*/
    private BigDecimal svcMachMstrPk;

    /** Stock Status Code */
    private String stkStsCd;

    /** Inventory Location Code */
    private String     invtyLocCd;

    /** DS CPO Line Number */
    private BigDecimal dsCpoLineNum;

    /** DS CPO Line Sub number */
    private BigDecimal dsCpoLineSubNum;

    /** Service Machine Master Update API Error Message List */
    private List<String> svcMachMastrErrMsgList = new ArrayList<String>(0);
    // 2017/09/21 S21_NA#21151 Add End

    /** Service Config Master PK */
    private String svcConfigMstrPk; // 2018/05/28 S21_NA#25854 Add

    /** Install Base Control Flag */
    private String instlBaseCtrlFlg; // 2018/09/13 S21_NA#28182 Add

    // 2019/03/17 S21_NA#30713 Add Start
    /** DS Contract Number */
    private String dsContrNum;
    // 2019/03/17 S21_NA#30713 Add End

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /** To Be Deinstalled By */
    private String toBeDeinstalledBy;
    // END   2023/12/12 K.Watanabe [QC#61300, ADD]

    /**
     * get header key
     * @return header key
     */
    public String getHeaderGroupingKey() {
        // 2018/05/28 S21_NA#25854 Mod Start
//        String key = String.format("%s-%s-%s"
//                                , this.cpoOrdNum
//                                , this.dsOrdPosnNum
//                                , this.rtlWhCd);
        String key = String.format("%s-%s-%s-%s"
                , this.cpoOrdNum
                , this.dsOrdPosnNum
                , this.rtlWhCd
                , this.svcConfigMstrPk);
        // 2018/05/28 S21_NA#25854 Mod End
        return key;
    }

    // 2017/09/21 S21_NA#21151 Add Start
    /**
     * get error message grouping key
     * @return error message grouping key
     */
    public String getErrorHeaderGroupingKey() {
        String key = String.format("%s-%s"
                                , this.cpoOrdNum
                                , this.dsOrdPosnNum);
        return key;
    }
    // 2017/09/21 S21_NA#21151 Add End

    /**
     * overwrite toString method
     * @see java.lang.Object#toString()
     * @return to string
     */
    public String toString() {
        StringBuffer objString = new StringBuffer("Record ===>\r\n");

        Method[] methods = this.getClass().getMethods();
        String fldName;
        Object fldVal;

        for (int i = 0; i < methods.length; i++) {
           if (methods[i].getName().startsWith("get")) {
               fldName = methods[i].getName().substring(3, 4).toLowerCase();
               fldName += methods[i].getName().substring(4);

               try {
                   fldVal = methods[i].invoke(this);
                } catch (Exception e) {
                    fldVal = e.getMessage();
                }

                // 2017/10/13 S21_NA#21151-2 Mod Start
//                objString.append(String.format("\t%s => [%s]\r\n", fldName, fldVal.toString()));
                try {
                    objString.append(String.format("\t%s => [%s]\r\n", fldName, fldVal.toString()));
                } catch (Exception e) {
                    objString.append(String.format("\t%s => [%s]\r\n", fldName, ""));
                }
                // 2017/10/13 S21_NA#21151-2 Mod End
            }
        }
        objString.append("<=== Record");

        return objString.toString();
    }

    /**
     * get CPO_ORD_NUM
     * @return CPO_ORD_NUM
     */
    public String getCpoOrdNum() {
        return cpoOrdNum;
    }
    /**
     * set CPO_ORD_NUM
     * @param cpoOrdNum CPO_ORD_NUM
     */
    public void setCpoOrdNum(String cpoOrdNum) {
        this.cpoOrdNum = cpoOrdNum;
    }
    /**
     * get DS_ORD_POSN_NUM
     * @return DS_ORD_POSN_NUM
     */
    public String getDsOrdPosnNum() {
        return dsOrdPosnNum;
    }
    /**
     * set DS_ORD_POSN_NUM
     * @param dsOrdPosnNum DS_ORD_POSN_NUM
     */
    public void setDsOrdPosnNum(String dsOrdPosnNum) {
        this.dsOrdPosnNum = dsOrdPosnNum;
    }
    /**
     * get RTL_WH_CD
     * @return RTL_WH_CD
     */
    public String getRtlWhCd() {
        return rtlWhCd;
    }
    /**
     * set RTL_WH_CD
     * @param rtlWhCd RTL_WL_CD
     */
    public void setRtlWhCd(String rtlWhCd) {
        this.rtlWhCd = rtlWhCd;
    }
    /**
     * get DS_CPO_RTRN_LINE_NUM
     * @return get DS_CPO_RTRN_LINE_NUM
     */
    public String getDsCpoRtrnLineNum() {
        return dsCpoRtrnLineNum;
    }
    /**
     * set DS_CPO_RTRN_LINE_NUM
     * @param dsCpoRtrnLineNum DS_CPO_RTRN_LINE_NUM
     */
    public void setDsCpoRtrnLineNum(String dsCpoRtrnLineNum) {
        this.dsCpoRtrnLineNum = dsCpoRtrnLineNum;
    }
    /**
     * get DS_CPO_RTRN_LINE_SUB_NUM
     * @return DS_CPO_RTRN_LINE_SUB_NUM
     */
    public String getDsCpoRtrnLineSubNum() {
        return dsCpoRtrnLineSubNum;
    }
    /**
     * set DS_CPO_RTRN_LINE_SUB_NUM
     * @param dsCpoRtrnLineSubNum DS_CPO_RTRN_LINE_SUB_NUM
     */
    public void setDsCpoRtrnLineSubNum(String dsCpoRtrnLineSubNum) {
        this.dsCpoRtrnLineSubNum = dsCpoRtrnLineSubNum;
    }
    /**
     * get ORD_QTY
     * @return ORD_QTY
     */
    public BigDecimal getOrdQty() {
        return ordQty;
    }
    /**
     * set ORD_QTY
     * @param ordQty ORD_QTY
     */
    public void setOrdQty(BigDecimal ordQty) {
        this.ordQty = ordQty;
    }
    /**
     * get SER_NUM
     * @return SER_NUM
     */
    public String getSerNum() {
        return serNum;
    }
    /**
     * set SER_NUM
     * @param serNum SER_NUM
     */
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    // 2017/09/21 S21_NA#21151 Add Start
    /**
     * get Service Machine Master PK
     * @return Service Machine Master PK
     */
    public BigDecimal getSvcMachMstrPk() {
        return svcMachMstrPk;
    }

    /**
     * Set Service Machine Master PK
     * @param svcMachMstrPk Service Machine Master Pk
     */
    public void setSvcMachMstrPk(BigDecimal svcMachMstrPk) {
        this.svcMachMstrPk = svcMachMstrPk;
    }

    /**
     * Get Stock Status Code
     * @return Stock Status Code
     */
    public String getStkStsCd() {
        return stkStsCd;
    }

    /**
     * Set Stock Status Code
     * @param stkStsCd Stock Status Code
     */
    public void setStkStsCd(String stkStsCd) {
        this.stkStsCd = stkStsCd;
    }

    /**
     * Get Inventory Location Code
     * @return Inventory Location Code
     */
    public String getInvtyLocCd() {
        return invtyLocCd;
    }

    /**
     * Set Inventory Location Code
     * @param invtyLocCd Inventory Location Code
     */
    public void setInvtyLocCd(String invtyLocCd) {
        this.invtyLocCd = invtyLocCd;
    }

    /**
     * Get DS CPO Line Number
     * @return DS CPO Line Number
     */
    public BigDecimal getDsCpoLineNum() {
        return dsCpoLineNum;
    }

    /**
     * Set DS CPO Line Number
     * @param dsCpoLineNum DS CPO Line Number
     */
    public void setDsCpoLineNum(BigDecimal dsCpoLineNum) {
        this.dsCpoLineNum = dsCpoLineNum;
    }

    /**
     * Get DS CPO Line Sub Number
     * @return DS CPO Line Sub Number
     */
    public BigDecimal getDsCpoLineSubNum() {
        return dsCpoLineSubNum;
    }

    /**
     * Set DS CPO Line Sub Number
     * @param dsCpoLineSubNum DS CPO Line Sub Number
     */
    public void setDsCpoLineSubNum(BigDecimal dsCpoLineSubNum) {
        this.dsCpoLineSubNum = dsCpoLineSubNum;
    }

    /**
     * get Service Machine Master Update Api Error Message List
     * @return Error Message List
     */
    public List<String> getSvcMachMastrErrMsgList() {
        return svcMachMastrErrMsgList;
    }
    // 2017/09/21 S21_NA#21151 Add End

    // 2018/05/29 S21_NA#25854 Add Start
    /**
     * get Service Config Master PK
     * @return Service Config Master PK
     */
    public String getSvcConfigMstrPk() {
        return svcConfigMstrPk;
    }

    /**
     * Set Service Config Master PK
     * @param svcConfigMstrPk Service Config Master Pk
     */
    public void setSvcConfigMstrPk(String svcConfigMstrPk) {
        this.svcConfigMstrPk = svcConfigMstrPk;
    }
    // 2018/05/29 S21_NA#25854 Add End

    // 2018/09/13 S21_NA#28182 Add Start
    /**
     * set Install Base Control Flag
     * @param instlBaseCtrlFlg Install Base Control Flag
     */
    public void setInstlBaseCtrlFlg(String instlBaseCtrlFlg) {
        this.instlBaseCtrlFlg = instlBaseCtrlFlg;
    }

    /**
     * get Install Base Control Flag
     * @return instlBaseCtrlFlg
     */
    public String getInstlBaseCtrlFlg() {
        return instlBaseCtrlFlg;
    }
    // 2018/09/13 S21_NA#28182 Add End

    // 2019/03/17 S21_NA#30713 Add Start
    /**
     * set DS Contract Number
     * @param dsContrNum dsContrNum
     */
    public void setDsContrNum(String dsContrNum) {
        this.dsContrNum = dsContrNum;
    }

    /**
     * get DS Contract Number
     * @return dsContrNum
     */
    public String getDsContrNum() {
        return dsContrNum;
    }
    // 2019/03/17 S21_NA#30713 Add End

    // START 2023/12/12 K.Watanabe [QC#61300, ADD]
    /**
     * get To Be Deinstalled By
     * @return To Be Deinstalled By
     */
    public String getToBeDeinstalledBy() {
        return toBeDeinstalledBy;
    }

    /**
     * set To Be Deinstalled By
     * @param toBeDeinstalledBy To Be Deinstalled By
     */
    public void setToBeDeinstalledBy(String toBeDeinstalledBy) {
        this.toBeDeinstalledBy = toBeDeinstalledBy;
    }
    // END   2023/12/12 K.Watanabe [QC#61300, ADD]

}

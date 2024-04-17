package com.canon.cusa.s21.batch.NWB.NWBB401001;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDPBigDecimalItem;
import parts.common.EZDPDateItem;
import parts.common.EZDPStringItem;
import business.parts.NLZC200001PMsg;
import business.parts.NLZC200001_xxDetailListPMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;

/** <pre>
 * Order Close Bean
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/16/2015   Fujitsu         M.Hara          Create          N/A
 * 2016/06/02   Fujitsu         M.Hara          Update          S21_NA#9255-2
 * 2017/12/12   Fujitsu         S.Takami        Update          S21_NA#21151-3
 * 2017/12/13   Fujitsu         S.Takami        Update          S21_NA#21151-4
 * </pre>
 */
public class DsRtrnRwsApiBean extends NLZC200001PMsg {

    /** Serial Verion */
    private static final long serialVersionUID = 1L;

    /** grouping key*/
    private String key;

    /** Error message List*/
    private List<String> cpoRtrnLineUpdApiErr = new ArrayList<String>(0);

    // 2017/12/12 S21_NA#21151-2 Add Start
    /** Booked Return Record List */
    private List<BookedReturnBean> readBeanList = new ArrayList<BookedReturnBean>(0);
    // 2017/12/12 S21_NA#21151-2 Add End

    /**
     * Overwritted toString Method
     * @see parts.common.EZDMsg#toString()
     * @return bean contents
     */
    public String toString() {
        StringBuffer objString = new StringBuffer();

        objString.append("===>\r\n");

        objString.append(getFieldItemString("key", key));
        objString.append(getFieldItemString("glblCmpyCd", this.glblCmpyCd));
        objString.append(getFieldItemString("sysSrcCd", this.sysSrcCd));
        objString.append(getFieldItemString("inbdSrcTpCd", this.inbdSrcTpCd));
        objString.append(getFieldItemString("slsDt", this.slsDt));
        objString.append(getFieldItemString("cpoOrdNum", this.cpoOrdNum));
        objString.append(getFieldItemString("dsOrdPosnNum", this.dsOrdPosnNum));

        NLZC200001_xxDetailListPMsg dtlMsg;

        for (int i = 0; i < this.xxDetailList.getValidCount(); i++) {
            dtlMsg = (NLZC200001_xxDetailListPMsg) this.xxDetailList.get(i);

            objString.append(getFieldItemString(i, "dsCpoRtrnLineNum", dtlMsg.dsCpoRtrnLineNum));
            objString.append(getFieldItemString(i, "dsCpoRtrnLineSubNum", dtlMsg.dsCpoRtrnLineSubNum));
            objString.append(getFieldItemString(i, "ordQty", dtlMsg.ordQty));
        }

        // 2017/12/13 S21_NA#21151-4 Mod Start
//        objString.append("API Error\r\n");
//        for (String errMsg : this.cpoRtrnLineUpdApiErr) {
//            objString.append(String.format("\t{%s}\r\n", errMsg));
//        }
        boolean hasErrMsg = !this.cpoRtrnLineUpdApiErr.isEmpty();
        if (hasErrMsg) {
            objString.append("Catch NLZC200001 API Error\r\n");

            for (String errMsg : this.cpoRtrnLineUpdApiErr) {
                objString.append(String.format("\t{%s}\r\n", errMsg));
            }
        }
        // 2017/12/13 S21_NA#21151-4 Mod End

        objString.append("<===\r\n");

        return objString.toString();
    }

    private static String getFieldItemString(Integer idx, String fldName, Object val) {
        return getFieldItemString(String.format("%d:%s", idx, fldName), val);
    }

    private static String getFieldItemString(String fldName, Object val) {
        String valString = "";

        if (val instanceof EZDPStringItem) {
            valString = ((EZDPStringItem) val).getValue();
        } else if (val instanceof EZDPBigDecimalItem) {
            valString = ((EZDPBigDecimalItem) val).getValue().toString();
        } else if (val instanceof EZDPDateItem) {
            valString = ((EZDPDateItem) val).getValue().toString();
        } else {
            valString = val.toString();
        }

        return String.format("\t%s => [%s]\r\n", fldName, valString);
    }

    // S21_NA#9255-2
//    public List<String> getAllErrMsgID() {
//        List<String> retList = new ArrayList<String>();
//
//        for (int n = 0; n < this.xxMsgIdList.getValidCount(); n++) {
//            retList.add(this.xxMsgIdList.no(n).xxMsgId.getValue());
//        }
//
//        retList.addAll(this.cpoRtrnLineUpdApiErr);
//
//        return retList;
//    }

    /**
     * Constructor
     * @param key Mapping Key
     */
    public DsRtrnRwsApiBean(String key) {
        this.key = key;
    }

    /**
     * get mapping key
     * @return mapping key
     */
    public String getKey() {
        return key;
    }

    /**
     * get return list
     * @return return list
     */
    public List<String> getCpoRtrnLineUpdApiErr() {
        return cpoRtrnLineUpdApiErr;
    }

    // 2017/12/13 S21_NA#21151-4 Add Start
    /**
     * Add Error Message Id
     * @param errMsg error message
     */
    public void addCpoRtrnLineUpdApiErr(String errMsg) {
        this.cpoRtrnLineUpdApiErr.add(errMsg);
    }
    // 2017/12/13 S21_NA#21151-4 Add End
    // 2017/12/12 S21_NA#21151-2 Add Start
    /**
     * add BookedReturnBean
     * @param readBean BookedReturnBean
     */
    public void addReadBeanList(BookedReturnBean readBean) {
        this.readBeanList.add(readBean);
    }

    /**
     * get BookedReturnBeanList
     * @return BookedReturnBeanList
     */
    public List<BookedReturnBean> getReadBeanList() {
        return this.readBeanList;
    }
    // 2017/12/12 S21_NA#21151-2 Add End

    // 2017/12/13 S21_NA#21151-4 Add Start
    /**
     * get Error Header Grouping Key
     * @return error header grouping key
     */
    public String getErrorHeaderGroupingKey() {
        if (!ZYPCommonFunc.hasValue(this.cpoOrdNum) //
                || !ZYPCommonFunc.hasValue(this.dsOrdPosnNum)) {
            return null;
        }
        String errKey = String.format("%s-%s"
                                , this.cpoOrdNum.getValue()
                                , this.dsOrdPosnNum.getValue());
        return errKey;
    }
    // 2017/12/13 S21_NA#21151-4 Add End
}

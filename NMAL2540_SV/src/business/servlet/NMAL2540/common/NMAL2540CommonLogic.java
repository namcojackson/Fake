/**
 * Copyright(c)2012 Canon USA Inc. All rights reserved.
 */
package business.servlet.NMAL2540.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL2540.NMAL2540BMsg;
import business.servlet.NMAL2540.constant.NMAL2540Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

/**
 *<pre>
 * Resource Search NMAL24000CommonLogic
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/10   Fujitsu         J.Sakamoto      Create          N/A
 * 2015/10/22   Fujitsu         K.Koitabashi    Update          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 * 2017/12/01   Fujitsu         Hd.Sugawara     Update          QC#21959
 *</pre>
 */

public class NMAL2540CommonLogic {

    /**
     * <pre>
     * Initial common button
     * </pre>
     * @param handler EZCommandHandler
     */
    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(NMAL2540Constant.BTN_CMN_CLEAR[0], NMAL2540Constant.BTN_CMN_CLEAR[1], NMAL2540Constant.BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(NMAL2540Constant.BTN_CMN_CLOSE[0], NMAL2540Constant.BTN_CMN_CLOSE[1], NMAL2540Constant.BTN_CMN_CLOSE[2], 1, null);
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2540BMsg
     */
    public static final void initialControlScreen(EZDCommonHandler handler, NMAL2540BMsg scrnMsg) {
        initCommonButton(handler);
        initButton(handler);
        controlScreenFields(handler, scrnMsg);

    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     */
    public static void initButton(EZDCommonHandler handler) {

        handler.setButtonEnabled(NMAL2540Constant.BTN_SEARCH[0], true);
    }

    /**
     * Control screen fields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2540BMsg
     */
    public static final void controlScreenFields(EZDCommonHandler handler, NMAL2540BMsg scrnMsg) {

        controlScreenDetailFields(handler, scrnMsg);
    }

    /**
     * controlScreenDetailFields
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL2540BMsg
     */
    private static final void controlScreenDetailFields(EZDCommonHandler handler, NMAL2540BMsg scrnMsg) {
        scrnMsg.ctryNm_H1.setInputProtected(true);
    }

    /**
     * Check input
     * @param scrnMsg NMAL2540BMsg
     */
    public static void checkInput(NMAL2540BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_H1);
        scrnMsg.addCheckItem(scrnMsg.stCd_H1);
        scrnMsg.addCheckItem(scrnMsg.postCd_H1);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_H1);
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL2540BMsg
     */
    public static void setInitParamForCtryPopup(NMAL2540BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.ctryCd_G2.clear();
        scrnMsg.ctryNm_G2.clear();

        // job title Code is set to sub screen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, NMAL2540Constant.TBL_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, NMAL2540Constant.TBL_CD_COL_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, NMAL2540Constant.TBL_NM_COL_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, NMAL2540Constant.TBL_SORT_COL_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, NMAL2540Constant.SCR_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, NMAL2540Constant.HDR_CD_LB_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, NMAL2540Constant.HDR_NM_LB_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, NMAL2540Constant.DTL_CD_LB_NM_FOR_CTRY);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, NMAL2540Constant.DTL_NM_LB_NM_FOR_CTRY);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2540BMsg
     * @return Object[]
     */
    public static Object[] setParamForCtryPopup(NMAL2540BMsg scrnMsg) {

        Object[] params = new Object[NMAL2540Constant.IDX_11];
        params[NMAL2540Constant.IDX_00] = scrnMsg.xxTblNm_P1;
        params[NMAL2540Constant.IDX_01] = scrnMsg.xxTblCdColNm_P1;
        params[NMAL2540Constant.IDX_02] = scrnMsg.xxTblNmColNm_P1;
        params[NMAL2540Constant.IDX_03] = scrnMsg.xxTblSortColNm_P1;
        params[NMAL2540Constant.IDX_04] = scrnMsg.xxScrNm_P1;
        params[NMAL2540Constant.IDX_05] = scrnMsg.xxHdrCdLbNm_P1;
        params[NMAL2540Constant.IDX_06] = scrnMsg.xxHdrNmLbNm_P1;
        params[NMAL2540Constant.IDX_07] = scrnMsg.xxDtlCdLbNm_P1;
        params[NMAL2540Constant.IDX_08] = scrnMsg.xxDtlNmLbNm_P1;
        params[NMAL2540Constant.IDX_09] = scrnMsg.ctryCd_G2;
        params[NMAL2540Constant.IDX_10] = scrnMsg.ctryNm_G2;

        return params;
    }

    /**
     * The method explanation: set initial parameter to call popup.
     * @param scrnMsg NMAL2540BMsg
     */
    public static void setInitParamForStPopup(NMAL2540BMsg scrnMsg) {

        scrnMsg.xxTblNm_P1.clear();
        scrnMsg.xxTblCdColNm_P1.clear();
        scrnMsg.xxTblNmColNm_P1.clear();
        scrnMsg.xxTblSortColNm_P1.clear();
        scrnMsg.xxScrNm_P1.clear();
        scrnMsg.xxHdrCdLbNm_P1.clear();
        scrnMsg.xxHdrNmLbNm_P1.clear();
        scrnMsg.xxDtlCdLbNm_P1.clear();
        scrnMsg.xxDtlNmLbNm_P1.clear();
        scrnMsg.stCd_G1.clear();
        scrnMsg.stNm_G1.clear();

        // job title Code is set to sub screen delivery information.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm_P1, NMAL2540Constant.TBL_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm_P1, NMAL2540Constant.TBL_CD_COL_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm_P1, NMAL2540Constant.TBL_NM_COL_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm_P1, NMAL2540Constant.TBL_SORT_COL_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm_P1, NMAL2540Constant.SCR_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm_P1, NMAL2540Constant.HDR_CD_LB_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm_P1, NMAL2540Constant.HDR_NM_LB_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm_P1, NMAL2540Constant.DTL_CD_LB_NM_FOR_ST);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm_P1, NMAL2540Constant.DTL_NM_LB_NM_FOR_ST);
    }

    /**
     * The method explanation: set parameter to call popup.
     * @param scrnMsg NMAL2540BMsg
     * @return Object[]
     */
    public static Object[] setParamForStPopup(NMAL2540BMsg scrnMsg) {

        Object[] params = new Object[NMAL2540Constant.IDX_11];
        params[NMAL2540Constant.IDX_00] = scrnMsg.xxTblNm_P1;
        params[NMAL2540Constant.IDX_01] = scrnMsg.xxTblCdColNm_P1;
        params[NMAL2540Constant.IDX_02] = scrnMsg.xxTblNmColNm_P1;
        params[NMAL2540Constant.IDX_03] = scrnMsg.xxTblSortColNm_P1;
        params[NMAL2540Constant.IDX_04] = scrnMsg.xxScrNm_P1;
        params[NMAL2540Constant.IDX_05] = scrnMsg.xxHdrCdLbNm_P1;
        params[NMAL2540Constant.IDX_06] = scrnMsg.xxHdrNmLbNm_P1;
        params[NMAL2540Constant.IDX_07] = scrnMsg.xxDtlCdLbNm_P1;
        params[NMAL2540Constant.IDX_08] = scrnMsg.xxDtlNmLbNm_P1;
        params[NMAL2540Constant.IDX_09] = scrnMsg.stCd_G1;
        params[NMAL2540Constant.IDX_10] = scrnMsg.stNm_G1;

        return params;
    }
    
    // QC#4505
    public static Object[] prepareAddressLookupPopupParameter(NMAL2540BMsg scrnMsg, String glblCmpyCd, String eventName) {
        scrnMsg.xxScrEventNm.setValue(eventName);
        
        Object[] params = new Object[7];

        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");
        
        List<Object[]> whereList = new ArrayList<Object[]>();
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.ctyAddr_H1.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.stCd_H1.getValue(), "Y");

        // Mod Start 2017/12/01 QC#21959
        //addWhereCondition(whereList, "Postal Code", "POST_CD", scrnMsg.postCd_H1.getValue(), "Y");
        String postCd = scrnMsg.postCd_H1.getValue();
        if (ZYPCommonFunc.hasValue(postCd) && postCd.length() >= 6) {
            postCd = postCd.substring(0, 5);
            postCd += NMAL2540Constant.WILD_CARD;
        }

        addWhereCondition(whereList, "Postal Code", "POST_CD", postCd, "Y");
        // Mod End 2017/12/01 QC#21959

        addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.cntyNm_H1.getValue(), "Y");

        List<Object[]> columnList = new ArrayList<Object[]>();
        addDisplayColumn(columnList, "City", "CTY_ADDR", BigDecimal.valueOf(25), "Y");
        addDisplayColumn(columnList, "State", "ST_CD", BigDecimal.valueOf(5), "Y");
        addDisplayColumn(columnList, "Postal Code", "POST_CD", BigDecimal.valueOf(10), "Y");
        addDisplayColumn(columnList, "County", "CNTY_NM", BigDecimal.valueOf(30), "Y");
        
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        addSortCondition(sortConditionList, "CTY_ADDR", "ASC");
        addSortCondition(sortConditionList, "ST_CD", "ASC");
        addSortCondition(sortConditionList, "POST_CD", "ASC");
        addSortCondition(sortConditionList, "CNTY_NM", "ASC");

        scrnMsg.L.clear();

        params[0] = "";
        params[1] = "Address Lookup Popup";
        params[2] = baseSql.toString();
        params[3] = whereList;
        params[4] = columnList;
        params[5] = sortConditionList;
        params[6] = scrnMsg.L;
        
        return params;
    }
    
    private static void addWhereCondition(List<Object[]> whereList, String labelName, String dbColumnName, String initValue, String likeConditionFlag){
        Object[] whereArray= new Object[4];
        whereArray[0] = labelName;
        whereArray[1] = dbColumnName;
        whereArray[2] = initValue;
        whereArray[3] = likeConditionFlag;
        whereList.add(whereArray);
    }
    
    private static void addDisplayColumn(List<Object[]> columnList, String labelName, String dbColumnName, BigDecimal displaySize, String linkFlag){
        Object[] columnArray = new Object[4];
        columnArray[0] = labelName;
        columnArray[1] = dbColumnName;
        columnArray[2] = displaySize;
        columnArray[3] = linkFlag;
        columnList.add(columnArray);
    }
    
    private static void addSortCondition(List<Object[]> sortConditionList, String dbColumnName, String orderBy){
        Object[] sortConditionArray = new Object[2];
        sortConditionArray[0] = dbColumnName;
        sortConditionArray[1] = orderBy;
        sortConditionList.add(sortConditionArray);
    }
}

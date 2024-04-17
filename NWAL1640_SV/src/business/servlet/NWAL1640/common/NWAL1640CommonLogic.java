/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1640.common;

import static business.servlet.NWAL1640.constant.NWAL1640Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1640.constant.NWAL1640Constant.BTN_CMN_CLS;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import business.servlet.NWAL1640.NWAL1640BMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1640CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/02   Fujitsu         C.Yokoi         Create          N/A
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL1640CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1640BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL1640BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);
    }

    /**
     * protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1640BMsg
     */
    public static void protectCmnBtnProp(S21CommonHandler handler, NWAL1640BMsg scrnMsg) {

        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
    }

    /**
     * addCheckItem
     * @param scrnMsg NWAL1640BMsg
     */
    public static void addCheckItem(NWAL1640BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.splyTpCd);
        // 2019/10/04 S21_NA#51372 Mod Start
        //scrnMsg.addCheckItem(scrnMsg.splyNm);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm);
        // 2019/10/04 S21_NA#51372 Mod End
        scrnMsg.addCheckItem(scrnMsg.splyCtyAddr);
        scrnMsg.addCheckItem(scrnMsg.splyFirstAddr);
        scrnMsg.addCheckItem(scrnMsg.splyStCd);
        scrnMsg.addCheckItem(scrnMsg.splyPostCd);

        scrnMsg.putErrorScreen();
    }

    /**
     * Active Link
     * @param scrnMsg NWAL1640BMsg
     */
    public static void activeLink(NWAL1640BMsg scrnMsg) {

        // 2019/10/04 S21_NA#51372 Mod Start
        //scrnMsg.splyNm_LK.setInputProtected(false);
        //scrnMsg.splyNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        scrnMsg.prntVndNm_LK.setInputProtected(false);
        scrnMsg.prntVndNm_LK.setValue(ZYPConstant.FLG_ON_Y);
        // 2019/10/04 S21_NA#51372 Mod End
        scrnMsg.splyStCd_LK.setInputProtected(false);
        scrnMsg.splyStCd_LK.setValue(ZYPConstant.FLG_ON_Y);
    }
    
    // QC#4505
    public static Object[] prepareAddressLookupPopupParameter(NWAL1640BMsg scrnMsg, String glblCmpyCd, String eventName) {
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
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.splyCtyAddr.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.splyStCd.getValue(), "Y");
        addWhereCondition(whereList, "Postal Code", "POST_CD", scrnMsg.splyPostCd.getValue(), "Y");
        addWhereCondition(whereList, "County", "CNTY_NM", "", "Y");

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

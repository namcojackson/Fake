/**
 * <Pre>
 * 
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * 
 * </Pre>
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   CUSA            Fujitsu         Update          N/A
 * 10/29/2009   CUSA            K.Hasegawa      Update
 * 11/03/2009   CUSA            T.Tanaka        Update          1480
 * 12/04/2009   CUSA            T.Tanaka        Update          2347
 * 04/23/2010   Fujitsu         K.Tajima        Update          4202
 * 05/20/2016   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
package business.servlet.NWAL0140.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NWAL0140.NWAL0140BMsg;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;

public class NWAL0140CommonLogic implements NWAL0140Constant {

    public static void clearCondition(NWAL0140BMsg scrnMsg) {

        scrnMsg.xxDtlLineNum.clear();
        scrnMsg.shipToCustCd.clear();
        scrnMsg.locNm.clear();
        scrnMsg.addlLocNm.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.scdRefCmntTxt.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.provNm.clear();
        scrnMsg.postCd.clear();
        scrnMsg.ctryNm.clear();
        scrnMsg.ctryCd.clear();
        scrnMsg.cntyNm.clear();
        scrnMsg.dropShipFlg.clear();
        scrnMsg.sellToCustCd_J1.clear();
        scrnMsg.billToCustCd_J1.clear();
        scrnMsg.sellToCustCd_BK.clear();
        scrnMsg.sellToLocNm_BK.clear();
        scrnMsg.billToCustCd_BK.clear();
        scrnMsg.billToLocNm_BK.clear();
        scrnMsg.xxReadOnlyFlg.clear();
    }

    public static void clearEditItem(NWAL0140BMsg scrnMsg) {

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.dropShipFlg.getValue())) {
            return;
        }
        scrnMsg.locNm.clear();
        scrnMsg.addlLocNm.clear();
        scrnMsg.firstLineAddr.clear();
        scrnMsg.scdLineAddr.clear();
        scrnMsg.thirdLineAddr.clear();
        scrnMsg.frthLineAddr.clear();
        scrnMsg.firstRefCmntTxt.clear();
        scrnMsg.scdRefCmntTxt.clear();
        scrnMsg.ctyAddr.clear();
        scrnMsg.stCd.clear();
        scrnMsg.provNm.clear();
        scrnMsg.postCd.clear();
        scrnMsg.ctryNm.clear();
        scrnMsg.ctryCd.clear();
        scrnMsg.cntyNm.clear();
    }

    public static void editCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

        handler.setButtonEnabled(EDIT_BTN, true);
        // QC#4505
        handler.setButtonEnabled(GET_ADDR_BTN, true);
    }

    public static void initCommonButton(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

        handler.setButtonEnabled(EDIT_BTN, true);
        // QC#4505
        handler.setButtonEnabled(GET_ADDR_BTN, false);
    }

    public static void initCommonButtonReadOnly(EZDCommonHandler handler) {

        handler.setButtonProperties(BTN_CMN_CLEAR[0], BTN_CMN_CLEAR[1], BTN_CMN_CLEAR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLOSE[0], BTN_CMN_CLOSE[1], BTN_CMN_CLOSE[2], 1, null);

        handler.setButtonEnabled(EDIT_BTN, false);
        // QC#4505
        handler.setButtonEnabled(GET_ADDR_BTN, false);
    }

    public static void protectedInput(NWAL0140BMsg scrnMsg) {

        scrnMsg.locNm.setInputProtected(true);
        scrnMsg.addlLocNm.setInputProtected(true);
        scrnMsg.firstRefCmntTxt.setInputProtected(true);
        scrnMsg.scdRefCmntTxt.setInputProtected(true);
        scrnMsg.firstLineAddr.setInputProtected(true);
        scrnMsg.scdLineAddr.setInputProtected(true);
        scrnMsg.thirdLineAddr.setInputProtected(true);
        scrnMsg.frthLineAddr.setInputProtected(true);
        scrnMsg.postCd.setInputProtected(true);
        scrnMsg.ctyAddr.setInputProtected(true);
        scrnMsg.cntyNm.setInputProtected(true);
        scrnMsg.stCd.setInputProtected(true);
        scrnMsg.provNm.setInputProtected(true);
        scrnMsg.ctryCd.setInputProtected(true);
        scrnMsg.ctryNm.setInputProtected(true);

        scrnMsg.postCd_H1.setInputProtected(true);
        scrnMsg.stCd_H1.setInputProtected(true);
        scrnMsg.ctryCd_H1.setInputProtected(true);
    }

    public static void unprotectedInput(NWAL0140BMsg scrnMsg) {

        scrnMsg.locNm.setInputProtected(false);
        scrnMsg.addlLocNm.setInputProtected(false);
        scrnMsg.firstRefCmntTxt.setInputProtected(false);
        scrnMsg.scdRefCmntTxt.setInputProtected(false);
        scrnMsg.firstLineAddr.setInputProtected(false);
        scrnMsg.scdLineAddr.setInputProtected(false);
        scrnMsg.thirdLineAddr.setInputProtected(false);
        scrnMsg.frthLineAddr.setInputProtected(false);
        scrnMsg.postCd.setInputProtected(false);
        scrnMsg.ctyAddr.setInputProtected(false);
        scrnMsg.cntyNm.setInputProtected(false);
        scrnMsg.stCd.setInputProtected(false);
        scrnMsg.provNm.setInputProtected(false);
        scrnMsg.ctryCd.setInputProtected(false);
        scrnMsg.ctryNm.setInputProtected(false);

        scrnMsg.postCd_H1.setInputProtected(false);
        scrnMsg.stCd_H1.setInputProtected(false);
        scrnMsg.ctryCd_H1.setInputProtected(false);
    }

    // QC#4505
    public static Object[] prepareAddressLookupPopupParameter(NWAL0140BMsg scrnMsg, String glblCmpyCd, String eventName) {
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
        addWhereCondition(whereList, "City", "CTY_ADDR", scrnMsg.ctyAddr.getValue(), "Y");
        addWhereCondition(whereList, "State", "ST_CD", scrnMsg.stCd.getValue(), "Y");
        addWhereCondition(whereList, "Postal Code", "POST_CD", scrnMsg.postCd.getValue(), "Y");
        addWhereCondition(whereList, "County", "CNTY_NM", scrnMsg.cntyNm.getValue(), "Y");

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

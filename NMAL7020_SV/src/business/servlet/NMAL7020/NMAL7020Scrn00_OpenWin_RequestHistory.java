/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL7020.common.NMAL7020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7020Scrn00_OpenWin_RequestHistory
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Scrn00_OpenWin_RequestHistory extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CommonLogic.clearMandantory(scrnMsg);
        NMAL7020CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("NMAL7020Scrn00_OpenWin_RequestHistory");
        ZYPTableUtil.clear(scrnMsg.P);
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Request History Search";
        params[2] = getSql(getGlobalCompanyCode());
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        params[6] = scrnMsg.P;
        setArgForSubScreen(params);
    }

    private String getSql(String globalCompanyCode) {
        StringBuilder sql = new StringBuilder();
        String dateFormat = ZYPDateUtil.getDateFormatString(true);

        sql.append("SELECT");
        sql.append(" PL.OLD_PRC_LIST_NM");
        sql.append(",PL.NEW_PRC_LIST_NM");
        sql.append(",PL.RQST_USR_ID");
        sql.append(",AP.FIRST_NM ||',' || AP.LAST_NM AS USR_NM");
        sql.append("    ,TO_CHAR(TO_DATE(PL.RQST_CRAT_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS RQST_CRAT_DT ");

        sql.append(",CR.COPY_RSLT_TP_DESC_TXT");
        sql.append(",PL.COPY_RSLT_CMNT_TXT");
        sql.append(",PL.GLBL_CMPY_CD");
        sql.append(",PL.EZCANCELFLAG");
        sql.append(" FROM");
        sql.append(" COPY_PRC_LIST_RQST PL");
        sql.append(",AUTH_PSN AP");
        sql.append(",COPY_RSLT_TP CR");
        sql.append(" WHERE");
        sql.append("    PL.GLBL_CMPY_CD = '");
        sql.append(globalCompanyCode);
        sql.append("'");
        sql.append("    AND PL.EZCANCELFLAG = '0'");
        sql.append("    AND PL.RQST_USR_ID = AP.USR_NM(+)");
        sql.append("    AND PL.GLBL_CMPY_CD = AP.GLBL_CMPY_CD(+)");
        sql.append("    AND AP.EZCANCELFLAG(+) = '0'");

        sql.append("    AND PL.COPY_RSLT_TP_CD = CR.COPY_RSLT_TP_CD");
        sql.append("    AND PL.GLBL_CMPY_CD = CR.GLBL_CMPY_CD");
        sql.append("    AND CR.EZCANCELFLAG = '0'");

        return sql.toString();
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[4];
        sortObj1[0] = "RQST_CRAT_DT";
        sortObj1[1] = "DESC";
        sortList.add(sortObj1);
        return sortList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[4];
        colObj1[0] = "From Price List";
        colObj1[1] = "OLD_PRC_LIST_NM";
        colObj1[2] = new BigDecimal("20");
        colObj1[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj1);

        Object[] colObj2 = new Object[4];
        colObj2[0] = "New Price List Name";
        colObj2[1] = "NEW_PRC_LIST_NM";
        colObj2[2] = new BigDecimal("20");
        colObj2[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj2);

        Object[] colObj3 = new Object[4];
        colObj3[0] = "Employ Name";
        colObj3[1] = "USR_NM";
        colObj3[2] = new BigDecimal("15");
        colObj3[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj3);

        Object[] colObj4 = new Object[4];
        colObj4[0] = "Request Date";
        colObj4[1] = "RQST_CRAT_DT";
        colObj4[2] = new BigDecimal("10");
        colObj4[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj4);

        Object[] colObj5 = new Object[5];
        colObj5[0] = "Request Status";
        colObj5[1] = "COPY_RSLT_TP_DESC_TXT";
        colObj5[2] = new BigDecimal("10");
        colObj5[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj5);

        Object[] colObj6 = new Object[6];
        colObj6[0] = "Request Fail Reason";
        colObj6[1] = "COPY_RSLT_CMNT_TXT";
        colObj6[2] = new BigDecimal("17");
        colObj6[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj6);

        return colList;
    }

    private List<Object> getSearchConditionSetting(NMAL7020BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[4];
        whereObj1[0] = "From Price List";
        whereObj1[1] = "OLD_PRC_LIST_NM";
        whereObj1[2] = null;
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        Object[] whereObj2 = new Object[4];
        whereObj2[0] = "New Price List Name";
        whereObj2[1] = "NEW_PRC_LIST_NM";
        whereObj2[2] = null;
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        Object[] whereObj3 = new Object[4];
        whereObj3[0] = "Employ ID";
        whereObj3[1] = "RQST_USR_ID";
        whereObj3[2] = null;
        whereObj3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj3);

        Object[] whereObj4 = new Object[4];
        whereObj4[0] = "Employ Name";
        whereObj4[1] = "USR_NM";
        whereObj4[2] = null;
        whereObj4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj4);

        Object[] whereObj5 = new Object[4];
        whereObj5[0] = "Request Date";
        whereObj5[1] = "RQST_CRAT_DT";
        whereObj5[2] = null;
        whereObj5[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj5);

        Object[] whereObj6 = new Object[4];
        whereObj6[0] = "Request Status";
        whereObj6[1] = "COPY_RSLT_TP_DESC_TXT";
        whereObj6[2] = null;
        whereObj6[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj6);

        Object[] whereObj7 = new Object[4];
        whereObj7[0] = "Request Fail Reason";
        whereObj7[1] = "COPY_RSLT_CMNT_TXT";
        whereObj7[2] = null;
        whereObj7[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj7);

        return whereList;
    }
}

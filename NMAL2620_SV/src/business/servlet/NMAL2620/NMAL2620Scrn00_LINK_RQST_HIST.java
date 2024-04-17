/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2620;

import static business.servlet.NMAL2620.constant.NMAL2620Constant.BUSINESS_ID;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   Hitachi         T.Mizuki        Create          N/A
 * 2013/04/26   Fujitsu         C.Yokoi         Update          CSA-QC#7589
 * 2013/05/09   Fujitsu         C.Yokoi         Update          CSA-QC#7693
 * 2016/07/13   Fujitsu         R.Nakamura      Update          CSA-QC#11069
 *</pre>
 */
public class NMAL2620Scrn00_LINK_RQST_HIST extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        // Mod Start 2016/07/13 QC#11069
        String[] params = new String[1];
//        params[0] = "";
//        params[1] = POPUP_WINDOW_TITLE;
//        params[2] = getSql(getGlobalCompanyCode());
//        params[NUM_3] = getSearchConditionSetting();
//        params[NUM_4] = getDisplayColumnSetting();
//        params[NUM_5] = getSortSetting();
//        params[NUM_6] = null;
        params[0] = BUSINESS_ID;
        // Mod End 2016/07/13 QC#11069

        setArgForSubScreen(params);
    }

    // Del Start 2016/07/13 QC#11069
//    private String getSql(String globalCompanyCode) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(" SELECT ");
//        sb.append("     A.TRTY_UPD_RQST_HDR_PK "); // 2016/04/26 CSA-QC#7589 Mod
//        sb.append("    ,A.RQST_USR_ID ");
//        sb.append("    ,B.FIRST_NM || ' ' || B.LAST_NM AS RQST_USR_NM "); // 2016/04/26 CSA-QC#7589 Mod
//        sb.append("    ,TO_CHAR( TO_TIMESTAMP(A.RQST_CRAT_TS, 'YYYYMMDDHH24MISSFF3'),'MM/DD/YYYY HH24:MI:SS') AS RQST_CRAT_TS"); // 2016/05/09 CSA-QC#7693 Mod
//        sb.append("    ,A.RQST_RSLT_CMNT_TXT ");
//        sb.append("    ,A.GLBL_CMPY_CD ");
//        sb.append("    ,A.EZCANCELFLAG ");
//
//        // 2016/04/26 CSA-QC#7589 Delete Start
//        // sb.append("    ,B.FIRST_NM ");
//        // sb.append("    ,B.LAST_NM ");
//        // 2016/04/26 CSA-QC#7589 Delete End
//        sb.append("    ,C.RQST_RSLT_TP_DESC_TXT ");
//        sb.append("    ,D.RQST_CRAT_SYS_TP_DESC_TXT ");
//        sb.append(" FROM ");
//        sb.append("     TRTY_UPD_RQST_HDR A ");
//        sb.append("    ,AUTH_PSN B ");
//        sb.append("    ,RQST_RSLT_TP C ");
//        sb.append("    ,RQST_CRAT_SYS_TP D ");
//        sb.append(" WHERE  ");
//        sb.append("     A.GLBL_CMPY_CD = '");
//        sb.append(globalCompanyCode);
//        sb.append("'");
//        sb.append("     AND A.GLBL_CMPY_CD = B.GLBL_CMPY_CD");
//        sb.append("     AND A.RQST_USR_ID = B.USR_NM"); // 2016/04/26 CSA-QC#7589 Mod
//        sb.append("     AND A.GLBL_CMPY_CD = C.GLBL_CMPY_CD");
//        sb.append("     AND A.RQST_RSLT_TP_CD = C.RQST_RSLT_TP_CD");
//        sb.append("     AND A.GLBL_CMPY_CD = D.GLBL_CMPY_CD");
//        sb.append("     AND A.RQST_CRAT_SYS_TP_CD = D.RQST_CRAT_SYS_TP_CD");
//        sb.append("     AND A.EZCANCELFLAG = '0'");
//        sb.append("     AND B.EZCANCELFLAG = '0'");
//        sb.append("     AND C.EZCANCELFLAG = '0'");
//        sb.append("     AND D.EZCANCELFLAG = '0'");
//        String sql = sb.toString();
//        return sql;
//    }
//
//    private List<Object> getSortSetting() {
//        List<Object> sortList = new ArrayList<Object>();
//        Object[] sortObj1 = new Object[NUM_4];
//        sortObj1[0] = "RQST_CRAT_TS";
//        sortObj1[1] = "DESC";
//        sortList.add(sortObj1);
//        return sortList;
//    }
//
//    private List<Object> getDisplayColumnSetting() {
//        List<Object> colList = new ArrayList<Object>();
//
//        Object[] colObj1 = new Object[NUM_4];
//        colObj1[0] = "Request ID";
//        colObj1[1] = "TRTY_UPD_RQST_HDR_PK";
//        colObj1[2] = new BigDecimal("8");
//        colObj1[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj1);
//
//        Object[] colObj2 = new Object[NUM_4];
//        colObj2[0] = "Employee ID";
//        colObj2[1] = "RQST_USR_ID";
//        colObj2[2] = new BigDecimal("8");
//        colObj2[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj2);
//
//        Object[] colObj3 = new Object[NUM_4];
//        colObj3[0] = "Employee Name";
//        colObj3[1] = "RQST_USR_NM";
//        colObj3[2] = new BigDecimal("10");
//        colObj3[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj3);
//
//        Object[] colObj4 = new Object[NUM_4];
//        colObj4[0] = "Request Date";
//        colObj4[1] = "RQST_CRAT_TS";
//        colObj4[2] = new BigDecimal("17");
//        colObj4[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj4);
//
//        Object[] colObj5 = new Object[NUM_4];
//        colObj5[0] = "Request Status";
//        colObj5[1] = "RQST_RSLT_TP_DESC_TXT";
//        colObj5[2] = new BigDecimal("15");
//        colObj5[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj5);
//
//        Object[] colObj6 = new Object[NUM_4];
//        colObj6[0] = "Request Type";
//        colObj6[1] = "RQST_CRAT_SYS_TP_DESC_TXT";
//        colObj6[2] = new BigDecimal("12");
//        colObj6[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj6);
//
//        Object[] colObj7 = new Object[NUM_4];
//        colObj7[0] = "Mass Update Reason";
//        colObj7[1] = "RQST_RSLT_CMNT_TXT";
//        colObj7[2] = new BigDecimal("22");
//        colObj7[NUM_3] = ZYPConstant.FLG_OFF_N;
//        colList.add(colObj7);
//
//        return colList;
//    }
//
//    private List<Object> getSearchConditionSetting() {
//        List<Object> whereList = new ArrayList<Object>();
//
//        Object[] whereObj1 = new Object[NUM_4];
//        whereObj1[0] = "Request ID";
//        whereObj1[1] = "TRTY_UPD_RQST_HDR_PK";
//        whereObj1[2] = null;
//        whereObj1[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj1);
//
//        Object[] whereObj2 = new Object[NUM_4];
//        whereObj2[0] = "Employee ID";
//        whereObj2[1] = "RQST_USR_ID";
//        whereObj2[2] = null;
//        whereObj2[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj2);
//
//        Object[] whereObj3 = new Object[NUM_4];
//        whereObj3[0] = "Employee Name";
//        whereObj3[1] = "RQST_USR_NM";
//        whereObj3[2] = null;
//        whereObj3[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj3);
//
//        Object[] whereObj4 = new Object[NUM_4];
//        whereObj4[0] = "Request Date";
//        whereObj4[1] = "RQST_CRAT_TS";
//        whereObj4[2] = null;
//        whereObj4[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj4);
//
//        Object[] whereObj5 = new Object[NUM_4];
//        whereObj5[0] = "Request Status";
//        whereObj5[1] = "RQST_RSLT_TP_DESC_TXT";
//        whereObj5[2] = null;
//        whereObj5[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj5);
//
//        Object[] whereObj6 = new Object[NUM_4];
//        whereObj6[0] = "Request Type";
//        whereObj6[1] = "RQST_CRAT_SYS_TP_DESC_TXT";
//        whereObj6[2] = null;
//        whereObj6[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj6);
//
//        Object[] whereObj7 = new Object[NUM_4];
//        whereObj7[0] = "Mass Update Reason";
//        whereObj7[1] = "RQST_RSLT_CMNT_TXT";
//        whereObj7[2] = null;
//        whereObj7[NUM_3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereObj7);
//
//        return whereList;
//    }
    // Del End 2016/07/13 QC#11069
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/02/03   Hitachi         T.Tomita        Update          QC#3312
 *</pre>
 */
public class NSAL0810Scrn00_OpenWin_Machine extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        // START 2016/02/03 T.Tomita [QC#3312, MOD]
//        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
        setArgForSubScreen(setConfigSearchPopUpInputParam(ctx, scrnMsg));
        // END 2016/02/03 T.Tomita [QC#3312, MOD]
    }

    // START 2016/02/03 T.Tomita [QC#3312, DEL]
//    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0810BMsg scrnMsg) {
//        ZYPTableUtil.clear(scrnMsg.X);
//        Object[] params = new Object[LENGTH_7];
//
//        int i = 0;
//        // Return value suffix
//        params[i++] = "";
//        // Window title
//        params[i++] = "Install Base ID Search Popup";
//        // Table SQL
//        params[i++] = getSqlStr(scrnMsg);
//        // Where List
//        params[i++] = getWhereList(scrnMsg);
//        // Column List
//        List<Object[]> columnList = getColumnList();
//        params[i++] = columnList;
//        // Sort Condition List
//        params[i++] = getSortConditionList();
//        // outPut List
//        params[i++] = scrnMsg.X;
//
//        return params;
//    }
//
//    private static String getSqlStr(NSAL0810BMsg scrnMsg) {
//        StringBuilder sql = new StringBuilder();
//
//        sql.append(" SELECT");
//        sql.append("      SMM.GLBL_CMPY_CD");
//        sql.append("     ,SMM.EZCANCELFLAG");
//        sql.append("     ,SMM.SER_NUM");
//        sql.append("     ,SMM.SVC_CONFIG_MSTR_PK");
//        sql.append("     ,SMM.SVC_MACH_MSTR_PK");
//        sql.append("     ,SMM.SVC_MACH_MSTR_STS_CD");
//        sql.append("     ,SMM.OWNR_ACCT_NUM");
//        sql.append("     ,SMM.OWNR_LOC_NUM");
//        sql.append("     ,SMM.BILL_TO_ACCT_NUM");
//        sql.append("     ,SMM.BILL_TO_LOC_NUM");
//        sql.append("     ,SMM.CUR_LOC_ACCT_NUM");
//        sql.append("     ,SMM.CUR_LOC_NUM");
//        sql.append("     ,MN.T_MDL_NM");
//        sql.append("     ,SMM.MDSE_CD");
//        sql.append("     ,M.MDSE_NM");
//        sql.append(" FROM");
//        sql.append("      SVC_MACH_MSTR      SMM");
//        sql.append("     ,SVC_CONFIG_MSTR    SCM");
//        sql.append("     ,MDL_NM             MN");
//        sql.append("     ,MDSE               M");
//        sql.append(" WHERE");
//        sql.append("         SMM.EZCANCELFLAG        = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD = '");
//        sql.append(scrnMsg.glblCmpyCd.getValue());
//        sql.append("'    AND SMM.GLBL_CMPY_CD        = SCM.GLBL_CMPY_CD");
//        sql.append("     AND SMM.SVC_CONFIG_MSTR_PK  = SCM.SVC_CONFIG_MSTR_PK");
//        sql.append("     AND SCM.EZCANCELFLAG        = '0'");
//        sql.append("     AND SCM.GLBL_CMPY_CD        = MN.T_GLBL_CMPY_CD");
//        sql.append("     AND SCM.MDL_ID              = MN.T_MDL_ID");
//        sql.append("     AND MN.EZCANCELFLAG         = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD        = M.GLBL_CMPY_CD");
//        sql.append("     AND SMM.MDSE_CD             = M.MDSE_CD");
//        sql.append("     AND M.EZCANCELFLAG          = '0'");
//
//        return sql.toString();
//    }
//
//    private List<Object[]> getWhereList(NSAL0810BMsg scrnMsg) {
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Serial#";
//        h0[WLIST_OBJ_ID] = "SER_NUM";
//        h0[WLIST_OBJ_VALUE] = "";
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
//        h1[WLIST_DSP_OBJ_NM] = "Config ID";
//        h1[WLIST_OBJ_ID] = "SVC_CONFIG_MSTR_PK";
//        h1[WLIST_OBJ_VALUE] = "";
//        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h1);
//
//        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
//        h2[WLIST_DSP_OBJ_NM] = "Machine ID";
//        h2[WLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
//        h2[WLIST_OBJ_VALUE] = "";
//        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h2);
//
//        Object[] h3 = new Object[ATTR_NUM_WHERE_LIST];
//        h3[WLIST_DSP_OBJ_NM] = "Machine Status";
//        h3[WLIST_OBJ_ID] = "SVC_MACH_MSTR_STS_CD";
//        h3[WLIST_OBJ_VALUE] = "";
//        h3[WLIST_WHERE_FLG] = ZYPConstant.FLG_OFF_N;
//        whereList.add(h3);
//
//        Object[] h4 = new Object[ATTR_NUM_WHERE_LIST];
//        h4[WLIST_DSP_OBJ_NM] = "Account#";
//        h4[WLIST_OBJ_ID] = "OWNR_ACCT_NUM";
//        h4[WLIST_OBJ_VALUE] = "";
//        h4[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h4);
//
//        Object[] h5 = new Object[ATTR_NUM_WHERE_LIST];
//        h5[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h5[WLIST_OBJ_ID] = "OWNR_LOC_NUM";
//        h5[WLIST_OBJ_VALUE] = "";
//        h5[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h5);
//
//        Object[] h6 = new Object[ATTR_NUM_WHERE_LIST];
//        h6[WLIST_DSP_OBJ_NM] = "Account#";
//        h6[WLIST_OBJ_ID] = "BILL_TO_ACCT_NUM";
//        h6[WLIST_OBJ_VALUE] = "";
//        h6[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h6);
//
//        Object[] h7 = new Object[ATTR_NUM_WHERE_LIST];
//        h7[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h7[WLIST_OBJ_ID] = "BILL_TO_LOC_NUM";
//        h7[WLIST_OBJ_VALUE] = "";
//        h7[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h7);
//
//        Object[] h8 = new Object[ATTR_NUM_WHERE_LIST];
//        h8[WLIST_DSP_OBJ_NM] = "Account#";
//        h8[WLIST_OBJ_ID] = "CUR_LOC_ACCT_NUM";
//        h8[WLIST_OBJ_VALUE] = "";
//        h8[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h8);
//
//        Object[] h9 = new Object[ATTR_NUM_WHERE_LIST];
//        h9[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h9[WLIST_OBJ_ID] = "CUR_LOC_NUM";
//        h9[WLIST_OBJ_VALUE] = "";
//        h9[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h9);
//
//        Object[] h10 = new Object[ATTR_NUM_WHERE_LIST];
//        h10[WLIST_DSP_OBJ_NM] = "Service Model";
//        h10[WLIST_OBJ_ID] = "T_MDL_NM";
//        h10[WLIST_OBJ_VALUE] = "";
//        h10[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h10);
//
//        Object[] h11 = new Object[ATTR_NUM_WHERE_LIST];
//        h11[WLIST_DSP_OBJ_NM] = "Mdse Cd";
//        h11[WLIST_OBJ_ID] = "MDSE_CD";
//        h11[WLIST_OBJ_VALUE] = "";
//        h11[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h11);
//
//        return whereList;
//    }
//
//    private List<Object[]> getColumnList() {
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//
//        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
//        c0[CLIST_DSP_OBJ_NM] = "Serial#";
//        c0[CLIST_OBJ_ID] = "SER_NUM";
//        c0[CLIST_OBJ_LENGTH] = new BigDecimal(SER_NUM_LENGTH);
//        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
//        columnList.add(c0);
//
//        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
//        c1[CLIST_DSP_OBJ_NM] = "Mdse Cd";
//        c1[CLIST_OBJ_ID] = "MDSE_CD";
//        c1[CLIST_OBJ_LENGTH] = new BigDecimal(MDSE_CD_LENGTH);
//        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c1);
//
//        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
//        c2[CLIST_DSP_OBJ_NM] = "Mdse Name";
//        c2[CLIST_OBJ_ID] = "MDSE_NM";
//        c2[CLIST_OBJ_LENGTH] = new BigDecimal(MDSE_NM_LENGTH);
//        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c2);
//
//        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
//        c3[CLIST_DSP_OBJ_NM] = "Machine Status";
//        c3[CLIST_OBJ_ID] = "SVC_MACH_MSTR_STS_CD";
//        c3[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_MACH_MSTR_STS_CD_LENGTH);
//        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c3);
//
//        Object[] c4 = new Object[ATTR_NUM_CLMN_LIST];
//        c4[CLIST_DSP_OBJ_NM] = "Service Model";
//        c4[CLIST_OBJ_ID] = "T_MDL_NM";
//        c4[CLIST_OBJ_LENGTH] = new BigDecimal(T_MDL_NM_LENGTH);
//        c4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c4);
//
//        Object[] c5 = new Object[ATTR_NUM_CLMN_LIST];
//        c5[CLIST_DSP_OBJ_NM] = "Config ID";
//        c5[CLIST_OBJ_ID] = "SVC_CONFIG_MSTR_PK";
//        c5[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_CONFIG_MSTR_PK_LENGTH);
//        c5[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c5);
//
//        Object[] c6 = new Object[ATTR_NUM_CLMN_LIST];
//        c6[CLIST_DSP_OBJ_NM] = "Machine ID";
//        c6[CLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
//        c6[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_MACH_MSTR_PK_LENGTH);
//        c6[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c6);
//
//        return columnList;
//    }
//
//    private List<Object[]> getSortConditionList() {
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray = new Object[2];
//        sortConditionArray[0] = "SER_NUM";
//        sortConditionArray[1] = "ASC";
//        sortConditionList.add(sortConditionArray);
//
//        sortConditionArray = new Object[2];
//        sortConditionArray[0] = "MDSE_CD";
//        sortConditionArray[1] = "ASC";
//        sortConditionList.add(sortConditionArray);
//
//        return sortConditionList;
//    }
    // END 2016/02/03 T.Tomita [QC#3312, DEL]

    // START 2016/02/03 T.Tomita [QC#3312, ADD]
    private Object[] setConfigSearchPopUpInputParam(EZDApplicationContext ctx, NSAL0810BMsg scrnMsg) {
        ZYPTableUtil.clear(scrnMsg.O);
        scrnMsg.O.setValidCount(0);
        Object[] params = new Object[PARAM_LENGTH_NSAL1240];
        params[PARAM_INDEX_30] = scrnMsg.O;
        return params;
    }
    // END 2016/02/03 T.Tomita [QC#3312, ADD]
}

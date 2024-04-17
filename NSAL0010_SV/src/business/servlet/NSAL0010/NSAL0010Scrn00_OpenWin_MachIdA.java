/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Solution Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/12/03   Hitachi         K.Yamada        Update          CSA QC#950
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4889
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_MachIdA extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        // START 2016/02/04 T.Tomita [QC#3312, MOD]
//        setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
        // START 2016/04/04 M.Gotou [QC#4889, MOD]
        setValue(scrnMsg.xxScrEventNm, OPENWIN_MACHIDA);
        setArgForSubScreen(NSAL0010CommonLogic.setConfigSearchPopUpInputParam(scrnMsg));
        // END 2016/04/04 M.Gotou [QC#4889, MOD]
        // END 2016/02/04 T.Tomita [QC#3312, MOD]
    }

    // START 2016/02/04 T.Tomita [QC#3312, DEL]
//    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0010BMsg scrnMsg) {
//        ZYPTableUtil.clear(scrnMsg.X);
//        setValue(scrnMsg.xxScrEventNm, OPENWIN_MACHIDA);
//        Object[] params = new Object[7];
//
//        int i = 0;
//        // Return value suffix
//        params[i++] = "";
//        // Window title
//        params[i++] = "Install Base ID Search Popup";
//        // Table SQL
//        params[i++] = getSqlStr();
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
//    private static String getSqlStr() {
//        StringBuilder sql = new StringBuilder();
//
//        sql.append(" SELECT");
//        sql.append("      SMM.GLBL_CMPY_CD");
//        sql.append("     ,SMM.EZCANCELFLAG");
//        sql.append("     ,SMM.SER_NUM");
//        sql.append("     ,SMM.SVC_MACH_MSTR_PK");
//        sql.append("     ,SMM.SVC_CONFIG_MSTR_PK");
//        sql.append("     ,SMM.SVC_MACH_TP_CD");
//        sql.append("     ,SMM.SVC_MACH_MSTR_STS_CD");
//        sql.append("     ,SMMS.SVC_MACH_MSTR_STS_DESC_TXT");
//        sql.append("     ,SMM.OWNR_ACCT_NUM");
//        sql.append("     ,SMM.OWNR_LOC_NUM");
//        sql.append("     ,SMM.BILL_TO_ACCT_NUM");
//        sql.append("     ,SMM.BILL_TO_LOC_NUM");
//        sql.append("     ,SMM.CUR_LOC_ACCT_NUM");
//        sql.append("     ,SMM.CUR_LOC_NUM");
//        sql.append("     ,SMM.MDSE_CD");
//        sql.append("     ,M.MDSE_NM");
//        sql.append(" FROM");
//        sql.append("      SVC_MACH_MSTR      SMM");
//        sql.append("     ,MDSE               M");
//        sql.append("     ,SVC_MACH_MSTR_STS  SMMS");
//        sql.append(" WHERE");
//        sql.append("         SMM.EZCANCELFLAG            = '0'");
//        sql.append("     AND SMM.SVC_CONFIG_MSTR_PK      IS NULL");
//        sql.append("     AND SMM.GLBL_CMPY_CD            = M.GLBL_CMPY_CD");
//        sql.append("     AND SMM.MDSE_CD                 = M.MDSE_CD");
//        sql.append("     AND M.EZCANCELFLAG              = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD            = SMMS.GLBL_CMPY_CD");
//        sql.append("     AND SMM.SVC_MACH_MSTR_STS_CD    = SMMS.SVC_MACH_MSTR_STS_CD");
//        sql.append("     AND SMMS.EZCANCELFLAG           = '0'");
//
//        return sql.toString();
//    }
//
//    private List<Object[]> getWhereList(NSAL0010BMsg scrnMsg) {
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Serial#";
//        h0[WLIST_OBJ_ID] = "SER_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h0[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
//        h1[WLIST_DSP_OBJ_NM] = "Machine ID";
//        h1[WLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
//        // mod start 2015/12/03 CSA Defect#950
//        h1[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h1);
//
//        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
//        h2[WLIST_DSP_OBJ_NM] = "Machine Status";
//        h2[WLIST_OBJ_ID] = "SVC_MACH_MSTR_STS_CD";
//        // mod start 2015/12/03 CSA Defect#950
//        h2[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_OFF_N;
//        whereList.add(h2);
//
//        Object[] h3 = new Object[ATTR_NUM_WHERE_LIST];
//        h3[WLIST_DSP_OBJ_NM] = "Account#";
//        h3[WLIST_OBJ_ID] = "OWNR_ACCT_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h3[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h3[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h3);
//
//        Object[] h4 = new Object[ATTR_NUM_WHERE_LIST];
//        h4[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h4[WLIST_OBJ_ID] = "OWNR_LOC_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h4[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h4[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h4);
//
//        Object[] h5 = new Object[ATTR_NUM_WHERE_LIST];
//        h5[WLIST_DSP_OBJ_NM] = "Account#";
//        h5[WLIST_OBJ_ID] = "BILL_TO_ACCT_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h5[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h5[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h5);
//
//        Object[] h6 = new Object[ATTR_NUM_WHERE_LIST];
//        h6[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h6[WLIST_OBJ_ID] = "BILL_TO_LOC_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h6[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h6[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h6);
//
//        Object[] h7 = new Object[ATTR_NUM_WHERE_LIST];
//        h7[WLIST_DSP_OBJ_NM] = "Account#";
//        h7[WLIST_OBJ_ID] = "CUR_LOC_ACCT_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h7[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h7[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h7);
//
//        Object[] h8 = new Object[ATTR_NUM_WHERE_LIST];
//        h8[WLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        h8[WLIST_OBJ_ID] = "CUR_LOC_NUM";
//        // mod start 2015/12/03 CSA Defect#950
//        h8[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h8[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h8);
//
//        Object[] h9 = new Object[ATTR_NUM_WHERE_LIST];
//        h9[WLIST_DSP_OBJ_NM] = "Mdse Cd";
//        h9[WLIST_OBJ_ID] = "MDSE_CD";
//        // mod start 2015/12/03 CSA Defect#950
//        h9[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h9[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h9);
//
//        Object[] h10 = new Object[ATTR_NUM_WHERE_LIST];
//        h10[WLIST_DSP_OBJ_NM] = "Mdse Name";
//        h10[WLIST_OBJ_ID] = "MDSE_NM";
//        // mod start 2015/12/03 CSA Defect#950
//        h10[WLIST_OBJ_VALUE] = "";
//        // mod end 2015/12/03 CSA Defect#950
//        h10[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h10);
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
//        c4[CLIST_DSP_OBJ_NM] = "Machine ID";
//        c4[CLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
//        c4[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_MACH_MSTR_PK_LENGTH);
//        c4[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c4);
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
    // END 2016/02/04 T.Tomita [QC#3312, DEL]
}

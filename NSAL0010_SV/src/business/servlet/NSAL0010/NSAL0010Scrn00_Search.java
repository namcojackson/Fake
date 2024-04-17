/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0010.NSAL0010CMsg;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;
import business.servlet.NSAL0010.constant.NSAL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/14   Hitachi         K.Kasai         Create          N/A
 * 2015/11/16   Hitachi         T.Tomita        Update          QC#647
 * 2016/02/04   Hitachi         T.Tomita        Update          QC#3312
 * 2016/04/04   Hitachi         M.Gotou         Update          QC#4889
 * 2016/05/12   Hitachi         M.Gotou         Update          QC#7856
 * 2017/01/27   Hitachi         K.Kojima        Update          QC#16600
 *</pre>
 */
public class NSAL0010Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.svcMachMstrPk_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.serNum_H1.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.serNum_H1);
            }
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;

        scrnMsg.xxDplyTab_01.setValue(TAB_MACH_CONFIG);

        NSAL0010CMsg bizMsg = new NSAL0010CMsg();
        bizMsg.setBusinessID(NSAL0010Constant.BUSINESS_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);
        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0010BMsg scrnMsg = (NSAL0010BMsg) bMsg;
        NSAL0010CMsg bizMsg = (NSAL0010CMsg) cMsg;

        this.setResult("no");
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2015/11/16 T.Tomita [QC#647, MOD]
        if ("E".equals(bizMsg.getMessageKind())) {
            return;
        }
        // END 2015/11/16 T.Tomita [QC#647, MOD]

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSetFlg_MM.getValue())) {
            //move to serial inquiry popup
            this.setResult("yes");
            // START 2016/02/04 T.Tomita [QC#3312, MOD]
//            setArgForSubScreen(setCommonPopUpParam(ctx, scrnMsg));
            // START 2016/04/04 M.Gotou [QC#4889, MOD]
            setValue(scrnMsg.xxScrEventNm, SEARCH);
            // START 2017/01/27 K.Kojima [QC#16600,MOD]
            // setArgForSubScreen(NSAL0010CommonLogic.setConfigSearchPopUpInputParam(scrnMsg));
            setArgForSubScreen(NSAL0010CommonLogic.setConfigSearchPopUpInputParam(scrnMsg, MODE_01, true));
            // END 2017/01/27 K.Kojima [QC#16600,MOD]
            // END 2016/04/04 M.Gotou [QC#4889, MOD]
            return;
            // END 2016/02/04 T.Tomita [QC#3312, MOD]
        }
        NSAL0010CommonLogic.controlScreenFields(this, scrnMsg, bizMsg.getUserID(), true, getUserProfileService());
        scrnMsg.serNum_AC.setInputProtected(true);
        scrnMsg.serNum_H1.setInputProtected(true);
        // START 2016/05/12 M.Gotou [QC#7856, MOD]
        List<String> functionIds = getUserProfileService().getFunctionCodeListForBizAppId(BUSINESS_ID);
        if (!functionIds.contains(FUNC_ID_MDSE_EDIT)) {
            scrnMsg.mdseCd_AC.setInputProtected(true);
            scrnMsg.mdseCd_H1.setInputProtected(true);
        }
        scrnMsg.setFocusItem(scrnMsg.serNum_H1);
        // END 2016/05/12 M.Gotou [QC#7856, MOD]

    }

    // START 2016/02/04 T.Tomita [QC#3312, DEL]
//    private Object[] setCommonPopUpParam(EZDApplicationContext ctx, NSAL0010BMsg scrnMsg) {
//        ZYPTableUtil.clear(scrnMsg.X);
//        setValue(scrnMsg.xxScrEventNm, SEARCH);
//        Object[] params = new Object[7];
//
//        int i = 0;
//        // Return value suffix
//        params[i++] = "";
//        // Window title
//        params[i++] = "Serial Number Search Popup";
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
//        sql.append("     ,SMM.MDSE_CD");
//        sql.append("     ,M.MDSE_NM");
//        sql.append("     ,MN.T_MDL_NM");
//        sql.append("     ,SMM.SVC_MACH_MSTR_PK");
//        sql.append("     ,SMM.SVC_MACH_MSTR_STS_CD");
//        sql.append("     ,DC.DS_CONTR_NUM");
//        sql.append("     ,SMM.CPO_ORD_NUM");
//        sql.append("     ,SMM.CUR_LOC_ACCT_NUM");
//        sql.append("     ,SMM.CUR_LOC_NUM");
//        sql.append(" FROM");
//        sql.append("      SVC_MACH_MSTR      SMM");
//        sql.append("     ,SVC_CONFIG_MSTR    SCM");
//        sql.append("     ,MDL_NM             MN");
//        sql.append("     ,MDSE               M");
//        sql.append("     ,DS_CONTR_DTL       DCD");
//        sql.append("     ,DS_CONTR           DC");
//        sql.append(" WHERE");
//        sql.append("         SMM.EZCANCELFLAG        = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD        = SCM.GLBL_CMPY_CD");
//        sql.append("     AND SMM.SVC_CONFIG_MSTR_PK  = SCM.SVC_CONFIG_MSTR_PK");
//        sql.append("     AND SCM.EZCANCELFLAG        = '0'");
//        sql.append("     AND SCM.GLBL_CMPY_CD        = MN.T_GLBL_CMPY_CD");
//        sql.append("     AND SCM.MDL_ID              = MN.T_MDL_ID");
//        sql.append("     AND MN.EZCANCELFLAG         = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD        = M.GLBL_CMPY_CD");
//        sql.append("     AND SMM.MDSE_CD             = M.MDSE_CD");
//        sql.append("     AND M.EZCANCELFLAG          = '0'");
//        sql.append("     AND SMM.GLBL_CMPY_CD        = DCD.GLBL_CMPY_CD");
//        sql.append("     AND SMM.SVC_MACH_MSTR_PK    = DCD.SVC_MACH_MSTR_PK");
//        sql.append("     AND DCD.EZCANCELFLAG        = '0'");
//        sql.append("     AND DCD.GLBL_CMPY_CD        = DC.GLBL_CMPY_CD");
//        sql.append("     AND DCD.DS_CONTR_PK         = DC.DS_CONTR_PK");
//        sql.append("     AND DC.EZCANCELFLAG         = '0'");
//
//        return sql.toString();
//    }
//
//    private List<Object[]> getWhereList(NSAL0010BMsg scrnMsg) {
//        List<Object[]> whereList = new ArrayList<Object[]>();
//
//        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
//        h0[WLIST_DSP_OBJ_NM] = "Serial#";
//        h0[WLIST_OBJ_ID] = "SER_NUM";
//        h0[WLIST_OBJ_VALUE] = scrnMsg.prntSerNum_H1.getValue();
//        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h0);
//
//        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
//        h1[WLIST_DSP_OBJ_NM] = "Service Model";
//        h1[WLIST_OBJ_ID] = "T_MDL_NM";
//        h1[WLIST_OBJ_VALUE] = "";
//        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h1);
//
//        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
//        h2[WLIST_DSP_OBJ_NM] = "Mdse Cd";
//        h2[WLIST_OBJ_ID] = "MDSE_CD";
//        h2[WLIST_OBJ_VALUE] = "";
//        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h2);
//
//        Object[] h3 = new Object[ATTR_NUM_WHERE_LIST];
//        h3[WLIST_DSP_OBJ_NM] = "Mdse Name";
//        h3[WLIST_OBJ_ID] = "MDSE_NM";
//        h3[WLIST_OBJ_VALUE] = "";
//        h3[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h3);
//
//        Object[] h4 = new Object[ATTR_NUM_WHERE_LIST];
//        h4[WLIST_DSP_OBJ_NM] = "Machine ID";
//        h4[WLIST_OBJ_ID] = "SVC_MACH_MSTR_PK";
//        h4[WLIST_OBJ_VALUE] = "";
//        h4[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h4);
//
//        Object[] h5 = new Object[ATTR_NUM_WHERE_LIST];
//        h5[WLIST_DSP_OBJ_NM] = "Machine Status";
//        h5[WLIST_OBJ_ID] = "SVC_MACH_MSTR_STS_CD";
//        h5[WLIST_OBJ_VALUE] = "";
//        h5[WLIST_WHERE_FLG] = ZYPConstant.FLG_OFF_N;
//        whereList.add(h5);
//
//        Object[] h6 = new Object[ATTR_NUM_WHERE_LIST];
//        h6[WLIST_DSP_OBJ_NM] = "Contract#";
//        h6[WLIST_OBJ_ID] = "DS_CONTR_NUM";
//        h6[WLIST_OBJ_VALUE] = "";
//        h6[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
//        whereList.add(h6);
//
//        Object[] h7 = new Object[ATTR_NUM_WHERE_LIST];
//        h7[WLIST_DSP_OBJ_NM] = "Order#";
//        h7[WLIST_OBJ_ID] = "CPO_ORD_NUM";
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
//        c1[CLIST_DSP_OBJ_NM] = "Service Model";
//        c1[CLIST_OBJ_ID] = "T_MDL_NM";
//        c1[CLIST_OBJ_LENGTH] = new BigDecimal(T_MDL_NM_LENGTH);
//        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c1);
//
//        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
//        c2[CLIST_DSP_OBJ_NM] = "Mdse Cd";
//        c2[CLIST_OBJ_ID] = "MDSE_CD";
//        c2[CLIST_OBJ_LENGTH] = new BigDecimal(MDSE_CD_LENGTH);
//        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c2);
//
//        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
//        c3[CLIST_DSP_OBJ_NM] = "Mdse Name";
//        c3[CLIST_OBJ_ID] = "MDSE_NM";
//        c3[CLIST_OBJ_LENGTH] = new BigDecimal(MDSE_NM_LENGTH);
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
//        Object[] c5 = new Object[ATTR_NUM_CLMN_LIST];
//        c5[CLIST_DSP_OBJ_NM] = "Machine Status";
//        c5[CLIST_OBJ_ID] = "SVC_MACH_MSTR_STS_CD";
//        c5[CLIST_OBJ_LENGTH] = new BigDecimal(SVC_MACH_MSTR_STS_CD_LENGTH);
//        c5[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
//        columnList.add(c5);
//
//        Object[] c6 = new Object[ATTR_NUM_CLMN_LIST];
//        c6[CLIST_DSP_OBJ_NM] = "Address(Loc#)";
//        c6[CLIST_OBJ_ID] = "CUR_LOC_NUM";
//        c6[CLIST_OBJ_LENGTH] = new BigDecimal(CUR_LOC_NUM_LENGTH);
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
    // END 2016/02/04 T.Tomita [QC#3312, DEL]
}

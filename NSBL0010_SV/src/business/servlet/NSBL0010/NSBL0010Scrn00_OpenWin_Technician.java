/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0010;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSBL0010.common.NSBL0010CommonLogic;
import business.servlet.NSBL0010.constant.NSBL0010Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/04/29   SRA             E.Inada         Create          N/A
 * 2013/09/05   SRA             E.Inada         Update          QC#2135
 * 2017/01/05   Hitachi         N.Arai          Update          QC#13901-2
 *</pre>
 */
public class NSBL0010Scrn00_OpenWin_Technician extends S21CommonHandler implements NSBL0010Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0010BMsg scrnMsg = (NSBL0010BMsg) bMsg;

// START 2017/01/05 N.Arai [QC#13901-2, MOD]
//        scrnMsg.xxPopPrm_P1.setValue("TECH_MSTR");
//        scrnMsg.xxPopPrm_P2.setValue("TECH_TOC_CD");
//        scrnMsg.xxPopPrm_P3.setValue("TECH_NM");
//        scrnMsg.xxPopPrm_P4.setValue("TECH_TOC_CD");
//        scrnMsg.xxPopPrm_P5.setValue("Technicial Code Search");
//        scrnMsg.xxPopPrm_P6.setValue("Technicial Code");
//        scrnMsg.xxPopPrm_P7.setValue("Technicial Name");
//        scrnMsg.xxPopPrm_P8.setValue("Technicial Code");
//        scrnMsg.xxPopPrm_P9.setValue("Technicial Name");
//        scrnMsg.xxPopPrm_PA.clear();
//
//        Object[] params = new Object[PARAM_11];
//        params[PARAM_0] = scrnMsg.xxPopPrm_P1;
//        params[PARAM_1] = scrnMsg.xxPopPrm_P2;
//        params[PARAM_2] = scrnMsg.xxPopPrm_P3;
//        params[PARAM_3] = scrnMsg.xxPopPrm_P4;
//        params[PARAM_4] = scrnMsg.xxPopPrm_P5;
//        params[PARAM_5] = scrnMsg.xxPopPrm_P6;
//        params[PARAM_6] = scrnMsg.xxPopPrm_P7;
//        params[PARAM_7] = scrnMsg.xxPopPrm_P8;
//        params[PARAM_8] = scrnMsg.xxPopPrm_P9;
//        params[PARAM_9] = scrnMsg.techCd;
//        params[PARAM_10] = scrnMsg.xxPopPrm_PA;
//
//        setArgForSubScreen(params);

        NSBL0010CommonLogic.clearPopupParameter(scrnMsg);

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        Object[] params = new Object[7];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Technician Popup";
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        params[i++] = getSqlStr(scrnMsg);
        // 3 : Lv2 : Where List (List: Search condition columns)
        params[i++] = getWhereList(scrnMsg);
        // 4 : Lv2 : Column List (List: Search result columns)
        List<Object[]> columnList = getColumnList();
        params[i++] = columnList;
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        params[i++] = getSortConditionList();
        // 6 : Lv2 : Output
        params[i++] = scrnMsg.Z;
        setArgForSubScreen(params);
    }
// END 2017/01/05 N.Arai [QC#13901-2, MOD]

 // START 2017/01/05 N.Arai [QC#13901-2, MOD]
    private String getSqlStr(NSBL0010BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     A.GLBL_CMPY_CD");
        sql.append("    ,A.EZCANCELFLAG");
        sql.append("    ,A.TECH_TOC_CD");
        sql.append("    ,A.TECH_NM");
        sql.append(" FROM ");
        sql.append("     TECH_MSTR A");
        sql.append(" WHERE");
        sql.append("        A.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("'   AND A.EZCANCELFLAG = '0'");
        sql.append("    AND EXISTS (");
        sql.append("        SELECT");
        sql.append("            1");
        sql.append("        FROM");
        sql.append("            ORG_FUNC_ASG B");
        sql.append("            ,TOC              T");
        sql.append("            ,ORG_FUNC_ROLE_TP OFRT");
        sql.append("        WHERE");
        sql.append("                B.GLBL_CMPY_CD = A.GLBL_CMPY_CD");
        sql.append("            AND B.PSN_CD       = A.TECH_TOC_CD");
        sql.append("            AND B.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("            AND NVL(B.EFF_THRU_DT, '").append(scrnMsg.slsDt.getValue()).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("            AND B.GLBL_CMPY_CD         = T.GLBL_CMPY_CD");
        sql.append("            AND B.TOC_CD               = T.TOC_CD");
        sql.append("            AND T.GLBL_CMPY_CD         = OFRT.GLBL_CMPY_CD");
        sql.append("            AND T.ORG_FUNC_ROLE_TP_CD  = OFRT.ORG_FUNC_ROLE_TP_CD");
        sql.append("            AND OFRT.TECH_MSTR_REQ_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sql.append("            AND OFRT.ACTV_FLG          = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sql.append("            AND B.EZCANCELFLAG = '0'");
        sql.append("            AND T.EZCANCELFLAG         = '0'");
        sql.append("            AND OFRT.EZCANCELFLAG      = '0'");
        sql.append("    )");

        return sql.toString();
    }
 // END 2017/01/05 N.Arai [QC#13901-2, MOD]

 // START 2017/01/05 N.Arai [QC#13901-2, MOD]
    private List<Object[]> getWhereList(NSBL0010BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Technician Code";
        h0[WLIST_OBJ_ID] = "TECH_TOC_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.techCd)) {
            h0[WLIST_OBJ_VALUE] = scrnMsg.techCd.getValue();
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Technician Name";
        h1[WLIST_OBJ_ID] = "TECH_NM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        return whereList;
    }
 // END 2017/01/05 N.Arai [QC#13901-2, MOD]

 // START 2017/01/05 N.Arai [QC#13901-2, MOD]
    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Technician Code";
        c0[CLIST_OBJ_ID] = "TECH_TOC_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(TECH_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Technician Name";
        c1[CLIST_OBJ_ID] = "TECH_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(TECH_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c1);

        return columnList;
    }
 // END 2017/01/05 N.Arai [QC#13901-2, MOD]

 // START 2017/01/05 N.Arai [QC#13901-2, MOD]
    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "TECH_TOC_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        return sortConditionList;
    }
 // END 2017/01/05 N.Arai [QC#13901-2, MOD]
}

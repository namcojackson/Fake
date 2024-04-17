/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0010;

import static business.servlet.NSAL0010.constant.NSAL0010Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0010.common.NSAL0010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2015/12/03   Hitachi         K.Yamada        Update          CSA QC#950
 * 2016/02/09   Hitachi         A.Kohinata      Update          QC#947
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 *</pre>
 */
public class NSAL0010Scrn00_OpenWin_RequestTech extends S21CommonHandler {

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
        NSAL0010CommonLogic.clearPopupParameter(scrnMsg);

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        // mod start 2016/02/09 QC#947
        Object[] params = new Object[7];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "Technician Pop Up";
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

    private String getSqlStr(NSAL0010BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     A.GLBL_CMPY_CD");
        sql.append("    ,A.EZCANCELFLAG");
        sql.append("    ,A.TECH_TOC_CD");
        sql.append("    ,A.TECH_NM");
        sql.append(" FROM ");
        sql.append("     TECH_MSTR A");
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // sql.append("    ,S21_PSN B");
        // END 2016/12/14 K.Kojima [QC#15653,DEL]
        sql.append(" WHERE");
        sql.append("        A.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("'   AND A.EZCANCELFLAG = '0'");
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // sql.append("    AND A.GLBL_CMPY_CD = B.GLBL_CMPY_CD");
        // sql.append("    AND A.TECH_TOC_CD  = B.PSN_CD");
        // sql.append("    AND B.EZCANCELFLAG = '0'");
        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        sql.append("    AND EXISTS (");
        sql.append("        SELECT");
        sql.append("            1");
        sql.append("        FROM");
        sql.append("            ORG_FUNC_ASG B");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            ,TOC              T");
        sql.append("            ,ORG_FUNC_ROLE_TP OFRT");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("        WHERE");
        sql.append("                B.GLBL_CMPY_CD = A.GLBL_CMPY_CD");
        sql.append("            AND B.PSN_CD       = A.TECH_TOC_CD");
        sql.append("            AND B.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("            AND NVL(B.EFF_THRU_DT, '").append(MAX_DT_VAL).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND B.GLBL_CMPY_CD         = T.GLBL_CMPY_CD");
        sql.append("            AND B.TOC_CD               = T.TOC_CD");
        sql.append("            AND T.GLBL_CMPY_CD         = OFRT.GLBL_CMPY_CD");
        sql.append("            AND T.ORG_FUNC_ROLE_TP_CD  = OFRT.ORG_FUNC_ROLE_TP_CD");
        sql.append("            AND OFRT.TECH_MSTR_REQ_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sql.append("            AND OFRT.ACTV_FLG          = '").append(ZYPConstant.FLG_ON_Y).append("'");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND B.EZCANCELFLAG = '0'");
        // START 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("            AND T.EZCANCELFLAG         = '0'");
        sql.append("            AND OFRT.EZCANCELFLAG      = '0'");
        // END 2016/12/21 K.Kojima [QC#15653,ADD]
        sql.append("    )");
        // END 2016/12/14 K.Kojima [QC#15653,ADD]

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0010BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Technician Code";
        h0[WLIST_OBJ_ID] = "TECH_TOC_CD";
        h0[WLIST_OBJ_VALUE] = "";
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

    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "TECH_TOC_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        return sortConditionList;
    }
    // mod end 2016/02/09 QC#947
}

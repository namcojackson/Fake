/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0500;

import static business.servlet.NSAL0500.constant.NSAL0500Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NSAL0500.common.NSAL0500CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/04/07   Hitachi         T.Aoyagi        Create          N/A
 * 2016/12/14   Hitachi         K.Kojima        Update          QC#15653
 * 2016/12/21   Hitachi         K.Kojima        Update          QC#15653
 * 2017/02/17   Hitachi         K.Kitachi       Update          QC#17564
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NSAL0500Scrn00_OpenWin_Tech extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2017/02/17 K.Kitachi [QC#17564, ADD]
        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CommonLogic.addCheckAllItem(scrnMsg);
        if (!hasValue(scrnMsg.vndCd)) {
            // START 2017/12/22 [QC#22831, MOD]
            scrnMsg.vndCd.setErrorInfo(1, ZZM9000E, new String[] {"Supplier Site" });
            // END   2017/12/22 [QC#22831, MOD]
        }
        scrnMsg.putErrorScreen();
        // END 2017/02/17 K.Kitachi [QC#17564, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;
        NSAL0500CommonLogic.clearPopupParam(scrnMsg);

        // START 2016/12/14 K.Kojima [QC#15653,DEL]
        // scrnMsg.xxPopPrm_01.setValue("TECH_MSTR");
        // scrnMsg.xxPopPrm_02.setValue("TECH_TOC_CD");
        // scrnMsg.xxPopPrm_03.setValue("TECH_NM");
        // scrnMsg.xxPopPrm_04.setValue("TECH_TOC_CD");
        // scrnMsg.xxPopPrm_05.setValue("Technicial Code Search");
        // scrnMsg.xxPopPrm_06.setValue("Technicial Code");
        // scrnMsg.xxPopPrm_07.setValue("Technicial Name");
        // scrnMsg.xxPopPrm_08.setValue("Technicial Code");
        // scrnMsg.xxPopPrm_09.setValue("Technicial Name");
        //
        // Object[] params = new Object[IDX_11];
        // params[0] = scrnMsg.xxPopPrm_01;
        // params[1] = scrnMsg.xxPopPrm_02;
        // params[2] = scrnMsg.xxPopPrm_03;
        // params[IDX_3] = scrnMsg.xxPopPrm_04;
        // params[IDX_4] = scrnMsg.xxPopPrm_05;
        // params[IDX_5] = scrnMsg.xxPopPrm_06;
        // params[IDX_6] = scrnMsg.xxPopPrm_07;
        // params[IDX_7] = scrnMsg.xxPopPrm_08;
        // params[IDX_8] = scrnMsg.xxPopPrm_09;
        // params[IDX_9] = scrnMsg.techTocCd;
        // params[IDX_10] = scrnMsg.xxPopPrm_10;
        // setArgForSubScreen(params);
        // END 2016/12/14 K.Kojima [QC#15653,DEL]

        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

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
        params[i++] = scrnMsg.D;
        setArgForSubScreen(params);
        // END 2016/12/14 K.Kojima [QC#15653,ADD]
    }

    // START 2016/12/14 K.Kojima [QC#15653,ADD]
    private String getSqlStr(NSAL0500BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     A.GLBL_CMPY_CD");
        sql.append("    ,A.EZCANCELFLAG");
        sql.append("    ,A.TECH_TOC_CD");
        sql.append("    ,A.TECH_NM");
        sql.append(" FROM ");
        sql.append("     TECH_MSTR A");
        sql.append(" WHERE");
        sql.append("        A.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sql.append("    AND A.EZCANCELFLAG = '0'");
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
        // START 2017/02/17 K.Kitachi [QC#17564, ADD]
        sql.append("    AND EXISTS (");
        sql.append("        SELECT");
        sql.append("            1");
        sql.append("        FROM");
        sql.append("             VND              V");
        sql.append("            ,DS_CTAC_PSN_RELN DCPR");
        sql.append("            ,S21_PSN          SP");
        sql.append("        WHERE");
        sql.append("                V.GLBL_CMPY_CD         = A.GLBL_CMPY_CD");
        sql.append("            AND V.EZCANCELFLAG         = '0'");
        sql.append("            AND V.VND_CD               = '").append(scrnMsg.vndCd.getValue()).append("'");
        sql.append("            AND V.GLBL_CMPY_CD         = DCPR.GLBL_CMPY_CD");
        sql.append("            AND V.LOC_NUM              = DCPR.LOC_NUM");
        sql.append("            AND DCPR.EZCANCELFLAG      = '0'");
        sql.append("            AND DCPR.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sql.append("            AND DCPR.CTAC_PSN_PK       = SP.CTAC_PSN_PK");
        sql.append("            AND SP.EZCANCELFLAG        = '0'");
        sql.append("            AND SP.PSN_TP_CD          <> '").append(PSN_TP.EMPLOYEE).append("'");
        sql.append("            AND SP.PSN_CD              = A.TECH_TOC_CD");
        sql.append("            AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("            AND NVL(SP.EFF_THRU_DT, '").append(MAX_DT_VAL).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sql.append("    )");
        // END 2017/02/17 K.Kitachi [QC#17564, ADD]

        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0500BMsg scrnMsg) {

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
    // END 2016/12/14 K.Kojima [QC#15653,ADD]

}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0730;

import static business.servlet.NSAL0730.constant.NSAL0730Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/19   Hitachi         J.Kim           Create          N/A
 * 2019/01/10   Hitachi         K.Kitachi       Update          QC#26928
 *</pre>
 */
public class NSAL0730Scrn00_OpenWin_PO extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0730BMsg scrnMsg = (NSAL0730BMsg) bMsg;

        Object[] params = new Object[NWAL1130_PRM_LENGTH];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        params[i++] = "PO# Search Popup";
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
        params[i++] = scrnMsg.C;
        setArgForSubScreen(params);
    }

    private String getSqlStr(NSAL0730BMsg scrnMsg) {

        int index = getButtonSelectNumber();
        String dsContrNum = null;
        if (index >= 0) {
            dsContrNum = scrnMsg.A.no(index).dsContrNum.getValue();
        }

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     DCR.GLBL_CMPY_CD");
        sql.append("    ,DCR.EZCANCELFLAG");
        sql.append("    ,DCR.DS_CONTR_NUM");
        sql.append("    ,DPO.CUST_PO_NUM");
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        sql.append("    ,DPO.PO_FROM_DT");
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        sql.append("    ,DPO.PO_DT");
        sql.append(" FROM ");
        sql.append("    DS_CONTR_CR_CARD_PO DPO");
        sql.append("   ,DS_CONTR DCR");
        sql.append(" WHERE");
        sql.append("    DCR.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("'   AND DPO.GLBL_CMPY_CD = DCR.GLBL_CMPY_CD");
        sql.append("    AND DPO.DS_CONTR_PK = DCR.DS_CONTR_PK");
        if (dsContrNum != null) {
            sql.append("    AND DCR.DS_CONTR_NUM = '");
            sql.append(dsContrNum);
            sql.append("' ");
        }
        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        sql.append("   AND DPO.CR_CARD_FLG  = 'N'");
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]
        sql.append("   AND DPO.EZCANCELFLAG = '0'");
        sql.append("   AND DCR.EZCANCELFLAG = '0'");
        return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0730BMsg scrnMsg) {

        String dsContrNum = null;
        int index = getButtonSelectNumber();
        if (index >= 0) {
            dsContrNum = scrnMsg.A.no(index).dsContrNum.getValue();
        }

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Contract#";
        h0[WLIST_OBJ_ID] = "DS_CONTR_NUM";
        if (dsContrNum != null) {
            h0[WLIST_OBJ_VALUE] = dsContrNum;
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h0);

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "PO Number";
        h1[WLIST_OBJ_ID] = "CUST_PO_NUM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h1);

        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        h2[WLIST_DSP_OBJ_NM] = "PO Expire Date";
//        h2[WLIST_OBJ_ID] = "PO_DT";
        h2[WLIST_DSP_OBJ_NM] = "From Date";
        h2[WLIST_OBJ_ID] = "PO_FROM_DT";
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        h2[WLIST_OBJ_VALUE] = "";
        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h2);

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        Object[] h3 = new Object[ATTR_NUM_WHERE_LIST];
        h3[WLIST_DSP_OBJ_NM] = "Thru Date";
        h3[WLIST_OBJ_ID] = "PO_DT";
        h3[WLIST_OBJ_VALUE] = "";
        h3[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        whereList.add(h3);
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Contract#";
        c0[CLIST_OBJ_ID] = "DS_CONTR_NUM";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(DS_CONTR_NUM_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c0);

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "PO Number";
        c1[CLIST_OBJ_ID] = "CUST_PO_NUM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(CUST_PO_NUM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;
        columnList.add(c1);

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        c2[CLIST_DSP_OBJ_NM] = "PO Expire Date";
//        c2[CLIST_OBJ_ID] = "PO_DT";
        c2[CLIST_DSP_OBJ_NM] = "From Date";
        c2[CLIST_OBJ_ID] = "PO_FROM_DT";
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(PO_DT_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c2);

        // START 2019/01/10 K.Kitachi [QC#26928, ADD]
        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "Thru Date";
        c3[CLIST_OBJ_ID] = "PO_DT";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(PO_DT_LENGTH);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;
        columnList.add(c3);
        // END 2019/01/10 K.Kitachi [QC#26928, ADD]

        return columnList;
    }

    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "DS_CONTR_NUM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "CUST_PO_NUM";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[2];
        // START 2019/01/10 K.Kitachi [QC#26928, MOD]
//        sortConditionArray3[0] = "PO_DT";
        sortConditionArray3[0] = "PO_FROM_DT";
        // END 2019/01/10 K.Kitachi [QC#26928, MOD]
        sortConditionArray3[1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        return sortConditionList;
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
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

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Sub Contract Maintenance
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         T.Tsuchida      Create          N/A
 * 2016/03/03   Hitachi         T.Tomita        Update          QC#3710
 * 2016/04/21   Hitachi         M.Gotou         Update          QC#5464
 * 2017/03/01   Hitachi         T.Mizuki        Update          QC#17575
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NSAL0500Scrn00_OpenWin_Vendor extends S21CommonHandler {

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

        NSAL0500BMsg scrnMsg = (NSAL0500BMsg) bMsg;

        // START 2016/12/14 K.Kojima [QC#15653,ADD]
        NSAL0500CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        // END 2016/12/14 K.Kojima [QC#15653,ADD]

        Object[] params = new Object[NWAL1130_PRM_LENGTH];
        int i = 0;
        params[i++] = "";
        // 1 : Lv1 : Window Title (String: Popup Title)
        // START 2017/12/22 [QC#22831, MOD]
        params[i++] = "Supplier/Supplier Site Search";
        // END   2017/12/22 [QC#22831, MOD]
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
    }

    private String getSqlStr(NSAL0500BMsg scrnMsg) {

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT");
        sql.append("     V.GLBL_CMPY_CD");
        sql.append("    ,V.EZCANCELFLAG");
        sql.append("    ,V.VND_CD");
        sql.append("    ,V.LOC_NM");
        sql.append("    ,PV.PRNT_VND_CD");
        sql.append("    ,PV.PRNT_VND_NM");
        sql.append(" FROM ");
        sql.append("    VND V");
        sql.append("   ,VND_TP_RELN VTR");
        sql.append("   ,PRNT_VND PV");
        sql.append(" WHERE");
        sql.append("    V.GLBL_CMPY_CD = '");
        sql.append(scrnMsg.glblCmpyCd.getValue());
        sql.append("'   AND V.RGTN_STS_CD = '");
        sql.append(RGTN_STS.READY_FOR_ORDER_TAKING);
        sql.append("'   AND V.EFF_THRU_DT >= '");
        sql.append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()));
        sql.append("'   AND V.EZCANCELFLAG = '0'");
        sql.append("    AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD");
        sql.append("    AND V.VND_CD = VTR.VND_CD");
        // mod start 2017/03/01 CSA QC#17575
        // START 2016/03/03 T.Tomita [QC#3710, MOD]
        sql.append("    AND VTR.VND_TP_CD = '");
        sql.append(VND_TP.SERVICE_DEALER);
        sql.append("'   AND VTR.EZCANCELFLAG = '0'");
//        sql.append("    AND VTR.EZCANCELFLAG = '0'");
        // END 2016/03/03 T.Tomita [QC#3710, MOD]
        // mod end 2017/03/01 CSA QC#17575
        sql.append("    AND PV.GLBL_CMPY_CD = V.GLBL_CMPY_CD");
        sql.append("    AND PV.PRNT_VND_PK = V.PRNT_VND_PK");
        sql.append("    AND (PV.INAC_DT IS NULL");
        sql.append("        OR PV.INAC_DT > '");
        sql.append(ZYPDateUtil.getSalesDate(scrnMsg.glblCmpyCd.getValue()));
        sql.append("')");
        sql.append("    AND PV.EZCANCELFLAG = '0'");

       return sql.toString();
    }

    private List<Object[]> getWhereList(NSAL0500BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        // START 2017/12/22 [QC#22831, MOD]
        // mod start 2016/04/21 CSA Defect#5464
        Object[] h0 = new Object[ATTR_NUM_WHERE_LIST];
        h0[WLIST_DSP_OBJ_NM] = "Supplier Site Code";
        h0[WLIST_OBJ_ID] = "VND_CD";
        if (hasValue(scrnMsg.vndCd)) {
            h0[WLIST_OBJ_VALUE] = scrnMsg.vndCd.getValue();
        } else {
            h0[WLIST_OBJ_VALUE] = "";
        }
        h0[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;

        Object[] h1 = new Object[ATTR_NUM_WHERE_LIST];
        h1[WLIST_DSP_OBJ_NM] = "Supplier Site Name";
        h1[WLIST_OBJ_ID] = "LOC_NM";
        h1[WLIST_OBJ_VALUE] = "";
        h1[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;

        Object[] h2 = new Object[ATTR_NUM_WHERE_LIST];
        h2[WLIST_DSP_OBJ_NM] = "Supplier Number";
        h2[WLIST_OBJ_ID] = "PRNT_VND_CD";
        h2[WLIST_OBJ_VALUE] = "";
        h2[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;

        Object[] h3 = new Object[ATTR_NUM_WHERE_LIST];
        h3[WLIST_DSP_OBJ_NM] = "Supplier Name";
        h3[WLIST_OBJ_ID] = "PRNT_VND_NM";
        if (hasValue(scrnMsg.prntVndNm)) {
            h3[WLIST_OBJ_VALUE] = scrnMsg.prntVndNm.getValue();
        } else {
            h3[WLIST_OBJ_VALUE] = "";
        }
        h3[WLIST_WHERE_FLG] = ZYPConstant.FLG_ON_Y;
        // mod end 2016/04/21 CSA Defect#5464

        whereList.add(h0);
        whereList.add(h1);
        whereList.add(h2);
        whereList.add(h3);
        // END   2017/12/22 [QC#22831, MOD]

        return whereList;
    }

    private List<Object[]> getColumnList() {

        List<Object[]> columnList = new ArrayList<Object[]>();

        // START 2017/12/22 [QC#22831, MOD]
        // mod start 2016/04/21 CSA Defect#5464
        Object[] c0 = new Object[ATTR_NUM_CLMN_LIST];
        c0[CLIST_DSP_OBJ_NM] = "Supplier Site Code";
        c0[CLIST_OBJ_ID] = "VND_CD";
        c0[CLIST_OBJ_LENGTH] = new BigDecimal(VND_CD_LENGTH);
        c0[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;

        Object[] c1 = new Object[ATTR_NUM_CLMN_LIST];
        c1[CLIST_DSP_OBJ_NM] = "Supplier Site Name";
        c1[CLIST_OBJ_ID] = "LOC_NM";
        c1[CLIST_OBJ_LENGTH] = new BigDecimal(LOC_NM_LENGTH);
        c1[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;

        Object[] c2 = new Object[ATTR_NUM_CLMN_LIST];
        c2[CLIST_DSP_OBJ_NM] = "Supplier Number";
        c2[CLIST_OBJ_ID] = "PRNT_VND_CD";
        c2[CLIST_OBJ_LENGTH] = new BigDecimal(PRNT_VND_CD_LENGTH);
        c2[CLIST_LINK_FLG] = ZYPConstant.FLG_OFF_N;

        Object[] c3 = new Object[ATTR_NUM_CLMN_LIST];
        c3[CLIST_DSP_OBJ_NM] = "Supplier Name";
        c3[CLIST_OBJ_ID] = "PRNT_VND_NM";
        c3[CLIST_OBJ_LENGTH] = new BigDecimal(PRNT_VND_NM_LENGTH);
        c3[CLIST_LINK_FLG] = ZYPConstant.FLG_ON_Y;

        columnList.add(c0);
        columnList.add(c1);
        columnList.add(c2);
        columnList.add(c3);
        // END   2017/12/22 [QC#22831, MOD]

        return columnList;
    }

    private List<Object[]> getSortConditionList() {

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "LOC_NM";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        return sortConditionList;
    }
}

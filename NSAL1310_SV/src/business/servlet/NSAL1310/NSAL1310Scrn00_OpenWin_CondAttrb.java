/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1310;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/10/31   Hitachi         K.Kojima        Create          QC#15136
 *</pre>
 */
public class NSAL1310Scrn00_OpenWin_CondAttrb extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1310BMsg scrnMsg = (NSAL1310BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "T&C Attrb Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("     STCA.GLBL_CMPY_CD ");
        sb.append("    ,STCA.EZCANCELFLAG ");
        sb.append("    ,STCA.SVC_TERM_COND_ATTRB_PK ");
        sb.append("    ,STCA.SVC_TERM_ATTRB_DESC_TXT ");
        sb.append("    ,STCA.SVC_TERM_ATTRB_SORT_NUM ");
        sb.append("    ,STCC.SVC_TERM_COND_CATG_DESC_TXT ");
        sb.append("    ,STADT.SVC_TERM_DATA_TP_DESC_TXT ");
        sb.append("FROM ");
        sb.append("     SVC_TERM_COND_ATTRB    STCA ");
        sb.append("    ,SVC_TERM_ATTRB_DATA_TP STADT ");
        sb.append("    ,SVC_TERM_COND_CATG     STCC ");
        sb.append("WHERE ");
        sb.append("        STCA.GLBL_CMPY_CD                  = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("    AND STCA.SVC_TERM_ATTRB_ACTV_FLG       = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append("    AND STCA.EFF_FROM_DT                  <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("    AND NVL(STCA.EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("    AND STCA.GLBL_CMPY_CD                  = STCC.GLBL_CMPY_CD ");
        sb.append("    AND STCA.SVC_TERM_COND_CATG_PK         = STCC.SVC_TERM_COND_CATG_PK ");
        sb.append("    AND STCC.SVC_TERM_COND_CATG_ACTV_FLG   = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append("    AND STCC.EFF_FROM_DT                  <= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("    AND NVL(STCC.EFF_THRU_DT, '99991231') >= '").append(scrnMsg.slsDt.getValue()).append("' ");
        sb.append("    AND STCA.GLBL_CMPY_CD                  = STADT.GLBL_CMPY_CD ");
        sb.append("    AND STCA.SVC_TERM_DATA_TP_CD           = STADT.SVC_TERM_ATTRB_DATA_TP_CD ");
        sb.append("    AND STCA.EZCANCELFLAG                  = '0' ");
        sb.append("    AND STCC.EZCANCELFLAG                  = '0' ");
        sb.append("    AND STADT.EZCANCELFLAG                 = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "T&C Attrb";
        whereArray0[1] = "SVC_TERM_ATTRB_DESC_TXT";
        whereArray0[2] = null;
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "T&C Attrb";
        columnArray0[1] = "SVC_TERM_ATTRB_DESC_TXT";
        columnArray0[2] = BigDecimal.valueOf(40);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Attrb Sort Num";
        columnArray1[1] = "SVC_TERM_ATTRB_SORT_NUM";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Cond Category";
        columnArray2[1] = "SVC_TERM_COND_CATG_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(20);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Data Type";
        columnArray3[1] = "SVC_TERM_DATA_TP_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "PK";
        columnArray4[1] = "SVC_TERM_COND_ATTRB_PK";
        columnArray4[2] = BigDecimal.valueOf(0);
        columnArray4[3] = "N";
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "SVC_TERM_ATTRB_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

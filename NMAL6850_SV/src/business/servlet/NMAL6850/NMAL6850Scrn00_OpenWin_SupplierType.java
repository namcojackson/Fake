/**
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6850;

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
 * 2020/02/28   Fujitsu         C.Hara          Create          QC#55971
 *</pre>
 */

public class NMAL6850Scrn00_OpenWin_SupplierType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg,EZDCMsg cMsg) {
        NMAL6850BMsg scrnMsg = (NMAL6850BMsg) bMsg;

            Object[] params = new Object[7];
            params[0] = "Z";
            params[1] = "Supplier Type Search";

            StringBuilder sb = new StringBuilder();
            sb.append("SELECT ");
            sb.append("  PVT.EZCANCELFLAG         AS EZCANCELFLAG ");
            sb.append(", PVT.GLBL_CMPY_CD         AS GLBL_CMPY_CD ");
            sb.append(", PVT.PRNT_VND_TP_CD       AS PRNT_VND_TP_CD ");
            sb.append(", PVT.PRNT_VND_TP_DESC_TXT AS PRNT_VND_TP_DESC_TXT ");
            sb.append(", PVT.PRNT_VND_TP_SORT_NUM AS PRNT_VND_TP_SORT_NUM ");
            sb.append("FROM ");
            sb.append("  PRNT_VND_TP PVT ");
            sb.append("WHERE ");
            sb.append("    PVT.EZCANCELFLAG   = '0' ");
            sb.append("AND PVT.GLBL_CMPY_CD   = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");

            params[2] = sb.toString();

            List<Object[]> whereList = new ArrayList<Object[]>();
            Object[] whereArray0 = new Object[4];
            whereArray0[0] = "Supplier Type Code";
            whereArray0[1] = "UPPER(PRNT_VND_TP_CD)";
            whereArray0[2] = null;
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereList.add(whereArray0);

            Object[] whereArray1 = new Object[4];
            whereArray1[0] = "Supplier Type Name";
            whereArray1[1] = "UPPER(PRNT_VND_TP_DESC_TXT)";
            whereArray1[2] = scrnMsg.prntVndTpDescTxt_H.getValue();
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereList.add(whereArray1);

            params[3] = whereList;

            List<Object[]> columnList = new ArrayList<Object[]>();
            Object[] columnArray0 = new Object[4];
            columnArray0[0] = "Supplier Type Code";
            columnArray0[1] = "PRNT_VND_TP_CD";
            columnArray0[2] = BigDecimal.valueOf(15);
            columnArray0[3] = ZYPConstant.FLG_ON_Y;
            columnList.add(columnArray0);

            Object[] columnArray1 = new Object[4];
            columnArray1[0] = "Supplier Type Name";
            columnArray1[1] = "PRNT_VND_TP_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = ZYPConstant.FLG_OFF_N;
            columnList.add(columnArray1);

            params[4] = columnList;

            List<Object[]> sortConditionList = new ArrayList<Object[]>();
            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = "PRNT_VND_TP_SORT_NUM";
            sortConditionArray0[1] = "ASC";
            sortConditionList.add(sortConditionArray0);

            params[5] = sortConditionList;

            ZYPTableUtil.clear(scrnMsg.Z);
            params[6] = scrnMsg.Z;

            setArgForSubScreen(params);
        }

}

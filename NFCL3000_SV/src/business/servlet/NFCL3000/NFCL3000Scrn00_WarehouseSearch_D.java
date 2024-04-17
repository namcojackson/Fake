/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NFCL3000.NFCL3000CMsg;
//import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/10/28   Fujitsu         T.Murai         Update          QC#15495
 *</pre>
 */
public class NFCL3000Scrn00_WarehouseSearch_D extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFCL3000BMsg scrnMsg = (NFCL3000BMsg) bMsg;

        scrnMsg.xxScrItem10Txt.setValue("WH_D");
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Ship From Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        // START 2016/10/28 T.Murai [QC#15495, MOD]
        // sb.append("   ,WH.RTL_WH_CD");
        // sb.append("   ,WH.RTL_WH_NM ");
        sb.append("   ,WH.INVTY_LOC_CD");
        sb.append("   ,WH.INVTY_LOC_NM ");
        // END   2016/10/28 T.Murai [QC#15495, MOD]
        sb.append("FROM");
        sb.append("    DS_INVTY_LOC_V                       WH ");
        sb.append("WHERE");
        sb.append("        WH.GLBL_CMPY_CD                  = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append("    AND WH.EFF_FROM_DT                   <= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(scrnMsg.procDt.getValue()).append("'");
        sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        sb.append("GROUP BY");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        // START 2016/10/28 T.Murai [QC#15495, MOD]
        // sb.append("   ,WH.RTL_WH_CD");
        // sb.append("   ,WH.RTL_WH_NM");
        sb.append("   ,WH.INVTY_LOC_CD");
        sb.append("   ,WH.INVTY_LOC_NM");
        // END   2016/10/28 T.Murai [QC#15495, MOD]

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Warehouse Code";

        // START 2016/10/28 T.Murai [QC#15495, MOD]
        // whereArray0[1] = "RTL_WH_CD";
        // whereArray0[2] = scrnMsg.shipFromInvtyLocCd_H4.getValue();
        whereArray0[1] = "INVTY_LOC_CD";
        whereArray0[2] = scrnMsg.D.no(idx).shipFromInvtyLocCd_D1.getValue();
        // END   2016/10/28 T.Murai [QC#15495, MOD]
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Warehouse Name";
        // START 2016/10/28 T.Murai [QC#15495, DEL]
//        whereArray1[1] = "RTL_WH_NM";
//        whereArray1[2] = scrnMsg.rtlWhNm_H4.getValue();
        whereArray1[1] = "INVTY_LOC_NM";
        whereArray1[2] = "";
        // END   2016/10/28 T.Murai [QC#15495, DEL]
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Warehouse Code";
        columnArray0[1] = "INVTY_LOC_CD"; // 2016/10/28 T.Murai [QC#15495, MOD(RTL_WH_CD -> INVTY_LOC_CD)]
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Warehouse Name";

        // START 2016/10/28 T.Murai [QC#15495, MOD]
        // columnArray1[1] = "RTL_WH_NM";
        // columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[1] = "INVTY_LOC_NM";
        columnArray1[2] = BigDecimal.valueOf(40);
        // END   2016/10/28 T.Murai [QC#15495, MOD] 
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);


        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "INVTY_LOC_CD"; // 2016/10/28 T.Murai [QC#15495, MOD(RTL_WH_CD -> INVTY_LOC_CD)]
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

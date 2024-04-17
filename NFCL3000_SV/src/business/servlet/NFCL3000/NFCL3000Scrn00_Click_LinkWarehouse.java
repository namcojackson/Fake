/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
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

import business.servlet.NFCL3000.constant.NFCL3000Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         T.Tanaka        Create          N/A
 *</pre>
 */
public class NFCL3000Scrn00_Click_LinkWarehouse extends S21CommonHandler implements NFCL3000Constant {

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

        ZYPEZDItemValueSetter.setValue(scrnMsg.shipFromInvtyLocCd_P, scrnMsg.shipFromInvtyLocCd_H4.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhNm_P, scrnMsg.rtlWhNm_H4.getValue());

        scrnMsg.xxScrEventNm.setValue("Click_LinkWarehouse");
        
        scrnMsg.xxScrItem10Txt.setValue("Warehouse");
        
        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Ship From Warehouse Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
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
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
        
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Warehouse Code";
        whereArray0[1] = "RTL_WH_CD";
        whereArray0[2] = scrnMsg.shipFromInvtyLocCd_P.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Warehouse Name";
        whereArray1[1] = "RTL_WH_NM";
        whereArray1[2] = scrnMsg.rtlWhNm_P.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Warehouse Code";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Warehouse Name";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);


        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "RTL_WH_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);

    }
}

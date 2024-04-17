/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1130;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/09/30   Hitachi         K.Kojima        Update          QC#14179
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL1130Scrn00_OnClick_SupplierLink extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFBL1130BMsg scrnMsg = (NFBL1130BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.putErrorScreen();

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Supplier Search";

        StringBuilder sb = new StringBuilder();
        sb.append("    SELECT ");
        sb.append("      PV.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append("    , PV.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append("    , PV.PRNT_VND_CD         AS PRNT_VND_CD ");
        sb.append("    , PV.PRNT_VND_NM         AS PRNT_VND_NM ");
        sb.append("    FROM ");
        sb.append("      ( ");
        sb.append("        SELECT ");
        sb.append("          PV.EZCANCELFLAG        AS EZCANCELFLAG ");
        sb.append("        , PV.GLBL_CMPY_CD        AS GLBL_CMPY_CD ");
        sb.append("        , PV.PRNT_VND_CD         AS PRNT_VND_CD ");
        sb.append("        , PV.PRNT_VND_NM         AS PRNT_VND_NM ");
        sb.append("        FROM ");
        sb.append("          PRNT_VND  PV ");
        sb.append("        WHERE ");
        sb.append("            PV.EZCANCELFLAG   = '0' ");
        sb.append("        AND PV.GLBL_CMPY_CD   = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("      ) PV ");
        sb.append("    WHERE ");
        sb.append("        PV.EZCANCELFLAG   = '0' ");
        sb.append("    AND PV.GLBL_CMPY_CD   = '").append(getGlobalCompanyCode()).append("' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray0[0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray0[1] = "PRNT_VND_CD";
        whereArray0[2] = scrnMsg.prntVndCd.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Name";
        whereArray1[1] = "PRNT_VND_NM";
        whereArray1[2] = scrnMsg.prntVndNm.getValue();
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray0[0] = "Supplier Number";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = "Y";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(70);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

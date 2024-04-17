/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2100;

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
 * 2016/10/12   Hitachi         K.Kojima        Create          QC#13088
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL2100Scrn00_OpenWin_CustomerSearch extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NFBL2100BMsg scrnMsg = (NFBL2100BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        scrnMsg.putErrorScreen();

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = new Object[7];
        params[0] = "";
        // START 2017/12/22 [QC#22831, MOD]
        params[1] = "Supplier/Supplier Site Search";
        // END   2017/12/22 [QC#22831, MOD]

        StringBuilder sb = new StringBuilder();

        sb.append("    SELECT ");
        sb.append("        PV.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append("       ,PV.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append("       ,PV.PRNT_VND_CD  AS PRNT_VND_CD ");
        sb.append("       ,PV.PRNT_VND_NM  AS PRNT_VND_NM ");
        sb.append("       ,V.VND_CD        AS VND_CD ");
        sb.append("       ,V.LOC_NM        AS LOC_NM ");
        sb.append("   FROM ");
        sb.append("        PRNT_VND PV ");
        sb.append("       ,VND      V ");
        sb.append("   WHERE ");
        sb.append("           PV.GLBL_CMPY_CD = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("       AND PV.GLBL_CMPY_CD = V.GLBL_CMPY_CD ");
        sb.append("       AND PV.PRNT_VND_PK  = V.PRNT_VND_PK ");
        sb.append("       AND PV.EZCANCELFLAG = '0' ");
        sb.append("       AND V.EZCANCELFLAG  = '0' ");
        sb.append("       AND EXISTS ( ");
        sb.append("           SELECT ");
        sb.append("               1 ");
        sb.append("           FROM ");
        sb.append("               AP_LSE_BO_WF_RQST ALBWR ");
        sb.append("           WHERE ");
        sb.append("                   ALBWR.GLBL_CMPY_CD = V.GLBL_CMPY_CD ");
        sb.append("               AND ALBWR.VND_PK = V.VND_PK ");
        sb.append("               AND ALBWR.EZCANCELFLAG = '0' ");
        sb.append("           ) ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray0[0] = "Supplier Number";
        whereArray0[1] = "PRNT_VND_CD";
        whereArray0[2] = null;
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray1[0] = "Supplier Name";
        whereArray1[1] = "PRNT_VND_NM";
        whereArray1[2] = null;
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray2[0] = "Supplier Site Code";
        whereArray2[1] = "VND_CD";
        whereArray2[2] = scrnMsg.vndCd.getValue();
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        whereArray3[0] = "Supplier Site Name";
        whereArray3[1] = "LOC_NM";
        whereArray3[2] = null;
        whereArray3[3] = "Y";
        whereList.add(whereArray3);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray0[0] = "Supplier Number";
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(15);
        columnArray0[3] = "N";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray2[0] = "Supplier Site Code";
        columnArray2[1] = "VND_CD";
        columnArray2[2] = BigDecimal.valueOf(15);
        columnArray2[3] = "Y";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        columnArray3[0] = "Supplier Site Name";
        columnArray3[1] = "LOC_NM";
        columnArray3[2] = BigDecimal.valueOf(30);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "VND_CD";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

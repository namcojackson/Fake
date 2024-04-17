/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL1110;

import static business.servlet.NFBL1110.constant.NFBL1110Constant.BIZ_ID;
import static business.servlet.NFBL1110.constant.NFBL1110Constant.FUNC_CD_20;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NFBL1110.NFBL1110CMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/29   Hitachi         K.Kojima        Create          QC#13442
 * 2017/12/22   Hitachi         Y.Takeno        Update          QC#22831
 * 2017/12/25   Hitachi         Y.Takeno        Update          QC#22831
 *</pre>
 */
public class NFBL1110Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;

        NFBL1110CMsg bizMsg = new NFBL1110CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_20);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFBL1110BMsg scrnMsg = (NFBL1110BMsg) bMsg;
        NFBL1110CMsg bizMsg = (NFBL1110CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.prntVndCd_IH);
        scrnMsg.addCheckItem(scrnMsg.vndSiteNm_IH);
        scrnMsg.putErrorScreen();

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Serial Search";

        StringBuilder sb = new StringBuilder();
        sb.append("    SELECT ");
        sb.append("         SMM.GLBL_CMPY_CD AS GLBL_CMPY_CD ");
        sb.append("        ,SMM.EZCANCELFLAG AS EZCANCELFLAG ");
        sb.append("        ,PV.PRNT_VND_CD   AS PRNT_VND_CD ");
        sb.append("        ,PV.PRNT_VND_NM   AS PRNT_VND_NM ");
        sb.append("        ,V.LOC_NM         AS LOC_NM ");
        sb.append("        ,V.VND_CD         AS VND_CD ");
        sb.append("        ,SMM.SER_NUM      AS SER_NUM ");
        sb.append("    FROM ");
        sb.append("         DS_SUB_CONTR DSC ");
        sb.append("        ,DS_CONTR_DTL DCD ");
        sb.append("        ,SVC_MACH_MSTR SMM ");
        sb.append("        ,VND V ");
        sb.append("        ,PRNT_VND PV ");
        sb.append("    WHERE  ");
        sb.append("            DSC.GLBL_CMPY_CD     = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("        AND (DSC.EFF_THRU_DT    >= '").append(ZYPDateUtil.getSalesDate()).append("' OR DSC.EFF_THRU_DT IS NULL ) ");
        sb.append("        AND DSC.EZCANCELFLAG     = '0' ");
        sb.append("        AND DSC.GLBL_CMPY_CD     = DCD.GLBL_CMPY_CD ");
        sb.append("        AND DSC.DS_CONTR_DTL_PK  = DCD.DS_CONTR_DTL_PK ");
        sb.append("        AND DCD.EZCANCELFLAG     = '0' ");
        sb.append("        AND DCD.GLBL_CMPY_CD     = SMM.GLBL_CMPY_CD ");
        sb.append("        AND DCD.SVC_MACH_MSTR_PK = SMM.SVC_MACH_MSTR_PK ");
        sb.append("        AND SMM.EZCANCELFLAG     = '0' ");
        sb.append("        AND DSC.GLBL_CMPY_CD     = V.GLBL_CMPY_CD ");
        sb.append("        AND DSC.VND_CD           = V.VND_CD ");
        sb.append("        AND V.EZCANCELFLAG       = '0' ");
        sb.append("        AND V.GLBL_CMPY_CD       = PV.GLBL_CMPY_CD ");
        sb.append("        AND V.PRNT_VND_PK        = PV.PRNT_VND_PK ");
        sb.append("        AND PV.EZCANCELFLAG      = '0' ");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        // START 2017/12/25 [QC#22831, MOD]
        whereArray0[0] = "Supplier Number";
        // END   2017/12/25 [QC#22831, MOD]
        whereArray0[1] = "PRNT_VND_CD";
        whereArray0[2] = scrnMsg.prntVndCd_IH.getValue();
        whereArray0[3] = "Y";
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Supplier Name";
        whereArray1[1] = "PRNT_VND_NM";
        whereArray1[2] = scrnMsg.prntVndNm_IH.getValue();
        whereArray1[3] = "Y";
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        // whereArray2[0] = "Site Name";
        whereArray2[0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray2[1] = "LOC_NM";
        whereArray2[2] = scrnMsg.vndSiteNm_IH.getValue();
        whereArray2[3] = "Y";
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        // whereArray3[0] = "Site Code";
        whereArray3[0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        whereArray3[1] = "VND_CD";
        whereArray3[2] = scrnMsg.apVndCd_HD.getValue();
        whereArray3[3] = "Y";
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[4];
        whereArray4[0] = "Serial#";
        whereArray4[1] = "SER_NUM";
        whereArray4[2] = scrnMsg.serNum_AD.getValue();
        whereArray4[3] = "Y";
        whereList.add(whereArray4);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        // START 2017/12/25 [QC#22831, MOD]
        columnArray0[0] = "Supplier Number";
        // END   2017/12/25 [QC#22831, MOD]
        columnArray0[1] = "PRNT_VND_CD";
        columnArray0[2] = BigDecimal.valueOf(17);
        columnArray0[3] = "N";
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Supplier Name";
        columnArray1[1] = "PRNT_VND_NM";
        columnArray1[2] = BigDecimal.valueOf(22);
        columnArray1[3] = "N";
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        // columnArray2[0] = "Site Name";
        columnArray2[0] = "Supplier Site Name";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray2[1] = "LOC_NM";
        columnArray2[2] = BigDecimal.valueOf(17);
        columnArray2[3] = "N";
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        // START 2017/12/22 [QC#22831, MOD]
        // columnArray3[0] = "Site Code";
        columnArray3[0] = "Supplier Site Code";
        // END   2017/12/22 [QC#22831, MOD]
        columnArray3[1] = "VND_CD";
        columnArray3[2] = BigDecimal.valueOf(18);
        columnArray3[3] = "N";
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Serial #";
        columnArray4[1] = "SER_NUM";
        columnArray4[2] = BigDecimal.valueOf(18);
        columnArray4[3] = "Y";
        columnList.add(columnArray4);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PRNT_VND_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = "LOC_NM";
        sortConditionArray1[1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0] = "SER_NUM";
        sortConditionArray2[1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

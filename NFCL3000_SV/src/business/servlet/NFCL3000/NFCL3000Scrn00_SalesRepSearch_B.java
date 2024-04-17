/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFCL3000;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFCL3000.common.NFCL3000CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/07/14   Fujitsu         S.Fujita        Update          QC#11157
 *</pre>
 */
public class NFCL3000Scrn00_SalesRepSearch_B extends S21CommonHandler {

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

        scrnMsg.xxScrItem10Txt.setValue("SalesRep_B");
        int idx = getButtonSelectNumber();
        scrnMsg.xxCellIdx.setValue(idx);

        // START 2016/07/14 S.Fujita [QC#11157,MOD]
//        Object[] params = new Object[7];
//        params[0] = "";
//        params[1] = "Sales Rep Search";
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("SELECT");
//        sb.append("    T.GLBL_CMPY_CD");
//        sb.append("   ,T.EZCANCELFLAG");
//        sb.append("   ,T.TOC_CD");
//        sb.append("   ,T.TOC_NM ");
//        sb.append("FROM");
//        sb.append("    S21_ORG T");
//        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
//        sb.append("   ,BIZ_AREA_ORG BA ");
//        sb.append("WHERE");
//        sb.append("        T.GLBL_CMPY_CD        = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
//        sb.append("    AND T.EZCANCELFLAG        = '0'");
//        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
//        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
//        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
//        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
//        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
//        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
//        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
//        sb.append("    AND BA.EZCANCELFLAG       = '0'");
//
//        params[2] = sb.toString();
//
//        List<Object[]> whereList = new ArrayList<Object[]>();
//        Object[] whereArray0 = new Object[4];
//        whereArray0[0] = "Sales Rep Code";
//        whereArray0[1] = "TOC_CD";
//        whereArray0[2] = scrnMsg.slsRepTocCd_H1.getValue();
//        whereArray0[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray0);
//
//        Object[] whereArray1 = new Object[4];
//        whereArray1[0] = "Sales Rep Name";
//        whereArray1[1] = "TOC_NM";
//        whereArray1[2] = scrnMsg.slsRepTocNm_H1.getValue();
//        whereArray1[3] = ZYPConstant.FLG_ON_Y;
//        whereList.add(whereArray1);
//
//        params[3] = whereList;
//
//        List<Object[]> columnList = new ArrayList<Object[]>();
//        Object[] columnArray0 = new Object[4];
//        columnArray0[0] = "Sales Rep Code";
//        columnArray0[1] = "TOC_CD";
//        columnArray0[2] = BigDecimal.valueOf(20);
//        columnArray0[3] = ZYPConstant.FLG_ON_Y;
//        columnList.add(columnArray0);
//
//        Object[] columnArray1 = new Object[4];
//        columnArray1[0] = "Sales Rep Name";
//        columnArray1[1] = "TOC_NM";
//        columnArray1[2] = BigDecimal.valueOf(50);
//        columnArray1[3] = ZYPConstant.FLG_OFF_N;
//        columnList.add(columnArray1);
//
//        params[4] = columnList;
//
//        List<Object[]> sortConditionList = new ArrayList<Object[]>();
//        Object[] sortConditionArray0 = new Object[2];
//        sortConditionArray0[0] = "TOC_CD";
//        sortConditionArray0[1] = "ASC";
//        sortConditionList.add(sortConditionArray0);
//
//        params[5] = sortConditionList;
//
//        ZYPTableUtil.clear(scrnMsg.Z);
//        params[6] = scrnMsg.Z;

        Object[] params = NFCL3000CommonLogic.getParamNWAL1130ForSlsrep_B(scrnMsg);
        // END   2016/07/14 S.Fujita [QC#11157,MOD]

        setArgForSubScreen(params);

    }
}

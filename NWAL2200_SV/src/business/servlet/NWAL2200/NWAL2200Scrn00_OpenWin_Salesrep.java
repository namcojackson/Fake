/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.ZZM9000E;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ORG_STRU_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_Salesrep
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28425
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_Salesrep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdTpCd)) {
            scrnMsg.dsOrdTpCd.setErrorInfo(1, ZZM9000E, new String[] {scrnMsg.dsOrdTpCd.getNameForMessage() });
            scrnMsg.addCheckItem(scrnMsg.dsOrdTpCd);
            scrnMsg.putErrorScreen();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = getParamNWAL1130ForSlsrep(scrnMsg);
        setArgForSubScreen(params);
    }

    private static Object[] getParamNWAL1130ForSlsrep(NWAL2200BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Sales Rep Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    T.GLBL_CMPY_CD");
        sb.append("   ,T.EZCANCELFLAG");
        sb.append("   ,SP.PSN_NUM");
        sb.append("   ,T.TOC_NM");
        sb.append("   ,SP.LINE_BIZ_TP_CD");
        sb.append("   ,CB.COA_BR_NM");
        sb.append("   ,OFRT.ORG_FUNC_ROLE_TP_NM");
        sb.append("   ,T.TOC_CD ");
        sb.append("FROM");
        sb.append("    TOC T");
        sb.append("   ,ORG_FUNC_ROLE_TP OFRT");
        sb.append("   ,BIZ_AREA_ORG BA");
        sb.append("   ,ORG_FUNC_ASG OFA");
        sb.append("   ,S21_PSN SP");
        sb.append("   ,COA_BR CB ");
        sb.append("   ,S21_ORG SO ");
        sb.append("WHERE");
        sb.append("        T.GLBL_CMPY_CD        =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND T.EZCANCELFLAG        = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFRT.GLBL_CMPY_CD");
        sb.append("    AND T.ORG_FUNC_ROLE_TP_CD = OFRT.ORG_FUNC_ROLE_TP_CD");
        sb.append("    AND OFRT.SLS_REP_FLG      = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND OFRT.EZCANCELFLAG     = '0'");
        sb.append("    AND OFRT.GLBL_CMPY_CD     = BA.GLBL_CMPY_CD");
        sb.append("    AND OFRT.FIRST_ORG_CD     = BA.BIZ_AREA_ORG_CD");
        sb.append("    AND BA.SLS_FLG            = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND BA.ORG_STRU_TP_CD     = '").append(ORG_STRU_TP.BASIC_ORGANIZATION_STRUCTURE_FOR_SALES_SUMMARY).append("'");
        sb.append("    AND BA.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = OFA.GLBL_CMPY_CD");
        sb.append("    AND T.TOC_CD              = OFA.TOC_CD");
        sb.append("    AND OFA.EZCANCELFLAG      = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = SO.GLBL_CMPY_CD ");
        sb.append("    AND T.TOC_CD              = SO.TOC_CD");
        sb.append("    AND SO.EZCANCELFLAG        = '0'");
        sb.append("    AND OFA.GLBL_CMPY_CD      = SP.GLBL_CMPY_CD");
        sb.append("    AND OFA.PSN_CD            = SP.PSN_CD");
        sb.append("    AND SP.EFF_FROM_DT        <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (SP.EFF_THRU_DT       >= '").append(scrnMsg.slsDt.getValue()).append("' OR SP.EFF_THRU_DT IS NULL)");
        sb.append("    AND SP.EZCANCELFLAG       = '0'");
        sb.append("    AND T.GLBL_CMPY_CD        = CB.GLBL_CMPY_CD (+)");
        sb.append("    AND T.COA_BR_CD           = CB.COA_BR_CD (+)");
        sb.append("    AND CB.EZCANCELFLAG (+)   = '0'");

        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[5];
        whereArray0[0] = "Resource ID";
        whereArray0[1] = "PSN_NUM";
        if (!ZYPCommonFunc.hasValue(scrnMsg.slsRepPsnNum) && !ZYPCommonFunc.hasValue(scrnMsg.slsRepTocNm)) { // S21_NA#8393
            whereArray0[2] = "%";
        } else {
            whereArray0[2] = scrnMsg.slsRepPsnNum.getValue();
        }
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray0[4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[5];
        whereArray1[0] = "Name";
        whereArray1[1] = "TOC_NM";
        whereArray1[2] = scrnMsg.slsRepTocNm.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add Start
        whereArray1[4] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28425 Add End
        whereList.add(whereArray1);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Resource ID";
        columnArray0[1] = "PSN_NUM";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Name";
        columnArray1[1] = "TOC_NM";
        columnArray1[2] = BigDecimal.valueOf(30);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Line of Business";
        columnArray2[1] = "LINE_BIZ_TP_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Branch";
        columnArray3[1] = "COA_BR_NM";
        columnArray3[2] = BigDecimal.valueOf(20);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Role";
        columnArray4[1] = "ORG_FUNC_ROLE_TP_NM";
        columnArray4[2] = BigDecimal.valueOf(20);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "TOC_CD";
        columnArray5[1] = "TOC_CD";
        columnArray5[2] = BigDecimal.valueOf(0);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PSN_NUM"; // S21_NA#8393
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
}

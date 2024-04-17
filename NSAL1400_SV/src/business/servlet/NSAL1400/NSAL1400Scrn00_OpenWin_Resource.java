/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1400;

import static business.servlet.NSAL1400.common.NSAL1400CommonLogic.clearPopupParams;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 * NSAL1400 Named Customer Resource setup
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/12/01   Hitachi         T.Tomita        Create          QC#18362
 *</pre>
 */
public class NSAL1400Scrn00_OpenWin_Resource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        int select = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(select).svcLineBizCd_A);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL1400BMsg scrnMsg = (NSAL1400BMsg) bMsg;
        clearPopupParams(scrnMsg);
        int select = getButtonSelectNumber();
        setValue(scrnMsg.xxRowNum_P, new BigDecimal(select));

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Resource Search";
        // SQL
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT");
        sb.append("      DISTINCT ");
        sb.append("      C.GLBL_CMPY_CD ");
        sb.append("     ,C.EZCANCELFLAG ");
        sb.append("     ,A.SVC_LINE_BIZ_CD");
        sb.append("     ,C.PSN_CD");
        sb.append("     ,NVL(D.PSN_FIRST_NM, '')");
        sb.append("         || DECODE(D.PSN_MID_NM,  NULL, '', ' ' || D.PSN_MID_NM)");
        sb.append("         || DECODE(D.PSN_LAST_NM, NULL, '', ' ' || D.PSN_LAST_NM)");
        sb.append("         AS XX_CUST_PSN_NM");
        sb.append(" FROM");
        sb.append("      SVC_RG             A");
        sb.append("     ,SVC_RG_BR_RELN     B");
        sb.append("     ,SVC_BR_RESRC_RELN  C");
        sb.append("     ,S21_PSN            D");
        sb.append(" WHERE");
        sb.append("         A.GLBL_CMPY_CD =  '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("     AND A.EZCANCELFLAG =  '0'");
        sb.append("     AND A.SVC_LINE_BIZ_CD =  '").append(scrnMsg.A.no(select).svcLineBizCd_A.getValue()).append("'");
        sb.append("     AND A.SVC_RG_ACTV_FLG =  'Y'");
        sb.append("     AND A.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND NVL(A.EFF_THRU_DT, '").append(scrnMsg.slsDt.getValue()).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND A.GLBL_CMPY_CD =  B.GLBL_CMPY_CD");
        sb.append("     AND A.SVC_RG_PK =  B.SVC_RG_PK");
        sb.append("     AND B.EZCANCELFLAG =  '0'");
        sb.append("     AND B.GLBL_CMPY_CD =  C.GLBL_CMPY_CD");
        sb.append("     AND B.SVC_CONTR_BR_CD =  C.SVC_CONTR_BR_CD");
        sb.append("     AND C.EZCANCELFLAG =  '0'");
        sb.append("     AND C.SVC_BR_RESRC_ACTV_FLG =  'Y'");
        sb.append("     AND C.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND NVL(C.EFF_THRU_DT, '").append(scrnMsg.slsDt.getValue()).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND C.GLBL_CMPY_CD =  D.GLBL_CMPY_CD");
        sb.append("     AND C.PSN_CD =  D.PSN_CD");
        sb.append("     AND D.EZCANCELFLAG =  '0'");
        sb.append("     AND D.DEL_FLG =  'N'");
        sb.append("     AND D.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("     AND NVL(D.EFF_THRU_DT, '").append(scrnMsg.slsDt.getValue()).append("') >= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append(" ORDER BY");
        sb.append("      A.SVC_LINE_BIZ_CD");
        sb.append("     ,C.PSN_CD");
        params[2] = sb.toString();

        // Search Condition
        List<Object[]> whereList = new ArrayList<Object[]>(5);
        // Parson Code
        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Parson Code";
        whereArray0[1] = "PSN_CD";
        String psnCd = scrnMsg.A.no(select).contrAdminPsnCd_A.getValue();
        if (!hasValue(psnCd)) {
            psnCd = "%";
        }
        whereArray0[2] = psnCd;
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        // Parson Name
        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Parson Name";
        whereArray1[1] = "XX_CUST_PSN_NM";
        whereArray1[2] = "";
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        params[3] = whereList;

        // Columns
        List<Object[]> columnList = new ArrayList<Object[]>(5);
        // LOB
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "LOB";
        columnArray0[1] = "SVC_LINE_BIZ_CD";
        columnArray0[2] = BigDecimal.valueOf(5);
        columnArray0[3] = FLG_OFF_N;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Parson Code";
        columnArray1[1] = "PSN_CD";
        columnArray1[2] = BigDecimal.valueOf(16);
        columnArray1[3] = FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Parson Name";
        columnArray2[1] = "XX_CUST_PSN_NM";
        columnArray2[2] = BigDecimal.valueOf(40);
        columnArray2[3] = FLG_ON_Y;
        columnList.add(columnArray2);

        params[4] = columnList;

        // Sort
        List<Object[]> sortConditionList = new ArrayList<Object[]>(5);
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "PSN_CD";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;


        // Result
        params[6] = scrnMsg.Z;

        setArgForSubScreen(params);
    }
}

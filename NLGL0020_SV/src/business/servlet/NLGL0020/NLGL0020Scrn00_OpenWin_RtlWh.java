/**
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLGL0020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLGL0020.NLGL0020CMsg;
import business.servlet.NLGL0020.common.NLGL0020CommonLogic;
import business.servlet.NLGL0020.constant.NLGL0020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * PO Maintenance
 * 
 * Date           Company         Name              Create/Update     Defect No
 * ------------------------------------------------------------------------------------
 * 05/23/2017     CITS            S.Endo            Create            RS#3173
 *</pre>
 */
public class NLGL0020Scrn00_OpenWin_RtlWh extends S21CommonHandler implements NLGL0020Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = NLGL0020CommonLogic.setRequestData_NLGL0020Scrn00_Function_20();
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLGL0020BMsg scrnMsg = (NLGL0020BMsg) bMsg;
        NLGL0020CMsg bizMsg = (NLGL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        setArgForSubScreen(getAddressPopupParam(scrnMsg, getGlobalCompanyCode()));
    }
    
    private Object[] getAddressPopupParam(NLGL0020BMsg scrnMsg, String glblCmpyCd) {
        scrnMsg.P.clear();
        Object[] params =new Object[7];
        //Suffix
        params[0]  = "";
        //Window Title
        params[1] = "WH Popup";
        //Select table Name
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    RW.RTL_WH_CD ");
        baseSql.append("   ,RW.RTL_WH_DESC_TXT ");
        baseSql.append("   ,WO.WH_OWNR_DESC_TXT ");
        baseSql.append("   ,RW.INVTY_OWNR_CD ");
        baseSql.append("   ,WW.WH_CD        AS WMS_WH_CD ");
        baseSql.append("   ,WW.WMS_DESC_NM  AS WMS_WH_NM ");
        baseSql.append("   ,WW.GLBL_CMPY_CD ");
        baseSql.append("   ,WW.EZCANCELFLAG ");
        baseSql.append("FROM ");
        baseSql.append("    WMS_WH  WW ");
        baseSql.append("   ,RTL_WH  RW ");
        baseSql.append("   ,WH_OWNR WO ");
        baseSql.append("WHERE ");
        baseSql.append("    WW.GLBL_CMPY_CD = 'ADB' ");
        baseSql.append("AND WW.EZCANCELFLAG = '0' ");
        baseSql.append("AND WW.GLBL_CMPY_CD = RW.GLBL_CMPY_CD ");
        baseSql.append("AND WW.WH_CD        = RW.WMS_WH_CD ");
        baseSql.append("AND RW.EZCANCELFLAG = '0' ");
        baseSql.append("AND RW.GLBL_CMPY_CD = WO.GLBL_CMPY_CD ");
        baseSql.append("AND RW.WH_OWNR_CD   = WO.WH_OWNR_CD ");
        baseSql.append("AND WO.EZCANCELFLAG = '0' ");
        baseSql.append("UNION ");
        baseSql.append("SELECT DISTINCT ");
        baseSql.append("    RW.RTL_WH_CD ");
        baseSql.append("   ,RW.RTL_WH_DESC_TXT ");
        baseSql.append("   ,WO.WH_OWNR_DESC_TXT ");
        baseSql.append("   ,RW.INVTY_OWNR_CD ");
        baseSql.append("   ,TL.WH_OWNR_CD        AS WMS_WH_CD ");
        baseSql.append("   ,WO.WH_OWNR_DESC_TXT  AS WMS_WH_NM ");
        baseSql.append("   ,TL.GLBL_CMPY_CD ");
        baseSql.append("   ,TL.EZCANCELFLAG ");
        baseSql.append("FROM ");
        baseSql.append("    TPL_LOC TL ");
        baseSql.append("   ,WH_OWNR WO ");
        baseSql.append("   ,RTL_WH  RW ");
        baseSql.append("WHERE ");
        baseSql.append("    TL.GLBL_CMPY_CD = 'ADB' ");
        baseSql.append("AND TL.EZCANCELFLAG = '0' ");
        baseSql.append("AND TL.GLBL_CMPY_CD = WO.GLBL_CMPY_CD ");
        baseSql.append("AND TL.WH_OWNR_CD   = WO.WH_OWNR_CD ");
        baseSql.append("AND WO.EZCANCELFLAG = '0' ");
        baseSql.append("AND TL.GLBL_CMPY_CD = RW.GLBL_CMPY_CD ");
        baseSql.append("AND TL.TPL_LOC_CD   = RW.RTL_WH_CD ");
        baseSql.append("AND RW.EZCANCELFLAG = '0' ");
        baseSql.append("AND NOT EXISTS (SELECT ");
        baseSql.append("                    1 ");
        baseSql.append("                FROM ");
        baseSql.append("                    RTL_WH RL ");
        baseSql.append("                   ,WMS_WH WW ");
        baseSql.append("                WHERE ");
        baseSql.append("                    RL.GLBL_CMPY_CD = TL.GLBL_CMPY_CD ");
        baseSql.append("                AND RL.RTL_WH_CD    = TL.TPL_LOC_CD ");
        baseSql.append("                AND RL.EZCANCELFLAG = '0' ");
        baseSql.append("                AND RL.GLBL_CMPY_CD = WW.GLBL_CMPY_CD ");
        baseSql.append("                AND RL.WMS_WH_CD    = WW.WH_CD ");
        baseSql.append("                AND WW.EZCANCELFLAG = '0' ");
        baseSql.append("               ) ");

        params[2] = baseSql.toString();

        //Where List
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 =new Object[4];
        whereArray1[0] = "WH Code";
        whereArray1[1] = "RTL_WH_CD";
        whereArray1[2] = scrnMsg.rtlWhCd_01.getValue();
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 =new Object[4];
        whereArray2[0] = "WH Name";
        whereArray2[1] = "RTL_WH_DESC_TXT";
        whereArray2[2] = scrnMsg.rtlWhNm_01.getValue();
        whereArray2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 =new Object[4];
        whereArray3[0] = "WMS WH Code";
        whereArray3[1] = "WMS_WH_CD";
        whereArray3[2] = scrnMsg.whCd_02.getValue();
        whereArray3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 =new Object[4];
        whereArray4[0] = "Owner";
        whereArray4[1] = "INVTY_OWNR_CD";
        whereArray4[2] = scrnMsg.invtyOwnrCd_01.getValue();
        whereArray4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[3]=whereList;

        //Column List
        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "WH Code";
        columnArray1[1] = "RTL_WH_CD";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "WH Name";
        columnArray2[1] = "RTL_WH_DESC_TXT";
        columnArray2[2] = BigDecimal.valueOf(50);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "WH Owner";
        columnArray3[1] = "WH_OWNR_DESC_TXT";
        columnArray3[2] = BigDecimal.valueOf(50);
        columnArray3[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Owner";
        columnArray4[1] = "INVTY_OWNR_CD";
        columnArray4[2] = BigDecimal.valueOf(3);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "WMS WH Code";
        columnArray5[1] = "WMS_WH_CD";
        columnArray5[2] = BigDecimal.valueOf(20);
        columnArray5[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "WMS WH Name";
        columnArray6[1] = "WMS_WH_NM";
        columnArray6[2] = BigDecimal.valueOf(60);
        columnArray6[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray6);

        params[4]=columnList;

        //Sort Condition List
        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0]="WMS_WH_CD";
        sortConditionArray1[1]="ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[2];
        sortConditionArray2[0]="RTL_WH_CD";
        sortConditionArray2[1]="ASC";
        sortConditionList.add(sortConditionArray2);
        
        params[5]=sortConditionList;
        params[6] = scrnMsg.P;
        return params;
        
    }
}

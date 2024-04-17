/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.FRT_COND;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_CarrierServiceLevel
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2018/12/13   Fujitsu         T.Noguchi       Update          S21_NA#29315
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_CarrierServiceLevel extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        scrnMsg.addCheckItem(scrnMsg.dsOrdCatgCd);
        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        scrnMsg.addCheckItem(scrnMsg.shpgSvcLvlCd);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        scrnMsg.putErrorScreen();

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());

        Object[] params = getParamNWAL1130ForCarrSvcLvl(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Get Param NWAL1130 For Carrier Service Level
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1130 Carrier Service Level
     */
    public static Object[] getParamNWAL1130ForCarrSvcLvl(NWAL2200BMsg scrnMsg) {

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Service Level Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append("    CSL.EZCANCELFLAG ");
        sb.append("   ,CSL.GLBL_CMPY_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_CD ");
        sb.append("   ,CSL.CARR_SVC_LVL_DESC_TXT ");
        sb.append("   ,CSL.CARR_SVC_LVL_SORT_NUM ");
        sb.append("FROM  ");
        sb.append("    FRT_COND_SVC_LVL_RELN RELN ");
        sb.append("   ,DS_ORD_TP_PROC_DFN OTD ");
        sb.append("   ,CARR_SVC_LVL CSL ");
        // 2018/12/13 S21_NA#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("   ,SHPG_SVC_LVL_CARR_RELN SSL ");
            sb.append("   ,( ");
            sb.append("   SELECT ");
            sb.append("      DAC.VND_CD ");
            sb.append("   FROM ");
            sb.append("      DS_ACCT_CARR DAC ");
            sb.append("     ,SHIP_TO_CUST STC ");
            sb.append("   WHERE ");
            sb.append("       STC.SHIP_TO_CUST_CD = '").append(scrnMsg.shipToCustCd.getValue()).append("' ");
            sb.append("   AND STC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("   AND STC.EZCANCELFLAG    = '0' ");
            sb.append("   AND DAC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("   AND DAC.DS_ACCT_NUM     IS NULL ");
            sb.append("   AND DAC.LOC_NUM         = STC.LOC_NUM ");
            sb.append("   AND DAC.EFF_FROM_DT    <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("   AND (DAC.EFF_THRU_DT   >= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("        OR DAC.EFF_THRU_DT IS NULL) ");
            sb.append("   AND DAC.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("   AND DAC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("   AND DAC.EZCANCELFLAG    = '0' ");
            sb.append("   UNION ALL");
            sb.append("   SELECT ");
            sb.append("      DAC.VND_CD ");
            sb.append("   FROM ");
            sb.append("      DS_ACCT_CARR DAC ");
            sb.append("   WHERE ");
            sb.append("       DAC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("   AND DAC.DS_ACCT_NUM     = '").append(scrnMsg.shipToCustAcctCd.getValue()).append("' ");
            sb.append("   AND DAC.LOC_NUM     IS NULL ");
            sb.append("   AND DAC.EFF_FROM_DT    <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("   AND (DAC.EFF_THRU_DT   >= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("        OR DAC.EFF_THRU_DT IS NULL) ");
            sb.append("   AND DAC.LINE_BIZ_TP_CD  = '").append(scrnMsg.lineBizTpCd.getValue()).append("' ");
            sb.append("   AND DAC.DS_BIZ_AREA_CD  = '").append(scrnMsg.dsBizAreaCd.getValue()).append("' ");
            sb.append("   AND DAC.EZCANCELFLAG    = '0' ");
            sb.append("   AND NOT EXISTS ( ");
            sb.append("       SELECT ");
            sb.append("           1 ");
            sb.append("       FROM ");
            sb.append("           DS_ACCT_CARR DAC2 ");
            sb.append("          ,SHIP_TO_CUST STC ");
            sb.append("       WHERE ");
            sb.append("           STC.SHIP_TO_CUST_CD = '").append(scrnMsg.shipToCustCd.getValue()).append("' ");
            sb.append("       AND STC.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
            sb.append("       AND STC.EZCANCELFLAG    = '0' ");
            sb.append("       AND DAC2.GLBL_CMPY_CD = DAC.GLBL_CMPY_CD ");
            sb.append("       AND DAC2.LOC_NUM      = STC.LOC_NUM ");
            sb.append("       AND DAC2.EFF_FROM_DT <= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("       AND (DAC.EFF_THRU_DT >= '").append(scrnMsg.slsDt.getValue()).append("' ");
            sb.append("            OR DAC.EFF_THRU_DT IS NULL) ");
            sb.append("       AND DAC2.LINE_BIZ_TP_CD = DAC.LINE_BIZ_TP_CD ");
            sb.append("       AND DAC2.DS_BIZ_AREA_CD = DAC.DS_BIZ_AREA_CD ");
            sb.append("       AND DAC2.EZCANCELFLAG   = DAC.EZCANCELFLAG ");
            sb.append("       ) ");
            sb.append("   )DACV ");
        }
        // 2018/12/13 S21_NA#29315 Add End
        sb.append("WHERE ");
        sb.append("    RELN.GLBL_CMPY_CD    = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND RELN.EZCANCELFLAG    = '0' ");
        sb.append("AND OTD.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND OTD.EZCANCELFLAG     = '0' ");
        sb.append("AND OTD.DS_ORD_TP_CD     = '").append(scrnMsg.dsOrdTpCd.getValue()).append("' ");
        sb.append("AND OTD.LINE_BIZ_TP_CD   = RELN.LINE_BIZ_TP_CD ");
        sb.append("AND RELN.FRT_COND_CD     = '").append(scrnMsg.frtCondCd.getValue()).append("'");
        sb.append("AND RELN.SHPG_SVC_LVL_CD = '").append(scrnMsg.shpgSvcLvlCd.getValue()).append("'");
        sb.append("AND CSL.GLBL_CMPY_CD     = RELN.GLBL_CMPY_CD ");
        sb.append("AND CSL.EZCANCELFLAG     = '0' ");
        sb.append("AND CSL.CARR_SVC_LVL_CD  = RELN.CARR_SVC_LVL_CD ");
        // 2018/12/13 S21_NA#29315 Add Start
        if (!FRT_COND.COLLECT.equals(scrnMsg.frtCondCd.getValue())) {
            sb.append("AND RELN.GLBL_CMPY_CD    = SSL.GLBL_CMPY_CD ");
            sb.append("AND RELN.SHPG_SVC_LVL_CD = SSL.SHPG_SVC_LVL_CD ");
            sb.append("AND RELN.CARR_SVC_LVL_CD = SSL.CARR_SVC_LVL_CD ");
            sb.append("AND SSL.EZCANCELFLAG   = '0' ");
            sb.append("AND SSL.CARR_CD        = DACV.VND_CD ");
        }
        // 2018/12/13 S21_NA#29315 Add End
        params[2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray = new Object[4];
        whereArray[0] = "Carr Svc Lvl Nm";
        whereArray[1] = "CARR_SVC_LVL_DESC_TXT";
        if (!ZYPCommonFunc.hasValue(scrnMsg.carrSvcLvlDescTxt)) { //S21_NA#8393
            whereArray[2] = "%";
        } else {
            whereArray[2] = scrnMsg.carrSvcLvlDescTxt.getValue();
        }
        whereArray[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray);

        params[3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Carr Svc Lvl Cd";
        columnArray0[1] = "CARR_SVC_LVL_CD";
        columnArray0[2] = BigDecimal.valueOf(13);
        columnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Carr Svc Lvl Nm";
        columnArray1[1] = "CARR_SVC_LVL_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(50);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        params[4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = "CARR_SVC_LVL_SORT_NUM";
        sortConditionArray0[1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        params[5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.Z);
        params[6] = scrnMsg.Z;

        return params;
    }
}

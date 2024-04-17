/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Business ID : NMAL2020 Manage Shipping Orders
 * Function Name : Open Common PopUp(NWAL1130)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/03/2016   CSAI            D.Fukaya        Create          QC# 2200
 *</pre>
 */
public class NLBL3200Scrn00_OpenWin_CarrInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no process
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3200BMsg scrnMsg = (NLBL3200BMsg) bMsg;
        int index = getButtonSelectNumber();
        scrnMsg.A.no(index).carrCd_A1.getValue();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Service Popup";
        params[2] = getSelectSQL(scrnMsg, index);
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3200Constant.EVENT_NM_NLBL3200SCRN00_OPENWIN_CARRINFO);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NLBL3200BMsg scrnMsg, int index) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Carrier Code", "CARR_CD", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Carrier Name", "CARR_NM", scrnMsg.A.no(index).carrNm_A1.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj3 = {"Service Level Name", "SHPG_SVC_LVL_DESC_TXT", null, ZYPConstant.FLG_ON_Y };

        int selectPulldownNumber = selectNumberForShpgSvcLvlPulldown(scrnMsg, scrnMsg.A.no(index).actlShpgSvcLvlCd_A1);
        if (selectPulldownNumber >= 0) {
            whereObj3[2] = scrnMsg.shpgSvcLvlDescTxt_PD.no(selectPulldownNumber).getValue();
        } 

        Object[] whereObj4 = {"Carrier Srv Lvl Name", "CARR_SVC_LVL_DESC_TXT", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NLBL3200BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Carrier Code", "CARR_CD", new BigDecimal("15"), ZYPConstant.FLG_OFF_N };
        Object[] colObj2 = {"Carrier Name", "CARR_NM", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj3 = {"Service Level Code", "SHPG_SVC_LVL_CD", new BigDecimal("15"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Service Level Name", "SHPG_SVC_LVL_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
        Object[] colObj5 = {"Carrier Service Level Name", "CARR_SVC_LVL_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);

        return colList;
    }

    private List<Object> getSortSetting(NLBL3200BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"CARR_CD", "ASC"};
        Object[] sortObj2 = {"SHPG_SVC_LVL_SORT_NUM", "ASC"};
        sortList.add(sortObj1);
        sortList.add(sortObj2);

        return sortList;
    }

    private String getSelectSQL(NLBL3200BMsg scrnMsg, int index) {

        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        sb.append("SELECT").append("\n");
        sb.append(" OC.CARR_CD").append("\n");
        sb.append(", OC.CARR_NM").append("\n");
        sb.append(", SSL.SHPG_SVC_LVL_CD").append("\n");
        sb.append(", SSL.SHPG_SVC_LVL_DESC_TXT").append("\n");
        sb.append(", CSL.CARR_SVC_LVL_DESC_TXT").append("\n");
        sb.append(", OC.GLBL_CMPY_CD").append("\n");
        sb.append(", OC.EZCANCELFLAG").append("\n");
        sb.append(", SSL.SHPG_SVC_LVL_SORT_NUM").append("\n");
        sb.append("FROM OTBD_CARR_V OC").append("\n");
        sb.append(", SHPG_SVC_LVL_CARR_RELN SCR").append("\n");
        sb.append(", SHPG_SVC_LVL SSL").append("\n");
        sb.append(", CARR_SVC_LVL CSL").append("\n");
        sb.append(" WHERE OC.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("  AND OC.EZCANCELFLAG = '0'").append("\n");
        sb.append("  AND OC.GLBL_CMPY_CD = SCR.GLBL_CMPY_CD(+)").append("\n");
        sb.append("  AND OC.CARR_CD = SCR.CARR_CD(+)").append("\n");
        sb.append("  AND SCR.EZCANCELFLAG(+) = '0'").append("\n");
        sb.append("  AND SCR.GLBL_CMPY_CD = SSL.GLBL_CMPY_CD(+)").append("\n");
        sb.append("  AND SCR.SHPG_SVC_LVL_CD = SSL.SHPG_SVC_LVL_CD(+)").append("\n");
        sb.append("  AND SSL.EZCANCELFLAG(+) = '0'").append("\n");
        sb.append("  AND SCR.GLBL_CMPY_CD = CSL.GLBL_CMPY_CD(+)").append("\n");
        sb.append("  AND SCR.CARR_SVC_LVL_CD = CSL.CARR_SVC_LVL_CD(+)").append("\n");
        sb.append("  AND CSL.EZCANCELFLAG(+) = '0'").append("\n");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());

        return sql;
    }

    private int selectNumberForShpgSvcLvlPulldown(NLBL3200BMsg scrnMsg, EZDBStringItem selectShpgSvcLvlCd){
        for(int i = 0; i<scrnMsg.shpgSvcLvlCd_PD.length() && ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_PD.no(i)); i++){
            if(equals(scrnMsg.shpgSvcLvlCd_PD.no(i), selectShpgSvcLvlCd)){
                return i;
            }
        }
        return -1;
    }

    private boolean equals(EZDBStringItem a, EZDBStringItem b){

        if(ZYPCommonFunc.hasValue(a) && ZYPCommonFunc.hasValue(b)){
            String sa = a.getValue();
            String sb = b.getValue();

            return sa.equals(sb);
        } else {
            return false;
        }
    }
}

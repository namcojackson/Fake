/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3070;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3070.constant.NLBL3070Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Delivery Scheduling / Manage Deliveries
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/02/2015   Fujitsu         Y.Taoka         Create          N/A
 * 02/26/2016   CSAI            Y.Imazu         Update          QC#2046, 2201
 *</pre>
 */
public class NLBL3070Scrn00_OpenWin_CarrInfo extends S21CommonHandler {

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

        NLBL3070BMsg scrnMsg = (NLBL3070BMsg) bMsg;
        int index = getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Popup";
        params[2] = getSelectSQL(scrnMsg, index);
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3070Constant.EVENT_NM_OPENWIN_CARR_LINE);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NLBL3070BMsg scrnMsg, int index) {

        List<Object> whereList = new ArrayList<Object>();

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            Object[] whereObj1 = {"Carrier Code", "CARR_CD", null, ZYPConstant.FLG_ON_Y };
            Object[] whereObj2 = {"Carrier Name", "CARR_NM", scrnMsg.A.no(index).carrNm_A1.getValue(), ZYPConstant.FLG_ON_Y };

            whereList.add(whereObj1);
            whereList.add(whereObj2);

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            Object[] whereObj1 = {"Carrier Code", "CARR_CD", null, ZYPConstant.FLG_ON_Y };
            Object[] whereObj2 = {"Carrier Name", "CARR_NM", scrnMsg.B.no(index).carrNm_B1.getValue(), ZYPConstant.FLG_ON_Y };

            whereList.add(whereObj1);
            whereList.add(whereObj2);
        }

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NLBL3070BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Carrier Code", "CARR_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Carrier Name", "CARR_NM", new BigDecimal("60"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting(NLBL3070BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"CARR_SORT_NUM", "ASC"};

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NLBL3070BMsg scrnMsg, int index) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n");
        sb.append("SELECT                                             ").append("\n");
        sb.append("       OC.CARR_CD                                  ").append("\n");
        sb.append("     , OC.CARR_NM                                  ").append("\n");
        sb.append("     , OC.CARR_SORT_NUM                            ").append("\n");
        sb.append("     , OC.GLBL_CMPY_CD                             ").append("\n");
        sb.append("     , OC.EZCANCELFLAG                             ").append("\n");
        sb.append("  FROM OTBD_CARR_V OC                              ").append("\n");
        sb.append(" WHERE OC.GLBL_CMPY_CD   = '#GLBL_CMPY_CD#'        ").append("\n");
        sb.append("   AND OC.EZCANCELFLAG   = '0'                     ").append("\n");

        if ((ZYPCommonFunc.hasValue(scrnMsg.A.no(index).shpgSvcLvlCd_A1) && NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue()))
                || (ZYPCommonFunc.hasValue(scrnMsg.B.no(index).shpgSvcLvlCd_B1) && NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue()))) {
            sb.append("   AND EXISTS ( SELECT *                                 ").append("\n");
            sb.append("                FROM   SHPG_SVC_LVL_CARR_RELN CR         ").append("\n");
            sb.append("                WHERE  OC.GLBL_CMPY_CD = CR.GLBL_CMPY_CD ").append("\n");
            sb.append("                   AND OC.CARR_CD      = CR.CARR_CD      ").append("\n");
            sb.append("                   AND CR.SHPG_SVC_LVL_CD = '#SHPG_SVC_LVL_CD#'").append("\n");
            sb.append("                   AND CR.EZCANCELFLAG = '0' )           ").append("\n");
        }

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", scrnMsg.glblCmpyCd.getValue());

        if (NLBL3070Constant.TAB_ID_SCHD.equals(scrnMsg.xxDplyTab.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).shpgSvcLvlCd_A1)) {

                sql = sql.replaceAll("#SHPG_SVC_LVL_CD#", scrnMsg.A.no(index).shpgSvcLvlCd_A1.getValue());
            }

        } else if (NLBL3070Constant.TAB_ID_DELV.equals(scrnMsg.xxDplyTab.getValue())) {

            if (ZYPCommonFunc.hasValue(scrnMsg.B.no(index).shpgSvcLvlCd_B1)) {

                sql = sql.replaceAll("#SHPG_SVC_LVL_CD#", scrnMsg.B.no(index).shpgSvcLvlCd_B1.getValue());
            }
        }

        return sql;
    }
}

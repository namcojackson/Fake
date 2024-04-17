/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3200;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLBL3200.NLBL3200BMsg;
import business.servlet.NLBL3200.constant.NLBL3200Constant;

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
public class NLBL3200Scrn00_OpenWin_AplyCarrCode extends S21CommonHandler {

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

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Popup";
        params[2] = getSelectSQL(scrnMsg);
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, NLBL3200Constant.EVENT_NM_NLBL3200SCRN00_OPENWIN_APLYCARRCODE);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NLBL3200BMsg scrnMsg) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Carrier Code", "CARR_CD", null, ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Carrier Name", "CARR_NM", scrnMsg.carrNm_D1.getValue(), ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NLBL3200BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Carrier Code", "CARR_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Carrier Name", "CARR_NM", new BigDecimal("60"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting(NLBL3200BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = {"CARR_SORT_NUM", "ASC"};
        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NLBL3200BMsg scrnMsg) {

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

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());

        return sql;
    }
}

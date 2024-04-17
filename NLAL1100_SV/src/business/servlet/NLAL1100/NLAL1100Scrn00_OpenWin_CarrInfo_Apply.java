/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL1100.constant.NLAL1100Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/09/28   CITS            R.Shimamoto     Create          QC#18669
 *</pre>
 */
public class NLAL1100Scrn00_OpenWin_CarrInfo_Apply extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Carrier Popup";
        params[2] = getSelectSQL(scrnMsg, index);
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting(scrnMsg);
        params[5] = getSortSetting(scrnMsg);
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, NLAL1100Constant.EVENT_NM_OPENWIN_CARR_APPLY);
        setArgForSubScreen(params);
    }

    /**
     * getSearchConditionSetting
     * @param scrnMsg NLAL1100BMsg
     * @param index int
     * @return List<Object>
     */
    private List<Object> getSearchConditionSetting(NLAL1100BMsg scrnMsg, int index) {

        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = {"Carrier Code", "CARR_CD", scrnMsg.carrCd_G.getValue(), ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Carrier Name", "CARR_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    /**
     * getDisplayColumnSetting
     * @param scrnMsg NLAL1100BMsg
     * @return List<Object>
     */
    private List<Object> getDisplayColumnSetting(NLAL1100BMsg scrnMsg) {

        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Carrier Code", "CARR_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Carrier Name", "CARR_NM", new BigDecimal("60"), ZYPConstant.FLG_ON_Y };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    /**
     * getSortSetting
     * @param scrnMsg NLAL1100BMsg
     * @return List<Object>
     */
    private List<Object> getSortSetting(NLAL1100BMsg scrnMsg) {

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"CARR_SORT_NUM", "ASC"};
        sortList.add(sortObj1);

        return sortList;
    }

    /**
     * getSelectSQL
     * @param scrnMsg NLAL1100BMsg
     * @param index Integer
     * @return String
     */
    private String getSelectSQL(NLAL1100BMsg scrnMsg, int index) {

        StringBuffer sb = new StringBuffer();

        sb.append("\n");
        sb.append("SELECT").append("\n");
        sb.append("    OC.CARR_CD").append("\n");
        sb.append("   ,OC.CARR_NM").append("\n");
        sb.append("   ,OC.CARR_SORT_NUM").append("\n");
        sb.append("   ,OC.GLBL_CMPY_CD").append("\n");
        sb.append("   ,OC.EZCANCELFLAG").append("\n");
        sb.append("FROM").append("\n");
        sb.append("    OTBD_CARR_V OC").append("\n");
        sb.append("WHERE").append("\n");
        sb.append("    OC.GLBL_CMPY_CD = '#GLBL_CMPY_CD#'").append("\n");
        sb.append("AND OC.EZCANCELFLAG = '0' ").append("\n");

        if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_G)) {

            sb.append("AND EXISTS (SELECT").append("\n");
            sb.append("                1").append("\n");
            sb.append("            FROM").append("\n");
            sb.append("                SHPG_SVC_LVL_CARR_RELN CR").append("\n");
            sb.append("            WHERE").append("\n");
            sb.append("                CR.GLBL_CMPY_CD    = OC.GLBL_CMPY_CD").append("\n");
            sb.append("            AND CR.CARR_CD         = OC.CARR_CD").append("\n");
            sb.append("            AND CR.SHPG_SVC_LVL_CD = '#SHPG_SVC_LVL_CD#'").append("\n");
            sb.append("            AND CR.EZCANCELFLAG    = '0'").append("\n");
            sb.append("           )").append("\n");
        }

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", scrnMsg.glblCmpyCd_G1.getValue());
        sql = sql.replaceAll("#SLS_DT#", scrnMsg.slsDt_G1.getValue());

        if (ZYPCommonFunc.hasValue(scrnMsg.shpgSvcLvlCd_G)) {

            sql = sql.replaceAll("#SHPG_SVC_LVL_CD#", scrnMsg.shpgSvcLvlCd_G.getValue());
        }

        return sql;
    }
}

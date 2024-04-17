/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_0;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_1;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_2;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_3;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_4;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_5;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_6;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.IDX_7;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NMAL6820Scrn00_OpenWin_Supplier extends S21CommonHandler {
    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_21, "OpenWin_Supplier");

        Object[] params = getPopupParam(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Set Supplier Popup param
     * @param scrnMsg NMAL6820BMsg
     * @return Supplier Popup Param (NWAL1130) Object[]
     */
    private Object[] getPopupParam(NMAL6820BMsg scrnMsg) {
        Object[] params = new Object[IDX_7];

        params[IDX_0] = "";
        params[IDX_1] = "Supplier/Supplier Site Search";
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" V.EZCANCELFLAG,");
        sb.append(" V.GLBL_CMPY_CD,");
        sb.append(" V.VND_CD,");
        sb.append(" V.LOC_NM,");
        sb.append(" V.ARCS_SPLY_SITE_CD,");
        sb.append(" V.INAC_DT,");
        sb.append(" PV.PRNT_VND_CD,");
        sb.append(" PV.PRNT_VND_NM,");
        sb.append(" PV.SPLY_TP_CD,");
        sb.append(" PV.PRNT_VND_TP_CD");
        sb.append(" FROM PRNT_VND PV,");
        sb.append(" VND V,");
        sb.append(" VND_TP_RELN VTR");
        sb.append(" where V.VND_CD = VTR.VND_CD");
        sb.append(" AND V.GLBL_CMPY_CD = VTR.GLBL_CMPY_CD");
        sb.append(" AND V.EZCANCELFLAG = VTR.EZCANCELFLAG");
        sb.append(" AND VTR.VND_TP_CD = '01'");
        sb.append(" AND V.RGTN_STS_CD = 'P20'");
        sb.append(" AND V.EZCANCELFLAG = '0'");
        sb.append(" AND V.EFF_THRU_DT >= TO_CHAR (SYSDATE, 'YYYYMMDD')");
        sb.append(" AND V.GLBL_CMPY_CD = PV.GLBL_CMPY_CD");
        sb.append(" AND V.EZCANCELFLAG = PV.EZCANCELFLAG");
        sb.append(" AND V.PRNT_VND_PK = PV.PRNT_VND_PK");
        sb.append(" AND (PV.INAC_DT IS NULL");
        sb.append(" OR PV.INAC_DT > TO_CHAR (SYSDATE, 'YYYYMMDD'))");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "Supplier Code";
        whereArray1[IDX_1] = "PRNT_VND_CD";
        whereArray1[IDX_2] = scrnMsg.prntVndCd.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "Supplier Name";
        whereArray2[IDX_1] = "PRNT_VND_NM";
        whereArray2[IDX_2] = scrnMsg.prntVndNm.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Supplier Site Code";
        whereArray3[IDX_1] = "VND_CD";
        whereArray3[IDX_2] = scrnMsg.vndCd.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "Supplier Site Name";
        whereArray4[IDX_1] = "LOC_NM";
        whereArray4[IDX_2] = scrnMsg.vndNm.getValue();
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "Supplier Code";
        columnArray1[1] = "PRNT_VND_CD";
        columnArray1[2] = BigDecimal.valueOf(10);
        columnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "Supplier Name";
        columnArray2[1] = "PRNT_VND_NM";
        columnArray2[2] = BigDecimal.valueOf(30);
        columnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Supplier Site Code";
        columnArray3[1] = "VND_CD";
        columnArray3[2] = BigDecimal.valueOf(15);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "Supplier Site Name";
        columnArray4[1] = "LOC_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "PRNT_VND_CD";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "VND_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.P;

        return params;
    }
//    @Override
//    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//
//    }
//
//    @Override
//    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
//
//        //NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//
//        //NMAL6820CMsg bizMsg = new NMAL6820CMsg();
//        //bizMsg.setBusinessID("NMAL6820");
//        //bizMsg.setFunctionCode("20");
//        //EZDMsg.copy(scrnMsg, null, bizMsg, null);
//
//        //return bizMsg;
//
//        return null;
//    }
//
//    @Override
//    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
//
//        //NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
//        //NMAL6820CMsg bizMsg  = (NMAL6820CMsg) cMsg;
//
//        //EZDMsg.copy(bizMsg, null, scrnMsg, null);
//
//    }
}

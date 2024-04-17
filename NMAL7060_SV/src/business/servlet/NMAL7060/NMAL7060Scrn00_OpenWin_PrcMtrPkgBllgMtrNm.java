/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7060;

import static business.servlet.NMAL7060.constant.NMAL7060Constant.EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.NLAM0023E;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_BILLING_METER_CODE;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_BILLING_METER_CODE_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_BILLING_METER_NAME;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_BILLING_METER_NAME_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_BILLING_METER_SEARCH;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_ITEM_CODE;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_ITEM_CODE_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_LEVEL;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_LEVEL_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_METER;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_NM_METER_DISPLAY;
import static business.servlet.NMAL7060.constant.NMAL7060Constant.POPUP_SORT_KEY_ASC;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7060Scrn00_OpenWin_PrcMtrPkgBllgMtrNm
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/19   Fujitsu         W.Honda         Create          N/A
 * 2016/07/08   Fujitsu         W.Honda         Update          QC#11509
 *</pre>
 */
public class NMAL7060Scrn00_OpenWin_PrcMtrPkgBllgMtrNm extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1)) {
                scrnMsg.setMessageInfo(NLAM0023E);
                throw new EZDFieldErrorException();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7060BMsg scrnMsg = (NMAL7060BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);
        int index = getButtonSelectNumber();

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = POPUP_NM_BILLING_METER_SEARCH;
        params[2] = getSelectSQL(scrnMsg);
        params[3] = getSearchConditionSetting(scrnMsg, index);
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        params[6] = scrnMsg.P;

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxMntEventNm, EVENT_NM_OPENWIN_PRCMTRPKGBLLGMTR);
        setArgForSubScreen(params);
    }

    private List<Object> getSearchConditionSetting(NMAL7060BMsg scrnMsg, int index) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[4];
        whereObj1[0] = POPUP_NM_LEVEL_DISPLAY;
        whereObj1[1] = POPUP_NM_LEVEL;
        whereObj1[2] = scrnMsg.B.no(index).bllgMtrLvlNum_B1.getValue();
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        Object[] whereObj2 = new Object[4];
        whereObj2[0] = POPUP_NM_BILLING_METER_CODE_DISPLAY;
        whereObj2[1] = POPUP_NM_BILLING_METER_CODE;
        whereObj2[2] = scrnMsg.B.no(index).mtrLbCd_B1.getValue();
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        Object[] whereObj3 = new Object[4];
        whereObj3[0] = POPUP_NM_BILLING_METER_NAME_DISPLAY;
        whereObj3[1] = POPUP_NM_BILLING_METER_NAME;
        whereObj3[2] = scrnMsg.B.no(index).mtrLbDescTxt_B1.getValue();
        whereObj3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj3);

        Object[] whereObj4 = new Object[4];
        whereObj4[0] = POPUP_NM_METER_DISPLAY;
        whereObj4[1] = POPUP_NM_METER;
        whereObj4[2] = scrnMsg.B.no(index).mtrLbNm_B1.getValue();
        whereObj4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj4);

        Object[] whereObj5 = new Object[4];
        whereObj5[0] = POPUP_NM_ITEM_CODE_DISPLAY;
        whereObj5[1] = POPUP_NM_ITEM_CODE;
        whereObj5[2] = scrnMsg.B.no(index).mdseCd_B1.getValue();
        whereObj5[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj5);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[4];
        colObj1[0] = POPUP_NM_LEVEL_DISPLAY;
        colObj1[1] = POPUP_NM_LEVEL;
        colObj1[2] = new BigDecimal("5");
        colObj1[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj1);

        Object[] colObj2 = new Object[4];
        colObj2[0] = POPUP_NM_BILLING_METER_CODE_DISPLAY;
        colObj2[1] = POPUP_NM_BILLING_METER_CODE;
        colObj2[2] = new BigDecimal("12");
        colObj2[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj2);

        Object[] colObj3 = new Object[4];
        colObj3[0] = POPUP_NM_BILLING_METER_NAME_DISPLAY;
        colObj3[1] = POPUP_NM_BILLING_METER_NAME;
        colObj3[2] = new BigDecimal("50");
        colObj3[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj3);

        Object[] colObj4 = new Object[4];
        colObj4[0] = POPUP_NM_METER_DISPLAY;
        colObj4[1] = POPUP_NM_METER;
        colObj4[2] = new BigDecimal("30");
        colObj4[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj4);

        Object[] colObj5 = new Object[4];
        colObj5[0] = POPUP_NM_ITEM_CODE_DISPLAY;
        colObj5[1] = POPUP_NM_ITEM_CODE;
        colObj5[2] = new BigDecimal("16");
        colObj5[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj5);

        return colList;
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();

        Object[] sortObj1 = new Object[2];
        sortObj1[0] = POPUP_NM_BILLING_METER_CODE;
        sortObj1[1] = POPUP_SORT_KEY_ASC;
        sortList.add(sortObj1);

        Object[] sortObj2 = new Object[2];
        sortObj2[0] = POPUP_NM_LEVEL;
        sortObj2[1] = POPUP_SORT_KEY_ASC;
        sortList.add(sortObj2);

        return sortList;
    }

    private String getSelectSQL(NMAL7060BMsg scrnMsg) {

        // Add Start 2017/02/16 QC#17529
        List<BigDecimal> mdlIdList = new ArrayList<BigDecimal>();
        // Add End 2017/02/16 QC#17529
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT DISTINCT");
        sb.append("     BMM.BLLG_MTR_MAP_LVL_NUM ");
        sb.append("     ,BMM.BLLG_MTR_LB_CD ");
        sb.append("     ,BLML.MTR_LB_DESC_TXT ");
        sb.append("     ,BMM.GLBL_CMPY_CD ");
        sb.append("     ,BMM.EZCANCELFLAG ");
        sb.append("     ,BLML.MTR_LB_NM ");
        sb.append("     ,BLML.INTG_MDSE_CD ");
        sb.append(" FROM ");
        // Mod Start 2017/02/15 QC#17529
//        sb.append("     DS_MDL        DM ");
        sb.append("     ( SELECT DISTINCT ");
        sb.append("             MDL.GLBL_CMPY_CD ");
        sb.append("           , MDL.EZCANCELFLAG ");
        sb.append("           , MDL.MTR_GRP_PK ");
        sb.append("       FROM ");
        sb.append("             DS_MDL MDL ");
        sb.append("           , PRC_MTR_PKG_MDL PMPM ");
        sb.append("       WHERE ");
        sb.append("               MDL.GLBL_CMPY_CD    = '").append(getGlobalCompanyCode()).append("' ");
        sb.append("           AND MDL.EZCANCELFLAG    = '0' ");
        sb.append("           AND MDL.GLBL_CMPY_CD    = PMPM.GLBL_CMPY_CD ");
        sb.append("           AND MDL.EZCANCELFLAG    = PMPM.EZCANCELFLAG ");
        sb.append("           AND MDL.MDL_ID          = PMPM.MDL_ID ");
        if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
            sb.append("           AND (PMPM.PRC_MTR_PKG_PK = ").append(scrnMsg.prcMtrPkgPk.getValue());
        }
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcMtrPkgMdlPk_A1) //
                    || !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1) //
                    || mdlIdList.contains(scrnMsg.A.no(i).mdlId_A1.getValue())) {
                continue;
            }
            if (mdlIdList.size() == 0) {
                if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
                    sb.append(" OR ");
                } else {
                    sb.append(" AND ");
                }
                sb.append(" MDL.MDL_ID IN (");
            } else {
                sb.append(", ");
            }
            sb.append("'").append(scrnMsg.A.no(i).mdlId_A1.getValue()).append("'");
            mdlIdList.add(scrnMsg.A.no(i).mdlId_A1.getValue());
        }
        if (mdlIdList.size() > 0) {
            sb.append(") ");
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.prcMtrPkgPk)) {
            sb.append(") ");
        }
        sb.append("     ) DM ");
        // Mod End 2017/02/15 QC#17529
        sb.append("     ,MTR_GRP      MG ");
        sb.append("     ,DS_MDL_MTR   DMM ");
        sb.append("     ,BLLG_MTR_MAP BMM ");
        sb.append("     ,MTR_LB       ML ");
        sb.append("     ,MTR_LB       BLML ");
        sb.append(" WHERE ");
        sb.append("     DM.GLBL_CMPY_CD      = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND DM.EZCANCELFLAG      = '0' ");
        // Del Start 2017/02/15 QC#17529
//        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
//            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).mdlId_A1)) {
//                continue;
//            }
//            if (i == 0) {
//                sb.append(" AND DM.MDL_ID IN (");
//            } else {
//                sb.append(", ");
//            }
//            sb.append("'").append(scrnMsg.A.no(i).mdlId_A1.getValue()).append("'");
//        }
//        if (scrnMsg.A.getValidCount() > 0) {
//            sb.append(") ");
//        }
        // Del End 2017/02/15 QC#17529
        sb.append(" AND MG.GLBL_CMPY_CD      = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND MG.EZCANCELFLAG      = '0' ");
        sb.append(" AND DM.MTR_GRP_PK        = MG.MTR_GRP_PK ");
        sb.append(" AND DMM.GLBL_CMPY_CD     = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND DMM.EZCANCELFLAG     = '0' ");
        sb.append(" AND MG.MTR_GRP_PK        = DMM.MTR_GRP_PK ");
        sb.append(" AND BMM.GLBL_CMPY_CD     = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND BMM.EZCANCELFLAG     = '0' ");
        sb.append(" AND DMM.MDL_MTR_LB_CD    = BMM.MDL_MTR_LB_CD ");
        // QC#11509 2016/07/08 Add Start
        sb.append(" AND BMM.ACTV_FLG         = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append(" AND BMM.EFF_FROM_DT      <= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append(" AND (BMM.EFF_THRU_DT     >= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append("     OR  BMM.EFF_THRU_DT  IS NULL) ");
        // QC#11509 2016/07/08 Add end
        sb.append(" AND ML.GLBL_CMPY_CD(+)   = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND ML.EZCANCELFLAG(+)   = '0' ");
        sb.append(" AND BMM.MDL_MTR_LB_CD    = ML.MTR_LB_CD(+) ");
        // QC#11509 2016/07/08 Add Start
        sb.append(" AND ML.ACTV_FLG          = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append(" AND ML.EFF_FROM_DT       <= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append(" AND (ML.EFF_THRU_DT      >= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append("     OR  ML.EFF_THRU_DT IS NULL) ");
        // QC#11509 2016/07/08 Add end
        sb.append(" AND BLML.GLBL_CMPY_CD(+) = '").append(getGlobalCompanyCode()).append("' ");
        sb.append(" AND BLML.EZCANCELFLAG(+) = '0' ");
        sb.append(" AND BMM.BLLG_MTR_LB_CD   = BLML.MTR_LB_CD(+) ");
        // QC#11509 2016/07/08 Add Start
        sb.append(" AND BLML.ACTV_FLG        = '").append(ZYPConstant.FLG_ON_Y).append("' ");
        sb.append(" AND BLML.EFF_FROM_DT     <= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append(" AND (BLML.EFF_THRU_DT    >= '").append(ZYPDateUtil.getSalesDate(getGlobalCompanyCode())).append("' ");
        sb.append("     OR  BLML.EFF_THRU_DT IS NULL) ");
        // QC#11509 2016/07/08 Add end
        sb.append(" order by BMM.BLLG_MTR_LB_CD,BMM.BLLG_MTR_MAP_LVL_NUM ");
        String sql = sb.toString();

        return sql;
    }
}

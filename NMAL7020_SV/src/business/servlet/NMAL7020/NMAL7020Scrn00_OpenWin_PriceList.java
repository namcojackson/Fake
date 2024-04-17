/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7020;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NMAL7020.common.NMAL7020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NMAL7020Scrn00_OpenWin_PriceList
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/04/11   Fujitsu         M.Suzuki        Create          N/A
 *</pre>
 */
public class NMAL7020Scrn00_OpenWin_PriceList extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        NMAL7020CommonLogic.clearMandantory(scrnMsg);
        NMAL7020CommonLogic.addCheckItem(scrnMsg);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL7020BMsg scrnMsg = (NMAL7020BMsg) bMsg;
        scrnMsg.xxScrEventNm.setValue("NMAL7020Scrn00_OpenWin_PriceList");
        ZYPTableUtil.clear(scrnMsg.P);

        Object[] params = new Object[7];
        params[0] = "";
        params[1] = "Price List Search";
        params[2] = getSql(getGlobalCompanyCode(), scrnMsg.prcCatgNm_O.getValue());
        params[3] = getSearchConditionSetting(scrnMsg);
        params[4] = getDisplayColumnSetting();
        params[5] = getSortSetting();
        params[6] = scrnMsg.P;
        setArgForSubScreen(params);
    }

    private List<Object> getSortSetting() {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = new Object[4];
        sortObj1[0] = "PRC_CATG_CD";
        sortObj1[1] = "ASC";
        sortList.add(sortObj1);
        return sortList;
    }

    private List<Object> getDisplayColumnSetting() {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = new Object[4];
        colObj1[0] = "Price List ID";
        colObj1[1] = "PRC_CATG_CD";
        colObj1[2] = new BigDecimal("8");
        colObj1[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj1);

        Object[] colObj2 = new Object[4];
        colObj2[0] = "Price List Name";
        colObj2[1] = "PRC_CATG_NM";
        colObj2[2] = new BigDecimal("35");
        colObj2[3] = ZYPConstant.FLG_ON_Y;
        colList.add(colObj2);

        Object[] colObj3 = new Object[4];
        colObj3[0] = "Price List Type";
        colObj3[1] = "PRC_LIST_TP_DESC_TXT";
        colObj3[2] = new BigDecimal("20");
        colObj3[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj3);

        Object[] colObj4 = new Object[4];
        colObj4[0] = "Status";
        colObj4[1] = "DISP_STS";
        colObj4[2] = new BigDecimal("6");
        colObj4[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj4);

        Object[] colObj5 = new Object[5];
        colObj5[0] = "Effective Date From";
        colObj5[1] = "EFF_FROM_DT";
        colObj5[2] = new BigDecimal("12");
        colObj5[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj5);

        Object[] colObj6 = new Object[6];
        colObj6[0] = "Effective Date To";
        colObj6[1] = "EFF_THRU_DT";
        colObj6[2] = new BigDecimal("10");
        colObj6[3] = ZYPConstant.FLG_OFF_N;
        colList.add(colObj6);

        return colList;
    }

    private List<Object> getSearchConditionSetting(NMAL7020BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        Object[] whereObj1 = new Object[4];
        whereObj1[0] = "Price List ID";
        whereObj1[1] = "PRC_CATG_CD";
        whereObj1[2] = null;
        whereObj1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj1);

        Object[] whereObj2 = new Object[4];
        whereObj2[0] = "Price List Name";
        whereObj2[1] = "PRC_CATG_NM";
        whereObj2[2] = scrnMsg.prcCatgNm_O.getValue();
        whereObj2[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj2);

        Object[] whereObj3 = new Object[4];
        whereObj3[0] = "Price List Type";
        whereObj3[1] = "PRC_LIST_TP_DESC_TXT";
        whereObj3[2] = null;
        whereObj3[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj3);

        Object[] whereObj4 = new Object[4];
        whereObj4[0] = "Status";
        whereObj4[1] = "DISP_STS";
        whereObj4[2] = null;
        whereObj4[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereObj4);

        return whereList;
    }

    private String getSql(String globalCompanyCode, String fromPriceList) {

        String slsDt = ZYPDateUtil.getSalesDate();
        String dateFormat = ZYPDateUtil.getDateFormatString(true);
        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT PC.GLBL_CMPY_CD");
        sql.append(",PC.PRC_CATG_CD");
        sql.append(",PC.PRC_CATG_NM");
        sql.append(",PC.EZCANCELFLAG");

        sql.append(",PLT.PRC_LIST_TP_DESC_TXT");
        sql.append(",PLST.PRC_LIST_STRU_TP_DESC_TXT");
        sql.append(",CASE");
        sql.append(" WHEN PC.ACTV_FLG = 'Y' AND PC.DEL_FLG = 'N' AND PC.EFF_FROM_DT <= '");
        sql.append(slsDt);
        sql.append("' AND NVL(PC.EFF_THRU_DT, '99991231') >= '");
        sql.append(slsDt);
        sql.append("' THEN 'Active'");
        sql.append(" WHEN (PC.ACTV_FLG = 'N' AND PC.DEL_FLG = 'N') OR (PC.ACTV_FLG = 'Y' AND PC.DEL_FLG = 'N' AND (PC.EFF_FROM_DT  > '");
        sql.append(slsDt);
        sql.append("' OR NVL(PC.EFF_THRU_DT, '99991231')  < '");
        sql.append(slsDt);
        sql.append("'))");
        sql.append(" THEN 'Inactive'");
        sql.append(" WHEN PC.DEL_FLG = 'Y'");
        sql.append(" THEN 'Delete'");
        sql.append(" ELSE 'None'");

        sql.append(" END DISP_STS ");
        sql.append(" ,PC.PRC_CONTR_NUM ");
        sql.append(" ,PCON.PRC_CONTR_NM ");
        sql.append(" ,PLG.PRC_LIST_GRP_DESC_TXT ");

        sql.append("    ,TO_CHAR(TO_DATE(PC.EFF_FROM_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_FROM_DT");
        sql.append("    ,TO_CHAR(TO_DATE(PC.EFF_THRU_DT,'YYYYMMDD'),'").append(dateFormat).append("') AS EFF_THRU_DT ");

        sql.append(" FROM PRC_CATG PC ");
        sql.append(" ,PRC_LIST_TP PLT ");
        sql.append(" ,PRC_LIST_STRU_TP PLST ");
        sql.append(" ,PRC_CONTR PCON ");
        sql.append(" ,PRC_LIST_GRP PLG ");

        sql.append("  WHERE PC.GLBL_CMPY_CD = '");
        sql.append(globalCompanyCode);
        sql.append("' ");
        sql.append("      AND PC.EZCANCELFLAG = '0' ");

        sql.append("      AND PC.GLBL_CMPY_CD    = PLT.GLBL_CMPY_CD ");
        sql.append("      AND PC.EZCANCELFLAG    = PLT.EZCANCELFLAG ");
        sql.append("     AND PC.PRC_LIST_TP_CD  = PLT.PRC_LIST_TP_CD ");

        sql.append("      AND PLT.GLBL_CMPY_CD   = PLST.GLBL_CMPY_CD ");
        sql.append("      AND PLT.EZCANCELFLAG   = PLST.EZCANCELFLAG ");
        sql.append("      AND PLT.PRC_LIST_STRU_TP_CD = PLST.PRC_LIST_STRU_TP_CD ");

        sql.append("      AND PC.GLBL_CMPY_CD   = PCON.GLBL_CMPY_CD(+) ");
        sql.append("      AND PC.EZCANCELFLAG   = PCON.EZCANCELFLAG(+) ");
        sql.append("      AND PC.PRC_CONTR_NUM  = PCON.PRC_CONTR_NUM(+) ");

        sql.append("      AND PC.GLBL_CMPY_CD   = PLG.GLBL_CMPY_CD(+) ");
        sql.append("      AND PC.EZCANCELFLAG   = PLG.EZCANCELFLAG(+) ");
        sql.append("      AND PC.PRC_LIST_GRP_CD  = PLG.PRC_LIST_GRP_CD(+) ");
        sql.append(" ORDER BY PRC_CATG_CD");

        return sql.toString();
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1610.common;

import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_CMN_CLR;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_CMN_CLS;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.BTN_OK_EVENT_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.CONFIG_MODE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_0;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_1;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_2;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_20;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_3;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_4;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_5;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_50;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_6;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.IDX_7;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.LINE_MODE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.MDSE_CD_SQL_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.MDSE_NM_SQL_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.MOD_LOGISTICS;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NMAL6760_DISP_HRCH_ACCT_CD_BILL;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NMAL6760_DISP_HRCH_ACCT_CD_SHIP;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.NMAL6760_STATUS_CD_ACTIVE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.OPENWIN_SHIP_TO;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.PERCENT;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.RMA_MODE;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SI_CD_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SI_NM_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SWH_CD_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SWH_CD_SQL_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SWH_NM_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.SWH_NM_SQL_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.WH_CD_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.WH_CD_SQL_NM;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.WH_NM_LB;
import static business.servlet.NWAL1610.constant.NWAL1610Constant.WH_NM_SQL_NM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import business.servlet.NWAL1610.NWAL1610BMsg;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1610CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/07   Fujitsu         C.Yokoi         Create          N/A
 * 2016/01/13   Fujitsu         T.Ishii         Update          S21_NA#2548
 * 2016/02/20   Fujitsu         W.Honda         Update          S21 CSA QC#1130
 * 2016/02/28   Fujitsu         Y.Kanefusa      Update          S21 CSA QC#2096
 * 2016/05/16   Fujitsu         M.Yamada        Update          S21 CSA QC#6148
 * 2016/06/01   Fujitsu         T.Murai         Update          S21 CSA QC#9189
 * 2016/08/01   Fujitsu         M.Hara          Update          S21 CSA QC#6148
 * 2016/08/30   Fujitsu         S.Takami        Update          S21_NA#9806
 * 2016/08/30   Fujitsu         R.Nakamura      Update          S21 CSA QC#11614
 * 2018/10/18   Fujitsu         K.Ishizuka      Update          S21 CSA QC#28712
 * 2018/10/23   Fujitsu         M.Ohno          Update          S21_NA#28426
 * 2019/12/23   Fujitsu         S.Kosaka        Update          QC#54999
 * 2019/12/25   Fujitsu         S.Kosaka        Update          QC#54999(Regression bug fix)
 *</pre>
 */
public class NWAL1610CommonLogic {

    /**
     * Initial Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1610BMsg
     */
    public static void initCmnBtnProp(S21CommonHandler handler, NWAL1610BMsg scrnMsg) {
        // 2019/12/23 QC#54999 Mod Start
        //handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        boolean isLogi = isLogistics(scrnMsg);
        if (!LINE_MODE.equals(scrnMsg.xxModeCd.getValue())
                && isLogi) {
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
            handler.setButtonEnabled(BTN_OK_EVENT_NM, false);
        } else {
            handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        }
        // 2019/12/23 QC#54999 Mod End
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 1, null);

        if (!ZYPCommonFunc.hasValue(scrnMsg.dsOrdLineCatgCd_CD.no(0))) {
            scrnMsg.dsOrdLineCatgCd.setInputProtected(true);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.ordLineSrcCd_CD.no(0))) {
            scrnMsg.ordLineSrcCd.setInputProtected(true);
        }
    }

    /**
     * Protect Common Button properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1610BMsg
     */
    public static void protectCmnBtn(S21CommonHandler handler, NWAL1610BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLS[0], BTN_CMN_CLS[1], BTN_CMN_CLS[2], 0, null);
    }

    /**
     * set input properties.
     * @param handler Event Handler
     * @param scrnMsg NWAL1610BMsg
     */
    public static void setinputProp(S21CommonHandler handler, NWAL1610BMsg scrnMsg) {
        scrnMsg.shipToCustLocCd.setInputProtected(true);
        scrnMsg.billToCustLocCd.setInputProtected(true);
        scrnMsg.ordQty.setInputProtected(true);
        scrnMsg.ordLineSrcCd.setInputProtected(true);
        scrnMsg.rtlWhCd.setInputProtected(true);
        scrnMsg.rtlSwhCd.setInputProtected(true);
        scrnMsg.xxScrItem20Txt.setInputProtected(true);
        scrnMsg.prcCatgNm.setInputProtected(true);
        // Mod Start #1130 02/20/2016
        // scrnMsg.flPrcListDescTxt.setInputProtected(true);
        scrnMsg.flPrcListNm.setInputProtected(true);
        // Mod End #1130 02/20/2016
        scrnMsg.prcBaseDt.setInputProtected(true);
        // Add Start #2096 02/28/2016
        scrnMsg.rddDt.setInputProtected(true);
        // Add End #2096 02/28/2016
        // Add Start #9189 06/01/2016
        scrnMsg.rqstPickUpDt.setInputProtected(true);
        // Add End #9189 06/01/2016
    }

    /**
     * Get Select Table Name For Warehouse
     * @param scrnMsg NWAL1610BMsg
     * @return Select Table Name For Warehouse
     */
    public static String getSlctTblNmForWh(NWAL1610BMsg scrnMsg) {
        String glblCmpyCd = scrnMsg.glblCmpyCd.getValue();
        String dsOrdTpCd = scrnMsg.dsOrdTpCd.getValue();

        String slsDt = null;
        if (ZYPCommonFunc.hasValue(scrnMsg.cpoOrdTs)) {
            slsDt = scrnMsg.cpoOrdTs.getValue();
        } else {
            slsDt = ZYPDateUtil.getSalesDate();
        }

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    WH.GLBL_CMPY_CD");
        sb.append("   ,WH.EZCANCELFLAG");
        sb.append("   ,WH.RTL_WH_CD");
        sb.append("   ,WH.RTL_WH_NM ");
        sb.append("   ,WH.RTL_SWH_CD");
        sb.append("   ,WH.RTL_SWH_NM ");
        sb.append("FROM");

        if (ZYPCommonFunc.hasValue(dsOrdTpCd)) {
            // sb.append("    DS_ORD_TP_LOC_ROLE_RELN              LRR");
            // sb.append("   ,LOC_USG                              LU");
            // sb.append("   ,DS_INVTY_LOC_V                       WH ");
            // sb.append("WHERE");
            // sb.append("        LRR.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            // sb.append("    AND LRR.DS_ORD_TP_CD                 = '").append(dsOrdTpCd).append("'");
            // sb.append("    AND LRR.EZCANCELFLAG                 = '0'");
            // sb.append("    AND LRR.GLBL_CMPY_CD                 = LU.GLBL_CMPY_CD");
            // sb.append("    AND LRR.LOC_ROLE_TP_CD               = LU.LOC_ROLE_TP_CD");
            // sb.append("    AND LU.EZCANCELFLAG                  = '0'");
            // sb.append("    AND LU.GLBL_CMPY_CD                  = WH.GLBL_CMPY_CD");
            // sb.append("    AND LU.PTY_LOC_PK                    = WH.PTY_LOC_PK");
            // sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            // sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            // sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            // sb.append("    AND WH.EZCANCELFLAG                  = '0'");
            // sb.append("    DS_INVTY_LOC_V WH ");
            // S21_NA#2548
            sb.append("    DS_INVTY_LOC_V WH ");
            sb.append("WHERE ");
            sb.append("        WH.GLBL_CMPY_CD                 = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.EZCANCELFLAG                 = '0'");
            sb.append("    AND WH.RGTN_STS_CD                  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                  <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("'");
            sb.append("    AND EXISTS(");
            // 2018/10/18 S21_NA#28712 Mod Start
            // sb.append("        SELECT");
            sb.append("        SELECT /*+ USE_NL(LRR LU) INDEX(LU LOC_USG_I3) */");
            // 2018/10/18 S21_NA#28712 Mod End
            sb.append("            *");
            sb.append("        FROM    ");
            sb.append("             DS_ORD_TP_LOC_ROLE_RELN    LRR   ");
            sb.append("            ,LOC_USG                    LU");
            sb.append("        WHERE");
            sb.append("                LRR.DS_ORD_TP_CD        = '").append(dsOrdTpCd).append("'");
            sb.append("            AND LRR.EZCANCELFLAG        = '0'");
            sb.append("            AND LRR.GLBL_CMPY_CD        = LU.GLBL_CMPY_CD");
            sb.append("            AND LRR.LOC_ROLE_TP_CD      = LU.LOC_ROLE_TP_CD");
            sb.append("            AND LU.EZCANCELFLAG         = '0'");
            sb.append("            AND LU.GLBL_CMPY_CD         = WH.GLBL_CMPY_CD");
            sb.append("            AND LU.PTY_LOC_PK           = WH.PTY_LOC_PK");
            sb.append("    )");
        } else {
            sb.append("    DS_INVTY_LOC_V                       WH ");
            sb.append("WHERE");
            sb.append("        WH.GLBL_CMPY_CD                  = '").append(glblCmpyCd).append("'");
            sb.append("    AND WH.RGTN_STS_CD                   = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
            sb.append("    AND WH.EFF_FROM_DT                   <= '").append(slsDt).append("'");
            sb.append("    AND NVL(WH.EFF_THRU_DT, '99991231')  >= '").append(slsDt).append("'");
            sb.append("    AND WH.EZCANCELFLAG                  = '0'");
        }

        return sb.toString();
    }

    /**
     * setWhWhereList
     * @param scrnMsg NWAL1610BMsg
     * @param index int
     * @return List<Objext[]> List
     */
    public static List<Object[]> setWhWhereList(NWAL1610BMsg scrnMsg, int index) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = WH_NM_LB;
        whereArray0[1] = WH_NM_SQL_NM;
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhNm)) {
            whereArray0[2] = scrnMsg.rtlWhNm.getValue();
        } else {
            whereArray0[2] = null;
        }
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = SWH_NM_LB;
        whereArray1[1] = SWH_NM_SQL_NM;
        if (ZYPCommonFunc.hasValue(scrnMsg.rtlSwhNm)) {
            whereArray1[2] = scrnMsg.rtlSwhNm.getValue();
        } else {
            whereArray1[2] = null;
        }
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }

    /**
     * setWhTblColumnList
     * @param scrnMsg NWAL1610BMsg
     * @return List<Objext[]> columnList
     */
    public static List<Object[]> setWhColumnList(NWAL1610BMsg scrnMsg) {
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] setColumnArray0 = new Object[4];
        setColumnArray0[0] = WH_CD_LB;
        setColumnArray0[1] = WH_CD_SQL_NM;
        setColumnArray0[2] = BigDecimal.valueOf(13);
        setColumnArray0[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray0);

        Object[] setColumnArray1 = new Object[4];
        setColumnArray1[0] = WH_NM_LB;
        setColumnArray1[1] = WH_NM_SQL_NM;
        setColumnArray1[2] = BigDecimal.valueOf(25);
        setColumnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(setColumnArray1);

        Object[] setColumnArray2 = new Object[4];
        setColumnArray2[0] = SWH_CD_LB;
        setColumnArray2[1] = SWH_CD_SQL_NM;
        setColumnArray2[2] = BigDecimal.valueOf(13);
        setColumnArray2[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray2);

        Object[] setColumnArray3 = new Object[4];
        setColumnArray3[0] = SWH_NM_LB;
        setColumnArray3[1] = SWH_NM_SQL_NM;
        setColumnArray3[2] = BigDecimal.valueOf(25);
        setColumnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(setColumnArray3);

        return columnList;
    }

    /**
     * setWhSortList
     * @param scrnMsg NWAL1610BMsg
     * @return List<Objext[]> sortCondList
     */
    public static List<Object[]> setWhSortList(NWAL1610BMsg scrnMsg) {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = WH_CD_SQL_NM;
        sortConditionArray0[1] = "ASC";
        sortCondList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[2];
        sortConditionArray1[0] = SWH_CD_SQL_NM;
        sortConditionArray1[1] = "ASC";
        sortCondList.add(sortConditionArray1);

        return sortCondList;
    }

    /**
     * Method name : setSbstItemSelectStr
     * @param scrnMsg NWAL1610BMsg
     * @return String
     */
    public static String setSbstItemSelectStr(NWAL1610BMsg scrnMsg) {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" V.EZCANCELFLAG ");
        sb.append(", V.GLBL_CMPY_CD ");
        sb.append(", V.MDSE_CD ");
        // Mod Start 2016/09/14 QC#11614
        // sb.append(", V.MDSE_NM ");
        sb.append(", V.MDSE_DESC_SHORT_TXT ");
        // Mod End 2016/09/14 QC#11614
        sb.append("FROM ");
        sb.append("ALL_MDSE_V V ");
        sb.append("WHERE ");
        sb.append("V.GLBL_CMPY_CD = '").append(scrnMsg.glblCmpyCd.getValue()).append("' ");
        sb.append("AND V.EZCANCELFLAG = '0' ");
        sb.append("AND V.RGTN_STS_CD  = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("' ");
        sb.append("AND V.MDSE_CD IN ( ");
        sb.append("SELECT ");
        sb.append("SUPD.SUPD_TO_MDSE_CD ");
        sb.append("FROM ");
        sb.append("SUPD_RELN SUPD ");
        sb.append("WHERE ");
        sb.append("SUPD.GLBL_CMPY_CD = V.GLBL_CMPY_CD ");
        sb.append("AND SUPD.EZCANCELFLAG = '0' ");
        sb.append("UNION ");
        sb.append("SELECT ");
        sb.append("FLIP.RELN_MDSE_CD ");
        sb.append("FROM ");
        sb.append("MDSE_ITEM_FLIP_SET FLIP ");
        sb.append("WHERE ");
        sb.append("FLIP.GLBL_CMPY_CD = V.GLBL_CMPY_CD ");
        sb.append("AND FLIP.EZCANCELFLAG = '0') ");

        return sb.toString();
    }

    /**
     * setSbstItemWhereList
     * @param scrnMsg NWAL1610BMsg
     * @param index int
     * @return List<Objext[]> List
     */
    public static List<Object[]> setSbstItemWhereList(NWAL1610BMsg scrnMsg, int index) {
        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[5];
        whereArray0[0] = SI_CD_LB;
        whereArray0[1] = MDSE_CD_SQL_NM;
        whereArray0[2] = scrnMsg.xxScrItem20Txt.getValue();
        whereArray0[3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28426 Add Start
        whereArray0[4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28426 Add End
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[5];
        whereArray1[0] = SI_NM_LB;
        whereArray1[1] = MDSE_NM_SQL_NM;
        whereArray1[3] = ZYPConstant.FLG_ON_Y;
        // 2018/10/23 S21_NA#28426 Add Start
        whereArray1[4] = ZYPConstant.FLG_OFF_N;
        // 2018/10/23 S21_NA#28426 Add End
        whereList.add(whereArray1);

        return whereList;
    }

    /**
     * setSbstItemTblColumnList
     * @param scrnMsg NWAL1610BMsg
     * @return List<Objext[]> columnList
     */
    public static List<Object[]> setSbstItemColumnList(NWAL1610BMsg scrnMsg) {
        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] setColumnArray0 = new Object[4];
        setColumnArray0[0] = SI_CD_LB;
        setColumnArray0[1] = MDSE_CD_SQL_NM;
        setColumnArray0[2] = BigDecimal.valueOf(20);
        setColumnArray0[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(setColumnArray0);

        Object[] setColumnArray1 = new Object[4];
        setColumnArray1[0] = SI_NM_LB;
        setColumnArray1[1] = MDSE_NM_SQL_NM;
        setColumnArray1[2] = BigDecimal.valueOf(50);
        setColumnArray1[3] = ZYPConstant.FLG_OFF_N;
        columnList.add(setColumnArray1);

        return columnList;
    }

    /**
     * openWinShipToBillTo
     * @param scrnMsg NWAL1610BMsg
     * @return Param NMAL6780 For Ship To/Bill To
     */
    public static Object[] openWinShipToBillTo(NWAL1610BMsg scrnMsg) {
        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // Request Type(ShipTo or BillTo)

        if (OPENWIN_SHIP_TO.equals(scrnMsg.xxScrEventNm.getValue())) {
            paramList.add(scrnMsg.P.no(1).xxPopPrm_P); // no used
            paramList.add(scrnMsg.shipToCustLocCd);
        } else {
            paramList.add(scrnMsg.billToCustLocCd);
            paramList.add(scrnMsg.P.no(2).xxPopPrm_P); // no used
        }

        for (int i = 3; i < 21; i++) {
            paramList.add(scrnMsg.P.no(i).xxPopPrm_P); // no used
        }

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * setSbstItemSortList
     * @param scrnMsg NWAL1610BMsg
     * @return List<Objext[]> sortCondList
     */
    public static List<Object[]> setSbstItemSortList(NWAL1610BMsg scrnMsg) {
        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortConditionArray0 = new Object[2];
        sortConditionArray0[0] = MDSE_CD_SQL_NM;
        sortConditionArray0[1] = "ASC";
        sortCondList.add(sortConditionArray0);

        return sortCondList;
    }

    /**
     * addCheckItems
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItems(NWAL1610BMsg scrnMsg) {
        if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.addCheckItem(scrnMsg.shipToCustLocCd);
            scrnMsg.addCheckItem(scrnMsg.billToCustLocCd);
        } else {
            scrnMsg.addCheckItem(scrnMsg.ordQty);
            scrnMsg.addCheckItem(scrnMsg.ordLineSrcCd);
            scrnMsg.addCheckItem(scrnMsg.rtlWhCd);
            scrnMsg.addCheckItem(scrnMsg.rtlSwhCd);
            scrnMsg.addCheckItem(scrnMsg.xxScrItem20Txt);
            scrnMsg.addCheckItem(scrnMsg.prcCatgNm);
            // Mod Start #1130 02/20/2016
            // scrnMsg.addCheckItem(scrnMsg.flPrcListDescTxt);
            scrnMsg.addCheckItem(scrnMsg.flPrcListNm);
            // Mod End #1130 02/20/2016
            scrnMsg.addCheckItem(scrnMsg.prcBaseDt);
            // Add Start #2096 02/28/2016
            scrnMsg.addCheckItem(scrnMsg.rddDt);
            // Add End #2096 02/28/2016
            // Add Start #9189 06/01/2016
            scrnMsg.addCheckItem(scrnMsg.rqstPickUpDt);
            // Add End #9189 06/01/2016
        }
    }

    /**
     * setBackUpParam
     * @param scrnMsg Screen Msg
     */
    public static void setBackUpParam(NWAL1610BMsg scrnMsg) {
        ZYPEZDItemValueSetter.setValue(scrnMsg.shipToCustLocCd_BK, scrnMsg.shipToCustLocCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.billToCustLocCd_BK, scrnMsg.billToCustLocCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordQty_BK, scrnMsg.ordQty);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdLineCatgCd_BK, scrnMsg.dsOrdLineCatgCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.ordLineSrcCd_BK, scrnMsg.ordLineSrcCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_BK, scrnMsg.rtlWhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtlSwhCd_BK, scrnMsg.rtlSwhCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnRsnCd_BK, scrnMsg.rtrnRsnCd);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrItem20Txt_BK, scrnMsg.xxScrItem20Txt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcCatgNm_BK, scrnMsg.prcCatgNm);
        // Mod Start #1130 02/20/2016
        // ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListDescTxt_BK, scrnMsg.flPrcListDescTxt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.flPrcListNm_BK, scrnMsg.flPrcListNm);
        // Mod End #1130 02/20/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcBaseDt_BK, scrnMsg.prcBaseDt);
        ZYPEZDItemValueSetter.setValue(scrnMsg.hddRmvCd_BK, scrnMsg.hddRmvCd);
        // Add Start #2096 02/28/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.rddDt_BK, scrnMsg.rddDt);
        // Add End #2096 02/28/2016
        // Add Start #9189 06/01/2016
        ZYPEZDItemValueSetter.setValue(scrnMsg.rqstPickUpDt_BK, scrnMsg.rqstPickUpDt);
        // Add End #9189 06/01/2016
        // 2019/12/25 QC#54999 Mod Start(Regression bug fix)
        ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum_BK, scrnMsg.csmpContrNum);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum_BK, scrnMsg.dlrRefNum);
        // 2019/12/25 QC#54999 Mod End(Regression bug fix)
    }

    /**
     * getOpenWinShipToParam(NWAL1610)
     * @param scrnMsg NWAL1610BMsg
     * @return Object[]
     */
    public static Object[] getOpenWinShipToParam(NWAL1610BMsg scrnMsg) {
        int ixP = 0;
        scrnMsg.P.no(ixP++).xxPopPrm_P.clear(); // 0
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, NMAL6760_STATUS_CD_ACTIVE); // 1
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, NMAL6760_DISP_HRCH_ACCT_CD_SHIP); // 2
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_OFF_N); // 3
        // 2016/08/01 QC#6148 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.shipToCustAcctCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_OFF_N); // 4
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_ON_Y); // 4
        }
        // 2016/08/01 QC#6148 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_ON_Y); // 5

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ixP = 1;

        // 2016/08/01 QC#6148 Mod
        // paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.shipToCustAcctCd); // Ship To Account
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used

        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P);
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 1.Active Only
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 2.Ship To's Only
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.shipToCustLocCd); // Ship To Location
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 3.Display Hierarchy Active Flag
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 4.Account Number Active Flag
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 5.Status Active Flag

        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used

        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used

        return paramList.toArray(new EZDBItem[0]);
    }

    /**
     * getOpenWinBillToParam(NWAL1610)
     * @param scrnMsg NWAL1610BMsg
     * @return Object[]
     */
    public static Object[] getOpenWinBillToParam(NWAL1610BMsg scrnMsg) {
        int ixP = 0;

        scrnMsg.P.no(ixP++).xxPopPrm_P.clear(); // 0
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, NMAL6760_STATUS_CD_ACTIVE); // 1
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, NMAL6760_DISP_HRCH_ACCT_CD_BILL); // 2
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_OFF_N); // 3
        // 2016/08/01 QC#6148 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.billToCustAcctCd)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_OFF_N); // 4
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_ON_Y); // 4
        }
        // 2016/08/01 QC#6148 Add End
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(ixP++).xxPopPrm_P, ZYPConstant.FLG_ON_Y); // 5

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        ixP = 1;

        // 2016/08/01 QC#6148 Mod
        // paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.billToCustAcctCd); // Bill To Account

        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used

        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 1.Active Only
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 2.Bill To's Only
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.billToCustLocCd); // Bill To Location
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(0).xxPopPrm_P); // no used
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 3.Display Hierarchy Active Flag
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 4.Account Number Active Flag
        paramList.add(scrnMsg.P.no(ixP++).xxPopPrm_P); // 5.Status Active Flag

        return paramList.toArray(new EZDBItem[0]);
    }

    // 2016/08/30 S21_NA#9806 Add Start
    /**
     * Get Param NWAL1130 For CSMP Number
     * @param scrnMsg NWAL1500BMsg
     * @return Param NWAL1130 For CSMP Number
     */
    public static Object[] getParamNWAL1130ForCSMPNumber(NWAL1610BMsg scrnMsg) {

        Object[] params = new Object[IDX_7];
        params[IDX_0] = "S";
        params[IDX_1] = "CSMP# Search";

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append("    CC.EZCANCELFLAG");
        sb.append("   ,CC.GLBL_CMPY_CD");
        sb.append("   ,CC.CSMP_NUM");
        sb.append("   ,CC.DLR_REF_NUM");
        sb.append("   ,CC.CSMP_NUM_CMNT_TXT ");
        sb.append("FROM ");
        sb.append("    CSMP_CONTR CC ");
        sb.append("WHERE ");
        sb.append("    CC.GLBL_CMPY_CD            = '").append(scrnMsg.glblCmpyCd.getValue()).append("'");
        sb.append("    AND CC.EZCANCELFLAG        = '0'");
        sb.append("    AND CC.DS_ACCT_NUM         = '").append(scrnMsg.sellToCustCd.getValue()).append("'");
        sb.append("    AND CC.EFF_FROM_DT         <= '").append(scrnMsg.slsDt.getValue()).append("'");
        sb.append("    AND (CC.EFF_THRU_DT        >= '").append(scrnMsg.slsDt.getValue()).append("' OR CC.EFF_THRU_DT IS NULL)");
        sb.append("    AND CC.CSMP_CONTR_ACTV_FLG = '").append(ZYPConstant.FLG_ON_Y).append("'");
        sb.append("    AND (CC.DLR_REF_NUM IS NOT NULL OR CC.CSMP_NUM IS NOT NULL)");
        params[IDX_2] = sb.toString();

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[IDX_4];
        whereArray0[IDX_0] = "CSMP Number";
        whereArray0[IDX_1] = "CSMP_NUM";

        if (ZYPCommonFunc.hasValue(scrnMsg.csmpContrNum) //
                || ZYPCommonFunc.hasValue(scrnMsg.dlrRefNum)) {
            whereArray0[IDX_2] = scrnMsg.csmpContrNum.getValue();
        } else {
            whereArray0[IDX_2] = PERCENT;
        }

        whereArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "CSA Number";
        whereArray1[IDX_1] = "DLR_REF_NUM";
        whereArray1[IDX_2] = scrnMsg.dlrRefNum.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();
        Object[] columnArray0 = new Object[IDX_4];
        columnArray0[IDX_0] = "CSMP Number";
        columnArray0[IDX_1] = "CSMP_NUM";
        columnArray0[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray0[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[IDX_0] = "CSA Number";
        columnArray1[IDX_1] = "DLR_REF_NUM";
        columnArray1[IDX_2] = BigDecimal.valueOf(IDX_20);
        columnArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[IDX_0] = "CSMP Number Comment";
        columnArray2[IDX_1] = "CSMP_NUM_CMNT_TXT";
        columnArray2[IDX_2] = BigDecimal.valueOf(IDX_50);
        columnArray2[IDX_3] = ZYPConstant.FLG_OFF_N;
        columnList.add(columnArray2);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray0 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "CSMP_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray0);

        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray0[IDX_0] = "DLR_REF_NUM";
        sortConditionArray0[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        params[IDX_5] = sortConditionList;

        ZYPTableUtil.clear(scrnMsg.S);
        params[IDX_6] = scrnMsg.S;

        return params;
    }
    // 2016/08/30 S21_NA#9806 Add End
    // 2019/12/23 QC#54999 Add Start
    /**
     * isLogistics
     * @param scrnMsg NWAL1610BMsg
     * @return isLogistics
     */
    public static boolean isLogistics(NWAL1610BMsg scrnMsg) {
        if (scrnMsg.Z.getValidCount() == 1 && MOD_LOGISTICS.equals(scrnMsg.Z.no(0).xxFuncId.getValue())) {
            return true;
        }
        return false;
    }

    /**
     * Set Protects
     * @param scrnMsg NWAL1610BMsg
     * @param isProtected boolean
     */
    public static void setProtects(NWAL1610BMsg scrnMsg) {
        boolean isLogi = isLogistics(scrnMsg);
        if (CONFIG_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.shipToCustLocCd.setInputProtected(isLogi);
            scrnMsg.billToCustLocCd.setInputProtected(isLogi);
            scrnMsg.csmpContrNum.setInputProtected(isLogi);
            scrnMsg.dlrRefNum.setInputProtected(isLogi);
        } else if (RMA_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            scrnMsg.ordQty.setInputProtected(isLogi);
            scrnMsg.dsOrdLineCatgCd.setInputProtected(isLogi);
            scrnMsg.prcCatgNm.setInputProtected(isLogi);
            scrnMsg.flPrcListNm.setInputProtected(isLogi);
            scrnMsg.prcBaseDt.setInputProtected(isLogi);
            scrnMsg.rqstPickUpDt.setInputProtected(isLogi);
            scrnMsg.rtrnRsnCd.setInputProtected(isLogi);
            scrnMsg.hddRmvCd.setInputProtected(isLogi);
            scrnMsg.rtrnRsnCd.setInputProtected(isLogi);
            scrnMsg.rtlWhNm.setInputProtected(isLogi);
            scrnMsg.rtlSwhNm.setInputProtected(isLogi);
        } else {
            scrnMsg.ordQty.setInputProtected(isLogi);
            scrnMsg.dsOrdLineCatgCd.setInputProtected(isLogi);
            scrnMsg.prcCatgNm.setInputProtected(isLogi);
            scrnMsg.flPrcListNm.setInputProtected(isLogi);
            scrnMsg.prcBaseDt.setInputProtected(isLogi);
            scrnMsg.xxScrItem20Txt.setInputProtected(isLogi);
            scrnMsg.rddDt.setInputProtected(isLogi);
        }
    }
    // 2019/12/23 QC#54999 Add End
}

/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL1330;

import static business.servlet.NSAL1330.constant.NSAL1330Constant.NEWLINE;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.NWAL1130_PRM_NUM;
import static business.servlet.NSAL1330.constant.NSAL1330Constant.POP_UP_BOOK_ITEM_CONFIG;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NSAL1330Scrn00_OpenWin_Item_Search_PriceBookItemConfig
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/14   Hitachi         Y.Takeno        Create          N/A
 *</pre>
 */
public class NSAL1330Scrn00_OpenWin_Item_Search_PriceBookItemConfig extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL1330BMsg scrnMsg = (NSAL1330BMsg) bMsg;
        scrnMsg.delFlg_W.clear();

        scrnMsg.P.clear();

        int index = getButtonSelectNumber();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxNum_A, new BigDecimal(index));

        // make parameterList
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Covered Item", "MDSE_CD", "%", ZYPConstant.FLG_ON_Y };
        Object[] whereObj2 = {"Item Description", "MDSE_NM", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);

        List<Object> colList = new ArrayList<Object>();
        Object[] colObj3 = {"Item Code", "MDSE_CD", new BigDecimal("20"), ZYPConstant.FLG_ON_Y };
        Object[] colObj4 = {"Item Description", "MDSE_NM", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        colList.add(colObj3);
        colList.add(colObj4);

        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"MDSE_CD", "ASC" };
        sortList.add(sortObj1);

        // set the transition screen name
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_P1, POP_UP_BOOK_ITEM_CONFIG);

        Object[] params = new Object[NWAL1130_PRM_NUM];

        StringBuilder sbSelect = new StringBuilder();
        setQuery(sbSelect);

        int ixP = 0;
        // 0 : Lv1 : Suffix
        params[ixP++] = "P";
        // 1 : Lv1 : Window Title
        params[ixP++] = POP_UP_BOOK_ITEM_CONFIG;
        // 2 : Lv1 : Select Table Name
        params[ixP++] = sbSelect.toString();
        // 3 : Lv1 : Where List
        params[ixP++] = whereList;
        // 4 : Lv1 : Column List
        params[ixP++] = colList;
        // 5 : Lv1 : Sort Condition List
        params[ixP++] = sortList;
        // 6 : Output
        params[ixP] = scrnMsg.P;

        setArgForSubScreen(params);
    }

    /**
     * @param mdseTpCtxTp
     * @param sb
     */
    private void setQuery(StringBuilder sb) {

        sb.append("SELECT");
        sb.append(NEWLINE).append("     M.EZCANCELFLAG                                          ");
        sb.append(NEWLINE).append("    ,M.GLBL_CMPY_CD                                          ");
        sb.append(NEWLINE).append("    ,NVL(OTM.ORD_TAKE_MDSE_CD, M.MDSE_CD) AS MDSE_CD         ");
        sb.append(NEWLINE).append("    ,RTRIM(M.MDSE_NM) AS MDSE_NM                               ");
        sb.append(NEWLINE).append("FROM                                                           ");
        sb.append(NEWLINE).append("     ITEM_TP_VAL_SET ITVS                                      ");
        sb.append(NEWLINE).append("    ,MDSE            M                                         ");
        sb.append(NEWLINE).append("    ,ORD_TAKE_MDSE   OTM                                       ");
        sb.append(NEWLINE).append("WHERE                                                          ");
        sb.append(NEWLINE).append("        ITVS.EZCANCELFLAG       = '0'                          ");
        sb.append(NEWLINE).append("    AND ITVS.ITEM_TP_CTX_TP_CD  = 'PRC_BOOK_MDSE_QLFY_ITEM_TP' ");
        sb.append(NEWLINE).append("    AND ITVS.EZCANCELFLAG       = M.EZCANCELFLAG             ");
        sb.append(NEWLINE).append("    AND ITVS.GLBL_CMPY_CD       = M.GLBL_CMPY_CD             ");
        sb.append(NEWLINE).append("    AND ITVS.MDSE_ITEM_TP_CD    = M.MDSE_ITEM_TP_CD          ");
        sb.append(NEWLINE).append("    AND ITVS.FIRST_BIZ_CTX_ATTRB_TXT    = M.DS_INTG_MDSE_TP_CD       ");
        sb.append(NEWLINE).append("    AND M.RGTN_STS_CD           = '").append(RGTN_STS.READY_FOR_ORDER_TAKING).append("'");
        sb.append(NEWLINE).append("    AND M.EZCANCELFLAG          = OTM.EZCANCELFLAG(+)          ");
        sb.append(NEWLINE).append("    AND M.GLBL_CMPY_CD          = OTM.GLBL_CMPY_CD(+)          ");
        sb.append(NEWLINE).append("    AND M.MDSE_CD               = OTM.MDSE_CD(+)               ");
    }
}

/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWCL0100;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRX_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NWCL0100Scrn00_OpenWin_CustPrcGrp extends S21CommonHandler {

    /** parameter count of Common Popup */
    private static final int PRM_CNT = 7;

    /** line separator for SQL */
    private static final String NEWLINE = System.getProperty("line.separator");

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        //NWCL0100CMsg bizMsg = new NWCL0100CMsg();
        //bizMsg.setBusinessID("NWCL0100");
        //bizMsg.setFunctionCode("20");
        //EZDMsg.copy(scrnMsg, null, bizMsg, null);

        //return bizMsg;

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWCL0100BMsg scrnMsg = (NWCL0100BMsg) bMsg;

        scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
        scrnMsg.Q.clear(); // parameter area for Common BIG Popup

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Search Statement (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        
        NWCL0100_ABMsg abMsg = scrnMsg.A.no(getButtonSelectNumber());
        
        // 6 : Lv2 : Output
        int i = 0;
        Object[] params = new Object[PRM_CNT];
        //TODO [Template] set the values
        params[i++] = "";
        params[i++] = "Customer Group Search";
        params[i++] = getSelectSQL(abMsg);
        params[i++] = getSearchConditionSetting(abMsg);
        params[i++] = getDisplayColumnSetting(abMsg);
        params[i++] = getSortSetting(abMsg);
        params[i] = scrnMsg.Q;

        setArgForSubScreen(params);
    }
    

    private List<Object> getSearchConditionSetting(NWCL0100_ABMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();

        //TODO [Template] set the search condition parameters.
        //TODO [Template] {"display name", "column name(physical)", value, use LIKE flag}
        Object[] whereObj1;
        
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_A0)){
            whereObj1 = new Object[]{"Customer Group Code", "PRC_GRP_PK", scrnMsg.prcGrpPk_A0.getValue().toPlainString(), ZYPConstant.FLG_OFF_N };
        } else {
            whereObj1 = new Object[]{"Customer Group Code", "PRC_GRP_PK", "", ZYPConstant.FLG_OFF_N };
        }

        Object[] whereObj2 = {"Customer Group Name", "PRC_GRP_NM", null, ZYPConstant.FLG_ON_Y };

        whereList.add(whereObj1);
        whereList.add(whereObj2);

        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NWCL0100_ABMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();

        Object[] colObj1 = {"Customer Group Code", "PRC_GRP_PK", new BigDecimal("28"), ZYPConstant.FLG_ON_Y };
        Object[] colObj2 = {"Customer Group Name", "PRC_GRP_NM", new BigDecimal("50"), ZYPConstant.FLG_OFF_N };

        colList.add(colObj1);
        colList.add(colObj2);

        return colList;
    }

    private List<Object> getSortSetting(NWCL0100_ABMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();

        //TODO [Template] set the sort parameters.
        //TODO [Template] {"column name(physical)", sort order(ASC/DESC)}
        Object[] sortObj1 = {"PRC_GRP_PK", "ASC" };

        sortList.add(sortObj1);

        return sortList;
    }

    private String getSelectSQL(NWCL0100_ABMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();

        sb.append("SELECT");
        sb.append(NEWLINE).append("    PG.GLBL_CMPY_CD ");
        sb.append(NEWLINE).append("  , PG.EZCANCELFLAG ");
        sb.append(NEWLINE).append("  , PG.PRC_GRP_PK");
        sb.append(NEWLINE).append("  , PG.PRC_GRP_NM ");
        
        sb.append(NEWLINE).append("FROM");
        sb.append(NEWLINE).append("  PRC_GRP PG ");
        sb.append(NEWLINE).append("WHERE");
        //sb.append(NEWLINE).append("1=1 ");
        sb.append(NEWLINE).append(String.format("  PG.GLBL_CMPY_CD = '%s' ", getGlobalCompanyCode()));
/*
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_A0)){
            sb.append(NEWLINE).append(String.format("  AND PG.PRC_GRP_PK = %s ", scrnMsg.prcGrpPk_A0.getValue()));
        }
*/
        sb.append(NEWLINE).append(String.format("  AND PG.PRC_GRP_TP_CD = '%s' ", PRC_GRP_TP.CUSTOMER_PRICING_GROUP));
        sb.append(NEWLINE).append(String.format("  AND PG.ACTV_FLG = '%s' ", ZYPConstant.FLG_ON_Y));
        sb.append(NEWLINE).append(String.format("  AND PG.PRC_GRP_TRX_TP_CD='%s'", PRC_GRP_TRX_TP.SUPPLIES));
        sb.append(NEWLINE).append("  AND PG.EZCANCELFLAG = '0'");
        //sb.append(NEWLINE).append("ORDER BY PG.PRC_GRP_PK");

        return sb.toString();

    }
    
}

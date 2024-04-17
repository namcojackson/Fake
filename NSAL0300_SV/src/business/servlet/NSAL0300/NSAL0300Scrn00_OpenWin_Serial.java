/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.constant.NSAL0300Constant.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0300.NSAL0300CMsg;
import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_MSTR_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_MACH_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            SRAA            Create          N/A
 * 2015/12/03   Hitachi         A.Kohinata      Update          QC501
 * 2016/02/17   Hitachi         T.Kanasaka      Update          QC2879
 * 2016/09/27   Hitachi         Y.Zhang         Update          QC#12582
 * 2018/10/15   Hitachi         T.Tomita        Update          QC#28713
 * 2018/10/15   Hitachi         K.Kitachi       Update          QC#28713
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Serial extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2016/02/17 T.Kanasaka [QC2879, ADD]
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsContrStsCd)) {
            scrnMsg.setMessageInfo(NSAM0426E);
            throw new EZDFieldErrorException();
        }
        // END 2016/02/17 T.Kanasaka [QC2879, ADD]
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // START 2018/10/15 K.Kitachi [QC#28713, MOD]
//        return null;
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        if (!ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)) {
            return null;
        }

        NSAL0300CMsg bizMsg = new NSAL0300CMsg();
        bizMsg.setBusinessID("NSAL0300");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
        // END 2018/10/15 K.Kitachi [QC#28713, MOD]
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        // START 2018/10/15 K.Kitachi [QC#28713, ADD]
        if (cMsg != null) {
            NSAL0300CMsg bizMsg = (NSAL0300CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            scrnMsg.addCheckItem(scrnMsg.dsAcctNum);
            scrnMsg.putErrorScreen();
        }
        // END 2018/10/15 K.Kitachi [QC#28713, ADD]
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_Serial");
        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        Object[] prm = new Object[7];
        prm[0] = "";
        prm[1] = "Search Machine Master Serial Number";
        prm[2] = getSelectSQL(scrnMsg);
        prm[3] = getSearchConditionSetting(scrnMsg);
        prm[4] = getDisplayColumnSetting(scrnMsg);
        prm[5] = getSortSetting(scrnMsg);
        prm[6] = scrnMsg.R;
        setArgForSubScreen(prm);
    }

    private List<Object> getSearchConditionSetting(NSAL0300BMsg scrnMsg) {
        List<Object> whereList = new ArrayList<Object>();
        Object[] whereObj1 = {"Machine Serial#", "MACH_SER_NUM", scrnMsg.serNum.getValue(), ZYPConstant.FLG_ON_Y };
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] whereObj2 = {"Machine Item Code", "MACH_MDSE_CD", null, ZYPConstant.FLG_OFF_N };
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] whereObj3 = {"Machine Model Name", "MACH_MDL_NM", null, ZYPConstant.FLG_ON_Y };
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] whereObj4 = {"Accessory Item Code", "ACC_MDSE_CD", null, ZYPConstant.FLG_OFF_N };
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] whereObj5 = {"Accessory Model Name", "ACC_MDL_NM", null, ZYPConstant.FLG_ON_Y };
        whereList.add(whereObj1);
        whereList.add(whereObj2);
        whereList.add(whereObj3);
        whereList.add(whereObj4);
        whereList.add(whereObj5);
        return whereList;
    }

    private List<Object> getDisplayColumnSetting(NSAL0300BMsg scrnMsg) {
        List<Object> colList = new ArrayList<Object>();
        Object[] colObj1 = {"Machine Serial#", "MACH_SER_NUM", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] colObj2 = {"Machine Item Code", "MACH_MDSE_CD", new BigDecimal("16"), ZYPConstant.FLG_OFF_N };
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] colObj3 = {"Machine Model Name", "MACH_MDL_NM", new BigDecimal("50"), ZYPConstant.FLG_OFF_N };
        Object[] colObj4 = {"Accessory Serial#", "ACC_SER_NUM", new BigDecimal("30"), ZYPConstant.FLG_ON_Y };
        // START 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] colObj5 = {"Accessory Item Code", "ACC_MDSE_CD", new BigDecimal("16"), ZYPConstant.FLG_OFF_N };
        // END 2016/09/27 Y.Zhang [QC#12582, MOD]
        Object[] colObj6 = {"Accessory Model Name", "ACC_MDL_NM", new BigDecimal("50"), ZYPConstant.FLG_OFF_N };
        colList.add(colObj1);
        colList.add(colObj2);
        colList.add(colObj3);
        colList.add(colObj4);
        colList.add(colObj5);
        colList.add(colObj6);
        return colList;
    }

    private List<Object> getSortSetting(NSAL0300BMsg scrnMsg) {
        List<Object> sortList = new ArrayList<Object>();
        Object[] sortObj1 = {"MACH_SER_NUM", "ASC" };
        Object[] sortObj2 = {"MACH_MDSE_CD", "ASC" };
        Object[] sortObj3 = {"ACC_SER_NUM", "ASC" };
        Object[] sortObj4 = {"ACC_MDSE_CD", "ASC" };
        sortList.add(sortObj1);
        sortList.add(sortObj2);
        sortList.add(sortObj3);
        sortList.add(sortObj4);
        return sortList;
    }

    private String getSelectSQL(NSAL0300BMsg scrnMsg) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT DISTINCT                                                    ");
        sb.append("       SMM_M.SER_NUM           MACH_SER_NUM                        ");
        sb.append("     , SMM_M.MDSE_CD           MACH_MDSE_CD                        ");
        sb.append("     , MN.T_MDL_NM             MACH_MDL_NM                         ");
        sb.append("     , SMM_A.SER_NUM           ACC_SER_NUM                         ");
        sb.append("     , SMM_A.MDSE_CD           ACC_MDSE_CD                         ");
        sb.append("     , MN.T_MDL_NM             ACC_MDL_NM                          ");
        sb.append("     , SMM_M.GLBL_CMPY_CD                                          ");
        sb.append("     , SMM_M.EZCANCELFLAG                                          ");
        sb.append("  FROM SVC_MACH_MSTR           SMM_M                               ");
        sb.append("     , SVC_CONFIG_MSTR         SCM                                 ");
        sb.append("     , MDL_NM                  MN                                  ");
        sb.append("     , SVC_MACH_MSTR           SMM_A                               ");
        sb.append(" WHERE SMM_M.GLBL_CMPY_CD             = '#GLBL_CMPY_CD#'           ");
        sb.append("   AND SMM_M.EZCANCELFLAG             = '0'                        ");
        // Add Start 2018/10/15 QC#28713
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)) {
            sb.append("   AND SMM_M.BILL_TO_ACCT_NUM         = '#MM_BILL_TO_ACCT#'");
        }
        // Add End 2018/10/15 QC#28713
        sb.append("   AND SMM_M.SVC_MACH_TP_CD           = '#MM_TP_MACH#'             ");
        sb.append("   AND SMM_M.SVC_MACH_MSTR_STS_CD     IN ('#MM_W4I#', '#MM_INSTL#') ");
        sb.append("   AND SMM_M.SVC_CONFIG_MSTR_PK       = SCM.SVC_CONFIG_MSTR_PK     ");
        sb.append("   AND SMM_M.GLBL_CMPY_CD             = SCM.GLBL_CMPY_CD           ");
        sb.append("   AND SCM.EZCANCELFLAG               = '0'                        ");
        sb.append("   AND SCM.MDL_ID                     = MN.T_MDL_ID(+)             ");
        sb.append("   AND SCM.GLBL_CMPY_CD               = MN.T_GLBL_CMPY_CD(+)       ");
        sb.append("   AND MN.EZCANCELFLAG(+)             = '0'                        ");
        sb.append("   AND SCM.SVC_CONFIG_MSTR_PK         = SMM_A.SVC_CONFIG_MSTR_PK(+)");
        sb.append("   AND SCM.GLBL_CMPY_CD               = SMM_A.GLBL_CMPY_CD(+)      ");
        sb.append("   AND SMM_A.SVC_MACH_TP_CD(+)        = '#MM_TP_ACC#'              ");
        sb.append("   AND SMM_A.SVC_MACH_MSTR_STS_CD(+)  IN ('#MM_W4I#', '#MM_INSTL#') ");
        sb.append("   AND SMM_A.EZCANCELFLAG(+)          = '0'                        ");

        String sql = sb.toString();
        sql = sql.replaceAll("#GLBL_CMPY_CD#", getGlobalCompanyCode());
        // Add Start 2018/10/15 QC#28713
        if (ZYPCommonFunc.hasValue(scrnMsg.dsAcctNum)) {
            sql = sql.replaceAll("#MM_BILL_TO_ACCT#", scrnMsg.dsAcctNum.getValue());
        }
        // Add End 2018/10/15 QC#28713
        sql = sql.replaceAll("#MM_W4I#", SVC_MACH_MSTR_STS.WAITING_FOR_INSTALLATION);
        sql = sql.replaceAll("#MM_INSTL#", SVC_MACH_MSTR_STS.INSTALLED);
        sql = sql.replaceAll("#MM_TP_MACH#", SVC_MACH_TP.MACHINE);
        sql = sql.replaceAll("#MM_TP_ACC#", SVC_MACH_TP.ACCESSORY);

        sql = sql.replaceAll(" +", " ");

        return sql;
    }
}

/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0300;

import static business.servlet.NSAL0300.common.NSAL0300CommonLogic.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NSAL0300.common.NSAL0300CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         T.Tomita        Create          QC#2182
 * 2016/04/07   Hitachi         M.Gotou         Update          QC5312,5313
 * 2017/01/24   Hitachi         N.Arai          Update          QC#17228
 *</pre>
 */
public class NSAL0300Scrn00_OpenWin_Rep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0300BMsg scrnMsg = (NSAL0300BMsg) bMsg;
        NSAL0300CommonLogic.clearPopupParam(scrnMsg);
        scrnMsg.xxScrEventNm.setValue("OpenWin_Rep");
        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        Object[] prm = new Object[7];
        prm[0] = "";
        prm[1] = "Rep  Search Popup";
        // mod start 2016/04/07 CSA Defect#5312,5313
        //setValue(scrnMsg.svcContrBrCd_WK, scrnMsg.svcContrBrCd);
        scrnMsg.xxPsnNm_PP.clear();
        setValue(scrnMsg.xxPsnNm_PP, scrnMsg.xxPsnNm);
     // START 2017/01/24 N.Arai [QC#17228, MOD]
        // prm[2] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        prm[2] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()), PSN_TP.EMPLOYEE);
     // END 2017/01/24 N.Arai [QC#17228, MOD]
        // mod end 2016/04/07 CSA Defect#5312,5313
        prm[3] = getSearchConditionSetting(scrnMsg);
        prm[4] = getDisplayColumnSetting(scrnMsg);
        prm[5] = getSortSetting(scrnMsg);
        prm[6] = scrnMsg.R;
        setArgForSubScreen(prm);
    }

//    private List<Object> getSearchConditionSetting(NSAL0300BMsg scrnMsg) {
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.contrAdminPsnCd.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.xxPsnNm.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.svcLineBizCd.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.svcContrBrCd.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.svcContrBrDescTxt.getValue(), ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//        whereList.add(whereObj3);
//        whereList.add(whereObj4);
//        whereList.add(whereObj5);
//        return whereList;
//    }
//
//    private List<Object> getDisplayColumnSetting(NSAL0300BMsg scrnMsg) {
//        List<Object> colList = new ArrayList<Object>();
//        Object[] colObj1 = {"Person Code", "PSN_CD", new BigDecimal("9"), ZYPConstant.FLG_ON_Y };
//        Object[] colObj2 = {"Person Name", "PSN_NM", new BigDecimal("42"), ZYPConstant.FLG_OFF_N };
//        Object[] colObj3 = {"Branch Code", "SVC_CONTR_BR_CD", new BigDecimal("10"), ZYPConstant.FLG_OFF_N };
//        Object[] colObj4 = {"Branch Name", "SVC_CONTR_BR_DESC_TXT", new BigDecimal("30"), ZYPConstant.FLG_OFF_N };
//        colList.add(colObj1);
//        colList.add(colObj2);
//        colList.add(colObj3);
//        colList.add(colObj4);
//        return colList;
//    }
//
//    private List<Object> getSortSetting(NSAL0300BMsg scrnMsg) {
//        List<Object> sortList = new ArrayList<Object>();
//        Object[] sortObj1 = {"PSN_CD", "ASC" };
//        Object[] sortObj2 = {"SVC_LINE_BIZ_CD", "ASC" };
//        Object[] sortObj3 = {"SVC_CONTR_BR_CD", "ASC" };
//        sortList.add(sortObj1);
//        sortList.add(sortObj2);
//        sortList.add(sortObj3);
//        return sortList;
//    }
//
//    private String getSelectSQL(NSAL0300BMsg scrnMsg) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("SELECT");
//        sb.append("      A.GLBL_CMPY_CD                         AS GLBL_CMPY_CD");
//        sb.append("     ,A.EZCANCELFLAG                         AS EZCANCELFLAG");
//        sb.append("     ,A.PSN_CD                               AS PSN_CD");
//        sb.append("     ,A.PSN_FIRST_NM || ' ' || A.PSN_LAST_NM AS PSN_NM");
//        sb.append("     ,E.SVC_LINE_BIZ_CD                      AS SVC_LINE_BIZ_CD");
//        sb.append("     ,C.SVC_CONTR_BR_CD                      AS SVC_CONTR_BR_CD");
//        sb.append("     ,C.SVC_CONTR_BR_DESC_TXT                AS SVC_CONTR_BR_DESC_TXT");
//        sb.append(" FROM");
//        sb.append("      S21_PSN           A");
//        sb.append("     ,(");
//        sb.append("         SELECT");
//        sb.append("              B1.GLBL_CMPY_CD");
//        sb.append("             ,B1.PSN_CD");
//        sb.append("             ,B1.SVC_CONTR_BR_CD");
//        sb.append("             ,B1.EZCANCELFLAG");
//        sb.append("             ,B1.EFF_FROM_DT");
//        sb.append("             ,NVL(B1.EFF_THRU_DT, #maxDt#)  AS EFF_THRU_DT");
//        sb.append("         FROM");
//        sb.append("             SVC_BR_RESRC_RELN B1");
//        sb.append("         WHERE");
//        sb.append("             B1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
//        sb.append("         AND B1.EZCANCELFLAG          = '0'");
//        sb.append("         AND B1.SVC_BR_RESRC_ACTV_FLG = 'Y'");
//        sb.append("      ) B");
//        sb.append("     ,(");
//        sb.append("         SELECT");
//        sb.append("              C1.GLBL_CMPY_CD");
//        sb.append("             ,C1.SVC_CONTR_BR_CD");
//        sb.append("             ,C1.SVC_CONTR_BR_DESC_TXT");
//        sb.append("             ,C1.EZCANCELFLAG");
//        sb.append("             ,C1.EFF_FROM_DT");
//        sb.append("             ,NVL(C1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
//        sb.append("         FROM");
//        sb.append("             SVC_CONTR_BR C1");
//        sb.append("         WHERE");
//        sb.append("             C1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
//        sb.append("         AND C1.EZCANCELFLAG          = '0'");
//        sb.append("         AND C1.SVC_CONTR_BR_ACTV_FLG = 'Y'");
//        sb.append("      ) C");
//        sb.append("     ,SVC_RG_BR_RELN D");
//        sb.append("     ,(");
//        sb.append("         SELECT");
//        sb.append("              E1.GLBL_CMPY_CD");
//        sb.append("             ,E1.SVC_RG_PK");
//        sb.append("             ,E1.SVC_LINE_BIZ_CD");
//        sb.append("             ,E1.EZCANCELFLAG");
//        sb.append("             ,E1.EFF_FROM_DT");
//        sb.append("             ,NVL(E1.EFF_THRU_DT, '#maxDt#')  AS EFF_THRU_DT");
//        sb.append("         FROM");
//        sb.append("             SVC_RG E1");
//        sb.append("         WHERE");
//        sb.append("             E1.GLBL_CMPY_CD          = '#glblCmpyCd#'");
//        sb.append("         AND E1.EZCANCELFLAG          = '0'");
//        sb.append("         AND E1.SVC_RG_ACTV_FLG       = 'Y'");
//        sb.append("      ) E");
//        sb.append(" WHERE");
//        sb.append("     A.GLBL_CMPY_CD          = '#glblCmpyCd#'");
//        sb.append(" AND A.EZCANCELFLAG          = '0'");
//        sb.append(" AND A.GLBL_CMPY_CD          = B.GLBL_CMPY_CD     (+)");
//        sb.append(" AND A.PSN_CD                = B.PSN_CD           (+)");
//        sb.append(" AND B.EZCANCELFLAG      (+) = '0'");
//        sb.append(" AND B.EFF_FROM_DT       (+) <= '#slsDt#'");
//        sb.append(" AND B.EFF_THRU_DT       (+) >= '#slsDt#'");
//        sb.append(" AND B.GLBL_CMPY_CD          = C.GLBL_CMPY_CD     (+)");
//        sb.append(" AND B.SVC_CONTR_BR_CD       = C.SVC_CONTR_BR_CD  (+)");
//        sb.append(" AND C.EZCANCELFLAG      (+) = '0'");
//        sb.append(" AND C.EFF_FROM_DT       (+) <= '#slsDt#'");
//        sb.append(" AND C.EFF_THRU_DT       (+) >= '#slsDt#'");
//        sb.append(" AND C.GLBL_CMPY_CD          = D.GLBL_CMPY_CD     (+)");
//        sb.append(" AND C.SVC_CONTR_BR_CD       = D.SVC_CONTR_BR_CD  (+)");
//        sb.append(" AND D.EZCANCELFLAG      (+) = '0'");
//        sb.append(" AND D.GLBL_CMPY_CD          = E.GLBL_CMPY_CD     (+)");
//        sb.append(" AND D.SVC_RG_PK             = E.SVC_RG_PK        (+)");
//        sb.append(" AND E.EZCANCELFLAG      (+) = '0'");
//        sb.append(" AND E.EFF_FROM_DT       (+) <= '#slsDt#'");
//        sb.append(" AND E.EFF_THRU_DT       (+) >= '#slsDt#'");
//
//        String sql = sb.toString();
//        sql = sql.replaceAll("#glblCmpyCd#", getGlobalCompanyCode());
//        sql = sql.replaceAll("#maxDt#", DEF_EFF_THRU_DT);
//        sql = sql.replaceAll("#slsDt#", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
//        return sql;
//    }
}

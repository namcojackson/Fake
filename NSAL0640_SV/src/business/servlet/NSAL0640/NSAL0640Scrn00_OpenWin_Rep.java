/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0640;

import static business.servlet.NSAL0640.common.NSAL0640CommonLogic.*;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.NWAL1130_PRM_LENGTH;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_DTL;
import static business.servlet.NSAL0640.constant.NSAL0640Constant.SELECT_BRANCH_HEAD;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/28   Hitachi         M.Gotou         Create          QC4915
 * 2016/03/31   Hitachi         M.Gotou         Update          QC4916
 *</pre>
 */
public class NSAL0640Scrn00_OpenWin_Rep extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NSAL0640BMsg scrnMsg = (NSAL0640BMsg) bMsg;

        // mod start 2016/03/31 CSA Defect#4916
        int index = getButtonSelectNumber();

        if (index >= 0) {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_DTL);
            setValue(scrnMsg.psnCd_P, scrnMsg.A.no(index).psnCd_A2);
            setValue(scrnMsg.xxPsnNm_P, scrnMsg.A.no(index).xxPsnNm_A2);
        } else {
            scrnMsg.xxScrEventNm.setValue(SELECT_BRANCH_HEAD);
            setValue(scrnMsg.psnCd_P, scrnMsg.psnCd_H);
            setValue(scrnMsg.xxPsnNm_P, scrnMsg.xxPsnNm_H);
        }
        ZYPTableUtil.clear(scrnMsg.R);
        // mod start 2016/03/31 CSA Defect#4916

        // 0 : Lv1 : Suffix (String: Output Item Suffix)
        // 1 : Lv1 : Window Title (String: Popup Title)
        // 2 : Lv1 : Select Table Name (String: Search SQL)
        // 3 : Lv2 : Where List (List: Search condition columns)
        // 4 : Lv2 : Column List (List: Search result columns)
        // 5 : Lv2 : Sort Condition (List: Sort columns)
        // 6 : Lv2 : Output
        Object[] prm = new Object[NWAL1130_PRM_LENGTH];
        int i = 0;
        prm[i++] = "";
        prm[i++] = "Rep  Search Popup";
        prm[i++] = getSelectSQL(scrnMsg, getGlobalCompanyCode(), ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
        if (index >= 0) {
            prm[i++] = getSearchConditionSettingDtl(scrnMsg, index);
        } else {
            prm[i++] = getSearchConditionSetting(scrnMsg);
        }
        prm[i++] = getDisplayColumnSetting(scrnMsg);
        prm[i++] = getSortSetting(scrnMsg);
        prm[i++] = scrnMsg.R;
        setArgForSubScreen(prm);
    }

// del 2016/04/04 CSA Defect#4916
//    private List<Object> getSearchConditionSettingDtl(NSAL0640BMsg scrnMsg, int index) {
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.A.no(index).psnCd_A2.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.A.no(index).xxPsnNm_A2.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.A.no(index).svcLineBizCd_A2.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.A.no(index).svcContrBrCd_A2.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.A.no(index).svcContrBrDescTxt_A2.getValue(), ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//        whereList.add(whereObj3);
//        whereList.add(whereObj4);
//        whereList.add(whereObj5);
//        return whereList;
//    }
//
//    private List<Object> getSearchConditionSetting(NSAL0640BMsg scrnMsg) {
//        List<Object> whereList = new ArrayList<Object>();
//        Object[] whereObj1 = {"Person Code",       "PSN_CD",                scrnMsg.psnCd_H.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj2 = {"Person Name",       "PSN_NM",                scrnMsg.xxPsnNm_H.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj3 = {"Line Of Business",  "SVC_LINE_BIZ_CD",       scrnMsg.svcLineBizCd_H.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj4 = {"Branch Code",       "SVC_CONTR_BR_CD",       scrnMsg.svcContrBrCd_H.getValue(), ZYPConstant.FLG_ON_Y };
//        Object[] whereObj5 = {"Branch Name",       "SVC_CONTR_BR_DESC_TXT", scrnMsg.svcContrBrDescTxt_H.getValue(), ZYPConstant.FLG_ON_Y };
//        whereList.add(whereObj1);
//        whereList.add(whereObj2);
//        whereList.add(whereObj3);
//        whereList.add(whereObj4);
//        whereList.add(whereObj5);
//        return whereList;
//    }
//
//    private List<Object> getDisplayColumnSetting(NSAL0640BMsg scrnMsg) {
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
//    private List<Object> getSortSetting(NSAL0640BMsg scrnMsg) {
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
//    private String getSelectSQL(NSAL0640BMsg scrnMsg) {
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
//        sql = sql.replaceAll("#maxDt#", "29991231");
//        sql = sql.replaceAll("#slsDt#", ZYPDateUtil.getSalesDate(getGlobalCompanyCode()));
//        return sql;
//    }
}

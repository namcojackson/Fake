/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200.common;

import static business.servlet.NSBL0200.constant.NSBL0200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDBStringItem;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

import business.servlet.NSBL0200.NSBL0200BMsg;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2013/09/16   WDS Team        H.Yanada           Update          QC2141
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public class NSBL0200CommonLogic {

    /**
     * 
     * setBgColor
     * 
     * @param scrnMsg NSBL0200BMsg
     */
    public static void setBgColor(NSBL0200BMsg scrnMsg) {
        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        tblColor.clearRowsBG(TBL_ID, scrnMsg.A);
        if (scrnMsg.A.getValidCount() > 0) {
            tblColor.setAlternateRowsBG(TBL_ID, scrnMsg.A, 1);
        }
    }

    /**
     * 
     * setProtected
     * 
     * @param scrnMsg NSBL0200BMsg
     */
    public static void setProtected(NSBL0200BMsg scrnMsg) {
        scrnMsg.mdlNm_SC.setInputProtected(true);
        scrnMsg.mdseCd_SC.setInputProtected(true);
        //START 2015/11/25 [CSA CHANGE]
        //scrnMsg.techTz_SC.setInputProtected(true);
        //QC1457
        //scrnMsg.orgLayerNum_SC.setInputProtected(true);
        //scrnMsg.orgCd_SC.setInputProtected(true);
        //scrnMsg.orgNm_SC.setInputProtected(true);
        //scrnMsg.orgNm_TC.setInputProtected(true);
        scrnMsg.serNum_SC.setInputProtected(true);
        scrnMsg.svcContrBrDescTxt_SC.setInputProtected(true);
        scrnMsg.lineBizTpDescTxt_SC.setInputProtected(true);
        scrnMsg.svcSkillNum_SC.setInputProtected(true);
        scrnMsg.svcSkillNm_SC.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            //QC2141
            scrnMsg.A.no(i).techCd_RS.setInputProtected(true);
            scrnMsg.A.no(i).techNm_RS.setInputProtected(true);
            //scrnMsg.A.no(i).techBakTpNm_RS.setInputProtected(true);
            //scrnMsg.A.no(i).techStsNm_RS.setInputProtected(true);
            //scrnMsg.A.no(i).orgNm_RS.setInputProtected(true);
            scrnMsg.A.no(i).svcSkillLvlDescTxt_RS.setInputProtected(true);
            scrnMsg.A.no(i).svcContrBrDescTxt_RS.setInputProtected(true);
            scrnMsg.A.no(i).lineBizTpDescTxt_RS.setInputProtected(true);
        }
        //END 2015/11/25 [CSA CHANGE]
    }

    /**
     * 
     * checkInput
     * 
     * @param scrnMsg NSBL0200BMsg
     */
    public static void checkInput(NSBL0200BMsg scrnMsg) {
        //START 2015/11/25 [CSA CHANGE]
        //checkTime(scrnMsg.xxHrsMn_SF);
        //checkTime(scrnMsg.xxHrsMn_ST);

        ////QC1457
        //scrnMsg.addCheckItem(scrnMsg.orgLayerNum_TC);
        //scrnMsg.addCheckItem(scrnMsg.orgCd_SC);
        //scrnMsg.addCheckItem(scrnMsg.techBakDt_SC);
        //scrnMsg.addCheckItem(scrnMsg.xxHrsMn_SF);
        //scrnMsg.addCheckItem(scrnMsg.xxHrsMn_ST);
        scrnMsg.addCheckItem(scrnMsg.techCd_SC);
        scrnMsg.addCheckItem(scrnMsg.techNm_SC);
        scrnMsg.addCheckItem(scrnMsg.fldSvcBrCd_SC);
        //END 2015/11/25 [CSA CHANGE]
    }

    /**
     * 
     * checkTime
     * 
     * @param hrsMn Time Object
     */
    public static void checkTime(EZDBStringItem hrsMn) {
        String ts = hrsMn.getValue();
        if (ZYPCommonFunc.hasValue(hrsMn)) {
            if (!ts.matches(FORMAT_TM)) {
                hrsMn.setErrorInfo(1, NSBM0004E, new String[] {"hh:mm"});
            }
        }
    }

    /**
     * 
     * chackTimeCompare
     * 
     * @param from From time
     * @param to   To time
     */
    public static void chackTimeCompare(EZDBStringItem from, EZDBStringItem to) {
        if (from.isError()) {
            return;
        }
        if (to.isError()) {
            return;
        }
        String strFrom = getSearchTime(from.getValue());
        String strTo = getSearchTime(to.getValue());
        if (!hasValue(strFrom)) {
            return;
        }
        if (!hasValue(strTo)) {
            return;
        }

        if (strFrom.compareTo(strTo) > 0) {
            from.setErrorInfo(1, NSBM0051E, new String[]{"times"});
            to.setErrorInfo(1, NSBM0051E, new String[]{"times"});
        }
    }

    private static String getSearchTime(String target) {
        if (hasValue(target)) {
            return target.replaceAll(FORMAT_TM, FORMAT_TM_DB);
        }
        return "";
    }

    /**
     * checkOrgSearch
     * @param scrnMsg NSBL0200BMsg
     */
     //QC1457
    public static final void checkOrgSearch(NSBL0200BMsg scrnMsg) {

        if (ZYPCommonFunc.hasValue(scrnMsg.orgLayerNum_TC) && !ZYPCommonFunc.hasValue(scrnMsg.orgCd_TC)) {

            scrnMsg.orgCd_TC.setErrorInfo(1, ZZM9000E, new String[] {"Org Code" });

        } else if (!ZYPCommonFunc.hasValue(scrnMsg.orgLayerNum_TC) && ZYPCommonFunc.hasValue(scrnMsg.orgCd_TC)) {

            scrnMsg.orgLayerNum_TC.setErrorInfo(1, ZZM9000E, new String[] {"Layer" });
        }

        scrnMsg.addCheckItem(scrnMsg.orgLayerNum_TC);
        scrnMsg.addCheckItem(scrnMsg.orgCd_TC);
    }
}

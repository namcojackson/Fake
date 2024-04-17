/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0480.common;

import static business.servlet.NSAL0480.constant.NSAL0480Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.servlet.NSAL0480.NSAL0480BMsg;
import business.servlet.NSAL0480.NSAL0480_ABMsg;
import business.servlet.NSAL0480.constant.NSAL0480Constant;
import business.servlet.NSAL0480.constant.NSAL0480Constant.BTN_LBL;
import business.servlet.NSAL0480.constant.NSAL0480Constant.NSAL0480Btn;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/06   Fujitsu         M.Yamada        Create          N/A
 * 2015/10/05   Hitachi         Y.Tsuchimoto    Update          NA#CSA
 * 2015/12/03   Hitachi         K.Yamada        Update          CSA QC#1362
 * 2016/05/19   Hitachi         M.Gotou         Update          QC#8535
 * 2016/10/07   Hitachi         N.Arai          Update          QC#15001
 *</pre>
 */
public class NSAL0480CommonLogic {

    /**
     * setRowColors
     * @param scrnMsg NSAL0480BMsg
     */
    public static void setRowColors(NSAL0480BMsg scrnMsg) {

        S21TableColorController tblColor = new S21TableColorController(SCREEN_ID, scrnMsg);
        try {
            EZDBMsgArray table = (EZDBMsgArray) scrnMsg.getClass().getField("A").get(scrnMsg);
            tblColor.setAlternateRowsBG("A", table);
        } catch (Throwable e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * initControlCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initControlCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonProperties("btn1", "", BTN_LBL.SAVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn2", "", BTN_LBL.SUBMIT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn3", "", BTN_LBL.APPLY.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn4", "", BTN_LBL.APPROVE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn5", "", BTN_LBL.REJECT.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn6", "CMN_Download", BTN_LBL.DOWNLOAD.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn7", "", BTN_LBL.DELETE.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn8", "CMN_Clear", BTN_LBL.CLEAR.getBtnLbl(), 0, null);
        scrnAppli.setButtonProperties("btn9", "", BTN_LBL.RESET.getBtnLbl(), 0, null);

        scrnAppli.setButtonProperties("btn10", "CMN_Return", BTN_LBL.RETURN.getBtnLbl(), 0, null);

        scrnAppli.setButtonEnabled(NSAL0480Btn.VIEW_ITEM.btnName(), false);
    }

    /**
     * initCommonButton
     * @param scrnAppli EZDCommonHandler
     */
    public static void initCommonButton(EZDCommonHandler scrnAppli) {

        scrnAppli.setButtonEnabled("btn6", true);
        scrnAppli.setButtonEnabled("btn8", true);
        scrnAppli.setButtonEnabled("btn10", true);
    }

    /**
     * setViewItemButtonControl
     * @param scrnAppli EZDCommonHandler
     * @param isEnabled boolean
     */
    public static void setViewItemButtonControl(EZDCommonHandler scrnAppli, boolean isEnabled) {
        scrnAppli.setButtonEnabled(NSAL0480Btn.VIEW_ITEM.btnName(), isEnabled);
    }

    /**
     * control Inactive Field
     * @param scrnAppli EZDCommonHandler
     * @param scrnMsg NSAL0480BMsg
     */
    public static void protectFields(//
            EZDCommonHandler scrnAppli //
            , NSAL0480BMsg scrnMsg) {

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            NSAL0480_ABMsg abMsg = scrnMsg.A.no(i);
            // mod start 2015/12/03 CSA Defect#1362
            abMsg.t_MdlNm_A.setInputProtected(false);
            // mod end 2015/12/03 CSA Defect#1362
            abMsg.t_MdlNm_A.setInputProtected(true);
            abMsg.mdlDescTxt_A.setInputProtected(true);
            abMsg.mdlGrpNm_A.setInputProtected(true);
            abMsg.svcSegCd_A.setInputProtected(true);
            abMsg.xxScrItem10Txt_A.setInputProtected(true);
            abMsg.xxCratDt_A.setInputProtected(true);
            // START 2016/05/19 M.Gotou [QC#8535, MOD]
            abMsg.mtrGrpNm_A.setInputProtected(true);
            // END 2016/05/19 M.Gotou [QC#8535, MOD]
            abMsg.svcSkillNm_A.setInputProtected(true);
            // 2015/10/05 CSA Y.Tsuchimoto Add Star
            abMsg.svcIstlRuleNum_A.setInputProtected(true);
            abMsg.svcDeinsRuleNum_A.setInputProtected(true);
            abMsg.custIstlFlg_A.setInputProtected(true);
            abMsg.svcIstlReqFlg_A.setInputProtected(true);
            abMsg.siteSrvyReqFlg_A.setInputProtected(true);
            // 2015/10/05 CSA Y.Tsuchimoto Add End
        }
    }

    /**
     * commonAddCheckItem
     * @param scrnMsg NSAL0480BMsg
     */
    public static void commonAddCheckItem(NSAL0480BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.srchOptPk_S);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_S);

        scrnMsg.addCheckItem(scrnMsg.t_MdlNm_H);
        scrnMsg.addCheckItem(scrnMsg.mdlDescTxt_H);
        scrnMsg.addCheckItem(scrnMsg.mdlGrpNm_H);
        scrnMsg.addCheckItem(scrnMsg.svcSegCd_H);
        scrnMsg.addCheckItem(scrnMsg.mdlActvFlg_H);
        scrnMsg.addCheckItem(scrnMsg.xxCratDt_H);
        scrnMsg.addCheckItem(scrnMsg.mtrGrpPk_H);
        scrnMsg.addCheckItem(scrnMsg.svcSkillNum_H);
        scrnMsg.addCheckItem(scrnMsg.svcIstlRuleNum_HY);
        scrnMsg.addCheckItem(scrnMsg.svcIstlReqFlg_HY);
        scrnMsg.addCheckItem(scrnMsg.svcIstlReqFlg_HN);
        scrnMsg.addCheckItem(scrnMsg.svcIstlRuleNum_HN);
        scrnMsg.addCheckItem(scrnMsg.t_ItemCd_H);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H);
        scrnMsg.addCheckItem(scrnMsg.imgSplyOemCd_H);
        scrnMsg.addCheckItem(scrnMsg.mdseItemClsTpCd_H);
        // 2015/10/05 CSA Y.Tsuchimoto Add Start
        scrnMsg.addCheckItem(scrnMsg.custIstlFlg_HY);
        scrnMsg.addCheckItem(scrnMsg.custIstlFlg_HN);
        scrnMsg.addCheckItem(scrnMsg.siteSrvyReqFlg_HY);
        scrnMsg.addCheckItem(scrnMsg.siteSrvyReqFlg_HN);
        // 2015/10/05 CSA Y.Tsuchimoto Add End
    }

// START 2016/10/07 N.Arai [QC#15001, MOD]    
    /**
     * set Parameter for Service Skills(NMAL6050)
     * @param scrnMsg NSAL0480BMsg
     */
    public static void setParamSvcSkill(NSAL0480BMsg scrnMsg) {

        setValue(scrnMsg.xxTblNm_P, "SVC_SKILL");
        setValue(scrnMsg.xxTblCdColNm_P, "SVC_SKILL_NUM");
        setValue(scrnMsg.xxTblNmColNm_P, "SVC_SKILL_DESC_TXT");
        setValue(scrnMsg.xxTblSortColNm_P, "SVC_SKILL_NUM");
        setValue(scrnMsg.xxScrNm_P, "Service Skills Popup");
        setValue(scrnMsg.xxHdrCdLbNm_P, "Skill Number");
        setValue(scrnMsg.xxHdrNmLbNm_P, "Skill Name");
        setValue(scrnMsg.xxDtlCdLbNm_P, "Skill Number");
        setValue(scrnMsg.xxDtlNmLbNm_P, "Skill Name");
        if (ZYPCommonFunc.hasValue(scrnMsg.svcSkillNum_H)) {
            setValue(scrnMsg.xxCondCd_P, scrnMsg.svcSkillNum_H);
        } else {
            scrnMsg.xxCondCd_P.clear();
        }
        scrnMsg.xxCondNm_P.clear();
    }

    /**
     * get Parameter for NMAL6050
     * @param scrnMsg NSAL0480BMsg
     * @return Parameter for NMAL6050
     */
    public static Object[] getParamNMAL6050(NSAL0480BMsg scrnMsg) {
        Object[] params = new Object[NSAL0480Constant.PRMS_11];
        params[NSAL0480Constant.PRMS_00] = scrnMsg.xxTblNm_P;
        params[NSAL0480Constant.PRMS_01] = scrnMsg.xxTblCdColNm_P;
        params[NSAL0480Constant.PRMS_02] = scrnMsg.xxTblNmColNm_P;
        params[NSAL0480Constant.PRMS_03] = scrnMsg.xxTblSortColNm_P;
        params[NSAL0480Constant.PRMS_04] = scrnMsg.xxScrNm_P;
        params[NSAL0480Constant.PRMS_05] = scrnMsg.xxHdrCdLbNm_P;
        params[NSAL0480Constant.PRMS_06] = scrnMsg.xxHdrNmLbNm_P;
        params[NSAL0480Constant.PRMS_07] = scrnMsg.xxDtlCdLbNm_P;
        params[NSAL0480Constant.PRMS_08] = scrnMsg.xxDtlNmLbNm_P;
        params[NSAL0480Constant.PRMS_09] = scrnMsg.xxCondCd_P;
        params[NSAL0480Constant.PRMS_10] = scrnMsg.xxCondNm_P;
        return params;
    }
// END 2016/10/07 N.Arai [QC#15001, MOD]}
}

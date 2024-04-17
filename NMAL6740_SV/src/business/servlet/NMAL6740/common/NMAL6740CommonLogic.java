/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6740.common;

import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_COA_AFFL_NM;
import static business.servlet.NMAL6730.constant.NMAL6730Constant.BTN_GET_COA_CH_NM;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BIZ_ID;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_01_SAV_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_01_SAV_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_01_SAV_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_02_SUB_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_02_SUB_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_02_SUB_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_03_APL_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_03_APL_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_03_APL_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_04_APR_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_04_APR_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_04_APR_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_05_REJ_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_05_REJ_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_05_REJ_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_06_DWL_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_06_DWL_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_06_DWL_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_07_DEL_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_07_DEL_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_07_DEL_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_08_CLE_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_08_CLE_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_08_CLE_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_09_RST_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_09_RST_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_09_RST_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_10_RTR_GUARD;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_10_RTR_LABEL;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.BTN_10_RTR_NAME;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_ID_TAXING_UPDATE;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.FUNC_ID_UPDATE;
import static business.servlet.NMAL6740.constant.NMAL6740Constant.TAB_TAXING;

import java.util.List;

import parts.servletcommon.EZDCommonHandler;
import business.servlet.NMAL6740.NMAL6740BMsg;

import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/20   CUSA            Fujitsu         Create          N/A
 * 2015/10/09   Fujitsu         C.Tanaka        Update          CSA
 * 2016/05/05   SRAA            Y.Chen          Update          QC#6382
 * 2016/09/09   SRAA            Y.Chen          Update          QC#9448
 * 2018/04/16   Fujitsu         M.Ohno          Update          QC#24635
 * 2018/08/07   Fujitsu         S.Ohki          Update          QC#27222
 * 2018/08/13   Fujitsu         Y.Matsui        Update          QC#27222
 *</pre>
 */
public class NMAL6740CommonLogic {
    /**
     * The initial state of the screen item is set.
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6740BMsg
     * @param userId String
     */
    public static final void initialControlScreen(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6740BMsg scrnMsg, String userId) {
        initCommonButton(userProfileService, handler, scrnMsg);
        controlScreenFields(userProfileService, handler, scrnMsg, userId);
    }

    /**
     * Method name: initCommonButton <dd>The method explanation: init
     * Common Button Control. <dd>Remarks:
     * @param userProfileService S21UserProfileService
     * @param handler EZ Common Handler
     * @param scrnMsg NMAL6740BMsg
     */
    public static final void initCommonButton(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6740BMsg scrnMsg) {

        handler.setButtonProperties(BTN_01_SAV_NAME, BTN_01_SAV_GUARD, BTN_01_SAV_LABEL, 0, null);
        handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 0, null);
        handler.setButtonProperties(BTN_03_APL_NAME, BTN_03_APL_GUARD, BTN_03_APL_LABEL, 0, null);
        handler.setButtonProperties(BTN_04_APR_NAME, BTN_04_APR_GUARD, BTN_04_APR_LABEL, 0, null);
        handler.setButtonProperties(BTN_05_REJ_NAME, BTN_05_REJ_GUARD, BTN_05_REJ_LABEL, 0, null);
        handler.setButtonProperties(BTN_06_DWL_NAME, BTN_06_DWL_GUARD, BTN_06_DWL_LABEL, 0, null);
        handler.setButtonProperties(BTN_07_DEL_NAME, BTN_07_DEL_GUARD, BTN_07_DEL_LABEL, 0, null);
        handler.setButtonProperties(BTN_08_CLE_NAME, BTN_08_CLE_GUARD, BTN_08_CLE_LABEL, 0, null);
        handler.setButtonProperties(BTN_09_RST_NAME, BTN_09_RST_GUARD, BTN_09_RST_LABEL, 1, null);
        handler.setButtonProperties(BTN_10_RTR_NAME, BTN_10_RTR_GUARD, BTN_10_RTR_LABEL, 1, null);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.contains(FUNC_ID_UPDATE)) {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        }
        // Add Start 2018/08/13 QC#27222
        if (functionIds.contains(FUNC_ID_TAXING_UPDATE)) {
            handler.setButtonProperties(BTN_02_SUB_NAME, BTN_02_SUB_GUARD, BTN_02_SUB_LABEL, 1, null);
        }
        // Add End 2018/08/13 QC#27222
    }

    /**
     * Control screen fields
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6740BMsg
     * @param userId String
     */
    public static final void controlScreenFields(S21UserProfileService userProfileService, EZDCommonHandler handler, NMAL6740BMsg scrnMsg, String userId) {
        // QC#6382
        scrnMsg.coaChNm.setInputProtected(true);
        // QC#9448
        // scrnMsg.coaAfflNm.setInputProtected(true);

        List<String> functionIds = userProfileService.getFunctionCodeListForBizAppId(BIZ_ID);
        if (functionIds.contains(FUNC_ID_UPDATE)) {
            // Del Start 2018/08/07 QC#27222
//            scrnMsg.dsCustTaxCd.setInputProtected(false);
//            scrnMsg.dsCustTaxCalcCd.setInputProtected(false);
//            scrnMsg.dsExemExprDt.setInputProtected(false);
//            scrnMsg.dsTaxExemFlg.setInputProtected(false);
//            scrnMsg.dsTaxPrntTpCd.setInputProtected(false);
//            scrnMsg.dsTaxGrpExemCd.setInputProtected(false);
            // Del End 2018/08/07 QC#27222
            scrnMsg.bigDealNum.setInputProtected(false);
            scrnMsg.coaChCd.setInputProtected(false);
            // QC#9448
            // scrnMsg.coaAfflCd.setInputProtected(false);
            // QC#6382
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], true);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], true);
            
        } else {
            // Del Start 2018/08/07 QC#27222
//            scrnMsg.dsCustTaxCd.setInputProtected(true);
//            scrnMsg.dsCustTaxCalcCd.setInputProtected(true);
//            scrnMsg.dsExemExprDt.setInputProtected(true);
//            scrnMsg.dsTaxExemFlg.setInputProtected(true);
//            scrnMsg.dsTaxPrntTpCd.setInputProtected(true);
//            scrnMsg.dsTaxGrpExemCd.setInputProtected(true);
            // Del End 2018/08/07 QC#27222
            scrnMsg.bigDealNum.setInputProtected(true);
            scrnMsg.coaChCd.setInputProtected(true);
            // QC#9448
            // scrnMsg.coaAfflCd.setInputProtected(true);
            // QC#6382
            handler.setButtonEnabled(BTN_GET_COA_CH_NM[0], false);
            handler.setButtonEnabled(BTN_GET_COA_AFFL_NM[0], false);
            
        }

        // Add Start 2018/08/07 QC#27222
        if (TAB_TAXING.equals(scrnMsg.xxDplyTab.getValue())) {
            controlScreenTabTaxing(userProfileService, handler, scrnMsg, userId, functionIds);
        }
        // Add End 2018/08/07 QC#27222
    }

    // Add Start 2018/08/07 QC#27222
    /**
     * controlScreenTabTaxing
     * @param userProfileService S21UserProfileService
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL6740BMsg
     * @param userId String
     */
    private static final void controlScreenTabTaxing(S21UserProfileService userProfileService, EZDCommonHandler handler
            , NMAL6740BMsg scrnMsg, String userId, List<String> functionIds) {

        if (functionIds.contains(FUNC_ID_TAXING_UPDATE)) {
        	scrnMsg.dsTaxGrpExemCd.setInputProtected(false);
        } else {
        	scrnMsg.dsTaxGrpExemCd.setInputProtected(true);
        }
    }
    // Add End 2018/08/07 QC#27222

    /**
     * Check addCheckItem return UPDATE
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItem(NMAL6740BMsg scrnMsg) {
        // Del Start 2018/08/07 QC#27222
//        scrnMsg.addCheckItem(scrnMsg.dsCustTaxCd);
//        scrnMsg.addCheckItem(scrnMsg.dsCustTaxCalcCd);
//        scrnMsg.addCheckItem(scrnMsg.dsExemExprDt);
//        scrnMsg.addCheckItem(scrnMsg.dsTaxExemFlg);
//        scrnMsg.addCheckItem(scrnMsg.dsTaxPrntTpCd);
        // Del End 2018/08/07 QC#27222
        scrnMsg.addCheckItem(scrnMsg.dsTaxGrpExemCd);
        scrnMsg.addCheckItem(scrnMsg.bigDealNum);
        // 2018/04/16 QC#24635 mod start
//        scrnMsg.addCheckItem(scrnMsg.coaChCd);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem130Txt);
        // 2018/04/16 QC#24635 mod end
        // QC#9448
        // scrnMsg.addCheckItem(scrnMsg.coaAfflCd);
    }
}

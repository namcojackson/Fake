/**
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.servlet.NMAL4500.common;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBStringItem;
import parts.i18n.EZDI18NContext;
import parts.i18n.EZDI18NLabelConv;
import business.servlet.NMAL4500.NMAL4500BMsg;
import business.servlet.NMAL4500.NMAL4500Bean;
import business.servlet.NMAL4500.NMAL4500_ABMsg;
import business.servlet.NMAL4500.constant.NMAL4500Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.VND_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;


/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/05/08   Fujitsu         F.Saito         Update          WDS#R-MS001
 * 2013/08/05   Fujitsu         N.Sugiura       Update          QC1469
 * 2013/09/19   Fujitsu         N.Sugiura       Update          MEX-LC004
 * 2013/10/21   Fujitsu         D.Yanagisawa    Update          MEX-LC001
 * 2016/07/29   CITS            S.Endo          Update          QC#10840
 *</pre>
 */
public class NMAL4500CommonLogic implements NMAL4500Constant {

    /**
     * @param handler handler
     * @param scrnMsg scrnMsg
     * @param profile profile
     */
    public static void doScreenControl(S21CommonHandler handler, NMAL4500BMsg scrnMsg, S21UserProfileService profile) {
        // Screen control base on function
        if (NMAL4500CommonLogic.FunctionCheck(profile)) {
            NMAL4500CommonLogic.initCommonButton_Update(handler, scrnMsg);
            NMAL4500CommonLogic.doScreenControl_Update(handler, scrnMsg);
        } else {
            NMAL4500CommonLogic.initCommonButton_Read(handler, scrnMsg);
            NMAL4500CommonLogic.doScreenControl_ReadOnly(handler, scrnMsg);
        }
    }

    /**
     * @param handler handler
     * @param scrnMsg scrnMsg
     * @param profile profile
     */
    public static void doScreenControl_VndCdSearchResult(S21CommonHandler handler, NMAL4500BMsg scrnMsg, S21UserProfileService profile) {

        // Screen control base on function
        if (NMAL4500CommonLogic.FunctionCheck(profile)) {
            NMAL4500CommonLogic.initCommonButton_Update(handler, scrnMsg);
            NMAL4500CommonLogic.doScreenControl_SearchUpdate(handler, scrnMsg);
        } else {
            NMAL4500CommonLogic.initCommonButton_Read(handler, scrnMsg);
            NMAL4500CommonLogic.doScreenControl_ReadOnly(handler, scrnMsg);
        }

    }

    private static void initCommonButton_Read(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],     BTN_CMN_SUBMIT[1],      BTN_CMN_SUBMIT[2],      0, null);
        handler.setButtonProperties(BTN_CMN_SAVE[0],       BTN_CMN_SAVE[1],        BTN_CMN_SAVE[2],        0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],      BTN_CMN_APPLY[1],       BTN_CMN_APPLY[2],       0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],    BTN_CMN_APPROVE[1],     BTN_CMN_APPROVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],     BTN_CMN_REJECT[1],      BTN_CMN_REJECT[2],      0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0],   BTN_CMN_DOWNLOAD[1],    BTN_CMN_DOWNLOAD[2],    0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],     BTN_CMN_DELETE[1],      BTN_CMN_DELETE[2],      0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],      BTN_CMN_CLEAR[1],       BTN_CMN_CLEAR[2],       1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],      BTN_CMN_RESET[1],       BTN_CMN_RESET[2],       1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],     BTN_CMN_RETURN[1],      BTN_CMN_RETURN[2],      1, null);
    }

    private static void initCommonButton_Update(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        handler.setButtonProperties(BTN_CMN_SUBMIT[0],     BTN_CMN_SUBMIT[1],      BTN_CMN_SUBMIT[2],      1, null);
        handler.setButtonProperties(BTN_CMN_SAVE[0],       BTN_CMN_SAVE[1],        BTN_CMN_SAVE[2],        0, null);
        handler.setButtonProperties(BTN_CMN_APPLY[0],      BTN_CMN_APPLY[1],       BTN_CMN_APPLY[2],       0, null);
        handler.setButtonProperties(BTN_CMN_APPROVE[0],    BTN_CMN_APPROVE[1],     BTN_CMN_APPROVE[2],     0, null);
        handler.setButtonProperties(BTN_CMN_REJECT[0],     BTN_CMN_REJECT[1],      BTN_CMN_REJECT[2],      0, null);
        handler.setButtonProperties(BTN_CMN_DOWNLOAD[0],   BTN_CMN_DOWNLOAD[1],    BTN_CMN_DOWNLOAD[2],    0, null);
        handler.setButtonProperties(BTN_CMN_DELETE[0],     BTN_CMN_DELETE[1],      BTN_CMN_DELETE[2],      0, null);
        handler.setButtonProperties(BTN_CMN_CLEAR[0],      BTN_CMN_CLEAR[1],       BTN_CMN_CLEAR[2],       1, null);
        handler.setButtonProperties(BTN_CMN_RESET[0],      BTN_CMN_RESET[1],       BTN_CMN_RESET[2],       1, null);
        handler.setButtonProperties(BTN_CMN_RETURN[0],     BTN_CMN_RETURN[1],      BTN_CMN_RETURN[2],      1, null);
    }

    private static void doScreenControl_Update(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        scrnMsg.vndCd_01.setInputProtected(false);
        scrnMsg.locNm_01.setInputProtected(false);
        scrnMsg.dbaNm_01.setInputProtected(false);
        scrnMsg.firstLineAddr_01.setInputProtected(false);
        scrnMsg.scdLineAddr_01.setInputProtected(false);
        scrnMsg.thirdLineAddr_01.setInputProtected(false);
        scrnMsg.frthLineAddr_01.setInputProtected(false);
        scrnMsg.ctryCd_03.setInputProtected(false);
        scrnMsg.postCd_01.setInputProtected(false);
        scrnMsg.ctyAddr_01.setInputProtected(false);
        scrnMsg.cntyPk_03.setInputProtected(false);
        scrnMsg.stCd_01.setInputProtected(false);
        scrnMsg.provNm_01.setInputProtected(false);
        scrnMsg.firstRefCmntTxt_01.setInputProtected(false);
        scrnMsg.scdRefCmntTxt_01.setInputProtected(false);
        scrnMsg.telNum_01.setInputProtected(false);
        scrnMsg.faxNum_01.setInputProtected(false);
        scrnMsg.taxPayerId_01.setInputProtected(false);
        scrnMsg.coaAfflCd_01.setInputProtected(false);
        scrnMsg.intlVndFlg_01.setInputProtected(false);
        scrnMsg.payeeFlg_01.setInputProtected(false);
        scrnMsg.asnReqFlg_01.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_03.setInputProtected(false);
        scrnMsg.frtChrgToCd_03.setInputProtected(false);
        scrnMsg.frtChrgMethCd_03.setInputProtected(false);
        scrnMsg.trsmtMethTpCd_03.setInputProtected(false);
        scrnMsg.chrgRateVndGrpCd_03.setInputProtected(false);
        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.setInputProtected(false);
        // ADD END   2013/05/08 WDS#R-MS001
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.setInputProtected(false);
        // ADD END 2013/09/19 MEX-LC004
        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.invVndCd_01.setInputProtected(false);
        scrnMsg.locNm_02.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        /** ADD-START 2009/12/08 takamura Link Screen Control **/
        scrnMsg.invRcvMethTpCd_03.setInputProtected(false);

        scrnMsg.xxLinkProt_01.setInputProtected(false);
        scrnMsg.xxLinkProt_02.setInputProtected(false);
        scrnMsg.xxLinkProt_03.setInputProtected(false);
        scrnMsg.xxLinkProt_04.setInputProtected(false);
        /** ADD-END **/

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.xxLinkProt_05.setInputProtected(false);
        scrnMsg.xxLinkProt_06.setInputProtected(false);
        // ADD END 2013/10/21 MEX-LC001

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_01.setInputProtected(false);
        scrnMsg.whCd_02.setInputProtected(false);
        scrnMsg.whPk_03.setInputProtected(false);
        scrnMsg.attnNm_01.setInputProtected(false);

        scrnMsg.carrTpCd_03.setInputProtected(false); // 10/22/2015 add 

        scrnMsg.thirdPtyVndFlg_01.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        }

        handler.setButtonEnabled("Search_Vendor_CD", true);
        handler.setButtonEnabled("Get_Address", true);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);
    }

    private static void doScreenControl_ReadOnly(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        scrnMsg.vndCd_01.setInputProtected(false);
        scrnMsg.locNm_01.setInputProtected(false);
        scrnMsg.dbaNm_01.setInputProtected(false);
        scrnMsg.firstLineAddr_01.setInputProtected(true);
        scrnMsg.scdLineAddr_01.setInputProtected(true);
        scrnMsg.thirdLineAddr_01.setInputProtected(true);
        scrnMsg.frthLineAddr_01.setInputProtected(true);
        scrnMsg.ctryCd_03.setInputProtected(true);
        scrnMsg.postCd_01.setInputProtected(true);
        scrnMsg.ctyAddr_01.setInputProtected(true);
        scrnMsg.cntyPk_03.setInputProtected(true);
        scrnMsg.stCd_01.setInputProtected(true);
        scrnMsg.provNm_01.setInputProtected(true);
        scrnMsg.firstRefCmntTxt_01.setInputProtected(true);
        scrnMsg.scdRefCmntTxt_01.setInputProtected(true);
        scrnMsg.telNum_01.setInputProtected(true);
        scrnMsg.faxNum_01.setInputProtected(true);
        scrnMsg.taxPayerId_01.setInputProtected(true);
        scrnMsg.coaAfflCd_01.setInputProtected(true);
        scrnMsg.intlVndFlg_01.setInputProtected(true);
        scrnMsg.payeeFlg_01.setInputProtected(true);
        scrnMsg.asnReqFlg_01.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_03.setInputProtected(true);
        scrnMsg.frtChrgToCd_03.setInputProtected(true);
        scrnMsg.frtChrgMethCd_03.setInputProtected(true);
        scrnMsg.trsmtMethTpCd_03.setInputProtected(true);
        scrnMsg.chrgRateVndGrpCd_03.setInputProtected(true);

        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.setInputProtected(true);
        // ADD END   2013/05/08 WDS#R-MS001
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.setInputProtected(true);
        // ADD END 2013/09/19 MEX-LC004
        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.invVndCd_01.setInputProtected(true);
        scrnMsg.locNm_02.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        /** ADD-START 2009/12/08 takamura Link Screen Control **/
        scrnMsg.invRcvMethTpCd_03.setInputProtected(true);

        scrnMsg.xxLinkProt_01.setInputProtected(false);
        scrnMsg.xxLinkProt_02.setInputProtected(true);
        scrnMsg.xxLinkProt_03.setInputProtected(true);
        scrnMsg.xxLinkProt_04.setInputProtected(true);
        /** ADD-END **/

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.xxLinkProt_05.setInputProtected(true);
        scrnMsg.xxLinkProt_06.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_01.setInputProtected(true);
        scrnMsg.whCd_02.setInputProtected(true);
        scrnMsg.whPk_03.setInputProtected(true);
        scrnMsg.attnNm_01.setInputProtected(true);

        scrnMsg.carrTpCd_03.setInputProtected(true); // 10/22/2015 add 

        scrnMsg.thirdPtyVndFlg_01.setInputProtected(true);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
        }

        handler.setButtonEnabled("Search_Vendor_CD", true);
        handler.setButtonEnabled("Get_Address", false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
    }

    /** ADD-START 2009/12/08 takamura **/
    private static void doScreenControl_SearchUpdate(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {

        // Header
        scrnMsg.vndCd_01.setInputProtected(true);
        scrnMsg.locNm_01.setInputProtected(false); // D7138, updated by itoga. 2010/06/15
        scrnMsg.dbaNm_01.setInputProtected(false);

        // Detail
        scrnMsg.firstLineAddr_01.setInputProtected(false);
        scrnMsg.scdLineAddr_01.setInputProtected(false);
        scrnMsg.thirdLineAddr_01.setInputProtected(false);
        scrnMsg.frthLineAddr_01.setInputProtected(false);
        scrnMsg.ctryCd_03.setInputProtected(false);
        scrnMsg.postCd_01.setInputProtected(false);
        scrnMsg.ctyAddr_01.setInputProtected(false);
        scrnMsg.cntyPk_03.setInputProtected(false);
        scrnMsg.stCd_01.setInputProtected(false);
        scrnMsg.provNm_01.setInputProtected(false);
        scrnMsg.firstRefCmntTxt_01.setInputProtected(false);
        scrnMsg.scdRefCmntTxt_01.setInputProtected(false);
        scrnMsg.telNum_01.setInputProtected(false);
        scrnMsg.faxNum_01.setInputProtected(false);
        scrnMsg.taxPayerId_01.setInputProtected(false);
        scrnMsg.coaAfflCd_01.setInputProtected(false);
        scrnMsg.intlVndFlg_01.setInputProtected(false);
        scrnMsg.payeeFlg_01.setInputProtected(false);
        scrnMsg.asnReqFlg_01.setInputProtected(false);
        scrnMsg.shpgSvcLvlCd_03.setInputProtected(false);
        scrnMsg.frtChrgToCd_03.setInputProtected(false);
        scrnMsg.frtChrgMethCd_03.setInputProtected(false);
        scrnMsg.trsmtMethTpCd_03.setInputProtected(false);
        scrnMsg.invRcvMethTpCd_03.setInputProtected(false);
        scrnMsg.chrgRateVndGrpCd_03.setInputProtected(false);
        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.setInputProtected(false);
        // ADD END   2013/05/08 WDS#R-MS001
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.setInputProtected(false);
        // ADD END 2013/09/19 MEX-LC004

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.invVndCd_01.setInputProtected(false);
        scrnMsg.locNm_02.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        // Screen Link
        scrnMsg.xxLinkProt_01.setInputProtected(true);
        scrnMsg.xxLinkProt_02.setInputProtected(false);
        scrnMsg.xxLinkProt_03.setInputProtected(false);
        scrnMsg.xxLinkProt_04.setInputProtected(false);

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.xxLinkProt_05.setInputProtected(false);
        scrnMsg.xxLinkProt_06.setInputProtected(false);
        // ADD END 2013/10/21 MEX-LC001

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_01.setInputProtected(true);
        scrnMsg.whCd_02.setInputProtected(true);
        scrnMsg.whPk_03.setInputProtected(true);
        scrnMsg.attnNm_01.setInputProtected(false);

        scrnMsg.carrTpCd_03.setInputProtected(false); // 10/22/2015 add 

        scrnMsg.thirdPtyVndFlg_01.setInputProtected(false);
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
        }

        handler.setButtonEnabled("Search_Vendor_CD", false);
        handler.setButtonEnabled("Get_Address", true);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], true);

    }
    /** ADD-END **/
    /**
     * @param handler handler
     * @param scrnMsg scrnMsg
     */
    public static void initScreenRadio(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        scrnMsg.intlVndFlg_01.setValue("N");
        scrnMsg.payeeFlg_01.setValue("N");
        scrnMsg.asnReqFlg_01.setValue("N");
        scrnMsg.thirdPtyVndFlg_01.setValue("N");
    }

    /**
     * @param handler handler
     * @param scrnMsg scrnMsg
     */
    public static void initScreenClearExceptVenderCode(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        scrnMsg.locNm_01.clear();
        scrnMsg.dbaNm_01.clear();
        scrnMsg.firstLineAddr_01.clear();
        scrnMsg.scdLineAddr_01.clear();
        scrnMsg.thirdLineAddr_01.clear();
        scrnMsg.frthLineAddr_01.clear();
        scrnMsg.ctryCd_03.clear();
        scrnMsg.postCd_01.clear();
        scrnMsg.ctyAddr_01.clear();
        scrnMsg.cntyPk_03.clear();
        scrnMsg.cntyNm_02.clear();
        scrnMsg.cntyPk_01.clear();
        scrnMsg.stCd_01.clear();
        scrnMsg.provNm_01.clear();
        scrnMsg.firstRefCmntTxt_01.clear();
        scrnMsg.scdRefCmntTxt_01.clear();
        scrnMsg.telNum_01.clear();
        scrnMsg.faxNum_01.clear();
        scrnMsg.taxPayerId_01.clear();
        scrnMsg.coaAfflCd_01.clear();
        scrnMsg.intlVndFlg_01.clear();
        scrnMsg.payeeFlg_01.clear();
        scrnMsg.asnReqFlg_01.clear();
        scrnMsg.shpgSvcLvlCd_03.clear();
        scrnMsg.frtChrgToCd_03.clear();
        scrnMsg.frtChrgMethCd_03.clear();
        scrnMsg.trsmtMethTpCd_03.clear();
        scrnMsg.chrgRateVndGrpCd_03.clear();
        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.clear();
        // ADD END   2013/05/08 WDS#R-MS001
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.clear();
        // ADD END 2013/09/19 MEX-LC004

        // ADD START 2013/10/21 MEX-LC004
        scrnMsg.invVndCd_01.clear();
        scrnMsg.locNm_02.clear();
        // ADD END 2013/10/21 MEX-LC004

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_03.clear();
        scrnMsg.attnNm_01.clear();

        scrnMsg.carrTpCd_03.clear(); // 10/22/2015 add 

        scrnMsg.thirdPtyVndFlg_01.clear();
        int i = 0;
        for (; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setValue(ZYPConstant.FLG_OFF_N);
        }

        initScreenRadio(handler, scrnMsg);
    }

    /**
     * @param handler handler
     * @param scrnMsg scrnMsg
     */
    public static void doScreenControl_AfterSubmit(S21CommonHandler handler, NMAL4500BMsg scrnMsg) {
        scrnMsg.vndCd_01.setInputProtected(true);
        scrnMsg.locNm_01.setInputProtected(true);
        scrnMsg.dbaNm_01.setInputProtected(true);
        scrnMsg.firstLineAddr_01.setInputProtected(true);
        scrnMsg.scdLineAddr_01.setInputProtected(true);
        scrnMsg.thirdLineAddr_01.setInputProtected(true);
        scrnMsg.frthLineAddr_01.setInputProtected(true);
        scrnMsg.ctryCd_03.setInputProtected(true);
        scrnMsg.postCd_01.setInputProtected(true);
        scrnMsg.ctyAddr_01.setInputProtected(true);
        scrnMsg.cntyPk_03.setInputProtected(true);
        scrnMsg.stCd_01.setInputProtected(true);
        scrnMsg.provNm_01.setInputProtected(true);
        scrnMsg.firstRefCmntTxt_01.setInputProtected(true);
        scrnMsg.scdRefCmntTxt_01.setInputProtected(true);
        scrnMsg.telNum_01.setInputProtected(true);
        scrnMsg.faxNum_01.setInputProtected(true);
        scrnMsg.taxPayerId_01.setInputProtected(true);
        scrnMsg.coaAfflCd_01.setInputProtected(true);
        scrnMsg.intlVndFlg_01.setInputProtected(true);
        scrnMsg.payeeFlg_01.setInputProtected(true);
        scrnMsg.asnReqFlg_01.setInputProtected(true);
        scrnMsg.shpgSvcLvlCd_03.setInputProtected(true);
        scrnMsg.frtChrgToCd_03.setInputProtected(true);
        scrnMsg.frtChrgMethCd_03.setInputProtected(true);
        scrnMsg.trsmtMethTpCd_03.setInputProtected(true);
        scrnMsg.chrgRateVndGrpCd_03.setInputProtected(true);

        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.setInputProtected(true);
        // ADD END   2013/05/08 WDS#R-MS001
        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.setInputProtected(true);
        // ADD END 2013/09/19 MEX-LC004
        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.invVndCd_01.setInputProtected(true);
        scrnMsg.locNm_02.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        /** ADD-START 2009/12/08 takamura Link Screen Control **/
        scrnMsg.invRcvMethTpCd_03.setInputProtected(true);

        scrnMsg.xxLinkProt_01.setInputProtected(true);
        scrnMsg.xxLinkProt_02.setInputProtected(true);
        scrnMsg.xxLinkProt_03.setInputProtected(true);
        scrnMsg.xxLinkProt_04.setInputProtected(true);
        /** ADD-END **/

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.xxLinkProt_05.setInputProtected(true);
        scrnMsg.xxLinkProt_06.setInputProtected(true);
        // ADD END 2013/10/21 MEX-LC001

        // <defect#5203,5206 T.Ishii 20100405>
        scrnMsg.whPk_01.setInputProtected(true);
        scrnMsg.whCd_02.setInputProtected(true);
        scrnMsg.whPk_03.setInputProtected(true);
        scrnMsg.attnNm_01.setInputProtected(true);

        scrnMsg.carrTpCd_03.setInputProtected(true); // 10/22/2015 add 

        scrnMsg.thirdPtyVndFlg_01.setInputProtected(true);
        int i = 0;
        for (; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
        }

        handler.setButtonEnabled("Search_Vendor_CD", false);
        handler.setButtonEnabled("Get_Address", false);
        handler.setButtonEnabled(BTN_CMN_SUBMIT[0], false);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////   Common  method                             ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_Vnd(NMAL4500BMsg scrnMsg) {

        // 10/22/2015 mod start
//        scrnMsg.xxTblNm_Z1.setValue("VND");
//        scrnMsg.xxTblCdColNm_Z1.setValue("VND_CD");
//        scrnMsg.xxTblNmColNm_Z1.setValue("LOC_NM");
//        scrnMsg.xxTblSortColNm_Z1.setValue("VND_CD");
//        scrnMsg.xxScrNm_Z1.setValue("Lookup Vendor");
//        scrnMsg.xxHdrCdLbNm_Z1.setValue("Vendor CD");
//        scrnMsg.xxHdrNmLbNm_Z1.setValue("Name");
//        scrnMsg.xxDtlCdLbNm_Z1.setValue("Vendor CD");
//        scrnMsg.xxDtlNmLbNm_Z1.setValue("Name");
//        if (!scrnMsg.vndCd_01.isClear()) {
//            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.vndCd_01.getValue());
//        } else {
//            scrnMsg.xxCondCd_Z1.clear();
//        }
//        scrnMsg.xxCondNm_Z1.clear();
        scrnMsg.xxTblNm_Z1.setValue("OTBD_CARR_V");
        scrnMsg.xxTblCdColNm_Z1.setValue("CARR_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("CARR_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("CARR_CD");
        scrnMsg.xxScrNm_Z1.setValue("Lookup Carrier");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Carrier CD");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Carrier CD");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("Name");
        if (!scrnMsg.vndCd_01.isClear()) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.vndCd_01.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
        // 10/22/2015 mod end
    }

    // ADD START 2013/10/21 MEX-LC001

    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_InvVnd(NMAL4500BMsg scrnMsg) {

        // 10/22/2015 mod start
//        scrnMsg.xxTblNm_Z1.setValue("VND");
//        scrnMsg.xxTblCdColNm_Z1.setValue("VND_CD");
//        scrnMsg.xxTblNmColNm_Z1.setValue("LOC_NM");
//        scrnMsg.xxTblSortColNm_Z1.setValue("VND_CD");
//        scrnMsg.xxScrNm_Z1.setValue("Lookup Vendor");
//        scrnMsg.xxHdrCdLbNm_Z1.setValue("Vendor CD");
//        scrnMsg.xxHdrNmLbNm_Z1.setValue("Name");
//        scrnMsg.xxDtlCdLbNm_Z1.setValue("Vendor CD");
//        scrnMsg.xxDtlNmLbNm_Z1.setValue("Name");
//        if (!scrnMsg.invVndCd_01.isClear()) {
//            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.invVndCd_01.getValue());
//        } else {
//            scrnMsg.xxCondCd_Z1.clear();
//        }
//        scrnMsg.xxCondNm_Z1.clear();
        scrnMsg.xxTblNm_Z1.setValue("OTBD_CARR_V");
        scrnMsg.xxTblCdColNm_Z1.setValue("CARR_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("CARR_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("CARR_CD");
        scrnMsg.xxScrNm_Z1.setValue("Lookup Carrier");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Carrier CD");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Carrier CD");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("Name");
        if (!scrnMsg.invVndCd_01.isClear()) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.invVndCd_01.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
        // 10/22/2015 mod end
    }
    // ADD E N D 2013/10/21 MEX-LC001

    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_City(NMAL4500BMsg scrnMsg) {

        scrnMsg.xxTblNm_Z1.setValue("POST_V");
        scrnMsg.xxTblCdColNm_Z1.setValue("POST_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("CTY_ADDR_ST_CD");
        scrnMsg.xxTblSortColNm_Z1.setValue("POST_CD");
        scrnMsg.xxScrNm_Z1.setValue("Lookup City");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Postal Code");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("City");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Postal Code");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("City");
        if (!scrnMsg.postCd_01.isClear()) {
            // QC :5515
            String postCdValue = scrnMsg.postCd_01.getValue();
            if (ZYPCommonFunc.hasValue(postCdValue) && postCdValue.length() > 5) {
                postCdValue = postCdValue.substring(0, 5);
            }
            scrnMsg.xxCondCd_Z1.setValue(postCdValue);
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
    }

    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_State(NMAL4500BMsg scrnMsg) {

        scrnMsg.xxTblNm_Z1.setValue("ST");
        scrnMsg.xxTblCdColNm_Z1.setValue("ST_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("ST_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("ST_SORT_NUM");
        scrnMsg.xxScrNm_Z1.setValue("Lookup State");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("State Code");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("State Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("State Code");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("State Name");
        if (!scrnMsg.stCd_01.isClear()) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.stCd_01.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
    }

    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_Affiliation(NMAL4500BMsg scrnMsg) {

        scrnMsg.xxTblNm_Z1.setValue("COA_AFFL");
        scrnMsg.xxTblCdColNm_Z1.setValue("COA_AFFL_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("COA_AFFL_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("COA_AFFL_CD");
        scrnMsg.xxScrNm_Z1.setValue("Lookup Affiliation");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Affiliation Code");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("Affiliation Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Affiliation Code");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("Affiliation Name");
        if (!scrnMsg.coaAfflCd_01.isClear()) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.coaAfflCd_01.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
    }
    // ADD START 2013/09/19 MEX-LC004
    /**
     * @param scrnMsg scrnMsg
     */
    public static void setInitParam_Currency(NMAL4500BMsg scrnMsg) {

        scrnMsg.xxTblNm_Z1.setValue("CCY");
        scrnMsg.xxTblCdColNm_Z1.setValue("CCY_CD");
        scrnMsg.xxTblNmColNm_Z1.setValue("CCY_NM");
        scrnMsg.xxTblSortColNm_Z1.setValue("CCY_SORT_NUM");
        scrnMsg.xxScrNm_Z1.setValue("Currency Search");
        scrnMsg.xxHdrCdLbNm_Z1.setValue("Currency Code");
        scrnMsg.xxHdrNmLbNm_Z1.setValue("Currency Name");
        scrnMsg.xxDtlCdLbNm_Z1.setValue("Currency Code");
        scrnMsg.xxDtlNmLbNm_Z1.setValue("Currency Name");
        if (!scrnMsg.dealCcyCd_01.isClear()) {
            scrnMsg.xxCondCd_Z1.setValue(scrnMsg.dealCcyCd_01.getValue());
        } else {
            scrnMsg.xxCondCd_Z1.clear();
        }
        scrnMsg.xxCondNm_Z1.clear();
    }
    // ADD END 2013/09/19 MEX-LC004
    /**
     * @param scrnMsg scrnMsg
     * @return Object[]
     */
    public static Object[] getParamOpenWin_NMAL6050(NMAL4500BMsg scrnMsg) {
        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm_Z1;
        params[1] = scrnMsg.xxTblCdColNm_Z1;
        params[2] = scrnMsg.xxTblNmColNm_Z1;
        params[3] = scrnMsg.xxTblSortColNm_Z1;
        params[4] = scrnMsg.xxScrNm_Z1;
        params[5] = scrnMsg.xxHdrCdLbNm_Z1;
        params[6] = scrnMsg.xxHdrNmLbNm_Z1;
        params[7] = scrnMsg.xxDtlCdLbNm_Z1;
        params[8] = scrnMsg.xxDtlNmLbNm_Z1;
        params[9] = scrnMsg.xxCondCd_Z1;
        params[10] = scrnMsg.xxCondNm_Z1;

        return params;
    }


    /**
     * @param scrnMsg scrnMsg
     */
    public static void setNameForMessage_INIT(NMAL4500BMsg scrnMsg) {
        EZDI18NLabelConv labelConv = EZDI18NContext.getInstance().getI18NAccessor().getLabelConv();

        scrnMsg.vndCd_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Vender Code"));
        scrnMsg.locNm_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Vendor Name"));
        scrnMsg.coaAfflCd_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Affiliation"));

        // <defect#5206 T.Ishii 20100405>
        scrnMsg.whPk_03.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Warehouse Code"));
        scrnMsg.attnNm_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Attention Name"));

        scrnMsg.carrTpCd_03.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Carrier Type")); // 10/22/2015 add

        // ADD START 2013/05/08 WDS#R-MS001
        scrnMsg.sendPoEmlAddr_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Send PO Notification via email to"));
        // ADD END   2013/05/08 WDS#R-MS001

        // ADD START 2013/09/19 MEX-LC004
        scrnMsg.dealCcyCd_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Deal Currency Code"));
        // ADD END 2013/09/19 MEX-LC004

        // ADD START 2013/10/21 MEX-LC001
        scrnMsg.invVndCd_01.setNameForMessage(labelConv.convLabel2i18nLabel(SCREEN_ID, "Invoice Vendor Code"));
        // ADD END 2013/10/21 MEX-LC001
    }

    /** QC Defect ID : 2537 2009/12/11 takamura ADD-START **/
    /**
     * @param scrnMsg scrnMsg
     * @return boolean
     */
    public static boolean isInfoMessage(NMAL4500BMsg scrnMsg) {

        String msgCode = scrnMsg.getMessageCode();

        if (msgCode != null && msgCode.length() > 0) {
            if (msgCode.charAt(msgCode.length() - 1) == 'I') {
                return true;
            }
        }
        return false;

    }
    /** ADD-END **/

    // <defect#5206 T.Ishii 20100405>
    /**
     * @param scrnMsg scrnMsg
     */
    public static void addCheckItemForVndTp(NMAL4500BMsg scrnMsg) {
        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL4500Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        for (int checkedRow : checkedRows) {
            scrnMsg.addCheckItem(((NMAL4500_ABMsg) scrnMsg.A.get(checkedRow)).xxChkBox_A1);
        }
    }

    /**
     * @param scrnMsg scrnMsg
     * @return boolean
     */
    public static boolean validateWHVndTpRelations(NMAL4500BMsg scrnMsg) {

        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL4500Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        boolean containsInHouseKittingOrInHouseRefubish = false;
        boolean containsOtherVndType = false;
        for (int checkedRow : checkedRows) {
            String vndTpCd = ((NMAL4500_ABMsg) scrnMsg.A.get(checkedRow)).vndTpCd_A1.getValue();
            if (ZYPCommonFunc.hasValue(vndTpCd)) {
                if (VND_TP.IN_HOUSE_KITTING.equals(vndTpCd) || (VND_TP.IN_HOUSE_REFURBISH.equals(vndTpCd))) {
                    containsInHouseKittingOrInHouseRefubish = true;
                } else {
                    containsOtherVndType = true;
                }
            }
        }

        if (containsInHouseKittingOrInHouseRefubish && containsOtherVndType) {
            setErrorCodeForVndTp(scrnMsg, "NMAM8067E");
            return false;
        }

        if (containsInHouseKittingOrInHouseRefubish) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.whPk_03)) {
                // wh is mandatory
                setErrorCodeForVndTp(scrnMsg, "NMAM8068E");
                scrnMsg.whPk_03.setErrorInfo(1, "NMAM8068E");
                return false;
            }
        } else {
            if (ZYPCommonFunc.hasValue(scrnMsg.whPk_03)) {
                // wh should be empty
                setErrorCodeForVndTp(scrnMsg, "NMAM8069E");
                scrnMsg.whPk_03.setErrorInfo(1, "NMAM8069E");
                return false;
            }
        }
        return true;
    }

    private static void setErrorCodeForVndTp(NMAL4500BMsg scrnMsg, String msgCode) {
        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL4500Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        for (int checkedRow : checkedRows) {
            ((NMAL4500_ABMsg) scrnMsg.A.get(checkedRow)).xxChkBox_A1.setErrorInfo(1, msgCode);
        }
    }

    /** ADD-END **/

    // <defect#5267 T.Ishii 20100413>
    /**
     * @param scrnMsg scrnMsg
     */
    public static void validateSelectedVndTp(NMAL4500BMsg scrnMsg) {
        List<Integer> checkedRows = ZYPTableUtil.getSelectedRows(scrnMsg.A, NMAL4500Bean.xxChkBox_A1, ZYPConstant.CHKBOX_ON_Y);
        for (int checkedRow : checkedRows) {
            String vndTpCd = ((NMAL4500_ABMsg) scrnMsg.A.get(checkedRow)).vndTpCd_A1.getValue();
            if (ZYPCommonFunc.hasValue(vndTpCd)) {
                if (VND_TP.CROSSDOCK.equals(vndTpCd) || VND_TP.TRANSLOADING.equals(vndTpCd)) {
                    if (checkedRows.size() > 1) {
                        setErrorCodeForVndTp(scrnMsg, "NMAM8073E");
                        return;
                    }
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////   Function  method                           ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    

    /**
     * @param userProfileService userProfileService
     * @return boolean
     */
    public static boolean FunctionCheck(S21UserProfileService userProfileService) {

        List<String> list = userProfileService.getFunctionCodeListForBizAppId(BUSINESS_ID);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(FUNCTION_UPDATE)) {
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////   ErrMessage  method                         ///////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @param scrnMsg scrnMsg
     * @return boolean
     */
    public static boolean isErrMessage(NMAL4500BMsg scrnMsg) {
        String msgCode = scrnMsg.getMessageCode();

        if (msgCode != null && msgCode.length() > 0) {
            if (msgCode.charAt(msgCode.length() - 1) == 'E') {
                return true;
            }
        }
        return false;
    }

    // DEL START 2013/08/05 QC1469
    // public static void checkPostCd(NMAL4500BMsg scrnMsg) {
    //
    //     if (!scrnMsg.ctryCd_03.getValue().equals(CTRY.UNITED_STATES_OF_AMERICA)) {
    //         return;
    //     }
    //     String postCd = scrnMsg.postCd_01.getValue();
    //     if (ZYPCommonFunc.hasValue(postCd) && postCd.length() > 5) {
    //         try {
    //             new BigDecimal(String.valueOf(postCd.substring(0, 5)));
    //             new BigDecimal(String.valueOf(postCd.substring(6, postCd.length())));
    //         } catch (Exception e) {
    //             scrnMsg.postCd_01.setErrorInfo(1, "NMAM8075E", new String[] {ZIP_CODE_FORMAT});
    //         }
    //         if (!postCd.substring(5, 6).equals("-")) {
    //             scrnMsg.postCd_01.setErrorInfo(1, "NMAM8075E", new String[] {ZIP_CODE_FORMAT});
    //         }
    //         scrnMsg.addCheckItem(scrnMsg.postCd_01);
    //     }
    // }
    // DEL END 2013/08/05 QC1469
    // ADD START 2013/05/08 WDS#R-MS001
    /**
     * Check Send PO Notification via email to
     * @param sendPoEmlAddr Address Text By Screen
     * @param isMandatory Need(True) or Need Not(False)
     * @return OK(True) or NG(False)
     */
    public static boolean checkPoEmlAddr(EZDBStringItem sendPoEmlAddr, boolean isMandatory) {
        // Is Empty
        if (!ZYPCommonFunc.hasValue(sendPoEmlAddr)) {
            if (isMandatory) {
                sendPoEmlAddr.setErrorInfo(1, "NMAM0075E", new String[]{"Outb PO Trans Method　is EMAIL", MAIL_FORMAT});
                return false;
            } else {
                // Empty Is OK
                return true;
            }
        }

        // Separate Comma
        for (String emlAddr : sendPoEmlAddr.getValue().split(COMMA, -1)) {
            // Check Address
            if (emlAddr == null || !checkEmlAddr(emlAddr.trim())) {
                sendPoEmlAddr.setErrorInfo(1, "NMAM8075E", new String[]{MAIL_FORMAT});
                return false;
            }
        }

        // No Error
        return true;
    }

    /**
     * Check EMail Format
     * @param eMail Target Mail Address
     * @return OK(True) or NG(False)
     */
    public static boolean checkEmlAddr(String eMail) {
        // Empty Address Is Error
        if (eMail.isEmpty()) {
            return false;
        }

        // No Or Too Many '@'
        int atIdx = eMail.indexOf(AT_MARK);
        if (atIdx < 0 || atIdx != eMail.lastIndexOf(AT_MARK)) {
            return false;
        }
        // No Dot After '@'
        if (eMail.lastIndexOf(DOT) < atIdx) {
            return false;
        }

        // Return OK
        return true;
    }
    // ADD END   2013/05/08 WDS#R-MS001

    /**
     * Get Address Lookup Popup param
     * @param scrnMsg NPAL1500BMsg
     * @param glblCmpyCd String
     * @return Parameter[ Object[7] ]
     */
    public static Object[] getAddressPopupParam(NMAL4500BMsg scrnMsg, String glblCmpyCd) {
        Object[] params = new Object[IDX_7];
        scrnMsg.P.clear();

        params[IDX_0] = "";
        params[IDX_1] = "Address Lookup Popup";
        StringBuilder baseSql = new StringBuilder();
        baseSql.append("SELECT ");
        baseSql.append("    P.GLBL_CMPY_CD ");
        baseSql.append("  , P.EZCANCELFLAG ");
        baseSql.append("  , P.CTY_ADDR ");
        baseSql.append("  , P.ST_CD ");
        baseSql.append("  , P.POST_CD ");
        baseSql.append("  , C.CNTY_NM ");
        baseSql.append("FROM ");
        baseSql.append("    POST P ");
        baseSql.append("  , CNTY_POST_RELN R ");
        baseSql.append("  , CNTY C ");
        baseSql.append("WHERE ");
        baseSql.append("    P.GLBL_CMPY_CD = '" + glblCmpyCd + "' ");
        baseSql.append("AND P.EZCANCELFLAG = '0' ");
        baseSql.append("AND R.POST_PK(+) = P.POST_PK ");
        baseSql.append("AND R.GLBL_CMPY_CD(+) = P.GLBL_CMPY_CD ");
        baseSql.append("AND R.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.GLBL_CMPY_CD(+) = R.GLBL_CMPY_CD ");
        baseSql.append("AND C.EZCANCELFLAG(+) = '0' ");
        baseSql.append("AND C.CNTY_PK(+) = R.CNTY_PK ");
        String sql = baseSql.toString();
        params[IDX_2] = sql;

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray1 = new Object[IDX_4];
        whereArray1[IDX_0] = "City";
        whereArray1[IDX_1] = "CTY_ADDR";
        whereArray1[IDX_2] = scrnMsg.ctyAddr_01.getValue();
        whereArray1[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[IDX_4];
        whereArray2[IDX_0] = "State";
        whereArray2[IDX_1] = "ST_CD";
        whereArray2[IDX_2] = scrnMsg.stCd_01.getValue();
        whereArray2[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray2);

        Object[] whereArray3 = new Object[IDX_4];
        whereArray3[IDX_0] = "Postal Code";
        whereArray3[IDX_1] = "POST_CD";
        whereArray3[IDX_2] = scrnMsg.postCd_01.getValue();
        whereArray3[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray3);

        Object[] whereArray4 = new Object[IDX_4];
        whereArray4[IDX_0] = "County";
        whereArray4[IDX_1] = "CNTY_NM";
        String selectedCntyNm = "";
       for (int i = 0; i < scrnMsg.cntyPk_01.length(); i++) {
           if (scrnMsg.cntyPk_03.getValue() == null) {
               break;
           }
           if (scrnMsg.cntyPk_03.getValue().compareTo(((EZDBBigDecimalItem) scrnMsg.cntyPk_01.get(i)).getValue()) == 0) {
               selectedCntyNm = ((EZDBStringItem) scrnMsg.cntyNm_02.get(i)).getValue();
               break;
           }
       }
        whereArray4[IDX_2] = selectedCntyNm;
        whereArray4[IDX_3] = ZYPConstant.FLG_ON_Y;
        whereList.add(whereArray4);

        params[IDX_3] = whereList;

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray1 = new Object[IDX_4];
        columnArray1[0] = "City";
        columnArray1[1] = "CTY_ADDR";
        columnArray1[2] = BigDecimal.valueOf(25);
        columnArray1[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[IDX_4];
        columnArray2[0] = "State";
        columnArray2[1] = "ST_CD";
        columnArray2[2] = BigDecimal.valueOf(5);
        columnArray2[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[IDX_4];
        columnArray3[0] = "Postal Code";
        columnArray3[1] = "POST_CD";
        columnArray3[2] = BigDecimal.valueOf(10);
        columnArray3[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[IDX_4];
        columnArray4[0] = "County";
        columnArray4[1] = "CNTY_NM";
        columnArray4[2] = BigDecimal.valueOf(30);
        columnArray4[3] = ZYPConstant.FLG_ON_Y;
        columnList.add(columnArray4);

        params[IDX_4] = columnList;

        List<Object[]> sortConditionList = new ArrayList<Object[]>();
        Object[] sortConditionArray1 = new Object[IDX_2];
        sortConditionArray1[IDX_0] = "CTY_ADDR";
        sortConditionArray1[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray1);

        Object[] sortConditionArray2 = new Object[IDX_2];
        sortConditionArray2[IDX_0] = "ST_CD";
        sortConditionArray2[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray2);

        Object[] sortConditionArray3 = new Object[IDX_2];
        sortConditionArray3[IDX_0] = "POST_CD";
        sortConditionArray3[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray3);

        Object[] sortConditionArray4 = new Object[IDX_2];
        sortConditionArray4[IDX_0] = "CNTY_NM";
        sortConditionArray4[IDX_1] = "ASC";
        sortConditionList.add(sortConditionArray4);

        params[IDX_5] = sortConditionList;
        params[IDX_6] = scrnMsg.P;

        return params;
    }
}

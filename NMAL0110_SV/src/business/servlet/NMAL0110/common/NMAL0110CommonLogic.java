package business.servlet.NMAL0110.common;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import parts.common.EZDBDateItem;
import parts.common.EZDBMsg;
import parts.common.EZDCommonConst;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL0110.NMAL0110BMsg;
import business.servlet.NMAL0110.NMAL0110_PBMsgArray;
import business.servlet.NMAL0110.constant.NMAL0110Constant;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.COA_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TERM_COND_OPT_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create          
 * 07/03/2015   Fujitsu         C.Tanaka        Update
 * 10/05/2015   SRAA            K.Aratani       Update          
 * 02/09/2016   SRAA            Y.Chen          Update          QC#3579
 * 03/16/2016   SRAA            K.Aratani       Update          QC#5580
 * 05/17/2016   SRAA            K.Aratani       Update          QC#4203
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 11/10/2016   SRAA            K.Aratani       Update          QC#15918
 * 06/08/2017   Hitachi         K.Kasai         Update          QC#18668
 * 08/29/2017   Fujitsu         K.Ishizuka      Update          QC#17149(Sol#259)
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 08/07/2018   Fujitsu         N.Sugiura       Update          QC#25385
 * 08/09/2018   Fujitsu         S.Ohki          Update          QC#27222
 * 08/13/2018   Fujitsu         Y.Matsui        Update          QC#27222
 * 08/29/2018   CSAI            T.Gotoda        Update          QC#27151
 * 09/12/2018   Fujitsu         T.Noguchi       Update          QC#27151-1
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 * 2020/04/07   Fujitsu         M.Ohno          Update          QC#56017
 * 2020/04/13   CITS            K.Ogino         Update          QC#56494
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NMAL0110CommonLogic implements NMAL0110Constant {

    public static Object[] toArray_popup(NMAL0110_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm;
        }
        return param;
    }
    public static Object[] toArray_popupForZYPL0310(NMAL0110_PBMsgArray p, int size) {
        Object[] param = new Object[size];
        for (int i = 0; i < size; i++) {
            param[i] = p.no(i).xxPopPrm.getValue();
        }
        return param;
    }
    
    
    public static Object[] setParamForNMAL6040(NMAL0110BMsg scrnMsg) {
    	
        ZYPTableUtil.clear(scrnMsg.P);
        scrnMsg.P.no(0).xxPopPrm.clear();
        scrnMsg.P.no(2).xxPopPrm.clear();
        scrnMsg.P.no(4).xxPopPrm.clear();
        scrnMsg.P.no(6).xxPopPrm.clear();
        scrnMsg.P.no(8).xxPopPrm.clear();
        scrnMsg.P.no(10).xxPopPrm.clear();
        scrnMsg.P.no(12).xxPopPrm.clear();
        Object[] params = toArray_popup(scrnMsg.P, 28);
        return params;
        
    }
    
    public static void changeActivation(S21CommonHandler handler, S21UserProfileService profile, NMAL0110BMsg scrnMsg) {

    	// Always Disabled Fields
        scrnMsg.mdseItemMnfNm_H1.setInputProtected(true); // 2019/10/18 QC#53815 Add
        scrnMsg.mdseCratTmplNm_H1.setInputProtected(true);
        scrnMsg.coaProjDescTxt_H1.setInputProtected(true);
        scrnMsg.coaProdNm_H1.setInputProtected(true);
        scrnMsg.mktMdlNm_H1.setInputProtected(true);
        scrnMsg.mktMdlSegNm_H1.setInputProtected(true);
        scrnMsg.zerothProdCtrlNm_H1.setInputProtected(true);
        scrnMsg.firstProdCtrlNm_H1.setInputProtected(true);
        scrnMsg.scdProdCtrlNm_H1.setInputProtected(true);
        scrnMsg.thirdProdCtrlNm_H1.setInputProtected(true);
        scrnMsg.frthProdCtrlNm_H1.setInputProtected(true);
        scrnMsg.mdseCratTmplNm_H2.setInputProtected(true);
        scrnMsg.mdseCratTmplCratDt_H1.setInputProtected(true);
    	scrnMsg.mdseItemActvDt_H1.setInputProtected(true);

        scrnMsg.slsMatGrpDescTxt_01.setInputProtected(true);
        scrnMsg.slsMatGrpDescTxt_02.setInputProtected(true);
        scrnMsg.slsMatGrpDescTxt_03.setInputProtected(true);
        scrnMsg.dsCmsnGrpDescTxt_H1.setInputProtected(true);
        // 2020/04/07 QC#56017 Add Start
        if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_RP.getValue()) //
                || MODE_NEW.equals(scrnMsg.xxModeCd_H1.getValue())
                || ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxDplyCtrlFlg_RP.getValue())) {
            scrnMsg.xxChkBox_RP.setInputProtected(true);
        } else {
            scrnMsg.xxChkBox_RP.setInputProtected(false);
        }
        // 2020/04/07 QC#56017 Add End

        String user = profile.getContextUserInfo().getUserId();
    	handler.setButtonEnabled(CMN_BTN31[1], true); //Search
        // Header Read
        boolean authHeaderEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_HEADER);
    	// No Read or No Edit
        if (!authHeaderEdit) {
        	// Save as Tmpl Button
        	scrnMsg.xxRsltFlg_H3.setValue(FLG_ON_Y);
        	// Header(Left)
        	scrnMsg.xxLinkProt_01.setInputProtected(true);
        	scrnMsg.mdseCd_H1.setInputProtected(true);
        	scrnMsg.mdseDescShortTxt_H1.setInputProtected(true);
        	// scrnMsg.mdseItemMnfCd_H1.setInputProtected(true); // 2019/10/18 QC#53815 Del
        	scrnMsg.mnfItemCd_H1.setInputProtected(true);
        	scrnMsg.upcCd_H1.setInputProtected(true);
        	scrnMsg.mdseItemStsCd_H1.setInputProtected(true);
        	scrnMsg.mdseDescLongTxt_H1.setInputProtected(true);
        	scrnMsg.mdseItemActvDt_H1.setInputProtected(true);

        	// Header(Center)
        	scrnMsg.mdseRgtnTpCd_H1.setInputProtected(true);
        	scrnMsg.mdseItemTpCd_H1.setInputProtected(true);
        	scrnMsg.mdseItemClsTpCd_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_28.setInputProtected(true);
        	scrnMsg.coaMdseTpCd_H1.setInputProtected(true);
        	scrnMsg.coaProjDescTxt_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_07.setInputProtected(true);
        	scrnMsg.coaProdCd_H1.setInputProtected(true);
        	scrnMsg.coaProdNm_H1.setInputProtected(true);
        	scrnMsg.prchGrpCd_H1.setInputProtected(true);
        	scrnMsg.thirdPtyItemFlg_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_19.setInputProtected(true);
        	scrnMsg.mktMdlCd_H1.setInputProtected(true);
        	scrnMsg.mktMdlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_20.setInputProtected(true);
        	scrnMsg.mktMdlSegCd_H1.setInputProtected(true);
        	scrnMsg.mktMdlSegNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_18.setInputProtected(true);
        	scrnMsg.mdseCratTmplNm_H1.setInputProtected(true);
        	//scrnMsg.mdsePrcGrpCd_H1.setInputProtected(true);
        	// 2020/04/02 QC#56017 Add Start
        	scrnMsg.xxChkBox_RP.setInputProtected(true);
        	// 2020/04/02 QC#56017 Add End

        	// Header(Right)
        	scrnMsg.xxLinkProt_02.setInputProtected(true);
        	scrnMsg.zerothProdCtrlCd_H1.setInputProtected(true);
        	scrnMsg.zerothProdCtrlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_03.setInputProtected(true);
        	scrnMsg.firstProdCtrlCd_H1.setInputProtected(true);
        	scrnMsg.firstProdCtrlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_04.setInputProtected(true);
        	scrnMsg.scdProdCtrlCd_H1.setInputProtected(true);
        	scrnMsg.scdProdCtrlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_05.setInputProtected(true);
        	scrnMsg.thirdProdCtrlCd_H1.setInputProtected(true);
        	scrnMsg.thirdProdCtrlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_06.setInputProtected(true);
        	scrnMsg.frthProdCtrlCd_H1.setInputProtected(true);
        	scrnMsg.frthProdCtrlNm_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_22.setInputProtected(true);
        	scrnMsg.slsMatGrpCd_01.setInputProtected(true);
        	scrnMsg.slsMatGrpDescTxt_01.setInputProtected(true);
        	scrnMsg.xxLinkProt_23.setInputProtected(true);
        	scrnMsg.slsMatGrpCd_02.setInputProtected(true);
        	scrnMsg.slsMatGrpDescTxt_02.setInputProtected(true);
        	scrnMsg.xxLinkProt_24.setInputProtected(true);
        	scrnMsg.slsMatGrpCd_03.setInputProtected(true);
        	scrnMsg.slsMatGrpDescTxt_03.setInputProtected(true);
        	scrnMsg.xxLinkProt_25.setInputProtected(true);
        	scrnMsg.dsCmsnGrpCd_H1.setInputProtected(true);
        	scrnMsg.dsCmsnGrpDescTxt_H1.setInputProtected(true);
        	//scrnMsg.mdseCratTmplNm_H2.setInputProtected(true);
        	//scrnMsg.mdseCratTmplCratDt_H1.setInputProtected(true);
            
        	handler.setButtonEnabled(CMN_BTN31[1], false); //Search
        	
        } else {
        	// Save as Tmpl Button
        	scrnMsg.xxRsltFlg_H3.setValue(FLG_OFF_N);
            // In case of Modification
            if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            	scrnMsg.mdseRgtnTpCd_H1.setInputProtected(true);
            	scrnMsg.xxLinkProt_01.setInputProtected(true);
            	handler.setButtonEnabled(CMN_BTN31[1], false); //Search
            	handler.setButtonEnabled(CMN_BTN32[1], true); //Attach
                scrnMsg.mdseCd_H1.setInputProtected(true);
                scrnMsg.mdseCd_H1.setInputProtected(true);
                scrnMsg.xxLinkProt_18.setInputProtected(true);
            } else {
            	scrnMsg.mdseRgtnTpCd_H1.setInputProtected(false);
            	scrnMsg.xxLinkProt_01.setInputProtected(false);
            	handler.setButtonEnabled(CMN_BTN31[1], true); //Search
            	handler.setButtonEnabled(CMN_BTN32[1], false); //Attach
                scrnMsg.mdseCd_H1.setInputProtected(false);
                scrnMsg.mdseCd_H1.setInputProtected(false);
                scrnMsg.xxLinkProt_18.setInputProtected(false);
            }
        }
        
        // QC#6748
        if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            scrnMsg.xxLinkProt_31.setInputProtected(true);
            scrnMsg.mdseCd_C1.setInputProtected(true);
            handler.setButtonEnabled(CMN_BTN37[1], false); // Copy Item
        	if (MDSE_ITEM_TP.SET.equals(scrnMsg.mdseItemTpCd_H1.getValue()) 
        			|| MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
	            handler.setButtonEnabled(CMN_BTN36[1], true); // Bill of Material
        	} else {
                handler.setButtonEnabled(CMN_BTN36[1], false); // Bill of Material
        	}
        } else {
            scrnMsg.xxLinkProt_31.setInputProtected(false);
            scrnMsg.mdseCd_C1.setInputProtected(false);
            handler.setButtonEnabled(CMN_BTN37[1], true); // Copy Item
            handler.setButtonEnabled(CMN_BTN36[1], false); // Bill of Material
        }

        // QC#3579
        if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            handler.setButtonEnabled(CMN_BTN35[1], true); // History
        } else {
            handler.setButtonEnabled(CMN_BTN35[1], false); // History
        }

        boolean authGeneralEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_GENERAL);
        boolean authInventoryEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_INVENTORY);
        boolean authAccountingEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_ACCOUNTING);
        boolean authFieldServiceEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_FIELD_SERVICE);
        boolean authSupdEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_SUPD);
        boolean authCustRefEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_CUST_REF);
        // 2018/08/13 QC#27222 Add Start
        boolean authTaxingEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_TAXING);
        // 2018/08/13 QC#27222 Add End

        //does not have any Edit roles.
        if (!authHeaderEdit && !authGeneralEdit && !authInventoryEdit && !authAccountingEdit && !authFieldServiceEdit && !authSupdEdit && !authCustRefEdit && !authTaxingEdit) { // 2018/08/13 QC#27222 Mod
            handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);  //Save
            handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 0, null);  //Submit
            handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);  //Apply
            handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);  //Approve
            handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);  //Reject
            handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);  //Down load
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);  //Delete
            handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 0, null);  //Clear
            handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 0, null);  //Reset
            handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);  //Return
        } else {
            handler.setButtonProperties(CMN_BTN1[0], CMN_BTN1[1], CMN_BTN1[2], 0, null);  //Save
            handler.setButtonProperties(CMN_BTN2[0], CMN_BTN2[1], CMN_BTN2[2], 1, null);  //Submit
            handler.setButtonProperties(CMN_BTN3[0], CMN_BTN3[1], CMN_BTN3[2], 0, null);  //Apply
            handler.setButtonProperties(CMN_BTN4[0], CMN_BTN4[1], CMN_BTN4[2], 0, null);  //Approve
            handler.setButtonProperties(CMN_BTN5[0], CMN_BTN5[1], CMN_BTN5[2], 0, null);  //Reject
            handler.setButtonProperties(CMN_BTN6[0], CMN_BTN6[1], CMN_BTN6[2], 0, null);  //Down load
            handler.setButtonProperties(CMN_BTN7[0], CMN_BTN7[1], CMN_BTN7[2], 0, null);  //Delete
            handler.setButtonProperties(CMN_BTN8[0], CMN_BTN8[1], CMN_BTN8[2], 1, null);  //Clear
            handler.setButtonProperties(CMN_BTN9[0], CMN_BTN9[1], CMN_BTN9[2], 1, null);  //Reset
            handler.setButtonProperties(CMN_BTN10[0], CMN_BTN10[1], CMN_BTN10[2], 1, null);  //Return
        }
        
        
    }
    
    public static void changeActivation_Detail(S21CommonHandler handler, S21UserProfileService profile, NMAL0110BMsg scrnMsg) {
        
    	// Always Disabled Fields
        scrnMsg.hazClsNm_H1.setInputProtected(true);
        scrnMsg.hazPrpShipNm_H1.setInputProtected(true);
        scrnMsg.hazIdNum_H1.setInputProtected(true);
        scrnMsg.mdseCstUpdDt_H1.setInputProtected(true);
        scrnMsg.assetRecovCostAmtUpdDt_H1.setInputProtected(true);
        if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
	        scrnMsg.thisMthTotStdCostAmt_H1.setInputProtected(true);
	        scrnMsg.assetRecovCostAmt_H1.setInputProtected(true);
        } else {
	        scrnMsg.thisMthTotStdCostAmt_H1.setInputProtected(false);
	        scrnMsg.assetRecovCostAmt_H1.setInputProtected(false);
        }
        scrnMsg.lastMthTotStdCostAmt_H1.setInputProtected(true);
        scrnMsg.prevAssetRecovCostAmt_H1.setInputProtected(true);
        scrnMsg.origFobAmtUpdDt_H1.setInputProtected(true);
        scrnMsg.ctryNm_MI.setInputProtected(true);
        scrnMsg.ctryNm_AI.setInputProtected(true);
        scrnMsg.locNm_DW.setInputProtected(true);
        scrnMsg.locNm_SW.setInputProtected(true);
        scrnMsg.coaAcctNm_RV.setInputProtected(true);
        scrnMsg.coaAcctNm_CG.setInputProtected(true);
        scrnMsg.coaAcctNm_EX.setInputProtected(true);
        scrnMsg.coaAcctNm_AC.setInputProtected(true);
        scrnMsg.mdseDescShortTxt_IW.setInputProtected(true);
    	scrnMsg.swProdCatgDescTxt_H1.setInputProtected(true);
    	scrnMsg.frtClsDescTxt_H1.setInputProtected(true);
    	scrnMsg.nmfcItemNum_H1.setInputProtected(true);
    	scrnMsg.nmfcItemSubNum_H1.setInputProtected(true);
    	scrnMsg.nmfcClsNum_H1.setInputProtected(true);
    	scrnMsg.trfDescTxt_H1.setInputProtected(true);
        
        scrnMsg.pkgUomDescTxt_BA.setInputProtected(true);
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_HM) || FLG_OFF_N.equals(scrnMsg.xxChkBox_HM.getValue())) {
        	scrnMsg.hazMatCd_H1.setInputProtected(true);
            scrnMsg.hazMatCd_H1.clear();
            scrnMsg.hazClsNm_H1.clear();
            scrnMsg.hazPrpShipNm_H1.clear();
            scrnMsg.hazIdNum_H1.clear();
        } else {
        	scrnMsg.hazMatCd_H1.setInputProtected(false);
        }
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RR) || FLG_OFF_N.equals(scrnMsg.xxChkBox_RR.getValue())) {
        	//QC#4203
        	scrnMsg.xxLinkProt_30.setInputProtected(true);
        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
        	scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
        	scrnMsg.locNm_V1.setInputProtected(true);
        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);
        	scrnMsg.locNm_P1.setInputProtected(true);
        	scrnMsg.rtrnWhCd_H1.setInputProtected(true);
        	scrnMsg.locNm_W1.setInputProtected(true);
        	scrnMsg.rtrnCtrlTpCd_H1.clear();
        	scrnMsg.rtrnCtrlTpNm_H1.clear();
        	scrnMsg.rtrnDsplTpCd_H1.clear();
        	scrnMsg.rtrnToVndCd_H1.clear();
        	scrnMsg.locNm_V1.clear();
        	scrnMsg.rtrnToPrntVndCd_H1.clear();
        	scrnMsg.locNm_P1.clear();
        	scrnMsg.rtrnWhCd_H1.clear();
        	scrnMsg.locNm_W1.clear();
        } else {
        	//QC#4203
        	scrnMsg.xxLinkProt_30.setInputProtected(false);
        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(false);
        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(false);
        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(false);
        	scrnMsg.rtrnToVndCd_H1.setInputProtected(false);
        	scrnMsg.locNm_V1.setInputProtected(false);
        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(false);
        	scrnMsg.locNm_P1.setInputProtected(false);
        	scrnMsg.rtrnWhCd_H1.setInputProtected(false);
        	scrnMsg.locNm_W1.setInputProtected(false);
        
        }
        
        // General Tab Authorization
        String user = profile.getContextUserInfo().getUserId();
        boolean authGeneralRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_GENERAL);
        boolean authGeneralEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_GENERAL);
        // General Tab No Read
        if (authGeneralRead || authGeneralEdit) {
        	scrnMsg.xxTabProt_HG.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HG.setValue(FLG_OFF_N);
        }
        // General Tab No Edit
        if (!authGeneralEdit) {
        	//Dimensions(Each)
        	scrnMsg.inPoundWt_EA.setInputProtected(true);
        	scrnMsg.inPoundWt_UN.setInputProtected(true);
        	scrnMsg.inInchLg_EA.setInputProtected(true);
        	scrnMsg.inInchLg_UN.setInputProtected(true);
        	scrnMsg.inInchWdt_EA.setInputProtected(true);
        	scrnMsg.inInchWdt_UN.setInputProtected(true);
        	scrnMsg.inInchHgt_EA.setInputProtected(true);
        	scrnMsg.inInchHgt_UN.setInputProtected(true);
        	
        	//Units of Measure
        	scrnMsg.pkgUomClsCd_H1.setInputProtected(true);
        	scrnMsg.pkgUomCd_H1.setInputProtected(true);
        	scrnMsg.xxRadioBtn_K1.setInputProtected(true);
            if (scrnMsg.K.getValidCount() != 0) {
	            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
	            	scrnMsg.K.no(i).xxChkBox_K1.setInputProtected(true);
	            	scrnMsg.K.no(i).pkgUomDescTxt_K1.setInputProtected(true);
	            	scrnMsg.K.no(i).inEachQty_K1.setInputProtected(true);
	            }
            }
        	handler.setButtonEnabled(CMN_BTN29[1], false); //Add
        	handler.setButtonEnabled(CMN_BTN30[1], false); //Delete
        
        	//Attributes(Item Type Specific)
        	//Machine
        	scrnMsg.xxChkBox_ME.setInputProtected(true);
        	scrnMsg.backOrdImpctTpCd_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_RM.setInputProtected(true);
        	scrnMsg.sowReqFlg_H1.setInputProtected(true);
        	scrnMsg.svcChrgItemTpCd_H1.setInputProtected(true);
        	//Accessory
        	scrnMsg.backOrdImpctTpCd_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_RM.setInputProtected(true);
        	scrnMsg.sowReqFlg_H1.setInputProtected(true);
        	//Supply
        	scrnMsg.imgSplyOemMnfCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyOemCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyTpCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyColorTpCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyYieldCnt_H1.setInputProtected(true);
        	scrnMsg.imgSplyYieldUomCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyYieldTpCd_H1.setInputProtected(true);
        	scrnMsg.imgSplyPvtLbTpCd_H1.setInputProtected(true);
        	scrnMsg.easyPackTpCd_H1.setInputProtected(true);
        	scrnMsg.areaOfPaperNum_H1.setInputProtected(true);
        	//Parts
        	scrnMsg.prtItemTpCd_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_PD.setInputProtected(true);
        	scrnMsg.prtYieldOtptQty_H1.setInputProtected(true);
        	scrnMsg.prtYieldDaysAot_H1.setInputProtected(true);
        	scrnMsg.prtSrvyReqFlg_H1.setInputProtected(true);
        	//Maintenance
        	scrnMsg.svcCovBaseTpCd_H1.setInputProtected(true);
        	scrnMsg.svcCovTmplTpCd_H1.setInputProtected(true);
        	scrnMsg.svcAllocTpCd_H1.setInputProtected(true);
        	// 2017/09/25 QC#18534(L3#445) ADD Start
        	scrnMsg.svcPgmTpCd_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_OM.setInputProtected(true);
        	// 2017/09/25 QC#18534(L3#445) ADD End
        	//Maintenance Charge
        	scrnMsg.svcAllocTpCd_H1.setInputProtected(true);
        	scrnMsg.svcChrgItemTpCd_H1.setInputProtected(true);
        	for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
        		scrnMsg.N.no(i).xxChkBox_N1.setInputProtected(true);
        		scrnMsg.N.no(i).termCondOptTpCd_N1.setInputProtected(true);
        		scrnMsg.N.no(i).termCondOptValTxt_N1.setInputProtected(true);
        	}
        	handler.setButtonEnabled(CMN_BTN33[1], false); //Add
        	handler.setButtonEnabled(CMN_BTN34[1], false); //Del
        	
        	//Maintenance Overages
        	scrnMsg.svcAllocTpCd_H1.setInputProtected(true);
        	//Software, Software License
        	scrnMsg.swCatgCd_H1.setInputProtected(true);
        	scrnMsg.swTpCd_H1.setInputProtected(true);
        	scrnMsg.swVrsnTxt_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_26.setInputProtected(true);
        	scrnMsg.swProdCatgCd_H1.setInputProtected(true);
        	scrnMsg.swLicCtrlTpCd_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_EC.setInputProtected(true);
        	scrnMsg.swLicSeatFromQty_H1.setInputProtected(true);
        	scrnMsg.swLicSeatToQty_H1.setInputProtected(true);
        	scrnMsg.swLicSeatFixQty_H1.setInputProtected(true);
        	scrnMsg.swLicCtrlTpCd_H1.setInputProtected(true);
        	scrnMsg.swMaintCtrlTpCd_H1.setInputProtected(true);
        	scrnMsg.asrnPntPerLicQty_H1.setInputProtected(true);
        	scrnMsg.bdlMaintMdseCd_H1.setInputProtected(true);
        	scrnMsg.maintPopAvalFlg_H1.setInputProtected(true);
        	scrnMsg.extMaintPopAvalFlg_H1.setInputProtected(true);
        	//Software Maintenance
        	scrnMsg.swMaintTpCd_H1.setInputProtected(true);
        	scrnMsg.maintItemTermMthNum_H1.setInputProtected(true);
        	scrnMsg.asrnPntMinQty_H1.setInputProtected(true);
        	scrnMsg.asrnPntMaxQty_H1.setInputProtected(true);
        	scrnMsg.asrnPntFixPerOrdQty_H1.setInputProtected(true);
        	//Intangible
        	//scrnMsg.mdsePrcGrpCd_H1.setInputProtected(true);
        	scrnMsg.dsIntgMdseTpCd_H1.setInputProtected(true);
        	//Set, Kit, Professional Service

        	//Safety
        	scrnMsg.xxChkBox_HM.setInputProtected(true);
        	scrnMsg.hazMatCd_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_08.setInputProtected(true);
        	scrnMsg.madeInCtryCd_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_09.setInputProtected(true);
        	scrnMsg.asmInCtryCd_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_27.setInputProtected(true);
        	scrnMsg.frtClsCd_H1.setInputProtected(true);
        	scrnMsg.trfCd_H1.setInputProtected(true);
        	
        } else {
            // S21_NA Add Start QC#17149(Sol#259)
            if (EASY_PACK_TP.EASYPAC_TONER.equals(scrnMsg.easyPackTpCd_H1.getValue())) {
                scrnMsg.areaOfPaperNum_H1.setInputProtected(true);
            } else {
                scrnMsg.areaOfPaperNum_H1.setInputProtected(false);
            }
            // S21_NA Add End QC#17149(Sol#259)
            if (scrnMsg.K != null && scrnMsg.K.length() > 0 && scrnMsg.K.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
                    scrnMsg.K.no(i).pkgUomDescTxt_K1.setInputProtected(true);
                    if (ZYPCommonFunc.hasValue(scrnMsg.K.no(i).basePkgUomCd_K1)
                    		&& scrnMsg.K.no(i).basePkgUomCd_K1.getValue().equals(scrnMsg.K.no(i).pkgUomCd_K1.getValue())) {
                    	scrnMsg.K.no(i).inEachQty_K1.setInputProtected(true);
                    }
                }
            }
            // 2017/09/25 QC#18534(L3#445) Add Start
            if (ZYPConstant.FLG_OFF_N.equals(scrnMsg.ovrdMnfWtyFlg_H1.getValue()) || scrnMsg.ovrdMnfWtyFlg_H1.getValue().isEmpty()) {
                scrnMsg.xxChkBox_OM.setInputProtected(true);
                scrnMsg.xxChkBox_OM.clear();
            } else {
                scrnMsg.xxChkBox_OM.setInputProtected(false);
            }
            // 2017/09/25 QC#18534(L3#445) Add End

            // 2018/09/12 QC#27151-1 Mod Start
            // 2018/08/29 QC#27151 Add Start
            //if (MDSE_ITEM_TP.PARTS.equals(scrnMsg.mdseItemTpCd_H1.getValue())){
            //
            //    if (COA_MDSE_TP.TOOLS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            //        scrnMsg.prtItemTpCd_H1.setValue(PRT_ITEM_TP.TOOL);
            //        scrnMsg.prtItemTpCd_H1.setInputProtected(true);
            //    } else {
            //        scrnMsg.prtItemTpCd_H1.setInputProtected(false);
            //    }
            //} else {
            //
            //    if (COA_MDSE_TP.TOOLS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            //        scrnMsg.prtItemTpCd_H1.setValue(PRT_ITEM_TP.TOOL);
            //    } else {
            //        scrnMsg.prtItemTpCd_H1.clear();
            //    }
            //
            //    scrnMsg.prtItemTpCd_H1.setInputProtected(false);
            //}
            // 2018/08/29 QC#27151 Add End
            if (!(MDSE_ITEM_TP.PARTS.equals(scrnMsg.mdseItemTpCd_H1.getValue()) ||
                  MDSE_ITEM_TP.PART_CONSUMABLES.equals(scrnMsg.mdseItemTpCd_H1.getValue()) ||
                 (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue()) && COA_MDSE_TP.PARTS.equals(scrnMsg.coaMdseTpCd_H1.getValue())))){
                scrnMsg.prtItemTpCd_H1.clear();
            }

            if (COA_MDSE_TP.TOOLS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
                scrnMsg.prtItemTpCd_H1.setValue(PRT_ITEM_TP.TOOL);
                scrnMsg.prtItemTpCd_H1.setInputProtected(true);
            } else {
                scrnMsg.prtItemTpCd_H1.setInputProtected(false);
            }
            // 2018/09/12 QC#27151-1 Mod End
        }
        //QC#15918
        //scrnMsg.inPoundWt_EA.setAppFracDigit(4);
        //scrnMsg.inPoundWt_UN.setAppFracDigit(4);
        //scrnMsg.inInchLg_EA.setAppFracDigit(4);
        //scrnMsg.inInchLg_UN.setAppFracDigit(4);
        //scrnMsg.inInchWdt_EA.setAppFracDigit(4);
        //scrnMsg.inInchWdt_UN.setAppFracDigit(4);
        //scrnMsg.inInchHgt_EA.setAppFracDigit(4);
        //scrnMsg.inInchHgt_UN.setAppFracDigit(4);
        
        // Inventory Tab Authorization
        boolean authInventoryRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_INVENTORY);
        boolean authInventoryEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_INVENTORY);
        // Inventory Tab No Read
        if (authInventoryRead || authInventoryEdit) {
        	scrnMsg.xxTabProt_HI.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HI.setValue(FLG_OFF_N);
        }
        // Inventory Tab No Edit
        if (!authInventoryEdit) {
        	// Inventory Attributes
        	scrnMsg.xxChkBox_IT.setInputProtected(true);
        	// 2020/04/13 QC#56494 Add Start
        	scrnMsg.xxChkBox_II.setInputProtected(true);
        	// 2020/04/13 QC#56494 Add End
        	scrnMsg.xxChkBox_RA.setInputProtected(true);
        	scrnMsg.xxChkBox_RI.setInputProtected(true);
        	scrnMsg.xxLinkProt_10.setInputProtected(true);
        	scrnMsg.defSrcWhCd_H1.setInputProtected(true);
        	scrnMsg.locNm_DW.setInputProtected(true);
        	scrnMsg.xxLinkProt_11.setInputProtected(true);
        	scrnMsg.defSrcSubWhCd_H1.setInputProtected(true);
        	scrnMsg.locNm_SW.setInputProtected(true);
        	scrnMsg.defSrcProcrTpCd_H1.setInputProtected(true);
        	scrnMsg.invtyHardAllocTpCd_H1.setInputProtected(true);
        	// Parts Return Control
        	//QC#4203
        	scrnMsg.xxChkBox_RR.setInputProtected(true);
        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
        	scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
        	scrnMsg.locNm_V1.setInputProtected(true);
        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);
        	scrnMsg.locNm_P1.setInputProtected(true);
        	scrnMsg.rtrnWhCd_H1.setInputProtected(true);
        	scrnMsg.locNm_W1.setInputProtected(true);
        	// Serial Control
        	scrnMsg.xxRadioBtn_SS.setInputProtected(true);
            if (scrnMsg.A.getValidCount() != 0) {
	            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
	            	scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
	            	scrnMsg.A.no(i).fromSerNum_A1.setInputProtected(true);
	            	scrnMsg.A.no(i).thruSerNum_A1.setInputProtected(true);
	            	//scrnMsg.A.no(i).lgSerNum_A1.setInputProtected(true);
	            }
            }
        	handler.setButtonEnabled(CMN_BTN36[1], false); //View Service Model
        	handler.setButtonEnabled(CMN_BTN13[1], false); //Insert Row
        	handler.setButtonEnabled(CMN_BTN14[1], false); //Delete Row
        } else {
        	
        	if (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())
        			|| MDSE_ITEM_TP.SET.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        		handler.setButtonEnabled(CMN_BTN36[1], true); //Bill of Material
        	} else {
        		handler.setButtonEnabled(CMN_BTN36[1], false); //Bill of Material
        	}
        	// 2018/08/07 QC#25385 Mod Start
//        	 if (!MDSE_ITEM_TP.PARTS.equals(scrnMsg.mdseItemTpCd_H1.getValue())
//        		 && !MDSE_ITEM_TP.PART_CONSUMABLES.equals(scrnMsg.mdseItemTpCd_H1.getValue())
//        		 && !(MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())
//        				 && COA_MDSE_TP.PARTS.equals(scrnMsg.coaMdseTpCd_H1.getValue()))) {
            List<String> partsRtrnCtrlMdseItemTpList = new ArrayList<String>();
            String partsRtrnCtrlMdseItemTpCsv = ZYPCodeDataUtil.getVarCharConstValue("PARTS_RTRN_CTRL_MDSE_ITEM_TP", profile.getGlobalCompanyCode());
            if (partsRtrnCtrlMdseItemTpCsv != null) {
                partsRtrnCtrlMdseItemTpList = Arrays.asList(partsRtrnCtrlMdseItemTpCsv.split(","));
            }
            if (!partsRtrnCtrlMdseItemTpList.contains(scrnMsg.mdseItemTpCd_H1.getValue())
                    && !COA_MDSE_TP.PARTS.equals(scrnMsg.coaMdseTpCd_H1.getValue())
                        && !COA_MDSE_TP.DRUM.equals(scrnMsg.coaMdseTpCd_H1.getValue())
                        && !COA_MDSE_TP.TOOLS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
        	// 2018/08/07 QC#25385 Mod End
	        	// Parts Return Control
	        	//QC#4203
	        	scrnMsg.xxChkBox_RR.clear();
	        	scrnMsg.rtrnCtrlTpCd_H1.clear();
	        	scrnMsg.rtrnCtrlTpNm_H1.clear();
	        	scrnMsg.rtrnDsplTpCd_H1.clear();
	        	scrnMsg.rtrnToVndCd_H1.clear();
	        	scrnMsg.locNm_V1.clear();
	        	scrnMsg.rtrnToPrntVndCd_H1.clear();
	        	scrnMsg.locNm_P1.clear();
	        	scrnMsg.rtrnWhCd_H1.clear();
	        	scrnMsg.locNm_W1.clear();
	        	scrnMsg.xxLinkProt_30.setInputProtected(true);
	        	scrnMsg.locNm_V1.setInputProtected(true);
	        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);
	        	scrnMsg.locNm_P1.setInputProtected(true);
	        	scrnMsg.rtrnWhCd_H1.setInputProtected(true);
	        	scrnMsg.locNm_W1.setInputProtected(true);
	        	scrnMsg.xxChkBox_RR.setInputProtected(true);
	        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
	        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
	        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
	        	scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
        	} else {
	        	scrnMsg.xxChkBox_RR.setInputProtected(false);
        		if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RR) && "Y".equals(scrnMsg.xxChkBox_RR.getValue())) {
                	scrnMsg.xxLinkProt_30.setInputProtected(false);
    	        	scrnMsg.locNm_V1.setInputProtected(true);
    	        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);
    	        	scrnMsg.locNm_P1.setInputProtected(true);
    	        	scrnMsg.rtrnWhCd_H1.setInputProtected(true);
    	        	scrnMsg.locNm_W1.setInputProtected(true);
    	        	//scrnMsg.xxChkBox_RR.setInputProtected(false);
    	        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
    	        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(false);
    	        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
    	        	scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
        		} else {
    	        	scrnMsg.xxChkBox_RR.clear();
    	        	scrnMsg.rtrnCtrlTpCd_H1.clear();
    	        	scrnMsg.rtrnCtrlTpNm_H1.clear();
    	        	scrnMsg.rtrnDsplTpCd_H1.clear();
    	        	scrnMsg.rtrnToVndCd_H1.clear();
    	        	scrnMsg.locNm_V1.clear();
    	        	scrnMsg.rtrnToPrntVndCd_H1.clear();
    	        	scrnMsg.locNm_P1.clear();
    	        	scrnMsg.rtrnWhCd_H1.clear();
    	        	scrnMsg.locNm_W1.clear();
                	scrnMsg.xxLinkProt_30.setInputProtected(true);
    	        	scrnMsg.locNm_V1.setInputProtected(true);
    	        	scrnMsg.rtrnToPrntVndCd_H1.setInputProtected(true);
    	        	scrnMsg.locNm_P1.setInputProtected(true);
    	        	scrnMsg.rtrnWhCd_H1.setInputProtected(true);
    	        	scrnMsg.locNm_W1.setInputProtected(true);
    	        	//scrnMsg.xxChkBox_RR.setInputProtected(true);
    	        	scrnMsg.rtrnCtrlTpCd_H1.setInputProtected(true);
    	        	scrnMsg.rtrnDsplTpCd_H1.setInputProtected(true);
    	        	scrnMsg.rtrnCtrlTpNm_H1.setInputProtected(true);
    	        	scrnMsg.rtrnToVndCd_H1.setInputProtected(true);
        		}
        	}
        }
        
        boolean authAccountingRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_ACCOUNTING);
        boolean authAccountingEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_ACCOUNTING);
        if (authAccountingRead || authAccountingEdit) {
        	scrnMsg.xxTabProt_HA.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HA.setValue(FLG_OFF_N);
        }
        if (!authAccountingEdit) {
        	//Costing
        	scrnMsg.thisMthTotStdCostAmt_H1.setInputProtected(true);
        	scrnMsg.mdseCstUpdDt_H1.setInputProtected(true);
        	scrnMsg.lastMthTotStdCostAmt_H1.setInputProtected(true);
        	scrnMsg.origFobAmt_H1.setInputProtected(true);
        	scrnMsg.origFobAmtUpdDt_H1.setInputProtected(true);
        	scrnMsg.assetRecovCostAmt_H1.setInputProtected(true);
        	scrnMsg.assetRecovCostAmtUpdDt_H1.setInputProtected(true);
        	scrnMsg.prevAssetRecovCostAmt_H1.setInputProtected(true);
        	handler.setButtonEnabled(CMN_BTN15[1], false); //ViewCostHistory
        	//Account Numbers
        	scrnMsg.xxLinkProt_12.setInputProtected(true);
        	scrnMsg.revCoaAcctCd_H1.setInputProtected(true);
        	scrnMsg.coaAcctNm_RV.setInputProtected(true);
        	scrnMsg.xxLinkProt_13.setInputProtected(true);
        	scrnMsg.cogsCoaAcctCd_H1.setInputProtected(true);
        	scrnMsg.coaAcctNm_CG.setInputProtected(true);
        	scrnMsg.xxLinkProt_14.setInputProtected(true);
        	scrnMsg.expCoaAcctCd_H1.setInputProtected(true);
        	scrnMsg.coaAcctNm_EX.setInputProtected(true);
        	scrnMsg.xxLinkProt_15.setInputProtected(true);
        	scrnMsg.coaAcctNm_AC.setInputProtected(true);
        	scrnMsg.xxLinkProt_20.setInputProtected(true);
        	scrnMsg.lineBizTpCd_H1.setInputProtected(true);
        	//Accounting Rules
        	scrnMsg.xxChkBox_IP.setInputProtected(true);
        	scrnMsg.dfrdAcctgRuleCd_H1.setInputProtected(true);
        	scrnMsg.dfrdInvRuleCd_H1.setInputProtected(true);
        	// 2018/08/08 QC#27222 Del Start
//        	scrnMsg.taxExemTpCd_H1.setInputProtected(true);
        	// 2018/08/08 QC#27222 Del End
        }
        
        scrnMsg.thisMthTotStdCostAmt_H1.setAppFracDigit(2);
        scrnMsg.lastMthTotStdCostAmt_H1.setAppFracDigit(2);
        scrnMsg.origFobAmt_H1.setAppFracDigit(2);
        scrnMsg.assetRecovCostAmt_H1.setAppFracDigit(2);
        scrnMsg.prevAssetRecovCostAmt_H1.setAppFracDigit(2);
        
        boolean authFieldServiceRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_FIELD_SERVICE);
        boolean authFieldServiceEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_FIELD_SERVICE);
        if (authFieldServiceRead || authFieldServiceEdit) {
        	scrnMsg.xxTabProt_HF.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HF.setValue(FLG_OFF_N);
        }
        if (!authFieldServiceEdit) {
        	scrnMsg.svcWtyTpCd_H1.setInputProtected(true);
        	scrnMsg.wtyDaysAot_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_MM.setInputProtected(true);
        	scrnMsg.xxChkBox_IB.setInputProtected(true);
        	scrnMsg.xxChkBox_SC.setInputProtected(true);
        	scrnMsg.xxChkBox_IR.setInputProtected(true);
        	scrnMsg.iwrMdlCd_H1.setInputProtected(true);
        	scrnMsg.xxLinkProt_17.setInputProtected(true);
        	scrnMsg.iwrMdseCd_H1.setInputProtected(true);
        	scrnMsg.mdseDescShortTxt_IW.setInputProtected(true);
        	scrnMsg.mdseItemBillTpCd_H1.setInputProtected(true);
        	// START 2023/09/05 K.Watanabe [QC#53408, ADD]
        	scrnMsg.svcIstlRuleNum_H1.setInputProtected(true);
        	scrnMsg.svcIstlCallGrpNum_H1.setInputProtected(true);
        	// END 2023/09/05 K.Watanabe [QC#53408, ADD]
        	// START 2023/12/12 K.Watanabe [QC#61300, ADD]
        	scrnMsg.svcDeinsRuleNum_H1.setInputProtected(true);
        	scrnMsg.svcDeinsCallGrpNum_H1.setInputProtected(true);
        	// END 2023/12/12 K.Watanabe [QC#61300, ADD]
        	
        	handler.setButtonEnabled(CMN_BTN16[1], false); //View Service Model
        	
        }
        
        boolean authOrdProcRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_ORDER_PROCESSING);
        boolean authOrdProcEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_ORDER_PROCESSING);
        if (authOrdProcRead || authOrdProcEdit) {
        	scrnMsg.xxTabProt_HO.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HO.setValue(FLG_OFF_N);
        }
        if (!authOrdProcEdit) {
        	scrnMsg.xxChkBox_CO.setInputProtected(true);
        	scrnMsg.cpoMinOrdQty_H1.setInputProtected(true);
        	scrnMsg.cpoMaxOrdQty_H1.setInputProtected(true);
        	scrnMsg.cpoIncrOrdQty_H1.setInputProtected(true);
        	scrnMsg.xxChkBox_IE.setInputProtected(true);
        	scrnMsg.xxChkBox_CF.setInputProtected(true);
        }
        
        boolean authSupdRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_SUPD);
        boolean authSupdEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_SUPD);
        if (authSupdRead || authSupdEdit) {
        	scrnMsg.xxTabProt_HS.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HS.setValue(FLG_OFF_N);
        }
        if (!authSupdEdit) {
        	if (scrnMsg.C != null && scrnMsg.C.length() > 0 && scrnMsg.C.getValidCount() > 0) {
	            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
	            	scrnMsg.C.no(i).xxChkBox_C1.setInputProtected(true);
	            	scrnMsg.C.no(i).supdToMdseCd_C1.setInputProtected(true);
	            	scrnMsg.C.no(i).supdCratDt_C1.setInputProtected(true);
	            	scrnMsg.C.no(i).mdseDescShortTxt_C1.setInputProtected(true);
	            }
            }
        	handler.setButtonEnabled(CMN_BTN19[1], false);
        	handler.setButtonEnabled(CMN_BTN20[1], false);
        	handler.setButtonEnabled(CMN_BTN26[1], false);
            if (scrnMsg.D.getValidCount() != 0) {
	            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
	            	scrnMsg.D.no(i).xxChkBox_D1.setInputProtected(true);
	            	scrnMsg.D.no(i).mdseItemRelnTpCd_D1.setInputProtected(true);
	            	scrnMsg.D.no(i).relnMdseCd_D1.setInputProtected(true);
	            	scrnMsg.D.no(i).mdseDescShortTxt_D1.setInputProtected(true);
	            }
            }
        	handler.setButtonEnabled(CMN_BTN21[1], false);
        	handler.setButtonEnabled(CMN_BTN22[1], false);
        	handler.setButtonEnabled(CMN_BTN27[1], false);
        } else {
            if (scrnMsg.C != null && scrnMsg.C.length() > 0 && scrnMsg.C.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                    scrnMsg.C.no(i).mdseDescShortTxt_C1.setInputProtected(true);
                }
            }
            if (scrnMsg.D != null && scrnMsg.D.length() > 0 && scrnMsg.D.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                    scrnMsg.D.no(i).mdseDescShortTxt_D1.setInputProtected(true);
                }
            }
        }
        boolean authCustRefRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_CUST_REF);
        boolean authCustRefEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_CUST_REF);
        if (authCustRefRead || authCustRefEdit) {
        	scrnMsg.xxTabProt_HC.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HC.setValue(FLG_OFF_N);
        }
        if (!authCustRefEdit) {
            if (scrnMsg.E.getValidCount() != 0) {
	            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
	            	scrnMsg.E.no(i).xxChkBox_E1.setInputProtected(true);
	            	scrnMsg.E.no(i).dsAcctNum_E1.setInputProtected(true);
                    scrnMsg.E.no(i).dsAcctNm_E1.setInputProtected(true);
	            	scrnMsg.E.no(i).custMdseCd_E1.setInputProtected(true);
	            	scrnMsg.E.no(i).custMdseNm_E1.setInputProtected(true);
	            	scrnMsg.E.no(i).effFromDt_E1.setInputProtected(true);
	            	scrnMsg.E.no(i).xxChkBox_EN.setInputProtected(true);
	            }
            }
        	handler.setButtonEnabled(CMN_BTN23[1], false);
        	handler.setButtonEnabled(CMN_BTN24[1], false);
        	handler.setButtonEnabled(CMN_BTN28[1], false);
        } else {
            if (scrnMsg.E != null && scrnMsg.E.length() > 0 && scrnMsg.E.getValidCount() > 0) {
                for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                    scrnMsg.E.no(i).dsAcctNm_E1.setInputProtected(true);
                }
            }
        }

        // 2018/08/08 QC#27222 Add Start
        boolean authTaxingRead = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_READ_TAXING);
        boolean authTaxingEdit = profile.isFunctionGranted(user, NMAL0110Constant.FUNC_EDIT_TAXING);
        if (authTaxingRead || authTaxingEdit) {
        	scrnMsg.xxTabProt_HT.setValue(FLG_ON_Y);
        } else {
        	scrnMsg.xxTabProt_HT.setValue(FLG_OFF_N);
        }
        if (!authTaxingEdit) {
        	scrnMsg.taxExemTpCd_H1.setInputProtected(true);
        }
        // 2018/08/08 QC#27222 Add End

        // default set
        if (!ZYPCommonFunc.hasValue(scrnMsg.xxDplyTab_H1)) {
        	if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HG.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_GENERAL);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HI.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_INVENTORY);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HA.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_ACCOUNTING);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HF.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_FIELD_SERVICE);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HO.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_ORDER_PROCESSING);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HS.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_SUPD);
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HC.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_CUST_REF);
            // 2018/08/08 QC#27222 Add Start
        	} else if (FLG_ON_Y.equals(scrnMsg.xxTabProt_HT.getValue())) {
        		scrnMsg.xxDplyTab_H1.setValue(TAB_TAXING);
            // 2018/08/08 QC#27222 Add End
        	}
        }
    }
    
    public static void changeTableColorController(NMAL0110BMsg scrnMsg) {
    	
    	if (TAB_GENERAL.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.setAlternateRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_INVENTORY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.setAlternateRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_ACCOUNTING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_FIELD_SERVICE.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_ORDER_PROCESSING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_SUPD.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.setAlternateRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.setAlternateRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
    	} else if (TAB_CUST_REF.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.setAlternateRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
        // 2018/08/08 QC#27222 Add Start
    	} else if (TAB_TAXING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            S21TableColorController tblColor = new S21TableColorController(NMAL0110Constant.SCREEN_ID, scrnMsg);
            tblColor.clearRowsBG(TABLE_UOM_K, scrnMsg.K);
            tblColor.clearRowsBG(TABLE_Serial_A, scrnMsg.A);
            tblColor.clearRowsBG(TABLE_Supd_C, scrnMsg.C);
            tblColor.clearRowsBG(TABLE_Relation_D, scrnMsg.D);
            tblColor.clearRowsBG(TABLE_CustRef_E, scrnMsg.E);
            tblColor.clearRowsBG(TABLE_TermCondOpt_N, scrnMsg.N);
        // 2018/08/08 QC#27222 Add End
    	}
    }
        
    public static void rowButtonControl(NMAL0110BMsg scrnMsg, S21CommonHandler handler, String table) {
        if (TABLE_Serial_A.equals(table)) {
            handler.setButtonEnabled("InsertRow_SER_NUM", false);
            handler.setButtonEnabled("DeleteRow_SER_NUM", false);
            int count = scrnMsg.A.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("DeleteRow_SER_NUM", true);
            }
            if (count < scrnMsg.A.length()) {
                handler.setButtonEnabled("InsertRow_SER_NUM", true);
            }
        } else if (TABLE_Supd_C.equals(table)) {
            handler.setButtonEnabled("InsertRow_SUPD", false);
            handler.setButtonEnabled("DeleteRow_SUPD", false);
            int count = scrnMsg.C.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("DeleteRow_SUPD", true);
            }
            if (count < scrnMsg.C.length()) {
                handler.setButtonEnabled("InsertRow_SUPD", true);
            }
        } else if (TABLE_Relation_D.equals(table)) {
            handler.setButtonEnabled("InsertRow_RELN_MDSE", false);
            handler.setButtonEnabled("DeleteRow_RELN_MDSE", false);
            int count = scrnMsg.D.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("DeleteRow_RELN_MDSE", true);
            }
            if (count < scrnMsg.D.length()) {
                handler.setButtonEnabled("InsertRow_RELN_MDSE", true);
            }
        } else if (TABLE_CustRef_E.equals(table)) {
            handler.setButtonEnabled("InsertRow_CUST_REF", false);
            handler.setButtonEnabled("DeleteRow_CUST_REF", false);
            int count = scrnMsg.E.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("DeleteRow_CUST_REF", true);
            }
            if (count < scrnMsg.E.length()) {
                handler.setButtonEnabled("InsertRow_CUST_REF", true);
            }
        } else if (TABLE_UOM_K.equals(table)) {
            handler.setButtonEnabled("Add_PkgType_SellingUOM", false);
            handler.setButtonEnabled("Del_PkgType_Selling", false);
            int count = scrnMsg.K.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("Add_PkgType_SellingUOM", true);
            }
            if (count < scrnMsg.K.length()) {
                handler.setButtonEnabled("Del_PkgType_Selling", true);
            }
        } else if (TABLE_TERM_COND_OPT_N.equals(table)) {
            handler.setButtonEnabled("Add_TermCondOpt", false);
            handler.setButtonEnabled("Del_TermCondOpt", false);
            int count = scrnMsg.N.getValidCount();
            if (count > 0) {
                handler.setButtonEnabled("Add_TermCondOpt", true);
            }
            if (count < scrnMsg.N.length()) {
                handler.setButtonEnabled("Del_TermCondOpt", true);
            }
        }
    }
    
    public static void checkInput_Submit(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
    
        // Input Check
    	// #############
    	// Header
    	// #############
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseDescShortTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemMnfCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mnfItemCd_H1);
        scrnMsg.addCheckItem(scrnMsg.upcCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemStsCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseDescLongTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemActvDt_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCratTmplNm_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseRgtnTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseItemClsTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.firstProdCtrlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.scdProdCtrlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.frthProdCtrlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_01);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_02);
        scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_03);
        scrnMsg.addCheckItem(scrnMsg.dsCmsnGrpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.prchGrpCd_H1);
        if (MDSE_ITEM_TP.MACHINE.equals(scrnMsg.mdseItemTpCd_H1.getValue())
        		|| (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())
        				&& COA_MDSE_TP.MACHINE.equals(scrnMsg.coaMdseTpCd_H1.getValue()))) {
        	if (!ZYPCommonFunc.hasValue(scrnMsg.mktMdlCd_H1)) {
        		scrnMsg.mktMdlCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Marketing Model"});
        	}
        	if (!ZYPCommonFunc.hasValue(scrnMsg.mktMdlSegCd_H1)) {
        		scrnMsg.mktMdlSegCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Marketing Segment"});
        	}
        }
        scrnMsg.addCheckItem(scrnMsg.mktMdlCd_H1);
        scrnMsg.addCheckItem(scrnMsg.mktMdlSegCd_H1);
        //scrnMsg.putErrorScreen();
        
    	// #############
    	// General
    	// #############
        if (TAB_GENERAL.equals(scrnMsg.xxDplyTab_H1.getValue())) {
            // Dimensions
	        scrnMsg.addCheckItem(scrnMsg.inPoundWt_EA);
	        scrnMsg.addCheckItem(scrnMsg.inPoundWt_UN);
	        scrnMsg.addCheckItem(scrnMsg.inInchLg_EA);
	        scrnMsg.addCheckItem(scrnMsg.inInchLg_UN);
	        scrnMsg.addCheckItem(scrnMsg.inInchWdt_EA);
	        scrnMsg.addCheckItem(scrnMsg.inInchWdt_UN);
	        scrnMsg.addCheckItem(scrnMsg.inInchHgt_EA);
	        scrnMsg.addCheckItem(scrnMsg.inInchHgt_UN);
	        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_K1);
	        // Units of Measure
	        scrnMsg.addCheckItem(scrnMsg.pkgUomCd_H1);
	        // table Check
	        checkInput_PkgUom(ctx, scrnMsg);
	        
	        if (ZYPCommonFunc.hasValue(scrnMsg.inPoundWt_EA) && BigDecimal.ZERO.compareTo(scrnMsg.inPoundWt_EA.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inPoundWt_EA.setErrorInfo(1, "ZZZM9026E", new String[]{"Weight(pounds)-Boxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchLg_EA) && BigDecimal.ZERO.compareTo(scrnMsg.inInchLg_EA.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchLg_EA.setErrorInfo(1, "ZZZM9026E", new String[]{"Length(inches)-Boxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchWdt_EA) && BigDecimal.ZERO.compareTo(scrnMsg.inInchWdt_EA.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchWdt_EA.setErrorInfo(1, "ZZZM9026E", new String[]{"Depth(inches)-Boxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchHgt_EA) && BigDecimal.ZERO.compareTo(scrnMsg.inInchHgt_EA.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchHgt_EA.setErrorInfo(1, "ZZZM9026E", new String[]{"Height(inches)-Boxed"});
	        }
	        
	        if (ZYPCommonFunc.hasValue(scrnMsg.inPoundWt_UN) && BigDecimal.ZERO.compareTo(scrnMsg.inPoundWt_UN.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inPoundWt_UN.setErrorInfo(1, "ZZZM9026E", new String[]{"Weight(pounds)-UnBoxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchLg_UN) && BigDecimal.ZERO.compareTo(scrnMsg.inInchLg_UN.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchLg_UN.setErrorInfo(1, "ZZZM9026E", new String[]{"Length(inches)-UnBoxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchWdt_UN) && BigDecimal.ZERO.compareTo(scrnMsg.inInchWdt_UN.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchWdt_UN.setErrorInfo(1, "ZZZM9026E", new String[]{"Depth(inches)-UnBoxed"});
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.inInchHgt_UN) && BigDecimal.ZERO.compareTo(scrnMsg.inInchHgt_UN.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.inInchHgt_UN.setErrorInfo(1, "ZZZM9026E", new String[]{"Height(inches)-UnBoxed"});
	        }
	        
	        // Attributes(Item Type Specific)
	        if (MDSE_ITEM_TP.MACHINE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_ME);
		        scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
		        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
	        } else if (MDSE_ITEM_TP.ACCESSORY.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
		        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
	        } else if (MDSE_ITEM_TP.SUPPLY.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.imgSplyOemMnfCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyOemCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyColorTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldCnt_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldUomCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.imgSplyPvtLbTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.easyPackTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.areaOfPaperNum_H1);
	        } else if (MDSE_ITEM_TP.PARTS.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
		        scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
		        scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
	        } else if (MDSE_ITEM_TP.MAINTENANCE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.svcCovBaseTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcCovTmplTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
		        // 2017/09/25 QC#18534(L3#445) ADD Start
		        scrnMsg.addCheckItem(scrnMsg.svcPgmTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_OM);
		        // 2017/09/25 QC#18534(L3#445) ADD End
	        } else if (MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
		        for (int i = 0; i < scrnMsg.N.getValidCount(); i++) {
		        	scrnMsg.addCheckItem(scrnMsg.N.no(i).termCondOptTpCd_N1);
		        	scrnMsg.addCheckItem(scrnMsg.N.no(i).termCondOptValTxt_N1);
                    //START 2017/06/08 K.Kasai [QC#18668,ADD]
                    if (!ZYPCommonFunc.hasValue(scrnMsg.N.no(i).termCondOptTpCd_N1)) {
                        scrnMsg.N.no(i).termCondOptTpCd_N1.setErrorInfo(1, "ZZM9000E", new String[]{"T&C Option"});
                    } else if (!ZYPCommonFunc.hasValue(scrnMsg.N.no(i).termCondOptValTxt_N1)) {
                        scrnMsg.N.no(i).termCondOptValTxt_N1.setErrorInfo(1, "ZZM9000E", new String[]{"T&C Value"});
                    } else if (ZYPCommonFunc.hasValue(scrnMsg.N.no(i).termCondOptTpCd_N1) &&
                            TERM_COND_OPT_TP.RESPONSE_TIME.equals(scrnMsg.N.no(i).termCondOptTpCd_N1.getValue()) &&
                            !ZYPCommonFunc.isNumberCheck(scrnMsg.N.no(i).termCondOptValTxt_N1.getValue())) {
                        scrnMsg.N.no(i).termCondOptValTxt_N1.setErrorInfo(1, "NMAM8668E");
                    }
                    //END 2017/06/08 K.Kasai [QC#18668,ADD]
		        }
	        } else if (MDSE_ITEM_TP.MAINTENANCE_OVERAGES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
                //QC#9346
		        //scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
	        } else if (MDSE_ITEM_TP.SOFTWARE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
		        scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicCtrlTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_EC);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFromQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swMaintCtrlTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.asrnPntPerLicQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.bdlMaintMdseCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.maintPopAvalFlg_H1);
		        scrnMsg.addCheckItem(scrnMsg.extMaintPopAvalFlg_H1);
	        } else if (MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
		        scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicCtrlTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_EC);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFromQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swMaintCtrlTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.asrnPntPerLicQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.bdlMaintMdseCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.maintPopAvalFlg_H1);
		        scrnMsg.addCheckItem(scrnMsg.extMaintPopAvalFlg_H1);
	        } else if (MDSE_ITEM_TP.SOFTWARE_MAINTENANCE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
		        scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFromQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.swMaintTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.maintItemTermMthNum_H1);
		        scrnMsg.addCheckItem(scrnMsg.asrnPntMinQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.asrnPntMaxQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.asrnPntFixPerOrdQty_H1);
	        } else if (MDSE_ITEM_TP.INTANGIBLE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
	            //scrnMsg.addCheckItem(scrnMsg.mdsePrcGrpCd_H1);
	            scrnMsg.addCheckItem(scrnMsg.dsIntgMdseTpCd_H1);
	        } else if (MDSE_ITEM_TP.SET.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
		        scrnMsg.addCheckItem(scrnMsg.easyPackTpCd_H1); // S21_NA Add Start QC#17149(Sol#259)
                scrnMsg.addCheckItem(scrnMsg.areaOfPaperNum_H1); // S21_NA Add Start QC#17149(Sol#259)
	        } else if (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        //scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
		        if (COA_MDSE_TP.MACHINE.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
			        scrnMsg.addCheckItem(scrnMsg.xxChkBox_ME);
			        scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
			        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
			        scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
		        } else if (COA_MDSE_TP.ACCESSORY.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
			        scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
			        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
		        } else if (COA_MDSE_TP.SUPPLY.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
			        scrnMsg.addCheckItem(scrnMsg.imgSplyOemMnfCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyOemCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyColorTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldCnt_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldUomCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyYieldTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.imgSplyPvtLbTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.easyPackTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.areaOfPaperNum_H1);
		        } else if (COA_MDSE_TP.PARTS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
			        scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
			        scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
			        scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
			        scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
			        scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
		        }
	        } else if (MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {  //Maintenance Charge
		        scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
	        } else if (MDSE_ITEM_TP.PROFESSIONAL_SERVICES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
	        } else if (MDSE_ITEM_TP.PART_CONSUMABLES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
		        scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
		        scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
		        scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
		        scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
		        scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
	        }
	        // Safety
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_HM);
	        scrnMsg.addCheckItem(scrnMsg.hazMatCd_H1);
	        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_HM) && FLG_ON_Y.equals(scrnMsg.xxChkBox_HM.getValue())) {
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.hazMatCd_H1)) {
	        		scrnMsg.hazMatCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Hazardous Number"});
	        	}
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.madeInCtryCd_H1)) {
	        		scrnMsg.madeInCtryCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Manufactured Country"});
	        	}
	        }
	        scrnMsg.addCheckItem(scrnMsg.madeInCtryCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.asmInCtryCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.pkgUomClsCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.frtClsCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.trfCd_H1);
	        //scrnMsg.putErrorScreen();
    	// #############
    	// Inventory
    	// #############
        } else if (TAB_INVENTORY.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        // Inventory Attributes
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IT);
            // 2020/04/13 QC#56494 Add Start
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_II);
            // 2020/04/13 QC#56494 Add End
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RA);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RI);
	        scrnMsg.addCheckItem(scrnMsg.defSrcWhCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.defSrcSubWhCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.defSrcProcrTpCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.invtyHardAllocTpCd_H1);
	        
	        // Parts Return Control
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_RR);
	        scrnMsg.addCheckItem(scrnMsg.rtrnCtrlTpCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.rtrnCtrlTpNm_H1);
	        scrnMsg.addCheckItem(scrnMsg.rtrnDsplTpCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.rtrnToVndCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.locNm_V1);
	        scrnMsg.addCheckItem(scrnMsg.rtrnToPrntVndCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.locNm_P1);
	        scrnMsg.addCheckItem(scrnMsg.rtrnWhCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.locNm_W1);
	        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RR) && FLG_ON_Y.equals(scrnMsg.xxChkBox_RR.getValue())) {
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpCd_H1)) {
	        		scrnMsg.rtrnCtrlTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Return Control Type"});
	        		scrnMsg.rtrnCtrlTpNm_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Return Control Type"});
	        	}
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnDsplTpCd_H1)) {
	        		scrnMsg.rtrnDsplTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Return Sub Warehouse"});
	        	}
	        }
	        // Serial Number Range
	        checkInput_SerialNoRg(ctx, scrnMsg, "Submit");
	        //scrnMsg.putErrorScreen();
    	// #############
    	// Accounting
    	// #############
        } else if (TAB_ACCOUNTING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        // Costing
        	if (MODE_NEW.equals(scrnMsg.xxModeCd_H1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.thisMthTotStdCostAmt_H1)) {
        		scrnMsg.thisMthTotStdCostAmt_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Standard Cost"});
        	}
	        scrnMsg.addCheckItem(scrnMsg.thisMthTotStdCostAmt_H1);
	        scrnMsg.addCheckItem(scrnMsg.mdseCstUpdDt_H1);
        	if (MODE_NEW.equals(scrnMsg.xxModeCd_H1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.assetRecovCostAmt_H1)) {
        		scrnMsg.assetRecovCostAmt_H1.setErrorInfo(1, "ZZM9000E", new String[]{"ARV Cost"});
        	}
	        scrnMsg.addCheckItem(scrnMsg.assetRecovCostAmt_H1);
	        scrnMsg.addCheckItem(scrnMsg.assetRecovCostAmtUpdDt_H1);
	        scrnMsg.addCheckItem(scrnMsg.prevAssetRecovCostAmt_H1);
	        scrnMsg.addCheckItem(scrnMsg.lastMthTotStdCostAmt_H1);
	        scrnMsg.addCheckItem(scrnMsg.origFobAmt_H1);
	        scrnMsg.addCheckItem(scrnMsg.origFobAmtUpdDt_H1);
	        // Account Numbers
	        scrnMsg.addCheckItem(scrnMsg.revCoaAcctCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.cogsCoaAcctCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.expCoaAcctCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H1);
	        // Accounting Rules
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IP);
	        scrnMsg.addCheckItem(scrnMsg.dfrdAcctgRuleCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.dfrdInvRuleCd_H1);
	        // 2018/08/08 QC#27222 Del Start
//	        scrnMsg.addCheckItem(scrnMsg.taxExemTpCd_H1);
	        // 2018/08/08 QC#27222 Del Start
	        //scrnMsg.putErrorScreen();
    	// ###############
    	// Field Service
    	// ###############
        } else if (TAB_FIELD_SERVICE.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        // Service Attributes
	        scrnMsg.addCheckItem(scrnMsg.svcWtyTpCd_H1);
	        //if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd_H1) 
	        //		&& (MDSE_ITEM_TP.MACHINE.equals(scrnMsg.mdseItemTpCd_H1.getValue()) 
	        //		|| MDSE_ITEM_TP.ACCESSORY.equals(scrnMsg.mdseItemTpCd_H1.getValue()))) {
	        //	if (!ZYPCommonFunc.hasValue(scrnMsg.svcWtyTpCd_H1)) {
	        //		scrnMsg.svcWtyTpCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Warranty"});
	        //	}
	        //}
	        scrnMsg.addCheckItem(scrnMsg.wtyDaysAot_H1);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_MM);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IB);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_SC);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IR);
	        scrnMsg.addCheckItem(scrnMsg.iwrMdlCd_H1);
	        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IR) && FLG_ON_Y.equals(scrnMsg.xxChkBox_IR.getValue())) {
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.iwrMdlCd_H1)) {
	        		scrnMsg.iwrMdlCd_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Imageware Remote Model"});
	        	}
	        }
	        scrnMsg.addCheckItem(scrnMsg.iwrMdseCd_H1);
	        scrnMsg.addCheckItem(scrnMsg.mdseItemBillTpCd_H1);
	        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
	        scrnMsg.addCheckItem(scrnMsg.svcIstlRuleNum_H1);
	        scrnMsg.addCheckItem(scrnMsg.svcIstlCallGrpNum_H1);
	        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
	        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
	        scrnMsg.addCheckItem(scrnMsg.svcDeinsRuleNum_H1);
	        scrnMsg.addCheckItem(scrnMsg.svcDeinsCallGrpNum_H1);
	        // END 2023/12/12 K.Watanabe [QC#61300, ADD]
	        //scrnMsg.putErrorScreen();
    	// ##################
    	// Order Processing
    	// ##################
        } else if (TAB_ORDER_PROCESSING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        // Order Processing
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CO);
	        scrnMsg.addCheckItem(scrnMsg.cpoMinOrdQty_H1);
	        scrnMsg.addCheckItem(scrnMsg.cpoMaxOrdQty_H1);
	        scrnMsg.addCheckItem(scrnMsg.cpoIncrOrdQty_H1);
	        boolean errorFlag = false;
	        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_CO) && FLG_ON_Y.equals(scrnMsg.xxChkBox_CO.getValue())) {
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.cpoMinOrdQty_H1)) {
	        		scrnMsg.cpoMinOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Minimum Order Quantity"});
	        		errorFlag = true;
	        	}
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.cpoMaxOrdQty_H1)) {
	        		scrnMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Maximum Order Quantity"});
	        		errorFlag = true;
	        	}
	        	if (!ZYPCommonFunc.hasValue(scrnMsg.cpoIncrOrdQty_H1)) {
	        		scrnMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "ZZM9000E", new String[]{"Order Increments"});
	        		errorFlag = true;
	        	}
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.cpoMinOrdQty_H1) && BigDecimal.ZERO.compareTo(scrnMsg.cpoMinOrdQty_H1.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.cpoMinOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Minimum Order Quantity"});
        		errorFlag = true;
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.cpoMaxOrdQty_H1) && BigDecimal.ZERO.compareTo(scrnMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Maximum Order Quantity"});
        		errorFlag = true;
	        }
	        if (ZYPCommonFunc.hasValue(scrnMsg.cpoIncrOrdQty_H1) && BigDecimal.ZERO.compareTo(scrnMsg.cpoIncrOrdQty_H1.getValue()) > 0) {
	        	//ZZZM9026E=0,[@] is invalid value
        		scrnMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "ZZZM9026E", new String[]{"Order Increments"});
        		errorFlag = true;
	        }
	        if (!errorFlag) {
		        if (ZYPCommonFunc.hasValue(scrnMsg.cpoMinOrdQty_H1) 
		        		&& ZYPCommonFunc.hasValue(scrnMsg.cpoMaxOrdQty_H1)) {
		            if (scrnMsg.cpoMinOrdQty_H1.getValue().compareTo(scrnMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
		            	//NMAM0042E=0,The value for [@] must be over [@].  
		            	scrnMsg.cpoMaxOrdQty_H1.setErrorInfo(1, "NMAM0042E", new String[] {"Maximum Order Quantity", "Minimum Order Quantity" });
		            }
		        }
		        if (ZYPCommonFunc.hasValue(scrnMsg.cpoMinOrdQty_H1) 
		        		&& ZYPCommonFunc.hasValue(scrnMsg.cpoMaxOrdQty_H1) 
		        		&& ZYPCommonFunc.hasValue(scrnMsg.cpoIncrOrdQty_H1)) {
		            if (scrnMsg.cpoIncrOrdQty_H1.getValue().compareTo(scrnMsg.cpoMaxOrdQty_H1.getValue().subtract(scrnMsg.cpoMinOrdQty_H1.getValue())) > 0) {
		            	//NMAM0045E=0,The value for [@] must be below [@].
		            	scrnMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "NMAM0045E", new String[] {"Order Increments", "(Maximum Order Quantity - Minimum Order Quantity)" });
		            }
		        }
		        if (ZYPCommonFunc.hasValue(scrnMsg.cpoMaxOrdQty_H1) 
		        		&& ZYPCommonFunc.hasValue(scrnMsg.cpoIncrOrdQty_H1)) {
		            if (scrnMsg.cpoIncrOrdQty_H1.getValue().compareTo(scrnMsg.cpoMaxOrdQty_H1.getValue()) > 0) {
		            	//NMAM0045E=0,The value for [@] must be below [@].
		            	scrnMsg.cpoIncrOrdQty_H1.setErrorInfo(1, "NMAM0045E", new String[] {"Order Increments", "Maximum Order Quantity" });
		            }
		        }
	        }

	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_IE);
	        scrnMsg.addCheckItem(scrnMsg.xxChkBox_CF);
	        //scrnMsg.putErrorScreen();

    	// ################
    	// Sup / Relation
    	// ################
        } else if (TAB_SUPD.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        checkInput_Supd(ctx, scrnMsg);
	        checkInput_MdseReln(ctx, scrnMsg);
	        //scrnMsg.putErrorScreen();
        
    	// ################
    	// Cust Ref
    	// ################
        } else if (TAB_CUST_REF.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        checkInput_CustRef(ctx, scrnMsg);
	        //scrnMsg.putErrorScreen();
	    // 2018/08/08 QC#27222 Add Start
	    // ################
	    // Taxing
	    // ################
        } else if (TAB_TAXING.equals(scrnMsg.xxDplyTab_H1.getValue())) {
	        scrnMsg.addCheckItem(scrnMsg.taxExemTpCd_H1);
	    // 2018/08/08 QC#27222 Add End
        }
        scrnMsg.putErrorScreen();
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }
        
    }

    public static void checkInput_SaveTmpl(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {

        scrnMsg.addCheckItem(scrnMsg.mdseCratTmplNm_H2);
        scrnMsg.addCheckItem(scrnMsg.mdseCratTmplCratDt_H1);
        if (!ZYPCommonFunc.hasValue(scrnMsg.mdseCratTmplNm_BK)) {
        	//ZZM9000E=0,[@] field is mandatory.
        	scrnMsg.mdseCratTmplNm_H2.setErrorInfo(1, "ZZM9000E", new String[] { "Template Name" });
        	ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCratTmplNm_H2, scrnMsg.mdseCratTmplNm_BK);
            scrnMsg.addCheckItem(scrnMsg.mdseCratTmplNm_H2);
            scrnMsg.putErrorScreen();
        }
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }
    }
    
    /**
     * checkInput Submit BASIC
     * @param ctx EZDApplicationContext
     * @param scrnMsg NMAL0110BMsg
     */
    public static void checkInput_Tab(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
    
    	String preDplyTab_H1 = scrnMsg.xxDplyTab_H1.getValue();
        // Input Check
    	// #############
    	// Header
    	// #############
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseDescLongTxt_H1)) scrnMsg.addCheckItem(scrnMsg.mdseDescLongTxt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemMnfCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemMnfCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mnfItemCd_H1)) scrnMsg.addCheckItem(scrnMsg.mnfItemCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.upcCd_H1)) scrnMsg.addCheckItem(scrnMsg.upcCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemStsCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemStsCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseDescLongTxt_H1)) scrnMsg.addCheckItem(scrnMsg.mdseDescLongTxt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemActvDt_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemActvDt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCratTmplNm_H1)) scrnMsg.addCheckItem(scrnMsg.mdseCratTmplNm_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseRgtnTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseRgtnTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemClsTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemClsTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.coaMdseTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.coaProdCd_H1)) scrnMsg.addCheckItem(scrnMsg.coaProdCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.prchGrpCd_H1)) scrnMsg.addCheckItem(scrnMsg.prchGrpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mktMdlCd_H1)) scrnMsg.addCheckItem(scrnMsg.mktMdlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mktMdlSegCd_H1)) scrnMsg.addCheckItem(scrnMsg.mktMdlSegCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.zerothProdCtrlCd_H1)) scrnMsg.addCheckItem(scrnMsg.zerothProdCtrlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.firstProdCtrlCd_H1)) scrnMsg.addCheckItem(scrnMsg.firstProdCtrlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.scdProdCtrlCd_H1)) scrnMsg.addCheckItem(scrnMsg.scdProdCtrlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.thirdProdCtrlCd_H1)) scrnMsg.addCheckItem(scrnMsg.thirdProdCtrlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.frthProdCtrlCd_H1)) scrnMsg.addCheckItem(scrnMsg.frthProdCtrlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpCd_01)) scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_01);
        if (ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpCd_02)) scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_02);
        if (ZYPCommonFunc.hasValue(scrnMsg.slsMatGrpCd_03)) scrnMsg.addCheckItem(scrnMsg.slsMatGrpCd_03);
        if (ZYPCommonFunc.hasValue(scrnMsg.dsCmsnGrpCd_H1)) scrnMsg.addCheckItem(scrnMsg.dsCmsnGrpCd_H1);
        scrnMsg.putErrorScreen();
        
    	// #############
    	// General
    	// #############
        // Dimensions
        if (scrnMsg.inPoundWt_EA.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("inPoundWt_EA");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.inPoundWt_EA.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.inPoundWt_EA);
        scrnMsg.addCheckItem(scrnMsg.inPoundWt_UN);
        if (scrnMsg.inInchLg_EA.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("inInchLg_EA");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.inInchLg_EA.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.inInchLg_EA);
        scrnMsg.addCheckItem(scrnMsg.inInchLg_UN);
        if (scrnMsg.inInchWdt_EA.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("inInchWdt_EA");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.inInchWdt_EA.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.inInchWdt_EA);
        scrnMsg.addCheckItem(scrnMsg.inInchWdt_UN);
        if (scrnMsg.inInchHgt_EA.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("inInchHgt_EA");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.inInchHgt_EA.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.inInchHgt_EA);
        scrnMsg.addCheckItem(scrnMsg.inInchHgt_UN);
        // Units of Measure
        if (ZYPCommonFunc.hasValue(scrnMsg.pkgUomCd_H1)) scrnMsg.addCheckItem(scrnMsg.pkgUomCd_H1);
        checkInput_PkgUom(ctx, scrnMsg);

        // Attributes(Item Type Specific)
        if (MDSE_ITEM_TP.MACHINE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_ME)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_ME);
        	if (ZYPCommonFunc.hasValue(scrnMsg.backOrdImpctTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
        	if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.svcChrgItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
        } else if (MDSE_ITEM_TP.ACCESSORY.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.backOrdImpctTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
        	if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
        } else if (MDSE_ITEM_TP.SUPPLY.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyOemMnfCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyOemMnfCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyOemCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyOemCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyColorTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyColorTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldCnt_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldCnt_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldUomCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldUomCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyPvtLbTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyPvtLbTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.easyPackTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.easyPackTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.areaOfPaperNum_H1)) scrnMsg.addCheckItem(scrnMsg.areaOfPaperNum_H1);
        } else if (MDSE_ITEM_TP.PARTS.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_PD)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldOtptQty_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldDaysAot_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtSrvyReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
        } else if (MDSE_ITEM_TP.MAINTENANCE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.svcCovBaseTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcCovBaseTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.svcCovTmplTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcCovTmplTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.svcAllocTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
        	// 2017/09/25 QC#18534(L3#445) ADD Start
        	if (ZYPCommonFunc.hasValue(scrnMsg.svcPgmTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcPgmTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_OM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_OM);
        	// 2017/09/25 QC#18534(L3#445) ADD End
        } else if (MDSE_ITEM_TP.MAINTENANCE_CHARGES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {  //Maintenance Charge
            if (ZYPCommonFunc.hasValue(scrnMsg.svcAllocTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.svcChrgItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
        } else if (MDSE_ITEM_TP.MAINTENANCE_OVERAGES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.svcAllocTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcAllocTpCd_H1);
        } else if (MDSE_ITEM_TP.SOFTWARE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.swCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swVrsnTxt_H1)) scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swProdCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicCtrlTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swLicCtrlTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_EC)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_EC);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatFromQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatFromQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatToQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatFixQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swMaintCtrlTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swMaintCtrlTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.asrnPntPerLicQty_H1)) scrnMsg.addCheckItem(scrnMsg.asrnPntPerLicQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.bdlMaintMdseCd_H1)) scrnMsg.addCheckItem(scrnMsg.bdlMaintMdseCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.maintPopAvalFlg_H1)) scrnMsg.addCheckItem(scrnMsg.maintPopAvalFlg_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.extMaintPopAvalFlg_H1)) scrnMsg.addCheckItem(scrnMsg.extMaintPopAvalFlg_H1);
        } else if (MDSE_ITEM_TP.SOFTWARE_LICENSE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.swCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swVrsnTxt_H1)) scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swProdCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicCtrlTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swLicCtrlTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_EC)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_EC);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatFromQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatFromQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatToQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatFixQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swMaintCtrlTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swMaintCtrlTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.asrnPntPerLicQty_H1)) scrnMsg.addCheckItem(scrnMsg.asrnPntPerLicQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.bdlMaintMdseCd_H1)) scrnMsg.addCheckItem(scrnMsg.bdlMaintMdseCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.maintPopAvalFlg_H1)) scrnMsg.addCheckItem(scrnMsg.maintPopAvalFlg_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.extMaintPopAvalFlg_H1)) scrnMsg.addCheckItem(scrnMsg.extMaintPopAvalFlg_H1);
        } else if (MDSE_ITEM_TP.SOFTWARE_MAINTENANCE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.swCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swTpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swVrsnTxt_H1)) scrnMsg.addCheckItem(scrnMsg.swVrsnTxt_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swProdCatgCd_H1)) scrnMsg.addCheckItem(scrnMsg.swProdCatgCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatToQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatToQty_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.swLicSeatFixQty_H1)) scrnMsg.addCheckItem(scrnMsg.swLicSeatFixQty_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.swMaintTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.swMaintTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.maintItemTermMthNum_H1)) scrnMsg.addCheckItem(scrnMsg.maintItemTermMthNum_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.asrnPntMinQty_H1)) scrnMsg.addCheckItem(scrnMsg.asrnPntMinQty_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.asrnPntMaxQty_H1)) scrnMsg.addCheckItem(scrnMsg.asrnPntMaxQty_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.asrnPntFixPerOrdQty_H1)) scrnMsg.addCheckItem(scrnMsg.asrnPntFixPerOrdQty_H1);
        } else if (MDSE_ITEM_TP.INTANGIBLE.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            //if (ZYPCommonFunc.hasValue(scrnMsg.mdsePrcGrpCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdsePrcGrpCd_H1);
            if (ZYPCommonFunc.hasValue(scrnMsg.dsIntgMdseTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.dsIntgMdseTpCd_H1);
        } else if (MDSE_ITEM_TP.SET.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
        } else if (MDSE_ITEM_TP.KIT.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            //if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
            if (COA_MDSE_TP.MACHINE.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_ME)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_ME);
            	if (ZYPCommonFunc.hasValue(scrnMsg.backOrdImpctTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
            	if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.svcChrgItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcChrgItemTpCd_H1);
            } else if (COA_MDSE_TP.ACCESSORY.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.backOrdImpctTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.backOrdImpctTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RM);
            	if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
            } else if (COA_MDSE_TP.SUPPLY.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyOemMnfCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyOemMnfCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyOemCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyOemCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyColorTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyColorTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldCnt_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldCnt_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldUomCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldUomCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyYieldTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyYieldTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.imgSplyPvtLbTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.imgSplyPvtLbTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.easyPackTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.easyPackTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.areaOfPaperNum_H1)) scrnMsg.addCheckItem(scrnMsg.areaOfPaperNum_H1);
            } else if (COA_MDSE_TP.PARTS.equals(scrnMsg.coaMdseTpCd_H1.getValue())) {
            	if (ZYPCommonFunc.hasValue(scrnMsg.prtItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_PD)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
            	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldOtptQty_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldDaysAot_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
            	if (ZYPCommonFunc.hasValue(scrnMsg.prtSrvyReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
            }
        } else if (MDSE_ITEM_TP.PROFESSIONAL_SERVICES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.sowReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.sowReqFlg_H1);
        } else if (MDSE_ITEM_TP.PART_CONSUMABLES.equals(scrnMsg.mdseItemTpCd_H1.getValue())) {
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtItemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.prtItemTpCd_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_PD)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_PD);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldOtptQty_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldOtptQty_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtYieldDaysAot_H1)) scrnMsg.addCheckItem(scrnMsg.prtYieldDaysAot_H1);
        	if (ZYPCommonFunc.hasValue(scrnMsg.prtSrvyReqFlg_H1)) scrnMsg.addCheckItem(scrnMsg.prtSrvyReqFlg_H1);
        }
        // Safety
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_HM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_HM);
        if (ZYPCommonFunc.hasValue(scrnMsg.hazMatCd_H1)) scrnMsg.addCheckItem(scrnMsg.hazMatCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.madeInCtryCd_H1)) scrnMsg.addCheckItem(scrnMsg.madeInCtryCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.asmInCtryCd_H1)) scrnMsg.addCheckItem(scrnMsg.asmInCtryCd_H1);
        scrnMsg.xxDplyTab_H1.setValue(TAB_GENERAL);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
    	// #############
    	// Inventory
    	// #############
        // Inventory Attributes
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IT)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IT);
        // 2020/04/13 QC#56494 Add Start
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_II)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IT);
        // 2020/04/13 QC#56494 Add End
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RA)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RA);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RI)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RI);
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcWhCd_H1)) scrnMsg.addCheckItem(scrnMsg.defSrcWhCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcSubWhCd_H1)) scrnMsg.addCheckItem(scrnMsg.defSrcSubWhCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcProcrTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.defSrcProcrTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.invtyHardAllocTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.invtyHardAllocTpCd_H1);
        // Parts Return Control
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_RR)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_RR);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnCtrlTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpNm_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnCtrlTpNm_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnDsplTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnDsplTpCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnToVndCd_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnToVndCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.locNm_V1)) scrnMsg.addCheckItem(scrnMsg.locNm_V1);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnToPrntVndCd_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnToPrntVndCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.locNm_P1)) scrnMsg.addCheckItem(scrnMsg.locNm_P1);
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnWhCd_H1)) scrnMsg.addCheckItem(scrnMsg.rtrnWhCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.locNm_W1)) scrnMsg.addCheckItem(scrnMsg.locNm_W1);
        // Serial Number Range
        //checkInput_SerialNoRg(ctx, scrnMsg);
        scrnMsg.xxDplyTab_H1.setValue(TAB_INVENTORY);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
    	// #############
    	// Accounting
    	// #############
        // Costing
        if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
            if (ZYPCommonFunc.hasValue(scrnMsg.thisMthTotStdCostAmt_H1)) scrnMsg.addCheckItem(scrnMsg.thisMthTotStdCostAmt_H1);
        } else {
            if (scrnMsg.thisMthTotStdCostAmt_H1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("thisMthTotStdCostAmt_H1");
                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.thisMthTotStdCostAmt_H1.clearErrorInfo();
                }
            }
        	scrnMsg.addCheckItem(scrnMsg.thisMthTotStdCostAmt_H1);
        }
        if (MODE_MOD.equals(scrnMsg.xxModeCd_H1.getValue())) {
	        if (ZYPCommonFunc.hasValue(scrnMsg.assetRecovCostAmt_H1)) scrnMsg.addCheckItem(scrnMsg.assetRecovCostAmt_H1);
        } else {
            if (scrnMsg.assetRecovCostAmt_H1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("assetRecovCostAmt_H1");
                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnMsg.assetRecovCostAmt_H1.clearErrorInfo();
                }
            }
        	scrnMsg.addCheckItem(scrnMsg.assetRecovCostAmt_H1);
        }
        if (ZYPCommonFunc.hasValue(scrnMsg.assetRecovCostAmtUpdDt_H1)) scrnMsg.addCheckItem(scrnMsg.assetRecovCostAmtUpdDt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.prevAssetRecovCostAmt_H1)) scrnMsg.addCheckItem(scrnMsg.prevAssetRecovCostAmt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseCstUpdDt_H1)) scrnMsg.addCheckItem(scrnMsg.mdseCstUpdDt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.lastMthTotStdCostAmt_H1)) scrnMsg.addCheckItem(scrnMsg.lastMthTotStdCostAmt_H1);
        if (scrnMsg.origFobAmt_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("origFobAmt_H1");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.origFobAmt_H1.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.origFobAmt_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.origFobAmtUpdDt_H1)) scrnMsg.addCheckItem(scrnMsg.origFobAmtUpdDt_H1);
        // Account Numbers
        if (ZYPCommonFunc.hasValue(scrnMsg.revCoaAcctCd_H1)) scrnMsg.addCheckItem(scrnMsg.revCoaAcctCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.cogsCoaAcctCd_H1)) scrnMsg.addCheckItem(scrnMsg.cogsCoaAcctCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.expCoaAcctCd_H1)) scrnMsg.addCheckItem(scrnMsg.expCoaAcctCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.lineBizTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.lineBizTpCd_H1);
        // Accounting Rules
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IP)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IP);
        if (ZYPCommonFunc.hasValue(scrnMsg.dfrdAcctgRuleCd_H1)) scrnMsg.addCheckItem(scrnMsg.dfrdAcctgRuleCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.dfrdInvRuleCd_H1)) scrnMsg.addCheckItem(scrnMsg.dfrdInvRuleCd_H1);
        // 2018/08/08 QC#27222 Del Start
//        if (ZYPCommonFunc.hasValue(scrnMsg.taxExemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.taxExemTpCd_H1);
        // 2018/08/08 QC#27222 Del End
        scrnMsg.xxDplyTab_H1.setValue(TAB_ACCOUNTING);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
    	// ###############
    	// Field Service
    	// ###############
        // Service Attributes
        if (ZYPCommonFunc.hasValue(scrnMsg.svcWtyTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.svcWtyTpCd_H1);
        if (scrnMsg.wtyDaysAot_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("wtyDaysAot_H1");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.wtyDaysAot_H1.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.wtyDaysAot_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_MM)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_MM);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IB)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IB);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_SC)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_SC);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IR)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IR);
        if (ZYPCommonFunc.hasValue(scrnMsg.iwrMdlCd_H1)) scrnMsg.addCheckItem(scrnMsg.iwrMdlCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.iwrMdseCd_H1)) scrnMsg.addCheckItem(scrnMsg.iwrMdseCd_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemBillTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.mdseItemBillTpCd_H1);
        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.svcIstlRuleNum_H1)) scrnMsg.addCheckItem(scrnMsg.svcIstlRuleNum_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.svcIstlCallGrpNum_H1)) scrnMsg.addCheckItem(scrnMsg.svcIstlCallGrpNum_H1);
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]
        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
        if (ZYPCommonFunc.hasValue(scrnMsg.svcDeinsRuleNum_H1)) scrnMsg.addCheckItem(scrnMsg.svcDeinsRuleNum_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.svcDeinsCallGrpNum_H1)) scrnMsg.addCheckItem(scrnMsg.svcDeinsCallGrpNum_H1);
        // END 2023/12/12 K.Watanabe [QC#61300, ADD]
        scrnMsg.xxDplyTab_H1.setValue(TAB_FIELD_SERVICE);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);

    	// ##################
    	// Order Processing
    	// ##################
        // Order Processing
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_CO)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_CO);
        if (scrnMsg.cpoMinOrdQty_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("cpoMinOrdQty_H1");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.cpoMinOrdQty_H1.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.cpoMinOrdQty_H1);
        if (scrnMsg.cpoMaxOrdQty_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("cpoMaxOrdQty_H1");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.cpoMaxOrdQty_H1.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.cpoMaxOrdQty_H1);
        if (scrnMsg.cpoIncrOrdQty_H1.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("cpoIncrOrdQty_H1");
            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.cpoIncrOrdQty_H1.clearErrorInfo();
            }
        }
        scrnMsg.addCheckItem(scrnMsg.cpoIncrOrdQty_H1);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_IE)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_IE);
        if (ZYPCommonFunc.hasValue(scrnMsg.xxChkBox_CF)) scrnMsg.addCheckItem(scrnMsg.xxChkBox_CF);
        scrnMsg.xxDplyTab_H1.setValue(TAB_ORDER_PROCESSING);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
    	// ################
    	// Sup / Relation
    	// ################
        //checkInput_Supd(ctx, scrnMsg);
        //checkInput_MdseReln(ctx, scrnMsg);
        scrnMsg.xxDplyTab_H1.setValue(TAB_SUPD);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
    	// ################
    	// Cust Ref
    	// ################
        //checkInput_CustRef(ctx, scrnMsg);
        scrnMsg.xxDplyTab_H1.setValue(TAB_CUST_REF);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        
        // 2018/08/08 QC#27222 Add Start
        // ################
    	// Taxing
    	// ################
        if (ZYPCommonFunc.hasValue(scrnMsg.taxExemTpCd_H1)) scrnMsg.addCheckItem(scrnMsg.taxExemTpCd_H1);
        scrnMsg.xxDplyTab_H1.setValue(TAB_TAXING);
        scrnMsg.putErrorScreen();
        scrnMsg.xxDplyTab_H1.setValue(preDplyTab_H1);
        // 2018/08/08 QC#27222 Add End
        
        String msgCode = scrnMsg.getMessageCode();
        if (msgCode.endsWith(ERROR_CODE_E)) {
            throw new EZDFieldErrorException();
        }
        
    }    
    private static void checkInput_SerialNoRg(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg, String action) {
    	//0:NOT CONTROLLED, 1:ON RECEIPT&SHIPMENT, 2:ON SHIPMENT
        //boolean emptyCheck = ZYPCommonFunc.hasValue(scrnMsg.xxRadioBtn_SS) && BigDecimal.ONE.compareTo(scrnMsg.xxRadioBtn_SS.getValue()) == 0;
    	if ("Submit".equals(action)) {
	        scrnMsg.addCheckItem(scrnMsg.xxRadioBtn_SS);
    	}
        final BigDecimal decimal30 = new BigDecimal("30");
        //scrnMsg.A.setCheckParam(new String[] {"lgSerNum_A1" }, 1);
        scrnMsg.addCheckItem(scrnMsg.A);
        if (scrnMsg.A.getValidCount() != 0) {
	        if (chkDetailListNotEmpty_SERIAL_NO_RG(scrnMsg)) {
	            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
	                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromSerNum_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).thruSerNum_A1)) {
	                    continue;
	                }
	                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromSerNum_A1) || ZYPCommonFunc.hasValue(scrnMsg.A.no(i).thruSerNum_A1)) {
	                    if (scrnMsg.A.no(i).fromSerNum_A1.getValue().length() != scrnMsg.A.no(i).thruSerNum_A1.getValue().length()) {
	                        // The value you entered is incorrect.
	                        scrnMsg.A.no(i).fromSerNum_A1.setErrorInfo(1, "NMAM0046E", new String[] {"From Length", "To Length" });
	
	                        scrnMsg.addCheckItem(scrnMsg.A.no(i).fromSerNum_A1);
	                     }
                         scrnMsg.A.no(i).lgSerNum_A1.setValue(scrnMsg.A.no(i).fromSerNum_A1.getValue().length());
	                 }
	                 if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromSerNum_A1)) {
	                     if (decimal30.compareTo(new BigDecimal(String.valueOf(scrnMsg.A.no(i).fromSerNum_A1.getValue().length()))) < 0) {
	                         scrnMsg.A.no(i).fromSerNum_A1.setErrorInfo(1, "NMAM0190E");
	                         scrnMsg.addCheckItem(scrnMsg.A.no(i).fromSerNum_A1);
	                     }
	                 }
	             }
            } else {
	            //if (emptyCheck) {
	            //    scrnMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM0011E", new String[] {"Serial Number Range" });
	            //}
	        }
	    } else {
	        //if (emptyCheck) {
	        //    scrnMsg.xxRadioBtn_SS.setErrorInfo(1, "NMAM0011E", new String[] {"Serial Number Range" });
	        //}
	    }
	}
    
    private static void checkInput_PkgUom(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.K);
        if (scrnMsg.K.getValidCount() != 0) {
	        if (chkDetailListNotEmpty_PKG_UOM(scrnMsg)) {
	            for (int i = 0; i < scrnMsg.K.getValidCount(); i++) {
	                if (!ZYPCommonFunc.hasValue(scrnMsg.K.no(i).pkgUomCd_K1)) {
	                    continue;
	                }
	                if (!ZYPCommonFunc.hasValue(scrnMsg.K.no(i).inEachQty_K1)) {
	                	//ZZM9000E=0,[@] field is mandatory.
	                	scrnMsg.K.no(i).inEachQty_K1.setErrorInfo(1, "ZZM9000E", new String[] { "Qty Inside" });
                        scrnMsg.addCheckItem(scrnMsg.K.no(i).inEachQty_K1);
	                }
	             }
	        }
	    }
	}

    private static void checkInput_Supd(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.C);
        if (scrnMsg.C.getValidCount() != 0) {
        	List<String> dupChkList = new ArrayList<String>();        	
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.C.no(i).supdToMdseCd_C1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.C.no(i).mdseDescShortTxt_C1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.C.no(i).supdCratDt_C1)) {
                    continue;
                }
                scrnMsg.C.no(i).supdToMdseCd_C1.setNameForMessage("Superseeded By");
                scrnMsg.C.no(i).mdseDescShortTxt_C1.setNameForMessage("Description");
                scrnMsg.C.no(i).supdCratDt_C1.setNameForMessage("Date");
                //QC#5580
                scrnMsg.addCheckItem(scrnMsg.C.no(i).xxChkBox_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).supdToMdseCd_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).mdseDescShortTxt_C1);
                scrnMsg.addCheckItem(scrnMsg.C.no(i).supdCratDt_C1);
                
                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).supdCratDt_C1)) {
                	validateDate(scrnMsg.C.no(i).supdCratDt_C1, true, true, scrnMsg, "Date");
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.C.no(i).supdToMdseCd_C1)) {
                	if (dupChkList.contains(scrnMsg.C.no(i).supdToMdseCd_C1.getValue())) {
                		// NMAM0072E=0, @  is duplicated.
                		scrnMsg.C.no(i).supdToMdseCd_C1.setErrorInfo(1, "NMAM0072E", new String[]{"Suprtseeded By"});
                        scrnMsg.addCheckItem(scrnMsg.C.no(i).supdToMdseCd_C1);
                	} else {
                    	dupChkList.add(scrnMsg.C.no(i).supdToMdseCd_C1.getValue());
                	}
                }
            }
            //QC#5580
            scrnMsg.putErrorScreen();
	    }
	}
    
    
    private static void checkInput_MdseReln(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.D);
        if (scrnMsg.D.getValidCount() != 0) {
        	List<String> dupChkList = new ArrayList<String>();        	
            for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.D.no(i).mdseItemRelnTpCd_D1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.D.no(i).relnMdseCd_D1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.D.no(i).mdseDescShortTxt_D1)) {
                    continue;
                }
                scrnMsg.D.no(i).mdseItemRelnTpCd_D1.setNameForMessage("Relationship");
                scrnMsg.D.no(i).relnMdseCd_D1.setNameForMessage("Related Item");
                scrnMsg.D.no(i).mdseDescShortTxt_D1.setNameForMessage("Description");
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseItemRelnTpCd_D1);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).relnMdseCd_D1);
                scrnMsg.addCheckItem(scrnMsg.D.no(i).mdseDescShortTxt_D1);
                if (ZYPCommonFunc.hasValue(scrnMsg.D.no(i).relnMdseCd_D1)) {
                	if (dupChkList.contains(scrnMsg.D.no(i).relnMdseCd_D1.getValue())) {
                		// NMAM0072E=0, @  is duplicated.
                		scrnMsg.D.no(i).relnMdseCd_D1.setErrorInfo(1, "NMAM0072E", new String[]{"Related Item"});
                        scrnMsg.addCheckItem(scrnMsg.D.no(i).relnMdseCd_D1);
                	} else {
                    	dupChkList.add(scrnMsg.D.no(i).relnMdseCd_D1.getValue());
                	}
                }
            }
	    }
	}
    
    private static void checkInput_CustRef(EZDApplicationContext ctx, NMAL0110BMsg scrnMsg) {
        scrnMsg.addCheckItem(scrnMsg.E);
        if (scrnMsg.E.getValidCount() != 0) {
        	List<String> dupChkList = new ArrayList<String>();        	
            for (int i = 0; i < scrnMsg.E.getValidCount(); i++) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.E.no(i).dsAcctNum_E1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.E.no(i).dsAcctNm_E1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.E.no(i).custMdseCd_E1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.E.no(i).custMdseNm_E1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.E.no(i).effFromDt_E1) 
                		&& !ZYPCommonFunc.hasValue(scrnMsg.E.no(i).xxChkBox_EN)) {
                    continue;
                }
                scrnMsg.E.no(i).dsAcctNum_E1.setNameForMessage("Customer Number");
                scrnMsg.E.no(i).dsAcctNm_E1.setNameForMessage("Customer Name");
                scrnMsg.E.no(i).custMdseCd_E1.setNameForMessage("Customer Item");
                scrnMsg.E.no(i).custMdseNm_E1.setNameForMessage("Description");
                scrnMsg.E.no(i).effFromDt_E1.setNameForMessage("Date");
                scrnMsg.E.no(i).xxChkBox_EN.setNameForMessage("Enabled");
                scrnMsg.addCheckItem(scrnMsg.E.no(i).dsAcctNum_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).dsAcctNm_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).custMdseCd_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).custMdseNm_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).effFromDt_E1);
                scrnMsg.addCheckItem(scrnMsg.E.no(i).xxChkBox_EN);
                
                if (ZYPCommonFunc.hasValue(scrnMsg.E.no(i).effFromDt_E1)) {
                	validateDate(scrnMsg.E.no(i).effFromDt_E1, true, true, scrnMsg, "Date");
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.E.no(i).dsAcctNum_E1) && ZYPCommonFunc.hasValue(scrnMsg.E.no(i).custMdseCd_E1)) {
                	if (dupChkList.contains(scrnMsg.E.no(i).dsAcctNum_E1.getValue() + scrnMsg.E.no(i).custMdseCd_E1.getValue())) {
                		// NMAM0072E=0, @  is duplicated.
                		scrnMsg.E.no(i).dsAcctNum_E1.setErrorInfo(1, "NMAM0072E", new String[]{"Customer Number and Customer Item"});
                		scrnMsg.E.no(i).custMdseCd_E1.setErrorInfo(1, "NMAM0072E", new String[]{"Customer Number and Customer Item"});
                        scrnMsg.addCheckItem(scrnMsg.E.no(i).dsAcctNum_E1);
                        scrnMsg.addCheckItem(scrnMsg.E.no(i).custMdseCd_E1);
                	} else {
                    	dupChkList.add(scrnMsg.E.no(i).dsAcctNum_E1.getValue() + scrnMsg.E.no(i).custMdseCd_E1.getValue());
                	}
                }
            }
            //QC#5580
            scrnMsg.putErrorScreen();
	    }
	}
    
    
    
    /**
     * @param scrnMsg
     * @return
     */
    public static boolean chkDetailListNotEmpty_SERIAL_NO_RG(NMAL0110BMsg scrnMsg) {

        boolean detailListChkFlg = false;
        for (int i = 0, length = scrnMsg.A.getValidCount(); i < length; i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).fromSerNum_A1) && !ZYPCommonFunc.hasValue(scrnMsg.A.no(i).thruSerNum_A1)) {
                continue;
            }
            detailListChkFlg = true;
        }
        return detailListChkFlg;
    }
    public static boolean chkDetailListNotEmpty_PKG_UOM(NMAL0110BMsg scrnMsg) {

        boolean detailListChkFlg = false;
        for (int i = 0, length = scrnMsg.K.getValidCount(); i < length; i++) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.K.no(i).pkgUomCd_K1)) {
                continue;
            }
            detailListChkFlg = true;
        }
        return detailListChkFlg;
    }
    
    public static void validateDate(EZDBDateItem dt, Boolean required, Boolean isFutureToDtAllowed, EZDBMsg scrn, String name) {
        String now = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");
        if (required && dt.isClear()) {
        	//ZZM9000E=0,[@] field is mandatory.
            dt.setErrorInfo(1, "ZZM9000E", new String[] { name });
        }
        if (!isFutureToDtAllowed && !dt.isClear() && dt.getValue().compareTo(now) > 0) {
        	//NMAM8173E=0,[@] cannot be set to a future date
            dt.setErrorInfo(1, "NMAM8173E", new String[] { name });
        }
        scrn.addCheckItem(dt);
        //QC#5580
        //scrn.putErrorScreen();
    }
    
    public static void validateDateRange(EZDBDateItem from, Boolean isFutureFromDtAllowed, EZDBDateItem to, Boolean isFutureToDtAllowed,
            EZDBMsg scrn, String fromName, String toName) {

        String now = ZYPDateUtil.getCurrentSystemTime("yyyyMMdd");// getFormatedDateString(Calendar.getInstance());
        if (from == null || to == null) {
            return;
        }
        if (from.isError()) {
            scrn.addCheckItem(from);
        }
        if (to.isError()) {
            scrn.addCheckItem(to);
        }
        // if to is specified and from date is not given
        if (from.isClear() && !to.isError() && !to.isClear()) {
        	//ZZM9000E=0,[@] field is mandatory.
            from.setErrorInfo(1, "ZZM9000E", new String[] { fromName });
            scrn.addCheckItem(from);
        }
        // if from is specified and to is not given
        if (to.isClear() && !from.isError() && !from.isClear()) {
        	//ZZM9000E=0,[@] field is mandatory.
            to.setErrorInfo(1, "ZZM9000E", new String[] { toName });
            scrn.addCheckItem(to);
        }
        // if from date is set to a future date
        if (from.getValue().compareTo(now) > 0 && !isFutureFromDtAllowed) {
        	//NMAM8173E=0,[@] cannot be set to a future date
            from.setErrorInfo(1, "NMAM8173E", new String[] { fromName });
            scrn.addCheckItem(from);
        }
        // if to date is set to a future date
        if (to.getValue().compareTo(now) > 0 && !isFutureToDtAllowed) {
        	//NMAM8173E=0,[@] cannot be set to a future date
            to.setErrorInfo(1, "NMAM8173E", new String[] {toName });
            scrn.addCheckItem(to);
        }
        // if from is greater than to
        if (from.getValue().compareTo(to.getValue()) > 0) {
        	//NMAM8174E=0,[@] cannot be greater than [@]
            from.setErrorInfo(1, "NMAM8174E", new String[] { fromName, toName });
            //NMAM8174E=0,[@] cannot be greater than [@]
            to.setErrorInfo(1, "NMAM8174E", new String[] { fromName, toName });
            scrn.addCheckItem(from);
            scrn.addCheckItem(to);
        }
        scrn.putErrorScreen();

    }
    
    //QC#10449
    public static String setSelectFromStr(NMAL0110BMsg scrnMsg, String glblCmpyCd, String slsDt) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    RS.EZCANCELFLAG ");
        sb.append("   ,RS.GLBL_CMPY_CD ");
        sb.append("   ,RS.RTL_WH_CD ");
        sb.append("   ,W.RTL_WH_NM ");
        sb.append("   ,RS.RTL_SWH_CD ");
        sb.append("   ,RS.RTL_SWH_NM ");
        sb.append("   ,RS.RTL_WH_CATG_CD ");
        sb.append("   ,C.RTL_WH_CATG_NM ");
        sb.append("FROM ");
        sb.append("    RTL_SWH RS ");
        sb.append("   ,RTL_WH W ");
        sb.append("   ,RTL_WH_CATG C ");
        sb.append("WHERE ");
        sb.append("    RS.EZCANCELFLAG = '0' ");
        sb.append("AND RS.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND RS.EZCANCELFLAG = W.EZCANCELFLAG ");
        sb.append("AND RS.GLBL_CMPY_CD = W.GLBL_CMPY_CD ");
        sb.append("AND RS.RTL_WH_CD = W.RTL_WH_CD ");
        sb.append("AND RS.EZCANCELFLAG = C.EZCANCELFLAG (+) ");
        sb.append("AND RS.GLBL_CMPY_CD = C.GLBL_CMPY_CD (+) ");
        sb.append("AND RS.RTL_WH_CATG_CD = C.RTL_WH_CATG_CD (+)  ");
        sb.append("AND W.EFF_FROM_DT <= '").append(slsDt).append("' ");
        sb.append("AND NVL(W.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("' ");
        sb.append("AND RS.EFF_FROM_DT <= '").append(slsDt).append("' ");
        sb.append("AND NVL(RS.EFF_THRU_DT, '99991231') >= '").append(slsDt).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereList(NMAL0110BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Warehouse";
        whereArray0[1] = "RTL_WH_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcWhCd_H1)) {
            whereArray0[2] = scrnMsg.defSrcWhCd_H1.getValue();
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Sub Warehouse";
        whereArray1[1] = "RTL_SWH_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcSubWhCd_H1)) {
            whereArray1[2] = scrnMsg.defSrcSubWhCd_H1.getValue();
        }
        whereArray1[3] = FLG_OFF_N;
        whereList.add(whereArray1);

        return whereList;
    }
    public static List<Object[]> setTblColumnList(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "WH";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "WH Name";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(21);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Sub WH";
        columnArray2[1] = "RTL_SWH_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = FLG_ON_Y;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Sub WH Name";
        columnArray3[1] = "RTL_SWH_NM";
        columnArray3[2] = BigDecimal.valueOf(21);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "WH Catg";
        columnArray4[1] = "RTL_WH_CATG_CD";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "WH Catg Name";
        columnArray5[1] = "RTL_WH_CATG_NM";
        columnArray5[2] = BigDecimal.valueOf(21);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        return columnList;
    }

    public static List<Object[]> setSortList(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTL_WH_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "RTL_SWH_CD";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }
    
    public static String setSelectForMaterialGroup(NMAL0110BMsg scrnMsg, String glblCmpyCd, String slsMatGrpSqNum) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    MG.EZCANCELFLAG ");
        sb.append("   ,MG.GLBL_CMPY_CD ");
        sb.append("   ,MG.SLS_MAT_GRP_CD ");
        sb.append("   ,MG.SLS_MAT_GRP_DESC_TXT ");
        sb.append("   ,MG.SLS_MAT_GRP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    SLS_MAT_GRP MG ");
        sb.append("WHERE ");
        sb.append("    MG.EZCANCELFLAG = '0' ");
        sb.append("AND MG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND MG.SLS_MAT_GRP_SQ_NUM = ").append(slsMatGrpSqNum).append(" ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForMaterialGroup(String slsMatGrpCd, String slsMatGrpDesctxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Material Group";
        whereArray0[1] = "SLS_MAT_GRP_CD";
        if (ZYPCommonFunc.hasValue(slsMatGrpCd)) {
            whereArray0[2] = slsMatGrpCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "SLS_MAT_GRP_DESC_TXT";
        if (ZYPCommonFunc.hasValue(slsMatGrpDesctxt)) {
            whereArray1[2] = slsMatGrpDesctxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForMaterialGroup(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Material Group";
        columnArray0[1] = "SLS_MAT_GRP_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "SLS_MAT_GRP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForMaterialGroup(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "SLS_MAT_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForCommisionGroup(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    DCG.EZCANCELFLAG ");
        sb.append("   ,DCG.GLBL_CMPY_CD ");
        sb.append("   ,DCG.DS_CMSN_GRP_CD ");
        sb.append("   ,DCG.DS_CMSN_GRP_DESC_TXT ");
        sb.append("   ,DCG.DS_CMSN_GRP_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    DS_CMSN_GRP DCG ");
        sb.append("WHERE ");
        sb.append("    DCG.EZCANCELFLAG = '0' ");
        sb.append("AND DCG.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForCommisionGroup(String dsCmsnGrpCd, String dsCmsnGrpDesctxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Commision Group";
        whereArray0[1] = "DS_CMSN_GRP_CD";
        if (ZYPCommonFunc.hasValue(dsCmsnGrpCd)) {
            whereArray0[2] = dsCmsnGrpCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "DS_CMSN_GRP_DESC_TXT";
        if (ZYPCommonFunc.hasValue(dsCmsnGrpDesctxt)) {
            whereArray1[2] = dsCmsnGrpDesctxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForCommisionGroup(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Commision Group";
        columnArray0[1] = "DS_CMSN_GRP_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "DS_CMSN_GRP_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForCommisionGroup(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "DS_CMSN_GRP_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForZerothProdHerch(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS ZEROTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS ZEROTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS ZEROTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForZerothProdHerch(String zerothProdHrchCd, String zerothProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 1";
        whereArray0[1] = "ZEROTH_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(zerothProdHrchCd)) {
            whereArray0[2] = zerothProdHrchCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(zerothProdHrchDescTxt)) {
            whereArray1[2] = zerothProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForZerothProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 1";
        columnArray0[1] = "ZEROTH_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "ZEROTH_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForZerothProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "ZEROTH_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForFirstProdHerch(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FIRST_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FIRST_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FIRST_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForFirstProdHerch(String firstProdHrchCd, String firstProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 2";
        whereArray0[1] = "FIRST_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(firstProdHrchCd)) {
            whereArray0[2] = firstProdHrchCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "FIRST_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(firstProdHrchDescTxt)) {
            whereArray1[2] = firstProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForFirstProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 2";
        columnArray0[1] = "FIRST_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "FIRST_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForFirstProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "FIRST_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForScdProdHerch(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS SCD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS SCD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS SCD_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForScdProdHerch(String scdProdHrchCd, String scdProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 3";
        whereArray0[1] = "SCD_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(scdProdHrchCd)) {
            whereArray0[2] = scdProdHrchCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "SCD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scdProdHrchDescTxt)) {
            whereArray1[2] = scdProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForScdProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 3";
        columnArray0[1] = "SCD_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "SCD_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForScdProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "SCD_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }
    
    public static String setSelectForThirdProdHerch(NMAL0110BMsg scrnMsg, String glblCmpyCd, String scdProdHrchCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS THIRD_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS THIRD_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS THIRD_PROD_HRCH_SORT_NUM");
        sb.append("     ,PC.SCD_PROD_HRCH_CD");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForThirdProdHerch(String thirdProdHrchCd, String thirdProdHrchDescTxt, String scdProdHrchCd) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 4";
        whereArray0[1] = "THIRD_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(thirdProdHrchCd)) {
            whereArray0[2] = thirdProdHrchCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "THIRD_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(thirdProdHrchDescTxt)) {
            whereArray1[2] = thirdProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        Object[] whereArray2 = new Object[4];
        whereArray2[0] = "Product Level 3";
        whereArray2[1] = "SCD_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(scdProdHrchCd)) {
        	whereArray2[2] = scdProdHrchCd;
        }
        whereArray2[3] = FLG_ON_Y;
        whereList.add(whereArray2);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForThirdProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 4";
        columnArray0[1] = "THIRD_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "THIRD_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForThirdProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "THIRD_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }    
    
    public static String setSelectForFrthProdHerch(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append(" SELECT ");
        sb.append("      PC.EZCANCELFLAG ");
        sb.append("     ,PC.GLBL_CMPY_CD ");
        sb.append("     ,PC.PROD_CTRL_CD AS FRTH_PROD_HRCH_CD");
        sb.append("     ,PC.PROD_CTRL_DESC_TXT AS FRTH_PROD_HRCH_DESC_TXT");
        sb.append("     ,PC.PROD_CTRL_SORT_NUM AS FRTH_PROD_HRCH_SORT_NUM");
        sb.append(" FROM ");
        sb.append("     PROD_CTRL PC ");
        sb.append(" WHERE ");
        sb.append("     PC.EZCANCELFLAG = '0' ");
        sb.append(" AND PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append(" AND PC.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForFrthProdHerch(String frthProdHrchCd, String frthProdHrchDescTxt) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Product Level 5";
        whereArray0[1] = "FRTH_PROD_HRCH_CD";
        if (ZYPCommonFunc.hasValue(frthProdHrchCd)) {
            whereArray0[2] = frthProdHrchCd;
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "FRTH_PROD_HRCH_DESC_TXT";
        if (ZYPCommonFunc.hasValue(frthProdHrchDescTxt)) {
            whereArray1[2] = frthProdHrchDescTxt;
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    
    public static List<Object[]> setTblColumnListForFrthProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Product Level 5";
        columnArray0[1] = "FRTH_PROD_HRCH_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "FRTH_PROD_HRCH_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForFrthProdHerch(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "FRTH_PROD_HRCH_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }        
    public static String setSelectFromStrForMdseTp(NMAL0110BMsg scrnMsg, String glblCmpyCd) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    CP.EZCANCELFLAG ");
        sb.append("   ,CP.GLBL_CMPY_CD ");
        sb.append("   ,CP.COA_PROJ_CD ");
        sb.append("   ,CP.COA_PROJ_DESC_TXT ");
        sb.append("   ,CP.COA_PROJ_SORT_NUM ");
        sb.append("FROM ");
        sb.append("    COA_PROJ CP ");
        sb.append("WHERE ");
        sb.append("    CP.EZCANCELFLAG = '0' ");
        sb.append("AND CP.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        if (ZYPCommonFunc.hasValue(scrnMsg.mdseItemTpCd_H1)) {
	        sb.append("AND EXISTS (SELECT '1' FROM INTG_PROD_CATG_CONV A WHERE A.EZCANCELFLAG = '0' ");
	        sb.append("AND A.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
	        sb.append("AND A.MDSE_ITEM_TP_CD = '").append(scrnMsg.mdseItemTpCd_H1.getValue()).append("' ");
	        sb.append("AND (A.COA_MDSE_TP_CD = CP.COA_PROJ_CD ");
	        sb.append("OR A.COA_MDSE_TP_CD = '*') ");
	        sb.append(")");
        }
        
        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForMdseTp(NMAL0110BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Merchandise Type";
        whereArray0[1] = "COA_PROJ_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaMdseTpCd_H1)) {
            whereArray0[2] = scrnMsg.coaMdseTpCd_H1.getValue();
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "COA_PROJ_DESC_TXT";
        if (ZYPCommonFunc.hasValue(scrnMsg.coaProjDescTxt_H1)) {
            whereArray1[2] = scrnMsg.coaProjDescTxt_H1.getValue();
        }
        whereArray1[3] = FLG_OFF_N;
        whereList.add(whereArray1);

        return whereList;
    }
    public static List<Object[]> setTblColumnListForMdseTp(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Merchandise Type";
        columnArray0[1] = "COA_PROJ_CD";
        columnArray0[2] = BigDecimal.valueOf(20);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "COA_PROJ_DESC_TXT";
        columnArray1[2] = BigDecimal.valueOf(65);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForMdseTp(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "COA_PROJ_SORT_NUM";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        return sortCondList;
    }

    //QC#10449
    public static String setSelectForRtrnCtrl(NMAL0110BMsg scrnMsg, String glblCmpyCd, String slsDt) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    A.EZCANCELFLAG ");
        sb.append("   ,A.GLBL_CMPY_CD ");
        sb.append("   ,A.RTRN_CTRL_TP_VND_RELN_PK ");
        sb.append("   ,A.RTRN_CTRL_TP_CD ");
        sb.append("   ,C.RTRN_CTRL_TP_NM ");
        sb.append("   ,B.PRNT_VND_CD ");
        sb.append("   ,B.PRNT_VND_NM ");
        sb.append("   ,A.VND_CD ");
        sb.append("   ,B.VND_NM ");
        sb.append("   ,A.RTRN_WH_CD ");
        sb.append("   ,D.RTL_WH_NM RTRN_WH_NM ");
        sb.append("FROM ");
        sb.append("    RTRN_CTRL_TP_VND_RELN A ");
        sb.append("   ,PO_VND_V B ");
        sb.append("   ,RTRN_CTRL_TP C ");
        sb.append("   ,RTL_WH D ");
        sb.append("WHERE ");
        sb.append("    A.EZCANCELFLAG = '0' ");
        sb.append("AND A.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
        sb.append("AND A.EZCANCELFLAG = B.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = B.GLBL_CMPY_CD (+) ");
        sb.append("AND A.VND_CD = B.VND_CD (+) ");
        sb.append("AND A.EZCANCELFLAG = C.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = C.GLBL_CMPY_CD (+) ");
        sb.append("AND A.RTRN_CTRL_TP_CD = C.RTRN_CTRL_TP_CD (+) ");
        sb.append("AND A.EZCANCELFLAG = D.EZCANCELFLAG (+) ");
        sb.append("AND A.GLBL_CMPY_CD = D.GLBL_CMPY_CD (+) ");
        sb.append("AND A.RTRN_WH_CD = D.RTL_WH_CD (+) ");
        sb.append("AND D.EFF_FROM_DT(+) <= '").append(slsDt).append("' ");
        sb.append("AND D.EFF_THRU_DT(+) >= '").append(slsDt).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForRtrnCtrl(NMAL0110BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();

        Object[] whereArray0 = new Object[4];
        whereArray0[0] = "Return Ctrl";
        whereArray0[1] = "RTRN_CTRL_TP_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpCd_H1)) {
            whereArray0[2] = scrnMsg.rtrnCtrlTpCd_H1.getValue();
        }
        whereArray0[3] = FLG_OFF_N;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Return Ctrl Nm";
        whereArray1[1] = "RTRN_CTRL_TP_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.rtrnCtrlTpNm_H1)) {
            whereArray1[2] = scrnMsg.rtrnCtrlTpNm_H1.getValue();
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);
        
        return whereList;
    }
    public static List<Object[]> setTblColumnListForRtrnCtrl(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray00 = new Object[4];
        columnArray00[0] = "ID";
        columnArray00[1] = "RTRN_CTRL_TP_VND_RELN_PK";
        columnArray00[2] = BigDecimal.valueOf(5);
        columnArray00[3] = FLG_OFF_N;
        columnList.add(columnArray00);
        
        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Rtrn Ctrl";
        columnArray0[1] = "RTRN_CTRL_TP_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Rtrn Ctrl Nm";
        columnArray1[1] = "RTRN_CTRL_TP_NM";
        columnArray1[2] = BigDecimal.valueOf(21);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        Object[] columnArray2 = new Object[4];
        columnArray2[0] = "Rtrn Vnd";
        columnArray2[1] = "PRNT_VND_CD";
        columnArray2[2] = BigDecimal.valueOf(10);
        columnArray2[3] = FLG_OFF_N;
        columnList.add(columnArray2);

        Object[] columnArray3 = new Object[4];
        columnArray3[0] = "Rtrn Vnd Nm";
        columnArray3[1] = "PRNT_VND_NM";
        columnArray3[2] = BigDecimal.valueOf(21);
        columnArray3[3] = FLG_OFF_N;
        columnList.add(columnArray3);

        Object[] columnArray4 = new Object[4];
        columnArray4[0] = "Rtrn Vnd Site";
        columnArray4[1] = "VND_CD";
        columnArray4[2] = BigDecimal.valueOf(10);
        columnArray4[3] = FLG_OFF_N;
        columnList.add(columnArray4);

        Object[] columnArray5 = new Object[4];
        columnArray5[0] = "Rtrn Vnd Site Nm";
        columnArray5[1] = "VND_NM";
        columnArray5[2] = BigDecimal.valueOf(21);
        columnArray5[3] = FLG_OFF_N;
        columnList.add(columnArray5);

        Object[] columnArray6 = new Object[4];
        columnArray6[0] = "Rtrn WH";
        columnArray6[1] = "RTRN_WH_CD";
        columnArray6[2] = BigDecimal.valueOf(10);
        columnArray6[3] = FLG_OFF_N;
        columnList.add(columnArray6);

        Object[] columnArray7 = new Object[4];
        columnArray7[0] = "Rtrn WH Nm";
        columnArray7[1] = "RTRN_WH_NM";
        columnArray7[2] = BigDecimal.valueOf(21);
        columnArray7[3] = FLG_OFF_N;
        columnList.add(columnArray7);

        return columnList;
    }

    public static List<Object[]> setSortListForRtrnCtrl(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTRN_CTRL_TP_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "VND_CD";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }
    
    //QC#10449
    public static String setSelectFromStrForDefSrcWh(NMAL0110BMsg scrnMsg, String glblCmpyCd, String slsDt) {

        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        sb.append("SELECT ");
        sb.append("    W.EZCANCELFLAG ");
        sb.append("   ,W.GLBL_CMPY_CD ");
        sb.append("   ,W.RTL_WH_CD ");
        sb.append("   ,W.RTL_WH_NM ");
        sb.append("FROM ");
        sb.append("    RTL_WH W ");
        sb.append("WHERE ");
        sb.append("    W.EZCANCELFLAG = '0' ");
        sb.append("AND W.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");

        return sb.toString();
    }
	
    public static List<Object[]> setWhereListForDefSrcWh(NMAL0110BMsg scrnMsg) {

        List<Object[]> whereList = new ArrayList<Object[]>();
        Object[] whereArray0 = new Object[4];

        whereArray0[0] = "Warehouse";
        whereArray0[1] = "RTL_WH_CD";
        if (ZYPCommonFunc.hasValue(scrnMsg.defSrcWhCd_H1)) {
            whereArray0[2] = scrnMsg.defSrcWhCd_H1.getValue();
        }
        whereArray0[3] = FLG_ON_Y;
        whereList.add(whereArray0);

        Object[] whereArray1 = new Object[4];
        whereArray1[0] = "Description";
        whereArray1[1] = "RTL_WH_NM";
        if (ZYPCommonFunc.hasValue(scrnMsg.locNm_DW)) {
            whereArray1[2] = scrnMsg.locNm_DW.getValue();
        }
        whereArray1[3] = FLG_ON_Y;
        whereList.add(whereArray1);

        return whereList;
    }
    public static List<Object[]> setTblColumnListForDefSrcWh(NMAL0110BMsg scrnMsg) {

        List<Object[]> columnList = new ArrayList<Object[]>();

        Object[] columnArray0 = new Object[4];
        columnArray0[0] = "Warehouse";
        columnArray0[1] = "RTL_WH_CD";
        columnArray0[2] = BigDecimal.valueOf(10);
        columnArray0[3] = FLG_ON_Y;
        columnList.add(columnArray0);

        Object[] columnArray1 = new Object[4];
        columnArray1[0] = "Description";
        columnArray1[1] = "RTL_WH_NM";
        columnArray1[2] = BigDecimal.valueOf(21);
        columnArray1[3] = FLG_OFF_N;
        columnList.add(columnArray1);

        return columnList;
    }

    public static List<Object[]> setSortListForDefSrcWh(NMAL0110BMsg scrnMsg) {

        List<Object[]> sortCondList = new ArrayList<Object[]>();

        Object[] sortCondArray0 = new Object[2];
        sortCondArray0[0] = "RTL_WH_CD";
        sortCondArray0[1] = "";
        sortCondList.add(sortCondArray0);

        Object[] sortCondArray1 = new Object[2];
        sortCondArray1[0] = "RTL_WH_NM";
        sortCondArray1[1] = "";
        sortCondList.add(sortCondArray1);

        
        return sortCondList;
    }
}

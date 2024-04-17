package business.blap.NMAL0110;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import parts.common.EZDTMsg;
import business.blap.NMAL0110.common.NMAL0110CommonLogic;
import business.blap.NMAL0110.constant.NMAL0110Constant;
import business.db.FRT_CLSTMsg;
import business.db.HAZ_MATTMsg;
import business.db.PKG_UOMTMsg;
import business.db.COA_PROJTMsg;
import business.db.SVC_PGM_TPTMsg;
import business.file.NMAL0110F00FMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.BACK_ORD_IMPCT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_ACCTG_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DFRD_INV_RULE;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DROP_RTRN_VND;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.EASY_PACK_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HARD_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.HAZ_MAT;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_COLOR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_OEM_MNF;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_PVT_LB_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_YIELD_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.IMG_SPLY_YIELD_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LINE_BIZ_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_BILL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_CLS_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_RELN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_PGM_TP;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_PRC_GRP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_RGTN_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PKG_UOM_CLS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRT_ITEM_TP;
//import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTRN_DSPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_ALLOC_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_CHRG_ITEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_COV_BASE_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_COV_TMPL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SVC_WTY_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_LIC_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_MAINT_CTRL_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_MAINT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.SW_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TAX_EXEM_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.TERM_COND_OPT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.DS_INTG_MDSE_TP;
import com.canon.cusa.s21.framework.ZYP.dataaccess.ZYPCodeDataUtil;
import com.canon.cusa.s21.framework.ZYP.file.ZYPCSVOutFile;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.workflow.core.context.S21WfHumanTaskExecutionContext;
import com.canon.cusa.s21.framework.workflow.ezd.business.S21WfBusinessHandlerBL02Support;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 03/12/2015   SRAA            K.Aratani       Create
 * 07/03/2015   Fujitsu         C.Tanaka        Update
 * 10/02/2015   SRAA            K.Aratani       Update
 * 02/09/2016   SRAA            Y.Chen          Update          QC#3579
 * 06/16/2016   SRAA            K.Aratani       Update          QC#6748,9891,9916,9970
 * 01/17/2017   SRAA            K.Aratani       Update          QC#17184
 * 08/24/2017   CITS            T.Kikuhara      Update          QC#18365(L3)
 * 09/25/2017   Fujitsu         T.Aoi           Update          QC#18534(L3#445)
 * 11/21/2018   Fujitsu         Mz.Takahashi    Update          QC#29168
 * 2019/10/18   Fujitsu         C.Hara          Update          QC#53815
 * 2019/10/28   Fujitsu         K.Kato          Update          QC#53741
 * 2022/02/17   Fujitsu         C.Hara          Update          QC#59693
 * 2023/09/05   Hitachi         K.Watanabe      Update          QC#53408
 * 2023/12/12   Hitachi         K.Watanabe      Update          QC#61300
 *</pre>
 */
public class NMAL0110BL02 extends S21WfBusinessHandlerBL02Support implements NMAL0110Constant {

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg, S21WfHumanTaskExecutionContext wfExecCtx) {
        super.preDoProcess(cMsg, sMsg);

        try {
            NMAL0110CMsg bizMsg = (NMAL0110CMsg) cMsg;
            String scrnAplID = bizMsg.getScreenAplID();
            String tabName = bizMsg.xxDplyTab_H1.getValue();
            // Header Event
            if ("NMAL0110_INIT".equals(scrnAplID)) {
                doProcess_NMAL0110_INIT(bizMsg, sMsg);
            } else if ("NMAL0110Scrn00_Search_Item".equals(scrnAplID)) {
                NMAL0110CommonLogic.clearValue(getGlobalCompanyCode(), getUserProfileService(), bizMsg, false);
                // search merchandise info
                NMAL0110CommonLogic.search(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue(), false);
            } else if ("NMAL0110Scrn00_CMN_Reset".equals(scrnAplID)) {
                doProcess_NMAL0110_RESET(bizMsg, sMsg);
            } else if ("NMAL0110Scrn00_CMN_Clear".equals(scrnAplID)) {
            	doProcess_NMAL0110_CLEAR(bizMsg, sMsg);
            //} else if ("NMAL0110_NMAL6810".equals(scrnAplID)) {
            //    doProcess_NMAL0110_NMAL6810(bizMsg, sMsg);
            } else if ("NMAL0110Scrn00_Change_ItemTp_Pulldown".equals(scrnAplID)) {
                doProcess_Change_ItemTp_Pulldown(bizMsg, sMsg);
            // 2017/09/25 QC#18534(L3#445) ADD Start
            } else if ("NMAL0110Scrn00_Change_svcPgmTpCd_Pulldown".equals(scrnAplID)) {
                doProcess_Change_svcPgmTpCd_Pulldown(bizMsg, sMsg);
            // 2017/09/25 QC#18534(L3#445) ADD End
            } else if ("NMAL0110_NMAL6050".equals(scrnAplID)) {
                doProcess_NMAL0110_NMAL6050(bizMsg, sMsg);
            // QC#3579
            } else if ("NMAL0110Scrn00_Download_Item_History".equals(scrnAplID)) {
                doProcess_Download_Item_History(bizMsg, sMsg);
            } else if ("NMAL0110_NWAL1130".equals(scrnAplID)) {
                doProcess_Product_Line_level_3_Link(bizMsg, sMsg);
            } else if ("NMAL0110Scrn00_CopyItem".equals(scrnAplID)) {
                doProcess_CopyItem(bizMsg, sMsg);
            } else if ("NMAL0110Scrn00_OnBlur_CoaMdseTpCd".equals(scrnAplID)) {
                doProcess_OnBlur_CoaMdseTpCd(bizMsg, sMsg);
                
            // Detail Event
            } else {
                // General Tab
                if (TAB_GENERAL.equals(tabName)) {
                    if ("NMAL0110Scrn00_Change_HazMatCd_Pulldown".equals(scrnAplID)) {
                        doProcess_Change_HazMatCd_Pulldown(bizMsg, sMsg);
                    } else if ("NMAL0110Scrn00_Add_PkgType_SellingUOM".equals(scrnAplID)) {
                        doProcess_Add_PkgType_SellingUOM(bizMsg, sMsg);
                    } else if ("NMAL0110Scrn00_Del_PkgType_Selling".equals(scrnAplID)) {
                        doProcess_Del_PkgType_Selling(bizMsg, sMsg);
                    } else if ("NMAL0110Scrn00_Change_PkgUomClsCd_Pulldown".equals(scrnAplID)) {
                        doProcess_Change_PkgUomClsCd_Pulldown(bizMsg, sMsg);
                    } else if ("NMAL0110Scrn00_Add_TermCondOpt".equals(scrnAplID)) {
                        doProcess_Add_TermCondOpt(bizMsg, sMsg);
                    } else if ("NMAL0110Scrn00_Del_TermCondOpt".equals(scrnAplID)) {
                        doProcess_Del_TermCondOpt(bizMsg, sMsg);
                    }
                }else if (TAB_INVENTORY.equals(tabName)) {
                	//QC#4203
                    //if ("NMAL0110Scrn00_Change_rtrnCtrlTpCd_Pulldown".equals(scrnAplID)) {
                    //	doProcess_Change_rtrnCtrlTpCd_Pulldown(bizMsg, sMsg);
                    //}
                }else if (TAB_FIELD_SERVICE.equals(tabName)) {
                    if ("NMAL0110Scrn00_ViewServiceModel".equals(scrnAplID)) {
                        doProcess_ViewServiceModel(bizMsg, sMsg);
                    }
                }
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }
    
    private void doProcess_NMAL0110_INIT(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        
        //################
        //Pull down setup
        //################
        //MANUFACTURER
        // 2019/10/18 QC#53815 Mod Start
        // bizMsg.mdseItemMnfCd_H1.clear();
        // bizMsg.mdseItemMnfCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseItemMnfNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_MNF.class, bizMsg.mdseItemMnfCd_L1, bizMsg.mdseItemMnfNm_L1);
        // bizMsg.mdseItemMnfDescTxt_L1.clear();
        // ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_MNF.class, bizMsg.mdseItemMnfCd_L1, bizMsg.mdseItemMnfDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End
        // 2019/10/18 QC#53815 Mod End

        //STATUS
        bizMsg.mdseItemStsCd_H1.clear();
        bizMsg.mdseItemStsCd_L1.clear();
        bizMsg.mdseItemStsNm_L1.clear();
        NMAL0110CommonLogic.setInitialItemStsPulldown(bizMsg, getGlobalCompanyCode());
        
        //REGISTRATION MODE
        bizMsg.mdseRgtnTpCd_H1.clear();
        bizMsg.mdseRgtnTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseRgtnTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_RGTN_TP.class, bizMsg.mdseRgtnTpCd_L1, bizMsg.mdseRgtnTpNm_L1);
        bizMsg.mdseRgtnTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_RGTN_TP.class, bizMsg.mdseRgtnTpCd_L1, bizMsg.mdseRgtnTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //ITEM TYPE
        bizMsg.mdseItemTpCd_H1.clear();
        bizMsg.mdseItemTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseItemTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpNm_L1);
        bizMsg.mdseItemTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_TP.class, bizMsg.mdseItemTpCd_L1, bizMsg.mdseItemTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //ITEM CLASSIFICATION
        bizMsg.mdseItemClsTpCd_H1.clear();
        bizMsg.mdseItemClsTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseItemClsTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class, bizMsg.mdseItemClsTpCd_L1, bizMsg.mdseItemClsTpNm_L1);
        bizMsg.mdseItemClsTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_CLS_TP.class, bizMsg.mdseItemClsTpCd_L1, bizMsg.mdseItemClsTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

//        //MERCHANDISE TYPE
//        bizMsg.coaMdseTpCd_H1.clear();
//        bizMsg.coaMdseTpCd_L1.clear();
//        bizMsg.coaMdseTpNm_L1.clear();
//        //ZYPCodeDataUtil.createPulldownList(COA_PROJ.class, bizMsg.coaMdseTpCd_L1, bizMsg.coaMdseTpNm_L1, PULLDOWN_DELIMITER);
//        NMAL0110CommonLogic.setCoaMdseTpPulldown(bizMsg, getGlobalCompanyCode());
                
        //PLANNING GROUP
        bizMsg.prchGrpCd_H1.clear();
        bizMsg.prchGrpCd_L1.clear();
        bizMsg.prchGrpNm_L1.clear();
        NMAL0110CommonLogic.setInitialPlanningGroupPulldown(bizMsg, getGlobalCompanyCode());
        
//        //PRICING GROUP
//        bizMsg.mdsePrcGrpCd_H1.clear();
//        bizMsg.mdsePrcGrpCd_L1.clear();
//        bizMsg.mdsePrcGrpDescTxt_L1.clear();
//        ZYPCodeDataUtil.createPulldownList(MDSE_PRC_GRP.class, bizMsg.mdsePrcGrpCd_L1, bizMsg.mdsePrcGrpDescTxt_L1);

        //Intangible Type
        bizMsg.dsIntgMdseTpCd_H1.clear();
        bizMsg.dsIntgMdseTpCd_L1.clear();
        bizMsg.dsIntgMdseTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(DS_INTG_MDSE_TP.class, bizMsg.dsIntgMdseTpCd_L1, bizMsg.dsIntgMdseTpDescTxt_L1);
        
        
        //PRODUCT LEVEL 1 - 5 NAME
        NMAL0110CommonLogic.setProductLevelName(bizMsg, getGlobalCompanyCode());
        
        //CRITICALITY
        bizMsg.backOrdImpctTpCd_H1.clear();
        bizMsg.backOrdImpctTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.backOrdImpctTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(BACK_ORD_IMPCT_TP.class, bizMsg.backOrdImpctTpCd_L1, bizMsg.backOrdImpctTpNm_L1);
        bizMsg.backOrdImpctTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(BACK_ORD_IMPCT_TP.class, bizMsg.backOrdImpctTpCd_L1, bizMsg.backOrdImpctTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //SUPPLY OEM MANUFACTUER
        bizMsg.imgSplyOemMnfCd_H1.clear();
        bizMsg.imgSplyOemMnfCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.imgSplyOemMnfNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(IMG_SPLY_OEM_MNF.class, bizMsg.imgSplyOemMnfCd_L1, bizMsg.imgSplyOemMnfNm_L1);
        bizMsg.imgSplyOemMnfDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_OEM_MNF.class, bizMsg.imgSplyOemMnfCd_L1, bizMsg.imgSplyOemMnfDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //SUPPLY TYPE
        bizMsg.imgSplyTpCd_H1.clear();
        bizMsg.imgSplyTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.imgSplyTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(IMG_SPLY_TP.class, bizMsg.imgSplyTpCd_L1, bizMsg.imgSplyTpNm_L1);
        bizMsg.imgSplyTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_TP.class, bizMsg.imgSplyTpCd_L1, bizMsg.imgSplyTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Supply Color
        bizMsg.imgSplyColorTpCd_H1.clear();
        bizMsg.imgSplyColorTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.imgSplyColorTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(IMG_SPLY_COLOR_TP.class, bizMsg.imgSplyColorTpCd_L1, bizMsg.imgSplyColorTpNm_L1);
        bizMsg.imgSplyColorTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_COLOR_TP.class, bizMsg.imgSplyColorTpCd_L1, bizMsg.imgSplyColorTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Supply Yield UOM
        bizMsg.imgSplyYieldUomCd_H1.clear();
        bizMsg.imgSplyYieldUomCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.imgSplyYieldUomNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(IMG_SPLY_YIELD_UOM.class, bizMsg.imgSplyYieldUomCd_L1, bizMsg.imgSplyYieldUomNm_L1);
        bizMsg.imgSplyYieldUomDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_YIELD_UOM.class, bizMsg.imgSplyYieldUomCd_L1, bizMsg.imgSplyYieldUomDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Supply Yield Type
        bizMsg.imgSplyYieldTpCd_H1.clear();
        bizMsg.imgSplyYieldTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.imgSplyYieldTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(IMG_SPLY_YIELD_TP.class, bizMsg.imgSplyYieldTpCd_L1, bizMsg.imgSplyYieldTpNm_L1);
        bizMsg.imgSplyYieldTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_YIELD_TP.class, bizMsg.imgSplyYieldTpCd_L1, bizMsg.imgSplyYieldTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Easy Pack Type
        bizMsg.easyPackTpCd_H1.clear();
        bizMsg.easyPackTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.easyPackTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(EASY_PACK_TP.class, bizMsg.easyPackTpCd_L1, bizMsg.easyPackTpNm_L1);
        bizMsg.easyPackTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(EASY_PACK_TP.class, bizMsg.easyPackTpCd_L1, bizMsg.easyPackTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Service Coverage Template
        bizMsg.svcCovTmplTpCd_H1.clear();
        bizMsg.svcCovTmplTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.svcCovTmplTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SVC_COV_TMPL_TP.class, bizMsg.svcCovTmplTpCd_L1, bizMsg.svcCovTmplTpNm_L1);
        bizMsg.svcCovTmplTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_COV_TMPL_TP.class, bizMsg.svcCovTmplTpCd_L1, bizMsg.svcCovTmplTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End
        
        //Service Allocation Type
        bizMsg.svcAllocTpCd_H1.clear();
        bizMsg.svcAllocTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.svcAllocTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SVC_ALLOC_TP.class, bizMsg.svcAllocTpCd_L1, bizMsg.svcAllocTpNm_L1);
        bizMsg.svcAllocTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_ALLOC_TP.class, bizMsg.svcAllocTpCd_L1, bizMsg.svcAllocTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        // 2017/09/25 QC#18534(L3#445) ADD Start
        //Service Program Type
        bizMsg.svcPgmTpCd_H1.clear();
        bizMsg.svcPgmTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.svcPgmTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SVC_PGM_TP.class, bizMsg.svcPgmTpCd_L1, bizMsg.svcPgmTpNm_L1);
        bizMsg.svcPgmTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_PGM_TP.class, bizMsg.svcPgmTpCd_L1, bizMsg.svcPgmTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End
        // 2017/09/25 QC#18534(L3#445) ADD End
        
        // Service Charge Type
        bizMsg.svcChrgItemTpCd_H1.clear();
        bizMsg.svcChrgItemTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.svcChrgItemTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SVC_CHRG_ITEM_TP.class, bizMsg.svcChrgItemTpCd_L1, bizMsg.svcChrgItemTpNm_L1);
        bizMsg.svcChrgItemTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_CHRG_ITEM_TP.class, bizMsg.svcChrgItemTpCd_L1, bizMsg.svcChrgItemTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Software Maintenance Controlled
        bizMsg.swMaintCtrlTpCd_H1.clear();
        bizMsg.swMaintCtrlTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.swMaintCtrlTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SW_MAINT_CTRL_TP.class, bizMsg.swMaintCtrlTpCd_L1, bizMsg.swMaintCtrlTpNm_L1);
        bizMsg.swMaintCtrlTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SW_MAINT_CTRL_TP.class, bizMsg.swMaintCtrlTpCd_L1, bizMsg.swMaintCtrlTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Software Maintenance Type
        bizMsg.swMaintTpCd_H1.clear();
        bizMsg.swMaintTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.swMaintTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SW_MAINT_TP.class, bizMsg.swMaintTpCd_L1, bizMsg.swMaintTpNm_L1);
        bizMsg.swMaintTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SW_MAINT_TP.class, bizMsg.swMaintTpCd_L1, bizMsg.swMaintTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //SOFTWARE CATEGORY
        bizMsg.swCatgCd_H1.clear();
        bizMsg.swCatgCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.swCatgNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SW_CATG.class, bizMsg.swCatgCd_L1, bizMsg.swCatgNm_L1);
        bizMsg.swCatgDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SW_CATG.class, bizMsg.swCatgCd_L1, bizMsg.swCatgDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //SOFTWARE TYPE
        bizMsg.swTpCd_H1.clear();
        bizMsg.swTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.swTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SW_TP.class, bizMsg.swTpCd_L1, bizMsg.swTpNm_L1);
        bizMsg.swTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SW_TP.class, bizMsg.swTpCd_L1, bizMsg.swTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //LICENSE CONTROL
        bizMsg.swLicCtrlTpCd_H1.clear();
        bizMsg.swLicCtrlTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.swLicCtrlTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SW_LIC_CTRL_TP.class, bizMsg.swLicCtrlTpCd_L1, bizMsg.swLicCtrlTpNm_L1);
        bizMsg.swLicCtrlTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SW_LIC_CTRL_TP.class, bizMsg.swLicCtrlTpCd_L1, bizMsg.swLicCtrlTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //HAZARDOUS NUMBER
        bizMsg.hazMatCd_H1.clear();
        bizMsg.hazMatCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.hazMatNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(HAZ_MAT.class, bizMsg.hazMatCd_L1, bizMsg.hazMatNm_L1);
        bizMsg.hazMatDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(HAZ_MAT.class, bizMsg.hazMatCd_L1, bizMsg.hazMatDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //DEFAULT SOURCE TYPE
        bizMsg.defSrcProcrTpCd_H1.clear();
        bizMsg.procrTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.procrTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, bizMsg.procrTpCd_L1, bizMsg.procrTpNm_L1);
        bizMsg.procrTpDescTxt_L1.clear();
        // 2022/02/17 QC#59693 Mod Start
//        ZYPCodeDataUtil.createPulldownList(PROCR_TP.class, bizMsg.procrTpCd_L1, bizMsg.procrTpDescTxt_L1);
//        // 2018/11/21 QC#29168 MOD End
        NMAL0110CommonLogic.getPullDownDataSourceType(bizMsg, getGlobalCompanyCode());
        // 2022/02/17 QC#59693 Mod End

        //HARD ALLOCATION TYPE
        bizMsg.invtyHardAllocTpCd_H1.clear();
        bizMsg.hardAllocTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.hardAllocTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(HARD_ALLOC_TP.class, bizMsg.hardAllocTpCd_L1, bizMsg.hardAllocTpNm_L1);
        bizMsg.hardAllocTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(HARD_ALLOC_TP.class, bizMsg.hardAllocTpCd_L1, bizMsg.hardAllocTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //QC#4203
        //RETURN CONTROL TYPE
        //bizMsg.rtrnCtrlTpCd_H1.clear();
        //bizMsg.rtrnCtrlTpCd_L1.clear();
        //bizMsg.rtrnCtrlTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(RTRN_CTRL_TP.class, bizMsg.rtrnCtrlTpCd_L1, bizMsg.rtrnCtrlTpNm_L1);
        
        //QC#18365 ADD START
        //DROP RMA
        bizMsg.dropRtrnVndCd_H1.clear();
        bizMsg.dropRtrnVndCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.dropRtrnVndNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(DROP_RTRN_VND.class, bizMsg.dropRtrnVndCd_L1, bizMsg.dropRtrnVndNm_L1);
        bizMsg.dropRtrnVndDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(DROP_RTRN_VND.class, bizMsg.dropRtrnVndCd_L1, bizMsg.dropRtrnVndDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //QC#18365 ADD END
        //RETURN DISPOSITION
        bizMsg.rtrnDsplTpCd_H1.clear();
        bizMsg.rtrnDsplTpCd_BK.clear();
        bizMsg.rtrnDsplTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.rtrnDsplTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(RTRN_DSPL_TP.class, bizMsg.rtrnDsplTpCd_L1, bizMsg.rtrnDsplTpNm_L1);
        bizMsg.rtrnDsplTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(RTRN_DSPL_TP.class, bizMsg.rtrnDsplTpCd_L1, bizMsg.rtrnDsplTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End
        
        //ACCOUNTING RULES
        bizMsg.dfrdAcctgRuleCd_H1.clear();
        bizMsg.dfrdAcctgRuleCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.dfrdAcctgRuleNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(DFRD_ACCTG_RULE.class, bizMsg.dfrdAcctgRuleCd_L1, bizMsg.dfrdAcctgRuleNm_L1);
        bizMsg.dfrdAcctgRuleDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(DFRD_ACCTG_RULE.class, bizMsg.dfrdAcctgRuleCd_L1, bizMsg.dfrdAcctgRuleDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End
        
        //INVOICING RULES
        bizMsg.dfrdInvRuleCd_H1.clear();
        bizMsg.dfrdInvRuleCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.dfrdInvRuleNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(DFRD_INV_RULE.class, bizMsg.dfrdInvRuleCd_L1, bizMsg.dfrdInvRuleNm_L1);
        bizMsg.dfrdInvRuleDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(DFRD_INV_RULE.class, bizMsg.dfrdInvRuleCd_L1, bizMsg.dfrdInvRuleDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //TAX CODE
        bizMsg.taxExemTpCd_H1.clear();
        bizMsg.taxExemTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.taxExemTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(TAX_EXEM_TP.class, bizMsg.taxExemTpCd_L1, bizMsg.taxExemTpNm_L1);
        bizMsg.taxExemTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(TAX_EXEM_TP.class, bizMsg.taxExemTpCd_L1, bizMsg.taxExemTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //RELATIONSHIP
        bizMsg.mdseItemRelnTpCd_DL.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseItemRelnTpNm_DL.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_RELN_TP.class, bizMsg.mdseItemRelnTpCd_DL, bizMsg.mdseItemRelnTpNm_DL);
        bizMsg.mdseItemRelnTpDescTxt_DL.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_RELN_TP.class, bizMsg.mdseItemRelnTpCd_DL, bizMsg.mdseItemRelnTpDescTxt_DL);
        // 2018/11/21 QC#29168 MOD End
                
        //SVC_WTY_TP_CD
        bizMsg.svcWtyTpCd_H1.clear();
        bizMsg.svcWtyTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.svcWtyTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(SVC_WTY_TP.class, bizMsg.svcWtyTpCd_L1, bizMsg.svcWtyTpNm_L1);
        bizMsg.svcWtyTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_WTY_TP.class, bizMsg.svcWtyTpCd_L1, bizMsg.svcWtyTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //ITEM BILLING TYPE
        bizMsg.mdseItemBillTpCd_H1.clear();
        bizMsg.mdseItemBillTpCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.mdseItemBillTpNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_BILL_TP.class, bizMsg.mdseItemBillTpCd_L1, bizMsg.mdseItemBillTpNm_L1);
        bizMsg.mdseItemBillTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(MDSE_ITEM_BILL_TP.class, bizMsg.mdseItemBillTpCd_L1, bizMsg.mdseItemBillTpDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        // START 2023/09/05 K.Watanabe [QC#53408, ADD]
        //SERVICE INSTALL RULE
        bizMsg.svcIstlRuleNum_H1.clear();
        bizMsg.svcIstlRuleNum_L1.clear();
        bizMsg.svcIstlRuleNm_L1.clear();
        NMAL0110CommonLogic.setSvcIstlRulePulldown(bizMsg, getGlobalCompanyCode());
        //ZYPCodeDataUtil.createPulldownList(SVC_ISTL_RULE.class, bizMsg.svcIstlRuleNum_L1, bizMsg.svcIstlRuleNm_L1);

        //SERVICE INSTALL CALL GROUP
        bizMsg.svcIstlCallGrpNum_H1.clear();
        bizMsg.svcIstlCallGrpNum_L1.clear();
        bizMsg.svcIstlCallGrpNm_L1.clear();
        NMAL0110CommonLogic.setSvcIstlCallGrpPulldown(bizMsg, getGlobalCompanyCode());
        //ZYPCodeDataUtil.createPulldownList(SVC_ISTL_CALL_GRP.class, bizMsg.svcIstlCallGrpNum_L1, bizMsg.svcIstlCallGrpNm_L1);
        // END 2023/09/05 K.Watanabe [QC#53408, ADD]

        // START 2023/12/12 K.Watanabe [QC#61300, ADD]
        //SERVICE DEINSTALL RULE
        bizMsg.svcDeinsRuleNum_H1.clear();
        bizMsg.svcDeinsRuleNum_L1.clear();
        bizMsg.xxSvcDeinsRuleNm_L1.clear();
        NMAL0110CommonLogic.setSvcDeinsRulePulldown(bizMsg, getGlobalCompanyCode());

        //SERVICE DEINSTALL CALL GROUP
        bizMsg.svcDeinsCallGrpNum_H1.clear();
        bizMsg.svcDeinsCallGrpNum_L1.clear();
        bizMsg.xxSvcDeinsCallGrpNm_L1.clear();
        NMAL0110CommonLogic.setSvcDeinsCallGrpPulldown(bizMsg, getGlobalCompanyCode());
        // END 2023/12/12 K.Watanabe [QC#61300, ADD]

        //UNIT OF MEASURE CLASS
        bizMsg.pkgUomClsCd_H1.clear();
        bizMsg.pkgUomClsCd_BK.clear();
        bizMsg.pkgUomClsCd_L1.clear();
        // 2018/11/21 QC#29168 MOD Start
        //bizMsg.pkgUomClsNm_L1.clear();
        //ZYPCodeDataUtil.createPulldownList(PKG_UOM_CLS.class, bizMsg.pkgUomClsCd_L1, bizMsg.pkgUomClsNm_L1);
        bizMsg.pkgUomClsDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(PKG_UOM_CLS.class, bizMsg.pkgUomClsCd_L1, bizMsg.pkgUomClsDescTxt_L1);
        // 2018/11/21 QC#29168 MOD End

        //Part Type
        bizMsg.prtItemTpCd_H1.clear();
        bizMsg.prtItemTpCd_L1.clear();
        bizMsg.prtItemTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(PRT_ITEM_TP.class, bizMsg.prtItemTpCd_L1, bizMsg.prtItemTpDescTxt_L1);
        
        //Software Product Category
        bizMsg.termCondOptTpCd_L1.clear();
        bizMsg.termCondOptTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(TERM_COND_OPT_TP.class, bizMsg.termCondOptTpCd_L1, bizMsg.termCondOptTpDescTxt_L1);
        
        //Coverage Base Type
        bizMsg.svcCovBaseTpCd_H1.clear();
        bizMsg.svcCovBaseTpCd_L1.clear();
        bizMsg.svcCovBaseTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(SVC_COV_BASE_TP.class, bizMsg.svcCovBaseTpCd_L1, bizMsg.svcCovBaseTpDescTxt_L1);
        
        //Line Business Type
        bizMsg.lineBizTpCd_H1.clear();
        bizMsg.lineBizTpCd_L1.clear();
        bizMsg.lineBizTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(LINE_BIZ_TP.class, bizMsg.lineBizTpCd_L1, bizMsg.lineBizTpDescTxt_L1);
        
        //Private Label Flag
        bizMsg.imgSplyPvtLbTpCd_H1.clear();
        bizMsg.imgSplyPvtLbTpCd_L1.clear();
        bizMsg.imgSplyPvtLbTpDescTxt_L1.clear();
        ZYPCodeDataUtil.createPulldownList(IMG_SPLY_PVT_LB_TP.class, bizMsg.imgSplyPvtLbTpCd_L1, bizMsg.imgSplyPvtLbTpDescTxt_L1);

        // 2019/10/28 QC#53741 Add Start
        NMAL0110CommonLogic.setDimensionsUOM(bizMsg, getGlobalCompanyCode());
        // 2019/10/28 QC#53741 Add End

        // if screen parameter exists
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_SC)) {
            // search merchandise info
            NMAL0110CommonLogic.search(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_SC.getValue(), true);
        } else {
            NMAL0110CommonLogic.setDefaultValue(bizMsg, getGlobalCompanyCode());
            bizMsg.xxModeCd_H1.setValue(MODE_NEW); //New
        }
    }

    private void doProcess_NMAL0110_RESET(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        // if screen parameter exists
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_SC)) {
            // 2019/10/28 QC#53741 Add Start
            NMAL0110CommonLogic.setDimensionsUOM(bizMsg, getGlobalCompanyCode());
            // 2019/10/28 QC#53741 Add End

            // search merchandise info
            NMAL0110CommonLogic.search(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_SC.getValue(), true);
        } else {
            bizMsg.xxModeCd_H1.setValue(MODE_NEW); //New
            NMAL0110CommonLogic.clearValue(getGlobalCompanyCode(), getUserProfileService(), bizMsg, true);
        }
    }

    private void doProcess_NMAL0110_CLEAR(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        bizMsg.xxModeCd_H1.setValue(MODE_NEW); //New
        NMAL0110CommonLogic.clearValue(getGlobalCompanyCode(), getUserProfileService(), bizMsg, true);
    }

    private void doProcess_Change_HazMatCd_Pulldown(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        
        if (ZYPCommonFunc.hasValue(bizMsg.hazMatCd_H1)) {
            HAZ_MATTMsg hazMatTMsg = NMAL0110CommonLogic.findHazMat(getGlobalCompanyCode(), bizMsg.hazMatCd_H1.getValue());
            if (hazMatTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.hazClsNm_H1, hazMatTMsg.hazClsNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.hazPrpShipNm_H1, hazMatTMsg.hazPrpShipNm);
                ZYPEZDItemValueSetter.setValue(bizMsg.hazIdNum_H1, hazMatTMsg.hazIdNum);
            }
        } else {
            bizMsg.hazClsNm_H1.clear();
            bizMsg.hazPrpShipNm_H1.clear();
            bizMsg.hazIdNum_H1.clear();
        }
        
    }
    private void doProcess_NMAL0110_NMAL6050(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        
		String event = bizMsg.P.no(29).xxPopPrm.getValue();
		if ("NMAL0110Scrn00_Frt_Cls_Link".equals(event)) {
	        if (ZYPCommonFunc.hasValue(bizMsg.frtClsCd_H1)) {
	            FRT_CLSTMsg frtClsTMsg = NMAL0110CommonLogic.findFrtCls(getGlobalCompanyCode(), bizMsg.frtClsCd_H1.getValue());
	            if (frtClsTMsg != null) {
	                ZYPEZDItemValueSetter.setValue(bizMsg.frtClsDescTxt_H1, frtClsTMsg.frtClsDescTxt);
	                ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemNum_H1, frtClsTMsg.nmfcItemNum);
	                ZYPEZDItemValueSetter.setValue(bizMsg.nmfcItemSubNum_H1, frtClsTMsg.nmfcItemSubNum);
	                ZYPEZDItemValueSetter.setValue(bizMsg.nmfcClsNum_H1, frtClsTMsg.nmfcClsNum);
	            }
	        } else {
	            bizMsg.frtClsDescTxt_H1.clear();
	            bizMsg.nmfcItemNum_H1.clear();
	            bizMsg.nmfcItemSubNum_H1.clear();
	            bizMsg.nmfcClsNum_H1.clear();
	        }
		}
    }

    // QC#3579
    private void doProcess_Download_Item_History(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        downloadItemHistory(bizMsg);
    }
    // Decide third
    private void doProcess_Product_Line_level_3_Link(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
    	if (!ZYPCommonFunc.hasValue(bizMsg.scdProdCtrlCd_H1) && ZYPCommonFunc.hasValue(bizMsg.thirdProdCtrlCd_H1)) {
    		Map<String, Object> map = NMAL0110CommonLogic.getThirdProdHrch(bizMsg, getGlobalCompanyCode());
    		if (map != null) {
    			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlCd_H1, (String) map.get("SCD_PROD_CTRL_CD"));
    			ZYPEZDItemValueSetter.setValue(bizMsg.scdProdCtrlNm_H1, (String) map.get("SCD_PROD_CTRL_DESC_TXT"));
    		}
    	}
    }
    
    //QC#4203
    //private void doProcess_Change_rtrnCtrlTpCd_Pulldown(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
    //    
    //    bizMsg.rtrnToVndCd_L1.clear();
    //    bizMsg.vndNm_L1.clear();
    //    if (ZYPCommonFunc.hasValue(bizMsg.rtrnCtrlTpCd_H1)) {
    //        NMAL0110CommonLogic.setRtrnVndPulldown(bizMsg, getGlobalCompanyCode(), bizMsg.rtrnCtrlTpCd_H1.getValue());
    //    }
    //    
    //}
    
    private void doProcess_Change_ItemTp_Pulldown(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {

        String mdseItemTpCd_H1 = bizMsg.mdseItemTpCd_H1.getValue();
        boolean mdseItemTpSetOrKit = (!ZYPCommonFunc.hasValue(mdseItemTpCd_H1) 
        		|| MDSE_ITEM_TP.SET.equals(mdseItemTpCd_H1) 
        		|| MDSE_ITEM_TP.KIT.equals(mdseItemTpCd_H1)) ? true : false;
        if (!mdseItemTpSetOrKit) {
            bizMsg.xxTabProt_HB.setValue(FLG_OFF_N);
        } else {
            bizMsg.xxTabProt_HB.setValue(FLG_ON_Y);
        }

    }

    //2017/09/25 QC#18534(L3#445) Add Start
    private void doProcess_Change_svcPgmTpCd_Pulldown(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {

        if (!bizMsg.svcPgmTpCd_H1.getValue().isEmpty()) {
            SVC_PGM_TPTMsg inTMsg = new SVC_PGM_TPTMsg();
            ZYPEZDItemValueSetter.setValue(inTMsg.glblCmpyCd, getGlobalCompanyCode());
            ZYPEZDItemValueSetter.setValue(inTMsg.svcPgmTpCd, bizMsg.svcPgmTpCd_H1);
            SVC_PGM_TPTMsg outTMsg = (SVC_PGM_TPTMsg) ZYPCodeDataUtil.findByKey(inTMsg);

            if (outTMsg != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.ovrdMnfWtyFlg_H1, outTMsg.ovrdMnfWtyAvalFlg);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(bizMsg.ovrdMnfWtyFlg_H1, "");
        }
    }
    //2017/09/25 QC#18534(L3#445) Add End

    private void doProcess_Add_PkgType_SellingUOM(NMAL0110CMsg bizMsg, EZDSMsg msg) {

        BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
        String primPkgUomCd = "";
        if (radioValueK1 != null) {
            int radioIntValueK1 = radioValueK1.intValue();
            for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
                if (i == radioIntValueK1) {
                    primPkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
                }
            }
        }
        
        // Add Record
        int count = bizMsg.K.getValidCount() + 1;
        bizMsg.K.setValidCount(count);

        bizMsg.K.no(count - 1).xxChkBox_K1.setValue(ZYPConstant.FLG_OFF_N);
        bizMsg.K.no(count - 1).pkgUomCd_K1.setValue(bizMsg.pkgUomCd_H1.getValue());

        PKG_UOMTMsg pkgUomTMsg = new PKG_UOMTMsg();

        pkgUomTMsg.glblCmpyCd.setValue(getGlobalCompanyCode());
        pkgUomTMsg.pkgUomCd.setValue(bizMsg.pkgUomCd_H1.getValue());
        pkgUomTMsg = (PKG_UOMTMsg) S21FastTBLAccessor.findByKey(pkgUomTMsg);

        if (pkgUomTMsg != null) {
            bizMsg.K.no(count - 1).pkgUomDescTxt_K1.setValue(pkgUomTMsg.pkgUomDescTxt.getValue());
            bizMsg.K.no(count - 1).pkgUomSortNum_K1.setValue(pkgUomTMsg.pkgUomSortNum.getValue());
        }
        
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            if (primPkgUomCd.equals(bizMsg.K.no(i).pkgUomCd_K1.getValue())) {
                bizMsg.xxRadioBtn_K1.setValue(new BigDecimal(String.valueOf(i)));
            }
        }
        
    }

    private void doProcess_Del_PkgType_Selling(NMAL0110CMsg bizMsg, EZDSMsg msg) {

        BigDecimal radioValueK1 = bizMsg.xxRadioBtn_K1.getValue();
        String primPkgUomCd = "";
        if (radioValueK1 != null) {
            int radioIntValueK1 = radioValueK1.intValue();
            for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
                if (i == radioIntValueK1) {
                    primPkgUomCd = bizMsg.K.no(i).pkgUomCd_K1.getValue();
                }
            }
        }
        
        // Delete Record
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.K, "xxChkBox_K1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < bizMsg.K.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                bizMsg.K.no(i).xxChkBox_K1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {
            for ( int i = 0; i < bizMsg.K.getValidCount(); i++ ) {
                if (selectedRows.contains(i) && ZYPCommonFunc.hasValue(bizMsg.K.no(i).basePkgUomCd_K1) && bizMsg.K.no(i).basePkgUomCd_K1.getValue().equals(bizMsg.K.no(i).pkgUomCd_K1.getValue())) {
                    //NMAM8221E=0,Base UOM Code can not remove.
                    bizMsg.K.no(i).xxChkBox_K1.setErrorInfo(1, "NMAM8221E");
                    return;
                }
            }

            ZYPTableUtil.deleteRows(bizMsg.K, selectedRows);
        }
        
        for (int i = 0; i < bizMsg.K.getValidCount(); i++) {
            if (primPkgUomCd.equals(bizMsg.K.no(i).pkgUomCd_K1.getValue())) {
                bizMsg.xxRadioBtn_K1.setValue(new BigDecimal(String.valueOf(i)));
            }
        }
        
    }

    private void doProcess_Change_PkgUomClsCd_Pulldown(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
            //CPO, PO Check
            S21SsmEZDResult rs = NMAL0110Query.getInstance().getTransactionDataCPOAndPO(getGlobalCompanyCode(), bizMsg.mdseCd_H1.getValue());
            if (rs.isCodeNormal()) {
                Integer count = (Integer) rs.getResultObject();
                if (count > 0) {
                    // NMAM8257E=0,Can not change UNIT OF MEASURE CLASS because this item has some transactions in CPO, PO.
                    ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomClsCd_H1, bizMsg.pkgUomClsCd_BK);
                    bizMsg.pkgUomClsCd_H1.setErrorInfo(1, "NMAM8257E");
                    return;
                }
            }
        }
        ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomClsCd_BK, bizMsg.pkgUomClsCd_H1);
        
        if (ZYPCommonFunc.hasValue(bizMsg.pkgUomClsCd_H1)) {
            Map<String, Object> map = NMAL0110CommonLogic.getBasePkgUomNm(getGlobalCompanyCode(), bizMsg.pkgUomClsCd_H1.getValue());
            if (map != null && map.get("PKG_UOM_DESC_TXT") != null) {
                ZYPEZDItemValueSetter.setValue(bizMsg.pkgUomDescTxt_BA, (String) map.get("PKG_UOM_DESC_TXT"));
            } else {
                bizMsg.pkgUomDescTxt_BA.clear();
            }
            //UOM
            bizMsg.pkgUomCd_BL.clear();
            bizMsg.pkgUomDescTxt_BL.clear();
            NMAL0110CommonLogic.setInitialPkgUomPulldown(bizMsg, getGlobalCompanyCode(), bizMsg.pkgUomClsCd_H1.getValue());        
            ZYPTableUtil.clear(bizMsg.K);
            Map<String, Object> baseMap = NMAL0110CommonLogic.getBasePkgUom(getGlobalCompanyCode(), bizMsg.pkgUomClsCd_H1.getValue());
            if (baseMap != null && baseMap.get("PKG_UOM_CD") != null) {
                bizMsg.xxRadioBtn_K1.setValue(new BigDecimal(String.valueOf(0)));
                // Add Record
                int count = bizMsg.K.getValidCount() + 1;
                bizMsg.K.setValidCount(count);
                bizMsg.K.no(count - 1).xxChkBox_K1.setValue(ZYPConstant.FLG_OFF_N);
                //QC#17184
                //bizMsg.K.no(count - 1).pkgUomCd_K1.setValue((String) baseMap.get("PKG_UOM_CD"));
                bizMsg.K.no(count - 1).pkgUomCd_K1.setValue(PKG_UOM.EACH);
                bizMsg.K.no(count - 1).pkgUomDescTxt_K1.setValue((String) baseMap.get("PKG_UOM_DESC_TXT"));
                bizMsg.K.no(count - 1).pkgUomSortNum_K1.setValue((BigDecimal) baseMap.get("PKG_UOM_SORT_NUM"));
                bizMsg.K.no(count - 1).inEachQty_K1.setValue((BigDecimal) baseMap.get("IN_EACH_QTY"));
                bizMsg.K.no(count - 1).basePkgUomCd_K1.setValue((String) baseMap.get("BASE_PKG_UOM_CD"));
                
                for (int j = 0; j < bizMsg.L.getValidCount(); j++) {
                	if (ZYPCommonFunc.hasValue(bizMsg.L.no(j).pkgUomCd_L1)
                			&& bizMsg.L.no(j).pkgUomCd_L1.getValue().equals(bizMsg.K.no(count - 1).pkgUomCd_K1.getValue())) {
                		bizMsg.K.no(count - 1).ezUpTime_K1.setValue(bizMsg.L.no(j).ezUpTime_L1.getValue());
                		bizMsg.K.no(count - 1).ezUpTimeZone_K1.setValue(bizMsg.L.no(j).ezUpTimeZone_L1.getValue());
                		bizMsg.K.no(count - 1).ezUpTime_KS.setValue(bizMsg.L.no(j).ezUpTime_LS.getValue());
                		bizMsg.K.no(count - 1).ezUpTimeZone_KS.setValue(bizMsg.L.no(j).ezUpTimeZone_LS.getValue());
                		bizMsg.K.no(count - 1).ezUpTime_KT.setValue(bizMsg.L.no(j).ezUpTime_LT.getValue());
                		bizMsg.K.no(count - 1).ezUpTimeZone_KT.setValue(bizMsg.L.no(j).ezUpTimeZone_LT.getValue());
                		break;
                	}
                }
                
            }
            
        } else {
            bizMsg.pkgUomDescTxt_BA.clear();
            bizMsg.pkgUomCd_BL.clear();
            bizMsg.pkgUomDescTxt_BL.clear();
            ZYPTableUtil.clear(bizMsg.K);
        }
        
    }

    private void doProcess_ViewServiceModel(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
        if (ZYPCommonFunc.hasValue(bizMsg.mdseCd_H1)) {
        	//None
        }
    }

    private void doProcess_Add_TermCondOpt(NMAL0110CMsg bizMsg, EZDSMsg msg) {

        // Add Record
        int count = bizMsg.N.getValidCount() + 1;
        bizMsg.N.setValidCount(count);

        bizMsg.N.no(count - 1).xxChkBox_N1.setValue(ZYPConstant.FLG_OFF_N);
        
    }

    private void doProcess_Del_TermCondOpt(NMAL0110CMsg bizMsg, EZDSMsg msg) {

        // Delete Record
        List<Integer> selectedRows = ZYPTableUtil.getSelectedRows(bizMsg.N, "xxChkBox_N1", ZYPConstant.FLG_ON_Y);
        if ( selectedRows.isEmpty() ) {
            for ( int i = 0; i < bizMsg.N.getValidCount(); i++ ) {
                //NMAM8054E=0,Please select item(s).
                bizMsg.N.no(i).xxChkBox_N1.setErrorInfo(1, "NMAM8054E");
                return;
            }
        } else {
            ZYPTableUtil.deleteRows(bizMsg.N, selectedRows);
        }
        
    }

    // QC#3579
    private void downloadItemHistory(NMAL0110CMsg bizMsg) {
        ItemHistoryReportConfig config = loadItemHistoryReportConfig(bizMsg);
        if (config == null) {
            bizMsg.setMessageInfo("NMAM8298E");
            return;
        }
        CsvWriterForItemHistory csvWriter = createCsvWriter(bizMsg);
        String sql = createItemHistoryReportSql(bizMsg, config);
        createItemHistoryReport(bizMsg, config, sql, csvWriter);

        if (csvWriter.hasData) {
            csvWriter.close();
        } else {
            bizMsg.setMessageInfo("NMAM8298E");
            return;
        }
    }

    private CsvWriterForItemHistory createCsvWriter(NMAL0110CMsg bizMsg) {
        bizMsg.xxFileData_H1.setTempFilePath(null, ZYPCSVOutFile.createCSVOutFileNm("Item History"), ".csv");
        CsvWriterForItemHistory csvWriter = new CsvWriterForItemHistory(bizMsg.xxFileData_H1.getTempFilePath());
        return csvWriter;
    }

    /**
     * CSV file writer for Item History Report
     */
    private class CsvWriterForItemHistory {

        /** CSV file layout */
        NMAL0110F00FMsg fMsg;

        /** CSV file handler */
        ZYPCSVOutFile csvOutFile;

        /** Temporary File Path */
        String tempFilePath;

        /** Has Date Flag */
        boolean hasData;

        CsvWriterForItemHistory(String tempFilePath) {
            this.tempFilePath = tempFilePath;
            this.hasData = false;
        }

        void close() {
            this.csvOutFile.close();
        }

        void write(ItemHistoryReportRow rptRow) {
            if (!hasData) {
                this.hasData = true;
                this.fMsg = new NMAL0110F00FMsg();
                this.csvOutFile = new ZYPCSVOutFile(this.tempFilePath, this.fMsg);
                this.csvOutFile.writeHeader(getItemHistoryReportHeader());
            }

            ZYPEZDItemValueSetter.setValue(this.fMsg.mdseCd, rptRow.bizKey01);
            ZYPEZDItemValueSetter.setValue(this.fMsg.xxDsHistRptColValTxt_K1, cutToMaxLength(rptRow.bizKey02));
            ZYPEZDItemValueSetter.setValue(this.fMsg.xxDsHistRptColValTxt_K2, cutToMaxLength(rptRow.bizKey03));
            ZYPEZDItemValueSetter.setValue(this.fMsg.dsHistRptColGrpNm, rptRow.rptColGrpNm);
            ZYPEZDItemValueSetter.setValue(this.fMsg.dsHistRptColDescTxt, rptRow.rptColDescTxt);
            ZYPEZDItemValueSetter.setValue(this.fMsg.xxDsHistRptColValTxt_O1, cutToMaxLength(rptRow.oldVal));
            ZYPEZDItemValueSetter.setValue(this.fMsg.xxDsHistRptColValTxt_N1, cutToMaxLength(rptRow.newVal));
            ZYPEZDItemValueSetter.setValue(this.fMsg.histActNm, rptRow.histActNm);
            ZYPEZDItemValueSetter.setValue(this.fMsg.xxHistRptUpdTsDescTxt, rptRow.updTs);
            ZYPEZDItemValueSetter.setValue(this.fMsg.updUsrId, rptRow.updUsrId);
            ZYPEZDItemValueSetter.setValue(this.fMsg.hrPsnIntfcPsnNm, rptRow.updUsrNm);
            csvOutFile.write();
        }

        private String[] getItemHistoryReportHeader() {
            List<String> list = new ArrayList<String>();
            list.add("Item Code");
            list.add("Key1");
            list.add("Key2");
            list.add("Group");
            list.add("Field Name");
            list.add("From");
            list.add("To");
            list.add("Action");
            list.add("Updated Date");
            list.add("Updated By");
            list.add("Updated By Name");
            return list.toArray(new String[] {});
        }

        private String cutToMaxLength(String value) {
            if (ZYPCommonFunc.hasValue(value)) {
                if (value.length() > ITEM_HIST_RPT_MAX_COL_VAL_LEN) {
                    return value.substring(0, ITEM_HIST_RPT_MAX_COL_VAL_LEN);
                }
            }
            return value;
        }
    }

    /**
     * Configuration for Item History Report
     */
    private class ItemHistoryReportConfig {
        /** Table Configuration List */
        List<ItemHistoryReportConfigTable> configTables;

        ItemHistoryReportConfig() {
            this.configTables = new ArrayList<ItemHistoryReportConfigTable>();
        }

        void addConfigTable(ItemHistoryReportConfigTable configTable) {
            this.configTables.add(configTable);
        }

        ItemHistoryReportConfigTable getConfigTable(String rptTblNm) {
            for (ItemHistoryReportConfigTable configTable : this.configTables) {
                if (rptTblNm.equals(configTable.rptTblNm)) {
                    return configTable;
                }
            }
            return null;
        }
    }

    /**
     * Table Configuration for Item History Report
     */
    private class ItemHistoryReportConfigTable {
        /** Report Table Name */
        String rptTblNm;

        /** History Table Name */
        String histTblNm;

        /** Search Column Name */
        String srchColNm;

        /** Business Key 1 */
        String bizKey01;

        /** Business Key 2 */
        String bizKey02;

        /** Business Key 3 */
        String bizKey03;

        /** Column Configuration List */
        List<ItemHistoryReportConfigColumn> configConlumns;

        /** DB Table Physical Keys */
        List<String> physKeys;

        ItemHistoryReportConfigTable(String rptTblNm, String histTblNm, String srchColNm, String bizKey01, String bizKey02, String bizKey03) {
            this.rptTblNm = rptTblNm;
            this.histTblNm = histTblNm;
            this.srchColNm = srchColNm;
            this.bizKey01 = bizKey01;
            this.bizKey02 = bizKey02;
            this.bizKey03 = bizKey03;
            this.configConlumns = new ArrayList<ItemHistoryReportConfigColumn>();
            this.physKeys = loadPhysicalKeys();
        }

        void addConfigColumn(ItemHistoryReportConfigColumn configColumn) {
            this.configConlumns.add(configColumn);
        }

        List<String> getBusinessKeys() {
            List<String> list = new ArrayList<String>();
            list.add(this.bizKey01);
            list.add(this.bizKey02);
            list.add(this.bizKey03);
            return list;
        }

        @SuppressWarnings("unchecked")
        private List<String> loadPhysicalKeys() {
            try {
                String tmsgClassNm = "business.db." + this.rptTblNm + "TMsg";
                EZDTMsg tmsg = (EZDTMsg) Class.forName(tmsgClassNm).newInstance();
                List<String>[] lists = tmsg.getKeyColumnList();
                List<String> listDbKey = lists[1];
                List<String> listPhysKey = new ArrayList<String>();
                for (String dbKey : listDbKey) {
                    listPhysKey.add(dbKey);
                }
                return listPhysKey;
            } catch (InstantiationException e) {
                throw new S21AbendException(e);
            } catch (IllegalAccessException e) {
                throw new S21AbendException(e);
            } catch (ClassNotFoundException e) {
                throw new S21AbendException(e);
            }
        }
    }

    /**
     * Column Configuration for Item History Report
     */
    private class ItemHistoryReportConfigColumn {
        /** Report Column Name */
        String rptColNm;

        /** Report Coumn Description */
        String rptColDescTxt;

        /** Report Column Group */
        String rptColGrpNm;

        /** Report Column Sort Number */
        BigDecimal rptColSortNum;

        /** Code Table Name */
        String cdTblNm;

        /** Code Table Search Column Name */
        String cdTblSrchColNm;

        /** Code Table Display Column Name */
        String cdTblDplyColNm;

        /** Insert Display Flag */
        String insrDplyFlg;

        /** Update Display Flag */
        String updDplyFlg;

        /** Delete Display Flag */
        String delDplyFlg;

        ItemHistoryReportConfigColumn(String rptColNm, String rptColDescTxt, String rptColGrpNm, BigDecimal rptColSortNum, String cdTblNm, String cdTblSrchColNm, String cdTblDplyColNm, String insrDplyFlg, String updDplyFlg,
                String delDplyFlg) {
            this.rptColNm = rptColNm;
            this.rptColDescTxt = rptColDescTxt;
            this.rptColGrpNm = rptColGrpNm;
            this.rptColSortNum = rptColSortNum;
            this.cdTblNm = cdTblNm;
            this.cdTblSrchColNm = cdTblSrchColNm;
            this.cdTblDplyColNm = cdTblDplyColNm;
            this.insrDplyFlg = insrDplyFlg;
            this.updDplyFlg = updDplyFlg;
            this.delDplyFlg = delDplyFlg;
        }
    }

    /**
     * Report Row for Item History Report
     */
    private class ItemHistoryReportRow implements Comparable<ItemHistoryReportRow> {
        /** Business Key 1 */
        String bizKey01;

        /** Business Key 2 */
        String bizKey02;

        /** Business Key 3 */
        String bizKey03;

        /** Report Column Group */
        String rptColGrpNm;

        /** Report Column Description */
        String rptColDescTxt;

        /** From Value */
        String oldVal;

        /** To Value */
        String newVal;

        /** Action */
        String histActNm;

        /** Updated Time */
        String updTs;

        /** Updated By User ID */
        String updUsrId;

        /** Updated By User Name */
        String updUsrNm;

        /** Report Column Sort Number */
        BigDecimal rptColSortNum;

        @Override
        public int compareTo(ItemHistoryReportRow row) {
            return this.rptColSortNum.compareTo(row.rptColSortNum);
        }
    }

    @SuppressWarnings("unchecked")
    private ItemHistoryReportConfig loadItemHistoryReportConfig(NMAL0110CMsg bizMsg) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getItemHistoryReportConfig(bizMsg);
        if (!result.isCodeNormal()) {
            return null;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
        if (list == null || list.size() <= 0) {
            return null;
        }

        ItemHistoryReportConfig config = new ItemHistoryReportConfig();

        for (Map<String, Object> map : list) {
            String rptTblNm = (String) map.get("DS_HIST_RPT_TBL_NM");
            String histTblNm = (String) map.get("HIST_TBL_NM");
            String srchColNm = (String) map.get("HIST_TBL_SRCH_COL_NM");
            String bizKey01 = (String) map.get("HIST_TBL_BIZ_KEY_COL_NM_01");
            String bizKey02 = (String) map.get("HIST_TBL_BIZ_KEY_COL_NM_02");
            String bizKey03 = (String) map.get("HIST_TBL_BIZ_KEY_COL_NM_03");

            String rptColNm = (String) map.get("DS_HIST_RPT_COL_NM");
            String rptColDescTxt = (String) map.get("DS_HIST_RPT_COL_DESC_TXT");
            String rptColGrpNm = (String) map.get("DS_HIST_RPT_COL_GRP_NM");
            BigDecimal rptColSortNum = (BigDecimal) map.get("DS_HIST_RPT_COL_SORT_NUM");
            String cdTblNm = (String) map.get("CD_TBL_NM");
            String cdTblSrchColNm = (String) map.get("CD_TBL_SRCH_COL_NM");
            String cdTblDplyColNm = (String) map.get("CD_TBL_DPLY_COL_NM");
            String insrDplyFlg = (String) map.get("DS_HIST_RPT_INSR_DPLY_FLG");
            String updDplyFlg = (String) map.get("DS_HIST_RPT_UPD_DPLY_FLG");
            String delDplyFlg = (String) map.get("DS_HIST_RPT_DEL_DPLY_FLG");

            ItemHistoryReportConfigTable configTable = config.getConfigTable(rptTblNm);
            if (configTable == null) {
                configTable = new ItemHistoryReportConfigTable(rptTblNm, histTblNm, srchColNm, bizKey01, bizKey02, bizKey03);
                config.addConfigTable(configTable);
            }
            ItemHistoryReportConfigColumn configColumn = new ItemHistoryReportConfigColumn(rptColNm, rptColDescTxt, rptColGrpNm, rptColSortNum, cdTblNm, cdTblSrchColNm, cdTblDplyColNm, insrDplyFlg, updDplyFlg, delDplyFlg);
            configTable.addConfigColumn(configColumn);
        }
        return config;
    }

    private String createItemHistoryReportSql(NMAL0110CMsg bizMsg, ItemHistoryReportConfig config) {
        String itemHistExclAplId = ZYPCodeDataUtil.getVarCharConstValue("ITEM_HIST_EXCL_APL_ID", this.getGlobalCompanyCode());
        StringBuffer sb = new StringBuffer();
        boolean isFirstUnionTable = true;
        sb.append("SELECT * FROM (").append(NEW_LINE_CHAR);
        for (ItemHistoryReportConfigTable configTable : config.configTables) {
            String singleTableSql = createItemHistoryReportSql(bizMsg, configTable);
            if (isFirstUnionTable) {
                isFirstUnionTable = false;
                sb.append(singleTableSql);
            } else {
                sb.append("UNION ALL").append(NEW_LINE_CHAR);
                sb.append(singleTableSql);
            }
        }
        sb.append(")").append(NEW_LINE_CHAR);
        sb.append("WHERE EZUPAPLID <> '" + itemHistExclAplId + "'").append(NEW_LINE_CHAR);
        sb.append("ORDER BY SORT_UPD_TS DESC, BIZ_KEY_0, BIZ_KEY_1, BIZ_KEY_2, HIST_CRAT_TS DESC");
        return sb.toString();
    }

    private String createItemHistoryReportSql(NMAL0110CMsg bizMsg, ItemHistoryReportConfigTable configTable) {
        StringBuffer sb = new StringBuffer();

        // Escape single quotation in search condition
        String mdseCd = bizMsg.mdseCd_H1.getValue().replaceAll("'", "''");

        // ----------------------------------------
        // select
        // ----------------------------------------
        // common
        sb.append("SELECT").append(NEW_LINE_CHAR);
        sb.append("'" + configTable.rptTblNm + "' RPT_TBL_NM").append(NEW_LINE_CHAR);
        sb.append(", H1.EZUPAPLID").append(NEW_LINE_CHAR);
        sb.append(", H1.HIST_CRAT_TS").append(NEW_LINE_CHAR);
        sb.append(", DECODE(H1.HIST_ACT_NM, 'DELETE', H1.HIST_CRAT_TS, H1.EZUPTIME) UPD_TS").append(NEW_LINE_CHAR);
        sb.append(", SUBSTR(DECODE(H1.HIST_ACT_NM, 'DELETE', H1.HIST_CRAT_TS, H1.EZUPTIME),1,14) SORT_UPD_TS").append(NEW_LINE_CHAR);
        sb.append(", H1.EZUPUSERID UPD_USR_ID").append(NEW_LINE_CHAR);
        sb.append(", (SELECT P.HR_PSN_INTFC_PSN_NM FROM HR_PSN_INTFC P WHERE P.EZCANCELFLAG = '0' AND P.GLBL_CMPY_CD = H1.GLBL_CMPY_CD AND P.HR_PSN_INTFC_EMP_ID = H1.EZUPUSERID AND ROWNUM <= 1) UPD_USR_NM").append(NEW_LINE_CHAR);
        sb.append(", DECODE(H1.EZCANCELFLAG, '1', 'DELETE', H1.HIST_ACT_NM) HIST_ACT_NM").append(NEW_LINE_CHAR);

        // business keys
        List<String> listBizKeys = configTable.getBusinessKeys();
        for (int i = 0; i < listBizKeys.size(); i++) {
            String bizKey = listBizKeys.get(i);
            if (ZYPCommonFunc.hasValue(bizKey)) {
                sb.append(", TO_CHAR(H1." + bizKey + ") BIZ_KEY_" + i).append(NEW_LINE_CHAR);
            } else {
                sb.append(", NULL BIZ_KEY_" + i).append(NEW_LINE_CHAR);
            }
        }

        // business fields
        int maxColumnCount = ITEM_HIST_RPT_MAX_COL_PER_TBL;
        for (int i = 0; i < maxColumnCount; i++) {
            if (i < configTable.configConlumns.size()) {
                ItemHistoryReportConfigColumn configColumn = configTable.configConlumns.get(i);
                if (ZYPCommonFunc.hasValue(configColumn.cdTblNm)) {
                    sb.append(
                            ", (SELECT C." + configColumn.cdTblDplyColNm + " FROM " + configColumn.cdTblNm + " C WHERE C." + configColumn.cdTblSrchColNm + " = H1." + configColumn.rptColNm
                                    + " AND C.GLBL_CMPY_CD = H1.GLBL_CMPY_CD AND C.EZCANCELFLAG = '0' AND ROWNUM <= 1)" + " NEW_COL_" + i).append(NEW_LINE_CHAR);
                    sb.append(
                            ", (SELECT C." + configColumn.cdTblDplyColNm + " FROM " + configColumn.cdTblNm + " C WHERE C." + configColumn.cdTblSrchColNm + " = H2." + configColumn.rptColNm
                                    + " AND C.GLBL_CMPY_CD = H2.GLBL_CMPY_CD AND C.EZCANCELFLAG = '0' AND ROWNUM <= 1)" + " OLD_COL_" + i).append(NEW_LINE_CHAR);
                } else {
                    sb.append(", TO_CHAR(H1." + configColumn.rptColNm + ") NEW_COL_" + i).append(NEW_LINE_CHAR);
                    sb.append(", TO_CHAR(H2." + configColumn.rptColNm + ") OLD_COL_" + i).append(NEW_LINE_CHAR);
                }
            } else {
                sb.append(", NULL NEW_COL_" + i).append(NEW_LINE_CHAR);
                sb.append(", NULL OLD_COL_" + i).append(NEW_LINE_CHAR);
            }
        }

        // ----------------------------------------
        // from
        // ----------------------------------------
        StringBuffer sqlPhysKeys = new StringBuffer();
        for (int i = 0; i < configTable.physKeys.size(); i++) {
            if (i > 0) {
                sqlPhysKeys.append(", ");
            }
            sqlPhysKeys.append("M." + configTable.physKeys.get(i));
        }
        sb.append("FROM").append(NEW_LINE_CHAR);
        sb.append(
                " (SELECT ROW_NUMBER() OVER(PARTITION BY " + sqlPhysKeys + " ORDER BY M.HIST_CRAT_TS ) SEQ, M.* FROM " + configTable.histTblNm + " M WHERE M.GLBL_CMPY_CD = '" + getGlobalCompanyCode() + "' AND M." + configTable.srchColNm
                        + " = '" + mdseCd + "') H1").append(NEW_LINE_CHAR);
        sb.append(
                ",(SELECT ROW_NUMBER() OVER(PARTITION BY " + sqlPhysKeys + " ORDER BY M.HIST_CRAT_TS ) SEQ, M.* FROM " + configTable.histTblNm + " M WHERE M.GLBL_CMPY_CD = '" + getGlobalCompanyCode() + "' AND M." + configTable.srchColNm
                        + " = '" + mdseCd + "') H2").append(NEW_LINE_CHAR);

        // ----------------------------------------
        // where
        // ----------------------------------------
        sb.append("WHERE").append(NEW_LINE_CHAR);
        sb.append("H1.HIST_ACT_NM <> 'DELETE'").append(NEW_LINE_CHAR);
        sb.append("AND H1.SEQ - 1 = H2.SEQ(+)").append(NEW_LINE_CHAR);
        for (int i = 0; i < configTable.physKeys.size(); i++) {
            sb.append("AND H1." + configTable.physKeys.get(i) + " = H2." + configTable.physKeys.get(i) + "(+)").append(NEW_LINE_CHAR);
        }

        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    private void createItemHistoryReport(NMAL0110CMsg bizMsg, ItemHistoryReportConfig config, String sql, CsvWriterForItemHistory csvWriter) {
        S21SsmEZDResult result = NMAL0110Query.getInstance().getItemHistory(bizMsg, sql);
        if (!result.isCodeNormal()) {
            return;
        }
        List<Map<String, Object>> list = (List<Map<String, Object>>) result.getResultObject();
        if (list == null || list.size() <= 0) {
            return;
        }

        String lastEzuptime = null;
        List<ItemHistoryReportRow> listRptRow = new ArrayList<ItemHistoryReportRow>();

        for (Map<String, Object> map : list) {
            String rptTblNm = (String) map.get("RPT_TBL_NM");
            String histActNm = (String) map.get("HIST_ACT_NM");
            String updTs = (String) map.get("UPD_TS");
            String updUsrId = (String) map.get("UPD_USR_ID");
            String updUsrNm = (String) map.get("UPD_USR_NM");
            String bizKey01 = (String) map.get("BIZ_KEY_0");
            String bizKey02 = (String) map.get("BIZ_KEY_1");
            String bizKey03 = (String) map.get("BIZ_KEY_2");

            if (lastEzuptime != null && !lastEzuptime.equals(updTs)) {
                flushReportRow(listRptRow, csvWriter);
                listRptRow.clear();
            }
            lastEzuptime = updTs;

            ItemHistoryReportConfigTable configTable = config.getConfigTable(rptTblNm);

            for (int i = 0; i < configTable.configConlumns.size(); i++) {
                ItemHistoryReportConfigColumn configColumn = configTable.configConlumns.get(i);
                if (isActionNeedToReport(histActNm, configColumn)) {
                    Object newValue = (Object) map.get("NEW_COL_" + i);
                    Object oldValue = (Object) map.get("OLD_COL_" + i);
                    if ((DB_INSR_ACT_NM.equals(histActNm) && !isBlankValue(newValue)) || (DB_UPD_ACT_NM.equals(histActNm) && isReportValueChanged(newValue, oldValue)) || DB_DEL_ACT_NM.equals(histActNm)) {
                        ItemHistoryReportRow rptRow = new ItemHistoryReportRow();
                        listRptRow.add(rptRow);
                        rptRow.bizKey01 = convertNullToBlank(bizKey01);
                        rptRow.bizKey02 = convertNullToBlank(bizKey02);
                        rptRow.bizKey03 = convertNullToBlank(bizKey03);
                        rptRow.rptColGrpNm = convertNullToBlank(configColumn.rptColGrpNm);
                        rptRow.rptColDescTxt = convertNullToBlank(configColumn.rptColDescTxt);

                        if (DB_INSR_ACT_NM.equals(histActNm)) {
                            rptRow.newVal = getFieldValueForReportDisplay(configColumn, newValue);
                            rptRow.oldVal = "";

                        } else if (DB_UPD_ACT_NM.equals(histActNm)) {
                            rptRow.newVal = getFieldValueForReportDisplay(configColumn, newValue);
                            rptRow.oldVal = getFieldValueForReportDisplay(configColumn, oldValue);

                        } else if (DB_DEL_ACT_NM.equals(histActNm)) {
                            rptRow.newVal = "";
                            rptRow.oldVal = getFieldValueForReportDisplay(configColumn, oldValue);
                        }

                        rptRow.histActNm = convertNullToBlank(histActNm);
                        rptRow.updTs = getUpdateTimeForReportDisplay(updTs);
                        rptRow.updUsrId = convertNullToBlank(updUsrId);
                        rptRow.updUsrNm = convertNullToBlank(updUsrNm);

                        rptRow.rptColSortNum = configColumn.rptColSortNum;
                    }
                }
            }
        }

        if (listRptRow.size() > 0) {
            flushReportRow(listRptRow, csvWriter);
            listRptRow.clear();
        }
    }

    private String getFieldValueForReportDisplay(ItemHistoryReportConfigColumn configColumn, Object value) {
        if (configColumn.rptColNm.endsWith("_FLG")) {
            if (ZYPConstant.FLG_ON_Y.equals(value)) {
                return "Yes";
            } else if (ZYPConstant.FLG_OFF_N.equals(value)) {
                return "No";
            }
            return convertNullToBlank(value);
        } else {
            return convertNullToBlank(value);
        }
    }

    private String getUpdateTimeForReportDisplay(String updTs) {
        if (updTs == null) {
            return "";
        }
        if (updTs.length() < 14) {
            return "";
        }
        String yyyy = updTs.substring(0, 4);
        String mm = updTs.substring(4, 6);
        String dd = updTs.substring(6, 8);
        String hh = updTs.substring(8, 10);
        String mi = updTs.substring(10, 12);
        String ss = updTs.substring(12, 14);

        return mm + "/" + dd + "/" + yyyy + " " + hh + ":" + mi + ":" + ss;
    }

    private boolean isActionNeedToReport(String histActNm, ItemHistoryReportConfigColumn configColumn) {
        if (DB_INSR_ACT_NM.equals(histActNm) && ZYPConstant.FLG_ON_Y.equals(configColumn.insrDplyFlg)) {
            return true;
        }
        if (DB_UPD_ACT_NM.equals(histActNm) && ZYPConstant.FLG_ON_Y.equals(configColumn.updDplyFlg)) {
            return true;
        }
        if (DB_DEL_ACT_NM.equals(histActNm) && ZYPConstant.FLG_ON_Y.equals(configColumn.delDplyFlg)) {
            return true;
        }
        return false;
    }

    private void flushReportRow(List<ItemHistoryReportRow> listRptRow, CsvWriterForItemHistory csvWriter) {
        Collections.sort(listRptRow);

        for (ItemHistoryReportRow rptRow : listRptRow) {
            flushReportRow(rptRow, csvWriter);
        }
    }

    private void flushReportRow(ItemHistoryReportRow rptRow, CsvWriterForItemHistory csvWriter) {
        csvWriter.write(rptRow);
    }

    private String convertNullToBlank(Object value) {
        if (value == null) {
            return "";
        }
        return value.toString();
    }

    private boolean isBlankValue(Object value) {
        if (value == null) {
            return true;
        }
        return !ZYPCommonFunc.hasValue(value.toString());
    }

    private boolean isReportValueChanged(Object newValue, Object oldValue) {
        if (newValue == null && oldValue == null) {
            return false;
        } else if (newValue == null) {
            return true;
        } else if (oldValue == null) {
            return true;
        }
        return !newValue.equals(oldValue);
    }
    
    @SuppressWarnings("unchecked")
    private void doProcess_CopyItem(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {
    	if (!ZYPCommonFunc.hasValue(bizMsg.mdseCd_C1)) {
    		//ZZM9000E=0,[@] field is mandatory.
    		bizMsg.mdseCd_C1.setErrorInfo(1, "ZZM9000E", new String[]{"Item Number"});
    		return;
    	}

        // 2019/10/28 QC#53741 Add Start
        NMAL0110CommonLogic.setDimensionsUOM(bizMsg, getGlobalCompanyCode());
        // 2019/10/28 QC#53741 Add End

		S21SsmEZDResult result = NMAL0110Query.getInstance().search(bizMsg, getGlobalCompanyCode(), bizMsg.mdseCd_C1.getValue());
		if (result.isCodeNormal() && (Map<String, Object>) result.getResultObject() != null) {
			NMAL0110CommonLogic.setScreenValueFromMapForCopy(bizMsg, (Map<String, Object>) result.getResultObject());
			bizMsg.mdseCd_H1.clear();
			bizMsg.mdseCd_C1.clear();
			bizMsg.xxModeCd_H1.setValue(MODE_NEW); //New
		} else {
			//NMAM8454E=0,[@] is not exists in master.
    		bizMsg.mdseCd_C1.setErrorInfo(1, "NDAM0007E", new String[]{"Copy Item"});
    		return;
		}
    }
    private void doProcess_OnBlur_CoaMdseTpCd(NMAL0110CMsg bizMsg, EZDSMsg sMsg) {

    	if (ZYPCommonFunc.hasValue(bizMsg.coaMdseTpCd_H1)) {
	    	COA_PROJTMsg coaPtojTMsg = NMAL0110CommonLogic.findCoaProj(getGlobalCompanyCode(), bizMsg.coaMdseTpCd_H1.getValue());
	    	if (coaPtojTMsg != null) {
	    		ZYPEZDItemValueSetter.setValue(bizMsg.coaProjDescTxt_H1, coaPtojTMsg.coaProjDescTxt);
	    	}
    	}
    	
    	if (ZYPCommonFunc.hasValue(bizMsg.mdseItemTpCd_H1) && MDSE_ITEM_TP.KIT.equals(bizMsg.mdseItemTpCd_H1.getValue())) {
	    	NMAL0110CommonLogic.clearValue_General_Attribute(bizMsg);
    	}
    	
    	
    }
}

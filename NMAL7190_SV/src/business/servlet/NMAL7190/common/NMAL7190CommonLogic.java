/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL7190.common;

import static business.servlet.NMAL7190.constant.NMAL7190Constant.BIZ_ID;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_APL;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_APR;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_CLR;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_DEL;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_DWL;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_RJT;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_RST;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_RTN;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_SAV;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_CMN_SUB;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_DELETE_ROW;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_FILTER;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_INSERT_ROW;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_SEARCH;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_TARGET_FROM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_TARGET_TO;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_UPLOAD;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.BTN_VIEW_USAGE;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.CMN_PRM_COLUMN_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.CMN_PRM_SORT_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.CMN_PRM_WHERE_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NAML6050_PRM_CODE_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NAML6050_PRM_NAME_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAL2550_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NAML6050_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NAML6760_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAL6800_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAL7440_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NMAM0052E;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.NWAL1130_PRM_NUM;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_NAME_NMAL2550;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_NAME_NMAL6050;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_NAME_NMAL6760;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_NAME_NMAL6800;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.POPUP_NAME_NWAL1130;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.SCRN_ID_00;
import static business.servlet.NMAL7190.constant.NMAL7190Constant.XX_MODE_CD_08;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDCMsgArray;
import parts.common.EZDCommonConst;
import parts.common.EZDItemAttribute;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.common.EZDSMsgArray;
import parts.servletcommon.EZDCommonHandler;
import business.blap.NMAL7190.NMAL7190CMsg;
import business.blap.NMAL7190.NMAL7190SMsg;
import business.blap.NMAL7190.NMAL7190_ACMsg;
import business.db.COA_CHTMsg;
import business.db.COA_PRODTMsg;
import business.db.COA_PROJTMsg;
import business.db.CSMP_CONTRTMsg;
import business.db.DS_ACCT_CLSTMsg;
import business.db.DS_ACCT_DLRTMsg;
import business.db.DS_ACCT_TPTMsg;
import business.db.DS_MDL_GRPTMsg;
import business.db.DS_ORD_CATGTMsg;
import business.db.DS_ORD_LINE_CATGTMsg;
import business.db.DS_ORD_TPTMsg;
import business.db.MDL_NMTMsg;
import business.db.MDSETMsg;
import business.db.MDSE_ITEM_CLS_TPTMsg;
import business.db.MDSE_ITEM_TPTMsg;
import business.db.MKT_MDLTMsg;
import business.db.PRC_CONTRTMsg;
import business.db.PRC_GRP_TRGT_ATTRBTMsg;
import business.db.PRC_GRP_TRGT_ATTRBTMsgArray;
import business.db.PROD_CTRLTMsg;
import business.db.SELL_TO_CUSTTMsg;
import business.servlet.NMAL7190.NMAL7190BMsg;
import business.servlet.NMAL7190.NMAL7190_ABMsg;
import business.servlet.NMAL7190.NMAL7190_ABMsgArray;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_OP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PRC_GRP_TRGT_ATTRB;
import com.canon.cusa.s21.framework.ZYP.web.ZYPEZDItemErrorUtil;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.table.S21TableColorController;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/**
 *<pre>
 * NMAL7190CommonLogic
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/14   Fujitsu         W.Honda         Create          N/A
 * 2016/05/31   Fujitsu         M.Nakamura      Update          QC#8354
 * 2017/08/14   Fujitsu         M.Ohno          Update          QC#18789(L3)
 * 2017/08/21   Fujitsu         M.Yamada        Update          QC#18785(L3)
 * 2018/12/05   Fujitsu         T.Noguchi       Update          QC#29324
 * 2023/04/20   Hitachi         H.Watanabe      Update          QC#61200
 *</pre>
 */
public class NMAL7190CommonLogic {

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7190BMsg
     */
    public static final void controlScreen(EZDCommonHandler handler, NMAL7190BMsg scrnMsg) {
        controlScreenFields(scrnMsg);
        setCmnBtnProp(handler, scrnMsg);
        setBizBtnProp(handler, scrnMsg);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7190BMsg
     */
    private static void setCmnBtnProp(EZDCommonHandler handler, NMAL7190BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_CMN_SAV[0], BTN_CMN_SAV[1], BTN_CMN_SAV[2], 0, null);
        if (hasUpdateFuncId()) {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_SUB[0], BTN_CMN_SUB[1], BTN_CMN_SUB[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_APL[0], BTN_CMN_APL[1], BTN_CMN_APL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_APR[0], BTN_CMN_APR[1], BTN_CMN_APR[2], 0, null);
        handler.setButtonProperties(BTN_CMN_RJT[0], BTN_CMN_RJT[1], BTN_CMN_RJT[2], 0, null);
        if (scrnMsg.A.getValidCount() > 0) {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 1, null);
        } else {
            handler.setButtonProperties(BTN_CMN_DWL[0], BTN_CMN_DWL[1], BTN_CMN_DWL[2], 0, null);
        }
        handler.setButtonProperties(BTN_CMN_DEL[0], BTN_CMN_DEL[1], BTN_CMN_DEL[2], 0, null);
        handler.setButtonProperties(BTN_CMN_CLR[0], BTN_CMN_CLR[1], BTN_CMN_CLR[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RST[0], BTN_CMN_RST[1], BTN_CMN_RST[2], 1, null);
        handler.setButtonProperties(BTN_CMN_RTN[0], BTN_CMN_RTN[1], BTN_CMN_RTN[2], 1, null);
    }

    /**
     * Initial Common Button properties.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7190BMsg
     */
    private static void setBizBtnProp(EZDCommonHandler handler, NMAL7190BMsg scrnMsg) {
        // 4th parameter(0:Inactive, 1:Active)
        handler.setButtonProperties(BTN_SEARCH[0], BTN_SEARCH[1], BTN_SEARCH[2], 1, null);
        handler.setButtonProperties(BTN_VIEW_USAGE[0], BTN_VIEW_USAGE[1], BTN_VIEW_USAGE[2], 1, null);
        handler.setButtonProperties(BTN_INSERT_ROW[0], BTN_INSERT_ROW[1], BTN_INSERT_ROW[2], 1, null);
        handler.setButtonProperties(BTN_UPLOAD[0], BTN_UPLOAD[1], BTN_UPLOAD[2], 1, null);

        handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 0, null);
        if (scrnMsg.A.getValidCount() > 0) {
            if (hasUpdateFuncId()) {
                handler.setButtonProperties(BTN_DELETE_ROW[0], BTN_DELETE_ROW[1], BTN_DELETE_ROW[2], 1, null);
            }

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                handler.setButtonEnabled(BTN_TARGET_FROM[0], i, false);
                handler.setButtonEnabled(BTN_TARGET_TO[0], i, false);

                if (!hasUpdateFuncId()) {
                    continue;
                }

                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpTrgtTpCd_A1)
                        && ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1)
                        && !PRC_GRP_TRGT_ATTRB.SIC_CODE.equals(scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1.getValue())) {
                    handler.setButtonEnabled(BTN_TARGET_FROM[0], i, true);
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpOpCd_A1)
                            && PRC_GRP_OP.BETWEEN.equals(scrnMsg.A.no(i).prcGrpOpCd_A1.getValue())) {
                        handler.setButtonEnabled(BTN_TARGET_TO[0], i, true);
                    }
                }
            }
        }

        // 2018/12/05 QC#29324 Add Start
        handler.setButtonEnabled(BTN_FILTER[0], false);
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_BK)) {
            handler.setButtonEnabled(BTN_FILTER[0], true);
        }
        // 2018/12/05 QC#29324 Add End
    }

    /**
     * <pre>
     * The initial state of the screen item is set.
     * </pre>
     * @param handler EZDCommonHandler
     * @param scrnMsg NMAL7190BMsg
     */
    private static final void controlScreenFields(NMAL7190BMsg scrnMsg) {
        // Header
        scrnMsg.prcGrpPk.setInputProtected(false);
        scrnMsg.prcGrpNm.setInputProtected(false);
        scrnMsg.prcGrpDescTxt.setInputProtected(false);
        if (ZYPCommonFunc.hasValue(scrnMsg.prcGrpPk_BK)) {
            scrnMsg.prcGrpTpCd.setInputProtected(true);
        } else {
            scrnMsg.prcGrpTpCd.setInputProtected(false);
        }
        scrnMsg.prcGrpTrxTpCd.setInputProtected(false);
        scrnMsg.actvFlg.setInputProtected(false);
        scrnMsg.effFromDt.setInputProtected(false);
        scrnMsg.effThruDt.setInputProtected(false);
        scrnMsg.xxFileData_UP.setInputProtected(false);


        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // 2023/04/20 QC#61200 Mod Start
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpDtlPk_A1)) {
//                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(true);
//            } else {
//                scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
//            }
            scrnMsg.A.no(i).xxChkBox_A1.setInputProtected(false);
            // 2023/04/20 QC#61200 Mod End
            scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1.setInputProtected(true);
            scrnMsg.A.no(i).prcGrpOpCd_A1.setInputProtected(true);
            if (!ZYPCommonFunc.hasValue(scrnMsg.prcGrpTpCd)) {
                scrnMsg.A.no(i).prcGrpTrgtTpCd_A1.setInputProtected(true);
            } else {
                scrnMsg.A.no(i).prcGrpTrgtTpCd_A1.setInputProtected(false);
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpTrgtTpCd_A1)) {
                    scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1.setInputProtected(false);
                    if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpTrgtAttrbCd_A1)) {
                        scrnMsg.A.no(i).prcGrpOpCd_A1.setInputProtected(false);
                    }
                }
            }
            scrnMsg.A.no(i).prcGrpFromTxt_A1.setInputProtected(false);
            // 2023/04/20 QC#61200 Add Start
            scrnMsg.A.no(i).dsAcctNm_A1.setInputProtected(true);
            // 2023/04/20 QC#61200 Add End
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prcGrpOpCd_A1)
                    && scrnMsg.A.no(i).prcGrpOpCd_A1.getValue().equals(PRC_GRP_OP.BETWEEN)) {
                scrnMsg.A.no(i).prcGrpThruTxt_A1.setInputProtected(false);
            } else {
                scrnMsg.A.no(i).prcGrpThruTxt_A1.clear();
                scrnMsg.A.no(i).prcGrpThruTxt_A1.setInputProtected(true);
            }
            scrnMsg.A.no(i).effFromDt_A1.setInputProtected(false);
            scrnMsg.A.no(i).effThruDt_A1.setInputProtected(false);
        }
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7190BMsg
     * @param scrnAMsgAry NMAL7190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void setRowsBGWithClear(NMAL7190BMsg scrnMsg, NMAL7190_ABMsgArray scrnAMsgAry, String tblId) {
        setRowsBGWithClear(scrnMsg, scrnAMsgAry, tblId, 1);
    }

    /**
     * Table has an id attribute of the argument row background color,
     * change the argument groupRows every alternate line groups.
     * 
     * @param scrnMsg     NMAL7190BMsg
     * @param scrnAMsgAry NMAL7190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     * @param grpRows     color reversal switch lines
     */
    public static void setRowsBGWithClear(NMAL7190BMsg scrnMsg, NMAL7190_ABMsgArray scrnAMsgAry, String tblId, int grpRows) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);

        // Color on
        tblColor.setAlternateRowsBG(tblId, scrnAMsgAry, grpRows);
    }

    /**
     * Table has an id attribute of the argument row background color back to the initial color. 
     * 
     * @param scrnMsg     NMAL7190BMsg
     * @param scrnAMsgAry NMAL7190_ABMsgArray
     * @param tblId       HTML id attribute given to the Table
     */
    public static void clearRowsBG(NMAL7190BMsg scrnMsg, NMAL7190_ABMsgArray scrnAMsgAry, String tblId) {

        S21TableColorController tblColor = new S21TableColorController(SCRN_ID_00, scrnMsg);

        // Color Clear
        tblColor.clearRowsBG(tblId, scrnAMsgAry);
    }

    /**
     * addCheckItemBizLayerErr
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemBizLayerErr(NMAL7190BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.prcGrpPk);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);

        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7190_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtTpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtAttrbCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpOpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpFromTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpThruTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpPrcdNum_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
        }
    }

    /**
     * addCheckItemForHeader
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForHeader(NMAL7190BMsg scrnMsg) {
        // Header
        scrnMsg.addCheckItem(scrnMsg.prcGrpPk);
        scrnMsg.addCheckItem(scrnMsg.prcGrpNm);
        scrnMsg.addCheckItem(scrnMsg.prcGrpDescTxt);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTpCd);
        scrnMsg.addCheckItem(scrnMsg.prcGrpTrxTpCd);
        scrnMsg.addCheckItem(scrnMsg.effFromDt);
        scrnMsg.addCheckItem(scrnMsg.effThruDt);
        scrnMsg.addCheckItem(scrnMsg.actvFlg);
    }

    /**
     * addCheckItemForDetail
     * 
     * @param scrnMsg Screen Msg
     */
    public static void addCheckItemForDetail(NMAL7190BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7190_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtTpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpTrgtAttrbCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpOpCd_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpFromTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpThruTxt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.prcGrpPrcdNum_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effFromDt_A1);
            scrnMsg.addCheckItem(scrnLineMsg.effThruDt_A1);
        }
    }

    /**
     * clearMandantoryCheck
     * 
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckHeader(NMAL7190BMsg scrnMsg) {

        // Header
        if (scrnMsg.prcGrpNm.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("prcGrpNm");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.prcGrpNm.clearErrorInfo();
            }
        }
        if (scrnMsg.effFromDt.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("effFromDt");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.effFromDt.clearErrorInfo();
            }
        }
        if (scrnMsg.prcGrpTpCd.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("prcGrpTpCd");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.prcGrpTpCd.clearErrorInfo();
            }
        }
        if (scrnMsg.prcGrpTrxTpCd.isError()) {
            EZDMessageInfo ezdMsgInfo = scrnMsg.getErrorInfo("prcGrpTrxTpCd");

            if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                scrnMsg.prcGrpTrxTpCd.clearErrorInfo();
            }
        }
    }

    /**
     * clearMandantoryCheck
     * 
     * @param scrnMsg Screen Msg
     */
    public static void clearMandantoryCheckDetail(NMAL7190BMsg scrnMsg) {
        // Detail
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            NMAL7190_ABMsg scrnLineMsg = scrnMsg.A.no(i);

            if (scrnLineMsg.prcGrpTrgtTpCd_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("prcGrpTrgtTpCd_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.prcGrpTrgtTpCd_A1.clearErrorInfo();
                }
            }
            if (scrnLineMsg.prcGrpTrgtAttrbCd_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("prcGrpTrgtAttrbCd_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.prcGrpTrgtAttrbCd_A1.clearErrorInfo();
                }
            }
            if (scrnLineMsg.prcGrpOpCd_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("prcGrpOpCd_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.prcGrpOpCd_A1.clearErrorInfo();
                }
            }
            if (scrnLineMsg.prcGrpFromTxt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("prcGrpFromTxt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.prcGrpFromTxt_A1.clearErrorInfo();
                }
            }
            if (scrnLineMsg.effFromDt_A1.isError()) {
                EZDMessageInfo ezdMsgInfo = scrnLineMsg.getErrorInfo("effFromDt_A1");

                if (ZYPEZDItemErrorUtil.getErrorDetail(ezdMsgInfo.getCode()) == EZDCommonConst.ERROR_HISSU) {
                    scrnLineMsg.effFromDt_A1.clearErrorInfo();
                }
            }
        }
    }

    /**
     * hasUpdateFuncId
     * @return boolean
     */
    public static boolean hasUpdateFuncId() {

        S21UserProfileService userProfSvc = S21UserProfileServiceFactory.getInstance().getService();

        List<String> funcList = userProfSvc.getFunctionCodeListForBizAppId(BIZ_ID);
        if (funcList == null || funcList.isEmpty()) {
            // ZZSM4300E=0,User @ has no permissions to operate this application.
            throw new S21AbendException("ZZSM4300E", new String[]{userProfSvc.getContextUserInfo().getUserId()});
        }

        if (funcList.contains("NMAL7190T020")) {
            return true;
        }

        return false;
    }

    /**
     * <pre>
     * The Header screen item is reset.
     * </pre>
     * @param scrnMsg NMAL7190BMsg
     */
    public static final void resetHeader(NMAL7190BMsg scrnMsg) {
        BigDecimal tmpPk = scrnMsg.prcGrpPk_BK.getValue();
        scrnMsg.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.prcGrpPk, tmpPk);
    }

    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param prcGrpCd       EZDBStringItem:Not Display Item
     * @param index          index of scrnMsg.A
     * @param glblCmpyCd     GlobalCompanyCode
     * @return Popup Parameter
     */
    public static final Object[] createPopPrm(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, EZDBStringItem prcGrpCd, int index, String glblCmpyCd) {

        ZYPTableUtil.clear(scrnMsg.P);

        if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue()) ) {
            scrnMsg.xxScrEventNm.setValue(POPUP_NAME_NWAL1130);
            return createPopPrmForNWAL1130(scrnMsg, prcGrpTxt, index, glblCmpyCd);
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            scrnMsg.xxScrEventNm.setValue(POPUP_NAME_NMAL6800);
            return createPopPrmForNMAL6800(scrnMsg, prcGrpTxt, index);
        } else if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            scrnMsg.xxScrEventNm.setValue(POPUP_NAME_NMAL6760);
            return createPopPrmForNMAL6760(scrnMsg, prcGrpTxt, index);
        // QC#18789 2017/08/14 add start
        } else if (PRC_GRP_TRGT_ATTRB.CHANNEL.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            scrnMsg.xxScrEventNm.setValue(POPUP_NAME_NMAL2550);
            return createPopPrmForNMAL2550(scrnMsg, prcGrpTxt, index);
        // QC#18789 2017/08/14 add end
        } else {
            scrnMsg.xxScrEventNm.setValue(POPUP_NAME_NMAL6050);
            return createPopPrmForNMAL6050(scrnMsg, prcGrpTxt, prcGrpCd, index, glblCmpyCd);
        }
    }

    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param index          index of scrnMsg.A
     * @return Popup Parameter
     */
    private static Object[] createPopPrmForNMAL6800(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, int index) {
        int i = 0;

        if (PRC_GRP_TRGT_ATTRB.ITEM_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            MDSETMsg tMsg = new MDSETMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        scrnMsg.P.no(i++).xxPopPrm.clear();
        if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            MDSE_ITEM_TPTMsg tMsg = new MDSE_ITEM_TPTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemTpCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            MDSE_ITEM_CLS_TPTMsg tMsg = new MDSE_ITEM_CLS_TPTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemClsTpCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            COA_PROJTMsg tMsg = new COA_PROJTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProjCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            COA_PRODTMsg tMsg = new COA_PRODTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProdCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        // 2023/04/20 QC#61200 Mod Start
//        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, NMAL6800Constant.XX_MODE_CD_08);
        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, XX_MODE_CD_08);
        // 2023/04/20 QC#61200 Mod End
        scrnMsg.P.setValidCount(i);

        Object[] params = new Object[NMAL6800_PRM_NUM];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }

        return params;
    }

    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param index          index of scrnMsg.A
     * @return Popup Parameter
     */
    private static Object[] createPopPrmForNMAL6760(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, int index) {
        int i = 0;

        if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("sellToCustCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        if (PRC_GRP_TRGT_ATTRB.ACCOUNT_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctNm"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        if (PRC_GRP_TRGT_ATTRB.LOCATION_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            SELL_TO_CUSTTMsg tMsg = new SELL_TO_CUSTTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("locNum"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        // Set Params
        Object[] params = new Object[NAML6760_PRM_NUM];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        return params;
    }

    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param index          index of scrnMsg.A
     * @param glblCmpyCd     GlobalCompanyCode
     * @return Popup Parameter
     */
    private static Object[] createPopPrmForNWAL1130(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, int index, String glblCmpyCd) {
        Object[] param = new Object[NWAL1130_PRM_NUM];
        // 0:Suffix
        String suffixP0 = "";
        // 1:Screen Name
        String scrnNmP1 = "";
        // 2:Table Name
        StringBuilder tblNmP2 = new StringBuilder();
        // 3:Criteria
        List<Object[]> whereListP3 = new ArrayList<Object[]>();
        // 4:Column
        List<Object[]> columnListP4 = new ArrayList<Object[]>();
        // 5:Sort
        List<Object[]> sortConditionListP5 = new ArrayList<Object[]>();
        // 6:Result(BMsg.R)
        ZYPTableUtil.clear(scrnMsg.R);

        if (PRC_GRP_TRGT_ATTRB.MODEL_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            suffixP0 = "";
            scrnNmP1 = "Model Name Search";

            tblNmP2.append(" SELECT ");
            tblNmP2.append(" MN.T_MDL_NM ");
            tblNmP2.append(" , MN.T_MDL_ID ");
            tblNmP2.append(" , MN.T_GLBL_CMPY_CD AS GLBL_CMPY_CD ");
            tblNmP2.append(" , MN.EZCANCELFLAG ");
            tblNmP2.append(" FROM MDL_NM MN ");
            tblNmP2.append(" WHERE MN.T_GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
            tblNmP2.append(" AND MN.EZCANCELFLAG = '0' ");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Model ID";
            whereArray0[1] = "T_MDL_ID";
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                MDL_NMTMsg tMsg = new MDL_NMTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("t_MdlId"))) {
                    whereArray0[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                whereArray0[2] = null;
            }
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Model Name";
            whereArray1[1] = "T_MDL_NM";
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                whereArray1[2] = null;
            } else {
                MDL_NMTMsg tMsg = new MDL_NMTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("t_MdlNm"))) {
                    whereArray1[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            }
            whereArray1[2] = prcGrpTxt.getValue();
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Model ID";
            columnArray0[1] = "T_MDL_ID";
            columnArray0[2] = BigDecimal.valueOf(22);
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Model Name";
            columnArray1[1] = "T_MDL_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray1);

            Object[] sortConditionArray = new Object[CMN_PRM_SORT_NUM];
            sortConditionArray[0] = "T_MDL_NM";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);

        } else if (PRC_GRP_TRGT_ATTRB.MODEL_SERIES_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            suffixP0 = "";
            scrnNmP1 = "Model Group Name Search";

            tblNmP2.append(" DS_MDL_GRP ");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Model Group ID";
            whereArray0[1] = "MDL_GRP_ID";
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
            } else {
                whereArray0[2] = null;
            }
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Model Group Name";
            whereArray1[1] = "MDL_GRP_DESC_TXT";
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                whereArray1[2] = null;
            } else {
                DS_MDL_GRPTMsg tMsg = new DS_MDL_GRPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdlGrpDescTxt"))) {
                    whereArray1[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            }
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Model Group ID";
            columnArray0[1] = "MDL_GRP_ID";
            columnArray0[2] = BigDecimal.valueOf(22);
            columnArray0[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Model Group Name";
            columnArray1[1] = "MDL_GRP_DESC_TXT";
            columnArray1[2] = BigDecimal.valueOf(90);
            columnArray1[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray1);

            Object[] sortConditionArray = new Object[CMN_PRM_SORT_NUM];
            sortConditionArray[0] = "MDL_GRP_DESC_TXT";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);
        } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            suffixP0 = "";
            scrnNmP1 = "CSMP Search";

            tblNmP2.append(" CSMP_CONTR ");

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Account Number";
            whereArray0[1] = "DS_ACCT_NUM";
            whereArray0[2] = null;
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Account Name";
            whereArray1[1] = "DS_ACCT_NM";
            whereArray1[2] = null;
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] whereArray2 = new Object[CMN_PRM_WHERE_NUM];
            whereArray2[0] = "CSMP Number";
            whereArray2[1] = "CSMP_NUM";
            if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                CSMP_CONTRTMsg tMsg = new CSMP_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("csmpNum"))) {
                    whereArray2[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                CSMP_CONTRTMsg tMsg = new CSMP_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("csmpNum"))) {
                    whereArray2[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                whereArray2[2] = null;
            }
            whereArray2[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray2);

            Object[] whereArray3 = new Object[CMN_PRM_WHERE_NUM];
            whereArray3[0] = "Dealer Ref Number";
            whereArray3[1] = "DLR_REF_NUM";
            if (PRC_GRP_TRGT_ATTRB.CSA_CSMP_REFERENCE_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                CSMP_CONTRTMsg tMsg = new CSMP_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dlrRefNum"))) {
                    whereArray3[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else if (PRC_GRP_TRGT_ATTRB.CSMP_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                CSMP_CONTRTMsg tMsg = new CSMP_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dlrRefNum"))) {
                    whereArray3[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                whereArray3[2] = null;
            }
            whereArray3[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray3);

            Object[] whereArray4 = new Object[CMN_PRM_WHERE_NUM];
            whereArray4[0] = "Price Contr Number";
            whereArray4[1] = "PRC_CONTR_NUM";
            whereArray4[2] = null;
            whereArray4[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray4);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Account Number";
            columnArray0[1] = "DS_ACCT_NUM";
            columnArray0[2] = BigDecimal.valueOf(10);
            columnArray0[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Account Name";
            columnArray1[1] = "DS_ACCT_NM";
            columnArray1[2] = BigDecimal.valueOf(50);
            columnArray1[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray1);

            Object[] columnArray2 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray2[0] = "CSMP Number";
            columnArray2[1] = "CSMP_NUM";
            columnArray2[2] = BigDecimal.valueOf(15);
            columnArray2[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray2);

            Object[] columnArray3 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray3[0] = "Dealer Ref Number";
            columnArray3[1] = "DLR_REF_NUM";
            columnArray3[2] = BigDecimal.valueOf(15);
            columnArray3[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray3);

            Object[] columnArray4 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray4[0] = "Price Contr Number";
            columnArray4[1] = "PRC_CONTR_NUM";
            columnArray4[2] = BigDecimal.valueOf(50);
            columnArray4[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray4);

            Object[] sortConditionArray = new Object[CMN_PRM_SORT_NUM];
            sortConditionArray[0] = "CSMP_NUM";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);
        } else if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.ASSOCIATION_NAME.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            suffixP0 = "";
            scrnNmP1 = "Price Contract Search";

            tblNmP2.append(" SELECT ");
            tblNmP2.append(" PC.PRC_CONTR_NM ");
            tblNmP2.append(" , PC.PRC_CONTR_NUM ");
            tblNmP2.append(" , PC.GLBL_CMPY_CD ");
            tblNmP2.append(" , PC.EZCANCELFLAG ");
            tblNmP2.append(" FROM PRC_CONTR PC ");
            tblNmP2.append(" WHERE PC.GLBL_CMPY_CD = '").append(glblCmpyCd).append("' ");
            tblNmP2.append(" AND PC.EZCANCELFLAG = '0' ");
            tblNmP2.append(" AND PC.EFF_FROM_DT <= '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' ");
            // 2016/05/31 QC#8354 Mod Start
            tblNmP2.append(" AND (PC.EFF_THRU_DT >= '").append(ZYPDateUtil.getSalesDate(glblCmpyCd)).append("' ");
            tblNmP2.append("      OR PC.EFF_THRU_DT IS NULL)");
            // 2016/05/31 QC#8354 Mod End

            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Price Contract Code";
            whereArray0[1] = "PRC_CONTR_NUM";
            if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && !ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                PRC_CONTRTMsg tMsg = new PRC_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("prcContrNum"))) {
                    whereArray0[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                whereArray0[2] = null;
            }
            whereArray0[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Price Contract Name";
            whereArray1[1] = "PRC_CONTR_NM";
            if (PRC_GRP_TRGT_ATTRB.CSA_PRICE_CONTRACT_NUMBER.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                PRC_CONTRTMsg tMsg = new PRC_CONTRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("prcContrNm"))) {
                    whereArray1[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                whereArray1[2] = null;
            }
            whereArray1[3] = ZYPConstant.FLG_ON_Y;
            whereListP3.add(whereArray1);

            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Price Contract Code";
            columnArray0[1] = "PRC_CONTR_NUM";
            columnArray0[2] = BigDecimal.valueOf(50);
            columnArray0[3] = ZYPConstant.FLG_ON_Y;
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Price Contract Name";
            columnArray1[1] = "PRC_CONTR_NM";
            columnArray1[2] = BigDecimal.valueOf(90);
            columnArray1[3] = ZYPConstant.FLG_OFF_N;
            columnListP4.add(columnArray1);

            Object[] sortConditionArray = new Object[CMN_PRM_SORT_NUM];
            sortConditionArray[0] = "PRC_CONTR_NUM";
            sortConditionArray[1] = "ASC";
            sortConditionListP5.add(sortConditionArray);
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())
                || PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            suffixP0 = "";
            scrnNmP1 = "Product Hierarchy Popup";

            String tableNm = "PROD_CTRL";
            String columnCdNm = "PROD_CTRL_CD";
            String columnNmNm = "PROD_CTRL_DESC_TXT";
            
            tblNmP2.append(" SELECT");
            tblNmP2.append(" DISTINCT");
            tblNmP2.append(" H.GLBL_CMPY_CD");
            tblNmP2.append(",H.EZCANCELFLAG");
            tblNmP2.append(",H.").append(columnCdNm);
            tblNmP2.append(",H.").append(columnNmNm);
            tblNmP2.append(" FROM ").append(tableNm).append(" H ");
            tblNmP2.append(" WHERE");
            tblNmP2.append(" H.GLBL_CMPY_CD = '").append(glblCmpyCd).append("'");
            tblNmP2.append(" AND H.EZCANCELFLAG = '").append("0").append("'");
            if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE_GROUP).append("'");
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LINE).append("'");
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_2).append("'");
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3).append("'");
            } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
                tblNmP2.append(" AND H.MDSE_STRU_ELMNT_TP_CD = '").append(MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_4).append("'");
            }
            Object[] whereArray0 = new Object[CMN_PRM_WHERE_NUM];
            whereArray0[0] = "Prod Hierarchy";
            whereArray0[1] = columnCdNm;
            whereArray0[2] = prcGrpTxt.getValue();
            whereArray0[3] = "Y";
            whereListP3.add(whereArray0);

            Object[] whereArray1 = new Object[CMN_PRM_WHERE_NUM];
            whereArray1[0] = "Prod Hierarchy Name";
            whereArray1[1] = columnNmNm;
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                whereArray1[2] = null;
            } else {
                PROD_CTRLTMsg tMsg = new PROD_CTRLTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("prodCtrlDescTxt"))) {
                    whereArray1[2] = prcGrpTxt.getValue();
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            }
            whereArray1[3] = "Y";
            whereListP3.add(whereArray1);
            
            Object[] columnArray0 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray0[0] = "Prod Hierarchy";
            columnArray0[1] = columnCdNm;
            columnArray0[2] = BigDecimal.valueOf(12);
            columnArray0[3] = "Y";
            columnListP4.add(columnArray0);

            Object[] columnArray1 = new Object[CMN_PRM_COLUMN_NUM];
            columnArray1[0] = "Prod Hierarchy Name";
            columnArray1[1] = columnNmNm;
            columnArray1[2] = BigDecimal.valueOf(20);
            columnArray1[3] = "N";
            columnListP4.add(columnArray1);

            Object[] sortConditionArray0 = new Object[2];
            sortConditionArray0[0] = columnCdNm;
            sortConditionArray0[1] = "ASC";
            sortConditionListP5.add(sortConditionArray0);
        }

        param[0] = suffixP0;
        param[1] = scrnNmP1;
        param[2] = tblNmP2.toString();
        param[3] = whereListP3;
        param[4] = columnListP4;
        param[5] = sortConditionListP5;
        param[6] = scrnMsg.R;

        return param;
    }

    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param prcGrpCd       EZDBStringItem:Not Display Item
     * @param index          index of scrnMsg.A
     * @param glblCmpyCd     GlobalCompanyCode
     * @return Popup Parameter
     */
    public static Object[] createPopPrmForNMAL6050(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, EZDBStringItem prcGrpCd, int index, String glblCmpyCd) {
        Object[] params = new Object[NAML6050_PRM_NUM];
        int i = 0;

        if (PRC_GRP_TRGT_ATTRB.ITEM_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_TP");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_TP_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Type Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Type Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Type Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                MDSE_ITEM_TPTMsg tMsg = new MDSE_ITEM_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemTpDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                MDSE_ITEM_TPTMsg tMsg = new MDSE_ITEM_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemTpCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_CLASSIFICATION.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_CLS_TP");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_CLS_TP_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_CLS_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MDSE_ITEM_CLS_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Classification Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Classification Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Classification Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Classification Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Classification Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                MDSE_ITEM_CLS_TPTMsg tMsg = new MDSE_ITEM_CLS_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemClsTpDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                MDSE_ITEM_CLS_TPTMsg tMsg = new MDSE_ITEM_CLS_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mdseItemClsTpCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.MERCHANDISE_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROJ_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Merchandise Type Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                COA_PROJTMsg tMsg = new COA_PROJTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProjDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                COA_PROJTMsg tMsg = new COA_PROJTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProjCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_CODE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROD_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROD_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "COA_PROD_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Product Code Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Product Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Product Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Product Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Product Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                COA_PRODTMsg tMsg = new COA_PRODTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProdDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                COA_PRODTMsg tMsg = new COA_PRODTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaProdCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.ITEM_MARKETING_MODEL.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MKT_MDL");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MKT_MDL_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MKT_MDL_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "MKT_MDL_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Marketing Model Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Marketing Model Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Marketing Model Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Marketing Model Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Item Marketing Model Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                MKT_MDLTMsg tMsg = new MKT_MDLTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mktMdlDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                MKT_MDLTMsg tMsg = new MKT_MDLTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("mktMdlCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
//        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_1.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "ZEROTH_PROD_HRCH");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "ZEROTH_PROD_HRCH_CD");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "ZEROTH_PROD_HRCH_DESC_TXT");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "ZEROTH_PROD_HRCH_SORT_NUM");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level1 Prod Hierarchy Search");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level1 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level1 Prod Hierarchy Name");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level1 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level1 Prod Hierarchy Name");
//            scrnMsg.P.setValidCount(i);
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
//                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//                ZEROTH_PROD_HRCHTMsg tMsg = new ZEROTH_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("zerothProdHrchDescTxt"))) {
//                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//            } else {
//                ZEROTH_PROD_HRCHTMsg tMsg = new ZEROTH_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("zerothProdHrchCd"))) {
//                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//            }
//        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_2.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FIRST_PROD_HRCH");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FIRST_PROD_HRCH_CD");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FIRST_PROD_HRCH_DESC_TXT");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FIRST_PROD_HRCH_SORT_NUM");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level2 Prod Hierarchy Search");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level2 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level2 Prod Hierarchy Name");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level2 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level2 Prod Hierarchy Name");
//            scrnMsg.P.setValidCount(i);
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
//                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//                FIRST_PROD_HRCHTMsg tMsg = new FIRST_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("firstProdHrchDescTxt"))) {
//                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//            } else {
//                FIRST_PROD_HRCHTMsg tMsg = new FIRST_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("firstProdHrchCd"))) {
//                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//            }
//        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_3.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "SCD_PROD_HRCH");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "SCD_PROD_HRCH_CD");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "SCD_PROD_HRCH_DESC_TXT");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "SCD_PROD_HRCH_SORT_NUM");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level3 Prod Hierarchy Search");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level3 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level3 Prod Hierarchy Name");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level3 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level3 Prod Hierarchy Name");
//            scrnMsg.P.setValidCount(i);
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
//                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//                SCD_PROD_HRCHTMsg tMsg = new SCD_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("scdProdHrchDescTxt"))) {
//                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//            } else {
//                SCD_PROD_HRCHTMsg tMsg = new SCD_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("scdProdHrchCd"))) {
//                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//            }
//        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_4.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "THIRD_PROD_HRCH");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "THIRD_PROD_HRCH_CD");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "THIRD_PROD_HRCH_DESC_TXT");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "THIRD_PROD_HRCH_SORT_NUM");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level4 Prod Hierarchy Search");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level4 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level4 Prod Hierarchy Name");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level4 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level4 Prod Hierarchy Name");
//            scrnMsg.P.setValidCount(i);
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
//                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//                THIRD_PROD_HRCHTMsg tMsg = new THIRD_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("thirdProdHrchDescTxt"))) {
//                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//            } else {
//                THIRD_PROD_HRCHTMsg tMsg = new THIRD_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("thirdProdHrchCd"))) {
//                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//            }
//        } else if (PRC_GRP_TRGT_ATTRB.PRODUCT_HIERARCHY_5.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FRTH_PROD_HRCH");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FRTH_PROD_HRCH_CD");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FRTH_PROD_HRCH_DESC_TXT");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "FRTH_PROD_HRCH_SORT_NUM");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level5 Prod Hierarchy Search");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level5 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level5 Prod Hierarchy Name");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level5 Prod Hierarchy Code");
//            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Level5 Prod Hierarchy Name");
//            scrnMsg.P.setValidCount(i);
//            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
//                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
//                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//                FRTH_PROD_HRCHTMsg tMsg = new FRTH_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("frthProdHrchDescTxt"))) {
//                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//            } else {
//                FRTH_PROD_HRCHTMsg tMsg = new FRTH_PROD_HRCHTMsg();
//                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("frthProdHrchCd"))) {
//                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
//                } else {
//                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
//                    scrnMsg.addCheckItem(prcGrpTxt);
//                    scrnMsg.putErrorScreen();
//                }
//                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
//            }
        } else if (PRC_GRP_TRGT_ATTRB.CLASSIFICATION.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_CLS");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_CLS_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_CLS_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_CLS_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Classification Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Classification Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Classification Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Classification Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Classification Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ACCT_CLSTMsg tMsg = new DS_ACCT_CLSTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctClsDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ACCT_CLSTMsg tMsg = new DS_ACCT_CLSTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctClsCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.CATEGORY.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_TP");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_TP_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Type Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Type Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Type Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ACCT_TPTMsg tMsg = new DS_ACCT_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctTpDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ACCT_TPTMsg tMsg = new DS_ACCT_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctTpCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.DEALER_CODE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_DLR");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_DLR_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_DLR_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ACCT_DLR_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Dealer Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Dealer Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Dealer Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Dealer Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Account Dealer Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ACCT_DLRTMsg tMsg = new DS_ACCT_DLRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctDlrDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ACCT_DLRTMsg tMsg = new DS_ACCT_DLRTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsAcctDlrCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_CATEGORY.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_CATG");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_CATG_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_CATG_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_CATG_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Category Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Category Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Category Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Category Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Category Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ORD_CATGTMsg tMsg = new DS_ORD_CATGTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdCatgDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ORD_CATGTMsg tMsg = new DS_ORD_CATGTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdCatgCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.ORDER_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_TP");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_TP_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_TP_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_TP_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Type Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Type Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Type Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Type Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdTpDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ORD_TPTMsg tMsg = new DS_ORD_TPTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdTpCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        } else if (PRC_GRP_TRGT_ATTRB.LINE_CATEGORY_TYPE.equals(scrnMsg.A.no(index).prcGrpTrgtAttrbCd_A1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_LINE_CATG");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_LINE_CATG_CD");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_LINE_CATG_DESC_TXT");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "DS_ORD_LINE_CATG_SORT_NUM");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Line Category Search");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Line Category Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Line Category Name");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Line Category Code");
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, "Order Line Category Name");
            scrnMsg.P.setValidCount(i);
            if (ZYPCommonFunc.hasValue(scrnMsg.A.no(index).prcGrpConvFlg_A1)
                    && ZYPConstant.FLG_ON_Y.equals(scrnMsg.A.no(index).prcGrpConvFlg_A1.getValue())) {
                params[NAML6050_PRM_CODE_NUM] = scrnMsg.P.no(i++).xxPopPrm;
                DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdLineCatgDescTxt"))) {
                    params[NAML6050_PRM_NAME_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
            } else {
                DS_ORD_LINE_CATGTMsg tMsg = new DS_ORD_LINE_CATGTMsg();
                if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("dsOrdLineCatgCd"))) {
                    params[NAML6050_PRM_CODE_NUM] = prcGrpTxt;
                } else {
                    prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                    scrnMsg.addCheckItem(prcGrpTxt);
                    scrnMsg.putErrorScreen();
                }
                params[NAML6050_PRM_NAME_NUM] = scrnMsg.P.no(i++).xxPopPrm;
            }
        }

        for (int j = 0; j < scrnMsg.P.getValidCount(); j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        return params;
    }

    /**
     * <pre>
     * checkValDigit
     * </pre>
     * @param targetLength int
     * @param checkAttrb EZDItemAttribute
     * @return result False : NG True : OK
     */
    public static boolean checkValDigit(int targetLength, EZDItemAttribute checkAttrb) {

        if (checkAttrb == null) {
            return false;
        }

        if (targetLength > checkAttrb.getDigit()) {
            return false;
        }
        return true;
    }

    // QC#18789 2017/08/14 add start
    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @param prcGrpTxt      EZDBStringItem:Display Item
     * @param index          index of scrnMsg.A
     * @return Popup Parameter
     */
    private static Object[] createPopPrmForNMAL2550(NMAL7190BMsg scrnMsg, EZDBStringItem prcGrpTxt, int index) {
        int i = 0;

        ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, BIZ_ID);
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();

        if (ZYPCommonFunc.hasValue(prcGrpTxt)) {
            COA_CHTMsg tMsg = new COA_CHTMsg();
            if (checkValDigit(prcGrpTxt.getValue().length(), tMsg.getAttr("coaChCd"))) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(i++).xxPopPrm, prcGrpTxt);
            } else {
                prcGrpTxt.setErrorInfo(1, NMAM0052E, new String[] {prcGrpTxt.getValue()});
                scrnMsg.addCheckItem(prcGrpTxt);
                scrnMsg.putErrorScreen();
            }
        } else {
            scrnMsg.P.no(i++).xxPopPrm.clear();
        }
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.no(i++).xxPopPrm.clear();
        scrnMsg.P.setValidCount(i);

        // Set Params
        Object[] params = new Object[NMAL2550_PRM_NUM];
        for (int j = 0; j < scrnMsg.P.getValidCount() && j < params.length; j++) {
            params[j] = scrnMsg.P.no(j).xxPopPrm;
        }
        return params;
    }
    // QC#18789 2017/08/14 add end

    // 2018/12/05 QC#29324 Add Start
    /**
     * <pre>
     * The Popup parameter is set.
     * </pre>
     * @param scrnMsg        NMAL7190BMsg
     * @return Popup Parameter
     */
    public static Object[] setParamFilterPopupNMAL7440(NMAL7190BMsg scrnMsg) {
        Object[] param = new Object[NMAL7440_PRM_NUM];
        int i = 0;
        scrnMsg.F.setValidCount(1);

        param[i++] = scrnMsg.prcGrpPk;
        param[i++] = scrnMsg.prcGrpNm;
        param[i++] = scrnMsg.prcGrpTpCd;
        param[i++] = scrnMsg.F.no(0).prcGrpTrgtTpCd;
        param[i++] = scrnMsg.F.no(0).prcGrpTrgtAttrbCd;
        param[i++] = scrnMsg.F.no(0).xxPrcQlfyValSrchTxt_TF;
        param[i++] = scrnMsg.F.no(0).xxPrcQlfyValSrchTxt_TT;
        param[i++] = scrnMsg.F.no(0).prcGrpPrcdNum;
        param[i++] = scrnMsg.F.no(0).effFromDt_H1;
        param[i++] = scrnMsg.F.no(0).effFromDt_H2;
        param[i++] = scrnMsg.F.no(0).effThruDt_H1;
        param[i++] = scrnMsg.F.no(0).effThruDt_H2;
        // 2023/04/20 QC#61200 Add Start
        param[i++] = scrnMsg.F.no(0).dsAcctNm;
        // 2023/04/20 QC#61200 Add End

        return param;
    }

    /**
     * initial Control Screen.
     * 
     * @param handler Event Handler
     * @param scrnMsg NMAL7190BMsg
     */
    public static void initControlScreen(EZDCommonHandler handler, NMAL7190BMsg scrnMsg) {

        handler.setButtonEnabled(BTN_FILTER[0], false);
    }
    // 2018/12/05 QC#29324 Add End
}

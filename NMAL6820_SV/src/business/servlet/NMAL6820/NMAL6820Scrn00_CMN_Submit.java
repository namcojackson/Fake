/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.DEF_EFF_DATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_CREATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SHIP_TO_CD;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_CTRY;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_CTY_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_DSBL_LOC_FLG;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_EFF_THRU;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_FIRST_LINE_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WH_GROUP_NAME;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_POST;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PRTY_LOC_FLG;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PRTY_SELECTED;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_ST;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SUPPLIER;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SUPPLIER_SITE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SWH_DESCRIPTION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SWH_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WMS_WH_CD;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NMAM0014E;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NMAM0051E;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NMAM0248E;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NMAM0803E;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NMAM0836E;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SWH_DSBL_CHK_BOX_NAME_VALUE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SWH_PRTY_CHK_BOX_NAME_VALUE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_SWH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.ZZM9000E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.calendar.ZYPDateUtil;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PROCR_TP;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RTL_WH_CATG;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.WH_SYS_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Submit
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#2380
 * 02/11/2016   CSAI            D.Fukaya        Update          QC#1598
 * 02/12/2016   CSAI            D.Fukaya        Update          QC#1600
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC#2369
 * 02/29/2016   CSAI            D.Fukaya        Update          QC#1596/2363/2365
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 * 10/25/2016   CITS            Y.IWASAKI       Update          QC#15120/2371
 * 02/07/2017   CITS            Y.IWASAKI       Update          QC#17233
 * 04/09/2019   Fujitsu         T.Ogura         Update          QC#28667
 * 09/17/2020   CITS            J.Evangelista   Update          QC#57659
 *</pre>
 */
public class NMAL6820Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        String nowDplyTab = scrnMsg.xxDplyTab.getValue();
        scrnMsg.clearAllGUIAttribute(SCRN_ID);

        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, scrnMsg.whCd_H1.getValue());
        }

        /**********************************************/
        /************ Header **********************/
        /**********************************************/
        // WH System Type
        if (WH_SYS_TP.WMS.equals(scrnMsg.whSysTpCd_H1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.wmsWhCd_H1.getValue())) {

            scrnMsg.wmsWhCd_H1.setErrorInfo(1, NMAM0014E, new String[] {NAME_FOR_MESSAGE_WMS_WH_CD });
            scrnMsg.addCheckItem(scrnMsg.wmsWhCd_H1);
        }
        if (WH_SYS_TP.WMS.equals(scrnMsg.whSysTpCd_H1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.physWhCd_H1.getValue())) {

            scrnMsg.physWhCd_H1.setErrorInfo(1, NMAM0014E, new String[] {NAME_FOR_MESSAGE_WH_GROUP_NAME });
            scrnMsg.addCheckItem(scrnMsg.physWhCd_H1);
        }

        // Effective Date
        if (!ZYPCommonFunc.hasValue(scrnMsg.effThruDt_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.effThruDt_H1, DEF_EFF_DATE);
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.whCd_H1.getValue())) {

            if (scrnMsg.effThruDt_H1.getValue().compareTo(ZYPDateUtil.getSalesDate()) < 0) {

                scrnMsg.effThruDt_H1.setErrorInfo(1, NMAM0803E, new String[] {NAME_FOR_MESSAGE_EFF_THRU });
                scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
            }
        }
        if (scrnMsg.effThruDt_H1.getValue().compareTo(scrnMsg.effFromDt_H1.getValue()) < 0) {

            scrnMsg.effThruDt_H1.setErrorInfo(1, NMAM0803E, new String[] {NAME_FOR_MESSAGE_EFF_THRU });
            scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        }

        NMAL6820CommonLogic.checkDate(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhNm_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhDescTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd_H2);
        scrnMsg.addCheckItem(scrnMsg.altPsnCd_H2);
        scrnMsg.addCheckItem(scrnMsg.telNum_H1);
        scrnMsg.addCheckItem(scrnMsg.faxNum_H1);
        scrnMsg.addCheckItem(scrnMsg.invtyAcctCd_H1);
        scrnMsg.addCheckItem(scrnMsg.invtyOwnrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.whOwnrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.whSysTpCd_H1);
        scrnMsg.addCheckItem(scrnMsg.wmsWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.physWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.autoSoDropFlg_H1);
        scrnMsg.addCheckItem(scrnMsg.firstRefCmntTxt_H1);
        scrnMsg.addCheckItem(scrnMsg.effFromDt_H1);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H1);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ST);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_ET);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_CT);
        // START 2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_FT);
        scrnMsg.addCheckItem(scrnMsg.xxScrItem7Txt_UT);
        // END   2020/09/17 J.Evangelista [QC#57659,ADD]
        scrnMsg.addCheckItem(scrnMsg.tmZoneCd_H1);
        scrnMsg.addCheckItem(scrnMsg.geoCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd_H1);

        // START 2019/04/09 T.Ogura [QC#28667,ADD]
        // Supplier/Supplier Site
        if (RTL_WH_CATG.DROP_RMA.equals(scrnMsg.rtlWhCatgCd_H1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndCd)) {
                scrnMsg.prntVndCd.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_SUPPLIER });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.vndCd)) {
                scrnMsg.vndCd.setErrorInfo(1, ZZM9000E, new String[] {NAME_FOR_MESSAGE_SUPPLIER_SITE });
            }
        }
        scrnMsg.addCheckItem(scrnMsg.prntVndCd);
        scrnMsg.addCheckItem(scrnMsg.vndCd);
        // END   2019/04/09 T.Ogura [QC#28667,ADD]


        /**********************************************/
        /************ Address *********************/
        /**********************************************/
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ADDR);
        // Ship-To
        if (MODE_CREATE.equals(scrnMsg.xxModeCd_G1.getValue()) && !ZYPCommonFunc.hasValue(scrnMsg.locNum_S1)) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.firstLineAddr_S1)) {
                scrnMsg.firstLineAddr_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_FIRST_LINE_ADDR });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.ctyAddr_S1)) {
                scrnMsg.ctyAddr_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_CTY_ADDR });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.stCd_S1)) {
                scrnMsg.stCd_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_ST });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.postCd_S1)) {
                scrnMsg.postCd_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_POST });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.ctryCd_S1)) {
                scrnMsg.ctryCd_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_CTRY });
            }
        } else {
            if (!ZYPCommonFunc.hasValue(scrnMsg.locNum_S1)) {
                scrnMsg.locNum_S1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SHIP_TO_CD });
            }
        }
        // Return-To
        if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_R1.getValue())) {

            if (!ZYPCommonFunc.hasValue(scrnMsg.locNum_R1)) {

                if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnToFirstLineAddr_R1)) {
                    scrnMsg.rtrnToFirstLineAddr_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_FIRST_LINE_ADDR });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnToCtyAddr_R1)) {
                    scrnMsg.rtrnToCtyAddr_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_CTY_ADDR });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnToStCd_R1)) {
                    scrnMsg.rtrnToStCd_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_ST });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.rtrnToPostCd_R1)) {
                    scrnMsg.rtrnToPostCd_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_POST });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.ctryCd_R1)) {
                    scrnMsg.ctryCd_R1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_CTRY });
                }
            }
        }

        // Postal Code Format check
        NMAL6820CommonLogic.checkPostCd(scrnMsg);

        scrnMsg.addCheckItem(scrnMsg.locNum_S1);
        scrnMsg.addCheckItem(scrnMsg.dsLocNm_S1);
        scrnMsg.addCheckItem(scrnMsg.addlLocNm_S1);
        scrnMsg.addCheckItem(scrnMsg.firstLineAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.scdLineAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.thirdLineAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.frthLineAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.ctyAddr_S1);
        scrnMsg.addCheckItem(scrnMsg.cntyPk_S1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_S1);
        scrnMsg.addCheckItem(scrnMsg.stCd_S1);
        scrnMsg.addCheckItem(scrnMsg.provNm_S1);
        scrnMsg.addCheckItem(scrnMsg.postCd_S1);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_S1);
        scrnMsg.addCheckItem(scrnMsg.vndLocCd_S1);
        scrnMsg.addCheckItem(scrnMsg.locNum_R1);
        scrnMsg.addCheckItem(scrnMsg.dsLocNm_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToAddlLocNm_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToFirstLineAddr_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToScdLineAddr_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToThirdLineAddr_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToFrthLineAddr_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToCtyAddr_R1);
        scrnMsg.addCheckItem(scrnMsg.cntyPk_R1);
        scrnMsg.addCheckItem(scrnMsg.cntyNm_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToStCd_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToProvNm_R1);
        scrnMsg.addCheckItem(scrnMsg.rtrnToPostCd_R1);
        scrnMsg.addCheckItem(scrnMsg.ctryCd_R1);
        scrnMsg.addCheckItem(scrnMsg.vndLocCd_R1);
        scrnMsg.addCheckItem(scrnMsg.poRcptRteTpCd_S1);
        scrnMsg.addCheckItem(scrnMsg.rmaRcptRteTpCd_S1);

        scrnMsg.putErrorScreen();

        /**********************************************/
        /************ Sourcing ********************/
        /**********************************************/
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SRC);

        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_S1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_SD)) {
                scrnMsg.prntVndNm_SD.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.vndNm_SD)) {
                scrnMsg.vndNm_SD.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
        }

        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_E1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_SE)) {
                scrnMsg.prntVndNm_SE.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.vndNm_SE)) {
                scrnMsg.vndNm_SE.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
        }

        if (PROCR_TP.WAREHOUSE.equals(scrnMsg.procrTpCd_R1.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.prntVndNm_SR)) {
                scrnMsg.prntVndNm_SR.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.vndNm_SR)) {
                scrnMsg.vndNm_SR.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }
        }

        scrnMsg.addCheckItem(scrnMsg.prntVndNm_SD);
        scrnMsg.addCheckItem(scrnMsg.vndNm_SD);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm_SE);
        scrnMsg.addCheckItem(scrnMsg.vndNm_SE);
        scrnMsg.addCheckItem(scrnMsg.prntVndNm_SR);
        scrnMsg.addCheckItem(scrnMsg.vndNm_SR);
        scrnMsg.addCheckItem(scrnMsg.techMblMsgAddr);
        scrnMsg.putErrorScreen();

        /**********************************************/
        /************ Sub-WH **********************/
        /**********************************************/
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SWH);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

            if (i == scrnMsg.A.length()) {
                break;
            }

            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rtlSwhNm_A1)) {
                scrnMsg.A.no(i).rtlSwhNm_A1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_NM });
            }
            if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).rtlSwhDescTxt_A1)) {
                scrnMsg.A.no(i).rtlSwhDescTxt_A1.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_SWH_DESCRIPTION });
            }

            if (PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A1.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_AS)) {
                    scrnMsg.A.no(i).prntVndNm_AS.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).vndNm_AS)) {
                    scrnMsg.A.no(i).vndNm_AS.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC });
                }
            }

            if (PROCR_TP.WAREHOUSE.equals(scrnMsg.A.no(i).procrTpCd_A2.getValue())) {
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).prntVndNm_AR)) {
                    scrnMsg.A.no(i).prntVndNm_AR.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN });
                }
                if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).vndNm_AR)) {
                    scrnMsg.A.no(i).vndNm_AR.setErrorInfo(1, NMAM0836E, new String[] {NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN });
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhNm_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhDescTxt_A1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_AS);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndNm_AS);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_AR);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).vndNm_AR);
        }

        if (scrnMsg.A.getValidCount() > 0) {

            List<Integer> chkYList_P = ZYPTableUtil.getSelectedRows(scrnMsg.A, SWH_PRTY_CHK_BOX_NAME_VALUE, ZYPConstant.FLG_ON_Y);
            List<Integer> chkYList_D = ZYPTableUtil.getSelectedRows(scrnMsg.A, SWH_DSBL_CHK_BOX_NAME_VALUE, ZYPConstant.FLG_ON_Y);

            // Priority Flag
            if (chkYList_P.isEmpty()) {

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    scrnMsg.A.no(i).xxChkBox_P1.setErrorInfo(1, NMAM0014E, new String[] {NAME_FOR_MESSAGE_PRTY_LOC_FLG });
                    scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_P1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SWH);
                }
                // Priority flag can not be selected for more than 1
                // line.
            } else if (chkYList_P.size() > 1) {

                for (int i = 0; i < chkYList_P.size(); i++) {
                    scrnMsg.A.no(chkYList_P.get(i)).xxChkBox_P1.setErrorInfo(1, NMAM0051E, new String[] {NAME_FOR_MESSAGE_PRTY_LOC_FLG });
                    scrnMsg.addCheckItem(scrnMsg.A.no(chkYList_P.get(i)).xxChkBox_P1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SWH);
                }
                // Priority flag can not be selected with Disabled
                // flag.
            } else if (!chkYList_P.isEmpty() && !chkYList_D.isEmpty()) {

                for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
                    if (chkYList_P.contains(i) && chkYList_D.contains(i)) {
                        scrnMsg.A.no(i).xxChkBox_P1.setErrorInfo(1, NMAM0248E, new String[] {NAME_FOR_MESSAGE_DSBL_LOC_FLG, NAME_FOR_MESSAGE_PRTY_SELECTED });
                        scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_P1);
                        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_SWH);
                    }
                }
            }
        }
        scrnMsg.putErrorScreen();

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, nowDplyTab);

        // clear html attribute
        scrnMsg.clearAllGUIAttribute(SCRN_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        NMAL6820CMsg bizMsg = new NMAL6820CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {
                NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
            } else {
                NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
            }
            return;
        }

        scrnMsg.addCheckItem(scrnMsg.whMgrPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.altPsnCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCatgCd_H1);
        scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.coaBrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.wmsWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.physWhCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H1);
        scrnMsg.putErrorScreen();

        if (TAB_SWH.equals(scrnMsg.xxDplyTab.getValue())) {

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (i == scrnMsg.A.length()) {
                    break;
                }
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_D1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_P1);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).vndNm_AS);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).prntVndNm_AR);
                scrnMsg.addCheckItem(scrnMsg.A.no(i).vndNm_AR);
            }
            scrnMsg.putErrorScreen();

        } else if (TAB_SRC.equals(scrnMsg.xxDplyTab.getValue())) {

            scrnMsg.addCheckItem(scrnMsg.prntVndNm_SD);
            scrnMsg.addCheckItem(scrnMsg.vndNm_SD);
            scrnMsg.addCheckItem(scrnMsg.prntVndNm_SE);
            scrnMsg.addCheckItem(scrnMsg.vndNm_SE);
            scrnMsg.addCheckItem(scrnMsg.prntVndNm_SR);
            scrnMsg.addCheckItem(scrnMsg.vndNm_SR);
            scrnMsg.putErrorScreen();

        } else {

            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ADDR);

            scrnMsg.addCheckItem(scrnMsg.firstLineAddr_S1);
            scrnMsg.addCheckItem(scrnMsg.scdLineAddr_S1);
            scrnMsg.addCheckItem(scrnMsg.ctyAddr_S1);
            scrnMsg.addCheckItem(scrnMsg.locNum_S1);
            scrnMsg.addCheckItem(scrnMsg.stCd_S1);
            scrnMsg.addCheckItem(scrnMsg.postCd_S1);
            scrnMsg.addCheckItem(scrnMsg.cntyNm_S1);

            scrnMsg.addCheckItem(scrnMsg.rtrnToFirstLineAddr_R1);
            scrnMsg.addCheckItem(scrnMsg.rtrnToScdLineAddr_R1);
            scrnMsg.addCheckItem(scrnMsg.rtrnToCtyAddr_R1);
            scrnMsg.addCheckItem(scrnMsg.locNum_R1);
            scrnMsg.addCheckItem(scrnMsg.rtrnToStCd_R1);
            scrnMsg.addCheckItem(scrnMsg.rtrnToPostCd_R1);
            scrnMsg.addCheckItem(scrnMsg.cntyNm_R1);
            scrnMsg.putErrorScreen();
        }

        scrnMsg.clearAllGUIAttribute(SCRN_ID);
        ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H1, scrnMsg.rtlWhCd_H1);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_UPDATE);
        NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);

        if (TAB_SWH.equals(scrnMsg.xxDplyTab.getValue())) {
            NMAL6820CommonLogic.setScreenPropertiesForSwhDetail(this, scrnMsg);
        }
    }
}

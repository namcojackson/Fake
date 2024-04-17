/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_CREATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_BR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_CTRY;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_CTY_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_EFF_FROM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_FIRST_LINE_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_INVTY_ACCT;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_INVTY_OWNR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_POST;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PO_RCPT_RTE_TP_CD;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PROCR_TP_CD_OF_RTRN;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_PROCR_TP_CD_OF_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RMA_RCPT_RTE_TP_CD;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_SWH_NM_OF_EMERGENCY;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_CATG;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_CD;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM_OF_EMERGENCY;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SHIP_TO_LOC_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_ST;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SUPPLIER;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SUPPLIER_SITE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SWH_DESCRIPTION;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_SWH_NM;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_TECH_MBL_MSG_ADDR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WH_OWNR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.NAME_FOR_MESSAGE_WH_SYS_TP;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.TAB_ADDR;

import java.io.Serializable;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Init
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#2380
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 04/25/2016   CSAI            D.Fukaya        Update          QC#6406
 * 04/09/2019   Fujitsu         T.Ogura         Update          QC#28667
 *</pre>
 */
public class NMAL6820_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // dev env doesn't need security check
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_APP_ID);

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            if (null != arg) {
                Object[] params = (Object[]) arg;
                ZYPEZDItemValueSetter.setValue(scrnMsg.rtlWhCd_H1, (EZDBStringItem) params[0]);
                ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H1, (EZDBStringItem) params[0]);

                scrnMsg.addCheckItem(scrnMsg.rtlWhCd_H1);

                scrnMsg.putErrorScreen();
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_UPDATE);
            }
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_CREATE);
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = new NMAL6820CMsg();

        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

        if (MODE_UPDATE.equals(scrnMsg.xxModeCd_G1.getValue())) {

            if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {

                // column and button input protection
                NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
            } else {

                // clear html attribute
                scrnMsg.clearAllGUIAttribute(SCRN_ID);

                // column and button input protection
                NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
            }
        } else {

            // column and button input protection
            NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);
        }
        // Tab focus
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxDplyTab, TAB_ADDR);
        // cursor focus
        scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) arg0;

        scrnMsg.rtlWhCd_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_CD);
        scrnMsg.rtlWhNm_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM);
        scrnMsg.rtlWhCatgCd_H1.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_CATG);
        scrnMsg.invtyAcctCd_H1.setNameForMessage(NAME_FOR_MESSAGE_INVTY_ACCT);
        scrnMsg.invtyOwnrCd_H1.setNameForMessage(NAME_FOR_MESSAGE_INVTY_OWNR);
        scrnMsg.whOwnrCd_H1.setNameForMessage(NAME_FOR_MESSAGE_WH_OWNR);
        scrnMsg.whSysTpCd_H1.setNameForMessage(NAME_FOR_MESSAGE_WH_SYS_TP);
        scrnMsg.effFromDt_H1.setNameForMessage(NAME_FOR_MESSAGE_EFF_FROM);
        scrnMsg.coaBrCd_H1.setNameForMessage(NAME_FOR_MESSAGE_BR);
        scrnMsg.prntVndCd.setNameForMessage(NAME_FOR_MESSAGE_SUPPLIER);     // 2019/04/09 T.Ogura [QC#28667,ADD]
        scrnMsg.vndCd.setNameForMessage(NAME_FOR_MESSAGE_SUPPLIER_SITE);    // 2019/04/09 T.Ogura [QC#28667,ADD]

        scrnMsg.locNum_S1.setNameForMessage(NAME_FOR_MESSAGE_SHIP_TO_LOC_NM);
        scrnMsg.firstLineAddr_S1.setNameForMessage(NAME_FOR_MESSAGE_FIRST_LINE_ADDR);
        scrnMsg.ctyAddr_S1.setNameForMessage(NAME_FOR_MESSAGE_CTY_ADDR);
        scrnMsg.stCd_S1.setNameForMessage(NAME_FOR_MESSAGE_ST);
        scrnMsg.postCd_S1.setNameForMessage(NAME_FOR_MESSAGE_POST);
        scrnMsg.ctryCd_S1.setNameForMessage(NAME_FOR_MESSAGE_CTRY);
        scrnMsg.poRcptRteTpCd_S1.setNameForMessage(NAME_FOR_MESSAGE_PO_RCPT_RTE_TP_CD);
        scrnMsg.rmaRcptRteTpCd_S1.setNameForMessage(NAME_FOR_MESSAGE_RMA_RCPT_RTE_TP_CD);

        scrnMsg.prntVndNm_SD.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC);
        scrnMsg.vndNm_SD.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC);
        scrnMsg.prntVndNm_SE.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM_OF_EMERGENCY);
        scrnMsg.vndNm_SE.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM_OF_EMERGENCY);
        scrnMsg.prntVndNm_SR.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN);
        scrnMsg.vndNm_SR.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN);
        scrnMsg.techMblMsgAddr.setNameForMessage(NAME_FOR_MESSAGE_TECH_MBL_MSG_ADDR);

        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).rtlSwhNm_A1.setNameForMessage(NAME_FOR_MESSAGE_SWH_NM);
            scrnMsg.A.no(i).rtlSwhDescTxt_A1.setNameForMessage(NAME_FOR_MESSAGE_SWH_DESCRIPTION);
            scrnMsg.A.no(i).procrTpCd_A1.setNameForMessage(NAME_FOR_MESSAGE_PROCR_TP_CD_OF_SRC);
            scrnMsg.A.no(i).prntVndNm_AS.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM_OF_SRC);
            scrnMsg.A.no(i).vndNm_AS.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM_OF_SRC);
            scrnMsg.A.no(i).procrTpCd_A2.setNameForMessage(NAME_FOR_MESSAGE_PROCR_TP_CD_OF_RTRN);
            scrnMsg.A.no(i).prntVndNm_AR.setNameForMessage(NAME_FOR_MESSAGE_RTL_WH_NM_OF_RTRN);
            scrnMsg.A.no(i).vndNm_AR.setNameForMessage(NAME_FOR_MESSAGE_RTL_SWH_NM_OF_RTRN);
        }
    }
}

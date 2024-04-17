/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6820;

import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_CMN_CLOSE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.BIZ_APP_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_CREATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.MODE_UPDATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.SCRN_ID;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_WH;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_ALT_OWNR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_CRR;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_STATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE;
import static business.servlet.NMAL6820.constant.NMAL6820Constant.EVENT_NM_AL6820SCRN00_OPEN_WIN_BRANCH;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NMAL6820.NMAL6820CMsg;
import business.servlet.NMAL6820.common.NMAL6820CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NMAL6820 Warehouse Setup
 * Function Name : Return Action from NMAL6050(General Purpose Popup)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 11/25/2015   CITS            M.Ito           Create          N/A
 * 02/04/2016   CSAI            D.Fukaya        Update          QC#1590
 * 02/18/2016   CSAI            D.Fukaya        Update          QC#3436
 * 02/22/2016   CSAI            D.Fukaya        Update          QC#2369
 *</pre>
 */
public class NMAL6820_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (MODE_CREATE.equals(scrnMsg.xxModeCd_G1.getValue()) && EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR.equals(scrnMsg.xxPopPrm_EV.getValue()) && LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {

                NMAL6820CMsg bizMsg = new NMAL6820CMsg();
                bizMsg.setBusinessID(BIZ_APP_ID);
                bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;

            } else {

                return null;
            }
        }
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6820BMsg scrnMsg = (NMAL6820BMsg) bMsg;
        NMAL6820CMsg bizMsg = (NMAL6820CMsg) cMsg;

        if (!EVENT_NM_CMN_CLOSE.equals(getLastGuard())) {

            if (EVENT_NM_AL6820SCRN00_OPEN_WIN_WH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_APP_ID);

                if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {

                    EZDMsg.copy(bizMsg, null, scrnMsg, null);

                    // column and button input protection
                    NMAL6820CommonLogic.cntrlScrnItemDispCreateMode(this, scrnMsg, funcList);

                    // cursor focus
                    scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
                } else {

                    // to keep a wh code for search when it is already
                    // registered.
                    ZYPEZDItemValueSetter.setValue(scrnMsg.whCd_H1, scrnMsg.rtlWhCd_H1);

                    // clear html attribute
                    scrnMsg.clearAllGUIAttribute(SCRN_ID);

                    // column and button input protection
                    NMAL6820CommonLogic.cntrlScrnItemDispUpdateMode(this, scrnMsg, funcList);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_G1, MODE_UPDATE);

                    // cursor focus
                    scrnMsg.setFocusItem(scrnMsg.rtlWhNm_H1);
                }

            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_MGR.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H1, scrnMsg.fullPsnNm_G1);

                if (MODE_CREATE.equals(scrnMsg.xxModeCd_G1.getValue()) && LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.firstLineAddr_S1, bizMsg.firstLineAddr_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.scdLineAddr_S1, bizMsg.scdLineAddr_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.thirdLineAddr_S1, bizMsg.thirdLineAddr_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.frthLineAddr_S1, bizMsg.frthLineAddr_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.ctyAddr_S1, bizMsg.ctyAddr_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cntyPk_S1, bizMsg.cntyPk_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.cntyNm_S1, bizMsg.cntyNm_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.provNm_S1, bizMsg.provNm_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, bizMsg.stCd_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.postCd_S1, bizMsg.postCd_S1);
                    ZYPEZDItemValueSetter.setValue(scrnMsg.ctryCd_S1, bizMsg.ctryCd_S1);
                }

                scrnMsg.setFocusItem(scrnMsg.altPsnCd_H1);
            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_ALT_OWNR.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.fullPsnNm_H2, scrnMsg.fullPsnNm_G1);
                scrnMsg.setFocusItem(scrnMsg.telNum_H1);
            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_CRR.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.carrCd_H1, scrnMsg.carrCd_G1);
                ZYPEZDItemValueSetter.setValue(scrnMsg.carrNm_H1, scrnMsg.carrNm_G1);

                scrnMsg.setFocusItem(scrnMsg.carrCd_H1);
            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_SHIP_TO_STATE.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.stCd_S1, scrnMsg.stCd_G1);

                scrnMsg.setFocusItem(scrnMsg.provNm_S1);
            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_RETURN_TO_STATE.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.rtrnToStCd_R1, scrnMsg.stCd_G2);

                scrnMsg.setFocusItem(scrnMsg.rtrnToProvNm_R1);
            } else if (EVENT_NM_AL6820SCRN00_OPEN_WIN_BRANCH.equals(scrnMsg.xxPopPrm_EV.getValue())) {

                ZYPEZDItemValueSetter.setValue(scrnMsg.coaBrNm_H1, scrnMsg.coaBrNm_G1);
                scrnMsg.setFocusItem(scrnMsg.coaBrCd_H1);
            } else {

                scrnMsg.setFocusItem(scrnMsg.rtlWhCd_H1);
            }
        }

    }
}

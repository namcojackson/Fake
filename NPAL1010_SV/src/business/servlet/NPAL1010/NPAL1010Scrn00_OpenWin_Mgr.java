/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1010;

import static business.servlet.NPAL1010.constant.NPAL1010Constant.BUSINESS_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.DTL_CD_LB_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.DTL_CD_LB_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.DTL_NM_LB_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.DTL_NM_LB_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.FUNC_SRCH_ID;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.HDR_CD_LB_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.HDR_CD_LB_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.HDR_NM_LB_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.HDR_NM_LB_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.SCR_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.SCR_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_CD_COL_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_NM_COL_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_SORT_COL_NM_FOR_OWNER;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_CD_COL_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_NM_COL_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.TBL_SORT_COL_NM_FOR_TECH;
import static business.servlet.NPAL1010.constant.NPAL1010Constant.EVENT_NM_OPENWIN_MGR;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1010.NPAL1010CMsg;
import business.servlet.NPAL1010.common.NPAL1010CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.LOC_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Business ID : NPAL1010 Location Info Popup
 * Function Name : Open Manager Search Popup(NMAL6050)
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/04/2016   CSAI            D.Fukaya        Create          QC#7629
 *</pre>
 */
public class NPAL1010Scrn00_OpenWin_Mgr extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no processing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd)) {
            NPAL1010CMsg bizMsg = new NPAL1010CMsg();
            bizMsg.setBusinessID(BUSINESS_ID);
            bizMsg.setFunctionCode(FUNC_SRCH_ID);
            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;

        } else {
            scrnMsg.locTpCd_H1.clear();
            return null;
        }
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        if (ZYPCommonFunc.hasValue(scrnMsg.rtlWhCatgCd)) {
            NPAL1010CMsg bizMsg = (NPAL1010CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NPAL1010CommonLogic.clearParamNPAL6050(scrnMsg);

        if (LOC_TP.TECHNICIAN.equals(scrnMsg.locTpCd_H1.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm, TBL_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm, TBL_CD_COL_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm, TBL_NM_COL_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm, TBL_SORT_COL_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, SCR_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm, HDR_CD_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm, HDR_NM_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm, DTL_CD_LB_NM_FOR_TECH);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm, DTL_NM_LB_NM_FOR_TECH);

        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNm, TBL_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblCdColNm, TBL_CD_COL_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblNmColNm, TBL_NM_COL_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxTblSortColNm, TBL_SORT_COL_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrNm, SCR_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrCdLbNm, HDR_CD_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxHdrNmLbNm, HDR_NM_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlCdLbNm, DTL_CD_LB_NM_FOR_OWNER);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxDtlNmLbNm, DTL_NM_LB_NM_FOR_OWNER);
        }

        Object[] params = new Object[11];
        params[0] = scrnMsg.xxTblNm;
        params[1] = scrnMsg.xxTblCdColNm;
        params[2] = scrnMsg.xxTblNmColNm;
        params[3] = scrnMsg.xxTblSortColNm;
        params[4] = scrnMsg.xxScrNm;
        params[5] = scrnMsg.xxHdrCdLbNm;
        params[6] = scrnMsg.xxHdrNmLbNm;
        params[7] = scrnMsg.xxDtlCdLbNm;
        params[8] = scrnMsg.xxDtlNmLbNm;
        params[9] = scrnMsg.whMgrPsnCd_H1;
        params[10] = scrnMsg.fullPsnNm_H1;

        // Set Event Name.
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxPopPrm_EV, EVENT_NM_OPENWIN_MGR);

        // Subscreen transition
        setArgForSubScreen(params);
    }
}
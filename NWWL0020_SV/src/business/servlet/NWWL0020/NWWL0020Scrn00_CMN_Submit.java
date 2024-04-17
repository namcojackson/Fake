/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWWL0020;

import static business.servlet.NWWL0020.constant.NWWL0020Constant.BIZ_ID;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.NZZM0002I;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TAB_NAME_HEADER;
import static business.servlet.NWWL0020.constant.NWWL0020Constant.TAB_NAME_SQL;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWWL0020.NWWL0020CMsg;
import business.servlet.NWWL0020.common.NWWL0020CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWWL0020Scrn00_CMN_Submit
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/07/28   Fujitsu         S.Ohki          Create          N/A
 * 2016/11/23   Fujitsu         M.Ohno          Update          S21_NA#16201
 *</pre>
 */
public class NWWL0020Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrNm_H0);
        scrnMsg.addCheckItem(scrnMsg.ntfyHdrDescTxt_H0);

        if (TAB_NAME_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {

            NWWL0020CommonLogic.checkTimeItem(scrnMsg);
            // NWWL0020CommonLogic.getScrnTm(scrnMsg); // Del 2016/11/24 M.Ohno S21_NA#16201

            scrnMsg.addCheckItem(scrnMsg.ntfyBizAreaTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.ntfySubAreaTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.effFromDt_H0);
            scrnMsg.addCheckItem(scrnMsg.effThruDt_H0);
            scrnMsg.addCheckItem(scrnMsg.ntfyFreqTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.xxStartDplyTmTxt);
            scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
            scrnMsg.addCheckItem(scrnMsg.ntfyHdrActvFlg_H0);
            scrnMsg.addCheckItem(scrnMsg.ntfyIntvlAot_PD);
            scrnMsg.addCheckItem(scrnMsg.ntfyIntvlUomTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.histDaysAot_PD);

        } else if (TAB_NAME_SQL.equals(scrnMsg.xxDplyTab.getValue())) {

            // NWWL0020CommonLogic.getScrnTm(scrnMsg); // Del 2016/11/24 M.Ohno S21_NA#16201
            scrnMsg.addCheckItem(scrnMsg.xxNtfySqlTxt);

        } else {

            scrnMsg.addCheckItem(scrnMsg.ntfyActNm);
            scrnMsg.addCheckItem(scrnMsg.ntfyActDescTxt);
            scrnMsg.addCheckItem(scrnMsg.ntfyActTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.ntfyOtptTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.ntfyEmlRpyToAddr);
            scrnMsg.addCheckItem(scrnMsg.ntfyEmlToAddr);
            scrnMsg.addCheckItem(scrnMsg.ntfyEmlCcAddr);
            scrnMsg.addCheckItem(scrnMsg.ntfyEmlBccAddr);
            scrnMsg.addCheckItem(scrnMsg.rtrvToAddrFromSqlFlg);
            scrnMsg.addCheckItem(scrnMsg.ntfyAttTpCd_SL);
            scrnMsg.addCheckItem(scrnMsg.ntfyDistListNmListTxt);
            scrnMsg.addCheckItem(scrnMsg.ntfyEmlSubjTxt);
            scrnMsg.addCheckItem(scrnMsg.xxNtfyEmlBodyTxt);

            for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
                scrnMsg.addCheckItem(scrnMsg.B.no(i).ntfyActDtlColSortNum_B0);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).hdrLbNm_B0);
                scrnMsg.addCheckItem(scrnMsg.B.no(i).placeHldNm_B0);
            }
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = new NWWL0020CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("40");
        // Add Start 2016/11/24 M.Ohno S21_NA#16201
        if (TAB_NAME_HEADER.equals(scrnMsg.xxDplyTab.getValue())) {
            NWWL0020CommonLogic.getScrnTm(scrnMsg);
        }
        // Add End 2016/11/24 M.Ohno S21_NA#16201
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWWL0020BMsg scrnMsg = (NWWL0020BMsg) bMsg;
        NWWL0020CMsg bizMsg = (NWWL0020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.effFromDt_H0);
        scrnMsg.addCheckItem(scrnMsg.effThruDt_H0);
        scrnMsg.addCheckItem(scrnMsg.xxStartDplyTmTxt);
        scrnMsg.addCheckItem(scrnMsg.xxEndDplyTmTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunSunFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunMonFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunTueFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunWedFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunThuFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunFriFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunSatFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunDayListTxt_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyRunMnListTxt_PD);
        scrnMsg.addCheckItem(scrnMsg.ntfyEomFlg_PD);
        scrnMsg.addCheckItem(scrnMsg.xxNtfySqlTxt);
        scrnMsg.addCheckItem(scrnMsg.ntfyEmlRpyToAddr);

        for (int i = 0; i < scrnMsg.B.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.B.no(i).ntfyActDtlColSortNum_B0);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).hdrLbNm_B0);
            scrnMsg.addCheckItem(scrnMsg.B.no(i).placeHldNm_B0);
        }

        scrnMsg.putErrorScreen();

        NWWL0020CommonLogic.setScrnTm(scrnMsg);

        if (!ZYPCommonFunc.hasValue(scrnMsg.getMessageCode())) {
            scrnMsg.setMessageInfo(NZZM0002I);
        }
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL2020;

import static business.servlet.NLBL2020.constant.NLBL2020Constant.BUSINESS_ID;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.DISPLAY_SRCH_OPT_NM;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.FUNC_UPD;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.ZZM9000E;
import static business.servlet.NLBL2020.constant.NLBL2020Constant.ZZM9037E;

import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL2020.NLBL2020CMsg;
import business.servlet.NLBL2020.common.NLBL2020CommonLogic;
import business.servlet.NLBL2020.constant.NLBL2020Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Manage Shipping Orders
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/16   CITS            T.Tokutomi      Create          N/A
 * 2017/01/24   CITS            T.Kikuhara      Update          QC#10621
 *</pre>
 */
public class NLBL2020Scrn00_Save_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        // QC#10621 update start
        // Check Search Header Area
        scrnMsg.addCheckItem(scrnMsg.trxHdrNum_H1);
        scrnMsg.addCheckItem(scrnMsg.soNum_H1);
        scrnMsg.addCheckItem(scrnMsg.svcConfigMstrPk_H1);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_RW);
        scrnMsg.addCheckItem(scrnMsg.xxRtrnInvtyLocSrchTxt_SW);
        scrnMsg.addCheckItem(scrnMsg.shipToCustCd_H1);
        scrnMsg.addCheckItem(scrnMsg.carrCd_H1);
        scrnMsg.addCheckItem(scrnMsg.proNum_H1);
        scrnMsg.addCheckItem(scrnMsg.mdseCd_H1);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_FR);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_TO);
        scrnMsg.addCheckItem(scrnMsg.rddDt_FR);
        scrnMsg.addCheckItem(scrnMsg.rddDt_TO);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_F3);
        scrnMsg.addCheckItem(scrnMsg.xxTrxDt_T3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T1);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T2);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_F3);
        scrnMsg.addCheckItem(scrnMsg.xxDtNm_T3);
        scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);
        // QC#10621 update end

        if (!ZYPCommonFunc.hasValue(scrnMsg.srchOptPk_PS) && !ZYPCommonFunc.hasValue(scrnMsg.srchOptNm_H1)) {
            scrnMsg.srchOptNm_H1.setErrorInfo(1, ZZM9000E, new String[] {DISPLAY_SRCH_OPT_NM });
            scrnMsg.setMessageInfo(ZZM9037E);
        }

        // clear html attribute
        initScrenAttribute(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;

        NLBL2020CMsg bizMsg = new NLBL2020CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_UPD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL2020BMsg scrnMsg = (NLBL2020BMsg) bMsg;
        NLBL2020CMsg bizMsg = (NLBL2020CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // reset html attribute
        initScrenAttribute(scrnMsg);

        if (scrnMsg.srchOptNm_H1.isError()) {

            scrnMsg.addCheckItem(scrnMsg.srchOptNm_H1);
            scrnMsg.putErrorScreen();

        } else {

            scrnMsg.setFocusItem(scrnMsg.srchOptNm_H1);
        }
    }

    private void initScrenAttribute(NLBL2020BMsg scrnMsg) {
        // reset html attribute
        List<String> funcList = getUserProfileService().getFunctionCodeListForBizAppId(NLBL2020Constant.BUSINESS_ID);
        NLBL2020CommonLogic.ctrlScrnItemProtection(scrnMsg, this, funcList);
    }
}

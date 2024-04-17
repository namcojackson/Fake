/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NPAL1080;

import static business.servlet.NPAL1080.constant.NPAL1080Constant.BUSINESS_APPLICATION_ID;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.NPAM1329E;
import static business.servlet.NPAL1080.constant.NPAL1080Constant.TECH_SWH_DEFALUT;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NPAL1080.NPAL1080CMsg;
import business.servlet.NPAL1080.common.NPAL1080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/12/2016   CITS            Hisashi         Create          N/A
 * 10/25/2017   CITS            S.Katsuma       Update          QC#21896
 *</pre>
 */
public class NPAL1080Scrn00_GetTechLocInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlWhCd_H1)) {
            scrnMsg.destRtlWhCd_H1.setErrorInfo(1, NPAM1329E, new String[]{"Tech Wh", " Please select."});
            scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_H1);
        }
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;

        NPAL1080CMsg bizMsg = new NPAL1080CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1080BMsg scrnMsg = (NPAL1080BMsg) bMsg;
        NPAL1080CMsg bizMsg = (NPAL1080CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.destRtlWhCd_H1);
        scrnMsg.putErrorScreen();

        // QC#7386
        if (!ZYPCommonFunc.hasValue(scrnMsg.destRtlSwhCd_H1)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.destRtlSwhCd_H1, TECH_SWH_DEFALUT);
        }

        // START 2017/10/25 S.Katsuma QC#21896 ADD
        NPAL1080CommonLogic.controlForTechSwhR(this, scrnMsg);
        // END 2017/10/25 S.Katsuma QC#21896 ADD

        scrnMsg.setFocusItem(scrnMsg.destRtlWhCd_H1);

    }
}

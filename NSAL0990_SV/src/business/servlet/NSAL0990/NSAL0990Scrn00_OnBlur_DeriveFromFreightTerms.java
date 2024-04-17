/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/11   Hitachi         A.Kohinata      Create          QC#5354
 * 2017/11/29   Hitachi         K.Kojima        Update          QC#20497
 * 2018/11/14   Fujitsu         M.Yamada        Update          QC#29121
 *</pre>
 */
public class NSAL0990Scrn00_OnBlur_DeriveFromFreightTerms extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;

        // START 2017/11/29 K.Kojima [QC#20497,DEL]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
        //     return null;
        // }
        // END 2017/11/29 K.Kojima [QC#20497,DEL]

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        setResult(ZYPConstant.FLG_OFF_N);

        // START 2017/11/29 K.Kojima [QC#20497,DEL]
        // if (!ZYPCommonFunc.hasValue(scrnMsg.frtCondDescTxt)) {
        //     scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
        //     return;
        // }
        // END 2017/11/29 K.Kojima [QC#20497,DEL]

        NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
        scrnMsg.putErrorScreen();

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxRqstFlg.getValue())) {
            setResult(ZYPConstant.FLG_ON_Y);
            scrnMsg.xxScrEventNm.setValue(ctx.getEventName());
            // QC#29121
//            Object[] params = NSAL0990CommonLogic.getParamNMAL6050ForFrtTerm(scrnMsg);
            Object[] params = NSAL0990CommonLogic.getParamNWAL1130ForFrtTerm(scrnMsg);
            setArgForSubScreen(params);
            return;
        }
        NSAL0990CommonLogic.setProtectByFrtCond(scrnMsg);
        scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);
    }
}

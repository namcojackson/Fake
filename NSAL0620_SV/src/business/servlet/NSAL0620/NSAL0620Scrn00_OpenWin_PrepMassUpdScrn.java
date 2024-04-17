/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0620;

import static business.servlet.NSAL0620.constant.NSAL0620Constant.BIZ_ID;
import static business.servlet.NSAL0620.constant.NSAL0620Constant.MASS_SCREEN_PRM_LENGTH;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0620.NSAL0620CMsg;
import business.servlet.NSAL0620.constant.NSAL0620Constant.FUNC;
import business.servlet.NSAL0620.constant.NSAL0620Constant.MASS_UPDATE_LIST;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Contract Search
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/23   Hitachi         T.Tsuchida      Create          N/A
 * 2016/02/16   Hitachi         O.Okuma         Update          QC#3029
 * 2016/05/18   Hitachi         A.Kohinata      Update          QC4212
 * 2017/02/07   Hitachi         K.Kojima        Update          QC#17303
 *</pre>
 */
public class NSAL0620Scrn00_OpenWin_PrepMassUpdScrn extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.xxGenlFldAreaTxt_B);
        scrnMsg.putErrorScreen();
   }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;

        NSAL0620CMsg bizMsg = new NSAL0620CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC.UPDATE.getFunc());
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0620BMsg scrnMsg = (NSAL0620BMsg) bMsg;
        NSAL0620CMsg bizMsg  = (NSAL0620CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        scrnMsg.addCheckItem(scrnMsg.xxGenlFldAreaTxt_B);
        // mod start 2016/03/14 CSA Defect#4428
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).xxChkBox_A);
        }
        // mod end 2016/03/14 CSA Defect#4428
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }

        if (MASS_UPDATE_LIST.NSAL0710.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0710.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0460.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0460.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0400.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0400.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0690.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0690.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0670.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0670.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0630.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0630.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0700.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0700.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0640.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0640.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0730.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0730.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0390.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0390.getScrnId());
            // START 2016/05/19 [QC4212, MOD]
//            setArgForSubScreen(setParamForOnlyContr(scrnMsg));
            setArgForSubScreen(setParamForContr(scrnMsg));
            // END 2016/05/19 [QC4212, MOD]
        } else if (MASS_UPDATE_LIST.NSAL0650.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0650.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0660.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0660.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0750.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0750.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0720.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0720.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0740.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0740.getScrnId());
            setArgForSubScreen(setParamForContr(scrnMsg));
        } else if (MASS_UPDATE_LIST.NSAL0380.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.NSAL0380.getScrnId());
            // START 2016/02/15 O.Okuma [QC3029, MOD]
//            setArgForSubScreen(setParamForOnlyContr(scrnMsg));
            setArgForSubScreen(setParamForContr(scrnMsg));
            // END 2016/02/15 O.Okuma [QC3029, MOD]
            // START 2017/02/07 K.Kojima [QC#17303,ADD]
        } else if (MASS_UPDATE_LIST.ZYPL0210.getScrnId().equals(scrnMsg.xxGenlFldAreaTxt_B.getValue())) {
            this.setResult(MASS_UPDATE_LIST.ZYPL0210.getScrnId());
            // END 2017/02/07 K.Kojima [QC#17303,ADD]
        } else {
            this.setResult("NO");
        }
    }

    private Object[] setParamForContr(NSAL0620BMsg scrnMsg) {
        Object[] params = new Object[MASS_SCREEN_PRM_LENGTH];
        params[0] = scrnMsg.P;

        return params;
    }

    // START 2016/05/19 [QC4212, DEL]
//    private Object[] setParamForOnlyContr(NSAL0620BMsg scrnMsg) {
//        Object[] params = new Object[MASS_SCREEN_PRM_LENGTH];
//        params[0] = scrnMsg.P.no(0).dsContrPk_P1;
//
//        return params;
//    }
    // END 2016/05/19 [QC4212, DEL]
}


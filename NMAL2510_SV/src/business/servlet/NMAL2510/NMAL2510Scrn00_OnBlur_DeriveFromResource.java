/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2510;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL2510.NMAL2510CMsg;
import business.servlet.NMAL2510.common.NMAL2510CommonLogic;
import business.servlet.NMAL2510.constant.NMAL2510Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.PSN_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 * 2016/03/04/  Fujitsu         M.suzuki        Update          S21_NA#4539 
 * 2016/05/24   SRAA            Y.Chen          Update          QC#7962
 * 2016/10/11   Hitachi         Y.Takeno        Update          CSA-QC#13431
 * 2018/09/14   Fujitsu         S.Kosaka        Update          QC#27723
 *</pre>
 */
public class NMAL2510Scrn00_OnBlur_DeriveFromResource extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.psnNum_H1.getValue())) {
            if (PSN_TP.EMPLOYEE.equals(scrnMsg.psnTpCd_P1.getValue())) {
                scrnMsg.psnNum_H1.setErrorInfo(1, NMAL2510Constant.ZZZM9007E, new String[] {NMAL2510Constant.NAME_FOR_MESSAGE_PSN_NUM });
                scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
            }
        }

        NMAL2510CommonLogic.clearMandantoryCheck(scrnMsg);
        // 2016/03/04 S21_NA#4539 Mod Start ------------
        //scrnMsg.addCheckItem(scrnMsg.psnNum_H1);
        NMAL2510CommonLogic.addCheckHeaderItems(scrnMsg);
        // 2016/03/04 S21_NA#4539 Mod End --------------

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;

        NMAL2510CMsg bizMsg = new NMAL2510CMsg();
        bizMsg.setBusinessID(NMAL2510Constant.BIZ_ID);
        bizMsg.setFunctionCode(NMAL2510Constant.FUNCTION_CD);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2510BMsg scrnMsg = (NMAL2510BMsg) bMsg;
        NMAL2510CMsg bizMsg = (NMAL2510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NMAL2510CommonLogic.setProtectEffectiveFrom(scrnMsg);

        NMAL2510CommonLogic.setProtectKeyValue(scrnMsg);
        
        // QC#13431
        NMAL2510CommonLogic.controlAttachmentButton(this, scrnMsg);

        // QC#7962
        scrnMsg.setFocusItem(scrnMsg.psnFirstNm_H1);

        // START 2018/09/14 S.Kosaka [QC#27723,ADD]
        NMAL2510CommonLogic.setAllBGWithClear(scrnMsg);
        NMAL2510CommonLogic.setAddDelButton(this, scrnMsg);
        // END 2018/09/14 S.Kosaka [QC#27723,ADD]
    }
}

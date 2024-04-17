/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.BUSINESS_ID;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.MODE_AFTER_SEARCH;
import static business.servlet.NSAL0830.constant.NSAL0830Constant.SCRN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0830.NSAL0830CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/21   Hitachi         Y.Takeno        Create          N/A
 * 2016/03/21   Hitachi         K.Yamada        Update          CSA QC#5648
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 * 2017/01/18   Hitachi         K.Ochiai        Update          QC#16331
 *</pre>
 */
public class NSAL0830Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        // START 2017/01/18 K.Ochiai [QC#16331, ADD]
        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBMsgArray) {
                EZDMsg.copy(((EZDBMsgArray) prm[0]), null, scrnMsg.T, null);
            }
        }

        scrnMsg.P.setValidCount(scrnMsg.T.getValidCount());
        for (int i = 0; i < scrnMsg.T.getValidCount(); i++) {
            setValue(scrnMsg.P.no(i).dsContrIntfcPk, scrnMsg.T.no(i).dsContrIntfcPk_T);
        }

        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrIntfcBatNum_S, scrnMsg.T.no(0).dsContrIntfcBatNum_T);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrSrcRefNum_S, scrnMsg.T.no(0).dsContrSrcRefNum_T);
        // END 2017/01/18 K.Ochiai [QC#16331, ADD]
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_AFTER_SEARCH);

        NSAL0830CMsg bizMsg = new NSAL0830CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        NSAL0830CMsg bizMsg = (NSAL0830CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // START 2016/09/01 Y.Zhang [QC#11846, MOD]
        // because focus set add,process execute sort change
        scrnMsg.putErrorScreen();
        initialControlScreen(this, scrnMsg);
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]
        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // START 2016/05/20 [QC#4061, ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2016/05/20 [QC#4061, ADD]
    }
}

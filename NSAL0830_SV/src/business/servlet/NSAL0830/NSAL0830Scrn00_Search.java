/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0830;

import static business.servlet.NSAL0830.constant.NSAL0830Constant.*;
import static business.servlet.NSAL0830.common.NSAL0830CommonLogic.initialControlScreen;

import parts.common.EZDBMsg;
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
 * 2016/03/23   Hitachi         Y.Takeno        Update          QC#5450
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0830Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;

        scrnMsg.addCheckItem(scrnMsg.contrIntfcSrcTpCd_SS);
        scrnMsg.addCheckItem(scrnMsg.dsContrSrcRefNum_S);
        scrnMsg.addCheckItem(scrnMsg.dsContrNum_S);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0830BMsg scrnMsg = (NSAL0830BMsg) bMsg;
        scrnMsg.contrIntfcSrcTpCd_S2.clear();
        scrnMsg.dsContrIntfcBatNum_S2.clear();
        scrnMsg.dsContrSrcRefNum_S2.clear();
        scrnMsg.dsContrNum_S2.clear();
        scrnMsg.xxErrFlg_S2.clear();
        ZYPEZDItemValueSetter.setValue(scrnMsg.contrIntfcSrcTpCd_S2, scrnMsg.contrIntfcSrcTpCd_SS);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrIntfcBatNum_S2, scrnMsg.dsContrIntfcBatNum_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrSrcRefNum_S2, scrnMsg.dsContrSrcRefNum_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_S2, scrnMsg.dsContrNum_S);
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxErrFlg_S2, scrnMsg.xxErrFlg_S);

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

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("NSAL0830Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END 2016/09/01 Y.Zhang [QC#11846, MOD]

        if ("E".equals(bizMsg.getMessageKind())) {
            throw new EZDFieldErrorException();
        }
        // START 2016/05/20 [QC#4061, ADD]
        S21SortColumnIMGController.clearIMG(SCRN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2016/05/20 [QC#4061, ADD]
    }
}

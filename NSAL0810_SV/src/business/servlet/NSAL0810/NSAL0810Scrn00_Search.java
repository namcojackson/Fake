/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0810;

import static business.servlet.NSAL0810.common.NSAL0810CommonLogic.initialControlScreen;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.BUSINESS_ID;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.FUNCTION_SEARCH;
import static business.servlet.NSAL0810.constant.NSAL0810Constant.SCREEN_ID;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0810.NSAL0810CMsg;

import static business.servlet.NSAL0810.constant.NSAL0810Constant.*;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Interface Maintenance
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/18   Hitachi         Y.Tsuchimoto    Create          N/A
 * 2016/05/20   Hitachi         Y.Tsuchimoto    Update          QC#4061
 * 2016/09/01   Hitachi         Y.Zhang         Update          QC#11846
 *</pre>
 */
public class NSAL0810Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.contrIntfcSrcTpCd_SS);
        scrnMsg.addCheckItem(scrnMsg.dsContrIntfcBatNum_S);
        scrnMsg.addCheckItem(scrnMsg.dsContrSrcRefNum_S);
        scrnMsg.addCheckItem(scrnMsg.dsContrIntfcDt_SF);
        scrnMsg.addCheckItem(scrnMsg.dsContrIntfcDt_ST);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        scrnMsg.contrIntfcSrcTpCd_S2.clear();
        scrnMsg.dsContrIntfcBatNum_S2.clear();
        scrnMsg.dsContrSrcRefNum_S2.clear();
        scrnMsg.dsContrIntfcDt_F2.clear();
        scrnMsg.dsContrIntfcDt_T2.clear();
        scrnMsg.xxErrFlg_S2.clear();
        setValue(scrnMsg.contrIntfcSrcTpCd_S2, scrnMsg.contrIntfcSrcTpCd_SS);
        setValue(scrnMsg.dsContrIntfcBatNum_S2, scrnMsg.dsContrIntfcBatNum_S);
        setValue(scrnMsg.dsContrSrcRefNum_S2, scrnMsg.dsContrSrcRefNum_S);
        setValue(scrnMsg.dsContrIntfcDt_F2, scrnMsg.dsContrIntfcDt_SF);
        setValue(scrnMsg.dsContrIntfcDt_T2, scrnMsg.dsContrIntfcDt_ST);
        setValue(scrnMsg.xxErrFlg_S2, scrnMsg.xxErrFlg_S);

        ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, MODE_AFTER_SEARCH);
        NSAL0810CMsg bizMsg = new NSAL0810CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0810BMsg scrnMsg = (NSAL0810BMsg) bMsg;
        NSAL0810CMsg bizMsg = (NSAL0810CMsg) cMsg;

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
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // END   2016/05/20 [QC#4061, ADD]
    }
}

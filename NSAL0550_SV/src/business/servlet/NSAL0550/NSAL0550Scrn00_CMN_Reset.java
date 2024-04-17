/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0550;

import static business.servlet.NSAL0550.constant.NSAL0550Constant.*;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0550.NSAL0550CMsg;
import business.servlet.NSAL0550.common.NSAL0550CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/01/18   Hitachi         K.Ochiai        Create          QC#16331
 * 2018/06/11   Fujitsu         M.Ohno          Update          QC#22381
 * 2018/09/27   Hitachi         T.Tomita        Update          QC#28530
 *</pre>
 */
public class NSAL0550Scrn00_CMN_Reset extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        String dsContrNum = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] prm = (Object[]) ser;
            if (prm != null) {
                if (prm.length > 0 && prm[0] != null && prm[0] instanceof EZDBStringItem) {
                    dsContrNum = ((EZDBStringItem) prm[0]).getValue();
                }
            }
        }

        scrnMsg.clear();
        if (dsContrNum != null) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum_H1, dsContrNum);
        }

        NSAL0550CMsg bizMsg = new NSAL0550CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0550BMsg scrnMsg = (NSAL0550BMsg) bMsg;
        NSAL0550CMsg bizMsg = (NSAL0550CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // Add Start 2018/09/27 QC#28530
        S21SortColumnIMGController.clearIMG(SCREEN_ID, scrnMsg, scrnMsg.A.no(0).getBaseContents());
        // Add End 2018/09/27 QC#28530

        // START 2018/06/11 M.Ohno [QC#22381, ADD]
        NSAL0550CommonLogic.alternateTableRowColor(scrnMsg);
        // END 2018/06/11 M.Ohno [QC#22381, ADD]
    }
}

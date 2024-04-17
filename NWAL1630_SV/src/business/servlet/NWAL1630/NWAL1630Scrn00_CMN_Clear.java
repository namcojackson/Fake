/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1630;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NWAL1630.common.NWAL1630CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1630Scrn00_CMN_Clear
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/05   Fujitsu         C.Yokoi         Create          N/A
 * 2017/09/22   Fujitsu         T.Ogura         Update          S21_NA#18859(Sol#154)
 * 2019/01/22   Fujitsu         K.Ishizuka      Update          S21_NA#29446
 *</pre>
 */
public class NWAL1630Scrn00_CMN_Clear extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1630BMsg scrnMsg = (NWAL1630BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.csmpContrNum, scrnMsg.csmpContrNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.csmpPrcListCd, scrnMsg.csmpPrcListCd_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.dlrRefNum, scrnMsg.dlrRefNum_BK);
        // ZYPEZDItemValueSetter.setValue(scrnMsg.rntlTrmnDt, scrnMsg.rntlTrmnDt_BK); // 2019/01/22 S21_NA#29446 Del
        // 2017/09/22 QC#18859 Add Start
        ZYPEZDItemValueSetter.setValue(scrnMsg.dsContrNum, scrnMsg.dsContrNum_BK);
        ZYPEZDItemValueSetter.setValue(scrnMsg.serNum, scrnMsg.serNum_BK);
        // 2017/09/22 QC#18859 Add End

        NWAL1630CommonLogic.initCmnBtnProp(this, scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.csmpContrNum);
    }
}

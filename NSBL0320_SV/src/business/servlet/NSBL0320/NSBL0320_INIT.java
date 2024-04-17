/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0320;

import static business.servlet.NSBL0320.constant.NSBL0320Constant.*;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0320.NSBL0320CMsg;
import business.servlet.NSBL0320.common.NSBL0320CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;


/**
 *<pre>
 * Select SR Summary Criteria
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/04   Hitachi         Y.Osawa         Create          N/A
 * 2016/03/22   Hitachi         K.Yamada        Update          CSA QC#5765
 *</pre>
 */
public class NSBL0320_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;

        Object[] params = (Object[]) getArgForSubScreen();
        if (params != null && params.length > 0) {
            if (params.length > CON_NUM_0 && params[CON_NUM_0] != null && params[CON_NUM_0] instanceof EZDBStringItem) {
                EZDBStringItem param00 = (EZDBStringItem) params[CON_NUM_0];
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgCd, param00);
            }

            if (params.length > CON_NUM_1 && params[CON_NUM_1] != null && params[CON_NUM_1] instanceof EZDBBigDecimalItem) {
                EZDBBigDecimalItem param01 = (EZDBBigDecimalItem) params[CON_NUM_1];
                ZYPEZDItemValueSetter.setValue(scrnMsg.orgLayerNum, param01);
            }

            if (params.length > CON_NUM_2 && params[CON_NUM_2] != null && params[CON_NUM_2] instanceof EZDBStringItem) {
                EZDBStringItem param02 = (EZDBStringItem) params[CON_NUM_2];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcMgrTpCd, param02);
            }

            if (params.length > CON_NUM_3 && params[CON_NUM_3] != null && params[CON_NUM_3] instanceof EZDBStringItem) {
                EZDBStringItem param03 = (EZDBStringItem) params[CON_NUM_3];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcRqstDownTpCd, param03);
            }

            if (params.length > CON_NUM_4 && params[CON_NUM_4] != null && params[CON_NUM_4] instanceof EZDBStringItem) {
                EZDBStringItem param04 = (EZDBStringItem) params[CON_NUM_4];
                ZYPEZDItemValueSetter.setValue(scrnMsg.svcMgrPsnCd, param04);
            }
        }


        NSBL0320CMsg bizMsg = new NSBL0320CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;
        NSBL0320CMsg bizMsg  = (NSBL0320CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0320CommonLogic.initialControlScreen(this, scrnMsg);
        // add start 2016/03/22 CSA Defect#5765
        NSBL0320CommonLogic.selectAll(scrnMsg);
        // add end 2016/03/22 CSA Defect#5765
    }

    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0320BMsg scrnMsg = (NSBL0320BMsg) bMsg;
        scrnMsg.svcRqstCritTpCd.setNameForMessage("Pull Down");
        for (int i = 0; i < scrnMsg.A.length(); i++) {
            scrnMsg.A.no(i).xxChkBox.setNameForMessage("ChkBox");
            scrnMsg.A.no(i).xxDtlCd.setNameForMessage("Dtl Code");
            scrnMsg.A.no(i).xxGenlFldAreaTxt.setNameForMessage("GenlFldAreaTxt");
        }
    }
}

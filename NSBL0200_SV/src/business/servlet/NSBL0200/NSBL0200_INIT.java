/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0200;

import static business.servlet.NSBL0200.constant.NSBL0200Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSBL0200.NSBL0200CMsg;
import business.servlet.NSBL0200.common.NSBL0200CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 *
 * Technician Recommend Popup
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/07/03   Hitachi         Y.Igarashi         Create          N/A
 * 2013/08/30   WDS Team        K.Aratani          Update          QC1457
 * 2015/11/25   Hitachi         T.Harada           Update          CSA
 *</pre>
 */
public class NSBL0200_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            setValue(scrnMsg.mdlNm_SC, (EZDBStringItem) params[ARGS_MODEL_NAME]);
            setValue(scrnMsg.mdseCd_SC, (EZDBStringItem) params[ARGS_MDSE_CODE]);

            //START 2015/11/25 [CSA,CHANGE]
            //setValue(scrnMsg.orgLayerNum_SC, (EZDBBigDecimalItem) params[ARGS_ORG_LAYER_NUM]);
            //setValue(scrnMsg.orgCd_SC, (EZDBStringItem) params[ARGS_ORG_CD]);
            setValue(scrnMsg.svcMachMstrPk_SC, (EZDBBigDecimalItem) params[ARGS_SVC_MACH_MSTR_PK]);
            setValue(scrnMsg.serNum_SC, (EZDBStringItem) params[ARGS_SER_NUM]);
            //END 2015/11/25 [CSA,CHANGE]

            setValue(scrnMsg.techCd_SC, (EZDBStringItem) params[ARGS_TECH_CODE]);
        }
        NSBL0200CMsg bizMsg = new NSBL0200CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        NSBL0200CMsg bizMsg  = (NSBL0200CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        initControl();

        NSBL0200CommonLogic.setProtected(scrnMsg);

        //START 2015/11/25 [CSA,CHANGE]
        //QC1457
        //scrnMsg.setFocusItem(scrnMsg.orgLayerNum_TC);
        scrnMsg.setFocusItem(scrnMsg.fldSvcBrCd_SC);
        //END 2015/11/25 [CSA,CHANGE]
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSBL0200BMsg scrnMsg = (NSBL0200BMsg) bMsg;
        //QC1457
        //START 2015/11/25 [CSA,CHANGE]
        //scrnMsg.orgLayerNum_TC.setNameForMessage("Layer");
        //scrnMsg.orgCd_TC.setNameForMessage("Org Cd");
        //scrnMsg.orgLayerNum_SC.setNameForMessage("Layer");
        //scrnMsg.orgCd_SC.setNameForMessage("Org Cd");
        //scrnMsg.techBakDt_SC.setNameForMessage("Date");
        //scrnMsg.xxHrsMn_SF.setNameForMessage("Time(From)");
        //scrnMsg.xxHrsMn_ST.setNameForMessage("Time(To)");
        scrnMsg.fldSvcBrCd_SC.setNameForMessage("Branch");
        //END 2015/11/25 [CSA,CHANGE]
        scrnMsg.techCd_SC.setNameForMessage("Tech Code");
        scrnMsg.techNm_SC.setNameForMessage("Tech Name");
    }

    private void initControl() {
        // Control
        setButtonProperties(BUTTON_NAME_CLEAR, BUTTON_GUARD_CLEAR, BUTTON_LABEL_CLEAR, 1, null);
        setButtonProperties(BUTTON_NAME_RETURN, BUTTON_GUARD_CLOSE, BUTTON_LABEL_CLOSE, 1, null);
    }
}

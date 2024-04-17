/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSBL0330;

import static business.servlet.NSBL0330.constant.NSBL0330Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSBL0330.NSBL0330CMsg;
import business.servlet.NSBL0330.common.NSBL0330CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Service Request By Summary Criteria
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/02   Hitachi         T.Tsuchida      Create          N/A
 *</pre>
 */
public class NSBL0330_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_APPLICATION_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSBL0330BMsg scrnMsg = (NSBL0330BMsg) bMsg;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            if (params != null) {
                int i = 0;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBStringItem) {
                    setValue(scrnMsg.orgCd_I, (EZDBStringItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBBigDecimalItem) {
                    setValue(scrnMsg.orgLayerNum_I, (EZDBBigDecimalItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBStringItem) {
                    setValue(scrnMsg.svcMgrTpCd_I, (EZDBStringItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBStringItem) {
                    setValue(scrnMsg.svcRqstDownTpCd_I, (EZDBStringItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBStringItem) {
                    setValue(scrnMsg.svcMgrPsnCd_I, (EZDBStringItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBStringItem) {
                    setValue(scrnMsg.svcRqstCritTpCd_I, (EZDBStringItem) params[i]);
                }
                i++;
                if (params.length > i && params[i] != null && params[i] instanceof EZDBMsgArray) {
                    EZDBMsg.copy((EZDBMsgArray) params[i], null, scrnMsg.I, null);
                }
            }
        }

        NSBL0330CMsg bizMsg = new NSBL0330CMsg();
        bizMsg.setBusinessID(BUSINESS_APPLICATION_ID);
        bizMsg.setFunctionCode(EZD_FUNC_CD_INQ);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSBL0330BMsg scrnMsg = (NSBL0330BMsg) bMsg;
        NSBL0330CMsg bizMsg = (NSBL0330CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NSBL0330CommonLogic.initialize(this);
        NSBL0330CommonLogic.screenItemControl(this, scrnMsg);
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        // nothing
    }
}

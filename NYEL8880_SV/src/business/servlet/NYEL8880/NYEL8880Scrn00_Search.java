/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NYEL8880;

import static com.canon.cusa.s21.common.NYX.NYXC880001.constant.NYXC880001constant.NYEM0005E;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NYEL8880.NYEL8880CMsg;
import business.servlet.NYEL8880.common.NYEL8880CommonLogic;
//import business.servlet.NYEL8880.constant.NYEL8880Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Fujitsu         Create          N/A
 *</pre>
 */
public class NYEL8880Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // TODO [Template] security violation check. if popup then
        // delete blow
        // checkBusinessAppGranted(getContextUserInfo().getUserId(),
        // BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        Serializable arg = super.getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;

            if (params.length >= 2) {
                if (params[0] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.wfBizAppId, ((EZDBStringItem) params[0]).getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.wfBizAppId, (String) params[0]);
                }

                if (params[1] instanceof EZDBStringItem) {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgCd_F, ((EZDBStringItem) params[1]).getValue());
                } else {
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxWfAsgCd_F, (String) params[1]);
                }

            }
        }

        if (!ZYPCommonFunc.hasValue(scrnMsg.wfBizAppId)) {
                scrnMsg.setMessageInfo(NYEM0005E, new String[] {"WF_BIZ_APP_ID"});
        }

        if (scrnMsg.getMessageCode().endsWith("E")) {
            throw new EZDFieldErrorException();
        }

//        if (ZYPCommonFunc.hasValue(scrnMsg.xxWfAsgCd_F)) {
//            ZYPEZDItemValueSetter.setValue(scrnMsg.usrNm, scrnMsg.xxWfAsgCd_F.getValue());
//        }

        NYEL8880CMsg bizMsg = new NYEL8880CMsg();
        bizMsg.setBusinessID("NYEL8880");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
    	NYEL8880BMsg scrnMsg = (NYEL8880BMsg) bMsg;
    	NYEL8880CMsg bizMsg = (NYEL8880CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        NYEL8880CommonLogic.initRowCtrlProp(scrnMsg);
        NYEL8880CommonLogic.initCmnBtnProp(this);

        NYEL8880CommonLogic.setRowsBGWithClear(scrnMsg, scrnMsg.A, "A");

        if (scrnMsg.getMessageCode().endsWith("E")) {
            return;
        }

    }
}

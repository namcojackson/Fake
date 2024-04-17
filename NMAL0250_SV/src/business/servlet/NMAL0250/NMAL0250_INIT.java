/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0250;

import static business.servlet.NMAL0250.constant.NMAL0250Constant.BUSINESS_ID;
import static business.servlet.NMAL0250.constant.NMAL0250Constant.FUNCTION_CD_SEARCH;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBMsgArray;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDMsgArray;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL0250.NMAL0250CMsg;
import business.servlet.NMAL0250.common.NMAL0250CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2018/02/28   CITS            K.Kameoka       Create          #22324
 *</pre>
 */
public class NMAL0250_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg ezdBMsg) {

        // security violation check.
        checkBusinessAppGranted(getContextUserInfo().getUserId(), BUSINESS_ID);

        NMAL0250BMsg bMsg = (NMAL0250BMsg) ezdBMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            if (params.length > 0 && params[0] instanceof EZDBStringItem) {
                ZYPEZDItemValueSetter.setValue(bMsg.mdseCd, (EZDBStringItem) params[0]);
            }
            if (params.length > 1 && params[1] instanceof EZDBMsgArray) {
                // KIT Comments Array
//                    EZDBMsg.copy((EZDBMsgArray)params[1], "A1", toMsg, "B1");
//                ZYPEZDItemValueSetter.setValue(bMsg.mdseCd, (EZDBStringItem) params[1]);
            }
            if (params.length > 2 ) {
                if(params[2] instanceof EZDBStringItem){
                    ZYPEZDItemValueSetter.setValue(bMsg.xxReadOnlyFlg, (EZDBStringItem) params[2]);
                } else if(params[2] instanceof String){
                    ZYPEZDItemValueSetter.setValue(bMsg.xxReadOnlyFlg, (String) params[2]);
                }
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0250BMsg scrnMsg = (NMAL0250BMsg) bMsg;
        NMAL0250CMsg bizMsg = new NMAL0250CMsg();

        // get param
        Serializable arg = getArgForSubScreen();

        // Set Msg
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, (EZDBStringItem) params[0]);
            NMAL0250CommonLogic.setInitMsg(scrnMsg, (EZDMsgArray) params[1]);
            if (!(ZYPCommonFunc.hasValue(scrnMsg.R.no(0).prntMdseCd_R) && scrnMsg.R.no(0).prntMdseCd_R.getValue().equals(scrnMsg.mdseCd.getValue()))) {
                scrnMsg.clear();
                ZYPTableUtil.clear(scrnMsg.R);
                ZYPEZDItemValueSetter.setValue(scrnMsg.mdseCd, (EZDBStringItem) params[0]);
            }
            if (params.length > 2 ) {
                if(params[2] instanceof EZDBStringItem){
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxReadOnlyFlg, (EZDBStringItem) params[2]);
                } else if(params[2] instanceof String){
                    ZYPEZDItemValueSetter.setValue(scrnMsg.xxReadOnlyFlg, (String) params[2]);
                }
            }
        }

        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;

    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0250BMsg scrnMsg = (NMAL0250BMsg) bMsg;
        NMAL0250CMsg bizMsg = (NMAL0250CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        NMAL0250CommonLogic.controlItemsOnInit(this, scrnMsg);
    }

    protected void setNameForMessage(EZDBMsg bMsg) {

        NMAL0250BMsg scrnMsg = (NMAL0250BMsg) bMsg;

        scrnMsg.cmpsnMsgTxt_A0.setNameForMessage("BOM Text Message 1");
        scrnMsg.cmpsnMsgTxt_A1.setNameForMessage("BOM Text Message 2");
        scrnMsg.cmpsnMsgTxt_A2.setNameForMessage("BOM Text Message 3");
        scrnMsg.cmpsnMsgTxt_A3.setNameForMessage("BOM Text Message 4");
        scrnMsg.cmpsnMsgTxt_A4.setNameForMessage("BOM Text Message 5");
        scrnMsg.cmpsnMsgTxt_A5.setNameForMessage("BOM Text Message 6");
        scrnMsg.cmpsnMsgTxt_A6.setNameForMessage("BOM Text Message 7");
        scrnMsg.cmpsnMsgTxt_A7.setNameForMessage("BOM Text Message 8");
        scrnMsg.cmpsnMsgTxt_A8.setNameForMessage("BOM Text Message 9");
        scrnMsg.cmpsnMsgTxt_A9.setNameForMessage("BOM Text Message 10");
        scrnMsg.cmpsnMsgTxt_AA.setNameForMessage("BOM Text Message 11");
        scrnMsg.cmpsnMsgTxt_AB.setNameForMessage("BOM Text Message 12");
        scrnMsg.cmpsnMsgTxt_AC.setNameForMessage("BOM Text Message 13");
        scrnMsg.cmpsnMsgTxt_AD.setNameForMessage("BOM Text Message 14");
        scrnMsg.cmpsnMsgTxt_AE.setNameForMessage("BOM Text Message 15");
    }
}

/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1620;

import static business.servlet.NWAL1620.constant.NWAL1620Constant.BIZ_ID;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.HEADER_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWAM0693E;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWAM0943E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1620.NWAL1620CMsg;
import business.blap.NWAL1620.constant.NWAL1620Constant;
import business.servlet.NWAL1620.common.NWAL1620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1620Scrn00_CMN_OK
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2019/09/20   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620Scrn00_CMN_OK extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;
        NWAL1620CommonLogic.addCheckInput(scrnMsg);

        if (!HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            if (!ZYPCommonFunc.hasValue(scrnMsg.xxQty10Num)) {
                scrnMsg.setMessageInfo(NWAM0693E);
                throw new EZDFieldErrorException();
            }
        }

        // Add Start 2017/09/20 S21_NA#18859
        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox.getValue()) && ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxChkBox_R.getValue()) ) {
            scrnMsg.xxChkBox.setErrorInfo(1, NWAM0943E, new String[]{"Copy Only Header", "Create Return Order"});
            scrnMsg.xxChkBox_R.setErrorInfo(1, NWAM0943E, new String[]{"Copy Only Header", "Create Return Order"});
            scrnMsg.addCheckItem(scrnMsg.xxChkBox);
            scrnMsg.addCheckItem(scrnMsg.xxChkBox_R);
            scrnMsg.putErrorScreen();
        }
        // Add End 2017/09/20 S21_NA#18859

        //Add Start NA QC#2177
        NWAL1620CommonLogic.parseLineNum(scrnMsg);
        //Add End   NA QC#2177
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;

        NWAL1620CMsg bizMsg = new NWAL1620CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;
        NWAL1620CMsg bizMsg = (NWAL1620CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        scrnMsg.addCheckItem(scrnMsg.xxQty10Num);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            throw new EZDFieldErrorException();
        }

        Object[] arg = (Object[]) getArgForSubScreen();
        if (arg instanceof Object[]) {
            Object[] params = (Object[]) arg;
            // Set Parameter
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[0], scrnMsg.xxModeCd_CP);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[1], scrnMsg.xxModeCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[2], scrnMsg.glblCmpyCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[3], scrnMsg.cpoOrdNum);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[4], scrnMsg.xxChkBox);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[5], scrnMsg.configCatgCd);
            ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[6], scrnMsg.dsOrdPosnNum);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[7], scrnMsg.dsCpoLineNum);
            ZYPEZDItemValueSetter.setValue((EZDBBigDecimalItem) params[8], scrnMsg.xxQty10Num);
            // Add Start 2017/09/20 S21_NA#18859
            if (NWAL1620Constant.HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue((EZDBStringItem) params[9], scrnMsg.xxChkBox_R);
            }
            // Add End 2017/09/20 S21_NA#18859
        }
    }
}

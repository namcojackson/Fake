/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1620;

import static business.servlet.NWAL1620.constant.NWAL1620Constant.BIZ_ID;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.HEADER_MODE;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.MESSAGE_KIND_ERROR;
import static business.servlet.NWAL1620.constant.NWAL1620Constant.NWAM0270E;
import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1620.NWAL1620CMsg;
import business.blap.NWAL1620.constant.NWAL1620Constant;
import business.servlet.NWAL1620.common.NWAL1620CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 * NWAL1620_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/10/08   Fujitsu         C.Yokoi         Create          N/A
 * 2016/02/20   Fujitsu         S.Yoshida       Update          QC#2177
 * 2017/09/20   Fujitsu         T.Murai         Update          S21_NA#18859(Sol#154)
 *</pre>
 */
public class NWAL1620_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;

        // IN Parameter Get
        Object[] params = (Object[]) getArgForSubScreen();

        if (params != null && params.length >= 9) { // Mod 2017/09/20 S21_NA#18859
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd_CP, (EZDBStringItem) params[0]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxModeCd, (EZDBStringItem) params[1]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.glblCmpyCd, (EZDBStringItem) params[2]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.cpoOrdNum, (EZDBStringItem) params[3]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox, (EZDBStringItem) params[4]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.configCatgCd, (EZDBStringItem) params[5]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsOrdPosnNum, (EZDBStringItem) params[6]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.dsCpoLineNum, (EZDBBigDecimalItem) params[7]);
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxQty10Num, (EZDBBigDecimalItem) params[8]);
            // Add Start 2017/09/20 S21_NA#18859
            if (NWAL1620Constant.HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.xxChkBox_R, (EZDBStringItem) params[9]);
            }
            // Add End 2017/09/20 S21_NA#18859
            NWAL1620CommonLogic.setBackup(scrnMsg);

            if (!NWAL1620CommonLogic.checkInputParam(scrnMsg)) {
                return null;
            }
        } else {
            scrnMsg.setMessageInfo(NWAM0270E);
            return null;
        }

        if (HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            return null;
        }

        NWAL1620CMsg bizMsg = new NWAL1620CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;

        if (scrnMsg.getMessageCode().endsWith(MESSAGE_KIND_ERROR)) {
            NWAL1620CommonLogic.protectCmnBtnProp(this, scrnMsg);
            scrnMsg.setInputProtected(true);
            scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);
            return;
        }

        if (!HEADER_MODE.equals(scrnMsg.xxModeCd.getValue())) {
            NWAL1620CMsg bizMsg = (NWAL1620CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        NWAL1620CommonLogic.initCmnBtnProp(this, scrnMsg);
        NWAL1620CommonLogic.setInputProtect(scrnMsg);

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum);

        //Add Start NA QC#2177
        NWAL1620CommonLogic.setDispLineNum(scrnMsg);
        //Add End   NA QC#2177
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {

        NWAL1620BMsg scrnMsg = (NWAL1620BMsg) bMsg;
        scrnMsg.cpoOrdNum.setNameForMessage("Order Number");
        scrnMsg.configCatgCd.setNameForMessage("Configuration Category");
        scrnMsg.dsOrdPosnNum.setNameForMessage("Configuration Number");
        scrnMsg.xxLineNum_DI.setNameForMessage("Line Number");
        scrnMsg.xxChkBox.setNameForMessage("Copy Only Header");
        scrnMsg.xxQty10Num.setNameForMessage("Number Of Copies");
        scrnMsg.xxChkBox_R.setNameForMessage("Create Return Order"); // Add 2017/09/20 S21_NA#18859
    }
}

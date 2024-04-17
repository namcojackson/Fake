/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL1510;

import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.ItemRequiredRerease;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.addCheckItemBizLayerErr;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkFormatItem;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkRadioButton;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkRequired;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.convTimeforScreen;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.dlvyIstlDateCompareCheck;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.getScrnTm;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setAppFracDigit;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setScrnTm;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.setTabProtect;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.timeCompareCheck;
import static business.servlet.NWAL1510.common.NWAL1510CommonLogic.checkMassApplyCheckBox;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.BUSINESS_ID;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.FUNC_CD_UPDT;
import static business.servlet.NWAL1510.constant.NWAL1510Constant.NWAM0014E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL1510.NWAL1510CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL1510Scrn00_CMN_Save
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/02/08   Fujitsu         S.Ohki          Create          N/A
 * 2016/05/13   Fujitsu         M.suzuki        Update          S21_NA#7088
 * 2016/06/06   Fujitsu         S.Ohki          Update          S21_NA#7088
 * 2016/07/11   Fujitsu         H.Ikeda         Update          S21_NA#5030
 * 2017/08/08   Fujitsu         H.Nagashima     Update          S21_NA#16452
 * 2018/07/18   Fujitsu         K.Ishizuka      Update          S21_NA#26188
 *</pre>
 */
public class NWAL1510Scrn00_CMN_Save extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.cpoOrdNum_H0)) {
            scrnMsg.cpoOrdNum_H0.setErrorInfo(1, NWAM0014E);
        }
        checkMassApplyCheckBox(scrnMsg); // 2018/07/18 S21_NA#26188 Add
        // S21_NA#5030 Add start
        checkRadioButton(scrnMsg);
        // S21_NA#5030 Add end
        checkRequired(scrnMsg);
        checkFormatItem(scrnMsg);
        dlvyIstlDateCompareCheck(scrnMsg);
        timeCompareCheck(scrnMsg);
        // 2016/06/06 S21_NA#7088 Del Start
//        checkNameMandatory(scrnMsg);
//        ///2016/05/13 S21_NA#7088 Add Start --------------
//        checkInstallMandatory(scrnMsg);
//        checkTransMandatory(scrnMsg);
//        checkBldgEntMandatory(scrnMsg);
//        ///2016/05/13 S21_NA#7088 End --------------------
        // 2016/06/06 S21_NA#7088 Del End

        // 2016/06/06 S21_NA#7088 Add Start
        ItemRequiredRerease(scrnMsg);
        // 2016/06/06 S21_NA#7088 Add End

        addCheckItemBizLayerErr(scrnMsg);

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;

        NWAL1510CMsg bizMsg = new NWAL1510CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        getScrnTm(scrnMsg);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL1510BMsg scrnMsg = (NWAL1510BMsg) bMsg;
        NWAL1510CMsg bizMsg = (NWAL1510CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        setAppFracDigit(scrnMsg);
        convTimeforScreen(scrnMsg, bizMsg);
        setScrnTm(scrnMsg);

        if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
            addCheckItemBizLayerErr(scrnMsg);
            scrnMsg.putErrorScreen();
            throw new EZDFieldErrorException();
        }

        setTabProtect(this, scrnMsg);   // QC#16452 add

        scrnMsg.setFocusItem(scrnMsg.cpoOrdNum_H0);
        // 2016/06/06 S21_NA#7088 Mod Start
//        scrnMsg.addCheckItem(scrnMsg.cpoOrdNum_H0);
        addCheckItemBizLayerErr(scrnMsg);
        // 2016/06/06 S21_NA#7088 Mod End

        scrnMsg.putErrorScreen();

    }
}

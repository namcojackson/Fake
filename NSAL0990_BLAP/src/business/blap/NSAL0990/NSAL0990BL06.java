/*
 * <Pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</Pre>
 */
package business.blap.NSAL0990;

import static business.blap.NSAL0990.constant.NSAL0990Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import parts.common.EZDCMsg;
import parts.common.EZDSMsg;
import business.blap.NSAL0990.common.NSAL0990CommonLogic;
import business.blap.NSAL0990.common.NSAL0990CommonLogicForDsCPOUpdate;
import business.parts.NMZC002001PMsg;
import business.parts.NWZC150001PMsg;
import business.parts.NWZC157001PMsg;

import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;

/**
 *<pre>
 * Supply Order
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/06   Hitachi         K.Kasai         Create          N/A
 * 2016/03/15   Hitachi         A.Kohinata      Update          QC#5375
 * 2016/03/17   Hitachi         A.Kohinata      Update          QC#5632
 * 2016/03/23   Hitachi         A.Kohinata      Update          QC#5730
 * 2016/09/21   Hitachi         A.Kohinata      Update          QC#13267
 * 2016/10/13   Hitachi         A.Kohinata      Update          QC#9885
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15352
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 * 2019/06/19   Hitachi         K.Kim           Update          QC#50879
 *</pre>
 */
public class NSAL0990BL06 extends S21BusinessHandler {

    @Override
    protected void doProcess(EZDCMsg arg0, EZDSMsg arg1) {
        NSAL0990CMsg cMsg = (NSAL0990CMsg) arg0;
        super.preDoProcess(cMsg, null);

        try {
            String screenAplID = cMsg.getScreenAplID();
            if ("NSAL0990Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_CMN_Submit(cMsg);
            // add start 2019/01/21 QC#27304
            } else if ("NSAL0990Scrn00_CMN_Save".equals(screenAplID)) {
                doProcess_NSAL0990Scrn00_CMN_Save(cMsg);
            // add end 2019/01/21 QC#27304
            }
        } finally {
            super.postDoProcess(cMsg, null);
        }
    }

    private void doProcess_NSAL0990Scrn00_CMN_Submit(NSAL0990CMsg cMsg) {
        // add start 2016/10/13 CSA Defect#9885
        NSAL0990CommonLogic.resetFilter(cMsg, cMsg.xxScrDply.getValue());
        // add end 2016/10/13 CSA Defect#9885

        // mandatory check
        if (!NSAL0990CommonLogic.checkMandatory(cMsg)) {
            return;
        }
        if (!NSAL0990CommonLogic.checkValidation(cMsg)) {
            return;
        }
        // add start 2016/09/21 CSA Defect#13267
        if (!NSAL0990CommonLogic.checkValidationForSubmit(cMsg)) {
            return;
        }
        // add end 2016/09/21 CSA Defect#13267

        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        // call Contact Update API
        if (hasValue(cMsg.ctacPsnFirstNm) && hasValue(cMsg.ctacPsnLastNm) && hasValue(cMsg.ctacPsnEmlAddr)) {
            NMZC002001PMsg ctacUpdApiReqPMsg = NSAL0990CommonLogic.createCtacUpdApiReqPMsg(cMsg);
            if (!NSAL0990CommonLogic.callCtacUpdApi(cMsg, ctacUpdApiReqPMsg)) {
                return;
            }
        }
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]

        // create DsAcpo Info
        NSAL0990CommonLogicForDsCPOUpdate dsCPOUpdate = NSAL0990CommonLogicForDsCPOUpdate.getInstance();
        if (!dsCPOUpdate.createDsAcpoInfo(cMsg)) {
            return;
        }

        // call Pricing API for Get Order All Mode
        // mod start 2019/01/21 QC#27304
        NWZC157001PMsg prcApiPMsg = NSAL0990CommonLogic.calcuLineDtlForSubmit(cMsg);
        // mod end 2019/01/21 QC#27304
        // mod start 2016/10/19 CSA Defect#15293
        //if (!NSAL0990CommonLogic.checkPrcApiParam(cMsg, prcApiPMsg)) {
        if (prcApiPMsg == null) {
        // mod end 2016/10/19 CSA Defect#15293
            return;
        }
        // call DS CPO Update API
        NWZC150001PMsg dsCPOUpdateApiPMsg = dsCPOUpdate.callDsCPOUpdate(cMsg, prcApiPMsg);
        if (dsCPOUpdateApiPMsg == null) {
            return;
        }

        // add text
        dsCPOUpdate.addText(cMsg);

        // add start 2019/01/21 QC#27304
        if (hasValue(cMsg.svcSplyOrdPk)) {
            NSAL0990CommonLogic.deleteSvcSplyOrd(cMsg);
        }
        // add end 2019/01/21 QC#27304

        cMsg.setMessageInfo(NZZM0002I);
    }

    // add start 2019/01/21 QC#27304
    private void doProcess_NSAL0990Scrn00_CMN_Save(NSAL0990CMsg cMsg) {
        NSAL0990CommonLogic.resetFilter(cMsg, cMsg.xxScrDply.getValue());
        // START 2019/06/19 [QC#50879, ADD]
        if (!NSAL0990CommonLogic.checkShpgInstn(cMsg)) {
            return;
        }
        // END 2019/06/19 [QC#50879, ADD]
        if (hasValue(cMsg.svcSplyOrdPk)) {
            if (!NSAL0990CommonLogic.updateSvcSplyOrd(cMsg)) {
                return;
            }
        } else {
            if (!NSAL0990CommonLogic.insertSvcSplyOrd(cMsg)) {
                return;
            }
        }
        cMsg.setMessageInfo(NZZM0002I);
    }
    // add end 2019/01/21 QC#27304
}

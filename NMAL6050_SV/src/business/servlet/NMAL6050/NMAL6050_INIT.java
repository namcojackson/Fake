package business.servlet.NMAL6050;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6050.NMAL6050CMsg;
import business.servlet.NMAL6050.common.NMAL6050CommonLogic;
import business.servlet.NMAL6050.constant.NMAL6050Constant;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/**
 *<pre>
 *  Common Code PopUp NMAL6050_INIT
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 09/20/2013   Hitachi         T.Arakawa       Update          WDS Defect#2453
 *</pre>
 */
public class NMAL6050_INIT extends S21INITCommonHandler implements NMAL6050Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        // NMAL6050BMsg scrnMsg = (NMAL6050BMsg)bMsg;
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;

        Serializable arg = getArgForSubScreen();

        if (arg instanceof Object[]) {

            Object[] params = (Object[]) arg;
            EZDBStringItem param0 = (EZDBStringItem) params[0];
            EZDBStringItem param1 = (EZDBStringItem) params[1];
            EZDBStringItem param2 = (EZDBStringItem) params[2];
            EZDBStringItem param3 = (EZDBStringItem) params[3];
            EZDBStringItem param4 = (EZDBStringItem) params[4];
            EZDBStringItem param5 = (EZDBStringItem) params[5];
            EZDBStringItem param6 = (EZDBStringItem) params[6];
            EZDBStringItem param7 = (EZDBStringItem) params[7];
            EZDBStringItem param8 = (EZDBStringItem) params[8];
            EZDBStringItem param9 = (EZDBStringItem) params[9];
            EZDBStringItem param10 = (EZDBStringItem) params[10];

            scrnMsg.xxTblNm.setValue(param0.getValue());
            scrnMsg.xxTblCdColNm.setValue(param1.getValue());
            scrnMsg.xxTblNmColNm.setValue(param2.getValue());
            scrnMsg.xxTblSortColNm.setValue(param3.getValue());
            scrnMsg.xxScrNm.setValue(param4.getValue());

            //scrnMsg.xxHdrCdLbNm.setValue(NMAL6050CommonLogic.catEndWithStringCd(param5.getValue()));
            //scrnMsg.xxHdrNmLbNm.setValue(NMAL6050CommonLogic.catEndWithStringNm(param6.getValue()));
            //scrnMsg.xxDtlCdLbNm.setValue(NMAL6050CommonLogic.catEndWithStringCd(param7.getValue()));
            //scrnMsg.xxDtlNmLbNm.setValue(NMAL6050CommonLogic.catEndWithStringNm(param8.getValue()));
            scrnMsg.xxHdrCdLbNm.setValue(param5.getValue());
            scrnMsg.xxHdrNmLbNm.setValue(param6.getValue());
            scrnMsg.xxDtlCdLbNm.setValue(param7.getValue());
            scrnMsg.xxDtlNmLbNm.setValue(param8.getValue());
            scrnMsg.xxCondCd.setValue(param9.getValue());
            scrnMsg.xxCondNm.setValue(param10.getValue());
        }

        NMAL6050CMsg bizMsg = new NMAL6050CMsg();
        bizMsg.setBusinessID("NMAL6050");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6050BMsg scrnMsg = (NMAL6050BMsg) bMsg;
        NMAL6050CMsg bizMsg = (NMAL6050CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        // S-ADD-20130920 WDS Defect#2453
        NMAL6050CommonLogic.convLabelNames(scrnMsg);
        // E-ADD-20130920 WDS Defect#2453

        scrnMsg.setDisplayPageName(scrnMsg.xxScrNm.getValue());

        NMAL6050CommonLogic.initCommonButton(this);
        NMAL6050CommonLogic.initButton(this);

        NMAL6050CommonLogic.initScrn(scrnMsg, scrnMsg.A.no(0).getBaseContents());

    }

    @Override
    protected void setNameForMessage(EZDBMsg arg0) {

    }

}

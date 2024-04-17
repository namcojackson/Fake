/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;

import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;
import business.servlet.NSAL0990.constant.NSAL0990Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/03/09   Hitachi         A.Kohinata      Update          QC#5196
 * 2016/03/11   Hitachi         A.Kohinata      Update          QC#5354
 * 2016/09/21   Hitachi         N.Arai          Update          QC#11616
 * 2018/11/14   Fujitsu         M.Yamada        Update          QC#29191
 * 2019/01/21   Hitachi         A.Kohinata      Update          QC#27304
 *</pre>
 */
public class NSAL0990_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // QC#29191
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (OPENWIN_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue()) //
                || ONBLUR_DERIVEFROM_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue())) {
            if (!"CMN_Close".equals(getLastGuard())) {
                NSAL0990CMsg bizMsg = new NSAL0990CMsg();
                bizMsg.setBusinessID("NSAL0990");
                bizMsg.setFunctionCode("20");

                setValue(scrnMsg.frtCondCd, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
                setValue(scrnMsg.frtCondDescTxt, scrnMsg.Z.no(2).xxComnScrColValTxt.getValue());
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            } else {
                return null;
            }
        }
        // add start 2019/01/21 QC#27304
        if ("OpenWin_EditingList".equals(scrnMsg.xxScrEventNm.getValue())) {
            if (!"CMN_Close".equals(getLastGuard()) && hasValue(scrnMsg.Z.no(6).xxComnScrColValTxt)) {
                NSAL0990CMsg bizMsg = new NSAL0990CMsg();
                bizMsg.setBusinessID("NSAL0990");
                bizMsg.setFunctionCode("20");
                setValue(scrnMsg.svcSplyOrdPk, new BigDecimal(scrnMsg.Z.no(6).xxComnScrColValTxt.getValue()));
                EZDMsg.copy(scrnMsg, null, bizMsg, null);
                return bizMsg;
            }
        }
        // add end 2019/01/21 QC#27304
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        if (OPENWIN_CARRIER_SERVICE_LEVEL.equals(scrnMsg.xxScrEventNm.getValue()) || ONBLUR_DERIVEFROM_CARRIER_SERVICE_LEVEL.equals(scrnMsg.xxScrEventNm.getValue())) {
            setValue(scrnMsg.carrSvcLvlCd, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
            setValue(scrnMsg.carrSvcLvlDescTxt, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            scrnMsg.setFocusItem(scrnMsg.carrAcctNum);
        // QC#29191
        } else if (OPENWIN_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue()) //
                || ONBLUR_DERIVEFROM_FREIGHT_TERMS.equals(scrnMsg.xxScrEventNm.getValue())) {
            NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            NSAL0990CommonLogic.setProtectByFrtCond(scrnMsg);
            scrnMsg.addCheckItem(scrnMsg.frtCondDescTxt);
            scrnMsg.putErrorScreen();
            scrnMsg.setFocusItem(scrnMsg.shpgSvcLvlCd);

        } else if (OPENWIN_MERCHANDISE.equals(scrnMsg.xxScrEventNm.getValue())) {
            setValue(scrnMsg.mdseCd, scrnMsg.Z.no(0).xxComnScrColValTxt.getValue());
         // START 2016/09/21 N.Arai [QC#11616, MOD]
         // setValue(scrnMsg.mdseNm, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
            setValue(scrnMsg.mdseDescShortTxt, scrnMsg.Z.no(1).xxComnScrColValTxt.getValue());
         // END 2016/09/21 N.Arai [QC#11616, MOD]
            scrnMsg.setFocusItem(scrnMsg.mdseCd);
        // add start 2019/01/21 QC#27304
        } else if (BTN_EDIT_LIST.equals(scrnMsg.xxScrEventNm.getValue())) {
            NSAL0990CMsg bizMsg = (NSAL0990CMsg) cMsg;
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
            if ("E".equals(bizMsg.getMessageKind())) {
                throw new EZDFieldErrorException();
            }
            List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(NSAL0990Constant.BIZ_ID);
            NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
            NSAL0990CommonLogic.activateScreenItems(this, functionList, scrnMsg);
            NSAL0990CommonLogic.formatItem(scrnMsg);
            NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
            NSAL0990CommonLogic.focusItem(scrnMsg);
            NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
        // add end 2019/01/21 QC#27304
        }
    }
}

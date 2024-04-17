/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0990;

import static business.servlet.NSAL0990.constant.NSAL0990Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0990.NSAL0990CMsg;
import business.servlet.NSAL0990.common.NSAL0990CommonLogic;

import com.canon.cusa.s21.framework.online.servlet.S21INITCommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/20   Hitachi         K.Kasai         Create          N/A
 * 2016/03/10   Hitachi         A.Kohinata      Update          QC#5210
 * 2016/07/06   Hitachi         O.Okuma         Update          QC#9630
 * 2016/09/23   Hitachi         Y.Zhang         Update          QC#12582
 * 2016/10/19   Hitachi         A.Kohinata      Update          QC#15344
 * 2017/11/29   Hitachi         K.Kojima        Update          QC#20497
 * 2017/12/26   Fujitsu         K.Ishizuka      Update          QC#20164(Sol#356)
 * 2018/04/11   Hitachi         K.Kitachi       Update          QC#11642
 * 2018/11/07   Hitachi         K.Kitachi       Update          QC#27387
 *</pre>
 */
public class NSAL0990_INIT extends S21INITCommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        checkBusinessAppGranted(getContextUserInfo().getUserId(), BIZ_ID);
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        setValue(scrnMsg.xxScrDply, MODE_1);
        BigDecimal svcMachMstrPk = null;
        BigDecimal dsContrDtlPk = null;

        Serializable ser = getArgForSubScreen();
        if (ser instanceof Object[]) {
            Object[] params = (Object[]) ser;
            if (params != null) {
                if (params.length > 0 && params[0] != null && params[0] instanceof EZDBBigDecimalItem) {
                    svcMachMstrPk = ((EZDBBigDecimalItem) params[0]).getValue();
                } else {
                    scrnMsg.setMessageInfo(NSAM0131E, new String[]{"Service Machine Master PK"});
                    throw new EZDFieldErrorException();
                }
                if (params.length > 1 && params[1] != null && params[1] instanceof EZDBBigDecimalItem) {
                    dsContrDtlPk = ((EZDBBigDecimalItem) params[1]).getValue();
                } else {
                    scrnMsg.setMessageInfo(NSAM0131E, new String[]{"Ds Contract Detail PK"});
                    throw new EZDFieldErrorException();
                }
            }
        }
        setValue(scrnMsg.svcMachMstrPk, svcMachMstrPk);
        setValue(scrnMsg.dsContrDtlPk, dsContrDtlPk);

        NSAL0990CMsg bizMsg = new NSAL0990CMsg();
        bizMsg.setBusinessID("NSAL0990");
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        NSAL0990CMsg bizMsg  = (NSAL0990CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        List<String> functionList = getUserProfileService().getFunctionCodeListForBizAppId(BIZ_ID);
        NSAL0990CommonLogic.activateButtons(this, scrnMsg, functionList);
        NSAL0990CommonLogic.activateScreenItems(this, functionList, scrnMsg);
        NSAL0990CommonLogic.alternateTableRowColor(scrnMsg);
        NSAL0990CommonLogic.focusItem(scrnMsg);
        NSAL0990CommonLogic.formatItem(scrnMsg);
        NSAL0990CommonLogic.setXxDplyCtrlFlg(scrnMsg);
        // QC#23726 Add Start
        NSAL0990CommonLogic.setProtectByFrtCond(scrnMsg);
        // QC#23726 Add End
    }

    @Override
    protected void setNameForMessage(EZDBMsg bMsg) {
        NSAL0990BMsg scrnMsg = (NSAL0990BMsg) bMsg;
        scrnMsg.billToLocNum.setNameForMessage("Bill To");
        scrnMsg.curLocNum.setNameForMessage("Ship To");
        scrnMsg.custIssPoNum.setNameForMessage("PO#");
        // START 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.ctacPsnFirstNm.setNameForMessage("First Name");
        scrnMsg.ctacPsnLastNm.setNameForMessage("Last Name");
        scrnMsg.ctacPsnEmlAddr.setNameForMessage("Email");
        // END 2018/04/11 K.Kitachi [QC#11642, ADD]
        scrnMsg.frtCondDescTxt.setNameForMessage("Freight Terms");
        scrnMsg.carrSvcLvlDescTxt.setNameForMessage("Carrier Service Level");
        scrnMsg.carrAcctNum.setNameForMessage("Customer's Carrier Account#");
        scrnMsg.invCmntTxt.setNameForMessage("Invoice Comments");
        // add start 2016/10/19 CSA Defect#15344
        scrnMsg.shpgSvcLvlCd.setNameForMessage("Service Level");
        // add end 2016/10/19 CSA Defect#15344
        // START 2016/09/23 Y.Zhang [QC#12582, MOD]
        scrnMsg.mdseCd.setNameForMessage("Item Cd");
        // END 2016/09/23 Y.Zhang [QC#12582, MOD]
        // START 2018/11/07 K.Kitachi [QC#27387, MOD]
//        scrnMsg.scrLbNm.setNameForMessage("Shipping Label");//2017/12/26 S21_NA#20164(Sol#356) ADD
        scrnMsg.scrLbNm.setNameForMessage("Attention");
        // END 2018/11/07 K.Kitachi [QC#27387, MOD]
        for (int i = 0; i < scrnMsg.C.length(); i++) {
            scrnMsg.C.no(i).ordCustUomQty_C.setNameForMessage("Ordered Qty");
        }
        for (int i = 0; i < scrnMsg.E.length(); i++) {
            scrnMsg.E.no(i).ordCustUomQty_E.setNameForMessage("Ordered Qty");
        }
        // START 2017/11/29 K.Kojima [QC#20497,ADD]
        NSAL0990CommonLogic.setProtectByFrtCond(scrnMsg);
        // END 2017/11/29 K.Kojima [QC#20497,ADD]
    }
}

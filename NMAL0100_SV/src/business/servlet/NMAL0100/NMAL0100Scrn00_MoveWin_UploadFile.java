/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0100;

import static business.servlet.NMAL0100.constant.NMAL0100Constant.NMAM8461E;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.NMAM8708E;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.SCRN_TRANS_COND_ZYP0210_TAX;
import static business.servlet.NMAL0100.constant.NMAL0100Constant.SCRN_TRANS_COND_ZYP0210_HEADER;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NMAL0100.NMAL0100CMsg;
//import business.servlet.NMAL0100.constant.NMAL0100Constant;

import business.servlet.NMAL0100.common.NMAL0100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ITEM_MSTR_UPLD_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2020/07/14   CITS            M.Furugoori     Create          QC#55448
 * 2022/10/28   Hitachi         S.Nakatani      Update          QC#60399
 *</pre>
 */
public class NMAL0100Scrn00_MoveWin_UploadFile extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;

        if (!ZYPCommonFunc.hasValue(scrnMsg.itemMstrUpldTpCd_H1)) {
            scrnMsg.itemMstrUpldTpCd_H1.setErrorInfo(1, NMAM8461E, new String[] {"Upload Name"});
        }

        scrnMsg.addCheckItem(scrnMsg.itemMstrUpldTpCd_H1);
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL0100BMsg scrnMsg = (NMAL0100BMsg) bMsg;
        ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrEventNm, getLastGuard());
        ZYPTableUtil.clear(scrnMsg.P);
        // START 2022/10/28 [QC#60399,MOD]
//        if (NMAL0100CommonLogic.isTaxUploadUser(getUserProfileService())) {
        if (ITEM_MSTR_UPLD_TP.TAXING_TAB_UPDATE.equals(scrnMsg.itemMstrUpldTpCd_H1.getValue()) && NMAL0100CommonLogic.isTaxUploadUser(getUserProfileService())) {
        // END 2022/10/28 [QC#60399,MOD]
            scrnMsg.P.no(0).xxPopPrm.setValue(SCRN_TRANS_COND_ZYP0210_TAX);
            Object[] params = new Object[1];
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            setArgForSubScreen(params);
            // START 2022/10/28 [QC#60399,ADD]
        }else if(ITEM_MSTR_UPLD_TP.HEADER_UPDATE.equals(scrnMsg.itemMstrUpldTpCd_H1.getValue()) && NMAL0100CommonLogic.isHeaderUploadUser(getUserProfileService())) {
            scrnMsg.P.no(0).xxPopPrm.setValue(SCRN_TRANS_COND_ZYP0210_HEADER);
            Object[] params = new Object[1];
            params[0] = scrnMsg.P.no(0).xxPopPrm;
            setArgForSubScreen(params);
            // END 2022/10/28 [QC#60399,ADD]
        }else {
            scrnMsg.setMessageInfo(NMAM8708E);
        }

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }
}

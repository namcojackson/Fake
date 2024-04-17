/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL2550;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NMAL2550.common.NMAL2550CommonLogic;
import business.servlet.NMAL2550.constant.NMAL2550Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/09/28   Fujitsu         K.Koitabashi    Create          N/A
 * 2016/11/25   Hitachi         Y.Tsuchimoto    Update          QC#16198
 *</pre>
 */
public class NMAL2550Scrn00_Select_ConditionCode extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL2550BMsg scrnMsg = (NMAL2550BMsg) bMsg;

        int selectedIndex = getButtonSelectNumber();
        // START 2016/11/25 Y.Tsuchimoto [QC#16198,MOD]
        if (NMAL2550Constant.COMPANY == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaCmpyCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaCmpyDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaCmpyCd_H1);

        } else if (NMAL2550Constant.AFFILIATE == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaAfflCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaAfflDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaAfflCd_H1);

        } else if (NMAL2550Constant.BRANCH == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaBrCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaBrDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaBrCd_H1);

        } else if (NMAL2550Constant.COST_CENTER == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaCcCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaCcDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaCcCd_H1);

        } else if (NMAL2550Constant.ACCOUNT == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaAcctCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaAcctDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaAcctCd_H1);

        } else if (NMAL2550Constant.PRODUCT == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaProdCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaProdDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaProdCd_H1);

        } else if (NMAL2550Constant.CHANNEL == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaChCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaChDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaChCd_H1);

        } else if (NMAL2550Constant.PROJECT == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaProjCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaProjDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaProjCd_H1);

        } else if (NMAL2550Constant.EXTENSION == scrnMsg.xxNum.getValueInt()) {
            scrnMsg.coaExtnCd_H1.setValue(scrnMsg.A.no(selectedIndex).xxDtlCd_A1.getValue());
            scrnMsg.coaExtnDescTxt_H1.setValue(scrnMsg.A.no(selectedIndex).dtlDescTxt_A1.getValue());
            scrnMsg.setFocusItem(scrnMsg.coaExtnCd_H1);
        }
        // END   2016/11/25 Y.Tsuchimoto [QC#16198,MOD]

        NMAL2550CommonLogic.clearCondition(scrnMsg);

    }
}

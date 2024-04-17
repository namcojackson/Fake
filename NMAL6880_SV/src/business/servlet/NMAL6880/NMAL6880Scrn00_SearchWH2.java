/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL6880;

import static business.servlet.NMAL6880.constant.NMAL6880Constant.BIZ_APP_ID;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.CSA_WH;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.FUNCTION_CD_SEARCH;
import static business.servlet.NMAL6880.constant.NMAL6880Constant.NMAM0836E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NMAL6880.NMAL6880CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL6880 TPC09 WH Mapping Maintenance
 * Function Name : SearchWH2
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/09/2016   CITS            K.Ogino         Create          N/A
 * 07/12/2017   CITS            T.Kikuhara      Update          QC#19864
 *</pre>
 */
public class NMAL6880Scrn00_SearchWH2 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        int selectIdx = getButtonSelectNumber();
        if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(selectIdx).rtlWhCd_A1)) {
            scrnMsg.A.no(selectIdx).rtlWhCd_A1.setErrorInfo(1, NMAM0836E, new String[] {CSA_WH });
        }
        scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).rtlWhCd_A1);
        scrnMsg.xxPopPrm_PD.setValue(String.valueOf(selectIdx));
        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;

        NMAL6880CMsg bizMsg = new NMAL6880CMsg();
        bizMsg.setBusinessID(BIZ_APP_ID);
        bizMsg.setFunctionCode(FUNCTION_CD_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NMAL6880BMsg scrnMsg = (NMAL6880BMsg) bMsg;
        NMAL6880CMsg bizMsg = (NMAL6880CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        int selectIdx = getButtonSelectNumber();
        scrnMsg.addCheckItem(scrnMsg.A.no(selectIdx).rtlWhCd_A1);
        scrnMsg.putErrorScreen();

        scrnMsg.setFocusItem(scrnMsg.A.no(selectIdx).rtlWhCd_A1);

    }
}

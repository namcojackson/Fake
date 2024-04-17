/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/03/12   Fujitsu         T.Yoshida       Create          N/A
 * 2017/07/25   CITS            S.Endo          Update          Sol#072(QC#18386)
 *</pre>
 */
public class NSAL0490Scrn00_Setting_MdseInfo extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        int selectIndex = getButtonSelectNumber();
        String dplyTab = scrnMsg.xxDplyTab.getValue();

        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(dplyTab)) {
            scrnMsg.xxCellIdx_A.setValue(selectIndex);
            NSAL0490_ABMsg targetAbMsg = scrnMsg.A.no(selectIndex);

            if (NSAL0490Constant.DTL_TP_PRNT.equals(targetAbMsg.xxFlgNm_A.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).prntMdseCd_A);
            } else {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).childMdseCd_A);
            }
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            scrnMsg.xxCellIdx_B.setValue(selectIndex);
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_B);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
            scrnMsg.xxCellIdx_D.setValue(selectIndex);
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_D);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }

        scrnMsg.putErrorScreen();
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        NSAL0490CMsg bizMsg = new NSAL0490CMsg();
        bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
        NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        String dplyTab = scrnMsg.xxDplyTab.getValue();
        int selectIndex = getButtonSelectNumber();

        if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
            if (NSAL0490Constant.DTL_TP_PRNT.equals(scrnMsg.A.no(selectIndex).xxFlgNm_A.getValue())) {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).prntMdseCd_A);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).prntMdseCd_A);
            } else {
                scrnMsg.addCheckItem(scrnMsg.A.no(selectIndex).childMdseCd_A);
                scrnMsg.setFocusItem(scrnMsg.A.no(selectIndex).childMdseCd_A);
            }
        } else if (NSAL0490Constant.TAB_SUPPLY_MAP.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.B.no(selectIndex).mdseCd_B);
            scrnMsg.setFocusItem(scrnMsg.B.no(selectIndex).mdseCd_B);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
        } else if (NSAL0490Constant.TAB_CRITICALITY.equals(dplyTab)) {
            scrnMsg.addCheckItem(scrnMsg.D.no(selectIndex).mdseCd_D);
            scrnMsg.setFocusItem(scrnMsg.D.no(selectIndex).mdseCd_D);
        //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
        }

        scrnMsg.putErrorScreen();
    }
}

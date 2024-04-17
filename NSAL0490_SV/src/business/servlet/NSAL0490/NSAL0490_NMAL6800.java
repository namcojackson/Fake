/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0490;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
//import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
//import business.blap.NSAL0490.NSAL0490CMsg;
//import business.servlet.NSAL0490.constant.NSAL0490Constant;

import business.blap.NSAL0490.NSAL0490CMsg;
import business.servlet.NSAL0490.constant.NSAL0490Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/03/21   Hitachi         K.Kasai         Create          QC#3586
 * 2017/08/01   CITS            S.Endo          Update          Sol#072(QC#18386)
 *</pre>
 */
public class NSAL0490_NMAL6800 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;

        if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue()) && !"CMN_Close".equals(getLastGuard())) {
            NSAL0490CMsg bizMsg = new NSAL0490CMsg();
            bizMsg.setBusinessID(NSAL0490Constant.BUSINESS_ID);
            bizMsg.setFunctionCode(NSAL0490Constant.FUNCTION_SEARCH);

            int selectIndex = getButtonSelectNumber();
            if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.xxCellIdx_A.setValue(selectIndex);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
            } else if (NSAL0490Constant.TAB_CRITICALITY.equals(scrnMsg.xxDplyTab.getValue())) {
                scrnMsg.xxCellIdx_D.setValue(selectIndex);
            //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
            }else {
                scrnMsg.xxCellIdx_B.setValue(selectIndex);
            }

            EZDMsg.copy(scrnMsg, null, bizMsg, null);
            return bizMsg;
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if (!"CMN_Close".equals(getLastGuard())) {
            NSAL0490BMsg scrnMsg = (NSAL0490BMsg) bMsg;
            NSAL0490CMsg bizMsg = (NSAL0490CMsg) cMsg;

            if (bizMsg != null) {
                EZDMsg.copy(bizMsg, null, scrnMsg, null);
            }

            if (NSAL0490Constant.ITEM_NM_MDSE_CD.equals(scrnMsg.xxScrEventNm.getValue())) {
                int selectIndex = getButtonSelectNumber();

                if (NSAL0490Constant.TAB_ITEM_CONFIG.equals(scrnMsg.xxDplyTab.getValue())) {
                    NSAL0490_ABMsg abMsg = scrnMsg.A.no(selectIndex);

                    if (NSAL0490Constant.DTL_TP_PRNT.equals(abMsg.xxFlgNm_A.getValue())) {
                        scrnMsg.setFocusItem(abMsg.prntMdseCd_A);
                    } else {
                        scrnMsg.setFocusItem(abMsg.childMdseCd_A);
                    }
                //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) START
                } else if (NSAL0490Constant.TAB_CRITICALITY.equals(scrnMsg.xxDplyTab.getValue())) {
                    NSAL0490_DBMsg dbMsg = scrnMsg.D.no(selectIndex);
                    scrnMsg.setFocusItem(dbMsg.mdseCd_D);
                //08/03/2017 CITS S.Endo Add Sol#072(QC#18386) END
                } else {
                    NSAL0490_BBMsg bbMsg = scrnMsg.B.no(selectIndex);
                    scrnMsg.setFocusItem(bbMsg.mdseCd_B);
                }
            }
        }

    }
}

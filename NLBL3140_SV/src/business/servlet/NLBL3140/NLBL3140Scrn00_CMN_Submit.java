/*
 * <pre>Copyright (c) 2017 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLBL3140;

import static business.servlet.NLBL3140.constant.NLBL3140Constant.BIZ_ID;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.FUNC_CD_UPDT;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.INVTY_HARD_ALLOC_TP_CD_10;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.INVTY_HARD_ALLOC_TP_CD_20;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.NLZM2494W;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.ZZM9000E;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.ZZZM9024E;
import static business.servlet.NLBL3140.constant.NLBL3140Constant.ZZZM9026E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NLBL3140.NLBL3140CMsg;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/07/21   Fujitsu         K.Ishizuka      Create          N/A
 * 2017/09/22   CITS            S.Endo          Update          QC#21077
 * 2017/10/06   CITS            T.Kikuhara      Update          QC#20772
 * 2023/07/11   Hitachi         G.Quan          Update          QC#61543
 *</pre>
 */
public class NLBL3140Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).lineBizTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhCd_A);
            if (INVTY_HARD_ALLOC_TP_CD_10.equals(scrnMsg.A.no(i).hardAllocTpCd_A.getValue())) {
                //2017/09/22 S.Endo Mod QC#21077 START
                //if (!ZYPCommonFunc.hasValue(scrnMsg.A.no(i).tmFenceDaysAot_A)) {
                //    scrnMsg.A.no(i).tmFenceDaysAot_A.setErrorInfo(1, ZZM9000E, new String[] {"Time Fence" });
                //} else if (scrnMsg.A.no(i).tmFenceDaysAot_A.getValueInt() < 0) {
                if (scrnMsg.A.no(i).tmFenceDaysAot_A.getValueInt() < 0) {
                    scrnMsg.A.no(i).tmFenceDaysAot_A.setErrorInfo(1, ZZZM9026E, new String[] {"Time Fence" });
                }
                //2017/09/22 S.Endo Mod QC#21077 END
            } else if (INVTY_HARD_ALLOC_TP_CD_20.equals(scrnMsg.A.no(i).hardAllocTpCd_A.getValue())) {
                if (ZYPCommonFunc.hasValue(scrnMsg.A.no(i).tmFenceDaysAot_A)) {
                    scrnMsg.A.no(i).tmFenceDaysAot_A.setErrorInfo(1, ZZZM9024E, new String[] {"Blank" });
                }
            }
            scrnMsg.addCheckItem(scrnMsg.A.no(i).tmFenceDaysAot_A);
        }
        scrnMsg.putErrorScreen();

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;

        NLBL3140CMsg bizMsg = new NLBL3140CMsg();

        // QC#20772 DEL START
        // 2017/08/14 QC#20555 ADD BEGIN
        //if (scrnMsg.A.getValidCount() == 0 && bizMsg.B.getValidCount() == 0) {
        //    scrnMsg.setMessageInfo(NLZM2494W);
        //    throw new EZDFieldErrorException();
        //}
        // 2017/08/14 QC#20555 ADD END
        // QC#20772 DEL START
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode(FUNC_CD_UPDT);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLBL3140BMsg scrnMsg = (NLBL3140BMsg) bMsg;
        NLBL3140CMsg bizMsg = (NLBL3140CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            scrnMsg.addCheckItem(scrnMsg.A.no(i).lineBizTpCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlWhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).rtlSwhCd_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).dsOrdCatgDescTxt_A);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).hardAllocTpCd_A);
            // START 2023/07/11 G.Quan [QC#61543, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).coaProdCd_A);
            // END 2023/07/11 G.Quan [QC#61543, ADD]
        }
        scrnMsg.putErrorScreen();

    }
}

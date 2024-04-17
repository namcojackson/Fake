/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL0740;

import static business.servlet.NSAL0740.constant.NSAL0740Constant.*;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NSAL0740.NSAL0740CMsg;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/11/30   Hitachi         Y.Takeno        Create          N/A
 * 2016/02/08   Hitachi         T.Aoyagi        Update          QC#4089
 * 2016/03/07   Hitachi         T.Aoyagi        Update          QC#3945
 * 2016/05/17   Hitachi         M.Gotou         Update          QC#4472
 * 2017/12/21   Hitachi         M.Kidokoro      Update          QC#22660
 * 2018/11/16   Hitachi         K.Kitachi       Update          QC#28638
 *</pre>
 */
public class NSAL0740Scrn00_CMN_Submit extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        scrnMsg.addCheckItem(scrnMsg.svcMemoRsnCd_H3);
        scrnMsg.addCheckItem(scrnMsg.svcCmntTxt_H1);
        // START 2016/04/15 T.Tomita [QC#4085, MOD]
//        // START 2016/02/08 T.Aoyagi [QC#4089, MOD]
////        scrnMsg.A.setCheckParam(new String[] {NSAL0740Bean.basePrcUpRatio_D1, NSAL0740Bean.mtrPrcUpRatio_D1 }, 1);
//        scrnMsg.A.setCheckParam(new String[] {NSAL0740Bean.uplftBasePrcUpRatio_D1, NSAL0740Bean.uplftMtrPrcUpRatio_D1 }, 1);
//        // END   2016/02/08 T.Aoyagi [QC#4089, MOD]
        // START 2018/11/16 K.Kitachi [QC#28638, MOD]
//        scrnMsg.A.setCheckParam(new String[] {NSAL0740Bean.uplftBefEndRnwDaysAot_D1, NSAL0740Bean.uplftBasePrcUpRatio_D1, NSAL0740Bean.uplftMtrPrcUpRatio_D1 }, 1);
        scrnMsg.A.setCheckParam(new String[] {NSAL0740Bean.uplftBefEndRnwDaysAot_D1, NSAL0740Bean.uplftBasePrcUpRatio_D1, NSAL0740Bean.uplftMtrPrcUpRatio_D1, NSAL0740Bean.maxPrcUpRatio_D1, NSAL0740Bean.fixTermInMthAot_D1, NSAL0740Bean.uplftFixedDt_D1 }, 1);
        // END 2018/11/16 K.Kitachi [QC#28638, MOD]
        // END 2016/04/15 T.Tomita [QC#4085, MOD]
        scrnMsg.addCheckItem(scrnMsg.A);
        scrnMsg.putErrorScreen();

        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;

        NSAL0740CMsg bizMsg = new NSAL0740CMsg();
        bizMsg.setBusinessID(BUSINESS_ID);
        bizMsg.setFunctionCode(FUCNTION_CD_UPDATE);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NSAL0740BMsg scrnMsg = (NSAL0740BMsg) bMsg;
        NSAL0740CMsg bizMsg = (NSAL0740CMsg) cMsg;

        EZDMsg.copy(bizMsg, null, scrnMsg, null);
        // START 2016/03/07 T.Aoyagi [QC#3945, ADD]
        for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {
            // START 2017/12/21 M.Kidokoro [QC#22660, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).contrUplftTpCd_D3);
            // END 2017/12/21 M.Kidokoro [QC#22660, ADD]
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftPrcMethCd_D3);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftBefEndRnwDaysAot_D1);
            // add start 2016/05/17 CSA Defect#4472
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftBasePrcUpRatio_D1);
            scrnMsg.addCheckItem(scrnMsg.A.no(i).uplftMtrPrcUpRatio_D1);
            // add end 2016/05/17 CSA Defect#4472
        }
        scrnMsg.putErrorScreen();
        if (scrnMsg.getMessageType() == EZDMessageInfo.MSGTYPE_ERROR) {
            throw new EZDFieldErrorException();
        }
        // END 2016/03/07 T.Aoyagi [QC#3945, ADD]

        // add start 2016/05/17 CSA Defect#4472
        if (scrnMsg.getMessageType() != EZDMessageInfo.MSGTYPE_WARNING) {
            setButtonEnabled(BTN_CMN_SUBMIT[0], false);
        }
        // add end 2016/05/17 CSA Defect#4472
    }
}

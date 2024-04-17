/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLAL1100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NLAL1100.common.NLAL1100CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * NLAL1100 Manage RMA Orders
 * 
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 05/16/2016   CSAI            Y.Imazu         Create          QC#7972
 * 02/07/2018   CITS            K.Ogino         Update          QC#24010
 *</pre>
 */
public class NLAL1100Scrn00_Add_Comment extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // There is no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLAL1100BMsg scrnMsg = (NLAL1100BMsg) bMsg;

        ZYPTableUtil.clear(scrnMsg.D);

        for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
            // QC#24010
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).updUsrId_D1, scrnMsg.C.no(i).updUsrId_C1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).xxScrItem130Txt_D1, scrnMsg.C.no(i).xxScrItem130Txt_C1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).xxTsDsp19Txt_D1, scrnMsg.C.no(i).xxTsDsp19Txt_C1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).rtrnTrxCmntTxt_D1, scrnMsg.C.no(i).rtrnTrxCmntTxt_C1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.D.no(i).manCratFlg_D1, scrnMsg.C.no(i).manCratFlg_C1.getValue());
        }

        scrnMsg.D.setValidCount(scrnMsg.C.getValidCount());

        ZYPTableUtil.clear(scrnMsg.C);

        int indexC = 0;
        // QC#24010
        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).xxScrItem130Txt_C1, scrnMsg.xxScrItem130Txt_U1.getValue());
        ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).manCratFlg_C1, ZYPConstant.FLG_ON_Y);

        indexC++;

        for (int i = 0; i < scrnMsg.D.getValidCount(); i++) {

            if (indexC == scrnMsg.C.length()) {

                break;
            }
            // QC#24010
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).updUsrId_C1, scrnMsg.D.no(i).updUsrId_D1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).xxScrItem130Txt_C1, scrnMsg.D.no(i).xxScrItem130Txt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).xxTsDsp19Txt_C1, scrnMsg.D.no(i).xxTsDsp19Txt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).rtrnTrxCmntTxt_C1, scrnMsg.D.no(i).rtrnTrxCmntTxt_D1.getValue());
            ZYPEZDItemValueSetter.setValue(scrnMsg.C.no(indexC).manCratFlg_C1, scrnMsg.D.no(i).manCratFlg_D1.getValue());

            indexC++;
        }

        scrnMsg.C.setValidCount(indexC);
        boolean isUpdateUser = NLAL1100CommonLogic.isUpdateUser(getUserProfileService());
        NLAL1100CommonLogic.controlCommentFields(scrnMsg, isUpdateUser);
        NLAL1100CommonLogic.setBgColorCommnets(scrnMsg);
    }
}

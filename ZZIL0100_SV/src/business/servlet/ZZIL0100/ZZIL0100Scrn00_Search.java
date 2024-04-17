/**
 * <pre>
 * Date         Company         Name           Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 01/23/2013   Fujitsu         M.Yaguchi      Create          N/A
 *</pre>
 */
package business.servlet.ZZIL0100;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.ZZIL0100.ZZIL0100CMsg;
import business.servlet.ZZIL0100.common.ZZIL0100CommonLogic;
import business.servlet.ZZIL0100.constant.ZZIL0100Constant;

import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.common.sort.S21SortColumnIMGController;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class ZZIL0100Scrn00_Search extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        ZZIL0100CommonLogic.checkCommonInput(scrnMsg);

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        String regDtFrom = scrnMsg.xxFromDt_RF.getValue();
        String regDtTo = scrnMsg.xxToDt_RT.getValue();
        String updDtFrom = scrnMsg.xxFromDt_UF.getValue();
        String updDtTo = scrnMsg.xxToDt_UT.getValue();

        String regTmFrom = scrnMsg.xxHrs_R1.getValue();
        String regTmTo = scrnMsg.xxHrs_R2.getValue();
        String updTmFrom = scrnMsg.xxHrs_U1.getValue();
        String updTmTo = scrnMsg.xxHrs_U2.getValue();

        // single category check
        if (regDtFrom.length() > 0 && regTmFrom.length() == 0) {
            scrnMsg.xxHrs_R1.setErrorInfo(1, "ZZM9032E", new String[] {"Register Time From" });
        } else if (regDtFrom.length() == 0 && regTmFrom.length() > 0) {
            scrnMsg.xxFromDt_RF.setErrorInfo(1, "ZZM9032E", new String[] {"Register Date From" });
        }

        if (regDtTo.length() > 0 && regTmTo.length() == 0) {
            scrnMsg.xxHrs_R2.setErrorInfo(1, "ZZM9032E", new String[] {"Register Time To" });
        } else if (regDtTo.length() == 0 && regTmTo.length() > 0) {
            scrnMsg.xxToDt_RT.setErrorInfo(1, "ZZM9032E", new String[] {"Register Date To" });
        }

        if (updDtFrom.length() > 0 && updTmFrom.length() == 0) {
            scrnMsg.xxHrs_U1.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Time From" });
        } else if (updDtFrom.length() == 0 && updTmFrom.length() > 0) {
            scrnMsg.xxFromDt_UF.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Date From" });
        }

        if (updDtTo.length() > 0 && updTmTo.length() == 0) {
            scrnMsg.xxHrs_U2.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Time To" });
        } else if (updDtTo.length() == 0 && updTmTo.length() > 0) {
            scrnMsg.xxToDt_UT.setErrorInfo(1, "ZZM9032E", new String[] {"Updated Date To" });
        }

        // correlative check
        String strFrom = regDtFrom + regTmFrom;
        String strTo = regDtTo + regTmTo;

        if (regDtTo.length() > 0 && regTmTo.length() > 0) {
            int comp = strFrom.compareTo(strTo);

            if (comp > 0) {
                scrnMsg.xxToDt_RT.setErrorInfo(1, "ZZZM9010E", new String[] {"Register Date From", "Register Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_R2.setErrorInfo(1, "ZZZM9010E", new String[] {"Register Time From", "Register Time To" });
            }
        }

        strFrom = updDtFrom + updTmFrom;
        strTo = updDtTo + updTmTo;

        if (updDtTo.length() > 0 && updTmTo.length() > 0) {

            int comp = strFrom.compareTo(strTo);
            if (comp > 0) {
                scrnMsg.xxToDt_UT.setErrorInfo(1, "ZZZM9010E", new String[] {"Updated Date From", "Updated Date To" });
            } else if (comp == 0) {
                scrnMsg.xxHrs_U2.setErrorInfo(1, "ZZZM9010E", new String[] {"Updated Time From", "Updated Time To" });
            }
        }

        boolean errFlg = false;
        errFlg |= scrnMsg.xxHrs_R1.isError();
        errFlg |= scrnMsg.xxHrs_R2.isError();
        errFlg |= scrnMsg.xxHrs_U1.isError();
        errFlg |= scrnMsg.xxHrs_U2.isError();
        errFlg |= scrnMsg.xxFromDt_RF.isError();
        errFlg |= scrnMsg.xxToDt_RT.isError();
        errFlg |= scrnMsg.xxFromDt_UF.isError();
        errFlg |= scrnMsg.xxToDt_UT.isError();

        // set values to items of pagenation for next page pagenation
        ZYPTableUtil.clear(scrnMsg.A);
        scrnMsg.xxPageShowFromNum.clear();
        scrnMsg.xxPageShowToNum.clear();
        scrnMsg.xxPageShowOfNum.clear();

        if (errFlg) {
            return null;
        }

        ZZIL0100CMsg bizMsg = new ZZIL0100CMsg();
        bizMsg.setBusinessID(ZZIL0100Constant.BUSINESS_ID);
        bizMsg.setFunctionCode(ZZIL0100Constant.ReadCode);
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        ZZIL0100BMsg scrnMsg = (ZZIL0100BMsg) bMsg;
        ZZIL0100CMsg bizMsg = (ZZIL0100CMsg) cMsg;

        if (bizMsg != null) {
            EZDMsg.copy(bizMsg, null, scrnMsg, null);
        }

        // clear image file of sort columns (Gif file. ASC or DESC.)
        S21SortColumnIMGController.clearIMG("ZZIL0100Scrn00", scrnMsg, scrnMsg.A.no(0).getBaseContents());

        ZZIL0100CommonLogic.setTableColor(scrnMsg, bizMsg);
        ZZIL0100CommonLogic.checkCommonInput(scrnMsg);

        if ( scrnMsg.A.getValidCount() > 0) {
            ZZIL0100CommonLogic.setSubmitButton(this);
        }

        scrnMsg.setFocusItem(scrnMsg.itrlIntfcId);

    }

}

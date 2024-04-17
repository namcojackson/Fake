/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NMAL0200;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.ZYP.constant.cdtable.MDSE_STRU_ELMNT_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * Business ID : NMAL0200 Product Categorization Maintenance
 * Function Name : OnChange_StruElmntType
 * </pre>
 * 
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 07/07/2016   CITS            K.Ogino         Create          N/A
 *</pre>
 */
public class NMAL0200Scrn00_OnChange_StruElmntType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // no process.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        NMAL0200BMsg scrnMsg = (NMAL0200BMsg) bMsg;

        int idx = getButtonSelectNumber();

        if (MDSE_STRU_ELMNT_TP.PRODUCT_LEVEL_3.equals(scrnMsg.A.no(idx).mdseStruElmntTpCd_A1.getValue())) {
            scrnMsg.A.no(idx).scdProdHrchCd_A1.setInputProtected(false);
            scrnMsg.A.no(idx).xxLinkAncr_A1.setInputProtected(false);
        } else {
            scrnMsg.A.no(idx).scdProdHrchCd_A1.clear();
            scrnMsg.A.no(idx).xxDplyByCtrlItemCdNm_A1.clear();
            scrnMsg.A.no(idx).scdProdHrchCd_A1.setInputProtected(true);
            scrnMsg.A.no(idx).xxLinkAncr_A1.setInputProtected(true);
        }

        scrnMsg.setFocusItem(scrnMsg.A.no(idx).mdseStruElmntTpCd_A1);
    }
}

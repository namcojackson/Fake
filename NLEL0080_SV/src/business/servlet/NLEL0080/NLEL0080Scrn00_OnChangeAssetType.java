/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NLEL0080;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NLEL0080.common.NLEL0080CommonLogic;

import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.ASSET_SRC_TP;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/09/13   Hitachi         J.Kim           Create          N/A
 *</pre>
 */
public class NLEL0080Scrn00_OnChangeAssetType extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NLEL0080BMsg scrnMsg = (NLEL0080BMsg) bMsg;

        if (ASSET_SRC_TP.PROCURED.equals(scrnMsg.assetSrcTpCd_S.getValue())) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrDply, ASSET_SRC_TP.PROCURED);
            ZYPTableUtil.clear(scrnMsg.A);
        } else {
            ZYPEZDItemValueSetter.setValue(scrnMsg.xxScrDply, ASSET_SRC_TP.LEASED);
            ZYPTableUtil.clear(scrnMsg.A);
        }
        NLEL0080CommonLogic.initialize(this, scrnMsg);
    }
}

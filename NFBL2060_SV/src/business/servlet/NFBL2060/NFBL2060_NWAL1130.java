/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFBL2060;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFBL2060.constant.NFBL2060Constant;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/12/14   CUSA            Y.Aikawa        Create          N/A
 * 2016/07/21   Hitachi         T.Tsuchida      Update          QC#12116
 * 2016/07/22   Hitachi         Y.Tsuchimoto    Update          QC#12008
 * 2016/07/25   Hitachi         Y.Tsuchimoto    Update          QC#11999
 * 2016/07/26   Hitachi         T.Tsuchida      Update          QC#12239
 * 2016/07/27   Hitachi         T.Tsuchida      Update          QC#12002
 * 2016/09/30   Hitachi         T.Tsuchida      Update          QC#14498
 * 2016/10/03   Hitachi         T.Tsuchida      Update          QC#13414
 * 2016/10/24   Hitachi         Y.Tsuchimoto    Update          QC#15493
 * 2018/05/28   CITS            K.Ogino         Update          QC#25902
 *</pre>
 */
public class NFBL2060_NWAL1130 extends S21CommonHandler implements NFBL2060Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return null;
        }

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (ONCLICK_TERM_LINK.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtTermDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if (ONCLICK_PAYMENT_METHOD_LINK.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndPmtMethDescTxt, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue());
        } else if (OPENWIN_SUPPLIER.equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.prntVndCd, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt);
            String dplyVndNm = scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue();
            if (ZYPCommonFunc.hasValue(dplyVndNm)
                    && ZYPCommonFunc.hasValue(scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue())) {
                dplyVndNm = scrnMsg.Z.no(IDX_1).xxComnScrColValTxt.getValue(); // + DIV_DPLY_VND_NM + scrnMsg.Z.no(IDX_3).xxComnScrColValTxt.getValue();
            }
            ZYPEZDItemValueSetter.setValue(scrnMsg.dplyVndNm, dplyVndNm);
            ZYPEZDItemValueSetter.setValue(scrnMsg.apVndCd, scrnMsg.Z.no(IDX_2).xxComnScrColValTxt);
        } else if (OPENWIN_PURCHASE_ORDER.equals(scrEventNm)) {
            // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
            ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(IDX_0).xxComnScrColValTxt);
            // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        } else if (OPENWIN_RECEIPT.equals(scrEventNm)) {
            // QC#25902
            if ("PO".equals(scrnMsg.Z.no(NFBL2060Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2060Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.vndRtrnNum.clear();
            } else if ("Vendor Return".equals(scrnMsg.Z.no(NFBL2060Constant.IDX_2).xxComnScrColValTxt.getValue())) {
                ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum, scrnMsg.Z.no(NFBL2060Constant.IDX_0).xxComnScrColValTxt);
                scrnMsg.poNum.clear();
            } else {
                ZYPEZDItemValueSetter.setValue(scrnMsg.poNum, scrnMsg.Z.no(NFBL2060Constant.IDX_0).xxComnScrColValTxt);
            }
            // QC#25902 End
            ZYPEZDItemValueSetter.setValue(scrnMsg.delyOrdNum, scrnMsg.Z.no(IDX_1).xxComnScrColValTxt);
        } else if ("OpenWin_VndRtrnOrder".equals(scrEventNm)) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.vndRtrnNum, scrnMsg.Z.no(NFBL2060Constant.IDX_0).xxComnScrColValTxt);
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        if ("CMN_Close".equals(getLastGuard())) {
            return;
        }

        NFBL2060BMsg scrnMsg = (NFBL2060BMsg) bMsg;
        String scrEventNm = scrnMsg.xxScrEventNm.getValue();

        if (ONCLICK_TERM_LINK.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndPmtTermDescTxt);
        } else if (ONCLICK_PAYMENT_METHOD_LINK.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndPmtMethDescTxt);
        } else if (OPENWIN_SUPPLIER.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.apVndCd);
        } else if (OPENWIN_PURCHASE_ORDER.equals(scrEventNm)) {
            // START 2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
            scrnMsg.setFocusItem(scrnMsg.poNum);
            // END   2016/10/24 Y.Tsuchimoto [QC#15493,MOD]
        } else if (OPENWIN_RECEIPT.equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.delyOrdNum);
        } else if ("OpenWin_VndRtrnOrder".equals(scrEventNm)) {
            scrnMsg.setFocusItem(scrnMsg.vndRtrnNum);
        }
    }
}

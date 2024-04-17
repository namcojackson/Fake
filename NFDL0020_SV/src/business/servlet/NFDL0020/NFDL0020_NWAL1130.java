/*
 * <pre>Copyright (c) 2013 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NFDL0020;

import static business.servlet.NFDL0020.constant.NFDL0020Constant.NFDM0057E;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.servletcommon.EZDApplicationContext;
import business.servlet.NFDL0020.common.NFDL0020CommonLogic;
import business.servlet.NFDL0020.constant.NFDL0020Constant.DUN_LTR_WRK_ITEM_CD;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_ITEM_STS;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CLT_WRK_TP;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2013/03/26   CUSA            Y.Miyauchi      Create          N/A
 * 2016/06/16   Hitachi         K.Kojima        Update          QC#10200
 * 2016/07/07   Hitachi         K.Kojima        Update          QC#10337
 * 2018/05/11   Hitachi         J.Kim           Update          QC#21426
 * 2021/09/17   CITS            G.Delgado       Update          QC#59070
 *</pre>
 */
public class NFDL0020_NWAL1130 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        //NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NFDL0020BMsg scrnMsg = (NFDL0020BMsg) bMsg;

        String scrEventNm = scrnMsg.xxScrEventNm.getValue();
        if (!"CMN_Close".equals(getLastGuard())) {
            if ("Click_TaskOwner".equals(scrEventNm)) {
                if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(0).xxComnScrColValTxt.getValue())) {
                     scrnMsg.cltTaskOwnrId_GH.setValue(scrnMsg.Q.no(0).xxComnScrColValTxt.getValue());
                }
                if (ZYPCommonFunc.hasValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue())) {
                    // START 2016/06/16 K.Kojima [QC#10200,MOD]
                    // scrnMsg.xxPsnNm_G1.setValue(scrnMsg.Q.no(1).xxComnScrQueryColNm.getValue());
                    // START 2016/07/07 K.Kojima [QC#10337,MOD]
                    // scrnMsg.xxPsnNm_G1.setValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue());
                    scrnMsg.cltPsnNm_G1.setValue(scrnMsg.Q.no(1).xxComnScrColValTxt.getValue());
                    // END 2016/07/07 K.Kojima [QC#10337,MOD]
                    // END 2016/06/16 K.Kojima [QC#10200,MOD]
               }
                scrnMsg.setFocusItem(scrnMsg.cltTaskOwnrId_GH);
            // START 2018/05/11 J.Kim [QC#21426,ADD]
            } else {

                String cltWrkItemCd = scrnMsg.Q.no(0).xxComnScrColValTxt.getValue();
                String cltWrkItemNm = scrnMsg.Q.no(1).xxComnScrColValTxt.getValue();
                String cltWrkTpNm = scrnMsg.Q.no(2).xxComnScrColValTxt.getValue();

                // START 2021/09/17 G.Delgado [QC#59070,ADD]
                // Check if dunning letter work item
                DUN_LTR_WRK_ITEM_CD dunLtrWrkItemEnum = DUN_LTR_WRK_ITEM_CD.fromValue(cltWrkItemCd);
                // END 2021/09/17 G.Delgado [QC#59070,ADD]

                int row = 0;
                scrnMsg.E.clear();
                boolean openflg = false;
                for (int idx = 0; idx < scrnMsg.D.getValidCount(); idx++) {
                    NFDL0020_DBMsg oldDMsg = scrnMsg.D.no(idx);
                    NFDL0020_EBMsg newCMsg = scrnMsg.E.no(row);

                    // START 2021/09/17 G.Delgado [QC#59070,ADD]
                    // Check if same dunning letter work item
                    if (dunLtrWrkItemEnum != null && dunLtrWrkItemEnum.getValues().contains(oldDMsg.cltWrkItemCd_DD.getValue())) {
                        String cltWrkItemStsCd = oldDMsg.cltWrkItemStsCd_DD.getValue();
                        if (CLT_WRK_ITEM_STS.PENDING.equals(cltWrkItemStsCd) || CLT_WRK_ITEM_STS.OPEN.equals(cltWrkItemStsCd)) {
                            // Error if Pending or Open work item already exists
                            scrnMsg.setMessageInfo(NFDM0057E);
                            throw new EZDFieldErrorException();
                        }
                    }
                    // END 2021/09/17 G.Delgado [QC#59070,ADD]

                    if (CLT_WRK_ITEM_STS.OPEN.equals(oldDMsg.cltWrkItemStsCd_DD.getValue()) && !openflg) {
                        openflg = true;
                        copyMsg(scrnMsg.D.no(idx), scrnMsg.E.no(row));
                        row = row + 1;
                        setAddLine(scrnMsg.E.no(row), cltWrkItemCd, cltWrkItemNm, cltWrkTpNm, scrnMsg.D.no(idx));

                    } else if (CLT_WRK_ITEM_STS.PENDING.equals(oldDMsg.cltWrkItemStsCd_DD.getValue()) && !openflg) {
                        openflg = true;
                        setAddLine(newCMsg, cltWrkItemCd, cltWrkItemNm, cltWrkTpNm, scrnMsg.D.no(idx));
                        row = row + 1;
                        copyMsg(scrnMsg.D.no(idx), scrnMsg.E.no(row));
                    } else {
                        copyMsg(scrnMsg.D.no(idx), scrnMsg.E.no(row));
                    }
                    row++;
                }

                // START 2021/09/17 G.Delgado [QC#59070,ADD]
                if (!openflg && row < scrnMsg.E.length()) {
                    // Add new work item to end of list, if not yet added
                    setAddLine(scrnMsg.E.no(row), cltWrkItemCd, cltWrkItemNm, cltWrkTpNm, scrnMsg.D.no(row - 1));
                    row++;
                }
                // END 2021/09/17 G.Delgado [QC#59070,ADD]

                scrnMsg.E.setValidCount(row);
                copyMsg(scrnMsg);
                NFDL0020CommonLogic.initialize(this, scrnMsg);
                scrnMsg.setFocusItem(scrnMsg.cltTaskOwnrId_GH);
            }
            // END 2018/05/11 J.Kim [QC#21426,ADD]
        }
    }

    // START 2018/05/11 J.Kim [QC#21426,ADD]
    private void setAddLine(NFDL0020_EBMsg newCMsg, String cltWrkItemCd, String cltWrkItemNm, String cltWrkTpNm, NFDL0020_DBMsg dMsg) {

        newCMsg.cltWrkItemStsCd_E.setValue(CLT_WRK_ITEM_STS.PENDING);
        newCMsg.cltStrgyTrxPk_E.setValue(dMsg.cltStrgyTrxPk_DD.getValue());
        newCMsg.cltWrkItemCd_E.setValue(cltWrkItemCd);
        newCMsg.cltWrkItemNm_E.setValue(cltWrkItemNm);
        newCMsg.cltWrkTpCd_E.setValue(CLT_WRK_TP.MANUAL);
        newCMsg.cltWrkTpNm_E.setValue(cltWrkTpNm);
        newCMsg.billToCustCd_E.setValue(dMsg.billToCustCd_DD.getValue());
        newCMsg.cltPsnNm_E.clear();
        newCMsg.cltWrkItemRwsdDt_E.clear();
        newCMsg.cltWrkItemRwedDt_E.clear();
        newCMsg.cltWrkItemWsrdDt_E.clear();
        newCMsg.cltWrkItemWerdDt_E.clear();
    }

    private void copyMsg(NFDL0020_DBMsg oldBMsg, NFDL0020_EBMsg newBMsg) {

        ZYPEZDItemValueSetter.setValue(newBMsg.cltStrgyWrkItemTrxPk_E, oldBMsg.cltStrgyWrkItemTrxPk_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemNm_E, oldBMsg.cltWrkItemNm_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemNm_EL, oldBMsg.cltWrkItemNm_LK);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemCd_E, oldBMsg.cltWrkItemCd_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemStsCd_E, oldBMsg.cltWrkItemStsCd_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkTpCd_E, oldBMsg.cltWrkTpCd_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkTpNm_E, oldBMsg.cltWrkTpNm_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltPsnNm_E, oldBMsg.cltPsnNm_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.billToCustCd_E, oldBMsg.billToCustCd_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltStrgyTrxPk_E, oldBMsg.cltStrgyTrxPk_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemRwsdDt_E, oldBMsg.cltWrkItemRwsdDt_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemRwedDt_E, oldBMsg.cltWrkItemRwedDt_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemWsrdDt_E, oldBMsg.cltWrkItemWsrdDt_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemWerdDt_E, oldBMsg.cltWrkItemWerdDt_DD);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemStsCd_E1, oldBMsg.cltWrkItemStsCd_DB);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemRwsdDt_E1, oldBMsg.cltWrkItemRwsdDt_DB);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemRwedDt_E1, oldBMsg.cltWrkItemRwedDt_DB);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemWsrdDt_E1, oldBMsg.cltWrkItemWsrdDt_DB);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltWrkItemWerdDt_E1, oldBMsg.cltWrkItemWerdDt_DB);
        ZYPEZDItemValueSetter.setValue(newBMsg.cltDunLtrRqstNum_E, oldBMsg.cltDunLtrRqstNum_DD);

    }

    private void copyMsg(NFDL0020BMsg scrnMsg) {

        scrnMsg.D.clear();
        for (int index = 0; index < scrnMsg.E.getValidCount(); index++) {
            NFDL0020_EBMsg eMsg = scrnMsg.E.no(index);
            NFDL0020_DBMsg dMsg = scrnMsg.D.no(index);
            ZYPEZDItemValueSetter.setValue(dMsg.cltStrgyWrkItemTrxPk_DD, eMsg.cltStrgyWrkItemTrxPk_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemNm_DD, eMsg.cltWrkItemNm_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemNm_LK, eMsg.cltWrkItemNm_EL);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemCd_DD, eMsg.cltWrkItemCd_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemStsCd_DD, eMsg.cltWrkItemStsCd_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkTpCd_DD, eMsg.cltWrkTpCd_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkTpNm_DD, eMsg.cltWrkTpNm_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltPsnNm_DD, eMsg.cltPsnNm_E);
            ZYPEZDItemValueSetter.setValue(dMsg.billToCustCd_DD, eMsg.billToCustCd_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltStrgyTrxPk_DD, eMsg.cltStrgyTrxPk_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemRwsdDt_DD, eMsg.cltWrkItemRwsdDt_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemRwedDt_DD, eMsg.cltWrkItemRwedDt_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemWsrdDt_DD, eMsg.cltWrkItemWsrdDt_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemWerdDt_DD, eMsg.cltWrkItemWerdDt_E);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemStsCd_DB, eMsg.cltWrkItemStsCd_E1);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemRwsdDt_DB, eMsg.cltWrkItemRwsdDt_E1);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemRwedDt_DB, eMsg.cltWrkItemRwedDt_E1);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemWsrdDt_DB, eMsg.cltWrkItemWsrdDt_E1);
            ZYPEZDItemValueSetter.setValue(dMsg.cltWrkItemWerdDt_DB, eMsg.cltWrkItemWerdDt_E1);
            ZYPEZDItemValueSetter.setValue(dMsg.cltDunLtrRqstNum_DD, eMsg.cltDunLtrRqstNum_E);
        }
        scrnMsg.D.setValidCount(scrnMsg.E.getValidCount());
    }
    // END 2018/05/11 J.Kim [QC#21426,ADD]
}

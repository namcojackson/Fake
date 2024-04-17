/*
 * <pre>Copyright (c) 2016 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NWAL2200;

import static business.servlet.NWAL2200.constant.NWAL2200Constant.BIZ_ID;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0666E;
import static business.servlet.NWAL2200.constant.NWAL2200Constant.NWAM0667E;

import java.util.ArrayList;
import java.util.List;

import parts.common.EZDBItem;
import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;

import business.blap.NWAL2200.NWAL2200CMsg;
import business.servlet.NWAL2200.common.NWAL2200CommonLogicForScreenFields;

import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.web.ZYPTableUtil;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 *<pre>
 * NWAL2200Scrn00_OpenWin_Buyout
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/01/22   Fujitsu         T.Ishii         Create          N/A
 * 2017/06/27   Fujitsu         H.Sugawara      Update          QC#18363
 * 2019/10/04   Fujitsu         K.Kato          Update          S21_NA#51372
 *</pre>
 */
public class NWAL2200Scrn00_OpenWin_Buyout extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;

        NWAL2200CommonLogicForScreenFields.addCheckItem(scrnMsg, false, scrnMsg.xxDplyTab.getValue());
        scrnMsg.putErrorScreen();

        List<Integer> checkList = ZYPTableUtil.getSelectedRows(scrnMsg.B, "xxChkBox_LL", ZYPConstant.FLG_ON_Y);

        if (checkList.size() == 1) {
            scrnMsg.xxCellIdx.setValue(checkList.get(0));
        } else {
            if (checkList.size() == 0) {
                scrnMsg.setMessageInfo(NWAM0667E, new String[] {"Component Line" });
            } else if (checkList.size() > 1) {
                scrnMsg.setMessageInfo(NWAM0666E, new String[] {"Component Line" });
            }
            throw new EZDFieldErrorException();
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        ZYPTableUtil.clear(scrnMsg.P);

        NWAL2200CMsg bizMsg = new NWAL2200CMsg();
        bizMsg.setBusinessID(BIZ_ID);
        bizMsg.setFunctionCode("20");
        EZDMsg.copy(scrnMsg, null, bizMsg, null);

        return bizMsg;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL2200BMsg scrnMsg = (NWAL2200BMsg) bMsg;
        NWAL2200CMsg bizMsg = (NWAL2200CMsg) cMsg;
        EZDMsg.copy(bizMsg, null, scrnMsg, null);

        Object[] params = getParamNWAL1640(scrnMsg);
        setArgForSubScreen(params);
    }

    /**
     * Get Param NWAL1640
     * @param scrnMsg NWAL2200BMsg
     * @return Param NWAL1640
     */
    private static Object[] getParamNWAL1640(NWAL2200BMsg scrnMsg) {

        NWAL2200_BBMsg lineMsg = scrnMsg.B.no(scrnMsg.xxCellIdx.getValueInt());

        List<EZDBItem> paramList = new ArrayList<EZDBItem>();
        paramList.add(scrnMsg.cpoOrdNum);
        paramList.add(lineMsg.dsOrdPosnNum_LL);
        paramList.add(lineMsg.dsCpoLineNum_LL);
        paramList.add(lineMsg.dsCpoLineSubNum_LL);
        paramList.add(scrnMsg.sellToCustCd);
        paramList.add(scrnMsg.soldToCustLocCd);
        // 2019/10/04 S21_NA#51372 Add Start
        String sellToCustAcctNm = scrnMsg.P.no(0).xxPopPrm.getValue();
        if (sellToCustAcctNm.length() >  lineMsg.getAttr("prntVndNm_LL").getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(0).xxPopPrm, sellToCustAcctNm.substring(0, lineMsg.getAttr("prntVndNm_LL").getDigit()));
        }
        // 2019/10/04 S21_NA#51372 Add End
        paramList.add(scrnMsg.P.no(0).xxPopPrm); // SELL_TO_CUST_LOC_NM
        paramList.add(scrnMsg.P.no(1).xxPopPrm); // SELL_TO_CUST_FIRST_LINE_ADDR
        paramList.add(scrnMsg.P.no(2).xxPopPrm); // SELL_TO_CUST_CTY_ADDR
        paramList.add(scrnMsg.P.no(3).xxPopPrm); // SELL_TO_CUST_ST_CD
        paramList.add(scrnMsg.P.no(4).xxPopPrm); // SELL_TO_CUST_POST_CD
        paramList.add(scrnMsg.P.no(5).xxPopPrm); // Bill To Account
        // Number(Config)
        paramList.add(scrnMsg.P.no(11).xxPopPrm); // Bill To Location
        // Number(Config)
        // 2019/10/04 S21_NA#51372 Mod Start
        String billToCustLocNm = scrnMsg.P.no(6).xxPopPrm.getValue();
        if (billToCustLocNm.length() >  lineMsg.getAttr("prntVndNm_LL").getDigit()) {
            ZYPEZDItemValueSetter.setValue(scrnMsg.P.no(6).xxPopPrm, billToCustLocNm.substring(0, lineMsg.getAttr("prntVndNm_LL").getDigit()));
        }
        // 2019/10/04 S21_NA#51372 Add End
        paramList.add(scrnMsg.P.no(6).xxPopPrm); // BILL_TO_CUST_LOC_NM
        paramList.add(scrnMsg.P.no(7).xxPopPrm); // BILL_TO_CUST_FIRST_LINE_ADDR
        paramList.add(scrnMsg.P.no(8).xxPopPrm); // BILL_TO_CUST_CTY_ADDR
        paramList.add(scrnMsg.P.no(9).xxPopPrm); // BILL_TO_CUST_ST_CD
        paramList.add(scrnMsg.P.no(10).xxPopPrm); // BILL_TO_CUST_POST_CD
        paramList.add(lineMsg.splyTpCd_LL);
        // 2019/10/04 S21_NA#51372 Mod Start
        //paramList.add(lineMsg.splyNm_LL);
        paramList.add(lineMsg.prntVndNm_LL);
        // 2019/10/04 S21_NA#51372 Mod End
        paramList.add(lineMsg.splyFirstAddr_LL);
        paramList.add(lineMsg.splyCtyAddr_LL);
        paramList.add(lineMsg.splyStCd_LL);
        paramList.add(lineMsg.splyPostCd_LL);
        // Add Start 2017/06/27 QC#18363
        paramList.add(scrnMsg.xxModeCd_N);
        // Add End 2017/06/27 QC#18363

        return paramList.toArray(new EZDBItem[0]);
    }
}

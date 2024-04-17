/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   CUSA            Fujitsu         Update          N/A
 * 04/23/2010   Fujitsu         K.Tajima        Update          4202
 * 06/14/2010   Fujitsu         S.Yamamoto      Update          7096
 *</pre>
 */
package business.servlet.NWAL0140;

import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;

import java.io.Serializable;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.common.EZDFieldErrorException;
import parts.common.EZDMessageInfo;
import parts.common.EZDMsg;
import parts.servletcommon.EZDApplicationContext;
import business.blap.NWAL0140.NWAL0140CMsg;
import business.servlet.NWAL0140.constant.NWAL0140Constant;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

public class NWAL0140Scrn00_CMN_Close extends S21CommonHandler implements NWAL0140Constant {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        // Read Only Flg is read only "N"
        if (FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg.getValue())) {

            if (!scrnMsg.locNm.isInputProtected()) {

                // Ship To 
                if (!hasValue(scrnMsg.shipToCustCd)) {
                    scrnMsg.setMessageInfo("NWAM0021E");
                    throw new EZDFieldErrorException();
                }
                
                scrnMsg.addCheckItem(scrnMsg.locNm);            // Loc Nm
                scrnMsg.addCheckItem(scrnMsg.addlLocNm);        // Add Loc Nm
                scrnMsg.addCheckItem(scrnMsg.firstRefCmntTxt);  // Ship To Txt1
                scrnMsg.addCheckItem(scrnMsg.scdRefCmntTxt);    // ip To Txt2 
                scrnMsg.addCheckItem(scrnMsg.firstLineAddr);    // Addr Ln1 
                scrnMsg.addCheckItem(scrnMsg.scdLineAddr);      // Addr Ln2
                scrnMsg.addCheckItem(scrnMsg.thirdLineAddr);    // Addr Ln3
                scrnMsg.addCheckItem(scrnMsg.frthLineAddr);     // Addr Ln4
                scrnMsg.addCheckItem(scrnMsg.postCd);           // Post Code
                scrnMsg.addCheckItem(scrnMsg.ctyAddr);          // City
                scrnMsg.addCheckItem(scrnMsg.cntyNm);           // County
                scrnMsg.addCheckItem(scrnMsg.ctryCd);           // Country

                if ("US".equals(scrnMsg.ctryCd.getValue().toUpperCase())) {
                    // State
                    final EZDBStringItem stCdItem = scrnMsg.stCd;
                    scrnMsg.addCheckItem(stCdItem);
                    if (!hasValue(stCdItem)) {
                        stCdItem.setErrorInfo(1, "ZZM9000E", new String[] {stCdItem.getNameForMessage() });
                    }
                } else {
                    // Province
                    scrnMsg.addCheckItem(scrnMsg.provNm);
                }
                
                scrnMsg.putErrorScreen();
            }
        }
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;

        // Read Only Flg is read only "N"
        if (FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg.getValue())) {

            if (!scrnMsg.locNm.isInputProtected()) {

                NWAL0140CMsg bizMsg = new NWAL0140CMsg();
                bizMsg.setBusinessID("NWAL0140");
                bizMsg.setFunctionCode("20");
                EZDMsg.copy(scrnMsg, null, bizMsg, null);

                return bizMsg;
            }
        }

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NWAL0140BMsg scrnMsg = (NWAL0140BMsg) bMsg;
        NWAL0140CMsg bizMsg = (NWAL0140CMsg) cMsg;

        // Read Only Flg is read only "N"
        if (FLG_OFF_N.equals(scrnMsg.xxReadOnlyFlg.getValue())) {

            if (!scrnMsg.locNm.isInputProtected()) {

                EZDMsg.copy(bizMsg, null, scrnMsg, null);

                scrnMsg.addCheckItem(scrnMsg.firstLineAddr);
                scrnMsg.addCheckItem(scrnMsg.scdLineAddr);
                scrnMsg.addCheckItem(scrnMsg.ctyAddr);
                scrnMsg.addCheckItem(scrnMsg.postCd);
                scrnMsg.addCheckItem(scrnMsg.stCd);
                scrnMsg.addCheckItem(scrnMsg.ctryCd);
                scrnMsg.addCheckItem(scrnMsg.cntyNm);

                scrnMsg.putErrorScreen();
                if (EZDMessageInfo.MSGTYPE_ERROR == scrnMsg.getMessageType()) {
                    throw new EZDFieldErrorException();
                }

                if (EZDMessageInfo.MSGTYPE_WARNING == scrnMsg.getMessageType()) {
                    throw new EZDFieldErrorException();
                }

                // set Result Data
                Serializable arg = getArgForSubScreen();
                if (arg instanceof Object[]) {
                    Object[] params = (Object[]) arg;
                    setResult(params, scrnMsg);
                }
            }
        }
    }

    private static void setResult(Object[] params, NWAL0140BMsg scrnMsg) {

        // [1] : Ship To
        ((EZDBStringItem) params[1]).setValue(scrnMsg.shipToCustCd.getValue());

        // [2] : Ship To Name
        ((EZDBStringItem) params[2]).setValue(scrnMsg.locNm.getValue());

        // [3] :Ship To Additional Name
        ((EZDBStringItem) params[3]).setValue(scrnMsg.addlLocNm.getValue());

        // [4] :Address Line 1
        ((EZDBStringItem) params[4]).setValue(scrnMsg.firstLineAddr.getValue());

        // [5] :Address Line 2
        ((EZDBStringItem) params[5]).setValue(scrnMsg.scdLineAddr.getValue());

        // [6] :Address Line 3
        ((EZDBStringItem) params[6]).setValue(scrnMsg.thirdLineAddr.getValue());

        // [7] : Address Line 4
        ((EZDBStringItem) params[7]).setValue(scrnMsg.frthLineAddr.getValue());

        // [8] : Ship To First Ref Cmnt Txt
        ((EZDBStringItem) params[8]).setValue(scrnMsg.firstRefCmntTxt.getValue());

        // [9] : Ship To Scd Ref Cmnt Txt
        ((EZDBStringItem) params[9]).setValue(scrnMsg.scdRefCmntTxt.getValue());

        // [10] : City
        ((EZDBStringItem) params[10]).setValue(scrnMsg.ctyAddr.getValue());

        // [11] : State
        ((EZDBStringItem) params[11]).setValue(scrnMsg.stCd.getValue());

        // [12] : Post Code
        ((EZDBStringItem) params[12]).setValue(scrnMsg.postCd.getValue());

        // [13] : Country
        ((EZDBStringItem) params[13]).setValue(scrnMsg.ctryCd.getValue());

        // [14] : County
        ((EZDBStringItem) params[14]).setValue(scrnMsg.cntyNm.getValue());

        // [15] : DropShipFlg
        ((EZDBStringItem) params[15]).setValue(scrnMsg.dropShipFlg.getValue());

        if (hasValue(scrnMsg.xxDtlLineNum)) {
            // [0] : Line Number
            ((EZDBStringItem) params[0]).setValue(scrnMsg.xxDtlLineNum.getValue());

        } else {
            // [17] : Bill To Code
            ((EZDBStringItem) params[17]).setValue(scrnMsg.billToCustCd_BK.getValue());

            // [18] : Bill To Name
            ((EZDBStringItem) params[18]).setValue(scrnMsg.billToLocNm_BK.getValue());

            // [19] : Sell To Code
            ((EZDBStringItem) params[19]).setValue(scrnMsg.sellToCustCd_BK.getValue());

            // [20] : Sell To Name
            ((EZDBStringItem) params[20]).setValue(scrnMsg.sellToLocNm_BK.getValue());
        }

        // [21] : Province
        if (params.length > 21) {
            ((EZDBStringItem) params[21]).setValue(scrnMsg.provNm.getValue());
        }
    }

}

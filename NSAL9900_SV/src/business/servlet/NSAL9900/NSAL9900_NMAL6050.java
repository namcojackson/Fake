/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import parts.common.EZDBMsg;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

// START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
import java.lang.reflect.Field;
import java.math.BigDecimal;
import parts.common.EZDBStringItem;
import parts.common.EZDBBigDecimalItem;
import business.servlet.NSAL9900.constant.NSAL9900Constant;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;

// END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]

/**
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 * 2017/07/11   Hitachi         M.Kidokoro      Update          18659,19534
 * 2017/07/21   Hitachi         K.Kojima        Update          QC#20054
 *</pre>
 */
public class NSAL9900_NMAL6050 extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // do nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {
        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {
        // START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
        if (NSAL9900Constant.EVENT_CMN_CLOSE.equals(ctx.getEventName())) {
            return;
        }

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;

        int selectIndex = getButtonSelectNumber();

        String callItemName = scrnMsg.xxPopPrm_NM.getValue();
        String colNum = callItemName.substring(callItemName.length() - 2);

        // START 2017/07/21 K.Kojima [QC#19822,ADD]
        boolean isSearchItem = false;
        if ("_A".equals(colNum)) {
            isSearchItem = true;
        }
        // END 2017/07/21 K.Kojima [QC#19822,ADD]

        // START 2017/07/21 K.Kojima [QC#20054,MOD]
        // String colStringName = "xxString_" + colNum;
        // String colNumberName = "xxNumber_" + colNum;
        String colStringName = "xxString_" + colNum;
        String colNumberName = "xxNumber_" + colNum;
        if (isSearchItem) {
            colStringName = "xxString" + colNum;
            colNumberName = "xxNumber" + colNum;
        } else {
            colStringName = "xxString_" + colNum;
            colNumberName = "xxNumber_" + colNum;
        }
        // END 2017/07/21 K.Kojima [QC#20054,MOD]

        // START 2017/07/21 K.Kojima [QC#20054,MOD]
        // int cIndex = Integer.parseInt(colNum);
        int cIndex = 0;
        if (isSearchItem) {
            cIndex = scrnMsg.A.no(selectIndex).xxRowNum_A.getValueInt();
        } else {
            cIndex = Integer.parseInt(colNum);
        }
        // END 2017/07/21 K.Kojima [QC#20054,MOD]

        try {
            Field f = null;
            String colTpCd = scrnMsg.C.no(cIndex).colTpCd.getValue();

            if (colTpCd.equals(NSAL9900Constant.COL_TP_NUMBER)) {
                // START 2017/07/21 K.Kojima [QC#20054,MOD]
                // f = NSAL9900_BBMsg.class.getField(colStringName);
                // EZDBStringItem colStringItem = (EZDBStringItem) f.get(scrnMsg.B.no(selectIndex));
                EZDBStringItem colStringItem = null;
                if (isSearchItem) {
                    f = NSAL9900_ABMsg.class.getField(colStringName);
                    colStringItem = (EZDBStringItem) f.get(scrnMsg.A.no(selectIndex));
                } else {
                    f = NSAL9900_BBMsg.class.getField(colStringName);
                    colStringItem = (EZDBStringItem) f.get(scrnMsg.B.no(selectIndex));
                }
                // END 2017/07/21 K.Kojima [QC#20054,MOD]
                BigDecimal colNumberItemValue = new BigDecimal(colStringItem.getValue());

                // START 2017/07/21 K.Kojima [QC#20054,MOD]
                // f = NSAL9900_BBMsg.class.getField(colNumberName);
                // EZDBBigDecimalItem colNumberItem = (EZDBBigDecimalItem) f.get(scrnMsg.B.no(selectIndex));
                EZDBBigDecimalItem colNumberItem = null;
                if (isSearchItem) {
                    f = NSAL9900_ABMsg.class.getField(colNumberName);
                    colNumberItem = (EZDBBigDecimalItem) f.get(scrnMsg.A.no(selectIndex));
                } else {
                    f = NSAL9900_BBMsg.class.getField(colNumberName);
                    colNumberItem = (EZDBBigDecimalItem) f.get(scrnMsg.B.no(selectIndex));
                }
                // END 2017/07/21 K.Kojima [QC#20054,MOD]

                ZYPEZDItemValueSetter.setValue(colNumberItem, colNumberItemValue);
                colStringItem.clear();
            }
        } catch (Exception ex) {
            throw new S21AbendException(NSAL9900Constant.MSG_ID_NSAM0219E, new String[] {ex.getCause().toString() });
        }
        // END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
    }
}

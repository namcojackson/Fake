/*
 * <pre>Copyright (c) 2015 Canon USA Inc. All rights reserved.</pre>
 */
package business.servlet.NSAL9900;

import static business.servlet.NSAL9900.constant.NSAL9900Constant.*;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter.setValue;

import java.lang.reflect.Field;

import parts.common.EZDBBigDecimalItem;
import parts.common.EZDBMsg;
//START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
import parts.common.EZDBStringItem;
//END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

//START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
//END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2015/05/28   Hitachi         T.Aoyagi        Create          N/A
 * 2017/07/11   Hitachi         M.Kidokoro      Update          18659,19534
 * 2017/07/21   Hitachi         K.Kojima        Update          QC#20054
 *</pre>
 */
public class NSAL9900Scrn00_OpenWin_CommonCodePopup extends S21CommonHandler {

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

        NSAL9900BMsg scrnMsg = (NSAL9900BMsg) bMsg;

        String callItemName = scrnMsg.xxPopPrm_NM.getValue();
        boolean isSearchItem = false;
        if ("_A".equals(callItemName.substring(callItemName.length() - 2))) {
            isSearchItem = true;
        }

        int selectIndex = getButtonSelectNumber();

        // START 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]
        if (callItemName.indexOf("Number") > 0) {
            try {
                // START 2017/07/21 K.Kojima [QC#20054,MOD]
                // Field f = NSAL9900_BBMsg.class.getField(callItemName);
                // EZDBBigDecimalItem numberItem = (EZDBBigDecimalItem) f.get(scrnMsg.B.no(selectIndex));
                Field f = null;
                EZDBBigDecimalItem numberItem = null;
                if (isSearchItem) {
                    f = NSAL9900_ABMsg.class.getField(callItemName);
                    numberItem = (EZDBBigDecimalItem) f.get(scrnMsg.A.no(selectIndex));
                } else {
                    f = NSAL9900_BBMsg.class.getField(callItemName);
                    numberItem = (EZDBBigDecimalItem) f.get(scrnMsg.B.no(selectIndex));
                }
                // END 2017/07/21 K.Kojima [QC#20054,MOD]

                callItemName = callItemName.replace("Number", "String");

                // START 2017/07/21 K.Kojima [QC#20054,MOD]
                // f = NSAL9900_BBMsg.class.getField(callItemName);
                // EZDBStringItem stringItem = (EZDBStringItem) f.get(scrnMsg.B.no(selectIndex));
                EZDBStringItem stringItem = null;
                if (isSearchItem) {
                    f = NSAL9900_ABMsg.class.getField(callItemName);
                    stringItem = (EZDBStringItem) f.get(scrnMsg.A.no(selectIndex));
                } else {
                    f = NSAL9900_BBMsg.class.getField(callItemName);
                    stringItem = (EZDBStringItem) f.get(scrnMsg.B.no(selectIndex));
                }
                // END 2017/07/21 K.Kojima [QC#20054,MOD]

                if (ZYPCommonFunc.hasValue(numberItem)) {
                    ZYPEZDItemValueSetter.setValue(stringItem, String.valueOf(numberItem.getValue()));
                } else {
                    ZYPEZDItemValueSetter.setValue(stringItem, "");
                }
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[] {ex.getCause().toString() });
            }
        }
        // END 2017/07/11 M.Kidokoro [QC#18659,19534, ADD]

        int cIndex = 0;
        if (isSearchItem) {
            cIndex = scrnMsg.A.no(selectIndex).xxRowNum_A.getValueInt();
        } else {
            String colNum = callItemName.substring(callItemName.length() - 2);
            String rowNumItemName = "xxRowNum_" + colNum;
            try {
                Field f = NSAL9900_BBMsg.class.getField(rowNumItemName);
                EZDBBigDecimalItem rowNumItem = (EZDBBigDecimalItem) f.get(scrnMsg.B.no(selectIndex));
                cIndex = rowNumItem.getValueInt();
            } catch (Exception ex) {
                throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
            }
        }

        Object[] params = new Object[IDX_11];

        setValue(scrnMsg.xxPopPrm_00, scrnMsg.C.no(cIndex).physRelnTblNm);
        setValue(scrnMsg.xxPopPrm_01, scrnMsg.C.no(cIndex).physRelnColCd);
        setValue(scrnMsg.xxPopPrm_02, scrnMsg.C.no(cIndex).physRelnColNm);
        setValue(scrnMsg.xxPopPrm_03, scrnMsg.C.no(cIndex).physRelnColCd);
        scrnMsg.xxPopPrm_04.clear();
        setValue(scrnMsg.xxPopPrm_05, scrnMsg.C.no(cIndex).logicMaintTrgtColNm);
        setValue(scrnMsg.xxPopPrm_06, scrnMsg.C.no(cIndex).logicPopupColNm);
        setValue(scrnMsg.xxPopPrm_07, scrnMsg.C.no(cIndex).logicMaintTrgtColNm);
        setValue(scrnMsg.xxPopPrm_08, scrnMsg.C.no(cIndex).logicPopupColNm);
        scrnMsg.xxPopPrm_10.clear();

        // Param10
        String linkItemName = null;
        int linkIndexA = 0;
        if (hasValue(scrnMsg.C.no(cIndex).physPopupColNm)) {
            String physRelnLinkColNm = scrnMsg.C.no(cIndex).physPopupColNm.getValue();
            for (int i = 0; i < scrnMsg.C.getValidCount(); i++) {
                if (physRelnLinkColNm.equals(scrnMsg.C.no(i).physMaintTrgtColNm.getValue())) {
                    if (isSearchItem) {
                        linkItemName = scrnMsg.C.no(i).searchItemNm.getValue();

                        for (int j = 0; j < scrnMsg.A.getValidCount(); j++) {
                            if (scrnMsg.A.no(j).xxRowNum_A.getValueInt() == i) {
                                linkIndexA = j;
                                break;
                            }
                        }
                    } else {
                        linkItemName = scrnMsg.C.no(i).detailItemNm.getValue();
                    }
                    break;
                }
            }
        }

        params[0] = scrnMsg.xxPopPrm_00;
        params[1] = scrnMsg.xxPopPrm_01;
        params[2] = scrnMsg.xxPopPrm_02;
        params[IDX_3] = scrnMsg.xxPopPrm_03;
        params[IDX_4] = scrnMsg.xxPopPrm_04;
        params[IDX_5] = scrnMsg.xxPopPrm_05;
        params[IDX_6] = scrnMsg.xxPopPrm_06;
        params[IDX_7] = scrnMsg.xxPopPrm_07;
        params[IDX_8] = scrnMsg.xxPopPrm_08;
        if (!hasValue(linkItemName)) {
            params[IDX_10] = scrnMsg.xxPopPrm_10;
        }

        try {
            if (isSearchItem) {
                Field f = NSAL9900_ABMsg.class.getField(callItemName);
                params[IDX_9] = f.get(scrnMsg.A.no(selectIndex));

                if (hasValue(linkItemName)) {
                    f = NSAL9900_ABMsg.class.getField(linkItemName);
                    params[IDX_10] = f.get(scrnMsg.A.no(linkIndexA));
                }
            } else {
                Field f = NSAL9900_BBMsg.class.getField(callItemName);
                params[IDX_9] = f.get(scrnMsg.B.no(selectIndex));

                if (hasValue(linkItemName)) {
                    f = NSAL9900_BBMsg.class.getField(linkItemName);
                    params[IDX_10] = f.get(scrnMsg.B.no(selectIndex));
                }
            }
        } catch (Exception ex) {
            throw new S21AbendException(MSG_ID_NSAM0219E, new String[]{ex.getCause().toString()});
        }

        setArgForSubScreen(params);
    }
}

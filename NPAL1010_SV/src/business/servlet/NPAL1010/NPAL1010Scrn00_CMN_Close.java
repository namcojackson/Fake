/**
 * Copyright(c)2011 Canon USA Inc. All rights reserved.
 */
package business.servlet.NPAL1010;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import parts.common.EZDBMsg;
import parts.common.EZDBStringItem;
import parts.common.EZDCMsg;
import parts.servletcommon.EZDApplicationContext;

import business.servlet.NPAL1010.constant.NPAL1010Constant.PARAMS_INDEX;

import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPConstant;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.online.servlet.S21CommonHandler;

/**
 * <pre>
 * NPAL1010 Location Info Pop Up
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2012/06/26   Fujitsu         S.Noguchi       Create          N/A
 * 03/18/2016   CSAI            Y.Imazu         Update          QC#3134
 *</pre>
 */
public class NPAL1010Scrn00_CMN_Close extends S21CommonHandler {

    @Override
    protected void checkInput(EZDApplicationContext ctx, EZDBMsg bMsg) {
        // Do Nothing.
    }

    @Override
    protected EZDCMsg setRequestData(EZDApplicationContext ctx, EZDBMsg bMsg) {

        return null;
    }

    @Override
    protected void doProcess(EZDApplicationContext ctx, EZDBMsg bMsg, EZDCMsg cMsg) {

        NPAL1010BMsg scrnMsg = (NPAL1010BMsg) bMsg;

        if (ZYPConstant.FLG_ON_Y.equals(scrnMsg.xxSelFlg_MS.getValue())) {

            Map<String, Object> rtrnValMap = new HashMap<String, Object>();
            ArrayList<String> invtyLocCdList = new ArrayList<String>();
            ArrayList<String> invtyLocNmList = new ArrayList<String>();
            ArrayList<String> locRoleTpCdList = new ArrayList<String>();
            ArrayList<String> rtlWhCdList = new ArrayList<String>();
            ArrayList<String> rtlWhNmList = new ArrayList<String>();
            ArrayList<String> rtlSwhCdList = new ArrayList<String>();
            ArrayList<String> rtlSwhNmList = new ArrayList<String>();
            ArrayList<String> invtyAcctCdList = new ArrayList<String>();

            for (int i = 0; i < scrnMsg.A.getValidCount(); i++) {

                if (ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.A.no(i).xxChkBox_A.getValue())) {

                    if (invtyLocCdList.isEmpty() || !invtyLocCdList.contains(scrnMsg.A.no(i).invtyLocCd_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "invtyLocCd", scrnMsg.A.no(i).invtyLocCd_A);
                        invtyLocCdList.add(scrnMsg.A.no(i).invtyLocCd_A.getValue());
                    }

                    if (invtyLocNmList.isEmpty() || !invtyLocNmList.contains(scrnMsg.A.no(i).invtyLocNm_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "invtyLocNm", scrnMsg.A.no(i).invtyLocNm_A);
                        invtyLocNmList.add(scrnMsg.A.no(i).invtyLocNm_A.getValue());
                    }

                    if (locRoleTpCdList.isEmpty() || !locRoleTpCdList.contains(scrnMsg.A.no(i).locRoleTpCd_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "locRoleTpCd", scrnMsg.A.no(i).locRoleTpCd_A);
                        locRoleTpCdList.add(scrnMsg.A.no(i).locRoleTpCd_A.getValue());
                    }

                    if (rtlWhCdList.isEmpty() || !rtlWhCdList.contains(scrnMsg.A.no(i).rtlWhCd_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "rtlWhCd", scrnMsg.A.no(i).rtlWhCd_A);
                        rtlWhCdList.add(scrnMsg.A.no(i).rtlWhCd_A.getValue());
                    }

                    if (rtlWhNmList.isEmpty() || !rtlWhNmList.contains(scrnMsg.A.no(i).rtlWhNm_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "rtlWhNm", scrnMsg.A.no(i).rtlWhNm_A);
                        rtlWhNmList.add(scrnMsg.A.no(i).rtlWhNm_A.getValue());
                    }

                    if (rtlSwhCdList.isEmpty() || !rtlSwhCdList.contains(scrnMsg.A.no(i).rtlSwhCd_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "rtlSwhCd", scrnMsg.A.no(i).rtlSwhCd_A);
                        rtlSwhCdList.add(scrnMsg.A.no(i).rtlSwhCd_A.getValue());
                    }

                    if (rtlSwhNmList.isEmpty() || !rtlSwhNmList.contains(scrnMsg.A.no(i).rtlSwhNm_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "rtlSwhNm", scrnMsg.A.no(i).rtlSwhNm_A);
                        rtlSwhNmList.add(scrnMsg.A.no(i).rtlSwhNm_A.getValue());
                    }

                    if (invtyAcctCdList.isEmpty() || !invtyAcctCdList.contains(scrnMsg.A.no(i).invtyAcctCd_A.getValue())) {

                        rtrnValMap = setRtrnValMap(rtrnValMap, "invtyAcctCd", scrnMsg.A.no(i).invtyAcctCd_A);
                        invtyAcctCdList.add(scrnMsg.A.no(i).invtyAcctCd_A.getValue());
                    }
                }
            }

            if (rtrnValMap != null && !rtrnValMap.isEmpty()) {

                Object[] arg = (Object[]) getArgForSubScreen();

                Object[] params = (Object[]) arg;
                EZDBStringItem param1;
                EZDBStringItem param2;
                EZDBStringItem param6;
                EZDBStringItem param7;
                EZDBStringItem param8;
                EZDBStringItem param9;
                EZDBStringItem param10;
                EZDBStringItem param12;

                if (!ZYPConstant.CHKBOX_ON_Y.equals(scrnMsg.xxChkBox_RS.getValue())) {

                    if (params.length > PARAMS_INDEX.LOCATION_CODE.getIndex()) {

                        param1 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_CODE.getIndex()];
                        ZYPEZDItemValueSetter.setValue(param1, (String) rtrnValMap.get("invtyLocCd"));
                    }

                    if (params.length > PARAMS_INDEX.LOCATION_NAME.getIndex()) {

                        param2 = (EZDBStringItem) params[PARAMS_INDEX.LOCATION_NAME.getIndex()];
                        ZYPEZDItemValueSetter.setValue(param2, (String) rtrnValMap.get("invtyLocNm"));
                    }

                    if (params.length > PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()) {

                        param9 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_CODE.getIndex()];
                        ZYPEZDItemValueSetter.setValue(param9, (String) rtrnValMap.get("rtlSwhCd"));
                    }

                    if (params.length > PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()) {

                        param10 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_SWH_NAME.getIndex()];
                        ZYPEZDItemValueSetter.setValue(param10, (String) rtrnValMap.get("rtlSwhNm"));
                    }
                }

                if (params.length > PARAMS_INDEX.LOC_ROLE_TP_CD.getIndex()) {

                    param6 = (EZDBStringItem) params[PARAMS_INDEX.LOC_ROLE_TP_CD.getIndex()];
                    ZYPEZDItemValueSetter.setValue(param6, (String) rtrnValMap.get("locRoleTpCd"));
                }

                if (params.length > PARAMS_INDEX.RETAIL_WH_CODE.getIndex()) {

                    param7 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_CODE.getIndex()];
                    ZYPEZDItemValueSetter.setValue(param7, (String) rtrnValMap.get("rtlWhCd"));
                }

                if (params.length > PARAMS_INDEX.RETAIL_WH_NAME.getIndex()) {

                    param8 = (EZDBStringItem) params[PARAMS_INDEX.RETAIL_WH_NAME.getIndex()];
                    ZYPEZDItemValueSetter.setValue(param8, (String) rtrnValMap.get("rtlWhNm"));
                }

                if (params.length > PARAMS_INDEX.INVTY_ACCT_CD.getIndex()) {

                    param12 = (EZDBStringItem) params[PARAMS_INDEX.INVTY_ACCT_CD.getIndex()];
                    ZYPEZDItemValueSetter.setValue(param12, (String) rtrnValMap.get("invtyAcctCd"));
                }
            }
        }
    }

    /**
     * setRtrnValMap
     * @param rtrnValMap Map<String, Object>
     * @param mapKey String
     * @param stringItem EZDBStringItem
     * @return Map<String, Object>
     */
    private Map<String, Object> setRtrnValMap(Map<String, Object> rtrnValMap, String mapKey, EZDBStringItem stringItem) {

        if (ZYPCommonFunc.hasValue(stringItem)) {

            String mapVal = (String) rtrnValMap.get(mapKey);

            if (ZYPCommonFunc.hasValue(mapVal)) {

                if (mapVal.length() == 1000) {

                    return rtrnValMap;
                }

                mapVal = ZYPCommonFunc.concatString(mapVal, ",", stringItem.getValue());

            } else {

                mapVal = stringItem.getValue();
            }

            if (mapVal.length() > 1000) {

                mapVal = mapVal.substring(0, 1000);
            }

            rtrnValMap.put(mapKey, mapVal);
        }

        return rtrnValMap;
    }
}

/**
 * <pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 06/23/2009   CUSA            Fujitsu         Update          N/A
 * 03/17/2010   CUSA            Fujitsu         Update          3488
 * 04/23/2010   Fujitsu         K.Tajima        Update          4202
 * 08/27/2010   Fujitsu         R.Watanabe      Update          361(All2)
 * 02/15/2011   Fujitsu         K.Tajima        Update          1570(PROD)
 * 02/17/2011   Fujitsu         K.Tajima        Update          1560(PROD)
 * 03/31/2011   Fujitsu         N.Aoyama        Update          1994(PROD)
 * 08/06/2013   Fujitsu         N.Nakazawa      Update          WDS Defect 1469
 * 2013/09/02   Fujitsu         M.Yamada        Update          #1489
 * 
 *</pre>
 */
package business.blap.NWAL0140;

import static business.blap.NWAL0140.constant.NWAL0140Constant.NMAM0039E;
import static business.blap.NWAL0140.constant.NWAL0140Constant.NMAM0147I;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_ERROR;
import static com.canon.cusa.s21.api.NMZ.NMZC003001.constant.NMZC003001Constant.RTRN_CD_SUGGEST;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc.hasValue;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_OFF_N;
import static com.canon.cusa.s21.framework.ZYP.common.ZYPConstant.FLG_ON_Y;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDTBLAccessor;
import business.db.CNTYTMsg;
import business.db.CTRYTMsg;
import business.db.SHIP_TO_CUSTTMsg;
import business.db.SHIP_TO_CUSTTMsgArray;
import business.db.STTMsg;
import business.parts.NMZC003001PMsg;

import com.canon.cusa.s21.api.NMZ.NMZC003001.NMZC003001;
import com.canon.cusa.s21.common.NWX.NWXC001001.NWXC001001Export;
import com.canon.cusa.s21.framework.ZYP.common.ZYPCommonFunc;
import com.canon.cusa.s21.framework.ZYP.common.ZYPEZDItemValueSetter;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.CTRY;
import com.canon.cusa.s21.framework.ZYP.constant.cdtable.RGTN_STS;
import com.canon.cusa.s21.framework.api.S21ApiMessage;
import com.canon.cusa.s21.framework.api.S21ApiUtil;
import com.canon.cusa.s21.framework.api.S21ApiInterface.ONBATCH_TYPE;
import com.canon.cusa.s21.framework.common.db.S21FastTBLAccessor;
import com.canon.cusa.s21.framework.dao.ssm.ezd.exec.context.S21SsmEZDResult;
import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileService;
import com.canon.cusa.s21.framework.userprofile.S21UserProfileServiceFactory;

/** 
 *<pre>
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2016/05/20   SRAA            Y.Chen          Update          QC#4505
 *</pre>
 */
public class NWAL0140BL02 extends S21BusinessHandler {

    /** It has been modified to suggested address. */
    public static final String NWZM1734W = "NWZM1734W";

    /** @ is invalid. */
    public static final String NWAM0789E = "NWAM0789E";
    
    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        super.preDoProcess(cMsg, sMsg);

        try {

            String screenAplID = cMsg.getScreenAplID();

            if ("NWAL0140_INIT".equals(screenAplID)) {
                doProcess_NWAL0140_INIT((NWAL0140CMsg) cMsg, sMsg);

            } else if ("NWAL0140_NMAL6010".equals(screenAplID)) {
                doProcess_NWAL0140_NMAL6010((NWAL0140CMsg) cMsg, sMsg);

            } else if ("NWAL0140_NMAL6050".equals(screenAplID)) {
                doProcess_NWAL0140_NMAL6050((NWAL0140CMsg) cMsg, sMsg);

            } else if ("NWAL0140Scrn00_CMN_Close".equals(screenAplID)) {
                doProcess_NWAL0140_CMN_Close((NWAL0140CMsg) cMsg, sMsg);

            // QC#4505
            } else if ("NWAL0140Scrn00_GetAddress".equals(screenAplID)) {
                doProcess_NWAL0140Scrn00_GetAddress((NWAL0140CMsg) cMsg, sMsg);

            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    private void doProcess_NWAL0140_INIT(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {

        // Get UserProfile
        String globalCmpyCd = getGlobalCompanyCode();

        // Get Country Name by CountryCd
        String ctryCd = bizMsg.ctryCd.getValue();
        bizMsg.ctryNm.setValue(getCtryName(globalCmpyCd, ctryCd));

        if (FLG_OFF_N.equals(bizMsg.xxReadOnlyFlg.getValue()) && FLG_OFF_N.equals(bizMsg.dropShipFlg.getValue())) {

            String shipToCustCd = bizMsg.shipToCustCd.getValue();
            String sellToCustCd = bizMsg.sellToCustCd_J1.getValue();
            String billToCustCd = bizMsg.billToCustCd_J1.getValue();

            SHIP_TO_CUSTTMsgArray shipToCustMsgArray = getShipToCust(getGlobalCompanyCode(), shipToCustCd, sellToCustCd, billToCustCd);

            if (shipToCustMsgArray.getValidCount() != 0) {
                copyShipToData(bizMsg, shipToCustMsgArray.no(0));
            }
        }
    }

    private void doProcess_NWAL0140_NMAL6010(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {

        this.searchOpenWin_ShipTo(bizMsg, sMsg);
    }

    private void doProcess_NWAL0140_NMAL6050(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {

        // Event State
        if ("OpenWin_ShipToState".equals(bizMsg.xxScrEventNm.getValue())) {

            this.searchOpenWin_ShipToState(bizMsg, sMsg);

        // Del Start #1489
//        // Event Post Code
//        } else if ("OpenWin_PostCode".equals(bizMsg.xxScrEventNm.getValue())) {
//
//            this.searchOpenWin_PostCode(bizMsg, sMsg);
        // Del Start #1489
        }
    }

    private void doProcess_NWAL0140_CMN_Close(NWAL0140CMsg cMsg, EZDSMsg sMsg) {
        // is Valid ShipTo?
        if (!isValidShipTo(cMsg)) {
            return;
        }

        if (!isValidAddress(cMsg)) {
            return;
        }

        final String glblCmpyCd = getGlobalCompanyCode();

        final SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = 
            getShipToCust(glblCmpyCd, cMsg.shipToCustCd.getValue(), cMsg.sellToCustCd_BK.getValue(), cMsg.billToCustCd_BK.getValue());

        if (shipToCustTMsgArray.getValidCount() > 0) {

            final SHIP_TO_CUSTTMsg shipToCustTMsg = shipToCustTMsgArray.no(0);

            if (shipToCustTMsg == null) {
                // nothing to do.

            } else {

                // ** from bizMsg
                // Ship To Name
                String locNm            = cMsg.locNm.getValue().replaceAll(" ", "");
                // Ship To Additional Name
                String addlLocNm        = cMsg.addlLocNm.getValue().replaceAll(" ", "");
                // Ship To Address Line 1
                String firstLineAddr    = cMsg.firstLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 2
                String scdLineAddr      = cMsg.scdLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 3
                String thirdLineAddr    = cMsg.thirdLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 4
                String frthLineAddr     = cMsg.frthLineAddr.getValue().replaceAll(" ", "");
                // Ship To Fisrt Ref Cmnt Txt
                String firstRefCmntTxt  = cMsg.firstRefCmntTxt.getValue().replaceAll(" ", "");
                // Ship To Scd Ref Cmnt Txt
                String scdRefCmntTxt    = cMsg.scdRefCmntTxt.getValue().replaceAll(" ", "");
                // Ship To City
                String ctyAddr          = cMsg.ctyAddr.getValue().replaceAll(" ", "");
                // Ship To Post Code
                String postCd           = cMsg.postCd.getValue().replaceAll(" ", "");
                // Ship To Country
                String ctryCd           = cMsg.ctryCd.getValue().replaceAll(" ", "");
                // Ship To County
                String cntyNm           = cMsg.cntyNm.getValue().replaceAll(" ", "");
                
                // State, Province
                String stCd;
                String provNm;

                // is "US" country.
                if ("US".equals(ctryCd.toUpperCase()) ) {
                    // ST_CD
                    stCd = cMsg.stCd.getValue().replaceAll(" ", "");
                    // PROV_NM
                    cMsg.provNm.clear();
                    provNm = cMsg.provNm.getValue();
                } else {
                    // ST_CD  DefID:1994
//                    cMsg.stCd.clear();
//                    stCd = cMsg.stCd.getValue();
                    stCd = cMsg.stCd.getValue().replaceAll(" ", "");
                    // PROV_NM
                    provNm = cMsg.provNm.getValue().replaceAll(" ", "");
                }

                // ** from DB
                // Ship To Name
                String locNmDB             = shipToCustTMsg.locNm.getValue().replaceAll(" ", "");
                // Ship To Additional Name
                String addlLocNmDB         = shipToCustTMsg.addlLocNm.getValue().replaceAll(" ", "");
                // Ship To Address Line 1
                String firstLineAddrDB     = shipToCustTMsg.firstLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 2
                String scdLineAddrDB       = shipToCustTMsg.scdLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 3
                String thirdLineAddrDB     = shipToCustTMsg.thirdLineAddr.getValue().replaceAll(" ", "");
                // Ship To Address Line 4
                String frthLineAddrDB      = shipToCustTMsg.frthLineAddr.getValue().replaceAll(" ", "");
                // Ship To Fisrt Ref Cmnt Txt
                String firstRefCmntTxtDB   = shipToCustTMsg.firstRefCmntTxt.getValue().replaceAll(" ", "");
                // Ship To Scd Ref Cmnt Txt
                String scdRefCmntTxtDB     = shipToCustTMsg.scdRefCmntTxt.getValue().replaceAll(" ", "");
                // Ship To City
                String ctyAddrDB           = shipToCustTMsg.ctyAddr.getValue().replaceAll(" ", "");
                // Ship To State
                String stCdDB              = shipToCustTMsg.stCd.getValue().replaceAll(" ", "");
                // Ship To Zip Code
                String postCdDB            = shipToCustTMsg.postCd.getValue().replaceAll(" ", "");
                // Ship To Country
                String ctryCdDB            = shipToCustTMsg.ctryCd.getValue().replaceAll(" ", "");
                // Ship To County
                String cntyNmDB = "";
                if (hasValue(shipToCustTMsg.cntyPk.getValue())) {
                    cntyNmDB = getCntyName(glblCmpyCd, shipToCustTMsg.cntyPk.getValue()).replaceAll(" ", "");
                }
                // Province
                String provNmDB            = shipToCustTMsg.provNm.getValue().replaceAll(" ", "");

                if (
                        locNm.equals(locNmDB) 
                    &&  addlLocNm.equals(addlLocNmDB) 
                    &&  firstLineAddr.equals(firstLineAddrDB) 
                    &&  scdLineAddr.equals(scdLineAddrDB) 
                    &&  thirdLineAddr.equals(thirdLineAddrDB)
                    &&  frthLineAddr.equals(frthLineAddrDB) 
                    &&  firstRefCmntTxt.equals(firstRefCmntTxtDB) 
                    &&  scdRefCmntTxt.equals(scdRefCmntTxtDB)
                    &&  ctyAddr.equals(ctyAddrDB)
                    &&  stCd.equals(stCdDB)
                    &&  postCd.equals(postCdDB)
                    &&  ctryCd.equals(ctryCdDB)
                    &&  cntyNm.equals(cntyNmDB)
                    &&  provNm.equals(provNmDB)
                ) {
                    cMsg.dropShipFlg.setValue(FLG_OFF_N);
                } else {
                    cMsg.dropShipFlg.setValue(FLG_ON_Y);
                }
            }
        }
    }

    private void searchOpenWin_ShipTo(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {

        // Not select shipToInfo
        if (!hasValue(bizMsg.xxPopPrm_W4.getValue())) {
            bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0021E");
            bizMsg.setMessageInfo("NWAM0021E");
            return;
        }

        // SellTo BillTo difference
        if (hasValue(bizMsg.billToCustCd_BK.getValue()) && hasValue(bizMsg.sellToCustCd_BK.getValue())) {
            
            boolean checkDiff = false;

            if (!bizMsg.billToCustCd_BK.getValue().equals(bizMsg.xxPopPrm.getValue())) {
                checkDiff = true;
                
            }
            if (!bizMsg.sellToCustCd_BK.getValue().equals(bizMsg.xxPopPrm_W2.getValue())) {
                checkDiff = true;
            }

            if (checkDiff) {
                bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0081E");
                bizMsg.setMessageInfo("NWAM0081E");
                return;
            }
        }
        
        // Get UserProfile
        String globalCmpyCd = getGlobalCompanyCode();

        // SHIP_TO_CUST
        SHIP_TO_CUSTTMsgArray shipToCustTMsgArray = 
            getShipToCust(globalCmpyCd, bizMsg.xxPopPrm_W4.getValue(), bizMsg.xxPopPrm_W2.getValue(), bizMsg.xxPopPrm.getValue());

        SHIP_TO_CUSTTMsg shipToCustTMsg = null;
        if(shipToCustTMsgArray.getValidCount() > 0) {
            shipToCustTMsg = shipToCustTMsgArray.no(0);
        }

        if (shipToCustTMsg == null) {
            bizMsg.shipToCustCd.setErrorInfo(1, "NWAM0021E");
            bizMsg.setMessageInfo("NWAM0021E");
            return;
        }
        
        this.setResult(bizMsg, shipToCustTMsg);
    }

    private void searchOpenWin_ShipToState(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {

        // Get UserProfile
        String globalCmpyCd = getGlobalCompanyCode();

        // Get State
        String stCd = bizMsg.stCd.getValue();
        STTMsg stRes = getSt(globalCmpyCd, stCd);

        if (stRes == null) {
            bizMsg.stCd.setErrorInfo(1, "NWAM0088E");

        } else {
            bizMsg.ctryCd.clear();
            bizMsg.ctryNm.clear();

            // Get Country
            String cntryCd = stRes.ctryCd.getValue();
            bizMsg.ctryCd.setValue(cntryCd);
            bizMsg.ctryNm.setValue(getCtryName(globalCmpyCd, cntryCd));
            
            // Provice
            bizMsg.provNm.clear();
        }
    }

    // Del Start #1489
//    private void searchOpenWin_PostCode(NWAL0140CMsg bizMsg, EZDSMsg sMsg) {
//
//        // Get User Profile
//        String globalCmpyCd = getGlobalCompanyCode();
//
//        POSTTMsg postTMsg = getPost(globalCmpyCd, bizMsg.postCd.getValue(), bizMsg.ctyAddr.getValue());
//
//        if (postTMsg == null) {
//            bizMsg.postCd.setErrorInfo(1, "NWAM0254E");
//            
//        } else {
//
//            bizMsg.stCd.clear();
//            bizMsg.ctryCd.clear();
//            bizMsg.ctryNm.clear();
//            bizMsg.cntyNm.clear();
//
//            // State
//            String state = "";
//            if (hasValue(postTMsg.stCd)) {
//                state = postTMsg.stCd.getValue();
//
//                // Country
//                STTMsg st = getSt(globalCmpyCd, state);
//                if (st != null) {
//                    String countryCd = st.ctryCd.getValue();
//                    bizMsg.ctryCd.setValue(countryCd);
//                    bizMsg.ctryNm.setValue(getCtryName(globalCmpyCd, countryCd));
//                }
//            }
//            bizMsg.stCd.setValue(state);
//
//            // County
//            CNTY_POST_RELNTMsg cntyPost = getCntyPostReln(globalCmpyCd, postTMsg.postPk.getValue());
//            String cntyName = "";
//            if (cntyPost != null) {
//                cntyName = getCntyName(globalCmpyCd, cntyPost.cntyPk.getValue());
//            }
//            bizMsg.cntyNm.setValue(cntyName);
//
//            // Provice
//            bizMsg.provNm.clear();
//        }
//    }
    // Del End #1489

    private void setResult(NWAL0140CMsg bizMsg, SHIP_TO_CUSTTMsg shipToCustRes) {

        // Ship To from Screen of Customer Sales Hierarchy
        bizMsg.shipToCustCd.setValue(bizMsg.xxPopPrm_W4.getValue());
        // UserProfile
        S21UserProfileService profileService = S21UserProfileServiceFactory.getInstance().getService();
        String globalCmpyCd = profileService.getGlobalCompanyCode();
        // Drop Ship Flg
        bizMsg.dropShipFlg.setValue(FLG_OFF_N);
        // Ship To Name
        bizMsg.locNm.setValue(bizMsg.xxPopPrm_W5.getValue());
        // Ship To Additional Name
        bizMsg.addlLocNm.setValue(shipToCustRes.addlLocNm.getValue());
        // Address Line 1
        bizMsg.firstLineAddr.setValue(shipToCustRes.firstLineAddr.getValue());
        // Address Line 2
        bizMsg.scdLineAddr.setValue(shipToCustRes.scdLineAddr.getValue());
        // Address Line 3
        bizMsg.thirdLineAddr.setValue(shipToCustRes.thirdLineAddr.getValue());
        // Address Line 4
        bizMsg.frthLineAddr.setValue(shipToCustRes.frthLineAddr.getValue());
        // Ship To Txt1
        bizMsg.firstRefCmntTxt.setValue(shipToCustRes.firstRefCmntTxt.getValue());
        // Ship To Txt2
        bizMsg.scdRefCmntTxt.setValue(shipToCustRes.scdRefCmntTxt.getValue());
        // City Name
        bizMsg.ctyAddr.setValue(shipToCustRes.ctyAddr.getValue());
        // County
        BigDecimal cntyPk = shipToCustRes.cntyPk.getValue();
        bizMsg.cntyNm.setValue(getCntyName(globalCmpyCd, cntyPk));
        // State
        bizMsg.stCd.setValue(shipToCustRes.stCd.getValue());
        // State
        bizMsg.provNm.setValue(shipToCustRes.provNm.getValue());
        // Post Code
        bizMsg.postCd.setValue(shipToCustRes.postCd.getValue());
        // Country Code
        String countryCd = shipToCustRes.ctryCd.getValue();
        bizMsg.ctryCd.setValue(countryCd);
        // Country Name
        bizMsg.ctryNm.setValue(getCtryName(globalCmpyCd, countryCd));
        if (!hasValue(bizMsg.xxDtlLineNum)) {
            // Bill To Code
            bizMsg.billToCustCd_BK.setValue(bizMsg.xxPopPrm.getValue());
            // Bill To Name
            bizMsg.billToLocNm_BK.setValue(bizMsg.xxPopPrm_W1.getValue());
            // Sell To Code
            bizMsg.sellToCustCd_BK.setValue(bizMsg.xxPopPrm_W2.getValue());
            // Sell To Name
            bizMsg.sellToLocNm_BK.setValue(bizMsg.xxPopPrm_W3.getValue());
        }
    }


    private boolean isValidAddress(NWAL0140CMsg cMsg) {
        boolean result = true;

        NMZC003001PMsg param = new NMZC003001PMsg();
        ZYPEZDItemValueSetter.setValue(param.glblCmpyCd, getGlobalCompanyCode());
        ZYPEZDItemValueSetter.setValue(param.firstLineAddr, cMsg.firstLineAddr);
        ZYPEZDItemValueSetter.setValue(param.scdLineAddr, cMsg.scdLineAddr);
        ZYPEZDItemValueSetter.setValue(param.ctyAddr, cMsg.ctyAddr);
        ZYPEZDItemValueSetter.setValue(param.stCd, cMsg.stCd);
        ZYPEZDItemValueSetter.setValue(param.postCd, cMsg.postCd);
        ZYPEZDItemValueSetter.setValue(param.ctryCd, cMsg.ctryCd);
        ZYPEZDItemValueSetter.setValue(param.cntyNm, cMsg.cntyNm);

        new NMZC003001().execute(param, ONBATCH_TYPE.ONLINE);
        if (S21ApiUtil.isXxMsgId(param)) {
            List<S21ApiMessage> msgList = S21ApiUtil.getXxMsgList(param);
            for (int i = 0; i < msgList.size(); i++) {
                S21ApiMessage msg = msgList.get(i);
                String msgId = msg.getXxMsgid();
                String[] msgPrms = msg.getXxMsgPrmArray();
                cMsg.setMessageInfo(msgId, msgPrms);
                return false;
            }
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_01.getValue())) {
            cMsg.firstLineAddr.setErrorInfo(1, NWAM0789E, new String[]{"Addr Ln1"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_01.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.firstLineAddr, param.firstLineAddr);
            cMsg.firstLineAddr.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_02.getValue())) {
            cMsg.scdLineAddr.setErrorInfo(1, NWAM0789E, new String[]{"Addr Ln2"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_02.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.scdLineAddr, param.scdLineAddr);
            cMsg.scdLineAddr.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_03.getValue())) {
            cMsg.ctyAddr.setErrorInfo(1, NWAM0789E, new String[]{"City"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_03.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr, param.ctyAddr);
            cMsg.ctyAddr.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_04.getValue())) {
            cMsg.stCd.setErrorInfo(1, NWAM0789E, new String[]{"State"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_04.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.stCd, param.stCd);
            cMsg.stCd.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_05.getValue())) {
            cMsg.postCd.setErrorInfo(1, NWAM0789E, new String[]{"Post Code"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_05.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.postCd, param.postCd);
            cMsg.postCd.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_06.getValue())) {
            cMsg.ctryCd.setErrorInfo(1, NWAM0789E, new String[]{"Country"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_06.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.ctryCd, param.ctryCd);
            cMsg.ctryCd.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        if (RTRN_CD_ERROR.equals(param.xxVldStsCd_07.getValue())) {
            cMsg.cntyNm.setErrorInfo(1, NWAM0789E, new String[]{"County"});
            result = false;
        } else if (RTRN_CD_SUGGEST.equals(param.xxVldStsCd_07.getValue())) {
            ZYPEZDItemValueSetter.setValue(cMsg.cntyNm, param.cntyNm);
            cMsg.cntyNm.setErrorInfo(2, NWZM1734W);
            cMsg.setMessageInfo(NWZM1734W);
            result = false;
        }

        return result;
    }

    private boolean isValidShipTo(NWAL0140CMsg cMsg) {

        final String glblCmpyCd = getGlobalCompanyCode();
        
        // Country 
        final String ctryCd = cMsg.ctryCd.getValue();
        final CTRYTMsg ctryTMsg = getCtry(glblCmpyCd, ctryCd);
        if (ctryTMsg == null) {
            cMsg.ctryCd.setErrorInfo(1, "NWAM0090E"); // Entered 'Ship To Country Code' does not exist in the Master.
            return false;
        }
        

        boolean isValidShipTo = true;

        // ------------------------------
        // Country is "US"
        // ------------------------------
        if (!NWXC001001Export.isExportForCtry(glblCmpyCd, ctryCd)) {
            
            // State
            final String stCd = cMsg.stCd.getValue();
            if (hasValue(stCd)) {
                final STTMsg stTMsg = getSt(glblCmpyCd, stCd);
                if (stTMsg == null) {
                    cMsg.stCd.setErrorInfo(1, "NWAM0088E"); // Entered 'Ship To State Code' does not exist in the Master.
                    isValidShipTo = false;
                }
            }
          //WDS Defect 1469 Del Start
            // Post Code
//            
//            final String postCd = cMsg.postCd.getValue();
//            
//            if (!"00000".equals(postCd)) {
//                
//                final POSTTMsgArray postTMsgArray = getPostArray(glblCmpyCd, postCd);
//
//                if (postTMsgArray.getValidCount() == 0) {
//                    cMsg.postCd.setErrorInfo(1, "NWAM0254E"); // Entered Post Code does not exist.
//                    isValidShipTo = false;
//                    
//                } else {
//                    
//                    // Post Code & State
//                    if (hasValue(stCd)) {
//                        if (!cMsg.stCd.isError()) {
//                            boolean isValidStCd = false;
//                            for (int i = 0; i < postTMsgArray.getValidCount(); i++) {
//                                if (stCd.equals(postTMsgArray.no(i).stCd.getValue())) {
//                                    isValidStCd = true;
//                                    break;
//                                }
//                            }
//                            if (!isValidStCd) {
//                                cMsg.stCd.setErrorInfo(1, "NWAM0255E"); // Entered State does not exist in Post Code.
//                                isValidShipTo = false;
//                            }
//                        }
//                    }
//                    
//                    // Post Code & Country
//                    boolean isValidCtryCd= false;
//                    for (int i = 0; i < postTMsgArray.getValidCount(); i++) {
//                        final STTMsg stTMsg = getSt(glblCmpyCd, postTMsgArray.no(i).stCd.getValue());
//                        if (stTMsg != null) {
//                            if (ctryCd.equals(stTMsg.ctryCd.getValue())) {
//                                isValidCtryCd = true;
//                                break;
//                            }
//                        }
//                    }
//                    if (!isValidCtryCd) {
//                        cMsg.ctryCd.setErrorInfo(1, "NWAM0256E"); // Entered Country does not exist in Post Code.
//                        isValidShipTo = false;
//                    }
//                }
//            }
            //WDS Defect 1469 Del End
        }

        return isValidShipTo;


    }

    private static SHIP_TO_CUSTTMsgArray getShipToCust(String globalCmpyCd, String shipTo, String sellTo, String billTo) {

        SHIP_TO_CUSTTMsg condition = new SHIP_TO_CUSTTMsg();
        condition.setSQLID("002");
        condition.setConditionValue("glblCmpyCd01", globalCmpyCd);
        condition.setConditionValue("shipToCustCd01", shipTo);
        condition.setConditionValue("rgtnStsCd01", RGTN_STS.READY_FOR_ORDER_TAKING);
        return (SHIP_TO_CUSTTMsgArray) EZDTBLAccessor.findByCondition(condition);
    }

    // Del Start #1489
//    // Def#1570(Prod)
//    private static POSTTMsgArray getPostArray(String globalCmpyCd, String postCd) {
//        
//        final POSTTMsg postTMsg = new POSTTMsg();
//        postTMsg.setSQLID("001");
//        postTMsg.setConditionValue("glblCmpyCd01", globalCmpyCd);
//        postTMsg.setConditionValue("postCd01",     postCd);
//        
//        return (POSTTMsgArray) EZDTBLAccessor.findByCondition(postTMsg);
//    }
//
//    private static POSTTMsg getPost(String globalCmpyCd, String postCd, String ctyAddr) {
//
//        POSTTMsg post = new POSTTMsg();
//        POSTTMsgArray postArr = new POSTTMsgArray();
//
//        post.setSQLID("007");
//        post.setConditionValue("glblCmpyCd01", globalCmpyCd);
//        post.setConditionValue("postCd01", postCd);
//        post.setConditionValue("ctyAddr01", ctyAddr);
//
//        postArr = (POSTTMsgArray) EZDTBLAccessor.findByCondition(post);
//
//        POSTTMsg postRes = new POSTTMsg();
//        if (postArr.length() > 0) {
//            postRes = postArr.no(0);
//        } else {
//            postRes = null;
//        }
//
//        return postRes;
//    }
    // Del End #1489

    private static STTMsg getSt(String globalCmpyCd, String stCd) {

        STTMsg st = new STTMsg();
        st.glblCmpyCd.setValue(globalCmpyCd);
        st.stCd.setValue(stCd);

        STTMsg stRes = (STTMsg) S21FastTBLAccessor.findByKey(st);

        return stRes;
    }

    private static CTRYTMsg getCtry(String globalCmpyCd, String ctryCd) {

        CTRYTMsg ctry = new CTRYTMsg();
        ctry.glblCmpyCd.setValue(globalCmpyCd);
        ctry.ctryCd.setValue(ctryCd);

        CTRYTMsg ctryRes = (CTRYTMsg) S21FastTBLAccessor.findByKey(ctry);

        return ctryRes;
    }

    private static String getCtryName(String globalCmpyCd, String ctryCd) {

        String ctryNm = "";

        CTRYTMsg ctry = new CTRYTMsg();
        ctry = getCtry(globalCmpyCd, ctryCd);

        if (ctry != null) {
            ctryNm = ctry.ctryNm.getValue();
        }

        return ctryNm;
    }

    private static CNTYTMsg getCnty(String globalCmpyCd, BigDecimal cntyPk) {

        CNTYTMsg cnty = new CNTYTMsg();
        cnty.glblCmpyCd.setValue(globalCmpyCd);

        cnty.cntyPk.setValue(cntyPk);

        CNTYTMsg cntyRes = (CNTYTMsg) S21FastTBLAccessor.findByKey(cnty);

        return cntyRes;
    }

    private static String getCntyName(String globalCmpyCd, BigDecimal cntyPk) {

        String cntyNm = "";

        CNTYTMsg cnty = new CNTYTMsg();
        cnty = getCnty(globalCmpyCd, cntyPk);

        if (cnty != null) {
            cntyNm = cnty.cntyNm.getValue();
        }

        return cntyNm;
    }

    // Del Start #1489
//    private static CNTY_POST_RELNTMsg getCntyPostReln(String globalCmpyCd, BigDecimal pstPk) {
//
//        CNTY_POST_RELNTMsg cntyPost = new CNTY_POST_RELNTMsg();
//        cntyPost.setSQLID("001");
//        cntyPost.setConditionValue("glblCmpyCd01", globalCmpyCd);
//        cntyPost.setConditionValue("postPk01", pstPk);
//
//        CNTY_POST_RELNTMsgArray ctryPostResArr = (CNTY_POST_RELNTMsgArray) EZDTBLAccessor.findByCondition(cntyPost);
//
//        CNTY_POST_RELNTMsg ctryPostRes = new CNTY_POST_RELNTMsg();
//        if (ctryPostResArr.getValidCount() > 0) {
//            ctryPostRes = ctryPostResArr.no(0);
//        }
//        return ctryPostRes;
//    }
    // Del End #1489

    private void copyShipToData(NWAL0140CMsg bizMsg, SHIP_TO_CUSTTMsg shipToCustMsg) {

        EZDMsg.copy(shipToCustMsg, null, bizMsg, null);

        String glblCmpyCd = getGlobalCompanyCode();

        // County
        bizMsg.cntyNm.setValue(getCntyName(glblCmpyCd, shipToCustMsg.cntyPk.getValue()));

        // Country Name
        bizMsg.ctryNm.setValue(getCtryName(glblCmpyCd, shipToCustMsg.ctryCd.getValue()));
    }
    
    // QC#4505
    private void doProcess_NWAL0140Scrn00_GetAddress(NWAL0140CMsg cMsg, EZDSMsg sMsg) {
        if(!CTRY.UNITED_STATES_OF_AMERICA.equals(cMsg.ctryCd.getValue())){
            cMsg.setMessageInfo(NMAM0147I);
            return;
        }

        List<Map<String, Object>> list = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("glblCmpyCd", this.getGlobalCompanyCode());
        queryParam.put("postCd", cMsg.postCd.getValue());
        S21SsmEZDResult res = NWAL0140Query.getInstance().getAddrByPost(queryParam);
        
        if (res.isCodeNormal()) {
            list = (List<Map<String, Object>>) res.getResultObject();
        } else {
            if(cMsg.postCd.getValue().length() > 5){
                queryParam.put("postCd", cMsg.postCd.getValue().subSequence(0, 5));
                res = NWAL0140Query.getInstance().getAddrByPost(queryParam);
                if (res.isCodeNormal()) {
                    list = (List<Map<String, Object>>) res.getResultObject();
                }
            }
        }
        
        if (list == null) {
            cMsg.postCd.setErrorInfo(1, NMAM0039E, new String[] {"Postal Code" });
            return;
        } else {
            List<String> listCtyAddr = getDistinctValueList(list, "CTY_ADDR");
            List<String> listStCd = getDistinctValueList(list, "ST_CD");
            List<String> listCntyNm = getDistinctValueList(list, "CNTY_NM");

            if (listCtyAddr.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.ctyAddr, listCtyAddr.get(0));
            }
            if (listStCd.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.stCd, listStCd.get(0));
            }
            if (listCntyNm.size() == 1) {
                ZYPEZDItemValueSetter.setValue(cMsg.cntyNm, listCntyNm.get(0));
            }
        }
    }
    
    private List<String> getDistinctValueList(List<Map<String, Object>> list, String colNm) {
        List<String> listDistinct = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> map = list.get(i);
            String value = (String) map.get(colNm);
            if (ZYPCommonFunc.hasValue(value)) {
                if (!listDistinct.contains(value)) {
                    listDistinct.add(value);
                }
            }
        }
        return listDistinct;
    }

}

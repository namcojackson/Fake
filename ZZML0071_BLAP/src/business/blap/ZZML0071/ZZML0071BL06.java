/*
 * Copyright (c) 2009 Canon USA Inc. All rights reserved.
 * Original Author: Q02046
 * Company: Fujitsu Limited
 * Date: Jul 8, 2009
 */
package business.blap.ZZML0071;

import java.math.BigDecimal;

import parts.common.EZDCMsg;
import parts.common.EZDMsg;
import parts.common.EZDSMsg;
import parts.dbcommon.EZDSeqTable;
import parts.dbcommon.EZDTBLAccessor;

import business.blap.ZZML0071.common.ZZML0071CommonLogic;
import business.blap.ZZML0071.constant.ZZML0071Constant;
import business.db.GLBL_CMPYTMsg;
import business.db.ML_GRP_ADDRTMsg;
import business.db.ML_USR_ADDRTMsg;

import com.canon.cusa.s21.framework.log.S21AbendException;
import com.canon.cusa.s21.framework.online.blap.S21BusinessHandler;
import com.canon.cusa.s21.framework.userprofile.S21UserInfo;

/**
 * @author Q02673
 */
public class ZZML0071BL06 extends S21BusinessHandler implements ZZML0071Constant {

    int addCnt = 0;
    int updCnt = 0;

    @Override
    protected void doProcess(EZDCMsg cMsg, EZDSMsg sMsg) {
        ZZML0071CMsg bizMsg = (ZZML0071CMsg) cMsg;
        ZZML0071SMsg sharedMsg = (ZZML0071SMsg) sMsg;
        String screenAplID = bizMsg.getScreenAplID();

        try {
            super.preDoProcess(cMsg, sMsg);

            if ("ZZML0071Scrn00_CMN_Submit".equals(screenAplID)) {
                doProcess_ZZML0071Scrn00_CMN_Submit(bizMsg, sharedMsg);
            } else {
                throw new S21AbendException("It's illegal ScreenAplID : " + screenAplID);
            }
        } finally {
            super.postDoProcess(cMsg, sMsg);
        }
    }

    /**
     * @param cMsg
     * @param sMsg
     */
    private void doProcess_ZZML0071Scrn00_CMN_Submit(ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        // existence check Global Company Code
        GLBL_CMPYTMsg chkMsg = new GLBL_CMPYTMsg();
        chkMsg.glblCmpyCd.setValue(cMsg.glblCmpyCd.getValue());
        GLBL_CMPYTMsg glblComtTMsg = (GLBL_CMPYTMsg) EZDTBLAccessor.findByKey(chkMsg);
        if ( glblComtTMsg == null ) {
            cMsg.glblCmpyCd.setErrorInfo(1, "ZZZM9006E", new String[] { "Global Company CD" });
            return;
        }

        // Get mail address from security component
        // In case of mlUsrId is exist.
        if (cMsg.mlUsrId.getValue().length() != 0) {
            if ( setMailAddr(cMsg) ) {
                return;
            }
        }
        
        int vaildCnt = cMsg.A.getValidCount();
        for (int idx = 0; idx < vaildCnt; idx++ ) {
            
            if ( chkMlGrpId(idx, cMsg, sMsg) ) {
                continue;
            }
            
            // get mlUsrAddrPk value
            if ( cMsg.A.no( idx ).mlUsrAddrPk_A.getValue() == null ) {
                // add
                if ( addMlUsrAddr(idx, cMsg, sMsg) ) {
                    break;
                }
            } else {
                // update
                if ( updMlUsrAddr(idx, cMsg, sMsg) ) {
                    break;
                }
            }
        }
        
        // set message
        if ( vaildCnt == ( addCnt + updCnt ) ) {

            if ( vaildCnt == addCnt ) {
                sMsg.A.clear();
                sMsg.A.setValidCount(0);
                
                for (int idx = 0; idx < vaildCnt; idx++ ) {
                    EZDMsg.copy(cMsg.A.no(idx), null, sMsg.A.no(idx), null);
                    sMsg.A.no(idx).ezUpTimeZone_A.setValue(cMsg.A.no(idx).ezUpTimeZone_A.getValue());
                    sMsg.A.no(idx).ezUpTime_A.setValue(cMsg.A.no(idx).ezUpTime_A.getValue());
                }
                sMsg.A.setValidCount(vaildCnt);
                
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Add"});
            } else if ( vaildCnt == updCnt ) {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update"});
            } else {
                cMsg.setMessageInfo("ZZZM9003I", new String[] {"Update and Add"});
            }
            
            ZZML0071CommonLogic.searchZZML0071(cMsg, sMsg);

            // re-search
//            if ( updCnt > 0 ) {
//                ZZML0071CommonLogic.searchZZML0071(cMsg, sMsg);
//            }
        }

    }
    
    /**
     * Get mail address from security component
     * @param cMsg ZZML0071CMsg
     * @return boolean
     */
    private boolean setMailAddr(ZZML0071CMsg cMsg) {
        
        String mlUsrId = cMsg.mlUsrId.getValue();
        
        // Get User Infomation
        S21UserInfo userInfo = getUserProfileService().getUserInfoFor(mlUsrId);
        if ( userInfo == null ) {
            cMsg.mlUsrId.setErrorInfo(1, "ZZZM9006E", new String[] { "User ID" });
            return true;
        }
        
        // Set Mail User Address
        if ( cMsg.mlUsrAddr.getValue().length() == 0 ) {

            String mlUsrAddr = userInfo.getEmailAddress();
            if ( mlUsrAddr == null || mlUsrAddr.length() == 0  ) {
                cMsg.mlUsrAddr.setErrorInfo(1, "ZZZM9006E", new String[] { "Mail Address" });
                return true;
            }
            if (mlUsrAddr.length() > 240) {
                mlUsrAddr = mlUsrAddr.substring(0,240);
            }
            cMsg.mlUsrAddr.setValue( mlUsrAddr );
        }

        // Set Mail User Name
        if ( cMsg.mlUsrNm.getValue().length() == 0 ) {
            cMsg.mlUsrNm.setValue( userInfo.getFullName() );
        }
        // Set Mail User Locale
        if ( cMsg.mlUsrLocId.getValue().length() == 0 ) {
            String mlUsrLocId = userInfo.getLocale().toString();
            // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            // if (mlUsrLocId != null && mlUsrLocId.length() != 0) {
            //     mlUsrLocId = mlUsrLocId.substring(0, 2);
            // }
            // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
            cMsg.mlUsrLocId.setValue( mlUsrLocId );
        }
        
        return false;
    }
    
    /**
     * Check Mail Group Id
     * @param idx   data index
     * @param cMsg  EZDCMsg
     * @param sMsg  EZDSMsg
     * @return boolean   false:non-error true:error
     */
    private boolean chkMlGrpId(int idx, ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        if ( idx < sMsg.A.getValidCount() ) {
            if ( sMsg.A.no( idx ).mlGrpId_A.getValue().equals( cMsg.A.no( idx ).mlGrpId_A.getValue() ) ) {
                return false;
            }
        }
        
        // check existence
        ML_GRP_ADDRTMsg conditionTMsg = new ML_GRP_ADDRTMsg();
        conditionTMsg.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        conditionTMsg.mlGrpId.setValue( cMsg.A.no( idx ).mlGrpId_A.getValue() );
        ML_GRP_ADDRTMsg rstTMsg = (ML_GRP_ADDRTMsg) EZDTBLAccessor.findByKey( conditionTMsg );
        if ( rstTMsg == null ) {
            cMsg.A.no(idx).mlGrpId_A.setErrorInfo(1, "ZZZM9006E", new String[] { "Mail Group ID" });
            return true;
        }
        
        return false;
    }

    /**
     * Mail user Address add (ML_USR_ADDR add)
     * @param idx   data index
     * @param cMsg  EZDCMsg
     * @param sMsg  EZDSMsg
     * @return boolean   false:non-error true:error
     */
    private boolean addMlUsrAddr(int idx, ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        ML_USR_ADDRTMsg regTMsg = new ML_USR_ADDRTMsg();

        // insert
        setMlUsrData(idx, cMsg, regTMsg);
        EZDTBLAccessor.create(regTMsg);
        if ( EZDTBLAccessor.RTNCD_NORMAL.equals( regTMsg.getReturnCode() ) ) {
            addCnt++;
            EZDMsg.copy(regTMsg, "", cMsg.A.no(idx), "A");
            cMsg.A.no(idx).ezUpTimeZone_A.setValue(regTMsg.ezUpTimeZone.getValue());
            cMsg.A.no(idx).ezUpTime_A.setValue(regTMsg.ezUpTime.getValue());

        } else if ( EZDTBLAccessor.RTNCD_DUPLICATE.equals( regTMsg.getReturnCode() ) ) {
            cMsg.A.no(idx).mlGrpId_A.setErrorInfo(1, "ZZZM9015E", null);
        } else {
            cMsg.setMessageInfo("ZZZM9012E", new String[] { regTMsg.getReturnCode() });
            return true;
        }
        
        return false;
    }

    /**
     * Mail user Address update (ML_USR_ADDR update)
     * @param idx   data index
     * @param cMsg  EZDCMsg
     * @param sMsg  EZDSMsg
     * @return boolean   false:non-error true:error
     */
    private boolean updMlUsrAddr(int idx, ZZML0071CMsg cMsg, ZZML0071SMsg sMsg) {

        ML_USR_ADDRTMsg conditionTMsg = new ML_USR_ADDRTMsg();
        
        // check existence
        conditionTMsg.glblCmpyCd.setValue( cMsg.glblCmpyCd.getValue() );
        conditionTMsg.mlUsrAddrPk.setValue( cMsg.A.no( idx ).mlUsrAddrPk_A.getValue() );
        ML_USR_ADDRTMsg regTMsg = (ML_USR_ADDRTMsg) EZDTBLAccessor.findByKeyForUpdateNoWait( conditionTMsg );
        if ( regTMsg == null ) {
            cMsg.A.no(idx).mlGrpId_A.setErrorInfo(1, "ZZZM9004E", null);
            return false;
        }

        // get the update date and time and update time zone
        if ( idx < sMsg.A.getValidCount() ) {
            
            String ezUpTime = sMsg.A.no( idx ).ezUpTime_A.getValue();
            String ezUpTimeZone = sMsg.A.no( idx ).ezUpTimeZone_A.getValue();

            // check the update date and time and update time zone
            if ( !ezUpTime.equals(regTMsg.ezUpTime.getValue()) || !ezUpTimeZone.equals(regTMsg.ezUpTimeZone.getValue()) ) {
                cMsg.A.no(idx).mlGrpId_A.setErrorInfo(1, "ZZZM9004E", null);
                return false;
            }
        }

        // update
        setMlUsrData(idx, cMsg, regTMsg);
        EZDTBLAccessor.update( regTMsg );
        if ( EZDTBLAccessor.RTNCD_NORMAL.equals( regTMsg.getReturnCode() ) ) {
            updCnt++;
        } else {
            cMsg.setMessageInfo("ZZZM9013E", new String[] { regTMsg.getReturnCode() });
            return true;
        }
        
        return false;
    }

    /**
     * set data of ZZML0071CMsg in ML_USR_ADDRTMsg
     * @param idx   data index
     * @param cMsg  EZDCMsg
     * @param regTMsg
     */
    private void setMlUsrData(int idx, ZZML0071CMsg cMsg, ML_USR_ADDRTMsg regTMsg) {
        
        if ( cMsg.A.no( idx ).mlUsrAddrPk_A.getValue() == null) {
            regTMsg.glblCmpyCd.setValue(    cMsg.glblCmpyCd.getValue());
            BigDecimal key = getMlUsrAddrKey();
            regTMsg.mlUsrAddrPk.setValue( key );
            cMsg.A.no( idx ).mlUsrAddrPk_A.setValue(key);
        }
        
        if ( idx < cMsg.A.getValidCount() ) {
            regTMsg.mlGrpId.setValue(       cMsg.A.no( idx ).mlGrpId_A.getValue() );
            regTMsg.mlKeyFirstCd.setValue(  cMsg.A.no( idx ).mlKeyFirstCd_A.getValue() );
            regTMsg.mlKeyScdCd.setValue(    cMsg.A.no( idx ).mlKeyScdCd_A.getValue() );
            regTMsg.mlKeyThirdCd.setValue(  cMsg.A.no( idx ).mlKeyThirdCd_A.getValue() );
        }
        
        regTMsg.mlUsrId.setValue(       cMsg.mlUsrId.getValue());
        regTMsg.mlUsrAddr.setValue(     cMsg.mlUsrAddr.getValue());
        regTMsg.mlUsrNm.setValue(       cMsg.mlUsrNm.getValue());
        regTMsg.mlUsrLocId.setValue(    cMsg.mlUsrLocId.getValue());
        regTMsg.mlUsrDescTxt.setValue(  cMsg.mlUsrDescTxt.getValue());
    }

    /**
     * Get Mail User Address Primary Key
     * @return BigDecimal  Primary Key
     */
    private BigDecimal getMlUsrAddrKey() {
        String mlUsrAddrSeq = EZDSeqTable.getNumberString(ML_USR_ADDR_SQ, ML_USR_ADDR_SQ_DIGIT);
        return new BigDecimal(mlUsrAddrSeq);
    }

}

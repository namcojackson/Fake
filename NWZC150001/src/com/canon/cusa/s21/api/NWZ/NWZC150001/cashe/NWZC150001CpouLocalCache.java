/**
 * <pre>
 * CPO Update API Local Cache
 *
 * The common feature to which order information cooperating
 *  is updated is offered from two or more Order Source.
 *
 * Date         Company         Name            Create/Update   Defect No
 * ----------------------------------------------------------------------
 * 2017/04/04   Fujitsu         S.Takami        Create          S21_NA#Review structure Lv.2
 *</pre>
 */
package com.canon.cusa.s21.api.NWZ.NWZC150001.cashe;

import java.math.BigDecimal;

import parts.common.EZDTMsg;

import business.parts.NWZC045001PMsg;

import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouBizDayCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouAvalInvtyAppIdCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouBillToCustCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouCashDiscTermCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouCntyCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouCoaAcctVCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouCoaProjCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouMdseStorePkgCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouOtbdCarrVCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouPmtTermCashDiscCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouSellToCustCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouShipFromLocListVCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouShipToCustCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouShpgSvcFrtChrgRelnCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouTocCache;
import com.canon.cusa.s21.api.NWZ.NWZC150001.cashe.NWZC150001CpouWHCache;
import com.canon.cusa.s21.framework.common.collections.S21LRUMap;
import com.canon.cusa.s21.framework.common.db.S21CacheTBLAccessor;

/**
 * Local data chache class. <br>
 * used by validation abount master data, transaction data...<br>
 * @author K.Tajima
 */
public class NWZC150001CpouLocalCache {

    public final NWZC150001CpouShipToCustCache shipToCustCache = new NWZC150001CpouShipToCustCache();

    public final NWZC150001CpouSellToCustCache sellToCustCache = new NWZC150001CpouSellToCustCache();

    public final NWZC150001CpouBillToCustCache billToCustCache = new NWZC150001CpouBillToCustCache();

    public final NWZC150001CpouCashDiscTermCache cashDiscTermCache = new NWZC150001CpouCashDiscTermCache();

    public final NWZC150001CpouShpgSvcFrtChrgRelnCache shpgSvcFrtChrgRelnCache = new NWZC150001CpouShpgSvcFrtChrgRelnCache();

//    public final NWZC150001CpouAltPayerCache altPayerCache = new NWZC150001CpouAltPayerCache();

    public final NWZC150001CpouAvalInvtyAppIdCache avalInvtyAppIdCache = new NWZC150001CpouAvalInvtyAppIdCache();

    public final NWZC150001CpouTocCache tocCache = new NWZC150001CpouTocCache();

    public final NWZC150001CpouCoaAcctVCache coaAcctVCache = new NWZC150001CpouCoaAcctVCache();

    public final NWZC150001CpouCoaProjCache coaProjCache = new NWZC150001CpouCoaProjCache();

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//    // 20121126 M.Fuji WDS Solution#127 OrderDrivenPO
//    /** venderCache */
//    public final VenderCache venderCache = new VenderCache();
    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//    // 20121126 M.Fuji WDS Solution Intangible
//    /** PrintCompanyVenderCache */
//    public final PrintCompanyVenderCache printCompanyVenderCache = new PrintCompanyVenderCache();
    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    public final NWZC150001CpouOtbdCarrVCache otbdCarrVCache = new NWZC150001CpouOtbdCarrVCache();

    public final NWZC150001CpouShipFromLocListVCache shipFromLocListVCache = new NWZC150001CpouShipFromLocListVCache();

    public final NWZC150001CpouWHCache whCache = new NWZC150001CpouWHCache();

    public final NWZC150001CpouCntyCache cntyCache = new NWZC150001CpouCntyCache();

    public final NWZC150001CpouMdseStorePkgCache mdseStorePkgCache = new NWZC150001CpouMdseStorePkgCache();

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//    // START   ADD M.Fuji [Defect#2394]
//    public final DsMdseInfoCache dsMdseInfoCache = new DsMdseInfoCache();
//
//    // END   ADD M.Fuji [Defect#2394]
    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    //START DELETE S.Yoshida [WDS Defect #1489]
    //        public final PostCache postCache = new PostCache(S21SsmBatchClient.getClient(NWZC150001.class));
    //END   DELETE S.Yoshida [WDS Defect #1489]

    public final NWZC150001CpouPmtTermCashDiscCache pmtTermCashDiscCache = new NWZC150001CpouPmtTermCashDiscCache();

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//    public final S21LRUMap<String, Boolean> countryOriginCheckResultCache = new S21LRUMap<String, Boolean>();
    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    public final S21LRUMap<String, Boolean> exportForCtryCheckResultCache = new S21LRUMap<String, Boolean>();

    public final S21LRUMap<String, Boolean> contractCheckResultCache = new S21LRUMap<String, Boolean>();

    public final S21LRUMap<String, String> defaultTocCdCache = new S21LRUMap<String, String>();

    public final S21LRUMap<String, BigDecimal> exchangeRateCache = new S21LRUMap<String, BigDecimal>();

    // 2017/03/29 S21_NA#Review structure Lv.1 Del Start
//        public final S21LRUMap<String, String> priceTimingCdCache = new S21LRUMap<String, String>();

//    public final S21LRUMap<String, Boolean> isDoCanceledCache = new S21LRUMap<String, Boolean>();
    // 2017/03/29 S21_NA#Review structure Lv.1 Del End

    public final S21LRUMap<String, NWZC045001PMsg> pmtTermCashDiscTermCache = new S21LRUMap<String, NWZC045001PMsg>();

    public final NWZC150001CpouBizDayCache bizDayCache = new NWZC150001CpouBizDayCache();

    public static EZDTMsg findByKeyWithCache(EZDTMsg reqTMsg) {

        return S21CacheTBLAccessor.findByKey(reqTMsg);
    }
}

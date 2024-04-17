package business.blap.ZZML0071.constant;

/**
 * @author q02673
 */
public interface ZZML0071Constant {

    /** ML_USR_ADDR_SQ sequence */
    static final String ML_USR_ADDR_SQ = "ML_USR_ADDR_SQ";
    
    /** ML_USR_ADDR_SQ sequence digit */
    static final int ML_USR_ADDR_SQ_DIGIT = 28;
    
    /** Language list */
    static enum Language {
        
        // START 2013/11/12 M.Sumida Mod from language only to locale(lang + country)
        // en("Engilish"),
        // fr("French"),
        // pt("Portuguese"),
        // es("Spanish"),
        // ja("Japanese");
        en("English general"),
        es("Spanish general"),
        es_MX("Spanish Mexico"),
        es_PA("Spanish Panama"),
        es_PE("Spanish Peru"),
        pt("Portuguese general");
        // END   2013/11/12 M.Sumida Mod from language only to locale(lang + country)
        
        private String languageNm;
        
        private Language(String arg) {
            this.languageNm = arg;
        }
        
        public String getLangName() {
            return languageNm;
        }
    }
}

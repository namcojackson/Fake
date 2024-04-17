package business.blap.ZZML0010.constant;

/**
 * @author Q02673
 */
public interface ZZML0010Constant {
    static final String GLOBAL_COMPANY_CODE = "Global Company Code";
    
    /** Language list */
    static enum Language {
        
        // START 2013/08/14 M.Sumida Mod from language only to locale(lang + country)
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
        // END   2013/08/14 M.Sumida Mod from language only to locale(lang + country)
        
        private String languageNm;
        
        private Language(String arg) {
            this.languageNm = arg;
        }
        
        public String getLangName() {
            return languageNm;
        }
    }
}

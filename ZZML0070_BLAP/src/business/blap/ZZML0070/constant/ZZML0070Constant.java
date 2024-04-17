package business.blap.ZZML0070.constant;

/**
 * @author q02673
 */
public interface ZZML0070Constant {
    /** Language list */
    static enum Language {
        
        en("Engilish"),
        fr("French"),
        pt("Portuguese"),
        es("Spanish"),
        ja("Japanese");
        
        private String languageNm;
        
        private Language(String arg) {
            this.languageNm = arg;
        }
        
        public String getLangName() {
            return languageNm;
        }
    }
}

/**
 * SanshokuSouryukai.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.yaku;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import wiz.project.jan.JanPai;



/**
 * 三色双竜会
 */
@SuppressWarnings("serial")
public enum SanshokuSouryukai {
    
    MPS,
    MSP,
    PSM,
    PMS,
    SMP,
    SPM;
    
    
    
    public Map<JanPai, Integer> getPaiMap () {
        return _souryukaiMap.get(this);
    }
    
    
    
    /**
     * 牌マップ
     */
    private final static EnumMap<SanshokuSouryukai, Map<JanPai, Integer>> _souryukaiMap;
    
    
    
    static {
        final Map<JanPai, Integer> mpsMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}};
        final Map<JanPai, Integer> mspMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}};
        final Map<JanPai, Integer> psmMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}};
        final Map<JanPai, Integer> pmsMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}};
        final Map<JanPai, Integer> smpMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.PIN_1, 1);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 1);}};
        final Map<JanPai, Integer> spmMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 1);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 1);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.MAN_1, 1);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 1);}};
        
        _souryukaiMap = new EnumMap<SanshokuSouryukai, Map<JanPai, Integer>>(SanshokuSouryukai.class);
        _souryukaiMap.put(MPS, mpsMap);
        _souryukaiMap.put(MSP, mspMap);
        _souryukaiMap.put(PSM, psmMap);
        _souryukaiMap.put(PMS, pmsMap);
        _souryukaiMap.put(SMP, smpMap);
        _souryukaiMap.put(SPM, spmMap);
    }
    
}


/**
 * IsshokuSouryukai.java
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
 * 一色双竜会
 */
@SuppressWarnings("serial")
public enum IsshokuSouryukai {
    
    MAN,
    PIN,
    SOU;
    
    
    
    public Map<JanPai, Integer> getPaiMap () {
        return _souryukaiMap.get(this);
    }
    
    
    
    /**
     * 牌マップ
     */
    private final static EnumMap<IsshokuSouryukai, Map<JanPai, Integer>> _souryukaiMap;
    
    
    
    static {
        final Map<JanPai, Integer> manMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 2);}
            {put(JanPai.MAN_2, 2);}
            {put(JanPai.MAN_3, 2);}
            {put(JanPai.MAN_5, 2);}
            {put(JanPai.MAN_7, 2);}
            {put(JanPai.MAN_8, 2);}
            {put(JanPai.MAN_9, 2);}};
        final Map<JanPai, Integer> pinMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 2);}
            {put(JanPai.PIN_2, 2);}
            {put(JanPai.PIN_3, 2);}
            {put(JanPai.PIN_5, 2);}
            {put(JanPai.PIN_7, 2);}
            {put(JanPai.PIN_8, 2);}
            {put(JanPai.PIN_9, 2);}};
        final Map<JanPai, Integer> souMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 2);}
            {put(JanPai.SOU_2, 2);}
            {put(JanPai.SOU_3, 2);}
            {put(JanPai.SOU_5, 2);}
            {put(JanPai.SOU_7, 2);}
            {put(JanPai.SOU_8, 2);}
            {put(JanPai.SOU_9, 2);}};
        
        _souryukaiMap = new EnumMap<IsshokuSouryukai, Map<JanPai, Integer>>(IsshokuSouryukai.class);
        _souryukaiMap.put(MAN, manMap);
        _souryukaiMap.put(PIN, pinMap);
        _souryukaiMap.put(SOU, souMap);
    }
    
}


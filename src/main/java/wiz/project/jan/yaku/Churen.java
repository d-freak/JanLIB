/**
 * Churen.java
 * 
 * @Author
 *   D-freak
 */

package wiz.project.jan.yaku;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import wiz.project.jan.JanPai;



/**
 * 九蓮宝燈
 */
@SuppressWarnings("serial")
public enum Churen {
    
    MAN,
    PIN,
    SOU;
    
    
    
    public Map<JanPai, Integer> getPaiMap () {
        return _churenMap.get(this);
    }
    
    
    
    /**
     * 牌マップ
     */
    private final static EnumMap<Churen, Map<JanPai, Integer>> _churenMap;
    
    
    
    static {
        final Map<JanPai, Integer> manMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.MAN_1, 3);}
            {put(JanPai.MAN_2, 1);}
            {put(JanPai.MAN_3, 1);}
            {put(JanPai.MAN_4, 1);}
            {put(JanPai.MAN_5, 1);}
            {put(JanPai.MAN_6, 1);}
            {put(JanPai.MAN_7, 1);}
            {put(JanPai.MAN_8, 1);}
            {put(JanPai.MAN_9, 3);}};
        final Map<JanPai, Integer> pinMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.PIN_1, 3);}
            {put(JanPai.PIN_2, 1);}
            {put(JanPai.PIN_3, 1);}
            {put(JanPai.PIN_4, 1);}
            {put(JanPai.PIN_5, 1);}
            {put(JanPai.PIN_6, 1);}
            {put(JanPai.PIN_7, 1);}
            {put(JanPai.PIN_8, 1);}
            {put(JanPai.PIN_9, 3);}};
        final Map<JanPai, Integer> souMap = new HashMap<JanPai, Integer>() {
            {put(JanPai.SOU_1, 3);}
            {put(JanPai.SOU_2, 1);}
            {put(JanPai.SOU_3, 1);}
            {put(JanPai.SOU_4, 1);}
            {put(JanPai.SOU_5, 1);}
            {put(JanPai.SOU_6, 1);}
            {put(JanPai.SOU_7, 1);}
            {put(JanPai.SOU_8, 1);}
            {put(JanPai.SOU_9, 3);}};
        
        _churenMap = new EnumMap<Churen, Map<JanPai, Integer>>(Churen.class);
        _churenMap.put(MAN, manMap);
        _churenMap.put(PIN, pinMap);
        _churenMap.put(SOU, souMap);
    }
    
}


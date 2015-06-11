/**
 * ChmYakuCheckUtil.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.JanPaiType;
import wiz.project.jan.MenTsu;



/**
 * 役確認ユーティリティ (中国麻雀)
 */
public final class ChmYakuCheckUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private ChmYakuCheckUtil() {}
    
    
    
    /**
     * 断幺か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllSimples(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (pai.isYao()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 七星不靠か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isGreaterHonorsAndKnittedTiles(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        if (paiList.containsAll(_allJiList)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 缺一門か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isOneVoidedSuit(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        HashSet<JanPaiType> paiTypeSet = new HashSet<JanPaiType>();
        
        for (final JanPai pai : paiList) {
            paiTypeSet.add(pai.getType());
        }
        
        if (paiTypeSet.contains(JanPaiType.MAN) && paiTypeSet.contains(JanPaiType.PIN) && !paiTypeSet.contains(JanPaiType.SOU)) {
            return true;
        }
        else if (paiTypeSet.contains(JanPaiType.SOU) && paiTypeSet.contains(JanPaiType.MAN) && !paiTypeSet.contains(JanPaiType.PIN)) {
            return true;
        }
        else if (paiTypeSet.contains(JanPaiType.PIN) && paiTypeSet.contains(JanPaiType.SOU) && !paiTypeSet.contains(JanPaiType.MAN)) {
            return true;
        }
        return false;
    }
    
    /**
     * 推不倒か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isReversibleTiles(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        if (_reversibleTilesList.containsAll(paiList)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 連七対か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isSevenShiftedPairs(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        switch (paiList.get(0)) {
        case MAN_1:
        case MAN_2:
        case MAN_3:
        case PIN_1:
        case PIN_2:
        case PIN_3:
        case SOU_1:
        case SOU_2:
        case SOU_3:
            break;
        default:
            return false;
        }
        
        for (final JanPai pai : paiList) {
            if (pai.equals(paiList.get(paiList.size() - 1))) {
                break;
            }
            
            if (!paiList.contains(pai.getNext())) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 四帰一の該当数を取得
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 四帰一の該当数。
     */
    public static int getTileHongCount(final Hand hand, final Map<JanPai, Integer> paiMap) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(paiMap.keySet());
        int count = 0;
        
        isTileHong: for (final JanPai pai : paiList) {
            if (paiMap.get(pai) == 4) {
                for (final MenTsu mentsu : hand.getFixedMenTsuList()) {
                    if (mentsu.getMenTsuType().isKanTsu() && mentsu.hasJanPai(pai)) {
                        continue isTileHong;
                    }
                }
                count++;
            }
        }
        return count;
    }
    
    
    
    /**
     * 全字牌リスト
     */
    private static final List<JanPai> _allJiList = Collections.unmodifiableList(Arrays.asList(JanPai.TON,
                                                                                              JanPai.NAN,
                                                                                              JanPai.SHA,
                                                                                              JanPai.PEI,
                                                                                              JanPai.HAKU,
                                                                                              JanPai.HATU,
                                                                                              JanPai.CHUN)
    );
    
    /**
     * 推不倒リスト
     */
    private static final List<JanPai> _reversibleTilesList = Collections.unmodifiableList(Arrays.asList(JanPai.PIN_1,
                                                                                                        JanPai.PIN_2,
                                                                                                        JanPai.PIN_3,
                                                                                                        JanPai.PIN_4,
                                                                                                        JanPai.PIN_5,
                                                                                                        JanPai.PIN_8,
                                                                                                        JanPai.PIN_9,
                                                                                                        JanPai.SOU_2,
                                                                                                        JanPai.SOU_4,
                                                                                                        JanPai.SOU_5,
                                                                                                        JanPai.SOU_6,
                                                                                                        JanPai.SOU_8,
                                                                                                        JanPai.SOU_9,
                                                                                                        JanPai.HAKU)
    );
    
}


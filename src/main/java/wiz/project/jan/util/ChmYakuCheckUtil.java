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
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import wiz.project.jan.ChmYaku;
import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompletePattern;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.JanPaiType;
import wiz.project.jan.Kumiairyu;
import wiz.project.jan.MenTsu;
import wiz.project.jan.Wind;



/**
 * 役確認ユーティリティ (中国麻雀)
 */
public final class ChmYakuCheckUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private ChmYakuCheckUtil() {}
    
    
    
    /**
     * 平和か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isAllChows(final CompletePattern pattern) {
        return !pattern.getHead().isJi();
    }
    
    /**
     * 緑一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllGreen(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        if (_allGreenList.containsAll(paiList)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * 字一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllHonors(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isJi()) {
                return false;
            }
        }
        return true;
    }
    
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
     * 清幺九か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllTerminals(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isYao()) {
                return false;
            }
            
            if (pai.isJi()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 混幺九か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllTerminalsAndHonors(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isYao()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 五門斉か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isAllTypes(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        HashSet<JanPaiType> paiTypeSet = new HashSet<JanPaiType>();
        
        for (final JanPai pai : paiList) {
            paiTypeSet.add(pai.getType());
        }
        
        if (paiTypeSet.containsAll(new HashSet<>(Arrays.asList(JanPaiType.values())))) {
            return true;
        }
        return false;
    }
    
    /**
     * 大四喜か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 判定結果。
     */
    public static boolean isBigFourWinds(final Hand hand, final Map<JanPai, Integer> paiMap) {
        int windCount = 0;
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (!pai.getType().equals(JanPaiType.JI_WIND)) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            windCount++;
        }
        
        if (windCount == 4) {
            return true;
        }
        return false;
    }
    
    /**
     * 大三元か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 判定結果。
     */
    public static boolean isBigThreeDragons(final Hand hand, final Map<JanPai, Integer> paiMap) {
        int doragonCount = 0;
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (!pai.getType().equals(JanPaiType.JI_DORAGON)) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            doragonCount++;
        }
        
        if (doragonCount == 3) {
            return true;
        }
        return false;
    }
    
    /**
     * 三風刻か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 判定結果。
     */
    public static boolean isBigThreeWinds(final Hand hand, final Map<JanPai, Integer> paiMap) {
        int windCount = 0;
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (!pai.getType().equals(JanPaiType.JI_WIND)) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            windCount++;
        }
        
        if (windCount == 3) {
            return true;
        }
        return false;
    }
    
    /**
     * 箭刻か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 判定結果。
     */
    public static boolean isDragonPung(final Hand hand, final Map<JanPai, Integer> paiMap) {
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (!pai.getType().equals(JanPaiType.JI_DORAGON)) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    /**
     * 清一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isFullFlush(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        HashSet<JanPaiType> paiTypeSet = new HashSet<JanPaiType>();
        
        for (final JanPai pai : paiList) {
            paiTypeSet.add(pai.getType());
        }
        
        for (final JanPaiType paiType : EnumSet.of(JanPaiType.MAN, JanPaiType.PIN, JanPaiType.SOU)) {
            if (paiTypeSet.contains(paiType) && paiTypeSet.size() == 1) {
                return true;
            }
        }
        return false;
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
     * 混一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isHalfFlush(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        HashSet<JanPaiType> paiTypeSet = new HashSet<JanPaiType>();
        boolean hasJi = false;
        
        for (final JanPai pai : paiList) {
            paiTypeSet.add(pai.getType());
            
            if (pai.isJi()) {
                hasJi = true;
            }
        }
        
        if (!hasJi) {
            return false;
        }
        
        if (paiTypeSet.contains(JanPaiType.MAN) && !paiTypeSet.contains(JanPaiType.PIN) && !paiTypeSet.contains(JanPaiType.SOU)) {
            return true;
        }
        else if (paiTypeSet.contains(JanPaiType.SOU) && !paiTypeSet.contains(JanPaiType.MAN) && !paiTypeSet.contains(JanPaiType.PIN)) {
            return true;
        }
        else if (paiTypeSet.contains(JanPaiType.PIN) && !paiTypeSet.contains(JanPaiType.SOU) && !paiTypeSet.contains(JanPaiType.MAN)) {
            return true;
        }
        return false;
    }
    
    /**
     * 組合龍か
     * 
     * @param shuntsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isKnittedStraight(final List<MenTsu> shuntsuList) {
        final List<JanPai> paiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : shuntsuList) {
            paiList.addAll(shuntsu.getSource());
        }
        return Kumiairyu.isKumiairyu(paiList);
    }
    
    /**
     * 小于五か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isLowerFour(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isLowerFour()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 全小か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isLowerTiles(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isLower()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 全中か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isMiddleTiles(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isMiddle()) {
                return false;
            }
        }
        return true;
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
     * 圈風刻か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @param wind 場風。
     * @return 判定結果。
     */
    public static boolean isPrevalentWind(final Hand hand, final Map<JanPai, Integer> paiMap, final Wind wind) {
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (pai.toString().indexOf(wind.toString()) == -1) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
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
     * 門風刻か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @param wind 自風。
     * @return 判定結果。
     */
    public static boolean isSeatWind(final Hand hand, final Map<JanPai, Integer> paiMap, final Wind wind) {
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (pai.toString().indexOf(wind.toString()) == -1) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            return true;
        }
        return false;
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
     * 双箭刻か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 判定結果。
     */
    public static boolean isTwoDragonPungs(final Hand hand, final Map<JanPai, Integer> paiMap) {
        int doragonCount = 0;
        
        for (final Map.Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final JanPai pai = entry.getKey();
            final int paiCount = entry.getValue();
            
            if (!pai.getType().equals(JanPaiType.JI_DORAGON)) {
                continue;
            }
            
            if (paiCount < 3) {
                continue;
            }
            
            if (isTileHog(hand, paiMap, pai)) {
                continue;
            }
            doragonCount++;
        }
        
        if (doragonCount == 2) {
            return true;
        }
        return false;
    }
    
    /**
     * 大于五か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isUpperFour(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isUpperFour()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 全大か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isUpperTiles(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (!pai.isUpper()) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * 和了牌の役を取得
     * 
     * @param completePai 和了牌。
     * @return 和了牌の役リスト。
     */
    public static List<ChmYaku> getCompleteJanPaiYaku(final CompleteJanPai completePai) {
        final List<ChmYaku> yakuList = new ArrayList<ChmYaku>();
        
        if (completePai.isLast()) {
            yakuList.add(ChmYaku.LAST_TILE);
        }
        
        switch (completePai.getType()) {
        case RON_MENZEN:
            yakuList.add(ChmYaku.CONCEALED_HAND);
            break;
        case RON_MENZEN_HO_TEI:
            yakuList.add(ChmYaku.LAST_TILE_CLAIM);
            yakuList.add(ChmYaku.CONCEALED_HAND);
            break;
        case RON_NOT_MENZEN_HO_TEI:
            yakuList.add(ChmYaku.LAST_TILE_CLAIM);
            break;
        case TSUMO_MENZEN:
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN:
            yakuList.add(ChmYaku.SELF_DRAWN);
            break;
        case TSUMO_MENZEN_HAI_TEI:
            yakuList.add(ChmYaku.LAST_TILE_DRAW);
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN_HAI_TEI:
            yakuList.add(ChmYaku.LAST_TILE_DRAW);
            break;
        case TSUMO_MENZEN_RIN_SYAN:
            yakuList.add(ChmYaku.OUT_WITH_REPLACEMENT_TILE);
            yakuList.add(ChmYaku.FULLY_CONCEALED);
            break;
        case TSUMO_NOT_MENZEN_RIN_SYAN:
            yakuList.add(ChmYaku.OUT_WITH_REPLACEMENT_TILE);
            break;
        default:
            break;
        }
        return yakuList;
    }
    
    /**
     * 4順子役を取得
     * 
     * @param fourShunTsuList 順子リスト。
     * @return 4順子役。
     */
    public static ChmYaku getFourChowsYaku(final List<MenTsu> fourShunTsuList) {
        if (fourShunTsuList.size() != 4) {
            throw new IllegalArgumentException("Invalid MenTsu size" + fourShunTsuList.size());
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 3順子役を取得
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 3順子役。
     */
    public static ChmYaku getThreeChowsYaku(final List<MenTsu> threeShunTsuList) {
        if (threeShunTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeShunTsuList.size());
        }
        
        if (ChmYakuCheckUtil.isKnittedStraight(threeShunTsuList)) {
            return ChmYaku.KNITTED_STRAIGHT;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 四帰一の該当数を取得
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @return 四帰一の該当数。
     */
    public static int getTileHogCount(final Hand hand, final Map<JanPai, Integer> paiMap) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(paiMap.keySet());
        int count = 0;
        
        for (final JanPai pai : paiList) {
            if (isTileHog(hand, paiMap, pai)) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * 2順子役を取得
     * 
     * @param first 順子1。
     * @param second 順子2。
     * @return 2順子役。
     */
    public static ChmYaku getTwoChowsYaku(final MenTsu first, final MenTsu second) {
        return ChmYaku.FLOWER;
    }
    
    
    
    /**
     * 指定牌が四帰一か
     * 
     * @param hand 手牌。
     * @param paiMap 和了牌を含む手牌。
     * @param pai 指定牌。
     * @return 判定結果。
     */
    private static boolean isTileHog(final Hand hand, final Map<JanPai, Integer> paiMap, final JanPai pai) {
        
        if (paiMap.get(pai) < 4) {
            return false;
        }
        
        for (final MenTsu mentsu : hand.getFixedMenTsuList()) {
            if (mentsu.hasJanPai(pai) && mentsu.getMenTsuType().isKanTsu()) {
                return false;
            }
        }
        return true;
    }
    
    
    
    /**
     * 緑一色リスト
     */
    private static final List<JanPai> _allGreenList = Collections.unmodifiableList(Arrays.asList(JanPai.SOU_2,
                                                                                                 JanPai.SOU_3,
                                                                                                 JanPai.SOU_4,
                                                                                                 JanPai.SOU_6,
                                                                                                 JanPai.SOU_8,
                                                                                                 JanPai.HATU)
    );
    
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


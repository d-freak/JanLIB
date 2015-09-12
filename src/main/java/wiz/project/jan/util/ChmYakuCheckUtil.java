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
import java.util.Map.Entry;

import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompletePattern;
import wiz.project.jan.Hand;
import wiz.project.jan.JanPai;
import wiz.project.jan.JanPaiType;
import wiz.project.jan.MenTsu;
import wiz.project.jan.Wind;
import wiz.project.jan.yaku.Chinryu;
import wiz.project.jan.yaku.ChmYaku;
import wiz.project.jan.yaku.Churen;
import wiz.project.jan.yaku.IsshokuSouryukai;
import wiz.project.jan.yaku.Karyu;
import wiz.project.jan.yaku.Kumiairyu;
import wiz.project.jan.yaku.SanshokuSouryukai;



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
     * 全双刻か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isAllEvenPungs(final CompletePattern pattern) {
        for (final MenTsu mentsu : pattern.getMenTsuList()) {
            if (!mentsu.isEvenKohTsu()) {
                return false;
            }
        }
        final JanPai pai = pattern.getHead();
        
        if (pai.isEven()) {
            return true;
        }
        return false;
    }
    
    /**
     * 全帯五か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isAllFives(final CompletePattern pattern) {
        for (final MenTsu mentsu : pattern.getMenTsuList()) {
            if (!mentsu.hasFive()) {
                return false;
            }
        }
        return pattern.getHead().isFive();
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
     * 双同刻か
     * 
     * @param twoKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isDoublePung(final List<MenTsu> twoKohTsuList) {
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu kohtsu : twoKohTsuList) {
            topJanPaiList.add(kohtsu.getHead());
        }
        
        for (final JanPai pai : topJanPaiList) {
            for (final JanPai mixedPai : pai.getMixedJanPaiList()) {
                if (topJanPaiList.contains(mixedPai)) {
                    return true;
                }
            }
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
     * 一色四節高か
     * 
     * @param fourKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isFourPureShiftedPungs(final List<MenTsu> fourKohTsuList) {
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu kohtsu : fourKohTsuList) {
            topJanPaiList.add(kohtsu.getHead());
        }
        final JanPai first = topJanPaiList.get(0);
        
        if (first.isJi()) {
            return false;
        }
        final JanPai second = first.getNext();
        
        if (second.getType() != first.getType() || !topJanPaiList.contains(second)) {
            return false;
        }
        final JanPai third = second.getNext();
        
        if (third.getType() != second.getType() || !topJanPaiList.contains(third)) {
            return false;
        }
        final JanPai fourth = third.getNext();
        
        if (fourth.getType() != third.getType() || !topJanPaiList.contains(fourth)) {
            return false;
        }
        return true;
    }
    
    /**
     * 一色四歩高か
     * 
     * @param fourShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isFourShiftedChows(final List<MenTsu> fourShunTsuList) {
        if (!isShunTsuList(fourShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : fourShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        final List<Integer> shiftCountList = Arrays.asList(1, 2);
        
        for (final int shiftCount : shiftCountList) {
            JanPai first = topJanPaiList.get(0);
            
            for (int Count = 0; Count < shiftCount; Count++) {
                first = first.getNext();
            }
            
            if (!topJanPaiList.contains(first)) {
                continue;
            }
            JanPai second = first;
            
            for (int Count = 0; Count < shiftCount; Count++) {
                second = second.getNext();
            }
            
            if (second.getType() != first.getType() || !topJanPaiList.contains(second)) {
                continue;
            }
            JanPai third = second;
            
            for (int Count = 0; Count < shiftCount; Count++) {
                third = third.getNext();
            }
            
            if (third.getType() != second.getType() || !topJanPaiList.contains(third)) {
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
     * 全求人か
     * 
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @return 判定結果。
     */
    public static boolean isMeldedHand(final Hand hand, final CompleteJanPai completePai) {
        if (hand.getFixedMenTsuCount() != 4) {
            return false;
        }
        
        for (final MenTsu mentsu : hand.getFixedMenTsuList()) {
            if (!mentsu.getMenTsuType().isCalled()) {
                return false;
            }
        }
        return completePai.getType().isRon();
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
     * 喜相逢か
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isMixedDoubleChow(final List<MenTsu> twoShunTsuList) {
        if (!isShunTsuList(twoShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : twoShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        
        for (final JanPai entryPai : topJanPaiList) {
            for (final JanPai first : entryPai.getMixedJanPaiList()) {
                if (topJanPaiList.contains(first)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * 三色三歩高か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isMixedShiftedChows(final List<MenTsu> threeShunTsuList) {
        if (!isShunTsuList(threeShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        
        for (final JanPai entryPai : topJanPaiList) {
            for (final JanPai first : entryPai.getMixedNextJanPaiList()) {
                if (topJanPaiList.contains(first)) {
                    for (final JanPai second : first.getMixedNextJanPaiList()) {
                        if (topJanPaiList.contains(second)) {
                            final List<JanPai> paiList = Arrays.asList(entryPai, first, second);
                            final List<JanPaiType> typeList = new ArrayList<JanPaiType>(Arrays.asList(JanPaiType.MAN, JanPaiType.PIN, JanPaiType.SOU));
                            
                            for (final JanPai pai : paiList) {
                                typeList.remove(pai.getType());
                            }
                            
                            if (typeList.isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * 三色三節高か
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isMixedShiftedPungs(final List<MenTsu> threeKohTsuList) {
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu kohtsu : threeKohTsuList) {
            topJanPaiList.add(kohtsu.getHead());
        }
        
        for (final JanPai entryPai : topJanPaiList) {
            for (final JanPai first : entryPai.getMixedNextJanPaiList()) {
                if (topJanPaiList.contains(first)) {
                    for (final JanPai second : first.getMixedNextJanPaiList()) {
                        if (topJanPaiList.contains(second)) {
                            final List<JanPai> paiList = Arrays.asList(entryPai, first, second);
                            final List<JanPaiType> typeList = new ArrayList<JanPaiType>(Arrays.asList(JanPaiType.MAN, JanPaiType.PIN, JanPaiType.SOU));
                            
                            for (final JanPai pai : paiList) {
                                typeList.remove(pai.getType());
                            }
                            
                            if (typeList.isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * 花龍か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isMixedStraight(final List<MenTsu> threeShunTsuList) {
        final List<JanPai> paiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            paiList.addAll(shuntsu.getSource());
        }
        
        for (final Karyu karyu: Karyu.values()) {
            if (paiList.containsAll(karyu.getPaiList())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 三色三同順か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isMixedTripleChow(final List<MenTsu> threeShunTsuList) {
        if (!isShunTsuList(threeShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        
        for (final JanPai entryPai : topJanPaiList) {
            for (final JanPai first : entryPai.getMixedJanPaiList()) {
                if (topJanPaiList.contains(first)) {
                    for (final JanPai second : first.getMixedJanPaiList()) {
                        if (topJanPaiList.contains(second)) {
                            final List<JanPai> paiList = Arrays.asList(entryPai, first, second);
                            final List<JanPaiType> typeList = new ArrayList<JanPaiType>(Arrays.asList(JanPaiType.MAN, JanPaiType.PIN, JanPaiType.SOU));
                            
                            for (final JanPai pai : paiList) {
                                typeList.remove(pai.getType());
                            }
                            
                            if (typeList.isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * 九連宝燈か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isNineGates(final Map<JanPai, Integer> hand) {
        for (final Churen churen : Churen.values()) {
            final Map<JanPai, Integer> churenMap = churen.getPaiMap();
            
            if (hand.equals(churenMap)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 無字か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isNoHonors(final Map<JanPai, Integer> hand) {
        final ArrayList<JanPai> paiList = new ArrayList<JanPai>(hand.keySet());
        
        for (final JanPai pai : paiList) {
            if (pai.isJi()) {
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
     * 全帯幺か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isOutsideHand(final CompletePattern pattern) {
        for (final MenTsu mentsu : pattern.getMenTsuList()) {
            if (!mentsu.hasYao()) {
                return false;
            }
        }
        return pattern.getHead().isYao();
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
     * 一般高か
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isPureDoubleChow(final List<MenTsu> twoShunTsuList) {
        if (!isShunTsuList(twoShunTsuList)) {
            return false;
        }
        final HashSet<JanPai> topJanPaiSet = new HashSet<JanPai>();
        
        for (final MenTsu shuntsu : twoShunTsuList) {
            topJanPaiSet.add(shuntsu.getHead());
        }
        
        if (topJanPaiSet.size() == 1) {
            return true;
        }
        return false;
    }
    
    /**
     * 一色三歩高か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isPureShiftedChows(final List<MenTsu> threeShunTsuList) {
        if (!isShunTsuList(threeShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        final List<Integer> shiftCountList = Arrays.asList(1, 2);
        
        for (final int shiftCount : shiftCountList) {
            JanPai first = topJanPaiList.get(0);
            
            for (int Count = 0; Count < shiftCount; Count++) {
                first = first.getNext();
            }
            
            if (!topJanPaiList.contains(first)) {
                continue;
            }
            JanPai second = first;
            
            for (int Count = 0; Count < shiftCount; Count++) {
                second = second.getNext();
            }
            
            if (second.getType() != first.getType() || !topJanPaiList.contains(second)) {
                continue;
            }
            return true;
        }
        return false;
    }
    
    /**
     * 一色三節高か
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isPureShiftedPungs(final List<MenTsu> threeKohTsuList) {
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu kohtsu : threeKohTsuList) {
            topJanPaiList.add(kohtsu.getHead());
        }
        final JanPai first = topJanPaiList.get(0);
        
        if (first.isJi()) {
            return false;
        }
        final JanPai second = first.getNext();
        
        if (second.getType() != first.getType() || !topJanPaiList.contains(second)) {
            return false;
        }
        final JanPai third = second.getNext();
        
        if (third.getType() != second.getType() || !topJanPaiList.contains(third)) {
            return false;
        }
        return true;
    }
    
    /**
     * 清龍か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isPureStraight(final List<MenTsu> threeShunTsuList) {
        final List<JanPai> paiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            paiList.addAll(shuntsu.getSource());
        }
        
        for (final Chinryu chinryu: Chinryu.values()) {
            if (paiList.containsAll(chinryu.getPaiList())) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 一色双龍会か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isPureTerminalChows(final Map<JanPai, Integer> hand) {
        for (final IsshokuSouryukai souryukai : IsshokuSouryukai.values()) {
            final Map<JanPai, Integer> souryukaiMap = souryukai.getPaiMap();
            
            if (hand.equals(souryukaiMap)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 一色三同順か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isPureTripleChow(final List<MenTsu> threeShunTsuList) {
        if (!isShunTsuList(threeShunTsuList)) {
            return false;
        }
        final HashSet<JanPai> topJanPaiSet = new HashSet<JanPai>();
        
        for (final MenTsu shuntsu : threeShunTsuList) {
            topJanPaiSet.add(shuntsu.getHead());
        }
        
        if (topJanPaiSet.size() == 1) {
            return true;
        }
        return false;
    }
    
    /**
     * 一色四同順か
     * 
     * @param fourShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isQuadrupleChow(final List<MenTsu> fourShunTsuList) {
        if (!isShunTsuList(fourShunTsuList)) {
            return false;
        }
        final HashSet<JanPai> topJanPaiSet = new HashSet<JanPai>();
        
        for (final MenTsu shuntsu : fourShunTsuList) {
            topJanPaiSet.add(shuntsu.getHead());
        }
        
        if (topJanPaiSet.size() == 1) {
            return true;
        }
        return false;
    }
    
    /**
     * 一色四同順か
     * 
     * @param hand 手牌。
     * @param completePai 和了牌。
     * @return 判定結果。
     */
    public static boolean isQuadrupleChow(final Hand hand, final CompleteJanPai completePai) {
        final JanPai completeJanPai = completePai.getJanPai();
        final Map<JanPai, Integer> paiMap = hand.getCleanMenZenMap(completeJanPai);
        final List<JanPai> tileHogJanPaiList = new ArrayList<>();
        
        for (final Entry<JanPai, Integer> entry : paiMap.entrySet()) {
            final int paiCount = entry.getValue();
            
            if (paiCount == 4) {
                final JanPai pai = entry.getKey();
                tileHogJanPaiList.add(pai);
            }
        }
        
        if (tileHogJanPaiList.size() != 3) {
            return false;
        }
        final JanPai first = tileHogJanPaiList.get(0);
        
        if (first.isJi()) {
            return false;
        }
        final JanPai second = first.getNext();
        
        if (second.getType() != first.getType() || !tileHogJanPaiList.contains(second)) {
            return false;
        }
        final JanPai third = second.getNext();
        
        if (third.getType() != second.getType() || !tileHogJanPaiList.contains(third)) {
            return false;
        }
        return true;
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
     * 連六か
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isShortStraight(final List<MenTsu> twoShunTsuList) {
        if (!isShunTsuList(twoShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : twoShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        JanPai first = topJanPaiList.get(0);
        final JanPaiType type = first.getType();
        final int shiftCount = 3;
        
        for (int Count = 0; Count < shiftCount; Count++) {
            first = first.getNext();
        }
        
        if (first.getType() != type || !topJanPaiList.contains(first)) {
            return false;
        }
        return true;
    }
    
    /**
     * 三色双龍会か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isThreeSuitedTerminalChows(final Map<JanPai, Integer> hand) {
        for (final SanshokuSouryukai souryukai : SanshokuSouryukai.values()) {
            final Map<JanPai, Integer> souryukaiMap = souryukai.getPaiMap();
            
            if (hand.equals(souryukaiMap)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 三同刻か
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isTriplePung(final List<MenTsu> threeKohTsuList) {
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu kohtsu : threeKohTsuList) {
            topJanPaiList.add(kohtsu.getHead());
        }
        
        for (final JanPai entryPai : topJanPaiList) {
            for (final JanPai first : entryPai.getMixedJanPaiList()) {
                if (topJanPaiList.contains(first)) {
                    for (final JanPai second : first.getMixedJanPaiList()) {
                        if (topJanPaiList.contains(second)) {
                            final List<JanPai> paiList = Arrays.asList(entryPai, first, second);
                            final List<JanPaiType> typeList = new ArrayList<JanPaiType>(Arrays.asList(JanPaiType.MAN, JanPaiType.PIN, JanPaiType.SOU));
                            
                            for (final JanPai pai : paiList) {
                                typeList.remove(pai.getType());
                            }
                            
                            if (typeList.isEmpty()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
    
    /**
     * 老少副か
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isTwoTerminalChows(final List<MenTsu> twoShunTsuList) {
        if (!isShunTsuList(twoShunTsuList)) {
            return false;
        }
        final List<JanPai> topJanPaiList = new ArrayList<JanPai>();
        
        for (final MenTsu shuntsu : twoShunTsuList) {
            topJanPaiList.add(shuntsu.getHead());
        }
        JanPai first = topJanPaiList.get(0);
        final JanPaiType type = first.getType();
        final int shiftCount = 6;
        
        for (int Count = 0; Count < shiftCount; Count++) {
            first = first.getNext();
        }
        
        if (first.getType() != type || !topJanPaiList.contains(first)) {
            return false;
        }
        return true;
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
        
        if (ChmYakuCheckUtil.isQuadrupleChow(fourShunTsuList)) {
            return ChmYaku.QUADRUPLE_CHOW;
        }
        
        if (ChmYakuCheckUtil.isFourShiftedChows(fourShunTsuList)) {
            return ChmYaku.FOUR_SHIFTED_CHOWS;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 4刻子役を取得
     * 
     * @param fourKohTsuList 刻子リスト。
     * @return 4刻子役。
     */
    public static ChmYaku getFourPungsYaku(final List<MenTsu> fourKohTsuList) {
        if (fourKohTsuList.size() != 4) {
            throw new IllegalArgumentException("Invalid MenTsu size" + fourKohTsuList.size());
        }
        
        if (ChmYakuCheckUtil.isFourPureShiftedPungs(fourKohTsuList)) {
            return ChmYaku.FOUR_PURE_SHIFTED_PUNGS;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 幺九刻の該当数を取得
     * 
     * @param kohtsuList 刻子リスト。
     * @return 幺九刻の該当数。
     */
    public static int getPungOfTerminalsOrHonorsCount(final List<MenTsu> kohtsuList) {
        int count = 0;
        
        for (final MenTsu kohtsu : kohtsuList) {
            final JanPai pai = kohtsu.getHead();
            
            if (pai.isYao()) {
                count++;
            }
        }
        return count;
    }
    
    /**
     * X色双龍会を取得
     * 
     * @param hand 手牌。
     * @return X色双龍会。
     */
    public static ChmYaku getTerminalChows(final Map<JanPai, Integer> hand) {
        if (isPureTerminalChows(hand)) {
            return ChmYaku.PURE_TERMINAL_CHOWS;
        }
        
        if (isThreeSuitedTerminalChows(hand)) {
            return ChmYaku.THREE_SUITED_TERMINAL_CHOWS;
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
        
        if (ChmYakuCheckUtil.isPureTripleChow(threeShunTsuList)) {
            return ChmYaku.PURE_TRIPLE_CHOW;
        }
        
        if (ChmYakuCheckUtil.isPureStraight(threeShunTsuList)) {
            return ChmYaku.PURE_STRAIGHT;
        }
        
        if (ChmYakuCheckUtil.isPureShiftedChows(threeShunTsuList)) {
            return ChmYaku.PURE_SHIFTED_CHOWS;
        }
        
        if (ChmYakuCheckUtil.isKnittedStraight(threeShunTsuList)) {
            return ChmYaku.KNITTED_STRAIGHT;
        }
        
        if (ChmYakuCheckUtil.isMixedStraight(threeShunTsuList)) {
            return ChmYaku.MIXED_STRAIGHT;
        }
        
        if (ChmYakuCheckUtil.isMixedTripleChow(threeShunTsuList)) {
            return ChmYaku.MIXED_TRIPLE_CHOW;
        }
        
        if (ChmYakuCheckUtil.isMixedShiftedChows(threeShunTsuList)) {
            return ChmYaku.MIXED_SHIFTED_CHOWS;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 3刻子役を取得
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 3刻子役。
     */
    public static ChmYaku getThreePungsYaku(final List<MenTsu> threeKohTsuList) {
        if (threeKohTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeKohTsuList.size());
        }
        
        if (ChmYakuCheckUtil.isPureShiftedPungs(threeKohTsuList)) {
            return ChmYaku.PURE_SHIFTED_PUNGS;
        }
        
        if (ChmYakuCheckUtil.isTriplePung(threeKohTsuList)) {
            return ChmYaku.TRIPLE_PUNG;
        }
        
        if (ChmYakuCheckUtil.isMixedShiftedPungs(threeKohTsuList)) {
            return ChmYaku.MIXED_SHIFTED_PUNGS;
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
     * @param twoShunTsuList 順子リスト。
     * @return 2順子役。
     */
    public static ChmYaku getTwoChowsYaku(final List<MenTsu> twoShunTsuList) {
        if (twoShunTsuList.size() != 2) {
            throw new IllegalArgumentException("Invalid MenTsu size" + twoShunTsuList.size());
        }
        
        if (ChmYakuCheckUtil.isPureDoubleChow(twoShunTsuList)) {
            return ChmYaku.PURE_DOUBLE_CHOW;
        }
        
        if (ChmYakuCheckUtil.isMixedDoubleChow(twoShunTsuList)) {
            return ChmYaku.MIXED_DOUBLE_CHOW;
        }
        
        if (ChmYakuCheckUtil.isShortStraight(twoShunTsuList)) {
            return ChmYaku.SHORT_STRAIGHT;
        }
        
        if (ChmYakuCheckUtil.isTwoTerminalChows(twoShunTsuList)) {
            return ChmYaku.TWO_TERMINAL_CHOWS;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 2刻子役を取得
     * 
     * @param twoKohTsuList 刻子リスト。
     * @return 2刻子役。
     */
    public static ChmYaku getTwoPungsYaku(final List<MenTsu> twoKohTsuList) {
        if (twoKohTsuList.size() != 2) {
            throw new IllegalArgumentException("Invalid MenTsu size" + twoKohTsuList.size());
        }
        
        if (ChmYakuCheckUtil.isDoublePung(twoKohTsuList)) {
            return ChmYaku.DOUBLE_PUNG;
        }
        return ChmYaku.FLOWER;
    }
    
    /**
     * 待ち役を取得
     * 
     * @param pattern  和了パターンリスト。
     * @param completePai 和了牌。
     * @param completablePaiList 待ち牌リスト。
     * @return 待ち役。
     */
    public static ChmYaku getWaitYaku(final CompletePattern pattern, final CompleteJanPai completePai, final List<JanPai> completablePaiList) {
        if (completablePaiList.size() != 1) {
            return ChmYaku.FLOWER;
        }
        final JanPai head = pattern.getHead();
        final JanPai pai = completePai.getJanPai();
        
        if (head.equals(pai)) {
            return ChmYaku.SINGLE_WAIT;
        }
        final List<MenTsu> shuntsuList = pattern.getShunTsuList();
        
        for (final MenTsu shuntsu : shuntsuList) {
            if (pai.isClosed(shuntsu)) {
                return ChmYaku.CLOSED_WAIT;
            }
            
            if (pai.isEdge(shuntsu)) {
                return ChmYaku.EDGE_WAIT;
            }
        }
        return ChmYaku.FLOWER;
    }
    
    
    
    /**
     * 順子リストか
     * 
     * @param shuntsuList 順子リスト。
     * @return 判定結果。
     */
    private static boolean isShunTsuList(final List<MenTsu> shuntsuList) {
        for (final MenTsu shuntsu : shuntsuList) {
            if (!shuntsu.isShunTsu()) {
                return false;
            }
        }
        return true;
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


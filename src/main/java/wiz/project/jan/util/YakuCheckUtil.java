/**
 * YakuCheckUtil.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import wiz.project.jan.CompleteJanPai;
import wiz.project.jan.CompletePattern;
import wiz.project.jan.JanPai;
import wiz.project.jan.MenTsu;
import wiz.project.jan.yaku.Yaku;



/**
 * 役確認ユーティリティ
 */
public final class YakuCheckUtil {
    
    /**
     * コンストラクタ利用禁止
     */
    private YakuCheckUtil() {}
    
    
    
    /**
     * 全帯ヤオ九か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isChan_Ta(final CompletePattern pattern) {
        // TODO 純全帯ヤオ九をfalseにする
        return ChmYakuCheckUtil.isOutsideHand(pattern);
    }
    
    /**
     * 清一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isChin_Itsu(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isFullFlush(hand);
    }
    
    /**
     * 清老頭か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isChin_Rou_Tou(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isAllTerminals(hand);
    }
    
    /**
     * 九連宝燈か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isChu_Ren(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isNineGates(hand);
    }
    
    /**
     * 混一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isHon_Itsu(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isHalfFlush(hand);
    }
    
    /**
     * 混老頭か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isHon_Rou_Tou(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isAllTerminalsAndHonors(hand);
    }
    
    /**
     * 平和か
     * 
     * @param pattern 和了パターン。
     * @return 判定結果。
     */
    public static boolean isPinfu(final CompletePattern pattern) {
        ChmYakuCheckUtil.isAllChows(pattern);
        // TODO 未実装
        return false;
    }
    
    /**
     * 緑一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isRyu_I_Sou(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isAllGreen(hand);
    }
    
    /**
     * 三色同順か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isSan_Shoku_Dou_Jun(final List<MenTsu> threeShunTsuList) {
        return ChmYakuCheckUtil.isMixedTripleChow(threeShunTsuList);
    }
    
    /**
     * 断幺か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isTan_Yao(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isAllSimples(hand);
    }
    
    /**
     * 字一色か
     * 
     * @param hand 手牌。
     * @return 判定結果。
     */
    public static boolean isTsu_I_Sou(final Map<JanPai, Integer> hand) {
        return ChmYakuCheckUtil.isAllHonors(hand);
    }
    
    /**
     * 一気通貫か
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isIkki_Tsu_Kan(final List<MenTsu> threeShunTsuList) {
        return ChmYakuCheckUtil.isPureStraight(threeShunTsuList);
    }
    
    /**
     * 一盃口か
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 判定結果。
     */
    public static boolean isI_Pei_Kou(final List<MenTsu> twoShunTsuList) {
        return ChmYakuCheckUtil.isPureDoubleChow(twoShunTsuList);
    }
    
    /**
     * 三色同刻か
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 判定結果。
     */
    public static boolean isSan_Shoku_Dou_Kou(final List<MenTsu> threeKohTsuList) {
        return ChmYakuCheckUtil.isTriplePung(threeKohTsuList);
    }
    
    /**
     * 和了牌の役を取得
     * 
     * @param completePai 和了牌。
     * @return 和了牌の役リスト。
     */
    public static List<Yaku> getCompleteJanPaiYaku(final CompleteJanPai completePai) {
        final List<Yaku> yakuList = new ArrayList<Yaku>();
        
        switch (completePai.getType()) {
        case RON_MENZEN_HO_TEI:
            yakuList.add(Yaku.HO_TEI);
            break;
        case RON_NOT_MENZEN_HO_TEI:
            yakuList.add(Yaku.HO_TEI);
            break;
        case TSUMO_MENZEN:
            yakuList.add(Yaku.TSUMO);
            break;
        case TSUMO_MENZEN_HAI_TEI:
            yakuList.add(Yaku.TSUMO);
            yakuList.add(Yaku.HAI_TEI);
            break;
        case TSUMO_NOT_MENZEN_HAI_TEI:
            yakuList.add(Yaku.HAI_TEI);
            break;
        case TSUMO_MENZEN_RIN_SYAN:
            yakuList.add(Yaku.TSUMO);
            yakuList.add(Yaku.RIN_SYAN);
            break;
        case TSUMO_NOT_MENZEN_RIN_SYAN:
            yakuList.add(Yaku.RIN_SYAN);
            break;
        default:
            break;
        }
        return yakuList;
    }
    
    /**
     * 3順子役を取得
     * 
     * @param threeShunTsuList 順子リスト。
     * @return 3順子役。
     */
    public static Yaku getThreeChowsYaku(final List<MenTsu> threeShunTsuList) {
        if (threeShunTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeShunTsuList.size());
        }
        
        if (YakuCheckUtil.isIkki_Tsu_Kan(threeShunTsuList)) {
            return Yaku.IKKI_TSU_KAN;
        }
        
        if (YakuCheckUtil.isSan_Shoku_Dou_Jun(threeShunTsuList)) {
            return Yaku.SAN_SHOKU_DOU_JUN;
        }
        return Yaku.DORA;
    }
    
    /**
     * 3刻子役を取得
     * 
     * @param threeKohTsuList 刻子リスト。
     * @return 3刻子役。
     */
    public static Yaku getThreePungsYaku(final List<MenTsu> threeKohTsuList) {
        if (threeKohTsuList.size() != 3) {
            throw new IllegalArgumentException("Invalid MenTsu size" + threeKohTsuList.size());
        }
        
        if (YakuCheckUtil.isSan_Shoku_Dou_Kou(threeKohTsuList)) {
            return Yaku.SAN_KAN_TSU;
        }
        return Yaku.DORA;
    }
    
    /**
     * 2順子役を取得
     * 
     * @param twoShunTsuList 順子リスト。
     * @return 2順子役。
     */
    public static Yaku getTwoChowsYaku(final List<MenTsu> twoShunTsuList) {
        if (twoShunTsuList.size() != 2) {
            throw new IllegalArgumentException("Invalid MenTsu size" + twoShunTsuList.size());
        }
        
        if (YakuCheckUtil.isI_Pei_Kou(twoShunTsuList)) {
            return Yaku.I_PEI_KOU;
        }
        return Yaku.DORA;
    }
    
}

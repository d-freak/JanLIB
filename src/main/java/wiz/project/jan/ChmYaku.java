/**
 * ChmYaku.java
 * 
 * @Author
 *   Masasutzu
 */

package wiz.project.jan;

import java.util.Arrays;
import java.util.List;



/**
 * 役 (中国麻雀)
 */
public enum ChmYaku {
    
    // 1点役
    
    /**
     * 一般高
     */
    PURE_DOUBLE_CHOW,
    
    /**
     * 喜相逢
     */
    MIXED_DOUBLE_CHOW,
    
    /**
     * 連六
     */
    SHORT_STRAIGHT,
    
    /**
     * 老少副
     */
    TWO_TERMINAL_CHOWS,
    
    /**
     * 幺九刻
     */
    PUNG_OF_TERMINALS_OR_HONORS,
    /**
     * 明槓
     */
    MELDED_KONG,
    
    /**
     * 缺一門
     */
    ONE_VOIDED_SUIT,
    
    /**
     * 無字
     */
    NO_HONORS,
    
    /**
     * 辺張
     */
    EDGE_WAIT,
    
    /**
     * 坎張
     */
    CLOSED_WAIT,
    
    /**
     * 単調将
     */
    SINGLE_WAIT,
    
    /**
     * 自摸
     */
    SELF_DRAWN,
    
    /**
     * 花牌
     */
    FLOWER,
    
    
    
    // 2点役
    
    /**
     * 箭刻
     */
    DRAGON_PUNG,
    
    /**
     * 圈風刻
     */
    PREVALENT_WIND,
    
    /**
     * 門風刻
     */
    SEAT_WIND,
    
    /**
     * 門前清
     */
    CONCEALED_HAND,
    
    /**
     * 平和
     */
    ALL_CHOWS,
    
    /**
     * 四帰一
     */
    TILE_HOG,
    
    /**
     * 双同刻
     */
    DOUBLE_PUNG,
    
    /**
     * 双暗刻
     */
    TWO_CONCEALED_PUNGS,
    
    /**
     * 暗槓
     */
    CONCEALED_KONG,
    
    /**
     * 断幺
     */
    ALL_SIMPLES,
    
    
    
    // 4点役
    
    /**
     * 全帯幺
     */
    OUTSIDE_HAND,
    
    /**
     * 不求人
     */
    FULLY_CONCEALED,
    
    /**
     * 双明槓
     */
    TWO_MELDED_KONGS,
    
    /**
     * 和絶張
     */
    LAST_TILE,
    
    
    
    // 6点役
    
    /**
     * 碰碰和
     */
    ALL_PUNGS,
    
    /**
     * 混一色
     */
    HALF_FLUSH,
    
    /**
     * 三色三歩高
     */
    MIXED_SHIFTED_CHOWS,
    
    /**
     * 五門斉
     */
    ALL_TYPES,
    
    /**
     * 全求人
     */
    MELDED_HAND,
    
    /**
     * 双箭刻
     */
    TWO_DRAGON_PUNGS,
    
    
    
    // 8点役
    
    /**
     * 花龍
     */
    MIXED_STRAIGHT,
    
    /**
     * 推不倒
     */
    REVERSIBLE_TILES,
    
    /**
     * 三色三同順
     */
    MIXED_TRIPLE_CHOW,
    
    /**
     * 三色三節高
     */
    MIXED_SHIFTED_PUNGS,
    
    /**
     * 無番和
     */
    CHICKEN_HAND,
    
    /**
     * 妙手回春
     */
    LAST_TILE_DRAW,
    
    /**
     * 海底撈月
     */
    LAST_TILE_CLAIM,
    
    /**
     * 槓上開花
     */
    OUT_WITH_REPLACEMENT_TILE,
    
    /**
     * 搶槓和
     */
    ROBBING_THE_KONGS,
    
    /**
     * 双暗槓
     */
    TWO_CONCEALED_KONGS,
    
    
    
    // 12点役
    
    /**
     * 全不靠
     */
    LESSER_HONORS_AND_KNITTED_TILES,
    
    /**
     * 組合龍
     */
    KNITTED_STRAIGHT,
    
    /**
     * 大于五
     */
    UPPER_FOUR,
    
    /**
     * 小于五
     */
    LOWER_FOUR,
    
    /**
     * 三風刻
     */
    BIG_THREE_WINDS,
    
    
    
    // 16点役
    
    /**
     * 清龍
     */
    PURE_STRAIGHT,
    
    /**
     * 三色双龍会
     */
    THREE_SUITED_TERMINAL_CHOWS,
    
    /**
     * 一色三歩高
     */
    PURE_SHIFTED_CHOWS,
    
    /**
     * 全帯五
     */
    ALL_FIVES,
    
    /**
     * 三同刻
     */
    TRIPLE_PUNG,
    
    /**
     * 三暗刻
     */
    THREE_CONCEALED_PUNGS,
    
    
    
    // 24点役
    
    /**
     * 七対
     */
    SEVEN_PAIRS,
    
    /**
     * 七星不靠
     */
    GREATER_HONORS_AND_KNITTED_TILES,
    
    /**
     * 全双刻
     */
    ALL_EVEN_PUNGS,
    
    /**
     * 清一色
     */
    FULL_FLUSH,
    
    /**
     * 一色三同順
     */
    PURE_TRIPLE_CHOW,
    
    /**
     * 一色三節高
     */
    PURE_SHIFTED_PUNGS,
    
    /**
     * 全大
     */
    UPPER_TILES,
    
    /**
     * 全中
     */
    MIDDLE_TILES,
    
    /**
     * 全小
     */
    LOWER_TILES,
    
    
    
    // 32点役
    
    /**
     * 一色四歩高
     */
    FOUR_SHIFTED_CHOWS,
    
    /**
     * 三槓
     */
    THREE_KONGS,
    
    /**
     * 混幺九
     */
    ALL_TERMINALS_AND_HONORS,
    
    
    
    // 48点役
    
    /**
     * 一色四同順
     */
    QUADRUPLE_CHOW,
    
    /**
     * 一色四節高
     */
    FOUR_PURE_SHIFTED_PUNGS,
    
    
    
    // 64点役
    
    /**
     * 清幺九
     */
    ALL_TERMINALS,
    
    /**
     * 小四喜
     */
    LITTLE_FOUR_WINDS,
    
    /**
     * 小三元
     */
    LITTLE_THREE_DRAGONS,
    
    /**
     * 字一色
     */
    ALL_HONORS,
    
    /**
     * 四暗刻
     */
    FOUR_CONCEALED_PUNGS,
    
    /**
     * 一色双龍会
     */
    PURE_TERMINAL_CHOWS,
    
    
    
    // 88点役
    
    /**
     * 大四喜
     */
    BIG_FOUR_WINDS,
    
    /**
     * 大三元
     */
    BIG_THREE_DRAGONS,
    
    /**
     * 緑一色
     */
    ALL_GREEN,
    
    /**
     * 九連宝燈
     */
    NINE_GATES,
    
    /**
     * 四槓
     */
    FOUR_KONGS,
    
    /**
     * 連七対
     */
    SEVEN_SHIFTED_PAIRS,
    
    /**
     * 十三幺
     */
    THIRTEEN_ORPHANS;
    
    
    
    /**
     * 実装済みの役を取得
     * 
     * @return 実装済みの役。
     */
    public static List<ChmYaku> getReleased() {
        return Arrays.asList (ChmYaku.ONE_VOIDED_SUIT,
                              ChmYaku.SELF_DRAWN,
                              ChmYaku.DRAGON_PUNG,
                              ChmYaku.PREVALENT_WIND,
                              ChmYaku.SEAT_WIND,
                              ChmYaku.CONCEALED_HAND,
                              ChmYaku.ALL_CHOWS,
                              ChmYaku.TILE_HOG,
                              ChmYaku.ALL_SIMPLES,
                              ChmYaku.FULLY_CONCEALED,
                              ChmYaku.LAST_TILE,
                              ChmYaku.HALF_FLUSH,
                              ChmYaku.ALL_TYPES,
                              ChmYaku.TWO_DRAGON_PUNGS,
                              ChmYaku.REVERSIBLE_TILES,
                              ChmYaku.LAST_TILE_DRAW,
                              ChmYaku.LAST_TILE_CLAIM,
                              ChmYaku.OUT_WITH_REPLACEMENT_TILE,
                              ChmYaku.LESSER_HONORS_AND_KNITTED_TILES,
                              ChmYaku.KNITTED_STRAIGHT,
                              ChmYaku.UPPER_FOUR,
                              ChmYaku.LOWER_FOUR,
                              ChmYaku.BIG_THREE_WINDS,
                              ChmYaku.SEVEN_PAIRS,
                              ChmYaku.GREATER_HONORS_AND_KNITTED_TILES,
                              ChmYaku.FULL_FLUSH,
                              ChmYaku.UPPER_TILES,
                              ChmYaku.MIDDLE_TILES,
                              ChmYaku.LOWER_TILES,
                              ChmYaku.ALL_TERMINALS_AND_HONORS,
                              ChmYaku.ALL_TERMINALS,
                              ChmYaku.ALL_HONORS,
                              ChmYaku.BIG_FOUR_WINDS,
                              ChmYaku.BIG_THREE_DRAGONS,
                              ChmYaku.ALL_GREEN,
                              ChmYaku.SEVEN_SHIFTED_PAIRS,
                              ChmYaku.THIRTEEN_ORPHANS);
    }
    
    
    
    /**
     * 点数を取得
     * 
     * @return 点数。
     */
    public int getPoint() {
        switch (this) {
        case PURE_DOUBLE_CHOW:
        case MIXED_DOUBLE_CHOW:
        case SHORT_STRAIGHT:
        case TWO_TERMINAL_CHOWS:
        case PUNG_OF_TERMINALS_OR_HONORS:
        case MELDED_KONG:
        case ONE_VOIDED_SUIT:
        case NO_HONORS:
        case EDGE_WAIT:
        case CLOSED_WAIT:
        case SINGLE_WAIT:
        case SELF_DRAWN:
        case FLOWER:
            return 1;
        case DRAGON_PUNG:
        case PREVALENT_WIND:
        case SEAT_WIND:
        case CONCEALED_HAND:
        case ALL_CHOWS:
        case TILE_HOG:
        case DOUBLE_PUNG:
        case TWO_CONCEALED_PUNGS:
        case CONCEALED_KONG:
        case ALL_SIMPLES:
            return 2;
        case OUTSIDE_HAND:
        case FULLY_CONCEALED:
        case TWO_MELDED_KONGS:
        case LAST_TILE:
            return 4;
        case ALL_PUNGS:
        case HALF_FLUSH:
        case MIXED_SHIFTED_CHOWS:
        case ALL_TYPES:
        case MELDED_HAND:
        case TWO_DRAGON_PUNGS:
            return 6;
        case MIXED_STRAIGHT:
        case REVERSIBLE_TILES:
        case MIXED_TRIPLE_CHOW:
        case MIXED_SHIFTED_PUNGS:
        case CHICKEN_HAND:
        case LAST_TILE_DRAW:
        case LAST_TILE_CLAIM:
        case OUT_WITH_REPLACEMENT_TILE:
        case ROBBING_THE_KONGS:
        case TWO_CONCEALED_KONGS:
            return 8;
        case LESSER_HONORS_AND_KNITTED_TILES:
        case KNITTED_STRAIGHT:
        case UPPER_FOUR:
        case LOWER_FOUR:
        case BIG_THREE_WINDS:
            return 12;
        case PURE_STRAIGHT:
        case THREE_SUITED_TERMINAL_CHOWS:
        case PURE_SHIFTED_CHOWS:
        case ALL_FIVES:
        case TRIPLE_PUNG:
        case THREE_CONCEALED_PUNGS:
            return 16;
        case SEVEN_PAIRS:
        case GREATER_HONORS_AND_KNITTED_TILES:
        case ALL_EVEN_PUNGS:
        case FULL_FLUSH:
        case PURE_TRIPLE_CHOW:
        case PURE_SHIFTED_PUNGS:
        case UPPER_TILES:
        case MIDDLE_TILES:
        case LOWER_TILES:
            return 24;
        case FOUR_SHIFTED_CHOWS:
        case THREE_KONGS:
        case ALL_TERMINALS_AND_HONORS:
            return 32;
        case QUADRUPLE_CHOW:
        case FOUR_PURE_SHIFTED_PUNGS:
            return 48;
        case ALL_TERMINALS:
        case LITTLE_FOUR_WINDS:
        case LITTLE_THREE_DRAGONS:
        case ALL_HONORS:
        case FOUR_CONCEALED_PUNGS:
        case PURE_TERMINAL_CHOWS:
            return 64;
        case BIG_FOUR_WINDS:
        case BIG_THREE_DRAGONS:
        case ALL_GREEN:
        case NINE_GATES:
        case FOUR_KONGS:
        case SEVEN_SHIFTED_PAIRS:
        case THIRTEEN_ORPHANS:
            return 88;
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 面前限定役か
     * 
     * @return 判定結果。
     */
    public boolean isMenZenOnly() {
        switch (this) {
        case CONCEALED_HAND:
        case FULLY_CONCEALED:
        case LESSER_HONORS_AND_KNITTED_TILES:
        case SEVEN_PAIRS:
        case GREATER_HONORS_AND_KNITTED_TILES:
        case FOUR_CONCEALED_PUNGS:
        case NINE_GATES:
        case SEVEN_SHIFTED_PAIRS:
        case THIRTEEN_ORPHANS:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        switch (this) {
        case PURE_DOUBLE_CHOW:
            return "一般高";
        case MIXED_DOUBLE_CHOW:
            return "喜相逢";
        case SHORT_STRAIGHT:
            return "連六";
        case TWO_TERMINAL_CHOWS:
            return "老少副";
        case PUNG_OF_TERMINALS_OR_HONORS:
            return "幺九刻";
        case MELDED_KONG:
            return "明槓";
        case ONE_VOIDED_SUIT:
            return "缺一門";
        case NO_HONORS:
            return "無字";
        case EDGE_WAIT:
            return "辺張";
        case CLOSED_WAIT:
            return "坎張";
        case SINGLE_WAIT:
            return "単調将";
        case SELF_DRAWN:
            return "自摸";
        case FLOWER:
            return "花牌";
        case DRAGON_PUNG:
            return "箭刻";
        case PREVALENT_WIND:
            return "圈風刻";
        case SEAT_WIND:
            return "門風刻";
        case CONCEALED_HAND:
            return "門前清";
        case ALL_CHOWS:
            return "平和";
        case TILE_HOG:
            return "四帰一";
        case DOUBLE_PUNG:
            return "双同刻";
        case TWO_CONCEALED_PUNGS:
            return "双暗刻";
        case CONCEALED_KONG:
            return "暗槓";
        case ALL_SIMPLES:
            return "断幺";
        case OUTSIDE_HAND:
            return "全帯幺";
        case FULLY_CONCEALED:
            return "不求人";
        case TWO_MELDED_KONGS:
            return "双明槓";
        case LAST_TILE:
            return "和絶張";
        case ALL_PUNGS:
            return "碰碰和";
        case HALF_FLUSH:
            return "混一色";
        case MIXED_SHIFTED_CHOWS:
            return "三色三歩高";
        case ALL_TYPES:
            return "五門斉";
        case MELDED_HAND:
            return "全求人";
        case TWO_DRAGON_PUNGS:
            return "双箭刻";
        case MIXED_STRAIGHT:
            return "花龍";
        case REVERSIBLE_TILES:
            return "推不倒";
        case MIXED_TRIPLE_CHOW:
            return "三色三同順";
        case MIXED_SHIFTED_PUNGS:
            return "三色三節高";
        case CHICKEN_HAND:
            return "無番和";
        case LAST_TILE_DRAW:
            return "妙手回春";
        case LAST_TILE_CLAIM:
            return "海底撈月";
        case OUT_WITH_REPLACEMENT_TILE:
            return "槓上開花";
        case ROBBING_THE_KONGS:
            return "搶槓和";
        case TWO_CONCEALED_KONGS:
            return "双暗槓";
        case LESSER_HONORS_AND_KNITTED_TILES:
            return "全不靠";
        case KNITTED_STRAIGHT:
            return "組合龍";
        case UPPER_FOUR:
            return "大于五";
        case LOWER_FOUR:
            return "小于五";
        case BIG_THREE_WINDS:
            return "三風刻";
        case PURE_STRAIGHT:
            return "清龍";
        case THREE_SUITED_TERMINAL_CHOWS:
            return "三色双龍会";
        case PURE_SHIFTED_CHOWS:
            return "一色三歩高";
        case ALL_FIVES:
            return "全帯五";
        case TRIPLE_PUNG:
            return "三同刻";
        case THREE_CONCEALED_PUNGS:
            return "三暗刻";
        case SEVEN_PAIRS:
            return "七対";
        case GREATER_HONORS_AND_KNITTED_TILES:
            return "七星不靠";
        case ALL_EVEN_PUNGS:
            return "全双刻";
        case FULL_FLUSH:
            return "清一色";
        case PURE_TRIPLE_CHOW:
            return "一色三同順";
        case PURE_SHIFTED_PUNGS:
            return "一色三節高";
        case UPPER_TILES:
            return "全大";
        case MIDDLE_TILES:
            return "全中";
        case LOWER_TILES:
            return "全小";
        case FOUR_SHIFTED_CHOWS:
            return "一色四歩高";
        case THREE_KONGS:
            return "三槓";
        case ALL_TERMINALS_AND_HONORS:
            return "混幺九";
        case QUADRUPLE_CHOW:
            return "一色四同順";
        case FOUR_PURE_SHIFTED_PUNGS:
            return "一色四節高";
        case ALL_TERMINALS:
            return "清幺九";
        case LITTLE_FOUR_WINDS:
            return "小四喜";
        case LITTLE_THREE_DRAGONS:
            return "小三元";
        case ALL_HONORS:
            return "字一色";
        case FOUR_CONCEALED_PUNGS:
            return "四暗刻";
        case PURE_TERMINAL_CHOWS:
            return "一色双龍会";
        case BIG_FOUR_WINDS:
            return "大四喜";
        case BIG_THREE_DRAGONS:
            return "大三元";
        case ALL_GREEN:
            return "緑一色";
        case NINE_GATES:
            return "九連宝燈";
        case FOUR_KONGS:
            return "四槓";
        case SEVEN_SHIFTED_PAIRS:
            return "連七対";
        case THIRTEEN_ORPHANS:
            return "十三幺";
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 英語名の文字列に変換
     * 
     * @return 変換結果。
     */
    public String toStringUS() {
        String string = "";
        
        for (String splited : name().split("_")) {
            if (splited.equals("ALL")) {
                splited = "A_LL";
            }
            string += splited.substring(0, 1).toUpperCase() + splited.substring(1).toLowerCase() + " ";
        }
        return string;
    }
    
}

/**
 * Yaku.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;



/**
 * 役
 */
public enum Yaku {
    
    // 一飜
    
    /**
     * 立直 (リーチ)
     */
    RICHI,
    
    /**
     * 一発 (イッパツ)
     */
    IPPATSU,
    
    /**
     * 門前清自摸和 (メンゼンツモ)
     */
    TSUMO,
    
    /**
     * 断ヤオ九 (タンヤオ)
     */
    TAN_YAO,
    
    /**
     * 平和 (ピンフ)
     */
    PINFU,
    
    /**
     * 一盃口 (イーペーコー)
     */
    I_PEI_KOU,
    
    /**
     * 役牌 (ヤクハイ、ファンパイ)
     */
    YAKU_HAI,
    
    /**
     * 嶺上開花 (リンシャンカイホウ)
     */
    RIN_SYAN,
    
    /**
     * 槍槓 (チャンカン)
     */
    CHAN_KAN,
    
    /**
     * 海底摸月 (ハイテイ)
     */
    HAI_TEI,
    
    /**
     * 河底撈魚 (ホウテイ)
     */
    HO_TEI,
    
    /**
     * ドラ
     */
    DORA,
    

    // 二飜

    /**
     * 三色同順 (サンショク)
     */
    SAN_SHOKU_DOU_JUN,
    
    /**
     * 一気通貫 (イッツー)
     */
    IKKI_TSU_KAN,
    
    /**
     * 混全帯ヤオ九 (チャンタ)
     */
    CHAN_TA,
    
    /**
     * 七対子 (チートイツ)
     */
    CHI_TOI,
    
    /**
     * 対々和 (トイトイホー)
     */
    TOI_TOI,
    
    /**
     * 三暗刻 (サンアンコー)
     */
    SAN_AN_KOU,
    
    /**
     * 混老頭 (ホンロウトー)
     */
    HON_ROU_TOU,
    
    /**
     * 三色同刻 (サンショクドーコー)
     */
    SHAN_SHOKU_DOU_KOU,
    
    /**
     * 三槓子 (サンカンツ)
     */
    SAN_KAN_TSU,
    
    /**
     * 小三元 (ショウサンゲン)
     */
    SHOU_SAN_GEN,
    
    /**
     * ダブル立直 (ダブルリーチ)
     */
    DOUBLE_RICHI,
    
    
    
    // 三飜

    /**
     * 混一色 (ホンイツ)
     */
    HON_ITSU,
    
    /**
     * 純全帯ヤオ九 (ジュンチャン)
     */
    JUN_CHAN,
    
    /**
     * 二盃口 (リャンペーコー)
     */
    RYAN_PEI_KOU,
    
    
    
    // 六飜
    
    /**
     * 清一色 (チンイツ)
     */
    CHIN_ITSU,
    
    
    
    // 役満
    
    /**
     * 国士無双 (コクシムソウ)
     */
    KOKUSHI,
    
    /**
     * 純正国士無双 (コクシムソウ)
     */
    KOKUSHI_13,
    
    /**
     * 四暗刻 (スーアンコー)
     */
    SU_AN_KOU,
    
    /**
     * 四暗刻単騎 (スーアンコー)
     */
    SU_AN_KOU_TANKI,
    
    /**
     * 大三元 (ダイサンゲン)
     */
    DAI_SAN_GEN,
    
    /**
     * 字一色 (ツーイーソー)
     */
    TSU_I_SOU,
    
    /**
     * 小四喜 (ショウスーシー)
     */
    SHOU_SU_SHI,
    
    /**
     * 大四喜 (ダイスーシー)
     */
    DAI_SU_SHI,
    
    /**
     * 緑一色 (リューイーソー)
     */
    RYU_I_SOU,
    
    /**
     * 清老頭 (チンロートー)
     */
    CHIN_ROU_TOU,
    
    /**
     * 四槓子 (スーカンツ)
     */
    SU_KAN_TSU,
    
    /**
     * 九蓮宝燈 (チューレンポウトー)
     */
    CHU_REN,
    
    /**
     * 純正九蓮宝燈 (チューレンポウトー)
     */
    CHU_REN_09,
    
    /**
     * 天和 (テンホウ)
     */
    TEN_HOU,
    
    /**
     * 地和 (チーホウ)
     */
    CHI_HOU;
    
    
    
    /**
     * 飜数を取得
     * 
     * @return 飜数。役満は13、ダブル役満は26を返す。
     */
    public int getFan() {
        switch (this) {
        case RICHI:
        case IPPATSU:
        case TSUMO:
        case TAN_YAO:
        case PINFU:
        case I_PEI_KOU:
        case YAKU_HAI:
        case RIN_SYAN:
        case CHAN_KAN:
        case HAI_TEI:
        case HO_TEI:
        case DORA:
            return 1;
        case SAN_SHOKU_DOU_JUN:
        case IKKI_TSU_KAN:
        case CHAN_TA:
        case CHI_TOI:
        case TOI_TOI:
        case SAN_AN_KOU:
        case HON_ROU_TOU:
        case SHAN_SHOKU_DOU_KOU:
        case SAN_KAN_TSU:
        case SHOU_SAN_GEN:
        case DOUBLE_RICHI:
            return 2;
        case HON_ITSU:
        case JUN_CHAN:
        case RYAN_PEI_KOU:
            return 3;
        case CHIN_ITSU:
            return 6;
        case KOKUSHI:
        case SU_AN_KOU:
        case DAI_SAN_GEN:
        case TSU_I_SOU:
        case SHOU_SU_SHI:
        case RYU_I_SOU:
        case CHIN_ROU_TOU:
        case SU_KAN_TSU:
        case CHU_REN:
        case TEN_HOU:
        case CHI_HOU:
            return 13;
        case KOKUSHI_13:
        case SU_AN_KOU_TANKI:
        case DAI_SU_SHI:
        case CHU_REN_09:
            return 26;
        default:
            throw new InternalError();
        }
    }
    
    /**
     * 喰い下がるか
     * 
     * @return 判定結果。
     */
    public boolean isCallDown() {
        switch (this) {
        case SAN_SHOKU_DOU_JUN:
        case IKKI_TSU_KAN:
        case CHAN_TA:
        case HON_ITSU:
        case JUN_CHAN:
        case CHIN_ITSU:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * ダブル役満か
     * 
     * @return 判定結果。
     */
    public boolean isDoubleYakuMan() {
        switch (this) {
        case KOKUSHI_13:
        case SU_AN_KOU_TANKI:
        case DAI_SU_SHI:
        case CHU_REN_09:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 面前限定役か
     * 
     * @return 判定結果。
     */
    public boolean isMenZenOnly() {
        switch (this) {
        case RICHI:
        case IPPATSU:
        case TSUMO:
        case PINFU:
        case I_PEI_KOU:
        case CHI_TOI:
        case DOUBLE_RICHI:
        case RYAN_PEI_KOU:
        case KOKUSHI:
        case KOKUSHI_13:
        case SU_AN_KOU:
        case SU_AN_KOU_TANKI:
        case CHU_REN:
        case CHU_REN_09:
        case TEN_HOU:
        case CHI_HOU:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 役満か (ダブル役満を含む)
     * 
     * @return 判定結果。
     */
    public boolean isYakuMan() {
        switch (this) {
        case KOKUSHI:
        case KOKUSHI_13:
        case SU_AN_KOU:
        case SU_AN_KOU_TANKI:
        case DAI_SAN_GEN:
        case TSU_I_SOU:
        case SHOU_SU_SHI:
        case DAI_SU_SHI:
        case RYU_I_SOU:
        case CHIN_ROU_TOU:
        case SU_KAN_TSU:
        case CHU_REN:
        case CHU_REN_09:
        case TEN_HOU:
        case CHI_HOU:
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
        case RICHI:
            return "立直";
        case IPPATSU:
            return "一発";
        case TSUMO:
            return "門前清自摸和";
        case TAN_YAO:
            return "断ヤオ九";
        case PINFU:
            return "平和";
        case I_PEI_KOU:
            return "一盃口";
        case YAKU_HAI:
            return "役牌";
        case RIN_SYAN:
            return "嶺上開花";
        case CHAN_KAN:
            return "槍槓";
        case HAI_TEI:
            return "海底摸月";
        case HO_TEI:
            return "河底撈魚";
        case DORA:
            return "ドラ";
        case SAN_SHOKU_DOU_JUN:
            return "三色同順";
        case IKKI_TSU_KAN:
            return "一気通貫";
        case CHAN_TA:
            return "混全帯ヤオ九";
        case CHI_TOI:
            return "七対子";
        case TOI_TOI:
            return "対々和";
        case SAN_AN_KOU:
            return "三暗刻";
        case HON_ROU_TOU:
            return "混老頭";
        case SHAN_SHOKU_DOU_KOU:
            return "三色同刻";
        case SAN_KAN_TSU:
            return "三槓子";
        case SHOU_SAN_GEN:
            return "小三元";
        case DOUBLE_RICHI:
            return "ダブル立直";
        case HON_ITSU:
            return "混一色";
        case JUN_CHAN:
            return "純全帯ヤオ九";
        case RYAN_PEI_KOU:
            return "二盃口";
        case CHIN_ITSU:
            return "清一色";
        case KOKUSHI:
            return "国士無双";
        case KOKUSHI_13:
            return "純正国士無双";
        case SU_AN_KOU:
            return "四暗刻";
        case SU_AN_KOU_TANKI:
            return "四暗刻単騎";
        case DAI_SAN_GEN:
            return "大三元";
        case TSU_I_SOU:
            return "字一色";
        case SHOU_SU_SHI:
            return "小四喜";
        case DAI_SU_SHI:
            return "大四喜";
        case RYU_I_SOU:
            return "緑一色";
        case CHIN_ROU_TOU:
            return "清老頭";
        case SU_KAN_TSU:
            return "四槓子";
        case CHU_REN:
            return "九蓮宝燈";
        case CHU_REN_09:
            return "純正九蓮宝燈";
        case TEN_HOU:
            return "天和";
        case CHI_HOU:
            return "地和";
        default:
            throw new InternalError();
        }
    }
    
}

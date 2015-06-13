/**
 * CompleteType.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;



/**
 * 和了タイプ
 */
public enum CompleteType {
    
    RON_MENZEN,
    RON_NOT_MENZEN,
    RON_MENZEN_HO_TEI,
    RON_NOT_MENZEN_HO_TEI,
    RON_MENZEN_CHAN_KAN,
    RON_NOT_MENZEN_CHAN_KAN,
    TSUMO_MENZEN,
    TSUMO_NOT_MENZEN,
    TSUMO_MENZEN_HAI_TEI,
    TSUMO_NOT_MENZEN_HAI_TEI,
    TSUMO_MENZEN_RIN_SYAN,
    TSUMO_NOT_MENZEN_RIN_SYAN,
    UNKNOWN;
    
    
    
    /**
     * 面前和了か
     * 
     * @return 判定結果。
     */
    public boolean isMenZen() {
        switch (this) {
        case RON_MENZEN:
        case RON_MENZEN_HO_TEI:
        case RON_MENZEN_CHAN_KAN:
        case TSUMO_MENZEN:
        case TSUMO_MENZEN_HAI_TEI:
        case TSUMO_MENZEN_RIN_SYAN:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * ロン和了か
     * 
     * @return 判定結果。
     */
    public boolean isRon() {
        switch (this) {
        case RON_MENZEN:
        case RON_NOT_MENZEN:
        case RON_MENZEN_HO_TEI:
        case RON_NOT_MENZEN_HO_TEI:
        case RON_MENZEN_CHAN_KAN:
        case RON_NOT_MENZEN_CHAN_KAN:
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
        case RON_MENZEN:
            return "面前ロン";
        case RON_NOT_MENZEN:
            return "ロン";
        case RON_MENZEN_HO_TEI:
            return "面前 河底撈魚";
        case RON_NOT_MENZEN_HO_TEI:
            return "河底撈魚";
        case RON_MENZEN_CHAN_KAN:
            return "面前 槍槓";
        case RON_NOT_MENZEN_CHAN_KAN:
            return "槍槓";
        case TSUMO_MENZEN:
            return "面前ツモ";
        case TSUMO_NOT_MENZEN:
            return "ツモ";
        case TSUMO_MENZEN_HAI_TEI:
            return "面前 海底摸月";
        case TSUMO_NOT_MENZEN_HAI_TEI:
            return "海底摸月";
        case TSUMO_MENZEN_RIN_SYAN:
            return "面前 嶺上開花";
        case TSUMO_NOT_MENZEN_RIN_SYAN:
            return "嶺上開花";
        case UNKNOWN:
            return "不明";
        default:
            throw new InternalError();
        }
    }
    
}


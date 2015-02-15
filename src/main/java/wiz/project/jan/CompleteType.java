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
    TSUMO_MENZEN,
    TSUMO_NOT_MENZEN,
    UNKNOWN;
    
    
    
    /**
     * 面前和了か
     * 
     * @return 判定結果。
     */
    public boolean isMenZen() {
        switch (this) {
        case RON_MENZEN:
        case TSUMO_MENZEN:
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
        case TSUMO_MENZEN:
            return "面前ツモ";
        case TSUMO_NOT_MENZEN:
            return "ツモ";
        case UNKNOWN:
            return "不明";
        default:
            throw new InternalError();
        }
    }
    
}


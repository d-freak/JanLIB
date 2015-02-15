/**
 * MenTsuType.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;



/**
 * 面子タイプ
 */
public enum MenTsuType {
    
    STANDARD_SHUN_TSU,
    STANDARD_KOU_TSU,
    CHI,
    PON,
    KAN_LIGHT,
    KAN_DARK,
    UNKNOWN;
    
    
    
    /**
     * 副露面子か
     * 
     * @return 判定結果。
     */
    public boolean isCalled() {
        switch (this) {
        case CHI:
        case PON:
        case KAN_LIGHT:
        case KAN_DARK:
            return true;
        default:
            return false;
        }
    }
    
    /**
     * 刻子か
     * 
     * @return 判定結果。
     */
    public boolean isKouTsu() {
        return !isShunTsu();
    }
    
    /**
     * 順子か
     * 
     * @return 判定結果。
     */
    public boolean isShunTsu() {
        switch (this) {
        case STANDARD_SHUN_TSU:
        case CHI:
            return true;
        default:
            return false;
        }
    }
    
}

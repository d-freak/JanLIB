/**
 * JanPaiType.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.Arrays;



/**
 * 牌タイプ
 */
public enum JanPaiType {
    
    MAN,
    PIN,
    SOU,
    JI_WIND,
    JI_DORAGON;
    
    
    
    public JanPai getU() {
        switch (this) {
        case MAN:
            return JanPai.MAN_5;
        case PIN:
            return JanPai.PIN_5;
        case SOU:
            return JanPai.SOU_5;
        default:
            return JanPai.HAKU;
        }
    }
    
}


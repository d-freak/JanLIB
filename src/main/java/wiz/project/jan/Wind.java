/**
 * Wind.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;



/**
 * 風
 */
public enum Wind {
    
    TON,
    NAN,
    SHA,
    PEI;
    
    
    
    /**
     * 次の風を取得
     * 
     * @return 次の風。
     */
    public Wind getNext() {
    	switch (this) {
        case TON:
            return NAN;
        case NAN:
            return SHA;
        case SHA:
            return PEI;
        case PEI:
            return TON;
        default:
            throw new InternalError();
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
        case TON:
            return "東";
        case NAN:
            return "南";
        case SHA:
            return "西";
        case PEI:
            return "北";
        default:
            throw new InternalError();
        }
    }
    
}


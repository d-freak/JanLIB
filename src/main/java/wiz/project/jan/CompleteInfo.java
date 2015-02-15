/**
 * CompleteInfo.java
 * 
 * @Author
 *   Yuki Kawata
 */

package wiz.project.jan;

import java.util.ArrayList;
import java.util.List;



/**
 * 和了情報 (不変オブジェクト)
 */
public final class CompleteInfo {
    
    /**
     * コンストラクタ
     * 
     * @param yakuList 役リスト。
     * @param fu 符。
     * @param type 和了タイプ。
     */
    public CompleteInfo(final List<Yaku> yakuList, final int fu, final CompleteType type) {
        setYakuList(yakuList);
        setFu(fu);
        setCompleteType(type);
    }
    
    /**
     * コピーコンストラクタ
     * 
     * @param source 複製元。
     */
    public CompleteInfo(final CompleteInfo source) {
        if (source != null) {
            _yakuList = deepCopyList(source._yakuList);
            _fu = source._fu;
            _completeType = source._completeType;
        }
    }
    
    
    
    /**
     * 等価なオブジェクトか
     * 
     * @param target 比較対象。
     * @return 比較結果。
     */
    @Override
    public boolean equals(final Object target) {
        if (this == target) {
            return true;
        }
        if (target == null) {
            return false;
        }
        if (!(target instanceof CompleteInfo)) {
            return false;
        }
        
        final CompleteInfo targetInfo = (CompleteInfo)target;
        return _yakuList.equals(targetInfo._yakuList) &&
               (_fu == targetInfo._fu) &&
               (_completeType == targetInfo._completeType);
    }
    
    /**
     * 和了タイプを取得
     * 
     * @return 和了タイプ。
     */
    public CompleteType getCompleteType() {
        return _completeType;
    }
    
    /**
     * 符を取得
     * 
     * @return 符。
     */
    public int getFu() {
        return _fu;
    }
    
    /**
     * 役リストを取得
     * 
     * @return 役リスト。
     */
    public List<Yaku> getYakuList() {
        return deepCopyList(_yakuList);
    }
    
    /**
     * ハッシュコードを取得
     * 
     * @return ハッシュコード。
     */
    @Override
    public int hashCode() {
        return _yakuList.hashCode() + _fu + _completeType.hashCode();
    }
    
    /**
     * 文字列に変換
     * 
     * @return 変換結果。
     */
    @Override
    public String toString() {
        return _yakuList + " (" + _fu + "符)";
    }
    
    
    
    /**
     * リストをディープコピー
     * 
     * @param sourceList 複製元リスト。
     * @return 複製結果。
     */
    private <E> List<E> deepCopyList(final List<E> sourceList) {
        return new ArrayList<E>(sourceList);
    }
    
    /**
     * 和了タイプを設定
     * 
     * @param type 和了タイプ。
     */
    private void setCompleteType(final CompleteType type) {
        if (type != null) {
            _completeType = type;
        }
        else {
            _completeType = CompleteType.UNKNOWN;
        }
    }
    
    /**
     * 符を設定
     * 
     * @param fu 符。
     */
    private void setFu(final int fu) {
        if (fu > 0) {
            _fu = fu;
        }
        else {
            _fu = 0;
        }
    }
    
    /**
     * 役リストを設定
     * 
     * @param yakuList 役リスト。
     */
    private void setYakuList(final List<Yaku> yakuList) {
        if (yakuList != null) {
            _yakuList = deepCopyList(yakuList);
        }
        else {
            _yakuList.clear();
        }
    }
    
    
    
    /**
     * 役リスト
     */
    private List<Yaku> _yakuList = new ArrayList<Yaku>();
    
    /**
     * 符
     */
    private int _fu = 0;
    
    /**
     * 和了タイプ
     */
    private CompleteType _completeType = CompleteType.UNKNOWN;
    
}


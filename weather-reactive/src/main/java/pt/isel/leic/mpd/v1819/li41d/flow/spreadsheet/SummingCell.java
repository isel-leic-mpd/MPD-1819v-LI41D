package pt.isel.leic.mpd.v1819.li41d.flow.spreadsheet;

public class SummingCell extends SimpleCell {
    private int left;
    private int right;

    public SummingCell(String name) {
        super(name);
    }

    public void setLeft(int leftValue){
        this.left = leftValue;
        onNext(left + right);
    }

    public void setRight(int rightValue){
        this.right = rightValue;
        onNext(left + right);
    }
}

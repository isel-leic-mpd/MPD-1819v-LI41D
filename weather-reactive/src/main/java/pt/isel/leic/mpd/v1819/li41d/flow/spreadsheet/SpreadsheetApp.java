package pt.isel.leic.mpd.v1819.li41d.flow.spreadsheet;



public class SpreadsheetApp {

    public static void main(String[] args) {
        // Preparation
        SimpleCell c1 = new SimpleCell("C1");
        SummingCell c3 = new SummingCell("C3");
        SimpleCell c2 = new SimpleCell("C2");
        SimpleCell c4 = new SimpleCell("C4");
        SummingCell c5 = new SummingCell("C5");

        // c3 = c1 + c2
        c1.subscribe(c3::setLeft);
        c2.subscribe(c3::setRight);


        // c5 = c3 + c4
        c3.subscribe(c5::setLeft);
        c4.subscribe(c5::setRight);


        // Reaction
        c1.onNext(10);
        c2.onNext(20);

        c4.onNext(1);
        c4.onNext(5);



    }
}

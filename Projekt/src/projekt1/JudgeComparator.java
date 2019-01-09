package projekt1;

import java.util.Comparator;

public class JudgeComparator implements Comparator<Judge> {
    @Override
    public int compare(Judge o1, Judge o2) {
        if(o2.numberOfCases()==o1.numberOfCases()) return 0;
        if(o2.numberOfCases()>o1.numberOfCases()) return 1;
        else return (-1);
    }
}

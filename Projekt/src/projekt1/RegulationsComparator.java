package projekt1;

import java.util.Comparator;

public class RegulationsComparator implements Comparator<Regulation> {
    @Override
    public int compare(Regulation o1, Regulation o2) {
        if(o1.regulationSize()==o2.regulationSize()) return 0;
        if(o1.regulationSize()>o2.regulationSize()) return -1;
        else return 1;
    }
}

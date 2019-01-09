package projekt1;

import java.util.*;

public class Judges {
    private Map<String, Judge> judges = new HashMap<>();
    private LinkedList<Judge> sorted=new LinkedList<>();
    private boolean ifSorted = false;

    public void addJudge(Judge judge, Judgement judgement) {
        if (judges.containsKey(judge.getName())) {
            judges.get(judge.getName()).assignJudgement2Judge(judgement);
        } else {
            judges.put(judge.getName(), judge);
        }
    }
private void sort(){
sorted.addAll(judges.values());
Collections.sort(sorted, new JudgeComparator());
ifSorted=true;
}
    void top10judges() {
        if(!ifSorted) sort();
        for(int i=1;i<11;i++){
            System.out.print(i+"."+sorted.get(i-1).getName()+": "+sorted.get(i-1).numberOfCases()+" spraw\n");
        }
    }
}

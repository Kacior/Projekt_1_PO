package projekt1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Regulations {
   private  List<Regulation> reg = new ArrayList<>();
   private boolean sorted = false;
    public void addRegulation(Regulation regulation, Judgement judgement){
        if(reg.contains(regulation)){
reg.get(reg.indexOf(regulation)).assignJudgement2Regulation(judgement);
        }
        else {
            reg.add(regulation);
        }
    }
    public void sortRegulations(){
        sorted=true;
        Collections.sort(reg, new RegulationsComparator());
    }
    public void top10regulations(){
        if(!sorted) sortRegulations();
        for(int i=1;i<11;i++){
            System.out.print(i+"."+reg.get(i-1).getTitle()+reg.get(i-1).regulationSize()+" \n");
        }
    }
}

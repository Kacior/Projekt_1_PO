package projekt1;

import java.util.*;

public class Judgements {
    private Map<String, Judgement> casesMap = new HashMap<>();

    public void addJudgement(Judgement judgement) {
        casesMap.put(judgement.signature(), judgement);
    }

    void printDateStatistics() {
        List<Judgement> cases = new LinkedList<>(casesMap.values());
        int[] months = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 0; i < cases.size(); i++) {
            months[cases.get(i).getDate().getMonth()]++;
        }
        for (int i = 0; i < months.length; i++) {
            System.out.print("W miesiacu: " + i + 1 + " wydano tyle orzeczen: " + months[i] + "\n");
        }
    }

    void printCourtStatistics() throws IllegalArgumentException {
        List<Judgement> cases = new LinkedList<>(casesMap.values());
        int[] courtTypes = {0, 0, 0, 0, 0};
        for (int i = 0; i < cases.size(); i++) {
            switch (cases.get(i).getCourtType()) {
                case "COMMON":
                    courtTypes[0]++;
                    break;
                case "SUPREME":
                    courtTypes[1]++;
                    break;
                case "NATIONAL_APPEAL_CHAMBER":
                    courtTypes[2]++;
                    break;
                case "CONSTITUTIONAL_TRIBUNAL":
                    courtTypes[3]++;
                    break;
                case "ADMINISTRATIVE":
                    courtTypes[4]++;
                    break;
                default:
                    throw new IllegalArgumentException("niepop arg " + cases.get(i).getCourtType());
            }
        }
        System.out.print("COMMON: " + courtTypes[0] + " CASES\n");
        System.out.print("SUPREME: " + courtTypes[1] + " CASES\n");
        System.out.print("NATIONAL_APPEAL_CHAMBER: " + courtTypes[2] + " CASES\n");
        System.out.print("CONSTITUTIONAL_TRIBUNAL: " + courtTypes[3] + " CASES\n");
        System.out.print("ADMINISTRATIVE: " + courtTypes[4] + " CASES");
    }

    void printStatistics() {
        List<Judgement> cases = new LinkedList<>(casesMap.values());
        for (int i = 0; i < cases.size(); i++) {
            System.out.println("Sprawa: " + cases.get(i).signature() + " Liczba sędziów: " + cases.get(i).judgesPerCase() + "\n");
        }
    }

    public void showJudgement(String signature) {
        casesMap.get(signature).showContent();
    }

    public void showRubrum(String signature) {
        casesMap.get(signature).rubrum();
    }
}

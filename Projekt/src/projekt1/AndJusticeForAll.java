package projekt1;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;


public class AndJusticeForAll {
    public static void main(String[] args) {
        try {
            final File folder = new File("json");
            Judgements cases = new Judgements();//inicjalizuję zbiór orzeczeń
            Judges judgesList = new Judges();
            Regulations regList = new Regulations();
            new JSONReader().loadData(folder, cases, judgesList, regList);
            final File folder2 = new File("cbosa");
            new HTMLReader().loadData(folder2, cases, judgesList, regList);
            String help = "List of commands:\nrubrum\ncontent\njudge\njudges\nmonths\ncourts\nregulations\njury\n";
            jline.console.ConsoleReader console = new jline.console.ConsoleReader();
            console.setPrompt("Enter command: >");
            String line;
            String innerLine;
            while (!(line = console.readLine()).equals("close")) {
                if (!line.contains(" ")) {
                    switch (line) {
                        case "help":
                            System.out.print(help);
                            break;
                        case "months":
                            cases.printDateStatistics();
                            break;
                        case "courts":
                            cases.printCourtStatistics();
                            break;
                        case "regulations":
                            regList.top10regulations();
                            break;
                        case "content":
                            System.out.print("Enter signature: ");
                            innerLine = console.readLine();
                            cases.showJudgement(innerLine);
                            break;
                        case "jury":
                            judgesList.top10judges();
                            break;
                        case "rubrum":
                            System.out.print("Enter signature/s (format: signature1.signature2...): ");
                            innerLine = console.readLine();
                            String command;
                            int index=0;
                            while(index<innerLine.length()){
                                command="";
                                while(index<innerLine.length() && innerLine.charAt(index)!='.'){
                                    command+=innerLine.charAt(index++);
                                }
                                cases.showRubrum(command);
                                System.out.print("\n");
                                index++;
                            }
                            break;
                        default:
                            System.out.println("Command does not exist. Type 'help' for more.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



import java.util.ArrayList;
import java.util.Collections;
import java.util.IllegalFormatException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class tail  {
    ArrayList<String> form = new ArrayList<String>();
    ArrayList<String> files = new ArrayList<String>();

    private void fromString(){
        System.out.println("Hello! This program can return last lines and symbols.\n Please, write a command:");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        Collections.addAll(form, string.split(" "));
    }
    public void tail() throws Exception{
        Boolean c = false;
        Boolean n = false;
        Boolean o = false;
        Integer cNum = 0;
        Integer nNum = 0;
        String oName = "output.txt";
        Pattern pattern = Pattern.compile("(([a-zA-z0-9|_|-]+).(txt|doc))");
        Matcher m;
        String element;
        //до 6, потому что флагов и их параметров, не считая слова tail, может быть максимум 6
        for (int i = 1; i < form.size(); i++){
            element = form.get(i);
            switch (element){
                case "-c":{
                    c = true;
                    cNum = Integer.parseInt(form.get(i+1));
                    ++i;
                }
                case "-n":{
                    n = true;
                    nNum = Integer.parseInt(form.get(i+1));
                    ++i;
            }
            case "-o":{
                o = true;
                oName = form.get(i+1);
                ++i;
            }
            }
            m = pattern.matcher(element);
            if(m.matches()) files.add(element);
        }
        Boolean returnTenLastLines = false;
        if (c && n) throw new IllegalArgumentException(); else
            if (!c && !n) returnTenLastLines = true;
        String fileNameBuffer;
        StringBuilder tempSymbolBuffer = new StringBuilder();
        ArrayList<String> linesBuffer = new ArrayList<String>();
        int i = 0;
        //FileReader fr = new FileReader("")
        if (o) {
            //try {
                FileWriter fw = new FileWriter(oName, false);
            //}catch (java.io.IOException fg){
                System.out.println("Wrong output file name");
            //}
            while (!files.isEmpty()){
                fileNameBuffer = files.get(0);
                //FileReader fr = new FileReader(fileNameBuffer);
                //Scanner sc = new Scanner(fr);
                //for ()
            }




        }

        while (!files.isEmpty()){
            fileNameBuffer = files.get(0);
            if (o)
            files.remove(0);
        }
    }
    ArrayList<String> readLines(String fileName) {

        ArrayList<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                lineList.add(s);
            }
        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
        return lineList;
    }

    void readSymbols(String fileName) {
        try(BufferedReader br = new BufferedReader (new FileReader(fileName)))
        {
            // чтение посимвольно
            int c;
            while((c=br.read())!=-1){

                System.out.print((char)c);
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}

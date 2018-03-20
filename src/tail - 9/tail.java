import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class tail {
    private ArrayList<String> form = new ArrayList<String>();

    public void run(String[] args) {
        fromString();
        get_tail();
    }

    private void fromString() {
        System.out.println("Hello! This program can return last lines and symbols.\n Please, write a command:");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        Collections.addAll(form, string.split(" "));
    }

    private void get_tail(){
        ArrayList<String> files = new ArrayList<String>(); //то, куда кидаю перечень имен файлов с текстом
        Boolean c = false;
        Boolean n = false;
        Boolean outputFileExists = false;
        Integer cNum = 0;
        Integer nNum = 0;
        String oName = "output.txt";
        Pattern pattern = Pattern.compile("(([a-zA-z0-9|_|-]+).(txt|doc))");
        Matcher m;
        String element;

        for (int i = 0; i < form.size(); i++) {
            element = form.get(i);
            switch (element) {
                case "-c": {
                    c = true;
                    cNum = Integer.parseInt(form.get(i + 1));
                    ++i;
                }
                case "-n": {
                    n = true;
                    nNum = Integer.parseInt(form.get(i + 1));
                    ++i;
                }
                case "-o": {
                    outputFileExists = true;
                    oName = form.get(i + 1);
                    ++i;
                }
            }
            m = pattern.matcher(element);
            if (m.matches()) files.add(element);
        }

        Boolean returnTenLastLines = false;
        if (c && n) throw new IllegalArgumentException();
        else if (!c && !n) returnTenLastLines = true;

        ArrayList<String> lineList = new ArrayList<>();

        for (int i = 0; i < files.size(); i++) {

            if (n) {
                lineList = readLines(files.get(i), nNum);
            } else if (c) {
                lineList = readSymbols(files.get(i), cNum);
            } else if (returnTenLastLines) {
                lineList = readLines(files.get(i), 10);
            }

            if (outputFileExists) {
                try {
                    update(oName, lineList);
                } catch (FileNotFoundException e) {
                    System.out.println(oName + " not found");
                    for (String line : lineList) {
                        System.out.println(line + System.lineSeparator());
                    }
                }

            } else {
                for (String line : lineList) {
                    System.out.println(line + System.lineSeparator());
                }
            }
        }
    }

    ArrayList<String> readLines(String fileName, Integer nNum) {
        ArrayList<String> lineList = new ArrayList<>();
        ArrayList<String> returnList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) { //наткнулся на try-with-resources
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                lineList.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage()); //должно выводить сообщение
        }
        for (int i = lineList.size() - 1; i >= nNum; i--) {
            returnList.add(lineList.get(i));
        }
        return returnList; // вернётся лист с последними nNum строками для флага -n
    }

    ArrayList<String> readSymbols(String fileName, Integer cNum) {
        ArrayList<String> lineList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            // чтение посимвольно
            String c;
            while ((c = br.readLine()) != null) {
                lineList.add(c);
                // возвращая n-ое кол-во последних символов, понадобятся разделители
                //lineList.add(System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        int symbols = 0; //счётчик
        ArrayList<String> linesWithLastSymbols = new ArrayList<>(); //строки, которые содержат те последние символы
        String linelistGet_i; //создана, чтобы обращаться к lineList.get(i)

        for (int i = lineList.size() - 1; i > 0; i--) { //читаем строки с конца
            linelistGet_i = lineList.get(i); // одна из последних строк, содержащих cNum последних символов
            symbols += linelistGet_i.length(); // пополняем счётчик
            linesWithLastSymbols.add(linelistGet_i);

            if (symbols >= cNum) {
                if (symbols != cNum) {
                    int leftSymbolsDelete = symbols - cNum;
                    String substring = linelistGet_i.substring(leftSymbolsDelete, linelistGet_i.length());
                    linesWithLastSymbols.set(linesWithLastSymbols.size() - 1, substring);
                }
                break;
            }
        }
        return linesWithLastSymbols;
    }

    public static String read(String fileName) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        exists(fileName);

        try {
            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e); //чтобы надолго не зависал // а так требует IOException всегда
        }
        return sb.toString();
    }

    private void update(String nameFile, ArrayList<String> newText) throws FileNotFoundException {
        exists(nameFile);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(nameFile);
        sb.append(oldFile);
        for (String line : newText) {
            sb.append(line);
            sb.append(System.lineSeparator()); /*я как-то набрёл на этот метод ,и мне он понравился.
            Как думаете, могу ли я всё время его использовать вместо append("\n") ?
            */
        }
        sb.append(newText);
        write(nameFile, sb.toString());
    }

    private void write(String fileName, String text) {
        File file = new File(fileName);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                out.print(text);
            }
        } catch (IOException e) { //от меня просто железно потребовалось ловить IOException
            System.out.println("Something has got wrong while writing");
        }
    }

    private static void exists(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
}

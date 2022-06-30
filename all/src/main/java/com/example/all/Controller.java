package com.example.all;

import com.example.all.Class.Class_Driver;
import com.example.all.Class.Class_Passenger;
import com.example.all.Files.Filetxt;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Controller {
//Driver================================================================================================================
        //Out-----------------------------------------------------------------------------------------------------------
        @FXML protected void
        OutDriverFile()
        throws FileNotFoundException{
        CountAddD = 0;CountRedactD = 0;AddD = 0;RedactD = 0;
        Scanner scanner = new Scanner(new FileReader(FileDriver));
        AfD.setText("Out");    int i = 1;    String Text = "";
        while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";  i++;}
        TAfD.setText(Text);
        ID.setText("Output driver file");
    } //Вивід файла Водія OutD
        //Add-----------------------------------------------------------------------------------------------------------
        @FXML protected void
        AddtoFileDriver()
        {
            RedactD=0;CountRedactD=0;
            CountAddD = 1;AfD.setText("Adding");
            ID.setText("Edit name driver"); TFfD.setPromptText("Name");
        }//Вписати Водія до файлу  AddD
        //Redact--------------------------------------------------------------------------------------------------------
        @FXML protected void
        RedactFileDriver()
        throws FileNotFoundException {
            CountAddD = 0;AddD=0;
            Scanner scanner = new Scanner(new FileReader(FileDriver));
            AfD.setText("Redact");CountRedactD = 1;int i = 1; String Text = "";

            while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";i++;}
            TAfD.setText(Text);
            TFfD.setPromptText("Edit line");
            ID.setText("Edit line what you\nwant redact");
        }//Редагувати конкретну строку в файлі Водія RedactD
        //Delete--------------------------------------------------------------------------------------------------------
        @FXML protected void
        DeleteFileDriver() throws FileNotFoundException {
            //зделать через кнопку
            CountAddD = 0;CountRedactD = 0;CountDeleteD = 1;
            AfD.setText("Delete");
            Scanner scanner = new Scanner(new FileReader(FileDriver));
            int i = 1;    String Text = "";
            while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";  i++;}
            TAfD.setText(Text);
            ID.setText("Edit line what\nyou want delete");
            TAfD.setPromptText("Edit Line");

        }
        //Sort----------------------------------------------------------------------------------------------------------
        @FXML protected  void
        SortFileDriver()
        throws IOException {
            CountRedactD = 0;CountAddD = 0;RedactP=0;AddD=0;
            AfD.setText("𝐒𝐨𝐫𝐭");
            ArrayList<String> Line = new ArrayList<>();

            try (BufferedReader r = new BufferedReader(new FileReader(FileDriver))) {String data;
                while ((data = r.readLine()) != null) {Line.add(data);}}
            catch (IOException e) {throw new RuntimeException(e);}

            Collections.sort(Line);

            try (FileWriter writer = new FileWriter(FileDriver, false)){
                for (String data : Line) {writer.write(data + "\n");}}
        }
        //Search--------------------------------------------------------------------------------------------------------
        @FXML protected void
        SearchD(){
            CountDeleteD=0;CountRedactD=0;CountAddD=0;
            AddD=0;RedactD=0;SearchD = 1;

            ID.setText("Edit what you want find");TFfD.setPromptText("Edit text");
            AfD.setText("Search");
        }
        //Enter keyboard------------------------------------------------------------------------------------------------
        @FXML protected void
        PressEnterD
        (KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER){

            Class_Driver driver = new Class_Driver();
            StringBuilder sb = new StringBuilder();
            Scanner scanner = new Scanner(new FileReader(FileDriver));
            StringBuilder newText = new StringBuilder();

            if (CountAddD    == 1){
                AddD ++ ;
                if (AddD == 1) {
                    driver.setName(TFfD.getText());
                    FCD = "Ім'я:" + driver.getName()+";";

                    TFfD.setText("");
                    AfD.setText("𝐒𝐮𝐫𝐧𝐚𝐦𝐞");ID.setText("Edit Surname");TFfD.setPromptText("Surname");
                }
                if (AddD == 2) {
                    driver.setSurname(TFfD.getText());
                    FCD += " Прізвище:" + driver.getSurname()+";";

                    TFfD.setText("");
                    AfD.setText("𝐒𝐞𝐜𝐨𝐧𝐝 𝐒𝐮𝐫𝐧𝐚𝐦𝐞");ID.setText("Edit Second Surname");TFfD.setPromptText("Second Surname");

                }
                if (AddD == 3) {
                    driver.setSecondSurname(TFfD.getText());
                    FCD += " Побатькові:" + driver.getSecondSurname()+";";

                    TFfD.setText("");
                    AfD.setText("𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐭𝐫𝐢𝐩𝐬");ID.setText("Edit Number of trips \n(Only numbers!)");TFfD.setPromptText("Number trips");
                }
                if (AddD == 4) {
                    try {driver.setCount(Integer.parseInt(TFfD.getText()));}
                    catch (NumberFormatException ex){
                        System.out.println("Error Add -> Set Count trips - 0");
                        driver.setCount(Integer.parseInt("0"));}
                    FCD += " Кількість-поїздок:" + driver.getCount()+";";
                    TFfD.setText("");
                    AfD.setText("𝐑𝐚𝐭𝐢𝐧𝐠");ID.setText("Edit Rating driver");TFfD.setPromptText("Rating");
                }
                if (AddD == 5) {
                    try {driver.setRating(Integer.parseInt(TFfD.getText()));}
                    catch (NumberFormatException ex){
                        System.out.println("Error Add -> Set Rating - 0");
                        driver.setRating(Integer.parseInt("0"));
                    }
                    FCD += " Рейтинг:" + driver.getRating()+";\n";

                    AfD.setText("");
                    TAfD.setText(FCD);

                    try (FileWriter writer = new FileWriter(FileDriver, true)) {
                        writer.write(FCD);
                    } catch (IOException e) {
                        TAfD.setText("\nError");
                    }
                    FCD = "";
                    TFfD.setText("");
                    AfD.setText("𝐀𝐜𝐭𝐢𝐨𝐧");
                }
            }/*  Add  */
            if (CountRedactD == 1){
                RedactD ++;
                if (RedactD == 1) {
                    try {line = Integer.parseInt(TFfD.getText());}
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Error Redact");
                        RedactD = 0;
                        AfD.setText("𝐀𝐜𝐭𝐢𝐨𝐧");
                        FCD = "";
                    }
                    TFfD.setText("");
                    TFfD.setPromptText("𝐍𝐚𝐦𝐞");
                }
                if (RedactD == 2) {
                    driver.setName(TFfD.getText());
                    FCD = "Ім'я:" + driver.getName();

                    TFfD.setText("");
                    AfD.setText("𝐒𝐮𝐫𝐧𝐚𝐦𝐞");
                }
                if (RedactD == 3) {
                    driver.setSurname(TFfD.getText());
                    FCD += " Прізвище:" + driver.getSurname();

                    TFfD.setText("");
                    AfD.setText("𝐒𝐞𝐜𝐨𝐧𝐝 𝐒𝐮𝐫𝐧𝐚𝐦𝐞");
                }
                if (RedactD == 4) {
                    driver.setSecondSurname(TFfD.getText());
                    FCD += " Побатькові:" + driver.getSecondSurname();

                    TFfD.setText("");
                    AfD.setText("𝐍𝐮𝐦𝐛𝐞𝐫 𝐨𝐟 𝐭𝐫𝐢𝐩𝐬");
                }
                if (RedactD == 5) {
                    try {driver.setCount(Integer.parseInt(TFfD.getText()));}
                    catch (NumberFormatException ex){
                        System.out.println("Error Add -> Set Count trips - 0");
                        driver.setCount(Integer.parseInt("0"));
                    }
                    FCD += " Кількість-поїздок:" + driver.getCount();

                    TFfD.setText("");
                    AfD.setText("𝐑𝐚𝐭𝐢𝐧𝐠");
                }
                if (RedactD == 6) {
                    try {driver.setRating(Integer.parseInt(TFfD.getText()));}
                    catch (NumberFormatException ex){
                        System.out.println("Error Add -> Set Rating - 0");
                        driver.setCount(0);
                    }

                    FCD += " Рейтинг:" + driver.getRating();
                    TFfD.setText("");


                    while (scanner.hasNext()){sb.append(scanner.nextLine()).append("\n");}
                    scanner.close();

                    String[] textArray = sb.toString().split("\n");
                    textArray[line - 1] = FCD;

                    for (String st : textArray){
                        newText.append(st).append("\n");
                    }
                    File newFile = new File(FileDriver);
                    newFile.delete();
                    newFile.createNewFile();

                    FileWriter fileWriter = new FileWriter(newFile);
                    fileWriter.write(newText.toString());
                    fileWriter.close();

                    FCD = "";
                    AfD.setText("𝐀𝐜𝐭𝐢𝐨𝐧");

                    Scanner sc = new Scanner(new FileReader(FileDriver));
                    int i = 1;
                    String Text = "";
                    while (sc.hasNextLine()) {
                        Text = Text + i + " - " + sc.nextLine() + "\n";
                        i++;
                    }
                    TAfD.setText(Text);
                }
            }/*Redact */
            if (CountDeleteD == 1){
                line = 0;
                try {line = Integer.parseInt(TFfD.getText());}
                catch (NumberFormatException ex){System.out.println("Error Delete");}
                FileReader fileReader = null;

                try {fileReader = new FileReader(FileDriver);}
                catch (FileNotFoundException e) {throw new RuntimeException(e);}

                Scanner scanner1 = new Scanner(fileReader);
                String[] lineArray = new String[100];
                int i = 0;
                while (scanner1.hasNextLine()) {
                    lineArray[i] = scanner1.nextLine();
                    i++;
                }
                scanner1.close();
                FileWriter fileWriter = null;
                try {
                    fileWriter = new FileWriter(FileDriver);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                for (int j = 0; j < i; j++) {
                    if (j != line - 1) {
                        fileWriter.write(lineArray[j] + "\n");
                    }
                }
                fileWriter.close();

                Scanner sc = new Scanner(new FileReader(FileDriver));
                int j = 1;
                String Text = "";
                while (sc.hasNextLine()) {
                    Text = Text + j + " - " + sc.nextLine() + "\n";
                    j++;
                }
                TAfD.setText(Text);
                TFfD.setText("");
            }/*Delete*/
            if (SearchD      == 1){
                String subject = TFfD.getText();TFfD.setText("");
                try{
                    FileReader fileReader = new FileReader(FileDriver);
                    Scanner sc = new Scanner(fileReader);
                    while (sc.hasNextLine()){
                        String line = sc.nextLine();
                        if (line.contains(subject)){
                            TAfD.setText(line);
                        }
                    }
                }catch (IOException e){
                    System.out.println("Error Search");
                }
            }/*Search*/
        }
    }

//Passengers============================================================================================================

    //Out---------------------------------------------------------------------------------------------------------------
    @FXML protected void
    OutPassengerFile()
    throws FileNotFoundException{
        CountAddP = 0;CountRedactP = 0;AddP=0;RedactD=0;
        Scanner scanner = new Scanner(new FileReader(FilePassenger));
        AfP.setText("Out");      int i = 1;     String Text = "";
        while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";  i++;}
        TAfP.setText(Text);
        IP.setText("Output passenger file");
    }//Вивід файла Пасажира OutP
    //Add---------------------------------------------------------------------------------------------------------------
    @FXML protected void
    AddtoFilePassenger()
    {
        RedactP=0;CountRedactP=0;CountDeleteP=0;
        CountAddP = 1;AfP.setText("Adding");
        IP.setText("Edit Where"); TFfP.setPromptText("Where");
    }//Вписати Пасажира до файлу  AddP
    //Redact------------------------------------------------------------------------------------------------------------
    @FXML protected void
    RedactFilePassenger()
    throws FileNotFoundException {
        CountAddP = 0; AddP = 0;CountDeleteP=0;
        Scanner scanner = new Scanner(new FileReader(FilePassenger));
        AfP.setText("Redact");CountRedactP = 1;int i = 1; String Text = "";

        while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";i++;}
        TAfP.setText(Text);
        TFfP.setPromptText("Edit line");
        IP.setText("Edit line what you want \nredact");
    }//Редагувати конкретну строку в файлі Пасажира RedactP
    //Delete------------------------------------------------------------------------------------------------------------
    @FXML protected void
    DeleteFilePassenger()
    throws IOException {
        CountAddP = 0;CountRedactP = 0;CountDeleteP = 1;
        AfP.setText("Delete");IP.setText("Edit line whar you \nwant delete");TFfP.setPromptText("Line Delete");
        Scanner scanner = new Scanner(new FileReader(FilePassenger));int i = 1;String Text = "";
        while (scanner.hasNextLine()) {Text = Text + i + " - " + scanner.nextLine() + "\n";  i++;}
        TAfP.setText(Text);
    }
    //Sort--------------------------------------------------------------------------------------------------------------
    @FXML protected  void
    SortFilePassenger()
    throws IOException{
        CountRedactP = 0;CountAddP = 0;AddP=0;RedactD=0;
        AfP.setText("𝐒𝐨𝐫𝐭");ArrayList<String> Line = new ArrayList<>();

        try (BufferedReader r = new BufferedReader(new FileReader(FilePassenger))) {String data;
            while ((data = r.readLine()) != null) {Line.add(data);}}
        catch (IOException e) {throw new RuntimeException(e);}

        Collections.sort(Line);

        try (FileWriter writer = new FileWriter(FilePassenger, false)){
            for (String data : Line) {writer.write(data + "\n");}}
    }
    //Search------------------------------------------------------------------------------------------------------------
    @FXML protected void
    SearchP()
    {
        CountDeleteP=0;CountRedactP=0;CountAddP=0;
        AddP=0;RedactP=0;SearchP = 1;

        IP.setText("Edit what you want find");TFfP.setPromptText("Edit text");
        AfP.setText("Search");
    }
    //Enter keyboard----------------------------------------------------------------------------------------------------
    @FXML protected void
    PressEnterP
    (KeyEvent event) throws IOException {
        if (event.getCode()== KeyCode.ENTER){

            Class_Passenger passenger = new Class_Passenger();
            StringBuilder sbP = new StringBuilder();
            Scanner scannerP = new Scanner(new FileReader(FilePassenger));
            StringBuilder newTextP = new StringBuilder();


            if (CountAddP == 1){
                AddP ++;
                if (AddP == 1){
                    passenger.setWhere(TFfP.getText());
                    FCP += "Where:"+ passenger.getWhere();

                    TFfP.setText("");
                    AfP.setText("Where to");
                    IP.setText("Edit Where to"); TFfP.setPromptText("Where To");
                }
                if (AddP == 2){
                    passenger.setWhereTo(TFfP.getText());
                    FCP += " WhereTo:"+passenger.getWhereTo();

                    TFfP.setText("");
                    AfP.setText("Cost");
                    IP.setText("Edit Cost\n(Only numbers!)"); TFfP.setPromptText("Cost");
                }
                if (AddP == 3){
                    try {passenger.setCost(Integer.parseInt(TFfP.getText()));}
                    catch (NumberFormatException ex){
                        System.out.println("Error Add");
                        passenger.setCost(0);
                    }
                    FCP += " Cost:"+passenger.getCost()+"\n";
                    TFfP.setText("");
                    TAfP.setText(FCP);

                    try (FileWriter writer = new FileWriter(FilePassenger,true)){
                        writer.write(FCP);}
                    catch (IOException e){
                        TAfP.setText("\nError");
                    }
                    FCP = "";
                    TFfP.setText("");
                    AfP.setText("Action");
                }
            }/*Add*/
            if (CountRedactP == 1){
                RedactP ++;
                if (RedactP == 1){
                    try {line = Integer.parseInt(TFfP.getText());}
                    catch (NumberFormatException ex)
                    {
                        System.out.println("Error Adding Set standard value -> 0");
                        RedactP = 0;
                        AfP.setText("Action");
                        FCP = "";
                    }
                    TFfP.setText("");TFfP.setPromptText("Where");
                    TFfP.setPromptText("Where");IP.setText("Edit Where");
                }
                if (RedactP == 2){
                    passenger.setWhere(TFfP.getText());
                    FCP += "Where:"+passenger.getWhere();

                    TFfP.setText("");TFfP.setPromptText("Where to");
                    AfP.setText("Where to");IP.setText("Edit Where to");
                }
                if (RedactP == 3){
                    passenger.setWhereTo(TFfP.getText());
                    FCP += ",Where to:"+passenger.getWhereTo();

                    TFfP.setText("");TFfP.setPromptText("Cost");
                    AfP.setText("Cost");IP.setText("Edit Cost\n(in numbers!)\nif you enter not numbers\n" + "we set -> 0");
                }
                if (RedactP == 4){

                    try{passenger.setWhereTo(TFfP.getText());}
                    catch (NumberFormatException ex){
                        System.out.println("Error redact Cost set -> 0");
                        passenger.setCost(0);
                    }
                    FCP += ",Cost:"+passenger.getCost();
                    TFfP.setText("");TFfP.setPromptText("Edit");
                    AfP.setText("Action");

                    while (scannerP.hasNext()){sbP.append(scannerP.nextLine()).append("\n");}
                    scannerP.close();

                    String[] textArray = sbP.toString().split("\n");
                    textArray[line - 1] = FCP;

                    for (String st : textArray) {
                        newTextP.append(st).append("\n");
                    }
                    File newFile = new File(FilePassenger);
                    newFile.delete();
                    newFile.createNewFile();

                    FileWriter fileWriter = new FileWriter(newFile);
                    fileWriter.write(newTextP.toString());
                    fileWriter.close();

                    FCP = "";AfP.setText("Action");

                    Scanner sc = new Scanner(new FileReader(FilePassenger));
                    int i = 1;
                    String Text = "";
                    while (sc.hasNextLine()){
                        Text += i +" - " + sc.nextLine() + "\n";
                        i++;
                    }
                    TAfP.setText(Text);
                }
            }/*Redact*/
            if (CountDeleteP == 1){
                line = 0;
                try {line = Integer.parseInt(TFfP.getText());}
                catch (NumberFormatException ex){System.out.println("Error Delete");}
                FileReader fileReader = null;
                try {fileReader = new FileReader(FilePassenger);}
                catch (FileNotFoundException e) {throw new RuntimeException(e);}

                Scanner scanner2 = new Scanner(fileReader);
                String[] lineArray = new String[100];
                int i = 0;
                while (scanner2.hasNextLine()) {
                    lineArray[i] = scanner2.nextLine();
                    i++;
                }
                scanner2.close();
                FileWriter fileWriter1 = null;
                try {fileWriter1 = new FileWriter(FilePassenger);}
                catch (IOException e) {throw new RuntimeException(e);}
                for (int j = 0; j < i; j++) {if (j != line - 1) {fileWriter1.write(lineArray[j] + "\n");}}
                fileWriter1.close();
                Scanner sc = new Scanner(new FileReader(FilePassenger));
                int j = 1;
                String Text = "";
                while (sc.hasNextLine()) {
                    Text = Text + j + " - " + sc.nextLine() + "\n";
                    j++;
                }
                TAfP.setText(Text);
                TFfD.setText("");
            }/*Delete*/
            if (SearchP == 1){
               String subject = TFfP.getText();TFfP.setText("");
               try{
                   FileReader fileReader = new FileReader(FilePassenger);
                   Scanner sc = new Scanner(fileReader);
                   while (sc.hasNextLine()){
                       String line = sc.nextLine();
                       if (line.contains(subject)){
                           TAfP.setText(line);
                       }
                   }
               }catch (IOException e){
                   System.out.println("Error Search");
               }
            }
        }
    }


        @FXML private Label AfD;        //Action for Drivers
        @FXML private Label AfP;        //Action for Passenger

        @FXML private TextField TFfD;   //TextField for Driver
        @FXML private TextField TFfP;   //TextField for Passengers

        @FXML private TextArea ID;      //TextArray for Drivers Information
        @FXML private TextArea IP;      //TextArray for Passengers Information

        @FXML private TextArea TAfP;    //TextArray for Passengers
        @FXML private TextArea TAfD;    //TextArray for Drivers

        String FCD = "";                //File Content Driver
        String FCP = "";                //File Content Passenger

    Filetxt filetxt = new Filetxt();
        String FileDriver = filetxt.getFileD();
        String FilePassenger = filetxt.getFileP();

        int AddD = 0; int RedactD = 0; int CountAddD = 0; int CountRedactD = 0; int CountDeleteD =0;int AddP = 0; int SearchP=0;int SearchD=0;
        int RedactP = 0; int CountAddP = 0; int CountRedactP = 0; int CountDeleteP = 0;String fileContent ="";int line = 0;
}
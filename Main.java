import java.io.File;
import java.util.EmptyStackException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    // Să se scrie un program care, prin intermediul clasei File, să permită navigarea în structura de directoare din sistem.

    public static void main(String[] args) {

        String[] listOfFiles;
        String nextCommand;
        String instruction;
        String fileName = null;
        Stack<String> locationHistory = new Stack<>();
        Scanner userInput = new Scanner(System.in);
        final String root = "C:";
        String currentLocation = root;
        System.out.print(currentLocation + '>');
        File file;
        int separator;

        nextCommand = userInput.nextLine();

        while(!nextCommand.isEmpty() && nextCommand.compareTo("exit") != 0){

            try{
                separator = nextCommand.indexOf(" ");
                instruction = nextCommand.substring(0,separator);
                fileName = nextCommand.substring(separator + 1);
            }catch (StringIndexOutOfBoundsException exception){
                instruction = nextCommand;
            }

            switch (instruction) {
                case "list":
                {
                    file = new File(currentLocation);
                    listOfFiles = file.list();
                    for (int i = 0; i < listOfFiles.length; i++) {
                        System.out.println(listOfFiles[i]);
                    }
                    System.out.print(currentLocation + '>');
                    break;
                }
                case "movin":
                {
                    String tempLocation = currentLocation + "//" + fileName;
                    File tempFile = new File(tempLocation);
                    if(tempFile.exists()){
                        currentLocation = tempLocation;
                        locationHistory.push(currentLocation);
                        System.out.print(currentLocation + '>');
                    }
                    else{
                        System.out.println("There's no such path...");
                        System.out.print(currentLocation + '>');
                    }
                    break;
                }
                case "movout":{
                    try{
                        locationHistory.pop();
                        currentLocation = locationHistory.peek();
                    }catch(EmptyStackException exception1){
                        currentLocation = root;
                    }finally {
                        System.out.print(currentLocation + '>');
                    }
                    break;

                }
                case "root":{
                    currentLocation = root;
                    locationHistory.push(currentLocation);
                    System.out.print(currentLocation + '>');
                    break;
                }
                case "mkdir":{
                    file = new File(fileName);
                    file.mkdir();
                    break;
                }
                case default:{
                    System.out.println("Incorrect instruction...");
                    System.out.print(currentLocation + '>');
                }
            }
            nextCommand = userInput.nextLine();
        }

    }
}

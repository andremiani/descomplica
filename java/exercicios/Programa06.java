import java.awt.Color;
import java.awt.Image;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Programa06 {

    public static void main(String[] args) {

        // Variables
        boolean useJOptionPane = false;
        double result;
        String message;

        Scanner scanner = new Scanner(System.in);

        // Check if the argument "--help" is passed
        if (args.length > 0 && args[0].equals("--help")) {
            displayHelp();
            System.exit(0);
        }

        // Check if the argument "--joptionpane" is passed
        if (args.length > 0 && args[0].equals("--joptionpane")) {
            useJOptionPane = true;
        }

        // Get data from user
        String values = getValueFromInput(useJOptionPane, "Insert a numeric array: ", scanner);
        int[] numericArray = convertToArray(values);

        String operationInput = getValueFromInput(useJOptionPane, "Digite 1 para somatória ou 2 para produtória: ",
                scanner);
        int operation = Integer.parseInt(operationInput);

        scanner.close();

        // Processing
        switch (operation) {
            case 1:
                result = calculateSummative(numericArray);
                message = "A somatória de " + Arrays.toString(numericArray) + " = " + result + "\n\n";
                break;

            case 2:
                result = calculateProductive(numericArray);
                message = "A produtória de " + Arrays.toString(numericArray) + " = " + result + "\n\n";
                break;

            default:
                message = "Opção inválida";
        }

        Object[] data = { message };

        // Output
        displayResults(data, useJOptionPane);

        System.exit(0);
    }

    private static int[] convertToArray(String data) {
        // Divide a string em partes usando espaço ou vírgula como separador
        String[] inputParts = data.split("[,\\s]+");

        // Cria um array de inteiros com o mesmo tamanho do array de strings
        int[] result = new int[inputParts.length];

        // Converte cada string para inteiro e armazena no array de inteiros
        for (int i = 0; i < inputParts.length; i++) {
            result[i] = Integer.parseInt(inputParts[i]);
        }

        return result;
    }

    // Input from User
    private static String getValueFromInput(boolean useJOptionPane, String prompt, Scanner scanner) {
        if (useJOptionPane) {
            return showInputDialog(prompt);
        } else {
            return showInputConsole(scanner, prompt);
        }
    }

    private static int calculateSummative(int[] vector) {
        int summative = 0;

        for (int i = 0; i < vector.length; i++) {
            summative += vector[i];
        }
        return summative;
    }

    private static int calculateProductive(int[] vector) {
        int productive = 1;

        for (int i = 0; i < vector.length; i++) {
            productive *= vector[i];
        }
        return productive;
    }

    private static String showInputDialog(String prompt) {
        String message = headerASCIIArt() + prompt;
        return JOptionPane.showInputDialog(null, message, "Faculdade Descomplica", JOptionPane.PLAIN_MESSAGE);
    }

    private static String showInputConsole(Scanner scanner, String prompt) {
        System.out.println(headerASCIIArt());
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static void showCustomPanel(String message) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 240, 255));
        JLabel label = new JLabel(
                "<html><br /><pre>" + headerASCIIArt() + "</pre><br /><br />"
                        + message.replaceAll("(\r\n|\n)", "<br />")
                        + "</html>");
        label.setForeground(new Color(30, 60, 90));
        panel.add(label);

        ImageIcon icon = new ImageIcon("assets/icon/calculator.png");
        Image resizedImage = icon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JOptionPane.showMessageDialog(null, panel, "Faculdade Descomplica", JOptionPane.INFORMATION_MESSAGE,
                resizedIcon);
    }

    private static void displayResults(Object[] data, boolean useJOptionPane) {
        String message = (String) data[0];

        if (useJOptionPane) {
            showCustomPanel(message);
        } else {
            System.out.println("Calculated Results:\n" + message);
        }
    }

    // Header ASCII Art
    private static String headerASCIIArt() {
        return """
                ░█▀▄░█▀▀░█▀▀░█▀▀░█▀█░█▄█░█▀█░█░░░▀█▀░█▀▀░█▀█
                ░█░█░█▀▀░▀▀█░█░░░█░█░█░█░█▀▀░█░░░░█░░█░░░█▀█
                ░▀▀░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░░░▀▀▀░▀▀▀░▀▀▀░▀░▀
                *André Miani -- ADS -- github.com/andremiani*
                """;
    }

    // Display Help
    private static void displayHelp() {
        String helpMessage = """

                Usage: java DataFlow [options]

                Options:
                  --joptionpane    Use graphical user interface for input and output
                  --help           Display this help message and exit

                Example:
                  java DataFlow --joptionpane
                  This will run the program using JOptionPane dialogs.
                """;
        System.out.println(headerASCIIArt());
        System.out.println(helpMessage);
    }
}

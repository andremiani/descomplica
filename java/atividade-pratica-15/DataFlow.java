import java.awt.Color;
import java.awt.Image;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataFlow {

    public static void main(String[] args) {

        // variables
        boolean useJOptionPane = false;
        double result;
        String message;

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
        int n1 = getValueFromInput(useJOptionPane, "Insert the first number: ");
        int n2 = getValueFromInput(useJOptionPane, "Insert the second number: ");
        int operation = getValueFromInput(useJOptionPane, "Digite 1 para produto\nDigite 2 para potência: ");

        // Processing
        switch (operation) {
            case 1:
                result = calculateProduct(n1, n2);
                message = "Produto de " + n1 + " por " + n2 + " = " + result + "\n\n";
                break;

            case 2:
                result = calculatePower(n1, n2);
                message = "Potência de " + n1 + " elevado a " + n2 + " = " + result + "\n\n";
                break;

            default:
                message = "Opção inválida";
        }

        Object[] data = { message };

        // Output
        displayResults(data, useJOptionPane);

        System.exit(0);
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

    // Input from User
    private static int getValueFromInput(boolean useJOptionPane, String prompt) {
        if (useJOptionPane) {
            return showInputDialog(prompt);
        } else {
            return showInputConsole(prompt);
        }
    }

    private static double calculateProduct(int n1, int n2) {
        return (double) n1 * n2;
    }

    private static double calculatePower(int n1, int n2) {
        return Math.pow(n1, n2);
    }

    private static int showInputDialog(String prompt) {
        String message = headerASCIIArt() + prompt;
        return Integer.parseInt(JOptionPane.showInputDialog(null, message,
                "Faculdade Descomplica", JOptionPane.PLAIN_MESSAGE));
    }

    private static int showInputConsole(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(headerASCIIArt());
        System.out.print(prompt);
        int result = scanner.nextInt();
        scanner.close();
        return result;
    }

    private static void showCustomPanel(String message) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 240, 255));
        JLabel label = new JLabel("<html>" + message.replaceAll("(\r\n|\n)", "<br />") + "</html>");
        label.setForeground(new Color(30, 60, 90));
        panel.add(label);

        ImageIcon icon = new ImageIcon("assets/icon/calculator.png");
        Image resizedImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JOptionPane.showMessageDialog(null, panel, "Calculated Results", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
    }

    private static void displayResults(Object[] data, boolean useJOptionPane) {
        String message = (String) data[0];

        if (useJOptionPane) {
            showCustomPanel(message);
        } else {
            System.out.println("Calculated Results:\n" + message);
        }
    }
}

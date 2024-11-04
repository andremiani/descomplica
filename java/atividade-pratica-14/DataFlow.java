// -------------------------------------------------------------------------------------
// Programa Java de Fluxo de Dados
// -------------------------------------------------------------------------------------
// OK <= Desenvolva um programa em Java que declara variáveis inteiras, reais e String, 
// OK <= recebe dois números inteiros usando interface com usuário, 
// OK <=calcula o quociente da divisão dos dois números, 
// OK <= a potência do primeiro número pelo segundo número
// OK <= mostra essas informações usando interface com usuário.
// -------------------------------------------------------------------------------------
// Extras:
// 
// Help Command: 
// java DataFlow --help
//
// Use graphical user interface for input and output
// java DataFlow --joptionpane
// -------------------------------------------------------------------------------------

import java.awt.Color;
import java.awt.Image;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DataFlow {

    public static void main(String args[]) {

        // variables
        boolean useJOptionPane = false;

        // Check if the argument "--help" is passed
        if (args.length > 0 && args[0].equals("--help")) {
            displayHelp();
            System.exit(0); // Exit after displaying help
        }

        // Check if the argument "--joption" is passed and set useJOptionPane to true
        if (args.length > 0 && args[0].equals("--joptionpane")) {
            useJOptionPane = true;
        }

        // get data from user
        int[] userInputNumbers = getUserInput(useJOptionPane);

        // processing
        double quotient = CalculateQuotiente(userInputNumbers);
        double power = CalculateMathPower(userInputNumbers);

        // output
        displayResults(quotient, power, useJOptionPane);

        System.exit(0);

    }

    // Partial Function to display header information
    private static String _header_descomplica_ascii_art() {
        return """
                ░█▀▄░█▀▀░█▀▀░█▀▀░█▀█░█▄█░█▀█░█░░░▀█▀░█▀▀░█▀█
                ░█░█░█▀▀░▀▀█░█░░░█░█░█░█░█▀▀░█░░░░█░░█░░░█▀█
                ░▀▀░░▀▀▀░▀▀▀░▀▀▀░▀▀▀░▀░▀░▀░░░▀▀▀░▀▀▀░▀▀▀░▀░▀
                * Andrẽ Miani -ADS- github.com/andremiani *
                 """;
    }

    // Function to display help information
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
        System.out.println(_header_descomplica_ascii_art());
        System.out.println(helpMessage);
    }

    private static int[] getUserInput(boolean useJOptionPane) {
        int n1, n2;
        String field_n1_title = "Enter the first number: ";
        String field_n2_title = "Enter the second number: ";
        if (useJOptionPane) {
            // Captura de dados usando interface gráfica
            n1 = getNumberFromUser(field_n1_title);
            n2 = getNumberFromUser(field_n2_title);
        } else {
            // Captura de dados usando console
            try (Scanner scanner = new Scanner(System.in)) {

                // header
                System.out.println(_header_descomplica_ascii_art());

                // Solicita e lê o primeiro número
                System.out.print(field_n1_title);
                n1 = scanner.nextInt();

                // Solicita e lê o segundo número
                System.out.print(field_n2_title);
                n2 = scanner.nextInt();
            }
        }

        return new int[] { n1, n2 };
    }

    private static double roundWithBigDecimal(double value) {
        // System.out.println(value);
        double roundedValue = new BigDecimal(Double.toString(value))
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
        return (double) roundedValue;

    }

    private static double CalculateQuotiente(int[] numbers) {
        int n1 = numbers[0];
        int n2 = numbers[1];
        return (double) (int) n1 / (int) n2;
    }

    private static double CalculateMathPower(int[] numbers) {
        int n1 = numbers[0];
        int n2 = numbers[1];
        double pow = Math.pow(n1, n2);
        return (double) pow;

    }

    private static int getNumberFromUser(String field_title) {

        // Display the custom input dialog
        // return (int) Integer.parseInt(JOptionPane.showInputDialog(null,
        // field_title));

        String message = _header_descomplica_ascii_art() + field_title;

        return Integer.parseInt(JOptionPane.showInputDialog(null, message,
                "Faculdade Descomplica - Atividade Prática 14", JOptionPane.NO_OPTION));

    }

    private static void showCustomPanel(String message) {

        JPanel panel = new JPanel();
        panel.setBackground(new Color(230, 240, 255)); // Cor de fundo personalizada
        message = message.replaceAll("(\r\n|\n)", "<br />");
        JLabel label = new JLabel("<html>" + message + "</html>");
        label.setForeground(new Color(30, 60, 90)); // Cor do texto
        panel.add(label);

        // Cria um ícone personalizado (use um caminho de imagem ou um ImageIcon)
        ImageIcon originalIcon = new ImageIcon("assets/icon/calculator.png"); // Substitua pelo caminho do seu ícone

        // Redimensiona o ícone para 150px
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        // Exibe o JOptionPane com o JPanel e o ícone
        JOptionPane.showMessageDialog(null, panel, "Calculated Results", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
    }

    private static void displayResults(double quotient, double pow, boolean useJOptionPane) {

        String message = "Quotient (n1/n2): " + roundWithBigDecimal(quotient) + "\nPower (n1^n2): " + pow;

        if (useJOptionPane) {
            // Exibe a message em uma janela
            showCustomPanel(message);
        } else {
            System.out.println("Calculated Results: \n" + message);
        }

    }

}

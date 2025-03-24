package org.example;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import org.example.commands.CommandsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);


        System.out.println("Активные профили: " + String.join(", ", context.getEnvironment().getActiveProfiles()));


        CommandsManager commandsManager = context.getBean(CommandsManager.class);
        Scanner scanner = new Scanner(System.in);


        commandsManager.printHelp();

        while (true) {
            String input = scanner.nextLine();

            if ("exit".equalsIgnoreCase(input)) {
                break;
            }
            commandsManager.execute(input);

        }
    }
}
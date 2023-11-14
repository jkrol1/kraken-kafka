package com.krakenkafkapipeline;

import com.krakenkafkapipeline.cli.ConsumeSubCommand;
import com.krakenkafkapipeline.cli.KrakenKafkaCommand;
import com.krakenkafkapipeline.cli.ProduceSubCommand;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        CommandLine commandLine = new CommandLine(new KrakenKafkaCommand());
        commandLine.addSubcommand("produce", new ProduceSubCommand(appConfig));
        commandLine.addSubcommand("consume", new ConsumeSubCommand(appConfig));
        commandLine.execute(args);
    }
}

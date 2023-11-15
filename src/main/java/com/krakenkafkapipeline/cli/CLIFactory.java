package com.krakenkafkapipeline.cli;

import com.krakenkafkapipeline.AppConfig;
import picocli.CommandLine;

public class CLIFactory {
    public static CommandLine create(AppConfig appConfig) {
        CommandLine commandLine = new CommandLine(new KrakenKafkaCommand());
        commandLine.addSubcommand("produce", new ProduceSubCommand(appConfig));
        commandLine.addSubcommand("consume", new ConsumeSubCommand(appConfig));
        return commandLine;
    }
}

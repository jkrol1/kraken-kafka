package com.krakenkafkapipeline.cli;

import static picocli.CommandLine.Command;

import com.krakenkafkapipeline.AppConfig;
import com.krakenkafkapipeline.kafka.Consumer;

@Command(name = "consume")
public class ConsumeSubCommand extends BaseSubCommand{

    public ConsumeSubCommand(AppConfig appConfig) {
        super(appConfig);
    }

    @Override
    public void run() {
        new Consumer(appConfig).run();
    }
}

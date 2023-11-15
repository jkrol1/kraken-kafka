package com.krakenkafkapipeline;

import com.krakenkafkapipeline.cli.CLIFactory;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        CommandLine cli = CLIFactory.create(appConfig);
        cli.execute(args);
    }
}

package com.krakenkafkapipeline.cli;

import com.krakenkafkapipeline.AppConfig;

public class BaseSubCommand {
    protected final AppConfig appConfig;

    public BaseSubCommand(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}

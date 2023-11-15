package com.krakenkafkapipeline.cli;

import com.krakenkafkapipeline.AppConfig;

public abstract class BaseSubCommand implements Runnable{
    protected final AppConfig appConfig;

    public BaseSubCommand(AppConfig appConfig) {
        this.appConfig = appConfig;
    }
}

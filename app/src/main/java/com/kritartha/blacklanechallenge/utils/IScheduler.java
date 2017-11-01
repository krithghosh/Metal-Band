package com.kritartha.blacklanechallenge.utils;

import rx.Scheduler;

/**
 * Created by kritarthaghosh on 01/11/17.
 */

public interface IScheduler {

    Scheduler mainThread();

    Scheduler backgroundThread();
}
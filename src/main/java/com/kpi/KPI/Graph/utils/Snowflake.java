
package com.kpi.KPI.Graph.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Snowflake {

    private static final Logger LOG = LoggerFactory.getLogger(Snowflake.class);
    private long sequence = 0L;
    private static final long CUST_EPOC = 1293840000000L;
    private static final long WORKER_ID_BITS = 6L;
    private static final long DATACENTER_ID_BITS = 4L;
    private static final int MAX_WORKER_ID = 63;
    private static final long SEQUENCE_BITS = 12L;
    private static final long WORKER_ID_SHIFT = 12L;
    private static final long DATACENTER_ID_SHIFT = 18L;
    private static final long TIMESTAMP_LEFT_SHIFT = 22L;
    private static final long SEQUENCE_MASK = 4095L;
    private long lastTimestamp = -1L;
    private Random random = new Random();

    public Snowflake() {
    }

    public synchronized long newId() {
        long timestamp = System.currentTimeMillis();
        if(timestamp < this.lastTimestamp) {
            LOG.error("Clock is moving backwards.  Rejecting requests until: " + this.lastTimestamp);
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", new Object[]{Long.valueOf(this.lastTimestamp - timestamp)}));
        } else {
            if(this.lastTimestamp == timestamp) {
                this.sequence = this.sequence + 1L & 4095L;
                if(this.sequence == 0L) {
                    timestamp = this.tilNextMillis(this.lastTimestamp);
                }
            } else {
                this.sequence = 0L;
            }

            this.lastTimestamp = timestamp;
            return timestamp - 1293840000000L << 22 | Thread.currentThread().getId() << 18 | (long)(this.random.nextInt(63) << 12) | this.sequence;
        }
    }

    protected long tilNextMillis(long lastRunTimestamp) {
        LOG.info("Waiting for next millisecond. Number of IDs generated for this timestamp exceeded limit.");

        long timestamp;
        for(timestamp = System.currentTimeMillis(); timestamp <= lastRunTimestamp; timestamp = System.currentTimeMillis()) {

        }
        return timestamp;
    }
}

package org.example.txsaga.config;

import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.BackOffExecution;
import org.springframework.util.backoff.ExponentialBackOff;

import java.util.concurrent.ThreadLocalRandom;

/**
 * ExponentialBackOff에 Full Jitter를 적용한 BackOff 구현체.
 * <p>
 * Jitter 적용 공식 (Full Jitter):
 * 실제 대기시간 = random(0, exponential_interval)
 * <p>
 * 여러 컨슈머가 동시에 실패했을 때 동시 재시도(thundering herd)를 방지한다.
 */
public class JitteredExponentialBackOff implements BackOff {

    private final ExponentialBackOff delegate;

    public JitteredExponentialBackOff(long initialInterval, double multiplier, long maxElapsedTime) {
        this.delegate = new ExponentialBackOff(initialInterval, multiplier);
        this.delegate.setMaxElapsedTime(maxElapsedTime);
    }

    @Override
    public BackOffExecution start() {
        return new JitteredExecution(delegate.start());
    }

    private record JitteredExecution(BackOffExecution delegate) implements BackOffExecution {

        @Override
        public long nextBackOff() {
            long interval = delegate.nextBackOff();
            if (interval == STOP) {
                return STOP;
            }
            // Full Jitter: [0, interval) 범위에서 랜덤 선택
            return ThreadLocalRandom.current().nextLong(interval);
        }
    }
}

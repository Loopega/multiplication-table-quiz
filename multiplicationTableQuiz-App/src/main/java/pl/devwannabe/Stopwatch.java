package pl.devwannabe;

import java.time.Duration;


class Stopwatch {
    private long start;
    private long stop;
    private String name;
    public Stopwatch(String name) {
        this.name = name;
    }
    public void start() {
        start = System.currentTimeMillis();
    }
    public void stop() {
        stop = System.currentTimeMillis();
    }
    public String toString() {

        String min = String.format("%s %d min ", name + ": ", Duration.ofMillis(stop - start).toMinutes());
        String sec = String.format("%s s", Duration.ofMillis(stop - start).getSeconds() % 60);
        return min + sec;

    }
}

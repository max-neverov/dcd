package dcd;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Maxim Neverov
 */
public class Metrics {

    @JsonProperty("ManagementFactory.getOperatingSystemMXBean.getAvailableProcessors")
    private final int osProcs;
    @JsonProperty("Runtime.getRuntime.availableProcessors")
    private final int runtimeProcs;
    @JsonProperty("nproc")
    private final int nprocProcs;
    @JsonProperty("fromCICompilerCount")
    private final int fromVmArguments;

    public Metrics(int osProcs, int runtimeProcs, int nprocProcs, int fromVmArguments) {
        this.osProcs = osProcs;
        this.runtimeProcs = runtimeProcs;
        this.nprocProcs = nprocProcs;
        this.fromVmArguments = fromVmArguments;
    }

    public int getOsProcs() {
        return osProcs;
    }

    public int getRuntimeProcs() {
        return runtimeProcs;
    }

    public int getNprocProcs() {
        return nprocProcs;
    }

    public int getFromVmArguments() {
        return fromVmArguments;
    }

}

package dcd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;

/**
 * @author Maxim Neverov
 */
@Controller
@RequestMapping("/cpu")
public class RestController {

    private static final OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();
    private static final RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody Metrics getMetrics() {
        return new Metrics(os.getAvailableProcessors(),
                           Runtime.getRuntime().availableProcessors(),
                           getNprocValue(),
                           Integer.valueOf(getCpuFromVmArguments()));
    }

    private String getCpuFromVmArguments() {
        return runtimeMxBean.getInputArguments().stream()
                .filter(it -> it.startsWith("-XX:CICompilerCount"))
                .findAny().map(it -> it.split("="))
                .orElse(new String[] {"", "-1"})[1];
    }

    private int getNprocValue() {
        int result = -1;
        try {
            Process nproc = Runtime.getRuntime().exec("nproc");
            StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(nproc.getInputStream())));
            in.nextToken();
            return (int)in.nval;
        } catch (IOException ignored) {
            // nproc is not found or not applicable
        }
        return result;
    }

}



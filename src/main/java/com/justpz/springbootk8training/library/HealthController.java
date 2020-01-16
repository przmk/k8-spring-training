package com.justpz.springbootk8training.library;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HealthController {

  private static final Logger log = LoggerFactory.getLogger(HealthController.class);
  public static final String DEFAULT = "Default";
  private final Environment environment;

  public HealthController(Environment environment) {
    this.environment = environment;
  }

  final String hostname = System.getenv().getOrDefault("HOSTNAME", "unknown");
  String greeting;


  private int count = 0; // simple counter to see lifecycle

  RestTemplate restTemplate = new RestTemplate();

  @GetMapping("/")
  public String sayHello() {
    greeting = environment.getProperty("GREETING", "Yoo");
    count++;
    log.info("{} from {} {}", greeting, hostname, count);
    return greeting + " from Spring Boot and pz! " + count + " on " + hostname + "\n";
  }

  @GetMapping("/sysresources")
  public String getSystemResources() {
    long memory = Runtime.getRuntime().maxMemory();
    int cores = Runtime.getRuntime().availableProcessors();
    log.info("/sysresources {}", hostname);
    return
        " Memory: " + (memory / 1024 / 1024) +
            " Cores: " + cores + "\n";
  }

  @GetMapping("/consume")
  public String consumeSome() {
    log.info("/consume {}", hostname);

    Runtime rt = Runtime.getRuntime();
    StringBuilder sb = new StringBuilder();
    long maxMemory = rt.maxMemory();
    long usedMemory = 0;
    // while usedMemory is less than 80% of Max
    while (((float) usedMemory / maxMemory) < 0.80) {
      sb.append(System.nanoTime()).append(sb.toString());
      usedMemory = rt.totalMemory();
    }
    String msg = "Allocated about 80% (" + humanReadableByteCount(usedMemory, false)
        + ") of the max allowed JVM memory size ("
        + humanReadableByteCount(maxMemory, false) + ")";
    log.info(msg);
    return msg + "\n";
  }

  @GetMapping(value = "/health")
  public ResponseEntity<String> health() {
    // if (count++ < 5) {
    //    return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Bad");
    // } else {
    return ResponseEntity.status(HttpStatus.OK)
        .body("I am fine, thank you\n");
    // }
  }

  @GetMapping("/configure")
  public String configure() {
    String databaseConn = environment.getProperty("DBCONN", DEFAULT);
    String msgBroker = environment.getProperty("MSGBROKER", DEFAULT);
    greeting = environment.getProperty("GREETING", DEFAULT);
    String love = environment.getProperty("LOVE", DEFAULT);
    return "Configuration for : " + hostname + "\n"
        + "databaseConn=" + databaseConn + "\n"
        + "msgBroker=" + msgBroker + "\n"
        + "greeting=" + greeting + "\n"
        + "love=" + love + "\n";
  }

  @GetMapping("/callinganother")
  public String callinganother() {

    // <servicename>.<namespace>.svc.cluster.local
    String url = "http://mynode.yourspace.svc.cluster.local:8000/";

    ResponseEntity<String> response
        = restTemplate.getForEntity(url, String.class);

    String responseBody = response.getBody();
    log.info(responseBody);

    return responseBody;
  }

  public static String humanReadableByteCount(long bytes, boolean si) {
    int unit = si ? 1000 : 1024;
    if (bytes < unit) {
      return bytes + " B";
    }
    int exp = (int) (Math.log(bytes) / Math.log(unit));
    String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
    return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
  }

}

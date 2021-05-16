package lorikeet.subsystem;

import java.net.URL;
import java.util.List;

public record SubSystemJAR(List<Class<?>> javaClasses, URL lorikeetFile) { }

package de.davidtobi.javagame.game.util;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JavaCompilerUtil {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static boolean isValidJavaCode(String className, String code) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("No Java compiler available. Run with JDK, not JRE.");
        }

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new InMemoryJavaFile(className, code);
        Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        return task.call();
    }

    public static Class<?> compileAndLoad(String className, String code) throws Exception {
        File sourceFile = new File(className + ".java");
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(code);
        }
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});

        scheduleFileDeletion(className);

        return Class.forName(className, true, classLoader);
    }

    public static Class<?> compileAndLoad(Map<String, String> classCodeMap, String mainClassName) throws Exception {
        for (Map.Entry<String, String> entry : classCodeMap.entrySet()) {
            File sourceFile = new File(entry.getKey() + ".java");
            try (FileWriter writer = new FileWriter(sourceFile)) {
                writer.write(entry.getValue());
            }
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, classCodeMap.keySet().stream()
                .map(className -> className + ".java").toArray(String[]::new));

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});

        // Clean up the source files after compilation

        classCodeMap.keySet().forEach(JavaCompilerUtil::scheduleFileDeletion);

        return Class.forName(mainClassName, true, classLoader);
    }

    // Schedules file deletion 2 seconds after compilation
    private static void scheduleFileDeletion(String className) {
        scheduler.schedule(() -> deleteFiles(className), 2, TimeUnit.SECONDS);
    }

    // Deletes Java source and compiled class files
    private static void deleteFiles(String className) {
        new File(className + ".java").delete(); // Delete source file
        new File(className + ".class").delete(); // Delete compiled class file
    }
}

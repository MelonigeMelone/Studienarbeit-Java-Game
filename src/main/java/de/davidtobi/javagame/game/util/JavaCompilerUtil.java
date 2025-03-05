package de.davidtobi.javagame.game.util;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;

public class JavaCompilerUtil {

    public static void main(String[] args) {
      System.out.println(isValidJavaCode("public class Test { public static void main(String[] args) { System.out.println(\"Hello World\"); } }"));
    }

    public static boolean isValidJavaCode(String code) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            throw new IllegalStateException("No Java compiler available. Run with JDK, not JRE.");
        }

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        JavaFileObject file = new InMemoryJavaFile("Test", code);
        Iterable<? extends JavaFileObject> compilationUnits = Collections.singletonList(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

        return task.call(); // Returns true if compilation is successful, false otherwise
    }

    public static Class<?> compileAndLoad(String code, String className) throws Exception {
        // Prepare source file
        File sourceFile = new File(className + ".java");
        try (FileWriter writer = new FileWriter(sourceFile)) {
            writer.write(code);
        }

        // Compile the Java file
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, null, null, sourceFile.getPath());

        // Load compiled class
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File(".").toURI().toURL()});
        return Class.forName(className, true, classLoader);
    }
}

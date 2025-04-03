package de.davidtobi.javagame.game.codingtask;

import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.util.JavaCompilerUtil;

import java.lang.reflect.Method;
import java.util.HashMap;

public enum CodingTasks {
    GATE_1(new CodingTask("gate_1",
            new CodingTaskClass[]{
                    new CodingTaskClass("Gate",
                            """
                                   boolean torIstOffen = false;
                                   
                                   public boolean isTorOffen() {
                                       return torIstOffen;
                                   }
                                    """,
                            """
                            """)
            },
            new HashMap<>() {{
                put(CodingTaskHelpSequenceType.INITIAL, TextSequences.GATE_1_INITIAL);
            }}) {
        @Override
        public boolean compiledSuccessfully() throws Exception {
            CodingTaskClass codingTaskClass = getCodingTaskClasses()[0];
            Class<?> compiledClass = JavaCompilerUtil.compileAndLoad(codingTaskClass.getClassName(), codingTaskClass.getCurrentCode());

            Object instance = compiledClass.getConstructor().newInstance();
            Method method = compiledClass.getMethod("isTorOffen");

            Object result = method.invoke(instance);

            return result instanceof Boolean && (Boolean) result;
        }
    });

    private final CodingTask codingTask;

    CodingTasks(CodingTask codingTask) {
        this.codingTask = codingTask;
    }

    public CodingTask getCodingTask() {
        return codingTask;
    }
}

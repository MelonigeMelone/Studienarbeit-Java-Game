package de.davidtobi.javagame.game.codingtask;

import de.davidtobi.javagame.game.textsequence.data.TextSequences;
import de.davidtobi.javagame.game.util.JavaCompilerUtil;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum CodingTasks {
    HELLO_WORLD(new CodingTask("hello World","Sende die Nachricht 'Hallo Familie, wir sind gut angekommen' mithilfe von System.out.println() an die Konsole",
            new CodingTaskClass[] {
                    new CodingTaskClass("Main",
                            """
                                        public static void main(String[] args) {
                                           //TODO Write Message
                                        }
                                    
                                    """,
                            """
                            """)
            },
            new HashMap<>() {{
                put(CodingTaskHelpSequenceType.INITIAL, TextSequences.HELLO_WORLD);
                put(CodingTaskHelpSequenceType.TASK_FINISH, TextSequences.HELLO_WORLD_FINISH);
                put(CodingTaskHelpSequenceType.TASK_FAILED, TextSequences.HELLO_WORLD_FAILED);
            }}) {
        @Override
        public boolean compiledSuccessfully() throws Exception {
            // Compile the code dynamically
            CodingTaskClass codingTaskClass = getCodingTaskClasses()[0];
            boolean compiled = JavaCompilerUtil.isValidJavaCode(codingTaskClass.getClassName(), codingTaskClass.getCurrentCode());

            return compiled && codingTaskClass.getCurrentCode().contains("System.out.println") &&
                    codingTaskClass.getCurrentCode().contains("Hallo Familie, wir sind gut angekommen");
        }
    }),
    TEST_FUNCTIONALITY(new CodingTask("test_functionality", "",
            new CodingTaskClass[]{
                    new CodingTaskClass("Main",
                            """
                                    private TestObject testObject;
                                    
                                    public Main() {
                                        testObject = new TestObject("HierNameEintragen");
                                    }
                                    
                                    public TestObject getTestObject() {
                                        return testObject;
                                    }
                                    """,
                            """
                            """),
                    new CodingTaskClass("TestObject",
                            """
                                    private final String name;
                                    
                                    public TestObject(String name) {
                                        this.name = name;
                                    }
                                   
                                    public String getName() {
                                       return name;
                                    }
                                    """,
                            """
                            """)
            },
            new HashMap<>() {{
                put(CodingTaskHelpSequenceType.INITIAL, TextSequences.TEST_CODE);
                put(CodingTaskHelpSequenceType.TASK_FINISH, TextSequences.TEST_CODE_FINISH);
                put(CodingTaskHelpSequenceType.TASK_FAILED, TextSequences.TEST_CODE_FAIL);
            }}) {
        @Override
        public boolean compiledSuccessfully() throws Exception {
            Map<String, String> classCodeMap = new HashMap<>();
            Arrays.stream(getCodingTaskClasses()).forEach(codingTaskClass -> {
                classCodeMap.put(codingTaskClass.getClassName(), codingTaskClass.getCurrentCode());
            });
            Class<?> compiledMainClass = JavaCompilerUtil.compileAndLoad(classCodeMap, "Main");

            Object instance = compiledMainClass.getConstructor().newInstance();

            // Invoke "getTestObject"
            Method getTestObjectMethod = compiledMainClass.getMethod("getTestObject");
            Object testObjectInstance = getTestObjectMethod.invoke(instance);

            if (testObjectInstance == null) {
                return false; // Return false if the object is null
            }

            // Use reflection to call getName() on the returned instance
            Method getNameMethod = testObjectInstance.getClass().getMethod("getName");
            String nameValue = (String) getNameMethod.invoke(testObjectInstance);

            // Verify the expected value
            return "Ideo".equals(nameValue);
        }
    }

    ),
    GATE_1(new CodingTask("gate_1","",
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

package dmme.kuvid.utils;


public class PathHandler {

    private static PathHandler instance = null;

    private PathHandler() {}

    public static PathHandler getInstance() {
        if (instance == null) instance = new PathHandler();
        return instance;
    }

    public String makePath(String... strings) {
        String osName = System.getProperty("os.name").toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        if (osName.contains("mac")) {
            for (int i = 0; i < strings.length; i++) {
                stringBuilder.append(strings[i]);
                if (i != strings.length-1)
                    stringBuilder.append("/");
            }
        } else {
            for (int i = 0; i < strings.length; i++) {
                stringBuilder.append(strings[i]);
                if (i != strings.length-1)
                    stringBuilder.append("\\");
            }
        }
        return stringBuilder.toString();
    }
}

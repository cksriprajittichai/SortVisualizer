package def;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class SorterFactory {

    private static final String PATH_TO_SORTERS_DIR =
            "out/production/SortVisualization/sorters/";

    static Sorter createSorter(final String displayName, final int speed,
                               final UiHelper uiHelper) {
        final String className = convertDisplayNameToClassName(displayName);

        Sorter ret = null;
        try {
            final Class<Sorter> sorterClass =
                    (Class<Sorter>) Class.forName("sorters." + className);
            final Constructor<Sorter> constructor =
                    sorterClass.getDeclaredConstructor(int.class, UiHelper.class);

            ret = constructor.newInstance(speed, uiHelper);
        } catch (final Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    static public List<String> getSorterNames() {
        final File dir = new File(PATH_TO_SORTERS_DIR);

        final ArrayList<String> ret = new ArrayList<>();
        String className;
        for (final String filename : Objects.requireNonNull(dir.list())) {
            className = filename
                    .replace(".class", "")
                    .replace("Sorter", "Sort");
            className = splitUpperCamelCase(className);

            ret.add(className);
        }

        return ret;
    }

    static private String splitUpperCamelCase(final String s) {
        final StringBuilder sb = new StringBuilder(s.length());

        // Loop below adds ' ' before upper case chars. Prevent this for first char
        sb.append(s.charAt(0));

        for (int i = 1; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                sb.append(' ');
            }

            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    static private String convertDisplayNameToClassName(final String s) {
        return s.replaceAll("\\s", "")
                .replaceAll("Sort[^a-zA-Z]*", "Sorter");
    }

}

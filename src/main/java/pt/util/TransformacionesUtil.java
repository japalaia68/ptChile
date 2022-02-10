package pt.util;

/**
 * Utility class just to generate particular dates's formats
 *
 * @date 26/02/2019
 */
public class TransformacionesUtil {

    /**
     * Private constructor
     */
    private TransformacionesUtil() {
    }

    /**
     * Retorna el cuil con guiones
     *
     * @return
     */
    public static String agregarGuionesACuil(String cuil) {
        if (cuil != null && !cuil.isEmpty()) {
            cuil = cuil.replace("-", "");
            StringBuffer sb = new StringBuffer(cuil);
            sb.insert(2, "-");
            sb.insert(cuil.length(), "-");
            return sb.toString();
        }
        return cuil;
    }

}

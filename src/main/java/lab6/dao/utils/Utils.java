package lab6.dao.utils;

import lombok.val;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public interface Utils {
    static <T> Map<T, T> mapOf(T... ts) {
        if (ts.length % 2 != 0) {
            throw new IllegalArgumentException("Invalid length: " + ts.length);
        }
        val result = new HashMap<T, T>(ts.length / 2);
        for (int i = 0; i < ts.length; )
            result.put(ts[i++], ts[i++]);
        return Collections.unmodifiableMap(result);
    }

}

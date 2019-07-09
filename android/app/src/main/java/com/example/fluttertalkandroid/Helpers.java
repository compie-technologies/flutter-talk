package com.example.fluttertalkandroid;

import java.util.Map;

public class Helpers {
    private static String _createParamString(String key, String value) {
        return key + "=" + value;
    }

    public static String attachParams(String originalRoute, Map<String, String> params) {
        if (params != null) {
            int index = 0;
            StringBuilder originalRouteBuilder = new StringBuilder(originalRoute);
            for (Map.Entry<String, String> entry : params.entrySet()) {
                originalRouteBuilder.append(index == 0 ? "?" : "&");
                originalRouteBuilder.append(_createParamString(entry.getKey(), entry.getValue()));
                index++;
            }
            originalRoute = originalRouteBuilder.toString();
        }
        return originalRoute;
    }
}

package ie.licenta.artassistant.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSecurity {

    public static final String API_USERS_URL = "http://localhost:8080/api/users";

    public static final Map<Endpoint, List<Role>> API_SECURITY_URLS = new HashMap<>() {{
        put(new Endpoint("/api/artist/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artist", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artist", MethodType.DELETE), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artwork", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.DELETE), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artworks", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment/{id}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/country/{id}", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artworks/{userId}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artwork}", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artwork/{artworkId}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artists/{userId}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artist}", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artist/{artistId}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/galleries", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/gallery/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/genre/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museum/{id}", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museums", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museum", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museum/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
    }};
}
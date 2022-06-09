package ie.licenta.artassistant.security;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiSecurity {

    public static final List<Endpoint> API_OPEN_URLS = Arrays.asList(
            new Endpoint("/api/country", MethodType.GET),
            new Endpoint("/api/country/{id}", MethodType.GET),
            new Endpoint("/api/galleries", MethodType.GET),
            new Endpoint("/api/museum/{id}", MethodType.GET),
            new Endpoint("/api/museums", MethodType.GET)
    );

    public static final Map<Endpoint, List<Role>> API_SECURITY_URLS = new HashMap<>() {{
        put(new Endpoint("/api/artist/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artist/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artist", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artist", MethodType.DELETE), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artwork", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artwork/{id}", MethodType.DELETE), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/artworks", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comments", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/comment/{id}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
//        put(new Endpoint("/api/country", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
//        put(new Endpoint("/api/country/{id}", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artworks", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artwork", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artwork/{artworkId}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artists", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artist", MethodType.POST), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/favourite_artist/{artistId}", MethodType.DELETE), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/gallery/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/gallery/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/gallery", MethodType.POST), Arrays.asList(Role.ADMIN));
//        put(new Endpoint("/api/galleries", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/genre/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/genre/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/genre", MethodType.POST), Arrays.asList(Role.ADMIN));
//        put(new Endpoint("/api/museum/{id}", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
//        put(new Endpoint("/api/museums", MethodType.GET), Arrays.asList(Role.GUEST, Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museum", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/museum", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/museum/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/room/{id}", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/rooms", MethodType.GET), Arrays.asList(Role.USER, Role.ADMIN));
        put(new Endpoint("/api/room", MethodType.POST), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/room/{id}", MethodType.PUT), Arrays.asList(Role.ADMIN));
        put(new Endpoint("/api/room/{id}", MethodType.DELETE), Arrays.asList(Role.ADMIN));
    }};
}

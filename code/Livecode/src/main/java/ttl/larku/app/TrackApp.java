package ttl.larku.app;

import ttl.larku.domain.Track;

import java.time.Duration;

/**
 * @author whynot
 */
public class TrackApp {

    public static void main(String[] args) {
        Track t = new Track();
        t.setAlbum("Misty Rain");
        t.setDuration(Duration.ofMinutes(30));

        Track t2 = new Track();
        t.setAlbum("Dry Rain");
        t.setDuration(Duration.ofMinutes(10));

        Track t3 = new Track.Builder()
                .album("Misty Rain")
                .duration(Duration.ofMinutes(30))
                .build();

        Track t4 = Track
                .album("Misty Rain")
                .duration(Duration.ofMinutes(30))
                .build();

    }
}

package com.edgesoft.common;

/**
 * This class represents a Song.
 * A Song has a title and a duration.
 */
public class Song {
    private String title;
    private double duration;

    /**
     * Retrieves the title of the Song.
     *
     * @return The title of the Song.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the duration of the Song.
     *
     * @return The duration of the Song as a double value.
     */
    public double getDuration() {
        return duration;
    }

    /**
     * Constructs a new Song object with the given title and duration.
     *
     * @param title The title of the song.
     * @param duration The duration of the song as a double value.
     */
    public Song(String title, double duration) {
        this.title = title;
        this.duration = duration;
    }

    /**
     * Returns a string representation of the Song object.
     * The string representation includes the title and duration of the song.
     *
     * @return A string representation of the Song object.
     */
    @Override
    public String toString() {
        return "Song{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                '}';
    }
}

package com.morestudio.craftify.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "created_at")
    private Date createdAt;
    @ColumnInfo(name = "isPinned")
    private Boolean isPinned;

    public Note() {
    }

    public Note(int id, String title, String content, Date createdAt, Boolean isPinned) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.isPinned = isPinned;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getPinned() {
        return isPinned;
    }

    public void setPinned(Boolean pinned) {
        isPinned = pinned;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return id == note.id && Objects.equals(title, note.title) && Objects.equals(content, note.content) && Objects.equals(createdAt, note.createdAt) && Objects.equals(isPinned, note.isPinned);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, createdAt, isPinned);
    }

    @NonNull
    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdAt=" + createdAt +
                ", isPinned=" + isPinned +
                '}';
    }
}

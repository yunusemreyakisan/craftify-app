package com.morestudio.craftify.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date
import java.util.UUID


@Entity(tableName = "note")
class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "noteId") var noteId: Int = 0,
    @ColumnInfo(name = "title") var title: String?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "created_at") var createdAt: String?,
    @ColumnInfo(name = "isPinned") var isPinned: Boolean?
) {
    constructor(noteId: Int, title: String?, content: String?, createdAt: String?) : this(
        noteId = noteId,
        title = title,
        content = content,
        createdAt = createdAt,
        isPinned = null
    )

    override fun toString(): String {
        return "Note(noteId=$noteId, title=$title, content=$content, createdAt=$createdAt, isPinned=$isPinned)"
    }
}


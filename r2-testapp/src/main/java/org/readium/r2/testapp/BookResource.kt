package org.readium.r2.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.flow.*
import org.json.JSONArray
import org.readium.r2.testapp.db.BooksDao
import org.readium.r2.testapp.domain.model.Bookmark
import org.readium.r2.testapp.domain.model.Highlight
import java.util.*

object BookResource {
    private var highlights: MutableStateFlow<MutableList<Highlight>> = MutableStateFlow(mutableListOf())
//    private var _uiState: MutableStateFlow<MutableList<Highlight>> = MutableStateFlow(mutableListOf())

    lateinit var notes: String

    suspend fun cacheData(bookmarksValue: String?, highlightsValue: String?, notesValue: String?) {
//        this.highlightsInternal = highlightsValue?.let {
//            val data = mutableListOf<Highlight>()
//            val array = JSONArray(highlightsValue)
//            for (index in 0 until array.length()) {
//                val item = array.getJSONObject(index)
//                data.add(Highlight.fromJson(item))
//            }
//            data
//        } ?: mutableListOf()

        this.notes = ""
    }

    fun getHighlights(): MutableStateFlow<MutableList<Highlight>> {
        return highlights
    }

    suspend fun add(highlight: Highlight) {
        highlight.id = UUID.randomUUID().toString()
        highlights.value.add(highlight)
    }

    fun findHighlightById(id: String): Highlight? {
        highlights.value.forEach {
            if (it.id == id) {
                return it
            }
        }
        return null
    }

//    fun highlightsForBook(repository: BooksDao, bookId: Long): Flow<List<Highlight>> =
//        booksDao.getHighlightsForBook(bookId)
//

    suspend fun removeFromHighlights(highlightId: String) {
        highlights.value.forEach {
            if (it.id == highlightId) {
                highlights.value.remove(it)
                return
            }
        }
    }
}
package com.example.instaflix.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.instaflix.data.local.entity.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeys>)

    @Query("Select * From remote_key Where movie_id = :id AND categoryId = :category")
    suspend fun getRemoteKeyByMovieIDFilterByCategory(id: Int, category: Int): RemoteKeys?

    @Query("Delete From remote_key Where categoryId = :category")
    suspend fun clearRemoteKeys(category: Int)

    @Query("Select created_at From remote_key Where categoryId = :category Order By created_at DESC LIMIT 1")
    suspend fun getCreationTime(category: Int): Long?
}
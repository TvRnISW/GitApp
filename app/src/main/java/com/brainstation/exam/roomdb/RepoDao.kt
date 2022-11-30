package com.brainstation.exam.roomdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.brainstation.exam.model.GitRepoResponse
import com.brainstation.exam.model.Item

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemEntity: List<Item>)

    @Query("select * from tblrepo order by id desc")
    suspend fun getRepoDataFromRoom(): List<Item>
}
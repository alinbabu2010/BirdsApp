package data.repos

import data.model.BirdInfo

interface BirdsRepository {
    suspend fun getImages(): List<BirdInfo>
    fun closeConnection()
}
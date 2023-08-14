package data.repos

import data.model.BirdInfo
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import util.IMAGE_URL

class BirdsImagesRepository : BirdsRepository {

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    override suspend fun getImages(): List<BirdInfo> {
        return httpClient
            .get(IMAGE_URL)
            .body()
    }

    override fun closeConnection() {
        httpClient.close()
    }

}
package com.example.dgs.directors

import com.example.dgs.generated.DgsConstants
import com.example.dgs.generated.types.Director
import com.example.dgs.generated.types.Show
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import graphql.schema.DataFetchingEnvironment
import org.dataloader.DataLoader
import org.springframework.beans.factory.annotation.Autowired
import java.util.concurrent.CompletableFuture

@DgsComponent
class DirectorsDataFetcher {

    @Autowired
    lateinit var directorsService: DirectorsService


    @DgsData(
        parentType = DgsConstants.SHOW.TYPE_NAME, field = DgsConstants
            .SHOW.Director
    )
    fun director(dfe: DataFetchingEnvironment):
        CompletableFuture<Director> {

        val dataLoader: DataLoader<String, Director> =
            dfe.getDataLoader("directors")

        val show: Show = dfe.getSource()
        return dataLoader.load(show.id)
    }
}
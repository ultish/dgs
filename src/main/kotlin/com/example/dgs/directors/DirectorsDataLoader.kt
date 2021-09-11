package com.example.dgs.directors

import com.example.dgs.generated.types.Director
import com.netflix.graphql.dgs.DgsDataLoader
import org.dataloader.MappedBatchLoader
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionStage
import kotlin.streams.toList

@DgsDataLoader(name = "directors")
class DirectorsDataLoader(val directorsService: DirectorsService) :
    MappedBatchLoader<String, Director> {

//    @Autowired
//    lateinit var directorsService: DirectorsService

//    override fun load(keys: MutableList<String>): CompletionStage<MutableList<Director>> =
//        CompletableFuture.supplyAsync {
//            directorsService.directorForShows(keys.stream().toList())
//                .toMutableList()
//        }

    override fun load(keys: MutableSet<String>): CompletionStage<Map<String,
        Director>> {
        return CompletableFuture.supplyAsync {
            directorsService.directorForShows(keys.stream().toList())
        }
    }
}